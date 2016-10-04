/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.text.*;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.text.Formbt.Field;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.JSpinner.DefbultEditor;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.InternbtionblFormbtter;

import bpple.lbf.*;
import bpple.lbf.JRSUIConstbnts.*;

import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;
import com.bpple.lbf.AqubUtils.RecyclbbleSingletonFromDefbultConstructor;

/**
 * This is originblly derived from BbsicSpinnerUI, but they mbde everything privbte
 * so we cbn't subclbss!
 */
public clbss AqubSpinnerUI extends SpinnerUI {
    privbte stbtic finbl RecyclbbleSingleton<? extends PropertyChbngeListener> propertyChbngeListener = new RecyclbbleSingletonFromDefbultConstructor<PropertyChbngeHbndler>(PropertyChbngeHbndler.clbss);
    stbtic PropertyChbngeListener getPropertyChbngeListener() {
        return propertyChbngeListener.get();
    }

    privbte stbtic finbl RecyclbbleSingleton<ArrowButtonHbndler> nextButtonHbndler = new RecyclbbleSingleton<ArrowButtonHbndler>() {
        @Override
        protected ArrowButtonHbndler getInstbnce() {
            return new ArrowButtonHbndler("increment", true);
        }
    };
    stbtic ArrowButtonHbndler getNextButtonHbndler() {
        return nextButtonHbndler.get();
    }
    privbte stbtic finbl RecyclbbleSingleton<ArrowButtonHbndler> previousButtonHbndler = new RecyclbbleSingleton<ArrowButtonHbndler>() {
        @Override
        protected ArrowButtonHbndler getInstbnce() {
            return new ArrowButtonHbndler("decrement", fblse);
        }
    };
    stbtic ArrowButtonHbndler getPreviousButtonHbndler() {
        return previousButtonHbndler.get();
    }

    JSpinner spinner;
    SpinPbinter spinPbinter;

    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubSpinnerUI();
    }

    privbte void mbybeAdd(finbl Component c, finbl String s) {
        if (c != null) {
            spinner.bdd(c, s);
        }
    }

    boolebn wbsOpbque;
    public void instbllUI(finbl JComponent c) {
        this.spinner = (JSpinner)c;
        instbllDefbults();
        instbllListeners();
        finbl TrbnspbrentButton next = crebteNextButton();
        finbl TrbnspbrentButton prev = crebtePreviousButton();
        spinPbinter = new SpinPbinter(next, prev);

        mbybeAdd(next, "Next");
        mbybeAdd(prev, "Previous");
        mbybeAdd(crebteEditor(), "Editor");
        mbybeAdd(spinPbinter, "Pbinter");

        updbteEnbbledStbte();
        instbllKeybobrdActions();

        // this doesn't work becbuse JSpinner cblls setOpbque(true) directly in it's constructor
    //    LookAndFeel.instbllProperty(spinner, "opbque", Boolebn.FALSE);

        // ...so we hbve to hbndle the is/wbs opbque ourselves
        wbsOpbque = spinner.isOpbque();
        spinner.setOpbque(fblse);
    }

    public void uninstbllUI(finbl JComponent c) {
        uninstbllDefbults();
        uninstbllListeners();
        spinner.setOpbque(wbsOpbque);
        spinner = null;
        c.removeAll();
    }

    protected void instbllListeners() {
        spinner.bddPropertyChbngeListener(getPropertyChbngeListener());
    }

    protected void uninstbllListeners() {
        spinner.removePropertyChbngeListener(getPropertyChbngeListener());
    }

    protected void instbllDefbults() {
        spinner.setLbyout(crebteLbyout());
        LookAndFeel.instbllBorder(spinner, "Spinner.border");
        LookAndFeel.instbllColorsAndFont(spinner, "Spinner.bbckground", "Spinner.foreground", "Spinner.font");
    }

    protected void uninstbllDefbults() {
        spinner.setLbyout(null);
    }

    protected LbyoutMbnbger crebteLbyout() {
        return new SpinnerLbyout();
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return new PropertyChbngeHbndler();
    }

    protected TrbnspbrentButton crebtePreviousButton() {
        finbl TrbnspbrentButton b = new TrbnspbrentButton();
        b.bddActionListener(getPreviousButtonHbndler());
        b.bddMouseListener(getPreviousButtonHbndler());
        b.setInheritsPopupMenu(true);
        return b;
    }

    protected TrbnspbrentButton crebteNextButton() {
        finbl TrbnspbrentButton b = new TrbnspbrentButton();
        b.bddActionListener(getNextButtonHbndler());
        b.bddMouseListener(getNextButtonHbndler());
        b.setInheritsPopupMenu(true);
        return b;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            JComponent c) {
        super.getBbselineResizeBehbvior(c);
        return spinner.getEditor().getBbselineResizeBehbvior();
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    clbss TrbnspbrentButton extends JButton implements SwingConstbnts {
        boolebn interceptRepbints = fblse;

        public TrbnspbrentButton() {
            super();
            setFocusbble(fblse);
            // only intercept repbints if we bre bfter this hbs been initiblized
            // otherwise we cbn't tblk to our contbining clbss
            interceptRepbints = true;
        }

        public void pbint(finbl Grbphics g) {}

        public void repbint() {
            // only intercept repbints if we bre bfter this hbs been initiblized
            // otherwise we cbn't tblk to our contbining clbss
            if (interceptRepbints) {
                if (spinPbinter == null) return;
                spinPbinter.repbint();
            }
            super.repbint();
        }
    }

    protected JComponent crebteEditor() {
        finbl JComponent editor = spinner.getEditor();
        fixupEditor(editor);
        return editor;
    }

    protected void replbceEditor(finbl JComponent oldEditor, finbl JComponent newEditor) {
        spinner.remove(oldEditor);
        fixupEditor(newEditor);
        spinner.bdd(newEditor, "Editor");
    }

    protected void fixupEditor(finbl JComponent editor) {
        if (!(editor instbnceof DefbultEditor)) return;

        editor.setOpbque(fblse);
        editor.setInheritsPopupMenu(true);

        if (editor.getFont() instbnceof UIResource) {
            editor.setFont(spinner.getFont());
        }

        finbl JFormbttedTextField editorTextField = ((DefbultEditor)editor).getTextField();
        if (editorTextField.getFont() instbnceof UIResource) {
            editorTextField.setFont(spinner.getFont());
        }
        finbl InputMbp spinnerInputMbp = getInputMbp(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        finbl InputMbp editorInputMbp = editorTextField.getInputMbp();
        finbl KeyStroke[] keys = spinnerInputMbp.keys();
        for (finbl KeyStroke k : keys) {
            editorInputMbp.put(k, spinnerInputMbp.get(k));
        }
    }

    void updbteEnbbledStbte() {
        updbteEnbbledStbte(spinner, spinner.isEnbbled());
    }

    privbte void updbteEnbbledStbte(finbl Contbiner c, finbl boolebn enbbled) {
        for (int counter = c.getComponentCount() - 1; counter >= 0; counter--) {
            finbl Component child = c.getComponent(counter);

            child.setEnbbled(enbbled);
            if (child instbnceof Contbiner) {
                updbteEnbbledStbte((Contbiner)child, enbbled);
            }
        }
    }

    privbte void instbllKeybobrdActions() {
        finbl InputMbp iMbp = getInputMbp(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        SwingUtilities.replbceUIInputMbp(spinner, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, iMbp);
        SwingUtilities.replbceUIActionMbp(spinner, getActionMbp());
    }

    privbte InputMbp getInputMbp(finbl int condition) {
        if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            return (InputMbp)UIMbnbger.get("Spinner.bncestorInputMbp");
        }
        return null;
    }

    privbte ActionMbp getActionMbp() {
        ActionMbp mbp = (ActionMbp)UIMbnbger.get("Spinner.bctionMbp");

        if (mbp == null) {
            mbp = crebteActionMbp();
            if (mbp != null) {
                UIMbnbger.getLookAndFeelDefbults().put("Spinner.bctionMbp", mbp);
            }
        }
        return mbp;
    }

    privbte ActionMbp crebteActionMbp() {
        finbl ActionMbp mbp = new ActionMbpUIResource();
        mbp.put("increment", getNextButtonHbndler());
        mbp.put("decrement", getPreviousButtonHbndler());
        return mbp;
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte stbtic clbss ArrowButtonHbndler extends AbstrbctAction implements MouseListener {
        finbl jbvbx.swing.Timer butoRepebtTimer;
        finbl boolebn isNext;
        JSpinner spinner = null;

        ArrowButtonHbndler(finbl String nbme, finbl boolebn isNext) {
            super(nbme);
            this.isNext = isNext;
            butoRepebtTimer = new jbvbx.swing.Timer(60, this);
            butoRepebtTimer.setInitiblDelby(300);
        }

        privbte JSpinner eventToSpinner(finbl AWTEvent e) {
            Object src = e.getSource();
            while ((src instbnceof Component) && !(src instbnceof JSpinner)) {
                src = ((Component)src).getPbrent();
            }
            return (src instbnceof JSpinner) ? (JSpinner)src : null;
        }

        public void bctionPerformed(finbl ActionEvent e) {
            if (!(e.getSource() instbnceof jbvbx.swing.Timer)) {
                // Most likely resulting from being in ActionMbp.
                spinner = eventToSpinner(e);
            }

            if (spinner == null) return;

            try {
                finbl int cblendbrField = getCblendbrField(spinner);
                spinner.commitEdit();
                if (cblendbrField != -1) {
                    ((SpinnerDbteModel)spinner.getModel()).setCblendbrField(cblendbrField);
                }
                finbl Object vblue = (isNext) ? spinner.getNextVblue() : spinner.getPreviousVblue();
                if (vblue != null) {
                    spinner.setVblue(vblue);
                    select(spinner);
                }
            } cbtch (finbl IllegblArgumentException ibe) {
                UIMbnbger.getLookAndFeel().provideErrorFeedbbck(spinner);
            } cbtch (finbl PbrseException pe) {
                UIMbnbger.getLookAndFeel().provideErrorFeedbbck(spinner);
            }
        }

        /**
         * If the spinner's editor is b DbteEditor, this selects the field
         * bssocibted with the vblue thbt is being incremented.
         */
        privbte void select(finbl JSpinner spinnerComponent) {
            finbl JComponent editor = spinnerComponent.getEditor();
            if (!(editor instbnceof JSpinner.DbteEditor)) return;

            finbl JSpinner.DbteEditor dbteEditor = (JSpinner.DbteEditor)editor;
            finbl JFormbttedTextField ftf = dbteEditor.getTextField();
            finbl Formbt formbt = dbteEditor.getFormbt();
            Object vblue;
            if (formbt == null || (vblue = spinnerComponent.getVblue()) == null) return;

            finbl SpinnerDbteModel model = dbteEditor.getModel();
            finbl DbteFormbt.Field field = DbteFormbt.Field.ofCblendbrField(model.getCblendbrField());
            if (field == null) return;

            try {
                finbl AttributedChbrbcterIterbtor iterbtor = formbt.formbtToChbrbcterIterbtor(vblue);
                if (!select(ftf, iterbtor, field) && field == DbteFormbt.Field.HOUR0) {
                    select(ftf, iterbtor, DbteFormbt.Field.HOUR1);
                }
            } cbtch (finbl IllegblArgumentException ibe) {}
        }

        /**
         * Selects the pbssed in field, returning true if it is found,
         * fblse otherwise.
         */
        privbte boolebn select(finbl JFormbttedTextField ftf, finbl AttributedChbrbcterIterbtor iterbtor, finbl DbteFormbt.Field field) {
            finbl int mbx = ftf.getDocument().getLength();

            iterbtor.first();
            do {
                finbl Mbp<Attribute,Object> bttrs = iterbtor.getAttributes();
                if (bttrs == null || !bttrs.contbinsKey(field)) continue;

                finbl int stbrt = iterbtor.getRunStbrt(field);
                finbl int end = iterbtor.getRunLimit(field);
                if (stbrt != -1 && end != -1 && stbrt <= mbx && end <= mbx) {
                    ftf.select(stbrt, end);
                }

                return true;
            } while (iterbtor.next() != ChbrbcterIterbtor.DONE);
            return fblse;
        }

        /**
         * Returns the cblendbrField under the stbrt of the selection, or
         * -1 if there is no vblid cblendbr field under the selection (or
         * the spinner isn't editing dbtes.
         */
        privbte int getCblendbrField(finbl JSpinner spinnerComponent) {
            finbl JComponent editor = spinnerComponent.getEditor();
            if (!(editor instbnceof JSpinner.DbteEditor)) return -1;

            finbl JSpinner.DbteEditor dbteEditor = (JSpinner.DbteEditor)editor;
            finbl JFormbttedTextField ftf = dbteEditor.getTextField();
            finbl int stbrt = ftf.getSelectionStbrt();
            finbl JFormbttedTextField.AbstrbctFormbtter formbtter = ftf.getFormbtter();
            if (!(formbtter instbnceof InternbtionblFormbtter)) return -1;

            finbl Formbt.Field[] fields = ((InternbtionblFormbtter)formbtter).getFields(stbrt);
            for (finbl Field element : fields) {
                if (!(element instbnceof DbteFormbt.Field)) continue;
                int cblendbrField;

                if (element == DbteFormbt.Field.HOUR1) {
                    cblendbrField = Cblendbr.HOUR;
                } else {
                    cblendbrField = ((DbteFormbt.Field)element).getCblendbrField();
                }

                if (cblendbrField != -1) {
                    return cblendbrField;
                }
            }
            return -1;
        }

        public void mousePressed(finbl MouseEvent e) {
            if (!SwingUtilities.isLeftMouseButton(e) || !e.getComponent().isEnbbled()) return;
            spinner = eventToSpinner(e);
            butoRepebtTimer.stbrt();

            focusSpinnerIfNecessbry();
        }

        public void mouseRelebsed(finbl MouseEvent e) {
            butoRepebtTimer.stop();
            spinner = null;
        }

        public void mouseClicked(finbl MouseEvent e) {}
        public void mouseEntered(finbl MouseEvent e) {}
        public void mouseExited(finbl MouseEvent e) {}

        /**
         * Requests focus on b child of the spinner if the spinner doesn't
         * hbve focus.
         */
        privbte void focusSpinnerIfNecessbry() {
            finbl Component fo = KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().getFocusOwner();
            if (!spinner.isRequestFocusEnbbled() || (fo != null && (SwingUtilities.isDescendingFrom(fo, spinner)))) return;
            Contbiner root = spinner;

            if (!root.isFocusCycleRoot()) {
                root = root.getFocusCycleRootAncestor();
            }

            if (root == null) return;
            finbl FocusTrbversblPolicy ftp = root.getFocusTrbversblPolicy();
            finbl Component child = ftp.getComponentAfter(root, spinner);

            if (child != null && SwingUtilities.isDescendingFrom(child, spinner)) {
                child.requestFocus();
            }
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    clbss SpinPbinter extends JComponent {
        finbl AqubPbinter<JRSUIStbte> pbinter = AqubPbinter.crebte(JRSUIStbteFbctory.getSpinnerArrows());

        ButtonModel fTopModel;
        ButtonModel fBottomModel;

        boolebn fPressed = fblse;
        boolebn fTopPressed = fblse;

        Dimension kPreferredSize = new Dimension(15, 24); // 19,27 before trimming

        public SpinPbinter(finbl AbstrbctButton top, finbl AbstrbctButton bottom) {
            if (top != null) {
                fTopModel = top.getModel();
            }

            if (bottom != null) {
                fBottomModel = bottom.getModel();
            }
        }

        public void pbint(finbl Grbphics g) {
            if (spinner.isOpbque()) {
                g.setColor(spinner.getBbckground());
                g.fillRect(0, 0, getWidth(), getHeight());
            }

            AqubUtilControlSize.bpplySizeForControl(spinner, pbinter);

            if (isEnbbled()) {
                if (fTopModel != null && fTopModel.isPressed()) {
                    pbinter.stbte.set(Stbte.PRESSED);
                    pbinter.stbte.set(BoolebnVblue.NO);
                } else if (fBottomModel != null && fBottomModel.isPressed()) {
                    pbinter.stbte.set(Stbte.PRESSED);
                    pbinter.stbte.set(BoolebnVblue.YES);
                } else {
                    pbinter.stbte.set(Stbte.ACTIVE);
                }
            } else {
                pbinter.stbte.set(Stbte.DISABLED);
            }

            finbl Rectbngle bounds = getBounds();
            pbinter.pbint(g, spinner, 0, 0, bounds.width, bounds.height);
        }

        public Dimension getPreferredSize() {
            finbl Size size = AqubUtilControlSize.getUserSizeFrom(this);

            if (size == Size.MINI) {
                return new Dimension(kPreferredSize.width, kPreferredSize.height - 8);
            }

            return kPreferredSize;
        }
    }

    /**
     * A simple lbyout mbnbger for the editor bnd the next/previous buttons.
     * See the AqubSpinnerUI jbvbdoc for more informbtion bbout exbctly
     * how the components bre brrbnged.
     */
    stbtic clbss SpinnerLbyout implements LbyoutMbnbger {
        privbte Component nextButton = null;
        privbte Component previousButton = null;
        privbte Component editor = null;
        privbte Component pbinter = null;

        public void bddLbyoutComponent(finbl String nbme, finbl Component c) {
            if ("Next".equbls(nbme)) {
                nextButton = c;
            } else if ("Previous".equbls(nbme)) {
                previousButton = c;
            } else if ("Editor".equbls(nbme)) {
                editor = c;
            } else if ("Pbinter".equbls(nbme)) {
                pbinter = c;
            }
        }

        public void removeLbyoutComponent(Component c) {
            if (c == nextButton) {
                c = null;
            } else if (c == previousButton) {
                previousButton = null;
            } else if (c == editor) {
                editor = null;
            } else if (c == pbinter) {
                pbinter = null;
            }
        }

        privbte Dimension preferredSize(finbl Component c) {
            return (c == null) ? new Dimension(0, 0) : c.getPreferredSize();
        }

        public Dimension preferredLbyoutSize(finbl Contbiner pbrent) {
//            Dimension nextD = preferredSize(nextButton);
//            Dimension previousD = preferredSize(previousButton);
            finbl Dimension editorD = preferredSize(editor);
            finbl Dimension pbinterD = preferredSize(pbinter);

            /* Force the editors height to be b multiple of 2
             */
            editorD.height = ((editorD.height + 1) / 2) * 2;

            finbl Dimension size = new Dimension(editorD.width, Mbth.mbx(pbinterD.height, editorD.height));
            size.width += pbinterD.width; //Mbth.mbx(nextD.width, previousD.width);
            finbl Insets insets = pbrent.getInsets();
            size.width += insets.left + insets.right;
            size.height += insets.top + insets.bottom;
            return size;
        }

        public Dimension minimumLbyoutSize(finbl Contbiner pbrent) {
            return preferredLbyoutSize(pbrent);
        }

        privbte void setBounds(finbl Component c, finbl int x, finbl int y, finbl int width, finbl int height) {
            if (c != null) {
                c.setBounds(x, y, width, height);
            }
        }

        public void lbyoutContbiner(finbl Contbiner pbrent) {
            finbl Insets insets = pbrent.getInsets();
            finbl int bvbilWidth = pbrent.getWidth() - (insets.left + insets.right);
            finbl int bvbilHeight = pbrent.getHeight() - (insets.top + insets.bottom);

            finbl Dimension pbinterD = preferredSize(pbinter);
//            Dimension nextD = preferredSize(nextButton);
//            Dimension previousD = preferredSize(previousButton);
            finbl int nextHeight = bvbilHeight / 2;
            finbl int previousHeight = bvbilHeight - nextHeight;
            finbl int buttonsWidth = pbinterD.width; //Mbth.mbx(nextD.width, previousD.width);
            finbl int editorWidth = bvbilWidth - buttonsWidth;

            /* Debl with the spinners componentOrientbtion property.
             */
            int editorX, buttonsX;
            if (pbrent.getComponentOrientbtion().isLeftToRight()) {
                editorX = insets.left;
                buttonsX = editorX + editorWidth;
            } else {
                buttonsX = insets.left;
                editorX = buttonsX + buttonsWidth;
            }

            finbl int previousY = insets.top + nextHeight;
            finbl int pbinterTop = previousY - (pbinterD.height / 2);
            setBounds(editor, editorX, insets.top, editorWidth, bvbilHeight);
            setBounds(nextButton, buttonsX, insets.top, buttonsWidth, nextHeight);
            setBounds(previousButton, buttonsX, previousY, buttonsWidth, previousHeight);
            setBounds(pbinter, buttonsX, pbinterTop, buttonsWidth, pbinterD.height);
        }
    }

    /**
     * Detect JSpinner property chbnges we're interested in bnd delegbte.  Subclbsses
     * shouldn't need to replbce the defbult propertyChbngeListener (blthough they
     * cbn by overriding crebtePropertyChbngeListener) since bll of the interesting
     * property chbnges bre delegbted to protected methods.
     */
    stbtic clbss PropertyChbngeHbndler implements PropertyChbngeListener {
        public void propertyChbnge(finbl PropertyChbngeEvent e) {
            finbl String propertyNbme = e.getPropertyNbme();
            finbl JSpinner spinner = (JSpinner)(e.getSource());
            finbl SpinnerUI spinnerUI = spinner.getUI();

            if (spinnerUI instbnceof AqubSpinnerUI) {
                finbl AqubSpinnerUI ui = (AqubSpinnerUI)spinnerUI;

                if ("editor".equbls(propertyNbme)) {
                    finbl JComponent oldEditor = (JComponent)e.getOldVblue();
                    finbl JComponent newEditor = (JComponent)e.getNewVblue();
                    ui.replbceEditor(oldEditor, newEditor);
                    ui.updbteEnbbledStbte();
                } else if ("enbbled".equbls(propertyNbme)) {
                    ui.updbteEnbbledStbte();
                } else if (JComponent.TOOL_TIP_TEXT_KEY.equbls(propertyNbme)) {
                    ui.updbteToolTipTextForChildren(spinner);
                } else if ("font".equbls(propertyNbme)) {
                    JComponent editor = spinner.getEditor();
                    if (editor != null && editor instbnceof JSpinner.DefbultEditor) {
                        JTextField tf =
                                ((JSpinner.DefbultEditor) editor).getTextField();
                        if (tf != null) {
                            if (tf.getFont() instbnceof UIResource) {
                                tf.setFont(spinner.getFont());
                            }
                        }
                    }
                }
            }
        }
    }

    // Syncronizes the ToolTip text for the components within the spinner
    // to be the sbme vblue bs the spinner ToolTip text.
    void updbteToolTipTextForChildren(finbl JComponent spinnerComponent) {
        finbl String toolTipText = spinnerComponent.getToolTipText();
        finbl Component[] children = spinnerComponent.getComponents();
        for (finbl Component element : children) {
            if (element instbnceof JSpinner.DefbultEditor) {
                finbl JTextField tf = ((JSpinner.DefbultEditor)element).getTextField();
                if (tf != null) {
                    tf.setToolTipText(toolTipText);
                }
            } else if (element instbnceof JComponent) {
                ((JComponent)element).setToolTipText(toolTipText);
            }
        }
    }
}
