package com.merusphere.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class IciciCryptoProvider {
	private static SecureRandom secureRandom = new SecureRandom();
	private static Cipher encCipher = null;
	private static Cipher decCipher = null;
	private static X509Certificate bankPubKey;
	private static RSAPublicKey bankRsaPubKey;

	/**
	 *  To encrypt the String Data using Public Key
	 * @param plainText
	 * @param filePath
	 * @return byte[]
	 */
	public static byte[] getCipher64(String plainText, String filePath) {
		try {
			InputStream inStream1 = IciciCryptoProvider.class.getClassLoader().getResourceAsStream(filePath);
			if (inStream1 == null) {
				throw new IOException("Public key file not found in resources");
			}
			// Sample public key (Base64-encoded)
			byte[] publicKeyBytes = inStream1.readAllBytes();

			encCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			try (ByteArrayInputStream inStream = new ByteArrayInputStream(publicKeyBytes)) {
				bankPubKey = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(inStream);
				bankRsaPubKey = (RSAPublicKey) bankPubKey.getPublicKey();
			}
			encCipher.init(Cipher.ENCRYPT_MODE, bankRsaPubKey, secureRandom);
			return encCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *  To Decrypt the String Data using Private Key
	 * @param cipher64
	 * @param filePath
	 * @return 
	 */
	public static byte[] getPlainText(String cipher64, String filePath) {
		try {
			InputStream inStream1 = IciciCryptoProvider.class.getClassLoader().getResourceAsStream(filePath);
			if (inStream1 == null) {
				throw new IOException("Public key file not found in resources");
			}
			// Sample public key (Base64-encoded)
			byte[] prvKeyBytes = inStream1.readAllBytes();

			decCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

			String appPrvKeyData = new String(prvKeyBytes);
			appPrvKeyData = appPrvKeyData.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "")
					.replace("-----END PRIVATE KEY-----", "");
			PKCS8EncodedKeySpec sayukthPrvKeyFileSpec = new PKCS8EncodedKeySpec(
					Base64.getDecoder().decode(appPrvKeyData));
			PrivateKey sayukthPrvKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
					.generatePrivate(sayukthPrvKeyFileSpec);
			decCipher.init(Cipher.DECRYPT_MODE, sayukthPrvKey, secureRandom);
			byte[] cipherByte = org.bouncycastle.util.encoders.Base64.decode(cipher64);
			return decCipher.doFinal(cipherByte);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

}