package readers

import gov.va.vinci.leo.cr.BatchDatabaseCollectionReader;

String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
String url = "jdbc:sqlserver://vhacdwrb02:1433;databasename=MAVIN_ComputeLib;integratedSecurity=true"
String username = "";
String password = "";

String query = ''' SELECT distinct d.[TIUDocumentSID], [ReportText] FROM [MAVIN_ComputeLib].[NLP_Stroke].[enrichedPtSet_1_PtDoc_Sets] d
join [CDW_TIU].[TIU].[TIUDocument_8925_02] t with(nolock) on d.TIUDocumentSID = t.TIUDocumentSID
where  RowNo > {min} and RowNo < {max}
'''
/*'''SELECT top 100 a.TIUDocumentSID, ReportText, b.PatientSID
FROM (select distinct TIUDocumentSID from [MAVIN_ComputeLib].[NLP_Stroke].[enrichedPtSet_1_SetId1_2_StrokeScore_20180304] ) a
join [MAVIN_ComputeLib].[NLP_Stroke].[enrichedPtSet_1_PtDoc_Sets] b   on a.tiudocumentsid=b.tiudocumentsid
join [CDWWork].[TIU].[TIUDocument_8925] c on c.tiudocumentsid=a.tiudocumentsid
where setid='2'
order by newid()
'''
/* '''SELECT distinct d.TIUDocumentSID, ReportText
FROM [MAVIN_ComputeLib].[NLP_Stroke].[enrichedPtSet_1_PtDoc_Sets] d
join [CDW_TIU].[TIU].[TIUDocument_8925_02] t with(nolock) on d.TIUDocumentSID = t.TIUDocumentSID
where SetID < 3 and rowno>{min} and rowno<{max} ;
'''
/**'''     select d.TIUDocumentSID, ReportText
from (
select distinct b.tiudocumentsid
FROM [MAVIN_ComputeLib].[NLP_Stroke].[Temp_ScoreOut_20180304] a
full join [MAVIN_ComputeLib].[NLP_Stroke].[ScoreOut_20180201] b
on a.TIUDocumentsid=b.tiudocumentsid
and ( a.spanend=b.spanend or (a.spanstart - b.spanstart > -5 and a.spanstart - b.spanstart < 15) )
where a.TIUDocumentSID is null ) d
join [CDW_TIU].[TIU].[TIUDocument_8925_02] t with(nolock) on d.TIUDocumentSID = t.TIUDocumentSID
'''
/**
''' select d.TIUDocumentSID, ReportText
FROM (select distinct TIUDocumentSID from [MAVIN_ComputeLib].[NLP_Stroke].[ScoreOut_20180201] ) d
join [CDW_TIU].[TIU].[TIUDocument_8925_02] t with(nolock) on d.TIUDocumentSID = t.TIUDocumentSID
'''

/**
''' select d.TIUDocumentSID, ReportText
from (
select distinct b.tiudocumentsid
FROM [MAVIN_ComputeLib].[NLP_Stroke].[ScoreOut_Set1_20180228] a
full join [MAVIN_ComputeLib].[NLP_Stroke].[ScoreOut_20180201] b
on a.TIUDocumentsid=b.tiudocumentsid and a.spanend=b.spanend
where a.TIUDocumentSID is null ) d
join [CDW_TIU].[TIU].[TIUDocument_8925_02] t with(nolock) on d.TIUDocumentSID = t.TIUDocumentSID
'''
/**''' SELECT distinct d.[TIUDocumentSID], [ReportText] FROM [MAVIN_ComputeLib].[NLP_Stroke].[enrichedPtSet_1_PtDoc_Sets] d
join [CDW_TIU].[TIU].[TIUDocument_8925_02] t with(nolock) on d.TIUDocumentSID = t.TIUDocumentSID
where setid = 1 and RowNo > {min} and RowNo < {max} ;

'''

/**
'''SELECT distinct d.[TIUDocumentSID] , [ReportText],   PatientSID
FROM [MAVIN_ComputeLib].[NLP_Stroke].[enrichedPtSet_2_PtDoc_Sets] d
join [CDW_TIU].[TIU].[TIUDocument_8925_02] t with(nolock) on d.TIUDocumentSID=t.TIUDocumentSID
where d.tiudocumentsid in ( select  distinct tiudocumentsid
FROM [MAVIN_ComputeLib].[NLP_Stroke].[ScoreOut_20180227] )
'''
/**

''' SELECT distinct d.[TIUDocumentSID] , [ReportText]
FROM [MAVIN_ComputeLib].[NLP_Stroke].[enrichedPtSet_2_PtDoc_Sets] d
join [CDW_TIU].[TIU].[TIUDocument_8925_02] t with(nolock) on d.TIUDocumentSID=t.TIUDocumentSID
where  setid =0
and RowNo > {min} and RowNo < {max}

'''
/**''' select distinct d.TIUDocumentSID, ReportText
FROM [MAVIN_ComputeLib].[NLP_validation].[Validation_tiu_StrokeScore_20180202]d
join [CDW_TIU].[TIU].[TIUDocument_8925_02] t with(nolock) on d.TIUDocumentSID=t.TIUDocumentSID

''';
/**
*
*  where ValidationValue not like 'Correct' ;
*
'''SELECT distinct d.[TIUDocumentSID] , [ReportText], ' ' PatientSID
FROM [MAVIN_ComputeLib].[NLP_Stroke].[stroke_nlp_docs_in_window] d
join [CDW_TIU].[TIU].[TIUDocument_8925_02] t with(nolock) on d.TIUDocumentSID=t.TIUDocumentSID
'''
/**
'''  SELECT distinct d.[TIUDocumentSID] , [ReportText], ' ' PatientSID
FROM [MAVIN_ComputeLib].[NLP_Stroke].[enrichedPtSet_1_PtDoc_Sets] d
join [CDW_TIU].[TIU].[TIUDocument_8925_02] t with(nolock) on d.TIUDocumentSID=t.TIUDocumentSID
where d.tiudocumentsid in (select top 300 TIUDocumentSID from  (select  distinct tiudocumentsid
FROM [MAVIN_ComputeLib].[NLP_Stroke].[ScoreOut_20180201] ) n order by newid())
'''
 /**/
