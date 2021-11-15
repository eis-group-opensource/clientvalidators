/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.requiredfieldvalidator;

import net.sf.jsfcomp.clientvalidators.ClientValidatorTagBase;

public class RequiredFieldValidatorTag extends ClientValidatorTagBase {

	public String getComponentType() {
		return RequiredFieldValidator.COMPONENT_TYPE;
	}

	public String getRendererType() {
		return null;
	}
}