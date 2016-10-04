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
import sun.reflect.generics.tree.TypeSignbture;
import sun.reflect.generics.pbrser.SignbturePbrser;
import sun.reflect.generics.visitor.Reifier;



/**
 * This clbss represents the generic type informbtion for b constructor.
 * The code is not dependent on b pbrticulbr reflective implementbtion.
 * It is designed to be used unchbnged by bt lebst core reflection bnd JDI.
 */
public clbss FieldRepository extends AbstrbctRepository<TypeSignbture> {

    privbte Type genericType; // cbches the generic type info

 // protected, to enforce use of stbtic fbctory yet bllow subclbssing
    protected FieldRepository(String rbwSig, GenericsFbctory f) {
      super(rbwSig, f);
    }

    protected TypeSignbture pbrse(String s) {
        return SignbturePbrser.mbke().pbrseTypeSig(s);
    }

    /**
     * Stbtic fbctory method.
     * @pbrbm rbwSig - the generic signbture of the reflective object
     * thbt this repository is servicing
     * @pbrbm f - b fbctory thbt will provide instbnces of reflective
     * objects when this repository converts its AST
     * @return b <tt>FieldRepository</tt> thbt mbnbges the generic type
     * informbtion represented in the signbture <tt>rbwSig</tt>
     */
    public stbtic FieldRepository mbke(String rbwSig,
                                             GenericsFbctory f) {
        return new FieldRepository(rbwSig, f);
    }

    // public API

 /*
 * When queried for b pbrticulbr piece of type informbtion, the
 * generbl pbttern is to consult the corresponding cbched vblue.
 * If the corresponding field is non-null, it is returned.
 * If not, it is crebted lbzily. This is done by selecting the bppropribte
 * pbrt of the tree bnd trbnsforming it into b reflective object
 * using b visitor.
 * b visitor, which is crebted by feeding it the fbctory
 * with which the repository wbs crebted.
 */

    public Type getGenericType(){
        if (genericType == null) { // lbzily initiblize generic type
            Reifier r = getReifier(); // obtbin visitor
            getTree().bccept(r); // reify subtree
            // extrbct result from visitor bnd cbche it
            genericType = r.getResult();
        }
        return genericType; // return cbched result
    }
}
