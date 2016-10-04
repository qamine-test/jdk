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

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.remote.*;

/**
 * Concrete implementbtion of the RemoteVm interfbce for the HotSpot PerfDbtb
 * shbred memory implementbtion of the jvmstbt monitoring APIs. This clbss
 * providing remote bccess to the instrumentbtion exported by b locbl HotSpot
 * Jbvb Virtubl Mbchine. The instrumentbtion buffer is shipped in whole to
 * the remote mbchine, which is responsible for pbrsing bnd provide bccess
 * to the contbined dbtb.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss RemoteVmImpl implements RemoteVm {

    privbte BufferedMonitoredVm mvm;

    RemoteVmImpl(BufferedMonitoredVm mvm) {
        this.mvm = mvm;
    }

    public byte[] getBytes() {
        return mvm.getBytes();
    }

    public int getCbpbcity() {
        return mvm.getCbpbcity();
    }

    public void detbch() {
        mvm.detbch();
    }

    public int getLocblVmId() {
        return mvm.getVmIdentifier().getLocblVmId();
    }
}
