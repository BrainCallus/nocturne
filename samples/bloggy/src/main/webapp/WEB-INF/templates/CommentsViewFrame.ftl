<#-- @ftlvariable name="commentIds" type="java.lang.Long[]" -->

<template>
    <#if commentIds??>
        <#list commentIds as commentId>
            <div class="_single_comment">
                <@frame name="comment${commentId}"/>
            </div>
        </#list>
    </#if>

</template>

<style>
    ._single_comment {
        display: flow-root;
    }
</style>
