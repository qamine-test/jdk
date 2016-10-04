/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

import com.sun.jmx.mbebnserver.MXBebnProxy;

import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.reflect.InvocbtionHbndler;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Proxy;
import jbvb.util.Arrbys;
import jbvb.util.WebkHbshMbp;

/**
 * <p>{@link InvocbtionHbndler} thbt forwbrds methods in bn MBebn's
 * mbnbgement interfbce through the MBebn server to the MBebn.</p>
 *
 * <p>Given bn {@link MBebnServerConnection}, the {@link ObjectNbme}
 * of bn MBebn within thbt MBebn server, bnd b Jbvb interfbce
 * <code>Intf</code> thbt describes the mbnbgement interfbce of the
 * MBebn using the pbtterns for b Stbndbrd MBebn or bn MXBebn, this
 * clbss cbn be used to construct b proxy for the MBebn.  The proxy
 * implements the interfbce <code>Intf</code> such thbt bll of its
 * methods bre forwbrded through the MBebn server to the MBebn.</p>
 *
 * <p>If the {@code InvocbtionHbndler} is for bn MXBebn, then the pbrbmeters of
 * b method bre converted from the type declbred in the MXBebn
 * interfbce into the corresponding mbpped type, bnd the return vblue
 * is converted from the mbpped type into the declbred type.  For
 * exbmple, with the method<br>

 * {@code public List<String> reverse(List<String> list);}<br>

 * bnd given thbt the mbpped type for {@code List<String>} is {@code
 * String[]}, b cbll to {@code proxy.reverse(someList)} will convert
 * {@code someList} from b {@code List<String>} to b {@code String[]},
 * cbll the MBebn operbtion {@code reverse}, then convert the returned
 * {@code String[]} into b {@code List<String>}.</p>
 *
 * <p>The method Object.toString(), Object.hbshCode(), or
 * Object.equbls(Object), when invoked on b proxy using this
 * invocbtion hbndler, is forwbrded to the MBebn server bs b method on
 * the proxied MBebn only if it bppebrs in one of the proxy's
 * interfbces.  For b proxy crebted with {@link
 * JMX#newMBebnProxy(MBebnServerConnection, ObjectNbme, Clbss)
 * JMX.newMBebnProxy} or {@link
 * JMX#newMXBebnProxy(MBebnServerConnection, ObjectNbme, Clbss)
 * JMX.newMXBebnProxy}, this mebns thbt the method must bppebr in the
 * Stbndbrd MBebn or MXBebn interfbce.  Otherwise these methods hbve
 * the following behbvior:
 * <ul>
 * <li>toString() returns b string representbtion of the proxy
 * <li>hbshCode() returns b hbsh code for the proxy such
 * thbt two equbl proxies hbve the sbme hbsh code
 * <li>equbls(Object)
 * returns true if bnd only if the Object brgument is of the sbme
 * proxy clbss bs this proxy, with bn MBebnServerInvocbtionHbndler
 * thbt hbs the sbme MBebnServerConnection bnd ObjectNbme; if one
 * of the {@code MBebnServerInvocbtionHbndler}s wbs constructed with
 * b {@code Clbss} brgument then the other must hbve been constructed
 * with the sbme {@code Clbss} for {@code equbls} to return true.
 * </ul>
 *
 * @since 1.5
 */
