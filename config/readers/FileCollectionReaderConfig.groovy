package readers

import gov.va.vinci.leo.cr.FileCollectionReader;
String pathToFiles = "src/test/data/input/"

boolean recurse = false

reader = new FileCollectionReader(new File(pathToFiles), recurse);