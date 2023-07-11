<#-- @ftlvariable name="password" type="java.lang.String" -->
<#-- @ftlvariable name="login" type="java.lang.String" -->
<#-- @ftlvariable name="name" type="java.lang.String" -->
<#-- @ftlvariable name="error__login" type="java.lang.String" -->
<#-- @ftlvariable name="error__password" type="java.lang.String" -->
<#-- @ftlvariable name="error__name" type="java.lang.String" -->
<#-- @ftlvariable name="err" type="java.lang.Boolean" -->

<#import "common.ftl" as c>

<template>
    <@c.page>
        <div class="form-box _enter-box">
            <div class="header">{{Register}}
                <div class="popup">
                    <span class="popuptext" id="myPopup">Something went wrong, please try again later.</span>
                </div>
            </div>

            <div class="body">
                <form method="post" action="<@link name="RegisterPage"/>">
                    <input type="hidden" name="action" value="register">
                    <div class="field">
                        <div class="name">
                            <label for="login">{{Login}}</label>
                        </div>
                        <div class="value">
                            <input id="login" name="login" value="${login!?html}"/>
                        </div>
                        <@c.errorRow value="${error__login!}"/>
                    </div>
                    <div class="field">
                        <div class="name">
                            <label for="name">{{Name}}</label>
                        </div>
                        <div class="value">
                            <input id="name" name="name" value="${name!?html}"/>
                        </div>
                        <@c.errorRow value="${error__name!}"/>
                    </div>
                    <div class="field">
                        <div class="name">
                            <label for="password">{{Password}}</label>
                        </div>
                        <div class="value">
                            <input id="password" type="password" name="password" value="${password!?html}"/>
                        </div>

                        <@c.errorRow value="${error__password!}"/>

                    </div>


                    <div class="button-field">
                        <input type="submit" value="{{Register}}">
                    </div>


                </form>

            </div>

        </div>

    </@c.page>
</template>

<script>
    $(".button-field").on('click', function () {
        <#if err??>
        var popup = document.getElementById("myPopup");
        popup.classList.toggle("show");
        </#if>
    });
</script>

<style type="text/less">
  ._enter-box {
    margin: 3em auto;
  }

  .popup {
    position: relative;
    display: inline-block;
    cursor: pointer;
  }

  /* Фактическое всплывающее окно (появляется сверху) */
  .popup .popuptext {
    visibility: hidden;
    width: 300px;
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
  }

  .popup .show {
    visibility: visible;

    -webkit-animation: fadeOut 37s;
    animation: fadeOut 37s;
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

</style>
