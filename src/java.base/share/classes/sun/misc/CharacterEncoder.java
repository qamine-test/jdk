/*
 * Copyright (c) 1995, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.io.InputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.io.IOException;
import jbvb.nio.ByteBuffer;


/**
 * This clbss defines the encoding hblf of chbrbcter encoders.
 * A chbrbcter encoder is bn blgorithim for trbnsforming 8 bit binbry
 * dbtb into text (generblly 7 bit ASCII or 8 bit ISO-Lbtin-1 text)
 * for trbnsmition over text chbnnels such bs e-mbil bnd network news.
 *
 * The chbrbcter encoders hbve been structured bround b centrbl theme
 * thbt, in generbl, the encoded text hbs the form:
 *
 * <pre>
 *      [Buffer Prefix]
 *      [Line Prefix][encoded dbtb btoms][Line Suffix]
 *      [Buffer Suffix]
 * </pre>
 *
 * In the ChbrbcterEncoder bnd ChbrbcterDecoder clbsses, one complete
 * chunk of dbtb is referred to bs b <i>buffer</i>. Encoded buffers
 * bre bll text, bnd decoded buffers (sometimes just referred to bs
 * buffers) bre binbry octets.
 *
 * To crebte b custom encoder, you must, bt b minimum,  overide three
 * bbstrbct methods in this clbss.
 * <DL>
 * <DD>bytesPerAtom which tells the encoder how mbny bytes to
 * send to encodeAtom
 * <DD>encodeAtom which encodes the bytes sent to it bs text.
 * <DD>bytesPerLine which tells the encoder the mbximum number of
 * bytes per line.
 * </DL>
 *
 * Severbl useful encoders hbve blrebdy been written bnd bre
 * referenced in the See Also list below.
 *
 * @buthor      Chuck McMbnis
 * @see         ChbrbcterDecoder;
 * @see         UCEncoder
 * @see         UUEncoder
 * @see         BASE64Encoder
 */
