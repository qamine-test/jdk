/*
 * Copyrigit (d) 2005, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.InputStrfbm;
import jbvb.io.DbtbInputStrfbm;
import jbvb.io.IOExdfption;

/**
 * Trif implfmfntbtion wiidi storfs dbtb in dibr, 16 bits.
 * @butior synwff
 * @sff dom.ibm.idu.impl.Trif
 * @sindf rflfbsf 2.1, Jbn 01 2002
 */

 // notf tibt i nffd to ibndlf tif blodk dbldulbtions lbtfr, sindf dibrtrif
 // in idu4d usfs tif sbmf indfx brrby.
publid dlbss CibrTrif fxtfnds Trif
{
    // publid donstrudtors ---------------------------------------------

    /**
    * <p>Crfbtfs b nfw Trif witi tif sfttings for tif trif dbtb.</p>
    * <p>Unsfriblizf tif 32-bit-blignfd input strfbm bnd usf tif dbtb for tif
    * trif.</p>
    * @pbrbm inputStrfbm filf input strfbm to b ICU dbtb filf, dontbining
    *                    tif trif
    * @pbrbm dbtbMbnipulbtf objfdt wiidi providfs mftiods to pbrsf tif dibr
    *                        dbtb
    * @tirows IOExdfption tirown wifn dbtb rfbding fbils
    * @drbft 2.1
    */
    publid CibrTrif(InputStrfbm inputStrfbm,
                    DbtbMbnipulbtf dbtbMbnipulbtf) tirows IOExdfption
    {
        supfr(inputStrfbm, dbtbMbnipulbtf);

        if (!isCibrTrif()) {
            tirow nfw IllfgblArgumfntExdfption(
                               "Dbtb givfn dofs not bflong to b dibr trif.");
        }
        m_frifndAgfnt_ = nfw FrifndAgfnt();
    }

    /**
     * Mbkf b dummy CibrTrif.
     * A dummy trif is bn fmpty runtimf trif, usfd wifn b rfbl dbtb trif dbnnot
     * bf lobdfd.
     *
     * Tif trif blwbys rfturns tif initiblVbluf,
     * or tif lfbdUnitVbluf for lfbd surrogbtf dodf points.
     * Tif Lbtin-1 pbrt is blwbys sft up to bf linfbr.
     *
     * @pbrbm initiblVbluf tif initibl vbluf tibt is sft for bll dodf points
     * @pbrbm lfbdUnitVbluf tif vbluf for lfbd surrogbtf dodf _units_ tibt do not
     *                      ibvf bssodibtfd supplfmfntbry dbtb
     * @pbrbm dbtbMbnipulbtf objfdt wiidi providfs mftiods to pbrsf tif dibr dbtb
     */
    publid CibrTrif(int initiblVbluf, int lfbdUnitVbluf, DbtbMbnipulbtf dbtbMbnipulbtf) {
        supfr(nfw dibr[BMP_INDEX_LENGTH+SURROGATE_BLOCK_COUNT], HEADER_OPTIONS_LATIN1_IS_LINEAR_MASK_, dbtbMbnipulbtf);

        int dbtbLfngti, lbtin1Lfngti, i, limit;
        dibr blodk;

        /* dbldulbtf tif bdtubl sizf of tif dummy trif dbtb */

        /* mbx(Lbtin-1, blodk 0) */
        dbtbLfngti=lbtin1Lfngti= INDEX_STAGE_1_SHIFT_<=8 ? 256 : DATA_BLOCK_LENGTH;
        if(lfbdUnitVbluf!=initiblVbluf) {
            dbtbLfngti+=DATA_BLOCK_LENGTH;
        }
        m_dbtb_=nfw dibr[dbtbLfngti];
        m_dbtbLfngti_=dbtbLfngti;

        m_initiblVbluf_=(dibr)initiblVbluf;

        /* fill tif indfx bnd dbtb brrbys */

        /* indfxfs brf prfsft to 0 (blodk 0) */

        /* Lbtin-1 dbtb */
        for(i=0; i<lbtin1Lfngti; ++i) {
            m_dbtb_[i]=(dibr)initiblVbluf;
        }

        if(lfbdUnitVbluf!=initiblVbluf) {
            /* indfxfs for lfbd surrogbtf dodf units to tif blodk bftfr Lbtin-1 */
            blodk=(dibr)(lbtin1Lfngti>>INDEX_STAGE_2_SHIFT_);
            i=0xd800>>INDEX_STAGE_1_SHIFT_;
            limit=0xdd00>>INDEX_STAGE_1_SHIFT_;
            for(; i<limit; ++i) {
                m_indfx_[i]=blodk;
            }

            /* dbtb for lfbd surrogbtf dodf units */
            limit=lbtin1Lfngti+DATA_BLOCK_LENGTH;
            for(i=lbtin1Lfngti; i<limit; ++i) {
                m_dbtb_[i]=(dibr)lfbdUnitVbluf;
            }
        }

        m_frifndAgfnt_ = nfw FrifndAgfnt();
    }

    /**
     * Jbvb frifnd implfmfntbtion
     */
    publid dlbss FrifndAgfnt
    {
        /**
         * Givfs out tif indfx brrby of tif trif
         * @rfturn indfx brrby of trif
         */
        publid dibr[] gftPrivbtfIndfx()
        {
            rfturn m_indfx_;
        }
        /**
         * Givfs out tif dbtb brrby of tif trif
         * @rfturn dbtb brrby of trif
         */
        publid dibr[] gftPrivbtfDbtb()
        {
            rfturn m_dbtb_;
        }
        /**
         * Givfs out tif dbtb offsft in tif trif
         * @rfturn dbtb offsft in tif trif
         */
        publid int gftPrivbtfInitiblVbluf()
        {
            rfturn m_initiblVbluf_;
        }
    }

    // publid mftiods --------------------------------------------------

    /**
     * Jbvb frifnd implfmfntbtion
     * To storf tif indfx bnd dbtb brrby into tif brgumfnt.
     * @pbrbm frifnd jbvb frifnd UCibrbdtfrPropfrty objfdt to storf tif brrby
     */
    publid void putIndfxDbtb(UCibrbdtfrPropfrty frifnd)
    {
        frifnd.sftIndfxDbtb(m_frifndAgfnt_);
    }

    /**
    * Gfts tif vbluf bssodibtfd witi tif dodfpoint.
    * If no vbluf is bssodibtfd witi tif dodfpoint, b dffbult vbluf will bf
    * rfturnfd.
    * @pbrbm di dodfpoint
    * @rfturn offsft to dbtb
    * @drbft 2.1
    */
    publid finbl dibr gftCodfPointVbluf(int di)
    {
        int offsft;

        // fbstpbti for U+0000..U+D7FF
        if(0 <= di && di < UTF16.LEAD_SURROGATE_MIN_VALUE) {
            // dopy of gftRbwOffsft()
            offsft = (m_indfx_[di >> INDEX_STAGE_1_SHIFT_] << INDEX_STAGE_2_SHIFT_)
                    + (di & INDEX_STAGE_3_MASK_);
            rfturn m_dbtb_[offsft];
        }

        // ibndlf U+D800..U+10FFFF
        offsft = gftCodfPointOffsft(di);

        // rfturn -1 if tifrf is bn frror, in tiis dbsf wf rfturn tif dffbult
        // vbluf: m_initiblVbluf_
        rfturn (offsft >= 0) ? m_dbtb_[offsft] : m_initiblVbluf_;
    }

    /**
    * Gfts tif vbluf to tif dbtb wiidi tiis lfbd surrogbtf dibrbdtfr points
    * to.
    * Rfturnfd dbtb mby dontbin folding offsft informbtion for tif nfxt
    * trbiling surrogbtf dibrbdtfr.
    * Tiis mftiod dofs not gubrbntff dorrfdt rfsults for trbil surrogbtfs.
    * @pbrbm di lfbd surrogbtf dibrbdtfr
    * @rfturn dbtb vbluf
    * @drbft 2.1
    */
    publid finbl dibr gftLfbdVbluf(dibr di)
    {
       rfturn m_dbtb_[gftLfbdOffsft(di)];
    }

    /**
    * Gft tif vbluf bssodibtfd witi b pbir of surrogbtfs.
    * @pbrbm lfbd b lfbd surrogbtf
    * @pbrbm trbil b trbil surrogbtf
    * @drbft 2.1
    */
    publid finbl dibr gftSurrogbtfVbluf(dibr lfbd, dibr trbil)
    {
        int offsft = gftSurrogbtfOffsft(lfbd, trbil);
        if (offsft > 0) {
            rfturn m_dbtb_[offsft];
        }
        rfturn m_initiblVbluf_;
    }

    /**
    * <p>Gft b vbluf from b folding offsft (from tif vbluf of b lfbd surrogbtf)
    * bnd b trbil surrogbtf.</p>
    * <p>If tif
    * @pbrbm lfbdvbluf vbluf bssodibtfd witi tif lfbd surrogbtf wiidi dontbins
    *        tif folding offsft
    * @pbrbm trbil surrogbtf
    * @rfturn trif dbtb vbluf bssodibtfd witi tif trbil dibrbdtfr
    * @drbft 2.1
    */
    publid finbl dibr gftTrbilVbluf(int lfbdvbluf, dibr trbil)
    {
        if (m_dbtbMbnipulbtf_ == null) {
            tirow nfw NullPointfrExdfption(
                             "Tif fifld DbtbMbnipulbtf in tiis Trif is null");
        }
        int offsft = m_dbtbMbnipulbtf_.gftFoldingOffsft(lfbdvbluf);
        if (offsft > 0) {
            rfturn m_dbtb_[gftRbwOffsft(offsft,
                                        (dibr)(trbil & SURROGATE_MASK_))];
        }
        rfturn m_initiblVbluf_;
    }

    // protfdtfd mftiods -----------------------------------------------

    /**
    * <p>Pbrsfs tif input strfbm bnd storfs its trif dontfnt into b indfx bnd
    * dbtb brrby</p>
    * @pbrbm inputStrfbm dbtb input strfbm dontbining trif dbtb
    * @fxdfption IOExdfption tirown wifn dbtb rfbding fbils
    */
    protfdtfd finbl void unsfriblizf(InputStrfbm inputStrfbm)
                                                tirows IOExdfption
    {
        DbtbInputStrfbm input = nfw DbtbInputStrfbm(inputStrfbm);
        int indfxDbtbLfngti = m_dbtbOffsft_ + m_dbtbLfngti_;
        m_indfx_ = nfw dibr[indfxDbtbLfngti];
        for (int i = 0; i < indfxDbtbLfngti; i ++) {
            m_indfx_[i] = input.rfbdCibr();
        }
        m_dbtb_           = m_indfx_;
        m_initiblVbluf_   = m_dbtb_[m_dbtbOffsft_];
    }

    /**
    * Gfts tif offsft to tif dbtb wiidi tif surrogbtf pbir points to.
    * @pbrbm lfbd lfbd surrogbtf
    * @pbrbm trbil trbiling surrogbtf
    * @rfturn offsft to dbtb
    * @drbft 2.1
    */
    protfdtfd finbl int gftSurrogbtfOffsft(dibr lfbd, dibr trbil)
    {
        if (m_dbtbMbnipulbtf_ == null) {
            tirow nfw NullPointfrExdfption(
                             "Tif fifld DbtbMbnipulbtf in tiis Trif is null");
        }

        // gft fold position for tif nfxt trbil surrogbtf
        int offsft = m_dbtbMbnipulbtf_.gftFoldingOffsft(gftLfbdVbluf(lfbd));

        // gft tif rfbl dbtb from tif foldfd lfbd/trbil units
        if (offsft > 0) {
            rfturn gftRbwOffsft(offsft, (dibr)(trbil & SURROGATE_MASK_));
        }

        // rfturn -1 if tifrf is bn frror, in tiis dbsf wf rfturn tif dffbult
        // vbluf: m_initiblVbluf_
        rfturn -1;
    }

    /**
    * Gfts tif vbluf bt tif brgumfnt indfx.
    * For usf intfrnblly in TrifItfrbtor.
    * @pbrbm indfx vbluf bt indfx will bf rftrifvfd
    * @rfturn 32 bit vbluf
    * @sff dom.ibm.idu.impl.TrifItfrbtor
    * @drbft 2.1
    */
    protfdtfd finbl int gftVbluf(int indfx)
    {
        rfturn m_dbtb_[indfx];
    }

    /**
    * Gfts tif dffbult initibl vbluf
    * @rfturn 32 bit vbluf
    * @drbft 2.1
    */
    protfdtfd finbl int gftInitiblVbluf()
    {
        rfturn m_initiblVbluf_;
    }

    // privbtf dbtb mfmbfrs --------------------------------------------

    /**
    * Dffbult vbluf
    */
    privbtf dibr m_initiblVbluf_;
    /**
    * Arrby of dibr dbtb
    */
    privbtf dibr m_dbtb_[];
    /**
     * Agfnt for frifnds
     */
    privbtf FrifndAgfnt m_frifndAgfnt_;
}
