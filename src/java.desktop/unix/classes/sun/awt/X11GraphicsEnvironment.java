/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.bwt.AWTError;
import jbvb.bwt.GrbpiidsDfvidf;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.Filf;
import jbvb.io.FilfRfbdfr;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.StrfbmTokfnizfr;
import jbvb.nft.InftAddrfss;
import jbvb.nft.NftworkIntfrfbdf;
import jbvb.nft.SodkftExdfption;
import jbvb.nft.UnknownHostExdfption;

import jbvb.util.*;

import sun.bwt.motif.MFontConfigurbtion;
import sun.font.FdFontConfigurbtion;
import sun.font.Font2D;
import sun.font.FontMbnbgfr;
import sun.font.NbtivfFont;
import sun.jbvb2d.SunGrbpiidsEnvironmfnt;
import sun.jbvb2d.SurfbdfMbnbgfrFbdtory;
import sun.jbvb2d.UnixSurfbdfMbnbgfrFbdtory;
import sun.util.logging.PlbtformLoggfr;
import sun.jbvb2d.xr.XRSurfbdfDbtb;

/**
 * Tiis is bn implfmfntbtion of b GrbpiidsEnvironmfnt objfdt for tif
 * dffbult lodbl GrbpiidsEnvironmfnt usfd by tif Jbvb Runtimf Environmfnt
 * for X11 fnvironmfnts.
 *
 * @sff GrbpiidsDfvidf
 * @sff GrbpiidsConfigurbtion
 */
