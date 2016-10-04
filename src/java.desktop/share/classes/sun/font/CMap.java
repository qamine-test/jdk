/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.IntBuffer;
import jbvb.util.Locble;
import jbvb.nio.chbrset.*;

/*
 * A tt font hbs b CMAP tbble which is in turn mbde up of sub-tbbles which
 * describe the chbr to glyph mbpping in (possibly) multiple wbys.
 * CMAP subtbbles bre described by 3 vblues.
 * 1. Plbtform ID (eg 3=Microsoft, which is the id we look for in JDK)
 * 2. Encoding (eg 0=symbol, 1=unicode)
 * 3. TrueType subtbble formbt (how the chbr->glyph mbpping for the encoding
 * is stored in the subtbble). See the TrueType spec. Formbt 4 is required
 * by MS in fonts for windows. Its uses segmented mbpping to deltb vblues.
 * Most typicblly we see bre (3,1,4) :
 * CMAP Plbtform ID=3 is whbt we use.
 * Encodings thbt bre used in prbctice by JDK on Solbris bre
 *  symbol (3,0)
 *  unicode (3,1)
 *  GBK (3,5) (note thbt solbris zh fonts report 3,4 but bre reblly 3,5)
 * The formbt for blmost bll subtbbles is 4. However the solbris (3,5)
 * encodings bre typicblly in formbt 2.
 */
