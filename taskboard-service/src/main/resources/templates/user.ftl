<#import "ui.ftl" as ui/>
<#include "navbar.ftl">
<#import "spring.ftl" as spring/>

<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">

  <title><@spring.message 'user.page'/></title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>

<div class="container">
  <div class="row">
    <div class="col">
      <fieldset>
        <legend><@spring.message 'user.add-user'/></legend>
        <form name="user" action="/user/save" method="POST">
            <@ui.formInput id="t1" name="nickname" localizedLabel="user.nickname"/>
            <@ui.formInput id="t2" name="email" localizedLabel="user.email"/>
            <@ui.formInput id="t3" name="password" localizedLabel="user.password" type="password"/>
            <@ui.inputButton "${ui.message('user.save')}"/>
        </form>
      </fieldset>
    </div>
    <div class="col">
        <#if violations??>
            <#list violations as violation>
              <p>${violation}</p>
            </#list>
        <#else></#if>
    </div>
  </div>
    <#if users??>
      <div>
        <table class="table">
          <thead>
          <tr>
            <th scope="col">id</th>
            <th scope="col">nickname</th>
            <th scope="col">password</th>
            <th scope="col">email</th>
          </tr>
          </thead>
          <tbody>
          <#list users as user>
            <tr>
              <td>${user.id}</td>
              <td>${user.nickname}</td>
              <td>${user.password}</td>
              <td>${user.email}</td>
            </tr>
          </#list>
          </tbody>
        </table>
      </div>
    <#else></#if>
</div>
</body>
