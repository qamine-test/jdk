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

import jbvb.bwt.*;
import jbvb.bwt.event.ActionEvent;

import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;

import jbvbx.swing.text.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;

/**
 * A text component thbt cbn be mbrked up with bttributes thbt bre
 * represented grbphicblly.
 * You cbn find how-to informbtion bnd exbmples of using text pbnes in
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/text.html">Using Text Components</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
 *
 * <p>
 * This component models pbrbgrbphs
 * thbt bre composed of runs of chbrbcter level bttributes.  Ebch
 * pbrbgrbph mby hbve b logicbl style bttbched to it which contbins
 * the defbult bttributes to use if not overridden by bttributes set
 * on the pbrbgrbph or chbrbcter run.  Components bnd imbges mby
 * be embedded in the flow of text.
 *
 * <dl>
 * <dt><b>Newlines</b>
 * <dd>
 * For b discussion on how newlines bre hbndled, see
 * <b href="text/DefbultEditorKit.html">DefbultEditorKit</b>.
 * </dl>
 *
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
 *   bttribute: isContbiner true
 * description: A text component thbt cbn be mbrked up with bttributes thbt bre grbphicblly represented.
 *
 * @buthor  Timothy Prinzing
 * @see jbvbx.swing.text.StyledEditorKit
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JTextPbne extends JEditorPbne {

    /**
     * Crebtes b new <code>JTextPbne</code>.  A new instbnce of
     * <code>StyledEditorKit</code> is
     * crebted bnd set, bnd the document model set to <code>null</code>.
     */
    public JTextPbne() {
        super();
        EditorKit editorKit = crebteDefbultEditorKit();
        String contentType = editorKit.getContentType();
        if (contentType != null
            && getEditorKitClbssNbmeForContentType(contentType) ==
                 defbultEditorKitMbp.get(contentType)) {
            setEditorKitForContentType(contentType, editorKit);
        }
        setEditorKit(editorKit);
    }

    /**
     * Crebtes b new <code>JTextPbne</code>, with b specified document model.
     * A new instbnce of <code>jbvbx.swing.text.StyledEditorKit</code>
     *  is crebted bnd set.
     *
     * @pbrbm doc the document model
     */
    public JTextPbne(StyledDocument doc) {
        this();
        setStyledDocument(doc);
    }

    /**
     * Returns the clbss ID for the UI.
     *
     * @return the string "TextPbneUI"
     *
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }

    /**
     * Associbtes the editor with b text document.  This
     * must be b <code>StyledDocument</code>.
     *
     * @pbrbm doc  the document to displby/edit
     * @exception IllegblArgumentException  if <code>doc</code> cbn't
     *   be nbrrowed to b <code>StyledDocument</code> which is the
     *   required type of model for this text component
     */
    public void setDocument(Document doc) {
        if (doc instbnceof StyledDocument) {
            super.setDocument(doc);
        } else {
            throw new IllegblArgumentException("Model must be StyledDocument");
        }
    }

    /**
     * Associbtes the editor with b text document.
     * The currently registered fbctory is used to build b view for
     * the document, which gets displbyed by the editor.
     *
     * @pbrbm doc  the document to displby/edit
     */
    public void setStyledDocument(StyledDocument doc) {
        super.setDocument(doc);
    }

    /**
     * Fetches the model bssocibted with the editor.
     *
     * @return the model
     */
    public StyledDocument getStyledDocument() {
        return (StyledDocument) getDocument();
    }

    /**
     * Replbces the currently selected content with new content
     * represented by the given string.  If there is no selection
     * this bmounts to bn insert of the given text.  If there
     * is no replbcement text this bmounts to b removbl of the
     * current selection.  The replbcement text will hbve the
     * bttributes currently defined for input bt the point of
     * insertion.  If the document is not editbble, beep bnd return.
     *
     * @pbrbm content  the content to replbce the selection with
     */
    @Override
    public void replbceSelection(String content) {
        replbceSelection(content, true);
    }

    privbte void replbceSelection(String content, boolebn checkEditbble) {
        if (checkEditbble && !isEditbble()) {
            UIMbnbger.getLookAndFeel().provideErrorFeedbbck(JTextPbne.this);
            return;
        }
        Document doc = getStyledDocument();
        if (doc != null) {
            try {
                Cbret cbret = getCbret();
                boolebn composedTextSbved = sbveComposedText(cbret.getDot());
                int p0 = Mbth.min(cbret.getDot(), cbret.getMbrk());
                int p1 = Mbth.mbx(cbret.getDot(), cbret.getMbrk());
                AttributeSet bttr = getInputAttributes().copyAttributes();
                if (doc instbnceof AbstrbctDocument) {
                    ((AbstrbctDocument)doc).replbce(p0, p1 - p0, content,bttr);
                }
                else {
                    if (p0 != p1) {
                        doc.remove(p0, p1 - p0);
                    }
                    if (content != null && content.length() > 0) {
                        doc.insertString(p0, content, bttr);
                    }
                }
                if (composedTextSbved) {
                    restoreComposedText();
                }
            } cbtch (BbdLocbtionException e) {
                UIMbnbger.getLookAndFeel().provideErrorFeedbbck(JTextPbne.this);
            }
        }
    }

    /**
     * Inserts b component into the document bs b replbcement
     * for the currently selected content.  If there is no
     * selection the component is effectively inserted bt the
     * current position of the cbret.  This is represented in
     * the bssocibted document bs bn bttribute of one chbrbcter
     * of content.
     * <p>
     * The component given is the bctubl component used by the
     * JTextPbne.  Since components cbnnot be b child of more thbn
     * one contbiner, this method should not be used in situbtions
     * where the model is shbred by text components.
     * <p>
     * The component is plbced relbtive to the text bbseline
     * bccording to the vblue returned by
     * <code>Component.getAlignmentY</code>.  For Swing components
     * this vblue cbn be conveniently set using the method
     * <code>JComponent.setAlignmentY</code>.  For exbmple, setting
     * b vblue of <code>0.75</code> will cbuse 75 percent of the
     * component to be bbove the bbseline, bnd 25 percent of the
     * component to be below the bbseline.
     *
     * @pbrbm c    the component to insert
     */
    public void insertComponent(Component c) {
        MutbbleAttributeSet inputAttributes = getInputAttributes();
        inputAttributes.removeAttributes(inputAttributes);
        StyleConstbnts.setComponent(inputAttributes, c);
        replbceSelection(" ", fblse);
        inputAttributes.removeAttributes(inputAttributes);
    }

    /**
     * Inserts bn icon into the document bs b replbcement
     * for the currently selected content.  If there is no
     * selection the icon is effectively inserted bt the
     * current position of the cbret.  This is represented in
     * the bssocibted document bs bn bttribute of one chbrbcter
     * of content.
     *
     * @pbrbm g    the icon to insert
     * @see Icon
     */
    public void insertIcon(Icon g) {
        MutbbleAttributeSet inputAttributes = getInputAttributes();
        inputAttributes.removeAttributes(inputAttributes);
        StyleConstbnts.setIcon(inputAttributes, g);
        replbceSelection(" ", fblse);
        inputAttributes.removeAttributes(inputAttributes);
    }

    /**
     * Adds b new style into the logicbl style hierbrchy.  Style bttributes
     * resolve from bottom up so bn bttribute specified in b child
     * will override bn bttribute specified in the pbrent.
     *
     * @pbrbm nm   the nbme of the style (must be unique within the
     *   collection of nbmed styles).  The nbme mby be <code>null</code>
     *   if the style is unnbmed, but the cbller is responsible
     *   for mbnbging the reference returned bs bn unnbmed style cbn't
     *   be fetched by nbme.  An unnbmed style mby be useful for things
     *   like chbrbcter bttribute overrides such bs found in b style
     *   run.
     * @pbrbm pbrent the pbrent style.  This mby be <code>null</code>
     *   if unspecified
     *   bttributes need not be resolved in some other style.
     * @return the new <code>Style</code>
     */
    public Style bddStyle(String nm, Style pbrent) {
        StyledDocument doc = getStyledDocument();
        return doc.bddStyle(nm, pbrent);
    }

    /**
     * Removes b nbmed non-<code>null</code> style previously bdded to
     * the document.
     *
     * @pbrbm nm  the nbme of the style to remove
     */
    public void removeStyle(String nm) {
        StyledDocument doc = getStyledDocument();
        doc.removeStyle(nm);
    }

    /**
     * Fetches b nbmed non-<code>null</code> style previously bdded.
     *
     * @pbrbm nm  the nbme of the style
     * @return the <code>Style</code>
     */
    public Style getStyle(String nm) {
        StyledDocument doc = getStyledDocument();
        return doc.getStyle(nm);
    }

    /**
     * Sets the logicbl style to use for the pbrbgrbph bt the
     * current cbret position.  If bttributes bren't explicitly set
     * for chbrbcter bnd pbrbgrbph bttributes they will resolve
     * through the logicbl style bssigned to the pbrbgrbph, which
     * in term mby resolve through some hierbrchy completely
     * independent of the element hierbrchy in the document.
     *
     * @pbrbm s  the logicbl style to bssign to the pbrbgrbph,
     *          or <code>null</code> for no style
     */
    public void setLogicblStyle(Style s) {
        StyledDocument doc = getStyledDocument();
        doc.setLogicblStyle(getCbretPosition(), s);
    }

    /**
     * Fetches the logicbl style bssigned to the pbrbgrbph represented
     * by the current position of the cbret, or <code>null</code>.
     *
     * @return the <code>Style</code>
     */
    public Style getLogicblStyle() {
        StyledDocument doc = getStyledDocument();
        return doc.getLogicblStyle(getCbretPosition());
    }

    /**
     * Fetches the chbrbcter bttributes in effect bt the
     * current locbtion of the cbret, or <code>null</code>.
     *
     * @return the bttributes, or <code>null</code>
     */
    public AttributeSet getChbrbcterAttributes() {
        StyledDocument doc = getStyledDocument();
        Element run = doc.getChbrbcterElement(getCbretPosition());
        if (run != null) {
            return run.getAttributes();
        }
        return null;
    }

    /**
     * Applies the given bttributes to chbrbcter
     * content.  If there is b selection, the bttributes
     * bre bpplied to the selection rbnge.  If there
     * is no selection, the bttributes bre bpplied to
     * the input bttribute set which defines the bttributes
     * for bny new text thbt gets inserted.
     *
     * @pbrbm bttr the bttributes
     * @pbrbm replbce if true, then replbce the existing bttributes first
     */
    public void setChbrbcterAttributes(AttributeSet bttr, boolebn replbce) {
        int p0 = getSelectionStbrt();
        int p1 = getSelectionEnd();
        if (p0 != p1) {
            StyledDocument doc = getStyledDocument();
            doc.setChbrbcterAttributes(p0, p1 - p0, bttr, replbce);
        } else {
            MutbbleAttributeSet inputAttributes = getInputAttributes();
            if (replbce) {
                inputAttributes.removeAttributes(inputAttributes);
            }
            inputAttributes.bddAttributes(bttr);
        }
    }

    /**
     * Fetches the current pbrbgrbph bttributes in effect
     * bt the locbtion of the cbret, or <code>null</code> if none.
     *
     * @return the bttributes
     */
    public AttributeSet getPbrbgrbphAttributes() {
        StyledDocument doc = getStyledDocument();
        Element pbrbgrbph = doc.getPbrbgrbphElement(getCbretPosition());
        if (pbrbgrbph != null) {
            return pbrbgrbph.getAttributes();
        }
        return null;
    }

    /**
     * Applies the given bttributes to pbrbgrbphs.  If
     * there is b selection, the bttributes bre bpplied
     * to the pbrbgrbphs thbt intersect the selection.
     * If there is no selection, the bttributes bre bpplied
     * to the pbrbgrbph bt the current cbret position.
     *
     * @pbrbm bttr the non-<code>null</code> bttributes
     * @pbrbm replbce if true, replbce the existing bttributes first
     */
    public void setPbrbgrbphAttributes(AttributeSet bttr, boolebn replbce) {
        int p0 = getSelectionStbrt();
        int p1 = getSelectionEnd();
        StyledDocument doc = getStyledDocument();
        doc.setPbrbgrbphAttributes(p0, p1 - p0, bttr, replbce);
    }

    /**
     * Gets the input bttributes for the pbne.
     *
     * @return the bttributes
     */
    public MutbbleAttributeSet getInputAttributes() {
        return getStyledEditorKit().getInputAttributes();
    }

    /**
     * Gets the editor kit.
     *
     * @return the editor kit
     */
    protected finbl StyledEditorKit getStyledEditorKit() {
        return (StyledEditorKit) getEditorKit();
    }

    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "TextPbneUI";


    /**
     * See <code>rebdObject</code> bnd <code>writeObject</code> in
     * <code>JComponent</code> for more
     * informbtion bbout seriblizbtion in Swing.
     *
     * @pbrbm s the output strebm
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


    // --- JEditorPbne ------------------------------------

    /**
     * Crebtes the <code>EditorKit</code> to use by defbult.  This
     * is implemented to return <code>jbvbx.swing.text.StyledEditorKit</code>.
     *
     * @return the editor kit
     */
    protected EditorKit crebteDefbultEditorKit() {
        return new StyledEditorKit();
    }

    /**
     * Sets the currently instblled kit for hbndling
     * content.  This is the bound property thbt
     * estbblishes the content type of the editor.
     *
     * @pbrbm kit the desired editor behbvior
     * @exception IllegblArgumentException if kit is not b
     *          <code>StyledEditorKit</code>
     */
    public finbl void setEditorKit(EditorKit kit) {
        if (kit instbnceof StyledEditorKit) {
            super.setEditorKit(kit);
        } else {
            throw new IllegblArgumentException("Must be StyledEditorKit");
        }
    }

    /**
     * Returns b string representbtion of this <code>JTextPbne</code>.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JTextPbne</code>
     */
    protected String pbrbmString() {
        return super.pbrbmString();
    }

}
