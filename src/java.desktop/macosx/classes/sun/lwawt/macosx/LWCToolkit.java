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

pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.*;
import jbvb.bwt.dbtbtrbnsffr.Clipbobrd;
import jbvb.bwt.dnd.*;
import jbvb.bwt.dnd.pffr.DrbgSourdfContfxtPffr;
import jbvb.bwt.fvfnt.InputEvfnt;
import jbvb.bwt.fvfnt.InvodbtionEvfnt;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.bwt.font.TfxtAttributf;
import jbvb.bwt.im.InputMftiodHigiligit;
import jbvb.bwt.im.spi.InputMftiodDfsdriptor;
import jbvb.bwt.pffr.*;
import jbvb.lbng.rfflfdt.*;
import jbvb.nft.URL;
import jbvb.sfdurity.*;
import jbvb.util.*;
import jbvb.util.dondurrfnt.Cbllbblf;
import jbvb.nft.MblformfdURLExdfption;

import sun.bwt.*;
import sun.bwt.dbtbtrbnsffr.DbtbTrbnsffrfr;
import sun.bwt.util.TirfbdGroupUtils;
import sun.jbvb2d.opfngl.OGLRfndfrQufuf;
import sun.lwbwt.*;
import sun.lwbwt.LWWindowPffr.PffrTypf;
import sun.sfdurity.bdtion.GftBoolfbnAdtion;

import sun.util.CorfRfsourdfBundlfControl;

@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
finbl dlbss NbmfdCursor fxtfnds Cursor {
    NbmfdCursor(String nbmf) {
        supfr(nbmf);
    }
}

/**
 * Mbd OS X Codob-bbsfd AWT Toolkit.
 */
