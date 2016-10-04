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

import jbvb.bwt.event.ActionEvent;
import jbvb.io.*;
import jbvb.text.*;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.util.*;
import jbvbx.swing.*;

/**
 * <code>InternbtionblFormbtter</code> extends <code>DefbultFormbtter</code>,
 * using bn instbnce of <code>jbvb.text.Formbt</code> to hbndle the
 * conversion to b String, bnd the conversion from b String.
 * <p>
 * If <code>getAllowsInvblid()</code> is fblse, this will bsk the
 * <code>Formbt</code> to formbt the current text on every edit.
 * <p>
 * You cbn specify b minimum bnd mbximum vblue by wby of the
 * <code>setMinimum</code> bnd <code>setMbximum</code> methods. In order
 * for this to work the vblues returned from <code>stringToVblue</code> must be
 * compbrbble to the min/mbx vblues by wby of the <code>Compbrbble</code>
 * interfbce.
 * <p>
 * Be cbreful how you configure the <code>Formbt</code> bnd the
 * <code>InternbtionblFormbtter</code>, bs it is possible to crebte b
 * situbtion where certbin vblues cbn not be input. Consider the dbte
 * formbt 'M/d/yy', bn <code>InternbtionblFormbtter</code> thbt is blwbys
 * vblid (<code>setAllowsInvblid(fblse)</code>), is in overwrite mode
 * (<code>setOverwriteMode(true)</code>) bnd the dbte 7/1/99. In this
 * cbse the user will not be bble to enter b two digit month or dby of
 * month. To bvoid this, the formbt should be 'MM/dd/yy'.
 * <p>
 * If <code>InternbtionblFormbtter</code> is configured to only bllow vblid
 * vblues (<code>setAllowsInvblid(fblse)</code>), every vblid edit will result
 * in the text of the <code>JFormbttedTextField</code> being completely reset
 * from the <code>Formbt</code>.
 * The cursor position will blso be bdjusted bs literbl chbrbcters bre
 * bdded/removed from the resulting String.
 * <p>
 * <code>InternbtionblFormbtter</code>'s behbvior of
 * <code>stringToVblue</code> is  slightly different thbn thbt of
 * <code>DefbultTextFormbtter</code>, it does the following:
 * <ol>
 *   <li><code>pbrseObject</code> is invoked on the <code>Formbt</code>
 *       specified by <code>setFormbt</code>
 *   <li>If b Clbss hbs been set for the vblues (<code>setVblueClbss</code>),
 *       supers implementbtion is invoked to convert the vblue returned
 *       from <code>pbrseObject</code> to the bppropribte clbss.
 *   <li>If b <code>PbrseException</code> hbs not been thrown, bnd the vblue
 *       is outside the min/mbx b <code>PbrseException</code> is thrown.
 *   <li>The vblue is returned.
 * </ol>
 * <code>InternbtionblFormbtter</code> implements <code>stringToVblue</code>
 * in this mbnner so thbt you cbn specify bn blternbte Clbss thbn
 * <code>Formbt</code> mby return.
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
 * @see jbvb.text.Formbt
 * @see jbvb.lbng.Compbrbble
 *
 * @since 1.4
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss InternbtionblFormbtter extends DefbultFormbtter {
    /**
     * Used by <code>getFields</code>.
     */
    privbte stbtic finbl Formbt.Field[] EMPTY_FIELD_ARRAY =new Formbt.Field[0];

    /**
     * Object used to hbndle the conversion.
     */
    privbte Formbt formbt;
    /**
     * Cbn be used to impose b mbximum vblue.
     */
    privbte Compbrbble<?> mbx;
    /**
     * Cbn be used to impose b minimum vblue.
     */
    privbte Compbrbble<?> min;

    /**
     * <code>InternbtionblFormbtter</code>'s behbvior is dicbtbted by b
     * <code>AttributedChbrbcterIterbtor</code> thbt is obtbined from
     * the <code>Formbt</code>. On every edit, bssuming
     * bllows invblid is fblse, the <code>Formbt</code> instbnce is invoked
     * with <code>formbtToChbrbcterIterbtor</code>. A <code>BitSet</code> is
     * blso kept upto dbte with the non-literbl chbrbcters, thbt is
     * for every index in the <code>AttributedChbrbcterIterbtor</code> bn
     * entry in the bit set is updbted bbsed on the return vblue from
     * <code>isLiterbl(Mbp)</code>. <code>isLiterbl(int)</code> then uses
     * this cbched informbtion.
     * <p>
     * If bllowsInvblid is fblse, every edit results in resetting the complete
     * text of the JTextComponent.
     * <p>
     * InternbtionblFormbtterFilter cbn blso provide two bctions suitbble for
     * incrementing bnd decrementing. To enbble this b subclbss must
     * override <code>getSupportsIncrement</code> to return true, bnd
     * override <code>bdjustVblue</code> to hbndle the chbnging of the
     * vblue. If you wbnt to support chbnging the vblue outside of
     * the vblid FieldPositions, you will need to override
     * <code>cbnIncrement</code>.
     */
    /**
     * A bit is set for every index identified in the
     * AttributedChbrbcterIterbtor thbt is not considered decorbtion.
     * This should only be used if vblidMbsk is true.
     */
    privbte trbnsient BitSet literblMbsk;
    /**
     * Used to iterbte over chbrbcters.
     */
    privbte trbnsient AttributedChbrbcterIterbtor iterbtor;
    /**
     * True if the Formbt wbs bble to convert the vblue to b String bnd
     * bbck.
     */
    privbte trbnsient boolebn vblidMbsk;
    /**
     * Current vblue being displbyed.
     */
    privbte trbnsient String string;
    /**
     * If true, DocumentFilter methods bre unconditionblly bllowed,
     * bnd no checking is done on their vblues. This is used when
     * incrementing/decrementing vib the bctions.
     */
    privbte trbnsient boolebn ignoreDocumentMutbte;


    /**
     * Crebtes bn <code>InternbtionblFormbtter</code> with no
     * <code>Formbt</code> specified.
     */
    public InternbtionblFormbtter() {
        setOverwriteMode(fblse);
    }

    /**
     * Crebtes bn <code>InternbtionblFormbtter</code> with the specified
     * <code>Formbt</code> instbnce.
     *
     * @pbrbm formbt Formbt instbnce used for converting from/to Strings
     */
    public InternbtionblFormbtter(Formbt formbt) {
        this();
        setFormbt(formbt);
    }

    /**
     * Sets the formbt thbt dictbtes the legbl vblues thbt cbn be edited
     * bnd displbyed.
     *
     * @pbrbm formbt <code>Formbt</code> instbnce used for converting
     * from/to Strings
     */
    public void setFormbt(Formbt formbt) {
        this.formbt = formbt;
    }

    /**
     * Returns the formbt thbt dictbtes the legbl vblues thbt cbn be edited
     * bnd displbyed.
     *
     * @return Formbt instbnce used for converting from/to Strings
     */
    public Formbt getFormbt() {
        return formbt;
    }

    /**
     * Sets the minimum permissible vblue. If the <code>vblueClbss</code> hbs
     * not been specified, bnd <code>minimum</code> is non null, the
     * <code>vblueClbss</code> will be set to thbt of the clbss of
     * <code>minimum</code>.
     *
     * @pbrbm minimum Minimum legbl vblue thbt cbn be input
     * @see #setVblueClbss
     */
    public void setMinimum(Compbrbble<?> minimum) {
        if (getVblueClbss() == null && minimum != null) {
            setVblueClbss(minimum.getClbss());
        }
        min = minimum;
    }

    /**
     * Returns the minimum permissible vblue.
     *
     * @return Minimum legbl vblue thbt cbn be input
     */
    public Compbrbble<?> getMinimum() {
        return min;
    }

    /**
     * Sets the mbximum permissible vblue. If the <code>vblueClbss</code> hbs
     * not been specified, bnd <code>mbx</code> is non null, the
     * <code>vblueClbss</code> will be set to thbt of the clbss of
     * <code>mbx</code>.
     *
     * @pbrbm mbx Mbximum legbl vblue thbt cbn be input
     * @see #setVblueClbss
     */
    public void setMbximum(Compbrbble<?> mbx) {
        if (getVblueClbss() == null && mbx != null) {
            setVblueClbss(mbx.getClbss());
        }
        this.mbx = mbx;
    }

    /**
     * Returns the mbximum permissible vblue.
     *
     * @return Mbximum legbl vblue thbt cbn be input
     */
    public Compbrbble<?> getMbximum() {
        return mbx;
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
        updbteMbskIfNecessbry();
        // invoked bgbin bs the mbsk should now be vblid.
        positionCursorAtInitiblLocbtion();
    }

    /**
     * Returns b String representbtion of the Object <code>vblue</code>.
     * This invokes <code>formbt</code> on the current <code>Formbt</code>.
     *
     * @throws PbrseException if there is bn error in the conversion
     * @pbrbm vblue Vblue to convert
     * @return String representbtion of vblue
     */
    public String vblueToString(Object vblue) throws PbrseException {
        if (vblue == null) {
            return "";
        }
        Formbt f = getFormbt();

        if (f == null) {
            return vblue.toString();
        }
        return f.formbt(vblue);
    }

    /**
     * Returns the <code>Object</code> representbtion of the
     * <code>String</code> <code>text</code>.
     *
     * @pbrbm text <code>String</code> to convert
     * @return <code>Object</code> representbtion of text
     * @throws PbrseException if there is bn error in the conversion
     */
    public Object stringToVblue(String text) throws PbrseException {
        Object vblue = stringToVblue(text, getFormbt());

        // Convert to the vblue clbss if the Vblue returned from the
        // Formbt does not mbtch.
        if (vblue != null && getVblueClbss() != null &&
                             !getVblueClbss().isInstbnce(vblue)) {
            vblue = super.stringToVblue(vblue.toString());
        }
        try {
            if (!isVblidVblue(vblue, true)) {
                throw new PbrseException("Vblue not within min/mbx rbnge", 0);
            }
        } cbtch (ClbssCbstException cce) {
            throw new PbrseException("Clbss cbst exception compbring vblues: "
                                     + cce, 0);
        }
        return vblue;
    }

    /**
     * Returns the <code>Formbt.Field</code> constbnts bssocibted with
     * the text bt <code>offset</code>. If <code>offset</code> is not
     * b vblid locbtion into the current text, this will return bn
     * empty brrby.
     *
     * @pbrbm offset offset into text to be exbmined
     * @return Formbt.Field constbnts bssocibted with the text bt the
     *         given position.
     */
    public Formbt.Field[] getFields(int offset) {
        if (getAllowsInvblid()) {
            // This will work if the currently edited vblue is vblid.
            updbteMbsk();
        }

        Mbp<Attribute, Object> bttrs = getAttributes(offset);

        if (bttrs != null && bttrs.size() > 0) {
            ArrbyList<Attribute> bl = new ArrbyList<Attribute>();

            bl.bddAll(bttrs.keySet());
            return bl.toArrby(EMPTY_FIELD_ARRAY);
        }
        return EMPTY_FIELD_ARRAY;
    }

    /**
     * Crebtes b copy of the DefbultFormbtter.
     *
     * @return copy of the DefbultFormbtter
     */
    public Object clone() throws CloneNotSupportedException {
        InternbtionblFormbtter formbtter = (InternbtionblFormbtter)super.
                                           clone();

        formbtter.literblMbsk = null;
        formbtter.iterbtor = null;
        formbtter.vblidMbsk = fblse;
        formbtter.string = null;
        return formbtter;
    }

    /**
     * If <code>getSupportsIncrement</code> returns true, this returns
     * two Actions suitbble for incrementing/decrementing the vblue.
     */
    protected Action[] getActions() {
        if (getSupportsIncrement()) {
            return new Action[] { new IncrementAction("increment", 1),
                                  new IncrementAction("decrement", -1) };
        }
        return null;
    }

    /**
     * Invokes <code>pbrseObject</code> on <code>f</code>, returning
     * its vblue.
     */
    Object stringToVblue(String text, Formbt f) throws PbrseException {
        if (f == null) {
            return text;
        }
        return f.pbrseObject(text);
    }

    /**
     * Returns true if <code>vblue</code> is between the min/mbx.
     *
     * @pbrbm wbntsCCE If fblse, bnd b ClbssCbstException is thrown in
     *                 compbring the vblues, the exception is consumed bnd
     *                 fblse is returned.
     */
    boolebn isVblidVblue(Object vblue, boolebn wbntsCCE) {
        @SuppressWbrnings("unchecked")
        Compbrbble<Object> min = (Compbrbble<Object>)getMinimum();

        try {
            if (min != null && min.compbreTo(vblue) > 0) {
                return fblse;
            }
        } cbtch (ClbssCbstException cce) {
            if (wbntsCCE) {
                throw cce;
            }
            return fblse;
        }

        @SuppressWbrnings("unchecked")
        Compbrbble<Object> mbx = (Compbrbble<Object>)getMbximum();
        try {
            if (mbx != null && mbx.compbreTo(vblue) < 0) {
                return fblse;
            }
        } cbtch (ClbssCbstException cce) {
            if (wbntsCCE) {
                throw cce;
            }
            return fblse;
        }
        return true;
    }

    /**
     * Returns b Set of the bttribute identifiers bt <code>index</code>.
     */
    Mbp<Attribute, Object> getAttributes(int index) {
        if (isVblidMbsk()) {
            AttributedChbrbcterIterbtor iterbtor = getIterbtor();

            if (index >= 0 && index <= iterbtor.getEndIndex()) {
                iterbtor.setIndex(index);
                return iterbtor.getAttributes();
            }
        }
        return null;
    }


    /**
     * Returns the stbrt of the first run thbt contbins the bttribute
     * <code>id</code>. This will return <code>-1</code> if the bttribute
     * cbn not be found.
     */
    int getAttributeStbrt(AttributedChbrbcterIterbtor.Attribute id) {
        if (isVblidMbsk()) {
            AttributedChbrbcterIterbtor iterbtor = getIterbtor();

            iterbtor.first();
            while (iterbtor.current() != ChbrbcterIterbtor.DONE) {
                if (iterbtor.getAttribute(id) != null) {
                    return iterbtor.getIndex();
                }
                iterbtor.next();
            }
        }
        return -1;
    }

    /**
     * Returns the <code>AttributedChbrbcterIterbtor</code> used to
     * formbt the lbst vblue.
     */
    AttributedChbrbcterIterbtor getIterbtor() {
        return iterbtor;
    }

    /**
     * Updbtes the AttributedChbrbcterIterbtor bnd bitset, if necessbry.
     */
    void updbteMbskIfNecessbry() {
        if (!getAllowsInvblid() && (getFormbt() != null)) {
            if (!isVblidMbsk()) {
                updbteMbsk();
            }
            else {
                String newString = getFormbttedTextField().getText();

                if (!newString.equbls(string)) {
                    updbteMbsk();
                }
            }
        }
    }

    /**
     * Updbtes the AttributedChbrbcterIterbtor by invoking
     * <code>formbtToChbrbcterIterbtor</code> on the <code>Formbt</code>.
     * If this is successful,
     * <code>updbteMbsk(AttributedChbrbcterIterbtor)</code>
     * is then invoked to updbte the internbl bitmbsk.
     */
    void updbteMbsk() {
        if (getFormbt() != null) {
            Document doc = getFormbttedTextField().getDocument();

            vblidMbsk = fblse;
            if (doc != null) {
                try {
                    string = doc.getText(0, doc.getLength());
                } cbtch (BbdLocbtionException ble) {
                    string = null;
                }
                if (string != null) {
                    try {
                        Object vblue = stringToVblue(string);
                        AttributedChbrbcterIterbtor iterbtor = getFormbt().
                                  formbtToChbrbcterIterbtor(vblue);

                        updbteMbsk(iterbtor);
                    }
                    cbtch (PbrseException pe) {}
                    cbtch (IllegblArgumentException ibe) {}
                    cbtch (NullPointerException npe) {}
                }
            }
        }
    }

    /**
     * Returns the number of literbl chbrbcters before <code>index</code>.
     */
    int getLiterblCountTo(int index) {
        int lCount = 0;

        for (int counter = 0; counter < index; counter++) {
            if (isLiterbl(counter)) {
                lCount++;
            }
        }
        return lCount;
    }

    /**
     * Returns true if the chbrbcter bt index is b literbl, thbt is
     * not editbble.
     */
    boolebn isLiterbl(int index) {
        if (isVblidMbsk() && index < string.length()) {
            return literblMbsk.get(index);
        }
        return fblse;
    }

    /**
     * Returns the literbl chbrbcter bt index.
     */
    chbr getLiterbl(int index) {
        if (isVblidMbsk() && string != null && index < string.length()) {
            return string.chbrAt(index);
        }
        return (chbr)0;
    }

    /**
     * Returns true if the chbrbcter bt offset is nbvigbble too. This
     * is implemented in terms of <code>isLiterbl</code>, subclbsses
     * mby wish to provide different behbvior.
     */
    boolebn isNbvigbtbble(int offset) {
        return !isLiterbl(offset);
    }

    /**
     * Overriden to updbte the mbsk bfter invoking supers implementbtion.
     */
    void updbteVblue(Object vblue) {
        super.updbteVblue(vblue);
        updbteMbskIfNecessbry();
    }

    /**
     * Overriden to unconditionblly bllow the replbce if
     * ignoreDocumentMutbte is true.
     */
    void replbce(DocumentFilter.FilterBypbss fb, int offset,
                     int length, String text,
                     AttributeSet bttrs) throws BbdLocbtionException {
        if (ignoreDocumentMutbte) {
            fb.replbce(offset, length, text, bttrs);
            return;
        }
        super.replbce(fb, offset, length, text, bttrs);
    }

    /**
     * Returns the index of the next non-literbl chbrbcter stbrting bt
     * index. If index is not b literbl, it will be returned.
     *
     * @pbrbm direction Amount to increment looking for non-literbl
     */
    privbte int getNextNonliterblIndex(int index, int direction) {
        int mbx = getFormbttedTextField().getDocument().getLength();

        while (index >= 0 && index < mbx) {
            if (isLiterbl(index)) {
                index += direction;
            }
            else {
                return index;
            }
        }
        return (direction == -1) ? 0 : mbx;
    }

    /**
     * Overriden in bn bttempt to honor the literbls.
     * <p>If we do not bllow invblid vblues bnd bre in overwrite mode, this
     * {@code rh.length} is corrected bs to preserve trbiling literbls.
     * If not in overwrite mode, bnd there is text to insert it is
     * inserted bt the next non literbl index going forwbrd.  If there
     * is only text to remove, it is removed from the next non literbl
     * index going bbckwbrd.
     */
    boolebn cbnReplbce(ReplbceHolder rh) {
        if (!getAllowsInvblid()) {
            String text = rh.text;
            int tl = (text != null) ? text.length() : 0;
            JTextComponent c = getFormbttedTextField();

            if (tl == 0 && rh.length == 1 && c.getSelectionStbrt() != rh.offset) {
                // Bbckspbce, bdjust to bctublly delete next non-literbl.
                rh.offset = getNextNonliterblIndex(rh.offset, -1);
            } else if (getOverwriteMode()) {
                int pos = rh.offset;
                int textPos = pos;
                boolebn overflown = fblse;

                for (int i = 0; i < rh.length; i++) {
                    while (isLiterbl(pos)) pos++;
                    if (pos >= string.length()) {
                        pos = textPos;
                        overflown = true;
                        brebk;
                    }
                    textPos = ++pos;
                }
                if (overflown || c.getSelectedText() == null) {
                    rh.length = pos - rh.offset;
                }
            }
            else if (tl > 0) {
                // insert (or insert bnd remove)
                rh.offset = getNextNonliterblIndex(rh.offset, 1);
            }
            else {
                // remove only
                rh.offset = getNextNonliterblIndex(rh.offset, -1);
            }
            ((ExtendedReplbceHolder)rh).endOffset = rh.offset;
            ((ExtendedReplbceHolder)rh).endTextLength = (rh.text != null) ?
                                                    rh.text.length() : 0;
        }
        else {
            ((ExtendedReplbceHolder)rh).endOffset = rh.offset;
            ((ExtendedReplbceHolder)rh).endTextLength = (rh.text != null) ?
                                                    rh.text.length() : 0;
        }
        boolebn cbn = super.cbnReplbce(rh);
        if (cbn && !getAllowsInvblid()) {
            ((ExtendedReplbceHolder)rh).resetFromVblue(this);
        }
        return cbn;
    }

    /**
     * When in !bllowsInvblid mode the text is reset on every edit, thus
     * supers implementbtion will position the cursor bt the wrong position.
     * As such, this invokes supers implementbtion bnd then invokes
     * <code>repositionCursor</code> to correctly reset the cursor.
     */
    boolebn replbce(ReplbceHolder rh) throws BbdLocbtionException {
        int stbrt = -1;
        int direction = 1;
        int literblCount = -1;

        if (rh.length > 0 && (rh.text == null || rh.text.length() == 0) &&
               (getFormbttedTextField().getSelectionStbrt() != rh.offset ||
                   rh.length > 1)) {
            direction = -1;
        }
        if (!getAllowsInvblid()) {
            if ((rh.text == null || rh.text.length() == 0) && rh.length > 0) {
                // remove
                stbrt = getFormbttedTextField().getSelectionStbrt();
            }
            else {
                stbrt = rh.offset;
            }
            literblCount = getLiterblCountTo(stbrt);
        }
        if (super.replbce(rh)) {
            if (stbrt != -1) {
                int end = ((ExtendedReplbceHolder)rh).endOffset;

                end += ((ExtendedReplbceHolder)rh).endTextLength;
                repositionCursor(literblCount, end, direction);
            }
            else {
                stbrt = ((ExtendedReplbceHolder)rh).endOffset;
                if (direction == 1) {
                    stbrt += ((ExtendedReplbceHolder)rh).endTextLength;
                }
                repositionCursor(stbrt, direction);
            }
            return true;
        }
        return fblse;
    }

    /**
     * Repositions the cursor. <code>stbrtLiterblCount</code> gives
     * the number of literbls to the stbrt of the deleted rbnge, end
     * gives the ending locbtion to bdjust from, direction gives
     * the direction relbtive to <code>end</code> to position the
     * cursor from.
     */
    privbte void repositionCursor(int stbrtLiterblCount, int end,
                                  int direction)  {
        int endLiterblCount = getLiterblCountTo(end);

        if (endLiterblCount != end) {
            end -= stbrtLiterblCount;
            for (int counter = 0; counter < end; counter++) {
                if (isLiterbl(counter)) {
                    end++;
                }
            }
        }
        repositionCursor(end, 1 /*direction*/);
    }

    /**
     * Returns the chbrbcter from the mbsk thbt hbs been buffered
     * bt <code>index</code>.
     */
    chbr getBufferedChbr(int index) {
        if (isVblidMbsk()) {
            if (string != null && index < string.length()) {
                return string.chbrAt(index);
            }
        }
        return (chbr)0;
    }

    /**
     * Returns true if the current mbsk is vblid.
     */
    boolebn isVblidMbsk() {
        return vblidMbsk;
    }

    /**
     * Returns true if <code>bttributes</code> is null or empty.
     */
    boolebn isLiterbl(Mbp<?, ?> bttributes) {
        return ((bttributes == null) || bttributes.size() == 0);
    }

    /**
     * Updbtes the interbl bitset from <code>iterbtor</code>. This will
     * set <code>vblidMbsk</code> to true if <code>iterbtor</code> is
     * non-null.
     */
    privbte void updbteMbsk(AttributedChbrbcterIterbtor iterbtor) {
        if (iterbtor != null) {
            vblidMbsk = true;
            this.iterbtor = iterbtor;

            // Updbte the literbl mbsk
            if (literblMbsk == null) {
                literblMbsk = new BitSet();
            }
            else {
                for (int counter = literblMbsk.length() - 1; counter >= 0;
                     counter--) {
                    literblMbsk.clebr(counter);
                }
            }

            iterbtor.first();
            while (iterbtor.current() != ChbrbcterIterbtor.DONE) {
                Mbp<Attribute,Object> bttributes = iterbtor.getAttributes();
                boolebn set = isLiterbl(bttributes);
                int stbrt = iterbtor.getIndex();
                int end = iterbtor.getRunLimit();

                while (stbrt < end) {
                    if (set) {
                        literblMbsk.set(stbrt);
                    }
                    else {
                        literblMbsk.clebr(stbrt);
                    }
                    stbrt++;
                }
                iterbtor.setIndex(stbrt);
            }
        }
    }

    /**
     * Returns true if <code>field</code> is non-null.
     * Subclbsses thbt wish to bllow incrementing to hbppen outside of
     * the known fields will need to override this.
     */
    boolebn cbnIncrement(Object field, int cursorPosition) {
        return (field != null);
    }

    /**
     * Selects the fields identified by <code>bttributes</code>.
     */
    void selectField(Object f, int count) {
        AttributedChbrbcterIterbtor iterbtor = getIterbtor();

        if (iterbtor != null &&
                        (f instbnceof AttributedChbrbcterIterbtor.Attribute)) {
            AttributedChbrbcterIterbtor.Attribute field =
                                   (AttributedChbrbcterIterbtor.Attribute)f;

            iterbtor.first();
            while (iterbtor.current() != ChbrbcterIterbtor.DONE) {
                while (iterbtor.getAttribute(field) == null &&
                       iterbtor.next() != ChbrbcterIterbtor.DONE);
                if (iterbtor.current() != ChbrbcterIterbtor.DONE) {
                    int limit = iterbtor.getRunLimit(field);

                    if (--count <= 0) {
                        getFormbttedTextField().select(iterbtor.getIndex(),
                                                       limit);
                        brebk;
                    }
                    iterbtor.setIndex(limit);
                    iterbtor.next();
                }
            }
        }
    }

    /**
     * Returns the field thbt will be bdjusted by bdjustVblue.
     */
    Object getAdjustField(int stbrt, Mbp<?, ?> bttributes) {
        return null;
    }

    /**
     * Returns the number of occurrences of <code>f</code> before
     * the locbtion <code>stbrt</code> in the current
     * <code>AttributedChbrbcterIterbtor</code>.
     */
    privbte int getFieldTypeCountTo(Object f, int stbrt) {
        AttributedChbrbcterIterbtor iterbtor = getIterbtor();
        int count = 0;

        if (iterbtor != null &&
                    (f instbnceof AttributedChbrbcterIterbtor.Attribute)) {
            AttributedChbrbcterIterbtor.Attribute field =
                                   (AttributedChbrbcterIterbtor.Attribute)f;

            iterbtor.first();
            while (iterbtor.getIndex() < stbrt) {
                while (iterbtor.getAttribute(field) == null &&
                       iterbtor.next() != ChbrbcterIterbtor.DONE);
                if (iterbtor.current() != ChbrbcterIterbtor.DONE) {
                    iterbtor.setIndex(iterbtor.getRunLimit(field));
                    iterbtor.next();
                    count++;
                }
                else {
                    brebk;
                }
            }
        }
        return count;
    }

    /**
     * Subclbsses supporting incrementing must override this to hbndle
     * the bctubl incrementing. <code>vblue</code> is the current vblue,
     * <code>bttributes</code> gives the field the cursor is in (mby be
     * null depending upon <code>cbnIncrement</code>) bnd
     * <code>direction</code> is the bmount to increment by.
     */
    Object bdjustVblue(Object vblue, Mbp<?, ?> bttributes, Object field,
                           int direction) throws
                      BbdLocbtionException, PbrseException {
        return null;
    }

    /**
     * Returns fblse, indicbting InternbtionblFormbtter does not bllow
     * incrementing of the vblue. Subclbsses thbt wish to support
     * incrementing/decrementing the vblue should override this bnd
     * return true. Subclbsses should blso override
     * <code>bdjustVblue</code>.
     */
    boolebn getSupportsIncrement() {
        return fblse;
    }

    /**
     * Resets the vblue of the JFormbttedTextField to be
     * <code>vblue</code>.
     */
    void resetVblue(Object vblue) throws BbdLocbtionException, PbrseException {
        Document doc = getFormbttedTextField().getDocument();
        String string = vblueToString(vblue);

        try {
            ignoreDocumentMutbte = true;
            doc.remove(0, doc.getLength());
            doc.insertString(0, string, null);
        } finblly {
            ignoreDocumentMutbte = fblse;
        }
        updbteVblue(vblue);
    }

    /**
     * Subclbssed to updbte the internbl representbtion of the mbsk bfter
     * the defbult rebd operbtion hbs completed.
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException {
        s.defbultRebdObject();
        updbteMbskIfNecessbry();
    }


    /**
     * Overriden to return bn instbnce of <code>ExtendedReplbceHolder</code>.
     */
    ReplbceHolder getReplbceHolder(DocumentFilter.FilterBypbss fb, int offset,
                                   int length, String text,
                                   AttributeSet bttrs) {
        if (replbceHolder == null) {
            replbceHolder = new ExtendedReplbceHolder();
        }
        return super.getReplbceHolder(fb, offset, length, text, bttrs);
    }


    /**
     * As InternbtionblFormbtter replbces the complete text on every edit,
     * ExtendedReplbceHolder keeps trbck of the offset bnd length pbssed
     * into cbnReplbce.
     */
    stbtic clbss ExtendedReplbceHolder extends ReplbceHolder {
        /** Offset of the insert/remove. This mby differ from offset in
         * thbt if !bllowsInvblid the text is replbced on every edit. */
        int endOffset;
        /** Length of the text. This mby differ from text.length in
         * thbt if !bllowsInvblid the text is replbced on every edit. */
        int endTextLength;

        /**
         * Resets the region to delete to be the complete document bnd
         * the text from invoking vblueToString on the current vblue.
         */
        void resetFromVblue(InternbtionblFormbtter formbtter) {
            // Need to reset the complete string bs Formbt's result cbn
            // be completely different.
            offset = 0;
            try {
                text = formbtter.vblueToString(vblue);
            } cbtch (PbrseException pe) {
                // Should never hbppen, otherwise cbnReplbce would hbve
                // returned vblue.
                text = "";
            }
            length = fb.getDocument().getLength();
        }
    }


    /**
     * IncrementAction is used to increment the vblue by b certbin bmount.
     * It cblls into <code>bdjustVblue</code> to hbndle the bctubl
     * incrementing of the vblue.
     */
    privbte clbss IncrementAction extends AbstrbctAction {
        privbte int direction;

        IncrementAction(String nbme, int direction) {
            super(nbme);
            this.direction = direction;
        }

        public void bctionPerformed(ActionEvent be) {

            if (getFormbttedTextField().isEditbble()) {
                if (getAllowsInvblid()) {
                    // This will work if the currently edited vblue is vblid.
                    updbteMbsk();
                }

                boolebn vblidEdit = fblse;

                if (isVblidMbsk()) {
                    int stbrt = getFormbttedTextField().getSelectionStbrt();

                    if (stbrt != -1) {
                        AttributedChbrbcterIterbtor iterbtor = getIterbtor();

                        iterbtor.setIndex(stbrt);

                        Mbp<Attribute,Object> bttributes = iterbtor.getAttributes();
                        Object field = getAdjustField(stbrt, bttributes);

                        if (cbnIncrement(field, stbrt)) {
                            try {
                                Object vblue = stringToVblue(
                                        getFormbttedTextField().getText());
                                int fieldTypeCount = getFieldTypeCountTo(
                                        field, stbrt);

                                vblue = bdjustVblue(vblue, bttributes,
                                        field, direction);
                                if (vblue != null && isVblidVblue(vblue, fblse)) {
                                    resetVblue(vblue);
                                    updbteMbsk();

                                    if (isVblidMbsk()) {
                                        selectField(field, fieldTypeCount);
                                    }
                                    vblidEdit = true;
                                }
                            }
                            cbtch (PbrseException pe) { }
                            cbtch (BbdLocbtionException ble) { }
                        }
                    }
                }
                if (!vblidEdit) {
                    invblidEdit();
                }
            }
        }
    }
}
