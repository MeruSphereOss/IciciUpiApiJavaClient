package com.merusphere.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrCodeGenerator {

	public static void main(String[] args) throws WriterException, IOException, NotFoundException {
		// The path where the QR image will get saved
		String path = "/Users/noosa.gontu/sts-workspace/icici-merusphere/src/main/java/com/merusphere/uat/upi/upi.jpg";

		// Encoding charset
		String charset = "UTF-8";

		// Set Error Correction Level
		Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
		hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		String merchantVpa = "Merusphereuat@icici";
		String merchantAlias = "Merusphere";
		String refId = "EZV2025123016233000174386";
		String amount = "5.00";
		String currency = "INR";
		String terminalId = "5411";

		// QR Code URL preparation
		String data1 = "upi://pay?pa=" + merchantVpa + "&pn=" + merchantAlias + "&tr=" + refId + "&am=" + amount
				+ "&cu=" + currency + "&mc=" + terminalId;

		// Create the QR code and save
		// in the specified folder
		// as a jpg file
		QrCodeGenerator.createQR(data1, path, charset, 400, 400);
		System.out.println("QR Code Generated!!! ");
	}

	// Function to create the QR code
	@SuppressWarnings("deprecation")
	public static void createQR(String data, String path, String charset, int height, int width)
			throws WriterException, IOException {

		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, width, height);

		MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
	}
}