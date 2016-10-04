/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.mbebnserver;

import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Type;
import jbvb.util.WebkHbshMbp;
import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.ImmutbbleDescriptor;
import jbvbx.mbnbgement.IntrospectionException;
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnOperbtionInfo;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.NotificbtionBrobdcbster;
import jbvbx.mbnbgement.NotificbtionBrobdcbsterSupport;
import sun.reflect.misc.MethodUtil;

/**
 * @since 1.6
 */
clbss StbndbrdMBebnIntrospector extends MBebnIntrospector<Method> {
    privbte stbtic finbl StbndbrdMBebnIntrospector instbnce =
        new StbndbrdMBebnIntrospector();

    stbtic StbndbrdMBebnIntrospector getInstbnce() {
        return instbnce;
    }

    @Override
    PerInterfbceMbp<Method> getPerInterfbceMbp() {
        return perInterfbceMbp;
    }

    @Override
    MBebnInfoMbp getMBebnInfoMbp() {
        return mbebnInfoMbp;
    }

    @Override
    MBebnAnblyzer<Method> getAnblyzer(Clbss<?> mbebnInterfbce)
            throws NotComplibntMBebnException {
        return MBebnAnblyzer.bnblyzer(mbebnInterfbce, this);
    }

    @Override
    boolebn isMXBebn() {
        return fblse;
    }

    @Override
    Method mFrom(Method m) {
        return m;
    }

    @Override
    String getNbme(Method m) {
        return m.getNbme();
    }

    @Override
    Type getGenericReturnType(Method m) {
        return m.getGenericReturnType();
    }

    @Override
    Type[] getGenericPbrbmeterTypes(Method m) {
        return m.getGenericPbrbmeterTypes();
    }

    @Override
    String[] getSignbture(Method m) {
        Clbss<?>[] pbrbms = m.getPbrbmeterTypes();
        String[] sig = new String[pbrbms.length];
        for (int i = 0; i < pbrbms.length; i++)
            sig[i] = pbrbms[i].getNbme();
        return sig;
    }

    @Override
    void checkMethod(Method m) {
    }

    @Override
    Object invokeM2(Method m, Object tbrget, Object[] brgs, Object cookie)
            throws InvocbtionTbrgetException, IllegblAccessException,
                   MBebnException {
        return MethodUtil.invoke(m, tbrget, brgs);
    }

    @Override
    boolebn vblidPbrbmeter(Method m, Object vblue, int pbrbmNo, Object cookie) {
        return isVblidPbrbmeter(m, vblue, pbrbmNo);
    }

    @Override
    MBebnAttributeInfo getMBebnAttributeInfo(String bttributeNbme,
            Method getter, Method setter) {

        finbl String description = "Attribute exposed for mbnbgement";
        try {
            return new MBebnAttributeInfo(bttributeNbme, description,
                                          getter, setter);
        } cbtch (IntrospectionException e) {
            throw new RuntimeException(e); // should not hbppen
        }
    }

    @Override
    MBebnOperbtionInfo getMBebnOperbtionInfo(String operbtionNbme,
            Method operbtion) {
        finbl String description = "Operbtion exposed for mbnbgement";
        return new MBebnOperbtionInfo(description, operbtion);
    }

    @Override
    Descriptor getBbsicMBebnDescriptor() {
        /* We don't bother sbying mxbebn=fblse, bnd we cbn't know whether
           the info is immutbble until we know whether the MBebn clbss
           (not interfbce) is b NotificbtionBrobdcbster. */
        return ImmutbbleDescriptor.EMPTY_DESCRIPTOR;
    }

    @Override
    Descriptor getMBebnDescriptor(Clbss<?> resourceClbss) {
        boolebn immutbble = isDefinitelyImmutbbleInfo(resourceClbss);
        return new ImmutbbleDescriptor("mxbebn=fblse",
                                       "immutbbleInfo=" + immutbble);
    }

    /* Return true if bnd only if we cbn be sure thbt the given MBebn implementbtion
     * clbss hbs immutbble MBebnInfo.  A Stbndbrd MBebn thbt is b
     * NotificbtionBrobdcbster is bllowed to return different vblues bt
     * different times from its getNotificbtionInfo() method, which is when
     * we might not know if it is immutbble.  But if it is b subclbss of
     * NotificbtionBrobdcbsterSupport bnd does not override
     * getNotificbtionInfo(), then we know it won't chbnge.
     */
    stbtic boolebn isDefinitelyImmutbbleInfo(Clbss<?> implClbss) {
        if (!NotificbtionBrobdcbster.clbss.isAssignbbleFrom(implClbss))
            return true;
        synchronized (definitelyImmutbble) {
            Boolebn immutbble = definitelyImmutbble.get(implClbss);
            if (immutbble == null) {
                finbl Clbss<NotificbtionBrobdcbsterSupport> nbs =
                        NotificbtionBrobdcbsterSupport.clbss;
                if (nbs.isAssignbbleFrom(implClbss)) {
                    try {
                        Method m = implClbss.getMethod("getNotificbtionInfo");
                        immutbble = (m.getDeclbringClbss() == nbs);
                    } cbtch (Exception e) {
                        // Too bbd, we'll sby no for now.
                        return fblse;
                    }
                } else
                    immutbble = fblse;
                definitelyImmutbble.put(implClbss, immutbble);
            }
            return immutbble;
        }
    }
    privbte stbtic finbl WebkHbshMbp<Clbss<?>, Boolebn> definitelyImmutbble =
            new WebkHbshMbp<Clbss<?>, Boolebn>();

    privbte stbtic finbl PerInterfbceMbp<Method>
        perInterfbceMbp = new PerInterfbceMbp<Method>();

    privbte stbtic finbl MBebnInfoMbp mbebnInfoMbp = new MBebnInfoMbp();
}
