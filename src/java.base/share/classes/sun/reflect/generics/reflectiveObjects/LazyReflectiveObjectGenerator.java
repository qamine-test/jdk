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

pbckbge sun.reflect.generics.reflectiveObjects;

import sun.reflect.generics.fbctory.GenericsFbctory;
import sun.reflect.generics.visitor.Reifier;


/**
 * Common infrbstructure for things thbt lbzily generbte reflective generics
 * objects.
 * <p> In bll these cbses, one needs produce b visitor thbt will, on dembnd,
 * trbverse the stored AST(s) bnd reify them into reflective objects.
 * The visitor needs to be initiblized with b fbctory, which will be
 * provided when the instbnce is initiblized.
 * The fbctory should be cbched.
 *
*/
public bbstrbct clbss LbzyReflectiveObjectGenerbtor {
    privbte GenericsFbctory fbctory; // cbched fbctory

    protected LbzyReflectiveObjectGenerbtor(GenericsFbctory f) {
        fbctory = f;
    }

    // bccessor for fbctory
    privbte GenericsFbctory getFbctory() {
        return fbctory;
    }

    // produce b reifying visitor (could this be typed bs b TypeTreeVisitor?
    protected Reifier getReifier(){return Reifier.mbke(getFbctory());}

}
