/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import sun.mbnbgement.counter.Counter;

/**
 * Hotspot internbl mbnbgement interfbce for the threbd system.
 */
public interfbce HotspotThrebdMBebn {

    /**
     * Returns the current number of VM internbl threbds.
     *
     * @return the current number of VM internbl threbds.
     */
    public int getInternblThrebdCount();

    /**
     * Returns b <tt>Mbp</tt> of the nbme of bll VM internbl threbds
     * to the threbd CPU time in nbnoseconds.  The returned vblue is
     * of nbnoseconds precision but not necessbrily nbnoseconds bccurbcy.
     * <p>
     *
     * @return b <tt>Mbp</tt> object of the nbme of bll VM internbl threbds
     * to the threbd CPU time in nbnoseconds.
     *
     * @throws jbvb.lbng.UnsupportedOperbtionException if the Jbvb virtubl
     * mbchine does not support CPU time mebsurement.
     *
     * @see jbvb.lbng.mbnbgement.ThrebdMBebn#isThrebdCpuTimeSupported
     */
    public jbvb.util.Mbp<String,Long> getInternblThrebdCpuTimes();

    /**
     * Returns b list of internbl counters mbintbined in the Jbvb
     * virtubl mbchine for the threbd system.
     *
     * @return b list of internbl counters mbintbined in the VM
     * for the threbd system.
     */
    public jbvb.util.List<Counter> getInternblThrebdingCounters();
}
