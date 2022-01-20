<#import "ui.ftl" as ui/>
<#import "spring.ftl" as spring/>

<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">

  <title><@spring.message 'login.page'/></title>
</head>
<body >
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>

<div class="container">
  <div class="row">
    <div class="col-md-3">
        <#if violations??>
            <#list violations as violation>
              <p>${violation}</p>
            </#list>
        <#else></#if>
    </div>
    <div class="col-md-6">
      <div class="row">
        <div class="col">
          <legend><@spring.message 'login.register'/></legend>
          <form id="regForm" name="user">
            <div class="mb-3">
                <@ui.formInput id="a1" name="nickname" localizedLabel="login.nickname"/>
            </div>
            <div class="mb-3">
                <@ui.formInput id="a2" name="email" localizedLabel="login.email"/>
            </div>
            <div class="mb-3">
                <@ui.formInput id="a3" name="password" localizedLabel="login.password" type="password"/>
            </div>
          </form>
        </div>
        <div class="col">
          <legend><@spring.message 'login.login'/></legend>
          <form id="authForm" name="authData">
            <div class="mb-3">
                <@ui.formInput id="a4" name="nickname" localizedLabel="login.nickname"/>
            </div>
            <div class="mb-3">
                <@ui.formInput id="a5" name="password" localizedLabel="login.password" type="password"/>
            </div>
          </form>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="d-grid">
            <button form="regForm" formaction="/login/save" formmethod="post" type="submit" class="btn btn-primary">
                <@spring.message 'login.signup'/>
            </button>
          </div>
            <#if result??>
            <p>${result}</p>
          <#else></#if>
        </div>
        <div class="col">
          <div class="d-grid">
            <button form="authForm" formaction="/login/logon" formmethod="post" type="submit" class="btn btn-primary">
                <@spring.message 'login.logon'/>
            </button>
          </div>
        </div>

      </div>
    </div>
    <div class="col-md-3"></div>
  </div>
</div>
</body>