<#macro formInput id name localizedLabel type="text" value="">
    <label for="${id}"><@spring.message '${localizedLabel}'/></label>
    <input type="${type}" id="${id}" name="${name}" value="${value}"> <br/>
</#macro>

<#macro inputButton localizedLabel>
    <input class="btn btn-primary" type="submit" value="${localizedLabel}"/>
</#macro>

<#macro table id rows>
    <table id="${id}" border="1px" cellspacing="2" border="1" cellpadding="5">
        <#list rows as row>
            <tr>
                <td>${row?index + 1}</td>
                <#list row as field>
                    <td>${field}</td>
                </#list>
            </tr>
        </#list>
    </table>
</#macro>

<#macro mycard task>
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">${task.title}</h5>
            <p class="card-text">${task.body}</p>
            <p class="card-text"><@spring.message 'new-task.difficulty'/>${task.difficulty}</p>
            <p class="card-text"><@spring.message 'new-task.priority'/>${task.priority}</p>
            <p class="card-text"><@spring.message 'task-board.status'/>${task.status}</p>
            <p class="card-text"><@spring.message 'my-tasks.endDate'/> ${task.endDate}</p>
            <a href="/my-tasks/delete?id=${task.id}" class="btn btn-primary"><@spring.message 'my-tasks.delete'/> </a>
        </div>
    </div>
</#macro>

<#macro card task>
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">${task.title}</h5>
            <p class="card-text">${task.body}</p>
            <p class="card-text"><@spring.message 'new-task.difficulty'/>${task.difficulty}</p>
            <p class="card-text"><@spring.message 'new-task.priority'/>${task.priority}</p>
            <p class="card-text"><@spring.message 'task-board.customer'/>${task.customer}</p>
            <p class="card-text"><@spring.message 'task-board.status'/>${task.status}</p>
            <p class="card-text"><@spring.message 'my-tasks.endDate'/> ${task.endDate}</p>
            <#if task.status =="ACTIVE">
                <a href="/task-board/start?id=${task.id}" class="btn btn-primary"><@spring.message 'task-board.start'/> </a>
            </#if>
            <#if task.status =="DEACTIVATED"|| task.status == "RESOLVED">
                <p class="card-text"> <@spring.message "task-board.closingDate"/> ${task.updateDate}</p>
            </#if>
        </div>
    </div>
</#macro>

<#macro inprogress task>
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">${task.title}</h5>
            <p class="card-text">${task.body}</p>
            <p class="card-text"><@spring.message 'new-task.difficulty'/>${task.difficulty}</p>
            <p class="card-text"><@spring.message 'new-task.priority'/>${task.priority}</p>
            <p class="card-text"><@spring.message 'task-board.customer'/>${task.customer}</p>
            <p class="card-text"><@spring.message 'my-tasks.endDate' /> ${task.endDate}</p>
            <a href="/in-progress-tasks/resolve?id=${task.id}" class="btn btn-primary"> <@spring.message 'in-progress-tasks.resolve'/>  </a>
        </div>
    </div>
</#macro>

<#macro search task>
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">${task.title}</h5>
            <p class="card-text">${task.body}</p>
            <p class="card-text"><@spring.message 'new-task.difficulty'/>${task.difficulty}</p>
            <p class="card-text"><@spring.message 'new-task.priority'/>${task.priority}</p>
            <p class="card-text"><@spring.message 'task-board.customer'/>${task.customer}</p>
            <p class="card-text"><@spring.message 'task-board.status'/>${task.status}</p>
            <p class="card-text"><@spring.message 'my-tasks.endDate'/> ${task.endDate}</p>
            <#if task.status =="ACTIVE">
                <a href="/task-board/start?id=${task.id}" class="btn btn-primary"><@spring.message 'task-board.start'/> </a>
            </#if>
            <#if task.status =="DEACTIVATED"|| task.status == "RESOLVED">
                <p class="card-text"> <@spring.message "task-board.closingDate"/> ${task.updateDate}</p>
            </#if>
        </div>
    </div>
</#macro>



<#function message code text=''>
    <#if text != ''>
        <#return springMacroRequestContext.getMessage(code, text)>
    <#else>
        <#return springMacroRequestContext.getMessage(code)>
    </#if>
</#function>