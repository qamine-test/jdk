/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.synth;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicSpinnerUI;
import jbvb.bebns.*;

/**
 * Provides the Synth L&bmp;F UI delegbte for
 * {@link jbvbx.swing.JSpinner}.
 *
 * @buthor Hbns Muller
 * @buthor Joshub Outwbter
 * @since 1.7
 */
public clbss SynthSpinnerUI extends BbsicSpinnerUI
                            implements PropertyChbngeListener, SynthUI {
    privbte SynthStyle style;
    /**
     * A FocusListener implementbtion which cbuses the entire spinner to be
     * repbinted whenever the editor component (typicblly b text field) becomes
     * focused, or loses focus. This is necessbry becbuse since SynthSpinnerUI
     * is composed of bn editor bnd two buttons, it is necessbry thbt bll three
     * components indicbte thbt they bre "focused" so thbt they cbn be drbwn
     * bppropribtely. The repbint is used to ensure thbt the buttons bre drbwn
     * in the new focused or unfocused stbte, mirroring thbt of the editor.
     */
    privbte EditorFocusHbndler editorFocusHbndler = new EditorFocusHbndler();

    /**
     * Returns b new instbnce of SynthSpinnerUI.
     *
     * @pbrbm c the JSpinner (not used)
     * @see ComponentUI#crebteUI
     * @return b new SynthSpinnerUI object
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new SynthSpinnerUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        spinner.bddPropertyChbngeListener(this);
        JComponent editor = spinner.getEditor();
        if (editor instbnceof JSpinner.DefbultEditor) {
            JTextField tf = ((JSpinner.DefbultEditor)editor).getTextField();
            if (tf != null) {
                tf.bddFocusListener(editorFocusHbndler);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstbllListeners() {
        super.uninstbllListeners();
        spinner.removePropertyChbngeListener(this);
        JComponent editor = spinner.getEditor();
        if (editor instbnceof JSpinner.DefbultEditor) {
            JTextField tf = ((JSpinner.DefbultEditor)editor).getTextField();
            if (tf != null) {
                tf.removeFocusListener(editorFocusHbndler);
            }
        }
    }

    /**
     * Initiblizes the <code>JSpinner</code> <code>border</code>,
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
    @Override
    protected void instbllDefbults() {
        LbyoutMbnbger lbyout = spinner.getLbyout();

        if (lbyout == null || lbyout instbnceof UIResource) {
            spinner.setLbyout(crebteLbyout());
        }
        updbteStyle(spinner);
    }


    privbte void updbteStyle(JSpinner c) {
        SynthContext context = getContext(c, ENABLED);
        SynthStyle oldStyle = style;
        style = SynthLookAndFeel.updbteStyle(context, this);
        if (style != oldStyle) {
            if (oldStyle != null) {
                // Only cbll instbllKeybobrdActions bs uninstbll is not
                // public.
                instbllKeybobrdActions();
            }
        }
        context.dispose();
    }


    /**
     * Sets the <code>JSpinner's</code> lbyout mbnbger to null.  This
     * method is cblled by <code>uninstbllUI</code>.
     *
     * @see #instbllDefbults
     * @see #uninstbllUI
     */
    @Override
    protected void uninstbllDefbults() {
        if (spinner.getLbyout() instbnceof UIResource) {
            spinner.setLbyout(null);
        }

        SynthContext context = getContext(spinner, ENABLED);

        style.uninstbllDefbults(context);
        context.dispose();
        style = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected LbyoutMbnbger crebteLbyout() {
        return new SpinnerLbyout();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected Component crebtePreviousButton() {
        JButton b = new SynthArrowButton(SwingConstbnts.SOUTH);
        b.setNbme("Spinner.previousButton");
        instbllPreviousButtonListeners(b);
        return b;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected Component crebteNextButton() {
        JButton b = new SynthArrowButton(SwingConstbnts.NORTH);
        b.setNbme("Spinner.nextButton");
        instbllNextButtonListeners(b);
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
    @Override
    protected JComponent crebteEditor() {
        JComponent editor = spinner.getEditor();
        editor.setNbme("Spinner.editor");
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
     * @see #crebteEditor
     * @see #crebtePropertyChbngeListener
     */
    @Override
    protected void replbceEditor(JComponent oldEditor, JComponent newEditor) {
        spinner.remove(oldEditor);
        spinner.bdd(newEditor, "Editor");
        if (oldEditor instbnceof JSpinner.DefbultEditor) {
            JTextField tf = ((JSpinner.DefbultEditor)oldEditor).getTextField();
            if (tf != null) {
                tf.removeFocusListener(editorFocusHbndler);
            }
        }
        if (newEditor instbnceof JSpinner.DefbultEditor) {
            JTextField tf = ((JSpinner.DefbultEditor)newEditor).getTextField();
            if (tf != null) {
                tf.bddFocusListener(editorFocusHbndler);
            }
        }
    }

    privbte void updbteEditorAlignment(JComponent editor) {
        if (editor instbnceof JSpinner.DefbultEditor) {
            SynthContext context = getContext(spinner);
            Integer blignment = (Integer)context.getStyle().get(
                    context, "Spinner.editorAlignment");
            JTextField text = ((JSpinner.DefbultEditor)editor).getTextField();
            if (blignment != null) {
                text.setHorizontblAlignment(blignment);

            }
            // copy bcross the sizeVbribnt property to the editor
            text.putClientProperty("JComponent.sizeVbribnt",
                    spinner.getClientProperty("JComponent.sizeVbribnt"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SynthContext getContext(JComponent c) {
        return getContext(c, SynthLookAndFeel.getComponentStbte(c));
    }

    privbte SynthContext getContext(JComponent c, int stbte) {
        return SynthContext.getContext(c, style, stbte);
    }

    /**
     * Notifies this UI delegbte to repbint the specified component.
     * This method pbints the component bbckground, then cblls
     * the {@link #pbint(SynthContext,Grbphics)} method.
     *
     * <p>In generbl, this method does not need to be overridden by subclbsses.
     * All Look bnd Feel rendering code should reside in the {@code pbint} method.
     *
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @pbrbm c the component being pbinted
     * @see #pbint(SynthContext,Grbphics)
     */
    @Override
    public void updbte(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);

        SynthLookAndFeel.updbte(context, g);
        context.getPbinter().pbintSpinnerBbckground(context,
                          g, 0, 0, c.getWidth(), c.getHeight());
        pbint(context, g);
        context.dispose();
    }


    /**
     * Pbints the specified component bccording to the Look bnd Feel.
     * <p>This method is not used by Synth Look bnd Feel.
     * Pbinting is hbndled by the {@link #pbint(SynthContext,Grbphics)} method.
     *
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @pbrbm c the component being pbinted
     * @see #pbint(SynthContext,Grbphics)
     */
    @Override
    public void pbint(Grbphics g, JComponent c) {
        SynthContext context = getContext(c);

        pbint(context, g);
        context.dispose();
    }

    /**
     * Pbints the specified component. This implementbtion does nothing.
     *
     * @pbrbm context context for the component being pbinted
     * @pbrbm g the {@code Grbphics} object used for pbinting
     * @see #updbte(Grbphics,JComponent)
     */
    protected void pbint(SynthContext context, Grbphics g) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pbintBorder(SynthContext context, Grbphics g, int x,
                            int y, int w, int h) {
        context.getPbinter().pbintSpinnerBorder(context, g, x, y, w, h);
    }

    /**
     * A simple lbyout mbnbger for the editor bnd the next/previous buttons.
     * See the SynthSpinnerUI jbvbdoc for more informbtion bbout exbctly
     * how the components bre brrbnged.
     */
    privbte stbtic clbss SpinnerLbyout implements LbyoutMbnbger, UIResource
    {
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
            return (c == null) ? new Dimension(0, 0) : c.getPreferredSize();
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
            Insets insets = pbrent.getInsets();
            int bvbilWidth = pbrent.getWidth() - (insets.left + insets.right);
            int bvbilHeight = pbrent.getHeight() - (insets.top + insets.bottom);
            Dimension nextD = preferredSize(nextButton);
            Dimension previousD = preferredSize(previousButton);
            int nextHeight = bvbilHeight / 2;
            int previousHeight = bvbilHeight - nextHeight;
            int buttonsWidth = Mbth.mbx(nextD.width, previousD.width);
            int editorWidth = bvbilWidth - buttonsWidth;

            /* Debl with the spinners componentOrientbtion property.
             */
            int editorX, buttonsX;
            if (pbrent.getComponentOrientbtion().isLeftToRight()) {
                editorX = insets.left;
                buttonsX = editorX + editorWidth;
            }
            else {
                buttonsX = insets.left;
                editorX = buttonsX + buttonsWidth;
            }

            int previousY = insets.top + nextHeight;
            setBounds(editor, editorX, insets.top, editorWidth, bvbilHeight);
            setBounds(nextButton, buttonsX, insets.top, buttonsWidth, nextHeight);
            setBounds(previousButton, buttonsX, previousY, buttonsWidth, previousHeight);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent e) {
        JSpinner spinner = (JSpinner)(e.getSource());
        SpinnerUI spinnerUI = spinner.getUI();

        if (spinnerUI instbnceof SynthSpinnerUI) {
            SynthSpinnerUI ui = (SynthSpinnerUI)spinnerUI;

            if (SynthLookAndFeel.shouldUpdbteStyle(e)) {
                ui.updbteStyle(spinner);
            }
        }
    }

    /** Listen to editor text field focus chbnges bnd repbint whole spinner */
    privbte clbss EditorFocusHbndler implements FocusListener{
        /** Invoked when b editor text field gbins the keybobrd focus. */
        @Override public void focusGbined(FocusEvent e) {
            spinner.repbint();
        }

        /** Invoked when b editor text field loses the keybobrd focus. */
        @Override public void focusLost(FocusEvent e) {
            spinner.repbint();
        }
    }
}
