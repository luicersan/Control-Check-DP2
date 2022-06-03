<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox readonly="true" code="inventor.duboa.form.label.code" path="code"/>
	<jstl:if test="${command != 'create'}">
		<acme:input-textbox code="inventor.duboa.form.label.creationMoment" path="creationMoment" readonly="true"/>
		<acme:input-textbox code="inventor.duboa.form.label.startPeriod" path="startPeriod" readonly="true"/>
		<acme:input-textbox code="inventor.duboa.form.label.finishPeriod" path="finishPeriod" readonly="true"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:input-textbox code="inventor.duboa.form.label.startPeriod" path="startPeriod" />
		<acme:input-textbox code="inventor.duboa.form.label.finishPeriod" path="finishPeriod" />
	</jstl:if>
	<acme:input-textbox code="inventor.duboa.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.duboa.form.label.summary" path="summary"/>
	<acme:input-money code="inventor.duboa.form.label.allotment" path="allotment"/>
	<acme:input-url code="inventor.duboa.form.label.additionalInfo" path="additionalInfo"/>
	
	<jstl:choose>
	    <jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
	        <acme:submit code="inventor.duboa.form.button.update" action="/inventor/duboa/update"/>
	        <acme:submit code="inventor.duboa.form.button.delete" action="/inventor/duboa/delete"/>
	    </jstl:when>
	    <jstl:when test="${command == 'create'}">
	    	<acme:hidden-data path="itemId"/>
	        <acme:submit code="inventor.duboa.form.button.create" action="/inventor/duboa/create"/>
	    </jstl:when>
	</jstl:choose>
</acme:form>