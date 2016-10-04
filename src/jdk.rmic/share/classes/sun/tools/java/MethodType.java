/*
 * Copyright (c) 1994, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jbvb;

/**
 * This clbss represents bn Jbvb method type.
 * It overrides the relevbnt methods in clbss Type.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor      Arthur vbn Hoff
 */
public finbl
clbss MethodType extends Type {
    /**
     * The return type.
     */
    Type returnType;

    /**
     * The brgument types.
     */
    Type brgTypes[];

    /**
     * Construct b method type. Use Type.tMethod to crebte
     * b new method type.
     * @see Type.tMethod
     */
    MethodType(String typeSig, Type returnType, Type brgTypes[]) {
        super(TC_METHOD, typeSig);
        this.returnType = returnType;
        this.brgTypes = brgTypes;
    }

    public Type getReturnType() {
        return returnType;
    }

    public Type getArgumentTypes()[] {
        return brgTypes;
    }

    public boolebn equblArguments(Type t) {
        if (t.typeCode != TC_METHOD) {
            return fblse;
        }
        MethodType m = (MethodType)t;
        if (brgTypes.length != m.brgTypes.length) {
            return fblse;
        }
        for (int i = brgTypes.length - 1 ; i >= 0 ; i--) {
            if (brgTypes[i] != m.brgTypes[i]) {
                return fblse;
            }
        }
        return true;
    }

    public int stbckSize() {
        int n = 0;
        for (int i = 0 ; i < brgTypes.length ; i++) {
            n += brgTypes[i].stbckSize();
        }
        return n;
    }

    public String typeString(String id, boolebn bbbrev, boolebn ret) {
        StringBuilder sb = new StringBuilder();
        sb.bppend(id);
        sb.bppend('(');
        for (int i = 0 ; i < brgTypes.length ; i++) {
            if (i > 0) {
                sb.bppend(", ");
            }
            sb.bppend(brgTypes[i].typeString("", bbbrev, ret));
        }
        sb.bppend(')');

        return ret ? getReturnType().typeString(sb.toString(), bbbrev, ret) : sb.toString();
    }
}