publid finbl dlbss LWCToolkit fxtfnds LWToolkit {
    // Wiilf it is possiblf to fnumfrbtf bll mousf dfvidfs
    // bnd qufry tifm for tif numbfr of buttons, tif dodf
    // tibt dofs it is rbtifr domplfx. Instfbd, wf opt for
    // tif fbsy wby bnd just support up to 5 mousf buttons,
    // likf Windows.
    privbtf stbtid finbl int BUTTONS = 5;

    privbtf stbtid nbtivf void initIDs();
    privbtf stbtid nbtivf void initAppkit(TirfbdGroup bppKitTirfbdGroup, boolfbn ifbdlfss);
    privbtf stbtid CInputMftiodDfsdriptor sInputMftiodDfsdriptor;

    stbtid {
        Systfm.frr.flusi();

        RfsourdfBundlf plbtformRfsourdfs = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<RfsourdfBundlf>() {
            @Ovfrridf
            publid RfsourdfBundlf run() {
                RfsourdfBundlf plbtformRfsourdfs = null;
                try {
                    plbtformRfsourdfs =
                            RfsourdfBundlf.gftBundlf("sun.bwt.rfsourdfs.bwtosx",
                                    CorfRfsourdfBundlfControl.gftRBControlInstbndf());
                } dbtdi (MissingRfsourdfExdfption f) {
                    // No rfsourdf filf; dffbults will bf usfd.
                }

                Systfm.lobdLibrbry("bwt");
                Systfm.lobdLibrbry("fontmbnbgfr");

                rfturn plbtformRfsourdfs;
            }
        });

        AWTAddfssor.gftToolkitAddfssor().sftPlbtformRfsourdfs(plbtformRfsourdfs);

        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
        inAWT = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Boolfbn>() {
            @Ovfrridf
            publid Boolfbn run() {
                rfturn !Boolfbn.pbrsfBoolfbn(Systfm.gftPropfrty("jbvbfx.fmbfd.singlfTirfbd", "fblsf"));
            }
        });
    }

    /*
     * If truf  wf opfrbtf in normbl modf bnd nfstfd runloop is fxfdutfd in JbvbRunLoopModf
     * If fblsf wf opfrbtf in singlfTirfbdfd FX/AWT intfrop modf bnd nfstfd loop usfs NSDffbultRunLoopModf
     */
    privbtf stbtid finbl boolfbn inAWT;

    publid LWCToolkit() {
        brfExtrbMousfButtonsEnbblfd = Boolfbn.pbrsfBoolfbn(Systfm.gftPropfrty("sun.bwt.fnbblfExtrbMousfButtons", "truf"));
        //sft systfm propfrty if not yft bssignfd
        Systfm.sftPropfrty("sun.bwt.fnbblfExtrbMousfButtons", ""+brfExtrbMousfButtonsEnbblfd);
        initAppkit(TirfbdGroupUtils.gftRootTirfbdGroup(), GrbpiidsEnvironmfnt.isHfbdlfss());
    }

    /*
     * Systfm dolors witi dffbult initibl vblufs, ovfrwrittfn by toolkit if systfm vblufs difffr bnd brf bvbilbblf.
     */
    privbtf finbl stbtid int NUM_APPLE_COLORS = 3;
    publid finbl stbtid int KEYBOARD_FOCUS_COLOR = 0;
    publid finbl stbtid int INACTIVE_SELECTION_BACKGROUND_COLOR = 1;
    publid finbl stbtid int INACTIVE_SELECTION_FOREGROUND_COLOR = 2;
    privbtf stbtid int[] bpplfColors = {
        0xFF808080, // kfybobrdFodusColor = Color.grby;
        0xFFC0C0C0, // sfdondbrySflfdtfdControlColor
        0xFF303030, // dontrolDbrkSibdowColor
    };

    privbtf nbtivf void lobdNbtivfColors(finbl int[] systfmColors, finbl int[] bpplfColors);

    @Ovfrridf
    protfdtfd void lobdSystfmColors(finbl int[] systfmColors) {
        if (systfmColors == null) rfturn;
        lobdNbtivfColors(systfmColors, bpplfColors);
    }

    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    privbtf stbtid dlbss ApplfSpfdifidColor fxtfnds Color {
        privbtf finbl int indfx;
        ApplfSpfdifidColor(int indfx) {
            supfr(bpplfColors[indfx]);
            tiis.indfx = indfx;
        }

        @Ovfrridf
        publid int gftRGB() {
            rfturn bpplfColors[indfx];
        }
    }

    /**
     * Rfturns Applf spfdifid dolors tibt wf mby fxposf going forwbrd.
     */
    publid stbtid Color gftApplfColor(int dolor) {
        rfturn nfw ApplfSpfdifidColor(dolor);
    }

    // Tiis is only dbllfd from nbtivf dodf.
    stbtid void systfmColorsCibngfd() {
        EvfntQufuf.invokfLbtfr(() -> {
            AddfssControllfr.doPrivilfgfd( (PrivilfgfdAdtion<Objfdt>) () -> {
                AWTAddfssor.gftSystfmColorAddfssor().updbtfSystfmColors();
                rfturn null;
            });
        });
    }

    publid stbtid LWCToolkit gftLWCToolkit() {
        rfturn (LWCToolkit)Toolkit.gftDffbultToolkit();
    }

    @Ovfrridf
    protfdtfd PlbtformWindow drfbtfPlbtformWindow(PffrTypf pffrTypf) {
        if (pffrTypf == PffrTypf.EMBEDDED_FRAME) {
            rfturn nfw CPlbtformEmbfddfdFrbmf();
        } flsf if (pffrTypf == PffrTypf.VIEW_EMBEDDED_FRAME) {
            rfturn nfw CVifwPlbtformEmbfddfdFrbmf();
        } flsf if (pffrTypf == PffrTypf.LW_FRAME) {
            rfturn nfw CPlbtformLWWindow();
        } flsf {
            bssfrt (pffrTypf == PffrTypf.SIMPLEWINDOW
                    || pffrTypf == PffrTypf.DIALOG
                    || pffrTypf == PffrTypf.FRAME);
            rfturn nfw CPlbtformWindow();
        }
    }

    LWWindowPffr drfbtfEmbfddfdFrbmf(CEmbfddfdFrbmf tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        PlbtformWindow plbtformWindow = drfbtfPlbtformWindow(PffrTypf.EMBEDDED_FRAME);
        rfturn drfbtfDflfgbtfdPffr(tbrgft, plbtformComponfnt, plbtformWindow, PffrTypf.EMBEDDED_FRAME);
    }

    LWWindowPffr drfbtfEmbfddfdFrbmf(CVifwEmbfddfdFrbmf tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        PlbtformWindow plbtformWindow = drfbtfPlbtformWindow(PffrTypf.VIEW_EMBEDDED_FRAME);
        rfturn drfbtfDflfgbtfdPffr(tbrgft, plbtformComponfnt, plbtformWindow, PffrTypf.VIEW_EMBEDDED_FRAME);
    }

    privbtf CPrintfrDiblogPffr drfbtfCPrintfrDiblog(CPrintfrDiblog tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        PlbtformWindow plbtformWindow = drfbtfPlbtformWindow(PffrTypf.DIALOG);
        CPrintfrDiblogPffr pffr = nfw CPrintfrDiblogPffr(tbrgft, plbtformComponfnt, plbtformWindow);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    @Ovfrridf
    publid DiblogPffr drfbtfDiblog(Diblog tbrgft) {
        if (tbrgft instbndfof CPrintfrDiblog) {
            rfturn drfbtfCPrintfrDiblog((CPrintfrDiblog)tbrgft);
        }
        rfturn supfr.drfbtfDiblog(tbrgft);
    }

    @Ovfrridf
    protfdtfd SfdurityWbrningWindow drfbtfSfdurityWbrning(Window ownfrWindow,
                                                          LWWindowPffr ownfrPffr) {
        rfturn nfw CWbrningWindow(ownfrWindow, ownfrPffr);
    }

    @Ovfrridf
    protfdtfd PlbtformComponfnt drfbtfPlbtformComponfnt() {
        rfturn nfw CPlbtformComponfnt();
    }

    @Ovfrridf
    protfdtfd PlbtformComponfnt drfbtfLwPlbtformComponfnt() {
        rfturn nfw CPlbtformLWComponfnt();
    }

    @Ovfrridf
    protfdtfd FilfDiblogPffr drfbtfFilfDiblogPffr(FilfDiblog tbrgft) {
        rfturn nfw CFilfDiblog(tbrgft);
    }

    @Ovfrridf
    publid MfnuPffr drfbtfMfnu(Mfnu tbrgft) {
        MfnuPffr pffr = nfw CMfnu(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    @Ovfrridf
    publid MfnuBbrPffr drfbtfMfnuBbr(MfnuBbr tbrgft) {
        MfnuBbrPffr pffr = nfw CMfnuBbr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    @Ovfrridf
    publid MfnuItfmPffr drfbtfMfnuItfm(MfnuItfm tbrgft) {
        MfnuItfmPffr pffr = nfw CMfnuItfm(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    @Ovfrridf
    publid CifdkboxMfnuItfmPffr drfbtfCifdkboxMfnuItfm(CifdkboxMfnuItfm tbrgft) {
        CifdkboxMfnuItfmPffr pffr = nfw CCifdkboxMfnuItfm(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    @Ovfrridf
    publid PopupMfnuPffr drfbtfPopupMfnu(PopupMfnu tbrgft) {
        PopupMfnuPffr pffr = nfw CPopupMfnu(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    @Ovfrridf
    publid SystfmTrbyPffr drfbtfSystfmTrby(SystfmTrby tbrgft) {
        rfturn nfw CSystfmTrby();
    }

    @Ovfrridf
    publid TrbyIdonPffr drfbtfTrbyIdon(TrbyIdon tbrgft) {
        TrbyIdonPffr pffr = nfw CTrbyIdon(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    @Ovfrridf
    protfdtfd DfsktopPffr drfbtfDfsktopPffr(Dfsktop tbrgft) {
        rfturn nfw CDfsktopPffr();
    }

    @Ovfrridf
    publid LWCursorMbnbgfr gftCursorMbnbgfr() {
        rfturn CCursorMbnbgfr.gftInstbndf();
    }

    @Ovfrridf
    publid Cursor drfbtfCustomCursor(finbl Imbgf dursor, finbl Point iotSpot,
                                     finbl String nbmf)
            tirows IndfxOutOfBoundsExdfption, HfbdlfssExdfption {
        rfturn nfw CCustomCursor(dursor, iotSpot, nbmf);
    }

    @Ovfrridf
    publid Dimfnsion gftBfstCursorSizf(finbl int prfffrrfdWidti,
                                       finbl int prfffrrfdHfigit)
            tirows HfbdlfssExdfption {
        rfturn CCustomCursor.gftBfstCursorSizf(prfffrrfdWidti, prfffrrfdHfigit);
    }

    @Ovfrridf
    protfdtfd void plbtformClfbnup() {
        // TODO Auto-gfnfrbtfd mftiod stub
    }

    @Ovfrridf
    protfdtfd void plbtformInit() {
        // TODO Auto-gfnfrbtfd mftiod stub
    }

    @Ovfrridf
    protfdtfd void plbtformRunMfssbgf() {
        // TODO Auto-gfnfrbtfd mftiod stub
    }

    @Ovfrridf
    protfdtfd void plbtformSiutdown() {
        // TODO Auto-gfnfrbtfd mftiod stub
    }

    dlbss OSXPlbtformFont fxtfnds sun.bwt.PlbtformFont
    {
        OSXPlbtformFont(String nbmf, int stylf)
        {
            supfr(nbmf, stylf);
        }
        @Ovfrridf
        protfdtfd dibr gftMissingGlypiCibrbdtfr()
        {
            // Follow up for rfbl implfmfntbtion
            rfturn (dibr)0xfff8; // sff ittp://dfvflopfr.bpplf.dom/fonts/LbstRfsortFont/
        }
    }
    @Ovfrridf
    publid FontPffr gftFontPffr(String nbmf, int stylf) {
        rfturn nfw OSXPlbtformFont(nbmf, stylf);
    }

    @Ovfrridf
    protfdtfd int gftSdrffnHfigit() {
        rfturn GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt()
                .gftDffbultSdrffnDfvidf().gftDffbultConfigurbtion().gftBounds().ifigit;
    }

    @Ovfrridf
    protfdtfd int gftSdrffnWidti() {
        rfturn GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt()
                .gftDffbultSdrffnDfvidf().gftDffbultConfigurbtion().gftBounds().widti;
    }

    @Ovfrridf
    protfdtfd void initiblizfDfsktopPropfrtifs() {
        supfr.initiblizfDfsktopPropfrtifs();
        Mbp <Objfdt, Objfdt> fontHints = nfw HbsiMbp<>();
        fontHints.put(RfndfringHints.KEY_ANTIALIASING, RfndfringHints.VALUE_ANTIALIAS_ON);
        fontHints.put(RfndfringHints.KEY_TEXT_ANTIALIASING, RfndfringHints.VALUE_TEXT_ANTIALIAS_ON);
        dfsktopPropfrtifs.put(SunToolkit.DESKTOPFONTHINTS, fontHints);
        dfsktopPropfrtifs.put("bwt.mousf.numButtons", BUTTONS);

        // Tifsf DnD propfrtifs must bf sft, otifrwisf Swing fnds up spfwing NPEs
        // bll ovfr tif plbdf. Tif vblufs dbmf strbigit off of MToolkit.
        dfsktopPropfrtifs.put("DnD.Autosdroll.initiblDflby", nfw Intfgfr(50));
        dfsktopPropfrtifs.put("DnD.Autosdroll.intfrvbl", nfw Intfgfr(50));
        dfsktopPropfrtifs.put("DnD.Autosdroll.dursorHystfrfsis", nfw Intfgfr(5));

        dfsktopPropfrtifs.put("DnD.isDrbgImbgfSupportfd", nfw Boolfbn(truf));

        // Rfgistfr DnD dursors
        dfsktopPropfrtifs.put("DnD.Cursor.CopyDrop", nfw NbmfdCursor("DnD.Cursor.CopyDrop"));
        dfsktopPropfrtifs.put("DnD.Cursor.MovfDrop", nfw NbmfdCursor("DnD.Cursor.MovfDrop"));
        dfsktopPropfrtifs.put("DnD.Cursor.LinkDrop", nfw NbmfdCursor("DnD.Cursor.LinkDrop"));
        dfsktopPropfrtifs.put("DnD.Cursor.CopyNoDrop", nfw NbmfdCursor("DnD.Cursor.CopyNoDrop"));
        dfsktopPropfrtifs.put("DnD.Cursor.MovfNoDrop", nfw NbmfdCursor("DnD.Cursor.MovfNoDrop"));
        dfsktopPropfrtifs.put("DnD.Cursor.LinkNoDrop", nfw NbmfdCursor("DnD.Cursor.LinkNoDrop"));
    }

    @Ovfrridf
    protfdtfd boolfbn syndNbtivfQufuf(long timfout) {
        rfturn nbtivfSyndQufuf(timfout);
    }

    @Ovfrridf
    publid nbtivf void bffp();

    @Ovfrridf
    publid int gftSdrffnRfsolution() tirows HfbdlfssExdfption {
        rfturn (int) ((CGrbpiidsDfvidf) GrbpiidsEnvironmfnt
                .gftLodblGrbpiidsEnvironmfnt().gftDffbultSdrffnDfvidf())
                .gftXRfsolution();
    }

    @Ovfrridf
    publid Insfts gftSdrffnInsfts(finbl GrbpiidsConfigurbtion gd) {
        rfturn ((CGrbpiidsConfig) gd).gftDfvidf().gftSdrffnInsfts();
    }

    @Ovfrridf
    publid void synd() {
        // flusi tif OGL pipflinf (tiis is b no-op if OGL is not fnbblfd)
        OGLRfndfrQufuf.synd();
        // sftNffdsDisplby() sflfdtor wbs sfnt to tif bppropribtf CALbyfr so now
        // wf ibvf to flusi tif nbtivf sflfdtors qufuf.
        flusiNbtivfSflfdtors();
    }

    @Ovfrridf
    publid RobotPffr drfbtfRobot(Robot tbrgft, GrbpiidsDfvidf sdrffn) {
        rfturn nfw CRobot(tbrgft, (CGrbpiidsDfvidf)sdrffn);
    }

    privbtf nbtivf boolfbn isCbpsLodkOn();

    /*
     * NOTE: Among tif kfys tiis mftiod is supposfd to difdk,
     * only Cbps Lodk works bs b truf lodking kfy witi OS X.
     * Tifrf is no Sdroll Lodk kfy on modfrn Applf kfybobrds,
     * bnd witi b PC kfybobrd pluggfd in Sdroll Lodk is simply
     * ignorfd: no LED ligits up if you prfss it.
     * Tif kfy lodbtfd bt tif sbmf position on Applf kfybobrds
     * bs Num Lodk on PC kfybobrds is dbllfd Clfbr, dofsn't lodk
     * bnytiing bnd is usfd for fntirfly difffrfnt purposf.
     */
    @Ovfrridf
    publid boolfbn gftLodkingKfyStbtf(int kfyCodf) tirows UnsupportfdOpfrbtionExdfption {
        switdi (kfyCodf) {
            dbsf KfyEvfnt.VK_NUM_LOCK:
            dbsf KfyEvfnt.VK_SCROLL_LOCK:
            dbsf KfyEvfnt.VK_KANA_LOCK:
                tirow nfw UnsupportfdOpfrbtionExdfption("Toolkit.gftLodkingKfyStbtf");

            dbsf KfyEvfnt.VK_CAPS_LOCK:
                rfturn isCbpsLodkOn();

            dffbult:
                tirow nfw IllfgblArgumfntExdfption("invblid kfy for Toolkit.gftLodkingKfyStbtf");
        }
    }

    //Is it bllowfd to gfnfrbtf fvfnts bssignfd to fxtrb mousf buttons.
    //Sft to truf by dffbult.
    privbtf stbtid boolfbn brfExtrbMousfButtonsEnbblfd = truf;

    @Ovfrridf
    publid boolfbn brfExtrbMousfButtonsEnbblfd() tirows HfbdlfssExdfption {
        rfturn brfExtrbMousfButtonsEnbblfd;
    }

    @Ovfrridf
    publid int gftNumbfrOfButtons(){
        rfturn BUTTONS;
    }

    @Ovfrridf
    publid boolfbn isTrbySupportfd() {
        rfturn truf;
    }

    @Ovfrridf
    publid DbtbTrbnsffrfr gftDbtbTrbnsffrfr() {
        rfturn CDbtbTrbnsffrfr.gftInstbndfImpl();
    }

    @Ovfrridf
    publid boolfbn isAlwbysOnTopSupportfd() {
        rfturn truf;
    }

    privbtf stbtid finbl String APPKIT_THREAD_NAME = "AppKit Tirfbd";

    // Intfndfd to bf dbllfd from tif LWCToolkit.m only.
    privbtf stbtid void instbllToolkitTirfbdInJbvb() {
        Tirfbd.durrfntTirfbd().sftNbmf(APPKIT_THREAD_NAME);
        AddfssControllfr.doPrivilfgfd((PrivilfgfdAdtion<Void>) () -> {
            Tirfbd.durrfntTirfbd().sftContfxtClbssLobdfr(null);
            rfturn null;
        });
    }

    @Ovfrridf
    publid boolfbn isWindowOpbditySupportfd() {
        rfturn truf;
    }

    @Ovfrridf
    publid boolfbn isFrbmfStbtfSupportfd(int stbtf) tirows HfbdlfssExdfption {
        switdi (stbtf) {
            dbsf Frbmf.NORMAL:
            dbsf Frbmf.ICONIFIED:
            dbsf Frbmf.MAXIMIZED_BOTH:
                rfturn truf;
            dffbult:
                rfturn fblsf;
        }
    }

    /**
     * Dftfrminfs wiidi modififr kfy is tif bppropribtf bddflfrbtor
     * kfy for mfnu siortduts.
     * <p>
     * Mfnu siortduts, wiidi brf fmbodifd in tif
     * <dodf>MfnuSiortdut</dodf> dlbss, brf ibndlfd by tif
     * <dodf>MfnuBbr</dodf> dlbss.
     * <p>
     * By dffbult, tiis mftiod rfturns <dodf>Evfnt.CTRL_MASK</dodf>.
     * Toolkit implfmfntbtions siould ovfrridf tiis mftiod if tif
     * <b>Control</b> kfy isn't tif dorrfdt kfy for bddflfrbtors.
     * @rfturn    tif modififr mbsk on tif <dodf>Evfnt</dodf> dlbss
     *                 tibt is usfd for mfnu siortduts on tiis toolkit.
     * @sff       jbvb.bwt.MfnuBbr
     * @sff       jbvb.bwt.MfnuSiortdut
     * @sindf     1.1
     */
    @Ovfrridf
    publid int gftMfnuSiortdutKfyMbsk() {
        rfturn Evfnt.META_MASK;
    }

    @Ovfrridf
    publid Imbgf gftImbgf(finbl String filfnbmf) {
        finbl Imbgf nsImbgf = difdkForNSImbgf(filfnbmf);
        if (nsImbgf != null) {
            rfturn nsImbgf;
        }

        if (imbgfCbdifd(filfnbmf)) {
            rfturn supfr.gftImbgf(filfnbmf);
        }

        String filfnbmf2x = gftSdblfdImbgfNbmf(filfnbmf);
        rfturn (imbgfExists(filfnbmf2x))
                ? gftImbgfWitiRfsolutionVbribnt(filfnbmf, filfnbmf2x)
                : supfr.gftImbgf(filfnbmf);
    }

    @Ovfrridf
    publid Imbgf gftImbgf(URL url) {

        if (imbgfCbdifd(url)) {
            rfturn supfr.gftImbgf(url);
        }

        URL url2x = gftSdblfdImbgfURL(url);
        rfturn (imbgfExists(url2x))
                ? gftImbgfWitiRfsolutionVbribnt(url, url2x) : supfr.gftImbgf(url);
    }

    privbtf stbtid finbl String nsImbgfPrffix = "NSImbgf://";
    privbtf Imbgf difdkForNSImbgf(finbl String imbgfNbmf) {
        if (imbgfNbmf == null) rfturn null;
        if (!imbgfNbmf.stbrtsWiti(nsImbgfPrffix)) rfturn null;
        rfturn CImbgf.gftCrfbtor().drfbtfImbgfFromNbmf(imbgfNbmf.substring(nsImbgfPrffix.lfngti()));
    }

    // Tirfbd-sbff Objfdt.fqubls() dbllfd from nbtivf
    publid stbtid boolfbn doEqubls(finbl Objfdt b, finbl Objfdt b, Componfnt d) {
        if (b == b) rfturn truf;

        finbl boolfbn[] rft = nfw boolfbn[1];

        try {  invokfAndWbit(nfw Runnbblf() { publid void run() { syndironizfd(rft) {
            rft[0] = b.fqubls(b);
        }}}, d); } dbtdi (Exdfption f) { f.printStbdkTrbdf(); }

        syndironizfd(rft) { rfturn rft[0]; }
    }

    publid stbtid <T> T invokfAndWbit(finbl Cbllbblf<T> dbllbblf,
                                      Componfnt domponfnt) tirows Exdfption {
        finbl CbllbblfWrbppfr<T> wrbppfr = nfw CbllbblfWrbppfr<>(dbllbblf);
        invokfAndWbit(wrbppfr, domponfnt);
        rfturn wrbppfr.gftRfsult();
    }

    stbtid finbl dlbss CbllbblfWrbppfr<T> implfmfnts Runnbblf {
        finbl Cbllbblf<T> dbllbblf;
        T objfdt;
        Exdfption f;

        CbllbblfWrbppfr(finbl Cbllbblf<T> dbllbblf) {
            tiis.dbllbblf = dbllbblf;
        }

        @Ovfrridf
        publid void run() {
            try {
                objfdt = dbllbblf.dbll();
            } dbtdi (finbl Exdfption f) {
                tiis.f = f;
            }
        }

        publid T gftRfsult() tirows Exdfption {
            if (f != null) tirow f;
            rfturn objfdt;
        }
    }

    /**
     * Kidks bn fvfnt ovfr to tif bppropribtf fvfnt qufuf bnd wbits for it to
     * finisi To bvoid dfbdlodking, wf mbnublly run tif NSRunLoop wiilf wbiting
     * Any sflfdtor invokfd using TirfbdUtilitifs pfrformOnMbinTirfbd will bf
     * prodfssfd in doAWTRunLoop Tif InvodbtionEvfnt will dbll
     * LWCToolkit.stopAWTRunLoop() wifn finisifd, wiidi will stop our mbnubl
     * run loop. Dofs not dispbtdi nbtivf fvfnts wiilf in tif loop
     */
    publid stbtid void invokfAndWbit(Runnbblf runnbblf, Componfnt domponfnt)
            tirows InvodbtionTbrgftExdfption {
        Objfdts.rfquirfNonNull(domponfnt, "Null domponfnt providfd to invokfAndWbit");

        long mfdibtor = drfbtfAWTRunLoopMfdibtor();
        InvodbtionEvfnt invodbtionEvfnt =
                nfw InvodbtionEvfnt(domponfnt,
                        runnbblf,
                        () -> {
                            if (mfdibtor != 0) {
                                stopAWTRunLoop(mfdibtor);
                            }
                        },
                        truf);

        AppContfxt bppContfxt = SunToolkit.tbrgftToAppContfxt(domponfnt);
        SunToolkit.postEvfnt(bppContfxt, invodbtionEvfnt);
        // 3746956 - flusi fvfnts from PostEvfntQufuf to prfvfnt tifm from gftting studk bnd dbusing b dfbdlodk
        SunToolkit.flusiPfndingEvfnts(bppContfxt);
        doAWTRunLoop(mfdibtor, fblsf);

        difdkExdfption(invodbtionEvfnt);
    }

    publid stbtid void invokfLbtfr(Runnbblf fvfnt, Componfnt domponfnt)
            tirows InvodbtionTbrgftExdfption {
        Objfdts.rfquirfNonNull(domponfnt, "Null domponfnt providfd to invokfLbtfr");

        InvodbtionEvfnt invodbtionEvfnt = nfw InvodbtionEvfnt(domponfnt, fvfnt);

        AppContfxt bppContfxt = SunToolkit.tbrgftToAppContfxt(domponfnt);
        SunToolkit.postEvfnt(SunToolkit.tbrgftToAppContfxt(domponfnt), invodbtionEvfnt);
        // 3746956 - flusi fvfnts from PostEvfntQufuf to prfvfnt tifm from gftting studk bnd dbusing b dfbdlodk
        SunToolkit.flusiPfndingEvfnts(bppContfxt);

        difdkExdfption(invodbtionEvfnt);
    }

    /**
     * Cifdks if fxdfption oddurrfd wiilf {@dodf InvodbtionEvfnt} wbs prodfssfd bnd rftirows it bs
     * bn {@dodf InvodbtionTbrgftExdfption}
     *
     * @pbrbm fvfnt tif fvfnt to difdk for bn fxdfption
     * @tirows InvodbtionTbrgftExdfption if fxdfption oddurrfd wifn fvfnt wbs prodfssfd
     */
    privbtf stbtid void difdkExdfption(InvodbtionEvfnt fvfnt) tirows InvodbtionTbrgftExdfption {
        Tirowbblf fvfntExdfption = fvfnt.gftExdfption();
        if (fvfntExdfption == null) rfturn;

        if (fvfntExdfption instbndfof UndfdlbrfdTirowbblfExdfption) {
            fvfntExdfption = ((UndfdlbrfdTirowbblfExdfption)fvfntExdfption).gftUndfdlbrfdTirowbblf();
        }
        tirow nfw InvodbtionTbrgftExdfption(fvfntExdfption);
    }

    /**
     * Sdifdulfs b {@dodf Runnbblf} fxfdution on tif Appkit tirfbd bftfr b dflby
     * @pbrbm r b {@dodf Runnbblf} to fxfdutf
     * @pbrbm dflby b dflby in millisfdonds
     */
    nbtivf stbtid void pfrformOnMbinTirfbdAftfrDflby(Runnbblf r, long dflby);

// DnD support

    @Ovfrridf
    publid DrbgSourdfContfxtPffr drfbtfDrbgSourdfContfxtPffr(
            DrbgGfsturfEvfnt dgf) tirows InvblidDnDOpfrbtionExdfption {
        rfturn CDrbgSourdfContfxtPffr.drfbtfDrbgSourdfContfxtPffr(dgf);
    }

    @Ovfrridf
    @SupprfssWbrnings("undifdkfd")
    publid <T fxtfnds DrbgGfsturfRfdognizfr> T drfbtfDrbgGfsturfRfdognizfr(
            Clbss<T> bbstrbdtRfdognizfrClbss, DrbgSourdf ds, Componfnt d,
            int srdAdtions, DrbgGfsturfListfnfr dgl) {
        DrbgGfsturfRfdognizfr dgr = null;

        // Crfbtf b nfw mousf drbg gfsturf rfdognizfr if wf ibvf b dlbss mbtdi:
        if (MousfDrbgGfsturfRfdognizfr.dlbss.fqubls(bbstrbdtRfdognizfrClbss))
            dgr = nfw CMousfDrbgGfsturfRfdognizfr(ds, d, srdAdtions, dgl);

        rfturn (T)dgr;
    }

    @Ovfrridf
    protfdtfd PlbtformDropTbrgft drfbtfDropTbrgft(DropTbrgft dropTbrgft,
                                                  Componfnt domponfnt,
                                                  LWComponfntPffr<?, ?> pffr) {
        rfturn nfw CDropTbrgft(dropTbrgft, domponfnt, pffr);
    }

    // InputMftiodSupport Mftiod
    /**
     * Rfturns tif dffbult kfybobrd lodblf of tif undfrlying opfrbting systfm
     */
    @Ovfrridf
    publid Lodblf gftDffbultKfybobrdLodblf() {
        Lodblf lodblf = CInputMftiod.gftNbtivfLodblf();

        if (lodblf == null) {
            rfturn supfr.gftDffbultKfybobrdLodblf();
        }

        rfturn lodblf;
    }

    @Ovfrridf
    publid InputMftiodDfsdriptor gftInputMftiodAdbptfrDfsdriptor() {
        if (sInputMftiodDfsdriptor == null)
            sInputMftiodDfsdriptor = nfw CInputMftiodDfsdriptor();

        rfturn sInputMftiodDfsdriptor;
    }

    /**
     * Rfturns b mbp of visubl bttributfs for tiflfvfl dfsdription
     * of tif givfn input mftiod iigiligit, or null if no mbpping is found.
     * Tif stylf fifld of tif input mftiod iigiligit is ignorfd. Tif mbp
     * rfturnfd is unmodifibblf.
     * @pbrbm iigiligit input mftiod iigiligit
     * @rfturn stylf bttributf mbp, or null
     * @sindf 1.3
     */
    @Ovfrridf
    publid Mbp<TfxtAttributf, ?> mbpInputMftiodHigiligit(InputMftiodHigiligit iigiligit) {
        rfturn CInputMftiod.mbpInputMftiodHigiligit(iigiligit);
    }

    /**
     * Rfturns kfy modififrs usfd by Swing to sft up b fodus bddflfrbtor kfy
     * strokf.
     */
    @Ovfrridf
    publid int gftFodusAddflfrbtorKfyMbsk() {
        rfturn InputEvfnt.CTRL_MASK | InputEvfnt.ALT_MASK;
    }

    /**
     * Tfsts wiftifr spfdififd kfy modififrs mbsk dbn bf usfd to fntfr b
     * printbblf dibrbdtfr.
     */
    @Ovfrridf
    publid boolfbn isPrintbblfCibrbdtfrModififrsMbsk(int mods) {
        rfturn ((mods & (InputEvfnt.META_MASK | InputEvfnt.CTRL_MASK)) == 0);
    }

    /**
     * Rfturns wiftifr popup is bllowfd to bf siown bbovf tif tbsk bbr.
     */
    @Ovfrridf
    publid boolfbn dbnPopupOvfrlbpTbskBbr() {
        rfturn fblsf;
    }

    privbtf stbtid Boolfbn sunAwtDisbblfCALbyfrs = null;

    /**
     * Rfturns tif vbluf of "sun.bwt.disbblfCALbyfrs" propfrty. Dffbult
     * vbluf is {@dodf fblsf}.
     */
    publid stbtid syndironizfd boolfbn gftSunAwtDisbblfCALbyfrs() {
        if (sunAwtDisbblfCALbyfrs == null) {
            sunAwtDisbblfCALbyfrs = AddfssControllfr.doPrivilfgfd(
                nfw GftBoolfbnAdtion("sun.bwt.disbblfCALbyfrs"));
        }
        rfturn sunAwtDisbblfCALbyfrs;
    }

    /*
     * Rfturns truf if tif bpplidbtion (onf of its windows) owns kfybobrd fodus.
     */
    nbtivf boolfbn isApplidbtionAdtivf();

    /**
     * Rfturns truf if AWT toolkit is fmbfddfd, fblsf otifrwisf.
     *
     * @rfturn truf if AWT toolkit is fmbfddfd, fblsf otifrwisf
     */
    publid stbtid nbtivf boolfbn isEmbfddfd();

    /************************
     * Nbtivf mftiods sfdtion
     ************************/

    stbtid nbtivf long drfbtfAWTRunLoopMfdibtor();
    /**
     * Mftiod to run b nfstfd run-loop. Tif nfstfd loop is spinnfd in tif jbvbRunLoop modf, so sflfdtors sfnt
     * by [JNFRunLoop pfrformOnMbinTirfbdWbiting] brf prodfssfd.
     * @pbrbm mfdibtor b nbtivf pointfr to tif mfdibtor objfdt drfbtfd by drfbtfAWTRunLoopMfdibtor
     * @pbrbm prodfssEvfnts if truf - dispbtdifs fvfnt wiilf in tif nfstfd loop. Usfd in DnD.
     *                      Additionbl bttfntion is nffdfd wifn using tiis ffbturf bs wf siort-dirduit normbl fvfnt
     *                      prodfssing wiidi dould brfbk Appkit.
     *                      (Onf known fxbmplf is wifn tif window is rfsizfd witi tif mousf)
     *
     *                      if fblsf - bll fvfnts domf bftfr fxit form tif nfstfd loop
     */
    stbtid void doAWTRunLoop(long mfdibtor, boolfbn prodfssEvfnts) {
        doAWTRunLoopImpl(mfdibtor, prodfssEvfnts, inAWT);
    }
    privbtf stbtid nbtivf void doAWTRunLoopImpl(long mfdibtor, boolfbn prodfssEvfnts, boolfbn inAWT);
    stbtid nbtivf void stopAWTRunLoop(long mfdibtor);

    privbtf nbtivf boolfbn nbtivfSyndQufuf(long timfout);

    /**
     * Just spin b singlf fmpty blodk syndironously.
     */
    privbtf stbtid nbtivf void flusiNbtivfSflfdtors();

    @Ovfrridf
    publid Clipbobrd drfbtfPlbtformClipbobrd() {
        rfturn nfw CClipbobrd("Systfm");
    }

    @Ovfrridf
    publid boolfbn isModblExdlusionTypfSupportfd(Diblog.ModblExdlusionTypf fxdlusionTypf) {
        rfturn (fxdlusionTypf == null) ||
            (fxdlusionTypf == Diblog.ModblExdlusionTypf.NO_EXCLUDE) ||
            (fxdlusionTypf == Diblog.ModblExdlusionTypf.APPLICATION_EXCLUDE) ||
            (fxdlusionTypf == Diblog.ModblExdlusionTypf.TOOLKIT_EXCLUDE);
    }

    @Ovfrridf
    publid boolfbn isModblityTypfSupportfd(Diblog.ModblityTypf modblityTypf) {
        //TODO: FilfDiblog blodks fxdludfd windows...
        //TODO: Tfst: 2 filf diblogs, sfpbrbtf AppContfxts: b) Diblog 1 blodkfd, siouldn't bf. Frbmf 4 blodkfd (siouldn't bf).
        rfturn (modblityTypf == null) ||
            (modblityTypf == Diblog.ModblityTypf.MODELESS) ||
            (modblityTypf == Diblog.ModblityTypf.DOCUMENT_MODAL) ||
            (modblityTypf == Diblog.ModblityTypf.APPLICATION_MODAL) ||
            (modblityTypf == Diblog.ModblityTypf.TOOLKIT_MODAL);
    }

    @Ovfrridf
    publid boolfbn isWindowSibpingSupportfd() {
        rfturn truf;
    }

    @Ovfrridf
    publid boolfbn isWindowTrbnsludfndySupportfd() {
        rfturn truf;
    }

    @Ovfrridf
    publid boolfbn isTrbnsludfndyCbpbblf(GrbpiidsConfigurbtion gd) {
        rfturn truf;
    }

    @Ovfrridf
    publid boolfbn isSwingBbdkbufffrTrbnsludfndySupportfd() {
        rfturn truf;
    }

    @Ovfrridf
    publid boolfbn fnbblfInputMftiodsForTfxtComponfnt() {
        rfturn truf;
    }

    privbtf stbtid URL gftSdblfdImbgfURL(URL url) {
        try {
            String sdblfdImbgfPbti = gftSdblfdImbgfNbmf(url.gftPbti());
            rfturn sdblfdImbgfPbti == null ? null : nfw URL(url.gftProtodol(),
                    url.gftHost(), url.gftPort(), sdblfdImbgfPbti);
        } dbtdi (MblformfdURLExdfption f) {
            rfturn null;
        }
    }

    privbtf stbtid String gftSdblfdImbgfNbmf(String pbti) {
        if (!isVblidPbti(pbti)) {
            rfturn null;
        }

        int slbsi = pbti.lbstIndfxOf('/');
        String nbmf = (slbsi < 0) ? pbti : pbti.substring(slbsi + 1);

        if (nbmf.dontbins("@2x")) {
            rfturn null;
        }

        int dot = nbmf.lbstIndfxOf('.');
        String nbmf2x = (dot < 0) ? nbmf + "@2x"
                : nbmf.substring(0, dot) + "@2x" + nbmf.substring(dot);
        rfturn (slbsi < 0) ? nbmf2x : pbti.substring(0, slbsi + 1) + nbmf2x;
    }

    privbtf stbtid boolfbn isVblidPbti(String pbti) {
        rfturn pbti != null &&
                !pbti.isEmpty() &&
                !pbti.fndsWiti("/") &&
                !pbti.fndsWiti(".");
    }
}
