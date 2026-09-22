#!/usr/bin/env bash
set -euo pipefail

PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"
SDK_ROOT="${ANDROID_SDK_ROOT:-${ANDROID_HOME:-}}"
BUILD_TOOLS_VERSION="${BUILD_TOOLS_VERSION:-35.0.0}"
PLATFORM_VERSION="${PLATFORM_VERSION:-35}"

if [[ -z "$SDK_ROOT" ]]; then
  echo "Postavi ANDROID_SDK_ROOT na instalirani Android SDK." >&2
  exit 1
fi

BT="$SDK_ROOT/build-tools/$BUILD_TOOLS_VERSION"
ANDROID_JAR="$SDK_ROOT/platforms/android-$PLATFORM_VERSION/android.jar"
OUT="$PROJECT_DIR/manual-build"
SRC="$PROJECT_DIR/app/src/main"

for tool in "$BT/aapt2" "$BT/d8" "$BT/zipalign" "$BT/apksigner" "$ANDROID_JAR"; do
  [[ -e "$tool" ]] || { echo "Nedostaje: $tool" >&2; exit 1; }
done

rm -rf "$OUT"
mkdir -p "$OUT/compiled-res" "$OUT/classes" "$OUT/dex"
"$BT/aapt2" compile --dir "$SRC/res" -o "$OUT/compiled-res/resources.zip"
"$BT/aapt2" link -o "$OUT/base.apk" -I "$ANDROID_JAR" --manifest "$SRC/AndroidManifest.xml" \
  --java "$OUT/generated" --min-sdk-version 26 --target-sdk-version 35 \
  --version-code 1 --version-name 1.0 \
  "$OUT/compiled-res/resources.zip"

if command -v javac >/dev/null 2>&1; then
  find "$SRC/java" -name '*.java' -print0 | xargs -0 javac -source 8 -target 8 -encoding UTF-8 \
    -classpath "$ANDROID_JAR" -d "$OUT/classes"
elif [[ -n "${ECJ_JAR:-}" && -f "$ECJ_JAR" ]]; then
  java -jar "$ECJ_JAR" -source 8 -target 8 -encoding UTF-8 -classpath "$ANDROID_JAR" \
    -d "$OUT/classes" $(find "$SRC/java" -name '*.java')
else
  echo "Nedostaje javac (JDK 17) ili ECJ_JAR." >&2
  exit 1
fi
"$BT/d8" --lib "$ANDROID_JAR" --min-api 26 --output "$OUT/dex" $(find "$OUT/classes" -name '*.class')
(cd "$OUT/dex" && zip -q "$OUT/base.apk" classes.dex)
"$BT/zipalign" -f 4 "$OUT/base.apk" "$OUT/GP8-Stoperica-unsigned.apk"

KEYSTORE="$PROJECT_DIR/gp8-debug.keystore"
if [[ ! -f "$KEYSTORE" ]]; then
  keytool -genkeypair -keystore "$KEYSTORE" -storepass gp8stoperica -keypass gp8stoperica \
    -alias gp8 -keyalg RSA -keysize 2048 -validity 10000 -dname "CN=GP8 Stoperica, O=Drenovac, C=HR" >/dev/null
fi
"$BT/apksigner" sign --ks "$KEYSTORE" --ks-pass pass:gp8stoperica --key-pass pass:gp8stoperica \
  --out "$OUT/GP8-Stoperica-v1.0.apk" "$OUT/GP8-Stoperica-unsigned.apk"
"$BT/apksigner" verify --verbose "$OUT/GP8-Stoperica-v1.0.apk"
echo "$OUT/GP8-Stoperica-v1.0.apk"
