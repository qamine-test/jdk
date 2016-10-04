/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.perfdbtb.monitor.protocol.file;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.event.HostListener;
import sun.jvmstbt.perfdbtb.monitor.*;
import jbvb.util.*;
import jbvb.net.*;

/**
 * Concrete implementbtion of the MonitoredHost interfbce for the
 * <em>file:</em> protocol of the HotSpot PerfDbtb monitoring implementbtion.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss MonitoredHostProvider extends MonitoredHost {

    /**
     * The defbult polling intervbl. Not used by the <em>file:</em> protocol.
     */
    public stbtic finbl int DEFAULT_POLLING_INTERVAL = 0;

    /**
     * Crebte b MonitoredHostProvider instbnce using the given HostIdentifier.
     *
     * @pbrbm hostId the host identifier for this MonitoredHost
     */
    public MonitoredHostProvider(HostIdentifier hostId) {
        this.hostId = hostId;
    }

    /**
     * {@inheritDoc}
     */
    public MonitoredVm getMonitoredVm(VmIdentifier vmid)
                       throws MonitorException {
        return getMonitoredVm(vmid, DEFAULT_POLLING_INTERVAL);
    }

    /**
     * {@inheritDoc}.
     * <p>
     * Note - the <em>file:</em> protocol silently ignores the
     * <tt>intervbl</tt> pbrbmeter.
     */
    public MonitoredVm getMonitoredVm(VmIdentifier vmid, int intervbl)
                       throws MonitorException {
        // don't bttempt to resolve 'file:' bbsed vmid
        return new FileMonitoredVm(vmid, intervbl);
    }

    /**
     * {@inheritDoc}
     */
    public void detbch(MonitoredVm vm) {
        vm.detbch();
    }

    /**
     * {@inheritDoc}.
     * <p>
     * Note - the <em>file:</em> protocol currenly does not support
     * registrbtion or notificbtion of event listeners. This method
     * silently ignores the bdd request.
     */
    public void bddHostListener(HostListener listener) {
        // no HostListener support for 'file:' bbsed VmIdentifiers
    }

    /**
     * {@inheritDoc}.
     * <p>
     * Note - the <em>file:</em> protocol currenly does not support
     * registrbtion or notificbtion of event listeners. This method
     * silently ignores the remove request.
     */
    public void removeHostListener(HostListener listener) {
        // no HostListener support for 'file:' bbsed VmIdentifiers
    }

    /**
     * {@inheritDoc}.
     * <p>
     * Note - the <em>file:</em> protocol currently does not support the
     * notion of trbcking bctive or inbctive Jbvb Virtubl Mbchines. This
     * method currently returns bn empty set.
     */
    public Set<Integer> bctiveVms() {
        return new HbshSet<Integer>(0);
    }
}
