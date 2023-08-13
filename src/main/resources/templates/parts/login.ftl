<#macro login path isRegisterForm>
<form action="${path}" method="post">
<input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div class="form-group row">
    	<label class ="col-sm-2 col-form-label"> User Name : </label>
    	<div class="col-sm-6">
    		<input type="text" name="username" placeholder="Login" class = "form-control ${(usernameError??)?string('is-invalid', '')}"/>
	    		<#if usernameError??>
					<div class = "invalid-feedback">
						${usernameError}
					</div>
				</#if>
    	</div>
    </div>
    
    <div class="form-group row">
    	<label class ="col-sm-2 col-form-label"> Password: </label>
	    <div class="col-sm-6">
	    	<input type="password" name="password" placeholder="Password" class="form-control ${(passwordError??)?string('is-invalid', '')}"/>
	    		<#if passwordError??>
					<div class = "invalid-feedback">
						${passwordError}
					</div>
				</#if>
	    </div>
    </div>
    <#if isRegisterForm>
        <div class="form-group row">
    	<label class ="col-sm-2 col-form-label"> Retype password: </label>
	    <div class="col-sm-6">
	    	<input type="password" name="password2" placeholder="Retype assword" class="form-control ${(password2Error??)?string('is-invalid', '')}"/>
	    		<#if password2Error??>
					<div class = "invalid-feedback">
						${password2Error}
					</div>	
				</#if>
		</div>
	    </div>
	    
    <div class="form-group row">
    	<label class ="col-sm-2 col-form-label"> Email: </label>
	    <div class="col-sm-6">
	    	<input type="email" name="email" placeholder = "Your@Email.com" class="form-control ${(emailError??)?string('is-invalid', '')}"/>
				<#if emailError??>
					<div class = "invalid-feedback">
						${emailError}
					</div>
				</#if>
		</div>
	</div>
	<div class="col-sm-6">
		<div class="g-recaptcha" data-sitekey="6LdVfoUnAAAAAKNpyDev9j1GxLTc7QIVJT0IDmnn"></div>
		<#if captchaError??>
			<div class="alert alert-danger" role="alert">
				${captchaError}
			</div>
		</#if>
	</div>
	</#if>

    <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
    <button class = "btn btn-primary" type="submit">
    	<#if isRegisterForm>Create
    	<#else>Sign in
    	</#if>
    </button>
</form>
</#macro>

<#macro logout>
	<form action="/logout" method="post">
		<input type="hidden" name="_csrf" value="${_csrf.token}" /> 
		<button class = "btn btn-primary" type="submit" value="Sign out" >Log out</button> 
	</form>
</#macro>