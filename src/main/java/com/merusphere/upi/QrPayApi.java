package com.merusphere.upi;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merusphere.model.QrPay;
import com.merusphere.util.IciciCryptoProvider;

public class QrPayApi {
	public static ObjectMapper objMapper = new ObjectMapper();
	public static final String BANK_PUBLIC_KEY = "bank_public_key.crt";
	public static final String ORGANIZATION_PRIVATE_KEY = "organization_private_key.key";

	public static void main(String[] args) {
		final BasicCookieStore cookieStore = new BasicCookieStore();
		QrPayApi obj = new QrPayApi();
		try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build()) {
			obj.qrPayApi(httpclient, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "deprecation" })
	private void qrPayApi(CloseableHttpClient httpclient, String string) throws IOException {
		try {
			// Merchant Id will be received from ICICI
			String merchantId = "123456";
			
			// Headers Preparation
			HttpPost postMethod = new HttpPost(
					"https://apibankingonesandbox.icicibank.com/api/MerchantAPI/UPI/v0/QR3/" + merchantId);
			postMethod.setHeader("accept", "*/*");
			postMethod.setHeader("accept-encoding", "*");
			postMethod.setHeader("accept-language", "en-US");
			postMethod.setHeader("cache-control", "no-cache");
			postMethod.setHeader("Content-Type", "text/plain");

			// Data preparation to Invoke QR Pay API
			QrPay reqDto = new QrPay();
			reqDto.setAmount("5.00");
			reqDto.setBillNumber("BillNumber");
			reqDto.setMerchantId(merchantId);
			reqDto.setMerchantTranId(UUID.randomUUID().toString().substring(0, 35).replaceAll("-", "1"));
			reqDto.setTerminalId("5411");

			// Convert Model class to String
			String reqStr = objMapper.writeValueAsString(reqDto);
			System.out.println("ReqBody:" + reqStr);

			// Encryption of request payload
			byte[] resEncPacketByteArr = IciciCryptoProvider.getCipher64(reqStr, BANK_PUBLIC_KEY);
			String encodedData = new String(Base64.getEncoder().encodeToString(resEncPacketByteArr));

			// Preparing request payload for API
			HttpEntity entity = new StringEntity(encodedData);
			postMethod.setEntity(entity);

			// Invoking the QR Pay API
			HttpResponse response = httpclient.execute(postMethod);

			// Checking the status code of the API
			int statusCode = response.getCode();
			System.out.println("statusCode:" + statusCode);

			// Get response body
			HttpEntity resEntity = ((CloseableHttpResponse) response).getEntity();
			String responseString = EntityUtils.toString(resEntity);
			System.out.println("responseString:" + responseString);

			// Decrypting the response
			byte[] plainRespByteArr = IciciCryptoProvider.getPlainText(responseString, ORGANIZATION_PRIVATE_KEY);
			String plainText = new String(plainRespByteArr);

			System.out.println("plainText:" + plainText);

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
