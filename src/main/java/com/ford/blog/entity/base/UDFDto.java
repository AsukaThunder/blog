package com.ford.blog.entity.base;

/**
 * @author Ford
 */
public class UDFDto {
	
	private String elementId;
	
	private String value;

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public UDFDto(String elementId, String value) {
		super();
		this.elementId = elementId;
		this.value = value;
	}

	public UDFDto() {
		super();
	}
	
	
	
	

}
