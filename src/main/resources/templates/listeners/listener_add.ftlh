<#import "../parts/commons/common_listener.ftlh" as c>

<@c.page>
    <h5>Данные нового слушателя</h5>
    <div>
        Администрация с пониманием относится к человеческим факторам, поэтому какие-то поля <b>могут быть не указаны</b>.<br>
        Заполнение недостающих полей лежит на сотрудниках организации. Без них вы <b>не сможете сформировать
            договоры!</b><br>
        Внести данные можно будет в профиле конкрентого слушателя.<br>
        Для этого используйте подраздел <b><i>"Найти слушателя"</i></b>.<br><br>
    </div>

    <div>
        P.s. Поля <b><i>"ФИО"</i></b> и <b><i>"Дата рождения"</i></b> заполняются в <b>ОБЯЗАТЕЛЬНОМ ПОРЯДКЕ</b>, иначе
        система не сможет найти этих людей!
    </div>

    <div class="text mb-3">
        <i>Спасибо за понимание:)</i>
    </div>

    <form method="post" action="/trudexpert/listener/add" id="addListenerForm">
        <div class="form-row">
            <div class="form-group col">
                <label for="inputSurname">Фамилия</label>
                <input type="text" name="surname" class="form-control" id="inputSurname" placeholder="Фамилия" required>
            </div>
            <div class="form-group col">
                <label for="inputName">Имя</label>
                <input type="text" name="name" class="form-control" id="inputName" placeholder="Имя" required>
            </div>
            <div class="form-group col">
                <label for="inputPatronymic">Отчество</label>
                <input type="text" name="patronymic" class="form-control" id="inputPatronymic"
                       placeholder="Отчество (при наличии)">
            </div>

            <div class="form-group col-sm-2">
                <div id="date-picker-example" class="md-form md-outline input-with-post-icon datepicker" inline="true">
                    <label for="inputDateOfBirth">Дата рождения</label>
                    <input id="inputDateOfBirth" name="dateOfBirth" class="form-control" type="date" required/>
                    <i class="fas fa-calendar input-prefix"></i>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="inputSnils">СНИЛС</label>
            <input type="text" name="snils" class="form-control" id="inputSnils" placeholder="СНИЛС">
        </div>

        <fieldset class="form-group">
            <div class="row">
                <legend class="col-form-label col-sm-1 pt-0">Пол</legend>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="male" value="Мужской" checked>
                        <label class="form-check-label" for="male">
                            Мужской
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gender" id="female" value="Женский">
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
                <input type="tel" name="phoneNumber" class="form-control" placeholder="(___)-___-__-__"/>
            </div>

        </div>

        <div class="form-group">
            <label for="inputCitizenshipCode">Гражданство получателя (код страны по ОКСМ). По умолчанию №643
                (Россия)</label>
            <input type="text" name="citizenshipCode" class="form-control" id="inputCitizenshipCode"
                   placeholder="Код страны по ОКСМ" value="643">
        </div>

        <div class="form-group">
            <label for="inputDriverLicense">Водительское удостоверение. (При наличии)</label>
            <input type="text" name="driverLicense" class="form-control" id="inputDriverLicense"
                   placeholder="Номер, кем выдано, дата выдачи, категория(-и), стаж(при необходимости)">
        </div>

        <div class="form-group">
            <label for="inputAddress">Адрес регистрации</label>
            <input type="text" class="form-control" name="address" id="inputAddress"
                   placeholder="Почтовый индекс, Город, Улица, Дом, Квартира">
        </div>

        <label for="passportForm"><b>Паспортные данные</b></label>
        <div class="form-row" id="passportForm">
            <div class="form-group col-md-2">
                <label for="inputPassportSeries">Серия</label>
                <input type="text" name="passportSeries" class="form-control" id="inputPassportSeries"
                       placeholder="Серия">
            </div>
            <div class="form-group col-md-2">
                <label for="inputPassportNumber">Номер</label>
                <input type="text" name="passportNumber" class="form-control" id="inputPassportNumber"
                       placeholder="Номер">
            </div>
            <div class="form-group col-md-8">
                <label for="inputPassportIssuedBy">Кем выдан</label>
                <input type="text" name="passportIssuedBy" class="form-control" id="inputPassportIssuedBy"
                       placeholder="Кем выдан">
            </div>
            <div class="form-group col-md-2">
                <label for="inputIssuedDate">Дата выдачи</label>
                <input id="inputIssuedDate" class="form-control" type="date" name="passportIssuedAt"/>
                <i class="fas fa-calendar input-prefix"></i>
            </div>
        </div>

        <label for="educationForm"><b>Образование</b></label>
        <div class="form-row" id="educationForm">
            <div class="form-group col-md-8">
                <label for="educationTypeSelect">Тип образования</label>
                <select class="form-control" id="educationTypeSelect" name="educationType">
                    <option>Высшеее</option>
                    <option>Средне профессиональное</option>
                    <option>Среднее общее</option>
                    <option>Основное общее</option>
                </select>
            </div>

            <div class="form-group col-md-8">
                <label for="inputEducation">Документ об образовании</label>
                <input type="text" name="educationDocument" class="form-control" id="inputEducation"
                       placeholder="Серия, номер документа">
            </div>

            <div class="form-group col-md-2">
                <label for="inputIssuedEducationDate">Дата выдачи</label>
                <input id="inputIssuedEducationDate" class="form-control" type="date" name="educationDocumentIssuedAt"/>
                <i class="fas fa-calendar input-prefix"></i>
            </div>

        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary">Добавить слушателя</button>
        </div>

    </form>

    <script>
        const form = document.getElementById('addListenerForm');

        document.getElementById('typePhone').addEventListener('input', function (e) {
            const x = e.target.value.replace(/\D/g, '').match(/(\d{0,3})(\d{0,3})(\d{0,2})(\d{0,2})/);
            e.target.value = !x[2] ? x[1] : '(' + x[1] + ')-' + x[2] + (x[3] ? '-' + x[3] : '') + (x[4] ? '-' + x[4] : '');
        });

        form.onsubmit = function (event) {
            const xhr = new XMLHttpRequest();
            const formData = new FormData(form);

            xhr.open('POST', '/trudexpert/listener/add');
            xhr.setRequestHeader("Content-Type", "application/json");

            xhr.send(JSON.stringify(Object.fromEntries(formData)));

            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    form.reset();
                }
            }

            return false;
        }
    </script>
</@c.page>