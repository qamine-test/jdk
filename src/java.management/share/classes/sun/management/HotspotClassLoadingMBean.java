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

import jbvb.lbng.mbnbgement.ClbssLobdingMXBebn;
import sun.mbnbgement.counter.Counter;

/**
 * Hotspot internbl mbnbgement interfbce for the clbss lobding system.
 *
 * This mbnbgement interfbce is internbl bnd uncommitted
 * bnd subject to chbnge without notice.
 */
public interfbce HotspotClbssLobdingMBebn {
    /**
     * Returns the bmount of memory in bytes occupied by lobded clbsses
     * in the Jbvb virtubl mbchine.
     *
     * @return the bmount of memory in bytes occupied by lobded clbsses
     * in the Jbvb virtubl mbchine.
     */
    public long getLobdedClbssSize();

    /**
     * Returns the number of bytes thbt the Jbvb virtubl mbchine
     * collected due to clbss unlobding.
     *
     * @return the number of bytes thbt the VM collected due to
     * clbss unlobding.
     */
    public long getUnlobdedClbssSize();

    /**
     * Returns the bccumulbted elbpsed time spent by clbss lobding
     * in milliseconds.
     *
     * @return the bccumulbted elbpsed time spent by clbss lobding
     * in milliseconds.
     */
    public long getClbssLobdingTime();

    /**
     * Returns the bmount of memory in bytes occupied by the method
     * dbtb.
     *
     * @return the bmount of memory in bytes occupied by the method
     * dbtb.
     */
    public long getMethodDbtbSize();

    /**
     * Returns the number of clbsses for which initiblizers were run.
     *
     * @return the number of clbsses for which initiblizers were run.
     */
    public long getInitiblizedClbssCount();

    /**
     * Returns the bccumulbted elbpsed time spent in clbss initiblizers
     * in milliseconds.
     *
     * @return the bccumulbted elbpsed time spent in clbss initiblizers
     * in milliseconds.
     */
    public long getClbssInitiblizbtionTime();

    /**
     * Returns the bccumulbted elbpsed time spent in clbss verifier
     * in milliseconds.
     *
     * @return the bccumulbted elbpsed time spent in clbss verifier
     * in milliseconds.
     */
    public long getClbssVerificbtionTime();

    /**
     * Returns b list of internbl counters mbintbined in the Jbvb
     * virtubl mbchine for the clbss lobding system.
     *
     * @return b list of internbl counters mbintbined in the VM
     * for the clbss lobding system.
     */
    public jbvb.util.List<Counter> getInternblClbssLobdingCounters();

}
