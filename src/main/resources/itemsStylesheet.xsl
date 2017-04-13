<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:param name="serverUri" select="'serverUri'"/>


    <xsl:template match="/weapon">
        <xsl:variable name="id" select="id"/>
        <xsl:variable name="uri" select="$serverUri"/>

        <div class="container text-center show-item">
            <div class="view-pane">
                <table class="table table-inverse">
                    <thead><tr><th></th><th>Оружие</th></tr></thead>
                    <tr>
                        <td>Название оружия</td>
                        <td><xsl:value-of select="name"/></td>
                    </tr>
                    <tr>
                        <td>Уровень</td>
                        <td><xsl:value-of select="level"/></td>
                    </tr>
                    <tr>
                        <td>К урону</td>
                        <td><xsl:value-of select="power"/></td>
                    </tr>
                    <tr>
                        <td>К паррированию</td>
                        <td><xsl:value-of select="parry"/></td>
                    </tr>
                    <tr>
                        <td>Цена</td>
                        <td><xsl:value-of select="price"/></td>
                    </tr>
                </table>
                <xsl:if test="id != 2">
                    <div class="btn-group" role="group">
                        <a href="{uri}/views/editItems.jsp?searchObject=weapon&amp;id={id}" class="btn btn-secondary">Изменить
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
                <p>Успешно удалено!
                    <button type="button" class="btn btn-secondary" onclick="">Восстановить</button>
                </p>
            </div>
            <div class="alert alert-error alert-server-error collapse">
                <h4>ERROR</h4>
                <p>Внутренняя ошибка сервера</p>
            </div>
        </div>
    </xsl:template>

</xsl:stylesheet>