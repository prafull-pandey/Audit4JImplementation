package com.pandey.audit;

import org.audit4j.core.MetaData;

public class AuditMetaData implements MetaData {
	@Override
	public String getOrigin() {
		return "ABC";
    }

	@Override
	public String getActor() {
		return "ABC";
	}
	
}
