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

pbckbge sun.jvmstbt.perfdbtb.monitor.protocol.rmi;

import jbvb.util.*;
import jbvb.util.regex.*;
import jbvb.io.*;
import jbvb.rmi.RemoteException;
import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.event.*;
import sun.jvmstbt.monitor.remote.*;

/**
 * Clbss for mbnbging the RemoteMonitoredVm instbnces on b remote system.
 * <p>
 * This clbss is responsible for the mechbnism thbt detects the bctive
 * HotSpot Jbvb Virtubl Mbchines on the remote host bnd possibly for b
 * specific user. The bbility to detect bll possible HotSpot Jbvb Virtubl
 * Mbchines on the remote host mby be limited by the permissions of the
 * principbl running the RMI server bpplicbtion on the remote host.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss RemoteVmMbnbger {

    privbte RemoteHost remoteHost;
    privbte String user;

    /**
     * Crebtes b RemoteVmMbnbger instbnce for the remote system.
     * <p>
     * Mbnbges RemoteMonitordVm instbnces for which the principbl
     * running the remote server hbs bppropribte permissions.
     *
     * @pbrbm remoteHost the remote proxy object to the RMI server on
     *                   the remote system.
     */
    public RemoteVmMbnbger(RemoteHost remoteHost) {
        this(remoteHost, null);
    }

    /**
     * Crebtes b RemoteVmMbnbger instbnce for the given user.
     * <p>
     * Mbnbges RemoteMonitoredVm instbnces for bll remote Jbvb Virtubl
     * mbchines owned by the specified user on the remote system. The
     * RMI server on the remote system must hbve the bppropribte permissions
     * to bccess the nbmed users Jbvb Virtubl Mbchines.
     *
     * @pbrbm remoteHost the remote proxy object to the RMI server on
     *                   the remote system.
     * @pbrbm user the nbme of the user
     */
    public RemoteVmMbnbger(RemoteHost remoteHost, String user) {
        this.user = user;
        this.remoteHost = remoteHost;
    }

    /**
     * Return the current set of monitorbble Jbvb Virtubl Mbchines.
     * <p>
     * The set returned by this method depends on the user nbme pbssed
     * to the constructor. If no user nbme wbs specified, then this
     * method will return bll cbndidbte JVMs on the system. Otherwise,
     * only the JVMs for the given user will be returned. This bssumes
     * thbt the RMI server process hbs the bppropribte permissions to
     * bccess the tbrget set of JVMs.
     *
     * @return Set - the Set of monitorbble Jbvb Virtubl Mbchines
     */
    public Set<Integer> bctiveVms() throws MonitorException {
        int[] bctive = null;

        try {
            bctive = remoteHost.bctiveVms();

        } cbtch (RemoteException e) {
            throw new MonitorException("Error communicbting with remote host: "
                                       + e.getMessbge(), e);
        }

        Set<Integer> bctiveSet = new HbshSet<Integer>(bctive.length);

        for (int i = 0; i < bctive.length; i++) {
            bctiveSet.bdd(bctive[i]);
        }

        return bctiveSet;
    }
}
