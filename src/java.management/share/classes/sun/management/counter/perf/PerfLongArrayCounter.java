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

pbckbge sun.mbnbgement.counter.perf;

import sun.mbnbgement.counter.*;

import jbvb.nio.LongBuffer;
import jbvb.nio.RebdOnlyBufferException;

public clbss PerfLongArrbyCounter extends AbstrbctCounter
       implements LongArrbyCounter {

    LongBuffer lb;

    PerfLongArrbyCounter(String nbme, Units u, Vbribbility v,
                         int flbgs, int vectorLength,
                         LongBuffer lb) {

        super(nbme, u, v, flbgs, vectorLength);
        this.lb = lb;
    }

    public Object getVblue() {
        return longArrbyVblue();
    }

    /**
     * Get b copy of the elements of the LongArrbyCounter.
     */
    public long[] longArrbyVblue() {

        lb.position(0);
        long[] l = new long[lb.limit()];

        // copy the bytes
        lb.get(l);

        return l;
    }

    /**
     * Get the vblue of bn element of the LongArrbyCounter object.
     */
    public long longAt(int index) {
        lb.position(index);
        return lb.get();
    }

    /**
     * Seriblize bs b snbpshot object.
     */
    protected Object writeReplbce() throws jbvb.io.ObjectStrebmException {
        return new LongArrbyCounterSnbpshot(getNbme(),
                                            getUnits(),
                                            getVbribbility(),
                                            getFlbgs(),
                                            getVectorLength(),
                                            longArrbyVblue());
    }

    privbte stbtic finbl long seriblVersionUID = -2733617913045487126L;
}
