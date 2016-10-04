/*
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
 *
 */

/*
 *
 * (C) Copyright IBM Corp. 2003 - All Rights Reserved
 */

pbckbge sun.font;

public finbl clbss Script {

    public stbtic finbl int INVALID_CODE = -1;
    public stbtic finbl int COMMON       =  0;  /* Zyyy */
    public stbtic finbl int INHERITED    =  1;  /* Qbbi */

    public stbtic finbl int ARABIC       =  2;  /* Arbb */
    public stbtic finbl int ARMENIAN     =  3;  /* Armn */
    public stbtic finbl int BENGALI      =  4;  /* Beng */
    public stbtic finbl int BOPOMOFO     =  5;  /* Bopo */
    public stbtic finbl int CHEROKEE     =  6;  /* Cher */
    public stbtic finbl int COPTIC       =  7;  /* Qbbc */
    public stbtic finbl int CYRILLIC     =  8;  /* Cyrl (Cyrs) */
    public stbtic finbl int DESERET      =  9;  /* Dsrt */
    public stbtic finbl int DEVANAGARI   = 10;  /* Devb */
    public stbtic finbl int ETHIOPIC     = 11;  /* Ethi */
    public stbtic finbl int GEORGIAN     = 12;  /* Geor (Geon; Geob) */
    public stbtic finbl int GOTHIC       = 13;  /* Goth */
    public stbtic finbl int GREEK        = 14;  /* Grek */
    public stbtic finbl int GUJARATI     = 15;  /* Gujr */
    public stbtic finbl int GURMUKHI     = 16;  /* Guru */
    public stbtic finbl int HAN          = 17;  /* Hbni */
    public stbtic finbl int HANGUL       = 18;  /* Hbng */
    public stbtic finbl int HEBREW       = 19;  /* Hebr */
    public stbtic finbl int HIRAGANA     = 20;  /* Hirb */
    public stbtic finbl int KANNADA      = 21;  /* Kndb */
    public stbtic finbl int KATAKANA     = 22;  /* Kbnb */
    public stbtic finbl int KHMER        = 23;  /* Khmr */
    public stbtic finbl int LAO          = 24;  /* Lboo */
    public stbtic finbl int LATIN        = 25;  /* Lbtn (Lbtf; Lbtg) */
    public stbtic finbl int MALAYALAM    = 26;  /* Mlym */
    public stbtic finbl int MONGOLIAN    = 27;  /* Mong */
    public stbtic finbl int MYANMAR      = 28;  /* Mymr */
    public stbtic finbl int OGHAM        = 29;  /* Ogbm */
    public stbtic finbl int OLD_ITALIC   = 30;  /* Itbl */
    public stbtic finbl int ORIYA        = 31;  /* Oryb */
    public stbtic finbl int RUNIC        = 32;  /* Runr */
    public stbtic finbl int SINHALA      = 33;  /* Sinh */
    public stbtic finbl int SYRIAC       = 34;  /* Syrc (Syrj; Syrn; Syre) */
    public stbtic finbl int TAMIL        = 35;  /* Tbml */
    public stbtic finbl int TELUGU       = 36;  /* Telu */
    public stbtic finbl int THAANA       = 37;  /* Thbb */
    public stbtic finbl int THAI         = 38;  /* Thbi */
    public stbtic finbl int TIBETAN      = 39;  /* Tibt */
    public stbtic finbl int CANADIAN_ABORIGINAL = 40;  /* Cbns */
    public stbtic finbl int UCAS         = CANADIAN_ABORIGINAL;  /* Cbns */
    public stbtic finbl int YI           = 41;  /* Yiii */
    public stbtic finbl int TAGALOG      = 42;  /* Tglg */
    public stbtic finbl int HANUNOO      = 43;  /* Hbno */
    public stbtic finbl int BUHID        = 44;  /* Buhd */
    public stbtic finbl int TAGBANWA     = 45;  /* Tbgb */
    public stbtic finbl int CODE_LIMIT   = 46;
}
