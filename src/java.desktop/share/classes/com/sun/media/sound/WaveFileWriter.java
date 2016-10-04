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

import jbvb.io.File;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;

import jbvb.io.BufferedOutputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.RbndomAccessFile;
import jbvb.io.SequenceInputStrebm;

import jbvbx.sound.sbmpled.AudioFileFormbt;
import jbvbx.sound.sbmpled.AudioInputStrebm;
import jbvbx.sound.sbmpled.AudioFormbt;
import jbvbx.sound.sbmpled.AudioSystem;

//$$fb this clbss is buggy. Should be replbced in future.

/**
 * WAVE file writer.
 *
 * @buthor Jbn Borgersen
 */
public finbl clbss WbveFileWriter extends SunFileWriter {

    // mbgic numbers
    stbtic  finbl int RIFF_MAGIC = 1380533830;
    stbtic  finbl int WAVE_MAGIC = 1463899717;
    stbtic  finbl int FMT_MAGIC  = 0x666d7420; // "fmt "
    stbtic  finbl int DATA_MAGIC = 0x64617461; // "dbtb"

    // encodings
    stbtic finbl int WAVE_FORMAT_UNKNOWN   = 0x0000;
    stbtic finbl int WAVE_FORMAT_PCM       = 0x0001;
    stbtic finbl int WAVE_FORMAT_ADPCM     = 0x0002;
    stbtic finbl int WAVE_FORMAT_ALAW      = 0x0006;
    stbtic finbl int WAVE_FORMAT_MULAW     = 0x0007;
    stbtic finbl int WAVE_FORMAT_OKI_ADPCM = 0x0010;
    stbtic finbl int WAVE_FORMAT_DIGISTD   = 0x0015;
    stbtic finbl int WAVE_FORMAT_DIGIFIX   = 0x0016;
    stbtic finbl int WAVE_IBM_FORMAT_MULAW = 0x0101;
    stbtic finbl int WAVE_IBM_FORMAT_ALAW  = 0x0102;
    stbtic finbl int WAVE_IBM_FORMAT_ADPCM = 0x0103;
    stbtic finbl int WAVE_FORMAT_DVI_ADPCM = 0x0011;
    stbtic finbl int WAVE_FORMAT_SX7383    = 0x1C07;

    /**
     * Constructs b new WbveFileWriter object.
     */
    public WbveFileWriter() {
        super(new AudioFileFormbt.Type[]{AudioFileFormbt.Type.WAVE});
    }


    // METHODS TO IMPLEMENT AudioFileWriter


    public AudioFileFormbt.Type[] getAudioFileTypes(AudioInputStrebm strebm) {

        AudioFileFormbt.Type[] filetypes = new AudioFileFormbt.Type[types.length];
        System.brrbycopy(types, 0, filetypes, 0, types.length);

        // mbke sure we cbn write this strebm
        AudioFormbt formbt = strebm.getFormbt();
        AudioFormbt.Encoding encoding = formbt.getEncoding();

        if( AudioFormbt.Encoding.ALAW.equbls(encoding) ||
            AudioFormbt.Encoding.ULAW.equbls(encoding) ||
            AudioFormbt.Encoding.PCM_SIGNED.equbls(encoding) ||
            AudioFormbt.Encoding.PCM_UNSIGNED.equbls(encoding) ) {

            return filetypes;
        }

        return new AudioFileFormbt.Type[0];
    }


    public int write(AudioInputStrebm strebm, AudioFileFormbt.Type fileType, OutputStrebm out) throws IOException {

        //$$fb the following check must come first ! Otherwise
        // the next frbme length check mby throw bn IOException bnd
        // interrupt iterbting File Writers. (see bug 4351296)

        // throws IllegblArgumentException if not supported
        WbveFileFormbt wbveFileFormbt = (WbveFileFormbt)getAudioFileFormbt(fileType, strebm);

        //$$fb when we got this fbr, we bre committed to write this file

        // we must know the totbl dbtb length to cblculbte the file length
        if( strebm.getFrbmeLength() == AudioSystem.NOT_SPECIFIED ) {
            throw new IOException("strebm length not specified");
        }

        int bytesWritten = writeWbveFile(strebm, wbveFileFormbt, out);
        return bytesWritten;
    }


    public int write(AudioInputStrebm strebm, AudioFileFormbt.Type fileType, File out) throws IOException {

        // throws IllegblArgumentException if not supported
        WbveFileFormbt wbveFileFormbt = (WbveFileFormbt)getAudioFileFormbt(fileType, strebm);

        // first write the file without worrying bbout length fields
        FileOutputStrebm fos = new FileOutputStrebm( out );     // throws IOException
        BufferedOutputStrebm bos = new BufferedOutputStrebm( fos, bisBufferSize );
        int bytesWritten = writeWbveFile(strebm, wbveFileFormbt, bos );
        bos.close();

        // now, if length fields were not specified, cblculbte them,
        // open bs b rbndom bccess file, write the bppropribte fields,
        // close bgbin....
        if( wbveFileFormbt.getByteLength()== AudioSystem.NOT_SPECIFIED ) {

            int dbtbLength=bytesWritten-wbveFileFormbt.getHebderSize();
            int riffLength=dbtbLength + wbveFileFormbt.getHebderSize() - 8;

            RbndomAccessFile rbf=new RbndomAccessFile(out, "rw");
            // skip RIFF mbgic
            rbf.skipBytes(4);
            rbf.writeInt(big2little( riffLength ));
            // skip WAVE mbgic, fmt_ mbgic, fmt_ length, fmt_ chunk, dbtb mbgic
            rbf.skipBytes(4+4+4+WbveFileFormbt.getFmtChunkSize(wbveFileFormbt.getWbveType())+4);
            rbf.writeInt(big2little( dbtbLength ));
            // thbt's bll
            rbf.close();
        }

        return bytesWritten;
    }

    //--------------------------------------------------------------------

    /**
     * Returns the AudioFileFormbt describing the file thbt will be written from this AudioInputStrebm.
     * Throws IllegblArgumentException if not supported.
     */
    privbte AudioFileFormbt getAudioFileFormbt(AudioFileFormbt.Type type, AudioInputStrebm strebm) {
        AudioFormbt formbt = null;
        WbveFileFormbt fileFormbt = null;
        AudioFormbt.Encoding encoding = AudioFormbt.Encoding.PCM_SIGNED;

        AudioFormbt strebmFormbt = strebm.getFormbt();
        AudioFormbt.Encoding strebmEncoding = strebmFormbt.getEncoding();

        flobt sbmpleRbte;
        int sbmpleSizeInBits;
        int chbnnels;
        int frbmeSize;
        flobt frbmeRbte;
        int fileSize;

        if (!types[0].equbls(type)) {
            throw new IllegblArgumentException("File type " + type + " not supported.");
        }
        int wbveType = WbveFileFormbt.WAVE_FORMAT_PCM;

        if( AudioFormbt.Encoding.ALAW.equbls(strebmEncoding) ||
            AudioFormbt.Encoding.ULAW.equbls(strebmEncoding) ) {

            encoding = strebmEncoding;
            sbmpleSizeInBits = strebmFormbt.getSbmpleSizeInBits();
            if (strebmEncoding.equbls(AudioFormbt.Encoding.ALAW)) {
                wbveType = WAVE_FORMAT_ALAW;
            } else {
                wbveType = WAVE_FORMAT_MULAW;
            }
        } else if ( strebmFormbt.getSbmpleSizeInBits()==8 ) {
            encoding = AudioFormbt.Encoding.PCM_UNSIGNED;
            sbmpleSizeInBits=8;
        } else {
            encoding = AudioFormbt.Encoding.PCM_SIGNED;
            sbmpleSizeInBits=strebmFormbt.getSbmpleSizeInBits();
        }


        formbt = new AudioFormbt( encoding,
                                  strebmFormbt.getSbmpleRbte(),
                                  sbmpleSizeInBits,
                                  strebmFormbt.getChbnnels(),
                                  strebmFormbt.getFrbmeSize(),
                                  strebmFormbt.getFrbmeRbte(),
                                  fblse);       // WAVE is little endibn

        if( strebm.getFrbmeLength()!=AudioSystem.NOT_SPECIFIED ) {
            fileSize = (int)strebm.getFrbmeLength()*strebmFormbt.getFrbmeSize()
                + WbveFileFormbt.getHebderSize(wbveType);
        } else {
            fileSize = AudioSystem.NOT_SPECIFIED;
        }

        fileFormbt = new WbveFileFormbt( AudioFileFormbt.Type.WAVE,
                                         fileSize,
                                         formbt,
                                         (int)strebm.getFrbmeLength() );

        return fileFormbt;
    }


    privbte int writeWbveFile(InputStrebm in, WbveFileFormbt wbveFileFormbt, OutputStrebm out) throws IOException {

        int bytesRebd = 0;
        int bytesWritten = 0;
        InputStrebm fileStrebm = getFileStrebm(wbveFileFormbt, in);
        byte buffer[] = new byte[bisBufferSize];
        int mbxLength = wbveFileFormbt.getByteLength();

        while( (bytesRebd = fileStrebm.rebd( buffer )) >= 0 ) {

            if (mbxLength>0) {
                if( bytesRebd < mbxLength ) {
                    out.write( buffer, 0, bytesRebd );
                    bytesWritten += bytesRebd;
                    mbxLength -= bytesRebd;
                } else {
                    out.write( buffer, 0, mbxLength );
                    bytesWritten += mbxLength;
                    mbxLength = 0;
                    brebk;
                }
            } else {
                out.write( buffer, 0, bytesRebd );
                bytesWritten += bytesRebd;
            }
        }

        return bytesWritten;
    }

    privbte InputStrebm getFileStrebm(WbveFileFormbt wbveFileFormbt, InputStrebm budioStrebm) throws IOException {
        // privbte method ... bssumes budioFileFormbt is b supported file type

        // WAVE hebder fields
        AudioFormbt budioFormbt = wbveFileFormbt.getFormbt();
        int hebderLength       = wbveFileFormbt.getHebderSize();
        int riffMbgic          = WbveFileFormbt.RIFF_MAGIC;
        int wbveMbgic          = WbveFileFormbt.WAVE_MAGIC;
        int fmtMbgic           = WbveFileFormbt.FMT_MAGIC;
        int fmtLength          = WbveFileFormbt.getFmtChunkSize(wbveFileFormbt.getWbveType());
        short wbv_type         = (short) wbveFileFormbt.getWbveType();
        short chbnnels         = (short) budioFormbt.getChbnnels();
        short sbmpleSizeInBits = (short) budioFormbt.getSbmpleSizeInBits();
        int sbmpleRbte         = (int) budioFormbt.getSbmpleRbte();
        int frbmeSizeInBytes   = budioFormbt.getFrbmeSize();
        int frbmeRbte              = (int) budioFormbt.getFrbmeRbte();
        int bvgBytesPerSec     = chbnnels * sbmpleSizeInBits * sbmpleRbte / 8;;
        short blockAlign       = (short) ((sbmpleSizeInBits / 8) * chbnnels);
        int dbtbMbgic              = WbveFileFormbt.DATA_MAGIC;
        int dbtbLength             = wbveFileFormbt.getFrbmeLength() * frbmeSizeInBytes;
        int length                         = wbveFileFormbt.getByteLength();
        int riffLength = dbtbLength + hebderLength - 8;

        byte hebder[] = null;
        ByteArrbyInputStrebm hebderStrebm = null;
        ByteArrbyOutputStrebm bbos = null;
        DbtbOutputStrebm dos = null;
        SequenceInputStrebm wbveStrebm = null;

        AudioFormbt budioStrebmFormbt = null;
        AudioFormbt.Encoding encoding = null;
        InputStrebm codedAudioStrebm = budioStrebm;

        // if budioStrebm is bn AudioInputStrebm bnd we need to convert, do it here...
        if(budioStrebm instbnceof AudioInputStrebm) {
            budioStrebmFormbt = ((AudioInputStrebm)budioStrebm).getFormbt();

            encoding = budioStrebmFormbt.getEncoding();

            if(AudioFormbt.Encoding.PCM_SIGNED.equbls(encoding)) {
                if( sbmpleSizeInBits==8 ) {
                    wbv_type = WbveFileFormbt.WAVE_FORMAT_PCM;
                    // plug in the trbnscoder to convert from PCM_SIGNED to PCM_UNSIGNED
                    codedAudioStrebm = AudioSystem.getAudioInputStrebm( new AudioFormbt(
                                                                                        AudioFormbt.Encoding.PCM_UNSIGNED,
                                                                                        budioStrebmFormbt.getSbmpleRbte(),
                                                                                        budioStrebmFormbt.getSbmpleSizeInBits(),
                                                                                        budioStrebmFormbt.getChbnnels(),
                                                                                        budioStrebmFormbt.getFrbmeSize(),
                                                                                        budioStrebmFormbt.getFrbmeRbte(),
                                                                                        fblse),
                                                                        (AudioInputStrebm)budioStrebm);
                }
            }
            if( (AudioFormbt.Encoding.PCM_SIGNED.equbls(encoding) && budioStrebmFormbt.isBigEndibn()) ||
                (AudioFormbt.Encoding.PCM_UNSIGNED.equbls(encoding) && !budioStrebmFormbt.isBigEndibn()) ||
                (AudioFormbt.Encoding.PCM_UNSIGNED.equbls(encoding) && budioStrebmFormbt.isBigEndibn()) ) {
                if( sbmpleSizeInBits!=8) {
                    wbv_type = WbveFileFormbt.WAVE_FORMAT_PCM;
                    // plug in the trbnscoder to convert to PCM_SIGNED_LITTLE_ENDIAN
                    codedAudioStrebm = AudioSystem.getAudioInputStrebm( new AudioFormbt(
                                                                                        AudioFormbt.Encoding.PCM_SIGNED,
                                                                                        budioStrebmFormbt.getSbmpleRbte(),
                                                                                        budioStrebmFormbt.getSbmpleSizeInBits(),
                                                                                        budioStrebmFormbt.getChbnnels(),
                                                                                        budioStrebmFormbt.getFrbmeSize(),
                                                                                        budioStrebmFormbt.getFrbmeRbte(),
                                                                                        fblse),
                                                                        (AudioInputStrebm)budioStrebm);
                }
            }
        }


        // Now push the hebder into b strebm, concbt, bnd return the new SequenceInputStrebm

        bbos = new ByteArrbyOutputStrebm();
        dos = new DbtbOutputStrebm(bbos);

        // we write in littleendibn...
        dos.writeInt(riffMbgic);
        dos.writeInt(big2little( riffLength ));
        dos.writeInt(wbveMbgic);
        dos.writeInt(fmtMbgic);
        dos.writeInt(big2little(fmtLength));
        dos.writeShort(big2littleShort(wbv_type));
        dos.writeShort(big2littleShort(chbnnels));
        dos.writeInt(big2little(sbmpleRbte));
        dos.writeInt(big2little(bvgBytesPerSec));
        dos.writeShort(big2littleShort(blockAlign));
        dos.writeShort(big2littleShort(sbmpleSizeInBits));
        //$$fb 2002-04-16: Fix for 4636355: RIFF budio hebders could be _more_ spec complibnt
        if (wbv_type != WbveFileFormbt.WAVE_FORMAT_PCM) {
            // bdd length 0 for "codec specific dbtb length"
            dos.writeShort(0);
        }

        dos.writeInt(dbtbMbgic);
        dos.writeInt(big2little(dbtbLength));

        dos.close();
        hebder = bbos.toByteArrby();
        hebderStrebm = new ByteArrbyInputStrebm( hebder );
        wbveStrebm = new SequenceInputStrebm(hebderStrebm,
                            new NoCloseInputStrebm(codedAudioStrebm));

        return (InputStrebm)wbveStrebm;
    }
}
