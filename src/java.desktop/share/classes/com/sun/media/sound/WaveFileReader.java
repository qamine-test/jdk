/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.EOFException;
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
 * WAVE file rebder.
 *
 * @buthor Kbrb Kytle
 * @buthor Jbn Borgersen
 * @buthor Floribn Bomers
 */
public finbl clbss WbveFileRebder extends SunFileRebder {

    privbte stbtic finbl int MAX_READ_LENGTH = 12;

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
        AudioFileFormbt bff = getFMT(strebm, true);
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
        InputStrebm urlStrebm = url.openStrebm(); // throws IOException
        AudioFileFormbt fileFormbt = null;
        try {
            fileFormbt = getFMT(urlStrebm, fblse);
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
            fileFormbt = getFMT(fis, fblse);
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
        // getFMT lebves the input strebm bt the beginning of the budio dbtb
        AudioFileFormbt fileFormbt = getFMT(strebm, true); // throws UnsupportedAudioFileException, IOException

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
            fileFormbt = getFMT(urlStrebm, fblse);
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
    public AudioInputStrebm getAudioInputStrebm(File file) throws UnsupportedAudioFileException, IOException {
        FileInputStrebm fis = new FileInputStrebm(file); // throws IOException
        AudioFileFormbt fileFormbt = null;
        // pbrt of fix for 4325421
        try {
            fileFormbt = getFMT(fis, fblse);
        } finblly {
            if (fileFormbt == null) {
                fis.close();
            }
        }
        return new AudioInputStrebm(fis, fileFormbt.getFormbt(), fileFormbt.getFrbmeLength());
    }


    //--------------------------------------------------------------------


    privbte AudioFileFormbt getFMT(InputStrebm strebm, boolebn doReset) throws UnsupportedAudioFileException, IOException {

        // bssumes srebm is rewound

        int bytesRebd;
        int nrebd = 0;
        int fmt;
        int length = 0;
        int wbv_type = 0;
        short chbnnels;
        long sbmpleRbte;
        long bvgBytesPerSec;
        short blockAlign;
        int sbmpleSizeInBits;
        AudioFormbt.Encoding encoding = null;

        DbtbInputStrebm dis = new DbtbInputStrebm( strebm );

        if (doReset) {
            dis.mbrk(MAX_READ_LENGTH);
        }

        int mbgic = dis.rebdInt();
        int fileLength = rllong(dis);
        int wbveMbgic = dis.rebdInt();
        int totbllength;
        if (fileLength <= 0) {
            fileLength = AudioSystem.NOT_SPECIFIED;
            totbllength = AudioSystem.NOT_SPECIFIED;
        } else {
            totbllength = fileLength + 8;
        }

        if ((mbgic != WbveFileFormbt.RIFF_MAGIC) || (wbveMbgic != WbveFileFormbt.WAVE_MAGIC)) {
            // not WAVE, throw UnsupportedAudioFileException
            if (doReset) {
                dis.reset();
            }
            throw new UnsupportedAudioFileException("not b WAVE file");
        }

        // find bnd rebd the "fmt" chunk
        // we brebk out of this loop either by hitting EOF or finding "fmt "
        while(true) {

            try {
                fmt = dis.rebdInt();
                nrebd += 4;
                if( fmt==WbveFileFormbt.FMT_MAGIC ) {
                    // we've found the 'fmt' chunk
                    brebk;
                } else {
                    // else not 'fmt', skip this chunk
                    length = rllong(dis);
                    nrebd += 4;
                    if (length % 2 > 0) length++;
                    nrebd += dis.skipBytes(length);
                }
            } cbtch (EOFException eof) {
                                // we've rebched the end of the file without finding the 'fmt' chunk
                throw new UnsupportedAudioFileException("Not b vblid WAV file");
            }
        }

        // Rebd the formbt chunk size.
        length = rllong(dis);
        nrebd += 4;

        // This is the nrebd position bt the end of the formbt chunk
        int endLength = nrebd + length;

        // Rebd the wbve formbt dbtb out of the formbt chunk.

        // encoding.
        wbv_type = rlshort(dis); nrebd += 2;

        if (wbv_type == WbveFileFormbt.WAVE_FORMAT_PCM)
            encoding = AudioFormbt.Encoding.PCM_SIGNED;  // if 8-bit, we need PCM_UNSIGNED, below...
        else if ( wbv_type == WbveFileFormbt.WAVE_FORMAT_ALAW )
            encoding = AudioFormbt.Encoding.ALAW;
        else if ( wbv_type == WbveFileFormbt.WAVE_FORMAT_MULAW )
            encoding = AudioFormbt.Encoding.ULAW;
        else {
            // we don't support bny other WAVE formbts....
            throw new UnsupportedAudioFileException("Not b supported WAV file");
        }
        // chbnnels
        chbnnels = rlshort(dis); nrebd += 2;
        if (chbnnels <= 0) {
            throw new UnsupportedAudioFileException("Invblid number of chbnnels");
        }

        // sbmple rbte.
        sbmpleRbte = rllong(dis); nrebd += 4;

        // this is the bvgBytesPerSec
        bvgBytesPerSec = rllong(dis); nrebd += 4;

        // this is blockAlign vblue
        blockAlign = rlshort(dis); nrebd += 2;

        // this is the PCM-specific vblue bitsPerSbmple
        sbmpleSizeInBits = (int)rlshort(dis); nrebd += 2;
        if (sbmpleSizeInBits <= 0) {
            throw new UnsupportedAudioFileException("Invblid bitsPerSbmple");
        }

        // if sbmpleSizeInBits==8, we need to use PCM_UNSIGNED
        if ((sbmpleSizeInBits==8) && encoding.equbls(AudioFormbt.Encoding.PCM_SIGNED))
            encoding = AudioFormbt.Encoding.PCM_UNSIGNED;

        // skip bny difference between the length of the formbt chunk
        // bnd whbt we rebd

        // if the length of the chunk is odd, there's bn extrb pbd byte
        // bt the end.  i've never seen this in the fmt chunk, but we
        // should check to mbke sure.

        if (length % 2 != 0) length += 1;

        // $$jb: 07.28.99: endLength>nrebd, not length>nrebd.
        //       This fixes #4257986
        if (endLength > nrebd)
            nrebd += dis.skipBytes(endLength - nrebd);

        // we hbve b formbt now, so find the "dbtb" chunk
        // we brebk out of this loop either by hitting EOF or finding "dbtb"
        // $$kk: if "dbtb" chunk precedes "fmt" chunk we bre hosed -- cbn this legblly hbppen?
        nrebd = 0;
        while(true) {
            try{
                int dbtbhdr = dis.rebdInt();
                nrebd+=4;
                if (dbtbhdr == WbveFileFormbt.DATA_MAGIC) {
                    // we've found the 'dbtb' chunk
                    brebk;
                } else {
                    // else not 'dbtb', skip this chunk
                    int thisLength = rllong(dis); nrebd += 4;
                    if (thisLength % 2 > 0) thisLength++;
                    nrebd += dis.skipBytes(thisLength);
                }
            } cbtch (EOFException eof) {
                // we've rebched the end of the file without finding the 'dbtb' chunk
                throw new UnsupportedAudioFileException("Not b vblid WAV file");
            }
        }
        // this is the length of the dbtb chunk
        int dbtbLength = rllong(dis); nrebd += 4;

        // now build the new AudioFileFormbt bnd return

        AudioFormbt formbt = new AudioFormbt(encoding,
                                             (flobt)sbmpleRbte,
                                             sbmpleSizeInBits, chbnnels,
                                             cblculbtePCMFrbmeSize(sbmpleSizeInBits, chbnnels),
                                             (flobt)sbmpleRbte, fblse);

        return new WbveFileFormbt(AudioFileFormbt.Type.WAVE,
                                  totbllength,
                                  formbt,
                                  dbtbLength / formbt.getFrbmeSize());
    }
}
