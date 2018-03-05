## stroke

Information extraction system for stroke score

NIH_Stroke_Score LTP:103   LFP:82 LFN:  23 LPrecision: 0.5568 LRecall: 0.8175 LF1 0.6624

## 2018-02-01
Score correction
LTP:         110        LFP:          11        LFN:          16 LPrecision:      0.9091    LRecall:      0.8730         LF1      0.8907

Starting to process test set to select precision validation instances.

Query
    SELECT  d. [TIUDocumentSID] ,[ReportText]
    FROM [MAVIN_ComputeLib].[NLP_Stroke].[enrichedPtSet_1_PtDoc_Sets] d
    join [CDW_TIU].[TIU].[TIUDocument_8925_02] t with(nolock) on d.TIUDocumentSID=t.TIUDocumentSID
    where SetID = 1
    and RowNo > -1 and RowNo < 20000

## 2008-02-10
Chex validation  _tiu_StrokeScore_20180202
300 files, 383 values
Precision = 322/383 = 0.84  - (321 by annotator but  1 was erroneously marked incorrect)



gov.va.vinci.kttr.types.NIH_Stroke_Score -> gov.va.vinci.stroke.types.ScorePattern
         TP:          77         FP:          43         FN:          49  Precision:      0.6417     Recall:      0.6111         F1:      0.6260
        LTP:         109        LFP:          11        LFN:          17 LPrecision:      0.9083    LRecall:      0.8651         LF1      0.8862

