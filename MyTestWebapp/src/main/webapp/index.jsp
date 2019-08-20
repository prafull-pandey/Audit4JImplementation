<%@ taglib uri="/struts-tags" prefix="s" %>  
<s:form action="loginAction">  
<s:textfield name="userName" label="User Name" />
<s:textfield name="password" label="Password" type="password"/>
<s:submit value="Login"></s:submit>  
</s:form>