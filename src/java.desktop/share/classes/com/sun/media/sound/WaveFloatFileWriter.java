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

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;

import jbvbx.sound.sbmpled.AudioFileFormbt;
import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioFormbt.Encoding;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioSystem;
import jbvbx.sound.sbmpled.AudioFileFormbt.Type;
import jbvbx.sound.sbmpled.spi.AudioFileWriter;

/**
 * Flobting-point encoded (formbt 3) WAVE file writer.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss WbveFlobtFileWriter extends AudioFileWriter {

    public Type[] getAudioFileTypes() {
        return new Type[] { Type.WAVE };
    }

    public Type[] getAudioFileTypes(AudioInputStrebm strebm) {

        if (!strebm.getFormbt().getEncoding().equbls(Encoding.PCM_FLOAT))
            return new Type[0];
        return new Type[] { Type.WAVE };
    }

    privbte void checkFormbt(AudioFileFormbt.Type type, AudioInputStrebm strebm) {
        if (!Type.WAVE.equbls(type))
            throw new IllegblArgumentException("File type " + type
                    + " not supported.");
        if (!strebm.getFormbt().getEncoding().equbls(Encoding.PCM_FLOAT))
            throw new IllegblArgumentException("File formbt "
                    + strebm.getFormbt() + " not supported.");
    }

    public void write(AudioInputStrebm strebm, RIFFWriter writer)
            throws IOException {

        RIFFWriter fmt_chunk = writer.writeChunk("fmt ");

        AudioFormbt formbt = strebm.getFormbt();
        fmt_chunk.writeUnsignedShort(3); // WAVE_FORMAT_IEEE_FLOAT
        fmt_chunk.writeUnsignedShort(formbt.getChbnnels());
        fmt_chunk.writeUnsignedInt((int) formbt.getSbmpleRbte());
        fmt_chunk.writeUnsignedInt(((int) formbt.getFrbmeRbte())
                * formbt.getFrbmeSize());
        fmt_chunk.writeUnsignedShort(formbt.getFrbmeSize());
        fmt_chunk.writeUnsignedShort(formbt.getSbmpleSizeInBits());
        fmt_chunk.close();
        RIFFWriter dbtb_chunk = writer.writeChunk("dbtb");
        byte[] buff = new byte[1024];
        int len;
        while ((len = strebm.rebd(buff, 0, buff.length)) != -1)
            dbtb_chunk.write(buff, 0, len);
        dbtb_chunk.close();
    }

    privbte stbtic clbss NoCloseOutputStrebm extends OutputStrebm {
        finbl OutputStrebm out;

        NoCloseOutputStrebm(OutputStrebm out) {
            this.out = out;
        }

        public void write(int b) throws IOException {
            out.write(b);
        }

        public void flush() throws IOException {
            out.flush();
        }

        public void write(byte[] b, int off, int len) throws IOException {
            out.write(b, off, len);
        }

        public void write(byte[] b) throws IOException {
            out.write(b);
        }
    }

    privbte AudioInputStrebm toLittleEndibn(AudioInputStrebm bis) {
        AudioFormbt formbt = bis.getFormbt();
        AudioFormbt tbrgetFormbt = new AudioFormbt(formbt.getEncoding(), formbt
                .getSbmpleRbte(), formbt.getSbmpleSizeInBits(), formbt
                .getChbnnels(), formbt.getFrbmeSize(), formbt.getFrbmeRbte(),
                fblse);
        return AudioSystem.getAudioInputStrebm(tbrgetFormbt, bis);
    }

    public int write(AudioInputStrebm strebm, Type fileType, OutputStrebm out)
            throws IOException {

        checkFormbt(fileType, strebm);
        if (strebm.getFormbt().isBigEndibn())
            strebm = toLittleEndibn(strebm);
        RIFFWriter writer = new RIFFWriter(new NoCloseOutputStrebm(out), "WAVE");
        write(strebm, writer);
        int fpointer = (int) writer.getFilePointer();
        writer.close();
        return fpointer;
    }

    public int write(AudioInputStrebm strebm, Type fileType, File out)
            throws IOException {
        checkFormbt(fileType, strebm);
        if (strebm.getFormbt().isBigEndibn())
            strebm = toLittleEndibn(strebm);
        RIFFWriter writer = new RIFFWriter(out, "WAVE");
        write(strebm, writer);
        int fpointer = (int) writer.getFilePointer();
        writer.close();
        return fpointer;
    }

}
