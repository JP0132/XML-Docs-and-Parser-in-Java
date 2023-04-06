<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<style>
                   h1 {
                     text-align: center;
                   }
                   table {
                     margin-left: auto;
                     margin-right: auto;
                   }
                </style>
			</head>
			<body>
				<h1>RESTInterface</h1>
				<table border="1">
					<tr bgcolor="#FFFF00">
						<th style="text-align:center">Operation</th>
						<th style="text-align:center">Argument(s)</th>
						<th style="text-align:center">Return</th>
						<th style="text-align:center">Exception</th>
					</tr>
					<xsl:for-each select="interface/abstract_method">
						<tr>
							<td>
								<xsl:value-of select="@name"></xsl:value-of>
							</td>
							<td>
								<xsl:for-each select="parameters/argument">
									<xsl:value-of select="current()"></xsl:value-of>
									<xsl:text>: </xsl:text>
									<xsl:value-of select="@type"></xsl:value-of>
									<xsl:if test="position() != last()">
										<xsl:text>, </xsl:text>
									</xsl:if>
								</xsl:for-each>
							</td>
							<td>
								<xsl:value-of select="return"></xsl:value-of>
							</td>
							<td>
								<xsl:choose>
									<xsl:when test="throws">Yes</xsl:when>
									<xsl:otherwise>No</xsl:otherwise>
								</xsl:choose>
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>