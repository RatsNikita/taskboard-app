<#import "ui.ftl" as ui/>
<#include "navbar.ftl">
<#import "spring.ftl" as spring/>

<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">

  <title><@spring.message 'new-task.page'/></title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>

<div class="container">
  <div class="col">

  </div>
  <div class="d-flex flex-column">
    <form id="f1" name="saveTask" class="d-flex flex-column">
        <@ui.formInput id="f1i1" name="title" localizedLabel="new-task.title"/>
      <label for="f1i2"><@spring.message 'new-task.body'/></label>
      <textarea id="f1i2" name="body" cols="60" rows="5"></textarea> <br/>
      <label for="f1i2"><@spring.message 'new-task.customer'/></label>
      <textarea id="f1i2" name="customer" cols="60" rows="5"></textarea> <br/>
        <@ui.formInput id="f1i3" name="endDate" localizedLabel="new-task.end-date" type="date"/>
      <button formaction="/new-task/save" formmethod="post" type="submit" class="btn btn-primary"><@spring.message 'new-task.save'/></button>
    </form>
  </div>
  <div class="col">

  </div>
</div>
</body>