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
        <table class="table table-bordered mt-5">
            <thead>
            <tr>
                <th scope="col">Фамилия</th>
                <th scope="col">Имя</th>
                <th scope="col">Отчество</th>
                <th scope="col">Дата рождения</th>
                <th scope="col">Действие</th>
            </tr>
            </thead>
            <#list listeners as listener>
                <tr>
                    <td>${listener.surname}</td>
                    <td>${listener.name}</td>
                    <td>${listener.patronymic}</td>
                    <td>${listener.dateOfBirth}</td>
                    <td>
                        <a href="/trudexpert/listener/edit?id=${listener.id}">Редактировать</a>
                    </td>
                </tr>
            <#else>
                <td>Не найдено</td>
                <td>Не найдено</td>
                <td>Не найдено</td>
                <td>Не найдено</td>
            </#list>
        </table>
    </div>

</@c.page>