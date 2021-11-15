/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.validationsummary;

import java.io.IOException;

import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.ValueBinding;

import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorsConstants;

/**
 * @author Cagatay Civici
 */
public class ValidationSummary extends UIOutput {

	public final static String COMPONENT_TYPE = "net.sf.jsfcomp.clientvalidators.validationsummary";
	
	private String style;

	public ValidationSummary() {
		setRendererType(null);
	}
	
	public String getStyle() {
		if (style != null)
			return style;

		ValueBinding vb = getValueBinding("style");
		if (vb != null)
			return (String) vb.getValue(getFacesContext());
		else
			return ClientValidatorsConstants.DEFAULT_VALIDATION_MSG_CLASS;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void encodeEnd(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.write("<FONT class=\""+getStyle()+"\">");
		writer.write("<div id=\"clientValidationSummary\">");
		writer.write("\n");
		writer.write("</div></FONT>");
	}
	
	public Object saveState(FacesContext context) {
		Object values[] = new Object[2];
		values[0] = super.saveState(context);
		values[1] = style;
		return ((Object) (values));
	}

	public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;
		super.restoreState(context, values[0]);
		style = (String) values[1];
	}
}