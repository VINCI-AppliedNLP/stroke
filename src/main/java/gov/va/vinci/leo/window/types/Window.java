

/* First created by JCasGen Wed Feb 18 10:55:16 CST 2015 */
package gov.va.vinci.leo.window.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** Window Type
 * Updated by JCasGen Wed Feb 18 10:55:16 CST 2015
 * XML source: H:/AppData/Local/Temp/leoTypeDescription_f053cd4c-5680-4123-8b32-8148371d82485383450208180565647.xml
 * @generated */
public class Window extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Window.class);
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
  protected Window() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Window(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Window(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Window(JCas jcas, int begin, int end) {
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
  //* Feature: Anchor

  /** getter for Anchor - gets Anchor annotation around which the window was created
   * @generated
   * @return value of the feature 
   */
  public Annotation getAnchor() {
    if (Window_Type.featOkTst && ((Window_Type)jcasType).casFeat_Anchor == null)
      jcasType.jcas.throwFeatMissing("Anchor", "gov.va.vinci.leo.window.types.Window");
    return (Annotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Window_Type)jcasType).casFeatCode_Anchor)));}
    
  /** setter for Anchor - sets Anchor annotation around which the window was created 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAnchor(Annotation v) {
    if (Window_Type.featOkTst && ((Window_Type)jcasType).casFeat_Anchor == null)
      jcasType.jcas.throwFeatMissing("Anchor", "gov.va.vinci.leo.window.types.Window");
    jcasType.ll_cas.ll_setRefValue(addr, ((Window_Type)jcasType).casFeatCode_Anchor, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    