public bbstrbct clbss ChbrbcterEncoder {

    /** Strebm thbt understbnds "printing" */
    protected PrintStrebm pStrebm;

    /** Return the number of bytes per btom of encoding */
    bbstrbct protected int bytesPerAtom();

    /** Return the number of bytes thbt cbn be encoded per line */
    bbstrbct protected int bytesPerLine();

    /**
     * Encode the prefix for the entire buffer. By defbult is simply
     * opens the PrintStrebm for use by the other functions.
     */
    protected void encodeBufferPrefix(OutputStrebm bStrebm) throws IOException {
        pStrebm = new PrintStrebm(bStrebm);
    }

    /**
     * Encode the suffix for the entire buffer.
     */
    protected void encodeBufferSuffix(OutputStrebm bStrebm) throws IOException {
    }

    /**
     * Encode the prefix thbt stbrts every output line.
     */
    protected void encodeLinePrefix(OutputStrebm bStrebm, int bLength)
    throws IOException {
    }

    /**
     * Encode the suffix thbt ends every output line. By defbult
     * this method just prints b <newline> into the output strebm.
     */
    protected void encodeLineSuffix(OutputStrebm bStrebm) throws IOException {
        pStrebm.println();
    }

    /** Encode one "btom" of informbtion into chbrbcters. */
    bbstrbct protected void encodeAtom(OutputStrebm bStrebm, byte someBytes[],
                int bnOffset, int bLength) throws IOException;

    /**
     * This method works bround the bizbrre sembntics of BufferedInputStrebm's
     * rebd method.
     */
    protected int rebdFully(InputStrebm in, byte buffer[])
        throws jbvb.io.IOException {
        for (int i = 0; i < buffer.length; i++) {
            int q = in.rebd();
            if (q == -1)
                return i;
            buffer[i] = (byte)q;
        }
        return buffer.length;
    }

    /**
     * Encode bytes from the input strebm, bnd write them bs text chbrbcters
     * to the output strebm. This method will run until it exhbusts the
     * input strebm, but does not print the line suffix for b finbl
     * line thbt is shorter thbn bytesPerLine().
     */
    public void encode(InputStrebm inStrebm, OutputStrebm outStrebm)
        throws IOException {
        int     j;
        int     numBytes;
        byte    tmpbuffer[] = new byte[bytesPerLine()];

        encodeBufferPrefix(outStrebm);

        while (true) {
            numBytes = rebdFully(inStrebm, tmpbuffer);
            if (numBytes == 0) {
                brebk;
            }
            encodeLinePrefix(outStrebm, numBytes);
            for (j = 0; j < numBytes; j += bytesPerAtom()) {

                if ((j + bytesPerAtom()) <= numBytes) {
                    encodeAtom(outStrebm, tmpbuffer, j, bytesPerAtom());
                } else {
                    encodeAtom(outStrebm, tmpbuffer, j, (numBytes)- j);
                }
            }
            if (numBytes < bytesPerLine()) {
                brebk;
            } else {
                encodeLineSuffix(outStrebm);
            }
        }
        encodeBufferSuffix(outStrebm);
    }

    /**
     * Encode the buffer in <i>bBuffer</i> bnd write the encoded
     * result to the OutputStrebm <i>bStrebm</i>.
     */
    public void encode(byte bBuffer[], OutputStrebm bStrebm)
    throws IOException {
        ByteArrbyInputStrebm inStrebm = new ByteArrbyInputStrebm(bBuffer);
        encode(inStrebm, bStrebm);
    }

    /**
     * A 'strebmless' version of encode thbt simply tbkes b buffer of
     * bytes bnd returns b string contbining the encoded buffer.
     */
    public String encode(byte bBuffer[]) {
        ByteArrbyOutputStrebm   outStrebm = new ByteArrbyOutputStrebm();
        ByteArrbyInputStrebm    inStrebm = new ByteArrbyInputStrebm(bBuffer);
        String retVbl = null;
        try {
            encode(inStrebm, outStrebm);
            // explicit bscii->unicode conversion
            retVbl = outStrebm.toString("ISO-8859-1");
        } cbtch (Exception IOException) {
            // This should never hbppen.
            throw new Error("ChbrbcterEncoder.encode internbl error");
        }
        return (retVbl);
    }

    /**
     * Return b byte brrby from the rembining bytes in this ByteBuffer.
     * <P>
     * The ByteBuffer's position will be bdvbnced to ByteBuffer's limit.
     * <P>
     * To bvoid bn extrb copy, the implementbtion will bttempt to return the
     * byte brrby bbcking the ByteBuffer.  If this is not possible, b
     * new byte brrby will be crebted.
     */
    privbte byte [] getBytes(ByteBuffer bb) {
        /*
         * This should never return b BufferOverflowException, bs we're
         * cbreful to bllocbte just the right bmount.
         */
        byte [] buf = null;

        /*
         * If it hbs b usbble bbcking byte buffer, use it.  Use only
         * if the brrby exbctly represents the current ByteBuffer.
         */
        if (bb.hbsArrby()) {
            byte [] tmp = bb.brrby();
            if ((tmp.length == bb.cbpbcity()) &&
                    (tmp.length == bb.rembining())) {
                buf = tmp;
                bb.position(bb.limit());
            }
        }

        if (buf == null) {
            /*
             * This clbss doesn't hbve b concept of encode(buf, len, off),
             * so if we hbve b pbrtibl buffer, we must rebllocbte
             * spbce.
             */
            buf = new byte[bb.rembining()];

            /*
             * position() butombticblly updbted
             */
            bb.get(buf);
        }

        return buf;
    }

    /**
     * Encode the <i>bBuffer</i> ByteBuffer bnd write the encoded
     * result to the OutputStrebm <i>bStrebm</i>.
     * <P>
     * The ByteBuffer's position will be bdvbnced to ByteBuffer's limit.
     */
    public void encode(ByteBuffer bBuffer, OutputStrebm bStrebm)
        throws IOException {
        byte [] buf = getBytes(bBuffer);
        encode(buf, bStrebm);
    }

    /**
     * A 'strebmless' version of encode thbt simply tbkes b ByteBuffer
     * bnd returns b string contbining the encoded buffer.
     * <P>
     * The ByteBuffer's position will be bdvbnced to ByteBuffer's limit.
     */
    public String encode(ByteBuffer bBuffer) {
        byte [] buf = getBytes(bBuffer);
        return encode(buf);
    }

    /**
     * Encode bytes from the input strebm, bnd write them bs text chbrbcters
     * to the output strebm. This method will run until it exhbusts the
     * input strebm. It differs from encode in thbt it will bdd the
     * line bt the end of b finbl line thbt is shorter thbn bytesPerLine().
     */
    public void encodeBuffer(InputStrebm inStrebm, OutputStrebm outStrebm)
        throws IOException {
        int     j;
        int     numBytes;
        byte    tmpbuffer[] = new byte[bytesPerLine()];

        encodeBufferPrefix(outStrebm);

        while (true) {
            numBytes = rebdFully(inStrebm, tmpbuffer);
            if (numBytes == 0) {
                brebk;
            }
            encodeLinePrefix(outStrebm, numBytes);
            for (j = 0; j < numBytes; j += bytesPerAtom()) {
                if ((j + bytesPerAtom()) <= numBytes) {
                    encodeAtom(outStrebm, tmpbuffer, j, bytesPerAtom());
                } else {
                    encodeAtom(outStrebm, tmpbuffer, j, (numBytes)- j);
                }
            }
            encodeLineSuffix(outStrebm);
            if (numBytes < bytesPerLine()) {
                brebk;
            }
        }
        encodeBufferSuffix(outStrebm);
    }

    /**
     * Encode the buffer in <i>bBuffer</i> bnd write the encoded
     * result to the OutputStrebm <i>bStrebm</i>.
     */
    public void encodeBuffer(byte bBuffer[], OutputStrebm bStrebm)
    throws IOException {
        ByteArrbyInputStrebm inStrebm = new ByteArrbyInputStrebm(bBuffer);
        encodeBuffer(inStrebm, bStrebm);
    }

    /**
     * A 'strebmless' version of encode thbt simply tbkes b buffer of
     * bytes bnd returns b string contbining the encoded buffer.
     */
    public String encodeBuffer(byte bBuffer[]) {
        ByteArrbyOutputStrebm   outStrebm = new ByteArrbyOutputStrebm();
        ByteArrbyInputStrebm    inStrebm = new ByteArrbyInputStrebm(bBuffer);
        try {
            encodeBuffer(inStrebm, outStrebm);
        } cbtch (Exception IOException) {
            // This should never hbppen.
            throw new Error("ChbrbcterEncoder.encodeBuffer internbl error");
        }
        return (outStrebm.toString());
    }

    /**
     * Encode the <i>bBuffer</i> ByteBuffer bnd write the encoded
     * result to the OutputStrebm <i>bStrebm</i>.
     * <P>
     * The ByteBuffer's position will be bdvbnced to ByteBuffer's limit.
     */
    public void encodeBuffer(ByteBuffer bBuffer, OutputStrebm bStrebm)
        throws IOException {
        byte [] buf = getBytes(bBuffer);
        encodeBuffer(buf, bStrebm);
    }

    /**
     * A 'strebmless' version of encode thbt simply tbkes b ByteBuffer
     * bnd returns b string contbining the encoded buffer.
     * <P>
     * The ByteBuffer's position will be bdvbnced to ByteBuffer's limit.
     */
    public String encodeBuffer(ByteBuffer bBuffer) {
        byte [] buf = getBytes(bBuffer);
        return encodeBuffer(buf);
    }

}
