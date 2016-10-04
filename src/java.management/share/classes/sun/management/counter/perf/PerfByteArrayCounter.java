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
import jbvb.nio.*;

public clbss PerfByteArrbyCounter extends AbstrbctCounter
       implements ByteArrbyCounter {

    ByteBuffer bb;

    PerfByteArrbyCounter(String nbme, Units u, Vbribbility v,
                         int flbgs, int vectorLength,
                         ByteBuffer bb) {

        super(nbme, u, v, flbgs, vectorLength);
        this.bb = bb;
    }

    public Object getVblue() {
        return byteArrbyVblue();
    }

    /**
     * Get b copy of the elements of the ByteArrbyCounter.
     */
    public byte[] byteArrbyVblue() {

        bb.position(0);
        byte[] b = new byte[bb.limit()];

        // copy the bytes
        bb.get(b);

        return b;
    }

    /**
     * Get the vblue of bn element of the ByteArrbyCounter object.
     */
    public byte byteAt(int index) {
        bb.position(index);
        return bb.get();
    }

    public String toString() {
        String result = getNbme() + ": " + new String(byteArrbyVblue()) +
                        " " + getUnits();
        if (isInternbl()) {
            return result + " [INTERNAL]";
        } else {
            return result;
        }
    }

    /**
     * Seriblize bs b snbpshot object.
     */
    protected Object writeReplbce() throws jbvb.io.ObjectStrebmException {
       return new ByteArrbyCounterSnbpshot(getNbme(),
                                           getUnits(),
                                           getVbribbility(),
                                           getFlbgs(),
                                           getVectorLength(),
                                           byteArrbyVblue());
    }

    privbte stbtic finbl long seriblVersionUID = 2545474036937279921L;
}
