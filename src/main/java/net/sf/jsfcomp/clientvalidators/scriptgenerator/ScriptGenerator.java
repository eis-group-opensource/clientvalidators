/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package net.sf.jsfcomp.clientvalidators.scriptgenerator;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.ValueBinding;

import net.sf.jsfcomp.clientvalidators.ClientValidatorBase;
import net.sf.jsfcomp.clientvalidators.comparevalidator.CompareValidator;
import net.sf.jsfcomp.clientvalidators.customvalidator.CustomValidator;
import net.sf.jsfcomp.clientvalidators.lengthvalidator.LengthValidator;
import net.sf.jsfcomp.clientvalidators.rangevalidator.RangeValidator;
import net.sf.jsfcomp.clientvalidators.regularexpressionvalidator.RegularExpressionValidator;
import net.sf.jsfcomp.clientvalidators.requiredfieldvalidator.RequiredFieldValidator;
import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorUtils;
import net.sf.jsfcomp.clientvalidators.utils.ClientValidatorsConstants;

/**
 * @author Cagatay Civici
 */
public class ScriptGenerator extends UIOutput {
	
	public final static String COMPONENT_TYPE = "net.sf.jsfcomp.clientvalidators.scriptgenerator";

	public ScriptGenerator() {
		setRendererType(null);
	}
	
	private String form;
	
	private Boolean popup;

	public void encodeEnd(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		encodeResources(writer, context.getExternalContext().getRequestContextPath());
		encodeValidateFormFunction(writer);
	}

	private void encodeResources(ResponseWriter writer, String requestContextPath) throws IOException {
		encodeStyleClasses(writer);
		writer.write("<script type=\"text/javascript\" src=\""+requestContextPath+"/js/clientvalidators.js\"></script>\n");
	}
		
	private void encodeStyleClasses(ResponseWriter writer) throws IOException {
			writer.write("<style type=\"text/css\">");
			writer.write("." + ClientValidatorsConstants.STATIC_DIV_CLASS
					+ " { position: relative; visibility: hidden; }");
			writer.write("." + ClientValidatorsConstants.DYNAMIC_DIV_CLASS
					+ " { position: relative; display : none; }");
			writer.write("." + ClientValidatorsConstants.DEFAULT_VALIDATION_MSG_CLASS + "{color: red;}");
			writer.write("</style>\n\n");
	}

	private void encodeValidateFormFunction(ResponseWriter writer) throws IOException {
		writer.write("<script type=\"text/javascript\">\n");
		writer.write("function validate() {\n");
		encodePopupAndSummaryVariables(writer);
		writer.write("var isFormValid=true;\n");
		writer.write("clearValidationSummary(vs);\n");
		UIComponent formToValidate = findComponent(getForm());
		findAndEncodeValidators(formToValidate, writer);
		writer.write("showPopupIfNecessary(vs);\n");
		writer.write("return isFormValid;\n");
		writer.write("}\n\n");
		writer.write("</script>\n");
	}
	
	private void encodePopupAndSummaryVariables(ResponseWriter writer) throws IOException {
		writer.write("var vs = new Object();\n");
		if(getPopup() == true)
			writer.write("vs.popup = true;\n");
		else
			writer.write("vs.popup = false;\n");
		
		writer.write("vs.summary = '';\n");
	}

	private void findAndEncodeValidators(UIComponent parent, ResponseWriter writer) throws IOException {
		for (Iterator iter = parent.getChildren().iterator(); iter.hasNext();) {
			UIComponent childrenComponent = (UIComponent) iter.next();
			if(childrenComponent instanceof ClientValidatorBase)
				encodeValidator((ClientValidatorBase)childrenComponent, writer);
			else if (childrenComponent.getChildCount() > 0)
				findAndEncodeValidators(childrenComponent, writer);
		}
	}
	
	private void encodeValidator(ClientValidatorBase validator, ResponseWriter writer) throws IOException {
		if (validator instanceof RequiredFieldValidator)
			encodeRequiredFieldValidatorCaller((RequiredFieldValidator) validator, writer);
		else if (validator instanceof RangeValidator)
			encodeRangeValidatorCaller((RangeValidator) validator,writer);
		else if (validator instanceof CompareValidator)
			encodeCompareValidatorCaller((CompareValidator) validator, writer);
		else if (validator instanceof RegularExpressionValidator)
			encodeRegularExpressionValidatorCaller((RegularExpressionValidator) validator, writer);
		else if (validator instanceof LengthValidator)
			encodeLengthValidatorCaller((LengthValidator) validator, writer);
		else if (validator instanceof CustomValidator)
			encodeCustomValidatorCaller((CustomValidator) validator, writer);
		
		encodeAfterValidatorMethodCall(writer, validator);
	}
	
	//Encodes function that adds error msg to the validation summary and sets general validity
	private void encodeAfterValidatorMethodCall(ResponseWriter writer, ClientValidatorBase validator) throws IOException{
		writer.write("addErrorToSummaryIfNecessary(retVal,'"+ validator.getClientId(FacesContext.getCurrentInstance()) +"',vs);\n");
		writer.write("isFormValid=isFormValid && retVal;\n");
	}

