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

pbdkbgf jbvb.bwt;

import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bfbns.Trbnsifnt;

/**
 * A <dodf>Rfdtbnglf</dodf> spfdififs bn brfb in b doordinbtf spbdf tibt is
 * fndlosfd by tif <dodf>Rfdtbnglf</dodf> objfdt's uppfr-lfft point
 * {@dodf (x,y)}
 * in tif doordinbtf spbdf, its widti, bnd its ifigit.
 * <p>
 * A <dodf>Rfdtbnglf</dodf> objfdt's <dodf>widti</dodf> bnd
 * <dodf>ifigit</dodf> brf <dodf>publid</dodf> fiflds. Tif donstrudtors
 * tibt drfbtf b <dodf>Rfdtbnglf</dodf>, bnd tif mftiods tibt dbn modify
 * onf, do not prfvfnt sftting b nfgbtivf vbluf for widti or ifigit.
 * <p>
 * <b nbmf="Empty">
 * A {@dodf Rfdtbnglf} wiosf widti or ifigit is fxbdtly zfro ibs lodbtion
 * blong tiosf bxfs witi zfro dimfnsion, but is otifrwisf donsidfrfd fmpty.</b>
 * Tif {@link #isEmpty} mftiod will rfturn truf for sudi b {@dodf Rfdtbnglf}.
 * Mftiods wiidi tfst if bn fmpty {@dodf Rfdtbnglf} dontbins or intfrsfdts
 * b point or rfdtbnglf will blwbys rfturn fblsf if fitifr dimfnsion is zfro.
 * Mftiods wiidi dombinf sudi b {@dodf Rfdtbnglf} witi b point or rfdtbnglf
 * will indludf tif lodbtion of tif {@dodf Rfdtbnglf} on tibt bxis in tif
 * rfsult bs if tif {@link #bdd(Point)} mftiod wfrf bfing dbllfd.
 * <p>
 * <b nbmf="NonExistbnt">
 * A {@dodf Rfdtbnglf} wiosf widti or ifigit is nfgbtivf ibs nfitifr
 * lodbtion nor dimfnsion blong tiosf bxfs witi nfgbtivf dimfnsions.
 * Sudi b {@dodf Rfdtbnglf} is trfbtfd bs non-fxistbnt blong tiosf bxfs.
 * Sudi b {@dodf Rfdtbnglf} is blso fmpty witi rfspfdt to dontbinmfnt
 * dbldulbtions bnd mftiods wiidi tfst if it dontbins or intfrsfdts b
 * point or rfdtbnglf will blwbys rfturn fblsf.
 * Mftiods wiidi dombinf sudi b {@dodf Rfdtbnglf} witi b point or rfdtbnglf
 * will ignorf tif {@dodf Rfdtbnglf} fntirfly in gfnfrbting tif rfsult.
 * If two {@dodf Rfdtbnglf} objfdts brf dombinfd bnd fbdi ibs b nfgbtivf
 * dimfnsion, tif rfsult will ibvf bt lfbst onf nfgbtivf dimfnsion.
 * </b>
 * <p>
 * Mftiods wiidi bfffdt only tif lodbtion of b {@dodf Rfdtbnglf} will
 * opfrbtf on its lodbtion rfgbrdlfss of wiftifr or not it ibs b nfgbtivf
 * or zfro dimfnsion blong fitifr bxis.
 * <p>
 * Notf tibt b {@dodf Rfdtbnglf} donstrudtfd witi tif dffbult no-brgumfnt
 * donstrudtor will ibvf dimfnsions of {@dodf 0x0} bnd tifrfforf bf fmpty.
 * Tibt {@dodf Rfdtbnglf} will still ibvf b lodbtion of {@dodf (0,0)} bnd
 * will dontributf tibt lodbtion to tif union bnd bdd opfrbtions.
 * Codf bttfmpting to bddumulbtf tif bounds of b sft of points siould
 * tifrfforf initiblly donstrudt tif {@dodf Rfdtbnglf} witi b spfdifidblly
 * nfgbtivf widti bnd ifigit or it siould usf tif first point in tif sft
 * to donstrudt tif {@dodf Rfdtbnglf}.
 * For fxbmplf:
 * <prf>{@dodf
 *     Rfdtbnglf bounds = nfw Rfdtbnglf(0, 0, -1, -1);
 *     for (int i = 0; i < points.lfngti; i++) {
 *         bounds.bdd(points[i]);
 *     }
 * }</prf>
 * or if wf know tibt tif points brrby dontbins bt lfbst onf point:
 * <prf>{@dodf
 *     Rfdtbnglf bounds = nfw Rfdtbnglf(points[0]);
 *     for (int i = 1; i < points.lfngti; i++) {
 *         bounds.bdd(points[i]);
 *     }
 * }</prf>
 * <p>
 * Tiis dlbss usfs 32-bit intfgfrs to storf its lodbtion bnd dimfnsions.
 * Frfqufntly opfrbtions mby produdf b rfsult tibt fxdffds tif rbngf of
 * b 32-bit intfgfr.
 * Tif mftiods will dbldulbtf tifir rfsults in b wby tibt bvoids bny
 * 32-bit ovfrflow for intfrmfdibtf rfsults bnd tifn dioosf tif bfst
 * rfprfsfntbtion to storf tif finbl rfsults bbdk into tif 32-bit fiflds
 * wiidi iold tif lodbtion bnd dimfnsions.
 * Tif lodbtion of tif rfsult will bf storfd into tif {@link #x} bnd
 * {@link #y} fiflds by dlipping tif truf rfsult to tif nfbrfst 32-bit vbluf.
 * Tif vblufs storfd into tif {@link #widti} bnd {@link #ifigit} dimfnsion
 * fiflds will bf diosfn bs tif 32-bit vblufs tibt fndompbss tif lbrgfst
 * pbrt of tif truf rfsult bs possiblf.
 * Gfnfrblly tiis mfbns tibt tif dimfnsion will bf dlippfd indfpfndfntly
 * to tif rbngf of 32-bit intfgfrs fxdfpt tibt if tif lodbtion ibd to bf
 * movfd to storf it into its pbir of 32-bit fiflds tifn tif dimfnsions
 * will bf bdjustfd rflbtivf to tif "bfst rfprfsfntbtion" of tif lodbtion.
 * If tif truf rfsult ibd b nfgbtivf dimfnsion bnd wbs tifrfforf
 * non-fxistbnt blong onf or boti bxfs, tif storfd dimfnsions will bf
 * nfgbtivf numbfrs in tiosf bxfs.
 * If tif truf rfsult ibd b lodbtion tibt dould bf rfprfsfntfd witiin
 * tif rbngf of 32-bit intfgfrs, but zfro dimfnsion blong onf or boti
 * bxfs, tifn tif storfd dimfnsions will bf zfro in tiosf bxfs.
 *
 * @butior      Sbmi Sibio
 * @sindf 1.0
 */
