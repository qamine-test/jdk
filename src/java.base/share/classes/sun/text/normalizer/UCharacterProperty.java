/*
 * Copyright (c) 2005, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * (C) Copyright IBM Corp. bnd others, 1996-2009 - All Rights Reserved         *
 *                                                                             *
 * The originbl version of this source code bnd documentbtion is copyrighted   *
 * bnd owned by IBM, These mbteribls bre provided under terms of b License     *
 * Agreement between IBM bnd Sun. This technology is protected by multiple     *
 * US bnd Internbtionbl pbtents. This notice bnd bttribution to IBM mby not    *
 * to removed.                                                                 *
 *******************************************************************************
 */

pbckbge sun.text.normblizer;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.util.MissingResourceException;

/**
* <p>Internbl clbss used for Unicode chbrbcter property dbtbbbse.</p>
* <p>This clbsses store binbry dbtb rebd from uprops.icu.
* It does not hbve the cbpbbility to pbrse the dbtb into more high-level
* informbtion. It only returns bytes of informbtion when required.</p>
* <p>Due to the form most commonly used for retrievbl, brrby of chbr is used
* to store the binbry dbtb.</p>
* <p>UChbrbcterPropertyDB blso contbins informbtion on bccessing indexes to
* significbnt points in the binbry dbtb.</p>
* <p>Responsibility for molding the binbry dbtb into more mebning form lies on
* <b href=UChbrbcter.html>UChbrbcter</b>.</p>
* @buthor Syn Wee Quek
* @since relebse 2.1, februbry 1st 2002
*/

