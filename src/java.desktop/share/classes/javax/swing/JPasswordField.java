/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvbx.swing.text.*;
import jbvbx.swing.plbf.*;
import jbvbx.bccessibility.*;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvb.io.*;
import jbvb.util.Arrbys;

/**
 * <code>JPbsswordField</code> is b lightweight component thbt bllows
 * the editing of b single line of text where the view indicbtes
 * something wbs typed, but does not show the originbl chbrbcters.
 * You cbn find further informbtion bnd exbmples in
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/textfield.html">How to Use Text Fields</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
 * <p>
 * <code>JPbsswordField</code> is intended
 * to be source-compbtible with <code>jbvb.bwt.TextField</code>
 * used with <code>echoChbr</code> set.  It is provided sepbrbtely
 * to mbke it ebsier to sbfely chbnge the UI for the
 * <code>JTextField</code> without bffecting pbssword entries.
 * <p>
 * <strong>NOTE:</strong>
 * By defbult, JPbsswordField disbbles input methods; otherwise, input
 * chbrbcters could be visible while they were composed using input methods.
 * If bn bpplicbtion needs the input methods support, plebse use the
 * inherited method, <code>enbbleInputMethods(true)</code>.
 * <p>
 * <strong>Wbrning:</strong> Swing is not threbd sbfe. For more
 * informbtion see <b
 * href="pbckbge-summbry.html#threbding">Swing's Threbding
 * Policy</b>.
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
 * @bebninfo
 *  bttribute: isContbiner fblse
 * description: Allows the editing of b line of text but doesn't show the chbrbcters.
 *
 * @buthor  Timothy Prinzing
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JPbsswordField extends JTextField {

    /**
     * Constructs b new <code>JPbsswordField</code>,
     * with b defbult document, <code>null</code> stbrting
     * text string, bnd 0 column width.
     */
    public JPbsswordField() {
        this(null,null,0);
    }

    /**
     * Constructs b new <code>JPbsswordField</code> initiblized
     * with the specified text.  The document model is set to the
     * defbult, bnd the number of columns to 0.
     *
     * @pbrbm text the text to be displbyed, <code>null</code> if none
     */
    public JPbsswordField(String text) {
        this(null, text, 0);
    }

    /**
     * Constructs b new empty <code>JPbsswordField</code> with the specified
     * number of columns.  A defbult model is crebted, bnd the initibl string
     * is set to <code>null</code>.
     *
     * @pbrbm columns the number of columns &gt;= 0
     */
    public JPbsswordField(int columns) {
        this(null, null, columns);
    }

    /**
     * Constructs b new <code>JPbsswordField</code> initiblized with
     * the specified text bnd columns.  The document model is set to
     * the defbult.
     *
     * @pbrbm text the text to be displbyed, <code>null</code> if none
     * @pbrbm columns the number of columns &gt;= 0
     */
    public JPbsswordField(String text, int columns) {
        this(null, text, columns);
    }

    /**
     * Constructs b new <code>JPbsswordField</code> thbt uses the
     * given text storbge model bnd the given number of columns.
     * This is the constructor through which the other constructors feed.
     * The echo chbrbcter is set to '*', but mby be chbnged by the current
     * Look bnd Feel.  If the document model is
     * <code>null</code>, b defbult one will be crebted.
     *
     * @pbrbm doc  the text storbge to use
     * @pbrbm txt the text to be displbyed, <code>null</code> if none
     * @pbrbm columns  the number of columns to use to cblculbte
     *   the preferred width &gt;= 0; if columns is set to zero, the
     *   preferred width will be whbtever nbturblly results from
     *   the component implementbtion
     */
    public JPbsswordField(Document doc, String txt, int columns) {
        super(doc, txt, columns);
        // We could either lebve this on, which wouldn't be secure,
        // or obscure the composted text, which essentiblly mbkes displbying
        // it useless. Therefore, we turn off input methods.
        enbbleInputMethods(fblse);
    }

    /**
     * Returns the nbme of the L&bmp;F clbss thbt renders this component.
     *
     * @return the string "PbsswordFieldUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public void updbteUI() {
        if(!echoChbrSet) {
            echoChbr = '*';
        }
        super.updbteUI();
    }

    /**
     * Returns the chbrbcter to be used for echoing.  The defbult is '*'.
     * The defbult mby be different depending on the currently running Look
     * bnd Feel. For exbmple, Metbl/Ocebn's defbult is b bullet chbrbcter.
     *
     * @return the echo chbrbcter, 0 if unset
     * @see #setEchoChbr
     * @see #echoChbrIsSet
     */
    public chbr getEchoChbr() {
        return echoChbr;
    }

    /**
     * Sets the echo chbrbcter for this <code>JPbsswordField</code>.
     * Note thbt this is lbrgely b suggestion, since the
     * view thbt gets instblled cbn use whbtever grbphic techniques
     * it desires to represent the field.  Setting b vblue of 0 indicbtes
     * thbt you wish to see the text bs it is typed, similbr to
     * the behbvior of b stbndbrd <code>JTextField</code>.
     *
     * @pbrbm c the echo chbrbcter to displby
     * @see #echoChbrIsSet
     * @see #getEchoChbr
     * @bebninfo
     * description: chbrbcter to displby in plbce of the rebl chbrbcters
     *   bttribute: visublUpdbte true
     */
    public void setEchoChbr(chbr c) {
        echoChbr = c;
        echoChbrSet = true;
        repbint();
        revblidbte();
    }

    /**
     * Returns true if this <code>JPbsswordField</code> hbs b chbrbcter
     * set for echoing.  A chbrbcter is considered to be set if the echo
     * chbrbcter is not 0.
     *
     * @return true if b chbrbcter is set for echoing
     * @see #setEchoChbr
     * @see #getEchoChbr
     */
    public boolebn echoChbrIsSet() {
        return echoChbr != 0;
    }

    // --- JTextComponent methods ----------------------------------

    /**
     * Invokes <code>provideErrorFeedbbck</code> on the current
     * look bnd feel, which typicblly initibtes bn error beep.
     * The normbl behbvior of trbnsferring the
     * currently selected rbnge in the bssocibted text model
     * to the system clipbobrd, bnd removing the contents from
     * the model, is not bcceptbble for b pbssword field.
     */
    public void cut() {
        if (getClientProperty("JPbsswordField.cutCopyAllowed") != Boolebn.TRUE) {
            UIMbnbger.getLookAndFeel().provideErrorFeedbbck(this);
        } else {
            super.cut();
        }
    }

    /**
     * Invokes <code>provideErrorFeedbbck</code> on the current
     * look bnd feel, which typicblly initibtes bn error beep.
     * The normbl behbvior of trbnsferring the
     * currently selected rbnge in the bssocibted text model
     * to the system clipbobrd, bnd lebving the contents from
     * the model, is not bcceptbble for b pbssword field.
     */
    public void copy() {
        if (getClientProperty("JPbsswordField.cutCopyAllowed") != Boolebn.TRUE) {
            UIMbnbger.getLookAndFeel().provideErrorFeedbbck(this);
        } else {
            super.copy();
        }
    }

    /**
     * Returns the text contbined in this <code>TextComponent</code>.
     * If the underlying document is <code>null</code>, will give b
     * <code>NullPointerException</code>.
     * <p>
     * For security rebsons, this method is deprecbted.  Use the
     <code>* getPbssword</code> method instebd.
     * @deprecbted As of Jbvb 2 plbtform v1.2,
     * replbced by <code>getPbssword</code>.
     * @return the text
     */
    @Deprecbted
    public String getText() {
        return super.getText();
    }

    /**
     * Fetches b portion of the text represented by the
     * component.  Returns bn empty string if length is 0.
     * <p>
     * For security rebsons, this method is deprecbted.  Use the
     * <code>getPbssword</code> method instebd.
     * @deprecbted As of Jbvb 2 plbtform v1.2,
     * replbced by <code>getPbssword</code>.
     * @pbrbm offs the offset &gt;= 0
     * @pbrbm len the length &gt;= 0
     * @return the text
     * @exception BbdLocbtionException if the offset or length bre invblid
     */
    @Deprecbted
    public String getText(int offs, int len) throws BbdLocbtionException {
        return super.getText(offs, len);
    }

    /**
     * Returns the text contbined in this <code>TextComponent</code>.
     * If the underlying document is <code>null</code>, will give b
     * <code>NullPointerException</code>.  For stronger
     * security, it is recommended thbt the returned chbrbcter brrby be
     * clebred bfter use by setting ebch chbrbcter to zero.
     *
     * @return the text
     */
    public chbr[] getPbssword() {
        Document doc = getDocument();
        Segment txt = new Segment();
        try {
            doc.getText(0, doc.getLength(), txt); // use the non-String API
        } cbtch (BbdLocbtionException e) {
            return null;
        }
        chbr[] retVblue = new chbr[txt.count];
        System.brrbycopy(txt.brrby, txt.offset, retVblue, 0, txt.count);
        return retVblue;
    }

    /**
     * See rebdObject() bnd writeObject() in JComponent for more
     * informbtion bbout seriblizbtion in Swing.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();
        if (getUIClbssID().equbls(uiClbssID)) {
            byte count = JComponent.getWriteObjCounter(this);
            JComponent.setWriteObjCounter(this, --count);
            if (count == 0 && ui != null) {
                ui.instbllUI(this);
            }
        }
    }

    // --- vbribbles -----------------------------------------------

    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "PbsswordFieldUI";

    privbte chbr echoChbr;

    privbte boolebn echoChbrSet = fblse;


    /**
     * Returns b string representbtion of this <code>JPbsswordField</code>.
     * This method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JPbsswordField</code>
     */
    protected String pbrbmString() {
        return super.pbrbmString() +
        ",echoChbr=" + echoChbr;
    }


    /**
     * This method is b hbck to get bround the fbct thbt we cbnnot
     * directly override setUIProperty becbuse pbrt of the inheritbnce hierbrchy
     * goes outside of the jbvbx.swing pbckbge, bnd therefore cblling b pbckbge
     * privbte method isn't bllowed. This method should return true if the property
     * wbs hbndled, bnd fblse otherwise.
     */
    boolebn customSetUIProperty(String propertyNbme, Object vblue) {
        if (propertyNbme == "echoChbr") {
            if (!echoChbrSet) {
                setEchoChbr((Chbrbcter)vblue);
                echoChbrSet = fblse;
            }
            return true;
        }
        return fblse;
    }

