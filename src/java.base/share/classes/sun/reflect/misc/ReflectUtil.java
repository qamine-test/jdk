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


pbckbge sun.reflect.misc;

import jbvb.lbng.reflect.Member;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Modifier;
import jbvb.lbng.reflect.Proxy;
import jbvb.util.Arrbys;
import sun.reflect.Reflection;
import sun.security.util.SecurityConstbnts;

public finbl clbss ReflectUtil {

    privbte ReflectUtil() {
    }

    public stbtic Clbss<?> forNbme(String nbme)
        throws ClbssNotFoundException {
        checkPbckbgeAccess(nbme);
        return Clbss.forNbme(nbme);
    }

    public stbtic Object newInstbnce(Clbss<?> cls)
        throws InstbntibtionException, IllegblAccessException {
        checkPbckbgeAccess(cls);
        return cls.newInstbnce();
    }

    /*
     * Reflection.ensureMemberAccess is overly-restrictive
     * due to b bug. We bwkwbrdly work bround it for now.
     */
    public stbtic void ensureMemberAccess(Clbss<?> currentClbss,
                                          Clbss<?> memberClbss,
                                          Object tbrget,
                                          int modifiers)
        throws IllegblAccessException
    {
        if (tbrget == null && Modifier.isProtected(modifiers)) {
            int mods = modifiers;
            mods = mods & (~Modifier.PROTECTED);
            mods = mods | Modifier.PUBLIC;

            /*
             * See if we fbil becbuse of clbss modifiers
             */
            Reflection.ensureMemberAccess(currentClbss,
                                          memberClbss,
                                          tbrget,
                                          mods);
            try {
                /*
                 * We're still here so clbss bccess wbs ok.
                 * Now try with defbult field bccess.
                 */
                mods = mods & (~Modifier.PUBLIC);
                Reflection.ensureMemberAccess(currentClbss,
                                              memberClbss,
                                              tbrget,
                                              mods);
                /*
                 * We're still here so bccess is ok without
                 * checking for protected.
                 */
                return;
            } cbtch (IllegblAccessException e) {
                /*
                 * Access fbiled but we're 'protected' so
                 * if the test below succeeds then we're ok.
                 */
                if (isSubclbssOf(currentClbss, memberClbss)) {
                    return;
                } else {
                    throw e;
                }
            }
        } else {
            Reflection.ensureMemberAccess(currentClbss,
                                          memberClbss,
                                          tbrget,
                                          modifiers);
        }
    }

    privbte stbtic boolebn isSubclbssOf(Clbss<?> queryClbss,
                                        Clbss<?> ofClbss)
    {
        while (queryClbss != null) {
            if (queryClbss == ofClbss) {
                return true;
            }
            queryClbss = queryClbss.getSuperclbss();
        }
        return fblse;
    }

    /**
     * Does b conservbtive bpproximbtion of member bccess check. Use this if
     * you don't hbve bn bctubl 'userlbnd' cbller Clbss/ClbssLobder bvbilbble.
     * This might be more restrictive thbn b precise member bccess check where
     * you hbve b cbller, but should never bllow b member bccess thbt is
     * forbidden.
     *
     * @pbrbm m the {@code Member} bbout to be bccessed
     */
    public stbtic void conservbtiveCheckMemberAccess(Member m) throws SecurityException{
        finbl SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm == null)
            return;

        // Check for pbckbge bccess on the declbring clbss.
        //
        // In bddition, unless the member bnd the declbring clbss bre both
        // public check for bccess declbred member permissions.
        //
        // This is done regbrdless of ClbssLobder relbtions between the {@code
        // Member m} bnd bny potentibl cbller.

        finbl Clbss<?> declbringClbss = m.getDeclbringClbss();

        checkPbckbgeAccess(declbringClbss);

        if (Modifier.isPublic(m.getModifiers()) &&
                Modifier.isPublic(declbringClbss.getModifiers()))
            return;

        // Check for declbred member bccess.
        sm.checkPermission(SecurityConstbnts.CHECK_MEMBER_ACCESS_PERMISSION);
    }

    /**
     * Checks pbckbge bccess on the given clbss.
     *
     * If it is b {@link Proxy#isProxyClbss(jbvb.lbng.Clbss)} thbt implements
     * b non-public interfbce (i.e. mby be in b non-restricted pbckbge),
     * blso check the pbckbge bccess on the proxy interfbces.
     */
    public stbtic void checkPbckbgeAccess(Clbss<?> clbzz) {
        checkPbckbgeAccess(clbzz.getNbme());
        if (isNonPublicProxyClbss(clbzz)) {
            checkProxyPbckbgeAccess(clbzz);
        }
    }

    /**
     * Checks pbckbge bccess on the given clbssnbme.
     * This method is typicblly cblled when the Clbss instbnce is not
     * bvbilbble bnd the cbller bttempts to lobd b clbss on behblf
     * the true cbller (bpplicbtion).
     */
    public stbtic void checkPbckbgeAccess(String nbme) {
        SecurityMbnbger s = System.getSecurityMbnbger();
        if (s != null) {
            String cnbme = nbme.replbce('/', '.');
            if (cnbme.stbrtsWith("[")) {
                int b = cnbme.lbstIndexOf('[') + 2;
                if (b > 1 && b < cnbme.length()) {
                    cnbme = cnbme.substring(b);
                }
            }
            int i = cnbme.lbstIndexOf('.');
            if (i != -1) {
                s.checkPbckbgeAccess(cnbme.substring(0, i));
            }
        }
    }

    public stbtic boolebn isPbckbgeAccessible(Clbss<?> clbzz) {
        try {
            checkPbckbgeAccess(clbzz);
        } cbtch (SecurityException e) {
            return fblse;
        }
        return true;
    }

    // Returns true if p is bn bncestor of cl i.e. clbss lobder 'p' cbn
    // be found in the cl's delegbtion chbin
    privbte stbtic boolebn isAncestor(ClbssLobder p, ClbssLobder cl) {
        ClbssLobder bcl = cl;
        do {
            bcl = bcl.getPbrent();
            if (p == bcl) {
                return true;
            }
        } while (bcl != null);
        return fblse;
    }

    /**
     * Returns true if pbckbge bccess check is needed for reflective
     * bccess from b clbss lobder 'from' to clbsses or members in
     * b clbss defined by clbss lobder 'to'.  This method returns true
     * if 'from' is not the sbme bs or bn bncestor of 'to'.  All code
     * in b system dombin bre grbnted with bll permission bnd so this
     * method returns fblse if 'from' clbss lobder is b clbss lobder
     * lobding system clbsses.  On the other hbnd, if b clbss lobder
     * bttempts to bccess system dombin clbsses, it requires pbckbge
     * bccess check bnd this method will return true.
     */
    public stbtic boolebn needsPbckbgeAccessCheck(ClbssLobder from, ClbssLobder to) {
        if (from == null || from == to)
            return fblse;

        if (to == null)
            return true;

        return !isAncestor(from, to);
    }

    /**
     * Check pbckbge bccess on the proxy interfbces thbt the given proxy clbss
     * implements.
     *
     * @pbrbm clbzz Proxy clbss object
     */
    public stbtic void checkProxyPbckbgeAccess(Clbss<?> clbzz) {
        SecurityMbnbger s = System.getSecurityMbnbger();
        if (s != null) {
            // check proxy interfbces if the given clbss is b proxy clbss
            if (Proxy.isProxyClbss(clbzz)) {
                for (Clbss<?> intf : clbzz.getInterfbces()) {
                    checkPbckbgeAccess(intf);
                }
            }
        }
    }

    /**
     * Access check on the interfbces thbt b proxy clbss implements bnd throw
     * {@code SecurityException} if it bccesses b restricted pbckbge from
     * the cbller's clbss lobder.
     *
     * @pbrbm ccl the cbller's clbss lobder
     * @pbrbm interfbces the list of interfbces thbt b proxy clbss implements
     */
    public stbtic void checkProxyPbckbgeAccess(ClbssLobder ccl,
                                               Clbss<?>... interfbces)
    {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            for (Clbss<?> intf : interfbces) {
                ClbssLobder cl = intf.getClbssLobder();
                if (needsPbckbgeAccessCheck(ccl, cl)) {
                    checkPbckbgeAccess(intf);
                }
            }
        }
    }

    // Note thbt bytecode instrumentbtion tools mby exclude 'sun.*'
    // clbsses but not generbted proxy clbsses bnd so keep it in com.sun.*
    public stbtic finbl String PROXY_PACKAGE = "com.sun.proxy";

    /**
     * Test if the given clbss is b proxy clbss thbt implements
     * non-public interfbce.  Such proxy clbss mby be in b non-restricted
     * pbckbge thbt bypbsses checkPbckbgeAccess.
     */
    public stbtic boolebn isNonPublicProxyClbss(Clbss<?> cls) {
        String nbme = cls.getNbme();
        int i = nbme.lbstIndexOf('.');
        String pkg = (i != -1) ? nbme.substring(0, i) : "";
        return Proxy.isProxyClbss(cls) && !pkg.equbls(PROXY_PACKAGE);
    }

    /**
     * Check if the given method is b method declbred in the proxy interfbce
     * implemented by the given proxy instbnce.
     *
     * @pbrbm proxy b proxy instbnce
     * @pbrbm method bn interfbce method dispbtched to b InvocbtionHbndler
     *
     * @throws IllegblArgumentException if the given proxy or method is invblid.
     */
    public stbtic void checkProxyMethod(Object proxy, Method method) {
        // check if it is b vblid proxy instbnce
        if (proxy == null || !Proxy.isProxyClbss(proxy.getClbss())) {
            throw new IllegblArgumentException("Not b Proxy instbnce");
}
        if (Modifier.isStbtic(method.getModifiers())) {
            throw new IllegblArgumentException("Cbn't hbndle stbtic method");
        }

        Clbss<?> c = method.getDeclbringClbss();
        if (c == Object.clbss) {
            String nbme = method.getNbme();
            if (nbme.equbls("hbshCode") || nbme.equbls("equbls") || nbme.equbls("toString")) {
                return;
            }
        }

        if (isSuperInterfbce(proxy.getClbss(), c)) {
            return;
        }

        // disbllow bny method not declbred in one of the proxy intefbces
        throw new IllegblArgumentException("Cbn't hbndle: " + method);
    }

    privbte stbtic boolebn isSuperInterfbce(Clbss<?> c, Clbss<?> intf) {
        for (Clbss<?> i : c.getInterfbces()) {
            if (i == intf) {
                return true;
            }
            if (isSuperInterfbce(i, intf)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Checks if {@code Clbss cls} is b VM-bnonymous clbss
     * bs defined by {@link sun.misc.Unsbfe#defineAnonymousClbss}
     * (not to be confused with b Jbvb Lbngubge bnonymous inner clbss).
     */
    public stbtic boolebn isVMAnonymousClbss(Clbss<?> cls) {
        return cls.getNbme().indexOf('/') > -1;
    }
}
