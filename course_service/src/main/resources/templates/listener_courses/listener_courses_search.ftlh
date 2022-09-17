<#import "../parts/commons/common_course.ftlh" as c>

<@c.page>
    <h5>Список курсов слушателя: ${listenerName}</h5>

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
            <div><br>Слушателю не назначен ни один курс!</div>
        </#if>
    </div>

    <div class="form-group mt-5">
        <div class="form-row">
            <button type="button" class="btn btn-primary" id="addCourse"
                    onclick="window.location.href='/trudexpert/course/listener/add?listener_id=${listenerId}'">
                Добавить курс слушателю
            </button>
        </div>
    </div>

</@c.page>