package readers

import gov.va.vinci.leo.cr.BatchDatabaseCollectionReader;
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://vhacdwrb02:1433;databasename=MAVIN_ComputeLib;integratedSecurity=true"
String username="";
String password="";
String query = "select distinct  a.TIUDocumentSID, ReportText FROM [MAVIN_ComputeLib].[NLP_Stroke].[stroke_nlp_docs_in_window] a join [CDWwork].[TIU].[TIUDocument_8925] b  on a.tiudocumentsid=b.TIUDocumentSID"

int startingIndex = 0;
int endingIndex = 10;
int batch_size = 10000;

reader = new BatchDatabaseCollectionReader(
        driver,
        url,
        username,
        password,
        query,
        "tiudocumentsid","reporttext",   /// Make sure that the names of the fields are low case.
        startingIndex , endingIndex
        , batch_size)