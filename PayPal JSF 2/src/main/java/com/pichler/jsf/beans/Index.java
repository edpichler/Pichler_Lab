package com.pichler.jsf.beans;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.soap.api.GetTransactionDetailsResponseType;
import com.pichler.paypal.util.PayPalUtil;

@ManagedBean(name = "index")
@RequestScoped
public class Index {

	private String transactionCode;

	/**
	 * @return the transactionCode
	 */
	public String getTransactionCode() {
		return transactionCode;
	}

	/**
	 * @param transactionCode
	 *            the transactionCode to set
	 */
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String status() throws PayPalException {
		GetTransactionDetailsResponseType transactionDetails = PayPalUtil
				.getTransactionDetails(this.transactionCode);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(""
						+ transactionDetails.getPaymentTransactionDetails()
								.getPaymentInfo().getPaymentStatus()));
		return null;
	}
}
