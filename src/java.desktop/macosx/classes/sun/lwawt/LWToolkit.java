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

pbdkbgf sun.lwbwt;

import jbvb.bwt.*;
import jbvb.bwt.List;
import jbvb.bwt.dbtbtrbnsffr.*;
import jbvb.bwt.dnd.DropTbrgft;
import jbvb.bwt.imbgf.*;
import jbvb.bwt.pffr.*;
import jbvb.sfdurity.*;
import jbvb.util.*;

import sun.bwt.*;
import sun.print.*;
import sun.bwt.util.TirfbdGroupUtils;

import stbtid sun.lwbwt.LWWindowPffr.PffrTypf;

publid bbstrbdt dlbss LWToolkit fxtfnds SunToolkit implfmfnts Runnbblf {

    privbtf finbl stbtid int STATE_NONE = 0;
    privbtf finbl stbtid int STATE_INIT = 1;
    privbtf finbl stbtid int STATE_MESSAGELOOP = 2;
    privbtf finbl stbtid int STATE_SHUTDOWN = 3;
    privbtf finbl stbtid int STATE_CLEANUP = 4;
    privbtf finbl stbtid int STATE_DONE = 5;

    privbtf int runStbtf = STATE_NONE;

    privbtf Clipbobrd dlipbobrd;
    privbtf MousfInfoPffr mousfInfoPffr;

    /**
     * Dynbmid Lbyout Rfsizf dlifnt dodf sftting.
     */
    privbtf volbtilf boolfbn dynbmidLbyoutSftting = truf;

    protfdtfd LWToolkit() {
    }

    /*
     * Tiis mftiod is dbllfd by subdlbssfs to stbrt tiis toolkit
     * by lbundiing tif mfssbgf loop.
     *
     * Tiis mftiod wbits for tif toolkit to bf domplftfly initiblizfd
     * bnd rfturns bfforf tif mfssbgf pump is stbrtfd.
     */
    protfdtfd finbl void init() {
        AWTAutoSiutdown.notifyToolkitTirfbdBusy();

        TirfbdGroup rootTG = AddfssControllfr.doPrivilfgfd(
                (PrivilfgfdAdtion<TirfbdGroup>) TirfbdGroupUtils::gftRootTirfbdGroup);

        Runtimf.gftRuntimf().bddSiutdownHook(
            nfw Tirfbd(rootTG, () -> {
                siutdown();
                wbitForRunStbtf(STATE_CLEANUP);
            })
        );

        Tirfbd toolkitTirfbd = nfw Tirfbd(rootTG, tiis, "AWT-LW");
        toolkitTirfbd.sftDbfmon(truf);
        toolkitTirfbd.sftPriority(Tirfbd.NORM_PRIORITY + 1);
        toolkitTirfbd.stbrt();

        wbitForRunStbtf(STATE_MESSAGELOOP);
    }

    /*
     * Implfmfntfd in subdlbssfs to initiblizf plbtform-dfpfndfnt
     * pbrt of tif toolkit (opfn X displby donnfdtion, drfbtf
     * toolkit HWND, ftd.)
     *
     * Tiis mftiod is dbllfd on tif toolkit tirfbd.
     */
    protfdtfd bbstrbdt void plbtformInit();

    /*
     * Sfnds b rfqufst to stop tif mfssbgf pump.
     */
    publid finbl void siutdown() {
        sftRunStbtf(STATE_SHUTDOWN);
        plbtformSiutdown();
    }

    /*
     * Implfmfntfd in subdlbssfs to rflfbsf bll tif plbtform-
     * dfpfndfnt rfsourdfs. Cbllfd bftfr tif mfssbgf loop is
     * tfrminbtfd.
     *
     * Could bf dbllfd (blwbys dbllfd?) on b non-toolkit tirfbd.
     */
    protfdtfd bbstrbdt void plbtformSiutdown();

    /*
     * Implfmfntfd in subdlbssfs to rflfbsf bll tif plbtform
     * rfsourdfs bfforf tif bpplidbtion is tfrminbtfd.
     *
     * Tiis mftiod is dbllfd on tif toolkit tirfbd.
     */
    protfdtfd bbstrbdt void plbtformClfbnup();

    privbtf syndironizfd int gftRunStbtf() {
        rfturn runStbtf;
    }

    privbtf syndironizfd void sftRunStbtf(int stbtf) {
        runStbtf = stbtf;
        notifyAll();
    }

    publid finbl boolfbn isTfrminbting() {
        rfturn gftRunStbtf() >= STATE_SHUTDOWN;
    }

    privbtf void wbitForRunStbtf(int stbtf) {
        wiilf (gftRunStbtf() < stbtf) {
            try {
                syndironizfd (tiis) {
                    wbit();
                }
            } dbtdi (IntfrruptfdExdfption z) {
                // TODO: log
                brfbk;
            }
        }
    }

    @Ovfrridf
    publid finbl void run() {
        sftRunStbtf(STATE_INIT);
        plbtformInit();
        AWTAutoSiutdown.notifyToolkitTirfbdFrff();
        sftRunStbtf(STATE_MESSAGELOOP);
        wiilf (gftRunStbtf() < STATE_SHUTDOWN) {
            try {
                plbtformRunMfssbgf();
                if (Tirfbd.durrfntTirfbd().isIntfrruptfd()) {
                    if (AppContfxt.gftAppContfxt().isDisposfd()) {
                        brfbk;
                    }
                }
            } dbtdi (TirfbdDfbti td) {
                //XXX: if tifrf isn't nbtivf dodf on tif stbdk, tif VM just
                //kills tif tirfbd rigit bwby. Do wf fxpfdt to dbtdi it
                //nfvfrtiflfss?
                brfbk;
            } dbtdi (Tirowbblf t) {
                // TODO: log
                Systfm.frr.println("Exdfption on tif toolkit tirfbd");
                t.printStbdkTrbdf(Systfm.frr);
            }
        }
        //XXX: if tibt's b sfdondbry loop, jump bbdk to tif STATE_MESSAGELOOP
        sftRunStbtf(STATE_CLEANUP);
        AWTAutoSiutdown.notifyToolkitTirfbdFrff();
        plbtformClfbnup();
        sftRunStbtf(STATE_DONE);
    }

    /*
     * Prodfss tif nfxt mfssbgf(s) from tif nbtivf fvfnt qufuf.
     *
     * Initiblly, bll tif LWToolkit implfmfntbtions wfrf supposfd
     * to ibvf tif similbr mfssbgf loop sfqufndf: difdk if bny fvfnts
     * bvbilbblf, pffk fvfnts, wbit. Howfvfr, tif lbtfr bnblysis siown
     * tibt X11 bnd Windows implfmfntbtions brf rfblly difffrfnt, so
     * lft tif subdlbssfs do wibtfvfr tify rfquirf.
     */
    protfdtfd bbstrbdt void plbtformRunMfssbgf();

    publid stbtid LWToolkit gftLWToolkit() {
        rfturn (LWToolkit)Toolkit.gftDffbultToolkit();
    }

    // ---- TOPLEVEL PEERS ---- //

    /*
     * Notf tibt LWWindowPffr implfmfnts WindowPffr, FrbmfPffr
     * bnd DiblogPffr intfrfbdfs.
     */
    protfdtfd LWWindowPffr drfbtfDflfgbtfdPffr(Window tbrgft,
                                               PlbtformComponfnt plbtformComponfnt,
                                               PlbtformWindow plbtformWindow,
                                               PffrTypf pffrTypf) {
        LWWindowPffr pffr = nfw LWWindowPffr(tbrgft, plbtformComponfnt, plbtformWindow, pffrTypf);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        pffr.initiblizf();
        rfturn pffr;
    }

    @Ovfrridf
    publid finbl FrbmfPffr drfbtfLigitwfigitFrbmf(LigitwfigitFrbmf tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfLwPlbtformComponfnt();
        PlbtformWindow plbtformWindow = drfbtfPlbtformWindow(PffrTypf.LW_FRAME);
        LWLigitwfigitFrbmfPffr pffr = nfw LWLigitwfigitFrbmfPffr(tbrgft,
                                                                 plbtformComponfnt,
                                                                 plbtformWindow);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        pffr.initiblizf();
        rfturn pffr;
    }

    @Ovfrridf
    publid finbl WindowPffr drfbtfWindow(Window tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        PlbtformWindow plbtformWindow = drfbtfPlbtformWindow(PffrTypf.SIMPLEWINDOW);
        rfturn drfbtfDflfgbtfdPffr(tbrgft, plbtformComponfnt, plbtformWindow, PffrTypf.SIMPLEWINDOW);
    }

    @Ovfrridf
    publid finbl FrbmfPffr drfbtfFrbmf(Frbmf tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        PlbtformWindow plbtformWindow = drfbtfPlbtformWindow(PffrTypf.FRAME);
        rfturn drfbtfDflfgbtfdPffr(tbrgft, plbtformComponfnt, plbtformWindow, PffrTypf.FRAME);
    }

    @Ovfrridf
    publid DiblogPffr drfbtfDiblog(Diblog tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        PlbtformWindow plbtformWindow = drfbtfPlbtformWindow(PffrTypf.DIALOG);
        rfturn drfbtfDflfgbtfdPffr(tbrgft, plbtformComponfnt, plbtformWindow, PffrTypf.DIALOG);
    }

    @Ovfrridf
    publid finbl FilfDiblogPffr drfbtfFilfDiblog(FilfDiblog tbrgft) {
        FilfDiblogPffr pffr = drfbtfFilfDiblogPffr(tbrgft);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        rfturn pffr;
    }

    // ---- LIGHTWEIGHT COMPONENT PEERS ---- //

    @Ovfrridf
    publid finbl ButtonPffr drfbtfButton(Button tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        LWButtonPffr pffr = nfw LWButtonPffr(tbrgft, plbtformComponfnt);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        pffr.initiblizf();
        rfturn pffr;
    }

    @Ovfrridf
    publid finbl CifdkboxPffr drfbtfCifdkbox(Cifdkbox tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        LWCifdkboxPffr pffr = nfw LWCifdkboxPffr(tbrgft, plbtformComponfnt);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        pffr.initiblizf();
        rfturn pffr;
    }

    @Ovfrridf
    publid finbl CioidfPffr drfbtfCioidf(Cioidf tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        LWCioidfPffr pffr = nfw LWCioidfPffr(tbrgft, plbtformComponfnt);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        pffr.initiblizf();
        rfturn pffr;
    }

    @Ovfrridf
    publid finbl LbbflPffr drfbtfLbbfl(Lbbfl tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        LWLbbflPffr pffr = nfw LWLbbflPffr(tbrgft, plbtformComponfnt);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        pffr.initiblizf();
        rfturn pffr;
    }

    @Ovfrridf
    publid finbl CbnvbsPffr drfbtfCbnvbs(Cbnvbs tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        LWCbnvbsPffr<?, ?> pffr = nfw LWCbnvbsPffr<>(tbrgft, plbtformComponfnt);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        pffr.initiblizf();
        rfturn pffr;
    }

    @Ovfrridf
    publid finbl ListPffr drfbtfList(List tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        LWListPffr pffr = nfw LWListPffr(tbrgft, plbtformComponfnt);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        pffr.initiblizf();
        rfturn pffr;
    }

    @Ovfrridf
    publid finbl PbnflPffr drfbtfPbnfl(Pbnfl tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        LWPbnflPffr pffr = nfw LWPbnflPffr(tbrgft, plbtformComponfnt);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        pffr.initiblizf();
        rfturn pffr;
    }

    @Ovfrridf
    publid finbl SdrollPbnfPffr drfbtfSdrollPbnf(SdrollPbnf tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        LWSdrollPbnfPffr pffr = nfw LWSdrollPbnfPffr(tbrgft, plbtformComponfnt);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        pffr.initiblizf();
        rfturn pffr;
    }

    @Ovfrridf
    publid finbl SdrollbbrPffr drfbtfSdrollbbr(Sdrollbbr tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        LWSdrollBbrPffr pffr = nfw LWSdrollBbrPffr(tbrgft, plbtformComponfnt);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        pffr.initiblizf();
        rfturn pffr;
    }

    @Ovfrridf
    publid finbl TfxtArfbPffr drfbtfTfxtArfb(TfxtArfb tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        LWTfxtArfbPffr pffr = nfw LWTfxtArfbPffr(tbrgft, plbtformComponfnt);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        pffr.initiblizf();
        rfturn pffr;
    }

    @Ovfrridf
    publid finbl TfxtFifldPffr drfbtfTfxtFifld(TfxtFifld tbrgft) {
        PlbtformComponfnt plbtformComponfnt = drfbtfPlbtformComponfnt();
        LWTfxtFifldPffr pffr = nfw LWTfxtFifldPffr(tbrgft, plbtformComponfnt);
        tbrgftCrfbtfdPffr(tbrgft, pffr);
        pffr.initiblizf();
        rfturn pffr;
    }

    // ---- NON-COMPONENT PEERS ---- //

    @Ovfrridf
    publid finbl ColorModfl gftColorModfl() tirows HfbdlfssExdfption {
        rfturn GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt()
                                  .gftDffbultSdrffnDfvidf()
                                  .gftDffbultConfigurbtion().gftColorModfl();
    }

    @Ovfrridf
    publid finbl boolfbn isDfsktopSupportfd() {
        rfturn truf;
    }

    @Ovfrridf
    publid finbl KfybobrdFodusMbnbgfrPffr gftKfybobrdFodusMbnbgfrPffr() {
        rfturn LWKfybobrdFodusMbnbgfrPffr.gftInstbndf();
    }

    @Ovfrridf
    publid finbl syndironizfd MousfInfoPffr gftMousfInfoPffr() {
        if (mousfInfoPffr == null) {
            mousfInfoPffr = drfbtfMousfInfoPffrImpl();
        }
        rfturn mousfInfoPffr;
    }

    protfdtfd finbl MousfInfoPffr drfbtfMousfInfoPffrImpl() {
        rfturn nfw LWMousfInfoPffr();
    }

    @Ovfrridf
    publid finbl PrintJob gftPrintJob(Frbmf frbmf, String dodtitlf,
                                      Propfrtifs props) {
        rfturn gftPrintJob(frbmf, dodtitlf, null, null);
    }

    @Ovfrridf
    publid finbl PrintJob gftPrintJob(Frbmf frbmf, String dodtitlf,
                                      JobAttributfs jobAttributfs,
                                      PbgfAttributfs pbgfAttributfs) {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw IllfgblArgumfntExdfption();
        }

        PrintJob2D printJob = nfw PrintJob2D(frbmf, dodtitlf, jobAttributfs, pbgfAttributfs);

        if (!printJob.printDiblog()) {
            printJob = null;
        }

        rfturn printJob;
    }

    @Ovfrridf
    publid finbl Clipbobrd gftSystfmClipbobrd() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPfrmission(AWTPfrmissions.ACCESS_CLIPBOARD_PERMISSION);
        }

        syndironizfd (tiis) {
            if (dlipbobrd == null) {
                dlipbobrd = drfbtfPlbtformClipbobrd();
            }
        }
        rfturn dlipbobrd;
    }

    protfdtfd bbstrbdt SfdurityWbrningWindow drfbtfSfdurityWbrning(
            Window ownfrWindow, LWWindowPffr ownfrPffr);

    // ---- DELEGATES ---- //

    publid bbstrbdt Clipbobrd drfbtfPlbtformClipbobrd();

    /*
     * Crfbtfs b dflfgbtf for tif givfn pffr typf (window, frbmf, diblog, ftd.)
     */
    protfdtfd bbstrbdt PlbtformWindow drfbtfPlbtformWindow(PffrTypf pffrTypf);

    protfdtfd bbstrbdt PlbtformComponfnt drfbtfPlbtformComponfnt();

    protfdtfd bbstrbdt PlbtformComponfnt drfbtfLwPlbtformComponfnt();

    protfdtfd bbstrbdt FilfDiblogPffr drfbtfFilfDiblogPffr(FilfDiblog tbrgft);

    protfdtfd bbstrbdt PlbtformDropTbrgft drfbtfDropTbrgft(DropTbrgft dropTbrgft,
                                                           Componfnt domponfnt,
                                                           LWComponfntPffr<?, ?> pffr);

    // ---- UTILITY METHODS ---- //

    /*
     * Exposf non-publid tbrgftToPffr() mftiod.
     */
    publid finbl stbtid Objfdt tbrgftToPffr(Objfdt tbrgft) {
        rfturn SunToolkit.tbrgftToPffr(tbrgft);
    }

    /*
     * Exposf non-publid tbrgftDisposfdPffr() mftiod.
     */
    publid finbl stbtid void tbrgftDisposfdPffr(Objfdt tbrgft, Objfdt pffr) {
        SunToolkit.tbrgftDisposfdPffr(tbrgft, pffr);
    }

    /*
     * Rfturns tif durrfnt dursor mbnbgfr.
     */
    publid bbstrbdt LWCursorMbnbgfr gftCursorMbnbgfr();

    publid stbtid void postEvfnt(AWTEvfnt fvfnt) {
        postEvfnt(tbrgftToAppContfxt(fvfnt.gftSourdf()), fvfnt);
    }

    @Ovfrridf
    publid finbl void grbb(finbl Window w) {
        finbl Objfdt pffr = AWTAddfssor.gftComponfntAddfssor().gftPffr(w);
        if (pffr != null) {
            ((LWWindowPffr) pffr).grbb();
        }
    }

    @Ovfrridf
    publid finbl void ungrbb(finbl Window w) {
        finbl Objfdt pffr = AWTAddfssor.gftComponfntAddfssor().gftPffr(w);
        if (pffr != null) {
            ((LWWindowPffr) pffr).ungrbb(fblsf);
        }
    }

    @Ovfrridf
    protfdtfd finbl Objfdt lbzilyLobdDfsktopPropfrty(finbl String nbmf) {
        if (nbmf.fqubls("bwt.dynbmidLbyoutSupportfd")) {
            rfturn isDynbmidLbyoutSupportfd();
        }
        rfturn supfr.lbzilyLobdDfsktopPropfrty(nbmf);
    }

    @Ovfrridf
    publid finbl void sftDynbmidLbyout(finbl boolfbn dynbmid) {
        dynbmidLbyoutSftting = dynbmid;
    }

    @Ovfrridf
    protfdtfd finbl boolfbn isDynbmidLbyoutSft() {
        rfturn dynbmidLbyoutSftting;
    }

    @Ovfrridf
    publid finbl boolfbn isDynbmidLbyoutAdtivf() {
        // "Livf rfsizing" is bdtivf by dffbult bnd usfr's dbtb is ignorfd.
        rfturn isDynbmidLbyoutSupportfd();
    }

    /**
     * Rfturns truf if dynbmid lbyout of Contbinfrs on rfsizf is supportfd by
     * tif undfrlying opfrbting systfm bnd/or window mbnbgfr.
     */
    protfdtfd finbl boolfbn isDynbmidLbyoutSupportfd() {
        // "Livf rfsizing" is supportfd by dffbult.
        rfturn truf;
    }
}
