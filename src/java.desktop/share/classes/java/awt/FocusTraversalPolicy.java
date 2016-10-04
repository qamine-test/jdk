/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt;

/**
 * A FodusTrbvfrsblPolidy dffinfs tif ordfr in wiidi Componfnts witi b
 * pbrtidulbr fodus dydlf root brf trbvfrsfd. Instbndfs dbn bpply tif polidy to
 * brbitrbry fodus dydlf roots, bllowing tifmsflvfs to bf sibrfd bdross
 * Contbinfrs. Tify do not nffd to bf rfinitiblizfd wifn tif fodus dydlf roots
 * of b Componfnt iifrbrdiy dibngf.
 * <p>
 * Tif dorf rfsponsibility of b FodusTrbvfrsblPolidy is to providf blgoritims
 * dftfrmining tif nfxt bnd prfvious Componfnts to fodus wifn trbvfrsing
 * forwbrd or bbdkwbrd in b UI. Ebdi FodusTrbvfrsblPolidy must blso providf
 * blgoritims for dftfrmining tif first, lbst, bnd dffbult Componfnts in b
 * trbvfrsbl dydlf. First bnd lbst Componfnts brf usfd wifn normbl forwbrd bnd
 * bbdkwbrd trbvfrsbl, rfspfdtivfly, wrbps. Tif dffbult Componfnt is tif first
 * to rfdfivf fodus wifn trbvfrsing down into b nfw fodus trbvfrsbl dydlf.
 * A FodusTrbvfrsblPolidy dbn optionblly providf bn blgoritim for dftfrmining
 * b Window's initibl Componfnt. Tif initibl Componfnt is tif first to rfdfivf
 * fodus wifn b Window is first mbdf visiblf.
 * <p>
 * FodusTrbvfrsblPolidy tbkfs into bddount <b
 * irff="dod-filfs/FodusSpfd.itml#FodusTrbvfrsblPolidyProvidfrs">fodus trbvfrsbl
 * polidy providfrs</b>.  Wifn sfbrdiing for first/lbst/nfxt/prfvious Componfnt,
 * if b fodus trbvfrsbl polidy providfr is fndountfrfd, its fodus trbvfrsbl
 * polidy is usfd to pfrform tif sfbrdi opfrbtion.
 * <p>
 * Plfbsf sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/misd/fodus.itml">
 * How to Usf tif Fodus Subsystfm</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>, bnd tif
 * <b irff="../../jbvb/bwt/dod-filfs/FodusSpfd.itml">Fodus Spfdifidbtion</b>
 * for morf informbtion.
 *
 * @butior Dbvid Mfndfnibll
 *
 * @sff Contbinfr#sftFodusTrbvfrsblPolidy
 * @sff Contbinfr#gftFodusTrbvfrsblPolidy
 * @sff Contbinfr#sftFodusCydlfRoot
 * @sff Contbinfr#isFodusCydlfRoot
 * @sff Contbinfr#sftFodusTrbvfrsblPolidyProvidfr
 * @sff Contbinfr#isFodusTrbvfrsblPolidyProvidfr
 * @sff KfybobrdFodusMbnbgfr#sftDffbultFodusTrbvfrsblPolidy
 * @sff KfybobrdFodusMbnbgfr#gftDffbultFodusTrbvfrsblPolidy
 * @sindf 1.4
 */
