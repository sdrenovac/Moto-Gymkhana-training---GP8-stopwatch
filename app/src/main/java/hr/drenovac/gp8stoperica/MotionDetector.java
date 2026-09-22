package hr.drenovac.gp8stoperica;

/** Sensor-only state machine. Timestamps are monotonic nanoseconds. */
public final class MotionDetector {
    public enum Event { NONE, CALIBRATED, STARTED, FINISH_CANDIDATE, FINISH_CANCELLED }

    private static final long CALIBRATION_NS = 2_000_000_000L;
    private static final long START_CONFIRM_NS = 300_000_000L;
    private static final long MIN_RUN_NS = 15_000_000_000L;

    private float startThreshold = 0.45f;
    private float stopThreshold = 0.18f;
    private long stopConfirmNs = 2_000_000_000L;
    private boolean armed;
    private boolean running;
    private long armedAt;
    private long startedAt;
    private long aboveSince;
    private long quietSince;
    private long finishCandidateAt;
    private long movingAgainSince;
    private float smoothX, smoothY, smoothZ;
    private float baselineSum;
    private int baselineCount;
    private float baseline;
    private boolean calibrated;

    public void configure(float start, float stop, float stopSeconds) {
        startThreshold = start;
        stopThreshold = stop;
        stopConfirmNs = (long) (stopSeconds * 1_000_000_000L);
    }

    public void arm(long now) {
        armed = true; running = false; armedAt = now; startedAt = 0L;
        aboveSince = 0L; quietSince = 0L; finishCandidateAt = 0L; movingAgainSince = 0L;
        baselineSum = 0f; baselineCount = 0; baseline = 0f; calibrated = false;
        smoothX = smoothY = smoothZ = 0f;
    }

    public void reset() { armed = false; running = false; }
    public boolean isArmed() { return armed; }
    public boolean isRunning() { return running; }
    public long getStartedAt() { return startedAt; }
    public long getFinishCandidateAt() { return finishCandidateAt; }
    public float getBaseline() { return baseline; }

    public Event addSample(float x, float y, float z, long now) {
        if (!armed) return Event.NONE;

        // Low-pass the linear-acceleration vector. It suppresses engine/road vibration
        // while retaining the sustained acceleration/deceleration of the motorcycle.
        final float alpha = 0.08f;
        smoothX += alpha * (x - smoothX);
        smoothY += alpha * (y - smoothY);
        smoothZ += alpha * (z - smoothZ);
        float energy = (float) Math.sqrt(smoothX * smoothX + smoothY * smoothY + smoothZ * smoothZ);

        if (!running && now - armedAt < CALIBRATION_NS) {
            baselineSum += energy;
            baselineCount++;
            return Event.NONE;
        }
        if (!running && baselineCount > 0 && !calibrated) {
            baseline = baselineSum / baselineCount;
            calibrated = true;
            return Event.CALIBRATED;
        }

        float adjusted = Math.max(0f, energy - baseline);
        if (!running) {
            if (adjusted >= startThreshold) {
                if (aboveSince == 0L) aboveSince = now;
                if (now - aboveSince >= START_CONFIRM_NS) {
                    running = true; startedAt = now; quietSince = 0L; finishCandidateAt = 0L;
                    return Event.STARTED;
                }
            } else {
                aboveSince = 0L;
            }
            return Event.NONE;
        }

        if (now - startedAt < MIN_RUN_NS) return Event.NONE;
        if (finishCandidateAt == 0L) {
            if (adjusted <= stopThreshold) {
                if (quietSince == 0L) quietSince = now;
                if (now - quietSince >= stopConfirmNs) {
                    finishCandidateAt = quietSince;
                    movingAgainSince = 0L;
                    return Event.FINISH_CANDIDATE;
                }
            } else {
                quietSince = 0L;
            }
        } else {
            // Tolerate engine vibration and small movements after the finish.
            // Only clear the candidate if the motorcycle clearly moves again.
            if (adjusted > startThreshold * 2f) {
                if (movingAgainSince == 0L) movingAgainSince = now;
                if (now - movingAgainSince >= 500_000_000L) {
                    finishCandidateAt = 0L; quietSince = 0L; movingAgainSince = 0L;
                    return Event.FINISH_CANCELLED;
                }
            } else {
                movingAgainSince = 0L;
            }
        }
        return Event.NONE;
    }
}
