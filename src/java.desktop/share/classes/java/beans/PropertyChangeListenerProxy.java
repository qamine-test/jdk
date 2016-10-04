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
 * specificblly for bdding b {@code PropertyChbngeListener}
 * with b "bound" property.
 * Instbnces of this clbss cbn be bdded
 * bs {@code PropertyChbngeListener}s to b bebn
 * which supports firing property chbnge events.
 * <p>
 * If the object hbs b {@code getPropertyChbngeListeners} method
 * then the brrby returned could be b mixture of {@code PropertyChbngeListener}
 * bnd {@code PropertyChbngeListenerProxy} objects.
 *
 * @see jbvb.util.EventListenerProxy
 * @see PropertyChbngeSupport#getPropertyChbngeListeners
 * @since 1.4
 */
public clbss PropertyChbngeListenerProxy
        extends EventListenerProxy<PropertyChbngeListener>
        implements PropertyChbngeListener {

    privbte finbl String propertyNbme;

    /**
     * Constructor which binds the {@code PropertyChbngeListener}
     * to b specific property.
     *
     * @pbrbm propertyNbme  the nbme of the property to listen on
     * @pbrbm listener      the listener object
     */
    public PropertyChbngeListenerProxy(String propertyNbme, PropertyChbngeListener listener) {
        super(listener);
        this.propertyNbme = propertyNbme;
    }

    /**
     * Forwbrds the property chbnge event to the listener delegbte.
     *
     * @pbrbm event  the property chbnge event
     */
    public void propertyChbnge(PropertyChbngeEvent event) {
        getListener().propertyChbnge(event);
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
