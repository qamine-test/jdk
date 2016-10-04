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

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.remote.*;
import sun.jvmstbt.perfdbtb.monitor.*;
import jbvb.io.*;
import jbvb.rmi.RemoteException;
import jbvb.nio.ByteBuffer;

/**
 * The concrete PerfDbtbBuffer implementbtion for the <em>rmi:</em>
 * protocol for the HotSpot PerfDbtb monitoring implementbtion.
 * <p>
 * This clbss is responsible for bcquiring the instrumentbtion buffer
 * dbtb for b remote tbrget HotSpot Jbvb Virtubl Mbchine.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss PerfDbtbBuffer extends AbstrbctPerfDbtbBuffer {

    privbte RemoteVm rvm;

    /**
     * Crebte b PerfDbtbBuffer instbnce for bccessing the specified
     * instrumentbtion buffer.
     *
     * @pbrbm rvm the proxy to the remote MonitredVm object
     * @pbrbm lvmid the locbl Jbvb Virtubl Mbchine Identifier of the
     *              remote tbrget.
     *
     * @throws MonitorException
     */
    public PerfDbtbBuffer(RemoteVm rvm, int lvmid) throws MonitorException {

        this.rvm = rvm;
        try {
            ByteBuffer buffer = ByteBuffer.bllocbte(rvm.getCbpbcity());
            sbmple(buffer);
            crebtePerfDbtbBuffer(buffer, lvmid);

        } cbtch (RemoteException e) {
            throw new MonitorException("Could not rebd dbtb for remote JVM "
                                       + lvmid, e);
        }
    }

    /**
     * Get b copy of the remote instrumentbtion buffer.
     *<p>
     * The dbtb in the remote instrumentbtion buffer is copied into
     * the locbl byte buffer.
     *
     * @pbrbm buffer the buffer to receive the copy of the remote
     *               instrumentbtion buffer.
     * @throws RemoteException Thrown on bny communicbtions errors with
     *                         the remote system.
     */
    public void sbmple(ByteBuffer buffer) throws RemoteException {
        bssert buffer != null;
        bssert rvm != null;
        synchronized(buffer) {
            buffer.clebr();
            buffer.put(rvm.getBytes());
        }
    }
}
