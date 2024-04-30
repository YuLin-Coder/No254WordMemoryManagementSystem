package com.ian.media.util;

/**
 * 
 */
import java.util.UUID;

/**
 * 
 * @author Michael
 * 生成32位ID
 */
public class IDGenerator {
	
	/**
	 * 生成ID
	 * @return
	 */
	public static String getID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
