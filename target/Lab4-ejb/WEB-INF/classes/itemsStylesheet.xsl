<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" encoding="UTF-8" omit-xml-declaration="yes"/>

    <xsl:param name="serverUri" />

    <xsl:template match="/weapon">
        <div class="container text-center show-item">
            <div class="view-pane">
                <table class="table table-inverse">
                    <thead><tr><th></th><th>Оружие</th></tr></thead>
                    <tr>
                        <td>Название оружия</td>
                        <td><xsl:value-of select="name" /></td>
                    </tr>
                    <tr>
                        <td>Уровень</td>
                        <td><xsl:value-of select="level" /></td>
                    </tr>
                    <tr>
                        <td>К урону</td>
                        <td><xsl:value-of select="power" /></td>
                    </tr>
                    <tr>
                        <td>К паррированию</td>
                        <td><xsl:value-of select="parry" /></td>
                    </tr>
                    <tr>
                        <td>Цена</td>
                        <td><xsl:value-of select="price" /></td>
                    </tr>
                </table>
                <xsl:if test="id != 2">
                    <div class="btn-group" role="group">
                        <a href="{$serverUri}/views/editItems.jsp?searchObject=weapon&amp;id={id}"
                           class="btn btn-secondary">Изменить
                        </a>
                        <button type="button" class="btn btn-danger" onclick="showAlertCommitDeletion()">Удалить</button>
                    </div>
                    <div class="alert alert-warning text-center alert-commit-deletion collapse" role="alert">
                        <strong>Удалить?</strong> Вы действительно хотите удалить данное оружие?
                        <hr/>
                        <button type="button" class="btn btn-danger" onclick="removeWeapon({id})">Удалить</button>
                    </div>
                </xsl:if>
            </div>
            <div class="alert alert-success text-center collapse alert-successfully-deleted" role="alert">
                <p>Успешно удалено!</p>
            </div>
            <div class="alert alert-error alert-server-error collapse">
                <h4>ERROR</h4>
                <p>Внутренняя ошибка сервера</p>
            </div>
        </div>
    </xsl:template>

    <xsl:template match="/equipment">
        <div class="container text-center show-item">
            <div class="view-pane">
                <table class="table table-inverse">
                    <thead><tr><th></th><th>Экипировка</th></tr></thead>
                    <tr>
                        <td>Название экипировки</td>
                        <td><xsl:value-of select="name" /></td>
                    </tr>
                    <tr>
                        <td>Уровень</td>
                        <td><xsl:value-of select="level" /></td>
                    </tr>
                    <tr>
                        <td>К защите</td>
                        <td><xsl:value-of select="protection" /></td>
                    </tr>
                    <tr>
                        <td>К скорости</td>
                        <td><xsl:value-of select="speed" /></td>
                    </tr>
                    <tr>
                        <td>К паррированию</td>
                        <td><xsl:value-of select="parry" /></td>
                    </tr>
                    <tr>
                        <td>Цена</td>
                        <td><xsl:value-of select="price" /></td>
                    </tr>
                </table>
                <xsl:if test="id != 7">
                    <div class="btn-group" role="group">
                        <a href="{$serverUri}/views/editItems.jsp?searchObject=equipment&amp;id={id}"
                           class="btn btn-secondary">Изменить
                        </a>
                        <button type="button" class="btn btn-danger" onclick="showAlertCommitDeletion()">Удалить</button>
                    </div>
                    <div class="alert alert-warning text-center alert-commit-deletion collapse" role="alert" id="alert-delete-equipment">
                        <strong>Удалить?</strong> Вы действительно хотите удалить данную экипировку?
                        <hr/>
                        <button type="button" class="btn btn-danger" onclick="removeEquipment({id})">Удалить</button>
                    </div>
                </xsl:if>>
            </div>
            <div class="alert alert-success text-center collapse alert-successfully-deleted" role="alert">
                <p>Успешно удалено!</p>
            </div>
            <div class="alert alert-error alert-server-error collapse">
                <h4>ERROR</h4>
                <p>Внутренняя ошибка сервера</p>
            </div>
        </div>
    </xsl:template>

    <xsl:template match="/personage">
        <div class="container text-center show-item">
            <div class="view-pane">
                <table class="table table-inverse">
                    <thead><tr><th></th><th>Персонаж</th></tr></thead>
                    <tr>
                        <td>Имя персонажа</td>
                        <td><xsl:value-of select="nickname" /></td>
                    </tr>
                    <tr>
                        <td>Уровень</td>
                        <td><xsl:value-of select="level" /></td>
                    </tr>
                    <tr>
                        <td>Расса</td>
                        <td><xsl:value-of select="race" /></td>
                    </tr>
                    <tr>
                        <td>Сила</td>
                        <td><xsl:value-of select="power" /></td>
                    </tr>
                    <tr>
                        <td>Паррирование</td>
                        <td><xsl:value-of select="parry" /></td>
                    </tr>
                    <tr>
                        <td>Защита</td>
                        <td><xsl:value-of select="protection" /></td>
                    </tr>
                    <tr>
                        <td>Скорость</td>
                        <td><xsl:value-of select="speed" /></td>
                    </tr>
                    <tr>
                        <td>Экипировка</td>
                        <td id="personage_equipment" onclick="findEquipmentAsXml({equipment/id})"><xsl:value-of select="equipment/name"/></td>
                    </tr>
                    <tr>
                        <td>Оружие</td>
                        <td id="personage_weapon" onclick="findWeaponAsXml({weapon/id})"><xsl:value-of select="weapon/name"/></td>
                    </tr>
                </table>
                <div class="btn-group" role="group">
                    <a href="{$serverUri}/views/editItems.jsp?searchObject=personage&amp;id={id}"
                       class="btn btn-secondary">Изменить
                    </a>
                    <button type="button" class="btn btn-danger" onclick="showAlertCommitDeletion()">Удалить</button>
                </div>
                <div class="alert alert-warning text-center alert-commit-deletion collapse" role="alert">
                    <strong>Удалить?</strong> Вы действительно хотите удалить данного персонажа?
                    <hr/>
                    <button type="button" class="btn btn-danger" onclick="removePersonage({id})">Удалить</button>
                </div>
            </div>
            <div class="alert alert-success text-center collapse alert-successfully-deleted" role="alert">
                <p>Успешно удалено!</p>
            </div>
            <div class="alert alert-error alert-server-error collapse">
                <h4>ERROR</h4>
                <p>Внутренняя ошибка сервера</p>
            </div>
        </div>
    </xsl:template>

    <xsl:template match="/weapons">
        <div class="container text-center show-item">
            <div class="view-pane">
                <table class="table table-inverse">
                    <thead><tr><th>Название оружия</th><th>Уровень</th><th></th></tr></thead>
                    <xsl:for-each select="item">
                        <tr>
                            <td><xsl:value-of select="name" /></td>
                            <td><xsl:value-of select="level" /></td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-outline-info" onclick="findWeaponAsXml({id})">Посмотреть</button>
                                    <xsl:if test="id != 2">
                                        <button type="button" class="btn btn-outline-danger" onclick="removeWeapon({id})">Удалить</button>
                                    </xsl:if>
                                </div>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </div>
        </div>
    </xsl:template>

    <xsl:template match="/equipments">
        <div class="container text-center show-item">
            <div class="view-pane">
                <table class="table table-inverse">
                    <thead><tr><th>Название экипировки</th><th>Уровень</th><th></th></tr></thead>
                    <xsl:for-each select="item">
                        <tr>
                            <td><xsl:value-of select="name" /></td>
                            <td><xsl:value-of select="level" /></td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-outline-info" onclick="findEquipmentAsXml({id})">Посмотреть</button>
                                    <xsl:if test="id != 7">
                                        <button type="button" class="btn btn-outline-danger" onclick="removeEquipment({id})">Удалить</button>
                                    </xsl:if>
                                </div>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </div>
        </div>
    </xsl:template>

    <xsl:template match="/personages">
        <div class="container text-center show-item">
            <div class="view-pane">
                <table class="table table-inverse">
                    <thead><tr><th>Имя персонажа</th><th>Уровень</th><th></th></tr></thead>
                    <xsl:for-each select="item">
                        <tr>
                            <td><xsl:value-of select="nickname" /></td>
                            <td><xsl:value-of select="level" /></td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-outline-info" onclick="findPersonageAsXml({id})">Посмотреть</button>
                                    <button type="button" class="btn btn-outline-danger" onclick="removePersonage({id})">Удалить</button>
                                </div>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </div>
        </div>
    </xsl:template>

</xsl:stylesheet>