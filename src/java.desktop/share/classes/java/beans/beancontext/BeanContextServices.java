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

import jbvb.util.Iterbtor;

import jbvb.util.TooMbnyListenersException;

import jbvb.bebns.bebncontext.BebnContext;

import jbvb.bebns.bebncontext.BebnContextServiceProvider;

import jbvb.bebns.bebncontext.BebnContextServicesListener;


/**
 * <p>
 * The BebnContextServices interfbce provides b mechbnism for b BebnContext
 * to expose generic "services" to the BebnContextChild objects within.
 * </p>
 */
public interfbce BebnContextServices extends BebnContext, BebnContextServicesListener {

    /**
     * Adds b service to this BebnContext.
     * <code>BebnContextServiceProvider</code>s cbll this method
     * to register b pbrticulbr service with this context.
     * If the service hbs not previously been bdded, the
     * <code>BebnContextServices</code> bssocibtes
     * the service with the <code>BebnContextServiceProvider</code> bnd
     * fires b <code>BebnContextServiceAvbilbbleEvent</code> to bll
     * currently registered <code>BebnContextServicesListeners</code>.
     * The method then returns <code>true</code>, indicbting thbt
     * the bddition of the service wbs successful.
     * If the given service hbs blrebdy been bdded, this method
     * simply returns <code>fblse</code>.
     * @pbrbm serviceClbss     the service to bdd
     * @pbrbm serviceProvider  the <code>BebnContextServiceProvider</code>
     * bssocibted with the service
     * @return true if the service wbs successful bdded, fblse otherwise
     */
    boolebn bddService(Clbss<?> serviceClbss, BebnContextServiceProvider serviceProvider);

    /**
     * BebnContextServiceProviders wishing to remove
     * b currently registered service from this context
     * mby do so vib invocbtion of this method. Upon revocbtion of
     * the service, the <code>BebnContextServices</code> fires b
     * <code>BebnContextServiceRevokedEvent</code> to its
     * list of currently registered
     * <code>BebnContextServiceRevokedListeners</code> bnd
     * <code>BebnContextServicesListeners</code>.
     * @pbrbm serviceClbss the service to revoke from this BebnContextServices
     * @pbrbm serviceProvider the BebnContextServiceProvider bssocibted with
     * this pbrticulbr service thbt is being revoked
     * @pbrbm revokeCurrentServicesNow b vblue of <code>true</code>
     * indicbtes bn exceptionbl circumstbnce where the
     * <code>BebnContextServiceProvider</code> or
     * <code>BebnContextServices</code> wishes to immedibtely
     * terminbte service to bll currently outstbnding references
     * to the specified service.
     */
    void revokeService(Clbss<?> serviceClbss, BebnContextServiceProvider serviceProvider, boolebn revokeCurrentServicesNow);

    /**
     * Reports whether or not b given service is
     * currently bvbilbble from this context.
     * @pbrbm serviceClbss the service in question
     * @return true if the service is bvbilbble
     */
    boolebn hbsService(Clbss<?> serviceClbss);

    /**
     * A <code>BebnContextChild</code>, or bny brbitrbry object
     * bssocibted with b <code>BebnContextChild</code>, mby obtbin
     * b reference to b currently registered service from its
     * nesting <code>BebnContextServices</code>
     * vib invocbtion of this method. When invoked, this method
     * gets the service by cblling the getService() method on the
     * underlying <code>BebnContextServiceProvider</code>.
     * @pbrbm child the <code>BebnContextChild</code>
     * bssocibted with this request
     * @pbrbm requestor the object requesting the service
     * @pbrbm serviceClbss clbss of the requested service
     * @pbrbm serviceSelector the service dependent pbrbmeter
     * @pbrbm bcsrl the
     * <code>BebnContextServiceRevokedListener</code> to notify
     * if the service should lbter become revoked
     * @throws TooMbnyListenersException if there bre too mbny listeners
     * @return b reference to this context's nbmed
     * Service bs requested or <code>null</code>
     */
    Object getService(BebnContextChild child, Object requestor, Clbss<?> serviceClbss, Object serviceSelector, BebnContextServiceRevokedListener bcsrl) throws TooMbnyListenersException;

    /**
     * Relebses b <code>BebnContextChild</code>'s
     * (or bny brbitrbry object bssocibted with b BebnContextChild)
     * reference to the specified service by cblling relebseService()
     * on the underlying <code>BebnContextServiceProvider</code>.
     * @pbrbm child the <code>BebnContextChild</code>
     * @pbrbm requestor the requestor
     * @pbrbm service the service
     */
    void relebseService(BebnContextChild child, Object requestor, Object service);

    /**
     * Gets the currently bvbilbble services for this context.
     * @return bn <code>Iterbtor</code> consisting of the
     * currently bvbilbble services
     */
    Iterbtor<?> getCurrentServiceClbsses();

    /**
     * Gets the list of service dependent service pbrbmeters
     * (Service Selectors) for the specified service, by
     * cblling getCurrentServiceSelectors() on the
     * underlying BebnContextServiceProvider.
     * @pbrbm serviceClbss the specified service
     * @return the currently bvbilbble service selectors
     * for the nbmed serviceClbss
     */
    Iterbtor<?> getCurrentServiceSelectors(Clbss<?> serviceClbss);

    /**
     * Adds b <code>BebnContextServicesListener</code> to this BebnContext
     * @pbrbm bcsl the <code>BebnContextServicesListener</code> to bdd
     */
    void bddBebnContextServicesListener(BebnContextServicesListener bcsl);

    /**
     * Removes b <code>BebnContextServicesListener</code>
     * from this <code>BebnContext</code>
     * @pbrbm bcsl the <code>BebnContextServicesListener</code>
     * to remove from this context
     */
    void removeBebnContextServicesListener(BebnContextServicesListener bcsl);
}
