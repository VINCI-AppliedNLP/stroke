package gov.va.vinci.stroke.listeners;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.tcas.Annotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vhaslcpatteo on 3/24/2015.
 */
public class ListenerLogic {


  private static Logger log = Logger.getLogger(gov.va.vinci.leo.tools.LeoUtils.getRuntimeClass().toString());

  public static HashMap<String, String> getInstanceData(JCas aJCas, Annotation currAnnotation) throws CASException {

    // output to csv
    HashMap<String, String> currentDoc = new HashMap<String, String>();
/**/
    getFeatures(currAnnotation, currentDoc);
    
    currentDoc.put("AnnotationType", currAnnotation.getClass().getSimpleName());
    currentDoc.put("Snippets", shortenText(currAnnotation.getCoveredText(), 400, false));
    currentDoc.put("SpanStart", Integer.toString(currAnnotation.getBegin()));
    currentDoc.put("SpanEnd", Integer.toString(currAnnotation.getEnd()));


/**/
    return currentDoc;
  }

  public static String shortenText(String text, int maxSize, boolean stripNewLines) {
    int l = text.length();
    if (l > maxSize) l = maxSize;

    if (stripNewLines)
      return text.substring(0, l).replaceAll("\\s+", " ").replaceAll("\\s+", " ");
    else
      return text.substring(0, l);
  }

  public static void getFeatures(Annotation annotation, HashMap<String, String> currentDoc) {

    // Recurse & insert features.
    Type type = annotation.getType();
    List<Feature> featuresList = type.getFeatures();
    for (int i = 0; i < featuresList.size(); ++i) {
      Feature feature = (Feature) featuresList.get(i);
      String nameFeat = feature.getName();

      if (nameFeat.startsWith("uima.")) {
        continue;
      }

      Type typeRange = feature.getRange();

      if (typeRange.isPrimitive()) {

        currentDoc.put(feature.getShortName(),
            annotation.getFeatureValueAsString(feature));

      } else {
        // This is a Feature Structure, recurse through it.
        FeatureStructure fs = annotation.getFeatureValue(feature);

        if (fs == null) {
          continue;
        }

        /** See if this is a reference to another annotation. If so, add it. **/
        if (fs instanceof Annotation) {
          currentDoc.put(feature.getShortName(), ((Annotation)annotation.getFeatureValue(feature)).getCoveredText());
         // log.debug("");

          continue;
        } else if (fs instanceof StringArray) {
          //TODO: sort the array alphabetically?
          int counter = 0;
          String out = "";
          if (((StringArray) fs).toArray().length > 0) {

            if (((StringArray) fs).toArray().length > 1) {
              for (String value : ((StringArray) fs).toArray()) {
                if (StringUtils.isBlank(out))
                  out = "|" + counter + ":" + value + "|";
                else
                  out = out + counter + ":" + value + "|";
                counter++;
              }
              currentDoc.put(feature.getShortName(), out);
              continue;
            } else {
              String value = ((StringArray) fs).toArray()[0];
              currentDoc.put(feature.getShortName(), value);
              continue;
            }
          }
        } else if (fs instanceof FSArray) {
          FeatureStructure[] fsArray = ((FSArray) fs).toArray();

          int counter = 0;
          for (FeatureStructure item : fsArray) {
            // TODO Implement, especially on recursive features.

          }
          continue;
        } else {
          throw new RuntimeException(
              "Unknown Feature Structure Type:"
                  + fs.getType().getName());
        }
      }
    }
  }

  public static Object[] convertFromMapToArray(HashMap<String, String> instanceData, ArrayList<ArrayList<String>> fieldList) {
    ArrayList rowData = new ArrayList<String>();
    for (ArrayList<String> headerInfo : fieldList) {
      if (org.apache.commons.lang3.StringUtils.isNotBlank((instanceData.get(headerInfo.get(0))))) {
        rowData.add(instanceData.get(headerInfo.get(0)));
      } else {
        rowData.add(null);
      }
    }
    return rowData.toArray(new Object[rowData.size()]);

  }

  public static Object[] convertFromMapToObjectArray(HashMap<String, String> instanceData, String[] fieldList) {
    ArrayList rowData = new ArrayList<String>();
    for (String headerInfo : fieldList) {
      if (org.apache.commons.lang3.StringUtils.isNotBlank((instanceData.get(headerInfo)))) {
        rowData.add(instanceData.get(headerInfo));
      } else {
        rowData.add(null);
      }
    }
    return rowData.toArray(new Object[rowData.size()]);

  }

  public static String[] convertFromMapToStringArray(HashMap<String, String> instanceData, String[] fieldList) {
    ArrayList<String> rowData = new ArrayList<String>();
    for (String headerInfo : fieldList) {
      if (org.apache.commons.lang3.StringUtils.isNotBlank((instanceData.get(headerInfo)))) {
        rowData.add(instanceData.get(headerInfo));
      } else {
        rowData.add(null);
      }
    }
    return rowData.toArray(new String[rowData.size()]);

  }

}
