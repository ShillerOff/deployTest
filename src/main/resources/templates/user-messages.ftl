<#import "parts/common.ftl" as c />

<@c.page>

<#if isCurrentUser>
	<#include "parts/message-edit.ftl">
</#if>

<#include "parts/message-list.ftl">

</@c.page>