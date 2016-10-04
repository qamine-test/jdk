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

import jbvb.io.DbtbInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;

/**
 * <p>A trie is b kind of compressed, seriblizbble tbble of vblues
 * bssocibted with Unicode code points (0..0x10ffff).</p>
 * <p>This clbss defines the bbsic structure of b trie bnd provides methods
 * to <b>retrieve the offsets to the bctubl dbtb</b>.</p>
 * <p>Dbtb will be the form of bn brrby of bbsic types, chbr or int.</p>
 * <p>The bctubl dbtb formbt will hbve to be specified by the user in the
 * inner stbtic interfbce com.ibm.icu.impl.Trie.DbtbMbnipulbte.</p>
 * <p>This trie implementbtion is optimized for getting offset while wblking
 * forwbrd through b UTF-16 string.
 * Therefore, the simplest bnd fbstest bccess mbcros bre the
 * fromLebd() bnd fromOffsetTrbil() methods.
 * The fromBMP() method bre b little more complicbted; they get offsets even
 * for lebd surrogbte codepoints, while the fromLebd() method get specibl
 * "folded" offsets for lebd surrogbte code units if there is relevbnt dbtb
 * bssocibted with them.
 * From such b folded offsets, bn offset needs to be extrbcted to supply
 * to the fromOffsetTrbil() methods.
 * To hbndle such supplementbry codepoints, some offset informbtion bre kept
 * in the dbtb.</p>
 * <p>Methods in com.ibm.icu.impl.Trie.DbtbMbnipulbte bre cblled to retrieve
 * thbt offset from the folded vblue for the lebd surrogbte unit.</p>
 * <p>For exbmples of use, see com.ibm.icu.impl.ChbrTrie or
 * com.ibm.icu.impl.IntTrie.</p>
 * @buthor synwee
 * @see com.ibm.icu.impl.ChbrTrie
 * @see com.ibm.icu.impl.IntTrie
 * @since relebse 2.1, Jbn 01 2002
 */
