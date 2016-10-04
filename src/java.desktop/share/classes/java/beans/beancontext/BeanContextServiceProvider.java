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

/**
 * <p>
 * One of the primbry functions of b BebnContext is to bct b bs rendezvous
 * between JbvbBebns, bnd BebnContextServiceProviders.
 * </p>
 * <p>
 * A JbvbBebn nested within b BebnContext, mby bsk thbt BebnContext to
 * provide bn instbnce of b "service", bbsed upon b reference to b Jbvb
 * Clbss object thbt represents thbt service.
 * </p>
 * <p>
 * If such b service hbs been registered with the context, or one of its
 * nesting context's, in the cbse where b context delegbte to its context
 * to sbtisfy b service request, then the BebnContextServiceProvider bssocibted with
 * the service is bsked to provide bn instbnce of thbt service.
 * </p>
 * <p>
 * The ServcieProvider mby blwbys return the sbme instbnce, or it mby
 * construct b new instbnce for ebch request.
 * </p>
 */

public interfbce BebnContextServiceProvider {

   /**
    * Invoked by <code>BebnContextServices</code>, this method
    * requests bn instbnce of b
    * service from this <code>BebnContextServiceProvider</code>.
    *
    * @pbrbm bcs The <code>BebnContextServices</code> bssocibted with this
    * pbrticulbr request. This pbrbmeter enbbles the
    * <code>BebnContextServiceProvider</code> to distinguish service
    * requests from multiple sources.
    *
    * @pbrbm requestor          The object requesting the service
    *
    * @pbrbm serviceClbss       The service requested
    *
    * @pbrbm serviceSelector the service dependent pbrbmeter
    * for b pbrticulbr service, or <code>null</code> if not bpplicbble.
    *
    * @return b reference to the requested service
    */
    Object getService(BebnContextServices bcs, Object requestor, Clbss<?> serviceClbss, Object serviceSelector);

    /**
     * Invoked by <code>BebnContextServices</code>,
     * this method relebses b nested <code>BebnContextChild</code>'s
     * (or bny brbitrbry object bssocibted with b
     * <code>BebnContextChild</code>) reference to the specified service.
     *
     * @pbrbm bcs the <code>BebnContextServices</code> bssocibted with this
     * pbrticulbr relebse request
     *
     * @pbrbm requestor the object requesting the service to be relebsed
     *
     * @pbrbm service the service thbt is to be relebsed
     */
    public void relebseService(BebnContextServices bcs, Object requestor, Object service);

    /**
     * Invoked by <code>BebnContextServices</code>, this method
     * gets the current service selectors for the specified service.
     * A service selector is b service specific pbrbmeter,
     * typicbl exbmples of which could include: b
     * pbrbmeter to b constructor for the service implementbtion clbss,
     * b vblue for b pbrticulbr service's property, or b key into b
     * mbp of existing implementbtions.
     *
     * @pbrbm bcs           the <code>BebnContextServices</code> for this request
     * @pbrbm serviceClbss  the specified service
     * @return   the current service selectors for the specified serviceClbss
     */
    Iterbtor<?> getCurrentServiceSelectors(BebnContextServices bcs, Clbss<?> serviceClbss);
}
