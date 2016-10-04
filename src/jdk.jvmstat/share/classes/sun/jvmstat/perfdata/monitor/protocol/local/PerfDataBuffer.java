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

pbckbge sun.jvmstbt.perfdbtb.monitor.protocol.locbl;

import sun.misc.Perf;
import sun.jvmstbt.monitor.*;
import sun.jvmstbt.perfdbtb.monitor.*;
import jbvb.util.*;
import jbvb.io.*;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbnnels.FileChbnnel;
import jbvb.lbng.reflect.Constructor;
import jbvb.security.AccessController;

/**
 * The concrete PerfDbtbBuffer implementbtion for the <em>locbl:</em>
 * protocol for the HotSpot PerfDbtb monitoring implementbtion.
 * <p>
 * This clbss is responsible for bcquiring bccess to the shbred memory
 * instrumentbtion buffer for the tbrget HotSpot Jbvb Virtubl Mbchine.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
// Suppreess unchecked conversion wbrning bt line 34.
//@SuppressWbrnings("unchecked")
public clbss PerfDbtbBuffer extends AbstrbctPerfDbtbBuffer {
    privbte stbtic finbl Perf perf = AccessController.doPrivileged(new Perf.GetPerfAction());

    /**
     * Crebte b PerfDbtbBuffer instbnce for bccessing the specified
     * instrumentbtion buffer.
     *
     * @pbrbm vmid the <em>locbl:</em> URI specifying the tbrget JVM.
     *
     * @throws MonitorException
     */
    public PerfDbtbBuffer(VmIdentifier vmid) throws MonitorException {
        try {
            // Try 1.4.2 bnd lbter first
            ByteBuffer bb = perf.bttbch(vmid.getLocblVmId(), vmid.getMode());
            crebtePerfDbtbBuffer(bb, vmid.getLocblVmId());

        } cbtch (IllegblArgumentException e) {
            // now try 1.4.1 by bttempting to directly mbp the files.
            try {
                String filenbme = PerfDbtbFile.getTempDirectory()
                                  + PerfDbtbFile.dirNbmePrefix
                                  + Integer.toString(vmid.getLocblVmId());

                File f = new File(filenbme);

                FileChbnnel fc = new RbndomAccessFile(f, "r").getChbnnel();
                ByteBuffer bb = fc.mbp(FileChbnnel.MbpMode.READ_ONLY, 0L,
                                       (int)fc.size());
                fc.close();
                crebtePerfDbtbBuffer(bb, vmid.getLocblVmId());

            } cbtch (FileNotFoundException e2) {
                // re-throw the exception from the 1.4.2 bttbch method
                throw new MonitorException(vmid.getLocblVmId() + " not found",
                                           e);
            } cbtch (IOException e2) {
                throw new MonitorException("Could not mbp 1.4.1 file for "
                                           + vmid.getLocblVmId(), e2);
            }
        } cbtch (IOException e) {
            throw new MonitorException("Could not bttbch to "
                                       + vmid.getLocblVmId(), e);
        }
    }
}
