

/* First created by JCasGen Tue Jan 02 17:41:49 CST 2018 */
package gov.va.vinci.stroke.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Jan 02 17:41:49 CST 2018
 * XML source: C:/Users/VHASLC~1/AppData/Local/Temp/3/leoTypeDescription_fb56143a-294c-486f-b290-e45919313d247090472557553284325.xml
 * @generated */
public class Logic extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Logic.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Logic() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Logic(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Logic(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Logic(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: Score_Value

  /** getter for Score_Value - gets 
   * @generated
   * @return value of the feature 
   */
  public String getScore_Value() {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_Score_Value == null)
      jcasType.jcas.throwFeatMissing("Score_Value", "gov.va.vinci.stroke.types.Logic");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Logic_Type)jcasType).casFeatCode_Score_Value);}
    
  /** setter for Score_Value - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setScore_Value(String v) {
    if (Logic_Type.featOkTst && ((Logic_Type)jcasType).casFeat_Score_Value == null)
      jcasType.jcas.throwFeatMissing("Score_Value", "gov.va.vinci.stroke.types.Logic");
    jcasType.ll_cas.ll_setStringValue(addr, ((Logic_Type)jcasType).casFeatCode_Score_Value, v);}    
  }

    