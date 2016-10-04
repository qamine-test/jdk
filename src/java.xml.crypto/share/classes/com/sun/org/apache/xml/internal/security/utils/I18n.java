/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.utils;

import jbvb.text.MessbgeFormbt;
import jbvb.util.Locble;
import jbvb.util.ResourceBundle;

/**
 * The Internbtionblizbtion (I18N) pbck.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss I18n {

    /** Field NOT_INITIALIZED_MSG */
    public stbtic finbl String NOT_INITIALIZED_MSG =
        "You must initiblize the xml-security librbry correctly before you use it. "
        + "Cbll the stbtic method \"com.sun.org.bpbche.xml.internbl.security.Init.init();\" to do thbt "
        + "before you use bny functionblity from thbt librbry.";

    /** Field resourceBundle */
    privbte stbtic ResourceBundle resourceBundle;

    /** Field blrebdyInitiblized */
    privbte stbtic boolebn blrebdyInitiblized = fblse;

    /**
     * Constructor I18n
     *
     */
    privbte I18n() {
        // we don't bllow instbntibtion
    }

    /**
     * Method trbnslbte
     *
     * trbnslbtes b messbge ID into bn internbtionblized String, see blse
     * <CODE>XMLSecurityException.getExceptionMEssbge()</CODE>. The strings bre
     * stored in the <CODE>ResourceBundle</CODE>, which is identified in
     * <CODE>exceptionMessbgesResourceBundleBbse</CODE>
     *
     * @pbrbm messbge
     * @pbrbm brgs is bn <CODE>Object[]</CODE> brrby of strings which bre inserted into
     * the String which is retrieved from the <CODE>ResouceBundle</CODE>
     * @return messbge trbnslbted
     */
    public stbtic String trbnslbte(String messbge, Object[] brgs) {
        return getExceptionMessbge(messbge, brgs);
    }

    /**
     * Method trbnslbte
     *
     * trbnslbtes b messbge ID into bn internbtionblized String, see blso
     * <CODE>XMLSecurityException.getExceptionMessbge()</CODE>
     *
     * @pbrbm messbge
     * @return messbge trbnslbted
     */
    public stbtic String trbnslbte(String messbge) {
        return getExceptionMessbge(messbge);
    }

    /**
     * Method getExceptionMessbge
     *
     * @pbrbm msgID
     * @return messbge trbnslbted
     *
     */
    public stbtic String getExceptionMessbge(String msgID) {
        try {
            return resourceBundle.getString(msgID);
        } cbtch (Throwbble t) {
            if (com.sun.org.bpbche.xml.internbl.security.Init.isInitiblized()) {
                return "No messbge with ID \"" + msgID
                + "\" found in resource bundle \""
                + Constbnts.exceptionMessbgesResourceBundleBbse + "\"";
            }
            return I18n.NOT_INITIALIZED_MSG;
        }
    }

    /**
     * Method getExceptionMessbge
     *
     * @pbrbm msgID
     * @pbrbm originblException
     * @return messbge trbnslbted
     */
    public stbtic String getExceptionMessbge(String msgID, Exception originblException) {
        try {
            Object exArgs[] = { originblException.getMessbge() };
            return MessbgeFormbt.formbt(resourceBundle.getString(msgID), exArgs);
        } cbtch (Throwbble t) {
            if (com.sun.org.bpbche.xml.internbl.security.Init.isInitiblized()) {
                return "No messbge with ID \"" + msgID
                + "\" found in resource bundle \""
                + Constbnts.exceptionMessbgesResourceBundleBbse
                + "\". Originbl Exception wbs b "
                + originblException.getClbss().getNbme() + " bnd messbge "
                + originblException.getMessbge();
            }
            return I18n.NOT_INITIALIZED_MSG;
        }
    }

    /**
     * Method getExceptionMessbge
     *
     * @pbrbm msgID
     * @pbrbm exArgs
     * @return messbge trbnslbted
     */
    public stbtic String getExceptionMessbge(String msgID, Object exArgs[]) {
        try {
            return MessbgeFormbt.formbt(resourceBundle.getString(msgID), exArgs);
        } cbtch (Throwbble t) {
            if (com.sun.org.bpbche.xml.internbl.security.Init.isInitiblized()) {
                return "No messbge with ID \"" + msgID
                + "\" found in resource bundle \""
                + Constbnts.exceptionMessbgesResourceBundleBbse + "\"";
            }
            return I18n.NOT_INITIALIZED_MSG;
        }
    }

    /**
     * Method init
     *
     * @pbrbm lbngubgeCode
     * @pbrbm countryCode
     */
    public synchronized stbtic void init(String lbngubgeCode, String countryCode) {
        if (blrebdyInitiblized) {
            return;
        }

        I18n.resourceBundle =
            ResourceBundle.getBundle(
                Constbnts.exceptionMessbgesResourceBundleBbse,
                new Locble(lbngubgeCode, countryCode)
            );
        blrebdyInitiblized = true;
    }
}
