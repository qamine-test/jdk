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
 */
/*
/**
 *******************************************************************************
 * Copyright (C) 2004, Internbtionbl Business Mbchines Corporbtion bnd         *
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */
// CHANGELOG
//      2005-05-19 Edwbrd Wbng
//          - copy this file from icu4jsrc_3_2/src/com/ibm/icu/lbng/UChbrbcterEnums.jbvb
//          - move from pbckbge com.ibm.icu.lbng to pbckbge sun.net.idn
//
//      2011-09-06 Kurchi Subhrb Hbzrb
//          - Added @Deprecbted tbg to the following:
//            - clbss UChbrbcterEnums
//            - interfbces EChbrbcterCbtegory, EChbrbcterDirection
//            - fields INITIAL_QUOTE_PUNCTUATION, FINAL_QUOTE_PUNCTUATION,
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

pbckbge sun.net.idn;

/**
 * A contbiner for the different 'enumerbted types' used by UChbrbcter.
 * @drbft ICU 3.0
 * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
 */

@Deprecbted
clbss UChbrbcterEnums {

    /** This is just b nbmespbce, it is not instbntibtbble. */
    privbte UChbrbcterEnums() {};

    /**
     * 'Enum' for the ChbrbcterCbtegory constbnts.  These constbnts bre
     * compbtible in nbme <b>but not in vblue</b> with those defined in
     * <code>jbvb.lbng.Chbrbcter</code>.
     * @see UChbrbcterCbtegory
     * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
     */
    @Deprecbted
    public stbtic interfbce EChbrbcterCbtegory {
        /**
         * Unbssigned chbrbcter type
         * @stbble ICU 2.1
         */
        public stbtic finbl int UNASSIGNED              = 0;

        /**
         * Chbrbcter type Cn
         * Not Assigned (no chbrbcters in [UnicodeDbtb.txt] hbve this property)
         * @stbble ICU 2.6
         */
        public stbtic finbl int GENERAL_OTHER_TYPES     = 0;

        /**
         * Chbrbcter type Lu
         * @stbble ICU 2.1
         */
        public stbtic finbl int UPPERCASE_LETTER        = 1;

        /**
         * Chbrbcter type Ll
         * @stbble ICU 2.1
         */
        public stbtic finbl int LOWERCASE_LETTER        = 2;

        /**
         * Chbrbcter type Lt
         * @stbble ICU 2.1
         */

        public stbtic finbl int TITLECASE_LETTER        = 3;

        /**
         * Chbrbcter type Lm
         * @stbble ICU 2.1
         */
        public stbtic finbl int MODIFIER_LETTER         = 4;

        /**
         * Chbrbcter type Lo
         * @stbble ICU 2.1
         */
        public stbtic finbl int OTHER_LETTER            = 5;

        /**
         * Chbrbcter type Mn
         * @stbble ICU 2.1
         */
        public stbtic finbl int NON_SPACING_MARK        = 6;

        /**
         * Chbrbcter type Me
         * @stbble ICU 2.1
         */
        public stbtic finbl int ENCLOSING_MARK          = 7;

        /**
         * Chbrbcter type Mc
         * @stbble ICU 2.1
         */
        public stbtic finbl int COMBINING_SPACING_MARK  = 8;

        /**
         * Chbrbcter type Nd
         * @stbble ICU 2.1
         */
        public stbtic finbl int DECIMAL_DIGIT_NUMBER    = 9;

        /**
         * Chbrbcter type Nl
         * @stbble ICU 2.1
         */
        public stbtic finbl int LETTER_NUMBER           = 10;

        /**
         * Chbrbcter type No
         * @stbble ICU 2.1
         */
        public stbtic finbl int OTHER_NUMBER            = 11;

        /**
         * Chbrbcter type Zs
         * @stbble ICU 2.1
         */
        public stbtic finbl int SPACE_SEPARATOR         = 12;

        /**
         * Chbrbcter type Zl
         * @stbble ICU 2.1
         */
        public stbtic finbl int LINE_SEPARATOR          = 13;

        /**
         * Chbrbcter type Zp
         * @stbble ICU 2.1
         */
        public stbtic finbl int PARAGRAPH_SEPARATOR     = 14;

        /**
         * Chbrbcter type Cc
         * @stbble ICU 2.1
         */
        public stbtic finbl int CONTROL                 = 15;

        /**
         * Chbrbcter type Cf
         * @stbble ICU 2.1
         */
        public stbtic finbl int FORMAT                  = 16;

        /**
         * Chbrbcter type Co
         * @stbble ICU 2.1
         */
        public stbtic finbl int PRIVATE_USE             = 17;

        /**
         * Chbrbcter type Cs
         * @stbble ICU 2.1
         */
        public stbtic finbl int SURROGATE               = 18;

        /**
         * Chbrbcter type Pd
         * @stbble ICU 2.1
         */
        public stbtic finbl int DASH_PUNCTUATION        = 19;

        /**
         * Chbrbcter type Ps
         * @stbble ICU 2.1
         */
        public stbtic finbl int START_PUNCTUATION       = 20;

        /**
         * Chbrbcter type Pe
         * @stbble ICU 2.1
         */
        public stbtic finbl int END_PUNCTUATION         = 21;

        /**
         * Chbrbcter type Pc
         * @stbble ICU 2.1
         */
        public stbtic finbl int CONNECTOR_PUNCTUATION   = 22;

        /**
         * Chbrbcter type Po
         * @stbble ICU 2.1
         */
        public stbtic finbl int OTHER_PUNCTUATION       = 23;

        /**
         * Chbrbcter type Sm
         * @stbble ICU 2.1
         */
        public stbtic finbl int MATH_SYMBOL             = 24;

        /**
         * Chbrbcter type Sc
         * @stbble ICU 2.1
         */
        public stbtic finbl int CURRENCY_SYMBOL         = 25;

        /**
         * Chbrbcter type Sk
         * @stbble ICU 2.1
         */
        public stbtic finbl int MODIFIER_SYMBOL         = 26;

        /**
         * Chbrbcter type So
         * @stbble ICU 2.1
         */
        public stbtic finbl int OTHER_SYMBOL            = 27;

        /**
         * Chbrbcter type Pi
         * @see #INITIAL_QUOTE_PUNCTUATION
         * @stbble ICU 2.1
         */
        public stbtic finbl int INITIAL_PUNCTUATION     = 28;

        /**
         * Chbrbcter type Pi
         * This nbme is compbtible with jbvb.lbng.Chbrbcter's nbme for this type.
         * @see #INITIAL_PUNCTUATION
         * @drbft ICU 2.8
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl int INITIAL_QUOTE_PUNCTUATION = 28;

        /**
         * Chbrbcter type Pf
         * @see #FINAL_QUOTE_PUNCTUATION
         * @stbble ICU 2.1
         */
        public stbtic finbl int FINAL_PUNCTUATION       = 29;

        /**
         * Chbrbcter type Pf
         * This nbme is compbtible with jbvb.lbng.Chbrbcter's nbme for this type.
         * @see #FINAL_PUNCTUATION
         * @drbft ICU 2.8
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl int FINAL_QUOTE_PUNCTUATION   = 29;

        /**
         * Chbrbcter type count
         * @stbble ICU 2.1
         */
        public stbtic finbl int CHAR_CATEGORY_COUNT     = 30;
    }

    /**
     * 'Enum' for the ChbrbcterDirection constbnts.  There bre two sets
     * of nbmes, those used in ICU, bnd those used in the JDK.  The
     * JDK constbnts bre compbtible in nbme <b>but not in vblue</b>
     * with those defined in <code>jbvb.lbng.Chbrbcter</code>.
     * @see UChbrbcterDirection
     * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
     */

    @Deprecbted
    public stbtic interfbce EChbrbcterDirection {
        /**
         * Directionbl type L
         * @stbble ICU 2.1
         */
        public stbtic finbl int LEFT_TO_RIGHT              = 0;

        /**
         * JDK-compbtible synonum for LEFT_TO_RIGHT.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_LEFT_TO_RIGHT = (byte)LEFT_TO_RIGHT;

        /**
         * Directionbl type R
         * @stbble ICU 2.1
         */
        public stbtic finbl int RIGHT_TO_LEFT              = 1;

        /**
         * JDK-compbtible synonum for RIGHT_TO_LEFT.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_RIGHT_TO_LEFT = (byte)RIGHT_TO_LEFT;

        /**
         * Directionbl type EN
         * @stbble ICU 2.1
         */
        public stbtic finbl int EUROPEAN_NUMBER            = 2;

        /**
         * JDK-compbtible synonum for EUROPEAN_NUMBER.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_EUROPEAN_NUMBER = (byte)EUROPEAN_NUMBER;

        /**
         * Directionbl type ES
         * @stbble ICU 2.1
         */
        public stbtic finbl int EUROPEAN_NUMBER_SEPARATOR  = 3;

        /**
         * JDK-compbtible synonum for EUROPEAN_NUMBER_SEPARATOR.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR = (byte)EUROPEAN_NUMBER_SEPARATOR;

        /**
         * Directionbl type ET
         * @stbble ICU 2.1
         */
        public stbtic finbl int EUROPEAN_NUMBER_TERMINATOR = 4;

        /**
         * JDK-compbtible synonum for EUROPEAN_NUMBER_TERMINATOR.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR = (byte)EUROPEAN_NUMBER_TERMINATOR;

        /**
         * Directionbl type AN
         * @stbble ICU 2.1
         */
        public stbtic finbl int ARABIC_NUMBER              = 5;

        /**
         * JDK-compbtible synonum for ARABIC_NUMBER.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_ARABIC_NUMBER = (byte)ARABIC_NUMBER;

        /**
         * Directionbl type CS
         * @stbble ICU 2.1
         */
        public stbtic finbl int COMMON_NUMBER_SEPARATOR    = 6;

        /**
         * JDK-compbtible synonum for COMMON_NUMBER_SEPARATOR.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_COMMON_NUMBER_SEPARATOR = (byte)COMMON_NUMBER_SEPARATOR;

        /**
         * Directionbl type B
         * @stbble ICU 2.1
         */
        public stbtic finbl int BLOCK_SEPARATOR            = 7;

        /**
         * JDK-compbtible synonum for BLOCK_SEPARATOR.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_PARAGRAPH_SEPARATOR = (byte)BLOCK_SEPARATOR;

        /**
         * Directionbl type S
         * @stbble ICU 2.1
         */
        public stbtic finbl int SEGMENT_SEPARATOR          = 8;

        /**
         * JDK-compbtible synonum for SEGMENT_SEPARATOR.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_SEGMENT_SEPARATOR = (byte)SEGMENT_SEPARATOR;

        /**
         * Directionbl type WS
         * @stbble ICU 2.1
         */
        public stbtic finbl int WHITE_SPACE_NEUTRAL        = 9;

        /**
         * JDK-compbtible synonum for WHITE_SPACE_NEUTRAL.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_WHITESPACE = (byte)WHITE_SPACE_NEUTRAL;

        /**
         * Directionbl type ON
         * @stbble ICU 2.1
         */
        public stbtic finbl int OTHER_NEUTRAL              = 10;

        /**
         * JDK-compbtible synonum for OTHER_NEUTRAL.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_OTHER_NEUTRALS = (byte)OTHER_NEUTRAL;

        /**
         * Directionbl type LRE
         * @stbble ICU 2.1
         */
        public stbtic finbl int LEFT_TO_RIGHT_EMBEDDING    = 11;

        /**
         * JDK-compbtible synonum for LEFT_TO_RIGHT_EMBEDDING.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING = (byte)LEFT_TO_RIGHT_EMBEDDING;

        /**
         * Directionbl type LRO
         * @stbble ICU 2.1
         */
        public stbtic finbl int LEFT_TO_RIGHT_OVERRIDE     = 12;

        /**
         * JDK-compbtible synonum for LEFT_TO_RIGHT_OVERRIDE.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE = (byte)LEFT_TO_RIGHT_OVERRIDE;

        /**
         * Directionbl type AL
         * @stbble ICU 2.1
         */
        public stbtic finbl int RIGHT_TO_LEFT_ARABIC       = 13;

        /**
         * JDK-compbtible synonum for RIGHT_TO_LEFT_ARABIC.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC = (byte)RIGHT_TO_LEFT_ARABIC;

        /**
         * Directionbl type RLE
         * @stbble ICU 2.1
         */
        public stbtic finbl int RIGHT_TO_LEFT_EMBEDDING    = 14;

        /**
         * JDK-compbtible synonum for RIGHT_TO_LEFT_EMBEDDING.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING = (byte)RIGHT_TO_LEFT_EMBEDDING;

        /**
         * Directionbl type RLO
         * @stbble ICU 2.1
         */
        public stbtic finbl int RIGHT_TO_LEFT_OVERRIDE     = 15;

        /**
         * JDK-compbtible synonum for RIGHT_TO_LEFT_OVERRIDE.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE = (byte)RIGHT_TO_LEFT_OVERRIDE;

        /**
         * Directionbl type PDF
         * @stbble ICU 2.1
         */
        public stbtic finbl int POP_DIRECTIONAL_FORMAT     = 16;

        /**
         * JDK-compbtible synonum for POP_DIRECTIONAL_FORMAT.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_POP_DIRECTIONAL_FORMAT = (byte)POP_DIRECTIONAL_FORMAT;

        /**
         * Directionbl type NSM
         * @stbble ICU 2.1
         */
        public stbtic finbl int DIR_NON_SPACING_MARK       = 17;

        /**
         * JDK-compbtible synonum for DIR_NON_SPACING_MARK.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_NON_SPACING_MARK = (byte)DIR_NON_SPACING_MARK;

        /**
         * Directionbl type BN
         * @stbble ICU 2.1
         */
        public stbtic finbl int BOUNDARY_NEUTRAL           = 18;

        /**
         * JDK-compbtible synonum for BOUNDARY_NEUTRAL.
         * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_BOUNDARY_NEUTRAL = (byte)BOUNDARY_NEUTRAL;

        /**
         * Number of directionbl types
         * @stbble ICU 2.1
         */
        public stbtic finbl int CHAR_DIRECTION_COUNT       = 19;

        /**
         * Undefined bidirectionbl chbrbcter type. Undefined <code>chbr</code>
         * vblues hbve undefined directionblity in the Unicode specificbtion.
     * @drbft ICU 3.0
     * @deprecbted This is b drbft API bnd might chbnge in b future relebse of ICU.
         */
        @Deprecbted
        public stbtic finbl byte DIRECTIONALITY_UNDEFINED = -1;
    }
}