/**
 where SetID = 1
 and RowNo > {min} and RowNo < {max}'''
 **/      //"select distinct  a.TIUDocumentSID, ReportText FROM [MAVIN_ComputeLib].[NLP_Stroke].[stroke_nlp_docs_in_window] a join [CDWwork].[TIU].[TIUDocument_8925] b  on a.tiudocumentsid=b.TIUDocumentSID"
client_batch =
        // services 7 with 10 pipelines each
        // [0, 100000]      // Mar 16, 2018 7:50:06 PM  CAS Count: 99999  Client finished in: 0:33:19.500.
        // [100000, 200000] // Mar 16, 2018 7:52:36 PM  CAS Count: 100000 Client finished in: 0:29:41.693.
        // [200000, 300000] // Mar 16, 2018 7:54:13 PM  CAS Count: 100000 Client finished in: 0:28:35.752.
        // [300000, 400000] // Mar 16, 2018 7:57:34 PM  CAS Count: 100000 Client finished in: 0:30:03.611.
        // [400000, 600000] // Mar 16, 2018 7:59:11 PM  CAS Count: 200000 Client finished in: 1:41:29.905.
        // [600000, 800000] // Mar 16, 2018 8:01:25 PM  CAS Count: 200000 Client finished in: 1:41:40.924
        [800000, 1033000] //Mar 16, 2018 8:06:55 PM CAS Count: 232890 - Client finished in: 2:13:31.347

int startingIndex = 0;
int endingIndex = 1033000;  // All sets: 1,032,889 // Set 1+2 count: 414326 // 211970 Client finished in: 1:58:10.325.  190447  Client finished in: 0:00:20.108.
int batch_size = 20000;

reader = new BatchDatabaseCollectionReader(
        driver,
        url,
        username,
        password,
        query,
        "tiudocumentsid", "reporttext",   /// Make sure that the names of the fields are low case.
        client_batch[0], client_batch[1]
        , batch_size)

/**
 SetID	Min_RowIndex	Max_RowIndex	PateintCount	DocumentCount
 1	     1	211969	1500	211969
 2	211970	414325	1500	202356
 3	414326	614976	1500	200651
 4	614977	1032889	2653	417913

 **/
