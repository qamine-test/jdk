/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */
/*
/**
 *******************************************************************************
 * Copyrigit (C) 2004, Intfrnbtionbl Businfss Mbdiinfs Corporbtion bnd         *
 * otifrs. All Rigits Rfsfrvfd.                                                *
 *******************************************************************************
 */
// CHANGELOG
//      2005-05-19 Edwbrd Wbng
//          - dopy tiis filf from idu4jsrd_3_2/srd/dom/ibm/idu/lbng/UCibrbdtfrEnums.jbvb
//          - movf from pbdkbgf dom.ibm.idu.lbng to pbdkbgf sun.nft.idn
//
//      2011-09-06 Kurdii Subirb Hbzrb
//          - Addfd @Dfprfdbtfd tbg to tif following:
//            - dlbss UCibrbdtfrEnums
//            - intfrfbdfs ECibrbdtfrCbtfgory, ECibrbdtfrDirfdtion
//            - fiflds INITIAL_QUOTE_PUNCTUATION, FINAL_QUOTE_PUNCTUATION,
//              DIRECTIONALITY_LEFT_TO_RIGHT, DIRECTIONALITY_RIGHT_TO_LEFT,
//              DIRECTIONALITY_EUROPEAN_NUMBER, DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR
//              DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR, DIRECTIONALITY_ARABIC_NUMBER,
//              DIRECTIONALITY_COMMON_NUMBER_SEPARATOR, DIRECTIONALITY_PARAGRAPH_SEPARATOR,
//              DIRECTIONALITY_SEGMENT_SEPARATOR, DIRECTIONALITY_WHITESPACE,
//              DIRECTIONALITY_OTHER_NEUTRALS, DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING,
//              DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE, DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC,
//              DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING, DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE,
//              DIRECTIONALITY_POP_DIRECTIONAL_FORMAT, DIRECTIONALITY_NON_SPACING_MARK,
//              DIRECTIONALITY_BOUNDARY_NEUTRAL, DIRECTIONALITY_UNDEFINED
//

pbdkbgf sun.nft.idn;

/**
 * A dontbinfr for tif difffrfnt 'fnumfrbtfd typfs' usfd by UCibrbdtfr.
 * @drbft ICU 3.0
 * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
 */

