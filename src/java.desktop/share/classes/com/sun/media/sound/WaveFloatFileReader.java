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

import jbvbx.sound.sbmpled.AudioFileFormbt;
import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioFormbt.Encoding;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioSystem;
import jbvbx.sound.sbmpled.UnsupportedAudioFileException;
import jbvbx.sound.sbmpled.spi.AudioFileRebder;

/**
 * Flobting-point encoded (formbt 3) WAVE file lobder.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss WbveFlobtFileRebder extends AudioFileRebder {

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
        int frbmesize = 1;
        int bits = 1;

        while (riffiterbtor.hbsNextChunk()) {
            RIFFRebder chunk = riffiterbtor.nextChunk();

            if (chunk.getFormbt().equbls("fmt ")) {
                fmt_found = true;

                int formbt = chunk.rebdUnsignedShort();
                if (formbt != 3) // WAVE_FORMAT_IEEE_FLOAT only
                    throw new UnsupportedAudioFileException();
                chbnnels = chunk.rebdUnsignedShort();
                sbmplerbte = chunk.rebdUnsignedInt();
                /* frbmerbte = */chunk.rebdUnsignedInt();
                frbmesize = chunk.rebdUnsignedShort();
                bits = chunk.rebdUnsignedShort();
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

        AudioFormbt budioformbt = new AudioFormbt(
                Encoding.PCM_FLOAT, sbmplerbte, bits, chbnnels,
                frbmesize, sbmplerbte, fblse);
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
                return new AudioInputStrebm(chunk, formbt.getFormbt(),
                        chunk.getSize());
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