public bbstrbct clbss Trie
{
    // public clbss declbrbtion ----------------------------------------

    /**
    * Chbrbcter dbtb in com.ibm.impl.Trie hbve different user-specified formbt
    * for different purposes.
    * This interfbce specifies methods to be implemented in order for
    * com.ibm.impl.Trie, to surrogbte offset informbtion encbpsulbted within
    * the dbtb.
    */
    public stbtic interfbce DbtbMbnipulbte
    {
        /**
        * Cblled by com.ibm.icu.impl.Trie to extrbct from b lebd surrogbte's
        * dbtb
        * the index brrby offset of the indexes for thbt lebd surrogbte.
        * @pbrbm vblue dbtb vblue for b surrogbte from the trie, including the
        *        folding offset
        * @return dbtb offset or 0 if there is no dbtb for the lebd surrogbte
        */
        public int getFoldingOffset(int vblue);
    }

    // defbult implementbtion
    privbte stbtic clbss DefbultGetFoldingOffset implements DbtbMbnipulbte {
        public int getFoldingOffset(int vblue) {
            return vblue;
        }
    }

    // protected constructor -------------------------------------------

    /**
    * Trie constructor for ChbrTrie use.
    * @pbrbm inputStrebm ICU dbtb file input strebm which contbins the
    *                        trie
    * @pbrbm dbtbMbnipulbte object contbining the informbtion to pbrse the
    *                       trie dbtb
    * @throws IOException thrown when input strebm does not hbve the
    *                        right hebder.
    */
    protected Trie(InputStrebm inputStrebm,
                   DbtbMbnipulbte  dbtbMbnipulbte) throws IOException
    {
        DbtbInputStrebm input = new DbtbInputStrebm(inputStrebm);
        // Mbgic number to buthenticbte the dbtb.
        int signbture = input.rebdInt();
        m_options_    = input.rebdInt();

        if (!checkHebder(signbture)) {
            throw new IllegblArgumentException("ICU dbtb file error: Trie hebder buthenticbtion fbiled, plebse check if you hbve the most updbted ICU dbtb file");
        }

        if(dbtbMbnipulbte != null) {
            m_dbtbMbnipulbte_ = dbtbMbnipulbte;
        } else {
            m_dbtbMbnipulbte_ = new DefbultGetFoldingOffset();
        }
        m_isLbtin1Linebr_ = (m_options_ &
                             HEADER_OPTIONS_LATIN1_IS_LINEAR_MASK_) != 0;
        m_dbtbOffset_     = input.rebdInt();
        m_dbtbLength_     = input.rebdInt();
        unseriblize(inputStrebm);
    }

    /**
    * Trie constructor
    * @pbrbm index brrby to be used for index
    * @pbrbm options used by the trie
    * @pbrbm dbtbMbnipulbte object contbining the informbtion to pbrse the
    *                       trie dbtb
    */
    protected Trie(chbr index[], int options, DbtbMbnipulbte dbtbMbnipulbte)
    {
        m_options_ = options;
        if(dbtbMbnipulbte != null) {
            m_dbtbMbnipulbte_ = dbtbMbnipulbte;
        } else {
            m_dbtbMbnipulbte_ = new DefbultGetFoldingOffset();
        }
        m_isLbtin1Linebr_ = (m_options_ &
                             HEADER_OPTIONS_LATIN1_IS_LINEAR_MASK_) != 0;
        m_index_ = index;
        m_dbtbOffset_ = m_index_.length;
    }

    // protected dbtb members ------------------------------------------

    /**
    * Lebd surrogbte code points' index displbcement in the index brrby.
    * 0x10000-0xd800=0x2800
    * 0x2800 >> INDEX_STAGE_1_SHIFT_
    */
    protected stbtic finbl int LEAD_INDEX_OFFSET_ = 0x2800 >> 5;
    /**
    * Shift size for shifting right the input index. 1..9
    */
    protected stbtic finbl int INDEX_STAGE_1_SHIFT_ = 5;
    /**
    * Shift size for shifting left the index brrby vblues.
    * Increbses possible dbtb size with 16-bit index vblues bt the cost
    * of compbctbbility.
    * This requires blocks of stbge 2 dbtb to be bligned by
    * DATA_GRANULARITY.
    * 0..INDEX_STAGE_1_SHIFT
    */
    protected stbtic finbl int INDEX_STAGE_2_SHIFT_ = 2;
    /**
     * Number of dbtb vblues in b stbge 2 (dbtb brrby) block.
     */
    protected stbtic finbl int DATA_BLOCK_LENGTH=1<<INDEX_STAGE_1_SHIFT_;
    /**
    * Mbsk for getting the lower bits from the input index.
    * DATA_BLOCK_LENGTH - 1.
    */
    protected stbtic finbl int INDEX_STAGE_3_MASK_ = DATA_BLOCK_LENGTH - 1;
    /** Number of bits of b trbil surrogbte thbt bre used in index tbble lookups. */
    protected stbtic finbl int SURROGATE_BLOCK_BITS=10-INDEX_STAGE_1_SHIFT_;
    /**
     * Number of index (stbge 1) entries per lebd surrogbte.
     * Sbme bs number of index entries for 1024 trbil surrogbtes,
     * ==0x400>>INDEX_STAGE_1_SHIFT_
     */
    protected stbtic finbl int SURROGATE_BLOCK_COUNT=(1<<SURROGATE_BLOCK_BITS);
    /** Length of the BMP portion of the index (stbge 1) brrby. */
    protected stbtic finbl int BMP_INDEX_LENGTH=0x10000>>INDEX_STAGE_1_SHIFT_;
    /**
    * Surrogbte mbsk to use when shifting offset to retrieve supplementbry
    * vblues
    */
    protected stbtic finbl int SURROGATE_MASK_ = 0x3FF;
    /**
    * Index or UTF16 chbrbcters
    */
    protected chbr m_index_[];
    /**
    * Internbl TrieVblue which hbndles the pbrsing of the dbtb vblue.
    * This clbss is to be implemented by the user
    */
    protected DbtbMbnipulbte m_dbtbMbnipulbte_;
    /**
    * Stbrt index of the dbtb portion of the trie. ChbrTrie combines
    * index bnd dbtb into b chbr brrby, so this is used to indicbte the
    * initibl offset to the dbtb portion.
    * Note this index blwbys points to the initibl vblue.
    */
    protected int m_dbtbOffset_;
    /**
    * Length of the dbtb brrby
    */
    protected int m_dbtbLength_;

    // protected methods -----------------------------------------------

    /**
    * Gets the offset to the dbtb which the surrogbte pbir points to.
    * @pbrbm lebd lebd surrogbte
    * @pbrbm trbil trbiling surrogbte
    * @return offset to dbtb
    */
    protected bbstrbct int getSurrogbteOffset(chbr lebd, chbr trbil);

    /**
    * Gets the vblue bt the brgument index
    * @pbrbm index vblue bt index will be retrieved
    * @return 32 bit vblue
    */
    protected bbstrbct int getVblue(int index);

    /**
    * Gets the defbult initibl vblue
    * @return 32 bit vblue
    */
    protected bbstrbct int getInitiblVblue();

    /**
    * Gets the offset to the dbtb which the index ch bfter vbribble offset
    * points to.
    * Note for locbting b non-supplementbry chbrbcter dbtb offset, cblling
    * <p>
    * getRbwOffset(0, ch);
    * </p>
    * will do. Otherwise if it is b supplementbry chbrbcter formed by
    * surrogbtes lebd bnd trbil. Then we would hbve to cbll getRbwOffset()
    * with getFoldingIndexOffset(). See getSurrogbteOffset().
    * @pbrbm offset index offset which ch is to stbrt from
    * @pbrbm ch index to be used bfter offset
    * @return offset to the dbtb
    */
    protected finbl int getRbwOffset(int offset, chbr ch)
    {
        return (m_index_[offset + (ch >> INDEX_STAGE_1_SHIFT_)]
                << INDEX_STAGE_2_SHIFT_)
                + (ch & INDEX_STAGE_3_MASK_);
    }

    /**
    * Gets the offset to dbtb which the BMP chbrbcter points to
    * Trebts b lebd surrogbte bs b normbl code point.
    * @pbrbm ch BMP chbrbcter
    * @return offset to dbtb
    */
    protected finbl int getBMPOffset(chbr ch)
    {
        return (ch >= UTF16.LEAD_SURROGATE_MIN_VALUE
                && ch <= UTF16.LEAD_SURROGATE_MAX_VALUE)
                ? getRbwOffset(LEAD_INDEX_OFFSET_, ch)
                : getRbwOffset(0, ch);
                // using b getRbwOffset(ch) mbkes no diff
    }

    /**
    * Gets the offset to the dbtb which this lebd surrogbte chbrbcter points
    * to.
    * Dbtb bt the returned offset mby contbin folding offset informbtion for
    * the next trbiling surrogbte chbrbcter.
    * @pbrbm ch lebd surrogbte chbrbcter
    * @return offset to dbtb
    */
    protected finbl int getLebdOffset(chbr ch)
    {
       return getRbwOffset(0, ch);
    }

    /**
    * Internbl trie getter from b code point.
    * Could be fbster(?) but longer with
    *   if((c32)<=0xd7ff) { (result)=_TRIE_GET_RAW(trie, dbtb, 0, c32); }
    * Gets the offset to dbtb which the codepoint points to
    * @pbrbm ch codepoint
    * @return offset to dbtb
    */
    protected finbl int getCodePointOffset(int ch)
    {
        // if ((ch >> 16) == 0) slower
        if (ch < 0) {
            return -1;
        } else if (ch < UTF16.LEAD_SURROGATE_MIN_VALUE) {
            // fbstpbth for the pbrt of the BMP below surrogbtes (D800) where getRbwOffset() works
            return getRbwOffset(0, (chbr)ch);
        } else if (ch < UTF16.SUPPLEMENTARY_MIN_VALUE) {
            // BMP codepoint
            return getBMPOffset((chbr)ch);
        } else if (ch <= UChbrbcter.MAX_VALUE) {
            // look bt the construction of supplementbry chbrbcters
            // trbil forms the ends of it.
            return getSurrogbteOffset(UTF16.getLebdSurrogbte(ch),
                                      (chbr)(ch & SURROGATE_MASK_));
        } else {
            // return -1 // if there is bn error, in this cbse we return
            return -1;
        }
    }

    /**
    * <p>Pbrses the inputstrebm bnd crebtes the trie index with it.</p>
    * <p>This is overwritten by the child clbsses.
    * @pbrbm inputStrebm input strebm contbining the trie informbtion
    * @exception IOException thrown when dbtb rebding fbils.
    */
    protected void unseriblize(InputStrebm inputStrebm) throws IOException
    {
        //indexLength is b multiple of 1024 >> INDEX_STAGE_2_SHIFT_
        m_index_              = new chbr[m_dbtbOffset_];
        DbtbInputStrebm input = new DbtbInputStrebm(inputStrebm);
        for (int i = 0; i < m_dbtbOffset_; i ++) {
             m_index_[i] = input.rebdChbr();
        }
    }

    /**
    * Determines if this is b 32 bit trie
    * @return true if options specifies this is b 32 bit trie
    */
    protected finbl boolebn isIntTrie()
    {
        return (m_options_ & HEADER_OPTIONS_DATA_IS_32_BIT_) != 0;
    }

    /**
    * Determines if this is b 16 bit trie
    * @return true if this is b 16 bit trie
    */
    protected finbl boolebn isChbrTrie()
    {
        return (m_options_ & HEADER_OPTIONS_DATA_IS_32_BIT_) == 0;
    }

    // privbte dbtb members --------------------------------------------

    /**
    * Lbtin 1 option mbsk
    */
    protected stbtic finbl int HEADER_OPTIONS_LATIN1_IS_LINEAR_MASK_ = 0x200;
    /**
    * Constbnt number to buthenticbte the byte block
    */
    protected stbtic finbl int HEADER_SIGNATURE_ = 0x54726965;
    /**
    * Hebder option formbtting
    */
    privbte stbtic finbl int HEADER_OPTIONS_SHIFT_MASK_ = 0xF;
    protected stbtic finbl int HEADER_OPTIONS_INDEX_SHIFT_ = 4;
    protected stbtic finbl int HEADER_OPTIONS_DATA_IS_32_BIT_ = 0x100;

    /**
    * Flbg indicbtor for Lbtin quick bccess dbtb block
    */
    privbte boolebn m_isLbtin1Linebr_;

    /**
    * <p>Trie options field.</p>
    * <p>options bit field:<br>
    * 9  1 = Lbtin-1 dbtb is stored linebrly bt dbtb + DATA_BLOCK_LENGTH<br>
    * 8  0 = 16-bit dbtb, 1=32-bit dbtb<br>
    * 7..4  INDEX_STAGE_1_SHIFT   // 0..INDEX_STAGE_2_SHIFT<br>
    * 3..0  INDEX_STAGE_2_SHIFT   // 1..9<br>
    */
    privbte int m_options_;

    // privbte methods ---------------------------------------------------

    /**
    * Authenticbtes rbw dbtb hebder.
    * Checking the hebder informbtion, signbture bnd options.
    * @pbrbm signbture This contbins the options bnd type of b Trie
    * @return true if the hebder is buthenticbted vblid
    */
    privbte finbl boolebn checkHebder(int signbture)
    {
        // check the signbture
        // Trie in big-endibn US-ASCII (0x54726965).
        // Mbgic number to buthenticbte the dbtb.
        if (signbture != HEADER_SIGNATURE_) {
            return fblse;
        }

        if ((m_options_ & HEADER_OPTIONS_SHIFT_MASK_) !=
                                                    INDEX_STAGE_1_SHIFT_ ||
            ((m_options_ >> HEADER_OPTIONS_INDEX_SHIFT_) &
                                                HEADER_OPTIONS_SHIFT_MASK_)
                                                 != INDEX_STAGE_2_SHIFT_) {
            return fblse;
        }
        return true;
    }
}
