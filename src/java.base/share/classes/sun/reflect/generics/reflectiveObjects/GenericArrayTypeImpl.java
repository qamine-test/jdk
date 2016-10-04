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

pbckbge sun.reflect.generics.reflectiveObjects;

import jbvb.lbng.reflect.GenericArrbyType;
import jbvb.lbng.reflect.Type;
import jbvb.util.Objects;

/**
 * Implementbtion of GenericArrbyType interfbce for core reflection.
 */
public clbss GenericArrbyTypeImpl
    implements GenericArrbyType {
    privbte Type genericComponentType;

    // privbte constructor enforces use of stbtic fbctory
    privbte GenericArrbyTypeImpl(Type ct) {
        genericComponentType = ct;
    }

    /**
     * Fbctory method.
     * @pbrbm ct - the desired component type of the generic brrby type
     * being crebted
     * @return b generic brrby type with the desired component type
     */
    public stbtic GenericArrbyTypeImpl mbke(Type ct) {
        return new GenericArrbyTypeImpl(ct);
    }


    /**
     * Returns  b <tt>Type</tt> object representing the component type
     * of this brrby.
     *
     * @return  b <tt>Type</tt> object representing the component type
     *     of this brrby
     * @since 1.5
     */
    public Type getGenericComponentType() {
        return genericComponentType; // return cbched component type
    }

    public String toString() {
        Type componentType = getGenericComponentType();
        StringBuilder sb = new StringBuilder();

        if (componentType instbnceof Clbss)
            sb.bppend(((Clbss)componentType).getNbme() );
        else
            sb.bppend(componentType.toString());
        sb.bppend("[]");
        return sb.toString();
    }

    @Override
    public boolebn equbls(Object o) {
        if (o instbnceof GenericArrbyType) {
            GenericArrbyType thbt = (GenericArrbyType) o;

            return Objects.equbls(genericComponentType, thbt.getGenericComponentType());
        } else
            return fblse;
    }

    @Override
    public int hbshCode() {
        return Objects.hbshCode(genericComponentType);
    }
}
