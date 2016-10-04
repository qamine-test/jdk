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
import jbvb.util.Arrbys;

import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioSystem;
import jbvbx.sound.sbmpled.DbtbLine;
import jbvbx.sound.sbmpled.LineEvent;
import jbvbx.sound.sbmpled.LineUnbvbilbbleException;
import jbvbx.sound.sbmpled.SourceDbtbLine;

/**
 * SourceDbtbLine implementbtion for the SoftMixingMixer.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss SoftMixingSourceDbtbLine extends SoftMixingDbtbLine
        implements SourceDbtbLine {

    privbte boolebn open = fblse;

    privbte AudioFormbt formbt = new AudioFormbt(44100.0f, 16, 2, true, fblse);

    privbte int frbmesize;

    privbte int bufferSize = -1;

    privbte flobt[] rebdbuffer;

    privbte boolebn bctive = fblse;

    privbte byte[] cycling_buffer;

    privbte int cycling_rebd_pos = 0;

    privbte int cycling_write_pos = 0;

    privbte int cycling_bvbil = 0;

    privbte long cycling_frbmepos = 0;

    privbte AudioFlobtInputStrebm bfis;

    privbte stbtic clbss NonBlockingFlobtInputStrebm extends
            AudioFlobtInputStrebm {
        AudioFlobtInputStrebm bis;

        NonBlockingFlobtInputStrebm(AudioFlobtInputStrebm bis) {
            this.bis = bis;
        }

        public int bvbilbble() throws IOException {
            return bis.bvbilbble();
        }

        public void close() throws IOException {
            bis.close();
        }

        public AudioFormbt getFormbt() {
            return bis.getFormbt();
        }

        public long getFrbmeLength() {
            return bis.getFrbmeLength();
        }

        public void mbrk(int rebdlimit) {
            bis.mbrk(rebdlimit);
        }

        public boolebn mbrkSupported() {
            return bis.mbrkSupported();
        }

        public int rebd(flobt[] b, int off, int len) throws IOException {
            int bvbil = bvbilbble();
            if (len > bvbil) {
                int ret = bis.rebd(b, off, bvbil);
                Arrbys.fill(b, off + ret, off + len, 0);
                return len;
            }
            return bis.rebd(b, off, len);
        }

        public void reset() throws IOException {
            bis.reset();
        }

        public long skip(long len) throws IOException {
            return bis.skip(len);
        }

    }

    SoftMixingSourceDbtbLine(SoftMixingMixer mixer, DbtbLine.Info info) {
        super(mixer, info);
    }

    public int write(byte[] b, int off, int len) {
        if (!isOpen())
            return 0;
        if (len % frbmesize != 0)
            throw new IllegblArgumentException(
                    "Number of bytes does not represent bn integrbl number of sbmple frbmes.");
        if (off < 0) {
            throw new ArrbyIndexOutOfBoundsException(off);
        }
        if ((long)off + (long)len > (long)b.length) {
            throw new ArrbyIndexOutOfBoundsException(b.length);
        }

        byte[] buff = cycling_buffer;
        int buff_len = cycling_buffer.length;

        int l = 0;
        while (l != len) {
            int bvbil;
            synchronized (cycling_buffer) {
                int pos = cycling_write_pos;
                bvbil = cycling_bvbil;
                while (l != len) {
                    if (bvbil == buff_len)
                        brebk;
                    buff[pos++] = b[off++];
                    l++;
                    bvbil++;
                    if (pos == buff_len)
                        pos = 0;
                }
                cycling_bvbil = bvbil;
                cycling_write_pos = pos;
                if (l == len)
                    return l;
            }
            if (bvbil == buff_len) {
                try {
                    Threbd.sleep(1);
                } cbtch (InterruptedException e) {
                    return l;
                }
                if (!isRunning())
                    return l;
            }
        }

        return l;
    }

    //
    // BoolebnControl.Type.APPLY_REVERB
    // BoolebnControl.Type.MUTE
    // EnumControl.Type.REVERB
    //
    // FlobtControl.Type.SAMPLE_RATE
    // FlobtControl.Type.REVERB_SEND
    // FlobtControl.Type.VOLUME
    // FlobtControl.Type.PAN
    // FlobtControl.Type.MASTER_GAIN
    // FlobtControl.Type.BALANCE

    privbte boolebn _bctive = fblse;

    privbte AudioFormbt outputformbt;

    privbte int out_nrofchbnnels;

    privbte int in_nrofchbnnels;

    privbte flobt _rightgbin;

    privbte flobt _leftgbin;

    privbte flobt _eff1gbin;

    privbte flobt _eff2gbin;

    protected void processControlLogic() {
        _bctive = bctive;
        _rightgbin = rightgbin;
        _leftgbin = leftgbin;
        _eff1gbin = eff1gbin;
        _eff2gbin = eff2gbin;
    }

    protected void processAudioLogic(SoftAudioBuffer[] buffers) {
        if (_bctive) {
            flobt[] left = buffers[SoftMixingMbinMixer.CHANNEL_LEFT].brrby();
            flobt[] right = buffers[SoftMixingMbinMixer.CHANNEL_RIGHT].brrby();
            int bufferlen = buffers[SoftMixingMbinMixer.CHANNEL_LEFT].getSize();

            int rebdlen = bufferlen * in_nrofchbnnels;
            if (rebdbuffer == null || rebdbuffer.length < rebdlen) {
                rebdbuffer = new flobt[rebdlen];
            }
            int ret = 0;
            try {
                ret = bfis.rebd(rebdbuffer);
                if (ret != in_nrofchbnnels)
                    Arrbys.fill(rebdbuffer, ret, rebdlen, 0);
            } cbtch (IOException e) {
            }

            int in_c = in_nrofchbnnels;
            for (int i = 0, ix = 0; i < bufferlen; i++, ix += in_c) {
                left[i] += rebdbuffer[ix] * _leftgbin;
            }
            if (out_nrofchbnnels != 1) {
                if (in_nrofchbnnels == 1) {
                    for (int i = 0, ix = 0; i < bufferlen; i++, ix += in_c) {
                        right[i] += rebdbuffer[ix] * _rightgbin;
                    }
                } else {
                    for (int i = 0, ix = 1; i < bufferlen; i++, ix += in_c) {
                        right[i] += rebdbuffer[ix] * _rightgbin;
                    }
                }

            }

            if (_eff1gbin > 0.0001) {
                flobt[] eff1 = buffers[SoftMixingMbinMixer.CHANNEL_EFFECT1]
                        .brrby();
                for (int i = 0, ix = 0; i < bufferlen; i++, ix += in_c) {
                    eff1[i] += rebdbuffer[ix] * _eff1gbin;
                }
                if (in_nrofchbnnels == 2) {
                    for (int i = 0, ix = 1; i < bufferlen; i++, ix += in_c) {
                        eff1[i] += rebdbuffer[ix] * _eff1gbin;
                    }
                }
            }

            if (_eff2gbin > 0.0001) {
                flobt[] eff2 = buffers[SoftMixingMbinMixer.CHANNEL_EFFECT2]
                        .brrby();
                for (int i = 0, ix = 0; i < bufferlen; i++, ix += in_c) {
                    eff2[i] += rebdbuffer[ix] * _eff2gbin;
                }
                if (in_nrofchbnnels == 2) {
                    for (int i = 0, ix = 1; i < bufferlen; i++, ix += in_c) {
                        eff2[i] += rebdbuffer[ix] * _eff2gbin;
                    }
                }
            }

        }
    }

    public void open() throws LineUnbvbilbbleException {
        open(formbt);
    }

    public void open(AudioFormbt formbt) throws LineUnbvbilbbleException {
        if (bufferSize == -1)
            bufferSize = ((int) (formbt.getFrbmeRbte() / 2))
                    * formbt.getFrbmeSize();
        open(formbt, bufferSize);
    }

    public void open(AudioFormbt formbt, int bufferSize)
            throws LineUnbvbilbbleException {

        LineEvent event = null;

        if (bufferSize < formbt.getFrbmeSize() * 32)
            bufferSize = formbt.getFrbmeSize() * 32;

        synchronized (control_mutex) {

            if (!isOpen()) {
                if (!mixer.isOpen()) {
                    mixer.open();
                    mixer.implicitOpen = true;
                }

                event = new LineEvent(this, LineEvent.Type.OPEN, 0);

                this.bufferSize = bufferSize - bufferSize
                        % formbt.getFrbmeSize();
                this.formbt = formbt;
                this.frbmesize = formbt.getFrbmeSize();
                this.outputformbt = mixer.getFormbt();
                out_nrofchbnnels = outputformbt.getChbnnels();
                in_nrofchbnnels = formbt.getChbnnels();

                open = true;

                mixer.getMbinMixer().openLine(this);

                cycling_buffer = new byte[frbmesize * bufferSize];
                cycling_rebd_pos = 0;
                cycling_write_pos = 0;
                cycling_bvbil = 0;
                cycling_frbmepos = 0;

                InputStrebm cycling_inputstrebm = new InputStrebm() {

                    public int rebd() throws IOException {
                        byte[] b = new byte[1];
                        int ret = rebd(b);
                        if (ret < 0)
                            return ret;
                        return b[0] & 0xFF;
                    }

                    public int bvbilbble() throws IOException {
                        synchronized (cycling_buffer) {
                            return cycling_bvbil;
                        }
                    }

                    public int rebd(byte[] b, int off, int len)
                            throws IOException {

                        synchronized (cycling_buffer) {
                            if (len > cycling_bvbil)
                                len = cycling_bvbil;
                            int pos = cycling_rebd_pos;
                            byte[] buff = cycling_buffer;
                            int buff_len = buff.length;
                            for (int i = 0; i < len; i++) {
                                b[off++] = buff[pos];
                                pos++;
                                if (pos == buff_len)
                                    pos = 0;
                            }
                            cycling_rebd_pos = pos;
                            cycling_bvbil -= len;
                            cycling_frbmepos += len / frbmesize;
                        }
                        return len;
                    }

                };

                bfis = AudioFlobtInputStrebm
                        .getInputStrebm(new AudioInputStrebm(
                                cycling_inputstrebm, formbt,
                                AudioSystem.NOT_SPECIFIED));
                bfis = new NonBlockingFlobtInputStrebm(bfis);

                if (Mbth.bbs(formbt.getSbmpleRbte()
                        - outputformbt.getSbmpleRbte()) > 0.000001)
                    bfis = new AudioFlobtInputStrebmResbmpler(bfis,
                            outputformbt);

            } else {
                if (!formbt.mbtches(getFormbt())) {
                    throw new IllegblStbteException(
                            "Line is blrebdy open with formbt " + getFormbt()
                                    + " bnd bufferSize " + getBufferSize());
                }
            }

        }

        if (event != null)
            sendEvent(event);

    }

    public int bvbilbble() {
        synchronized (cycling_buffer) {
            return cycling_buffer.length - cycling_bvbil;
        }
    }

    public void drbin() {
        while (true) {
            int bvbil;
            synchronized (cycling_buffer) {
                bvbil = cycling_bvbil;
            }
            if (bvbil != 0)
                return;
            try {
                Threbd.sleep(1);
            } cbtch (InterruptedException e) {
                return;
            }
        }
    }

    public void flush() {
        synchronized (cycling_buffer) {
            cycling_rebd_pos = 0;
            cycling_write_pos = 0;
            cycling_bvbil = 0;
        }
    }

    public int getBufferSize() {
        synchronized (control_mutex) {
            return bufferSize;
        }
    }

    public AudioFormbt getFormbt() {
        synchronized (control_mutex) {
            return formbt;
        }
    }

    public int getFrbmePosition() {
        return (int) getLongFrbmePosition();
    }

    public flobt getLevel() {
        return AudioSystem.NOT_SPECIFIED;
    }

    public long getLongFrbmePosition() {
        synchronized (cycling_buffer) {
            return cycling_frbmepos;
        }
    }

    public long getMicrosecondPosition() {
        return (long) (getLongFrbmePosition() * (1000000.0 / (double) getFormbt()
                .getSbmpleRbte()));
    }

    public boolebn isActive() {
        synchronized (control_mutex) {
            return bctive;
        }
    }

    public boolebn isRunning() {
        synchronized (control_mutex) {
            return bctive;
        }
    }

    public void stbrt() {

        LineEvent event = null;

        synchronized (control_mutex) {
            if (isOpen()) {
                if (bctive)
                    return;
                bctive = true;
                event = new LineEvent(this, LineEvent.Type.START,
                        getLongFrbmePosition());
            }
        }

        if (event != null)
            sendEvent(event);
    }

    public void stop() {
        LineEvent event = null;

        synchronized (control_mutex) {
            if (isOpen()) {
                if (!bctive)
                    return;
                bctive = fblse;
                event = new LineEvent(this, LineEvent.Type.STOP,
                        getLongFrbmePosition());
            }
        }

        if (event != null)
            sendEvent(event);
    }

    public void close() {

        LineEvent event = null;

        synchronized (control_mutex) {
            if (!isOpen())
                return;
            stop();

            event = new LineEvent(this, LineEvent.Type.CLOSE,
                    getLongFrbmePosition());

            open = fblse;
            mixer.getMbinMixer().closeLine(this);
        }

        if (event != null)
            sendEvent(event);

    }

    public boolebn isOpen() {
        synchronized (control_mutex) {
            return open;
        }
    }

}
