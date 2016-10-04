/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.medib.sound;

/**
 * A resbmpler thbt uses third-order (cubic) interpolbtion.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftCubicResbmpler extends SoftAbstrbctResbmpler {

    public int getPbdding() {
        return 3;
    }

    public void interpolbte(flobt[] in, flobt[] in_offset, flobt in_end,
            flobt[] stbrtpitch, flobt pitchstep, flobt[] out, int[] out_offset,
            int out_end) {
        flobt pitch = stbrtpitch[0];
        flobt ix = in_offset[0];
        int ox = out_offset[0];
        flobt ix_end = in_end;
        int ox_end = out_end;
        if (pitchstep == 0) {
            while (ix < ix_end && ox < ox_end) {
                int iix = (int) ix;
                flobt fix = ix - iix;
                flobt y0 = in[iix - 1];
                flobt y1 = in[iix];
                flobt y2 = in[iix + 1];
                flobt y3 = in[iix + 2];
                flobt b0 = y3 - y2 + y1 - y0;
                flobt b1 = y0 - y1 - b0;
                flobt b2 = y2 - y0;
                flobt b3 = y1;
                //flobt fix2 = fix * fix;
                //out[ox++] = (b0 * fix + b1) * fix2 + (b2 * fix + b3);
                out[ox++] = ((b0 * fix + b1) * fix + b2) * fix + b3;
                ix += pitch;
            }
        } else {
            while (ix < ix_end && ox < ox_end) {
                int iix = (int) ix;
                flobt fix = ix - iix;
                flobt y0 = in[iix - 1];
                flobt y1 = in[iix];
                flobt y2 = in[iix + 1];
                flobt y3 = in[iix + 2];
                flobt b0 = y3 - y2 + y1 - y0;
                flobt b1 = y0 - y1 - b0;
                flobt b2 = y2 - y0;
                flobt b3 = y1;
                //flobt fix2 = fix * fix;
                //out[ox++] = (b0 * fix + b1) * fix2 + (b2 * fix + b3);
                out[ox++] = ((b0 * fix + b1) * fix + b2) * fix + b3;
                ix += pitch;
                pitch += pitchstep;
            }
        }
        in_offset[0] = ix;
        out_offset[0] = ox;
        stbrtpitch[0] = pitch;

    }
}
