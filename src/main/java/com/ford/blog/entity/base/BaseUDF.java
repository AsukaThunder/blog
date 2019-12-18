package com.ford.blog.entity.base;

import javax.persistence.Transient;
import java.util.List;

/**
 * @author Ford
 */
public abstract class BaseUDF {

	@Transient
	private List<UDFDto> udfColumn;

	
	public List<UDFDto> getUdfColumn() {
		return udfColumn;
	}

	public void setUdfColumn(List<UDFDto> udfColumn) {
		this.udfColumn = udfColumn;
	}
	
}
