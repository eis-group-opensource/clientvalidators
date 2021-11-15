/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.requiredfieldvalidator;

import net.sf.jsfcomp.clientvalidators.ClientValidatorBase;
import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorUtils;
import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorsConstants;

/**
 * @author Cagatay Civici
 */
public class RequiredFieldValidator extends ClientValidatorBase {
	public final static String COMPONENT_TYPE = "net.sf.jsfcomp.clientvalidators.requiredfieldvalidator";

	public RequiredFieldValidator() {
		setRendererType(null);
	}
	
	public String getErrorMessage() {
		String errorMessage = super.getErrorMessage();
		if(errorMessage == null) {
			errorMessage = ClientValidatorUtils.getValidatonErrorMessage(ClientValidatorsConstants.MSG_KEY_REQUIRED);
		}
		return errorMessage;
	}
}