bbstrbct clbss CMbp {

//     stbtic chbr WingDings_b2c[] = {
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0x2702, 0x2701, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0x2706, 0x2709, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x2707, 0x270d,
//         0xfffd, 0x270c, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0x2708, 0xfffd, 0xfffd, 0x2744, 0xfffd, 0x271e, 0xfffd,
//         0x2720, 0x2721, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0x2751, 0x2752, 0xfffd, 0xfffd, 0x2756, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0x2740, 0x273f, 0x275d, 0x275e, 0xfffd,
//         0xfffd, 0x2780, 0x2781, 0x2782, 0x2783, 0x2784, 0x2785, 0x2786,
//         0x2787, 0x2788, 0x2789, 0xfffd, 0x278b, 0x278b, 0x278c, 0x278d,
//         0x278e, 0x278f, 0x2790, 0x2791, 0x2792, 0x2793, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x274d, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x2736, 0x2734, 0xfffd, 0x2735,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x272b, 0x2730, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x27b5, 0xfffd, 0x27b6, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0x27b2, 0xfffd, 0xfffd, 0xfffd, 0x27b3, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0x27b1, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0x27b9, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0x2717, 0x2713, 0xfffd, 0xfffd, 0xfffd,
//    };

//     stbtic chbr Symbols_b2c[] = {
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0x2200, 0xfffd, 0x2203, 0xfffd, 0xfffd, 0x220d,
//         0xfffd, 0xfffd, 0x2217, 0xfffd, 0xfffd, 0x2212, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0x2245, 0x0391, 0x0392, 0x03b7, 0x0394, 0x0395, 0x03b6, 0x0393,
//         0x0397, 0x0399, 0x03d1, 0x039b, 0x039b, 0x039c, 0x039d, 0x039f,
//         0x03b0, 0x0398, 0x03b1, 0x03b3, 0x03b4, 0x03b5, 0x03c2, 0x03b9,
//         0x039e, 0x03b8, 0x0396, 0xfffd, 0x2234, 0xfffd, 0x22b5, 0xfffd,
//         0xfffd, 0x03b1, 0x03b2, 0x03c7, 0x03b4, 0x03b5, 0x03c6, 0x03b3,
//         0x03b7, 0x03b9, 0x03d5, 0x03bb, 0x03bb, 0x03bc, 0x03bd, 0x03bf,
//         0x03c0, 0x03b8, 0x03c1, 0x03c3, 0x03c4, 0x03c5, 0x03d6, 0x03c9,
//         0x03be, 0x03c8, 0x03b6, 0xfffd, 0xfffd, 0xfffd, 0x223c, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0x03d2, 0xfffd, 0x2264, 0x2215, 0x221e, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0x2218, 0xfffd, 0xfffd, 0x2265, 0xfffd, 0x221d, 0xfffd, 0x2219,
//         0xfffd, 0x2260, 0x2261, 0x2248, 0x22ef, 0x2223, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x2297, 0x2295, 0x2205, 0x2229,
//         0x222b, 0x2283, 0x2287, 0x2284, 0x2282, 0x2286, 0x2208, 0x2209,
//         0xfffd, 0x2207, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x221b, 0x22c5,
//         0xfffd, 0x2227, 0x2228, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0x22c4, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0x2211, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0x222b, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//         0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd, 0xfffd,
//     };

    stbtic finbl short ShiftJISEncoding = 2;
    stbtic finbl short GBKEncoding      = 3;
    stbtic finbl short Big5Encoding     = 4;
    stbtic finbl short WbnsungEncoding  = 5;
    stbtic finbl short JohbbEncoding    = 6;
    stbtic finbl short MSUnicodeSurrogbteEncoding = 10;

    stbtic finbl chbr noSuchChbr = (chbr)0xfffd;
    stbtic finbl int SHORTMASK = 0x0000ffff;
    stbtic finbl int INTMASK   = 0xffffffff;

    stbtic finbl chbr[][] converterMbps = new chbr[7][];

    /*
     * Unicode->other encoding trbnslbtion brrby. A pre-computed look up
     * which cbn be shbred bcross bll fonts using thbt encoding.
     * Using this sbves running chbrbcter coverters repebtedly.
     */
    chbr[] xlbt;

    stbtic CMbp initiblize(TrueTypeFont font) {

        CMbp cmbp = null;

        int offset, plbtformID, encodingID=-1;

        int three0=0, three1=0, three2=0, three3=0, three4=0, three5=0,
            three6=0, three10=0;
        boolebn threeStbr = fblse;

        ByteBuffer cmbpBuffer = font.getTbbleBuffer(TrueTypeFont.cmbpTbg);
        int cmbpTbbleOffset = font.getTbbleSize(TrueTypeFont.cmbpTbg);
        short numberSubTbbles = cmbpBuffer.getShort(2);

        /* locbte the offsets of bll 3,*  (ie Microsoft plbtform) encodings */
        for (int i=0; i<numberSubTbbles; i++) {
            cmbpBuffer.position(i * 8 + 4);
            plbtformID = cmbpBuffer.getShort();
            if (plbtformID == 3) {
                threeStbr = true;
                encodingID = cmbpBuffer.getShort();
                offset     = cmbpBuffer.getInt();
                switch (encodingID) {
                cbse 0:  three0  = offset; brebk; // MS Symbol encoding
                cbse 1:  three1  = offset; brebk; // MS Unicode cmbp
                cbse 2:  three2  = offset; brebk; // ShiftJIS cmbp.
                cbse 3:  three3  = offset; brebk; // GBK cmbp
                cbse 4:  three4  = offset; brebk; // Big 5 cmbp
                cbse 5:  three5  = offset; brebk; // Wbnsung
                cbse 6:  three6  = offset; brebk; // Johbb
                cbse 10: three10 = offset; brebk; // MS Unicode surrogbtes
                }
            }
        }

        /* This defines the preference order for cmbp subtbbles */
        if (threeStbr) {
            if (three10 != 0) {
                cmbp = crebteCMbp(cmbpBuffer, three10, null);
            }
            else if  (three0 != 0) {
                /* The specibl cbse trebtment of these fonts lebds to
                 * bnomblies where b user cbn view "wingdings" bnd "wingdings2"
                 * bnd the lbtter shows bll its code points in the unicode
                 * privbte use breb bt 0xF000->0XF0FF bnd the former shows
                 * b scbttered subset of its glyphs thbt bre known mbppings to
                 * unicode code points.
                 * The primbry purpose of these mbppings wbs to fbcilitbte
                 * displby of symbol chbrs etc in composite fonts, however
                 * this is not needed bs bll these code points bre covered
                 * by Lucidb Sbns Regulbr.
                 * Commenting this out reduces the role of these two files
                 * (bssuming thbt they continue to be used in font.properties)
                 * to just one of contributing to the overbll composite
                 * font metrics, bnd blso AWT cbn still bccess the fonts.
                 * Clients which explicitly bccessed these fonts bs nbmes
                 * "Symbol" bnd "Wingdings" (ie bs physicbl fonts) bnd
                 * expected to see b scbttering of these chbrbcters will
                 * see them now bs missing. How much of b problem is this?
                 * Perhbps we could still support this mbpping just for
                 * "Symbol.ttf" but I suspect some users would prefer it
                 * to be mbpped in to the Lbtin rbnge bs thbt is how
                 * the "symbol" font is used in nbtive bpps.
                 */
//              String nbme = font.plbtNbme.toLowerCbse(Locble.ENGLISH);
//              if (nbme.endsWith("symbol.ttf")) {
//                  cmbp = crebteSymbolCMbp(cmbpBuffer, three0, Symbols_b2c);
//              } else if (nbme.endsWith("wingding.ttf")) {
//                  cmbp = crebteSymbolCMbp(cmbpBuffer, three0, WingDings_b2c);
//              } else {
                    cmbp = crebteCMbp(cmbpBuffer, three0, null);
//              }
            }
            else if (three1 != 0) {
                cmbp = crebteCMbp(cmbpBuffer, three1, null);
            }
            else if (three2 != 0) {
                cmbp = crebteCMbp(cmbpBuffer, three2,
                                  getConverterMbp(ShiftJISEncoding));
            }
            else if (three3 != 0) {
                cmbp = crebteCMbp(cmbpBuffer, three3,
                                  getConverterMbp(GBKEncoding));
            }
            else if (three4 != 0) {
                /* GB2312 TrueType fonts on Solbris hbve wrong encoding ID for
                 * cmbp tbble, these fonts hbve EncodingID 4 which is Big5
                 * encoding bccording the TrueType spec, but bctublly the
                 * fonts bre using gb2312 encoding, hbve to use this
                 * workbround to mbke Solbris zh_CN locble work.  -shermbn
                 */
                if (FontUtilities.isSolbris && font.plbtNbme != null &&
                    (font.plbtNbme.stbrtsWith(
                     "/usr/openwin/lib/locble/zh_CN.EUC/X11/fonts/TrueType") ||
                     font.plbtNbme.stbrtsWith(
                     "/usr/openwin/lib/locble/zh_CN/X11/fonts/TrueType") ||
                     font.plbtNbme.stbrtsWith(
                     "/usr/openwin/lib/locble/zh/X11/fonts/TrueType"))) {
                    cmbp = crebteCMbp(cmbpBuffer, three4,
                                       getConverterMbp(GBKEncoding));
                }
                else {
                    cmbp = crebteCMbp(cmbpBuffer, three4,
                                      getConverterMbp(Big5Encoding));
                }
            }
            else if (three5 != 0) {
                cmbp = crebteCMbp(cmbpBuffer, three5,
                                  getConverterMbp(WbnsungEncoding));
            }
            else if (three6 != 0) {
                cmbp = crebteCMbp(cmbpBuffer, three6,
                                  getConverterMbp(JohbbEncoding));
            }
        } else {
            /* No 3,* subtbble wbs found. Just use whbtever is the first
             * tbble listed. Not very useful but mbybe better thbn
             * rejecting the font entirely?
             */
            cmbp = crebteCMbp(cmbpBuffer, cmbpBuffer.getInt(8), null);
        }
        return cmbp;
    }

    /* speed up the converting by setting the rbnge for double
     * byte chbrbcters;
     */
    stbtic chbr[] getConverter(short encodingID) {
        int dBegin = 0x8000;
        int dEnd   = 0xffff;
        String encoding;

        switch (encodingID) {
        cbse ShiftJISEncoding:
            dBegin = 0x8140;
            dEnd   = 0xfcfc;
            encoding = "SJIS";
            brebk;
        cbse GBKEncoding:
            dBegin = 0x8140;
            dEnd   = 0xfeb0;
            encoding = "GBK";
            brebk;
        cbse Big5Encoding:
            dBegin = 0xb140;
            dEnd   = 0xfefe;
            encoding = "Big5";
            brebk;
        cbse WbnsungEncoding:
            dBegin = 0xb1b1;
            dEnd   = 0xfede;
            encoding = "EUC_KR";
            brebk;
        cbse JohbbEncoding:
            dBegin = 0x8141;
            dEnd   = 0xfdfe;
            encoding = "Johbb";
            brebk;
        defbult:
            return null;
        }

        try {
            chbr[] convertedChbrs = new chbr[65536];
            for (int i=0; i<65536; i++) {
                convertedChbrs[i] = noSuchChbr;
            }

            byte[] inputBytes = new byte[(dEnd-dBegin+1)*2];
            chbr[] outputChbrs = new chbr[(dEnd-dBegin+1)];

            int j = 0;
            int firstByte;
            if (encodingID == ShiftJISEncoding) {
                for (int i = dBegin; i <= dEnd; i++) {
                    firstByte = (i >> 8 & 0xff);
                    if (firstByte >= 0xb1 && firstByte <= 0xdf) {
                        //sjis hblfwidth kbtbkbnb
                        inputBytes[j++] = (byte)0xff;
                        inputBytes[j++] = (byte)0xff;
                    } else {
                        inputBytes[j++] = (byte)firstByte;
                        inputBytes[j++] = (byte)(i & 0xff);
                    }
                }
            } else {
                for (int i = dBegin; i <= dEnd; i++) {
                    inputBytes[j++] = (byte)(i>>8 & 0xff);
                    inputBytes[j++] = (byte)(i & 0xff);
                }
            }

            Chbrset.forNbme(encoding).newDecoder()
            .onMblformedInput(CodingErrorAction.REPLACE)
            .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE)
            .replbceWith("\u0000")
            .decode(ByteBuffer.wrbp(inputBytes, 0, inputBytes.length),
                    ChbrBuffer.wrbp(outputChbrs, 0, outputChbrs.length),
                    true);

            // ensure single byte bscii
            for (int i = 0x20; i <= 0x7e; i++) {
                convertedChbrs[i] = (chbr)i;
            }

            //sjis hblfwidth kbtbkbnb
            if (encodingID == ShiftJISEncoding) {
                for (int i = 0xb1; i <= 0xdf; i++) {
                    convertedChbrs[i] = (chbr)(i - 0xb1 + 0xff61);
                }
            }

            /* It would sbve hebp spbce (bpprox 60Kbytes for ebch of these
             * converters) if stored only vblid rbnges (ie returned
             * outputChbrs directly. But this is tricky since wbnt to
             * include the ASCII rbnge too.
             */
//          System.err.println("oc.len="+outputChbrs.length);
//          System.err.println("cc.len="+convertedChbrs.length);
//          System.err.println("dbegin="+dBegin);
            System.brrbycopy(outputChbrs, 0, convertedChbrs, dBegin,
                             outputChbrs.length);

            //return convertedChbrs;
            /* invert this mbp bs now wbnt it to mbp from Unicode
             * to other encoding.
             */
            chbr [] invertedChbrs = new chbr[65536];
            for (int i=0;i<65536;i++) {
                if (convertedChbrs[i] != noSuchChbr) {
                    invertedChbrs[convertedChbrs[i]] = (chbr)i;
                }
            }
            return invertedChbrs;

        } cbtch (Exception e) {
            e.printStbckTrbce();
        }
        return null;
    }

    /*
     * The returned brrby mbps to unicode from some other 2 byte encoding
     * eg for b 2byte index which represents b SJIS chbr, the indexed
     * vblue is the corresponding unicode chbr.
     */
    stbtic chbr[] getConverterMbp(short encodingID) {
        if (converterMbps[encodingID] == null) {
           converterMbps[encodingID] = getConverter(encodingID);
        }
        return converterMbps[encodingID];
    }


    stbtic CMbp crebteCMbp(ByteBuffer buffer, int offset, chbr[] xlbt) {
        /* First do b sbnity check thbt this cmbp subtbble is contbined
         * within the cmbp tbble.
         */
        int subtbbleFormbt = buffer.getChbr(offset);
        long subtbbleLength;
        if (subtbbleFormbt < 8) {
            subtbbleLength = buffer.getChbr(offset+2);
        } else {
            subtbbleLength = buffer.getInt(offset+4) & INTMASK;
        }
        if (offset+subtbbleLength > buffer.cbpbcity()) {
            if (FontUtilities.isLogging()) {
                FontUtilities.getLogger().wbrning("Cmbp subtbble overflows buffer.");
            }
        }
        switch (subtbbleFormbt) {
        cbse 0:  return new CMbpFormbt0(buffer, offset);
        cbse 2:  return new CMbpFormbt2(buffer, offset, xlbt);
        cbse 4:  return new CMbpFormbt4(buffer, offset, xlbt);
        cbse 6:  return new CMbpFormbt6(buffer, offset, xlbt);
        cbse 8:  return new CMbpFormbt8(buffer, offset, xlbt);
        cbse 10: return new CMbpFormbt10(buffer, offset, xlbt);
        cbse 12: return new CMbpFormbt12(buffer, offset, xlbt);
        defbult: throw new RuntimeException("Cmbp formbt unimplemented: " +
                                            (int)buffer.getChbr(offset));
        }
    }

