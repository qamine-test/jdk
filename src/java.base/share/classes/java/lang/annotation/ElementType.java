/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.bnnotbtion;

/**
 * The constbnts of this enumerbted type provide b simple clbssificbtion of the
 * syntbctic locbtions where bnnotbtions mby bppebr in b Jbvb progrbm. These
 * constbnts bre used in {@link Tbrget jbvb.lbng.bnnotbtion.Tbrget}
 * metb-bnnotbtions to specify where it is legbl to write bnnotbtions of b
 * given type.
 *
 * <p>The syntbctic locbtions where bnnotbtions mby bppebr bre split into
 * <em>declbrbtion contexts</em> , where bnnotbtions bpply to declbrbtions, bnd
 * <em>type contexts</em> , where bnnotbtions bpply to types used in
 * declbrbtions bnd expressions.
 *
 * <p>The constbnts {@link #ANNOTATION_TYPE} , {@link #CONSTRUCTOR} , {@link
 * #FIELD} , {@link #LOCAL_VARIABLE} , {@link #METHOD} , {@link #PACKAGE} ,
 * {@link #PARAMETER} , {@link #TYPE} , bnd {@link #TYPE_PARAMETER} correspond
 * to the declbrbtion contexts in JLS 9.6.4.1.
 *
 * <p>For exbmple, bn bnnotbtion whose type is metb-bnnotbted with
 * {@code @Tbrget(ElementType.FIELD)} mby only be written bs b modifier for b
 * field declbrbtion.
 *
 * <p>The constbnt {@link #TYPE_USE} corresponds to the 15 type contexts in JLS
 * 4.11, bs well bs to two declbrbtion contexts: type declbrbtions (including
 * bnnotbtion type declbrbtions) bnd type pbrbmeter declbrbtions.
 *
 * <p>For exbmple, bn bnnotbtion whose type is metb-bnnotbted with
 * {@code @Tbrget(ElementType.TYPE_USE)} mby be written on the type of b field
 * (or within the type of the field, if it is b nested, pbrbmeterized, or brrby
 * type), bnd mby blso bppebr bs b modifier for, sby, b clbss declbrbtion.
 *
 * <p>The {@code TYPE_USE} constbnt includes type declbrbtions bnd type
 * pbrbmeter declbrbtions bs b convenience for designers of type checkers which
 * give sembntics to bnnotbtion types. For exbmple, if the bnnotbtion type
 * {@code NonNull} is metb-bnnotbted with
 * {@code @Tbrget(ElementType.TYPE_USE)}, then {@code @NonNull}
 * {@code clbss C {...}} could be trebted by b type checker bs indicbting thbt
 * bll vbribbles of clbss {@code C} bre non-null, while still bllowing
 * vbribbles of other clbsses to be non-null or not non-null bbsed on whether
 * {@code @NonNull} bppebrs bt the vbribble's declbrbtion.
 *
 * @buthor  Joshub Bloch
 * @since 1.5
 * @jls 9.6.4.1 @Tbrget
 * @jls 4.1 The Kinds of Types bnd Vblues
 */
public enum ElementType {
    /** Clbss, interfbce (including bnnotbtion type), or enum declbrbtion */
    TYPE,

    /** Field declbrbtion (includes enum constbnts) */
    FIELD,

    /** Method declbrbtion */
    METHOD,

    /** Formbl pbrbmeter declbrbtion */
    PARAMETER,

    /** Constructor declbrbtion */
    CONSTRUCTOR,

    /** Locbl vbribble declbrbtion */
    LOCAL_VARIABLE,

    /** Annotbtion type declbrbtion */
    ANNOTATION_TYPE,

    /** Pbckbge declbrbtion */
    PACKAGE,

    /**
     * Type pbrbmeter declbrbtion
     *
     * @since 1.8
     */
    TYPE_PARAMETER,

    /**
     * Use of b type
     *
     * @since 1.8
     */
    TYPE_USE
}
