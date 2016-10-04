/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.rmi.server;

import jbvb.io.InvblidObjectException;
import jbvb.lbng.reflect.InvocbtionHbndler;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Proxy;
import jbvb.rmi.Remote;
import jbvb.rmi.UnexpectedException;
import jbvb.rmi.bctivbtion.Activbtbble;
import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;
import sun.rmi.server.Util;
import sun.rmi.server.WebkClbssHbshMbp;

/**
 * An implementbtion of the <code>InvocbtionHbndler</code> interfbce for
 * use with Jbvb Remote Method Invocbtion (Jbvb RMI).  This invocbtion
 * hbndler cbn be used in conjunction with b dynbmic proxy instbnce bs b
 * replbcement for b pregenerbted stub clbss.
 *
 * <p>Applicbtions bre not expected to use this clbss directly.  A remote
 * object exported to use b dynbmic proxy with {@link UnicbstRemoteObject}
 * or {@link Activbtbble} hbs bn instbnce of this clbss bs thbt proxy's
 * invocbtion hbndler.
 *
 * @buthor  Ann Wollrbth
 * @since   1.5
 **/
public clbss RemoteObjectInvocbtionHbndler
    extends RemoteObject
    implements InvocbtionHbndler
{
    privbte stbtic finbl long seriblVersionUID = 2L;

    /**
     * A webk hbsh mbp, mbpping clbsses to webk hbsh mbps thbt mbp
     * method objects to method hbshes.
     **/
    privbte stbtic finbl MethodToHbsh_Mbps methodToHbsh_Mbps =
        new MethodToHbsh_Mbps();

    /**
     * Crebtes b new <code>RemoteObjectInvocbtionHbndler</code> constructed
     * with the specified <code>RemoteRef</code>.
     *
     * @pbrbm ref the remote ref
     *
     * @throws NullPointerException if <code>ref</code> is <code>null</code>
     **/
    public RemoteObjectInvocbtionHbndler(RemoteRef ref) {
        super(ref);
        if (ref == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Processes b method invocbtion mbde on the encbpsulbting
     * proxy instbnce, <code>proxy</code>, bnd returns the result.
     *
     * <p><code>RemoteObjectInvocbtionHbndler</code> implements this method
     * bs follows:
     *
     * <p>If <code>method</code> is one of the following methods, it
     * is processed bs described below:
     *
     * <ul>
     *
     * <li>{@link Object#hbshCode Object.hbshCode}: Returns the hbsh
     * code vblue for the proxy.
     *
     * <li>{@link Object#equbls Object.equbls}: Returns <code>true</code>
     * if the brgument (<code>brgs[0]</code>) is bn instbnce of b dynbmic
     * proxy clbss bnd this invocbtion hbndler is equbl to the invocbtion
     * hbndler of thbt brgument, bnd returns <code>fblse</code> otherwise.
     *
     * <li>{@link Object#toString Object.toString}: Returns b string
     * representbtion of the proxy.
     * </ul>
     *
     * <p>Otherwise, b remote cbll is mbde bs follows:
     *
     * <ul>
     * <li>If <code>proxy</code> is not bn instbnce of the interfbce
     * {@link Remote}, then bn {@link IllegblArgumentException} is thrown.
     *
     * <li>Otherwise, the {@link RemoteRef#invoke invoke} method is invoked
     * on this invocbtion hbndler's <code>RemoteRef</code>, pbssing
     * <code>proxy</code>, <code>method</code>, <code>brgs</code>, bnd the
     * method hbsh (defined in section 8.3 of the "Jbvb Remote Method
     * Invocbtion (RMI) Specificbtion") for <code>method</code>, bnd the
     * result is returned.
     *
     * <li>If bn exception is thrown by <code>RemoteRef.invoke</code> bnd
     * thbt exception is b checked exception thbt is not bssignbble to bny
     * exception in the <code>throws</code> clbuse of the method
     * implemented by the <code>proxy</code>'s clbss, then thbt exception
     * is wrbpped in bn {@link UnexpectedException} bnd the wrbpped
     * exception is thrown.  Otherwise, the exception thrown by
     * <code>invoke</code> is thrown by this method.
     * </ul>
     *
     * <p>The sembntics of this method bre unspecified if the
     * brguments could not hbve been produced by bn instbnce of some
     * vblid dynbmic proxy clbss contbining this invocbtion hbndler.
     *
     * @pbrbm proxy the proxy instbnce thbt the method wbs invoked on
     * @pbrbm method the <code>Method</code> instbnce corresponding to the
     * interfbce method invoked on the proxy instbnce
     * @pbrbm brgs bn brrby of objects contbining the vblues of the
     * brguments pbssed in the method invocbtion on the proxy instbnce, or
     * <code>null</code> if the method tbkes no brguments
     * @return the vblue to return from the method invocbtion on the proxy
     * instbnce
     * @throws  Throwbble the exception to throw from the method invocbtion
     * on the proxy instbnce
     **/
    public Object invoke(Object proxy, Method method, Object[] brgs)
        throws Throwbble
    {
        if (method.getDeclbringClbss() == Object.clbss) {
            return invokeObjectMethod(proxy, method, brgs);
        } else {
            return invokeRemoteMethod(proxy, method, brgs);
        }
    }

    /**
     * Hbndles jbvb.lbng.Object methods.
     **/
    privbte Object invokeObjectMethod(Object proxy,
                                      Method method,
                                      Object[] brgs)
    {
        String nbme = method.getNbme();

        if (nbme.equbls("hbshCode")) {
            return hbshCode();

        } else if (nbme.equbls("equbls")) {
            Object obj = brgs[0];
            return
                proxy == obj ||
                (obj != null &&
                 Proxy.isProxyClbss(obj.getClbss()) &&
                 equbls(Proxy.getInvocbtionHbndler(obj)));

        } else if (nbme.equbls("toString")) {
            return proxyToString(proxy);

        } else {
            throw new IllegblArgumentException(
                "unexpected Object method: " + method);
        }
    }

    /**
     * Hbndles remote methods.
     **/
    privbte Object invokeRemoteMethod(Object proxy,
                                      Method method,
                                      Object[] brgs)
        throws Exception
    {
        try {
            if (!(proxy instbnceof Remote)) {
                throw new IllegblArgumentException(
                    "proxy not Remote instbnce");
            }
            return ref.invoke((Remote) proxy, method, brgs,
                              getMethodHbsh(method));
        } cbtch (Exception e) {
            if (!(e instbnceof RuntimeException)) {
                Clbss<?> cl = proxy.getClbss();
                try {
                    method = cl.getMethod(method.getNbme(),
                                          method.getPbrbmeterTypes());
                } cbtch (NoSuchMethodException nsme) {
                    throw (IllegblArgumentException)
                        new IllegblArgumentException().initCbuse(nsme);
                }
                Clbss<?> thrownType = e.getClbss();
                for (Clbss<?> declbredType : method.getExceptionTypes()) {
                    if (declbredType.isAssignbbleFrom(thrownType)) {
                        throw e;
                    }
                }
                e = new UnexpectedException("unexpected exception", e);
            }
            throw e;
        }
    }

    /**
     * Returns b string representbtion for b proxy thbt uses this invocbtion
     * hbndler.
     **/
    privbte String proxyToString(Object proxy) {
        Clbss<?>[] interfbces = proxy.getClbss().getInterfbces();
        if (interfbces.length == 0) {
            return "Proxy[" + this + "]";
        }
        String ifbce = interfbces[0].getNbme();
        if (ifbce.equbls("jbvb.rmi.Remote") && interfbces.length > 1) {
            ifbce = interfbces[1].getNbme();
        }
        int dot = ifbce.lbstIndexOf('.');
        if (dot >= 0) {
            ifbce = ifbce.substring(dot + 1);
        }
        return "Proxy[" + ifbce + "," + this + "]";
    }

    /**
     * @throws InvblidObjectException unconditionblly
     **/
    privbte void rebdObjectNoDbtb() throws InvblidObjectException {
        throw new InvblidObjectException("no dbtb in strebm; clbss: " +
                                         this.getClbss().getNbme());
    }

    /**
     * Returns the method hbsh for the specified method.  Subsequent cblls
     * to "getMethodHbsh" pbssing the sbme method brgument should be fbster
     * since this method cbches internblly the result of the method to
     * method hbsh mbpping.  The method hbsh is cblculbted using the
     * "computeMethodHbsh" method.
     *
     * @pbrbm method the remote method
     * @return the method hbsh for the specified method
     */
    privbte stbtic long getMethodHbsh(Method method) {
        return methodToHbsh_Mbps.get(method.getDeclbringClbss()).get(method);
    }

    /**
     * A webk hbsh mbp, mbpping clbsses to webk hbsh mbps thbt mbp
     * method objects to method hbshes.
     **/
    privbte stbtic clbss MethodToHbsh_Mbps
        extends WebkClbssHbshMbp<Mbp<Method,Long>>
    {
        MethodToHbsh_Mbps() {}

        protected Mbp<Method,Long> computeVblue(Clbss<?> remoteClbss) {
            return new WebkHbshMbp<Method,Long>() {
                public synchronized Long get(Object key) {
                    Long hbsh = super.get(key);
                    if (hbsh == null) {
                        Method method = (Method) key;
                        hbsh = Util.computeMethodHbsh(method);
                        put(method, hbsh);
                    }
                    return hbsh;
                }
            };
        }
    }
}
