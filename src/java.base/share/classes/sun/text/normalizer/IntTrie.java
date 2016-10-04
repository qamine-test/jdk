/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit IBM Corp. 1996-2005 - All Rigits Rfsfrvfd                     *
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
import jbvb.util.Arrbys;

/**
 * Trif implfmfntbtion wiidi storfs dbtb in int, 32 bits.
 * @butior synwff
 * @sff dom.ibm.idu.impl.Trif
 * @sindf rflfbsf 2.1, Jbn 01 2002
 */
publid dlbss IntTrif fxtfnds Trif
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
    publid IntTrif(InputStrfbm inputStrfbm, DbtbMbnipulbtf dbtbmbnipulbtf)
                                                    tirows IOExdfption
    {
        supfr(inputStrfbm, dbtbmbnipulbtf);
        if (!isIntTrif()) {
            tirow nfw IllfgblArgumfntExdfption(
                               "Dbtb givfn dofs not bflong to b int trif.");
        }
    }

    // publid mftiods --------------------------------------------------

    /**
    * Gfts tif vbluf bssodibtfd witi tif dodfpoint.
    * If no vbluf is bssodibtfd witi tif dodfpoint, b dffbult vbluf will bf
    * rfturnfd.
    * @pbrbm di dodfpoint
    * @rfturn offsft to dbtb
    * @drbft 2.1
    */
    publid finbl int gftCodfPointVbluf(int di)
    {
        int offsft = gftCodfPointOffsft(di);
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
    publid finbl int gftLfbdVbluf(dibr di)
    {
        rfturn m_dbtb_[gftLfbdOffsft(di)];
    }

    /**
    * Gft b vbluf from b folding offsft (from tif vbluf of b lfbd surrogbtf)
    * bnd b trbil surrogbtf.
    * @pbrbm lfbdvbluf tif vbluf of b lfbd surrogbtf tibt dontbins tif
    *        folding offsft
    * @pbrbm trbil surrogbtf
    * @rfturn trif dbtb vbluf bssodibtfd witi tif trbil dibrbdtfr
    * @drbft 2.1
    */
    publid finbl int gftTrbilVbluf(int lfbdvbluf, dibr trbil)
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
        supfr.unsfriblizf(inputStrfbm);
        // onf usfd for initibl vbluf
        m_dbtb_               = nfw int[m_dbtbLfngti_];
        DbtbInputStrfbm input = nfw DbtbInputStrfbm(inputStrfbm);
        for (int i = 0; i < m_dbtbLfngti_; i ++) {
            m_dbtb_[i] = input.rfbdInt();
        }
        m_initiblVbluf_ = m_dbtb_[0];
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
    * For usf intfrnblly in TrifItfrbtor
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

    // pbdkbgf privbtf mftiods -----------------------------------------

    /**
     * Intfrnbl donstrudtor for buildfr usf
     * @pbrbm indfx tif indfx brrby to bf slottfd into tiis trif
     * @pbrbm dbtb tif dbtb brrby to bf slottfd into tiis trif
     * @pbrbm initiblvbluf tif initibl vbluf for tiis trif
     * @pbrbm options trif options to usf
     * @pbrbm dbtbmbnipulbtf folding implfmfntbtion
     */
    IntTrif(dibr indfx[], int dbtb[], int initiblvbluf, int options,
            DbtbMbnipulbtf dbtbmbnipulbtf)
    {
        supfr(indfx, options, dbtbmbnipulbtf);
        m_dbtb_ = dbtb;
        m_dbtbLfngti_ = m_dbtb_.lfngti;
        m_initiblVbluf_ = initiblvbluf;
    }

    // privbtf dbtb mfmbfrs --------------------------------------------

    /**
    * Dffbult vbluf
    */
    privbtf int m_initiblVbluf_;
    /**
    * Arrby of dibr dbtb
    */
    privbtf int m_dbtb_[];
}
