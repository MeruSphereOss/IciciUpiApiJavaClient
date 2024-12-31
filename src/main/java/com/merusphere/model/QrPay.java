package com.merusphere.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class QrPay {
	
	@JsonProperty("amount")
	private String amount;

	@JsonProperty("merchantId")
	private String merchantId;

	@JsonProperty("terminalId")
	private String terminalId;

	@JsonProperty("merchantTranId")
	private String merchantTranId;

	@JsonProperty("billNumber")
	private String billNumber;

	@JsonProperty("validatePayerAccFlag")
	private String validatePayerAccFlag;

	@JsonProperty("payerAccount")
	private String payerAccount;

	@JsonProperty("payerIFSC")
	private String payerIFSC;

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the merchantId
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * @param merchantId the merchantId to set
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * @return the terminalId
	 */
	public String getTerminalId() {
		return terminalId;
	}

	/**
	 * @param terminalId the terminalId to set
	 */
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	/**
	 * @return the merchantTranId
	 */
	public String getMerchantTranId() {
		return merchantTranId;
	}

	/**
	 * @param merchantTranId the merchantTranId to set
	 */
	public void setMerchantTranId(String merchantTranId) {
		this.merchantTranId = merchantTranId;
	}

	/**
	 * @return the billNumber
	 */
	public String getBillNumber() {
		return billNumber;
	}

	/**
	 * @param billNumber the billNumber to set
	 */
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	/**
	 * @return the validatePayerAccFlag
	 */
	public String getValidatePayerAccFlag() {
		return validatePayerAccFlag;
	}

	/**
	 * @param validatePayerAccFlag the validatePayerAccFlag to set
	 */
	public void setValidatePayerAccFlag(String validatePayerAccFlag) {
		this.validatePayerAccFlag = validatePayerAccFlag;
	}

	/**
	 * @return the payerAccount
	 */
	public String getPayerAccount() {
		return payerAccount;
	}

	/**
	 * @param payerAccount the payerAccount to set
	 */
	public void setPayerAccount(String payerAccount) {
		this.payerAccount = payerAccount;
	}

	/**
	 * @return the payerIFSC
	 */
	public String getPayerIFSC() {
		return payerIFSC;
	}

	/**
	 * @param payerIFSC the payerIFSC to set
	 */
	public void setPayerIFSC(String payerIFSC) {
		this.payerIFSC = payerIFSC;
	}

}