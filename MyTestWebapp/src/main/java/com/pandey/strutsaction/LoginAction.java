package com.pandey.strutsaction;

import org.audit4j.core.AuditManager;
import org.audit4j.core.IAuditManager;
import org.audit4j.core.annotation.Audit;
import org.audit4j.core.annotation.AuditField;
import org.audit4j.core.annotation.IgnoreAudit;
import org.audit4j.core.dto.AnnotationAuditEvent;
import org.audit4j.core.dto.AuditEvent;
import org.audit4j.core.dto.EventBuilder;
import org.audit4j.core.exception.HandlerException;
import org.audit4j.handler.db.DatabaseAuditHandler;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1603475580879944823L;

	String userName;
	String password;

	@Override
	public String execute() {


		boolean isLoginSuccess=false;
		isLoginSuccess=userName.equalsIgnoreCase("prafull") && password.equals("12345");
		//auditLogin(isLoginSuccess);
		auditLoginDB(isLoginSuccess);
		return isLoginSuccess?"success":"error";
	}

	private void auditLogin(boolean isLoginSuccess) {
		String actor = userName;
		EventBuilder builder = new EventBuilder();
		builder.addActor(actor).addAction("Login").addField("username", userName).addField("sucessful", isLoginSuccess);
		IAuditManager manager = AuditManager.getInstance();
		manager.audit(builder.build());
	}
	private void auditLoginDB(boolean isLoginSuccess) {
		
		String actor = userName;
		EventBuilder builder = new EventBuilder();
		builder.addActor(actor).addAction("Login").addField("username", userName).addField("sucessful", isLoginSuccess);
		
		
		DatabaseAuditHandler dbHandler = new DatabaseAuditHandler();
		
		dbHandler.setDefault_table_name("audittable");
		dbHandler.setEmbedded("false");
		dbHandler.setDb_connection_type("single");
		dbHandler.setDb_driver("org.postgresql.Driver");
		dbHandler.setDb_user("postgres");
		dbHandler.setDb_password("postgres");
		dbHandler.setDb_url("jdbc:postgresql://localhost:5432/postgres");
		dbHandler.init();
		dbHandler.setAuditEvent(builder.build());
		
		try {
			dbHandler.handle();
		} catch (HandlerException e) {
			e.printStackTrace();
		}
		dbHandler.stop();
		//AuditEvent builder=dbHandler.getAuditEvent();
		//builder.setActor(actor);
		//builder.setAction("Login");
//		builder.setTag("audittable");
		//builder.addField("username", userName);
		//builder.addField("sucessful", isLoginSuccess);
		//IAuditManager manager = AuditManager.getInstance();
		//manager.audit(dbHandler);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	@IgnoreAudit
	public void setPassword(String password) {
		this.password = password;
	}
}
