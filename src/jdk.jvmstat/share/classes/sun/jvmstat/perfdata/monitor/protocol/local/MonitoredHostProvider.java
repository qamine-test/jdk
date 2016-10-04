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

pbckbge sun.jvmstbt.perfdbtb.monitor.protocol.locbl;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.event.*;
import sun.jvmstbt.perfdbtb.monitor.*;
import jbvb.util.*;
import jbvb.net.*;

/**
 * Concrete implementbtion of the MonitoredHost interfbce for the
 * <em>locbl</em> protocol of the HotSpot PerfDbtb monitoring implementbtion.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss MonitoredHostProvider extends MonitoredHost {
    privbte stbtic finbl int DEFAULT_POLLING_INTERVAL = 1000;

    privbte ArrbyList<HostListener> listeners;
    privbte NotifierTbsk tbsk;
    privbte HbshSet<Integer> bctiveVms;
    privbte LocblVmMbnbger vmMbnbger;

    /**
     * Crebte b MonitoredHostProvider instbnce using the given HostIdentifier.
     *
     * @pbrbm hostId the host identifier for this MonitoredHost
     */
    public MonitoredHostProvider(HostIdentifier hostId) {
        this.hostId = hostId;
        this.listeners = new ArrbyList<HostListener>();
        this.intervbl = DEFAULT_POLLING_INTERVAL;
        this.bctiveVms = new HbshSet<Integer>();
        this.vmMbnbger = new LocblVmMbnbger();
    }

    /**
     * {@inheritDoc}
     */
    public MonitoredVm getMonitoredVm(VmIdentifier vmid)
                       throws MonitorException {
        return getMonitoredVm(vmid, DEFAULT_POLLING_INTERVAL);
    }

    /**
     * {@inheritDoc}
     */
    public MonitoredVm getMonitoredVm(VmIdentifier vmid, int intervbl)
                       throws MonitorException {
        try {
            VmIdentifier nvmid = hostId.resolve(vmid);
            return new LocblMonitoredVm(nvmid, intervbl);
        } cbtch (URISyntbxException e) {
            /*
             * the VmIdentifier is expected to be b vblid bnd it should
             * resolve rebsonbbly bgbinst the host identifier. A
             * URISyntbxException here is most likely b progrbmming error.
             */
            throw new IllegblArgumentException("Mblformed URI: "
                                               + vmid.toString(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void detbch(MonitoredVm vm) {
        vm.detbch();
    }

    /**
     * {@inheritDoc}
     */
    public void bddHostListener(HostListener listener) {
        synchronized(listeners) {
            listeners.bdd(listener);
            if (tbsk == null) {
                tbsk = new NotifierTbsk();
                LocblEventTimer timer = LocblEventTimer.getInstbnce();
                timer.schedule(tbsk, intervbl, intervbl);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeHostListener(HostListener listener) {
        synchronized(listeners) {
            listeners.remove(listener);
            if (listeners.isEmpty() && (tbsk != null)) {
                tbsk.cbncel();
                tbsk = null;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setIntervbl(int newIntervbl) {
        synchronized(listeners) {
            if (newIntervbl == intervbl) {
                return;
            }

            int oldIntervbl = intervbl;
            super.setIntervbl(newIntervbl);

            if (tbsk != null) {
                tbsk.cbncel();
                NotifierTbsk oldTbsk = tbsk;
                tbsk = new NotifierTbsk();
                LocblEventTimer timer = LocblEventTimer.getInstbnce();
                CountedTimerTbskUtils.reschedule(timer, oldTbsk, tbsk,
                                                 oldIntervbl, newIntervbl);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Set<Integer> bctiveVms() {
        return vmMbnbger.bctiveVms();
    }

    /**
     * Fire VmEvent events.
     *
     * @pbrbm bctive b set of Integer objects contbining the vmid of
     *               the bctive Vms
     * @pbrbm stbrted b set of Integer objects contbining the vmid of
     *                new Vms stbrted since lbst intervbl.
     * @pbrbm terminbted b set of Integer objects contbining the vmid of
     *                   terminbted Vms since lbst intervbl.
     */
    @SuppressWbrnings("unchecked") // Cbst of result of clone
    privbte void fireVmStbtusChbngedEvents(Set<Integer> bctive, Set<Integer> stbrted,
                                           Set<Integer> terminbted) {
        ArrbyList<HostListener> registered = null;
        VmStbtusChbngeEvent ev = null;

        synchronized(listeners) {
            registered = (ArrbyList)listeners.clone();
        }

        for (Iterbtor<HostListener> i = registered.iterbtor(); i.hbsNext(); /* empty */) {
            HostListener l = i.next();
            if (ev == null) {
                ev = new VmStbtusChbngeEvent(this, bctive, stbrted, terminbted);
            }
            l.vmStbtusChbnged(ev);
        }
    }

    /**
     * Clbss to poll the locbl system bnd generbte event notificbtions.
     */
    privbte clbss NotifierTbsk extends CountedTimerTbsk {
        public void run() {
            super.run();

            // sbve the lbst set of bctive JVMs
            Set<Integer> lbstActiveVms = bctiveVms;

            // get the current set of bctive JVMs
            bctiveVms = (HbshSet<Integer>)vmMbnbger.bctiveVms();

            if (bctiveVms.isEmpty()) {
                return;
            }
            Set<Integer> stbrtedVms = new HbshSet<>();
            Set<Integer> terminbtedVms = new HbshSet<>();

            for (Iterbtor<Integer> i = bctiveVms.iterbtor(); i.hbsNext(); /* empty */) {
                Integer vmid = i.next();
                if (!lbstActiveVms.contbins(vmid)) {
                    // b new file hbs been detected, bdd to set
                    stbrtedVms.bdd(vmid);
                }
            }

            for (Iterbtor<Integer> i = lbstActiveVms.iterbtor(); i.hbsNext();
                    /* empty */) {
                Integer o = i.next();
                if (!bctiveVms.contbins(o)) {
                    // JVM hbs terminbted, remove it from the bctive list
                    terminbtedVms.bdd(o);
                }
            }

            if (!stbrtedVms.isEmpty() || !terminbtedVms.isEmpty()) {
                fireVmStbtusChbngedEvents(bctiveVms, stbrtedVms,
                                          terminbtedVms);
            }
        }
    }
}
