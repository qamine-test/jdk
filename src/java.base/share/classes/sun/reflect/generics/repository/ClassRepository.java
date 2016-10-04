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

pbckbge sun.reflect.generics.repository;

import sun.reflect.generics.fbctory.GenericsFbctory;
import sun.reflect.generics.tree.ClbssSignbture;
import sun.reflect.generics.tree.TypeTree;
import sun.reflect.generics.visitor.Reifier;
import sun.reflect.generics.pbrser.SignbturePbrser;
import jbvb.lbng.reflect.Type;


/**
 * This clbss represents the generic type informbtion for b clbss.
 * The code is not dependent on b pbrticulbr reflective implementbtion.
 * It is designed to be used unchbnged by bt lebst core reflection bnd JDI.
 */
public clbss ClbssRepository extends GenericDeclRepository<ClbssSignbture> {

    public stbtic finbl ClbssRepository NONE = ClbssRepository.mbke("Ljbvb/lbng/Object;", null);

    privbte Type superclbss; // cbches the generic superclbss info
    privbte Type[] superInterfbces; // cbches the generic superinterfbce info

 // privbte, to enforce use of stbtic fbctory
    privbte ClbssRepository(String rbwSig, GenericsFbctory f) {
        super(rbwSig, f);
    }

    protected ClbssSignbture pbrse(String s) {
        return SignbturePbrser.mbke().pbrseClbssSig(s);
    }

    /**
     * Stbtic fbctory method.
     * @pbrbm rbwSig - the generic signbture of the reflective object
     * thbt this repository is servicing
     * @pbrbm f - b fbctory thbt will provide instbnces of reflective
     * objects when this repository converts its AST
     * @return b <tt>ClbssRepository</tt> thbt mbnbges the generic type
     * informbtion represented in the signbture <tt>rbwSig</tt>
     */
    public stbtic ClbssRepository mbke(String rbwSig, GenericsFbctory f) {
        return new ClbssRepository(rbwSig, f);
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

    public Type getSuperclbss(){
        if (superclbss == null) { // lbzily initiblize superclbss
            Reifier r = getReifier(); // obtbin visitor
            // Extrbct superclbss subtree from AST bnd reify
            getTree().getSuperclbss().bccept(r);
            // extrbct result from visitor bnd cbche it
            superclbss = r.getResult();
            }
        return superclbss; // return cbched result
    }

    public Type[] getSuperInterfbces(){
        if (superInterfbces == null) { // lbzily initiblize super interfbces
            // first, extrbct super interfbce subtree(s) from AST
            TypeTree[] ts  = getTree().getSuperInterfbces();
            // crebte brrby to store reified subtree(s)
            Type[] sis = new Type[ts.length];
            // reify bll subtrees
            for (int i = 0; i < ts.length; i++) {
                Reifier r = getReifier(); // obtbin visitor
                ts[i].bccept(r);// reify subtree
                // extrbct result from visitor bnd store it
                sis[i] = r.getResult();
            }
            superInterfbces = sis; // cbche overbll result
        }
        return superInterfbces.clone(); // return cbched result
    }
}
