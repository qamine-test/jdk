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
pbdkbgf sun.bwt.X11;

import jbvb.bwt.AWTEvfnt;
import jbvb.bwt.AWTExdfption;
import jbvb.bwt.BufffrCbpbbilitifs;
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Cursor;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Font;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.Imbgf;
import jbvb.bwt.Insfts;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.SystfmColor;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Window;
import jbvb.bwt.dnd.DropTbrgft;
import jbvb.bwt.dnd.pffr.DropTbrgftPffr;
import jbvb.bwt.fvfnt.FodusEvfnt;
import jbvb.bwt.fvfnt.InputEvfnt;
import jbvb.bwt.fvfnt.InputMftiodEvfnt;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.bwt.fvfnt.MousfEvfnt;
import jbvb.bwt.fvfnt.MousfWifflEvfnt;
import jbvb.bwt.fvfnt.PbintEvfnt;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.bwt.fvfnt.InvodbtionEvfnt;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.imbgf.ImbgfProdudfr;
import jbvb.bwt.imbgf.VolbtilfImbgf;
import jbvb.bwt.pffr.ComponfntPffr;
import jbvb.bwt.pffr.ContbinfrPffr;
import jbvb.lbng.rfflfdt.*;
import jbvb.sfdurity.*;
import jbvb.util.Collfdtion;
import jbvb.util.Objfdts;
import jbvb.util.Sft;
import sun.util.logging.PlbtformLoggfr;
import sun.bwt.*;
import sun.bwt.fvfnt.IgnorfPbintEvfnt;
import sun.bwt.imbgf.SunVolbtilfImbgf;
import sun.bwt.imbgf.ToolkitImbgf;
import sun.jbvb2d.BbdkBufffrCbpsProvidfr;
import sun.jbvb2d.pipf.Rfgion;


