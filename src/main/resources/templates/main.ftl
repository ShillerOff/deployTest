<#import "parts/common.ftl" as c />

<@c.page>

<div class="form-row mb-3">
	<form method="get" action="/main" class="form-inline">
		<input type="text" name="filter" placeholder="tag to find" value="${filter?ifExists}"/>
		<button class="btn btn-primary ml-2" type="submit">Search</button>
	</form>
</div>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
	Add new message
</a>

<div class="collapse <#if message??>show</#if>" id="collapseExample">

	<div class="form-group mt-3">
		<form method="post" enctype="multipart/form-data">		
		<input class="form-control" type="hidden" name="_csrf" value="${_csrf.token}" />
		
			<div class="form-group">
				<input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
				 	name="text" placeholder="Enter your message" />
				<#if textError??>
					<div class = "invalid-feedback">
						${textError}
					</div>
				</#if>
			</div>
			<div class="form-group">
				<input class="form-control ${(tagError??)?string('is-invalid', '')}" type="text" name="tag" placeholder="Tag" />
				<#if tagError??>
					<div class = "invalid-feedback">
						${tagError}
					</div>
				</#if>
			</div>
			
			<div class="custom-file">
				<input type="file" name = "file" id="customFile">
				<label class="custom-file-label" for = "customFile">Choose file</label>
			</div>	
			<div class="form-group pt-3">
				<button type="submit" class="btn btn-primary">Add</button>
			</div>
		</form>
	</div>
</div>


<div class="card-columns">
	<#list messages as message>
	<div class="card my-3">
		<div>
			<#if message.filename??>
				<img src="/img/${message.filename}" class="card-img-top">
			</#if>
		</div>
		
		<div class="m-2">
			<span>${message.text}</span>
			<br>
			<#if message.tag?trim != "">
			<i>#${message.tag}</i>
			</#if>
		</div>
		
		<div class="card-footer text-muted">
			${message.authorName}
		</div>	
	</div>
	<#else>
	No messages
	</#list>
</div>
</@c.page>
