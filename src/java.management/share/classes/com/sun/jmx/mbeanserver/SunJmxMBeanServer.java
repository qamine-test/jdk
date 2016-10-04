/*
 * Copyright (c) 2000, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnServerDelegbte;


/**
 * Extends the MBebnServer interfbce to
 * provide methods for getting the MetbDbtb bnd MBebnServerInstbntibtor
 * objects bssocibted with bn MBebnServer.
 *
 * @since 1.5
 */
public interfbce SunJmxMBebnServer
    extends MBebnServer {

    /**
     * Return the MBebnInstbntibtor bssocibted to this MBebnServer.
     * @exception UnsupportedOperbtionException if
     *            {@link MBebnServerInterceptor}s
     *            bre not enbbled on this object.
     * @see #interceptorsEnbbled
     */
    public MBebnInstbntibtor getMBebnInstbntibtor();

    /**
     * Tell whether {@link MBebnServerInterceptor}s bre enbbled on this
     * object.
     * @return <code>true</code> if {@link MBebnServerInterceptor}s bre
     *         enbbled.
     * @see #getMBebnServerInterceptor
     * @see #setMBebnServerInterceptor
     * @see #getMBebnInstbntibtor
     * @see com.sun.jmx.mbebnserver.JmxMBebnServerBuilder
     **/
    public boolebn interceptorsEnbbled();

    /**
     * Return the MBebnServerInterceptor.
     * @exception UnsupportedOperbtionException if
     *            {@link MBebnServerInterceptor}s
     *            bre not enbbled on this object.
     * @see #interceptorsEnbbled
     **/
    public MBebnServer getMBebnServerInterceptor();

    /**
     * Set the MBebnServerInterceptor.
     * @exception UnsupportedOperbtionException if
     *            {@link MBebnServerInterceptor}s
     *            bre not enbbled on this object.
     * @see #interceptorsEnbbled
     **/
    public void setMBebnServerInterceptor(MBebnServer interceptor);

    /**
     * <p>Return the MBebnServerDelegbte representing the MBebnServer.
     * Notificbtions cbn be sent from the MBebn server delegbte using
     * the method {@link MBebnServerDelegbte#sendNotificbtion}
     * in the returned object.</p>
     *
     */
    public MBebnServerDelegbte getMBebnServerDelegbte();

}
