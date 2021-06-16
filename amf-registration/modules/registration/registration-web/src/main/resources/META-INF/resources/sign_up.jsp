<%@ include file="./init.jsp" %>
<liferay-ui:success key="userAdded" message="user-added-successfully" />

<liferay-ui:error key="serviceErrorDetails">
	<liferay-ui:message arguments='<%= SessionErrors.get(liferayPortletRequest, "serviceErrorDetails") %>' key="error.assignment-service-error" />
</liferay-ui:error>
<liferay-ui:error key="addressEmpty" message="error.address-empty" />
<liferay-ui:error key="addressNonAlphanumeric" message="error.address-non-alphanumeric" />
<liferay-ui:error key="addressLengthError" message="error.address-length" />
<liferay-ui:error key="birthdayEmpty" message="error.birthday-empty" />
<liferay-ui:error key="birthdayInvalid" message="error.birthday-invalid" />
<liferay-ui:error key="birthdayYoung" message="error.birthday-young" />
<liferay-ui:error key="cityEmpty" message="error.city-empty" />
<liferay-ui:error key="cityNonAlphanumeric" message="error.city-non-alphanumeric" />
<liferay-ui:error key="cityLengthError" message="error.city-length" />
<liferay-ui:error key="emailEmpty" message="error.email-empty" />
<liferay-ui:error key="emailTooLong" message="error.email-length" />
<liferay-ui:error key="emailNotValid" message="error.email-invalid" />
<liferay-ui:error key="nameEmpty" message="error.name-empty" />
<liferay-ui:error key="nameTooLong" message="error.name-length" />
<liferay-ui:error key="nameNonAlphanumeric" message="error.name-non-alphanumeric" />
<liferay-ui:error key="passwordEmpty" message="error.password-empty" />
<liferay-ui:error key="passwordNoMatch" message="error.password-match" />
<liferay-ui:error key="passwordInvalid" message="error.password-invalid" />
<liferay-ui:error key="phoneLengthError" message="error.phone-length" />
<liferay-ui:error key="phoneInvalid" message="error.phone-invalid" />
<liferay-ui:error key="secAnswerEmpty" message="error.security-answer-empty" />
<liferay-ui:error key="secAnswerLength" message="error.security-answer-length" />
<liferay-ui:error key="secNonAlphanumeric" message="error.security-non-alphanumeric" />
<liferay-ui:error key="secQuestionEmpty" message="error.security-question-empty" />
<liferay-ui:error key="stateEmpty" message="error.state-empty" />
<liferay-ui:error key="stateIllegal" message="error.state-illegal" />
<liferay-ui:error key="ToUNotAgreed" message="error.tou-agreement" />
<liferay-ui:error key="usernameEmpty" message="error.username-empty" />
<liferay-ui:error key="usernameTooLong" message="error.username-long" />
<liferay-ui:error key="usernameTooShort" message="error.username-short" />
<liferay-ui:error key="usernameNonAlphanumeric" message="error.username-non-alphanumeric" />
<liferay-ui:error key="usernameNotUnique" message="error.username-not-unique" />
<liferay-ui:error key="zipEmpty" message="error.zip-empty" />
<liferay-ui:error key="zipLengthError" message="error.zip-length" />
<liferay-ui:error key="zipNonNumeric" message="error.zip-non-numeric" />


<portlet:actionURL name="addUser" var="addUserURL"></portlet:actionURL>

<c:if test="${themeDisplay.isSignedIn()}">
	<h3>You are already signed in. Welcome back!</h3>
</c:if>

<c:if test = "${!themeDisplay.isSignedIn()}">
	<aui:form action="<%= addUserURL %>" name="<portlet:namespace />fm" >
        <aui:fieldset label="basicInformation">
        	<aui:row>
            	<aui:col width="50">
            		<aui:input name="firstName"></aui:input>
            	</aui:col>
            	<aui:col width="50">
            		<aui:input name="lastName"></aui:input>
            	</aui:col>
            </aui:row>
        	<aui:row>
            	<aui:col width="50">
            		<aui:input name="email"></aui:input>
            	</aui:col>
            	<aui:col width="50">
            		<aui:input name="username"></aui:input>
            	</aui:col>
            </aui:row>
            <aui:row>
            	<aui:col width="50">
            		<aui:select name="male">
            			<option>Male</option>
            			<option>Female</option>
            		</aui:select>
            	</aui:col>
            	<aui:col width="50">
            		<label for="birthday">Birthday</label>
            		<liferay-ui:input-date name="birthday" yearValue="2000" monthValue="0" dayValue="1"></liferay-ui:input-date>
            	</aui:col>
            </aui:row>
        	<aui:row>
            	<aui:col width="50">
            		<aui:input type="password" name="password1"></aui:input>
            	</aui:col>
            	<aui:col width="50">
            		<aui:input type="password" name="password2"></aui:input>
            	</aui:col>
            </aui:row>
        </aui:fieldset> <br />
        <aui:fieldset label="Phone">
        	<aui:row>
            	<aui:col width="50">
            		<aui:input name="homePhone"></aui:input>
            	</aui:col>
            	<aui:col width="50">
            		<aui:input name="mobilePhone"></aui:input>
            	</aui:col>
        	</aui:row>
        </aui:fieldset> <br />
        <aui:fieldset label="billingAddress">
        	<aui:input name="address1"></aui:input>
        	<aui:input name="address2"></aui:input>
        	<aui:row>
        		<aui:col width="33">
        			<aui:input name="city"></aui:input>
        		</aui:col>
        		<aui:col width="33">
        			<aui:input name="state"></aui:input>
        		</aui:col>
        		<aui:col width="33">
        			<aui:input name="zip"></aui:input>
        		</aui:col>
        	</aui:row>
        </aui:fieldset> <br />
        <aui:fieldset label="Misc.">
        	<aui:select name="secQuestion">
        		<option>What is your mother's maiden name?</option>
        		<option>What is the make of your first car?</option>
        		<option>What is your high school mascot?</option>
        		<option>Who is your favorite actor?</option>
        	</aui:select>
        	<aui:input name="secAnswer"></aui:input>
        	<a href="/home" target="_blank" rel="noopener noreferrer"><h2>Terms of Use</h2></a>
        	<aui:input id="termsOfUse" type="checkbox" name="termsOfUse" label="I have read, understand, and agree with the Terms of Use governing my access to and use of the Acme Movie Fanatics web site." value="accepted" />
        </aui:fieldset> <br />

        <aui:button type="submit" value="Sign Up"></aui:button>
	</aui:form>
	</c:if>
