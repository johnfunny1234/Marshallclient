package net.lax1dude.eaglercraft.v1_8.marshall;

/**
 * Platform neutral stub used in environments that cannot read the Marshall
 * overlay preferences. Platform specific source sets can override this class
 * with an implementation that sources preferences from local storage or other
 * runtime state.
 */
	public class MarshallPreferencesLoader {

	public static MarshallPreferences load() {
		return new MarshallPreferences();
}

}
