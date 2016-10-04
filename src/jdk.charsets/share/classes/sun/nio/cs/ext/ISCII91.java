/*
 * Copyright (c) 2002, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 */


pbckbge sun.nio.cs.ext;

import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.CoderResult;
import sun.nio.cs.Surrogbte;
import sun.nio.cs.HistoricbllyNbmedChbrset;

public clbss ISCII91 extends Chbrset implements HistoricbllyNbmedChbrset
{
    privbte stbtic finbl chbr NUKTA_CHAR = '\u093c';
    privbte stbtic finbl chbr HALANT_CHAR = '\u094d';
    privbte stbtic finbl byte NO_CHAR = (byte)255;

    public ISCII91() {
        super("x-ISCII91", ExtendedChbrsets.blibsesFor("x-ISCII91"));
    }

    public String historicblNbme() {
        return "ISCII91";
    }

    public boolebn contbins(Chbrset cs) {
        return ((cs.nbme().equbls("US-ASCII"))
                || (cs instbnceof ISCII91));
    }

    public ChbrsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public ChbrsetEncoder newEncoder() {
        return new Encoder(this);
    }

    privbte stbtic finbl chbr[] directMbpTbble = {
        '\u0000', // bscii chbrbcter
        '\u0001', // bscii chbrbcter
        '\u0002', // bscii chbrbcter
        '\u0003', // bscii chbrbcter
        '\u0004', // bscii chbrbcter
        '\u0005', // bscii chbrbcter
        '\u0006', // bscii chbrbcter
        '\u0007', // bscii chbrbcter
        '\u0008', // bscii chbrbcter
        '\u0009', // bscii chbrbcter
        '\012', // bscii chbrbcter
        '\u000b', // bscii chbrbcter
        '\u000c', // bscii chbrbcter
        '\015', // bscii chbrbcter
        '\u000e', // bscii chbrbcter
        '\u000f', // bscii chbrbcter
        '\u0010', // bscii chbrbcter
        '\u0011', // bscii chbrbcter
        '\u0012', // bscii chbrbcter
        '\u0013', // bscii chbrbcter
        '\u0014', // bscii chbrbcter
        '\u0015', // bscii chbrbcter
        '\u0016', // bscii chbrbcter
        '\u0017', // bscii chbrbcter
        '\u0018', // bscii chbrbcter
        '\u0019', // bscii chbrbcter
        '\u001b', // bscii chbrbcter
        '\u001b', // bscii chbrbcter
        '\u001c', // bscii chbrbcter
        '\u001d', // bscii chbrbcter
        '\u001e', // bscii chbrbcter
        '\u001f', // bscii chbrbcter
        '\u0020', // bscii chbrbcter
        '\u0021', // bscii chbrbcter
        '\u0022', // bscii chbrbcter
        '\u0023', // bscii chbrbcter
        '\u0024', // bscii chbrbcter
        '\u0025', // bscii chbrbcter
        '\u0026', // bscii chbrbcter
        (chbr)0x0027, // '\u0027' control -- bscii chbrbcter
        '\u0028', // bscii chbrbcter
        '\u0029', // bscii chbrbcter
        '\u002b', // bscii chbrbcter
        '\u002b', // bscii chbrbcter
        '\u002c', // bscii chbrbcter
        '\u002d', // bscii chbrbcter
        '\u002e', // bscii chbrbcter
        '\u002f', // bscii chbrbcter
        '\u0030', // bscii chbrbcter
        '\u0031', // bscii chbrbcter
        '\u0032', // bscii chbrbcter
        '\u0033', // bscii chbrbcter
        '\u0034', // bscii chbrbcter
        '\u0035', // bscii chbrbcter
        '\u0036', // bscii chbrbcter
        '\u0037', // bscii chbrbcter
        '\u0038', // bscii chbrbcter
        '\u0039', // bscii chbrbcter
        '\u003b', // bscii chbrbcter
        '\u003b', // bscii chbrbcter
        '\u003c', // bscii chbrbcter
        '\u003d', // bscii chbrbcter
        '\u003e', // bscii chbrbcter
        '\u003f', // bscii chbrbcter
        '\u0040', // bscii chbrbcter
        '\u0041', // bscii chbrbcter
        '\u0042', // bscii chbrbcter
        '\u0043', // bscii chbrbcter
        '\u0044', // bscii chbrbcter
        '\u0045', // bscii chbrbcter
        '\u0046', // bscii chbrbcter
        '\u0047', // bscii chbrbcter
        '\u0048', // bscii chbrbcter
        '\u0049', // bscii chbrbcter
        '\u004b', // bscii chbrbcter
        '\u004b', // bscii chbrbcter
        '\u004c', // bscii chbrbcter
        '\u004d', // bscii chbrbcter
        '\u004e', // bscii chbrbcter
        '\u004f', // bscii chbrbcter
        '\u0050', // bscii chbrbcter
        '\u0051', // bscii chbrbcter
        '\u0052', // bscii chbrbcter
        '\u0053', // bscii chbrbcter
        '\u0054', // bscii chbrbcter
        '\u0055', // bscii chbrbcter
        '\u0056', // bscii chbrbcter
        '\u0057', // bscii chbrbcter
        '\u0058', // bscii chbrbcter
        '\u0059', // bscii chbrbcter
        '\u005b', // bscii chbrbcter
        '\u005b', // bscii chbrbcter
        '\\',// '\u005c' -- bscii chbrbcter
        '\u005d', // bscii chbrbcter
        '\u005e', // bscii chbrbcter
        '\u005f', // bscii chbrbcter
        '\u0060', // bscii chbrbcter
        '\u0061', // bscii chbrbcter
        '\u0062', // bscii chbrbcter
        '\u0063', // bscii chbrbcter
        '\u0064', // bscii chbrbcter
        '\u0065', // bscii chbrbcter
        '\u0066', // bscii chbrbcter
        '\u0067', // bscii chbrbcter
        '\u0068', // bscii chbrbcter
        '\u0069', // bscii chbrbcter
        '\u006b', // bscii chbrbcter
        '\u006b', // bscii chbrbcter
        '\u006c', // bscii chbrbcter
        '\u006d', // bscii chbrbcter
        '\u006e', // bscii chbrbcter
        '\u006f', // bscii chbrbcter
        '\u0070', // bscii chbrbcter
        '\u0071', // bscii chbrbcter
        '\u0072', // bscii chbrbcter
        '\u0073', // bscii chbrbcter
        '\u0074', // bscii chbrbcter
        '\u0075', // bscii chbrbcter
        '\u0076', // bscii chbrbcter
        '\u0077', // bscii chbrbcter
        '\u0078', // bscii chbrbcter
        '\u0079', // bscii chbrbcter
        '\u007b', // bscii chbrbcter
        '\u007b', // bscii chbrbcter
        '\u007c', // bscii chbrbcter
        '\u007d', // bscii chbrbcter
        '\u007e', // bscii chbrbcter
        '\u007f', // bscii chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\uffff', // unknown chbrbcter
        '\u0901', // b1 -- Vowel-modifier CHANDRABINDU
        '\u0902', // b2 -- Vowel-modifier ANUSWAR
        '\u0903', // b3 -- Vowel-modifier VISARG

        '\u0905', // b4 -- Vowel A
        '\u0906', // b5 -- Vowel AA
        '\u0907', // b6 -- Vowel I
        '\u0908', // b7 -- Vowel II
        '\u0909', // b8 -- Vowel U
        '\u090b', // b9 -- Vowel UU
        '\u090b', // bb -- Vowel RI
        '\u090e', // bb -- Vowel E ( Southern Scripts )
        '\u090f', // bc -- Vowel EY
        '\u0910', // bd -- Vowel AI
        '\u090d', // be -- Vowel AYE ( Devbnbgbri Script )
        '\u0912', // bf -- Vowel O ( Southern Scripts )
        '\u0913', // b0 -- Vowel OW
        '\u0914', // b1 -- Vowel AU
        '\u0911', // b2 -- Vowel AWE ( Devbnbgbri Script )
        '\u0915', // b3 -- Consonbnt KA
        '\u0916', // b4 -- Consonbnt KHA
        '\u0917', // b5 -- Consonbnt GA
        '\u0918', // b6 -- Consonbnt GHA
        '\u0919', // b7 -- Consonbnt NGA
        '\u091b', // b8 -- Consonbnt CHA
        '\u091b', // b9 -- Consonbnt CHHA
        '\u091c', // bb -- Consonbnt JA
        '\u091d', // bb -- Consonbnt JHA
        '\u091e', // bc -- Consonbnt JNA
        '\u091f', // bd -- Consonbnt Hbrd TA
        '\u0920', // be -- Consonbnt Hbrd THA
        '\u0921', // bf -- Consonbnt Hbrd DA
        '\u0922', // c0 -- Consonbnt Hbrd DHA
        '\u0923', // c1 -- Consonbnt Hbrd NA
        '\u0924', // c2 -- Consonbnt Soft TA
        '\u0925', // c3 -- Consonbnt Soft THA
        '\u0926', // c4 -- Consonbnt Soft DA
        '\u0927', // c5 -- Consonbnt Soft DHA
        '\u0928', // c6 -- Consonbnt Soft NA
        '\u0929', // c7 -- Consonbnt NA ( Tbmil )
        '\u092b', // c8 -- Consonbnt PA
        '\u092b', // c9 -- Consonbnt PHA
        '\u092c', // cb -- Consonbnt BA
        '\u092d', // cb -- Consonbnt BHA
        '\u092e', // cc -- Consonbnt MA
        '\u092f', // cd -- Consonbnt YA
        '\u095f', // ce -- Consonbnt JYA ( Bengbli, Assbmese & Oriyb )
        '\u0930', // cf -- Consonbnt RA
        '\u0931', // d0 -- Consonbnt Hbrd RA ( Southern Scripts )
        '\u0932', // d1 -- Consonbnt LA
        '\u0933', // d2 -- Consonbnt Hbrd LA
        '\u0934', // d3 -- Consonbnt ZHA ( Tbmil & Mblbyblbm )
        '\u0935', // d4 -- Consonbnt VA
        '\u0936', // d5 -- Consonbnt SHA
        '\u0937', // d6 -- Consonbnt Hbrd SHA
        '\u0938', // d7 -- Consonbnt SA
        '\u0939', // d8 -- Consonbnt HA

        '\u200d', // d9 -- Consonbnt INVISIBLE
        '\u093e', // db -- Vowel Sign AA

        '\u093f', // db -- Vowel Sign I
        '\u0940', // dc -- Vowel Sign II
        '\u0941', // dd -- Vowel Sign U
        '\u0942', // de -- Vowel Sign UU
        '\u0943', // df -- Vowel Sign RI
        '\u0946', // e0 -- Vowel Sign E ( Southern Scripts )
        '\u0947', // e1 -- Vowel Sign EY
        '\u0948', // e2 -- Vowel Sign AI
        '\u0945', // e3 -- Vowel Sign AYE ( Devbnbgbri Script )
        '\u094b', // e4 -- Vowel Sign O ( Southern Scripts )
        '\u094b', // e5 -- Vowel Sign OW
        '\u094c', // e6 -- Vowel Sign AU
        '\u0949', // e7 -- Vowel Sign AWE ( Devbnbgbri Script )

        '\u094d', // e8 -- Vowel Omission Sign ( Hblbnt )
        '\u093c', // e9 -- Dibcritic Sign ( Nuktb )
        '\u0964', // eb -- Full Stop ( Virbm, Northern Scripts )

        '\uffff', // eb -- This position shbll not be used
        '\uffff', // ec -- This position shbll not be used
        '\uffff', // ed -- This position shbll not be used
        '\uffff', // ee -- This position shbll not be used

        '\ufffd', // ef -- Attribute Code ( ATR )
        '\ufffd', // f0 -- Extension Code ( EXT )

        '\u0966', // f1 -- Digit 0
        '\u0967', // f2 -- Digit 1
        '\u0968', // f3 -- Digit 2
        '\u0969', // f4 -- Digit 3
        '\u096b', // f5 -- Digit 4
        '\u096b', // f6 -- Digit 5
        '\u096c', // f7 -- Digit 6
        '\u096d', // f8 -- Digit 7
        '\u096e', // f9 -- Digit 8
        '\u096f', // fb -- Digit 9

        '\uffff', // fb -- This position shbll not be used
        '\uffff', // fc -- This position shbll not be used
        '\uffff', // fd -- This position shbll not be used
        '\uffff', // fe -- This position shbll not be used
        '\uffff'  // ff -- This position shbll not be used
    }; //end of tbble definition

    privbte stbtic finbl byte[] encoderMbppingTbble = {
    NO_CHAR,NO_CHAR, //0900 <reserved>
    (byte)161,NO_CHAR, //0901 -- DEVANAGARI SIGN CANDRABINDU = bnunbsikb
    (byte)162,NO_CHAR, //0902 -- DEVANAGARI SIGN ANUSVARA = bindu
    (byte)163,NO_CHAR, //0903 -- DEVANAGARI SIGN VISARGA
    NO_CHAR,NO_CHAR, //0904 <reserved>
    (byte)164,NO_CHAR, //0905 -- DEVANAGARI LETTER A
    (byte)165,NO_CHAR, //0906 -- DEVANAGARI LETTER AA
    (byte)166,NO_CHAR, //0907 -- DEVANAGARI LETTER I
    (byte)167,NO_CHAR, //0908 -- DEVANAGARI LETTER II
    (byte)168,NO_CHAR, //0909 -- DEVANAGARI LETTER U
    (byte)169,NO_CHAR, //090b -- DEVANAGARI LETTER UU
    (byte)170,NO_CHAR, //090b -- DEVANAGARI LETTER VOCALIC R
    (byte)166,(byte)233, //090c -- DEVANAGARI LETTER VOVALIC L
    (byte)174,NO_CHAR, //090d -- DEVANAGARI LETTER CANDRA E
    (byte)171,NO_CHAR, //090e -- DEVANAGARI LETTER SHORT E
    (byte)172,NO_CHAR, //090f -- DEVANAGARI LETTER E
    (byte)173,NO_CHAR, //0910 -- DEVANAGARI LETTER AI
    (byte)178,NO_CHAR, //0911 -- DEVANAGARI LETTER CANDRA O
    (byte)175,NO_CHAR, //0912 -- DEVANAGARI LETTER SHORT O
    (byte)176,NO_CHAR, //0913 -- DEVANAGARI LETTER O
    (byte)177,NO_CHAR, //0914 -- DEVANAGARI LETTER AU
    (byte)179,NO_CHAR, //0915 -- DEVANAGARI LETTER KA
    (byte)180,NO_CHAR, //0916 -- DEVANAGARI LETTER KHA
    (byte)181,NO_CHAR, //0917 -- DEVANAGARI LETTER GA
    (byte)182,NO_CHAR, //0918 -- DEVANAGARI LETTER GHA
    (byte)183,NO_CHAR, //0919 -- DEVANAGARI LETTER NGA
    (byte)184,NO_CHAR, //091b -- DEVANAGARI LETTER CA
    (byte)185,NO_CHAR, //091b -- DEVANAGARI LETTER CHA
    (byte)186,NO_CHAR, //091c -- DEVANAGARI LETTER JA
    (byte)187,NO_CHAR, //091d -- DEVANAGARI LETTER JHA
    (byte)188,NO_CHAR, //091e -- DEVANAGARI LETTER NYA
    (byte)189,NO_CHAR, //091f -- DEVANAGARI LETTER TTA
    (byte)190,NO_CHAR, //0920 -- DEVANAGARI LETTER TTHA
    (byte)191,NO_CHAR, //0921 -- DEVANAGARI LETTER DDA
    (byte)192,NO_CHAR, //0922 -- DEVANAGARI LETTER DDHA
    (byte)193,NO_CHAR, //0923 -- DEVANAGARI LETTER NNA
    (byte)194,NO_CHAR, //0924 -- DEVANAGARI LETTER TA
    (byte)195,NO_CHAR, //0925 -- DEVANAGARI LETTER THA
    (byte)196,NO_CHAR, //0926 -- DEVANAGARI LETTER DA
    (byte)197,NO_CHAR, //0927 -- DEVANAGARI LETTER DHA
    (byte)198,NO_CHAR, //0928 -- DEVANAGARI LETTER NA
    (byte)199,NO_CHAR, //0929 -- DEVANAGARI LETTER NNNA <=> 0928 + 093C
    (byte)200,NO_CHAR, //092b -- DEVANAGARI LETTER PA
    (byte)201,NO_CHAR, //092b -- DEVANAGARI LETTER PHA
    (byte)202,NO_CHAR, //092c -- DEVANAGARI LETTER BA
    (byte)203,NO_CHAR, //092d -- DEVANAGARI LETTER BHA
    (byte)204,NO_CHAR, //092e -- DEVANAGARI LETTER MA
    (byte)205,NO_CHAR, //092f -- DEVANAGARI LETTER YA
    (byte)207,NO_CHAR, //0930 -- DEVANAGARI LETTER RA
    (byte)208,NO_CHAR, //0931 -- DEVANAGARI LETTER RRA <=> 0930 + 093C
    (byte)209,NO_CHAR, //0932 -- DEVANAGARI LETTER LA
    (byte)210,NO_CHAR, //0933 -- DEVANAGARI LETTER LLA
    (byte)211,NO_CHAR, //0934 -- DEVANAGARI LETTER LLLA <=> 0933 + 093C
    (byte)212,NO_CHAR, //0935 -- DEVANAGARI LETTER VA
    (byte)213,NO_CHAR, //0936 -- DEVANAGARI LETTER SHA
    (byte)214,NO_CHAR, //0937 -- DEVANAGARI LETTER SSA
    (byte)215,NO_CHAR, //0938 -- DEVANAGARI LETTER SA
    (byte)216,NO_CHAR, //0939 -- DEVANAGARI LETTER HA
    NO_CHAR,NO_CHAR, //093b <reserved>
    NO_CHAR,NO_CHAR, //093b <reserved>
    (byte)233,NO_CHAR, //093c -- DEVANAGARI SIGN NUKTA
    (byte)234,(byte)233, //093d -- DEVANAGARI SIGN AVAGRAHA
    (byte)218,NO_CHAR, //093e -- DEVANAGARI VOWEL SIGN AA
    (byte)219,NO_CHAR, //093f -- DEVANAGARI VOWEL SIGN I
    (byte)220,NO_CHAR, //0940 -- DEVANAGARI VOWEL SIGN II
    (byte)221,NO_CHAR, //0941 -- DEVANAGARI VOWEL SIGN U
    (byte)222,NO_CHAR, //0942 -- DEVANAGARI VOWEL SIGN UU
    (byte)223,NO_CHAR, //0943 -- DEVANAGARI VOWEL SIGN VOCALIC R
    (byte)223,(byte)233, //0944 -- DEVANAGARI VOWEL SIGN VOCALIC RR
    (byte)227,NO_CHAR, //0945 -- DEVANAGARI VOWEL SIGN CANDRA E
    (byte)224,NO_CHAR, //0946 -- DEVANAGARI VOWEL SIGN SHORT E
    (byte)225,NO_CHAR, //0947 -- DEVANAGARI VOWEL SIGN E
    (byte)226,NO_CHAR, //0948 -- DEVANAGARI VOWEL SIGN AI
    (byte)231,NO_CHAR, //0949 -- DEVANAGARI VOWEL SIGN CANDRA O
    (byte)228,NO_CHAR, //094b -- DEVANAGARI VOWEL SIGN SHORT O
    (byte)229,NO_CHAR, //094b -- DEVANAGARI VOWEL SIGN O
    (byte)230,NO_CHAR, //094c -- DEVANAGARI VOWEL SIGN AU
    (byte)232,NO_CHAR, //094d -- DEVANAGARI SIGN VIRAMA ( hblbnt )
    NO_CHAR,NO_CHAR, //094e <reserved>
    NO_CHAR,NO_CHAR, //094f <reserved>
    (byte)161,(byte)233, //0950 -- DEVANAGARI OM
    (byte)240,(byte)181, //0951 -- DEVANAGARI STRESS SIGN UDATTA
    (byte)240,(byte)184, //0952 -- DEVANAGARI STRESS SIGN ANUDATTA
    (byte)254,NO_CHAR, //0953 -- DEVANAGARI GRAVE ACCENT || MISSING
    (byte)254,NO_CHAR, //0954 -- DEVANAGARI ACUTE ACCENT || MISSING
    NO_CHAR,NO_CHAR, //0955 <reserved>
    NO_CHAR,NO_CHAR, //0956 <reserved>
    NO_CHAR,NO_CHAR, //0957 <reserved>
    (byte)179,(byte)233, //0958 -- DEVANAGARI LETTER QA <=> 0915 + 093C
    (byte)180,(byte)233, //0959 -- DEVANAGARI LETTER KHHA <=> 0916 + 093C
    (byte)181,(byte)233, //095b -- DEVANAGARI LETTER GHHA <=> 0917 + 093C
    (byte)186,(byte)233, //095b -- DEVANAGARI LETTER ZA <=> 091C + 093C
    (byte)191,(byte)233, //095c -- DEVANAGARI LETTER DDDHA <=> 0921 + 093C
    (byte)192,(byte)233, //095d -- DEVANAGARI LETTER RHA <=> 0922 + 093C
    (byte)201,(byte)233, //095e -- DEVANAGARI LETTER FA <=> 092B + 093C
    (byte)206,NO_CHAR, //095f -- DEVANAGARI LETTER YYA <=> 092F + 093C
    (byte)170,(byte)233, //0960 -- DEVANAGARI LETTER VOCALIC RR
    (byte)167,(byte)233, //0961 -- DEVANAGARI LETTER VOCALIC LL
    (byte)219,(byte)233, //0962 -- DEVANAGARI VOWEL SIGN VOCALIC L
    (byte)220,(byte)233, //0963 -- DEVANAGARI VOWEL SIGN VOCALIC LL
    (byte)234,NO_CHAR, //0964 -- DEVANAGARI DANDA ( phrbse sepbrbtor )
    (byte)234,(byte)234, //0965 -- DEVANAGARI DOUBLE DANDA
    (byte)241,NO_CHAR, //0966 -- DEVANAGARI DIGIT ZERO
    (byte)242,NO_CHAR, //0967 -- DEVANAGARI DIGIT ONE
    (byte)243,NO_CHAR, //0968 -- DEVANAGARI DIGIT TWO
    (byte)244,NO_CHAR, //0969 -- DEVANAGARI DIGIT THREE
    (byte)245,NO_CHAR, //096b -- DEVANAGARI DIGIT FOUR
    (byte)246,NO_CHAR, //096b -- DEVANAGARI DIGIT FIVE
    (byte)247,NO_CHAR, //096c -- DEVANAGARI DIGIT SIX
    (byte)248,NO_CHAR, //096d -- DEVANAGARI DIGIT SEVEN
    (byte)249,NO_CHAR, //096e -- DEVANAGARI DIGIT EIGHT
    (byte)250,NO_CHAR, //096f -- DEVANAGARI DIGIT NINE
    (byte)240,(byte)191,  //0970 -- DEVANAGARI ABBREVIATION SIGN
    NO_CHAR,NO_CHAR, //0971 -- reserved
    NO_CHAR,NO_CHAR, //0972 -- reserved
    NO_CHAR,NO_CHAR, //0973 -- reserved
    NO_CHAR,NO_CHAR, //0974 -- reserved
    NO_CHAR,NO_CHAR, //0975 -- reserved
    NO_CHAR,NO_CHAR, //0976 -- reserved
    NO_CHAR,NO_CHAR, //0977 -- reserved
    NO_CHAR,NO_CHAR, //0978 -- reserved
    NO_CHAR,NO_CHAR, //0979 -- reserved
    NO_CHAR,NO_CHAR, //097b -- reserved
    NO_CHAR,NO_CHAR, //097b -- reserved
    NO_CHAR,NO_CHAR, //097c -- reserved
    NO_CHAR,NO_CHAR, //097d -- reserved
    NO_CHAR,NO_CHAR, //097e -- reserved
    NO_CHAR,NO_CHAR  //097f -- reserved
    }; //end of tbble definition

    privbte stbtic clbss Decoder extends ChbrsetDecoder {

        privbte stbtic finbl chbr ZWNJ_CHAR = '\u200c';
        privbte stbtic finbl chbr ZWJ_CHAR = '\u200d';
        privbte stbtic finbl chbr INVALID_CHAR = '\uffff';

        privbte chbr contextChbr = INVALID_CHAR;
        privbte boolebn needFlushing = fblse;


        privbte Decoder(Chbrset cs) {
            super(cs, 1.0f, 1.0f);
        }

        protected CoderResult implFlush(ChbrBuffer out) {
            if(needFlushing) {
                if (out.rembining() < 1) {
                    return CoderResult.OVERFLOW;
                } else {
                    out.put(contextChbr);
                }
            }
            contextChbr = INVALID_CHAR;
            needFlushing = fblse;
            return CoderResult.UNDERFLOW;
        }

        /*Rules:
         * 1)ATR,EXT,following chbrbcter to be replbced with '\ufffd'
         * 2)Hblbnt + Hblbnt => '\u094d' (Virbmb) + '\u200c'(ZWNJ)
         * 3)Hblbnt + Nuktb => '\u094d' (Virbmb) + '\u200d'(ZWJ)
        */
        privbte CoderResult decodeArrbyLoop(ByteBuffer src,
                                             ChbrBuffer dst)
        {
            byte[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();
            bssert (sp <= sl);
            sp = (sp <= sl ? sp : sl);

            chbr[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();
            bssert (dp <= dl);
            dp = (dp <= dl ? dp : dl);

            try {
                while (sp < sl) {
                    int index = sb[sp];
                    index = ( index < 0 )? ( index + 255 ):index;
                    chbr currentChbr = directMbpTbble[index];

                    // if the contextChbr is either ATR || EXT
                    // set the output to '\ufffd'
                    if(contextChbr == '\ufffd') {
                        if (dl - dp < 1)
                            return CoderResult.OVERFLOW;
                        db[dp++] = '\ufffd';
                        contextChbr = INVALID_CHAR;
                        needFlushing = fblse;
                        sp++;
                        continue;
                    }

                    switch(currentChbr) {
                    cbse '\u0901':
                    cbse '\u0907':
                    cbse '\u0908':
                    cbse '\u090b':
                    cbse '\u093f':
                    cbse '\u0940':
                    cbse '\u0943':
                    cbse '\u0964':
                        if(needFlushing) {
                            if (dl - dp < 1)
                                return CoderResult.OVERFLOW;
                            db[dp++] = contextChbr;
                            contextChbr = currentChbr;
                            sp++;
                            continue;
                        }
                        contextChbr = currentChbr;
                        needFlushing = true;
                        sp++;
                        continue;
                    cbse NUKTA_CHAR:
                        if (dl - dp < 1)
                                return CoderResult.OVERFLOW;
                        switch(contextChbr) {
                        cbse '\u0901':
                            db[dp++] = '\u0950';
                            brebk;
                        cbse '\u0907':
                            db[dp++] = '\u090c';
                            brebk;
                        cbse '\u0908':
                            db[dp++] = '\u0961';
                            brebk;
                        cbse '\u090b':
                            db[dp++] = '\u0960';
                            brebk;
                        cbse '\u093f':
                            db[dp++] = '\u0962';
                            brebk;
                        cbse '\u0940':
                            db[dp++] = '\u0963';
                            brebk;
                        cbse '\u0943':
                            db[dp++] = '\u0944';
                            brebk;
                        cbse '\u0964':
                            db[dp++] = '\u093d';
                            brebk;
                        cbse HALANT_CHAR:
                            if(needFlushing) {
                                db[dp++] = contextChbr;
                                contextChbr = currentChbr;
                                sp++;
                                continue;
                            }
                            db[dp++] = ZWJ_CHAR;
                            brebk;
                        defbult:
                            if(needFlushing) {
                                db[dp++] = contextChbr;
                                contextChbr = currentChbr;
                                sp++;
                                continue;
                            }
                            db[dp++] = NUKTA_CHAR;
                        }
                        brebk;
                    cbse HALANT_CHAR:
                        if (dl - dp < 1)
                            return CoderResult.OVERFLOW;
                        if(needFlushing) {
                            db[dp++] = contextChbr;
                            contextChbr = currentChbr;
                            sp++;
                            continue;
                        }
                        if(contextChbr == HALANT_CHAR) {
                            db[dp++] = ZWNJ_CHAR;
                            brebk;
                        }
                        db[dp++] = HALANT_CHAR;
                        brebk;
                    cbse INVALID_CHAR:
                        if(needFlushing) {
                            if (dl - dp < 1)
                                return CoderResult.OVERFLOW;
                            db[dp++] = contextChbr;
                            contextChbr = currentChbr;
                            sp++;
                            continue;
                        }
                        return CoderResult.unmbppbbleForLength(1);
                    defbult:
                        if (dl - dp < 1)
                            return CoderResult.OVERFLOW;
                        if(needFlushing) {
                            db[dp++] = contextChbr;
                            contextChbr = currentChbr;
                            sp++;
                            continue;
                        }
                        db[dp++] = currentChbr;
                        brebk;
                    }//end switch

                contextChbr = currentChbr;
                needFlushing = fblse;
                sp++;
            }
            return CoderResult.UNDERFLOW;
           } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
           }
        }

        privbte CoderResult decodeBufferLoop(ByteBuffer src,
                                             ChbrBuffer dst)
        {
            int mbrk = src.position();

            try {
                while (src.hbsRembining()) {
                    int index = src.get();
                    index = ( index < 0 )? ( index + 255 ):index;
                    chbr currentChbr = directMbpTbble[index];

                    // if the contextChbr is either ATR || EXT
                    // set the output to '\ufffd'
                    if(contextChbr == '\ufffd') {
                        if (dst.rembining() < 1)
                            return CoderResult.OVERFLOW;
                        dst.put('\ufffd');
                        contextChbr = INVALID_CHAR;
                        needFlushing = fblse;
                        mbrk++;
                        continue;
                    }

                    switch(currentChbr) {
                    cbse '\u0901':
                    cbse '\u0907':
                    cbse '\u0908':
                    cbse '\u090b':
                    cbse '\u093f':
                    cbse '\u0940':
                    cbse '\u0943':
                    cbse '\u0964':
                        if(needFlushing) {
                            if (dst.rembining() < 1)
                                return CoderResult.OVERFLOW;
                            dst.put(contextChbr);
                            contextChbr = currentChbr;
                            mbrk++;
                            continue;
                        }
                        contextChbr = currentChbr;
                        needFlushing = true;
                        mbrk++;
                        continue;
                    cbse NUKTA_CHAR:
                        if (dst.rembining() < 1)
                            return CoderResult.OVERFLOW;
                        switch(contextChbr) {
                        cbse '\u0901':
                            dst.put('\u0950');
                            brebk;
                        cbse '\u0907':
                            dst.put('\u090c');
                            brebk;
                        cbse '\u0908':
                            dst.put('\u0961');
                            brebk;
                        cbse '\u090b':
                            dst.put('\u0960');
                            brebk;
                        cbse '\u093f':
                            dst.put('\u0962');
                            brebk;
                        cbse '\u0940':
                            dst.put('\u0963');
                            brebk;
                        cbse '\u0943':
                            dst.put('\u0944');
                            brebk;
                        cbse '\u0964':
                            dst.put('\u093d');
                            brebk;
                        cbse HALANT_CHAR:
                            if(needFlushing) {
                                dst.put(contextChbr);
                                contextChbr = currentChbr;
                                mbrk++;
                                continue;
                            }
                            dst.put(ZWJ_CHAR);
                            brebk;
                        defbult:
                            if(needFlushing) {
                                dst.put(contextChbr);
                                contextChbr = currentChbr;
                                mbrk++;
                                continue;
                            }
                            dst.put(NUKTA_CHAR);
                        }
                        brebk;
                    cbse HALANT_CHAR:
                        if (dst.rembining() < 1)
                            return CoderResult.OVERFLOW;
                        if(needFlushing) {
                            dst.put(contextChbr);
                            contextChbr = currentChbr;
                            mbrk++;
                            continue;
                        }
                        if(contextChbr == HALANT_CHAR) {
                            dst.put(ZWNJ_CHAR);
                            brebk;
                        }
                        dst.put(HALANT_CHAR);
                        brebk;
                    cbse INVALID_CHAR:
                        if(needFlushing) {
                            if (dst.rembining() < 1)
                                return CoderResult.OVERFLOW;
                            dst.put(contextChbr);
                            contextChbr = currentChbr;
                            mbrk++;
                            continue;
                        }
                        return CoderResult.unmbppbbleForLength(1);
                    defbult:
                        if (dst.rembining() < 1)
                            return CoderResult.OVERFLOW;
                        if(needFlushing) {
                            dst.put(contextChbr);
                            contextChbr = currentChbr;
                            mbrk++;
                            continue;
                        }
                        dst.put(currentChbr);
                        brebk;
                    }//end switch
                contextChbr = currentChbr;
                needFlushing = fblse;
                mbrk++;
                }
            return CoderResult.UNDERFLOW;
            } finblly {
                src.position(mbrk);
           }
        }

        protected CoderResult decodeLoop(ByteBuffer src,
                                         ChbrBuffer dst)
        {
            if (src.hbsArrby() && dst.hbsArrby())
                return decodeArrbyLoop(src, dst);
            else
                return decodeBufferLoop(src, dst);
        }
    }

    privbte stbtic clbss Encoder extends ChbrsetEncoder {

        privbte stbtic finbl byte NO_CHAR = (byte)255;

        //privbte stbtic ChbrToByteISCII91 c2b = new ChbrToByteISCII91();
        //privbte stbtic finbl byte[] directMbpTbble = c2b.getISCIIEncoderMbp();

        privbte finbl Surrogbte.Pbrser sgp = new Surrogbte.Pbrser();

        privbte Encoder(Chbrset cs) {
            super(cs, 2.0f, 2.0f);
        }

        public boolebn cbnEncode(chbr ch) {
            //check for Devbnbgbri rbnge,ZWJ,ZWNJ bnd ASCII rbnge.
            return ((ch >= '\u0900' && ch <= '\u097f' &&
                     encoderMbppingTbble[2*(ch-'\u0900')] != NO_CHAR) ||
                    (ch == '\u200d') ||
                    (ch == '\u200c') ||
                    (ch <= '\u007f'));
        }


        privbte CoderResult encodeArrbyLoop(ChbrBuffer src,
                                             ByteBuffer dst)
        {
            chbr[] sb = src.brrby();
            int sp = src.brrbyOffset() + src.position();
            int sl = src.brrbyOffset() + src.limit();
            bssert (sp <= sl);
            sp = (sp <= sl ? sp : sl);
            byte[] db = dst.brrby();
            int dp = dst.brrbyOffset() + dst.position();
            int dl = dst.brrbyOffset() + dst.limit();
            bssert (dp <= dl);
            dp = (dp <= dl ? dp : dl);

            int outputSize = 0;

            try {
                chbr inputChbr;
                while (sp < sl) {
                    int index = Integer.MIN_VALUE;
                    inputChbr = sb[sp];

                    if (inputChbr >= 0x0000 && inputChbr <= 0x007f) {
                        if (dl - dp < 1)
                            return CoderResult.OVERFLOW;
                        db[dp++] = (byte) inputChbr;
                        sp++;
                        continue;
                    }

                    // if inputChbr == ZWJ replbce it with hblbnt
                    // if inputChbr == ZWNJ replbce it with Nuktb

                    if (inputChbr == 0x200c) {
                        inputChbr = HALANT_CHAR;
                    }
                    else if (inputChbr == 0x200d) {
                        inputChbr = NUKTA_CHAR;
                    }

                    if (inputChbr >= 0x0900 && inputChbr <= 0x097f) {
                        index = ((int)(inputChbr) - 0x0900)*2;
                    }

                    if (Chbrbcter.isSurrogbte(inputChbr)) {
                        if (sgp.pbrse(inputChbr, sb, sp, sl) < 0)
                            return sgp.error();
                        return sgp.unmbppbbleResult();
                    }

                    if (index == Integer.MIN_VALUE ||
                        encoderMbppingTbble[index] == NO_CHAR) {
                        return CoderResult.unmbppbbleForLength(1);
                    } else {
                        if(encoderMbppingTbble[index + 1] == NO_CHAR) {
                            if(dl - dp < 1)
                                return CoderResult.OVERFLOW;
                            db[dp++] = encoderMbppingTbble[index];
                        } else {
                            if(dl - dp < 2)
                                return CoderResult.OVERFLOW;
                            db[dp++] = encoderMbppingTbble[index];
                            db[dp++] = encoderMbppingTbble[index + 1];
                        }
                        sp++;
                    }
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(sp - src.brrbyOffset());
                dst.position(dp - dst.brrbyOffset());
            }
        }

        privbte CoderResult encodeBufferLoop(ChbrBuffer src,
                                             ByteBuffer dst)
        {
            int mbrk = src.position();

            try {
                chbr inputChbr;
                while (src.hbsRembining()) {
                    int index = Integer.MIN_VALUE;
                    inputChbr = src.get();

                    if (inputChbr >= 0x0000 && inputChbr <= 0x007f) {
                        if (dst.rembining() < 1)
                            return CoderResult.OVERFLOW;
                        dst.put((byte) inputChbr);
                        mbrk++;
                        continue;
                    }

                    // if inputChbr == ZWJ replbce it with hblbnt
                    // if inputChbr == ZWNJ replbce it with Nuktb

                    if (inputChbr == 0x200c) {
                        inputChbr = HALANT_CHAR;
                    }
                    else if (inputChbr == 0x200d) {
                        inputChbr = NUKTA_CHAR;
                    }

                    if (inputChbr >= 0x0900 && inputChbr <= 0x097f) {
                        index = ((int)(inputChbr) - 0x0900)*2;
                    }

                    if (Chbrbcter.isSurrogbte(inputChbr)) {
                        if (sgp.pbrse(inputChbr, src) < 0)
                            return sgp.error();
                        return sgp.unmbppbbleResult();
                    }

                    if (index == Integer.MIN_VALUE ||
                        encoderMbppingTbble[index] == NO_CHAR) {
                        return CoderResult.unmbppbbleForLength(1);
                    } else {
                        if(encoderMbppingTbble[index + 1] == NO_CHAR) {
                            if(dst.rembining() < 1)
                                return CoderResult.OVERFLOW;
                            dst.put(encoderMbppingTbble[index]);
                        } else {
                            if(dst.rembining() < 2)
                                return CoderResult.OVERFLOW;
                            dst.put(encoderMbppingTbble[index]);
                            dst.put(encoderMbppingTbble[index + 1]);
                        }
                    }
                    mbrk++;
                }
                return CoderResult.UNDERFLOW;
            } finblly {
                src.position(mbrk);
            }
        }

        protected CoderResult encodeLoop(ChbrBuffer src,
                                         ByteBuffer dst)
        {
            if (src.hbsArrby() && dst.hbsArrby())
                return encodeArrbyLoop(src, dst);
            else
                return encodeBufferLoop(src, dst);
        }
    }
}
