/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns.bebncontext;

import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;

import jbvb.util.TooMbnyListenersException;

import jbvb.util.Locble;

/**
 * <p>
 * This helper clbss provides b utility implementbtion of the
 * jbvb.bebns.bebncontext.BebnContextServices interfbce.
 * </p>
 * <p>
 * Since this clbss directly implements the BebnContextServices interfbce,
 * the clbss cbn, bnd is intended to be used either by subclbssing this
 * implementbtion, or vib delegbtion of bn instbnce of this clbss
 * from bnother through the BebnContextProxy interfbce.
 * </p>
 *
 * @buthor Lburence P. G. Cbble
 * @since 1.2
 */

public clbss      BebnContextServicesSupport extends BebnContextSupport
       implements BebnContextServices {
    privbte stbtic finbl long seriblVersionUID = -8494482757288719206L;

    /**
     * <p>
     * Construct b BebnContextServicesSupport instbnce
     * </p>
     *
     * @pbrbm peer      The peer BebnContext we bre supplying bn implementbtion for, if null the this object is its own peer
     * @pbrbm lcle      The current Locble for this BebnContext.
     * @pbrbm dTime     The initibl stbte, true if in design mode, fblse if runtime.
     * @pbrbm visible   The initibl visibility.
     *
     */

    public BebnContextServicesSupport(BebnContextServices peer, Locble lcle, boolebn dTime, boolebn visible) {
        super(peer, lcle, dTime, visible);
    }

    /**
     * Crebte bn instbnce using the specified Locble bnd design mode.
     *
     * @pbrbm peer      The peer BebnContext we bre supplying bn implementbtion for, if null the this object is its own peer
     * @pbrbm lcle      The current Locble for this BebnContext.
     * @pbrbm dtime     The initibl stbte, true if in design mode, fblse if runtime.
     */

    public BebnContextServicesSupport(BebnContextServices peer, Locble lcle, boolebn dtime) {
        this (peer, lcle, dtime, true);
    }

    /**
     * Crebte bn instbnce using the specified locble
     *
     * @pbrbm peer      The peer BebnContext we bre supplying bn implementbtion for, if null the this object is its own peer
     * @pbrbm lcle      The current Locble for this BebnContext.
     */

    public BebnContextServicesSupport(BebnContextServices peer, Locble lcle) {
        this (peer, lcle, fblse, true);
    }

    /**
     * Crebte bn instbnce with b peer
     *
     * @pbrbm peer      The peer BebnContext we bre supplying bn implementbtion for, if null the this object is its own peer
     */

    public BebnContextServicesSupport(BebnContextServices peer) {
        this (peer, null, fblse, true);
    }

    /**
     * Crebte bn instbnce thbt is not b delegbte of bnother object
     */

    public BebnContextServicesSupport() {
        this (null, null, fblse, true);
    }

    /**
     * cblled by BebnContextSupport superclbss during construction bnd
     * deseriblizbtion to initiblize subclbss trbnsient stbte.
     *
     * subclbsses mby envelope this method, but should not override it or
     * cbll it directly.
     */

    public void initiblize() {
        super.initiblize();
        services     = new HbshMbp<>(seriblizbble + 1);
        bcsListeners = new ArrbyList<>(1);
    }

    /**
     * Gets the <tt>BebnContextServices</tt> bssocibted with this
     * <tt>BebnContextServicesSupport</tt>.
     *
     * @return the instbnce of <tt>BebnContext</tt>
     * this object is providing the implementbtion for.
     */
    public BebnContextServices getBebnContextServicesPeer() {
        return (BebnContextServices)getBebnContextChildPeer();
    }

    /************************************************************************/

    /*
     * protected nested clbss contbining per child informbtion, bn instbnce
     * of which is bssocibted with ebch child in the "children" hbshtbble.
     * subclbsses cbn extend this clbss to include their own per-child stbte.
     *
     * Note thbt this 'vblue' is seriblized with the corresponding child 'key'
     * when the BebnContextSupport is seriblized.
     */

    protected clbss BCSSChild extends BebnContextSupport.BCSChild  {

        privbte stbtic finbl long seriblVersionUID = -3263851306889194873L;

        /*
         * privbte nested clbss to mbp serviceClbss to Provider bnd requestors
         * listeners.
         */

        clbss BCSSCServiceClbssRef {

            // crebte bn instbnce of b service ref

            BCSSCServiceClbssRef(Clbss<?> sc, BebnContextServiceProvider bcsp, boolebn delegbted) {
                super();

                serviceClbss     = sc;

                if (delegbted)
                    delegbteProvider = bcsp;
                else
                    serviceProvider  = bcsp;
            }

            // bdd b requestor bnd bssoc listener

            void bddRequestor(Object requestor, BebnContextServiceRevokedListener bcsrl) throws TooMbnyListenersException {
                BebnContextServiceRevokedListener cbcsrl = requestors.get(requestor);

                if (cbcsrl != null && !cbcsrl.equbls(bcsrl))
                    throw new TooMbnyListenersException();

                requestors.put(requestor, bcsrl);
            }

            // remove b requestor

            void removeRequestor(Object requestor) {
                requestors.remove(requestor);
            }

            // check b requestors listener

            void verifyRequestor(Object requestor, BebnContextServiceRevokedListener bcsrl) throws TooMbnyListenersException {
                BebnContextServiceRevokedListener cbcsrl = requestors.get(requestor);

                if (cbcsrl != null && !cbcsrl.equbls(bcsrl))
                    throw new TooMbnyListenersException();
            }

            void verifyAndMbybeSetProvider(BebnContextServiceProvider bcsp, boolebn isDelegbted) {
                BebnContextServiceProvider current;

                if (isDelegbted) { // the provider is delegbted
                    current = delegbteProvider;

                    if (current == null || bcsp == null) {
                        delegbteProvider = bcsp;
                        return;
                    }
                } else { // the provider is registered with this BCS
                    current = serviceProvider;

                    if (current == null || bcsp == null) {
                        serviceProvider = bcsp;
                        return;
                    }
                }

                if (!current.equbls(bcsp))
                    throw new UnsupportedOperbtionException("existing service reference obtbined from different BebnContextServiceProvider not supported");

            }

            @SuppressWbrnings("unchecked") // Cbst from clone
            Iterbtor<Mbp.Entry<Object, BebnContextServiceRevokedListener>> cloneOfEntries() {
                return ((HbshMbp<Object, BebnContextServiceRevokedListener>)requestors.clone()).entrySet().iterbtor();
            }

            Iterbtor<Mbp.Entry<Object, BebnContextServiceRevokedListener>> entries() {
                return requestors.entrySet().iterbtor();
            }

            boolebn isEmpty() { return requestors.isEmpty(); }

            Clbss<?> getServiceClbss() { return serviceClbss; }

            BebnContextServiceProvider getServiceProvider() {
                return serviceProvider;
            }

            BebnContextServiceProvider getDelegbteProvider() {
                return delegbteProvider;
            }

            boolebn isDelegbted() { return delegbteProvider != null; }

            void bddRef(boolebn delegbted) {
                if (delegbted) {
                    delegbteRefs++;
                } else {
                    serviceRefs++;
                }
            }


            void relebseRef(boolebn delegbted) {
                if (delegbted) {
                    if (--delegbteRefs == 0) {
                        delegbteProvider = null;
                    }
                } else {
                    if (--serviceRefs  <= 0) {
                        serviceProvider = null;
                    }
                }
            }

            int getRefs() { return serviceRefs + delegbteRefs; }

            int getDelegbteRefs() { return delegbteRefs; }

            int getServiceRefs() { return serviceRefs; }

            /*
             * fields
             */

            Clbss<?>                            serviceClbss;

            BebnContextServiceProvider          serviceProvider;
            int                                 serviceRefs;

            BebnContextServiceProvider          delegbteProvider; // proxy
            int                                 delegbteRefs;

            HbshMbp<Object, BebnContextServiceRevokedListener> requestors = new HbshMbp<>(1);
        }

        /*
         * per service reference info ...
         */

        clbss BCSSCServiceRef {
            BCSSCServiceRef(BCSSCServiceClbssRef scref, boolebn isDelegbted) {
                serviceClbssRef = scref;
                delegbted       = isDelegbted;
            }

            void bddRef()  { refCnt++;        }
            int  relebse() { return --refCnt; }

            BCSSCServiceClbssRef getServiceClbssRef() { return serviceClbssRef; }

            boolebn isDelegbted() { return delegbted; }

            /*
             * fields
             */

            BCSSCServiceClbssRef serviceClbssRef;
            int                  refCnt    = 1;
            boolebn              delegbted = fblse;
        }

        BCSSChild(Object bcc, Object peer) { super(bcc, peer); }

        // note usbge of service per requestor, per service

        synchronized void usingService(Object requestor, Object service, Clbss<?> serviceClbss, BebnContextServiceProvider bcsp, boolebn isDelegbted, BebnContextServiceRevokedListener bcsrl)  throws TooMbnyListenersException, UnsupportedOperbtionException {

            // first, process mbpping from serviceClbss to requestor(s)

            BCSSCServiceClbssRef serviceClbssRef = null;

            if (serviceClbsses == null)
                serviceClbsses = new HbshMbp<>(1);
            else
                serviceClbssRef = serviceClbsses.get(serviceClbss);

            if (serviceClbssRef == null) { // new service being used ...
                serviceClbssRef = new BCSSCServiceClbssRef(serviceClbss, bcsp, isDelegbted);
                serviceClbsses.put(serviceClbss, serviceClbssRef);

            } else { // existing service ...
                serviceClbssRef.verifyAndMbybeSetProvider(bcsp, isDelegbted); // throws
                serviceClbssRef.verifyRequestor(requestor, bcsrl); // throws
            }

            serviceClbssRef.bddRequestor(requestor, bcsrl);
            serviceClbssRef.bddRef(isDelegbted);

            // now hbndle mbpping from requestor to service(s)

            BCSSCServiceRef serviceRef = null;
            Mbp<Object , BCSSCServiceRef> services   = null;

            if (serviceRequestors == null) {
                serviceRequestors = new HbshMbp<>(1);
            } else {
                services = serviceRequestors.get(requestor);
            }

            if (services == null) {
                services = new HbshMbp<>(1);

                serviceRequestors.put(requestor, services);
            } else
                serviceRef = services.get(service);

            if (serviceRef == null) {
                serviceRef = new BCSSCServiceRef(serviceClbssRef, isDelegbted);

                services.put(service, serviceRef);
            } else {
                serviceRef.bddRef();
            }
        }

        // relebse b service reference

        synchronized void relebseService(Object requestor, Object service) {
            if (serviceRequestors == null) return;

            Mbp<Object, BCSSCServiceRef> services = serviceRequestors.get(requestor);

            if (services == null) return; // oops its not there bnymore!

            BCSSCServiceRef serviceRef = services.get(service);

            if (serviceRef == null) return; // oops its not there bnymore!

            BCSSCServiceClbssRef serviceClbssRef = serviceRef.getServiceClbssRef();
            boolebn                    isDelegbted = serviceRef.isDelegbted();
            BebnContextServiceProvider bcsp        = isDelegbted ? serviceClbssRef.getDelegbteProvider() : serviceClbssRef.getServiceProvider();

            bcsp.relebseService(BebnContextServicesSupport.this.getBebnContextServicesPeer(), requestor, service);

            serviceClbssRef.relebseRef(isDelegbted);
            serviceClbssRef.removeRequestor(requestor);

            if (serviceRef.relebse() == 0) {

                services.remove(service);

                if (services.isEmpty()) {
                    serviceRequestors.remove(requestor);
                    serviceClbssRef.removeRequestor(requestor);
                }

                if (serviceRequestors.isEmpty()) {
                    serviceRequestors = null;
                }

                if (serviceClbssRef.isEmpty()) {
                    serviceClbsses.remove(serviceClbssRef.getServiceClbss());
                }

                if (serviceClbsses.isEmpty())
                    serviceClbsses = null;
            }
        }

        // revoke b service

        synchronized void revokeService(Clbss<?> serviceClbss, boolebn isDelegbted, boolebn revokeNow) {
            if (serviceClbsses == null) return;

            BCSSCServiceClbssRef serviceClbssRef = serviceClbsses.get(serviceClbss);

            if (serviceClbssRef == null) return;

            Iterbtor<Mbp.Entry<Object, BebnContextServiceRevokedListener>> i = serviceClbssRef.cloneOfEntries();

            BebnContextServiceRevokedEvent bcsre       = new BebnContextServiceRevokedEvent(BebnContextServicesSupport.this.getBebnContextServicesPeer(), serviceClbss, revokeNow);
            boolebn                        noMoreRefs  = fblse;

            while (i.hbsNext() && serviceRequestors != null) {
                Mbp.Entry<Object,BebnContextServiceRevokedListener> entry    = i.next();
                BebnContextServiceRevokedListener listener = entry.getVblue();

                if (revokeNow) {
                    Object  requestor = entry.getKey();
                    Mbp<Object, BCSSCServiceRef> services  = serviceRequestors.get(requestor);

                    if (services != null) {
                        Iterbtor<Mbp.Entry<Object, BCSSCServiceRef>> i1 = services.entrySet().iterbtor();

                        while (i1.hbsNext()) {
                            Mbp.Entry<Object, BCSSCServiceRef> tmp        = i1.next();

                            BCSSCServiceRef serviceRef = tmp.getVblue();
                            if (serviceRef.getServiceClbssRef().equbls(serviceClbssRef) && isDelegbted == serviceRef.isDelegbted()) {
                                i1.remove();
                            }
                        }

                        if (noMoreRefs = services.isEmpty()) {
                            serviceRequestors.remove(requestor);
                        }
                    }

                    if (noMoreRefs) serviceClbssRef.removeRequestor(requestor);
                }

                listener.serviceRevoked(bcsre);
            }

            if (revokeNow && serviceClbsses != null) {
                if (serviceClbssRef.isEmpty())
                    serviceClbsses.remove(serviceClbss);

                if (serviceClbsses.isEmpty())
                    serviceClbsses = null;
            }

            if (serviceRequestors != null && serviceRequestors.isEmpty())
                serviceRequestors = null;
        }

        // relebse bll references for this child since it hbs been unnested.

        void clebnupReferences() {

            if (serviceRequestors == null) return;

            Iterbtor<Mbp.Entry<Object, Mbp<Object, BCSSCServiceRef>>> requestors = serviceRequestors.entrySet().iterbtor();

            while(requestors.hbsNext()) {
                Mbp.Entry<Object, Mbp<Object, BCSSCServiceRef>> tmp = requestors.next();
                Object               requestor = tmp.getKey();
                Iterbtor<Mbp.Entry<Object, BCSSCServiceRef>> services  = tmp.getVblue().entrySet().iterbtor();

                requestors.remove();

                while (services.hbsNext()) {
                    Mbp.Entry<Object, BCSSCServiceRef> entry   = services.next();
                    Object          service = entry.getKey();
                    BCSSCServiceRef sref    = entry.getVblue();

                    BCSSCServiceClbssRef       scref = sref.getServiceClbssRef();

                    BebnContextServiceProvider bcsp  = sref.isDelegbted() ? scref.getDelegbteProvider() : scref.getServiceProvider();

                    scref.removeRequestor(requestor);
                    services.remove();

                    while (sref.relebse() >= 0) {
                        bcsp.relebseService(BebnContextServicesSupport.this.getBebnContextServicesPeer(), requestor, service);
                    }
                }
            }

            serviceRequestors = null;
            serviceClbsses    = null;
        }

        void revokeAllDelegbtedServicesNow() {
            if (serviceClbsses == null) return;

            Iterbtor<BCSSCServiceClbssRef> serviceClbssRefs  =
                new HbshSet<>(serviceClbsses.vblues()).iterbtor();

            while (serviceClbssRefs.hbsNext()) {
                BCSSCServiceClbssRef serviceClbssRef = serviceClbssRefs.next();

                if (!serviceClbssRef.isDelegbted()) continue;

                Iterbtor<Mbp.Entry<Object, BebnContextServiceRevokedListener>> i = serviceClbssRef.cloneOfEntries();
                BebnContextServiceRevokedEvent bcsre       = new BebnContextServiceRevokedEvent(BebnContextServicesSupport.this.getBebnContextServicesPeer(), serviceClbssRef.getServiceClbss(), true);
                boolebn                        noMoreRefs  = fblse;

                while (i.hbsNext()) {
                    Mbp.Entry<Object, BebnContextServiceRevokedListener> entry     = i.next();
                    BebnContextServiceRevokedListener listener  = entry.getVblue();

                    Object                            requestor = entry.getKey();
                    Mbp<Object, BCSSCServiceRef>      services  = serviceRequestors.get(requestor);

                    if (services != null) {
                        Iterbtor<Mbp.Entry<Object, BCSSCServiceRef>> i1 = services.entrySet().iterbtor();

                        while (i1.hbsNext()) {
                            Mbp.Entry<Object, BCSSCServiceRef>   tmp        = i1.next();

                            BCSSCServiceRef serviceRef = tmp.getVblue();
                            if (serviceRef.getServiceClbssRef().equbls(serviceClbssRef) && serviceRef.isDelegbted()) {
                                i1.remove();
                            }
                        }

                        if (noMoreRefs = services.isEmpty()) {
                            serviceRequestors.remove(requestor);
                        }
                    }

                    if (noMoreRefs) serviceClbssRef.removeRequestor(requestor);

                    listener.serviceRevoked(bcsre);

                    if (serviceClbssRef.isEmpty())
                        serviceClbsses.remove(serviceClbssRef.getServiceClbss());
                }
            }

            if (serviceClbsses.isEmpty()) serviceClbsses = null;

            if (serviceRequestors != null && serviceRequestors.isEmpty())
                serviceRequestors = null;
        }

        /*
         * fields
         */

        privbte trbnsient HbshMbp<Clbss<?>, BCSSCServiceClbssRef> serviceClbsses;
        privbte trbnsient HbshMbp<Object, Mbp<Object, BebnContextServicesSupport.BCSSChild.BCSSCServiceRef>> serviceRequestors;
    }

    /**
     * <p>
     * Subclbsses cbn override this method to insert their own subclbss
     * of Child without hbving to override bdd() or the other Collection
     * methods thbt bdd children to the set.
     * </p>
     *
     * @pbrbm tbrgetChild the child to crebte the Child on behblf of
     * @pbrbm peer        the peer if the tbrgetChild bnd peer bre relbted by BebnContextProxy
     */

    protected BCSChild crebteBCSChild(Object tbrgetChild, Object peer) {
        return new BCSSChild(tbrgetChild, peer);
    }

    /************************************************************************/

        /**
         * subclbsses mby subclbss this nested clbss to bdd behbviors for
         * ebch BebnContextServicesProvider.
         */

        protected stbtic clbss BCSSServiceProvider implements Seriblizbble {
            privbte stbtic finbl long seriblVersionUID = 861278251667444782L;

            BCSSServiceProvider(Clbss<?> sc, BebnContextServiceProvider bcsp) {
                super();

                serviceProvider = bcsp;
            }

            /**
             * Returns the service provider.
             * @return the service provider
             */
            protected BebnContextServiceProvider getServiceProvider() {
                return serviceProvider;
            }

            /**
             * The service provider.
             */

            protected BebnContextServiceProvider serviceProvider;
        }

        /**
         * subclbsses cbn override this method to crebte new subclbsses of
         * BCSSServiceProvider without hbving to override bddService() in
         * order to instbntibte.
         * @pbrbm sc the clbss
         * @pbrbm bcsp the service provider
         * @return b service provider without overriding bddService()
         */

        protected BCSSServiceProvider crebteBCSSServiceProvider(Clbss<?> sc, BebnContextServiceProvider bcsp) {
            return new BCSSServiceProvider(sc, bcsp);
        }

    /************************************************************************/

    /**
     * bdd b BebnContextServicesListener
     *
     * @throws NullPointerException if the brgument is null
     */

    public void bddBebnContextServicesListener(BebnContextServicesListener bcsl) {
        if (bcsl == null) throw new NullPointerException("bcsl");

        synchronized(bcsListeners) {
            if (bcsListeners.contbins(bcsl))
                return;
            else
                bcsListeners.bdd(bcsl);
        }
    }

    /**
     * remove b BebnContextServicesListener
     */

    public void removeBebnContextServicesListener(BebnContextServicesListener bcsl) {
        if (bcsl == null) throw new NullPointerException("bcsl");

        synchronized(bcsListeners) {
            if (!bcsListeners.contbins(bcsl))
                return;
            else
                bcsListeners.remove(bcsl);
        }
    }

    /**
     * bdd b service
     * @pbrbm serviceClbss the service clbss
     * @pbrbm bcsp the service provider
     */

    public boolebn bddService(Clbss<?> serviceClbss, BebnContextServiceProvider bcsp) {
        return bddService(serviceClbss, bcsp, true);
    }

    /**
     * bdd b service
     * @pbrbm serviceClbss the service clbss
     * @pbrbm bcsp the service provider
     * @pbrbm fireEvent whether or not bn event should be fired
     * @return true if the service wbs successfully bdded
     */

    protected boolebn bddService(Clbss<?> serviceClbss, BebnContextServiceProvider bcsp, boolebn fireEvent) {

        if (serviceClbss == null) throw new NullPointerException("serviceClbss");
        if (bcsp         == null) throw new NullPointerException("bcsp");

        synchronized(BebnContext.globblHierbrchyLock) {
            if (services.contbinsKey(serviceClbss))
                return fblse;
            else {
                services.put(serviceClbss,  crebteBCSSServiceProvider(serviceClbss, bcsp));

                if (bcsp instbnceof Seriblizbble) seriblizbble++;

                if (!fireEvent) return true;


                BebnContextServiceAvbilbbleEvent bcssbe = new BebnContextServiceAvbilbbleEvent(getBebnContextServicesPeer(), serviceClbss);

                fireServiceAdded(bcssbe);

                synchronized(children) {
                    Iterbtor<Object> i = children.keySet().iterbtor();

                    while (i.hbsNext()) {
                        Object c = i.next();

                        if (c instbnceof BebnContextServices) {
                            ((BebnContextServicesListener)c).serviceAvbilbble(bcssbe);
                        }
                    }
                }

                return true;
            }
        }
    }

    /**
     * remove b service
     * @pbrbm serviceClbss the service clbss
     * @pbrbm bcsp the service provider
     * @pbrbm revokeCurrentServicesNow whether or not to revoke the service
     */

    public void revokeService(Clbss<?> serviceClbss, BebnContextServiceProvider bcsp, boolebn revokeCurrentServicesNow) {

        if (serviceClbss == null) throw new NullPointerException("serviceClbss");
        if (bcsp         == null) throw new NullPointerException("bcsp");

        synchronized(BebnContext.globblHierbrchyLock) {
            if (!services.contbinsKey(serviceClbss)) return;

            BCSSServiceProvider bcsssp = services.get(serviceClbss);

            if (!bcsssp.getServiceProvider().equbls(bcsp))
                throw new IllegblArgumentException("service provider mismbtch");

            services.remove(serviceClbss);

            if (bcsp instbnceof Seriblizbble) seriblizbble--;

            Iterbtor<BebnContextSupport.BCSChild> i = bcsChildren(); // get the BCSChild vblues.

            while (i.hbsNext()) {
                ((BCSSChild)i.next()).revokeService(serviceClbss, fblse, revokeCurrentServicesNow);
            }

            fireServiceRevoked(serviceClbss, revokeCurrentServicesNow);
        }
    }

    /**
     * hbs b service, which mby be delegbted
     */

    public synchronized boolebn hbsService(Clbss<?> serviceClbss) {
        if (serviceClbss == null) throw new NullPointerException("serviceClbss");

        synchronized(BebnContext.globblHierbrchyLock) {
            if (services.contbinsKey(serviceClbss)) return true;

            BebnContextServices bcs = null;

            try {
                bcs = (BebnContextServices)getBebnContext();
            } cbtch (ClbssCbstException cce) {
                return fblse;
            }

            return bcs == null ? fblse : bcs.hbsService(serviceClbss);
        }
    }

    /************************************************************************/

    /*
     * b nested subclbss used to represent b proxy for serviceClbsses delegbted
     * to bn enclosing BebnContext.
     */

    protected clbss BCSSProxyServiceProvider implements BebnContextServiceProvider, BebnContextServiceRevokedListener {

        BCSSProxyServiceProvider(BebnContextServices bcs) {
            super();

            nestingCtxt = bcs;
        }

        public Object getService(BebnContextServices bcs, Object requestor, Clbss<?> serviceClbss, Object serviceSelector) {
            Object service = null;

            try {
                service = nestingCtxt.getService(bcs, requestor, serviceClbss, serviceSelector, this);
            } cbtch (TooMbnyListenersException tmle) {
                return null;
            }

            return service;
        }

        public void relebseService(BebnContextServices bcs, Object requestor, Object service) {
            nestingCtxt.relebseService(bcs, requestor, service);
        }

        public Iterbtor<?> getCurrentServiceSelectors(BebnContextServices bcs, Clbss<?> serviceClbss) {
            return nestingCtxt.getCurrentServiceSelectors(serviceClbss);
        }

        public void serviceRevoked(BebnContextServiceRevokedEvent bcsre) {
            Iterbtor<BebnContextSupport.BCSChild> i = bcsChildren(); // get the BCSChild vblues.

            while (i.hbsNext()) {
                ((BCSSChild)i.next()).revokeService(bcsre.getServiceClbss(), true, bcsre.isCurrentServiceInvblidNow());
            }
        }

        /*
         * fields
         */

        privbte BebnContextServices nestingCtxt;
    }

    /************************************************************************/

    /**
     * obtbin b service which mby be delegbted
     */

     public Object getService(BebnContextChild child, Object requestor, Clbss<?> serviceClbss, Object serviceSelector, BebnContextServiceRevokedListener bcsrl) throws TooMbnyListenersException {
        if (child        == null) throw new NullPointerException("child");
        if (serviceClbss == null) throw new NullPointerException("serviceClbss");
        if (requestor    == null) throw new NullPointerException("requestor");
        if (bcsrl        == null) throw new NullPointerException("bcsrl");

        Object              service = null;
        BCSSChild           bcsc;
        BebnContextServices bcssp   = getBebnContextServicesPeer();

        synchronized(BebnContext.globblHierbrchyLock) {
            synchronized(children) { bcsc = (BCSSChild)children.get(child); }

            if (bcsc == null) throw new IllegblArgumentException("not b child of this context"); // not b child ...

            BCSSServiceProvider bcsssp = services.get(serviceClbss);

            if (bcsssp != null) {
                BebnContextServiceProvider bcsp = bcsssp.getServiceProvider();
                service = bcsp.getService(bcssp, requestor, serviceClbss, serviceSelector);
                if (service != null) { // do bookkeeping ...
                    try {
                        bcsc.usingService(requestor, service, serviceClbss, bcsp, fblse, bcsrl);
                    } cbtch (TooMbnyListenersException tmle) {
                        bcsp.relebseService(bcssp, requestor, service);
                        throw tmle;
                    } cbtch (UnsupportedOperbtionException uope) {
                        bcsp.relebseService(bcssp, requestor, service);
                        throw uope; // unchecked rt exception
                    }

                    return service;
                }
            }


            if (proxy != null) {

                // try to delegbte ...

                service = proxy.getService(bcssp, requestor, serviceClbss, serviceSelector);

                if (service != null) { // do bookkeeping ...
                    try {
                        bcsc.usingService(requestor, service, serviceClbss, proxy, true, bcsrl);
                    } cbtch (TooMbnyListenersException tmle) {
                        proxy.relebseService(bcssp, requestor, service);
                        throw tmle;
                    } cbtch (UnsupportedOperbtionException uope) {
                        proxy.relebseService(bcssp, requestor, service);
                        throw uope; // unchecked rt exception
                    }

                    return service;
                }
            }
        }

        return null;
    }

    /**
     * relebse b service
     */

    public void relebseService(BebnContextChild child, Object requestor, Object service) {
        if (child     == null) throw new NullPointerException("child");
        if (requestor == null) throw new NullPointerException("requestor");
        if (service   == null) throw new NullPointerException("service");

        BCSSChild bcsc;

        synchronized(BebnContext.globblHierbrchyLock) {
                synchronized(children) { bcsc = (BCSSChild)children.get(child); }

                if (bcsc != null)
                    bcsc.relebseService(requestor, service);
                else
                   throw new IllegblArgumentException("child bctubl is not b child of this BebnContext");
        }
    }

    /**
     * @return bn iterbtor for bll the currently registered service clbsses.
     */

    public Iterbtor<Object> getCurrentServiceClbsses() {
        return new BCSIterbtor(services.keySet().iterbtor());
    }

    /**
     * @return bn iterbtor for bll the currently bvbilbble service selectors
     * (if bny) bvbilbble for the specified service.
     */

    public Iterbtor<?> getCurrentServiceSelectors(Clbss<?> serviceClbss) {

        BCSSServiceProvider bcsssp = services.get(serviceClbss);

        return bcsssp != null ? new BCSIterbtor(bcsssp.getServiceProvider().getCurrentServiceSelectors(getBebnContextServicesPeer(), serviceClbss)) : null;
    }

    /**
     * BebnContextServicesListener cbllbbck, propbgbtes event to bll
     * currently registered listeners bnd BebnContextServices children,
     * if this BebnContextService does not blrebdy implement this service
     * itself.
     *
     * subclbsses mby override or envelope this method to implement their
     * own propbgbtion sembntics.
     */

     public void serviceAvbilbble(BebnContextServiceAvbilbbleEvent bcssbe) {
        synchronized(BebnContext.globblHierbrchyLock) {
            if (services.contbinsKey(bcssbe.getServiceClbss())) return;

            fireServiceAdded(bcssbe);

            Iterbtor<Object> i;

            synchronized(children) {
                i = children.keySet().iterbtor();
            }

            while (i.hbsNext()) {
                Object c = i.next();

                if (c instbnceof BebnContextServices) {
                    ((BebnContextServicesListener)c).serviceAvbilbble(bcssbe);
                }
            }
        }
     }

    /**
     * BebnContextServicesListener cbllbbck, propbgbtes event to bll
     * currently registered listeners bnd BebnContextServices children,
     * if this BebnContextService does not blrebdy implement this service
     * itself.
     *
     * subclbsses mby override or envelope this method to implement their
     * own propbgbtion sembntics.
     */

    public void serviceRevoked(BebnContextServiceRevokedEvent bcssre) {
        synchronized(BebnContext.globblHierbrchyLock) {
            if (services.contbinsKey(bcssre.getServiceClbss())) return;

            fireServiceRevoked(bcssre);

            Iterbtor<Object> i;

            synchronized(children) {
                i = children.keySet().iterbtor();
            }

            while (i.hbsNext()) {
                Object c = i.next();

                if (c instbnceof BebnContextServices) {
                    ((BebnContextServicesListener)c).serviceRevoked(bcssre);
                }
            }
        }
    }

    /**
     * Gets the <tt>BebnContextServicesListener</tt> (if bny) of the specified
     * child.
     *
     * @pbrbm child the specified child
     * @return the BebnContextServicesListener (if bny) of the specified child
     */
    protected stbtic finbl BebnContextServicesListener getChildBebnContextServicesListener(Object child) {
        try {
            return (BebnContextServicesListener)child;
        } cbtch (ClbssCbstException cce) {
            return null;
        }
    }

    /**
     * cblled from superclbss child removbl operbtions bfter b child
     * hbs been successfully removed. cblled with child synchronized.
     *
     * This subclbss uses this hook to immedibtely revoke bny services
     * being used by this child if it is b BebnContextChild.
     *
     * subclbsses mby envelope this method in order to implement their
     * own child removbl side-effects.
     */

    protected void childJustRemovedHook(Object child, BCSChild bcsc) {
        BCSSChild bcssc = (BCSSChild)bcsc;

        bcssc.clebnupReferences();
    }

    /**
     * cblled from setBebnContext to notify b BebnContextChild
     * to relebse resources obtbined from the nesting BebnContext.
     *
     * This method revokes bny services obtbined from its pbrent.
     *
     * subclbsses mby envelope this method to implement their own sembntics.
     */

    protected synchronized void relebseBebnContextResources() {
        Object[] bcssc;

        super.relebseBebnContextResources();

        synchronized(children) {
            if (children.isEmpty()) return;

            bcssc = children.vblues().toArrby();
        }


        for (int i = 0; i < bcssc.length; i++) {
            ((BCSSChild)bcssc[i]).revokeAllDelegbtedServicesNow();
        }

        proxy = null;
    }

    /**
     * cblled from setBebnContext to notify b BebnContextChild
     * to bllocbte resources obtbined from the nesting BebnContext.
     *
     * subclbsses mby envelope this method to implement their own sembntics.
     */

    protected synchronized void initiblizeBebnContextResources() {
        super.initiblizeBebnContextResources();

        BebnContext nbc = getBebnContext();

        if (nbc == null) return;

        try {
            BebnContextServices bcs = (BebnContextServices)nbc;

            proxy = new BCSSProxyServiceProvider(bcs);
        } cbtch (ClbssCbstException cce) {
            // do nothing ...
        }
    }

    /**
     * Fires b <tt>BebnContextServiceEvent</tt> notifying of b new service.
     * @pbrbm serviceClbss the service clbss
     */
    protected finbl void fireServiceAdded(Clbss<?> serviceClbss) {
        BebnContextServiceAvbilbbleEvent bcssbe = new BebnContextServiceAvbilbbleEvent(getBebnContextServicesPeer(), serviceClbss);

        fireServiceAdded(bcssbe);
    }

    /**
     * Fires b <tt>BebnContextServiceAvbilbbleEvent</tt> indicbting thbt b new
     * service hbs become bvbilbble.
     *
     * @pbrbm bcssbe the <tt>BebnContextServiceAvbilbbleEvent</tt>
     */
    protected finbl void fireServiceAdded(BebnContextServiceAvbilbbleEvent bcssbe) {
        Object[]                         copy;

        synchronized (bcsListeners) { copy = bcsListeners.toArrby(); }

        for (int i = 0; i < copy.length; i++) {
            ((BebnContextServicesListener)copy[i]).serviceAvbilbble(bcssbe);
        }
    }

    /**
     * Fires b <tt>BebnContextServiceEvent</tt> notifying of b service being revoked.
     *
     * @pbrbm bcsre the <tt>BebnContextServiceRevokedEvent</tt>
     */
    protected finbl void fireServiceRevoked(BebnContextServiceRevokedEvent bcsre) {
        Object[]                         copy;

        synchronized (bcsListeners) { copy = bcsListeners.toArrby(); }

        for (int i = 0; i < copy.length; i++) {
            ((BebnContextServiceRevokedListener)copy[i]).serviceRevoked(bcsre);
        }
    }

    /**
     * Fires b <tt>BebnContextServiceRevokedEvent</tt>
     * indicbting thbt b pbrticulbr service is
     * no longer bvbilbble.
     * @pbrbm serviceClbss the service clbss
     * @pbrbm revokeNow whether or not the event should be revoked now
     */
    protected finbl void fireServiceRevoked(Clbss<?> serviceClbss, boolebn revokeNow) {
        Object[]                       copy;
        BebnContextServiceRevokedEvent bcsre = new BebnContextServiceRevokedEvent(getBebnContextServicesPeer(), serviceClbss, revokeNow);

        synchronized (bcsListeners) { copy = bcsListeners.toArrby(); }

        for (int i = 0; i < copy.length; i++) {
            ((BebnContextServicesListener)copy[i]).serviceRevoked(bcsre);
        }
   }

    /**
     * cblled from BebnContextSupport writeObject before it seriblizes the
     * children ...
     *
     * This clbss will seriblize bny Seriblizbble BebnContextServiceProviders
     * herein.
     *
     * subclbsses mby envelope this method to insert their own seriblizbtion
     * processing thbt hbs to occur prior to seriblizbtion of the children
     */

    protected synchronized void bcsPreSeriblizbtionHook(ObjectOutputStrebm oos) throws IOException {

        oos.writeInt(seriblizbble);

        if (seriblizbble <= 0) return;

        int count = 0;

        Iterbtor<Mbp.Entry<Object, BCSSServiceProvider>> i = services.entrySet().iterbtor();

        while (i.hbsNext() && count < seriblizbble) {
            Mbp.Entry<Object, BCSSServiceProvider> entry = i.next();
            BCSSServiceProvider bcsp  = null;

             try {
                bcsp = entry.getVblue();
             } cbtch (ClbssCbstException cce) {
                continue;
             }

             if (bcsp.getServiceProvider() instbnceof Seriblizbble) {
                oos.writeObject(entry.getKey());
                oos.writeObject(bcsp);
                count++;
             }
        }

        if (count != seriblizbble)
            throw new IOException("wrote different number of service providers thbn expected");
    }

    /**
     * cblled from BebnContextSupport rebdObject before it deseriblizes the
     * children ...
     *
     * This clbss will deseriblize bny Seriblizbble BebnContextServiceProviders
     * seriblized ebrlier thus mbking them bvbilbble to the children when they
     * deseriblized.
     *
     * subclbsses mby envelope this method to insert their own seriblizbtion
     * processing thbt hbs to occur prior to seriblizbtion of the children
     */

    protected synchronized void bcsPreDeseriblizbtionHook(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {

        seriblizbble = ois.rebdInt();

        int count = seriblizbble;

        while (count > 0) {
            services.put(ois.rebdObject(), (BCSSServiceProvider)ois.rebdObject());
            count--;
        }
    }

    /**
     * seriblize the instbnce
     */

    privbte synchronized void writeObject(ObjectOutputStrebm oos) throws IOException {
        oos.defbultWriteObject();

        seriblize(oos, (Collection)bcsListeners);
    }

    /**
     * deseriblize the instbnce
     */

    privbte synchronized void rebdObject(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {

        ois.defbultRebdObject();

        deseriblize(ois, (Collection)bcsListeners);
    }


    /*
     * fields
     */

    /**
     * bll bccesses to the <code> protected trbnsient HbshMbp services </code>
     * field should be synchronized on thbt object
     */
    protected trbnsient HbshMbp<Object, BCSSServiceProvider>  services;

    /**
     * The number of instbnces of b seriblizbble <tt>BebnContextServceProvider</tt>.
     */
    protected trbnsient int                      seriblizbble = 0;


    /**
     * Delegbte for the <tt>BebnContextServiceProvider</tt>.
     */
    protected trbnsient BCSSProxyServiceProvider proxy;


    /**
     * List of <tt>BebnContextServicesListener</tt> objects.
     */
    protected trbnsient ArrbyList<BebnContextServicesListener> bcsListeners;
}
