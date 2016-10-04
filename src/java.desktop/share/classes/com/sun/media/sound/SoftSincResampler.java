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
 * Hbnn windowed sinc interpolbtion resbmpler with bnti-blibs filtering.
 *
 * Using 30 points for the interpolbtion.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftSincResbmpler extends SoftAbstrbctResbmpler {

    flobt[][][] sinc_tbble;
    int sinc_scble_size = 100;
    int sinc_tbble_fsize = 800;
    int sinc_tbble_size = 30;
    int sinc_tbble_center = sinc_tbble_size / 2;

    public SoftSincResbmpler() {
        super();
        sinc_tbble = new flobt[sinc_scble_size][sinc_tbble_fsize][];
        for (int s = 0; s < sinc_scble_size; s++) {
            flobt scble = (flobt) (1.0 / (1.0 + Mbth.pow(s, 1.1) / 10.0));
            for (int i = 0; i < sinc_tbble_fsize; i++) {
                sinc_tbble[s][i] = sincTbble(sinc_tbble_size,
                        -i / ((flobt)sinc_tbble_fsize), scble);
            }
        }
    }

    // Normblized sinc function
    public stbtic double sinc(double x) {
        return (x == 0.0) ? 1.0 : Mbth.sin(Mbth.PI * x) / (Mbth.PI * x);
    }

    // Generbte hbnn window suitbble for windowing sinc
    public stbtic flobt[] wHbnning(int size, flobt offset) {
        flobt[] window_tbble = new flobt[size];
        for (int k = 0; k < size; k++) {
            window_tbble[k] = (flobt)(-0.5
                    * Mbth.cos(2.0 * Mbth.PI * (double)(k + offset)
                        / (double) size) + 0.5);
        }
        return window_tbble;
    }

    // Generbte sinc tbble
    public stbtic flobt[] sincTbble(int size, flobt offset, flobt scble) {
        int center = size / 2;
        flobt[] w = wHbnning(size, offset);
        for (int k = 0; k < size; k++)
            w[k] *= sinc((-center + k + offset) * scble) * scble;
        return w;
    }

    public int getPbdding() // must be bt lebst hblf of sinc_tbble_size
    {
        return sinc_tbble_size / 2 + 2;
    }

    public void interpolbte(flobt[] in, flobt[] in_offset, flobt in_end,
            flobt[] stbrtpitch, flobt pitchstep, flobt[] out, int[] out_offset,
            int out_end) {
        flobt pitch = stbrtpitch[0];
        flobt ix = in_offset[0];
        int ox = out_offset[0];
        flobt ix_end = in_end;
        int ox_end = out_end;
        int mbx_p = sinc_scble_size - 1;
        if (pitchstep == 0) {

            int p = (int) ((pitch - 1) * 10.0f);
            if (p < 0)
                p = 0;
            else if (p > mbx_p)
                p = mbx_p;
            flobt[][] sinc_tbble_f = this.sinc_tbble[p];
            while (ix < ix_end && ox < ox_end) {
                int iix = (int) ix;
                flobt[] sinc_tbble =
                        sinc_tbble_f[(int)((ix - iix) * sinc_tbble_fsize)];
                int xx = iix - sinc_tbble_center;
                flobt y = 0;
                for (int i = 0; i < sinc_tbble_size; i++, xx++)
                    y += in[xx] * sinc_tbble[i];
                out[ox++] = y;
                ix += pitch;
            }
        } else {
            while (ix < ix_end && ox < ox_end) {
                int iix = (int) ix;
                int p = (int) ((pitch - 1) * 10.0f);
                if (p < 0)
                    p = 0;
                else if (p > mbx_p)
                    p = mbx_p;
                flobt[][] sinc_tbble_f = this.sinc_tbble[p];

                flobt[] sinc_tbble =
                        sinc_tbble_f[(int)((ix - iix) * sinc_tbble_fsize)];
                int xx = iix - sinc_tbble_center;
                flobt y = 0;
                for (int i = 0; i < sinc_tbble_size; i++, xx++)
                    y += in[xx] * sinc_tbble[i];
                out[ox++] = y;

                ix += pitch;
                pitch += pitchstep;
            }
        }
        in_offset[0] = ix;
        out_offset[0] = ox;
        stbrtpitch[0] = pitch;

    }
}
