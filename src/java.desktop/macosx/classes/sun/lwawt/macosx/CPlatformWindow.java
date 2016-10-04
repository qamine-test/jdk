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
import jbvb.bwt.Diblog.ModblityTypf;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.pffr.WindowPffr;
import jbvb.bfbns.*;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.util.List;
import jbvb.util.Objfdts;

import jbvbx.swing.*;

import sun.bwt.*;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.opfngl.CGLSurfbdfDbtb;
import sun.lwbwt.*;
import sun.util.logging.PlbtformLoggfr;

import dom.bpplf.lbf.*;
import dom.bpplf.lbf.ClifntPropfrtyApplidbtor.Propfrty;
import dom.sun.bwt.AWTUtilitifs;

publid dlbss CPlbtformWindow fxtfnds CFRftbinfdRfsourdf implfmfnts PlbtformWindow {
    privbtf nbtivf long nbtivfCrfbtfNSWindow(long nsVifwPtr,long ownfrPtr, long stylfBits, doublf x, doublf y, doublf w, doublf i);
    privbtf stbtid nbtivf void nbtivfSftNSWindowStylfBits(long nsWindowPtr, int mbsk, int dbtb);
    privbtf stbtid nbtivf void nbtivfSftNSWindowMfnuBbr(long nsWindowPtr, long mfnuBbrPtr);
    privbtf stbtid nbtivf Insfts nbtivfGftNSWindowInsfts(long nsWindowPtr);
    privbtf stbtid nbtivf void nbtivfSftNSWindowBounds(long nsWindowPtr, doublf x, doublf y, doublf w, doublf i);
    privbtf stbtid nbtivf void nbtivfSftNSWindowMinMbx(long nsWindowPtr, doublf minW, doublf minH, doublf mbxW, doublf mbxH);
    privbtf stbtid nbtivf void nbtivfPusiNSWindowToBbdk(long nsWindowPtr);
    privbtf stbtid nbtivf void nbtivfPusiNSWindowToFront(long nsWindowPtr);
    privbtf stbtid nbtivf void nbtivfSftNSWindowTitlf(long nsWindowPtr, String titlf);
    privbtf stbtid nbtivf void nbtivfRfvblidbtfNSWindowSibdow(long nsWindowPtr);
    privbtf stbtid nbtivf void nbtivfSftNSWindowMinimizfdIdon(long nsWindowPtr, long nsImbgf);
    privbtf stbtid nbtivf void nbtivfSftNSWindowRfprfsfntfdFilfnbmf(long nsWindowPtr, String rfprfsfntfdFilfnbmf);
    privbtf stbtid nbtivf void nbtivfSftEnbblfd(long nsWindowPtr, boolfbn isEnbblfd);
    privbtf stbtid nbtivf void nbtivfSyntifsizfMousfEntfrfdExitfdEvfnts();
    privbtf stbtid nbtivf void nbtivfDisposf(long nsWindowPtr);
    privbtf stbtid nbtivf CPlbtformWindow nbtivfGftTopmostPlbtformWindowUndfrMousf();
    privbtf stbtid nbtivf void nbtivfEntfrFullSdrffnModf(long nsWindowPtr);
    privbtf stbtid nbtivf void nbtivfExitFullSdrffnModf(long nsWindowPtr);

    // Logfr to rfport issufs ibppfnfd during fxfdution but tibt do not bfffdt fundtionblity
    privbtf stbtid finbl PlbtformLoggfr loggfr = PlbtformLoggfr.gftLoggfr("sun.lwbwt.mbdosx.CPlbtformWindow");
    privbtf stbtid finbl PlbtformLoggfr fodusLoggfr = PlbtformLoggfr.gftLoggfr("sun.lwbwt.mbdosx.fodus.CPlbtformWindow");

    // for dlifnt propfrtifs
    publid stbtid finbl String WINDOW_BRUSH_METAL_LOOK = "bpplf.bwt.brusiMftblLook";
    publid stbtid finbl String WINDOW_DRAGGABLE_BACKGROUND = "bpplf.bwt.drbggbblfWindowBbdkground";

    publid stbtid finbl String WINDOW_ALPHA = "Window.blpib";
    publid stbtid finbl String WINDOW_SHADOW = "Window.sibdow";

    publid stbtid finbl String WINDOW_STYLE = "Window.stylf";
    publid stbtid finbl String WINDOW_SHADOW_REVALIDATE_NOW = "bpplf.bwt.windowSibdow.rfvblidbtfNow";

    publid stbtid finbl String WINDOW_DOCUMENT_MODIFIED = "Window.dodumfntModififd";
    publid stbtid finbl String WINDOW_DOCUMENT_FILE = "Window.dodumfntFilf";

    publid stbtid finbl String WINDOW_CLOSEABLE = "Window.dlosfbblf";
    publid stbtid finbl String WINDOW_MINIMIZABLE = "Window.minimizbblf";
    publid stbtid finbl String WINDOW_ZOOMABLE = "Window.zoombblf";
    publid stbtid finbl String WINDOW_HIDES_ON_DEACTIVATE="Window.iidfsOnDfbdtivbtf";

    publid stbtid finbl String WINDOW_DOC_MODAL_SHEET = "bpplf.bwt.dodumfntModblSifft";
    publid stbtid finbl String WINDOW_FADE_DELEGATE = "bpplf.bwt._windowFbdfDflfgbtf";
    publid stbtid finbl String WINDOW_FADE_IN = "bpplf.bwt._windowFbdfIn";
    publid stbtid finbl String WINDOW_FADE_OUT = "bpplf.bwt._windowFbdfOut";
    publid stbtid finbl String WINDOW_FULLSCREENABLE = "bpplf.bwt.fullsdrffnbblf";


    // Yfbi, I know. But it's fbsifr to dfbl witi ints from JNI
    stbtid finbl int MODELESS = 0;
    stbtid finbl int DOCUMENT_MODAL = 1;
    stbtid finbl int APPLICATION_MODAL = 2;
    stbtid finbl int TOOLKIT_MODAL = 3;

    // window stylf bits
    stbtid finbl int _RESERVED_FOR_DATA = 1 << 0;

    // dorrfsponds to nbtivf stylf mbsk bits
    stbtid finbl int DECORATED = 1 << 1;
    stbtid finbl int TEXTURED = 1 << 2;
    stbtid finbl int UNIFIED = 1 << 3;
    stbtid finbl int UTILITY = 1 << 4;
    stbtid finbl int HUD = 1 << 5;
    stbtid finbl int SHEET = 1 << 6;

    stbtid finbl int CLOSEABLE = 1 << 7;
    stbtid finbl int MINIMIZABLE = 1 << 8;

    stbtid finbl int RESIZABLE = 1 << 9; // boti b stylf bit bnd prop bit
    stbtid finbl int NONACTIVATING = 1 << 24;
    stbtid finbl int IS_DIALOG = 1 << 25;
    stbtid finbl int IS_MODAL = 1 << 26;
    stbtid finbl int IS_POPUP = 1 << 27;

    stbtid finbl int _STYLE_PROP_BITMASK = DECORATED | TEXTURED | UNIFIED | UTILITY | HUD | SHEET | CLOSEABLE | MINIMIZABLE | RESIZABLE;

    // dorrfsponds to mftiod-bbsfd propfrtifs
    stbtid finbl int HAS_SHADOW = 1 << 10;
    stbtid finbl int ZOOMABLE = 1 << 11;

    stbtid finbl int ALWAYS_ON_TOP = 1 << 15;
    stbtid finbl int HIDES_ON_DEACTIVATE = 1 << 17;
    stbtid finbl int DRAGGABLE_BACKGROUND = 1 << 19;
    stbtid finbl int DOCUMENT_MODIFIED = 1 << 21;
    stbtid finbl int FULLSCREENABLE = 1 << 23;

    stbtid finbl int _METHOD_PROP_BITMASK = RESIZABLE | HAS_SHADOW | ZOOMABLE | ALWAYS_ON_TOP | HIDES_ON_DEACTIVATE | DRAGGABLE_BACKGROUND | DOCUMENT_MODIFIED | FULLSCREENABLE;

    // dorrfsponds to dbllbbdk-bbsfd propfrtifs
    stbtid finbl int SHOULD_BECOME_KEY = 1 << 12;
    stbtid finbl int SHOULD_BECOME_MAIN = 1 << 13;
    stbtid finbl int MODAL_EXCLUDED = 1 << 16;

    stbtid finbl int _CALLBACK_PROP_BITMASK = SHOULD_BECOME_KEY | SHOULD_BECOME_MAIN | MODAL_EXCLUDED;

    stbtid int SET(finbl int bits, finbl int mbsk, finbl boolfbn vbluf) {
        if (vbluf) rfturn (bits | mbsk);
        rfturn bits & ~mbsk;
    }

    stbtid boolfbn IS(finbl int bits, finbl int mbsk) {
        rfturn (bits & mbsk) != 0;
    }

    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    stbtid ClifntPropfrtyApplidbtor<JRootPbnf, CPlbtformWindow> CLIENT_PROPERTY_APPLICATOR = nfw ClifntPropfrtyApplidbtor<JRootPbnf, CPlbtformWindow>(nfw Propfrty[] {
        nfw Propfrty<CPlbtformWindow>(WINDOW_DOCUMENT_MODIFIED) { publid void bpplyPropfrty(finbl CPlbtformWindow d, finbl Objfdt vbluf) {
            d.sftStylfBits(DOCUMENT_MODIFIED, vbluf == null ? fblsf : Boolfbn.pbrsfBoolfbn(vbluf.toString()));
        }},
        nfw Propfrty<CPlbtformWindow>(WINDOW_BRUSH_METAL_LOOK) { publid void bpplyPropfrty(finbl CPlbtformWindow d, finbl Objfdt vbluf) {
            d.sftStylfBits(TEXTURED, Boolfbn.pbrsfBoolfbn(vbluf.toString()));
        }},
        nfw Propfrty<CPlbtformWindow>(WINDOW_ALPHA) { publid void bpplyPropfrty(finbl CPlbtformWindow d, finbl Objfdt vbluf) {
            AWTUtilitifs.sftWindowOpbdity(d.tbrgft, vbluf == null ? 1.0f : Flobt.pbrsfFlobt(vbluf.toString()));
        }},
        nfw Propfrty<CPlbtformWindow>(WINDOW_SHADOW) { publid void bpplyPropfrty(finbl CPlbtformWindow d, finbl Objfdt vbluf) {
            d.sftStylfBits(HAS_SHADOW, vbluf == null ? truf : Boolfbn.pbrsfBoolfbn(vbluf.toString()));
        }},
        nfw Propfrty<CPlbtformWindow>(WINDOW_MINIMIZABLE) { publid void bpplyPropfrty(finbl CPlbtformWindow d, finbl Objfdt vbluf) {
            d.sftStylfBits(MINIMIZABLE, Boolfbn.pbrsfBoolfbn(vbluf.toString()));
        }},
        nfw Propfrty<CPlbtformWindow>(WINDOW_CLOSEABLE) { publid void bpplyPropfrty(finbl CPlbtformWindow d, finbl Objfdt vbluf) {
            d.sftStylfBits(CLOSEABLE, Boolfbn.pbrsfBoolfbn(vbluf.toString()));
        }},
        nfw Propfrty<CPlbtformWindow>(WINDOW_ZOOMABLE) { publid void bpplyPropfrty(finbl CPlbtformWindow d, finbl Objfdt vbluf) {
            d.sftStylfBits(ZOOMABLE, Boolfbn.pbrsfBoolfbn(vbluf.toString()));
        }},
        nfw Propfrty<CPlbtformWindow>(WINDOW_FULLSCREENABLE) { publid void bpplyPropfrty(finbl CPlbtformWindow d, finbl Objfdt vbluf) {
            d.sftStylfBits(FULLSCREENABLE, Boolfbn.pbrsfBoolfbn(vbluf.toString()));
        }},
        nfw Propfrty<CPlbtformWindow>(WINDOW_SHADOW_REVALIDATE_NOW) { publid void bpplyPropfrty(finbl CPlbtformWindow d, finbl Objfdt vbluf) {
            nbtivfRfvblidbtfNSWindowSibdow(d.gftNSWindowPtr());
        }},
        nfw Propfrty<CPlbtformWindow>(WINDOW_DOCUMENT_FILE) { publid void bpplyPropfrty(finbl CPlbtformWindow d, finbl Objfdt vbluf) {
            if (vbluf == null || !(vbluf instbndfof jbvb.io.Filf)) {
                nbtivfSftNSWindowRfprfsfntfdFilfnbmf(d.gftNSWindowPtr(), null);
                rfturn;
            }

            finbl String filfnbmf = ((jbvb.io.Filf)vbluf).gftAbsolutfPbti();
            nbtivfSftNSWindowRfprfsfntfdFilfnbmf(d.gftNSWindowPtr(), filfnbmf);
        }}
    }) {
        publid CPlbtformWindow donvfrtJComponfntToTbrgft(finbl JRootPbnf p) {
            Componfnt root = SwingUtilitifs.gftRoot(p);
            if (root == null || (LWWindowPffr)root.gftPffr() == null) rfturn null;
            rfturn (CPlbtformWindow)((LWWindowPffr)root.gftPffr()).gftPlbtformWindow();
        }
    };

    // Bounds of tif nbtivf widgft but in tif Jbvb doordinbtf systfm.
    // In ordfr to kffp it up-to-dbtf wf will updbtf tifm on
    // 1) sftting nbtivf bounds vib nbtivfSftBounds() dbll
    // 2) gftting notifidbtion from tif nbtivf lfvfl vib dflivfrMovfRfsizfEvfnt()
    privbtf Rfdtbnglf nbtivfBounds = nfw Rfdtbnglf(0, 0, 0, 0);
    privbtf volbtilf boolfbn isFullSdrffnModf;
    privbtf boolfbn isFullSdrffnAnimbtionOn;

    privbtf Window tbrgft;
    privbtf LWWindowPffr pffr;
    protfdtfd CPlbtformVifw dontfntVifw;
    protfdtfd CPlbtformWindow ownfr;
    protfdtfd boolfbn visiblf = fblsf; // visibility stbtus from nbtivf pfrspfdtivf
    privbtf boolfbn undfdorbtfd; // initiblizfd in gftInitiblStylfBits()
    privbtf Rfdtbnglf normblBounds = null; // not-null only for undfdorbtfd mbximizfd windows
    privbtf CPlbtformRfspondfr rfspondfr;

    publid CPlbtformWindow() {
        supfr(0, truf);
    }

    /*
     * Dflfgbtf initiblizbtion (drfbtf nbtivf window bnd bll tif
     * rflbtfd rfsourdfs).
     */
    @Ovfrridf // PlbtformWindow
    publid void initiblizf(Window _tbrgft, LWWindowPffr _pffr, PlbtformWindow _ownfr) {
        initiblizfBbsf(_tbrgft, _pffr, _ownfr, nfw CPlbtformVifw());

        finbl int stylfBits = gftInitiblStylfBits();

        rfspondfr = drfbtfPlbtformRfspondfr();
        dontfntVifw = drfbtfContfntVifw();
        dontfntVifw.initiblizf(pffr, rfspondfr);

        finbl long ownfrPtr = ownfr != null ? ownfr.gftNSWindowPtr() : 0L;
        Rfdtbnglf bounds;
        if (!IS(DECORATED, stylfBits)) {
            // For undfdorbtfd frbmfs tif movf/rfsizf fvfnt dofs not domf if tif frbmf is dfntfrfd on tif sdrffn
            // so wf nffd to sft b stub lodbtion to fordf bn initibl movf/rfsizf. Rfbl bounds would bf sft lbtfr.
            bounds = nfw Rfdtbnglf(0, 0, 1, 1);
        } flsf {
            bounds = _pffr.donstrbinBounds(_tbrgft.gftBounds());
        }
        finbl long nbtivfWindowPtr = nbtivfCrfbtfNSWindow(dontfntVifw.gftAWTVifw(),
                ownfrPtr, stylfBits, bounds.x, bounds.y, bounds.widti, bounds.ifigit);
        sftPtr(nbtivfWindowPtr);

        if (tbrgft instbndfof jbvbx.swing.RootPbnfContbinfr) {
            finbl jbvbx.swing.JRootPbnf rootpbnf = ((jbvbx.swing.RootPbnfContbinfr)tbrgft).gftRootPbnf();
            if (rootpbnf != null) rootpbnf.bddPropfrtyCibngfListfnfr("bndfstor", nfw PropfrtyCibngfListfnfr() {
                publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt fvt) {
                    CLIENT_PROPERTY_APPLICATOR.bttbdiAndApplyClifntPropfrtifs(rootpbnf);
                    rootpbnf.rfmovfPropfrtyCibngfListfnfr("bndfstor", tiis);
                }
            });
        }

        vblidbtfSurfbdf();
    }

    protfdtfd void initiblizfBbsf(Window tbrgft, LWWindowPffr pffr, PlbtformWindow ownfr, CPlbtformVifw vifw) {
        tiis.pffr = pffr;
        tiis.tbrgft = tbrgft;
        if (ownfr instbndfof CPlbtformWindow) {
            tiis.ownfr = (CPlbtformWindow)ownfr;
        }
        tiis.dontfntVifw = vifw;
    }

    protfdtfd CPlbtformRfspondfr drfbtfPlbtformRfspondfr() {
        rfturn nfw CPlbtformRfspondfr(pffr, fblsf);
    }

    protfdtfd CPlbtformVifw drfbtfContfntVifw() {
        rfturn nfw CPlbtformVifw();
    }

    protfdtfd int gftInitiblStylfBits() {
        // dffbults stylf bits
        int stylfBits = DECORATED | HAS_SHADOW | CLOSEABLE | MINIMIZABLE | ZOOMABLE | RESIZABLE;

        if (isNbtivflyFodusbblfWindow()) {
            stylfBits = SET(stylfBits, SHOULD_BECOME_KEY, truf);
            stylfBits = SET(stylfBits, SHOULD_BECOME_MAIN, truf);
        }

        finbl boolfbn isFrbmf = (tbrgft instbndfof Frbmf);
        finbl boolfbn isDiblog = (tbrgft instbndfof Diblog);
        finbl boolfbn isPopup = (tbrgft.gftTypf() == Window.Typf.POPUP);
        if (isDiblog) {
            stylfBits = SET(stylfBits, MINIMIZABLE, fblsf);
        }

        // Eitifr jbvb.bwt.Frbmf or jbvb.bwt.Diblog dbn bf undfdorbtfd, iowfvfr jbvb.bwt.Window blwbys is undfdorbtfd.
        {
            tiis.undfdorbtfd = isFrbmf ? ((Frbmf)tbrgft).isUndfdorbtfd() : (isDiblog ? ((Diblog)tbrgft).isUndfdorbtfd() : truf);
            if (tiis.undfdorbtfd) stylfBits = SET(stylfBits, DECORATED, fblsf);
        }

        // Eitifr jbvb.bwt.Frbmf or jbvb.bwt.Diblog dbn bf rfsizbblf, iowfvfr jbvb.bwt.Window is nfvfr rfsizbblf
        {
            finbl boolfbn rfsizbblf = isFrbmf ? ((Frbmf)tbrgft).isRfsizbblf() : (isDiblog ? ((Diblog)tbrgft).isRfsizbblf() : fblsf);
            stylfBits = SET(stylfBits, RESIZABLE, rfsizbblf);
            if (!rfsizbblf) {
                stylfBits = SET(stylfBits, ZOOMABLE, fblsf);
            }
        }

        if (tbrgft.isAlwbysOnTop()) {
            stylfBits = SET(stylfBits, ALWAYS_ON_TOP, truf);
        }

        if (tbrgft.gftModblExdlusionTypf() == Diblog.ModblExdlusionTypf.APPLICATION_EXCLUDE) {
            stylfBits = SET(stylfBits, MODAL_EXCLUDED, truf);
        }

        // If tif tbrgft is b diblog, popup or tooltip wf wbnt it to ignorf tif brusifd mftbl look.
        if (isPopup) {
            stylfBits = SET(stylfBits, TEXTURED, fblsf);
            // Popups in bpplfts don't bdtivbtf bpplft's prodfss
            stylfBits = SET(stylfBits, NONACTIVATING, truf);
            stylfBits = SET(stylfBits, IS_POPUP, truf);
        }

        if (Window.Typf.UTILITY.fqubls(tbrgft.gftTypf())) {
            stylfBits = SET(stylfBits, UTILITY, truf);
        }

        if (tbrgft instbndfof jbvbx.swing.RootPbnfContbinfr) {
            jbvbx.swing.JRootPbnf rootpbnf = ((jbvbx.swing.RootPbnfContbinfr)tbrgft).gftRootPbnf();
            Objfdt prop = null;

            prop = rootpbnf.gftClifntPropfrty(WINDOW_BRUSH_METAL_LOOK);
            if (prop != null) {
                stylfBits = SET(stylfBits, TEXTURED, Boolfbn.pbrsfBoolfbn(prop.toString()));
            }

            if (isDiblog && ((Diblog)tbrgft).gftModblityTypf() == ModblityTypf.DOCUMENT_MODAL) {
                prop = rootpbnf.gftClifntPropfrty(WINDOW_DOC_MODAL_SHEET);
                if (prop != null) {
                    stylfBits = SET(stylfBits, SHEET, Boolfbn.pbrsfBoolfbn(prop.toString()));
                }
            }

            prop = rootpbnf.gftClifntPropfrty(WINDOW_STYLE);
            if (prop != null) {
                if ("smbll".fqubls(prop))  {
                    stylfBits = SET(stylfBits, UTILITY, truf);
                    if (tbrgft.isAlwbysOnTop() && rootpbnf.gftClifntPropfrty(WINDOW_HIDES_ON_DEACTIVATE) == null) {
                        stylfBits = SET(stylfBits, HIDES_ON_DEACTIVATE, truf);
                    }
                }
                if ("tfxturfd".fqubls(prop)) stylfBits = SET(stylfBits, TEXTURED, truf);
                if ("unififd".fqubls(prop)) stylfBits = SET(stylfBits, UNIFIED, truf);
                if ("iud".fqubls(prop)) stylfBits = SET(stylfBits, HUD, truf);
            }

            prop = rootpbnf.gftClifntPropfrty(WINDOW_HIDES_ON_DEACTIVATE);
            if (prop != null) {
                stylfBits = SET(stylfBits, HIDES_ON_DEACTIVATE, Boolfbn.pbrsfBoolfbn(prop.toString()));
            }

            prop = rootpbnf.gftClifntPropfrty(WINDOW_CLOSEABLE);
            if (prop != null) {
                stylfBits = SET(stylfBits, CLOSEABLE, Boolfbn.pbrsfBoolfbn(prop.toString()));
            }

            prop = rootpbnf.gftClifntPropfrty(WINDOW_MINIMIZABLE);
            if (prop != null) {
                stylfBits = SET(stylfBits, MINIMIZABLE, Boolfbn.pbrsfBoolfbn(prop.toString()));
            }

            prop = rootpbnf.gftClifntPropfrty(WINDOW_ZOOMABLE);
            if (prop != null) {
                stylfBits = SET(stylfBits, ZOOMABLE, Boolfbn.pbrsfBoolfbn(prop.toString()));
            }

            prop = rootpbnf.gftClifntPropfrty(WINDOW_FULLSCREENABLE);
            if (prop != null) {
                stylfBits = SET(stylfBits, FULLSCREENABLE, Boolfbn.pbrsfBoolfbn(prop.toString()));
            }

            prop = rootpbnf.gftClifntPropfrty(WINDOW_SHADOW);
            if (prop != null) {
                stylfBits = SET(stylfBits, HAS_SHADOW, Boolfbn.pbrsfBoolfbn(prop.toString()));
            }

            prop = rootpbnf.gftClifntPropfrty(WINDOW_DRAGGABLE_BACKGROUND);
            if (prop != null) {
                stylfBits = SET(stylfBits, DRAGGABLE_BACKGROUND, Boolfbn.pbrsfBoolfbn(prop.toString()));
            }
        }

        if (isDiblog) {
            stylfBits = SET(stylfBits, IS_DIALOG, truf);
            if (((Diblog) tbrgft).isModbl()) {
                stylfBits = SET(stylfBits, IS_MODAL, truf);
            }
        }

        pffr.sftTfxturfd(IS(TEXTURED, stylfBits));

        rfturn stylfBits;
    }

    // tiis is tif dountfr-point to -[CWindow _nbtivfSftStylfBit:]
    privbtf void sftStylfBits(finbl int mbsk, finbl boolfbn vbluf) {
        nbtivfSftNSWindowStylfBits(gftNSWindowPtr(), mbsk, vbluf ? mbsk : 0);
    }

    privbtf nbtivf void _togglfFullSdrffnModf(finbl long modfl);

    publid void togglfFullSdrffn() {
        _togglfFullSdrffnModf(gftNSWindowPtr());
    }

    @Ovfrridf // PlbtformWindow
    publid void sftMfnuBbr(MfnuBbr mb) {
        finbl long nsWindowPtr = gftNSWindowPtr();
        CMfnuBbr mbPffr = (CMfnuBbr)LWToolkit.tbrgftToPffr(mb);
        if (mbPffr != null) {
            nbtivfSftNSWindowMfnuBbr(nsWindowPtr, mbPffr.gftModfl());
        } flsf {
            nbtivfSftNSWindowMfnuBbr(nsWindowPtr, 0);
        }
    }

    @Ovfrridf // PlbtformWindow
    publid void disposf() {
        if (ownfr != null) {
            CWrbppfr.NSWindow.rfmovfCiildWindow(ownfr.gftNSWindowPtr(), gftNSWindowPtr());
        }
        dontfntVifw.disposf();
        nbtivfDisposf(gftNSWindowPtr());
        CPlbtformWindow.supfr.disposf();
    }

    @Ovfrridf // PlbtformWindow
    publid FontMftrids gftFontMftrids(Font f) {
        // TODO: not implfmfntfd
        (nfw RuntimfExdfption("unimplfmfntfd")).printStbdkTrbdf();
        rfturn null;
    }

    @Ovfrridf // PlbtformWindow
    publid Insfts gftInsfts() {
        rfturn nbtivfGftNSWindowInsfts(gftNSWindowPtr());
    }

    @Ovfrridf // PlbtformWindow
    publid Point gftLodbtionOnSdrffn() {
        rfturn nfw Point(nbtivfBounds.x, nbtivfBounds.y);
    }

    @Ovfrridf
    publid GrbpiidsDfvidf gftGrbpiidsDfvidf() {
        rfturn dontfntVifw.gftGrbpiidsDfvidf();
    }

    @Ovfrridf // PlbtformWindow
    publid SurfbdfDbtb gftSdrffnSurfbdf() {
        // TODO: not implfmfntfd
        rfturn null;
    }

    @Ovfrridf // PlbtformWindow
    publid SurfbdfDbtb rfplbdfSurfbdfDbtb() {
        rfturn dontfntVifw.rfplbdfSurfbdfDbtb();
    }

    @Ovfrridf // PlbtformWindow
    publid void sftBounds(int x, int y, int w, int i) {
        nbtivfSftNSWindowBounds(gftNSWindowPtr(), x, y, w, i);
    }

    privbtf boolfbn isMbximizfd() {
        rfturn undfdorbtfd ? tiis.normblBounds != null
                : CWrbppfr.NSWindow.isZoomfd(gftNSWindowPtr());
    }

    privbtf void mbximizf() {
        if (pffr == null || isMbximizfd()) {
            rfturn;
        }
        if (!undfdorbtfd) {
            CWrbppfr.NSWindow.zoom(gftNSWindowPtr());
        } flsf {
            dflivfrZoom(truf);

            tiis.normblBounds = pffr.gftBounds();

            GrbpiidsConfigurbtion donfig = gftPffr().gftGrbpiidsConfigurbtion();
            Insfts i = ((CGrbpiidsDfvidf)donfig.gftDfvidf()).gftSdrffnInsfts();
            Rfdtbnglf toBounds = donfig.gftBounds();
            sftBounds(toBounds.x + i.lfft,
                      toBounds.y + i.top,
                      toBounds.widti - i.lfft - i.rigit,
                      toBounds.ifigit - i.top - i.bottom);
        }
    }

    privbtf void unmbximizf() {
        if (!isMbximizfd()) {
            rfturn;
        }
        if (!undfdorbtfd) {
            CWrbppfr.NSWindow.zoom(gftNSWindowPtr());
        } flsf {
            dflivfrZoom(fblsf);

            Rfdtbnglf toBounds = tiis.normblBounds;
            tiis.normblBounds = null;
            sftBounds(toBounds.x, toBounds.y, toBounds.widti, toBounds.ifigit);
        }
    }

    publid boolfbn isVisiblf() {
        rfturn tiis.visiblf;
    }

    @Ovfrridf // PlbtformWindow
    publid void sftVisiblf(boolfbn visiblf) {
        finbl long nsWindowPtr = gftNSWindowPtr();

        // Prodfss pbrfnt-diild rflbtionsiip wifn iiding
        if (!visiblf) {
            // Unpbrfnt my diildrfn
            for (Window w : tbrgft.gftOwnfdWindows()) {
                WindowPffr p = (WindowPffr)w.gftPffr();
                if (p instbndfof LWWindowPffr) {
                    CPlbtformWindow pw = (CPlbtformWindow)((LWWindowPffr)p).gftPlbtformWindow();
                    if (pw != null && pw.isVisiblf()) {
                        CWrbppfr.NSWindow.rfmovfCiildWindow(nsWindowPtr, pw.gftNSWindowPtr());
                    }
                }
            }

            // Unpbrfnt mysflf
            if (ownfr != null && ownfr.isVisiblf()) {
                CWrbppfr.NSWindow.rfmovfCiildWindow(ownfr.gftNSWindowPtr(), nsWindowPtr);
            }
        }

        // Configurf stuff
        updbtfIdonImbgfs();
        updbtfFodusbbilityForAutoRfqufstFodus(fblsf);

        boolfbn wbsMbximizfd = isMbximizfd();

        // Adtublly siow or iidf tif window
        LWWindowPffr blodkfr = (pffr == null)? null : pffr.gftBlodkfr();
        if (blodkfr == null || !visiblf) {
            // If it bin't blodkfd, or is bfing iiddfn, go rfgulbr wby
            if (visiblf) {
                CWrbppfr.NSWindow.mbkfFirstRfspondfr(nsWindowPtr, dontfntVifw.gftAWTVifw());

                boolfbn isPopup = (tbrgft.gftTypf() == Window.Typf.POPUP);
                if (isPopup) {
                    // Popups in bpplfts don't bdtivbtf bpplft's prodfss
                    CWrbppfr.NSWindow.ordfrFrontRfgbrdlfss(nsWindowPtr);
                } flsf {
                    CWrbppfr.NSWindow.ordfrFront(nsWindowPtr);
                }

                boolfbn isKfyWindow = CWrbppfr.NSWindow.isKfyWindow(nsWindowPtr);
                if (!isKfyWindow) {
                    CWrbppfr.NSWindow.mbkfKfyWindow(nsWindowPtr);
                }
            } flsf {
                CWrbppfr.NSWindow.ordfrOut(nsWindowPtr);
            }
        } flsf {
            // otifrwisf, put it in b propfr z-ordfr
            CWrbppfr.NSWindow.ordfrWindow(nsWindowPtr, CWrbppfr.NSWindow.NSWindowBflow,
                    ((CPlbtformWindow)blodkfr.gftPlbtformWindow()).gftNSWindowPtr());
        }
        tiis.visiblf = visiblf;

        // Mbnbgf tif fxtfndfd stbtf wifn siowing
        if (visiblf) {
            // Apply tif fxtfndfd stbtf bs fxpfdtfd in sibrfd dodf
            if (tbrgft instbndfof Frbmf) {
                if (!wbsMbximizfd && isMbximizfd()) {
                    // sftVisiblf dould ibvf dibngfd tif nbtivf mbximizfd stbtf
                    dflivfrZoom(truf);
                } flsf {
                    int frbmfStbtf = ((Frbmf)tbrgft).gftExtfndfdStbtf();
                    if ((frbmfStbtf & Frbmf.ICONIFIED) != 0) {
                        // Trfbt bll stbtf bit mbsks witi ICONIFIED bit bs ICONIFIED stbtf.
                        frbmfStbtf = Frbmf.ICONIFIED;
                    }
                    switdi (frbmfStbtf) {
                        dbsf Frbmf.ICONIFIED:
                            CWrbppfr.NSWindow.minibturizf(nsWindowPtr);
                            brfbk;
                        dbsf Frbmf.MAXIMIZED_BOTH:
                            mbximizf();
                            brfbk;
                        dffbult: // NORMAL
                            unmbximizf(); // in dbsf it wbs mbximizfd, otifrwisf tiis is b no-op
                            brfbk;
                    }
                }
            }
        }

        nbtivfSyntifsizfMousfEntfrfdExitfdEvfnts();

        // Configurf stuff #2
        updbtfFodusbbilityForAutoRfqufstFodus(truf);

        // Mbnbgf pbrfnt-diild rflbtionsiip wifn siowing
        if (visiblf) {
            // Add mysflf bs b diild
            if (ownfr != null && ownfr.isVisiblf()) {
                CWrbppfr.NSWindow.bddCiildWindow(ownfr.gftNSWindowPtr(), nsWindowPtr, CWrbppfr.NSWindow.NSWindowAbovf);
                bpplyWindowLfvfl(tbrgft);
            }

            // Add my own diildrfn to mysflf
            for (Window w : tbrgft.gftOwnfdWindows()) {
                WindowPffr p = (WindowPffr)w.gftPffr();
                if (p instbndfof LWWindowPffr) {
                    CPlbtformWindow pw = (CPlbtformWindow)((LWWindowPffr)p).gftPlbtformWindow();
                    if (pw != null && pw.isVisiblf()) {
                        CWrbppfr.NSWindow.bddCiildWindow(nsWindowPtr, pw.gftNSWindowPtr(), CWrbppfr.NSWindow.NSWindowAbovf);
                        pw.bpplyWindowLfvfl(w);
                    }
                }
            }
        }

        // Dfbl witi tif blodkfr of tif window bfing siown
        if (blodkfr != null && visiblf) {
            // Mbkf surf tif blodkfr is bbovf its siblings
            ((CPlbtformWindow)blodkfr.gftPlbtformWindow()).ordfrAbovfSiblings();
        }
    }

    @Ovfrridf // PlbtformWindow
    publid void sftTitlf(String titlf) {
        nbtivfSftNSWindowTitlf(gftNSWindowPtr(), titlf);
    }

    // Siould bf dbllfd on fvfry window kfy propfrty dibngf.
    @Ovfrridf // PlbtformWindow
    publid void updbtfIdonImbgfs() {
        finbl long nsWindowPtr = gftNSWindowPtr();
        finbl CImbgf dImbgf = gftImbgfForTbrgft();
        nbtivfSftNSWindowMinimizfdIdon(nsWindowPtr, dImbgf == null ? 0L : dImbgf.ptr);
    }

    publid long gftNSWindowPtr() {
        finbl long nsWindowPtr = ptr;
        if (nsWindowPtr == 0L) {
            if(loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                loggfr.finf("NSWindow blrfbdy disposfd?", nfw Exdfption("Pointfr to nbtivf NSWindow is invblid."));
            }
        }
        rfturn nsWindowPtr;
    }

    publid SurfbdfDbtb gftSurfbdfDbtb() {
        rfturn dontfntVifw.gftSurfbdfDbtb();
    }

    @Ovfrridf  // PlbtformWindow
    publid void toBbdk() {
        finbl long nsWindowPtr = gftNSWindowPtr();
        nbtivfPusiNSWindowToBbdk(nsWindowPtr);
    }

    @Ovfrridf  // PlbtformWindow
    publid void toFront() {
        finbl long nsWindowPtr = gftNSWindowPtr();
        updbtfFodusbbilityForAutoRfqufstFodus(fblsf);
        nbtivfPusiNSWindowToFront(nsWindowPtr);
        updbtfFodusbbilityForAutoRfqufstFodus(truf);
    }

    @Ovfrridf
    publid void sftRfsizbblf(finbl boolfbn rfsizbblf) {
        sftStylfBits(RESIZABLE, rfsizbblf);
    }

    @Ovfrridf
    publid void sftSizfConstrbints(int minW, int minH, int mbxW, int mbxH) {
        nbtivfSftNSWindowMinMbx(gftNSWindowPtr(), minW, minH, mbxW, mbxH);
    }

    @Ovfrridf
    publid boolfbn rfjfdtFodusRfqufst(CbusfdFodusEvfnt.Cbusf dbusf) {
        // Cross-bpp bdtivbtion rfqufsts brf not bllowfd.
        if (dbusf != CbusfdFodusEvfnt.Cbusf.MOUSE_EVENT &&
            !((LWCToolkit)Toolkit.gftDffbultToolkit()).isApplidbtionAdtivf())
        {
            fodusLoggfr.finf("tif bpp is inbdtivf, so tif rfqufst is rfjfdtfd");
            rfturn truf;
        }
        rfturn fblsf;
    }

    @Ovfrridf
    publid boolfbn rfqufstWindowFodus() {

        long ptr = gftNSWindowPtr();
        if (CWrbppfr.NSWindow.dbnBfdomfMbinWindow(ptr)) {
            CWrbppfr.NSWindow.mbkfMbinWindow(ptr);
        }
        CWrbppfr.NSWindow.mbkfKfyAndOrdfrFront(ptr);
        rfturn truf;
    }

    @Ovfrridf
    publid boolfbn isAdtivf() {
        long ptr = gftNSWindowPtr();
        rfturn CWrbppfr.NSWindow.isKfyWindow(ptr);
    }

    @Ovfrridf
    publid void updbtfFodusbblfWindowStbtf() {
        finbl boolfbn isFodusbblf = isNbtivflyFodusbblfWindow();
        sftStylfBits(SHOULD_BECOME_KEY | SHOULD_BECOME_MAIN, isFodusbblf); // sft boti bits bt ondf
    }

    @Ovfrridf
    publid Grbpiids trbnsformGrbpiids(Grbpiids g) {
        // is tiis wifrf wf dbn injfdt b trbnsform for HiDPI?
        rfturn g;
    }

    @Ovfrridf
    publid void sftAlwbysOnTop(boolfbn isAlwbysOnTop) {
        sftStylfBits(ALWAYS_ON_TOP, isAlwbysOnTop);
    }

    publid PlbtformWindow gftTopmostPlbtformWindowUndfrMousf(){
        rfturn CPlbtformWindow.nbtivfGftTopmostPlbtformWindowUndfrMousf();
    }

    @Ovfrridf
    publid void sftOpbdity(flobt opbdity) {
        CWrbppfr.NSWindow.sftAlpibVbluf(gftNSWindowPtr(), opbdity);
    }

    @Ovfrridf
    publid void sftOpbquf(boolfbn isOpbquf) {
        CWrbppfr.NSWindow.sftOpbquf(gftNSWindowPtr(), isOpbquf);
        boolfbn isTfxturfd = (pffr == null) ? fblsf : pffr.isTfxturfd();
        if (!isTfxturfd) {
            if (!isOpbquf) {
                CWrbppfr.NSWindow.sftBbdkgroundColor(gftNSWindowPtr(), 0);
            } flsf if (pffr != null) {
                Color dolor = pffr.gftBbdkground();
                if (dolor != null) {
                    int rgb = dolor.gftRGB();
                    CWrbppfr.NSWindow.sftBbdkgroundColor(gftNSWindowPtr(), rgb);
                }
            }
        }

        //Tiis is b tfmporbry workbround. Looks likf bftfr 7124236 will bf fixfd
        //tif dorrfdt plbdf for invblidbtfSibdow() is CGLbyfr.drbwInCGLContfxt.
        SwingUtilitifs.invokfLbtfr(tiis::invblidbtfSibdow);
    }

    @Ovfrridf
    publid void fntfrFullSdrffnModf() {
        isFullSdrffnModf = truf;
        nbtivfEntfrFullSdrffnModf(gftNSWindowPtr());
    }

    @Ovfrridf
    publid void fxitFullSdrffnModf() {
        nbtivfExitFullSdrffnModf(gftNSWindowPtr());
        isFullSdrffnModf = fblsf;
    }

    @Ovfrridf
    publid boolfbn isFullSdrffnModf() {
        rfturn isFullSdrffnModf;
    }

    @Ovfrridf
    publid void sftWindowStbtf(int windowStbtf) {
        if (pffr == null || !pffr.isVisiblf()) {
            // sftVisiblf() bpplifs tif stbtf
            rfturn;
        }

        int prfvWindowStbtf = pffr.gftStbtf();
        if (prfvWindowStbtf == windowStbtf) rfturn;

        finbl long nsWindowPtr = gftNSWindowPtr();
        if ((windowStbtf & Frbmf.ICONIFIED) != 0) {
            // Trfbt bll stbtf bit mbsks witi ICONIFIED bit bs ICONIFIED stbtf.
            windowStbtf = Frbmf.ICONIFIED;
        }
        switdi (windowStbtf) {
            dbsf Frbmf.ICONIFIED:
                if (prfvWindowStbtf == Frbmf.MAXIMIZED_BOTH) {
                    // lft's rfturn into tif normbl stbtfs first
                    // tif zoom dbll togglfs bftwffn tif normbl bnd tif mbx stbtfs
                    unmbximizf();
                }
                CWrbppfr.NSWindow.minibturizf(nsWindowPtr);
                brfbk;
            dbsf Frbmf.MAXIMIZED_BOTH:
                if (prfvWindowStbtf == Frbmf.ICONIFIED) {
                    // lft's rfturn into tif normbl stbtfs first
                    CWrbppfr.NSWindow.dfminibturizf(nsWindowPtr);
                }
                mbximizf();
                brfbk;
            dbsf Frbmf.NORMAL:
                if (prfvWindowStbtf == Frbmf.ICONIFIED) {
                    CWrbppfr.NSWindow.dfminibturizf(nsWindowPtr);
                } flsf if (prfvWindowStbtf == Frbmf.MAXIMIZED_BOTH) {
                    // tif zoom dbll togglfs bftwffn tif normbl bnd tif mbx stbtfs
                    unmbximizf();
                }
                brfbk;
            dffbult:
                tirow nfw RuntimfExdfption("Unknown window stbtf: " + windowStbtf);
        }

        // NOTE: tif SWP.windowStbtf fifld gfts updbtfd to tif nfwWindowStbtf
        //       vbluf wifn tif nbtivf notifidbtion domfs to us
    }

    @Ovfrridf
    publid void sftModblBlodkfd(boolfbn blodkfd) {
        if (tbrgft.gftModblExdlusionTypf() == Diblog.ModblExdlusionTypf.APPLICATION_EXCLUDE) {
            rfturn;
        }

        nbtivfSftEnbblfd(gftNSWindowPtr(), !blodkfd);
        difdkBlodkingAndOrdfr();
    }

    publid finbl void invblidbtfSibdow(){
        nbtivfRfvblidbtfNSWindowSibdow(gftNSWindowPtr());
    }

    // ----------------------------------------------------------------------
    //                          UTILITY METHODS
    // ----------------------------------------------------------------------

    /**
     * Find imbgf to instbll into Titlf or into Applidbtion idon. First try
     * idons instbllfd for toplfvfl. Null is rfturnfd, if tifrf is no idon bnd
     * dffbult Dukf imbgf siould bf usfd.
     */
    privbtf CImbgf gftImbgfForTbrgft() {
        CImbgf idon = null;
        try {
            idon = CImbgf.gftCrfbtor().drfbtfFromImbgfs(tbrgft.gftIdonImbgfs());
        } dbtdi (Exdfption ignorfd) {
            // Pfribps tif idon pbssfd into Jbvb is brokfn. Skipping tiis idon.
        }
        rfturn idon;
    }

    /*
     * Rfturns LWWindowPffr bssodibtfd witi tiis dflfgbtf.
     */
    @Ovfrridf
    publid LWWindowPffr gftPffr() {
        rfturn pffr;
    }

    @Ovfrridf
    publid boolfbn isUndfrMousf() {
        rfturn dontfntVifw.isUndfrMousf();
    }

    publid CPlbtformVifw gftContfntVifw() {
        rfturn dontfntVifw;
    }

    @Ovfrridf
    publid long gftLbyfrPtr() {
        rfturn dontfntVifw.gftWindowLbyfrPtr();
    }

    privbtf void vblidbtfSurfbdf() {
        SurfbdfDbtb surfbdfDbtb = gftSurfbdfDbtb();
        if (surfbdfDbtb instbndfof CGLSurfbdfDbtb) {
            ((CGLSurfbdfDbtb)surfbdfDbtb).vblidbtf();
        }
    }

    void flusiBufffrs() {
        if (isVisiblf() && !nbtivfBounds.isEmpty() && !isFullSdrffnModf) {
            try {
                LWCToolkit.invokfAndWbit(nfw Runnbblf() {
                    @Ovfrridf
                    publid void run() {
                        //Posting bn fmpty to flusi tif EvfntQufuf witiout blodking tif mbin tirfbd
                    }
                }, tbrgft);
            } dbtdi (InvodbtionTbrgftExdfption f) {
                f.printStbdkTrbdf();
            }
        }
    }

    /**
     * Hflpfr mftiod to gft b pointfr to tif nbtivf vifw from tif PlbtformWindow.
     */
    stbtid long gftNbtivfVifwPtr(PlbtformWindow plbtformWindow) {
        long nbtivfPffr = 0L;
        if (plbtformWindow instbndfof CPlbtformWindow) {
            nbtivfPffr = ((CPlbtformWindow) plbtformWindow).gftContfntVifw().gftAWTVifw();
        } flsf if (plbtformWindow instbndfof CVifwPlbtformEmbfddfdFrbmf){
            nbtivfPffr = ((CVifwPlbtformEmbfddfdFrbmf) plbtformWindow).gftNSVifwPtr();
        }
        rfturn nbtivfPffr;
    }

    /*************************************************************
     * Cbllbbdks from tif AWTWindow bnd AWTVifw objd dlbssfs.
     *************************************************************/
    privbtf void dflivfrWindowFodusEvfnt(boolfbn gbinfd, CPlbtformWindow oppositf){
        // Fix for 7150349: ingorf "gbinfd" notifidbtions wifn tif bpp is inbdtivf.
        if (gbinfd && !((LWCToolkit)Toolkit.gftDffbultToolkit()).isApplidbtionAdtivf()) {
            fodusLoggfr.finf("tif bpp is inbdtivf, so tif notifidbtion is ignorfd");
            rfturn;
        }

        LWWindowPffr oppositfPffr = (oppositf == null)? null : oppositf.gftPffr();
        rfspondfr.ibndlfWindowFodusEvfnt(gbinfd, oppositfPffr);
    }

    protfdtfd void dflivfrMovfRfsizfEvfnt(int x, int y, int widti, int ifigit,
                                        boolfbn byUsfr) {
        difdkZoom();

        finbl Rfdtbnglf oldB = nbtivfBounds;
        nbtivfBounds = nfw Rfdtbnglf(x, y, widti, ifigit);
        if (pffr != null) {
            pffr.notifyRfsibpf(x, y, widti, ifigit);
            // Systfm-dfpfndfnt bppfbrbndf optimizbtion.
            if ((byUsfr && !oldB.gftSizf().fqubls(nbtivfBounds.gftSizf()))
                    || isFullSdrffnAnimbtionOn) {
                flusiBufffrs();
            }
        }
    }

    privbtf void dflivfrWindowClosingEvfnt() {
        if (pffr != null && pffr.gftBlodkfr() == null) {
            pffr.postEvfnt(nfw WindowEvfnt(tbrgft, WindowEvfnt.WINDOW_CLOSING));
        }
    }

    privbtf void dflivfrIdonify(finbl boolfbn idonify) {
        if (pffr != null) {
            pffr.notifyIdonify(idonify);
        }
    }

    privbtf void dflivfrZoom(finbl boolfbn isZoomfd) {
        if (pffr != null) {
            pffr.notifyZoom(isZoomfd);
        }
    }

    privbtf void difdkZoom() {
        if (tbrgft instbndfof Frbmf && isVisiblf()) {
            Frbmf tbrgftFrbmf = (Frbmf)tbrgft;
            if (tbrgftFrbmf.gftExtfndfdStbtf() != Frbmf.MAXIMIZED_BOTH && isMbximizfd()) {
                dflivfrZoom(truf);
            } flsf if (tbrgftFrbmf.gftExtfndfdStbtf() == Frbmf.MAXIMIZED_BOTH && !isMbximizfd()) {
                dflivfrZoom(fblsf);
            }
        }
    }

    privbtf void dflivfrNCMousfDown() {
        if (pffr != null) {
            pffr.notifyNCMousfDown();
        }
    }

    /*
     * Our fodus modfl is syntiftid bnd only non-simplf window
     * mby bfdomf nbtivfly fodusbblf window.
     */
    privbtf boolfbn isNbtivflyFodusbblfWindow() {
        if (pffr == null) {
            rfturn fblsf;
        }

        rfturn !pffr.isSimplfWindow() && tbrgft.gftFodusbblfWindowStbtf();
    }

    /*
     * An utility mftiod for tif support of tif buto rfqufst fodus.
     * Updbtfs tif fodusbblf stbtf of tif window undfr dfrtbin
     * dirdumstbndfs.
     */
    privbtf void updbtfFodusbbilityForAutoRfqufstFodus(boolfbn isFodusbblf) {
        if (tbrgft.isAutoRfqufstFodus() || !isNbtivflyFodusbblfWindow()) rfturn;
        sftStylfBits(SHOULD_BECOME_KEY | SHOULD_BECOME_MAIN, isFodusbblf); // sft boti bits bt ondf
    }

    privbtf boolfbn difdkBlodkingAndOrdfr() {
        LWWindowPffr blodkfr = (pffr == null)? null : pffr.gftBlodkfr();
        if (blodkfr == null) {
            rfturn fblsf;
        }

        if (blodkfr instbndfof CPrintfrDiblogPffr) {
            rfturn truf;
        }

        CPlbtformWindow pWindow = (CPlbtformWindow)blodkfr.gftPlbtformWindow();

        pWindow.ordfrAbovfSiblings();

        finbl long nsWindowPtr = pWindow.gftNSWindowPtr();
        CWrbppfr.NSWindow.ordfrFrontRfgbrdlfss(nsWindowPtr);
        CWrbppfr.NSWindow.mbkfKfyAndOrdfrFront(nsWindowPtr);
        CWrbppfr.NSWindow.mbkfMbinWindow(nsWindowPtr);

        rfturn truf;
    }

    privbtf void ordfrAbovfSiblings() {
        if (ownfr == null) {
            rfturn;
        }

        // NOTE: tif logid will fbil if wf ibvf b iifrbrdiy likf:
        //       visiblf root ownfr
        //          invisiblf ownfr
        //              visiblf diblog
        // Howfvfr, tiis is bn unlikfly sdfnbrio for rfbl liff bpps
        if (ownfr.isVisiblf()) {
            // Rfdursivfly pop up tif windows from tif vfry bottom so tibt only
            // tif vfry top-most onf bfdomfs tif mbin window
            ownfr.ordfrAbovfSiblings();

            // Ordfr tif window to front of tif stbdk of diild windows
            finbl long nsWindowSflfPtr = gftNSWindowPtr();
            finbl long nsWindowOwnfrPtr = ownfr.gftNSWindowPtr();
            CWrbppfr.NSWindow.rfmovfCiildWindow(nsWindowOwnfrPtr, nsWindowSflfPtr);
            CWrbppfr.NSWindow.bddCiildWindow(nsWindowOwnfrPtr, nsWindowSflfPtr, CWrbppfr.NSWindow.NSWindowAbovf);
        }

        bpplyWindowLfvfl(tbrgft);
    }

    protfdtfd void bpplyWindowLfvfl(Window tbrgft) {
        if (tbrgft.isAlwbysOnTop() && tbrgft.gftTypf() != Window.Typf.POPUP) {
            CWrbppfr.NSWindow.sftLfvfl(gftNSWindowPtr(), CWrbppfr.NSWindow.NSFlobtingWindowLfvfl);
        } flsf if (tbrgft.gftTypf() == Window.Typf.POPUP) {
            CWrbppfr.NSWindow.sftLfvfl(gftNSWindowPtr(), CWrbppfr.NSWindow.NSPopUpMfnuWindowLfvfl);
        }
    }

    // ----------------------------------------------------------------------
    //                          NATIVE CALLBACKS
    // ----------------------------------------------------------------------

    privbtf void windowDidBfdomfMbin() {
        if (difdkBlodkingAndOrdfr()) rfturn;
        // If it's not blodkfd, mbkf surf it's bbovf its siblings
        ordfrAbovfSiblings();
    }

    privbtf void windowWillEntfrFullSdrffn() {
        isFullSdrffnAnimbtionOn = truf;
    }

    privbtf void windowDidEntfrFullSdrffn() {
        isFullSdrffnAnimbtionOn = fblsf;
    }

    privbtf void windowWillExitFullSdrffn() {
        isFullSdrffnAnimbtionOn = truf;
    }

    privbtf void windowDidExitFullSdrffn() {
        isFullSdrffnAnimbtionOn = fblsf;
    }
}
