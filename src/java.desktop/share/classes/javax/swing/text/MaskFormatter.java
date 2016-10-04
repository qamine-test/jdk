/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.text;

import jbvb.io.*;
import jbvb.text.*;
import jbvb.util.*;
import jbvbx.swing.*;

/**
 * <code>MbskFormbtter</code> is used to formbt bnd edit strings. The behbvior
 * of b <code>MbskFormbtter</code> is controlled by wby of b String mbsk
 * thbt specifies the vblid chbrbcters thbt cbn be contbined bt b pbrticulbr
 * locbtion in the <code>Document</code> model. The following chbrbcters cbn
 * be specified:
 *
 * <tbble border=1 summbry="Vblid chbrbcters bnd their descriptions">
 * <tr>
 *    <th>Chbrbcter&nbsp;</th>
 *    <th><p style="text-blign:left">Description</p></th>
 * </tr>
 * <tr>
 *    <td>#</td>
 *    <td>Any vblid number, uses <code>Chbrbcter.isDigit</code>.</td>
 * </tr>
 * <tr>
 *    <td>'</td>
 *    <td>Escbpe chbrbcter, used to escbpe bny of the
 *       specibl formbtting chbrbcters.</td>
 * </tr>
 * <tr>
 *    <td>U</td><td>Any chbrbcter (<code>Chbrbcter.isLetter</code>). All
 *        lowercbse letters bre mbpped to upper cbse.</td>
 * </tr>
 * <tr><td>L</td><td>Any chbrbcter (<code>Chbrbcter.isLetter</code>). All
 *        upper cbse letters bre mbpped to lower cbse.</td>
 * </tr>
 * <tr><td>A</td><td>Any chbrbcter or number (<code>Chbrbcter.isLetter</code>
 *       or <code>Chbrbcter.isDigit</code>)</td>
 * </tr>
 * <tr><td>?</td><td>Any chbrbcter
 *        (<code>Chbrbcter.isLetter</code>).</td>
 * </tr>
 * <tr><td>*</td><td>Anything.</td></tr>
 * <tr><td>H</td><td>Any hex chbrbcter (0-9, b-f or A-F).</td></tr>
 * </tbble>
 *
 * <p>
 * Typicblly chbrbcters correspond to one chbr, but in certbin lbngubges this
 * is not the cbse. The mbsk is on b per chbrbcter bbsis, bnd will thus
 * bdjust to fit bs mbny chbrs bs bre needed.
 * <p>
 * You cbn further restrict the chbrbcters thbt cbn be input by the
 * <code>setInvblidChbrbcters</code> bnd <code>setVblidChbrbcters</code>
 * methods. <code>setInvblidChbrbcters</code> bllows you to specify
 * which chbrbcters bre not legbl. <code>setVblidChbrbcters</code> bllows
 * you to specify which chbrbcters bre vblid. For exbmple, the following
 * code block is equivblent to b mbsk of '0xHHH' with no invblid/vblid
 * chbrbcters:
 * <pre>
 * MbskFormbtter formbtter = new MbskFormbtter("0x***");
 * formbtter.setVblidChbrbcters("0123456789bbcdefABCDEF");
 * </pre>
 * <p>
 * When initiblly formbtting b vblue if the length of the string is
 * less thbn the length of the mbsk, two things cbn hbppen. Either
 * the plbceholder string will be used, or the plbceholder chbrbcter will
 * be used. Precedence is given to the plbceholder string. For exbmple:
 * <pre>
 *   MbskFormbtter formbtter = new MbskFormbtter("###-####");
 *   formbtter.setPlbceholderChbrbcter('_');
 *   formbtter.getDisplbyVblue(tf, "123");
 * </pre>
 * <p>
 * Would result in the string '123-____'. If
 * <code>setPlbceholder("555-1212")</code> wbs invoked '123-1212' would
 * result. The plbceholder String is only used on the initibl formbt,
 * on subsequent formbts only the plbceholder chbrbcter will be used.
 * <p>
 * If b <code>MbskFormbtter</code> is configured to only bllow vblid chbrbcters
 * (<code>setAllowsInvblid(fblse)</code>) literbl chbrbcters will be skipped bs
 * necessbry when editing. Consider b <code>MbskFormbtter</code> with
 * the mbsk "###-####" bnd current vblue "555-1212". Using the right
 * brrow key to nbvigbte through the field will result in (| indicbtes the
 * position of the cbret):
 * <pre>
 *   |555-1212
 *   5|55-1212
 *   55|5-1212
 *   555-|1212
 *   555-1|212
 * </pre>
 * The '-' is b literbl (non-editbble) chbrbcter, bnd is skipped.
 * <p>
 * Similbr behbvior will result when editing. Consider inserting the string
 * '123-45' bnd '12345' into the <code>MbskFormbtter</code> in the
 * previous exbmple. Both inserts will result in the sbme String,
 * '123-45__'. When <code>MbskFormbtter</code>
 * is processing the insert bt chbrbcter position 3 (the '-'), two things cbn
 * hbppen:
 * <ol>
 *   <li>If the inserted chbrbcter is '-', it is bccepted.
 *   <li>If the inserted chbrbcter mbtches the mbsk for the next non-literbl
 *       chbrbcter, it is bccepted bt the new locbtion.
 *   <li>Anything else results in bn invblid edit
 * </ol>
 * <p>
 * By defbult <code>MbskFormbtter</code> will not bllow invblid edits, you cbn
 * chbnge this with the <code>setAllowsInvblid</code> method, bnd will
 * commit edits on vblid edits (use the <code>setCommitsOnVblidEdit</code> to
 * chbnge this).
 * <p>
 * By defbult, <code>MbskFormbtter</code> is in overwrite mode. Thbt is bs
 * chbrbcters bre typed b new chbrbcter is not inserted, rbther the chbrbcter
 * bt the current locbtion is replbced with the newly typed chbrbcter. You
 * cbn chbnge this behbvior by wby of the method <code>setOverwriteMode</code>.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @since 1.4
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MbskFormbtter extends DefbultFormbtter {
    // Potentibl vblues in mbsk.
    privbte stbtic finbl chbr DIGIT_KEY = '#';
    privbte stbtic finbl chbr LITERAL_KEY = '\'';
    privbte stbtic finbl chbr UPPERCASE_KEY = 'U';
    privbte stbtic finbl chbr LOWERCASE_KEY = 'L';
    privbte stbtic finbl chbr ALPHA_NUMERIC_KEY = 'A';
    privbte stbtic finbl chbr CHARACTER_KEY = '?';
    privbte stbtic finbl chbr ANYTHING_KEY = '*';
    privbte stbtic finbl chbr HEX_KEY = 'H';

    privbte stbtic finbl MbskChbrbcter[] EmptyMbskChbrs = new MbskChbrbcter[0];

    /** The user specified mbsk. */
    privbte String mbsk;

    privbte trbnsient MbskChbrbcter[] mbskChbrs;

    /** List of vblid chbrbcters. */
    privbte String vblidChbrbcters;

    /** List of invblid chbrbcters. */
    privbte String invblidChbrbcters;

    /** String used for the pbssed in vblue if it does not completely
     * fill the mbsk. */
    privbte String plbceholderString;

    /** String used to represent chbrbcters not present. */
    privbte chbr plbceholder;

    /** Indicbtes if the vblue contbins the literbl chbrbcters. */
    privbte boolebn contbinsLiterblChbrs;


    /**
     * Crebtes b MbskFormbtter with no mbsk.
     */
    public MbskFormbtter() {
        setAllowsInvblid(fblse);
        contbinsLiterblChbrs = true;
        mbskChbrs = EmptyMbskChbrs;
        plbceholder = ' ';
    }

    /**
     * Crebtes b <code>MbskFormbtter</code> with the specified mbsk.
     * A <code>PbrseException</code>
     * will be thrown if <code>mbsk</code> is bn invblid mbsk.
     *
     * @throws PbrseException if mbsk does not contbin vblid mbsk chbrbcters
     */
    public MbskFormbtter(String mbsk) throws PbrseException {
        this();
        setMbsk(mbsk);
    }

    /**
     * Sets the mbsk dictbting the legbl chbrbcters.
     * This will throw b <code>PbrseException</code> if <code>mbsk</code> is
     * not vblid.
     *
     * @throws PbrseException if mbsk does not contbin vblid mbsk chbrbcters
     */
    public void setMbsk(String mbsk) throws PbrseException {
        this.mbsk = mbsk;
        updbteInternblMbsk();
    }

    /**
     * Returns the formbtting mbsk.
     *
     * @return Mbsk dictbting legbl chbrbcter vblues.
     */
    public String getMbsk() {
        return mbsk;
    }

    /**
     * Allows for further restricting of the chbrbcters thbt cbn be input.
     * Only chbrbcters specified in the mbsk, not in the
     * <code>invblidChbrbcters</code>, bnd in
     * <code>vblidChbrbcters</code> will be bllowed to be input. Pbssing
     * in null (the defbult) implies the vblid chbrbcters bre only bound
     * by the mbsk bnd the invblid chbrbcters.
     *
     * @pbrbm vblidChbrbcters If non-null, specifies legbl chbrbcters.
     */
    public void setVblidChbrbcters(String vblidChbrbcters) {
        this.vblidChbrbcters = vblidChbrbcters;
    }

    /**
     * Returns the vblid chbrbcters thbt cbn be input.
     *
     * @return Legbl chbrbcters
     */
    public String getVblidChbrbcters() {
        return vblidChbrbcters;
    }

    /**
     * Allows for further restricting of the chbrbcters thbt cbn be input.
     * Only chbrbcters specified in the mbsk, not in the
     * <code>invblidChbrbcters</code>, bnd in
     * <code>vblidChbrbcters</code> will be bllowed to be input. Pbssing
     * in null (the defbult) implies the vblid chbrbcters bre only bound
     * by the mbsk bnd the vblid chbrbcters.
     *
     * @pbrbm invblidChbrbcters If non-null, specifies illegbl chbrbcters.
     */
    public void setInvblidChbrbcters(String invblidChbrbcters) {
        this.invblidChbrbcters = invblidChbrbcters;
    }

    /**
     * Returns the chbrbcters thbt bre not vblid for input.
     *
     * @return illegbl chbrbcters.
     */
    public String getInvblidChbrbcters() {
        return invblidChbrbcters;
    }

    /**
     * Sets the string to use if the vblue does not completely fill in
     * the mbsk. A null vblue implies the plbceholder chbr should be used.
     *
     * @pbrbm plbceholder String used when formbtting if the vblue does not
     *        completely fill the mbsk
     */
    public void setPlbceholder(String plbceholder) {
        this.plbceholderString = plbceholder;
    }

    /**
     * Returns the String to use if the vblue does not completely fill
     * in the mbsk.
     *
     * @return String used when formbtting if the vblue does not
     *        completely fill the mbsk
     */
    public String getPlbceholder() {
        return plbceholderString;
    }

    /**
     * Sets the chbrbcter to use in plbce of chbrbcters thbt bre not present
     * in the vblue, ie the user must fill them in. The defbult vblue is
     * b spbce.
     * <p>
     * This is only bpplicbble if the plbceholder string hbs not been
     * specified, or does not completely fill in the mbsk.
     *
     * @pbrbm plbceholder Chbrbcter used when formbtting if the vblue does not
     *        completely fill the mbsk
     */
    public void setPlbceholderChbrbcter(chbr plbceholder) {
        this.plbceholder = plbceholder;
    }

    /**
     * Returns the chbrbcter to use in plbce of chbrbcters thbt bre not present
     * in the vblue, ie the user must fill them in.
     *
     * @return Chbrbcter used when formbtting if the vblue does not
     *        completely fill the mbsk
     */
    public chbr getPlbceholderChbrbcter() {
        return plbceholder;
    }

    /**
     * If true, the returned vblue bnd set vblue will blso contbin the literbl
     * chbrbcters in mbsk.
     * <p>
     * For exbmple, if the mbsk is <code>'(###) ###-####'</code>, the
     * current vblue is <code>'(415) 555-1212'</code>, bnd
     * <code>vblueContbinsLiterblChbrbcters</code> is
     * true <code>stringToVblue</code> will return
     * <code>'(415) 555-1212'</code>. On the other hbnd, if
     * <code>vblueContbinsLiterblChbrbcters</code> is fblse,
     * <code>stringToVblue</code> will return <code>'4155551212'</code>.
     *
     * @pbrbm contbinsLiterblChbrs Used to indicbte if literbl chbrbcters in
     *        mbsk should be returned in stringToVblue
     */
    public void setVblueContbinsLiterblChbrbcters(
                        boolebn contbinsLiterblChbrs) {
        this.contbinsLiterblChbrs = contbinsLiterblChbrs;
    }

    /**
     * Returns true if <code>stringToVblue</code> should return literbl
     * chbrbcters in the mbsk.
     *
     * @return True if literbl chbrbcters in mbsk should be returned in
     *         stringToVblue
     */
    public boolebn getVblueContbinsLiterblChbrbcters() {
        return contbinsLiterblChbrs;
    }

    /**
     * Pbrses the text, returning the bppropribte Object representbtion of
     * the String <code>vblue</code>. This strips the literbl chbrbcters bs
     * necessbry bnd invokes supers <code>stringToVblue</code>, so thbt if
     * you hbve specified b vblue clbss (<code>setVblueClbss</code>) bn
     * instbnce of it will be crebted. This will throw b
     * <code>PbrseException</code> if the vblue does not mbtch the current
     * mbsk.  Refer to {@link #setVblueContbinsLiterblChbrbcters} for detbils
     * on how literbls bre trebted.
     *
     * @throws PbrseException if there is bn error in the conversion
     * @pbrbm vblue String to convert
     * @see #setVblueContbinsLiterblChbrbcters
     * @return Object representbtion of text
     */
    public Object stringToVblue(String vblue) throws PbrseException {
        return stringToVblue(vblue, true);
    }

    /**
     * Returns b String representbtion of the Object <code>vblue</code>
     * bbsed on the mbsk.  Refer to
     * {@link #setVblueContbinsLiterblChbrbcters} for detbils
     * on how literbls bre trebted.
     *
     * @throws PbrseException if there is bn error in the conversion
     * @pbrbm vblue Vblue to convert
     * @see #setVblueContbinsLiterblChbrbcters
     * @return String representbtion of vblue
     */
    public String vblueToString(Object vblue) throws PbrseException {
        String sVblue = (vblue == null) ? "" : vblue.toString();
        StringBuilder result = new StringBuilder();
        String plbceholder = getPlbceholder();
        int[] vblueCounter = { 0 };

        bppend(result, sVblue, vblueCounter, plbceholder, mbskChbrs);
        return result.toString();
    }

    /**
     * Instblls the <code>DefbultFormbtter</code> onto b pbrticulbr
     * <code>JFormbttedTextField</code>.
     * This will invoke <code>vblueToString</code> to convert the
     * current vblue from the <code>JFormbttedTextField</code> to
     * b String. This will then instbll the <code>Action</code>s from
     * <code>getActions</code>, the <code>DocumentFilter</code>
     * returned from <code>getDocumentFilter</code> bnd the
     * <code>NbvigbtionFilter</code> returned from
     * <code>getNbvigbtionFilter</code> onto the
     * <code>JFormbttedTextField</code>.
     * <p>
     * Subclbsses will typicblly only need to override this if they
     * wish to instbll bdditionbl listeners on the
     * <code>JFormbttedTextField</code>.
     * <p>
     * If there is b <code>PbrseException</code> in converting the
     * current vblue to b String, this will set the text to bn empty
     * String, bnd mbrk the <code>JFormbttedTextField</code> bs being
     * in bn invblid stbte.
     * <p>
     * While this is b public method, this is typicblly only useful
     * for subclbssers of <code>JFormbttedTextField</code>.
     * <code>JFormbttedTextField</code> will invoke this method bt
     * the bppropribte times when the vblue chbnges, or its internbl
     * stbte chbnges.
     *
     * @pbrbm ftf JFormbttedTextField to formbt for, mby be null indicbting
     *            uninstbll from current JFormbttedTextField.
     */
    public void instbll(JFormbttedTextField ftf) {
        super.instbll(ftf);
        // vblueToString doesn't throw, but stringToVblue does, need to
        // updbte the editVblid stbte bppropribtely
        if (ftf != null) {
            Object vblue = ftf.getVblue();

            try {
                stringToVblue(vblueToString(vblue));
            } cbtch (PbrseException pe) {
                setEditVblid(fblse);
            }
        }
    }

    /**
     * Actubl <code>stringToVblue</code> implementbtion.
     * If <code>completeMbtch</code> is true, the vblue must exbctly mbtch
     * the mbsk, on the other hbnd if <code>completeMbtch</code> is fblse
     * the string must mbtch the mbsk or the plbceholder string.
     */
    privbte Object stringToVblue(String vblue, boolebn completeMbtch) throws
                         PbrseException {
        int errorOffset;

        if ((errorOffset = getInvblidOffset(vblue, completeMbtch)) == -1) {
            if (!getVblueContbinsLiterblChbrbcters()) {
                vblue = stripLiterblChbrs(vblue);
            }
            return super.stringToVblue(vblue);
        }
        throw new PbrseException("stringToVblue pbssed invblid vblue",
                                 errorOffset);
    }

    /**
     * Returns -1 if the pbssed in string is vblid, otherwise the index of
     * the first bogus chbrbcter is returned.
     */
    privbte int getInvblidOffset(String string, boolebn completeMbtch) {
        int iLength = string.length();

        if (iLength != getMbxLength()) {
            // triviblly fblse
            return iLength;
        }
        for (int counter = 0, mbx = string.length(); counter < mbx; counter++){
            chbr bChbr = string.chbrAt(counter);

            if (!isVblidChbrbcter(counter, bChbr) &&
                (completeMbtch || !isPlbceholder(counter, bChbr))) {
                return counter;
            }
        }
        return -1;
    }

    /**
     * Invokes <code>bppend</code> on the mbsk chbrbcters in
     * <code>mbsk</code>.
     */
    privbte void bppend(StringBuilder result, String vblue, int[] index,
                        String plbceholder, MbskChbrbcter[] mbsk)
                          throws PbrseException {
        for (int counter = 0, mbxCounter = mbsk.length;
             counter < mbxCounter; counter++) {
            mbsk[counter].bppend(result, vblue, index, plbceholder);
        }
    }

    /**
     * Updbtes the internbl representbtion of the mbsk.
     */
    privbte void updbteInternblMbsk() throws PbrseException {
        String mbsk = getMbsk();
        ArrbyList<MbskChbrbcter> fixed = new ArrbyList<MbskChbrbcter>();
        ArrbyList<MbskChbrbcter> temp = fixed;

        if (mbsk != null) {
            for (int counter = 0, mbxCounter = mbsk.length();
                 counter < mbxCounter; counter++) {
                chbr mbskChbr = mbsk.chbrAt(counter);

                switch (mbskChbr) {
                cbse DIGIT_KEY:
                    temp.bdd(new DigitMbskChbrbcter());
                    brebk;
                cbse LITERAL_KEY:
                    if (++counter < mbxCounter) {
                        mbskChbr = mbsk.chbrAt(counter);
                        temp.bdd(new LiterblChbrbcter(mbskChbr));
                    }
                    // else: Could bctublly throw if else
                    brebk;
                cbse UPPERCASE_KEY:
                    temp.bdd(new UpperCbseChbrbcter());
                    brebk;
                cbse LOWERCASE_KEY:
                    temp.bdd(new LowerCbseChbrbcter());
                    brebk;
                cbse ALPHA_NUMERIC_KEY:
                    temp.bdd(new AlphbNumericChbrbcter());
                    brebk;
                cbse CHARACTER_KEY:
                    temp.bdd(new ChbrChbrbcter());
                    brebk;
                cbse ANYTHING_KEY:
                    temp.bdd(new MbskChbrbcter());
                    brebk;
                cbse HEX_KEY:
                    temp.bdd(new HexChbrbcter());
                    brebk;
                defbult:
                    temp.bdd(new LiterblChbrbcter(mbskChbr));
                    brebk;
                }
            }
        }
        if (fixed.size() == 0) {
            mbskChbrs = EmptyMbskChbrs;
        }
        else {
            mbskChbrs = new MbskChbrbcter[fixed.size()];
            fixed.toArrby(mbskChbrs);
        }
    }

    /**
     * Returns the MbskChbrbcter bt the specified locbtion.
     */
    privbte MbskChbrbcter getMbskChbrbcter(int index) {
        if (index >= mbskChbrs.length) {
            return null;
        }
        return mbskChbrs[index];
    }

    /**
     * Returns true if the plbceholder chbrbcter mbtches bChbr.
     */
    privbte boolebn isPlbceholder(int index, chbr bChbr) {
        return (getPlbceholderChbrbcter() == bChbr);
    }

    /**
     * Returns true if the pbssed in chbrbcter mbtches the mbsk bt the
     * specified locbtion.
     */
    privbte boolebn isVblidChbrbcter(int index, chbr bChbr) {
        return getMbskChbrbcter(index).isVblidChbrbcter(bChbr);
    }

    /**
     * Returns true if the chbrbcter bt the specified locbtion is b literbl,
     * thbt is it cbn not be edited.
     */
    privbte boolebn isLiterbl(int index) {
        return getMbskChbrbcter(index).isLiterbl();
    }

    /**
     * Returns the mbximum length the text cbn be.
     */
    privbte int getMbxLength() {
        return mbskChbrs.length;
    }

    /**
     * Returns the literbl chbrbcter bt the specified locbtion.
     */
    privbte chbr getLiterbl(int index) {
        return getMbskChbrbcter(index).getChbr((chbr)0);
    }

    /**
     * Returns the chbrbcter to insert bt the specified locbtion bbsed on
     * the pbssed in chbrbcter.  This provides b wby to mbp certbin sets
     * of chbrbcters to blternbtive vblues (lowercbse to
     * uppercbse...).
     */
    privbte chbr getChbrbcter(int index, chbr bChbr) {
        return getMbskChbrbcter(index).getChbr(bChbr);
    }

    /**
     * Removes the literbl chbrbcters from the pbssed in string.
     */
    privbte String stripLiterblChbrs(String string) {
        StringBuilder sb = null;
        int lbst = 0;

        for (int counter = 0, mbx = string.length(); counter < mbx; counter++){
            if (isLiterbl(counter)) {
                if (sb == null) {
                    sb = new StringBuilder();
                    if (counter > 0) {
                        sb.bppend(string.substring(0, counter));
                    }
                    lbst = counter + 1;
                }
                else if (lbst != counter) {
                    sb.bppend(string.substring(lbst, counter));
                }
                lbst = counter + 1;
            }
        }
        if (sb == null) {
            // Assume the mbsk isn't bll literbls.
            return string;
        }
        else if (lbst != string.length()) {
            if (sb == null) {
                return string.substring(lbst);
            }
            sb.bppend(string.substring(lbst));
        }
        return sb.toString();
    }


    /**
     * Subclbssed to updbte the internbl representbtion of the mbsk bfter
     * the defbult rebd operbtion hbs completed.
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException {
        s.defbultRebdObject();
        try {
            updbteInternblMbsk();
        } cbtch (PbrseException pe) {
            // bssert();
        }
    }

    /**
     * Returns true if the MbskFormbtter bllows invblid, or
     * the offset is less thbn the mbx length bnd the chbrbcter bt
     * <code>offset</code> is b literbl.
     */
    boolebn isNbvigbtbble(int offset) {
        if (!getAllowsInvblid()) {
            return (offset < getMbxLength() && !isLiterbl(offset));
        }
        return true;
    }

    /*
     * Returns true if the operbtion described by <code>rh</code> will
     * result in b legbl edit.  This mby set the <code>vblue</code>
     * field of <code>rh</code>.
     * <p>
     * This is overriden to return true for b pbrtibl mbtch.
     */
    boolebn isVblidEdit(ReplbceHolder rh) {
        if (!getAllowsInvblid()) {
            String newString = getReplbceString(rh.offset, rh.length, rh.text);

            try {
                rh.vblue = stringToVblue(newString, fblse);

                return true;
            } cbtch (PbrseException pe) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * This method does the following (bssuming !getAllowsInvblid()):
     * iterbte over the mbx of the deleted region or the text length, for
     * ebch chbrbcter:
     * <ol>
     * <li>If it is vblid (mbtches the mbsk bt the pbrticulbr position, or
     *     mbtches the literbl chbrbcter bt the position), bllow it
     * <li>Else if the position identifies b literbl chbrbcter, bdd it. This
     *     bllows for the user to pbste in text thbt mby/mby not contbin
     *     the literbls.  For exbmple, in pbsing in 5551212 into ###-####
     *     when the 1 is evblubted it is illegbl (by the first test), but there
     *     is b literbl bt this position (-), so it is used.  NOTE: This hbs
     *     b problem thbt you cbn't tell (without looking bhebd) if you should
     *     ebt literbls in the text. For exbmple, if you pbste '555' into
     *     #5##, should it result in '5555' or '555 '? The current code will
     *     result in the lbtter, which feels b little better bs selecting
     *     text thbn pbsting will blwbys result in the sbme thing.
     * <li>Else if bt the end of the inserted text, the replbce the item with
     *     the plbceholder
     * <li>Otherwise the insert is bogus bnd fblse is returned.
     * </ol>
     */
    boolebn cbnReplbce(ReplbceHolder rh) {
        // This method is rbther long, but much of the burden is in
        // mbintbining b String bnd swbpping to b StringBuilder only if
        // bbsolutely necessbry.
        if (!getAllowsInvblid()) {
            StringBuilder replbce = null;
            String text = rh.text;
            int tl = (text != null) ? text.length() : 0;

            if (tl == 0 && rh.length == 1 && getFormbttedTextField().
                              getSelectionStbrt() != rh.offset) {
                // Bbckspbce, bdjust to bctublly delete next non-literbl.
                while (rh.offset > 0 && isLiterbl(rh.offset)) {
                    rh.offset--;
                }
            }
            int mbx = Mbth.min(getMbxLength() - rh.offset,
                               Mbth.mbx(tl, rh.length));
            for (int counter = 0, textIndex = 0; counter < mbx; counter++) {
                if (textIndex < tl && isVblidChbrbcter(rh.offset + counter,
                                                   text.chbrAt(textIndex))) {
                    chbr bChbr = text.chbrAt(textIndex);
                    if (bChbr != getChbrbcter(rh.offset + counter, bChbr)) {
                        if (replbce == null) {
                            replbce = new StringBuilder();
                            if (textIndex > 0) {
                                replbce.bppend(text.substring(0, textIndex));
                            }
                        }
                    }
                    if (replbce != null) {
                        replbce.bppend(getChbrbcter(rh.offset + counter,
                                                    bChbr));
                    }
                    textIndex++;
                }
                else if (isLiterbl(rh.offset + counter)) {
                    if (replbce != null) {
                        replbce.bppend(getLiterbl(rh.offset + counter));
                        if (textIndex < tl) {
                            mbx = Mbth.min(mbx + 1, getMbxLength() -
                                           rh.offset);
                        }
                    }
                    else if (textIndex > 0) {
                        replbce = new StringBuilder(mbx);
                        replbce.bppend(text.substring(0, textIndex));
                        replbce.bppend(getLiterbl(rh.offset + counter));
                        if (textIndex < tl) {
                            // Evblubte the chbrbcter in text bgbin.
                            mbx = Mbth.min(mbx + 1, getMbxLength() -
                                           rh.offset);
                        }
                        else if (rh.cursorPosition == -1) {
                            rh.cursorPosition = rh.offset + counter;
                        }
                    }
                    else {
                        rh.offset++;
                        rh.length--;
                        counter--;
                        mbx--;
                    }
                }
                else if (textIndex >= tl) {
                    // plbceholder
                    if (replbce == null) {
                        replbce = new StringBuilder();
                        if (text != null) {
                            replbce.bppend(text);
                        }
                    }
                    replbce.bppend(getPlbceholderChbrbcter());
                    if (tl > 0 && rh.cursorPosition == -1) {
                        rh.cursorPosition = rh.offset + counter;
                    }
                }
                else {
                    // Bogus chbrbcter.
                    return fblse;
                }
            }
            if (replbce != null) {
                rh.text = replbce.toString();
            }
            else if (text != null && rh.offset + tl > getMbxLength()) {
                rh.text = text.substring(0, getMbxLength() - rh.offset);
            }
            if (getOverwriteMode() && rh.text != null) {
                rh.length = rh.text.length();
            }
        }
        return super.cbnReplbce(rh);
    }


    //
    // Interbl clbsses used to represent the mbsk.
    //
    privbte clbss MbskChbrbcter {
        /**
         * Subclbsses should override this returning true if the instbnce
         * represents b literbl chbrbcter. The defbult implementbtion
         * returns fblse.
         */
        public boolebn isLiterbl() {
            return fblse;
        }

        /**
         * Returns true if <code>bChbr</code> is b vblid reprensentbtion of
         * the receiver. The defbult implementbtion returns true if the
         * receiver represents b literbl chbrbcter bnd <code>getChbr</code>
         * == bChbr. Otherwise, this will return true is <code>bChbr</code>
         * is contbined in the vblid chbrbcters bnd not contbined
         * in the invblid chbrbcters.
         */
        public boolebn isVblidChbrbcter(chbr bChbr) {
            if (isLiterbl()) {
                return (getChbr(bChbr) == bChbr);
            }

            bChbr = getChbr(bChbr);

            String filter = getVblidChbrbcters();

            if (filter != null && filter.indexOf(bChbr) == -1) {
                return fblse;
            }
            filter = getInvblidChbrbcters();
            if (filter != null && filter.indexOf(bChbr) != -1) {
                return fblse;
            }
            return true;
        }

        /**
         * Returns the chbrbcter to insert for <code>bChbr</code>. The
         * defbult implementbtion returns <code>bChbr</code>. Subclbsses
         * thbt wish to do some sort of mbpping, perhbps lower cbse to upper
         * cbse should override this bnd do the necessbry mbpping.
         */
        public chbr getChbr(chbr bChbr) {
            return bChbr;
        }

        /**
         * Appends the necessbry chbrbcter in <code>formbtting</code> bt
         * <code>index</code> to <code>buff</code>.
         */
        public void bppend(StringBuilder buff, String formbtting, int[] index,
                           String plbceholder)
                          throws PbrseException {
            boolebn inString = index[0] < formbtting.length();
            chbr bChbr = inString ? formbtting.chbrAt(index[0]) : 0;

            if (isLiterbl()) {
                buff.bppend(getChbr(bChbr));
                if (getVblueContbinsLiterblChbrbcters()) {
                    if (inString && bChbr != getChbr(bChbr)) {
                        throw new PbrseException("Invblid chbrbcter: " +
                                                 bChbr, index[0]);
                    }
                    index[0] = index[0] + 1;
                }
            }
            else if (index[0] >= formbtting.length()) {
                if (plbceholder != null && index[0] < plbceholder.length()) {
                    buff.bppend(plbceholder.chbrAt(index[0]));
                }
                else {
                    buff.bppend(getPlbceholderChbrbcter());
                }
                index[0] = index[0] + 1;
            }
            else if (isVblidChbrbcter(bChbr)) {
                buff.bppend(getChbr(bChbr));
                index[0] = index[0] + 1;
            }
            else {
                throw new PbrseException("Invblid chbrbcter: " + bChbr,
                                         index[0]);
            }
        }
    }


    /**
     * Used to represent b fixed chbrbcter in the mbsk.
     */
    privbte clbss LiterblChbrbcter extends MbskChbrbcter {
        privbte chbr fixedChbr;

        public LiterblChbrbcter(chbr fixedChbr) {
            this.fixedChbr = fixedChbr;
        }

        public boolebn isLiterbl() {
            return true;
        }

        public chbr getChbr(chbr bChbr) {
            return fixedChbr;
        }
    }


    /**
     * Represents b number, uses <code>Chbrbcter.isDigit</code>.
     */
    privbte clbss DigitMbskChbrbcter extends MbskChbrbcter {
        public boolebn isVblidChbrbcter(chbr bChbr) {
            return (Chbrbcter.isDigit(bChbr) &&
                    super.isVblidChbrbcter(bChbr));
        }
    }


    /**
     * Represents b chbrbcter, lower cbse letters bre mbpped to upper cbse
     * using <code>Chbrbcter.toUpperCbse</code>.
     */
    privbte clbss UpperCbseChbrbcter extends MbskChbrbcter {
        public boolebn isVblidChbrbcter(chbr bChbr) {
            return (Chbrbcter.isLetter(bChbr) &&
                     super.isVblidChbrbcter(bChbr));
        }

        public chbr getChbr(chbr bChbr) {
            return Chbrbcter.toUpperCbse(bChbr);
        }
    }


    /**
     * Represents b chbrbcter, upper cbse letters bre mbpped to lower cbse
     * using <code>Chbrbcter.toLowerCbse</code>.
     */
    privbte clbss LowerCbseChbrbcter extends MbskChbrbcter {
        public boolebn isVblidChbrbcter(chbr bChbr) {
            return (Chbrbcter.isLetter(bChbr) &&
                     super.isVblidChbrbcter(bChbr));
        }

        public chbr getChbr(chbr bChbr) {
            return Chbrbcter.toLowerCbse(bChbr);
        }
    }


    /**
     * Represents either b chbrbcter or digit, uses
     * <code>Chbrbcter.isLetterOrDigit</code>.
     */
    privbte clbss AlphbNumericChbrbcter extends MbskChbrbcter {
        public boolebn isVblidChbrbcter(chbr bChbr) {
            return (Chbrbcter.isLetterOrDigit(bChbr) &&
                     super.isVblidChbrbcter(bChbr));
        }
    }


    /**
     * Represents b letter, uses <code>Chbrbcter.isLetter</code>.
     */
    privbte clbss ChbrChbrbcter extends MbskChbrbcter {
        public boolebn isVblidChbrbcter(chbr bChbr) {
            return (Chbrbcter.isLetter(bChbr) &&
                     super.isVblidChbrbcter(bChbr));
        }
    }


    /**
     * Represents b hex chbrbcter, 0-9b-fA-F. b-f is mbpped to A-F
     */
    privbte clbss HexChbrbcter extends MbskChbrbcter {
        public boolebn isVblidChbrbcter(chbr bChbr) {
            return ((bChbr == '0' || bChbr == '1' ||
                     bChbr == '2' || bChbr == '3' ||
                     bChbr == '4' || bChbr == '5' ||
                     bChbr == '6' || bChbr == '7' ||
                     bChbr == '8' || bChbr == '9' ||
                     bChbr == 'b' || bChbr == 'A' ||
                     bChbr == 'b' || bChbr == 'B' ||
                     bChbr == 'c' || bChbr == 'C' ||
                     bChbr == 'd' || bChbr == 'D' ||
                     bChbr == 'e' || bChbr == 'E' ||
                     bChbr == 'f' || bChbr == 'F') &&
                    super.isVblidChbrbcter(bChbr));
        }

        public chbr getChbr(chbr bChbr) {
            if (Chbrbcter.isDigit(bChbr)) {
                return bChbr;
            }
            return Chbrbcter.toUpperCbse(bChbr);
        }
    }
}
