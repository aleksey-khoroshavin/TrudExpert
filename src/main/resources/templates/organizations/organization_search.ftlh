<#import "../parts/commons/common_organization.ftlh" as c>

<@c.page>
    <h5>Список организаций</h5>

    <div class="form-group mt-3">
        <form method="get" action="/trudexpert/organization/search" id="searchByName" class="form-inline">
            <input type="text" name="companyName" id="companyNameTag" class="form-control" value="${companyName!""}"
                   placeholder="Введите название организации" size="20">
            <button type="submit" class="btn btn-primary ml-2">Найти</button>
        </form>
    </div>

    <div>
        <#if organizations??>
            <table class="table table-bordered mt-5">
                <thead>
                <tr>
                    <th scope="col">Название организации</th>
                    <th scope="col">Данные организации</th>
                    <th scope="col">Данные контрагента</th>
                </tr>
                </thead>
                <#list organizations as organization>
                    <tr>
                        <td>${organization.name}</td>
                        <td>
                            <a href="/trudexpert/organization/edit?id=${organization.id}">Редактировать</a>
                        </td>
                        <td>
                            <a href="/trudexpert/organization/agent/edit?id=${organization.id}&companyName=${organization.name}">Редактировать</a>
                        </td>
                    </tr>
                </#list>
            </table>
        <#else>
            <div><br>Не найдено организаций в системе</div>
        </#if>
    </div>

    <script>

        const input = document.getElementById('companyNameTag');
        input.setAttribute('size',input.getAttribute('placeholder').length.toString());

    </script>

</@c.page>