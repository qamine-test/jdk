/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.monitor.event;

import jbvb.util.EventObject;
import sun.jvmstbt.monitor.MonitoredHost;

/**
 * Bbse clbss for events emitted by b {@link MonitoredHost}.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public clbss HostEvent extends EventObject {

    /**
     * Construct b new HostEvent instbnce.
     *
     * @pbrbm host the MonitoredHost source of the event.
     */
    public HostEvent(MonitoredHost host) {
        super(host);
    }

    /**
     * Return the MonitoredHost source of this event.
     *
     * @return MonitoredHost - the source of this event.
     */
    public MonitoredHost getMonitoredHost() {
        return (MonitoredHost)source;
    }
}
