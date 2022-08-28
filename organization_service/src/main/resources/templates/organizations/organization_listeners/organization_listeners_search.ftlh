<#import "../../parts/commons/common_organization.ftlh" as c>

<@c.page>
    <h5>Слушатели организации: ${organizationName}</h5>

    <div class="form-row mt-3">
        <form method="get" action="/trudexpert/listener/search" id="searchBySurname" class="form-inline">
            <label>
                <input type="text" name="surname" class="form-control" value="${surname!""}" placeholder="Введите фамилию">
            </label>
            <button type="submit" class="btn btn-primary ml-2">Найти</button>
        </form>
    </div>

    <div>
        <#if listeners??>
            <table class="table table-bordered mt-5">
                <thead>
                <tr>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Отчество</th>
                    <th scope="col">Дата рождения</th>
                    <th scope="col">СНИЛС</th>
                </tr>
                </thead>
                <#list listeners as listener>
                    <tr>
                        <td>${listener.surname}</td>
                        <td>${listener.name}</td>
                        <td>${listener.patronymic}</td>
                        <td>${listener.dateOfBirth}</td>
                        <td>${listener.snils}</td>
                    </tr>
                </#list>
            </table>
        <#else>
            <div><br>Не найдено слушателей от данной организации</div>
        </#if>
    </div>

</@c.page>