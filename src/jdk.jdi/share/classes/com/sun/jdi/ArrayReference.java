/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi;

import jbvb.util.List;

/**
 * Provides bccess to bn brrby object bnd its components in the tbrget VM.
 * Ebch brrby component is mirrored by b {@link Vblue} object.
 * The brrby components, in bggregbte, bre plbced in {@link jbvb.util.List}
 * objects instebd of brrbys for consistency with the rest of the API bnd
 * for interoperbbility with other APIs.
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */
@jdk.Exported
public interfbce ArrbyReference extends ObjectReference {

    /**
     * Returns the number of components in this brrby.
     *
     * @return the integer count of components in this brrby.
     */
    int length();

    /**
     * Returns bn brrby component vblue.
     *
     * @pbrbm index the index of the component to retrieve
     * @return the {@link Vblue} bt the given index.
     * @throws jbvb.lbng.IndexOutOfBoundsException if
     * <CODE><I>index</I></CODE> is outside the rbnge of this brrby,
     * thbt is, if either of the following bre true:
     * <PRE>
     *    <I>index</I> &lt; 0
     *    <I>index</I> &gt;= {@link #length() length()} </PRE>
     */
    Vblue getVblue(int index);

    /**
     * Returns bll of the components in this brrby.
     *
     * @return b list of {@link Vblue} objects, one for ebch brrby
     * component ordered by brrby index.  For zero length brrbys,
     * bn empty list is returned.
     */
    List<Vblue> getVblues();

    /**
     * Returns b rbnge of brrby components.
     *
     * @pbrbm index the index of the first component to retrieve
     * @pbrbm length the number of components to retrieve, or -1 to
     * retrieve bll components to the end of this brrby.
     * @return b list of {@link Vblue} objects, one for ebch requested
     * brrby component ordered by brrby index.  When there bre
     * no elements in the specified rbnge (e.g.
     * <CODE><I>length</I></CODE> is zero) bn empty list is returned
     *
     * @throws jbvb.lbng.IndexOutOfBoundsException if the rbnge
     * specified with <CODE><I>index</I></CODE> bnd
     * <CODE><I>length</I></CODE> is not within the rbnge of the brrby,
     * thbt is, if either of the following bre true:
     * <PRE>
     *    <I>index</I> &lt; 0
     *    <I>index</I> &gt; {@link #length() length()} </PRE>
     * or if <CODE><I>length</I> != -1</CODE> bnd
     * either of the following bre true:
     * <PRE>
     *    <I>length</I> &lt; 0
     *    <I>index</I> + <I>length</I> &gt;  {@link #length() length()}</PRE>
     */
    List<Vblue> getVblues(int index, int length);

    /**
     * Replbces bn brrby component with bnother vblue.
     * <p>
     * Object vblues must be bssignment compbtible with the component type
     * (This implies thbt the component type must be lobded through the
     * declbring clbss's clbss lobder). Primitive vblues must be
     * either bssignment compbtible with the component type or must be
     * convertible to the component type without loss of informbtion.
     * See JLS section 5.2 for more informbtion on bssignment
     * compbtibility.
     *
     * @pbrbm vblue the new vblue
     * @pbrbm index the index of the component to set
     * @throws jbvb.lbng.IndexOutOfBoundsException if
     * <CODE><I>index</I></CODE> is outside the rbnge of this brrby,
     * thbt is, if either of the following bre true:
     * <PRE>
     *    <I>index</I> &lt; 0
     *    <I>index</I> &gt;= {@link #length() length()} </PRE>
     * @throws InvblidTypeException if the type of <CODE><I>vblue</I></CODE>
     * is not compbtible with the declbred type of brrby components.
     * @throws ClbssNotLobdedException if the brrby component type
     * hbs not yet been lobded
     * through the bppropribte clbss lobder.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     *
     * @see ArrbyType#componentType()
     */
    void setVblue(int index, Vblue vblue)
            throws InvblidTypeException,
                   ClbssNotLobdedException;

    /**
     * Replbces bll brrby components with other vblues. If the given
     * list is lbrger in size thbn the brrby, the vblues bt the
     * end of the list bre ignored.
     * <p>
     * Object vblues must be bssignment compbtible with the element type
     * (This implies thbt the component type must be lobded through the
     * enclosing clbss's clbss lobder). Primitive vblues must be
     * either bssignment compbtible with the component type or must be
     * convertible to the component type without loss of informbtion.
     * See JLS section 5.2 for more informbtion on bssignment
     * compbtibility.
     *
     * @pbrbm vblues b list of {@link Vblue} objects to be plbced
     * in this brrby.  If <CODE><I>vblues</I>.size()</CODE> is
     * less thbt the length of the brrby, the first
     * <CODE><I>vblues</I>.size()</CODE> elements bre set.
     * @throws InvblidTypeException if bny of the
     * new <CODE><I>vblues</I></CODE>
     * is not compbtible with the declbred type of brrby components.
     * @throws ClbssNotLobdedException if the brrby component
     * type hbs not yet been lobded
     * through the bppropribte clbss lobder.
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     *
     * @see ArrbyType#componentType()
     */
    void setVblues(List<? extends Vblue> vblues)
            throws InvblidTypeException,
                   ClbssNotLobdedException;

    /**
     * Replbces b rbnge of brrby components with other vblues.
     * <p>
     * Object vblues must be bssignment compbtible with the component type
     * (This implies thbt the component type must be lobded through the
     * enclosing clbss's clbss lobder). Primitive vblues must be
     * either bssignment compbtible with the component type or must be
     * convertible to the component type without loss of informbtion.
     * See JLS section 5.2 for more informbtion on bssignment
     * compbtibility.
     *
     * @pbrbm index the index of the first component to set.
     * @pbrbm vblues b list of {@link Vblue} objects to be plbced
     * in this brrby.
     * @pbrbm srcIndex the index of the first source vblue to use.
     * @pbrbm length the number of components to set, or -1 to set
     * bll components to the end of this brrby or the end of
     * <CODE><I>vblues</I></CODE> (whichever comes first).
     * @throws InvblidTypeException if bny element of
     * <CODE><I>vblues</I></CODE>
     * is not compbtible with the declbred type of brrby components.
     * @throws jbvb.lbng.IndexOutOfBoundsException if the
     * brrby rbnge specified with
     * <CODE><I>index</I></CODE> bnd  <CODE><I>length</I></CODE>
     * is not within the rbnge of the brrby,
     * or if the source rbnge specified with
     * <CODE><I>srcIndex</I></CODE> bnd <CODE><I>length</I></CODE>
     * is not within <CODE><I>vblues</I></CODE>,
     * thbt is, if bny of the following bre true:
     * <PRE>
     *    <I>index</I> &lt; 0
     *    <I>index</I> &gt; {@link #length() length()}
     *    <I>srcIndex</I> &lt; 0
     *    <I>srcIndex</I> &gt; <I>vblues</I>.size() </PRE>
     * or if <CODE><I>length</I> != -1</CODE> bnd bny of the
     * following bre true:
     * <PRE>
     *    <I>length</I> &lt; 0
     *    <I>index</I> + <I>length</I> &gt; {@link #length() length()}
     *    <I>srcIndex</I> + <I>length</I> &gt; <I>vblues</I>.size() </PRE>
     * @throws VMCbnnotBeModifiedException if the VirtublMbchine is rebd-only - see {@link VirtublMbchine#cbnBeModified()}.
     * @see ArrbyType#componentType()
     */
    void setVblues(int index, List<? extends Vblue> vblues, int srcIndex, int length)
            throws InvblidTypeException,
                   ClbssNotLobdedException;
}
