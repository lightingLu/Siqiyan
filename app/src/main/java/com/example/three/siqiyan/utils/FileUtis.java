package com.example.three.siqiyan.utils;

import java.io.File;
import android.os.Environment;
public class FileUtis{
	public static final String CACHE = "cache";
	public static final String ICON = "icon";
	public static final String ROOT = "Siqiyan";
	/**
	 * 获取图片的缓存的路径
	 * @return
	 */
	public static File getIconDir(){
		return getDir(ICON);
	}
	/**
	 * 获取缓存路径
	 * @return
	 */
	public static File getCacheDir() {
		return getDir(CACHE);
	}
	public static File getDir(String cache) {
		StringBuilder path = new StringBuilder();
		if (isSDAvailable()) {
			path.append(Environment.getExternalStorageDirectory()
					.getAbsolutePath());//sd卡的根目录
			path.append(File.separator);// '/'
			path.append(ROOT);// /mnt/sdcard/GooglePlay
			path.append(File.separator);
			path.append(cache);// /mnt/sdcard/GooglePlay/cache
		}else{
			File filesDir = Uiutils.getContext().getCacheDir();    //  cache路径  getFileDir file路径
			path.append(filesDir.getAbsolutePath());// /data/data/com.itheima.googleplay/cache
			path.append(File.separator);///data/data/com.itheima.googleplay/cache/
			path.append(cache);///data/data/com.itheima.googleplay/cache/cache
		}
		File file = new File(path.toString());
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();// 创建文件夹
		}
		return file;
	}
/**
 * 判断sdcard是否有用
 * @return
 */
	private static boolean isSDAvailable() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}


}

