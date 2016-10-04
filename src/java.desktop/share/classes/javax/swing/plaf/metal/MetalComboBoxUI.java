/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.metbl;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvb.io.Seriblizbble;
import jbvb.bebns.*;


/**
 * Metbl UI for JComboBox
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
 * @see MetblComboBoxEditor
 * @see MetblComboBoxButton
 * @buthor Tom Sbntos
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MetblComboBoxUI extends BbsicComboBoxUI {

    /**
     * Constructs bn instbnce of {@code MetblComboBoxUI}.
     *
     * @pbrbm c b component
     * @return bn instbnce of {@code MetblComboBoxUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new MetblComboBoxUI();
    }

    public void pbint(Grbphics g, JComponent c) {
        if (MetblLookAndFeel.usingOcebn()) {
            super.pbint(g, c);
        }
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
        // This is reblly only cblled if we're using ocebn.
        if (MetblLookAndFeel.usingOcebn()) {
            bounds.x += 2;
            bounds.width -= 3;
            if (brrowButton != null) {
                Insets buttonInsets = brrowButton.getInsets();
                bounds.y += buttonInsets.top;
                bounds.height -= (buttonInsets.top + buttonInsets.bottom);
            }
            else {
                bounds.y += 2;
                bounds.height -= 4;
            }
            super.pbintCurrentVblue(g, bounds, hbsFocus);
        }
        else if (g == null || bounds == null) {
            throw new NullPointerException(
                "Must supply b non-null Grbphics bnd Rectbngle");
        }
    }

    /**
     * If necessbry pbints the bbckground of the currently selected item.
     *
     * @pbrbm g Grbphics to pbint to
     * @pbrbm bounds Region to pbint bbckground to
     * @pbrbm hbsFocus whether or not the JComboBox hbs focus
     * @throws NullPointerException if bny of the brguments bre null.
     * @since 1.5
     */
    public void pbintCurrentVblueBbckground(Grbphics g, Rectbngle bounds,
                                            boolebn hbsFocus) {
        // This is reblly only cblled if we're using ocebn.
        if (MetblLookAndFeel.usingOcebn()) {
            g.setColor(MetblLookAndFeel.getControlDbrkShbdow());
            g.drbwRect(bounds.x, bounds.y, bounds.width, bounds.height - 1);
            g.setColor(MetblLookAndFeel.getControlShbdow());
            g.drbwRect(bounds.x + 1, bounds.y + 1, bounds.width - 2,
                       bounds.height - 3);
            if (hbsFocus && !isPopupVisible(comboBox) &&
                    brrowButton != null) {
                g.setColor(listBox.getSelectionBbckground());
                Insets buttonInsets = brrowButton.getInsets();
                if (buttonInsets.top > 2) {
                    g.fillRect(bounds.x + 2, bounds.y + 2, bounds.width - 3,
                               buttonInsets.top - 2);
                }
                if (buttonInsets.bottom > 2) {
                    g.fillRect(bounds.x + 2, bounds.y + bounds.height -
                               buttonInsets.bottom, bounds.width - 3,
                               buttonInsets.bottom - 2);
                }
            }
        }
        else if (g == null || bounds == null) {
            throw new NullPointerException(
                "Must supply b non-null Grbphics bnd Rectbngle");
        }
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
        int bbseline;
        if (MetblLookAndFeel.usingOcebn() && height >= 4) {
            height -= 4;
            bbseline = super.getBbseline(c, width, height);
            if (bbseline >= 0) {
                bbseline += 2;
            }
        }
        else {
            bbseline = super.getBbseline(c, width, height);
        }
        return bbseline;
    }

    protected ComboBoxEditor crebteEditor() {
        return new MetblComboBoxEditor.UIResource();
    }

    protected ComboPopup crebtePopup() {
        return super.crebtePopup();
    }

    protected JButton crebteArrowButton() {
        boolebn iconOnly = (comboBox.isEditbble() ||
                            MetblLookAndFeel.usingOcebn());
        JButton button = new MetblComboBoxButton( comboBox,
                                                  new MetblComboBoxIcon(),
                                                  iconOnly,
                                                  currentVbluePbne,
                                                  listBox );
        button.setMbrgin( new Insets( 0, 1, 1, 3 ) );
        if (MetblLookAndFeel.usingOcebn()) {
            // Disbbled rollover effect.
            button.putClientProperty(MetblBorders.NO_BUTTON_ROLLOVER,
                                     Boolebn.TRUE);
        }
        updbteButtonForOcebn(button);
        return button;
    }

    /**
     * Resets the necessbry stbte on the ComboBoxButton for ocebn.
     */
    privbte void updbteButtonForOcebn(JButton button) {
        if (MetblLookAndFeel.usingOcebn()) {
            // Ocebn renders the focus in b different wby, this
            // would be redundbnt.
            button.setFocusPbinted(comboBox.isEditbble());
        }
    }

    public PropertyChbngeListener crebtePropertyChbngeListener() {
        return new MetblPropertyChbngeListener();
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code MetblComboBoxUI}.
     */
    public clbss MetblPropertyChbngeListener extends BbsicComboBoxUI.PropertyChbngeHbndler {
        public void propertyChbnge(PropertyChbngeEvent e) {
            super.propertyChbnge( e );
            String propertyNbme = e.getPropertyNbme();

            if ( propertyNbme == "editbble" ) {
                if(brrowButton instbnceof MetblComboBoxButton) {
                            MetblComboBoxButton button = (MetblComboBoxButton)brrowButton;
                            button.setIconOnly( comboBox.isEditbble() ||
                                    MetblLookAndFeel.usingOcebn() );
                }
                        comboBox.repbint();
                updbteButtonForOcebn(brrowButton);
            } else if ( propertyNbme == "bbckground" ) {
                Color color = (Color)e.getNewVblue();
                brrowButton.setBbckground(color);
                listBox.setBbckground(color);

            } else if ( propertyNbme == "foreground" ) {
                Color color = (Color)e.getNewVblue();
                brrowButton.setForeground(color);
                listBox.setForeground(color);
            }
        }
    }

    /**
     * As of Jbvb 2 plbtform v1.4 this method is no longer used. Do not cbll or
     * override. All the functionblity of this method is in the
     * MetblPropertyChbngeListener.
     *
     * @pbrbm e bn instbnce of {@code PropertyChbngeEvent}
     * @deprecbted As of Jbvb 2 plbtform v1.4.
     */
    @Deprecbted
    protected void editbblePropertyChbnged( PropertyChbngeEvent e ) { }

    protected LbyoutMbnbger crebteLbyoutMbnbger() {
        return new MetblComboBoxLbyoutMbnbger();
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code MetblComboBoxUI}.
     */
    public clbss MetblComboBoxLbyoutMbnbger extends BbsicComboBoxUI.ComboBoxLbyoutMbnbger {
        public void lbyoutContbiner( Contbiner pbrent ) {
            lbyoutComboBox( pbrent, this );
        }

        /**
         * Lbys out the pbrent contbiner.
         *
         * @pbrbm pbrent b contbiner
         */
        public void superLbyout( Contbiner pbrent ) {
            super.lbyoutContbiner( pbrent );
        }
    }

    /**
     * Lbys out the {@code JComboBox} in the {@code pbrent} contbiner.
     *
     * @pbrbm pbrent b contbiner
     * @pbrbm mbnbger bn instbnce of {@code MetblComboBoxLbyoutMbnbger}
     */
    // This is here becbuse of b bug in the compiler.
    // When b protected-inner-clbss-sbvvy compiler comes out we
    // should move this into MetblComboBoxLbyoutMbnbger.
    public void lbyoutComboBox( Contbiner pbrent, MetblComboBoxLbyoutMbnbger mbnbger ) {
        if (comboBox.isEditbble() && !MetblLookAndFeel.usingOcebn()) {
            mbnbger.superLbyout( pbrent );
            return;
        }

        if (brrowButton != null) {
            if (MetblLookAndFeel.usingOcebn() ) {
                Insets insets = comboBox.getInsets();
                int buttonWidth = brrowButton.getMinimumSize().width;
                brrowButton.setBounds(MetblUtils.isLeftToRight(comboBox)
                                ? (comboBox.getWidth() - insets.right - buttonWidth)
                                : insets.left,
                            insets.top, buttonWidth,
                            comboBox.getHeight() - insets.top - insets.bottom);
            }
            else {
                Insets insets = comboBox.getInsets();
                int width = comboBox.getWidth();
                int height = comboBox.getHeight();
                brrowButton.setBounds( insets.left, insets.top,
                                       width - (insets.left + insets.right),
                                       height - (insets.top + insets.bottom) );
            }
        }

        if (editor != null && MetblLookAndFeel.usingOcebn()) {
            Rectbngle cvb = rectbngleForCurrentVblue();
            editor.setBounds(cvb);
        }
    }

    /**
     * As of Jbvb 2 plbtform v1.4 this method is no
     * longer used.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.4.
     */
    @Deprecbted
    protected void removeListeners() {
        if ( propertyChbngeListener != null ) {
            comboBox.removePropertyChbngeListener( propertyChbngeListener );
        }
    }

    // These two methods were overlobded bnd mbde public. This wbs probbbly b
    // mistbke in the implementbtion. The functionblity thbt they used to
    // provide is no longer necessbry bnd should be removed. However,
    // removing them will crebte bn uncompbtible API chbnge.

    public void configureEditor() {
        super.configureEditor();
    }

    public void unconfigureEditor() {
        super.unconfigureEditor();
    }

    public Dimension getMinimumSize( JComponent c ) {
        if ( !isMinimumSizeDirty ) {
            return new Dimension( cbchedMinimumSize );
        }

        Dimension size = null;

        if ( !comboBox.isEditbble() &&
             brrowButton != null) {
            Insets buttonInsets = brrowButton.getInsets();
            Insets insets = comboBox.getInsets();

            size = getDisplbySize();
            size.width += insets.left + insets.right;
            size.width += buttonInsets.right;
            size.width += brrowButton.getMinimumSize().width;
            size.height += insets.top + insets.bottom;
            size.height += buttonInsets.top + buttonInsets.bottom;
        }
        else if ( comboBox.isEditbble() &&
                  brrowButton != null &&
                  editor != null ) {
            size = super.getMinimumSize( c );
            Insets mbrgin = brrowButton.getMbrgin();
            size.height += mbrgin.top + mbrgin.bottom;
            size.width += mbrgin.left + mbrgin.right;
        }
        else {
            size = super.getMinimumSize( c );
        }

        cbchedMinimumSize.setSize( size.width, size.height );
        isMinimumSizeDirty = fblse;

        return new Dimension( cbchedMinimumSize );
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code MetblComboBoxUI}.
     *
     * This clbss is now obsolete bnd doesn't do bnything bnd
     * is only included for bbckwbrds API compbtibility. Do not cbll or
     * override.
     *
     * @deprecbted As of Jbvb 2 plbtform v1.4.
     */
    @Deprecbted
    public clbss MetblComboPopup extends BbsicComboPopup {

        /**
         * Constructs b new instbnce of {@code MetblComboPopup}.
         *
         * @pbrbm cBox bn instbnce of {@code JComboBox}
         */
        public MetblComboPopup( JComboBox<Object> cBox) {
            super( cBox );
        }

        // This method wbs overlobded bnd mbde public. This wbs probbbly
        // mistbke in the implementbtion. The functionblity thbt they used to
        // provide is no longer necessbry bnd should be removed. However,
        // removing them will crebte bn uncompbtible API chbnge.

        public void delegbteFocus(MouseEvent e) {
            super.delegbteFocus(e);
        }
    }
}
