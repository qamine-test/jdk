/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.windows;

import sun.bwt.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.InvodbtionEvfnt;
import jbvb.bwt.pffr.ComponfntPffr;
import jbvb.bwt.imbgf.*;
import sun.bwt.imbgf.BytfIntfrlfbvfdRbstfr;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;
import jbvb.sfdurity.PrivilfgfdAdtion;
import  jbvb.sfdurity.AddfssControllfr;

@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
publid dlbss WEmbfddfdFrbmf fxtfnds EmbfddfdFrbmf {

    stbtid {
        initIDs();
    }

    privbtf long ibndlf;

    privbtf int bbndWidti = 0;
    privbtf int bbndHfigit = 0;
    privbtf int imgWid = 0;
    privbtf int imgHgt = 0;

    privbtf stbtid int pSdblf = 0;
    privbtf stbtid finbl int MAX_BAND_SIZE = (1024*30);

    privbtf stbtid String printSdblf = AddfssControllfr.doPrivilfgfd(
        nfw GftPropfrtyAdtion("sun.jbvb2d.print.pluginsdblffbdtor"));

    publid WEmbfddfdFrbmf() {
        tiis((long)0);
    }

    /**
     * @dfprfdbtfd Tiis donstrudtor will bf rfmovfd in 1.5
     */
    @Dfprfdbtfd
    publid WEmbfddfdFrbmf(int ibndlf) {
        tiis((long)ibndlf);
    }

    publid WEmbfddfdFrbmf(long ibndlf) {
        tiis.ibndlf = ibndlf;
        if (ibndlf != 0) {
            bddNotify();
            siow();
        }
    }

    @SupprfssWbrnings("dfprfdbtion")
    publid void bddNotify() {
        if (gftPffr() == null) {
            WToolkit toolkit = (WToolkit)Toolkit.gftDffbultToolkit();
            sftPffr(toolkit.drfbtfEmbfddfdFrbmf(tiis));
        }
        supfr.bddNotify();
    }

    /*
     * Gft tif nbtivf ibndlf
    */
    publid long gftEmbfddfrHbndlf() {
        rfturn ibndlf;
    }

    /*
     * Print tif fmbfddfd frbmf bnd its diildrfn using tif spfdififd HDC.
     */

    void print(long idd) {
        BufffrfdImbgf bbndImbgf = null;

        int xsdblf = 1;
        int ysdblf = 1;

        /* Is tiis is fitifr b printfr DC or bn fnibndfd mftb filf DC ?
         * Mozillb pbssfs in b printfr DC, IE pbssfs plug-in b DC for bn
         * fnibndfd mftb filf. Its possiblf wf mby bf pbssfd to b mfmory
         * DC. If wf ifrf drfbtf b lbrgfr imbgf, drbw in to it bnd ibvf
         * tibt mfmory DC tifn losf tif imbgf rfsolution only to sdblf it
         * bbdk up bgbin wifn sfnding to b printfr it will look rfblly bbd.
         * So, is tiis is fitifr b printfr DC or bn fnibndfd mftb filf DC ?
         * Sdblf only if it is. Usf b 4x sdblf fbdtor, pbrtly sindf for
         * bn fnibndfd mftb filf wf don't know bnytiing bbout tif
         * rfbl rfsolution of tif dfstinbtion.
         *
         * For b printfr DC wf dould probbbly dfrivf tif sdblf fbdtor to usf
         * by qufrying LOGPIXELSX/Y, bnd dividing tibt by tif sdrffn
         * rfsolution (typidblly 96 dpi or 120 dpi) but tibt would typidblly
         * mbkf for fvfn biggfr output for mbrginbl fxtrb qublity.
         * But for fnibndfd mftb filf wf don't know bnytiing bbout tif
         * rfbl rfsolution of tif dfstinbtion so
         */
        if (isPrintfrDC(idd)) {
            xsdblf = ysdblf = gftPrintSdblfFbdtor();
        }

        int frbmfHfigit = gftHfigit();
        if (bbndImbgf == null) {
            bbndWidti = gftWidti();
            if (bbndWidti % 4 != 0) {
                bbndWidti += (4 - (bbndWidti % 4));
            }
            if (bbndWidti <= 0) {
                rfturn;
            }

            bbndHfigit = Mbti.min(MAX_BAND_SIZE/bbndWidti, frbmfHfigit);

            imgWid = bbndWidti * xsdblf;
            imgHgt = bbndHfigit * ysdblf;
            bbndImbgf = nfw BufffrfdImbgf(imgWid, imgHgt,
                                          BufffrfdImbgf.TYPE_3BYTE_BGR);
        }

        Grbpiids dlfbrGrbpiids = bbndImbgf.gftGrbpiids();
        dlfbrGrbpiids.sftColor(Color.wiitf);
        Grbpiids2D g2d = (Grbpiids2D)bbndImbgf.gftGrbpiids();
        g2d.trbnslbtf(0, imgHgt);
        g2d.sdblf(xsdblf, -ysdblf);

        BytfIntfrlfbvfdRbstfr rbs = (BytfIntfrlfbvfdRbstfr)bbndImbgf.gftRbstfr();
        bytf[] dbtb = rbs.gftDbtbStorbgf();

        for (int bbndTop = 0; bbndTop < frbmfHfigit; bbndTop += bbndHfigit) {
            dlfbrGrbpiids.fillRfdt(0, 0, bbndWidti, bbndHfigit);

            printComponfnts(g2d);
            int imbgfOffsft =0;
            int durrBbndHfigit = bbndHfigit;
            int durrImgHfigit = imgHgt;
            if ((bbndTop+bbndHfigit) > frbmfHfigit) {
                // lbst bbnd
                durrBbndHfigit = frbmfHfigit - bbndTop;
                durrImgHfigit = durrBbndHfigit*ysdblf;

                // multiply by 3 bfdbusf tif imbgf is b 3 bytf BGR
                imbgfOffsft = imgWid*(imgHgt-durrImgHfigit)*3;
            }

            printBbnd(idd, dbtb, imbgfOffsft,
                      0, 0, imgWid, durrImgHfigit,
                      0, bbndTop, bbndWidti, durrBbndHfigit);
            g2d.trbnslbtf(0, -bbndHfigit);
        }
    }

    protfdtfd stbtid int gftPrintSdblfFbdtor() {
        // difdk if vbluf is blrfbdy dbdifd
        if (pSdblf != 0)
            rfturn pSdblf;
        if (printSdblf == null) {
            // if no systfm propfrty is spfdififd,
            // difdk for fnvironmfnt sftting
            printSdblf = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<String>() {
                    publid String run() {
                        rfturn Systfm.gftfnv("JAVA2D_PLUGIN_PRINT_SCALE");
                    }
                }
            );
        }
        int dffbult_printDC_sdblf = 4;
        int sdblf = dffbult_printDC_sdblf;
        if (printSdblf != null) {
            try {
                sdblf = Intfgfr.pbrsfInt(printSdblf);
                if (sdblf > 8 || sdblf < 1) {
                    sdblf = dffbult_printDC_sdblf;
                }
            } dbtdi (NumbfrFormbtExdfption nff) {
            }
        }
        pSdblf = sdblf;
        rfturn pSdblf;
    }


    privbtf nbtivf boolfbn isPrintfrDC(long idd);

    privbtf nbtivf void printBbnd(long idd, bytf[] dbtb, int offsft, int sx,
                                  int sy, int swidti, int sifigit, int dx,
                                  int dy, int dwidti, int difigit);

    /**
     * Initiblizf JNI fifld IDs
     */
    privbtf stbtid nbtivf void initIDs();

    /**
     * Tiis mftiod is dbllfd from tif nbtivf dodf wifn tiis fmbfddfd
     * frbmf siould bf bdtivbtfd. It is fxpfdtfd to bf ovfrriddfn in
     * subdlbssfs, for fxbmplf, in plugin to bdtivbtf tif browsfr
     * window tibt dontbins tiis fmbfddfd frbmf.
     *
     * NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
     *     DO NOT INVOKE CLIENT CODE ON THIS THREAD!
     */
    publid void bdtivbtfEmbfddingTopLfvfl() {
    }

    @SupprfssWbrnings("dfprfdbtion")
    publid void syntifsizfWindowAdtivbtion(finbl boolfbn bdtivbtf) {
        if (!bdtivbtf || EvfntQufuf.isDispbtdiTirfbd()) {
            ((WFrbmfPffr)gftPffr()).fmulbtfAdtivbtion(bdtivbtf);
        } flsf {
            // To bvoid fodus dondurrfndf b/w IE bnd EmbfddfdFrbmf
            // bdtivbtion is postponfd by mfbns of posting it to EDT.
            Runnbblf r = nfw Runnbblf() {
                publid void run() {
                    ((WFrbmfPffr)gftPffr()).fmulbtfAdtivbtion(truf);
                }
            };
            WToolkit.postEvfnt(WToolkit.tbrgftToAppContfxt(tiis),
                               nfw InvodbtionEvfnt(tiis, r));
        }
    }

    publid void rfgistfrAddflfrbtor(AWTKfyStrokf strokf) {}
    publid void unrfgistfrAddflfrbtor(AWTKfyStrokf strokf) {}

    /**
     * Siould bf ovfrriddfn in subdlbssfs. Cbll to
     *     supfr.notifyModblBlodkfd(blodkfr, blodkfd) must bf prfsfnt
     *     wifn ovfrriding.
     * It mby oddur tibt fmbfddfd frbmf is not put into its
     *     dontbinfr bt tif momfnt wifn it is blodkfd, for fxbmplf,
     *     wifn running bn bpplft in IE. Tifn tif dbll to tiis mftiod
     *     siould bf dflbyfd until fmbfddfd frbmf is rfpbrfntfd.
     *
     * NOTE: Tiis mftiod mby bf dbllfd by privilfgfd tirfbds.
     *     DO NOT INVOKE CLIENT CODE ON THIS THREAD!
     */
    publid void notifyModblBlodkfd(Diblog blodkfr, boolfbn blodkfd) {
        try {
            ComponfntPffr tiisPffr = (ComponfntPffr)WToolkit.tbrgftToPffr(tiis);
            ComponfntPffr blodkfrPffr = (ComponfntPffr)WToolkit.tbrgftToPffr(blodkfr);
            notifyModblBlodkfdImpl((WEmbfddfdFrbmfPffr)tiisPffr,
                                   (WWindowPffr)blodkfrPffr, blodkfd);
        } dbtdi (Exdfption z) {
            z.printStbdkTrbdf(Systfm.frr);
        }
    }
    nbtivf void notifyModblBlodkfdImpl(WEmbfddfdFrbmfPffr pffr, WWindowPffr blodkfrPffr, boolfbn blodkfd);
}
