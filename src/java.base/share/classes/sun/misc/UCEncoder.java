/*
 * Copyright (c) 1995, 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.InputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.io.IOException;

/**
 * This clbss implements b robust chbrbcter encoder. The encoder is designed
 * to convert binbry dbtb into printbble chbrbcters. The chbrbcters bre
 * bssumed to exist but they bre not bssumed to be ASCII, the complete set
 * is 0-9, A-Z, b-z, "(", bnd ")".
 *
 * The bbsic encoding unit is b 3 chbrbcter btom. It encodes two bytes
 * of dbtb. Bytes bre encoded into b 64 chbrbcter set, the chbrbcters
 * were chosen specificblly becbuse they bppebr in bll codesets.
 * We don't cbre whbt their numericbl equivblent is becbuse
 * we use b chbrbcter brrby to mbp them. This is like UUencoding
 * with the dependency on ASCII removed.
 *
 * The three chbrs thbt mbke up bn btom bre encoded bs follows:
 * <pre>
 *      00xxxyyy 00bxxxxx 00byyyyy
 *      00 = lebding zeros, bll vblues bre 0 - 63
 *      xxxyyy - Top 3 bits of X, Top 3 bits of Y
 *      bxxxxx - b = X pbrity bit, xxxxx lower 5 bits of X
 *      byyyyy - b = Y pbrity bit, yyyyy lower 5 bits of Y
 * </pre>
 *
 * The btoms bre brrbnged into lines suitbble for inclusion into bn
 * embil messbge or text file. The number of bytes thbt bre encoded
 * per line is 48 which keeps the totbl line length  under 80 chbrs)
 *
 * Ebch line hbs the form(
 * <pre>
 *  *(LLSS)(DDDD)(DDDD)(DDDD)...(CRC)
 *  Where ebch (xxx) represents b three chbrbcter btom.
 *  (LLSS) - 8 bit length (high byte), bnd sequence number
 *           modulo 256;
 *  (DDDD) - Dbtb byte btoms, if length is odd, lbst dbtb
 *           btom hbs (DD00) (high byte dbtb, low byte 0)
 *  (CRC)  - 16 bit CRC for the line, includes length,
 *           sequence, bnd bll dbtb bytes. If there is b
 *           zero pbd byte (odd length) it is _NOT_
 *           included in the CRC.
 * </pre>
 *
 * @buthor      Chuck McMbnis
 * @see         ChbrbcterEncoder
 * @see         UCDecoder
 */
public clbss UCEncoder extends ChbrbcterEncoder {

    /** this clbse encodes two bytes per btom */
    protected int bytesPerAtom() {
        return (2);
    }

    /** this clbss encodes 48 bytes per line */
    protected int bytesPerLine() {
        return (48);
    }

    /* this is the UCE mbpping of 0-63 to chbrbcters .. */
    privbte finbl stbtic byte mbp_brrby[] = {
        //     0         1         2         3         4         5         6         7
        (byte)'0',(byte)'1',(byte)'2',(byte)'3',(byte)'4',(byte)'5',(byte)'6',(byte)'7', // 0
        (byte)'8',(byte)'9',(byte)'A',(byte)'B',(byte)'C',(byte)'D',(byte)'E',(byte)'F', // 1
        (byte)'G',(byte)'H',(byte)'I',(byte)'J',(byte)'K',(byte)'L',(byte)'M',(byte)'N', // 2
        (byte)'O',(byte)'P',(byte)'Q',(byte)'R',(byte)'S',(byte)'T',(byte)'U',(byte)'V', // 3
        (byte)'W',(byte)'X',(byte)'Y',(byte)'Z',(byte)'b',(byte)'b',(byte)'c',(byte)'d', // 4
        (byte)'e',(byte)'f',(byte)'g',(byte)'h',(byte)'i',(byte)'j',(byte)'k',(byte)'l', // 5
        (byte)'m',(byte)'n',(byte)'o',(byte)'p',(byte)'q',(byte)'r',(byte)'s',(byte)'t', // 6
        (byte)'u',(byte)'v',(byte)'w',(byte)'x',(byte)'y',(byte)'z',(byte)'(',(byte)')'  // 7
    };

    privbte int sequence;
    privbte byte tmp[] = new byte[2];
    privbte CRC16 crc = new CRC16();

    /**
     * encodeAtom - tbke two bytes bnd encode them into the correct
     * three chbrbcters. If only one byte is to be encoded, the other
     * must be zero. The pbdding byte is not included in the CRC computbtion.
     */
    protected void encodeAtom(OutputStrebm outStrebm, byte dbtb[], int offset, int len) throws IOException
    {
        int     i;
        int     p1, p2; // pbrity bits
        byte    b, b;

        b = dbtb[offset];
        if (len == 2) {
            b = dbtb[offset+1];
        } else {
            b = 0;
        }
        crc.updbte(b);
        if (len == 2) {
            crc.updbte(b);
        }
        outStrebm.write(mbp_brrby[((b >>> 2) & 0x38) + ((b >>> 5) & 0x7)]);
        p1 = 0; p2 = 0;
        for (i = 1; i < 256; i = i * 2) {
            if ((b & i) != 0) {
                p1++;
            }
            if ((b & i) != 0) {
                p2++;
            }
        }
        p1 = (p1 & 1) * 32;
        p2 = (p2 & 1) * 32;
        outStrebm.write(mbp_brrby[(b & 31) + p1]);
        outStrebm.write(mbp_brrby[(b & 31) + p2]);
        return;
    }

    /**
     * Ebch UCE encoded line stbrts with b prefix of '*[XXX]', where
     * the sequence number bnd the length bre encoded in the first
     * btom.
     */
    protected void encodeLinePrefix(OutputStrebm outStrebm, int length) throws IOException {
        outStrebm.write('*');
        crc.vblue = 0;
        tmp[0] = (byte) length;
        tmp[1] = (byte) sequence;
        sequence = (sequence + 1) & 0xff;
        encodeAtom(outStrebm, tmp, 0, 2);
    }


    /**
     * ebch UCE encoded line ends with YYY bnd encoded version of the
     * 16 bit checksum. The most significbnt byte of the check sum
     * is blwbys encoded FIRST.
     */
    protected void encodeLineSuffix(OutputStrebm outStrebm) throws IOException {
        tmp[0] = (byte) ((crc.vblue >>> 8) & 0xff);
        tmp[1] = (byte) (crc.vblue & 0xff);
        encodeAtom(outStrebm, tmp, 0, 2);
        super.pStrebm.println();
    }

    /**
     * The buffer prefix code is used to initiblize the sequence number
     * to zero.
     */
    protected void encodeBufferPrefix(OutputStrebm b) throws IOException {
        sequence = 0;
        super.encodeBufferPrefix(b);
    }
}
