<#import "../parts/commons/common_listener.ftlh" as c>

<@c.page>
    <h5>Данные нового слушателя</h5>
    <div>
        Администрация с пониманием относится к человеческим факторам, поэтому какие-то поля <b>могут быть не указаны</b>.<br>
        Заполнение недостающих полей лежит на сотрудниках организации. Без них вы <b>не сможете сформировать договоры</b>!<br>
        Внести данные можно будет в профиле конкрентого слушателя.<br>
        Для этого используйте подраздел <b><i>"Найти слушателя"</i></b>.<br><br>
    </div>

    <div class="text mb-3">
        <i>Спасибо за понимание:)</i>
    </div>

    <form method="post" action="/trudexpert/listener/add">
        <div class="form-row">
            <div class="form-group col">
                <label for="inputSurname">Фамилия</label>
                <input type="text" class="form-control" id="inputSurname" placeholder="Фамилия">
            </div>
            <div class="form-group col">
                <label for="inputName">Имя</label>
                <input type="text" class="form-control" id="inputName" placeholder="Имя">
            </div>
            <div class="form-group col">
                <label for="inputPatronymic">Отчество</label>
                <input type="text" class="form-control" id="inputPatronymic" placeholder="Отчество (при наличии)">
            </div>
        </div>

        <div class="form-group">
            <div id="date-picker-example" class="md-form md-outline input-with-post-icon datepicker" inline="true">
                <label for="inputDateOfBirth">Дата рождения</label>
                <input id="inputDateOfBirth" class="form-control" type="date" />
                <i class="fas fa-calendar input-prefix"></i>
            </div>
        </div>

        <div class="form-group">
            <label for="inputSnils">СНИЛС</label>
            <input type="text" class="form-control" id="inputSnils" placeholder="СНИЛС">
        </div>

        <fieldset class="form-group">
            <div class="row">
                <legend class="col-form-label col-sm-1 pt-0">Пол</legend>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gridRadios" id="male" value="option1" checked>
                        <label class="form-check-label" for="male">
                            Мужской
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gridRadios" id="female" value="option2">
                        <label class="form-check-label" for="female">
                            Женский
                        </label>
                    </div>
                </div>
            </div>
        </fieldset>

        <div class="form-group">
            <label for="typePhone">Номер телефона</label>
            <input type="tel" id="typePhone" class="form-control" pattern="[8]{1}-[0-9]{3}-[0-9]{2}-[0-9]{2}-[0-9]{2}" placeholder="8-___-___-__-__"/>
        </div>

        <div class="form-group">
            <label for="inputCitizenshipCode">Гражданство получателя (код страны по ОКСМ). По умолчанию №643 (Россия)</label>
            <input type="text" class="form-control" id="inputCitizenshipCode" placeholder="Код страны по ОКСМ" value="643">
        </div>

        <div class="form-group">
            <label for="inputDriverLicense">Водительское удостоверение. (При наличии)</label>
            <input type="text" class="form-control" id="inputDriverLicense" placeholder="Номер, кем выдано">
        </div>

        <div class="form-group">
            <label for="inputAddress">Адрес проживания</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="Почтовый индекс, Город, Улица, Дом, Квартира">
        </div>

        <label for="passportForm">Паспортые данные</label>
        <div class="form-row" id="passportForm">
            <div class="form-group col-md-2">
                <label for="inputPassportSeries">Серия</label>
                <input type="number" class="form-control" id="inputPassportSeries" placeholder="Серия">
            </div>
            <div class="form-group col-md-2">
                <label for="inputPassportNumber">Номер</label>
                <input type="number" class="form-control" id="inputPassportNumber" placeholder="Номер">
            </div>
            <div class="form-group col-md-8">
                <label for="inputPassportIssuedBy">Кем выдан</label>
                <input type="text" class="form-control" id="inputPassportIssuedBy" placeholder="Кем выдан">
            </div>
            <div class="form-group col-md-2">
                <label for="inputIssuedDate">Дата выдачи</label>
                <input id="inputIssuedDate" class="form-control" type="date"/>
                <i class="fas fa-calendar input-prefix"></i>
            </div>
        </div>

        <div class="form-group">
            <label for="inputEducation">Образование</label>
            <input type="text" class="form-control" id="inputEducation" placeholder="Тип обрзования, серия, номер документа">
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary">Добавить слушателя</button>
        </div>

    </form>
</@c.page>