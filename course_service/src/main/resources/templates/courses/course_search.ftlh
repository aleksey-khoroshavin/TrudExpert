<#import "../parts/commons/common_course.ftlh" as c>

<@c.page>
    <h5>Список курсов</h5>

    <div class="form-group mt-3">
        <form method="get" action="/trudexpert/organization/search" id="searchByName" class="form-inline">
            <input type="text" name="companyName" id="courseDesc" class="form-control" value="${desc!""}"
                   placeholder="Введите название курса или его описание" size="20">
            <button type="submit" class="btn btn-primary ml-2">Найти</button>
        </form>
    </div>

    <div>
        <#if courses??>
            <table class="table table-bordered mt-5">
                <thead>
                <tr>
                    <th scope="col">Описание курса</th>
                    <th scope="col">Срок обучения (дн.)</th>
                    <th scope="col">Цена курса (руб.\чел.)</th>
                </tr>
                </thead>
                <#list courses as course>
                    <tr>
                        <td>${course.description}</td>
                        <td>${course.studyingTime}</td>
                        <td>${course.cost}</td>
                    </tr>
                </#list>
            </table>
        <#else>
            <div><br>Не найдено курсов в системе</div>
        </#if>
    </div>

    <script>
        const input = document.getElementById('courseDesc');
        input.setAttribute('size',input.getAttribute('placeholder').length.toString());
    </script>

</@c.page>