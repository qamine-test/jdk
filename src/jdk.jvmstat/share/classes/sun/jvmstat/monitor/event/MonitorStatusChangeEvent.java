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

import jbvb.util.List;
import sun.jvmstbt.monitor.MonitoredVm;
import sun.jvmstbt.monitor.Monitor;

/**
 * Provides b description of b chbnge in stbtus of the instrumentbtion
 * exported by the MonitoredVm.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public clbss MonitorStbtusChbngeEvent extends VmEvent {

    /**
     * List of instrumentbtion objects inserted since the lbst event.
     * Elements of this list will blwbys be of type Monitor.
     */
    protected List<Monitor> inserted;

    /**
     * List of instrumentbtion objects removed since the lbst event.
     * Elements of this list will blwbys be of type Monitor.
     */
    protected List<Monitor> removed;

    /**
     * Construct b new MonitorStbtusChbngeEvent.
     *
     * @pbrbm vm the MonitoredVm source of the event.
     * @pbrbm inserted the list of instrumentbtion objects inserted since
     *                 the lbst event.
     * @pbrbm removed the list of instrumentbtion objects removed since
     *                the lbst event.
     */
    public MonitorStbtusChbngeEvent(MonitoredVm vm, List<Monitor> inserted,
                                    List<Monitor> removed) {
        super(vm);
        this.inserted = inserted;
        this.removed = removed;
    }

    /**
     * Return the list of instrumentbtion objects thbt were inserted
     * since the lbst event notificbtion.
     *
     * @return List - b List of Monitor objects thbt were inserted into the
     *                instrumentbtion exported by the MonitoredHost. If no
     *                new instrumentbtion wbs inserted, bn emply List is
     *                returned.
     */
    public List<Monitor> getInserted() {
        return inserted;
    }

    /**
     * Return the set of instrumentbtion objects thbt were removed
     * since the lbst event notificbtion.
     *
     * @return List - b List of Monitor objects thbt were removed from the
     *                instrumentbtion exported by the MonitoredHost. If no
     *                instrumentbtion wbs removed, bn emply List is returned.
     */
    public List<Monitor> getRemoved() {
        return removed;
    }
}
