<#import "../parts/commons/common_listener.ftlh" as c>

<@c.page>
    <h5>Поиск слушателей</h5>
    <div>
        Для поиска слушателей необходимо заполнить поле <i>"Фамилия"</i>.<br>
        Для нахождения конкретного лица будут дополнительно отображены <i>"Имя"</i>, <i>"Отчество"(при наличии)</i> и <i>"Дата рождения".</i><br>
    </div>

    <div class="form-row mt-3">
        <form method="get" action="/trudexpert/listener/search" id="searchBySurname" class="form-inline">
            <label>
                <input type="text" name="surname" class="form-control" value="${surname!""}" placeholder="Введите фамилию">
            </label>
            <button type="submit" class="btn btn-primary ml-2">Найти</button>
        </form>
    </div>

    <div>
        <#list listeners as listener>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Фамилия</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Отчество</th>
                    <th scope="col">Дата рождения</th>
                    <th scope="col">Действие</th>
                </tr>
                </thead>
                <tr>
                    <td>${listener.surname}</td>
                    <td>${listener.name}</td>
                    <td>${listener.patronymic}</td>
                    <td>${listener.dateOfBirth}</td>
                    <td>

                    </td>
                </tr>
            </table>
        <#else>
            Не найдено слушателей в системе
        </#list>
    </div>

</@c.page>