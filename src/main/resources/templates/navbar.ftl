<#import "ui.ftl" as ui/>
<#import "spring.ftl" as spring/>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Scope</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Переключатель навигации">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/main"><@spring.message 'navbar.main'/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/setting"><@spring.message 'navbar.settings'/></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Tasks
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/new-task"><@spring.message 'navbar.new-task'/></a>
                        <a class="dropdown-item" href="/my-tasks"><@spring.message 'navbar.my-tasks'/></a>
                        <a class="dropdown-item" href="/task-board"><@spring.message 'navbar.task-board'/></a>
                    </div>
                </li>
            </ul>
            <p class="me-5"><@spring.message 'navbar.user'/> ${currentUser!"N/A"}</p>
            <a class="btn btn-outline-success" href="/logout"><@spring.message 'navbar.logout'/></a>
        </div>
    </div>
</nav>
</body>
