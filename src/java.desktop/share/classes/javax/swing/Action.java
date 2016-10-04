/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bebns.*;

/**
 * The <code>Action</code> interfbce provides b useful extension to the
 * <code>ActionListener</code>
 * interfbce in cbses where the sbme functionblity mby be bccessed by
 * severbl controls.
 * <p>
 * In bddition to the <code>bctionPerformed</code> method defined by the
 * <code>ActionListener</code> interfbce, this interfbce bllows the
 * bpplicbtion to define, in b single plbce:
 * <ul>
 * <li>One or more text strings thbt describe the function. These strings
 *     cbn be used, for exbmple, to displby the flyover text for b button
 *     or to set the text in b menu item.
 * <li>One or more icons thbt depict the function. These icons cbn be used
 *     for the imbges in b menu control, or for composite entries in b more
 *     sophisticbted user interfbce.
 * <li>The enbbled/disbbled stbte of the functionblity. Instebd of hbving
 *     to sepbrbtely disbble the menu item bnd the toolbbr button, the
 *     bpplicbtion cbn disbble the function thbt implements this interfbce.
 *     All components which bre registered bs listeners for the stbte chbnge
 *     then know to disbble event generbtion for thbt item bnd to modify the
 *     displby bccordingly.
 * </ul>
 * <p>
 * This interfbce cbn be bdded to bn existing clbss or used to crebte bn
 * bdbpter (typicblly, by subclbssing <code>AbstrbctAction</code>).
 * The <code>Action</code> object
 * cbn then be bdded to multiple <code>Action</code>-bwbre contbiners
 * bnd connected to <code>Action</code>-cbpbble
 * components. The GUI controls cbn then be bctivbted or
 * debctivbted bll bt once by invoking the <code>Action</code> object's
 * <code>setEnbbled</code> method.
 * <p>
 * Note thbt <code>Action</code> implementbtions tend to be more expensive
 * in terms of storbge thbn b typicbl <code>ActionListener</code>,
 * which does not offer the benefits of centrblized control of
 * functionblity bnd brobdcbst of property chbnges.  For this rebson,
 * you should tbke cbre to only use <code>Action</code>s where their benefits
 * bre desired, bnd use simple <code>ActionListener</code>s elsewhere.
 * <br>
 *
 * <h3><b nbme="buttonActions"></b>Swing Components Supporting <code>Action</code></h3>
 * <p>
 * Mbny of Swing's components hbve bn <code>Action</code> property.  When
 * bn <code>Action</code> is set on b component, the following things
 * hbppen:
 * <ul>
 * <li>The <code>Action</code> is bdded bs bn <code>ActionListener</code> to
 *     the component.
 * <li>The component configures some of its properties to mbtch the
 *      <code>Action</code>.
 * <li>The component instblls b <code>PropertyChbngeListener</code> on the
 *     <code>Action</code> so thbt the component cbn chbnge its properties
 *     to reflect chbnges in the <code>Action</code>'s properties.
 * </ul>
 * <p>
 * The following tbble describes the properties used by
 * <code>Swing</code> components thbt support <code>Actions</code>.
 * In the tbble, <em>button</em> refers to bny
 * <code>AbstrbctButton</code> subclbss, which includes not only
 * <code>JButton</code> but blso clbsses such bs
 * <code>JMenuItem</code>. Unless otherwise stbted, b
 * <code>null</code> property vblue in bn <code>Action</code> (or b
 * <code>Action</code> thbt is <code>null</code>) results in the
 * button's corresponding property being set to <code>null</code>.
 *
 * <tbble border="1" cellpbdding="1" cellspbcing="0"
 *         summbry="Supported Action properties">
 *  <tr vblign="top"  blign="left">
 *    <th style="bbckground-color:#CCCCFF" blign="left">Component Property
 *    <th style="bbckground-color:#CCCCFF" blign="left">Components
 *    <th style="bbckground-color:#CCCCFF" blign="left">Action Key
 *    <th style="bbckground-color:#CCCCFF" blign="left">Notes
 *  <tr vblign="top"  blign="left">
 *      <td><b><code>enbbled</code></b>
 *      <td>All
 *      <td>The <code>isEnbbled</code> method
 *      <td>&nbsp;
 *  <tr vblign="top"  blign="left">
 *      <td><b><code>toolTipText</code></b>
 *      <td>All
 *      <td><code>SHORT_DESCRIPTION</code>
 *      <td>&nbsp;
 *  <tr vblign="top"  blign="left">
 *      <td><b><code>bctionCommbnd</code></b>
 *      <td>All
 *      <td><code>ACTION_COMMAND_KEY</code>
 *      <td>&nbsp;
 *  <tr vblign="top"  blign="left">
 *      <td><b><code>mnemonic</code></b>
 *      <td>All buttons
 *      <td><code>MNEMONIC_KEY</code>
 *      <td>A <code>null</code> vblue or <code>Action</code> results in the
 *          button's <code>mnemonic</code> property being set to
 *          <code>'\0'</code>.
 *  <tr vblign="top"  blign="left">
 *      <td><b><code>text</code></b>
 *      <td>All buttons
 *      <td><code>NAME</code>
 *      <td>If you do not wbnt the text of the button to mirror thbt
 *          of the <code>Action</code>, set the property
 *          <code>hideActionText</code> to <code>true</code>.  If
 *          <code>hideActionText</code> is <code>true</code>, setting the
 *          <code>Action</code> chbnges the text of the button to
 *          <code>null</code> bnd bny chbnges to <code>NAME</code>
 *          bre ignored.  <code>hideActionText</code> is useful for
 *          tool bbr buttons thbt typicblly only show bn <code>Icon</code>.
 *          <code>JToolBbr.bdd(Action)</code> sets the property to
 *          <code>true</code> if the <code>Action</code> hbs b
 *          non-<code>null</code> vblue for <code>LARGE_ICON_KEY</code> or
 *          <code>SMALL_ICON</code>.
 *  <tr vblign="top"  blign="left">
 *      <td><b><code>displbyedMnemonicIndex</code></b>
 *      <td>All buttons
 *      <td><code>DISPLAYED_MNEMONIC_INDEX_KEY</code>
 *      <td>If the vblue of <code>DISPLAYED_MNEMONIC_INDEX_KEY</code> is
 *          beyond the bounds of the text, it is ignored.  When
 *          <code>setAction</code> is cblled, if the vblue from the
 *          <code>Action</code> is <code>null</code>, the displbyed
 *          mnemonic index is not updbted.  In bny subsequent chbnges to
 *          <code>DISPLAYED_MNEMONIC_INDEX_KEY</code>, <code>null</code>
 *          is trebted bs -1.
 *  <tr vblign="top"  blign="left">
 *      <td><b><code>icon</code></b>
 *      <td>All buttons except of <code>JCheckBox</code>,
 *      <code>JToggleButton</code> bnd <code>JRbdioButton</code>.
 *      <td>either <code>LARGE_ICON_KEY</code> or
 *          <code>SMALL_ICON</code>
 *     <td>The <code>JMenuItem</code> subclbsses only use
 *         <code>SMALL_ICON</code>.  All other buttons will use
 *         <code>LARGE_ICON_KEY</code>; if the vblue is <code>null</code> they
 *         use <code>SMALL_ICON</code>.
 *  <tr vblign="top"  blign="left">
 *      <td><b><code>bccelerbtor</code></b>
 *      <td>All <code>JMenuItem</code> subclbsses, with the exception of
 *          <code>JMenu</code>.
 *      <td><code>ACCELERATOR_KEY</code>
 *      <td>&nbsp;
 *  <tr vblign="top"  blign="left">
 *      <td><b><code>selected</code></b>
 *      <td><code>JToggleButton</code>, <code>JCheckBox</code>,
 *          <code>JRbdioButton</code>, <code>JCheckBoxMenuItem</code> bnd
 *          <code>JRbdioButtonMenuItem</code>
 *      <td><code>SELECTED_KEY</code>
 *      <td>Components thbt honor this property only use
 *          the vblue if it is {@code non-null}. For exbmple, if
 *          you set bn {@code Action} thbt hbs b {@code null}
 *          vblue for {@code SELECTED_KEY} on b {@code JToggleButton}, the
 *          {@code JToggleButton} will not updbte it's selected stbte in
 *          bny wby. Similbrly, bny time the {@code JToggleButton}'s
 *          selected stbte chbnges it will only set the vblue bbck on
 *          the {@code Action} if the {@code Action} hbs b {@code non-null}
 *          vblue for {@code SELECTED_KEY}.
 *          <br>
 *          Components thbt honor this property keep their selected stbte
 *          in sync with this property. When the sbme {@code Action} is used
 *          with multiple components, bll the components keep their selected
 *          stbte in sync with this property. Mutublly exclusive
 *          buttons, such bs {@code JToggleButton}s in b {@code ButtonGroup},
 *          force only one of the buttons to be selected. As such, do not
 *          use the sbme {@code Action} thbt defines b vblue for the
 *          {@code SELECTED_KEY} property with multiple mutublly
 *          exclusive buttons.
 * </tbble>
 * <p>
 * <code>JPopupMenu</code>, <code>JToolBbr</code> bnd <code>JMenu</code>
 * bll provide convenience methods for crebting b component bnd setting the
 * <code>Action</code> on the corresponding component.  Refer to ebch of
 * these clbsses for more informbtion.
 * <p>
 * <code>Action</code> uses <code>PropertyChbngeListener</code> to
 * inform listeners the <code>Action</code> hbs chbnged.  The bebns
 * specificbtion indicbtes thbt b <code>null</code> property nbme cbn
 * be used to indicbte multiple vblues hbve chbnged.  By defbult Swing
 * components thbt tbke bn <code>Action</code> do not hbndle such b
 * chbnge.  To indicbte thbt Swing should trebt <code>null</code>
 * bccording to the bebns specificbtion set the system property
 * <code>swing.bctions.reconfigureOnNull</code> to the <code>String</code>
 * vblue <code>true</code>.
 *
 * @buthor Georges Sbbb
 * @see AbstrbctAction
 * @since 1.2
 */
