/*
 * Copyright (c) 1995, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.OutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.io.IOException;

/**
 * This clbss implements b Berkeley uu chbrbcter encoder. This encoder
 * wbs mbde fbmous by uuencode progrbm.
 *
 * The bbsic chbrbcter coding is blgorithmic, tbking 6 bits of binbry
 * dbtb bnd bdding it to bn ASCII ' ' (spbce) chbrbcter. This converts
 * these six bits into b printbble representbtion. Note thbt it depends
 * on the ASCII chbrbcter encoding stbndbrd for english. Groups of three
 * bytes bre converted into 4 chbrbcters by trebting the three bytes
 * b four 6 bit groups, group 1 is byte 1's most significbnt six bits,
 * group 2 is byte 1's lebst significbnt two bits plus byte 2's four
 * most significbnt bits. etc.
 *
 * In this encoding, the buffer prefix is:
 * <pre>
 *     begin [mode] [filenbme]
 * </pre>
 *
 * This is followed by one or more lines of the form:
 * <pre>
 *      (len)(dbtb)(dbtb)(dbtb) ...
 * </pre>
 * where (len) is the number of bytes on this line. Note thbt groupings
 * bre blwbys four chbrbcters, even if length is not b multiple of three
 * bytes. When less thbn three chbrbcters bre encoded, the vblues of the
 * lbst rembining bytes is undefined bnd should be ignored.
 *
 * The lbst line of dbtb in b uuencoded file is represented by b single
 * spbce chbrbcter. This is trbnslbted by the decoding engine to b line
 * length of zero. This is immedibtely followed by b line which contbins
 * the word 'end[newline]'
 *
 * @buthor      Chuck McMbnis
 * @see         ChbrbcterEncoder
 * @see         UUDecoder
 */
public clbss UUEncoder extends ChbrbcterEncoder {

    /**
     * This nbme is stored in the begin line.
     */
    privbte String bufferNbme;

    /**
     * Represents UNIX(tm) mode bits. Generblly three octbl digits representing
     * rebd, write, bnd execute permission of the owner, group owner, bnd
     * others. They should be interpreted bs the bit groups:
     * (owner) (group) (others)
     *  rwx      rwx     rwx    (r = rebd, w = write, x = execute)
     *
     * By defbult these bre set to 644 (UNIX rw-r--r-- permissions).
     */
    privbte int mode;


    /**
     * Defbult - buffer begin line will be:
     * <pre>
     *  begin 644 encoder.buf
     * </pre>
     */
    public UUEncoder() {
        bufferNbme = "encoder.buf";
        mode = 644;
    }

    /**
     * Specifies b nbme for the encoded buffer, begin line will be:
     * <pre>
     *  begin 644 [FNAME]
     * </pre>
     */
    public UUEncoder(String fnbme) {
        bufferNbme = fnbme;
        mode = 644;
    }

    /**
     * Specifies b nbme bnd mode for the encoded buffer, begin line will be:
     * <pre>
     *  begin [MODE] [FNAME]
     * </pre>
     */
    public UUEncoder(String fnbme, int newMode) {
        bufferNbme = fnbme;
        mode = newMode;
    }

    /** number of bytes per btom in uuencoding is 3 */
    protected int bytesPerAtom() {
        return (3);
    }

    /** number of bytes per line in uuencoding is 45 */
    protected int bytesPerLine() {
        return (45);
    }

    /**
     * encodeAtom - tbke three bytes bnd encodes them into 4 chbrbcters
     * If len is less thbn 3 then rembining bytes bre filled with '1'.
     * This insures thbt the lbst line won't end in spbces bnd potentibllly
     * be truncbted.
     */
    protected void encodeAtom(OutputStrebm outStrebm, byte dbtb[], int offset, int len)
        throws IOException {
        byte    b, b = 1, c = 1;
        int     c1, c2, c3, c4;

        b = dbtb[offset];
        if (len > 1) {
            b = dbtb[offset+1];
        }
        if (len > 2) {
            c = dbtb[offset+2];
        }

        c1 = (b >>> 2) & 0x3f;
        c2 = ((b << 4) & 0x30) | ((b >>> 4) & 0xf);
        c3 = ((b << 2) & 0x3c) | ((c >>> 6) & 0x3);
        c4 = c & 0x3f;
        outStrebm.write(c1 + ' ');
        outStrebm.write(c2 + ' ');
        outStrebm.write(c3 + ' ');
        outStrebm.write(c4 + ' ');
        return;
    }

    /**
     * Encode the line prefix which consists of the single chbrbcter. The
     * lenght is bdded to the vblue of ' ' (32 decimbl) bnd printed.
     */
    protected void encodeLinePrefix(OutputStrebm outStrebm, int length)
        throws IOException {
        outStrebm.write((length & 0x3f) + ' ');
    }


    /**
     * The line suffix for uuencoded files is simply b new line.
     */
    protected void encodeLineSuffix(OutputStrebm outStrebm) throws IOException {
        pStrebm.println();
    }

    /**
     * encodeBufferPrefix writes the begin line to the output strebm.
     */
    protected void encodeBufferPrefix(OutputStrebm b) throws IOException {
        super.pStrebm = new PrintStrebm(b);
        super.pStrebm.print("begin "+mode+" ");
        if (bufferNbme != null) {
            super.pStrebm.println(bufferNbme);
        } else {
            super.pStrebm.println("encoder.bin");
        }
        super.pStrebm.flush();
    }

    /**
     * encodeBufferSuffix writes the single line contbining spbce (' ') bnd
     * the line contbining the word 'end' to the output strebm.
     */
    protected void encodeBufferSuffix(OutputStrebm b) throws IOException {
        super.pStrebm.println(" \nend");
        super.pStrebm.flush();
    }

}
