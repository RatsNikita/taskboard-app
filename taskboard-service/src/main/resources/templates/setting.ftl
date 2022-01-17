<#include "navbar.ftl">
<#import "spring.ftl" as spring/>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">

    <title><@spring.message 'my-notes.page'/></title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
<div class="container">
<form>
    <p><@spring.message 'setting.mailing'/></p>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="mailing" id="mailing" value="true" >
        <label class="form-check-label" for="mailing">
            ON
        </label>
    </div>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="mailing" id="mailing" value="false">
        <label class="form-check-label" for="mailing">
            OFF
        </label>
    </div>
    <label for="f1i2"><@spring.message 'setting.telegramId'/></label>
    <textarea id="f1i2" name="telegramId" cols="30" rows="1" maxlength="30"></textarea> <br/>
<button formaction="/setting/accept" formmethod="post" type="submit" class="btn btn-primary"><@spring.message 'setting.save'/></button>
</form>
</div>
</body>