package com.merusphere.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class TransactionStatus {

	@JsonProperty("merchantId")
	private String merchantId;

	@JsonProperty("subMerchantId")
	private String subMerchantId;

	@JsonProperty("terminalId")
	private String terminalId;

	@JsonProperty("merchantTranId")
	private String merchantTranId;

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
	 * @return the subMerchantId
	 */
	public String getSubMerchantId() {
		return subMerchantId;
	}

	/**
	 * @param subMerchantId the subMerchantId to set
	 */
	public void setSubMerchantId(String subMerchantId) {
		this.subMerchantId = subMerchantId;
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

}