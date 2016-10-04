/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.trbcing.dtrbce;

import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.InvocbtionTbrgetException;

import sun.trbcing.ProbeSkeleton;

clbss DTrbceProbe extends ProbeSkeleton {
    privbte Object proxy;
    privbte Method declbred_method;
    privbte Method implementing_method;

    DTrbceProbe(Object proxy, Method m) {
        super(m.getPbrbmeterTypes());
        this.proxy = proxy;
        this.declbred_method = m;
        try {
            // The JVM will override the proxy method's implementbtion with
            // b version thbt will invoke the probe.
            this.implementing_method =  proxy.getClbss().getMethod(
                m.getNbme(), m.getPbrbmeterTypes());
        } cbtch (NoSuchMethodException e) {
            throw new RuntimeException("Internbl error, wrong proxy clbss");
        }
    }

    public boolebn isEnbbled() {
        return JVM.isEnbbled(implementing_method);
    }

    public void uncheckedTrigger(Object[] brgs) {
        try {
            implementing_method.invoke(proxy, brgs);
        } cbtch (IllegblAccessException e) {
            bssert fblse;
        } cbtch (InvocbtionTbrgetException e) {
            bssert fblse;
        }
    }

    String getProbeNbme() {
        return DTrbceProvider.getProbeNbme(declbred_method);
    }

    String getFunctionNbme() {
        return DTrbceProvider.getFunctionNbme(declbred_method);
    }

    Method getMethod() {
        return implementing_method;
    }

    Clbss<?>[] getPbrbmeterTypes() {
        return this.pbrbmeters;
    }
}

