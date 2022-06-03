<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox readonly="true" code="inventor.chimpum.form.label.code" path="code"/>
	<jstl:if test="${command != 'create'}">
		<acme:input-textbox code="inventor.chimpum.form.label.creationMoment" path="creationMoment" readonly="true"/>
		<acme:input-textbox code="inventor.chimpum.form.label.startPeriod" path="startPeriod" readonly="true"/>
		<acme:input-textbox code="inventor.chimpum.form.label.finishPeriod" path="finishPeriod" readonly="true"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:input-textbox code="inventor.chimpum.form.label.startPeriod" path="startPeriod" />
		<acme:input-textbox code="inventor.chimpum.form.label.finishPeriod" path="finishPeriod" />
	</jstl:if>
	<acme:input-textbox code="inventor.chimpum.form.label.title" path="title"/>
	<acme:input-textbox code="inventor.chimpum.form.label.description" path="description"/>
	<acme:input-money code="inventor.chimpum.form.label.budget" path="budget"/>
	<acme:input-url code="inventor.chimpum.form.label.link" path="link"/>
	
	<jstl:choose>
	    <jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
	        <acme:submit code="inventor.chimpum.form.button.update" action="/inventor/chimpum/update"/>
	        <acme:submit code="inventor.chimpum.form.button.delete" action="/inventor/chimpum/delete"/>
	    </jstl:when>
	    <jstl:when test="${command == 'create'}">
	    	<acme:hidden-data path="itemId"/>
	        <acme:submit code="inventor.chimpum.form.button.create" action="/inventor/chimpum/create"/>
	    </jstl:when>
	</jstl:choose>
</acme:form>