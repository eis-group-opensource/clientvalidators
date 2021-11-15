/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators;

import java.io.IOException;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.ValueBinding;

import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorsConstants;

/**
 * @author Cagatay Civici
 */
public abstract class ClientValidatorBase extends UIComponentBase {

	private String componentToValidate;

	private String errorMessage;

	private Boolean highlight;

	private String display;
	
	private String style;

	protected boolean isDynamicDisplay() {
		String display = getDisplay();

		if (display == null)
			return false;
		else if (display
				.equalsIgnoreCase(ClientValidatorsConstants.DISPLAY_DYNAMIC))
			return true;
		else if (display
				.equalsIgnoreCase(ClientValidatorsConstants.DISPLAY_STATIC))
			return false;
		else if (display
				.equalsIgnoreCase(ClientValidatorsConstants.DISPLAY_NONE))
			return true;
		else
			return false;
	}

	protected String getDivClass() {
		if (isDynamicDisplay())
			return ClientValidatorsConstants.DYNAMIC_DIV_CLASS;
		else
			return ClientValidatorsConstants.STATIC_DIV_CLASS;
	}

	public String getComponentToValidate() {
		if (componentToValidate != null)
			return componentToValidate;

		ValueBinding vb = getValueBinding("componentToValidate");
		if (vb != null)
			return (String) vb.getValue(getFacesContext());
		else
			return null;
	}

	public void setComponentToValidate(String componentToValidate) {
		this.componentToValidate = componentToValidate;
	}
	
	public String getErrorMessage() {
		if (errorMessage != null)
			return errorMessage;

		ValueBinding vb = getValueBinding("errorMessage");
		if (vb != null)
			return (String) vb.getValue(getFacesContext());
		else
			return null;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDisplay() {
		if (display != null)
			return display;

		ValueBinding vb = getValueBinding("display");
		if (vb != null)
			return (String) vb.getValue(getFacesContext());
		else
			return null;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public boolean getHighlight() {
		if (highlight != null)
			return highlight.booleanValue();

		ValueBinding vb = getValueBinding("highlight");
		Boolean v = vb != null ? (Boolean) vb.getValue(getFacesContext()): null;
		return v != null ? v.booleanValue() : false;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = Boolean.valueOf(highlight);
	}
	
	public String getStyle() {
		if (style != null)
			return style;

		ValueBinding vb = getValueBinding("style");
		if (vb != null)
			return (String) vb.getValue(getFacesContext());

		return null;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	public void encodeEnd(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String id = getClientId(getFacesContext());
		String divClass = getDivClass();
		StringBuffer sb = new StringBuffer();
		sb.append("<div ");
		if(getStyle()!=null) sb.append("style=\"").append(getStyle()).append("\"");
		sb.append("class=\"").append(ClientValidatorsConstants.DEFAULT_VALIDATION_MSG_CLASS).append("\"");
		sb.append(">");
		sb.append("<div id=\"").append(id).append("\" class=\"").append(divClass).append("\">");
		writer.write(sb.toString());
		writer.write(getErrorMessage());
		writer.write("</div></div>\n");
	}

	public Object saveState(FacesContext context) {
		Object values[] = new Object[6];
		values[0] = super.saveState(context);
		values[1] = componentToValidate;
		values[2] = errorMessage;
		values[3] = highlight;
		values[4] = display;
		values[5] = style;
		return ((Object) (values));
	}

	public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;
		super.restoreState(context, values[0]);
		componentToValidate = (String) values[1];
		errorMessage = (String) values[2];
		highlight = (Boolean) values[3];
		display = (String) values[4];
		style = (String) values[5];
	}
	
	public String getFamily() {
		return ClientValidatorsConstants.FAMILY;
	}

}
