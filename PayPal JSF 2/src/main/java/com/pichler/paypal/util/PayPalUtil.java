package com.pichler.paypal.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.CallerServices;
import com.paypal.soap.api.GetTransactionDetailsRequestType;
import com.paypal.soap.api.GetTransactionDetailsResponseType;
import com.paypal.soap.api.PaymentInfoType;
import com.paypal.soap.api.PaymentStatusCodeType;

public class PayPalUtil {

	/**
	 * Checa com o paypal se a notificação foi real, e se a origem foi o Paypal.
	 * 
	 * @throws Exception
	 * */
	public static void verificaIPN(FacesContext fc) throws Exception {
		// https://cms.paypal.com/us/cgi-bin/?cmd=_render-content&content_ID=developer/e_howto_admin_IPNIntro

		// HttpServletRequestWrapper req = (HttpServletRequestWrapper)
		// fc.getExternalContext().getRequest();

		ExternalContext externalContext = fc.getExternalContext();
		Iterator<String> requestParameterNames = externalContext
				.getRequestParameterNames();

		// Precisa ser na ordem exata
		String requestUrl = Messages.getString("PayPalUtil.paypalURL"); //$NON-NLS-1$

		while (requestParameterNames.hasNext()) {
			String chave = requestParameterNames.next();
			String valor = externalContext.getRequestParameterMap().get(chave);
			requestUrl += "&" + chave + "=" + URLEncoder.encode(valor, "UTF-8");
		}

		System.out.println("Verificando se é valido: " + requestUrl);

		URL urlConPayPal = new URL(requestUrl);

		URLConnection yc = urlConPayPal.openConnection();
		yc.setDoOutput(true);
		yc.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");

		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			if (inputLine.equals("VERIFIED")) {
				// OK
				// verificar se o vendedor é o correto
				// verificar se o valor é o correto
				// se a vendaID é o correto
				// se tudo for correto atualiza a minha base.
			} else {
				throw new Exception("O paypal não confirma esta transação: "
						+ inputLine);
			}
		}
		in.close();
	}

	public static GetTransactionDetailsResponseType getTransactionDetails(
			String transactionId) throws PayPalException {
		CallerServices caller = new CallerServices();

		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
		/*
		 * WARNING: Do not embed plaintext credentials in your application code.
		 * Doing so is insecure and against best practices. Your API credentials
		 * must be handled securely. Please consider encrypting them for use in
		 * any production environment, and ensure that only authorized
		 * individuals may view or modify them.
		 */

		// Set up your API credentials, PayPal end point, and API version.

		profile.setAPIUsername(Messages.getString("PayPalUtil.paypalAPIUsername")); //$NON-NLS-1$
		profile.setAPIPassword(Messages.getString("PayPalUtil.paypalAPIPassword")); //$NON-NLS-1$
		profile.setSignature(Messages.getString("PayPalUtil.PayPalSignature")); //$NON-NLS-1$
		profile.setEnvironment(Messages.getString("PayPalUtil.PayPalEnvironment")); //$NON-NLS-1$
		caller.setAPIProfile(profile);

		GetTransactionDetailsRequestType pprequest = new GetTransactionDetailsRequestType();
		pprequest.setVersion(Messages.getString("PayPalUtil.PayPalAPIVersion")); //$NON-NLS-1$

		// Add request-specific fields to the request.
		pprequest.setTransactionID(transactionId);

		// Execute the API operation and obtain the response.
		GetTransactionDetailsResponseType ppresponse = (GetTransactionDetailsResponseType) caller
				.call("GetTransactionDetails", pprequest);
		return ppresponse;
	}

	//https://cms.paypal.com/us/cgi-bin/?cmd=_render-content&content_ID=developer/e_howto_api_soap_r_GetTransactionDetails
		
	public static void main(String[] args) throws PayPalException {
		GetTransactionDetailsResponseType transactionDetails = getTransactionDetails("8HM00992D19032406");
		PaymentInfoType paymentInfo = transactionDetails.getPaymentTransactionDetails().getPaymentInfo();
		PaymentStatusCodeType paymentStatus = paymentInfo.getPaymentStatus();
		System.out.println(paymentStatus);
		
//		8HM00992D19032406
//		9421277387929481Y
	}
}
