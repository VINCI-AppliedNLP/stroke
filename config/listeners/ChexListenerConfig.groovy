package listeners

/**
 * Created by vhaslcalbap on 1/20/2017.
 */
import gov.va.vinci.leo.tools.LeoUtils
import gov.va.vinci.stroke.listeners.ChexListener


String timeStamp = LeoUtils.getTimestampDateDotTime().replaceAll("[.]", "_")


String chexSchema = "NLP_validation"  //
String chexSuffix = "_tiu_StrokeScore_" + timeStamp.substring(0, 8) // Change the suffix for each run, otherwise the data WILL BE OVERWRITTEN!
def chexTypes= [
        "gov.va.vinci.stroke.types.ScorePattern" ] // when blank, SimanListener outputs all annotations
boolean chexOverwrite = true
String chexDocumentTextSelectQuery = ""
String chexColumnPrefix = "["
String chexColumnSuffix ="]"
int batchSize = 1000
String url = "jdbc:sqlserver://vhacdwrb02:1433;databasename=MAVIN_ComputeLib;integratedSecurity=true"
String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"



listener = ChexListener.newChexListener(
        driver,
        url,
        chexDocumentTextSelectQuery,
        chexSchema,
        chexSuffix,
        chexColumnPrefix,
        chexColumnSuffix,
        chexTypes,
        batchSize,
        chexOverwrite)
