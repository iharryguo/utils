package harryguo.utils;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author harryguo
 * 这个加密解密和nodejs的加解密是互通的
 */
public class AESUtils2
{
	public static final String DEFAULT_CODING = "utf-8";

	//解密
	public static String decryptAES(String encrypted, String seed) {
        try {
    		byte[] keyb = seed.getBytes(DEFAULT_CODING);
    		MessageDigest md = MessageDigest.getInstance("MD5");
    		byte[] thedigest = md.digest(keyb);
    		SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
    		Cipher dcipher = Cipher.getInstance("AES");
    		dcipher.init(Cipher.DECRYPT_MODE, skey);

    		byte[] clearbyte = dcipher.doFinal(toByte(encrypted));
    		return new String(clearbyte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	//加密
	public static String encryptAES(String content, String key) {
        try {
    		byte[] input = content.getBytes(DEFAULT_CODING);

    		MessageDigest md = MessageDigest.getInstance("MD5");
    		byte[] thedigest = md.digest(key.getBytes(DEFAULT_CODING));
    		SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
    		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    		cipher.init(Cipher.ENCRYPT_MODE, skc);

    		byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
    		int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
    		ctLength += cipher.doFinal(cipherText, ctLength);

    		return parseByte2HexStr(cipherText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	//字符串转字节
	private static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
		}
		return result;
	}

	//字节转16进制数组
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex);
		}
		return sb.toString();
	}
}
