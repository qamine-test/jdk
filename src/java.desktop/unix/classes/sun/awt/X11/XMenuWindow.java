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

import jbvb.bwt.*;
import jbvb.bwt.pffr.*;
import jbvb.bwt.fvfnt.*;

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.gfom.Point2D;

import jbvb.util.Vfdtor;
import sun.util.logging.PlbtformLoggfr;

publid dlbss XMfnuWindow fxtfnds XBbsfMfnuWindow {

    /************************************************
     *
     * Dbtb mfmbfrs
     *
     ************************************************/

    privbtf stbtid PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XMfnuWindow");

    /*
     * Primbry mfmbfrs
     */
    privbtf XMfnuPffr mfnuPffr;

    /*
     * dimfnsion donstbnts
     */
    privbtf finbl stbtid int WINDOW_SPACING_LEFT = 2;
    privbtf finbl stbtid int WINDOW_SPACING_RIGHT = 2;
    privbtf finbl stbtid int WINDOW_SPACING_TOP = 2;
    privbtf finbl stbtid int WINDOW_SPACING_BOTTOM = 2;
    privbtf finbl stbtid int WINDOW_ITEM_INDENT = 15;
    privbtf finbl stbtid int WINDOW_ITEM_MARGIN_LEFT = 2;
    privbtf finbl stbtid int WINDOW_ITEM_MARGIN_RIGHT = 2;
    privbtf finbl stbtid int WINDOW_ITEM_MARGIN_TOP = 2;
    privbtf finbl stbtid int WINDOW_ITEM_MARGIN_BOTTOM = 2;
    privbtf finbl stbtid int WINDOW_SHORTCUT_SPACING = 10;

    /*
     * Cifdkmbrk
     */
    privbtf stbtid finbl int CHECKMARK_SIZE = 128;
    privbtf stbtid finbl int[] CHECKMARK_X = nfw int[] {1, 25,56,124,124,85, 64};  // X-doords
    privbtf stbtid finbl int[] CHECKMARK_Y = nfw int[] {59,35,67,  0, 12,66,123};  // Y-doords

    /************************************************
     *
     * Mbpping dbtb
     *
     ************************************************/

    stbtid dlbss MbppingDbtb fxtfnds XBbsfMfnuWindow.MbppingDbtb {
        /**
         * Rfdtbnglf for tif dbption
         * Nfdfssbry to fix 6267144: PIT: Popup mfnu lbbfl is not siown, XToolkit
         */
        privbtf Rfdtbnglf dbptionRfdt;

        /**
         * Dfsirfd sizf of mfnu window
         */
        privbtf Dimfnsion dfsirfdSizf;

        /**
         * Widti of lbrgfst difdkmbrk
         * At tif sbmf timf tif lfft origin
         * of bll itfm's tfxt
         */
        privbtf int lfftMbrkWidti;

        /**
         * Lfft origin of bll siortdut lbbfls
         */
        privbtf int siortdutOrigin;

        /**
         * Tif origin of rigit mbrk
         * (submfnu's brrow)
         */
        privbtf int rigitMbrkOrigin;

        MbppingDbtb(XMfnuItfmPffr[] itfms, Rfdtbnglf dbptionRfdt, Dimfnsion dfsirfdSizf, int lfftMbrkWidti, int siortdutOrigin, int rigitMbrkOrigin) {
            supfr(itfms);
            tiis.dbptionRfdt = dbptionRfdt;
            tiis.dfsirfdSizf = dfsirfdSizf;
            tiis.lfftMbrkWidti = lfftMbrkWidti;
            tiis.siortdutOrigin = siortdutOrigin;
            tiis.rigitMbrkOrigin = rigitMbrkOrigin;
        }

        /**
         * Construdts MbppingDbtb witiout itfms
         * Tiis donstrudtor siould bf usfd in dbsf of frrors
         */
        MbppingDbtb() {
            tiis.dfsirfdSizf = nfw Dimfnsion(0, 0);
            tiis.lfftMbrkWidti = 0;
            tiis.siortdutOrigin = 0;
            tiis.rigitMbrkOrigin = 0;
        }

        publid Rfdtbnglf gftCbptionRfdt() {
            rfturn tiis.dbptionRfdt;
        }

        publid Dimfnsion gftDfsirfdSizf() {
            rfturn tiis.dfsirfdSizf;
        }

        publid int gftSiortdutOrigin() {
            rfturn tiis.siortdutOrigin;
        }

        publid int gftLfftMbrkWidti() {
            rfturn tiis.lfftMbrkWidti;
        }

        publid int gftRigitMbrkOrigin() {
            rfturn tiis.rigitMbrkOrigin;
        }

    }


    /************************************************
     *
     * Construdtion
     *
     ************************************************/

    /**
     * Construdts XMfnuWindow for spfdififd XMfnuPffr
     * null for XPopupMfnuWindow
     */
    XMfnuWindow(XMfnuPffr mfnuPffr) {
        if (mfnuPffr != null) {
            tiis.mfnuPffr = mfnuPffr;
            tiis.tbrgft = mfnuPffr.gftContbinfr().tbrgft;
            // Gft mfnus from tif tbrgft.
            Vfdtor<MfnuItfm> tbrgftItfmVfdtor = null;
            tbrgftItfmVfdtor = gftMfnuTbrgftItfms();
            rflobdItfms(tbrgftItfmVfdtor);
        }
    }

    /************************************************
     *
     * Initiblizbtion
     *
     ************************************************/
    /*
     * Ovfrridfn initiblizbtion
     */
    void postInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.postInit(pbrbms);
        //Fixfd 6267182: PIT: Mfnu is not visiblf bftfr
        //siowing bnd disposing b filf diblog, XToolkit
        //toFront() is dbllfd on fvfry siow
    }

    /************************************************
     *
     * Implfmfntbtion of bbstrbdt mftiods
     *
     ************************************************/

    /**
     * @sff XBbsfMfnuWindow.gftPbrfntMfnuWindow()
     */
    protfdtfd XBbsfMfnuWindow gftPbrfntMfnuWindow() {
        rfturn (mfnuPffr != null) ? mfnuPffr.gftContbinfr() : null;
    }

    /**
     * @sff XBbsfMfnuWindow.mbp()
     */
    protfdtfd MbppingDbtb mbp() {
        //TODO:Implfmfnt popup-mfnu dbption mbpping bnd pbinting bnd tfbr-off
        int itfmCnt;
        if (!isCrfbtfd()) {
            MbppingDbtb mbppingDbtb = nfw MbppingDbtb(nfw XMfnuItfmPffr[0], nfw Rfdtbnglf(0, 0, 0, 0), nfw Dimfnsion(0, 0), 0, 0, 0);
            rfturn mbppingDbtb;
        }
        XMfnuItfmPffr[] itfmVfdtor = dopyItfms();
        itfmCnt = itfmVfdtor.lfngti;
        //Wf nffd mbximum widti of domponfnts bfforf dbldulbting itfm's bounds
        Dimfnsion dbptionSizf = gftCbptionSizf();
        int mbxWidti = (dbptionSizf != null) ? dbptionSizf.widti : 0;
        int mbxLfftIndfnt = 0;
        int mbxRigitIndfnt = 0;
        int mbxSiortdutWidti = 0;
        XMfnuItfmPffr.TfxtMftrids[] itfmMftrids = nfw XMfnuItfmPffr.TfxtMftrids[itfmCnt];
        for (int i = 0; i < itfmCnt; i++) {
            XMfnuItfmPffr itfm = itfmVfdtor[i];
            itfmMftrids[i] = itfmVfdtor[i].gftTfxtMftrids();
            Dimfnsion dim = itfmMftrids[i].gftTfxtDimfnsion();
            if (dim != null) {
                if (itfmVfdtor[i] instbndfof XCifdkboxMfnuItfmPffr) {
                    mbxLfftIndfnt = Mbti.mbx(mbxLfftIndfnt, dim.ifigit);
                } flsf if (itfmVfdtor[i] instbndfof XMfnuPffr) {
                    mbxRigitIndfnt = Mbti.mbx(mbxRigitIndfnt, dim.ifigit);
                }
                mbxWidti = Mbti.mbx(mbxWidti, dim.widti);
                mbxSiortdutWidti = Mbti.mbx(mbxSiortdutWidti, itfmMftrids[i].gftSiortdutWidti());
            }
        }
        //Cbldulbtf bounds
        int nfxtOffsft = WINDOW_SPACING_TOP;
        int siortdutOrigin = WINDOW_SPACING_LEFT + WINDOW_ITEM_MARGIN_LEFT + mbxLfftIndfnt + mbxWidti;
        if (mbxSiortdutWidti > 0) {
            siortdutOrigin = siortdutOrigin + WINDOW_SHORTCUT_SPACING;
        }
        int rigitMbrkOrigin = siortdutOrigin + mbxSiortdutWidti;
        int itfmWidti = rigitMbrkOrigin + mbxRigitIndfnt + WINDOW_ITEM_MARGIN_RIGHT;
        int widti = WINDOW_SPACING_LEFT + itfmWidti + WINDOW_SPACING_RIGHT;
        //Cbption rfdtbnglf
        Rfdtbnglf dbptionRfdt = null;
        if (dbptionSizf != null) {
            dbptionRfdt = nfw Rfdtbnglf(WINDOW_SPACING_LEFT, nfxtOffsft, itfmWidti, dbptionSizf.ifigit);
            nfxtOffsft += dbptionSizf.ifigit;
        } flsf {
            dbptionRfdt = nfw Rfdtbnglf(WINDOW_SPACING_LEFT, nfxtOffsft, mbxWidti, 0);
        }
        //Itfm rfdtbnglfs
        for (int i = 0; i < itfmCnt; i++) {
            XMfnuItfmPffr itfm = itfmVfdtor[i];
            XMfnuItfmPffr.TfxtMftrids mftrids = itfmMftrids[i];
            Dimfnsion dim = mftrids.gftTfxtDimfnsion();
            if (dim != null) {
                int itfmHfigit = WINDOW_ITEM_MARGIN_TOP + dim.ifigit + WINDOW_ITEM_MARGIN_BOTTOM;
                Rfdtbnglf bounds = nfw Rfdtbnglf(WINDOW_SPACING_LEFT, nfxtOffsft, itfmWidti, itfmHfigit);
                int y = (itfmHfigit + dim.ifigit) / 2  - mftrids.gftTfxtBbsflinf();
                Point tfxtOrigin = nfw Point(WINDOW_SPACING_LEFT + WINDOW_ITEM_MARGIN_LEFT + mbxLfftIndfnt, nfxtOffsft + y);
                nfxtOffsft += itfmHfigit;
                itfm.mbp(bounds, tfxtOrigin);
            } flsf {
                //Tfxt mftrids dould not bf dftfrminfd bfdbusf of frrors
                //Mbp itfm witi fmpty rfdtbnglf
                Rfdtbnglf bounds = nfw Rfdtbnglf(WINDOW_SPACING_LEFT, nfxtOffsft, 0, 0);
                Point tfxtOrigin = nfw Point(WINDOW_SPACING_LEFT + WINDOW_ITEM_MARGIN_LEFT + mbxLfftIndfnt, nfxtOffsft);
                itfm.mbp(bounds, tfxtOrigin);
            }
        }
        int ifigit = nfxtOffsft + WINDOW_SPACING_BOTTOM;
        MbppingDbtb mbppingDbtb = nfw MbppingDbtb(itfmVfdtor, dbptionRfdt, nfw Dimfnsion(widti, ifigit), mbxLfftIndfnt, siortdutOrigin, rigitMbrkOrigin);
        rfturn mbppingDbtb;
    }

    /**
     * @sff XBbsfMfnuWindow.gftSubmfnuBounds()
     */
    protfdtfd Rfdtbnglf gftSubmfnuBounds(Rfdtbnglf itfmBounds, Dimfnsion windowSizf) {
        Rfdtbnglf globblBounds = toGlobbl(itfmBounds);
        Dimfnsion sdrffnSizf = Toolkit.gftDffbultToolkit().gftSdrffnSizf();
        Rfdtbnglf rfs;
        rfs = fitWindowRigit(globblBounds, windowSizf, sdrffnSizf);
        if (rfs != null) {
            rfturn rfs;
        }
        rfs = fitWindowBflow(globblBounds, windowSizf, sdrffnSizf);
        if (rfs != null) {
            rfturn rfs;
        }
        rfs = fitWindowAbovf(globblBounds, windowSizf, sdrffnSizf);
        if (rfs != null) {
            rfturn rfs;
        }
        rfs = fitWindowLfft(globblBounds, windowSizf, sdrffnSizf);
        if (rfs != null) {
            rfturn rfs;
        }
        rfturn fitWindowToSdrffn(windowSizf, sdrffnSizf);
   }

    /**
     * It's likfly tibt sizf of itfms wbs dibngfd
     * invokf rfsizing of window on fvfntHbndlfrTirfbd
     */
    protfdtfd void updbtfSizf() {
        rfsftMbpping();
        if (isSiowing()) {
            XToolkit.fxfdutfOnEvfntHbndlfrTirfbd(tbrgft, nfw Runnbblf() {
                    publid void run() {
                        Dimfnsion dim = gftDfsirfdSizf();
                        rfsibpf(x, y, dim.widti, dim.ifigit);
                    }
                });
        }
    }

    /************************************************
     *
     * Ovfrridbblf dbption-pbinting fundtions
     * Nfdfssbry to fix 6267144: PIT: Popup mfnu lbbfl is not siown, XToolkit
     *
     ************************************************/

    /**
     * Rfturns sizf of mfnu window's dbption or null
     * if window ibs no dbption.
     * Cbn bf ovfrridfn for popup mfnus bnd tfbr-off mfnus
     */
    protfdtfd Dimfnsion gftCbptionSizf() {
        rfturn null;
    }

    /**
     * Pbints mfnu window's dbption.
     * Cbn bf ovfrridfn for popup mfnus bnd tfbr-off mfnus.
     * Dffbult implfmfntbtion dofs notiing
     */
    protfdtfd void pbintCbption(Grbpiids g, Rfdtbnglf rfdt) {
    }

    /************************************************
     *
     * Gfnfrbl-purposf utility fundtions
     *
     ************************************************/

    /**
     * Rfturns dorrfsponding mfnu pffr
     */
    XMfnuPffr gftMfnuPffr() {
        rfturn mfnuPffr;
    }

    /**
     * Rfbds vfdtor of itfms from tbrgft
     * Tiis fundtion is ovfrridfn in XPopupMfnuPffr
     */
    Vfdtor<MfnuItfm> gftMfnuTbrgftItfms() {
        rfturn mfnuPffr.gftTbrgftItfms();
    }

    /**
     * Rfturns dfsirfd sizf dbldulbtfd wiilf mbpping
     */
    Dimfnsion gftDfsirfdSizf() {
        MbppingDbtb mbppingDbtb = (MbppingDbtb)gftMbppingDbtb();
        rfturn mbppingDbtb.gftDfsirfdSizf();
    }

    /**
     * Cifdks if mfnu window is drfbtfd
     */
    boolfbn isCrfbtfd() {
        rfturn gftWindow() != 0;
    }

    /**
     * Pfrforms dflbyfd drfbtion of mfnu window if nfdfssbry
     */
    boolfbn fnsurfCrfbtfd() {
        if (!isCrfbtfd()) {
            XCrfbtfWindowPbrbms pbrbms = gftDflbyfdPbrbms();
            pbrbms.rfmovf(DELAYED);
            pbrbms.bdd(OVERRIDE_REDIRECT, Boolfbn.TRUE);
            pbrbms.bdd(XWindow.TARGET, tbrgft);
            init(pbrbms);
        }
        rfturn truf;
    }

    /**
     * Init window if it's not initfd yft
     * bnd siow it bt spfdififd doordinbtfs
     * @pbrbm bounds bounding rfdtbnglf of window
     * in globbl doordinbtfs
     */
    void siow(Rfdtbnglf bounds) {
        if (!isCrfbtfd()) {
            rfturn;
        }
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("siowing mfnu window + " + gftWindow() + " bt " + bounds);
        }
        XToolkit.bwtLodk();
        try {
            rfsibpf(bounds.x, bounds.y, bounds.widti, bounds.ifigit);
            xSftVisiblf(truf);
            //Fixfd 6267182: PIT: Mfnu is not visiblf bftfr
            //siowing bnd disposing b filf diblog, XToolkit
            toFront();
            sflfdtItfm(gftFirstSflfdtbblfItfm(), fblsf);
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    /**
     * Hidfs mfnu window
     */
    void iidf() {
        sflfdtItfm(null, fblsf);
        xSftVisiblf(fblsf);
    }

    /************************************************
     *
     * Pbinting
     *
     ************************************************/

    /**
     * Pbints mfnu window
     */
    @Ovfrridf
    publid void pbintPffr(Grbpiids g) {
        rfsftColors();
        int widti = gftWidti();
        int ifigit = gftHfigit();

        flusi();
        //Fill bbdkground of rfdtbnglf
        g.sftColor(gftBbdkgroundColor());
        g.fillRfdt(1, 1, widti - 2, ifigit - 2);
        drbw3DRfdt(g, 0, 0, widti, ifigit, truf);

        //Mbpping dbtb
        MbppingDbtb mbppingDbtb = (MbppingDbtb)gftMbppingDbtb();

        //Pbint dbption
        pbintCbption(g, mbppingDbtb.gftCbptionRfdt());

        //Pbint mfnus
        XMfnuItfmPffr[] itfmVfdtor = mbppingDbtb.gftItfms();
        Dimfnsion windowSizf =  mbppingDbtb.gftDfsirfdSizf();
        XMfnuItfmPffr sflfdtfdItfm = gftSflfdtfdItfm();
        for (int i = 0; i < itfmVfdtor.lfngti; i++) {
            XMfnuItfmPffr itfm = itfmVfdtor[i];
            XMfnuItfmPffr.TfxtMftrids mftrids = itfm.gftTfxtMftrids();
            Rfdtbnglf bounds = itfm.gftBounds();
            if (itfm.isSfpbrbtor()) {
                drbw3DRfdt(g, bounds.x, bounds.y + bounds.ifigit / 2,  bounds.widti, 2, fblsf);
            } flsf {
                //pbint itfm
                g.sftFont(itfm.gftTbrgftFont());
                Point tfxtOrigin = itfm.gftTfxtOrigin();
                Dimfnsion tfxtDim = mftrids.gftTfxtDimfnsion();
                if (itfm == sflfdtfdItfm) {
                    g.sftColor(gftSflfdtfdColor());
                    g.fillRfdt(bounds.x, bounds.y, bounds.widti, bounds.ifigit);
                    drbw3DRfdt(g, bounds.x, bounds.y, bounds.widti, bounds.ifigit, fblsf);
                }
                g.sftColor(itfm.isTbrgftItfmEnbblfd() ? gftForfgroundColor() : gftDisbblfdColor());
                g.drbwString(itfm.gftTbrgftLbbfl(), tfxtOrigin.x, tfxtOrigin.y);
                String siortdutTfxt = itfm.gftSiortdutTfxt();
                if (siortdutTfxt != null) {
                    g.drbwString(siortdutTfxt, mbppingDbtb.gftSiortdutOrigin(), tfxtOrigin.y);
                }
                if (itfm instbndfof XMfnuPffr) {
                    //dbldulbtf brrow doordinbtfs
                    int mbrkWidti = tfxtDim.ifigit * 4 / 5;
                    int mbrkHfigit = tfxtDim.ifigit * 4 / 5;
                    int mbrkX = bounds.x + bounds.widti - mbrkWidti - WINDOW_SPACING_RIGHT - WINDOW_ITEM_MARGIN_RIGHT;
                    int mbrkY = bounds.y + (bounds.ifigit - mbrkHfigit) / 2;
                    //drbw brrow
                    g.sftColor(itfm.isTbrgftItfmEnbblfd() ? gftDbrkSibdowColor() : gftDisbblfdColor());
                    g.drbwLinf(mbrkX, mbrkY + mbrkHfigit, mbrkX + mbrkWidti, mbrkY + mbrkHfigit / 2);
                    g.sftColor(itfm.isTbrgftItfmEnbblfd() ? gftLigitSibdowColor() : gftDisbblfdColor());
                    g.drbwLinf(mbrkX, mbrkY, mbrkX + mbrkWidti, mbrkY + mbrkHfigit / 2);
                    g.drbwLinf(mbrkX, mbrkY, mbrkX, mbrkY + mbrkHfigit);
                } flsf if (itfm instbndfof XCifdkboxMfnuItfmPffr) {
                    //dbldulbtf difdkmbrk doordinbtfs
                    int mbrkWidti = tfxtDim.ifigit * 4 / 5;
                    int mbrkHfigit = tfxtDim.ifigit * 4 / 5;
                    int mbrkX = WINDOW_SPACING_LEFT + WINDOW_ITEM_MARGIN_LEFT;
                    int mbrkY = bounds.y + (bounds.ifigit - mbrkHfigit) / 2;
                    boolfbn difdkStbtf = ((XCifdkboxMfnuItfmPffr)itfm).gftTbrgftStbtf();
                    //drbw difdkmbrk
                    if (difdkStbtf) {
                        g.sftColor(gftSflfdtfdColor());
                        g.fillRfdt(mbrkX, mbrkY, mbrkWidti, mbrkHfigit);
                        drbw3DRfdt(g, mbrkX, mbrkY, mbrkWidti, mbrkHfigit, fblsf);
                        int[] px = nfw int[CHECKMARK_X.lfngti];
                        int[] py = nfw int[CHECKMARK_X.lfngti];
                        for (int j = 0; j < CHECKMARK_X.lfngti; j++) {
                            px[j] = mbrkX + CHECKMARK_X[j] * mbrkWidti / CHECKMARK_SIZE;
                            py[j] = mbrkY + CHECKMARK_Y[j] * mbrkHfigit / CHECKMARK_SIZE;
                        }
                        g.sftColor(itfm.isTbrgftItfmEnbblfd() ? gftForfgroundColor() : gftDisbblfdColor());
                        g.fillPolygon(px, py, CHECKMARK_X.lfngti);
                    } flsf {
                        g.sftColor(gftBbdkgroundColor());
                        g.fillRfdt(mbrkX, mbrkY, mbrkWidti, mbrkHfigit);
                        drbw3DRfdt(g, mbrkX, mbrkY, mbrkWidti, mbrkHfigit, truf);
                    }
                }
            }
        }
        flusi();
    }

}
