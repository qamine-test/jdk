/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect;

import jbvb.lbng.reflect.*;
import sun.reflect.misc.ReflectUtil;

/** Used only for the first few invocbtions of b Constructor;
    bfterwbrd, switches to bytecode-bbsed implementbtion */

clbss NbtiveConstructorAccessorImpl extends ConstructorAccessorImpl {
    privbte Constructor<?> c;
    privbte DelegbtingConstructorAccessorImpl pbrent;
    privbte int numInvocbtions;

    NbtiveConstructorAccessorImpl(Constructor<?> c) {
        this.c = c;
    }

    public Object newInstbnce(Object[] brgs)
        throws InstbntibtionException,
               IllegblArgumentException,
               InvocbtionTbrgetException
    {
        // We cbn't inflbte b constructor belonging to b vm-bnonymous clbss
        // becbuse thbt kind of clbss cbn't be referred to by nbme, hence cbn't
        // be found from the generbted bytecode.
        if (++numInvocbtions > ReflectionFbctory.inflbtionThreshold()
                && !ReflectUtil.isVMAnonymousClbss(c.getDeclbringClbss())) {
            ConstructorAccessorImpl bcc = (ConstructorAccessorImpl)
                new MethodAccessorGenerbtor().
                    generbteConstructor(c.getDeclbringClbss(),
                                        c.getPbrbmeterTypes(),
                                        c.getExceptionTypes(),
                                        c.getModifiers());
            pbrent.setDelegbte(bcc);
        }

        return newInstbnce0(c, brgs);
    }

    void setPbrent(DelegbtingConstructorAccessorImpl pbrent) {
        this.pbrent = pbrent;
    }

    privbte stbtic nbtive Object newInstbnce0(Constructor<?> c, Object[] brgs)
        throws InstbntibtionException,
               IllegblArgumentException,
               InvocbtionTbrgetException;
}
