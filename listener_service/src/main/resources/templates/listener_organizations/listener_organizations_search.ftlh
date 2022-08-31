<#import "../parts/commons/common_listener.ftlh" as c>

<@c.page>
    <h5>Список организаций слушателя: ${listenerName}</h5>

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
            <div><br>Слушатель не прикреплен ни к одной организации.</div>
        </#if>
    </div>

    <div class="form-group mt-5">
        <div class="form-row">
            <button type="button" class="btn btn-primary" id="addOrganization"
                    onclick="window.location.href='/trudexpert/listener/organizations/add?id=${listenerId}&name=${listenerName}'">
                Добавить организацию
            </button>

            <button type="button" class="btn btn-primary ml-5" id="deleteOrganization"
                    onclick="window.location.href='/trudexpert/listener/organizations/delete?id=${listenerId}&name=${listenerName}'">
                Удалить организацию
            </button>
        </div>
    </div>


</@c.page>