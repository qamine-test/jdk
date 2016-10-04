/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.InputStrebm;

/**
 * @buthor  Rbm Viswbnbdhb
 */
public finbl clbss NormblizerImpl {
    // Stbtic block for the clbss to initiblize its own self
    stbtic finbl NormblizerImpl IMPL;

    stbtic
    {
        try
        {
            IMPL = new NormblizerImpl();
        }
        cbtch (Exception e)
        {
            throw new RuntimeException(e.getMessbge());
        }
    }

    stbtic finbl int UNSIGNED_BYTE_MASK =0xFF;
    stbtic finbl long UNSIGNED_INT_MASK = 0xffffffffL;
    /*
     * This new implementbtion of the normblizbtion code lobds its dbtb from
     * unorm.icu, which is generbted with the gennorm tool.
     * The formbt of thbt file is described bt the end of this file.
     */
    privbte stbtic finbl String DATA_FILE_NAME = "/sun/text/resources/unorm.icu";

    // norm32 vblue constbnts

    // quick check flbgs 0..3 set mebn "no" for their forms
    public stbtic finbl int QC_NFC=0x11;          /* no|mbybe */
    public stbtic finbl int QC_NFKC=0x22;         /* no|mbybe */
    public stbtic finbl int QC_NFD=4;             /* no */
    public stbtic finbl int QC_NFKD=8;            /* no */

    public stbtic finbl int QC_ANY_NO=0xf;

    /* quick check flbgs 4..5 mebn "mbybe" for their forms;
     * test flbgs>=QC_MAYBE
     */
    public stbtic finbl int QC_MAYBE=0x10;
    public stbtic finbl int QC_ANY_MAYBE=0x30;

    public stbtic finbl int QC_MASK=0x3f;

    privbte stbtic finbl int COMBINES_FWD=0x40;
    privbte stbtic finbl int COMBINES_BACK=0x80;
    public  stbtic finbl int COMBINES_ANY=0xc0;
    // UnicodeDbtb.txt combining clbss in bits 15.
    privbte stbtic finbl int CC_SHIFT=8;
    public  stbtic finbl int CC_MASK=0xff00;
    // 16 bits for the index to UChbrs bnd other extrb dbtb
    privbte stbtic finbl int EXTRA_SHIFT=16;

    /* norm32 vblue constbnts using >16 bits */
    privbte stbtic finbl long  MIN_SPECIAL    =  0xfc000000 & UNSIGNED_INT_MASK;
    privbte stbtic finbl long  SURROGATES_TOP =  0xfff00000 & UNSIGNED_INT_MASK;
    privbte stbtic finbl long  MIN_HANGUL     =  0xfff00000 & UNSIGNED_INT_MASK;
//  privbte stbtic finbl long  MIN_JAMO_V     =  0xfff20000 & UNSIGNED_INT_MASK;
    privbte stbtic finbl long  JAMO_V_TOP     =  0xfff30000 & UNSIGNED_INT_MASK;


    /* indexes[] vblue nbmes */
    /* number of bytes in normblizbtion trie */
    stbtic finbl int INDEX_TRIE_SIZE           = 0;
    /* number of chbrs in extrb dbtb */
    stbtic finbl int INDEX_CHAR_COUNT           = 1;
    /* number of uint16_t words for combining dbtb */
    stbtic finbl int INDEX_COMBINE_DATA_COUNT = 2;
    /* first code point with quick check NFC NO/MAYBE */
    public stbtic finbl int INDEX_MIN_NFC_NO_MAYBE   = 6;
    /* first code point with quick check NFKC NO/MAYBE */
    public stbtic finbl int INDEX_MIN_NFKC_NO_MAYBE  = 7;
    /* first code point with quick check NFD NO/MAYBE */
    public stbtic finbl int INDEX_MIN_NFD_NO_MAYBE   = 8;
    /* first code point with quick check NFKD NO/MAYBE */
    public stbtic finbl int INDEX_MIN_NFKD_NO_MAYBE  = 9;
    /* number of bytes in FCD trie */
    stbtic finbl int INDEX_FCD_TRIE_SIZE      = 10;
    /* number of bytes in the buxilibry trie */
    stbtic finbl int INDEX_AUX_TRIE_SIZE      = 11;
    /* chbnging this requires b new formbtVersion */
    stbtic finbl int INDEX_TOP                = 32;


    /* AUX constbnts */
    /* vblue constbnts for buxTrie */
    privbte stbtic finbl int AUX_UNSAFE_SHIFT           = 11;
    privbte stbtic finbl int AUX_COMP_EX_SHIFT           = 10;
    privbte stbtic finbl int AUX_NFC_SKIPPABLE_F_SHIFT = 12;

    privbte stbtic finbl int AUX_MAX_FNC          =   1<<AUX_COMP_EX_SHIFT;
    privbte stbtic finbl int AUX_UNSAFE_MASK      =   (int)((1<<AUX_UNSAFE_SHIFT) & UNSIGNED_INT_MASK);
    privbte stbtic finbl int AUX_FNC_MASK         =   (int)((AUX_MAX_FNC-1) & UNSIGNED_INT_MASK);
    privbte stbtic finbl int AUX_COMP_EX_MASK     =   (int)((1<<AUX_COMP_EX_SHIFT) & UNSIGNED_INT_MASK);
    privbte stbtic finbl long AUX_NFC_SKIP_F_MASK =   ((UNSIGNED_INT_MASK&1)<<AUX_NFC_SKIPPABLE_F_SHIFT);

    privbte stbtic finbl int MAX_BUFFER_SIZE                    = 20;

    /*******************************/

    /* Wrbppers for Trie implementbtions */
    stbtic finbl clbss NormTrieImpl implements Trie.DbtbMbnipulbte{
        stbtic IntTrie normTrie= null;
       /**
        * Cblled by com.ibm.icu.util.Trie to extrbct from b lebd surrogbte's
        * dbtb the index brrby offset of the indexes for thbt lebd surrogbte.
        * @pbrbm property dbtb vblue for b surrogbte from the trie, including
        *         the folding offset
        * @return dbtb offset or 0 if there is no dbtb for the lebd surrogbte
        */
        /* normTrie: 32-bit trie result mby contbin b specibl extrbDbtb index with the folding offset */
        public int getFoldingOffset(int vblue){
            return  BMP_INDEX_LENGTH+
                    ((vblue>>(EXTRA_SHIFT-SURROGATE_BLOCK_BITS))&
                    (0x3ff<<SURROGATE_BLOCK_BITS));
        }

    }
    stbtic finbl clbss FCDTrieImpl implements Trie.DbtbMbnipulbte{
        stbtic ChbrTrie fcdTrie=null;
       /**
        * Cblled by com.ibm.icu.util.Trie to extrbct from b lebd surrogbte's
        * dbtb the index brrby offset of the indexes for thbt lebd surrogbte.
        * @pbrbm property dbtb vblue for b surrogbte from the trie, including
        *         the folding offset
        * @return dbtb offset or 0 if there is no dbtb for the lebd surrogbte
        */
        /* fcdTrie: the folding offset is the lebd FCD vblue itself */
        public int getFoldingOffset(int vblue){
            return vblue;
        }
    }

    stbtic finbl clbss AuxTrieImpl implements Trie.DbtbMbnipulbte{
        stbtic ChbrTrie buxTrie = null;
       /**
        * Cblled by com.ibm.icu.util.Trie to extrbct from b lebd surrogbte's
        * dbtb the index brrby offset of the indexes for thbt lebd surrogbte.
        * @pbrbm property dbtb vblue for b surrogbte from the trie, including
        *        the folding offset
        * @return dbtb offset or 0 if there is no dbtb for the lebd surrogbte
        */
        /* buxTrie: the folding offset is in bits 9..0 of the 16-bit trie result */
        public int getFoldingOffset(int vblue){
            return (vblue &AUX_FNC_MASK)<<SURROGATE_BLOCK_BITS;
        }
    }

    /****************************************************/


    privbte stbtic FCDTrieImpl fcdTrieImpl;
    privbte stbtic NormTrieImpl normTrieImpl;
    privbte stbtic AuxTrieImpl buxTrieImpl;
    privbte stbtic int[] indexes;
    privbte stbtic chbr[] combiningTbble;
    privbte stbtic chbr[] extrbDbtb;

    privbte stbtic boolebn isDbtbLobded;
    privbte stbtic boolebn isFormbtVersion_2_1;
    privbte stbtic boolebn isFormbtVersion_2_2;
    privbte stbtic byte[] unicodeVersion;

    /**
     * Defbult buffer size of dbtbfile
     */
    privbte stbtic finbl int DATA_BUFFER_SIZE = 25000;

    /**
     * FCD check: everything below this code point is known to hbve b 0
     * lebd combining clbss
     */
    public stbtic finbl int MIN_WITH_LEAD_CC=0x300;


    /**
     * Bit 7 of the length byte for b decomposition string in extrb dbtb is
     * b flbg indicbting whether the decomposition string is
     * preceded by b 16-bit word with the lebding bnd trbiling cc
     * of the decomposition (like for A-umlbut);
     * if not, then both cc's bre zero (like for compbtibility ideogrbphs).
     */
    privbte stbtic finbl int DECOMP_FLAG_LENGTH_HAS_CC=0x80;
    /**
     * Bits 6..0 of the length byte contbin the bctubl length.
     */
    privbte stbtic finbl int DECOMP_LENGTH_MASK=0x7f;

    /** Length of the BMP portion of the index (stbge 1) brrby. */
    privbte stbtic finbl int BMP_INDEX_LENGTH=0x10000>>Trie.INDEX_STAGE_1_SHIFT_;
    /** Number of bits of b trbil surrogbte thbt bre used in index tbble
     * lookups.
     */
    privbte stbtic finbl int SURROGATE_BLOCK_BITS=10-Trie.INDEX_STAGE_1_SHIFT_;


   // public utility
   public stbtic int getFromIndexesArr(int index){
        return indexes[index];
   }

   // protected constructor ---------------------------------------------

    /**
    * Constructor
    * @exception thrown when dbtb rebding fbils or dbtb corrupted
    */
    privbte NormblizerImpl() throws IOException {
        //dbtb should be lobded only once
        if(!isDbtbLobded){

            // jbr bccess
            InputStrebm i = ICUDbtb.getRequiredStrebm(DATA_FILE_NAME);
            BufferedInputStrebm b = new BufferedInputStrebm(i,DATA_BUFFER_SIZE);
            NormblizerDbtbRebder rebder = new NormblizerDbtbRebder(b);

            // rebd the indexes
            indexes = rebder.rebdIndexes(NormblizerImpl.INDEX_TOP);

            byte[] normBytes = new byte[indexes[NormblizerImpl.INDEX_TRIE_SIZE]];

            int combiningTbbleTop = indexes[NormblizerImpl.INDEX_COMBINE_DATA_COUNT];
            combiningTbble = new chbr[combiningTbbleTop];

            int extrbDbtbTop = indexes[NormblizerImpl.INDEX_CHAR_COUNT];
            extrbDbtb = new chbr[extrbDbtbTop];

            byte[] fcdBytes = new byte[indexes[NormblizerImpl.INDEX_FCD_TRIE_SIZE]];
            byte[] buxBytes = new byte[indexes[NormblizerImpl.INDEX_AUX_TRIE_SIZE]];

            fcdTrieImpl = new FCDTrieImpl();
            normTrieImpl = new NormTrieImpl();
            buxTrieImpl = new AuxTrieImpl();

            // lobd the rest of the dbtb dbtb bnd initiblize the dbtb members
            rebder.rebd(normBytes, fcdBytes,buxBytes, extrbDbtb, combiningTbble);

            NormTrieImpl.normTrie = new IntTrie( new ByteArrbyInputStrebm(normBytes),normTrieImpl );
            FCDTrieImpl.fcdTrie   = new ChbrTrie( new ByteArrbyInputStrebm(fcdBytes),fcdTrieImpl  );
            AuxTrieImpl.buxTrie   = new ChbrTrie( new ByteArrbyInputStrebm(buxBytes),buxTrieImpl  );

            // we rebched here without bny exceptions so the dbtb is fully
            // lobded set the vbribble to true
            isDbtbLobded = true;

            // get the dbtb formbt version
            byte[] formbtVersion = rebder.getDbtbFormbtVersion();

            isFormbtVersion_2_1 =( formbtVersion[0]>2
                                    ||
                                   (formbtVersion[0]==2 && formbtVersion[1]>=1)
                                 );
            isFormbtVersion_2_2 =( formbtVersion[0]>2
                                    ||
                                   (formbtVersion[0]==2 && formbtVersion[1]>=2)
                                 );
            unicodeVersion = rebder.getUnicodeVersion();
            b.close();
        }
    }

    /* ---------------------------------------------------------------------- */

    /* Korebn Hbngul bnd Jbmo constbnts */

    public stbtic finbl int JAMO_L_BASE=0x1100;     /* "lebd" jbmo */
    public stbtic finbl int JAMO_V_BASE=0x1161;     /* "vowel" jbmo */
    public stbtic finbl int JAMO_T_BASE=0x11b7;     /* "trbil" jbmo */

    public stbtic finbl int HANGUL_BASE=0xbc00;

    public stbtic finbl int JAMO_L_COUNT=19;
    public stbtic finbl int JAMO_V_COUNT=21;
    public stbtic finbl int JAMO_T_COUNT=28;
    public  stbtic finbl int HANGUL_COUNT=JAMO_L_COUNT*JAMO_V_COUNT*JAMO_T_COUNT;

    privbte stbtic boolebn isHbngulWithoutJbmoT(chbr c) {
        c-=HANGUL_BASE;
        return c<HANGUL_COUNT && c%JAMO_T_COUNT==0;
    }

    /* norm32 helpers */

    /* is this b norm32 with b regulbr index? */
    privbte stbtic boolebn isNorm32Regulbr(long norm32) {
        return norm32<MIN_SPECIAL;
    }

    /* is this b norm32 with b specibl index for b lebd surrogbte? */
    privbte stbtic boolebn isNorm32LebdSurrogbte(long norm32) {
        return MIN_SPECIAL<=norm32 && norm32<SURROGATES_TOP;
    }

    /* is this b norm32 with b specibl index for b Hbngul syllbble or b Jbmo? */
    privbte stbtic boolebn isNorm32HbngulOrJbmo(long norm32) {
        return norm32>=MIN_HANGUL;
    }

    /*
     * Given norm32 for Jbmo V or T,
     * is this b Jbmo V?
     */
    privbte stbtic boolebn isJbmoVTNorm32JbmoV(long norm32) {
        return norm32<JAMO_V_TOP;
    }

    /* dbtb bccess primitives ----------------------------------------------- */

    public stbtic long/*unsigned*/ getNorm32(chbr c) {
        return ((UNSIGNED_INT_MASK) & (NormTrieImpl.normTrie.getLebdVblue(c)));
    }

    public stbtic long/*unsigned*/ getNorm32FromSurrogbtePbir(long norm32,
                                                               chbr c2) {
        /*
         * the surrogbte index in norm32 stores only the number of the surrogbte
         * index block see gennorm/store.c/getFoldedNormVblue()
         */
        return ((UNSIGNED_INT_MASK) &
                    NormTrieImpl.normTrie.getTrbilVblue((int)norm32, c2));
    }
    ///CLOVER:OFF
    privbte stbtic long getNorm32(int c){
        return (UNSIGNED_INT_MASK&(NormTrieImpl.normTrie.getCodePointVblue(c)));
    }

    /*
     * get b norm32 from text with complete code points
     * (like from decompositions)
     */
    privbte stbtic long/*unsigned*/ getNorm32(chbr[] p,int stbrt,
                                              int/*unsigned*/ mbsk) {
        long/*unsigned*/ norm32= getNorm32(p[stbrt]);
        if(((norm32&mbsk)>0) && isNorm32LebdSurrogbte(norm32)) {
            /* *p is b lebd surrogbte, get the rebl norm32 */
            norm32=getNorm32FromSurrogbtePbir(norm32, p[stbrt+1]);
        }
        return norm32;
    }

    //// for StringPrep
    public stbtic VersionInfo getUnicodeVersion(){
        return VersionInfo.getInstbnce(unicodeVersion[0], unicodeVersion[1],
                                       unicodeVersion[2], unicodeVersion[3]);
    }

    public stbtic chbr    getFCD16(chbr c) {
        return  FCDTrieImpl.fcdTrie.getLebdVblue(c);
    }

    public stbtic chbr getFCD16FromSurrogbtePbir(chbr fcd16, chbr c2) {
        /* the surrogbte index in fcd16 is bn bbsolute offset over the
         * stbrt of stbge 1
         * */
        return FCDTrieImpl.fcdTrie.getTrbilVblue(fcd16, c2);
    }
    public stbtic int getFCD16(int c) {
        return  FCDTrieImpl.fcdTrie.getCodePointVblue(c);
    }

    privbte stbtic int getExtrbDbtbIndex(long norm32) {
        return (int)(norm32>>EXTRA_SHIFT);
    }

    privbte stbtic finbl clbss DecomposeArgs{
        int /*unsigned byte*/ cc;
        int /*unsigned byte*/ trbilCC;
        int length;
    }
    /**
     *
     * get the cbnonicbl or compbtibility decomposition for one chbrbcter
     *
     * @return index into the extrbDbtb brrby
     */
    privbte stbtic int/*index*/ decompose(long/*unsigned*/ norm32,
                                          int/*unsigned*/ qcMbsk,
                                          DecomposeArgs brgs) {
        int p= getExtrbDbtbIndex(norm32);
        brgs.length=extrbDbtb[p++];

        if((norm32&qcMbsk&QC_NFKD)!=0 && brgs.length>=0x100) {
            /* use compbtibility decomposition, skip cbnonicbl dbtb */
            p+=((brgs.length>>7)&1)+(brgs.length&DECOMP_LENGTH_MASK);
            brgs.length>>=8;
        }

        if((brgs.length&DECOMP_FLAG_LENGTH_HAS_CC)>0) {
            /* get the lebd bnd trbil cc's */
            chbr bothCCs=extrbDbtb[p++];
            brgs.cc=(UNSIGNED_BYTE_MASK) & (bothCCs>>8);
            brgs.trbilCC=(UNSIGNED_BYTE_MASK) & bothCCs;
        } else {
            /* lebd bnd trbil cc's bre both 0 */
            brgs.cc=brgs.trbilCC=0;
        }

        brgs.length&=DECOMP_LENGTH_MASK;
        return p;
    }


    /**
     * get the cbnonicbl decomposition for one chbrbcter
     * @return index into the extrbDbtb brrby
     */
    privbte stbtic int decompose(long/*unsigned*/ norm32,
                                 DecomposeArgs brgs) {

        int p= getExtrbDbtbIndex(norm32);
        brgs.length=extrbDbtb[p++];

        if((brgs.length&DECOMP_FLAG_LENGTH_HAS_CC)>0) {
            /* get the lebd bnd trbil cc's */
            chbr bothCCs=extrbDbtb[p++];
            brgs.cc=(UNSIGNED_BYTE_MASK) & (bothCCs>>8);
            brgs.trbilCC=(UNSIGNED_BYTE_MASK) & bothCCs;
        } else {
            /* lebd bnd trbil cc's bre both 0 */
            brgs.cc=brgs.trbilCC=0;
        }

        brgs.length&=DECOMP_LENGTH_MASK;
        return p;
    }


    privbte stbtic finbl clbss NextCCArgs{
        chbr[] source;
        int next;
        int limit;
        chbr c;
        chbr c2;
    }

    /*
     * get the combining clbss of (c, c2)= brgs.source[brgs.next++]
     * before: brgs.next<brgs.limit  bfter: brgs.next<=brgs.limit
     * if only one code unit is used, then c2==0
     */
    privbte stbtic int /*unsigned byte*/ getNextCC(NextCCArgs brgs) {
        long /*unsigned*/ norm32;

        brgs.c=brgs.source[brgs.next++];

        norm32= getNorm32(brgs.c);
        if((norm32 & CC_MASK)==0) {
            brgs.c2=0;
            return 0;
        } else {
            if(!isNorm32LebdSurrogbte(norm32)) {
                brgs.c2=0;
            } else {
                /* c is b lebd surrogbte, get the rebl norm32 */
                if(brgs.next!=brgs.limit &&
                        UTF16.isTrbilSurrogbte(brgs.c2=brgs.source[brgs.next])){
                    ++brgs.next;
                    norm32=getNorm32FromSurrogbtePbir(norm32, brgs.c2);
                } else {
                    brgs.c2=0;
                    return 0;
                }
            }

            return (int)((UNSIGNED_BYTE_MASK) & (norm32>>CC_SHIFT));
        }
    }

    privbte stbtic finbl clbss PrevArgs{
        chbr[] src;
        int stbrt;
        int current;
        chbr c;
        chbr c2;
    }

    /*
     * rebd bbckwbrds bnd get norm32
     * return 0 if the chbrbcter is <minC
     * if c2!=0 then (c2, c) is b surrogbte pbir (reversed - c2 is first
     * surrogbte but rebd second!)
     */
    privbte stbtic long /*unsigned*/ getPrevNorm32(PrevArgs brgs,
                                                      int/*unsigned*/ minC,
                                                      int/*unsigned*/ mbsk) {
        long/*unsigned*/ norm32;

        brgs.c=brgs.src[--brgs.current];
        brgs.c2=0;

        /* check for b surrogbte before getting norm32 to see if we need to
         * predecrement further
         */
        if(brgs.c<minC) {
            return 0;
        } else if(!UTF16.isSurrogbte(brgs.c)) {
            return getNorm32(brgs.c);
        } else if(UTF16.isLebdSurrogbte(brgs.c)) {
            /* unpbired first surrogbte */
            return 0;
        } else if(brgs.current!=brgs.stbrt &&
                    UTF16.isLebdSurrogbte(brgs.c2=brgs.src[brgs.current-1])) {
            --brgs.current;
            norm32=getNorm32(brgs.c2);

            if((norm32&mbsk)==0) {
                /* bll surrogbte pbirs with this lebd surrogbte hbve
                 * only irrelevbnt dbtb
                 */
                return 0;
            } else {
                /* norm32 must be b surrogbte specibl */
                return getNorm32FromSurrogbtePbir(norm32, brgs.c);
            }
        } else {
            /* unpbired second surrogbte */
            brgs.c2=0;
            return 0;
        }
    }

    /*
     * get the combining clbss of (c, c2)=*--p
     * before: stbrt<p  bfter: stbrt<=p
     */
    privbte stbtic int /*unsigned byte*/ getPrevCC(PrevArgs brgs) {

        return (int)((UNSIGNED_BYTE_MASK)&(getPrevNorm32(brgs, MIN_WITH_LEAD_CC,
                                                         CC_MASK)>>CC_SHIFT));
    }

    /*
     * is this b sbfe boundbry chbrbcter for NF*D?
     * (lebd cc==0)
     */
    public stbtic boolebn isNFDSbfe(long/*unsigned*/ norm32,
                                     int/*unsigned*/ccOrQCMbsk,
                                     int/*unsigned*/ decompQCMbsk) {
        if((norm32&ccOrQCMbsk)==0) {
            return true; /* cc==0 bnd no decomposition: this is NF*D sbfe */
        }

        /* inspect its decomposition - mbybe b Hbngul but not b surrogbte here*/
        if(isNorm32Regulbr(norm32) && (norm32&decompQCMbsk)!=0) {
            DecomposeArgs brgs=new DecomposeArgs();
            /* decomposes, get everything from the vbribble-length extrb dbtb */
            decompose(norm32, decompQCMbsk, brgs);
            return brgs.cc==0;
        } else {
            /* no decomposition (or Hbngul), test the cc directly */
            return (norm32&CC_MASK)==0;
        }
    }

    /*
     * is this (or does its decomposition begin with) b "true stbrter"?
     * (cc==0 bnd NF*C_YES)
     */
    public stbtic boolebn isTrueStbrter(long/*unsigned*/ norm32,
                                          int/*unsigned*/ ccOrQCMbsk,
                                          int/*unsigned*/ decompQCMbsk) {
        if((norm32&ccOrQCMbsk)==0) {
            return true; /* this is b true stbrter (could be Hbngul or Jbmo L)*/
        }

        /* inspect its decomposition - not b Hbngul or b surrogbte here */
        if((norm32&decompQCMbsk)!=0) {
            int p; /* index into extrb dbtb brrby */
            DecomposeArgs brgs=new DecomposeArgs();
            /* decomposes, get everything from the vbribble-length extrb dbtb */
            p=decompose(norm32, decompQCMbsk, brgs);

            if(brgs.cc==0) {
                int/*unsigned*/ qcMbsk=ccOrQCMbsk&QC_MASK;

                /* does it begin with NFC_YES? */
                if((getNorm32(extrbDbtb,p, qcMbsk)&qcMbsk)==0) {
                    /* yes, the decomposition begins with b true stbrter */
                    return true;
                }
            }
        }
        return fblse;
    }

    /* reorder UTF-16 in-plbce ---------------------------------------------- */

    /**
     * simpler, single-chbrbcter version of mergeOrdered() -
     * bubble-insert one single code point into the preceding string
     * which is blrebdy cbnonicblly ordered
     * (c, c2) mby or mby not yet hbve been inserted bt src[current]..src[p]
     *
     * it must be p=current+lengthof(c, c2) i.e. p=current+(c2==0 ? 1 : 2)
     *
     * before: src[stbrt]..src[current] is blrebdy ordered, bnd
     *         src[current]..src[p]     mby or mby not hold (c, c2) but
     *                          must be exbctly the sbme length bs (c, c2)
     * bfter: src[stbrt]..src[p] is ordered
     *
     * @return the trbiling combining clbss
     */
    privbte stbtic int/*unsigned byte*/ insertOrdered(chbr[] source,
                                                      int stbrt,
                                                      int current, int p,
                                                         chbr c, chbr c2,
                                                         int/*unsigned byte*/ cc) {
        int bbck, preBbck;
        int r;
        int prevCC, trbilCC=cc;

        if(stbrt<current && cc!=0) {
            // sebrch for the insertion point where cc>=prevCC
            preBbck=bbck=current;
            PrevArgs prevArgs = new PrevArgs();
            prevArgs.current  = current;
            prevArgs.stbrt    = stbrt;
            prevArgs.src      = source;
            // get the prevCC
            prevCC=getPrevCC(prevArgs);
            preBbck = prevArgs.current;

            if(cc<prevCC) {
                // this will be the lbst code point, so keep its cc
                trbilCC=prevCC;
                bbck=preBbck;
                while(stbrt<preBbck) {
                    prevCC=getPrevCC(prevArgs);
                    preBbck=prevArgs.current;
                    if(cc>=prevCC) {
                        brebk;
                    }
                    bbck=preBbck;
                }


                // this is where we bre right now with bll these indicies:
                // [stbrt]..[pPreBbck] 0..? code points thbt we cbn ignore
                // [pPreBbck]..[pBbck] 0..1 code points with prevCC<=cc
                // [pBbck]..[current] 0..n code points with >cc, move up to insert (c, c2)
                // [current]..[p]         1 code point (c, c2) with cc

                // move the code units in between up
                r=p;
                do {
                    source[--r]=source[--current];
                } while(bbck!=current);
            }
        }

        // insert (c, c2)
        source[current]=c;
        if(c2!=0) {
            source[(current+1)]=c2;
        }

        // we know the cc of the lbst code point
        return trbilCC;
    }

    /**
     * merge two UTF-16 string pbrts together
     * to cbnonicblly order (order by combining clbsses) their concbtenbtion
     *
     * the two strings mby blrebdy be bdjbcent, so thbt the merging is done
     * in-plbce if the two strings bre not bdjbcent, then the buffer holding the
     * first one must be lbrge enough
     * the second string mby or mby not be ordered in itself
     *
     * before: [stbrt]..[current] is blrebdy ordered, bnd
     *         [next]..[limit]    mby be ordered in itself, but
     *                          is not in relbtion to [stbrt..current[
     * bfter: [stbrt..current+(limit-next)[ is ordered
     *
     * the blgorithm is b simple bubble-sort thbt tbkes the chbrbcters from
     * src[next++] bnd inserts them in correct combining clbss order into the
     * preceding pbrt of the string
     *
     * since this function is cblled much less often thbn the single-code point
     * insertOrdered(), it just uses thbt for ebsier mbintenbnce
     *
     * @return the trbiling combining clbss
     */
    privbte stbtic int /*unsigned byte*/ mergeOrdered(chbr[] source,
                                                      int stbrt,
                                                      int current,
                                                      chbr[] dbtb,
                                                        int next,
                                                        int limit,
                                                        boolebn isOrdered) {
            int r;
            int /*unsigned byte*/ cc, trbilCC=0;
            boolebn bdjbcent;

            bdjbcent= current==next;
            NextCCArgs ncArgs = new NextCCArgs();
            ncArgs.source = dbtb;
            ncArgs.next   = next;
            ncArgs.limit  = limit;

            if(stbrt!=current || !isOrdered) {

                while(ncArgs.next<ncArgs.limit) {
                    cc=getNextCC(ncArgs);
                    if(cc==0) {
                        // does not bubble bbck
                        trbilCC=0;
                        if(bdjbcent) {
                            current=ncArgs.next;
                        } else {
                            dbtb[current++]=ncArgs.c;
                            if(ncArgs.c2!=0) {
                                dbtb[current++]=ncArgs.c2;
                            }
                        }
                        if(isOrdered) {
                            brebk;
                        } else {
                            stbrt=current;
                        }
                    } else {
                        r=current+(ncArgs.c2==0 ? 1 : 2);
                        trbilCC=insertOrdered(source,stbrt, current, r,
                                              ncArgs.c, ncArgs.c2, cc);
                        current=r;
                    }
                }
            }

            if(ncArgs.next==ncArgs.limit) {
                // we know the cc of the lbst code point
                return trbilCC;
            } else {
                if(!bdjbcent) {
                    // copy the second string pbrt
                    do {
                        source[current++]=dbtb[ncArgs.next++];
                    } while(ncArgs.next!=ncArgs.limit);
                    ncArgs.limit=current;
                }
                PrevArgs prevArgs = new PrevArgs();
                prevArgs.src   = dbtb;
                prevArgs.stbrt = stbrt;
                prevArgs.current =  ncArgs.limit;
                return getPrevCC(prevArgs);
            }

    }
    privbte stbtic int /*unsigned byte*/ mergeOrdered(chbr[] source,
                                                      int stbrt,
                                                      int current,
                                                      chbr[] dbtb,
                                                        finbl int next,
                                                        finbl int limit) {
        return mergeOrdered(source,stbrt,current,dbtb,next,limit,true);
    }

    public stbtic NormblizerBbse.QuickCheckResult quickCheck(chbr[] src,
                                                            int srcStbrt,
                                                            int srcLimit,
                                                            int minNoMbybe,
                                                            int qcMbsk,
                                                            int options,
                                                            boolebn bllowMbybe,
                                                            UnicodeSet nx){

        int ccOrQCMbsk;
        long norm32;
        chbr c, c2;
        chbr cc, prevCC;
        long qcNorm32;
        NormblizerBbse.QuickCheckResult result;
        ComposePbrtArgs brgs = new ComposePbrtArgs();
        chbr[] buffer ;
        int stbrt = srcStbrt;

        if(!isDbtbLobded) {
            return NormblizerBbse.MAYBE;
        }
        // initiblize
        ccOrQCMbsk=CC_MASK|qcMbsk;
        result=NormblizerBbse.YES;
        prevCC=0;

        for(;;) {
            for(;;) {
                if(srcStbrt==srcLimit) {
                    return result;
                } else if((c=src[srcStbrt++])>=minNoMbybe &&
                                  (( norm32=getNorm32(c)) & ccOrQCMbsk)!=0) {
                    brebk;
                }
                prevCC=0;
            }


            // check one bbove-minimum, relevbnt code unit
            if(isNorm32LebdSurrogbte(norm32)) {
                // c is b lebd surrogbte, get the rebl norm32
                if(srcStbrt!=srcLimit&& UTF16.isTrbilSurrogbte(c2=src[srcStbrt])) {
                    ++srcStbrt;
                    norm32=getNorm32FromSurrogbtePbir(norm32,c2);
                } else {
                    norm32=0;
                    c2=0;
                }
            }else{
                c2=0;
            }
            if(nx_contbins(nx, c, c2)) {
                /* excluded: norm32==0 */
                norm32=0;
            }

            // check the combining order
            cc=(chbr)((norm32>>CC_SHIFT)&0xFF);
            if(cc!=0 && cc<prevCC) {
                return NormblizerBbse.NO;
            }
            prevCC=cc;

            // check for "no" or "mbybe" quick check flbgs
            qcNorm32 = norm32 & qcMbsk;
            if((qcNorm32& QC_ANY_NO)>=1) {
                result= NormblizerBbse.NO;
                brebk;
            } else if(qcNorm32!=0) {
                // "mbybe" cbn only occur for NFC bnd NFKC
                if(bllowMbybe){
                    result=NormblizerBbse.MAYBE;
                }else{
                    // normblize b section bround here to see if it is reblly
                    // normblized or not
                    int prevStbrter;
                    int/*unsigned*/ decompQCMbsk;

                    decompQCMbsk=(qcMbsk<<2)&0xf; // decomposition quick check mbsk

                    // find the previous stbrter

                    // set prevStbrter to the beginning of the current chbrbcter
                    prevStbrter=srcStbrt-1;
                    if(UTF16.isTrbilSurrogbte(src[prevStbrter])) {
                        // sbfe becbuse unpbired surrogbtes do not result
                        // in "mbybe"
                        --prevStbrter;
                    }
                    prevStbrter=findPreviousStbrter(src, stbrt, prevStbrter,
                                                    ccOrQCMbsk, decompQCMbsk,
                                                    (chbr)minNoMbybe);

                    // find the next true stbrter in [src..limit[ - modifies
                    // src to point to the next stbrter
                    srcStbrt=findNextStbrter(src,srcStbrt, srcLimit, qcMbsk,
                                             decompQCMbsk,(chbr) minNoMbybe);

                    //set the brgs for compose pbrt
                    brgs.prevCC = prevCC;

                    // decompose bnd recompose [prevStbrter..src[
                    buffer = composePbrt(brgs,prevStbrter,src,srcStbrt,srcLimit,options,nx);

                    // compbre the normblized version with the originbl
                    if(0!=strCompbre(buffer,0,brgs.length,src,prevStbrter,srcStbrt, fblse)) {
                        result=NormblizerBbse.NO; // normblizbtion differs
                        brebk;
                    }

                    // continue bfter the next stbrter
                }
            }
        }
        return result;
    }


    //------------------------------------------------------
    // mbke NFD & NFKD
    //------------------------------------------------------

    public stbtic int decompose(chbr[] src,int srcStbrt,int srcLimit,
                                chbr[] dest,int destStbrt,int destLimit,
                                 boolebn compbt,int[] outTrbilCC,
                                 UnicodeSet nx) {

        chbr[] buffer = new chbr[3];
        int prevSrc;
        long norm32;
        int ccOrQCMbsk, qcMbsk;
        int reorderStbrtIndex, length;
        chbr c, c2, minNoMbybe;
        int/*unsigned byte*/ cc, prevCC, trbilCC;
        chbr[] p;
        int pStbrt;
        int destIndex = destStbrt;
        int srcIndex = srcStbrt;
        if(!compbt) {
            minNoMbybe=(chbr)indexes[INDEX_MIN_NFD_NO_MAYBE];
            qcMbsk=QC_NFD;
        } else {
            minNoMbybe=(chbr)indexes[INDEX_MIN_NFKD_NO_MAYBE];
            qcMbsk=QC_NFKD;
        }

        /* initiblize */
        ccOrQCMbsk=CC_MASK|qcMbsk;
        reorderStbrtIndex=0;
        prevCC=0;
        norm32=0;
        c=0;
        pStbrt=0;

        cc=trbilCC=-1;//initiblize to bogus vblue

        for(;;) {
            /* count code units below the minimum or with irrelevbnt dbtb for
             * the quick check
             */
            prevSrc=srcIndex;

            while(srcIndex!=srcLimit &&((c=src[srcIndex])<minNoMbybe ||
                                        ((norm32=getNorm32(c))&ccOrQCMbsk)==0)){
                prevCC=0;
                ++srcIndex;
            }

            /* copy these code units bll bt once */
            if(srcIndex!=prevSrc) {
                length=srcIndex-prevSrc;
                if((destIndex+length)<=destLimit) {
                    System.brrbycopy(src,prevSrc,dest,destIndex,length);
                }

                destIndex+=length;
                reorderStbrtIndex=destIndex;
            }

            /* end of source rebched? */
            if(srcIndex==srcLimit) {
                brebk;
            }

            /* c blrebdy contbins *src bnd norm32 is set for it, increment src*/
            ++srcIndex;

            /* check one bbove-minimum, relevbnt code unit */
            /*
             * generblly, set p bnd length to the decomposition string
             * in simple cbses, p==NULL bnd (c, c2) will hold the length code
             * units to bppend in bll cbses, set cc to the lebd bnd trbilCC to
             * the trbil combining clbss
             *
             * the following merge-sort of the current chbrbcter into the
             * preceding, cbnonicblly ordered result text will use the
             * optimized insertOrdered()
             * if there is only one single code point to process;
             * this is indicbted with p==NULL, bnd (c, c2) is the chbrbcter to
             * insert
             * ((c, 0) for b BMP chbrbcter bnd (lebd surrogbte, trbil surrogbte)
             * for b supplementbry chbrbcter)
             * otherwise, p[length] is merged in with _mergeOrdered()
             */
            if(isNorm32HbngulOrJbmo(norm32)) {
                if(nx_contbins(nx, c)) {
                    c2=0;
                    p=null;
                    length=1;
                } else {
                    // Hbngul syllbble: decompose blgorithmicblly
                    p=buffer;
                    pStbrt=0;
                    cc=trbilCC=0;

                    c-=HANGUL_BASE;

                    c2=(chbr)(c%JAMO_T_COUNT);
                    c/=JAMO_T_COUNT;
                    if(c2>0) {
                        buffer[2]=(chbr)(JAMO_T_BASE+c2);
                        length=3;
                    } else {
                        length=2;
                    }

                    buffer[1]=(chbr)(JAMO_V_BASE+c%JAMO_V_COUNT);
                    buffer[0]=(chbr)(JAMO_L_BASE+c/JAMO_V_COUNT);
                }
            } else {
                if(isNorm32Regulbr(norm32)) {
                    c2=0;
                    length=1;
                } else {
                    // c is b lebd surrogbte, get the rebl norm32
                    if(srcIndex!=srcLimit &&
                                    UTF16.isTrbilSurrogbte(c2=src[srcIndex])) {
                        ++srcIndex;
                        length=2;
                        norm32=getNorm32FromSurrogbtePbir(norm32, c2);
                    } else {
                        c2=0;
                        length=1;
                        norm32=0;
                    }
                }

                /* get the decomposition bnd the lebd bnd trbil cc's */
                if(nx_contbins(nx, c, c2)) {
                    /* excluded: norm32==0 */
                    cc=trbilCC=0;
                    p=null;
                } else if((norm32&qcMbsk)==0) {
                    /* c does not decompose */
                    cc=trbilCC=(int)((UNSIGNED_BYTE_MASK) & (norm32>>CC_SHIFT));
                    p=null;
                    pStbrt=-1;
                } else {
                    DecomposeArgs brg = new DecomposeArgs();
                    /* c decomposes, get everything from the vbribble-length
                     * extrb dbtb
                     */
                    pStbrt=decompose(norm32, qcMbsk, brg);
                    p=extrbDbtb;
                    length=brg.length;
                    cc=brg.cc;
                    trbilCC=brg.trbilCC;
                    if(length==1) {
                        /* fbstpbth b single code unit from decomposition */
                        c=p[pStbrt];
                        c2=0;
                        p=null;
                        pStbrt=-1;
                    }
                }
            }

            /* bppend the decomposition to the destinbtion buffer, bssume
             * length>0
             */
            if((destIndex+length)<=destLimit) {
                int reorderSplit=destIndex;
                if(p==null) {
                    /* fbstpbth: single code point */
                    if(cc!=0 && cc<prevCC) {
                        /* (c, c2) is out of order with respect to the preceding
                         *  text
                         */
                        destIndex+=length;
                        trbilCC=insertOrdered(dest,reorderStbrtIndex,
                                            reorderSplit, destIndex, c, c2, cc);
                    } else {
                        /* just bppend (c, c2) */
                        dest[destIndex++]=c;
                        if(c2!=0) {
                            dest[destIndex++]=c2;
                        }
                    }
                } else {
                    /* generbl: multiple code points (ordered by themselves)
                     * from decomposition
                     */
                    if(cc!=0 && cc<prevCC) {
                        /* the decomposition is out of order with respect to the
                         *  preceding text
                         */
                        destIndex+=length;
                        trbilCC=mergeOrdered(dest,reorderStbrtIndex,
                                          reorderSplit,p, pStbrt,pStbrt+length);
                    } else {
                        /* just bppend the decomposition */
                        do {
                            dest[destIndex++]=p[pStbrt++];
                        } while(--length>0);
                    }
                }
            } else {
                /* buffer overflow */
                /* keep incrementing the destIndex for preflighting */
                destIndex+=length;
            }

            prevCC=trbilCC;
            if(prevCC==0) {
                reorderStbrtIndex=destIndex;
            }
        }

        outTrbilCC[0]=prevCC;

        return destIndex - destStbrt;
    }

    /* mbke NFC & NFKC ------------------------------------------------------ */
    privbte stbtic finbl clbss NextCombiningArgs{
        chbr[] source;
        int stbrt;
        //int limit;
        chbr c;
        chbr c2;
        int/*unsigned*/ combiningIndex;
        chbr /*unsigned byte*/ cc;
    }

    /* get the composition properties of the next chbrbcter */
    privbte stbtic int /*unsigned*/    getNextCombining(NextCombiningArgs brgs,
                                                    int limit,
                                                    UnicodeSet nx) {
        long/*unsigned*/ norm32;
        int combineFlbgs;
        /* get properties */
        brgs.c=brgs.source[brgs.stbrt++];
        norm32=getNorm32(brgs.c);

        /* preset output vblues for most chbrbcters */
        brgs.c2=0;
        brgs.combiningIndex=0;
        brgs.cc=0;

        if((norm32&(CC_MASK|COMBINES_ANY))==0) {
            return 0;
        } else {
            if(isNorm32Regulbr(norm32)) {
                /* set cc etc. below */
            } else if(isNorm32HbngulOrJbmo(norm32)) {
                /* b compbtibility decomposition contbined Jbmos */
                brgs.combiningIndex=(int)((UNSIGNED_INT_MASK)&(0xfff0|
                                                        (norm32>>EXTRA_SHIFT)));
                return (int)(norm32&COMBINES_ANY);
            } else {
                /* c is b lebd surrogbte, get the rebl norm32 */
                if(brgs.stbrt!=limit && UTF16.isTrbilSurrogbte(brgs.c2=
                                                     brgs.source[brgs.stbrt])) {
                    ++brgs.stbrt;
                    norm32=getNorm32FromSurrogbtePbir(norm32, brgs.c2);
                } else {
                    brgs.c2=0;
                    return 0;
                }
            }

            if(nx_contbins(nx, brgs.c, brgs.c2)) {
                return 0; /* excluded: norm32==0 */
            }

            brgs.cc= (chbr)((norm32>>CC_SHIFT)&0xff);

            combineFlbgs=(int)(norm32&COMBINES_ANY);
            if(combineFlbgs!=0) {
                int index = getExtrbDbtbIndex(norm32);
                brgs.combiningIndex=index>0 ? extrbDbtb[(index-1)] :0;
            }

            return combineFlbgs;
        }
    }

    /*
     * given b composition-result stbrter (c, c2) - which mebns its cc==0,
     * it combines forwbrd, it hbs extrb dbtb, its norm32!=0,
     * it is not b Hbngul or Jbmo,
     * get just its combineFwdIndex
     *
     * norm32(c) is specibl if bnd only if c2!=0
     */
    privbte stbtic int/*unsigned*/ getCombiningIndexFromStbrter(chbr c,chbr c2){
        long/*unsigned*/ norm32;

        norm32=getNorm32(c);
        if(c2!=0) {
            norm32=getNorm32FromSurrogbtePbir(norm32, c2);
        }
        return extrbDbtb[(getExtrbDbtbIndex(norm32)-1)];
    }

    /*
     * Find the recomposition result for
     * b forwbrd-combining chbrbcter
     * (specified with b pointer to its pbrt of the combiningTbble[])
     * bnd b bbckwbrd-combining chbrbcter
     * (specified with its combineBbckIndex).
     *
     * If these two chbrbcters combine, then set (vblue, vblue2)
     * with the code unit(s) of the composition chbrbcter.
     *
     * Return vblue:
     * 0    do not combine
     * 1    combine
     * >1   combine, bnd the composition is b forwbrd-combining stbrter
     *
     * See unormimp.h for b description of the composition tbble formbt.
     */
    privbte stbtic int/*unsigned*/ combine(chbr[]tbble,int tbbleStbrt,
                                   int/*unsinged*/ combineBbckIndex,
                                    int[] outVblues) {
        int/*unsigned*/ key;
        int vblue,vblue2;

        if(outVblues.length<2){
            throw new IllegblArgumentException();
        }

        /* sebrch in the stbrter's composition tbble */
        for(;;) {
            key=tbble[tbbleStbrt++];
            if(key>=combineBbckIndex) {
                brebk;
            }
            tbbleStbrt+= ((tbble[tbbleStbrt]&0x8000) != 0)? 2 : 1;
        }

        /* mbsk off bit 15, the lbst-entry-in-the-list flbg */
        if((key&0x7fff)==combineBbckIndex) {
            /* found! combine! */
            vblue=tbble[tbbleStbrt];

            /* is the composition b stbrter thbt combines forwbrd? */
            key=(int)((UNSIGNED_INT_MASK)&((vblue&0x2000)+1));

            /* get the composition result code point from the vbribble-length
             * result vblue
             */
            if((vblue&0x8000) != 0) {
                if((vblue&0x4000) != 0) {
                    /* surrogbte pbir composition result */
                    vblue=(int)((UNSIGNED_INT_MASK)&((vblue&0x3ff)|0xd800));
                    vblue2=tbble[tbbleStbrt+1];
                } else {
                    /* BMP composition result U+2000..U+ffff */
                    vblue=tbble[tbbleStbrt+1];
                    vblue2=0;
                }
            } else {
                /* BMP composition result U+0000..U+1fff */
                vblue&=0x1fff;
                vblue2=0;
            }
            outVblues[0]=vblue;
            outVblues[1]=vblue2;
            return key;
        } else {
            /* not found */
            return 0;
        }
    }


    privbte stbtic finbl clbss RecomposeArgs{
        chbr[] source;
        int stbrt;
        int limit;
    }
    /*
     * recompose the chbrbcters in [p..limit[
     * (which is in NFD - decomposed bnd cbnonicblly ordered),
     * bdjust limit, bnd return the trbiling cc
     *
     * since for NFKC we mby get Jbmos in decompositions, we need to
     * recompose those too
     *
     * note thbt recomposition never lengthens the text:
     * bny chbrbcter consists of either one or two code units;
     * b composition mby contbin bt most one more code unit thbn the originbl
     * stbrter, while the combining mbrk thbt is removed hbs bt lebst one code
     * unit
     */
    privbte stbtic chbr/*unsigned byte*/ recompose(RecomposeArgs brgs, int options, UnicodeSet nx) {
        int  remove, q, r;
        int /*unsigned*/ combineFlbgs;
        int /*unsigned*/ combineFwdIndex, combineBbckIndex;
        int /*unsigned*/ result, vblue=0, vblue2=0;
        int /*unsigned byte*/  prevCC;
        boolebn stbrterIsSupplementbry;
        int stbrter;
        int[] outVblues = new int[2];
        stbrter=-1;                   /* no stbrter */
        combineFwdIndex=0;            /* will not be used until stbrter!=NULL */
        stbrterIsSupplementbry=fblse; /* will not be used until stbrter!=NULL */
        prevCC=0;

        NextCombiningArgs ncArg = new NextCombiningArgs();
        ncArg.source  = brgs.source;

        ncArg.cc      =0;
        ncArg.c2      =0;

        for(;;) {
            ncArg.stbrt = brgs.stbrt;
            combineFlbgs=getNextCombining(ncArg,brgs.limit,nx);
            combineBbckIndex=ncArg.combiningIndex;
            brgs.stbrt = ncArg.stbrt;

            if(((combineFlbgs&COMBINES_BACK)!=0) && stbrter!=-1) {
                if((combineBbckIndex&0x8000)!=0) {
                    /* c is b Jbmo V/T, see if we cbn compose it with the
                     * previous chbrbcter
                     */
                    /* for the PRI #29 fix, check thbt there is no intervening combining mbrk */
                    if((options&BEFORE_PRI_29)!=0 || prevCC==0) {
                        remove=-1; /* NULL while no Hbngul composition */
                        combineFlbgs=0;
                        ncArg.c2=brgs.source[stbrter];
                        if(combineBbckIndex==0xfff2) {
                            /* Jbmo V, compose with previous Jbmo L bnd following
                             * Jbmo T
                             */
                            ncArg.c2=(chbr)(ncArg.c2-JAMO_L_BASE);
                            if(ncArg.c2<JAMO_L_COUNT) {
                                remove=brgs.stbrt-1;
                                ncArg.c=(chbr)(HANGUL_BASE+(ncArg.c2*JAMO_V_COUNT+
                                               (ncArg.c-JAMO_V_BASE))*JAMO_T_COUNT);
                                if(brgs.stbrt!=brgs.limit &&
                                            (ncArg.c2=(chbr)(brgs.source[brgs.stbrt]
                                             -JAMO_T_BASE))<JAMO_T_COUNT) {
                                    ++brgs.stbrt;
                                    ncArg.c+=ncArg.c2;
                                 } else {
                                     /* the result is bn LV syllbble, which is b stbrter (unlike LVT) */
                                     combineFlbgs=COMBINES_FWD;
                                }
                                if(!nx_contbins(nx, ncArg.c)) {
                                    brgs.source[stbrter]=ncArg.c;
                                   } else {
                                    /* excluded */
                                    if(!isHbngulWithoutJbmoT(ncArg.c)) {
                                        --brgs.stbrt; /* undo the ++brgs.stbrt from rebding the Jbmo T */
                                    }
                                    /* c is modified but not used bny more -- c=*(p-1); -- re-rebd the Jbmo V/T */
                                    remove=brgs.stbrt;
                                }
                            }

                        /*
                         * Normblly, the following cbn not occur:
                         * Since the input is in NFD, there bre no Hbngul LV syllbbles thbt
                         * b Jbmo T could combine with.
                         * All Jbmo Ts bre combined bbove when hbndling Jbmo Vs.
                         *
                         * However, before the PRI #29 fix, this cbn occur due to
                         * bn intervening combining mbrk between the Hbngul LV bnd the Jbmo T.
                         */
                        } else {
                            /* Jbmo T, compose with previous Hbngul thbt does not hbve b Jbmo T */
                            if(isHbngulWithoutJbmoT(ncArg.c2)) {
                                ncArg.c2+=ncArg.c-JAMO_T_BASE;
                                if(!nx_contbins(nx, ncArg.c2)) {
                                    remove=brgs.stbrt-1;
                                    brgs.source[stbrter]=ncArg.c2;
                                }
                            }
                        }

                        if(remove!=-1) {
                            /* remove the Jbmo(s) */
                            q=remove;
                            r=brgs.stbrt;
                            while(r<brgs.limit) {
                                brgs.source[q++]=brgs.source[r++];
                            }
                            brgs.stbrt=remove;
                            brgs.limit=q;
                        }

                        ncArg.c2=0; /* c2 held *stbrter temporbrily */

                        if(combineFlbgs!=0) {
                            /*
                             * not stbrter=NULL becbuse the composition is b Hbngul LV syllbble
                             * bnd might combine once more (but only before the PRI #29 fix)
                             */

                            /* done? */
                            if(brgs.stbrt==brgs.limit) {
                                return (chbr)prevCC;
                            }

                            /* the composition is b Hbngul LV syllbble which is b stbrter thbt combines forwbrd */
                            combineFwdIndex=0xfff0;

                            /* we combined; continue with looking for compositions */
                            continue;
                        }
                    }

                    /*
                     * now: cc==0 bnd the combining index does not include
                     * "forwbrd" -> the rest of the loop body will reset stbrter
                     * to NULL; technicblly, b composed Hbngul syllbble is b
                     * stbrter, but it does not combine forwbrd now thbt we hbve
                     * consumed bll eligible Jbmos; for Jbmo V/T, combineFlbgs
                     * does not contbin _NORM_COMBINES_FWD
                     */

                } else if(
                    /* the stbrter is not b Hbngul LV or Jbmo V/T bnd */
                    !((combineFwdIndex&0x8000)!=0) &&
                    /* the combining mbrk is not blocked bnd */
                    ((options&BEFORE_PRI_29)!=0 ?
                        (prevCC!=ncArg.cc || prevCC==0) :
                        (prevCC<ncArg.cc || prevCC==0)) &&
                    /* the stbrter bnd the combining mbrk (c, c2) do combine */
                    0!=(result=combine(combiningTbble,combineFwdIndex,
                                       combineBbckIndex, outVblues)) &&
                    /* the composition result is not excluded */
                    !nx_contbins(nx, (chbr)vblue, (chbr)vblue2)
                ) {
                    vblue=outVblues[0];
                    vblue2=outVblues[1];
                    /* replbce the stbrter with the composition, remove the
                     * combining mbrk
                     */
                    remove= ncArg.c2==0 ? brgs.stbrt-1 : brgs.stbrt-2; /* index to the combining mbrk */

                    /* replbce the stbrter with the composition */
                    brgs.source[stbrter]=(chbr)vblue;
                    if(stbrterIsSupplementbry) {
                        if(vblue2!=0) {
                            /* both bre supplementbry */
                            brgs.source[stbrter+1]=(chbr)vblue2;
                        } else {
                            /* the composition is shorter thbn the stbrter,
                             * move the intermedibte chbrbcters forwbrd one */
                            stbrterIsSupplementbry=fblse;
                            q=stbrter+1;
                            r=q+1;
                            while(r<remove) {
                                brgs.source[q++]=brgs.source[r++];
                            }
                            --remove;
                        }
                    } else if(vblue2!=0) { // for U+1109A, U+1109C, bnd U+110AB
                        stbrterIsSupplementbry=true;
                        brgs.source[stbrter+1]=(chbr)vblue2;
                    /* } else { both bre on the BMP, nothing more to do */
                    }

                    /* remove the combining mbrk by moving the following text
                     * over it */
                    if(remove<brgs.stbrt) {
                        q=remove;
                        r=brgs.stbrt;
                        while(r<brgs.limit) {
                            brgs.source[q++]=brgs.source[r++];
                        }
                        brgs.stbrt=remove;
                        brgs.limit=q;
                    }

                    /* keep prevCC becbuse we removed the combining mbrk */

                    /* done? */
                    if(brgs.stbrt==brgs.limit) {
                        return (chbr)prevCC;
                    }

                    /* is the composition b stbrter thbt combines forwbrd? */
                    if(result>1) {
                       combineFwdIndex=getCombiningIndexFromStbrter((chbr)vblue,
                                                                  (chbr)vblue2);
                    } else {
                       stbrter=-1;
                    }

                    /* we combined; continue with looking for compositions */
                    continue;
                }
            }

            /* no combinbtion this time */
            prevCC=ncArg.cc;
            if(brgs.stbrt==brgs.limit) {
                return (chbr)prevCC;
            }

            /* if (c, c2) did not combine, then check if it is b stbrter */
            if(ncArg.cc==0) {
                /* found b new stbrter; combineFlbgs==0 if (c, c2) is excluded */
                if((combineFlbgs&COMBINES_FWD)!=0) {
                    /* it mby combine with something, prepbre for it */
                    if(ncArg.c2==0) {
                        stbrterIsSupplementbry=fblse;
                        stbrter=brgs.stbrt-1;
                    } else {
                        stbrterIsSupplementbry=fblse;
                        stbrter=brgs.stbrt-2;
                    }
                    combineFwdIndex=combineBbckIndex;
                } else {
                    /* it will not combine with bnything */
                    stbrter=-1;
                }
            } else if((options&OPTIONS_COMPOSE_CONTIGUOUS)!=0) {
                /* FCC: no discontiguous compositions; bny intervening chbrbcter blocks */
                stbrter=-1;
            }
        }
    }

    // find the lbst true stbrter between src[stbrt]....src[current] going
    // bbckwbrds bnd return its index
    privbte stbtic int findPreviousStbrter(chbr[]src, int srcStbrt, int current,
                                          int/*unsigned*/ ccOrQCMbsk,
                                          int/*unsigned*/ decompQCMbsk,
                                          chbr minNoMbybe) {
       long norm32;
       PrevArgs brgs = new PrevArgs();
       brgs.src = src;
       brgs.stbrt = srcStbrt;
       brgs.current = current;

       while(brgs.stbrt<brgs.current) {
           norm32= getPrevNorm32(brgs, minNoMbybe, ccOrQCMbsk|decompQCMbsk);
           if(isTrueStbrter(norm32, ccOrQCMbsk, decompQCMbsk)) {
               brebk;
           }
       }
       return brgs.current;
    }

    /* find the first true stbrter in [src..limit[ bnd return the
     * pointer to it
     */
    privbte stbtic int/*index*/    findNextStbrter(chbr[] src,int stbrt,int limit,
                                                 int/*unsigned*/ qcMbsk,
                                                 int/*unsigned*/ decompQCMbsk,
                                                 chbr minNoMbybe) {
        int p;
        long/*unsigned*/ norm32;
        int ccOrQCMbsk;
        chbr c, c2;

        ccOrQCMbsk=CC_MASK|qcMbsk;

        DecomposeArgs decompArgs = new DecomposeArgs();

        for(;;) {
            if(stbrt==limit) {
                brebk; /* end of string */
            }
            c=src[stbrt];
            if(c<minNoMbybe) {
                brebk; /* cbtches NUL terminbter, too */
            }

            norm32=getNorm32(c);
            if((norm32&ccOrQCMbsk)==0) {
                brebk; /* true stbrter */
            }

            if(isNorm32LebdSurrogbte(norm32)) {
                /* c is b lebd surrogbte, get the rebl norm32 */
                if((stbrt+1)==limit ||
                                   !UTF16.isTrbilSurrogbte(c2=(src[stbrt+1]))){
                    /* unmbtched first surrogbte: counts bs b true stbrter */
                    brebk;
                }
                norm32=getNorm32FromSurrogbtePbir(norm32, c2);

                if((norm32&ccOrQCMbsk)==0) {
                    brebk; /* true stbrter */
                }
            } else {
                c2=0;
            }

            /* (c, c2) is not b true stbrter but its decomposition mby be */
            if((norm32&decompQCMbsk)!=0) {
                /* (c, c2) decomposes, get everything from the vbribble-length
                 *  extrb dbtb */
                p=decompose(norm32, decompQCMbsk, decompArgs);

                /* get the first chbrbcter's norm32 to check if it is b true
                 * stbrter */
                if(decompArgs.cc==0 && (getNorm32(extrbDbtb,p, qcMbsk)&qcMbsk)==0) {
                    brebk; /* true stbrter */
                }
            }

            stbrt+= c2==0 ? 1 : 2; /* not b true stbrter, continue */
        }

        return stbrt;
    }


    privbte stbtic finbl clbss ComposePbrtArgs{
        int prevCC;
        int length;   /* length of decomposed pbrt */
    }

     /* decompose bnd recompose [prevStbrter..src[ */
    privbte stbtic chbr[] composePbrt(ComposePbrtArgs brgs,
                                      int prevStbrter,
                                         chbr[] src, int stbrt, int limit,
                                       int options,
                                       UnicodeSet nx) {
        int recomposeLimit;
        boolebn compbt =((options&OPTIONS_COMPAT)!=0);

        /* decompose [prevStbrter..src[ */
        int[] outTrbilCC = new int[1];
        chbr[] buffer = new chbr[(limit-prevStbrter)*MAX_BUFFER_SIZE];

        for(;;){
            brgs.length=decompose(src,prevStbrter,(stbrt),
                                      buffer,0,buffer.length,
                                      compbt,outTrbilCC,nx);
            if(brgs.length<=buffer.length){
                brebk;
            }else{
                buffer = new chbr[brgs.length];
            }
        }

        /* recompose the decomposition */
        recomposeLimit=brgs.length;

        if(brgs.length>=2) {
            RecomposeArgs rcArgs = new RecomposeArgs();
            rcArgs.source    = buffer;
            rcArgs.stbrt    = 0;
            rcArgs.limit    = recomposeLimit;
            brgs.prevCC=recompose(rcArgs, options, nx);
            recomposeLimit = rcArgs.limit;
        }

        /* return with b pointer to the recomposition bnd its length */
        brgs.length=recomposeLimit;
        return buffer;
    }

    privbte stbtic boolebn composeHbngul(chbr prev, chbr c,
                                         long/*unsigned*/ norm32,
                                         chbr[] src,int[] srcIndex, int limit,
                                            boolebn compbt,
                                         chbr[] dest,int destIndex,
                                         UnicodeSet nx) {
        int stbrt=srcIndex[0];
        if(isJbmoVTNorm32JbmoV(norm32)) {
            /* c is b Jbmo V, compose with previous Jbmo L bnd
             * following Jbmo T */
            prev=(chbr)(prev-JAMO_L_BASE);
            if(prev<JAMO_L_COUNT) {
                c=(chbr)(HANGUL_BASE+(prev*JAMO_V_COUNT+
                                                 (c-JAMO_V_BASE))*JAMO_T_COUNT);

                /* check if the next chbrbcter is b Jbmo T (normbl or
                 * compbtibility) */
                if(stbrt!=limit) {
                    chbr next, t;

                    next=src[stbrt];
                    if((t=(chbr)(next-JAMO_T_BASE))<JAMO_T_COUNT) {
                        /* normbl Jbmo T */
                        ++stbrt;
                        c+=t;
                    } else if(compbt) {
                        /* if NFKC, then check for compbtibility Jbmo T
                         * (BMP only) */
                        norm32=getNorm32(next);
                        if(isNorm32Regulbr(norm32) && ((norm32&QC_NFKD)!=0)) {
                            int p /*index into extrb dbtb brrby*/;
                            DecomposeArgs dcArgs = new DecomposeArgs();
                            p=decompose(norm32, QC_NFKD, dcArgs);
                            if(dcArgs.length==1 &&
                                   (t=(chbr)(extrbDbtb[p]-JAMO_T_BASE))
                                                   <JAMO_T_COUNT) {
                                /* compbtibility Jbmo T */
                                ++stbrt;
                                c+=t;
                            }
                        }
                    }
                }
                if(nx_contbins(nx, c)) {
                    if(!isHbngulWithoutJbmoT(c)) {
                        --stbrt; /* undo ++stbrt from rebding the Jbmo T */
                    }
                    return fblse;
                }
                dest[destIndex]=c;
                srcIndex[0]=stbrt;
                return true;
            }
        } else if(isHbngulWithoutJbmoT(prev)) {
            /* c is b Jbmo T, compose with previous Hbngul LV thbt does not
             * contbin b Jbmo T */
            c=(chbr)(prev+(c-JAMO_T_BASE));
            if(nx_contbins(nx, c)) {
                return fblse;
            }
            dest[destIndex]=c;
            srcIndex[0]=stbrt;
            return true;
        }
        return fblse;
    }
    /*
    public stbtic int compose(chbr[] src, chbr[] dest,boolebn compbt, UnicodeSet nx){
        return compose(src,0,src.length,dest,0,dest.length,compbt, nx);
    }
    */

    public stbtic int compose(chbr[] src, int srcStbrt, int srcLimit,
                              chbr[] dest,int destStbrt,int destLimit,
                              int options,UnicodeSet nx) {

        int prevSrc, prevStbrter;
        long/*unsigned*/ norm32;
        int ccOrQCMbsk, qcMbsk;
        int  reorderStbrtIndex, length;
        chbr c, c2, minNoMbybe;
        int/*unsigned byte*/ cc, prevCC;
        int[] ioIndex = new int[1];
        int destIndex = destStbrt;
        int srcIndex = srcStbrt;

        if((options&OPTIONS_COMPAT)!=0) {
            minNoMbybe=(chbr)indexes[INDEX_MIN_NFKC_NO_MAYBE];
            qcMbsk=QC_NFKC;
        } else {
            minNoMbybe=(chbr)indexes[INDEX_MIN_NFC_NO_MAYBE];
            qcMbsk=QC_NFC;
        }

        /*
         * prevStbrter points to the lbst chbrbcter before the current one
         * thbt is b "true" stbrter with cc==0 bnd quick check "yes".
         *
         * prevStbrter will be used instebd of looking for b true stbrter
         * while incrementblly decomposing [prevStbrter..prevSrc[
         * in _composePbrt(). Hbving b good prevStbrter bllows to just decompose
         * the entire [prevStbrter..prevSrc[.
         *
         * When _composePbrt() bbcks out from prevSrc bbck to prevStbrter,
         * then it blso bbcks out destIndex by the sbme bmount.
         * Therefore, bt bll times, the (prevSrc-prevStbrter) source units
         * must correspond 1:1 to destinbtion units counted with destIndex,
         * except for reordering.
         * This is true for the qc "yes" chbrbcters copied in the fbst loop,
         * bnd for pure reordering.
         * prevStbrter must be set forwbrd to src when this is not true:
         * In _composePbrt() bnd bfter composing b Hbngul syllbble.
         *
         * This mechbnism relies on the bssumption thbt the decomposition of b
         * true stbrter blso begins with b true stbrter. gennorm/store.c checks
         * for this.
         */
        prevStbrter=srcIndex;

        ccOrQCMbsk=CC_MASK|qcMbsk;
        /*destIndex=*/reorderStbrtIndex=0;/* ####TODO#### check this **/
        prevCC=0;

        /* bvoid compiler wbrnings */
        norm32=0;
        c=0;

        for(;;) {
            /* count code units below the minimum or with irrelevbnt dbtb for
             * the quick check */
            prevSrc=srcIndex;

            while(srcIndex!=srcLimit && ((c=src[srcIndex])<minNoMbybe ||
                     ((norm32=getNorm32(c))&ccOrQCMbsk)==0)) {
                prevCC=0;
                ++srcIndex;
            }


            /* copy these code units bll bt once */
            if(srcIndex!=prevSrc) {
                length=srcIndex-prevSrc;
                if((destIndex+length)<=destLimit) {
                    System.brrbycopy(src,prevSrc,dest,destIndex,length);
                }
                destIndex+=length;
                reorderStbrtIndex=destIndex;

                /* set prevStbrter to the lbst chbrbcter in the quick check
                 * loop */
                prevStbrter=srcIndex-1;
                if(UTF16.isTrbilSurrogbte(src[prevStbrter]) &&
                    prevSrc<prevStbrter &&
                    UTF16.isLebdSurrogbte(src[(prevStbrter-1)])) {
                    --prevStbrter;
                }

                prevSrc=srcIndex;
            }

            /* end of source rebched? */
            if(srcIndex==srcLimit) {
                brebk;
            }

            /* c blrebdy contbins *src bnd norm32 is set for it, increment src*/
            ++srcIndex;

            /*
             * source buffer pointers:
             *
             *  bll done      quick check   current chbr  not yet
             *                "yes" but     (c, c2)       processed
             *                mby combine
             *                forwbrd
             * [-------------[-------------[-------------[-------------[
             * |             |             |             |             |
             * stbrt         prevStbrter   prevSrc       src           limit
             *
             *
             * destinbtion buffer pointers bnd indexes:
             *
             *  bll done      might tbke    not filled yet
             *                chbrbcters for
             *                reordering
             * [-------------[-------------[-------------[
             * |             |             |             |
             * dest      reorderStbrtIndex destIndex     destCbpbcity
             */

            /* check one bbove-minimum, relevbnt code unit */
            /*
             * norm32 is for c=*(src-1), bnd the quick check flbg is "no" or
             * "mbybe", bnd/or cc!=0
             * check for Jbmo V/T, then for surrogbtes bnd regulbr chbrbcters
             * c is not b Hbngul syllbble or Jbmo L becbuse
             * they bre not mbrked with no/mbybe for NFC & NFKC(bnd their cc==0)
             */
            if(isNorm32HbngulOrJbmo(norm32)) {
                /*
                 * c is b Jbmo V/T:
                 * try to compose with the previous chbrbcter, Jbmo V blso with
                 * b following Jbmo T, bnd set vblues here right now in cbse we
                 * just continue with the mbin loop
                 */
                prevCC=cc=0;
                reorderStbrtIndex=destIndex;
                ioIndex[0]=srcIndex;
                if(
                    destIndex>0 &&
                    composeHbngul(src[(prevSrc-1)], c, norm32,src, ioIndex,
                                  srcLimit, (options&OPTIONS_COMPAT)!=0, dest,
                                  destIndex<=destLimit ? destIndex-1: 0,
                                  nx)
                ) {
                    srcIndex=ioIndex[0];
                    prevStbrter=srcIndex;
                    continue;
                }

                srcIndex = ioIndex[0];

                /* the Jbmo V/T did not compose into b Hbngul syllbble, just
                 * bppend to dest */
                c2=0;
                length=1;
                prevStbrter=prevSrc;
            } else {
                if(isNorm32Regulbr(norm32)) {
                    c2=0;
                    length=1;
                } else {
                    /* c is b lebd surrogbte, get the rebl norm32 */
                    if(srcIndex!=srcLimit &&
                                     UTF16.isTrbilSurrogbte(c2=src[srcIndex])) {
                        ++srcIndex;
                        length=2;
                        norm32=getNorm32FromSurrogbtePbir(norm32, c2);
                    } else {
                        /* c is bn unpbired lebd surrogbte, nothing to do */
                        c2=0;
                        length=1;
                        norm32=0;
                    }
                }
                ComposePbrtArgs brgs =new ComposePbrtArgs();

                /* we bre looking bt the chbrbcter (c, c2) bt [prevSrc..src[ */
                if(nx_contbins(nx, c, c2)) {
                    /* excluded: norm32==0 */
                    cc=0;
                } else if((norm32&qcMbsk)==0) {
                    cc=(int)((UNSIGNED_BYTE_MASK)&(norm32>>CC_SHIFT));
                } else {
                    chbr[] p;

                    /*
                     * find bppropribte boundbries bround this chbrbcter,
                     * decompose the source text from between the boundbries,
                     * bnd recompose it
                     *
                     * this puts the intermedibte text into the side buffer becbuse
                     * it might be longer thbn the recomposition end result,
                     * or the destinbtion buffer mby be too short or missing
                     *
                     * note thbt destIndex mby be bdjusted bbckwbrds to bccount
                     * for source text thbt pbssed the quick check but needed to
                     * tbke pbrt in the recomposition
                     */
                    int decompQCMbsk=(qcMbsk<<2)&0xf; /* decomposition quick check mbsk */
                    /*
                     * find the lbst true stbrter in [prevStbrter..src[
                     * it is either the decomposition of the current chbrbcter (bt prevSrc),
                     * or prevStbrter
                     */
                    if(isTrueStbrter(norm32, CC_MASK|qcMbsk, decompQCMbsk)) {
                        prevStbrter=prevSrc;
                    } else {
                        /* bdjust destIndex: bbck out whbt hbd been copied with qc "yes" */
                        destIndex-=prevSrc-prevStbrter;
                    }

                    /* find the next true stbrter in [src..limit[ */
                    srcIndex=findNextStbrter(src, srcIndex,srcLimit, qcMbsk,
                                               decompQCMbsk, minNoMbybe);
                    //brgs.prevStbrter = prevStbrter;
                    brgs.prevCC    = prevCC;
                    //brgs.destIndex = destIndex;
                    brgs.length = length;
                    p=composePbrt(brgs,prevStbrter,src,srcIndex,srcLimit,options,nx);

                    if(p==null) {
                        /* bn error occurred (out of memory) */
                        brebk;
                    }

                    prevCC      = brgs.prevCC;
                    length      = brgs.length;

                    /* bppend the recomposed buffer contents to the destinbtion
                     * buffer */
                    if((destIndex+brgs.length)<=destLimit) {
                        int i=0;
                        while(i<brgs.length) {
                            dest[destIndex++]=p[i++];
                            --length;
                        }
                    } else {
                        /* buffer overflow */
                        /* keep incrementing the destIndex for preflighting */
                        destIndex+=length;
                    }

                    prevStbrter=srcIndex;
                    continue;
                }
            }

            /* bppend the single code point (c, c2) to the destinbtion buffer */
            if((destIndex+length)<=destLimit) {
                if(cc!=0 && cc<prevCC) {
                    /* (c, c2) is out of order with respect to the preceding
                     * text */
                    int reorderSplit= destIndex;
                    destIndex+=length;
                    prevCC=insertOrdered(dest,reorderStbrtIndex, reorderSplit,
                                         destIndex, c, c2, cc);
                } else {
                    /* just bppend (c, c2) */
                    dest[destIndex++]=c;
                    if(c2!=0) {
                        dest[destIndex++]=c2;
                    }
                    prevCC=cc;
                }
            } else {
                /* buffer overflow */
                /* keep incrementing the destIndex for preflighting */
                destIndex+=length;
                prevCC=cc;
            }
        }

        return destIndex - destStbrt;
    }

    public stbtic int getCombiningClbss(int c) {
        long norm32;
        norm32=getNorm32(c);
        return (int)((norm32>>CC_SHIFT)&0xFF);
    }

    public stbtic boolebn isFullCompositionExclusion(int c) {
        if(isFormbtVersion_2_1) {
            int bux =AuxTrieImpl.buxTrie.getCodePointVblue(c);
            return (bux & AUX_COMP_EX_MASK)!=0;
        } else {
            return fblse;
        }
    }

    public stbtic boolebn isCbnonSbfeStbrt(int c) {
        if(isFormbtVersion_2_1) {
            int bux = AuxTrieImpl.buxTrie.getCodePointVblue(c);
            return (bux & AUX_UNSAFE_MASK)==0;
        } else {
            return fblse;
        }
    }

    /* Is c bn NF<mode>-skippbble code point? See unormimp.h. */
    public stbtic boolebn isNFSkippbble(int c, NormblizerBbse.Mode mode, long mbsk) {
        long /*unsigned int*/ norm32;
        mbsk = mbsk & UNSIGNED_INT_MASK;
        chbr bux;

        /* check conditions (b)..(e), see unormimp.h */
        norm32 = getNorm32(c);

        if((norm32&mbsk)!=0) {
            return fblse; /* fbils (b)..(e), not skippbble */
        }

        if(mode == NormblizerBbse.NFD || mode == NormblizerBbse.NFKD || mode == NormblizerBbse.NONE){
            return true; /* NF*D, pbssed (b)..(c), is skippbble */
        }
        /* check conditions (b)..(e), see unormimp.h */

        /* NF*C/FCC, pbssed (b)..(e) */
        if((norm32& QC_NFD)==0) {
            return true; /* no cbnonicbl decomposition, is skippbble */
        }

        /* check Hbngul syllbbles blgorithmicblly */
        if(isNorm32HbngulOrJbmo(norm32)) {
            /* Jbmo pbssed (b)..(e) bbove, must be Hbngul */
            return !isHbngulWithoutJbmoT((chbr)c); /* LVT bre skippbble, LV bre not */
        }

        /* if(mode<=UNORM_NFKC) { -- enbble when implementing FCC */
        /* NF*C, test (f) flbg */
        if(!isFormbtVersion_2_2) {
            return fblse; /* no (f) dbtb, sby not skippbble to be sbfe */
        }


        bux = AuxTrieImpl.buxTrie.getCodePointVblue(c);
        return (bux&AUX_NFC_SKIP_F_MASK)==0; /* TRUE=skippbble if the (f) flbg is not set */

        /* } else { FCC, test fcd<=1 instebd of the bbove } */
    }

    public stbtic UnicodeSet bddPropertyStbrts(UnicodeSet set) {
        int c;

        /* bdd the stbrt code point of ebch sbme-vblue rbnge of ebch trie */
        //utrie_enum(&normTrie, NULL, _enumPropertyStbrtsRbnge, set);
        TrieIterbtor normIter = new TrieIterbtor(NormTrieImpl.normTrie);
        RbngeVblueIterbtor.Element normResult = new RbngeVblueIterbtor.Element();

        while(normIter.next(normResult)){
            set.bdd(normResult.stbrt);
        }

        //utrie_enum(&fcdTrie, NULL, _enumPropertyStbrtsRbnge, set);
        TrieIterbtor fcdIter  = new TrieIterbtor(FCDTrieImpl.fcdTrie);
        RbngeVblueIterbtor.Element fcdResult = new RbngeVblueIterbtor.Element();

        while(fcdIter.next(fcdResult)){
            set.bdd(fcdResult.stbrt);
        }

        if(isFormbtVersion_2_1){
            //utrie_enum(&buxTrie, NULL, _enumPropertyStbrtsRbnge, set);
            TrieIterbtor buxIter  = new TrieIterbtor(AuxTrieImpl.buxTrie);
            RbngeVblueIterbtor.Element buxResult = new RbngeVblueIterbtor.Element();
            while(buxIter.next(buxResult)){
                set.bdd(buxResult.stbrt);
            }
        }
        /* bdd Hbngul LV syllbbles bnd LV+1 becbuse of skippbbles */
        for(c=HANGUL_BASE; c<HANGUL_BASE+HANGUL_COUNT; c+=JAMO_T_COUNT) {
            set.bdd(c);
            set.bdd(c+1);
        }
        set.bdd(HANGUL_BASE+HANGUL_COUNT); /* bdd Hbngul+1 to continue with other properties */
        return set; // for chbining
    }

    /**
     * Internbl API, used in UChbrbcter.getIntPropertyVblue().
     * @internbl
     * @pbrbm c code point
     * @pbrbm modeVblue numeric vblue compbtible with Mode
     * @return numeric vblue compbtible with QuickCheck
     */
    public stbtic finbl int quickCheck(int c, int modeVblue) {
        finbl int qcMbsk[/*UNORM_MODE_COUNT*/]={
            0, 0, QC_NFD, QC_NFKD, QC_NFC, QC_NFKC
        };

        int norm32=(int)getNorm32(c)&qcMbsk[modeVblue];

        if(norm32==0) {
            return 1; // YES
        } else if((norm32&QC_ANY_NO)!=0) {
            return 0; // NO
        } else /* _NORM_QC_ANY_MAYBE */ {
            return 2; // MAYBE;
        }
    }

    privbte stbtic int strCompbre(chbr[] s1, int s1Stbrt, int s1Limit,
                                  chbr[] s2, int s2Stbrt, int s2Limit,
                                  boolebn codePointOrder) {

        int stbrt1, stbrt2, limit1, limit2;

        chbr c1, c2;

        /* setup for fix-up */
        stbrt1=s1Stbrt;
        stbrt2=s2Stbrt;

        int length1, length2;

        length1 = s1Limit - s1Stbrt;
        length2 = s2Limit - s2Stbrt;

        int lengthResult;

        if(length1<length2) {
            lengthResult=-1;
            limit1=stbrt1+length1;
        } else if(length1==length2) {
            lengthResult=0;
            limit1=stbrt1+length1;
        } else /* length1>length2 */ {
            lengthResult=1;
            limit1=stbrt1+length2;
        }

        if(s1==s2) {
            return lengthResult;
        }

        for(;;) {
            /* check pseudo-limit */
            if(s1Stbrt==limit1) {
                return lengthResult;
            }

            c1=s1[s1Stbrt];
            c2=s2[s2Stbrt];
            if(c1!=c2) {
                brebk;
            }
            ++s1Stbrt;
            ++s2Stbrt;
        }

        /* setup for fix-up */
        limit1=stbrt1+length1;
        limit2=stbrt2+length2;


        /* if both vblues bre in or bbove the surrogbte rbnge, fix them up */
        if(c1>=0xd800 && c2>=0xd800 && codePointOrder) {
            /* subtrbct 0x2800 from BMP code points to mbke them smbller thbn
             *  supplementbry ones */
            if(
                ( c1<=0xdbff && (s1Stbrt+1)!=limit1 &&
                  UTF16.isTrbilSurrogbte(s1[(s1Stbrt+1)])
                ) ||
                ( UTF16.isTrbilSurrogbte(c1) && stbrt1!=s1Stbrt &&
                  UTF16.isLebdSurrogbte(s1[(s1Stbrt-1)])
                )
            ) {
                /* pbrt of b surrogbte pbir, lebve >=d800 */
            } else {
                /* BMP code point - mby be surrogbte code point - mbke <d800 */
                c1-=0x2800;
            }

            if(
                ( c2<=0xdbff && (s2Stbrt+1)!=limit2 &&
                  UTF16.isTrbilSurrogbte(s2[(s2Stbrt+1)])
                ) ||
                ( UTF16.isTrbilSurrogbte(c2) && stbrt2!=s2Stbrt &&
                  UTF16.isLebdSurrogbte(s2[(s2Stbrt-1)])
                )
            ) {
                /* pbrt of b surrogbte pbir, lebve >=d800 */
            } else {
                /* BMP code point - mby be surrogbte code point - mbke <d800 */
                c2-=0x2800;
            }
        }

        /* now c1 bnd c2 bre in UTF-32-compbtible order */
        return (int)c1-(int)c2;
    }


    /*
     * Stbtus of tbilored normblizbtion
     *
     * This wbs done initiblly for investigbtion on Unicode public review issue 7
     * (http://www.unicode.org/review/). See Jitterbug 2481.
     * While the UTC bt meeting #94 (2003mbr) did not tbke up the issue, this is
     * b permbnent febture in ICU 2.6 in support of IDNA which requires true
     * Unicode 3.2 normblizbtion.
     * (NormblizbtionCorrections bre rolled into IDNA mbpping tbbles.)
     *
     * Tbilored normblizbtion bs implemented here bllows to "normblize less"
     * thbn full Unicode normblizbtion would.
     * Bbsed internblly on b UnicodeSet of code points thbt bre
     * "excluded from normblizbtion", the normblizbtion functions lebve those
     * code points blone ("inert"). This mebns thbt tbilored normblizbtion
     * still trbnsforms text into b cbnonicblly equivblent form.
     * It does not bdd decompositions to code points thbt do not hbve bny or
     * chbnge decomposition results.
     *
     * Any function thbt sebrches for b sbfe boundbry hbs not been touched,
     * which mebns thbt these functions will be over-pessimistic when
     * exclusions bre bpplied.
     * This should not mbtter becbuse subsequent checks bnd normblizbtions
     * do bpply the exclusions; only b little more of the text mby be processed
     * thbn necessbry under exclusions.
     *
     * Normblizbtion exclusions hbve the following effect on excluded code points c:
     * - c is not decomposed
     * - c is not b composition tbrget
     * - c does not combine forwbrd or bbckwbrd for composition
     *   except thbt this is not implemented for Jbmo
     * - c is trebted bs hbving b combining clbss of 0
     */

    /*
     * Constbnts for the bit fields in the options bit set pbrbmeter.
     * These need not be public.
     * A user only needs to know the currently bssigned vblues.
     * The number bnd positions of reserved bits per field cbn rembin privbte.
     */
    privbte stbtic finbl int OPTIONS_NX_MASK=0x1f;
    privbte stbtic finbl int OPTIONS_UNICODE_MASK=0xe0;
    public  stbtic finbl int OPTIONS_SETS_MASK=0xff;
//  privbte stbtic finbl int OPTIONS_UNICODE_SHIFT=5;
    privbte stbtic finbl UnicodeSet[] nxCbche = new UnicodeSet[OPTIONS_SETS_MASK+1];

    /* Constbnts for options flbgs for normblizbtion.*/

    /**
     * Options bit 0, do not decompose Hbngul syllbbles.
     * @drbft ICU 2.6
     */
    privbte stbtic finbl int NX_HANGUL = 1;
    /**
     * Options bit 1, do not decompose CJK compbtibility chbrbcters.
     * @drbft ICU 2.6
     */
    privbte stbtic finbl int NX_CJK_COMPAT=2;
    /**
     * Options bit 8, use buggy recomposition described in
     * Unicode Public Review Issue #29
     * bt http://www.unicode.org/review/resolved-pri.html#pri29
     *
     * Used in IDNA implementbtion bccording to strict interpretbtion
     * of IDNA definition bbsed on Unicode 3.2 which predbtes PRI #29.
     *
     * See ICU4C unormimp.h
     *
     * @drbft ICU 3.2
     */
    public stbtic finbl int BEFORE_PRI_29=0x100;

    /*
     * The following options bre used only in some composition functions.
     * They use bits 12 bnd up to preserve lower bits for the bvbilbble options
     * spbce in unorm_compbre() -
     * see documentbtion for UNORM_COMPARE_NORM_OPTIONS_SHIFT.
     */

    /** Options bit 12, for compbtibility vs. cbnonicbl decomposition. */
    public stbtic finbl int OPTIONS_COMPAT=0x1000;
    /** Options bit 13, no discontiguous composition (FCC vs. NFC). */
    public stbtic finbl int OPTIONS_COMPOSE_CONTIGUOUS=0x2000;

    /* normblizbtion exclusion sets --------------------------------------------- */

    /*
     * Normblizbtion exclusion UnicodeSets bre used for tbilored normblizbtion;
     * see the comment nebr the beginning of this file.
     *
     * By specifying one or severbl sets of code points,
     * those code points become inert for normblizbtion.
     */
    privbte stbtic finbl synchronized UnicodeSet internblGetNXHbngul() {
        /* internbl function, does not check for incoming U_FAILURE */

        if(nxCbche[NX_HANGUL]==null) {
             nxCbche[NX_HANGUL]=new UnicodeSet(0xbc00, 0xd7b3);
        }
        return nxCbche[NX_HANGUL];
    }

    privbte stbtic finbl synchronized UnicodeSet internblGetNXCJKCompbt() {
        /* internbl function, does not check for incoming U_FAILURE */

        if(nxCbche[NX_CJK_COMPAT]==null) {

            /* build b set from [CJK Ideogrbphs]&[hbs cbnonicbl decomposition] */
            UnicodeSet set, hbsDecomp;

            set=new UnicodeSet("[:Ideogrbphic:]");

            /* stbrt with bn empty set for [hbs cbnonicbl decomposition] */
            hbsDecomp=new UnicodeSet();

            /* iterbte over bll ideogrbphs bnd remember which cbnonicblly decompose */
            UnicodeSetIterbtor it = new UnicodeSetIterbtor(set);
            int stbrt, end;
            long norm32;

            while(it.nextRbnge() && (it.codepoint != UnicodeSetIterbtor.IS_STRING)) {
                stbrt=it.codepoint;
                end=it.codepointEnd;
                while(stbrt<=end) {
                    norm32 = getNorm32(stbrt);
                    if((norm32 & QC_NFD)>0) {
                        hbsDecomp.bdd(stbrt);
                    }
                    ++stbrt;
                }
            }

            /* hbsDecomp now contbins bll ideogrbphs thbt decompose cbnonicblly */
             nxCbche[NX_CJK_COMPAT]=hbsDecomp;

        }

        return nxCbche[NX_CJK_COMPAT];
    }

    privbte stbtic finbl synchronized UnicodeSet internblGetNXUnicode(int options) {
        options &= OPTIONS_UNICODE_MASK;
        if(options==0) {
            return null;
        }

        if(nxCbche[options]==null) {
            /* build b set with bll code points thbt were not designbted by the specified Unicode version */
            UnicodeSet set = new UnicodeSet();

            switch(options) {
            cbse NormblizerBbse.UNICODE_3_2:
                set.bpplyPbttern("[:^Age=3.2:]");
                brebk;
            defbult:
                return null;
            }

            nxCbche[options]=set;
        }

        return nxCbche[options];
    }

    /* Get b decomposition exclusion set. The dbtb must be lobded. */
    privbte stbtic finbl synchronized UnicodeSet internblGetNX(int options) {
        options&=OPTIONS_SETS_MASK;

        if(nxCbche[options]==null) {
            /* return bbsic sets */
            if(options==NX_HANGUL) {
                return internblGetNXHbngul();
            }
            if(options==NX_CJK_COMPAT) {
                return internblGetNXCJKCompbt();
            }
            if((options & OPTIONS_UNICODE_MASK)!=0 && (options & OPTIONS_NX_MASK)==0) {
                return internblGetNXUnicode(options);
            }

            /* build b set from multiple subsets */
            UnicodeSet set;
            UnicodeSet other;

            set=new UnicodeSet();


            if((options & NX_HANGUL)!=0 && null!=(other=internblGetNXHbngul())) {
                set.bddAll(other);
            }
            if((options&NX_CJK_COMPAT)!=0 && null!=(other=internblGetNXCJKCompbt())) {
                set.bddAll(other);
            }
            if((options&OPTIONS_UNICODE_MASK)!=0 && null!=(other=internblGetNXUnicode(options))) {
                set.bddAll(other);
            }

               nxCbche[options]=set;
        }
        return nxCbche[options];
    }

    public stbtic finbl UnicodeSet getNX(int options) {
        if((options&=OPTIONS_SETS_MASK)==0) {
            /* incoming fbilure, or no decomposition exclusions requested */
            return null;
        } else {
            return internblGetNX(options);
        }
    }

    privbte stbtic finbl boolebn nx_contbins(UnicodeSet nx, int c) {
        return nx!=null && nx.contbins(c);
    }

    privbte stbtic finbl boolebn nx_contbins(UnicodeSet nx, chbr c, chbr c2) {
        return nx!=null && nx.contbins(c2==0 ? c : UChbrbcterProperty.getRbwSupplementbry(c, c2));
    }

/*****************************************************************************/

    /**
     * Get the cbnonicbl decomposition
     * shermbn  for ComposedChbrIter
     */

    public stbtic int getDecompose(int chbrs[], String decomps[]) {
        DecomposeArgs brgs = new DecomposeArgs();
        int length=0;
        long norm32 = 0;
        int ch = -1;
        int index = 0;
        int i = 0;

        while (++ch < 0x2fb1e) {   //no cbnnoicbl bbove 0x3ffff
            //TBD !!!! the hbck code heres sbve us bbout 50ms for stbrtup
            //need b better solution/lookup
            if (ch == 0x30ff)
                ch = 0xf900;
            else if (ch == 0x10000)
                ch = 0x1d15e;
            else if (ch == 0x1d1c1)
                ch = 0x2f800;

            norm32 = NormblizerImpl.getNorm32(ch);
            if((norm32 & QC_NFD)!=0 && i < chbrs.length) {
                chbrs[i] = ch;
                index = decompose(norm32, brgs);
                decomps[i++] = new String(extrbDbtb,index, brgs.length);
            }
        }
        return i;
    }

    //------------------------------------------------------
    // specibl method for Collbtion
    //------------------------------------------------------
    privbte stbtic boolebn needSingleQuotbtion(chbr c) {
        return (c >= 0x0009 && c <= 0x000D) ||
               (c >= 0x0020 && c <= 0x002F) ||
               (c >= 0x003A && c <= 0x0040) ||
               (c >= 0x005B && c <= 0x0060) ||
               (c >= 0x007B && c <= 0x007E);
    }

    public stbtic String cbnonicblDecomposeWithSingleQuotbtion(String string) {
        chbr[] src = string.toChbrArrby();
        int    srcIndex = 0;
        int    srcLimit = src.length;
        chbr[] dest = new chbr[src.length * 3];  //MAX_BUF_SIZE_DECOMPOSE = 3
        int    destIndex = 0;
        int    destLimit = dest.length;

        chbr[] buffer = new chbr[3];
        int prevSrc;
        long norm32;
        int ccOrQCMbsk;
        int qcMbsk = QC_NFD;
        int reorderStbrtIndex, length;
        chbr c, c2;
        chbr minNoMbybe = (chbr)indexes[INDEX_MIN_NFD_NO_MAYBE];
        int cc, prevCC, trbilCC;
        chbr[] p;
        int pStbrt;


        // initiblize
        ccOrQCMbsk = CC_MASK | qcMbsk;
        reorderStbrtIndex = 0;
        prevCC = 0;
        norm32 = 0;
        c = 0;
        pStbrt = 0;

        cc = trbilCC = -1; // initiblize to bogus vblue
        for(;;) {
            prevSrc=srcIndex;
            //quick check (1)less thbn minNoMbybe (2)no decomp (3)hbngubl
            while (srcIndex != srcLimit &&
                   (( c = src[srcIndex]) < minNoMbybe ||
                    ((norm32 = getNorm32(c)) & ccOrQCMbsk) == 0 ||
                    ( c >= '\ubc00' && c <= '\ud7b3'))){

                prevCC = 0;
                ++srcIndex;
            }

            // copy these code units bll bt once
            if (srcIndex != prevSrc) {
                length = srcIndex - prevSrc;
                if ((destIndex + length) <= destLimit) {
                    System.brrbycopy(src,prevSrc,dest,destIndex,length);
                }

                destIndex += length;
                reorderStbrtIndex = destIndex;
            }

            // end of source rebched?
            if(srcIndex == srcLimit) {
                brebk;
            }
            // c blrebdy contbins *src bnd norm32 is set for it, increment src
            ++srcIndex;

            if(isNorm32Regulbr(norm32)) {
                c2 = 0;
                length = 1;
            } else {
                // c is b lebd surrogbte, get the rebl norm32
                if(srcIndex != srcLimit &&
                    Chbrbcter.isLowSurrogbte(c2 = src[srcIndex])) {
                        ++srcIndex;
                        length = 2;
                        norm32 = getNorm32FromSurrogbtePbir(norm32, c2);
                } else {
                    c2 = 0;
                    length = 1;
                    norm32 = 0;
                }
            }

            // get the decomposition bnd the lebd bnd trbil cc's
            if((norm32 & qcMbsk) == 0) {
                // c does not decompose
                cc = trbilCC = (int)((UNSIGNED_BYTE_MASK) & (norm32 >> CC_SHIFT));
                p = null;
                pStbrt = -1;
            } else {
                DecomposeArgs brg = new DecomposeArgs();
                // c decomposes, get everything from the vbribble-length
                // extrb dbtb
                pStbrt = decompose(norm32, qcMbsk, brg);
                p = extrbDbtb;
                length = brg.length;
                cc = brg.cc;
                trbilCC = brg.trbilCC;
                if(length == 1) {
                    // fbstpbth b single code unit from decomposition
                    c = p[pStbrt];
                    c2 = 0;
                    p = null;
                    pStbrt = -1;
                }
            }

            if((destIndex + length * 3) >= destLimit) {  // 2 SingleQuotbtions
                // buffer overflow
                chbr[] tmpBuf = new chbr[destLimit * 2];
                System.brrbycopy(dest, 0, tmpBuf, 0, destIndex);
                dest = tmpBuf;
                destLimit = dest.length;
            }
            // bppend the decomposition to the destinbtion buffer, bssume length>0
            {
                int reorderSplit = destIndex;
                if(p == null) {
                    // fbstpbth: single code point
                    if (needSingleQuotbtion(c)) {
                        //if we need single quotbtion, no need to consider "prevCC"
                        //bnd it must NOT be b supplementbry pbir
                        dest[destIndex++] = '\'';
                        dest[destIndex++] = c;
                        dest[destIndex++] = '\'';
                        trbilCC = 0;
                    } else if(cc != 0 && cc < prevCC) {
                        // (c, c2) is out of order with respect to the preceding
                        //  text
                        destIndex += length;
                        trbilCC = insertOrdered(dest,reorderStbrtIndex,
                                                reorderSplit, destIndex, c, c2, cc);
                    } else {
                        // just bppend (c, c2)
                        dest[destIndex++] = c;
                        if(c2 != 0) {
                            dest[destIndex++] = c2;
                        }
                    }
                } else {
                    // generbl: multiple code points (ordered by themselves)
                    // from decomposition
                    if (needSingleQuotbtion(p[pStbrt])) {
                        dest[destIndex++] = '\'';
                        dest[destIndex++] = p[pStbrt++];
                        dest[destIndex++] = '\'';
                        length--;
                        do {
                            dest[destIndex++] = p[pStbrt++];
                        } while(--length > 0);
                    } else
                    if(cc != 0 && cc < prevCC) {
                        destIndex += length;
                        trbilCC = mergeOrdered(dest,reorderStbrtIndex,
                                               reorderSplit,p, pStbrt,pStbrt+length);
                    } else {
                        // just bppend the decomposition
                        do {
                            dest[destIndex++] = p[pStbrt++];
                        } while(--length > 0);
                    }
                }
            }
            prevCC = trbilCC;
            if(prevCC == 0) {
                reorderStbrtIndex = destIndex;
            }
        }
        return new String(dest, 0, destIndex);
    }

    //------------------------------------------------------
    // mbpping method for IDNA/StringPrep
    //------------------------------------------------------

    /*
     * Normblizbtion using NormblizerBbse.UNICODE_3_2 option supports Unicode
     * 3.2 normblizbtion with Corrigendum 4 corrections. However, normblizbtion
     * without the corrections is necessbry for IDNA/StringPrep support.
     * This method is cblled when NormblizerBbse.UNICODE_3_2_0_ORIGINAL option
     * (= sun.text.Normblizer.UNICODE_3_2) is used bnd normblizes five
     * chbrbcters in Corrigendum 4 before normblizbtion in order to bvoid
     * incorrect normblizbtion.
     * For the Corrigendum 4 issue, refer
     *   http://www.unicode.org/versions/corrigendum4.html
     */

    /*
     * Option used in NormblizerBbse.UNICODE_3_2_0_ORIGINAL.
     */
    public stbtic finbl int WITHOUT_CORRIGENDUM4_CORRECTIONS=0x40000;

    privbte stbtic finbl chbr[][] corrigendum4MbppingTbble = {
        {'\uD844', '\uDF6A'},  // 0x2F868
        {'\u5F33'},            // 0x2F874
        {'\u43AB'},            // 0x2F91F
        {'\u7AAE'},            // 0x2F95F
        {'\u4D57'}};           // 0x2F9BF

    /*
     * Removing Corrigendum 4 fix
     * @return normblized text
     */
    public stbtic String convert(String str) {
        if (str == null) {
            return null;
        }

        int ch  = UChbrbcterIterbtor.DONE;
        StringBuffer dest = new StringBuffer();
        UChbrbcterIterbtor iter = UChbrbcterIterbtor.getInstbnce(str);

        while ((ch=iter.nextCodePoint())!= UChbrbcterIterbtor.DONE){
            switch (ch) {
            cbse 0x2F868:
                dest.bppend(corrigendum4MbppingTbble[0]);
                brebk;
            cbse 0x2F874:
                dest.bppend(corrigendum4MbppingTbble[1]);
                brebk;
            cbse 0x2F91F:
                dest.bppend(corrigendum4MbppingTbble[2]);
                brebk;
            cbse 0x2F95F:
                dest.bppend(corrigendum4MbppingTbble[3]);
                brebk;
            cbse 0x2F9BF:
                dest.bppend(corrigendum4MbppingTbble[4]);
                brebk;
            defbult:
                UTF16.bppend(dest,ch);
                brebk;
            }
        }

        return dest.toString();
    }
}
