package harryguo.utils;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 
 * @author harryguo
 * 
 */
public class DESUtils
{
	/**
	 * Des加密方法
	 * 
	 * @param source
	 * @param key
	 */
	public static String encrypt(byte[] source, String key)
	{
		try
		{
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory skeyFactory = null;
			Cipher cipher = null;
			try
			{
				skeyFactory = SecretKeyFactory.getInstance("DES");
				cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			}
			catch (NoSuchAlgorithmException e)
			{
				e.printStackTrace();
				return null;
			}
			SecretKey deskey = skeyFactory.generateSecret(desKeySpec);
			cipher.init(Cipher.ENCRYPT_MODE, deskey);
			byte[] cipherText = cipher.doFinal(source);
			StringBuffer sb = new StringBuffer();
			for (int n = 0; n < cipherText.length; n++)
			{
				String stmp = (java.lang.Integer.toHexString(cipherText[n] & 0XFF));
	
				if (stmp.length() == 1)
				{
					sb.append("0" + stmp);
				}
				else
				{
					sb.append(stmp);
				}
			}
			return sb.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密（使用DES算法）
	 * 
	 * @param encryptedStr 需要解密的文本
	 * @param key 密钥
	 * @return 成功解密的文本
	 */
	public static byte[] decrypt(String encryptedStr, String key)
	{
		try
		{
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory skeyFactory = null;
			Cipher cipher = null;
			try
			{
				skeyFactory = SecretKeyFactory.getInstance("DES");
				cipher = Cipher.getInstance("DES");
			}
			catch (NoSuchAlgorithmException e)
			{
				e.printStackTrace();
			}
			SecretKey deskey = skeyFactory.generateSecret(desKeySpec);
			cipher.init(Cipher.DECRYPT_MODE, deskey);
			byte[] btxts = new byte[encryptedStr.length() / 2];
			for (int i = 0, count = encryptedStr.length(); i < count; i += 2)
			{
				if (i == 270 || i == 272 || i == 271)
				{
					// System.out.println();
				}
				btxts[i / 2] = (byte) Integer.parseInt(encryptedStr.substring(i, i + 2), 16);
			}
			return cipher.doFinal(btxts);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
