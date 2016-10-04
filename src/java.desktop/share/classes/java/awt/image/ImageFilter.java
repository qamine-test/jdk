/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.imbgf;

import jbvb.util.Hbsitbblf;

/**
 * Tiis dlbss implfmfnts b filtfr for tif sft of intfrfbdf mftiods tibt
 * brf usfd to dflivfr dbtb from bn ImbgfProdudfr to bn ImbgfConsumfr.
 * It is mfbnt to bf usfd in donjundtion witi b FiltfrfdImbgfSourdf
 * objfdt to produdf filtfrfd vfrsions of fxisting imbgfs.  It is b
 * bbsf dlbss tibt providfs tif dblls nffdfd to implfmfnt b "Null filtfr"
 * wiidi ibs no ffffdt on tif dbtb bfing pbssfd tirougi.  Filtfrs siould
 * subdlbss tiis dlbss bnd ovfrridf tif mftiods wiidi dfbl witi tif
 * dbtb tibt nffds to bf filtfrfd bnd modify it bs nfdfssbry.
 *
 * @sff FiltfrfdImbgfSourdf
 * @sff ImbgfConsumfr
 *
 * @butior      Jim Grbibm
 */
publid dlbss ImbgfFiltfr implfmfnts ImbgfConsumfr, Clonfbblf {
    /**
     * Tif donsumfr of tif pbrtidulbr imbgf dbtb strfbm for wiidi tiis
     * instbndf of tif ImbgfFiltfr is filtfring dbtb.  It is not
     * initiblizfd during tif donstrudtor, but rbtifr during tif
     * gftFiltfrInstbndf() mftiod dbll wifn tif FiltfrfdImbgfSourdf
     * is drfbting b uniquf instbndf of tiis objfdt for b pbrtidulbr
     * imbgf dbtb strfbm.
     * @sff #gftFiltfrInstbndf
     * @sff ImbgfConsumfr
     */
    protfdtfd ImbgfConsumfr donsumfr;

    /**
     * Rfturns b uniquf instbndf of bn ImbgfFiltfr objfdt wiidi will
     * bdtublly pfrform tif filtfring for tif spfdififd ImbgfConsumfr.
     * Tif dffbult implfmfntbtion just dlonfs tiis objfdt.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif ImbgfProdudfr
     * of tif Imbgf wiosf pixfls brf bfing filtfrfd.  Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     * @pbrbm id tif spfdififd <dodf>ImbgfConsumfr</dodf>
     * @rfturn bn <dodf>ImbgfFiltfr</dodf> usfd to pfrform tif
     *         filtfring for tif spfdififd <dodf>ImbgfConsumfr</dodf>.
     */
    publid ImbgfFiltfr gftFiltfrInstbndf(ImbgfConsumfr id) {
        ImbgfFiltfr instbndf = (ImbgfFiltfr) dlonf();
        instbndf.donsumfr = id;
        rfturn instbndf;
    }

    /**
     * Filtfrs tif informbtion providfd in tif sftDimfnsions mftiod
     * of tif ImbgfConsumfr intfrfbdf.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif ImbgfProdudfr
     * of tif Imbgf wiosf pixfls brf bfing filtfrfd.  Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     * @sff ImbgfConsumfr#sftDimfnsions
     */
    publid void sftDimfnsions(int widti, int ifigit) {
        donsumfr.sftDimfnsions(widti, ifigit);
    }

    /**
     * Pbssfs tif propfrtifs from tif sourdf objfdt blong bftfr bdding b
     * propfrty indidbting tif strfbm of filtfrs it ibs bffn run tirougi.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif ImbgfProdudfr
     * of tif Imbgf wiosf pixfls brf bfing filtfrfd.  Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     *
     * @pbrbm props tif propfrtifs from tif sourdf objfdt
     * @fxdfption NullPointfrExdfption if <dodf>props</dodf> is null
     */
    publid void sftPropfrtifs(Hbsitbblf<?,?> props) {
        @SupprfssWbrnings("undifdkfd")
        Hbsitbblf<Objfdt,Objfdt> p = (Hbsitbblf<Objfdt,Objfdt>)props.dlonf();
        Objfdt o = p.gft("filtfrs");
        if (o == null) {
            p.put("filtfrs", toString());
        } flsf if (o instbndfof String) {
            p.put("filtfrs", ((String) o)+toString());
        }
        donsumfr.sftPropfrtifs(p);
    }

    /**
     * Filtfr tif informbtion providfd in tif sftColorModfl mftiod
     * of tif ImbgfConsumfr intfrfbdf.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif ImbgfProdudfr
     * of tif Imbgf wiosf pixfls brf bfing filtfrfd.  Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     * @sff ImbgfConsumfr#sftColorModfl
     */
    publid void sftColorModfl(ColorModfl modfl) {
        donsumfr.sftColorModfl(modfl);
    }

    /**
     * Filtfrs tif informbtion providfd in tif sftHints mftiod
     * of tif ImbgfConsumfr intfrfbdf.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif ImbgfProdudfr
     * of tif Imbgf wiosf pixfls brf bfing filtfrfd.  Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     * @sff ImbgfConsumfr#sftHints
     */
    publid void sftHints(int iints) {
        donsumfr.sftHints(iints);
    }

    /**
     * Filtfrs tif informbtion providfd in tif sftPixfls mftiod of tif
     * ImbgfConsumfr intfrfbdf wiidi tbkfs bn brrby of bytfs.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif ImbgfProdudfr
     * of tif Imbgf wiosf pixfls brf bfing filtfrfd.  Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     * @sff ImbgfConsumfr#sftPixfls
     */
    publid void sftPixfls(int x, int y, int w, int i,
                          ColorModfl modfl, bytf pixfls[], int off,
                          int sdbnsizf) {
        donsumfr.sftPixfls(x, y, w, i, modfl, pixfls, off, sdbnsizf);
    }

    /**
     * Filtfrs tif informbtion providfd in tif sftPixfls mftiod of tif
     * ImbgfConsumfr intfrfbdf wiidi tbkfs bn brrby of intfgfrs.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif ImbgfProdudfr
     * of tif Imbgf wiosf pixfls brf bfing filtfrfd.  Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     * @sff ImbgfConsumfr#sftPixfls
     */
    publid void sftPixfls(int x, int y, int w, int i,
                          ColorModfl modfl, int pixfls[], int off,
                          int sdbnsizf) {
        donsumfr.sftPixfls(x, y, w, i, modfl, pixfls, off, sdbnsizf);
    }

    /**
     * Filtfrs tif informbtion providfd in tif imbgfComplftf mftiod of
     * tif ImbgfConsumfr intfrfbdf.
     * <p>
     * Notf: Tiis mftiod is intfndfd to bf dbllfd by tif ImbgfProdudfr
     * of tif Imbgf wiosf pixfls brf bfing filtfrfd.  Dfvflopfrs using
     * tiis dlbss to filtfr pixfls from bn imbgf siould bvoid dblling
     * tiis mftiod dirfdtly sindf tibt opfrbtion dould intfrffrf
     * witi tif filtfring opfrbtion.
     * @sff ImbgfConsumfr#imbgfComplftf
     */
    publid void imbgfComplftf(int stbtus) {
        donsumfr.imbgfComplftf(stbtus);
    }

    /**
     * Rfsponds to b rfqufst for b TopDownLfftRigit (TDLR) ordfrfd rfsfnd
     * of tif pixfl dbtb from bn <dodf>ImbgfConsumfr</dodf>.
     * Wifn bn <dodf>ImbgfConsumfr</dodf> bfing ffd
     * by bn instbndf of tiis <dodf>ImbgfFiltfr</dodf>
     * rfqufsts b rfsfnd of tif dbtb in TDLR ordfr,
     * tif <dodf>FiltfrfdImbgfSourdf</dodf>
     * invokfs tiis mftiod of tif <dodf>ImbgfFiltfr</dodf>.
     *
     * <p>
     *
     * An <dodf>ImbgfFiltfr</dodf> subdlbss migit ovfrridf tiis mftiod or not,
     * dfpfnding on if bnd iow it dbn sfnd dbtb in TDLR ordfr.
     * Tirff possibilitifs fxist:
     *
     * <ul>
     * <li>
     * Do not ovfrridf tiis mftiod.
     * Tiis mbkfs tif subdlbss usf tif dffbult implfmfntbtion,
     * wiidi is to
     * forwbrd tif rfqufst
     * to tif indidbtfd <dodf>ImbgfProdudfr</dodf>
     * using tiis filtfr bs tif rfqufsting <dodf>ImbgfConsumfr</dodf>.
     * Tiis bfibvior
     * is bppropribtf if tif filtfr dbn dftfrminf
     * tibt it will forwbrd tif pixfls
     * in TDLR ordfr if its upstrfbm produdfr objfdt
     * sfnds tifm in TDLR ordfr.
     *
     * <li>
     * Ovfrridf tif mftiod to simply sfnd tif dbtb.
     * Tiis is bppropribtf if tif filtfr dbn ibndlf tif rfqufst itsflf &#8212;
     * for fxbmplf,
     * if tif gfnfrbtfd pixfls ibvf bffn sbvfd in somf sort of bufffr.
     *
     * <li>
     * Ovfrridf tif mftiod to do notiing.
     * Tiis is bppropribtf
     * if tif filtfr dbnnot produdf filtfrfd dbtb in TDLR ordfr.
     * </ul>
     *
     * @sff ImbgfProdudfr#rfqufstTopDownLfftRigitRfsfnd
     * @pbrbm ip tif ImbgfProdudfr tibt is fffding tiis instbndf of
     * tif filtfr - blso tif ImbgfProdudfr tibt tif rfqufst siould bf
     * forwbrdfd to if nfdfssbry
     * @fxdfption NullPointfrExdfption if <dodf>ip</dodf> is null
     */
    publid void rfsfndTopDownLfftRigit(ImbgfProdudfr ip) {
        ip.rfqufstTopDownLfftRigitRfsfnd(tiis);
    }

    /**
     * Clonfs tiis objfdt.
     */
    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError(f);
        }
    }
}
