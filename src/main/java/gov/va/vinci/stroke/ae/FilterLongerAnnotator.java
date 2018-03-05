package gov.va.vinci.stroke.ae;

import gov.va.vinci.leo.AnnotationLibrarian;
import gov.va.vinci.leo.ae.LeoBaseAnnotator;
import gov.va.vinci.leo.descriptors.LeoConfigurationParameter;
import gov.va.vinci.leo.tools.LeoUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.*;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import java.util.ArrayList;

/**

 */
public class FilterLongerAnnotator extends LeoBaseAnnotator {

    /**
     * Log messages
     */
    private static final Logger log = Logger.getLogger(LeoUtils.getRuntimeClass());

    @LeoConfigurationParameter(mandatory = true, description = "The list of annotation types to keep")
    private String[] typesToKeep = null;

    @LeoConfigurationParameter(description = "The list of annotation types to delete. If not specified, remove only annotations of the first type")
    private String[] typesToDelete = null;

    @LeoConfigurationParameter(description = "The list of features to match")
    private String[] featuresToMatch = null;

    @LeoConfigurationParameter(description = "Set true to remove overlapping. Otherwise, remove completely covered only")
    private boolean removeOverlapping = false;

    @LeoConfigurationParameter(description = "Set true to keep these types and their subtypes")
    private boolean keepChildren = false;

    @LeoConfigurationParameter(description = "Set true to delete these types and their subtypes")
    private boolean deleteChildren = false;

    /**
     * Initialize the annotator
     *
     * @param aContext UIMA context
     */
    @Override
    public void initialize(UimaContext aContext) throws ResourceInitializationException {
        super.initialize(aContext);

        // If both keepChildren and deleteChildren are true and in the same class hierarchy, throw exception. This is a rare case and as
        // such is not implemented
        if (isKeepChildren() && isDeleteChildren()) {
            for (String keep : getTypesToKeep()) {
                for (String delete : getTypesToDelete()) {
                    try {
                        if (Class.forName(keep).isAssignableFrom(Class.forName(delete))) {
                            throw new ResourceInitializationException();
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        log.info("Cannot initialize/find " + keep + " or " + delete);
                    }
                }
            }
        }
    }

    /**
     * Filtering process
     *
     * @param aJCas CAS object
     * @throws AnalysisEngineProcessException Exception
     */
    @Override
    public void annotate(JCas aJCas) throws AnalysisEngineProcessException {
        ArrayList<Annotation> toDelete = new ArrayList();
        if (getTypesToDelete() == null) {

            for (String type : getTypesToKeep()) {

                Type typeObj = null;
                try {
                    typeObj = aJCas.getRequiredType(type);
                } catch (CASException e) {
                    throw new AnalysisEngineProcessException(e);
                }
                ArrayList<Annotation> annotations = (ArrayList<Annotation>) AnnotationLibrarian.getAllAnnotationsOfType(aJCas, typeObj, keepChildren);

                for (int current = 0; current < annotations.size(); current++) {
                    Annotation ca = annotations.get(current);
                    for (int next = current + 1; next < annotations.size(); next++) {
                        Annotation na = annotations.get(next);
                        if (AnnotationLibrarian.completelyCovers(ca, na)) {
                            if (!AnnotationLibrarian.completelyCovers(na, ca)) {
                                toDelete.add(ca);
                            }
                        }
                    }//for
                }
            }
        }
        for (Annotation ca : toDelete) {
            ca.removeFromIndexes();
        }
    }

    /**
     * The annotation types that need to be retained
     *
     * @return types to keep
     */
    public String[] getTypesToKeep() {
        return typesToKeep;
    }

    public FilterLongerAnnotator setTypesToKeep(String[] typesToKeep) {
        this.typesToKeep = typesToKeep;
        return this;
    }

    /**
     * The annotation types that need to be removed
     *
     * @return types to delete
     */
    public String[] getTypesToDelete() {
        return typesToDelete;
    }

    public FilterLongerAnnotator setTypesToDelete(String[] typesToDelete) {
        this.typesToDelete = typesToDelete;
        return this;
    }

    /**
     * The base names of the features to be matched.
     *
     * @return features to match
     */
    public String[] getFeaturesToMatch() {
        return featuresToMatch;
    }

    public FilterLongerAnnotator setFeaturesToMatch(String[] featuresToMatch) {
        this.featuresToMatch = featuresToMatch;
        return this;
    }

    /**
     * Boolean flag to indicate whether to remove overlapping annotations
     *
     * @return define whether to remove overlapping annotations
     */
    public boolean isRemoveOverlapping() {
        return removeOverlapping;
    }

    public FilterLongerAnnotator setRemoveOverlapping(boolean removeOverlapping) {
        this.removeOverlapping = removeOverlapping;
        return this;
    }

    /**
     * Boolean flag to indicate whether to consider only the unique typesToKeep (i.e., no subtypes)
     *
     * @return flag to define whether to keep sub classes
     */
    public boolean isKeepChildren() {
        return keepChildren;
    }

    public FilterLongerAnnotator setKeepChildren(boolean keepChildren) {
        this.keepChildren = keepChildren;
        return this;
    }

    /**
     * Boolean flag to indicate whether to consider only the unique typesToDelete (i.e., no subtypes)
     *
     * @return flag to define whether to delete sub classes
     */
    public boolean isDeleteChildren() {
        return deleteChildren;
    }

    public FilterLongerAnnotator setDeleteChildren(boolean deleteChildren) {
        this.deleteChildren = deleteChildren;
        return this;
    }
}
