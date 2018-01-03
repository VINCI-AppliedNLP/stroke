package listeners

import gov.va.vinci.leo.listener.SimpleXmiListener

String xmiDir = "O:\\MAVIN_ComputeLib\\NLP_Stroke\\Test\\NLP_Processing\\xmi"

if(!(new File(xmiDir)).exists()) (new File(xmiDir)).mkdirs()

listener = new SimpleXmiListener(new File(xmiDir))
listener.setLaunchAnnotationViewer(false)  // if true, a Viewer will be displayed after processing

//listener.setAnnotationTypeFilter("gov.va.vinci.ef.types.Relation") // This is optional. If this line is commented out, all documents will be written out.