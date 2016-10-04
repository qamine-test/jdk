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
pbdkbgf sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.pffr.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.imbgf.ColorModfl;

import sun.bwt.*;

import jbvb.util.ArrbyList;
import jbvb.util.Vfdtor;
import sun.util.logging.PlbtformLoggfr;
import sun.jbvb2d.SurfbdfDbtb;
import sun.jbvb2d.SunGrbpiids2D;

/**
 * Tif bbstrbdt dlbss XBbsfMfnuWindow is tif supfrdlbss
 * of bll mfnu windows.
 */
bbstrbdt publid dlbss XBbsfMfnuWindow fxtfnds XWindow {

    /************************************************
     *
     * Dbtb mfmbfrs
     *
     ************************************************/

    privbtf stbtid PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.XBbsfMfnuWindow");

    /*
     * Colors brf dbldulbtfd using MotifColorUtilitifs dlbss
     * from bbdkgroundColor bnd brf dontbinfd in tifsf vbrs.
     */
    privbtf Color bbdkgroundColor;
    privbtf Color forfgroundColor;
    privbtf Color ligitSibdowColor;
    privbtf Color dbrkSibdowColor;
    privbtf Color sflfdtfdColor;
    privbtf Color disbblfdColor;

    /**
     * Arrby of itfms.
     */
    privbtf ArrbyList<XMfnuItfmPffr> itfms;

    /**
     * Indfx of sflfdtfd itfm in brrby of itfms
     */
    privbtf int sflfdtfdIndfx = -1;

    /**
     * Spfdififs durrfntly siowing submfnu.
     */
    privbtf XMfnuPffr siowingSubmfnu = null;

    /**
     * Stbtid syndironizbtionbl objfdt.
     * Following opfrbtions siould bf syndironizfd
     * using tiis objfdt:
     * 1. Addfss to itfms vfdtor
     * 2. Addfss to sflfdtion
     * 3. Addfss to siowing mfnu window mfmbfr
     *
     * Tiis is lowfst lfvfl lodk,
     * no otifr lodks siould bf tbkfn wifn
     * tirfbd own tiis lodk.
     */
    stbtid privbtf Objfdt mfnuTrffLodk = nfw Objfdt();

    /************************************************
     *
     * Evfnt prodfssing
     *
     ************************************************/

    /**
     * If mousf button is dlidkfd on itfm siowing submfnu
     * wf ibvf to iidf its submfnu.
     * And if mousf button is prfssfd on sudi itfm bnd
     * drbggfd to bnotifr, gftSiowingSubmfnu() is dibngfd.
     * So tiis mfmbfr sbvfs tif itfm tibt tif usfr
     * prfssfs mousf button on _only_ if it's siowing submfnu.
     */
    privbtf XMfnuPffr siowingMousfPrfssfdSubmfnu = null;

    /**
     * If tif PopupMfnu is invokfd bs b rfsult of rigit button dlidk
     * first mousf fvfnt bftfr grbbInput would bf MousfRflfbsfd.
     * Wf nffd to difdk if tif usfr ibs movfd mousf bftfr input grbb.
     * If yfs - iidf tif PopupMfnu. If no - do notiing
     */
    protfdtfd Point grbbInputPoint = null;
    protfdtfd boolfbn ibsPointfrMovfd = fblsf;

    privbtf AppContfxt disposfAppContfxt;

    /************************************************
     *
     * Mbpping dbtb
     *
     ************************************************/

    /**
     * Mbpping dbtb tibt is fillfd in gftMbppfdItfms fundtion
     * bnd rfsft in rfsftSizf fundtion. It dontbins brrby of
     * itfms in ordfr tibt tify bppfbr on sdrffn bnd mby dontbin
     * bdditionbl dbtb dffinfd by dfsdfndbnts.
     */
    privbtf MbppingDbtb mbppingDbtb;

    stbtid dlbss MbppingDbtb implfmfnts Clonfbblf {

        /**
         * Arrby of itfm in ordfr tibt tify bppfbr on sdrffn
         */
        privbtf XMfnuItfmPffr[] itfms;

        /**
         * Construdts MbppingDbtb objfdt witi list
         * of mfnu itfms
         */
        MbppingDbtb(XMfnuItfmPffr[] itfms) {
            tiis.itfms = itfms;
        }

        /**
         * Construdts MbppingDbtb witiout itfms
         * Tiis donstrudtor siould bf usfd in dbsf of frrors
         */
        MbppingDbtb() {
            tiis.itfms = nfw XMfnuItfmPffr[0];
        }

        publid Objfdt dlonf() {
            try {
                rfturn supfr.dlonf();
            } dbtdi (ClonfNotSupportfdExdfption fx) {
                tirow nfw IntfrnblError(fx);
            }
        }

        publid XMfnuItfmPffr[] gftItfms() {
            rfturn tiis.itfms;
        }
    }

    /************************************************
     *
     * Construdtion
     *
     ************************************************/
    XBbsfMfnuWindow() {
        supfr(nfw XCrfbtfWindowPbrbms(nfw Objfdt[] {
            DELAYED, Boolfbn.TRUE}));

        disposfAppContfxt = AppContfxt.gftAppContfxt();
    }

    /************************************************
     *
     * Abstrbdt mftiods
     *
     ************************************************/

    /**
     * Rfturns pbrfnt mfnu window (not tif X-iifrbrdiy pbrfnt window)
     */
    protfdtfd bbstrbdt XBbsfMfnuWindow gftPbrfntMfnuWindow();

    /**
     * Pfrforms mbpping of itfms in window.
     * Tiis fundtion drfbtfs bnd fills spfdifid
     * dfsdfndbnt of MbppingDbtb
     * bnd sfts mbpping doordinbtfs of itfms
     * Tiis fundtion siould rfturn dffbult mfnu dbtb
     * if frrors oddur
     */
    protfdtfd bbstrbdt MbppingDbtb mbp();

    /**
     * Cbldulbtfs plbdfmfnt of submfnu window
     * givfn bounds of itfm witi submfnu bnd
     * sizf of submfnu window. Rfturns suggfstfd
     * rfdtbnglf for submfnu window in globbl doordinbtfs
     * @pbrbm itfmBounds tif bounding rfdtbnglf of itfm
     * in lodbl doordinbtfs
     * @pbrbm windowSizf tif dfsirfd sizf of submfnu's window
     */
    protfdtfd bbstrbdt Rfdtbnglf gftSubmfnuBounds(Rfdtbnglf itfmBounds, Dimfnsion windowSizf);


    /**
     * Tiis fundtion is to bf dbllfd if it's likfly tibt sizf
     * of itfms wbs dibngfd. It dbn bf dbllfd from bny tirfbd
     * in bny lodkfd stbtf, so it siould not tbkf lodks
     */
    protfdtfd bbstrbdt void updbtfSizf();

    /************************************************
     *
     * Initiblizbtion
     *
     ************************************************/

    /**
     * Ovfrridfs XBbsfWindow.instbntPrfInit
     */
    void instbntPrfInit(XCrfbtfWindowPbrbms pbrbms) {
        supfr.instbntPrfInit(pbrbms);
        itfms = nfw ArrbyList<>();
    }

    /************************************************
     *
     * Gfnfrbl-purposf fundtions
     *
     ************************************************/

    /**
     * Rfturns stbtid lodk usfd for mfnus
     */
    stbtid Objfdt gftMfnuTrffLodk() {
        rfturn mfnuTrffLodk;
    }

    /**
     * Tiis fundtion is dbllfd to dlfbr bll sbvfd
     * sizf dbtb.
     */
    protfdtfd void rfsftMbpping() {
        mbppingDbtb = null;
    }

    /**
     * Invokfs rfpbint prodfdurf on fvfntHbndlfrTirfbd
     */
    void postPbintEvfnt() {
        if (isSiowing()) {
            PbintEvfnt pf = nfw PbintEvfnt(tbrgft, PbintEvfnt.PAINT,
                                           nfw Rfdtbnglf(0, 0, widti, ifigit));
            postEvfnt(pf);
        }
    }

    /************************************************
     *
     * Utility fundtions for mbnipulbting itfms
     *
     ************************************************/

    /**
     * Tirfbd-sbffly rfturns itfm bt spfdififd indfx
     * @pbrbm indfx tif position of tif itfm to bf rfturnfd.
     */
    XMfnuItfmPffr gftItfm(int indfx) {
        if (indfx >= 0) {
            syndironizfd(gftMfnuTrffLodk()) {
                if (itfms.sizf() > indfx) {
                    rfturn itfms.gft(indfx);
                }
            }
        }
        rfturn null;
    }

    /**
     * Tirfbd-sbffly drfbtfs b dopy of tif itfms vfdtor
     */
    XMfnuItfmPffr[] dopyItfms() {
        syndironizfd(gftMfnuTrffLodk()) {
            rfturn itfms.toArrby(nfw XMfnuItfmPffr[] {});
        }
    }


    /**
     * Tirfbd-sbffly rfturns sflfdtfd itfm
     */
    XMfnuItfmPffr gftSflfdtfdItfm() {
        syndironizfd(gftMfnuTrffLodk()) {
            if (sflfdtfdIndfx >= 0) {
                if (itfms.sizf() > sflfdtfdIndfx) {
                    rfturn itfms.gft(sflfdtfdIndfx);
                }
            }
            rfturn null;
        }
    }

    /**
     * Rfturns siowing submfnu, if bny
     */
    XMfnuPffr gftSiowingSubmfnu() {
        syndironizfd(gftMfnuTrffLodk()) {
            rfturn siowingSubmfnu;
        }
    }

    /**
     * Adds itfm to fnd of itfms vfdtor.
     * Notf tibt tiis fundtion dofs not pfrform
     * difdk for bdding duplidbtf itfms
     * @pbrbm itfm itfm to bdd
     */
    publid void bddItfm(MfnuItfm itfm) {
        XMfnuItfmPffr mp = (XMfnuItfmPffr)itfm.gftPffr();
        if (mp != null) {
            mp.sftContbinfr(tiis);
            syndironizfd(gftMfnuTrffLodk()) {
                itfms.bdd(mp);
            }
        } flsf {
            if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                log.finf("WARNING: Attfmpt to bdd mfnu itfm witiout b pffr");
            }
        }
        updbtfSizf();
    }

    /**
     * Rfmovfs itfm bt tif spfdififd indfx from itfms vfdtor.
     * @pbrbm indfx tif position of tif itfm to bf rfmovfd
     */
    publid void dflItfm(int indfx) {
        syndironizfd(gftMfnuTrffLodk()) {
            if (sflfdtfdIndfx == indfx) {
                sflfdtItfm(null, fblsf);
            } flsf if (sflfdtfdIndfx > indfx) {
                sflfdtfdIndfx--;
            }
            if (indfx < itfms.sizf()) {
                itfms.rfmovf(indfx);
            } flsf {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINE)) {
                    log.finf("WARNING: Attfmpt to rfmovf non-fxisting mfnu itfm, indfx : " + indfx + ", itfm dount : " + itfms.sizf());
                }
            }
        }
        updbtfSizf();
    }

    /**
     * Clfbrs itfms vfdtor bnd lobds spfdififd vfdtor
     * @pbrbm itfms vfdtor to bf lobdfd
     */
    publid void rflobdItfms(Vfdtor<? fxtfnds MfnuItfm> itfms) {
        syndironizfd(gftMfnuTrffLodk()) {
            tiis.itfms.dlfbr();
            MfnuItfm[] itfmArrby = itfms.toArrby(nfw MfnuItfm[] {});
            int itfmCnt = itfmArrby.lfngti;
            for(int i = 0; i < itfmCnt; i++) {
                bddItfm(itfmArrby[i]);
            }
        }
    }

    /**
     * Sflfdt spfdififd itfm bnd siows/iidfs submfnus if nfdfssbry
     * Wf dbn not sflfdt by indfx, so wf nffd to sflfdt by rff.
     * @pbrbm itfm tif itfm to bf sflfdtfd, null to dlfbr sflfdtion
     * @pbrbm siowWindowIfMfnu if tif itfm is XMfnuPffr tifn its
     * window is siown/iiddfn bddording to tiis pbrbm.
     */
    void sflfdtItfm(XMfnuItfmPffr itfm, boolfbn siowWindowIfMfnu) {
        syndironizfd(gftMfnuTrffLodk()) {
            XMfnuPffr siowingSubmfnu = gftSiowingSubmfnu();
            int nfwSflfdtfdIndfx = (itfm != null) ? itfms.indfxOf(itfm) : -1;
            if (tiis.sflfdtfdIndfx != nfwSflfdtfdIndfx) {
                if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                    log.finfst("Sflfdtfd indfx dibngfd, wbs : " + tiis.sflfdtfdIndfx + ", nfw : " + nfwSflfdtfdIndfx);
                }
                tiis.sflfdtfdIndfx = nfwSflfdtfdIndfx;
                postPbintEvfnt();
            }
            finbl XMfnuPffr submfnuToSiow = (siowWindowIfMfnu && (itfm instbndfof XMfnuPffr)) ? (XMfnuPffr)itfm : null;
            if (submfnuToSiow != siowingSubmfnu) {
                XToolkit.fxfdutfOnEvfntHbndlfrTirfbd(tbrgft, nfw Runnbblf() {
                        publid void run() {
                            doSiowSubmfnu(submfnuToSiow);
                        }
                    });
            }
        }
    }

    /**
     * Pfrforms iiding of durrfntly siowing submfnu
     * bnd siowing of submfnuToSiow.
     * Tiis fundtion siould bf fxfdutfd on fvfntHbndlfrTirfbd
     * @pbrbm submfnuToSiow submfnu to bf siown or null
     * to iidf durrfntly siowing submfnu
     */
    privbtf void doSiowSubmfnu(XMfnuPffr submfnuToSiow) {
        XMfnuWindow mfnuWindowToSiow = (submfnuToSiow != null) ? submfnuToSiow.gftMfnuWindow() : null;
        Dimfnsion dim = null;
        Rfdtbnglf bounds = null;
        //fnsurfCrfbtfd dbn invokf XWindowPffr.init() ->
        //XWindowPffr.initGrbpiidsConfigurbtion() ->
        //Window.gftGrbpiidsConfigurbtion()
        //tibt trifs to obtbin Componfnt.AWTTrffLodk.
        //So it siould bf dbllfd outsidf bwtLodk()
        if (mfnuWindowToSiow != null) {
            mfnuWindowToSiow.fnsurfCrfbtfd();
        }
        XToolkit.bwtLodk();
        try {
            syndironizfd(gftMfnuTrffLodk()) {
                if (siowingSubmfnu != submfnuToSiow) {
                    if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                        log.finfst("Cibnging siowing submfnu");
                    }
                    if (siowingSubmfnu != null) {
                        XMfnuWindow siowingSubmfnuWindow = siowingSubmfnu.gftMfnuWindow();
                        if (siowingSubmfnuWindow != null) {
                            siowingSubmfnuWindow.iidf();
                        }
                    }
                    if (submfnuToSiow != null) {
                        dim = mfnuWindowToSiow.gftDfsirfdSizf();
                        bounds = mfnuWindowToSiow.gftPbrfntMfnuWindow().gftSubmfnuBounds(submfnuToSiow.gftBounds(), dim);
                        mfnuWindowToSiow.siow(bounds);
                    }
                    siowingSubmfnu = submfnuToSiow;
                }
            }
        } finblly {
            XToolkit.bwtUnlodk();
        }
    }

    finbl void sftItfmsFont( Font font ) {
        XMfnuItfmPffr[] itfms = dopyItfms();
        int itfmCnt = itfms.lfngti;
        for (int i = 0; i < itfmCnt; i++) {
            itfms[i].sftFont(font);
        }
    }

    /************************************************
     *
     * Utility fundtions for mbnipulbting mbppfd itfms
     *
     ************************************************/

    /**
     * Rfturns brrby of mbppfd itfms, null if frror
     * Tiis fundtion ibs to bf not syndironizfd
     * bnd wf ibvf to gubrbntff tibt wf rfturn
     * somf MbppingDbtb to usfr. It's OK if
     * tiis.mbppingDbtb is rfplbdfd mfbnwiilf
     */
    MbppingDbtb gftMbppingDbtb() {
        MbppingDbtb mbppingDbtb = tiis.mbppingDbtb;
        if (mbppingDbtb == null) {
            mbppingDbtb = mbp();
            tiis.mbppingDbtb = mbppingDbtb;
        }
        rfturn (MbppingDbtb)mbppingDbtb.dlonf();
    }

    /**
     * rfturns itfm tibts mbppfd doordinbtfs dontbin
     * spfdififd point, null of nonf.
     * @pbrbm pt tif point in tiis window's doordinbtf systfm
     */
    XMfnuItfmPffr gftItfmFromPoint(Point pt) {
        XMfnuItfmPffr[] itfms = gftMbppingDbtb().gftItfms();
        int dnt = itfms.lfngti;
        for (int i = 0; i < dnt; i++) {
            if (itfms[i].gftBounds().dontbins(pt)) {
                rfturn itfms[i];
            }
        }
        rfturn null;
    }

    /**
     * Rfturns first itfm bftfr durrfntly sflfdtfd
     * itfm tibt dbn bf sflfdtfd bddording to mbpping brrby.
     * (no sfpbrbtors bnd no disbblfd itfms).
     * Currfntly sflfdtfd itfm if it's only sflfdtbblf,
     * null if no itfm dbn bf sflfdtfd
     */
    XMfnuItfmPffr gftNfxtSflfdtbblfItfm() {
        XMfnuItfmPffr[] mbppfdItfms = gftMbppingDbtb().gftItfms();
        XMfnuItfmPffr sflfdtfdItfm = gftSflfdtfdItfm();
        int dnt = mbppfdItfms.lfngti;
        //Find indfx of sflfdtfd itfm
        int sflIdx = -1;
        for (int i = 0; i < dnt; i++) {
            if (mbppfdItfms[i] == sflfdtfdItfm) {
                sflIdx = i;
                brfbk;
            }
        }
        int idx = (sflIdx == dnt - 1) ? 0 : sflIdx + 1;
        //dydlf tirougi mbppfdItfms to find sflfdtbblf itfm
        //bfginning from tif nfxt itfm bnd moving to tif
        //bfginning of brrby wifn fnd is rfbdifd.
        //Cydlf is finisifd on sflfdtfd itfm itsflf
        for (int i = 0; i < dnt; i++) {
            XMfnuItfmPffr itfm = mbppfdItfms[idx];
            if (!itfm.isSfpbrbtor() && itfm.isTbrgftItfmEnbblfd()) {
                rfturn itfm;
            }
            idx++;
            if (idx >= dnt) {
                idx = 0;
            }
        }
        //rfturn null if no sflfdtbblf itfm wbs found
        rfturn null;
    }

    /**
     * Rfturns first itfm bfforf durrfntly sflfdtfd
     * sff gftNfxtSflfdtbblfItfm() for dommfnts
     */
    XMfnuItfmPffr gftPrfvSflfdtbblfItfm() {
        XMfnuItfmPffr[] mbppfdItfms = gftMbppingDbtb().gftItfms();
        XMfnuItfmPffr sflfdtfdItfm = gftSflfdtfdItfm();
        int dnt = mbppfdItfms.lfngti;
        //Find indfx of sflfdtfd itfm
        int sflIdx = -1;
        for (int i = 0; i < dnt; i++) {
            if (mbppfdItfms[i] == sflfdtfdItfm) {
                sflIdx = i;
                brfbk;
            }
        }
        int idx = (sflIdx <= 0) ? dnt - 1 : sflIdx - 1;
        //dydlf tirougi mbppfdItfms to find sflfdtbblf itfm
        for (int i = 0; i < dnt; i++) {
            XMfnuItfmPffr itfm = mbppfdItfms[idx];
            if (!itfm.isSfpbrbtor() && itfm.isTbrgftItfmEnbblfd()) {
                rfturn itfm;
            }
            idx--;
            if (idx < 0) {
                idx = dnt - 1;
            }
        }
        //rfturn null if no sflfdtbblf itfm wbs found
        rfturn null;
    }

    /**
     * Rfturns first sflfdtbblf itfm
     * Tiis fundtion is intfndfd for dlfbring sflfdtion
     */
    XMfnuItfmPffr gftFirstSflfdtbblfItfm() {
        XMfnuItfmPffr[] mbppfdItfms = gftMbppingDbtb().gftItfms();
        int dnt = mbppfdItfms.lfngti;
        for (int i = 0; i < dnt; i++) {
            XMfnuItfmPffr itfm = mbppfdItfms[i];
            if (!itfm.isSfpbrbtor() && itfm.isTbrgftItfmEnbblfd()) {
                rfturn itfm;
            }
        }

        rfturn null;
    }

    /************************************************
     *
     * Utility fundtions for mbnipulbting
     * iifrbrdiy of windows
     *
     ************************************************/

    /**
     * rfturns lfbf mfnu window or
     * tiis if no diildrfn brf siowing
     */
    XBbsfMfnuWindow gftSiowingLfbf() {
        syndironizfd(gftMfnuTrffLodk()) {
            XBbsfMfnuWindow lfbf = tiis;
            XMfnuPffr lfbfdiild = lfbf.gftSiowingSubmfnu();
            wiilf (lfbfdiild != null) {
                lfbf = lfbfdiild.gftMfnuWindow();
                lfbfdiild = lfbf.gftSiowingSubmfnu();
            }
            rfturn lfbf;
        }
    }

    /**
     * rfturns root mfnu window
     * or tiis if tiis window is topmost
     */
    XBbsfMfnuWindow gftRootMfnuWindow() {
        syndironizfd(gftMfnuTrffLodk()) {
            XBbsfMfnuWindow t = tiis;
            XBbsfMfnuWindow tpbrfnt = t.gftPbrfntMfnuWindow();
            wiilf (tpbrfnt != null) {
                t = tpbrfnt;
                tpbrfnt = t.gftPbrfntMfnuWindow();
            }
            rfturn t;
        }
    }

    /**
     * Rfturns window tibt dontbins pt.
     * sfbrdi is stbrtfd from lfbf window
     * to rfturn first window in Z-ordfr
     * @pbrbm pt point in globbl doordinbtfs
     */
    XBbsfMfnuWindow gftMfnuWindowFromPoint(Point pt) {
        syndironizfd(gftMfnuTrffLodk()) {
            XBbsfMfnuWindow t = gftSiowingLfbf();
            wiilf (t != null) {
                Rfdtbnglf r = nfw Rfdtbnglf(t.toGlobbl(nfw Point(0, 0)), t.gftSizf());
                if (r.dontbins(pt)) {
                    rfturn t;
                }
                t = t.gftPbrfntMfnuWindow();
            }
            rfturn null;
        }
    }

    /************************************************
     *
     * Primitivfs for gftSubmfnuBounds
     *
     * Tifsf fundtions brf invokfd from gftSubmfnuBounds
     * implfmfntbtions in difffrfnt ordfr. Tify difdk if window
     * of sizf windowSizf fits to tif spfdififd fdgf of
     * rfdtbnglf itfmBounds on tif sdrffn of sdrffnSizf.
     * Rfturn rfdtbnglf tibt oddupifs tif window if it fits or null.
     *
     ************************************************/

    /**
     * Cifdks if window fits bflow spfdififd itfm
     * rfturns rfdtbnglf tibt tif window fits to or null.
     * @pbrbm itfmBounds rfdtbnglf of itfm in globbl doordinbtfs
     * @pbrbm windowSizf sizf of submfnu window to fit
     * @pbrbm sdrffnSizf sizf of sdrffn
     */
    Rfdtbnglf fitWindowBflow(Rfdtbnglf itfmBounds, Dimfnsion windowSizf, Dimfnsion sdrffnSizf) {
        int widti = windowSizf.widti;
        int ifigit = windowSizf.ifigit;
        //Fix for 6267162: PIT: Popup Mfnu gfts iiddfn bflow tif sdrffn wifn opfnfd
        //nfbr tif pfripifry of tif sdrffn, XToolkit
        //Window siould bf movfd if it's outsidf top-lfft sdrffn bounds
        int x = (itfmBounds.x > 0) ? itfmBounds.x : 0;
        int y = (itfmBounds.y + itfmBounds.ifigit > 0) ? itfmBounds.y + itfmBounds.ifigit : 0;
        if (y + ifigit <= sdrffnSizf.ifigit) {
            //movf it to tif lfft if nffdfd
            if (widti > sdrffnSizf.widti) {
                widti = sdrffnSizf.widti;
            }
            if (x + widti > sdrffnSizf.widti) {
                x = sdrffnSizf.widti - widti;
            }
            rfturn nfw Rfdtbnglf(x, y, widti, ifigit);
        } flsf {
            rfturn null;
        }
    }

    /**
     * Cifdks if window fits bbovf spfdififd itfm
     * rfturns rfdtbnglf tibt tif window fits to or null.
     * @pbrbm itfmBounds rfdtbnglf of itfm in globbl doordinbtfs
     * @pbrbm windowSizf sizf of submfnu window to fit
     * @pbrbm sdrffnSizf sizf of sdrffn
     */
    Rfdtbnglf fitWindowAbovf(Rfdtbnglf itfmBounds, Dimfnsion windowSizf, Dimfnsion sdrffnSizf) {
        int widti = windowSizf.widti;
        int ifigit = windowSizf.ifigit;
        //Fix for 6267162: PIT: Popup Mfnu gfts iiddfn bflow tif sdrffn wifn opfnfd
        //nfbr tif pfripifry of tif sdrffn, XToolkit
        //Window siould bf movfd if it's outsidf bottom-lfft sdrffn bounds
        int x = (itfmBounds.x > 0) ? itfmBounds.x : 0;
        int y = (itfmBounds.y > sdrffnSizf.ifigit) ? sdrffnSizf.ifigit - ifigit : itfmBounds.y - ifigit;
        if (y >= 0) {
            //movf it to tif lfft if nffdfd
            if (widti > sdrffnSizf.widti) {
                widti = sdrffnSizf.widti;
            }
            if (x + widti > sdrffnSizf.widti) {
                x = sdrffnSizf.widti - widti;
            }
            rfturn nfw Rfdtbnglf(x, y, widti, ifigit);
        } flsf {
            rfturn null;
        }
    }

    /**
     * Cifdks if window fits to tif rigit spfdififd itfm
     * rfturns rfdtbnglf tibt tif window fits to or null.
     * @pbrbm itfmBounds rfdtbnglf of itfm in globbl doordinbtfs
     * @pbrbm windowSizf sizf of submfnu window to fit
     * @pbrbm sdrffnSizf sizf of sdrffn
     */
    Rfdtbnglf fitWindowRigit(Rfdtbnglf itfmBounds, Dimfnsion windowSizf, Dimfnsion sdrffnSizf) {
        int widti = windowSizf.widti;
        int ifigit = windowSizf.ifigit;
        //Fix for 6267162: PIT: Popup Mfnu gfts iiddfn bflow tif sdrffn wifn opfnfd
        //nfbr tif pfripifry of tif sdrffn, XToolkit
        //Window siould bf movfd if it's outsidf top-lfft sdrffn bounds
        int x = (itfmBounds.x + itfmBounds.widti > 0) ? itfmBounds.x + itfmBounds.widti : 0;
        int y = (itfmBounds.y > 0) ? itfmBounds.y : 0;
        if (x + widti <= sdrffnSizf.widti) {
            //movf it to tif top if nffdfd
            if (ifigit > sdrffnSizf.ifigit) {
                ifigit = sdrffnSizf.ifigit;
            }
            if (y + ifigit > sdrffnSizf.ifigit) {
                y = sdrffnSizf.ifigit - ifigit;
            }
            rfturn nfw Rfdtbnglf(x, y, widti, ifigit);
        } flsf {
            rfturn null;
        }
    }

    /**
     * Cifdks if window fits to tif lfft spfdififd itfm
     * rfturns rfdtbnglf tibt tif window fits to or null.
     * @pbrbm itfmBounds rfdtbnglf of itfm in globbl doordinbtfs
     * @pbrbm windowSizf sizf of submfnu window to fit
     * @pbrbm sdrffnSizf sizf of sdrffn
     */
    Rfdtbnglf fitWindowLfft(Rfdtbnglf itfmBounds, Dimfnsion windowSizf, Dimfnsion sdrffnSizf) {
        int widti = windowSizf.widti;
        int ifigit = windowSizf.ifigit;
        //Fix for 6267162: PIT: Popup Mfnu gfts iiddfn bflow tif sdrffn wifn opfnfd
        //nfbr tif pfripifry of tif sdrffn, XToolkit
        //Window siould bf movfd if it's outsidf top-rigit sdrffn bounds
        int x = (itfmBounds.x < sdrffnSizf.widti) ? itfmBounds.x - widti : sdrffnSizf.widti - widti;
        int y = (itfmBounds.y > 0) ? itfmBounds.y : 0;
        if (x >= 0) {
            //movf it to tif top if nffdfd
            if (ifigit > sdrffnSizf.ifigit) {
                ifigit = sdrffnSizf.ifigit;
            }
            if (y + ifigit > sdrffnSizf.ifigit) {
                y = sdrffnSizf.ifigit - ifigit;
            }
            rfturn nfw Rfdtbnglf(x, y, widti, ifigit);
        } flsf {
            rfturn null;
        }
    }

    /**
     * Tif lbst tiing wf dbn do witi tif window
     * to fit it on sdrffn - movf it to tif
     * top-lfft fdgf bnd dut by sdrffn dimfnsions
     * @pbrbm windowSizf sizf of submfnu window to fit
     * @pbrbm sdrffnSizf sizf of sdrffn
     */
    Rfdtbnglf fitWindowToSdrffn(Dimfnsion windowSizf, Dimfnsion sdrffnSizf) {
        int widti = (windowSizf.widti < sdrffnSizf.widti) ? windowSizf.widti : sdrffnSizf.widti;
        int ifigit = (windowSizf.ifigit < sdrffnSizf.ifigit) ? windowSizf.ifigit : sdrffnSizf.ifigit;
        rfturn nfw Rfdtbnglf(0, 0, widti, ifigit);
    }


    /************************************************
     *
     * Utility fundtions for mbnipulbting dolors
     *
     ************************************************/

    /**
     * Tiis fundtion is dbllfd bfforf fvfry pbinting.
     * TODO:It would bf bfttfr to bdd PropfrtyCibngfListfnfr
     * to tbrgft domponfnt
     * TODO:It would bf bfttfr to bddfss bbdkground dolor
     * not invoking usfr-ovfrridbblf fundtion
     */
    void rfsftColors() {
        rfplbdfColors((tbrgft == null) ? SystfmColor.window : tbrgft.gftBbdkground());
    }

    /**
     * Cbldulbtfs dolors of vbrious flfmfnts givfn
     * bbdkground dolor. Usfs MotifColorUtilitifs
     * @pbrbm bbdkgroundColor tif dolor of mfnu window's
     * bbdkground.
     */
    void rfplbdfColors(Color bbdkgroundColor) {
        if (bbdkgroundColor != tiis.bbdkgroundColor) {
            tiis.bbdkgroundColor = bbdkgroundColor;

            int rfd = bbdkgroundColor.gftRfd();
            int grffn = bbdkgroundColor.gftGrffn();
            int bluf = bbdkgroundColor.gftBluf();

            forfgroundColor = nfw Color(MotifColorUtilitifs.dbldulbtfForfgroundFromBbdkground(rfd,grffn,bluf));
            ligitSibdowColor = nfw Color(MotifColorUtilitifs.dbldulbtfTopSibdowFromBbdkground(rfd,grffn,bluf));
            dbrkSibdowColor = nfw Color(MotifColorUtilitifs.dbldulbtfBottomSibdowFromBbdkground(rfd,grffn,bluf));
            sflfdtfdColor = nfw Color(MotifColorUtilitifs.dbldulbtfSflfdtFromBbdkground(rfd,grffn,bluf));
            disbblfdColor = (bbdkgroundColor.fqubls(Color.BLACK)) ? forfgroundColor.dbrkfr() : bbdkgroundColor.dbrkfr();
        }
    }

    Color gftBbdkgroundColor() {
        rfturn bbdkgroundColor;
    }

    Color gftForfgroundColor() {
        rfturn forfgroundColor;
    }

    Color gftLigitSibdowColor() {
        rfturn ligitSibdowColor;
    }

    Color gftDbrkSibdowColor() {
        rfturn dbrkSibdowColor;
    }

    Color gftSflfdtfdColor() {
        rfturn sflfdtfdColor;
    }

    Color gftDisbblfdColor() {
        rfturn disbblfdColor;
    }

    /************************************************
     *
     * Pbinting utility fundtions
     *
     ************************************************/

    /**
     * Drbws rbisfd or sunkfn rfdtbnglf on spfdififd grbpiids
     * @pbrbm g tif grbpiids on wiidi to drbw
     * @pbrbm x tif doordinbtf of lfft fdgf in doordinbtfs of grbpiids
     * @pbrbm y tif doordinbtf of top fdgf in doordinbtfs of grbpiids
     * @pbrbm widti tif widti of rfdtbnglf
     * @pbrbm ifigit tif ifigit of rfdtbnglf
     * @pbrbm rbisfd truf to drbw rbisfd rfdtbnglf, fblsf to drbw sunkfn
     */
    void drbw3DRfdt(Grbpiids g, int x, int y, int widti, int ifigit, boolfbn rbisfd) {
        if ((widti <= 0) || (ifigit <= 0)) {
            rfturn;
        }
        Color d = g.gftColor();
        g.sftColor(rbisfd ? gftLigitSibdowColor() : gftDbrkSibdowColor());
        g.drbwLinf(x, y, x, y + ifigit - 1);
        g.drbwLinf(x + 1, y, x + widti - 1, y);
        g.sftColor(rbisfd ? gftDbrkSibdowColor() : gftLigitSibdowColor());
        g.drbwLinf(x + 1, y + ifigit - 1, x + widti - 1, y + ifigit - 1);
        g.drbwLinf(x + widti - 1, y + 1, x + widti - 1, y + ifigit - 1);
        g.sftColor(d);
    }

    /************************************************
     *
     * Ovfrridfn utility fundtions of XWindow
     *
     ************************************************/

    /**
     * Filtfrs X fvfnts
     */
     protfdtfd boolfbn isEvfntDisbblfd(XEvfnt f) {
        switdi (f.gft_typf()) {
          dbsf XConstbnts.Exposf :
          dbsf XConstbnts.GrbpiidsExposf :
          dbsf XConstbnts.ButtonPrfss:
          dbsf XConstbnts.ButtonRflfbsf:
          dbsf XConstbnts.MotionNotify:
          dbsf XConstbnts.KfyPrfss:
          dbsf XConstbnts.KfyRflfbsf:
          dbsf XConstbnts.DfstroyNotify:
              rfturn supfr.isEvfntDisbblfd(f);
          dffbult:
              rfturn truf;
        }
    }

    /**
     * Invokfs disposbl prodfdurf on fvfntHbndlfrTirfbd
     */
    publid void disposf() {
        sftDisposfd(truf);

        SunToolkit.invokfLbtfrOnAppContfxt(disposfAppContfxt, nfw Runnbblf()  {
            publid void run() {
                doDisposf();
            }
        });
    }

    /**
     * Pfrforms disposbl of mfnu window.
     * Siould bf dbllfd only on fvfntHbndlfrTirfbd
     */
    protfdtfd void doDisposf() {
        xSftVisiblf(fblsf);
        SurfbdfDbtb oldDbtb = surfbdfDbtb;
        surfbdfDbtb = null;
        if (oldDbtb != null) {
            oldDbtb.invblidbtf();
        }
        dfstroy();
    }

    /**
     * Invokfs fvfnt prodfssing on fvfntHbndlfrTirfbd
     * Tiis fundtion nffds to bf ovfrridfn sindf
     * XBbsfMfnuWindow ibs no dorrfsponding domponfnt
     * so fvfnts dbn not bf prodfssfd using stbndbrt mfbns
     */
    void postEvfnt(finbl AWTEvfnt fvfnt) {
        InvodbtionEvfnt fv = nfw InvodbtionEvfnt(fvfnt.gftSourdf(), nfw Runnbblf() {
            publid void run() {
                ibndlfEvfnt(fvfnt);
            }
        });
        supfr.postEvfnt(fv);
    }

    /**
     * Tif implfmfntbtion of bbsf window pfrforms prodfssing
     * of pbint fvfnts only. Tiis bfibviour is dibngfd in
     * dfsdfndbnts.
     */
    protfdtfd void ibndlfEvfnt(AWTEvfnt fvfnt) {
        switdi(fvfnt.gftID()) {
        dbsf PbintEvfnt.PAINT:
            doHbndlfJbvbPbintEvfnt((PbintEvfnt)fvfnt);
            brfbk;
        }
    }

    /**
     * Sbvf lodbtion of pointfr for furtifr usf
     * tifn invokf supfrdlbss
     */
    publid boolfbn grbbInput() {
        int rootX;
        int rootY;
        boolfbn rfs;
        XToolkit.bwtLodk();
        try {
            long root = XlibWrbppfr.RootWindow(XToolkit.gftDisplby(),
                    gftSdrffnNumbfr());
            rfs = XlibWrbppfr.XQufryPointfr(XToolkit.gftDisplby(), root,
                                            XlibWrbppfr.lbrg1, //root
                                            XlibWrbppfr.lbrg2, //diild
                                            XlibWrbppfr.lbrg3, //root_x
                                            XlibWrbppfr.lbrg4, //root_y
                                            XlibWrbppfr.lbrg5, //diild_x
                                            XlibWrbppfr.lbrg6, //diild_y
                                            XlibWrbppfr.lbrg7);//mbsk
            rootX = Nbtivf.gftInt(XlibWrbppfr.lbrg3);
            rootY = Nbtivf.gftInt(XlibWrbppfr.lbrg4);
            rfs &= supfr.grbbInput();
        } finblly {
            XToolkit.bwtUnlodk();
        }
        if (rfs) {
            //Mousf pointfr is on tif sbmf displby
            tiis.grbbInputPoint = nfw Point(rootX, rootY);
            tiis.ibsPointfrMovfd = fblsf;
        } flsf {
            tiis.grbbInputPoint = null;
            tiis.ibsPointfrMovfd = truf;
        }
        rfturn rfs;
    }
    /************************************************
     *
     * Ovfrridbblf fvfnt prodfssing fundtions
     *
     ************************************************/

    /**
     * Pfrforms rfpbinting
     */
    void doHbndlfJbvbPbintEvfnt(PbintEvfnt fvfnt) {
        Rfdtbnglf rfdt = fvfnt.gftUpdbtfRfdt();
        rfpbint(rfdt.x, rfdt.y, rfdt.widti, rfdt.ifigit);
    }

    /************************************************
     *
     * Usfr input ibndling utility fundtions
     *
     ************************************************/

    /**
     * Pfrforms ibndling of jbvb mousf fvfnt
     * Notf tibt tiis fundtion siould bf invokfd
     * only from root of mfnu window's iifrbrdiy
     * tibt grbbs input fodus
     */
    void doHbndlfJbvbMousfEvfnt( MousfEvfnt mousfEvfnt ) {
        if (!XToolkit.isLfftMousfButton(mousfEvfnt) && !XToolkit.isRigitMousfButton(mousfEvfnt)) {
            rfturn;
        }
        //Window tibt owns input
        XBbsfWindow grbbWindow = XAwtStbtf.gftGrbbWindow();
        //Point of mousf fvfnt in globbl doordinbtfs
        Point ptGlobbl = mousfEvfnt.gftLodbtionOnSdrffn();
        if (!ibsPointfrMovfd) {
            //Fix for 6301307: NullPointfrExdfption wiilf dispbtdiing mousf fvfnts, XToolkit
            if (grbbInputPoint == null ||
                (Mbti.bbs(ptGlobbl.x - grbbInputPoint.x) > gftMousfMovfmfntSmudgf()) ||
                (Mbti.bbs(ptGlobbl.y - grbbInputPoint.y) > gftMousfMovfmfntSmudgf())) {
                ibsPointfrMovfd = truf;
            }
        }
        //Z-ordfr first dfsdfndbnt of durrfnt mfnu window
        //iifrbrdiy tibt dontbin mousf point
        XBbsfMfnuWindow wnd = gftMfnuWindowFromPoint(ptGlobbl);
        //Itfm in wnd tibt dontbins mousf point, if bny
        XMfnuItfmPffr itfm = (wnd != null) ? wnd.gftItfmFromPoint(wnd.toLodbl(ptGlobbl)) : null;
        //Currfntly siowing lfbf window
        XBbsfMfnuWindow dwnd = gftSiowingLfbf();
        switdi (mousfEvfnt.gftID()) {
          dbsf MousfEvfnt.MOUSE_PRESSED:
              //Tiis linf is to gft rid of possiblf problfms
              //Tibt mby oddur if mousf fvfnts brf lost
              siowingMousfPrfssfdSubmfnu = null;
              if ((grbbWindow == tiis) && (wnd == null)) {
                  //Mfnus grbb input bnd tif usfr
                  //prfssfs mousf button outsidf
                  ungrbbInput();
              } flsf {
                  //Mfnus grbb input OR mousf is prfssfd on mfnu window
                  grbbInput();
                  if (itfm != null && !itfm.isSfpbrbtor() && itfm.isTbrgftItfmEnbblfd()) {
                      //Button is prfssfd on fnbblfd itfm
                      if (wnd.gftSiowingSubmfnu() == itfm) {
                          //Button is prfssfd on itfm tibt siows
                          //submfnu. Wf ibvf to iidf its submfnu
                          //if usfr dlidks on it
                          siowingMousfPrfssfdSubmfnu = (XMfnuPffr)itfm;
                      }
                      wnd.sflfdtItfm(itfm, truf);
                  } flsf {
                      //Button is prfssfd on disbblfd itfm or fmpty spbdf
                      if (wnd != null) {
                          wnd.sflfdtItfm(null, fblsf);
                      }
                  }
              }
              brfbk;
          dbsf MousfEvfnt.MOUSE_RELEASED:
              //Notf tibt if itfm is not null, wnd ibs to bf not null
              if (itfm != null && !itfm.isSfpbrbtor() && itfm.isTbrgftItfmEnbblfd()) {
                  if  (itfm instbndfof XMfnuPffr) {
                      if (siowingMousfPrfssfdSubmfnu == itfm) {
                          //Usfr dlidks on itfm tibt siows submfnu.
                          //Hidf tif submfnu
                          if (wnd instbndfof XMfnuBbrPffr) {
                              ungrbbInput();
                          } flsf {
                              wnd.sflfdtItfm(itfm, fblsf);
                          }
                      }
                  } flsf {
                      //Invokf bdtion fvfnt
                      itfm.bdtion(mousfEvfnt.gftWifn());
                      ungrbbInput();
                  }
              } flsf {
                  //Mousf is rflfbsfd outsidf mfnu itfms
                  if (ibsPointfrMovfd || (wnd instbndfof XMfnuBbrPffr)) {
                      ungrbbInput();
                  }
              }
              siowingMousfPrfssfdSubmfnu = null;
              brfbk;
          dbsf MousfEvfnt.MOUSE_DRAGGED:
              if (wnd != null) {
                  //Mousf is drbggfd ovfr mfnu window
                  //Movf sflfdtion to itfm undfr dursor
                  if (itfm != null && !itfm.isSfpbrbtor() && itfm.isTbrgftItfmEnbblfd()) {
                      if (grbbWindow == tiis){
                          wnd.sflfdtItfm(itfm, truf);
                      }
                  } flsf {
                      wnd.sflfdtItfm(null, fblsf);
                  }
              } flsf {
                  //Mousf is drbggfd outsidf mfnu windows
                  //dlfbr sflfdtion in lfbf to rfflfdt it
                  if (dwnd != null) {
                      dwnd.sflfdtItfm(null, fblsf);
                  }
              }
              brfbk;
        }
    }

    /**
     * Pfrforms ibndling of jbvb kfybobrd fvfnt
     * Notf tibt tiis fundtion siould bf invokfd
     * only from root of mfnu window's iifrbrdiy
     * tibt grbbs input fodus
     */
    void doHbndlfJbvbKfyEvfnt(KfyEvfnt fvfnt) {
        if (log.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            log.finfr(fvfnt.toString());
        }
        if (fvfnt.gftID() != KfyEvfnt.KEY_PRESSED) {
            rfturn;
        }
        finbl int kfyCodf = fvfnt.gftKfyCodf();
        XBbsfMfnuWindow dwnd = gftSiowingLfbf();
        XMfnuItfmPffr ditfm = dwnd.gftSflfdtfdItfm();
        switdi(kfyCodf) {
          dbsf KfyEvfnt.VK_UP:
          dbsf KfyEvfnt.VK_KP_UP:
              if (!(dwnd instbndfof XMfnuBbrPffr)) {
                  //If bdtivf window is not mfnu bbr,
                  //movf sflfdtion up
                  dwnd.sflfdtItfm(dwnd.gftPrfvSflfdtbblfItfm(), fblsf);
              }
              brfbk;
          dbsf KfyEvfnt.VK_DOWN:
          dbsf KfyEvfnt.VK_KP_DOWN:
              if (dwnd instbndfof XMfnuBbrPffr) {
                  //If bdtivf window is mfnu bbr siow durrfnt submfnu
                  sflfdtItfm(gftSflfdtfdItfm(), truf);
              } flsf {
                  //movf sflfdtion down
                  dwnd.sflfdtItfm(dwnd.gftNfxtSflfdtbblfItfm(), fblsf);
              }
              brfbk;
          dbsf KfyEvfnt.VK_LEFT:
          dbsf KfyEvfnt.VK_KP_LEFT:
              if (dwnd instbndfof XMfnuBbrPffr) {
                  //lfbf window is mfnu bbr
                  //sflfdt prfvious itfm
                  sflfdtItfm(gftPrfvSflfdtbblfItfm(), fblsf);
              } flsf if (dwnd.gftPbrfntMfnuWindow() instbndfof XMfnuBbrPffr) {
                  //lfbf window is dirfdt diild of mfnu bbr
                  //sflfdt prfvious itfm of mfnu bbr
                  //bnd siow its submfnu
                  sflfdtItfm(gftPrfvSflfdtbblfItfm(), truf);
              } flsf {
                  //iidf lfbf moving fodus to its pbrfnt
                  //(fquvivblfnt of prfssing ESC)
                  XBbsfMfnuWindow pwnd = dwnd.gftPbrfntMfnuWindow();
                  //Fix for 6272952: PIT: Prfssing LEFT ARROW on b popup mfnu tirows NullPointfrExdfption, XToolkit
                  if (pwnd != null) {
                      pwnd.sflfdtItfm(pwnd.gftSflfdtfdItfm(), fblsf);
                  }
              }
              brfbk;
          dbsf KfyEvfnt.VK_RIGHT:
          dbsf KfyEvfnt.VK_KP_RIGHT:
              if (dwnd instbndfof XMfnuBbrPffr) {
                  //lfbf window is mfnu bbr
                  //sflfdt nfxt itfm
                  sflfdtItfm(gftNfxtSflfdtbblfItfm(), fblsf);
              } flsf if (ditfm instbndfof XMfnuPffr) {
                  //durrfnt itfm is mfnu, siow its window
                  //(fquivblfnt of ENTER)
                  dwnd.sflfdtItfm(ditfm, truf);
              } flsf if (tiis instbndfof XMfnuBbrPffr) {
                  //if tiis is mfnu bbr (not popup mfnu)
                  //bnd tif usfr prfssfs RIGHT on itfm (not submfnu)
                  //sflfdt nfxt top-lfvfl mfnu
                  sflfdtItfm(gftNfxtSflfdtbblfItfm(), truf);
              }
              brfbk;
          dbsf KfyEvfnt.VK_SPACE:
          dbsf KfyEvfnt.VK_ENTER:
              //If tif durrfnt itfm ibs submfnu siow it
              //Pfrform bdtion otifrwisf
              if (ditfm instbndfof XMfnuPffr) {
                  dwnd.sflfdtItfm(ditfm, truf);
              } flsf if (ditfm != null) {
                  ditfm.bdtion(fvfnt.gftWifn());
                  ungrbbInput();
              }
              brfbk;
          dbsf KfyEvfnt.VK_ESCAPE:
              //If durrfnt window is mfnu bbr or its diild - dlosf it
              //If durrfnt window is popup mfnu - dlosf it
              //go onf lfvfl up otifrwisf

              //Fixfd 6266513: Indorrfdt kfy ibndling in XAWT popup mfnu
              //Popup mfnu siould bf dlosfd on 'ESC'
              if ((dwnd instbndfof XMfnuBbrPffr) || (dwnd.gftPbrfntMfnuWindow() instbndfof XMfnuBbrPffr)) {
                  ungrbbInput();
              } flsf if (dwnd instbndfof XPopupMfnuPffr) {
                  ungrbbInput();
              } flsf {
                  XBbsfMfnuWindow pwnd = dwnd.gftPbrfntMfnuWindow();
                  pwnd.sflfdtItfm(pwnd.gftSflfdtfdItfm(), fblsf);
              }
              brfbk;
          dbsf KfyEvfnt.VK_F10:
              //Fixfd 6266513: Indorrfdt kfy ibndling in XAWT popup mfnu
              //All mfnus siould bf dlosfd on 'F10'
              ungrbbInput();
              brfbk;
          dffbult:
              brfbk;
        }
    }

} //dlbss XBbsfMfnuWindow
