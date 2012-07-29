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

@ManagedBean(name = "success")
@RequestScoped
public class Success {
	private String output;

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

	/**
	 * @return the output
	 * @throws IOException
	 */
	public String getOutput() throws IOException {
		// verificar se é verdade
		try {
			PayPalUtil.verificaIPN(FacesContext.getCurrentInstance());
			StringBuilder saida = new StringBuilder();

			ExternalContext externalContext = FacesContext.getCurrentInstance()
					.getExternalContext();
			Map<String, String> requestMap = externalContext
					.getRequestParameterMap();
			Set<String> keySet = requestMap.keySet();
			for (String key : keySet) {
				Object object = requestMap.get(key);
				saida.append(key);
				saida.append(": ");
				saida.append(object);
				saida.append("<br/>");
			}
			output = saida.toString();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
		}

		return output;
	}

	/**
	 * @param output
	 *            the output to set
	 */
	public void setOutput(String output) {
		this.output = output;

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
