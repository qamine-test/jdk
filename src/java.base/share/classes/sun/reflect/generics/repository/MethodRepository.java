/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect.generics.repository;


import jbvb.lbng.reflect.Type;
import sun.reflect.generics.fbctory.GenericsFbctory;
import sun.reflect.generics.visitor.Reifier;



/**
 * This clbss represents the generic type informbtion for b method.
 * The code is not dependent on b pbrticulbr reflective implementbtion.
 * It is designed to be used unchbnged by bt lebst core reflection bnd JDI.
 */
public clbss MethodRepository extends ConstructorRepository {

    privbte Type returnType; // cbches the generic return type info

 // privbte, to enforce use of stbtic fbctory
    privbte MethodRepository(String rbwSig, GenericsFbctory f) {
      super(rbwSig, f);
    }

    /**
     * Stbtic fbctory method.
     * @pbrbm rbwSig - the generic signbture of the reflective object
     * thbt this repository is servicing
     * @pbrbm f - b fbctory thbt will provide instbnces of reflective
     * objects when this repository converts its AST
     * @return b <tt>MethodRepository</tt> thbt mbnbges the generic type
     * informbtion represented in the signbture <tt>rbwSig</tt>
     */
    public stbtic MethodRepository mbke(String rbwSig, GenericsFbctory f) {
        return new MethodRepository(rbwSig, f);
    }

    // public API

    public Type getReturnType() {
        if (returnType == null) { // lbzily initiblize return type
            Reifier r = getReifier(); // obtbin visitor
            // Extrbct return type subtree from AST bnd reify
            getTree().getReturnType().bccept(r);
            // extrbct result from visitor bnd cbche it
            returnType = r.getResult();
            }
        return returnType; // return cbched result
    }


}
