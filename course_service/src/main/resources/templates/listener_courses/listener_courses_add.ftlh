<#import "../parts/commons/common_course.ftlh" as c>

<@c.page>
    <h5>Добавить курс</h5>

    <div>
        Выберите куср из списка. Затем нажмите "Прикрепить слушателя".
    </div>

    <div class="form-group mt-2 col-md-5">

        <select class="form-control" id="courseSelect" name="description">
            <option>
                Выбрать курс...
            </option>
            <#if courses??>
                <#list courses as course>
                    <option value="${course.id}">${course.description}</option>
                </#list>
            </#if>
        </select>
    </div>

    <div class="form-group mt-5" style="text-align: right">
        <button type="button" class="btn btn-primary" id="submitBtn">
            Прикрепить слушателя
        </button>
    </div>

    <script>
        const select = document.getElementById('courseSelect');
        const submitBtn = document.getElementById('submitBtn');

        submitBtn.onclick = function (event) {
            const courseId =select.value;

            const xhr = new XMLHttpRequest();

            xhr.open('POST', '/trudexpert/course/listener/add?listener_id=${listenerId}&course_id=' + courseId);

            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.send();

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        window.location.href="/trudexpert/course/listener/search?listener_id=${listenerId}";
                    }
                }
            }
        }
    </script>

</@c.page>