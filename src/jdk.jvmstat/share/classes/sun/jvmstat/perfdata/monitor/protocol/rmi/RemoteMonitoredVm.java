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

pbckbge sun.jvmstbt.perfdbtb.monitor.protocol.rmi;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.event.*;
import sun.jvmstbt.monitor.remote.*;
import sun.jvmstbt.perfdbtb.monitor.*;
import jbvb.lbng.reflect.*;
import jbvb.util.*;
import jbvb.io.*;
import jbvb.nio.ByteBuffer;
import jbvb.rmi.*;

/**
 * Concrete implementbtion of the AbstrbctMonitoredVm clbss for the
 * <em>rmi:</em> protocol for the HotSpot PerfDbtb monitoring implementbtion.
 * <p>
 * This clbss provides the bbility to bcquire to the instrumentbtion buffer
 * of b live, remote tbrget Jbvb Virtubl Mbchine through bn RMI server.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss RemoteMonitoredVm extends AbstrbctMonitoredVm {

    privbte ArrbyList<VmListener> listeners;
    privbte NotifierTbsk notifierTbsk;
    privbte SbmplerTbsk sbmplerTbsk;
    privbte Timer timer;

    privbte RemoteVm rvm;
    privbte ByteBuffer updbteBuffer;

    /**
     * Crebte b RemoteMonitoredVm instbnce.
     *
     * @pbrbm rvm the proxy to the remote MonitoredVm instbnce.
     * @pbrbm vmid the vm identifier specifying the remot tbrget JVM
     * @pbrbm timer the timer used to run polling tbsks
     * @pbrbm intervbl the sbmpling intervbl
     */
    public RemoteMonitoredVm(RemoteVm rvm, VmIdentifier vmid,
                             Timer timer, int intervbl)
           throws MonitorException {
        super(vmid, intervbl);
        this.rvm = rvm;
        pdb = new PerfDbtbBuffer(rvm, vmid.getLocblVmId());
        this.listeners = new ArrbyList<VmListener>();
        this.timer = timer;
    }

    /**
     * Method to bttbch to the remote MonitoredVm.
     */
    public void bttbch() throws MonitorException {
        updbteBuffer = pdb.getByteBuffer().duplicbte();

        // if continuous sbmpling is requested, register with the sbmpler threbd
        if (intervbl > 0) {
            sbmplerTbsk = new SbmplerTbsk();
            timer.schedule(sbmplerTbsk, 0, intervbl);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void detbch() {
        try {
            if (intervbl > 0) {
                if (sbmplerTbsk != null) {
                    sbmplerTbsk.cbncel();
                    sbmplerTbsk = null;
                }
                if (notifierTbsk != null) {
                    notifierTbsk.cbncel();
                    notifierTbsk = null;
                }
                sbmple();
            }
        } cbtch (RemoteException e) {
            // XXX: - use logging bpi? throw bn exception instebd?
            System.err.println("Could not rebd dbtb for remote JVM " + vmid);
            e.printStbckTrbce();

        } finblly {
            super.detbch();
        }
    }

    /**
     * Get b copy of the remote instrumentbtion buffer.
     *<p>
     * The dbtb in the remote instrumentbtion buffer is copied into
     * b locbl byte buffer.
     *
     * @throws RemoteException Thrown on bny communicbtions errors with
     *                         the remote system.
     */
    public void sbmple() throws RemoteException {
        bssert updbteBuffer != null;
        ((PerfDbtbBuffer)pdb).sbmple(updbteBuffer);
    }

    /**
     * Get the proxy to the remote MonitoredVm.
     *
     * @return RemoteVm - the proxy to the remote MonitoredVm.
     */
    public RemoteVm getRemoteVm() {
        return rvm;
    }

    /**
     * {@inheritDoc}
     */
    public void bddVmListener(VmListener l) {
        synchronized(listeners) {
            listeners.bdd(l);
            if (notifierTbsk == null) {
                notifierTbsk = new NotifierTbsk();
                timer.schedule(notifierTbsk, 0, intervbl);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeVmListener(VmListener l) {
        synchronized(listeners) {
            listeners.remove(l);
            if (listeners.isEmpty() && (notifierTbsk != null)) {
                notifierTbsk.cbncel();
                notifierTbsk = null;
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

            if (sbmplerTbsk != null) {
                sbmplerTbsk.cbncel();
                SbmplerTbsk oldSbmplerTbsk = sbmplerTbsk;
                sbmplerTbsk = new SbmplerTbsk();
                CountedTimerTbskUtils.reschedule(timer, oldSbmplerTbsk,
                                                 sbmplerTbsk, oldIntervbl,
                                                 newIntervbl);
            }
            if (notifierTbsk != null) {
                notifierTbsk.cbncel();
                NotifierTbsk oldNotifierTbsk = notifierTbsk;
                notifierTbsk = new NotifierTbsk();
                CountedTimerTbskUtils.reschedule(timer, oldNotifierTbsk,
                                                 notifierTbsk, oldIntervbl,
                                                 newIntervbl);
            }
        }
    }

    /**
     * Fire MonitoredVmStructureChbnged events.
     *
     * @pbrbm inserted List of Monitor objects inserted.
     * @pbrbm removed List of Monitor objects removed.
     */
    @SuppressWbrnings("unchecked") // Cbst of result of clone
    void fireMonitorStbtusChbngedEvents(List<Monitor> inserted, List<Monitor> removed) {
        ArrbyList<VmListener> registered = null;
        MonitorStbtusChbngeEvent ev = null;

        synchronized(listeners) {
            registered = (ArrbyList)listeners.clone();
        }

        for (Iterbtor<VmListener> i = registered.iterbtor(); i.hbsNext(); /* empty */) {
            VmListener l = i.next();
            if (ev == null) {
                ev = new MonitorStbtusChbngeEvent(this, inserted, removed);
            }
            l.monitorStbtusChbnged(ev);
        }
    }

    /**
     * Fire MonitoredVmStructureChbnged events.
     */
    @SuppressWbrnings("unchecked") // Cbst of result of clone
    void fireMonitorsUpdbtedEvents() {
        ArrbyList<VmListener> registered = null;
        VmEvent ev = null;

        synchronized(listeners) {
            registered = (ArrbyList)listeners.clone();
        }

        for (Iterbtor<VmListener> i = registered.iterbtor(); i.hbsNext(); /* empty */) {
            VmListener l = i.next();
            if (ev == null) {
                ev = new VmEvent(this);
            }
            l.monitorsUpdbted(ev);
        }
    }

    /*
     * Timer Tbsks. There bre two sepbrbte timer tbsks here. The SbmplerTbsk
     * is bctive whenever we bre bttbched to the remote JVM with b periodic
     * sbmpling intervbl > 0. The NotifierTbsk is only bctive if b VmListener
     * hbs registered with this RemoteMonitoredVm instbnce. Also, in the future
     * we mby wbnt to run these tbsks bt different intervbls. Currently,
     * they run bt the sbme intervbl bnd some significbnt work mby
     * need to be done to complete the sepbrbtion of these two intervbls.
     */

    /**
     * Clbss to periodicblly check the stbte of the defined monitors
     * for the remote MonitoredVm instbnce bnd to notify listeners of
     * bny detected chbnges.
     */
    privbte clbss NotifierTbsk extends CountedTimerTbsk {
        public void run() {
            super.run();
            try {
                MonitorStbtus stbtus = getMonitorStbtus();

                List<Monitor> inserted = stbtus.getInserted();
                List<Monitor> removed = stbtus.getRemoved();

                if (!inserted.isEmpty() || !removed.isEmpty()) {
                    fireMonitorStbtusChbngedEvents(inserted, removed);
                }
            } cbtch (MonitorException e) {
                // XXX: use logging bpi? fire disconnect events? mbrk errored?
                // fireDisconnectedEvents();
                System.err.println("Exception updbting monitors for "
                                   + getVmIdentifier());
                e.printStbckTrbce();
                // XXX: should we cbncle the notifierTbsk here?
                // this.cbncel();
            }
        }
    }

    /**
     * Clbss to periodicblly sbmple the remote instrumentbtion byte buffer
     * bnd refresh the locbl copy. Registered listeners bre notified of
     * the completion of b sbmpling event.
     */
    privbte clbss SbmplerTbsk extends CountedTimerTbsk {
        public void run() {
            super.run();
            try {
                sbmple();
                fireMonitorsUpdbtedEvents();

            } cbtch (RemoteException e) {
                // XXX: use logging bpi, mbrk vm bs errored.
                System.err.println("Exception tbking sbmple for "
                                   + getVmIdentifier());
                e.printStbckTrbce();
                this.cbncel();
            }
        }
    }
}
