package com.pichler.jsf.beans;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.pichler.paypal.util.PayPalUtil;

// Verify that you are the intended recipient of the IPN message by checking the email address in the message; this handles a situation where another merchant could accidentally or intentionally attempt to use your listener.
// Avoid duplicate IPN messages. Check that you have not already processed the transaction identified by the transaction ID returned in the IPN message. You may need to store transaction IDs and the last payment status returned by IPN messages in a file or database so that you can check for duplicates. If the transaction ID sent by PayPal is a duplicate, you should not process it again.
//NOTE:	You must track the last payment status returned by IPN messages because PayPal could send an IPN for a pending payment and a second one for the completed payment, both of which would have the same transaction ID. Relying on just the transaction ID could lead to the completed payment being treated as a duplicate.
// Because IPN messages can be sent at various stages in a transaction’s progress, make sure that the transaction’s payment status is “completed” before enabling shipment of merchandise or allowing the download of digital media.
// Verify that the payment amount actually matches what you intend to charge. Although not technically an IPN issue, if you do not encrypt buttons, it is possible for someone to capture the original transmission and change the price. Without this check, you could accept a lesser payment than what you expected.

@ManagedBean(name = "ipnListener")
@RequestScoped
public class IPNPayPalListener {
//	private String output;

	/**
	 * @return the output
	 * @throws IOException
	 */
	public String getOutput() {
		// verificar se é verdade
		System.out.println("IPN listener in action...");
		try {
			//TODO LOGS
			//salva o request na base.
			PayPalUtil.verificaIPN(FacesContext.getCurrentInstance());
			System.out.println("it was valid! Order updated!");
		} catch (Throwable e) {
			//LOGS
			System.out.println("it was invalid!");
		}
		return "OK. Thanks Paypal!";
		
	}
}
