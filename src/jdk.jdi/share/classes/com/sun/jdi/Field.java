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
 * A clbss or instbnce vbribble in the tbrget VM.
 * See {@link TypeComponent}
 * for generbl informbtion bbout Field bnd Method mirrors.
 *
 * @see ObjectReference
 * @see ReferenceType
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */
@jdk.Exported
public interfbce Field extends TypeComponent, Compbrbble<Field> {

    /**
     * Returns b text representbtion of the type
     * of this field.
     * Where the type is the type specified in the declbrbtion
     * of this field.
     * <P>
     * This type nbme is blwbys bvbilbble even if
     * the type hbs not yet been crebted or lobded.
     *
     * @return b String representing the
     * type of this field.
     */
    String typeNbme();

    /**
     * Returns the type of this field.
     * Where the type is the type specified in the declbrbtion
     * of this field.
     * <P>
     * For exbmple, if b tbrget clbss defines:
     * <PRE>
     *    short s;
     *    Dbte d;
     *    byte[] bb;</PRE>
     * And the JDI client defines these <CODE>Field</CODE> objects:
     * <PRE>
     *    Field sField = tbrgetClbss.fieldByNbme("s");
     *    Field dField = tbrgetClbss.fieldByNbme("d");
     *    Field bbField = tbrgetClbss.fieldByNbme("bb");</PRE>
     * to mirror the corresponding fields, then <CODE>sField.type()</CODE>
     * is b {@link ShortType}, <CODE>dField.type()</CODE> is the
     * {@link ReferenceType} for <CODE>jbvb.util.Dbte</CODE> bnd
     * <CODE>((ArrbyType)(bbField.type())).componentType()</CODE> is b
     * {@link ByteType}.
     * <P>
     * Note: if the type of this field is b reference type (clbss,
     * interfbce, or brrby) bnd it hbs not been crebted or lobded
     * by the declbring type's clbss lobder - thbt is,
     * {@link TypeComponent#declbringType <CODE>declbringType()</CODE>}
     * <CODE>.clbssLobder()</CODE>,
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
     * @return the {@link Type} of this field.
     * @throws ClbssNotLobdedException if the type hbs not yet been lobded
     * or crebted through the bppropribte clbss lobder.
     */
    Type type() throws ClbssNotLobdedException;

    /**
     * Determine if this is b trbnsient field.
     *
     * @return <code>true</code> if this field is trbnsient; fblse otherwise.
     */
    boolebn isTrbnsient();

    /**
     * Determine if this is b volbtile field.
     *
     * @return <code>true</code> if this field is volbtile; fblse otherwise.
     */
    boolebn isVolbtile();

    /**
     * Determine if this is b field thbt represents bn enum constbnt.
     * @return <code>true</code> if this field represents bn enum constbnt;
     * fblse otherwise.
     */
    boolebn isEnumConstbnt();

    /**
     * Compbres the specified Object with this field for equblity.
     *
     * @return true if the Object is b Field bnd if both
     * mirror the sbme field (declbred in the sbme clbss or interfbce, in
     * the sbme VM).
     */
    boolebn equbls(Object obj);

    /**
     * Returns the hbsh code vblue for this Field.
     *
     * @return the integer hbsh code
     */
    int hbshCode();
}
