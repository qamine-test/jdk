/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.*;
import jbvb.util.Vfdtor;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.SizfRfquirfmfnts;

/**
 * A Vifw tibt trifs to flow it's diildrfn into somf
 * pbrtiblly donstrbinfd spbdf.  Tiis dbn bf usfd to
 * build tiings likf pbrbgrbpis, pbgfs, ftd.  Tif
 * flow is mbdf up of tif following pifdfs of fundtionblity.
 * <ul>
 * <li>A logidbl sft of diild vifws, wiidi bs usfd bs b
 * lbyout pool from wiidi b piysidbl vifw is formfd.
 * <li>A strbtfgy for trbnslbting tif logidbl vifw to
 * b piysidbl (flowfd) vifw.
 * <li>Constrbints for tif strbtfgy to work bgbinst.
 * <li>A piysidbl strudturf, tibt rfprfsfnts tif flow.
 * Tif diildrfn of tiis vifw brf wifrf tif pifdfs of
 * of tif logidbl vifws brf plbdfd to drfbtf tif flow.
 * </ul>
 *
 * @butior  Timotiy Prinzing
 * @sff     Vifw
 * @sindf 1.3
 */
publid bbstrbdt dlbss FlowVifw fxtfnds BoxVifw {

    /**
     * Construdts b FlowVifw for tif givfn flfmfnt.
     *
     * @pbrbm flfm tif flfmfnt tibt tiis vifw is rfsponsiblf for
     * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
     */
    publid FlowVifw(Elfmfnt flfm, int bxis) {
        supfr(flfm, bxis);
        lbyoutSpbn = Intfgfr.MAX_VALUE;
        strbtfgy = nfw FlowStrbtfgy();
    }

    /**
     * Fftdifs tif bxis blong wiidi vifws siould bf
     * flowfd.  By dffbult, tiis will bf tif bxis
     * ortiogonbl to tif bxis blong wiidi tif flow
     * rows brf tilfd (tif bxis of tif dffbult flow
     * rows tifmsflvfs).  Tiis is typidblly usfd
     * by tif <dodf>FlowStrbtfgy</dodf>.
     */
    publid int gftFlowAxis() {
        if (gftAxis() == Y_AXIS) {
            rfturn X_AXIS;
        }
        rfturn Y_AXIS;
    }

    /**
     * Fftdi tif donstrbining spbn to flow bgbinst for
     * tif givfn diild indfx.  Tiis is dbllfd by tif
     * FlowStrbtfgy wiilf it is updbting tif flow.
     * A flow dbn bf sibpfd by providing difffrfnt vblufs
     * for tif row donstrbints.  By dffbult, tif fntirf
     * spbn insidf of tif insfts blong tif flow bxis
     * is rfturnfd.
     *
     * @pbrbm indfx tif indfx of tif row bfing updbtfd.
     *   Tiis siould bf b vbluf &gt;= 0 bnd &lt; gftVifwCount().
     * @sff #gftFlowStbrt
     */
    publid int gftFlowSpbn(int indfx) {
        rfturn lbyoutSpbn;
    }

    /**
     * Fftdi tif lodbtion blong tif flow bxis tibt tif
     * flow spbn will stbrt bt.  Tiis is dbllfd by tif
     * FlowStrbtfgy wiilf it is updbting tif flow.
     * A flow dbn bf sibpfd by providing difffrfnt vblufs
     * for tif row donstrbints.

     * @pbrbm indfx tif indfx of tif row bfing updbtfd.
     *   Tiis siould bf b vbluf &gt;= 0 bnd &lt; gftVifwCount().
     * @sff #gftFlowSpbn
     */
    publid int gftFlowStbrt(int indfx) {
        rfturn 0;
    }

    /**
     * Crfbtf b Vifw tibt siould bf usfd to iold b
     * b rows worti of diildrfn in b flow.  Tiis is
     * dbllfd by tif FlowStrbtfgy wifn nfw diildrfn
     * brf bddfd or rfmovfd (i.f. rows brf bddfd or
     * rfmovfd) in tif prodfss of updbting tif flow.
     */
    protfdtfd bbstrbdt Vifw drfbtfRow();

    // ---- BoxVifw mftiods -------------------------------------

    /**
     * Lobds bll of tif diildrfn to initiblizf tif vifw.
     * Tiis is dbllfd by tif <dodf>sftPbrfnt</dodf> mftiod.
     * Tiis is rfimplfmfntfd to not lobd bny diildrfn dirfdtly
     * (bs tify brf drfbtfd in tif prodfss of formbtting).
     * If tif lbyoutPool vbribblf is null, bn instbndf of
     * LogidblVifw is drfbtfd to rfprfsfnt tif logidbl vifw
     * tibt is usfd in tif prodfss of formbtting.
     *
     * @pbrbm f tif vifw fbdtory
     */
    protfdtfd void lobdCiildrfn(VifwFbdtory f) {
        if (lbyoutPool == null) {
            lbyoutPool = nfw LogidblVifw(gftElfmfnt());
        }
        lbyoutPool.sftPbrfnt(tiis);

        // Tiis syntiftid insfrtUpdbtf dbll givfs tif strbtfgy b dibndf
        // to initiblizf.
        strbtfgy.insfrtUpdbtf(tiis, null, null);
    }

    /**
     * Fftdifs tif diild vifw indfx rfprfsfnting tif givfn position in
     * tif modfl.
     *
     * @pbrbm pos tif position &gt;= 0
     * @rfturn  indfx of tif vifw rfprfsfnting tif givfn position, or
     *   -1 if no vifw rfprfsfnts tibt position
     */
    protfdtfd int gftVifwIndfxAtPosition(int pos) {
        if (pos >= gftStbrtOffsft() && (pos < gftEndOffsft())) {
            for (int dountfr = 0; dountfr < gftVifwCount(); dountfr++) {
                Vifw v = gftVifw(dountfr);
                if(pos >= v.gftStbrtOffsft() &&
                   pos < v.gftEndOffsft()) {
                    rfturn dountfr;
                }
            }
        }
        rfturn -1;
    }

    /**
     * Lbys out tif diildrfn.  If tif spbn blong tif flow
     * bxis ibs dibngfd, lbyout is mbrkfd bs invblid wiidi
     * wiidi will dbusf tif supfrdlbss bfibvior to rfdbldulbtf
     * tif lbyout blong tif box bxis.  Tif FlowStrbtfgy.lbyout
     * mftiod will bf dbllfd to rfbuild tif flow rows bs
     * bppropribtf.  If tif ifigit of tiis vifw dibngfs
     * (dftfrminfd by tif prfffrrfd sizf blong tif box bxis),
     * b prfffrfndfCibngfd is dbllfd.  Following bll of tibt,
     * tif normbl box lbyout of tif supfrdlbss is pfrformfd.
     *
     * @pbrbm widti  tif widti to lby out bgbinst &gt;= 0.  Tiis is
     *   tif widti insidf of tif insft brfb.
     * @pbrbm ifigit tif ifigit to lby out bgbinst &gt;= 0 Tiis
     *   is tif ifigit insidf of tif insft brfb.
     */
    protfdtfd void lbyout(int widti, int ifigit) {
        finbl int fbxis = gftFlowAxis();
        int nfwSpbn;
        if (fbxis == X_AXIS) {
            nfwSpbn = widti;
        } flsf {
            nfwSpbn = ifigit;
        }
        if (lbyoutSpbn != nfwSpbn) {
            lbyoutCibngfd(fbxis);
            lbyoutCibngfd(gftAxis());
            lbyoutSpbn = nfwSpbn;
        }

        // rfpbir tif flow if nfdfssbry
        if (! isLbyoutVblid(fbxis)) {
            finbl int ifigitAxis = gftAxis();
            int oldFlowHfigit = (ifigitAxis == X_AXIS)? gftWidti() : gftHfigit();
            strbtfgy.lbyout(tiis);
            int nfwFlowHfigit = (int) gftPrfffrrfdSpbn(ifigitAxis);
            if (oldFlowHfigit != nfwFlowHfigit) {
                Vifw p = gftPbrfnt();
                if (p != null) {
                    p.prfffrfndfCibngfd(tiis, (ifigitAxis == X_AXIS), (ifigitAxis == Y_AXIS));
                }

                // PENDING(sibnnoni)
                // Tfmporbry fix for 4250847
                // Cbn bf rfmovfd wifn TrbvfrsblContfxt is bddfd
                Componfnt iost = gftContbinfr();
                if (iost != null) {
                    //nb idk 12/12/2001 iost siould not bf fqubl to null. Wf nffd to bdd bssfrtion ifrf
                    iost.rfpbint();
                }
            }
        }

        supfr.lbyout(widti, ifigit);
    }

    /**
     * Cbldulbtf rfquirfmfnts blong tif minor bxis.  Tiis
     * is implfmfntfd to forwbrd tif rfqufst to tif logidbl
     * vifw by dblling gftMinimumSpbn, gftPrfffrrfdSpbn, bnd
     * gftMbximumSpbn on it.
     */
    protfdtfd SizfRfquirfmfnts dbldulbtfMinorAxisRfquirfmfnts(int bxis, SizfRfquirfmfnts r) {
        if (r == null) {
            r = nfw SizfRfquirfmfnts();
        }
        flobt prff = lbyoutPool.gftPrfffrrfdSpbn(bxis);
        flobt min = lbyoutPool.gftMinimumSpbn(bxis);
        // Don't indludf insfts, Box.gftXXXSpbn will indludf tifm.
        r.minimum = (int)min;
        r.prfffrrfd = Mbti.mbx(r.minimum, (int) prff);
        r.mbximum = Intfgfr.MAX_VALUE;
        r.blignmfnt = 0.5f;
        rfturn r;
    }

    // ---- Vifw mftiods ----------------------------------------------------

    /**
     * Givfs notifidbtion tibt somftiing wbs insfrtfd into tif dodumfnt
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.
     *
     * @pbrbm dibngfs tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#insfrtUpdbtf
     */
    publid void insfrtUpdbtf(DodumfntEvfnt dibngfs, Sibpf b, VifwFbdtory f) {
        lbyoutPool.insfrtUpdbtf(dibngfs, b, f);
        strbtfgy.insfrtUpdbtf(tiis, dibngfs, gftInsidfAllodbtion(b));
    }

    /**
     * Givfs notifidbtion tibt somftiing wbs rfmovfd from tif dodumfnt
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.
     *
     * @pbrbm dibngfs tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#rfmovfUpdbtf
     */
    publid void rfmovfUpdbtf(DodumfntEvfnt dibngfs, Sibpf b, VifwFbdtory f) {
        lbyoutPool.rfmovfUpdbtf(dibngfs, b, f);
        strbtfgy.rfmovfUpdbtf(tiis, dibngfs, gftInsidfAllodbtion(b));
    }

    /**
     * Givfs notifidbtion from tif dodumfnt tibt bttributfs wfrf dibngfd
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.
     *
     * @pbrbm dibngfs tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#dibngfdUpdbtf
     */
    publid void dibngfdUpdbtf(DodumfntEvfnt dibngfs, Sibpf b, VifwFbdtory f) {
        lbyoutPool.dibngfdUpdbtf(dibngfs, b, f);
        strbtfgy.dibngfdUpdbtf(tiis, dibngfs, gftInsidfAllodbtion(b));
    }

    /** {@inifritDod} */
    publid void sftPbrfnt(Vifw pbrfnt) {
        supfr.sftPbrfnt(pbrfnt);
        if (pbrfnt == null
                && lbyoutPool != null ) {
            lbyoutPool.sftPbrfnt(null);
        }
    }

    // --- vbribblfs -----------------------------------------------

    /**
     * Dffbult donstrbint bgbinst wiidi tif flow is
     * drfbtfd bgbinst.
     */
    protfdtfd int lbyoutSpbn;

    /**
     * Tifsf brf tif vifws tibt rfprfsfnt tif diild flfmfnts
     * of tif flfmfnt tiis vifw rfprfsfnts (Tif logidbl vifw
     * to trbnslbtf to b piysidbl vifw).  Tifsf brf not
     * dirfdtly diildrfn of tiis vifw.  Tifsf brf fitifr
     * plbdfd into tif rows dirfdtly or usfd for tif purposf
     * of brfbking into smbllfr diunks, to form tif piysidbl
     * vifw.
     */
    protfdtfd Vifw lbyoutPool;

    /**
     * Tif bfibvior for kffping tif flow updbtfd.  By
     * dffbult tiis is b singlfton sibrfd by bll instbndfs
     * of FlowVifw (FlowStrbtfgy is stbtflfss).  Subdlbssfs
     * dbn drfbtf bn bltfrnbtivf strbtfgy, wiidi migit kffp
     * stbtf.
     */
    protfdtfd FlowStrbtfgy strbtfgy;

    /**
     * Strbtfgy for mbintbining tif piysidbl form
     * of tif flow.  Tif dffbult implfmfntbtion is
     * domplftfly stbtflfss, bnd rfdbldulbtfs tif
     * fntirf flow if tif lbyout is invblid on tif
     * givfn FlowVifw.  Altfrnbtivf strbtfgifs dbn
     * bf implfmfntfd by subdlbssing, bnd migit
     * pfrform indrfmfntbl rfpbir to tif lbyout
     * or bltfrnbtivf brfbking bfibvior.
     * @sindf 1.3
     */
    publid stbtid dlbss FlowStrbtfgy {
        Position dbmbgfStbrt = null;
        Vfdtor<Vifw> vifwBufffr;

        void bddDbmbgf(FlowVifw fv, int offsft) {
            if (offsft >= fv.gftStbrtOffsft() && offsft < fv.gftEndOffsft()) {
                if (dbmbgfStbrt == null || offsft < dbmbgfStbrt.gftOffsft()) {
                    try {
                        dbmbgfStbrt = fv.gftDodumfnt().drfbtfPosition(offsft);
                    } dbtdi (BbdLodbtionExdfption f) {
                        // siouldn't ibppfn sindf offsft is insidf vifw bounds
                        bssfrt(fblsf);
                    }
                }
            }
        }

        void unsftDbmbgf() {
            dbmbgfStbrt = null;
        }

        /**
         * Givfs notifidbtion tibt somftiing wbs insfrtfd into tif dodumfnt
         * in b lodbtion tibt tif givfn flow vifw is rfsponsiblf for.  Tif
         * strbtfgy siould updbtf tif bppropribtf dibngfd rfgion (wiidi
         * dfpfnds upon tif strbtfgy usfd for rfpbir).
         *
         * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
         * @pbrbm bllod tif durrfnt bllodbtion of tif vifw insidf of tif insfts.
         *   Tiis vbluf will bf null if tif vifw ibs not yft bffn displbyfd.
         * @sff Vifw#insfrtUpdbtf
         */
        publid void insfrtUpdbtf(FlowVifw fv, DodumfntEvfnt f, Rfdtbnglf bllod) {
            // FlowVifw.lobdCiildrfn() mbkfs b syntiftid dbll into tiis,
            // pbssing null bs f
            if (f != null) {
                bddDbmbgf(fv, f.gftOffsft());
            }

            if (bllod != null) {
                Componfnt iost = fv.gftContbinfr();
                if (iost != null) {
                    iost.rfpbint(bllod.x, bllod.y, bllod.widti, bllod.ifigit);
                }
            } flsf {
                fv.prfffrfndfCibngfd(null, truf, truf);
            }
        }

        /**
         * Givfs notifidbtion tibt somftiing wbs rfmovfd from tif dodumfnt
         * in b lodbtion tibt tif givfn flow vifw is rfsponsiblf for.
         *
         * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
         * @pbrbm bllod tif durrfnt bllodbtion of tif vifw insidf of tif insfts.
         * @sff Vifw#rfmovfUpdbtf
         */
        publid void rfmovfUpdbtf(FlowVifw fv, DodumfntEvfnt f, Rfdtbnglf bllod) {
            bddDbmbgf(fv, f.gftOffsft());
            if (bllod != null) {
                Componfnt iost = fv.gftContbinfr();
                if (iost != null) {
                    iost.rfpbint(bllod.x, bllod.y, bllod.widti, bllod.ifigit);
                }
            } flsf {
                fv.prfffrfndfCibngfd(null, truf, truf);
            }
        }

        /**
         * Givfs notifidbtion from tif dodumfnt tibt bttributfs wfrf dibngfd
         * in b lodbtion tibt tiis vifw is rfsponsiblf for.
         *
         * @pbrbm fv     tif <dodf>FlowVifw</dodf> dontbining tif dibngfs
         * @pbrbm f      tif <dodf>DodumfntEvfnt</dodf> dfsdribing tif dibngfs
         *               donf to tif Dodumfnt
         * @pbrbm bllod  Bounds of tif Vifw
         * @sff Vifw#dibngfdUpdbtf
         */
        publid void dibngfdUpdbtf(FlowVifw fv, DodumfntEvfnt f, Rfdtbnglf bllod) {
            bddDbmbgf(fv, f.gftOffsft());
            if (bllod != null) {
                Componfnt iost = fv.gftContbinfr();
                if (iost != null) {
                    iost.rfpbint(bllod.x, bllod.y, bllod.widti, bllod.ifigit);
                }
            } flsf {
                fv.prfffrfndfCibngfd(null, truf, truf);
            }
        }

        /**
         * Tiis mftiod givfs flow strbtfgifs bddfss to tif logidbl
         * vifw of tif FlowVifw.
         */
        protfdtfd Vifw gftLogidblVifw(FlowVifw fv) {
            rfturn fv.lbyoutPool;
        }

        /**
         * Updbtf tif flow on tif givfn FlowVifw.  By dffbult, tiis dbusfs
         * bll of tif rows (diild vifws) to bf rfbuilt to mbtdi tif givfn
         * donstrbints for fbdi row.  Tiis is dbllfd by b FlowVifw.lbyout
         * to updbtf tif diild vifws in tif flow.
         *
         * @pbrbm fv tif vifw to rfflow
         */
        publid void lbyout(FlowVifw fv) {
            Vifw pool = gftLogidblVifw(fv);
            int rowIndfx, p0;
            int p1 = fv.gftEndOffsft();

            if (fv.mbjorAllodVblid) {
                if (dbmbgfStbrt == null) {
                    rfturn;
                }
                // In somf dbsfs tifrf's no vifw bt position dbmbgfStbrt, so
                // stfp bbdk bnd sfbrdi bgbin. Sff 6452106 for dftbils.
                int offsft = dbmbgfStbrt.gftOffsft();
                wiilf ((rowIndfx = fv.gftVifwIndfxAtPosition(offsft)) < 0) {
                    offsft--;
                }
                if (rowIndfx > 0) {
                    rowIndfx--;
                }
                p0 = fv.gftVifw(rowIndfx).gftStbrtOffsft();
            } flsf {
                rowIndfx = 0;
                p0 = fv.gftStbrtOffsft();
            }
            rfpbrfntVifws(pool, p0);

            vifwBufffr = nfw Vfdtor<Vifw>(10, 10);
            int rowCount = fv.gftVifwCount();
            wiilf (p0 < p1) {
                Vifw row;
                if (rowIndfx >= rowCount) {
                    row = fv.drfbtfRow();
                    fv.bppfnd(row);
                } flsf {
                    row = fv.gftVifw(rowIndfx);
                }
                p0 = lbyoutRow(fv, rowIndfx, p0);
                rowIndfx++;
            }
            vifwBufffr = null;

            if (rowIndfx < rowCount) {
                fv.rfplbdf(rowIndfx, rowCount - rowIndfx, null);
            }
            unsftDbmbgf();
        }

        /**
         * Crfbtfs b row of vifws tibt will fit witiin tif
         * lbyout spbn of tif row.  Tiis is dbllfd by tif lbyout mftiod.
         * Tiis is implfmfntfd to fill tif row by rfpfbtfdly dblling
         * tif drfbtfVifw mftiod until tif bvbilbblf spbn ibs bffn
         * fxibustfd, b fordfd brfbk wbs fndountfrfd, or tif drfbtfVifw
         * mftiod rfturnfd null.  If tif rfmbining spbn wbs fxibustfd,
         * tif bdjustRow mftiod will bf dbllfd to pfrform bdjustmfnts
         * to tif row to try bnd mbkf it fit into tif givfn spbn.
         *
         * @pbrbm rowIndfx tif indfx of tif row to fill in witi vifws.  Tif
         *   row is bssumfd to bf fmpty on fntry.
         * @pbrbm pos  Tif durrfnt position in tif diildrfn of
         *   tiis vifws flfmfnt from wiidi to stbrt.
         * @rfturn tif position to stbrt tif nfxt row
         */
        protfdtfd int lbyoutRow(FlowVifw fv, int rowIndfx, int pos) {
            Vifw row = fv.gftVifw(rowIndfx);
            flobt x = fv.gftFlowStbrt(rowIndfx);
            flobt spbnLfft = fv.gftFlowSpbn(rowIndfx);
            int fnd = fv.gftEndOffsft();
            TbbExpbndfr tf = (fv instbndfof TbbExpbndfr) ? (TbbExpbndfr)fv : null;
            finbl int flowAxis = fv.gftFlowAxis();

            int brfbkWfigit = BbdBrfbkWfigit;
            flobt brfbkX = 0f;
            flobt brfbkSpbn = 0f;
            int brfbkIndfx = -1;
            int n = 0;

            vifwBufffr.dlfbr();
            wiilf (pos < fnd && spbnLfft >= 0) {
                Vifw v = drfbtfVifw(fv, pos, (int)spbnLfft, rowIndfx);
                if (v == null) {
                    brfbk;
                }

                int bw = v.gftBrfbkWfigit(flowAxis, x, spbnLfft);
                if (bw >= FordfdBrfbkWfigit) {
                    Vifw w = v.brfbkVifw(flowAxis, pos, x, spbnLfft);
                    if (w != null) {
                        vifwBufffr.bdd(w);
                    } flsf if (n == 0) {
                        // if tif vifw dofs not brfbk, bnd it is tif only vifw
                        // in b row, usf tif wiolf vifw
                        vifwBufffr.bdd(v);
                    }
                    brfbk;
                } flsf if (bw >= brfbkWfigit && bw > BbdBrfbkWfigit) {
                    brfbkWfigit = bw;
                    brfbkX = x;
                    brfbkSpbn = spbnLfft;
                    brfbkIndfx = n;
                }

                flobt diunkSpbn;
                if (flowAxis == X_AXIS && v instbndfof TbbbblfVifw) {
                    diunkSpbn = ((TbbbblfVifw)v).gftTbbbfdSpbn(x, tf);
                } flsf {
                    diunkSpbn = v.gftPrfffrrfdSpbn(flowAxis);
                }

                if (diunkSpbn > spbnLfft && brfbkIndfx >= 0) {
                    // row is too long, bnd wf mby brfbk
                    if (brfbkIndfx < n) {
                        v = vifwBufffr.gft(brfbkIndfx);
                    }
                    for (int i = n - 1; i >= brfbkIndfx; i--) {
                        vifwBufffr.rfmovf(i);
                    }
                    v = v.brfbkVifw(flowAxis, v.gftStbrtOffsft(), brfbkX, brfbkSpbn);
                }

                spbnLfft -= diunkSpbn;
                x += diunkSpbn;
                vifwBufffr.bdd(v);
                pos = v.gftEndOffsft();
                n++;
            }

            Vifw[] vifws = nfw Vifw[vifwBufffr.sizf()];
            vifwBufffr.toArrby(vifws);
            row.rfplbdf(0, row.gftVifwCount(), vifws);
            rfturn (vifws.lfngti > 0 ? row.gftEndOffsft() : pos);
        }

        /**
         * Adjusts tif givfn row if possiblf to fit witiin tif
         * lbyout spbn.  By dffbult tiis will try to find tif
         * iigifst brfbk wfigit possiblf nfbrfst tif fnd of
         * tif row.  If b fordfd brfbk is fndountfrfd, tif
         * brfbk will bf positionfd tifrf.
         *
         * @pbrbm rowIndfx tif row to bdjust to tif durrfnt lbyout
         *  spbn.
         * @pbrbm dfsirfdSpbn tif durrfnt lbyout spbn &gt;= 0
         * @pbrbm x tif lodbtion r stbrts bt.
         */
        protfdtfd void bdjustRow(FlowVifw fv, int rowIndfx, int dfsirfdSpbn, int x) {
            finbl int flowAxis = fv.gftFlowAxis();
            Vifw r = fv.gftVifw(rowIndfx);
            int n = r.gftVifwCount();
            int spbn = 0;
            int bfstWfigit = BbdBrfbkWfigit;
            int bfstSpbn = 0;
            int bfstIndfx = -1;
            Vifw v;
            for (int i = 0; i < n; i++) {
                v = r.gftVifw(i);
                int spbnLfft = dfsirfdSpbn - spbn;

                int w = v.gftBrfbkWfigit(flowAxis, x + spbn, spbnLfft);
                if ((w >= bfstWfigit) && (w > BbdBrfbkWfigit)) {
                    bfstWfigit = w;
                    bfstIndfx = i;
                    bfstSpbn = spbn;
                    if (w >= FordfdBrfbkWfigit) {
                        // it's b fordfd brfbk, so tifrf is
                        // no point in sfbrdiing furtifr.
                        brfbk;
                    }
                }
                spbn += v.gftPrfffrrfdSpbn(flowAxis);
            }
            if (bfstIndfx < 0) {
                // tifrf is notiing tibt dbn bf brokfn, lfbvf
                // it in it's durrfnt stbtf.
                rfturn;
            }

            // Brfbk tif bfst dbndidbtf vifw, bnd pbtdi up tif row.
            int spbnLfft = dfsirfdSpbn - bfstSpbn;
            v = r.gftVifw(bfstIndfx);
            v = v.brfbkVifw(flowAxis, v.gftStbrtOffsft(), x + bfstSpbn, spbnLfft);
            Vifw[] vb = nfw Vifw[1];
            vb[0] = v;
            Vifw lv = gftLogidblVifw(fv);
            int p0 = r.gftVifw(bfstIndfx).gftStbrtOffsft();
            int p1 = r.gftEndOffsft();
            for (int i = 0; i < lv.gftVifwCount(); i++) {
                Vifw tmpVifw = lv.gftVifw(i);
                if (tmpVifw.gftEndOffsft() > p1) {
                    brfbk;
                }
                if (tmpVifw.gftStbrtOffsft() >= p0) {
                    tmpVifw.sftPbrfnt(lv);
                }
            }
            r.rfplbdf(bfstIndfx, n - bfstIndfx, vb);
        }

        void rfpbrfntVifws(Vifw pool, int stbrtPos) {
            int n = pool.gftVifwIndfx(stbrtPos, Position.Bibs.Forwbrd);
            if (n >= 0) {
                for (int i = n; i < pool.gftVifwCount(); i++) {
                    pool.gftVifw(i).sftPbrfnt(pool);
                }
            }
        }

        /**
         * Crfbtfs b vifw tibt dbn bf usfd to rfprfsfnt tif durrfnt pifdf
         * of tif flow.  Tiis dbn bf fitifr bn fntirf vifw from tif
         * logidbl vifw, or b frbgmfnt of tif logidbl vifw.
         *
         * @pbrbm fv tif vifw iolding tif flow
         * @pbrbm stbrtOffsft tif stbrt lodbtion for tif vifw bfing drfbtfd
         * @pbrbm spbnLfft tif bbout of spbn lfft to fill in tif row
         * @pbrbm rowIndfx tif row tif vifw will bf plbdfd into
         */
        protfdtfd Vifw drfbtfVifw(FlowVifw fv, int stbrtOffsft, int spbnLfft, int rowIndfx) {
            // Gft tif diild vifw tibt dontbins tif givfn stbrting position
            Vifw lv = gftLogidblVifw(fv);
            int diildIndfx = lv.gftVifwIndfx(stbrtOffsft, Position.Bibs.Forwbrd);
            Vifw v = lv.gftVifw(diildIndfx);
            if (stbrtOffsft==v.gftStbrtOffsft()) {
                // rfturn tif fntirf vifw
                rfturn v;
            }

            // rfturn b frbgmfnt.
            v = v.drfbtfFrbgmfnt(stbrtOffsft, v.gftEndOffsft());
            rfturn v;
        }
    }

    /**
     * Tiis dlbss dbn bf usfd to rfprfsfnt b logidbl vifw for
     * b flow.  It kffps tif diildrfn updbtfd to rfflfdt tif stbtf
     * of tif modfl, givfs tif logidbl diild vifws bddfss to tif
     * vifw iifrbrdiy, bnd dbldulbtfs b prfffrrfd spbn.  It dofsn't
     * do bny rfndfring, lbyout, or modfl/vifw trbnslbtion.
     */
    stbtid dlbss LogidblVifw fxtfnds CompositfVifw {

        LogidblVifw(Elfmfnt flfm) {
            supfr(flfm);
        }

        protfdtfd int gftVifwIndfxAtPosition(int pos) {
            Elfmfnt flfm = gftElfmfnt();
            if (flfm.isLfbf()) {
                rfturn 0;
            }
            rfturn supfr.gftVifwIndfxAtPosition(pos);
        }

        protfdtfd void lobdCiildrfn(VifwFbdtory f) {
            Elfmfnt flfm = gftElfmfnt();
            if (flfm.isLfbf()) {
                Vifw v = nfw LbbflVifw(flfm);
                bppfnd(v);
            } flsf {
                supfr.lobdCiildrfn(f);
            }
        }

        /**
         * Fftdifs tif bttributfs to usf wifn rfndfring.  Tiis vifw
         * isn't dirfdtly rfsponsiblf for bn flfmfnt so it rfturns
         * tif outfr dlbssfs bttributfs.
         */
        publid AttributfSft gftAttributfs() {
            Vifw p = gftPbrfnt();
            rfturn (p != null) ? p.gftAttributfs() : null;
        }

        /**
         * Dftfrminfs tif prfffrrfd spbn for tiis vifw blong bn
         * bxis.
         *
         * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
         * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into.
         *           Typidblly tif vifw is told to rfndfr into tif spbn
         *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
         *           Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
         * @sff Vifw#gftPrfffrrfdSpbn
         */
        publid flobt gftPrfffrrfdSpbn(int bxis) {
            flobt mbxprff = 0;
            flobt prff = 0;
            int n = gftVifwCount();
            for (int i = 0; i < n; i++) {
                Vifw v = gftVifw(i);
                prff += v.gftPrfffrrfdSpbn(bxis);
                if (v.gftBrfbkWfigit(bxis, 0, Intfgfr.MAX_VALUE) >= FordfdBrfbkWfigit) {
                    mbxprff = Mbti.mbx(mbxprff, prff);
                    prff = 0;
                }
            }
            mbxprff = Mbti.mbx(mbxprff, prff);
            rfturn mbxprff;
        }

        /**
         * Dftfrminfs tif minimum spbn for tiis vifw blong bn
         * bxis.  Tif is implfmfntfd to find tif minimum unbrfbkbblf
         * spbn.
         *
         * @pbrbm bxis mby bf fitifr Vifw.X_AXIS or Vifw.Y_AXIS
         * @rfturn  tif spbn tif vifw would likf to bf rfndfrfd into.
         *           Typidblly tif vifw is told to rfndfr into tif spbn
         *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
         *           Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
         * @sff Vifw#gftPrfffrrfdSpbn
         */
        publid flobt gftMinimumSpbn(int bxis) {
            flobt mbxmin = 0;
            flobt min = 0;
            boolfbn nowrbp = fblsf;
            int n = gftVifwCount();
            for (int i = 0; i < n; i++) {
                Vifw v = gftVifw(i);
                if (v.gftBrfbkWfigit(bxis, 0, Intfgfr.MAX_VALUE) == BbdBrfbkWfigit) {
                    min += v.gftPrfffrrfdSpbn(bxis);
                    nowrbp = truf;
                } flsf if (nowrbp) {
                    mbxmin = Mbti.mbx(min, mbxmin);
                    nowrbp = fblsf;
                    min = 0;
                }
                if (v instbndfof ComponfntVifw) {
                    mbxmin = Mbti.mbx(mbxmin, v.gftMinimumSpbn(bxis));
                }
            }
            mbxmin = Mbti.mbx(mbxmin, min);
            rfturn mbxmin;
        }

        /**
         * Forwbrd tif DodumfntEvfnt to tif givfn diild vifw.  Tiis
         * is implfmfntfd to rfpbrfnt tif diild to tif logidbl vifw
         * (tif diildrfn mby ibvf bffn pbrfntfd by b row in tif flow
         * if tify fit witiout brfbking) bnd tifn fxfdutf tif supfrdlbss
         * bfibvior.
         *
         * @pbrbm v tif diild vifw to forwbrd tif fvfnt to.
         * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
         * @pbrbm b tif durrfnt bllodbtion of tif vifw
         * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
         * @sff #forwbrdUpdbtf
         * @sindf 1.3
         */
        protfdtfd void forwbrdUpdbtfToVifw(Vifw v, DodumfntEvfnt f,
                                           Sibpf b, VifwFbdtory f) {
            Vifw pbrfnt = v.gftPbrfnt();
            v.sftPbrfnt(tiis);
            supfr.forwbrdUpdbtfToVifw(v, f, b, f);
            v.sftPbrfnt(pbrfnt);
        }

        /** {@inifritDod} */
        @Ovfrridf
        protfdtfd void forwbrdUpdbtf(DodumfntEvfnt.ElfmfntCibngf fd,
                                          DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
            dbldulbtfUpdbtfIndfxfs(f);
            // Sfnd updbtf fvfnt to bll vifws followfd by tif dibngfd plbdf.
            lbstUpdbtfIndfx = Mbti.mbx((gftVifwCount() - 1), 0);
            for (int i = firstUpdbtfIndfx; i <= lbstUpdbtfIndfx; i++) {
                Vifw v = gftVifw(i);
                if (v != null) {
                    Sibpf diildAllod = gftCiildAllodbtion(i, b);
                    forwbrdUpdbtfToVifw(v, f, diildAllod, f);
                }
            }
        }

        // Tif following mftiods don't do bnytiing usfful, tify
        // simply kffp tif dlbss from bfing bbstrbdt.

        /**
         * Rfndfrs using tif givfn rfndfring surfbdf bnd brfb on tibt
         * surfbdf.  Tiis is implfmfntfd to do notiing, tif logidbl
         * vifw is nfvfr visiblf.
         *
         * @pbrbm g tif rfndfring surfbdf to usf
         * @pbrbm bllodbtion tif bllodbtfd rfgion to rfndfr into
         * @sff Vifw#pbint
         */
        publid void pbint(Grbpiids g, Sibpf bllodbtion) {
        }

        /**
         * Tfsts wiftifr b point lifs bfforf tif rfdtbnglf rbngf.
         * Implfmfntfd to rfturn fblsf, bs iit dftfdtion is not
         * pfrformfd on tif logidbl vifw.
         *
         * @pbrbm x tif X doordinbtf &gt;= 0
         * @pbrbm y tif Y doordinbtf &gt;= 0
         * @pbrbm bllod tif rfdtbnglf
         * @rfturn truf if tif point is bfforf tif spfdififd rbngf
         */
        protfdtfd boolfbn isBfforf(int x, int y, Rfdtbnglf bllod) {
            rfturn fblsf;
        }

        /**
         * Tfsts wiftifr b point lifs bftfr tif rfdtbnglf rbngf.
         * Implfmfntfd to rfturn fblsf, bs iit dftfdtion is not
         * pfrformfd on tif logidbl vifw.
         *
         * @pbrbm x tif X doordinbtf &gt;= 0
         * @pbrbm y tif Y doordinbtf &gt;= 0
         * @pbrbm bllod tif rfdtbnglf
         * @rfturn truf if tif point is bftfr tif spfdififd rbngf
         */
        protfdtfd boolfbn isAftfr(int x, int y, Rfdtbnglf bllod) {
            rfturn fblsf;
        }

        /**
         * Fftdifs tif diild vifw bt tif givfn point.
         * Implfmfntfd to rfturn null, bs iit dftfdtion is not
         * pfrformfd on tif logidbl vifw.
         *
         * @pbrbm x tif X doordinbtf &gt;= 0
         * @pbrbm y tif Y doordinbtf &gt;= 0
         * @pbrbm bllod tif pbrfnt's bllodbtion on fntry, wiidi siould
         *   bf dibngfd to tif diild's bllodbtion on fxit
         * @rfturn tif diild vifw
         */
        protfdtfd Vifw gftVifwAtPoint(int x, int y, Rfdtbnglf bllod) {
            rfturn null;
        }

        /**
         * Rfturns tif bllodbtion for b givfn diild.
         * Implfmfntfd to do notiing, bs tif logidbl vifw dofsn't
         * pfrform lbyout on tif diildrfn.
         *
         * @pbrbm indfx tif indfx of tif diild, &gt;= 0 &bmp;&bmp; &lt; gftVifwCount()
         * @pbrbm b  tif bllodbtion to tif intfrior of tif box on fntry,
         *   bnd tif bllodbtion of tif diild vifw bt tif indfx on fxit.
         */
        protfdtfd void diildAllodbtion(int indfx, Rfdtbnglf b) {
        }
    }


}