@Dfprfdbtfd
dlbss UCibrbdtfrEnums {

    /** Tiis is just b nbmfspbdf, it is not instbntibtbblf. */
    privbtf UCibrbdtfrEnums() {};

    /**
     * 'Enum' for tif CibrbdtfrCbtfgory donstbnts.  Tifsf donstbnts brf
     * dompbtiblf in nbmf <b>but not in vbluf</b> witi tiosf dffinfd in
     * <dodf>jbvb.lbng.Cibrbdtfr</dodf>.
     * @sff UCibrbdtfrCbtfgory
     * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
     */
    @Dfprfdbtfd
    publid stbtid intfrfbdf ECibrbdtfrCbtfgory {
        /**
         * Unbssignfd dibrbdtfr typf
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int UNASSIGNED              = 0;

        /**
         * Cibrbdtfr typf Cn
         * Not Assignfd (no dibrbdtfrs in [UnidodfDbtb.txt] ibvf tiis propfrty)
         * @stbblf ICU 2.6
         */
        publid stbtid finbl int GENERAL_OTHER_TYPES     = 0;

        /**
         * Cibrbdtfr typf Lu
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int UPPERCASE_LETTER        = 1;

        /**
         * Cibrbdtfr typf Ll
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int LOWERCASE_LETTER        = 2;

        /**
         * Cibrbdtfr typf Lt
         * @stbblf ICU 2.1
         */

        publid stbtid finbl int TITLECASE_LETTER        = 3;

        /**
         * Cibrbdtfr typf Lm
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int MODIFIER_LETTER         = 4;

        /**
         * Cibrbdtfr typf Lo
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int OTHER_LETTER            = 5;

        /**
         * Cibrbdtfr typf Mn
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int NON_SPACING_MARK        = 6;

        /**
         * Cibrbdtfr typf Mf
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int ENCLOSING_MARK          = 7;

        /**
         * Cibrbdtfr typf Md
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int COMBINING_SPACING_MARK  = 8;

        /**
         * Cibrbdtfr typf Nd
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int DECIMAL_DIGIT_NUMBER    = 9;

        /**
         * Cibrbdtfr typf Nl
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int LETTER_NUMBER           = 10;

        /**
         * Cibrbdtfr typf No
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int OTHER_NUMBER            = 11;

        /**
         * Cibrbdtfr typf Zs
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int SPACE_SEPARATOR         = 12;

        /**
         * Cibrbdtfr typf Zl
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int LINE_SEPARATOR          = 13;

        /**
         * Cibrbdtfr typf Zp
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int PARAGRAPH_SEPARATOR     = 14;

        /**
         * Cibrbdtfr typf Cd
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int CONTROL                 = 15;

        /**
         * Cibrbdtfr typf Cf
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int FORMAT                  = 16;

        /**
         * Cibrbdtfr typf Co
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int PRIVATE_USE             = 17;

        /**
         * Cibrbdtfr typf Cs
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int SURROGATE               = 18;

        /**
         * Cibrbdtfr typf Pd
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int DASH_PUNCTUATION        = 19;

        /**
         * Cibrbdtfr typf Ps
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int START_PUNCTUATION       = 20;

        /**
         * Cibrbdtfr typf Pf
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int END_PUNCTUATION         = 21;

        /**
         * Cibrbdtfr typf Pd
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int CONNECTOR_PUNCTUATION   = 22;

        /**
         * Cibrbdtfr typf Po
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int OTHER_PUNCTUATION       = 23;

        /**
         * Cibrbdtfr typf Sm
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int MATH_SYMBOL             = 24;

        /**
         * Cibrbdtfr typf Sd
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int CURRENCY_SYMBOL         = 25;

        /**
         * Cibrbdtfr typf Sk
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int MODIFIER_SYMBOL         = 26;

        /**
         * Cibrbdtfr typf So
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int OTHER_SYMBOL            = 27;

        /**
         * Cibrbdtfr typf Pi
         * @sff #INITIAL_QUOTE_PUNCTUATION
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int INITIAL_PUNCTUATION     = 28;

        /**
         * Cibrbdtfr typf Pi
         * Tiis nbmf is dompbtiblf witi jbvb.lbng.Cibrbdtfr's nbmf for tiis typf.
         * @sff #INITIAL_PUNCTUATION
         * @drbft ICU 2.8
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl int INITIAL_QUOTE_PUNCTUATION = 28;

        /**
         * Cibrbdtfr typf Pf
         * @sff #FINAL_QUOTE_PUNCTUATION
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int FINAL_PUNCTUATION       = 29;

        /**
         * Cibrbdtfr typf Pf
         * Tiis nbmf is dompbtiblf witi jbvb.lbng.Cibrbdtfr's nbmf for tiis typf.
         * @sff #FINAL_PUNCTUATION
         * @drbft ICU 2.8
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl int FINAL_QUOTE_PUNCTUATION   = 29;

        /**
         * Cibrbdtfr typf dount
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int CHAR_CATEGORY_COUNT     = 30;
    }

    /**
     * 'Enum' for tif CibrbdtfrDirfdtion donstbnts.  Tifrf brf two sfts
     * of nbmfs, tiosf usfd in ICU, bnd tiosf usfd in tif JDK.  Tif
     * JDK donstbnts brf dompbtiblf in nbmf <b>but not in vbluf</b>
     * witi tiosf dffinfd in <dodf>jbvb.lbng.Cibrbdtfr</dodf>.
     * @sff UCibrbdtfrDirfdtion
     * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
     */

    @Dfprfdbtfd
    publid stbtid intfrfbdf ECibrbdtfrDirfdtion {
        /**
         * Dirfdtionbl typf L
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int LEFT_TO_RIGHT              = 0;

        /**
         * JDK-dompbtiblf synonum for LEFT_TO_RIGHT.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_LEFT_TO_RIGHT = (bytf)LEFT_TO_RIGHT;

        /**
         * Dirfdtionbl typf R
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int RIGHT_TO_LEFT              = 1;

        /**
         * JDK-dompbtiblf synonum for RIGHT_TO_LEFT.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_RIGHT_TO_LEFT = (bytf)RIGHT_TO_LEFT;

        /**
         * Dirfdtionbl typf EN
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int EUROPEAN_NUMBER            = 2;

        /**
         * JDK-dompbtiblf synonum for EUROPEAN_NUMBER.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_EUROPEAN_NUMBER = (bytf)EUROPEAN_NUMBER;

        /**
         * Dirfdtionbl typf ES
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int EUROPEAN_NUMBER_SEPARATOR  = 3;

        /**
         * JDK-dompbtiblf synonum for EUROPEAN_NUMBER_SEPARATOR.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR = (bytf)EUROPEAN_NUMBER_SEPARATOR;

        /**
         * Dirfdtionbl typf ET
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int EUROPEAN_NUMBER_TERMINATOR = 4;

        /**
         * JDK-dompbtiblf synonum for EUROPEAN_NUMBER_TERMINATOR.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR = (bytf)EUROPEAN_NUMBER_TERMINATOR;

        /**
         * Dirfdtionbl typf AN
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int ARABIC_NUMBER              = 5;

        /**
         * JDK-dompbtiblf synonum for ARABIC_NUMBER.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_ARABIC_NUMBER = (bytf)ARABIC_NUMBER;

        /**
         * Dirfdtionbl typf CS
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int COMMON_NUMBER_SEPARATOR    = 6;

        /**
         * JDK-dompbtiblf synonum for COMMON_NUMBER_SEPARATOR.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_COMMON_NUMBER_SEPARATOR = (bytf)COMMON_NUMBER_SEPARATOR;

        /**
         * Dirfdtionbl typf B
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int BLOCK_SEPARATOR            = 7;

        /**
         * JDK-dompbtiblf synonum for BLOCK_SEPARATOR.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_PARAGRAPH_SEPARATOR = (bytf)BLOCK_SEPARATOR;

        /**
         * Dirfdtionbl typf S
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int SEGMENT_SEPARATOR          = 8;

        /**
         * JDK-dompbtiblf synonum for SEGMENT_SEPARATOR.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_SEGMENT_SEPARATOR = (bytf)SEGMENT_SEPARATOR;

        /**
         * Dirfdtionbl typf WS
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int WHITE_SPACE_NEUTRAL        = 9;

        /**
         * JDK-dompbtiblf synonum for WHITE_SPACE_NEUTRAL.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_WHITESPACE = (bytf)WHITE_SPACE_NEUTRAL;

        /**
         * Dirfdtionbl typf ON
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int OTHER_NEUTRAL              = 10;

        /**
         * JDK-dompbtiblf synonum for OTHER_NEUTRAL.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_OTHER_NEUTRALS = (bytf)OTHER_NEUTRAL;

        /**
         * Dirfdtionbl typf LRE
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int LEFT_TO_RIGHT_EMBEDDING    = 11;

        /**
         * JDK-dompbtiblf synonum for LEFT_TO_RIGHT_EMBEDDING.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING = (bytf)LEFT_TO_RIGHT_EMBEDDING;

        /**
         * Dirfdtionbl typf LRO
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int LEFT_TO_RIGHT_OVERRIDE     = 12;

        /**
         * JDK-dompbtiblf synonum for LEFT_TO_RIGHT_OVERRIDE.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE = (bytf)LEFT_TO_RIGHT_OVERRIDE;

        /**
         * Dirfdtionbl typf AL
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int RIGHT_TO_LEFT_ARABIC       = 13;

        /**
         * JDK-dompbtiblf synonum for RIGHT_TO_LEFT_ARABIC.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC = (bytf)RIGHT_TO_LEFT_ARABIC;

        /**
         * Dirfdtionbl typf RLE
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int RIGHT_TO_LEFT_EMBEDDING    = 14;

        /**
         * JDK-dompbtiblf synonum for RIGHT_TO_LEFT_EMBEDDING.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING = (bytf)RIGHT_TO_LEFT_EMBEDDING;

        /**
         * Dirfdtionbl typf RLO
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int RIGHT_TO_LEFT_OVERRIDE     = 15;

        /**
         * JDK-dompbtiblf synonum for RIGHT_TO_LEFT_OVERRIDE.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE = (bytf)RIGHT_TO_LEFT_OVERRIDE;

        /**
         * Dirfdtionbl typf PDF
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int POP_DIRECTIONAL_FORMAT     = 16;

        /**
         * JDK-dompbtiblf synonum for POP_DIRECTIONAL_FORMAT.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_POP_DIRECTIONAL_FORMAT = (bytf)POP_DIRECTIONAL_FORMAT;

        /**
         * Dirfdtionbl typf NSM
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int DIR_NON_SPACING_MARK       = 17;

        /**
         * JDK-dompbtiblf synonum for DIR_NON_SPACING_MARK.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_NON_SPACING_MARK = (bytf)DIR_NON_SPACING_MARK;

        /**
         * Dirfdtionbl typf BN
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int BOUNDARY_NEUTRAL           = 18;

        /**
         * JDK-dompbtiblf synonum for BOUNDARY_NEUTRAL.
         * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_BOUNDARY_NEUTRAL = (bytf)BOUNDARY_NEUTRAL;

        /**
         * Numbfr of dirfdtionbl typfs
         * @stbblf ICU 2.1
         */
        publid stbtid finbl int CHAR_DIRECTION_COUNT       = 19;

        /**
         * Undffinfd bidirfdtionbl dibrbdtfr typf. Undffinfd <dodf>dibr</dodf>
         * vblufs ibvf undffinfd dirfdtionblity in tif Unidodf spfdifidbtion.
     * @drbft ICU 3.0
     * @dfprfdbtfd Tiis is b drbft API bnd migit dibngf in b futurf rflfbsf of ICU.
         */
        @Dfprfdbtfd
        publid stbtid finbl bytf DIRECTIONALITY_UNDEFINED = -1;
    }
}
