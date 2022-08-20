<#import "../../parts/commons/common_organization.ftlh" as c>

<@c.page>
    <form id="agentForm">
        <label for="agentForm"><b>Данные контрагента</b></label>
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
                        </#if>>
            </div>
            <div class="form-group col">
                <label for="inputPatronymic">Отчество</label>
                <input type="text" name="patronymic" class="form-control" id="inputPatronymic"
                       placeholder="Отчество (при наличии)"
                        <#if agent??>
                            value="${agent.patronymic}"
                        </#if>>
            </div>
        </div>

        <div class="form-group">
            <label for="inputName">Документ</label>
            <input type="text" name="document" class="form-control" id="inputName" placeholder="Документ"
                    <#if agent??>
                        value="${agent.document}"
                    </#if>>
        </div>

        <div class="form-group">
            <label for="inputLawAddress">Должность</label>
            <input type="text" name="post" class="form-control" id="inputLawAddress" placeholder="Должность"
                    <#if agent??>
                        value="${agent.post}"
                    </#if>>
        </div>
    </form>

    <script>

    </script>
</@c.page>