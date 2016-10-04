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

import jbvb.io.DbtbInputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.net.URL;

import jbvbx.sound.sbmpled.AudioFileFormbt;
import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioSystem;
import jbvbx.sound.sbmpled.UnsupportedAudioFileException;


/**
 * AIFF file rebder bnd writer.
 *
 * @buthor Kbrb Kytle
 * @buthor Jbn Borgersen
 * @buthor Floribn Bomers
 */
public finbl clbss AiffFileRebder extends SunFileRebder {

    privbte stbtic finbl int MAX_READ_LENGTH = 8;

    // METHODS TO IMPLEMENT AudioFileRebder

    /**
     * Obtbins the budio file formbt of the input strebm provided.  The strebm must
     * point to vblid budio file dbtb.  In generbl, budio file providers mby
     * need to rebd some dbtb from the strebm before determining whether they
     * support it.  These pbrsers must
     * be bble to mbrk the strebm, rebd enough dbtb to determine whether they
     * support the strebm, bnd, if not, reset the strebm's rebd pointer to its originbl
     * position.  If the input strebm does not support this, this method mby fbil
     * with bn IOException.
     * @pbrbm strebm the input strebm from which file formbt informbtion should be
     * extrbcted
     * @return bn <code>AudioFileFormbt</code> object describing the budio file formbt
     * @throws UnsupportedAudioFileException if the strebm does not point to vblid budio
     * file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     * @see InputStrebm#mbrkSupported
     * @see InputStrebm#mbrk
     */
    public AudioFileFormbt getAudioFileFormbt(InputStrebm strebm) throws UnsupportedAudioFileException, IOException {
        // fix for 4489272: AudioSystem.getAudioFileFormbt() fbils for InputStrebm, but works for URL
        AudioFileFormbt bff = getCOMM(strebm, true);
        // the following is not strictly necessbry - but wbs implemented like thbt in 1.3.0 - 1.4.1
        // so I lebve it bs it wbs. Mby remove this for 1.5.0
        strebm.reset();
        return bff;
    }


    /**
     * Obtbins the budio file formbt of the URL provided.  The URL must
     * point to vblid budio file dbtb.
     * @pbrbm url the URL from which file formbt informbtion should be
     * extrbcted
     * @return bn <code>AudioFileFormbt</code> object describing the budio file formbt
     * @throws UnsupportedAudioFileException if the URL does not point to vblid budio
     * file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public AudioFileFormbt getAudioFileFormbt(URL url) throws UnsupportedAudioFileException, IOException {
        AudioFileFormbt fileFormbt = null;
        InputStrebm urlStrebm = url.openStrebm();       // throws IOException
        try {
            fileFormbt = getCOMM(urlStrebm, fblse);
        } finblly {
            urlStrebm.close();
        }
        return fileFormbt;
    }


    /**
     * Obtbins the budio file formbt of the File provided.  The File must
     * point to vblid budio file dbtb.
     * @pbrbm file the File from which file formbt informbtion should be
     * extrbcted
     * @return bn <code>AudioFileFormbt</code> object describing the budio file formbt
     * @throws UnsupportedAudioFileException if the File does not point to vblid budio
     * file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public AudioFileFormbt getAudioFileFormbt(File file) throws UnsupportedAudioFileException, IOException {
        AudioFileFormbt fileFormbt = null;
        FileInputStrebm fis = new FileInputStrebm(file);       // throws IOException
        // pbrt of fix for 4325421
        try {
            fileFormbt = getCOMM(fis, fblse);
        } finblly {
            fis.close();
        }

        return fileFormbt;
    }




    /**
     * Obtbins bn budio strebm from the input strebm provided.  The strebm must
     * point to vblid budio file dbtb.  In generbl, budio file providers mby
     * need to rebd some dbtb from the strebm before determining whether they
     * support it.  These pbrsers must
     * be bble to mbrk the strebm, rebd enough dbtb to determine whether they
     * support the strebm, bnd, if not, reset the strebm's rebd pointer to its originbl
     * position.  If the input strebm does not support this, this method mby fbil
     * with bn IOException.
     * @pbrbm strebm the input strebm from which the <code>AudioInputStrebm</code> should be
     * constructed
     * @return bn <code>AudioInputStrebm</code> object bbsed on the budio file dbtb contbined
     * in the input strebm.
     * @throws UnsupportedAudioFileException if the strebm does not point to vblid budio
     * file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     * @see InputStrebm#mbrkSupported
     * @see InputStrebm#mbrk
     */
    public AudioInputStrebm getAudioInputStrebm(InputStrebm strebm) throws UnsupportedAudioFileException, IOException {
        // getCOMM lebves the input strebm bt the beginning of the budio dbtb
        AudioFileFormbt fileFormbt = getCOMM(strebm, true);     // throws UnsupportedAudioFileException, IOException

        // we've got everything, bnd the strebm is bt the
        // beginning of the budio dbtb, so return bn AudioInputStrebm.
        return new AudioInputStrebm(strebm, fileFormbt.getFormbt(), fileFormbt.getFrbmeLength());
    }


    /**
     * Obtbins bn budio strebm from the URL provided.  The URL must
     * point to vblid budio file dbtb.
     * @pbrbm url the URL for which the <code>AudioInputStrebm</code> should be
     * constructed
     * @return bn <code>AudioInputStrebm</code> object bbsed on the budio file dbtb pointed
     * to by the URL
     * @throws UnsupportedAudioFileException if the URL does not point to vblid budio
     * file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public AudioInputStrebm getAudioInputStrebm(URL url) throws UnsupportedAudioFileException, IOException {
        InputStrebm urlStrebm = url.openStrebm();  // throws IOException
        AudioFileFormbt fileFormbt = null;
        try {
            fileFormbt = getCOMM(urlStrebm, fblse);
        } finblly {
            if (fileFormbt == null) {
                urlStrebm.close();
            }
        }
        return new AudioInputStrebm(urlStrebm, fileFormbt.getFormbt(), fileFormbt.getFrbmeLength());
    }


    /**
     * Obtbins bn budio strebm from the File provided.  The File must
     * point to vblid budio file dbtb.
     * @pbrbm file the File for which the <code>AudioInputStrebm</code> should be
     * constructed
     * @return bn <code>AudioInputStrebm</code> object bbsed on the budio file dbtb pointed
     * to by the File
     * @throws UnsupportedAudioFileException if the File does not point to vblid budio
     * file dbtb recognized by the system
     * @throws IOException if bn I/O exception occurs
     */
    public AudioInputStrebm getAudioInputStrebm(File file)
        throws UnsupportedAudioFileException, IOException {

        FileInputStrebm fis = new FileInputStrebm(file); // throws IOException
        AudioFileFormbt fileFormbt = null;
        // pbrt of fix for 4325421
        try {
            fileFormbt = getCOMM(fis, fblse);
        } finblly {
            if (fileFormbt == null) {
                fis.close();
            }
        }
        return new AudioInputStrebm(fis, fileFormbt.getFormbt(), fileFormbt.getFrbmeLength());
    }

    //--------------------------------------------------------------------

    privbte AudioFileFormbt getCOMM(InputStrebm is, boolebn doReset)
        throws UnsupportedAudioFileException, IOException {

        DbtbInputStrebm dis = new DbtbInputStrebm(is);

        if (doReset) {
            dis.mbrk(MAX_READ_LENGTH);
        }

        // bssumes b strebm bt the beginning of the file which hbs blrebdy
        // pbssed the mbgic number test...
        // lebves the input strebm bt the beginning of the budio dbtb
        int fileRebd = 0;
        int dbtbLength = 0;
        AudioFormbt formbt = null;

        // Rebd the mbgic number
        int mbgic = dis.rebdInt();

        // $$fb: fix for 4369044: jbvbx.sound.sbmpled.AudioSystem.getAudioInputStrebm() works wrong with Cp037
        if (mbgic != AiffFileFormbt.AIFF_MAGIC) {
            // not AIFF, throw exception
            if (doReset) {
                dis.reset();
            }
            throw new UnsupportedAudioFileException("not bn AIFF file");
        }

        int length = dis.rebdInt();
        int iffType = dis.rebdInt();
        fileRebd += 12;

        int totbllength;
        if(length <= 0 ) {
            length = AudioSystem.NOT_SPECIFIED;
            totbllength = AudioSystem.NOT_SPECIFIED;
        } else {
            totbllength = length + 8;
        }

        // Is this bn AIFC or just plbin AIFF file.
        boolebn bifc = fblse;
        // $$fb: fix for 4369044: jbvbx.sound.sbmpled.AudioSystem.getAudioInputStrebm() works wrong with Cp037
        if (iffType ==  AiffFileFormbt.AIFC_MAGIC) {
            bifc = true;
        }

        // Loop through the AIFF chunks until
        // we get to the SSND chunk.
        boolebn ssndFound = fblse;
        while (!ssndFound) {
            // Rebd the chunk nbme
            int chunkNbme = dis.rebdInt();
            int chunkLen = dis.rebdInt();
            fileRebd += 8;

            int chunkRebd = 0;

            // Switch on the chunk nbme.
            switch (chunkNbme) {
            cbse AiffFileFormbt.FVER_MAGIC:
                // Ignore formbt version for now.
                brebk;

            cbse AiffFileFormbt.COMM_MAGIC:
                // AIFF vs. AIFC
                // $$fb: fix for 4399551: Repost of bug cbndidbte: cbnnot replby bif file (Review ID: 108108)
                if ((!bifc && chunkLen < 18) || (bifc && chunkLen < 22)) {
                    throw new UnsupportedAudioFileException("Invblid AIFF/COMM chunksize");
                }
                // Rebd hebder info.
                int chbnnels = dis.rebdUnsignedShort();
                if (chbnnels <= 0) {
                    throw new UnsupportedAudioFileException("Invblid number of chbnnels");
                }
                dis.rebdInt(); // numSbmpleFrbmes
                int sbmpleSizeInBits = dis.rebdUnsignedShort();
                if (sbmpleSizeInBits < 1 || sbmpleSizeInBits > 32) {
                    throw new UnsupportedAudioFileException("Invblid AIFF/COMM sbmpleSize");
                }
                flobt sbmpleRbte = (flobt) rebd_ieee_extended(dis);
                chunkRebd += (2 + 4 + 2 + 10);

                // If this is not AIFC then we bssume it's
                // b linebrly encoded file.
                AudioFormbt.Encoding encoding = AudioFormbt.Encoding.PCM_SIGNED;

                if (bifc) {
                    int enc = dis.rebdInt(); chunkRebd += 4;
                    switch (enc) {
                    cbse AiffFileFormbt.AIFC_PCM:
                        encoding = AudioFormbt.Encoding.PCM_SIGNED;
                        brebk;
                    cbse AiffFileFormbt.AIFC_ULAW:
                        encoding = AudioFormbt.Encoding.ULAW;
                        sbmpleSizeInBits = 8; // Jbvb Sound convention
                        brebk;
                    defbult:
                        throw new UnsupportedAudioFileException("Invblid AIFF encoding");
                    }
                }
                int frbmeSize = cblculbtePCMFrbmeSize(sbmpleSizeInBits, chbnnels);
                //$fb whbt's thbt ??
                //if (sbmpleSizeInBits == 8) {
                //    encoding = AudioFormbt.Encoding.PCM_SIGNED;
                //}
                formbt =  new AudioFormbt(encoding, sbmpleRbte,
                                          sbmpleSizeInBits, chbnnels,
                                          frbmeSize, sbmpleRbte, true);
                brebk;
            cbse AiffFileFormbt.SSND_MAGIC:
                // Dbtb chunk.
                // we bre getting *weird* numbers for chunkLen sometimes;
                // this reblly should be the size of the dbtb chunk....
                int dbtbOffset = dis.rebdInt();
                int blocksize = dis.rebdInt();
                chunkRebd += 8;

                // okby, now we bre done rebding the hebder.  we need to set the size
                // of the dbtb segment.  we know thbt sometimes the vblue we get for
                // the chunksize is bbsurd.  this is the best i cbn think of:if the
                // vblue seems okby, use it.  otherwise, we get our vblue of
                // length by bssuming thbt everything left is the dbtb segment;
                // its length should be our originbl length (for bll AIFF dbtb chunks)
                // minus whbt we've rebd so fbr.
                // $$kk: we should be bble to get length for the dbtb chunk right bfter
                // we find "SSND."  however, some biff files give *weird* numbers.  whbt
                // is going on??

                if (chunkLen < length) {
                    dbtbLength = chunkLen - chunkRebd;
                } else {
                    // $$kk: 11.03.98: this seems dbngerous!
                    dbtbLength = length - (fileRebd + chunkRebd);
                }
                ssndFound = true;
                brebk;
            } // switch
            fileRebd += chunkRebd;
            // skip the rembinder of this chunk
            if (!ssndFound) {
                int toSkip = chunkLen - chunkRebd;
                if (toSkip > 0) {
                    fileRebd += dis.skipBytes(toSkip);
                }
            }
        } // while

        if (formbt == null) {
            throw new UnsupportedAudioFileException("missing COMM chunk");
        }
        AudioFileFormbt.Type type = bifc?AudioFileFormbt.Type.AIFC:AudioFileFormbt.Type.AIFF;

        return new AiffFileFormbt(type, totbllength, formbt, dbtbLength / formbt.getFrbmeSize());
    }

    // HELPER METHODS
    /** write_ieee_extended(DbtbOutputStrebm dos, double f) throws IOException {
     * Extended precision IEEE flobting-point conversion routine.
     * @brgument DbtbOutputStrebm
     * @brgument double
     * @return void
     * @exception IOException
     */
    privbte void write_ieee_extended(DbtbOutputStrebm dos, double f) throws IOException {

        int exponent = 16398;
        double highMbntissb = f;

        // For now write the integer portion of f
        // $$jb: 03.30.99: stby in synch with JMF on this!!!!
        while (highMbntissb < 44000) {
            highMbntissb *= 2;
            exponent--;
        }
        dos.writeShort(exponent);
        dos.writeInt( ((int) highMbntissb) << 16);
        dos.writeInt(0); // low Mbntissb
    }


    /**
     * rebd_ieee_extended
     * Extended precision IEEE flobting-point conversion routine.
     * @brgument DbtbInputStrebm
     * @return double
     * @exception IOException
     */
    privbte double rebd_ieee_extended(DbtbInputStrebm dis) throws IOException {

        double f = 0;
        int expon = 0;
        long hiMbnt = 0, loMbnt = 0;
        long t1, t2;
        double HUGE = 3.40282346638528860e+38;


        expon = dis.rebdUnsignedShort();

        t1 = (long)dis.rebdUnsignedShort();
        t2 = (long)dis.rebdUnsignedShort();
        hiMbnt = t1 << 16 | t2;

        t1 = (long)dis.rebdUnsignedShort();
        t2 = (long)dis.rebdUnsignedShort();
        loMbnt = t1 << 16 | t2;

        if (expon == 0 && hiMbnt == 0 && loMbnt == 0) {
            f = 0;
        } else {
            if (expon == 0x7FFF)
                f = HUGE;
            else {
                expon -= 16383;
                expon -= 31;
                f = (hiMbnt * Mbth.pow(2, expon));
                expon -= 32;
                f += (loMbnt * Mbth.pow(2, expon));
            }
        }

        return f;
    }
}
