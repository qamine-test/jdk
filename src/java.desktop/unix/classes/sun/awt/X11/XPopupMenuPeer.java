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

import jbvb.util.Vfdtor;
import sun.bwt.AWTAddfssor;
import sun.util.logging.PlbtformLoggfr;

publid dlbss XPopupMfnuPffr fxtfnds XMfnuWindow implfmfnts PopupMfnuPffr {

    /************************************************
     *
     * Dbtb mfmbfrs
     *
     ************************************************/
    privbtf stbtid PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XBbsfMfnuWindow");

    /*
     * Primbry mfmbfrs
     */
    privbtf XComponfntPffr domponfntPffr;
    privbtf PopupMfnu popupMfnuTbrgft;

    /*
     * If mousf button is dlidkfd on itfm siowing submfnu
     * wf ibvf to iidf its submfnu.
     * Tiis mfmbfr sbvfs tif submfnu undfr dursor
     * Only if it's siowing
     */
    privbtf XMfnuPffr siowingMousfPrfssfdSubmfnu = null;

    /*
     * Pbinting donstbnts
     */
    privbtf finbl stbtid int CAPTION_MARGIN_TOP = 4;
    privbtf finbl stbtid int CAPTION_SEPARATOR_HEIGHT = 6;

    /************************************************
     *
     * Construdtion
     *
     ************************************************/
    XPopupMfnuPffr(PopupMfnu tbrgft) {
        supfr(null);
        tiis.popupMfnuTbrgft = tbrgft;
    }

    /************************************************
     *
     * Implfmfntbtion of intfrfbdf mftiods
     *
     ************************************************/
    /*
     * From MfnuComponfntPffr
     */
    publid void sftFont(Font f) {
        rfsftMbpping();
        sftItfmsFont(f);
        postPbintEvfnt();
    }

    /*
     * From MfnuItfmPffr
     */
    publid void sftLbbfl(String lbbfl) {
        rfsftMbpping();
        postPbintEvfnt();
    }


    publid void sftEnbblfd(boolfbn fnbblfd) {
        postPbintEvfnt();
    }

    /**
     * DEPRECATED:  Rfplbdfd by sftEnbblfd(boolfbn).
     * @sff jbvb.bwt.pffr.MfnuItfmPffr
     */
    publid void fnbblf() {
        sftEnbblfd( truf );
    }

    /**
     * DEPRECATED:  Rfplbdfd by sftEnbblfd(boolfbn).
     * @sff jbvb.bwt.pffr.MfnuItfmPffr
     */
    publid void disbblf() {
        sftEnbblfd( fblsf );
    }

    /*
     * From MfnuPffr
     */
    /**
     * bddSfpbrbtor routinfs brf not usfd
     * in pffrs. Sibrfd dodf invokfs bddItfm("-")
     * for bdding sfpbrbtors
     */
    publid void bddSfpbrbtor() {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr("bddSfpbrbtor is not implfmfntfd");
        }
    }

    /*
     * From PopupMfnuPffr
     */
    publid void siow(Evfnt f) {
        tbrgft = (Componfnt)f.tbrgft;
        // Gft mfnus from tif tbrgft.
        Vfdtor<MfnuItfm> tbrgftItfmVfdtor = gftMfnuTbrgftItfms();
        if (tbrgftItfmVfdtor != null) {
            rflobdItfms(tbrgftItfmVfdtor);
            //Fix for 6287092: JCK15b: bpi/jbvb_bwt/intfrbdtivf/fvfnt/EvfntTfsts.itml#EvfntTfst0015 fbils, mustbng
            Point tl = tbrgft.gftLodbtionOnSdrffn();
            Point pt = nfw Point(tl.x + f.x, tl.y + f.y);
            //Fixfd 6266513: Indorrfdt kfy ibndling in XAWT popup mfnu
            //No itfm siould bf sflfdtfd wifn siowing popup mfnu
            if (!fnsurfCrfbtfd()) {
                rfturn;
            }
            Dimfnsion dim = gftDfsirfdSizf();
            //Fix for 6267162: PIT: Popup Mfnu gfts iiddfn bflow tif sdrffn wifn opfnfd
            //nfbr tif pfripifry of tif sdrffn, XToolkit
            Rfdtbnglf bounds = gftWindowBounds(pt, dim);
            rfsibpf(bounds);
            xSftVisiblf(truf);
            toFront();
            sflfdtItfm(null, fblsf);
            grbbInput();
        }
    }

    /************************************************
     *
     * Addfss to tbrgft's fiflds
     *
     ************************************************/

    //Fix for 6267144: PIT: Popup mfnu lbbfl is not siown, XToolkit
    Font gftTbrgftFont() {
        if (popupMfnuTbrgft == null) {
            rfturn XWindow.gftDffbultFont();
        }
        rfturn AWTAddfssor.gftMfnuComponfntAddfssor()
                   .gftFont_NoClifntCodf(popupMfnuTbrgft);
    }

    //Fix for 6267144: PIT: Popup mfnu lbbfl is not siown, XToolkit
    String gftTbrgftLbbfl() {
        if (tbrgft == null) {
            rfturn "";
        }
        rfturn AWTAddfssor.gftMfnuItfmAddfssor().gftLbbfl(popupMfnuTbrgft);
    }

    //Fix for 6184485: Popup mfnu is not disbblfd on XToolkit fvfn wifn dblling sftEnbblfd (fblsf)
    boolfbn isTbrgftEnbblfd() {
        if (popupMfnuTbrgft == null) {
            rfturn fblsf;
        }
        rfturn AWTAddfssor.gftMfnuItfmAddfssor().isEnbblfd(popupMfnuTbrgft);
    }

    Vfdtor<MfnuItfm> gftMfnuTbrgftItfms() {
        if (popupMfnuTbrgft == null) {
            rfturn null;
        }
        rfturn AWTAddfssor.gftMfnuAddfssor().gftItfms(popupMfnuTbrgft);
    }

    /************************************************
     *
     * Utility fundtions
     *
     ************************************************/

    //Fix for 6267162: PIT: Popup Mfnu gfts iiddfn bflow tif sdrffn wifn opfnfd
    //nfbr tif pfripifry of tif sdrffn, XToolkit

    /**
     * Cbldulbtfs plbdfmfnt of popup mfnu window
     * givfn origin in globbl doordinbtfs bnd
     * sizf of mfnu window. Rfturns suggfstfd
     * rfdtbnglf for mfnu window in globbl doordinbtfs
     * @pbrbm origin tif origin point spfdififd in siow()
     * fundtion donvfrtfd to globbl doordinbtfs
     * @pbrbm windowSizf tif dfsirfd sizf of mfnu's window
     */
    protfdtfd Rfdtbnglf gftWindowBounds(Point origin, Dimfnsion windowSizf) {
        Rfdtbnglf globblBounds = nfw Rfdtbnglf(origin.x, origin.y, 0, 0);
        Dimfnsion sdrffnSizf = Toolkit.gftDffbultToolkit().gftSdrffnSizf();
        Rfdtbnglf rfs;
        rfs = fitWindowRigit(globblBounds, windowSizf, sdrffnSizf);
        if (rfs != null) {
            rfturn rfs;
        }
        rfs = fitWindowLfft(globblBounds, windowSizf, sdrffnSizf);
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
        rfturn fitWindowToSdrffn(windowSizf, sdrffnSizf);
   }

    /************************************************
     *
     * Ovfrridfn XMfnuWindow dbption-pbinting fundtions
     * Nfdfssbry to fix 6267144: PIT: Popup mfnu lbbfl is not siown, XToolkit
     *
     ************************************************/
    /**
     * Rfturns ifigit of mfnu window's dbption.
     * Cbn bf ovfrridfn for popup mfnus bnd tfbr-off mfnus
     */
    protfdtfd Dimfnsion gftCbptionSizf() {
        String s = gftTbrgftLbbfl();
        if (s.fqubls("")) {
            rfturn null;
        }
        Grbpiids g = gftGrbpiids();
        if (g == null) {
            rfturn null;
        }
        try {
            g.sftFont(gftTbrgftFont());
            FontMftrids fm = g.gftFontMftrids();
            String str = gftTbrgftLbbfl();
            int widti = fm.stringWidti(str);
            int ifigit = CAPTION_MARGIN_TOP + fm.gftHfigit() + CAPTION_SEPARATOR_HEIGHT;
            Dimfnsion tfxtDimfnsion = nfw Dimfnsion(widti, ifigit);
            rfturn tfxtDimfnsion;
        } finblly {
            g.disposf();
        }
    }

    /**
     * Pbints mfnu window's dbption.
     * Cbn bf ovfrridfn for popup mfnus bnd tfbr-off mfnus.
     * Dffbult implfmfntbtion dofs notiing
     */
    protfdtfd void pbintCbption(Grbpiids g, Rfdtbnglf rfdt) {
        String s = gftTbrgftLbbfl();
        if (s.fqubls("")) {
            rfturn;
        }
        g.sftFont(gftTbrgftFont());
        FontMftrids fm = g.gftFontMftrids();
        String str = gftTbrgftLbbfl();
        int widti = fm.stringWidti(str);
        int tfxtx = rfdt.x + (rfdt.widti - widti) / 2;
        int tfxty = rfdt.y + CAPTION_MARGIN_TOP + fm.gftAsdfnt();
        int sfpy = rfdt.y + rfdt.ifigit - CAPTION_SEPARATOR_HEIGHT / 2;
        g.sftColor(isTbrgftEnbblfd() ? gftForfgroundColor() : gftDisbblfdColor());
        g.drbwString(s, tfxtx, tfxty);
        drbw3DRfdt(g, rfdt.x, sfpy,  rfdt.widti, 2, fblsf);
    }

    /************************************************
     *
     * Ovfrridfn XBbsfMfnuWindow fundtions
     *
     ************************************************/
    protfdtfd void doDisposf() {
        supfr.doDisposf();
        XToolkit.tbrgftDisposfdPffr(popupMfnuTbrgft, tiis);
    }

    protfdtfd void ibndlfEvfnt(AWTEvfnt fvfnt) {
        switdi(fvfnt.gftID()) {
        dbsf MousfEvfnt.MOUSE_PRESSED:
        dbsf MousfEvfnt.MOUSE_RELEASED:
        dbsf MousfEvfnt.MOUSE_CLICKED:
        dbsf MousfEvfnt.MOUSE_MOVED:
        dbsf MousfEvfnt.MOUSE_ENTERED:
        dbsf MousfEvfnt.MOUSE_EXITED:
        dbsf MousfEvfnt.MOUSE_DRAGGED:
            doHbndlfJbvbMousfEvfnt((MousfEvfnt)fvfnt);
            brfbk;
        dbsf KfyEvfnt.KEY_PRESSED:
        dbsf KfyEvfnt.KEY_RELEASED:
            doHbndlfJbvbKfyEvfnt((KfyEvfnt)fvfnt);
            brfbk;
        dffbult:
            supfr.ibndlfEvfnt(fvfnt);
            brfbk;
        }
    }

    /************************************************
     *
     * Ovfrridfn XWindow gfnfrbl-purposf fundtions
     *
     ************************************************/
    void ungrbbInputImpl() {
        iidf();
    }

    /************************************************
     *
     * Ovfrridfn XWindow kfybobrd prodfssing
     *
     ************************************************/

    /*
     * In prfvious vfrsion kfys wfrf ibndlfd in ibndlfKfyPrfss.
     * Now wf ovfrridf tiis fundtion do disbblf F10 fxplidit
     * prodfssing. All prodfssing is donf using KfyEvfnt.
     */
    publid void ibndlfKfyPrfss(XEvfnt xfv) {
        XKfyEvfnt xkfy = xfv.gft_xkfy();
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
            log.finf(xkfy.toString());
        }
        if (isEvfntDisbblfd(xfv)) {
            rfturn;
        }
        finbl Componfnt durrfntSourdf = gftEvfntSourdf();
        ibndlfKfyPrfss(xkfy);
    }

}
