package listeners

import gov.va.vinci.stroke.listeners.BasicDatabaseListener


int batchSize = 1000
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://vhacdwrb02:1433;databasename=MAVIN_ComputeLib;integratedSecurity=true"
String dbUser="";
String dbPwd="";

String dbsName = "MAVIN_ComputeLib"
String tableName = "[NLP_Stroke].[RefSt_ScoreOut_20180315]"
incomingTypes = "gov.va.vinci.stroke.types.ScorePattern"

fieldList = [
        ["TIUDocumentSID", "0", "varchar(20)"],
        ["DocID", "-1", "varchar(20)"],
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
        "gov.va.vinci.stroke.types.ScorePattern",
"gov.va.vinci.kttr.types.NIH_Stroke_Score")



boolean dropExisting = true;
// Comment out the statement below if you want to add to the existing table
listener.createTable(dropExisting);

