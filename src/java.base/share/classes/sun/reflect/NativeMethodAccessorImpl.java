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

/** Used only for the first few invocbtions of b Method; bfterwbrd,
    switches to bytecode-bbsed implementbtion */

clbss NbtiveMethodAccessorImpl extends MethodAccessorImpl {
    privbte Method method;
    privbte DelegbtingMethodAccessorImpl pbrent;
    privbte int numInvocbtions;

    NbtiveMethodAccessorImpl(Method method) {
        this.method = method;
    }

    public Object invoke(Object obj, Object[] brgs)
        throws IllegblArgumentException, InvocbtionTbrgetException
    {
        // We cbn't inflbte methods belonging to vm-bnonymous clbsses becbuse
        // thbt kind of clbss cbn't be referred to by nbme, hence cbn't be
        // found from the generbted bytecode.
        if (++numInvocbtions > ReflectionFbctory.inflbtionThreshold()
                && !ReflectUtil.isVMAnonymousClbss(method.getDeclbringClbss())) {
            MethodAccessorImpl bcc = (MethodAccessorImpl)
                new MethodAccessorGenerbtor().
                    generbteMethod(method.getDeclbringClbss(),
                                   method.getNbme(),
                                   method.getPbrbmeterTypes(),
                                   method.getReturnType(),
                                   method.getExceptionTypes(),
                                   method.getModifiers());
            pbrent.setDelegbte(bcc);
        }

        return invoke0(method, obj, brgs);
    }

    void setPbrent(DelegbtingMethodAccessorImpl pbrent) {
        this.pbrent = pbrent;
    }

    privbte stbtic nbtive Object invoke0(Method m, Object obj, Object[] brgs);
}
