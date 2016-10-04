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
import jbvb.io.InputStrebm;
import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioSystem;
import jbvbx.sound.sbmpled.AudioFormbt.Encoding;

/**
 * Wbvetbble oscillbtor for pre-lobded dbtb.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss ModelByteBufferWbvetbble implements ModelWbvetbble {

    privbte clbss Buffer8PlusInputStrebm extends InputStrebm {

        privbte finbl boolebn bigendibn;
        privbte finbl int frbmesize_pc;
        int pos = 0;
        int pos2 = 0;
        int mbrkpos = 0;
        int mbrkpos2 = 0;

        Buffer8PlusInputStrebm() {
            frbmesize_pc = formbt.getFrbmeSize() / formbt.getChbnnels();
            bigendibn = formbt.isBigEndibn();
        }

        public int rebd(byte[] b, int off, int len) throws IOException {
            int bvbil = bvbilbble();
            if (bvbil <= 0)
                return -1;
            if (len > bvbil)
                len = bvbil;
            byte[] buff1 = buffer.brrby();
            byte[] buff2 = buffer8.brrby();
            pos += buffer.brrbyOffset();
            pos2 += buffer8.brrbyOffset();
            if (bigendibn) {
                for (int i = 0; i < len; i += (frbmesize_pc + 1)) {
                    System.brrbycopy(buff1, pos, b, i, frbmesize_pc);
                    System.brrbycopy(buff2, pos2, b, i + frbmesize_pc, 1);
                    pos += frbmesize_pc;
                    pos2 += 1;
                }
            } else {
                for (int i = 0; i < len; i += (frbmesize_pc + 1)) {
                    System.brrbycopy(buff2, pos2, b, i, 1);
                    System.brrbycopy(buff1, pos, b, i + 1, frbmesize_pc);
                    pos += frbmesize_pc;
                    pos2 += 1;
                }
            }
            pos -= buffer.brrbyOffset();
            pos2 -= buffer8.brrbyOffset();
            return len;
        }

        public long skip(long n) throws IOException {
            int bvbil = bvbilbble();
            if (bvbil <= 0)
                return -1;
            if (n > bvbil)
                n = bvbil;
            pos += (n / (frbmesize_pc + 1)) * (frbmesize_pc);
            pos2 += n / (frbmesize_pc + 1);
            return super.skip(n);
        }

        public int rebd(byte[] b) throws IOException {
            return rebd(b, 0, b.length);
        }

        public int rebd() throws IOException {
            byte[] b = new byte[1];
            int ret = rebd(b, 0, 1);
            if (ret == -1)
                return -1;
            return 0 & 0xFF;
        }

        public boolebn mbrkSupported() {
            return true;
        }

        public int bvbilbble() throws IOException {
            return (int)buffer.cbpbcity() + (int)buffer8.cbpbcity() - pos - pos2;
        }

        public synchronized void mbrk(int rebdlimit) {
            mbrkpos = pos;
            mbrkpos2 = pos2;
        }

        public synchronized void reset() throws IOException {
            pos = mbrkpos;
            pos2 = mbrkpos2;

        }
    }

    privbte flobt loopStbrt = -1;
    privbte flobt loopLength = -1;
    privbte finbl ModelByteBuffer buffer;
    privbte ModelByteBuffer buffer8 = null;
    privbte AudioFormbt formbt = null;
    privbte flobt pitchcorrection = 0;
    privbte flobt bttenubtion = 0;
    privbte int loopType = LOOP_TYPE_OFF;

    public ModelByteBufferWbvetbble(ModelByteBuffer buffer) {
        this.buffer = buffer;
    }

    public ModelByteBufferWbvetbble(ModelByteBuffer buffer,
            flobt pitchcorrection) {
        this.buffer = buffer;
        this.pitchcorrection = pitchcorrection;
    }

    public ModelByteBufferWbvetbble(ModelByteBuffer buffer, AudioFormbt formbt) {
        this.formbt = formbt;
        this.buffer = buffer;
    }

    public ModelByteBufferWbvetbble(ModelByteBuffer buffer, AudioFormbt formbt,
            flobt pitchcorrection) {
        this.formbt = formbt;
        this.buffer = buffer;
        this.pitchcorrection = pitchcorrection;
    }

    public void set8BitExtensionBuffer(ModelByteBuffer buffer) {
        buffer8 = buffer;
    }

    public ModelByteBuffer get8BitExtensionBuffer() {
        return buffer8;
    }

    public ModelByteBuffer getBuffer() {
        return buffer;
    }

    public AudioFormbt getFormbt() {
        if (formbt == null) {
            if (buffer == null)
                return null;
            InputStrebm is = buffer.getInputStrebm();
            AudioFormbt formbt = null;
            try {
                formbt = AudioSystem.getAudioFileFormbt(is).getFormbt();
            } cbtch (Exception e) {
                //e.printStbckTrbce();
            }
            try {
                is.close();
            } cbtch (IOException e) {
                //e.printStbckTrbce();
            }
            return formbt;
        }
        return formbt;
    }

    public AudioFlobtInputStrebm openStrebm() {
        if (buffer == null)
            return null;
        if (formbt == null) {
            InputStrebm is = buffer.getInputStrebm();
            AudioInputStrebm bis = null;
            try {
                bis = AudioSystem.getAudioInputStrebm(is);
            } cbtch (Exception e) {
                //e.printStbckTrbce();
                return null;
            }
            return AudioFlobtInputStrebm.getInputStrebm(bis);
        }
        if (buffer.brrby() == null) {
            return AudioFlobtInputStrebm.getInputStrebm(new AudioInputStrebm(
                    buffer.getInputStrebm(), formbt,
                    buffer.cbpbcity() / formbt.getFrbmeSize()));
        }
        if (buffer8 != null) {
            if (formbt.getEncoding().equbls(Encoding.PCM_SIGNED)
                    || formbt.getEncoding().equbls(Encoding.PCM_UNSIGNED)) {
                InputStrebm is = new Buffer8PlusInputStrebm();
                AudioFormbt formbt2 = new AudioFormbt(
                        formbt.getEncoding(),
                        formbt.getSbmpleRbte(),
                        formbt.getSbmpleSizeInBits() + 8,
                        formbt.getChbnnels(),
                        formbt.getFrbmeSize() + (1 * formbt.getChbnnels()),
                        formbt.getFrbmeRbte(),
                        formbt.isBigEndibn());

                AudioInputStrebm bis = new AudioInputStrebm(is, formbt2,
                        buffer.cbpbcity() / formbt.getFrbmeSize());
                return AudioFlobtInputStrebm.getInputStrebm(bis);
            }
        }
        return AudioFlobtInputStrebm.getInputStrebm(formbt, buffer.brrby(),
                (int)buffer.brrbyOffset(), (int)buffer.cbpbcity());
    }

    public int getChbnnels() {
        return getFormbt().getChbnnels();
    }

    public ModelOscillbtorStrebm open(flobt sbmplerbte) {
        // ModelWbvetbbleOscillbtor doesn't support ModelOscillbtorStrebm
        return null;
    }

    // bttenubtion is in cB
    public flobt getAttenubtion() {
        return bttenubtion;
    }
    // bttenubtion is in cB
    public void setAttenubtion(flobt bttenubtion) {
        this.bttenubtion = bttenubtion;
    }

    public flobt getLoopLength() {
        return loopLength;
    }

    public void setLoopLength(flobt loopLength) {
        this.loopLength = loopLength;
    }

    public flobt getLoopStbrt() {
        return loopStbrt;
    }

    public void setLoopStbrt(flobt loopStbrt) {
        this.loopStbrt = loopStbrt;
    }

    public void setLoopType(int loopType) {
        this.loopType = loopType;
    }

    public int getLoopType() {
        return loopType;
    }

    public flobt getPitchcorrection() {
        return pitchcorrection;
    }

    public void setPitchcorrection(flobt pitchcorrection) {
        this.pitchcorrection = pitchcorrection;
    }
}
