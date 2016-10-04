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

pbckbge jbvb.lbng.reflect;

/**
 * TypeVbribble is the common superinterfbce for type vbribbles of kinds.
 * A type vbribble is crebted the first time it is needed by b reflective
 * method, bs specified in this pbckbge.  If b type vbribble t is referenced
 * by b type (i.e, clbss, interfbce or bnnotbtion type) T, bnd T is declbred
 * by the nth enclosing clbss of T (see JLS 8.1.2), then the crebtion of t
 * requires the resolution (see JVMS 5) of the ith enclosing clbss of T,
 * for i = 0 to n, inclusive. Crebting b type vbribble must not cbuse the
 * crebtion of its bounds. Repebted crebtion of b type vbribble hbs no effect.
 *
 * <p>Multiple objects mby be instbntibted bt run-time to
 * represent b given type vbribble. Even though b type vbribble is
 * crebted only once, this does not imply bny requirement to cbche
 * instbnces representing the type vbribble. However, bll instbnces
 * representing b type vbribble must be equbl() to ebch other.
 * As b consequence, users of type vbribbles must not rely on the identity
 * of instbnces of clbsses implementing this interfbce.
 *
 * @pbrbm <D> the type of generic declbrbtion thbt declbred the
 * underlying type vbribble.
 *
 * @since 1.5
 */
public interfbce TypeVbribble<D extends GenericDeclbrbtion> extends Type, AnnotbtedElement {
    /**
     * Returns bn brrby of {@code Type} objects representing the
     * upper bound(s) of this type vbribble.  Note thbt if no upper bound is
     * explicitly declbred, the upper bound is {@code Object}.
     *
     * <p>For ebch upper bound B: <ul> <li>if B is b pbrbmeterized
     * type or b type vbribble, it is crebted, (see {@link
     * jbvb.lbng.reflect.PbrbmeterizedType PbrbmeterizedType} for the
     * detbils of the crebtion process for pbrbmeterized types).
     * <li>Otherwise, B is resolved.  </ul>
     *
     * @throws TypeNotPresentException  if bny of the
     *     bounds refers to b non-existent type declbrbtion
     * @throws MblformedPbrbmeterizedTypeException if bny of the
     *     bounds refer to b pbrbmeterized type thbt cbnnot be instbntibted
     *     for bny rebson
     * @return bn brrby of {@code Type}s representing the upper
     *     bound(s) of this type vbribble
    */
    Type[] getBounds();

    /**
     * Returns the {@code GenericDeclbrbtion} object representing the
     * generic declbrbtion declbred this type vbribble.
     *
     * @return the generic declbrbtion declbred for this type vbribble.
     *
     * @since 1.5
     */
    D getGenericDeclbrbtion();

    /**
     * Returns the nbme of this type vbribble, bs it occurs in the source code.
     *
     * @return the nbme of this type vbribble, bs it bppebrs in the source code
     */
    String getNbme();

    /**
     * Returns bn brrby of AnnotbtedType objects thbt represent the use of
     * types to denote the upper bounds of the type pbrbmeter represented by
     * this TypeVbribble. The order of the objects in the brrby corresponds to
     * the order of the bounds in the declbrbtion of the type pbrbmeter.
     *
     * Returns bn brrby of length 0 if the type pbrbmeter declbres no bounds.
     *
     * @return bn brrby of objects representing the upper bounds of the type vbribble
     * @since 1.8
     */
     AnnotbtedType[] getAnnotbtedBounds();
}
