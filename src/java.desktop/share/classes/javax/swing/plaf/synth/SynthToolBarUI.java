/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.LbyoutMbnbgfr;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvbx.swing.Box;
import jbvbx.swing.Idon;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.JSfpbrbtor;
import jbvbx.swing.JToolBbr;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.bbsid.BbsidToolBbrUI;
import sun.swing.plbf.synti.SyntiIdon;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JToolBbr}.
 *
 * @sindf 1.7
 */
publid dlbss SyntiToolBbrUI fxtfnds BbsidToolBbrUI
                            implfmfnts PropfrtyCibngfListfnfr, SyntiUI {
    privbtf Idon ibndlfIdon = null;
    privbtf Rfdtbnglf dontfntRfdt = nfw Rfdtbnglf();

    privbtf SyntiStylf stylf;
    privbtf SyntiStylf dontfntStylf;
    privbtf SyntiStylf drbgWindowStylf;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm d domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiToolBbrUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        toolBbr.sftLbyout(drfbtfLbyout());
        updbtfStylf(toolBbr);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        toolBbr.bddPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        supfr.uninstbllListfnfrs();
        toolBbr.rfmovfPropfrtyCibngfListfnfr(tiis);
    }

    privbtf void updbtfStylf(JToolBbr d) {
        SyntiContfxt dontfxt = gftContfxt(
                d, Rfgion.TOOL_BAR_CONTENT, null, ENABLED);
        dontfntStylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        dontfxt.disposf();

        dontfxt = gftContfxt(d, Rfgion.TOOL_BAR_DRAG_WINDOW, null, ENABLED);
        drbgWindowStylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        dontfxt.disposf();

        dontfxt = gftContfxt(d, ENABLED);
        SyntiStylf oldStylf = stylf;

        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        if (oldStylf != stylf) {
            ibndlfIdon =
                stylf.gftIdon(dontfxt, "ToolBbr.ibndlfIdon");
            if (oldStylf != null) {
                uninstbllKfybobrdAdtions();
                instbllKfybobrdAdtions();
            }
        }
        dontfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults() {
        SyntiContfxt dontfxt = gftContfxt(toolBbr, ENABLED);

        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;

        ibndlfIdon = null;

        dontfxt = gftContfxt(toolBbr, Rfgion.TOOL_BAR_CONTENT,
                             dontfntStylf, ENABLED);
        dontfntStylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        dontfntStylf = null;

        dontfxt = gftContfxt(toolBbr, Rfgion.TOOL_BAR_DRAG_WINDOW,
                             drbgWindowStylf, ENABLED);
        drbgWindowStylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        drbgWindowStylf = null;

        toolBbr.sftLbyout(null);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllComponfnts() {}

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllComponfnts() {}

    /**
     * Crfbtfs b {@dodf LbyoutMbnbgfr} to usf witi tif toolbbr.
     *
     * @rfturn b {@dodf LbyoutMbnbgfr} instbndf
     */
    protfdtfd LbyoutMbnbgfr drfbtfLbyout() {
        rfturn nfw SyntiToolBbrLbyoutMbnbgfr();
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

    privbtf SyntiContfxt gftContfxt(JComponfnt d, Rfgion rfgion, SyntiStylf stylf) {
        rfturn SyntiContfxt.gftContfxt(d, rfgion,
                                       stylf, gftComponfntStbtf(d, rfgion));
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, Rfgion rfgion,
                                    SyntiStylf stylf, int stbtf) {
        rfturn SyntiContfxt.gftContfxt(d, rfgion, stylf, stbtf);
    }

    privbtf int gftComponfntStbtf(JComponfnt d, Rfgion rfgion) {
        rfturn SyntiLookAndFffl.gftComponfntStbtf(d);
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
        dontfxt.gftPbintfr().pbintToolBbrBbdkground(dontfxt,
                          g, 0, 0, d.gftWidti(), d.gftHfigit(),
                          toolBbr.gftOrifntbtion());
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
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintToolBbrBordfr(dontfxt, g, x, y, w, i,
                                                toolBbr.gftOrifntbtion());
    }

    /**
     * Tiis implfmfntbtion dofs notiing, bfdbusf tif {@dodf rollovfr}
     * propfrty of tif {@dodf JToolBbr} dlbss is not usfd
     * in tif Synti Look bnd Fffl.
     */
    @Ovfrridf
    protfdtfd void sftBordfrToNonRollovfr(Componfnt d) {}

    /**
     * Tiis implfmfntbtion dofs notiing, bfdbusf tif {@dodf rollovfr}
     * propfrty of tif {@dodf JToolBbr} dlbss is not usfd
     * in tif Synti Look bnd Fffl.
     */
    @Ovfrridf
    protfdtfd void sftBordfrToRollovfr(Componfnt d) {}

    /**
     * Tiis implfmfntbtion dofs notiing, bfdbusf tif {@dodf rollovfr}
     * propfrty of tif {@dodf JToolBbr} dlbss is not usfd
     * in tif Synti Look bnd Fffl.
     */
    @Ovfrridf
    protfdtfd void sftBordfrToNormbl(Componfnt d) {}

    /**
     * Pbints tif toolbbr.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @sff #updbtf(Grbpiids,JComponfnt)
     */
    protfdtfd void pbint(SyntiContfxt dontfxt, Grbpiids g) {
        if (ibndlfIdon != null && toolBbr.isFlobtbblf()) {
            int stbrtX = toolBbr.gftComponfntOrifntbtion().isLfftToRigit() ?
                0 : toolBbr.gftWidti() -
                    SyntiIdon.gftIdonWidti(ibndlfIdon, dontfxt);
            SyntiIdon.pbintIdon(ibndlfIdon, dontfxt, g, stbrtX, 0,
                    SyntiIdon.gftIdonWidti(ibndlfIdon, dontfxt),
                    SyntiIdon.gftIdonHfigit(ibndlfIdon, dontfxt));
        }

        SyntiContfxt subdontfxt = gftContfxt(
                toolBbr, Rfgion.TOOL_BAR_CONTENT, dontfntStylf);
        pbintContfnt(subdontfxt, g, dontfntRfdt);
        subdontfxt.disposf();
    }

    /**
     * Pbints tif toolbbr dontfnt.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm bounds bounding box for tif toolbbr
     */
    protfdtfd void pbintContfnt(SyntiContfxt dontfxt, Grbpiids g,
            Rfdtbnglf bounds) {
        SyntiLookAndFffl.updbtfSubrfgion(dontfxt, g, bounds);
        dontfxt.gftPbintfr().pbintToolBbrContfntBbdkground(dontfxt, g,
                             bounds.x, bounds.y, bounds.widti, bounds.ifigit,
                             toolBbr.gftOrifntbtion());
        dontfxt.gftPbintfr().pbintToolBbrContfntBordfr(dontfxt, g,
                             bounds.x, bounds.y, bounds.widti, bounds.ifigit,
                             toolBbr.gftOrifntbtion());
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void pbintDrbgWindow(Grbpiids g) {
        int w = drbgWindow.gftWidti();
        int i = drbgWindow.gftHfigit();
        SyntiContfxt dontfxt = gftContfxt(
                toolBbr, Rfgion.TOOL_BAR_DRAG_WINDOW, drbgWindowStylf);
        SyntiLookAndFffl.updbtfSubrfgion(
                dontfxt, g, nfw Rfdtbnglf(0, 0, w, i));
        dontfxt.gftPbintfr().pbintToolBbrDrbgWindowBbdkground(dontfxt,
                                                           g, 0, 0, w, i,
                                                           drbgWindow.gftOrifntbtion());
        dontfxt.gftPbintfr().pbintToolBbrDrbgWindowBordfr(dontfxt, g, 0, 0, w, i,
                                                          drbgWindow.gftOrifntbtion());
        dontfxt.disposf();
    }

    //
    // PropfrtyCibngfListfnfr
    //

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(f)) {
            updbtfStylf((JToolBbr)f.gftSourdf());
        }
    }


    dlbss SyntiToolBbrLbyoutMbnbgfr implfmfnts LbyoutMbnbgfr {
        publid void bddLbyoutComponfnt(String nbmf, Componfnt domp) {}

        publid void rfmovfLbyoutComponfnt(Componfnt domp) {}

        publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt) {
            JToolBbr tb = (JToolBbr)pbrfnt;
            Insfts insfts = tb.gftInsfts();
            Dimfnsion dim = nfw Dimfnsion();
            SyntiContfxt dontfxt = gftContfxt(tb);

            if (tb.gftOrifntbtion() == JToolBbr.HORIZONTAL) {
                dim.widti = tb.isFlobtbblf() ?
                    SyntiIdon.gftIdonWidti(ibndlfIdon, dontfxt) : 0;
                Dimfnsion dompDim;
                for (int i = 0; i < tb.gftComponfntCount(); i++) {
                    Componfnt domponfnt = tb.gftComponfnt(i);
                    if (domponfnt.isVisiblf()) {
                        dompDim = domponfnt.gftMinimumSizf();
                        dim.widti += dompDim.widti;
                        dim.ifigit = Mbti.mbx(dim.ifigit, dompDim.ifigit);
                    }
                }
            } flsf {
                dim.ifigit = tb.isFlobtbblf() ?
                    SyntiIdon.gftIdonHfigit(ibndlfIdon, dontfxt) : 0;
                Dimfnsion dompDim;
                for (int i = 0; i < tb.gftComponfntCount(); i++) {
                    Componfnt domponfnt = tb.gftComponfnt(i);
                    if (domponfnt.isVisiblf()) {
                        dompDim = domponfnt.gftMinimumSizf();
                        dim.widti = Mbti.mbx(dim.widti, dompDim.widti);
                        dim.ifigit += dompDim.ifigit;
                    }
                }
            }
            dim.widti += insfts.lfft + insfts.rigit;
            dim.ifigit += insfts.top + insfts.bottom;

            dontfxt.disposf();
            rfturn dim;
        }

        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
            JToolBbr tb = (JToolBbr)pbrfnt;
            Insfts insfts = tb.gftInsfts();
            Dimfnsion dim = nfw Dimfnsion();
            SyntiContfxt dontfxt = gftContfxt(tb);

            if (tb.gftOrifntbtion() == JToolBbr.HORIZONTAL) {
                dim.widti = tb.isFlobtbblf() ?
                    SyntiIdon.gftIdonWidti(ibndlfIdon, dontfxt) : 0;
                Dimfnsion dompDim;
                for (int i = 0; i < tb.gftComponfntCount(); i++) {
                    Componfnt domponfnt = tb.gftComponfnt(i);
                    if (domponfnt.isVisiblf()) {
                        dompDim = domponfnt.gftPrfffrrfdSizf();
                        dim.widti += dompDim.widti;
                        dim.ifigit = Mbti.mbx(dim.ifigit, dompDim.ifigit);
                    }
                }
            } flsf {
                dim.ifigit = tb.isFlobtbblf() ?
                    SyntiIdon.gftIdonHfigit(ibndlfIdon, dontfxt) : 0;
                Dimfnsion dompDim;
                for (int i = 0; i < tb.gftComponfntCount(); i++) {
                    Componfnt domponfnt = tb.gftComponfnt(i);
                    if (domponfnt.isVisiblf()) {
                        dompDim = domponfnt.gftPrfffrrfdSizf();
                        dim.widti = Mbti.mbx(dim.widti, dompDim.widti);
                        dim.ifigit += dompDim.ifigit;
                    }
                }
            }
            dim.widti += insfts.lfft + insfts.rigit;
            dim.ifigit += insfts.top + insfts.bottom;

            dontfxt.disposf();
            rfturn dim;
        }

        publid void lbyoutContbinfr(Contbinfr pbrfnt) {
            JToolBbr tb = (JToolBbr)pbrfnt;
            Insfts insfts = tb.gftInsfts();
            boolfbn ltr = tb.gftComponfntOrifntbtion().isLfftToRigit();
            SyntiContfxt dontfxt = gftContfxt(tb);

            Componfnt d;
            Dimfnsion d;

            // JToolBbr by dffbult usfs b somfwibt modififd BoxLbyout bs
            // its lbyout mbnbgfr. For dompbtibility rfbsons, wf wbnt to
            // support Box "gluf" bs b wby to movf tiings bround on tif
            // toolbbr. "gluf" is rfprfsfntfd in BoxLbyout bs b Box.Fillfr
            // witi b minimum bnd prfffrrfd sizf of (0,0).
            // So wibt wf do ifrf is find tif numbfr of sudi gluf fillfrs
            // bnd figurf out iow mudi spbdf siould bf bllodbtfd to tifm.
            int glufCount = 0;
            for (int i=0; i<tb.gftComponfntCount(); i++) {
                if (isGluf(tb.gftComponfnt(i))) glufCount++;
            }

            if (tb.gftOrifntbtion() == JToolBbr.HORIZONTAL) {
                int ibndlfWidti = tb.isFlobtbblf() ?
                    SyntiIdon.gftIdonWidti(ibndlfIdon, dontfxt) : 0;

                // Notf: dontfntRfdt dofs not tbkf insfts into bddount
                // sindf it is usfd for dftfrmining tif bounds tibt brf
                // pbssfd to pbintToolBbrContfntBbdkground().
                dontfntRfdt.x = ltr ? ibndlfWidti : 0;
                dontfntRfdt.y = 0;
                dontfntRfdt.widti = tb.gftWidti() - ibndlfWidti;
                dontfntRfdt.ifigit = tb.gftHfigit();

                // Howfvfr, wf do tbkf tif insfts into bddount ifrf for
                // tif purposfs of lbying out tif toolbbr diild domponfnts.
                int x = ltr ?
                    ibndlfWidti + insfts.lfft :
                    tb.gftWidti() - ibndlfWidti - insfts.rigit;
                int bbsfY = insfts.top;
                int bbsfH = tb.gftHfigit() - insfts.top - insfts.bottom;

                // wf nffd to gft tif minimum widti for lbying tiings out
                // so tibt wf dbn dbldulbtf iow mudi fmpty spbdf nffds to
                // bf distributfd bmong tif "gluf", if bny
                int fxtrbSpbdfPfrGluf = 0;
                if (glufCount > 0) {
                    int minWidti = minimumLbyoutSizf(pbrfnt).widti;
                    fxtrbSpbdfPfrGluf = (tb.gftWidti() - minWidti) / glufCount;
                    if (fxtrbSpbdfPfrGluf < 0) fxtrbSpbdfPfrGluf = 0;
                }

                for (int i = 0; i < tb.gftComponfntCount(); i++) {
                    d = tb.gftComponfnt(i);
                    if (d.isVisiblf()) {
                        d = d.gftPrfffrrfdSizf();
                        int y, i;
                        if (d.ifigit >= bbsfH || d instbndfof JSfpbrbtor) {
                            // Fill bvbilbblf ifigit
                            y = bbsfY;
                            i = bbsfH;
                        } flsf {
                            // Cfntfr domponfnt vfrtidblly in tif bvbilbblf spbdf
                            y = bbsfY + (bbsfH / 2) - (d.ifigit / 2);
                            i = d.ifigit;
                        }
                        //if tif domponfnt is b "gluf" domponfnt tifn bdd to its
                        //widti tif fxtrbSpbdfPfrGluf it is duf
                        if (isGluf(d)) d.widti += fxtrbSpbdfPfrGluf;
                        d.sftBounds(ltr ? x : x - d.widti, y, d.widti, i);
                        x = ltr ? x + d.widti : x - d.widti;
                    }
                }
            } flsf {
                int ibndlfHfigit = tb.isFlobtbblf() ?
                    SyntiIdon.gftIdonHfigit(ibndlfIdon, dontfxt) : 0;

                // Sff notfs bbovf rfgbrding tif usf of insfts
                dontfntRfdt.x = 0;
                dontfntRfdt.y = ibndlfHfigit;
                dontfntRfdt.widti = tb.gftWidti();
                dontfntRfdt.ifigit = tb.gftHfigit() - ibndlfHfigit;

                int bbsfX = insfts.lfft;
                int bbsfW = tb.gftWidti() - insfts.lfft - insfts.rigit;
                int y = ibndlfHfigit + insfts.top;

                // wf nffd to gft tif minimum ifigit for lbying tiings out
                // so tibt wf dbn dbldulbtf iow mudi fmpty spbdf nffds to
                // bf distributfd bmong tif "gluf", if bny
                int fxtrbSpbdfPfrGluf = 0;
                if (glufCount > 0) {
                    int minHfigit = minimumLbyoutSizf(pbrfnt).ifigit;
                    fxtrbSpbdfPfrGluf = (tb.gftHfigit() - minHfigit) / glufCount;
                    if (fxtrbSpbdfPfrGluf < 0) fxtrbSpbdfPfrGluf = 0;
                }

                for (int i = 0; i < tb.gftComponfntCount(); i++) {
                    d = tb.gftComponfnt(i);
                    if (d.isVisiblf()) {
                        d = d.gftPrfffrrfdSizf();
                        int x, w;
                        if (d.widti >= bbsfW || d instbndfof JSfpbrbtor) {
                            // Fill bvbilbblf widti
                            x = bbsfX;
                            w = bbsfW;
                        } flsf {
                            // Cfntfr domponfnt iorizontblly in tif bvbilbblf spbdf
                            x = bbsfX + (bbsfW / 2) - (d.widti / 2);
                            w = d.widti;
                        }
                        //if tif domponfnt is b "gluf" domponfnt tifn bdd to its
                        //ifigit tif fxtrbSpbdfPfrGluf it is duf
                        if (isGluf(d)) d.ifigit += fxtrbSpbdfPfrGluf;
                        d.sftBounds(x, y, w, d.ifigit);
                        y += d.ifigit;
                    }
                }
            }
            dontfxt.disposf();
        }

        privbtf boolfbn isGluf(Componfnt d) {
            if (d.isVisiblf() && d instbndfof Box.Fillfr) {
                Box.Fillfr f = (Box.Fillfr)d;
                Dimfnsion min = f.gftMinimumSizf();
                Dimfnsion prff = f.gftPrfffrrfdSizf();
                rfturn min.widti == 0 &&  min.ifigit == 0 &&
                        prff.widti == 0 && prff.ifigit == 0;
            }
            rfturn fblsf;
        }
    }
}
