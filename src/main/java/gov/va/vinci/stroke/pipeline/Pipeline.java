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
    public Pipeline(){

    }

    public Pipeline(HashMap args) throws Exception {
        /*
		 * List for holding our annotators. This list, and the order of the list
		 * created the annotator pipeline.
		 */
        pipeline = new LeoAEDescriptor();

        // First, run regex on the document.

        pipeline.addDelegate(new RegexAnnotator()
                .setGroovyConfigFile( "src/main/resources/CCS_regex.groovy")
                .getLeoAEDescriptor()
                .setName("RegexAnnotator")
                .setTypeSystemDescription(getLeoTypeSystemDescription()));

        pipeline.addDelegate(new AnnotationPatternAnnotator()
                .setResource("src/main/resources/Exclude.pattern")
                .setOutputType("gov.va.vinci.angina.types.ExcludePattern")
                .getLeoAEDescriptor()
                .setName("ExcludeAnnotator")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));

        /**
         * Remove extra annotations
         */

        pipeline.addDelegate(new FilterAnnotator()
                .setTypesToKeep(new String[]{"gov.va.vinci.angina.types.CCS",
                                "gov.va.vinci.angina.types.CCS_score",
                                "gov.va.vinci.angina.types.Exclude",
                                "gov.va.vinci.angina.types.CCS_word",
                                "gov.va.vinci.angina.types.ExcludePattern"})
                .getLeoAEDescriptor().setName("AnnotationFilter")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));
        pipeline.addDelegate(new FilterAnnotator()
                .setTypesToKeep(new String[]{"gov.va.vinci.angina.types.ExcludePattern"})
                .setTypesToDelete( new String[]{"gov.va.vinci.angina.types.CCS",
                                "gov.va.vinci.angina.types.CCS_score",
                                "gov.va.vinci.angina.types.Exclude",
                                "gov.va.vinci.angina.types.CCS_word"})
                .setRemoveOverlapping(true)
                .getLeoAEDescriptor().setName("AnnotationFilter")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));

        /**
         * Second, add windows around the regexes. The window annotation will
         * extend 1 word (defined as separated by a whitespace character before
         * and after each character string.
         */
        pipeline.addDelegate(new WindowAnnotator()
                .setAnchorFeature(  "Anchor")
                .setWindowSize(5)
                .setOutputType( Window.class.getCanonicalName())
                .setInputTypes( "gov.va.vinci.angina.types.CCS")
                .getLeoAEDescriptor().setName("AnnotationFilter")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));

        pipeline.addDelegate(new AnnotationPatternAnnotator()
                .setResource("src/main/resources/CCS_Pattern.pattern")
                .setOutputType("gov.va.vinci.angina.types.CCSPattern")
                .getLeoAEDescriptor()
                .setName("CCS_Pattern")
                .addTypeSystemDescription(getLeoTypeSystemDescription()));




    }

    @Override
    protected LeoTypeSystemDescription defineTypeSystem() {
        return null;
    }

    /**
     *
     */
    @Override
    public LeoTypeSystemDescription getLeoTypeSystemDescription() {

        if (description == null) {
            try {
                description = new WindowAnnotator().getLeoTypeSystemDescription();
                description.addTypeSystemDescription(new RegexAnnotator().getLeoTypeSystemDescription());
                description.addType("gov.va.vinci.angina.types.CCS", "", regexParentType);
                description.addType("gov.va.vinci.angina.types.CCS_score", "", regexParentType);
                description.addType("gov.va.vinci.angina.types.Exclude", "", regexParentType);
                description.addType("gov.va.vinci.angina.types.CCS_word", "", regexParentType);
                description.addTypeSystemDescription(new AnnotationPatternAnnotator().getLeoTypeSystemDescription());
                description.addType("gov.va.vinci.angina.types.ExcludePattern", "", apaParentType);
                description.addType("gov.va.vinci.angina.types.CCSPattern", "", apaParentType);

                TypeDescription finalType = new TypeDescription_impl("gov.va.vinci.angina.types.Logic", "", "uima.tcas.Annotation");
                finalType.addFeature("CCS_Value", "", "uima.cas.String");
                description.addType(finalType);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return description;
    }
}
