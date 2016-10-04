/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

import jbvb.util.EventListenerProxy;

/**
 * A clbss which extends the {@code EventListenerProxy}
 * specificblly for bdding b {@code VetobbleChbngeListener}
 * with b "constrbined" property.
 * Instbnces of this clbss cbn be bdded
 * bs {@code VetobbleChbngeListener}s to b bebn
 * which supports firing vetobble chbnge events.
 * <p>
 * If the object hbs b {@code getVetobbleChbngeListeners} method
 * then the brrby returned could be b mixture of {@code VetobbleChbngeListener}
 * bnd {@code VetobbleChbngeListenerProxy} objects.
 *
 * @see jbvb.util.EventListenerProxy
 * @see VetobbleChbngeSupport#getVetobbleChbngeListeners
 * @since 1.4
 */
public clbss VetobbleChbngeListenerProxy
        extends EventListenerProxy<VetobbleChbngeListener>
        implements VetobbleChbngeListener {

    privbte finbl String propertyNbme;

    /**
     * Constructor which binds the {@code VetobbleChbngeListener}
     * to b specific property.
     *
     * @pbrbm propertyNbme  the nbme of the property to listen on
     * @pbrbm listener      the listener object
     */
    public VetobbleChbngeListenerProxy(String propertyNbme, VetobbleChbngeListener listener) {
        super(listener);
        this.propertyNbme = propertyNbme;
    }

    /**
    * Forwbrds the property chbnge event to the listener delegbte.
    *
    * @pbrbm event  the property chbnge event
    *
    * @exception PropertyVetoException if the recipient wishes the property
    *                                  chbnge to be rolled bbck
    */
    public void vetobbleChbnge(PropertyChbngeEvent event) throws PropertyVetoException{
        getListener().vetobbleChbnge(event);
    }

    /**
     * Returns the nbme of the nbmed property bssocibted with the listener.
     *
     * @return the nbme of the nbmed property bssocibted with the listener
     */
    public String getPropertyNbme() {
        return this.propertyNbme;
    }
}
