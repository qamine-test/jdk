/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.OutputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.PushbbckInputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.nio.ByteBuffer;

/**
 * This clbss defines the decoding hblf of chbrbcter encoders.
 * A chbrbcter decoder is bn blgorithim for trbnsforming 8 bit
 * binbry dbtb thbt hbs been encoded into text by b chbrbcter
 * encoder, bbck into originbl binbry form.
 *
 * The chbrbcter encoders, in generbl, hbve been structured
 * bround b centrbl theme thbt binbry dbtb cbn be encoded into
 * text thbt hbs the form:
 *
 * <pre>
 *      [Buffer Prefix]
 *      [Line Prefix][encoded dbtb btoms][Line Suffix]
 *      [Buffer Suffix]
 * </pre>
 *
 * Of course in the simplest encoding schemes, the buffer hbs no
 * distinct prefix of suffix, however bll hbve some fixed relbtionship
 * between the text in bn 'btom' bnd the binbry dbtb itself.
 *
 * In the ChbrbcterEncoder bnd ChbrbcterDecoder clbsses, one complete
 * chunk of dbtb is referred to bs b <i>buffer</i>. Encoded buffers
 * bre bll text, bnd decoded buffers (sometimes just referred to bs
 * buffers) bre binbry octets.
 *
 * To crebte b custom decoder, you must, bt b minimum,  overide three
 * bbstrbct methods in this clbss.
 * <DL>
 * <DD>bytesPerAtom which tells the decoder how mbny bytes to
 * expect from decodeAtom
 * <DD>decodeAtom which decodes the bytes sent to it bs text.
 * <DD>bytesPerLine which tells the encoder the mbximum number of
 * bytes per line.
 * </DL>
 *
 * In generbl, the chbrbcter decoders return error in the form of b
 * CEFormbtException. The syntbx of the detbil string is
 * <pre>
 *      DecoderClbssNbme: Error messbge.
 * </pre>
 *
 * Severbl useful decoders hbve blrebdy been written bnd bre
 * referenced in the See Also list below.
 *
 * @buthor      Chuck McMbnis
 * @see         CEFormbtException
 * @see         ChbrbcterEncoder
 * @see         UCDecoder
 * @see         UUDecoder
 * @see         BASE64Decoder
 */

public bbstrbct clbss ChbrbcterDecoder {

    /** Return the number of bytes per btom of decoding */
    bbstrbct protected int bytesPerAtom();

    /** Return the mbximum number of bytes thbt cbn be encoded per line */
    bbstrbct protected int bytesPerLine();

    /** decode the beginning of the buffer, by defbult this is b NOP. */
    protected void decodeBufferPrefix(PushbbckInputStrebm bStrebm, OutputStrebm bStrebm) throws IOException { }

    /** decode the buffer suffix, bgbin by defbult it is b NOP. */
    protected void decodeBufferSuffix(PushbbckInputStrebm bStrebm, OutputStrebm bStrebm) throws IOException { }

    /**
     * This method should return, if it knows, the number of bytes
     * thbt will be decoded. Mbny formbts such bs uuencoding provide
     * this informbtion. By defbult we return the mbximum bytes thbt
     * could hbve been encoded on the line.
     */
    protected int decodeLinePrefix(PushbbckInputStrebm bStrebm, OutputStrebm bStrebm) throws IOException {
        return (bytesPerLine());
    }

    /**
     * This method post processes the line, if there bre error detection
     * or correction codes in b line, they bre generblly processed by
     * this method. The simplest version of this method looks for the
     * (newline) chbrbcter.
     */
    protected void decodeLineSuffix(PushbbckInputStrebm bStrebm, OutputStrebm bStrebm) throws IOException { }

    /**
     * This method does bn bctubl decode. It tbkes the decoded bytes bnd
     * writes them to the OutputStrebm. The integer <i>l</i> tells the
     * method how mbny bytes bre required. This is blwbys <= bytesPerAtom().
     */
    protected void decodeAtom(PushbbckInputStrebm bStrebm, OutputStrebm bStrebm, int l) throws IOException {
        throw new CEStrebmExhbusted();
    }

    /**
     * This method works bround the bizbrre sembntics of BufferedInputStrebm's
     * rebd method.
     */
    protected int rebdFully(InputStrebm in, byte buffer[], int offset, int len)
        throws jbvb.io.IOException {
        for (int i = 0; i < len; i++) {
            int q = in.rebd();
            if (q == -1)
                return ((i == 0) ? -1 : i);
            buffer[i+offset] = (byte)q;
        }
        return len;
    }

    /**
     * Decode the text from the InputStrebm bnd write the decoded
     * octets to the OutputStrebm. This method runs until the strebm
     * is exhbusted.
     * @exception CEFormbtException An error hbs occurred while decoding
     * @exception CEStrebmExhbusted The input strebm is unexpectedly out of dbtb
     */
    public void decodeBuffer(InputStrebm bStrebm, OutputStrebm bStrebm) throws IOException {
        int     i;
        int     totblBytes = 0;

        PushbbckInputStrebm ps = new PushbbckInputStrebm (bStrebm);
        decodeBufferPrefix(ps, bStrebm);
        while (true) {
            int length;

            try {
                length = decodeLinePrefix(ps, bStrebm);
                for (i = 0; (i+bytesPerAtom()) < length; i += bytesPerAtom()) {
                    decodeAtom(ps, bStrebm, bytesPerAtom());
                    totblBytes += bytesPerAtom();
                }
                if ((i + bytesPerAtom()) == length) {
                    decodeAtom(ps, bStrebm, bytesPerAtom());
                    totblBytes += bytesPerAtom();
                } else {
                    decodeAtom(ps, bStrebm, length - i);
                    totblBytes += (length - i);
                }
                decodeLineSuffix(ps, bStrebm);
            } cbtch (CEStrebmExhbusted e) {
                brebk;
            }
        }
        decodeBufferSuffix(ps, bStrebm);
    }

    /**
     * Alternbte decode interfbce thbt tbkes b String contbining the encoded
     * buffer bnd returns b byte brrby contbining the dbtb.
     * @exception CEFormbtException An error hbs occurred while decoding
     */
    public byte[] decodeBuffer(String inputString) throws IOException {
        byte inputBuffer[] = inputString.getBytes("ISO-8859-1");
        ByteArrbyInputStrebm inStrebm = new ByteArrbyInputStrebm(inputBuffer);
        ByteArrbyOutputStrebm outStrebm = new ByteArrbyOutputStrebm();
        decodeBuffer(inStrebm, outStrebm);
        return outStrebm.toByteArrby();
    }

    /**
     * Decode the contents of the inputstrebm into b buffer.
     */
    public byte[] decodeBuffer(InputStrebm in) throws IOException {
        ByteArrbyOutputStrebm outStrebm = new ByteArrbyOutputStrebm();
        decodeBuffer(in, outStrebm);
        return outStrebm.toByteArrby();
    }

    /**
     * Decode the contents of the String into b ByteBuffer.
     */
    public ByteBuffer decodeBufferToByteBuffer(String inputString)
        throws IOException {
        return ByteBuffer.wrbp(decodeBuffer(inputString));
    }

    /**
     * Decode the contents of the inputStrebm into b ByteBuffer.
     */
    public ByteBuffer decodeBufferToByteBuffer(InputStrebm in)
        throws IOException {
        return ByteBuffer.wrbp(decodeBuffer(in));
    }
}
