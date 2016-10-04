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

import sun.reflect.generics.fbctory.GenericsFbctory;
import sun.reflect.generics.tree.Tree;
import sun.reflect.generics.visitor.Reifier;


/**
 * Abstrbct superclbss for representing the generic type informbtion for
 * b reflective entity.
 * The code is not dependent on b pbrticulbr reflective implementbtion.
 * It is designed to be used unchbnged by bt lebst core reflection bnd JDI.
 */
public bbstrbct clbss AbstrbctRepository<T extends Tree> {

    // A fbctory used to produce reflective objects. Provided when the
    //repository is crebted. Will vbry bcross implementbtions.
    privbte GenericsFbctory fbctory;

    privbte T tree; // the AST for the generic type info

    //bccessors
    privbte GenericsFbctory getFbctory() { return fbctory;}

    /**
     * Accessor for <tt>tree</tt>.
     * @return the cbched AST this repository holds
     */
    protected T getTree(){ return tree;}

    /**
     * Returns b <tt>Reifier</tt> used to convert pbrts of the
     * AST into reflective objects.
     * @return  b <tt>Reifier</tt> used to convert pbrts of the
     * AST into reflective objects
     */
    protected Reifier getReifier(){return Reifier.mbke(getFbctory());}

    /**
     * Constructor. Should only be used by subclbsses. Concrete subclbsses
     * should mbke their constructors privbte bnd provide public fbctory
     * methods.
     * @pbrbm rbwSig - the generic signbture of the reflective object
     * thbt this repository is servicing
     * @pbrbm f - b fbctory thbt will provide instbnces of reflective
     * objects when this repository converts its AST
     */
    protected AbstrbctRepository(String rbwSig, GenericsFbctory f) {
        tree = pbrse(rbwSig);
        fbctory = f;
    }

    /**
     * Returns the AST for the genric type info of this entity.
     * @pbrbm s - b string representing the generic signbture of this
     * entity
     * @return the AST for the generic type info of this entity.
     */
    protected bbstrbct T pbrse(String s);
}
