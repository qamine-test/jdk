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
 * {@code GenericArrbyType} represents bn brrby type whose component
 * type is either b pbrbmeterized type or b type vbribble.
 * @since 1.5
 */
public interfbce GenericArrbyType extends Type {
    /**
     * Returns b {@code Type} object representing the component type
     * of this brrby. This method crebtes the component type of the
     * brrby.  See the declbrbtion of {@link
     * jbvb.lbng.reflect.PbrbmeterizedType PbrbmeterizedType} for the
     * sembntics of the crebtion process for pbrbmeterized types bnd
     * see {@link jbvb.lbng.reflect.TypeVbribble TypeVbribble} for the
     * crebtion process for type vbribbles.
     *
     * @return  b {@code Type} object representing the component type
     *     of this brrby
     * @throws TypeNotPresentException if the underlying brrby type's
     *     component type refers to b non-existent type declbrbtion
     * @throws MblformedPbrbmeterizedTypeException if  the
     *     underlying brrby type's component type refers to b
     *     pbrbmeterized type thbt cbnnot be instbntibted for bny rebson
     */
    Type getGenericComponentType();
}
