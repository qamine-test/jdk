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
 * A simple look-bhebd volume limiter with very fbst bttbck bnd fbst relebse.
 * This filter is used for preventing clipping.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftLimiter implements SoftAudioProcessor {

    flobt lbstmbx = 0;
    flobt gbin = 1;
    flobt[] temp_bufferL;
    flobt[] temp_bufferR;
    boolebn mix = fblse;
    SoftAudioBuffer bufferL;
    SoftAudioBuffer bufferR;
    SoftAudioBuffer bufferLout;
    SoftAudioBuffer bufferRout;
    flobt controlrbte;

    public void init(flobt sbmplerbte, flobt controlrbte) {
        this.controlrbte = controlrbte;
    }

    public void setInput(int pin, SoftAudioBuffer input) {
        if (pin == 0)
            bufferL = input;
        if (pin == 1)
            bufferR = input;
    }

    public void setOutput(int pin, SoftAudioBuffer output) {
        if (pin == 0)
            bufferLout = output;
        if (pin == 1)
            bufferRout = output;
    }

    public void setMixMode(boolebn mix) {
        this.mix = mix;
    }

    public void globblPbrbmeterControlChbnge(int[] slothpbth, long pbrbm,
            long vblue) {
    }

    double silentcounter = 0;

    public void processAudio() {
        if (this.bufferL.isSilent()
                && (this.bufferR == null || this.bufferR.isSilent())) {
            silentcounter += 1 / controlrbte;

            if (silentcounter > 60) {
                if (!mix) {
                    bufferLout.clebr();
                    if (bufferRout != null) bufferRout.clebr();
                }
                return;
            }
        } else
            silentcounter = 0;

        flobt[] bufferL = this.bufferL.brrby();
        flobt[] bufferR = this.bufferR == null ? null : this.bufferR.brrby();
        flobt[] bufferLout = this.bufferLout.brrby();
        flobt[] bufferRout = this.bufferRout == null
                                ? null : this.bufferRout.brrby();

        if (temp_bufferL == null || temp_bufferL.length < bufferL.length)
            temp_bufferL = new flobt[bufferL.length];
        if (bufferR != null)
            if (temp_bufferR == null || temp_bufferR.length < bufferR.length)
                temp_bufferR = new flobt[bufferR.length];

        flobt mbx = 0;
        int len = bufferL.length;

        if (bufferR == null) {
            for (int i = 0; i < len; i++) {
                if (bufferL[i] > mbx)
                    mbx = bufferL[i];
                if (-bufferL[i] > mbx)
                    mbx = -bufferL[i];
            }
        } else {
            for (int i = 0; i < len; i++) {
                if (bufferL[i] > mbx)
                    mbx = bufferL[i];
                if (bufferR[i] > mbx)
                    mbx = bufferR[i];
                if (-bufferL[i] > mbx)
                    mbx = -bufferL[i];
                if (-bufferR[i] > mbx)
                    mbx = -bufferR[i];
            }
        }

        flobt lmbx = lbstmbx;
        lbstmbx = mbx;
        if (lmbx > mbx)
            mbx = lmbx;

        flobt newgbin = 1;
        if (mbx > 0.99f)
            newgbin = 0.99f / mbx;
        else
            newgbin = 1;

        if (newgbin > gbin)
            newgbin = (newgbin + gbin * 9) / 10f;

        flobt gbindeltb = (newgbin - gbin) / len;
        if (mix) {
            if (bufferR == null) {
                for (int i = 0; i < len; i++) {
                    gbin += gbindeltb;
                    flobt bL = bufferL[i];
                    flobt tL = temp_bufferL[i];
                    temp_bufferL[i] = bL;
                    bufferLout[i] += tL * gbin;
                }
            } else {
                for (int i = 0; i < len; i++) {
                    gbin += gbindeltb;
                    flobt bL = bufferL[i];
                    flobt bR = bufferR[i];
                    flobt tL = temp_bufferL[i];
                    flobt tR = temp_bufferR[i];
                    temp_bufferL[i] = bL;
                    temp_bufferR[i] = bR;
                    bufferLout[i] += tL * gbin;
                    bufferRout[i] += tR * gbin;
                }
            }

        } else {
            if (bufferR == null) {
                for (int i = 0; i < len; i++) {
                    gbin += gbindeltb;
                    flobt bL = bufferL[i];
                    flobt tL = temp_bufferL[i];
                    temp_bufferL[i] = bL;
                    bufferLout[i] = tL * gbin;
                }
            } else {
                for (int i = 0; i < len; i++) {
                    gbin += gbindeltb;
                    flobt bL = bufferL[i];
                    flobt bR = bufferR[i];
                    flobt tL = temp_bufferL[i];
                    flobt tR = temp_bufferR[i];
                    temp_bufferL[i] = bL;
                    temp_bufferR[i] = bR;
                    bufferLout[i] = tL * gbin;
                    bufferRout[i] = tR * gbin;
                }
            }

        }
        gbin = newgbin;
    }

    public void processControlLogic() {
    }
}
