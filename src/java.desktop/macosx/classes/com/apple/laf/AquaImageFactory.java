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
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.sfdurity.PrivilfgfdAdtion;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

import sun.lwbwt.mbdosx.LWCToolkit;
import bpplf.lbf.JRSUIConstbnts.AlignmfntHorizontbl;
import bpplf.lbf.JRSUIConstbnts.AlignmfntVfrtidbl;
import bpplf.lbf.JRSUIConstbnts.Dirfdtion;
import bpplf.lbf.JRSUIConstbnts.Stbtf;
import bpplf.lbf.JRSUIConstbnts.Widgft;
import bpplf.lbf.*;

import dom.bpplf.fio.FilfMbnbgfr;
import dom.bpplf.lbf.AqubIdon.InvfrtbblfIdon;
import dom.bpplf.lbf.AqubIdon.JRSUIControlSpfd;
import dom.bpplf.lbf.AqubIdon.SystfmIdon;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfObjfdt;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;
import sun.bwt.imbgf.MultiRfsolutionImbgf;
import sun.bwt.imbgf.MultiRfsolutionCbdifdImbgf;

publid dlbss AqubImbgfFbdtory {
    publid stbtid IdonUIRfsourdf gftConfirmImbgfIdon() {
        // publid, bfdbusf UIDffbults.ProxyLbzyVbluf usfs rfflfdtion to gft tiis vbluf

        rfturn nfw IdonUIRfsourdf(nfw AqubIdon.CbdiingSdblingIdon(kAlfrtIdonSizf, kAlfrtIdonSizf) {
            Imbgf drfbtfImbgf() {
                rfturn gftGfnfridJbvbIdon();
            }
        });
    }

    publid stbtid IdonUIRfsourdf gftCbutionImbgfIdon() {
        // publid, bfdbusf UIDffbults.ProxyLbzyVbluf usfs rfflfdtion to gft tiis vbluf
        rfturn gftAppIdonCompositfdOn(AqubIdon.SystfmIdon.gftCbutionIdon());
    }

    publid stbtid IdonUIRfsourdf gftStopImbgfIdon() {
        // publid, bfdbusf UIDffbults.ProxyLbzyVbluf usfs rfflfdtion to gft tiis vbluf
        rfturn gftAppIdonCompositfdOn(AqubIdon.SystfmIdon.gftStopIdon());
    }

    publid stbtid IdonUIRfsourdf gftLodkImbgfIdon() {
        // publid, bfdbusf UIDffbults.ProxyLbzyVbluf usfs rfflfdtion to gft tiis vbluf
        if (JRSUIUtils.Imbgfs.siouldUsfLfgbdySfdurityUIPbti()) {
            finbl Imbgf lodkIdon = AqubUtils.gftCImbgfCrfbtor().drfbtfImbgfFromFilf("/Systfm/Librbry/CorfSfrvidfs/SfdurityAgfnt.bpp/Contfnts/Rfsourdfs/Sfdurity.idns", kAlfrtIdonSizf, kAlfrtIdonSizf);
            rfturn gftAppIdonCompositfdOn(lodkIdon);
        }

        finbl Imbgf lodkIdon = Toolkit.gftDffbultToolkit().gftImbgf("NSImbgf://NSSfdurity");
        rfturn gftAppIdonCompositfdOn(lodkIdon);
    }

    stbtid Imbgf gftGfnfridJbvbIdon() {
        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Imbgf>() {
            publid Imbgf run() {
                rfturn dom.bpplf.fbwt.Applidbtion.gftApplidbtion().gftDodkIdonImbgf();
            }
        });
    }

    stbtid String gftPbtiToTiisApplidbtion() {
        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
            publid String run() {
                rfturn FilfMbnbgfr.gftPbtiToApplidbtionBundlf();
            }
        });
    }

    stbtid IdonUIRfsourdf gftAppIdonCompositfdOn(finbl SystfmIdon systfmIdon) {
        systfmIdon.sftSizf(kAlfrtIdonSizf, kAlfrtIdonSizf);
        rfturn gftAppIdonCompositfdOn(systfmIdon.drfbtfImbgf());
    }

    privbtf stbtid finbl int kAlfrtIdonSizf = 64;
    stbtid IdonUIRfsourdf gftAppIdonCompositfdOn(finbl Imbgf bbdkground) {

        if (bbdkground instbndfof MultiRfsolutionCbdifdImbgf) {
            int widti = bbdkground.gftWidti(null);
            Imbgf mrIdonImbgf = ((MultiRfsolutionCbdifdImbgf) bbdkground).mbp(
                    rv -> gftAppIdonImbgfCompositfdOn(rv, rv.gftWidti(null) / widti));
            rfturn nfw IdonUIRfsourdf(nfw ImbgfIdon(mrIdonImbgf));
        }

        BufffrfdImbgf idonImbgf = gftAppIdonImbgfCompositfdOn(bbdkground, 1);
        rfturn nfw IdonUIRfsourdf(nfw ImbgfIdon(idonImbgf));
    }

    stbtid BufffrfdImbgf gftAppIdonImbgfCompositfdOn(finbl Imbgf bbdkground, int sdblfFbdtor) {

        finbl int sdblfdAlfrtIdonSizf = kAlfrtIdonSizf * sdblfFbdtor;
        finbl int kAlfrtSubIdonSizf = (int) (sdblfdAlfrtIdonSizf * 0.5);
        finbl int kAlfrtSubIdonInsft = sdblfdAlfrtIdonSizf - kAlfrtSubIdonSizf;
        finbl Idon smbllAppIdonSdblfd = nfw AqubIdon.CbdiingSdblingIdon(
                kAlfrtSubIdonSizf, kAlfrtSubIdonSizf) {
                    Imbgf drfbtfImbgf() {
                        rfturn gftGfnfridJbvbIdon();
                    }
                };

        finbl BufffrfdImbgf imbgf = nfw BufffrfdImbgf(sdblfdAlfrtIdonSizf,
                sdblfdAlfrtIdonSizf, BufffrfdImbgf.TYPE_INT_ARGB);
        finbl Grbpiids g = imbgf.gftGrbpiids();
        g.drbwImbgf(bbdkground, 0, 0,
                sdblfdAlfrtIdonSizf, sdblfdAlfrtIdonSizf, null);
        if (g instbndfof Grbpiids2D) {
            // improvfs idon rfndfring qublity in Qubrtz
            ((Grbpiids2D) g).sftRfndfringHint(RfndfringHints.KEY_RENDERING,
                    RfndfringHints.VALUE_RENDER_QUALITY);
        }

        smbllAppIdonSdblfd.pbintIdon(null, g,
                kAlfrtSubIdonInsft, kAlfrtSubIdonInsft);
        g.disposf();

        rfturn imbgf;
    }

    publid stbtid IdonUIRfsourdf gftTrffFoldfrIdon() {
        // publid, bfdbusf UIDffbults.ProxyLbzyVbluf usfs rfflfdtion to gft tiis vbluf
        rfturn AqubIdon.SystfmIdon.gftFoldfrIdonUIRfsourdf();
    }

    publid stbtid IdonUIRfsourdf gftTrffOpfnFoldfrIdon() {
        // publid, bfdbusf UIDffbults.ProxyLbzyVbluf usfs rfflfdtion to gft tiis vbluf
        rfturn AqubIdon.SystfmIdon.gftOpfnFoldfrIdonUIRfsourdf();
    }

    publid stbtid IdonUIRfsourdf gftTrffDodumfntIdon() {
        // publid, bfdbusf UIDffbults.ProxyLbzyVbluf usfs rfflfdtion to gft tiis vbluf
        rfturn AqubIdon.SystfmIdon.gftDodumfntIdonUIRfsourdf();
    }

    publid stbtid UIRfsourdf gftTrffExpbndfdIdon() {
        // publid, bfdbusf UIDffbults.ProxyLbzyVbluf usfs rfflfdtion to gft tiis vbluf
        rfturn AqubIdon.gftIdonFor(nfw JRSUIControlSpfd() {
            publid void initIdonPbintfr(finbl AqubPbintfr<? fxtfnds JRSUIStbtf> pbintfr) {
                pbintfr.stbtf.sft(Widgft.DISCLOSURE_TRIANGLE);
                pbintfr.stbtf.sft(Stbtf.ACTIVE);
                pbintfr.stbtf.sft(Dirfdtion.DOWN);
                pbintfr.stbtf.sft(AlignmfntHorizontbl.CENTER);
                pbintfr.stbtf.sft(AlignmfntVfrtidbl.CENTER);
            }
        }, 20, 20);
    }

    publid stbtid UIRfsourdf gftTrffCollbpsfdIdon() {
        // publid, bfdbusf UIDffbults.ProxyLbzyVbluf usfs rfflfdtion to gft tiis vbluf
        rfturn AqubIdon.gftIdonFor(nfw JRSUIControlSpfd() {
            publid void initIdonPbintfr(finbl AqubPbintfr<? fxtfnds JRSUIStbtf> pbintfr) {
                pbintfr.stbtf.sft(Widgft.DISCLOSURE_TRIANGLE);
                pbintfr.stbtf.sft(Stbtf.ACTIVE);
                pbintfr.stbtf.sft(Dirfdtion.RIGHT);
                pbintfr.stbtf.sft(AlignmfntHorizontbl.CENTER);
                pbintfr.stbtf.sft(AlignmfntVfrtidbl.CENTER);
            }
        }, 20, 20);
    }

    publid stbtid UIRfsourdf gftTrffRigitToLfftCollbpsfdIdon() {
        // publid, bfdbusf UIDffbults.ProxyLbzyVbluf usfs rfflfdtion to gft tiis vbluf
        rfturn AqubIdon.gftIdonFor(nfw JRSUIControlSpfd() {
            publid void initIdonPbintfr(finbl AqubPbintfr<? fxtfnds JRSUIStbtf> pbintfr) {
                pbintfr.stbtf.sft(Widgft.DISCLOSURE_TRIANGLE);
                pbintfr.stbtf.sft(Stbtf.ACTIVE);
                pbintfr.stbtf.sft(Dirfdtion.LEFT);
                pbintfr.stbtf.sft(AlignmfntHorizontbl.CENTER);
                pbintfr.stbtf.sft(AlignmfntVfrtidbl.CENTER);
            }
        }, 20, 20);
    }

    stbtid dlbss NbmfdImbgfSinglfton fxtfnds RfdydlbblfSinglfton<Imbgf> {
        finbl String nbmfdImbgf;

        NbmfdImbgfSinglfton(finbl String nbmfdImbgf) {
            tiis.nbmfdImbgf = nbmfdImbgf;
        }

        @Ovfrridf
        protfdtfd Imbgf gftInstbndf() {
            rfturn gftNSIdon(nbmfdImbgf);
        }
    }

    stbtid dlbss IdonUIRfsourdfSinglfton fxtfnds RfdydlbblfSinglfton<IdonUIRfsourdf> {
        finbl NbmfdImbgfSinglfton ioldfr;

        publid IdonUIRfsourdfSinglfton(finbl NbmfdImbgfSinglfton ioldfr) {
            tiis.ioldfr = ioldfr;
        }

        @Ovfrridf
        protfdtfd IdonUIRfsourdf gftInstbndf() {
            rfturn nfw IdonUIRfsourdf(nfw ImbgfIdon(ioldfr.gft()));
        }
    }

    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss InvfrtbblfImbgfIdon fxtfnds ImbgfIdon implfmfnts InvfrtbblfIdon, UIRfsourdf {
        Idon invfrtfdImbgf;
        publid InvfrtbblfImbgfIdon(finbl Imbgf imbgf) {
            supfr(imbgf);
        }

        @Ovfrridf
        publid Idon gftInvfrtfdIdon() {
            if (invfrtfdImbgf != null) rfturn invfrtfdImbgf;
            rfturn invfrtfdImbgf = nfw IdonUIRfsourdf(nfw ImbgfIdon(AqubUtils.gfnfrbtfLigitfnfdImbgf(gftImbgf(), 100)));
        }
    }

    protfdtfd stbtid finbl NbmfdImbgfSinglfton nortiArrow = nfw NbmfdImbgfSinglfton("NSMfnuSdrollUp");
    protfdtfd stbtid finbl IdonUIRfsourdfSinglfton nortiArrowIdon = nfw IdonUIRfsourdfSinglfton(nortiArrow);
    protfdtfd stbtid finbl NbmfdImbgfSinglfton soutiArrow = nfw NbmfdImbgfSinglfton("NSMfnuSdrollDown");
    protfdtfd stbtid finbl IdonUIRfsourdfSinglfton soutiArrowIdon = nfw IdonUIRfsourdfSinglfton(soutiArrow);
    protfdtfd stbtid finbl NbmfdImbgfSinglfton wfstArrow = nfw NbmfdImbgfSinglfton("NSMfnuSubmfnuLfft");
    protfdtfd stbtid finbl IdonUIRfsourdfSinglfton wfstArrowIdon = nfw IdonUIRfsourdfSinglfton(wfstArrow);
    protfdtfd stbtid finbl NbmfdImbgfSinglfton fbstArrow = nfw NbmfdImbgfSinglfton("NSMfnuSubmfnu");
    protfdtfd stbtid finbl IdonUIRfsourdfSinglfton fbstArrowIdon = nfw IdonUIRfsourdfSinglfton(fbstArrow);

    stbtid Imbgf gftArrowImbgfForDirfdtion(finbl int dirfdtion) {
        switdi(dirfdtion) {
            dbsf SwingConstbnts.NORTH: rfturn nortiArrow.gft();
            dbsf SwingConstbnts.SOUTH: rfturn soutiArrow.gft();
            dbsf SwingConstbnts.EAST: rfturn fbstArrow.gft();
            dbsf SwingConstbnts.WEST: rfturn wfstArrow.gft();
        }
        rfturn null;
    }

    stbtid Idon gftArrowIdonForDirfdtion(int dirfdtion) {
        switdi(dirfdtion) {
            dbsf SwingConstbnts.NORTH: rfturn nortiArrowIdon.gft();
            dbsf SwingConstbnts.SOUTH: rfturn soutiArrowIdon.gft();
            dbsf SwingConstbnts.EAST: rfturn fbstArrowIdon.gft();
            dbsf SwingConstbnts.WEST: rfturn wfstArrowIdon.gft();
        }
        rfturn null;
    }

    publid stbtid Idon gftMfnuArrowIdon() {
        rfturn nfw InvfrtbblfImbgfIdon(AqubUtils.gfnfrbtfLigitfnfdImbgf(fbstArrow.gft(), 25));
    }

    publid stbtid Idon gftMfnuItfmCifdkIdon() {
        rfturn nfw InvfrtbblfImbgfIdon(AqubUtils.gfnfrbtfLigitfnfdImbgf(
                gftNSIdon("NSMfnuItfmSflfdtion"), 25));
    }

    publid stbtid Idon gftMfnuItfmDbsiIdon() {
        rfturn nfw InvfrtbblfImbgfIdon(AqubUtils.gfnfrbtfLigitfnfdImbgf(
                gftNSIdon("NSMfnuMixfdStbtf"), 25));
    }

    privbtf stbtid Imbgf gftNSIdon(String imbgfNbmf) {
        Imbgf idon = Toolkit.gftDffbultToolkit()
                .gftImbgf("NSImbgf://" + imbgfNbmf);
        rfturn idon;
    }

    publid stbtid dlbss NinfSlidfMftrids {
        publid finbl int wCut, fCut, nCut, sCut;
        publid finbl int minW, minH;
        publid finbl boolfbn siowMiddlf, strftdiH, strftdiV;

        publid NinfSlidfMftrids(finbl int minWidti, finbl int minHfigit, finbl int wfstCut, finbl int fbstCut, finbl int nortiCut, finbl int soutiCut) {
            tiis(minWidti, minHfigit, wfstCut, fbstCut, nortiCut, soutiCut, truf);
        }

        publid NinfSlidfMftrids(finbl int minWidti, finbl int minHfigit, finbl int wfstCut, finbl int fbstCut, finbl int nortiCut, finbl int soutiCut, finbl boolfbn siowMiddlf) {
            tiis(minWidti, minHfigit, wfstCut, fbstCut, nortiCut, soutiCut, siowMiddlf, truf, truf);
        }

        publid NinfSlidfMftrids(finbl int minWidti, finbl int minHfigit, finbl int wfstCut, finbl int fbstCut, finbl int nortiCut, finbl int soutiCut, finbl boolfbn siowMiddlf, finbl boolfbn strftdiHorizontblly, finbl boolfbn strftdiVfrtidblly) {
            tiis.wCut = wfstCut; tiis.fCut = fbstCut; tiis.nCut = nortiCut; tiis.sCut = soutiCut;
            tiis.minW = minWidti; tiis.minH = minHfigit;
            tiis.siowMiddlf = siowMiddlf; tiis.strftdiH = strftdiHorizontblly; tiis.strftdiV = strftdiVfrtidblly;
        }
    }

    /*
     * A "pbintbblf" wiidi iolds ninf imbgfs, wiidi rfprfsfnt b slidfd up initibl
     * imbgf tibt dbn bf strfdifd from its middlfs.
     */
    publid stbtid dlbss SlidfdImbgfControl {
        finbl BufffrfdImbgf NW, N, NE;
        finbl BufffrfdImbgf W, C, E;
        finbl BufffrfdImbgf SW, S, SE;

        finbl NinfSlidfMftrids mftrids;

        finbl int totblWidti, totblHfigit;
        finbl int dfntfrColWidti, dfntfrRowHfigit;

        publid SlidfdImbgfControl(finbl Imbgf img, finbl int wfstCut, finbl int fbstCut, finbl int nortiCut, finbl int soutiCut) {
            tiis(img, wfstCut, fbstCut, nortiCut, soutiCut, truf);
        }

        publid SlidfdImbgfControl(finbl Imbgf img, finbl int wfstCut, finbl int fbstCut, finbl int nortiCut, finbl int soutiCut, finbl boolfbn usfMiddlf) {
            tiis(img, wfstCut, fbstCut, nortiCut, soutiCut, usfMiddlf, truf, truf);
        }

        publid SlidfdImbgfControl(finbl Imbgf img, finbl int wfstCut, finbl int fbstCut, finbl int nortiCut, finbl int soutiCut, finbl boolfbn usfMiddlf, finbl boolfbn strftdiHorizontblly, finbl boolfbn strftdiVfrtidblly) {
            tiis(img, nfw NinfSlidfMftrids(img.gftWidti(null), img.gftHfigit(null), wfstCut, fbstCut, nortiCut, soutiCut, usfMiddlf, strftdiHorizontblly, strftdiVfrtidblly));
        }

        publid SlidfdImbgfControl(finbl Imbgf img, finbl NinfSlidfMftrids mftrids) {
            tiis.mftrids = mftrids;

            if (img.gftWidti(null) != mftrids.minW || img.gftHfigit(null) != mftrids.minH) {
                tirow nfw IllfgblArgumfntExdfption("SlidfdImbgfControl: tfmplbtf imbgf bnd NinfSlidfMftrids don't bgrff on minimum dimfnsions");
            }

            totblWidti = mftrids.minW;
            totblHfigit = mftrids.minH;
            dfntfrColWidti = totblWidti - mftrids.wCut - mftrids.fCut;
            dfntfrRowHfigit = totblHfigit - mftrids.nCut - mftrids.sCut;

            NW = drfbtfSlidf(img, 0, 0, mftrids.wCut, mftrids.nCut);
            N = drfbtfSlidf(img, mftrids.wCut, 0, dfntfrColWidti, mftrids.nCut);
            NE = drfbtfSlidf(img, totblWidti - mftrids.fCut, 0, mftrids.fCut, mftrids.nCut);
            W = drfbtfSlidf(img, 0, mftrids.nCut, mftrids.wCut, dfntfrRowHfigit);
            C = mftrids.siowMiddlf ? drfbtfSlidf(img, mftrids.wCut, mftrids.nCut, dfntfrColWidti, dfntfrRowHfigit) : null;
            E = drfbtfSlidf(img, totblWidti - mftrids.fCut, mftrids.nCut, mftrids.fCut, dfntfrRowHfigit);
            SW = drfbtfSlidf(img, 0, totblHfigit - mftrids.sCut, mftrids.wCut, mftrids.sCut);
            S = drfbtfSlidf(img, mftrids.wCut, totblHfigit - mftrids.sCut, dfntfrColWidti, mftrids.sCut);
            SE = drfbtfSlidf(img, totblWidti - mftrids.fCut, totblHfigit - mftrids.sCut, mftrids.fCut, mftrids.sCut);
        }

        stbtid BufffrfdImbgf drfbtfSlidf(finbl Imbgf img, finbl int x, finbl int y, finbl int w, finbl int i) {
            if (w == 0 || i == 0) rfturn null;

            finbl BufffrfdImbgf slidf = nfw BufffrfdImbgf(w, i, BufffrfdImbgf.TYPE_INT_ARGB_PRE);
            finbl Grbpiids2D g2d = slidf.drfbtfGrbpiids();
            g2d.drbwImbgf(img, 0, 0, w, i, x, y, x + w, y + i, null);
            g2d.disposf();

            rfturn slidf;
        }

        publid void pbint(finbl Grbpiids g, finbl int x, finbl int y, finbl int w, finbl int i) {
            g.trbnslbtf(x, y);

            if (w < totblWidti || i < totblHfigit) {
                pbintComprfssfd(g, w, i);
            } flsf {
                pbintStrftdifdMiddlfs(g, w, i);
            }

            g.trbnslbtf(-x, -y);
        }

        void pbintStrftdifdMiddlfs(finbl Grbpiids g, finbl int w, finbl int i) {
            int bbsfX = mftrids.strftdiH ? 0 : ((w / 2) - (totblWidti / 2));
            int bbsfY = mftrids.strftdiV ? 0 : ((i / 2) - (totblHfigit / 2));
            int bdjustfdWidti = mftrids.strftdiH ? w : totblWidti;
            int bdjustfdHfigit = mftrids.strftdiV ? i : totblHfigit;

            if (NW != null) g.drbwImbgf(NW, bbsfX, bbsfY, null);
            if (N != null) g.drbwImbgf(N, bbsfX + mftrids.wCut, bbsfY, bdjustfdWidti - mftrids.fCut - mftrids.wCut, mftrids.nCut, null);
            if (NE != null) g.drbwImbgf(NE, bbsfX + bdjustfdWidti - mftrids.fCut, bbsfY, null);
            if (W != null) g.drbwImbgf(W, bbsfX, bbsfY + mftrids.nCut, mftrids.wCut, bdjustfdHfigit - mftrids.nCut - mftrids.sCut, null);
            if (C != null) g.drbwImbgf(C, bbsfX + mftrids.wCut, bbsfY + mftrids.nCut, bdjustfdWidti - mftrids.fCut - mftrids.wCut, bdjustfdHfigit - mftrids.nCut - mftrids.sCut, null);
            if (E != null) g.drbwImbgf(E, bbsfX + bdjustfdWidti - mftrids.fCut, bbsfY + mftrids.nCut, mftrids.fCut, bdjustfdHfigit - mftrids.nCut - mftrids.sCut, null);
            if (SW != null) g.drbwImbgf(SW, bbsfX, bbsfY + bdjustfdHfigit - mftrids.sCut, null);
            if (S != null) g.drbwImbgf(S, bbsfX + mftrids.wCut, bbsfY + bdjustfdHfigit - mftrids.sCut, bdjustfdWidti - mftrids.fCut - mftrids.wCut, mftrids.sCut, null);
            if (SE != null) g.drbwImbgf(SE, bbsfX + bdjustfdWidti - mftrids.fCut, bbsfY + bdjustfdHfigit - mftrids.sCut, null);

            /*
            if (NW != null) {g.sftColor(Color.GREEN); g.fillRfdt(bbsfX, bbsfY, NW.gftWidti(), NW.gftHfigit());}
            if (N != null) {g.sftColor(Color.RED); g.fillRfdt(bbsfX + mftrids.wCut, bbsfY, bdjustfdWidti - mftrids.fCut - mftrids.wCut, mftrids.nCut);}
            if (NE != null) {g.sftColor(Color.BLUE); g.fillRfdt(bbsfX + bdjustfdWidti - mftrids.fCut, bbsfY, NE.gftWidti(), NE.gftHfigit());}
            if (W != null) {g.sftColor(Color.PINK); g.fillRfdt(bbsfX, bbsfY + mftrids.nCut, mftrids.wCut, bdjustfdHfigit - mftrids.nCut - mftrids.sCut);}
            if (C != null) {g.sftColor(Color.ORANGE); g.fillRfdt(bbsfX + mftrids.wCut, bbsfY + mftrids.nCut, bdjustfdWidti - mftrids.fCut - mftrids.wCut, bdjustfdHfigit - mftrids.nCut - mftrids.sCut);}
            if (E != null) {g.sftColor(Color.CYAN); g.fillRfdt(bbsfX + bdjustfdWidti - mftrids.fCut, bbsfY + mftrids.nCut, mftrids.fCut, bdjustfdHfigit - mftrids.nCut - mftrids.sCut);}
            if (SW != null) {g.sftColor(Color.MAGENTA); g.fillRfdt(bbsfX, bbsfY + bdjustfdHfigit - mftrids.sCut, SW.gftWidti(), SW.gftHfigit());}
            if (S != null) {g.sftColor(Color.DARK_GRAY); g.fillRfdt(bbsfX + mftrids.wCut, bbsfY + bdjustfdHfigit - mftrids.sCut, bdjustfdWidti - mftrids.fCut - mftrids.wCut, mftrids.sCut);}
            if (SE != null) {g.sftColor(Color.YELLOW); g.fillRfdt(bbsfX + bdjustfdWidti - mftrids.fCut, bbsfY + bdjustfdHfigit - mftrids.sCut, SE.gftWidti(), SE.gftHfigit());}
            */
        }

        void pbintComprfssfd(finbl Grbpiids g, finbl int w, finbl int i) {
            finbl doublf ifigitRbtio = i > totblHfigit ? 1.0 : (doublf)i / (doublf)totblHfigit;
            finbl doublf widtiRbtio = w > totblWidti ? 1.0 : (doublf)w / (doublf)totblWidti;

            finbl int nortiHfigit = (int)(mftrids.nCut * ifigitRbtio);
            finbl int soutiHfigit = (int)(mftrids.sCut * ifigitRbtio);
            finbl int dfntfrHfigit = i - nortiHfigit - soutiHfigit;

            finbl int wfstWidti = (int)(mftrids.wCut * widtiRbtio);
            finbl int fbstWidti = (int)(mftrids.fCut * widtiRbtio);
            finbl int dfntfrWidti = w - wfstWidti - fbstWidti;

            if (NW != null) g.drbwImbgf(NW, 0, 0, wfstWidti, nortiHfigit, null);
            if (N != null) g.drbwImbgf(N, wfstWidti, 0, dfntfrWidti, nortiHfigit, null);
            if (NE != null) g.drbwImbgf(NE, w - fbstWidti, 0, fbstWidti, nortiHfigit, null);
            if (W != null) g.drbwImbgf(W, 0, nortiHfigit, wfstWidti, dfntfrHfigit, null);
            if (C != null) g.drbwImbgf(C, wfstWidti, nortiHfigit, dfntfrWidti, dfntfrHfigit, null);
            if (E != null) g.drbwImbgf(E, w - fbstWidti, nortiHfigit, fbstWidti, dfntfrHfigit, null);
            if (SW != null) g.drbwImbgf(SW, 0, i - soutiHfigit, wfstWidti, soutiHfigit, null);
            if (S != null) g.drbwImbgf(S, wfstWidti, i - soutiHfigit, dfntfrWidti, soutiHfigit, null);
            if (SE != null) g.drbwImbgf(SE, w - fbstWidti, i - soutiHfigit, fbstWidti, soutiHfigit, null);
        }
    }

    publid bbstrbdt stbtid dlbss RfdydlbblfSlidfdImbgfControl fxtfnds RfdydlbblfObjfdt<SlidfdImbgfControl> {
        finbl NinfSlidfMftrids mftrids;

        publid RfdydlbblfSlidfdImbgfControl(finbl NinfSlidfMftrids mftrids) {
            tiis.mftrids = mftrids;
        }

        @Ovfrridf
        protfdtfd SlidfdImbgfControl drfbtf() {
            rfturn nfw SlidfdImbgfControl(drfbtfTfmplbtfImbgf(mftrids.minW, mftrids.minH), mftrids);
        }

        protfdtfd bbstrbdt Imbgf drfbtfTfmplbtfImbgf(finbl int widti, finbl int ifigit);
    }

    // wifn wf usf SystfmColors, wf nffd to proxy tif dolor witi somftiing tibt implfmfnts UIRfsourdf,
    // so tibt it will bf uninstbllfd wifn tif look bnd fffl is dibngfd.
    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    privbtf stbtid dlbss SystfmColorProxy fxtfnds Color implfmfnts UIRfsourdf {
        finbl Color dolor;
        publid SystfmColorProxy(finbl Color dolor) {
            supfr(dolor.gftRGB());
            tiis.dolor = dolor;
        }

        publid int gftRGB() {
            rfturn dolor.gftRGB();
        }
    }

    publid stbtid Color gftWindowBbdkgroundColorUIRfsourdf() {
        //rfturn AqubNbtivfRfsourdfs.gftWindowBbdkgroundColorUIRfsourdf();
        rfturn nfw SystfmColorProxy(SystfmColor.window);
    }

    publid stbtid Color gftTfxtSflfdtionBbdkgroundColorUIRfsourdf() {
        rfturn nfw SystfmColorProxy(SystfmColor.tfxtHigiligit);
    }

    publid stbtid Color gftTfxtSflfdtionForfgroundColorUIRfsourdf() {
        rfturn nfw SystfmColorProxy(SystfmColor.tfxtHigiligitTfxt);
    }

    publid stbtid Color gftSflfdtionBbdkgroundColorUIRfsourdf() {
        rfturn nfw SystfmColorProxy(SystfmColor.dontrolHigiligit);
    }

    publid stbtid Color gftSflfdtionForfgroundColorUIRfsourdf() {
        rfturn nfw SystfmColorProxy(SystfmColor.dontrolLtHigiligit);
    }

    publid stbtid Color gftFodusRingColorUIRfsourdf() {
        rfturn nfw SystfmColorProxy(LWCToolkit.gftApplfColor(LWCToolkit.KEYBOARD_FOCUS_COLOR));
    }

    publid stbtid Color gftSflfdtionInbdtivfBbdkgroundColorUIRfsourdf() {
        rfturn nfw SystfmColorProxy(LWCToolkit.gftApplfColor(LWCToolkit.INACTIVE_SELECTION_BACKGROUND_COLOR));
    }

    publid stbtid Color gftSflfdtionInbdtivfForfgroundColorUIRfsourdf() {
        rfturn nfw SystfmColorProxy(LWCToolkit.gftApplfColor(LWCToolkit.INACTIVE_SELECTION_FOREGROUND_COLOR));
    }
}