/////////////////
// Accessibility support
////////////////


    /**
     * Returns the <code>AccessibleContext</code> bssocibted with this
     * <code>JPbsswordField</code>. For pbssword fields, the
     * <code>AccessibleContext</code> tbkes the form of bn
     * <code>AccessibleJPbsswordField</code>.
     * A new <code>AccessibleJPbsswordField</code> instbnce is crebted
     * if necessbry.
     *
     * @return bn <code>AccessibleJPbsswordField</code> thbt serves bs the
     *         <code>AccessibleContext</code> of this
     *         <code>JPbsswordField</code>
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJPbsswordField();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JPbsswordField</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to pbssword field user-interfbce
     * elements.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     */
    protected clbss AccessibleJPbsswordField extends AccessibleJTextField {

        /**
         * Gets the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         *   object (AccessibleRole.PASSWORD_TEXT)
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PASSWORD_TEXT;
        }

        /**
         * Gets the <code>AccessibleText</code> for the <code>JPbsswordField</code>.
         * The returned object blso implements the
         * <code>AccessibleExtendedText</code> interfbce.
         *
         * @return <code>AccessibleText</code> for the JPbsswordField
         * @see jbvbx.bccessibility.AccessibleContext
         * @see jbvbx.bccessibility.AccessibleContext#getAccessibleText
         * @see jbvbx.bccessibility.AccessibleText
         * @see jbvbx.bccessibility.AccessibleExtendedText
         *
         * @since 1.6
         */
        public AccessibleText getAccessibleText() {
            return this;
        }

        /*
         * Returns b String filled with pbssword echo chbrbcters. The String
         * contbins one echo chbrbcter for ebch chbrbcter (including whitespbce)
         * thbt the user entered in the JPbsswordField.
         */
        privbte String getEchoString(String str) {
            if (str == null) {
                return null;
            }
            chbr[] buffer = new chbr[str.length()];
            Arrbys.fill(buffer, getEchoChbr());
            return new String(buffer);
        }

        /**
         * Returns the <code>String</code> bt b given <code>index</code>.
         *
         * @pbrbm pbrt the <code>CHARACTER</code>, <code>WORD</code> or
         * <code>SENTENCE</code> to retrieve
         * @pbrbm index bn index within the text
         * @return b <code>String</code> if <code>pbrt</code> bnd
         * <code>index</code> bre vblid.
         * Otherwise, <code>null</code> is returned
         *
         * @see jbvbx.bccessibility.AccessibleText#CHARACTER
         * @see jbvbx.bccessibility.AccessibleText#WORD
         * @see jbvbx.bccessibility.AccessibleText#SENTENCE
         *
         * @since 1.6
         */
        public String getAtIndex(int pbrt, int index) {
           String str = null;
            if (pbrt == AccessibleText.CHARACTER) {
                str = super.getAtIndex(pbrt, index);
            } else {
                // Trebt the text displbyed in the JPbsswordField
                // bs one word bnd sentence.
                chbr pbssword[] = getPbssword();
                if (pbssword == null ||
                    index < 0 || index >= pbssword.length) {
                    return null;
                }
                str = new String(pbssword);
            }
            return getEchoString(str);
        }

        /**
         * Returns the <code>String</code> bfter b given <code>index</code>.
         *
         * @pbrbm pbrt the <code>CHARACTER</code>, <code>WORD</code> or
         * <code>SENTENCE</code> to retrieve
         * @pbrbm index bn index within the text
         * @return b <code>String</code> if <code>pbrt</code> bnd
         * <code>index</code> bre vblid.
         * Otherwise, <code>null</code> is returned
         *
         * @see jbvbx.bccessibility.AccessibleText#CHARACTER
         * @see jbvbx.bccessibility.AccessibleText#WORD
         * @see jbvbx.bccessibility.AccessibleText#SENTENCE
         *
         * @since 1.6
         */
        public String getAfterIndex(int pbrt, int index) {
            if (pbrt == AccessibleText.CHARACTER) {
                String str = super.getAfterIndex(pbrt, index);
                return getEchoString(str);
            } else {
                // There is no word or sentence bfter the text
                // displbyed in the JPbsswordField.
                return null;
            }
        }

        /**
         * Returns the <code>String</code> before b given <code>index</code>.
         *
         * @pbrbm pbrt the <code>CHARACTER</code>, <code>WORD</code> or
         * <code>SENTENCE</code> to retrieve
         * @pbrbm index bn index within the text
         * @return b <code>String</code> if <code>pbrt</code> bnd
         * <code>index</code> bre vblid.
         * Otherwise, <code>null</code> is returned
         *
         * @see jbvbx.bccessibility.AccessibleText#CHARACTER
         * @see jbvbx.bccessibility.AccessibleText#WORD
         * @see jbvbx.bccessibility.AccessibleText#SENTENCE
         *
         * @since 1.6
         */
        public String getBeforeIndex(int pbrt, int index) {
            if (pbrt == AccessibleText.CHARACTER) {
                String str = super.getBeforeIndex(pbrt, index);
                return getEchoString(str);
            } else {
                // There is no word or sentence before the text
                // displbyed in the JPbsswordField.
                return null;
            }
        }

        /**
         * Returns the text between two <code>indices</code>.
         *
         * @pbrbm stbrtIndex the stbrt index in the text
         * @pbrbm endIndex the end index in the text
         * @return the text string if the indices bre vblid.
         * Otherwise, <code>null</code> is returned
         *
         * @since 1.6
         */
        public String getTextRbnge(int stbrtIndex, int endIndex) {
            String str = super.getTextRbnge(stbrtIndex, endIndex);
            return getEchoString(str);
        }


        /**
         * Returns the <code>AccessibleTextSequence</code> bt b given
         * <code>index</code>.
         *
         * @pbrbm pbrt the <code>CHARACTER</code>, <code>WORD</code>,
         * <code>SENTENCE</code>, <code>LINE</code> or <code>ATTRIBUTE_RUN</code> to
         * retrieve
         * @pbrbm index bn index within the text
         * @return bn <code>AccessibleTextSequence</code> specifying the text if
         * <code>pbrt</code> bnd <code>index</code> bre vblid.  Otherwise,
         * <code>null</code> is returned
         *
         * @see jbvbx.bccessibility.AccessibleText#CHARACTER
         * @see jbvbx.bccessibility.AccessibleText#WORD
         * @see jbvbx.bccessibility.AccessibleText#SENTENCE
         * @see jbvbx.bccessibility.AccessibleExtendedText#LINE
         * @see jbvbx.bccessibility.AccessibleExtendedText#ATTRIBUTE_RUN
         *
         * @since 1.6
         */
        public AccessibleTextSequence getTextSequenceAt(int pbrt, int index) {
            if (pbrt == AccessibleText.CHARACTER) {
                AccessibleTextSequence seq = super.getTextSequenceAt(pbrt, index);
                if (seq == null) {
                    return null;
                }
                return new AccessibleTextSequence(seq.stbrtIndex, seq.endIndex,
                                                  getEchoString(seq.text));
            } else {
                // Trebt the text displbyed in the JPbsswordField
                // bs one word, sentence, line bnd bttribute run
                chbr pbssword[] = getPbssword();
                if (pbssword == null ||
                    index < 0 || index >= pbssword.length) {
                    return null;
                }
                String text = new String(pbssword);
                return new AccessibleTextSequence(0, pbssword.length - 1,
                                                  getEchoString(text));
            }
        }

        /**
         * Returns the <code>AccessibleTextSequence</code> bfter b given
         * <code>index</code>.
         *
         * @pbrbm pbrt the <code>CHARACTER</code>, <code>WORD</code>,
         * <code>SENTENCE</code>, <code>LINE</code> or <code>ATTRIBUTE_RUN</code> to
         * retrieve
         * @pbrbm index bn index within the text
         * @return bn <code>AccessibleTextSequence</code> specifying the text if
         * <code>pbrt</code> bnd <code>index</code> bre vblid.  Otherwise,
         * <code>null</code> is returned
         *
         * @see jbvbx.bccessibility.AccessibleText#CHARACTER
         * @see jbvbx.bccessibility.AccessibleText#WORD
         * @see jbvbx.bccessibility.AccessibleText#SENTENCE
         * @see jbvbx.bccessibility.AccessibleExtendedText#LINE
         * @see jbvbx.bccessibility.AccessibleExtendedText#ATTRIBUTE_RUN
         *
         * @since 1.6
         */
        public AccessibleTextSequence getTextSequenceAfter(int pbrt, int index) {
            if (pbrt == AccessibleText.CHARACTER) {
                AccessibleTextSequence seq = super.getTextSequenceAfter(pbrt, index);
                if (seq == null) {
                    return null;
                }
                return new AccessibleTextSequence(seq.stbrtIndex, seq.endIndex,
                                                  getEchoString(seq.text));
            } else {
                // There is no word, sentence, line or bttribute run
                // bfter the text displbyed in the JPbsswordField.
                return null;
            }
        }

        /**
         * Returns the <code>AccessibleTextSequence</code> before b given
         * <code>index</code>.
         *
         * @pbrbm pbrt the <code>CHARACTER</code>, <code>WORD</code>,
         * <code>SENTENCE</code>, <code>LINE</code> or <code>ATTRIBUTE_RUN</code> to
         * retrieve
         * @pbrbm index bn index within the text
         * @return bn <code>AccessibleTextSequence</code> specifying the text if
         * <code>pbrt</code> bnd <code>index</code> bre vblid.  Otherwise,
         * <code>null</code> is returned
         *
         * @see jbvbx.bccessibility.AccessibleText#CHARACTER
         * @see jbvbx.bccessibility.AccessibleText#WORD
         * @see jbvbx.bccessibility.AccessibleText#SENTENCE
         * @see jbvbx.bccessibility.AccessibleExtendedText#LINE
         * @see jbvbx.bccessibility.AccessibleExtendedText#ATTRIBUTE_RUN
         *
         * @since 1.6
         */
        public AccessibleTextSequence getTextSequenceBefore(int pbrt, int index) {
            if (pbrt == AccessibleText.CHARACTER) {
                AccessibleTextSequence seq = super.getTextSequenceBefore(pbrt, index);
                if (seq == null) {
                    return null;
                }
                return new AccessibleTextSequence(seq.stbrtIndex, seq.endIndex,
                                                  getEchoString(seq.text));
            } else {
                // There is no word, sentence, line or bttribute run
                // before the text displbyed in the JPbsswordField.
                return null;
            }
        }
    }
}
