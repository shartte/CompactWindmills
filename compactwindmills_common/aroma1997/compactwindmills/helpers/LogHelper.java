
package aroma1997.compactwindmills.helpers;


import java.util.logging.Level;
import java.util.logging.Logger;

import aroma1997.compactwindmills.CompactWindmills;
import aroma1997.compactwindmills.Reference;

import cpw.mods.fml.common.FMLLog;

public class LogHelper {
	
	private static Logger WindmillLog = Logger.getLogger(Reference.ModID);
	
	public static void debugLog(Level level, String message) {
		if (! CompactWindmills.debugMode) {
			return;
		}
		log(level, message);
	}
	
	public static void init() {
		WindmillLog.setParent(FMLLog.getLogger());
	}
	
	public static void log(Level level, String message) {
		WindmillLog.log(level, message);
	}
	
}
