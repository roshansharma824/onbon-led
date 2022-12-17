package com.stackfusion.onbon;


import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;

import static com.stackfusion.onbon.Constants.textSize;


public class Utils{

	public static final Logger logger = LogManager.getLogger(Utils.class);

	public static String getEthernetMacAddress(){
        String mac_address = null;
        try {
        	
        	NetworkInterface networkInterfaces = NetworkInterface.getByName("eth0");
        	printLog("eth0 mac "+formatMac(networkInterfaces.getHardwareAddress()));
        	mac_address= formatMac(networkInterfaces.getHardwareAddress());

        } catch (Exception ex) {
			printLog(Level.ERROR,"eth0 get mac exception : "+ ex.getMessage());
        }

        return mac_address;
    }

	private static String formatMac(byte[] hardwareAddress) {
		 if (hardwareAddress != null) {
 	        String[] hexadecimalFormat = new String[hardwareAddress.length];
 	        for (int i = 0; i < hardwareAddress.length; i++) {
 	            hexadecimalFormat[i] = String.format("%02X", hardwareAddress[i]);
 	        }
 	        return String.join(":", hexadecimalFormat);
 	    }
		return null;
	}


	public static void printLog(String message){
		logger.info(message);
//		System.out.println(message);

	}

	public static void printLog(Level level,String message){
		logger.log(level,message);
	}
	public static boolean checkInternet(){
		try {
			URL url = new URL("https://www.google.com/");
			URLConnection connection = url.openConnection();
			connection.connect();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	public static BufferedImage showImageFromURL(String from, String stringUrl) {
		BufferedImage image = null;
		try {
			URL url = new URL(stringUrl);
			image = ImageIO.read(url);

		} catch (IOException e) {
			printLog(Level.ERROR,"IOException in show image from "+from);
			e.printStackTrace();
		}catch (Exception e){
			printLog(Level.ERROR,"Exception in show image from "+from);
			e.printStackTrace();
		}

		return image;
	}

	public static Font setFont(float textSize){
		Font font = null;
		try {
			InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Roboto-Bold.ttf");
			assert stream != null;
			font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(textSize);
			return Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(textSize);
		}catch (Exception e){
			printLog(Level.ERROR,"Exception in setting font");
		}
		return font;
	}

	public static int width(float percentage){
		int width=0;
		width = (int) ((percentage*80)/100);
		return width;
	}
	public static int height(float percentage){
		int height = 0;
		height = (int) ((percentage*40)/100);
		return height;
	}

}