	private void encodeRequiredFieldValidatorCaller(RequiredFieldValidator validator, ResponseWriter writer) throws IOException {
		if(validator.getComponentToValidate() == null){
			validator.setComponentToValidate(validator.getParent().getId());
		}
		String componentType = "";
		
		UIComponent componentToValidate = ClientValidatorUtils.findComponentInView(validator.getComponentToValidate());
		if(componentToValidate instanceof HtmlSelectOneRadio){
			componentType = "RadioGroup";
		}
		String componentToValidateClientId = componentToValidate.getClientId(getFacesContext());
		MessageFormat mf = new MessageFormat("retVal=validateRequiredField{0}(''{1}'',''{2}'',{3},''{4}'');\n");
		String script = mf.format(new Object[]{
				componentType,
				componentToValidateClientId, validator.getClientId(getFacesContext()), 
				validator.getHighlight(), validator.getDisplay()});
		writer.write(script);
	}

	private void encodeRangeValidatorCaller(RangeValidator validator, ResponseWriter writer) throws IOException {
		UIComponent componentToValidate = ClientValidatorUtils.findComponentInView(validator.getComponentToValidate());
		String componentToValidateClientId = componentToValidate.getClientId(getFacesContext());
		writer.write("retVal=validateRangeField('" + componentToValidateClientId
				+ "','" + validator.getClientId(getFacesContext()) + "',"
				+ validator.getMinValue() + "," + validator.getMaxValue() + "," + validator.getHighlight() + ",'" + validator.getDisplay()
				+ "');\n");
	}

	private void encodeCompareValidatorCaller(CompareValidator validator,ResponseWriter writer) throws IOException {
		UIComponent componentToValidate = ClientValidatorUtils.findComponentInView(validator.getComponentToValidate());
		String componentToValidateClientId = componentToValidate.getClientId(getFacesContext());
		UIComponent componentToCompare = ClientValidatorUtils.findComponentInView(validator.getComponentToCompare());
		String componentToCompareClientId = componentToCompare.getClientId(getFacesContext());

		writer.write("retVal=validateCompareFields('"
				+ componentToValidateClientId + "','" + componentToCompareClientId
				+ "','" + validator.getClientId(getFacesContext()) + "','"
				+ validator.getOperator() + "'," + validator.getHighlight() + ",'" + validator.getDisplay() + "');\n");
	}

	private void encodeRegularExpressionValidatorCaller(RegularExpressionValidator validator, ResponseWriter writer)throws IOException {
		UIComponent componentToValidate = ClientValidatorUtils.findComponentInView(validator.getComponentToValidate());
		String componentToValidateClientId = componentToValidate.getClientId(getFacesContext());

		writer.write("retVal=validateRegExp('" + componentToValidateClientId
				+ "','" + validator.getClientId(getFacesContext()) + "',"
				+ validator.getPattern() + "," + validator.getHighlight() + ",'" + validator.getDisplay() + "');\n");
	}
	
	private void encodeLengthValidatorCaller(LengthValidator validator, ResponseWriter writer)throws IOException {
		UIComponent componentToValidate = ClientValidatorUtils.findComponentInView(validator.getComponentToValidate());
		String componentToValidateClientId = componentToValidate.getClientId(getFacesContext());
		String min = validator.getMin() == null ? null : validator.getMin().toString();
		String max = validator.getMax() == null ? null : validator.getMax().toString();

		if(validator.getExactly() == null) {
		writer.write("retVal=validateLengthInterval('" + componentToValidateClientId
				+ "','" + validator.getClientId(getFacesContext()) + "',"
				+ min + "," + max + "," + validator.getHighlight() + ",'" + validator.getDisplay() + "');\n");
		} else {
			writer.write("retVal=validateLengthExactly('" + componentToValidateClientId
					+ "','" + validator.getClientId(getFacesContext()) + "',"
					+ validator.getExactly().intValue() + "," + validator.getHighlight() + ",'" + validator.getDisplay() + "');\n");
		}
	}
	
	private void encodeCustomValidatorCaller(CustomValidator validator, ResponseWriter writer)throws IOException {
		UIComponent componentToValidate = ClientValidatorUtils.findComponentInView(validator.getComponentToValidate());
		String componentToValidateClientId = componentToValidate.getClientId(getFacesContext());
		writer.write("retVal=" + validator.getFunction() + "(" + validator.getParams() + ");\n");
		writer.write("if(retVal == false)\n showValidationError('"
				+ componentToValidateClientId + "','"
				+ validator.getClientId(getFacesContext()) + "',"
				+ validator.getHighlight() + ",'" + validator.getDisplay()
				+ "');\n");
		writer.write("else \nhideValidationError('"
				+ componentToValidateClientId + "','"
				+ validator.getClientId(getFacesContext()) + "',"
				+ validator.getHighlight() + ",'" + validator.getDisplay()
				+ "');\n");
	}
	
	public String getForm() {
		if(form != null)
			return form;
		
		ValueBinding vb = getValueBinding("form");
		if(vb != null)
			return (String) vb.getValue(getFacesContext());
		else
			return null;
	}

	public void setForm(String form) {
		this.form = form;
	}
	
	public boolean getPopup() {
		if (popup != null)
			return popup.booleanValue();

		ValueBinding vb = getValueBinding("popup");
		Boolean v = vb != null ? (Boolean) vb.getValue(getFacesContext()): null;
		return v != null ? v.booleanValue() : false;
	}

	public void setPopup(boolean popup) {
		this.popup = Boolean.valueOf(popup);
	}
	
	public Object saveState(FacesContext context) {
		Object values[] = new Object[3];
		values[0] = super.saveState(context);
		values[1] = form;
		values[2] = popup;
		return ((Object) (values));
	}

	public void restoreState(FacesContext context, Object state) {
		Object values[] = (Object[]) state;
		super.restoreState(context, values[0]);
		form = (String) values[1];
		popup = (Boolean) values[2];
	}
}