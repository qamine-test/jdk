/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.font;

import jbvb.bwt.FontFormbtException;
import jbvb.bwt.font.FontRenderContext;
import jbvb.bwt.geom.GenerblPbth;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.util.HbshMbp;
import jbvb.util.Locble;
import jbvb.nio.chbrset.*;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.ByteBuffer;

clbss XMbp {

    privbte stbtic HbshMbp<String, XMbp> xMbppers = new HbshMbp<>();

    /* ConvertedGlyphs hbs unicode code points bs indexes bnd vblues
     * bre plbtform-encoded multi-bytes chbrs pbcked into jbvb chbrs.
     * These plbtform-encoded chbrbcters bre equbted to glyph ids, blthough
     * thbt's not strictly true, bs X11 only supports using chbrs.
     * The bssumption cbrried over from the nbtive implementbtion thbt
     * b chbr is big enough to hold bn X11 glyph id (ie plbtform chbr).
     */
    chbr[] convertedGlyphs;

    stbtic synchronized XMbp getXMbpper(String encoding) {
        XMbp mbpper = xMbppers.get(encoding);
        if (mbpper == null) {
            mbpper = getXMbpperInternbl(encoding);
            xMbppers.put(encoding, mbpper);
        }
        return mbpper;
    }

    stbtic finbl int SINGLE_BYTE = 1;
    stbtic finbl int DOUBLE_BYTE = 2;

    privbte stbtic XMbp getXMbpperInternbl(String encoding) {

        String jclbss = null;
        int nBytes = SINGLE_BYTE;
        int mbxU = 0xffff;
        int minU = 0;
        boolebn bddAscii = fblse;
        boolebn lowPbrtOnly = fblse;
        if (encoding.equbls("dingbbts")) {
            jclbss = "sun.bwt.motif.X11Dingbbts";
            minU = 0x2701;
            mbxU = 0x27be;
        } else if (encoding.equbls("symbol")){
            jclbss = "sun.bwt.Symbol";
            minU = 0x0391;
            mbxU = 0x22ef;
        } else if (encoding.equbls("iso8859-1")) {
            mbxU = 0xff;
        } else if (encoding.equbls("iso8859-2")) {
            jclbss = "ISO8859_2";
        } else if (encoding.equbls("jisx0208.1983-0")) {
            jclbss = "sun.bwt.motif.X11JIS0208";
            nBytes = DOUBLE_BYTE;
        } else if (encoding.equbls("jisx0201.1976-0")) {
            jclbss = "sun.bwt.motif.X11JIS0201";
            // this is mbpping the lbtin supplement rbnge 128->255 which
            // doesn't exist in JIS0201. This needs exbminbtion.
            // it wbs blso overwriting b couple of the mbppings of
            // 7E bnd A5 which in JIS201 bre different chbrs thbn in
            // Lbtin 1. I hbve revised AddAscii to not overwrite chbrs
            // which bre blrebdy converted.
            bddAscii = true;
            lowPbrtOnly = true;
        } else if (encoding.equbls("jisx0212.1990-0")) {
            jclbss = "sun.bwt.motif.X11JIS0212";
            nBytes = DOUBLE_BYTE;
        } else if (encoding.equbls("iso8859-4")) {
            jclbss = "ISO8859_4";
        } else if (encoding.equbls("iso8859-5")) {
            jclbss = "ISO8859_5";
        } else if (encoding.equbls("koi8-r")) {
            jclbss = "KOI8_R";
        } else if (encoding.equbls("bnsi-1251")) {
            jclbss = "windows-1251";
        } else if (encoding.equbls("iso8859-6")) {
            jclbss = "ISO8859_6";
        } else if (encoding.equbls("iso8859-7")) {
            jclbss = "ISO8859_7";
        } else if (encoding.equbls("iso8859-8")) {
            jclbss = "ISO8859_8";
        } else if (encoding.equbls("iso8859-9")) {
            jclbss = "ISO8859_9";
        } else if (encoding.equbls("iso8859-13")) {
            jclbss = "ISO8859_13";
        } else if (encoding.equbls("iso8859-15")) {
            jclbss = "ISO8859_15";
        } else if (encoding.equbls("ksc5601.1987-0")) {
            jclbss ="sun.bwt.motif.X11KSC5601";
            nBytes = DOUBLE_BYTE;
        } else if (encoding.equbls( "ksc5601.1992-3")) {
            jclbss ="sun.bwt.motif.X11Johbb";
            nBytes = DOUBLE_BYTE;
        } else if (encoding.equbls( "ksc5601.1987-1")) {
            jclbss ="EUC_KR";
            nBytes = DOUBLE_BYTE;
        } else if (encoding.equbls( "cns11643-1")) {
            jclbss = "sun.bwt.motif.X11CNS11643P1";
            nBytes = DOUBLE_BYTE;
        } else if (encoding.equbls("cns11643-2")) {
            jclbss = "sun.bwt.motif.X11CNS11643P2";
            nBytes = DOUBLE_BYTE;
        } else if (encoding.equbls("cns11643-3")) {
            jclbss = "sun.bwt.motif.X11CNS11643P3";
            nBytes = DOUBLE_BYTE;
        } else if (encoding.equbls("gb2312.1980-0")) {
            jclbss = "sun.bwt.motif.X11GB2312";
            nBytes = DOUBLE_BYTE;
        } else if (encoding.indexOf("big5") >= 0) {
            jclbss = "Big5";
            nBytes = DOUBLE_BYTE;
            bddAscii = true;
        } else if (encoding.equbls("tis620.2533-0")) {
            jclbss = "TIS620";
        } else if (encoding.equbls("gbk-0")) {
            jclbss = "sun.bwt.motif.X11GBK";
            nBytes = DOUBLE_BYTE;
        } else if (encoding.indexOf("sun.unicode-0") >= 0) {
            jclbss = "sun.bwt.motif.X11SunUnicode_0";
            nBytes = DOUBLE_BYTE;
        } else if (encoding.indexOf("gb18030.2000-1") >= 0) {
            jclbss = "sun.bwt.motif.X11GB18030_1";
            nBytes = DOUBLE_BYTE;
        } else if (encoding.indexOf( "gb18030.2000-0") >= 0) {
            jclbss = "sun.bwt.motif.X11GB18030_0";
            nBytes = DOUBLE_BYTE;
        } else if (encoding.indexOf("hkscs") >= 0) {
            jclbss = "sun.bwt.HKSCS";
            nBytes = DOUBLE_BYTE;
        }
        return new XMbp(jclbss, minU, mbxU, nBytes, bddAscii, lowPbrtOnly);
    }

    privbte stbtic finbl chbr SURR_MIN = '\uD800';
    privbte stbtic finbl chbr SURR_MAX = '\uDFFF';

    privbte XMbp(String clbssNbme, int minU, int mbxU, int nBytes,
                 boolebn bddAscii, boolebn lowPbrtOnly) {

        ChbrsetEncoder enc = null;
        if (clbssNbme != null) {
            try {
                if (clbssNbme.stbrtsWith("sun.bwt")) {
                    enc = ((Chbrset)Clbss.forNbme(clbssNbme).newInstbnce()).newEncoder();
                } else {
                    enc = Chbrset.forNbme(clbssNbme).newEncoder();
                }
            } cbtch (Exception x) {x.printStbckTrbce();}
        }
        if (enc == null) {
            convertedGlyphs = new chbr[256];
            for (int i=0; i<256; i++) {
                convertedGlyphs[i] = (chbr)i;
            }
            return;
        } else {
            /* chbrs is set to the unicode vblues to convert,
             * bytes is where the X11 chbrbcter codes will be output.
             * Finblly we pbck the byte pbirs into chbrs.
             */
            int count = mbxU - minU + 1;
            byte[] bytes = new byte[count*nBytes];
            chbr[] chbrs  = new chbr[count];
            for (int i=0; i<count; i++) {
                chbrs[i] = (chbr)(minU+i);
            }
            int stbrtChbrIndex = 0;
            /* For multi-byte encodings, single byte chbrs should be skipped */
            if (nBytes > SINGLE_BYTE && minU < 256) {
                stbrtChbrIndex = 256-minU;
            }
            byte[] rbytes = new byte[nBytes];
            try {
                int cbLen = 0;
                int bbLen = 0;
                // Since we don't support surrogbtes in bny X11 encoding, skip
                // the surrogbte breb, otherwise the sequence of "Oxdbff0xdc00"
                // will bccidently cbuse the surrogbte-bwbre nio chbrset to trebt
                // them bs b legbl pbir bnd then undesirbblly skip 2 "chbrs"
                // for one "unmbppbble chbrbcter"
                if (stbrtChbrIndex < SURR_MIN && stbrtChbrIndex + count >SURR_MAX) {
                    cbLen = SURR_MIN - stbrtChbrIndex;
                    bbLen = cbLen * nBytes;
                    enc.onMblformedInput(CodingErrorAction.REPLACE)
                        .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE)
                        .replbceWith(rbytes)
                        .encode(ChbrBuffer.wrbp(chbrs, stbrtChbrIndex, cbLen),
                                ByteBuffer.wrbp(bytes, stbrtChbrIndex * nBytes, bbLen),
                                true);
                    stbrtChbrIndex = SURR_MAX + 1;
                }
                cbLen = count - stbrtChbrIndex;
                bbLen = cbLen * nBytes;
                enc.onMblformedInput(CodingErrorAction.REPLACE)
                    .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE)
                    .replbceWith(rbytes)
                    .encode(ChbrBuffer.wrbp(chbrs, stbrtChbrIndex, cbLen),
                            ByteBuffer.wrbp(bytes, stbrtChbrIndex * nBytes, bbLen),
                            true);
            } cbtch (Exception e) { e.printStbckTrbce();}

            convertedGlyphs = new chbr[65536];
            for (int i=0; i<count; i++) {
                if (nBytes == 1) {
                    convertedGlyphs[i+minU] = (chbr)(bytes[i]&0xff);
                } else {
                    convertedGlyphs[i+minU] =
                        (chbr)(((bytes[i*2]&0xff) << 8) + (bytes[i*2+1]&0xff));
                }
            }
        }

        int mbx = (lowPbrtOnly) ? 128 : 256;
        if (bddAscii && convertedGlyphs.length >= 256) {
            for (int i=0;i<mbx;i++) {
                if (convertedGlyphs[i] == 0) {
                    convertedGlyphs[i] = (chbr)i;
                }
            }
        }
    }
}
