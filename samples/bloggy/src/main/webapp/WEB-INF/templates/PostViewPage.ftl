<#-- @ftlvariable name="commentsAmount" type="java.lang.Long" -->
<#-- @ftlvariable name="showForm" type="java.lang.Boolean" -->
<#-- @ftlvariable name="text" type="java.lang.String" -->
<#-- @ftlvariable name="error__text" type="java.lang.String" -->
<#-- @ftlvariable name="post" type="bloggy.model.Post" -->

<#import "common.ftl" as c>

<template>
    <@c.page>
        <@frame name="postViewFrame"/>
        <div class="comments">
            <#if showForm??>

                <div class="head">
                    <div class="_title">
                        <img src="/assets/img/comments-36x36.png" alt="comments"/>
                        {{Comments}}(${commentsAmount})
                    </div>
                </div>
                <@commentForm/>

            <#else>
                <div class="head">
                    <div class="_title">
                        <img src="/assets/img/comments-36x36.png" alt="comments"/>
                        {{Comments}}(${commentsAmount})
                    </div>

                    <div class="_preview" style="font-size: large;">
                        <div class="popup">
                            <a href='#comments' class="_full">[{{Leave a comment}}]</a>
                            <span class="popuptext" id="myPopup">{{<a href="<@link name="EnterPage"/>">Enter</a> or <a
                                        href="<@link name="RegisterPage"/>">register</a> to leave comments}}</span>
                        </div>
                    </div>
                </div>

                <div class="_complete" style="display: none;">
                    <#if user??>
                        <@commentForm/>

                    </#if>
                </div>


            </#if>
        </div>
        <@frame name="commentsViewFrame"/>
    </@c.page>
</template>

<script>
    $("._full").on('click', function () {
        <#if user??>
        $("._preview").hide();
        $("._complete").show();

        <#else>
        var popup = document.getElementById("myPopup");
        popup.classList.toggle("show");
        </#if>
    });
</script>

<style type="text/less">
  ._title {
    font-size: @title-font-size*1.25;
    color: @caption-color;

    a {
      color: @caption-color;
      text-decoration: none;
    }
  }

  .middle {
    display: initial;
  }

  .comments {
    margin-top: 3em;
  }

  .head {
    display: flex;
    justify-content: space-between;
    width: 80%;
    align-items: baseline;
    margin-bottom: 25px;
  }

  .subscription-row.error {
    margin-left: 0.75em;
    margin-top: 0.25rem;
    padding-left: 1px;
    font-size: 16px;
  }

  .popup {
    position: relative;
    display: inline-block;
    cursor: pointer;
  }

  /* Фактическое всплывающее окно (появляется сверху) */
  .popup .popuptext {
    visibility: hidden;
    width: 360px;
    background-color: #fc9a9ae0;
    color: #fff;
    text-align: center;
    border-radius: 6px;
    padding: 8px 0;
    position: absolute;
    z-index: 1;
    top: 120%;
    left: 50%;
    margin-left: -150px;

    a {
      color: #b1f2ff;
    }
  }

  .popup .show {
    visibility: visible;

    -webkit-animation: fadeOut 7s;
    animation: fadeOut 7s;
  }

  @-webkit-keyframes fadeOut {
    from {
      opacity: 1;
    }
    to {
      opacity: 0;
    }
  }

  @keyframes fadeOut {
    from {
      opacity: 1;
    }
    to {
      opacity: 0;
    }
  }

  form.comment_box {
    width: 100%;

    .comment_entry {
      margin: 5px 0;
      border: 0 solid #f5f5f5;

      .value {
        border: 1px solid #b9b9b9;
        padding: 5px 5px 2px;
        font: 14px Verdana, Arial, Helvetica, sans-serif;
      }

      .button-field {
        margin-top: 0.5em;
        margin-bottom: 0.25em;
        width: 99%;
        display: flex;

        flex-direction: row-reverse;
        font-size: 22px;
      }

      input {
        background-color: #b4efef;
        border-color: #709f9f;
        border-radius: 5px;
      }
    }

    textarea {
      width: 100%;
      height: 200px;
      resize: vertical;
    }

  }

</style>

<#macro commentForm>
    <form class="comment_box" method="post" action="<@link name="PostViewPage" postId=post.id/>">
        <input type="hidden" name="action" value="addComment">
        <div class="comment_entry">
            <div class="value">
                <div class="name">
                    <label for="text"></label>
                </div>
                <div class="value" style="border: none">
                    <textarea id="text" name="text" value="${text!?html}"></textarea>
                </div>

                <@c.errorRow value="${error__text!}"></@c.errorRow>

                <div class="container_footer">

                </div>
                <div class="button-field">
                    <input type="submit" value="{{Add}}">
                </div>
            </div>

        </div>

    </form>

</#macro>