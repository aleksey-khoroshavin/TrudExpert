<#import "../parts/commons/common_organization.ftlh" as c>

<@c.page>
    <div id="formInfo">
        <#if type == 'add'>
        <h5>Данные новой организации</h5>
        <div>
            Администрация с пониманием относится к человеческим факторам, поэтому какие-то поля <b>могут быть не
                указаны</b>.<br>
            Заполнение недостающих полей лежит на сотрудниках организации. Без них вы <b>не сможете сформировать
                договоры!</b><br>
            Внести данные можно будет в профиле конкретной организации.<br>
            Для этого используйте подраздел <b><i>"Список организаций"</i></b>.
            <br><br>
        </div>

        <div>
            P.s. Поле <b><i>"Название организации"</i></b> заполняется в <b>ОБЯЗАТЕЛЬНОМ ПОРЯДКЕ</b>, иначе система не
            сможет найти эту организацию в списке!
        </div>

        <div class="text mb-3">
            <i>Спасибо за понимание:)</i>
        </div>
        <#elseif type == 'edit'>
            <h5>Изменение данных организации: ${organization.organizationDTO.organizationName}</h5>
            <div>
                Форма данных аналогична форме добавления новых организаций. При изменении поля <b>"Название
                    организации"</b>
                учитывайте,<br>
                что его <b>отсутсвтие не позволит вам сохранить организацию</b> в системе!<br><br>
            </div>
        <#elseif type == 'error'>
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </#if>
    </div>

    <form id="organizationForm">
        <label for="organizationForm"><b>Данные организации</b></label>
        <div class="form-group">
            <label for="inputName">Название организации</label>
            <input type="text" name="organizationName" class="form-control" id="inputName" placeholder="Название"
                   required
                    <#if type == 'edit'>
                        value="${organization.organizationDTO.organizationName}"
                    </#if>>
        </div>

        <div class="form-group">
            <label for="inputLawAddress">Юридический адрес органазации</label>
            <input type="text" name="lawAddress" class="form-control" id="inputLawAddress" placeholder="Юридич. адрес"
                    <#if type == 'edit'>
                        value="${organization.organizationDTO.lawAddress}"
                    </#if>>
        </div>

        <div class="form-group">
            <label for="inputFactAddress">Фактический адрес организации</label>
            <input type="text" name="factAddress" class="form-control" id="inputFactAddress" placeholder="Фактич. адрес"
                    <#if type == 'edit'>
                        value="${organization.organizationDTO.factAddress}"
                    </#if>>
        </div>


        <div class="form-row">
            <div class="form-group col">
                <label for="typePhone">Номер телефона</label>
                <div class="input-group" id="typePhone">
                    <div class="input-group-prepend">
                        <div class="input-group-text">+7</div>
                    </div>
                    <input type="tel" name="phone" class="form-control" placeholder="(___)-___-__-__"
                            <#if type == 'edit'>
                                value="${organization.organizationDTO.phone}"
                            </#if>
                    />
                </div>
            </div>

            <div class="form-group col">
                <label for="inputEmail">Email</label>
                <input type="text" class="form-control" name="email" id="inputEmail" placeholder="example@.com"
                        <#if type == 'edit'>
                            value="${organization.organizationDTO.email}"
                        </#if>>
            </div>

        </div>


        <div class="form-row">
            <div class="form-group col">
                <label for="inputINN">ИНН</label>
                <input type="text" name="inn" class="form-control" id="inputINN" placeholder="ИНН"
                        <#if type == 'edit'>
                            value="${organization.organizationDTO.inn}"
                        </#if>>
            </div>

            <div class="form-group col">
                <label for="inputKPP">КПП</label>
                <input type="text" name="kpp" class="form-control" id="inputKPP" placeholder="КПП"
                        <#if type == 'edit'>
                            value="${organization.organizationDTO.kpp}"
                        </#if>>
            </div>
        </div>

        <div class="form-group">
            <label for="inputOGRN">ОГРН</label>
            <input type="text" class="form-control" name="orgn" id="inputOGRN" placeholder="ОГРН"
                    <#if type == 'edit'>
                        value="${organization.organizationDTO.orgn}"
                    </#if>>
        </div>

        <div class="form-row">
            <div class="form-group col">
                <label for="inputOKVED">ОКВЕД</label>
                <input type="text" class="form-control" name="okved" id="inputOKVED" placeholder="ОКВЕД"
                        <#if type == 'edit'>
                            value="${organization.organizationDTO.okved}"
                        </#if>>
            </div>

            <div class="form-group col">
                <label for="inputOKPO">ОКПО</label>
                <input type="text" class="form-control" name="okpo" id="inputOKPO" placeholder="ОКВЕД"
                        <#if type == 'edit'>
                            value="${organization.organizationDTO.okpo}"
                        </#if>>
            </div>
        </div>

        <div class="form-group">
            <label for="inputCheckingAccount">Р/с</label>
            <input type="text" class="form-control" name="checkingAccount" id="inputCheckingAccount" placeholder="Р/с"
                    <#if type == 'edit'>
                        value="${organization.organizationDTO.checkingAccount}"
                    </#if>>
        </div>

        <div class="form-group">
            <label for="inputCorrespondentAccount">К/с</label>
            <input type="text" class="form-control" name="correspondentAccount" id="inputCorrespondentAccount"
                   placeholder="К/с"
                    <#if type == 'edit'>
                        value="${organization.organizationDTO.correspondentAccount}"
                    </#if>>
        </div>


        <div class="form-group">
            <label for="inputBIK">БИК</label>
            <input type="text" class="form-control" name="bik" id="inputBIK" placeholder="БИК"
                    <#if type == 'edit'>
                        value="${organization.organizationDTO.bik}"
                    </#if>>
        </div>
    </form>


    <form id="agentForm">
        <label for="agentForm"><b>Данные контрагента</b></label>
        <div class="form-row">
            <div class="form-group col">
                <label for="inputSurname">Фамилия</label>
                <input type="text" name="surname" class="form-control" id="inputSurname" placeholder="Фамилия">
            </div>
            <div class="form-group col">
                <label for="inputName">Имя</label>
                <input type="text" name="name" class="form-control" id="inputName" placeholder="Имя">
            </div>
            <div class="form-group col">
                <label for="inputPatronymic">Отчество</label>
                <input type="text" name="patronymic" class="form-control" id="inputPatronymic"
                       placeholder="Отчество (при наличии)">
            </div>
        </div>

        <div class="form-group">
            <label for="inputName">Документ</label>
            <input type="text" name="document" class="form-control" id="inputName" placeholder="Документ">
        </div>

        <div class="form-group">
            <label for="inputLawAddress">Должность</label>
            <input type="text" name="post" class="form-control" id="inputLawAddress" placeholder="Должность">
        </div>
    </form>

    <div class="alert alert-success" role="alert" style="visibility: hidden" id="resultMessageOrganization">
    </div>

    <div class="form-group" style="text-align: right">
        <button type="submit" class="btn btn-primary" id="submitBtn">
            <#if type == 'add'>
                Добавить организацию
            <#elseif type == 'edit'>
                Сохранить изменения
            </#if>
        </button>

        <button type="submit" class="btn btn-primary" id="backBtn"
                onclick="window.location.href='/trudexpert/organization'" style="display: none">
            На главную раздела
        </button>

    </div>

    <script>
        const organizationForm = document.getElementById('organizationForm');
        const organizationAgentForm = document.getElementById('agentForm');

        const submitBtn = document.getElementById('submitBtn');
        const backBtn = document.getElementById('backBtn');

        const resultMessage = document.getElementById('resultMessageOrganization');

        document.getElementById('typePhone').addEventListener('input', function (e) {
            const x = e.target.value.replace(/\D/g, '').match(/(\d{0,3})(\d{0,3})(\d{0,2})(\d{0,2})/);
            e.target.value = !x[2] ? x[1] : '(' + x[1] + ')-' + x[2] + (x[3] ? '-' + x[3] : '') + (x[4] ? '-' + x[4] : '');
        });

        <#if type == 'error'>
            organizationForm.style.display = 'none';
            organizationAgentForm.style.display = 'none';
            submitBtn.style.display = 'none';

            backBtn.style.display = 'inline';
        </#if>

        <#if type == 'add'>
        submitBtn.onclick = function (event) {
            const xhr = new XMLHttpRequest();
            const organizationFormData = new FormData(organizationForm);
            const organizationAgentData = new FormData(organizationAgentForm);

            xhr.open('POST', '/trudexpert/organization/add');
            xhr.setRequestHeader("Content-Type", "application/json");

            xhr.send(JSON.stringify({organizationDTO : Object.fromEntries(organizationFormData),
            organizationAgentDTO : Object.fromEntries(organizationAgentData)}));

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        resultMessage.className = "alert alert-success";
                        resultMessage.style.visibility = 'visible';
                        resultMessage.textContent = "Организация успешно добавлена в систему.";

                        organizationForm.style.display = 'none';
                        organizationAgentForm.style.display = 'none';
                        document.getElementById('formInfo').style.display = 'none';
                        submitBtn.style.display = 'none';
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

        <#if type == 'edit'>
        organizationAgentForm.style.display = 'none';
        submitBtn.onclick = function (event) {
            const xhr = new XMLHttpRequest();
            const formData = new FormData(organizationForm);

            xhr.open('POST', '/trudexpert/organization/update?id=${organization.organizationDTO.id}');
            xhr.setRequestHeader("Content-Type", "application/json");

            xhr.send(JSON.stringify(Object.fromEntries(formData)));

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        resultMessage.className = "alert alert-success";
                        resultMessage.style.visibility = 'visible';
                        resultMessage.textContent = "Данные организации успешно обновлены";
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