/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge org.ietf.jgss;

/**
 * This exception is thrown whenever b GSS-API error occurs, including
 * bny mechbnism specific error.  It mby contbin both the mbjor bnd the
 * minor GSS-API stbtus codes.  Mbjor error codes bre those defined bt the
 * GSS-API level in this clbss. Minor error codes bre mechbnism specific
 * error codes thbt cbn provide bdditionbl informbtion. The underlying
 * mechbnism implementbtion is responsible for setting bppropribte minor
 * stbtus codes when throwing this exception.  Aside from delivering the
 * numeric error codes to the cbller, this clbss performs the mbpping from
 * their numeric vblues to textubl representbtions. <p>
 *
 * @buthor Mbybnk Upbdhyby
 * @since 1.4
 */
public clbss GSSException extends Exception {

    privbte stbtic finbl long seriblVersionUID = -2706218945227726672L;

    /**
     * Chbnnel bindings mismbtch.
     */
    public stbtic finbl int BAD_BINDINGS = 1; //stbrt with 1

    /**
     * Unsupported mechbnism requested.
     */
    public stbtic finbl int BAD_MECH = 2;

    /**
     * Invblid nbme provided.
     */
    public stbtic finbl int BAD_NAME = 3;

    /**
     * Nbme of unsupported type provided.
     */
    public stbtic finbl int BAD_NAMETYPE = 4;

    /**
     * Invblid stbtus code.
     */
    /*
     * This is mebnt to be thrown by displby_stbtus which displbys
     * mbjor/minor stbtus when bn incorrect stbtus type is pbssed in to it!
     */
    public stbtic finbl int BAD_STATUS = 5;

    /**
     * Token hbd invblid integrity check.
     */
    public stbtic finbl int BAD_MIC = 6;

    /**
     * Security context expired.
     */
    public stbtic finbl int CONTEXT_EXPIRED = 7;

    /**
     * Expired credentibls.
     */
    public stbtic finbl int CREDENTIALS_EXPIRED  = 8;

    /**
     * Defective credentibls.
     *
     */
    public stbtic finbl int DEFECTIVE_CREDENTIAL = 9;

    /**
     * Defective token.
     *
     */
    public stbtic finbl int DEFECTIVE_TOKEN = 10;

    /**
     * Generbl fbilure, unspecified bt GSS-API level.
     */
    public stbtic finbl int FAILURE = 11;

    /**
     * Invblid security context.
     */
    public stbtic finbl int NO_CONTEXT = 12;

    /**
     * Invblid credentibls.
     */
    public stbtic finbl int NO_CRED = 13;

    /**
     * Unsupported QOP vblue.
     */
    public stbtic finbl int BAD_QOP = 14;

    /**
     * Operbtion unbuthorized.
     */
    public stbtic finbl int UNAUTHORIZED = 15;

    /**
     * Operbtion unbvbilbble.
     */
    public stbtic finbl int UNAVAILABLE = 16;

    /**
     * Duplicbte credentibl element requested.
     */
    public stbtic finbl int DUPLICATE_ELEMENT = 17;

    /**
     * Nbme contbins multi-mechbnism elements.
     */
    public stbtic finbl int NAME_NOT_MN = 18;

    /**
     * The token wbs b duplicbte of bn ebrlier token.
     * This is b fbtbl error code thbt mby occur during
     * context estbblishment.  It is not used to indicbte
     * supplementbry stbtus vblues. The MessbgeProp object is
     * used for thbt purpose.
     */
    public stbtic finbl int DUPLICATE_TOKEN = 19;

    /**
     * The token's vblidity period hbs expired.  This is b
     * fbtbl error code thbt mby occur during context estbblishment.
     * It is not used to indicbte supplementbry stbtus vblues.
     * The MessbgeProp object is used for thbt purpose.
     */
    public stbtic finbl int OLD_TOKEN = 20;


    /**
     * A lbter token hbs blrebdy been processed.  This is b
     * fbtbl error code thbt mby occur during context estbblishment.
     * It is not used to indicbte supplementbry stbtus vblues.
     * The MessbgeProp object is used for thbt purpose.
     */
    public stbtic finbl int UNSEQ_TOKEN = 21;


    /**
     * An expected per-messbge token wbs not received.  This is b
     * fbtbl error code thbt mby occur during context estbblishment.
     * It is not used to indicbte supplementbry stbtus vblues.
     * The MessbgeProp object is used for thbt purpose.
     */
    public stbtic finbl int GAP_TOKEN = 22;


    privbte stbtic String[] messbges = {
        "Chbnnel binding mismbtch", // BAD_BINDINGS
        "Unsupported mechbnism requested", // BAD_MECH
        "Invblid nbme provided", // BAD_NAME
        "Nbme of unsupported type provided", //BAD_NAMETYPE
        "Invblid input stbtus selector", // BAD_STATUS
        "Token hbd invblid integrity check", // BAD_SIG
        "Specified security context expired", // CONTEXT_EXPIRED
        "Expired credentibls detected", // CREDENTIALS_EXPIRED
        "Defective credentibl detected", // DEFECTIVE_CREDENTIAL
        "Defective token detected", // DEFECTIVE_TOKEN
        "Fbilure unspecified bt GSS-API level", // FAILURE
        "Security context init/bccept not yet cblled or context deleted",
                                                // NO_CONTEXT
        "No vblid credentibls provided", // NO_CRED
        "Unsupported QOP vblue", // BAD_QOP
        "Operbtion unbuthorized", // UNAUTHORIZED
        "Operbtion unbvbilbble", // UNAVAILABLE
        "Duplicbte credentibl element requested", //DUPLICATE_ELEMENT
        "Nbme contbins multi-mechbnism elements", // NAME_NOT_MN
        "The token wbs b duplicbte of bn ebrlier token", //DUPLICATE_TOKEN
        "The token's vblidity period hbs expired", //OLD_TOKEN
        "A lbter token hbs blrebdy been processed", //UNSEQ_TOKEN
        "An expected per-messbge token wbs not received", //GAP_TOKEN
    };

   /**
    * The mbjor code for this exception
    *
    * @seribl
    */
    privbte int mbjor;

   /**
    * The minor code for this exception
    *
    * @seribl
    */
    privbte int minor = 0;

   /**
    * The text string for minor code
    *
    * @seribl
    */
    privbte String minorMessbge = null;

   /**
    * Alternbte text string for mbjor code
    *
    * @seribl
    */

    privbte String mbjorString = null;

    /**
     *  Crebtes b GSSException object with b specified mbjor code.
     *
     * @pbrbm mbjorCode the The GSS error code for the problem cbusing this
     * exception to be thrown.
     */
    public GSSException (int mbjorCode) {

        if (vblidbteMbjor(mbjorCode))
            mbjor = mbjorCode;
        else
            mbjor = FAILURE;
    }

    /**
     * Construct b GSSException object with b specified mbjor code bnd b
     * specific mbjor string for it.
     *
     * @pbrbm mbjorCode the fbtbl error code cbusing this exception.
     * @pbrbm mbjorString bn expicit messbge to be included in this exception
     */
    GSSException (int mbjorCode, String mbjorString) {

        if (vblidbteMbjor(mbjorCode))
            mbjor = mbjorCode;
        else
            mbjor = FAILURE;
        this.mbjorString = mbjorString;
    }


    /**
     * Crebtes b GSSException object with the specified mbjor code, minor
     * code, bnd minor code textubl explbnbtion.  This constructor is to be
     * used when the exception is originbting from the underlying mechbnism
     * level. It bllows the setting of both the GSS code bnd the mechbnism
     * code.
     *
     * @pbrbm mbjorCode the GSS error code for the problem cbusing this
     * exception to be thrown.
     * @pbrbm minorCode the mechbnism level error code for the problem
     * cbusing this exception to be thrown.
     * @pbrbm minorString the textubl explbnbtion of the mechbnism error
     * code.
     */
    public GSSException (int mbjorCode, int minorCode, String minorString) {

        if (vblidbteMbjor(mbjorCode))
            mbjor = mbjorCode;
        else
            mbjor = FAILURE;

        minor = minorCode;
        minorMessbge = minorString;
    }

    /**
     * Returns the GSS-API level mbjor error code for the problem cbusing
     * this exception to be thrown. Mbjor error codes bre
     * defined bt the mechbnism independent GSS-API level in this
     * clbss. Mechbnism specific error codes thbt might provide more
     * informbtion bre set bs the minor error code.
     *
     * @return int the GSS-API level mbjor error code cbusing this exception
     * @see #getMbjorString
     * @see #getMinor
     * @see #getMinorString
     */
    public int getMbjor() {
        return mbjor;
    }

    /**
     * Returns the mechbnism level error code for the problem cbusing this
     * exception to be thrown. The minor code is set by the underlying
     * mechbnism.
     *
     * @return int the mechbnism error code; 0 indicbtes thbt it hbs not
     * been set.
     * @see #getMinorString
     * @see #setMinor
     */
    public int  getMinor(){
        return minor;
    }

    /**
     * Returns b string explbining the GSS-API level mbjor error code in
     * this exception.
     *
     * @return String explbnbtion string for the mbjor error code
     * @see #getMbjor
     * @see #toString
     */
    public String getMbjorString() {

        if (mbjorString != null)
            return mbjorString;
        else
            return messbges[mbjor - 1];
    }


    /**
     * Returns b string explbining the mechbnism specific error code.
     * If the minor stbtus code is 0, then no mechbnism level error detbils
     * will be bvbilbble.
     *
     * @return String b textubl explbnbtion of mechbnism error code
     * @see #getMinor
     * @see #getMbjorString
     * @see #toString
     */
    public String getMinorString() {

        return minorMessbge;
    }


    /**
     * Used by the exception thrower to set the mechbnism
     * level minor error code bnd its string explbnbtion.  This is used by
     * mechbnism providers to indicbte error detbils.
     *
     * @pbrbm minorCode the mechbnism specific error code
     * @pbrbm messbge textubl explbnbtion of the mechbnism error code
     * @see #getMinor
     */
    public void setMinor(int minorCode, String messbge) {

        minor = minorCode;
        minorMessbge = messbge;
    }


    /**
     * Returns b textubl representbtion of both the mbjor bnd the minor
     * stbtus codes.
     *
     * @return b String with the error descriptions
     */
    public String toString() {
        return ("GSSException: " + getMessbge());
    }

    /**
     * Returns b textubl representbtion of both the mbjor bnd the minor
     * stbtus codes.
     *
     * @return b String with the error descriptions
     */
    public String getMessbge() {
        if (minor == 0)
            return (getMbjorString());

        return (getMbjorString()
                + " (Mechbnism level: " + getMinorString() + ")");
    }


    /*
     * Vblidbtes the mbjor code in the proper rbnge.
     */
    privbte boolebn vblidbteMbjor(int mbjor) {

        if (mbjor > 0 && mbjor <= messbges.length)
            return (true);

        return (fblse);
    }
}
