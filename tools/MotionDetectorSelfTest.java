package hr.drenovac.gp8stoperica;

public final class MotionDetectorSelfTest {
    public static void main(String[] args) {
        MotionDetector d = new MotionDetector();
        d.configure(0.45f, 0.18f, 2f);
        long t = 0L;
        d.arm(t);

        boolean started = false;
        boolean finish = false;
        for (int i = 0; i < 220; i++, t += 10_000_000L) d.addSample(0f, 0f, 0f, t);
        for (int i = 0; i < 140; i++, t += 10_000_000L) {
            if (d.addSample(2f, 0.4f, 0f, t) == MotionDetector.Event.STARTED) started = true;
        }
        if (!started) throw new AssertionError("START nije prepoznat");

        // Simulated GP8 cornering motion until well beyond the 15 s guard.
        for (int i = 0; i < 1700; i++, t += 10_000_000L) {
            float x = (i % 200 < 100) ? 1.4f : -1.4f;
            d.addSample(x, 0.35f, 0f, t);
        }
        for (int i = 0; i < 400; i++, t += 10_000_000L) {
            if (d.addSample(0f, 0f, 0f, t) == MotionDetector.Event.FINISH_CANDIDATE) finish = true;
        }
        if (!finish || d.getFinishCandidateAt() == 0L) throw new AssertionError("CILJ nije prepoznat");
        if (!d.isRunning()) throw new AssertionError("Sat mora raditi do tipke STOP");
        System.out.println("MotionDetector self-test: OK");
    }
}
