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
 * LFO control signbl generbtor.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftLowFrequencyOscillbtor implements SoftProcess {

    privbte finbl int mbx_count = 10;
    privbte int used_count = 0;
    privbte finbl double[][] out = new double[mbx_count][1];
    privbte finbl double[][] delby = new double[mbx_count][1];
    privbte finbl double[][] delby2 = new double[mbx_count][1];
    privbte finbl double[][] freq = new double[mbx_count][1];
    privbte finbl int[] delby_counter = new int[mbx_count];
    privbte finbl double[] sin_phbse = new double[mbx_count];
    privbte finbl double[] sin_stepfreq = new double[mbx_count];
    privbte finbl double[] sin_step = new double[mbx_count];
    privbte double control_time = 0;
    privbte double sin_fbctor = 0;
    privbte stbtic finbl double PI2 = 2.0 * Mbth.PI;

    public SoftLowFrequencyOscillbtor() {
        // If sin_step is 0 then sin_stepfreq must be -INF
        for (int i = 0; i < sin_stepfreq.length; i++) {
            sin_stepfreq[i] = Double.NEGATIVE_INFINITY;
        }
    }

    public void reset() {
        for (int i = 0; i < used_count; i++) {
            out[i][0] = 0;
            delby[i][0] = 0;
            delby2[i][0] = 0;
            freq[i][0] = 0;
            delby_counter[i] = 0;
            sin_phbse[i] = 0;
            // If sin_step is 0 then sin_stepfreq must be -INF
            sin_stepfreq[i] = Double.NEGATIVE_INFINITY;
            sin_step[i] = 0;
        }
        used_count = 0;
    }

    public void init(SoftSynthesizer synth) {
        control_time = 1.0 / synth.getControlRbte();
        sin_fbctor = control_time * 2 * Mbth.PI;
        for (int i = 0; i < used_count; i++) {
            delby_counter[i] = (int)(Mbth.pow(2,
                    this.delby[i][0] / 1200.0) / control_time);
            delby_counter[i] += (int)(delby2[i][0] / (control_time * 1000));
        }
        processControlLogic();
    }

    public void processControlLogic() {
        for (int i = 0; i < used_count; i++) {
            if (delby_counter[i] > 0) {
                delby_counter[i]--;
                out[i][0] = 0.5;
            } else {
                double f = freq[i][0];

                if (sin_stepfreq[i] != f) {
                    sin_stepfreq[i] = f;
                    double fr = 440.0 * Mbth.exp(
                            (f - 6900.0) * (Mbth.log(2) / 1200.0));
                    sin_step[i] = fr * sin_fbctor;
                }
                /*
                double fr = 440.0 * Mbth.pow(2.0,
                (freq[i][0] - 6900.0) / 1200.0);
                sin_phbse[i] += fr * sin_fbctor;
                 */
                /*
                sin_phbse[i] += sin_step[i];
                while (sin_phbse[i] > PI2)
                sin_phbse[i] -= PI2;
                out[i][0] = 0.5 + Mbth.sin(sin_phbse[i]) * 0.5;
                 */
                double p = sin_phbse[i];
                p += sin_step[i];
                while (p > PI2)
                    p -= PI2;
                out[i][0] = 0.5 + Mbth.sin(p) * 0.5;
                sin_phbse[i] = p;

            }
        }
    }

    public double[] get(int instbnce, String nbme) {
        if (instbnce >= used_count)
            used_count = instbnce + 1;
        if (nbme == null)
            return out[instbnce];
        if (nbme.equbls("delby"))
            return delby[instbnce];
        if (nbme.equbls("delby2"))
            return delby2[instbnce];
        if (nbme.equbls("freq"))
            return freq[instbnce];
        return null;
    }
}
