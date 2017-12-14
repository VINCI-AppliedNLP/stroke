package gov.va.vinci.stroke.ae;

import java.io.File;
import java.io.FileOutputStream;
import java.util.prefs.Preferences;

import gov.va.vinci.stroke.pipeline.Pipeline;
import org.apache.commons.lang3.StringUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.jcas.JCas;
import org.apache.uima.tools.AnnotationViewerMain;
import org.apache.uima.util.FileUtils;
import org.junit.Before;
import org.junit.Test;

import gov.va.vinci.leo.descriptors.LeoAEDescriptor;
import gov.va.vinci.leo.descriptors.LeoTypeSystemDescription;

public class ExampleTest {
    protected LeoAEDescriptor aggregate = null;
    protected LeoAEDescriptor aggLearning = null;
    protected LeoTypeSystemDescription types = null;
    protected String inputDir = "src/test/data/input/";
    protected String outputDir = "src/test/data/output/";
    protected boolean launchView = true;

    @Before
    public void setUp() throws Exception {
    }

    @Before
    public void setup() throws Exception {
        Pipeline bs = new Pipeline();
        types = bs.getLeoTypeSystemDescription();
        aggregate = bs.getPipeline();
        File o = new File(outputDir);
        if (!o.exists()) {
            o.mkdirs();
        }//if
    }//setup

    @Test
    public void testXmi() throws Exception {
        String types = "";
        String docText = "";
        AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(aggregate.getAnalysisEngineDescription());
        String filename = "example1.txt";
        String filePath = this.inputDir + filename;

        try {
            docText = FileUtils.file2String(new File(filePath));
        } catch (Exception e) {
            System.out.println("Missing file!!");
        }
        if (StringUtils.isBlank(docText)) {
            System.out.println("Blank file!!");
        }

        JCas jcas = ae.newJCas();
        jcas.setDocumentText(docText);
        ae.process(jcas);
        try {
            File xmio = new File(outputDir, filename + ".xmi");
            XmiCasSerializer.serialize(jcas.getCas(), new FileOutputStream(xmio));
        } catch (Exception e) {

        }

        if (launchView) launchViewer();
        System.out.println(types);
        if (launchView) System.in.read();
    }//testXmi method

    protected void launchViewer() throws Exception {
        if (aggregate == null) {
            throw new RuntimeException("Aggregate is null, unable to generate descriptor for viewing xmi");
        }
        aggregate.toXML(outputDir);
        String aggLoc = aggregate.getDescriptorLocator().substring(5);
        Preferences prefs = Preferences.userRoot().node("org/apache/uima/tools/AnnotationViewer");
        if (aggLoc != null) {
            prefs.put("taeDescriptorFile", aggLoc);
        }//if mAggDesc != null
        if (outputDir != null) {
            prefs.put("inDir", outputDir);
        }//if mOutputDir != null
        AnnotationViewerMain avm = new AnnotationViewerMain();
        avm.setBounds(0, 0, 1000, 225);
        avm.setVisible(true);
    }//launchViewer method

    /**
     * @After
     */
    public void cleanup() throws Exception {
        File o = new File(outputDir);
        if (o.exists()) {
            FileUtils.deleteRecursive(o);
        }//if
    }//cleanup method
}//EchoTest class
