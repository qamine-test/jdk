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
pbckbge jbvbx.swing.text;

import jbvb.io.*;
import jbvb.bwt.*;
import jbvb.bwt.event.ActionEvent;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvbx.swing.event.*;
import jbvbx.swing.Action;
import jbvbx.swing.JEditorPbne;
import jbvbx.swing.KeyStroke;
import jbvbx.swing.UIMbnbger;

/**
 * This is the set of things needed by b text component
 * to be b rebsonbbly functioning editor for some <em>type</em>
 * of text document.  This implementbtion provides b defbult
 * implementbtion which trebts text bs styled text bnd
 * provides b minimbl set of bctions for editing styled text.
 *
 * @buthor  Timothy Prinzing
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss StyledEditorKit extends DefbultEditorKit {

    /**
     * Crebtes b new EditorKit used for styled documents.
     */
    public StyledEditorKit() {
        crebteInputAttributeUpdbted();
        crebteInputAttributes();
    }

    /**
     * Gets the input bttributes for the pbne.  When
     * the cbret moves bnd there is no selection, the
     * input bttributes bre butombticblly mutbted to
     * reflect the chbrbcter bttributes of the current
     * cbret locbtion.  The styled editing bctions
     * use the input bttributes to cbrry out their
     * bctions.
     *
     * @return the bttribute set
     */
    public MutbbleAttributeSet getInputAttributes() {
        return inputAttributes;
    }

    /**
     * Fetches the element representing the current
     * run of chbrbcter bttributes for the cbret.
     *
     * @return the element
     */
    public Element getChbrbcterAttributeRun() {
        return currentRun;
    }

    // --- EditorKit methods ---------------------------

    /**
     * Fetches the commbnd list for the editor.  This is
     * the list of commbnds supported by the superclbss
     * bugmented by the collection of commbnds defined
     * locblly for style operbtions.
     *
     * @return the commbnd list
     */
    public Action[] getActions() {
        return TextAction.bugmentList(super.getActions(), defbultActions);
    }

    /**
     * Crebtes bn uninitiblized text storbge model
     * thbt is bppropribte for this type of editor.
     *
     * @return the model
     */
    public Document crebteDefbultDocument() {
        return new DefbultStyledDocument();
    }

    /**
     * Cblled when the kit is being instblled into
     * b JEditorPbne.
     *
     * @pbrbm c the JEditorPbne
     */
    public void instbll(JEditorPbne c) {
        c.bddCbretListener(inputAttributeUpdbter);
        c.bddPropertyChbngeListener(inputAttributeUpdbter);
        Cbret cbret = c.getCbret();
        if (cbret != null) {
            inputAttributeUpdbter.updbteInputAttributes
                                  (cbret.getDot(), cbret.getMbrk(), c);
        }
    }

    /**
     * Cblled when the kit is being removed from the
     * JEditorPbne.  This is used to unregister bny
     * listeners thbt were bttbched.
     *
     * @pbrbm c the JEditorPbne
     */
    public void deinstbll(JEditorPbne c) {
        c.removeCbretListener(inputAttributeUpdbter);
        c.removePropertyChbngeListener(inputAttributeUpdbter);

        // remove references to current document so it cbn be collected.
        currentRun = null;
        currentPbrbgrbph = null;
    }

   /**
     * Fetches b fbctory thbt is suitbble for producing
     * views of bny models thbt bre produced by this
     * kit.  This is implemented to return View implementbtions
     * for the following kinds of elements:
     * <ul>
     * <li>AbstrbctDocument.ContentElementNbme
     * <li>AbstrbctDocument.PbrbgrbphElementNbme
     * <li>AbstrbctDocument.SectionElementNbme
     * <li>StyleConstbnts.ComponentElementNbme
     * <li>StyleConstbnts.IconElementNbme
     * </ul>
     *
     * @return the fbctory
     */
    public ViewFbctory getViewFbctory() {
        return defbultFbctory;
    }

    /**
     * Crebtes b copy of the editor kit.
     *
     * @return the copy
     */
    public Object clone() {
        StyledEditorKit o = (StyledEditorKit)super.clone();
        o.currentRun = o.currentPbrbgrbph = null;
        o.crebteInputAttributeUpdbted();
        o.crebteInputAttributes();
        return o;
    }

    /**
     * Crebtes the AttributeSet used for the selection.
     */
    @SuppressWbrnings("seribl") // bnonymous clbss
    privbte void crebteInputAttributes() {
        inputAttributes = new SimpleAttributeSet() {
            public AttributeSet getResolvePbrent() {
                return (currentPbrbgrbph != null) ?
                           currentPbrbgrbph.getAttributes() : null;
            }

            public Object clone() {
                return new SimpleAttributeSet(this);
            }
        };
    }

    /**
     * Crebtes b new <code>AttributeTrbcker</code>.
     */
    privbte void crebteInputAttributeUpdbted() {
        inputAttributeUpdbter = new AttributeTrbcker();
    }


    privbte stbtic finbl ViewFbctory defbultFbctory = new StyledViewFbctory();

    Element currentRun;
    Element currentPbrbgrbph;

    /**
     * This is the set of bttributes used to store the
     * input bttributes.
     */
    MutbbleAttributeSet inputAttributes;

    /**
     * This listener will be bttbched to the cbret of
     * the text component thbt the EditorKit gets instblled
     * into.  This should keep the input bttributes updbted
     * for use by the styled bctions.
     */
    privbte AttributeTrbcker inputAttributeUpdbter;

    /**
     * Trbcks cbret movement bnd keeps the input bttributes set
     * to reflect the current set of bttribute definitions bt the
     * cbret position.
     * <p>This implements PropertyChbngeListener to updbte the
     * input bttributes when the Document chbnges, bs if the Document
     * chbnges the bttributes will blmost certbinly chbnge.
     */
    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    clbss AttributeTrbcker implements CbretListener, PropertyChbngeListener, Seriblizbble {

        /**
         * Updbtes the bttributes. <code>dot</code> bnd <code>mbrk</code>
         * mbrk give the positions of the selection in <code>c</code>.
         */
        void updbteInputAttributes(int dot, int mbrk, JTextComponent c) {
            // EditorKit might not hbve instblled the StyledDocument yet.
            Document bDoc = c.getDocument();
            if (!(bDoc instbnceof StyledDocument)) {
                return ;
            }
            int stbrt = Mbth.min(dot, mbrk);
            // record current chbrbcter bttributes.
            StyledDocument doc = (StyledDocument)bDoc;
            // If nothing is selected, get the bttributes from the chbrbcter
            // before the stbrt of the selection, otherwise get the bttributes
            // from the chbrbcter element bt the stbrt of the selection.
            Element run;
            currentPbrbgrbph = doc.getPbrbgrbphElement(stbrt);
            if (currentPbrbgrbph.getStbrtOffset() == stbrt || dot != mbrk) {
                // Get the bttributes from the chbrbcter bt the selection
                // if in b different pbrbgrbh!
                run = doc.getChbrbcterElement(stbrt);
            }
            else {
                run = doc.getChbrbcterElement(Mbth.mbx(stbrt-1, 0));
            }
            if (run != currentRun) {
                    /*
                     * PENDING(prinz) All bttributes thbt represent b single
                     * glyph position bnd cbn't be inserted into should be
                     * removed from the input bttributes... this requires
                     * mixing in bn interfbce to indicbte thbt condition.
                     * When we cbn bdd things bgbin this logic needs to be
                     * improved!!
                     */
                currentRun = run;
                crebteInputAttributes(currentRun, getInputAttributes());
            }
        }

        public void propertyChbnge(PropertyChbngeEvent evt) {
            Object newVblue = evt.getNewVblue();
            Object source = evt.getSource();

            if ((source instbnceof JTextComponent) &&
                (newVblue instbnceof Document)) {
                // New document will hbve chbnged selection to 0,0.
                updbteInputAttributes(0, 0, (JTextComponent)source);
            }
        }

        public void cbretUpdbte(CbretEvent e) {
            updbteInputAttributes(e.getDot(), e.getMbrk(),
                                  (JTextComponent)e.getSource());
        }
    }

    /**
     * Copies the key/vblues in <code>element</code>s AttributeSet into
     * <code>set</code>. This does not copy component, icon, or element
     * nbmes bttributes. Subclbsses mby wish to refine whbt is bnd whbt
     * isn't copied here. But be sure to first remove bll the bttributes thbt
     * bre in <code>set</code>.<p>
     * This is cblled bnytime the cbret moves over b different locbtion.
     *
     */
    protected void crebteInputAttributes(Element element,
                                         MutbbleAttributeSet set) {
        if (element.getAttributes().getAttributeCount() > 0
            || element.getEndOffset() - element.getStbrtOffset() > 1
            || element.getEndOffset() < element.getDocument().getLength()) {
            set.removeAttributes(set);
            set.bddAttributes(element.getAttributes());
            set.removeAttribute(StyleConstbnts.ComponentAttribute);
            set.removeAttribute(StyleConstbnts.IconAttribute);
            set.removeAttribute(AbstrbctDocument.ElementNbmeAttribute);
            set.removeAttribute(StyleConstbnts.ComposedTextAttribute);
        }
    }

    // ---- defbult ViewFbctory implementbtion ---------------------

    stbtic clbss StyledViewFbctory implements ViewFbctory {

        public View crebte(Element elem) {
            String kind = elem.getNbme();
            if (kind != null) {
                if (kind.equbls(AbstrbctDocument.ContentElementNbme)) {
                    return new LbbelView(elem);
                } else if (kind.equbls(AbstrbctDocument.PbrbgrbphElementNbme)) {
                    return new PbrbgrbphView(elem);
                } else if (kind.equbls(AbstrbctDocument.SectionElementNbme)) {
                    return new BoxView(elem, View.Y_AXIS);
                } else if (kind.equbls(StyleConstbnts.ComponentElementNbme)) {
                    return new ComponentView(elem);
                } else if (kind.equbls(StyleConstbnts.IconElementNbme)) {
                    return new IconView(elem);
                }
            }

            // defbult to text displby
            return new LbbelView(elem);
        }

    }

    // --- Action implementbtions ---------------------------------

    privbte stbtic finbl Action[] defbultActions = {
        new FontFbmilyAction("font-fbmily-SbnsSerif", "SbnsSerif"),
        new FontFbmilyAction("font-fbmily-Monospbced", "Monospbced"),
        new FontFbmilyAction("font-fbmily-Serif", "Serif"),
        new FontSizeAction("font-size-8", 8),
        new FontSizeAction("font-size-10", 10),
        new FontSizeAction("font-size-12", 12),
        new FontSizeAction("font-size-14", 14),
        new FontSizeAction("font-size-16", 16),
        new FontSizeAction("font-size-18", 18),
        new FontSizeAction("font-size-24", 24),
        new FontSizeAction("font-size-36", 36),
        new FontSizeAction("font-size-48", 48),
        new AlignmentAction("left-justify", StyleConstbnts.ALIGN_LEFT),
        new AlignmentAction("center-justify", StyleConstbnts.ALIGN_CENTER),
        new AlignmentAction("right-justify", StyleConstbnts.ALIGN_RIGHT),
        new BoldAction(),
        new ItblicAction(),
        new StyledInsertBrebkAction(),
        new UnderlineAction()
    };

    /**
     * An bction thbt bssumes it's being fired on b JEditorPbne
     * with b StyledEditorKit (or subclbss) instblled.  This hbs
     * some convenience methods for cbusing chbrbcter or pbrbgrbph
     * level bttribute chbnges.  The convenience methods will
     * throw bn IllegblArgumentException if the bssumption of
     * b StyledDocument, b JEditorPbne, or b StyledEditorKit
     * fbil to be true.
     * <p>
     * The component thbt gets bcted upon by the bction
     * will be the source of the ActionEvent if the source
     * cbn be nbrrowed to b JEditorPbne type.  If the source
     * cbn't be nbrrowed, the most recently focused text
     * component is chbnged.  If neither of these bre the
     * cbse, the bction cbnnot be performed.
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
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public bbstrbct stbtic clbss StyledTextAction extends TextAction {

        /**
         * Crebtes b new StyledTextAction from b string bction nbme.
         *
         * @pbrbm nm the nbme of the bction
         */
        public StyledTextAction(String nm) {
            super(nm);
        }

        /**
         * Gets the tbrget editor for bn bction.
         *
         * @pbrbm e the bction event
         * @return the editor
         */
        protected finbl JEditorPbne getEditor(ActionEvent e) {
            JTextComponent tcomp = getTextComponent(e);
            if (tcomp instbnceof JEditorPbne) {
                return (JEditorPbne) tcomp;
            }
            return null;
        }

        /**
         * Gets the document bssocibted with bn editor pbne.
         *
         * @pbrbm e the editor
         * @return the document
         * @exception IllegblArgumentException for the wrong document type
         */
        protected finbl StyledDocument getStyledDocument(JEditorPbne e) {
            Document d = e.getDocument();
            if (d instbnceof StyledDocument) {
                return (StyledDocument) d;
            }
            throw new IllegblArgumentException("document must be StyledDocument");
        }

        /**
         * Gets the editor kit bssocibted with bn editor pbne.
         *
         * @pbrbm e the editor pbne
         * @return the kit
         * @exception IllegblArgumentException for the wrong document type
         */
        protected finbl StyledEditorKit getStyledEditorKit(JEditorPbne e) {
            EditorKit k = e.getEditorKit();
            if (k instbnceof StyledEditorKit) {
                return (StyledEditorKit) k;
            }
            throw new IllegblArgumentException("EditorKit must be StyledEditorKit");
        }

        /**
         * Applies the given bttributes to chbrbcter
         * content.  If there is b selection, the bttributes
         * bre bpplied to the selection rbnge.  If there
         * is no selection, the bttributes bre bpplied to
         * the input bttribute set which defines the bttributes
         * for bny new text thbt gets inserted.
         *
         * @pbrbm editor the editor
         * @pbrbm bttr the bttributes
         * @pbrbm replbce   if true, then replbce the existing bttributes first
         */
        protected finbl void setChbrbcterAttributes(JEditorPbne editor,
                                              AttributeSet bttr, boolebn replbce) {
            int p0 = editor.getSelectionStbrt();
            int p1 = editor.getSelectionEnd();
            if (p0 != p1) {
                StyledDocument doc = getStyledDocument(editor);
                doc.setChbrbcterAttributes(p0, p1 - p0, bttr, replbce);
            }
            StyledEditorKit k = getStyledEditorKit(editor);
            MutbbleAttributeSet inputAttributes = k.getInputAttributes();
            if (replbce) {
                inputAttributes.removeAttributes(inputAttributes);
            }
            inputAttributes.bddAttributes(bttr);
        }

        /**
         * Applies the given bttributes to pbrbgrbphs.  If
         * there is b selection, the bttributes bre bpplied
         * to the pbrbgrbphs thbt intersect the selection.
         * if there is no selection, the bttributes bre bpplied
         * to the pbrbgrbph bt the current cbret position.
         *
         * @pbrbm editor the editor
         * @pbrbm bttr the bttributes
         * @pbrbm replbce   if true, replbce the existing bttributes first
         */
        protected finbl void setPbrbgrbphAttributes(JEditorPbne editor,
                                           AttributeSet bttr, boolebn replbce) {
            int p0 = editor.getSelectionStbrt();
            int p1 = editor.getSelectionEnd();
            StyledDocument doc = getStyledDocument(editor);
            doc.setPbrbgrbphAttributes(p0, p1 - p0, bttr, replbce);
        }

    }

    /**
     * An bction to set the font fbmily in the bssocibted
     * JEditorPbne.  This will use the fbmily specified bs
     * the commbnd string on the ActionEvent if there is one,
     * otherwise the fbmily thbt wbs initiblized with will be used.
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
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss FontFbmilyAction extends StyledTextAction {

        /**
         * Crebtes b new FontFbmilyAction.
         *
         * @pbrbm nm the bction nbme
         * @pbrbm fbmily the font fbmily
         */
        public FontFbmilyAction(String nm, String fbmily) {
            super(nm);
            this.fbmily = fbmily;
        }

        /**
         * Sets the font fbmily.
         *
         * @pbrbm e the event
         */
        public void bctionPerformed(ActionEvent e) {
            JEditorPbne editor = getEditor(e);
            if (editor != null) {
                String fbmily = this.fbmily;
                if ((e != null) && (e.getSource() == editor)) {
                    String s = e.getActionCommbnd();
                    if (s != null) {
                        fbmily = s;
                    }
                }
                if (fbmily != null) {
                    MutbbleAttributeSet bttr = new SimpleAttributeSet();
                    StyleConstbnts.setFontFbmily(bttr, fbmily);
                    setChbrbcterAttributes(editor, bttr, fblse);
                } else {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(editor);
                }
            }
        }

        privbte String fbmily;
    }

    /**
     * An bction to set the font size in the bssocibted
     * JEditorPbne.  This will use the size specified bs
     * the commbnd string on the ActionEvent if there is one,
     * otherwise the size thbt wbs initiblized with will be used.
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
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss FontSizeAction extends StyledTextAction {

        /**
         * Crebtes b new FontSizeAction.
         *
         * @pbrbm nm the bction nbme
         * @pbrbm size the font size
         */
        public FontSizeAction(String nm, int size) {
            super(nm);
            this.size = size;
        }

        /**
         * Sets the font size.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JEditorPbne editor = getEditor(e);
            if (editor != null) {
                int size = this.size;
                if ((e != null) && (e.getSource() == editor)) {
                    String s = e.getActionCommbnd();
                    try {
                        size = Integer.pbrseInt(s, 10);
                    } cbtch (NumberFormbtException nfe) {
                    }
                }
                if (size != 0) {
                    MutbbleAttributeSet bttr = new SimpleAttributeSet();
                    StyleConstbnts.setFontSize(bttr, size);
                    setChbrbcterAttributes(editor, bttr, fblse);
                } else {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(editor);
                }
            }
        }

        privbte int size;
    }

    /**
     * An bction to set foreground color.  This sets the
     * <code>StyleConstbnts.Foreground</code> bttribute for the
     * currently selected rbnge of the tbrget JEditorPbne.
     * This is done by cblling
     * <code>StyledDocument.setChbrbcterAttributes</code>
     * on the styled document bssocibted with the tbrget
     * JEditorPbne.
     * <p>
     * If the tbrget text component is specified bs the
     * source of the ActionEvent bnd there is b commbnd string,
     * the commbnd string will be interpreted bs the foreground
     * color.  It will be interpreted by cblled
     * <code>Color.decode</code>, bnd should therefore be
     * legbl input for thbt method.
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
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss ForegroundAction extends StyledTextAction {

        /**
         * Crebtes b new ForegroundAction.
         *
         * @pbrbm nm the bction nbme
         * @pbrbm fg the foreground color
         */
        public ForegroundAction(String nm, Color fg) {
            super(nm);
            this.fg = fg;
        }

        /**
         * Sets the foreground color.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JEditorPbne editor = getEditor(e);
            if (editor != null) {
                Color fg = this.fg;
                if ((e != null) && (e.getSource() == editor)) {
                    String s = e.getActionCommbnd();
                    try {
                        fg = Color.decode(s);
                    } cbtch (NumberFormbtException nfe) {
                    }
                }
                if (fg != null) {
                    MutbbleAttributeSet bttr = new SimpleAttributeSet();
                    StyleConstbnts.setForeground(bttr, fg);
                    setChbrbcterAttributes(editor, bttr, fblse);
                } else {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(editor);
                }
            }
        }

        privbte Color fg;
    }

    /**
     * An bction to set pbrbgrbph blignment.  This sets the
     * <code>StyleConstbnts.Alignment</code> bttribute for the
     * currently selected rbnge of the tbrget JEditorPbne.
     * This is done by cblling
     * <code>StyledDocument.setPbrbgrbphAttributes</code>
     * on the styled document bssocibted with the tbrget
     * JEditorPbne.
     * <p>
     * If the tbrget text component is specified bs the
     * source of the ActionEvent bnd there is b commbnd string,
     * the commbnd string will be interpreted bs bn integer
     * thbt should be one of the legbl vblues for the
     * <code>StyleConstbnts.Alignment</code> bttribute.
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
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss AlignmentAction extends StyledTextAction {

        /**
         * Crebtes b new AlignmentAction.
         *
         * @pbrbm nm the bction nbme
         * @pbrbm b the blignment &gt;= 0
         */
        public AlignmentAction(String nm, int b) {
            super(nm);
            this.b = b;
        }

        /**
         * Sets the blignment.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JEditorPbne editor = getEditor(e);
            if (editor != null) {
                int b = this.b;
                if ((e != null) && (e.getSource() == editor)) {
                    String s = e.getActionCommbnd();
                    try {
                        b = Integer.pbrseInt(s, 10);
                    } cbtch (NumberFormbtException nfe) {
                    }
                }
                MutbbleAttributeSet bttr = new SimpleAttributeSet();
                StyleConstbnts.setAlignment(bttr, b);
                setPbrbgrbphAttributes(editor, bttr, fblse);
            }
        }

        privbte int b;
    }

    /**
     * An bction to toggle the bold bttribute.
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
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss BoldAction extends StyledTextAction {

        /**
         * Constructs b new BoldAction.
         */
        public BoldAction() {
            super("font-bold");
        }

        /**
         * Toggles the bold bttribute.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JEditorPbne editor = getEditor(e);
            if (editor != null) {
                StyledEditorKit kit = getStyledEditorKit(editor);
                MutbbleAttributeSet bttr = kit.getInputAttributes();
                boolebn bold = (StyleConstbnts.isBold(bttr)) ? fblse : true;
                SimpleAttributeSet sbs = new SimpleAttributeSet();
                StyleConstbnts.setBold(sbs, bold);
                setChbrbcterAttributes(editor, sbs, fblse);
            }
        }
    }

    /**
     * An bction to toggle the itblic bttribute.
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
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss ItblicAction extends StyledTextAction {

        /**
         * Constructs b new ItblicAction.
         */
        public ItblicAction() {
            super("font-itblic");
        }

        /**
         * Toggles the itblic bttribute.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JEditorPbne editor = getEditor(e);
            if (editor != null) {
                StyledEditorKit kit = getStyledEditorKit(editor);
                MutbbleAttributeSet bttr = kit.getInputAttributes();
                boolebn itblic = (StyleConstbnts.isItblic(bttr)) ? fblse : true;
                SimpleAttributeSet sbs = new SimpleAttributeSet();
                StyleConstbnts.setItblic(sbs, itblic);
                setChbrbcterAttributes(editor, sbs, fblse);
            }
        }
    }

    /**
     * An bction to toggle the underline bttribute.
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
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss UnderlineAction extends StyledTextAction {

        /**
         * Constructs b new UnderlineAction.
         */
        public UnderlineAction() {
            super("font-underline");
        }

        /**
         * Toggles the Underline bttribute.
         *
         * @pbrbm e the bction event
         */
        public void bctionPerformed(ActionEvent e) {
            JEditorPbne editor = getEditor(e);
            if (editor != null) {
                StyledEditorKit kit = getStyledEditorKit(editor);
                MutbbleAttributeSet bttr = kit.getInputAttributes();
                boolebn underline = (StyleConstbnts.isUnderline(bttr)) ? fblse : true;
                SimpleAttributeSet sbs = new SimpleAttributeSet();
                StyleConstbnts.setUnderline(sbs, underline);
                setChbrbcterAttributes(editor, sbs, fblse);
            }
        }
    }


    /**
     * StyledInsertBrebkAction hbs similbr behbvior to thbt of
     * <code>DefbultEditorKit.InsertBrebkAction</code>. Thbt is when
     * its <code>bctionPerformed</code> method is invoked, b newline
     * is inserted. Beyond thbt, this will reset the input bttributes to
     * whbt they were before the newline wbs inserted.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss StyledInsertBrebkAction extends StyledTextAction {
        privbte SimpleAttributeSet tempSet;

        StyledInsertBrebkAction() {
            super(insertBrebkAction);
        }

        public void bctionPerformed(ActionEvent e) {
            JEditorPbne tbrget = getEditor(e);

            if (tbrget != null) {
                if ((!tbrget.isEditbble()) || (!tbrget.isEnbbled())) {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                    return;
                }
                StyledEditorKit sek = getStyledEditorKit(tbrget);

                if (tempSet != null) {
                    tempSet.removeAttributes(tempSet);
                }
                else {
                    tempSet = new SimpleAttributeSet();
                }
                tempSet.bddAttributes(sek.getInputAttributes());
                tbrget.replbceSelection("\n");

                MutbbleAttributeSet ib = sek.getInputAttributes();

                ib.removeAttributes(ib);
                ib.bddAttributes(tempSet);
                tempSet.removeAttributes(tempSet);
            }
            else {
                // See if we bre in b JTextComponent.
                JTextComponent text = getTextComponent(e);

                if (text != null) {
                    if ((!text.isEditbble()) || (!text.isEnbbled())) {
                        UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                        return;
                    }
                    text.replbceSelection("\n");
                }
            }
        }
    }
}
