<#import "../parts/commons/common_listener.ftlh" as c>

<@c.page>
    <h5>Добавить организацию</h5>

    <div>
        Выберите организацию из списка. Затем нажмите "Прикрепить слушателя".
    </div>

    <div class="form-group mt-2 col-md-5">

        <select class="form-control" id="organizationSelect" name="organizationName">
            <option>
                Выбрать организацию...
            </option>
            <#if organizations??>
                <#list organizations as organization>
                    <option value="${organization.id}">${organization.name}</option>
                </#list>
            </#if>
        </select>
    </div>


    <div class="form-group mt-2 col-md-5">
        <label for="inputPost">Должность в организации</label>
        <input type="text" name="post" class="form-control" id="inputPost"
               placeholder="Название должности">
    </div>

    <div class="form-group mt-5" style="text-align: right">
        <button type="button" class="btn btn-primary" id="submitBtn">
            Прикрепить слушателя
        </button>
    </div>


    <script>
        const select = document.getElementById('organizationSelect');
        const submitBtn = document.getElementById('submitBtn');

        submitBtn.onclick = function (event) {

            const organizationId = select.value;
            const post = document.getElementById('inputPost').value;

            const xhr = new XMLHttpRequest();

            xhr.open('POST', '/trudexpert/listener/organizations/add?organizationId='
                + organizationId + '&listenerId=${listenerId}' + '&post=' + post);

            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.send();

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        window.location.href="/trudexpert/listener/organizations?id=${listenerId}&name=${listenerName}";
                    }
                }
            }
        }

    </script>

</@c.page>