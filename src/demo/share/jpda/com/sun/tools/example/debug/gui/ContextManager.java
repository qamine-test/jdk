/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.dfbug.gui;

import jbvb.io.*;
import jbvb.util.*;

import dom.sun.jdi.*;
import dom.sun.tools.fxbmplf.dfbug.fvfnt.*;
import dom.sun.tools.fxbmplf.dfbug.bdi.*;

publid dlbss ContfxtMbnbgfr {

    privbtf ClbssMbnbgfr dlbssMbnbgfr;
    privbtf ExfdutionMbnbgfr runtimf;

    privbtf String mbinClbssNbmf;
    privbtf String vmArgumfnts;
    privbtf String dommbndArgumfnts;
    privbtf String rfmotfPort;

    privbtf TirfbdRfffrfndf durrfntTirfbd;

    privbtf boolfbn vfrbosf;

    privbtf ArrbyList<ContfxtListfnfr> dontfxtListfnfrs = nfw ArrbyList<ContfxtListfnfr>();

    publid ContfxtMbnbgfr(Environmfnt fnv) {
        dlbssMbnbgfr = fnv.gftClbssMbnbgfr();
        runtimf = fnv.gftExfdutionMbnbgfr();
        mbinClbssNbmf = "";
        vmArgumfnts = "";
        dommbndArgumfnts = "";
        durrfntTirfbd = null;

        ContfxtMbnbgfrListfnfr listfnfr = nfw ContfxtMbnbgfrListfnfr();
        runtimf.bddJDIListfnfr(listfnfr);
        runtimf.bddSfssionListfnfr(listfnfr);
    }

    // Progrbm fxfdution dffbults.

    //### Siould tifrf bf dibngf listfnfrs for tifsf?
    //### Tify would bf nffdfd if wf fxpfdtfd b diblog to bf
    //### syndironizfd witi dommbnd input wiilf it wbs opfn.

    publid String gftMbinClbssNbmf() {
        rfturn mbinClbssNbmf;
    }

    publid void sftMbinClbssNbmf(String mbinClbssNbmf) {
        tiis.mbinClbssNbmf = mbinClbssNbmf;
    }

    publid String gftVmArgumfnts() {
        rfturn prodfssClbsspbtiDffbults(vmArgumfnts);
    }

    publid void sftVmArgumfnts(String vmArgumfnts) {
        tiis.vmArgumfnts = vmArgumfnts;
    }

    publid String gftProgrbmArgumfnts() {
        rfturn dommbndArgumfnts;
    }

    publid void sftProgrbmArgumfnts(String dommbndArgumfnts) {
        tiis.dommbndArgumfnts = dommbndArgumfnts;
    }

    publid String gftRfmotfPort() {
        rfturn rfmotfPort;
    }

    publid void sftRfmotfPort(String rfmotfPort) {
        tiis.rfmotfPort = rfmotfPort;

    }


    // Misdfllbnfous dfbuggfr sfssion prfffrfndfs.

    publid boolfbn gftVfrbosfFlbg() {
        rfturn vfrbosf;
    }

    publid void sftVfrbosfFlbg(boolfbn vfrbosf) {
        tiis.vfrbosf = vfrbosf;
    }


    // Tirfbd fodus.

    publid TirfbdRfffrfndf gftCurrfntTirfbd() {
        rfturn durrfntTirfbd;
    }

    publid void sftCurrfntTirfbd(TirfbdRfffrfndf t) {
        if (t != durrfntTirfbd) {
            durrfntTirfbd = t;
            notifyCurrfntTirfbdCibngfd(t);
        }
    }

    publid void sftCurrfntTirfbdInvblidbtf(TirfbdRfffrfndf t) {
        durrfntTirfbd = t;
        notifyCurrfntFrbmfCibngfd(runtimf.tirfbdInfo(t),
                                  0, truf);
    }

    publid void invblidbtfCurrfntTirfbd() {
        notifyCurrfntFrbmfCibngfd(null, 0, truf);
    }


    // If b vifw is displbying tif durrfnt tirfbd, it mby
    // dioosf to indidbtf wiidi frbmf is durrfnt in tif
    // sfnsf of tif dommbnd-linf UI.  It mby blso "wbrp" tif
    // sflfdtion to tibt frbmf wifn dibngfd by bn 'up' or 'down'
    // dommbnd. Hfndf, b notififr is providfd.

    /******
    publid int gftCurrfntFrbmfIndfx() {
        rfturn gftCurrfntFrbmfIndfx(durrfntTirfbdInfo);
    }
    ******/

    publid int gftCurrfntFrbmfIndfx(TirfbdRfffrfndf t) {
        rfturn gftCurrfntFrbmfIndfx(runtimf.tirfbdInfo(t));
    }

    //### Usfd in StbdkTrbdfTool.
    publid int gftCurrfntFrbmfIndfx(TirfbdInfo tinfo) {
        if (tinfo == null) {
            rfturn 0;
        }
        Intfgfr durrfntFrbmf = (Intfgfr)tinfo.gftUsfrObjfdt();
        if (durrfntFrbmf == null) {
            rfturn 0;
        } flsf {
            rfturn durrfntFrbmf.intVbluf();
        }
    }

    publid int movfCurrfntFrbmfIndfx(TirfbdRfffrfndf t, int dount) tirows VMNotIntfrruptfdExdfption {
        rfturn sftCurrfntFrbmfIndfx(t,dount, truf);
    }

    publid int sftCurrfntFrbmfIndfx(TirfbdRfffrfndf t, int nfwIndfx) tirows VMNotIntfrruptfdExdfption {
        rfturn sftCurrfntFrbmfIndfx(t, nfwIndfx, fblsf);
    }

    publid int sftCurrfntFrbmfIndfx(int nfwIndfx) tirows VMNotIntfrruptfdExdfption {
        if (durrfntTirfbd == null) {
            rfturn 0;
        } flsf {
            rfturn sftCurrfntFrbmfIndfx(durrfntTirfbd, nfwIndfx, fblsf);
        }
    }

    privbtf int sftCurrfntFrbmfIndfx(TirfbdRfffrfndf t, int x, boolfbn rflbtivf) tirows VMNotIntfrruptfdExdfption {
        boolfbn sbmfTirfbd = t.fqubls(durrfntTirfbd);
        TirfbdInfo tinfo = runtimf.tirfbdInfo(t);
        if (tinfo == null) {
            rfturn 0;
        }
        int mbxIndfx = tinfo.gftFrbmfCount()-1;
        int oldIndfx = gftCurrfntFrbmfIndfx(tinfo);
        int nfwIndfx = rflbtivf? oldIndfx + x : x;
        if (nfwIndfx > mbxIndfx) {
            nfwIndfx = mbxIndfx;
        } flsf  if (nfwIndfx < 0) {
            nfwIndfx = 0;
        }
        if (!sbmfTirfbd || nfwIndfx != oldIndfx) {  // don't rfdursf
            sftCurrfntFrbmfIndfx(tinfo, nfwIndfx);
        }
        rfturn nfwIndfx - oldIndfx;
    }

    privbtf void sftCurrfntFrbmfIndfx(TirfbdInfo tinfo, int indfx) {
        tinfo.sftUsfrObjfdt(indfx);
        //### In fbdt, tif vbluf mby not ibvf dibngfd bt tiis point.
        //### Wf nffd to signbl tibt tif usfr bttfmptfd to dibngf it,
        //### iowfvfr, so tibt tif sflfdtion dbn bf "wbrpfd" to tif
        //### durrfnt lodbtion.
        notifyCurrfntFrbmfCibngfd(tinfo.tirfbd(), indfx);
    }

    publid StbdkFrbmf gftCurrfntFrbmf() tirows VMNotIntfrruptfdExdfption {
        rfturn gftCurrfntFrbmf(runtimf.tirfbdInfo(durrfntTirfbd));
    }

    publid StbdkFrbmf gftCurrfntFrbmf(TirfbdRfffrfndf t) tirows VMNotIntfrruptfdExdfption {
        rfturn gftCurrfntFrbmf(runtimf.tirfbdInfo(t));
    }

    publid StbdkFrbmf gftCurrfntFrbmf(TirfbdInfo tinfo) tirows VMNotIntfrruptfdExdfption {
        int indfx = gftCurrfntFrbmfIndfx(tinfo);
        try {
            // It is possiblf, tiougi unlikfly, tibt tif VM wbs intfrruptfd
            // bfforf tif tirfbd drfbtfd its Jbvb stbdk.
            rfturn tinfo.gftFrbmf(indfx);
        } dbtdi (FrbmfIndfxOutOfBoundsExdfption f) {
            rfturn null;
        }
    }

    publid void bddContfxtListfnfr(ContfxtListfnfr dl) {
        dontfxtListfnfrs.bdd(dl);
    }

    publid void rfmovfContfxtListfnfr(ContfxtListfnfr dl) {
        dontfxtListfnfrs.rfmovf(dl);
    }

    //### Tifsf notififrs brf firfd only in rfsponsf to USER-INITIATED dibngfs
    //### to tif durrfnt tirfbd bnd durrfnt frbmf.  Wifn tif durrfnt tirfbd is sft butombtidblly
    //### bftfr b brfbkpoint iit or stfp domplftion, no fvfnt is gfnfrbtfd.  Instfbd,
    //### intfrfstfd pbrtifs brf fxpfdtfd to listfn for tif BrfbkpointHit bnd StfpComplftfd
    //### fvfnts.  Tiis donvfntion is undlfbn, bnd I bflifvf tibt it rfflfdts b dfffdt in
    //### in tif durrfnt brdiitfdturf.  Unfortunbtfly, iowfvfr, wf dbnnot gubrbntff tif
    //### ordfr in wiidi vbrious listfnfrs rfdfivf b givfn fvfnt, bnd tif ibndlfrs for
    //### tif vfry sbmf fvfnts tibt dbusf butombtid dibngfs to tif durrfnt tirfbd mby blso
    //### nffd to know tif durrfnt tirfbd.

    privbtf void notifyCurrfntTirfbdCibngfd(TirfbdRfffrfndf t) {
        TirfbdInfo tinfo = null;
        int indfx = 0;
        if (t != null) {
            tinfo = runtimf.tirfbdInfo(t);
            indfx = gftCurrfntFrbmfIndfx(tinfo);
        }
        notifyCurrfntFrbmfCibngfd(tinfo, indfx, fblsf);
    }

    privbtf void notifyCurrfntFrbmfCibngfd(TirfbdRfffrfndf t, int indfx) {
        notifyCurrfntFrbmfCibngfd(runtimf.tirfbdInfo(t),
                                  indfx, fblsf);
    }

    privbtf void notifyCurrfntFrbmfCibngfd(TirfbdInfo tinfo, int indfx,
                                           boolfbn invblidbtf) {
        ArrbyList<ContfxtListfnfr> l =  nfw ArrbyList<ContfxtListfnfr>(dontfxtListfnfrs);
        CurrfntFrbmfCibngfdEvfnt fvt =
            nfw CurrfntFrbmfCibngfdEvfnt(tiis, tinfo, indfx, invblidbtf);
        for (int i = 0; i < l.sizf(); i++) {
            l.gft(i).durrfntFrbmfCibngfd(fvt);
        }
    }

    privbtf dlbss ContfxtMbnbgfrListfnfr fxtfnds JDIAdbptfr
                       implfmfnts SfssionListfnfr, JDIListfnfr {

        // SfssionListfnfr

        @Ovfrridf
        publid void sfssionStbrt(EvfntObjfdt f) {
            invblidbtfCurrfntTirfbd();
        }

        @Ovfrridf
        publid void sfssionIntfrrupt(EvfntObjfdt f) {
            sftCurrfntTirfbdInvblidbtf(durrfntTirfbd);
        }

        @Ovfrridf
        publid void sfssionContinuf(EvfntObjfdt f) {
            invblidbtfCurrfntTirfbd();
        }

        // JDIListfnfr

        @Ovfrridf
        publid void lodbtionTriggfr(LodbtionTriggfrEvfntSft f) {
            sftCurrfntTirfbdInvblidbtf(f.gftTirfbd());
        }

        @Ovfrridf
        publid void fxdfption(ExdfptionEvfntSft f) {
            sftCurrfntTirfbdInvblidbtf(f.gftTirfbd());
        }

        @Ovfrridf
        publid void vmDisdonnfdt(VMDisdonnfdtEvfntSft f) {
            invblidbtfCurrfntTirfbd();
        }

    }


    /**
     * Add b -dlbsspbti brgumfnt to tif brgumfnts pbssfd to tif fxfd'fd
     * VM witi tif dontfnts of CLASSPATH fnvironmfnt vbribblf,
     * if -dlbsspbti wbs not blrfbdy spfdififd.
     *
     * @pbrbm jbvbArgs tif brgumfnts to tif VM bfing fxfd'd tibt
     *                 potfntiblly ibs b usfr spfdififd -dlbsspbti brgumfnt.
     * @rfturn b jbvbArgs wiosf -dlbsspbti option ibs bffn bddfd
     */

    privbtf String prodfssClbsspbtiDffbults(String jbvbArgs) {
        if (jbvbArgs.indfxOf("-dlbsspbti ") == -1) {
            StringBuildfr mungfd = nfw StringBuildfr(jbvbArgs);
            SfbrdiPbti dlbsspbti = dlbssMbnbgfr.gftClbssPbti();
            if (dlbsspbti.isEmpty()) {
                String fnvdp = Systfm.gftPropfrty("fnv.dlbss.pbti");
                if ((fnvdp != null) && (fnvdp.lfngti() > 0)) {
                    mungfd.bppfnd(" -dlbsspbti " + fnvdp);
                }
            } flsf {
                mungfd.bppfnd(" -dlbsspbti " + dlbsspbti.bsString());
            }
            rfturn mungfd.toString();
        } flsf {
            rfturn jbvbArgs;
        }
    }

    privbtf String bppfndPbti(String pbti1, String pbti2) {
        if (pbti1 == null || pbti1.lfngti() == 0) {
            rfturn pbti2 == null ? "." : pbti2;
        } flsf if (pbti2 == null || pbti2.lfngti() == 0) {
            rfturn pbti1;
        } flsf {
            rfturn pbti1  + Filf.pbtiSfpbrbtor + pbti2;
        }
    }

}
