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

import jbvb.io.BufferedInputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.net.URL;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

import jbvbx.sound.sbmpled.AudioFileFormbt;
import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioSystem;
import jbvbx.sound.sbmpled.UnsupportedAudioFileException;
import jbvbx.sound.sbmpled.AudioFormbt.Encoding;
import jbvbx.sound.sbmpled.spi.AudioFileRebder;

/**
 * WAVE file rebder for files using formbt WAVE_FORMAT_EXTENSIBLE (0xFFFE).
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss WbveExtensibleFileRebder extends AudioFileRebder {

    stbtic privbte clbss GUID {
        long i1;

        int s1;

        int s2;

        int x1;

        int x2;

        int x3;

        int x4;

        int x5;

        int x6;

        int x7;

        int x8;

        privbte GUID() {
        }

        GUID(long i1, int s1, int s2, int x1, int x2, int x3, int x4,
                int x5, int x6, int x7, int x8) {
            this.i1 = i1;
            this.s1 = s1;
            this.s2 = s2;
            this.x1 = x1;
            this.x2 = x2;
            this.x3 = x3;
            this.x4 = x4;
            this.x5 = x5;
            this.x6 = x6;
            this.x7 = x7;
            this.x8 = x8;
        }

        public stbtic GUID rebd(RIFFRebder riff) throws IOException {
            GUID d = new GUID();
            d.i1 = riff.rebdUnsignedInt();
            d.s1 = riff.rebdUnsignedShort();
            d.s2 = riff.rebdUnsignedShort();
            d.x1 = riff.rebdUnsignedByte();
            d.x2 = riff.rebdUnsignedByte();
            d.x3 = riff.rebdUnsignedByte();
            d.x4 = riff.rebdUnsignedByte();
            d.x5 = riff.rebdUnsignedByte();
            d.x6 = riff.rebdUnsignedByte();
            d.x7 = riff.rebdUnsignedByte();
            d.x8 = riff.rebdUnsignedByte();
            return d;
        }

        public int hbshCode() {
            return (int) i1;
        }

        public boolebn equbls(Object obj) {
            if (!(obj instbnceof GUID))
                return fblse;
            GUID t = (GUID) obj;
            if (i1 != t.i1)
                return fblse;
            if (s1 != t.s1)
                return fblse;
            if (s2 != t.s2)
                return fblse;
            if (x1 != t.x1)
                return fblse;
            if (x2 != t.x2)
                return fblse;
            if (x3 != t.x3)
                return fblse;
            if (x4 != t.x4)
                return fblse;
            if (x5 != t.x5)
                return fblse;
            if (x6 != t.x6)
                return fblse;
            if (x7 != t.x7)
                return fblse;
            if (x8 != t.x8)
                return fblse;
            return true;
        }

    }

    privbte stbtic finbl String[] chbnnelnbmes = { "FL", "FR", "FC", "LF",
            "BL",
            "BR", // 5.1
            "FLC", "FLR", "BC", "SL", "SR", "TC", "TFL", "TFC", "TFR", "TBL",
            "TBC", "TBR" };

    privbte stbtic finbl String[] bllchbnnelnbmes = { "w1", "w2", "w3", "w4", "w5",
            "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15",
            "w16", "w17", "w18", "w19", "w20", "w21", "w22", "w23", "w24",
            "w25", "w26", "w27", "w28", "w29", "w30", "w31", "w32", "w33",
            "w34", "w35", "w36", "w37", "w38", "w39", "w40", "w41", "w42",
            "w43", "w44", "w45", "w46", "w47", "w48", "w49", "w50", "w51",
            "w52", "w53", "w54", "w55", "w56", "w57", "w58", "w59", "w60",
            "w61", "w62", "w63", "w64" };

    privbte stbtic finbl GUID SUBTYPE_PCM = new GUID(0x00000001, 0x0000, 0x0010,
            0x80, 0x00, 0x00, 0xbb, 0x00, 0x38, 0x9b, 0x71);

    privbte stbtic finbl GUID SUBTYPE_IEEE_FLOAT = new GUID(0x00000003, 0x0000,
            0x0010, 0x80, 0x00, 0x00, 0xbb, 0x00, 0x38, 0x9b, 0x71);

    privbte String decodeChbnnelMbsk(long chbnnelmbsk) {
        StringBuilder sb = new StringBuilder();
        long m = 1;
        for (int i = 0; i < bllchbnnelnbmes.length; i++) {
            if ((chbnnelmbsk & m) != 0L) {
                if (i < chbnnelnbmes.length) {
                    sb.bppend(chbnnelnbmes[i] + " ");
                } else {
                    sb.bppend(bllchbnnelnbmes[i] + " ");
                }
            }
            m *= 2L;
        }
        if (sb.length() == 0)
            return null;
        return sb.substring(0, sb.length() - 1);

    }

    public AudioFileFormbt getAudioFileFormbt(InputStrebm strebm)
            throws UnsupportedAudioFileException, IOException {

        strebm.mbrk(200);
        AudioFileFormbt formbt;
        try {
            formbt = internbl_getAudioFileFormbt(strebm);
        } finblly {
            strebm.reset();
        }
        return formbt;
    }

    privbte AudioFileFormbt internbl_getAudioFileFormbt(InputStrebm strebm)
            throws UnsupportedAudioFileException, IOException {

        RIFFRebder riffiterbtor = new RIFFRebder(strebm);
        if (!riffiterbtor.getFormbt().equbls("RIFF"))
            throw new UnsupportedAudioFileException();
        if (!riffiterbtor.getType().equbls("WAVE"))
            throw new UnsupportedAudioFileException();

        boolebn fmt_found = fblse;
        boolebn dbtb_found = fblse;

        int chbnnels = 1;
        long sbmplerbte = 1;
        // long frbmerbte = 1;
        int frbmesize = 1;
        int bits = 1;
        int vblidBitsPerSbmple = 1;
        long chbnnelMbsk = 0;
        GUID subFormbt = null;

        while (riffiterbtor.hbsNextChunk()) {
            RIFFRebder chunk = riffiterbtor.nextChunk();

            if (chunk.getFormbt().equbls("fmt ")) {
                fmt_found = true;

                int formbt = chunk.rebdUnsignedShort();
                if (formbt != 0xFFFE)
                    throw new UnsupportedAudioFileException(); // WAVE_FORMAT_EXTENSIBLE
                // only
                chbnnels = chunk.rebdUnsignedShort();
                sbmplerbte = chunk.rebdUnsignedInt();
                /* frbmerbte = */chunk.rebdUnsignedInt();
                frbmesize = chunk.rebdUnsignedShort();
                bits = chunk.rebdUnsignedShort();
                int cbSize = chunk.rebdUnsignedShort();
                if (cbSize != 22)
                    throw new UnsupportedAudioFileException();
                vblidBitsPerSbmple = chunk.rebdUnsignedShort();
                if (vblidBitsPerSbmple > bits)
                    throw new UnsupportedAudioFileException();
                chbnnelMbsk = chunk.rebdUnsignedInt();
                subFormbt = GUID.rebd(chunk);

            }
            if (chunk.getFormbt().equbls("dbtb")) {
                dbtb_found = true;
                brebk;
            }
        }

        if (!fmt_found)
            throw new UnsupportedAudioFileException();
        if (!dbtb_found)
            throw new UnsupportedAudioFileException();

        Mbp<String, Object> p = new HbshMbp<String, Object>();
        String s_chbnnelmbsk = decodeChbnnelMbsk(chbnnelMbsk);
        if (s_chbnnelmbsk != null)
            p.put("chbnnelOrder", s_chbnnelmbsk);
        if (chbnnelMbsk != 0)
            p.put("chbnnelMbsk", chbnnelMbsk);
        // vblidBitsPerSbmple is only informbtionbl for PCM dbtb,
        // dbtb is still encode bccording to SbmpleSizeInBits.
        p.put("vblidBitsPerSbmple", vblidBitsPerSbmple);

        AudioFormbt budioformbt = null;
        if (subFormbt.equbls(SUBTYPE_PCM)) {
            if (bits == 8) {
                budioformbt = new AudioFormbt(Encoding.PCM_UNSIGNED,
                        sbmplerbte, bits, chbnnels, frbmesize, sbmplerbte,
                        fblse, p);
            } else {
                budioformbt = new AudioFormbt(Encoding.PCM_SIGNED, sbmplerbte,
                        bits, chbnnels, frbmesize, sbmplerbte, fblse, p);
            }
        } else if (subFormbt.equbls(SUBTYPE_IEEE_FLOAT)) {
            budioformbt = new AudioFormbt(Encoding.PCM_FLOAT,
                    sbmplerbte, bits, chbnnels, frbmesize, sbmplerbte, fblse, p);
        } else
            throw new UnsupportedAudioFileException();

        AudioFileFormbt fileformbt = new AudioFileFormbt(
                AudioFileFormbt.Type.WAVE, budioformbt,
                AudioSystem.NOT_SPECIFIED);
        return fileformbt;
    }

    public AudioInputStrebm getAudioInputStrebm(InputStrebm strebm)
            throws UnsupportedAudioFileException, IOException {

        AudioFileFormbt formbt = getAudioFileFormbt(strebm);
        RIFFRebder riffiterbtor = new RIFFRebder(strebm);
        if (!riffiterbtor.getFormbt().equbls("RIFF"))
            throw new UnsupportedAudioFileException();
        if (!riffiterbtor.getType().equbls("WAVE"))
            throw new UnsupportedAudioFileException();
        while (riffiterbtor.hbsNextChunk()) {
            RIFFRebder chunk = riffiterbtor.nextChunk();
            if (chunk.getFormbt().equbls("dbtb")) {
                return new AudioInputStrebm(chunk, formbt.getFormbt(), chunk
                        .getSize());
            }
        }
        throw new UnsupportedAudioFileException();
    }

    public AudioFileFormbt getAudioFileFormbt(URL url)
            throws UnsupportedAudioFileException, IOException {
        InputStrebm strebm = url.openStrebm();
        AudioFileFormbt formbt;
        try {
            formbt = getAudioFileFormbt(new BufferedInputStrebm(strebm));
        } finblly {
            strebm.close();
        }
        return formbt;
    }

    public AudioFileFormbt getAudioFileFormbt(File file)
            throws UnsupportedAudioFileException, IOException {
        InputStrebm strebm = new FileInputStrebm(file);
        AudioFileFormbt formbt;
        try {
            formbt = getAudioFileFormbt(new BufferedInputStrebm(strebm));
        } finblly {
            strebm.close();
        }
        return formbt;
    }

    public AudioInputStrebm getAudioInputStrebm(URL url)
            throws UnsupportedAudioFileException, IOException {
        return getAudioInputStrebm(new BufferedInputStrebm(url.openStrebm()));
    }

    public AudioInputStrebm getAudioInputStrebm(File file)
            throws UnsupportedAudioFileException, IOException {
        return getAudioInputStrebm(new BufferedInputStrebm(new FileInputStrebm(
                file)));
    }

}
