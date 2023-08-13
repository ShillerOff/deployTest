<#import "parts/common.ftl" as c/>

<@c.page>
<h5>${username}</h5>
${message?ifExists}
	
<form method="post">
<input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div class="form-group row">
    	<label class ="col-sm-2 col-form-label"> Password: </label>
	    <div class="col-sm-6">
	    	<input type="password" name="password" placeholder="Password" class="form-control"/>
	    </div>
    </div>
    <div class="form-group row">
    	<label class ="col-sm-2 col-form-label"> Email: </label>
	    <div class="col-sm-6">
	    	<input class="form-control" type="email" name="email" placeholder = "New@Email.com"
	    	value="${email!''}"/>
		</div>
	</div>
   <button class = "btn btn-primary" type="submit">
   	Save
   </button>
</form>
</@c.page>


