//
// important: if you change this file, make sure to copy it into base/ipb-war-commons/webapp/js/ folder !
//
function showValidationDiv(obj,display) {
	if(display == 'static')
		document.getElementById(obj).style.visibility='visible';
	else if(display == 'dynamic')	
		document.getElementById(obj).style.display='inline';
	else if(display == 'none')
		document.getElementById(obj).style.display='none';
}

function hideValidationDiv(obj,display) {
	if(display == 'static')
		document.getElementById(obj).style.visibility='hidden';
	else if(display == 'dynamic')	
		document.getElementById(obj).style.display='none';
	else if(display == 'none')
		document.getElementById(obj).style.display='none';
}

function highlightField(obj) {
	if(document.getElementById(obj) != null)
		document.getElementById(obj).style.backgroundColor="#FF0000";
}

function resetFieldColor(obj) {
	if(document.getElementById(obj) != null)
		document.getElementById(obj).style.backgroundColor="";
}

function showValidationError(field, error, highlight, display) {
	showValidationDiv(error,display);
	if(highlight==true)
		highlightField(field);
}

function hideValidationError(field, error, highlight, display) {
	hideValidationDiv(error,display);
	if(highlight==true)
		resetFieldColor(field);
}

function showCompareValidationError(field1, field2,error, highlight, display) {
	showValidationDiv(error,display);
	if(highlight==true) {
		highlightField(field1);
		highlightField(field2);
	}
}

function hideCompareValidationError(field1, field2, error, highlight, display) {
	hideValidationDiv(error,display);
	if(highlight==true) {
		resetFieldColor(field1);
		resetFieldColor(field2);
	}
}

function validateRequiredField(field,error,highlight,display) {
	var value=document.getElementById(field).value;
	if(value == null || value == '') {
		showValidationError(field, error, highlight, display);
		return false;
	}else {
		hideValidationError(field, error, highlight, display);
		return true;
	}
}

function validateRequiredFieldRadioGroup(field,error,highlight,display){
	var value = getHtmlSelectOneRadioFieldValue(field);
	if(value == null || value == '') {
		showValidationError(field, error, highlight, display);
		return false;
	}else {
		hideValidationError(field, error, highlight, display);
		return true;
	}
}

function getHtmlSelectOneRadioFieldValue(controlId) {
	var comp = document.getElementById(controlId);
	if(comp == null) return null;
	var elems = comp.getElementsByTagName('input');
	for (var i = 0; i < elems.length; i++) {
		var elem = elems[i];
		if (elem == 'length') {
		} else {
			if ((elem != null) && (elem.checked)) {
				return elem.value;
			}
		}
	}
	return null;
}


function validateRangeField(field,error,minValue,maxValue,highlight,display) {
	var valueText = document.getElementById(field).value;
	if(valueText == null || valueText == '') {
		showValidationError(field, error, highlight, display);
		return false;
	}
	var value = parseInt(valueText);
	if(minValue != null && maxValue !=null ) {
		if(value > maxValue || value < minValue ) {
			showValidationError(field, error, highlight, display);
			return false;
		}
		else {
			hideValidationError(field, error, highlight, display);
			return true;
		}	
	} else if(minValue == null && maxValue !=null) {
		if(value > maxValue) {
			showValidationError(field, error, highlight, display);
			return false;
		}
		else {
			hideValidationError(field, error, highlight, display);
			return true;
		}
	} else if(minValue != null && maxValue ==null) {
		if(value <minValue) {
			showValidationError(field, error, highlight, display);
			return false;
		}
		else {
			hideValidationError(field, error, highlight, display);
			return true;
		}
	}
}

function validateCompareFields(field1,field2,error,operator,highlight,display) {
	if(operator == 'eq')
		return validateEquality(field1,field2,error,highlight,display);
	else if(operator == 'not')
		return validateNotEquals(field1,field2,error,highlight,display);
}

function validateEquality(field1,field2,error,highlight,display) {
	var value1=document.getElementById(field1).value;
	var value2=document.getElementById(field2).value;
	
	if(value1 == value2) {
		hideCompareValidationError(field1, field2, error, highlight, display);
		return true;
	}
	else {
		showCompareValidationError(field1, field2, error, highlight, display);
		return false;
	}
}


function validateNotEquals(field1,field2,error,highlight,display) {
	var value1=document.getElementById(field1).value;
	var value2=document.getElementById(field2).value;
	
	if(value1 != value2) {
		hideCompareValidationError(field1, field2, error, highlight, display);
		return true;
	}
	else {
		showCompareValidationError(field1, field2, error, highlight, display);
		return false;
	}
}

function validateRegExp(field,error,pattern,highlight,display) {
	var value=document.getElementById(field).value;
	if(value == null || value == '') {
		hideValidationError(field, error, highlight, display);
		return true;
	}
	
	if(value.match(pattern) == null) {
		showValidationError(field, error, highlight, display);
		return false;
	}
	else {
		hideValidationError(field, error, highlight, display);
		return true;
	}
}

function validateLengthExactly(field, error, exactValue, highlight, display) {
	var value = document.getElementById(field).value;
	if(value.length != exactValue) {
		showValidationError(field, error, highlight, display);
		return false;
	} else {
		hideValidationError(field, error, highlight, display);
		return true;
	}
}

function validateLengthInterval(field, error, min, max, highlight, display) {
	var value = document.getElementById(field).value;
	var len = value.length;
	if(min != null && max !=null ) {
		if(len > max || len < min ) {
			showValidationError(field, error, highlight, display);
			return false;
		}
		else {
			hideValidationError(field, error, highlight, display);
			return true;
		}	
	} else if(min == null && max !=null) {
		if(len > max) {
			showValidationError(field, error, highlight, display);
			return false;
		}
		else {
			hideValidationError(field, error, highlight, display);
			return true;
		}
	} else if(min != null && max ==null) {
		if(len < min) {
			showValidationError(field, error, highlight, display);
			return false;
		}
		else {
			hideValidationError(error,display);
			if(highlight==true)
				resetFieldColor(field);
			return true;
		}
	}
}

function keyPressNumber(decimal) {
  var kc = window.event.keyCode;
  var t = window.event.srcElement.value ;

      if (decimal) {
            if (t.indexOf(',') != -1) {
                  if( (kc >= 48 && kc <= 57) == false) {
                        window.event.keyCode = 0;
                  }
            }
            else {
                  if ((kc >= 48 && kc <= 57 || kc == 44) == false) {
                        window.event.keyCode = 0;
                  }
            }
      }
      else {
            if ((kc >= 48 && kc <= 57) == false) {
                  window.event.keyCode = 0;
            }
      }
}

function addErrorToSummaryIfNecessary(isValid, error,vs) {
	if(isValid == true)
		return;
	
	var errorMsg  = document.getElementById(error).innerHTML;
	if(vs.popup == false) {
		if(document.getElementById('clientValidationSummary') != null) {
			document.getElementById('clientValidationSummary').innerHTML +=  errorMsg + '<br>';
		}
	} else {
		vs.summary += '* ' + errorMsg + '\n';
	}
}

function clearValidationSummary(vs) {
	if(vs.popup == false) {
		if(document.getElementById('clientValidationSummary') != null) {
			document.getElementById('clientValidationSummary').innerHTML="";
		}	
	} else {
		vs.summary = '';
	}
}

function showPopupIfNecessary(vs) {
	if(vs.popup == true && vs.summary != '')
		window.alert(vs.summary);
}