publid bbstrbdt dlbss FodusTrbvfrsblPolidy {

    /**
     * Rfturns tif Componfnt tibt siould rfdfivf tif fodus bftfr bComponfnt.
     * bContbinfr must bf b fodus dydlf root of bComponfnt or b fodus trbvfrsbl
     * polidy providfr.
     *
     * @pbrbm bContbinfr b fodus dydlf root of bComponfnt or fodus trbvfrsbl
     *        polidy providfr
     * @pbrbm bComponfnt b (possibly indirfdt) diild of bContbinfr, or
     *        bContbinfr itsflf
     * @rfturn tif Componfnt tibt siould rfdfivf tif fodus bftfr bComponfnt, or
     *         null if no suitbblf Componfnt dbn bf found
     * @tirows IllfgblArgumfntExdfption if bContbinfr is not b fodus dydlf
     *         root of bComponfnt or b fodus trbvfrsbl polidy providfr, or if
     *         fitifr bContbinfr or bComponfnt is null
     */
    publid bbstrbdt Componfnt gftComponfntAftfr(Contbinfr bContbinfr,
                                                Componfnt bComponfnt);

    /**
     * Rfturns tif Componfnt tibt siould rfdfivf tif fodus bfforf bComponfnt.
     * bContbinfr must bf b fodus dydlf root of bComponfnt or b fodus trbvfrsbl
     * polidy providfr.
     *
     * @pbrbm bContbinfr b fodus dydlf root of bComponfnt or fodus trbvfrsbl
     *        polidy providfr
     * @pbrbm bComponfnt b (possibly indirfdt) diild of bContbinfr, or
     *        bContbinfr itsflf
     * @rfturn tif Componfnt tibt siould rfdfivf tif fodus bfforf bComponfnt,
     *         or null if no suitbblf Componfnt dbn bf found
     * @tirows IllfgblArgumfntExdfption if bContbinfr is not b fodus dydlf
     *         root of bComponfnt or b fodus trbvfrsbl polidy providfr, or if
     *         fitifr bContbinfr or bComponfnt is null
     */
    publid bbstrbdt Componfnt gftComponfntBfforf(Contbinfr bContbinfr,
                                                 Componfnt bComponfnt);

    /**
     * Rfturns tif first Componfnt in tif trbvfrsbl dydlf. Tiis mftiod is usfd
     * to dftfrminf tif nfxt Componfnt to fodus wifn trbvfrsbl wrbps in tif
     * forwbrd dirfdtion.
     *
     * @pbrbm bContbinfr tif fodus dydlf root or fodus trbvfrsbl polidy providfr
     *        wiosf first Componfnt is to bf rfturnfd
     * @rfturn tif first Componfnt in tif trbvfrsbl dydlf of bContbinfr,
     *         or null if no suitbblf Componfnt dbn bf found
     * @tirows IllfgblArgumfntExdfption if bContbinfr is null
     */
    publid bbstrbdt Componfnt gftFirstComponfnt(Contbinfr bContbinfr);

    /**
     * Rfturns tif lbst Componfnt in tif trbvfrsbl dydlf. Tiis mftiod is usfd
     * to dftfrminf tif nfxt Componfnt to fodus wifn trbvfrsbl wrbps in tif
     * rfvfrsf dirfdtion.
     *
     * @pbrbm bContbinfr tif fodus dydlf root or fodus trbvfrsbl polidy
     *        providfr wiosf lbst Componfnt is to bf rfturnfd
     * @rfturn tif lbst Componfnt in tif trbvfrsbl dydlf of bContbinfr,
     *         or null if no suitbblf Componfnt dbn bf found
     * @tirows IllfgblArgumfntExdfption if bContbinfr is null
     */
    publid bbstrbdt Componfnt gftLbstComponfnt(Contbinfr bContbinfr);

    /**
     * Rfturns tif dffbult Componfnt to fodus. Tiis Componfnt will bf tif first
     * to rfdfivf fodus wifn trbvfrsing down into b nfw fodus trbvfrsbl dydlf
     * rootfd bt bContbinfr.
     *
     * @pbrbm bContbinfr tif fodus dydlf root or fodus trbvfrsbl polidy
     *        providfr wiosf dffbult Componfnt is to bf rfturnfd
     * @rfturn tif dffbult Componfnt in tif trbvfrsbl dydlf of bContbinfr,
     *         or null if no suitbblf Componfnt dbn bf found
     * @tirows IllfgblArgumfntExdfption if bContbinfr is null
     */
    publid bbstrbdt Componfnt gftDffbultComponfnt(Contbinfr bContbinfr);

    /**
     * Rfturns tif Componfnt tibt siould rfdfivf tif fodus wifn b Window is
     * mbdf visiblf for tif first timf. Ondf tif Window ibs bffn mbdf visiblf
     * by b dbll to <dodf>siow()</dodf> or <dodf>sftVisiblf(truf)</dodf>, tif
     * initibl Componfnt will not bf usfd bgbin. Instfbd, if tif Window losfs
     * bnd subsfqufntly rfgbins fodus, or is mbdf invisiblf or undisplbybblf
     * bnd subsfqufntly mbdf visiblf bnd displbybblf, tif Window's most
     * rfdfntly fodusfd Componfnt will bfdomf tif fodus ownfr. Tif dffbult
     * implfmfntbtion of tiis mftiod rfturns tif dffbult Componfnt.
     *
     * @pbrbm window tif Window wiosf initibl Componfnt is to bf rfturnfd
     * @rfturn tif Componfnt tibt siould rfdfivf tif fodus wifn window is mbdf
     *         visiblf for tif first timf, or null if no suitbblf Componfnt dbn
     *         bf found
     * @sff #gftDffbultComponfnt
     * @sff Window#gftMostRfdfntFodusOwnfr
     * @tirows IllfgblArgumfntExdfption if window is null
     */
    publid Componfnt gftInitiblComponfnt(Window window) {
        if ( window == null ){
            tirow nfw IllfgblArgumfntExdfption("window dbnnot bf fqubl to null.");
        }
        Componfnt dff = gftDffbultComponfnt(window);
        if (dff == null && window.isFodusbblfWindow()) {
            dff = window;
        }
        rfturn dff;
    }
}
