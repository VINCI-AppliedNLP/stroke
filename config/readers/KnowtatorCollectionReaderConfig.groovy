package readers

import gov.va.vinci.knowtator.cr.KnowtatorCollectionReader
import gov.va.vinci.knowtator.model.KnowtatorToUimaTypeMap;

def knowtatorToUimaTypeMap = { ->
    KnowtatorToUimaTypeMap map = new KnowtatorToUimaTypeMap();
    // map.addAnnotationTypeMap(String knowtatorType, String uimaType)
    map.addAnnotationTypeMap("Stroke_Score", "gov.va.vinci.kttr.types.NIH_Stroke_Score")
    map.addAnnotationTypeMap("NIH_Stroke_Score", "gov.va.vinci.kttr.types.NIH_Stroke_Score")
    map.addAnnotationTypeMap("Symptom_Term", "gov.va.vinci.kttr.types.Symptom_Term")
    map.addAnnotationTypeMap("Stroke_Term", "gov.va.vinci.kttr.types.Stroke_Term")
    map.addAnnotationTypeMap("TIA_Term", "gov.va.vinci.kttr.types.TIA_Term")
    // .................
    // map.addFeatureTypeMap(String knowtatorType, String knowtatorFeatureName, String uimaFeatureName)
    // ..................
    return map
}

knowtatorCorpusPath = "O:\\MAVIN_ComputeLib\\NLP_Stroke\\FinalValidation_Set1_20180314\\200_Stroke_Set1_enriched\\corpus\\"
knowtatorXmlPath = "O:\\MAVIN_ComputeLib\\NLP_Stroke\\FinalValidation_Set1_20180314\\200_Stroke_Set1_enriched\\saved\\"

//knowtatorCorpusPath = "O:\\MAVIN_ComputeLib\\NLP_Stroke\\Test\\TestEHost\\Completed_Annotation\\corpus\\"
//knowtatorXmlPath = "O:\\MAVIN_ComputeLib\\NLP_Stroke\\Test\\TestEHost\\Completed_Annotation\\saved\\"

reader = new KnowtatorCollectionReader(new File(knowtatorCorpusPath), new File(knowtatorXmlPath),
        knowtatorToUimaTypeMap(), true).produceCollectionReader()
