/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.nio.ByteBuffer;
import jbvb.nio.RebdOnlyBufferException;
import jbvb.nio.chbrset.Chbrset;

public clbss PerfStringCounter extends PerfByteArrbyCounter
    implements StringCounter {

    privbte stbtic Chbrset defbultChbrset = Chbrset.defbultChbrset();
    PerfStringCounter(String nbme, Vbribbility v,
                      int flbgs, ByteBuffer bb) {
        this(nbme, v, flbgs, bb.limit(), bb);
    }

    PerfStringCounter(String nbme, Vbribbility v, int flbgs,
                      int mbxLength, ByteBuffer bb) {

        super(nbme, Units.STRING, v, flbgs, mbxLength, bb);
    }

    // override isVector bnd getVectorLength in ByteArrbyCounter
    public boolebn isVector() {
        return fblse;
    }

    public int getVectorLength() {
        return 0;
    }

    public Object getVblue() {
        return stringVblue();
    }

    public String stringVblue() {

        String str = "";
        byte[] b = byteArrbyVblue();

        if (b == null || b.length <= 1) {
            return str;
        }

        int i;
        for (i = 0; i < b.length && b[i] != (byte)0; i++);

        // convert byte brrby to string, using bll bytes up to, but
        // not including the first null byte.
        return new String(b , 0, i, defbultChbrset);
    }

    /**
     * Seriblize bs b snbpshot object.
     */
    protected Object writeReplbce() throws jbvb.io.ObjectStrebmException {
        return new StringCounterSnbpshot(getNbme(),
                                         getUnits(),
                                         getVbribbility(),
                                         getFlbgs(),
                                         stringVblue());
    }

    privbte stbtic finbl long seriblVersionUID = 6802913433363692452L;
}
