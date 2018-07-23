package listeners

import gov.va.vinci.leo.tools.LeoUtils
import gov.va.vinci.stroke.listeners.BasicDatabaseListener


int batchSize = 1000
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://vhacdwrb02:1433;databasename=MAVIN_ComputeLib;integratedSecurity=true"
String dbUser="";
String dbPwd="";

String dbsName = "MAVIN_ComputeLib"
String timeStamp = LeoUtils.getTimestampDateDotTime().replaceAll("[.]", "_")
String tableName = "[NLP_Stroke].[" + timeStamp.substring(0, 8) + "_Stroke_Score_Full_Cohort]"

incomingTypes = "gov.va.vinci.stroke.types.ScorePattern"

fieldList = [
        ["TIUDocumentSID", "0", "varchar(20)"],
        ["anchor", "-1", "varchar(50)"],
        ["Snippets", "-1", "varchar(500)"],
        ["SpanStart", "-1", "int"],
        ["SpanEnd", "-1", "int"]
]

listener = BasicDatabaseListener.createNewListener(
        driver,
        url,
        dbUser,
        dbPwd,
        dbsName,
        tableName,
        batchSize,
        fieldList,
        incomingTypes)


boolean dropExisting = false;
// Comment out the statement below if you want to add to the existing table
listener.createTable(dropExisting);

