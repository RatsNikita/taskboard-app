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

<#macro card note>
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">${note.title}</h5>
            <p class="card-text">${note.body}</p>
            <p class="card-text"><@spring.message 'my-notes.endDate'/> ${note.endDate}</p>
            <a href="/my-notes/resolve?id=${note.id}"><@spring.message 'my-notes.resolve'/> </a>
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