<#-- @ftlvariable name="commentProperties" type="java.lang.Long[]" -->

<template>
    <#if commentProperties??>
        <#list commentProperties as commentId>
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
