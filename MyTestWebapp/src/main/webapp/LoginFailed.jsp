<%@ taglib uri="/struts-tags" prefix="s" %>
<h2>Login Failed! Please try again.</h2> 
<s:form action="loginAction">  
<s:textfield name="userName" label="User Name" />
<s:textfield name="password" label="Password" type="password"/>
<s:submit value="Login"></s:submit>  
</s:form>