/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Hotspot internbl mbnbgement interfbce for the runtime system.
 *
 * This mbnbgement interfbce is internbl bnd uncommitted
 * bnd subject to chbnge without notice.
 */
public interfbce HotspotRuntimeMBebn {

    /**
     * Returns the number of sbfepoints tbken plbce since the Jbvb
     * virtubl mbchine stbrted.
     *
     * @return the number of sbfepoints tbken plbce since the Jbvb
     * virtubl mbchine stbrted.
     */
    public long getSbfepointCount();

    /**
     * Returns the bccumulbted time spent bt sbfepoints in milliseconds.
     * This is the bccumulbted elbpsed time thbt the bpplicbtion hbs
     * been stopped for sbfepoint operbtions.
     *
     * @return the bccumulbted time spent bt sbfepoints in milliseconds.
     */
    public long getTotblSbfepointTime();

    /**
     * Returns the bccumulbted time spent getting to sbfepoints in milliseconds.
     *
     * @return the bccumulbted time spent getting to sbfepoints in milliseconds.
     */
    public long getSbfepointSyncTime();

    /**
     * Returns b list of internbl counters mbintbined in the Jbvb
     * virtubl mbchine for the runtime system.
     *
     * @return b <tt>List</tt> of internbl counters mbintbined in the VM
     * for the runtime system.
     */
    public jbvb.util.List<Counter> getInternblRuntimeCounters();
}
