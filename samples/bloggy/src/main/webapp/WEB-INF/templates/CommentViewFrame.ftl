<#-- @ftlvariable name="shortMode" type="java.lang.Boolean" -->
<#-- @ftlvariable name="commentUser" type="bloggy.model.User" -->
<#-- @ftlvariable name="comment" type="bloggy.model.Comment" -->
<#import "common.ftl" as c>
<template>
    <article class="_comment" data-commentId="${comment.id}">
        <div class="left">
            <div class="_userpicFrameWrapper">
                <@frame name="userpicFrame"/>
            </div>

        </div>
        <div class="right">
            <header>${comment.getUpdateTime()?datetime}</header>
            <@c.shortText maxLen=500 short=shortMode entity=comment/>
        </div>

    </article>
</template>


<style type="text/less">
  ._comment {
    margin-bottom: @larger-gap-size;

    header {
      font-size: @smaller-font-size;
      color: @muted-color;
    }

    ._title {
      font-size: @title-font-size;

      a {
        color: @caption-color;
        text-decoration: none;
      }
    }

    ._full {
      font-size: @smaller-font-size;
    }

    ._complete {
      display: none;
    }

    ._userpicFrameWrapper {
      float: left;
    }

    ._text {
      font-family: Tahoma, Verdana, Arial, sans-serif;
      line-height: 22px;
    }

    ._text::after {
      clear: both;
      content: "";
      height: 0;
      display: block;
    }
  }

  article._comment {
    display: flex;
    justify-content: center;
  }

  .right {
    width: 90%;
    font-size: 14px;
    margin-top: 10px;
  }

</style>


