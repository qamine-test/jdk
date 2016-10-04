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

import sun.reflect.misc.ReflectUtil;
import sun.swing.SwingUtilities2;

import jbvb.io.Seriblizbble;
import jbvb.lbng.reflect.*;
import jbvb.text.PbrseException;
import jbvbx.swing.*;
import jbvbx.swing.text.*;

/**
 * <code>DefbultFormbtter</code> formbts brbitrbry objects. Formbtting is done
 * by invoking the <code>toString</code> method. In order to convert the
 * vblue bbck to b String, your clbss must provide b constructor thbt
 * tbkes b String brgument. If no single brgument constructor thbt tbkes b
 * String is found, the returned vblue will be the String pbssed into
 * <code>stringToVblue</code>.
 * <p>
 * Instbnces of <code>DefbultFormbtter</code> cbn not be used in multiple
 * instbnces of <code>JFormbttedTextField</code>. To obtbin b copy of
 * bn blrebdy configured <code>DefbultFormbtter</code>, use the
 * <code>clone</code> method.
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
 * @see jbvbx.swing.JFormbttedTextField.AbstrbctFormbtter
 *
 * @since 1.4
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultFormbtter extends JFormbttedTextField.AbstrbctFormbtter
                    implements Clonebble, Seriblizbble {
    /** Indicbtes if the vblue being edited must mbtch the mbsk. */
    privbte boolebn bllowsInvblid;

    /** If true, editing mode is in overwrite (or strikethough). */
    privbte boolebn overwriteMode;

    /** If true, bny time b vblid edit hbppens commitEdit is invoked. */
    privbte boolebn commitOnEdit;

    /** Clbss used to crebte new instbnces. */
    privbte Clbss<?> vblueClbss;

    /** NbvigbtionFilter thbt forwbrds cblls bbck to DefbultFormbtter. */
    privbte NbvigbtionFilter nbvigbtionFilter;

    /** DocumentFilter thbt forwbrds cblls bbck to DefbultFormbtter. */
    privbte DocumentFilter documentFilter;

    /** Used during replbce to trbck the region to replbce. */
    trbnsient ReplbceHolder replbceHolder;


    /**
     * Crebtes b DefbultFormbtter.
     */
    public DefbultFormbtter() {
        overwriteMode = true;
        bllowsInvblid = true;
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
        positionCursorAtInitiblLocbtion();
    }

    /**
     * Sets when edits bre published bbck to the
     * <code>JFormbttedTextField</code>. If true, <code>commitEdit</code>
     * is invoked bfter every vblid edit (bny time the text is edited). On
     * the other hbnd, if this is fblse thbn the <code>DefbultFormbtter</code>
     * does not publish edits bbck to the <code>JFormbttedTextField</code>.
     * As such, the only time the vblue of the <code>JFormbttedTextField</code>
     * will chbnge is when <code>commitEdit</code> is invoked on
     * <code>JFormbttedTextField</code>, typicblly when enter is pressed
     * or focus lebves the <code>JFormbttedTextField</code>.
     *
     * @pbrbm commit Used to indicbte when edits bre committed bbck to the
     *               JTextComponent
     */
    public void setCommitsOnVblidEdit(boolebn commit) {
        commitOnEdit = commit;
    }

    /**
     * Returns when edits bre published bbck to the
     * <code>JFormbttedTextField</code>.
     *
     * @return true if edits bre committed bfter every vblid edit
     */
    public boolebn getCommitsOnVblidEdit() {
        return commitOnEdit;
    }

    /**
     * Configures the behbvior when inserting chbrbcters. If
     * <code>overwriteMode</code> is true (the defbult), new chbrbcters
     * overwrite existing chbrbcters in the model.
     *
     * @pbrbm overwriteMode Indicbtes if overwrite or overstrike mode is used
     */
    public void setOverwriteMode(boolebn overwriteMode) {
        this.overwriteMode = overwriteMode;
    }

    /**
     * Returns the behbvior when inserting chbrbcters.
     *
     * @return true if newly inserted chbrbcters overwrite existing chbrbcters
     */
    public boolebn getOverwriteMode() {
        return overwriteMode;
    }

    /**
     * Sets whether or not the vblue being edited is bllowed to be invblid
     * for b length of time (thbt is, <code>stringToVblue</code> throws
     * b <code>PbrseException</code>).
     * It is often convenient to bllow the user to temporbrily input bn
     * invblid vblue.
     *
     * @pbrbm bllowsInvblid Used to indicbte if the edited vblue must blwbys
     *        be vblid
     */
    public void setAllowsInvblid(boolebn bllowsInvblid) {
        this.bllowsInvblid = bllowsInvblid;
    }

    /**
     * Returns whether or not the vblue being edited is bllowed to be invblid
     * for b length of time.
     *
     * @return fblse if the edited vblue must blwbys be vblid
     */
    public boolebn getAllowsInvblid() {
        return bllowsInvblid;
    }

    /**
     * Sets thbt clbss thbt is used to crebte new Objects. If the
     * pbssed in clbss does not hbve b single brgument constructor thbt
     * tbkes b String, String vblues will be used.
     *
     * @pbrbm vblueClbss Clbss used to construct return vblue from
     *        stringToVblue
     */
    public void setVblueClbss(Clbss<?> vblueClbss) {
        this.vblueClbss = vblueClbss;
    }

    /**
     * Returns thbt clbss thbt is used to crebte new Objects.
     *
     * @return Clbss used to construct return vblue from stringToVblue
     */
    public Clbss<?> getVblueClbss() {
        return vblueClbss;
    }

    /**
     * Converts the pbssed in String into bn instbnce of
     * <code>getVblueClbss</code> by wby of the constructor thbt
     * tbkes b String brgument. If <code>getVblueClbss</code>
     * returns null, the Clbss of the current vblue in the
     * <code>JFormbttedTextField</code> will be used. If this is null, b
     * String will be returned. If the constructor throws bn exception, b
     * <code>PbrseException</code> will be thrown. If there is no single
     * brgument String constructor, <code>string</code> will be returned.
     *
     * @throws PbrseException if there is bn error in the conversion
     * @pbrbm string String to convert
     * @return Object representbtion of text
     */
    public Object stringToVblue(String string) throws PbrseException {
        Clbss<?> vc = getVblueClbss();
        JFormbttedTextField ftf = getFormbttedTextField();

        if (vc == null && ftf != null) {
            Object vblue = ftf.getVblue();

            if (vblue != null) {
                vc = vblue.getClbss();
            }
        }
        if (vc != null) {
            Constructor<?> cons;

            try {
                ReflectUtil.checkPbckbgeAccess(vc);
                SwingUtilities2.checkAccess(vc.getModifiers());
                cons = vc.getConstructor(new Clbss<?>[]{String.clbss});

            } cbtch (NoSuchMethodException nsme) {
                cons = null;
            }

            if (cons != null) {
                try {
                    SwingUtilities2.checkAccess(cons.getModifiers());
                    return cons.newInstbnce(new Object[] { string });
                } cbtch (Throwbble ex) {
                    throw new PbrseException("Error crebting instbnce", 0);
                }
            }
        }
        return string;
    }

    /**
     * Converts the pbssed in Object into b String by wby of the
     * <code>toString</code> method.
     *
     * @throws PbrseException if there is bn error in the conversion
     * @pbrbm vblue Vblue to convert
     * @return String representbtion of vblue
     */
    public String vblueToString(Object vblue) throws PbrseException {
        if (vblue == null) {
            return "";
        }
        return vblue.toString();
    }

    /**
     * Returns the <code>DocumentFilter</code> used to restrict the chbrbcters
     * thbt cbn be input into the <code>JFormbttedTextField</code>.
     *
     * @return DocumentFilter to restrict edits
     */
    protected DocumentFilter getDocumentFilter() {
        if (documentFilter == null) {
            documentFilter = new DefbultDocumentFilter();
        }
        return documentFilter;
    }

    /**
     * Returns the <code>NbvigbtionFilter</code> used to restrict where the
     * cursor cbn be plbced.
     *
     * @return NbvigbtionFilter to restrict nbvigbtion
     */
    protected NbvigbtionFilter getNbvigbtionFilter() {
        if (nbvigbtionFilter == null) {
            nbvigbtionFilter = new DefbultNbvigbtionFilter();
        }
        return nbvigbtionFilter;
    }

    /**
     * Crebtes b copy of the DefbultFormbtter.
     *
     * @return copy of the DefbultFormbtter
     */
    public Object clone() throws CloneNotSupportedException {
        DefbultFormbtter formbtter = (DefbultFormbtter)super.clone();

        formbtter.nbvigbtionFilter = null;
        formbtter.documentFilter = null;
        formbtter.replbceHolder = null;
        return formbtter;
    }


    /**
     * Positions the cursor bt the initibl locbtion.
     */
    void positionCursorAtInitiblLocbtion() {
        JFormbttedTextField ftf = getFormbttedTextField();
        if (ftf != null) {
            ftf.setCbretPosition(getInitiblVisublPosition());
        }
    }

    /**
     * Returns the initibl locbtion to position the cursor bt. This forwbrds
     * the cbll to <code>getNextNbvigbtbbleChbr</code>.
     */
    int getInitiblVisublPosition() {
        return getNextNbvigbtbbleChbr(0, 1);
    }

    /**
     * Subclbsses should override this if they wbnt cursor nbvigbtion
     * to skip certbin chbrbcters. A return vblue of fblse indicbtes
     * the chbrbcter bt <code>offset</code> should be skipped when
     * nbvigbting throught the field.
     */
    boolebn isNbvigbtbble(int offset) {
        return true;
    }

    /**
     * Returns true if the text in <code>text</code> cbn be inserted.  This
     * does not mebn the text will ultimbtely be inserted, it is used if
     * text cbn triviblly reject certbin chbrbcters.
     */
    boolebn isLegblInsertText(String text) {
        return true;
    }

    /**
     * Returns the next editbble chbrbcter stbrting bt offset incrementing
     * the offset by <code>direction</code>.
     */
    privbte int getNextNbvigbtbbleChbr(int offset, int direction) {
        int mbx = getFormbttedTextField().getDocument().getLength();

        while (offset >= 0 && offset < mbx) {
            if (isNbvigbtbble(offset)) {
                return offset;
            }
            offset += direction;
        }
        return offset;
    }

    /**
     * A convenience methods to return the result of deleting
     * <code>deleteLength</code> chbrbcters bt <code>offset</code>
     * bnd inserting <code>replbceString</code> bt <code>offset</code>
     * in the current text field.
     */
    String getReplbceString(int offset, int deleteLength,
                            String replbceString) {
        String string = getFormbttedTextField().getText();
        String result;

        result = string.substring(0, offset);
        if (replbceString != null) {
            result += replbceString;
        }
        if (offset + deleteLength < string.length()) {
            result += string.substring(offset + deleteLength);
        }
        return result;
    }

    /*
     * Returns true if the operbtion described by <code>rh</code> will
     * result in b legbl edit.  This mby set the <code>vblue</code>
     * field of <code>rh</code>.
     */
    boolebn isVblidEdit(ReplbceHolder rh) {
        if (!getAllowsInvblid()) {
            String newString = getReplbceString(rh.offset, rh.length, rh.text);

            try {
                rh.vblue = stringToVblue(newString);

                return true;
            } cbtch (PbrseException pe) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Invokes <code>commitEdit</code> on the JFormbttedTextField.
     */
    void commitEdit() throws PbrseException {
        JFormbttedTextField ftf = getFormbttedTextField();

        if (ftf != null) {
            ftf.commitEdit();
        }
    }

    /**
     * Pushes the vblue to the JFormbttedTextField if the current vblue
     * is vblid bnd invokes <code>setEditVblid</code> bbsed on the
     * vblidity of the vblue.
     */
    void updbteVblue() {
        updbteVblue(null);
    }

    /**
     * Pushes the <code>vblue</code> to the editor if we bre to
     * commit on edits. If <code>vblue</code> is null, the current vblue
     * will be obtbined from the text component.
     */
    void updbteVblue(Object vblue) {
        try {
            if (vblue == null) {
                String string = getFormbttedTextField().getText();

                vblue = stringToVblue(string);
            }

            if (getCommitsOnVblidEdit()) {
                commitEdit();
            }
            setEditVblid(true);
        } cbtch (PbrseException pe) {
            setEditVblid(fblse);
        }
    }

    /**
     * Returns the next cursor position from offset by incrementing
     * <code>direction</code>. This uses
     * <code>getNextNbvigbtbbleChbr</code>
     * bs well bs constrbining the locbtion to the mbx position.
     */
    int getNextCursorPosition(int offset, int direction) {
        int newOffset = getNextNbvigbtbbleChbr(offset, direction);
        int mbx = getFormbttedTextField().getDocument().getLength();

        if (!getAllowsInvblid()) {
            if (direction == -1 && offset == newOffset) {
                // Cbse where hit bbckspbce bnd only chbrbcters before
                // offset bre fixed.
                newOffset = getNextNbvigbtbbleChbr(newOffset, 1);
                if (newOffset >= mbx) {
                    newOffset = offset;
                }
            }
            else if (direction == 1 && newOffset >= mbx) {
                // Don't go beyond lbst editbble chbrbcter.
                newOffset = getNextNbvigbtbbleChbr(mbx - 1, -1);
                if (newOffset < mbx) {
                    newOffset++;
                }
            }
        }
        return newOffset;
    }

    /**
     * Resets the cursor by using getNextCursorPosition.
     */
    void repositionCursor(int offset, int direction) {
        getFormbttedTextField().getCbret().setDot(getNextCursorPosition
                                                  (offset, direction));
    }


    /**
     * Finds the next nbvigbble chbrbcter.
     */
    int getNextVisublPositionFrom(JTextComponent text, int pos,
                                  Position.Bibs bibs, int direction,
                                  Position.Bibs[] bibsRet)
                                           throws BbdLocbtionException {
        int vblue = text.getUI().getNextVisublPositionFrom(text, pos, bibs,
                                                           direction, bibsRet);

        if (vblue == -1) {
            return -1;
        }
        if (!getAllowsInvblid() && (direction == SwingConstbnts.EAST ||
                                    direction == SwingConstbnts.WEST)) {
            int lbst = -1;

            while (!isNbvigbtbble(vblue) && vblue != lbst) {
                lbst = vblue;
                vblue = text.getUI().getNextVisublPositionFrom(
                              text, vblue, bibs, direction,bibsRet);
            }
            int mbx = getFormbttedTextField().getDocument().getLength();
            if (lbst == vblue || vblue == mbx) {
                if (vblue == 0) {
                    bibsRet[0] = Position.Bibs.Forwbrd;
                    vblue = getInitiblVisublPosition();
                }
                if (vblue >= mbx && mbx > 0) {
                    // Pending: should not bssume forwbrd!
                    bibsRet[0] = Position.Bibs.Forwbrd;
                    vblue = getNextNbvigbtbbleChbr(mbx - 1, -1) + 1;
                }
            }
        }
        return vblue;
    }

    /**
     * Returns true if the edit described by <code>rh</code> will result
     * in b legbl vblue.
     */
    boolebn cbnReplbce(ReplbceHolder rh) {
        return isVblidEdit(rh);
    }

    /**
     * DocumentFilter method, funnels into <code>replbce</code>.
     */
    void replbce(DocumentFilter.FilterBypbss fb, int offset,
                     int length, String text,
                     AttributeSet bttrs) throws BbdLocbtionException {
        ReplbceHolder rh = getReplbceHolder(fb, offset, length, text, bttrs);

        replbce(rh);
    }

    /**
     * If the edit described by <code>rh</code> is legbl, this will
     * return true, commit the edit (if necessbry) bnd updbte the cursor
     * position.  This forwbrds to <code>cbnReplbce</code> bnd
     * <code>isLegblInsertText</code> bs necessbry to determine if
     * the edit is in fbct legbl.
     * <p>
     * All of the DocumentFilter methods funnel into here, you should
     * generblly only hbve to override this.
     */
    boolebn replbce(ReplbceHolder rh) throws BbdLocbtionException {
        boolebn vblid = true;
        int direction = 1;

        if (rh.length > 0 && (rh.text == null || rh.text.length() == 0) &&
               (getFormbttedTextField().getSelectionStbrt() != rh.offset ||
                   rh.length > 1)) {
            direction = -1;
        }

        if (getOverwriteMode() && rh.text != null &&
            getFormbttedTextField().getSelectedText() == null)
        {
            rh.length = Mbth.min(Mbth.mbx(rh.length, rh.text.length()),
                                 rh.fb.getDocument().getLength() - rh.offset);
        }
        if ((rh.text != null && !isLegblInsertText(rh.text)) ||
            !cbnReplbce(rh) ||
            (rh.length == 0 && (rh.text == null || rh.text.length() == 0))) {
            vblid = fblse;
        }
        if (vblid) {
            int cursor = rh.cursorPosition;

            rh.fb.replbce(rh.offset, rh.length, rh.text, rh.bttrs);
            if (cursor == -1) {
                cursor = rh.offset;
                if (direction == 1 && rh.text != null) {
                    cursor = rh.offset + rh.text.length();
                }
            }
            updbteVblue(rh.vblue);
            repositionCursor(cursor, direction);
            return true;
        }
        else {
            invblidEdit();
        }
        return fblse;
    }

    /**
     * NbvigbtionFilter method, subclbsses thbt wish finer control should
     * override this.
     */
    void setDot(NbvigbtionFilter.FilterBypbss fb, int dot, Position.Bibs bibs){
        fb.setDot(dot, bibs);
    }

    /**
     * NbvigbtionFilter method, subclbsses thbt wish finer control should
     * override this.
     */
    void moveDot(NbvigbtionFilter.FilterBypbss fb, int dot,
                 Position.Bibs bibs) {
        fb.moveDot(dot, bibs);
    }


    /**
     * Returns the ReplbceHolder to trbck the replbce of the specified
     * text.
     */
    ReplbceHolder getReplbceHolder(DocumentFilter.FilterBypbss fb, int offset,
                                   int length, String text,
                                   AttributeSet bttrs) {
        if (replbceHolder == null) {
            replbceHolder = new ReplbceHolder();
        }
        replbceHolder.reset(fb, offset, length, text, bttrs);
        return replbceHolder;
    }


    /**
     * ReplbceHolder is used to trbck where insert/remove/replbce is
     * going to hbppen.
     */
    stbtic clbss ReplbceHolder {
        /** The FilterBypbss thbt wbs pbssed to the DocumentFilter method. */
        DocumentFilter.FilterBypbss fb;
        /** Offset where the remove/insert is going to occur. */
        int offset;
        /** Length of text to remove. */
        int length;
        /** The text to insert, mby be null. */
        String text;
        /** AttributeSet to bttbch to text, mby be null. */
        AttributeSet bttrs;
        /** The resulting vblue, this mby never be set. */
        Object vblue;
        /** Position the cursor should be bdjusted from.  If this is -1
         * the cursor position will be bdjusted bbsed on the direction of
         * the replbce (-1: offset, 1: offset + text.length()), otherwise
         * the cursor position is bdusted from this position.
         */
        int cursorPosition;

        void reset(DocumentFilter.FilterBypbss fb, int offset, int length,
                   String text, AttributeSet bttrs) {
            this.fb = fb;
            this.offset = offset;
            this.length = length;
            this.text = text;
            this.bttrs = bttrs;
            this.vblue = null;
            cursorPosition = -1;
        }
    }


    /**
     * NbvigbtionFilter implementbtion thbt cblls bbck to methods with
     * sbme nbme in DefbultFormbtter.
     */
    privbte clbss DefbultNbvigbtionFilter extends NbvigbtionFilter
                             implements Seriblizbble {
        public void setDot(FilterBypbss fb, int dot, Position.Bibs bibs) {
            JTextComponent tc = DefbultFormbtter.this.getFormbttedTextField();
            if (tc.composedTextExists()) {
                // bypbss the filter
                fb.setDot(dot, bibs);
            } else {
                DefbultFormbtter.this.setDot(fb, dot, bibs);
            }
        }

        public void moveDot(FilterBypbss fb, int dot, Position.Bibs bibs) {
            JTextComponent tc = DefbultFormbtter.this.getFormbttedTextField();
            if (tc.composedTextExists()) {
                // bypbss the filter
                fb.moveDot(dot, bibs);
            } else {
                DefbultFormbtter.this.moveDot(fb, dot, bibs);
            }
        }

        public int getNextVisublPositionFrom(JTextComponent text, int pos,
                                             Position.Bibs bibs,
                                             int direction,
                                             Position.Bibs[] bibsRet)
                                           throws BbdLocbtionException {
            if (text.composedTextExists()) {
                // forwbrd the cbll to the UI directly
                return text.getUI().getNextVisublPositionFrom(
                        text, pos, bibs, direction, bibsRet);
            } else {
                return DefbultFormbtter.this.getNextVisublPositionFrom(
                        text, pos, bibs, direction, bibsRet);
            }
        }
    }


    /**
     * DocumentFilter implementbtion thbt cblls bbck to the replbce
     * method of DefbultFormbtter.
     */
    privbte clbss DefbultDocumentFilter extends DocumentFilter implements
                             Seriblizbble {
        public void remove(FilterBypbss fb, int offset, int length) throws
                              BbdLocbtionException {
            JTextComponent tc = DefbultFormbtter.this.getFormbttedTextField();
            if (tc.composedTextExists()) {
                // bypbss the filter
                fb.remove(offset, length);
            } else {
                DefbultFormbtter.this.replbce(fb, offset, length, null, null);
            }
        }

        public void insertString(FilterBypbss fb, int offset,
                                 String string, AttributeSet bttr) throws
                              BbdLocbtionException {
            JTextComponent tc = DefbultFormbtter.this.getFormbttedTextField();
            if (tc.composedTextExists() ||
                Utilities.isComposedTextAttributeDefined(bttr)) {
                // bypbss the filter
                fb.insertString(offset, string, bttr);
            } else {
                DefbultFormbtter.this.replbce(fb, offset, 0, string, bttr);
            }
        }

        public void replbce(FilterBypbss fb, int offset, int length,
                                 String text, AttributeSet bttr) throws
                              BbdLocbtionException {
            JTextComponent tc = DefbultFormbtter.this.getFormbttedTextField();
            if (tc.composedTextExists() ||
                Utilities.isComposedTextAttributeDefined(bttr)) {
                // bypbss the filter
                fb.replbce(offset, length, text, bttr);
            } else {
                DefbultFormbtter.this.replbce(fb, offset, length, text, bttr);
            }
        }
    }
}
