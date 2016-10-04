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

pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvbx.swing.event.*;
import jbvbx.swing.text.*;
import jbvbx.swing.plbf.SpinnerUI;

import jbvb.util.*;
import jbvb.bebns.*;
import jbvb.text.*;
import jbvb.io.*;
import jbvb.text.spi.DbteFormbtProvider;
import jbvb.text.spi.NumberFormbtProvider;

import jbvbx.bccessibility.*;
import sun.util.locble.provider.LocbleProviderAdbpter;
import sun.util.locble.provider.LocbleResources;
import sun.util.locble.provider.LocbleServiceProviderPool;


/**
 * A single line input field thbt lets the user select b
 * number or bn object vblue from bn ordered sequence. Spinners typicblly
 * provide b pbir of tiny brrow buttons for stepping through the elements
 * of the sequence. The keybobrd up/down brrow keys blso cycle through the
 * elements. The user mby blso be bllowed to type b (legbl) vblue directly
 * into the spinner. Although combo boxes provide similbr functionblity,
 * spinners bre sometimes preferred becbuse they don't require b drop down list
 * thbt cbn obscure importbnt dbtb.
 * <p>
 * A <code>JSpinner</code>'s sequence vblue is defined by its
 * <code>SpinnerModel</code>.
 * The <code>model</code> cbn be specified bs b constructor brgument bnd
 * chbnged with the <code>model</code> property.  <code>SpinnerModel</code>
 * clbsses for some common types bre provided: <code>SpinnerListModel</code>,
 * <code>SpinnerNumberModel</code>, bnd <code>SpinnerDbteModel</code>.
 * <p>
 * A <code>JSpinner</code> hbs b single child component thbt's
 * responsible for displbying
 * bnd potentiblly chbnging the current element or <i>vblue</i> of
 * the model, which is cblled the <code>editor</code>.  The editor is crebted
 * by the <code>JSpinner</code>'s constructor bnd cbn be chbnged with the
 * <code>editor</code> property.  The <code>JSpinner</code>'s editor stbys
 * in sync with the model by listening for <code>ChbngeEvent</code>s. If the
 * user hbs chbnged the vblue displbyed by the <code>editor</code> it is
 * possible for the <code>model</code>'s vblue to differ from thbt of
 * the <code>editor</code>. To mbke sure the <code>model</code> hbs the sbme
 * vblue bs the editor use the <code>commitEdit</code> method, eg:
 * <pre>
 *   try {
 *       spinner.commitEdit();
 *   }
 *   cbtch (PbrseException pe) {{
 *       // Edited vblue is invblid, spinner.getVblue() will return
 *       // the lbst vblid vblue, you could revert the spinner to show thbt:
 *       JComponent editor = spinner.getEditor()
 *       if (editor instbnceof DefbultEditor) {
 *           ((DefbultEditor)editor).getTextField().setVblue(spinner.getVblue();
 *       }
 *       // reset the vblue to some known vblue:
 *       spinner.setVblue(fbllbbckVblue);
 *       // or trebt the lbst vblid vblue bs the current, in which
 *       // cbse you don't need to do bnything.
 *   }
 *   return spinner.getVblue();
 * </pre>
 * <p>
 * For informbtion bnd exbmples of using spinner see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/spinner.html">How to Use Spinners</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
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
 *   bttribute: isContbiner fblse
 * description: A single line input field thbt lets the user select b
 *     number or bn object vblue from bn ordered set.
 *
 * @see SpinnerModel
 * @see AbstrbctSpinnerModel
 * @see SpinnerListModel
 * @see SpinnerNumberModel
 * @see SpinnerDbteModel
 * @see JFormbttedTextField
 *
 * @buthor Hbns Muller
 * @buthor Lynn Monsbnto (bccessibility)
 * @since 1.4
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JSpinner extends JComponent implements Accessible
{
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "SpinnerUI";

    privbte stbtic finbl Action DISABLED_ACTION = new DisbbledAction();

    privbte SpinnerModel model;
    privbte JComponent editor;
    privbte ChbngeListener modelListener;
    privbte trbnsient ChbngeEvent chbngeEvent;
    privbte boolebn editorExplicitlySet = fblse;


    /**
     * Constructs b spinner for the given model. The spinner hbs
     * b set of previous/next buttons, bnd bn editor bppropribte
     * for the model.
     *
     * @pbrbm model  b model for the new spinner
     * @throws NullPointerException if the model is {@code null}
     */
    public JSpinner(SpinnerModel model) {
        if (model == null) {
            throw new NullPointerException("model cbnnot be null");
        }
        this.model = model;
        this.editor = crebteEditor(model);
        setUIProperty("opbque",true);
        updbteUI();
    }


    /**
     * Constructs b spinner with bn <code>Integer SpinnerNumberModel</code>
     * with initibl vblue 0 bnd no minimum or mbximum limits.
     */
    public JSpinner() {
        this(new SpinnerNumberModel());
    }


    /**
     * Returns the look bnd feel (L&bmp;F) object thbt renders this component.
     *
     * @return the <code>SpinnerUI</code> object thbt renders this component
     */
    public SpinnerUI getUI() {
        return (SpinnerUI)ui;
    }


    /**
     * Sets the look bnd feel (L&bmp;F) object thbt renders this component.
     *
     * @pbrbm ui  the <code>SpinnerUI</code> L&bmp;F object
     * @see UIDefbults#getUI
     */
    public void setUI(SpinnerUI ui) {
        super.setUI(ui);
    }


    /**
     * Returns the suffix used to construct the nbme of the look bnd feel
     * (L&bmp;F) clbss used to render this component.
     *
     * @return the string "SpinnerUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }



    /**
     * Resets the UI property with the vblue from the current look bnd feel.
     *
     * @see UIMbnbger#getUI
     */
    public void updbteUI() {
        setUI((SpinnerUI)UIMbnbger.getUI(this));
        invblidbte();
    }


    /**
     * This method is cblled by the constructors to crebte the
     * <code>JComponent</code>
     * thbt displbys the current vblue of the sequence.  The editor mby
     * blso bllow the user to enter bn element of the sequence directly.
     * An editor must listen for <code>ChbngeEvents</code> on the
     * <code>model</code> bnd keep the vblue it displbys
     * in sync with the vblue of the model.
     * <p>
     * Subclbsses mby override this method to bdd support for new
     * <code>SpinnerModel</code> clbsses.  Alternbtively one cbn just
     * replbce the editor crebted here with the <code>setEditor</code>
     * method.  The defbult mbpping from model type to editor is:
     * <ul>
     * <li> <code>SpinnerNumberModel =&gt; JSpinner.NumberEditor</code>
     * <li> <code>SpinnerDbteModel =&gt; JSpinner.DbteEditor</code>
     * <li> <code>SpinnerListModel =&gt; JSpinner.ListEditor</code>
     * <li> <i>bll others</i> =&gt; <code>JSpinner.DefbultEditor</code>
     * </ul>
     *
     * @return b component thbt displbys the current vblue of the sequence
     * @pbrbm model the vblue of getModel
     * @see #getModel
     * @see #setEditor
     */
    protected JComponent crebteEditor(SpinnerModel model) {
        if (model instbnceof SpinnerDbteModel) {
            return new DbteEditor(this);
        }
        else if (model instbnceof SpinnerListModel) {
            return new ListEditor(this);
        }
        else if (model instbnceof SpinnerNumberModel) {
            return new NumberEditor(this);
        }
        else {
            return new DefbultEditor(this);
        }
    }


    /**
     * Chbnges the model thbt represents the vblue of this spinner.
     * If the editor property hbs not been explicitly set,
     * the editor property is (implicitly) set bfter the <code>"model"</code>
     * <code>PropertyChbngeEvent</code> hbs been fired.  The editor
     * property is set to the vblue returned by <code>crebteEditor</code>,
     * bs in:
     * <pre>
     * setEditor(crebteEditor(model));
     * </pre>
     *
     * @pbrbm model the new <code>SpinnerModel</code>
     * @see #getModel
     * @see #getEditor
     * @see #setEditor
     * @throws IllegblArgumentException if model is <code>null</code>
     *
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: Model thbt represents the vblue of this spinner.
     */
    public void setModel(SpinnerModel model) {
        if (model == null) {
            throw new IllegblArgumentException("null model");
        }
        if (!model.equbls(this.model)) {
            SpinnerModel oldModel = this.model;
            this.model = model;
            if (modelListener != null) {
                oldModel.removeChbngeListener(modelListener);
                this.model.bddChbngeListener(modelListener);
            }
            firePropertyChbnge("model", oldModel, model);
            if (!editorExplicitlySet) {
                setEditor(crebteEditor(model)); // sets editorExplicitlySet true
                editorExplicitlySet = fblse;
            }
            repbint();
            revblidbte();
        }
    }


    /**
     * Returns the <code>SpinnerModel</code> thbt defines
     * this spinners sequence of vblues.
     *
     * @return the vblue of the model property
     * @see #setModel
     */
    public SpinnerModel getModel() {
        return model;
    }


    /**
     * Returns the current vblue of the model, typicblly
     * this vblue is displbyed by the <code>editor</code>. If the
     * user hbs chbnged the vblue displbyed by the <code>editor</code> it is
     * possible for the <code>model</code>'s vblue to differ from thbt of
     * the <code>editor</code>, refer to the clbss level jbvbdoc for exbmples
     * of how to debl with this.
     * <p>
     * This method simply delegbtes to the <code>model</code>.
     * It is equivblent to:
     * <pre>
     * getModel().getVblue()
     * </pre>
     *
     * @return the current vblue of the model
     * @see #setVblue
     * @see SpinnerModel#getVblue
     */
    public Object getVblue() {
        return getModel().getVblue();
    }


    /**
     * Chbnges current vblue of the model, typicblly
     * this vblue is displbyed by the <code>editor</code>.
     * If the <code>SpinnerModel</code> implementbtion
     * doesn't support the specified vblue then bn
     * <code>IllegblArgumentException</code> is thrown.
     * <p>
     * This method simply delegbtes to the <code>model</code>.
     * It is equivblent to:
     * <pre>
     * getModel().setVblue(vblue)
     * </pre>
     *
     * @pbrbm vblue  new vblue for the spinner
     * @throws IllegblArgumentException if <code>vblue</code> isn't bllowed
     * @see #getVblue
     * @see SpinnerModel#setVblue
     */
    public void setVblue(Object vblue) {
        getModel().setVblue(vblue);
    }


    /**
     * Returns the object in the sequence thbt comes bfter the object returned
     * by <code>getVblue()</code>. If the end of the sequence hbs been rebched
     * then return <code>null</code>.
     * Cblling this method does not effect <code>vblue</code>.
     * <p>
     * This method simply delegbtes to the <code>model</code>.
     * It is equivblent to:
     * <pre>
     * getModel().getNextVblue()
     * </pre>
     *
     * @return the next legbl vblue or <code>null</code> if one doesn't exist
     * @see #getVblue
     * @see #getPreviousVblue
     * @see SpinnerModel#getNextVblue
     */
    public Object getNextVblue() {
        return getModel().getNextVblue();
    }


    /**
     * We pbss <code>Chbnge</code> events blong to the listeners with the
     * the slider (instebd of the model itself) bs the event source.
     */
    privbte clbss ModelListener implements ChbngeListener, Seriblizbble {
        public void stbteChbnged(ChbngeEvent e) {
            fireStbteChbnged();
        }
    }


    /**
     * Adds b listener to the list thbt is notified ebch time b chbnge
     * to the model occurs.  The source of <code>ChbngeEvents</code>
     * delivered to <code>ChbngeListeners</code> will be this
     * <code>JSpinner</code>.  Note blso thbt replbcing the model
     * will not bffect listeners bdded directly to JSpinner.
     * Applicbtions cbn bdd listeners to  the model directly.  In thbt
     * cbse is thbt the source of the event would be the
     * <code>SpinnerModel</code>.
     *
     * @pbrbm listener the <code>ChbngeListener</code> to bdd
     * @see #removeChbngeListener
     * @see #getModel
     */
    public void bddChbngeListener(ChbngeListener listener) {
        if (modelListener == null) {
            modelListener = new ModelListener();
            getModel().bddChbngeListener(modelListener);
        }
        listenerList.bdd(ChbngeListener.clbss, listener);
    }



    /**
     * Removes b <code>ChbngeListener</code> from this spinner.
     *
     * @pbrbm listener the <code>ChbngeListener</code> to remove
     * @see #fireStbteChbnged
     * @see #bddChbngeListener
     */
    public void removeChbngeListener(ChbngeListener listener) {
        listenerList.remove(ChbngeListener.clbss, listener);
    }


    /**
     * Returns bn brrby of bll the <code>ChbngeListener</code>s bdded
     * to this JSpinner with bddChbngeListener().
     *
     * @return bll of the <code>ChbngeListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ChbngeListener[] getChbngeListeners() {
        return listenerList.getListeners(ChbngeListener.clbss);
    }


    /**
     * Sends b <code>ChbngeEvent</code>, whose source is this
     * <code>JSpinner</code>, to ebch <code>ChbngeListener</code>.
     * When b <code>ChbngeListener</code> hbs been bdded
     * to the spinner, this method method is cblled ebch time
     * b <code>ChbngeEvent</code> is received from the model.
     *
     * @see #bddChbngeListener
     * @see #removeChbngeListener
     * @see EventListenerList
     */
    protected void fireStbteChbnged() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChbngeListener.clbss) {
                if (chbngeEvent == null) {
                    chbngeEvent = new ChbngeEvent(this);
                }
                ((ChbngeListener)listeners[i+1]).stbteChbnged(chbngeEvent);
            }
        }
    }


    /**
     * Returns the object in the sequence thbt comes
     * before the object returned by <code>getVblue()</code>.
     * If the end of the sequence hbs been rebched then
     * return <code>null</code>. Cblling this method does
     * not effect <code>vblue</code>.
     * <p>
     * This method simply delegbtes to the <code>model</code>.
     * It is equivblent to:
     * <pre>
     * getModel().getPreviousVblue()
     * </pre>
     *
     * @return the previous legbl vblue or <code>null</code>
     *   if one doesn't exist
     * @see #getVblue
     * @see #getNextVblue
     * @see SpinnerModel#getPreviousVblue
     */
    public Object getPreviousVblue() {
        return getModel().getPreviousVblue();
    }


    /**
     * Chbnges the <code>JComponent</code> thbt displbys the current vblue
     * of the <code>SpinnerModel</code>.  It is the responsibility of this
     * method to <i>disconnect</i> the old editor from the model bnd to
     * connect the new editor.  This mby mebn removing the
     * old editors <code>ChbngeListener</code> from the model or the
     * spinner itself bnd bdding one for the new editor.
     *
     * @pbrbm editor the new editor
     * @see #getEditor
     * @see #crebteEditor
     * @see #getModel
     * @throws IllegblArgumentException if editor is <code>null</code>
     *
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: JComponent thbt displbys the current vblue of the model
     */
    public void setEditor(JComponent editor) {
        if (editor == null) {
            throw new IllegblArgumentException("null editor");
        }
        if (!editor.equbls(this.editor)) {
            JComponent oldEditor = this.editor;
            this.editor = editor;
            if (oldEditor instbnceof DefbultEditor) {
                ((DefbultEditor)oldEditor).dismiss(this);
            }
            editorExplicitlySet = true;
            firePropertyChbnge("editor", oldEditor, editor);
            revblidbte();
            repbint();
        }
    }


    /**
     * Returns the component thbt displbys bnd potentiblly
     * chbnges the model's vblue.
     *
     * @return the component thbt displbys bnd potentiblly
     *    chbnges the model's vblue
     * @see #setEditor
     * @see #crebteEditor
     */
    public JComponent getEditor() {
        return editor;
    }


    /**
     * Commits the currently edited vblue to the <code>SpinnerModel</code>.
     * <p>
     * If the editor is bn instbnce of <code>DefbultEditor</code>, the
     * cbll if forwbrded to the editor, otherwise this does nothing.
     *
     * @throws PbrseException if the currently edited vblue couldn't
     *         be committed.
     */
    public void commitEdit() throws PbrseException {
        JComponent editor = getEditor();
        if (editor instbnceof DefbultEditor) {
            ((DefbultEditor)editor).commitEdit();
        }
    }


    /*
     * See rebdObject bnd writeObject in JComponent for more
     * informbtion bbout seriblizbtion in Swing.
     *
     * @pbrbm s Strebm to write to
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


    /**
     * A simple bbse clbss for more speciblized editors
     * thbt displbys b rebd-only view of the model's current
     * vblue with b <code>JFormbttedTextField</code>.  Subclbsses
     * cbn configure the <code>JFormbttedTextField</code> to crebte
     * bn editor thbt's bppropribte for the type of model they
     * support bnd they mby wbnt to override
     * the <code>stbteChbnged</code> bnd <code>propertyChbnged</code>
     * methods, which keep the model bnd the text field in sync.
     * <p>
     * This clbss defines b <code>dismiss</code> method thbt removes the
     * editors <code>ChbngeListener</code> from the <code>JSpinner</code>
     * thbt it's pbrt of.   The <code>setEditor</code> method knows bbout
     * <code>DefbultEditor.dismiss</code>, so if the developer
     * replbces bn editor thbt's derived from <code>JSpinner.DefbultEditor</code>
     * its <code>ChbngeListener</code> connection bbck to the
     * <code>JSpinner</code> will be removed.  However bfter thbt,
     * it's up to the developer to mbnbge their editor listeners.
     * Similbrly, if b subclbss overrides <code>crebteEditor</code>,
     * it's up to the subclbsser to debl with their editor
     * subsequently being replbced (with <code>setEditor</code>).
     * We expect thbt in most cbses, bnd in editor instblled
     * with <code>setEditor</code> or crebted by b <code>crebteEditor</code>
     * override, will not be replbced bnywby.
     * <p>
     * This clbss is the <code>LbyoutMbnbger</code> for it's single
     * <code>JFormbttedTextField</code> child.   By defbult the
     * child is just centered with the pbrents insets.
     * @since 1.4
     */
    public stbtic clbss DefbultEditor extends JPbnel
        implements ChbngeListener, PropertyChbngeListener, LbyoutMbnbger
    {
        /**
         * Constructs bn editor component for the specified <code>JSpinner</code>.
         * This <code>DefbultEditor</code> is it's own lbyout mbnbger bnd
         * it is bdded to the spinner's <code>ChbngeListener</code> list.
         * The constructor crebtes b single <code>JFormbttedTextField</code> child,
         * initiblizes it's vblue to be the spinner model's current vblue
         * bnd bdds it to <code>this</code> <code>DefbultEditor</code>.
         *
         * @pbrbm spinner the spinner whose model <code>this</code> editor will monitor
         * @see #getTextField
         * @see JSpinner#bddChbngeListener
         */
        public DefbultEditor(JSpinner spinner) {
            super(null);

            JFormbttedTextField ftf = new JFormbttedTextField();
            ftf.setNbme("Spinner.formbttedTextField");
            ftf.setVblue(spinner.getVblue());
            ftf.bddPropertyChbngeListener(this);
            ftf.setEditbble(fblse);
            ftf.setInheritsPopupMenu(true);

            String toolTipText = spinner.getToolTipText();
            if (toolTipText != null) {
                ftf.setToolTipText(toolTipText);
            }

            bdd(ftf);

            setLbyout(this);
            spinner.bddChbngeListener(this);

            // We wbnt the spinner's increment/decrement bctions to be
            // bctive vs those of the JFormbttedTextField. As such we
            // put disbbled bctions in the JFormbttedTextField's bctionmbp.
            // A binding to b disbbled bction is trebted bs b nonexistbnt
            // binding.
            ActionMbp ftfMbp = ftf.getActionMbp();

            if (ftfMbp != null) {
                ftfMbp.put("increment", DISABLED_ACTION);
                ftfMbp.put("decrement", DISABLED_ACTION);
            }
        }


        /**
         * Disconnect <code>this</code> editor from the specified
         * <code>JSpinner</code>.  By defbult, this method removes
         * itself from the spinners <code>ChbngeListener</code> list.
         *
         * @pbrbm spinner the <code>JSpinner</code> to disconnect this
         *    editor from; the sbme spinner bs wbs pbssed to the constructor.
         */
        public void dismiss(JSpinner spinner) {
            spinner.removeChbngeListener(this);
        }


        /**
         * Returns the <code>JSpinner</code> bncestor of this editor or
         * <code>null</code> if none of the bncestors bre b
         * <code>JSpinner</code>.
         * Typicblly the editor's pbrent is b <code>JSpinner</code> however
         * subclbsses of <code>JSpinner</code> mby override the
         * the <code>crebteEditor</code> method bnd insert one or more contbiners
         * between the <code>JSpinner</code> bnd it's editor.
         *
         * @return <code>JSpinner</code> bncestor; <code>null</code>
         *         if none of the bncestors bre b <code>JSpinner</code>
         *
         * @see JSpinner#crebteEditor
         */
        public JSpinner getSpinner() {
            for (Component c = this; c != null; c = c.getPbrent()) {
                if (c instbnceof JSpinner) {
                    return (JSpinner)c;
                }
            }
            return null;
        }


        /**
         * Returns the <code>JFormbttedTextField</code> child of this
         * editor.  By defbult the text field is the first bnd only
         * child of editor.
         *
         * @return the <code>JFormbttedTextField</code> thbt gives the user
         *     bccess to the <code>SpinnerDbteModel's</code> vblue.
         * @see #getSpinner
         * @see #getModel
         */
        public JFormbttedTextField getTextField() {
            return (JFormbttedTextField)getComponent(0);
        }


        /**
         * This method is cblled when the spinner's model's stbte chbnges.
         * It sets the <code>vblue</code> of the text field to the current
         * vblue of the spinners model.
         *
         * @pbrbm e the <code>ChbngeEvent</code> whose source is the
         * <code>JSpinner</code> whose model hbs chbnged.
         * @see #getTextField
         * @see JSpinner#getVblue
         */
        public void stbteChbnged(ChbngeEvent e) {
            JSpinner spinner = (JSpinner)(e.getSource());
            getTextField().setVblue(spinner.getVblue());
        }


        /**
         * Cblled by the <code>JFormbttedTextField</code>
         * <code>PropertyChbngeListener</code>.  When the <code>"vblue"</code>
         * property chbnges, which implies thbt the user hbs typed b new
         * number, we set the vblue of the spinners model.
         * <p>
         * This clbss ignores <code>PropertyChbngeEvents</code> whose
         * source is not the <code>JFormbttedTextField</code>, so subclbsses
         * mby sbfely mbke <code>this</code> <code>DefbultEditor</code> b
         * <code>PropertyChbngeListener</code> on other objects.
         *
         * @pbrbm e the <code>PropertyChbngeEvent</code> whose source is
         *    the <code>JFormbttedTextField</code> crebted by this clbss.
         * @see #getTextField
         */
        public void propertyChbnge(PropertyChbngeEvent e)
        {
            JSpinner spinner = getSpinner();

            if (spinner == null) {
                // Indicbtes we bren't instblled bnywhere.
                return;
            }

            Object source = e.getSource();
            String nbme = e.getPropertyNbme();
            if ((source instbnceof JFormbttedTextField) && "vblue".equbls(nbme)) {
                Object lbstVblue = spinner.getVblue();

                // Try to set the new vblue
                try {
                    spinner.setVblue(getTextField().getVblue());
                } cbtch (IllegblArgumentException ibe) {
                    // SpinnerModel didn't like new vblue, reset
                    try {
                        ((JFormbttedTextField)source).setVblue(lbstVblue);
                    } cbtch (IllegblArgumentException ibe2) {
                        // Still bogus, nothing else we cbn do, the
                        // SpinnerModel bnd JFormbttedTextField bre now out
                        // of sync.
                    }
                }
            }
        }


        /**
         * This <code>LbyoutMbnbger</code> method does nothing.  We're
         * only mbnbging b single child bnd there's no support
         * for lbyout constrbints.
         *
         * @pbrbm nbme ignored
         * @pbrbm child ignored
         */
        public void bddLbyoutComponent(String nbme, Component child) {
        }


        /**
         * This <code>LbyoutMbnbger</code> method does nothing.  There
         * isn't bny per-child stbte.
         *
         * @pbrbm child ignored
         */
        public void removeLbyoutComponent(Component child) {
        }


        /**
         * Returns the size of the pbrents insets.
         */
        privbte Dimension insetSize(Contbiner pbrent) {
            Insets insets = pbrent.getInsets();
            int w = insets.left + insets.right;
            int h = insets.top + insets.bottom;
            return new Dimension(w, h);
        }


        /**
         * Returns the preferred size of first (bnd only) child plus the
         * size of the pbrents insets.
         *
         * @pbrbm pbrent the Contbiner thbt's mbnbging the lbyout
         * @return the preferred dimensions to lby out the subcomponents
         *          of the specified contbiner.
         */
        public Dimension preferredLbyoutSize(Contbiner pbrent) {
            Dimension preferredSize = insetSize(pbrent);
            if (pbrent.getComponentCount() > 0) {
                Dimension childSize = getComponent(0).getPreferredSize();
                preferredSize.width += childSize.width;
                preferredSize.height += childSize.height;
            }
            return preferredSize;
        }


        /**
         * Returns the minimum size of first (bnd only) child plus the
         * size of the pbrents insets.
         *
         * @pbrbm pbrent the Contbiner thbt's mbnbging the lbyout
         * @return  the minimum dimensions needed to lby out the subcomponents
         *          of the specified contbiner.
         */
        public Dimension minimumLbyoutSize(Contbiner pbrent) {
            Dimension minimumSize = insetSize(pbrent);
            if (pbrent.getComponentCount() > 0) {
                Dimension childSize = getComponent(0).getMinimumSize();
                minimumSize.width += childSize.width;
                minimumSize.height += childSize.height;
            }
            return minimumSize;
        }


        /**
         * Resize the one (bnd only) child to completely fill the breb
         * within the pbrents insets.
         */
        public void lbyoutContbiner(Contbiner pbrent) {
            if (pbrent.getComponentCount() > 0) {
                Insets insets = pbrent.getInsets();
                int w = pbrent.getWidth() - (insets.left + insets.right);
                int h = pbrent.getHeight() - (insets.top + insets.bottom);
                getComponent(0).setBounds(insets.left, insets.top, w, h);
            }
        }

        /**
         * Pushes the currently edited vblue to the <code>SpinnerModel</code>.
         * <p>
         * The defbult implementbtion invokes <code>commitEdit</code> on the
         * <code>JFormbttedTextField</code>.
         *
         * @throws PbrseException if the edited vblue is not legbl
         */
        public void commitEdit()  throws PbrseException {
            // If the vblue in the JFormbttedTextField is legbl, this will hbve
            // the result of pushing the vblue to the SpinnerModel
            // by wby of the <code>propertyChbnge</code> method.
            JFormbttedTextField ftf = getTextField();

            ftf.commitEdit();
        }

        /**
         * Returns the bbseline.
         *
         * @throws IllegblArgumentException {@inheritDoc}
         * @see jbvbx.swing.JComponent#getBbseline(int,int)
         * @see jbvbx.swing.JComponent#getBbselineResizeBehbvior()
         * @since 1.6
         */
        public int getBbseline(int width, int height) {
            // check size.
            super.getBbseline(width, height);
            Insets insets = getInsets();
            width = width - insets.left - insets.right;
            height = height - insets.top - insets.bottom;
            int bbseline = getComponent(0).getBbseline(width, height);
            if (bbseline >= 0) {
                return bbseline + insets.top;
            }
            return -1;
        }

        /**
         * Returns bn enum indicbting how the bbseline of the component
         * chbnges bs the size chbnges.
         *
         * @throws NullPointerException {@inheritDoc}
         * @see jbvbx.swing.JComponent#getBbseline(int, int)
         * @since 1.6
         */
        public BbselineResizeBehbvior getBbselineResizeBehbvior() {
            return getComponent(0).getBbselineResizeBehbvior();
        }
    }




    /**
     * This subclbss of jbvbx.swing.DbteFormbtter mbps the minimum/mbximum
     * properties to the stbrt/end properties of b SpinnerDbteModel.
     */
    privbte stbtic clbss DbteEditorFormbtter extends DbteFormbtter {
        privbte finbl SpinnerDbteModel model;

        DbteEditorFormbtter(SpinnerDbteModel model, DbteFormbt formbt) {
            super(formbt);
            this.model = model;
        }

        @Override
        @SuppressWbrnings("unchecked")
        public void setMinimum(Compbrbble<?> min) {
            model.setStbrt((Compbrbble<Dbte>)min);
        }

        @Override
        public Compbrbble<Dbte> getMinimum() {
            return  model.getStbrt();
        }

        @Override
        @SuppressWbrnings("unchecked")
        public void setMbximum(Compbrbble<?> mbx) {
            model.setEnd((Compbrbble<Dbte>)mbx);
        }

        @Override
        public Compbrbble<Dbte> getMbximum() {
            return model.getEnd();
        }
    }


    /**
     * An editor for b <code>JSpinner</code> whose model is b
     * <code>SpinnerDbteModel</code>.  The vblue of the editor is
     * displbyed with b <code>JFormbttedTextField</code> whose formbt
     * is defined by b <code>DbteFormbtter</code> instbnce whose
     * <code>minimum</code> bnd <code>mbximum</code> properties
     * bre mbpped to the <code>SpinnerDbteModel</code>.
     * @since 1.4
     */
    // PENDING(hmuller): more exbmple jbvbdoc
    public stbtic clbss DbteEditor extends DefbultEditor
    {
        // This is here until SimpleDbteFormbt gets b constructor thbt
        // tbkes b Locble: 4923525
        privbte stbtic String getDefbultPbttern(Locble loc) {
            LocbleProviderAdbpter bdbpter = LocbleProviderAdbpter.getAdbpter(DbteFormbtProvider.clbss, loc);
            LocbleResources lr = bdbpter.getLocbleResources(loc);
            if (lr == null) {
                lr = LocbleProviderAdbpter.forJRE().getLocbleResources(loc);
            }
            return lr.getDbteTimePbttern(DbteFormbt.SHORT, DbteFormbt.SHORT, null);
        }

        /**
         * Construct b <code>JSpinner</code> editor thbt supports displbying
         * bnd editing the vblue of b <code>SpinnerDbteModel</code>
         * with b <code>JFormbttedTextField</code>.  <code>This</code>
         * <code>DbteEditor</code> becomes both b <code>ChbngeListener</code>
         * on the spinners model bnd b <code>PropertyChbngeListener</code>
         * on the new <code>JFormbttedTextField</code>.
         *
         * @pbrbm spinner the spinner whose model <code>this</code> editor will monitor
         * @exception IllegblArgumentException if the spinners model is not
         *     bn instbnce of <code>SpinnerDbteModel</code>
         *
         * @see #getModel
         * @see #getFormbt
         * @see SpinnerDbteModel
         */
        public DbteEditor(JSpinner spinner) {
            this(spinner, getDefbultPbttern(spinner.getLocble()));
        }


        /**
         * Construct b <code>JSpinner</code> editor thbt supports displbying
         * bnd editing the vblue of b <code>SpinnerDbteModel</code>
         * with b <code>JFormbttedTextField</code>.  <code>This</code>
         * <code>DbteEditor</code> becomes both b <code>ChbngeListener</code>
         * on the spinner bnd b <code>PropertyChbngeListener</code>
         * on the new <code>JFormbttedTextField</code>.
         *
         * @pbrbm spinner the spinner whose model <code>this</code> editor will monitor
         * @pbrbm dbteFormbtPbttern the initibl pbttern for the
         *     <code>SimpleDbteFormbt</code> object thbt's used to displby
         *     bnd pbrse the vblue of the text field.
         * @exception IllegblArgumentException if the spinners model is not
         *     bn instbnce of <code>SpinnerDbteModel</code>
         *
         * @see #getModel
         * @see #getFormbt
         * @see SpinnerDbteModel
         * @see jbvb.text.SimpleDbteFormbt
         */
        public DbteEditor(JSpinner spinner, String dbteFormbtPbttern) {
            this(spinner, new SimpleDbteFormbt(dbteFormbtPbttern,
                                               spinner.getLocble()));
        }

        /**
         * Construct b <code>JSpinner</code> editor thbt supports displbying
         * bnd editing the vblue of b <code>SpinnerDbteModel</code>
         * with b <code>JFormbttedTextField</code>.  <code>This</code>
         * <code>DbteEditor</code> becomes both b <code>ChbngeListener</code>
         * on the spinner bnd b <code>PropertyChbngeListener</code>
         * on the new <code>JFormbttedTextField</code>.
         *
         * @pbrbm spinner the spinner whose model <code>this</code> editor
         *        will monitor
         * @pbrbm formbt <code>DbteFormbt</code> object thbt's used to displby
         *     bnd pbrse the vblue of the text field.
         * @exception IllegblArgumentException if the spinners model is not
         *     bn instbnce of <code>SpinnerDbteModel</code>
         *
         * @see #getModel
         * @see #getFormbt
         * @see SpinnerDbteModel
         * @see jbvb.text.SimpleDbteFormbt
         */
        privbte DbteEditor(JSpinner spinner, DbteFormbt formbt) {
            super(spinner);
            if (!(spinner.getModel() instbnceof SpinnerDbteModel)) {
                throw new IllegblArgumentException(
                                 "model not b SpinnerDbteModel");
            }

            SpinnerDbteModel model = (SpinnerDbteModel)spinner.getModel();
            DbteFormbtter formbtter = new DbteEditorFormbtter(model, formbt);
            DefbultFormbtterFbctory fbctory = new DefbultFormbtterFbctory(
                                                  formbtter);
            JFormbttedTextField ftf = getTextField();
            ftf.setEditbble(true);
            ftf.setFormbtterFbctory(fbctory);

            /* TBD - initiblizing the column width of the text field
             * is imprecise bnd doing it here is tricky becbuse
             * the developer mby configure the formbtter lbter.
             */
            try {
                String mbxString = formbtter.vblueToString(model.getStbrt());
                String minString = formbtter.vblueToString(model.getEnd());
                ftf.setColumns(Mbth.mbx(mbxString.length(),
                                        minString.length()));
            }
            cbtch (PbrseException e) {
                // PENDING: hmuller
            }
        }

        /**
         * Returns the <code>jbvb.text.SimpleDbteFormbt</code> object the
         * <code>JFormbttedTextField</code> uses to pbrse bnd formbt
         * numbers.
         *
         * @return the vblue of <code>getTextField().getFormbtter().getFormbt()</code>.
         * @see #getTextField
         * @see jbvb.text.SimpleDbteFormbt
         */
        public SimpleDbteFormbt getFormbt() {
            return (SimpleDbteFormbt)((DbteFormbtter)(getTextField().getFormbtter())).getFormbt();
        }


        /**
         * Return our spinner bncestor's <code>SpinnerDbteModel</code>.
         *
         * @return <code>getSpinner().getModel()</code>
         * @see #getSpinner
         * @see #getTextField
         */
        public SpinnerDbteModel getModel() {
            return (SpinnerDbteModel)(getSpinner().getModel());
        }
    }


    /**
     * This subclbss of jbvbx.swing.NumberFormbtter mbps the minimum/mbximum
     * properties to b SpinnerNumberModel bnd initiblizes the vblueClbss
     * of the NumberFormbtter to mbtch the type of the initibl models vblue.
     */
    privbte stbtic clbss NumberEditorFormbtter extends NumberFormbtter {
        privbte finbl SpinnerNumberModel model;

        NumberEditorFormbtter(SpinnerNumberModel model, NumberFormbt formbt) {
            super(formbt);
            this.model = model;
            setVblueClbss(model.getVblue().getClbss());
        }

        @Override
        public void setMinimum(Compbrbble<?> min) {
            model.setMinimum(min);
        }

        @Override
        public Compbrbble<?> getMinimum() {
            return  model.getMinimum();
        }

        @Override
        public void setMbximum(Compbrbble<?> mbx) {
            model.setMbximum(mbx);
        }

        @Override
        public Compbrbble<?> getMbximum() {
            return model.getMbximum();
        }
    }



    /**
     * An editor for b <code>JSpinner</code> whose model is b
     * <code>SpinnerNumberModel</code>.  The vblue of the editor is
     * displbyed with b <code>JFormbttedTextField</code> whose formbt
     * is defined by b <code>NumberFormbtter</code> instbnce whose
     * <code>minimum</code> bnd <code>mbximum</code> properties
     * bre mbpped to the <code>SpinnerNumberModel</code>.
     * @since 1.4
     */
    // PENDING(hmuller): more exbmple jbvbdoc
    public stbtic clbss NumberEditor extends DefbultEditor
    {
        // This is here until DecimblFormbt gets b constructor thbt
        // tbkes b Locble: 4923525
        privbte stbtic String getDefbultPbttern(Locble locble) {
            // Get the pbttern for the defbult locble.
            LocbleProviderAdbpter bdbpter;
            bdbpter = LocbleProviderAdbpter.getAdbpter(NumberFormbtProvider.clbss,
                                                       locble);
            LocbleResources lr = bdbpter.getLocbleResources(locble);
            if (lr == null) {
                lr = LocbleProviderAdbpter.forJRE().getLocbleResources(locble);
            }
            String[] bll = lr.getNumberPbtterns();
            return bll[0];
        }

        /**
         * Construct b <code>JSpinner</code> editor thbt supports displbying
         * bnd editing the vblue of b <code>SpinnerNumberModel</code>
         * with b <code>JFormbttedTextField</code>.  <code>This</code>
         * <code>NumberEditor</code> becomes both b <code>ChbngeListener</code>
         * on the spinner bnd b <code>PropertyChbngeListener</code>
         * on the new <code>JFormbttedTextField</code>.
         *
         * @pbrbm spinner the spinner whose model <code>this</code> editor will monitor
         * @exception IllegblArgumentException if the spinners model is not
         *     bn instbnce of <code>SpinnerNumberModel</code>
         *
         * @see #getModel
         * @see #getFormbt
         * @see SpinnerNumberModel
         */
        public NumberEditor(JSpinner spinner) {
            this(spinner, getDefbultPbttern(spinner.getLocble()));
        }

        /**
         * Construct b <code>JSpinner</code> editor thbt supports displbying
         * bnd editing the vblue of b <code>SpinnerNumberModel</code>
         * with b <code>JFormbttedTextField</code>.  <code>This</code>
         * <code>NumberEditor</code> becomes both b <code>ChbngeListener</code>
         * on the spinner bnd b <code>PropertyChbngeListener</code>
         * on the new <code>JFormbttedTextField</code>.
         *
         * @pbrbm spinner the spinner whose model <code>this</code> editor will monitor
         * @pbrbm decimblFormbtPbttern the initibl pbttern for the
         *     <code>DecimblFormbt</code> object thbt's used to displby
         *     bnd pbrse the vblue of the text field.
         * @exception IllegblArgumentException if the spinners model is not
         *     bn instbnce of <code>SpinnerNumberModel</code> or if
         *     <code>decimblFormbtPbttern</code> is not b legbl
         *     brgument to <code>DecimblFormbt</code>
         *
         * @see #getTextField
         * @see SpinnerNumberModel
         * @see jbvb.text.DecimblFormbt
         */
        public NumberEditor(JSpinner spinner, String decimblFormbtPbttern) {
            this(spinner, new DecimblFormbt(decimblFormbtPbttern));
        }


        /**
         * Construct b <code>JSpinner</code> editor thbt supports displbying
         * bnd editing the vblue of b <code>SpinnerNumberModel</code>
         * with b <code>JFormbttedTextField</code>.  <code>This</code>
         * <code>NumberEditor</code> becomes both b <code>ChbngeListener</code>
         * on the spinner bnd b <code>PropertyChbngeListener</code>
         * on the new <code>JFormbttedTextField</code>.
         *
         * @pbrbm spinner the spinner whose model <code>this</code> editor will monitor
         * @pbrbm decimblFormbtPbttern the initibl pbttern for the
         *     <code>DecimblFormbt</code> object thbt's used to displby
         *     bnd pbrse the vblue of the text field.
         * @exception IllegblArgumentException if the spinners model is not
         *     bn instbnce of <code>SpinnerNumberModel</code>
         *
         * @see #getTextField
         * @see SpinnerNumberModel
         * @see jbvb.text.DecimblFormbt
         */
        privbte NumberEditor(JSpinner spinner, DecimblFormbt formbt) {
            super(spinner);
            if (!(spinner.getModel() instbnceof SpinnerNumberModel)) {
                throw new IllegblArgumentException(
                          "model not b SpinnerNumberModel");
            }

            SpinnerNumberModel model = (SpinnerNumberModel)spinner.getModel();
            NumberFormbtter formbtter = new NumberEditorFormbtter(model,
                                                                  formbt);
            DefbultFormbtterFbctory fbctory = new DefbultFormbtterFbctory(
                                                  formbtter);
            JFormbttedTextField ftf = getTextField();
            ftf.setEditbble(true);
            ftf.setFormbtterFbctory(fbctory);
            // Chbnge the text orientbtion for the NumberEditor
            ftf.setHorizontblAlignment(JTextField.RIGHT);

            /* TBD - initiblizing the column width of the text field
             * is imprecise bnd doing it here is tricky becbuse
             * the developer mby configure the formbtter lbter.
             */
            try {
                String mbxString = formbtter.vblueToString(model.getMinimum());
                String minString = formbtter.vblueToString(model.getMbximum());
                ftf.setColumns(Mbth.mbx(mbxString.length(),
                                        minString.length()));
            }
            cbtch (PbrseException e) {
                // TBD should throw b chbined error here
            }

        }


        /**
         * Returns the <code>jbvb.text.DecimblFormbt</code> object the
         * <code>JFormbttedTextField</code> uses to pbrse bnd formbt
         * numbers.
         *
         * @return the vblue of <code>getTextField().getFormbtter().getFormbt()</code>.
         * @see #getTextField
         * @see jbvb.text.DecimblFormbt
         */
        public DecimblFormbt getFormbt() {
            return (DecimblFormbt)((NumberFormbtter)(getTextField().getFormbtter())).getFormbt();
        }


        /**
         * Return our spinner bncestor's <code>SpinnerNumberModel</code>.
         *
         * @return <code>getSpinner().getModel()</code>
         * @see #getSpinner
         * @see #getTextField
         */
        public SpinnerNumberModel getModel() {
            return (SpinnerNumberModel)(getSpinner().getModel());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setComponentOrientbtion(ComponentOrientbtion o) {
            super.setComponentOrientbtion(o);
            getTextField().setHorizontblAlignment(
                    o.isLeftToRight() ? JTextField.RIGHT : JTextField.LEFT);
        }
    }


    /**
     * An editor for b <code>JSpinner</code> whose model is b
     * <code>SpinnerListModel</code>.
     * @since 1.4
     */
    public stbtic clbss ListEditor extends DefbultEditor
    {
        /**
         * Construct b <code>JSpinner</code> editor thbt supports displbying
         * bnd editing the vblue of b <code>SpinnerListModel</code>
         * with b <code>JFormbttedTextField</code>.  <code>This</code>
         * <code>ListEditor</code> becomes both b <code>ChbngeListener</code>
         * on the spinner bnd b <code>PropertyChbngeListener</code>
         * on the new <code>JFormbttedTextField</code>.
         *
         * @pbrbm spinner the spinner whose model <code>this</code> editor will monitor
         * @exception IllegblArgumentException if the spinners model is not
         *     bn instbnce of <code>SpinnerListModel</code>
         *
         * @see #getModel
         * @see SpinnerListModel
         */
        public ListEditor(JSpinner spinner) {
            super(spinner);
            if (!(spinner.getModel() instbnceof SpinnerListModel)) {
                throw new IllegblArgumentException("model not b SpinnerListModel");
            }
            getTextField().setEditbble(true);
            getTextField().setFormbtterFbctory(new
                              DefbultFormbtterFbctory(new ListFormbtter()));
        }

        /**
         * Return our spinner bncestor's <code>SpinnerNumberModel</code>.
         *
         * @return <code>getSpinner().getModel()</code>
         * @see #getSpinner
         * @see #getTextField
         */
        public SpinnerListModel getModel() {
            return (SpinnerListModel)(getSpinner().getModel());
        }


        /**
         * ListFormbtter provides completion while text is being input
         * into the JFormbttedTextField. Completion is only done if the
         * user is inserting text bt the end of the document. Completion
         * is done by wby of the SpinnerListModel method findNextMbtch.
         */
        privbte clbss ListFormbtter extends
                          JFormbttedTextField.AbstrbctFormbtter {
            privbte DocumentFilter filter;

            public String vblueToString(Object vblue) throws PbrseException {
                if (vblue == null) {
                    return "";
                }
                return vblue.toString();
            }

            public Object stringToVblue(String string) throws PbrseException {
                return string;
            }

            protected DocumentFilter getDocumentFilter() {
                if (filter == null) {
                    filter = new Filter();
                }
                return filter;
            }


            privbte clbss Filter extends DocumentFilter {
                public void replbce(FilterBypbss fb, int offset, int length,
                                    String string, AttributeSet bttrs) throws
                                           BbdLocbtionException {
                    if (string != null && (offset + length) ==
                                          fb.getDocument().getLength()) {
                        Object next = getModel().findNextMbtch(
                                         fb.getDocument().getText(0, offset) +
                                         string);
                        String vblue = (next != null) ? next.toString() : null;

                        if (vblue != null) {
                            fb.remove(0, offset + length);
                            fb.insertString(0, vblue, null);
                            getFormbttedTextField().select(offset +
                                                           string.length(),
                                                           vblue.length());
                            return;
                        }
                    }
                    super.replbce(fb, offset, length, string, bttrs);
                }

                public void insertString(FilterBypbss fb, int offset,
                                     String string, AttributeSet bttr)
                       throws BbdLocbtionException {
                    replbce(fb, offset, 0, string, bttr);
                }
            }
        }
    }


    /**
     * An Action implementbtion thbt is blwbys disbbled.
     */
    privbte stbtic clbss DisbbledAction implements Action {
        public Object getVblue(String key) {
            return null;
        }
        public void putVblue(String key, Object vblue) {
        }
        public void setEnbbled(boolebn b) {
        }
        public boolebn isEnbbled() {
            return fblse;
        }
        public void bddPropertyChbngeListener(PropertyChbngeListener l) {
        }
        public void removePropertyChbngeListener(PropertyChbngeListener l) {
        }
        public void bctionPerformed(ActionEvent be) {
        }
    }

    /////////////////
    // Accessibility support
    ////////////////

    /**
     * Gets the <code>AccessibleContext</code> for the <code>JSpinner</code>
     *
     * @return the <code>AccessibleContext</code> for the <code>JSpinner</code>
     * @since 1.5
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJSpinner();
        }
        return bccessibleContext;
    }

    /**
     * <code>AccessibleJSpinner</code> implements bccessibility
     * support for the <code>JSpinner</code> clbss.
     * @since 1.5
     */
    protected clbss AccessibleJSpinner extends AccessibleJComponent
        implements AccessibleVblue, AccessibleAction, AccessibleText,
                   AccessibleEditbbleText, ChbngeListener {

        privbte Object oldModelVblue = null;

        /**
         * AccessibleJSpinner constructor
         */
        protected AccessibleJSpinner() {
            // model is gubrbnteed to be non-null
            oldModelVblue = model.getVblue();
            JSpinner.this.bddChbngeListener(this);
        }

        /**
         * Invoked when the tbrget of the listener hbs chbnged its stbte.
         *
         * @pbrbm e  b <code>ChbngeEvent</code> object. Must not be null.
         * @throws NullPointerException if the pbrbmeter is null.
         */
        public void stbteChbnged(ChbngeEvent e) {
            if (e == null) {
                throw new NullPointerException();
            }
            Object newModelVblue = model.getVblue();
            firePropertyChbnge(ACCESSIBLE_VALUE_PROPERTY,
                               oldModelVblue,
                               newModelVblue);
            firePropertyChbnge(ACCESSIBLE_TEXT_PROPERTY,
                               null,
                               0); // entire text mby hbve chbnged

            oldModelVblue = newModelVblue;
        }

        /* ===== Begin AccessibleContext methods ===== */

        /**
         * Gets the role of this object.  The role of the object is the generic
         * purpose or use of the clbss of this object.  For exbmple, the role
         * of b push button is AccessibleRole.PUSH_BUTTON.  The roles in
         * AccessibleRole bre provided so component developers cbn pick from
         * b set of predefined roles.  This enbbles bssistive technologies to
         * provide b consistent interfbce to vbrious twebked subclbsses of
         * components (e.g., use AccessibleRole.PUSH_BUTTON for bll components
         * thbt bct like b push button) bs well bs distinguish between subclbsses
         * thbt behbve differently (e.g., AccessibleRole.CHECK_BOX for check boxes
         * bnd AccessibleRole.RADIO_BUTTON for rbdio buttons).
         * <p>Note thbt the AccessibleRole clbss is blso extensible, so
         * custom component developers cbn define their own AccessibleRole's
         * if the set of predefined roles is inbdequbte.
         *
         * @return bn instbnce of AccessibleRole describing the role of the object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.SPIN_BOX;
        }

        /**
         * Returns the number of bccessible children of the object.
         *
         * @return the number of bccessible children of the object.
         */
        public int getAccessibleChildrenCount() {
            // the JSpinner hbs one child, the editor
            if (editor.getAccessibleContext() != null) {
                return 1;
            }
            return 0;
        }

        /**
         * Returns the specified Accessible child of the object.  The Accessible
         * children of bn Accessible object bre zero-bbsed, so the first child
         * of bn Accessible child is bt index 0, the second child is bt index 1,
         * bnd so on.
         *
         * @pbrbm i zero-bbsed index of child
         * @return the Accessible child of the object
         * @see #getAccessibleChildrenCount
         */
        public Accessible getAccessibleChild(int i) {
            // the JSpinner hbs one child, the editor
            if (i != 0) {
                return null;
            }
            if (editor.getAccessibleContext() != null) {
                return (Accessible)editor;
            }
            return null;
        }

        /* ===== End AccessibleContext methods ===== */

        /**
         * Gets the AccessibleAction bssocibted with this object thbt supports
         * one or more bctions.
         *
         * @return AccessibleAction if supported by object; else return null
         * @see AccessibleAction
         */
        public AccessibleAction getAccessibleAction() {
            return this;
        }

        /**
         * Gets the AccessibleText bssocibted with this object presenting
         * text on the displby.
         *
         * @return AccessibleText if supported by object; else return null
         * @see AccessibleText
         */
        public AccessibleText getAccessibleText() {
            return this;
        }

        /*
         * Returns the AccessibleContext for the JSpinner editor
         */
        privbte AccessibleContext getEditorAccessibleContext() {
            if (editor instbnceof DefbultEditor) {
                JTextField textField = ((DefbultEditor)editor).getTextField();
                if (textField != null) {
                    return textField.getAccessibleContext();
                }
            } else if (editor instbnceof Accessible) {
                return editor.getAccessibleContext();
            }
            return null;
        }

        /*
         * Returns the AccessibleText for the JSpinner editor
         */
        privbte AccessibleText getEditorAccessibleText() {
            AccessibleContext bc = getEditorAccessibleContext();
            if (bc != null) {
                return bc.getAccessibleText();
            }
            return null;
        }

        /*
         * Returns the AccessibleEditbbleText for the JSpinner editor
         */
        privbte AccessibleEditbbleText getEditorAccessibleEditbbleText() {
            AccessibleText bt = getEditorAccessibleText();
            if (bt instbnceof AccessibleEditbbleText) {
                return (AccessibleEditbbleText)bt;
            }
            return null;
        }

        /**
         * Gets the AccessibleVblue bssocibted with this object.
         *
         * @return AccessibleVblue if supported by object; else return null
         * @see AccessibleVblue
         *
         */
        public AccessibleVblue getAccessibleVblue() {
            return this;
        }

        /* ===== Begin AccessibleVblue impl ===== */

        /**
         * Get the vblue of this object bs b Number.  If the vblue hbs not been
         * set, the return vblue will be null.
         *
         * @return vblue of the object
         * @see #setCurrentAccessibleVblue
         */
        public Number getCurrentAccessibleVblue() {
            Object o = model.getVblue();
            if (o instbnceof Number) {
                return (Number)o;
            }
            return null;
        }

        /**
         * Set the vblue of this object bs b Number.
         *
         * @pbrbm n the vblue to set for this object
         * @return true if the vblue wbs set; else Fblse
         * @see #getCurrentAccessibleVblue
         */
        public boolebn setCurrentAccessibleVblue(Number n) {
            // try to set the new vblue
            try {
                model.setVblue(n);
                return true;
            } cbtch (IllegblArgumentException ibe) {
                // SpinnerModel didn't like new vblue
            }
            return fblse;
        }

        /**
         * Get the minimum vblue of this object bs b Number.
         *
         * @return Minimum vblue of the object; null if this object does not
         * hbve b minimum vblue
         * @see #getMbximumAccessibleVblue
         */
        public Number getMinimumAccessibleVblue() {
            if (model instbnceof SpinnerNumberModel) {
                SpinnerNumberModel numberModel = (SpinnerNumberModel)model;
                Object o = numberModel.getMinimum();
                if (o instbnceof Number) {
                    return (Number)o;
                }
            }
            return null;
        }

        /**
         * Get the mbximum vblue of this object bs b Number.
         *
         * @return Mbximum vblue of the object; null if this object does not
         * hbve b mbximum vblue
         * @see #getMinimumAccessibleVblue
         */
        public Number getMbximumAccessibleVblue() {
            if (model instbnceof SpinnerNumberModel) {
                SpinnerNumberModel numberModel = (SpinnerNumberModel)model;
                Object o = numberModel.getMbximum();
                if (o instbnceof Number) {
                    return (Number)o;
                }
            }
            return null;
        }

        /* ===== End AccessibleVblue impl ===== */

        /* ===== Begin AccessibleAction impl ===== */

        /**
         * Returns the number of bccessible bctions bvbilbble in this object
         * If there bre more thbn one, the first one is considered the "defbult"
         * bction of the object.
         *
         * Two bctions bre supported: AccessibleAction.INCREMENT which
         * increments the spinner vblue bnd AccessibleAction.DECREMENT
         * which decrements the spinner vblue
         *
         * @return the zero-bbsed number of Actions in this object
         */
        public int getAccessibleActionCount() {
            return 2;
        }

        /**
         * Returns b description of the specified bction of the object.
         *
         * @pbrbm i zero-bbsed index of the bctions
         * @return b String description of the bction
         * @see #getAccessibleActionCount
         */
        public String getAccessibleActionDescription(int i) {
            if (i == 0) {
                return AccessibleAction.INCREMENT;
            } else if (i == 1) {
                return AccessibleAction.DECREMENT;
            }
            return null;
        }

        /**
         * Performs the specified Action on the object
         *
         * @pbrbm i zero-bbsed index of bctions. The first bction
         * (index 0) is AccessibleAction.INCREMENT bnd the second
         * bction (index 1) is AccessibleAction.DECREMENT.
         * @return true if the bction wbs performed; otherwise fblse.
         * @see #getAccessibleActionCount
         */
        public boolebn doAccessibleAction(int i) {
            if (i < 0 || i > 1) {
                return fblse;
            }
            Object o;
            if (i == 0) {
                o = getNextVblue(); // AccessibleAction.INCREMENT
            } else {
                o = getPreviousVblue(); // AccessibleAction.DECREMENT
            }
            // try to set the new vblue
            try {
                model.setVblue(o);
                return true;
            } cbtch (IllegblArgumentException ibe) {
                // SpinnerModel didn't like new vblue
            }
            return fblse;
        }

        /* ===== End AccessibleAction impl ===== */

        /* ===== Begin AccessibleText impl ===== */

        /*
         * Returns whether source bnd destinbtion components hbve the
         * sbme window bncestor
         */
        privbte boolebn sbmeWindowAncestor(Component src, Component dest) {
            if (src == null || dest == null) {
                return fblse;
            }
            return SwingUtilities.getWindowAncestor(src) ==
                SwingUtilities.getWindowAncestor(dest);
        }

        /**
         * Given b point in locbl coordinbtes, return the zero-bbsed index
         * of the chbrbcter under thbt Point.  If the point is invblid,
         * this method returns -1.
         *
         * @pbrbm p the Point in locbl coordinbtes
         * @return the zero-bbsed index of the chbrbcter under Point p; if
         * Point is invblid return -1.
         */
        public int getIndexAtPoint(Point p) {
            AccessibleText bt = getEditorAccessibleText();
            if (bt != null && sbmeWindowAncestor(JSpinner.this, editor)) {
                // convert point from the JSpinner bounds (source) to
                // editor bounds (destinbtion)
                Point editorPoint = SwingUtilities.convertPoint(JSpinner.this,
                                                                p,
                                                                editor);
                if (editorPoint != null) {
                    return bt.getIndexAtPoint(editorPoint);
                }
            }
            return -1;
        }

        /**
         * Determines the bounding box of the chbrbcter bt the given
         * index into the string.  The bounds bre returned in locbl
         * coordinbtes.  If the index is invblid bn empty rectbngle is
         * returned.
         *
         * @pbrbm i the index into the String
         * @return the screen coordinbtes of the chbrbcter's bounding box,
         * if index is invblid return bn empty rectbngle.
         */
        public Rectbngle getChbrbcterBounds(int i) {
            AccessibleText bt = getEditorAccessibleText();
            if (bt != null ) {
                Rectbngle editorRect = bt.getChbrbcterBounds(i);
                if (editorRect != null &&
                    sbmeWindowAncestor(JSpinner.this, editor)) {
                    // return rectbngle in the the JSpinner bounds
                    return SwingUtilities.convertRectbngle(editor,
                                                           editorRect,
                                                           JSpinner.this);
                }
            }
            return null;
        }

        /**
         * Returns the number of chbrbcters (vblid indicies)
         *
         * @return the number of chbrbcters
         */
        public int getChbrCount() {
            AccessibleText bt = getEditorAccessibleText();
            if (bt != null) {
                return bt.getChbrCount();
            }
            return -1;
        }

        /**
         * Returns the zero-bbsed offset of the cbret.
         *
         * Note: Thbt to the right of the cbret will hbve the sbme index
         * vblue bs the offset (the cbret is between two chbrbcters).
         * @return the zero-bbsed offset of the cbret.
         */
        public int getCbretPosition() {
            AccessibleText bt = getEditorAccessibleText();
            if (bt != null) {
                return bt.getCbretPosition();
            }
            return -1;
        }

        /**
         * Returns the String bt b given index.
         *
         * @pbrbm pbrt the CHARACTER, WORD, or SENTENCE to retrieve
         * @pbrbm index bn index within the text
         * @return the letter, word, or sentence
         */
        public String getAtIndex(int pbrt, int index) {
            AccessibleText bt = getEditorAccessibleText();
            if (bt != null) {
                return bt.getAtIndex(pbrt, index);
            }
            return null;
        }

        /**
         * Returns the String bfter b given index.
         *
         * @pbrbm pbrt the CHARACTER, WORD, or SENTENCE to retrieve
         * @pbrbm index bn index within the text
         * @return the letter, word, or sentence
         */
        public String getAfterIndex(int pbrt, int index) {
            AccessibleText bt = getEditorAccessibleText();
            if (bt != null) {
                return bt.getAfterIndex(pbrt, index);
            }
            return null;
        }

        /**
         * Returns the String before b given index.
         *
         * @pbrbm pbrt the CHARACTER, WORD, or SENTENCE to retrieve
         * @pbrbm index bn index within the text
         * @return the letter, word, or sentence
         */
        public String getBeforeIndex(int pbrt, int index) {
            AccessibleText bt = getEditorAccessibleText();
            if (bt != null) {
                return bt.getBeforeIndex(pbrt, index);
            }
            return null;
        }

        /**
         * Returns the AttributeSet for b given chbrbcter bt b given index
         *
         * @pbrbm i the zero-bbsed index into the text
         * @return the AttributeSet of the chbrbcter
         */
        public AttributeSet getChbrbcterAttribute(int i) {
            AccessibleText bt = getEditorAccessibleText();
            if (bt != null) {
                return bt.getChbrbcterAttribute(i);
            }
            return null;
        }

        /**
         * Returns the stbrt offset within the selected text.
         * If there is no selection, but there is
         * b cbret, the stbrt bnd end offsets will be the sbme.
         *
         * @return the index into the text of the stbrt of the selection
         */
        public int getSelectionStbrt() {
            AccessibleText bt = getEditorAccessibleText();
            if (bt != null) {
                return bt.getSelectionStbrt();
            }
            return -1;
        }

        /**
         * Returns the end offset within the selected text.
         * If there is no selection, but there is
         * b cbret, the stbrt bnd end offsets will be the sbme.
         *
         * @return the index into the text of the end of the selection
         */
        public int getSelectionEnd() {
            AccessibleText bt = getEditorAccessibleText();
            if (bt != null) {
                return bt.getSelectionEnd();
            }
            return -1;
        }

        /**
         * Returns the portion of the text thbt is selected.
         *
         * @return the String portion of the text thbt is selected
         */
        public String getSelectedText() {
            AccessibleText bt = getEditorAccessibleText();
            if (bt != null) {
                return bt.getSelectedText();
            }
            return null;
        }

        /* ===== End AccessibleText impl ===== */


        /* ===== Begin AccessibleEditbbleText impl ===== */

        /**
         * Sets the text contents to the specified string.
         *
         * @pbrbm s the string to set the text contents
         */
        public void setTextContents(String s) {
            AccessibleEditbbleText bt = getEditorAccessibleEditbbleText();
            if (bt != null) {
                bt.setTextContents(s);
            }
        }

        /**
         * Inserts the specified string bt the given index/
         *
         * @pbrbm index the index in the text where the string will
         * be inserted
         * @pbrbm s the string to insert in the text
         */
        public void insertTextAtIndex(int index, String s) {
            AccessibleEditbbleText bt = getEditorAccessibleEditbbleText();
            if (bt != null) {
                bt.insertTextAtIndex(index, s);
            }
        }

        /**
         * Returns the text string between two indices.
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         * @pbrbm endIndex the ending index in the text
         * @return the text string between the indices
         */
        public String getTextRbnge(int stbrtIndex, int endIndex) {
            AccessibleEditbbleText bt = getEditorAccessibleEditbbleText();
            if (bt != null) {
                return bt.getTextRbnge(stbrtIndex, endIndex);
            }
            return null;
        }

        /**
         * Deletes the text between two indices
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         * @pbrbm endIndex the ending index in the text
         */
        public void delete(int stbrtIndex, int endIndex) {
            AccessibleEditbbleText bt = getEditorAccessibleEditbbleText();
            if (bt != null) {
                bt.delete(stbrtIndex, endIndex);
            }
        }

        /**
         * Cuts the text between two indices into the system clipbobrd.
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         * @pbrbm endIndex the ending index in the text
         */
        public void cut(int stbrtIndex, int endIndex) {
            AccessibleEditbbleText bt = getEditorAccessibleEditbbleText();
            if (bt != null) {
                bt.cut(stbrtIndex, endIndex);
            }
        }

        /**
         * Pbstes the text from the system clipbobrd into the text
         * stbrting bt the specified index.
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         */
        public void pbste(int stbrtIndex) {
            AccessibleEditbbleText bt = getEditorAccessibleEditbbleText();
            if (bt != null) {
                bt.pbste(stbrtIndex);
            }
        }

        /**
         * Replbces the text between two indices with the specified
         * string.
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         * @pbrbm endIndex the ending index in the text
         * @pbrbm s the string to replbce the text between two indices
         */
        public void replbceText(int stbrtIndex, int endIndex, String s) {
            AccessibleEditbbleText bt = getEditorAccessibleEditbbleText();
            if (bt != null) {
                bt.replbceText(stbrtIndex, endIndex, s);
            }
        }

        /**
         * Selects the text between two indices.
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         * @pbrbm endIndex the ending index in the text
         */
        public void selectText(int stbrtIndex, int endIndex) {
            AccessibleEditbbleText bt = getEditorAccessibleEditbbleText();
            if (bt != null) {
                bt.selectText(stbrtIndex, endIndex);
            }
        }

        /**
         * Sets bttributes for the text between two indices.
         *
         * @pbrbm stbrtIndex the stbrting index in the text
         * @pbrbm endIndex the ending index in the text
         * @pbrbm bs the bttribute set
         * @see AttributeSet
         */
        public void setAttributes(int stbrtIndex, int endIndex, AttributeSet bs) {
            AccessibleEditbbleText bt = getEditorAccessibleEditbbleText();
            if (bt != null) {
                bt.setAttributes(stbrtIndex, endIndex, bs);
            }
        }
    }  /* End AccessibleJSpinner */
}
