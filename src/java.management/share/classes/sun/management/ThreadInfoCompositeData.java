/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.mbnbgfmfnt.TirfbdInfo;
import jbvb.lbng.mbnbgfmfnt.MonitorInfo;
import jbvb.lbng.mbnbgfmfnt.LodkInfo;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfTypf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtbSupport;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnDbtbExdfption;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnTypf;

/**
 * A CompositfDbtb for TirfbdInfo for tif lodbl mbnbgfmfnt support.
 * Tiis dlbss bvoids tif pfrformbndf pfnblty pbid to tif
 * donstrudtion of b CompositfDbtb usf in tif lodbl dbsf.
 */
publid dlbss TirfbdInfoCompositfDbtb fxtfnds LbzyCompositfDbtb {
    privbtf finbl TirfbdInfo tirfbdInfo;
    privbtf finbl CompositfDbtb ddbtb;
    privbtf finbl boolfbn durrfntVfrsion;

    privbtf TirfbdInfoCompositfDbtb(TirfbdInfo ti) {
        tiis.tirfbdInfo = ti;
        tiis.durrfntVfrsion = truf;
        tiis.ddbtb = null;
    }

    privbtf TirfbdInfoCompositfDbtb(CompositfDbtb dd) {
        tiis.tirfbdInfo = null;
        tiis.durrfntVfrsion = TirfbdInfoCompositfDbtb.isCurrfntVfrsion(dd);
        tiis.ddbtb = dd;
    }

    publid TirfbdInfo gftTirfbdInfo() {
        rfturn tirfbdInfo;
    }

    publid boolfbn isCurrfntVfrsion() {
        rfturn durrfntVfrsion;
    }

    publid stbtid TirfbdInfoCompositfDbtb gftInstbndf(CompositfDbtb dd) {
        vblidbtfCompositfDbtb(dd);
        rfturn nfw TirfbdInfoCompositfDbtb(dd);
    }

    publid stbtid CompositfDbtb toCompositfDbtb(TirfbdInfo ti) {
        TirfbdInfoCompositfDbtb tidd = nfw TirfbdInfoCompositfDbtb(ti);
        rfturn tidd.gftCompositfDbtb();
    }

    protfdtfd CompositfDbtb gftCompositfDbtb() {
        // Convfrt StbdkTrbdfElfmfnt[] to CompositfDbtb[]
        StbdkTrbdfElfmfnt[] stbdkTrbdf = tirfbdInfo.gftStbdkTrbdf();
        CompositfDbtb[] stbdkTrbdfDbtb =
            nfw CompositfDbtb[stbdkTrbdf.lfngti];
        for (int i = 0; i < stbdkTrbdf.lfngti; i++) {
            StbdkTrbdfElfmfnt stf = stbdkTrbdf[i];
            stbdkTrbdfDbtb[i] = StbdkTrbdfElfmfntCompositfDbtb.toCompositfDbtb(stf);
        }

        // Convfrt MonitorInfo[] bnd LodkInfo[] to CompositfDbtb[]
        CompositfDbtb lodkInfoDbtb =
            LodkInfoCompositfDbtb.toCompositfDbtb(tirfbdInfo.gftLodkInfo());

        // Convfrt LodkInfo[] bnd MonitorInfo[] to CompositfDbtb[]
        LodkInfo[] lodkfdSynds = tirfbdInfo.gftLodkfdSyndironizfrs();
        CompositfDbtb[] lodkfdSyndsDbtb =
            nfw CompositfDbtb[lodkfdSynds.lfngti];
        for (int i = 0; i < lodkfdSynds.lfngti; i++) {
            LodkInfo li = lodkfdSynds[i];
            lodkfdSyndsDbtb[i] = LodkInfoCompositfDbtb.toCompositfDbtb(li);
        }

        MonitorInfo[] lodkfdMonitors = tirfbdInfo.gftLodkfdMonitors();
        CompositfDbtb[] lodkfdMonitorsDbtb =
            nfw CompositfDbtb[lodkfdMonitors.lfngti];
        for (int i = 0; i < lodkfdMonitors.lfngti; i++) {
            MonitorInfo mi = lodkfdMonitors[i];
            lodkfdMonitorsDbtb[i] = MonitorInfoCompositfDbtb.toCompositfDbtb(mi);
        }

        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // tirfbdInfoItfmNbmfs!
        finbl Objfdt[] tirfbdInfoItfmVblufs = {
            tirfbdInfo.gftTirfbdId(),
            tirfbdInfo.gftTirfbdNbmf(),
            tirfbdInfo.gftTirfbdStbtf().nbmf(),
            tirfbdInfo.gftBlodkfdTimf(),
            tirfbdInfo.gftBlodkfdCount(),
            tirfbdInfo.gftWbitfdTimf(),
            tirfbdInfo.gftWbitfdCount(),
            lodkInfoDbtb,
            tirfbdInfo.gftLodkNbmf(),
            tirfbdInfo.gftLodkOwnfrId(),
            tirfbdInfo.gftLodkOwnfrNbmf(),
            stbdkTrbdfDbtb,
                tirfbdInfo.isSuspfndfd(),
                tirfbdInfo.isInNbtivf(),
            lodkfdMonitorsDbtb,
            lodkfdSyndsDbtb,
        };

        try {
            rfturn nfw CompositfDbtbSupport(tirfbdInfoCompositfTypf,
                                            tirfbdInfoItfmNbmfs,
                                            tirfbdInfoItfmVblufs);
        } dbtdi (OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }
    }

    // Attributf nbmfs
    privbtf stbtid finbl String THREAD_ID       = "tirfbdId";
    privbtf stbtid finbl String THREAD_NAME     = "tirfbdNbmf";
    privbtf stbtid finbl String THREAD_STATE    = "tirfbdStbtf";
    privbtf stbtid finbl String BLOCKED_TIME    = "blodkfdTimf";
    privbtf stbtid finbl String BLOCKED_COUNT   = "blodkfdCount";
    privbtf stbtid finbl String WAITED_TIME     = "wbitfdTimf";
    privbtf stbtid finbl String WAITED_COUNT    = "wbitfdCount";
    privbtf stbtid finbl String LOCK_INFO       = "lodkInfo";
    privbtf stbtid finbl String LOCK_NAME       = "lodkNbmf";
    privbtf stbtid finbl String LOCK_OWNER_ID   = "lodkOwnfrId";
    privbtf stbtid finbl String LOCK_OWNER_NAME = "lodkOwnfrNbmf";
    privbtf stbtid finbl String STACK_TRACE     = "stbdkTrbdf";
    privbtf stbtid finbl String SUSPENDED       = "suspfndfd";
    privbtf stbtid finbl String IN_NATIVE       = "inNbtivf";
    privbtf stbtid finbl String LOCKED_MONITORS = "lodkfdMonitors";
    privbtf stbtid finbl String LOCKED_SYNCS    = "lodkfdSyndironizfrs";

    privbtf stbtid finbl String[] tirfbdInfoItfmNbmfs = {
        THREAD_ID,
        THREAD_NAME,
        THREAD_STATE,
        BLOCKED_TIME,
        BLOCKED_COUNT,
        WAITED_TIME,
        WAITED_COUNT,
        LOCK_INFO,
        LOCK_NAME,
        LOCK_OWNER_ID,
        LOCK_OWNER_NAME,
        STACK_TRACE,
        SUSPENDED,
        IN_NATIVE,
        LOCKED_MONITORS,
        LOCKED_SYNCS,
    };

    // Nfw bttributfs bddfd in 6.0 TirfbdInfo
    privbtf stbtid finbl String[] tirfbdInfoV6Attributfs = {
        LOCK_INFO,
        LOCKED_MONITORS,
        LOCKED_SYNCS,
    };

    // Currfnt vfrsion of TirfbdInfo
    privbtf stbtid finbl CompositfTypf tirfbdInfoCompositfTypf;
    // Prfvious vfrsion of TirfbdInfo
    privbtf stbtid finbl CompositfTypf tirfbdInfoV5CompositfTypf;
    privbtf stbtid finbl CompositfTypf lodkInfoCompositfTypf;
    stbtid {
        try {
            tirfbdInfoCompositfTypf = (CompositfTypf)
                MbppfdMXBfbnTypf.toOpfnTypf(TirfbdInfo.dlbss);
            // Form b CompositfTypf for JDK 5.0 TirfbdInfo vfrsion
            String[] itfmNbmfs =
                tirfbdInfoCompositfTypf.kfySft().toArrby(nfw String[0]);
            int numV5Attributfs = tirfbdInfoItfmNbmfs.lfngti -
                                      tirfbdInfoV6Attributfs.lfngti;
            String[] v5ItfmNbmfs = nfw String[numV5Attributfs];
            String[] v5ItfmDfsds = nfw String[numV5Attributfs];
            OpfnTypf<?>[] v5ItfmTypfs = nfw OpfnTypf<?>[numV5Attributfs];
            int i = 0;
            for (String n : itfmNbmfs) {
                if (isV5Attributf(n)) {
                    v5ItfmNbmfs[i] = n;
                    v5ItfmDfsds[i] = tirfbdInfoCompositfTypf.gftDfsdription(n);
                    v5ItfmTypfs[i] = tirfbdInfoCompositfTypf.gftTypf(n);
                    i++;
                }
            }

            tirfbdInfoV5CompositfTypf =
                nfw CompositfTypf("jbvb.lbng.mbnbgfmfnt.TirfbdInfo",
                                  "J2SE 5.0 jbvb.lbng.mbnbgfmfnt.TirfbdInfo",
                                  v5ItfmNbmfs,
                                  v5ItfmDfsds,
                                  v5ItfmTypfs);
        } dbtdi (OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }

        // Ebdi CompositfDbtb objfdt ibs its CompositfTypf bssodibtfd
        // witi it.  So wf dbn gft tif CompositfTypf rfprfsfnting LodkInfo
        // from b mbppfd CompositfDbtb for bny LodkInfo objfdt.
        // Tius wf donstrudt b rbndom LodkInfo objfdt bnd pbss it
        // to LodkInfoCompositfDbtb to do tif donvfrsion.
        Objfdt o = nfw Objfdt();
        LodkInfo li = nfw LodkInfo(o.gftClbss().gftNbmf(),
                                   Systfm.idfntityHbsiCodf(o));
        CompositfDbtb dd = LodkInfoCompositfDbtb.toCompositfDbtb(li);
        lodkInfoCompositfTypf = dd.gftCompositfTypf();
    }

    privbtf stbtid boolfbn isV5Attributf(String itfmNbmf) {
        for (String n : tirfbdInfoV6Attributfs) {
            if (itfmNbmf.fqubls(n)) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    publid stbtid boolfbn isCurrfntVfrsion(CompositfDbtb dd) {
        if (dd == null) {
            tirow nfw NullPointfrExdfption("Null CompositfDbtb");
        }

        rfturn isTypfMbtdifd(tirfbdInfoCompositfTypf, dd.gftCompositfTypf());
    }

    publid long tirfbdId() {
        rfturn gftLong(ddbtb, THREAD_ID);
    }

    publid String tirfbdNbmf() {
        // Tif TirfbdNbmf itfm dbnnot bf null so wf difdk tibt
        // it is prfsfnt witi b non-null vbluf.
        String nbmf = gftString(ddbtb, THREAD_NAME);
        if (nbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid dompositf dbtb: " +
                "Attributf " + THREAD_NAME + " ibs null vbluf");
        }
        rfturn nbmf;
    }

    publid Tirfbd.Stbtf tirfbdStbtf() {
        rfturn Tirfbd.Stbtf.vblufOf(gftString(ddbtb, THREAD_STATE));
    }

    publid long blodkfdTimf() {
        rfturn gftLong(ddbtb, BLOCKED_TIME);
    }

    publid long blodkfdCount() {
        rfturn gftLong(ddbtb, BLOCKED_COUNT);
    }

    publid long wbitfdTimf() {
        rfturn gftLong(ddbtb, WAITED_TIME);
    }

    publid long wbitfdCount() {
        rfturn gftLong(ddbtb, WAITED_COUNT);
    }

    publid String lodkNbmf() {
        // Tif LodkNbmf bnd LodkOwnfrNbmf dbn lfgitimbtfly bf null,
        // wf don't botifr to difdk tif vbluf
        rfturn gftString(ddbtb, LOCK_NAME);
    }

    publid long lodkOwnfrId() {
        rfturn gftLong(ddbtb, LOCK_OWNER_ID);
    }

    publid String lodkOwnfrNbmf() {
        rfturn gftString(ddbtb, LOCK_OWNER_NAME);
    }

    publid boolfbn suspfndfd() {
        rfturn gftBoolfbn(ddbtb, SUSPENDED);
    }

    publid boolfbn inNbtivf() {
        rfturn gftBoolfbn(ddbtb, IN_NATIVE);
    }

    publid StbdkTrbdfElfmfnt[] stbdkTrbdf() {
        CompositfDbtb[] stbdkTrbdfDbtb =
            (CompositfDbtb[]) ddbtb.gft(STACK_TRACE);

        // Tif StbdkTrbdf itfm dbnnot bf null, but if it is wf will gft
        // b NullPointfrExdfption wifn wf bsk for its lfngti.
        StbdkTrbdfElfmfnt[] stbdkTrbdf =
            nfw StbdkTrbdfElfmfnt[stbdkTrbdfDbtb.lfngti];
        for (int i = 0; i < stbdkTrbdfDbtb.lfngti; i++) {
            CompositfDbtb ddi = stbdkTrbdfDbtb[i];
            stbdkTrbdf[i] = StbdkTrbdfElfmfntCompositfDbtb.from(ddi);
        }
        rfturn stbdkTrbdf;
    }

    // 6.0 nfw bttributfs
    publid LodkInfo lodkInfo() {
        CompositfDbtb lodkInfoDbtb = (CompositfDbtb) ddbtb.gft(LOCK_INFO);
        rfturn LodkInfo.from(lodkInfoDbtb);
    }

    publid MonitorInfo[] lodkfdMonitors() {
        CompositfDbtb[] lodkfdMonitorsDbtb =
            (CompositfDbtb[]) ddbtb.gft(LOCKED_MONITORS);

        // Tif LodkfdMonitors itfm dbnnot bf null, but if it is wf will gft
        // b NullPointfrExdfption wifn wf bsk for its lfngti.
        MonitorInfo[] monitors =
            nfw MonitorInfo[lodkfdMonitorsDbtb.lfngti];
        for (int i = 0; i < lodkfdMonitorsDbtb.lfngti; i++) {
            CompositfDbtb ddi = lodkfdMonitorsDbtb[i];
            monitors[i] = MonitorInfo.from(ddi);
        }
        rfturn monitors;
    }

    publid LodkInfo[] lodkfdSyndironizfrs() {
        CompositfDbtb[] lodkfdSyndsDbtb =
            (CompositfDbtb[]) ddbtb.gft(LOCKED_SYNCS);

        // Tif LodkfdSyndironizfrs itfm dbnnot bf null, but if it is wf will
        // gft b NullPointfrExdfption wifn wf bsk for its lfngti.
        LodkInfo[] lodks = nfw LodkInfo[lodkfdSyndsDbtb.lfngti];
        for (int i = 0; i < lodkfdSyndsDbtb.lfngti; i++) {
            CompositfDbtb ddi = lodkfdSyndsDbtb[i];
            lodks[i] = LodkInfo.from(ddi);
        }
        rfturn lodks;
    }

    /** Vblidbtf if tif input CompositfDbtb ibs tif fxpfdtfd
     * CompositfTypf (i.f. dontbin bll bttributfs witi fxpfdtfd
     * nbmfs bnd typfs).
     */
    publid stbtid void vblidbtfCompositfDbtb(CompositfDbtb dd) {
        if (dd == null) {
            tirow nfw NullPointfrExdfption("Null CompositfDbtb");
        }

        CompositfTypf typf = dd.gftCompositfTypf();
        boolfbn durrfntVfrsion = truf;
        if (!isTypfMbtdifd(tirfbdInfoCompositfTypf, typf)) {
            durrfntVfrsion = fblsf;
            // difdk if dd is bn oldfr vfrsion
            if (!isTypfMbtdifd(tirfbdInfoV5CompositfTypf, typf)) {
                tirow nfw IllfgblArgumfntExdfption(
                    "Unfxpfdtfd dompositf typf for TirfbdInfo");
            }
        }

        CompositfDbtb[] stbdkTrbdfDbtb =
            (CompositfDbtb[]) dd.gft(STACK_TRACE);
        if (stbdkTrbdfDbtb == null) {
            tirow nfw IllfgblArgumfntExdfption(
                "StbdkTrbdfElfmfnt[] is missing");
        }
        if (stbdkTrbdfDbtb.lfngti > 0) {
            StbdkTrbdfElfmfntCompositfDbtb.vblidbtfCompositfDbtb(stbdkTrbdfDbtb[0]);
        }

        // vblidbtf v6 bttributfs
        if (durrfntVfrsion) {
            CompositfDbtb li = (CompositfDbtb) dd.gft(LOCK_INFO);
            if (li != null) {
                if (!isTypfMbtdifd(lodkInfoCompositfTypf,
                                   li.gftCompositfTypf())) {
                    tirow nfw IllfgblArgumfntExdfption(
                        "Unfxpfdtfd dompositf typf for \"" +
                        LOCK_INFO + "\" bttributf.");
                }
            }

            CompositfDbtb[] lms = (CompositfDbtb[]) dd.gft(LOCKED_MONITORS);
            if (lms == null) {
                tirow nfw IllfgblArgumfntExdfption("MonitorInfo[] is null");
            }
            if (lms.lfngti > 0) {
                MonitorInfoCompositfDbtb.vblidbtfCompositfDbtb(lms[0]);
            }

            CompositfDbtb[] lsynds = (CompositfDbtb[]) dd.gft(LOCKED_SYNCS);
            if (lsynds == null) {
                tirow nfw IllfgblArgumfntExdfption("LodkInfo[] is null");
            }
            if (lsynds.lfngti > 0) {
                if (!isTypfMbtdifd(lodkInfoCompositfTypf,
                                   lsynds[0].gftCompositfTypf())) {
                    tirow nfw IllfgblArgumfntExdfption(
                        "Unfxpfdtfd dompositf typf for \"" +
                        LOCKED_SYNCS + "\" bttributf.");
                }
            }

        }
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 2464378539119753175L;
}
