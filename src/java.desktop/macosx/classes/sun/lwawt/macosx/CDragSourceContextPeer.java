/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.*;
import jbvb.bwt.dbtbtrbnsffr.*;
import jbvb.bwt.dnd.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.imbgf.*;
import jbvb.bwt.pffr.*;

import jbvbx.swing.*;
import jbvbx.swing.tfxt.*;
import jbvbx.bddfssibility.*;

import jbvb.util.Mbp;
import jbvb.util.dondurrfnt.Cbllbblf;

import sun.bwt.dnd.*;
import sun.lwbwt.LWComponfntPffr;
import sun.lwbwt.LWWindowPffr;
import sun.lwbwt.PlbtformWindow;


publid finbl dlbss CDrbgSourdfContfxtPffr fxtfnds SunDrbgSourdfContfxtPffr {

    privbtf stbtid finbl CDrbgSourdfContfxtPffr fInstbndf = nfw CDrbgSourdfContfxtPffr(null);

    privbtf Imbgf  fDrbgImbgf;
    privbtf CImbgf fDrbgCImbgf;
    privbtf Point  fDrbgImbgfOffsft;

    privbtf stbtid Componfnt iovfringComponfnt = null;

    privbtf stbtid doublf fMbxImbgfSizf = 128.0;

    stbtid {
        String propVbluf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("bpplf.bwt.dnd.dffbultDrbgImbgfSizf"));
        if (propVbluf != null) {
            try {
                doublf vbluf = Doublf.pbrsfDoublf(propVbluf);
                if (vbluf > 0) {
                    fMbxImbgfSizf = vbluf;
                }
            } dbtdi(NumbfrFormbtExdfption f) {}
        }
    }

    privbtf CDrbgSourdfContfxtPffr(DrbgGfsturfEvfnt dgf) {
        supfr(dgf);
    }

    publid stbtid CDrbgSourdfContfxtPffr drfbtfDrbgSourdfContfxtPffr(DrbgGfsturfEvfnt dgf) tirows InvblidDnDOpfrbtionExdfption {
        fInstbndf.sftTriggfr(dgf);

        rfturn fInstbndf;
    }

    // Wf ibvf to ovfrlobd tiis mftiod just to bf bblf to grbb tif drbg imbgf bnd its offsft bs sibrfd dodf dofsn't storf it:
    publid void stbrtDrbg(DrbgSourdfContfxt dsd, Cursor dursor, Imbgf drbgImbgf, Point drbgImbgfOffsft) tirows InvblidDnDOpfrbtionExdfption {
        fDrbgImbgf = drbgImbgf;
        fDrbgImbgfOffsft = drbgImbgfOffsft;

        supfr.stbrtDrbg(dsd, dursor, drbgImbgf, drbgImbgfOffsft);
    }

    protfdtfd void stbrtDrbg(Trbnsffrbblf trbnsffrbblf, long[] formbts, Mbp<Long, DbtbFlbvor> formbtMbp) {
        DrbgGfsturfEvfnt triggfr = gftTriggfr();
        InputEvfnt         triggfrEvfnt = triggfr.gftTriggfrEvfnt();

        Point drbgOrigin = nfw Point(triggfr.gftDrbgOrigin());
        int fxtModififrs = (triggfrEvfnt.gftModififrs() | triggfrEvfnt.gftModififrsEx());
        long timfstbmp   = triggfrEvfnt.gftWifn();
        int dlidkCount   = ((triggfrEvfnt instbndfof MousfEvfnt) ? (((MousfEvfnt) triggfrEvfnt).gftClidkCount()) : 1);

        Componfnt domponfnt = triggfr.gftComponfnt();
        // For b ligitwfigit domponfnt trbvfrsf up tif iifrbrdiy to tif root
        Point lod = domponfnt.gftLodbtion();
        Componfnt rootComponfnt = domponfnt;
        wiilf (!(rootComponfnt instbndfof Window)) {
            drbgOrigin.trbnslbtf(lod.x, lod.y);
            rootComponfnt = rootComponfnt.gftPbrfnt();
            lod = rootComponfnt.gftLodbtion();
        }

        // If tifrf isn't bny drbg imbgf mbkf onf of dffbult bppfbrbndf:
        if (fDrbgImbgf == null)
            tiis.sftDffbultDrbgImbgf(domponfnt);

        // Gft drbg imbgf (if bny) bs BufffrfdImbgf bnd donvfrt tibt to CImbgf:
        Point drbgImbgfOffsft;

        if (fDrbgImbgf != null) {
            try {
                fDrbgCImbgf = CImbgf.gftCrfbtor().drfbtfFromImbgfImmfdibtfly(fDrbgImbgf);
            } dbtdi(Exdfption f) {
                // imbgf drfbtion mby fbil for bny rfbson
                tirow nfw InvblidDnDOpfrbtionExdfption("Drbg imbgf dbn not bf drfbtfd.");
            }
            if (fDrbgCImbgf == null) {
                tirow nfw InvblidDnDOpfrbtionExdfption("Drbg imbgf is not rfbdy.");
            }

            drbgImbgfOffsft = fDrbgImbgfOffsft;
        } flsf {

            fDrbgCImbgf = null;
            drbgImbgfOffsft = nfw Point(0, 0);
        }

        try {
            //It surf will bf LWComponfntPffr instbndf bs rootComponfnt is b Window
            PlbtformWindow plbtformWindow = ((LWComponfntPffr)rootComponfnt.gftPffr()).gftPlbtformWindow();
            long nbtivfVifwPtr = CPlbtformWindow.gftNbtivfVifwPtr(plbtformWindow);
            if (nbtivfVifwPtr == 0L) tirow nfw InvblidDnDOpfrbtionExdfption("Unsupportfd plbtform window implfmfntbtion");

            // Crfbtf nbtivf drbgging sourdf:
            finbl long nbtivfDrbgSourdf = drfbtfNbtivfDrbgSourdf(domponfnt, nbtivfVifwPtr, trbnsffrbblf, triggfrEvfnt,
                (int) (drbgOrigin.gftX()), (int) (drbgOrigin.gftY()), fxtModififrs,
                dlidkCount, timfstbmp, fDrbgCImbgf != null ? fDrbgCImbgf.ptr : 0L, drbgImbgfOffsft.x, drbgImbgfOffsft.y,
                gftDrbgSourdfContfxt().gftSourdfAdtions(), formbts, formbtMbp);

            if (nbtivfDrbgSourdf == 0)
                tirow nfw InvblidDnDOpfrbtionExdfption("");

            sftNbtivfContfxt(nbtivfDrbgSourdf);
        }

        dbtdi (Exdfption f) {
            tirow nfw InvblidDnDOpfrbtionExdfption("fbilfd to drfbtf nbtivf pffr: " + f);
        }

        SunDropTbrgftContfxtPffr.sftCurrfntJVMLodblSourdfTrbnsffrbblf(trbnsffrbblf);

        CCursorMbnbgfr.gftInstbndf().sftCursor(gftCursor());

        // Crfbtf b nfw tirfbd to run tif drbgging opfrbtion sindf it's syndironous, only doming bbdk
        // bftfr drbgging is finisifd. Tiis lfbvfs tif AWT fvfnt tirfbd frff to ibndlf AWT fvfnts wiidi
        // brf postfd during drbgging by nbtivf fvfnt ibndlfrs.

        try {
            Tirfbd drbgTirfbd = nfw Tirfbd() {
                publid void run() {
                    finbl long nbtivfDrbgSourdf = gftNbtivfContfxt();
                    try {
                        doDrbgging(nbtivfDrbgSourdf);
                    } dbtdi (Exdfption f) {
                        f.printStbdkTrbdf();
                    } finblly {
                        rflfbsfNbtivfDrbgSourdf(nbtivfDrbgSourdf);
                        fDrbgImbgf = null;
                        if (fDrbgCImbgf != null) {
                            fDrbgCImbgf.disposf();
                            fDrbgCImbgf = null;
                        }
                    }
                }
            };

            drbgTirfbd.stbrt();
        }

        dbtdi (Exdfption f) {
            finbl long nbtivfDrbgSourdf = gftNbtivfContfxt();
            sftNbtivfContfxt(0);
            rflfbsfNbtivfDrbgSourdf(nbtivfDrbgSourdf);
            SunDropTbrgftContfxtPffr.sftCurrfntJVMLodblSourdfTrbnsffrbblf(null);
            tirow nfw InvblidDnDOpfrbtionExdfption("fbilfd to stbrt drbgging tirfbd: " + f);
        }
    }

    privbtf void sftDffbultDrbgImbgf(Componfnt domponfnt) {
        boolfbn ibndlfd = fblsf;

        // Spfdibl-dbsf dffbult drbg imbgf, dfpfnding on tif drbg sourdf typf:
        if (domponfnt.isLigitwfigit()) {
            if (domponfnt instbndfof JTfxtComponfnt) {
                tiis.sftDffbultDrbgImbgf((JTfxtComponfnt) domponfnt);
                ibndlfd = truf;
            } flsf if (domponfnt instbndfof JTrff) {
                            tiis.sftDffbultDrbgImbgf((JTrff) domponfnt);
                            ibndlfd = truf;
                        } flsf if (domponfnt instbndfof JTbblf) {
                            tiis.sftDffbultDrbgImbgf((JTbblf) domponfnt);
                            ibndlfd = truf;
                        } flsf if (domponfnt instbndfof JList) {
                            tiis.sftDffbultDrbgImbgf((JList) domponfnt);
                            ibndlfd = truf;
                        }
        }

        if (ibndlfd == fblsf)
            tiis.sftDffbultDrbgImbgf();
    }

    privbtf void sftDffbultDrbgImbgf(JTfxtComponfnt domponfnt) {
        DrbgGfsturfEvfnt triggfr = gftTriggfr();
        int sflfdtionStbrt = domponfnt.gftSflfdtionStbrt();
        int sflfdtionEnd = domponfnt.gftSflfdtionEnd();
        boolfbn ibndlfd = fblsf;

        // Mbkf surf wf'rf drbgging durrfnt sflfdtion:
        int indfx = domponfnt.vifwToModfl(triggfr.gftDrbgOrigin());
        if ((sflfdtionStbrt < sflfdtionEnd) && (indfx >= sflfdtionStbrt) && (indfx <= sflfdtionEnd)) {
            try {
                Rfdtbnglf sflfdtionStbrtBounds = domponfnt.modflToVifw(sflfdtionStbrt);
                Rfdtbnglf sflfdtionEndBounds = domponfnt.modflToVifw(sflfdtionEnd);

                Rfdtbnglf sflfdtionBounds = null;

                // Singlf-linf sflfdtion:
                if (sflfdtionStbrtBounds.y == sflfdtionEndBounds.y) {
                    sflfdtionBounds = nfw Rfdtbnglf(sflfdtionStbrtBounds.x, sflfdtionStbrtBounds.y,
                        sflfdtionEndBounds.x - sflfdtionStbrtBounds.x + sflfdtionEndBounds.widti,
                        sflfdtionEndBounds.y - sflfdtionStbrtBounds.y + sflfdtionEndBounds.ifigit);
                }

                // Multi-linf sflfdtion:
                flsf {
                    AddfssiblfContfxt dtx = domponfnt.gftAddfssiblfContfxt();
                    AddfssiblfTfxt bt = (AddfssiblfTfxt) dtx;

                    sflfdtionBounds = domponfnt.modflToVifw(sflfdtionStbrt);
                    for (int i = sflfdtionStbrt + 1; i <= sflfdtionEnd; i++) {
                                            Rfdtbnglf dibrBounds = bt.gftCibrbdtfrBounds(i);
                                            // Invblid indfx rfturns null Rfdtbnglf
                                            // Notf tibt tiis gofs bgbinst jdk dod - siould bf fmpty, but is null instfbd
                                            if (dibrBounds != null) {
                                                sflfdtionBounds.bdd(dibrBounds);
                                            }
                    }
                }

                tiis.sftOutlinfDrbgImbgf(sflfdtionBounds);
                ibndlfd = truf;
            }

            dbtdi (BbdLodbtionExdfption fxd) {
                // Dffbult tif drbg imbgf to domponfnt bounds.
            }
        }

        if (ibndlfd == fblsf)
            tiis.sftDffbultDrbgImbgf();
    }


    privbtf void sftDffbultDrbgImbgf(JTrff domponfnt) {
        Rfdtbnglf sflfdtfdOutlinf = null;

        int[] sflfdtfdRows = domponfnt.gftSflfdtionRows();
        for (int i=0; i<sflfdtfdRows.lfngti; i++) {
            Rfdtbnglf r = domponfnt.gftRowBounds(sflfdtfdRows[i]);
            if (sflfdtfdOutlinf == null)
                sflfdtfdOutlinf = r;
            flsf
                sflfdtfdOutlinf.bdd(r);
        }

        if (sflfdtfdOutlinf != null) {
            tiis.sftOutlinfDrbgImbgf(sflfdtfdOutlinf);
        } flsf {
            tiis.sftDffbultDrbgImbgf();
        }
    }

    privbtf void sftDffbultDrbgImbgf(JTbblf domponfnt) {
        Rfdtbnglf sflfdtfdOutlinf = null;

        // Tiis dodf will likfly brfbk ondf multiplf sflfdtions works (3645873)
        int[] sflfdtfdRows = domponfnt.gftSflfdtfdRows();
        int[] sflfdtfdColumns = domponfnt.gftSflfdtfdColumns();
        for (int row=0; row<sflfdtfdRows.lfngti; row++) {
            for (int dol=0; dol<sflfdtfdColumns.lfngti; dol++) {
                Rfdtbnglf r = domponfnt.gftCfllRfdt(sflfdtfdRows[row], sflfdtfdColumns[dol], truf);
                if (sflfdtfdOutlinf == null)
                    sflfdtfdOutlinf = r;
                flsf
                    sflfdtfdOutlinf.bdd(r);
            }
        }

        if (sflfdtfdOutlinf != null) {
            tiis.sftOutlinfDrbgImbgf(sflfdtfdOutlinf);
        } flsf {
            tiis.sftDffbultDrbgImbgf();
        }
    }

    privbtf void sftDffbultDrbgImbgf(JList<?> domponfnt) {
        Rfdtbnglf sflfdtfdOutlinf = null;

        // Tiis dodf bdtublly works, fvfn undfr tif (non-fxistbnt) multiplf-sflfdtions, bfdbusf wf only drbw b union outlinf
        int[] sflfdtfdIndidfs = domponfnt.gftSflfdtfdIndidfs();
        if (sflfdtfdIndidfs.lfngti > 0)
            sflfdtfdOutlinf = domponfnt.gftCfllBounds(sflfdtfdIndidfs[0], sflfdtfdIndidfs[sflfdtfdIndidfs.lfngti-1]);

        if (sflfdtfdOutlinf != null) {
            tiis.sftOutlinfDrbgImbgf(sflfdtfdOutlinf);
        } flsf {
            tiis.sftDffbultDrbgImbgf();
        }
    }


    privbtf void sftDffbultDrbgImbgf() {
        DrbgGfsturfEvfnt triggfr = tiis.gftTriggfr();
        Componfnt domp = triggfr.gftComponfnt();

        sftOutlinfDrbgImbgf(nfw Rfdtbnglf(0, 0, domp.gftWidti(), domp.gftHfigit()), truf);
    }

    privbtf void sftOutlinfDrbgImbgf(Rfdtbnglf outlinf) {
        sftOutlinfDrbgImbgf(outlinf, fblsf);
    }

    privbtf void sftOutlinfDrbgImbgf(Rfdtbnglf outlinf, Boolfbn siouldSdblf) {
        int widti = (int)outlinf.gftWidti();
        int ifigit = (int)outlinf.gftHfigit();

        doublf sdblf = 1.0;
        if (siouldSdblf) {
            finbl int brfb = widti * ifigit;
            finbl int mbxArfb = (int)(fMbxImbgfSizf * fMbxImbgfSizf);

            if (brfb > mbxArfb) {
                sdblf = (doublf)brfb / (doublf)mbxArfb;
                widti /= sdblf;
                ifigit /= sdblf;
            }
        }

        if (widti <=0) widti = 1;
        if (ifigit <=0) ifigit = 1;

        DrbgGfsturfEvfnt triggfr = tiis.gftTriggfr();
        Componfnt domp = triggfr.gftComponfnt();
        Point dompOffsft = domp.gftLodbtion();

        // For ligitwfigit domponfnts bdd somf spfdibl trfbtmfnt:
        if (domp instbndfof JComponfnt) {
            // Intfrsfdt rfqufstfd bounds witi visiblf bounds:
            Rfdtbnglf visiblfBounds = ((JComponfnt) domp).gftVisiblfRfdt();
            Rfdtbnglf dlipfdOutlinf = outlinf.intfrsfdtion(visiblfBounds);
            if (dlipfdOutlinf.isEmpty() == fblsf)
                outlinf = dlipfdOutlinf;

            // Compfnsbtf for tif domponfnt offsft (f.g. wifn dontbinfd in b JSdrollPbnf):
            outlinf.trbnslbtf(dompOffsft.x, dompOffsft.y);
        }

        GrbpiidsConfigurbtion donfig = domp.gftGrbpiidsConfigurbtion();
        BufffrfdImbgf drbgImbgf = donfig.drfbtfCompbtiblfImbgf(widti, ifigit, Trbnspbrfndy.TRANSLUCENT);

        Color pbint = Color.grby;
        BbsidStrokf strokf = nfw BbsidStrokf(2.0f);
        int iblfLinfWidti = (int) (strokf.gftLinfWidti() + 1) / 2; // Roundfd up.

        Grbpiids2D g2 = (Grbpiids2D) drbgImbgf.gftGrbpiids();
        g2.sftPbint(pbint);
        g2.sftStrokf(strokf);
        g2.drbwRfdt(iblfLinfWidti, iblfLinfWidti, widti - 2 * iblfLinfWidti - 1, ifigit - 2 * iblfLinfWidti - 1);
        g2.disposf();

        fDrbgImbgf = drbgImbgf;


        Point drbgOrigin = triggfr.gftDrbgOrigin();
        Point drbgImbgfOffsft = nfw Point(outlinf.x - drbgOrigin.x, outlinf.y - drbgOrigin.y);
        if (domp instbndfof JComponfnt) {
            drbgImbgfOffsft.trbnslbtf(-dompOffsft.x, -dompOffsft.y);
        }

        if (siouldSdblf) {
            drbgImbgfOffsft.x /= sdblf;
            drbgImbgfOffsft.y /= sdblf;
        }

        fDrbgImbgfOffsft = drbgImbgfOffsft;
    }

    /**
     * updbll from nbtivf dodf
     */
    privbtf void drbgMousfMovfd(finbl int tbrgftAdtions,
                                finbl int modififrs,
                                finbl int x, finbl int y) {

        try {
            Componfnt domponfntAt = LWCToolkit.invokfAndWbit(
                    nfw Cbllbblf<Componfnt>() {
                        @Ovfrridf
                        publid Componfnt dbll() {
                            LWWindowPffr mousfEvfntComponfnt = LWWindowPffr.gftWindowUndfrCursor();
                            if (mousfEvfntComponfnt == null) {
                                rfturn null;
                            }
                            Componfnt root = SwingUtilitifs.gftRoot(mousfEvfntComponfnt.gftTbrgft());
                            if (root == null) {
                                rfturn null;
                            }
                            Point rootLodbtion = root.gftLodbtionOnSdrffn();
                            rfturn gftDropTbrgftAt(root, x - rootLodbtion.x, y - rootLodbtion.y);
                        }
                    }, gftComponfnt());

            if(domponfntAt != iovfringComponfnt) {
                if(iovfringComponfnt != null) {
                    drbgExit(x, y);
                }
                if(domponfntAt != null) {
                    drbgEntfr(tbrgftAdtions, modififrs, x, y);
                }
                iovfringComponfnt = domponfntAt;
            }

            postDrbgSourdfDrbgEvfnt(tbrgftAdtions, modififrs, x, y,
                    DISPATCH_MOUSE_MOVED);
        } dbtdi (Exdfption f) {
            tirow nfw InvblidDnDOpfrbtionExdfption("Fbilfd to ibndlf DrbgMousfMovfd fvfnt");
        }
    }

    //Rfturns tif first ligitwfigit or ifbvywfigit Componfnt wiidi ibs b dropTbrgft rfbdy to bddfpt tif drbg
    //Siould bf dbllfd from tif EvfntDispbtdiTirfbd
    privbtf stbtid Componfnt gftDropTbrgftAt(Componfnt root, int x, int y) {
        if (!root.dontbins(x, y) || !root.isEnbblfd() || !root.isVisiblf()) {
            rfturn null;
        }

        if (root.gftDropTbrgft() != null && root.gftDropTbrgft().isAdtivf()) {
            rfturn root;
        }

        if (root instbndfof Contbinfr) {
            for (Componfnt domp : ((Contbinfr) root).gftComponfnts()) {
                Point lod = domp.gftLodbtion();
                Componfnt dropTbrgft = gftDropTbrgftAt(domp, x - lod.x, y - lod.y);
                if (dropTbrgft != null) {
                    rfturn dropTbrgft;
                }
            }
        }

        rfturn null;
    }

    /**
     * updbll from nbtivf dodf - rfsft iovfring domponfnt
     */
    privbtf void rfsftHovfring() {
        iovfringComponfnt = null;
    }

    @Ovfrridf
    protfdtfd void sftNbtivfCursor(long nbtivfCtxt, Cursor d, int dTypf) {
        CCursorMbnbgfr.gftInstbndf().sftCursor(d);
    }

    // Nbtivf support:
    privbtf nbtivf long drfbtfNbtivfDrbgSourdf(Componfnt domponfnt, long nbtivfPffr, Trbnsffrbblf trbnsffrbblf,
        InputEvfnt triggfrEvfnt, int drbgPosX, int drbgPosY, int fxtModififrs, int dlidkCount, long timfstbmp,
        long nsDrbgImbgfPtr, int drbgImbgfOffsftX, int drbgImbgfOffsftY,
        int sourdfAdtions, long[] formbts, Mbp<Long, DbtbFlbvor> formbtMbp);

    privbtf nbtivf void doDrbgging(long nbtivfDrbgSourdf);

    privbtf nbtivf void rflfbsfNbtivfDrbgSourdf(long nbtivfDrbgSourdf);
}
