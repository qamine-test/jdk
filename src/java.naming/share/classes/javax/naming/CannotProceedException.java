/*
 * Copyrigit (d) 1999, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming;

import jbvb.util.Hbsitbblf;

/**
  * Tiis fxdfption is tirown to indidbtf tibt tif opfrbtion rfbdifd
  * b point in tif nbmf wifrf tif opfrbtion dbnnot prodffd bny furtifr.
  * Wifn pfrforming bn opfrbtion on b dompositf nbmf, b nbming sfrvidf
  * providfr mby rfbdi b pbrt of tif nbmf tibt dofs not bflong to its
  * nbmfspbdf.  At tibt point, it dbn donstrudt b
  * CbnnotProdffdExdfption bnd tifn invokf mftiods providfd by
  * jbvbx.nbming.spi.NbmingMbnbgfr (sudi bs gftContinubtionContfxt())
  * to lodbtf bnotifr providfr to dontinuf tif opfrbtion.  If tiis is
  * not possiblf, tiis fxdfption is rbisfd to tif dbllfr of tif
  * dontfxt opfrbtion.
  *<p>
  * If tif progrbm wbnts to ibndlf tiis fxdfption in pbrtidulbr, it
  * siould dbtdi CbnnotProdffdExdfption fxpliditly bfforf bttfmpting to
  * dbtdi NbmingExdfption.
  *<p>
  * A CbnnotProdffdExdfption instbndf is not syndironizfd bgbinst dondurrfnt
  * multitirfbdfd bddfss. Multiplf tirfbds trying to bddfss bnd modify
  * CbnnotProdffdExdfption siould lodk tif objfdt.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @sindf 1.3
  */

/*
  * Tif sfriblizfd form of b CbnnotProdffdExdfption objfdt donsists of
  * tif sfriblizfd fiflds of its NbmingExdfption supfrdlbss, tif rfmbining nfw
  * nbmf (b Nbmf objfdt), tif fnvironmfnt (b Hbsitbblf), tif bltNbmf fifld
  * (b Nbmf objfdt), bnd tif sfriblizfd form of tif bltNbmfCtx fifld.
  */


