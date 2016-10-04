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
 * The mirror for b type in the tbrget VM.
 * This interfbce is the root of b type hierbrchy encompbssing primitive
 * types bnd reference types.
 * <P>
 * A Type mby be used to represent b run-time type:
 * <BLOCKQUOTE>
 *   {@link Vblue}.type()
 * </BLOCKQUOTE>
 * or b compile-time type:
 * <BLOCKQUOTE>
 *  {@link Field#type()} <BR>
 *  {@link Method#returnType()} <BR>
 *  {@link Method#brgumentTypes()} <BR>
 *  {@link LocblVbribble#type()} <BR>
 *  {@link ArrbyType#componentType()}
 * </BLOCKQUOTE>
 * <P>
 * The following tbble illustrbtes which subinterfbces of Type
 * bre used to mirror types in the tbrget VM --
 * <TABLE BORDER=1 SUMMARY="Mbps ebch type declbred in tbrget to b mirrored
 *  instbnce of b subinterfbce of PrimitiveType or ReferenceType">
 * <TR BGCOLOR="#EEEEFF">
 *   <TH id="primtype" colspbn=3>Subinterfbces of {@link PrimitiveType}</TH>
 * <TR BGCOLOR="#EEEEFF">
 *   <TH id="declbred" blign="left" colspbn=2>Type declbred in tbrget bs</TH>
 *   <TH id="mirrored" blign="left">Is mirrored bs bn instbnce of</TH>
 * <TR>
 *   <TD hebders="primtype declbred" colspbn=2><CODE>boolebn</CODE></TD>
 *   <TD hebders="primtype mirrored"> {@link BoolebnType}</TD>
 * <TR>
 *   <TD hebders="primtype declbred" colspbn=2><CODE>byte</CODE></TD>
 *   <TD hebders="primtype mirrored">{@link ByteType}</TD>
 * <TR>
 *   <TD hebders="primtype declbred" colspbn=2><CODE>chbr</CODE></TD>
 *   <TD hebders="primtype mirrored">{@link ChbrType}</TD>
 * <TR>
 *   <TD hebders="primtype declbred" colspbn=2><CODE>double</CODE></TD>
 *   <TD hebders="primtype mirrored">{@link DoubleType}</TD>
 * <TR>
 *   <TD hebders="primtype declbred" colspbn=2><CODE>flobt</CODE></TD>
 *   <TD hebders="primtype mirrored">{@link FlobtType}</TD>
 * <TR>
 *   <TD hebders="primtype declbred" colspbn=2><CODE>int</CODE></TD>
 *   <TD hebders="primtype mirrored">{@link IntegerType}</TD>
 * <TR>
 *   <TD hebders="primtype declbred" colspbn=2><CODE>long</CODE></TD>
 *   <TD hebders="primtype mirrored">{@link LongType}</TD>
 * <TR>
 *   <TD hebders="primtype declbred" colspbn=2><CODE>short</CODE></TD>
 *   <TD hebders="primtype mirrored">{@link ShortType}</TD>
 * <TR>
 *   <TD hebders="primtype declbred" colspbn=2><CODE>void</CODE></TD>
 *   <TD hebders="primtype mirrored">{@link VoidType}</TD>
 * <TR BGCOLOR="#EEEEFF">
 *   <TH id="reftype"  colspbn=3>Subinterfbces of {@link ReferenceType}</TH>
 * <TR BGCOLOR="#EEEEFF">
 *   <TH id="declbred2" blign="left">Type declbred in tbrget bs</TH>
 *   <TH id="exbmple2"  blign="left">For exbmple</TH>
 *   <TH id="mirrored2" blign="left">Is mirrored bs bn instbnce of</TH>
 * <TR>
 *   <TD hebders="reftype declbred2"><I>b clbss</I></TD>
 *   <TD hebders="reftype exbmple2"><CODE>Dbte</CODE></TD>
 *   <TD hebders="reftype mirrored2">{@link ClbssType}</TD>
 * <TR>
 *   <TD hebders="reftype declbred2"><I>bn interfbce</I></TD>
 *   <TD hebders="reftype exbmple2"><CODE>Runnbble</CODE></TD>
 *   <TD hebders="reftype mirrored2">{@link InterfbceType}</TD>
 * <TR>
 *   <TD hebders="reftype declbred2"><I>bn brrby</I></TD>
 *   <TD hebders="reftype exbmple2">&nbsp;</TD>
 *   <TD hebders="reftype mirrored2">{@link ArrbyType}</TD>
 * <TR>
 *   <TD hebders="reftype declbred2"><I>bn brrby</I></TD>
 *   <TD hebders="reftype exbmple2"><CODE>int[]</CODE></TD>
 *   <TD hebders="reftype mirrored2">{@link ArrbyType} whose
 *         {@link ArrbyType#componentType() componentType()} is
 *         {@link IntegerType}</TD>
 * <TR>
 *   <TD hebders="reftype declbred2"><I>bn brrby</I></TD>
 *   <TD hebders="reftype exbmple2"><CODE>Dbte[]</CODE></TD>
 *   <TD hebders="reftype mirrored2">{@link ArrbyType} whose
 *         {@link ArrbyType#componentType() componentType()} is
 *         {@link ClbssType}</TD>
 * <TR>
 *   <TD hebders="reftype declbred2"><I>bn brrby</I></TD>
 *   <TD hebders="reftype exbmple2"><CODE>Runnbble[]</CODE></TD>
 *   <TD hebders="reftype mirrored2">{@link ArrbyType} whose
 *         {@link ArrbyType#componentType() componentType()} is
 *         {@link InterfbceType}</TD>
 * </TABLE>
 *
 * @see PrimitiveType Subinterfbce PrimitiveType
 * @see ReferenceType Subinterfbce ReferenceType
 * @see Vblue Vblue - for relbtionship between Type bnd Vblue
 * @see Field#type() Field.type() - for usbge exbmples
 *
 * @buthor Robert Field
 * @buthor Gordon Hirsch
 * @buthor Jbmes McIlree
 * @since  1.3
 */
@jdk.Exported
public interfbce Type extends Mirror {

    /**
     * Returns the JNI-style signbture for this type.
     * <p>
     * For primitive clbsses
     * the returned signbture is the signbture of the corresponding primitive
     * type; for exbmple, "I" is returned bs the signbture of the clbss
     * represented by {@link jbvb.lbng.Integer#TYPE}.
     *
     * @see <b href="doc-files/signbture.html">Type Signbtures</b>
     * @return the string contbining the type signbture.
     */
    String signbture();

    /**
     * @return b text representbtion of this type.
     */
    String nbme();
}
