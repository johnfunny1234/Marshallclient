package net.lax1dude.eaglercraft.v1_8.marshall;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.minecraft.client.settings.GameSettings;

/**
 * Periodically pulls Marshall overlay preferences from the active platform and
 * applies them to the running client.
 */
public class MarshallFeatureController {

    private static final long REFRESH_INTERVAL_MS = 500L;
    private static MarshallPreferences cachedPrefs = new MarshallPreferences();
    private static long lastRefresh = 0L;
    private static boolean lastFullbright = false;

    public static void apply(GameSettings settings) {
        if (settings == null) {
            return;
        }

        refreshPreferences();

        if (Float.isNaN(settings.marshallBaseGamma)) {
            settings.marshallBaseGamma = settings.gammaSetting;
        }

        settings.marshallFullbright = cachedPrefs.fullbright;
        settings.marshallNoHurtCam = cachedPrefs.noHurtCam;
        settings.marshallCompassHud = cachedPrefs.compassHud;
        if (cachedPrefs.loaded) {
            settings.hudCoords = cachedPrefs.coordinatesOverlay;
        }

        if (cachedPrefs.fullbright) {
            if (!lastFullbright) {
                settings.marshallBaseGamma = settings.gammaSetting;
            }
            settings.gammaSetting = Math.max(settings.marshallBaseGamma, 15.0f);
        } else {
            if (lastFullbright && !Float.isNaN(settings.marshallBaseGamma)) {
                settings.gammaSetting = settings.marshallBaseGamma;
            }
            settings.marshallBaseGamma = settings.gammaSetting;
        }

        lastFullbright = cachedPrefs.fullbright;
    }

    private static void refreshPreferences() {
        long now = EagRuntime.steadyTimeMillis();
        if (now - lastRefresh < REFRESH_INTERVAL_MS) {
            return;
        }
        cachedPrefs = MarshallPreferencesLoader.load();
        lastRefresh = now;
    }
}
