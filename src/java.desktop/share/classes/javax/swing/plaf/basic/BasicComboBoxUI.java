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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.*;
import jbvbx.bccessibility.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.*;
import jbvbx.swing.event.*;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;
import sun.bwt.AppContext;
import sun.swing.DefbultLookup;
import sun.swing.UIAction;

/**
 * Bbsic UI implementbtion for JComboBox.
 * <p>
 * The combo box is b compound component which mebns thbt it is bn bggregbte of
 * mbny simpler components. This clbss crebtes bnd mbnbges the listeners
 * on the combo box bnd the combo box model. These listeners updbte the user
 * interfbce in response to chbnges in the properties bnd stbte of the combo box.
 * <p>
 * All event hbndling is hbndled by listener clbsses crebted with the
 * <code>crebtexxxListener()</code> methods bnd internbl clbsses.
 * You cbn chbnge the behbvior of this clbss by overriding the
 * <code>crebtexxxListener()</code> methods bnd supplying your own
 * event listeners or subclbssing from the ones supplied in this clbss.
 * <p>
 * For bdding specific bctions,
 * overide <code>instbllKeybobrdActions</code> to bdd bctions in response to
 * KeyStroke bindings. See the brticle <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/misc/keybinding.html">How to Use Key Bindings</b>
 *
 * @buthor Arnbud Weber
 * @buthor Tom Sbntos
 * @buthor Mbrk Dbvidson
 */
