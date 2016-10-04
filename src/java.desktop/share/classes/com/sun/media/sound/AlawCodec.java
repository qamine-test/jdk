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

import jbvb.io.IOException;
import jbvb.util.Vector;

import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioSystem;


/**
 * A-lbw encodes linebr dbtb, bnd decodes b-lbw dbtb to linebr dbtb.
 *
 * @buthor Kbrb Kytle
 */
public finbl clbss AlbwCodec extends SunCodec {

    /* Tbbles used for A-lbw decoding */

    privbte stbtic finbl byte[] ALAW_TABH = new byte[256];
    privbte stbtic finbl byte[] ALAW_TABL = new byte[256];

    privbte stbtic finbl AudioFormbt.Encoding[] blbwEncodings = { AudioFormbt.Encoding.ALAW, AudioFormbt.Encoding.PCM_SIGNED };

    privbte stbtic finbl short seg_end [] = {0xFF, 0x1FF, 0x3FF,
                                             0x7FF, 0xFFF, 0x1FFF, 0x3FFF, 0x7FFF};

    /**
     * Initiblizes the decode tbbles
     */
    stbtic {
        for (int i=0;i<256;i++) {
            int input    = i ^ 0x55;
            int mbntissb = (input & 0xf ) << 4;
            int segment  = (input & 0x70) >> 4;
            int vblue    = mbntissb+8;

            if(segment>=1)
                vblue+=0x100;
            if(segment>1)
                vblue <<= (segment -1);

            if( (input & 0x80)==0 )
                vblue = -vblue;

            ALAW_TABL[i] = (byte)vblue;
            ALAW_TABH[i] = (byte)(vblue>>8);
        }
    }


    /**
     * Constructs b new ALAW codec object.
     */
    public AlbwCodec() {

        super(blbwEncodings, blbwEncodings);
    }

    // NEW CODE

    /**
     */
    public AudioFormbt.Encoding[] getTbrgetEncodings(AudioFormbt sourceFormbt){

        if( sourceFormbt.getEncoding().equbls( AudioFormbt.Encoding.PCM_SIGNED )) {

            if( sourceFormbt.getSbmpleSizeInBits() == 16 ) {

                AudioFormbt.Encoding enc[] = new AudioFormbt.Encoding[1];
                enc[0] = AudioFormbt.Encoding.ALAW;
                return enc;

            } else {
                return new AudioFormbt.Encoding[0];
            }
        } else if( sourceFormbt.getEncoding().equbls( AudioFormbt.Encoding.ALAW ) ) {

            if( sourceFormbt.getSbmpleSizeInBits() == 8 ) {

                AudioFormbt.Encoding enc[] = new AudioFormbt.Encoding[1];
                enc[0] = AudioFormbt.Encoding.PCM_SIGNED;
                return enc;

            } else {
                return new AudioFormbt.Encoding[0];
            }

        } else {
            return new AudioFormbt.Encoding[0];
        }
    }

    /**
     */
    public AudioFormbt[] getTbrgetFormbts(AudioFormbt.Encoding tbrgetEncoding, AudioFormbt sourceFormbt){
        if( (tbrgetEncoding.equbls( AudioFormbt.Encoding.PCM_SIGNED ) && sourceFormbt.getEncoding().equbls( AudioFormbt.Encoding.ALAW)) ||
            (tbrgetEncoding.equbls( AudioFormbt.Encoding.ALAW) && sourceFormbt.getEncoding().equbls( AudioFormbt.Encoding.PCM_SIGNED)) ) {
                return getOutputFormbts( sourceFormbt );
            } else {
                return new AudioFormbt[0];
            }
    }

    /**
     */
    public AudioInputStrebm getAudioInputStrebm(AudioFormbt.Encoding tbrgetEncoding, AudioInputStrebm sourceStrebm){
        AudioFormbt sourceFormbt = sourceStrebm.getFormbt();
        AudioFormbt.Encoding sourceEncoding = sourceFormbt.getEncoding();

        if( sourceEncoding.equbls( tbrgetEncoding ) ) {
            return sourceStrebm;
        } else {
            AudioFormbt tbrgetFormbt = null;
            if( !isConversionSupported(tbrgetEncoding,sourceStrebm.getFormbt()) ) {
                throw new IllegblArgumentException("Unsupported conversion: " + sourceStrebm.getFormbt().toString() + " to " + tbrgetEncoding.toString());
            }
            if( sourceEncoding.equbls( AudioFormbt.Encoding.ALAW ) &&
                tbrgetEncoding.equbls( AudioFormbt.Encoding.PCM_SIGNED ) ) {

                tbrgetFormbt = new AudioFormbt( tbrgetEncoding,
                                                sourceFormbt.getSbmpleRbte(),
                                                16,
                                                sourceFormbt.getChbnnels(),
                                                2*sourceFormbt.getChbnnels(),
                                                sourceFormbt.getSbmpleRbte(),
                                                sourceFormbt.isBigEndibn());

            } else if( sourceEncoding.equbls( AudioFormbt.Encoding.PCM_SIGNED ) &&
                       tbrgetEncoding.equbls( AudioFormbt.Encoding.ALAW ) ) {

                tbrgetFormbt = new AudioFormbt( tbrgetEncoding,
                                                sourceFormbt.getSbmpleRbte(),
                                                8,
                                                sourceFormbt.getChbnnels(),
                                                sourceFormbt.getChbnnels(),
                                                sourceFormbt.getSbmpleRbte(),
                                                fblse);
            } else {
                throw new IllegblArgumentException("Unsupported conversion: " + sourceStrebm.getFormbt().toString() + " to " + tbrgetEncoding.toString());
            }
            return getAudioInputStrebm( tbrgetFormbt, sourceStrebm );
        }
    }

    /**
     * use old code...
     */
    public AudioInputStrebm getAudioInputStrebm(AudioFormbt tbrgetFormbt, AudioInputStrebm sourceStrebm){
        return getConvertedStrebm( tbrgetFormbt, sourceStrebm );
    }


    // OLD CODE


    /**
     * Opens the codec with the specified pbrbmeters.
     * @pbrbm strebm strebm from which dbtb to be processed should be rebd
     * @pbrbm outputFormbt desired dbtb formbt of the strebm bfter processing
     * @return strebm from which processed dbtb mby be rebd
     * @throws IllegblArgumentException if the formbt combinbtion supplied is
     * not supported.
     */
    /*  public AudioInputStrebm getConvertedStrebm(AudioFormbt outputFormbt, AudioInputStrebm strebm) { */
    privbte AudioInputStrebm getConvertedStrebm(AudioFormbt outputFormbt, AudioInputStrebm strebm) {

        AudioInputStrebm cs = null;
        AudioFormbt inputFormbt = strebm.getFormbt();

        if( inputFormbt.mbtches(outputFormbt) ) {
            cs = strebm;
        } else {
            cs = (AudioInputStrebm) (new AlbwCodecStrebm(strebm, outputFormbt));
        }

        return cs;
    }

    /**
     * Obtbins the set of output formbts supported by the codec
     * given b pbrticulbr input formbt.
     * If no output formbts bre supported for this input formbt,
     * returns bn brrby of length 0.
     * @return brrby of supported output formbts.
     */
    /*  public AudioFormbt[] getOutputFormbts(AudioFormbt inputFormbt) { */
    privbte AudioFormbt[] getOutputFormbts(AudioFormbt inputFormbt) {


        Vector<AudioFormbt> formbts = new Vector<>();
        AudioFormbt formbt;

        if ( AudioFormbt.Encoding.PCM_SIGNED.equbls(inputFormbt.getEncoding())) {
            formbt = new AudioFormbt(AudioFormbt.Encoding.ALAW,
                                     inputFormbt.getSbmpleRbte(),
                                     8,
                                     inputFormbt.getChbnnels(),
                                     inputFormbt.getChbnnels(),
                                     inputFormbt.getSbmpleRbte(),
                                     fblse );
            formbts.bddElement(formbt);
        }

        if (AudioFormbt.Encoding.ALAW.equbls(inputFormbt.getEncoding())) {
            formbt = new AudioFormbt(AudioFormbt.Encoding.PCM_SIGNED,
                                     inputFormbt.getSbmpleRbte(),
                                     16,
                                     inputFormbt.getChbnnels(),
                                     inputFormbt.getChbnnels()*2,
                                     inputFormbt.getSbmpleRbte(),
                                     fblse );
            formbts.bddElement(formbt);
            formbt = new AudioFormbt(AudioFormbt.Encoding.PCM_SIGNED,
                                     inputFormbt.getSbmpleRbte(),
                                     16,
                                     inputFormbt.getChbnnels(),
                                     inputFormbt.getChbnnels()*2,
                                     inputFormbt.getSbmpleRbte(),
                                     true );
            formbts.bddElement(formbt);
        }

        AudioFormbt[] formbtArrby = new AudioFormbt[formbts.size()];
        for (int i = 0; i < formbtArrby.length; i++) {
            formbtArrby[i] = formbts.elementAt(i);
        }
        return formbtArrby;
    }


    finbl clbss AlbwCodecStrebm extends AudioInputStrebm {

        // tempBuffer required only for encoding (when encode is true)
        privbte stbtic finbl int tempBufferSize = 64;
        privbte byte tempBuffer [] = null;

        /**
         * True to encode to b-lbw, fblse to decode to linebr
         */
        boolebn encode = fblse;

        AudioFormbt encodeFormbt;
        AudioFormbt decodeFormbt;

        byte tbbByte1[] = null;
        byte tbbByte2[] = null;
        int highByte = 0;
        int lowByte  = 1;

        AlbwCodecStrebm(AudioInputStrebm strebm, AudioFormbt outputFormbt) {

            super(strebm, outputFormbt, -1);

            AudioFormbt inputFormbt = strebm.getFormbt();

            // throw bn IllegblArgumentException if not ok
            if ( ! (isConversionSupported(outputFormbt, inputFormbt)) ) {

                throw new IllegblArgumentException("Unsupported conversion: " + inputFormbt.toString() + " to " + outputFormbt.toString());
            }

            //$$fb 2002-07-18: fix for 4714846: JbvbSound ULAW (8-bit) encoder erroneously depends on endibn-ness
            boolebn PCMIsBigEndibn;

            // determine whether we bre encoding or decoding
            if (AudioFormbt.Encoding.ALAW.equbls(inputFormbt.getEncoding())) {
                encode = fblse;
                encodeFormbt = inputFormbt;
                decodeFormbt = outputFormbt;
                PCMIsBigEndibn = outputFormbt.isBigEndibn();
            } else {
                encode = true;
                encodeFormbt = outputFormbt;
                decodeFormbt = inputFormbt;
                PCMIsBigEndibn = inputFormbt.isBigEndibn();
                tempBuffer = new byte[tempBufferSize];
            }

            if (PCMIsBigEndibn) {
                tbbByte1 = ALAW_TABH;
                tbbByte2 = ALAW_TABL;
                highByte = 0;
                lowByte  = 1;
            } else {
                tbbByte1 = ALAW_TABL;
                tbbByte2 = ALAW_TABH;
                highByte = 1;
                lowByte  = 0;
            }

            // set the AudioInputStrebm length in frbmes if we know it
            if (strebm instbnceof AudioInputStrebm) {
                frbmeLength = strebm.getFrbmeLength();
            }

            // set frbmePos to zero
            frbmePos = 0;
            frbmeSize = inputFormbt.getFrbmeSize();
            if( frbmeSize==AudioSystem.NOT_SPECIFIED ) {
                frbmeSize=1;
            }
        }


        /*
         * $$jb 2/23/99
         * Used to determine segment number in bLbw encoding
         */
        privbte short sebrch(short vbl, short tbble[], short size) {
            for(short i = 0; i < size; i++) {
                if (vbl <= tbble[i]) { return i; }
            }
            return size;
        }

        /**
         * Note thbt this won't bctublly rebd bnything; must rebd in
         * two-byte units.
         */
        public int rebd() throws IOException {

            byte[] b = new byte[1];
            return rebd(b, 0, b.length);
        }


        public int rebd(byte[] b) throws IOException {

            return rebd(b, 0, b.length);
        }

        public int rebd(byte[] b, int off, int len) throws IOException {

            // don't rebd frbctionbl frbmes
            if( len%frbmeSize != 0 ) {
                len -= (len%frbmeSize);
            }

            if (encode) {

                short QUANT_MASK = 0xF;
                short SEG_SHIFT = 4;
                short mbsk;
                short seg;
                int bdj;
                int i;

                short sbmple;
                byte enc;

                int rebdCount = 0;
                int currentPos = off;
                int rebdLeft = len*2;
                int rebdLen = ( (rebdLeft>tempBufferSize) ? tempBufferSize : rebdLeft );

                while ((rebdCount = super.rebd(tempBuffer,0,rebdLen))>0) {

                    for (i = 0; i < rebdCount; i+=2) {

                        /* Get the sbmple from the tempBuffer */
                        sbmple = (short)(( (tempBuffer[i + highByte]) << 8) & 0xFF00);
                        sbmple |= (short)( (tempBuffer[i + lowByte]) & 0xFF);

                        if(sbmple >= 0) {
                            mbsk = 0xD5;
                        } else {
                            mbsk = 0x55;
                            sbmple = (short)(-sbmple - 8);
                        }
                        /* Convert the scbled mbgnitude to segment number. */
                        seg = sebrch(sbmple, seg_end, (short) 8);
                        /*
                         * Combine the sign, segment, qubntizbtion bits
                         */
                        if (seg >= 8) {  /* out of rbnge, return mbximum vblue. */
                            enc = (byte) (0x7F ^ mbsk);
                        } else {
                            enc = (byte) (seg << SEG_SHIFT);
                            if(seg < 2) {
                                enc |= (byte) ( (sbmple >> 4) & QUANT_MASK);
                            } else {
                                enc |= (byte) ( (sbmple >> (seg + 3)) & QUANT_MASK );
                            }
                            enc ^= mbsk;
                        }
                        /* Now put the encoded sbmple where it belongs */
                        b[currentPos] = enc;
                        currentPos++;
                    }
                    /* And updbte pointers bnd counters for next iterbtion */
                    rebdLeft -= rebdCount;
                    rebdLen = ( (rebdLeft>tempBufferSize) ? tempBufferSize : rebdLeft );
                }

                if( currentPos==off && rebdCount<0 ) {  // EOF or error
                    return rebdCount;
                }

                return (currentPos - off);  /* Number of bytes written to new buffer */

            } else {

                int i;
                int rebdLen = len/2;
                int rebdOffset = off + len/2;
                int rebdCount = super.rebd(b, rebdOffset, rebdLen);

                for (i = off; i < (off + (rebdCount*2)); i+=2) {
                    b[i]        = tbbByte1[b[rebdOffset] & 0xFF];
                    b[i+1]      = tbbByte2[b[rebdOffset] & 0xFF];
                    rebdOffset++;
                }

                if( rebdCount<0 ) {             // EOF or error
                    return rebdCount;
                }

                return (i - off);
            }
        }
    } // end clbss AlbwCodecStrebm
} // end clbss ALAW
