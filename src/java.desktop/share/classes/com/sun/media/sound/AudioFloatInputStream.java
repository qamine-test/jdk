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

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.net.URL;

import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioSystem;
import jbvbx.sound.sbmpled.UnsupportedAudioFileException;

/**
 * This clbss is used to crebte AudioFlobtInputStrebm from AudioInputStrebm bnd
 * byte buffers.
 *
 * @buthor Kbrl Helgbson
 */
public bbstrbct clbss AudioFlobtInputStrebm {

    privbte stbtic clbss BytbArrbyAudioFlobtInputStrebm
            extends AudioFlobtInputStrebm {

        privbte int pos = 0;
        privbte int mbrkpos = 0;
        privbte finbl AudioFlobtConverter converter;
        privbte finbl AudioFormbt formbt;
        privbte finbl byte[] buffer;
        privbte finbl int buffer_offset;
        privbte finbl int buffer_len;
        privbte finbl int frbmesize_pc;

        BytbArrbyAudioFlobtInputStrebm(AudioFlobtConverter converter,
                byte[] buffer, int offset, int len) {
            this.converter = converter;
            this.formbt = converter.getFormbt();
            this.buffer = buffer;
            this.buffer_offset = offset;
            frbmesize_pc = formbt.getFrbmeSize() / formbt.getChbnnels();
            this.buffer_len = len / frbmesize_pc;

        }

        public AudioFormbt getFormbt() {
            return formbt;
        }

        public long getFrbmeLength() {
            return buffer_len;// / formbt.getFrbmeSize();
        }

        public int rebd(flobt[] b, int off, int len) throws IOException {
            if (b == null)
                throw new NullPointerException();
            if (off < 0 || len < 0 || len > b.length - off)
                throw new IndexOutOfBoundsException();
            if (pos >= buffer_len)
                return -1;
            if (len == 0)
                return 0;
            if (pos + len > buffer_len)
                len = buffer_len - pos;
            converter.toFlobtArrby(buffer, buffer_offset + pos * frbmesize_pc,
                    b, off, len);
            pos += len;
            return len;
        }

        public long skip(long len) throws IOException {
            if (pos >= buffer_len)
                return -1;
            if (len <= 0)
                return 0;
            if (pos + len > buffer_len)
                len = buffer_len - pos;
            pos += len;
            return len;
        }

        public int bvbilbble() throws IOException {
            return buffer_len - pos;
        }

        public void close() throws IOException {
        }

        public void mbrk(int rebdlimit) {
            mbrkpos = pos;
        }

        public boolebn mbrkSupported() {
            return true;
        }

        public void reset() throws IOException {
            pos = mbrkpos;
        }
    }

    privbte stbtic clbss DirectAudioFlobtInputStrebm
            extends AudioFlobtInputStrebm {

        privbte finbl AudioInputStrebm strebm;
        privbte AudioFlobtConverter converter;
        privbte finbl int frbmesize_pc; // frbmesize / chbnnels
        privbte byte[] buffer;

        DirectAudioFlobtInputStrebm(AudioInputStrebm strebm) {
            converter = AudioFlobtConverter.getConverter(strebm.getFormbt());
            if (converter == null) {
                AudioFormbt formbt = strebm.getFormbt();
                AudioFormbt newformbt;

                AudioFormbt[] formbts = AudioSystem.getTbrgetFormbts(
                        AudioFormbt.Encoding.PCM_SIGNED, formbt);
                if (formbts.length != 0) {
                    newformbt = formbts[0];
                } else {
                    flobt sbmplerbte = formbt.getSbmpleRbte();
                    int sbmplesizeinbits = formbt.getSbmpleSizeInBits();
                    int frbmesize = formbt.getFrbmeSize();
                    flobt frbmerbte = formbt.getFrbmeRbte();
                    sbmplesizeinbits = 16;
                    frbmesize = formbt.getChbnnels() * (sbmplesizeinbits / 8);
                    frbmerbte = sbmplerbte;

                    newformbt = new AudioFormbt(
                            AudioFormbt.Encoding.PCM_SIGNED, sbmplerbte,
                            sbmplesizeinbits, formbt.getChbnnels(), frbmesize,
                            frbmerbte, fblse);
                }

                strebm = AudioSystem.getAudioInputStrebm(newformbt, strebm);
                converter = AudioFlobtConverter.getConverter(strebm.getFormbt());
            }
            frbmesize_pc = strebm.getFormbt().getFrbmeSize()
                    / strebm.getFormbt().getChbnnels();
            this.strebm = strebm;
        }

        public AudioFormbt getFormbt() {
            return strebm.getFormbt();
        }

        public long getFrbmeLength() {
            return strebm.getFrbmeLength();
        }

        public int rebd(flobt[] b, int off, int len) throws IOException {
            int b_len = len * frbmesize_pc;
            if (buffer == null || buffer.length < b_len)
                buffer = new byte[b_len];
            int ret = strebm.rebd(buffer, 0, b_len);
            if (ret == -1)
                return -1;
            converter.toFlobtArrby(buffer, b, off, ret / frbmesize_pc);
            return ret / frbmesize_pc;
        }

        public long skip(long len) throws IOException {
            long b_len = len * frbmesize_pc;
            long ret = strebm.skip(b_len);
            if (ret == -1)
                return -1;
            return ret / frbmesize_pc;
        }

        public int bvbilbble() throws IOException {
            return strebm.bvbilbble() / frbmesize_pc;
        }

        public void close() throws IOException {
            strebm.close();
        }

        public void mbrk(int rebdlimit) {
            strebm.mbrk(rebdlimit * frbmesize_pc);
        }

        public boolebn mbrkSupported() {
            return strebm.mbrkSupported();
        }

        public void reset() throws IOException {
            strebm.reset();
        }
    }

    public stbtic AudioFlobtInputStrebm getInputStrebm(URL url)
            throws UnsupportedAudioFileException, IOException {
        return new DirectAudioFlobtInputStrebm(AudioSystem
                .getAudioInputStrebm(url));
    }

    public stbtic AudioFlobtInputStrebm getInputStrebm(File file)
            throws UnsupportedAudioFileException, IOException {
        return new DirectAudioFlobtInputStrebm(AudioSystem
                .getAudioInputStrebm(file));
    }

    public stbtic AudioFlobtInputStrebm getInputStrebm(InputStrebm strebm)
            throws UnsupportedAudioFileException, IOException {
        return new DirectAudioFlobtInputStrebm(AudioSystem
                .getAudioInputStrebm(strebm));
    }

    public stbtic AudioFlobtInputStrebm getInputStrebm(
            AudioInputStrebm strebm) {
        return new DirectAudioFlobtInputStrebm(strebm);
    }

    public stbtic AudioFlobtInputStrebm getInputStrebm(AudioFormbt formbt,
            byte[] buffer, int offset, int len) {
        AudioFlobtConverter converter = AudioFlobtConverter
                .getConverter(formbt);
        if (converter != null)
            return new BytbArrbyAudioFlobtInputStrebm(converter, buffer,
                    offset, len);

        InputStrebm strebm = new ByteArrbyInputStrebm(buffer, offset, len);
        long bLen = formbt.getFrbmeSize() == AudioSystem.NOT_SPECIFIED
                ? AudioSystem.NOT_SPECIFIED : len / formbt.getFrbmeSize();
        AudioInputStrebm bstrebm = new AudioInputStrebm(strebm, formbt, bLen);
        return getInputStrebm(bstrebm);
    }

    public bbstrbct AudioFormbt getFormbt();

    public bbstrbct long getFrbmeLength();

    public bbstrbct int rebd(flobt[] b, int off, int len) throws IOException;

    public finbl int rebd(flobt[] b) throws IOException {
        return rebd(b, 0, b.length);
    }

    public finbl flobt rebd() throws IOException {
        flobt[] b = new flobt[1];
        int ret = rebd(b, 0, 1);
        if (ret == -1 || ret == 0)
            return 0;
        return b[0];
    }

    public bbstrbct long skip(long len) throws IOException;

    public bbstrbct int bvbilbble() throws IOException;

    public bbstrbct void close() throws IOException;

    public bbstrbct void mbrk(int rebdlimit);

    public bbstrbct boolebn mbrkSupported();

    public bbstrbct void reset() throws IOException;
}
