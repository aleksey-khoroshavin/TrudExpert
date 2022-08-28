<#import "../parts/commons/common_listener.ftlh" as c>

<@c.page>
    <h5>Организации слушателя: ${listenerName}</h5>

    <div>
        <#if organizations??>
            <table class="table table-bordered mt-5">
                <thead>
                <tr>
                    <th scope="col">Название организации</th>
                    <th scope="col">Должность</th>
                </tr>
                </thead>
                <#list organizations as organization>
                    <tr>
                        <td>${organization.organizationName}</td>
                        <td>${organization.post}</td>
                    </tr>
                </#list>
            </table>
        <#else>
            <div><br>Слушатель не прикреплен ни к одной организации</div>
        </#if>
    </div>

</@c.page>