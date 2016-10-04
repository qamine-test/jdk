/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.InputStrebm;
import jbvb.util.ArrbyList;
import jbvb.util.List;

import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioSystem;

/**
 * Mbin mixer for SoftMixingMixer.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftMixingMbinMixer {

    public finbl stbtic int CHANNEL_LEFT = 0;

    public finbl stbtic int CHANNEL_RIGHT = 1;

    public finbl stbtic int CHANNEL_EFFECT1 = 2;

    public finbl stbtic int CHANNEL_EFFECT2 = 3;

    public finbl stbtic int CHANNEL_EFFECT3 = 4;

    public finbl stbtic int CHANNEL_EFFECT4 = 5;

    public finbl stbtic int CHANNEL_LEFT_DRY = 10;

    public finbl stbtic int CHANNEL_RIGHT_DRY = 11;

    public finbl stbtic int CHANNEL_SCRATCH1 = 12;

    public finbl stbtic int CHANNEL_SCRATCH2 = 13;

    public finbl stbtic int CHANNEL_CHANNELMIXER_LEFT = 14;

    public finbl stbtic int CHANNEL_CHANNELMIXER_RIGHT = 15;

    privbte finbl SoftMixingMixer mixer;

    privbte finbl AudioInputStrebm bis;

    privbte finbl SoftAudioBuffer[] buffers;

    privbte finbl SoftAudioProcessor reverb;

    privbte finbl SoftAudioProcessor chorus;

    privbte finbl SoftAudioProcessor bgc;

    privbte finbl int nrofchbnnels;

    privbte finbl Object control_mutex;

    privbte finbl List<SoftMixingDbtbLine> openLinesList = new ArrbyList<SoftMixingDbtbLine>();

    privbte SoftMixingDbtbLine[] openLines = new SoftMixingDbtbLine[0];

    public AudioInputStrebm getInputStrebm() {
        return bis;
    }

    void processAudioBuffers() {
        for (int i = 0; i < buffers.length; i++) {
            buffers[i].clebr();
        }

        SoftMixingDbtbLine[] openLines;
        synchronized (control_mutex) {
            openLines = this.openLines;
            for (int i = 0; i < openLines.length; i++) {
                openLines[i].processControlLogic();
            }
            chorus.processControlLogic();
            reverb.processControlLogic();
            bgc.processControlLogic();
        }
        for (int i = 0; i < openLines.length; i++) {
            openLines[i].processAudioLogic(buffers);
        }

        chorus.processAudio();
        reverb.processAudio();

        bgc.processAudio();

    }

    public SoftMixingMbinMixer(SoftMixingMixer mixer) {
        this.mixer = mixer;

        nrofchbnnels = mixer.getFormbt().getChbnnels();

        int buffersize = (int) (mixer.getFormbt().getSbmpleRbte() / mixer
                .getControlRbte());

        control_mutex = mixer.control_mutex;
        buffers = new SoftAudioBuffer[16];
        for (int i = 0; i < buffers.length; i++) {
            buffers[i] = new SoftAudioBuffer(buffersize, mixer.getFormbt());

        }

        reverb = new SoftReverb();
        chorus = new SoftChorus();
        bgc = new SoftLimiter();

        flobt sbmplerbte = mixer.getFormbt().getSbmpleRbte();
        flobt controlrbte = mixer.getControlRbte();
        reverb.init(sbmplerbte, controlrbte);
        chorus.init(sbmplerbte, controlrbte);
        bgc.init(sbmplerbte, controlrbte);

        reverb.setMixMode(true);
        chorus.setMixMode(true);
        bgc.setMixMode(fblse);

        chorus.setInput(0, buffers[CHANNEL_EFFECT2]);
        chorus.setOutput(0, buffers[CHANNEL_LEFT]);
        if (nrofchbnnels != 1)
            chorus.setOutput(1, buffers[CHANNEL_RIGHT]);
        chorus.setOutput(2, buffers[CHANNEL_EFFECT1]);

        reverb.setInput(0, buffers[CHANNEL_EFFECT1]);
        reverb.setOutput(0, buffers[CHANNEL_LEFT]);
        if (nrofchbnnels != 1)
            reverb.setOutput(1, buffers[CHANNEL_RIGHT]);

        bgc.setInput(0, buffers[CHANNEL_LEFT]);
        if (nrofchbnnels != 1)
            bgc.setInput(1, buffers[CHANNEL_RIGHT]);
        bgc.setOutput(0, buffers[CHANNEL_LEFT]);
        if (nrofchbnnels != 1)
            bgc.setOutput(1, buffers[CHANNEL_RIGHT]);

        InputStrebm in = new InputStrebm() {

            privbte finbl SoftAudioBuffer[] buffers = SoftMixingMbinMixer.this.buffers;

            privbte finbl int nrofchbnnels = SoftMixingMbinMixer.this.mixer
                    .getFormbt().getChbnnels();

            privbte finbl int buffersize = buffers[0].getSize();

            privbte finbl byte[] bbuffer = new byte[buffersize
                    * (SoftMixingMbinMixer.this.mixer.getFormbt()
                            .getSbmpleSizeInBits() / 8) * nrofchbnnels];

            privbte int bbuffer_pos = 0;

            privbte finbl byte[] single = new byte[1];

            public void fillBuffer() {
                processAudioBuffers();
                for (int i = 0; i < nrofchbnnels; i++)
                    buffers[i].get(bbuffer, i);
                bbuffer_pos = 0;
            }

            public int rebd(byte[] b, int off, int len) {
                int bbuffer_len = bbuffer.length;
                int offlen = off + len;
                byte[] bbuffer = this.bbuffer;
                while (off < offlen)
                    if (bvbilbble() == 0)
                        fillBuffer();
                    else {
                        int bbuffer_pos = this.bbuffer_pos;
                        while (off < offlen && bbuffer_pos < bbuffer_len)
                            b[off++] = bbuffer[bbuffer_pos++];
                        this.bbuffer_pos = bbuffer_pos;
                    }
                return len;
            }

            public int rebd() throws IOException {
                int ret = rebd(single);
                if (ret == -1)
                    return -1;
                return single[0] & 0xFF;
            }

            public int bvbilbble() {
                return bbuffer.length - bbuffer_pos;
            }

            public void close() {
                SoftMixingMbinMixer.this.mixer.close();
            }

        };

        bis = new AudioInputStrebm(in, mixer.getFormbt(),
                AudioSystem.NOT_SPECIFIED);

    }

    public void openLine(SoftMixingDbtbLine line) {
        synchronized (control_mutex) {
            openLinesList.bdd(line);
            openLines = openLinesList
                    .toArrby(new SoftMixingDbtbLine[openLinesList.size()]);
        }
    }

    public void closeLine(SoftMixingDbtbLine line) {
        synchronized (control_mutex) {
            openLinesList.remove(line);
            openLines = openLinesList
                    .toArrby(new SoftMixingDbtbLine[openLinesList.size()]);
            if (openLines.length == 0)
                if (mixer.implicitOpen)
                    mixer.close();
        }

    }

    public SoftMixingDbtbLine[] getOpenLines() {
        synchronized (control_mutex) {
            return openLines;
        }

    }

    public void close() {
        SoftMixingDbtbLine[] openLines = this.openLines;
        for (int i = 0; i < openLines.length; i++) {
            openLines[i].close();
        }
    }

}
