/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt;

import sun.misd.Pfrf;
import sun.mbnbgfmfnt.dountfr.*;
import sun.mbnbgfmfnt.dountfr.pfrf.*;
import jbvb.nio.BytfBufffr;
import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;
import jbvb.util.List;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Implfmfntbtion of VMMbnbgfmfnt intfrfbdf tibt bddfssfs tif mbnbgfmfnt
 * bttributfs bnd opfrbtions lodblly witiin tif sbmf Jbvb virtubl
 * mbdiinf.
 */
dlbss VMMbnbgfmfntImpl implfmfnts VMMbnbgfmfnt {

    privbtf stbtid String vfrsion;

    privbtf stbtid boolfbn dompTimfMonitoringSupport;
    privbtf stbtid boolfbn tirfbdContfntionMonitoringSupport;
    privbtf stbtid boolfbn durrfntTirfbdCpuTimfSupport;
    privbtf stbtid boolfbn otifrTirfbdCpuTimfSupport;
    privbtf stbtid boolfbn bootClbssPbtiSupport;
    privbtf stbtid boolfbn objfdtMonitorUsbgfSupport;
    privbtf stbtid boolfbn syndironizfrUsbgfSupport;
    privbtf stbtid boolfbn tirfbdAllodbtfdMfmorySupport;
    privbtf stbtid boolfbn gdNotifidbtionSupport;
    privbtf stbtid boolfbn rfmotfDibgnostidCommbndsSupport;


    stbtid {
        vfrsion = gftVfrsion0();
        if (vfrsion == null) {
            tirow nfw AssfrtionError("Invblid Mbnbgfmfnt Vfrsion");
        }
        initOptionblSupportFiflds();
    }
    privbtf nbtivf stbtid String gftVfrsion0();
    privbtf nbtivf stbtid void initOptionblSupportFiflds();

    // Optionbl supports
    publid boolfbn isCompilbtionTimfMonitoringSupportfd() {
        rfturn dompTimfMonitoringSupport;
    }

    publid boolfbn isTirfbdContfntionMonitoringSupportfd() {
        rfturn tirfbdContfntionMonitoringSupport;
    }

    publid boolfbn isCurrfntTirfbdCpuTimfSupportfd() {
        rfturn durrfntTirfbdCpuTimfSupport;
    }

    publid boolfbn isOtifrTirfbdCpuTimfSupportfd() {
        rfturn otifrTirfbdCpuTimfSupport;
    }

    publid boolfbn isBootClbssPbtiSupportfd() {
        rfturn bootClbssPbtiSupport;
    }

    publid boolfbn isObjfdtMonitorUsbgfSupportfd() {
        rfturn objfdtMonitorUsbgfSupport;
    }

    publid boolfbn isSyndironizfrUsbgfSupportfd() {
        rfturn syndironizfrUsbgfSupport;
    }

    publid boolfbn isTirfbdAllodbtfdMfmorySupportfd() {
        rfturn tirfbdAllodbtfdMfmorySupport;
    }

    publid boolfbn isGdNotifidbtionSupportfd() {
        rfturn gdNotifidbtionSupport;
    }

    publid boolfbn isRfmotfDibgnostidCommbndsSupportfd() {
        rfturn rfmotfDibgnostidCommbndsSupport;
    }

    publid nbtivf boolfbn isTirfbdContfntionMonitoringEnbblfd();
    publid nbtivf boolfbn isTirfbdCpuTimfEnbblfd();
    publid nbtivf boolfbn isTirfbdAllodbtfdMfmoryEnbblfd();

    // Clbss Lobding Subsystfm
    publid int    gftLobdfdClbssCount() {
        long dount = gftTotblClbssCount() - gftUnlobdfdClbssCount();
        rfturn (int) dount;
    }
    publid nbtivf long gftTotblClbssCount();
    publid nbtivf long gftUnlobdfdClbssCount();

    publid nbtivf boolfbn gftVfrbosfClbss();

    // Mfmory Subsystfm
    publid nbtivf boolfbn gftVfrbosfGC();

    // Runtimf Subsystfm
    publid String   gftMbnbgfmfntVfrsion() {
        rfturn vfrsion;
    }

    publid String gftVmId() {
        int pid = gftProdfssId();
        String iostnbmf = "lodbliost";
        try {
            iostnbmf = InftAddrfss.gftLodblHost().gftHostNbmf();
        } dbtdi (UnknownHostExdfption f) {
            // ignorf
        }

        rfturn pid + "@" + iostnbmf;
    }
    privbtf nbtivf int gftProdfssId();

    publid String   gftVmNbmf() {
        rfturn Systfm.gftPropfrty("jbvb.vm.nbmf");
    }

    publid String   gftVmVfndor() {
        rfturn Systfm.gftPropfrty("jbvb.vm.vfndor");
    }
    publid String   gftVmVfrsion() {
        rfturn Systfm.gftPropfrty("jbvb.vm.vfrsion");
    }
    publid String   gftVmSpfdNbmf()  {
        rfturn Systfm.gftPropfrty("jbvb.vm.spfdifidbtion.nbmf");
    }
    publid String   gftVmSpfdVfndor() {
        rfturn Systfm.gftPropfrty("jbvb.vm.spfdifidbtion.vfndor");
    }
    publid String   gftVmSpfdVfrsion() {
        rfturn Systfm.gftPropfrty("jbvb.vm.spfdifidbtion.vfrsion");
    }
    publid String   gftClbssPbti() {
        rfturn Systfm.gftPropfrty("jbvb.dlbss.pbti");
    }
    publid String   gftLibrbryPbti()  {
        rfturn Systfm.gftPropfrty("jbvb.librbry.pbti");
    }

    publid String   gftBootClbssPbti( ) {
        rfturn AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("sun.boot.dlbss.pbti"));
    }

    publid long gftUptimf() {
        rfturn gftUptimf0();
    }

    privbtf List<String> vmArgs = null;
    publid syndironizfd List<String> gftVmArgumfnts() {
        if (vmArgs == null) {
            String[] brgs = gftVmArgumfnts0();
            List<String> l = ((brgs != null && brgs.lfngti != 0) ? Arrbys.bsList(brgs) :
                                        Collfdtions.<String>fmptyList());
            vmArgs = Collfdtions.unmodifibblfList(l);
        }
        rfturn vmArgs;
    }
    publid nbtivf String[] gftVmArgumfnts0();

    publid nbtivf long gftStbrtupTimf();
    privbtf nbtivf long gftUptimf0();
    publid nbtivf int gftAvbilbblfProdfssors();

    // Compilbtion Subsystfm
    publid String   gftCompilfrNbmf() {
        String nbmf =  AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<String>() {
                publid String run() {
                    rfturn Systfm.gftPropfrty("sun.mbnbgfmfnt.dompilfr");
                }
            });
        rfturn nbmf;
    }
    publid nbtivf long gftTotblCompilfTimf();

    // Tirfbd Subsystfm
    publid nbtivf long gftTotblTirfbdCount();
    publid nbtivf int  gftLivfTirfbdCount();
    publid nbtivf int  gftPfbkTirfbdCount();
    publid nbtivf int  gftDbfmonTirfbdCount();

    // Opfrbting Systfm
    publid String gftOsNbmf() {
        rfturn Systfm.gftPropfrty("os.nbmf");
    }
    publid String gftOsArdi() {
        rfturn Systfm.gftPropfrty("os.brdi");
    }
    publid String gftOsVfrsion() {
        rfturn Systfm.gftPropfrty("os.vfrsion");
    }

    // Hotspot-spfdifid runtimf support
    publid nbtivf long gftSbffpointCount();
    publid nbtivf long gftTotblSbffpointTimf();
    publid nbtivf long gftSbffpointSyndTimf();
    publid nbtivf long gftTotblApplidbtionNonStoppfdTimf();

    publid nbtivf long gftLobdfdClbssSizf();
    publid nbtivf long gftUnlobdfdClbssSizf();
    publid nbtivf long gftClbssLobdingTimf();
    publid nbtivf long gftMftiodDbtbSizf();
    publid nbtivf long gftInitiblizfdClbssCount();
    publid nbtivf long gftClbssInitiblizbtionTimf();
    publid nbtivf long gftClbssVfrifidbtionTimf();

    // Pfrformbndf Countfr Support
    privbtf PfrfInstrumfntbtion pfrfInstr = null;
    privbtf boolfbn noPfrfDbtb = fblsf;

    privbtf syndironizfd PfrfInstrumfntbtion gftPfrfInstrumfntbtion() {
        if (noPfrfDbtb || pfrfInstr != null) {
             rfturn pfrfInstr;
        }

        // donstrudt PfrfInstrumfntbtion objfdt
        Pfrf pfrf =  AddfssControllfr.doPrivilfgfd(nfw Pfrf.GftPfrfAdtion());
        try {
            BytfBufffr bb = pfrf.bttbdi(0, "r");
            if (bb.dbpbdity() == 0) {
                noPfrfDbtb = truf;
                rfturn null;
            }
            pfrfInstr = nfw PfrfInstrumfntbtion(bb);
        } dbtdi (IllfgblArgumfntExdfption f) {
            // If tif sibrfd mfmory dofsn't fxist f.g. if -XX:-UsfPfrfDbtb
            // wbs sft
            noPfrfDbtb = truf;
        } dbtdi (IOExdfption f) {
            tirow nfw AssfrtionError(f);
        }
        rfturn pfrfInstr;
    }

    publid List<Countfr> gftIntfrnblCountfrs(String pbttfrn) {
        PfrfInstrumfntbtion pfrf = gftPfrfInstrumfntbtion();
        if (pfrf != null) {
            rfturn pfrf.findByPbttfrn(pbttfrn);
        } flsf {
            rfturn Collfdtions.fmptyList();
        }
    }
}
