<#import "../../parts/commons/common_organization.ftlh" as c>

<@c.page>

    <form id="agentForm">
        <label for="agentForm"><b>Данные контрагента от организации "${company}"</b></label>
        <div class="form-row">
            <div class="form-group col">
                <label for="inputSurname">Фамилия</label>
                <input type="text" name="surname" class="form-control" id="inputSurname" placeholder="Фамилия"
                        <#if agent??>
                            value="${agent.surname}"
                        </#if>
                >
            </div>
            <div class="form-group col">
                <label for="inputName">Имя</label>
                <input type="text" name="name" class="form-control" id="inputName" placeholder="Имя"
                        <#if agent??>
                            value="${agent.name}"
                        </#if>
                >
            </div>
            <div class="form-group col">
                <label for="inputPatronymic">Отчество</label>
                <input type="text" name="patronymic" class="form-control" id="inputPatronymic"
                       placeholder="Отчество (при наличии)"
                        <#if agent??>
                            value="${agent.patronymic}"
                        </#if>
                >
            </div>
        </div>

        <div class="form-group">
            <label for="inputName">Документ</label>
            <input type="text" name="document" class="form-control" id="inputName" placeholder="Документ"
                    <#if agent??>
                        value="${agent.document}"
                    </#if>
            >
        </div>

        <div class="form-group">
            <label for="inputLawAddress">Должность</label>
            <input type="text" name="post" class="form-control" id="inputLawAddress" placeholder="Должность"
                    <#if agent??>
                        value="${agent.post}"
                    </#if>
            >
        </div>
    </form>

    <div class="alert alert-success" role="alert" style="visibility: hidden" id="resultMessageAgent">
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="submitBtn">
            Сохранить изменения
        </button>
    </div>

    <script>
        const agentForm = document.getElementById('agentForm');
        const submitBtn = document.getElementById('submitBtn');
        const resultMessageAgent = document.getElementById('resultMessageAgent');

        submitBtn.onclick = function (event) {
            resultMessageAgent.style.visibility = 'hidden';

            const xhr = new XMLHttpRequest();
            const formData = new FormData(agentForm);

            xhr.open('POST', '/trudexpert/organization/agent/update?agent_id=${agent.id}');
            xhr.setRequestHeader("Content-Type", "application/json");

            xhr.send(JSON.stringify(Object.fromEntries(formData)));

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        resultMessageAgent.className = "alert alert-success";
                        resultMessageAgent.style.visibility = 'visible';
                        resultMessageAgent.textContent = "Данные агента успешно обновлены";
                    } else {
                        resultMessageAgent.className = "alert alert-danger";
                        resultMessageAgent.style.visibility = 'visible';
                        resultMessageAgent.textContent = JSON.parse(xhr.responseText)["message"];
                    }
                }
            }

            return false;
        }
    </script>

</@c.page>