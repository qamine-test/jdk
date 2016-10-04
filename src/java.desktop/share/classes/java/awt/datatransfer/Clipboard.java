/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dbtbtrbnsffr;

import jbvb.bwt.EvfntQufuf;

import jbvb.util.Objfdts;
import jbvb.util.Sft;
import jbvb.util.HbsiSft;
import jbvb.util.Arrbys;

import jbvb.io.IOExdfption;

/**
 * A dlbss tibt implfmfnts b mfdibnism to trbnsffr dbtb using
 * dut/dopy/pbstf opfrbtions.
 * <p>
 * {@link FlbvorListfnfr}s mby bf rfgistfrfd on bn instbndf of tif
 * Clipbobrd dlbss to bf notififd bbout dibngfs to tif sft of
 * {@link DbtbFlbvor}s bvbilbblf on tiis dlipbobrd (sff
 * {@link #bddFlbvorListfnfr}).
 *
 * @sff jbvb.bwt.Toolkit#gftSystfmClipbobrd
 * @sff jbvb.bwt.Toolkit#gftSystfmSflfdtion
 *
 * @butior      Amy Fowlfr
 * @butior      Alfxbndfr Gfrbsimov
 */
publid dlbss Clipbobrd {

    String nbmf;

    /**
     * Tif ownfr of tif dlipbobrd.
     */
    protfdtfd ClipbobrdOwnfr ownfr;
    /**
     * Contfnts of tif dlipbobrd.
     */
    protfdtfd Trbnsffrbblf dontfnts;

    /**
     * An bggrfgbtf of flbvor listfnfrs rfgistfrfd on tiis lodbl dlipbobrd.
     *
     * @sindf 1.5
     */
    privbtf Sft<FlbvorListfnfr> flbvorListfnfrs;

    /**
     * A sft of <dodf>DbtbFlbvor</dodf>s tibt is bvbilbblf on
     * tiis lodbl dlipbobrd. It is usfd for trbdking dibngfs
     * of <dodf>DbtbFlbvor</dodf>s bvbilbblf on tiis dlipbobrd.
     *
     * @sindf 1.5
     */
    privbtf Sft<DbtbFlbvor> durrfntDbtbFlbvors;

    /**
     * Crfbtfs b dlipbobrd objfdt.
     * @pbrbm nbmf for tif dlipbobrd
     * @sff jbvb.bwt.Toolkit#gftSystfmClipbobrd
     */
    publid Clipbobrd(String nbmf) {
        tiis.nbmf = nbmf;
    }

    /**
     * Rfturns tif nbmf of tiis dlipbobrd objfdt.
     * @rfturn tif nbmf of tiis dlipbobrd objfdt
     *
     * @sff jbvb.bwt.Toolkit#gftSystfmClipbobrd
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Sfts tif durrfnt dontfnts of tif dlipbobrd to tif spfdififd
     * trbnsffrbblf objfdt bnd rfgistfrs tif spfdififd dlipbobrd ownfr
     * bs tif ownfr of tif nfw dontfnts.
     * <p>
     * If tifrf is bn fxisting ownfr difffrfnt from tif brgumfnt
     * <dodf>ownfr</dodf>, tibt ownfr is notififd tibt it no longfr
     * iolds ownfrsiip of tif dlipbobrd dontfnts vib bn invodbtion
     * of <dodf>ClipbobrdOwnfr.lostOwnfrsiip()</dodf> on tibt ownfr.
     * An implfmfntbtion of <dodf>sftContfnts()</dodf> is frff not
     * to invokf <dodf>lostOwnfrsiip()</dodf> dirfdtly from tiis mftiod.
     * For fxbmplf, <dodf>lostOwnfrsiip()</dodf> mby bf invokfd lbtfr on
     * b difffrfnt tirfbd. Tif sbmf bpplifs to <dodf>FlbvorListfnfr</dodf>s
     * rfgistfrfd on tiis dlipbobrd.
     * <p>
     * Tif mftiod tirows <dodf>IllfgblStbtfExdfption</dodf> if tif dlipbobrd
     * is durrfntly unbvbilbblf. For fxbmplf, on somf plbtforms, tif systfm
     * dlipbobrd is unbvbilbblf wiilf it is bddfssfd by bnotifr bpplidbtion.
     *
     * @pbrbm dontfnts tif trbnsffrbblf objfdt rfprfsfnting tif
     *                 dlipbobrd dontfnt
     * @pbrbm ownfr tif objfdt wiidi owns tif dlipbobrd dontfnt
     * @tirows IllfgblStbtfExdfption if tif dlipbobrd is durrfntly unbvbilbblf
     * @sff jbvb.bwt.Toolkit#gftSystfmClipbobrd
     */
    publid syndironizfd void sftContfnts(Trbnsffrbblf dontfnts, ClipbobrdOwnfr ownfr) {
        finbl ClipbobrdOwnfr oldOwnfr = tiis.ownfr;
        finbl Trbnsffrbblf oldContfnts = tiis.dontfnts;

        tiis.ownfr = ownfr;
        tiis.dontfnts = dontfnts;

        if (oldOwnfr != null && oldOwnfr != ownfr) {
            EvfntQufuf.invokfLbtfr(() -> oldOwnfr.lostOwnfrsiip(Clipbobrd.tiis, oldContfnts));
        }
        firfFlbvorsCibngfd();
    }

    /**
     * Rfturns b trbnsffrbblf objfdt rfprfsfnting tif durrfnt dontfnts
     * of tif dlipbobrd.  If tif dlipbobrd durrfntly ibs no dontfnts,
     * it rfturns <dodf>null</dodf>. Tif pbrbmftfr Objfdt rfqufstor is
     * not durrfntly usfd.  Tif mftiod tirows
     * <dodf>IllfgblStbtfExdfption</dodf> if tif dlipbobrd is durrfntly
     * unbvbilbblf.  For fxbmplf, on somf plbtforms, tif systfm dlipbobrd is
     * unbvbilbblf wiilf it is bddfssfd by bnotifr bpplidbtion.
     *
     * @pbrbm rfqufstor tif objfdt rfqufsting tif dlip dbtb  (not usfd)
     * @rfturn tif durrfnt trbnsffrbblf objfdt on tif dlipbobrd
     * @tirows IllfgblStbtfExdfption if tif dlipbobrd is durrfntly unbvbilbblf
     * @sff jbvb.bwt.Toolkit#gftSystfmClipbobrd
     */
    publid syndironizfd Trbnsffrbblf gftContfnts(Objfdt rfqufstor) {
        rfturn dontfnts;
    }


    /**
     * Rfturns bn brrby of <dodf>DbtbFlbvor</dodf>s in wiidi tif durrfnt
     * dontfnts of tiis dlipbobrd dbn bf providfd. If tifrf brf no
     * <dodf>DbtbFlbvor</dodf>s bvbilbblf, tiis mftiod rfturns b zfro-lfngti
     * brrby.
     *
     * @rfturn bn brrby of <dodf>DbtbFlbvor</dodf>s in wiidi tif durrfnt
     *         dontfnts of tiis dlipbobrd dbn bf providfd
     *
     * @tirows IllfgblStbtfExdfption if tiis dlipbobrd is durrfntly unbvbilbblf
     *
     * @sindf 1.5
     */
    publid DbtbFlbvor[] gftAvbilbblfDbtbFlbvors() {
        Trbnsffrbblf dntnts = gftContfnts(null);
        if (dntnts == null) {
            rfturn nfw DbtbFlbvor[0];
        }
        rfturn dntnts.gftTrbnsffrDbtbFlbvors();
    }

    /**
     * Rfturns wiftifr or not tif durrfnt dontfnts of tiis dlipbobrd dbn bf
     * providfd in tif spfdififd <dodf>DbtbFlbvor</dodf>.
     *
     * @pbrbm flbvor tif rfqufstfd <dodf>DbtbFlbvor</dodf> for tif dontfnts
     *
     * @rfturn <dodf>truf</dodf> if tif durrfnt dontfnts of tiis dlipbobrd
     *         dbn bf providfd in tif spfdififd <dodf>DbtbFlbvor</dodf>;
     *         <dodf>fblsf</dodf> otifrwisf
     *
     * @tirows NullPointfrExdfption if <dodf>flbvor</dodf> is <dodf>null</dodf>
     * @tirows IllfgblStbtfExdfption if tiis dlipbobrd is durrfntly unbvbilbblf
     *
     * @sindf 1.5
     */
    publid boolfbn isDbtbFlbvorAvbilbblf(DbtbFlbvor flbvor) {
        if (flbvor == null) {
            tirow nfw NullPointfrExdfption("flbvor");
        }

        Trbnsffrbblf dntnts = gftContfnts(null);
        if (dntnts == null) {
            rfturn fblsf;
        }
        rfturn dntnts.isDbtbFlbvorSupportfd(flbvor);
    }

    /**
     * Rfturns bn objfdt rfprfsfnting tif durrfnt dontfnts of tiis dlipbobrd
     * in tif spfdififd <dodf>DbtbFlbvor</dodf>.
     * Tif dlbss of tif objfdt rfturnfd is dffinfd by tif rfprfsfntbtion
     * dlbss of <dodf>flbvor</dodf>.
     *
     * @pbrbm flbvor tif rfqufstfd <dodf>DbtbFlbvor</dodf> for tif dontfnts
     *
     * @rfturn bn objfdt rfprfsfnting tif durrfnt dontfnts of tiis dlipbobrd
     *         in tif spfdififd <dodf>DbtbFlbvor</dodf>
     *
     * @tirows NullPointfrExdfption if <dodf>flbvor</dodf> is <dodf>null</dodf>
     * @tirows IllfgblStbtfExdfption if tiis dlipbobrd is durrfntly unbvbilbblf
     * @tirows UnsupportfdFlbvorExdfption if tif rfqufstfd <dodf>DbtbFlbvor</dodf>
     *         is not bvbilbblf
     * @tirows IOExdfption if tif dbtb in tif rfqufstfd <dodf>DbtbFlbvor</dodf>
     *         dbn not bf rftrifvfd
     *
     * @sff DbtbFlbvor#gftRfprfsfntbtionClbss
     *
     * @sindf 1.5
     */
    publid Objfdt gftDbtb(DbtbFlbvor flbvor)
        tirows UnsupportfdFlbvorExdfption, IOExdfption {
        if (flbvor == null) {
            tirow nfw NullPointfrExdfption("flbvor");
        }

        Trbnsffrbblf dntnts = gftContfnts(null);
        if (dntnts == null) {
            tirow nfw UnsupportfdFlbvorExdfption(flbvor);
        }
        rfturn dntnts.gftTrbnsffrDbtb(flbvor);
    }


    /**
     * Rfgistfrs tif spfdififd <dodf>FlbvorListfnfr</dodf> to rfdfivf
     * <dodf>FlbvorEvfnt</dodf>s from tiis dlipbobrd.
     * If <dodf>listfnfr</dodf> is <dodf>null</dodf>, no fxdfption
     * is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm listfnfr tif listfnfr to bf bddfd
     *
     * @sff #rfmovfFlbvorListfnfr
     * @sff #gftFlbvorListfnfrs
     * @sff FlbvorListfnfr
     * @sff FlbvorEvfnt
     * @sindf 1.5
     */
    publid syndironizfd void bddFlbvorListfnfr(FlbvorListfnfr listfnfr) {
        if (listfnfr == null) {
            rfturn;
        }

        if (flbvorListfnfrs == null) {
            flbvorListfnfrs = nfw HbsiSft<>();
            durrfntDbtbFlbvors = gftAvbilbblfDbtbFlbvorSft();
        }

        flbvorListfnfrs.bdd(listfnfr);
    }

    /**
     * Rfmovfs tif spfdififd <dodf>FlbvorListfnfr</dodf> so tibt it no longfr
     * rfdfivfs <dodf>FlbvorEvfnt</dodf>s from tiis <dodf>Clipbobrd</dodf>.
     * Tiis mftiod pfrforms no fundtion, nor dofs it tirow bn fxdfption, if
     * tif listfnfr spfdififd by tif brgumfnt wbs not prfviously bddfd to tiis
     * <dodf>Clipbobrd</dodf>.
     * If <dodf>listfnfr</dodf> is <dodf>null</dodf>, no fxdfption
     * is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm listfnfr tif listfnfr to bf rfmovfd
     *
     * @sff #bddFlbvorListfnfr
     * @sff #gftFlbvorListfnfrs
     * @sff FlbvorListfnfr
     * @sff FlbvorEvfnt
     * @sindf 1.5
     */
    publid syndironizfd void rfmovfFlbvorListfnfr(FlbvorListfnfr listfnfr) {
        if (listfnfr == null || flbvorListfnfrs == null) {
            rfturn;
        }
        flbvorListfnfrs.rfmovf(listfnfr);
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>FlbvorListfnfr</dodf>s durrfntly
     * rfgistfrfd on tiis <dodf>Clipbobrd</dodf>.
     *
     * @rfturn bll of tiis dlipbobrd's <dodf>FlbvorListfnfr</dodf>s or bn fmpty
     *         brrby if no listfnfrs brf durrfntly rfgistfrfd
     * @sff #bddFlbvorListfnfr
     * @sff #rfmovfFlbvorListfnfr
     * @sff FlbvorListfnfr
     * @sff FlbvorEvfnt
     * @sindf 1.5
     */
    publid syndironizfd FlbvorListfnfr[] gftFlbvorListfnfrs() {
        rfturn flbvorListfnfrs == null ? nfw FlbvorListfnfr[0] :
            flbvorListfnfrs.toArrby(nfw FlbvorListfnfr[flbvorListfnfrs.sizf()]);
    }

    /**
     * Cifdks dibngf of tif <dodf>DbtbFlbvor</dodf>s bnd, if nfdfssbry,
     * notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for notifidbtion
     * on <dodf>FlbvorEvfnt</dodf>s.
     *
     * @sindf 1.5
     */
    privbtf void firfFlbvorsCibngfd() {
        if (flbvorListfnfrs == null) {
            rfturn;
        }

        Sft<DbtbFlbvor> prfvDbtbFlbvors = durrfntDbtbFlbvors;
        durrfntDbtbFlbvors = gftAvbilbblfDbtbFlbvorSft();
        if (Objfdts.fqubls(prfvDbtbFlbvors, durrfntDbtbFlbvors)) {
            rfturn;
        }
        flbvorListfnfrs.forEbdi(listfnfr ->
                EvfntQufuf.invokfLbtfr(() ->
                        listfnfr.flbvorsCibngfd(nfw FlbvorEvfnt(Clipbobrd.tiis))));
    }

    /**
     * Rfturns b sft of <dodf>DbtbFlbvor</dodf>s durrfntly bvbilbblf
     * on tiis dlipbobrd.
     *
     * @rfturn b sft of <dodf>DbtbFlbvor</dodf>s durrfntly bvbilbblf
     *         on tiis dlipbobrd
     *
     * @sindf 1.5
     */
    privbtf Sft<DbtbFlbvor> gftAvbilbblfDbtbFlbvorSft() {
        Sft<DbtbFlbvor> sft = nfw HbsiSft<>();
        Trbnsffrbblf dontfnts = gftContfnts(null);
        if (dontfnts != null) {
            DbtbFlbvor[] flbvors = dontfnts.gftTrbnsffrDbtbFlbvors();
            if (flbvors != null) {
                sft.bddAll(Arrbys.bsList(flbvors));
            }
        }
        rfturn sft;
    }
}
