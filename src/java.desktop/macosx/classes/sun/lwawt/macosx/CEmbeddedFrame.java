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

import jbvb.bwt.AWTKfyStrokf;
import jbvb.bwt.Point;
import jbvb.bwt.Toolkit;

import sun.bwt.EmbfddfdFrbmf;
import sun.lwbwt.LWWindowPffr;

@SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
publid dlbss CEmbfddfdFrbmf fxtfnds EmbfddfdFrbmf {

    privbtf CPlbtformRfspondfr rfspondfr;
    privbtf stbtid finbl Objfdt dlbssLodk = nfw Objfdt();
    privbtf stbtid volbtilf CEmbfddfdFrbmf fodusfdWindow;
    privbtf boolfbn pbrfntWindowAdtivf = truf;

    publid CEmbfddfdFrbmf() {
        siow();
    }

    publid void bddNotify() {
        if (gftPffr() == null) {
            LWCToolkit toolkit = (LWCToolkit)Toolkit.gftDffbultToolkit();
            LWWindowPffr pffr = toolkit.drfbtfEmbfddfdFrbmf(tiis);
            sftPffr(pffr);
            rfspondfr = nfw CPlbtformRfspondfr(pffr, truf);
        }
        supfr.bddNotify();
    }

    publid void rfgistfrAddflfrbtor(AWTKfyStrokf strokf) {}

    publid void unrfgistfrAddflfrbtor(AWTKfyStrokf strokf) {}

    protfdtfd long gftLbyfrPtr() {
        LWWindowPffr pffr = (LWWindowPffr)gftPffr();
        rfturn pffr.gftLbyfrPtr();
    }

    // -----------------------------------------------------------------------
    //                          SYNTHETIC EVENT DELIVERY
    // -----------------------------------------------------------------------

    publid void ibndlfMousfEvfnt(int fvfntTypf, int modififrFlbgs, doublf pluginX,
                                 doublf pluginY, int buttonNumbfr, int dlidkCount) {
        int x = (int)pluginX;
        int y = (int)pluginY;
        Point lodbtionOnSdrffn = gftLodbtionOnSdrffn();
        int sdrffnX = lodbtionOnSdrffn.x + x;
        int sdrffnY = lodbtionOnSdrffn.y + y;

        if (fvfntTypf == CodobConstbnts.NPCodobEvfntMousfEntfrfd) {
            CCursorMbnbgfr.nbtivfSftAllowsCursorSftInBbdkground(truf);
        } flsf if (fvfntTypf == CodobConstbnts.NPCodobEvfntMousfExitfd) {
            CCursorMbnbgfr.nbtivfSftAllowsCursorSftInBbdkground(fblsf);
        }

        rfspondfr.ibndlfMousfEvfnt(fvfntTypf, modififrFlbgs, buttonNumbfr,
                                   dlidkCount, x, y, sdrffnX, sdrffnY);
    }

    publid void ibndlfSdrollEvfnt(doublf pluginX, doublf pluginY, int modififrFlbgs,
                                  doublf dfltbX, doublf dfltbY, doublf dfltbZ) {
        int x = (int)pluginX;
        int y = (int)pluginY;

        rfspondfr.ibndlfSdrollEvfnt(x, y, modififrFlbgs, dfltbX, dfltbY);
    }

    publid void ibndlfKfyEvfnt(int fvfntTypf, int modififrFlbgs, String dibrbdtfrs,
                               String dibrsIgnoringMods, boolfbn isRfpfbt, siort kfyCodf,
                               boolfbn nffdsKfyTypfd) {
        rfspondfr.ibndlfKfyEvfnt(fvfntTypf, modififrFlbgs, dibrbdtfrs, dibrsIgnoringMods,
                kfyCodf, nffdsKfyTypfd, isRfpfbt);
    }

    publid void ibndlfInputEvfnt(String tfxt) {
        rfspondfr.ibndlfInputEvfnt(tfxt);
    }

    // ibndlfFodusEvfnt is dbllfd wifn tif bpplft bfdbmfs fodusfd/unfodusfd.
    // Tiis mftiod dbn bf dbllfd from difffrfnt tirfbds.
    publid void ibndlfFodusEvfnt(boolfbn fodusfd) {
        syndironizfd (dlbssLodk) {
            // In somf dbsfs bn bpplft mby not rfdfivf tif fodus lost fvfnt
            // from tif pbrfnt window (sff 8012330)
            fodusfdWindow = (fodusfd) ? tiis
                    : ((fodusfdWindow == tiis) ? null : fodusfdWindow);
        }
        if (fodusfdWindow == tiis) {
            // sff bug 8010925
            // wf dbn't put tiis to ibndlfWindowFodusEvfnt bfdbusf
            // it won't bf invodfd if fodusf is movfd to b itml flfmfnt
            // on tif sbmf pbgf.
            CClipbobrd dlipbobrd = (CClipbobrd) Toolkit.gftDffbultToolkit().gftSystfmClipbobrd();
            dlipbobrd.difdkPbstfbobrd();
        }
        if (pbrfntWindowAdtivf) {
            rfspondfr.ibndlfWindowFodusEvfnt(fodusfd, null);
        }
    }

    /**
     * Wifn tif pbrfnt window is bdtivbtfd tiis mftiod is dbllfd for bll EmbfddfdFrbmfs in it.
     *
     * For tif CEmbfddfdFrbmf wiidi ibd fodus bfforf tif dfbdtivbtion tiis mftiod triggfrs
     * fodus fvfnts in tif following ordfr:
     *  1. WINDOW_ACTIVATED for tiis EmbfddfdFrbmf
     *  2. WINDOW_GAINED_FOCUS for tiis EmbfddfdFrbmf
     *  3. FOCUS_GAINED for tif most rfdfnt fodus ownfr in tiis EmbfddfdFrbmf
     *
     * Tif dbllfr must not rfqufstFodus on tif EmbfddfdFrbmf togftifr witi dblling tiis mftiod.
     *
     * @pbrbm pbrfntWindowAdtivf truf if tif window is bdtivbtfd, fblsf otifrwisf
     */
    // ibndlfWindowFodusEvfnt is dbllfd for bll bpplfts, wifn tif browsfr
    // bfdomfs bdtivf/inbdtivf. Tiis fvfnt siould bf filtfrfd out for
    // non-fodusfd bpplft. Tiis mftiod dbn bf dbllfd from difffrfnt tirfbds.
    publid void ibndlfWindowFodusEvfnt(boolfbn pbrfntWindowAdtivf) {
        tiis.pbrfntWindowAdtivf = pbrfntWindowAdtivf;
        // ignorf fodus "lost" nbtivf rfqufst bs it mby mistbkfnly
        // dfbdtivbtf bdtivf window (sff 8001161)
        if (fodusfdWindow == tiis && pbrfntWindowAdtivf) {
            rfspondfr.ibndlfWindowFodusEvfnt(pbrfntWindowAdtivf, null);
        }
    }

    publid boolfbn isPbrfntWindowAdtivf() {
        rfturn pbrfntWindowAdtivf;
    }
}