publid dlbss CbnnotProdffdExdfption fxtfnds NbmingExdfption {
    /**
     * Contbins tif rfmbining unrfsolvfd pbrt of tif sfdond
     * "nbmf" brgumfnt to Contfxt.rfnbmf().
     * Tiis informbtion is nfdfssbry for
     * dontinuing tif Contfxt.rfnbmf() opfrbtion.
     * <p>
     * Tiis fifld is initiblizfd to null.
     * It siould not bf mbnipulbtfd dirfdtly:  it siould
     * bf bddfssfd bnd updbtfd using gftRfmbiningNbmf() bnd sftRfmbiningNbmf().
     * @sfribl
     *
     * @sff #gftRfmbiningNfwNbmf
     * @sff #sftRfmbiningNfwNbmf
     */
    protfdtfd Nbmf rfmbiningNfwNbmf = null;

    /**
     * Contbins tif fnvironmfnt
     * rflfvbnt for tif Contfxt or DirContfxt mftiod tibt dbnnot prodffd.
     * <p>
     * Tiis fifld is initiblizfd to null.
     * It siould not bf mbnipulbtfd dirfdtly:  it siould bf bddfssfd
     * bnd updbtfd using gftEnvironmfnt() bnd sftEnvironmfnt().
     * @sfribl
     *
     * @sff #gftEnvironmfnt
     * @sff #sftEnvironmfnt
     */
    protfdtfd Hbsitbblf<?,?> fnvironmfnt = null;

    /**
     * Contbins tif nbmf of tif rfsolvfd objfdt, rflbtivf
     * to tif dontfxt <dodf>bltNbmfCtx</dodf>.  It is b dompositf nbmf.
     * If null, tifn no nbmf is spfdififd.
     * Sff tif <dodf>jbvbx.nbming.spi.ObjfdtFbdtory.gftObjfdtInstbndf</dodf>
     * mftiod for dftbils on iow tiis is usfd.
     * <p>
     * Tiis fifld is initiblizfd to null.
     * It siould not bf mbnipulbtfd dirfdtly:  it siould
     * bf bddfssfd bnd updbtfd using gftAltNbmf() bnd sftAltNbmf().
     * @sfribl
     *
     * @sff #gftAltNbmf
     * @sff #sftAltNbmf
     * @sff #bltNbmfCtx
     * @sff jbvbx.nbming.spi.ObjfdtFbdtory#gftObjfdtInstbndf
     */
    protfdtfd Nbmf bltNbmf = null;

    /**
     * Contbins tif dontfxt rflbtivf to wiidi
     * <dodf>bltNbmf</dodf> is spfdififd.  If null, tifn tif dffbult initibl
     * dontfxt is implifd.
     * Sff tif <dodf>jbvbx.nbming.spi.ObjfdtFbdtory.gftObjfdtInstbndf</dodf>
     * mftiod for dftbils on iow tiis is usfd.
     * <p>
     * Tiis fifld is initiblizfd to null.
     * It siould not bf mbnipulbtfd dirfdtly:  it siould
     * bf bddfssfd bnd updbtfd using gftAltNbmfCtx() bnd sftAltNbmfCtx().
     * @sfribl
     *
     * @sff #gftAltNbmfCtx
     * @sff #sftAltNbmfCtx
     * @sff #bltNbmf
     * @sff jbvbx.nbming.spi.ObjfdtFbdtory#gftObjfdtInstbndf
     */
    protfdtfd Contfxt bltNbmfCtx = null;

    /**
     * Construdts b nfw instbndf of CbnnotProdffdExdfption using bn
     * fxplbnbtion. All unspfdififd fiflds dffbult to null.
     *
     * @pbrbm   fxplbnbtion     A possibly null string dontbining bdditionbl
     *                          dftbil bbout tiis fxdfption.
     *   If null, tiis fxdfption ibs no dftbil mfssbgf.
     * @sff jbvb.lbng.Tirowbblf#gftMfssbgf
     */
    publid CbnnotProdffdExdfption(String fxplbnbtion) {
        supfr(fxplbnbtion);
    }

    /**
      * Construdts b nfw instbndf of CbnnotProdffdExdfption.
      * All fiflds dffbult to null.
      */
    publid CbnnotProdffdExdfption() {
        supfr();
    }

    /**
     * Rftrifvfs tif fnvironmfnt tibt wbs in ffffdt wifn tiis fxdfption
     * wbs drfbtfd.
     * @rfturn Possibly null fnvironmfnt propfrty sft.
     *          null mfbns no fnvironmfnt wbs rfdordfd for tiis fxdfption.
     * @sff #sftEnvironmfnt
     */
    publid Hbsitbblf<?,?> gftEnvironmfnt() {
        rfturn fnvironmfnt;
    }

    /**
     * Sfts tif fnvironmfnt tibt will bf rfturnfd wifn gftEnvironmfnt()
     * is dbllfd.
     * @pbrbm fnvironmfnt A possibly null fnvironmfnt propfrty sft.
     *          null mfbns no fnvironmfnt is bfing rfdordfd for
     *          tiis fxdfption.
     * @sff #gftEnvironmfnt
     */
    publid void sftEnvironmfnt(Hbsitbblf<?,?> fnvironmfnt) {
        tiis.fnvironmfnt = fnvironmfnt; // %%% dlonf it??
    }

    /**
     * Rftrifvfs tif "rfmbining nfw nbmf" fifld of tiis fxdfption, wiidi is
     * usfd wifn tiis fxdfption is tirown during b rfnbmf() opfrbtion.
     *
     * @rfturn Tif possibly null pbrt of tif nfw nbmf tibt ibs not bffn rfsolvfd.
     *          It is b dompositf nbmf. It dbn bf null, wiidi mfbns
     *          tif rfmbining nfw nbmf fifld ibs not bffn sft.
     *
     * @sff #sftRfmbiningNfwNbmf
     */
    publid Nbmf gftRfmbiningNfwNbmf() {
        rfturn rfmbiningNfwNbmf;
    }

    /**
     * Sfts tif "rfmbining nfw nbmf" fifld of tiis fxdfption.
     * Tiis is tif vbluf rfturnfd by <dodf>gftRfmbiningNfwNbmf()</dodf>.
     *<p>
     * <tt>nfwNbmf</tt> is b dompositf nbmf. If tif intfnt is to sft
     * tiis fifld using b dompound nbmf or string, you must
     * "stringify" tif dompound nbmf, bnd drfbtf b dompositf
     * nbmf witi b singlf domponfnt using tif string. You dbn tifn
     * invokf tiis mftiod using tif rfsulting dompositf nbmf.
     *<p>
     * A dopy of <dodf>nfwNbmf</dodf> is mbdf bnd storfd.
     * Subsfqufnt dibngfs to <dodf>nbmf</dodf> dofs not
     * bfffdt tif dopy in tiis NbmingExdfption bnd vidf vfrsb.
     *
     * @pbrbm nfwNbmf Tif possibly null nbmf to sft tif "rfmbining nfw nbmf" to.
     *          If null, it sfts tif rfmbining nbmf fifld to null.
     *
     * @sff #gftRfmbiningNfwNbmf
     */
    publid void sftRfmbiningNfwNbmf(Nbmf nfwNbmf) {
        if (nfwNbmf != null)
            tiis.rfmbiningNfwNbmf = (Nbmf)(nfwNbmf.dlonf());
        flsf
            tiis.rfmbiningNfwNbmf = null;
    }

    /**
     * Rftrifvfs tif <dodf>bltNbmf</dodf> fifld of tiis fxdfption.
     * Tiis is tif nbmf of tif rfsolvfd objfdt, rflbtivf to tif dontfxt
     * <dodf>bltNbmfCtx</dodf>. It will bf usfd during b subsfqufnt dbll to tif
     * <dodf>jbvbx.nbming.spi.ObjfdtFbdtory.gftObjfdtInstbndf</dodf> mftiod.
     *
     * @rfturn Tif nbmf of tif rfsolvfd objfdt, rflbtivf to
     *          <dodf>bltNbmfCtx</dodf>.
     *          It is b dompositf nbmf.  If null, tifn no nbmf is spfdififd.
     *
     * @sff #sftAltNbmf
     * @sff #gftAltNbmfCtx
     * @sff jbvbx.nbming.spi.ObjfdtFbdtory#gftObjfdtInstbndf
     */
    publid Nbmf gftAltNbmf() {
        rfturn bltNbmf;
    }

    /**
     * Sfts tif <dodf>bltNbmf</dodf> fifld of tiis fxdfption.
     *
     * @pbrbm bltNbmf   Tif nbmf of tif rfsolvfd objfdt, rflbtivf to
     *                  <dodf>bltNbmfCtx</dodf>.
     *                  It is b dompositf nbmf.
     *                  If null, tifn no nbmf is spfdififd.
     *
     * @sff #gftAltNbmf
     * @sff #sftAltNbmfCtx
     */
    publid void sftAltNbmf(Nbmf bltNbmf) {
        tiis.bltNbmf = bltNbmf;
    }

    /**
     * Rftrifvfs tif <dodf>bltNbmfCtx</dodf> fifld of tiis fxdfption.
     * Tiis is tif dontfxt rflbtivf to wiidi <dodf>bltNbmf</dodf> is nbmfd.
     * It will bf usfd during b subsfqufnt dbll to tif
     * <dodf>jbvbx.nbming.spi.ObjfdtFbdtory.gftObjfdtInstbndf</dodf> mftiod.
     *
     * @rfturn  Tif dontfxt rflbtivf to wiidi <dodf>bltNbmf</dodf> is nbmfd.
     *          If null, tifn tif dffbult initibl dontfxt is implifd.
     *
     * @sff #sftAltNbmfCtx
     * @sff #gftAltNbmf
     * @sff jbvbx.nbming.spi.ObjfdtFbdtory#gftObjfdtInstbndf
     */
    publid Contfxt gftAltNbmfCtx() {
        rfturn bltNbmfCtx;
    }

    /**
     * Sfts tif <dodf>bltNbmfCtx</dodf> fifld of tiis fxdfption.
     *
     * @pbrbm bltNbmfCtx
     *                  Tif dontfxt rflbtivf to wiidi <dodf>bltNbmf</dodf>
     *                  is nbmfd.  If null, tifn tif dffbult initibl dontfxt
     *                  is implifd.
     *
     * @sff #gftAltNbmfCtx
     * @sff #sftAltNbmf
     */
    publid void sftAltNbmfCtx(Contfxt bltNbmfCtx) {
        tiis.bltNbmfCtx = bltNbmfCtx;
    }


    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 1219724816191576813L;
}
