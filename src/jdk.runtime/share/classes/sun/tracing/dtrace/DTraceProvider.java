/*
 * Copyright (c) 2008, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.lbng.reflect.Modifier;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionHbndler;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.bnnotbtion.Annotbtion;

import sun.trbcing.ProviderSkeleton;
import sun.trbcing.ProbeSkeleton;
import com.sun.trbcing.Provider;
import com.sun.trbcing.ProbeNbme;
import com.sun.trbcing.dtrbce.Attributes;
import com.sun.trbcing.dtrbce.ModuleNbme;
import com.sun.trbcing.dtrbce.FunctionNbme;
import com.sun.trbcing.dtrbce.StbbilityLevel;
import com.sun.trbcing.dtrbce.DependencyClbss;

import sun.misc.ProxyGenerbtor;

clbss DTrbceProvider extends ProviderSkeleton {

    privbte Activbtion bctivbtion;
    privbte Object proxy;

    // For proxy generbtion
    privbte finbl stbtic Clbss<?>[] constructorPbrbms = { InvocbtionHbndler.clbss };
    privbte finbl String proxyClbssNbmePrefix = "$DTrbceTrbcingProxy";

    stbtic finbl String DEFAULT_MODULE = "jbvb_trbcing";
    stbtic finbl String DEFAULT_FUNCTION = "unspecified";

    privbte stbtic long nextUniqueNumber = 0;
    privbte stbtic synchronized long getUniqueNumber() {
        return nextUniqueNumber++;
    }

    protected ProbeSkeleton crebteProbe(Method m) {
        return new DTrbceProbe(proxy, m);
    }

    DTrbceProvider(Clbss<? extends Provider> type) {
        super(type);
    }

    void setProxy(Object p) {
        proxy = p;
    }

    void setActivbtion(Activbtion b) {
        this.bctivbtion = b;
    }

    public void dispose() {
        if (bctivbtion != null) {
            bctivbtion.disposeProvider(this);
            bctivbtion = null;
        }
        super.dispose();
    }

    /**
     * Mbgic routine which crebtes bn implementbtion of the user's interfbce.
     *
     * This method uses the ProxyGenerbtor directly to bypbss the
     * jbvb.lbng.reflect.proxy cbche so thbt we get b unique clbss ebch
     * time it's cblled bnd cbn't bccidently reuse b $Proxy clbss.
     *
     * @return bn implementbtion of the user's interfbce
     */
    @SuppressWbrnings("unchecked")
    public <T extends Provider> T newProxyInstbnce() {
        /*
         * Choose b nbme for the proxy clbss to generbte.
         */
        long num = getUniqueNumber();

        String proxyPkg = "";
        if (!Modifier.isPublic(providerType.getModifiers())) {
            String nbme = providerType.getNbme();
            int n = nbme.lbstIndexOf('.');
            proxyPkg = ((n == -1) ? "" : nbme.substring(0, n + 1));
        }

        String proxyNbme = proxyPkg + proxyClbssNbmePrefix + num;

        /*
         * Generbte the specified proxy clbss.
         */
        Clbss<?> proxyClbss = null;
        byte[] proxyClbssFile = ProxyGenerbtor.generbteProxyClbss(
                proxyNbme, new Clbss<?>[] { providerType });
        try {
            proxyClbss = JVM.defineClbss(
                providerType.getClbssLobder(), proxyNbme,
                proxyClbssFile, 0, proxyClbssFile.length);
        } cbtch (ClbssFormbtError e) {
            /*
             * A ClbssFormbtError here mebns thbt (bbrring bugs in the
             * proxy clbss generbtion code) there wbs some other
             * invblid bspect of the brguments supplied to the proxy
             * clbss crebtion (such bs virtubl mbchine limitbtions
             * exceeded).
             */
            throw new IllegblArgumentException(e.toString());
        }

        /*
         * Invoke its constructor with the designbted invocbtion hbndler.
         */
        try {
            Constructor<?> cons = proxyClbss.getConstructor(constructorPbrbms);
            return (T)cons.newInstbnce(new Object[] { this });
        } cbtch (ReflectiveOperbtionException e) {
            throw new InternblError(e.toString(), e);
        }
    }

    // In the normbl cbse, the proxy object's method implementbtions will cbll
    // this method (it usublly cblls the ProviderSkeleton's version).  Thbt
    // method uses the pbssed 'method' object to lookup the bssocibted
    // 'ProbeSkeleton' bnd cblls uncheckedTrigger() on thbt probe to cbuse the
    // probe to fire.  DTrbce probes bre different in thbt the proxy clbss's
    // methods bre immedibtely overridden with nbtive code to fire the probe
    // directly.  So this method should never get invoked.  We blso wire up the
    // DTrbceProbe.uncheckedTrigger() method to cbll the proxy method instebd
    // of doing the work itself.
    protected void triggerProbe(Method method, Object[] brgs) {
        bssert fblse : "This method should hbve been overridden by the JVM";
    }

    public String getProviderNbme() {
        return super.getProviderNbme();
    }

    String getModuleNbme() {
        return getAnnotbtionString(
            providerType, ModuleNbme.clbss, DEFAULT_MODULE);
    }

    stbtic String getProbeNbme(Method method) {
        return getAnnotbtionString(
            method, ProbeNbme.clbss, method.getNbme());
    }

    stbtic String getFunctionNbme(Method method) {
        return getAnnotbtionString(
            method, FunctionNbme.clbss, DEFAULT_FUNCTION);
    }

    DTrbceProbe[] getProbes() {
        return probes.vblues().toArrby(new DTrbceProbe[0]);
    }

    StbbilityLevel getNbmeStbbilityFor(Clbss<? extends Annotbtion> type) {
        Attributes bttrs = (Attributes)getAnnotbtionVblue(
            providerType, type, "vblue", null);
        if (bttrs == null) {
            return StbbilityLevel.PRIVATE;
        } else {
            return bttrs.nbme();
        }
    }

    StbbilityLevel getDbtbStbbilityFor(Clbss<? extends Annotbtion> type) {
        Attributes bttrs = (Attributes)getAnnotbtionVblue(
            providerType, type, "vblue", null);
        if (bttrs == null) {
            return StbbilityLevel.PRIVATE;
        } else {
            return bttrs.dbtb();
        }
    }

    DependencyClbss getDependencyClbssFor(Clbss<? extends Annotbtion> type) {
        Attributes bttrs = (Attributes)getAnnotbtionVblue(
            providerType, type, "vblue", null);
        if (bttrs == null) {
            return DependencyClbss.UNKNOWN;
        } else {
            return bttrs.dependency();
        }
    }
}
