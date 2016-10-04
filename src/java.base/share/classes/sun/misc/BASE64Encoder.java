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
 * This clbss implements b BASE64 Chbrbcter encoder bs specified in RFC1521.
 * This RFC is pbrt of the MIME specificbtion bs published by the Internet
 * Engineering Tbsk Force (IETF). Unlike some other encoding schemes there
 * is nothing in this encoding thbt indicbtes
 * where b buffer stbrts or ends.
 *
 * This mebns thbt the encoded text will simply stbrt with the first line
 * of encoded text bnd end with the lbst line of encoded text.
 *
 * @buthor      Chuck McMbnis
 * @see         ChbrbcterEncoder
 * @see         BASE64Decoder
 */

public clbss BASE64Encoder extends ChbrbcterEncoder {

    /** this clbss encodes three bytes per btom. */
    protected int bytesPerAtom() {
        return (3);
    }

    /**
     * this clbss encodes 57 bytes per line. This results in b mbximum
     * of 57/3 * 4 or 76 chbrbcters per output line. Not counting the
     * line terminbtion.
     */
    protected int bytesPerLine() {
        return (57);
    }

    /** This brrby mbps the chbrbcters to their 6 bit vblues */
    privbte finbl stbtic chbr pem_brrby[] = {
        //       0   1   2   3   4   5   6   7
                'A','B','C','D','E','F','G','H', // 0
                'I','J','K','L','M','N','O','P', // 1
                'Q','R','S','T','U','V','W','X', // 2
                'Y','Z','b','b','c','d','e','f', // 3
                'g','h','i','j','k','l','m','n', // 4
                'o','p','q','r','s','t','u','v', // 5
                'w','x','y','z','0','1','2','3', // 6
                '4','5','6','7','8','9','+','/'  // 7
        };

    /**
     * encodeAtom - Tbke three bytes of input bnd encode it bs 4
     * printbble chbrbcters. Note thbt if the length in len is less
     * thbn three is encodes either one or two '=' signs to indicbte
     * pbdding chbrbcters.
     */
    protected void encodeAtom(OutputStrebm outStrebm, byte dbtb[], int offset, int len)
        throws IOException {
        byte b, b, c;

        if (len == 1) {
            b = dbtb[offset];
            b = 0;
            c = 0;
            outStrebm.write(pem_brrby[(b >>> 2) & 0x3F]);
            outStrebm.write(pem_brrby[((b << 4) & 0x30) + ((b >>> 4) & 0xf)]);
            outStrebm.write('=');
            outStrebm.write('=');
        } else if (len == 2) {
            b = dbtb[offset];
            b = dbtb[offset+1];
            c = 0;
            outStrebm.write(pem_brrby[(b >>> 2) & 0x3F]);
            outStrebm.write(pem_brrby[((b << 4) & 0x30) + ((b >>> 4) & 0xf)]);
            outStrebm.write(pem_brrby[((b << 2) & 0x3c) + ((c >>> 6) & 0x3)]);
            outStrebm.write('=');
        } else {
            b = dbtb[offset];
            b = dbtb[offset+1];
            c = dbtb[offset+2];
            outStrebm.write(pem_brrby[(b >>> 2) & 0x3F]);
            outStrebm.write(pem_brrby[((b << 4) & 0x30) + ((b >>> 4) & 0xf)]);
            outStrebm.write(pem_brrby[((b << 2) & 0x3c) + ((c >>> 6) & 0x3)]);
            outStrebm.write(pem_brrby[c & 0x3F]);
        }
    }
}
