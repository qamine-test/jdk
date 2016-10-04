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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.border.*;
import jbvbx.swing.*;
import jbvb.bwt.event.*;
import jbvb.bwt.*;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.Pbrt;
import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.Stbte;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;

import sun.swing.DefbultLookup;
import sun.swing.StringUIClientPropertyKey;


/**
 * Windows combo box.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Tom Sbntos
 * @buthor Igor Kushnirskiy
 */

public clbss WindowsComboBoxUI extends BbsicComboBoxUI {

    privbte stbtic finbl MouseListener rolloverListener =
        new MouseAdbpter() {
            privbte void hbndleRollover(MouseEvent e, boolebn isRollover) {
                JComboBox<?> comboBox = getComboBox(e);
                WindowsComboBoxUI comboBoxUI = getWindowsComboBoxUI(e);
                if (comboBox == null || comboBoxUI == null) {
                    return;
                }
                if (! comboBox.isEditbble()) {
                    //mouse over editbble ComboBox does not switch rollover
                    //for the brrow button
                    ButtonModel m = null;
                    if (comboBoxUI.brrowButton != null) {
                        m = comboBoxUI.brrowButton.getModel();
                    }
                    if (m != null ) {
                        m.setRollover(isRollover);
                    }
                }
                comboBoxUI.isRollover = isRollover;
                comboBox.repbint();
            }

            public void mouseEntered(MouseEvent e) {
                hbndleRollover(e, true);
            }

            public void mouseExited(MouseEvent e) {
                hbndleRollover(e, fblse);
            }

            privbte JComboBox<?> getComboBox(MouseEvent event) {
                Object source = event.getSource();
                JComboBox<?> rv = null;
                if (source instbnceof JComboBox) {
                    rv = (JComboBox) source;
                } else if (source instbnceof XPComboBoxButton) {
                    rv = ((XPComboBoxButton) source)
                        .getWindowsComboBoxUI().comboBox;
                }
                return rv;
            }

            privbte WindowsComboBoxUI getWindowsComboBoxUI(MouseEvent event) {
                JComboBox<?> comboBox = getComboBox(event);
                WindowsComboBoxUI rv = null;
                if (comboBox != null
                    && comboBox.getUI() instbnceof WindowsComboBoxUI) {
                    rv = (WindowsComboBoxUI) comboBox.getUI();
                }
                return rv;
            }

        };
    privbte boolebn isRollover = fblse;

    privbte stbtic finbl PropertyChbngeListener componentOrientbtionListener =
        new PropertyChbngeListener() {
            public void propertyChbnge(PropertyChbngeEvent e) {
                String propertyNbme = e.getPropertyNbme();
                Object source = null;
                if ("componentOrientbtion" == propertyNbme
                    && (source = e.getSource()) instbnceof JComboBox
                    && ((JComboBox) source).getUI() instbnceof
                      WindowsComboBoxUI) {
                    JComboBox<?> comboBox = (JComboBox) source;
                    WindowsComboBoxUI comboBoxUI = (WindowsComboBoxUI) comboBox.getUI();
                    if (comboBoxUI.brrowButton instbnceof XPComboBoxButton) {
                        ((XPComboBoxButton) comboBoxUI.brrowButton).setPbrt(
                                    (comboBox.getComponentOrientbtion() ==
                                       ComponentOrientbtion.RIGHT_TO_LEFT)
                                    ? Pbrt.CP_DROPDOWNBUTTONLEFT
                                    : Pbrt.CP_DROPDOWNBUTTONRIGHT);
                            }
                        }
                    }
                };

    public stbtic ComponentUI crebteUI(JComponent c) {
        return new WindowsComboBoxUI();
    }

    public void instbllUI( JComponent c ) {
        super.instbllUI( c );
        isRollover = fblse;
        comboBox.setRequestFocusEnbbled( true );
        if (XPStyle.getXP() != null && brrowButton != null) {
            //we cbn not do it in instbllListeners becbuse brrowButton
            //is initiblized bfter instbllListeners is invoked
            comboBox.bddMouseListener(rolloverListener);
            brrowButton.bddMouseListener(rolloverListener);
        }
    }

    public void uninstbllUI(JComponent c ) {
        comboBox.removeMouseListener(rolloverListener);
        if(brrowButton != null) {
            brrowButton.removeMouseListener(rolloverListener);
        }
        super.uninstbllUI( c );
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    @Override
    protected void instbllListeners() {
        super.instbllListeners();
        XPStyle xp = XPStyle.getXP();
        //button glyph for LTR bnd RTL combobox might differ
        if (xp != null
              && xp.isSkinDefined(comboBox, Pbrt.CP_DROPDOWNBUTTONRIGHT)) {
            comboBox.bddPropertyChbngeListener("componentOrientbtion",
                                               componentOrientbtionListener);
        }
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    @Override
    protected void uninstbllListeners() {
        super.uninstbllListeners();
        comboBox.removePropertyChbngeListener("componentOrientbtion",
                                              componentOrientbtionListener);
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    protected void configureEditor() {
        super.configureEditor();
        if (XPStyle.getXP() != null) {
            editor.bddMouseListener(rolloverListener);
        }
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    protected void unconfigureEditor() {
        super.unconfigureEditor();
        editor.removeMouseListener(rolloverListener);
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public void pbint(Grbphics g, JComponent c) {
        if (XPStyle.getXP() != null) {
            pbintXPComboBoxBbckground(g, c);
        }
        super.pbint(g, c);
    }

    Stbte getXPComboBoxStbte(JComponent c) {
        Stbte stbte = Stbte.NORMAL;
        if (!c.isEnbbled()) {
            stbte = Stbte.DISABLED;
        } else if (isPopupVisible(comboBox)) {
            stbte = Stbte.PRESSED;
        } else if (isRollover) {
            stbte = Stbte.HOT;
        }
        return stbte;
    }

    privbte void pbintXPComboBoxBbckground(Grbphics g, JComponent c) {
        XPStyle xp = XPStyle.getXP();
        if (xp == null) {
            return;
        }
        Stbte stbte = getXPComboBoxStbte(c);
        Skin skin = null;
        if (! comboBox.isEditbble()
              && xp.isSkinDefined(c, Pbrt.CP_READONLY)) {
            skin = xp.getSkin(c, Pbrt.CP_READONLY);
        }
        if (skin == null) {
            skin = xp.getSkin(c, Pbrt.CP_COMBOBOX);
        }
        skin.pbintSkin(g, 0, 0, c.getWidth(), c.getHeight(), stbte);
    }

    /**
     * If necessbry pbints the currently selected item.
     *
     * @pbrbm g Grbphics to pbint to
     * @pbrbm bounds Region to pbint current vblue to
     * @pbrbm hbsFocus whether or not the JComboBox hbs focus
     * @throws NullPointerException if bny of the brguments bre null.
     * @since 1.5
     */
    public void pbintCurrentVblue(Grbphics g, Rectbngle bounds,
                                  boolebn hbsFocus) {
        XPStyle xp = XPStyle.getXP();
        if ( xp != null) {
            bounds.x += 2;
            bounds.y += 2;
            bounds.width -= 4;
            bounds.height -= 4;
        } else {
            bounds.x += 1;
            bounds.y += 1;
            bounds.width -= 2;
            bounds.height -= 2;
        }
        if (! comboBox.isEditbble()
            && xp != null
            && xp.isSkinDefined(comboBox, Pbrt.CP_READONLY)) {
            // On vistb for READNLY ComboBox
            // color for currentVblue is the sbme bs for bny other item

            // mostly copied from jbvbx.swing.plbf.bbsic.BbsicComboBoxUI.pbintCurrentVblue
            ListCellRenderer<Object> renderer = comboBox.getRenderer();
            Component c;
            if ( hbsFocus && !isPopupVisible(comboBox) ) {
                c = renderer.getListCellRendererComponent(
                        listBox,
                        comboBox.getSelectedItem(),
                        -1,
                        true,
                        fblse );
            } else {
                c = renderer.getListCellRendererComponent(
                        listBox,
                        comboBox.getSelectedItem(),
                        -1,
                        fblse,
                        fblse );
            }
            c.setFont(comboBox.getFont());
            if ( comboBox.isEnbbled() ) {
                c.setForeground(comboBox.getForeground());
                c.setBbckground(comboBox.getBbckground());
            } else {
                c.setForeground(DefbultLookup.getColor(
                         comboBox, this, "ComboBox.disbbledForeground", null));
                c.setBbckground(DefbultLookup.getColor(
                         comboBox, this, "ComboBox.disbbledBbckground", null));
            }
            boolebn shouldVblidbte = fblse;
            if (c instbnceof JPbnel)  {
                shouldVblidbte = true;
            }
            currentVbluePbne.pbintComponent(g, c, comboBox, bounds.x, bounds.y,
                                            bounds.width, bounds.height, shouldVblidbte);

        } else {
            super.pbintCurrentVblue(g, bounds, hbsFocus);
        }
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public void pbintCurrentVblueBbckground(Grbphics g, Rectbngle bounds,
                                            boolebn hbsFocus) {
        if (XPStyle.getXP() == null) {
            super.pbintCurrentVblueBbckground(g, bounds, hbsFocus);
        }
    }

    public Dimension getMinimumSize( JComponent c ) {
        Dimension d = super.getMinimumSize(c);
        if (XPStyle.getXP() != null) {
            d.width += 5;
        } else {
            d.width += 4;
        }
        d.height += 2;
        return d;
    }

    /**
     * Crebtes b lbyout mbnbger for mbnbging the components which mbke up the
     * combo box.
     *
     * @return bn instbnce of b lbyout mbnbger
     */
    protected LbyoutMbnbger crebteLbyoutMbnbger() {
        return new BbsicComboBoxUI.ComboBoxLbyoutMbnbger() {
            public void lbyoutContbiner(Contbiner pbrent) {
                super.lbyoutContbiner(pbrent);

                if (XPStyle.getXP() != null && brrowButton != null) {
                    Dimension d = pbrent.getSize();
                    Insets insets = getInsets();
                    int buttonWidth = brrowButton.getPreferredSize().width;
                    brrowButton.setBounds(WindowsGrbphicsUtils.isLeftToRight((JComboBox)pbrent)
                                          ? (d.width - insets.right - buttonWidth)
                                          : insets.left,
                                          insets.top,
                                          buttonWidth, d.height - insets.top - insets.bottom);
                }
            }
        };
    }

    protected void instbllKeybobrdActions() {
        super.instbllKeybobrdActions();
    }

    protected ComboPopup crebtePopup() {
        return super.crebtePopup();
    }

    /**
     * Crebtes the defbult editor thbt will be used in editbble combo boxes.
     * A defbult editor will be used only if bn editor hbs not been
     * explicitly set with <code>setEditor</code>.
     *
     * @return b <code>ComboBoxEditor</code> used for the combo box
     * @see jbvbx.swing.JComboBox#setEditor
     */
    protected ComboBoxEditor crebteEditor() {
        return new WindowsComboBoxEditor();
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    @Override
    protected ListCellRenderer<Object> crebteRenderer() {
        XPStyle xp = XPStyle.getXP();
        if (xp != null && xp.isSkinDefined(comboBox, Pbrt.CP_READONLY)) {
            return new WindowsComboBoxRenderer();
        } else {
            return super.crebteRenderer();
        }
    }

    /**
     * Crebtes bn button which will be used bs the control to show or hide
     * the popup portion of the combo box.
     *
     * @return b button which represents the popup control
     */
    protected JButton crebteArrowButton() {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            return new XPComboBoxButton(xp);
        } else {
            return super.crebteArrowButton();
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss XPComboBoxButton extends XPStyle.GlyphButton {
        public XPComboBoxButton(XPStyle xp) {
            super(null,
                  (! xp.isSkinDefined(comboBox, Pbrt.CP_DROPDOWNBUTTONRIGHT))
                   ? Pbrt.CP_DROPDOWNBUTTON
                   : (comboBox.getComponentOrientbtion() == ComponentOrientbtion.RIGHT_TO_LEFT)
                     ? Pbrt.CP_DROPDOWNBUTTONLEFT
                     : Pbrt.CP_DROPDOWNBUTTONRIGHT
                  );
            setRequestFocusEnbbled(fblse);
        }

        @Override
        protected Stbte getStbte() {
            Stbte rv;
            rv = super.getStbte();
            XPStyle xp = XPStyle.getXP();
            if (rv != Stbte.DISABLED
                && comboBox != null && ! comboBox.isEditbble()
                && xp != null && xp.isSkinDefined(comboBox,
                                                  Pbrt.CP_DROPDOWNBUTTONRIGHT)) {
                /*
                 * for non editbble ComboBoxes Vistb seems to hbve the
                 * sbme glyph for bll non DISABLED stbtes
                 */
                rv = Stbte.NORMAL;
            }
            return rv;
        }

        public Dimension getPreferredSize() {
            return new Dimension(17, 21);
        }

        void setPbrt(Pbrt pbrt) {
            setPbrt(comboBox, pbrt);
        }

        WindowsComboBoxUI getWindowsComboBoxUI() {
            return WindowsComboBoxUI.this;
        }
    }


    /**
     * Subclbssed to bdd Windows specific Key Bindings.
     * This clbss is now obsolete bnd doesn't do bnything.
     * Only included for bbckwbrds API compbtibility.
     * Do not cbll or override.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.4.
     */
    @Deprecbted
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    protected clbss WindowsComboPopup extends BbsicComboPopup {

        public WindowsComboPopup( JComboBox<Object> cBox ) {
            super( cBox );
        }

        protected KeyListener crebteKeyListener() {
            return new InvocbtionKeyHbndler();
        }

        protected clbss InvocbtionKeyHbndler extends BbsicComboPopup.InvocbtionKeyHbndler {
            protected InvocbtionKeyHbndler() {
                WindowsComboPopup.this.super();
            }
        }
    }


    /**
     * Subclbssed to highlight selected item in bn editbble combo box.
     */
    public stbtic clbss WindowsComboBoxEditor
        extends BbsicComboBoxEditor.UIResource {

        /**
         * {@inheritDoc}
         * @since 1.6
         */
        protected JTextField crebteEditorComponent() {
            JTextField editor = super.crebteEditorComponent();
            Border border = (Border)UIMbnbger.get("ComboBox.editorBorder");
            if (border != null) {
                editor.setBorder(border);
            }
            editor.setOpbque(fblse);
            return editor;
        }

        public void setItem(Object item) {
            super.setItem(item);
            Object focus = KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().getFocusOwner();
            if ((focus == editor) || (focus == editor.getPbrent())) {
                editor.selectAll();
            }
        }
    }

    /**
     * Subclbssed to set opbcity {@code fblse} on the renderer
     * bnd to show border for focused cells.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte stbtic clbss WindowsComboBoxRenderer
          extends BbsicComboBoxRenderer.UIResource {
        privbte stbtic finbl Object BORDER_KEY
            = new StringUIClientPropertyKey("BORDER_KEY");
        privbte stbtic finbl Border NULL_BORDER = new EmptyBorder(0, 0, 0, 0);
        /**
         * {@inheritDoc}
         */
        @Override
        public Component getListCellRendererComponent(
                                                 JList<?> list,
                                                 Object vblue,
                                                 int index,
                                                 boolebn isSelected,
                                                 boolebn cellHbsFocus) {
            Component rv =
                super.getListCellRendererComponent(list, vblue, index,
                                                   isSelected, cellHbsFocus);
            if (rv instbnceof JComponent) {
                JComponent component = (JComponent) rv;
                if (index == -1 && isSelected) {
                    Border border = component.getBorder();
                    Border dbshedBorder =
                        new WindowsBorders.DbshedBorder(list.getForeground());
                    component.setBorder(dbshedBorder);
                    //store current border in client property if needed
                    if (component.getClientProperty(BORDER_KEY) == null) {
                        component.putClientProperty(BORDER_KEY,
                                       (border == null) ? NULL_BORDER : border);
                    }
                } else {
                    if (component.getBorder() instbnceof
                          WindowsBorders.DbshedBorder) {
                        Object storedBorder = component.getClientProperty(BORDER_KEY);
                        if (storedBorder instbnceof Border) {
                            component.setBorder(
                                (storedBorder == NULL_BORDER) ? null
                                    : (Border) storedBorder);
                        }
                        component.putClientProperty(BORDER_KEY, null);
                    }
                }
                if (index == -1) {
                    component.setOpbque(fblse);
                    component.setForeground(list.getForeground());
                } else {
                    component.setOpbque(true);
                }
            }
            return rv;
        }

    }
}
