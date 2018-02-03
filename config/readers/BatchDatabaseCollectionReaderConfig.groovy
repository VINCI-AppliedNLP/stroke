package readers

import gov.va.vinci.leo.cr.BatchDatabaseCollectionReader;
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://vhacdwrb02:1433;databasename=MAVIN_ComputeLib;integratedSecurity=true"
String username="";
String password="";
String query ='''  SELECT distinct d.[TIUDocumentSID] , [ReportText], ' ' PatientSID
  FROM [MAVIN_ComputeLib].[NLP_Stroke].[enrichedPtSet_1_PtDoc_Sets] d
  join [CDW_TIU].[TIU].[TIUDocument_8925_02] t with(nolock) on d.TIUDocumentSID=t.TIUDocumentSID
  where d.tiudocumentsid in (select top 300 TIUDocumentSID from  (select  distinct tiudocumentsid
  FROM [MAVIN_ComputeLib].[NLP_Stroke].[ScoreOut_20180201] ) n order by newid())
  '''
/**
  where SetID = 1 
  and RowNo > {min} and RowNo < {max}
'''
  **/      //"select distinct  a.TIUDocumentSID, ReportText FROM [MAVIN_ComputeLib].[NLP_Stroke].[stroke_nlp_docs_in_window] a join [CDWwork].[TIU].[TIUDocument_8925] b  on a.tiudocumentsid=b.TIUDocumentSID"

int startingIndex = 0;
int endingIndex = 10 // 211970;  // Client finished in: 1:58:10.325.
int batch_size = 20000;

reader = new BatchDatabaseCollectionReader(
        driver,
        url,
        username,
        password,
        query,
        "tiudocumentsid","reporttext",   /// Make sure that the names of the fields are low case.
        startingIndex , endingIndex
        , batch_size)


/**
 SetID	Min_RowIndex	Max_RowIndex	PateintCount	DocumentCount
 1	     1	211969	1500	211969
 2	211970	414325	1500	202356
 3	414326	614976	1500	200651
 4	614977	1032889	2653	417913

 **/