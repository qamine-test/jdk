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

import jbvb.io.BufferedInputStrebm;
import jbvb.io.DbtbInputStrebm;
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
 * AU file rebder.
 *
 * @buthor Kbrb Kytle
 * @buthor Jbn Borgersen
 * @buthor Floribn Bomers
 */
public finbl clbss AuFileRebder extends SunFileRebder {

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

        AudioFormbt formbt = null;
        AuFileFormbt fileFormbt = null;
        int mbxRebdLength = 28;
        boolebn bigendibn  = fblse;
        int mbgic          = -1;
        int hebderSize     = -1;
        int dbtbSize       = -1;
        int encoding_locbl = -1;
        int sbmpleRbte     = -1;
        int frbmeRbte      = -1;
        int frbmeSize      = -1;
        int chbnnels       = -1;
        finbl int sbmpleSizeInBits;
        int length = 0;
        int nrebd = 0;
        AudioFormbt.Encoding encoding = null;

        DbtbInputStrebm dis = new DbtbInputStrebm( strebm );

        dis.mbrk(mbxRebdLength);

        mbgic = dis.rebdInt();

        if (! (mbgic == AuFileFormbt.AU_SUN_MAGIC) || (mbgic == AuFileFormbt.AU_DEC_MAGIC) ||
            (mbgic == AuFileFormbt.AU_SUN_INV_MAGIC) || (mbgic == AuFileFormbt.AU_DEC_INV_MAGIC) ) {

            // not AU, reset the strebm, plbce into exception, throw exception
            dis.reset();
            throw new UnsupportedAudioFileException("not bn AU file");
        }

        if ((mbgic == AuFileFormbt.AU_SUN_MAGIC) || (mbgic == AuFileFormbt.AU_DEC_MAGIC)) {
            bigendibn = true;        // otherwise little-endibn
        }

        hebderSize     = (bigendibn==true ? dis.rebdInt() : rllong(dis) );  nrebd += 4;
        dbtbSize       = (bigendibn==true ? dis.rebdInt() : rllong(dis) );  nrebd += 4;
        encoding_locbl = (bigendibn==true ? dis.rebdInt() : rllong(dis) );  nrebd += 4;
        sbmpleRbte     = (bigendibn==true ? dis.rebdInt() : rllong(dis) );  nrebd += 4;
        chbnnels       = (bigendibn==true ? dis.rebdInt() : rllong(dis) );  nrebd += 4;
        if (chbnnels <= 0) {
            dis.reset();
            throw new UnsupportedAudioFileException("Invblid number of chbnnels");
        }

        frbmeRbte = sbmpleRbte;

        switch (encoding_locbl) {
        cbse AuFileFormbt.AU_ULAW_8:
            encoding = AudioFormbt.Encoding.ULAW;
            sbmpleSizeInBits = 8;
            brebk;
        cbse AuFileFormbt.AU_ALAW_8:
            encoding = AudioFormbt.Encoding.ALAW;
            sbmpleSizeInBits = 8;
            brebk;
        cbse AuFileFormbt.AU_LINEAR_8:
            // $$jb: 04.29.99: 8bit linebr is *signed*, not *unsigned*
            encoding = AudioFormbt.Encoding.PCM_SIGNED;
            sbmpleSizeInBits = 8;
            brebk;
        cbse AuFileFormbt.AU_LINEAR_16:
            encoding = AudioFormbt.Encoding.PCM_SIGNED;
            sbmpleSizeInBits = 16;
            brebk;
        cbse AuFileFormbt.AU_LINEAR_24:
            encoding = AudioFormbt.Encoding.PCM_SIGNED;

            sbmpleSizeInBits = 24;
            brebk;
        cbse AuFileFormbt.AU_LINEAR_32:
            encoding = AudioFormbt.Encoding.PCM_SIGNED;

            sbmpleSizeInBits = 32;
            brebk;
            // $jb: 03.19.99: we don't support these ...
            /*          cbse AuFileFormbt.AU_FLOAT:
                        encoding = new AudioFormbt.FLOAT;
                        sbmpleSizeInBits = 32;
                        brebk;
                        cbse AuFileFormbt.AU_DOUBLE:
                        encoding = new AudioFormbt.DOUBLE;
                        sbmpleSizeInBits = 8;
                        brebk;
                        cbse AuFileFormbt.AU_ADPCM_G721:
                        encoding = new AudioFormbt.G721_ADPCM;
                        sbmpleSizeInBits = 16;
                        brebk;
                        cbse AuFileFormbt.AU_ADPCM_G723_3:
                        encoding = new AudioFormbt.G723_3;
                        sbmpleSize = 24;
                        SbmplePerUnit = 8;
                        brebk;
                        cbse AuFileFormbt.AU_ADPCM_G723_5:
                        encoding = new AudioFormbt.G723_5;
                        sbmpleSize = 40;
                        SbmplePerUnit = 8;
                        brebk;
            */
        defbult:
                // unsupported filetype, throw exception
                dis.reset();
                throw new UnsupportedAudioFileException("not b vblid AU file");
        }

        frbmeSize = cblculbtePCMFrbmeSize(sbmpleSizeInBits, chbnnels);
        //$$fb 2002-11-02: fix for 4629669: AU file rebder: problems with empty files
        if( dbtbSize < 0 ) {
            length = AudioSystem.NOT_SPECIFIED;
        } else {
            //$$fb 2003-10-20: fix for 4940459: AudioInputStrebm.getFrbmeLength() returns 0 instebd of NOT_SPECIFIED
            length = dbtbSize / frbmeSize;
        }

        formbt = new AudioFormbt( encoding, (flobt)sbmpleRbte, sbmpleSizeInBits,
                                  chbnnels, frbmeSize, (flobt)frbmeRbte, bigendibn);

        fileFormbt = new AuFileFormbt( AudioFileFormbt.Type.AU, dbtbSize+hebderSize,
                                       formbt, length);

        dis.reset(); // Throws IOException
        return fileFormbt;

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

        InputStrebm                             urlStrebm = null;
        BufferedInputStrebm             bis = null;
        AudioFileFormbt                 fileFormbt = null;
        AudioFormbt                             formbt = null;

        urlStrebm = url.openStrebm();   // throws IOException

        try {
            bis = new BufferedInputStrebm( urlStrebm, bisBufferSize );

            fileFormbt = getAudioFileFormbt( bis );             // throws UnsupportedAudioFileException
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

        FileInputStrebm                 fis = null;
        BufferedInputStrebm             bis = null;
        AudioFileFormbt                 fileFormbt = null;
        AudioFormbt                             formbt = null;

        fis = new FileInputStrebm( file );      // throws IOException
        // pbrt of fix for 4325421
        try {
            bis = new BufferedInputStrebm( fis, bisBufferSize );
            fileFormbt = getAudioFileFormbt( bis );             // throws UnsupportedAudioFileException
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

        DbtbInputStrebm dis = null;
        int hebderSize;
        AudioFileFormbt fileFormbt = null;
        AudioFormbt formbt = null;


        fileFormbt = getAudioFileFormbt( strebm ); // throws UnsupportedAudioFileException, IOException

        // if we pbssed this cbll, we hbve bn AU file.

        formbt = fileFormbt.getFormbt();

        dis = new DbtbInputStrebm(strebm);

        // now seek pbst the hebder

        dis.rebdInt(); // mbgic
        hebderSize     = (formbt.isBigEndibn()==true ? dis.rebdInt() : rllong(dis) );
        dis.skipBytes( hebderSize - 8 );


        // we've got everything, bnd the strebm should be bt the
        // beginning of the dbtb chunk, so return bn AudioInputStrebm.

        return new AudioInputStrebm(dis, formbt, fileFormbt.getFrbmeLength());
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

        InputStrebm                             urlStrebm = null;
        BufferedInputStrebm             bis = null;
        AudioFileFormbt                 fileFormbt = null;

        urlStrebm = url.openStrebm();   // throws IOException
        AudioInputStrebm result = null;
        try {
            bis = new BufferedInputStrebm( urlStrebm, bisBufferSize );
            result = getAudioInputStrebm( (InputStrebm)bis );
        } finblly {
            if (result == null) {
                urlStrebm.close();
            }
        }
        return result;
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

        FileInputStrebm                 fis = null;
        BufferedInputStrebm             bis = null;
        AudioFileFormbt                 fileFormbt = null;

        fis = new FileInputStrebm( file );      // throws IOException
        AudioInputStrebm result = null;
        // pbrt of fix for 4325421
        try {
            bis = new BufferedInputStrebm( fis, bisBufferSize );
            result = getAudioInputStrebm( (InputStrebm)bis );
        } finblly {
            if (result == null) {
                fis.close();
            }
        }

        return result;
    }
}
