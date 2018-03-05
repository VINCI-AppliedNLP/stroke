package gov.va.vinci.stroke.ae;

import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoConfigurationParameter;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

/**

 */
public class LogicAnnotator extends LeoBaseAnnotator {

    public String getAnnotation_feature_name() {
        return annotation_feature_name;
    }

    public LogicAnnotator setAnnotation_feature_name(String annotation_feature_name) {
        this.annotation_feature_name = annotation_feature_name;
        return this;
    }

    public String getString_feature_name() {
        return string_feature_name;
    }

    public LogicAnnotator setString_feature_name(String string_feature_name) {
        this.string_feature_name = string_feature_name;
        return this;
    }



    @LeoConfigurationParameter(mandatory = true, description = "The list of annotation feature")
    String annotation_feature_name = "anchor";

    @LeoConfigurationParameter(mandatory = true, description = "The list of string feature")
    String string_feature_name = "anchorString";




    public void annotate(JCas aJCas) throws AnalysisEngineProcessException {

        for (String type_name : inputTypes) {
            FSIterator<Annotation> iter = this.getAnnotationListForType(aJCas, type_name);

            while (iter.hasNext()) {
                String featureCoveredText = "";
                Annotation i = iter.next();
                Type type = i.getType();
                Feature featureAnn = type.getFeatureByBaseName(annotation_feature_name);
                Feature featureStr = type.getFeatureByBaseName(string_feature_name);
                FeatureStructure fs = i.getFeatureValue(featureAnn);
                if (fs == null) {
                    continue;
                }
                if (fs instanceof Annotation) {
                    featureCoveredText = ((Annotation)i.getFeatureValue(featureAnn)).getCoveredText();
                    i.setFeatureValueFromString(featureStr, featureCoveredText ) ;
                }

            }
        }
    }

}