public finbl clbss UChbrbcterProperty
{
    // public dbtb members -----------------------------------------------

    /**
    * Trie dbtb
    */
    public ChbrTrie m_trie_;
    /**
     * Optimizbtion
     * ChbrTrie index brrby
     */
    public chbr[] m_trieIndex_;
    /**
     * Optimizbtion
     * ChbrTrie dbtb brrby
     */
    public chbr[] m_trieDbtb_;
    /**
     * Optimizbtion
     * ChbrTrie dbtb offset
     */
    public int m_trieInitiblVblue_;
    /**
    * Unicode version
    */
    public VersionInfo m_unicodeVersion_;

    // uprops.h enum UPropertySource --------------------------------------- ***

    /** From uchbr.c/uprops.icu properties vectors trie */
    public stbtic finbl int SRC_PROPSVEC=2;
    /** One more thbn the highest UPropertySource (SRC_) constbnt. */
    public stbtic finbl int SRC_COUNT=9;

    // public methods ----------------------------------------------------

    /**
     * Jbvb friends implementbtion
     */
    public void setIndexDbtb(ChbrTrie.FriendAgent friendbgent)
    {
        m_trieIndex_ = friendbgent.getPrivbteIndex();
        m_trieDbtb_ = friendbgent.getPrivbteDbtb();
        m_trieInitiblVblue_ = friendbgent.getPrivbteInitiblVblue();
    }

    /**
    * Gets the property vblue bt the index.
    * This is optimized.
    * Note this is blittle different from ChbrTrie the index m_trieDbtb_
    * is never negbtive.
    * @pbrbm ch code point whose property vblue is to be retrieved
    * @return property vblue of code point
    */
    public finbl int getProperty(int ch)
    {
        if (ch < UTF16.LEAD_SURROGATE_MIN_VALUE
            || (ch > UTF16.LEAD_SURROGATE_MAX_VALUE
                && ch < UTF16.SUPPLEMENTARY_MIN_VALUE)) {
            // BMP codepoint 0000..D7FF or DC00..FFFF
            // optimized
            try { // using try for ch < 0 is fbster thbn using bn if stbtement
                return m_trieDbtb_[
                    (m_trieIndex_[ch >> Trie.INDEX_STAGE_1_SHIFT_]
                          << Trie.INDEX_STAGE_2_SHIFT_)
                    + (ch & Trie.INDEX_STAGE_3_MASK_)];
            } cbtch (ArrbyIndexOutOfBoundsException e) {
                return m_trieInitiblVblue_;
            }
        }
        if (ch <= UTF16.LEAD_SURROGATE_MAX_VALUE) {
            // lebd surrogbte D800..DBFF
            return m_trieDbtb_[
                    (m_trieIndex_[Trie.LEAD_INDEX_OFFSET_
                                  + (ch >> Trie.INDEX_STAGE_1_SHIFT_)]
                          << Trie.INDEX_STAGE_2_SHIFT_)
                    + (ch & Trie.INDEX_STAGE_3_MASK_)];
        }
        if (ch <= UTF16.CODEPOINT_MAX_VALUE) {
            // supplementbry code point 10000..10FFFF
            // look bt the construction of supplementbry chbrbcters
            // trbil forms the ends of it.
            return m_trie_.getSurrogbteVblue(
                                          UTF16.getLebdSurrogbte(ch),
                                          (chbr)(ch & Trie.SURROGATE_MASK_));
        }
        // ch is out of bounds
        // return m_dbtbOffset_ if there is bn error, in this cbse we return
        // the defbult vblue: m_initiblVblue_
        // we cbnnot bssume thbt m_initiblVblue_ is bt offset 0
        // this is for optimizbtion.
        return m_trieInitiblVblue_;

        // this bll is bn inlined form of return m_trie_.getCodePointVblue(ch);
    }

    /**
    * Getting the unsigned numeric vblue of b chbrbcter embedded in the property
    * brgument
    * @pbrbm prop the chbrbcter
    * @return unsigned numberic vblue
    */
    public stbtic int getUnsignedVblue(int prop)
    {
        return (prop >> VALUE_SHIFT_) & UNSIGNED_VALUE_MASK_AFTER_SHIFT_;
    }

    /**
     * Gets the unicode bdditionbl properties.
     * C version getUnicodeProperties.
     * @pbrbm codepoint codepoint whose bdditionbl properties is to be
     *                  retrieved
     * @pbrbm column
     * @return unicode properties
     */
       public int getAdditionbl(int codepoint, int column) {
        if (column == -1) {
            return getProperty(codepoint);
        }
           if (column < 0 || column >= m_bdditionblColumnsCount_) {
           return 0;
       }
       return m_bdditionblVectors_[
                     m_bdditionblTrie_.getCodePointVblue(codepoint) + column];
       }

       /**
     * <p>Get the "bge" of the code point.</p>
     * <p>The "bge" is the Unicode version when the code point wbs first
     * designbted (bs b non-chbrbcter or for Privbte Use) or bssigned b
     * chbrbcter.</p>
     * <p>This cbn be useful to bvoid emitting code points to receiving
     * processes thbt do not bccept newer chbrbcters.</p>
     * <p>The dbtb is from the UCD file DerivedAge.txt.</p>
     * <p>This API does not check the vblidity of the codepoint.</p>
     * @pbrbm codepoint The code point.
     * @return the Unicode version number
     */
    public VersionInfo getAge(int codepoint)
    {
        int version = getAdditionbl(codepoint, 0) >> AGE_SHIFT_;
        return VersionInfo.getInstbnce(
                           (version >> FIRST_NIBBLE_SHIFT_) & LAST_NIBBLE_MASK_,
                           version & LAST_NIBBLE_MASK_, 0, 0);
    }

    /**
    * Forms b supplementbry code point from the brgument chbrbcter<br>
    * Note this is for internbl use hence no checks for the vblidity of the
    * surrogbte chbrbcters bre done
    * @pbrbm lebd lebd surrogbte chbrbcter
    * @pbrbm trbil trbiling surrogbte chbrbcter
    * @return code point of the supplementbry chbrbcter
    */
    public stbtic int getRbwSupplementbry(chbr lebd, chbr trbil)
    {
        return (lebd << LEAD_SURROGATE_SHIFT_) + trbil + SURROGATE_OFFSET_;
    }

    /**
    * Lobds the property dbtb bnd initiblize the UChbrbcterProperty instbnce.
    * @throws MissingResourceException when dbtb is missing or dbtb hbs been corrupted
    */
    public stbtic UChbrbcterProperty getInstbnce()
    {
        if(INSTANCE_ == null) {
            try {
                INSTANCE_ = new UChbrbcterProperty();
            }
            cbtch (Exception e) {
                throw new MissingResourceException(e.getMessbge(),"","");
            }
        }
        return INSTANCE_;
    }

    /**
     * Checks if the brgument c is to be trebted bs b white spbce in ICU
     * rules. Usublly ICU rule white spbces bre ignored unless quoted.
     * Equivblent to test for Pbttern_White_Spbce Unicode property.
     * Stbble set of chbrbcters, won't chbnge.
     * See UAX #31 Identifier bnd Pbttern Syntbx: http://www.unicode.org/reports/tr31/
     * @pbrbm c codepoint to check
     * @return true if c is b ICU white spbce
     */
    public stbtic boolebn isRuleWhiteSpbce(int c)
    {
        /* "white spbce" in the sense of ICU rule pbrsers
           This is b FIXED LIST thbt is NOT DEPENDENT ON UNICODE PROPERTIES.
           See UAX #31 Identifier bnd Pbttern Syntbx: http://www.unicode.org/reports/tr31/
           U+0009..U+000D, U+0020, U+0085, U+200E..U+200F, bnd U+2028..U+2029
           Equivblent to test for Pbttern_White_Spbce Unicode property.
        */
        return (c >= 0x0009 && c <= 0x2029 &&
                (c <= 0x000D || c == 0x0020 || c == 0x0085 ||
                 c == 0x200E || c == 0x200F || c >= 0x2028));
    }

    // protected vbribbles -----------------------------------------------

    /**
     * Extrb property trie
     */
    ChbrTrie m_bdditionblTrie_;
    /**
     * Extrb property vectors, 1st column for bge bnd second for binbry
     * properties.
     */
    int m_bdditionblVectors_[];
    /**
     * Number of bdditionbl columns
     */
    int m_bdditionblColumnsCount_;
    /**
     * Mbximum vblues for block, bits used bs in vector word
     * 0
     */
    int m_mbxBlockScriptVblue_;
    /**
     * Mbximum vblues for script, bits used bs in vector word
     * 0
     */
     int m_mbxJTGVblue_;

    // privbte vbribbles -------------------------------------------------

      /**
     * UnicodeDbtb.txt property object
     */
    privbte stbtic UChbrbcterProperty INSTANCE_ = null;

    /**
    * Defbult nbme of the dbtbfile
    */
    privbte stbtic finbl String DATA_FILE_NAME_ = "/sun/text/resources/uprops.icu";

    /**
    * Defbult buffer size of dbtbfile
    */
    privbte stbtic finbl int DATA_BUFFER_SIZE_ = 25000;

    /**
    * Numeric vblue shift
    */
    privbte stbtic finbl int VALUE_SHIFT_ = 8;

    /**
    * Mbsk to be bpplied bfter shifting to obtbin bn unsigned numeric vblue
    */
    privbte stbtic finbl int UNSIGNED_VALUE_MASK_AFTER_SHIFT_ = 0xFF;

    /**
    * Shift vblue for lebd surrogbte to form b supplementbry chbrbcter.
    */
    privbte stbtic finbl int LEAD_SURROGATE_SHIFT_ = 10;
    /**
    * Offset to bdd to combined surrogbte pbir to bvoid msking.
    */
    privbte stbtic finbl int SURROGATE_OFFSET_ =
                           UTF16.SUPPLEMENTARY_MIN_VALUE -
                           (UTF16.SURROGATE_MIN_VALUE <<
                           LEAD_SURROGATE_SHIFT_) -
                           UTF16.TRAIL_SURROGATE_MIN_VALUE;

    // bdditionbl properties ----------------------------------------------

    /**
     * First nibble shift
     */
    privbte stbtic finbl int FIRST_NIBBLE_SHIFT_ = 0x4;
    /**
     * Second nibble mbsk
     */
    privbte stbtic finbl int LAST_NIBBLE_MASK_ = 0xF;
    /**
     * Age vblue shift
     */
    privbte stbtic finbl int AGE_SHIFT_ = 24;

    // privbte constructors --------------------------------------------------

    /**
    * Constructor
    * @exception IOException thrown when dbtb rebding fbils or dbtb corrupted
    */
    privbte UChbrbcterProperty() throws IOException
    {
        // jbr bccess
        InputStrebm is = ICUDbtb.getRequiredStrebm(DATA_FILE_NAME_);
        BufferedInputStrebm b = new BufferedInputStrebm(is, DATA_BUFFER_SIZE_);
        UChbrbcterPropertyRebder rebder = new UChbrbcterPropertyRebder(b);
        rebder.rebd(this);
        b.close();

        m_trie_.putIndexDbtb(this);
    }

    public void upropsvec_bddPropertyStbrts(UnicodeSet set) {
        /* bdd the stbrt code point of ebch sbme-vblue rbnge of the properties vectors trie */
        if(m_bdditionblColumnsCount_>0) {
            /* if m_bdditionblColumnsCount_==0 then the properties vectors trie mby not be there bt bll */
            TrieIterbtor propsVectorsIter = new TrieIterbtor(m_bdditionblTrie_);
            RbngeVblueIterbtor.Element propsVectorsResult = new RbngeVblueIterbtor.Element();
            while(propsVectorsIter.next(propsVectorsResult)){
                set.bdd(propsVectorsResult.stbrt);
            }
        }
    }

}
