package net.lax1dude.eaglercraft.v1_8.marshall;

/**
 * Simple value object describing the state of the Marshall Client overlay
 * toggles. This class is shared across platforms; platform specific loaders
 * populate it from local storage or other data sources.
 */
	public class MarshallPreferences {

public boolean fullbright;
public boolean noHurtCam;
public boolean coordinatesOverlay;
public boolean compassHud;
public boolean loaded;

}
