<#import "../parts/commons/common_course.ftlh" as c>

<@c.page>
    <div id="formInfo">
        <#if type == 'add'>
            <h5>Данные нового курса</h5>
        <#elseif type == 'edit'>
            <h5>Изменение данных курса</h5>
        </#if>
    </div>

    <form id="addCourseForm">
        <div class="form-group col-md-8">
            <label for="inputDesc">Описание курса</label>
            <textarea type="text" name="description" class="form-control" id="inputDesc" rows="6" required
                    <#if type == 'edit'>
                        value="${course.description}"
                    </#if>></textarea>
        </div>


        <div class="form-group col-md-2">
            <label for="inputStudyDays">Срок обучения</label>
            <input type="text" name="studyingTime" class="form-control" id="inputStudyDays" placeholder="Кол-во дней"
                    <#if type == 'edit'>
                        value="${course.studyingTime}"
                    </#if>>
        </div>


        <div class="form-group col-md-2">
            <label for="inputCost">Цена курса</label>
            <input type="text" name="cost" class="form-control" id="inputCost" placeholder="Цена в руб."
                    <#if type == 'edit'>
                        value="${course.cost}"
                    </#if>>
        </div>
    </form>

    <div class="form-group mt-5" style="text-align: right">
        <button type="submit" class="btn btn-primary" id="submitBtn">
            <#if type == 'add'>
                Добавить курс
            <#elseif type == 'edit'>
                Сохранить изменения
            </#if>
        </button>
    </div>

    <div class="alert alert-success" role="alert" style="visibility: hidden" id="resultMessage">
    </div>

    <div class="form-group mt-5">
        <button type="submit" class="btn btn-primary" id="backBtn"
                onclick="window.location.href='/trudexpert/course'" style="display: none">
            На главную раздела
        </button>
    </div>

    <script>
        const form = document.getElementById('addCourseForm');
        const submitBtn = document.getElementById('submitBtn');

        const resultMessage = document.getElementById('resultMessage');
        const backBtn = document.getElementById('backBtn');

        <#if type == 'add'>
        submitBtn.onclick = function (event){
            const xhr = new XMLHttpRequest();
            const formData = new FormData(form);

            xhr.open('POST', '/trudexpert/course/add');
            xhr.setRequestHeader("Content-Type", "application/json");

            xhr.send(JSON.stringify(Object.fromEntries(formData)));

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        form.style.display = 'none';
                        document.getElementById('formInfo').style.display = 'none';
                        submitBtn.style.display = 'none';

                        resultMessage.className = "alert alert-success";
                        resultMessage.textContent = "Курс успешно добавлен в систему";
                        resultMessage.style.visibility = 'visible';
                        backBtn.style.display = 'inline';
                    } else {
                        resultMessage.className = "alert alert-danger";
                        resultMessage.style.visibility = 'visible';
                        resultMessage.textContent = JSON.parse(xhr.responseText)["message"];
                    }
                }
            }

            return false;
        }
        </#if>

    </script>
</@c.page>