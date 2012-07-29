package com.pichler.paypal.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class PayPalUtil {

	/**
	 * Checa com o paypal se a notificação foi real, e se a origem foi o Paypal.
	 * @throws Exception  
	 * */
	public static void verificaIPN(FacesContext fc) throws Exception {
		// https://cms.paypal.com/us/cgi-bin/?cmd=_render-content&content_ID=developer/e_howto_admin_IPNIntro

//		HttpServletRequestWrapper req = (HttpServletRequestWrapper) fc.getExternalContext().getRequest();
		
		ExternalContext externalContext = fc.getExternalContext();
		Iterator<String> requestParameterNames = externalContext.getRequestParameterNames();

		// Precisa ser na ordem exata
		String requestUrl = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_notify-validate";
		
		while(requestParameterNames.hasNext()){
			String chave = requestParameterNames.next();
			String valor = externalContext.getRequestParameterMap().get(chave);
			requestUrl += "&" + chave + "="
					+ URLEncoder.encode(valor, "UTF-8");	
		}
		
		System.out.println("Verificando se é valido: " + requestUrl);

		URL urlConPayPal = new URL(requestUrl);

		URLConnection yc = urlConPayPal.openConnection();
		yc.setDoOutput(true);
	    yc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	   
		
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			if (inputLine.equals("VERIFIED")) {
				// OK
				//verificar se o vendedor é o correto
				//verificar se o valor é o correto
				//se a vendaID é o correto
				//se tudo for correto atualiza a minha base.
			} else {
				throw new Exception("O paypal não confirma esta transação: " + inputLine);
			}
		}
		in.close();
	}
}
