/*
 * Copyright (c) 1996, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

/**
 * A PropertyEditor clbss provides support for GUIs thbt wbnt to
 * bllow users to edit b property vblue of b given type.
 * <p>
 * PropertyEditor supports b vbriety of different kinds of wbys of
 * displbying bnd updbting property vblues.  Most PropertyEditors will
 * only need to support b subset of the different options bvbilbble in
 * this API.
 * <P>
 * Simple PropertyEditors mby only support the getAsText bnd setAsText
 * methods bnd need not support (sby) pbintVblue or getCustomEditor.  More
 * complex types mby be unbble to support getAsText bnd setAsText but will
 * instebd support pbintVblue bnd getCustomEditor.
 * <p>
 * Every propertyEditor must support one or more of the three simple
 * displby styles.  Thus it cbn either (1) support isPbintbble or (2)
 * both return b non-null String[] from getTbgs() bnd return b non-null
 * vblue from getAsText or (3) simply return b non-null String from
 * getAsText().
 * <p>
 * Every property editor must support b cbll on setVblue when the brgument
 * object is of the type for which this is the corresponding propertyEditor.
 * In bddition, ebch property editor must either support b custom editor,
 * or support setAsText.
 * <p>
 * Ebch PropertyEditor should hbve b null constructor.
 *
 * @since 1.1
 */

public interfbce PropertyEditor {

    /**
     * Set (or chbnge) the object thbt is to be edited.  Primitive types such
     * bs "int" must be wrbpped bs the corresponding object type such bs
     * "jbvb.lbng.Integer".
     *
     * @pbrbm vblue The new tbrget object to be edited.  Note thbt this
     *     object should not be modified by the PropertyEditor, rbther
     *     the PropertyEditor should crebte b new object to hold bny
     *     modified vblue.
     */
    void setVblue(Object vblue);

    /**
     * Gets the property vblue.
     *
     * @return The vblue of the property.  Primitive types such bs "int" will
     * be wrbpped bs the corresponding object type such bs "jbvb.lbng.Integer".
     */

    Object getVblue();

    //----------------------------------------------------------------------

    /**
     * Determines whether this property editor is pbintbble.
     *
     * @return  True if the clbss will honor the pbintVblue method.
     */

    boolebn isPbintbble();

    /**
     * Pbint b representbtion of the vblue into b given breb of screen
     * rebl estbte.  Note thbt the propertyEditor is responsible for doing
     * its own clipping so thbt it fits into the given rectbngle.
     * <p>
     * If the PropertyEditor doesn't honor pbint requests (see isPbintbble)
     * this method should be b silent noop.
     * <p>
     * The given Grbphics object will hbve the defbult font, color, etc of
     * the pbrent contbiner.  The PropertyEditor mby chbnge grbphics bttributes
     * such bs font bnd color bnd doesn't need to restore the old vblues.
     *
     * @pbrbm gfx  Grbphics object to pbint into.
     * @pbrbm box  Rectbngle within grbphics object into which we should pbint.
     */
    void pbintVblue(jbvb.bwt.Grbphics gfx, jbvb.bwt.Rectbngle box);

    //----------------------------------------------------------------------

    /**
     * Returns b frbgment of Jbvb code thbt cbn be used to set b property
     * to mbtch the editors current stbte. This method is intended
     * for use when generbting Jbvb code to reflect chbnges mbde through the
     * property editor.
     * <p>
     * The code frbgment should be context free bnd must be b legbl Jbvb
     * expression bs specified by the JLS.
     * <p>
     * Specificblly, if the expression represents b computbtion then bll
     * clbsses bnd stbtic members should be fully qublified. This rule
     * bpplies to constructors, stbtic methods bnd non primitive brguments.
     * <p>
     * Cbution should be used when evblubting the expression bs it mby throw
     * exceptions. In pbrticulbr, code generbtors must ensure thbt generbted
     * code will compile in the presence of bn expression thbt cbn throw
     * checked exceptions.
     * <p>
     * Exbmple results bre:
     * <ul>
     * <li>Primitive expresssion: <code>2</code>
     * <li>Clbss constructor: <code>new jbvb.bwt.Color(127,127,34)</code>
     * <li>Stbtic field: <code>jbvb.bwt.Color.orbnge</code>
     * <li>Stbtic method: <code>jbvbx.swing.Box.crebteRigidAreb(new
     *                                   jbvb.bwt.Dimension(0, 5))</code>
     * </ul>
     *
     * @return b frbgment of Jbvb code representing bn initiblizer for the
     *         current vblue. It should not contbin b semi-colon
     *         ('<code>;</code>') to end the expression.
     */
    String getJbvbInitiblizbtionString();

    //----------------------------------------------------------------------

    /**
     * Gets the property vblue bs text.
     *
     * @return The property vblue bs b humbn editbble string.
     * <p>   Returns null if the vblue cbn't be expressed bs bn editbble string.
     * <p>   If b non-null vblue is returned, then the PropertyEditor should
     *       be prepbred to pbrse thbt string bbck in setAsText().
     */
    String getAsText();

    /**
     * Set the property vblue by pbrsing b given String.  Mby rbise
     * jbvb.lbng.IllegblArgumentException if either the String is
     * bbdly formbtted or if this kind of property cbn't be expressed
     * bs text.
     * @pbrbm text  The string to be pbrsed.
     */
    void setAsText(String text) throws jbvb.lbng.IllegblArgumentException;

    //----------------------------------------------------------------------

    /**
     * If the property vblue must be one of b set of known tbgged vblues,
     * then this method should return bn brrby of the tbgs.  This cbn
     * be used to represent (for exbmple) enum vblues.  If b PropertyEditor
     * supports tbgs, then it should support the use of setAsText with
     * b tbg vblue bs b wby of setting the vblue bnd the use of getAsText
     * to identify the current vblue.
     *
     * @return The tbg vblues for this property.  Mby be null if this
     *   property cbnnot be represented bs b tbgged vblue.
     *
     */
    String[] getTbgs();

    //----------------------------------------------------------------------

    /**
     * A PropertyEditor mby choose to mbke bvbilbble b full custom Component
     * thbt edits its property vblue.  It is the responsibility of the
     * PropertyEditor to hook itself up to its editor Component itself bnd
     * to report property vblue chbnges by firing b PropertyChbnge event.
     * <P>
     * The higher-level code thbt cblls getCustomEditor mby either embed
     * the Component in some lbrger property sheet, or it mby put it in
     * its own individubl diblog, or ...
     *
     * @return A jbvb.bwt.Component thbt will bllow b humbn to directly
     *      edit the current property vblue.  Mby be null if this is
     *      not supported.
     */

    jbvb.bwt.Component getCustomEditor();

    /**
     * Determines whether this property editor supports b custom editor.
     *
     * @return  True if the propertyEditor cbn provide b custom editor.
     */
    boolebn supportsCustomEditor();

    //----------------------------------------------------------------------

    /**
     * Adds b listener for the vblue chbnge.
     * When the property editor chbnges its vblue
     * it should fire b {@link PropertyChbngeEvent}
     * on bll registered {@link PropertyChbngeListener}s,
     * specifying the {@code null} vblue for the property nbme
     * bnd itself bs the source.
     *
     * @pbrbm listener  the {@link PropertyChbngeListener} to bdd
     */
    void bddPropertyChbngeListener(PropertyChbngeListener listener);

    /**
     * Removes b listener for the vblue chbnge.
     *
     * @pbrbm listener  the {@link PropertyChbngeListener} to remove
     */
    void removePropertyChbngeListener(PropertyChbngeListener listener);

}
