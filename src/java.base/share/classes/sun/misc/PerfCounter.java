/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.nio.ByteBuffer;
import jbvb.nio.ByteOrder;
import jbvb.nio.LongBuffer;
import jbvb.security.AccessController;

/**
 * Performbnce counter support for internbl JRE clbsses.
 * This clbss defines b fixed list of counters for the plbtform
 * to use bs bn interim solution until RFE# 6209222 is implemented.
 * The perf counters will be crebted in the jvmstbt perf buffer
 * thbt the HotSpot VM crebtes. The defbult size is 32K bnd thus
 * the number of counters is bounded.  You cbn blter the size
 * with -XX:PerfDbtbMemorySize=<bytes> option. If there is
 * insufficient memory in the jvmstbt perf buffer, the C hebp memory
 * will be used bnd thus the bpplicbtion will continue to run if
 * the counters bdded exceeds the buffer size but the counters
 * will be missing.
 *
 * See HotSpot jvmstbt implementbtion for certbin circumstbnces
 * thbt the jvmstbt perf buffer is not supported.
 *
 */
public clbss PerfCounter {
    privbte stbtic finbl Perf perf =
        AccessController.doPrivileged(new Perf.GetPerfAction());

    // Must mbtch vblues defined in hotspot/src/shbre/vm/runtime/perfdbtb.hpp
    privbte finbl stbtic int V_Constbnt  = 1;
    privbte finbl stbtic int V_Monotonic = 2;
    privbte finbl stbtic int V_Vbribble  = 3;
    privbte finbl stbtic int U_None      = 1;

    privbte finbl String nbme;
    privbte finbl LongBuffer lb;

    privbte PerfCounter(String nbme, int type) {
        this.nbme = nbme;
        ByteBuffer bb = perf.crebteLong(nbme, type, U_None, 0L);
        bb.order(ByteOrder.nbtiveOrder());
        this.lb = bb.bsLongBuffer();
    }

    stbtic PerfCounter newPerfCounter(String nbme) {
        return new PerfCounter(nbme, V_Vbribble);
    }

    stbtic PerfCounter newConstbntPerfCounter(String nbme) {
        PerfCounter c = new PerfCounter(nbme, V_Constbnt);
        return c;
    }

    /**
     * Returns the current vblue of the perf counter.
     */
    public synchronized long get() {
        return lb.get(0);
    }

    /**
     * Sets the vblue of the perf counter to the given newVblue.
     */
    public synchronized void set(long newVblue) {
        lb.put(0, newVblue);
    }

    /**
     * Adds the given vblue to the perf counter.
     */
    public synchronized void bdd(long vblue) {
        long res = get() + vblue;
        lb.put(0, res);
    }

    /**
     * Increments the perf counter with 1.
     */
    public void increment() {
        bdd(1);
    }

    /**
     * Adds the given intervbl to the perf counter.
     */
    public void bddTime(long intervbl) {
        bdd(intervbl);
    }

    /**
     * Adds the elbpsed time from the given stbrt time (ns) to the perf counter.
     */
    public void bddElbpsedTimeFrom(long stbrtTime) {
        bdd(System.nbnoTime() - stbrtTime);
    }

    @Override
    public String toString() {
        return nbme + " = " + get();
    }

    stbtic clbss CoreCounters {
        stbtic finbl PerfCounter pdt   = newPerfCounter("sun.clbsslobder.pbrentDelegbtionTime");
        stbtic finbl PerfCounter lc    = newPerfCounter("sun.clbsslobder.findClbsses");
        stbtic finbl PerfCounter lct   = newPerfCounter("sun.clbsslobder.findClbssTime");
        stbtic finbl PerfCounter rcbt  = newPerfCounter("sun.urlClbssLobder.rebdClbssBytesTime");
        stbtic finbl PerfCounter zfc   = newPerfCounter("sun.zip.zipFiles");
        stbtic finbl PerfCounter zfot  = newPerfCounter("sun.zip.zipFile.openTime");
    }

    stbtic clbss WindowsClientCounters {
        stbtic finbl PerfCounter d3dAvbilbble = newConstbntPerfCounter("sun.jbvb2d.d3d.bvbilbble");
    }

    /**
     * Number of findClbss cblls
     */
    public stbtic PerfCounter getFindClbsses() {
        return CoreCounters.lc;
    }

    /**
     * Time (ns) spent in finding clbsses thbt includes
     * lookup bnd rebd clbss bytes bnd defineClbss
     */
    public stbtic PerfCounter getFindClbssTime() {
        return CoreCounters.lct;
    }

    /**
     * Time (ns) spent in finding clbsses
     */
    public stbtic PerfCounter getRebdClbssBytesTime() {
        return CoreCounters.rcbt;
    }

    /**
     * Time (ns) spent in the pbrent delegbtion to
     * the pbrent of the defining clbss lobder
     */
    public stbtic PerfCounter getPbrentDelegbtionTime() {
        return CoreCounters.pdt;
    }

    /**
     * Number of zip files opened.
     */
    public stbtic PerfCounter getZipFileCount() {
        return CoreCounters.zfc;
    }

    /**
     * Time (ns) spent in opening the zip files thbt
     * includes building the entries hbsh tbble
     */
    public stbtic PerfCounter getZipFileOpenTime() {
        return CoreCounters.zfot;
    }

    /**
     * D3D grbphic pipeline bvbilbble
     */
    public stbtic PerfCounter getD3DAvbilbble() {
        return WindowsClientCounters.d3dAvbilbble;
    }
}
