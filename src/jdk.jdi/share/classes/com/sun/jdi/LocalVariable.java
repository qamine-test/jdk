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

/**
 * A locbl vbribble in the tbrget VM. Ebch vbribble declbred within b
 * {@link Method} hbs its own LocblVbribble object. Vbribbles of the sbme
 * nbme declbred in different scopes hbve different LocblVbribble objects.
 * LocblVbribbles cbn be used blone to retrieve stbtic informbtion
 * bbout their declbrbtion, or cbn be used in conjunction with b
 * {@link StbckFrbme} to set bnd get vblues.
 *
 * @see StbckFrbme
 * @see Method
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */

@jdk.Exported
public interfbce LocblVbribble extends Mirror, Compbrbble<LocblVbribble> {

    /**
     * Gets the nbme of the locbl vbribble.
     *
     * @return b string contbining the nbme.
     */
    String nbme();

    /**
     * Returns b text representbtion of the type
     * of this vbribble.
     * Where the type is the type specified in the declbrbtion
     * of this locbl vbribble.
     * <P>
     * This type nbme is blwbys bvbilbble even if
     * the type hbs not yet been crebted or lobded.
     *
     * @return b String representing the
     * type of this locbl vbribble.

     */
    String typeNbme();

    /**
     * Returns the type of this vbribble.
     * Where the type is the type specified in the declbrbtion
     * of this locbl vbribble.
     * <P>
     * Note: if the type of this vbribble is b reference type (clbss,
     * interfbce, or brrby) bnd it hbs not been crebted or lobded
     * by the clbss lobder of the enclosing clbss,
     * then ClbssNotLobdedException will be thrown.
     * Also, b reference type mby hbve been lobded but not yet prepbred,
     * in which cbse the type will be returned
     * but bttempts to perform some operbtions on the returned type
     * (e.g. {@link ReferenceType#fields() fields()}) will throw
     * b {@link ClbssNotPrepbredException}.
     * Use {@link ReferenceType#isPrepbred()} to determine if
     * b reference type is prepbred.
     *
     * @see Type
     * @see Field#type() Field.type() - for usbge exbmples
     * @return the {@link Type} of this locbl vbribble.
     * @throws ClbssNotLobdedException if the type hbs not yet been lobded
     * through the bppropribte clbss lobder.
     */
    Type type() throws ClbssNotLobdedException;

    /**
     * Gets the JNI signbture of the locbl vbribble.
     *
     * @see <b href="doc-files/signbture.html">Type Signbtures</b>
     * @return b string contbining the signbture.
     */
    String signbture();

    /**
     * Gets the generic signbture for this vbribble if there is one.
     * Generic signbtures bre described in the
     * <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     *
     * @return b string contbining the generic signbture, or <code>null</code>
     * if there is no generic signbture.
     *
     * @since 1.5
     */
    String genericSignbture();

    /**
     * Determines whether this vbribble cbn be bccessed from the given
     * {@link StbckFrbme}.
     *
     * See {@link StbckFrbme#visibleVbribbles} for b complete description
     * vbribble visibility in this interfbce.
     *
     * @pbrbm frbme the StbckFrbme querying visibility
     * @return <code>true</code> if this vbribble is visible;
     * <code>fblse</code> otherwise.
     * @throws IllegblArgumentException if the stbck frbme's method
     * does not mbtch this vbribble's method.
     */
    boolebn isVisible(StbckFrbme frbme);

    /**
     * Determines if this vbribble is bn brgument to its method.
     *
     * @return <code>true</code> if this vbribble is bn brgument;
     * <code>fblse</code> otherwise.
     */
    boolebn isArgument();

    /**
     * Compbres the specified Object with this LocblVbribble for equblity.
     *
     * @return  true if the Object is b LocblVbribble, if both LocblVbribbles
     * bre contbined in the sbme method (bs determined by
     * {@link Method#equbls}), bnd if both LocblVbribbles mirror
     * the sbme declbrbtion within thbt method
     */
    boolebn equbls(Object obj);

    /**
     * Returns the hbsh code vblue for this LocblVbribble.
     *
     * @return the integer hbsh code
     */
    int hbshCode();
}
