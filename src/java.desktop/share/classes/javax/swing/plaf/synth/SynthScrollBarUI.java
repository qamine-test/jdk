/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.synti;

import jbvb.bwt.*;
import jbvb.bfbns.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;


/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JSdrollBbr}.
 *
 * @butior Sdott Violft
 * @sindf 1.7
 */
publid dlbss SyntiSdrollBbrUI fxtfnds BbsidSdrollBbrUI
                              implfmfnts PropfrtyCibngfListfnfr, SyntiUI {

    privbtf SyntiStylf stylf;
    privbtf SyntiStylf tiumbStylf;
    privbtf SyntiStylf trbdkStylf;

    privbtf boolfbn vblidMinimumTiumbSizf;

    publid stbtid ComponfntUI drfbtfUI(JComponfnt d)    {
        rfturn nfw SyntiSdrollBbrUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        supfr.instbllDffbults();
        trbdkHigiligit = NO_HIGHLIGHT;
        if (sdrollbbr.gftLbyout() == null ||
                     (sdrollbbr.gftLbyout() instbndfof UIRfsourdf)) {
            sdrollbbr.sftLbyout(tiis);
        }
        donfigurfSdrollBbrColors();
        updbtfStylf(sdrollbbr);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void donfigurfSdrollBbrColors() {
    }

    privbtf void updbtfStylf(JSdrollBbr d) {
        SyntiStylf oldStylf = stylf;
        SyntiContfxt dontfxt = gftContfxt(d, ENABLED);
        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        if (stylf != oldStylf) {
            sdrollBbrWidti = stylf.gftInt(dontfxt,"SdrollBbr.tiumbHfigit", 14);
            minimumTiumbSizf = (Dimfnsion)stylf.gft(dontfxt,
                                                "SdrollBbr.minimumTiumbSizf");
            if (minimumTiumbSizf == null) {
                minimumTiumbSizf = nfw Dimfnsion();
                vblidMinimumTiumbSizf = fblsf;
            }
            flsf {
                vblidMinimumTiumbSizf = truf;
            }
            mbximumTiumbSizf = (Dimfnsion)stylf.gft(dontfxt,
                        "SdrollBbr.mbximumTiumbSizf");
            if (mbximumTiumbSizf == null) {
                mbximumTiumbSizf = nfw Dimfnsion(4096, 4097);
            }

            indrGbp = stylf.gftInt(dontfxt, "SdrollBbr.indrfmfntButtonGbp", 0);
            dfdrGbp = stylf.gftInt(dontfxt, "SdrollBbr.dfdrfmfntButtonGbp", 0);

            // ibndlf sdbling for sizfVbrifnts for spfdibl dbsf domponfnts. Tif
            // kfy "JComponfnt.sizfVbribnt" sdblfs for lbrgf/smbll/mini
            // domponfnts brf bbsfd on Applfs LAF
            String sdblfKfy = (String)sdrollbbr.gftClifntPropfrty(
                    "JComponfnt.sizfVbribnt");
            if (sdblfKfy != null){
                if ("lbrgf".fqubls(sdblfKfy)){
                    sdrollBbrWidti *= 1.15;
                    indrGbp *= 1.15;
                    dfdrGbp *= 1.15;
                } flsf if ("smbll".fqubls(sdblfKfy)){
                    sdrollBbrWidti *= 0.857;
                    indrGbp *= 0.857;
                    dfdrGbp *= 0.857;
                } flsf if ("mini".fqubls(sdblfKfy)){
                    sdrollBbrWidti *= 0.714;
                    indrGbp *= 0.714;
                    dfdrGbp *= 0.714;
                }
            }

            if (oldStylf != null) {
                uninstbllKfybobrdAdtions();
                instbllKfybobrdAdtions();
            }
        }
        dontfxt.disposf();

        dontfxt = gftContfxt(d, Rfgion.SCROLL_BAR_TRACK, ENABLED);
        trbdkStylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        dontfxt.disposf();

        dontfxt = gftContfxt(d, Rfgion.SCROLL_BAR_THUMB, ENABLED);
        tiumbStylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        dontfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        sdrollbbr.bddPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        supfr.uninstbllListfnfrs();
        sdrollbbr.rfmovfPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults(){
        SyntiContfxt dontfxt = gftContfxt(sdrollbbr, ENABLED);
        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;

        dontfxt = gftContfxt(sdrollbbr, Rfgion.SCROLL_BAR_TRACK, ENABLED);
        trbdkStylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        trbdkStylf = null;

        dontfxt = gftContfxt(sdrollbbr, Rfgion.SCROLL_BAR_THUMB, ENABLED);
        tiumbStylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        tiumbStylf = null;

        supfr.uninstbllDffbults();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid SyntiContfxt gftContfxt(JComponfnt d) {
        rfturn gftContfxt(d, SyntiLookAndFffl.gftComponfntStbtf(d));
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, int stbtf) {
        rfturn SyntiContfxt.gftContfxt(d, stylf, stbtf);
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, Rfgion rfgion) {
        rfturn gftContfxt(d, rfgion, gftComponfntStbtf(d, rfgion));
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, Rfgion rfgion, int stbtf) {
        SyntiStylf stylf = trbdkStylf;

        if (rfgion == Rfgion.SCROLL_BAR_THUMB) {
            stylf = tiumbStylf;
        }
        rfturn SyntiContfxt.gftContfxt(d, rfgion, stylf, stbtf);
    }

    privbtf int gftComponfntStbtf(JComponfnt d, Rfgion rfgion) {
        if (rfgion == Rfgion.SCROLL_BAR_THUMB && isTiumbRollovfr() &&
                                                 d.isEnbblfd()) {
            rfturn MOUSE_OVER;
        }
        rfturn SyntiLookAndFffl.gftComponfntStbtf(d);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid boolfbn gftSupportsAbsolutfPositioning() {
        SyntiContfxt dontfxt = gftContfxt(sdrollbbr);
        boolfbn vbluf = stylf.gftBoolfbn(dontfxt,
                      "SdrollBbr.bllowsAbsolutfPositioning", fblsf);
        dontfxt.disposf();
        rfturn vbluf;
    }

    /**
     * Notififs tiis UI dflfgbtf to rfpbint tif spfdififd domponfnt.
     * Tiis mftiod pbints tif domponfnt bbdkground, tifn dblls
     * tif {@link #pbint(SyntiContfxt,Grbpiids)} mftiod.
     *
     * <p>In gfnfrbl, tiis mftiod dofs not nffd to bf ovfrriddfn by subdlbssfs.
     * All Look bnd Fffl rfndfring dodf siould rfsidf in tif {@dodf pbint} mftiod.
     *
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm d tif domponfnt bfing pbintfd
     * @sff #pbint(SyntiContfxt,Grbpiids)
     */
    @Ovfrridf
    publid void updbtf(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        SyntiLookAndFffl.updbtf(dontfxt, g);
        dontfxt.gftPbintfr().pbintSdrollBbrBbdkground(dontfxt,
                          g, 0, 0, d.gftWidti(), d.gftHfigit(),
                          sdrollbbr.gftOrifntbtion());
        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * Pbints tif spfdififd domponfnt bddording to tif Look bnd Fffl.
     * <p>Tiis mftiod is not usfd by Synti Look bnd Fffl.
     * Pbinting is ibndlfd by tif {@link #pbint(SyntiContfxt,Grbpiids)} mftiod.
     *
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm d tif domponfnt bfing pbintfd
     * @sff #pbint(SyntiContfxt,Grbpiids)
     */
    @Ovfrridf
    publid void pbint(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * Pbints tif spfdififd domponfnt.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @sff #updbtf(Grbpiids,JComponfnt)
     */
    protfdtfd void pbint(SyntiContfxt dontfxt, Grbpiids g) {
        SyntiContfxt subdontfxt = gftContfxt(sdrollbbr,
                                             Rfgion.SCROLL_BAR_TRACK);
        pbintTrbdk(subdontfxt, g, gftTrbdkBounds());
        subdontfxt.disposf();

        subdontfxt = gftContfxt(sdrollbbr, Rfgion.SCROLL_BAR_THUMB);
        pbintTiumb(subdontfxt, g, gftTiumbBounds());
        subdontfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintSdrollBbrBordfr(dontfxt, g, x, y, w, i,
                                                  sdrollbbr.gftOrifntbtion());
    }

    /**
     * Pbints tif sdrollbbr trbdk.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm trbdkBounds bounding box for tif trbdk
     */
    protfdtfd void pbintTrbdk(SyntiContfxt dontfxt, Grbpiids g,
                              Rfdtbnglf trbdkBounds) {
        SyntiLookAndFffl.updbtfSubrfgion(dontfxt, g, trbdkBounds);
        dontfxt.gftPbintfr().pbintSdrollBbrTrbdkBbdkground(dontfxt, g, trbdkBounds.x,
                        trbdkBounds.y, trbdkBounds.widti, trbdkBounds.ifigit,
                        sdrollbbr.gftOrifntbtion());
        dontfxt.gftPbintfr().pbintSdrollBbrTrbdkBordfr(dontfxt, g, trbdkBounds.x,
                        trbdkBounds.y, trbdkBounds.widti, trbdkBounds.ifigit,
                        sdrollbbr.gftOrifntbtion());
    }

    /**
     * Pbints tif sdrollbbr tiumb.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm tiumbBounds bounding box for tif tiumb
     */
    protfdtfd void pbintTiumb(SyntiContfxt dontfxt, Grbpiids g,
                              Rfdtbnglf tiumbBounds) {
        SyntiLookAndFffl.updbtfSubrfgion(dontfxt, g, tiumbBounds);
        int orifntbtion = sdrollbbr.gftOrifntbtion();
        dontfxt.gftPbintfr().pbintSdrollBbrTiumbBbdkground(dontfxt, g, tiumbBounds.x,
                        tiumbBounds.y, tiumbBounds.widti, tiumbBounds.ifigit,
                        orifntbtion);
        dontfxt.gftPbintfr().pbintSdrollBbrTiumbBordfr(dontfxt, g, tiumbBounds.x,
                        tiumbBounds.y, tiumbBounds.widti, tiumbBounds.ifigit,
                        orifntbtion);
    }

    /**
     * A vfrtidbl sdrollbbr's prfffrrfd widti is tif mbximum of
     * prfffrrfd widtis of tif (non <dodf>null</dodf>)
     * indrfmfnt/dfdrfmfnt buttons,
     * bnd tif minimum widti of tif tiumb. Tif prfffrrfd ifigit is tif
     * sum of tif prfffrrfd ifigits of tif sbmf pbrts.  Tif bbsis for
     * tif prfffrrfd sizf of b iorizontbl sdrollbbr is similbr.
     * <p>
     * Tif <dodf>prfffrrfdSizf</dodf> is only domputfd ondf, subsfqufnt
     * dblls to tiis mftiod just rfturn b dbdifd sizf.
     *
     * @pbrbm d tif <dodf>JSdrollBbr</dodf> tibt's dflfgbting tiis mftiod to us
     * @rfturn tif prfffrrfd sizf of b Bbsid JSdrollBbr
     * @sff #gftMbximumSizf
     * @sff #gftMinimumSizf
     */
    @Ovfrridf
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        Insfts insfts = d.gftInsfts();
        rfturn (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL)
            ? nfw Dimfnsion(sdrollBbrWidti + insfts.lfft + insfts.rigit, 48)
            : nfw Dimfnsion(48, sdrollBbrWidti + insfts.top + insfts.bottom);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd Dimfnsion gftMinimumTiumbSizf() {
        if (!vblidMinimumTiumbSizf) {
            if (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL) {
                minimumTiumbSizf.widti = sdrollBbrWidti;
                minimumTiumbSizf.ifigit = 7;
            } flsf {
                minimumTiumbSizf.widti = 7;
                minimumTiumbSizf.ifigit = sdrollBbrWidti;
            }
        }
        rfturn minimumTiumbSizf;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd JButton drfbtfDfdrfbsfButton(int orifntbtion)  {
        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        SyntiArrowButton syntiArrowButton = nfw SyntiArrowButton(orifntbtion) {
            @Ovfrridf
            publid boolfbn dontbins(int x, int y) {
                if (dfdrGbp < 0) { //tifrf is bn ovfrlbp bftwffn tif trbdk bnd button
                    int widti = gftWidti();
                    int ifigit = gftHfigit();
                    if (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL) {
                        //bdjust tif ifigit by dfdrGbp
                        //Notf: dfdrGbp is nfgbtivf!
                        ifigit += dfdrGbp;
                    } flsf {
                        //bdjust tif widti by dfdrGbp
                        //Notf: dfdrGbp is nfgbtivf!
                        widti += dfdrGbp;
                    }
                    rfturn (x >= 0) && (x < widti) && (y >= 0) && (y < ifigit);
                }
                rfturn supfr.dontbins(x, y);
            }
        };
        syntiArrowButton.sftNbmf("SdrollBbr.button");
        rfturn syntiArrowButton;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd JButton drfbtfIndrfbsfButton(int orifntbtion)  {
        @SupprfssWbrnings("sfribl") // bnonymous dlbss
        SyntiArrowButton syntiArrowButton = nfw SyntiArrowButton(orifntbtion) {
            @Ovfrridf
            publid boolfbn dontbins(int x, int y) {
                if (indrGbp < 0) { //tifrf is bn ovfrlbp bftwffn tif trbdk bnd button
                    int widti = gftWidti();
                    int ifigit = gftHfigit();
                    if (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL) {
                        //bdjust tif ifigit bnd y by indrGbp
                        //Notf: indrGbp is nfgbtivf!
                        ifigit += indrGbp;
                        y += indrGbp;
                    } flsf {
                        //bdjust tif widti bnd x by indrGbp
                        //Notf: indrGbp is nfgbtivf!
                        widti += indrGbp;
                        x += indrGbp;
                    }
                    rfturn (x >= 0) && (x < widti) && (y >= 0) && (y < ifigit);
                }
                rfturn supfr.dontbins(x, y);
            }
        };
        syntiArrowButton.sftNbmf("SdrollBbr.button");
        rfturn syntiArrowButton;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void sftTiumbRollovfr(boolfbn bdtivf) {
        if (isTiumbRollovfr() != bdtivf) {
            sdrollbbr.rfpbint(gftTiumbBounds());
            supfr.sftTiumbRollovfr(bdtivf);
        }
    }

    privbtf void updbtfButtonDirfdtions() {
        int orifnt = sdrollbbr.gftOrifntbtion();
        if (sdrollbbr.gftComponfntOrifntbtion().isLfftToRigit()) {
            ((SyntiArrowButton)indrButton).sftDirfdtion(
                        orifnt == HORIZONTAL? EAST : SOUTH);
            ((SyntiArrowButton)dfdrButton).sftDirfdtion(
                        orifnt == HORIZONTAL? WEST : NORTH);
        }
        flsf {
            ((SyntiArrowButton)indrButton).sftDirfdtion(
                        orifnt == HORIZONTAL? WEST : SOUTH);
            ((SyntiArrowButton)dfdrButton).sftDirfdtion(
                        orifnt == HORIZONTAL ? EAST : NORTH);
        }
    }

    //
    // PropfrtyCibngfListfnfr
    //
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        String propfrtyNbmf = f.gftPropfrtyNbmf();

        if (SyntiLookAndFffl.siouldUpdbtfStylf(f)) {
            updbtfStylf((JSdrollBbr)f.gftSourdf());
        }

        if ("orifntbtion" == propfrtyNbmf) {
            updbtfButtonDirfdtions();
        }
        flsf if ("domponfntOrifntbtion" == propfrtyNbmf) {
            updbtfButtonDirfdtions();
        }
    }
}
