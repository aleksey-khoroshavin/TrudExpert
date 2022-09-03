<#import "../parts/commons/common_listener.ftlh" as c>

<@c.page>
    <div id="formInfo">
        <#if type == 'add'>
            <h5>Данные нового слушателя</h5>
            <div>
                Администрация с пониманием относится к человеческим факторам, поэтому какие-то поля <b>могут быть не
                    указаны</b>.<br>
                Заполнение недостающих полей лежит на сотрудниках организации. Без них вы <b>не сможете сформировать
                    договоры!</b><br>
                Внести данные можно будет в профиле конкрентого слушателя.<br>
                Для этого используйте подраздел <b><i>"Найти слушателя"</i></b>.<br><br>
            </div>

            <div>
                P.s. Поля <b><i>"ФИО"</i></b> и <b><i>"Дата рождения"</i></b> заполняются в <b>ОБЯЗАТЕЛЬНОМ ПОРЯДКЕ</b>,
                иначе система не сможет найти этих людей!
            </div>

            <div class="text mb-3">
                <i>Спасибо за понимание:)</i>
            </div>
        <#elseif type == 'edit'>
            <h5>Изменение данных слушателя: ${listener.surname} ${listener.name} ${listener.patronymic}</h5>
            <div>
                Форма данных аналогична форме добавления новых слушателей. При изменении полей <b>ФИО и даты
                    рождения</b> учитывайте,<br>
                что их <b>отсутсвтие не позволит вам сохранить пользователя</b> в системе!<br><br>
            </div>
        </#if>
    </div>

    <form id="addListenerForm">
        <div class="form-row">
            <div class="form-group col">
                <label for="inputSurname">Фамилия</label>
                <input type="text" name="surname" class="form-control" id="inputSurname" placeholder="Фамилия" required
                        <#if type == 'edit'>
                    value="${listener.surname}"
                        </#if>>
            </div>
            <div class="form-group col">
                <label for="inputName">Имя</label>
                <input type="text" name="name" class="form-control" id="inputName" placeholder="Имя" required
                        <#if type == 'edit'>
                    value="${listener.name}"
                        </#if>>
            </div>
            <div class="form-group col">
                <label for="inputPatronymic">Отчество</label>
                <input type="text" name="patronymic" class="form-control" id="inputPatronymic"
                       placeholder="Отчество (при наличии)"
                        <#if type == 'edit'>
                    value="${listener.patronymic}"
                        </#if>>
            </div>

            <div class="form-group col-sm-2">
                <div id="date-picker-example" class="md-form md-outline input-with-post-icon datepicker" inline="true">
                    <label for="inputDateOfBirth">Дата рождения</label>
                    <input id="inputDateOfBirth" name="dateOfBirth" class="form-control" type="date" required
                            <#if type == 'edit'>
                                value="${listener.dateOfBirth}"
                            </#if>
                    />
                    <i class="fas fa-calendar input-prefix"></i>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="inputSnils">СНИЛС</label>
            <input type="text" name="snils" class="form-control" id="inputSnils" placeholder="СНИЛС"
                    <#if type == 'edit'>
                value="${listener.snils}"
                    </#if>>
        </div>

        <fieldset class="form-group">
            <div class="row">
                <legend class="col-form-label col-sm-1 pt-0">Пол</legend>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="male" value="Мужской"
                                <#if type == 'edit'>
                                    <#if "${listener.gender}" == "Мужской">
                                        checked
                                    </#if>
                                </#if>

                                <#if type == 'add'>
                                    checked
                                </#if>
                        >
                        <label class="form-check-label" for="male">
                            Мужской
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="female" value="Женский"
                                <#if type == 'edit'>
                                    <#if "${listener.gender}" == "Женский">
                                        checked
                                    </#if>
                                </#if>
                        >
                        <label class="form-check-label" for="female">
                            Женский
                        </label>
                    </div>
                </div>
            </div>
        </fieldset>

        <div class="form-group">
            <label for="typePhone">Номер телефона</label>
            <div class="input-group" id="typePhone">
                <div class="input-group-prepend">
                    <div class="input-group-text">+7</div>
                </div>
                <input type="tel" name="phoneNumber" class="form-control" placeholder="(___)-___-__-__"
                        <#if type == 'edit'>
                            value="${listener.phoneNumber}"
                        </#if>
                />
            </div>

        </div>

        <div class="form-group">
            <label for="inputCitizenshipCode">Гражданство получателя (код страны по ОКСМ). По умолчанию №643
                (Россия)</label>
            <input type="text" name="citizenshipCode" class="form-control" id="inputCitizenshipCode"
                   placeholder="Код страны по ОКСМ"
                    <#if type == 'edit'>
                        value="${listener.citizenshipCode}"
                    </#if>
                    <#if type == 'add'>
                        value="643"
                    </#if>
            >
        </div>

        <div class="form-group">
            <label for="inputDriverLicense">Водительское удостоверение. (При наличии)</label>
            <input type="text" name="driverLicense" class="form-control" id="inputDriverLicense"
                   placeholder="Номер, кем выдано, дата выдачи, категория(-и), стаж(при необходимости)"
                    <#if type == 'edit'>
                        value="${listener.driverLicense}"
                    </#if>
            >
        </div>

        <div class="form-group">
            <label for="inputAddress">Адрес регистрации</label>
            <input type="text" class="form-control" name="address" id="inputAddress"
                   placeholder="Почтовый индекс, Город, Улица, Дом, Квартира"
                    <#if type == 'edit'>
                value="${listener.address}"
                    </#if>>
        </div>

        <label for="passportForm"><b>Паспортные данные</b></label>
        <div class="form-row" id="passportForm">
            <div class="form-group col-md-2">
                <label for="inputPassportSeries">Серия</label>
                <input type="text" name="passportSeries" class="form-control" id="inputPassportSeries"
                       placeholder="Серия"
                        <#if type == 'edit'>
                            value="${listener.passportSeries}"
                        </#if>
                >
            </div>
            <div class="form-group col-md-2">
                <label for="inputPassportNumber">Номер</label>
                <input type="text" name="passportNumber" class="form-control" id="inputPassportNumber"
                       placeholder="Номер"
                        <#if type == 'edit'>
                            value="${listener.passportNumber}"
                        </#if>
                >
            </div>
            <div class="form-group col-md-8">
                <label for="inputPassportIssuedBy">Кем выдан</label>
                <input type="text" name="passportIssuedBy" class="form-control" id="inputPassportIssuedBy"
                       placeholder="Кем выдан"
                        <#if type == 'edit'>
                            value="${listener.passportIssuedBy}"
                        </#if>
                >
            </div>
            <div class="form-group col-md-2">
                <label for="inputIssuedDate">Дата выдачи</label>
                <input id="inputIssuedDate" class="form-control" type="date" name="passportIssuedAt"
                        <#if type == 'edit'>
                            value="${listener.passportIssuedAt!""}"
                        </#if>
                />
                <i class="fas fa-calendar input-prefix"></i>
            </div>
        </div>

        <label for="educationForm"><b>Образование</b></label>
        <div class="form-row" id="educationForm">
            <div class="form-group col-md-8">
                <label for="educationTypeSelect">Тип образования</label>
                <select class="form-control" id="educationTypeSelect" name="educationType">
                    <option <#if type == 'edit'>
                        <#if "${listener.educationType}" == 'Высшее'>
                            selected
                        </#if>
                    </#if>
                    >Высшеее
                    </option>
                    <option
                            <#if type == 'edit'>
                                <#if "${listener.educationType}" == 'Средне профессиональное'>
                                    selected
                                </#if>
                            </#if>
                    >Средне профессиональное
                    </option>
                    <option
                            <#if type == 'edit'>
                                <#if "${listener.educationType}" == 'Среднее общее'>
                                    selected
                                </#if>
                            </#if>
                    >Среднее общее
                    </option>
                    <option
                            <#if type == 'edit'>
                                <#if "${listener.educationType}" == 'Основное общее'>
                                    selected
                                </#if>
                            </#if>
                    >Основное общее
                    </option>
                </select>
            </div>

            <div class="form-group col-md-5">
                <label for="inputEducation">Документ об образовании</label>
                <input type="text" name="educationDocument" class="form-control" id="inputEducation"
                       placeholder="Серия, номер документа"
                        <#if type == 'edit'>
                            value="${listener.educationDocument}"
                        </#if>
                >
            </div>

            <div class="form-group col-md-5">
                <label for="inputEducationSurname">Фамилия в дипломе (при смене)</label>
                <input type="text" name="educationSurname" class="form-control" id="inputEducationSurname"
                       placeholder="Фамилия в дипломе"
                        <#if type == 'edit'>
                            value="${listener.educationSurname}"
                        </#if>
                >
            </div>

            <div class="form-group col-md-2">
                <label for="inputIssuedEducationDate">Дата выдачи</label>
                <input id="inputIssuedEducationDate" class="form-control" type="date" name="educationDocumentIssuedAt"
                        <#if type == 'edit'>
                            value="${listener.educationDocumentIssuedAt!""}"
                        </#if>
                />
                <i class="fas fa-calendar input-prefix"></i>
            </div>

        </div>
    </form>

    <#if type == 'edit'>
        <label for="organizationsBtn"><b>Организации слушателя (заполняется при необходимости)</b></label>
        <div class="form-group" id="organizationsBtn">
            <button type="submit" class="btn btn-primary"
                    onclick="window.location.href='/trudexpert/listener/organizations?id=${listener.id}&name=${listener.surname + ' ' + listener.name + ' ' + listener.patronymic}'">
                Список организаций
            </button>
        </div>

        <label for="coursesBtn"><b>Назначенные курсы для данного слушателя (заполняется, чтобы добавить слушателя в группу)</b></label>
        <div class="form-group" id="coursesBtn">
            <button type="submit" class="btn btn-primary">
                Список назначенных курсов
            </button>
        </div>

    </#if>

    <div class="form-group mt-5" style="text-align: right">
        <button type="submit" class="btn btn-primary" id="submitBtn">
            <#if type == 'add'>
                Добавить слушателя
            <#elseif type == 'edit'>
                Сохранить изменения
            </#if>
        </button>
    </div>

    <div class="alert alert-success" role="alert" style="visibility: hidden" id="resultMessage">
    </div>

    <div class="form-group mt-5">
        <button type="submit" class="btn btn-primary" id="backBtn"
                onclick="window.location.href='/trudexpert/listener'" style="display: none">
            На главную раздела
        </button>
    </div>


    <script>
        const form = document.getElementById('addListenerForm');
        const submitBtn = document.getElementById('submitBtn');

        const resultMessage = document.getElementById('resultMessage');

        const backBtn = document.getElementById('backBtn');

        document.getElementById('typePhone').addEventListener('input', function (e) {
            const x = e.target.value.replace(/\D/g, '').match(/(\d{0,3})(\d{0,3})(\d{0,2})(\d{0,2})/);
            e.target.value = !x[2] ? x[1] : '(' + x[1] + ')-' + x[2] + (x[3] ? '-' + x[3] : '') + (x[4] ? '-' + x[4] : '');
        });

        document.getElementById('inputSnils').addEventListener('input', function (e) {
           const x = e.target.value.replace(/\D/g, '').match(/(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/);
           e.target.value = !x[2] ? x[1] : x[1] + '-' + x[2] + (x[3] ? '-' + x[3] : '') + (x[4] ? ' ' + x[4] : '');
        });

        <#if type == 'add'>
        submitBtn.onclick = function (event) {
            const xhr = new XMLHttpRequest();
            const formData = new FormData(form);

            xhr.open('POST', '/trudexpert/listener/add');
            xhr.setRequestHeader("Content-Type", "application/json");

            xhr.send(JSON.stringify(Object.fromEntries(formData)));

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        form.style.display = 'none';
                        document.getElementById('formInfo').style.display = 'none';
                        submitBtn.style.display = 'none';

                        resultMessage.className = "alert alert-success";
                        resultMessage.textContent = "Слушатель успешно добавлен в систему";
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

        <#if type == 'edit'>
        submitBtn.onclick = function (event) {
            resultMessage.style.visibility = 'hidden';
            const xhr = new XMLHttpRequest();
            const formData = new FormData(form);

            xhr.open('POST', '/trudexpert/listener/update?listener_id=${listener.id}');
            xhr.setRequestHeader("Content-Type", "application/json");

            xhr.send(JSON.stringify(Object.fromEntries(formData)));

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        resultMessage.className = "alert alert-success";
                        resultMessage.style.visibility = 'visible';
                        resultMessage.textContent = "Данные слушателя успешно обновлены";
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
