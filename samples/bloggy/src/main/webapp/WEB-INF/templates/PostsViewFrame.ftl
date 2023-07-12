<#-- @ftlvariable name="posts" type="bloggy.model.Post[]" -->
<#-- @ftlvariable name="commentNums" type="java.lang.Long[]" -->

<template>
    <section class="_posts">
        <#if posts??>
            <#assign i=0/>
            <#list posts as post>
                <div class="post_container">
                    <@frame name="post${post.id}"/>
                    <div class="post-footer">
                        <div class="date">
                            <img src="/assets/img/date_32x32.png" alt="date"/>
                            <header>${post.updateTime?date}</header>
                        </div>
                        <div class="comments">
                            <a href="<@link name="PostViewPage" postId=post.id/>#comments">
                                ${commentNums[i]}
                                <img src="/assets/img/comments-36x30.png" alt="comments"/>
                            </a>
                        </div>

                    </div>
                </div>
                <#assign i=i+1/>
            </#list>
        </#if>

    </section>
</template>

<style>
    .post_container {
        margin-bottom: 2em;
    }

    .post-footer {
        width: 99%;
        display: flex;
        align-items: flex-end;
        justify-content: space-between;
        border: 1px solid var(--border-color);
        border-radius: var(--border-radius);
    }

    .date {

        margin-left: 50px;
        display: flex;
        width: 50%;
        justify-content: flex-start;
        align-items: flex-end;
        font-size: large;

    }

    .date header {

        color: gray;
        padding-left: 10px;
        padding-bottom: 5px;
        font-size: larger;

    }

    .date img {
        padding-bottom: 3px;
    }

    .comments {
        width: 50%;
        margin-right: 50px;
        display: flex;
        font-size: x-large;
        justify-content: flex-end;

    }

    .comments a {
        display: flex;
        align-items: flex-start;
        flex-direction: row-reverse;

    }

    .comments img {
        padding-right: 10px;
    }

</style>