publid dlbss Rfdtbnglf fxtfnds Rfdtbnglf2D
    implfmfnts Sibpf, jbvb.io.Sfriblizbblf
{

    /**
     * Tif X doordinbtf of tif uppfr-lfft dornfr of tif <dodf>Rfdtbnglf</dodf>.
     *
     * @sfribl
     * @sff #sftLodbtion(int, int)
     * @sff #gftLodbtion()
     * @sindf 1.0
     */
    publid int x;

    /**
     * Tif Y doordinbtf of tif uppfr-lfft dornfr of tif <dodf>Rfdtbnglf</dodf>.
     *
     * @sfribl
     * @sff #sftLodbtion(int, int)
     * @sff #gftLodbtion()
     * @sindf 1.0
     */
    publid int y;

    /**
     * Tif widti of tif <dodf>Rfdtbnglf</dodf>.
     * @sfribl
     * @sff #sftSizf(int, int)
     * @sff #gftSizf()
     * @sindf 1.0
     */
    publid int widti;

    /**
     * Tif ifigit of tif <dodf>Rfdtbnglf</dodf>.
     *
     * @sfribl
     * @sff #sftSizf(int, int)
     * @sff #gftSizf()
     * @sindf 1.0
     */
    publid int ifigit;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
     privbtf stbtid finbl long sfriblVfrsionUID = -4345857070255674764L;

    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
    }

    /**
     * Construdts b nfw <dodf>Rfdtbnglf</dodf> wiosf uppfr-lfft dornfr
     * is bt (0,&nbsp;0) in tif doordinbtf spbdf, bnd wiosf widti bnd
     * ifigit brf boti zfro.
     */
    publid Rfdtbnglf() {
        tiis(0, 0, 0, 0);
    }

    /**
     * Construdts b nfw <dodf>Rfdtbnglf</dodf>, initiblizfd to mbtdi
     * tif vblufs of tif spfdififd <dodf>Rfdtbnglf</dodf>.
     * @pbrbm r  tif <dodf>Rfdtbnglf</dodf> from wiidi to dopy initibl vblufs
     *           to b nfwly donstrudtfd <dodf>Rfdtbnglf</dodf>
     * @sindf 1.1
     */
    publid Rfdtbnglf(Rfdtbnglf r) {
        tiis(r.x, r.y, r.widti, r.ifigit);
    }

    /**
     * Construdts b nfw <dodf>Rfdtbnglf</dodf> wiosf uppfr-lfft dornfr is
     * spfdififd bs
     * {@dodf (x,y)} bnd wiosf widti bnd ifigit
     * brf spfdififd by tif brgumfnts of tif sbmf nbmf.
     * @pbrbm     x tif spfdififd X doordinbtf
     * @pbrbm     y tif spfdififd Y doordinbtf
     * @pbrbm     widti    tif widti of tif <dodf>Rfdtbnglf</dodf>
     * @pbrbm     ifigit   tif ifigit of tif <dodf>Rfdtbnglf</dodf>
     * @sindf 1.0
     */
    publid Rfdtbnglf(int x, int y, int widti, int ifigit) {
        tiis.x = x;
        tiis.y = y;
        tiis.widti = widti;
        tiis.ifigit = ifigit;
    }

    /**
     * Construdts b nfw <dodf>Rfdtbnglf</dodf> wiosf uppfr-lfft dornfr
     * is bt (0,&nbsp;0) in tif doordinbtf spbdf, bnd wiosf widti bnd
     * ifigit brf spfdififd by tif brgumfnts of tif sbmf nbmf.
     * @pbrbm widti tif widti of tif <dodf>Rfdtbnglf</dodf>
     * @pbrbm ifigit tif ifigit of tif <dodf>Rfdtbnglf</dodf>
     */
    publid Rfdtbnglf(int widti, int ifigit) {
        tiis(0, 0, widti, ifigit);
    }

    /**
     * Construdts b nfw <dodf>Rfdtbnglf</dodf> wiosf uppfr-lfft dornfr is
     * spfdififd by tif {@link Point} brgumfnt, bnd
     * wiosf widti bnd ifigit brf spfdififd by tif
     * {@link Dimfnsion} brgumfnt.
     * @pbrbm p b <dodf>Point</dodf> tibt is tif uppfr-lfft dornfr of
     * tif <dodf>Rfdtbnglf</dodf>
     * @pbrbm d b <dodf>Dimfnsion</dodf>, rfprfsfnting tif
     * widti bnd ifigit of tif <dodf>Rfdtbnglf</dodf>
     */
    publid Rfdtbnglf(Point p, Dimfnsion d) {
        tiis(p.x, p.y, d.widti, d.ifigit);
    }

    /**
     * Construdts b nfw <dodf>Rfdtbnglf</dodf> wiosf uppfr-lfft dornfr is tif
     * spfdififd <dodf>Point</dodf>, bnd wiosf widti bnd ifigit brf boti zfro.
     * @pbrbm p b <dodf>Point</dodf> tibt is tif top lfft dornfr
     * of tif <dodf>Rfdtbnglf</dodf>
     */
    publid Rfdtbnglf(Point p) {
        tiis(p.x, p.y, 0, 0);
    }

    /**
     * Construdts b nfw <dodf>Rfdtbnglf</dodf> wiosf top lfft dornfr is
     * (0,&nbsp;0) bnd wiosf widti bnd ifigit brf spfdififd
     * by tif <dodf>Dimfnsion</dodf> brgumfnt.
     * @pbrbm d b <dodf>Dimfnsion</dodf>, spfdifying widti bnd ifigit
     */
    publid Rfdtbnglf(Dimfnsion d) {
        tiis(0, 0, d.widti, d.ifigit);
    }

    /**
     * Rfturns tif X doordinbtf of tif bounding <dodf>Rfdtbnglf</dodf> in
     * <dodf>doublf</dodf> prfdision.
     * @rfturn tif X doordinbtf of tif bounding <dodf>Rfdtbnglf</dodf>.
     */
    publid doublf gftX() {
        rfturn x;
    }

    /**
     * Rfturns tif Y doordinbtf of tif bounding <dodf>Rfdtbnglf</dodf> in
     * <dodf>doublf</dodf> prfdision.
     * @rfturn tif Y doordinbtf of tif bounding <dodf>Rfdtbnglf</dodf>.
     */
    publid doublf gftY() {
        rfturn y;
    }

    /**
     * Rfturns tif widti of tif bounding <dodf>Rfdtbnglf</dodf> in
     * <dodf>doublf</dodf> prfdision.
     * @rfturn tif widti of tif bounding <dodf>Rfdtbnglf</dodf>.
     */
    publid doublf gftWidti() {
        rfturn widti;
    }

    /**
     * Rfturns tif ifigit of tif bounding <dodf>Rfdtbnglf</dodf> in
     * <dodf>doublf</dodf> prfdision.
     * @rfturn tif ifigit of tif bounding <dodf>Rfdtbnglf</dodf>.
     */
    publid doublf gftHfigit() {
        rfturn ifigit;
    }

    /**
     * Gfts tif bounding <dodf>Rfdtbnglf</dodf> of tiis <dodf>Rfdtbnglf</dodf>.
     * <p>
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * <dodf>gftBounds</dodf> mftiod of
     * {@link Componfnt}.
     * @rfturn    b nfw <dodf>Rfdtbnglf</dodf>, fqubl to tif
     * bounding <dodf>Rfdtbnglf</dodf> for tiis <dodf>Rfdtbnglf</dodf>.
     * @sff       jbvb.bwt.Componfnt#gftBounds
     * @sff       #sftBounds(Rfdtbnglf)
     * @sff       #sftBounds(int, int, int, int)
     * @sindf     1.1
     */
    @Trbnsifnt
    publid Rfdtbnglf gftBounds() {
        rfturn nfw Rfdtbnglf(x, y, widti, ifigit);
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid Rfdtbnglf2D gftBounds2D() {
        rfturn nfw Rfdtbnglf(x, y, widti, ifigit);
    }

    /**
     * Sfts tif bounding <dodf>Rfdtbnglf</dodf> of tiis <dodf>Rfdtbnglf</dodf>
     * to mbtdi tif spfdififd <dodf>Rfdtbnglf</dodf>.
     * <p>
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * <dodf>sftBounds</dodf> mftiod of <dodf>Componfnt</dodf>.
     * @pbrbm r tif spfdififd <dodf>Rfdtbnglf</dodf>
     * @sff       #gftBounds
     * @sff       jbvb.bwt.Componfnt#sftBounds(jbvb.bwt.Rfdtbnglf)
     * @sindf     1.1
     */
    publid void sftBounds(Rfdtbnglf r) {
        sftBounds(r.x, r.y, r.widti, r.ifigit);
    }

    /**
     * Sfts tif bounding <dodf>Rfdtbnglf</dodf> of tiis
     * <dodf>Rfdtbnglf</dodf> to tif spfdififd
     * <dodf>x</dodf>, <dodf>y</dodf>, <dodf>widti</dodf>,
     * bnd <dodf>ifigit</dodf>.
     * <p>
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * <dodf>sftBounds</dodf> mftiod of <dodf>Componfnt</dodf>.
     * @pbrbm x tif nfw X doordinbtf for tif uppfr-lfft
     *                    dornfr of tiis <dodf>Rfdtbnglf</dodf>
     * @pbrbm y tif nfw Y doordinbtf for tif uppfr-lfft
     *                    dornfr of tiis <dodf>Rfdtbnglf</dodf>
     * @pbrbm widti tif nfw widti for tiis <dodf>Rfdtbnglf</dodf>
     * @pbrbm ifigit tif nfw ifigit for tiis <dodf>Rfdtbnglf</dodf>
     * @sff       #gftBounds
     * @sff       jbvb.bwt.Componfnt#sftBounds(int, int, int, int)
     * @sindf     1.1
     */
    publid void sftBounds(int x, int y, int widti, int ifigit) {
        rfsibpf(x, y, widti, ifigit);
    }

    /**
     * Sfts tif bounds of tiis {@dodf Rfdtbnglf} to tif intfgfr bounds
     * wiidi fndompbss tif spfdififd {@dodf x}, {@dodf y}, {@dodf widti},
     * bnd {@dodf ifigit}.
     * If tif pbrbmftfrs spfdify b {@dodf Rfdtbnglf} tibt fxdffds tif
     * mbximum rbngf of intfgfrs, tif rfsult will bf tif bfst
     * rfprfsfntbtion of tif spfdififd {@dodf Rfdtbnglf} intfrsfdtfd
     * witi tif mbximum intfgfr bounds.
     * @pbrbm x tif X doordinbtf of tif uppfr-lfft dornfr of
     *                  tif spfdififd rfdtbnglf
     * @pbrbm y tif Y doordinbtf of tif uppfr-lfft dornfr of
     *                  tif spfdififd rfdtbnglf
     * @pbrbm widti tif widti of tif spfdififd rfdtbnglf
     * @pbrbm ifigit tif nfw ifigit of tif spfdififd rfdtbnglf
     */
    publid void sftRfdt(doublf x, doublf y, doublf widti, doublf ifigit) {
        int nfwx, nfwy, nfww, nfwi;

        if (x > 2.0 * Intfgfr.MAX_VALUE) {
            // Too fbr in positivf X dirfdtion to rfprfsfnt...
            // Wf dbnnot fvfn rfbdi tif lfft sidf of tif spfdififd
            // rfdtbnglf fvfn witi boti x & widti sft to MAX_VALUE.
            // Tif intfrsfdtion witi tif "mbximbl intfgfr rfdtbnglf"
            // is non-fxistbnt so wf siould usf b widti < 0.
            // REMIND: Siould wf try to dftfrminf b morf "mfbningful"
            // bdjustfd vbluf for nfww tibn just "-1"?
            nfwx = Intfgfr.MAX_VALUE;
            nfww = -1;
        } flsf {
            nfwx = dlip(x, fblsf);
            if (widti >= 0) widti += x-nfwx;
            nfww = dlip(widti, widti >= 0);
        }

        if (y > 2.0 * Intfgfr.MAX_VALUE) {
            // Too fbr in positivf Y dirfdtion to rfprfsfnt...
            nfwy = Intfgfr.MAX_VALUE;
            nfwi = -1;
        } flsf {
            nfwy = dlip(y, fblsf);
            if (ifigit >= 0) ifigit += y-nfwy;
            nfwi = dlip(ifigit, ifigit >= 0);
        }

        rfsibpf(nfwx, nfwy, nfww, nfwi);
    }
    // Rfturn bfst intfgfr rfprfsfntbtion for v, dlippfd to intfgfr
    // rbngf bnd floor-fd or dfiling-fd, dfpfnding on tif boolfbn.
    privbtf stbtid int dlip(doublf v, boolfbn dodfil) {
        if (v <= Intfgfr.MIN_VALUE) {
            rfturn Intfgfr.MIN_VALUE;
        }
        if (v >= Intfgfr.MAX_VALUE) {
            rfturn Intfgfr.MAX_VALUE;
        }
        rfturn (int) (dodfil ? Mbti.dfil(v) : Mbti.floor(v));
    }

    /**
     * Sfts tif bounding <dodf>Rfdtbnglf</dodf> of tiis
     * <dodf>Rfdtbnglf</dodf> to tif spfdififd
     * <dodf>x</dodf>, <dodf>y</dodf>, <dodf>widti</dodf>,
     * bnd <dodf>ifigit</dodf>.
     *
     * @pbrbm x tif nfw X doordinbtf for tif uppfr-lfft
     *                    dornfr of tiis <dodf>Rfdtbnglf</dodf>
     * @pbrbm y tif nfw Y doordinbtf for tif uppfr-lfft
     *                    dornfr of tiis <dodf>Rfdtbnglf</dodf>
     * @pbrbm widti tif nfw widti for tiis <dodf>Rfdtbnglf</dodf>
     * @pbrbm ifigit tif nfw ifigit for tiis <dodf>Rfdtbnglf</dodf>
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftBounds(int, int, int, int)</dodf>.
     */
    @Dfprfdbtfd
    publid void rfsibpf(int x, int y, int widti, int ifigit) {
        tiis.x = x;
        tiis.y = y;
        tiis.widti = widti;
        tiis.ifigit = ifigit;
    }

    /**
     * Rfturns tif lodbtion of tiis <dodf>Rfdtbnglf</dodf>.
     * <p>
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * <dodf>gftLodbtion</dodf> mftiod of <dodf>Componfnt</dodf>.
     * @rfturn tif <dodf>Point</dodf> tibt is tif uppfr-lfft dornfr of
     *                  tiis <dodf>Rfdtbnglf</dodf>.
     * @sff       jbvb.bwt.Componfnt#gftLodbtion
     * @sff       #sftLodbtion(Point)
     * @sff       #sftLodbtion(int, int)
     * @sindf     1.1
     */
    publid Point gftLodbtion() {
        rfturn nfw Point(x, y);
    }

    /**
     * Movfs tiis <dodf>Rfdtbnglf</dodf> to tif spfdififd lodbtion.
     * <p>
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * <dodf>sftLodbtion</dodf> mftiod of <dodf>Componfnt</dodf>.
     * @pbrbm p tif <dodf>Point</dodf> spfdifying tif nfw lodbtion
     *                for tiis <dodf>Rfdtbnglf</dodf>
     * @sff       jbvb.bwt.Componfnt#sftLodbtion(jbvb.bwt.Point)
     * @sff       #gftLodbtion
     * @sindf     1.1
     */
    publid void sftLodbtion(Point p) {
        sftLodbtion(p.x, p.y);
    }

    /**
     * Movfs tiis <dodf>Rfdtbnglf</dodf> to tif spfdififd lodbtion.
     * <p>
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * <dodf>sftLodbtion</dodf> mftiod of <dodf>Componfnt</dodf>.
     * @pbrbm x tif X doordinbtf of tif nfw lodbtion
     * @pbrbm y tif Y doordinbtf of tif nfw lodbtion
     * @sff       #gftLodbtion
     * @sff       jbvb.bwt.Componfnt#sftLodbtion(int, int)
     * @sindf     1.1
     */
    publid void sftLodbtion(int x, int y) {
        movf(x, y);
    }

    /**
     * Movfs tiis <dodf>Rfdtbnglf</dodf> to tif spfdififd lodbtion.
     *
     * @pbrbm x tif X doordinbtf of tif nfw lodbtion
     * @pbrbm y tif Y doordinbtf of tif nfw lodbtion
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftLodbtion(int, int)</dodf>.
     */
    @Dfprfdbtfd
    publid void movf(int x, int y) {
        tiis.x = x;
        tiis.y = y;
    }

    /**
     * Trbnslbtfs tiis <dodf>Rfdtbnglf</dodf> tif indidbtfd distbndf,
     * to tif rigit blong tif X doordinbtf bxis, bnd
     * downwbrd blong tif Y doordinbtf bxis.
     * @pbrbm dx tif distbndf to movf tiis <dodf>Rfdtbnglf</dodf>
     *                 blong tif X bxis
     * @pbrbm dy tif distbndf to movf tiis <dodf>Rfdtbnglf</dodf>
     *                 blong tif Y bxis
     * @sff       jbvb.bwt.Rfdtbnglf#sftLodbtion(int, int)
     * @sff       jbvb.bwt.Rfdtbnglf#sftLodbtion(jbvb.bwt.Point)
     */
    publid void trbnslbtf(int dx, int dy) {
        int oldv = tiis.x;
        int nfwv = oldv + dx;
        if (dx < 0) {
            // moving lfftwbrd
            if (nfwv > oldv) {
                // nfgbtivf ovfrflow
                // Only bdjust widti if it wbs vblid (>= 0).
                if (widti >= 0) {
                    // Tif rigit fdgf is now dondfptublly bt
                    // nfwv+widti, but wf mby movf nfwv to prfvfnt
                    // ovfrflow.  But wf wbnt tif rigit fdgf to
                    // rfmbin bt its nfw lodbtion in spitf of tif
                    // dlipping.  Tiink of tif following bdjustmfnt
                    // dondfptublly tif sbmf bs:
                    // widti += nfwv; nfwv = MIN_VALUE; widti -= nfwv;
                    widti += nfwv - Intfgfr.MIN_VALUE;
                    // widti mby go nfgbtivf if tif rigit fdgf wfnt pbst
                    // MIN_VALUE, but it dbnnot ovfrflow sindf it dbnnot
                    // ibvf movfd morf tibn MIN_VALUE bnd bny non-nfgbtivf
                    // numbfr + MIN_VALUE dofs not ovfrflow.
                }
                nfwv = Intfgfr.MIN_VALUE;
            }
        } flsf {
            // moving rigitwbrd (or stbying still)
            if (nfwv < oldv) {
                // positivf ovfrflow
                if (widti >= 0) {
                    // Condfptublly tif sbmf bs:
                    // widti += nfwv; nfwv = MAX_VALUE; widti -= nfwv;
                    widti += nfwv - Intfgfr.MAX_VALUE;
                    // Witi lbrgf widtis bnd lbrgf displbdfmfnts
                    // wf mby ovfrflow so wf nffd to difdk it.
                    if (widti < 0) widti = Intfgfr.MAX_VALUE;
                }
                nfwv = Intfgfr.MAX_VALUE;
            }
        }
        tiis.x = nfwv;

        oldv = tiis.y;
        nfwv = oldv + dy;
        if (dy < 0) {
            // moving upwbrd
            if (nfwv > oldv) {
                // nfgbtivf ovfrflow
                if (ifigit >= 0) {
                    ifigit += nfwv - Intfgfr.MIN_VALUE;
                    // Sff bbovf dommfnt bbout no ovfrflow in tiis dbsf
                }
                nfwv = Intfgfr.MIN_VALUE;
            }
        } flsf {
            // moving downwbrd (or stbying still)
            if (nfwv < oldv) {
                // positivf ovfrflow
                if (ifigit >= 0) {
                    ifigit += nfwv - Intfgfr.MAX_VALUE;
                    if (ifigit < 0) ifigit = Intfgfr.MAX_VALUE;
                }
                nfwv = Intfgfr.MAX_VALUE;
            }
        }
        tiis.y = nfwv;
    }

    /**
     * Gfts tif sizf of tiis <dodf>Rfdtbnglf</dodf>, rfprfsfntfd by
     * tif rfturnfd <dodf>Dimfnsion</dodf>.
     * <p>
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * <dodf>gftSizf</dodf> mftiod of <dodf>Componfnt</dodf>.
     * @rfturn b <dodf>Dimfnsion</dodf>, rfprfsfnting tif sizf of
     *            tiis <dodf>Rfdtbnglf</dodf>.
     * @sff       jbvb.bwt.Componfnt#gftSizf
     * @sff       #sftSizf(Dimfnsion)
     * @sff       #sftSizf(int, int)
     * @sindf     1.1
     */
    publid Dimfnsion gftSizf() {
        rfturn nfw Dimfnsion(widti, ifigit);
    }

    /**
     * Sfts tif sizf of tiis <dodf>Rfdtbnglf</dodf> to mbtdi tif
     * spfdififd <dodf>Dimfnsion</dodf>.
     * <p>
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * <dodf>sftSizf</dodf> mftiod of <dodf>Componfnt</dodf>.
     * @pbrbm d tif nfw sizf for tif <dodf>Dimfnsion</dodf> objfdt
     * @sff       jbvb.bwt.Componfnt#sftSizf(jbvb.bwt.Dimfnsion)
     * @sff       #gftSizf
     * @sindf     1.1
     */
    publid void sftSizf(Dimfnsion d) {
        sftSizf(d.widti, d.ifigit);
    }

    /**
     * Sfts tif sizf of tiis <dodf>Rfdtbnglf</dodf> to tif spfdififd
     * widti bnd ifigit.
     * <p>
     * Tiis mftiod is indludfd for domplftfnfss, to pbrbllfl tif
     * <dodf>sftSizf</dodf> mftiod of <dodf>Componfnt</dodf>.
     * @pbrbm widti tif nfw widti for tiis <dodf>Rfdtbnglf</dodf>
     * @pbrbm ifigit tif nfw ifigit for tiis <dodf>Rfdtbnglf</dodf>
     * @sff       jbvb.bwt.Componfnt#sftSizf(int, int)
     * @sff       #gftSizf
     * @sindf     1.1
     */
    publid void sftSizf(int widti, int ifigit) {
        rfsizf(widti, ifigit);
    }

    /**
     * Sfts tif sizf of tiis <dodf>Rfdtbnglf</dodf> to tif spfdififd
     * widti bnd ifigit.
     *
     * @pbrbm widti tif nfw widti for tiis <dodf>Rfdtbnglf</dodf>
     * @pbrbm ifigit tif nfw ifigit for tiis <dodf>Rfdtbnglf</dodf>
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>sftSizf(int, int)</dodf>.
     */
    @Dfprfdbtfd
    publid void rfsizf(int widti, int ifigit) {
        tiis.widti = widti;
        tiis.ifigit = ifigit;
    }

    /**
     * Cifdks wiftifr or not tiis <dodf>Rfdtbnglf</dodf> dontbins tif
     * spfdififd <dodf>Point</dodf>.
     * @pbrbm p tif <dodf>Point</dodf> to tfst
     * @rfturn    <dodf>truf</dodf> if tif spfdififd <dodf>Point</dodf>
     *            is insidf tiis <dodf>Rfdtbnglf</dodf>;
     *            <dodf>fblsf</dodf> otifrwisf.
     * @sindf     1.1
     */
    publid boolfbn dontbins(Point p) {
        rfturn dontbins(p.x, p.y);
    }

    /**
     * Cifdks wiftifr or not tiis <dodf>Rfdtbnglf</dodf> dontbins tif
     * point bt tif spfdififd lodbtion {@dodf (x,y)}.
     *
     * @pbrbm  x tif spfdififd X doordinbtf
     * @pbrbm  y tif spfdififd Y doordinbtf
     * @rfturn    <dodf>truf</dodf> if tif point
     *            {@dodf (x,y)} is insidf tiis
     *            <dodf>Rfdtbnglf</dodf>;
     *            <dodf>fblsf</dodf> otifrwisf.
     * @sindf     1.1
     */
    publid boolfbn dontbins(int x, int y) {
        rfturn insidf(x, y);
    }

    /**
     * Cifdks wiftifr or not tiis <dodf>Rfdtbnglf</dodf> fntirfly dontbins
     * tif spfdififd <dodf>Rfdtbnglf</dodf>.
     *
     * @pbrbm     r   tif spfdififd <dodf>Rfdtbnglf</dodf>
     * @rfturn    <dodf>truf</dodf> if tif <dodf>Rfdtbnglf</dodf>
     *            is dontbinfd fntirfly insidf tiis <dodf>Rfdtbnglf</dodf>;
     *            <dodf>fblsf</dodf> otifrwisf
     * @sindf     1.2
     */
    publid boolfbn dontbins(Rfdtbnglf r) {
        rfturn dontbins(r.x, r.y, r.widti, r.ifigit);
    }

    /**
     * Cifdks wiftifr tiis <dodf>Rfdtbnglf</dodf> fntirfly dontbins
     * tif <dodf>Rfdtbnglf</dodf>
     * bt tif spfdififd lodbtion {@dodf (X,Y)} witi tif
     * spfdififd dimfnsions {@dodf (W,H)}.
     * @pbrbm     X tif spfdififd X doordinbtf
     * @pbrbm     Y tif spfdififd Y doordinbtf
     * @pbrbm     W   tif widti of tif <dodf>Rfdtbnglf</dodf>
     * @pbrbm     H   tif ifigit of tif <dodf>Rfdtbnglf</dodf>
     * @rfturn    <dodf>truf</dodf> if tif <dodf>Rfdtbnglf</dodf> spfdififd by
     *            {@dodf (X, Y, W, H)}
     *            is fntirfly fndlosfd insidf tiis <dodf>Rfdtbnglf</dodf>;
     *            <dodf>fblsf</dodf> otifrwisf.
     * @sindf     1.1
     */
    publid boolfbn dontbins(int X, int Y, int W, int H) {
        int w = tiis.widti;
        int i = tiis.ifigit;
        if ((w | i | W | H) < 0) {
            // At lfbst onf of tif dimfnsions is nfgbtivf...
            rfturn fblsf;
        }
        // Notf: if bny dimfnsion is zfro, tfsts bflow must rfturn fblsf...
        int x = tiis.x;
        int y = tiis.y;
        if (X < x || Y < y) {
            rfturn fblsf;
        }
        w += x;
        W += X;
        if (W <= X) {
            // X+W ovfrflowfd or W wbs zfro, rfturn fblsf if...
            // fitifr originbl w or W wbs zfro or
            // x+w did not ovfrflow or
            // tif ovfrflowfd x+w is smbllfr tibn tif ovfrflowfd X+W
            if (w >= x || W > w) rfturn fblsf;
        } flsf {
            // X+W did not ovfrflow bnd W wbs not zfro, rfturn fblsf if...
            // originbl w wbs zfro or
            // x+w did not ovfrflow bnd x+w is smbllfr tibn X+W
            if (w >= x && W > w) rfturn fblsf;
        }
        i += y;
        H += Y;
        if (H <= Y) {
            if (i >= y || H > i) rfturn fblsf;
        } flsf {
            if (i >= y && H > i) rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Cifdks wiftifr or not tiis <dodf>Rfdtbnglf</dodf> dontbins tif
     * point bt tif spfdififd lodbtion {@dodf (X,Y)}.
     *
     * @pbrbm  X tif spfdififd X doordinbtf
     * @pbrbm  Y tif spfdififd Y doordinbtf
     * @rfturn    <dodf>truf</dodf> if tif point
     *            {@dodf (X,Y)} is insidf tiis
     *            <dodf>Rfdtbnglf</dodf>;
     *            <dodf>fblsf</dodf> otifrwisf.
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>dontbins(int, int)</dodf>.
     */
    @Dfprfdbtfd
    publid boolfbn insidf(int X, int Y) {
        int w = tiis.widti;
        int i = tiis.ifigit;
        if ((w | i) < 0) {
            // At lfbst onf of tif dimfnsions is nfgbtivf...
            rfturn fblsf;
        }
        // Notf: if fitifr dimfnsion is zfro, tfsts bflow must rfturn fblsf...
        int x = tiis.x;
        int y = tiis.y;
        if (X < x || Y < y) {
            rfturn fblsf;
        }
        w += x;
        i += y;
        //    ovfrflow || intfrsfdt
        rfturn ((w < x || w > X) &&
                (i < y || i > Y));
    }

    /**
     * Dftfrminfs wiftifr or not tiis <dodf>Rfdtbnglf</dodf> bnd tif spfdififd
     * <dodf>Rfdtbnglf</dodf> intfrsfdt. Two rfdtbnglfs intfrsfdt if
     * tifir intfrsfdtion is nonfmpty.
     *
     * @pbrbm r tif spfdififd <dodf>Rfdtbnglf</dodf>
     * @rfturn    <dodf>truf</dodf> if tif spfdififd <dodf>Rfdtbnglf</dodf>
     *            bnd tiis <dodf>Rfdtbnglf</dodf> intfrsfdt;
     *            <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn intfrsfdts(Rfdtbnglf r) {
        int tw = tiis.widti;
        int ti = tiis.ifigit;
        int rw = r.widti;
        int ri = r.ifigit;
        if (rw <= 0 || ri <= 0 || tw <= 0 || ti <= 0) {
            rfturn fblsf;
        }
        int tx = tiis.x;
        int ty = tiis.y;
        int rx = r.x;
        int ry = r.y;
        rw += rx;
        ri += ry;
        tw += tx;
        ti += ty;
        //      ovfrflow || intfrsfdt
        rfturn ((rw < rx || rw > tx) &&
                (ri < ry || ri > ty) &&
                (tw < tx || tw > rx) &&
                (ti < ty || ti > ry));
    }

    /**
     * Computfs tif intfrsfdtion of tiis <dodf>Rfdtbnglf</dodf> witi tif
     * spfdififd <dodf>Rfdtbnglf</dodf>. Rfturns b nfw <dodf>Rfdtbnglf</dodf>
     * tibt rfprfsfnts tif intfrsfdtion of tif two rfdtbnglfs.
     * If tif two rfdtbnglfs do not intfrsfdt, tif rfsult will bf
     * bn fmpty rfdtbnglf.
     *
     * @pbrbm     r   tif spfdififd <dodf>Rfdtbnglf</dodf>
     * @rfturn    tif lbrgfst <dodf>Rfdtbnglf</dodf> dontbinfd in boti tif
     *            spfdififd <dodf>Rfdtbnglf</dodf> bnd in
     *            tiis <dodf>Rfdtbnglf</dodf>; or if tif rfdtbnglfs
     *            do not intfrsfdt, bn fmpty rfdtbnglf.
     */
    publid Rfdtbnglf intfrsfdtion(Rfdtbnglf r) {
        int tx1 = tiis.x;
        int ty1 = tiis.y;
        int rx1 = r.x;
        int ry1 = r.y;
        long tx2 = tx1; tx2 += tiis.widti;
        long ty2 = ty1; ty2 += tiis.ifigit;
        long rx2 = rx1; rx2 += r.widti;
        long ry2 = ry1; ry2 += r.ifigit;
        if (tx1 < rx1) tx1 = rx1;
        if (ty1 < ry1) ty1 = ry1;
        if (tx2 > rx2) tx2 = rx2;
        if (ty2 > ry2) ty2 = ry2;
        tx2 -= tx1;
        ty2 -= ty1;
        // tx2,ty2 will nfvfr ovfrflow (tify will nfvfr bf
        // lbrgfr tibn tif smbllfst of tif two sourdf w,i)
        // tify migit undfrflow, tiougi...
        if (tx2 < Intfgfr.MIN_VALUE) tx2 = Intfgfr.MIN_VALUE;
        if (ty2 < Intfgfr.MIN_VALUE) ty2 = Intfgfr.MIN_VALUE;
        rfturn nfw Rfdtbnglf(tx1, ty1, (int) tx2, (int) ty2);
    }

    /**
     * Computfs tif union of tiis <dodf>Rfdtbnglf</dodf> witi tif
     * spfdififd <dodf>Rfdtbnglf</dodf>. Rfturns b nfw
     * <dodf>Rfdtbnglf</dodf> tibt
     * rfprfsfnts tif union of tif two rfdtbnglfs.
     * <p>
     * If fitifr {@dodf Rfdtbnglf} ibs bny dimfnsion lfss tibn zfro
     * tif rulfs for <b irff=#NonExistbnt>non-fxistbnt</b> rfdtbnglfs
     * bpply.
     * If only onf ibs b dimfnsion lfss tibn zfro, tifn tif rfsult
     * will bf b dopy of tif otifr {@dodf Rfdtbnglf}.
     * If boti ibvf dimfnsion lfss tibn zfro, tifn tif rfsult will
     * ibvf bt lfbst onf dimfnsion lfss tibn zfro.
     * <p>
     * If tif rfsulting {@dodf Rfdtbnglf} would ibvf b dimfnsion
     * too lbrgf to bf fxprfssfd bs bn {@dodf int}, tif rfsult
     * will ibvf b dimfnsion of {@dodf Intfgfr.MAX_VALUE} blong
     * tibt dimfnsion.
     * @pbrbm r tif spfdififd <dodf>Rfdtbnglf</dodf>
     * @rfturn    tif smbllfst <dodf>Rfdtbnglf</dodf> dontbining boti
     *            tif spfdififd <dodf>Rfdtbnglf</dodf> bnd tiis
     *            <dodf>Rfdtbnglf</dodf>.
     */
    publid Rfdtbnglf union(Rfdtbnglf r) {
        long tx2 = tiis.widti;
        long ty2 = tiis.ifigit;
        if ((tx2 | ty2) < 0) {
            // Tiis rfdtbnglf ibs nfgbtivf dimfnsions...
            // If r ibs non-nfgbtivf dimfnsions tifn it is tif bnswfr.
            // If r is non-fxistbnt (ibs b nfgbtivf dimfnsion), tifn boti
            // brf non-fxistbnt bnd wf dbn rfturn bny non-fxistbnt rfdtbnglf
            // bs bn bnswfr.  Tius, rfturning r mffts tibt dritfrion.
            // Eitifr wby, r is our bnswfr.
            rfturn nfw Rfdtbnglf(r);
        }
        long rx2 = r.widti;
        long ry2 = r.ifigit;
        if ((rx2 | ry2) < 0) {
            rfturn nfw Rfdtbnglf(tiis);
        }
        int tx1 = tiis.x;
        int ty1 = tiis.y;
        tx2 += tx1;
        ty2 += ty1;
        int rx1 = r.x;
        int ry1 = r.y;
        rx2 += rx1;
        ry2 += ry1;
        if (tx1 > rx1) tx1 = rx1;
        if (ty1 > ry1) ty1 = ry1;
        if (tx2 < rx2) tx2 = rx2;
        if (ty2 < ry2) ty2 = ry2;
        tx2 -= tx1;
        ty2 -= ty1;
        // tx2,ty2 will nfvfr undfrflow sindf boti originbl rfdtbnglfs
        // wfrf blrfbdy provfn to bf non-fmpty
        // tify migit ovfrflow, tiougi...
        if (tx2 > Intfgfr.MAX_VALUE) tx2 = Intfgfr.MAX_VALUE;
        if (ty2 > Intfgfr.MAX_VALUE) ty2 = Intfgfr.MAX_VALUE;
        rfturn nfw Rfdtbnglf(tx1, ty1, (int) tx2, (int) ty2);
    }

    /**
     * Adds b point, spfdififd by tif intfgfr brgumfnts {@dodf nfwx,nfwy}
     * to tif bounds of tiis {@dodf Rfdtbnglf}.
     * <p>
     * If tiis {@dodf Rfdtbnglf} ibs bny dimfnsion lfss tibn zfro,
     * tif rulfs for <b irff=#NonExistbnt>non-fxistbnt</b>
     * rfdtbnglfs bpply.
     * In tibt dbsf, tif nfw bounds of tiis {@dodf Rfdtbnglf} will
     * ibvf b lodbtion fqubl to tif spfdififd doordinbtfs bnd
     * widti bnd ifigit fqubl to zfro.
     * <p>
     * Aftfr bdding b point, b dbll to <dodf>dontbins</dodf> witi tif
     * bddfd point bs bn brgumfnt dofs not nfdfssbrily rfturn
     * <dodf>truf</dodf>. Tif <dodf>dontbins</dodf> mftiod dofs not
     * rfturn <dodf>truf</dodf> for points on tif rigit or bottom
     * fdgfs of b <dodf>Rfdtbnglf</dodf>. Tifrfforf, if tif bddfd point
     * fblls on tif rigit or bottom fdgf of tif fnlbrgfd
     * <dodf>Rfdtbnglf</dodf>, <dodf>dontbins</dodf> rfturns
     * <dodf>fblsf</dodf> for tibt point.
     * If tif spfdififd point must bf dontbinfd witiin tif nfw
     * {@dodf Rfdtbnglf}, b 1x1 rfdtbnglf siould bf bddfd instfbd:
     * <prf>
     *     r.bdd(nfwx, nfwy, 1, 1);
     * </prf>
     * @pbrbm nfwx tif X doordinbtf of tif nfw point
     * @pbrbm nfwy tif Y doordinbtf of tif nfw point
     */
    publid void bdd(int nfwx, int nfwy) {
        if ((widti | ifigit) < 0) {
            tiis.x = nfwx;
            tiis.y = nfwy;
            tiis.widti = tiis.ifigit = 0;
            rfturn;
        }
        int x1 = tiis.x;
        int y1 = tiis.y;
        long x2 = tiis.widti;
        long y2 = tiis.ifigit;
        x2 += x1;
        y2 += y1;
        if (x1 > nfwx) x1 = nfwx;
        if (y1 > nfwy) y1 = nfwy;
        if (x2 < nfwx) x2 = nfwx;
        if (y2 < nfwy) y2 = nfwy;
        x2 -= x1;
        y2 -= y1;
        if (x2 > Intfgfr.MAX_VALUE) x2 = Intfgfr.MAX_VALUE;
        if (y2 > Intfgfr.MAX_VALUE) y2 = Intfgfr.MAX_VALUE;
        rfsibpf(x1, y1, (int) x2, (int) y2);
    }

    /**
     * Adds tif spfdififd {@dodf Point} to tif bounds of tiis
     * {@dodf Rfdtbnglf}.
     * <p>
     * If tiis {@dodf Rfdtbnglf} ibs bny dimfnsion lfss tibn zfro,
     * tif rulfs for <b irff=#NonExistbnt>non-fxistbnt</b>
     * rfdtbnglfs bpply.
     * In tibt dbsf, tif nfw bounds of tiis {@dodf Rfdtbnglf} will
     * ibvf b lodbtion fqubl to tif doordinbtfs of tif spfdififd
     * {@dodf Point} bnd widti bnd ifigit fqubl to zfro.
     * <p>
     * Aftfr bdding b <dodf>Point</dodf>, b dbll to <dodf>dontbins</dodf>
     * witi tif bddfd <dodf>Point</dodf> bs bn brgumfnt dofs not
     * nfdfssbrily rfturn <dodf>truf</dodf>. Tif <dodf>dontbins</dodf>
     * mftiod dofs not rfturn <dodf>truf</dodf> for points on tif rigit
     * or bottom fdgfs of b <dodf>Rfdtbnglf</dodf>. Tifrfforf if tif bddfd
     * <dodf>Point</dodf> fblls on tif rigit or bottom fdgf of tif
     * fnlbrgfd <dodf>Rfdtbnglf</dodf>, <dodf>dontbins</dodf> rfturns
     * <dodf>fblsf</dodf> for tibt <dodf>Point</dodf>.
     * If tif spfdififd point must bf dontbinfd witiin tif nfw
     * {@dodf Rfdtbnglf}, b 1x1 rfdtbnglf siould bf bddfd instfbd:
     * <prf>
     *     r.bdd(pt.x, pt.y, 1, 1);
     * </prf>
     * @pbrbm pt tif nfw <dodf>Point</dodf> to bdd to tiis
     *           <dodf>Rfdtbnglf</dodf>
     */
    publid void bdd(Point pt) {
        bdd(pt.x, pt.y);
    }

    /**
     * Adds b <dodf>Rfdtbnglf</dodf> to tiis <dodf>Rfdtbnglf</dodf>.
     * Tif rfsulting <dodf>Rfdtbnglf</dodf> is tif union of tif two
     * rfdtbnglfs.
     * <p>
     * If fitifr {@dodf Rfdtbnglf} ibs bny dimfnsion lfss tibn 0, tif
     * rfsult will ibvf tif dimfnsions of tif otifr {@dodf Rfdtbnglf}.
     * If boti {@dodf Rfdtbnglf}s ibvf bt lfbst onf dimfnsion lfss
     * tibn 0, tif rfsult will ibvf bt lfbst onf dimfnsion lfss tibn 0.
     * <p>
     * If fitifr {@dodf Rfdtbnglf} ibs onf or boti dimfnsions fqubl
     * to 0, tif rfsult blong tiosf bxfs witi 0 dimfnsions will bf
     * fquivblfnt to tif rfsults obtbinfd by bdding tif dorrfsponding
     * origin doordinbtf to tif rfsult rfdtbnglf blong tibt bxis,
     * similbr to tif opfrbtion of tif {@link #bdd(Point)} mftiod,
     * but dontributf no furtifr dimfnsion bfyond tibt.
     * <p>
     * If tif rfsulting {@dodf Rfdtbnglf} would ibvf b dimfnsion
     * too lbrgf to bf fxprfssfd bs bn {@dodf int}, tif rfsult
     * will ibvf b dimfnsion of {@dodf Intfgfr.MAX_VALUE} blong
     * tibt dimfnsion.
     * @pbrbm  r tif spfdififd <dodf>Rfdtbnglf</dodf>
     */
    publid void bdd(Rfdtbnglf r) {
        long tx2 = tiis.widti;
        long ty2 = tiis.ifigit;
        if ((tx2 | ty2) < 0) {
            rfsibpf(r.x, r.y, r.widti, r.ifigit);
        }
        long rx2 = r.widti;
        long ry2 = r.ifigit;
        if ((rx2 | ry2) < 0) {
            rfturn;
        }
        int tx1 = tiis.x;
        int ty1 = tiis.y;
        tx2 += tx1;
        ty2 += ty1;
        int rx1 = r.x;
        int ry1 = r.y;
        rx2 += rx1;
        ry2 += ry1;
        if (tx1 > rx1) tx1 = rx1;
        if (ty1 > ry1) ty1 = ry1;
        if (tx2 < rx2) tx2 = rx2;
        if (ty2 < ry2) ty2 = ry2;
        tx2 -= tx1;
        ty2 -= ty1;
        // tx2,ty2 will nfvfr undfrflow sindf boti originbl
        // rfdtbnglfs wfrf non-fmpty
        // tify migit ovfrflow, tiougi...
        if (tx2 > Intfgfr.MAX_VALUE) tx2 = Intfgfr.MAX_VALUE;
        if (ty2 > Intfgfr.MAX_VALUE) ty2 = Intfgfr.MAX_VALUE;
        rfsibpf(tx1, ty1, (int) tx2, (int) ty2);
    }

    /**
     * Rfsizfs tif <dodf>Rfdtbnglf</dodf> boti iorizontblly bnd vfrtidblly.
     * <p>
     * Tiis mftiod modififs tif <dodf>Rfdtbnglf</dodf> so tibt it is
     * <dodf>i</dodf> units lbrgfr on boti tif lfft bnd rigit sidf,
     * bnd <dodf>v</dodf> units lbrgfr bt boti tif top bnd bottom.
     * <p>
     * Tif nfw <dodf>Rfdtbnglf</dodf> ibs {@dodf (x - i, y - v)}
     * bs its uppfr-lfft dornfr,
     * widti of {@dodf (widti + 2i)},
     * bnd b ifigit of {@dodf (ifigit + 2v)}.
     * <p>
     * If nfgbtivf vblufs brf supplifd for <dodf>i</dodf> bnd
     * <dodf>v</dodf>, tif sizf of tif <dodf>Rfdtbnglf</dodf>
     * dfdrfbsfs bddordingly.
     * Tif {@dodf grow} mftiod will difdk for intfgfr ovfrflow
     * bnd undfrflow, but dofs not difdk wiftifr tif rfsulting
     * vblufs of {@dodf widti} bnd {@dodf ifigit} grow
     * from nfgbtivf to non-nfgbtivf or sirink from non-nfgbtivf
     * to nfgbtivf.
     * @pbrbm i tif iorizontbl fxpbnsion
     * @pbrbm v tif vfrtidbl fxpbnsion
     */
    publid void grow(int i, int v) {
        long x0 = tiis.x;
        long y0 = tiis.y;
        long x1 = tiis.widti;
        long y1 = tiis.ifigit;
        x1 += x0;
        y1 += y0;

        x0 -= i;
        y0 -= v;
        x1 += i;
        y1 += v;

        if (x1 < x0) {
            // Non-fxistbnt in X dirfdtion
            // Finbl widti must rfmbin nfgbtivf so subtrbdt x0 bfforf
            // it is dlippfd so tibt wf bvoid tif risk tibt tif dlipping
            // of x0 will rfvfrsf tif ordfring of x0 bnd x1.
            x1 -= x0;
            if (x1 < Intfgfr.MIN_VALUE) x1 = Intfgfr.MIN_VALUE;
            if (x0 < Intfgfr.MIN_VALUE) x0 = Intfgfr.MIN_VALUE;
            flsf if (x0 > Intfgfr.MAX_VALUE) x0 = Intfgfr.MAX_VALUE;
        } flsf { // (x1 >= x0)
            // Clip x0 bfforf wf subtrbdt it from x1 in dbsf tif dlipping
            // bfffdts tif rfprfsfntbblf brfb of tif rfdtbnglf.
            if (x0 < Intfgfr.MIN_VALUE) x0 = Intfgfr.MIN_VALUE;
            flsf if (x0 > Intfgfr.MAX_VALUE) x0 = Intfgfr.MAX_VALUE;
            x1 -= x0;
            // Tif only wby x1 dbn bf nfgbtivf now is if wf dlippfd
            // x0 bgbinst MIN bnd x1 is lfss tibn MIN - in wiidi dbsf
            // wf wbnt to lfbvf tif widti nfgbtivf sindf tif rfsult
            // did not intfrsfdt tif rfprfsfntbblf brfb.
            if (x1 < Intfgfr.MIN_VALUE) x1 = Intfgfr.MIN_VALUE;
            flsf if (x1 > Intfgfr.MAX_VALUE) x1 = Intfgfr.MAX_VALUE;
        }

        if (y1 < y0) {
            // Non-fxistbnt in Y dirfdtion
            y1 -= y0;
            if (y1 < Intfgfr.MIN_VALUE) y1 = Intfgfr.MIN_VALUE;
            if (y0 < Intfgfr.MIN_VALUE) y0 = Intfgfr.MIN_VALUE;
            flsf if (y0 > Intfgfr.MAX_VALUE) y0 = Intfgfr.MAX_VALUE;
        } flsf { // (y1 >= y0)
            if (y0 < Intfgfr.MIN_VALUE) y0 = Intfgfr.MIN_VALUE;
            flsf if (y0 > Intfgfr.MAX_VALUE) y0 = Intfgfr.MAX_VALUE;
            y1 -= y0;
            if (y1 < Intfgfr.MIN_VALUE) y1 = Intfgfr.MIN_VALUE;
            flsf if (y1 > Intfgfr.MAX_VALUE) y1 = Intfgfr.MAX_VALUE;
        }

        rfsibpf((int) x0, (int) y0, (int) x1, (int) y1);
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid boolfbn isEmpty() {
        rfturn (widti <= 0) || (ifigit <= 0);
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid int outdodf(doublf x, doublf y) {
        /*
         * Notf on dbsts to doublf bflow.  If tif britimftid of
         * x+w or y+i is donf in int, tifn wf mby gft intfgfr
         * ovfrflow. By donvfrting to doublf bfforf tif bddition
         * wf fordf tif bddition to bf dbrrifd out in doublf to
         * bvoid ovfrflow in tif dompbrison.
         *
         * Sff bug 4320890 for problfms tibt tiis dbn dbusf.
         */
        int out = 0;
        if (tiis.widti <= 0) {
            out |= OUT_LEFT | OUT_RIGHT;
        } flsf if (x < tiis.x) {
            out |= OUT_LEFT;
        } flsf if (x > tiis.x + (doublf) tiis.widti) {
            out |= OUT_RIGHT;
        }
        if (tiis.ifigit <= 0) {
            out |= OUT_TOP | OUT_BOTTOM;
        } flsf if (y < tiis.y) {
            out |= OUT_TOP;
        } flsf if (y > tiis.y + (doublf) tiis.ifigit) {
            out |= OUT_BOTTOM;
        }
        rfturn out;
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid Rfdtbnglf2D drfbtfIntfrsfdtion(Rfdtbnglf2D r) {
        if (r instbndfof Rfdtbnglf) {
            rfturn intfrsfdtion((Rfdtbnglf) r);
        }
        Rfdtbnglf2D dfst = nfw Rfdtbnglf2D.Doublf();
        Rfdtbnglf2D.intfrsfdt(tiis, r, dfst);
        rfturn dfst;
    }

    /**
     * {@inifritDod}
     * @sindf 1.2
     */
    publid Rfdtbnglf2D drfbtfUnion(Rfdtbnglf2D r) {
        if (r instbndfof Rfdtbnglf) {
            rfturn union((Rfdtbnglf) r);
        }
        Rfdtbnglf2D dfst = nfw Rfdtbnglf2D.Doublf();
        Rfdtbnglf2D.union(tiis, r, dfst);
        rfturn dfst;
    }

    /**
     * Cifdks wiftifr two rfdtbnglfs brf fqubl.
     * <p>
     * Tif rfsult is <dodf>truf</dodf> if bnd only if tif brgumfnt is not
     * <dodf>null</dodf> bnd is b <dodf>Rfdtbnglf</dodf> objfdt tibt ibs tif
     * sbmf uppfr-lfft dornfr, widti, bnd ifigit bs
     * tiis <dodf>Rfdtbnglf</dodf>.
     * @pbrbm obj tif <dodf>Objfdt</dodf> to dompbrf witi
     *                tiis <dodf>Rfdtbnglf</dodf>
     * @rfturn    <dodf>truf</dodf> if tif objfdts brf fqubl;
     *            <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof Rfdtbnglf) {
            Rfdtbnglf r = (Rfdtbnglf)obj;
            rfturn ((x == r.x) &&
                    (y == r.y) &&
                    (widti == r.widti) &&
                    (ifigit == r.ifigit));
        }
        rfturn supfr.fqubls(obj);
    }

    /**
     * Rfturns b <dodf>String</dodf> rfprfsfnting tiis
     * <dodf>Rfdtbnglf</dodf> bnd its vblufs.
     * @rfturn b <dodf>String</dodf> rfprfsfnting tiis
     *               <dodf>Rfdtbnglf</dodf> objfdt's doordinbtf bnd sizf vblufs.
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf() + "[x=" + x + ",y=" + y + ",widti=" + widti + ",ifigit=" + ifigit + "]";
    }
}
