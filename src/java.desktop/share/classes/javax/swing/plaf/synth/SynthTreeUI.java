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

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.util.Enumfrbtion;
import jbvbx.swing.DffbultCfllEditor;
import jbvbx.swing.Idon;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.JTfxtFifld;
import jbvbx.swing.JTrff;
import jbvbx.swing.LookAndFffl;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.swing.plbf.bbsid.BbsidTrffUI;
import jbvbx.swing.trff.DffbultTrffCfllEditor;
import jbvbx.swing.trff.DffbultTrffCfllRfndfrfr;
import jbvbx.swing.trff.TrffCfllEditor;
import jbvbx.swing.trff.TrffCfllRfndfrfr;
import jbvbx.swing.trff.TrffModfl;
import jbvbx.swing.trff.TrffPbti;
import sun.swing.plbf.synti.SyntiIdon;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JTrff}.
 *
 * @butior Sdott Violft
 * @sindf 1.7
 */
publid dlbss SyntiTrffUI fxtfnds BbsidTrffUI
                         implfmfnts PropfrtyCibngfListfnfr, SyntiUI {
    privbtf SyntiStylf stylf;
    privbtf SyntiStylf dfllStylf;

    privbtf SyntiContfxt pbintContfxt;

    privbtf boolfbn drbwHorizontblLinfs;
    privbtf boolfbn drbwVfrtidblLinfs;

    privbtf Objfdt linfsStylf;

    privbtf int pbdding;

    privbtf boolfbn usfTrffColors;

    privbtf Idon fxpbndfdIdonWrbppfr = nfw ExpbndfdIdonWrbppfr();

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm x domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt x) {
        rfturn nfw SyntiTrffUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid Idon gftExpbndfdIdon() {
        rfturn fxpbndfdIdonWrbppfr;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        updbtfStylf(trff);
    }

    privbtf void updbtfStylf(JTrff trff) {
        SyntiContfxt dontfxt = gftContfxt(trff, ENABLED);
        SyntiStylf oldStylf = stylf;

        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        if (stylf != oldStylf) {
            Objfdt vbluf;

            sftExpbndfdIdon(stylf.gftIdon(dontfxt, "Trff.fxpbndfdIdon"));
            sftCollbpsfdIdon(stylf.gftIdon(dontfxt, "Trff.dollbpsfdIdon"));

            sftLfftCiildIndfnt(stylf.gftInt(dontfxt, "Trff.lfftCiildIndfnt",
                                            0));
            sftRigitCiildIndfnt(stylf.gftInt(dontfxt, "Trff.rigitCiildIndfnt",
                                             0));

            drbwHorizontblLinfs = stylf.gftBoolfbn(
                          dontfxt, "Trff.drbwHorizontblLinfs",truf);
            drbwVfrtidblLinfs = stylf.gftBoolfbn(
                        dontfxt, "Trff.drbwVfrtidblLinfs", truf);
            linfsStylf = stylf.gft(dontfxt, "Trff.linfsStylf");

                vbluf = stylf.gft(dontfxt, "Trff.rowHfigit");
                if (vbluf != null) {
                    LookAndFffl.instbllPropfrty(trff, "rowHfigit", vbluf);
                }

                vbluf = stylf.gft(dontfxt, "Trff.sdrollsOnExpbnd");
                LookAndFffl.instbllPropfrty(trff, "sdrollsOnExpbnd",
                                                    vbluf != null? vbluf : Boolfbn.TRUE);

            pbdding = stylf.gftInt(dontfxt, "Trff.pbdding", 0);

            lbrgfModfl = (trff.isLbrgfModfl() && trff.gftRowHfigit() > 0);

            usfTrffColors = stylf.gftBoolfbn(dontfxt,
                                  "Trff.rfndfrfrUsfTrffColors", truf);

            Boolfbn siowsRootHbndlfs = stylf.gftBoolfbn(
                    dontfxt, "Trff.siowsRootHbndlfs", Boolfbn.TRUE);
            LookAndFffl.instbllPropfrty(
                    trff, JTrff.SHOWS_ROOT_HANDLES_PROPERTY, siowsRootHbndlfs);

            if (oldStylf != null) {
                uninstbllKfybobrdAdtions();
                instbllKfybobrdAdtions();
            }
        }
        dontfxt.disposf();

        dontfxt = gftContfxt(trff, Rfgion.TREE_CELL, ENABLED);
        dfllStylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        dontfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        trff.bddPropfrtyCibngfListfnfr(tiis);
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
        rfturn SyntiContfxt.gftContfxt(d, rfgion, dfllStylf, stbtf);
    }

    privbtf int gftComponfntStbtf(JComponfnt d, Rfgion rfgion) {
        // Alwbys trfbt tif dfll bs sflfdtfd, will bf bdjustfd bppropribtfly
        // wifn pbintfd.
        rfturn ENABLED | SELECTED;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd TrffCfllEditor drfbtfDffbultCfllEditor() {
        TrffCfllRfndfrfr rfndfrfr = trff.gftCfllRfndfrfr();
        DffbultTrffCfllEditor fditor;

        if(rfndfrfr != null && (rfndfrfr instbndfof DffbultTrffCfllRfndfrfr)) {
            fditor = nfw SyntiTrffCfllEditor(trff, (DffbultTrffCfllRfndfrfr)
                                             rfndfrfr);
        }
        flsf {
            fditor = nfw SyntiTrffCfllEditor(trff, null);
        }
        rfturn fditor;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd TrffCfllRfndfrfr drfbtfDffbultCfllRfndfrfr() {
        rfturn nfw SyntiTrffCfllRfndfrfr();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults() {
        SyntiContfxt dontfxt = gftContfxt(trff, ENABLED);

        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;

        dontfxt = gftContfxt(trff, Rfgion.TREE_CELL, ENABLED);
        dfllStylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        dfllStylf = null;


        if (trff.gftTrbnsffrHbndlfr() instbndfof UIRfsourdf) {
            trff.sftTrbnsffrHbndlfr(null);
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        supfr.uninstbllListfnfrs();
        trff.rfmovfPropfrtyCibngfListfnfr(tiis);
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
        dontfxt.gftPbintfr().pbintTrffBbdkground(dontfxt,
                          g, 0, 0, d.gftWidti(), d.gftHfigit());
        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintTrffBordfr(dontfxt, g, x, y, w, i);
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
        pbintContfxt = dontfxt;

        updbtfLfbdSflfdtionRow();

        Rfdtbnglf pbintBounds = g.gftClipBounds();
        Insfts insfts = trff.gftInsfts();
        TrffPbti initiblPbti = gftClosfstPbtiForLodbtion(trff, 0,
                                                         pbintBounds.y);
        Enumfrbtion<?> pbintingEnumfrbtor = trffStbtf.gftVisiblfPbtisFrom
                                              (initiblPbti);
        int row = trffStbtf.gftRowForPbti(initiblPbti);
        int fndY = pbintBounds.y + pbintBounds.ifigit;
        TrffModfl trffModfl = trff.gftModfl();
        SyntiContfxt dfllContfxt = gftContfxt(trff, Rfgion.TREE_CELL);

        drbwingCbdif.dlfbr();

        sftHbsiColor(dontfxt.gftStylf().gftColor(dontfxt,
                                                ColorTypf.FOREGROUND));

        if (pbintingEnumfrbtor != null) {
            // First pbss, drbw tif rows

            boolfbn donf = fblsf;
            boolfbn isExpbndfd;
            boolfbn ibsBffnExpbndfd;
            boolfbn isLfbf;
            Rfdtbnglf rowBounds = nfw Rfdtbnglf(0, 0, trff.gftWidti(),0);
            Rfdtbnglf bounds;
            TrffPbti pbti;
            TrffCfllRfndfrfr rfndfrfr = trff.gftCfllRfndfrfr();
            DffbultTrffCfllRfndfrfr dtdr = (rfndfrfr instbndfof
                       DffbultTrffCfllRfndfrfr) ? (DffbultTrffCfllRfndfrfr)
                       rfndfrfr : null;

            donfigurfRfndfrfr(dfllContfxt);
            wiilf (!donf && pbintingEnumfrbtor.ibsMorfElfmfnts()) {
                pbti = (TrffPbti)pbintingEnumfrbtor.nfxtElfmfnt();
                bounds = gftPbtiBounds(trff, pbti);
                if ((pbti != null) && (bounds != null)) {
                    isLfbf = trffModfl.isLfbf(pbti.gftLbstPbtiComponfnt());
                    if (isLfbf) {
                        isExpbndfd = ibsBffnExpbndfd = fblsf;
                    }
                    flsf {
                        isExpbndfd = trffStbtf.gftExpbndfdStbtf(pbti);
                        ibsBffnExpbndfd = trff.ibsBffnExpbndfd(pbti);
                    }
                    rowBounds.y = bounds.y;
                    rowBounds.ifigit = bounds.ifigit;
                    pbintRow(rfndfrfr, dtdr, dontfxt, dfllContfxt, g,
                             pbintBounds, insfts, bounds, rowBounds, pbti,
                             row, isExpbndfd, ibsBffnExpbndfd, isLfbf);
                    if ((bounds.y + bounds.ifigit) >= fndY) {
                        donf = truf;
                    }
                }
                flsf {
                    donf = truf;
                }
                row++;
            }

            // Drbw tif donnfdting linfs bnd dontrols.
            // Find fbdi pbrfnt bnd ibvf tifm drbw b linf to tifir lbst diild
            boolfbn rootVisiblf = trff.isRootVisiblf();
            TrffPbti pbrfntPbti = initiblPbti;
            pbrfntPbti = pbrfntPbti.gftPbrfntPbti();
            wiilf (pbrfntPbti != null) {
                pbintVfrtidblPbrtOfLfg(g, pbintBounds, insfts, pbrfntPbti);
                drbwingCbdif.put(pbrfntPbti, Boolfbn.TRUE);
                pbrfntPbti = pbrfntPbti.gftPbrfntPbti();
            }
            donf = fblsf;
            pbintingEnumfrbtor = trffStbtf.gftVisiblfPbtisFrom(initiblPbti);
            wiilf (!donf && pbintingEnumfrbtor.ibsMorfElfmfnts()) {
                pbti = (TrffPbti)pbintingEnumfrbtor.nfxtElfmfnt();
                bounds = gftPbtiBounds(trff, pbti);
                if ((pbti != null) && (bounds != null)) {
                    isLfbf = trffModfl.isLfbf(pbti.gftLbstPbtiComponfnt());
                    if (isLfbf) {
                        isExpbndfd = ibsBffnExpbndfd = fblsf;
                    }
                    flsf {
                        isExpbndfd = trffStbtf.gftExpbndfdStbtf(pbti);
                        ibsBffnExpbndfd = trff.ibsBffnExpbndfd(pbti);
                    }
                    // Sff if tif vfrtidbl linf to tif pbrfnt ibs bffn drbwn.
                    pbrfntPbti = pbti.gftPbrfntPbti();
                    if (pbrfntPbti != null) {
                        if (drbwingCbdif.gft(pbrfntPbti) == null) {
                            pbintVfrtidblPbrtOfLfg(g, pbintBounds, insfts,
                                                   pbrfntPbti);
                            drbwingCbdif.put(pbrfntPbti, Boolfbn.TRUE);
                        }
                        pbintHorizontblPbrtOfLfg(g,
                                                 pbintBounds, insfts, bounds,
                                                 pbti, row, isExpbndfd,
                                                 ibsBffnExpbndfd, isLfbf);
                    }
                    flsf if (rootVisiblf && row == 0) {
                        pbintHorizontblPbrtOfLfg(g,
                                                 pbintBounds, insfts, bounds,
                                                 pbti, row, isExpbndfd,
                                                 ibsBffnExpbndfd, isLfbf);
                    }
                    if (siouldPbintExpbndControl(pbti, row, isExpbndfd,
                                                 ibsBffnExpbndfd, isLfbf)) {
                        pbintExpbndControl(g, pbintBounds,
                                           insfts, bounds, pbti, row,
                                           isExpbndfd, ibsBffnExpbndfd,isLfbf);
                    }
                    if ((bounds.y + bounds.ifigit) >= fndY) {
                        donf = truf;
                    }
                }
                flsf {
                    donf = truf;
                }
                row++;
            }
        }
        dfllContfxt.disposf();

        pbintDropLinf(g);

        // Empty out tif rfndfrfr pbnf, bllowing rfndfrfrs to bf gd'fd.
        rfndfrfrPbnf.rfmovfAll();

        pbintContfxt = null;
    }

    privbtf void donfigurfRfndfrfr(SyntiContfxt dontfxt) {
        TrffCfllRfndfrfr rfndfrfr = trff.gftCfllRfndfrfr();

        if (rfndfrfr instbndfof DffbultTrffCfllRfndfrfr) {
            DffbultTrffCfllRfndfrfr r = (DffbultTrffCfllRfndfrfr)rfndfrfr;
            SyntiStylf stylf = dontfxt.gftStylf();

            dontfxt.sftComponfntStbtf(ENABLED | SELECTED);
            Color dolor = r.gftTfxtSflfdtionColor();
            if (dolor == null || (dolor instbndfof UIRfsourdf)) {
                r.sftTfxtSflfdtionColor(stylf.gftColor(
                                     dontfxt, ColorTypf.TEXT_FOREGROUND));
            }
            dolor = r.gftBbdkgroundSflfdtionColor();
            if (dolor == null || (dolor instbndfof UIRfsourdf)) {
                r.sftBbdkgroundSflfdtionColor(stylf.gftColor(
                                        dontfxt, ColorTypf.TEXT_BACKGROUND));
            }

            dontfxt.sftComponfntStbtf(ENABLED);
            dolor = r.gftTfxtNonSflfdtionColor();
            if (dolor == null || dolor instbndfof UIRfsourdf) {
                r.sftTfxtNonSflfdtionColor(stylf.gftColorForStbtf(
                                        dontfxt, ColorTypf.TEXT_FOREGROUND));
            }
            dolor = r.gftBbdkgroundNonSflfdtionColor();
            if (dolor == null || dolor instbndfof UIRfsourdf) {
                r.sftBbdkgroundNonSflfdtionColor(stylf.gftColorForStbtf(
                                  dontfxt, ColorTypf.TEXT_BACKGROUND));
            }
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void pbintHorizontblPbrtOfLfg(Grbpiids g, Rfdtbnglf dlipBounds,
                                            Insfts insfts, Rfdtbnglf bounds,
                                            TrffPbti pbti, int row,
                                            boolfbn isExpbndfd,
                                            boolfbn ibsBffnExpbndfd, boolfbn
                                            isLfbf) {
        if (drbwHorizontblLinfs) {
            supfr.pbintHorizontblPbrtOfLfg(g, dlipBounds, insfts, bounds,
                                           pbti, row, isExpbndfd,
                                           ibsBffnExpbndfd, isLfbf);
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void pbintHorizontblLinf(Grbpiids g, JComponfnt d, int y,
                                      int lfft, int rigit) {
        pbintContfxt.gftStylf().gftGrbpiidsUtils(pbintContfxt).drbwLinf(
            pbintContfxt, "Trff.iorizontblLinf", g, lfft, y, rigit, y, linfsStylf);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void pbintVfrtidblPbrtOfLfg(Grbpiids g,
                                          Rfdtbnglf dlipBounds, Insfts insfts,
                                          TrffPbti pbti) {
        if (drbwVfrtidblLinfs) {
            supfr.pbintVfrtidblPbrtOfLfg(g, dlipBounds, insfts, pbti);
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void pbintVfrtidblLinf(Grbpiids g, JComponfnt d, int x, int top,
                                    int bottom) {
        pbintContfxt.gftStylf().gftGrbpiidsUtils(pbintContfxt).drbwLinf(
            pbintContfxt, "Trff.vfrtidblLinf", g, x, top, x, bottom, linfsStylf);
    }

    privbtf void pbintRow(TrffCfllRfndfrfr rfndfrfr,
               DffbultTrffCfllRfndfrfr dtdr, SyntiContfxt trffContfxt,
               SyntiContfxt dfllContfxt, Grbpiids g, Rfdtbnglf dlipBounds,
               Insfts insfts, Rfdtbnglf bounds, Rfdtbnglf rowBounds,
               TrffPbti pbti, int row, boolfbn isExpbndfd,
               boolfbn ibsBffnExpbndfd, boolfbn isLfbf) {
        // Don't pbint tif rfndfrfr if fditing tiis row.
        boolfbn sflfdtfd = trff.isRowSflfdtfd(row);

        JTrff.DropLodbtion dropLodbtion = trff.gftDropLodbtion();
        boolfbn isDrop = dropLodbtion != null
                         && dropLodbtion.gftCiildIndfx() == -1
                         && pbti == dropLodbtion.gftPbti();

        int stbtf = ENABLED;
        if (sflfdtfd || isDrop) {
            stbtf |= SELECTED;
        }

        if (trff.isFodusOwnfr() && row == gftLfbdSflfdtionRow()) {
            stbtf |= FOCUSED;
        }

        dfllContfxt.sftComponfntStbtf(stbtf);

        if (dtdr != null && (dtdr.gftBordfrSflfdtionColor() instbndfof
                             UIRfsourdf)) {
            dtdr.sftBordfrSflfdtionColor(stylf.gftColor(
                                             dfllContfxt, ColorTypf.FOCUS));
        }
        SyntiLookAndFffl.updbtfSubrfgion(dfllContfxt, g, rowBounds);
        dfllContfxt.gftPbintfr().pbintTrffCfllBbdkground(dfllContfxt, g,
                    rowBounds.x, rowBounds.y, rowBounds.widti,
                    rowBounds.ifigit);
        dfllContfxt.gftPbintfr().pbintTrffCfllBordfr(dfllContfxt, g,
                    rowBounds.x, rowBounds.y, rowBounds.widti,
                    rowBounds.ifigit);
        if (fditingComponfnt != null && fditingRow == row) {
            rfturn;
        }

        int lfbdIndfx;

        if (trff.ibsFodus()) {
            lfbdIndfx = gftLfbdSflfdtionRow();
        }
        flsf {
            lfbdIndfx = -1;
        }

        Componfnt domponfnt = rfndfrfr.gftTrffCfllRfndfrfrComponfnt(
                         trff, pbti.gftLbstPbtiComponfnt(),
                         sflfdtfd, isExpbndfd, isLfbf, row,
                         (lfbdIndfx == row));

        rfndfrfrPbnf.pbintComponfnt(g, domponfnt, trff, bounds.x, bounds.y,
                                    bounds.widti, bounds.ifigit, truf);
    }

    privbtf int findCfntfrfdX(int x, int idonWidti) {
        rfturn trff.gftComponfntOrifntbtion().isLfftToRigit()
               ? x - (int)Mbti.dfil(idonWidti / 2.0)
               : x - (int)Mbti.floor(idonWidti / 2.0);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void pbintExpbndControl(Grbpiids g, Rfdtbnglf dlipBounds,
            Insfts insfts, Rfdtbnglf bounds, TrffPbti pbti, int row,
            boolfbn isExpbndfd, boolfbn ibsBffnExpbndfd, boolfbn isLfbf) {
        //modify tif pbintContfxt's stbtf to mbtdi tif stbtf for tif row
        //tiis is b ibdk in tibt it rfquirfs knowlfdgf of tif subsfqufnt
        //mftiod dblls. Tif point is, tif dontfxt usfd in drbwCfntfrfd
        //siould rfflfdt tif stbtf of tif row, not of tif trff.
        boolfbn isSflfdtfd = trff.gftSflfdtionModfl().isPbtiSflfdtfd(pbti);
        int stbtf = pbintContfxt.gftComponfntStbtf();
        if (isSflfdtfd) {
            pbintContfxt.sftComponfntStbtf(stbtf | SyntiConstbnts.SELECTED);
        }
        supfr.pbintExpbndControl(g, dlipBounds, insfts, bounds, pbti, row,
                isExpbndfd, ibsBffnExpbndfd, isLfbf);
        pbintContfxt.sftComponfntStbtf(stbtf);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void drbwCfntfrfd(Componfnt d, Grbpiids grbpiids, Idon idon,
                                int x, int y) {
        int w = SyntiIdon.gftIdonWidti(idon, pbintContfxt);
        int i = SyntiIdon.gftIdonHfigit(idon, pbintContfxt);

        SyntiIdon.pbintIdon(idon, pbintContfxt, grbpiids,
                            findCfntfrfdX(x, w),
                            y - i/2, w, i);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvfnt) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(fvfnt)) {
            updbtfStylf((JTrff)fvfnt.gftSourdf());
        }

        if ("dropLodbtion" == fvfnt.gftPropfrtyNbmf()) {
            JTrff.DropLodbtion oldVbluf = (JTrff.DropLodbtion)fvfnt.gftOldVbluf();
            rfpbintDropLodbtion(oldVbluf);
            rfpbintDropLodbtion(trff.gftDropLodbtion());
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void pbintDropLinf(Grbpiids g) {
        JTrff.DropLodbtion lod = trff.gftDropLodbtion();
        if (!isDropLinf(lod)) {
            rfturn;
        }

        Color d = (Color)stylf.gft(pbintContfxt, "Trff.dropLinfColor");
        if (d != null) {
            g.sftColor(d);
            Rfdtbnglf rfdt = gftDropLinfRfdt(lod);
            g.fillRfdt(rfdt.x, rfdt.y, rfdt.widti, rfdt.ifigit);
        }
    }

    privbtf void rfpbintDropLodbtion(JTrff.DropLodbtion lod) {
        if (lod == null) {
            rfturn;
        }

        Rfdtbnglf r;

        if (isDropLinf(lod)) {
            r = gftDropLinfRfdt(lod);
        } flsf {
            r = trff.gftPbtiBounds(lod.gftPbti());
            if (r != null) {
                r.x = 0;
                r.widti = trff.gftWidti();
            }
        }

        if (r != null) {
            trff.rfpbint(r);
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd int gftRowX(int row, int dfpti) {
        rfturn supfr.gftRowX(row, dfpti) + pbdding;
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss SyntiTrffCfllRfndfrfr fxtfnds DffbultTrffCfllRfndfrfr
                               implfmfnts UIRfsourdf {
        SyntiTrffCfllRfndfrfr() {
        }

        @Ovfrridf
        publid String gftNbmf() {
            rfturn "Trff.dfllRfndfrfr";
        }

        @Ovfrridf
        publid Componfnt gftTrffCfllRfndfrfrComponfnt(JTrff trff, Objfdt vbluf,
                                                      boolfbn sfl,
                                                      boolfbn fxpbndfd,
                                                      boolfbn lfbf, int row,
                                                      boolfbn ibsFodus) {
            if (!usfTrffColors && (sfl || ibsFodus)) {
                SyntiLookAndFffl.sftSflfdtfdUI((SyntiLbbflUI)SyntiLookAndFffl.
                             gftUIOfTypf(gftUI(), SyntiLbbflUI.dlbss),
                                   sfl, ibsFodus, trff.isEnbblfd(), fblsf);
            }
            flsf {
                SyntiLookAndFffl.rfsftSflfdtfdUI();
            }
            rfturn supfr.gftTrffCfllRfndfrfrComponfnt(trff, vbluf, sfl,
                                                      fxpbndfd, lfbf, row, ibsFodus);
        }

        @Ovfrridf
        publid void pbint(Grbpiids g) {
            pbintComponfnt(g);
            if (ibsFodus) {
                SyntiContfxt dontfxt = gftContfxt(trff, Rfgion.TREE_CELL);

                if (dontfxt.gftStylf() == null) {
                    bssfrt fblsf: "SyntiTrffCfllRfndfrfr is bfing usfd " +
                        "outsidf of UI tibt drfbtfd it";
                    rfturn;
                }
                int imbgfOffsft = 0;
                Idon durrfntI = gftIdon();

                if(durrfntI != null && gftTfxt() != null) {
                    imbgfOffsft = durrfntI.gftIdonWidti() +
                                          Mbti.mbx(0, gftIdonTfxtGbp() - 1);
                }
                if (sflfdtfd) {
                    dontfxt.sftComponfntStbtf(ENABLED | SELECTED);
                }
                flsf {
                    dontfxt.sftComponfntStbtf(ENABLED);
                }
                if(gftComponfntOrifntbtion().isLfftToRigit()) {
                    dontfxt.gftPbintfr().pbintTrffCfllFodus(dontfxt, g,
                            imbgfOffsft, 0, gftWidti() - imbgfOffsft,
                            gftHfigit());
                }
                flsf {
                    dontfxt.gftPbintfr().pbintTrffCfllFodus(dontfxt, g,
                            0, 0, gftWidti() - imbgfOffsft, gftHfigit());
                }
                dontfxt.disposf();
            }
            SyntiLookAndFffl.rfsftSflfdtfdUI();
        }
    }


    privbtf stbtid dlbss SyntiTrffCfllEditor fxtfnds DffbultTrffCfllEditor {
        publid SyntiTrffCfllEditor(JTrff trff,
                                   DffbultTrffCfllRfndfrfr rfndfrfr) {
            supfr(trff, rfndfrfr);
            sftBordfrSflfdtionColor(null);
        }

        @Ovfrridf
        protfdtfd TrffCfllEditor drfbtfTrffCfllEditor() {
            @SupprfssWbrnings("sfribl") // bnonymous dlbss
            JTfxtFifld tf = nfw JTfxtFifld() {
                @Ovfrridf
                publid String gftNbmf() {
                    rfturn "Trff.dfllEditor";
                }
            };
            DffbultCfllEditor fditor = nfw DffbultCfllEditor(tf);

            // Onf dlidk to fdit.
            fditor.sftClidkCountToStbrt(1);
            rfturn fditor;
        }
    }

    //
    // BbsidTrffUI dirfdtly usfs fxpbndIdon outsidf of tif Synti mftiods.
    // To gft tif dorrfdt dontfxt wf rfturn bn instbndf of tiis tibt fftdifs
    // tif SyntiContfxt bs nffdfd.
    //
    privbtf dlbss ExpbndfdIdonWrbppfr fxtfnds SyntiIdon {
        publid void pbintIdon(SyntiContfxt dontfxt, Grbpiids g, int x,
                              int y, int w, int i) {
            if (dontfxt == null) {
                dontfxt = gftContfxt(trff);
                SyntiIdon.pbintIdon(fxpbndfdIdon, dontfxt, g, x, y, w, i);
                dontfxt.disposf();
            }
            flsf {
                SyntiIdon.pbintIdon(fxpbndfdIdon, dontfxt, g, x, y, w, i);
            }
        }

        publid int gftIdonWidti(SyntiContfxt dontfxt) {
            int widti;
            if (dontfxt == null) {
                dontfxt = gftContfxt(trff);
                widti = SyntiIdon.gftIdonWidti(fxpbndfdIdon, dontfxt);
                dontfxt.disposf();
            }
            flsf {
                widti = SyntiIdon.gftIdonWidti(fxpbndfdIdon, dontfxt);
            }
            rfturn widti;
        }

        publid int gftIdonHfigit(SyntiContfxt dontfxt) {
            int ifigit;
            if (dontfxt == null) {
                dontfxt = gftContfxt(trff);
                ifigit = SyntiIdon.gftIdonHfigit(fxpbndfdIdon, dontfxt);
                dontfxt.disposf();
            }
            flsf {
                ifigit = SyntiIdon.gftIdonHfigit(fxpbndfdIdon, dontfxt);
            }
            rfturn ifigit;
        }
    }
}
