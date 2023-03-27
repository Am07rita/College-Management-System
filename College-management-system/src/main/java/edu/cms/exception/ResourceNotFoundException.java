package edu.cms.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 7769501835212716147L;

String resourceName;
String fieldName;
Object fieldvalue;
public ResourceNotFoundException(String resourceName, String fieldName, Object fieldvalue) {
	super(String.format("%s not found with %s : '%s'",resourceName,fieldName,fieldvalue));
	this.resourceName = resourceName;
	this.fieldName = fieldName;
	this.fieldvalue = fieldvalue;
}
public String getResourceName() {
	return resourceName;
}
public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
}
public String getFieldName() {
	return fieldName;
}
public void setFieldName(String fieldName) {
	this.fieldName = fieldName;
}
public Object getFieldvalue() {
	return fieldvalue;
}
public void setFieldvalue(String fieldvalue) {
	this.fieldvalue = fieldvalue;
}

}
