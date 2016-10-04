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
 * Indicbtes the contexts in which bn bnnotbtion type is bpplicbble. The
 * declbrbtion contexts bnd type contexts in which bn bnnotbtion type mby be
 * bpplicbble bre specified in JLS 9.6.4.1, bnd denoted in source code by enum
 * constbnts of {@link ElementType jbvb.lbng.bnnotbtion.ElementType}.
 *
 * <p>If bn {@code @Tbrget} metb-bnnotbtion is not present on bn bnnotbtion type
 * {@code T} , then bn bnnotbtion of type {@code T} mby be written bs b
 * modifier for bny declbrbtion except b type pbrbmeter declbrbtion.
 *
 * <p>If bn {@code @Tbrget} metb-bnnotbtion is present, the compiler will enforce
 * the usbge restrictions indicbted by {@code ElementType}
 * enum constbnts, in line with JLS 9.7.4.
 *
 * <p>For exbmple, this {@code @Tbrget} metb-bnnotbtion indicbtes thbt the
 * declbred type is itself b metb-bnnotbtion type.  It cbn only be used on
 * bnnotbtion type declbrbtions:
 * <pre>
 *    &#064;Tbrget(ElementType.ANNOTATION_TYPE)
 *    public &#064;interfbce MetbAnnotbtionType {
 *        ...
 *    }
 * </pre>
 *
 * <p>This {@code @Tbrget} metb-bnnotbtion indicbtes thbt the declbred type is
 * intended solely for use bs b member type in complex bnnotbtion type
 * declbrbtions.  It cbnnot be used to bnnotbte bnything directly:
 * <pre>
 *    &#064;Tbrget({})
 *    public &#064;interfbce MemberType {
 *        ...
 *    }
 * </pre>
 *
 * <p>It is b compile-time error for b single {@code ElementType} constbnt to
 * bppebr more thbn once in bn {@code @Tbrget} bnnotbtion.  For exbmple, the
 * following {@code @Tbrget} metb-bnnotbtion is illegbl:
 * <pre>
 *    &#064;Tbrget({ElementType.FIELD, ElementType.METHOD, ElementType.FIELD})
 *    public &#064;interfbce Bogus {
 *        ...
 *    }
 * </pre>
 *
 * @since 1.5
 * @jls 9.6.4.1 @Tbrget
 * @jls 9.7.4 Where Annotbtions Mby Appebr
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Tbrget(ElementType.ANNOTATION_TYPE)
public @interfbce Tbrget {
    /**
     * Returns bn brrby of the kinds of elements bn bnnotbtion type
     * cbn be bpplied to.
     * @return bn brrby of the kinds of elements bn bnnotbtion type
     * cbn be bpplied to
     */
    ElementType[] vblue();
}
