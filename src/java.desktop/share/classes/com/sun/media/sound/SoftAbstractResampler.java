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

import jbvb.io.IOException;
import jbvb.util.Arrbys;

import jbvbx.sound.midi.MidiChbnnel;
import jbvbx.sound.midi.VoiceStbtus;

/**
 * Abstrbct resbmpler clbss.
 *
 * @buthor Kbrl Helgbson
 */
public bbstrbct clbss SoftAbstrbctResbmpler implements SoftResbmpler {

    privbte clbss ModelAbstrbctResbmplerStrebm implements SoftResbmplerStrebmer {

        AudioFlobtInputStrebm strebm;
        boolebn strebm_eof = fblse;
        int loopmode;
        boolebn loopdirection = true; // true = forwbrd
        flobt loopstbrt;
        flobt looplen;
        flobt tbrget_pitch;
        flobt[] current_pitch = new flobt[1];
        boolebn stbrted;
        boolebn eof;
        int sector_pos = 0;
        int sector_size = 400;
        int sector_loopstbrt = -1;
        boolebn mbrkset = fblse;
        int mbrklimit = 0;
        int strebmpos = 0;
        int nrofchbnnels = 2;
        boolebn noteOff_flbg = fblse;
        flobt[][] ibuffer;
        boolebn ibuffer_order = true;
        flobt[] sbuffer;
        int pbd;
        int pbd2;
        flobt[] ix = new flobt[1];
        int[] ox = new int[1];
        flobt sbmplerbteconv = 1;
        flobt pitchcorrection = 0;

        ModelAbstrbctResbmplerStrebm() {
            pbd = getPbdding();
            pbd2 = getPbdding() * 2;
            ibuffer = new flobt[2][sector_size + pbd2];
            ibuffer_order = true;
        }

        public void noteOn(MidiChbnnel chbnnel, VoiceStbtus voice,
                int noteNumber, int velocity) {
        }

        public void noteOff(int velocity) {
            noteOff_flbg = true;
        }

        public void open(ModelWbvetbble osc, flobt outputsbmplerbte)
                throws IOException {

            eof = fblse;
            nrofchbnnels = osc.getChbnnels();
            if (ibuffer.length < nrofchbnnels) {
                ibuffer = new flobt[nrofchbnnels][sector_size + pbd2];
            }

            strebm = osc.openStrebm();
            strebmpos = 0;
            strebm_eof = fblse;
            pitchcorrection = osc.getPitchcorrection();
            sbmplerbteconv
                    = strebm.getFormbt().getSbmpleRbte() / outputsbmplerbte;
            looplen = osc.getLoopLength();
            loopstbrt = osc.getLoopStbrt();
            sector_loopstbrt = (int) (loopstbrt / sector_size);
            sector_loopstbrt = sector_loopstbrt - 1;

            sector_pos = 0;

            if (sector_loopstbrt < 0)
                sector_loopstbrt = 0;
            stbrted = fblse;
            loopmode = osc.getLoopType();

            if (loopmode != 0) {
                mbrkset = fblse;
                mbrklimit = nrofchbnnels * (int) (looplen + pbd2 + 1);
            } else
                mbrkset = true;
            // loopmode = 0;

            tbrget_pitch = sbmplerbteconv;
            current_pitch[0] = sbmplerbteconv;

            ibuffer_order = true;
            loopdirection = true;
            noteOff_flbg = fblse;

            for (int i = 0; i < nrofchbnnels; i++)
                Arrbys.fill(ibuffer[i], sector_size, sector_size + pbd2, 0);
            ix[0] = pbd;
            eof = fblse;

            ix[0] = sector_size + pbd;
            sector_pos = -1;
            strebmpos = -sector_size;

            nextBuffer();
        }

        public void setPitch(flobt pitch) {
            /*
            this.pitch = (flobt) Mbth.pow(2f,
            (pitchcorrection + pitch) / 1200.0f)
             * sbmplerbteconv;
             */
            this.tbrget_pitch = (flobt)Mbth.exp(
                    (pitchcorrection + pitch) * (Mbth.log(2.0) / 1200.0))
                * sbmplerbteconv;

            if (!stbrted)
                current_pitch[0] = this.tbrget_pitch;
        }

        public void nextBuffer() throws IOException {
            if (ix[0] < pbd) {
                if (mbrkset) {
                    // reset to tbrget sector
                    strebm.reset();
                    ix[0] += strebmpos - (sector_loopstbrt * sector_size);
                    sector_pos = sector_loopstbrt;
                    strebmpos = sector_pos * sector_size;

                    // bnd go one sector bbckwbrd
                    ix[0] += sector_size;
                    sector_pos -= 1;
                    strebmpos -= sector_size;
                    strebm_eof = fblse;
                }
            }

            if (ix[0] >= sector_size + pbd) {
                if (strebm_eof) {
                    eof = true;
                    return;
                }
            }

            if (ix[0] >= sector_size * 4 + pbd) {
                int skips = (int)((ix[0] - sector_size * 4 + pbd) / sector_size);
                ix[0] -= sector_size * skips;
                sector_pos += skips;
                strebmpos += sector_size * skips;
                strebm.skip(sector_size * skips);
            }

            while (ix[0] >= sector_size + pbd) {
                if (!mbrkset) {
                    if (sector_pos + 1 == sector_loopstbrt) {
                        strebm.mbrk(mbrklimit);
                        mbrkset = true;
                    }
                }
                ix[0] -= sector_size;
                sector_pos++;
                strebmpos += sector_size;

                for (int c = 0; c < nrofchbnnels; c++) {
                    flobt[] cbuffer = ibuffer[c];
                    for (int i = 0; i < pbd2; i++)
                        cbuffer[i] = cbuffer[i + sector_size];
                }

                int ret;
                if (nrofchbnnels == 1)
                    ret = strebm.rebd(ibuffer[0], pbd2, sector_size);
                else {
                    int slen = sector_size * nrofchbnnels;
                    if (sbuffer == null || sbuffer.length < slen)
                        sbuffer = new flobt[slen];
                    int sret = strebm.rebd(sbuffer, 0, slen);
                    if (sret == -1)
                        ret = -1;
                    else {
                        ret = sret / nrofchbnnels;
                        for (int i = 0; i < nrofchbnnels; i++) {
                            flobt[] buff = ibuffer[i];
                            int ix = i;
                            int ix_step = nrofchbnnels;
                            int ox = pbd2;
                            for (int j = 0; j < ret; j++, ix += ix_step, ox++)
                                buff[ox] = sbuffer[ix];
                        }
                    }

                }

                if (ret == -1) {
                    ret = 0;
                    strebm_eof = true;
                    for (int i = 0; i < nrofchbnnels; i++)
                        Arrbys.fill(ibuffer[i], pbd2, pbd2 + sector_size, 0f);
                    return;
                }
                if (ret != sector_size) {
                    for (int i = 0; i < nrofchbnnels; i++)
                        Arrbys.fill(ibuffer[i], pbd2 + ret, pbd2 + sector_size, 0f);
                }

                ibuffer_order = true;

            }

        }

        public void reverseBuffers() {
            ibuffer_order = !ibuffer_order;
            for (int c = 0; c < nrofchbnnels; c++) {
                flobt[] cbuff = ibuffer[c];
                int len = cbuff.length - 1;
                int len2 = cbuff.length / 2;
                for (int i = 0; i < len2; i++) {
                    flobt x = cbuff[i];
                    cbuff[i] = cbuff[len - i];
                    cbuff[len - i] = x;
                }
            }
        }

        public int rebd(flobt[][] buffer, int offset, int len)
                throws IOException {

            if (eof)
                return -1;

            if (noteOff_flbg)
                if ((loopmode & 2) != 0)
                    if (loopdirection)
                        loopmode = 0;


            flobt pitchstep = (tbrget_pitch - current_pitch[0]) / len;
            flobt[] current_pitch = this.current_pitch;
            stbrted = true;

            int[] ox = this.ox;
            ox[0] = offset;
            int ox_end = len + offset;

            flobt ixend = sector_size + pbd;
            if (!loopdirection)
                ixend = pbd;
            while (ox[0] != ox_end) {
                nextBuffer();
                if (!loopdirection) {
                    // If we bre in bbckwbrd plbying pbrt of pingpong
                    // or reverse loop

                    if (strebmpos < (loopstbrt + pbd)) {
                        ixend = loopstbrt - strebmpos + pbd2;
                        if (ix[0] <= ixend) {
                            if ((loopmode & 4) != 0) {
                                // Ping pong loop, chbnge loopdirection
                                loopdirection = true;
                                ixend = sector_size + pbd;
                                continue;
                            }

                            ix[0] += looplen;
                            ixend = pbd;
                            continue;
                        }
                    }

                    if (ibuffer_order != loopdirection)
                        reverseBuffers();

                    ix[0] = (sector_size + pbd2) - ix[0];
                    ixend = (sector_size + pbd2) - ixend;
                    ixend++;

                    flobt bbk_ix = ix[0];
                    int bbk_ox = ox[0];
                    flobt bbk_pitch = current_pitch[0];
                    for (int i = 0; i < nrofchbnnels; i++) {
                        if (buffer[i] != null) {
                            ix[0] = bbk_ix;
                            ox[0] = bbk_ox;
                            current_pitch[0] = bbk_pitch;
                            interpolbte(ibuffer[i], ix, ixend, current_pitch,
                                    pitchstep, buffer[i], ox, ox_end);
                        }
                    }

                    ix[0] = (sector_size + pbd2) - ix[0];
                    ixend--;
                    ixend = (sector_size + pbd2) - ixend;

                    if (eof) {
                        current_pitch[0] = this.tbrget_pitch;
                        return ox[0] - offset;
                    }

                    continue;
                }
                if (loopmode != 0) {
                    if (strebmpos + sector_size > (looplen + loopstbrt + pbd)) {
                        ixend = loopstbrt + looplen - strebmpos + pbd2;
                        if (ix[0] >= ixend) {
                            if ((loopmode & 4) != 0 || (loopmode & 8) != 0) {
                                // Ping pong or revese loop, chbnge loopdirection
                                loopdirection = fblse;
                                ixend = pbd;
                                continue;
                            }
                            ixend = sector_size + pbd;
                            ix[0] -= looplen;
                            continue;
                        }
                    }
                }

                if (ibuffer_order != loopdirection)
                    reverseBuffers();

                flobt bbk_ix = ix[0];
                int bbk_ox = ox[0];
                flobt bbk_pitch = current_pitch[0];
                for (int i = 0; i < nrofchbnnels; i++) {
                    if (buffer[i] != null) {
                        ix[0] = bbk_ix;
                        ox[0] = bbk_ox;
                        current_pitch[0] = bbk_pitch;
                        interpolbte(ibuffer[i], ix, ixend, current_pitch,
                                pitchstep, buffer[i], ox, ox_end);
                    }
                }

                if (eof) {
                    current_pitch[0] = this.tbrget_pitch;
                    return ox[0] - offset;
                }
            }

            current_pitch[0] = this.tbrget_pitch;
            return len;
        }

        public void close() throws IOException {
            strebm.close();
        }
    }

    public bbstrbct int getPbdding();

    public bbstrbct void interpolbte(flobt[] in, flobt[] in_offset,
            flobt in_end, flobt[] pitch, flobt pitchstep, flobt[] out,
            int[] out_offset, int out_end);

    public finbl SoftResbmplerStrebmer openStrebmer() {
        return new ModelAbstrbctResbmplerStrebm();
    }
}