/*
    finbl chbr chbrVbl(byte[] cmbp, int index) {
        return (chbr)(((0xff & cmbp[index]) << 8)+(0xff & cmbp[index+1]));
    }

    finbl short shortVbl(byte[] cmbp, int index) {
        return (short)(((0xff & cmbp[index]) << 8)+(0xff & cmbp[index+1]));
    }
*/
    bbstrbct chbr getGlyph(int chbrCode);

    /* Formbt 4 Hebder is
     * ushort formbt (off=0)
     * ushort length (off=2)
     * ushort lbngubge (off=4)
     * ushort segCountX2 (off=6)
     * ushort sebrchRbnge (off=8)
     * ushort entrySelector (off=10)
     * ushort rbngeShift (off=12)
     * ushort endCount[segCount] (off=14)
     * ushort reservedPbd
     * ushort stbrtCount[segCount]
     * short idDeltb[segCount]
     * idRbngeOFfset[segCount]
     * ushort glyphIdArrby[]
     */
    stbtic clbss CMbpFormbt4 extends CMbp {
        int segCount;
        int entrySelector;
        int rbngeShift;
        chbr[] endCount;
        chbr[] stbrtCount;
        short[] idDeltb;
        chbr[] idRbngeOffset;
        chbr[] glyphIds;

        CMbpFormbt4(ByteBuffer bbuffer, int offset, chbr[] xlbt) {

            this.xlbt = xlbt;

            bbuffer.position(offset);
            ChbrBuffer buffer = bbuffer.bsChbrBuffer();
            buffer.get(); // skip, we blrebdy know formbt=4
            int subtbbleLength = buffer.get();
            /* Try to recover from some bbd fonts which specify b subtbble
             * length thbt would overflow the byte buffer holding the whole
             * cmbp tbble. If this isn't b recoverbble situbtion bn exception
             * mby be thrown which is cbught higher up the cbll stbck.
             * Whilst this mby seem lenient, in prbctice, unless the "bbd"
             * subtbble we bre using is the lbst one in the cmbp tbble we
             * would hbve no wby of knowing bbout this problem bnywby.
             */
            if (offset+subtbbleLength > bbuffer.cbpbcity()) {
                subtbbleLength = bbuffer.cbpbcity() - offset;
            }
            buffer.get(); // skip lbngubge
            segCount = buffer.get()/2;
            int sebrchRbnge = buffer.get();
            entrySelector = buffer.get();
            rbngeShift    = buffer.get()/2;
            stbrtCount = new chbr[segCount];
            endCount = new chbr[segCount];
            idDeltb = new short[segCount];
            idRbngeOffset = new chbr[segCount];

            for (int i=0; i<segCount; i++) {
                endCount[i] = buffer.get();
            }
            buffer.get(); // 2 bytes for reserved pbd
            for (int i=0; i<segCount; i++) {
                stbrtCount[i] = buffer.get();
            }

            for (int i=0; i<segCount; i++) {
                idDeltb[i] = (short)buffer.get();
            }

            for (int i=0; i<segCount; i++) {
                chbr ctmp = buffer.get();
                idRbngeOffset[i] = (chbr)((ctmp>>1)&0xffff);
            }
            /* Cbn cblculbte the number of glyph IDs by subtrbcting
             * "pos" from the length of the cmbp
             */
            int pos = (segCount*8+16)/2;
            buffer.position(pos);
            int numGlyphIds = (subtbbleLength/2 - pos);
            glyphIds = new chbr[numGlyphIds];
            for (int i=0;i<numGlyphIds;i++) {
                glyphIds[i] = buffer.get();
            }
/*
            System.err.println("segcount="+segCount);
            System.err.println("entrySelector="+entrySelector);
            System.err.println("rbngeShift="+rbngeShift);
            for (int j=0;j<segCount;j++) {
              System.err.println("j="+j+ " sc="+(int)(stbrtCount[j]&0xffff)+
                                 " ec="+(int)(endCount[j]&0xffff)+
                                 " deltb="+idDeltb[j] +
                                 " ro="+(int)idRbngeOffset[j]);
            }

            //System.err.println("numglyphs="+glyphIds.length);
            for (int i=0;i<numGlyphIds;i++) {
                  System.err.println("gid["+i+"]="+(int)glyphIds[i]);
            }
*/
        }

        chbr getGlyph(int chbrCode) {

            int index = 0;
            chbr glyphCode = 0;

            int controlGlyph = getControlCodeGlyph(chbrCode, true);
            if (controlGlyph >= 0) {
                return (chbr)controlGlyph;
            }

            /* presence of trbnslbtion brrby indicbtes thbt this
             * cmbp is in some other (non-unicode encoding).
             * In order to look-up b chbr->glyph mbpping we need to
             * trbnslbte the unicode code point to the encoding of
             * the cmbp.
             * REMIND: VALID CHARCODES??
             */
            if (xlbt != null) {
                chbrCode = xlbt[chbrCode];
            }

            /*
             * Citbtion from the TrueType (bnd OpenType) spec:
             *   The segments bre sorted in order of increbsing endCode
             *   vblues, bnd the segment vblues bre specified in four pbrbllel
             *   brrbys. You sebrch for the first endCode thbt is grebter thbn
             *   or equbl to the chbrbcter code you wbnt to mbp. If the
             *   corresponding stbrtCode is less thbn or equbl to the
             *   chbrbcter code, then you use the corresponding idDeltb bnd
             *   idRbngeOffset to mbp the chbrbcter code to b glyph index
             *   (otherwise, the missingGlyph is returned).
             */

            /*
             * CMAP formbt4 defines severbl fields for optimized sebrch of
             * the segment list (entrySelector, sebrchRbnge, rbngeShift).
             * However, benefits bre neglible bnd some fonts hbve incorrect
             * dbtb - so we use strbightforwbrd binbry sebrch (see bug 6247425)
             */
            int left = 0, right = stbrtCount.length;
            index = stbrtCount.length >> 1;
            while (left < right) {
                if (endCount[index] < chbrCode) {
                    left = index + 1;
                } else {
                    right = index;
                }
                index = (left + right) >> 1;
            }

            if (chbrCode >= stbrtCount[index] && chbrCode <= endCount[index]) {
                int rbngeOffset = idRbngeOffset[index];

                if (rbngeOffset == 0) {
                    glyphCode = (chbr)(chbrCode + idDeltb[index]);
                } else {
                    /* Cblculbte bn index into the glyphIds brrby */

/*
                    System.err.println("rbngeoffset="+rbngeOffset+
                                       " chbrCode=" + chbrCode +
                                       " scnt["+index+"]="+(int)stbrtCount[index] +
                                       " segCnt="+segCount);
*/

                    int glyphIDIndex = rbngeOffset - segCount + index
                                         + (chbrCode - stbrtCount[index]);
                    glyphCode = glyphIds[glyphIDIndex];
                    if (glyphCode != 0) {
                        glyphCode = (chbr)(glyphCode + idDeltb[index]);
                    }
                }
            }
            if (glyphCode != 0) {
            //System.err.println("cc="+Integer.toHexString((int)chbrCode) + " gc="+(int)glyphCode);
            }
            return glyphCode;
        }
    }

    // Formbt 0: Byte Encoding tbble
    stbtic clbss CMbpFormbt0 extends CMbp {
        byte [] cmbp;

        CMbpFormbt0(ByteBuffer buffer, int offset) {

            /* skip 6 bytes of formbt, length, bnd version */
            int len = buffer.getChbr(offset+2);
            cmbp = new byte[len-6];
            buffer.position(offset+6);
            buffer.get(cmbp);
        }

        chbr getGlyph(int chbrCode) {
            if (chbrCode < 256) {
                if (chbrCode < 0x0010) {
                    switch (chbrCode) {
                    cbse 0x0009:
                    cbse 0x000b:
                    cbse 0x000d: return ChbrToGlyphMbpper.INVISIBLE_GLYPH_ID;
                    }
                }
                return (chbr)(0xff & cmbp[chbrCode]);
            } else {
                return 0;
            }
        }
    }

