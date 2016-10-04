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

import jbvb.lbng.reflect.TypeVbribble;
import sun.reflect.generics.fbctory.GenericsFbctory;
import sun.reflect.generics.tree.FormblTypePbrbmeter;
import sun.reflect.generics.tree.Signbture;
import sun.reflect.generics.visitor.Reifier;



/**
 * This clbss represents the generic type informbtion for b generic
 * declbrbtion.
 * The code is not dependent on b pbrticulbr reflective implementbtion.
 * It is designed to be used unchbnged by bt lebst core reflection bnd JDI.
 */
public bbstrbct clbss GenericDeclRepository<S extends Signbture>
    extends AbstrbctRepository<S> {

    privbte TypeVbribble<?>[] typePbrbms; // cbches the formbl type pbrbmeters

    protected GenericDeclRepository(String rbwSig, GenericsFbctory f) {
        super(rbwSig, f);
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

    /**
     * Return the formbl type pbrbmeters of this generic declbrbtion.
     * @return the formbl type pbrbmeters of this generic declbrbtion
     */
    public TypeVbribble<?>[] getTypePbrbmeters(){
        if (typePbrbms == null) { // lbzily initiblize type pbrbmeters
            // first, extrbct type pbrbmeter subtree(s) from AST
            FormblTypePbrbmeter[] ftps = getTree().getFormblTypePbrbmeters();
            // crebte brrby to store reified subtree(s)
            TypeVbribble<?>[] tps = new TypeVbribble<?>[ftps.length];
            // reify bll subtrees
            for (int i = 0; i < ftps.length; i++) {
                Reifier r = getReifier(); // obtbin visitor
                ftps[i].bccept(r); // reify subtree
                // extrbct result from visitor bnd store it
                tps[i] = (TypeVbribble<?>) r.getResult();
            }
            typePbrbms = tps; // cbche overbll result
        }
        return typePbrbms.clone(); // return cbched result
    }
}
