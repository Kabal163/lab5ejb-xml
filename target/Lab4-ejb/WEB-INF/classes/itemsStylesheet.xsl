<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/weapon">
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
            </div>
        </div>
    </xsl:template>

</xsl:stylesheet>