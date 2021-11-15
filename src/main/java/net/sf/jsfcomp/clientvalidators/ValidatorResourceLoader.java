/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorUtils;
import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorsConstants;

/**
 * @author Cagatay Civici Phaselistener to load resource from jar and handle
 * ajax requests
 */
public class ValidatorResourceLoader implements PhaseListener {

	public void afterPhase(PhaseEvent event) {
		String rootId = event.getFacesContext().getViewRoot().getViewId();

		if (rootId.indexOf(ClientValidatorsConstants.VALIDATOR_RESOURCE_VIEW_ID) != -1) {
			serveResource(event);
		}
	}

	private void serveResource(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		Map requestMap = facesContext.getExternalContext().getRequestParameterMap();
		String resourceName = ClientValidatorUtils.getResourceName(requestMap);
		String resourceType = ClientValidatorUtils.getResourceType(requestMap);
		String contentType = ClientValidatorUtils.getContentType(resourceType);
		
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		try {
			URL url = ValidatorResourceLoader.class.getResource("/META-INF/" + resourceName + "." + resourceType);
			URLConnection connection = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response.setContentType(contentType);
            response.setStatus(200);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(response.getOutputStream(), response.getCharacterEncoding());
			
			//Send the resource line by line
			String line = reader.readLine();
			while(line != null) {
				outputStreamWriter.write(line+"\n");
				line = reader.readLine();
			}
			
            outputStreamWriter.flush();
            outputStreamWriter.close();
            facesContext.responseComplete();		//Stop the jsf lifecycle
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void beforePhase(PhaseEvent arg0) {
		//Do nothing here
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}