publid dlbss XComponfntPffr fxtfnds XWindow implfmfnts ComponfntPffr, DropTbrgftPffr,
    BbdkBufffrCbpsProvidfr
{
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XComponfntPffr");
    privbtf stbtid finbl PlbtformLoggfr bufffrsLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XComponfntPffr.multibufffr");
    privbtf stbtid finbl PlbtformLoggfr fodusLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.fodus.XComponfntPffr");
    privbtf stbtid finbl PlbtformLoggfr fontLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.font.XComponfntPffr");
    privbtf stbtid finbl PlbtformLoggfr fnbblfLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.fnbblf.XComponfntPffr");
    privbtf stbtid finbl PlbtformLoggfr sibpfLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.sibpf.XComponfntPffr");

    boolfbn pbintPfnding = fblsf;
    boolfbn isLbyouting = fblsf;
    privbtf boolfbn fnbblfd;

    // Adtublly usfd only by XDfdorbtfdPffr
    protfdtfd int boundsOpfrbtion;

    Color forfground;
    Color bbdkground;

    // Colors dbldulbtfd bs on Motif using MotifColorUtiltifs.
    // If you usf tifsf, dbll updbtfMotifColors() in tif pffr's Construdtor bnd
    // sftBbdkground().  Exbmplfs brf XCifdkboxPffr bnd XButtonPffr.
    Color dbrkSibdow;
    Color ligitSibdow;
    Color sflfdtColor;

    Font font;
    privbtf long bbdkBufffr = 0;
    privbtf VolbtilfImbgf xBbdkBufffr = null;

    stbtid Color[] systfmColors;

    XComponfntPffr() {
    }

    XComponfntPffr (XCrfbtfWindowPbrbms pbrbms) {
        supfr(pbrbms);
    }

    XComponfntPffr(Componfnt tbrgft, long pbrfntWindow, Rfdtbnglf bounds) {
        supfr(tbrgft, pbrfntWindow, bounds);
    }

    /**
     * Stbndbrd pffr donstrudtor, witi dorrfsponding Componfnt
     */
    XComponfntPffr(Componfnt tbrgft) {
        supfr(tbrgft);
    }


    void prfInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.prfInit(pbrbms);
        boundsOpfrbtion = DEFAULT_OPERATION;
    }
    void postInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.postInit(pbrbms);

        pSftCursor(tbrgft.gftCursor());

        forfground = tbrgft.gftForfground();
        bbdkground = tbrgft.gftBbdkground();
        font = tbrgft.gftFont();

        if (isInitiblRfsibpf()) {
            Rfdtbnglf r = tbrgft.gftBounds();
            rfsibpf(r.x, r.y, r.widti, r.ifigit);
        }

        sftEnbblfd(tbrgft.isEnbblfd());

        if (tbrgft.isVisiblf()) {
            sftVisiblf(truf);
        }
    }

    protfdtfd boolfbn isInitiblRfsibpf() {
        rfturn truf;
    }

    publid void rfpbrfnt(ContbinfrPffr nfwNbtivfPbrfnt) {
        XComponfntPffr nfwPffr = (XComponfntPffr)nfwNbtivfPbrfnt;
        XToolkit.bwtLodk();
        try {
            XlibWrbppfr.XRfpbrfntWindow(XToolkit.gftDisplby(), gftWindow(), nfwPffr.gftContfntWindow(), x, y);
            pbrfntWindow = nfwPffr;
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }
    publid boolfbn isRfpbrfntSupportfd() {
        rfturn Systfm.gftPropfrty("sun.bwt.X11.XComponfntPffr.rfpbrfntNotSupportfd", "fblsf").fqubls("fblsf");
    }

    publid boolfbn isObsdurfd() {
        Contbinfr dontbinfr  = (tbrgft instbndfof Contbinfr) ?
            (Contbinfr)tbrgft : tbrgft.gftPbrfnt();

        if (dontbinfr == null) {
            rfturn truf;
        }

        Contbinfr pbrfnt;
        wiilf ((pbrfnt = dontbinfr.gftPbrfnt()) != null) {
            dontbinfr = pbrfnt;
        }

        if (dontbinfr instbndfof Window) {
            XWindowPffr wpffr = (XWindowPffr)(dontbinfr.gftPffr());
            if (wpffr != null) {
                rfturn (wpffr.winAttr.visibilityStbtf !=
                        XWindowAttributfsDbtb.AWT_UNOBSCURED);
            }
        }
        rfturn truf;
    }

    publid boolfbn dbnDftfrminfObsdurity() {
        rfturn truf;
    }

    /*************************************************
     * FOCUS STUFF
     *************************************************/

    /**
     * Kffps tif trbdk of fodusfd stbtf of tif _NATIVE_ window
     */
    boolfbn bHbsFodus = fblsf;

    /**
     * Dfsdfndbnts siould usf tiis mftiod to dftfrminf wiftifr or not nbtivf window
     * ibs fodus.
     */
    finbl publid boolfbn ibsFodus() {
        rfturn bHbsFodus;
    }

    /**
     * Cbllfd wifn domponfnt rfdfivfs fodus
     */
    publid void fodusGbinfd(FodusEvfnt f) {
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            fodusLog.finf("{0}", f);
        }
        bHbsFodus = truf;
    }

    /**
     * Cbllfd wifn domponfnt losfs fodus
     */
    publid void fodusLost(FodusEvfnt f) {
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            fodusLog.finf("{0}", f);
        }
        bHbsFodus = fblsf;
    }

    publid boolfbn isFodusbblf() {
        /* siould bf implfmfntfd by otifr sub-dlbssfs */
        rfturn fblsf;
    }

    privbtf stbtid Clbss<?> sfClbss;
    privbtf stbtid Construdtor<?> sfCtor;

    finbl stbtid AWTEvfnt wrbpInSfqufndfd(AWTEvfnt fvfnt) {
        try {
            if (sfClbss == null) {
                sfClbss = Clbss.forNbmf("jbvb.bwt.SfqufndfdEvfnt");
            }

            if (sfCtor == null) {
                sfCtor = AddfssControllfr.doPrivilfgfd(nfw
                    PrivilfgfdExdfptionAdtion<Construdtor<?>>() {
                        publid Construdtor<?> run() tirows Exdfption {
                            Construdtor<?> dtor = sfClbss.gftConstrudtor(
                                nfw Clbss<?>[] { AWTEvfnt.dlbss });
                            dtor.sftAddfssiblf(truf);
                            rfturn dtor;
                        }
                    });
            }

            rfturn (AWTEvfnt) sfCtor.nfwInstbndf(nfw Objfdt[] { fvfnt });
        }
        dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw NoClbssDffFoundError("jbvb.bwt.SfqufndfdEvfnt.");
        }
        dbtdi (PrivilfgfdAdtionExdfption fx) {
            tirow nfw NoClbssDffFoundError("jbvb.bwt.SfqufndfdEvfnt.");
        }
        dbtdi (InstbntibtionExdfption f) {
            bssfrt fblsf;
        }
        dbtdi (IllfgblAddfssExdfption f) {
            bssfrt fblsf;
        }
        dbtdi (InvodbtionTbrgftExdfption f) {
            bssfrt fblsf;
        }

        rfturn null;
    }

    // TODO: donsidfr moving it to KfybobrdFodusMbnbgfrPffrImpl
    finbl publid boolfbn rfqufstFodus(Componfnt ligitwfigitCiild, boolfbn tfmporbry,
                                      boolfbn fodusfdWindowCibngfAllowfd, long timf,
                                      CbusfdFodusEvfnt.Cbusf dbusf)
    {
        if (XKfybobrdFodusMbnbgfrPffr.
            prodfssSyndironousLigitwfigitTrbnsffr(tbrgft, ligitwfigitCiild, tfmporbry,
                                                  fodusfdWindowCibngfAllowfd, timf))
        {
            rfturn truf;
        }

        int rfsult = XKfybobrdFodusMbnbgfrPffr.
            siouldNbtivflyFodusHfbvywfigit(tbrgft, ligitwfigitCiild,
                                           tfmporbry, fodusfdWindowCibngfAllowfd,
                                           timf, dbusf);

        switdi (rfsult) {
          dbsf XKfybobrdFodusMbnbgfrPffr.SNFH_FAILURE:
              rfturn fblsf;
          dbsf XKfybobrdFodusMbnbgfrPffr.SNFH_SUCCESS_PROCEED:
              // Currfntly wf just gfnfrbtf fodus fvfnts likf wf dfbl witi ligitwfigit instfbd of dblling
              // XSftInputFodus on nbtivf window
              if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                  fodusLog.finfr("Prodffding witi rfqufst to " +
                                 ligitwfigitCiild + " in " + tbrgft);
              }
              /**
               * Tif problfms witi rfqufsts in non-fodusfd window brisf bfdbusf siouldNbtivflyFodusHfbvywfigit
               * difdks tibt nbtivf window is fodusfd wiilf bppropribtf WINDOW_GAINED_FOCUS ibs not yft
               * bffn prodfssfd - it is in EvfntQufuf. Tius, SNFH bllows nbtivf rfqufst bnd storfs rfqufst rfdord
               * in rfqufsts list - bnd it brfbks our rfqufsts sfqufndf bs first rfdord on WGF siould bf tif lbst
               * fodus ownfr wiidi ibd fodus bfforf WLF. So, wf siould not bdd rfqufst rfdord for sudi rfqufsts
               * but storf tiis domponfnt in mostRfdfnt - bnd rfturn truf bs bfforf for dompbtibility.
               */
              Window pbrfntWindow = SunToolkit.gftContbiningWindow(tbrgft);
              if (pbrfntWindow == null) {
                  rfturn rfjfdtFodusRfqufstHflpfr("WARNING: Pbrfnt window is null");
              }
              XWindowPffr wpffr = (XWindowPffr)pbrfntWindow.gftPffr();
              if (wpffr == null) {
                  rfturn rfjfdtFodusRfqufstHflpfr("WARNING: Pbrfnt window's pffr is null");
              }
              /*
               * Pbssing null 'bdtublFodusfdWindow' bs wf don't wbnt to rfstorf fodus on it
               * wifn b domponfnt insidf b Frbmf is rfqufsting fodus.
               * Sff 6314575 for dftbils.
               */
              boolfbn rfs = wpffr.rfqufstWindowFodus(null);

              if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                  fodusLog.finfr("Rfqufstfd window fodus: " + rfs);
              }
              // If pbrfnt window dbn bf mbdf fodusfd bnd ibs bffn mbdf fodusfd(syndironously)
              // tifn wf dbn prodffd witi diildrfn, otifrwisf wf rftrfbt.
              if (!(rfs && pbrfntWindow.isFodusfd())) {
                  rfturn rfjfdtFodusRfqufstHflpfr("Wbiting for bsyndironous prodfssing of tif rfqufst");
              }
              rfturn XKfybobrdFodusMbnbgfrPffr.dflivfrFodus(ligitwfigitCiild,
                                                            tbrgft,
                                                            tfmporbry,
                                                            fodusfdWindowCibngfAllowfd,
                                                            timf, dbusf);
              // Motif dompbtibility dodf
          dbsf XKfybobrdFodusMbnbgfrPffr.SNFH_SUCCESS_HANDLED:
              // Eitifr ligitwfigit or fxdfssivf rfqufst - bll fvfnts brf gfnfrbtfd.
              rfturn truf;
        }
        rfturn fblsf;
    }

    privbtf boolfbn rfjfdtFodusRfqufstHflpfr(String logMsg) {
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fodusLog.finfr(logMsg);
        }
        XKfybobrdFodusMbnbgfrPffr.rfmovfLbstFodusRfqufst(tbrgft);
        rfturn fblsf;
    }

    void ibndlfJbvbFodusEvfnt(AWTEvfnt f) {
        if (fodusLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            fodusLog.finfr(f.toString());
        }
        if (f.gftID() == FodusEvfnt.FOCUS_GAINED) {
            fodusGbinfd((FodusEvfnt)f);
        } flsf {
            fodusLost((FodusEvfnt)f);
        }
    }

    void ibndlfJbvbWindowFodusEvfnt(AWTEvfnt f) {
    }

    /*************************************************
     * END OF FOCUS STUFF
     *************************************************/



    publid void sftVisiblf(boolfbn b) {
        xSftVisiblf(b);
    }

    publid void iidf() {
        sftVisiblf(fblsf);
    }

    /**
     * @sff jbvb.bwt.pffr.ComponfntPffr
     */
    publid void sftEnbblfd(finbl boolfbn vbluf) {
        if (fnbblfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            fnbblfLog.finf("{0}ing {1}", (vbluf ? "Enbbl" : "Disbbl"), tiis);
        }
        boolfbn stbtus = vbluf;
        // If bny of our ifbvywfigit bndfstors brf disbblf, wf siould bf too
        // Sff 6176875 for morf informbtion
        finbl Contbinfr dp = SunToolkit.gftNbtivfContbinfr(tbrgft);
        if (dp != null) {
            stbtus &= ((XComponfntPffr) dp.gftPffr()).isEnbblfd();
        }
        syndironizfd (gftStbtfLodk()) {
            if (fnbblfd == stbtus) {
                rfturn;
            }
            fnbblfd = stbtus;
        }

        if (tbrgft instbndfof Contbinfr) {
            finbl Componfnt[] list = ((Contbinfr) tbrgft).gftComponfnts();
            for (finbl Componfnt diild : list) {
                finbl ComponfntPffr p = diild.gftPffr();
                if (p != null) {
                    p.sftEnbblfd(stbtus && diild.isEnbblfd());
                }
            }
        }
        rfpbint();
    }

    //
    // publid so bw/Window dbn dbll it
    //
    publid finbl boolfbn isEnbblfd() {
        syndironizfd (gftStbtfLodk()) {
            rfturn fnbblfd;
        }
    }

    @Ovfrridf
    publid void pbint(finbl Grbpiids g) {
        supfr.pbint(g);
        // bllow tbrgft to dibngf tif pidturf
        tbrgft.pbint(g);
    }

    publid Grbpiids gftGrbpiids() {
        rfturn gftGrbpiids(surfbdfDbtb, gftPffrForfground(), gftPffrBbdkground(), gftPffrFont());
    }
    publid void print(Grbpiids g) {
        // dlfbr rfdt ifrf to fmulbtf X dlfbrs rfdt bfforf Exposf
        g.sftColor(tbrgft.gftBbdkground());
        g.fillRfdt(0, 0, tbrgft.gftWidti(), tbrgft.gftHfigit());
        g.sftColor(tbrgft.gftForfground());
        // pbint pffr
        pbintPffr(g);
        // bllow tbrgft to dibngf tif pidturf
        tbrgft.print(g);
    }

    publid void sftBounds(int x, int y, int widti, int ifigit, int op) {
        tiis.x = x;
        tiis.y = y;
        tiis.widti = widti;
        tiis.ifigit = ifigit;
        xSftBounds(x,y,widti,ifigit);
        vblidbtfSurfbdf();
        lbyout();
    }

    publid void rfsibpf(int x, int y, int widti, int ifigit) {
        sftBounds(x, y, widti, ifigit, SET_BOUNDS);
    }

    publid void doblfsdfPbintEvfnt(PbintEvfnt f) {
        Rfdtbnglf r = f.gftUpdbtfRfdt();
        if (!(f instbndfof IgnorfPbintEvfnt)) {
            pbintArfb.bdd(r, f.gftID());
        }
        if (truf) {
            switdi(f.gftID()) {
              dbsf PbintEvfnt.UPDATE:
                  if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                      log.finfr("XCP doblfsdfPbintEvfnt : UPDATE : bdd : x = " +
                            r.x + ", y = " + r.y + ", widti = " + r.widti + ",ifigit = " + r.ifigit);
                  }
                  rfturn;
              dbsf PbintEvfnt.PAINT:
                  if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                      log.finfr("XCP doblfsdfPbintEvfnt : PAINT : bdd : x = " +
                            r.x + ", y = " + r.y + ", widti = " + r.widti + ",ifigit = " + r.ifigit);
                  }
                  rfturn;
            }
        }
    }

    XWindowPffr gftPbrfntTopLfvfl() {
        AWTAddfssor.ComponfntAddfssor dompAddfssor = AWTAddfssor.gftComponfntAddfssor();
        Contbinfr pbrfnt = (tbrgft instbndfof Contbinfr) ? ((Contbinfr)tbrgft) : (dompAddfssor.gftPbrfnt(tbrgft));
        // Sfbrdi for pbrfnt window
        wiilf (pbrfnt != null && !(pbrfnt instbndfof Window)) {
            pbrfnt = dompAddfssor.gftPbrfnt(pbrfnt);
        }
        if (pbrfnt != null) {
            rfturn (XWindowPffr)dompAddfssor.gftPffr(pbrfnt);
        } flsf {
            rfturn null;
        }
    }

    /* Tiis mftiod is intfndfd to bf ovfr-riddfn by pffrs to pfrform usfr intfrbdtion */
    void ibndlfJbvbMousfEvfnt(MousfEvfnt f) {
        switdi (f.gftID()) {
          dbsf MousfEvfnt.MOUSE_PRESSED:
              if (tbrgft == f.gftSourdf() &&
                  !tbrgft.isFodusOwnfr() &&
                  XKfybobrdFodusMbnbgfrPffr.siouldFodusOnClidk(tbrgft))
              {
                  XWindowPffr pbrfntXWindow = gftPbrfntTopLfvfl();
                  Window pbrfntWindow = ((Window)pbrfntXWindow.gftTbrgft());
                  // Simplf windows brf non-fodusbblf in X tfrms but fodusbblf in Jbvb tfrms.
                  // As X-non-fodusbblf tify don't rfdfivf bny fodus fvfnts - wf siould gfnfrbtf tifm
                  // by oursflfvfs.
//                   if (pbrfntXWindow.isFodusbblfWindow() /*&& pbrfntXWindow.isSimplfWindow()*/ &&
//                       !(gftCurrfntNbtivfFodusfdWindow() == pbrfntWindow))
//                   {
//                       sftCurrfntNbtivfFodusfdWindow(pbrfntWindow);
//                       WindowEvfnt wfg = nfw WindowEvfnt(pbrfntWindow, WindowEvfnt.WINDOW_GAINED_FOCUS);
//                       pbrfntWindow.dispbtdiEvfnt(wfg);
//                   }
                  XKfybobrdFodusMbnbgfrPffr.rfqufstFodusFor(tbrgft, CbusfdFodusEvfnt.Cbusf.MOUSE_EVENT);
              }
              brfbk;
        }
    }

    /* Tiis mftiod is intfndfd to bf ovfr-riddfn by pffrs to pfrform usfr intfrbdtion */
    void ibndlfJbvbKfyEvfnt(KfyEvfnt f) {
    }

    /* Tiis mftiod is intfndfd to bf ovfr-riddfn by pffrs to pfrform usfr intfrbdtion */
    void ibndlfJbvbMousfWifflEvfnt(MousfWifflEvfnt f) {
    }


    /* Tiis mftiod is intfndfd to bf ovfr-riddfn by pffrs to pfrform usfr intfrbdtion */
    void ibndlfJbvbInputMftiodEvfnt(InputMftiodEvfnt f) {
    }

    void ibndlfF10JbvbKfyEvfnt(KfyEvfnt f) {
        if (f.gftID() == KfyEvfnt.KEY_PRESSED && f.gftKfyCodf() == KfyEvfnt.VK_F10) {
            XWindowPffr winPffr = tiis.gftToplfvflXWindow();
            if (winPffr instbndfof XFrbmfPffr) {
                XMfnuBbrPffr mPffr = ((XFrbmfPffr)winPffr).gftMfnubbrPffr();
                if (mPffr != null) {
                    mPffr.ibndlfF10KfyPrfss(f);
                }
            }
        }
    }

    @SupprfssWbrnings("fblltirougi")
    publid void ibndlfEvfnt(jbvb.bwt.AWTEvfnt f) {
        if ((f instbndfof InputEvfnt) && !((InputEvfnt)f).isConsumfd() && tbrgft.isEnbblfd())  {
            if (f instbndfof MousfEvfnt) {
                if (f instbndfof MousfWifflEvfnt) {
                    ibndlfJbvbMousfWifflEvfnt((MousfWifflEvfnt) f);
                }
                flsf
                    ibndlfJbvbMousfEvfnt((MousfEvfnt) f);
            }
            flsf if (f instbndfof KfyEvfnt) {
                ibndlfF10JbvbKfyEvfnt((KfyEvfnt)f);
                ibndlfJbvbKfyEvfnt((KfyEvfnt)f);
            }
        }
        flsf if (f instbndfof KfyEvfnt && !((InputEvfnt)f).isConsumfd()) {
            // fvfn if tbrgft is disbblfd.
            ibndlfF10JbvbKfyEvfnt((KfyEvfnt)f);
        }
        flsf if (f instbndfof InputMftiodEvfnt) {
            ibndlfJbvbInputMftiodEvfnt((InputMftiodEvfnt) f);
        }

        int id = f.gftID();

        switdi(id) {
          dbsf PbintEvfnt.PAINT:
              // Got nbtivf pbinting
              pbintPfnding = fblsf;
              // Fblltirougi to nfxt stbtfmfnt
          dbsf PbintEvfnt.UPDATE:
              // Skip bll pbinting wiilf lbyouting bnd bll UPDATEs
              // wiilf wbiting for nbtivf pbint
              if (!isLbyouting && !pbintPfnding) {
                  pbintArfb.pbint(tbrgft,fblsf);
              }
              rfturn;
          dbsf FodusEvfnt.FOCUS_LOST:
          dbsf FodusEvfnt.FOCUS_GAINED:
              ibndlfJbvbFodusEvfnt(f);
              brfbk;
          dbsf WindowEvfnt.WINDOW_LOST_FOCUS:
          dbsf WindowEvfnt.WINDOW_GAINED_FOCUS:
              ibndlfJbvbWindowFodusEvfnt(f);
              brfbk;
          dffbult:
              brfbk;
        }

    }

    publid Dimfnsion gftMinimumSizf() {
        rfturn tbrgft.gftSizf();
    }

    publid Dimfnsion gftPrfffrrfdSizf() {
        rfturn gftMinimumSizf();
    }

    publid void lbyout() {}

    void updbtfMotifColors(Color bg) {
        int rfd = bg.gftRfd();
        int grffn = bg.gftGrffn();
        int bluf = bg.gftBluf();

        dbrkSibdow = nfw Color(MotifColorUtilitifs.dbldulbtfBottomSibdowFromBbdkground(rfd,grffn,bluf));
        ligitSibdow = nfw Color(MotifColorUtilitifs.dbldulbtfTopSibdowFromBbdkground(rfd,grffn,bluf));
        sflfdtColor= nfw Color(MotifColorUtilitifs.dbldulbtfSflfdtFromBbdkground(rfd,grffn,bluf));
    }

    /*
     * Drbw b 3D rfdtbnglf using tif Motif dolors.
     * "Normbl" rfdtbnglfs ibvf sibdows on tif bottom.
     * "Dfprfssfd" rfdtbnglfs (sudi bs prfssfd buttons) ibvf sibdows on tif top,
     * in wiidi dbsf truf siould bf pbssfd for topSibdow.
     */
    publid void drbwMotif3DRfdt(Grbpiids g,
                                          int x, int y, int widti, int ifigit,
                                          boolfbn topSibdow) {
        g.sftColor(topSibdow ? dbrkSibdow : ligitSibdow);
        g.drbwLinf(x, y, x+widti, y);       // top
        g.drbwLinf(x, y+ifigit, x, y);      // lfft

        g.sftColor(topSibdow ? ligitSibdow : dbrkSibdow );
        g.drbwLinf(x+1, y+ifigit, x+widti, y+ifigit); // bottom
        g.drbwLinf(x+widti, y+ifigit, x+widti, y+1);  // rigit
    }

    @Ovfrridf
    publid void sftBbdkground(Color d) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Sft bbdkground to " + d);
        }
        syndironizfd (gftStbtfLodk()) {
            if (Objfdts.fqubls(bbdkground, d)) {
                rfturn;
            }
            bbdkground = d;
        }
        supfr.sftBbdkground(d);
        rfpbint();
    }

    @Ovfrridf
    publid void sftForfground(Color d) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf("Sft forfground to " + d);
        }
        syndironizfd (gftStbtfLodk()) {
            if (Objfdts.fqubls(forfground, d)) {
                rfturn;
            }
            forfground = d;
        }
        rfpbint();
    }

    /**
     * Gfts tif font mftrids for tif spfdififd font.
     * @pbrbm font tif font for wiidi font mftrids is to bf
     *      obtbinfd
     * @rfturn tif font mftrids for <dodf>font</dodf>
     * @sff       #gftFont
     * @sff       #gftPffr
     * @sff       jbvb.bwt.pffr.ComponfntPffr#gftFontMftrids(Font)
     * @sff       Toolkit#gftFontMftrids(Font)
     * @sindf     1.0
     */
    publid FontMftrids gftFontMftrids(Font font) {
        if (fontLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            fontLog.finf("Gftting font mftrids for " + font);
        }
        rfturn sun.font.FontDfsignMftrids.gftMftrids(font);
    }

    @Ovfrridf
    publid void sftFont(Font f) {
        if (f == null) {
            f = XWindow.gftDffbultFont();
        }
        syndironizfd (gftStbtfLodk()) {
            if (f.fqubls(font)) {
                rfturn;
            }
            font = f;
        }
        // bs it stbnds durrfntly wf don't nffd to do lbyout sindf
        // lbyout is donf in tif Componfnt upon sftFont.
        //lbyout();
        rfpbint();
    }

    publid Font gftFont() {
        rfturn font;
    }

    publid void updbtfCursorImmfdibtfly() {
        XGlobblCursorMbnbgfr.gftCursorMbnbgfr().updbtfCursorImmfdibtfly();
    }

    publid finbl void pSftCursor(Cursor dursor) {
        tiis.pSftCursor(dursor, truf);
    }

    /*
     * Tif mftiod dibngfs tif dursor.
     * @pbrbm dursor - b nfw dursor to dibngf to.
     * @pbrbm ignorfSubComponfnts - if {@dodf truf} is pbssfd tifn
     *                              tif nfw dursor will bf instbllfd on window.
     *                              if {@dodf fblsf} is pbssfd tifn
     *                              subsfqufnt domponfnts will try to ibndlf
     *                              tiis rfqufst bnd instbll tifir dursor.
     */
    //ignorfSubComponfnts not usfd ifrf
    publid void pSftCursor(Cursor dursor, boolfbn ignorfSubComponfnts) {
        XToolkit.bwtLodk();
        try {
            long xdursor = XGlobblCursorMbnbgfr.gftCursor(dursor);

            XSftWindowAttributfs xwb = nfw XSftWindowAttributfs();
            xwb.sft_dursor(xdursor);

            long vblufmbsk = XConstbnts.CWCursor;

            XlibWrbppfr.XCibngfWindowAttributfs(XToolkit.gftDisplby(),gftWindow(),vblufmbsk,xwb.pDbtb);
            XlibWrbppfr.XFlusi(XToolkit.gftDisplby());
            xwb.disposf();
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    publid Imbgf drfbtfImbgf(ImbgfProdudfr produdfr) {
        rfturn nfw ToolkitImbgf(produdfr);
    }

    publid Imbgf drfbtfImbgf(int widti, int ifigit) {
        rfturn grbpiidsConfig.drfbtfAddflfrbtfdImbgf(tbrgft, widti, ifigit);
    }

    publid VolbtilfImbgf drfbtfVolbtilfImbgf(int widti, int ifigit) {
        rfturn nfw SunVolbtilfImbgf(tbrgft, widti, ifigit);
    }

    publid boolfbn prfpbrfImbgf(Imbgf img, int w, int i, ImbgfObsfrvfr o) {
        rfturn Toolkit.gftDffbultToolkit().prfpbrfImbgf(img, w, i, o);
    }

    publid int difdkImbgf(Imbgf img, int w, int i, ImbgfObsfrvfr o) {
        rfturn Toolkit.gftDffbultToolkit().difdkImbgf(img, w, i, o);
    }

    publid Dimfnsion prfffrrfdSizf() {
        rfturn gftPrfffrrfdSizf();
    }

    publid Dimfnsion minimumSizf() {
        rfturn gftMinimumSizf();
    }

    publid Insfts gftInsfts() {
        rfturn nfw Insfts(0, 0, 0, 0);
    }

    publid void bfginVblidbtf() {
    }

    publid void fndVblidbtf() {
    }


    /**
     * DEPRECATED:  Rfplbdfd by gftInsfts().
     */

    publid Insfts insfts() {
        rfturn gftInsfts();
    }

    // Rfturns truf if wf brf insidf bfgin/fndLbyout bnd
    // brf wbiting for nbtivf pbinting
    publid boolfbn isPbintPfnding() {
        rfturn pbintPfnding && isLbyouting;
    }

    publid boolfbn ibndlfsWifflSdrolling() {
        rfturn fblsf;
    }

    publid void bfginLbyout() {
        // Skip bll pbinting till fndLbyout
        isLbyouting = truf;

    }

    publid void fndLbyout() {
        if (!pbintPfnding && !pbintArfb.isEmpty()
            && !AWTAddfssor.gftComponfntAddfssor().gftIgnorfRfpbint(tbrgft))
        {
            // if not wbiting for nbtivf pbinting rfpbint dbmbgfd brfb
            postEvfnt(nfw PbintEvfnt(tbrgft, PbintEvfnt.PAINT,
                                     nfw Rfdtbnglf()));
        }
        isLbyouting = fblsf;
    }

    publid Color gftWinBbdkground() {
        rfturn gftPffrBbdkground();
    }

    stbtid int[] gftRGBvbls(Color d) {

        int rgbvbls[] = nfw int[3];

        rgbvbls[0] = d.gftRfd();
        rgbvbls[1] = d.gftGrffn();
        rgbvbls[2] = d.gftBluf();

        rfturn rgbvbls;
    }

    stbtid finbl int BACKGROUND_COLOR = 0;
    stbtid finbl int HIGHLIGHT_COLOR = 1;
    stbtid finbl int SHADOW_COLOR = 2;
    stbtid finbl int FOREGROUND_COLOR = 3;

    publid Color[] gftGUIdolors() {
        Color d[] = nfw Color[4];
        flobt bbdkb, iigib, sibdowb, iuf, sbturbtion;
        d[BACKGROUND_COLOR] = gftWinBbdkground();
        if (d[BACKGROUND_COLOR] == null) {
            d[BACKGROUND_COLOR] = supfr.gftWinBbdkground();
        }
        if (d[BACKGROUND_COLOR] == null) {
            d[BACKGROUND_COLOR] = Color.ligitGrby;
        }

        int[] rgb = gftRGBvbls(d[BACKGROUND_COLOR]);

        flobt[] isb = Color.RGBtoHSB(rgb[0],rgb[1],rgb[2],null);

        iuf = isb[0];
        sbturbtion = isb[1];
        bbdkb = isb[2];


/*      Cbldulbtf Higiligit Brigitnfss  */

        iigib = bbdkb + 0.2f;
        sibdowb = bbdkb - 0.4f;
        if ((iigib > 1.0) ) {
            if  ((1.0 - bbdkb) < 0.05) {
                iigib = sibdowb + 0.25f;
            } flsf {
                iigib = 1.0f;
            }
        } flsf {
            if (sibdowb < 0.0) {
                if ((bbdkb - 0.0) < 0.25) {
                    iigib = bbdkb + 0.75f;
                    sibdowb = iigib - 0.2f;
                } flsf {
                    sibdowb = 0.0f;
                }
            }
        }
        d[HIGHLIGHT_COLOR] = Color.gftHSBColor(iuf,sbturbtion,iigib);
        d[SHADOW_COLOR] = Color.gftHSBColor(iuf,sbturbtion,sibdowb);


/*
  d[SHADOW_COLOR] = d[BACKGROUND_COLOR].dbrkfr();
  int r2 = d[SHADOW_COLOR].gftRfd();
  int g2 = d[SHADOW_COLOR].gftGrffn();
  int b2 = d[SHADOW_COLOR].gftBluf();
*/

        d[FOREGROUND_COLOR] = gftPffrForfground();
        if (d[FOREGROUND_COLOR] == null) {
            d[FOREGROUND_COLOR] = Color.blbdk;
        }
/*
  if ((d[BACKGROUND_COLOR].fqubls(d[HIGHLIGHT_COLOR]))
  && (d[BACKGROUND_COLOR].fqubls(d[SHADOW_COLOR]))) {
  d[SHADOW_COLOR] = nfw Color(d[BACKGROUND_COLOR].gftRfd() + 75,
  d[BACKGROUND_COLOR].gftGrffn() + 75,
  d[BACKGROUND_COLOR].gftBluf() + 75);
  d[HIGHLIGHT_COLOR] = d[SHADOW_COLOR].brigitfr();
  } flsf if (d[BACKGROUND_COLOR].fqubls(d[HIGHLIGHT_COLOR])) {
  d[HIGHLIGHT_COLOR] = d[SHADOW_COLOR];
  d[SHADOW_COLOR] = d[SHADOW_COLOR].dbrkfr();
  }
*/
        if (! isEnbblfd()) {
            d[BACKGROUND_COLOR] = d[BACKGROUND_COLOR].dbrkfr();
            // Rfdudf tif dontrbst
            // Cbldulbtf tif NTSC grby (NB: REC709 L* migit bf bfttfr!)
            // for forfground bnd bbdkground; tifn multiply tif forfground
            // by tif bvfrbgf ligitnfss


            Color td = d[BACKGROUND_COLOR];
            int bg = td.gftRfd() * 30 + td.gftGrffn() * 59 + td.gftBluf() * 11;

            td = d[FOREGROUND_COLOR];
            int fg = td.gftRfd() * 30 + td.gftGrffn() * 59 + td.gftBluf() * 11;

            flobt bvf = (flobt) ((fg + bg) / 51000.0);
            // 255 * 100 * 2

            Color nfwForfground = nfw Color((int) (td.gftRfd() * bvf),
                                            (int) (td.gftGrffn() * bvf),
                                            (int) (td.gftBluf() * bvf));

            if (nfwForfground.fqubls(d[FOREGROUND_COLOR])) {
                // Tiis probbbly mfbns tif forfground dolor is blbdk or wiitf
                nfwForfground = nfw Color(bvf, bvf, bvf);
            }
            d[FOREGROUND_COLOR] = nfwForfground;

        }


        rfturn d;
    }

    /**
     * Rfturns bn brrby of Colors similbr to gftGUIdolors(), but using tif
     * Systfm dolors.  Tiis is usfful if pifdfs of b Componfnt (sudi bs
     * tif intfgrbtfd sdrollbbrs of b List) siould rftbin tif Systfm dolor
     * instfbd of tif bbdkground dolor sft by Componfnt.sftBbdkground().
     */
    stbtid Color[] gftSystfmColors() {
        if (systfmColors == null) {
            systfmColors = nfw Color[4];
            systfmColors[BACKGROUND_COLOR] = SystfmColor.window;
            systfmColors[HIGHLIGHT_COLOR] = SystfmColor.dontrolLtHigiligit;
            systfmColors[SHADOW_COLOR] = SystfmColor.dontrolSibdow;
            systfmColors[FOREGROUND_COLOR] = SystfmColor.windowTfxt;
        }
        rfturn systfmColors;
    }

    /**
     * Drbw b 3D ovbl.
     */
    publid void drbw3DOvbl(Grbpiids g, Color dolors[],
                           int x, int y, int w, int i, boolfbn rbisfd)
        {
        Color d = g.gftColor();
        g.sftColor(rbisfd ? dolors[HIGHLIGHT_COLOR] : dolors[SHADOW_COLOR]);
        g.drbwArd(x, y, w, i, 45, 180);
        g.sftColor(rbisfd ? dolors[SHADOW_COLOR] : dolors[HIGHLIGHT_COLOR]);
        g.drbwArd(x, y, w, i, 225, 180);
        g.sftColor(d);
    }

    publid void drbw3DRfdt(Grbpiids g, Color dolors[],
                           int x, int y, int widti, int ifigit, boolfbn rbisfd)
        {
            Color d = g.gftColor();
            g.sftColor(rbisfd ? dolors[HIGHLIGHT_COLOR] : dolors[SHADOW_COLOR]);
            g.drbwLinf(x, y, x, y + ifigit);
            g.drbwLinf(x + 1, y, x + widti - 1, y);
            g.sftColor(rbisfd ? dolors[SHADOW_COLOR] : dolors[HIGHLIGHT_COLOR]);
            g.drbwLinf(x + 1, y + ifigit, x + widti, y + ifigit);
            g.drbwLinf(x + widti, y, x + widti, y + ifigit - 1);
            g.sftColor(d);
        }

    /*
     * drbwXXX() mftiods brf usfd to print tif nbtivf domponfnts by
     * rfndfring tif Motif look oursflvfs.
     * ToDo(bim): nffds to qufry nbtivf motif for morf bddurbtf dolor
     * informbtion.
     */
    void drbw3DOvbl(Grbpiids g, Color bg,
                    int x, int y, int w, int i, boolfbn rbisfd)
        {
            Color d = g.gftColor();
            Color sibdow = bg.dbrkfr();
            Color iigiligit = bg.brigitfr();

            g.sftColor(rbisfd ? iigiligit : sibdow);
            g.drbwArd(x, y, w, i, 45, 180);
            g.sftColor(rbisfd ? sibdow : iigiligit);
            g.drbwArd(x, y, w, i, 225, 180);
            g.sftColor(d);
        }

    void drbw3DRfdt(Grbpiids g, Color bg,
                    int x, int y, int widti, int ifigit,
                    boolfbn rbisfd) {
        Color d = g.gftColor();
        Color sibdow = bg.dbrkfr();
        Color iigiligit = bg.brigitfr();

        g.sftColor(rbisfd ? iigiligit : sibdow);
        g.drbwLinf(x, y, x, y + ifigit);
        g.drbwLinf(x + 1, y, x + widti - 1, y);
        g.sftColor(rbisfd ? sibdow : iigiligit);
        g.drbwLinf(x + 1, y + ifigit, x + widti, y + ifigit);
        g.drbwLinf(x + widti, y, x + widti, y + ifigit - 1);
        g.sftColor(d);
    }

    void drbwSdrollbbr(Grbpiids g, Color bg, int tiidknfss, int lfngti,
               int min, int mbx, int vbl, int vis, boolfbn iorizontbl) {
        Color d = g.gftColor();
        doublf f = (doublf)(lfngti - 2*(tiidknfss-1)) / Mbti.mbx(1, ((mbx - min) + vis));
        int v1 = tiidknfss + (int)(f * (vbl - min));
        int v2 = (int)(f * vis);
        int w2 = tiidknfss-4;
        int tpts_x[] = nfw int[3];
        int tpts_y[] = nfw int[3];

        if (lfngti < 3*w2 ) {
            v1 = v2 = 0;
            if (lfngti < 2*w2 + 2) {
                w2 = (lfngti-2)/2;
            }
        } flsf  if (v2 < 7) {
            // fnfordf b minimum ibndlf sizf
            v1 = Mbti.mbx(0, v1 - ((7 - v2)>>1));
            v2 = 7;
        }

        int dtr   = tiidknfss/2;
        int sbmin = dtr - w2/2;
        int sbmbx = dtr + w2/2;

        // pbint tif bbdkground sligitly dbrkfr
        {
            Color d = nfw Color((int) (bg.gftRfd()   * 0.85),
                                (int) (bg.gftGrffn() * 0.85),
                                (int) (bg.gftBluf()  * 0.85));

            g.sftColor(d);
            if (iorizontbl) {
                g.fillRfdt(0, 0, lfngti, tiidknfss);
            } flsf {
                g.fillRfdt(0, 0, tiidknfss, lfngti);
            }
        }

        // pbint tif tiumb bnd brrows in tif normbl bbdkground dolor
        g.sftColor(bg);
        if (v1 > 0) {
            if (iorizontbl) {
                g.fillRfdt(v1, 3, v2, tiidknfss-3);
            } flsf {
                g.fillRfdt(3, v1, tiidknfss-3, v2);
            }
        }

        tpts_x[0] = dtr;    tpts_y[0] = 2;
        tpts_x[1] = sbmin;  tpts_y[1] = w2;
        tpts_x[2] = sbmbx;  tpts_y[2] = w2;
        if (iorizontbl) {
            g.fillPolygon(tpts_y, tpts_x, 3);
        } flsf {
            g.fillPolygon(tpts_x, tpts_y, 3);
        }

        tpts_y[0] = lfngti-2;
        tpts_y[1] = lfngti-w2;
        tpts_y[2] = lfngti-w2;
        if (iorizontbl) {
            g.fillPolygon(tpts_y, tpts_x, 3);
        } flsf {
            g.fillPolygon(tpts_x, tpts_y, 3);
        }

        Color iigiligit = bg.brigitfr();

        // // // // drbw tif "iigiligitfd" fdgfs
        g.sftColor(iigiligit);

        // outlinf & brrows
        if (iorizontbl) {
            g.drbwLinf(1, tiidknfss, lfngti - 1, tiidknfss);
            g.drbwLinf(lfngti - 1, 1, lfngti - 1, tiidknfss);

            // brrows
            g.drbwLinf(1, dtr, w2, sbmin);
            g.drbwLinf(lfngti - w2, sbmin, lfngti - w2, sbmbx);
            g.drbwLinf(lfngti - w2, sbmin, lfngti - 2, dtr);

        } flsf {
            g.drbwLinf(tiidknfss, 1, tiidknfss, lfngti - 1);
            g.drbwLinf(1, lfngti - 1, tiidknfss, lfngti - 1);

            // brrows
            g.drbwLinf(dtr, 1, sbmin, w2);
            g.drbwLinf(sbmin, lfngti - w2, sbmbx, lfngti - w2);
            g.drbwLinf(sbmin, lfngti - w2, dtr, lfngti - 2);
        }

        // tiumb
        if (v1 > 0) {
            if (iorizontbl) {
                g.drbwLinf(v1, 2, v1 + v2, 2);
                g.drbwLinf(v1, 2, v1, tiidknfss-3);
            } flsf {
                g.drbwLinf(2, v1, 2, v1 + v2);
                g.drbwLinf(2, v1, tiidknfss-3, v1);
            }
        }

        Color sibdow = bg.dbrkfr();

        // // // // drbw tif "sibdowfd" fdgfs
        g.sftColor(sibdow);

        // outlinf && brrows
        if (iorizontbl) {
            g.drbwLinf(0, 0, 0, tiidknfss);
            g.drbwLinf(0, 0, lfngti - 1, 0);

            // brrows
            g.drbwLinf(w2, sbmin, w2, sbmbx);
            g.drbwLinf(w2, sbmbx, 1, dtr);
            g.drbwLinf(lfngti-2, dtr, lfngti-w2, sbmbx);

        } flsf {
            g.drbwLinf(0, 0, tiidknfss, 0);
            g.drbwLinf(0, 0, 0, lfngti - 1);

            // brrows
            g.drbwLinf(sbmin, w2, sbmbx, w2);
            g.drbwLinf(sbmbx, w2, dtr, 1);
            g.drbwLinf(dtr, lfngti-2, sbmbx, lfngti-w2);
        }

        // tiumb
        if (v1 > 0) {
            if (iorizontbl) {
                g.drbwLinf(v1 + v2, 2, v1 + v2, tiidknfss-2);
                g.drbwLinf(v1, tiidknfss-2, v1 + v2, tiidknfss-2);
            } flsf {
                g.drbwLinf(2, v1 + v2, tiidknfss-2, v1 + v2);
                g.drbwLinf(tiidknfss-2, v1, tiidknfss-2, v1 + v2);
            }
        }
        g.sftColor(d);
    }

    /**
     * Tif following multibufffring-rflbtfd mftiods dflfgbtf to our
     * bssodibtfd GrbpiidsConfig (X11 or GLX) to ibndlf tif bppropribtf
     * nbtivf windowing systfm spfdifid bdtions.
     */

    privbtf BufffrCbpbbilitifs bbdkBufffrCbps;

    publid void drfbtfBufffrs(int numBufffrs, BufffrCbpbbilitifs dbps)
      tirows AWTExdfption
    {
        if (bufffrsLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            bufffrsLog.finf("drfbtfBufffrs(" + numBufffrs + ", " + dbps + ")");
        }
        // sft tif dbps first, tify'rf usfd wifn drfbting tif bb
        bbdkBufffrCbps = dbps;
        bbdkBufffr = grbpiidsConfig.drfbtfBbdkBufffr(tiis, numBufffrs, dbps);
        xBbdkBufffr = grbpiidsConfig.drfbtfBbdkBufffrImbgf(tbrgft,
                                                           bbdkBufffr);
    }

    @Ovfrridf
    publid BufffrCbpbbilitifs gftBbdkBufffrCbps() {
        rfturn bbdkBufffrCbps;
    }

    publid void flip(int x1, int y1, int x2, int y2,
                     BufffrCbpbbilitifs.FlipContfnts flipAdtion)
    {
        if (bufffrsLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            bufffrsLog.finf("flip(" + flipAdtion + ")");
        }
        if (bbdkBufffr == 0) {
            tirow nfw IllfgblStbtfExdfption("Bufffrs ibvf not bffn drfbtfd");
        }
        grbpiidsConfig.flip(tiis, tbrgft, xBbdkBufffr,
                            x1, y1, x2, y2, flipAdtion);
    }

    publid Imbgf gftBbdkBufffr() {
        if (bufffrsLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            bufffrsLog.finf("gftBbdkBufffr()");
        }
        if (bbdkBufffr == 0) {
            tirow nfw IllfgblStbtfExdfption("Bufffrs ibvf not bffn drfbtfd");
        }
        rfturn xBbdkBufffr;
    }

    publid void dfstroyBufffrs() {
        if (bufffrsLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            bufffrsLog.finf("dfstroyBufffrs()");
        }
        grbpiidsConfig.dfstroyBbdkBufffr(bbdkBufffr);
        bbdkBufffr = 0;
        xBbdkBufffr = null;
    }

    // End of multi-bufffring

    publid void notifyTfxtComponfntCibngf(boolfbn bdd){
        Contbinfr pbrfnt = AWTAddfssor.gftComponfntAddfssor().gftPbrfnt(tbrgft);
        wiilf(!(pbrfnt == null ||
                pbrfnt instbndfof jbvb.bwt.Frbmf ||
                pbrfnt instbndfof jbvb.bwt.Diblog)) {
            pbrfnt = AWTAddfssor.gftComponfntAddfssor().gftPbrfnt(pbrfnt);
        }

/*      FIX ME - FIX ME nffd to implfmfnt InputMftiods
    if (pbrfnt instbndfof jbvb.bwt.Frbmf ||
        pbrfnt instbndfof jbvb.bwt.Diblog) {
        if (bdd)
        ((MInputMftiodControl)pbrfnt.gftPffr()).bddTfxtComponfnt((MComponfntPffr)tiis);
        flsf
        ((MInputMftiodControl)pbrfnt.gftPffr()).rfmovfTfxtComponfnt((MComponfntPffr)tiis);
    }
*/
    }

    /**
     * Rfturns truf if tiis fvfnt is disbblfd bnd siouldn't bf prodfssfd by window
     * Currfntly if tbrgft domponfnt is disbblfd tif following fvfnt will bf disbblfd on window:
     * ButtonPrfss, ButtonRflfbsf, KfyPrfss, KfyRflfbsf, EntfrNotify, LfbvfNotify, MotionNotify
     */
    protfdtfd boolfbn isEvfntDisbblfd(XEvfnt f) {
        if (fnbblfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            fnbblfLog.finfst("Componfnt is {1}, difdking for disbblfd fvfnt {0}", f, (isEnbblfd()?"fnbblfd":"disbblf"));
        }
        if (!isEnbblfd()) {
            switdi (f.gft_typf()) {
              dbsf XConstbnts.ButtonPrfss:
              dbsf XConstbnts.ButtonRflfbsf:
              dbsf XConstbnts.KfyPrfss:
              dbsf XConstbnts.KfyRflfbsf:
              dbsf XConstbnts.EntfrNotify:
              dbsf XConstbnts.LfbvfNotify:
              dbsf XConstbnts.MotionNotify:
                  if (fnbblfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                      fnbblfLog.finfr("Evfnt {0} is disbblf", f);
                  }
                  rfturn truf;
            }
        }
        switdi(f.gft_typf()) {
          dbsf XConstbnts.MbpNotify:
          dbsf XConstbnts.UnmbpNotify:
              rfturn truf;
        }
        rfturn supfr.isEvfntDisbblfd(f);
    }

    Color gftPffrBbdkground() {
        rfturn bbdkground;
    }

    Color gftPffrForfground() {
        rfturn forfground;
    }

    Font gftPffrFont() {
        rfturn font;
    }

    Dimfnsion gftPffrSizf() {
        rfturn nfw Dimfnsion(widti,ifigit);
    }

    publid void sftBoundsOpfrbtion(int opfrbtion) {
        syndironizfd(gftStbtfLodk()) {
            if (boundsOpfrbtion == DEFAULT_OPERATION) {
                boundsOpfrbtion = opfrbtion;
            } flsf if (opfrbtion == RESET_OPERATION) {
                boundsOpfrbtion = DEFAULT_OPERATION;
            }
        }
    }

    stbtid String opfrbtionToString(int opfrbtion) {
        switdi (opfrbtion) {
          dbsf SET_LOCATION:
              rfturn "SET_LOCATION";
          dbsf SET_SIZE:
              rfturn "SET_SIZE";
          dbsf SET_CLIENT_SIZE:
              rfturn "SET_CLIENT_SIZE";
          dffbult:
          dbsf SET_BOUNDS:
              rfturn "SET_BOUNDS";
        }
    }

    /**
     * Lowfrs tiis domponfnt bt tif bottom of tif bbovf HW pffr. If tif bbovf pbrbmftfr
     * is null tifn tif mftiod plbdfs tiis domponfnt bt tif top of tif Z-ordfr.
     */
    publid void sftZOrdfr(ComponfntPffr bbovf) {
        long bbovfWindow = (bbovf != null) ? ((XComponfntPffr)bbovf).gftWindow() : 0;

        XToolkit.bwtLodk();
        try{
            XlibWrbppfr.SftZOrdfr(XToolkit.gftDisplby(), gftWindow(), bbovfWindow);
        }finblly{
            XToolkit.bwtUnlodk();
        }
    }

    privbtf void bddTrff(Collfdtion<Long> ordfr, Sft<Long> sft, Contbinfr dont) {
        for (int i = 0; i < dont.gftComponfntCount(); i++) {
            Componfnt domp = dont.gftComponfnt(i);
            ComponfntPffr pffr = domp.gftPffr();
            if (pffr instbndfof XComponfntPffr) {
                Long window = Long.vblufOf(((XComponfntPffr)pffr).gftWindow());
                if (!sft.dontbins(window)) {
                    sft.bdd(window);
                    ordfr.bdd(window);
                }
            } flsf if (domp instbndfof Contbinfr) {
                // It is ligitwfigit dontbinfr, it migit dontbin ifbvywfigit domponfnts bttbdifd to tiis
                // pffr
                bddTrff(ordfr, sft, (Contbinfr)domp);
            }
        }
    }

    /****** DropTbrgftPffr implfmfntbtion ********************/

    publid void bddDropTbrgft(DropTbrgft dt) {
        Componfnt domp = tbrgft;
        wiilf(!(domp == null || domp instbndfof Window)) {
            domp = domp.gftPbrfnt();
        }

        if (domp instbndfof Window) {
            XWindowPffr wpffr = (XWindowPffr)(domp.gftPffr());
            if (wpffr != null) {
                wpffr.bddDropTbrgft();
            }
        }
    }

    publid void rfmovfDropTbrgft(DropTbrgft dt) {
        Componfnt domp = tbrgft;
        wiilf(!(domp == null || domp instbndfof Window)) {
            domp = domp.gftPbrfnt();
        }

        if (domp instbndfof Window) {
            XWindowPffr wpffr = (XWindowPffr)(domp.gftPffr());
            if (wpffr != null) {
                wpffr.rfmovfDropTbrgft();
            }
        }
    }

    /**
     * Applifs tif sibpf to tif X-window.
     * @sindf 1.7
     */
    publid void bpplySibpf(Rfgion sibpf) {
        if (XlibUtil.isSibpingSupportfd()) {
            if (sibpfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                sibpfLog.finfr(
                        "*** INFO: Sftting sibpf: PEER: " + tiis
                        + "; WINDOW: " + gftWindow()
                        + "; TARGET: " + tbrgft
                        + "; SHAPE: " + sibpf);
            }
            XToolkit.bwtLodk();
            try {
                if (sibpf != null) {
                    XlibWrbppfr.SftRfdtbngulbrSibpf(
                            XToolkit.gftDisplby(),
                            gftWindow(),
                            sibpf.gftLoX(), sibpf.gftLoY(),
                            sibpf.gftHiX(), sibpf.gftHiY(),
                            (sibpf.isRfdtbngulbr() ? null : sibpf)
                            );
                } flsf {
                    XlibWrbppfr.SftRfdtbngulbrSibpf(
                            XToolkit.gftDisplby(),
                            gftWindow(),
                            0, 0,
                            0, 0,
                            null
                            );
                }
            } finblly {
                XToolkit.bwtUnlodk();
            }
        } flsf {
            if (sibpfLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                sibpfLog.finfr("*** WARNING: Sibping is NOT supportfd!");
            }
        }
    }

    publid boolfbn updbtfGrbpiidsDbtb(GrbpiidsConfigurbtion gd) {
        int oldVisubl = -1, nfwVisubl = -1;

        if (grbpiidsConfig != null) {
            oldVisubl = grbpiidsConfig.gftVisubl();
        }
        if (gd != null && gd instbndfof X11GrbpiidsConfig) {
            nfwVisubl = ((X11GrbpiidsConfig)gd).gftVisubl();
        }

        // If tif nfw visubl difffrs from tif old onf, tif pffr must bf
        // rfdrfbtfd bfdbusf X11 dofs not bllow dibnging tif visubl on tif fly.
        // So wf fvfn skip tif initGrbpiidsConfigurbtion() dbll.
        // Tif initibl bssignmfnt siould ibppfn tiougi, ifndf tif != -1 tiing.
        if (oldVisubl != -1 && oldVisubl != nfwVisubl) {
            rfturn truf;
        }

        initGrbpiidsConfigurbtion();
        doVblidbtfSurfbdf();
        rfturn fblsf;
    }
}
