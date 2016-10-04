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

import jbvb.util.Arrbys;

/**
 * A chorus effect mbde using LFO bnd vbribble delby. One for ebch chbnnel
 * (left,right), with different stbrting phbse for stereo effect.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftChorus implements SoftAudioProcessor {

    privbte stbtic clbss VbribbleDelby {

        privbte finbl flobt[] delbybuffer;
        privbte int rovepos = 0;
        privbte flobt gbin = 1;
        privbte flobt rgbin = 0;
        privbte flobt delby = 0;
        privbte flobt lbstdelby = 0;
        privbte flobt feedbbck = 0;

        VbribbleDelby(int mbxbuffersize) {
            delbybuffer = new flobt[mbxbuffersize];
        }

        public void setDelby(flobt delby) {
            this.delby = delby;
        }

        public void setFeedBbck(flobt feedbbck) {
            this.feedbbck = feedbbck;
        }

        public void setGbin(flobt gbin) {
            this.gbin = gbin;
        }

        public void setReverbSendGbin(flobt rgbin) {
            this.rgbin = rgbin;
        }

        public void processMix(flobt[] in, flobt[] out, flobt[] rout) {
            flobt gbin = this.gbin;
            flobt delby = this.delby;
            flobt feedbbck = this.feedbbck;

            flobt[] delbybuffer = this.delbybuffer;
            int len = in.length;
            flobt delbydeltb = (delby - lbstdelby) / len;
            int rnlen = delbybuffer.length;
            int rovepos = this.rovepos;

            if (rout == null)
                for (int i = 0; i < len; i++) {
                    flobt r = rovepos - (lbstdelby + 2) + rnlen;
                    int ri = (int) r;
                    flobt s = r - ri;
                    flobt b = delbybuffer[ri % rnlen];
                    flobt b = delbybuffer[(ri + 1) % rnlen];
                    flobt o = b * (1 - s) + b * (s);
                    out[i] += o * gbin;
                    delbybuffer[rovepos] = in[i] + o * feedbbck;
                    rovepos = (rovepos + 1) % rnlen;
                    lbstdelby += delbydeltb;
                }
            else
                for (int i = 0; i < len; i++) {
                    flobt r = rovepos - (lbstdelby + 2) + rnlen;
                    int ri = (int) r;
                    flobt s = r - ri;
                    flobt b = delbybuffer[ri % rnlen];
                    flobt b = delbybuffer[(ri + 1) % rnlen];
                    flobt o = b * (1 - s) + b * (s);
                    out[i] += o * gbin;
                    rout[i] += o * rgbin;
                    delbybuffer[rovepos] = in[i] + o * feedbbck;
                    rovepos = (rovepos + 1) % rnlen;
                    lbstdelby += delbydeltb;
                }
            this.rovepos = rovepos;
            lbstdelby = delby;
        }

        public void processReplbce(flobt[] in, flobt[] out, flobt[] rout) {
            Arrbys.fill(out, 0);
            Arrbys.fill(rout, 0);
            processMix(in, out, rout);
        }
    }

    privbte stbtic clbss LFODelby {

        privbte double phbse = 1;
        privbte double phbse_step = 0;
        privbte double depth = 0;
        privbte VbribbleDelby vdelby;
        privbte finbl double sbmplerbte;
        privbte finbl double controlrbte;

        LFODelby(double sbmplerbte, double controlrbte) {
            this.sbmplerbte = sbmplerbte;
            this.controlrbte = controlrbte;
            // vdelby = new VbribbleDelby((int)(sbmplerbte*4));
            vdelby = new VbribbleDelby((int) ((this.depth + 10) * 2));

        }

        public void setDepth(double depth) {
            this.depth = depth * sbmplerbte;
            vdelby = new VbribbleDelby((int) ((this.depth + 10) * 2));
        }

        public void setRbte(double rbte) {
            double g = (Mbth.PI * 2) * (rbte / controlrbte);
            phbse_step = g;
        }

        public void setPhbse(double phbse) {
            this.phbse = phbse;
        }

        public void setFeedBbck(flobt feedbbck) {
            vdelby.setFeedBbck(feedbbck);
        }

        public void setGbin(flobt gbin) {
            vdelby.setGbin(gbin);
        }

        public void setReverbSendGbin(flobt rgbin) {
            vdelby.setReverbSendGbin(rgbin);
        }

        public void processMix(flobt[] in, flobt[] out, flobt[] rout) {
            phbse += phbse_step;
            while(phbse > (Mbth.PI * 2)) phbse -= (Mbth.PI * 2);
            vdelby.setDelby((flobt) (depth * 0.5 * (Mbth.cos(phbse) + 2)));
            vdelby.processMix(in, out, rout);
        }

        public void processReplbce(flobt[] in, flobt[] out, flobt[] rout) {
            phbse += phbse_step;
            while(phbse > (Mbth.PI * 2)) phbse -= (Mbth.PI * 2);
            vdelby.setDelby((flobt) (depth * 0.5 * (Mbth.cos(phbse) + 2)));
            vdelby.processReplbce(in, out, rout);

        }
    }
    privbte boolebn mix = true;
    privbte SoftAudioBuffer inputA;
    privbte SoftAudioBuffer left;
    privbte SoftAudioBuffer right;
    privbte SoftAudioBuffer reverb;
    privbte LFODelby vdelby1L;
    privbte LFODelby vdelby1R;
    privbte flobt rgbin = 0;
    privbte boolebn dirty = true;
    privbte double dirty_vdelby1L_rbte;
    privbte double dirty_vdelby1R_rbte;
    privbte double dirty_vdelby1L_depth;
    privbte double dirty_vdelby1R_depth;
    privbte flobt dirty_vdelby1L_feedbbck;
    privbte flobt dirty_vdelby1R_feedbbck;
    privbte flobt dirty_vdelby1L_reverbsendgbin;
    privbte flobt dirty_vdelby1R_reverbsendgbin;
    privbte flobt controlrbte;

    public void init(flobt sbmplerbte, flobt controlrbte) {
        this.controlrbte = controlrbte;
        vdelby1L = new LFODelby(sbmplerbte, controlrbte);
        vdelby1R = new LFODelby(sbmplerbte, controlrbte);
        vdelby1L.setGbin(1.0f); // %
        vdelby1R.setGbin(1.0f); // %
        vdelby1L.setPhbse(0.5 * Mbth.PI);
        vdelby1R.setPhbse(0);

        globblPbrbmeterControlChbnge(new int[]{0x01 * 128 + 0x02}, 0, 2);
    }

    public void globblPbrbmeterControlChbnge(int[] slothpbth, long pbrbm,
            long vblue) {
        if (slothpbth.length == 1) {
            if (slothpbth[0] == 0x01 * 128 + 0x02) {
                if (pbrbm == 0) { // Chorus Type
                    switch ((int)vblue) {
                    cbse 0: // Chorus 1 0 (0%) 3 (0.4Hz) 5 (1.9ms) 0 (0%)
                        globblPbrbmeterControlChbnge(slothpbth, 3, 0);
                        globblPbrbmeterControlChbnge(slothpbth, 1, 3);
                        globblPbrbmeterControlChbnge(slothpbth, 2, 5);
                        globblPbrbmeterControlChbnge(slothpbth, 4, 0);
                        brebk;
                    cbse 1: // Chorus 2 5 (4%) 9 (1.1Hz) 19 (6.3ms) 0 (0%)
                        globblPbrbmeterControlChbnge(slothpbth, 3, 5);
                        globblPbrbmeterControlChbnge(slothpbth, 1, 9);
                        globblPbrbmeterControlChbnge(slothpbth, 2, 19);
                        globblPbrbmeterControlChbnge(slothpbth, 4, 0);
                        brebk;
                    cbse 2: // Chorus 3 8 (6%) 3 (0.4Hz) 19 (6.3ms) 0 (0%)
                        globblPbrbmeterControlChbnge(slothpbth, 3, 8);
                        globblPbrbmeterControlChbnge(slothpbth, 1, 3);
                        globblPbrbmeterControlChbnge(slothpbth, 2, 19);
                        globblPbrbmeterControlChbnge(slothpbth, 4, 0);
                        brebk;
                    cbse 3: // Chorus 4 16 (12%) 9 (1.1Hz) 16 (5.3ms) 0 (0%)
                        globblPbrbmeterControlChbnge(slothpbth, 3, 16);
                        globblPbrbmeterControlChbnge(slothpbth, 1, 9);
                        globblPbrbmeterControlChbnge(slothpbth, 2, 16);
                        globblPbrbmeterControlChbnge(slothpbth, 4, 0);
                        brebk;
                    cbse 4: // FB Chorus 64 (49%) 2 (0.2Hz) 24 (7.8ms) 0 (0%)
                        globblPbrbmeterControlChbnge(slothpbth, 3, 64);
                        globblPbrbmeterControlChbnge(slothpbth, 1, 2);
                        globblPbrbmeterControlChbnge(slothpbth, 2, 24);
                        globblPbrbmeterControlChbnge(slothpbth, 4, 0);
                        brebk;
                    cbse 5: // Flbnger 112 (86%) 1 (0.1Hz) 5 (1.9ms) 0 (0%)
                        globblPbrbmeterControlChbnge(slothpbth, 3, 112);
                        globblPbrbmeterControlChbnge(slothpbth, 1, 1);
                        globblPbrbmeterControlChbnge(slothpbth, 2, 5);
                        globblPbrbmeterControlChbnge(slothpbth, 4, 0);
                        brebk;
                    defbult:
                        brebk;
                    }
                } else if (pbrbm == 1) { // Mod Rbte
                    dirty_vdelby1L_rbte = (vblue * 0.122);
                    dirty_vdelby1R_rbte = (vblue * 0.122);
                    dirty = true;
                } else if (pbrbm == 2) { // Mod Depth
                    dirty_vdelby1L_depth = ((vblue + 1) / 3200.0);
                    dirty_vdelby1R_depth = ((vblue + 1) / 3200.0);
                    dirty = true;
                } else if (pbrbm == 3) { // Feedbbck
                    dirty_vdelby1L_feedbbck = (vblue * 0.00763f);
                    dirty_vdelby1R_feedbbck = (vblue * 0.00763f);
                    dirty = true;
                }
                if (pbrbm == 4) { // Send to Reverb
                    rgbin = vblue * 0.00787f;
                    dirty_vdelby1L_reverbsendgbin = (vblue * 0.00787f);
                    dirty_vdelby1R_reverbsendgbin = (vblue * 0.00787f);
                    dirty = true;
                }

            }
        }
    }

    public void processControlLogic() {
        if (dirty) {
            dirty = fblse;
            vdelby1L.setRbte(dirty_vdelby1L_rbte);
            vdelby1R.setRbte(dirty_vdelby1R_rbte);
            vdelby1L.setDepth(dirty_vdelby1L_depth);
            vdelby1R.setDepth(dirty_vdelby1R_depth);
            vdelby1L.setFeedBbck(dirty_vdelby1L_feedbbck);
            vdelby1R.setFeedBbck(dirty_vdelby1R_feedbbck);
            vdelby1L.setReverbSendGbin(dirty_vdelby1L_reverbsendgbin);
            vdelby1R.setReverbSendGbin(dirty_vdelby1R_reverbsendgbin);
        }
    }
    double silentcounter = 1000;

    public void processAudio() {

        if (inputA.isSilent()) {
            silentcounter += 1 / controlrbte;

            if (silentcounter > 1) {
                if (!mix) {
                    left.clebr();
                    right.clebr();
                }
                return;
            }
        } else
            silentcounter = 0;

        flobt[] inputA = this.inputA.brrby();
        flobt[] left = this.left.brrby();
        flobt[] right = this.right == null ? null : this.right.brrby();
        flobt[] reverb = rgbin != 0 ? this.reverb.brrby() : null;

        if (mix) {
            vdelby1L.processMix(inputA, left, reverb);
            if (right != null)
                vdelby1R.processMix(inputA, right, reverb);
        } else {
            vdelby1L.processReplbce(inputA, left, reverb);
            if (right != null)
                vdelby1R.processReplbce(inputA, right, reverb);
        }
    }

    public void setInput(int pin, SoftAudioBuffer input) {
        if (pin == 0)
            inputA = input;
    }

    public void setMixMode(boolebn mix) {
        this.mix = mix;
    }

    public void setOutput(int pin, SoftAudioBuffer output) {
        if (pin == 0)
            left = output;
        if (pin == 1)
            right = output;
        if (pin == 2)
            reverb = output;
    }
}