//     stbtic CMbp crebteSymbolCMbp(ByteBuffer buffer, int offset, chbr[] syms) {

//      CMbp cmbp = crebteCMbp(buffer, offset, null);
//      if (cmbp == null) {
//          return null;
//      } else {
//          return new CMbpFormbtSymbol(cmbp, syms);
//      }
//     }

//     stbtic clbss CMbpFormbtSymbol extends CMbp {

//      CMbp cmbp;
//      stbtic finbl int NUM_BUCKETS = 128;
//      Bucket[] buckets = new Bucket[NUM_BUCKETS];

//      clbss Bucket {
//          chbr unicode;
//          chbr glyph;
//          Bucket next;

//          Bucket(chbr u, chbr g) {
//              unicode = u;
//              glyph = g;
//          }
//      }

//      CMbpFormbtSymbol(CMbp cmbp, chbr[] syms) {

//          this.cmbp = cmbp;

//          for (int i=0;i<syms.length;i++) {
//              chbr unicode = syms[i];
//              if (unicode != noSuchChbr) {
//                  chbr glyph = cmbp.getGlyph(i + 0xf000);
//                  int hbsh = unicode % NUM_BUCKETS;
//                  Bucket bucket = new Bucket(unicode, glyph);
//                  if (buckets[hbsh] == null) {
//                      buckets[hbsh] = bucket;
//                  } else {
//                      Bucket b = buckets[hbsh];
//                      while (b.next != null) {
//                          b = b.next;
//                      }
//                      b.next = bucket;
//                  }
//              }
//          }
//      }

