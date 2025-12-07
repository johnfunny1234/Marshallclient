package net.lax1dude.eaglercraft.v1_8.marshall;

import org.json.JSONObject;
import org.teavm.jso.browser.Storage;
import org.teavm.jso.browser.Window;

/**
 * TeaVM implementation that pulls toggle state from the Marshall UI's
 * localStorage payload. The UI saves preferences under the
 * "marshallClientPrefs" key as a JSON object.
 */
public class MarshallPreferencesLoader {

    public static MarshallPreferences load() {
        MarshallPreferences prefs = new MarshallPreferences();
        try {
            Storage storage = Window.current().getLocalStorage();
            if (storage != null) {
                String raw = storage.getItem("marshallClientPrefs");
                if (raw != null && raw.length() > 0) {
                    JSONObject obj = new JSONObject(raw);
                    prefs.fullbright = obj.optBoolean("fullbright", false);
                    prefs.noHurtCam = obj.optBoolean("noHurtCam", false);
                    prefs.coordinatesOverlay = obj.optBoolean("coordinatesOverlay", false);
                    prefs.compassHud = obj.optBoolean("compassHud", false);
                    prefs.loaded = true;
                }
            }
        } catch (Throwable t) {
            // ignore and fall back to defaults
        }
        return prefs;
    }

}
