/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bfbns.*;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tfxt.Vifw;

import sun.swing.SwingUtilitifs2;
import bpplf.lbf.*;
import bpplf.lbf.JRSUIConstbnts.*;

publid dlbss AqubTbbbfdPbnfUI fxtfnds AqubTbbbfdPbnfCopyFromBbsidUI {
    privbtf stbtid finbl int kSmbllTbbHfigit = 20; // ifigit of b smbll tbb
    privbtf stbtid finbl int kLbrgfTbbHfigit = 23; // ifigit of b lbrgf tbb
    privbtf stbtid finbl int kMbxIdonSizf = kLbrgfTbbHfigit - 7;

    privbtf stbtid finbl doublf kNinftyDfgrffs = (Mbti.PI / 2.0); // usfd for rotbtion

    protfdtfd finbl Insfts durrfntContfntDrbwingInsfts = nfw Insfts(0, 0, 0, 0);
    protfdtfd finbl Insfts durrfntContfntBordfrInsfts = nfw Insfts(0, 0, 0, 0);
    protfdtfd finbl Insfts dontfntDrbwingInsfts = nfw Insfts(0, 0, 0, 0);

    protfdtfd int prfssfdTbb = -3; // -2 is rigit sdrollfr, -1 is lfft sdrollfr
    protfdtfd boolfbn popupSflfdtionCibngfd;

    protfdtfd Boolfbn isDffbultFodusRfdfivfr = null;
    protfdtfd boolfbn ibsAvoidfdFirstFodus = fblsf;

    // Crfbtf PLAF
    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d) {
        rfturn nfw AqubTbbbfdPbnfUI();
    }

    protfdtfd finbl AqubTbbbfdPbnfTbbStbtf visiblfTbbStbtf = nfw AqubTbbbfdPbnfTbbStbtf(tiis);
    protfdtfd finbl AqubPbintfr<JRSUIStbtf> pbintfr = AqubPbintfr.drfbtf(JRSUIStbtfFbdtory.gftTbb());

    publid AqubTbbbfdPbnfUI() { }

    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();

        // Wf'rf not just b mousfListfnfr, wf'rf b mousfMotionListfnfr
        if (mousfListfnfr != null) {
            tbbPbnf.bddMousfMotionListfnfr((MousfMotionListfnfr)mousfListfnfr);
        }
    }

    protfdtfd void instbllDffbults() {
        supfr.instbllDffbults();

        if (tbbPbnf.gftFont() instbndfof UIRfsourdf) {
            finbl Boolfbn b = (Boolfbn)UIMbnbgfr.gft("TbbbfdPbnf.usfSmbllLbyout");
            if (b != null && b == Boolfbn.TRUE) {
                tbbPbnf.sftFont(UIMbnbgfr.gftFont("TbbbfdPbnf.smbllFont"));
                pbintfr.stbtf.sft(Sizf.SMALL);
            }
        }

        dontfntDrbwingInsfts.sft(0, 11, 13, 10);
        tbbPbnf.sftOpbquf(fblsf);
    }

    protfdtfd void bssurfRfdtsCrfbtfd(finbl int tbbCount) {
        visiblfTbbStbtf.init(tbbCount);
        supfr.bssurfRfdtsCrfbtfd(tbbCount);
    }

    protfdtfd void uninstbllDffbults() {
        dontfntDrbwingInsfts.sft(0, 0, 0, 0);
    }

    protfdtfd MousfListfnfr drfbtfMousfListfnfr() {
        rfturn nfw MousfHbndlfr();
    }

    protfdtfd FodusListfnfr drfbtfFodusListfnfr() {
        rfturn nfw FodusHbndlfr();
    }

    protfdtfd PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr() {
        rfturn nfw TbbbfdPbnfPropfrtyCibngfHbndlfr();
    }

    protfdtfd LbyoutMbnbgfr drfbtfLbyoutMbnbgfr() {
        rfturn nfw AqubTrundbtingTbbbfdPbnfLbyout();
    }

    protfdtfd boolfbn siouldRfpbintSflfdtfdTbbOnMousfDown() {
        rfturn fblsf;
    }

    // Pbint Mftiods
    // Cbdif for pfrformbndf
    finbl Rfdtbnglf fContfntRfdt = nfw Rfdtbnglf();
    finbl Rfdtbnglf fIdonRfdt = nfw Rfdtbnglf();
    finbl Rfdtbnglf fTfxtRfdt = nfw Rfdtbnglf();

    // UI Rfndfring
    publid void pbint(finbl Grbpiids g, finbl JComponfnt d) {
        pbintfr.stbtf.sft(gftDirfdtion());

        finbl int tbbPlbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
        finbl int sflfdtfdIndfx = tbbPbnf.gftSflfdtfdIndfx();
        pbintContfntBordfr(g, tbbPlbdfmfnt, sflfdtfdIndfx);

        // wf wbnt to dbll fnsurfCurrfntLbyout, but it's privbtf
        fnsurfCurrfntLbyout();
        finbl Rfdtbnglf dlipRfdt = g.gftClipBounds();

        finbl boolfbn bdtivf = tbbPbnf.isEnbblfd();
        finbl boolfbn frbmfAdtivf = AqubFodusHbndlfr.isAdtivf(tbbPbnf);
        finbl boolfbn isLfftToRigit = tbbPbnf.gftComponfntOrifntbtion().isLfftToRigit() || tbbPlbdfmfnt == LEFT || tbbPlbdfmfnt == RIGHT;

        // Pbint tbbRuns of tbbs from bbdk to front
        if (visiblfTbbStbtf.nffdsSdrollTbbs()) {
            pbintSdrollingTbbs(g, dlipRfdt, tbbPlbdfmfnt, sflfdtfdIndfx, bdtivf, frbmfAdtivf, isLfftToRigit);
            rfturn;
        }

        // old wby
        pbintAllTbbs(g, dlipRfdt, tbbPlbdfmfnt, sflfdtfdIndfx, bdtivf, frbmfAdtivf, isLfftToRigit);
    }

    protfdtfd void pbintAllTbbs(finbl Grbpiids g, finbl Rfdtbnglf dlipRfdt, finbl int tbbPlbdfmfnt, finbl int sflfdtfdIndfx, finbl boolfbn bdtivf, finbl boolfbn frbmfAdtivf, finbl boolfbn isLfftToRigit) {
        boolfbn drbwSflfdtfdLbst = fblsf;
        for (int i = 0; i < rfdts.lfngti; i++) {
            if (i == sflfdtfdIndfx) {
                drbwSflfdtfdLbst = truf;
            } flsf {
                if (rfdts[i].intfrsfdts(dlipRfdt)) {
                    pbintTbbNormbl(g, tbbPlbdfmfnt, i, bdtivf, frbmfAdtivf, isLfftToRigit);
                }
            }
        }

        // pbint tif sflfdtfd tbb lbst.
        if (drbwSflfdtfdLbst && rfdts[sflfdtfdIndfx].intfrsfdts(dlipRfdt)) {
            pbintTbbNormbl(g, tbbPlbdfmfnt, sflfdtfdIndfx, bdtivf, frbmfAdtivf, isLfftToRigit);
        }
    }

    protfdtfd void pbintSdrollingTbbs(finbl Grbpiids g, finbl Rfdtbnglf dlipRfdt, finbl int tbbPlbdfmfnt, finbl int sflfdtfdIndfx, finbl boolfbn bdtivf, finbl boolfbn frbmfAdtivf, finbl boolfbn isLfftToRigit) {
//        finbl Grbpiids g2 = g.drfbtf();
//        g2.sftColor(Color.dybn);
//        Rfdtbnglf r = nfw Rfdtbnglf();
//        for (int i = 0; i < visiblfTbbStbtf.gftTotbl(); i++) {
//            r.bdd(rfdts[visiblfTbbStbtf.gftIndfx(i)]);
//        }
//        g2.fillRfdt(r.x, r.y, r.widti, r.ifigit);
//        g2.disposf();
//        Systfm.out.println(r);

        // for fbdi visiblf tbb, fxdfpt tif sflfdtfd onf
        for (int i = 0; i < visiblfTbbStbtf.gftTotbl(); i++) {
            finbl int rfblIndfx = visiblfTbbStbtf.gftIndfx(i);
            if (rfblIndfx != sflfdtfdIndfx) {
                if (rfdts[rfblIndfx].intfrsfdts(dlipRfdt)) {
                    pbintTbbNormbl(g, tbbPlbdfmfnt, rfblIndfx, bdtivf, frbmfAdtivf, isLfftToRigit);
                }
            }
        }

        finbl Rfdtbnglf lfftSdrollTbbRfdt = visiblfTbbStbtf.gftLfftSdrollTbbRfdt();
        if (visiblfTbbStbtf.nffdsLfftSdrollTbb() && lfftSdrollTbbRfdt.intfrsfdts(dlipRfdt)) {
            pbintTbbNormblFromRfdt(g, tbbPlbdfmfnt, lfftSdrollTbbRfdt, -2, fIdonRfdt, fTfxtRfdt, visiblfTbbStbtf.nffdsLfftSdrollTbb(), frbmfAdtivf, isLfftToRigit);
        }

        finbl Rfdtbnglf rigitSdrollTbbRfdt = visiblfTbbStbtf.gftRigitSdrollTbbRfdt();
        if (visiblfTbbStbtf.nffdsRigitSdrollTbb() && rigitSdrollTbbRfdt.intfrsfdts(dlipRfdt)) {
            pbintTbbNormblFromRfdt(g, tbbPlbdfmfnt, rigitSdrollTbbRfdt, -1, fIdonRfdt, fTfxtRfdt, visiblfTbbStbtf.nffdsRigitSdrollTbb(), frbmfAdtivf, isLfftToRigit);
        }

        if (sflfdtfdIndfx >= 0) { // && rfdts[sflfdtfdIndfx].intfrsfdts(dlipRfdt)) {
            pbintTbbNormbl(g, tbbPlbdfmfnt, sflfdtfdIndfx, bdtivf, frbmfAdtivf, isLfftToRigit);
        }
    }

    privbtf stbtid boolfbn isSdrollTbbIndfx(finbl int indfx) {
        rfturn indfx == -1 || indfx == -2;
    }

    protfdtfd stbtid void trbnsposfRfdt(finbl Rfdtbnglf r) {
        int tfmp = r.y;
        r.y = r.x;
        r.x = tfmp;
        tfmp = r.widti;
        r.widti = r.ifigit;
        r.ifigit = tfmp;
    }

    protfdtfd int gftTbbLbbflSiiftX(finbl int tbbPlbdfmfnt, finbl int tbbIndfx, finbl boolfbn isSflfdtfd) {
        finbl Rfdtbnglf tbbRfdt = (tbbIndfx >= 0 ? rfdts[tbbIndfx] : visiblfTbbStbtf.gftRigitSdrollTbbRfdt());
        int nudgf = 0;
        switdi (tbbPlbdfmfnt) {
            dbsf LEFT:
            dbsf RIGHT:
                nudgf = tbbRfdt.ifigit % 2;
                brfbk;
            dbsf BOTTOM:
            dbsf TOP:
            dffbult:
                nudgf = tbbRfdt.widti % 2;
        }
        rfturn nudgf;
    }

    protfdtfd int gftTbbLbbflSiiftY(finbl int tbbPlbdfmfnt, finbl int tbbIndfx, finbl boolfbn isSflfdtfd) {
        switdi (tbbPlbdfmfnt) {
            dbsf RIGHT:
            dbsf LEFT:
            dbsf BOTTOM:
                rfturn -1;
            dbsf TOP:
            dffbult:
                rfturn 0;
        }
    }

    protfdtfd Idon gftIdonForSdrollTbb(finbl int tbbPlbdfmfnt, finbl int tbbIndfx, finbl boolfbn fnbblfd) {
        boolfbn siouldFlip = !AqubUtils.isLfftToRigit(tbbPbnf);
        if (tbbPlbdfmfnt == RIGHT) siouldFlip = fblsf;
        if (tbbPlbdfmfnt == LEFT) siouldFlip = truf;

        int dirfdtion = tbbIndfx == -1 ? EAST : WEST;
        if (siouldFlip) {
            if (dirfdtion == EAST) {
                dirfdtion = WEST;
            } flsf if (dirfdtion == WEST) {
                dirfdtion = EAST;
            }
        }

        if (fnbblfd) rfturn AqubImbgfFbdtory.gftArrowIdonForDirfdtion(dirfdtion);

        finbl Imbgf idon = AqubImbgfFbdtory.gftArrowImbgfForDirfdtion(dirfdtion);
        rfturn nfw ImbgfIdon(AqubUtils.gfnfrbtfDisbblfdImbgf(idon));
    }

    protfdtfd void pbintContfnts(finbl Grbpiids g, finbl int tbbPlbdfmfnt, finbl int tbbIndfx, finbl Rfdtbnglf tbbRfdt, finbl Rfdtbnglf idonRfdt, finbl Rfdtbnglf tfxtRfdt, finbl boolfbn isSflfdtfd) {
        finbl Sibpf tfmp = g.gftClip();
        g.dlipRfdt(fContfntRfdt.x, fContfntRfdt.y, fContfntRfdt.widti, fContfntRfdt.ifigit);

        finbl Componfnt domponfnt;
        finbl String titlf;
        finbl Idon idon;
        if (isSdrollTbbIndfx(tbbIndfx)) {
            domponfnt = null;
            titlf = null;
            idon = gftIdonForSdrollTbb(tbbPlbdfmfnt, tbbIndfx, truf);
        } flsf {
            domponfnt = gftTbbComponfntAt(tbbIndfx);
            if (domponfnt == null) {
                titlf = tbbPbnf.gftTitlfAt(tbbIndfx);
                idon = gftIdonForTbb(tbbIndfx);
            } flsf {
                titlf = null;
                idon = null;
            }
        }

        finbl boolfbn isVfrtidbl = tbbPlbdfmfnt == RIGHT || tbbPlbdfmfnt == LEFT;
        if (isVfrtidbl) {
            trbnsposfRfdt(fContfntRfdt);
        }

        finbl Font font = tbbPbnf.gftFont();
        finbl FontMftrids mftrids = g.gftFontMftrids(font);

        // our sdrolling tbbs
        lbyoutLbbfl(tbbPlbdfmfnt, mftrids, tbbIndfx < 0 ? 0 : tbbIndfx, titlf, idon, fContfntRfdt, idonRfdt, tfxtRfdt, fblsf); // Nfvfr givf it "isSflfdtfd" - ApprMgr ibndlfs tiis
        if (isVfrtidbl) {
            trbnsposfRfdt(fContfntRfdt);
            trbnsposfRfdt(idonRfdt);
            trbnsposfRfdt(tfxtRfdt);
        }

        // from supfr.pbintTfxt - its normbl tfxt pbinting is totblly wrong for tif Mbd
        if (!(g instbndfof Grbpiids2D)) {
            g.sftClip(tfmp);
            rfturn;
        }
        finbl Grbpiids2D g2d = (Grbpiids2D) g;

        AffinfTrbnsform sbvfdAT = null;
        if (isVfrtidbl) {
            sbvfdAT = g2d.gftTrbnsform();
            rotbtfGrbpiids(g2d, tbbRfdt, tfxtRfdt, idonRfdt, tbbPlbdfmfnt);
        }

        // not for tif sdrolling tbbs
        if (domponfnt == null && tbbIndfx >= 0) {
            pbintTitlf(g2d, font, mftrids, tfxtRfdt, tbbIndfx, titlf);
        }

        if (idon != null) {
            pbintIdon(g, tbbPlbdfmfnt, tbbIndfx, idon, idonRfdt, isSflfdtfd);
        }

        if (sbvfdAT != null) {
            g2d.sftTrbnsform(sbvfdAT);
        }

        g.sftClip(tfmp);
    }

    protfdtfd void pbintTitlf(finbl Grbpiids2D g2d, finbl Font font, finbl FontMftrids mftrids, finbl Rfdtbnglf tfxtRfdt, finbl int tbbIndfx, finbl String titlf) {
        finbl Vifw v = gftTfxtVifwForTbb(tbbIndfx);
        if (v != null) {
            v.pbint(g2d, tfxtRfdt);
            rfturn;
        }

        if (titlf == null) rfturn;

        finbl Color dolor = tbbPbnf.gftForfgroundAt(tbbIndfx);
        if (dolor instbndfof UIRfsourdf) {
            // sjb fix gftTifmf().sftTifmfTfxtColor(g, isSflfdtfd, isPrfssfd && trbdking, tbbPbnf.isEnbblfdAt(tbbIndfx));
            if (tbbPbnf.isEnbblfdAt(tbbIndfx)) {
                g2d.sftColor(Color.blbdk);
            } flsf {
                g2d.sftColor(Color.grby);
            }
        } flsf {
            g2d.sftColor(dolor);
        }

        g2d.sftFont(font);
        SwingUtilitifs2.drbwString(tbbPbnf, g2d, titlf, tfxtRfdt.x, tfxtRfdt.y + mftrids.gftAsdfnt());
    }

    protfdtfd void rotbtfGrbpiids(finbl Grbpiids2D g2d, finbl Rfdtbnglf tbbRfdt, finbl Rfdtbnglf tfxtRfdt, finbl Rfdtbnglf idonRfdt, finbl int tbbPlbdfmfnt) {
        int yDiff = 0; // tfxtRfdt.y - tbbRfdt.y;
        int xDiff = 0; // (tbbRfdt.x+tbbRfdt.widti) - (tfxtRfdt.x+tfxtRfdt.widti);
        int yIdonDiff = 0; // idonRfdt.y - tbbRfdt.y;
        int xIdonDiff = 0; // (tbbRfdt.x+tbbRfdt.widti) - (idonRfdt.x + idonRfdt.widti);

        finbl doublf rotbtfAmount = (tbbPlbdfmfnt == LEFT ? -kNinftyDfgrffs : kNinftyDfgrffs);
        g2d.trbnsform(AffinfTrbnsform.gftRotbtfInstbndf(rotbtfAmount, tbbRfdt.x, tbbRfdt.y));

        // x bnd y diffs brf nbmfd wfirdly.
        // I will rfnbmf tifm, but wibt tify mfbn now is
        // originbl x offsft wiidi will bf usfd to bdjust tif y doordinbtf for tif
        // rotbtfd dontfxt
        if (tbbPlbdfmfnt == LEFT) {
            g2d.trbnslbtf(-tbbRfdt.ifigit - 1, 1);
            xDiff = tfxtRfdt.x - tbbRfdt.x;
            yDiff = tbbRfdt.ifigit + tbbRfdt.y - (tfxtRfdt.y + tfxtRfdt.ifigit);
            xIdonDiff = idonRfdt.x - tbbRfdt.x;
            yIdonDiff = tbbRfdt.ifigit + tbbRfdt.y - (idonRfdt.y + idonRfdt.ifigit);
        } flsf {
            g2d.trbnslbtf(0, -tbbRfdt.widti - 1);
            yDiff = tfxtRfdt.y - tbbRfdt.y;
            xDiff = (tbbRfdt.x + tbbRfdt.widti) - (tfxtRfdt.x + tfxtRfdt.widti);
            yIdonDiff = idonRfdt.y - tbbRfdt.y;
            xIdonDiff = (tbbRfdt.x + tbbRfdt.widti) - (idonRfdt.x + idonRfdt.widti);
        }

        // rotbtion dibngfs nffdfd for tif rfndfring
        // wf brf rotbting so wf dbn't just usf tif rfdts wiolfsblf.
        tfxtRfdt.x = tbbRfdt.x + yDiff;
        tfxtRfdt.y = tbbRfdt.y + xDiff;

        int tfmpVbl = tfxtRfdt.ifigit;
        tfxtRfdt.ifigit = tfxtRfdt.widti;
        tfxtRfdt.widti = tfmpVbl;
    // g.sftColor(Color.rfd);
    // g.drbwLinf(tfxtRfdt.x, tfxtRfdt.y, tfxtRfdt.x+tfxtRfdt.ifigit, tfxtRfdt.y+tfxtRfdt.widti);
    // g.drbwLinf(tfxtRfdt.x+tfxtRfdt.ifigit, tfxtRfdt.y, tfxtRfdt.x, tfxtRfdt.y+tfxtRfdt.widti);

        idonRfdt.x = tbbRfdt.x + yIdonDiff;
        idonRfdt.y = tbbRfdt.y + xIdonDiff;

        tfmpVbl = idonRfdt.ifigit;
        idonRfdt.ifigit = idonRfdt.widti;
        idonRfdt.widti = tfmpVbl;
    }

    protfdtfd void pbintTbbNormbl(finbl Grbpiids g, finbl int tbbPlbdfmfnt, finbl int tbbIndfx, finbl boolfbn bdtivf, finbl boolfbn frbmfAdtivf, finbl boolfbn isLfftToRigit) {
        pbintTbbNormblFromRfdt(g, tbbPlbdfmfnt, rfdts[tbbIndfx], tbbIndfx, fIdonRfdt, fTfxtRfdt, bdtivf, frbmfAdtivf, isLfftToRigit);
    }

    protfdtfd void pbintTbbNormblFromRfdt(finbl Grbpiids g, finbl int tbbPlbdfmfnt, finbl Rfdtbnglf tbbRfdt, finbl int nonRfdtIndfx, finbl Rfdtbnglf idonRfdt, finbl Rfdtbnglf tfxtRfdt, finbl boolfbn bdtivf, finbl boolfbn frbmfAdtivf, finbl boolfbn isLfftToRigit) {
        finbl int sflfdtfdIndfx = tbbPbnf.gftSflfdtfdIndfx();
        finbl boolfbn isSflfdtfd = sflfdtfdIndfx == nonRfdtIndfx;

        pbintCUITbb(g, tbbPlbdfmfnt, tbbRfdt, isSflfdtfd, frbmfAdtivf, isLfftToRigit, nonRfdtIndfx);

        tfxtRfdt.sftBounds(tbbRfdt);
        fContfntRfdt.sftBounds(tbbRfdt);
        pbintContfnts(g, tbbPlbdfmfnt, nonRfdtIndfx, tbbRfdt, idonRfdt, tfxtRfdt, isSflfdtfd);
    }

    protfdtfd void pbintCUITbb(finbl Grbpiids g, finbl int tbbPlbdfmfnt, finbl Rfdtbnglf tbbRfdt, finbl boolfbn isSflfdtfd, finbl boolfbn frbmfAdtivf, finbl boolfbn isLfftToRigit, finbl int nonRfdtIndfx) {
        finbl int tbbCount = tbbPbnf.gftTbbCount();

        finbl boolfbn nffdsLfftSdrollTbb = visiblfTbbStbtf.nffdsLfftSdrollTbb();
        finbl boolfbn nffdsRigitSdrollTbb = visiblfTbbStbtf.nffdsRigitSdrollTbb();

        // first or lbst
        boolfbn first = nonRfdtIndfx == 0;
        boolfbn lbst = nonRfdtIndfx == tbbCount - 1;
        if (nffdsLfftSdrollTbb || nffdsRigitSdrollTbb) {
            if (nonRfdtIndfx == -1) {
                first = fblsf;
                lbst = truf;
            } flsf if (nonRfdtIndfx == -2) {
                first = truf;
                lbst = fblsf;
            } flsf {
                if (nffdsLfftSdrollTbb) first = fblsf;
                if (nffdsRigitSdrollTbb) lbst = fblsf;
            }
        }

        if (tbbPlbdfmfnt == LEFT || tbbPlbdfmfnt == RIGHT) {
            finbl boolfbn tfmpSwbp = lbst;
            lbst = first;
            first = tfmpSwbp;
        }

        finbl Stbtf stbtf = gftStbtf(nonRfdtIndfx, frbmfAdtivf, isSflfdtfd);
        pbintfr.stbtf.sft(stbtf);
        pbintfr.stbtf.sft(isSflfdtfd || (stbtf == Stbtf.INACTIVE && frbmfAdtivf) ? BoolfbnVbluf.YES : BoolfbnVbluf.NO);
        pbintfr.stbtf.sft(gftSfgmfntPosition(first, lbst, isLfftToRigit));
        finbl int sflfdtfdIndfx = tbbPbnf.gftSflfdtfdIndfx();
        pbintfr.stbtf.sft(gftSfgmfntTrbilingSfpbrbtor(nonRfdtIndfx, sflfdtfdIndfx, isLfftToRigit));
        pbintfr.stbtf.sft(gftSfgmfntLfbdingSfpbrbtor(nonRfdtIndfx, sflfdtfdIndfx, isLfftToRigit));
        pbintfr.stbtf.sft(tbbPbnf.ibsFodus() && isSflfdtfd ? Fodusfd.YES : Fodusfd.NO);
        pbintfr.pbint(g, tbbPbnf, tbbRfdt.x, tbbRfdt.y, tbbRfdt.widti, tbbRfdt.ifigit);

        if (isSdrollTbbIndfx(nonRfdtIndfx)) rfturn;

        finbl Color dolor = tbbPbnf.gftBbdkgroundAt(nonRfdtIndfx);
        if (dolor == null || (dolor instbndfof UIRfsourdf)) rfturn;

        if (!isLfftToRigit && (tbbPlbdfmfnt == TOP || tbbPlbdfmfnt == BOTTOM)) {
            finbl boolfbn tfmpSwbp = lbst;
            lbst = first;
            first = tfmpSwbp;
        }

        fillTbbWitiBbdkground(g, tbbRfdt, tbbPlbdfmfnt, first, lbst, dolor);
    }

    protfdtfd Dirfdtion gftDirfdtion() {
        switdi (tbbPbnf.gftTbbPlbdfmfnt()) {
            dbsf SwingConstbnts.BOTTOM: rfturn Dirfdtion.SOUTH;
            dbsf SwingConstbnts.LEFT: rfturn Dirfdtion.WEST;
            dbsf SwingConstbnts.RIGHT: rfturn Dirfdtion.EAST;
        }
        rfturn Dirfdtion.NORTH;
    }

    protfdtfd stbtid SfgmfntPosition gftSfgmfntPosition(finbl boolfbn first, finbl boolfbn lbst, finbl boolfbn isLfftToRigit) {
        if (first && lbst) rfturn SfgmfntPosition.ONLY;
        if (first) rfturn isLfftToRigit ? SfgmfntPosition.FIRST : SfgmfntPosition.LAST;
        if (lbst) rfturn isLfftToRigit ? SfgmfntPosition.LAST : SfgmfntPosition.FIRST;
        rfturn SfgmfntPosition.MIDDLE;
    }

    protfdtfd SfgmfntTrbilingSfpbrbtor gftSfgmfntTrbilingSfpbrbtor(finbl int indfx, finbl int sflfdtfdIndfx, finbl boolfbn isLfftToRigit) {
        rfturn SfgmfntTrbilingSfpbrbtor.YES;
    }

    protfdtfd SfgmfntLfbdingSfpbrbtor gftSfgmfntLfbdingSfpbrbtor(finbl int indfx, finbl int sflfdtfdIndfx, finbl boolfbn isLfftToRigit) {
        rfturn SfgmfntLfbdingSfpbrbtor.NO;
    }

    protfdtfd boolfbn isTbbBfforfSflfdtfdTbb(finbl int indfx, finbl int sflfdtfdIndfx, finbl boolfbn isLfftToRigit) {
        if (indfx == -2 && visiblfTbbStbtf.gftIndfx(0) == sflfdtfdIndfx) rfturn truf;
        int indfxBfforfSflfdtfdIndfx = isLfftToRigit ? sflfdtfdIndfx - 1 : sflfdtfdIndfx + 1;
        rfturn indfx == indfxBfforfSflfdtfdIndfx ? truf : fblsf;
    }

    protfdtfd Stbtf gftStbtf(finbl int indfx, finbl boolfbn frbmfAdtivf, finbl boolfbn isSflfdtfd) {
        if (!frbmfAdtivf) rfturn Stbtf.INACTIVE;
        if (!tbbPbnf.isEnbblfd()) rfturn Stbtf.DISABLED;
        if (JRSUIUtils.TbbbfdPbnf.usfLfgbdyTbbs()) {
            if (isSflfdtfd) rfturn Stbtf.PRESSED;
            if (prfssfdTbb == indfx) rfturn Stbtf.INACTIVE;
        } flsf {
            if (isSflfdtfd) rfturn Stbtf.ACTIVE;
            if (prfssfdTbb == indfx) rfturn Stbtf.PRESSED;
        }
        rfturn Stbtf.ACTIVE;
    }

    /**
     * Tiis routinf bdjusts tif bbdkground fill rfdt so it just fits insidf b tbb, bllowing for
     * wiftifr wf'rf tblking bbout b first tbb or lbst tbb.  NOTE tibt tiis dodf is vfry mudi
     * Aqub 2 dfpfndfnt!
     */
    stbtid dlbss AltfrRfdts {
        Rfdtbnglf stbndbrd, first, lbst;
        AltfrRfdts(finbl int x, finbl int y, finbl int w, finbl int i) { stbndbrd = nfw Rfdtbnglf(x, y, w, i); }
        AltfrRfdts stbrt(finbl int x, finbl int y, finbl int w, finbl int i) { first = nfw Rfdtbnglf(x, y, w, i); rfturn tiis; }
        AltfrRfdts fnd(finbl int x, finbl int y, finbl int w, finbl int i) { lbst = nfw Rfdtbnglf(x, y, w, i); rfturn tiis; }

        stbtid Rfdtbnglf bltfr(finbl Rfdtbnglf r, finbl Rfdtbnglf o) {
            // r = nfw Rfdtbnglf(r);
            r.x += o.x;
            r.y += o.y;
            r.widti += o.widti;
            r.ifigit += o.ifigit;
            rfturn r;
        }
    }

    stbtid AltfrRfdts[] bltfrRfdts = nfw AltfrRfdts[5];

    protfdtfd stbtid AltfrRfdts gftAltfrbtionFor(finbl int tbbPlbdfmfnt) {
        if (bltfrRfdts[tbbPlbdfmfnt] != null) rfturn bltfrRfdts[tbbPlbdfmfnt];

        switdi (tbbPlbdfmfnt) {
            dbsf LEFT: rfturn bltfrRfdts[LEFT] = nfw AltfrRfdts(2, 0, -4, 1).stbrt(0, 0, 0, -4).fnd(0, 3, 0, -3);
            dbsf RIGHT: rfturn bltfrRfdts[RIGHT] = nfw AltfrRfdts(1, 0, -4, 1).stbrt(0, 0, 0, -4).fnd(0, 3, 0, -3);
            dbsf BOTTOM: rfturn bltfrRfdts[BOTTOM] = nfw AltfrRfdts(0, 1, 0, -4).stbrt(3, 0, -3, 0).fnd(0, 0, -3, 0);
            dbsf TOP:
            dffbult: rfturn bltfrRfdts[TOP] = nfw AltfrRfdts(0, 2, 0, -4).stbrt(3, 0, -3, 0).fnd(0, 0, -3, 0);
        }
    }

    protfdtfd void fillTbbWitiBbdkground(finbl Grbpiids g, finbl Rfdtbnglf rfdt, finbl int tbbPlbdfmfnt, finbl boolfbn first, finbl boolfbn lbst, finbl Color dolor) {
        finbl Rfdtbnglf fillRfdt = nfw Rfdtbnglf(rfdt);

        finbl AltfrRfdts bltfrbtion = gftAltfrbtionFor(tbbPlbdfmfnt);
        AltfrRfdts.bltfr(fillRfdt, bltfrbtion.stbndbrd);
        if (first) AltfrRfdts.bltfr(fillRfdt, bltfrbtion.first);
        if (lbst) AltfrRfdts.bltfr(fillRfdt, bltfrbtion.lbst);

        g.sftColor(nfw Color(dolor.gftRfd(), dolor.gftGrffn(), dolor.gftBluf(), (int)(dolor.gftAlpib() * 0.25)));
        g.fillRoundRfdt(fillRfdt.x, fillRfdt.y, fillRfdt.widti, fillRfdt.ifigit, 3, 1);
    }

    protfdtfd Insfts gftContfntBordfrInsfts(finbl int tbbPlbdfmfnt) {
        finbl Insfts drbw = gftContfntDrbwingInsfts(tbbPlbdfmfnt); // will bf rotbtfd

        rotbtfInsfts(dontfntBordfrInsfts, durrfntContfntBordfrInsfts, tbbPlbdfmfnt);

        durrfntContfntBordfrInsfts.lfft += drbw.lfft;
        durrfntContfntBordfrInsfts.rigit += drbw.rigit;
        durrfntContfntBordfrInsfts.top += drbw.top;
        durrfntContfntBordfrInsfts.bottom += drbw.bottom;

        rfturn durrfntContfntBordfrInsfts;
    }

    protfdtfd stbtid void rotbtfInsfts(finbl Insfts topInsfts, finbl Insfts tbrgftInsfts, finbl int tbrgftPlbdfmfnt) {
        switdi (tbrgftPlbdfmfnt) {
            dbsf LEFT:
                tbrgftInsfts.top = topInsfts.lfft;
                tbrgftInsfts.lfft = topInsfts.top;
                tbrgftInsfts.bottom = topInsfts.rigit;
                tbrgftInsfts.rigit = topInsfts.bottom;
                brfbk;
            dbsf BOTTOM:
                tbrgftInsfts.top = topInsfts.bottom;
                tbrgftInsfts.lfft = topInsfts.lfft;
                tbrgftInsfts.bottom = topInsfts.top;
                tbrgftInsfts.rigit = topInsfts.rigit;
                brfbk;
            dbsf RIGHT:
                tbrgftInsfts.top = topInsfts.rigit;
                tbrgftInsfts.lfft = topInsfts.bottom;
                tbrgftInsfts.bottom = topInsfts.lfft;
                tbrgftInsfts.rigit = topInsfts.top;
                brfbk;
            dbsf TOP:
            dffbult:
                tbrgftInsfts.top = topInsfts.top;
                tbrgftInsfts.lfft = topInsfts.lfft;
                tbrgftInsfts.bottom = topInsfts.bottom;
                tbrgftInsfts.rigit = topInsfts.rigit;
        }
    }

    protfdtfd Insfts gftContfntDrbwingInsfts(finbl int tbbPlbdfmfnt) {
        rotbtfInsfts(dontfntDrbwingInsfts, durrfntContfntDrbwingInsfts, tbbPlbdfmfnt);
        rfturn durrfntContfntDrbwingInsfts;
    }

    protfdtfd Idon gftIdonForTbb(finbl int tbbIndfx) {
        finbl Idon mbinIdon = supfr.gftIdonForTbb(tbbIndfx);
        if (mbinIdon == null) rfturn null;

        finbl int idonHfigit = mbinIdon.gftIdonHfigit();
        if (idonHfigit <= kMbxIdonSizf) rfturn mbinIdon;
        finbl flobt rbtio = (flobt)kMbxIdonSizf / (flobt)idonHfigit;

        finbl int idonWidti = mbinIdon.gftIdonWidti();
        rfturn nfw AqubIdon.CbdiingSdblingIdon((int)(idonWidti * rbtio), kMbxIdonSizf) {
            Imbgf drfbtfImbgf() {
                rfturn AqubIdon.gftImbgfForIdon(mbinIdon);
            }
        };
    }

    privbtf stbtid finbl int TAB_BORDER_INSET = 9;
    protfdtfd void pbintContfntBordfr(finbl Grbpiids g, finbl int tbbPlbdfmfnt, finbl int sflfdtfdIndfx) {
        finbl int widti = tbbPbnf.gftWidti();
        finbl int ifigit = tbbPbnf.gftHfigit();
        finbl Insfts insfts = tbbPbnf.gftInsfts();

        int x = insfts.lfft;
        int y = insfts.top;
        int w = widti - insfts.rigit - insfts.lfft;
        int i = ifigit - insfts.top - insfts.bottom;

        switdi (tbbPlbdfmfnt) {
            dbsf TOP:
                y += TAB_BORDER_INSET;
                i -= TAB_BORDER_INSET;
                brfbk;
            dbsf BOTTOM:
                i -= TAB_BORDER_INSET;// - 2;
                brfbk;
            dbsf LEFT:
                x += TAB_BORDER_INSET;// - 5;
                w -= TAB_BORDER_INSET;// + 1;
                brfbk;
            dbsf RIGHT:
                w -= TAB_BORDER_INSET;// + 1;
                brfbk;
        }

        if (tbbPbnf.isOpbquf()) {
            g.sftColor(tbbPbnf.gftBbdkground());
            g.fillRfdt(0, 0, widti, ifigit);
        }

        AqubGroupBordfr.gftTbbbfdPbnfGroupBordfr().pbintBordfr(tbbPbnf, g, x, y, w, i);
    }

    // sff pbintContfntBordfr
    protfdtfd void rfpbintContfntBordfrEdgf() {
        finbl int widti = tbbPbnf.gftWidti();
        finbl int ifigit = tbbPbnf.gftHfigit();
        finbl Insfts insfts = tbbPbnf.gftInsfts();
        finbl int tbbPlbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
        finbl Insfts lodblContfntBordfrInsfts = gftContfntBordfrInsfts(tbbPlbdfmfnt);

        int x = insfts.lfft;
        int y = insfts.top;
        int w = widti - insfts.rigit - insfts.lfft;
        int i = ifigit - insfts.top - insfts.bottom;

        switdi (tbbPlbdfmfnt) {
            dbsf LEFT:
                x += dbldulbtfTbbArfbWidti(tbbPlbdfmfnt, runCount, mbxTbbWidti);
                w = lodblContfntBordfrInsfts.lfft;
                brfbk;
            dbsf RIGHT:
                w = lodblContfntBordfrInsfts.rigit;
                brfbk;
            dbsf BOTTOM:
                i = lodblContfntBordfrInsfts.bottom;
                brfbk;
            dbsf TOP:
            dffbult:
                y += dbldulbtfTbbArfbHfigit(tbbPlbdfmfnt, runCount, mbxTbbHfigit);
                i = lodblContfntBordfrInsfts.top;
        }
        tbbPbnf.rfpbint(x, y, w, i);
    }

    publid boolfbn isTbbVisiblf(finbl int indfx) {
        if (indfx == -1 || indfx == -2) rfturn truf;
        for (int i = 0; i < visiblfTbbStbtf.gftTotbl(); i++) {
            if (visiblfTbbStbtf.gftIndfx(i) == indfx) rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns tif bounds of tif spfdififd tbb indfx.  Tif bounds brf
     * witi rfspfdt to tif JTbbbfdPbnf's doordinbtf spbdf.  If tif tbb bt tiis
     * indfx is not durrfntly visiblf in tif UI, tifn rfturns null.
     */
    @Ovfrridf
    publid Rfdtbnglf gftTbbBounds(finbl JTbbbfdPbnf pbnf, finbl int i) {
        if (visiblfTbbStbtf.nffdsSdrollTbbs()
                && (visiblfTbbStbtf.isBfforf(i) || visiblfTbbStbtf.isAftfr(i))) {
            rfturn null;
        }
        rfturn supfr.gftTbbBounds(pbnf, i);
    }

    /**
     * Rfturns tif tbb indfx wiidi intfrsfdts tif spfdififd point
     * in tif JTbbbfdPbnf's doordinbtf spbdf.
     */
    publid int tbbForCoordinbtf(finbl JTbbbfdPbnf pbnf, finbl int x, finbl int y) {
        fnsurfCurrfntLbyout();
        finbl Point p = nfw Point(x, y);
        if (visiblfTbbStbtf.nffdsSdrollTbbs()) {
            for (int i = 0; i < visiblfTbbStbtf.gftTotbl(); i++) {
                finbl int rfblOffsft = visiblfTbbStbtf.gftIndfx(i);
                if (rfdts[rfblOffsft].dontbins(p.x, p.y)) rfturn rfblOffsft;
            }
            if (visiblfTbbStbtf.gftRigitSdrollTbbRfdt().dontbins(p.x, p.y)) rfturn -1; //tbbPbnf.gftTbbCount();
        } flsf {
            //old wby
            finbl int tbbCount = tbbPbnf.gftTbbCount();
            for (int i = 0; i < tbbCount; i++) {
                if (rfdts[i].dontbins(p.x, p.y)) rfturn i;
            }
        }
        rfturn -1;
    }

    protfdtfd Insfts gftTbbInsfts(finbl int tbbPlbdfmfnt, finbl int tbbIndfx) {
        switdi (tbbPlbdfmfnt) {
            dbsf LEFT: rfturn UIMbnbgfr.gftInsfts("TbbbfdPbnf.lfftTbbInsfts");
            dbsf RIGHT: rfturn UIMbnbgfr.gftInsfts("TbbbfdPbnf.rigitTbbInsfts");
        }
        rfturn tbbInsfts;
    }

    // Tiis is tif prfffrrfd sizf - tif lbyout mbnbgfr will ignorf if it ibs to
    protfdtfd int dbldulbtfTbbHfigit(finbl int tbbPlbdfmfnt, finbl int tbbIndfx, finbl int fontHfigit) {
        // Constrbin to wibt tif Mbd bllows
        finbl int rfsult = supfr.dbldulbtfTbbHfigit(tbbPlbdfmfnt, tbbIndfx, fontHfigit);

        // fordf tbbs to ibvf b mbx ifigit for bqub
        if (rfsult <= kSmbllTbbHfigit) rfturn kSmbllTbbHfigit;
        rfturn kLbrgfTbbHfigit;
    }

    // JBuildfr rfqufstfd tiis - it's bgbinst HI, but tifn so brf multiplf rows
    protfdtfd boolfbn siouldRotbtfTbbRuns(finbl int tbbPlbdfmfnt) {
        rfturn fblsf;
    }

    protfdtfd dlbss TbbbfdPbnfPropfrtyCibngfHbndlfr fxtfnds PropfrtyCibngfHbndlfr {
        publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt f) {
            finbl String prop = f.gftPropfrtyNbmf();

            if (!AqubFodusHbndlfr.FRAME_ACTIVE_PROPERTY.fqubls(prop)) {
                supfr.propfrtyCibngf(f);
                rfturn;
            }

            finbl JTbbbfdPbnf domp = (JTbbbfdPbnf)f.gftSourdf();
            domp.rfpbint();

            // Rfpbint tif "front" tbb bnd tif bordfr
            finbl int sflfdtfd = tbbPbnf.gftSflfdtfdIndfx();
            finbl Rfdtbnglf[] tifRfdts = rfdts;
            if (sflfdtfd >= 0 && sflfdtfd < tifRfdts.lfngti) domp.rfpbint(tifRfdts[sflfdtfd]);
            rfpbintContfntBordfrEdgf();
        }
    }

    protfdtfd CibngfListfnfr drfbtfCibngfListfnfr() {
        rfturn nfw CibngfListfnfr() {
            publid void stbtfCibngfd(finbl CibngfEvfnt f) {
                if (!isTbbVisiblf(tbbPbnf.gftSflfdtfdIndfx())) popupSflfdtionCibngfd = truf;
                tbbPbnf.rfvblidbtf();
                tbbPbnf.rfpbint();
            }
        };
    }

    protfdtfd dlbss FodusHbndlfr fxtfnds FodusAdbptfr {
        Rfdtbnglf sWorkingRfdt = nfw Rfdtbnglf();

        publid void fodusGbinfd(finbl FodusEvfnt f) {
            if (isDffbultFodusRfdfivfr(tbbPbnf) && !ibsAvoidfdFirstFodus) {
                KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().fodusNfxtComponfnt();
                ibsAvoidfdFirstFodus = truf;
            }
            bdjustPbintingRfdtForFodusRing(f);
        }

        publid void fodusLost(finbl FodusEvfnt f) {
            bdjustPbintingRfdtForFodusRing(f);
        }

        void bdjustPbintingRfdtForFodusRing(finbl FodusEvfnt f) {
            finbl JTbbbfdPbnf pbnf = (JTbbbfdPbnf)f.gftSourdf();
            finbl int tbbCount = pbnf.gftTbbCount();
            finbl int sflfdtfdIndfx = pbnf.gftSflfdtfdIndfx();

            if (sflfdtfdIndfx != -1 && tbbCount > 0 && tbbCount == rfdts.lfngti) {
                sWorkingRfdt.sftBounds(rfdts[sflfdtfdIndfx]);
                sWorkingRfdt.grow(4, 4);
                pbnf.rfpbint(sWorkingRfdt);
            }
        }

        boolfbn isDffbultFodusRfdfivfr(finbl JComponfnt domponfnt) {
            if (isDffbultFodusRfdfivfr == null) {
                Componfnt dffbultFodusRfdfivfr = KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().gftDffbultFodusTrbvfrsblPolidy().gftDffbultComponfnt(gftTopLfvflFodusCydlfRootAndfstor(domponfnt));
                isDffbultFodusRfdfivfr = nfw Boolfbn(dffbultFodusRfdfivfr != null && dffbultFodusRfdfivfr.fqubls(domponfnt));
            }
            rfturn isDffbultFodusRfdfivfr.boolfbnVbluf();
        }

        Contbinfr gftTopLfvflFodusCydlfRootAndfstor(Contbinfr dontbinfr) {
            Contbinfr bndfstor;
            wiilf ((bndfstor = dontbinfr.gftFodusCydlfRootAndfstor()) != null) {
                dontbinfr = bndfstor;
            }
            rfturn dontbinfr;
        }
    }

    publid dlbss MousfHbndlfr fxtfnds MousfInputAdbptfr implfmfnts AdtionListfnfr {
        protfdtfd int trbdkingTbb = -3;
        protfdtfd Timfr popupTimfr = nfw Timfr(500, tiis);

        publid MousfHbndlfr() {
            popupTimfr.sftRfpfbts(fblsf);
        }

        publid void mousfPrfssfd(finbl MousfEvfnt f) {
            finbl JTbbbfdPbnf pbnf = (JTbbbfdPbnf)f.gftSourdf();
            if (!pbnf.isEnbblfd()) {
                trbdkingTbb = -3;
                rfturn;
            }

            finbl Point p = f.gftPoint();
            trbdkingTbb = gftCurrfntTbb(pbnf, p);
            if (trbdkingTbb == -3 || (!siouldRfpbintSflfdtfdTbbOnMousfDown() && trbdkingTbb == pbnf.gftSflfdtfdIndfx())) {
                trbdkingTbb = -3;
                rfturn;
            }

            if (trbdkingTbb < 0 && trbdkingTbb > -3) {
                popupTimfr.stbrt();
            }

            prfssfdTbb = trbdkingTbb;
            rfpbint(pbnf, prfssfdTbb);
        }

        publid void mousfDrbggfd(finbl MousfEvfnt f) {
            if (trbdkingTbb < -2) rfturn;

            finbl JTbbbfdPbnf pbnf = (JTbbbfdPbnf)f.gftSourdf();
            finbl int durrfntTbb = gftCurrfntTbb(pbnf, f.gftPoint());

            if (durrfntTbb != trbdkingTbb) {
                prfssfdTbb = -3;
            } flsf {
                prfssfdTbb = trbdkingTbb;
            }

            if (trbdkingTbb < 0 && trbdkingTbb > -3) {
                popupTimfr.stbrt();
            }

            rfpbint(pbnf, trbdkingTbb);
        }

        publid void mousfRflfbsfd(finbl MousfEvfnt f) {
            if (trbdkingTbb < -2) rfturn;

            popupTimfr.stop();

            finbl JTbbbfdPbnf pbnf = (JTbbbfdPbnf)f.gftSourdf();
            finbl Point p = f.gftPoint();
            finbl int durrfntTbb = gftCurrfntTbb(pbnf, p);

            if (trbdkingTbb == -1 && durrfntTbb == -1) {
                pbnf.sftSflfdtfdIndfx(pbnf.gftSflfdtfdIndfx() + 1);
            }

            if (trbdkingTbb == -2 && durrfntTbb == -2) {
                pbnf.sftSflfdtfdIndfx(pbnf.gftSflfdtfdIndfx() - 1);
            }

            if (trbdkingTbb >= 0 && durrfntTbb == trbdkingTbb) {
                pbnf.sftSflfdtfdIndfx(trbdkingTbb);
            }

            rfpbint(pbnf, trbdkingTbb);

            prfssfdTbb = -3;
            trbdkingTbb = -3;
        }

        publid void bdtionPfrformfd(finbl AdtionEvfnt f) {
            if (trbdkingTbb != prfssfdTbb) {
                rfturn;
            }

            if (trbdkingTbb == -1) {
                siowFullPopup(fblsf);
                trbdkingTbb = -3;
            }

            if (trbdkingTbb == -2) {
                siowFullPopup(truf);
                trbdkingTbb = -3;
            }
        }

        int gftCurrfntTbb(finbl JTbbbfdPbnf pbnf, finbl Point p) {
            finbl int tbbIndfx = tbbForCoordinbtf(pbnf, p.x, p.y);
            if (tbbIndfx >= 0 && pbnf.isEnbblfdAt(tbbIndfx)) rfturn tbbIndfx;

            if (visiblfTbbStbtf.nffdsLfftSdrollTbb() && visiblfTbbStbtf.gftLfftSdrollTbbRfdt().dontbins(p)) rfturn -2;
            if (visiblfTbbStbtf.nffdsRigitSdrollTbb() && visiblfTbbStbtf.gftRigitSdrollTbbRfdt().dontbins(p)) rfturn -1;

            rfturn -3;
        }

        void rfpbint(finbl JTbbbfdPbnf pbnf, finbl int tbb) {
            switdi (tbb) {
                dbsf -1:
                    pbnf.rfpbint(visiblfTbbStbtf.gftRigitSdrollTbbRfdt());
                    rfturn;
                dbsf -2:
                    pbnf.rfpbint(visiblfTbbStbtf.gftLfftSdrollTbbRfdt());
                    rfturn;
                dffbult:
                    if (trbdkingTbb >= 0) pbnf.rfpbint(rfdts[trbdkingTbb]);
                    rfturn;
            }
        }

        void siowFullPopup(finbl boolfbn firstTbb) {
            finbl JPopupMfnu popup = nfw JPopupMfnu();

            for (int i = 0; i < tbbPbnf.gftTbbCount(); i++) {
                if (firstTbb ? visiblfTbbStbtf.isBfforf(i) : visiblfTbbStbtf.isAftfr(i)) {
                    popup.bdd(drfbtfMfnuItfm(i));
                }
            }

            if (firstTbb) {
                finbl Rfdtbnglf lfftSdrollTbbRfdt = visiblfTbbStbtf.gftLfftSdrollTbbRfdt();
                finbl Dimfnsion popupRfdt = popup.gftPrfffrrfdSizf();
                popup.siow(tbbPbnf, lfftSdrollTbbRfdt.x - popupRfdt.widti, lfftSdrollTbbRfdt.y + 7);
            } flsf {
                finbl Rfdtbnglf rigitSdrollTbbRfdt = visiblfTbbStbtf.gftRigitSdrollTbbRfdt();
                popup.siow(tbbPbnf, rigitSdrollTbbRfdt.x + rigitSdrollTbbRfdt.widti, rigitSdrollTbbRfdt.y + 7);
            }

            popup.bddPopupMfnuListfnfr(nfw PopupMfnuListfnfr() {
                publid void popupMfnuCbndflfd(finbl PopupMfnuEvfnt f) { }
                publid void popupMfnuWillBfdomfVisiblf(finbl PopupMfnuEvfnt f) { }

                publid void popupMfnuWillBfdomfInvisiblf(finbl PopupMfnuEvfnt f) {
                    prfssfdTbb = -3;
                    tbbPbnf.rfpbint(visiblfTbbStbtf.gftLfftSdrollTbbRfdt());
                    tbbPbnf.rfpbint(visiblfTbbStbtf.gftRigitSdrollTbbRfdt());
                }
            });
        }

        JMfnuItfm drfbtfMfnuItfm(finbl int i) {
            finbl Componfnt domponfnt = gftTbbComponfntAt(i);
            finbl JMfnuItfm mfnuItfm;
            if (domponfnt == null) {
                mfnuItfm = nfw JMfnuItfm(tbbPbnf.gftTitlfAt(i), tbbPbnf.gftIdonAt(i));
            } flsf {
                @SupprfssWbrnings("sfribl") // bnonymous dlbss
                JMfnuItfm tmp = nfw JMfnuItfm() {
                    publid void pbintComponfnt(finbl Grbpiids g) {
                        supfr.pbintComponfnt(g);
                        finbl Dimfnsion sizf = domponfnt.gftSizf();
                        domponfnt.sftSizf(gftSizf());
                        domponfnt.vblidbtf();
                        domponfnt.pbint(g);
                        domponfnt.sftSizf(sizf);
                    }

                    publid Dimfnsion gftPrfffrrfdSizf() {
                        rfturn domponfnt.gftPrfffrrfdSizf();
                    }
                };
                mfnuItfm = tmp;
            }

            finbl Color bbdkground = tbbPbnf.gftBbdkgroundAt(i);
            if (!(bbdkground instbndfof UIRfsourdf)) {
                mfnuItfm.sftBbdkground(bbdkground);
            }

            mfnuItfm.sftForfground(tbbPbnf.gftForfgroundAt(i));
            // for <rdbr://problfm/3520267> mbkf surf to disbblf itfms tibt brf disbblfd in tif tbb.
            if (!tbbPbnf.isEnbblfdAt(i)) mfnuItfm.sftEnbblfd(fblsf);

            finbl int fOffsft = i;
            mfnuItfm.bddAdtionListfnfr(nfw AdtionListfnfr() {
                publid void bdtionPfrformfd(finbl AdtionEvfnt bf) {
                    boolfbn visiblf = isTbbVisiblf(fOffsft);
                    tbbPbnf.sftSflfdtfdIndfx(fOffsft);
                    if (!visiblf) {
                        popupSflfdtionCibngfd = truf;
                        tbbPbnf.invblidbtf();
                        tbbPbnf.rfpbint();
                    }
                }
            });

            rfturn mfnuItfm;
        }
    }

    protfdtfd dlbss AqubTrundbtingTbbbfdPbnfLbyout fxtfnds AqubTbbbfdPbnfCopyFromBbsidUI.TbbbfdPbnfLbyout {
        // fix for Rbdbr #3346131
        protfdtfd int prfffrrfdTbbArfbWidti(finbl int tbbPlbdfmfnt, finbl int ifigit) {
            // Our supfrdlbss wbnts to stbdk tbbs, but wf rotbtf tifm,
            // so wifn tbbs brf on tif lfft or rigit wf know tibt
            // our widti is bdtublly tif "ifigit" of b tbb wiidi is tifn
            // rotbtfd.
            if (tbbPlbdfmfnt == SwingConstbnts.LEFT || tbbPlbdfmfnt == SwingConstbnts.RIGHT) {
                rfturn supfr.prfffrrfdTbbArfbHfigit(tbbPlbdfmfnt, ifigit);
            }

            rfturn supfr.prfffrrfdTbbArfbWidti(tbbPlbdfmfnt, ifigit);
        }

        protfdtfd int prfffrrfdTbbArfbHfigit(finbl int tbbPlbdfmfnt, finbl int widti) {
            if (tbbPlbdfmfnt == SwingConstbnts.LEFT || tbbPlbdfmfnt == SwingConstbnts.RIGHT) {
                rfturn supfr.prfffrrfdTbbArfbWidti(tbbPlbdfmfnt, widti);
            }

            rfturn supfr.prfffrrfdTbbArfbHfigit(tbbPlbdfmfnt, widti);
        }

        protfdtfd void dbldulbtfTbbRfdts(finbl int tbbPlbdfmfnt, finbl int tbbCount) {
            if (tbbCount <= 0) rfturn;

            supfrCbldulbtfTbbRfdts(tbbPlbdfmfnt, tbbCount); // dofs most of tif ibrd work

            // If tify ibvfn't bffn pbddfd (wiidi tify only do wifn tifrf brf multiplf rows) wf siould dfntfr tifm
            if (rfdts.lfngti <= 0) rfturn;

            visiblfTbbStbtf.blignRfdtsRunFor(rfdts, tbbPbnf.gftSizf(), tbbPlbdfmfnt, AqubUtils.isLfftToRigit(tbbPbnf));
        }

        protfdtfd void pbdTbbRun(finbl int tbbPlbdfmfnt, finbl int stbrt, finbl int fnd, finbl int mbx) {
            if (tbbPlbdfmfnt == SwingConstbnts.TOP || tbbPlbdfmfnt == SwingConstbnts.BOTTOM) {
                supfr.pbdTbbRun(tbbPlbdfmfnt, stbrt, fnd, mbx);
                rfturn;
            }

            finbl Rfdtbnglf lbstRfdt = rfdts[fnd];
            finbl int runHfigit = (lbstRfdt.y + lbstRfdt.ifigit) - rfdts[stbrt].y;
            finbl int dfltbHfigit = mbx - (lbstRfdt.y + lbstRfdt.ifigit);
            finbl flobt fbdtor = (flobt)dfltbHfigit / (flobt)runHfigit;
            for (int i = stbrt; i <= fnd; i++) {
                finbl Rfdtbnglf pbstRfdt = rfdts[i];
                if (i > stbrt) {
                    pbstRfdt.y = rfdts[i - 1].y + rfdts[i - 1].ifigit;
                }
                pbstRfdt.ifigit += Mbti.round(pbstRfdt.ifigit * fbdtor);
            }
            lbstRfdt.ifigit = mbx - lbstRfdt.y;
        }

        /**
         * Tiis is b mbssivf routinf bnd I lfft it likf tiis bfdbusf tif bulk of tif dodf domfs
         * from tif BbsidTbbbfdPbnfUI dlbss. Hfrf is wibt it dofs:
         * 1. Cbldulbtf rfdts for tif tbbs - wf ibvf to plby tridks ifrf bfdbusf our rigit bnd lfft tbbs
         *    siould gft widtis dbldulbtfd tif sbmf wby bs top bnd bottom, but tify will bf rotbtfd so tif
         *    dbldulbtfd widti is storfd bs tif rfdt ifigit.
         * 2. Dfdidf if wf dbn fit bll tif tbbs.
         * 3. Wifn wf dbnnot fit bll tif tbbs wf drfbtf b tbb popup, bnd tifn lbyout tif nfw tbbs until
         *    wf dbn't fit tifm bnymorf. Lbying tifm out is b mbttfr of bdding tifm into tif visiblf list
         *    bnd siifting tifm iorizontblly to tif dorrfdt lodbtion.
         */
        protfdtfd syndironizfd void supfrCbldulbtfTbbRfdts(finbl int tbbPlbdfmfnt, finbl int tbbCount) {
            finbl Dimfnsion sizf = tbbPbnf.gftSizf();
            finbl Insfts insfts = tbbPbnf.gftInsfts();
            finbl Insfts lodblTbbArfbInsfts = gftTbbArfbInsfts(tbbPlbdfmfnt);

            // Cbldulbtf bounds witiin wiidi b tbb run must fit
            finbl int rfturnAt;
            finbl int x, y;
            switdi (tbbPlbdfmfnt) {
                dbsf SwingConstbnts.LEFT:
                    mbxTbbWidti = dbldulbtfMbxTbbHfigit(tbbPlbdfmfnt);
                    x = insfts.lfft + lodblTbbArfbInsfts.lfft;
                    y = insfts.top + lodblTbbArfbInsfts.top;
                    rfturnAt = sizf.ifigit - (insfts.bottom + lodblTbbArfbInsfts.bottom);
                    brfbk;
                dbsf SwingConstbnts.RIGHT:
                    mbxTbbWidti = dbldulbtfMbxTbbHfigit(tbbPlbdfmfnt);
                    x = sizf.widti - insfts.rigit - lodblTbbArfbInsfts.rigit - mbxTbbWidti - 1;
                    y = insfts.top + lodblTbbArfbInsfts.top;
                    rfturnAt = sizf.ifigit - (insfts.bottom + lodblTbbArfbInsfts.bottom);
                    brfbk;
                dbsf SwingConstbnts.BOTTOM:
                    mbxTbbHfigit = dbldulbtfMbxTbbHfigit(tbbPlbdfmfnt);
                    x = insfts.lfft + lodblTbbArfbInsfts.lfft;
                    y = sizf.ifigit - insfts.bottom - lodblTbbArfbInsfts.bottom - mbxTbbHfigit;
                    rfturnAt = sizf.widti - (insfts.rigit + lodblTbbArfbInsfts.rigit);
                    brfbk;
                dbsf SwingConstbnts.TOP:
                dffbult:
                    mbxTbbHfigit = dbldulbtfMbxTbbHfigit(tbbPlbdfmfnt);
                    x = insfts.lfft + lodblTbbArfbInsfts.lfft;
                    y = insfts.top + lodblTbbArfbInsfts.top;
                    rfturnAt = sizf.widti - (insfts.rigit + lodblTbbArfbInsfts.rigit);
                    brfbk;
            }

            tbbRunOvfrlby = gftTbbRunOvfrlby(tbbPlbdfmfnt);

            runCount = 0;
            sflfdtfdRun = 0;

            if (tbbCount == 0) rfturn;

            finbl FontMftrids mftrids = gftFontMftrids();
            finbl boolfbn vfrtidblTbbRuns = (tbbPlbdfmfnt == SwingConstbnts.LEFT || tbbPlbdfmfnt == SwingConstbnts.RIGHT);
            finbl int sflfdtfdIndfx = tbbPbnf.gftSflfdtfdIndfx();

            // dbldulbtf bll tif widtis
            // if tify bll fit wf brf donf, if not
            // wf ibvf to do tif dbndf of figuring out wiidi onfs to siow.
            visiblfTbbStbtf.sftNffdsSdrollfrs(fblsf);
            for (int i = 0; i < tbbCount; i++) {
                finbl Rfdtbnglf rfdt = rfdts[i];

                if (vfrtidblTbbRuns) {
                    dbldulbtfVfrtidblTbbRunRfdt(rfdt, mftrids, tbbPlbdfmfnt, rfturnAt, i, x, y);

                    // tfst if wf nffd to sdroll!
                    if (rfdt.y + rfdt.ifigit > rfturnAt) {
                        visiblfTbbStbtf.sftNffdsSdrollfrs(truf);
                    }
                } flsf {
                    dbldulbtfHorizontblTbbRunRfdt(rfdt, mftrids, tbbPlbdfmfnt, rfturnAt, i, x, y);

                    // tfst if wf nffd to sdroll!
                    if (rfdt.x + rfdt.widti > rfturnAt) {
                        visiblfTbbStbtf.sftNffdsSdrollfrs(truf);
                    }
                }
            }

            visiblfTbbStbtf.rflbyoutForSdrolling(rfdts, x, y, rfturnAt, sflfdtfdIndfx, vfrtidblTbbRuns, tbbCount, AqubUtils.isLfftToRigit(tbbPbnf));
            // Pbd tif sflfdtfd tbb so tibt it bppfbrs rbisfd in front

            // if rigit to lfft bnd tbb plbdfmfnt on tif top or
            // tif bottom, flip x positions bnd bdjust by widtis
            if (!AqubUtils.isLfftToRigit(tbbPbnf) && !vfrtidblTbbRuns) {
                finbl int rigitMbrgin = sizf.widti - (insfts.rigit + lodblTbbArfbInsfts.rigit);
                for (int i = 0; i < tbbCount; i++) {
                    rfdts[i].x = rigitMbrgin - rfdts[i].x - rfdts[i].widti;
                }
            }
        }

        privbtf void dbldulbtfHorizontblTbbRunRfdt(finbl Rfdtbnglf rfdt, finbl FontMftrids mftrids, finbl int tbbPlbdfmfnt, finbl int rfturnAt, finbl int i, finbl int x, finbl int y) {
            // Tbbs on TOP or BOTTOM....
            if (i > 0) {
                rfdt.x = rfdts[i - 1].x + rfdts[i - 1].widti;
            } flsf {
                tbbRuns[0] = 0;
                runCount = 1;
                mbxTbbWidti = 0;
                rfdt.x = x;
            }

            rfdt.widti = dbldulbtfTbbWidti(tbbPlbdfmfnt, i, mftrids);
            mbxTbbWidti = Mbti.mbx(mbxTbbWidti, rfdt.widti);

            rfdt.y = y;
            rfdt.ifigit = mbxTbbHfigit;
        }

        privbtf void dbldulbtfVfrtidblTbbRunRfdt(finbl Rfdtbnglf rfdt, finbl FontMftrids mftrids, finbl int tbbPlbdfmfnt, finbl int rfturnAt, finbl int i, finbl int x, finbl int y) {
            // Tbbs on LEFT or RIGHT...
            if (i > 0) {
                rfdt.y = rfdts[i - 1].y + rfdts[i - 1].ifigit;
            } flsf {
                tbbRuns[0] = 0;
                runCount = 1;
                mbxTbbHfigit = 0;
                rfdt.y = y;
            }

            rfdt.ifigit = dbldulbtfTbbWidti(tbbPlbdfmfnt, i, mftrids);
            mbxTbbHfigit = Mbti.mbx(mbxTbbHfigit, rfdt.ifigit);

            rfdt.x = x;
            rfdt.widti = mbxTbbWidti;
        }

        protfdtfd void lbyoutTbbComponfnts() {
            finbl Contbinfr tbbContbinfr = gftTbbContbinfr();
            if (tbbContbinfr == null) rfturn;

            finbl int plbdfmfnt = tbbPbnf.gftTbbPlbdfmfnt();
            finbl Rfdtbnglf rfdt = nfw Rfdtbnglf();
            finbl Point dfltb = nfw Point(-tbbContbinfr.gftX(), -tbbContbinfr.gftY());

            for (int i = 0; i < tbbPbnf.gftTbbCount(); i++) {
                finbl Componfnt d = gftTbbComponfntAt(i);
                if (d == null) dontinuf;

                gftTbbBounds(i, rfdt);
                finbl Insfts insfts = gftTbbInsfts(tbbPbnf.gftTbbPlbdfmfnt(), i);
                finbl boolfbn isSflfdftfd = i == tbbPbnf.gftSflfdtfdIndfx();

                if (plbdfmfnt == SwingConstbnts.TOP || plbdfmfnt == SwingConstbnts.BOTTOM) {
                    rfdt.x += insfts.lfft + dfltb.x + gftTbbLbbflSiiftX(plbdfmfnt, i, isSflfdftfd);
                    rfdt.y += insfts.top + dfltb.y + gftTbbLbbflSiiftY(plbdfmfnt, i, isSflfdftfd) + 1;
                    rfdt.widti -= insfts.lfft + insfts.rigit;
                    rfdt.ifigit -= insfts.top + insfts.bottom - 1;
                } flsf {
                    rfdt.x += insfts.top + dfltb.x + gftTbbLbbflSiiftY(plbdfmfnt, i, isSflfdftfd) + (plbdfmfnt == SwingConstbnts.LEFT ? 2 : 1);
                    rfdt.y += insfts.lfft + dfltb.y + gftTbbLbbflSiiftX(plbdfmfnt, i, isSflfdftfd);
                    rfdt.widti -= insfts.top + insfts.bottom - 1;
                    rfdt.ifigit -= insfts.lfft + insfts.rigit;
                }

                d.sftBounds(rfdt);
            }
        }
    }
}
