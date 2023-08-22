<#include "security.ftl">

<div class="card-columns">
	<#list messages as message>
	<div class="card my-3">
		<div>
			<#if message.filename??>
				<img src="/img/${message.filename}" class="card-img-top">
			</#if>
		</div>
		
		<div class="m-2">
			<span>${message.text}</span><br>
			<br>
			<#if message.tag?trim != "">
				<i>#${message.tag}</i>
			</#if>
		</div>
		
		<div class="card-footer text-muted">
			<a href= "/user-messages/${message.author.id}">${message.authorName}</a>
			<#if message.author.id == currentUserId>
			<a class = "btn btn-primary" href = "/user-messages/${message.author.id}?message=${message.id}">
				Edit
			</a>
			</#if>
		</div>	
	</div>
	<#else>
	No messages
	</#list>
</div>