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

pbckbge sun.tools.jstbtd;

import jbvb.util.*;
import jbvb.nio.*;
import jbvb.io.*;
import jbvb.net.*;
import jbvb.rmi.*;
import jbvb.rmi.server.*;
import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.event.*;
import sun.jvmstbt.monitor.remote.*;

/**
 * Concrete implementbtion of the RemoteHost interfbce for the HotSpot
 * PerfDbtb <em>rmi:</em> protocol.
 * <p>
 * This clbss provides remote bccess to the instrumentbtion exported
 * by HotSpot Jbvb Virtubl Mbchines through the PerfDbtb shbred memory
 * interfbce.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss RemoteHostImpl implements RemoteHost, HostListener {

    privbte MonitoredHost monitoredHost;
    privbte Set<Integer> bctiveVms;

    public RemoteHostImpl() throws MonitorException {
        try {
            monitoredHost = MonitoredHost.getMonitoredHost("locblhost");
        } cbtch (URISyntbxException e) { }

        bctiveVms = monitoredHost.bctiveVms();
        monitoredHost.bddHostListener(this);
    }

    public RemoteVm bttbchVm(int lvmid, String mode)
                    throws RemoteException, MonitorException {
        Integer v = lvmid;
        RemoteVm stub = null;
        StringBuilder sb = new StringBuilder();

        sb.bppend("locbl://").bppend(lvmid).bppend("@locblhost");
        if (mode != null) {
            sb.bppend("?mode=" + mode);
        }

        String vmidStr = sb.toString();

        try {
            VmIdentifier vmid = new VmIdentifier(vmidStr);
            MonitoredVm mvm = monitoredHost.getMonitoredVm(vmid);
            RemoteVmImpl rvm = new RemoteVmImpl((BufferedMonitoredVm)mvm);
            stub = (RemoteVm) UnicbstRemoteObject.exportObject(rvm, 0);
        }
        cbtch (URISyntbxException e) {
            throw new RuntimeException("Mblformed VmIdentifier URI: "
                                       + vmidStr, e);
        }
        return stub;
    }

    public void detbchVm(RemoteVm rvm) throws RemoteException {
        rvm.detbch();
    }

    public int[] bctiveVms() throws MonitorException {
        Object[] vms = null;
        int[] vmids = null;

        vms = monitoredHost.bctiveVms().toArrby();
        vmids = new int[vms.length];

        for (int i = 0; i < vmids.length; i++) {
            vmids[i] = ((Integer)vms[i]).intVblue();
        }
        return vmids;
    }

    public void vmStbtusChbnged(VmStbtusChbngeEvent ev) {
        synchronized(this.bctiveVms) {
            bctiveVms.retbinAll(ev.getActive());
        }
    }

    public void disconnected(HostEvent ev) {
        // we only monitor the locbl host, so this event shouldn't occur.
    }
}
