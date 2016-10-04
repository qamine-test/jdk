/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi.request;

import com.sun.jdi.*;

/**
 * Identifies b {@link Locbtion} in the tbrget VM bt which
 * execution should be stopped. When bn enbbled BrebkpointRequest is
 * sbtisfied, bn
 * {@link com.sun.jdi.event.EventSet event set} contbining bn
 * {@link com.sun.jdi.event.BrebkpointEvent BrebkpointEvent}
 * will be plbced on the
 * {@link com.sun.jdi.event.EventQueue EventQueue} bnd
 * the bpplicbtion is interrupted. The collection of existing brebkpoints is
 * mbnbged by the {@link EventRequestMbnbger}
 *
 * @see Locbtion
 * @see com.sun.jdi.event.BrebkpointEvent
 * @see com.sun.jdi.event.EventQueue
 * @see EventRequestMbnbger
 *
 * @buthor Robert Field
 * @since  1.3
 */
@jdk.Exported
public interfbce BrebkpointRequest extends EventRequest, Locbtbble {

    /**
     * Returns the locbtion of the requested brebkpoint.
     *
     * @return the {@link Locbtion} where this brebkpoint hbs been set.
     */
    Locbtion locbtion();

    /**
     * Restricts the events generbted by this request to those in
     * the given threbd.
     * @pbrbm threbd the threbd to filter on.
     * @throws InvblidRequestStbteException if this request is currently
     * enbbled or hbs been deleted.
     * Filters mby be bdded only to disbbled requests.
     */
    void bddThrebdFilter(ThrebdReference threbd);

    /**
     * Restricts the events generbted by this request to those in
     * which the currently executing instbnce is the object
     * specified.
     * <P>
     * Not bll tbrgets support this operbtion.
     * Use {@link VirtublMbchine#cbnUseInstbnceFilters()}
     * to determine if the operbtion is supported.
     * @since 1.4
     * @pbrbm instbnce the object which must be the current instbnce
     * in order to pbss this filter.
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget virtubl mbchine does not support this
     * operbtion.
     * @throws InvblidRequestStbteException if this request is currently
     * enbbled or hbs been deleted.
     * Filters mby be bdded only to disbbled requests.
     */
    void bddInstbnceFilter(ObjectReference instbnce);
}
