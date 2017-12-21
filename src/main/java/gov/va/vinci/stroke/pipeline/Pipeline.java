package gov.va.vinci.stroke.pipeline;


import gov.va.vinci.leo.annotationpattern.ae.AnnotationPatternAnnotator;
import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;
import gov.va.vinci.leo.filter.ae.FilterAnnotator;
import gov.va.vinci.leo.regex.ae.RegexAnnotator;
import gov.va.vinci.leo.window.ae.WindowAnnotator;
import gov.va.vinci.leo.window.types.Window;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.impl.TypeDescription_impl;

import java.util.ArrayList;
import java.util.HashMap;

public class Pipeline extends BasePipeline {
    String regexParentType = "gov.va.vinci.leo.regex.types.RegularExpressionType";
    String apaParentType = "gov.va.vinci.leo.annotationpattern.types.AnnotationPatternType";

    /**
     * Constructor
     *
     * @throws Exception
     */
    public Pipeline() {

    }

    public Pipeline(HashMap args) throws Exception {
    /*
     * List for holding our annotators. This list, and the order of the list
     * created the annotator pipeline.
     */
        pipeline = new LeoAEDescriptor();

        // First, run regex on the document.

        pipeline.addDelegate(new RegexAnnotator()
                .setGroovyConfigFile("src/main/resources/Score_regex.groovy")
                .getLeoAEDescriptor()
                .setName("RegexAnnotator")
                .setTypeSystemDescription(getLeoTypeSystemDescription()));

        pipeline.addDelegate(new AnnotationPatternAnnotator()
                .setResource("src/main/resources/Exclude.pattern")
                .setOutputType("gov.va.vinci.stroke.types.ExcludePattern")
                .getLeoAEDescriptor()
                .setName("ExcludeAnnotator")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));

        /**
         * Remove extra annotations
         */

        pipeline.addDelegate(new FilterAnnotator()
                .setTypesToKeep(new String[]{"gov.va.vinci.stroke.types.Score",
                        "gov.va.vinci.stroke.types.Score_score",
                        "gov.va.vinci.stroke.types.Exclude",
                        "gov.va.vinci.stroke.types.Score_word",
                        "gov.va.vinci.stroke.types.ExcludePattern"})
                .getLeoAEDescriptor().setName("AnnotationFilter")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));
        pipeline.addDelegate(new FilterAnnotator()
                .setTypesToKeep(new String[]{"gov.va.vinci.stroke.types.ExcludePattern"})
                .setTypesToDelete(new String[]{"gov.va.vinci.stroke.types.Score",
                        "gov.va.vinci.stroke.types.Score_score",
                        "gov.va.vinci.stroke.types.Exclude",
                        "gov.va.vinci.stroke.types.Score_word"})
                .setRemoveOverlapping(true)
                .getLeoAEDescriptor().setName("AnnotationFilter")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));

        /**
         * Second, add windows around the regexes. The window annotation will
         * extend 1 word (defined as separated by a whitespace character before
         * and after each character string.
         */
        pipeline.addDelegate(new WindowAnnotator()
                .setAnchorFeature("Anchor")
                .setWindowSize(5)
                .setOutputType(Window.class.getCanonicalName())
                .setInputTypes("gov.va.vinci.stroke.types.Score")
                .getLeoAEDescriptor().setName("AnnotationFilter")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));

        pipeline.addDelegate(new AnnotationPatternAnnotator()
                .setResource("src/main/resources/Score_Pattern.pattern")
                .setOutputType("gov.va.vinci.stroke.types.ScorePattern")
                .getLeoAEDescriptor()
                .setName("Score_Pattern")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));
        pipeline.addDelegate(new FilterAnnotator()
                .setTypesToKeep(new String[]{"gov.va.vinci.stroke.types.ScorePattern"})
                .getLeoAEDescriptor()
                .setName("Score_Pattern_filter")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));


    }

    /**
     *
     */
    @Override
    public LeoTypeSystemDescription defineTypeSystem() {

        if (description == null) {
            try {
                description = new WindowAnnotator().getLeoTypeSystemDescription();
                description.addTypeSystemDescription(new RegexAnnotator().getLeoTypeSystemDescription());
                description.addType("gov.va.vinci.stroke.types.Score", "", regexParentType);
                description.addType("gov.va.vinci.stroke.types.Score_score", "", regexParentType);
                description.addType("gov.va.vinci.stroke.types.Exclude", "", regexParentType);
                description.addType("gov.va.vinci.stroke.types.Score_word", "", regexParentType);
                description.addTypeSystemDescription(new AnnotationPatternAnnotator().getLeoTypeSystemDescription());
                description.addType("gov.va.vinci.stroke.types.ExcludePattern", "", apaParentType);
                description.addType("gov.va.vinci.stroke.types.ScorePattern", "", apaParentType);

                TypeDescription finalType = new TypeDescription_impl("gov.va.vinci.stroke.types.Logic", "", "uima.tcas.Annotation");
                finalType.addFeature("Score_Value", "", "uima.cas.String");
                description.addType(finalType);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return description;
    }


}
