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

import jbvb.bebns.bebncontext.BebnContextChild;
import jbvb.bebns.bebncontext.BebnContextEvent;

import jbvb.bebns.bebncontext.BebnContextServices;

import jbvb.util.Iterbtor;

/**
 * <p>
 * This event type is used by the BebnContextServicesListener in order to
 * identify the service being registered.
 * </p>
 */

public clbss BebnContextServiceAvbilbbleEvent extends BebnContextEvent {
    privbte stbtic finbl long seriblVersionUID = -5333985775656400778L;

    /**
     * Construct b <code>BebnContextAvbilbbleServiceEvent</code>.
     * @pbrbm bcs The context in which the service hbs become bvbilbble
     * @pbrbm sc A <code>Clbss</code> reference to the newly bvbilbble service
     */
    public BebnContextServiceAvbilbbleEvent(BebnContextServices bcs, Clbss<?> sc) {
        super((BebnContext)bcs);

        serviceClbss = sc;
    }

    /**
     * Gets the source bs b reference of type <code>BebnContextServices</code>.
     * @return The context in which the service hbs become bvbilbble
     */
    public BebnContextServices getSourceAsBebnContextServices() {
        return (BebnContextServices)getBebnContext();
    }

    /**
     * Gets the service clbss thbt is the subject of this notificbtion.
     * @return A <code>Clbss</code> reference to the newly bvbilbble service
     */
    public Clbss<?> getServiceClbss() { return serviceClbss; }

    /**
     * Gets the list of service dependent selectors.
     * @return the current selectors bvbilbble from the service
     */
    public Iterbtor<?> getCurrentServiceSelectors() {
        return ((BebnContextServices)getSource()).getCurrentServiceSelectors(serviceClbss);
    }

    /*
     * fields
     */

    /**
     * A <code>Clbss</code> reference to the newly bvbilbble service
     */
    protected Clbss<?>                   serviceClbss;
}