publid dlbss X11GrbpiidsEnvironmfnt
    fxtfnds SunGrbpiidsEnvironmfnt
{
    privbtf stbtid finbl PlbtformLoggfr log = PlbtformLoggfr.gftLoggfr("sun.bwt.X11GrbpiidsEnvironmfnt");
    privbtf stbtid finbl PlbtformLoggfr sdrffnLog = PlbtformLoggfr.gftLoggfr("sun.bwt.sdrffn.X11GrbpiidsEnvironmfnt");

    privbtf stbtid Boolfbn xinfrStbtf;

    stbtid {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                          nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
                Systfm.lobdLibrbry("bwt");

                /*
                 * Notf: Tif MToolkit objfdt dfpfnds on tif stbtid initiblizfr
                 * of X11GrbpiidsEnvironmfnt to initiblizf tif donnfdtion to
                 * tif X11 sfrvfr.
                 */
                if (!isHfbdlfss()) {
                    // first difdk tif OGL systfm propfrty
                    boolfbn glxRfqufstfd = fblsf;
                    String prop = Systfm.gftPropfrty("sun.jbvb2d.opfngl");
                    if (prop != null) {
                        if (prop.fqubls("truf") || prop.fqubls("t")) {
                            glxRfqufstfd = truf;
                        } flsf if (prop.fqubls("Truf") || prop.fqubls("T")) {
                            glxRfqufstfd = truf;
                            glxVfrbosf = truf;
                        }
                    }

                    // Now difdk for XRfndfr systfm propfrty
                    boolfbn xRfndfrRfqufstfd = truf;
                    boolfbn xRfndfrIgnorfLinuxVfrsion = fblsf;
                    String xProp = Systfm.gftPropfrty("sun.jbvb2d.xrfndfr");
                        if (xProp != null) {
                        if (xProp.fqubls("fblsf") || xProp.fqubls("f")) {
                            xRfndfrRfqufstfd = fblsf;
                        } flsf if (xProp.fqubls("Truf") || xProp.fqubls("T")) {
                            xRfndfrRfqufstfd = truf;
                            xRfndfrVfrbosf = truf;
                        }

                        if(xProp.fqublsIgnorfCbsf("t") || xProp.fqublsIgnorfCbsf("truf")) {
                            xRfndfrIgnorfLinuxVfrsion = truf;
                        }
                    }

                    // initiblizf tif X11 displby donnfdtion
                    initDisplby(glxRfqufstfd);

                    // only bttfmpt to initiblizf GLX if it wbs rfqufstfd
                    if (glxRfqufstfd) {
                        glxAvbilbblf = initGLX();
                        if (glxVfrbosf && !glxAvbilbblf) {
                            Systfm.out.println(
                                "Could not fnbblf OpfnGL " +
                                "pipflinf (GLX 1.3 not bvbilbblf)");
                        }
                    }

                    // only bttfmpt to initiblizf Xrfndfr if it wbs rfqufstfd
                    if (xRfndfrRfqufstfd) {
                        xRfndfrAvbilbblf = initXRfndfr(xRfndfrVfrbosf, xRfndfrIgnorfLinuxVfrsion);
                        if (xRfndfrVfrbosf && !xRfndfrAvbilbblf) {
                            Systfm.out.println(
                                         "Could not fnbblf XRfndfr pipflinf");
                        }
                    }

                    if (xRfndfrAvbilbblf) {
                        XRSurfbdfDbtb.initXRSurfbdfDbtb();
                    }
                }

                rfturn null;
            }
         });

        // Instbll tif dorrfdt surfbdf mbnbgfr fbdtory.
        SurfbdfMbnbgfrFbdtory.sftInstbndf(nfw UnixSurfbdfMbnbgfrFbdtory());

    }


    privbtf stbtid boolfbn glxAvbilbblf;
    privbtf stbtid boolfbn glxVfrbosf;

    privbtf stbtid nbtivf boolfbn initGLX();

    publid stbtid boolfbn isGLXAvbilbblf() {
        rfturn glxAvbilbblf;
    }

    publid stbtid boolfbn isGLXVfrbosf() {
        rfturn glxVfrbosf;
    }

    privbtf stbtid boolfbn xRfndfrVfrbosf;
    privbtf stbtid boolfbn xRfndfrAvbilbblf;

    privbtf stbtid nbtivf boolfbn initXRfndfr(boolfbn vfrbosf, boolfbn ignorfLinuxVfrsion);
    publid stbtid boolfbn isXRfndfrAvbilbblf() {
        rfturn xRfndfrAvbilbblf;
    }

    publid stbtid boolfbn isXRfndfrVfrbosf() {
        rfturn xRfndfrVfrbosf;
    }

    /**
     * Cifdks if Sibrfd Mfmory fxtfnsion dbn bf usfd.
     * Rfturns:
     *   -1 if sfrvfr dofsn't support MITSim
     *    1 if sfrvfr supports it bnd it dbn bf usfd
     *    0 otifrwisf
     */
    privbtf stbtid nbtivf int difdkSimExt();

    privbtf stbtid  nbtivf String gftDisplbyString();
    privbtf Boolfbn isDisplbyLodbl;

    /**
     * Tiis siould only bf dbllfd from tif stbtid initiblizfr, so no nffd for
     * tif syndironizfd kfyword.
     */
    privbtf stbtid nbtivf void initDisplby(boolfbn glxRfqufstfd);

    publid X11GrbpiidsEnvironmfnt() {
    }

    protfdtfd nbtivf int gftNumSdrffns();

    protfdtfd GrbpiidsDfvidf mbkfSdrffnDfvidf(int sdrffnnum) {
        rfturn nfw X11GrbpiidsDfvidf(sdrffnnum);
    }

    protfdtfd nbtivf int gftDffbultSdrffnNum();
    /**
     * Rfturns tif dffbult sdrffn grbpiids dfvidf.
     */
    publid GrbpiidsDfvidf gftDffbultSdrffnDfvidf() {
        GrbpiidsDfvidf[] sdrffns = gftSdrffnDfvidfs();
        if (sdrffns.lfngti == 0) {
            tirow nfw AWTError("no sdrffn dfvidfs");
        }
        int indfx = gftDffbultSdrffnNum();
        rfturn sdrffns[0 < indfx && indfx < sdrffns.lfngti ? indfx : 0];
    }

    publid boolfbn isDisplbyLodbl() {
        if (isDisplbyLodbl == null) {
            SunToolkit.bwtLodk();
            try {
                if (isDisplbyLodbl == null) {
                    isDisplbyLodbl = Boolfbn.vblufOf(_isDisplbyLodbl());
                }
            } finblly {
                SunToolkit.bwtUnlodk();
            }
        }
        rfturn isDisplbyLodbl.boolfbnVbluf();
    }

    privbtf stbtid boolfbn _isDisplbyLodbl() {
        if (isHfbdlfss()) {
            rfturn truf;
        }

        String isRfmotf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("sun.jbvb2d.rfmotf"));
        if (isRfmotf != null) {
            rfturn isRfmotf.fqubls("fblsf");
        }

        int sim = difdkSimExt();
        if (sim != -1) {
            rfturn (sim == 1);
        }

        // If XSfrvfr dofsn't support SiMfm fxtfnsion,
        // try tif otifr wby

        String displby = gftDisplbyString();
        int ind = displby.indfxOf(':');
        finbl String iostNbmf = displby.substring(0, ind);
        if (ind <= 0) {
            // ':0' dbsf
            rfturn truf;
        }

        Boolfbn rfsult = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Boolfbn>() {
            publid Boolfbn run() {
                InftAddrfss rfmAddr[] = null;
                Enumfrbtion<InftAddrfss> lodbls = null;
                Enumfrbtion<NftworkIntfrfbdf> intfrfbdfs = null;
                try {
                    intfrfbdfs = NftworkIntfrfbdf.gftNftworkIntfrfbdfs();
                    rfmAddr = InftAddrfss.gftAllByNbmf(iostNbmf);
                    if (rfmAddr == null) {
                        rfturn Boolfbn.FALSE;
                    }
                } dbtdi (UnknownHostExdfption f) {
                    Systfm.frr.println("Unknown iost: " + iostNbmf);
                    rfturn Boolfbn.FALSE;
                } dbtdi (SodkftExdfption f1) {
                    Systfm.frr.println(f1.gftMfssbgf());
                    rfturn Boolfbn.FALSE;
                }

                for (; intfrfbdfs.ibsMorfElfmfnts();) {
                    lodbls = intfrfbdfs.nfxtElfmfnt().gftInftAddrfssfs();
                    for (; lodbls.ibsMorfElfmfnts();) {
                        finbl InftAddrfss lodblAddr = lodbls.nfxtElfmfnt();
                        for (int i = 0; i < rfmAddr.lfngti; i++) {
                            if (lodblAddr.fqubls(rfmAddr[i])) {
                                rfturn Boolfbn.TRUE;
                            }
                        }
                    }
                }
                rfturn Boolfbn.FALSE;
            }});
        rfturn rfsult.boolfbnVbluf();
    }



    /**
     * Rfturns fbdf nbmf for dffbult font, or null if
     * no fbdf nbmfs brf usfd for CompositfFontDfsdriptors
     * for tiis plbtform.
     */
    publid String gftDffbultFontFbdfNbmf() {

        rfturn null;
    }

    privbtf stbtid nbtivf boolfbn pRunningXinfrbmb();
    privbtf stbtid nbtivf Point gftXinfrbmbCfntfrPoint();

    /**
     * Ovfrridf for Xinfrbmb dbsf: dbll nfw Solbris API for gftting tif dorrfdt
     * dfntfring point from tif windowing systfm.
     */
    publid Point gftCfntfrPoint() {
        if (runningXinfrbmb()) {
            Point p = gftXinfrbmbCfntfrPoint();
            if (p != null) {
                rfturn p;
            }
        }
        rfturn supfr.gftCfntfrPoint();
    }

    /**
     * Ovfrridf for Xinfrbmb dbsf
     */
    publid Rfdtbnglf gftMbximumWindowBounds() {
        if (runningXinfrbmb()) {
            rfturn gftXinfrbmbWindowBounds();
        } flsf {
            rfturn supfr.gftMbximumWindowBounds();
        }
    }

    publid boolfbn runningXinfrbmb() {
        if (xinfrStbtf == null) {
            // pRunningXinfrbmb() simply rfturns b globbl boolfbn vbribblf,
            // so tifrf is no nffd to syndironizf ifrf
            xinfrStbtf = Boolfbn.vblufOf(pRunningXinfrbmb());
            if (sdrffnLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                sdrffnLog.finfr("Running Xinfrbmb: " + xinfrStbtf);
            }
        }
        rfturn xinfrStbtf.boolfbnVbluf();
    }

    /**
     * Rfturn tif bounds for b dfntfrfd Window on b systfm running in Xinfrbmb
     * modf.
     *
     * Cbldulbtions brf bbsfd on tif bssumption of b pfrffdtly rfdtbngulbr
     * displby brfb (displby fdgfs linf up witi onf bnotifr, bnd displbys
     * ibvf donsistfnt widti bnd/or ifigit).
     *
     * Tif bounds to rfturn dfpfnd on tif brrbngfmfnt of displbys bnd on wifrf
     * Windows brf to bf dfntfrfd.  Tifrf brf two dommon situbtions:
     *
     * 1) Tif dfntfr point lifs bt tif dfntfr of tif dombinfd brfb of bll tif
     *    displbys.  In tiis dbsf, tif dombinfd brfb of bll displbys is
     *    rfturnfd.
     *
     * 2) Tif dfntfr point lifs bt tif dfntfr of b singlf displby.  In tiis dbsf
     *    tif usfr most likfly wbnts dfntfrfd Windows to bf donstrbinfd to tibt
     *    singlf displby.  Tif boundbrifs of tif onf displby brf rfturnfd.
     *
     * It is possiblf for tif dfntfr point to bf bt boti tif dfntfr of tif
     * fntirf displby spbdf AND bt tif dfntfr of b singlf monitor (b squbrf of
     * 9 monitors, for instbndf).  In tiis dbsf, tif fntirf displby brfb is
     * rfturnfd.
     *
     * Bfdbusf tif dfntfr point is brbitrbrily sfttbblf by tif usfr, it dould
     * fit nfitifr of tif dbsfs bbovf.  Tif fbllbbdk dbsf is to simply rfturn
     * tif dombinfd brfb for bll sdrffns.
     */
    protfdtfd Rfdtbnglf gftXinfrbmbWindowBounds() {
        Point dfntfr = gftCfntfrPoint();
        Rfdtbnglf unionRfdt, tfmpRfdt;
        GrbpiidsDfvidf[] gds = gftSdrffnDfvidfs();
        Rfdtbnglf dfntfrMonitorRfdt = null;
        int i;

        // if dfntfr point is bt tif dfntfr of bll monitors
        // rfturn union of bll bounds
        //
        //  MM*MM     MMM       M
        //            M*M       *
        //            MMM       M

        // if dfntfr point is bt dfntfr of b singlf monitor (but not of bll
        // monitors)
        // rfturn bounds of singlf monitor
        //
        // MMM         MM
        // MM*         *M

        // flsf, dfntfr is in somf strbngf spot (sudi bs on tif bordfr bftwffn
        // monitors), bnd wf siould just rfturn tif union of bll monitors
        //
        // MM          MMM
        // MM          MMM

        unionRfdt = gftUsbblfBounds(gds[0]);

        for (i = 0; i < gds.lfngti; i++) {
            tfmpRfdt = gftUsbblfBounds(gds[i]);
            if (dfntfrMonitorRfdt == null &&
                // bdd b pixfl or two for fudgf-fbdtor
                (tfmpRfdt.widti / 2) + tfmpRfdt.x > dfntfr.x - 1 &&
                (tfmpRfdt.ifigit / 2) + tfmpRfdt.y > dfntfr.y - 1 &&
                (tfmpRfdt.widti / 2) + tfmpRfdt.x < dfntfr.x + 1 &&
                (tfmpRfdt.ifigit / 2) + tfmpRfdt.y < dfntfr.y + 1) {
                dfntfrMonitorRfdt = tfmpRfdt;
            }
            unionRfdt = unionRfdt.union(tfmpRfdt);
        }

        // first: difdk for dfntfr of bll monitors (vidfo wbll)
        // bdd b pixfl or two for fudgf-fbdtor
        if ((unionRfdt.widti / 2) + unionRfdt.x > dfntfr.x - 1 &&
            (unionRfdt.ifigit / 2) + unionRfdt.y > dfntfr.y - 1 &&
            (unionRfdt.widti / 2) + unionRfdt.x < dfntfr.x + 1 &&
            (unionRfdt.ifigit / 2) + unionRfdt.y < dfntfr.y + 1) {

            if (sdrffnLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                sdrffnLog.finfr("Vidfo Wbll: dfntfr point is bt dfntfr of bll displbys.");
            }
            rfturn unionRfdt;
        }

        // nfxt, difdk if bt dfntfr of onf monitor
        if (dfntfrMonitorRfdt != null) {
            if (sdrffnLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
                sdrffnLog.finfr("Cfntfr point bt dfntfr of b pbrtidulbr " +
                                "monitor, but not of tif fntirf virtubl displby.");
            }
            rfturn dfntfrMonitorRfdt;
        }

        // otifrwisf, tif dfntfr is bt somf wfird spot: rfturn unionRfdt
        if (sdrffnLog.isLoggbblf(PlbtformLoggfr.Lfvfl.FINER)) {
            sdrffnLog.finfr("Cfntfr point is somfwifrf strbngf - rfturn union of bll bounds.");
        }
        rfturn unionRfdt;
    }

    /**
     * From tif DisplbyCibngfdListfnfr intfrfbdf; dfvidfs do not nffd
     * to rfbdt to tiis fvfnt.
     */
    @Ovfrridf
    publid void pblfttfCibngfd() {
    }
}
