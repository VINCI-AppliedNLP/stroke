import gov.va.vinci.leo.aucompare.listener.AuCompareCSVListener
import gov.va.vinci.leo.aucompare.comparators.SpanAuComparator
import gov.va.vinci.leo.tools.LeoUtils;
HashMap auMap = [ "gov.va.vinci.kttr.types.NIH_Stroke_Score":"gov.va.vinci.stroke.types.ScorePattern"
]

String timeStamp = LeoUtils.getTimestampDateDotTime().replaceAll("[.]", "_")
String outPath =  "O:\\MAVIN_ComputeLib\\NLP_Stroke\\Test\\NLP_Processing\\Au_ScoreCompare.csv"

listener = new AuCompareCSVListener(new File (outPath), new SpanAuComparator(auMap, true));