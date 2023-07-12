<#-- @ftlvariable name="shortMode" type="java.lang.Boolean" -->
<#-- @ftlvariable name="postUser" type="bloggy.model.User" -->
<#-- @ftlvariable name="post" type="bloggy.model.Post" -->
<#import "common.ftl" as c>
<template>
    <article class="_post" data-postId="${post.id}">
        <section class="_title">
            <a href="<@link name="PostViewPage" postId=post.id/>">${post.title?html}</a>
        </section>
        <header>
            {{By}} ${postUser.name}
        </header>
        <div class="_text">
            <div class="_userpicFrameWrapper">
                <@frame name="userpicFrame"/>
            </div>

            <@c.shortText maxLen=1500 short=shortMode entity=post/>
        </div>
    </article>
</template>

<script>
    $("._full").css("cursor", "pointer").click(function () {
        var $post = $(this).closest("article._post");
        $("._preview").hide();
        $("._complete").show();
    });
</script>

<style type="text/less">
  ._post {
    margin-bottom: 5px;
    margin-top: 10px;

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

</style>