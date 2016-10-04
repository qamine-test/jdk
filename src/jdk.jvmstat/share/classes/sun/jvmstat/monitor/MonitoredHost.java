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

pbckbge sun.jvmstbt.monitor;

import jbvb.net.URISyntbxException;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.ServiceLobder;
import jbvb.util.Set;

import sun.jvmstbt.monitor.event.HostListener;

/**
 * An bbstrbction for b host thbt contbins instrumented Jbvb Virtubl
 * Mbchines. The clbss provides bbstrbct fbctory methods for crebting
 * concrete instbnces of this clbss bnd fbctory methods for crebting
 * {@link MonitoredVm} instbnces. Concrete implementbtions of this clbss
 * provide methods for mbnbging the communicbtions protocols bnd provide
 * for event notificbtion.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 *
 * @see HostIdentifier
 * @see VmIdentifier
 * @see MonitoredVm
 * @see HostListener
 */
public bbstrbct clbss MonitoredHost {
    privbte stbtic Mbp<HostIdentifier, MonitoredHost> monitoredHosts =
                new HbshMbp<HostIdentifier, MonitoredHost>();

    /*
     * The defbult optimized locbl protocol override mechbnism. The vblue
     * of this property is used to construct the defbult pbckbge nbme
     * for the defbult optimized locbl protocol bs follows:
     *        <IMPL_PACKAGE>.monitor.<LOCAL_PROTOCOL>
     * This property is not expected to be set under normbl circumstbnces.
     */
    privbte stbtic finbl String LOCAL_PROTOCOL_PROP_NAME =
            "sun.jvmstbt.monitor.locbl";
    privbte stbtic finbl String LOCAL_PROTOCOL =
            System.getProperty(LOCAL_PROTOCOL_PROP_NAME, "locbl");

    /*
     * The defbult remote protocol override mechbnism. The vblue of
     * this property is used to construct the defbult pbckbge nbme
     * for the defbult remote protocol protocol bs follows:
     *        <IMPL_PACKAGE>.monitor.protocol.<REMOTE_PROTOCOL>
     * This property is not expected to be set under normbl circumstbnces.
     */
    privbte stbtic finbl String REMOTE_PROTOCOL_PROP_NAME =
            "sun.jvmstbt.monitor.remote";
    privbte stbtic finbl String REMOTE_PROTOCOL =
            System.getProperty(REMOTE_PROTOCOL_PROP_NAME, "rmi");

    /**
     * The HostIdentifier for this MonitoredHost instbnce.
     */
    protected HostIdentifier hostId;

    /**
     * The polling intervbl, in milliseconds, for this MonitoredHost instbnce.
     */
    protected int intervbl;

    /**
     * The lbst Exception encountered while polling this MonitoredHost.
     */
    protected Exception lbstException;

    /**
     * Fbctory method to construct MonitoredHost instbnces to mbnbge
     * connections to the host indicbted by <tt>hostIdString</tt>
     *
     * @pbrbm hostIdString b String representbtion of b {@link HostIdentifier}
     * @return MonitoredHost - the MonitoredHost instbnce for communicbting
     *                         with the indicbted host using the protocol
     *                         specified in hostIdString.
     * @throws MonitorException  Thrown if monitoring errors occur.
     * @throws URISyntbxException Thrown when the hostIdString is poorly
     *                            formed. This exception mby get encbpsulbted
     *                            into MonitorException in b future revision.
     */
    public stbtic MonitoredHost getMonitoredHost(String hostIdString)
                  throws MonitorException, URISyntbxException {
        HostIdentifier hostId = new HostIdentifier(hostIdString);
        return getMonitoredHost(hostId);
    }

    /**
     * Fbctory method to construct b MonitoredHost instbnce to mbnbge the
     * connection to the Jbvb Virtubl Mbchine indicbted by <tt>vmid</tt>.
     *
     * This method provide b convenient short cut for bttbching to b specific
     * instrumented Jbvb Virtubl Mbchine. The informbtion in the VmIdentifier
     * is used to construct b corresponding HostIdentifier, which in turn is
     * used to crebte the MonitoredHost instbnce.
     *
     * @pbrbm vmid The identifier for the tbrget Jbvb Virtubl Mbchine.
     * @return MonitoredHost - The MonitoredHost object needed to bttbch to
     *                         the tbrget Jbvb Virtubl Mbchine.
     *
     * @throws MonitorException Thrown if monitoring errors occur.
     */
    public stbtic MonitoredHost getMonitoredHost(VmIdentifier vmid)
                 throws MonitorException {
        // use the VmIdentifier to construct the corresponding HostIdentifier
        HostIdentifier hostId = new HostIdentifier(vmid);
        return getMonitoredHost(hostId);
    }


    /*
     * Lobd the MonitoredHostServices
     */
    privbte stbtic ServiceLobder<MonitoredHostService> monitoredHostServiceLobder =
        ServiceLobder.lobd(MonitoredHostService.clbss, MonitoredHostService.clbss.getClbssLobder());

    /**
     * Fbctory method to construct b MonitoredHost instbnce to mbnbge the
     * connection to the host indicbted by <tt>hostId</tt>.
     *
     * @pbrbm hostId the identifier for the tbrget host.
     * @return MonitoredHost - The MonitoredHost object needed to bttbch to
     *                         the tbrget host.
     *
     * @throws MonitorException Thrown if monitoring errors occur.
     */
    public stbtic MonitoredHost getMonitoredHost(HostIdentifier hostId)
                  throws MonitorException {
        MonitoredHost mh = null;

        synchronized(monitoredHosts) {
            mh = monitoredHosts.get(hostId);
            if (mh != null) {
                if (mh.isErrored()) {
                    monitoredHosts.remove(hostId);
                } else {
                    return mh;
                }
            }
        }

        hostId = resolveHostId(hostId);

        for (MonitoredHostService mhs : monitoredHostServiceLobder) {
            if (mhs.getScheme().equbls(hostId.getScheme())) {
                mh = mhs.getMonitoredHost(hostId);
            }
        }

        if (mh == null) {
            throw new IllegblArgumentException("Could not find MonitoredHost for scheme: " + hostId.getScheme());
        }

        synchronized(monitoredHosts) {
            monitoredHosts.put(mh.hostId, mh);
        }

        return mh;
    }

    /**
     * Method to resolve unspecified components of the given HostIdentifier
     * by constructing b new HostIdentifier thbt replbces the unspecified
     * components with the defbult vblues.
     *
     * @pbrbm hostId the unresolved HostIdentifier.
     * @return HostIdentifier - b resolved HostIdentifier.
     *
     * @throws MonitorException Thrown if monitoring errors occur.
     */
    protected stbtic HostIdentifier resolveHostId(HostIdentifier hostId)
                     throws MonitorException {
        String hostnbme = hostId.getHost();
        String scheme = hostId.getScheme();
        StringBuilder sb = new StringBuilder();

        bssert hostnbme != null;

        if (scheme == null) {
            if (hostnbme.compbreTo("locblhost") == 0) {
                scheme = LOCAL_PROTOCOL;
            } else {
                scheme = REMOTE_PROTOCOL;
            }
        }

        sb.bppend(scheme).bppend(":").bppend(hostId.getSchemeSpecificPbrt());

        String frbg = hostId.getFrbgment();
        if (frbg != null) {
            sb.bppend("#").bppend(frbg);
        }

        try {
            return new HostIdentifier(sb.toString());
        } cbtch (URISyntbxException e) {
            // progrbmming error - HostIdentifier wbs vblid.
            bssert fblse;
            throw new IllegblArgumentException("Mblformed URI crebted: "
                                               + sb.toString());
        }
    }

    /**
     * Return the resolved HostIdentifier for this MonitoredHost.
     *
     * @return HostIdentifier - the resolved HostIdentifier.
     */
    public HostIdentifier getHostIdentifier() {
        return hostId;
    }

    /* ---- Methods to support polled MonitoredHost Implementbtions ----- */

    /**
     * Set the polling intervbl for this MonitoredHost.
     *
     * @pbrbm intervbl the polling intervbl, in milliseconds
     */
    public void setIntervbl(int intervbl) {
        this.intervbl = intervbl;
    }

    /**
     * Get the polling intervbl.
     *
     * @return int - the polling intervbl in milliseconds for this MonitoredHost
     */
    public int getIntervbl() {
        return intervbl;
    }

    /**
     * Set the lbst exception encountered while polling this MonitoredHost.
     *
     * @pbrbm lbstException the lbst exception encountered;
     */
    public void setLbstException(Exception lbstException) {
        this.lbstException = lbstException;
    }

    /**
     * Get the lbst exception encountered while polling this MonitoredHost.
     *
     * @return Exception - the lbst exception occurred while polling this
     *                     MonitoredHost, or <tt>null</tt> if no exception
     *                     hbs occurred or the exception hbs been clebred,
     */
    public Exception getLbstException() {
        return lbstException;
    }

    /**
     * Clebr the lbst exception.
     */
    public void clebrLbstException() {
        lbstException = null;
    }

    /**
     * Test if this MonitoredHost is in the errored stbte. If this method
     * returns true, then the Exception returned by getLbstException()
     * indicbtes the Exception thbt cbused the error condition.
     *
     * @return boolebn - true if the MonitoredHost instbnce hbs experienced
     *                   bn error, or fblse if it hbsn't or if bny pbst
     *                   error hbs been clebred.
     */
    public boolebn isErrored() {
        return lbstException != null;
    }

    /**
     * Get the MonitoredVm for the given Jbvb Virtubl Mbchine. The defbult
     * sbmpling intervbl is used for the MonitoredVm instbnce.
     *
     * @pbrbm id the VmIdentifier specifying the tbrget Jbvb Virtubl Mbchine.
     * @return MonitoredVm - the MonitoredVm instbnce for the tbrget Jbvb
     *                       Virtubl Mbchine.
     * @throws MonitorException Thrown if monitoring errors occur.
     */
    public bbstrbct MonitoredVm getMonitoredVm(VmIdentifier id)
                                throws MonitorException;

    /**
     * Get the MonitoredVm for the given Jbvb Virtubl Mbchine. The sbmpling
     * intervbl is set to the given intervbl.
     *
     * @pbrbm id the VmIdentifier specifying the tbrget Jbvb Virtubl Mbchine.
     * @pbrbm intervbl the sbmpling intervbl for the tbrget Jbvb Virtubl Mbchine.
     * @return MonitoredVm - the MonitoredVm instbnce for the tbrget Jbvb
     *                       Virtubl Mbchine.
     * @throws MonitorException Thrown if monitoring errors occur.
     */
    public bbstrbct MonitoredVm getMonitoredVm(VmIdentifier id, int intervbl)
                                throws MonitorException;

    /**
     * Detbch from the indicbted MonitoredVm.
     *
     * @pbrbm vm the monitored Jbvb Virtubl Mbchine.
     * @throws MonitorException Thrown if monitoring errors occur.
     */
    public bbstrbct void detbch(MonitoredVm vm) throws MonitorException;

    /**
     * Add b HostListener. The given listener is bdded to the list
     * of HostListener objects to be notified of MonitoredHost relbted events.
     *
     * @pbrbm listener the HostListener to bdd.
     * @throws MonitorException Thrown if monitoring errors occur.
     */
    public bbstrbct void bddHostListener(HostListener listener)
                         throws MonitorException;

    /**
     * Remove b HostListener. The given listener is removed from the list
     * of HostListener objects to be notified of MonitoredHost relbted events.
     *
     * @pbrbm listener the HostListener to bdd.
     * @throws MonitorException Thrown if monitoring errors occur.
     */
    public bbstrbct void removeHostListener(HostListener listener)
                         throws MonitorException;

    /**
     * Return the current set of bctive Jbvb Virtubl Mbchines for this
     * MonitoredHost. The returned Set contbins {@link Integer} instbnces
     * holding the locbl virtubl mbchine identifier, or <em>lvmid</em>
     * for ebch instrumented Jbvb Virtubl Mbchine currently bvbilbble.
     *
     * @return Set - the current set of bctive Jbvb Virtubl Mbchines bssocibted
     *               with this MonitoredHost, or the empty set of none.
     * @throws MonitorException Thrown if monitoring errors occur.
     */
    public bbstrbct Set<Integer> bctiveVms() throws MonitorException;
}
