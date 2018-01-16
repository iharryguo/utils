package harryguo;

import harryguo.utils.AESUtils2;

/**
 * 
 * @author harryguo
 * 
 */
public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("程序开始");
		String key = "Lonely-Girl*";
		String originalStr = System.currentTimeMillis() / 1000 + "";
		String encryptedStr = AESUtils2.encryptAES(originalStr, key);
		if (encryptedStr != null)
			System.out.println("加密后:" + encryptedStr);
		else
			System.out.println("加密结果为空");

		String decryptedStr = AESUtils2.decryptAES(encryptedStr, key);
		if (encryptedStr != null)
			System.out.println("解密后:" + decryptedStr);
		else
			System.out.println("解密结果为空");
		System.out.println("程序结束...");
	}
}
