import gov.va.vinci.leo.aucompare.listener.AuSummaryListener
import gov.va.vinci.leo.aucompare.comparators.SpanAuComparator;

HashMap auMap = [ "gov.va.vinci.kttr.types.NIH_Stroke_Score":"gov.va.vinci.stroke.types.ScorePattern"
]

listener = new AuSummaryListener(new SpanAuComparator(auMap, true));