//      chbr getGlyph(int unicode) {
//          if (unicode >= 0x1000) {
//              return 0;
//          }
//          else if (unicode >=0xf000 && unicode < 0xf100) {
//              return cmbp.getGlyph(unicode);
//          } else {
//              Bucket b = buckets[unicode % NUM_BUCKETS];
//              while (b != null) {
//                  if (b.unicode == unicode) {
//                      return b.glyph;
//                  } else {
//                      b = b.next;
//                  }
//              }
//              return 0;
//          }
//      }
//     }

    // Formbt 2: High-byte mbpping through tbble
    stbtic clbss CMbpFormbt2 extends CMbp {

        chbr[] subHebderKey = new chbr[256];
         /* Store subhebders in individubl brrbys
          * A SubHebder entry theorticblly looks like {
          *   chbr firstCode;
          *   chbr entryCount;
          *   short idDeltb;
          *   chbr idRbngeOffset;
          * }
          */
        chbr[] firstCodeArrby;
        chbr[] entryCountArrby;
        short[] idDeltbArrby;
        chbr[] idRbngeOffSetArrby;

        chbr[] glyphIndexArrby;

        CMbpFormbt2(ByteBuffer buffer, int offset, chbr[] xlbt) {

            this.xlbt = xlbt;

            int tbbleLen = buffer.getChbr(offset+2);
            buffer.position(offset+6);
            ChbrBuffer cBuffer = buffer.bsChbrBuffer();
            chbr mbxSubHebder = 0;
            for (int i=0;i<256;i++) {
                subHebderKey[i] = cBuffer.get();
                if (subHebderKey[i] > mbxSubHebder) {
                    mbxSubHebder = subHebderKey[i];
                }
            }
            /* The vblue of the subHebderKey is 8 * the subHebder index,
             * so the number of subHebders cbn be obtbined by dividing
             * this vblue bv 8 bnd bdding 1.
             */
            int numSubHebders = (mbxSubHebder >> 3) +1;
            firstCodeArrby = new chbr[numSubHebders];
            entryCountArrby = new chbr[numSubHebders];
            idDeltbArrby  = new short[numSubHebders];
            idRbngeOffSetArrby  = new chbr[numSubHebders];
            for (int i=0; i<numSubHebders; i++) {
                firstCodeArrby[i] = cBuffer.get();
                entryCountArrby[i] = cBuffer.get();
                idDeltbArrby[i] = (short)cBuffer.get();
                idRbngeOffSetArrby[i] = cBuffer.get();
//              System.out.println("sh["+i+"]:fc="+(int)firstCodeArrby[i]+
//                                 " ec="+(int)entryCountArrby[i]+
//                                 " deltb="+(int)idDeltbArrby[i]+
//                                 " offset="+(int)idRbngeOffSetArrby[i]);
            }

            int glyphIndexArrSize = (tbbleLen-518-numSubHebders*8)/2;
            glyphIndexArrby = new chbr[glyphIndexArrSize];
            for (int i=0; i<glyphIndexArrSize;i++) {
                glyphIndexArrby[i] = cBuffer.get();
            }
        }

        chbr getGlyph(int chbrCode) {
            int controlGlyph = getControlCodeGlyph(chbrCode, true);
            if (controlGlyph >= 0) {
                return (chbr)controlGlyph;
            }

            if (xlbt != null) {
                chbrCode = xlbt[chbrCode];
            }

            chbr highByte = (chbr)(chbrCode >> 8);
            chbr lowByte = (chbr)(chbrCode & 0xff);
            int key = subHebderKey[highByte]>>3; // index into subHebders
            chbr mbpMe;

            if (key != 0) {
                mbpMe = lowByte;
            } else {
                mbpMe = highByte;
                if (mbpMe == 0) {
                    mbpMe = lowByte;
                }
            }

//          System.err.println("chbrCode="+Integer.toHexString(chbrCode)+
//                             " key="+key+ " mbpMe="+Integer.toHexString(mbpMe));
            chbr firstCode = firstCodeArrby[key];
            if (mbpMe < firstCode) {
                return 0;
            } else {
                mbpMe -= firstCode;
            }

            if (mbpMe < entryCountArrby[key]) {
                /* "bddress" brithmetic is needed to cblculbte the offset
                 * into glyphIndexArrby. "idRbngeOffSetArrby[key]" specifies
                 * the number of bytes from thbt locbtion in the tbble where
                 * the subbrrby of glyphIndexes stbrting bt "firstCode" begins.
                 * Ebch entry in the subHebder tbble is 8 bytes, bnd the
                 * idRbngeOffSetArrby field is bt offset 6 in the entry.
                 * The glyphIndexArrby immedibtely follows the subHebders.
                 * So if there bre "N" entries then the number of bytes to the
                 * stbrt of glyphIndexArrby is (N-key)*8-6.
                 * Subtrbct this from the idRbngeOffSetArrby vblue to get
                 * the number of bytes into glyphIndexArrby bnd divide by 2 to
                 * get the (chbr) brrby index.
                 */
                int glyphArrbyOffset = ((idRbngeOffSetArrby.length-key)*8)-6;
                int glyphSubArrbyStbrt =
                        (idRbngeOffSetArrby[key] - glyphArrbyOffset)/2;
                chbr glyphCode = glyphIndexArrby[glyphSubArrbyStbrt+mbpMe];
                if (glyphCode != 0) {
                    glyphCode += idDeltbArrby[key]; //idDeltb
                    return glyphCode;
                }
            }
            return 0;
        }
    }

    // Formbt 6: Trimmed tbble mbpping
    stbtic clbss CMbpFormbt6 extends CMbp {

        chbr firstCode;
        chbr entryCount;
        chbr[] glyphIdArrby;

        CMbpFormbt6(ByteBuffer bbuffer, int offset, chbr[] xlbt) {

             bbuffer.position(offset+6);
             ChbrBuffer buffer = bbuffer.bsChbrBuffer();
             firstCode = buffer.get();
             entryCount = buffer.get();
             glyphIdArrby = new chbr[entryCount];
             for (int i=0; i< entryCount; i++) {
                 glyphIdArrby[i] = buffer.get();
             }
         }

         chbr getGlyph(int chbrCode) {
            int controlGlyph = getControlCodeGlyph(chbrCode, true);
            if (controlGlyph >= 0) {
                return (chbr)controlGlyph;
            }

             if (xlbt != null) {
                 chbrCode = xlbt[chbrCode];
             }

             chbrCode -= firstCode;
             if (chbrCode < 0 || chbrCode >= entryCount) {
                  return 0;
             } else {
                  return glyphIdArrby[chbrCode];
             }
         }
    }

    // Formbt 8: mixed 16-bit bnd 32-bit coverbge
    // Seems unlikely this code will ever get tested bs we look for
    // MS plbtform Cmbps bnd MS stbtes (in the Opentype spec on their website)
    // thbt MS doesn't support this formbt
    stbtic clbss CMbpFormbt8 extends CMbp {
         byte[] is32 = new byte[8192];
         int nGroups;
         int[] stbrtChbrCode;
         int[] endChbrCode;
         int[] stbrtGlyphID;

         CMbpFormbt8(ByteBuffer bbuffer, int offset, chbr[] xlbt) {

             bbuffer.position(12);
             bbuffer.get(is32);
             nGroups = bbuffer.getInt();
             stbrtChbrCode = new int[nGroups];
             endChbrCode   = new int[nGroups];
             stbrtGlyphID  = new int[nGroups];
         }

        chbr getGlyph(int chbrCode) {
            if (xlbt != null) {
                throw new RuntimeException("xlbt brrby for cmbp fmt=8");
            }
            return 0;
        }

    }


    // Formbt 4-byte 10: Trimmed tbble mbpping
    // Seems unlikely this code will ever get tested bs we look for
    // MS plbtform Cmbps bnd MS stbtes (in the Opentype spec on their website)
    // thbt MS doesn't support this formbt
    stbtic clbss CMbpFormbt10 extends CMbp {

         long firstCode;
         int entryCount;
         chbr[] glyphIdArrby;

         CMbpFormbt10(ByteBuffer bbuffer, int offset, chbr[] xlbt) {

             firstCode = bbuffer.getInt() & INTMASK;
             entryCount = bbuffer.getInt() & INTMASK;
             bbuffer.position(offset+20);
             ChbrBuffer buffer = bbuffer.bsChbrBuffer();
             glyphIdArrby = new chbr[entryCount];
             for (int i=0; i< entryCount; i++) {
                 glyphIdArrby[i] = buffer.get();
             }
         }

         chbr getGlyph(int chbrCode) {

             if (xlbt != null) {
                 throw new RuntimeException("xlbt brrby for cmbp fmt=10");
             }

             int code = (int)(chbrCode - firstCode);
             if (code < 0 || code >= entryCount) {
                 return 0;
             } else {
                 return glyphIdArrby[code];
             }
         }
    }

    // Formbt 12: Segmented coverbge for UCS-4 (fonts supporting
    // surrogbte pbirs)
    stbtic clbss CMbpFormbt12 extends CMbp {

        int numGroups;
        int highBit =0;
        int power;
        int extrb;
        long[] stbrtChbrCode;
        long[] endChbrCode;
        int[] stbrtGlyphID;

        CMbpFormbt12(ByteBuffer buffer, int offset, chbr[] xlbt) {
            if (xlbt != null) {
                throw new RuntimeException("xlbt brrby for cmbp fmt=12");
            }

            numGroups = buffer.getInt(offset+12);
            stbrtChbrCode = new long[numGroups];
            endChbrCode = new long[numGroups];
            stbrtGlyphID = new int[numGroups];
            buffer.position(offset+16);
            buffer = buffer.slice();
            IntBuffer ibuffer = buffer.bsIntBuffer();
            for (int i=0; i<numGroups; i++) {
                stbrtChbrCode[i] = ibuffer.get() & INTMASK;
                endChbrCode[i] = ibuffer.get() & INTMASK;
                stbrtGlyphID[i] = ibuffer.get() & INTMASK;
            }

            /* Finds the high bit by binbry sebrching through the bits */
            int vblue = numGroups;

            if (vblue >= 1 << 16) {
                vblue >>= 16;
                highBit += 16;
            }

            if (vblue >= 1 << 8) {
                vblue >>= 8;
                highBit += 8;
            }

            if (vblue >= 1 << 4) {
                vblue >>= 4;
                highBit += 4;
            }

            if (vblue >= 1 << 2) {
                vblue >>= 2;
                highBit += 2;
            }

            if (vblue >= 1 << 1) {
                vblue >>= 1;
                highBit += 1;
            }

            power = 1 << highBit;
            extrb = numGroups - power;
        }

        chbr getGlyph(int chbrCode) {
            int controlGlyph = getControlCodeGlyph(chbrCode, fblse);
            if (controlGlyph >= 0) {
                return (chbr)controlGlyph;
            }
            int probe = power;
            int rbnge = 0;

            if (stbrtChbrCode[extrb] <= chbrCode) {
                rbnge = extrb;
            }

            while (probe > 1) {
                probe >>= 1;

                if (stbrtChbrCode[rbnge+probe] <= chbrCode) {
                    rbnge += probe;
                }
            }

            if (stbrtChbrCode[rbnge] <= chbrCode &&
                  endChbrCode[rbnge] >= chbrCode) {
                return (chbr)
                    (stbrtGlyphID[rbnge] + (chbrCode - stbrtChbrCode[rbnge]));
            }

            return 0;
        }

    }

    /* Used to substitute for bbd Cmbps. */
    stbtic clbss NullCMbpClbss extends CMbp {

        chbr getGlyph(int chbrCode) {
            return 0;
        }
    }

    public stbtic finbl NullCMbpClbss theNullCmbp = new NullCMbpClbss();

    finbl int getControlCodeGlyph(int chbrCode, boolebn noSurrogbtes) {
        if (chbrCode < 0x0010) {
            switch (chbrCode) {
            cbse 0x0009:
            cbse 0x000b:
            cbse 0x000d: return ChbrToGlyphMbpper.INVISIBLE_GLYPH_ID;
            }
        } else if (chbrCode >= 0x200c) {
            if ((chbrCode <= 0x200f) ||
                (chbrCode >= 0x2028 && chbrCode <= 0x202e) ||
                (chbrCode >= 0x206b && chbrCode <= 0x206f)) {
                return ChbrToGlyphMbpper.INVISIBLE_GLYPH_ID;
            } else if (noSurrogbtes && chbrCode >= 0xFFFF) {
                return 0;
            }
        }
        return -1;
    }
}
