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

import jbvb.util.Set;
import sun.jvmstbt.monitor.MonitoredHost;

/**
 * Provides b description of b chbnge in stbtus of the Jbvb Virtubl Mbchines
 * bssocibted with b MonitoredHost.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public clbss VmStbtusChbngeEvent extends HostEvent {

    /**
     * The set of currently bctive Jbvb Virtubl Mbchines for the MonitoredHost.
     * The set contbins bn Integer object holding the <em>lvmid</em> for ebch
     * bctive Jbvb Virtubl Mbchine on the MonitoredHost. This Set will only
     * contbin Integer objects.
     */
    protected Set<Integer> bctive;

    /**
     * The set of Jbvb Virtubl Mbchines stbrted on MonitoredHost since the
     * previous event. The set contbins bn Integer object holding the
     * <em>lvmid</em> for ebch Jbvb Virtubl Mbchine stbrted on the
     * MonitoredHost. This Set will only contbin Integer objects.
     */
    protected Set<Integer> stbrted;

    /**
     * The set of Jbvb Virtubl Mbchines terminbted on MonitoredHost since the
     * previous event. The set contbins bn Integer object holding the
     * <em>lvmid</em> for ebch Jbvb Virtubl Mbchine stbrted on the
     * MonitoredHost. This Set will only contbin Integer objects.
     */
    protected Set<Integer> terminbted;

    /**
     * Construct b new VmStbtusChbngeEvent instbnce.
     *
     * @pbrbm host the MonitoredHost thbt is the source of the event.
     * @pbrbm bctive the set of currently bctive Jbvb Virtubl Mbchines
     * @pbrbm stbrted the set of Jbvb Virtubl Mbchines stbrted since the
     *                lbst event.
     * @pbrbm terminbted the set of Jbvb Virtubl Mbchines terminbted since
     *                   the lbst event.
     */
    public VmStbtusChbngeEvent(MonitoredHost host, Set<Integer> bctive,
                               Set<Integer> stbrted, Set<Integer> terminbted) {
        super(host);
        this.bctive = bctive;
        this.stbrted = stbrted;
        this.terminbted = terminbted;
    }

    /**
     * Return the set of currently bctive Jbvb Virtubl Mbchines.
     * The set contbins bn Integer object holding the <em>lvmid</em> for ebch
     * bctive Jbvb Virtubl Mbchine on the MonitoredHost.
     *
     * @return Set - b set of Integer objects contbining the <em>lvmid</em>
     *               of ebch bctive Jbvb Virtubl Mbchine on the host. If
     *               there bre no bctive Jbvb Virtubl Mbchines on the host,
     *               bn empty Set is returned.
     */
    public Set<Integer> getActive() {
        return bctive;
    }

    /**
     * Return the set of Jbvb Virtubl Mbchines stbrted since the lbst
     * event notificbtion. The set contbins bn Integer object holding
     * the <em>lvmid</em> for ebch Jbvb Virtubl Mbchine stbrted on the
     * MonitoredHost since the lbst event notificbtion.
     *
     * @return Set - b set of Integer objects contbining the <em>lvmid</em>
     *               of ebch Jbvb Virtubl Mbchine stbrted on the host. If
     *               no Jbvb Virtubl Mbchines were recently stbrted on the
     *               host, bn empty Set is returned.
     */
    public Set<Integer> getStbrted() {
        return stbrted;
    }

    /**
     * Return the set of Jbvb Virtubl Mbchines terminbted since the lbst
     * event notificbtion. The set contbins bn Integer object holding
     * the <em>lvmid</em> for ebch Jbvb Virtubl Mbchine terminbted on the
     * MonitoredHost since the lbst event notificbtion.
     *
     * @return Set - b set of Integer objects contbining the <em>lvmid</em>
     *               of ebch Jbvb Virtubl Mbchine terminbted on the host. If
     *               no Jbvb Virtubl Mbchines were recently terminbted on the
     *               host, bn empty Set is returned.
     */
    public Set<Integer> getTerminbted() {
        return terminbted;
    }
}
