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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.text.PbrseException;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.*;

import jbvb.bebns.*;
import jbvb.text.*;
import jbvb.util.*;
import sun.swing.DefbultLookup;


/**
 * The defbult Spinner UI delegbte.
 *
 * @buthor Hbns Muller
 * @since 1.4
 */
public clbss BbsicSpinnerUI extends SpinnerUI
{
    /**
     * The spinner thbt we're b UI delegbte for.  Initiblized by
     * the <code>instbllUI</code> method, bnd reset to null
     * by <code>uninstbllUI</code>.
     *
     * @see #instbllUI
     * @see #uninstbllUI
     */
    protected JSpinner spinner;
    privbte Hbndler hbndler;


    /**
     * The mouse/bction listeners thbt bre bdded to the spinner's
     * brrow buttons.  These listeners bre shbred by bll
     * spinner brrow buttons.
     *
     * @see #crebteNextButton
     * @see #crebtePreviousButton
     */
    privbte stbtic finbl ArrowButtonHbndler nextButtonHbndler = new ArrowButtonHbndler("increment", true);
    privbte stbtic finbl ArrowButtonHbndler previousButtonHbndler = new ArrowButtonHbndler("decrement", fblse);
    privbte PropertyChbngeListener propertyChbngeListener;


    /**
     * Used by the defbult LbyoutMbnbger clbss - SpinnerLbyout for
     * missing (null) editor/nextButton/previousButton children.
     */
    privbte stbtic finbl Dimension zeroSize = new Dimension(0, 0);


    /**
     * Returns b new instbnce of BbsicSpinnerUI.  SpinnerListUI
     * delegbtes bre bllocbted one per JSpinner.
     *
     * @pbrbm c the JSpinner (not used)
     * @see ComponentUI#crebteUI
     * @return b new BbsicSpinnerUI object
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new BbsicSpinnerUI();
    }


    privbte void mbybeAdd(Component c, String s) {
        if (c != null) {
            spinner.bdd(c, s);
        }
    }


    /**
     * Cblls <code>instbllDefbults</code>, <code>instbllListeners</code>,
     * bnd then bdds the components returned by <code>crebteNextButton</code>,
     * <code>crebtePreviousButton</code>, bnd <code>crebteEditor</code>.
     *
     * @pbrbm c the JSpinner
     * @see #instbllDefbults
     * @see #instbllListeners
     * @see #crebteNextButton
     * @see #crebtePreviousButton
     * @see #crebteEditor
     */
    public void instbllUI(JComponent c) {
        this.spinner = (JSpinner)c;
        instbllDefbults();
        instbllListeners();
        mbybeAdd(crebteNextButton(), "Next");
        mbybeAdd(crebtePreviousButton(), "Previous");
        mbybeAdd(crebteEditor(), "Editor");
        updbteEnbbledStbte();
        instbllKeybobrdActions();
    }


    /**
     * Cblls <code>uninstbllDefbults</code>, <code>uninstbllListeners</code>,
     * bnd then removes bll of the spinners children.
     *
     * @pbrbm c the JSpinner (not used)
     */
    public void uninstbllUI(JComponent c) {
        uninstbllDefbults();
        uninstbllListeners();
        this.spinner = null;
        c.removeAll();
    }


    /**
     * Initiblizes <code>PropertyChbngeListener</code> with
     * b shbred object thbt delegbtes interesting PropertyChbngeEvents
     * to protected methods.
     * <p>
     * This method is cblled by <code>instbllUI</code>.
     *
     * @see #replbceEditor
     * @see #uninstbllListeners
     */
    protected void instbllListeners() {
        propertyChbngeListener = crebtePropertyChbngeListener();
        spinner.bddPropertyChbngeListener(propertyChbngeListener);
        if (DefbultLookup.getBoolebn(spinner, this,
            "Spinner.disbbleOnBoundbryVblues", fblse)) {
            spinner.bddChbngeListener(getHbndler());
        }
        JComponent editor = spinner.getEditor();
        if (editor != null && editor instbnceof JSpinner.DefbultEditor) {
            JTextField tf = ((JSpinner.DefbultEditor)editor).getTextField();
            if (tf != null) {
                tf.bddFocusListener(nextButtonHbndler);
                tf.bddFocusListener(previousButtonHbndler);
            }
        }
    }


    /**
     * Removes the <code>PropertyChbngeListener</code> bdded
     * by instbllListeners.
     * <p>
     * This method is cblled by <code>uninstbllUI</code>.
     *
     * @see #instbllListeners
     */
    protected void uninstbllListeners() {
        spinner.removePropertyChbngeListener(propertyChbngeListener);
        spinner.removeChbngeListener(hbndler);
        JComponent editor = spinner.getEditor();
        removeEditorBorderListener(editor);
        if (editor instbnceof JSpinner.DefbultEditor) {
            JTextField tf = ((JSpinner.DefbultEditor)editor).getTextField();
            if (tf != null) {
                tf.removeFocusListener(nextButtonHbndler);
                tf.removeFocusListener(previousButtonHbndler);
            }
        }
        propertyChbngeListener = null;
        hbndler = null;
    }


    /**
     * Initiblize the <code>JSpinner</code> <code>border</code>,
     * <code>foreground</code>, bnd <code>bbckground</code>, properties
     * bbsed on the corresponding "Spinner.*" properties from defbults tbble.
     * The <code>JSpinners</code> lbyout is set to the vblue returned by
     * <code>crebteLbyout</code>.  This method is cblled by <code>instbllUI</code>.
     *
     * @see #uninstbllDefbults
     * @see #instbllUI
     * @see #crebteLbyout
     * @see LookAndFeel#instbllBorder
     * @see LookAndFeel#instbllColors
     */
    protected void instbllDefbults() {
        spinner.setLbyout(crebteLbyout());
        LookAndFeel.instbllBorder(spinner, "Spinner.border");
        LookAndFeel.instbllColorsAndFont(spinner, "Spinner.bbckground", "Spinner.foreground", "Spinner.font");
        LookAndFeel.instbllProperty(spinner, "opbque", Boolebn.TRUE);
    }


    /**
     * Sets the <code>JSpinner's</code> lbyout mbnbger to null.  This
     * method is cblled by <code>uninstbllUI</code>.
     *
     * @see #instbllDefbults
     * @see #uninstbllUI
     */
    protected void uninstbllDefbults() {
        spinner.setLbyout(null);
    }


    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }


    /**
     * Instblls the necessbry listeners on the next button, <code>c</code>,
     * to updbte the <code>JSpinner</code> in response to b user gesture.
     *
     * @pbrbm c Component to instbll the listeners on
     * @throws NullPointerException if <code>c</code> is null.
     * @see #crebteNextButton
     * @since 1.5
     */
    protected void instbllNextButtonListeners(Component c) {
        instbllButtonListeners(c, nextButtonHbndler);
    }

    /**
     * Instblls the necessbry listeners on the previous button, <code>c</code>,
     * to updbte the <code>JSpinner</code> in response to b user gesture.
     *
     * @pbrbm c Component to instbll the listeners on.
     * @throws NullPointerException if <code>c</code> is null.
     * @see #crebtePreviousButton
     * @since 1.5
     */
    protected void instbllPreviousButtonListeners(Component c) {
        instbllButtonListeners(c, previousButtonHbndler);
    }

    privbte void instbllButtonListeners(Component c,
                                        ArrowButtonHbndler hbndler) {
        if (c instbnceof JButton) {
            ((JButton)c).bddActionListener(hbndler);
        }
        c.bddMouseListener(hbndler);
    }

    /**
     * Crebtes b <code>LbyoutMbnbger</code> thbt mbnbges the <code>editor</code>,
     * <code>nextButton</code>, bnd <code>previousButton</code>
     * children of the JSpinner.  These three children must be
     * bdded with b constrbint thbt identifies their role:
     * "Editor", "Next", bnd "Previous". The defbult lbyout mbnbger
     * cbn hbndle the bbsence of bny of these children.
     *
     * @return b LbyoutMbnbger for the editor, next button, bnd previous button.
     * @see #crebteNextButton
     * @see #crebtePreviousButton
     * @see #crebteEditor
     */
    protected LbyoutMbnbger crebteLbyout() {
        return getHbndler();
    }


    /**
     * Crebtes b <code>PropertyChbngeListener</code> thbt cbn be
     * bdded to the JSpinner itself.  Typicblly, this listener
     * will cbll replbceEditor when the "editor" property chbnges,
     * since it's the <code>SpinnerUI's</code> responsibility to
     * bdd the editor to the JSpinner (bnd remove the old one).
     * This method is cblled by <code>instbllListeners</code>.
     *
     * @return A PropertyChbngeListener for the JSpinner itself
     * @see #instbllListeners
     */
    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return getHbndler();
    }


    /**
     * Crebtes b decrement button, i.e. component thbt replbces the spinner
     * vblue with the object returned by <code>spinner.getPreviousVblue</code>.
     * By defbult the <code>previousButton</code> is b {@code JButton}. If the
     * decrement button is not needed this method should return {@code null}.
     *
     * @return b component thbt will replbce the spinner's vblue with the
     *     previous vblue in the sequence, or {@code null}
     * @see #instbllUI
     * @see #crebteNextButton
     * @see #instbllPreviousButtonListeners
     */
    protected Component crebtePreviousButton() {
        Component c = crebteArrowButton(SwingConstbnts.SOUTH);
        c.setNbme("Spinner.previousButton");
        instbllPreviousButtonListeners(c);
        return c;
    }


    /**
     * Crebtes bn increment button, i.e. component thbt replbces the spinner
     * vblue with the object returned by <code>spinner.getNextVblue</code>.
     * By defbult the <code>nextButton</code> is b {@code JButton}. If the
     * increment button is not needed this method should return {@code null}.
     *
     * @return b component thbt will replbce the spinner's vblue with the
     *     next vblue in the sequence, or {@code null}
     * @see #instbllUI
     * @see #crebtePreviousButton
     * @see #instbllNextButtonListeners
     */
    protected Component crebteNextButton() {
        Component c = crebteArrowButton(SwingConstbnts.NORTH);
        c.setNbme("Spinner.nextButton");
        instbllNextButtonListeners(c);
        return c;
    }

    privbte Component crebteArrowButton(int direction) {
        JButton b = new BbsicArrowButton(direction);
        Border buttonBorder = UIMbnbger.getBorder("Spinner.brrowButtonBorder");
        if (buttonBorder instbnceof UIResource) {
            // Wrbp the border to bvoid hbving the UIResource be replbced by
            // the ButtonUI. This is the opposite of using BorderUIResource.
            b.setBorder(new CompoundBorder(buttonBorder, null));
        } else {
            b.setBorder(buttonBorder);
        }
        b.setInheritsPopupMenu(true);
        return b;
    }


    /**
     * This method is cblled by instbllUI to get the editor component
     * of the <code>JSpinner</code>.  By defbult it just returns
     * <code>JSpinner.getEditor()</code>.  Subclbsses cbn override
     * <code>crebteEditor</code> to return b component thbt contbins
     * the spinner's editor or null, if they're going to hbndle bdding
     * the editor to the <code>JSpinner</code> in bn
     * <code>instbllUI</code> override.
     * <p>
     * Typicblly this method would be overridden to wrbp the editor
     * with b contbiner with b custom border, since one cbn't bssume
     * thbt the editors border cbn be set directly.
     * <p>
     * The <code>replbceEditor</code> method is cblled when the spinners
     * editor is chbnged with <code>JSpinner.setEditor</code>.  If you've
     * overriden this method, then you'll probbbly wbnt to override
     * <code>replbceEditor</code> bs well.
     *
     * @return the JSpinners editor JComponent, spinner.getEditor() by defbult
     * @see #instbllUI
     * @see #replbceEditor
     * @see JSpinner#getEditor
     */
    protected JComponent crebteEditor() {
        JComponent editor = spinner.getEditor();
        mbybeRemoveEditorBorder(editor);
        instbllEditorBorderListener(editor);
        editor.setInheritsPopupMenu(true);
        updbteEditorAlignment(editor);
        return editor;
    }


    /**
     * Cblled by the <code>PropertyChbngeListener</code> when the
     * <code>JSpinner</code> editor property chbnges.  It's the responsibility
     * of this method to remove the old editor bnd bdd the new one.  By
     * defbult this operbtion is just:
     * <pre>
     * spinner.remove(oldEditor);
     * spinner.bdd(newEditor, "Editor");
     * </pre>
     * The implementbtion of <code>replbceEditor</code> should be coordinbted
     * with the <code>crebteEditor</code> method.
     *
     * @pbrbm oldEditor bn old instbnce of editor
     * @pbrbm newEditor b new instbnce of editor
     * @see #crebteEditor
     * @see #crebtePropertyChbngeListener
     */
    protected void replbceEditor(JComponent oldEditor, JComponent newEditor) {
        spinner.remove(oldEditor);
        mbybeRemoveEditorBorder(newEditor);
        instbllEditorBorderListener(newEditor);
        newEditor.setInheritsPopupMenu(true);
        spinner.bdd(newEditor, "Editor");
    }

    privbte void updbteEditorAlignment(JComponent editor) {
        if (editor instbnceof JSpinner.DefbultEditor) {
            // if editor blignment isn't set in LAF, we get 0 (CENTER) here
            int blignment = UIMbnbger.getInt("Spinner.editorAlignment");
            JTextField text = ((JSpinner.DefbultEditor)editor).getTextField();
            text.setHorizontblAlignment(blignment);
        }
    }

    /**
     * Remove the border bround the inner editor component for LbFs
     * thbt instbll bn outside border bround the spinner,
     */
    privbte void mbybeRemoveEditorBorder(JComponent editor) {
        if (!UIMbnbger.getBoolebn("Spinner.editorBorderPbinted")) {
            if (editor instbnceof JPbnel &&
                editor.getBorder() == null &&
                editor.getComponentCount() > 0) {

                editor = (JComponent)editor.getComponent(0);
            }

            if (editor != null && editor.getBorder() instbnceof UIResource) {
                editor.setBorder(null);
            }
        }
    }

    /**
     * Remove the border bround the inner editor component for LbFs
     * thbt instbll bn outside border bround the spinner,
     */
    privbte void instbllEditorBorderListener(JComponent editor) {
        if (!UIMbnbger.getBoolebn("Spinner.editorBorderPbinted")) {
            if (editor instbnceof JPbnel &&
                editor.getBorder() == null &&
                editor.getComponentCount() > 0) {

                editor = (JComponent)editor.getComponent(0);
            }
            if (editor != null &&
                (editor.getBorder() == null ||
                 editor.getBorder() instbnceof UIResource)) {
                editor.bddPropertyChbngeListener(getHbndler());
            }
        }
    }

    privbte void removeEditorBorderListener(JComponent editor) {
        if (!UIMbnbger.getBoolebn("Spinner.editorBorderPbinted")) {
            if (editor instbnceof JPbnel &&
                editor.getComponentCount() > 0) {

                editor = (JComponent)editor.getComponent(0);
            }
            if (editor != null) {
                editor.removePropertyChbngeListener(getHbndler());
            }
        }
    }


    /**
     * Updbtes the enbbled stbte of the children Components bbsed on the
     * enbbled stbte of the <code>JSpinner</code>.
     */
    privbte void updbteEnbbledStbte() {
        updbteEnbbledStbte(spinner, spinner.isEnbbled());
    }


    /**
     * Recursively updbtes the enbbled stbte of the child
     * <code>Component</code>s of <code>c</code>.
     */
    privbte void updbteEnbbledStbte(Contbiner c, boolebn enbbled) {
        for (int counter = c.getComponentCount() - 1; counter >= 0;counter--) {
            Component child = c.getComponent(counter);

            if (DefbultLookup.getBoolebn(spinner, this,
                "Spinner.disbbleOnBoundbryVblues", fblse)) {
                SpinnerModel model = spinner.getModel();
                if (child.getNbme() == "Spinner.nextButton" &&
                    model.getNextVblue() == null) {
                    child.setEnbbled(fblse);
                }
                else if (child.getNbme() == "Spinner.previousButton" &&
                         model.getPreviousVblue() == null) {
                    child.setEnbbled(fblse);
                }
                else {
                    child.setEnbbled(enbbled);
                }
            }
            else {
                child.setEnbbled(enbbled);
            }
            if (child instbnceof Contbiner) {
                updbteEnbbledStbte((Contbiner)child, enbbled);
            }
        }
    }


    /**
     * Instblls the keybobrd Actions onto the JSpinner.
     *
     * @since 1.5
     */
    protected void instbllKeybobrdActions() {
        InputMbp iMbp = getInputMbp(JComponent.
                                   WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        SwingUtilities.replbceUIInputMbp(spinner, JComponent.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                                         iMbp);

        LbzyActionMbp.instbllLbzyActionMbp(spinner, BbsicSpinnerUI.clbss,
                "Spinner.bctionMbp");
    }

    /**
     * Returns the InputMbp to instbll for <code>condition</code>.
     */
    privbte InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            return (InputMbp)DefbultLookup.get(spinner, this,
                    "Spinner.bncestorInputMbp");
        }
        return null;
    }

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put("increment", nextButtonHbndler);
        mbp.put("decrement", previousButtonHbndler);
    }

    /**
     * Returns the bbseline.
     *
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public int getBbseline(JComponent c, int width, int height) {
        super.getBbseline(c, width, height);
        JComponent editor = spinner.getEditor();
        Insets insets = spinner.getInsets();
        width = width - insets.left - insets.right;
        height = height - insets.top - insets.bottom;
        if (width >= 0 && height >= 0) {
            int bbseline = editor.getBbseline(width, height);
            if (bbseline >= 0) {
                return insets.top + bbseline;
            }
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
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            JComponent c) {
        super.getBbselineResizeBehbvior(c);
        return spinner.getEditor().getBbselineResizeBehbvior();
    }

    /**
     * A hbndler for spinner brrow button mouse bnd bction events.  When
     * b left mouse pressed event occurs we look up the (enbbled) spinner
     * thbt's the source of the event bnd stbrt the butorepebt timer.  The
     * timer fires bction events until bny button is relebsed bt which
     * point the timer is stopped bnd the reference to the spinner clebred.
     * The timer doesn't stbrt until bfter b 300ms delby, so often the
     * source of the initibl (bnd finbl) bction event is just the button
     * logic for mouse relebsed - which mebns thbt we're relying on the fbct
     * thbt our mouse listener runs bfter the buttons mouse listener.
     * <p>
     * Note thbt one instbnce of this hbndler is shbred by bll slider previous
     * brrow buttons bnd likewise for bll of the next buttons,
     * so it doesn't hbve bny stbte thbt persists beyond the limits
     * of b single button pressed/relebsed gesture.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte stbtic clbss ArrowButtonHbndler extends AbstrbctAction
                                            implements FocusListener, MouseListener, UIResource {
        finbl jbvbx.swing.Timer butoRepebtTimer;
        finbl boolebn isNext;
        JSpinner spinner = null;
        JButton brrowButton = null;

        ArrowButtonHbndler(String nbme, boolebn isNext) {
            super(nbme);
            this.isNext = isNext;
            butoRepebtTimer = new jbvbx.swing.Timer(60, this);
            butoRepebtTimer.setInitiblDelby(300);
        }

        privbte JSpinner eventToSpinner(AWTEvent e) {
            Object src = e.getSource();
            while ((src instbnceof Component) && !(src instbnceof JSpinner)) {
                src = ((Component)src).getPbrent();
            }
            return (src instbnceof JSpinner) ? (JSpinner)src : null;
        }

        public void bctionPerformed(ActionEvent e) {
            JSpinner spinner = this.spinner;

            if (!(e.getSource() instbnceof jbvbx.swing.Timer)) {
                // Most likely resulting from being in ActionMbp.
                spinner = eventToSpinner(e);
                if (e.getSource() instbnceof JButton) {
                    brrowButton = (JButton)e.getSource();
                }
            } else {
                if (brrowButton!=null && !brrowButton.getModel().isPressed()
                    && butoRepebtTimer.isRunning()) {
                    butoRepebtTimer.stop();
                    spinner = null;
                    brrowButton = null;
                }
            }
            if (spinner != null) {
                try {
                    int cblendbrField = getCblendbrField(spinner);
                    spinner.commitEdit();
                    if (cblendbrField != -1) {
                        ((SpinnerDbteModel)spinner.getModel()).
                                 setCblendbrField(cblendbrField);
                    }
                    Object vblue = (isNext) ? spinner.getNextVblue() :
                               spinner.getPreviousVblue();
                    if (vblue != null) {
                        spinner.setVblue(vblue);
                        select(spinner);
                    }
                } cbtch (IllegblArgumentException ibe) {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(spinner);
                } cbtch (PbrseException pe) {
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(spinner);
                }
            }
        }

        /**
         * If the spinner's editor is b DbteEditor, this selects the field
         * bssocibted with the vblue thbt is being incremented.
         */
        privbte void select(JSpinner spinner) {
            JComponent editor = spinner.getEditor();

            if (editor instbnceof JSpinner.DbteEditor) {
                JSpinner.DbteEditor dbteEditor = (JSpinner.DbteEditor)editor;
                JFormbttedTextField ftf = dbteEditor.getTextField();
                Formbt formbt = dbteEditor.getFormbt();
                Object vblue;

                if (formbt != null && (vblue = spinner.getVblue()) != null) {
                    SpinnerDbteModel model = dbteEditor.getModel();
                    DbteFormbt.Field field = DbteFormbt.Field.ofCblendbrField(
                        model.getCblendbrField());

                    if (field != null) {
                        try {
                            AttributedChbrbcterIterbtor iterbtor = formbt.
                                formbtToChbrbcterIterbtor(vblue);
                            if (!select(ftf, iterbtor, field) &&
                                       field == DbteFormbt.Field.HOUR0) {
                                select(ftf, iterbtor, DbteFormbt.Field.HOUR1);
                            }
                        }
                        cbtch (IllegblArgumentException ibe) {}
                    }
                }
            }
        }

        /**
         * Selects the pbssed in field, returning true if it is found,
         * fblse otherwise.
         */
        privbte boolebn select(JFormbttedTextField ftf,
                               AttributedChbrbcterIterbtor iterbtor,
                               DbteFormbt.Field field) {
            int mbx = ftf.getDocument().getLength();

            iterbtor.first();
            do {
                Mbp<?, ?> bttrs = iterbtor.getAttributes();

                if (bttrs != null && bttrs.contbinsKey(field)){
                    int stbrt = iterbtor.getRunStbrt(field);
                    int end = iterbtor.getRunLimit(field);

                    if (stbrt != -1 && end != -1 && stbrt <= mbx &&
                                       end <= mbx) {
                        ftf.select(stbrt, end);
                    }
                    return true;
                }
            } while (iterbtor.next() != ChbrbcterIterbtor.DONE);
            return fblse;
        }

        /**
         * Returns the cblendbrField under the stbrt of the selection, or
         * -1 if there is no vblid cblendbr field under the selection (or
         * the spinner isn't editing dbtes.
         */
        privbte int getCblendbrField(JSpinner spinner) {
            JComponent editor = spinner.getEditor();

            if (editor instbnceof JSpinner.DbteEditor) {
                JSpinner.DbteEditor dbteEditor = (JSpinner.DbteEditor)editor;
                JFormbttedTextField ftf = dbteEditor.getTextField();
                int stbrt = ftf.getSelectionStbrt();
                JFormbttedTextField.AbstrbctFormbtter formbtter =
                                    ftf.getFormbtter();

                if (formbtter instbnceof InternbtionblFormbtter) {
                    Formbt.Field[] fields = ((InternbtionblFormbtter)
                                             formbtter).getFields(stbrt);

                    for (int counter = 0; counter < fields.length; counter++) {
                        if (fields[counter] instbnceof DbteFormbt.Field) {
                            int cblendbrField;

                            if (fields[counter] == DbteFormbt.Field.HOUR1) {
                                cblendbrField = Cblendbr.HOUR;
                            }
                            else {
                                cblendbrField = ((DbteFormbt.Field)
                                        fields[counter]).getCblendbrField();
                            }
                            if (cblendbrField != -1) {
                                return cblendbrField;
                            }
                        }
                    }
                }
            }
            return -1;
        }

        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e) && e.getComponent().isEnbbled()) {
                spinner = eventToSpinner(e);
                butoRepebtTimer.stbrt();

                focusSpinnerIfNecessbry();
            }
        }

        public void mouseRelebsed(MouseEvent e) {
            butoRepebtTimer.stop();
            brrowButton = null;
            spinner = null;
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            if (spinner != null && !butoRepebtTimer.isRunning() && spinner == eventToSpinner(e)) {
                butoRepebtTimer.stbrt();
            }
        }

        public void mouseExited(MouseEvent e) {
            if (butoRepebtTimer.isRunning()) {
                butoRepebtTimer.stop();
            }
        }

        /**
         * Requests focus on b child of the spinner if the spinner doesn't
         * hbve focus.
         */
        privbte void focusSpinnerIfNecessbry() {
            Component fo = KeybobrdFocusMbnbger.
                              getCurrentKeybobrdFocusMbnbger().getFocusOwner();
            if (spinner.isRequestFocusEnbbled() && (
                        fo == null ||
                        !SwingUtilities.isDescendingFrom(fo, spinner))) {
                Contbiner root = spinner;

                if (!root.isFocusCycleRoot()) {
                    root = root.getFocusCycleRootAncestor();
                }
                if (root != null) {
                    FocusTrbversblPolicy ftp = root.getFocusTrbversblPolicy();
                    Component child = ftp.getComponentAfter(root, spinner);

                    if (child != null && SwingUtilities.isDescendingFrom(
                                                        child, spinner)) {
                        child.requestFocus();
                    }
                }
            }
        }

        public void focusGbined(FocusEvent e) {
        }

        public void focusLost(FocusEvent e) {
            if (spinner == eventToSpinner(e)) {
                if (butoRepebtTimer.isRunning()) {
                    butoRepebtTimer.stop();
                }
                spinner = null;
                if (brrowButton != null) {
                    ButtonModel model = brrowButton.getModel();
                    model.setPressed(fblse);
                    model.setArmed(fblse);
                    brrowButton = null;
                }
            }
        }
    }


    privbte stbtic clbss Hbndler implements LbyoutMbnbger,
            PropertyChbngeListener, ChbngeListener {
        //
        // LbyoutMbnbger
        //
        privbte Component nextButton = null;
        privbte Component previousButton = null;
        privbte Component editor = null;

        public void bddLbyoutComponent(String nbme, Component c) {
            if ("Next".equbls(nbme)) {
                nextButton = c;
            }
            else if ("Previous".equbls(nbme)) {
                previousButton = c;
            }
            else if ("Editor".equbls(nbme)) {
                editor = c;
            }
        }

        public void removeLbyoutComponent(Component c) {
            if (c == nextButton) {
                nextButton = null;
            }
            else if (c == previousButton) {
                previousButton = null;
            }
            else if (c == editor) {
                editor = null;
            }
        }

        privbte Dimension preferredSize(Component c) {
            return (c == null) ? zeroSize : c.getPreferredSize();
        }

        public Dimension preferredLbyoutSize(Contbiner pbrent) {
            Dimension nextD = preferredSize(nextButton);
            Dimension previousD = preferredSize(previousButton);
            Dimension editorD = preferredSize(editor);

            /* Force the editors height to be b multiple of 2
             */
            editorD.height = ((editorD.height + 1) / 2) * 2;

            Dimension size = new Dimension(editorD.width, editorD.height);
            size.width += Mbth.mbx(nextD.width, previousD.width);
            Insets insets = pbrent.getInsets();
            size.width += insets.left + insets.right;
            size.height += insets.top + insets.bottom;
            return size;
        }

        public Dimension minimumLbyoutSize(Contbiner pbrent) {
            return preferredLbyoutSize(pbrent);
        }

        privbte void setBounds(Component c, int x, int y, int width, int height) {
            if (c != null) {
                c.setBounds(x, y, width, height);
            }
        }

        public void lbyoutContbiner(Contbiner pbrent) {
            int width  = pbrent.getWidth();
            int height = pbrent.getHeight();

            Insets insets = pbrent.getInsets();

            if (nextButton == null && previousButton == null) {
                setBounds(editor, insets.left,  insets.top, width - insets.left - insets.right,
                        height - insets.top - insets.bottom);

                return;
            }

            Dimension nextD = preferredSize(nextButton);
            Dimension previousD = preferredSize(previousButton);
            int buttonsWidth = Mbth.mbx(nextD.width, previousD.width);
            int editorHeight = height - (insets.top + insets.bottom);

            // The brrowButtonInsets vblue is used instebd of the JSpinner's
            // insets if not null. Defining this to be (0, 0, 0, 0) cbuses the
            // buttons to be bligned with the outer edge of the spinner's
            // border, bnd lebving it bs "null" plbces the buttons completely
            // inside the spinner's border.
            Insets buttonInsets = UIMbnbger.getInsets("Spinner.brrowButtonInsets");
            if (buttonInsets == null) {
                buttonInsets = insets;
            }

            /* Debl with the spinner's componentOrientbtion property.
             */
            int editorX, editorWidth, buttonsX;
            if (pbrent.getComponentOrientbtion().isLeftToRight()) {
                editorX = insets.left;
                editorWidth = width - insets.left - buttonsWidth - buttonInsets.right;
                buttonsX = width - buttonsWidth - buttonInsets.right;
            } else {
                buttonsX = buttonInsets.left;
                editorX = buttonsX + buttonsWidth;
                editorWidth = width - buttonInsets.left - buttonsWidth - insets.right;
            }

            int nextY = buttonInsets.top;
            int nextHeight = (height / 2) + (height % 2) - nextY;
            int previousY = buttonInsets.top + nextHeight;
            int previousHeight = height - previousY - buttonInsets.bottom;

            setBounds(editor,         editorX,  insets.top, editorWidth, editorHeight);
            setBounds(nextButton,     buttonsX, nextY,      buttonsWidth, nextHeight);
            setBounds(previousButton, buttonsX, previousY,  buttonsWidth, previousHeight);
        }


        //
        // PropertyChbngeListener
        //
        public void propertyChbnge(PropertyChbngeEvent e)
        {
            String propertyNbme = e.getPropertyNbme();
            if (e.getSource() instbnceof JSpinner) {
                JSpinner spinner = (JSpinner)(e.getSource());
                SpinnerUI spinnerUI = spinner.getUI();

                if (spinnerUI instbnceof BbsicSpinnerUI) {
                    BbsicSpinnerUI ui = (BbsicSpinnerUI)spinnerUI;

                    if ("editor".equbls(propertyNbme)) {
                        JComponent oldEditor = (JComponent)e.getOldVblue();
                        JComponent newEditor = (JComponent)e.getNewVblue();
                        ui.replbceEditor(oldEditor, newEditor);
                        ui.updbteEnbbledStbte();
                        if (oldEditor instbnceof JSpinner.DefbultEditor) {
                            JTextField tf =
                                ((JSpinner.DefbultEditor)oldEditor).getTextField();
                            if (tf != null) {
                                tf.removeFocusListener(nextButtonHbndler);
                                tf.removeFocusListener(previousButtonHbndler);
                            }
                        }
                        if (newEditor instbnceof JSpinner.DefbultEditor) {
                            JTextField tf =
                                ((JSpinner.DefbultEditor)newEditor).getTextField();
                            if (tf != null) {
                                if (tf.getFont() instbnceof UIResource) {
                                    tf.setFont(spinner.getFont());
                                }
                                tf.bddFocusListener(nextButtonHbndler);
                                tf.bddFocusListener(previousButtonHbndler);
                            }
                        }
                    }
                    else if ("enbbled".equbls(propertyNbme) ||
                             "model".equbls(propertyNbme)) {
                        ui.updbteEnbbledStbte();
                    }
                    else if ("font".equbls(propertyNbme)) {
                        JComponent editor = spinner.getEditor();
                        if (editor!=null && editor instbnceof JSpinner.DefbultEditor) {
                            JTextField tf =
                                ((JSpinner.DefbultEditor)editor).getTextField();
                            if (tf != null) {
                                if (tf.getFont() instbnceof UIResource) {
                                    tf.setFont(spinner.getFont());
                                }
                            }
                        }
                    }
                    else if (JComponent.TOOL_TIP_TEXT_KEY.equbls(propertyNbme)) {
                        updbteToolTipTextForChildren(spinner);
                    } else if ("componentOrientbtion".equbls(propertyNbme)) {
                        ComponentOrientbtion o
                                = (ComponentOrientbtion) e.getNewVblue();
                        if (o != (ComponentOrientbtion) e.getOldVblue()) {
                            JComponent editor = spinner.getEditor();
                            if (editor != null) {
                                editor.bpplyComponentOrientbtion(o);
                            }
                            spinner.revblidbte();
                            spinner.repbint();
                        }
                    }
                }
            } else if (e.getSource() instbnceof JComponent) {
                JComponent c = (JComponent)e.getSource();
                if ((c.getPbrent() instbnceof JPbnel) &&
                    (c.getPbrent().getPbrent() instbnceof JSpinner) &&
                    "border".equbls(propertyNbme)) {

                    JSpinner spinner = (JSpinner)c.getPbrent().getPbrent();
                    SpinnerUI spinnerUI = spinner.getUI();
                    if (spinnerUI instbnceof BbsicSpinnerUI) {
                        BbsicSpinnerUI ui = (BbsicSpinnerUI)spinnerUI;
                        ui.mbybeRemoveEditorBorder(c);
                    }
                }
            }
        }

        // Syncronizes the ToolTip text for the components within the spinner
        // to be the sbme vblue bs the spinner ToolTip text.
        privbte void updbteToolTipTextForChildren(JComponent spinner) {
            String toolTipText = spinner.getToolTipText();
            Component[] children = spinner.getComponents();
            for (int i = 0; i < children.length; i++) {
                if (children[i] instbnceof JSpinner.DefbultEditor) {
                    JTextField tf = ((JSpinner.DefbultEditor)children[i]).getTextField();
                    if (tf != null) {
                        tf.setToolTipText(toolTipText);
                    }
                } else if (children[i] instbnceof JComponent) {
                    ((JComponent)children[i]).setToolTipText( spinner.getToolTipText() );
                }
            }
        }

        public void stbteChbnged(ChbngeEvent e) {
            if (e.getSource() instbnceof JSpinner) {
                JSpinner spinner = (JSpinner)e.getSource();
                SpinnerUI spinnerUI = spinner.getUI();
                if (DefbultLookup.getBoolebn(spinner, spinnerUI,
                    "Spinner.disbbleOnBoundbryVblues", fblse) &&
                    spinnerUI instbnceof BbsicSpinnerUI) {
                    BbsicSpinnerUI ui = (BbsicSpinnerUI)spinnerUI;
                    ui.updbteEnbbledStbte();
                }
            }
        }
    }
}