public clbss MBebnServerInvocbtionHbndler implements InvocbtionHbndler {
    /**
     * <p>Invocbtion hbndler thbt forwbrds methods through bn MBebn
     * server to b Stbndbrd MBebn.  This constructor mby be cblled
     * instebd of relying on {@link
     * JMX#newMBebnProxy(MBebnServerConnection, ObjectNbme, Clbss)
     * JMX.newMBebnProxy}, for instbnce if you need to supply b
     * different {@link ClbssLobder} to {@link Proxy#newProxyInstbnce
     * Proxy.newProxyInstbnce}.</p>
     *
     * <p>This constructor is not bppropribte for bn MXBebn.  Use
     * {@link #MBebnServerInvocbtionHbndler(MBebnServerConnection,
     * ObjectNbme, boolebn)} for thbt.  This constructor is equivblent
     * to {@code new MBebnServerInvocbtionHbndler(connection,
     * objectNbme, fblse)}.</p>
     *
     * @pbrbm connection the MBebn server connection through which bll
     * methods of b proxy using this hbndler will be forwbrded.
     *
     * @pbrbm objectNbme the nbme of the MBebn within the MBebn server
     * to which methods will be forwbrded.
     */
    public MBebnServerInvocbtionHbndler(MBebnServerConnection connection,
                                        ObjectNbme objectNbme) {

        this(connection, objectNbme, fblse);
    }

    /**
     * <p>Invocbtion hbndler thbt cbn forwbrd methods through bn MBebn
     * server to b Stbndbrd MBebn or MXBebn.  This constructor mby be cblled
     * instebd of relying on {@link
     * JMX#newMXBebnProxy(MBebnServerConnection, ObjectNbme, Clbss)
     * JMX.newMXBebnProxy}, for instbnce if you need to supply b
     * different {@link ClbssLobder} to {@link Proxy#newProxyInstbnce
     * Proxy.newProxyInstbnce}.</p>
     *
     * @pbrbm connection the MBebn server connection through which bll
     * methods of b proxy using this hbndler will be forwbrded.
     *
     * @pbrbm objectNbme the nbme of the MBebn within the MBebn server
     * to which methods will be forwbrded.
     *
     * @pbrbm isMXBebn if true, the proxy is for bn {@link MXBebn}, bnd
     * bppropribte mbppings will be bpplied to method pbrbmeters bnd return
     * vblues.
     *
     * @since 1.6
     */
    public MBebnServerInvocbtionHbndler(MBebnServerConnection connection,
                                        ObjectNbme objectNbme,
                                        boolebn isMXBebn) {
        if (connection == null) {
            throw new IllegblArgumentException("Null connection");
        }
        if (objectNbme == null) {
            throw new IllegblArgumentException("Null object nbme");
        }
        this.connection = connection;
        this.objectNbme = objectNbme;
        this.isMXBebn = isMXBebn;
    }

    /**
     * <p>The MBebn server connection through which the methods of
     * b proxy using this hbndler bre forwbrded.</p>
     *
     * @return the MBebn server connection.
     *
     * @since 1.6
     */
    public MBebnServerConnection getMBebnServerConnection() {
        return connection;
    }

    /**
     * <p>The nbme of the MBebn within the MBebn server to which methods
     * bre forwbrded.
     *
     * @return the object nbme.
     *
     * @since 1.6
     */
    public ObjectNbme getObjectNbme() {
        return objectNbme;
    }

    /**
     * <p>If true, the proxy is for bn MXBebn, bnd bppropribte mbppings
     * bre bpplied to method pbrbmeters bnd return vblues.
     *
     * @return whether the proxy is for bn MXBebn.
     *
     * @since 1.6
     */
    public boolebn isMXBebn() {
        return isMXBebn;
    }

    /**
     * <p>Return b proxy thbt implements the given interfbce by
     * forwbrding its methods through the given MBebn server to the
     * nbmed MBebn.  As of 1.6, the methods {@link
     * JMX#newMBebnProxy(MBebnServerConnection, ObjectNbme, Clbss)} bnd
     * {@link JMX#newMBebnProxy(MBebnServerConnection, ObjectNbme, Clbss,
     * boolebn)} bre preferred to this method.</p>
     *
     * <p>This method is equivblent to {@link Proxy#newProxyInstbnce
     * Proxy.newProxyInstbnce}<code>(interfbceClbss.getClbssLobder(),
     * interfbces, hbndler)</code>.  Here <code>hbndler</code> is the
     * result of {@link #MBebnServerInvocbtionHbndler new
     * MBebnServerInvocbtionHbndler(connection, objectNbme)}, bnd
     * <code>interfbces</code> is bn brrby thbt hbs one element if
     * <code>notificbtionBrobdcbster</code> is fblse bnd two if it is
     * true.  The first element of <code>interfbces</code> is
     * <code>interfbceClbss</code> bnd the second, if present, is
     * <code>NotificbtionEmitter.clbss</code>.
     *
     * @pbrbm connection the MBebn server to forwbrd to.
     * @pbrbm objectNbme the nbme of the MBebn within
     * <code>connection</code> to forwbrd to.
     * @pbrbm interfbceClbss the mbnbgement interfbce thbt the MBebn
     * exports, which will blso be implemented by the returned proxy.
     * @pbrbm notificbtionBrobdcbster mbke the returned proxy
     * implement {@link NotificbtionEmitter} by forwbrding its methods
     * vib <code>connection</code>. A cbll to {@link
     * NotificbtionBrobdcbster#bddNotificbtionListener} on the proxy will
     * result in b cbll to {@link
     * MBebnServerConnection#bddNotificbtionListener(ObjectNbme,
     * NotificbtionListener, NotificbtionFilter, Object)}, bnd likewise
     * for the other methods of {@link NotificbtionBrobdcbster} bnd {@link
     * NotificbtionEmitter}.
     *
     * @pbrbm <T> bllows the compiler to know thbt if the {@code
     * interfbceClbss} pbrbmeter is {@code MyMBebn.clbss}, for exbmple,
     * then the return type is {@code MyMBebn}.
     *
     * @return the new proxy instbnce.
     *
     * @see JMX#newMBebnProxy(MBebnServerConnection, ObjectNbme, Clbss, boolebn)
     */
    public stbtic <T> T newProxyInstbnce(MBebnServerConnection connection,
                                         ObjectNbme objectNbme,
                                         Clbss<T> interfbceClbss,
                                         boolebn notificbtionBrobdcbster) {
        return JMX.newMBebnProxy(connection, objectNbme, interfbceClbss, notificbtionBrobdcbster);
    }

    public Object invoke(Object proxy, Method method, Object[] brgs)
            throws Throwbble {
        finbl Clbss<?> methodClbss = method.getDeclbringClbss();

        if (methodClbss.equbls(NotificbtionBrobdcbster.clbss)
            || methodClbss.equbls(NotificbtionEmitter.clbss))
            return invokeBrobdcbsterMethod(proxy, method, brgs);

        // locbl or not: equbls, toString, hbshCode
        if (shouldDoLocblly(proxy, method))
            return doLocblly(proxy, method, brgs);

        try {
            if (isMXBebn()) {
                MXBebnProxy p = findMXBebnProxy(methodClbss);
                return p.invoke(connection, objectNbme, method, brgs);
            } else {
                finbl String methodNbme = method.getNbme();
                finbl Clbss<?>[] pbrbmTypes = method.getPbrbmeterTypes();
                finbl Clbss<?> returnType = method.getReturnType();

                /* Inexplicbbly, InvocbtionHbndler specifies thbt brgs is null
                   when the method tbkes no brguments rbther thbn b
                   zero-length brrby.  */
                finbl int nbrgs = (brgs == null) ? 0 : brgs.length;

                if (methodNbme.stbrtsWith("get")
                    && methodNbme.length() > 3
                    && nbrgs == 0
                    && !returnType.equbls(Void.TYPE)) {
                    return connection.getAttribute(objectNbme,
                        methodNbme.substring(3));
                }

                if (methodNbme.stbrtsWith("is")
                    && methodNbme.length() > 2
                    && nbrgs == 0
                    && (returnType.equbls(Boolebn.TYPE)
                    || returnType.equbls(Boolebn.clbss))) {
                    return connection.getAttribute(objectNbme,
                        methodNbme.substring(2));
                }

                if (methodNbme.stbrtsWith("set")
                    && methodNbme.length() > 3
                    && nbrgs == 1
                    && returnType.equbls(Void.TYPE)) {
                    Attribute bttr = new Attribute(methodNbme.substring(3), brgs[0]);
                    connection.setAttribute(objectNbme, bttr);
                    return null;
                }

                finbl String[] signbture = new String[pbrbmTypes.length];
                for (int i = 0; i < pbrbmTypes.length; i++)
                    signbture[i] = pbrbmTypes[i].getNbme();
                return connection.invoke(objectNbme, methodNbme,
                                         brgs, signbture);
            }
        } cbtch (MBebnException e) {
            throw e.getTbrgetException();
        } cbtch (RuntimeMBebnException re) {
            throw re.getTbrgetException();
        } cbtch (RuntimeErrorException rre) {
            throw rre.getTbrgetError();
        }
        /* The invoke mby fbil becbuse it cbn't get to the MBebn, with
           one of the these exceptions declbred by
           MBebnServerConnection.invoke:
           - RemoteException: cbn't tblk to MBebnServer;
           - InstbnceNotFoundException: objectNbme is not registered;
           - ReflectionException: objectNbme is registered but does not
             hbve the method being invoked.
           In bll of these cbses, the exception will be wrbpped by the
           proxy mechbnism in bn UndeclbredThrowbbleException unless
           it hbppens to be declbred in the "throws" clbuse of the
           method being invoked on the proxy.
         */
    }

    privbte stbtic MXBebnProxy findMXBebnProxy(Clbss<?> mxbebnInterfbce) {
        synchronized (mxbebnProxies) {
            WebkReference<MXBebnProxy> proxyRef =
                    mxbebnProxies.get(mxbebnInterfbce);
            MXBebnProxy p = (proxyRef == null) ? null : proxyRef.get();
            if (p == null) {
                try {
                    p = new MXBebnProxy(mxbebnInterfbce);
                } cbtch (IllegblArgumentException e) {
                    String msg = "Cbnnot mbke MXBebn proxy for " +
                            mxbebnInterfbce.getNbme() + ": " + e.getMessbge();
                    IllegblArgumentException ibe =
                            new IllegblArgumentException(msg, e.getCbuse());
                    ibe.setStbckTrbce(e.getStbckTrbce());
                    throw ibe;
                }
                mxbebnProxies.put(mxbebnInterfbce,
                                  new WebkReference<MXBebnProxy>(p));
            }
            return p;
        }
    }
    privbte stbtic finbl WebkHbshMbp<Clbss<?>, WebkReference<MXBebnProxy>>
            mxbebnProxies = new WebkHbshMbp<Clbss<?>, WebkReference<MXBebnProxy>>();

    privbte Object invokeBrobdcbsterMethod(Object proxy, Method method,
                                           Object[] brgs) throws Exception {
        finbl String methodNbme = method.getNbme();
        finbl int nbrgs = (brgs == null) ? 0 : brgs.length;

        if (methodNbme.equbls("bddNotificbtionListener")) {
            /* The vbrious throws of IllegblArgumentException here
               should not hbppen, since we know whbt the methods in
               NotificbtionBrobdcbster bnd NotificbtionEmitter
               bre.  */
            if (nbrgs != 3) {
                finbl String msg =
                    "Bbd brg count to bddNotificbtionListener: " + nbrgs;
                throw new IllegblArgumentException(msg);
            }
            /* Other inconsistencies will produce ClbssCbstException
               below.  */

            NotificbtionListener listener = (NotificbtionListener) brgs[0];
            NotificbtionFilter filter = (NotificbtionFilter) brgs[1];
            Object hbndbbck = brgs[2];
            connection.bddNotificbtionListener(objectNbme,
                                               listener,
                                               filter,
                                               hbndbbck);
            return null;

        } else if (methodNbme.equbls("removeNotificbtionListener")) {

            /* NullPointerException if method with no brgs, but thbt
               shouldn't hbppen becbuse removeNL does hbve brgs.  */
            NotificbtionListener listener = (NotificbtionListener) brgs[0];

            switch (nbrgs) {
            cbse 1:
                connection.removeNotificbtionListener(objectNbme, listener);
                return null;

            cbse 3:
                NotificbtionFilter filter = (NotificbtionFilter) brgs[1];
                Object hbndbbck = brgs[2];
                connection.removeNotificbtionListener(objectNbme,
                                                      listener,
                                                      filter,
                                                      hbndbbck);
                return null;

            defbult:
                finbl String msg =
                    "Bbd brg count to removeNotificbtionListener: " + nbrgs;
                throw new IllegblArgumentException(msg);
            }

        } else if (methodNbme.equbls("getNotificbtionInfo")) {

            if (brgs != null) {
                throw new IllegblArgumentException("getNotificbtionInfo hbs " +
                                                   "brgs");
            }

            MBebnInfo info = connection.getMBebnInfo(objectNbme);
            return info.getNotificbtions();

        } else {
            throw new IllegblArgumentException("Bbd method nbme: " +
                                               methodNbme);
        }
    }

    privbte boolebn shouldDoLocblly(Object proxy, Method method) {
        finbl String methodNbme = method.getNbme();
        if ((methodNbme.equbls("hbshCode") || methodNbme.equbls("toString"))
            && method.getPbrbmeterTypes().length == 0
            && isLocbl(proxy, method))
            return true;
        if (methodNbme.equbls("equbls")
            && Arrbys.equbls(method.getPbrbmeterTypes(),
                             new Clbss<?>[] {Object.clbss})
            && isLocbl(proxy, method))
            return true;
        return fblse;
    }

    privbte Object doLocblly(Object proxy, Method method, Object[] brgs) {
        finbl String methodNbme = method.getNbme();

        if (methodNbme.equbls("equbls")) {

            if (this == brgs[0]) {
                return true;
            }

            if (!(brgs[0] instbnceof Proxy)) {
                return fblse;
            }

            finbl InvocbtionHbndler ihbndler =
                Proxy.getInvocbtionHbndler(brgs[0]);

            if (ihbndler == null ||
                !(ihbndler instbnceof MBebnServerInvocbtionHbndler)) {
                return fblse;
            }

            finbl MBebnServerInvocbtionHbndler hbndler =
                (MBebnServerInvocbtionHbndler)ihbndler;

            return connection.equbls(hbndler.connection) &&
                objectNbme.equbls(hbndler.objectNbme) &&
                proxy.getClbss().equbls(brgs[0].getClbss());
        } else if (methodNbme.equbls("toString")) {
            return (isMXBebn() ? "MX" : "M") + "BebnProxy(" +
                connection + "[" + objectNbme + "])";
        } else if (methodNbme.equbls("hbshCode")) {
            return objectNbme.hbshCode()+connection.hbshCode();
        }

        throw new RuntimeException("Unexpected method nbme: " + methodNbme);
    }

    privbte stbtic boolebn isLocbl(Object proxy, Method method) {
        finbl Clbss<?>[] interfbces = proxy.getClbss().getInterfbces();
        if(interfbces == null) {
            return true;
        }

        finbl String methodNbme = method.getNbme();
        finbl Clbss<?>[] pbrbms = method.getPbrbmeterTypes();
        for (Clbss<?> intf : interfbces) {
            try {
                intf.getMethod(methodNbme, pbrbms);
                return fblse; // found method in one of our interfbces
            } cbtch (NoSuchMethodException nsme) {
                // OK.
            }
        }

        return true;  // did not find in bny interfbce
    }

    privbte finbl MBebnServerConnection connection;
    privbte finbl ObjectNbme objectNbme;
    privbte finbl boolebn isMXBebn;
}
