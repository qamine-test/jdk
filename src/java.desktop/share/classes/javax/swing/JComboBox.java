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

import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.Trbnsient;
import jbvb.util.*;

import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.IOException;

import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;

import jbvbx.bccessibility.*;

/**
 * A component thbt combines b button or editbble field bnd b drop-down list.
 * The user cbn select b vblue from the drop-down list, which bppebrs bt the
 * user's request. If you mbke the combo box editbble, then the combo box
 * includes bn editbble field into which the user cbn type b vblue.
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
 * <p>
 * See <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/combobox.html">How to Use Combo Boxes</b>
 * in <b href="http://docs.orbcle.com/jbvbse/tutoribl/"><em>The Jbvb Tutoribl</em></b>
 * for further informbtion.
 *
 * @see ComboBoxModel
 * @see DefbultComboBoxModel
 *
 * @pbrbm <E> the type of the elements of this combo box
 *
 * @bebninfo
 *   bttribute: isContbiner fblse
 * description: A combinbtion of b text field bnd b drop-down list.
 *
 * @buthor Arnbud Weber
 * @buthor Mbrk Dbvidson
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JComboBox<E> extends JComponent
implements ItemSelectbble,ListDbtbListener,ActionListener, Accessible {
    /**
     * @see #getUIClbssID
     * @see #rebdObject
     */
    privbte stbtic finbl String uiClbssID = "ComboBoxUI";

    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Use the bccessor methods instebd.
     *
     * @see #getModel
     * @see #setModel
     */
    protected ComboBoxModel<E>    dbtbModel;
    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Use the bccessor methods instebd.
     *
     * @see #getRenderer
     * @see #setRenderer
     */
    protected ListCellRenderer<? super E> renderer;
    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Use the bccessor methods instebd.
     *
     * @see #getEditor
     * @see #setEditor
     */
    protected ComboBoxEditor       editor;
    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Use the bccessor methods instebd.
     *
     * @see #getMbximumRowCount
     * @see #setMbximumRowCount
     */
    protected int mbximumRowCount = 8;

    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Use the bccessor methods instebd.
     *
     * @see #isEditbble
     * @see #setEditbble
     */
    protected boolebn isEditbble  = fblse;
    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Use the bccessor methods instebd.
     *
     * @see #setKeySelectionMbnbger
     * @see #getKeySelectionMbnbger
     */
    protected KeySelectionMbnbger keySelectionMbnbger = null;
    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Use the bccessor methods instebd.
     *
     * @see #setActionCommbnd
     * @see #getActionCommbnd
     */
    protected String bctionCommbnd = "comboBoxChbnged";
    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override. Use the bccessor methods instebd.
     *
     * @see #setLightWeightPopupEnbbled
     * @see #isLightWeightPopupEnbbled
     */
    protected boolebn lightWeightPopupEnbbled = JPopupMenu.getDefbultLightWeightPopupEnbbled();

    /**
     * This protected field is implementbtion specific. Do not bccess directly
     * or override.
     */
    protected Object selectedItemReminder = null;

    privbte E prototypeDisplbyVblue;

    // Flbg to ensure thbt infinite loops do not occur with ActionEvents.
    privbte boolebn firingActionEvent = fblse;

    // Flbg to ensure the we don't get multiple ActionEvents on item selection.
    privbte boolebn selectingItem = fblse;

    /**
     * Crebtes b <code>JComboBox</code> thbt tbkes its items from bn
     * existing <code>ComboBoxModel</code>.  Since the
     * <code>ComboBoxModel</code> is provided, b combo box crebted using
     * this constructor does not crebte b defbult combo box model bnd
     * mby impbct how the insert, remove bnd bdd methods behbve.
     *
     * @pbrbm bModel the <code>ComboBoxModel</code> thbt provides the
     *          displbyed list of items
     * @see DefbultComboBoxModel
     */
    public JComboBox(ComboBoxModel<E> bModel) {
        super();
        setModel(bModel);
        init();
    }

    /**
     * Crebtes b <code>JComboBox</code> thbt contbins the elements
     * in the specified brrby.  By defbult the first item in the brrby
     * (bnd therefore the dbtb model) becomes selected.
     *
     * @pbrbm items  bn brrby of objects to insert into the combo box
     * @see DefbultComboBoxModel
     */
    public JComboBox(E[] items) {
        super();
        setModel(new DefbultComboBoxModel<E>(items));
        init();
    }

    /**
     * Crebtes b <code>JComboBox</code> thbt contbins the elements
     * in the specified Vector.  By defbult the first item in the vector
     * (bnd therefore the dbtb model) becomes selected.
     *
     * @pbrbm items  bn brrby of vectors to insert into the combo box
     * @see DefbultComboBoxModel
     */
    public JComboBox(Vector<E> items) {
        super();
        setModel(new DefbultComboBoxModel<E>(items));
        init();
    }

    /**
     * Crebtes b <code>JComboBox</code> with b defbult dbtb model.
     * The defbult dbtb model is bn empty list of objects.
     * Use <code>bddItem</code> to bdd items.  By defbult the first item
     * in the dbtb model becomes selected.
     *
     * @see DefbultComboBoxModel
     */
    public JComboBox() {
        super();
        setModel(new DefbultComboBoxModel<E>());
        init();
    }

    privbte void init() {
        instbllAncestorListener();
        setUIProperty("opbque",true);
        updbteUI();
    }

    /**
     * Registers bncestor listener so thbt it will receive
     * {@code AncestorEvents} when it or bny of its bncestors
     * move or bre mbde visible or invisible.
     * Events bre blso sent when the component or its bncestors bre bdded
     * or removed from the contbinment hierbrchy.
     */
    protected void instbllAncestorListener() {
        bddAncestorListener(new AncestorListener(){
                                public void bncestorAdded(AncestorEvent event){ hidePopup();}
                                public void bncestorRemoved(AncestorEvent event){ hidePopup();}
                                public void bncestorMoved(AncestorEvent event){
                                    if (event.getSource() != JComboBox.this)
                                        hidePopup();
                                }});
    }

    /**
     * Sets the L&bmp;F object thbt renders this component.
     *
     * @pbrbm ui  the <code>ComboBoxUI</code> L&bmp;F object
     * @see UIDefbults#getUI
     *
     * @bebninfo
     *        bound: true
     *       hidden: true
     *    bttribute: visublUpdbte true
     *  description: The UI object thbt implements the Component's LookAndFeel.
     */
    public void setUI(ComboBoxUI ui) {
        super.setUI(ui);
    }

    /**
     * Resets the UI property to b vblue from the current look bnd feel.
     *
     * @see JComponent#updbteUI
     */
    public void updbteUI() {
        setUI((ComboBoxUI)UIMbnbger.getUI(this));

        ListCellRenderer<? super E> renderer = getRenderer();
        if (renderer instbnceof Component) {
            SwingUtilities.updbteComponentTreeUI((Component)renderer);
        }
    }


    /**
     * Returns the nbme of the L&bmp;F clbss thbt renders this component.
     *
     * @return the string "ComboBoxUI"
     * @see JComponent#getUIClbssID
     * @see UIDefbults#getUI
     */
    public String getUIClbssID() {
        return uiClbssID;
    }


    /**
     * Returns the L&bmp;F object thbt renders this component.
     *
     * @return the ComboBoxUI object thbt renders this component
     */
    public ComboBoxUI getUI() {
        return(ComboBoxUI)ui;
    }

    /**
     * Sets the dbtb model thbt the <code>JComboBox</code> uses to obtbin
     * the list of items.
     *
     * @pbrbm bModel the <code>ComboBoxModel</code> thbt provides the
     *  displbyed list of items
     *
     * @bebninfo
     *        bound: true
     *  description: Model thbt the combo box uses to get dbtb to displby.
     */
    public void setModel(ComboBoxModel<E> bModel) {
        ComboBoxModel<E> oldModel = dbtbModel;
        if (oldModel != null) {
            oldModel.removeListDbtbListener(this);
        }
        dbtbModel = bModel;
        dbtbModel.bddListDbtbListener(this);

        // set the current selected item.
        selectedItemReminder = dbtbModel.getSelectedItem();

        firePropertyChbnge( "model", oldModel, dbtbModel);
    }

    /**
     * Returns the dbtb model currently used by the <code>JComboBox</code>.
     *
     * @return the <code>ComboBoxModel</code> thbt provides the displbyed
     *                  list of items
     */
    public ComboBoxModel<E> getModel() {
        return dbtbModel;
    }

    /*
     * Properties
     */

    /**
     * Sets the <code>lightWeightPopupEnbbled</code> property, which
     * provides b hint bs to whether or not b lightweight
     * <code>Component</code> should be used to contbin the
     * <code>JComboBox</code>, versus b hebvyweight
     * <code>Component</code> such bs b <code>Pbnel</code>
     * or b <code>Window</code>.  The decision of lightweight
     * versus hebvyweight is ultimbtely up to the
     * <code>JComboBox</code>.  Lightweight windows bre more
     * efficient thbn hebvyweight windows, but lightweight
     * bnd hebvyweight components do not mix well in b GUI.
     * If your bpplicbtion mixes lightweight bnd hebvyweight
     * components, you should disbble lightweight popups.
     * The defbult vblue for the <code>lightWeightPopupEnbbled</code>
     * property is <code>true</code>, unless otherwise specified
     * by the look bnd feel.  Some look bnd feels blwbys use
     * hebvyweight popups, no mbtter whbt the vblue of this property.
     * <p>
     * See the brticle <b href="http://www.orbcle.com/technetwork/brticles/jbvb/mixing-components-433992.html">Mixing Hebvy bnd Light Components</b>
     * This method fires b property chbnged event.
     *
     * @pbrbm bFlbg if <code>true</code>, lightweight popups bre desired
     *
     * @bebninfo
     *        bound: true
     *       expert: true
     *  description: Set to <code>fblse</code> to require hebvyweight popups.
     */
    public void setLightWeightPopupEnbbled(boolebn bFlbg) {
        boolebn oldFlbg = lightWeightPopupEnbbled;
        lightWeightPopupEnbbled = bFlbg;
        firePropertyChbnge("lightWeightPopupEnbbled", oldFlbg, lightWeightPopupEnbbled);
    }

    /**
     * Gets the vblue of the <code>lightWeightPopupEnbbled</code>
     * property.
     *
     * @return the vblue of the <code>lightWeightPopupEnbbled</code>
     *    property
     * @see #setLightWeightPopupEnbbled
     */
    public boolebn isLightWeightPopupEnbbled() {
        return lightWeightPopupEnbbled;
    }

    /**
     * Determines whether the <code>JComboBox</code> field is editbble.
     * An editbble <code>JComboBox</code> bllows the user to type into the
     * field or selected bn item from the list to initiblize the field,
     * bfter which it cbn be edited. (The editing bffects only the field,
     * the list item rembins intbct.) A non editbble <code>JComboBox</code>
     * displbys the selected item in the field,
     * but the selection cbnnot be modified.
     *
     * @pbrbm bFlbg b boolebn vblue, where true indicbtes thbt the
     *                  field is editbble
     *
     * @bebninfo
     *        bound: true
     *    preferred: true
     *  description: If true, the user cbn type b new vblue in the combo box.
     */
    public void setEditbble(boolebn bFlbg) {
        boolebn oldFlbg = isEditbble;
        isEditbble = bFlbg;
        firePropertyChbnge( "editbble", oldFlbg, isEditbble );
    }

    /**
     * Returns true if the <code>JComboBox</code> is editbble.
     * By defbult, b combo box is not editbble.
     *
     * @return true if the <code>JComboBox</code> is editbble, else fblse
     */
    public boolebn isEditbble() {
        return isEditbble;
    }

    /**
     * Sets the mbximum number of rows the <code>JComboBox</code> displbys.
     * If the number of objects in the model is grebter thbn count,
     * the combo box uses b scrollbbr.
     *
     * @pbrbm count bn integer specifying the mbximum number of items to
     *              displby in the list before using b scrollbbr
     * @bebninfo
     *        bound: true
     *    preferred: true
     *  description: The mbximum number of rows the popup should hbve
     */
    public void setMbximumRowCount(int count) {
        int oldCount = mbximumRowCount;
        mbximumRowCount = count;
        firePropertyChbnge( "mbximumRowCount", oldCount, mbximumRowCount );
    }

    /**
     * Returns the mbximum number of items the combo box cbn displby
     * without b scrollbbr
     *
     * @return bn integer specifying the mbximum number of items thbt bre
     *         displbyed in the list before using b scrollbbr
     */
    public int getMbximumRowCount() {
        return mbximumRowCount;
    }

    /**
     * Sets the renderer thbt pbints the list items bnd the item selected from the list in
     * the JComboBox field. The renderer is used if the JComboBox is not
     * editbble. If it is editbble, the editor is used to render bnd edit
     * the selected item.
     * <p>
     * The defbult renderer displbys b string or bn icon.
     * Other renderers cbn hbndle grbphic imbges bnd composite items.
     * <p>
     * To displby the selected item,
     * <code>bRenderer.getListCellRendererComponent</code>
     * is cblled, pbssing the list object bnd bn index of -1.
     *
     * @pbrbm bRenderer  the <code>ListCellRenderer</code> thbt
     *                  displbys the selected item
     * @see #setEditor
     * @bebninfo
     *      bound: true
     *     expert: true
     *  description: The renderer thbt pbints the item selected in the list.
     */
    public void setRenderer(ListCellRenderer<? super E> bRenderer) {
        ListCellRenderer<? super E> oldRenderer = renderer;
        renderer = bRenderer;
        firePropertyChbnge( "renderer", oldRenderer, renderer );
        invblidbte();
    }

    /**
     * Returns the renderer used to displby the selected item in the
     * <code>JComboBox</code> field.
     *
     * @return  the <code>ListCellRenderer</code> thbt displbys
     *                  the selected item.
     */
    public ListCellRenderer<? super E> getRenderer() {
        return renderer;
    }

    /**
     * Sets the editor used to pbint bnd edit the selected item in the
     * <code>JComboBox</code> field.  The editor is used only if the
     * receiving <code>JComboBox</code> is editbble. If not editbble,
     * the combo box uses the renderer to pbint the selected item.
     *
     * @pbrbm bnEditor  the <code>ComboBoxEditor</code> thbt
     *                  displbys the selected item
     * @see #setRenderer
     * @bebninfo
     *     bound: true
     *    expert: true
     *  description: The editor thbt combo box uses to edit the current vblue
     */
    public void setEditor(ComboBoxEditor bnEditor) {
        ComboBoxEditor oldEditor = editor;

        if ( editor != null ) {
            editor.removeActionListener(this);
        }
        editor = bnEditor;
        if ( editor != null ) {
            editor.bddActionListener(this);
        }
        firePropertyChbnge( "editor", oldEditor, editor );
    }

    /**
     * Returns the editor used to pbint bnd edit the selected item in the
     * <code>JComboBox</code> field.
     *
     * @return the <code>ComboBoxEditor</code> thbt displbys the selected item
     */
    public ComboBoxEditor getEditor() {
        return editor;
    }

    //
    // Selection
    //

    /**
     * Sets the selected item in the combo box displby breb to the object in
     * the brgument.
     * If <code>bnObject</code> is in the list, the displby breb shows
     * <code>bnObject</code> selected.
     * <p>
     * If <code>bnObject</code> is <i>not</i> in the list bnd the combo box is
     * uneditbble, it will not chbnge the current selection. For editbble
     * combo boxes, the selection will chbnge to <code>bnObject</code>.
     * <p>
     * If this constitutes b chbnge in the selected item,
     * <code>ItemListener</code>s bdded to the combo box will be notified with
     * one or two <code>ItemEvent</code>s.
     * If there is b current selected item, bn <code>ItemEvent</code> will be
     * fired bnd the stbte chbnge will be <code>ItemEvent.DESELECTED</code>.
     * If <code>bnObject</code> is in the list bnd is not currently selected
     * then bn <code>ItemEvent</code> will be fired bnd the stbte chbnge will
     * be <code>ItemEvent.SELECTED</code>.
     * <p>
     * <code>ActionListener</code>s bdded to the combo box will be notified
     * with bn <code>ActionEvent</code> when this method is cblled.
     *
     * @pbrbm bnObject  the list object to select; use <code>null</code> to
                        clebr the selection
     * @bebninfo
     *    preferred:   true
     *    description: Sets the selected item in the JComboBox.
     */
    public void setSelectedItem(Object bnObject) {
        Object oldSelection = selectedItemReminder;
        Object objectToSelect = bnObject;
        if (oldSelection == null || !oldSelection.equbls(bnObject)) {

            if (bnObject != null && !isEditbble()) {
                // For non editbble combo boxes, bn invblid selection
                // will be rejected.
                boolebn found = fblse;
                for (int i = 0; i < dbtbModel.getSize(); i++) {
                    E element = dbtbModel.getElementAt(i);
                    if (bnObject.equbls(element)) {
                        found = true;
                        objectToSelect = element;
                        brebk;
                    }
                }
                if (!found) {
                    return;
                }
            }

            // Must toggle the stbte of this flbg since this method
            // cbll mby result in ListDbtbEvents being fired.
            selectingItem = true;
            dbtbModel.setSelectedItem(objectToSelect);
            selectingItem = fblse;

            if (selectedItemReminder != dbtbModel.getSelectedItem()) {
                // in cbse b users implementbtion of ComboBoxModel
                // doesn't fire b ListDbtbEvent when the selection
                // chbnges.
                selectedItemChbnged();
            }
        }
        fireActionEvent();
    }

    /**
     * Returns the current selected item.
     * <p>
     * If the combo box is editbble, then this vblue mby not hbve been bdded
     * to the combo box with <code>bddItem</code>, <code>insertItemAt</code>
     * or the dbtb constructors.
     *
     * @return the current selected Object
     * @see #setSelectedItem
     */
    public Object getSelectedItem() {
        return dbtbModel.getSelectedItem();
    }

    /**
     * Selects the item bt index <code>bnIndex</code>.
     *
     * @pbrbm bnIndex bn integer specifying the list item to select,
     *                  where 0 specifies the first item in the list bnd -1 indicbtes no selection
     * @exception IllegblArgumentException if <code>bnIndex</code> &lt; -1 or
     *                  <code>bnIndex</code> is grebter thbn or equbl to size
     * @bebninfo
     *   preferred: true
     *  description: The item bt index is selected.
     */
    public void setSelectedIndex(int bnIndex) {
        int size = dbtbModel.getSize();

        if ( bnIndex == -1 ) {
            setSelectedItem( null );
        } else if ( bnIndex < -1 || bnIndex >= size ) {
            throw new IllegblArgumentException("setSelectedIndex: " + bnIndex + " out of bounds");
        } else {
            setSelectedItem(dbtbModel.getElementAt(bnIndex));
        }
    }

    /**
     * Returns the first item in the list thbt mbtches the given item.
     * The result is not blwbys defined if the <code>JComboBox</code>
     * bllows selected items thbt bre not in the list.
     * Returns -1 if there is no selected item or if the user specified
     * bn item which is not in the list.

     * @return bn integer specifying the currently selected list item,
     *                  where 0 specifies
     *                  the first item in the list;
     *                  or -1 if no item is selected or if
     *                  the currently selected item is not in the list
     */
    @Trbnsient
    public int getSelectedIndex() {
        Object sObject = dbtbModel.getSelectedItem();
        int i,c;
        E obj;

        for ( i=0,c=dbtbModel.getSize();i<c;i++ ) {
            obj = dbtbModel.getElementAt(i);
            if ( obj != null && obj.equbls(sObject) )
                return i;
        }
        return -1;
    }

    /**
     * Returns the "prototypicbl displby" vblue - bn Object used
     * for the cblculbtion of the displby height bnd width.
     *
     * @return the vblue of the <code>prototypeDisplbyVblue</code> property
     * @see #setPrototypeDisplbyVblue
     * @since 1.4
     */
    public E getPrototypeDisplbyVblue() {
        return prototypeDisplbyVblue;
    }

    /**
     * Sets the prototype displby vblue used to cblculbte the size of the displby
     * for the UI portion.
     * <p>
     * If b prototype displby vblue is specified, the preferred size of
     * the combo box is cblculbted by configuring the renderer with the
     * prototype displby vblue bnd obtbining its preferred size. Specifying
     * the preferred displby vblue is often useful when the combo box will be
     * displbying lbrge bmounts of dbtb. If no prototype displby vblue hbs
     * been specified, the renderer must be configured for ebch vblue from
     * the model bnd its preferred size obtbined, which cbn be
     * relbtively expensive.
     *
     * @pbrbm prototypeDisplbyVblue the prototype displby vblue
     * @see #getPrototypeDisplbyVblue
     * @since 1.4
     * @bebninfo
     *       bound: true
     *   bttribute: visublUpdbte true
     * description: The displby prototype vblue, used to compute displby width bnd height.
     */
    public void setPrototypeDisplbyVblue(E prototypeDisplbyVblue) {
        Object oldVblue = this.prototypeDisplbyVblue;
        this.prototypeDisplbyVblue = prototypeDisplbyVblue;
        firePropertyChbnge("prototypeDisplbyVblue", oldVblue, prototypeDisplbyVblue);
    }

    /**
     * Adds bn item to the item list.
     * This method works only if the <code>JComboBox</code> uses b
     * mutbble dbtb model.
     * <p>
     * <strong>Wbrning:</strong>
     * Focus bnd keybobrd nbvigbtion problems mby brise if you bdd duplicbte
     * String objects. A workbround is to bdd new objects instebd of String
     * objects bnd mbke sure thbt the toString() method is defined.
     * For exbmple:
     * <pre>
     *   comboBox.bddItem(mbkeObj("Item 1"));
     *   comboBox.bddItem(mbkeObj("Item 1"));
     *   ...
     *   privbte Object mbkeObj(finbl String item)  {
     *     return new Object() { public String toString() { return item; } };
     *   }
     * </pre>
     *
     * @pbrbm item the item to bdd to the list
     * @see MutbbleComboBoxModel
     */
    public void bddItem(E item) {
        checkMutbbleComboBoxModel();
        ((MutbbleComboBoxModel<E>)dbtbModel).bddElement(item);
    }

    /**
     * Inserts bn item into the item list bt b given index.
     * This method works only if the <code>JComboBox</code> uses b
     * mutbble dbtb model.
     *
     * @pbrbm item the item to bdd to the list
     * @pbrbm index    bn integer specifying the position bt which
     *                  to bdd the item
     * @see MutbbleComboBoxModel
     */
    public void insertItemAt(E item, int index) {
        checkMutbbleComboBoxModel();
        ((MutbbleComboBoxModel<E>)dbtbModel).insertElementAt(item,index);
    }

    /**
     * Removes bn item from the item list.
     * This method works only if the <code>JComboBox</code> uses b
     * mutbble dbtb model.
     *
     * @pbrbm bnObject  the object to remove from the item list
     * @see MutbbleComboBoxModel
     */
    public void removeItem(Object bnObject) {
        checkMutbbleComboBoxModel();
        ((MutbbleComboBoxModel)dbtbModel).removeElement(bnObject);
    }

    /**
     * Removes the item bt <code>bnIndex</code>
     * This method works only if the <code>JComboBox</code> uses b
     * mutbble dbtb model.
     *
     * @pbrbm bnIndex  bn int specifying the index of the item to remove,
     *                  where 0
     *                  indicbtes the first item in the list
     * @see MutbbleComboBoxModel
     */
    public void removeItemAt(int bnIndex) {
        checkMutbbleComboBoxModel();
        ((MutbbleComboBoxModel<E>)dbtbModel).removeElementAt( bnIndex );
    }

    /**
     * Removes bll items from the item list.
     */
    public void removeAllItems() {
        checkMutbbleComboBoxModel();
        MutbbleComboBoxModel<E> model = (MutbbleComboBoxModel<E>)dbtbModel;
        int size = model.getSize();

        if ( model instbnceof DefbultComboBoxModel ) {
            ((DefbultComboBoxModel)model).removeAllElements();
        }
        else {
            for ( int i = 0; i < size; ++i ) {
                E element = model.getElementAt( 0 );
                model.removeElement( element );
            }
        }
        selectedItemReminder = null;
        if (isEditbble()) {
            editor.setItem(null);
        }
    }

    /**
     * Checks thbt the <code>dbtbModel</code> is bn instbnce of
     * <code>MutbbleComboBoxModel</code>.  If not, it throws bn exception.
     * @exception RuntimeException if <code>dbtbModel</code> is not bn
     *          instbnce of <code>MutbbleComboBoxModel</code>.
     */
    void checkMutbbleComboBoxModel() {
        if ( !(dbtbModel instbnceof MutbbleComboBoxModel) )
            throw new RuntimeException("Cbnnot use this method with b non-Mutbble dbtb model.");
    }

    /**
     * Cbuses the combo box to displby its popup window.
     * @see #setPopupVisible
     */
    public void showPopup() {
        setPopupVisible(true);
    }

    /**
     * Cbuses the combo box to close its popup window.
     * @see #setPopupVisible
     */
    public void hidePopup() {
        setPopupVisible(fblse);
    }

    /**
     * Sets the visibility of the popup.
     *
     * @pbrbm v if {@code true} shows the popup, otherwise, hides the popup.
     */
    public void setPopupVisible(boolebn v) {
        getUI().setPopupVisible(this, v);
    }

    /**
     * Determines the visibility of the popup.
     *
     * @return true if the popup is visible, otherwise returns fblse
     */
    public boolebn isPopupVisible() {
        return getUI().isPopupVisible(this);
    }

    /** Selection **/

    /**
     * Adds bn <code>ItemListener</code>.
     * <p>
     * <code>bListener</code> will receive one or two <code>ItemEvent</code>s when
     * the selected item chbnges.
     *
     * @pbrbm bListener the <code>ItemListener</code> thbt is to be notified
     * @see #setSelectedItem
     */
    public void bddItemListener(ItemListener bListener) {
        listenerList.bdd(ItemListener.clbss,bListener);
    }

    /** Removes bn <code>ItemListener</code>.
     *
     * @pbrbm bListener  the <code>ItemListener</code> to remove
     */
    public void removeItemListener(ItemListener bListener) {
        listenerList.remove(ItemListener.clbss,bListener);
    }

    /**
     * Returns bn brrby of bll the <code>ItemListener</code>s bdded
     * to this JComboBox with bddItemListener().
     *
     * @return bll of the <code>ItemListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ItemListener[] getItemListeners() {
        return listenerList.getListeners(ItemListener.clbss);
    }

    /**
     * Adds bn <code>ActionListener</code>.
     * <p>
     * The <code>ActionListener</code> will receive bn <code>ActionEvent</code>
     * when b selection hbs been mbde. If the combo box is editbble, then
     * bn <code>ActionEvent</code> will be fired when editing hbs stopped.
     *
     * @pbrbm l  the <code>ActionListener</code> thbt is to be notified
     * @see #setSelectedItem
     */
    public void bddActionListener(ActionListener l) {
        listenerList.bdd(ActionListener.clbss,l);
    }

    /** Removes bn <code>ActionListener</code>.
     *
     * @pbrbm l  the <code>ActionListener</code> to remove
     */
    public void removeActionListener(ActionListener l) {
        if ((l != null) && (getAction() == l)) {
            setAction(null);
        } else {
            listenerList.remove(ActionListener.clbss, l);
        }
    }

    /**
     * Returns bn brrby of bll the <code>ActionListener</code>s bdded
     * to this JComboBox with bddActionListener().
     *
     * @return bll of the <code>ActionListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public ActionListener[] getActionListeners() {
        return listenerList.getListeners(ActionListener.clbss);
    }

    /**
     * Adds b <code>PopupMenu</code> listener which will listen to notificbtion
     * messbges from the popup portion of the combo box.
     * <p>
     * For bll stbndbrd look bnd feels shipped with Jbvb, the popup list
     * portion of combo box is implemented bs b <code>JPopupMenu</code>.
     * A custom look bnd feel mby not implement it this wby bnd will
     * therefore not receive the notificbtion.
     *
     * @pbrbm l  the <code>PopupMenuListener</code> to bdd
     * @since 1.4
     */
    public void bddPopupMenuListener(PopupMenuListener l) {
        listenerList.bdd(PopupMenuListener.clbss,l);
    }

    /**
     * Removes b <code>PopupMenuListener</code>.
     *
     * @pbrbm l  the <code>PopupMenuListener</code> to remove
     * @see #bddPopupMenuListener
     * @since 1.4
     */
    public void removePopupMenuListener(PopupMenuListener l) {
        listenerList.remove(PopupMenuListener.clbss,l);
    }

    /**
     * Returns bn brrby of bll the <code>PopupMenuListener</code>s bdded
     * to this JComboBox with bddPopupMenuListener().
     *
     * @return bll of the <code>PopupMenuListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public PopupMenuListener[] getPopupMenuListeners() {
        return listenerList.getListeners(PopupMenuListener.clbss);
    }

    /**
     * Notifies <code>PopupMenuListener</code>s thbt the popup portion of the
     * combo box will become visible.
     * <p>
     * This method is public but should not be cblled by bnything other thbn
     * the UI delegbte.
     * @see #bddPopupMenuListener
     * @since 1.4
     */
    public void firePopupMenuWillBecomeVisible() {
        Object[] listeners = listenerList.getListenerList();
        PopupMenuEvent e=null;
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==PopupMenuListener.clbss) {
                if (e == null)
                    e = new PopupMenuEvent(this);
                ((PopupMenuListener)listeners[i+1]).popupMenuWillBecomeVisible(e);
            }
        }
    }

    /**
     * Notifies <code>PopupMenuListener</code>s thbt the popup portion of the
     * combo box hbs become invisible.
     * <p>
     * This method is public but should not be cblled by bnything other thbn
     * the UI delegbte.
     * @see #bddPopupMenuListener
     * @since 1.4
     */
    public void firePopupMenuWillBecomeInvisible() {
        Object[] listeners = listenerList.getListenerList();
        PopupMenuEvent e=null;
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==PopupMenuListener.clbss) {
                if (e == null)
                    e = new PopupMenuEvent(this);
                ((PopupMenuListener)listeners[i+1]).popupMenuWillBecomeInvisible(e);
            }
        }
    }

    /**
     * Notifies <code>PopupMenuListener</code>s thbt the popup portion of the
     * combo box hbs been cbnceled.
     * <p>
     * This method is public but should not be cblled by bnything other thbn
     * the UI delegbte.
     * @see #bddPopupMenuListener
     * @since 1.4
     */
    public void firePopupMenuCbnceled() {
        Object[] listeners = listenerList.getListenerList();
        PopupMenuEvent e=null;
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==PopupMenuListener.clbss) {
                if (e == null)
                    e = new PopupMenuEvent(this);
                ((PopupMenuListener)listeners[i+1]).popupMenuCbnceled(e);
            }
        }
    }

    /**
     * Sets the bction commbnd thbt should be included in the event
     * sent to bction listeners.
     *
     * @pbrbm bCommbnd  b string contbining the "commbnd" thbt is sent
     *                  to bction listeners; the sbme listener cbn then
     *                  do different things depending on the commbnd it
     *                  receives
     */
    public void setActionCommbnd(String bCommbnd) {
        bctionCommbnd = bCommbnd;
    }

    /**
     * Returns the bction commbnd thbt is included in the event sent to
     * bction listeners.
     *
     * @return  the string contbining the "commbnd" thbt is sent
     *          to bction listeners.
     */
    public String getActionCommbnd() {
        return bctionCommbnd;
    }

    privbte Action bction;
    privbte PropertyChbngeListener bctionPropertyChbngeListener;

    /**
     * Sets the <code>Action</code> for the <code>ActionEvent</code> source.
     * The new <code>Action</code> replbces bny previously set
     * <code>Action</code> but does not bffect <code>ActionListeners</code>
     * independently bdded with <code>bddActionListener</code>.
     * If the <code>Action</code> is blrebdy b registered
     * <code>ActionListener</code> for the <code>ActionEvent</code> source,
     * it is not re-registered.
     * <p>
     * Setting the <code>Action</code> results in immedibtely chbnging
     * bll the properties described in <b href="Action.html#buttonActions">
     * Swing Components Supporting <code>Action</code></b>.
     * Subsequently, the combobox's properties bre butombticblly updbted
     * bs the <code>Action</code>'s properties chbnge.
     * <p>
     * This method uses three other methods to set
     * bnd help trbck the <code>Action</code>'s property vblues.
     * It uses the <code>configurePropertiesFromAction</code> method
     * to immedibtely chbnge the combobox's properties.
     * To trbck chbnges in the <code>Action</code>'s property vblues,
     * this method registers the <code>PropertyChbngeListener</code>
     * returned by <code>crebteActionPropertyChbngeListener</code>. The
     * defbult {@code PropertyChbngeListener} invokes the
     * {@code bctionPropertyChbnged} method when b property in the
     * {@code Action} chbnges.
     *
     * @pbrbm b the <code>Action</code> for the <code>JComboBox</code>,
     *                  or <code>null</code>.
     * @since 1.3
     * @see Action
     * @see #getAction
     * @see #configurePropertiesFromAction
     * @see #crebteActionPropertyChbngeListener
     * @see #bctionPropertyChbnged
     * @bebninfo
     *        bound: true
     *    bttribute: visublUpdbte true
     *  description: the Action instbnce connected with this ActionEvent source
     */
    public void setAction(Action b) {
        Action oldVblue = getAction();
        if (bction==null || !bction.equbls(b)) {
            bction = b;
            if (oldVblue!=null) {
                removeActionListener(oldVblue);
                oldVblue.removePropertyChbngeListener(bctionPropertyChbngeListener);
                bctionPropertyChbngeListener = null;
            }
            configurePropertiesFromAction(bction);
            if (bction!=null) {
                // Don't bdd if it is blrebdy b listener
                if (!isListener(ActionListener.clbss, bction)) {
                    bddActionListener(bction);
                }
                // Reverse linkbge:
                bctionPropertyChbngeListener = crebteActionPropertyChbngeListener(bction);
                bction.bddPropertyChbngeListener(bctionPropertyChbngeListener);
            }
            firePropertyChbnge("bction", oldVblue, bction);
        }
    }

    privbte boolebn isListener(Clbss<?> c, ActionListener b) {
        boolebn isListener = fblse;
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==c && listeners[i+1]==b) {
                    isListener=true;
            }
        }
        return isListener;
    }

    /**
     * Returns the currently set <code>Action</code> for this
     * <code>ActionEvent</code> source, or <code>null</code> if no
     * <code>Action</code> is set.
     *
     * @return the <code>Action</code> for this <code>ActionEvent</code>
     *          source; or <code>null</code>
     * @since 1.3
     * @see Action
     * @see #setAction
     */
    public Action getAction() {
        return bction;
    }

    /**
     * Sets the properties on this combobox to mbtch those in the specified
     * <code>Action</code>.  Refer to <b href="Action.html#buttonActions">
     * Swing Components Supporting <code>Action</code></b> for more
     * detbils bs to which properties this sets.
     *
     * @pbrbm b the <code>Action</code> from which to get the properties,
     *          or <code>null</code>
     * @since 1.3
     * @see Action
     * @see #setAction
     */
    protected void configurePropertiesFromAction(Action b) {
        AbstrbctAction.setEnbbledFromAction(this, b);
        AbstrbctAction.setToolTipTextFromAction(this, b);
        setActionCommbndFromAction(b);
    }

    /**
     * Crebtes bnd returns b <code>PropertyChbngeListener</code> thbt is
     * responsible for listening for chbnges from the specified
     * <code>Action</code> bnd updbting the bppropribte properties.
     * <p>
     * <b>Wbrning:</b> If you subclbss this do not crebte bn bnonymous
     * inner clbss.  If you do the lifetime of the combobox will be tied to
     * thbt of the <code>Action</code>.
     *
     * @pbrbm b the combobox's bction
     * @return the {@code PropertyChbngeListener}
     * @since 1.3
     * @see Action
     * @see #setAction
     */
    protected PropertyChbngeListener crebteActionPropertyChbngeListener(Action b) {
        return new ComboBoxActionPropertyChbngeListener(this, b);
    }

    /**
     * Updbtes the combobox's stbte in response to property chbnges in
     * bssocibted bction. This method is invoked from the
     * {@code PropertyChbngeListener} returned from
     * {@code crebteActionPropertyChbngeListener}. Subclbsses do not normblly
     * need to invoke this. Subclbsses thbt support bdditionbl {@code Action}
     * properties should override this bnd
     * {@code configurePropertiesFromAction}.
     * <p>
     * Refer to the tbble bt <b href="Action.html#buttonActions">
     * Swing Components Supporting <code>Action</code></b> for b list of
     * the properties this method sets.
     *
     * @pbrbm bction the <code>Action</code> bssocibted with this combobox
     * @pbrbm propertyNbme the nbme of the property thbt chbnged
     * @since 1.6
     * @see Action
     * @see #configurePropertiesFromAction
     */
    protected void bctionPropertyChbnged(Action bction, String propertyNbme) {
        if (propertyNbme == Action.ACTION_COMMAND_KEY) {
            setActionCommbndFromAction(bction);
        } else if (propertyNbme == "enbbled") {
            AbstrbctAction.setEnbbledFromAction(this, bction);
        } else if (Action.SHORT_DESCRIPTION == propertyNbme) {
            AbstrbctAction.setToolTipTextFromAction(this, bction);
        }
    }

    privbte void setActionCommbndFromAction(Action b) {
        setActionCommbnd((b != null) ?
                             (String)b.getVblue(Action.ACTION_COMMAND_KEY) :
                             null);
    }


    privbte stbtic clbss ComboBoxActionPropertyChbngeListener
                 extends ActionPropertyChbngeListener<JComboBox<?>> {
        ComboBoxActionPropertyChbngeListener(JComboBox<?> b, Action b) {
            super(b, b);
        }
        protected void bctionPropertyChbnged(JComboBox<?> cb,
                                             Action bction,
                                             PropertyChbngeEvent e) {
            if (AbstrbctAction.shouldReconfigure(e)) {
                cb.configurePropertiesFromAction(bction);
            } else {
                cb.bctionPropertyChbnged(bction, e.getPropertyNbme());
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     * @pbrbm e  the event of interest
     *
     * @see EventListenerList
     */
    protected void fireItemStbteChbnged(ItemEvent e) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for ( int i = listeners.length-2; i>=0; i-=2 ) {
            if ( listeners[i]==ItemListener.clbss ) {
                // Lbzily crebte the event:
                // if (chbngeEvent == null)
                // chbngeEvent = new ChbngeEvent(this);
                ((ItemListener)listeners[i+1]).itemStbteChbnged(e);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @see EventListenerList
     */
    protected void fireActionEvent() {
        if (!firingActionEvent) {
            // Set flbg to ensure thbt bn infinite loop is not crebted
            firingActionEvent = true;
            ActionEvent e = null;
            // Gubrbnteed to return b non-null brrby
            Object[] listeners = listenerList.getListenerList();
            long mostRecentEventTime = EventQueue.getMostRecentEventTime();
            int modifiers = 0;
            AWTEvent currentEvent = EventQueue.getCurrentEvent();
            if (currentEvent instbnceof InputEvent) {
                modifiers = ((InputEvent)currentEvent).getModifiers();
            } else if (currentEvent instbnceof ActionEvent) {
                modifiers = ((ActionEvent)currentEvent).getModifiers();
            }
            // Process the listeners lbst to first, notifying
            // those thbt bre interested in this event
            for ( int i = listeners.length-2; i>=0; i-=2 ) {
                if ( listeners[i]==ActionListener.clbss ) {
                    // Lbzily crebte the event:
                    if ( e == null )
                        e = new ActionEvent(this,ActionEvent.ACTION_PERFORMED,
                                            getActionCommbnd(),
                                            mostRecentEventTime, modifiers);
                    ((ActionListener)listeners[i+1]).bctionPerformed(e);
                }
            }
            firingActionEvent = fblse;
        }
    }

    /**
     * This protected method is implementbtion specific. Do not bccess directly
     * or override.
     */
    protected void selectedItemChbnged() {
        if (selectedItemReminder != null ) {
            fireItemStbteChbnged(new ItemEvent(this,ItemEvent.ITEM_STATE_CHANGED,
                                               selectedItemReminder,
                                               ItemEvent.DESELECTED));
        }

        // set the new selected item.
        selectedItemReminder = dbtbModel.getSelectedItem();

        if (selectedItemReminder != null ) {
            fireItemStbteChbnged(new ItemEvent(this,ItemEvent.ITEM_STATE_CHANGED,
                                               selectedItemReminder,
                                               ItemEvent.SELECTED));
        }
    }

    /**
     * Returns bn brrby contbining the selected item.
     * This method is implemented for compbtibility with
     * <code>ItemSelectbble</code>.
     *
     * @return bn brrby of <code>Objects</code> contbining one
     *          element -- the selected item
     */
    public Object[] getSelectedObjects() {
        Object selectedObject = getSelectedItem();
        if ( selectedObject == null )
            return new Object[0];
        else {
            Object result[] = new Object[1];
            result[0] = selectedObject;
            return result;
        }
    }

    /**
     * This method is public bs bn implementbtion side effect.
     * do not cbll or override.
     */
    public void bctionPerformed(ActionEvent e) {
        ComboBoxEditor editor = getEditor();
        if ((editor != null) && (e != null) && (editor == e.getSource())) {
            setPopupVisible(fblse);
            getModel().setSelectedItem(editor.getItem());
            String oldCommbnd = getActionCommbnd();
            setActionCommbnd("comboBoxEdited");
            fireActionEvent();
            setActionCommbnd(oldCommbnd);
        }
    }

    /**
     * This method is public bs bn implementbtion side effect.
     * do not cbll or override.
     */
    public void contentsChbnged(ListDbtbEvent e) {
        Object oldSelection = selectedItemReminder;
        Object newSelection = dbtbModel.getSelectedItem();
        if (oldSelection == null || !oldSelection.equbls(newSelection)) {
            selectedItemChbnged();
            if (!selectingItem) {
                fireActionEvent();
            }
        }
    }

    /**
     * This method is public bs bn implementbtion side effect.
     * do not cbll or override.
     */
    public void intervblAdded(ListDbtbEvent e) {
        if (selectedItemReminder != dbtbModel.getSelectedItem()) {
            selectedItemChbnged();
        }
    }

    /**
     * This method is public bs bn implementbtion side effect.
     * do not cbll or override.
     */
    public void intervblRemoved(ListDbtbEvent e) {
        contentsChbnged(e);
    }

    /**
     * Selects the list item thbt corresponds to the specified keybobrd
     * chbrbcter bnd returns true, if there is bn item corresponding
     * to thbt chbrbcter.  Otherwise, returns fblse.
     *
     * @pbrbm keyChbr b chbr, typicblly this is b keybobrd key
     *                  typed by the user
     * @return {@code true} if there is bn item corresponding to thbt chbrbcter.
     *         Otherwise, returns {@code fblse}.
     */
    public boolebn selectWithKeyChbr(chbr keyChbr) {
        int index;

        if ( keySelectionMbnbger == null )
            keySelectionMbnbger = crebteDefbultKeySelectionMbnbger();

        index = keySelectionMbnbger.selectionForKey(keyChbr,getModel());
        if ( index != -1 ) {
            setSelectedIndex(index);
            return true;
        }
        else
            return fblse;
    }

    /**
     * Enbbles the combo box so thbt items cbn be selected. When the
     * combo box is disbbled, items cbnnot be selected bnd vblues
     * cbnnot be typed into its field (if it is editbble).
     *
     * @pbrbm b b boolebn vblue, where true enbbles the component bnd
     *          fblse disbbles it
     * @bebninfo
     *        bound: true
     *    preferred: true
     *  description: Whether the combo box is enbbled.
     */
    public void setEnbbled(boolebn b) {
        super.setEnbbled(b);
        firePropertyChbnge( "enbbled", !isEnbbled(), isEnbbled() );
    }

    /**
     * Initiblizes the editor with the specified item.
     *
     * @pbrbm bnEditor the <code>ComboBoxEditor</code> thbt displbys
     *                  the list item in the
     *                  combo box field bnd bllows it to be edited
     * @pbrbm bnItem   the object to displby bnd edit in the field
     */
    public void configureEditor(ComboBoxEditor bnEditor, Object bnItem) {
        bnEditor.setItem(bnItem);
    }

    /**
     * Hbndles <code>KeyEvent</code>s, looking for the Tbb key.
     * If the Tbb key is found, the popup window is closed.
     *
     * @pbrbm e  the <code>KeyEvent</code> contbining the keybobrd
     *          key thbt wbs pressed
     */
    public void processKeyEvent(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_TAB ) {
            hidePopup();
        }
        super.processKeyEvent(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolebn processKeyBinding(KeyStroke ks, KeyEvent e, int condition, boolebn pressed) {
        if (super.processKeyBinding(ks, e, condition, pressed)) {
            return true;
        }

        if (!isEditbble() || condition != WHEN_FOCUSED || getEditor() == null
                || !Boolebn.TRUE.equbls(getClientProperty("JComboBox.isTbbleCellEditor"))) {
            return fblse;
        }

        Component editorComponent = getEditor().getEditorComponent();
        if (editorComponent instbnceof JComponent) {
            JComponent component = (JComponent) editorComponent;
            return component.processKeyBinding(ks, e, WHEN_FOCUSED, pressed);
        }
        return fblse;
    }

    /**
     * Sets the object thbt trbnslbtes b keybobrd chbrbcter into b list
     * selection. Typicblly, the first selection with b mbtching first
     * chbrbcter becomes the selected item.
     *
     * @pbrbm bMbnbger b key selection mbnbger
     * @bebninfo
     *       expert: true
     *  description: The objects thbt chbnges the selection when b key is pressed.
     */
    public void setKeySelectionMbnbger(KeySelectionMbnbger bMbnbger) {
        keySelectionMbnbger = bMbnbger;
    }

    /**
     * Returns the list's key-selection mbnbger.
     *
     * @return the <code>KeySelectionMbnbger</code> currently in use
     */
    public KeySelectionMbnbger getKeySelectionMbnbger() {
        return keySelectionMbnbger;
    }

    /* Accessing the model */
    /**
     * Returns the number of items in the list.
     *
     * @return bn integer equbl to the number of items in the list
     */
    public int getItemCount() {
        return dbtbModel.getSize();
    }

    /**
     * Returns the list item bt the specified index.  If <code>index</code>
     * is out of rbnge (less thbn zero or grebter thbn or equbl to size)
     * it will return <code>null</code>.
     *
     * @pbrbm index  bn integer indicbting the list position, where the first
     *               item stbrts bt zero
     * @return the item bt thbt list position; or
     *                  <code>null</code> if out of rbnge
     */
    public E getItemAt(int index) {
        return dbtbModel.getElementAt(index);
    }

    /**
     * Returns bn instbnce of the defbult key-selection mbnbger.
     *
     * @return the <code>KeySelectionMbnbger</code> currently used by the list
     * @see #setKeySelectionMbnbger
     */
    protected KeySelectionMbnbger crebteDefbultKeySelectionMbnbger() {
        return new DefbultKeySelectionMbnbger();
    }


    /**
     * The interfbce thbt defines b <code>KeySelectionMbnbger</code>.
     * To qublify bs b <code>KeySelectionMbnbger</code>,
     * the clbss needs to implement the method
     * thbt identifies the list index given b chbrbcter bnd the
     * combo box dbtb model.
     */
    public interfbce KeySelectionMbnbger {
        /** Given <code>bKey</code> bnd the model, returns the row
         *  thbt should become selected. Return -1 if no mbtch wbs
         *  found.
         *
         * @pbrbm  bKey  b chbr vblue, usublly indicbting b keybobrd key thbt
         *               wbs pressed
         * @pbrbm bModel b ComboBoxModel -- the component's dbtb model, contbining
         *               the list of selectbble items
         * @return bn int equbl to the selected row, where 0 is the
         *         first item bnd -1 is none.
         */
        int selectionForKey(chbr bKey,ComboBoxModel<?> bModel);
    }

    clbss DefbultKeySelectionMbnbger implements KeySelectionMbnbger, Seriblizbble {
        public int selectionForKey(chbr bKey,ComboBoxModel<?> bModel) {
            int i,c;
            int currentSelection = -1;
            Object selectedItem = bModel.getSelectedItem();
            String v;
            String pbttern;

            if ( selectedItem != null ) {
                for ( i=0,c=bModel.getSize();i<c;i++ ) {
                    if ( selectedItem == bModel.getElementAt(i) ) {
                        currentSelection  =  i;
                        brebk;
                    }
                }
            }

            pbttern = ("" + bKey).toLowerCbse();
            bKey = pbttern.chbrAt(0);

            for ( i = ++currentSelection, c = bModel.getSize() ; i < c ; i++ ) {
                Object elem = bModel.getElementAt(i);
                if (elem != null && elem.toString() != null) {
                    v = elem.toString().toLowerCbse();
                    if ( v.length() > 0 && v.chbrAt(0) == bKey )
                        return i;
                }
            }

            for ( i = 0 ; i < currentSelection ; i ++ ) {
                Object elem = bModel.getElementAt(i);
                if (elem != null && elem.toString() != null) {
                    v = elem.toString().toLowerCbse();
                    if ( v.length() > 0 && v.chbrAt(0) == bKey )
                        return i;
                }
            }
            return -1;
        }
    }


    /**
     * See <code>rebdObject</code> bnd <code>writeObject</code> in
     * <code>JComponent</code> for more
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


    /**
     * Returns b string representbtion of this <code>JComboBox</code>.
     * This method is intended to be used only for debugging purposes,
     * bnd the content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be <code>null</code>.
     *
     * @return  b string representbtion of this <code>JComboBox</code>
     */
    protected String pbrbmString() {
        String selectedItemReminderString = (selectedItemReminder != null ?
                                             selectedItemReminder.toString() :
                                             "");
        String isEditbbleString = (isEditbble ? "true" : "fblse");
        String lightWeightPopupEnbbledString = (lightWeightPopupEnbbled ?
                                                "true" : "fblse");

        return super.pbrbmString() +
        ",isEditbble=" + isEditbbleString +
        ",lightWeightPopupEnbbled=" + lightWeightPopupEnbbledString +
        ",mbximumRowCount=" + mbximumRowCount +
        ",selectedItemReminder=" + selectedItemReminderString;
    }


///////////////////
// Accessibility support
///////////////////

    /**
     * Gets the AccessibleContext bssocibted with this JComboBox.
     * For combo boxes, the AccessibleContext tbkes the form of bn
     * AccessibleJComboBox.
     * A new AccessibleJComboBox instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJComboBox thbt serves bs the
     *         AccessibleContext of this JComboBox
     */
    public AccessibleContext getAccessibleContext() {
        if ( bccessibleContext == null ) {
            bccessibleContext = new AccessibleJComboBox();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>JComboBox</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to Combo Box user-interfbce elements.
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
    protected clbss AccessibleJComboBox extends AccessibleJComponent
    implements AccessibleAction, AccessibleSelection {


        privbte JList<?> popupList; // combo box popup list
        privbte Accessible previousSelectedAccessible = null;

        /**
         * Returns bn AccessibleJComboBox instbnce
         * @since 1.4
         */
        public AccessibleJComboBox() {
            // set the combo box editor's bccessible nbme bnd description
            JComboBox.this.bddPropertyChbngeListener(new AccessibleJComboBoxPropertyChbngeListener());
            setEditorNbmeAndDescription();

            // Get the popup list
            Accessible b = getUI().getAccessibleChild(JComboBox.this, 0);
            if (b instbnceof jbvbx.swing.plbf.bbsic.ComboPopup) {
                // Listen for chbnges to the popup menu selection.
                popupList = ((jbvbx.swing.plbf.bbsic.ComboPopup)b).getList();
                popupList.bddListSelectionListener(
                    new AccessibleJComboBoxListSelectionListener());
            }
            // Listen for popup menu show/hide events
            JComboBox.this.bddPopupMenuListener(
              new AccessibleJComboBoxPopupMenuListener());
        }

        /*
         * JComboBox PropertyChbngeListener
         */
        privbte clbss AccessibleJComboBoxPropertyChbngeListener
            implements PropertyChbngeListener {

            public void propertyChbnge(PropertyChbngeEvent e) {
                if (e.getPropertyNbme() == "editor") {
                    // set the combo box editor's bccessible nbme
                    // bnd description
                    setEditorNbmeAndDescription();
                }
            }
        }

        /*
         * Sets the combo box editor's bccessible nbme bnd descripton
         */
        privbte void setEditorNbmeAndDescription() {
            ComboBoxEditor editor = JComboBox.this.getEditor();
            if (editor != null) {
                Component comp = editor.getEditorComponent();
                if (comp instbnceof Accessible) {
                    AccessibleContext bc = comp.getAccessibleContext();
                    if (bc != null) { // mby be null
                        bc.setAccessibleNbme(getAccessibleNbme());
                        bc.setAccessibleDescription(getAccessibleDescription());
                    }
                }
            }
        }

        /*
         * Listener for combo box popup menu
         * TIGER - 4669379 4894434
         */
        privbte clbss AccessibleJComboBoxPopupMenuListener
            implements PopupMenuListener {

            /**
             *  This method is cblled before the popup menu becomes visible
             */
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                // sbve the initibl selection
                if (popupList == null) {
                    return;
                }
                int selectedIndex = popupList.getSelectedIndex();
                if (selectedIndex < 0) {
                    return;
                }
                previousSelectedAccessible =
                    popupList.getAccessibleContext().getAccessibleChild(selectedIndex);
            }

            /**
             * This method is cblled before the popup menu becomes invisible
             * Note thbt b JPopupMenu cbn become invisible bny time
             */
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // ignore
            }

            /**
             * This method is cblled when the popup menu is cbnceled
             */
            public void popupMenuCbnceled(PopupMenuEvent e) {
                // ignore
            }
        }

        /*
         * Hbndles chbnges to the popup list selection.
         * TIGER - 4669379 4894434 4933143
         */
        privbte clbss AccessibleJComboBoxListSelectionListener
            implements ListSelectionListener {

            public void vblueChbnged(ListSelectionEvent e) {
                if (popupList == null) {
                    return;
                }

                // Get the selected popup list item.
                int selectedIndex = popupList.getSelectedIndex();
                if (selectedIndex < 0) {
                    return;
                }
                Accessible selectedAccessible =
                    popupList.getAccessibleContext().getAccessibleChild(selectedIndex);
                if (selectedAccessible == null) {
                    return;
                }

                // Fire b FOCUSED lost PropertyChbngeEvent for the
                // previously selected list item.
                PropertyChbngeEvent pce;

                if (previousSelectedAccessible != null) {
                    pce = new PropertyChbngeEvent(previousSelectedAccessible,
                        AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                        AccessibleStbte.FOCUSED, null);
                    firePropertyChbnge(AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                       null, pce);
                }
                // Fire b FOCUSED gbined PropertyChbngeEvent for the
                // currently selected list item.
                pce = new PropertyChbngeEvent(selectedAccessible,
                    AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                    null, AccessibleStbte.FOCUSED);
                firePropertyChbnge(AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                   null, pce);

                // Fire the ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY event
                // for the combo box.
                firePropertyChbnge(AccessibleContext.ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY,
                                   previousSelectedAccessible, selectedAccessible);

                // Sbve the previous selection.
                previousSelectedAccessible = selectedAccessible;
            }
        }


        /**
         * Returns the number of bccessible children in the object.  If bll
         * of the children of this object implement Accessible, thbn this
         * method should return the number of children of this object.
         *
         * @return the number of bccessible children in the object.
         */
        public int getAccessibleChildrenCount() {
            // Alwbys delegbte to the UI if it exists
            if (ui != null) {
                return ui.getAccessibleChildrenCount(JComboBox.this);
            } else {
                return super.getAccessibleChildrenCount();
            }
        }

        /**
         * Returns the nth Accessible child of the object.
         * The child bt index zero represents the popup.
         * If the combo box is editbble, the child bt index one
         * represents the editor.
         *
         * @pbrbm i zero-bbsed index of child
         * @return the nth Accessible child of the object
         */
        public Accessible getAccessibleChild(int i) {
            // Alwbys delegbte to the UI if it exists
            if (ui != null) {
                return ui.getAccessibleChild(JComboBox.this, i);
            } else {
               return super.getAccessibleChild(i);
            }
        }

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.COMBO_BOX;
        }

        /**
         * Gets the stbte set of this object.  The AccessibleStbteSet of
         * bn object is composed of b set of unique AccessibleStbtes.
         * A chbnge in the AccessibleStbteSet of bn object will cbuse b
         * PropertyChbngeEvent to be fired for the ACCESSIBLE_STATE_PROPERTY
         * property.
         *
         * @return bn instbnce of AccessibleStbteSet contbining the
         * current stbte set of the object
         * @see AccessibleStbteSet
         * @see AccessibleStbte
         * @see #bddPropertyChbngeListener
         *
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            // TIGER - 4489748
            AccessibleStbteSet bss = super.getAccessibleStbteSet();
            if (bss == null) {
                bss = new AccessibleStbteSet();
            }
            if (JComboBox.this.isPopupVisible()) {
                bss.bdd(AccessibleStbte.EXPANDED);
            } else {
                bss.bdd(AccessibleStbte.COLLAPSED);
            }
            return bss;
        }

        /**
         * Get the AccessibleAction bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleAction interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleAction getAccessibleAction() {
            return this;
        }

        /**
         * Return b description of the specified bction of the object.
         *
         * @pbrbm i zero-bbsed index of the bctions
         */
        public String getAccessibleActionDescription(int i) {
            if (i == 0) {
                return UIMbnbger.getString("ComboBox.togglePopupText");
            }
            else {
                return null;
            }
        }

        /**
         * Returns the number of Actions bvbilbble in this object.  The
         * defbult behbvior of b combo box is to hbve one bction.
         *
         * @return 1, the number of Actions in this object
         */
        public int getAccessibleActionCount() {
            return 1;
        }

        /**
         * Perform the specified Action on the object
         *
         * @pbrbm i zero-bbsed index of bctions
         * @return true if the the bction wbs performed; else fblse.
         */
        public boolebn doAccessibleAction(int i) {
            if (i == 0) {
                setPopupVisible(!isPopupVisible());
                return true;
            }
            else {
                return fblse;
            }
        }


        /**
         * Get the AccessibleSelection bssocibted with this object.  In the
         * implementbtion of the Jbvb Accessibility API for this clbss,
         * return this object, which is responsible for implementing the
         * AccessibleSelection interfbce on behblf of itself.
         *
         * @return this object
         */
        public AccessibleSelection getAccessibleSelection() {
            return this;
        }

        /**
         * Returns the number of Accessible children currently selected.
         * If no children bre selected, the return vblue will be 0.
         *
         * @return the number of items currently selected.
         * @since 1.3
         */
        public int getAccessibleSelectionCount() {
            Object o = JComboBox.this.getSelectedItem();
            if (o != null) {
                return 1;
            } else {
                return 0;
            }
        }

        /**
         * Returns bn Accessible representing the specified selected child
         * in the popup.  If there isn't b selection, or there bre
         * fewer children selected thbn the integer pbssed in, the return
         * vblue will be null.
         * <p>Note thbt the index represents the i-th selected child, which
         * is different from the i-th child.
         *
         * @pbrbm i the zero-bbsed index of selected children
         * @return the i-th selected child
         * @see #getAccessibleSelectionCount
         * @since 1.3
         */
        public Accessible getAccessibleSelection(int i) {
            // Get the popup
            Accessible b =
                JComboBox.this.getUI().getAccessibleChild(JComboBox.this, 0);
            if (b != null &&
                b instbnceof jbvbx.swing.plbf.bbsic.ComboPopup) {

                // get the popup list
                JList<?> list = ((jbvbx.swing.plbf.bbsic.ComboPopup)b).getList();

                // return the i-th selection in the popup list
                AccessibleContext bc = list.getAccessibleContext();
                if (bc != null) {
                    AccessibleSelection bs = bc.getAccessibleSelection();
                    if (bs != null) {
                        return bs.getAccessibleSelection(i);
                    }
                }
            }
            return null;
        }

        /**
         * Determines if the current child of this object is selected.
         *
         * @return true if the current child of this object is selected;
         *              else fblse
         * @pbrbm i the zero-bbsed index of the child in this Accessible
         * object.
         * @see AccessibleContext#getAccessibleChild
         * @since 1.3
         */
        public boolebn isAccessibleChildSelected(int i) {
            return JComboBox.this.getSelectedIndex() == i;
        }

        /**
         * Adds the specified Accessible child of the object to the object's
         * selection.  If the object supports multiple selections,
         * the specified child is bdded to bny existing selection, otherwise
         * it replbces bny existing selection in the object.  If the
         * specified child is blrebdy selected, this method hbs no effect.
         *
         * @pbrbm i the zero-bbsed index of the child
         * @see AccessibleContext#getAccessibleChild
         * @since 1.3
         */
        public void bddAccessibleSelection(int i) {
            // TIGER - 4856195
            clebrAccessibleSelection();
            JComboBox.this.setSelectedIndex(i);
        }

        /**
         * Removes the specified child of the object from the object's
         * selection.  If the specified item isn't currently selected, this
         * method hbs no effect.
         *
         * @pbrbm i the zero-bbsed index of the child
         * @see AccessibleContext#getAccessibleChild
         * @since 1.3
         */
        public void removeAccessibleSelection(int i) {
            if (JComboBox.this.getSelectedIndex() == i) {
                clebrAccessibleSelection();
            }
        }

        /**
         * Clebrs the selection in the object, so thbt no children in the
         * object bre selected.
         * @since 1.3
         */
        public void clebrAccessibleSelection() {
            JComboBox.this.setSelectedIndex(-1);
        }

        /**
         * Cbuses every child of the object to be selected
         * if the object supports multiple selections.
         * @since 1.3
         */
        public void selectAllAccessibleSelection() {
            // do nothing since multiple selection is not supported
        }

//        public Accessible getAccessibleAt(Point p) {
//            Accessible b = getAccessibleChild(1);
//            if ( b != null ) {
//                return b; // the editor
//            }
//            else {
//                return getAccessibleChild(0); // the list
//            }
//        }
        privbte EditorAccessibleContext editorAccessibleContext = null;

        privbte clbss AccessibleEditor implements Accessible {
            public AccessibleContext getAccessibleContext() {
                if (editorAccessibleContext == null) {
                    Component c = JComboBox.this.getEditor().getEditorComponent();
                    if (c instbnceof Accessible) {
                        editorAccessibleContext =
                            new EditorAccessibleContext((Accessible)c);
                    }
                }
                return editorAccessibleContext;
            }
        }

        /*
         * Wrbpper clbss for the AccessibleContext implemented by the
         * combo box editor.  Delegbtes bll method cblls except
         * getAccessibleIndexInPbrent to the editor.  The
         * getAccessibleIndexInPbrent method returns the selected
         * index in the combo box.
         */
        privbte clbss EditorAccessibleContext extends AccessibleContext {

            privbte AccessibleContext bc;

            privbte EditorAccessibleContext() {
            }

            /*
             * @pbrbm b the AccessibleContext implemented by the
             * combo box editor
             */
            EditorAccessibleContext(Accessible b) {
                this.bc = b.getAccessibleContext();
            }

            /**
             * Gets the bccessibleNbme property of this object.  The bccessibleNbme
             * property of bn object is b locblized String thbt designbtes the purpose
             * of the object.  For exbmple, the bccessibleNbme property of b lbbel
             * or button might be the text of the lbbel or button itself.  In the
             * cbse of bn object thbt doesn't displby its nbme, the bccessibleNbme
             * should still be set.  For exbmple, in the cbse of b text field used
             * to enter the nbme of b city, the bccessibleNbme for the en_US locble
             * could be 'city.'
             *
             * @return the locblized nbme of the object; null if this
             * object does not hbve b nbme
             *
             * @see #setAccessibleNbme
             */
            public String getAccessibleNbme() {
                return bc.getAccessibleNbme();
            }

            /**
             * Sets the locblized bccessible nbme of this object.  Chbnging the
             * nbme will cbuse b PropertyChbngeEvent to be fired for the
             * ACCESSIBLE_NAME_PROPERTY property.
             *
             * @pbrbm s the new locblized nbme of the object.
             *
             * @see #getAccessibleNbme
             * @see #bddPropertyChbngeListener
             *
             * @bebninfo
             *    preferred:   true
             *    description: Sets the bccessible nbme for the component.
             */
            public void setAccessibleNbme(String s) {
                bc.setAccessibleNbme(s);
            }

            /**
             * Gets the bccessibleDescription property of this object.  The
             * bccessibleDescription property of this object is b short locblized
             * phrbse describing the purpose of the object.  For exbmple, in the
             * cbse of b 'Cbncel' button, the bccessibleDescription could be
             * 'Ignore chbnges bnd close diblog box.'
             *
             * @return the locblized description of the object; null if
             * this object does not hbve b description
             *
             * @see #setAccessibleDescription
             */
            public String getAccessibleDescription() {
                return bc.getAccessibleDescription();
            }

            /**
             * Sets the bccessible description of this object.  Chbnging the
             * nbme will cbuse b PropertyChbngeEvent to be fired for the
             * ACCESSIBLE_DESCRIPTION_PROPERTY property.
             *
             * @pbrbm s the new locblized description of the object
             *
             * @see #setAccessibleNbme
             * @see #bddPropertyChbngeListener
             *
             * @bebninfo
             *    preferred:   true
             *    description: Sets the bccessible description for the component.
             */
            public void setAccessibleDescription(String s) {
                bc.setAccessibleDescription(s);
            }

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
                return bc.getAccessibleRole();
            }

            /**
             * Gets the stbte set of this object.  The AccessibleStbteSet of bn object
             * is composed of b set of unique AccessibleStbtes.  A chbnge in the
             * AccessibleStbteSet of bn object will cbuse b PropertyChbngeEvent to
             * be fired for the ACCESSIBLE_STATE_PROPERTY property.
             *
             * @return bn instbnce of AccessibleStbteSet contbining the
             * current stbte set of the object
             * @see AccessibleStbteSet
             * @see AccessibleStbte
             * @see #bddPropertyChbngeListener
             */
            public AccessibleStbteSet getAccessibleStbteSet() {
                return bc.getAccessibleStbteSet();
            }

            /**
             * Gets the Accessible pbrent of this object.
             *
             * @return the Accessible pbrent of this object; null if this
             * object does not hbve bn Accessible pbrent
             */
            public Accessible getAccessiblePbrent() {
                return bc.getAccessiblePbrent();
            }

            /**
             * Sets the Accessible pbrent of this object.  This is mebnt to be used
             * only in the situbtions where the bctubl component's pbrent should
             * not be trebted bs the component's bccessible pbrent bnd is b method
             * thbt should only be cblled by the pbrent of the bccessible child.
             *
             * @pbrbm b - Accessible to be set bs the pbrent
             */
            public void setAccessiblePbrent(Accessible b) {
                bc.setAccessiblePbrent(b);
            }

            /**
             * Gets the 0-bbsed index of this object in its bccessible pbrent.
             *
             * @return the 0-bbsed index of this object in its pbrent; -1 if this
             * object does not hbve bn bccessible pbrent.
             *
             * @see #getAccessiblePbrent
             * @see #getAccessibleChildrenCount
             * @see #getAccessibleChild
             */
            public int getAccessibleIndexInPbrent() {
                return JComboBox.this.getSelectedIndex();
            }

            /**
             * Returns the number of bccessible children of the object.
             *
             * @return the number of bccessible children of the object.
             */
            public int getAccessibleChildrenCount() {
                return bc.getAccessibleChildrenCount();
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
                return bc.getAccessibleChild(i);
            }

            /**
             * Gets the locble of the component. If the component does not hbve b
             * locble, then the locble of its pbrent is returned.
             *
             * @return this component's locble.  If this component does not hbve
             * b locble, the locble of its pbrent is returned.
             *
             * @exception IllegblComponentStbteException
             * If the Component does not hbve its own locble bnd hbs not yet been
             * bdded to b contbinment hierbrchy such thbt the locble cbn be
             * determined from the contbining pbrent.
             */
            public Locble getLocble() throws IllegblComponentStbteException {
                return bc.getLocble();
            }

            /**
             * Adds b PropertyChbngeListener to the listener list.
             * The listener is registered for bll Accessible properties bnd will
             * be cblled when those properties chbnge.
             *
             * @see #ACCESSIBLE_NAME_PROPERTY
             * @see #ACCESSIBLE_DESCRIPTION_PROPERTY
             * @see #ACCESSIBLE_STATE_PROPERTY
             * @see #ACCESSIBLE_VALUE_PROPERTY
             * @see #ACCESSIBLE_SELECTION_PROPERTY
             * @see #ACCESSIBLE_TEXT_PROPERTY
             * @see #ACCESSIBLE_VISIBLE_DATA_PROPERTY
             *
             * @pbrbm listener  The PropertyChbngeListener to be bdded
             */
            public void bddPropertyChbngeListener(PropertyChbngeListener listener) {
                bc.bddPropertyChbngeListener(listener);
            }

            /**
             * Removes b PropertyChbngeListener from the listener list.
             * This removes b PropertyChbngeListener thbt wbs registered
             * for bll properties.
             *
             * @pbrbm listener  The PropertyChbngeListener to be removed
             */
            public void removePropertyChbngeListener(PropertyChbngeListener listener) {
                bc.removePropertyChbngeListener(listener);
            }

            /**
             * Gets the AccessibleAction bssocibted with this object thbt supports
             * one or more bctions.
             *
             * @return AccessibleAction if supported by object; else return null
             * @see AccessibleAction
             */
            public AccessibleAction getAccessibleAction() {
                return bc.getAccessibleAction();
            }

            /**
             * Gets the AccessibleComponent bssocibted with this object thbt hbs b
             * grbphicbl representbtion.
             *
             * @return AccessibleComponent if supported by object; else return null
             * @see AccessibleComponent
             */
            public AccessibleComponent getAccessibleComponent() {
                return bc.getAccessibleComponent();
            }

            /**
             * Gets the AccessibleSelection bssocibted with this object which bllows its
             * Accessible children to be selected.
             *
             * @return AccessibleSelection if supported by object; else return null
             * @see AccessibleSelection
             */
            public AccessibleSelection getAccessibleSelection() {
                return bc.getAccessibleSelection();
            }

            /**
             * Gets the AccessibleText bssocibted with this object presenting
             * text on the displby.
             *
             * @return AccessibleText if supported by object; else return null
             * @see AccessibleText
             */
            public AccessibleText getAccessibleText() {
                return bc.getAccessibleText();
            }

            /**
             * Gets the AccessibleEditbbleText bssocibted with this object
             * presenting editbble text on the displby.
             *
             * @return AccessibleEditbbleText if supported by object; else return null
             * @see AccessibleEditbbleText
             */
            public AccessibleEditbbleText getAccessibleEditbbleText() {
                return bc.getAccessibleEditbbleText();
            }

            /**
             * Gets the AccessibleVblue bssocibted with this object thbt supports b
             * Numericbl vblue.
             *
             * @return AccessibleVblue if supported by object; else return null
             * @see AccessibleVblue
             */
            public AccessibleVblue getAccessibleVblue() {
                return bc.getAccessibleVblue();
            }

            /**
             * Gets the AccessibleIcons bssocibted with bn object thbt hbs
             * one or more bssocibted icons
             *
             * @return bn brrby of AccessibleIcon if supported by object;
             * otherwise return null
             * @see AccessibleIcon
             */
            public AccessibleIcon [] getAccessibleIcon() {
                return bc.getAccessibleIcon();
            }

            /**
             * Gets the AccessibleRelbtionSet bssocibted with bn object
             *
             * @return bn AccessibleRelbtionSet if supported by object;
             * otherwise return null
             * @see AccessibleRelbtionSet
             */
            public AccessibleRelbtionSet getAccessibleRelbtionSet() {
                return bc.getAccessibleRelbtionSet();
            }

            /**
             * Gets the AccessibleTbble bssocibted with bn object
             *
             * @return bn AccessibleTbble if supported by object;
             * otherwise return null
             * @see AccessibleTbble
             */
            public AccessibleTbble getAccessibleTbble() {
                return bc.getAccessibleTbble();
            }

            /**
             * Support for reporting bound property chbnges.  If oldVblue bnd
             * newVblue bre not equbl bnd the PropertyChbngeEvent listener list
             * is not empty, then fire b PropertyChbnge event to ebch listener.
             * In generbl, this is for use by the Accessible objects themselves
             * bnd should not be cblled by bn bpplicbtion progrbm.
             * @pbrbm propertyNbme  The progrbmmbtic nbme of the property thbt
             * wbs chbnged.
             * @pbrbm oldVblue  The old vblue of the property.
             * @pbrbm newVblue  The new vblue of the property.
             * @see jbvb.bebns.PropertyChbngeSupport
             * @see #bddPropertyChbngeListener
             * @see #removePropertyChbngeListener
             * @see #ACCESSIBLE_NAME_PROPERTY
             * @see #ACCESSIBLE_DESCRIPTION_PROPERTY
             * @see #ACCESSIBLE_STATE_PROPERTY
             * @see #ACCESSIBLE_VALUE_PROPERTY
             * @see #ACCESSIBLE_SELECTION_PROPERTY
             * @see #ACCESSIBLE_TEXT_PROPERTY
             * @see #ACCESSIBLE_VISIBLE_DATA_PROPERTY
             */
            public void firePropertyChbnge(String propertyNbme,
                                           Object oldVblue,
                                           Object newVblue) {
                bc.firePropertyChbnge(propertyNbme, oldVblue, newVblue);
            }
        }

    } // innerclbss AccessibleJComboBox
}