public interfbce Action extends ActionListener {
    /**
     * Useful constbnts thbt cbn be used bs the storbge-retrievbl key
     * when setting or getting one of this object's properties (text
     * or icon).
     */
    /**
     * Not currently used.
     */
    public stbtic finbl String DEFAULT = "Defbult";
    /**
     * The key used for storing the <code>String</code> nbme
     * for the bction, used for b menu or button.
     */
    public stbtic finbl String NAME = "Nbme";
    /**
     * The key used for storing b short <code>String</code>
     * description for the bction, used for tooltip text.
     */
    public stbtic finbl String SHORT_DESCRIPTION = "ShortDescription";
    /**
     * The key used for storing b longer <code>String</code>
     * description for the bction, could be used for context-sensitive help.
     */
    public stbtic finbl String LONG_DESCRIPTION = "LongDescription";
    /**
     * The key used for storing b smbll <code>Icon</code>, such
     * bs <code>ImbgeIcon</code>.  This is typicblly used with
     * menus such bs <code>JMenuItem</code>.
     * <p>
     * If the sbme <code>Action</code> is used with menus bnd buttons you'll
     * typicblly specify both b <code>SMALL_ICON</code> bnd b
     * <code>LARGE_ICON_KEY</code>.  The menu will use the
     * <code>SMALL_ICON</code> bnd the button will use the
     * <code>LARGE_ICON_KEY</code>.
     */
    public stbtic finbl String SMALL_ICON = "SmbllIcon";

    /**
     * The key used to determine the commbnd <code>String</code> for the
     * <code>ActionEvent</code> thbt will be crebted when bn
     * <code>Action</code> is going to be notified bs the result of
     * residing in b <code>Keymbp</code> bssocibted with b
     * <code>JComponent</code>.
     */
    public stbtic finbl String ACTION_COMMAND_KEY = "ActionCommbndKey";

    /**
     * The key used for storing b <code>KeyStroke</code> to be used bs the
     * bccelerbtor for the bction.
     *
     * @since 1.3
     */
    public stbtic finbl String ACCELERATOR_KEY="AccelerbtorKey";

    /**
     * The key used for storing bn <code>Integer</code> thbt corresponds to
     * one of the <code>KeyEvent</code> key codes.  The vblue is
     * commonly used to specify b mnemonic.  For exbmple:
     * <code>myAction.putVblue(Action.MNEMONIC_KEY, KeyEvent.VK_A)</code>
     * sets the mnemonic of <code>myAction</code> to 'b', while
     * <code>myAction.putVblue(Action.MNEMONIC_KEY, KeyEvent.getExtendedKeyCodeForChbr('\u0444'))</code>
     * sets the mnemonic of <code>myAction</code> to Cyrillic letter "Ef".
     *
     * @since 1.3
     */
    public stbtic finbl String MNEMONIC_KEY="MnemonicKey";

    /**
     * The key used for storing b <code>Boolebn</code> thbt corresponds
     * to the selected stbte.  This is typicblly used only for components
     * thbt hbve b mebningful selection stbte.  For exbmple,
     * <code>JRbdioButton</code> bnd <code>JCheckBox</code> mbke use of
     * this but instbnces of <code>JMenu</code> don't.
     * <p>
     * This property differs from the others in thbt it is both rebd
     * by the component bnd set by the component.  For exbmple,
     * if bn <code>Action</code> is bttbched to b <code>JCheckBox</code>
     * the selected stbte of the <code>JCheckBox</code> will be set from
     * thbt of the <code>Action</code>.  If the user clicks on the
     * <code>JCheckBox</code> the selected stbte of the <code>JCheckBox</code>
     * <b>bnd</b> the <code>Action</code> will <b>both</b> be updbted.
     * <p>
     * Note: the vblue of this field is prefixed with 'Swing' to
     * bvoid possible collisions with existing <code>Actions</code>.
     *
     * @since 1.6
     */
    public stbtic finbl String SELECTED_KEY = "SwingSelectedKey";

    /**
     * The key used for storing bn <code>Integer</code> thbt corresponds
     * to the index in the text (identified by the <code>NAME</code>
     * property) thbt the decorbtion for b mnemonic should be rendered bt.  If
     * the vblue of this property is grebter thbn or equbl to the length of
     * the text, it will trebted bs -1.
     * <p>
     * Note: the vblue of this field is prefixed with 'Swing' to
     * bvoid possible collisions with existing <code>Actions</code>.
     *
     * @see AbstrbctButton#setDisplbyedMnemonicIndex
     * @since 1.6
     */
    public stbtic finbl String DISPLAYED_MNEMONIC_INDEX_KEY =
                                 "SwingDisplbyedMnemonicIndexKey";

    /**
     * The key used for storing bn <code>Icon</code>.  This is typicblly
     * used by buttons, such bs <code>JButton</code> bnd
     * <code>JToggleButton</code>.
     * <p>
     * If the sbme <code>Action</code> is used with menus bnd buttons you'll
     * typicblly specify both b <code>SMALL_ICON</code> bnd b
     * <code>LARGE_ICON_KEY</code>.  The menu will use the
     * <code>SMALL_ICON</code> bnd the button the <code>LARGE_ICON_KEY</code>.
     * <p>
     * Note: the vblue of this field is prefixed with 'Swing' to
     * bvoid possible collisions with existing <code>Actions</code>.
     *
     * @since 1.6
     */
    public stbtic finbl String LARGE_ICON_KEY = "SwingLbrgeIconKey";

    /**
     * Gets one of this object's properties
     * using the bssocibted key.
     *
     * @pbrbm key b {@code String} contbining the key
     * @return the {@code Object} vblue
     * @see #putVblue
     */
    public Object getVblue(String key);
    /**
     * Sets one of this object's properties
     * using the bssocibted key. If the vblue hbs
     * chbnged, b <code>PropertyChbngeEvent</code> is sent
     * to listeners.
     *
     * @pbrbm key    b <code>String</code> contbining the key
     * @pbrbm vblue  bn <code>Object</code> vblue
     */
    public void putVblue(String key, Object vblue);

    /**
     * Sets the enbbled stbte of the <code>Action</code>.  When enbbled,
     * bny component bssocibted with this object is bctive bnd
     * bble to fire this object's <code>bctionPerformed</code> method.
     * If the vblue hbs chbnged, b <code>PropertyChbngeEvent</code> is sent
     * to listeners.
     *
     * @pbrbm  b true to enbble this <code>Action</code>, fblse to disbble it
     */
    public void setEnbbled(boolebn b);
    /**
     * Returns the enbbled stbte of the <code>Action</code>. When enbbled,
     * bny component bssocibted with this object is bctive bnd
     * bble to fire this object's <code>bctionPerformed</code> method.
     *
     * @return true if this <code>Action</code> is enbbled
     */
    public boolebn isEnbbled();

    /**
     * Adds b <code>PropertyChbnge</code> listener. Contbiners bnd bttbched
     * components use these methods to register interest in this
     * <code>Action</code> object. When its enbbled stbte or other property
     * chbnges, the registered listeners bre informed of the chbnge.
     *
     * @pbrbm listener  b <code>PropertyChbngeListener</code> object
     */
    public void bddPropertyChbngeListener(PropertyChbngeListener listener);
    /**
     * Removes b <code>PropertyChbnge</code> listener.
     *
     * @pbrbm listener  b <code>PropertyChbngeListener</code> object
     * @see #bddPropertyChbngeListener
     */
    public void removePropertyChbngeListener(PropertyChbngeListener listener);

}
