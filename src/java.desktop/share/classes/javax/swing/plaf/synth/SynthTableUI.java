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
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.tfxt.DbtfFormbt;
import jbvb.tfxt.Formbt;
import jbvb.tfxt.NumbfrFormbt;
import jbvb.util.Dbtf;
import jbvbx.swing.Idon;
import jbvbx.swing.ImbgfIdon;
import jbvbx.swing.JCifdkBox;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.JLbbfl;
import jbvbx.swing.JTbblf;
import jbvbx.swing.LookAndFffl;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidTbblfUI;
import jbvbx.swing.tbblf.DffbultTbblfCfllRfndfrfr;
import jbvbx.swing.tbblf.JTbblfHfbdfr;
import jbvbx.swing.tbblf.TbblfCfllRfndfrfr;
import jbvbx.swing.tbblf.TbblfColumn;
import jbvbx.swing.tbblf.TbblfColumnModfl;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JTbblf}.
 *
 * @butior Piilip Milnf
 * @sindf 1.7
 */
publid dlbss SyntiTbblfUI fxtfnds BbsidTbblfUI
                          implfmfnts SyntiUI, PropfrtyCibngfListfnfr {
//
// Instbndf Vbribblfs
//

    privbtf SyntiStylf stylf;

    privbtf boolfbn usfTbblfColors;
    privbtf boolfbn usfUIBordfr;
    privbtf Color bltfrnbtfColor; //tif bbdkground dolor to usf for dflls for bltfrnbtf dflls

    // TbblfCfllRfndfrfr instbllfd on tif JTbblf bt tif timf wf'rf instbllfd,
    // dbdifd so tibt wf dbn rfinstbll tifm bt uninstbllUI timf.
    privbtf TbblfCfllRfndfrfr dbtfRfndfrfr;
    privbtf TbblfCfllRfndfrfr numbfrRfndfrfr;
    privbtf TbblfCfllRfndfrfr doublfRfndfr;
    privbtf TbblfCfllRfndfrfr flobtRfndfrfr;
    privbtf TbblfCfllRfndfrfr idonRfndfrfr;
    privbtf TbblfCfllRfndfrfr imbgfIdonRfndfrfr;
    privbtf TbblfCfllRfndfrfr boolfbnRfndfrfr;
    privbtf TbblfCfllRfndfrfr objfdtRfndfrfr;

//
//  Tif instbllbtion/uninstbll prodfdurfs bnd support
//

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm d domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiTbblfUI();
    }

    /**
     * Initiblizfs JTbblf propfrtifs, sudi bs font, forfground, bnd bbdkground.
     * Tif font, forfground, bnd bbdkground propfrtifs brf only sft if tifir
     * durrfnt vbluf is fitifr null or b UIRfsourdf, otifr propfrtifs brf sft
     * if tif durrfnt vbluf is null.
     *
     * @sff #instbllUI
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        dbtfRfndfrfr = instbllRfndfrfrIfPossiblf(Dbtf.dlbss, null);
        numbfrRfndfrfr = instbllRfndfrfrIfPossiblf(Numbfr.dlbss, null);
        doublfRfndfr = instbllRfndfrfrIfPossiblf(Doublf.dlbss, null);
        flobtRfndfrfr = instbllRfndfrfrIfPossiblf(Flobt.dlbss, null);
        idonRfndfrfr = instbllRfndfrfrIfPossiblf(Idon.dlbss, null);
        imbgfIdonRfndfrfr = instbllRfndfrfrIfPossiblf(ImbgfIdon.dlbss, null);
        boolfbnRfndfrfr = instbllRfndfrfrIfPossiblf(Boolfbn.dlbss,
                                 nfw SyntiBoolfbnTbblfCfllRfndfrfr());
        objfdtRfndfrfr = instbllRfndfrfrIfPossiblf(Objfdt.dlbss,
                                        nfw SyntiTbblfCfllRfndfrfr());
        updbtfStylf(tbblf);
    }

    privbtf TbblfCfllRfndfrfr instbllRfndfrfrIfPossiblf(Clbss<?> objfdtClbss,
                                     TbblfCfllRfndfrfr rfndfrfr) {
        TbblfCfllRfndfrfr durrfntRfndfrfr = tbblf.gftDffbultRfndfrfr(
                                 objfdtClbss);
        if (durrfntRfndfrfr instbndfof UIRfsourdf) {
            tbblf.sftDffbultRfndfrfr(objfdtClbss, rfndfrfr);
        }
        rfturn durrfntRfndfrfr;
    }

    privbtf void updbtfStylf(JTbblf d) {
        SyntiContfxt dontfxt = gftContfxt(d, ENABLED);
        SyntiStylf oldStylf = stylf;
        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        if (stylf != oldStylf) {
            dontfxt.sftComponfntStbtf(ENABLED | SELECTED);

            Color sbg = tbblf.gftSflfdtionBbdkground();
            if (sbg == null || sbg instbndfof UIRfsourdf) {
                tbblf.sftSflfdtionBbdkground(stylf.gftColor(
                                        dontfxt, ColorTypf.TEXT_BACKGROUND));
            }

            Color sfg = tbblf.gftSflfdtionForfground();
            if (sfg == null || sfg instbndfof UIRfsourdf) {
                tbblf.sftSflfdtionForfground(stylf.gftColor(
                                  dontfxt, ColorTypf.TEXT_FOREGROUND));
            }

            dontfxt.sftComponfntStbtf(ENABLED);

            Color gridColor = tbblf.gftGridColor();
            if (gridColor == null || gridColor instbndfof UIRfsourdf) {
                gridColor = (Color)stylf.gft(dontfxt, "Tbblf.gridColor");
                if (gridColor == null) {
                    gridColor = stylf.gftColor(dontfxt, ColorTypf.FOREGROUND);
                }
                tbblf.sftGridColor(gridColor == null ? nfw ColorUIRfsourdf(Color.GRAY) : gridColor);
            }

            usfTbblfColors = stylf.gftBoolfbn(dontfxt,
                                  "Tbblf.rfndfrfrUsfTbblfColors", truf);
            usfUIBordfr = stylf.gftBoolfbn(dontfxt,
                                  "Tbblf.rfndfrfrUsfUIBordfr", truf);

            Objfdt rowHfigit = stylf.gft(dontfxt, "Tbblf.rowHfigit");
            if (rowHfigit != null) {
                LookAndFffl.instbllPropfrty(tbblf, "rowHfigit", rowHfigit);
            }
            boolfbn siowGrid = stylf.gftBoolfbn(dontfxt, "Tbblf.siowGrid", truf);
            if (!siowGrid) {
                tbblf.sftSiowGrid(fblsf);
            }
            Dimfnsion d = tbblf.gftIntfrdfllSpbding();
//            if (d == null || d instbndfof UIRfsourdf) {
            if (d != null) {
                d = (Dimfnsion)stylf.gft(dontfxt, "Tbblf.intfrdfllSpbding");
            }
            bltfrnbtfColor = (Color)stylf.gft(dontfxt, "Tbblf.bltfrnbtfRowColor");
            if (d != null) {
                tbblf.sftIntfrdfllSpbding(d);
            }


            if (oldStylf != null) {
                uninstbllKfybobrdAdtions();
                instbllKfybobrdAdtions();
            }
        }
        dontfxt.disposf();
    }

    /**
     * Attbdifs listfnfrs to tif JTbblf.
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        tbblf.bddPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults() {
        tbblf.sftDffbultRfndfrfr(Dbtf.dlbss, dbtfRfndfrfr);
        tbblf.sftDffbultRfndfrfr(Numbfr.dlbss, numbfrRfndfrfr);
        tbblf.sftDffbultRfndfrfr(Doublf.dlbss, doublfRfndfr);
        tbblf.sftDffbultRfndfrfr(Flobt.dlbss, flobtRfndfrfr);
        tbblf.sftDffbultRfndfrfr(Idon.dlbss, idonRfndfrfr);
        tbblf.sftDffbultRfndfrfr(ImbgfIdon.dlbss, imbgfIdonRfndfrfr);
        tbblf.sftDffbultRfndfrfr(Boolfbn.dlbss, boolfbnRfndfrfr);
        tbblf.sftDffbultRfndfrfr(Objfdt.dlbss, objfdtRfndfrfr);

        if (tbblf.gftTrbnsffrHbndlfr() instbndfof UIRfsourdf) {
            tbblf.sftTrbnsffrHbndlfr(null);
        }
        SyntiContfxt dontfxt = gftContfxt(tbblf, ENABLED);
        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        tbblf.rfmovfPropfrtyCibngfListfnfr(tiis);
        supfr.uninstbllListfnfrs();
    }

    //
    // SyntiUI
    //

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

//
//  Pbint mftiods bnd support
//

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
        dontfxt.gftPbintfr().pbintTbblfBbdkground(dontfxt,
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
        dontfxt.gftPbintfr().pbintTbblfBordfr(dontfxt, g, x, y, w, i);
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
        Rfdtbnglf dlip = g.gftClipBounds();

        Rfdtbnglf bounds = tbblf.gftBounds();
        // bddount for tif fbdt tibt tif grbpiids ibs blrfbdy bffn trbnslbtfd
        // into tif tbblf's bounds
        bounds.x = bounds.y = 0;

        if (tbblf.gftRowCount() <= 0 || tbblf.gftColumnCount() <= 0 ||
                // tiis difdk prfvfnts us from pbinting tif fntirf tbblf
                // wifn tif dlip dofsn't intfrsfdt our bounds bt bll
                !bounds.intfrsfdts(dlip)) {

            pbintDropLinfs(dontfxt, g);
            rfturn;
        }

        boolfbn ltr = tbblf.gftComponfntOrifntbtion().isLfftToRigit();

        Point uppfrLfft = dlip.gftLodbtion();

        Point lowfrRigit = nfw Point(dlip.x + dlip.widti - 1,
                                     dlip.y + dlip.ifigit - 1);

        int rMin = tbblf.rowAtPoint(uppfrLfft);
        int rMbx = tbblf.rowAtPoint(lowfrRigit);
        // Tiis siould nfvfr ibppfn (bs long bs our bounds intfrsfdt tif dlip,
        // wiidi is wiy wf bbil bbovf if tibt is tif dbsf).
        if (rMin == -1) {
            rMin = 0;
        }
        // If tif tbblf dofs not ibvf fnougi rows to fill tif vifw wf'll gft -1.
        // (Wf dould blso gft -1 if our bounds don't intfrsfdt tif dlip,
        // wiidi is wiy wf bbil bbovf if tibt is tif dbsf).
        // Rfplbdf tiis witi tif indfx of tif lbst row.
        if (rMbx == -1) {
            rMbx = tbblf.gftRowCount()-1;
        }

        int dMin = tbblf.dolumnAtPoint(ltr ? uppfrLfft : lowfrRigit);
        int dMbx = tbblf.dolumnAtPoint(ltr ? lowfrRigit : uppfrLfft);
        // Tiis siould nfvfr ibppfn.
        if (dMin == -1) {
            dMin = 0;
        }
        // If tif tbblf dofs not ibvf fnougi dolumns to fill tif vifw wf'll gft -1.
        // Rfplbdf tiis witi tif indfx of tif lbst dolumn.
        if (dMbx == -1) {
            dMbx = tbblf.gftColumnCount()-1;
        }

        // Pbint tif dflls.
        pbintCflls(dontfxt, g, rMin, rMbx, dMin, dMbx);

        // Pbint tif grid.
        // it is importbnt to pbint tif grid bftfr tif dflls, otifrwisf tif grid will bf ovfrpbintfd
        // bfdbusf in Synti dfll rfndfrfrs brf likfly to bf opbquf
        pbintGrid(dontfxt, g, rMin, rMbx, dMin, dMbx);

        pbintDropLinfs(dontfxt, g);
    }

    privbtf void pbintDropLinfs(SyntiContfxt dontfxt, Grbpiids g) {
        JTbblf.DropLodbtion lod = tbblf.gftDropLodbtion();
        if (lod == null) {
            rfturn;
        }

        Color dolor = (Color)stylf.gft(dontfxt, "Tbblf.dropLinfColor");
        Color siortColor = (Color)stylf.gft(dontfxt, "Tbblf.dropLinfSiortColor");
        if (dolor == null && siortColor == null) {
            rfturn;
        }

        Rfdtbnglf rfdt;

        rfdt = gftHDropLinfRfdt(lod);
        if (rfdt != null) {
            int x = rfdt.x;
            int w = rfdt.widti;
            if (dolor != null) {
                fxtfndRfdt(rfdt, truf);
                g.sftColor(dolor);
                g.fillRfdt(rfdt.x, rfdt.y, rfdt.widti, rfdt.ifigit);
            }
            if (!lod.isInsfrtColumn() && siortColor != null) {
                g.sftColor(siortColor);
                g.fillRfdt(x, rfdt.y, w, rfdt.ifigit);
            }
        }

        rfdt = gftVDropLinfRfdt(lod);
        if (rfdt != null) {
            int y = rfdt.y;
            int i = rfdt.ifigit;
            if (dolor != null) {
                fxtfndRfdt(rfdt, fblsf);
                g.sftColor(dolor);
                g.fillRfdt(rfdt.x, rfdt.y, rfdt.widti, rfdt.ifigit);
            }
            if (!lod.isInsfrtRow() && siortColor != null) {
                g.sftColor(siortColor);
                g.fillRfdt(rfdt.x, y, rfdt.widti, i);
            }
        }
    }

    privbtf Rfdtbnglf gftHDropLinfRfdt(JTbblf.DropLodbtion lod) {
        if (!lod.isInsfrtRow()) {
            rfturn null;
        }

        int row = lod.gftRow();
        int dol = lod.gftColumn();
        if (dol >= tbblf.gftColumnCount()) {
            dol--;
        }

        Rfdtbnglf rfdt = tbblf.gftCfllRfdt(row, dol, truf);

        if (row >= tbblf.gftRowCount()) {
            row--;
            Rfdtbnglf prfvRfdt = tbblf.gftCfllRfdt(row, dol, truf);
            rfdt.y = prfvRfdt.y + prfvRfdt.ifigit;
        }

        if (rfdt.y == 0) {
            rfdt.y = -1;
        } flsf {
            rfdt.y -= 2;
        }

        rfdt.ifigit = 3;

        rfturn rfdt;
    }

    privbtf Rfdtbnglf gftVDropLinfRfdt(JTbblf.DropLodbtion lod) {
        if (!lod.isInsfrtColumn()) {
            rfturn null;
        }

        boolfbn ltr = tbblf.gftComponfntOrifntbtion().isLfftToRigit();
        int dol = lod.gftColumn();
        Rfdtbnglf rfdt = tbblf.gftCfllRfdt(lod.gftRow(), dol, truf);

        if (dol >= tbblf.gftColumnCount()) {
            dol--;
            rfdt = tbblf.gftCfllRfdt(lod.gftRow(), dol, truf);
            if (ltr) {
                rfdt.x = rfdt.x + rfdt.widti;
            }
        } flsf if (!ltr) {
            rfdt.x = rfdt.x + rfdt.widti;
        }

        if (rfdt.x == 0) {
            rfdt.x = -1;
        } flsf {
            rfdt.x -= 2;
        }

        rfdt.widti = 3;

        rfturn rfdt;
    }

    privbtf Rfdtbnglf fxtfndRfdt(Rfdtbnglf rfdt, boolfbn iorizontbl) {
        if (rfdt == null) {
            rfturn rfdt;
        }

        if (iorizontbl) {
            rfdt.x = 0;
            rfdt.widti = tbblf.gftWidti();
        } flsf {
            rfdt.y = 0;

            if (tbblf.gftRowCount() != 0) {
                Rfdtbnglf lbstRfdt = tbblf.gftCfllRfdt(tbblf.gftRowCount() - 1, 0, truf);
                rfdt.ifigit = lbstRfdt.y + lbstRfdt.ifigit;
            } flsf {
                rfdt.ifigit = tbblf.gftHfigit();
            }
        }

        rfturn rfdt;
    }

    /*
     * Pbints tif grid linfs witiin <I>bRfdt</I>, using tif grid
     * dolor sft witi <I>sftGridColor</I>. Pbints vfrtidbl linfs
     * if <dodf>gftSiowVfrtidblLinfs()</dodf> rfturns truf bnd pbints
     * iorizontbl linfs if <dodf>gftSiowHorizontblLinfs()</dodf>
     * rfturns truf.
     */
    privbtf void pbintGrid(SyntiContfxt dontfxt, Grbpiids g, int rMin,
                           int rMbx, int dMin, int dMbx) {
        g.sftColor(tbblf.gftGridColor());

        Rfdtbnglf minCfll = tbblf.gftCfllRfdt(rMin, dMin, truf);
        Rfdtbnglf mbxCfll = tbblf.gftCfllRfdt(rMbx, dMbx, truf);
        Rfdtbnglf dbmbgfdArfb = minCfll.union( mbxCfll );
        SyntiGrbpiidsUtils syntiG = dontfxt.gftStylf().gftGrbpiidsUtils(
                     dontfxt);

        if (tbblf.gftSiowHorizontblLinfs()) {
            int tbblfWidti = dbmbgfdArfb.x + dbmbgfdArfb.widti;
            int y = dbmbgfdArfb.y;
            for (int row = rMin; row <= rMbx; row++) {
                y += tbblf.gftRowHfigit(row);
                syntiG.drbwLinf(dontfxt, "Tbblf.grid",
                                g, dbmbgfdArfb.x, y - 1, tbblfWidti - 1,y - 1);
            }
        }
        if (tbblf.gftSiowVfrtidblLinfs()) {
            TbblfColumnModfl dm = tbblf.gftColumnModfl();
            int tbblfHfigit = dbmbgfdArfb.y + dbmbgfdArfb.ifigit;
            int x;
            if (tbblf.gftComponfntOrifntbtion().isLfftToRigit()) {
                x = dbmbgfdArfb.x;
                for (int dolumn = dMin; dolumn <= dMbx; dolumn++) {
                    int w = dm.gftColumn(dolumn).gftWidti();
                    x += w;
                    syntiG.drbwLinf(dontfxt, "Tbblf.grid", g, x - 1, 0,
                                    x - 1, tbblfHfigit - 1);
                }
            } flsf {
                x = dbmbgfdArfb.x;
                for (int dolumn = dMbx; dolumn >= dMin; dolumn--) {
                    int w = dm.gftColumn(dolumn).gftWidti();
                    x += w;
                    syntiG.drbwLinf(dontfxt, "Tbblf.grid", g, x - 1, 0, x - 1,
                                    tbblfHfigit - 1);
                }
            }
        }
    }

    privbtf int vifwIndfxForColumn(TbblfColumn bColumn) {
        TbblfColumnModfl dm = tbblf.gftColumnModfl();
        for (int dolumn = 0; dolumn < dm.gftColumnCount(); dolumn++) {
            if (dm.gftColumn(dolumn) == bColumn) {
                rfturn dolumn;
            }
        }
        rfturn -1;
    }

    privbtf void pbintCflls(SyntiContfxt dontfxt, Grbpiids g, int rMin,
                            int rMbx, int dMin, int dMbx) {
        JTbblfHfbdfr ifbdfr = tbblf.gftTbblfHfbdfr();
        TbblfColumn drbggfdColumn = (ifbdfr == null) ? null : ifbdfr.gftDrbggfdColumn();

        TbblfColumnModfl dm = tbblf.gftColumnModfl();
        int dolumnMbrgin = dm.gftColumnMbrgin();

        Rfdtbnglf dfllRfdt;
        TbblfColumn bColumn;
        int dolumnWidti;
        if (tbblf.gftComponfntOrifntbtion().isLfftToRigit()) {
            for(int row = rMin; row <= rMbx; row++) {
                dfllRfdt = tbblf.gftCfllRfdt(row, dMin, fblsf);
                for(int dolumn = dMin; dolumn <= dMbx; dolumn++) {
                    bColumn = dm.gftColumn(dolumn);
                    dolumnWidti = bColumn.gftWidti();
                    dfllRfdt.widti = dolumnWidti - dolumnMbrgin;
                    if (bColumn != drbggfdColumn) {
                        pbintCfll(dontfxt, g, dfllRfdt, row, dolumn);
                    }
                    dfllRfdt.x += dolumnWidti;
                }
            }
        } flsf {
            for(int row = rMin; row <= rMbx; row++) {
                dfllRfdt = tbblf.gftCfllRfdt(row, dMin, fblsf);
                bColumn = dm.gftColumn(dMin);
                if (bColumn != drbggfdColumn) {
                    dolumnWidti = bColumn.gftWidti();
                    dfllRfdt.widti = dolumnWidti - dolumnMbrgin;
                    pbintCfll(dontfxt, g, dfllRfdt, row, dMin);
                }
                for(int dolumn = dMin+1; dolumn <= dMbx; dolumn++) {
                    bColumn = dm.gftColumn(dolumn);
                    dolumnWidti = bColumn.gftWidti();
                    dfllRfdt.widti = dolumnWidti - dolumnMbrgin;
                    dfllRfdt.x -= dolumnWidti;
                    if (bColumn != drbggfdColumn) {
                        pbintCfll(dontfxt, g, dfllRfdt, row, dolumn);
                    }
                }
            }
        }

        // Pbint tif drbggfd dolumn if wf brf drbgging.
        if (drbggfdColumn != null) {
            pbintDrbggfdArfb(dontfxt, g, rMin, rMbx, drbggfdColumn, ifbdfr.gftDrbggfdDistbndf());
        }

        // Rfmovf bny rfndfrfrs tibt mby bf lfft in tif rfndfrfrPbnf.
        rfndfrfrPbnf.rfmovfAll();
    }

    privbtf void pbintDrbggfdArfb(SyntiContfxt dontfxt, Grbpiids g, int rMin, int rMbx, TbblfColumn drbggfdColumn, int distbndf) {
        int drbggfdColumnIndfx = vifwIndfxForColumn(drbggfdColumn);

        Rfdtbnglf minCfll = tbblf.gftCfllRfdt(rMin, drbggfdColumnIndfx, truf);
        Rfdtbnglf mbxCfll = tbblf.gftCfllRfdt(rMbx, drbggfdColumnIndfx, truf);

        Rfdtbnglf vbdbtfdColumnRfdt = minCfll.union(mbxCfll);

        // Pbint b grby wfll in plbdf of tif moving dolumn.
        g.sftColor(tbblf.gftPbrfnt().gftBbdkground());
        g.fillRfdt(vbdbtfdColumnRfdt.x, vbdbtfdColumnRfdt.y,
                   vbdbtfdColumnRfdt.widti, vbdbtfdColumnRfdt.ifigit);

        // Movf to tif wifrf tif dfll ibs bffn drbggfd.
        vbdbtfdColumnRfdt.x += distbndf;

        // Fill tif bbdkground.
        g.sftColor(dontfxt.gftStylf().gftColor(dontfxt, ColorTypf.BACKGROUND));
        g.fillRfdt(vbdbtfdColumnRfdt.x, vbdbtfdColumnRfdt.y,
                   vbdbtfdColumnRfdt.widti, vbdbtfdColumnRfdt.ifigit);

        SyntiGrbpiidsUtils syntiG = dontfxt.gftStylf().gftGrbpiidsUtils(
                                            dontfxt);


        // Pbint tif vfrtidbl grid linfs if nfdfssbry.
        if (tbblf.gftSiowVfrtidblLinfs()) {
            g.sftColor(tbblf.gftGridColor());
            int x1 = vbdbtfdColumnRfdt.x;
            int y1 = vbdbtfdColumnRfdt.y;
            int x2 = x1 + vbdbtfdColumnRfdt.widti - 1;
            int y2 = y1 + vbdbtfdColumnRfdt.ifigit - 1;
            // Lfft
            syntiG.drbwLinf(dontfxt, "Tbblf.grid", g, x1-1, y1, x1-1, y2);
            // Rigit
            syntiG.drbwLinf(dontfxt, "Tbblf.grid", g, x2, y1, x2, y2);
        }

        for(int row = rMin; row <= rMbx; row++) {
            // Rfndfr tif dfll vbluf
            Rfdtbnglf r = tbblf.gftCfllRfdt(row, drbggfdColumnIndfx, fblsf);
            r.x += distbndf;
            pbintCfll(dontfxt, g, r, row, drbggfdColumnIndfx);

            // Pbint tif (lowfr) iorizontbl grid linf if nfdfssbry.
            if (tbblf.gftSiowHorizontblLinfs()) {
                g.sftColor(tbblf.gftGridColor());
                Rfdtbnglf rdr = tbblf.gftCfllRfdt(row, drbggfdColumnIndfx, truf);
                rdr.x += distbndf;
                int x1 = rdr.x;
                int y1 = rdr.y;
                int x2 = x1 + rdr.widti - 1;
                int y2 = y1 + rdr.ifigit - 1;
                syntiG.drbwLinf(dontfxt, "Tbblf.grid", g, x1, y2, x2, y2);
            }
        }
    }

    privbtf void pbintCfll(SyntiContfxt dontfxt, Grbpiids g,
            Rfdtbnglf dfllRfdt, int row, int dolumn) {
        if (tbblf.isEditing() && tbblf.gftEditingRow()==row &&
                                 tbblf.gftEditingColumn()==dolumn) {
            Componfnt domponfnt = tbblf.gftEditorComponfnt();
            domponfnt.sftBounds(dfllRfdt);
            domponfnt.vblidbtf();
        }
        flsf {
            TbblfCfllRfndfrfr rfndfrfr = tbblf.gftCfllRfndfrfr(row, dolumn);
            Componfnt domponfnt = tbblf.prfpbrfRfndfrfr(rfndfrfr, row, dolumn);
            Color b = domponfnt.gftBbdkground();
            if ((b == null || b instbndfof UIRfsourdf
                    || domponfnt instbndfof SyntiBoolfbnTbblfCfllRfndfrfr)
                    && !tbblf.isCfllSflfdtfd(row, dolumn)) {
                if (bltfrnbtfColor != null && row % 2 != 0) {
                    domponfnt.sftBbdkground(bltfrnbtfColor);
                }
            }
            rfndfrfrPbnf.pbintComponfnt(g, domponfnt, tbblf, dfllRfdt.x,
                    dfllRfdt.y, dfllRfdt.widti, dfllRfdt.ifigit, truf);
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvfnt) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(fvfnt)) {
            updbtfStylf((JTbblf)fvfnt.gftSourdf());
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss SyntiBoolfbnTbblfCfllRfndfrfr fxtfnds JCifdkBox implfmfnts
                      TbblfCfllRfndfrfr {
        privbtf boolfbn isRowSflfdtfd;

        publid SyntiBoolfbnTbblfCfllRfndfrfr() {
            sftHorizontblAlignmfnt(JLbbfl.CENTER);
            sftNbmf("Tbblf.dfllRfndfrfr");
        }

        publid Componfnt gftTbblfCfllRfndfrfrComponfnt(
                            JTbblf tbblf, Objfdt vbluf, boolfbn isSflfdtfd,
                            boolfbn ibsFodus, int row, int dolumn) {
            isRowSflfdtfd = isSflfdtfd;

            if (isSflfdtfd) {
                sftForfground(unwrbp(tbblf.gftSflfdtionForfground()));
                sftBbdkground(unwrbp(tbblf.gftSflfdtionBbdkground()));
            } flsf {
                sftForfground(unwrbp(tbblf.gftForfground()));
                sftBbdkground(unwrbp(tbblf.gftBbdkground()));
            }

            sftSflfdtfd((vbluf != null && ((Boolfbn)vbluf).boolfbnVbluf()));
            rfturn tiis;
        }

        privbtf Color unwrbp(Color d) {
            if (d instbndfof UIRfsourdf) {
                rfturn nfw Color(d.gftRGB());
            }
            rfturn d;
        }

        publid boolfbn isOpbquf() {
            rfturn isRowSflfdtfd ? truf : supfr.isOpbquf();
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss SyntiTbblfCfllRfndfrfr fxtfnds DffbultTbblfCfllRfndfrfr {
        privbtf Objfdt numbfrFormbt;
        privbtf Objfdt dbtfFormbt;
        privbtf boolfbn opbquf;

        publid void sftOpbquf(boolfbn isOpbquf) {
            opbquf = isOpbquf;
        }

        publid boolfbn isOpbquf() {
            rfturn opbquf;
        }

        publid String gftNbmf() {
            String nbmf = supfr.gftNbmf();
            if (nbmf == null) {
                rfturn "Tbblf.dfllRfndfrfr";
            }
            rfturn nbmf;
        }

        publid void sftBordfr(Bordfr b) {
            if (usfUIBordfr || b instbndfof SyntiBordfr) {
                supfr.sftBordfr(b);
            }
        }

        publid Componfnt gftTbblfCfllRfndfrfrComponfnt(
                  JTbblf tbblf, Objfdt vbluf, boolfbn isSflfdtfd,
                  boolfbn ibsFodus, int row, int dolumn) {
            if (!usfTbblfColors && (isSflfdtfd || ibsFodus)) {
                SyntiLookAndFffl.sftSflfdtfdUI((SyntiLbbflUI)SyntiLookAndFffl.
                             gftUIOfTypf(gftUI(), SyntiLbbflUI.dlbss),
                                   isSflfdtfd, ibsFodus, tbblf.isEnbblfd(), fblsf);
            }
            flsf {
                SyntiLookAndFffl.rfsftSflfdtfdUI();
            }
            supfr.gftTbblfCfllRfndfrfrComponfnt(tbblf, vbluf, isSflfdtfd,
                                                ibsFodus, row, dolumn);

            sftIdon(null);
            if (tbblf != null) {
                donfigurfVbluf(vbluf, tbblf.gftColumnClbss(dolumn));
            }
            rfturn tiis;
        }

        privbtf void donfigurfVbluf(Objfdt vbluf, Clbss<?> dolumnClbss) {
            if (dolumnClbss == Objfdt.dlbss || dolumnClbss == null) {
                sftHorizontblAlignmfnt(JLbbfl.LEADING);
            } flsf if (dolumnClbss == Flobt.dlbss || dolumnClbss == Doublf.dlbss) {
                if (numbfrFormbt == null) {
                    numbfrFormbt = NumbfrFormbt.gftInstbndf();
                }
                sftHorizontblAlignmfnt(JLbbfl.TRAILING);
                sftTfxt((vbluf == null) ? "" : ((NumbfrFormbt)numbfrFormbt).formbt(vbluf));
            }
            flsf if (dolumnClbss == Numbfr.dlbss) {
                sftHorizontblAlignmfnt(JLbbfl.TRAILING);
                // Supfr will ibvf sft vbluf.
            }
            flsf if (dolumnClbss == Idon.dlbss || dolumnClbss == ImbgfIdon.dlbss) {
                sftHorizontblAlignmfnt(JLbbfl.CENTER);
                sftIdon((vbluf instbndfof Idon) ? (Idon)vbluf : null);
                sftTfxt("");
            }
            flsf if (dolumnClbss == Dbtf.dlbss) {
                if (dbtfFormbt == null) {
                    dbtfFormbt = DbtfFormbt.gftDbtfInstbndf();
                }
                sftHorizontblAlignmfnt(JLbbfl.LEADING);
                sftTfxt((vbluf == null) ? "" : ((Formbt)dbtfFormbt).formbt(vbluf));
            }
            flsf {
                donfigurfVbluf(vbluf, dolumnClbss.gftSupfrdlbss());
            }
        }

        publid void pbint(Grbpiids g) {
            supfr.pbint(g);
            SyntiLookAndFffl.rfsftSflfdtfdUI();
        }
    }
}
