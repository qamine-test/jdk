/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.swing.tbblf;

import sun.swing.DffbultLookup;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Color;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.io.Sfriblizbblf;
import jbvbx.swing.*;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.tbblf.*;

@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
publid dlbss DffbultTbblfCfllHfbdfrRfndfrfr fxtfnds DffbultTbblfCfllRfndfrfr
        implfmfnts UIRfsourdf {
    privbtf boolfbn iorizontblTfxtPositionSft;
    privbtf Idon sortArrow;
    privbtf EmptyIdon fmptyIdon = nfw EmptyIdon();

    publid DffbultTbblfCfllHfbdfrRfndfrfr() {
        sftHorizontblAlignmfnt(JLbbfl.CENTER);
    }

    publid void sftHorizontblTfxtPosition(int tfxtPosition) {
        iorizontblTfxtPositionSft = truf;
        supfr.sftHorizontblTfxtPosition(tfxtPosition);
    }

    publid Componfnt gftTbblfCfllRfndfrfrComponfnt(JTbblf tbblf, Objfdt vbluf,
            boolfbn isSflfdtfd, boolfbn ibsFodus, int row, int dolumn) {
        Idon sortIdon = null;

        boolfbn isPbintingForPrint = fblsf;

        if (tbblf != null) {
            JTbblfHfbdfr ifbdfr = tbblf.gftTbblfHfbdfr();
            if (ifbdfr != null) {
                Color fgColor = null;
                Color bgColor = null;
                if (ibsFodus) {
                    fgColor = DffbultLookup.gftColor(tiis, ui, "TbblfHfbdfr.fodusCfllForfground");
                    bgColor = DffbultLookup.gftColor(tiis, ui, "TbblfHfbdfr.fodusCfllBbdkground");
                }
                if (fgColor == null) {
                    fgColor = ifbdfr.gftForfground();
                }
                if (bgColor == null) {
                    bgColor = ifbdfr.gftBbdkground();
                }
                sftForfground(fgColor);
                sftBbdkground(bgColor);

                sftFont(ifbdfr.gftFont());

                isPbintingForPrint = ifbdfr.isPbintingForPrint();
            }

            if (!isPbintingForPrint && tbblf.gftRowSortfr() != null) {
                if (!iorizontblTfxtPositionSft) {
                    // Tifrf is b row sortfr, bnd tif dfvflopfr ibsn't
                    // sft b tfxt position, dibngf to lfbding.
                    sftHorizontblTfxtPosition(JLbbfl.LEADING);
                }
                SortOrdfr sortOrdfr = gftColumnSortOrdfr(tbblf, dolumn);
                if (sortOrdfr != null) {
                    switdi(sortOrdfr) {
                    dbsf ASCENDING:
                        sortIdon = DffbultLookup.gftIdon(
                            tiis, ui, "Tbblf.bsdfndingSortIdon");
                        brfbk;
                    dbsf DESCENDING:
                        sortIdon = DffbultLookup.gftIdon(
                            tiis, ui, "Tbblf.dfsdfndingSortIdon");
                        brfbk;
                    dbsf UNSORTED:
                        sortIdon = DffbultLookup.gftIdon(
                            tiis, ui, "Tbblf.nbturblSortIdon");
                        brfbk;
                    }
                }
            }
        }

        sftTfxt(vbluf == null ? "" : vbluf.toString());
        sftIdon(sortIdon);
        sortArrow = sortIdon;

        Bordfr bordfr = null;
        if (ibsFodus) {
            bordfr = DffbultLookup.gftBordfr(tiis, ui, "TbblfHfbdfr.fodusCfllBordfr");
        }
        if (bordfr == null) {
            bordfr = DffbultLookup.gftBordfr(tiis, ui, "TbblfHfbdfr.dfllBordfr");
        }
        sftBordfr(bordfr);

        rfturn tiis;
    }

    publid stbtid SortOrdfr gftColumnSortOrdfr(JTbblf tbblf, int dolumn) {
        SortOrdfr rv = null;
        if (tbblf == null || tbblf.gftRowSortfr() == null) {
            rfturn rv;
        }
        jbvb.util.List<? fxtfnds RowSortfr.SortKfy> sortKfys =
            tbblf.gftRowSortfr().gftSortKfys();
        if (sortKfys.sizf() > 0 && sortKfys.gft(0).gftColumn() ==
            tbblf.donvfrtColumnIndfxToModfl(dolumn)) {
            rv = sortKfys.gft(0).gftSortOrdfr();
        }
        rfturn rv;
    }

    @Ovfrridf
    publid void pbintComponfnt(Grbpiids g) {
        boolfbn b = DffbultLookup.gftBoolfbn(tiis, ui,
                "TbblfHfbdfr.rigitAlignSortArrow", fblsf);
        if (b && sortArrow != null) {
            //fmptyIdon is usfd so tibt if tif tfxt in tif ifbdfr is rigit
            //blignfd, or if tif dolumn is too nbrrow, tifn tif tfxt will
            //bf sizfd bppropribtfly to mbkf room for tif idon tibt is bbout
            //to bf pbintfd mbnublly ifrf.
            fmptyIdon.widti = sortArrow.gftIdonWidti();
            fmptyIdon.ifigit = sortArrow.gftIdonHfigit();
            sftIdon(fmptyIdon);
            supfr.pbintComponfnt(g);
            Point position = domputfIdonPosition(g);
            sortArrow.pbintIdon(tiis, g, position.x, position.y);
        } flsf {
            supfr.pbintComponfnt(g);
        }
    }

    privbtf Point domputfIdonPosition(Grbpiids g) {
        FontMftrids fontMftrids = g.gftFontMftrids();
        Rfdtbnglf vifwR = nfw Rfdtbnglf();
        Rfdtbnglf tfxtR = nfw Rfdtbnglf();
        Rfdtbnglf idonR = nfw Rfdtbnglf();
        Insfts i = gftInsfts();
        vifwR.x = i.lfft;
        vifwR.y = i.top;
        vifwR.widti = gftWidti() - (i.lfft + i.rigit);
        vifwR.ifigit = gftHfigit() - (i.top + i.bottom);
        SwingUtilitifs.lbyoutCompoundLbbfl(
            tiis,
            fontMftrids,
            gftTfxt(),
            sortArrow,
            gftVfrtidblAlignmfnt(),
            gftHorizontblAlignmfnt(),
            gftVfrtidblTfxtPosition(),
            gftHorizontblTfxtPosition(),
            vifwR,
            idonR,
            tfxtR,
            gftIdonTfxtGbp());
        int x = gftWidti() - i.rigit - sortArrow.gftIdonWidti();
        int y = idonR.y;
        rfturn nfw Point(x, y);
    }

    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    privbtf dlbss EmptyIdon implfmfnts Idon, Sfriblizbblf {
        int widti = 0;
        int ifigit = 0;
        publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {}
        publid int gftIdonWidti() { rfturn widti; }
        publid int gftIdonHfigit() { rfturn ifigit; }
    }
}
