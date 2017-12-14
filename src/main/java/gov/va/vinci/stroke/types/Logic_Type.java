
/* First created by JCasGen Thu Dec 14 13:16:25 MST 2017 */
package gov.va.vinci.stroke.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Dec 14 13:16:25 MST 2017
 * @generated */
public class Logic_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Logic_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Logic_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Logic(addr, Logic_Type.this);
  			   Logic_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Logic(addr, Logic_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Logic.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("gov.va.vinci.stroke.types.Logic");
 
  /** @generated */
  final Feature casFeat_Score_Value;
  /** @generated */
  final int     casFeatCode_Score_Value;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getScore_Value(int addr) {
        if (featOkTst && casFeat_Score_Value == null)
      jcas.throwFeatMissing("Score_Value", "gov.va.vinci.stroke.types.Logic");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Score_Value);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setScore_Value(int addr, String v) {
        if (featOkTst && casFeat_Score_Value == null)
      jcas.throwFeatMissing("Score_Value", "gov.va.vinci.stroke.types.Logic");
    ll_cas.ll_setStringValue(addr, casFeatCode_Score_Value, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Logic_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Score_Value = jcas.getRequiredFeatureDE(casType, "Score_Value", "uima.cas.String", featOkTst);
    casFeatCode_Score_Value  = (null == casFeat_Score_Value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Score_Value).getCode();

  }
}



    