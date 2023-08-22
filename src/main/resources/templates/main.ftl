<#import "parts/common.ftl" as c />

<@c.page>

<div class="form-row mb-3">
	<form method="get" action="/main" class="form-inline">
		<input type="text" name="filter" placeholder="tag to find" value="${filter?ifExists}"/>
		<button class="btn btn-primary ml-2" type="submit">Search</button>
	</form>
</div>

<#include "parts/message-edit.ftl" />

<#include "parts/message-list.ftl" />

</@c.page>
