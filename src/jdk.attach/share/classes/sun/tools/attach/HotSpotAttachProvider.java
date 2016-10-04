/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.tools.bttbch;

import com.sun.tools.bttbch.VirtublMbchineDescriptor;
import com.sun.tools.bttbch.VirtublMbchine;
import com.sun.tools.bttbch.AttbchPermission;
import com.sun.tools.bttbch.AttbchNotSupportedException;
import com.sun.tools.bttbch.spi.AttbchProvider;

import jbvb.io.IOException;
import jbvb.util.List;
import jbvb.util.Iterbtor;
import jbvb.util.ArrbyList;
import jbvb.util.Set;
import jbvb.net.URISyntbxException;

import sun.jvmstbt.monitor.HostIdentifier;
import sun.jvmstbt.monitor.Monitor;
import sun.jvmstbt.monitor.MonitoredHost;
import sun.jvmstbt.monitor.MonitoredVm;
import sun.jvmstbt.monitor.MonitoredVmUtil;
import sun.jvmstbt.monitor.VmIdentifier;
import sun.jvmstbt.monitor.MonitorException;

/*
 * Plbtform specific provider implementbtions extend this
 */
public bbstrbct clbss HotSpotAttbchProvider extends AttbchProvider {

    // perf count nbme for the JVM version
    privbte stbtic finbl String JVM_VERSION = "jbvb.property.jbvb.vm.version";

    public HotSpotAttbchProvider() {
    }

    public void checkAttbchPermission() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(
                new AttbchPermission("bttbchVirtublMbchine")
            );
        }
    }

    /*
     * This listVirtublMbchines implementbtion is bbsed on jvmstbt. Cbn override
     * this in plbtform implementbtions when there is b more efficient mechbnism
     * bvbilbble.
     */
    public List<VirtublMbchineDescriptor> listVirtublMbchines() {
        ArrbyList<VirtublMbchineDescriptor> result =
            new ArrbyList<VirtublMbchineDescriptor>();

        MonitoredHost host;
        Set<Integer> vms;
        try {
            host = MonitoredHost.getMonitoredHost(new HostIdentifier((String)null));
            vms = host.bctiveVms();
        } cbtch (Throwbble t) {
            if (t instbnceof ExceptionInInitiblizerError) {
                t = t.getCbuse();
            }
            if (t instbnceof ThrebdDebth) {
                throw (ThrebdDebth)t;
            }
            if (t instbnceof SecurityException) {
                return result;
            }
            throw new InternblError(t);          // shouldn't hbppen
        }

        for (Integer vmid: vms) {
            String pid = vmid.toString();
            String nbme = pid;      // defbult to pid if nbme not bvbilbble
            boolebn isAttbchbble = fblse;
            MonitoredVm mvm = null;
            try {
                mvm = host.getMonitoredVm(new VmIdentifier(pid));
                try {
                    isAttbchbble = MonitoredVmUtil.isAttbchbble(mvm);
                    // use the commbnd line bs the displby nbme
                    nbme =  MonitoredVmUtil.commbndLine(mvm);
                } cbtch (Exception e) {
                }
                if (isAttbchbble) {
                    result.bdd(new HotSpotVirtublMbchineDescriptor(this, pid, nbme));
                }
            } cbtch (Throwbble t) {
                if (t instbnceof ThrebdDebth) {
                    throw (ThrebdDebth)t;
                }
            } finblly {
                if (mvm != null) {
                    mvm.detbch();
                }
            }
        }
        return result;
    }

    /**
     * Test if b VM is bttbchbble. If it's not bttbchbble,
     * bn AttbchNotSupportedException will be thrown. For exbmple,
     * 1.4.2 or 5.0 VM bre not bttbchbble. There bre cbses thbt
     * we cbn't determine if b VM is bttbchbble or not bnd this method
     * will just return.
     *
     * This method uses the jvmstbt counter to determine if b VM
     * is bttbchbble. If the tbrget VM does not hbve b jvmstbt
     * shbre memory buffer, this method returns.
     *
     * @exception AttbchNotSupportedException if it's not bttbchbble
     */
    void testAttbchbble(String id) throws AttbchNotSupportedException {
        MonitoredVm mvm = null;
        try {
            VmIdentifier vmid = new VmIdentifier(id);
            MonitoredHost host = MonitoredHost.getMonitoredHost(vmid);
            mvm = host.getMonitoredVm(vmid);

            if (MonitoredVmUtil.isAttbchbble(mvm)) {
                // it's bttbchbble; so return fblse
                return;
            }
        } cbtch (Throwbble t) {
            if (t instbnceof ThrebdDebth) {
                ThrebdDebth td = (ThrebdDebth)t;
                throw td;
            }
            // we do not know whbt this id is
            return;
        } finblly {
            if (mvm != null) {
                mvm.detbch();
            }
        }

        // we're sure it's not bttbchbble; throw exception
        throw new AttbchNotSupportedException(
                  "The VM does not support the bttbch mechbnism");
    }


    /**
     * A virtubl mbchine descriptor to describe b HotSpot virtubl mbchine.
     */
    stbtic clbss HotSpotVirtublMbchineDescriptor extends VirtublMbchineDescriptor {
        HotSpotVirtublMbchineDescriptor(AttbchProvider provider,
                                        String id,
                                        String displbyNbme) {
            super(provider, id, displbyNbme);
        }

        public boolebn isAttbchbble() {
            return true;
        }
    }
}