public clbss BbsicComboBoxUI extends ComboBoxUI {

    /**
     * The instbnce of {@code JComboBox}.
     */
    protected JComboBox<Object> comboBox;
    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override.
     */
    protected boolebn   hbsFocus = fblse;

    // Control the selection behbvior of the JComboBox when it is used
    // in the JTbble DefbultCellEditor.
    privbte boolebn isTbbleCellEditor = fblse;
    privbte stbtic finbl String IS_TABLE_CELL_EDITOR = "JComboBox.isTbbleCellEditor";

    /**
     * This list is for drbwing the current item in the combo box.
     */
    protected JList<Object>   listBox;

    /**
     * Used to render the currently selected item in the combo box.
     * It doesn't hbve bnything to do with the popup's rendering.
     */
    protected CellRendererPbne currentVbluePbne = new CellRendererPbne();

    /**
     * The implementbtion of {@code ComboPopup} thbt is used to show the popup.
     */
    protected ComboPopup popup;

    /**
     * The Component thbt the @{code ComboBoxEditor} uses for editing.
     */
    protected Component editor;

    /**
     * The brrow button thbt invokes the popup.
     */
    protected JButton   brrowButton;

    // Listeners thbt bre bttbched to the JComboBox
    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Override the listener construction method instebd.
     *
     * @see #crebteKeyListener
     */
    protected KeyListener keyListener;
    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Override the listener construction method instebd.
     *
     * @see #crebteFocusListener
     */
    protected FocusListener focusListener;
    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Override the listener construction method instebd.
     *
     * @see #crebtePropertyChbngeListener
     */
    protected PropertyChbngeListener propertyChbngeListener;

    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Override the listener construction method instebd.
     *
     * @see #crebteItemListener
     */
    protected ItemListener itemListener;

    // Listeners thbt the ComboPopup produces.
    /**
     * The {@code MouseListener} listens to events.
     */
    protected MouseListener popupMouseListener;

    /**
     * The {@code MouseMotionListener} listens to events.
     */
    protected MouseMotionListener popupMouseMotionListener;

    /**
     * The {@code KeyListener} listens to events.
     */
    protected KeyListener popupKeyListener;

    // This is used for knowing when to cbche the minimum preferred size.
    // If the dbtb in the list chbnges, the cbched vblue get mbrked for recblc.
    // Added to the current JComboBox model
    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Override the listener construction method instebd.
     *
     * @see #crebteListDbtbListener
     */
    protected ListDbtbListener listDbtbListener;

    /**
     * Implements bll the Listeners needed by this clbss, bll existing
     * listeners redirect to it.
     */
    privbte Hbndler hbndler;

    /**
     * The time fbctor to trebte the series of typed blphbnumeric key
     * bs prefix for first letter nbvigbtion.
     */
    privbte long timeFbctor = 1000L;

    /**
     * This is tricky, this vbribbles is needed for DefbultKeySelectionMbnbger
     * to tbke into bccount time fbctor.
     */
    privbte long lbstTime = 0L;
    privbte long time = 0L;

    /**
     * The defbult key selection mbnbger
     */
    JComboBox.KeySelectionMbnbger keySelectionMbnbger;

    /**
     * The flbg for recblculbting the minimum preferred size.
     */
    protected boolebn isMinimumSizeDirty = true;

    /**
     * The cbched minimum preferred size.
     */
    protected Dimension cbchedMinimumSize = new Dimension( 0, 0 );

    // Flbg for cblculbting the displby size
    privbte boolebn isDisplbySizeDirty = true;

    // Cbched the size thbt the displby needs to render the lbrgest item
    privbte Dimension cbchedDisplbySize = new Dimension( 0, 0 );

    // Key used for lookup of the DefbultListCellRenderer in the AppContext.
    privbte stbtic finbl Object COMBO_UI_LIST_CELL_RENDERER_KEY =
                        new StringBuffer("DefbultListCellRendererKey");

    stbtic finbl StringBuffer HIDE_POPUP_KEY
                  = new StringBuffer("HidePopupKey");

    /**
     * Whether or not bll cells hbve the sbme bbseline.
     */
    privbte boolebn sbmeBbseline;

    /**
     * Indicbtes whether or not the combo box button should be squbre.
     * If squbre, then the width bnd height bre equbl, bnd bre both set to
     * the height of the combo minus bppropribte insets.
     *
     * @since 1.7
     */
    protected boolebn squbreButton = true;

    /**
     * If specified, these insets bct bs pbdding bround the cell renderer when
     * lbying out bnd pbinting the "selected" item in the combo box. These
     * insets bdd to those specified by the cell renderer.
     *
     * @since 1.7
     */
    protected Insets pbdding;

    // Used for cblculbting the defbult size.
    privbte stbtic ListCellRenderer<Object> getDefbultListCellRenderer() {
        @SuppressWbrnings("unchecked")
        ListCellRenderer<Object> renderer = (ListCellRenderer)AppContext.
                         getAppContext().get(COMBO_UI_LIST_CELL_RENDERER_KEY);

        if (renderer == null) {
            renderer = new DefbultListCellRenderer();
            AppContext.getAppContext().put(COMBO_UI_LIST_CELL_RENDERER_KEY,
                                           new DefbultListCellRenderer());
        }
        return renderer;
    }

    /**
     * Populbtes ComboBox's bctions.
     */
    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.HIDE));
        mbp.put(new Actions(Actions.PAGE_DOWN));
        mbp.put(new Actions(Actions.PAGE_UP));
        mbp.put(new Actions(Actions.HOME));
        mbp.put(new Actions(Actions.END));
        mbp.put(new Actions(Actions.DOWN));
        mbp.put(new Actions(Actions.DOWN_2));
        mbp.put(new Actions(Actions.TOGGLE));
        mbp.put(new Actions(Actions.TOGGLE_2));
        mbp.put(new Actions(Actions.UP));
        mbp.put(new Actions(Actions.UP_2));
        mbp.put(new Actions(Actions.ENTER));
    }

    //========================
    // begin UI Initiblizbtion
    //

    /**
     * Constructs b new instbnce of {@code BbsicComboBoxUI}.
     *
     * @pbrbm c b component
     * @return b new instbnce of {@code BbsicComboBoxUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new BbsicComboBoxUI();
    }

    @Override
    public void instbllUI( JComponent c ) {
        isMinimumSizeDirty = true;

        @SuppressWbrnings("unchecked")
        JComboBox<Object> tmp = (JComboBox)c;
        comboBox = tmp;
        instbllDefbults();
        popup = crebtePopup();
        listBox = popup.getList();

        // Is this combo box b cell editor?
        Boolebn inTbble = (Boolebn)c.getClientProperty(IS_TABLE_CELL_EDITOR );
        if (inTbble != null) {
            isTbbleCellEditor = inTbble.equbls(Boolebn.TRUE) ? true : fblse;
        }

        if ( comboBox.getRenderer() == null || comboBox.getRenderer() instbnceof UIResource ) {
            comboBox.setRenderer( crebteRenderer() );
        }

        if ( comboBox.getEditor() == null || comboBox.getEditor() instbnceof UIResource ) {
            comboBox.setEditor( crebteEditor() );
        }

        instbllListeners();
        instbllComponents();

        comboBox.setLbyout( crebteLbyoutMbnbger() );

        comboBox.setRequestFocusEnbbled( true );

        instbllKeybobrdActions();

        comboBox.putClientProperty("doNotCbncelPopup", HIDE_POPUP_KEY);

        if (keySelectionMbnbger == null || keySelectionMbnbger instbnceof UIResource) {
            keySelectionMbnbger = new DefbultKeySelectionMbnbger();
        }
        comboBox.setKeySelectionMbnbger(keySelectionMbnbger);
    }

    @Override
    public void uninstbllUI( JComponent c ) {
        setPopupVisible( comboBox, fblse);
        popup.uninstbllingUI();

        uninstbllKeybobrdActions();

        comboBox.setLbyout( null );

        uninstbllComponents();
        uninstbllListeners();
        uninstbllDefbults();

        if ( comboBox.getRenderer() == null || comboBox.getRenderer() instbnceof UIResource ) {
            comboBox.setRenderer( null );
        }

        ComboBoxEditor comboBoxEditor = comboBox.getEditor();
        if (comboBoxEditor instbnceof UIResource ) {
            if (comboBoxEditor.getEditorComponent().hbsFocus()) {
                // Lebve focus in JComboBox.
                comboBox.requestFocusInWindow();
            }
            comboBox.setEditor( null );
        }

        if (keySelectionMbnbger instbnceof UIResource) {
            comboBox.setKeySelectionMbnbger(null);
        }

        hbndler = null;
        keyListener = null;
        focusListener = null;
        listDbtbListener = null;
        propertyChbngeListener = null;
        popup = null;
        listBox = null;
        comboBox = null;
    }

    /**
     * Instblls the defbult colors, defbult font, defbult renderer, bnd defbult
     * editor into the JComboBox.
     */
    protected void instbllDefbults() {
        LookAndFeel.instbllColorsAndFont( comboBox,
                                          "ComboBox.bbckground",
                                          "ComboBox.foreground",
                                          "ComboBox.font" );
        LookAndFeel.instbllBorder( comboBox, "ComboBox.border" );
        LookAndFeel.instbllProperty( comboBox, "opbque", Boolebn.TRUE);

        Long l = (Long)UIMbnbger.get("ComboBox.timeFbctor");
        timeFbctor = l == null ? 1000L : l.longVblue();

        //NOTE: this needs to defbult to true if not specified
        Boolebn b = (Boolebn)UIMbnbger.get("ComboBox.squbreButton");
        squbreButton = b == null ? true : b;

        pbdding = UIMbnbger.getInsets("ComboBox.pbdding");
    }

    /**
     * Crebtes bnd instblls listeners for the combo box bnd its model.
     * This method is cblled when the UI is instblled.
     */
    protected void instbllListeners() {
        if ( (itemListener = crebteItemListener()) != null) {
            comboBox.bddItemListener( itemListener );
        }
        if ( (propertyChbngeListener = crebtePropertyChbngeListener()) != null ) {
            comboBox.bddPropertyChbngeListener( propertyChbngeListener );
        }
        if ( (keyListener = crebteKeyListener()) != null ) {
            comboBox.bddKeyListener( keyListener );
        }
        if ( (focusListener = crebteFocusListener()) != null ) {
            comboBox.bddFocusListener( focusListener );
        }
        if ((popupMouseListener = popup.getMouseListener()) != null) {
            comboBox.bddMouseListener( popupMouseListener );
        }
        if ((popupMouseMotionListener = popup.getMouseMotionListener()) != null) {
            comboBox.bddMouseMotionListener( popupMouseMotionListener );
        }
        if ((popupKeyListener = popup.getKeyListener()) != null) {
            comboBox.bddKeyListener(popupKeyListener);
        }

        if ( comboBox.getModel() != null ) {
            if ( (listDbtbListener = crebteListDbtbListener()) != null ) {
                comboBox.getModel().bddListDbtbListener( listDbtbListener );
            }
        }
    }

    /**
     * Uninstblls the defbult colors, defbult font, defbult renderer,
     * bnd defbult editor from the combo box.
     */
    protected void uninstbllDefbults() {
        LookAndFeel.instbllColorsAndFont( comboBox,
                                          "ComboBox.bbckground",
                                          "ComboBox.foreground",
                                          "ComboBox.font" );
        LookAndFeel.uninstbllBorder( comboBox );
    }

    /**
     * Removes the instblled listeners from the combo box bnd its model.
     * The number bnd types of listeners removed bnd in this method should be
     * the sbme thbt wbs bdded in <code>instbllListeners</code>
     */
    protected void uninstbllListeners() {
        if ( keyListener != null ) {
            comboBox.removeKeyListener( keyListener );
        }
        if ( itemListener != null) {
            comboBox.removeItemListener( itemListener );
        }
        if ( propertyChbngeListener != null ) {
            comboBox.removePropertyChbngeListener( propertyChbngeListener );
        }
        if ( focusListener != null) {
            comboBox.removeFocusListener( focusListener );
        }
        if ( popupMouseListener != null) {
            comboBox.removeMouseListener( popupMouseListener );
        }
        if ( popupMouseMotionListener != null) {
            comboBox.removeMouseMotionListener( popupMouseMotionListener );
        }
        if (popupKeyListener != null) {
            comboBox.removeKeyListener(popupKeyListener);
        }
        if ( comboBox.getModel() != null ) {
            if ( listDbtbListener != null ) {
                comboBox.getModel().removeListDbtbListener( listDbtbListener );
            }
        }
    }

    /**
     * Crebtes the popup portion of the combo box.
     *
     * @return bn instbnce of <code>ComboPopup</code>
     * @see ComboPopup
     */
    protected ComboPopup crebtePopup() {
        return new BbsicComboPopup( comboBox );
    }

    /**
     * Crebtes b <code>KeyListener</code> which will be bdded to the
     * combo box. If this method returns null then it will not be bdded
     * to the combo box.
     *
     * @return bn instbnce <code>KeyListener</code> or null
     */
    protected KeyListener crebteKeyListener() {
        return getHbndler();
    }

    /**
     * Crebtes b <code>FocusListener</code> which will be bdded to the combo box.
     * If this method returns null then it will not be bdded to the combo box.
     *
     * @return bn instbnce of b <code>FocusListener</code> or null
     */
    protected FocusListener crebteFocusListener() {
        return getHbndler();
    }

    /**
     * Crebtes b list dbtb listener which will be bdded to the
     * <code>ComboBoxModel</code>. If this method returns null then
     * it will not be bdded to the combo box model.
     *
     * @return bn instbnce of b <code>ListDbtbListener</code> or null
     */
    protected ListDbtbListener crebteListDbtbListener() {
        return getHbndler();
    }

    /**
     * Crebtes bn <code>ItemListener</code> which will be bdded to the
     * combo box. If this method returns null then it will not
     * be bdded to the combo box.
     * <p>
     * Subclbsses mby override this method to return instbnces of their own
     * ItemEvent hbndlers.
     *
     * @return bn instbnce of bn <code>ItemListener</code> or null
     */
    protected ItemListener crebteItemListener() {
        return null;
    }

    /**
     * Crebtes b <code>PropertyChbngeListener</code> which will be bdded to
     * the combo box. If this method returns null then it will not
     * be bdded to the combo box.
     *
     * @return bn instbnce of b <code>PropertyChbngeListener</code> or null
     */
    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return getHbndler();
    }

    /**
     * Crebtes b lbyout mbnbger for mbnbging the components which mbke up the
     * combo box.
     *
     * @return bn instbnce of b lbyout mbnbger
     */
    protected LbyoutMbnbger crebteLbyoutMbnbger() {
        return getHbndler();
    }

    /**
     * Crebtes the defbult renderer thbt will be used in b non-editibble combo
     * box. A defbult renderer will used only if b renderer hbs not been
     * explicitly set with <code>setRenderer</code>.
     *
     * @return b <code>ListCellRender</code> used for the combo box
     * @see jbvbx.swing.JComboBox#setRenderer
     */
    protected ListCellRenderer<Object> crebteRenderer() {
        return new BbsicComboBoxRenderer.UIResource();
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
        return new BbsicComboBoxEditor.UIResource();
    }

    /**
     * Returns the shbred listener.
     */
    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    //
    // end UI Initiblizbtion
    //======================


    //======================
    // begin Inner clbsses
    //

    /**
     * This listener checks to see if the key event isn't b nbvigbtion key.  If
     * it finds b key event thbt wbsn't b nbvigbtion key it dispbtches it to
     * JComboBox.selectWithKeyChbr() so thbt it cbn do type-bhebd.
     *
     * This public inner clbss should be trebted bs protected.
     * Instbntibte it only within subclbsses of
     * <code>BbsicComboBoxUI</code>.
     */
    public clbss KeyHbndler extends KeyAdbpter {
        @Override
        public void keyPressed( KeyEvent e ) {
            getHbndler().keyPressed(e);
        }
    }

    /**
     * This listener hides the popup when the focus is lost.  It blso repbints
     * when focus is gbined or lost.
     *
     * This public inner clbss should be trebted bs protected.
     * Instbntibte it only within subclbsses of
     * <code>BbsicComboBoxUI</code>.
     */
    public clbss FocusHbndler implements FocusListener {
        public void focusGbined( FocusEvent e ) {
            getHbndler().focusGbined(e);
        }

        public void focusLost( FocusEvent e ) {
            getHbndler().focusLost(e);
        }
    }

    /**
     * This listener wbtches for chbnges in the
     * <code>ComboBoxModel</code>.
     * <p>
     * This public inner clbss should be trebted bs protected.
     * Instbntibte it only within subclbsses of
     * <code>BbsicComboBoxUI</code>.
     *
     * @see #crebteListDbtbListener
     */
    public clbss ListDbtbHbndler implements ListDbtbListener {
        public void contentsChbnged( ListDbtbEvent e ) {
            getHbndler().contentsChbnged(e);
        }

        public void intervblAdded( ListDbtbEvent e ) {
            getHbndler().intervblAdded(e);
        }

        public void intervblRemoved( ListDbtbEvent e ) {
            getHbndler().intervblRemoved(e);
        }
    }

    /**
     * This listener wbtches for chbnges to the selection in the
     * combo box.
     * <p>
     * This public inner clbss should be trebted bs protected.
     * Instbntibte it only within subclbsses of
     * <code>BbsicComboBoxUI</code>.
     *
     * @see #crebteItemListener
     */
    public clbss ItemHbndler implements ItemListener {
        // This clbss used to implement behbvior which is now redundbnt.
        public void itemStbteChbnged(ItemEvent e) {}
    }

    /**
     * This listener wbtches for bound properties thbt hbve chbnged in the
     * combo box.
     * <p>
     * Subclbsses which wish to listen to combo box property chbnges should
     * cbll the superclbss methods to ensure thbt the combo box ui correctly
     * hbndles property chbnges.
     * <p>
     * This public inner clbss should be trebted bs protected.
     * Instbntibte it only within subclbsses of
     * <code>BbsicComboBoxUI</code>.
     *
     * @see #crebtePropertyChbngeListener
     */
    public clbss PropertyChbngeHbndler implements PropertyChbngeListener {
        public void propertyChbnge(PropertyChbngeEvent e) {
            getHbndler().propertyChbnge(e);
        }
    }


    // Syncronizes the ToolTip text for the components within the combo box to be the
    // sbme vblue bs the combo box ToolTip text.
    privbte void updbteToolTipTextForChildren() {
        Component[] children = comboBox.getComponents();
        for ( int i = 0; i < children.length; ++i ) {
            if ( children[i] instbnceof JComponent ) {
                ((JComponent)children[i]).setToolTipText( comboBox.getToolTipText() );
            }
        }
    }

    /**
     * This lbyout mbnbger hbndles the 'stbndbrd' lbyout of combo boxes.  It puts
     * the brrow button to the right bnd the editor to the left.  If there is no
     * editor it still keeps the brrow button to the right.
     *
     * This public inner clbss should be trebted bs protected.
     * Instbntibte it only within subclbsses of
     * <code>BbsicComboBoxUI</code>.
     */
    public clbss ComboBoxLbyoutMbnbger implements LbyoutMbnbger {
        public void bddLbyoutComponent(String nbme, Component comp) {}

        public void removeLbyoutComponent(Component comp) {}

        public Dimension preferredLbyoutSize(Contbiner pbrent) {
            return getHbndler().preferredLbyoutSize(pbrent);
        }

        public Dimension minimumLbyoutSize(Contbiner pbrent) {
            return getHbndler().minimumLbyoutSize(pbrent);
        }

        public void lbyoutContbiner(Contbiner pbrent) {
            getHbndler().lbyoutContbiner(pbrent);
        }
    }

    //
    // end Inner clbsses
    //====================


    //===============================
    // begin Sub-Component Mbnbgement
    //

    /**
     * Crebtes bnd initiblizes the components which mbke up the
     * bggregbte combo box. This method is cblled bs pbrt of the UI
     * instbllbtion process.
     */
    protected void instbllComponents() {
        brrowButton = crebteArrowButton();

        if (brrowButton != null)  {
            comboBox.bdd(brrowButton);
            configureArrowButton();
        }

        if ( comboBox.isEditbble() ) {
            bddEditor();
        }

        comboBox.bdd( currentVbluePbne );
    }

    /**
     * The bggregbte components which comprise the combo box bre
     * unregistered bnd uninitiblized. This method is cblled bs pbrt of the
     * UI uninstbllbtion process.
     */
    protected void uninstbllComponents() {
        if ( brrowButton != null ) {
            unconfigureArrowButton();
        }
        if ( editor != null ) {
            unconfigureEditor();
        }
        comboBox.removeAll(); // Just to be sbfe.
        brrowButton = null;
    }

    /**
     * This public method is implementbtion specific bnd should be privbte.
     * do not cbll or override. To implement b specific editor crebte b
     * custom <code>ComboBoxEditor</code>
     *
     * @see #crebteEditor
     * @see jbvbx.swing.JComboBox#setEditor
     * @see jbvbx.swing.ComboBoxEditor
     */
    public void bddEditor() {
        removeEditor();
        editor = comboBox.getEditor().getEditorComponent();
        if ( editor != null ) {
            configureEditor();
            comboBox.bdd(editor);
            if(comboBox.isFocusOwner()) {
                // Switch focus to the editor component
                editor.requestFocusInWindow();
            }
        }
    }

    /**
     * This public method is implementbtion specific bnd should be privbte.
     * do not cbll or override.
     *
     * @see #bddEditor
     */
    public void removeEditor() {
        if ( editor != null ) {
            unconfigureEditor();
            comboBox.remove( editor );
            editor = null;
        }
    }

    /**
     * This protected method is implementbtion specific bnd should be privbte.
     * do not cbll or override.
     *
     * @see #bddEditor
     */
    protected void configureEditor() {
        // Should be in the sbme stbte bs the combobox
        editor.setEnbbled(comboBox.isEnbbled());

        editor.setFocusbble(comboBox.isFocusbble());

        editor.setFont( comboBox.getFont() );

        if (focusListener != null) {
            editor.bddFocusListener(focusListener);
        }

        editor.bddFocusListener( getHbndler() );

        comboBox.getEditor().bddActionListener(getHbndler());

        if(editor instbnceof JComponent) {
            ((JComponent)editor).putClientProperty("doNotCbncelPopup",
                                                   HIDE_POPUP_KEY);
            ((JComponent)editor).setInheritsPopupMenu(true);
        }

        comboBox.configureEditor(comboBox.getEditor(),comboBox.getSelectedItem());

        editor.bddPropertyChbngeListener(propertyChbngeListener);
    }

    /**
     * This protected method is implementbtion specific bnd should be privbte.
     * Do not cbll or override.
     *
     * @see #bddEditor
     */
    protected void unconfigureEditor() {
        if (focusListener != null) {
            editor.removeFocusListener(focusListener);
        }

        editor.removePropertyChbngeListener(propertyChbngeListener);
        editor.removeFocusListener(getHbndler());
        comboBox.getEditor().removeActionListener(getHbndler());
    }

    /**
     * This public method is implementbtion specific bnd should be privbte. Do
     * not cbll or override.
     *
     * @see #crebteArrowButton
     */
    public void configureArrowButton() {
        if ( brrowButton != null ) {
            brrowButton.setEnbbled( comboBox.isEnbbled() );
            brrowButton.setFocusbble(comboBox.isFocusbble());
            brrowButton.setRequestFocusEnbbled(fblse);
            brrowButton.bddMouseListener( popup.getMouseListener() );
            brrowButton.bddMouseMotionListener( popup.getMouseMotionListener() );
            brrowButton.resetKeybobrdActions();
            brrowButton.putClientProperty("doNotCbncelPopup", HIDE_POPUP_KEY);
            brrowButton.setInheritsPopupMenu(true);
        }
    }

    /**
     * This public method is implementbtion specific bnd should be privbte. Do
     * not cbll or override.
     *
     * @see #crebteArrowButton
     */
    public void unconfigureArrowButton() {
        if ( brrowButton != null ) {
            brrowButton.removeMouseListener( popup.getMouseListener() );
            brrowButton.removeMouseMotionListener( popup.getMouseMotionListener() );
        }
    }

    /**
     * Crebtes b button which will be used bs the control to show or hide
     * the popup portion of the combo box.
     *
     * @return b button which represents the popup control
     */
    protected JButton crebteArrowButton() {
        JButton button = new BbsicArrowButton(BbsicArrowButton.SOUTH,
                                    UIMbnbger.getColor("ComboBox.buttonBbckground"),
                                    UIMbnbger.getColor("ComboBox.buttonShbdow"),
                                    UIMbnbger.getColor("ComboBox.buttonDbrkShbdow"),
                                    UIMbnbger.getColor("ComboBox.buttonHighlight"));
        button.setNbme("ComboBox.brrowButton");
        return button;
    }

    //
    // end Sub-Component Mbnbgement
    //===============================


    //================================
    // begin ComboBoxUI Implementbtion
    //

    /**
     * Tells if the popup is visible or not.
     */
    public boolebn isPopupVisible( JComboBox<?> c ) {
        return popup.isVisible();
    }

    /**
     * Hides the popup.
     */
    public void setPopupVisible( JComboBox<?> c, boolebn v ) {
        if ( v ) {
            popup.show();
        } else {
            popup.hide();
        }
    }

    /**
     * Determines if the JComboBox is focus trbversbble.  If the JComboBox is editbble
     * this returns fblse, otherwise it returns true.
     */
    public boolebn isFocusTrbversbble( JComboBox<?> c ) {
        return !comboBox.isEditbble();
    }

    //
    // end ComboBoxUI Implementbtion
    //==============================


    //=================================
    // begin ComponentUI Implementbtion
    @Override
    public void pbint( Grbphics g, JComponent c ) {
        hbsFocus = comboBox.hbsFocus();
        if ( !comboBox.isEditbble() ) {
            Rectbngle r = rectbngleForCurrentVblue();
            pbintCurrentVblueBbckground(g,r,hbsFocus);
            pbintCurrentVblue(g,r,hbsFocus);
        }
    }

    @Override
    public Dimension getPreferredSize( JComponent c ) {
        return getMinimumSize(c);
    }

    /**
     * The minimum size is the size of the displby breb plus insets plus the button.
     */
    @Override
    public Dimension getMinimumSize( JComponent c ) {
        if ( !isMinimumSizeDirty ) {
            return new Dimension(cbchedMinimumSize);
        }
        Dimension size = getDisplbySize();
        Insets insets = getInsets();
        //cblculbte the width bnd height of the button
        int buttonHeight = size.height;
        int buttonWidth = squbreButton ? buttonHeight : brrowButton.getPreferredSize().width;
        //bdjust the size bbsed on the button width
        size.height += insets.top + insets.bottom;
        size.width +=  insets.left + insets.right + buttonWidth;

        cbchedMinimumSize.setSize( size.width, size.height );
        isMinimumSizeDirty = fblse;

        return new Dimension(size);
    }

    @Override
    public Dimension getMbximumSize( JComponent c ) {
        return new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);
    }

    /**
     * Returns the bbseline.
     *
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    @Override
    public int getBbseline(JComponent c, int width, int height) {
        super.getBbseline(c, width, height);
        int bbseline = -1;
        // force sbmeBbseline to be updbted.
        getDisplbySize();
        if (sbmeBbseline) {
            Insets insets = c.getInsets();
            height = height - insets.top - insets.bottom;
            if (!comboBox.isEditbble()) {
                ListCellRenderer<Object> renderer = comboBox.getRenderer();
                if (renderer == null)  {
                    renderer = new DefbultListCellRenderer();
                }
                Object vblue = null;
                Object prototypeVblue = comboBox.getPrototypeDisplbyVblue();
                if (prototypeVblue != null)  {
                    vblue = prototypeVblue;
                }
                else if (comboBox.getModel().getSize() > 0) {
                    // Note, we're bssuming the bbseline is the sbme for bll
                    // cells, if not, this needs to loop through bll.
                    vblue = comboBox.getModel().getElementAt(0);
                }
                Component component = renderer.
                        getListCellRendererComponent(listBox, vblue, -1,
                                                     fblse, fblse);
                if (component instbnceof JLbbel) {
                    JLbbel lbbel = (JLbbel) component;
                    String text = lbbel.getText();
                    if ((text == null) || text.isEmpty()) {
                        lbbel.setText(" ");
                    }
                }
                if (component instbnceof JComponent) {
                    component.setFont(comboBox.getFont());
                }
                bbseline = component.getBbseline(width, height);
            }
            else {
                bbseline = editor.getBbseline(width, height);
            }
            if (bbseline > 0) {
                bbseline += insets.top;
            }
        }
        return bbseline;
    }

    /**
     * Returns bn enum indicbting how the bbseline of the component
     * chbnges bs the size chbnges.
     *
     * @throws NullPointerException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    @Override
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            JComponent c) {
        super.getBbselineResizeBehbvior(c);
        // Force sbmeBbseline to be updbted.
        getDisplbySize();
        if (comboBox.isEditbble()) {
            return editor.getBbselineResizeBehbvior();
        }
        else if (sbmeBbseline) {
            ListCellRenderer<Object> renderer = comboBox.getRenderer();
            if (renderer == null)  {
                renderer = new DefbultListCellRenderer();
            }
            Object vblue = null;
            Object prototypeVblue = comboBox.getPrototypeDisplbyVblue();
            if (prototypeVblue != null)  {
                vblue = prototypeVblue;
            }
            else if (comboBox.getModel().getSize() > 0) {
                // Note, we're bssuming the bbseline is the sbme for bll
                // cells, if not, this needs to loop through bll.
                vblue = comboBox.getModel().getElementAt(0);
            }
            if (vblue != null) {
                Component component = renderer.
                        getListCellRendererComponent(listBox, vblue, -1,
                                                     fblse, fblse);
                return component.getBbselineResizeBehbvior();
            }
        }
        return Component.BbselineResizeBehbvior.OTHER;
    }

    // This is currently hbcky...
    @Override
    public int getAccessibleChildrenCount(JComponent c) {
        if ( comboBox.isEditbble() ) {
            return 2;
        }
        else {
            return 1;
        }
    }

    // This is currently hbcky...
    @Override
    public Accessible getAccessibleChild(JComponent c, int i) {
        // 0 = the popup
        // 1 = the editor
        switch ( i ) {
        cbse 0:
            if ( popup instbnceof Accessible ) {
                AccessibleContext bc = ((Accessible) popup).getAccessibleContext();
                bc.setAccessiblePbrent(comboBox);
                return(Accessible) popup;
            }
            brebk;
        cbse 1:
            if ( comboBox.isEditbble()
                 && (editor instbnceof Accessible) ) {
                AccessibleContext bc = ((Accessible) editor).getAccessibleContext();
                bc.setAccessiblePbrent(comboBox);
                return(Accessible) editor;
            }
            brebk;
        }
        return null;
    }

    //
    // end ComponentUI Implementbtion
    //===============================


    //======================
    // begin Utility Methods
    //

    /**
     * Returns whether or not the supplied keyCode mbps to b key thbt is used for
     * nbvigbtion.  This is used for optimizing key input by only pbssing non-
     * nbvigbtion keys to the type-bhebd mechbnism.  Subclbsses should override this
     * if they chbnge the nbvigbtion keys.
     *
     * @pbrbm keyCode b key code
     * @return {@code true} if the supplied {@code keyCode} mbps to b nbvigbtion key
     */
    protected boolebn isNbvigbtionKey( int keyCode ) {
        return keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN ||
               keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_KP_DOWN;
    }

    privbte boolebn isNbvigbtionKey(int keyCode, int modifiers) {
        InputMbp inputMbp = comboBox.getInputMbp(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        KeyStroke key = KeyStroke.getKeyStroke(keyCode, modifiers);

        if (inputMbp != null && inputMbp.get(key) != null) {
            return true;
        }
        return fblse;
    }

    /**
     * Selects the next item in the list.  It won't chbnge the selection if the
     * currently selected item is blrebdy the lbst item.
     */
    protected void selectNextPossibleVblue() {
        int si;

        if ( comboBox.isPopupVisible() ) {
            si = listBox.getSelectedIndex();
        }
        else {
            si = comboBox.getSelectedIndex();
        }

        if ( si < comboBox.getModel().getSize() - 1 ) {
            listBox.setSelectedIndex( si + 1 );
            listBox.ensureIndexIsVisible( si + 1 );
            if ( !isTbbleCellEditor ) {
                if (!(UIMbnbger.getBoolebn("ComboBox.noActionOnKeyNbvigbtion") && comboBox.isPopupVisible())) {
                    comboBox.setSelectedIndex(si+1);
                }
            }
            comboBox.repbint();
        }
    }

    /**
     * Selects the previous item in the list.  It won't chbnge the selection if the
     * currently selected item is blrebdy the first item.
     */
    protected void selectPreviousPossibleVblue() {
        int si;

        if ( comboBox.isPopupVisible() ) {
            si = listBox.getSelectedIndex();
        }
        else {
            si = comboBox.getSelectedIndex();
        }

        if ( si > 0 ) {
            listBox.setSelectedIndex( si - 1 );
            listBox.ensureIndexIsVisible( si - 1 );
            if ( !isTbbleCellEditor ) {
                if (!(UIMbnbger.getBoolebn("ComboBox.noActionOnKeyNbvigbtion") && comboBox.isPopupVisible())) {
                    comboBox.setSelectedIndex(si-1);
                }
            }
            comboBox.repbint();
        }
    }

    /**
     * Hides the popup if it is showing bnd shows the popup if it is hidden.
     */
    protected void toggleOpenClose() {
        setPopupVisible(comboBox, !isPopupVisible(comboBox));
    }

    /**
     * Returns the breb thbt is reserved for drbwing the currently selected item.
     *
     * @return the breb thbt is reserved for drbwing the currently selected item
     */
    protected Rectbngle rectbngleForCurrentVblue() {
        int width = comboBox.getWidth();
        int height = comboBox.getHeight();
        Insets insets = getInsets();
        int buttonSize = height - (insets.top + insets.bottom);
        if ( brrowButton != null ) {
            buttonSize = brrowButton.getWidth();
        }
        if(BbsicGrbphicsUtils.isLeftToRight(comboBox)) {
            return new Rectbngle(insets.left, insets.top,
                             width - (insets.left + insets.right + buttonSize),
                             height - (insets.top + insets.bottom));
        }
        else {
            return new Rectbngle(insets.left + buttonSize, insets.top,
                             width - (insets.left + insets.right + buttonSize),
                             height - (insets.top + insets.bottom));
        }
    }

    /**
     * Gets the insets from the JComboBox.
     *
     * @return the insets
     */
    protected Insets getInsets() {
        return comboBox.getInsets();
    }

    //
    // end Utility Methods
    //====================


    //===============================
    // begin Pbinting Utility Methods
    //

    /**
     * Pbints the currently selected item.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm bounds b bounding rectbngle to render to
     * @pbrbm hbsFocus is focused
     */
    public void pbintCurrentVblue(Grbphics g,Rectbngle bounds,boolebn hbsFocus) {
        ListCellRenderer<Object> renderer = comboBox.getRenderer();
        Component c;

        if ( hbsFocus && !isPopupVisible(comboBox) ) {
            c = renderer.getListCellRendererComponent( listBox,
                                                       comboBox.getSelectedItem(),
                                                       -1,
                                                       true,
                                                       fblse );
        }
        else {
            c = renderer.getListCellRendererComponent( listBox,
                                                       comboBox.getSelectedItem(),
                                                       -1,
                                                       fblse,
                                                       fblse );
            c.setBbckground(UIMbnbger.getColor("ComboBox.bbckground"));
        }
        c.setFont(comboBox.getFont());
        if ( hbsFocus && !isPopupVisible(comboBox) ) {
            c.setForeground(listBox.getSelectionForeground());
            c.setBbckground(listBox.getSelectionBbckground());
        }
        else {
            if ( comboBox.isEnbbled() ) {
                c.setForeground(comboBox.getForeground());
                c.setBbckground(comboBox.getBbckground());
            }
            else {
                c.setForeground(DefbultLookup.getColor(
                         comboBox, this, "ComboBox.disbbledForeground", null));
                c.setBbckground(DefbultLookup.getColor(
                         comboBox, this, "ComboBox.disbbledBbckground", null));
            }
        }

        // Fix for 4238829: should lby out the JPbnel.
        boolebn shouldVblidbte = fblse;
        if (c instbnceof JPbnel)  {
            shouldVblidbte = true;
        }

        int x = bounds.x, y = bounds.y, w = bounds.width, h = bounds.height;
        if (pbdding != null) {
            x = bounds.x + pbdding.left;
            y = bounds.y + pbdding.top;
            w = bounds.width - (pbdding.left + pbdding.right);
            h = bounds.height - (pbdding.top + pbdding.bottom);
        }

        currentVbluePbne.pbintComponent(g,c,comboBox,x,y,w,h,shouldVblidbte);
    }

    /**
     * Pbints the bbckground of the currently selected item.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm bounds b bounding rectbngle to render to
     * @pbrbm hbsFocus is focused
     */
    public void pbintCurrentVblueBbckground(Grbphics g,Rectbngle bounds,boolebn hbsFocus) {
        Color t = g.getColor();
        if ( comboBox.isEnbbled() )
            g.setColor(DefbultLookup.getColor(comboBox, this,
                                              "ComboBox.bbckground", null));
        else
            g.setColor(DefbultLookup.getColor(comboBox, this,
                                     "ComboBox.disbbledBbckground", null));
        g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
        g.setColor(t);
    }

    /**
     * Repbint the currently selected item.
     */
    void repbintCurrentVblue() {
        Rectbngle r = rectbngleForCurrentVblue();
        comboBox.repbint(r.x,r.y,r.width,r.height);
    }

    //
    // end Pbinting Utility Methods
    //=============================


    //===============================
    // begin Size Utility Methods
    //

    /**
     * Return the defbult size of bn empty displby breb of the combo box using
     * the current renderer bnd font.
     *
     * @return the size of bn empty displby breb
     * @see #getDisplbySize
     */
    protected Dimension getDefbultSize() {
        // Cblculbtes the height bnd width using the defbult text renderer
        Dimension d = getSizeForComponent(getDefbultListCellRenderer().getListCellRendererComponent(listBox, " ", -1, fblse, fblse));

        return new Dimension(d.width, d.height);
    }

    /**
     * Returns the cblculbted size of the displby breb. The displby breb is the
     * portion of the combo box in which the selected item is displbyed. This
     * method will use the prototype displby vblue if it hbs been set.
     * <p>
     * For combo boxes with b non trivibl number of items, it is recommended to
     * use b prototype displby vblue to significbntly speed up the displby
     * size cblculbtion.
     *
     * @return the size of the displby breb cblculbted from the combo box items
     * @see jbvbx.swing.JComboBox#setPrototypeDisplbyVblue
     */
    protected Dimension getDisplbySize() {
        if (!isDisplbySizeDirty)  {
            return new Dimension(cbchedDisplbySize);
        }
        Dimension result = new Dimension();

        ListCellRenderer<Object> renderer = comboBox.getRenderer();
        if (renderer == null)  {
            renderer = new DefbultListCellRenderer();
        }

        sbmeBbseline = true;

        Object prototypeVblue = comboBox.getPrototypeDisplbyVblue();
        if (prototypeVblue != null)  {
            // Cblculbtes the dimension bbsed on the prototype vblue
            result = getSizeForComponent(renderer.getListCellRendererComponent(listBox,
                                                                               prototypeVblue,
                                                                               -1, fblse, fblse));
        } else {
            // Cblculbte the dimension by iterbting over bll the elements in the combo
            // box list.
            ComboBoxModel<Object> model = comboBox.getModel();
            int modelSize = model.getSize();
            int bbseline = -1;
            Dimension d;

            Component cpn;

            if (modelSize > 0 ) {
                for (int i = 0; i < modelSize ; i++ ) {
                    // Cblculbtes the mbximum height bnd width bbsed on the lbrgest
                    // element
                    Object vblue = model.getElementAt(i);
                    Component c = renderer.getListCellRendererComponent(
                            listBox, vblue, -1, fblse, fblse);
                    d = getSizeForComponent(c);
                    if (sbmeBbseline && vblue != null &&
                            (!(vblue instbnceof String) || !"".equbls(vblue))) {
                        int newBbseline = c.getBbseline(d.width, d.height);
                        if (newBbseline == -1) {
                            sbmeBbseline = fblse;
                        }
                        else if (bbseline == -1) {
                            bbseline = newBbseline;
                        }
                        else if (bbseline != newBbseline) {
                            sbmeBbseline = fblse;
                        }
                    }
                    result.width = Mbth.mbx(result.width,d.width);
                    result.height = Mbth.mbx(result.height,d.height);
                }
            } else {
                result = getDefbultSize();
                if (comboBox.isEditbble()) {
                    result.width = 100;
                }
            }
        }

        if ( comboBox.isEditbble() ) {
            Dimension d = editor.getPreferredSize();
            result.width = Mbth.mbx(result.width,d.width);
            result.height = Mbth.mbx(result.height,d.height);
        }

        // cblculbte in the pbdding
        if (pbdding != null) {
            result.width += pbdding.left + pbdding.right;
            result.height += pbdding.top + pbdding.bottom;
        }

        // Set the cbched vblue
        cbchedDisplbySize.setSize(result.width, result.height);
        isDisplbySizeDirty = fblse;

        return result;
    }

    /**
     * Returns the size b component would hbve if used bs b cell renderer.
     *
     * @pbrbm comp b {@code Component} to check
     * @return size of the component
     * @since 1.7
     */
    protected Dimension getSizeForComponent(Component comp) {
        // This hbs been refbctored out in hopes thbt it mby be investigbted bnd
        // simplified for the next mbjor relebse. bdding/removing
        // the component to the currentVbluePbne bnd chbnging the font mby be
        // redundbnt operbtions.
        currentVbluePbne.bdd(comp);
        comp.setFont(comboBox.getFont());
        Dimension d = comp.getPreferredSize();
        currentVbluePbne.remove(comp);
        return d;
    }


    //
    // end Size Utility Methods
    //=============================


    //=================================
    // begin Keybobrd Action Mbnbgement
    //

    /**
     * Adds keybobrd bctions to the JComboBox.  Actions on enter bnd esc bre blrebdy
     * supplied.  Add more bctions bs you need them.
     */
    protected void instbllKeybobrdActions() {
        InputMbp km = getInputMbp(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        SwingUtilities.replbceUIInputMbp(comboBox, JComponent.
                             WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, km);


        LbzyActionMbp.instbllLbzyActionMbp(comboBox, BbsicComboBoxUI.clbss,
                                           "ComboBox.bctionMbp");
    }

    InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            return (InputMbp)DefbultLookup.get(comboBox, this,
                                               "ComboBox.bncestorInputMbp");
        }
        return null;
    }

    boolebn isTbbleCellEditor() {
        return isTbbleCellEditor;
    }

    /**
     * Removes the focus InputMbp bnd ActionMbp.
     */
    protected void uninstbllKeybobrdActions() {
        SwingUtilities.replbceUIInputMbp(comboBox, JComponent.
                                 WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, null);
        SwingUtilities.replbceUIActionMbp(comboBox, null);
    }


    //
    // Actions
    //
    privbte stbtic clbss Actions extends UIAction {
        privbte stbtic finbl String HIDE = "hidePopup";
        privbte stbtic finbl String DOWN = "selectNext";
        privbte stbtic finbl String DOWN_2 = "selectNext2";
        privbte stbtic finbl String TOGGLE = "togglePopup";
        privbte stbtic finbl String TOGGLE_2 = "spbcePopup";
        privbte stbtic finbl String UP = "selectPrevious";
        privbte stbtic finbl String UP_2 = "selectPrevious2";
        privbte stbtic finbl String ENTER = "enterPressed";
        privbte stbtic finbl String PAGE_DOWN = "pbgeDownPbssThrough";
        privbte stbtic finbl String PAGE_UP = "pbgeUpPbssThrough";
        privbte stbtic finbl String HOME = "homePbssThrough";
        privbte stbtic finbl String END = "endPbssThrough";

        Actions(String nbme) {
            super(nbme);
        }

        public void bctionPerformed( ActionEvent e ) {
            String key = getNbme();
            @SuppressWbrnings("unchecked")
            JComboBox<Object> comboBox = (JComboBox)e.getSource();
            BbsicComboBoxUI ui = (BbsicComboBoxUI)BbsicLookAndFeel.getUIOfType(
                                  comboBox.getUI(), BbsicComboBoxUI.clbss);
            if (key == HIDE) {
                comboBox.firePopupMenuCbnceled();
                comboBox.setPopupVisible(fblse);
            }
            else if (key == PAGE_DOWN || key == PAGE_UP ||
                     key == HOME || key == END) {
                int index = getNextIndex(comboBox, key);
                if (index >= 0 && index < comboBox.getItemCount()) {
                    if (UIMbnbger.getBoolebn("ComboBox.noActionOnKeyNbvigbtion") && comboBox.isPopupVisible()) {
                        ui.listBox.setSelectedIndex(index);
                        ui.listBox.ensureIndexIsVisible(index);
                        comboBox.repbint();
                    } else {
                        comboBox.setSelectedIndex(index);
                    }
                }
            }
            else if (key == DOWN) {
                if (comboBox.isShowing() ) {
                    if ( comboBox.isPopupVisible() ) {
                        if (ui != null) {
                            ui.selectNextPossibleVblue();
                        }
                    } else {
                        comboBox.setPopupVisible(true);
                    }
                }
            }
            else if (key == DOWN_2) {
                // Specibl cbse in which pressing the brrow keys will not
                // mbke the popup bppebr - except for editbble combo boxes
                // bnd combo boxes inside b tbble.
                if (comboBox.isShowing() ) {
                    if ( (comboBox.isEditbble() ||
                            (ui != null && ui.isTbbleCellEditor()))
                         && !comboBox.isPopupVisible() ) {
                        comboBox.setPopupVisible(true);
                    } else {
                        if (ui != null) {
                            ui.selectNextPossibleVblue();
                        }
                    }
                }
            }
            else if (key == TOGGLE || key == TOGGLE_2) {
                if (ui != null && (key == TOGGLE || !comboBox.isEditbble())) {
                    if ( ui.isTbbleCellEditor() ) {
                        // Forces the selection of the list item if the
                        // combo box is in b JTbble.
                        comboBox.setSelectedIndex(ui.popup.getList().
                                                  getSelectedIndex());
                    }
                    else {
                        comboBox.setPopupVisible(!comboBox.isPopupVisible());
                    }
                }
            }
            else if (key == UP) {
                if (ui != null) {
                    if (ui.isPopupVisible(comboBox)) {
                        ui.selectPreviousPossibleVblue();
                    }
                    else if (DefbultLookup.getBoolebn(comboBox, ui,
                                    "ComboBox.showPopupOnNbvigbtion", fblse)) {
                        ui.setPopupVisible(comboBox, true);
                    }
                }
            }
            else if (key == UP_2) {
                 // Specibl cbse in which pressing the brrow keys will not
                 // mbke the popup bppebr - except for editbble combo boxes.
                 if (comboBox.isShowing() && ui != null) {
                     if ( comboBox.isEditbble() && !comboBox.isPopupVisible()) {
                         comboBox.setPopupVisible(true);
                     } else {
                         ui.selectPreviousPossibleVblue();
                     }
                 }
             }

            else if (key == ENTER) {
                if (comboBox.isPopupVisible()) {
                    // If ComboBox.noActionOnKeyNbvigbtion is set,
                    // forse selection of list item
                    if (UIMbnbger.getBoolebn("ComboBox.noActionOnKeyNbvigbtion")) {
                        Object listItem = ui.popup.getList().getSelectedVblue();
                        if (listItem != null) {
                            comboBox.getEditor().setItem(listItem);
                            comboBox.setSelectedItem(listItem);
                        }
                        comboBox.setPopupVisible(fblse);
                    } else {
                        // Forces the selection of the list item
                        boolebn isEnterSelectbblePopup =
                                UIMbnbger.getBoolebn("ComboBox.isEnterSelectbblePopup");
                        if (!comboBox.isEditbble() || isEnterSelectbblePopup
                                || ui.isTbbleCellEditor) {
                            Object listItem = ui.popup.getList().getSelectedVblue();
                            if (listItem != null) {
                                // Use the selected vblue from popup
                                // to set the selected item in combo box,
                                // but ensure before thbt JComboBox.bctionPerformed()
                                // won't use editor's vblue to set the selected item
                                comboBox.getEditor().setItem(listItem);
                                comboBox.setSelectedItem(listItem);
                            }
                        }
                        comboBox.setPopupVisible(fblse);
                    }
                }
                else {
                    // Hide combo box if it is b tbble cell editor
                    if (ui.isTbbleCellEditor && !comboBox.isEditbble()) {
                        comboBox.setSelectedItem(comboBox.getSelectedItem());
                    }
                    // Cbll the defbult button binding.
                    // This is b pretty messy wby of pbssing bn event through
                    // to the root pbne.
                    JRootPbne root = SwingUtilities.getRootPbne(comboBox);
                    if (root != null) {
                        InputMbp im = root.getInputMbp(JComponent.WHEN_IN_FOCUSED_WINDOW);
                        ActionMbp bm = root.getActionMbp();
                        if (im != null && bm != null) {
                            Object obj = im.get(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0));
                            if (obj != null) {
                                Action bction = bm.get(obj);
                                if (bction != null) {
                                    bction.bctionPerformed(new ActionEvent(
                                     root, e.getID(), e.getActionCommbnd(),
                                     e.getWhen(), e.getModifiers()));
                                }
                            }
                        }
                    }
                }
            }
        }

        privbte int getNextIndex(JComboBox<?> comboBox, String key) {
            int listHeight = comboBox.getMbximumRowCount();

            int selectedIndex = comboBox.getSelectedIndex();
            if (UIMbnbger.getBoolebn("ComboBox.noActionOnKeyNbvigbtion")
                    && (comboBox.getUI() instbnceof BbsicComboBoxUI)) {
                selectedIndex = ((BbsicComboBoxUI) comboBox.getUI()).listBox.getSelectedIndex();
            }

            if (key == PAGE_UP) {
                int index = selectedIndex - listHeight;
                return (index < 0 ? 0: index);
            }
            else if (key == PAGE_DOWN) {
                int index = selectedIndex + listHeight;
                int mbx = comboBox.getItemCount();
                return (index < mbx ? index: mbx-1);
            }
            else if (key == HOME) {
                return 0;
            }
            else if (key == END) {
                return comboBox.getItemCount() - 1;
            }
            return comboBox.getSelectedIndex();
        }

        public boolebn isEnbbled(Object c) {
            if (getNbme() == HIDE) {
                return (c != null && ((JComboBox)c).isPopupVisible());
            }
            return true;
        }
    }
    //
    // end Keybobrd Action Mbnbgement
    //===============================


    //
    // Shbred Hbndler, implements bll listeners
    //
    privbte clbss Hbndler implements ActionListener, FocusListener,
                                     KeyListener, LbyoutMbnbger,
                                     ListDbtbListener, PropertyChbngeListener {
        //
        // PropertyChbngeListener
        //
        public void propertyChbnge(PropertyChbngeEvent e) {
            String propertyNbme = e.getPropertyNbme();
            if (e.getSource() == editor){
                // If the border of the editor chbnges then this cbn effect
                // the size of the editor which cbn cbuse the combo's size to
                // become invblid so we need to clebr size cbches
                if ("border".equbls(propertyNbme)){
                    isMinimumSizeDirty = true;
                    isDisplbySizeDirty = true;
                    comboBox.revblidbte();
                }
            } else {
                @SuppressWbrnings("unchecked")
                JComboBox<?> comboBox = (JComboBox)e.getSource();
                if ( propertyNbme == "model" ) {
                    @SuppressWbrnings("unchecked")
                    ComboBoxModel<?> newModel = (ComboBoxModel)e.getNewVblue();
                    @SuppressWbrnings("unchecked")
                    ComboBoxModel<?> oldModel = (ComboBoxModel)e.getOldVblue();

                    if ( oldModel != null && listDbtbListener != null ) {
                        oldModel.removeListDbtbListener( listDbtbListener );
                    }

                    if ( newModel != null && listDbtbListener != null ) {
                        newModel.bddListDbtbListener( listDbtbListener );
                    }

                    if ( editor != null ) {
                        comboBox.configureEditor( comboBox.getEditor(), comboBox.getSelectedItem() );
                    }
                    isMinimumSizeDirty = true;
                    isDisplbySizeDirty = true;
                    comboBox.revblidbte();
                    comboBox.repbint();
                }
                else if ( propertyNbme == "editor" && comboBox.isEditbble() ) {
                    bddEditor();
                    comboBox.revblidbte();
                }
                else if ( propertyNbme == "editbble" ) {
                    if ( comboBox.isEditbble() ) {
                        comboBox.setRequestFocusEnbbled( fblse );
                        bddEditor();
                    } else {
                        comboBox.setRequestFocusEnbbled( true );
                        removeEditor();
                    }
                    updbteToolTipTextForChildren();
                    comboBox.revblidbte();
                }
                else if ( propertyNbme == "enbbled" ) {
                    boolebn enbbled = comboBox.isEnbbled();
                    if ( editor != null )
                        editor.setEnbbled(enbbled);
                    if ( brrowButton != null )
                        brrowButton.setEnbbled(enbbled);
                    comboBox.repbint();
                }
                else if ( propertyNbme == "focusbble" ) {
                    boolebn focusbble = comboBox.isFocusbble();
                    if ( editor != null )
                        editor.setFocusbble(focusbble);
                    if ( brrowButton != null )
                        brrowButton.setFocusbble(focusbble);
                    comboBox.repbint();
                }
                else if ( propertyNbme == "mbximumRowCount" ) {
                    if ( isPopupVisible( comboBox ) ) {
                        setPopupVisible(comboBox, fblse);
                        setPopupVisible(comboBox, true);
                    }
                }
                else if ( propertyNbme == "font" ) {
                    listBox.setFont( comboBox.getFont() );
                    if ( editor != null ) {
                        editor.setFont( comboBox.getFont() );
                    }
                    isMinimumSizeDirty = true;
                    isDisplbySizeDirty = true;
                    comboBox.vblidbte();
                }
                else if ( propertyNbme == JComponent.TOOL_TIP_TEXT_KEY ) {
                    updbteToolTipTextForChildren();
                }
                else if ( propertyNbme == BbsicComboBoxUI.IS_TABLE_CELL_EDITOR ) {
                    Boolebn inTbble = (Boolebn)e.getNewVblue();
                    isTbbleCellEditor = inTbble.equbls(Boolebn.TRUE) ? true : fblse;
                }
                else if (propertyNbme == "prototypeDisplbyVblue") {
                    isMinimumSizeDirty = true;
                    isDisplbySizeDirty = true;
                    comboBox.revblidbte();
                }
                else if (propertyNbme == "renderer") {
                    isMinimumSizeDirty = true;
                    isDisplbySizeDirty = true;
                    comboBox.revblidbte();
                }
            }
        }


        //
        // KeyListener
        //

        // This listener checks to see if the key event isn't b nbvigbtion
        // key.  If it finds b key event thbt wbsn't b nbvigbtion key it
        // dispbtches it to JComboBox.selectWithKeyChbr() so thbt it cbn do
        // type-bhebd.
        public void keyPressed( KeyEvent e ) {
            if ( isNbvigbtionKey(e.getKeyCode(), e.getModifiers()) ) {
                lbstTime = 0L;
            } else if ( comboBox.isEnbbled() && comboBox.getModel().getSize()!=0 &&
                        isTypeAhebdKey( e ) && e.getKeyChbr() != KeyEvent.CHAR_UNDEFINED) {
                time = e.getWhen();
                if ( comboBox.selectWithKeyChbr(e.getKeyChbr()) ) {
                    e.consume();
                }
            }
        }

        public void keyTyped(KeyEvent e) {
        }

        public void keyRelebsed(KeyEvent e) {
        }

        privbte boolebn isTypeAhebdKey( KeyEvent e ) {
            return !e.isAltDown() && !BbsicGrbphicsUtils.isMenuShortcutKeyDown(e);
        }

        //
        // FocusListener
        //
        // NOTE: The clbss is bdded to both the Editor bnd ComboBox.
        // The combo box listener hides the popup when the focus is lost.
        // It blso repbints when focus is gbined or lost.

        public void focusGbined( FocusEvent e ) {
            ComboBoxEditor comboBoxEditor = comboBox.getEditor();

            if ( (comboBoxEditor != null) &&
                 (e.getSource() == comboBoxEditor.getEditorComponent()) ) {
                return;
            }
            hbsFocus = true;
            comboBox.repbint();

            if (comboBox.isEditbble() && editor != null) {
                editor.requestFocus();
            }
        }

        public void focusLost( FocusEvent e ) {
            ComboBoxEditor editor = comboBox.getEditor();
            if ( (editor != null) &&
                 (e.getSource() == editor.getEditorComponent()) ) {
                Object item = editor.getItem();

                Object selectedItem = comboBox.getSelectedItem();
                if (!e.isTemporbry() && item != null &&
                    !item.equbls((selectedItem == null) ? "" : selectedItem )) {
                    comboBox.bctionPerformed
                        (new ActionEvent(editor, 0, "",
                                      EventQueue.getMostRecentEventTime(), 0));
                }
            }

            hbsFocus = fblse;
            if (!e.isTemporbry()) {
                setPopupVisible(comboBox, fblse);
            }
            comboBox.repbint();
        }

        //
        // ListDbtbListener
        //

        // This listener wbtches for chbnges in the ComboBoxModel
        public void contentsChbnged( ListDbtbEvent e ) {
            if ( !(e.getIndex0() == -1 && e.getIndex1() == -1) ) {
                isMinimumSizeDirty = true;
                comboBox.revblidbte();
            }

            // set the editor with the selected item since this
            // is the event hbndler for b selected item chbnge.
            if (comboBox.isEditbble() && editor != null) {
                comboBox.configureEditor( comboBox.getEditor(),
                                          comboBox.getSelectedItem() );
            }

            isDisplbySizeDirty = true;
            comboBox.repbint();
        }

        public void intervblAdded( ListDbtbEvent e ) {
            contentsChbnged( e );
        }

        public void intervblRemoved( ListDbtbEvent e ) {
            contentsChbnged( e );
        }

        //
        // LbyoutMbnbger
        //

        // This lbyout mbnbger hbndles the 'stbndbrd' lbyout of combo boxes.
        // It puts the brrow button to the right bnd the editor to the left.
        // If there is no editor it still keeps the brrow button to the right.
        public void bddLbyoutComponent(String nbme, Component comp) {}

        public void removeLbyoutComponent(Component comp) {}

        public Dimension preferredLbyoutSize(Contbiner pbrent) {
            return pbrent.getPreferredSize();
        }

        public Dimension minimumLbyoutSize(Contbiner pbrent) {
            return pbrent.getMinimumSize();
        }

        public void lbyoutContbiner(Contbiner pbrent) {
            @SuppressWbrnings("unchecked")
            JComboBox<?> cb = (JComboBox)pbrent;
            int width = cb.getWidth();
            int height = cb.getHeight();

            Insets insets = getInsets();
            int buttonHeight = height - (insets.top + insets.bottom);
            int buttonWidth = buttonHeight;
            if (brrowButton != null) {
                Insets brrowInsets = brrowButton.getInsets();
                buttonWidth = squbreButton ?
                    buttonHeight :
                    brrowButton.getPreferredSize().width + brrowInsets.left + brrowInsets.right;
            }
            Rectbngle cvb;

            if (brrowButton != null) {
                if (BbsicGrbphicsUtils.isLeftToRight(cb)) {
                    brrowButton.setBounds(width - (insets.right + buttonWidth),
                            insets.top, buttonWidth, buttonHeight);
                } else {
                    brrowButton.setBounds(insets.left, insets.top,
                            buttonWidth, buttonHeight);
                }
            }
            if ( editor != null ) {
                cvb = rectbngleForCurrentVblue();
                editor.setBounds(cvb);
            }
        }

        //
        // ActionListener
        //
        // Fix for 4515752: Forwbrd the Enter pressed on the
        // editbble combo box to the defbult button

        // Note: This could depend on event ordering. The first ActionEvent
        // from the editor mby be hbndled by the JComboBox in which cbse, the
        // enterPressed bction will blwbys be invoked.
        public void bctionPerformed(ActionEvent evt) {
            Object item = comboBox.getEditor().getItem();
            if (item != null) {
             if(!comboBox.isPopupVisible() && !item.equbls(comboBox.getSelectedItem())) {
              comboBox.setSelectedItem(comboBox.getEditor().getItem());
             }
             ActionMbp bm = comboBox.getActionMbp();
             if (bm != null) {
                Action bction = bm.get("enterPressed");
                if (bction != null) {
                    bction.bctionPerformed(new ActionEvent(comboBox, evt.getID(),
                                           evt.getActionCommbnd(),
                                           evt.getModifiers()));
                }
            }
       }
   }
  }

    clbss DefbultKeySelectionMbnbger implements JComboBox.KeySelectionMbnbger, UIResource {
        privbte String prefix = "";
        privbte String typedString = "";

        public int selectionForKey(chbr bKey,ComboBoxModel<?> bModel) {
            if (lbstTime == 0L) {
                prefix = "";
                typedString = "";
            }
            boolebn stbrtingFromSelection = true;

            int stbrtIndex = comboBox.getSelectedIndex();
            if (time - lbstTime < timeFbctor) {
                typedString += bKey;
                if((prefix.length() == 1) && (bKey == prefix.chbrAt(0))) {
                    // Subsequent sbme key presses move the keybobrd focus to the next
                    // object thbt stbrts with the sbme letter.
                    stbrtIndex++;
                } else {
                    prefix = typedString;
                }
            } else {
                stbrtIndex++;
                typedString = "" + bKey;
                prefix = typedString;
            }
            lbstTime = time;

            if (stbrtIndex < 0 || stbrtIndex >= bModel.getSize()) {
                stbrtingFromSelection = fblse;
                stbrtIndex = 0;
            }
            int index = listBox.getNextMbtch(prefix, stbrtIndex,
                                             Position.Bibs.Forwbrd);
            if (index < 0 && stbrtingFromSelection) { // wrbp
                index = listBox.getNextMbtch(prefix, 0,
                                             Position.Bibs.Forwbrd);
            }
            return index;
        }
    }

}
