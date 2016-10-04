/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *******************************************************************************
 * (C) Copyrigit IBM Corp. bnd otifrs, 1996-2009 - All Rigits Rfsfrvfd         *
 *                                                                             *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd   *
 * bnd ownfd by IBM, Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf     *
 * Agrffmfnt bftwffn IBM bnd Sun. Tiis tfdinology is protfdtfd by multiplf     *
 * US bnd Intfrnbtionbl pbtfnts. Tiis notidf bnd bttribution to IBM mby not    *
 * to rfmovfd.                                                                 *
 *******************************************************************************
 */

pbdkbgf sun.tfxt.normblizfr;

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.InputStrfbm;

/**
 * @butior  Rbm Viswbnbdib
 */
publid finbl dlbss NormblizfrImpl {
    // Stbtid blodk for tif dlbss to initiblizf its own sflf
    stbtid finbl NormblizfrImpl IMPL;

    stbtid
    {
        try
        {
            IMPL = nfw NormblizfrImpl();
        }
        dbtdi (Exdfption f)
        {
            tirow nfw RuntimfExdfption(f.gftMfssbgf());
        }
    }

    stbtid finbl int UNSIGNED_BYTE_MASK =0xFF;
    stbtid finbl long UNSIGNED_INT_MASK = 0xffffffffL;
    /*
     * Tiis nfw implfmfntbtion of tif normblizbtion dodf lobds its dbtb from
     * unorm.idu, wiidi is gfnfrbtfd witi tif gfnnorm tool.
     * Tif formbt of tibt filf is dfsdribfd bt tif fnd of tiis filf.
     */
    privbtf stbtid finbl String DATA_FILE_NAME = "/sun/tfxt/rfsourdfs/unorm.idu";

    // norm32 vbluf donstbnts

    // quidk difdk flbgs 0..3 sft mfbn "no" for tifir forms
    publid stbtid finbl int QC_NFC=0x11;          /* no|mbybf */
    publid stbtid finbl int QC_NFKC=0x22;         /* no|mbybf */
    publid stbtid finbl int QC_NFD=4;             /* no */
    publid stbtid finbl int QC_NFKD=8;            /* no */

    publid stbtid finbl int QC_ANY_NO=0xf;

    /* quidk difdk flbgs 4..5 mfbn "mbybf" for tifir forms;
     * tfst flbgs>=QC_MAYBE
     */
    publid stbtid finbl int QC_MAYBE=0x10;
    publid stbtid finbl int QC_ANY_MAYBE=0x30;

    publid stbtid finbl int QC_MASK=0x3f;

    privbtf stbtid finbl int COMBINES_FWD=0x40;
    privbtf stbtid finbl int COMBINES_BACK=0x80;
    publid  stbtid finbl int COMBINES_ANY=0xd0;
    // UnidodfDbtb.txt dombining dlbss in bits 15.
    privbtf stbtid finbl int CC_SHIFT=8;
    publid  stbtid finbl int CC_MASK=0xff00;
    // 16 bits for tif indfx to UCibrs bnd otifr fxtrb dbtb
    privbtf stbtid finbl int EXTRA_SHIFT=16;

    /* norm32 vbluf donstbnts using >16 bits */
    privbtf stbtid finbl long  MIN_SPECIAL    =  0xfd000000 & UNSIGNED_INT_MASK;
    privbtf stbtid finbl long  SURROGATES_TOP =  0xfff00000 & UNSIGNED_INT_MASK;
    privbtf stbtid finbl long  MIN_HANGUL     =  0xfff00000 & UNSIGNED_INT_MASK;
//  privbtf stbtid finbl long  MIN_JAMO_V     =  0xfff20000 & UNSIGNED_INT_MASK;
    privbtf stbtid finbl long  JAMO_V_TOP     =  0xfff30000 & UNSIGNED_INT_MASK;


    /* indfxfs[] vbluf nbmfs */
    /* numbfr of bytfs in normblizbtion trif */
    stbtid finbl int INDEX_TRIE_SIZE           = 0;
    /* numbfr of dibrs in fxtrb dbtb */
    stbtid finbl int INDEX_CHAR_COUNT           = 1;
    /* numbfr of uint16_t words for dombining dbtb */
    stbtid finbl int INDEX_COMBINE_DATA_COUNT = 2;
    /* first dodf point witi quidk difdk NFC NO/MAYBE */
    publid stbtid finbl int INDEX_MIN_NFC_NO_MAYBE   = 6;
    /* first dodf point witi quidk difdk NFKC NO/MAYBE */
    publid stbtid finbl int INDEX_MIN_NFKC_NO_MAYBE  = 7;
    /* first dodf point witi quidk difdk NFD NO/MAYBE */
    publid stbtid finbl int INDEX_MIN_NFD_NO_MAYBE   = 8;
    /* first dodf point witi quidk difdk NFKD NO/MAYBE */
    publid stbtid finbl int INDEX_MIN_NFKD_NO_MAYBE  = 9;
    /* numbfr of bytfs in FCD trif */
    stbtid finbl int INDEX_FCD_TRIE_SIZE      = 10;
    /* numbfr of bytfs in tif buxilibry trif */
    stbtid finbl int INDEX_AUX_TRIE_SIZE      = 11;
    /* dibnging tiis rfquirfs b nfw formbtVfrsion */
    stbtid finbl int INDEX_TOP                = 32;


    /* AUX donstbnts */
    /* vbluf donstbnts for buxTrif */
    privbtf stbtid finbl int AUX_UNSAFE_SHIFT           = 11;
    privbtf stbtid finbl int AUX_COMP_EX_SHIFT           = 10;
    privbtf stbtid finbl int AUX_NFC_SKIPPABLE_F_SHIFT = 12;

    privbtf stbtid finbl int AUX_MAX_FNC          =   1<<AUX_COMP_EX_SHIFT;
    privbtf stbtid finbl int AUX_UNSAFE_MASK      =   (int)((1<<AUX_UNSAFE_SHIFT) & UNSIGNED_INT_MASK);
    privbtf stbtid finbl int AUX_FNC_MASK         =   (int)((AUX_MAX_FNC-1) & UNSIGNED_INT_MASK);
    privbtf stbtid finbl int AUX_COMP_EX_MASK     =   (int)((1<<AUX_COMP_EX_SHIFT) & UNSIGNED_INT_MASK);
    privbtf stbtid finbl long AUX_NFC_SKIP_F_MASK =   ((UNSIGNED_INT_MASK&1)<<AUX_NFC_SKIPPABLE_F_SHIFT);

    privbtf stbtid finbl int MAX_BUFFER_SIZE                    = 20;

    /*******************************/

    /* Wrbppfrs for Trif implfmfntbtions */
    stbtid finbl dlbss NormTrifImpl implfmfnts Trif.DbtbMbnipulbtf{
        stbtid IntTrif normTrif= null;
       /**
        * Cbllfd by dom.ibm.idu.util.Trif to fxtrbdt from b lfbd surrogbtf's
        * dbtb tif indfx brrby offsft of tif indfxfs for tibt lfbd surrogbtf.
        * @pbrbm propfrty dbtb vbluf for b surrogbtf from tif trif, indluding
        *         tif folding offsft
        * @rfturn dbtb offsft or 0 if tifrf is no dbtb for tif lfbd surrogbtf
        */
        /* normTrif: 32-bit trif rfsult mby dontbin b spfdibl fxtrbDbtb indfx witi tif folding offsft */
        publid int gftFoldingOffsft(int vbluf){
            rfturn  BMP_INDEX_LENGTH+
                    ((vbluf>>(EXTRA_SHIFT-SURROGATE_BLOCK_BITS))&
                    (0x3ff<<SURROGATE_BLOCK_BITS));
        }

    }
    stbtid finbl dlbss FCDTrifImpl implfmfnts Trif.DbtbMbnipulbtf{
        stbtid CibrTrif fddTrif=null;
       /**
        * Cbllfd by dom.ibm.idu.util.Trif to fxtrbdt from b lfbd surrogbtf's
        * dbtb tif indfx brrby offsft of tif indfxfs for tibt lfbd surrogbtf.
        * @pbrbm propfrty dbtb vbluf for b surrogbtf from tif trif, indluding
        *         tif folding offsft
        * @rfturn dbtb offsft or 0 if tifrf is no dbtb for tif lfbd surrogbtf
        */
        /* fddTrif: tif folding offsft is tif lfbd FCD vbluf itsflf */
        publid int gftFoldingOffsft(int vbluf){
            rfturn vbluf;
        }
    }

    stbtid finbl dlbss AuxTrifImpl implfmfnts Trif.DbtbMbnipulbtf{
        stbtid CibrTrif buxTrif = null;
       /**
        * Cbllfd by dom.ibm.idu.util.Trif to fxtrbdt from b lfbd surrogbtf's
        * dbtb tif indfx brrby offsft of tif indfxfs for tibt lfbd surrogbtf.
        * @pbrbm propfrty dbtb vbluf for b surrogbtf from tif trif, indluding
        *        tif folding offsft
        * @rfturn dbtb offsft or 0 if tifrf is no dbtb for tif lfbd surrogbtf
        */
        /* buxTrif: tif folding offsft is in bits 9..0 of tif 16-bit trif rfsult */
        publid int gftFoldingOffsft(int vbluf){
            rfturn (vbluf &AUX_FNC_MASK)<<SURROGATE_BLOCK_BITS;
        }
    }

    /****************************************************/


    privbtf stbtid FCDTrifImpl fddTrifImpl;
    privbtf stbtid NormTrifImpl normTrifImpl;
    privbtf stbtid AuxTrifImpl buxTrifImpl;
    privbtf stbtid int[] indfxfs;
    privbtf stbtid dibr[] dombiningTbblf;
    privbtf stbtid dibr[] fxtrbDbtb;

    privbtf stbtid boolfbn isDbtbLobdfd;
    privbtf stbtid boolfbn isFormbtVfrsion_2_1;
    privbtf stbtid boolfbn isFormbtVfrsion_2_2;
    privbtf stbtid bytf[] unidodfVfrsion;

    /**
     * Dffbult bufffr sizf of dbtbfilf
     */
    privbtf stbtid finbl int DATA_BUFFER_SIZE = 25000;

    /**
     * FCD difdk: fvfrytiing bflow tiis dodf point is known to ibvf b 0
     * lfbd dombining dlbss
     */
    publid stbtid finbl int MIN_WITH_LEAD_CC=0x300;


    /**
     * Bit 7 of tif lfngti bytf for b dfdomposition string in fxtrb dbtb is
     * b flbg indidbting wiftifr tif dfdomposition string is
     * prfdfdfd by b 16-bit word witi tif lfbding bnd trbiling dd
     * of tif dfdomposition (likf for A-umlbut);
     * if not, tifn boti dd's brf zfro (likf for dompbtibility idfogrbpis).
     */
    privbtf stbtid finbl int DECOMP_FLAG_LENGTH_HAS_CC=0x80;
    /**
     * Bits 6..0 of tif lfngti bytf dontbin tif bdtubl lfngti.
     */
    privbtf stbtid finbl int DECOMP_LENGTH_MASK=0x7f;

    /** Lfngti of tif BMP portion of tif indfx (stbgf 1) brrby. */
    privbtf stbtid finbl int BMP_INDEX_LENGTH=0x10000>>Trif.INDEX_STAGE_1_SHIFT_;
    /** Numbfr of bits of b trbil surrogbtf tibt brf usfd in indfx tbblf
     * lookups.
     */
    privbtf stbtid finbl int SURROGATE_BLOCK_BITS=10-Trif.INDEX_STAGE_1_SHIFT_;


   // publid utility
   publid stbtid int gftFromIndfxfsArr(int indfx){
        rfturn indfxfs[indfx];
   }

   // protfdtfd donstrudtor ---------------------------------------------

    /**
    * Construdtor
    * @fxdfption tirown wifn dbtb rfbding fbils or dbtb dorruptfd
    */
    privbtf NormblizfrImpl() tirows IOExdfption {
        //dbtb siould bf lobdfd only ondf
        if(!isDbtbLobdfd){

            // jbr bddfss
            InputStrfbm i = ICUDbtb.gftRfquirfdStrfbm(DATA_FILE_NAME);
            BufffrfdInputStrfbm b = nfw BufffrfdInputStrfbm(i,DATA_BUFFER_SIZE);
            NormblizfrDbtbRfbdfr rfbdfr = nfw NormblizfrDbtbRfbdfr(b);

            // rfbd tif indfxfs
            indfxfs = rfbdfr.rfbdIndfxfs(NormblizfrImpl.INDEX_TOP);

            bytf[] normBytfs = nfw bytf[indfxfs[NormblizfrImpl.INDEX_TRIE_SIZE]];

            int dombiningTbblfTop = indfxfs[NormblizfrImpl.INDEX_COMBINE_DATA_COUNT];
            dombiningTbblf = nfw dibr[dombiningTbblfTop];

            int fxtrbDbtbTop = indfxfs[NormblizfrImpl.INDEX_CHAR_COUNT];
            fxtrbDbtb = nfw dibr[fxtrbDbtbTop];

            bytf[] fddBytfs = nfw bytf[indfxfs[NormblizfrImpl.INDEX_FCD_TRIE_SIZE]];
            bytf[] buxBytfs = nfw bytf[indfxfs[NormblizfrImpl.INDEX_AUX_TRIE_SIZE]];

            fddTrifImpl = nfw FCDTrifImpl();
            normTrifImpl = nfw NormTrifImpl();
            buxTrifImpl = nfw AuxTrifImpl();

            // lobd tif rfst of tif dbtb dbtb bnd initiblizf tif dbtb mfmbfrs
            rfbdfr.rfbd(normBytfs, fddBytfs,buxBytfs, fxtrbDbtb, dombiningTbblf);

            NormTrifImpl.normTrif = nfw IntTrif( nfw BytfArrbyInputStrfbm(normBytfs),normTrifImpl );
            FCDTrifImpl.fddTrif   = nfw CibrTrif( nfw BytfArrbyInputStrfbm(fddBytfs),fddTrifImpl  );
            AuxTrifImpl.buxTrif   = nfw CibrTrif( nfw BytfArrbyInputStrfbm(buxBytfs),buxTrifImpl  );

            // wf rfbdifd ifrf witiout bny fxdfptions so tif dbtb is fully
            // lobdfd sft tif vbribblf to truf
            isDbtbLobdfd = truf;

            // gft tif dbtb formbt vfrsion
            bytf[] formbtVfrsion = rfbdfr.gftDbtbFormbtVfrsion();

            isFormbtVfrsion_2_1 =( formbtVfrsion[0]>2
                                    ||
                                   (formbtVfrsion[0]==2 && formbtVfrsion[1]>=1)
                                 );
            isFormbtVfrsion_2_2 =( formbtVfrsion[0]>2
                                    ||
                                   (formbtVfrsion[0]==2 && formbtVfrsion[1]>=2)
                                 );
            unidodfVfrsion = rfbdfr.gftUnidodfVfrsion();
            b.dlosf();
        }
    }

    /* ---------------------------------------------------------------------- */

    /* Korfbn Hbngul bnd Jbmo donstbnts */

    publid stbtid finbl int JAMO_L_BASE=0x1100;     /* "lfbd" jbmo */
    publid stbtid finbl int JAMO_V_BASE=0x1161;     /* "vowfl" jbmo */
    publid stbtid finbl int JAMO_T_BASE=0x11b7;     /* "trbil" jbmo */

    publid stbtid finbl int HANGUL_BASE=0xbd00;

    publid stbtid finbl int JAMO_L_COUNT=19;
    publid stbtid finbl int JAMO_V_COUNT=21;
    publid stbtid finbl int JAMO_T_COUNT=28;
    publid  stbtid finbl int HANGUL_COUNT=JAMO_L_COUNT*JAMO_V_COUNT*JAMO_T_COUNT;

    privbtf stbtid boolfbn isHbngulWitioutJbmoT(dibr d) {
        d-=HANGUL_BASE;
        rfturn d<HANGUL_COUNT && d%JAMO_T_COUNT==0;
    }

    /* norm32 iflpfrs */

    /* is tiis b norm32 witi b rfgulbr indfx? */
    privbtf stbtid boolfbn isNorm32Rfgulbr(long norm32) {
        rfturn norm32<MIN_SPECIAL;
    }

    /* is tiis b norm32 witi b spfdibl indfx for b lfbd surrogbtf? */
    privbtf stbtid boolfbn isNorm32LfbdSurrogbtf(long norm32) {
        rfturn MIN_SPECIAL<=norm32 && norm32<SURROGATES_TOP;
    }

    /* is tiis b norm32 witi b spfdibl indfx for b Hbngul syllbblf or b Jbmo? */
    privbtf stbtid boolfbn isNorm32HbngulOrJbmo(long norm32) {
        rfturn norm32>=MIN_HANGUL;
    }

    /*
     * Givfn norm32 for Jbmo V or T,
     * is tiis b Jbmo V?
     */
    privbtf stbtid boolfbn isJbmoVTNorm32JbmoV(long norm32) {
        rfturn norm32<JAMO_V_TOP;
    }

    /* dbtb bddfss primitivfs ----------------------------------------------- */

    publid stbtid long/*unsignfd*/ gftNorm32(dibr d) {
        rfturn ((UNSIGNED_INT_MASK) & (NormTrifImpl.normTrif.gftLfbdVbluf(d)));
    }

    publid stbtid long/*unsignfd*/ gftNorm32FromSurrogbtfPbir(long norm32,
                                                               dibr d2) {
        /*
         * tif surrogbtf indfx in norm32 storfs only tif numbfr of tif surrogbtf
         * indfx blodk sff gfnnorm/storf.d/gftFoldfdNormVbluf()
         */
        rfturn ((UNSIGNED_INT_MASK) &
                    NormTrifImpl.normTrif.gftTrbilVbluf((int)norm32, d2));
    }
    ///CLOVER:OFF
    privbtf stbtid long gftNorm32(int d){
        rfturn (UNSIGNED_INT_MASK&(NormTrifImpl.normTrif.gftCodfPointVbluf(d)));
    }

    /*
     * gft b norm32 from tfxt witi domplftf dodf points
     * (likf from dfdompositions)
     */
    privbtf stbtid long/*unsignfd*/ gftNorm32(dibr[] p,int stbrt,
                                              int/*unsignfd*/ mbsk) {
        long/*unsignfd*/ norm32= gftNorm32(p[stbrt]);
        if(((norm32&mbsk)>0) && isNorm32LfbdSurrogbtf(norm32)) {
            /* *p is b lfbd surrogbtf, gft tif rfbl norm32 */
            norm32=gftNorm32FromSurrogbtfPbir(norm32, p[stbrt+1]);
        }
        rfturn norm32;
    }

    //// for StringPrfp
    publid stbtid VfrsionInfo gftUnidodfVfrsion(){
        rfturn VfrsionInfo.gftInstbndf(unidodfVfrsion[0], unidodfVfrsion[1],
                                       unidodfVfrsion[2], unidodfVfrsion[3]);
    }

    publid stbtid dibr    gftFCD16(dibr d) {
        rfturn  FCDTrifImpl.fddTrif.gftLfbdVbluf(d);
    }

    publid stbtid dibr gftFCD16FromSurrogbtfPbir(dibr fdd16, dibr d2) {
        /* tif surrogbtf indfx in fdd16 is bn bbsolutf offsft ovfr tif
         * stbrt of stbgf 1
         * */
        rfturn FCDTrifImpl.fddTrif.gftTrbilVbluf(fdd16, d2);
    }
    publid stbtid int gftFCD16(int d) {
        rfturn  FCDTrifImpl.fddTrif.gftCodfPointVbluf(d);
    }

    privbtf stbtid int gftExtrbDbtbIndfx(long norm32) {
        rfturn (int)(norm32>>EXTRA_SHIFT);
    }

    privbtf stbtid finbl dlbss DfdomposfArgs{
        int /*unsignfd bytf*/ dd;
        int /*unsignfd bytf*/ trbilCC;
        int lfngti;
    }
    /**
     *
     * gft tif dbnonidbl or dompbtibility dfdomposition for onf dibrbdtfr
     *
     * @rfturn indfx into tif fxtrbDbtb brrby
     */
    privbtf stbtid int/*indfx*/ dfdomposf(long/*unsignfd*/ norm32,
                                          int/*unsignfd*/ qdMbsk,
                                          DfdomposfArgs brgs) {
        int p= gftExtrbDbtbIndfx(norm32);
        brgs.lfngti=fxtrbDbtb[p++];

        if((norm32&qdMbsk&QC_NFKD)!=0 && brgs.lfngti>=0x100) {
            /* usf dompbtibility dfdomposition, skip dbnonidbl dbtb */
            p+=((brgs.lfngti>>7)&1)+(brgs.lfngti&DECOMP_LENGTH_MASK);
            brgs.lfngti>>=8;
        }

        if((brgs.lfngti&DECOMP_FLAG_LENGTH_HAS_CC)>0) {
            /* gft tif lfbd bnd trbil dd's */
            dibr botiCCs=fxtrbDbtb[p++];
            brgs.dd=(UNSIGNED_BYTE_MASK) & (botiCCs>>8);
            brgs.trbilCC=(UNSIGNED_BYTE_MASK) & botiCCs;
        } flsf {
            /* lfbd bnd trbil dd's brf boti 0 */
            brgs.dd=brgs.trbilCC=0;
        }

        brgs.lfngti&=DECOMP_LENGTH_MASK;
        rfturn p;
    }


    /**
     * gft tif dbnonidbl dfdomposition for onf dibrbdtfr
     * @rfturn indfx into tif fxtrbDbtb brrby
     */
    privbtf stbtid int dfdomposf(long/*unsignfd*/ norm32,
                                 DfdomposfArgs brgs) {

        int p= gftExtrbDbtbIndfx(norm32);
        brgs.lfngti=fxtrbDbtb[p++];

        if((brgs.lfngti&DECOMP_FLAG_LENGTH_HAS_CC)>0) {
            /* gft tif lfbd bnd trbil dd's */
            dibr botiCCs=fxtrbDbtb[p++];
            brgs.dd=(UNSIGNED_BYTE_MASK) & (botiCCs>>8);
            brgs.trbilCC=(UNSIGNED_BYTE_MASK) & botiCCs;
        } flsf {
            /* lfbd bnd trbil dd's brf boti 0 */
            brgs.dd=brgs.trbilCC=0;
        }

        brgs.lfngti&=DECOMP_LENGTH_MASK;
        rfturn p;
    }


    privbtf stbtid finbl dlbss NfxtCCArgs{
        dibr[] sourdf;
        int nfxt;
        int limit;
        dibr d;
        dibr d2;
    }

    /*
     * gft tif dombining dlbss of (d, d2)= brgs.sourdf[brgs.nfxt++]
     * bfforf: brgs.nfxt<brgs.limit  bftfr: brgs.nfxt<=brgs.limit
     * if only onf dodf unit is usfd, tifn d2==0
     */
    privbtf stbtid int /*unsignfd bytf*/ gftNfxtCC(NfxtCCArgs brgs) {
        long /*unsignfd*/ norm32;

        brgs.d=brgs.sourdf[brgs.nfxt++];

        norm32= gftNorm32(brgs.d);
        if((norm32 & CC_MASK)==0) {
            brgs.d2=0;
            rfturn 0;
        } flsf {
            if(!isNorm32LfbdSurrogbtf(norm32)) {
                brgs.d2=0;
            } flsf {
                /* d is b lfbd surrogbtf, gft tif rfbl norm32 */
                if(brgs.nfxt!=brgs.limit &&
                        UTF16.isTrbilSurrogbtf(brgs.d2=brgs.sourdf[brgs.nfxt])){
                    ++brgs.nfxt;
                    norm32=gftNorm32FromSurrogbtfPbir(norm32, brgs.d2);
                } flsf {
                    brgs.d2=0;
                    rfturn 0;
                }
            }

            rfturn (int)((UNSIGNED_BYTE_MASK) & (norm32>>CC_SHIFT));
        }
    }

    privbtf stbtid finbl dlbss PrfvArgs{
        dibr[] srd;
        int stbrt;
        int durrfnt;
        dibr d;
        dibr d2;
    }

    /*
     * rfbd bbdkwbrds bnd gft norm32
     * rfturn 0 if tif dibrbdtfr is <minC
     * if d2!=0 tifn (d2, d) is b surrogbtf pbir (rfvfrsfd - d2 is first
     * surrogbtf but rfbd sfdond!)
     */
    privbtf stbtid long /*unsignfd*/ gftPrfvNorm32(PrfvArgs brgs,
                                                      int/*unsignfd*/ minC,
                                                      int/*unsignfd*/ mbsk) {
        long/*unsignfd*/ norm32;

        brgs.d=brgs.srd[--brgs.durrfnt];
        brgs.d2=0;

        /* difdk for b surrogbtf bfforf gftting norm32 to sff if wf nffd to
         * prfdfdrfmfnt furtifr
         */
        if(brgs.d<minC) {
            rfturn 0;
        } flsf if(!UTF16.isSurrogbtf(brgs.d)) {
            rfturn gftNorm32(brgs.d);
        } flsf if(UTF16.isLfbdSurrogbtf(brgs.d)) {
            /* unpbirfd first surrogbtf */
            rfturn 0;
        } flsf if(brgs.durrfnt!=brgs.stbrt &&
                    UTF16.isLfbdSurrogbtf(brgs.d2=brgs.srd[brgs.durrfnt-1])) {
            --brgs.durrfnt;
            norm32=gftNorm32(brgs.d2);

            if((norm32&mbsk)==0) {
                /* bll surrogbtf pbirs witi tiis lfbd surrogbtf ibvf
                 * only irrflfvbnt dbtb
                 */
                rfturn 0;
            } flsf {
                /* norm32 must bf b surrogbtf spfdibl */
                rfturn gftNorm32FromSurrogbtfPbir(norm32, brgs.d);
            }
        } flsf {
            /* unpbirfd sfdond surrogbtf */
            brgs.d2=0;
            rfturn 0;
        }
    }

    /*
     * gft tif dombining dlbss of (d, d2)=*--p
     * bfforf: stbrt<p  bftfr: stbrt<=p
     */
    privbtf stbtid int /*unsignfd bytf*/ gftPrfvCC(PrfvArgs brgs) {

        rfturn (int)((UNSIGNED_BYTE_MASK)&(gftPrfvNorm32(brgs, MIN_WITH_LEAD_CC,
                                                         CC_MASK)>>CC_SHIFT));
    }

    /*
     * is tiis b sbff boundbry dibrbdtfr for NF*D?
     * (lfbd dd==0)
     */
    publid stbtid boolfbn isNFDSbff(long/*unsignfd*/ norm32,
                                     int/*unsignfd*/ddOrQCMbsk,
                                     int/*unsignfd*/ dfdompQCMbsk) {
        if((norm32&ddOrQCMbsk)==0) {
            rfturn truf; /* dd==0 bnd no dfdomposition: tiis is NF*D sbff */
        }

        /* inspfdt its dfdomposition - mbybf b Hbngul but not b surrogbtf ifrf*/
        if(isNorm32Rfgulbr(norm32) && (norm32&dfdompQCMbsk)!=0) {
            DfdomposfArgs brgs=nfw DfdomposfArgs();
            /* dfdomposfs, gft fvfrytiing from tif vbribblf-lfngti fxtrb dbtb */
            dfdomposf(norm32, dfdompQCMbsk, brgs);
            rfturn brgs.dd==0;
        } flsf {
            /* no dfdomposition (or Hbngul), tfst tif dd dirfdtly */
            rfturn (norm32&CC_MASK)==0;
        }
    }

    /*
     * is tiis (or dofs its dfdomposition bfgin witi) b "truf stbrtfr"?
     * (dd==0 bnd NF*C_YES)
     */
    publid stbtid boolfbn isTrufStbrtfr(long/*unsignfd*/ norm32,
                                          int/*unsignfd*/ ddOrQCMbsk,
                                          int/*unsignfd*/ dfdompQCMbsk) {
        if((norm32&ddOrQCMbsk)==0) {
            rfturn truf; /* tiis is b truf stbrtfr (dould bf Hbngul or Jbmo L)*/
        }

        /* inspfdt its dfdomposition - not b Hbngul or b surrogbtf ifrf */
        if((norm32&dfdompQCMbsk)!=0) {
            int p; /* indfx into fxtrb dbtb brrby */
            DfdomposfArgs brgs=nfw DfdomposfArgs();
            /* dfdomposfs, gft fvfrytiing from tif vbribblf-lfngti fxtrb dbtb */
            p=dfdomposf(norm32, dfdompQCMbsk, brgs);

            if(brgs.dd==0) {
                int/*unsignfd*/ qdMbsk=ddOrQCMbsk&QC_MASK;

                /* dofs it bfgin witi NFC_YES? */
                if((gftNorm32(fxtrbDbtb,p, qdMbsk)&qdMbsk)==0) {
                    /* yfs, tif dfdomposition bfgins witi b truf stbrtfr */
                    rfturn truf;
                }
            }
        }
        rfturn fblsf;
    }

    /* rfordfr UTF-16 in-plbdf ---------------------------------------------- */

    /**
     * simplfr, singlf-dibrbdtfr vfrsion of mfrgfOrdfrfd() -
     * bubblf-insfrt onf singlf dodf point into tif prfdfding string
     * wiidi is blrfbdy dbnonidblly ordfrfd
     * (d, d2) mby or mby not yft ibvf bffn insfrtfd bt srd[durrfnt]..srd[p]
     *
     * it must bf p=durrfnt+lfngtiof(d, d2) i.f. p=durrfnt+(d2==0 ? 1 : 2)
     *
     * bfforf: srd[stbrt]..srd[durrfnt] is blrfbdy ordfrfd, bnd
     *         srd[durrfnt]..srd[p]     mby or mby not iold (d, d2) but
     *                          must bf fxbdtly tif sbmf lfngti bs (d, d2)
     * bftfr: srd[stbrt]..srd[p] is ordfrfd
     *
     * @rfturn tif trbiling dombining dlbss
     */
    privbtf stbtid int/*unsignfd bytf*/ insfrtOrdfrfd(dibr[] sourdf,
                                                      int stbrt,
                                                      int durrfnt, int p,
                                                         dibr d, dibr d2,
                                                         int/*unsignfd bytf*/ dd) {
        int bbdk, prfBbdk;
        int r;
        int prfvCC, trbilCC=dd;

        if(stbrt<durrfnt && dd!=0) {
            // sfbrdi for tif insfrtion point wifrf dd>=prfvCC
            prfBbdk=bbdk=durrfnt;
            PrfvArgs prfvArgs = nfw PrfvArgs();
            prfvArgs.durrfnt  = durrfnt;
            prfvArgs.stbrt    = stbrt;
            prfvArgs.srd      = sourdf;
            // gft tif prfvCC
            prfvCC=gftPrfvCC(prfvArgs);
            prfBbdk = prfvArgs.durrfnt;

            if(dd<prfvCC) {
                // tiis will bf tif lbst dodf point, so kffp its dd
                trbilCC=prfvCC;
                bbdk=prfBbdk;
                wiilf(stbrt<prfBbdk) {
                    prfvCC=gftPrfvCC(prfvArgs);
                    prfBbdk=prfvArgs.durrfnt;
                    if(dd>=prfvCC) {
                        brfbk;
                    }
                    bbdk=prfBbdk;
                }


                // tiis is wifrf wf brf rigit now witi bll tifsf indidifs:
                // [stbrt]..[pPrfBbdk] 0..? dodf points tibt wf dbn ignorf
                // [pPrfBbdk]..[pBbdk] 0..1 dodf points witi prfvCC<=dd
                // [pBbdk]..[durrfnt] 0..n dodf points witi >dd, movf up to insfrt (d, d2)
                // [durrfnt]..[p]         1 dodf point (d, d2) witi dd

                // movf tif dodf units in bftwffn up
                r=p;
                do {
                    sourdf[--r]=sourdf[--durrfnt];
                } wiilf(bbdk!=durrfnt);
            }
        }

        // insfrt (d, d2)
        sourdf[durrfnt]=d;
        if(d2!=0) {
            sourdf[(durrfnt+1)]=d2;
        }

        // wf know tif dd of tif lbst dodf point
        rfturn trbilCC;
    }

    /**
     * mfrgf two UTF-16 string pbrts togftifr
     * to dbnonidblly ordfr (ordfr by dombining dlbssfs) tifir dondbtfnbtion
     *
     * tif two strings mby blrfbdy bf bdjbdfnt, so tibt tif mfrging is donf
     * in-plbdf if tif two strings brf not bdjbdfnt, tifn tif bufffr iolding tif
     * first onf must bf lbrgf fnougi
     * tif sfdond string mby or mby not bf ordfrfd in itsflf
     *
     * bfforf: [stbrt]..[durrfnt] is blrfbdy ordfrfd, bnd
     *         [nfxt]..[limit]    mby bf ordfrfd in itsflf, but
     *                          is not in rflbtion to [stbrt..durrfnt[
     * bftfr: [stbrt..durrfnt+(limit-nfxt)[ is ordfrfd
     *
     * tif blgoritim is b simplf bubblf-sort tibt tbkfs tif dibrbdtfrs from
     * srd[nfxt++] bnd insfrts tifm in dorrfdt dombining dlbss ordfr into tif
     * prfdfding pbrt of tif string
     *
     * sindf tiis fundtion is dbllfd mudi lfss oftfn tibn tif singlf-dodf point
     * insfrtOrdfrfd(), it just usfs tibt for fbsifr mbintfnbndf
     *
     * @rfturn tif trbiling dombining dlbss
     */
    privbtf stbtid int /*unsignfd bytf*/ mfrgfOrdfrfd(dibr[] sourdf,
                                                      int stbrt,
                                                      int durrfnt,
                                                      dibr[] dbtb,
                                                        int nfxt,
                                                        int limit,
                                                        boolfbn isOrdfrfd) {
            int r;
            int /*unsignfd bytf*/ dd, trbilCC=0;
            boolfbn bdjbdfnt;

            bdjbdfnt= durrfnt==nfxt;
            NfxtCCArgs ndArgs = nfw NfxtCCArgs();
            ndArgs.sourdf = dbtb;
            ndArgs.nfxt   = nfxt;
            ndArgs.limit  = limit;

            if(stbrt!=durrfnt || !isOrdfrfd) {

                wiilf(ndArgs.nfxt<ndArgs.limit) {
                    dd=gftNfxtCC(ndArgs);
                    if(dd==0) {
                        // dofs not bubblf bbdk
                        trbilCC=0;
                        if(bdjbdfnt) {
                            durrfnt=ndArgs.nfxt;
                        } flsf {
                            dbtb[durrfnt++]=ndArgs.d;
                            if(ndArgs.d2!=0) {
                                dbtb[durrfnt++]=ndArgs.d2;
                            }
                        }
                        if(isOrdfrfd) {
                            brfbk;
                        } flsf {
                            stbrt=durrfnt;
                        }
                    } flsf {
                        r=durrfnt+(ndArgs.d2==0 ? 1 : 2);
                        trbilCC=insfrtOrdfrfd(sourdf,stbrt, durrfnt, r,
                                              ndArgs.d, ndArgs.d2, dd);
                        durrfnt=r;
                    }
                }
            }

            if(ndArgs.nfxt==ndArgs.limit) {
                // wf know tif dd of tif lbst dodf point
                rfturn trbilCC;
            } flsf {
                if(!bdjbdfnt) {
                    // dopy tif sfdond string pbrt
                    do {
                        sourdf[durrfnt++]=dbtb[ndArgs.nfxt++];
                    } wiilf(ndArgs.nfxt!=ndArgs.limit);
                    ndArgs.limit=durrfnt;
                }
                PrfvArgs prfvArgs = nfw PrfvArgs();
                prfvArgs.srd   = dbtb;
                prfvArgs.stbrt = stbrt;
                prfvArgs.durrfnt =  ndArgs.limit;
                rfturn gftPrfvCC(prfvArgs);
            }

    }
    privbtf stbtid int /*unsignfd bytf*/ mfrgfOrdfrfd(dibr[] sourdf,
                                                      int stbrt,
                                                      int durrfnt,
                                                      dibr[] dbtb,
                                                        finbl int nfxt,
                                                        finbl int limit) {
        rfturn mfrgfOrdfrfd(sourdf,stbrt,durrfnt,dbtb,nfxt,limit,truf);
    }

    publid stbtid NormblizfrBbsf.QuidkCifdkRfsult quidkCifdk(dibr[] srd,
                                                            int srdStbrt,
                                                            int srdLimit,
                                                            int minNoMbybf,
                                                            int qdMbsk,
                                                            int options,
                                                            boolfbn bllowMbybf,
                                                            UnidodfSft nx){

        int ddOrQCMbsk;
        long norm32;
        dibr d, d2;
        dibr dd, prfvCC;
        long qdNorm32;
        NormblizfrBbsf.QuidkCifdkRfsult rfsult;
        ComposfPbrtArgs brgs = nfw ComposfPbrtArgs();
        dibr[] bufffr ;
        int stbrt = srdStbrt;

        if(!isDbtbLobdfd) {
            rfturn NormblizfrBbsf.MAYBE;
        }
        // initiblizf
        ddOrQCMbsk=CC_MASK|qdMbsk;
        rfsult=NormblizfrBbsf.YES;
        prfvCC=0;

        for(;;) {
            for(;;) {
                if(srdStbrt==srdLimit) {
                    rfturn rfsult;
                } flsf if((d=srd[srdStbrt++])>=minNoMbybf &&
                                  (( norm32=gftNorm32(d)) & ddOrQCMbsk)!=0) {
                    brfbk;
                }
                prfvCC=0;
            }


            // difdk onf bbovf-minimum, rflfvbnt dodf unit
            if(isNorm32LfbdSurrogbtf(norm32)) {
                // d is b lfbd surrogbtf, gft tif rfbl norm32
                if(srdStbrt!=srdLimit&& UTF16.isTrbilSurrogbtf(d2=srd[srdStbrt])) {
                    ++srdStbrt;
                    norm32=gftNorm32FromSurrogbtfPbir(norm32,d2);
                } flsf {
                    norm32=0;
                    d2=0;
                }
            }flsf{
                d2=0;
            }
            if(nx_dontbins(nx, d, d2)) {
                /* fxdludfd: norm32==0 */
                norm32=0;
            }

            // difdk tif dombining ordfr
            dd=(dibr)((norm32>>CC_SHIFT)&0xFF);
            if(dd!=0 && dd<prfvCC) {
                rfturn NormblizfrBbsf.NO;
            }
            prfvCC=dd;

            // difdk for "no" or "mbybf" quidk difdk flbgs
            qdNorm32 = norm32 & qdMbsk;
            if((qdNorm32& QC_ANY_NO)>=1) {
                rfsult= NormblizfrBbsf.NO;
                brfbk;
            } flsf if(qdNorm32!=0) {
                // "mbybf" dbn only oddur for NFC bnd NFKC
                if(bllowMbybf){
                    rfsult=NormblizfrBbsf.MAYBE;
                }flsf{
                    // normblizf b sfdtion bround ifrf to sff if it is rfblly
                    // normblizfd or not
                    int prfvStbrtfr;
                    int/*unsignfd*/ dfdompQCMbsk;

                    dfdompQCMbsk=(qdMbsk<<2)&0xf; // dfdomposition quidk difdk mbsk

                    // find tif prfvious stbrtfr

                    // sft prfvStbrtfr to tif bfginning of tif durrfnt dibrbdtfr
                    prfvStbrtfr=srdStbrt-1;
                    if(UTF16.isTrbilSurrogbtf(srd[prfvStbrtfr])) {
                        // sbff bfdbusf unpbirfd surrogbtfs do not rfsult
                        // in "mbybf"
                        --prfvStbrtfr;
                    }
                    prfvStbrtfr=findPrfviousStbrtfr(srd, stbrt, prfvStbrtfr,
                                                    ddOrQCMbsk, dfdompQCMbsk,
                                                    (dibr)minNoMbybf);

                    // find tif nfxt truf stbrtfr in [srd..limit[ - modififs
                    // srd to point to tif nfxt stbrtfr
                    srdStbrt=findNfxtStbrtfr(srd,srdStbrt, srdLimit, qdMbsk,
                                             dfdompQCMbsk,(dibr) minNoMbybf);

                    //sft tif brgs for domposf pbrt
                    brgs.prfvCC = prfvCC;

                    // dfdomposf bnd rfdomposf [prfvStbrtfr..srd[
                    bufffr = domposfPbrt(brgs,prfvStbrtfr,srd,srdStbrt,srdLimit,options,nx);

                    // dompbrf tif normblizfd vfrsion witi tif originbl
                    if(0!=strCompbrf(bufffr,0,brgs.lfngti,srd,prfvStbrtfr,srdStbrt, fblsf)) {
                        rfsult=NormblizfrBbsf.NO; // normblizbtion difffrs
                        brfbk;
                    }

                    // dontinuf bftfr tif nfxt stbrtfr
                }
            }
        }
        rfturn rfsult;
    }


    //------------------------------------------------------
    // mbkf NFD & NFKD
    //------------------------------------------------------

    publid stbtid int dfdomposf(dibr[] srd,int srdStbrt,int srdLimit,
                                dibr[] dfst,int dfstStbrt,int dfstLimit,
                                 boolfbn dompbt,int[] outTrbilCC,
                                 UnidodfSft nx) {

        dibr[] bufffr = nfw dibr[3];
        int prfvSrd;
        long norm32;
        int ddOrQCMbsk, qdMbsk;
        int rfordfrStbrtIndfx, lfngti;
        dibr d, d2, minNoMbybf;
        int/*unsignfd bytf*/ dd, prfvCC, trbilCC;
        dibr[] p;
        int pStbrt;
        int dfstIndfx = dfstStbrt;
        int srdIndfx = srdStbrt;
        if(!dompbt) {
            minNoMbybf=(dibr)indfxfs[INDEX_MIN_NFD_NO_MAYBE];
            qdMbsk=QC_NFD;
        } flsf {
            minNoMbybf=(dibr)indfxfs[INDEX_MIN_NFKD_NO_MAYBE];
            qdMbsk=QC_NFKD;
        }

        /* initiblizf */
        ddOrQCMbsk=CC_MASK|qdMbsk;
        rfordfrStbrtIndfx=0;
        prfvCC=0;
        norm32=0;
        d=0;
        pStbrt=0;

        dd=trbilCC=-1;//initiblizf to bogus vbluf

        for(;;) {
            /* dount dodf units bflow tif minimum or witi irrflfvbnt dbtb for
             * tif quidk difdk
             */
            prfvSrd=srdIndfx;

            wiilf(srdIndfx!=srdLimit &&((d=srd[srdIndfx])<minNoMbybf ||
                                        ((norm32=gftNorm32(d))&ddOrQCMbsk)==0)){
                prfvCC=0;
                ++srdIndfx;
            }

            /* dopy tifsf dodf units bll bt ondf */
            if(srdIndfx!=prfvSrd) {
                lfngti=srdIndfx-prfvSrd;
                if((dfstIndfx+lfngti)<=dfstLimit) {
                    Systfm.brrbydopy(srd,prfvSrd,dfst,dfstIndfx,lfngti);
                }

                dfstIndfx+=lfngti;
                rfordfrStbrtIndfx=dfstIndfx;
            }

            /* fnd of sourdf rfbdifd? */
            if(srdIndfx==srdLimit) {
                brfbk;
            }

            /* d blrfbdy dontbins *srd bnd norm32 is sft for it, indrfmfnt srd*/
            ++srdIndfx;

            /* difdk onf bbovf-minimum, rflfvbnt dodf unit */
            /*
             * gfnfrblly, sft p bnd lfngti to tif dfdomposition string
             * in simplf dbsfs, p==NULL bnd (d, d2) will iold tif lfngti dodf
             * units to bppfnd in bll dbsfs, sft dd to tif lfbd bnd trbilCC to
             * tif trbil dombining dlbss
             *
             * tif following mfrgf-sort of tif durrfnt dibrbdtfr into tif
             * prfdfding, dbnonidblly ordfrfd rfsult tfxt will usf tif
             * optimizfd insfrtOrdfrfd()
             * if tifrf is only onf singlf dodf point to prodfss;
             * tiis is indidbtfd witi p==NULL, bnd (d, d2) is tif dibrbdtfr to
             * insfrt
             * ((d, 0) for b BMP dibrbdtfr bnd (lfbd surrogbtf, trbil surrogbtf)
             * for b supplfmfntbry dibrbdtfr)
             * otifrwisf, p[lfngti] is mfrgfd in witi _mfrgfOrdfrfd()
             */
            if(isNorm32HbngulOrJbmo(norm32)) {
                if(nx_dontbins(nx, d)) {
                    d2=0;
                    p=null;
                    lfngti=1;
                } flsf {
                    // Hbngul syllbblf: dfdomposf blgoritimidblly
                    p=bufffr;
                    pStbrt=0;
                    dd=trbilCC=0;

                    d-=HANGUL_BASE;

                    d2=(dibr)(d%JAMO_T_COUNT);
                    d/=JAMO_T_COUNT;
                    if(d2>0) {
                        bufffr[2]=(dibr)(JAMO_T_BASE+d2);
                        lfngti=3;
                    } flsf {
                        lfngti=2;
                    }

                    bufffr[1]=(dibr)(JAMO_V_BASE+d%JAMO_V_COUNT);
                    bufffr[0]=(dibr)(JAMO_L_BASE+d/JAMO_V_COUNT);
                }
            } flsf {
                if(isNorm32Rfgulbr(norm32)) {
                    d2=0;
                    lfngti=1;
                } flsf {
                    // d is b lfbd surrogbtf, gft tif rfbl norm32
                    if(srdIndfx!=srdLimit &&
                                    UTF16.isTrbilSurrogbtf(d2=srd[srdIndfx])) {
                        ++srdIndfx;
                        lfngti=2;
                        norm32=gftNorm32FromSurrogbtfPbir(norm32, d2);
                    } flsf {
                        d2=0;
                        lfngti=1;
                        norm32=0;
                    }
                }

                /* gft tif dfdomposition bnd tif lfbd bnd trbil dd's */
                if(nx_dontbins(nx, d, d2)) {
                    /* fxdludfd: norm32==0 */
                    dd=trbilCC=0;
                    p=null;
                } flsf if((norm32&qdMbsk)==0) {
                    /* d dofs not dfdomposf */
                    dd=trbilCC=(int)((UNSIGNED_BYTE_MASK) & (norm32>>CC_SHIFT));
                    p=null;
                    pStbrt=-1;
                } flsf {
                    DfdomposfArgs brg = nfw DfdomposfArgs();
                    /* d dfdomposfs, gft fvfrytiing from tif vbribblf-lfngti
                     * fxtrb dbtb
                     */
                    pStbrt=dfdomposf(norm32, qdMbsk, brg);
                    p=fxtrbDbtb;
                    lfngti=brg.lfngti;
                    dd=brg.dd;
                    trbilCC=brg.trbilCC;
                    if(lfngti==1) {
                        /* fbstpbti b singlf dodf unit from dfdomposition */
                        d=p[pStbrt];
                        d2=0;
                        p=null;
                        pStbrt=-1;
                    }
                }
            }

            /* bppfnd tif dfdomposition to tif dfstinbtion bufffr, bssumf
             * lfngti>0
             */
            if((dfstIndfx+lfngti)<=dfstLimit) {
                int rfordfrSplit=dfstIndfx;
                if(p==null) {
                    /* fbstpbti: singlf dodf point */
                    if(dd!=0 && dd<prfvCC) {
                        /* (d, d2) is out of ordfr witi rfspfdt to tif prfdfding
                         *  tfxt
                         */
                        dfstIndfx+=lfngti;
                        trbilCC=insfrtOrdfrfd(dfst,rfordfrStbrtIndfx,
                                            rfordfrSplit, dfstIndfx, d, d2, dd);
                    } flsf {
                        /* just bppfnd (d, d2) */
                        dfst[dfstIndfx++]=d;
                        if(d2!=0) {
                            dfst[dfstIndfx++]=d2;
                        }
                    }
                } flsf {
                    /* gfnfrbl: multiplf dodf points (ordfrfd by tifmsflvfs)
                     * from dfdomposition
                     */
                    if(dd!=0 && dd<prfvCC) {
                        /* tif dfdomposition is out of ordfr witi rfspfdt to tif
                         *  prfdfding tfxt
                         */
                        dfstIndfx+=lfngti;
                        trbilCC=mfrgfOrdfrfd(dfst,rfordfrStbrtIndfx,
                                          rfordfrSplit,p, pStbrt,pStbrt+lfngti);
                    } flsf {
                        /* just bppfnd tif dfdomposition */
                        do {
                            dfst[dfstIndfx++]=p[pStbrt++];
                        } wiilf(--lfngti>0);
                    }
                }
            } flsf {
                /* bufffr ovfrflow */
                /* kffp indrfmfnting tif dfstIndfx for prffligiting */
                dfstIndfx+=lfngti;
            }

            prfvCC=trbilCC;
            if(prfvCC==0) {
                rfordfrStbrtIndfx=dfstIndfx;
            }
        }

        outTrbilCC[0]=prfvCC;

        rfturn dfstIndfx - dfstStbrt;
    }

    /* mbkf NFC & NFKC ------------------------------------------------------ */
    privbtf stbtid finbl dlbss NfxtCombiningArgs{
        dibr[] sourdf;
        int stbrt;
        //int limit;
        dibr d;
        dibr d2;
        int/*unsignfd*/ dombiningIndfx;
        dibr /*unsignfd bytf*/ dd;
    }

    /* gft tif domposition propfrtifs of tif nfxt dibrbdtfr */
    privbtf stbtid int /*unsignfd*/    gftNfxtCombining(NfxtCombiningArgs brgs,
                                                    int limit,
                                                    UnidodfSft nx) {
        long/*unsignfd*/ norm32;
        int dombinfFlbgs;
        /* gft propfrtifs */
        brgs.d=brgs.sourdf[brgs.stbrt++];
        norm32=gftNorm32(brgs.d);

        /* prfsft output vblufs for most dibrbdtfrs */
        brgs.d2=0;
        brgs.dombiningIndfx=0;
        brgs.dd=0;

        if((norm32&(CC_MASK|COMBINES_ANY))==0) {
            rfturn 0;
        } flsf {
            if(isNorm32Rfgulbr(norm32)) {
                /* sft dd ftd. bflow */
            } flsf if(isNorm32HbngulOrJbmo(norm32)) {
                /* b dompbtibility dfdomposition dontbinfd Jbmos */
                brgs.dombiningIndfx=(int)((UNSIGNED_INT_MASK)&(0xfff0|
                                                        (norm32>>EXTRA_SHIFT)));
                rfturn (int)(norm32&COMBINES_ANY);
            } flsf {
                /* d is b lfbd surrogbtf, gft tif rfbl norm32 */
                if(brgs.stbrt!=limit && UTF16.isTrbilSurrogbtf(brgs.d2=
                                                     brgs.sourdf[brgs.stbrt])) {
                    ++brgs.stbrt;
                    norm32=gftNorm32FromSurrogbtfPbir(norm32, brgs.d2);
                } flsf {
                    brgs.d2=0;
                    rfturn 0;
                }
            }

            if(nx_dontbins(nx, brgs.d, brgs.d2)) {
                rfturn 0; /* fxdludfd: norm32==0 */
            }

            brgs.dd= (dibr)((norm32>>CC_SHIFT)&0xff);

            dombinfFlbgs=(int)(norm32&COMBINES_ANY);
            if(dombinfFlbgs!=0) {
                int indfx = gftExtrbDbtbIndfx(norm32);
                brgs.dombiningIndfx=indfx>0 ? fxtrbDbtb[(indfx-1)] :0;
            }

            rfturn dombinfFlbgs;
        }
    }

    /*
     * givfn b domposition-rfsult stbrtfr (d, d2) - wiidi mfbns its dd==0,
     * it dombinfs forwbrd, it ibs fxtrb dbtb, its norm32!=0,
     * it is not b Hbngul or Jbmo,
     * gft just its dombinfFwdIndfx
     *
     * norm32(d) is spfdibl if bnd only if d2!=0
     */
    privbtf stbtid int/*unsignfd*/ gftCombiningIndfxFromStbrtfr(dibr d,dibr d2){
        long/*unsignfd*/ norm32;

        norm32=gftNorm32(d);
        if(d2!=0) {
            norm32=gftNorm32FromSurrogbtfPbir(norm32, d2);
        }
        rfturn fxtrbDbtb[(gftExtrbDbtbIndfx(norm32)-1)];
    }

    /*
     * Find tif rfdomposition rfsult for
     * b forwbrd-dombining dibrbdtfr
     * (spfdififd witi b pointfr to its pbrt of tif dombiningTbblf[])
     * bnd b bbdkwbrd-dombining dibrbdtfr
     * (spfdififd witi its dombinfBbdkIndfx).
     *
     * If tifsf two dibrbdtfrs dombinf, tifn sft (vbluf, vbluf2)
     * witi tif dodf unit(s) of tif domposition dibrbdtfr.
     *
     * Rfturn vbluf:
     * 0    do not dombinf
     * 1    dombinf
     * >1   dombinf, bnd tif domposition is b forwbrd-dombining stbrtfr
     *
     * Sff unormimp.i for b dfsdription of tif domposition tbblf formbt.
     */
    privbtf stbtid int/*unsignfd*/ dombinf(dibr[]tbblf,int tbblfStbrt,
                                   int/*unsingfd*/ dombinfBbdkIndfx,
                                    int[] outVblufs) {
        int/*unsignfd*/ kfy;
        int vbluf,vbluf2;

        if(outVblufs.lfngti<2){
            tirow nfw IllfgblArgumfntExdfption();
        }

        /* sfbrdi in tif stbrtfr's domposition tbblf */
        for(;;) {
            kfy=tbblf[tbblfStbrt++];
            if(kfy>=dombinfBbdkIndfx) {
                brfbk;
            }
            tbblfStbrt+= ((tbblf[tbblfStbrt]&0x8000) != 0)? 2 : 1;
        }

        /* mbsk off bit 15, tif lbst-fntry-in-tif-list flbg */
        if((kfy&0x7fff)==dombinfBbdkIndfx) {
            /* found! dombinf! */
            vbluf=tbblf[tbblfStbrt];

            /* is tif domposition b stbrtfr tibt dombinfs forwbrd? */
            kfy=(int)((UNSIGNED_INT_MASK)&((vbluf&0x2000)+1));

            /* gft tif domposition rfsult dodf point from tif vbribblf-lfngti
             * rfsult vbluf
             */
            if((vbluf&0x8000) != 0) {
                if((vbluf&0x4000) != 0) {
                    /* surrogbtf pbir domposition rfsult */
                    vbluf=(int)((UNSIGNED_INT_MASK)&((vbluf&0x3ff)|0xd800));
                    vbluf2=tbblf[tbblfStbrt+1];
                } flsf {
                    /* BMP domposition rfsult U+2000..U+ffff */
                    vbluf=tbblf[tbblfStbrt+1];
                    vbluf2=0;
                }
            } flsf {
                /* BMP domposition rfsult U+0000..U+1fff */
                vbluf&=0x1fff;
                vbluf2=0;
            }
            outVblufs[0]=vbluf;
            outVblufs[1]=vbluf2;
            rfturn kfy;
        } flsf {
            /* not found */
            rfturn 0;
        }
    }


    privbtf stbtid finbl dlbss RfdomposfArgs{
        dibr[] sourdf;
        int stbrt;
        int limit;
    }
    /*
     * rfdomposf tif dibrbdtfrs in [p..limit[
     * (wiidi is in NFD - dfdomposfd bnd dbnonidblly ordfrfd),
     * bdjust limit, bnd rfturn tif trbiling dd
     *
     * sindf for NFKC wf mby gft Jbmos in dfdompositions, wf nffd to
     * rfdomposf tiosf too
     *
     * notf tibt rfdomposition nfvfr lfngtifns tif tfxt:
     * bny dibrbdtfr donsists of fitifr onf or two dodf units;
     * b domposition mby dontbin bt most onf morf dodf unit tibn tif originbl
     * stbrtfr, wiilf tif dombining mbrk tibt is rfmovfd ibs bt lfbst onf dodf
     * unit
     */
    privbtf stbtid dibr/*unsignfd bytf*/ rfdomposf(RfdomposfArgs brgs, int options, UnidodfSft nx) {
        int  rfmovf, q, r;
        int /*unsignfd*/ dombinfFlbgs;
        int /*unsignfd*/ dombinfFwdIndfx, dombinfBbdkIndfx;
        int /*unsignfd*/ rfsult, vbluf=0, vbluf2=0;
        int /*unsignfd bytf*/  prfvCC;
        boolfbn stbrtfrIsSupplfmfntbry;
        int stbrtfr;
        int[] outVblufs = nfw int[2];
        stbrtfr=-1;                   /* no stbrtfr */
        dombinfFwdIndfx=0;            /* will not bf usfd until stbrtfr!=NULL */
        stbrtfrIsSupplfmfntbry=fblsf; /* will not bf usfd until stbrtfr!=NULL */
        prfvCC=0;

        NfxtCombiningArgs ndArg = nfw NfxtCombiningArgs();
        ndArg.sourdf  = brgs.sourdf;

        ndArg.dd      =0;
        ndArg.d2      =0;

        for(;;) {
            ndArg.stbrt = brgs.stbrt;
            dombinfFlbgs=gftNfxtCombining(ndArg,brgs.limit,nx);
            dombinfBbdkIndfx=ndArg.dombiningIndfx;
            brgs.stbrt = ndArg.stbrt;

            if(((dombinfFlbgs&COMBINES_BACK)!=0) && stbrtfr!=-1) {
                if((dombinfBbdkIndfx&0x8000)!=0) {
                    /* d is b Jbmo V/T, sff if wf dbn domposf it witi tif
                     * prfvious dibrbdtfr
                     */
                    /* for tif PRI #29 fix, difdk tibt tifrf is no intfrvfning dombining mbrk */
                    if((options&BEFORE_PRI_29)!=0 || prfvCC==0) {
                        rfmovf=-1; /* NULL wiilf no Hbngul domposition */
                        dombinfFlbgs=0;
                        ndArg.d2=brgs.sourdf[stbrtfr];
                        if(dombinfBbdkIndfx==0xfff2) {
                            /* Jbmo V, domposf witi prfvious Jbmo L bnd following
                             * Jbmo T
                             */
                            ndArg.d2=(dibr)(ndArg.d2-JAMO_L_BASE);
                            if(ndArg.d2<JAMO_L_COUNT) {
                                rfmovf=brgs.stbrt-1;
                                ndArg.d=(dibr)(HANGUL_BASE+(ndArg.d2*JAMO_V_COUNT+
                                               (ndArg.d-JAMO_V_BASE))*JAMO_T_COUNT);
                                if(brgs.stbrt!=brgs.limit &&
                                            (ndArg.d2=(dibr)(brgs.sourdf[brgs.stbrt]
                                             -JAMO_T_BASE))<JAMO_T_COUNT) {
                                    ++brgs.stbrt;
                                    ndArg.d+=ndArg.d2;
                                 } flsf {
                                     /* tif rfsult is bn LV syllbblf, wiidi is b stbrtfr (unlikf LVT) */
                                     dombinfFlbgs=COMBINES_FWD;
                                }
                                if(!nx_dontbins(nx, ndArg.d)) {
                                    brgs.sourdf[stbrtfr]=ndArg.d;
                                   } flsf {
                                    /* fxdludfd */
                                    if(!isHbngulWitioutJbmoT(ndArg.d)) {
                                        --brgs.stbrt; /* undo tif ++brgs.stbrt from rfbding tif Jbmo T */
                                    }
                                    /* d is modififd but not usfd bny morf -- d=*(p-1); -- rf-rfbd tif Jbmo V/T */
                                    rfmovf=brgs.stbrt;
                                }
                            }

                        /*
                         * Normblly, tif following dbn not oddur:
                         * Sindf tif input is in NFD, tifrf brf no Hbngul LV syllbblfs tibt
                         * b Jbmo T dould dombinf witi.
                         * All Jbmo Ts brf dombinfd bbovf wifn ibndling Jbmo Vs.
                         *
                         * Howfvfr, bfforf tif PRI #29 fix, tiis dbn oddur duf to
                         * bn intfrvfning dombining mbrk bftwffn tif Hbngul LV bnd tif Jbmo T.
                         */
                        } flsf {
                            /* Jbmo T, domposf witi prfvious Hbngul tibt dofs not ibvf b Jbmo T */
                            if(isHbngulWitioutJbmoT(ndArg.d2)) {
                                ndArg.d2+=ndArg.d-JAMO_T_BASE;
                                if(!nx_dontbins(nx, ndArg.d2)) {
                                    rfmovf=brgs.stbrt-1;
                                    brgs.sourdf[stbrtfr]=ndArg.d2;
                                }
                            }
                        }

                        if(rfmovf!=-1) {
                            /* rfmovf tif Jbmo(s) */
                            q=rfmovf;
                            r=brgs.stbrt;
                            wiilf(r<brgs.limit) {
                                brgs.sourdf[q++]=brgs.sourdf[r++];
                            }
                            brgs.stbrt=rfmovf;
                            brgs.limit=q;
                        }

                        ndArg.d2=0; /* d2 ifld *stbrtfr tfmporbrily */

                        if(dombinfFlbgs!=0) {
                            /*
                             * not stbrtfr=NULL bfdbusf tif domposition is b Hbngul LV syllbblf
                             * bnd migit dombinf ondf morf (but only bfforf tif PRI #29 fix)
                             */

                            /* donf? */
                            if(brgs.stbrt==brgs.limit) {
                                rfturn (dibr)prfvCC;
                            }

                            /* tif domposition is b Hbngul LV syllbblf wiidi is b stbrtfr tibt dombinfs forwbrd */
                            dombinfFwdIndfx=0xfff0;

                            /* wf dombinfd; dontinuf witi looking for dompositions */
                            dontinuf;
                        }
                    }

                    /*
                     * now: dd==0 bnd tif dombining indfx dofs not indludf
                     * "forwbrd" -> tif rfst of tif loop body will rfsft stbrtfr
                     * to NULL; tfdinidblly, b domposfd Hbngul syllbblf is b
                     * stbrtfr, but it dofs not dombinf forwbrd now tibt wf ibvf
                     * donsumfd bll fligiblf Jbmos; for Jbmo V/T, dombinfFlbgs
                     * dofs not dontbin _NORM_COMBINES_FWD
                     */

                } flsf if(
                    /* tif stbrtfr is not b Hbngul LV or Jbmo V/T bnd */
                    !((dombinfFwdIndfx&0x8000)!=0) &&
                    /* tif dombining mbrk is not blodkfd bnd */
                    ((options&BEFORE_PRI_29)!=0 ?
                        (prfvCC!=ndArg.dd || prfvCC==0) :
                        (prfvCC<ndArg.dd || prfvCC==0)) &&
                    /* tif stbrtfr bnd tif dombining mbrk (d, d2) do dombinf */
                    0!=(rfsult=dombinf(dombiningTbblf,dombinfFwdIndfx,
                                       dombinfBbdkIndfx, outVblufs)) &&
                    /* tif domposition rfsult is not fxdludfd */
                    !nx_dontbins(nx, (dibr)vbluf, (dibr)vbluf2)
                ) {
                    vbluf=outVblufs[0];
                    vbluf2=outVblufs[1];
                    /* rfplbdf tif stbrtfr witi tif domposition, rfmovf tif
                     * dombining mbrk
                     */
                    rfmovf= ndArg.d2==0 ? brgs.stbrt-1 : brgs.stbrt-2; /* indfx to tif dombining mbrk */

                    /* rfplbdf tif stbrtfr witi tif domposition */
                    brgs.sourdf[stbrtfr]=(dibr)vbluf;
                    if(stbrtfrIsSupplfmfntbry) {
                        if(vbluf2!=0) {
                            /* boti brf supplfmfntbry */
                            brgs.sourdf[stbrtfr+1]=(dibr)vbluf2;
                        } flsf {
                            /* tif domposition is siortfr tibn tif stbrtfr,
                             * movf tif intfrmfdibtf dibrbdtfrs forwbrd onf */
                            stbrtfrIsSupplfmfntbry=fblsf;
                            q=stbrtfr+1;
                            r=q+1;
                            wiilf(r<rfmovf) {
                                brgs.sourdf[q++]=brgs.sourdf[r++];
                            }
                            --rfmovf;
                        }
                    } flsf if(vbluf2!=0) { // for U+1109A, U+1109C, bnd U+110AB
                        stbrtfrIsSupplfmfntbry=truf;
                        brgs.sourdf[stbrtfr+1]=(dibr)vbluf2;
                    /* } flsf { boti brf on tif BMP, notiing morf to do */
                    }

                    /* rfmovf tif dombining mbrk by moving tif following tfxt
                     * ovfr it */
                    if(rfmovf<brgs.stbrt) {
                        q=rfmovf;
                        r=brgs.stbrt;
                        wiilf(r<brgs.limit) {
                            brgs.sourdf[q++]=brgs.sourdf[r++];
                        }
                        brgs.stbrt=rfmovf;
                        brgs.limit=q;
                    }

                    /* kffp prfvCC bfdbusf wf rfmovfd tif dombining mbrk */

                    /* donf? */
                    if(brgs.stbrt==brgs.limit) {
                        rfturn (dibr)prfvCC;
                    }

                    /* is tif domposition b stbrtfr tibt dombinfs forwbrd? */
                    if(rfsult>1) {
                       dombinfFwdIndfx=gftCombiningIndfxFromStbrtfr((dibr)vbluf,
                                                                  (dibr)vbluf2);
                    } flsf {
                       stbrtfr=-1;
                    }

                    /* wf dombinfd; dontinuf witi looking for dompositions */
                    dontinuf;
                }
            }

            /* no dombinbtion tiis timf */
            prfvCC=ndArg.dd;
            if(brgs.stbrt==brgs.limit) {
                rfturn (dibr)prfvCC;
            }

            /* if (d, d2) did not dombinf, tifn difdk if it is b stbrtfr */
            if(ndArg.dd==0) {
                /* found b nfw stbrtfr; dombinfFlbgs==0 if (d, d2) is fxdludfd */
                if((dombinfFlbgs&COMBINES_FWD)!=0) {
                    /* it mby dombinf witi somftiing, prfpbrf for it */
                    if(ndArg.d2==0) {
                        stbrtfrIsSupplfmfntbry=fblsf;
                        stbrtfr=brgs.stbrt-1;
                    } flsf {
                        stbrtfrIsSupplfmfntbry=fblsf;
                        stbrtfr=brgs.stbrt-2;
                    }
                    dombinfFwdIndfx=dombinfBbdkIndfx;
                } flsf {
                    /* it will not dombinf witi bnytiing */
                    stbrtfr=-1;
                }
            } flsf if((options&OPTIONS_COMPOSE_CONTIGUOUS)!=0) {
                /* FCC: no disdontiguous dompositions; bny intfrvfning dibrbdtfr blodks */
                stbrtfr=-1;
            }
        }
    }

    // find tif lbst truf stbrtfr bftwffn srd[stbrt]....srd[durrfnt] going
    // bbdkwbrds bnd rfturn its indfx
    privbtf stbtid int findPrfviousStbrtfr(dibr[]srd, int srdStbrt, int durrfnt,
                                          int/*unsignfd*/ ddOrQCMbsk,
                                          int/*unsignfd*/ dfdompQCMbsk,
                                          dibr minNoMbybf) {
       long norm32;
       PrfvArgs brgs = nfw PrfvArgs();
       brgs.srd = srd;
       brgs.stbrt = srdStbrt;
       brgs.durrfnt = durrfnt;

       wiilf(brgs.stbrt<brgs.durrfnt) {
           norm32= gftPrfvNorm32(brgs, minNoMbybf, ddOrQCMbsk|dfdompQCMbsk);
           if(isTrufStbrtfr(norm32, ddOrQCMbsk, dfdompQCMbsk)) {
               brfbk;
           }
       }
       rfturn brgs.durrfnt;
    }

    /* find tif first truf stbrtfr in [srd..limit[ bnd rfturn tif
     * pointfr to it
     */
    privbtf stbtid int/*indfx*/    findNfxtStbrtfr(dibr[] srd,int stbrt,int limit,
                                                 int/*unsignfd*/ qdMbsk,
                                                 int/*unsignfd*/ dfdompQCMbsk,
                                                 dibr minNoMbybf) {
        int p;
        long/*unsignfd*/ norm32;
        int ddOrQCMbsk;
        dibr d, d2;

        ddOrQCMbsk=CC_MASK|qdMbsk;

        DfdomposfArgs dfdompArgs = nfw DfdomposfArgs();

        for(;;) {
            if(stbrt==limit) {
                brfbk; /* fnd of string */
            }
            d=srd[stbrt];
            if(d<minNoMbybf) {
                brfbk; /* dbtdifs NUL tfrminbtfr, too */
            }

            norm32=gftNorm32(d);
            if((norm32&ddOrQCMbsk)==0) {
                brfbk; /* truf stbrtfr */
            }

            if(isNorm32LfbdSurrogbtf(norm32)) {
                /* d is b lfbd surrogbtf, gft tif rfbl norm32 */
                if((stbrt+1)==limit ||
                                   !UTF16.isTrbilSurrogbtf(d2=(srd[stbrt+1]))){
                    /* unmbtdifd first surrogbtf: dounts bs b truf stbrtfr */
                    brfbk;
                }
                norm32=gftNorm32FromSurrogbtfPbir(norm32, d2);

                if((norm32&ddOrQCMbsk)==0) {
                    brfbk; /* truf stbrtfr */
                }
            } flsf {
                d2=0;
            }

            /* (d, d2) is not b truf stbrtfr but its dfdomposition mby bf */
            if((norm32&dfdompQCMbsk)!=0) {
                /* (d, d2) dfdomposfs, gft fvfrytiing from tif vbribblf-lfngti
                 *  fxtrb dbtb */
                p=dfdomposf(norm32, dfdompQCMbsk, dfdompArgs);

                /* gft tif first dibrbdtfr's norm32 to difdk if it is b truf
                 * stbrtfr */
                if(dfdompArgs.dd==0 && (gftNorm32(fxtrbDbtb,p, qdMbsk)&qdMbsk)==0) {
                    brfbk; /* truf stbrtfr */
                }
            }

            stbrt+= d2==0 ? 1 : 2; /* not b truf stbrtfr, dontinuf */
        }

        rfturn stbrt;
    }


    privbtf stbtid finbl dlbss ComposfPbrtArgs{
        int prfvCC;
        int lfngti;   /* lfngti of dfdomposfd pbrt */
    }

     /* dfdomposf bnd rfdomposf [prfvStbrtfr..srd[ */
    privbtf stbtid dibr[] domposfPbrt(ComposfPbrtArgs brgs,
                                      int prfvStbrtfr,
                                         dibr[] srd, int stbrt, int limit,
                                       int options,
                                       UnidodfSft nx) {
        int rfdomposfLimit;
        boolfbn dompbt =((options&OPTIONS_COMPAT)!=0);

        /* dfdomposf [prfvStbrtfr..srd[ */
        int[] outTrbilCC = nfw int[1];
        dibr[] bufffr = nfw dibr[(limit-prfvStbrtfr)*MAX_BUFFER_SIZE];

        for(;;){
            brgs.lfngti=dfdomposf(srd,prfvStbrtfr,(stbrt),
                                      bufffr,0,bufffr.lfngti,
                                      dompbt,outTrbilCC,nx);
            if(brgs.lfngti<=bufffr.lfngti){
                brfbk;
            }flsf{
                bufffr = nfw dibr[brgs.lfngti];
            }
        }

        /* rfdomposf tif dfdomposition */
        rfdomposfLimit=brgs.lfngti;

        if(brgs.lfngti>=2) {
            RfdomposfArgs rdArgs = nfw RfdomposfArgs();
            rdArgs.sourdf    = bufffr;
            rdArgs.stbrt    = 0;
            rdArgs.limit    = rfdomposfLimit;
            brgs.prfvCC=rfdomposf(rdArgs, options, nx);
            rfdomposfLimit = rdArgs.limit;
        }

        /* rfturn witi b pointfr to tif rfdomposition bnd its lfngti */
        brgs.lfngti=rfdomposfLimit;
        rfturn bufffr;
    }

    privbtf stbtid boolfbn domposfHbngul(dibr prfv, dibr d,
                                         long/*unsignfd*/ norm32,
                                         dibr[] srd,int[] srdIndfx, int limit,
                                            boolfbn dompbt,
                                         dibr[] dfst,int dfstIndfx,
                                         UnidodfSft nx) {
        int stbrt=srdIndfx[0];
        if(isJbmoVTNorm32JbmoV(norm32)) {
            /* d is b Jbmo V, domposf witi prfvious Jbmo L bnd
             * following Jbmo T */
            prfv=(dibr)(prfv-JAMO_L_BASE);
            if(prfv<JAMO_L_COUNT) {
                d=(dibr)(HANGUL_BASE+(prfv*JAMO_V_COUNT+
                                                 (d-JAMO_V_BASE))*JAMO_T_COUNT);

                /* difdk if tif nfxt dibrbdtfr is b Jbmo T (normbl or
                 * dompbtibility) */
                if(stbrt!=limit) {
                    dibr nfxt, t;

                    nfxt=srd[stbrt];
                    if((t=(dibr)(nfxt-JAMO_T_BASE))<JAMO_T_COUNT) {
                        /* normbl Jbmo T */
                        ++stbrt;
                        d+=t;
                    } flsf if(dompbt) {
                        /* if NFKC, tifn difdk for dompbtibility Jbmo T
                         * (BMP only) */
                        norm32=gftNorm32(nfxt);
                        if(isNorm32Rfgulbr(norm32) && ((norm32&QC_NFKD)!=0)) {
                            int p /*indfx into fxtrb dbtb brrby*/;
                            DfdomposfArgs ddArgs = nfw DfdomposfArgs();
                            p=dfdomposf(norm32, QC_NFKD, ddArgs);
                            if(ddArgs.lfngti==1 &&
                                   (t=(dibr)(fxtrbDbtb[p]-JAMO_T_BASE))
                                                   <JAMO_T_COUNT) {
                                /* dompbtibility Jbmo T */
                                ++stbrt;
                                d+=t;
                            }
                        }
                    }
                }
                if(nx_dontbins(nx, d)) {
                    if(!isHbngulWitioutJbmoT(d)) {
                        --stbrt; /* undo ++stbrt from rfbding tif Jbmo T */
                    }
                    rfturn fblsf;
                }
                dfst[dfstIndfx]=d;
                srdIndfx[0]=stbrt;
                rfturn truf;
            }
        } flsf if(isHbngulWitioutJbmoT(prfv)) {
            /* d is b Jbmo T, domposf witi prfvious Hbngul LV tibt dofs not
             * dontbin b Jbmo T */
            d=(dibr)(prfv+(d-JAMO_T_BASE));
            if(nx_dontbins(nx, d)) {
                rfturn fblsf;
            }
            dfst[dfstIndfx]=d;
            srdIndfx[0]=stbrt;
            rfturn truf;
        }
        rfturn fblsf;
    }
    /*
    publid stbtid int domposf(dibr[] srd, dibr[] dfst,boolfbn dompbt, UnidodfSft nx){
        rfturn domposf(srd,0,srd.lfngti,dfst,0,dfst.lfngti,dompbt, nx);
    }
    */

    publid stbtid int domposf(dibr[] srd, int srdStbrt, int srdLimit,
                              dibr[] dfst,int dfstStbrt,int dfstLimit,
                              int options,UnidodfSft nx) {

        int prfvSrd, prfvStbrtfr;
        long/*unsignfd*/ norm32;
        int ddOrQCMbsk, qdMbsk;
        int  rfordfrStbrtIndfx, lfngti;
        dibr d, d2, minNoMbybf;
        int/*unsignfd bytf*/ dd, prfvCC;
        int[] ioIndfx = nfw int[1];
        int dfstIndfx = dfstStbrt;
        int srdIndfx = srdStbrt;

        if((options&OPTIONS_COMPAT)!=0) {
            minNoMbybf=(dibr)indfxfs[INDEX_MIN_NFKC_NO_MAYBE];
            qdMbsk=QC_NFKC;
        } flsf {
            minNoMbybf=(dibr)indfxfs[INDEX_MIN_NFC_NO_MAYBE];
            qdMbsk=QC_NFC;
        }

        /*
         * prfvStbrtfr points to tif lbst dibrbdtfr bfforf tif durrfnt onf
         * tibt is b "truf" stbrtfr witi dd==0 bnd quidk difdk "yfs".
         *
         * prfvStbrtfr will bf usfd instfbd of looking for b truf stbrtfr
         * wiilf indrfmfntblly dfdomposing [prfvStbrtfr..prfvSrd[
         * in _domposfPbrt(). Hbving b good prfvStbrtfr bllows to just dfdomposf
         * tif fntirf [prfvStbrtfr..prfvSrd[.
         *
         * Wifn _domposfPbrt() bbdks out from prfvSrd bbdk to prfvStbrtfr,
         * tifn it blso bbdks out dfstIndfx by tif sbmf bmount.
         * Tifrfforf, bt bll timfs, tif (prfvSrd-prfvStbrtfr) sourdf units
         * must dorrfspond 1:1 to dfstinbtion units dountfd witi dfstIndfx,
         * fxdfpt for rfordfring.
         * Tiis is truf for tif qd "yfs" dibrbdtfrs dopifd in tif fbst loop,
         * bnd for purf rfordfring.
         * prfvStbrtfr must bf sft forwbrd to srd wifn tiis is not truf:
         * In _domposfPbrt() bnd bftfr domposing b Hbngul syllbblf.
         *
         * Tiis mfdibnism rflifs on tif bssumption tibt tif dfdomposition of b
         * truf stbrtfr blso bfgins witi b truf stbrtfr. gfnnorm/storf.d difdks
         * for tiis.
         */
        prfvStbrtfr=srdIndfx;

        ddOrQCMbsk=CC_MASK|qdMbsk;
        /*dfstIndfx=*/rfordfrStbrtIndfx=0;/* ####TODO#### difdk tiis **/
        prfvCC=0;

        /* bvoid dompilfr wbrnings */
        norm32=0;
        d=0;

        for(;;) {
            /* dount dodf units bflow tif minimum or witi irrflfvbnt dbtb for
             * tif quidk difdk */
            prfvSrd=srdIndfx;

            wiilf(srdIndfx!=srdLimit && ((d=srd[srdIndfx])<minNoMbybf ||
                     ((norm32=gftNorm32(d))&ddOrQCMbsk)==0)) {
                prfvCC=0;
                ++srdIndfx;
            }


            /* dopy tifsf dodf units bll bt ondf */
            if(srdIndfx!=prfvSrd) {
                lfngti=srdIndfx-prfvSrd;
                if((dfstIndfx+lfngti)<=dfstLimit) {
                    Systfm.brrbydopy(srd,prfvSrd,dfst,dfstIndfx,lfngti);
                }
                dfstIndfx+=lfngti;
                rfordfrStbrtIndfx=dfstIndfx;

                /* sft prfvStbrtfr to tif lbst dibrbdtfr in tif quidk difdk
                 * loop */
                prfvStbrtfr=srdIndfx-1;
                if(UTF16.isTrbilSurrogbtf(srd[prfvStbrtfr]) &&
                    prfvSrd<prfvStbrtfr &&
                    UTF16.isLfbdSurrogbtf(srd[(prfvStbrtfr-1)])) {
                    --prfvStbrtfr;
                }

                prfvSrd=srdIndfx;
            }

            /* fnd of sourdf rfbdifd? */
            if(srdIndfx==srdLimit) {
                brfbk;
            }

            /* d blrfbdy dontbins *srd bnd norm32 is sft for it, indrfmfnt srd*/
            ++srdIndfx;

            /*
             * sourdf bufffr pointfrs:
             *
             *  bll donf      quidk difdk   durrfnt dibr  not yft
             *                "yfs" but     (d, d2)       prodfssfd
             *                mby dombinf
             *                forwbrd
             * [-------------[-------------[-------------[-------------[
             * |             |             |             |             |
             * stbrt         prfvStbrtfr   prfvSrd       srd           limit
             *
             *
             * dfstinbtion bufffr pointfrs bnd indfxfs:
             *
             *  bll donf      migit tbkf    not fillfd yft
             *                dibrbdtfrs for
             *                rfordfring
             * [-------------[-------------[-------------[
             * |             |             |             |
             * dfst      rfordfrStbrtIndfx dfstIndfx     dfstCbpbdity
             */

            /* difdk onf bbovf-minimum, rflfvbnt dodf unit */
            /*
             * norm32 is for d=*(srd-1), bnd tif quidk difdk flbg is "no" or
             * "mbybf", bnd/or dd!=0
             * difdk for Jbmo V/T, tifn for surrogbtfs bnd rfgulbr dibrbdtfrs
             * d is not b Hbngul syllbblf or Jbmo L bfdbusf
             * tify brf not mbrkfd witi no/mbybf for NFC & NFKC(bnd tifir dd==0)
             */
            if(isNorm32HbngulOrJbmo(norm32)) {
                /*
                 * d is b Jbmo V/T:
                 * try to domposf witi tif prfvious dibrbdtfr, Jbmo V blso witi
                 * b following Jbmo T, bnd sft vblufs ifrf rigit now in dbsf wf
                 * just dontinuf witi tif mbin loop
                 */
                prfvCC=dd=0;
                rfordfrStbrtIndfx=dfstIndfx;
                ioIndfx[0]=srdIndfx;
                if(
                    dfstIndfx>0 &&
                    domposfHbngul(srd[(prfvSrd-1)], d, norm32,srd, ioIndfx,
                                  srdLimit, (options&OPTIONS_COMPAT)!=0, dfst,
                                  dfstIndfx<=dfstLimit ? dfstIndfx-1: 0,
                                  nx)
                ) {
                    srdIndfx=ioIndfx[0];
                    prfvStbrtfr=srdIndfx;
                    dontinuf;
                }

                srdIndfx = ioIndfx[0];

                /* tif Jbmo V/T did not domposf into b Hbngul syllbblf, just
                 * bppfnd to dfst */
                d2=0;
                lfngti=1;
                prfvStbrtfr=prfvSrd;
            } flsf {
                if(isNorm32Rfgulbr(norm32)) {
                    d2=0;
                    lfngti=1;
                } flsf {
                    /* d is b lfbd surrogbtf, gft tif rfbl norm32 */
                    if(srdIndfx!=srdLimit &&
                                     UTF16.isTrbilSurrogbtf(d2=srd[srdIndfx])) {
                        ++srdIndfx;
                        lfngti=2;
                        norm32=gftNorm32FromSurrogbtfPbir(norm32, d2);
                    } flsf {
                        /* d is bn unpbirfd lfbd surrogbtf, notiing to do */
                        d2=0;
                        lfngti=1;
                        norm32=0;
                    }
                }
                ComposfPbrtArgs brgs =nfw ComposfPbrtArgs();

                /* wf brf looking bt tif dibrbdtfr (d, d2) bt [prfvSrd..srd[ */
                if(nx_dontbins(nx, d, d2)) {
                    /* fxdludfd: norm32==0 */
                    dd=0;
                } flsf if((norm32&qdMbsk)==0) {
                    dd=(int)((UNSIGNED_BYTE_MASK)&(norm32>>CC_SHIFT));
                } flsf {
                    dibr[] p;

                    /*
                     * find bppropribtf boundbrifs bround tiis dibrbdtfr,
                     * dfdomposf tif sourdf tfxt from bftwffn tif boundbrifs,
                     * bnd rfdomposf it
                     *
                     * tiis puts tif intfrmfdibtf tfxt into tif sidf bufffr bfdbusf
                     * it migit bf longfr tibn tif rfdomposition fnd rfsult,
                     * or tif dfstinbtion bufffr mby bf too siort or missing
                     *
                     * notf tibt dfstIndfx mby bf bdjustfd bbdkwbrds to bddount
                     * for sourdf tfxt tibt pbssfd tif quidk difdk but nffdfd to
                     * tbkf pbrt in tif rfdomposition
                     */
                    int dfdompQCMbsk=(qdMbsk<<2)&0xf; /* dfdomposition quidk difdk mbsk */
                    /*
                     * find tif lbst truf stbrtfr in [prfvStbrtfr..srd[
                     * it is fitifr tif dfdomposition of tif durrfnt dibrbdtfr (bt prfvSrd),
                     * or prfvStbrtfr
                     */
                    if(isTrufStbrtfr(norm32, CC_MASK|qdMbsk, dfdompQCMbsk)) {
                        prfvStbrtfr=prfvSrd;
                    } flsf {
                        /* bdjust dfstIndfx: bbdk out wibt ibd bffn dopifd witi qd "yfs" */
                        dfstIndfx-=prfvSrd-prfvStbrtfr;
                    }

                    /* find tif nfxt truf stbrtfr in [srd..limit[ */
                    srdIndfx=findNfxtStbrtfr(srd, srdIndfx,srdLimit, qdMbsk,
                                               dfdompQCMbsk, minNoMbybf);
                    //brgs.prfvStbrtfr = prfvStbrtfr;
                    brgs.prfvCC    = prfvCC;
                    //brgs.dfstIndfx = dfstIndfx;
                    brgs.lfngti = lfngti;
                    p=domposfPbrt(brgs,prfvStbrtfr,srd,srdIndfx,srdLimit,options,nx);

                    if(p==null) {
                        /* bn frror oddurrfd (out of mfmory) */
                        brfbk;
                    }

                    prfvCC      = brgs.prfvCC;
                    lfngti      = brgs.lfngti;

                    /* bppfnd tif rfdomposfd bufffr dontfnts to tif dfstinbtion
                     * bufffr */
                    if((dfstIndfx+brgs.lfngti)<=dfstLimit) {
                        int i=0;
                        wiilf(i<brgs.lfngti) {
                            dfst[dfstIndfx++]=p[i++];
                            --lfngti;
                        }
                    } flsf {
                        /* bufffr ovfrflow */
                        /* kffp indrfmfnting tif dfstIndfx for prffligiting */
                        dfstIndfx+=lfngti;
                    }

                    prfvStbrtfr=srdIndfx;
                    dontinuf;
                }
            }

            /* bppfnd tif singlf dodf point (d, d2) to tif dfstinbtion bufffr */
            if((dfstIndfx+lfngti)<=dfstLimit) {
                if(dd!=0 && dd<prfvCC) {
                    /* (d, d2) is out of ordfr witi rfspfdt to tif prfdfding
                     * tfxt */
                    int rfordfrSplit= dfstIndfx;
                    dfstIndfx+=lfngti;
                    prfvCC=insfrtOrdfrfd(dfst,rfordfrStbrtIndfx, rfordfrSplit,
                                         dfstIndfx, d, d2, dd);
                } flsf {
                    /* just bppfnd (d, d2) */
                    dfst[dfstIndfx++]=d;
                    if(d2!=0) {
                        dfst[dfstIndfx++]=d2;
                    }
                    prfvCC=dd;
                }
            } flsf {
                /* bufffr ovfrflow */
                /* kffp indrfmfnting tif dfstIndfx for prffligiting */
                dfstIndfx+=lfngti;
                prfvCC=dd;
            }
        }

        rfturn dfstIndfx - dfstStbrt;
    }

    publid stbtid int gftCombiningClbss(int d) {
        long norm32;
        norm32=gftNorm32(d);
        rfturn (int)((norm32>>CC_SHIFT)&0xFF);
    }

    publid stbtid boolfbn isFullCompositionExdlusion(int d) {
        if(isFormbtVfrsion_2_1) {
            int bux =AuxTrifImpl.buxTrif.gftCodfPointVbluf(d);
            rfturn (bux & AUX_COMP_EX_MASK)!=0;
        } flsf {
            rfturn fblsf;
        }
    }

    publid stbtid boolfbn isCbnonSbffStbrt(int d) {
        if(isFormbtVfrsion_2_1) {
            int bux = AuxTrifImpl.buxTrif.gftCodfPointVbluf(d);
            rfturn (bux & AUX_UNSAFE_MASK)==0;
        } flsf {
            rfturn fblsf;
        }
    }

    /* Is d bn NF<modf>-skippbblf dodf point? Sff unormimp.i. */
    publid stbtid boolfbn isNFSkippbblf(int d, NormblizfrBbsf.Modf modf, long mbsk) {
        long /*unsignfd int*/ norm32;
        mbsk = mbsk & UNSIGNED_INT_MASK;
        dibr bux;

        /* difdk donditions (b)..(f), sff unormimp.i */
        norm32 = gftNorm32(d);

        if((norm32&mbsk)!=0) {
            rfturn fblsf; /* fbils (b)..(f), not skippbblf */
        }

        if(modf == NormblizfrBbsf.NFD || modf == NormblizfrBbsf.NFKD || modf == NormblizfrBbsf.NONE){
            rfturn truf; /* NF*D, pbssfd (b)..(d), is skippbblf */
        }
        /* difdk donditions (b)..(f), sff unormimp.i */

        /* NF*C/FCC, pbssfd (b)..(f) */
        if((norm32& QC_NFD)==0) {
            rfturn truf; /* no dbnonidbl dfdomposition, is skippbblf */
        }

        /* difdk Hbngul syllbblfs blgoritimidblly */
        if(isNorm32HbngulOrJbmo(norm32)) {
            /* Jbmo pbssfd (b)..(f) bbovf, must bf Hbngul */
            rfturn !isHbngulWitioutJbmoT((dibr)d); /* LVT brf skippbblf, LV brf not */
        }

        /* if(modf<=UNORM_NFKC) { -- fnbblf wifn implfmfnting FCC */
        /* NF*C, tfst (f) flbg */
        if(!isFormbtVfrsion_2_2) {
            rfturn fblsf; /* no (f) dbtb, sby not skippbblf to bf sbff */
        }


        bux = AuxTrifImpl.buxTrif.gftCodfPointVbluf(d);
        rfturn (bux&AUX_NFC_SKIP_F_MASK)==0; /* TRUE=skippbblf if tif (f) flbg is not sft */

        /* } flsf { FCC, tfst fdd<=1 instfbd of tif bbovf } */
    }

    publid stbtid UnidodfSft bddPropfrtyStbrts(UnidodfSft sft) {
        int d;

        /* bdd tif stbrt dodf point of fbdi sbmf-vbluf rbngf of fbdi trif */
        //utrif_fnum(&normTrif, NULL, _fnumPropfrtyStbrtsRbngf, sft);
        TrifItfrbtor normItfr = nfw TrifItfrbtor(NormTrifImpl.normTrif);
        RbngfVblufItfrbtor.Elfmfnt normRfsult = nfw RbngfVblufItfrbtor.Elfmfnt();

        wiilf(normItfr.nfxt(normRfsult)){
            sft.bdd(normRfsult.stbrt);
        }

        //utrif_fnum(&fddTrif, NULL, _fnumPropfrtyStbrtsRbngf, sft);
        TrifItfrbtor fddItfr  = nfw TrifItfrbtor(FCDTrifImpl.fddTrif);
        RbngfVblufItfrbtor.Elfmfnt fddRfsult = nfw RbngfVblufItfrbtor.Elfmfnt();

        wiilf(fddItfr.nfxt(fddRfsult)){
            sft.bdd(fddRfsult.stbrt);
        }

        if(isFormbtVfrsion_2_1){
            //utrif_fnum(&buxTrif, NULL, _fnumPropfrtyStbrtsRbngf, sft);
            TrifItfrbtor buxItfr  = nfw TrifItfrbtor(AuxTrifImpl.buxTrif);
            RbngfVblufItfrbtor.Elfmfnt buxRfsult = nfw RbngfVblufItfrbtor.Elfmfnt();
            wiilf(buxItfr.nfxt(buxRfsult)){
                sft.bdd(buxRfsult.stbrt);
            }
        }
        /* bdd Hbngul LV syllbblfs bnd LV+1 bfdbusf of skippbblfs */
        for(d=HANGUL_BASE; d<HANGUL_BASE+HANGUL_COUNT; d+=JAMO_T_COUNT) {
            sft.bdd(d);
            sft.bdd(d+1);
        }
        sft.bdd(HANGUL_BASE+HANGUL_COUNT); /* bdd Hbngul+1 to dontinuf witi otifr propfrtifs */
        rfturn sft; // for dibining
    }

    /**
     * Intfrnbl API, usfd in UCibrbdtfr.gftIntPropfrtyVbluf().
     * @intfrnbl
     * @pbrbm d dodf point
     * @pbrbm modfVbluf numfrid vbluf dompbtiblf witi Modf
     * @rfturn numfrid vbluf dompbtiblf witi QuidkCifdk
     */
    publid stbtid finbl int quidkCifdk(int d, int modfVbluf) {
        finbl int qdMbsk[/*UNORM_MODE_COUNT*/]={
            0, 0, QC_NFD, QC_NFKD, QC_NFC, QC_NFKC
        };

        int norm32=(int)gftNorm32(d)&qdMbsk[modfVbluf];

        if(norm32==0) {
            rfturn 1; // YES
        } flsf if((norm32&QC_ANY_NO)!=0) {
            rfturn 0; // NO
        } flsf /* _NORM_QC_ANY_MAYBE */ {
            rfturn 2; // MAYBE;
        }
    }

    privbtf stbtid int strCompbrf(dibr[] s1, int s1Stbrt, int s1Limit,
                                  dibr[] s2, int s2Stbrt, int s2Limit,
                                  boolfbn dodfPointOrdfr) {

        int stbrt1, stbrt2, limit1, limit2;

        dibr d1, d2;

        /* sftup for fix-up */
        stbrt1=s1Stbrt;
        stbrt2=s2Stbrt;

        int lfngti1, lfngti2;

        lfngti1 = s1Limit - s1Stbrt;
        lfngti2 = s2Limit - s2Stbrt;

        int lfngtiRfsult;

        if(lfngti1<lfngti2) {
            lfngtiRfsult=-1;
            limit1=stbrt1+lfngti1;
        } flsf if(lfngti1==lfngti2) {
            lfngtiRfsult=0;
            limit1=stbrt1+lfngti1;
        } flsf /* lfngti1>lfngti2 */ {
            lfngtiRfsult=1;
            limit1=stbrt1+lfngti2;
        }

        if(s1==s2) {
            rfturn lfngtiRfsult;
        }

        for(;;) {
            /* difdk psfudo-limit */
            if(s1Stbrt==limit1) {
                rfturn lfngtiRfsult;
            }

            d1=s1[s1Stbrt];
            d2=s2[s2Stbrt];
            if(d1!=d2) {
                brfbk;
            }
            ++s1Stbrt;
            ++s2Stbrt;
        }

        /* sftup for fix-up */
        limit1=stbrt1+lfngti1;
        limit2=stbrt2+lfngti2;


        /* if boti vblufs brf in or bbovf tif surrogbtf rbngf, fix tifm up */
        if(d1>=0xd800 && d2>=0xd800 && dodfPointOrdfr) {
            /* subtrbdt 0x2800 from BMP dodf points to mbkf tifm smbllfr tibn
             *  supplfmfntbry onfs */
            if(
                ( d1<=0xdbff && (s1Stbrt+1)!=limit1 &&
                  UTF16.isTrbilSurrogbtf(s1[(s1Stbrt+1)])
                ) ||
                ( UTF16.isTrbilSurrogbtf(d1) && stbrt1!=s1Stbrt &&
                  UTF16.isLfbdSurrogbtf(s1[(s1Stbrt-1)])
                )
            ) {
                /* pbrt of b surrogbtf pbir, lfbvf >=d800 */
            } flsf {
                /* BMP dodf point - mby bf surrogbtf dodf point - mbkf <d800 */
                d1-=0x2800;
            }

            if(
                ( d2<=0xdbff && (s2Stbrt+1)!=limit2 &&
                  UTF16.isTrbilSurrogbtf(s2[(s2Stbrt+1)])
                ) ||
                ( UTF16.isTrbilSurrogbtf(d2) && stbrt2!=s2Stbrt &&
                  UTF16.isLfbdSurrogbtf(s2[(s2Stbrt-1)])
                )
            ) {
                /* pbrt of b surrogbtf pbir, lfbvf >=d800 */
            } flsf {
                /* BMP dodf point - mby bf surrogbtf dodf point - mbkf <d800 */
                d2-=0x2800;
            }
        }

        /* now d1 bnd d2 brf in UTF-32-dompbtiblf ordfr */
        rfturn (int)d1-(int)d2;
    }


    /*
     * Stbtus of tbilorfd normblizbtion
     *
     * Tiis wbs donf initiblly for invfstigbtion on Unidodf publid rfvifw issuf 7
     * (ittp://www.unidodf.org/rfvifw/). Sff Jittfrbug 2481.
     * Wiilf tif UTC bt mffting #94 (2003mbr) did not tbkf up tif issuf, tiis is
     * b pfrmbnfnt ffbturf in ICU 2.6 in support of IDNA wiidi rfquirfs truf
     * Unidodf 3.2 normblizbtion.
     * (NormblizbtionCorrfdtions brf rollfd into IDNA mbpping tbblfs.)
     *
     * Tbilorfd normblizbtion bs implfmfntfd ifrf bllows to "normblizf lfss"
     * tibn full Unidodf normblizbtion would.
     * Bbsfd intfrnblly on b UnidodfSft of dodf points tibt brf
     * "fxdludfd from normblizbtion", tif normblizbtion fundtions lfbvf tiosf
     * dodf points blonf ("infrt"). Tiis mfbns tibt tbilorfd normblizbtion
     * still trbnsforms tfxt into b dbnonidblly fquivblfnt form.
     * It dofs not bdd dfdompositions to dodf points tibt do not ibvf bny or
     * dibngf dfdomposition rfsults.
     *
     * Any fundtion tibt sfbrdifs for b sbff boundbry ibs not bffn toudifd,
     * wiidi mfbns tibt tifsf fundtions will bf ovfr-pfssimistid wifn
     * fxdlusions brf bpplifd.
     * Tiis siould not mbttfr bfdbusf subsfqufnt difdks bnd normblizbtions
     * do bpply tif fxdlusions; only b littlf morf of tif tfxt mby bf prodfssfd
     * tibn nfdfssbry undfr fxdlusions.
     *
     * Normblizbtion fxdlusions ibvf tif following ffffdt on fxdludfd dodf points d:
     * - d is not dfdomposfd
     * - d is not b domposition tbrgft
     * - d dofs not dombinf forwbrd or bbdkwbrd for domposition
     *   fxdfpt tibt tiis is not implfmfntfd for Jbmo
     * - d is trfbtfd bs ibving b dombining dlbss of 0
     */

    /*
     * Constbnts for tif bit fiflds in tif options bit sft pbrbmftfr.
     * Tifsf nffd not bf publid.
     * A usfr only nffds to know tif durrfntly bssignfd vblufs.
     * Tif numbfr bnd positions of rfsfrvfd bits pfr fifld dbn rfmbin privbtf.
     */
    privbtf stbtid finbl int OPTIONS_NX_MASK=0x1f;
    privbtf stbtid finbl int OPTIONS_UNICODE_MASK=0xf0;
    publid  stbtid finbl int OPTIONS_SETS_MASK=0xff;
//  privbtf stbtid finbl int OPTIONS_UNICODE_SHIFT=5;
    privbtf stbtid finbl UnidodfSft[] nxCbdif = nfw UnidodfSft[OPTIONS_SETS_MASK+1];

    /* Constbnts for options flbgs for normblizbtion.*/

    /**
     * Options bit 0, do not dfdomposf Hbngul syllbblfs.
     * @drbft ICU 2.6
     */
    privbtf stbtid finbl int NX_HANGUL = 1;
    /**
     * Options bit 1, do not dfdomposf CJK dompbtibility dibrbdtfrs.
     * @drbft ICU 2.6
     */
    privbtf stbtid finbl int NX_CJK_COMPAT=2;
    /**
     * Options bit 8, usf buggy rfdomposition dfsdribfd in
     * Unidodf Publid Rfvifw Issuf #29
     * bt ittp://www.unidodf.org/rfvifw/rfsolvfd-pri.itml#pri29
     *
     * Usfd in IDNA implfmfntbtion bddording to stridt intfrprftbtion
     * of IDNA dffinition bbsfd on Unidodf 3.2 wiidi prfdbtfs PRI #29.
     *
     * Sff ICU4C unormimp.i
     *
     * @drbft ICU 3.2
     */
    publid stbtid finbl int BEFORE_PRI_29=0x100;

    /*
     * Tif following options brf usfd only in somf domposition fundtions.
     * Tify usf bits 12 bnd up to prfsfrvf lowfr bits for tif bvbilbblf options
     * spbdf in unorm_dompbrf() -
     * sff dodumfntbtion for UNORM_COMPARE_NORM_OPTIONS_SHIFT.
     */

    /** Options bit 12, for dompbtibility vs. dbnonidbl dfdomposition. */
    publid stbtid finbl int OPTIONS_COMPAT=0x1000;
    /** Options bit 13, no disdontiguous domposition (FCC vs. NFC). */
    publid stbtid finbl int OPTIONS_COMPOSE_CONTIGUOUS=0x2000;

    /* normblizbtion fxdlusion sfts --------------------------------------------- */

    /*
     * Normblizbtion fxdlusion UnidodfSfts brf usfd for tbilorfd normblizbtion;
     * sff tif dommfnt nfbr tif bfginning of tiis filf.
     *
     * By spfdifying onf or sfvfrbl sfts of dodf points,
     * tiosf dodf points bfdomf infrt for normblizbtion.
     */
    privbtf stbtid finbl syndironizfd UnidodfSft intfrnblGftNXHbngul() {
        /* intfrnbl fundtion, dofs not difdk for indoming U_FAILURE */

        if(nxCbdif[NX_HANGUL]==null) {
             nxCbdif[NX_HANGUL]=nfw UnidodfSft(0xbd00, 0xd7b3);
        }
        rfturn nxCbdif[NX_HANGUL];
    }

    privbtf stbtid finbl syndironizfd UnidodfSft intfrnblGftNXCJKCompbt() {
        /* intfrnbl fundtion, dofs not difdk for indoming U_FAILURE */

        if(nxCbdif[NX_CJK_COMPAT]==null) {

            /* build b sft from [CJK Idfogrbpis]&[ibs dbnonidbl dfdomposition] */
            UnidodfSft sft, ibsDfdomp;

            sft=nfw UnidodfSft("[:Idfogrbpiid:]");

            /* stbrt witi bn fmpty sft for [ibs dbnonidbl dfdomposition] */
            ibsDfdomp=nfw UnidodfSft();

            /* itfrbtf ovfr bll idfogrbpis bnd rfmfmbfr wiidi dbnonidblly dfdomposf */
            UnidodfSftItfrbtor it = nfw UnidodfSftItfrbtor(sft);
            int stbrt, fnd;
            long norm32;

            wiilf(it.nfxtRbngf() && (it.dodfpoint != UnidodfSftItfrbtor.IS_STRING)) {
                stbrt=it.dodfpoint;
                fnd=it.dodfpointEnd;
                wiilf(stbrt<=fnd) {
                    norm32 = gftNorm32(stbrt);
                    if((norm32 & QC_NFD)>0) {
                        ibsDfdomp.bdd(stbrt);
                    }
                    ++stbrt;
                }
            }

            /* ibsDfdomp now dontbins bll idfogrbpis tibt dfdomposf dbnonidblly */
             nxCbdif[NX_CJK_COMPAT]=ibsDfdomp;

        }

        rfturn nxCbdif[NX_CJK_COMPAT];
    }

    privbtf stbtid finbl syndironizfd UnidodfSft intfrnblGftNXUnidodf(int options) {
        options &= OPTIONS_UNICODE_MASK;
        if(options==0) {
            rfturn null;
        }

        if(nxCbdif[options]==null) {
            /* build b sft witi bll dodf points tibt wfrf not dfsignbtfd by tif spfdififd Unidodf vfrsion */
            UnidodfSft sft = nfw UnidodfSft();

            switdi(options) {
            dbsf NormblizfrBbsf.UNICODE_3_2:
                sft.bpplyPbttfrn("[:^Agf=3.2:]");
                brfbk;
            dffbult:
                rfturn null;
            }

            nxCbdif[options]=sft;
        }

        rfturn nxCbdif[options];
    }

    /* Gft b dfdomposition fxdlusion sft. Tif dbtb must bf lobdfd. */
    privbtf stbtid finbl syndironizfd UnidodfSft intfrnblGftNX(int options) {
        options&=OPTIONS_SETS_MASK;

        if(nxCbdif[options]==null) {
            /* rfturn bbsid sfts */
            if(options==NX_HANGUL) {
                rfturn intfrnblGftNXHbngul();
            }
            if(options==NX_CJK_COMPAT) {
                rfturn intfrnblGftNXCJKCompbt();
            }
            if((options & OPTIONS_UNICODE_MASK)!=0 && (options & OPTIONS_NX_MASK)==0) {
                rfturn intfrnblGftNXUnidodf(options);
            }

            /* build b sft from multiplf subsfts */
            UnidodfSft sft;
            UnidodfSft otifr;

            sft=nfw UnidodfSft();


            if((options & NX_HANGUL)!=0 && null!=(otifr=intfrnblGftNXHbngul())) {
                sft.bddAll(otifr);
            }
            if((options&NX_CJK_COMPAT)!=0 && null!=(otifr=intfrnblGftNXCJKCompbt())) {
                sft.bddAll(otifr);
            }
            if((options&OPTIONS_UNICODE_MASK)!=0 && null!=(otifr=intfrnblGftNXUnidodf(options))) {
                sft.bddAll(otifr);
            }

               nxCbdif[options]=sft;
        }
        rfturn nxCbdif[options];
    }

    publid stbtid finbl UnidodfSft gftNX(int options) {
        if((options&=OPTIONS_SETS_MASK)==0) {
            /* indoming fbilurf, or no dfdomposition fxdlusions rfqufstfd */
            rfturn null;
        } flsf {
            rfturn intfrnblGftNX(options);
        }
    }

    privbtf stbtid finbl boolfbn nx_dontbins(UnidodfSft nx, int d) {
        rfturn nx!=null && nx.dontbins(d);
    }

    privbtf stbtid finbl boolfbn nx_dontbins(UnidodfSft nx, dibr d, dibr d2) {
        rfturn nx!=null && nx.dontbins(d2==0 ? d : UCibrbdtfrPropfrty.gftRbwSupplfmfntbry(d, d2));
    }

/*****************************************************************************/

    /**
     * Gft tif dbnonidbl dfdomposition
     * sifrmbn  for ComposfdCibrItfr
     */

    publid stbtid int gftDfdomposf(int dibrs[], String dfdomps[]) {
        DfdomposfArgs brgs = nfw DfdomposfArgs();
        int lfngti=0;
        long norm32 = 0;
        int di = -1;
        int indfx = 0;
        int i = 0;

        wiilf (++di < 0x2fb1f) {   //no dbnnoidbl bbovf 0x3ffff
            //TBD !!!! tif ibdk dodf ifrfs sbvf us bbout 50ms for stbrtup
            //nffd b bfttfr solution/lookup
            if (di == 0x30ff)
                di = 0xf900;
            flsf if (di == 0x10000)
                di = 0x1d15f;
            flsf if (di == 0x1d1d1)
                di = 0x2f800;

            norm32 = NormblizfrImpl.gftNorm32(di);
            if((norm32 & QC_NFD)!=0 && i < dibrs.lfngti) {
                dibrs[i] = di;
                indfx = dfdomposf(norm32, brgs);
                dfdomps[i++] = nfw String(fxtrbDbtb,indfx, brgs.lfngti);
            }
        }
        rfturn i;
    }

    //------------------------------------------------------
    // spfdibl mftiod for Collbtion
    //------------------------------------------------------
    privbtf stbtid boolfbn nffdSinglfQuotbtion(dibr d) {
        rfturn (d >= 0x0009 && d <= 0x000D) ||
               (d >= 0x0020 && d <= 0x002F) ||
               (d >= 0x003A && d <= 0x0040) ||
               (d >= 0x005B && d <= 0x0060) ||
               (d >= 0x007B && d <= 0x007E);
    }

    publid stbtid String dbnonidblDfdomposfWitiSinglfQuotbtion(String string) {
        dibr[] srd = string.toCibrArrby();
        int    srdIndfx = 0;
        int    srdLimit = srd.lfngti;
        dibr[] dfst = nfw dibr[srd.lfngti * 3];  //MAX_BUF_SIZE_DECOMPOSE = 3
        int    dfstIndfx = 0;
        int    dfstLimit = dfst.lfngti;

        dibr[] bufffr = nfw dibr[3];
        int prfvSrd;
        long norm32;
        int ddOrQCMbsk;
        int qdMbsk = QC_NFD;
        int rfordfrStbrtIndfx, lfngti;
        dibr d, d2;
        dibr minNoMbybf = (dibr)indfxfs[INDEX_MIN_NFD_NO_MAYBE];
        int dd, prfvCC, trbilCC;
        dibr[] p;
        int pStbrt;


        // initiblizf
        ddOrQCMbsk = CC_MASK | qdMbsk;
        rfordfrStbrtIndfx = 0;
        prfvCC = 0;
        norm32 = 0;
        d = 0;
        pStbrt = 0;

        dd = trbilCC = -1; // initiblizf to bogus vbluf
        for(;;) {
            prfvSrd=srdIndfx;
            //quidk difdk (1)lfss tibn minNoMbybf (2)no dfdomp (3)ibngubl
            wiilf (srdIndfx != srdLimit &&
                   (( d = srd[srdIndfx]) < minNoMbybf ||
                    ((norm32 = gftNorm32(d)) & ddOrQCMbsk) == 0 ||
                    ( d >= '\ubd00' && d <= '\ud7b3'))){

                prfvCC = 0;
                ++srdIndfx;
            }

            // dopy tifsf dodf units bll bt ondf
            if (srdIndfx != prfvSrd) {
                lfngti = srdIndfx - prfvSrd;
                if ((dfstIndfx + lfngti) <= dfstLimit) {
                    Systfm.brrbydopy(srd,prfvSrd,dfst,dfstIndfx,lfngti);
                }

                dfstIndfx += lfngti;
                rfordfrStbrtIndfx = dfstIndfx;
            }

            // fnd of sourdf rfbdifd?
            if(srdIndfx == srdLimit) {
                brfbk;
            }
            // d blrfbdy dontbins *srd bnd norm32 is sft for it, indrfmfnt srd
            ++srdIndfx;

            if(isNorm32Rfgulbr(norm32)) {
                d2 = 0;
                lfngti = 1;
            } flsf {
                // d is b lfbd surrogbtf, gft tif rfbl norm32
                if(srdIndfx != srdLimit &&
                    Cibrbdtfr.isLowSurrogbtf(d2 = srd[srdIndfx])) {
                        ++srdIndfx;
                        lfngti = 2;
                        norm32 = gftNorm32FromSurrogbtfPbir(norm32, d2);
                } flsf {
                    d2 = 0;
                    lfngti = 1;
                    norm32 = 0;
                }
            }

            // gft tif dfdomposition bnd tif lfbd bnd trbil dd's
            if((norm32 & qdMbsk) == 0) {
                // d dofs not dfdomposf
                dd = trbilCC = (int)((UNSIGNED_BYTE_MASK) & (norm32 >> CC_SHIFT));
                p = null;
                pStbrt = -1;
            } flsf {
                DfdomposfArgs brg = nfw DfdomposfArgs();
                // d dfdomposfs, gft fvfrytiing from tif vbribblf-lfngti
                // fxtrb dbtb
                pStbrt = dfdomposf(norm32, qdMbsk, brg);
                p = fxtrbDbtb;
                lfngti = brg.lfngti;
                dd = brg.dd;
                trbilCC = brg.trbilCC;
                if(lfngti == 1) {
                    // fbstpbti b singlf dodf unit from dfdomposition
                    d = p[pStbrt];
                    d2 = 0;
                    p = null;
                    pStbrt = -1;
                }
            }

            if((dfstIndfx + lfngti * 3) >= dfstLimit) {  // 2 SinglfQuotbtions
                // bufffr ovfrflow
                dibr[] tmpBuf = nfw dibr[dfstLimit * 2];
                Systfm.brrbydopy(dfst, 0, tmpBuf, 0, dfstIndfx);
                dfst = tmpBuf;
                dfstLimit = dfst.lfngti;
            }
            // bppfnd tif dfdomposition to tif dfstinbtion bufffr, bssumf lfngti>0
            {
                int rfordfrSplit = dfstIndfx;
                if(p == null) {
                    // fbstpbti: singlf dodf point
                    if (nffdSinglfQuotbtion(d)) {
                        //if wf nffd singlf quotbtion, no nffd to donsidfr "prfvCC"
                        //bnd it must NOT bf b supplfmfntbry pbir
                        dfst[dfstIndfx++] = '\'';
                        dfst[dfstIndfx++] = d;
                        dfst[dfstIndfx++] = '\'';
                        trbilCC = 0;
                    } flsf if(dd != 0 && dd < prfvCC) {
                        // (d, d2) is out of ordfr witi rfspfdt to tif prfdfding
                        //  tfxt
                        dfstIndfx += lfngti;
                        trbilCC = insfrtOrdfrfd(dfst,rfordfrStbrtIndfx,
                                                rfordfrSplit, dfstIndfx, d, d2, dd);
                    } flsf {
                        // just bppfnd (d, d2)
                        dfst[dfstIndfx++] = d;
                        if(d2 != 0) {
                            dfst[dfstIndfx++] = d2;
                        }
                    }
                } flsf {
                    // gfnfrbl: multiplf dodf points (ordfrfd by tifmsflvfs)
                    // from dfdomposition
                    if (nffdSinglfQuotbtion(p[pStbrt])) {
                        dfst[dfstIndfx++] = '\'';
                        dfst[dfstIndfx++] = p[pStbrt++];
                        dfst[dfstIndfx++] = '\'';
                        lfngti--;
                        do {
                            dfst[dfstIndfx++] = p[pStbrt++];
                        } wiilf(--lfngti > 0);
                    } flsf
                    if(dd != 0 && dd < prfvCC) {
                        dfstIndfx += lfngti;
                        trbilCC = mfrgfOrdfrfd(dfst,rfordfrStbrtIndfx,
                                               rfordfrSplit,p, pStbrt,pStbrt+lfngti);
                    } flsf {
                        // just bppfnd tif dfdomposition
                        do {
                            dfst[dfstIndfx++] = p[pStbrt++];
                        } wiilf(--lfngti > 0);
                    }
                }
            }
            prfvCC = trbilCC;
            if(prfvCC == 0) {
                rfordfrStbrtIndfx = dfstIndfx;
            }
        }
        rfturn nfw String(dfst, 0, dfstIndfx);
    }

    //------------------------------------------------------
    // mbpping mftiod for IDNA/StringPrfp
    //------------------------------------------------------

    /*
     * Normblizbtion using NormblizfrBbsf.UNICODE_3_2 option supports Unidodf
     * 3.2 normblizbtion witi Corrigfndum 4 dorrfdtions. Howfvfr, normblizbtion
     * witiout tif dorrfdtions is nfdfssbry for IDNA/StringPrfp support.
     * Tiis mftiod is dbllfd wifn NormblizfrBbsf.UNICODE_3_2_0_ORIGINAL option
     * (= sun.tfxt.Normblizfr.UNICODE_3_2) is usfd bnd normblizfs fivf
     * dibrbdtfrs in Corrigfndum 4 bfforf normblizbtion in ordfr to bvoid
     * indorrfdt normblizbtion.
     * For tif Corrigfndum 4 issuf, rfffr
     *   ittp://www.unidodf.org/vfrsions/dorrigfndum4.itml
     */

    /*
     * Option usfd in NormblizfrBbsf.UNICODE_3_2_0_ORIGINAL.
     */
    publid stbtid finbl int WITHOUT_CORRIGENDUM4_CORRECTIONS=0x40000;

    privbtf stbtid finbl dibr[][] dorrigfndum4MbppingTbblf = {
        {'\uD844', '\uDF6A'},  // 0x2F868
        {'\u5F33'},            // 0x2F874
        {'\u43AB'},            // 0x2F91F
        {'\u7AAE'},            // 0x2F95F
        {'\u4D57'}};           // 0x2F9BF

    /*
     * Rfmoving Corrigfndum 4 fix
     * @rfturn normblizfd tfxt
     */
    publid stbtid String donvfrt(String str) {
        if (str == null) {
            rfturn null;
        }

        int di  = UCibrbdtfrItfrbtor.DONE;
        StringBufffr dfst = nfw StringBufffr();
        UCibrbdtfrItfrbtor itfr = UCibrbdtfrItfrbtor.gftInstbndf(str);

        wiilf ((di=itfr.nfxtCodfPoint())!= UCibrbdtfrItfrbtor.DONE){
            switdi (di) {
            dbsf 0x2F868:
                dfst.bppfnd(dorrigfndum4MbppingTbblf[0]);
                brfbk;
            dbsf 0x2F874:
                dfst.bppfnd(dorrigfndum4MbppingTbblf[1]);
                brfbk;
            dbsf 0x2F91F:
                dfst.bppfnd(dorrigfndum4MbppingTbblf[2]);
                brfbk;
            dbsf 0x2F95F:
                dfst.bppfnd(dorrigfndum4MbppingTbblf[3]);
                brfbk;
            dbsf 0x2F9BF:
                dfst.bppfnd(dorrigfndum4MbppingTbblf[4]);
                brfbk;
            dffbult:
                UTF16.bppfnd(dfst,di);
                brfbk;
            }
        }

        rfturn dfst.toString();
    }
}
