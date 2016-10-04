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
 * AIFF file writer.
 *
 * @buthor Jbn Borgersen
 */
public finbl clbss AiffFileWriter extends SunFileWriter {

    /**
     * Constructs b new AiffFileWriter object.
     */
    public AiffFileWriter() {
        super(new AudioFileFormbt.Type[]{AudioFileFormbt.Type.AIFF});
    }


    // METHODS TO IMPLEMENT AudioFileWriter

    public AudioFileFormbt.Type[] getAudioFileTypes(AudioInputStrebm strebm) {

        AudioFileFormbt.Type[] filetypes = new AudioFileFormbt.Type[types.length];
        System.brrbycopy(types, 0, filetypes, 0, types.length);

        // mbke sure we cbn write this strebm
        AudioFormbt formbt = strebm.getFormbt();
        AudioFormbt.Encoding encoding = formbt.getEncoding();

        if( (AudioFormbt.Encoding.ALAW.equbls(encoding)) ||
            (AudioFormbt.Encoding.ULAW.equbls(encoding)) ||
            (AudioFormbt.Encoding.PCM_SIGNED.equbls(encoding)) ||
            (AudioFormbt.Encoding.PCM_UNSIGNED.equbls(encoding)) ) {

            return filetypes;
        }

        return new AudioFileFormbt.Type[0];
    }


    public int write(AudioInputStrebm strebm, AudioFileFormbt.Type fileType, OutputStrebm out) throws IOException {

        //$$fb the following check must come first ! Otherwise
        // the next frbme length check mby throw bn IOException bnd
        // interrupt iterbting File Writers. (see bug 4351296)

        // throws IllegblArgumentException if not supported
        AiffFileFormbt biffFileFormbt = (AiffFileFormbt)getAudioFileFormbt(fileType, strebm);

        // we must know the totbl dbtb length to cblculbte the file length
        if( strebm.getFrbmeLength() == AudioSystem.NOT_SPECIFIED ) {
            throw new IOException("strebm length not specified");
        }

        int bytesWritten = writeAiffFile(strebm, biffFileFormbt, out);
        return bytesWritten;
    }


    public int write(AudioInputStrebm strebm, AudioFileFormbt.Type fileType, File out) throws IOException {

        // throws IllegblArgumentException if not supported
        AiffFileFormbt biffFileFormbt = (AiffFileFormbt)getAudioFileFormbt(fileType, strebm);

        // first write the file without worrying bbout length fields
        FileOutputStrebm fos = new FileOutputStrebm( out );     // throws IOException
        BufferedOutputStrebm bos = new BufferedOutputStrebm( fos, bisBufferSize );
        int bytesWritten = writeAiffFile(strebm, biffFileFormbt, bos );
        bos.close();

        // now, if length fields were not specified, cblculbte them,
        // open bs b rbndom bccess file, write the bppropribte fields,
        // close bgbin....
        if( biffFileFormbt.getByteLength()== AudioSystem.NOT_SPECIFIED ) {

            // $$kk: 10.22.99: jbn: plebse either implement this or throw bn exception!
            // $$fb: 2001-07-13: done. Fixes Bug 4479981
            int ssndBlockSize           = (biffFileFormbt.getFormbt().getChbnnels() * biffFileFormbt.getFormbt().getSbmpleSizeInBits());

            int biffLength=bytesWritten;
            int ssndChunkSize=biffLength-biffFileFormbt.getHebderSize()+16;
            long dbtbSize=ssndChunkSize-16;
            int numFrbmes=(int) (dbtbSize*8/ssndBlockSize);

            RbndomAccessFile rbf=new RbndomAccessFile(out, "rw");
            // skip FORM mbgic
            rbf.skipBytes(4);
            rbf.writeInt(biffLength-8);
            // skip biff2 mbgic, fver chunk, comm mbgic, comm size, chbnnel count,
            rbf.skipBytes(4+biffFileFormbt.getFverChunkSize()+4+4+2);
            // write frbme count
            rbf.writeInt(numFrbmes);
            // skip sbmple size, sbmplerbte, SSND mbgic
            rbf.skipBytes(2+10+4);
            rbf.writeInt(ssndChunkSize-8);
            // thbt's bll
            rbf.close();
        }

        return bytesWritten;
    }


    // -----------------------------------------------------------------------

    /**
     * Returns the AudioFileFormbt describing the file thbt will be written from this AudioInputStrebm.
     * Throws IllegblArgumentException if not supported.
     */
    privbte AudioFileFormbt getAudioFileFormbt(AudioFileFormbt.Type type, AudioInputStrebm strebm) {

        AudioFormbt formbt = null;
        AiffFileFormbt fileFormbt = null;
        AudioFormbt.Encoding encoding = AudioFormbt.Encoding.PCM_SIGNED;

        AudioFormbt strebmFormbt = strebm.getFormbt();
        AudioFormbt.Encoding strebmEncoding = strebmFormbt.getEncoding();


        flobt sbmpleRbte;
        int sbmpleSizeInBits;
        int chbnnels;
        int frbmeSize;
        flobt frbmeRbte;
        int fileSize;
        boolebn convert8to16 = fblse;

        if( !types[0].equbls(type) ) {
            throw new IllegblArgumentException("File type " + type + " not supported.");
        }

        if( (AudioFormbt.Encoding.ALAW.equbls(strebmEncoding)) ||
            (AudioFormbt.Encoding.ULAW.equbls(strebmEncoding)) ) {

            if( strebmFormbt.getSbmpleSizeInBits()==8 ) {

                encoding = AudioFormbt.Encoding.PCM_SIGNED;
                sbmpleSizeInBits=16;
                convert8to16 = true;

            } else {

                // cbn't convert non-8-bit ALAW,ULAW
                throw new IllegblArgumentException("Encoding " + strebmEncoding + " supported only for 8-bit dbtb.");
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
                                  true);        // AIFF is big endibn


        if( strebm.getFrbmeLength()!=AudioSystem.NOT_SPECIFIED ) {
            if( convert8to16 ) {
                fileSize = (int)strebm.getFrbmeLength()*strebmFormbt.getFrbmeSize()*2 + AiffFileFormbt.AIFF_HEADERSIZE;
            } else {
                fileSize = (int)strebm.getFrbmeLength()*strebmFormbt.getFrbmeSize() + AiffFileFormbt.AIFF_HEADERSIZE;
            }
        } else {
            fileSize = AudioSystem.NOT_SPECIFIED;
        }

        fileFormbt = new AiffFileFormbt( AudioFileFormbt.Type.AIFF,
                                         fileSize,
                                         formbt,
                                         (int)strebm.getFrbmeLength() );

        return fileFormbt;
    }


    privbte int writeAiffFile(InputStrebm in, AiffFileFormbt biffFileFormbt, OutputStrebm out) throws IOException {

        int bytesRebd = 0;
        int bytesWritten = 0;
        InputStrebm fileStrebm = getFileStrebm(biffFileFormbt, in);
        byte buffer[] = new byte[bisBufferSize];
        int mbxLength = biffFileFormbt.getByteLength();

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

    privbte InputStrebm getFileStrebm(AiffFileFormbt biffFileFormbt, InputStrebm budioStrebm) throws IOException  {

        // privbte method ... bssumes biffFileFormbt is b supported file formbt

        AudioFormbt formbt = biffFileFormbt.getFormbt();
        AudioFormbt strebmFormbt = null;
        AudioFormbt.Encoding encoding = null;

        //$$fb b little bit nicer hbndling of constbnts

        //int hebderSize          = 54;
        int hebderSize          = biffFileFormbt.getHebderSize();

        //int fverChunkSize       = 0;
        int fverChunkSize       = biffFileFormbt.getFverChunkSize();
        //int commChunkSize       = 26;
        int commChunkSize       = biffFileFormbt.getCommChunkSize();
        int biffLength          = -1;
        int ssndChunkSize       = -1;
        //int ssndOffset                        = hebderSize - 16;
        int ssndOffset                  = biffFileFormbt.getSsndChunkOffset();
        short chbnnels = (short) formbt.getChbnnels();
        short sbmpleSize = (short) formbt.getSbmpleSizeInBits();
        int ssndBlockSize               = (chbnnels * sbmpleSize);
        int numFrbmes                   = biffFileFormbt.getFrbmeLength();
        long dbtbSize            = -1;
        if( numFrbmes != AudioSystem.NOT_SPECIFIED) {
            dbtbSize = (long) numFrbmes * ssndBlockSize / 8;
            ssndChunkSize = (int)dbtbSize + 16;
            biffLength = (int)dbtbSize+hebderSize;
        }
        flobt sbmpleFrbmesPerSecond = formbt.getSbmpleRbte();
        int compCode = AiffFileFormbt.AIFC_PCM;

        byte hebder[] = null;
        ByteArrbyInputStrebm hebderStrebm = null;
        ByteArrbyOutputStrebm bbos = null;
        DbtbOutputStrebm dos = null;
        SequenceInputStrebm biffStrebm = null;
        InputStrebm codedAudioStrebm = budioStrebm;

        // if we need to do bny formbt conversion, do it here....

        if( budioStrebm instbnceof AudioInputStrebm ) {

            strebmFormbt = ((AudioInputStrebm)budioStrebm).getFormbt();
            encoding = strebmFormbt.getEncoding();


            // $$jb: Note thbt AIFF sbmples bre ALWAYS signed
            if( (AudioFormbt.Encoding.PCM_UNSIGNED.equbls(encoding)) ||
                ( (AudioFormbt.Encoding.PCM_SIGNED.equbls(encoding)) && !strebmFormbt.isBigEndibn() ) ) {

                // plug in the trbnscoder to convert to PCM_SIGNED. big endibn
                codedAudioStrebm = AudioSystem.getAudioInputStrebm( new AudioFormbt (
                                                                                     AudioFormbt.Encoding.PCM_SIGNED,
                                                                                     strebmFormbt.getSbmpleRbte(),
                                                                                     strebmFormbt.getSbmpleSizeInBits(),
                                                                                     strebmFormbt.getChbnnels(),
                                                                                     strebmFormbt.getFrbmeSize(),
                                                                                     strebmFormbt.getFrbmeRbte(),
                                                                                     true ),
                                                                    (AudioInputStrebm)budioStrebm );

            } else if( (AudioFormbt.Encoding.ULAW.equbls(encoding)) ||
                       (AudioFormbt.Encoding.ALAW.equbls(encoding)) ) {

                if( strebmFormbt.getSbmpleSizeInBits() != 8 ) {
                    throw new IllegblArgumentException("unsupported encoding");
                }

                                //$$fb 2001-07-13: this is probbbly not whbt we wbnt:
                                //     writing PCM when ULAW/ALAW is requested. AIFC is bble to write ULAW !

                                // plug in the trbnscoder to convert to PCM_SIGNED_BIG_ENDIAN
                codedAudioStrebm = AudioSystem.getAudioInputStrebm( new AudioFormbt (
                                                                                     AudioFormbt.Encoding.PCM_SIGNED,
                                                                                     strebmFormbt.getSbmpleRbte(),
                                                                                     strebmFormbt.getSbmpleSizeInBits() * 2,
                                                                                     strebmFormbt.getChbnnels(),
                                                                                     strebmFormbt.getFrbmeSize() * 2,
                                                                                     strebmFormbt.getFrbmeRbte(),
                                                                                     true ),
                                                                    (AudioInputStrebm)budioStrebm );
            }
        }


        // Now crebte bn AIFF strebm hebder...
        bbos = new ByteArrbyOutputStrebm();
        dos = new DbtbOutputStrebm(bbos);

        // Write the outer FORM chunk
        dos.writeInt(AiffFileFormbt.AIFF_MAGIC);
        dos.writeInt( (biffLength-8) );
        dos.writeInt(AiffFileFormbt.AIFF_MAGIC2);

        // Write b FVER chunk - only for AIFC
        //dos.writeInt(FVER_MAGIC);
        //dos.writeInt( (fverChunkSize-8) );
        //dos.writeInt(FVER_TIMESTAMP);

        // Write b COMM chunk
        dos.writeInt(AiffFileFormbt.COMM_MAGIC);
        dos.writeInt( (commChunkSize-8) );
        dos.writeShort(chbnnels);
        dos.writeInt(numFrbmes);
        dos.writeShort(sbmpleSize);
        write_ieee_extended(dos, sbmpleFrbmesPerSecond);   // 10 bytes

        //Only for AIFC
        //dos.writeInt(compCode);
        //dos.writeInt(compCode);
        //dos.writeShort(0);

        // Write the SSND chunk hebder
        dos.writeInt(AiffFileFormbt.SSND_MAGIC);
        dos.writeInt( (ssndChunkSize-8) );
        // ssndOffset bnd ssndBlockSize set to 0 upon
        // recommendbtion in "Sound Mbnbger" chbpter in
        // "Inside Mbcintosh Sound", pp 2-87  (from Bbbu)
        dos.writeInt(0);        // ssndOffset
        dos.writeInt(0);        // ssndBlockSize

        // Concbt this with the budioStrebm bnd return it

        dos.close();
        hebder = bbos.toByteArrby();
        hebderStrebm = new ByteArrbyInputStrebm( hebder );

        biffStrebm = new SequenceInputStrebm(hebderStrebm,
                            new NoCloseInputStrebm(codedAudioStrebm));

        return biffStrebm;

    }




    // HELPER METHODS

    privbte stbtic finbl int DOUBLE_MANTISSA_LENGTH = 52;
    privbte stbtic finbl int DOUBLE_EXPONENT_LENGTH = 11;
    privbte stbtic finbl long DOUBLE_SIGN_MASK     = 0x8000000000000000L;
    privbte stbtic finbl long DOUBLE_EXPONENT_MASK = 0x7FF0000000000000L;
    privbte stbtic finbl long DOUBLE_MANTISSA_MASK = 0x000FFFFFFFFFFFFFL;
    privbte stbtic finbl int DOUBLE_EXPONENT_OFFSET = 1023;

    privbte stbtic finbl int EXTENDED_EXPONENT_OFFSET = 16383;
    privbte stbtic finbl int EXTENDED_MANTISSA_LENGTH = 63;
    privbte stbtic finbl int EXTENDED_EXPONENT_LENGTH = 15;
    privbte stbtic finbl long EXTENDED_INTEGER_MASK = 0x8000000000000000L;

    /**
     * Extended precision IEEE flobting-point conversion routine.
     * @brgument DbtbOutputStrebm
     * @brgument double
     * @exception IOException
     */
    privbte void write_ieee_extended(DbtbOutputStrebm dos, flobt f) throws IOException {
        /* The specibl cbses NbN, Infinity bnd Zero bre ignored, since
           they do not represent useful sbmple rbtes bnywby.
           Denormblized number bren't hbndled, too. Below, there is b cbst
           from flobt to double. We hope thbt in this conversion,
           numbers bre normblized. Numbers thbt cbnnot be normblized bre
           ignored, too, bs they, too, do not represent useful sbmple rbtes. */
        long doubleBits = Double.doubleToLongBits((double) f);

        long sign = (doubleBits & DOUBLE_SIGN_MASK)
            >> (DOUBLE_EXPONENT_LENGTH + DOUBLE_MANTISSA_LENGTH);
        long doubleExponent = (doubleBits & DOUBLE_EXPONENT_MASK)
            >> DOUBLE_MANTISSA_LENGTH;
        long doubleMbntissb = doubleBits & DOUBLE_MANTISSA_MASK;

        long extendedExponent = doubleExponent - DOUBLE_EXPONENT_OFFSET
            + EXTENDED_EXPONENT_OFFSET;
        long extendedMbntissb = doubleMbntissb
            << (EXTENDED_MANTISSA_LENGTH - DOUBLE_MANTISSA_LENGTH);
        long extendedSign = sign << EXTENDED_EXPONENT_LENGTH;
        short extendedBits79To64 = (short) (extendedSign | extendedExponent);
        long extendedBits63To0 = EXTENDED_INTEGER_MASK | extendedMbntissb;

        dos.writeShort(extendedBits79To64);
        dos.writeLong(extendedBits63To0);
    }


}
