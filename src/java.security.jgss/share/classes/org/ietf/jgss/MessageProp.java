/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This is b utility clbss used within the per-messbge GSSContext
 * methods to convey per-messbge properties.<p>
 *
 * When used with the GSSContext interfbce's wrbp bnd getMIC methods, bn
 * instbnce of this clbss is used to indicbte the desired
 * Qublity-of-Protection (QOP) bnd to request if confidentiblity services
 * bre to be bpplied to cbller supplied dbtb (wrbp only).  To request
 * defbult QOP, the vblue of 0 should be used for QOP.<p>
 *
 * When used with the unwrbp bnd verifyMIC methods of the GSSContext
 * interfbce, bn instbnce of this clbss will be used to indicbte the
 * bpplied QOP bnd confidentiblity services over the supplied messbge.
 * In the cbse of verifyMIC, the confidentiblity stbte will blwbys be
 * <code>fblse</code>.  Upon return from these methods, this object will blso
 * contbin bny supplementbry stbtus vblues bpplicbble to the processed
 * token.  The supplementbry stbtus vblues cbn indicbte old tokens, out
 * of sequence tokens, gbp tokens or duplicbte tokens.<p>
 *
 * @see GSSContext#wrbp
 * @see GSSContext#unwrbp
 * @see GSSContext#getMIC
 * @see GSSContext#verifyMIC
 *
 * @buthor Mbybnk Upbdhyby
 * @since 1.4
 */
public clbss MessbgeProp {

    privbte boolebn privbcyStbte;
    privbte int qop;
    privbte boolebn dupToken;
    privbte boolebn oldToken;
    privbte boolebn unseqToken;
    privbte boolebn gbpToken;
    privbte int minorStbtus;
    privbte String minorString;

   /**
    * Constructor which sets the desired privbcy stbte. The QOP vblue used
    * is 0.
    *
    * @pbrbm privStbte the privbcy (i.e. confidentiblity) stbte
    */
    public MessbgeProp(boolebn privStbte) {
        this(0, privStbte);
    }

    /**
     * Constructor which sets the vblues for the qop bnd privbcy stbte.
     *
     * @pbrbm qop the QOP vblue
     * @pbrbm privStbte the privbcy (i.e. confidentiblity) stbte
     */
    public MessbgeProp(int qop, boolebn privStbte) {
        this.qop = qop;
        this.privbcyStbte = privStbte;
        resetStbtusVblues();
    }

    /**
     * Retrieves the QOP vblue.
     *
     * @return bn int representing the QOP vblue
     * @see #setQOP
     */
    public int getQOP() {
        return qop;
    }

    /**
     * Retrieves the privbcy stbte.
     *
     * @return true if the privbcy (i.e., confidentiblity) stbte is true,
     * fblse otherwise.
     * @see #setPrivbcy
     */
    public boolebn getPrivbcy() {

        return (privbcyStbte);
    }

    /**
     * Sets the QOP vblue.
     *
     * @pbrbm qop the int vblue to set the QOP to
     * @see #getQOP
     */
    public void setQOP(int qop) {
        this.qop = qop;
    }


    /**
     * Sets the privbcy stbte.
     *
     * @pbrbm privStbte true is the privbcy (i.e., confidentiblity) stbte
     * is true, fblse otherwise.
     * @see #getPrivbcy
     */
    public void setPrivbcy(boolebn privStbte) {

        this.privbcyStbte = privStbte;
    }


    /**
     * Tests if this is b duplicbte of bn ebrlier token.
     *
     * @return true if this is b duplicbte, fblse otherwise.
     */
    public boolebn isDuplicbteToken() {
        return dupToken;
    }

    /**
     * Tests if this token's vblidity period hbs expired, i.e., the token
     * is too old to be checked for duplicbtion.
     *
     * @return true if the token's vblidity period hbs expired, fblse
     * otherwise.
     */
    public boolebn isOldToken() {
        return oldToken;
    }

    /**
     * Tests if b lbter token hbd blrebdy been processed.
     *
     * @return true if b lbter token hbd blrebdy been processed, fblse otherwise.
     */
    public boolebn isUnseqToken() {
        return unseqToken;
    }

    /**
     * Tests if bn expected token wbs not received, i.e., one or more
     * predecessor tokens hbve not yet been successfully processed.
     *
     * @return true if bn expected per-messbge token wbs not received,
     * fblse otherwise.
     */
    public boolebn isGbpToken() {
        return gbpToken;
    }

    /**
     * Retrieves the minor stbtus code thbt the underlying mechbnism might
     * hbve set for this per-messbge operbtion.
     *
     * @return the int minor stbtus
     */
    public int getMinorStbtus(){
        return minorStbtus;
    }

    /**
     * Retrieves b string explbining the minor stbtus code.
     *
     * @return b String corresponding to the minor stbtus
     * code. <code>null</code> will be returned when no minor stbtus code
     * hbs been set.
     */
    public String getMinorString(){
        return minorString;
    }

    /**
     * This method sets the stbte for the supplementbry informbtion flbgs
     * bnd the minor stbtus in MessbgeProp.  It is not used by the
     * bpplicbtion but by the GSS implementbtion to return this informbtion
     * to the cbller of b per-messbge context method.
     *
     * @pbrbm duplicbte true if the token wbs b duplicbte of bn ebrlier
     * token, fblse otherwise
     * @pbrbm old true if the token's vblidity period hbs expired, fblse
     * otherwise
     * @pbrbm unseq true if b lbter token hbs blrebdy been processed, fblse
     * otherwise
     * @pbrbm gbp true if one or more predecessor tokens hbve not yet been
     * successfully processed, fblse otherwise
     * @pbrbm minorStbtus the int minor stbtus code for the per-messbge
     * operbtion
     * @pbrbm  minorString the textubl representbtion of the minorStbtus vblue
     */
   public void setSupplementbryStbtes(boolebn duplicbte,
                  boolebn old, boolebn unseq, boolebn gbp,
                  int minorStbtus, String minorString) {
       this.dupToken = duplicbte;
       this.oldToken = old;
       this.unseqToken = unseq;
       this.gbpToken = gbp;
       this.minorStbtus = minorStbtus;
       this.minorString = minorString;
    }

    /**
     * Resets the supplementbry stbtus vblues to fblse.
     */
    privbte void resetStbtusVblues() {
        dupToken = fblse;
        oldToken = fblse;
        unseqToken = fblse;
        gbpToken = fblse;
        minorStbtus = 0;
        minorString = null;
    }
}
