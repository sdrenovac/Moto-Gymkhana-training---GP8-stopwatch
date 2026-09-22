package hr.drenovac.gp8stoperica;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public final class MainActivity extends Activity implements SensorEventListener {
    private static final int BG = Color.rgb(9, 11, 14);
    private static final int WHITE = Color.rgb(247, 249, 252);
    private static final int MUTED = Color.rgb(160, 169, 183);
    private static final int ACCENT = Color.rgb(217, 255, 53);
    private static final int RED = Color.rgb(255, 77, 77);

    private final MotionDetector detector = new MotionDetector();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private SensorManager sensorManager;
    private Sensor motionSensor;
    private boolean rawAccelerometer;
    private final float[] gravity = new float[3];
    private TextView timer;
    private TextView status;
    private TextView hint;
    private TextView resultDetails;
    private Button mainButton;
    private Button stopButton;
    private long displayedStartNs;
    private long frozenElapsedMs;
    private long buttonStopElapsedMs;
    private SharedPreferences prefs;

    private final Runnable ticker = new Runnable() {
        @Override public void run() {
            if (detector.isRunning()) {
                long elapsed = (SystemClock.elapsedRealtimeNanos() - displayedStartNs) / 1_000_000L;
                timer.setText(formatTime(elapsed));
            }
            handler.postDelayed(this, 16L);
        }
    };

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        prefs = getSharedPreferences("gp8", MODE_PRIVATE);
        buildUi();
        applySettings();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        motionSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        if (motionSensor == null) {
            motionSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            rawAccelerometer = true;
        }
        if (motionSensor == null) {
            status.setText("AKCELEROMETAR NIJE DOSTUPAN");
            mainButton.setEnabled(false);
        }
        handler.post(ticker);
    }

    private void buildUi() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int widthDp = Math.round(metrics.widthPixels / metrics.density);
        int heightDp = Math.round(metrics.heightPixels / metrics.density);
        boolean landscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        boolean compactHeight = heightDp < 650 || landscape;

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(BG);
        scroll.setClipToPadding(false);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        int horizontalPadding = widthDp < 360 ? 14 : (landscape ? 28 : 22);
        int verticalPadding = compactHeight ? 12 : 22;
        root.setPadding(dp(horizontalPadding), dp(verticalPadding),
                dp(horizontalPadding), dp(verticalPadding));
        root.setBackgroundColor(BG);

        TextView title = text("GP8  •  5× OSMICA", compactHeight ? 16 : 18, ACCENT, Typeface.BOLD);
        root.addView(title, matchWrap());

        status = text("SPREMNO", compactHeight ? 20 : 24, WHITE, Typeface.BOLD);
        LinearLayout.LayoutParams statusLp = matchWrap();
        statusLp.topMargin = dp(compactHeight ? 10 : 24);
        root.addView(status, statusLp);

        int timerSp = widthDp < 360 ? 46 : (landscape ? 48 : 58);
        timer = text("00:00.000", timerSp, WHITE, Typeface.BOLD);
        timer.setGravity(Gravity.CENTER);
        timer.setSingleLine(true);
        LinearLayout.LayoutParams timerLp = new LinearLayout.LayoutParams(-1,
                dp(compactHeight ? 82 : 150));
        root.addView(timer, timerLp);

        resultDetails = text("", compactHeight ? 15 : 18, WHITE, Typeface.BOLD);
        resultDetails.setGravity(Gravity.CENTER);
        root.addView(resultDetails, matchWrap());

        hint = text("Pritisni PRIPREMA prije dolaska na start.\nVrijeme kreće automatski pri polasku.",
                compactHeight ? 14 : 16, MUTED, Typeface.NORMAL);
        hint.setGravity(Gravity.CENTER);
        root.addView(hint, matchWrap());

        mainButton = button("PRIPREMA", ACCENT, BG);
        LinearLayout.LayoutParams btnLp = new LinearLayout.LayoutParams(-1, dp(compactHeight ? 60 : 68));
        btnLp.topMargin = dp(compactHeight ? 12 : 18);
        root.addView(mainButton, btnLp);
        mainButton.setOnClickListener(v -> armOrReset());

        stopButton = button("STOP", RED, Color.WHITE);
        LinearLayout.LayoutParams stopLp = new LinearLayout.LayoutParams(-1, dp(compactHeight ? 56 : 60));
        stopLp.topMargin = dp(8);
        root.addView(stopButton, stopLp);
        stopButton.setVisibility(View.GONE);
        stopButton.setOnClickListener(v -> manualStop());

        Button settings = button("POSTAVKE SENZORA", Color.rgb(35, 40, 49), WHITE);
        LinearLayout.LayoutParams settingsLp = new LinearLayout.LayoutParams(-1, dp(compactHeight ? 50 : 54));
        settingsLp.topMargin = dp(8);
        root.addView(settings, settingsLp);
        settings.setOnClickListener(v -> showSettings());

        scroll.addView(root, new ScrollView.LayoutParams(-1, -2));
        setContentView(scroll);
    }

    private void armOrReset() {
        if (detector.isArmed() || detector.isRunning()) {
            detector.reset();
            setReady();
            return;
        }
        applySettings();
        frozenElapsedMs = 0L;
        buttonStopElapsedMs = 0L;
        resultDetails.setText("");
        timer.setText("00:00.000");
        detector.arm(SystemClock.elapsedRealtimeNanos());
        status.setText("KALIBRACIJA…");
        status.setTextColor(ACCENT);
        hint.setText("Ne diraj motocikl 2 sekunde.\nZatim kreni kada budeš spreman.");
        mainButton.setText("ODUSTANI");
        stopButton.setVisibility(View.GONE);
        vibrate(80);
    }

    private void started() {
        displayedStartNs = detector.getStartedAt();
        status.setText("MJERENJE");
        status.setTextColor(ACCENT);
        hint.setText("Vozi 5× osmicu, zaustavi motocikl\ni zatim pritisni STOP.");
        mainButton.setText("ODUSTANI");
        stopButton.setVisibility(View.VISIBLE);
        vibrate(160);
    }

    private void manualStop() {
        if (!detector.isRunning()) return;
        long now = SystemClock.elapsedRealtimeNanos();
        buttonStopElapsedMs = (now - displayedStartNs) / 1_000_000L;
        long candidate = detector.getFinishCandidateAt();
        long gp8Elapsed = candidate > 0L
                ? (candidate - displayedStartNs) / 1_000_000L
                : buttonStopElapsedMs;
        finishRun(Math.max(0L, gp8Elapsed), candidate > 0L);
    }

    private void finishRun(long elapsedMs, boolean detectedFinish) {
        detector.reset();
        frozenElapsedMs = elapsedMs;
        timer.setText(formatTime(elapsedMs));
        status.setText("CILJ");
        status.setTextColor(WHITE);
        long confirmationMs = Math.max(0L, buttonStopElapsedMs - elapsedMs);
        if (detectedFinish) {
            resultDetails.setText("START → STOP tipka: " + formatTime(buttonStopElapsedMs)
                    + "\nNakon zaustavljanja: +" + formatTime(confirmationMs));
            hint.setText("Veliki broj je GP8 vrijeme do prvog završnog mirovanja.");
        } else {
            resultDetails.setText("START → STOP tipka: " + formatTime(buttonStopElapsedMs)
                    + "\nMirovanje nije prepoznato");
            hint.setText("GP8 vrijeme uzeto je s tipke STOP.");
        }
        mainButton.setText("NOVA VOŽNJA");
        stopButton.setVisibility(View.GONE);
        prefs.edit().putLong("last_time", elapsedMs).apply();
        vibrate(450);
    }

    private void setReady() {
        status.setText("SPREMNO");
        status.setTextColor(WHITE);
        timer.setText(frozenElapsedMs > 0 ? formatTime(frozenElapsedMs) : "00:00.000");
        hint.setText("Pritisni PRIPREMA prije dolaska na start.\nVrijeme kreće automatski pri polasku.");
        resultDetails.setText("");
        mainButton.setText("PRIPREMA");
        stopButton.setVisibility(View.GONE);
    }

    @Override public void onSensorChanged(SensorEvent event) {
        float x = event.values[0], y = event.values[1], z = event.values[2];
        if (rawAccelerometer) {
            final float a = 0.8f;
            gravity[0] = a * gravity[0] + (1f - a) * x;
            gravity[1] = a * gravity[1] + (1f - a) * y;
            gravity[2] = a * gravity[2] + (1f - a) * z;
            x -= gravity[0]; y -= gravity[1]; z -= gravity[2];
        }
        MotionDetector.Event result = detector.addSample(x, y, z, event.timestamp);
        if (result == MotionDetector.Event.CALIBRATED) {
            status.setText("ČEKA POLAZAK");
            hint.setText("Kalibrirano. Kreni kada budeš spreman.");
        } else if (result == MotionDetector.Event.STARTED) {
            started();
        } else if (result == MotionDetector.Event.FINISH_CANDIDATE) {
            status.setText("CILJ PREPOZNAT");
            status.setTextColor(ACCENT);
            hint.setText("Motocikl miruje. Pritisni STOP.\nGP8 vrijeme je već zapamćeno.");
        } else if (result == MotionDetector.Event.FINISH_CANCELLED) {
            status.setText("MJERENJE");
            hint.setText("Kretanje je nastavljeno; prethodno mirovanje je poništeno.");
        }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) { }
    @Override protected void onResume() {
        super.onResume();
        if (motionSensor != null) sensorManager.registerListener(this, motionSensor, SensorManager.SENSOR_DELAY_GAME);
    }
    @Override protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        if (detector.isRunning()) Toast.makeText(this, "Mjerenje je prekinuto jer je aplikacija napuštena.", Toast.LENGTH_LONG).show();
        detector.reset();
        setReady();
    }
    @Override protected void onDestroy() { handler.removeCallbacks(ticker); super.onDestroy(); }

    private void applySettings() {
        detector.configure(prefs.getFloat("start_threshold", 0.45f),
                prefs.getFloat("stop_threshold", 0.18f), prefs.getFloat("stop_seconds", 2f));
    }

    private void showSettings() {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(dp(24), dp(10), dp(24), 0);
        SeekBar start = setting(box, "Osjetljivost polaska", 10, 100,
                Math.round(prefs.getFloat("start_threshold", .45f) * 100f));
        SeekBar stop = setting(box, "Prag mirovanja", 5, 40,
                Math.round(prefs.getFloat("stop_threshold", .18f) * 100f));
        SeekBar seconds = setting(box, "Potvrda mirovanja (1–3 s)", 1, 3,
                Math.round(prefs.getFloat("stop_seconds", 2f)));
        new AlertDialog.Builder(this)
                .setTitle("Postavke senzora")
                .setView(box)
                .setMessage("Početne vrijednosti su preporučene za S25 čvrsto montiran na upravljaču.")
                .setPositiveButton("SPREMI", (d, w) -> {
                    prefs.edit()
                            .putFloat("start_threshold", start.getProgress() / 100f)
                            .putFloat("stop_threshold", stop.getProgress() / 100f)
                            .putFloat("stop_seconds", seconds.getProgress())
                            .apply();
                    applySettings();
                })
                .setNegativeButton("ODUSTANI", null)
                .setNeutralButton("POČETNE", (d, w) -> {
                    prefs.edit().clear().apply(); applySettings();
                }).show();
    }

    private SeekBar setting(LinearLayout parent, String label, int min, int max, int value) {
        TextView t = text(label, 15, Color.DKGRAY, Typeface.BOLD);
        parent.addView(t, matchWrap());
        SeekBar bar = new SeekBar(this);
        bar.setMin(min); bar.setMax(max); bar.setProgress(value);
        parent.addView(bar, new LinearLayout.LayoutParams(-1, dp(48)));
        return bar;
    }

    private void vibrate(long ms) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (v != null && v.hasVibrator()) v.vibrate(VibrationEffect.createOneShot(ms, VibrationEffect.DEFAULT_AMPLITUDE));
    }

    private static String formatTime(long ms) {
        long minutes = ms / 60_000L;
        long seconds = (ms / 1_000L) % 60L;
        long millis = ms % 1_000L;
        return String.format(Locale.US, "%02d:%02d.%03d", minutes, seconds, millis);
    }

    private TextView text(String value, int sp, int color, int style) {
        TextView v = new TextView(this); v.setText(value); v.setTextSize(sp); v.setTextColor(color);
        v.setTypeface(Typeface.create("sans", style)); v.setGravity(Gravity.CENTER); return v;
    }
    private Button button(String value, int background, int foreground) {
        Button b = new Button(this); b.setText(value); b.setTextSize(18); b.setTypeface(null, Typeface.BOLD);
        b.setTextColor(foreground); b.setBackgroundColor(background); return b;
    }
    private LinearLayout.LayoutParams matchWrap() { return new LinearLayout.LayoutParams(-1, -2); }
    private int dp(int n) { return Math.round(n * getResources().getDisplayMetrics().density); }
}
