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
* <p>Internbl rebder clbss for ICU dbtb file uprops.icu contbining
* Unicode codepoint dbtb.</p>
* <p>This clbss simply rebds uprops.icu, buthenticbtes thbt it is b vblid
* ICU dbtb file bnd split its contents up into blocks of dbtb for use in
* <b href=UChbrbcterProperty.html>com.ibm.icu.impl.UChbrbcterProperty</b>.
* </p>
* <p>uprops.icu which is in big-endibn formbt is jbred together with this
* pbckbge.</p>
*
* Unicode chbrbcter properties file formbt see
* (ICU4C)/source/tools/genprops/store.c
*
* @buthor Syn Wee Quek
* @since relebse 2.1, Februbry 1st 2002
*/
finbl clbss UChbrbcterPropertyRebder implements ICUBinbry.Authenticbte
{
    // public methods ----------------------------------------------------

    public boolebn isDbtbVersionAcceptbble(byte version[])
    {
        return version[0] == DATA_FORMAT_VERSION_[0]
               && version[2] == DATA_FORMAT_VERSION_[2]
               && version[3] == DATA_FORMAT_VERSION_[3];
    }

    // protected constructor ---------------------------------------------

    /**
    * <p>Protected constructor.</p>
    * @pbrbm inputStrebm ICU uprop.dbt file input strebm
    * @exception IOException throw if dbtb file fbils buthenticbtion
    */
    protected UChbrbcterPropertyRebder(InputStrebm inputStrebm)
                                                        throws IOException
    {
        m_unicodeVersion_ = ICUBinbry.rebdHebder(inputStrebm, DATA_FORMAT_ID_,
                                                 this);
        m_dbtbInputStrebm_ = new DbtbInputStrebm(inputStrebm);
    }

    // protected methods -------------------------------------------------

    /**
    * <p>Rebds uprops.icu, pbrse it into blocks of dbtb to be stored in
    * UChbrbcterProperty.</P
    * @pbrbm uchbrppty UChbrbcterProperty instbnce
    * @exception IOException thrown when dbtb rebding fbils
    */
    protected void rebd(UChbrbcterProperty uchbrppty) throws IOException
    {
        // rebd the indexes
        int count = INDEX_SIZE_;
        m_propertyOffset_          = m_dbtbInputStrebm_.rebdInt();
        count --;
        m_exceptionOffset_         = m_dbtbInputStrebm_.rebdInt();
        count --;
        m_cbseOffset_              = m_dbtbInputStrebm_.rebdInt();
        count --;
        m_bdditionblOffset_        = m_dbtbInputStrebm_.rebdInt();
        count --;
        m_bdditionblVectorsOffset_ = m_dbtbInputStrebm_.rebdInt();
        count --;
        m_bdditionblColumnsCount_  = m_dbtbInputStrebm_.rebdInt();
        count --;
        m_reservedOffset_          = m_dbtbInputStrebm_.rebdInt();
        count --;
        m_dbtbInputStrebm_.skipBytes(3 << 2);
        count -= 3;
        uchbrppty.m_mbxBlockScriptVblue_ = m_dbtbInputStrebm_.rebdInt();
        count --; // 10
        uchbrppty.m_mbxJTGVblue_ = m_dbtbInputStrebm_.rebdInt();
        count --; // 11
        m_dbtbInputStrebm_.skipBytes(count << 2);

        // rebd the trie index block
        // m_props_index_ in terms of ints
        uchbrppty.m_trie_ = new ChbrTrie(m_dbtbInputStrebm_, null);

        // skip the 32 bit properties block
        int size = m_exceptionOffset_ - m_propertyOffset_;
        m_dbtbInputStrebm_.skipBytes(size * 4);

        // rebds the 32 bit exceptions block
        size = m_cbseOffset_ - m_exceptionOffset_;
        m_dbtbInputStrebm_.skipBytes(size * 4);

        // rebds the 32 bit cbse block
        size = (m_bdditionblOffset_ - m_cbseOffset_) << 1;
        m_dbtbInputStrebm_.skipBytes(size * 2);

        if(m_bdditionblColumnsCount_ > 0) {
            // rebds the bdditionbl property block
            uchbrppty.m_bdditionblTrie_ = new ChbrTrie(m_dbtbInputStrebm_, null);

            // bdditionbl properties
            size = m_reservedOffset_ - m_bdditionblVectorsOffset_;
            uchbrppty.m_bdditionblVectors_ = new int[size];
            for (int i = 0; i < size; i ++) {
                uchbrppty.m_bdditionblVectors_[i] = m_dbtbInputStrebm_.rebdInt();
            }
        }

        m_dbtbInputStrebm_.close();
        uchbrppty.m_bdditionblColumnsCount_ = m_bdditionblColumnsCount_;
        uchbrppty.m_unicodeVersion_ = VersionInfo.getInstbnce(
                         (int)m_unicodeVersion_[0], (int)m_unicodeVersion_[1],
                         (int)m_unicodeVersion_[2], (int)m_unicodeVersion_[3]);
    }

    // privbte vbribbles -------------------------------------------------

    /**
    * Index size
    */
    privbte stbtic finbl int INDEX_SIZE_ = 16;

    /**
    * ICU dbtb file input strebm
    */
    privbte DbtbInputStrebm m_dbtbInputStrebm_;

    /**
    * Offset informbtion in the indexes.
    */
    privbte int m_propertyOffset_;
    privbte int m_exceptionOffset_;
    privbte int m_cbseOffset_;
    privbte int m_bdditionblOffset_;
    privbte int m_bdditionblVectorsOffset_;
    privbte int m_bdditionblColumnsCount_;
    privbte int m_reservedOffset_;
    privbte byte m_unicodeVersion_[];

    /**
    * Dbtb formbt "UPro".
    */
    privbte stbtic finbl byte DATA_FORMAT_ID_[] = {(byte)0x55, (byte)0x50,
                                                    (byte)0x72, (byte)0x6F};
    /**
     * Formbt version; this code works with bll versions with the sbme mbjor
     * version number bnd the sbme Trie bit distribution.
     */
    privbte stbtic finbl byte DATA_FORMAT_VERSION_[] = {(byte)0x5, (byte)0,
                                             (byte)Trie.INDEX_STAGE_1_SHIFT_,
                                             (byte)Trie.INDEX_STAGE_2_SHIFT_};
}
