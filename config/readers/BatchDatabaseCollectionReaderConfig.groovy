package readers

import gov.va.vinci.leo.cr.BatchDatabaseCollectionReader;

String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://vhacdwrb02:1433;databasename=MAVIN_ComputeLib;integratedSecurity=true"
String username = "";
String password = "";

String query = "SELECT a.TIUDOCUMENTSID, b.Reporttext, A.PATIENTSID, a.sta3n, a.referencedatetime, rowid   FROM [MAVIN_ComputeLib].[temp].[allstroke_forNLPvalidation_DocSet_WithIdentifiers]   a  join [cdwwork].[TIU].[TIUDocument_8925] b with(nolock)on a.tiudocumentsid=b.tiudocumentsid where rowid >{min} and rowid <{max}";

String docIDColumnName = "rowid"
String docTextColumnName = "ReportText"
//Max =   1,032,889
int startingIndex = 0;
int endingIndex =50830;
int batchSize = 20000;


reader = new BatchDatabaseCollectionReader(dbDriver, url ,
        "", "",
        query,
        docIDColumnName.toLowerCase(),
        docTextColumnName.toLowerCase(),
        startingIndex, endingIndex,
        batchSize);
/**
 SetID	Min_RowIndex	Max_RowIndex	PateintCount	DocumentCount
 1	     1	211969	1500	211969
 2	211970	414325	1500	202356
 3	414326	614976	1500	200651
 4	614977	1032889	2653	417913

 **/
