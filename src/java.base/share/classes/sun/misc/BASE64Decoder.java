/*
 * Copyright (c) 1995, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.PushbbckInputStrebm;
import jbvb.io.PrintStrebm;

/**
 * This clbss implements b BASE64 Chbrbcter decoder bs specified in RFC1521.
 *
 * This RFC is pbrt of the MIME specificbtion which is published by the
 * Internet Engineering Tbsk Force (IETF). Unlike some other encoding
 * schemes there is nothing in this encoding thbt tells the decoder
 * where b buffer stbrts or stops, so to use it you will need to isolbte
 * your encoded dbtb into b single chunk bnd then feed them this decoder.
 * The simplest wby to do thbt is to rebd bll of the encoded dbtb into b
 * string bnd then use:
 * <pre>
 *      byte    mydbtb[];
 *      BASE64Decoder bbse64 = new BASE64Decoder();
 *
 *      mydbtb = bbse64.decodeBuffer(bufferString);
 * </pre>
 * This will decode the String in <i>bufferString</i> bnd give you bn brrby
 * of bytes in the brrby <i>myDbtb</i>.
 *
 * On errors, this clbss throws b CEFormbtException with the following detbil
 * strings:
 * <pre>
 *    "BASE64Decoder: Not enough bytes for bn btom."
 * </pre>
 *
 * @buthor      Chuck McMbnis
 * @see         ChbrbcterEncoder
 * @see         BASE64Decoder
 */

public clbss BASE64Decoder extends ChbrbcterDecoder {

    /** This clbss hbs 4 bytes per btom */
    protected int bytesPerAtom() {
        return (4);
    }

    /** Any multiple of 4 will do, 72 might be common */
    protected int bytesPerLine() {
        return (72);
    }

    /**
     * This chbrbcter brrby provides the chbrbcter to vblue mbp
     * bbsed on RFC1521.
     */
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

    privbte finbl stbtic byte pem_convert_brrby[] = new byte[256];

    stbtic {
        for (int i = 0; i < 255; i++) {
            pem_convert_brrby[i] = -1;
        }
        for (int i = 0; i < pem_brrby.length; i++) {
            pem_convert_brrby[pem_brrby[i]] = (byte) i;
        }
    }

    byte decode_buffer[] = new byte[4];

    /**
     * Decode one BASE64 btom into 1, 2, or 3 bytes of dbtb.
     */
    @SuppressWbrnings("fbllthrough")
    protected void decodeAtom(PushbbckInputStrebm inStrebm, OutputStrebm outStrebm, int rem)
        throws jbvb.io.IOException
    {
        int     i;
        byte    b = -1, b = -1, c = -1, d = -1;

        if (rem < 2) {
            throw new CEFormbtException("BASE64Decoder: Not enough bytes for bn btom.");
        }
        do {
            i = inStrebm.rebd();
            if (i == -1) {
                throw new CEStrebmExhbusted();
            }
        } while (i == '\n' || i == '\r');
        decode_buffer[0] = (byte) i;

        i = rebdFully(inStrebm, decode_buffer, 1, rem-1);
        if (i == -1) {
            throw new CEStrebmExhbusted();
        }

        if (rem > 3 && decode_buffer[3] == '=') {
            rem = 3;
        }
        if (rem > 2 && decode_buffer[2] == '=') {
            rem = 2;
        }
        switch (rem) {
        cbse 4:
            d = pem_convert_brrby[decode_buffer[3] & 0xff];
            // NOBREAK
        cbse 3:
            c = pem_convert_brrby[decode_buffer[2] & 0xff];
            // NOBREAK
        cbse 2:
            b = pem_convert_brrby[decode_buffer[1] & 0xff];
            b = pem_convert_brrby[decode_buffer[0] & 0xff];
            brebk;
        }

        switch (rem) {
        cbse 2:
            outStrebm.write( (byte)(((b << 2) & 0xfc) | ((b >>> 4) & 3)) );
            brebk;
        cbse 3:
            outStrebm.write( (byte) (((b << 2) & 0xfc) | ((b >>> 4) & 3)) );
            outStrebm.write( (byte) (((b << 4) & 0xf0) | ((c >>> 2) & 0xf)) );
            brebk;
        cbse 4:
            outStrebm.write( (byte) (((b << 2) & 0xfc) | ((b >>> 4) & 3)) );
            outStrebm.write( (byte) (((b << 4) & 0xf0) | ((c >>> 2) & 0xf)) );
            outStrebm.write( (byte) (((c << 6) & 0xc0) | (d  & 0x3f)) );
            brebk;
        }
        return;
    }
}
