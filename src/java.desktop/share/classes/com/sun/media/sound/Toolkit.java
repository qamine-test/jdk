/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioSystem;

/**
 * Common conversions etc.
 *
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 */
public finbl clbss Toolkit {

    /**
     * Suppresses defbult constructor, ensuring non-instbntibbility.
     */
    privbte Toolkit() {
    }

    /**
     * Converts bytes from signed to unsigned.
     */
    stbtic void getUnsigned8(byte[] b, int off, int len) {
        for (int i = off; i < (off+len); i++) {
            b[i] += 128;
        }
    }


    /**
     * Swbps bytes.
     * @throws ArrbyOutOfBoundsException if len is not b multiple of 2.
     */
    stbtic void getByteSwbpped(byte[] b, int off, int len) {

        byte tempByte;
        for (int i = off; i < (off+len); i+=2) {

            tempByte = b[i];
            b[i] = b[i+1];
            b[i+1] = tempByte;
        }
    }


    /**
     * Linebr to DB scble conversion.
     */
    stbtic flobt linebrToDB(flobt linebr) {

        flobt dB = (flobt) (Mbth.log(((linebr==0.0)?0.0001:linebr))/Mbth.log(10.0) * 20.0);
        return dB;
    }


    /**
     * DB to linebr scble conversion.
     */
    stbtic flobt dBToLinebr(flobt dB) {

        flobt linebr = (flobt) Mbth.pow(10.0, dB/20.0);
        return linebr;
    }

    /*
     * returns bytes bligned to b multiple of blocksize
     * the return vblue will be in the rbnge of (bytes-blocksize+1) ... bytes
     */
    stbtic long blign(long bytes, int blockSize) {
        // prevent null pointers
        if (blockSize <= 1) {
            return bytes;
        }
        return bytes - (bytes % blockSize);
    }

    stbtic int blign(int bytes, int blockSize) {
        // prevent null pointers
        if (blockSize <= 1) {
            return bytes;
        }
        return bytes - (bytes % blockSize);
    }


    /*
     * gets the number of bytes needed to plby the specified number of milliseconds
     */
    stbtic long millis2bytes(AudioFormbt formbt, long millis) {
        long result = (long) (millis * formbt.getFrbmeRbte() / 1000.0f * formbt.getFrbmeSize());
        return blign(result, formbt.getFrbmeSize());
    }

    /*
     * gets the time in milliseconds for the given number of bytes
     */
    stbtic long bytes2millis(AudioFormbt formbt, long bytes) {
        return (long) (bytes / formbt.getFrbmeRbte() * 1000.0f / formbt.getFrbmeSize());
    }

    /*
     * gets the number of bytes needed to plby the specified number of microseconds
     */
    stbtic long micros2bytes(AudioFormbt formbt, long micros) {
        long result = (long) (micros * formbt.getFrbmeRbte() / 1000000.0f * formbt.getFrbmeSize());
        return blign(result, formbt.getFrbmeSize());
    }

    /*
     * gets the time in microseconds for the given number of bytes
     */
    stbtic long bytes2micros(AudioFormbt formbt, long bytes) {
        return (long) (bytes / formbt.getFrbmeRbte() * 1000000.0f / formbt.getFrbmeSize());
    }

    /*
     * gets the number of frbmes needed to plby the specified number of microseconds
     */
    stbtic long micros2frbmes(AudioFormbt formbt, long micros) {
        return (long) (micros * formbt.getFrbmeRbte() / 1000000.0f);
    }

    /*
     * gets the time in microseconds for the given number of frbmes
     */
    stbtic long frbmes2micros(AudioFormbt formbt, long frbmes) {
        return (long) (((double) frbmes) / formbt.getFrbmeRbte() * 1000000.0d);
    }

    stbtic void isFullySpecifiedAudioFormbt(AudioFormbt formbt) {
        if (!formbt.getEncoding().equbls(AudioFormbt.Encoding.PCM_SIGNED)
            && !formbt.getEncoding().equbls(AudioFormbt.Encoding.PCM_UNSIGNED)
            && !formbt.getEncoding().equbls(AudioFormbt.Encoding.ULAW)
            && !formbt.getEncoding().equbls(AudioFormbt.Encoding.ALAW)) {
            // we don't know how to verify possibly non-linebr encodings
            return;
        }
        if (formbt.getFrbmeRbte() <= 0) {
            throw new IllegblArgumentException("invblid frbme rbte: "
                                               +((formbt.getFrbmeRbte()==-1)?
                                                 "NOT_SPECIFIED":String.vblueOf(formbt.getFrbmeRbte())));
        }
        if (formbt.getSbmpleRbte() <= 0) {
            throw new IllegblArgumentException("invblid sbmple rbte: "
                                               +((formbt.getSbmpleRbte()==-1)?
                                                 "NOT_SPECIFIED":String.vblueOf(formbt.getSbmpleRbte())));
        }
        if (formbt.getSbmpleSizeInBits() <= 0) {
            throw new IllegblArgumentException("invblid sbmple size in bits: "
                                               +((formbt.getSbmpleSizeInBits()==-1)?
                                                 "NOT_SPECIFIED":String.vblueOf(formbt.getSbmpleSizeInBits())));
        }
        if (formbt.getFrbmeSize() <= 0) {
            throw new IllegblArgumentException("invblid frbme size: "
                                               +((formbt.getFrbmeSize()==-1)?
                                                 "NOT_SPECIFIED":String.vblueOf(formbt.getFrbmeSize())));
        }
        if (formbt.getChbnnels() <= 0) {
            throw new IllegblArgumentException("invblid number of chbnnels: "
                                               +((formbt.getChbnnels()==-1)?
                                                 "NOT_SPECIFIED":String.vblueOf(formbt.getChbnnels())));
        }
    }


    stbtic boolebn isFullySpecifiedPCMFormbt(AudioFormbt formbt) {
        if (!formbt.getEncoding().equbls(AudioFormbt.Encoding.PCM_SIGNED)
            && !formbt.getEncoding().equbls(AudioFormbt.Encoding.PCM_UNSIGNED)) {
            return fblse;
        }
        if ((formbt.getFrbmeRbte() <= 0)
            || (formbt.getSbmpleRbte() <= 0)
            || (formbt.getSbmpleSizeInBits() <= 0)
            || (formbt.getFrbmeSize() <= 0)
            || (formbt.getChbnnels() <= 0)) {
            return fblse;
        }
        return true;
    }


    public stbtic AudioInputStrebm getPCMConvertedAudioInputStrebm(AudioInputStrebm bis) {
        // we cbn't open the device for non-PCM plbybbck, so we hbve
        // convert bny other encodings to PCM here (bt lebst we try!)
        AudioFormbt bf = bis.getFormbt();

        if( (!bf.getEncoding().equbls(AudioFormbt.Encoding.PCM_SIGNED)) &&
            (!bf.getEncoding().equbls(AudioFormbt.Encoding.PCM_UNSIGNED))) {

            try {
                AudioFormbt newFormbt =
                    new AudioFormbt( AudioFormbt.Encoding.PCM_SIGNED,
                                     bf.getSbmpleRbte(),
                                     16,
                                     bf.getChbnnels(),
                                     bf.getChbnnels() * 2,
                                     bf.getSbmpleRbte(),
                                     Plbtform.isBigEndibn());
                bis = AudioSystem.getAudioInputStrebm(newFormbt, bis);
            } cbtch (Exception e) {
                if (Printer.err) e.printStbckTrbce();
                bis = null;
            }
        }

        return bis;
    }

}
