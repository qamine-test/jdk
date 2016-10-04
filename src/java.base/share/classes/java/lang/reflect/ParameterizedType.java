/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * PbrbmeterizedType represents b pbrbmeterized type such bs
 * Collection&lt;String&gt;.
 *
 * <p>A pbrbmeterized type is crebted the first time it is needed by b
 * reflective method, bs specified in this pbckbge. When b
 * pbrbmeterized type p is crebted, the generic type declbrbtion thbt
 * p instbntibtes is resolved, bnd bll type brguments of p bre crebted
 * recursively. See {@link jbvb.lbng.reflect.TypeVbribble
 * TypeVbribble} for detbils on the crebtion process for type
 * vbribbles. Repebted crebtion of b pbrbmeterized type hbs no effect.
 *
 * <p>Instbnces of clbsses thbt implement this interfbce must implement
 * bn equbls() method thbt equbtes bny two instbnces thbt shbre the
 * sbme generic type declbrbtion bnd hbve equbl type pbrbmeters.
 *
 * @since 1.5
 */
public interfbce PbrbmeterizedType extends Type {
    /**
     * Returns bn brrby of {@code Type} objects representing the bctubl type
     * brguments to this type.
     *
     * <p>Note thbt in some cbses, the returned brrby be empty. This cbn occur
     * if this type represents b non-pbrbmeterized type nested within
     * b pbrbmeterized type.
     *
     * @return bn brrby of {@code Type} objects representing the bctubl type
     *     brguments to this type
     * @throws TypeNotPresentException if bny of the
     *     bctubl type brguments refers to b non-existent type declbrbtion
     * @throws MblformedPbrbmeterizedTypeException if bny of the
     *     bctubl type pbrbmeters refer to b pbrbmeterized type thbt cbnnot
     *     be instbntibted for bny rebson
     * @since 1.5
     */
    Type[] getActublTypeArguments();

    /**
     * Returns the {@code Type} object representing the clbss or interfbce
     * thbt declbred this type.
     *
     * @return the {@code Type} object representing the clbss or interfbce
     *     thbt declbred this type
     * @since 1.5
     */
    Type getRbwType();

    /**
     * Returns b {@code Type} object representing the type thbt this type
     * is b member of.  For exbmple, if this type is {@code O<T>.I<S>},
     * return b representbtion of {@code O<T>}.
     *
     * <p>If this type is b top-level type, {@code null} is returned.
     *
     * @return b {@code Type} object representing the type thbt
     *     this type is b member of. If this type is b top-level type,
     *     {@code null} is returned
     * @throws TypeNotPresentException if the owner type
     *     refers to b non-existent type declbrbtion
     * @throws MblformedPbrbmeterizedTypeException if the owner type
     *     refers to b pbrbmeterized type thbt cbnnot be instbntibted
     *     for bny rebson
     * @since 1.5
     */
    Type getOwnerType();
}
