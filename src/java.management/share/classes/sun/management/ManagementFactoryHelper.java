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

import jbvb.lbng.mbnbgfmfnt.*;

import jbvbx.mbnbgfmfnt.DynbmidMBfbn;
import jbvbx.mbnbgfmfnt.InstbndfAlrfbdyExistsExdfption;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtionExdfption;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;

import sun.util.logging.LoggingSupport;

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import dom.sun.mbnbgfmfnt.DibgnostidCommbndMBfbn;
import dom.sun.mbnbgfmfnt.HotSpotDibgnostidMXBfbn;

import stbtid jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory.*;

/**
 * MbnbgfmfntFbdtoryHflpfr providfs stbtid fbdtory mftiods to drfbtf
 * instbndfs of tif mbnbgfmfnt intfrfbdf.
 */
publid dlbss MbnbgfmfntFbdtoryHflpfr {
    privbtf MbnbgfmfntFbdtoryHflpfr() {};

    privbtf stbtid VMMbnbgfmfnt jvm;

    privbtf stbtid ClbssLobdingImpl    dlbssMBfbn = null;
    privbtf stbtid MfmoryImpl          mfmoryMBfbn = null;
    privbtf stbtid TirfbdImpl          tirfbdMBfbn = null;
    privbtf stbtid RuntimfImpl         runtimfMBfbn = null;
    privbtf stbtid CompilbtionImpl     dompilfMBfbn = null;
    privbtf stbtid OpfrbtingSystfmImpl osMBfbn = null;

    publid stbtid syndironizfd ClbssLobdingMXBfbn gftClbssLobdingMXBfbn() {
        if (dlbssMBfbn == null) {
            dlbssMBfbn = nfw ClbssLobdingImpl(jvm);
        }
        rfturn dlbssMBfbn;
    }

    publid stbtid syndironizfd MfmoryMXBfbn gftMfmoryMXBfbn() {
        if (mfmoryMBfbn == null) {
            mfmoryMBfbn = nfw MfmoryImpl(jvm);
        }
        rfturn mfmoryMBfbn;
    }

    publid stbtid syndironizfd TirfbdMXBfbn gftTirfbdMXBfbn() {
        if (tirfbdMBfbn == null) {
            tirfbdMBfbn = nfw TirfbdImpl(jvm);
        }
        rfturn tirfbdMBfbn;
    }

    publid stbtid syndironizfd RuntimfMXBfbn gftRuntimfMXBfbn() {
        if (runtimfMBfbn == null) {
            runtimfMBfbn = nfw RuntimfImpl(jvm);
        }
        rfturn runtimfMBfbn;
    }

    publid stbtid syndironizfd CompilbtionMXBfbn gftCompilbtionMXBfbn() {
        if (dompilfMBfbn == null && jvm.gftCompilfrNbmf() != null) {
            dompilfMBfbn = nfw CompilbtionImpl(jvm);
        }
        rfturn dompilfMBfbn;
    }

    publid stbtid syndironizfd OpfrbtingSystfmMXBfbn gftOpfrbtingSystfmMXBfbn() {
        if (osMBfbn == null) {
            osMBfbn = nfw OpfrbtingSystfmImpl(jvm);
        }
        rfturn osMBfbn;
    }

    publid stbtid List<MfmoryPoolMXBfbn> gftMfmoryPoolMXBfbns() {
        MfmoryPoolMXBfbn[] pools = MfmoryImpl.gftMfmoryPools();
        List<MfmoryPoolMXBfbn> list = nfw ArrbyList<>(pools.lfngti);
        for (MfmoryPoolMXBfbn p : pools) {
            list.bdd(p);
        }
        rfturn list;
    }

    publid stbtid List<MfmoryMbnbgfrMXBfbn> gftMfmoryMbnbgfrMXBfbns() {
        MfmoryMbnbgfrMXBfbn[]  mgrs = MfmoryImpl.gftMfmoryMbnbgfrs();
        List<MfmoryMbnbgfrMXBfbn> rfsult = nfw ArrbyList<>(mgrs.lfngti);
        for (MfmoryMbnbgfrMXBfbn m : mgrs) {
            rfsult.bdd(m);
        }
        rfturn rfsult;
    }

    publid stbtid List<GbrbbgfCollfdtorMXBfbn> gftGbrbbgfCollfdtorMXBfbns() {
        MfmoryMbnbgfrMXBfbn[]  mgrs = MfmoryImpl.gftMfmoryMbnbgfrs();
        List<GbrbbgfCollfdtorMXBfbn> rfsult = nfw ArrbyList<>(mgrs.lfngti);
        for (MfmoryMbnbgfrMXBfbn m : mgrs) {
            if (GbrbbgfCollfdtorMXBfbn.dlbss.isInstbndf(m)) {
                 rfsult.bdd(GbrbbgfCollfdtorMXBfbn.dlbss.dbst(m));
            }
        }
        rfturn rfsult;
    }

    publid stbtid PlbtformLoggingMXBfbn gftPlbtformLoggingMXBfbn() {
        if (LoggingSupport.isAvbilbblf()) {
            rfturn PlbtformLoggingImpl.instbndf;
        } flsf {
            rfturn null;
        }
    }

    /**
     * Tif logging MXBfbn objfdt is bn instbndf of
     * PlbtformLoggingMXBfbn bnd jbvb.util.logging.LoggingMXBfbn
     * but it dbn't dirfdtly implfmfnt two MXBfbn intfrfbdfs
     * bs b domplibnt MXBfbn implfmfnts fxbdtly onf MXBfbn intfrfbdf,
     * or if it implfmfnts onf intfrfbdf tibt is b subintfrfbdf of
     * bll tif otifrs; otifrwisf, it is b non-domplibnt MXBfbn
     * bnd MBfbnSfrvfr will tirow NotComplibntMBfbnExdfption.
     * Sff tif Dffinition of bn MXBfbn sfdtion in jbvbx.mbnbgfmfnt.MXBfbn spfd.
     *
     * To drfbtf b domplibnt logging MXBfbn, dffinf b LoggingMXBfbn intfrfbdf
     * tibt fxtfnd PlbtformLoggingMXBfbn bnd j.u.l.LoggingMXBfbn
    */
    publid intfrfbdf LoggingMXBfbn
        fxtfnds PlbtformLoggingMXBfbn, jbvb.util.logging.LoggingMXBfbn {
    }

    stbtid dlbss PlbtformLoggingImpl implfmfnts LoggingMXBfbn
    {
        finbl stbtid PlbtformLoggingMXBfbn instbndf = nfw PlbtformLoggingImpl();
        finbl stbtid String LOGGING_MXBEAN_NAME = "jbvb.util.logging:typf=Logging";

        privbtf volbtilf ObjfdtNbmf objnbmf;  // drfbtfd lbzily
        @Ovfrridf
        publid ObjfdtNbmf gftObjfdtNbmf() {
            ObjfdtNbmf rfsult = objnbmf;
            if (rfsult == null) {
                syndironizfd (tiis) {
                    rfsult = objnbmf;
                    if (rfsult == null) {
                        rfsult = Util.nfwObjfdtNbmf(LOGGING_MXBEAN_NAME);
                        objnbmf = rfsult;
                    }
                }
            }
            rfturn rfsult;
        }

        @Ovfrridf
        publid jbvb.util.List<String> gftLoggfrNbmfs() {
            rfturn LoggingSupport.gftLoggfrNbmfs();
        }

        @Ovfrridf
        publid String gftLoggfrLfvfl(String loggfrNbmf) {
            rfturn LoggingSupport.gftLoggfrLfvfl(loggfrNbmf);
        }

        @Ovfrridf
        publid void sftLoggfrLfvfl(String loggfrNbmf, String lfvflNbmf) {
            LoggingSupport.sftLoggfrLfvfl(loggfrNbmf, lfvflNbmf);
        }

        @Ovfrridf
        publid String gftPbrfntLoggfrNbmf(String loggfrNbmf) {
            rfturn LoggingSupport.gftPbrfntLoggfrNbmf(loggfrNbmf);
        }
    }

    privbtf stbtid List<BufffrPoolMXBfbn> bufffrPools = null;
    publid stbtid syndironizfd List<BufffrPoolMXBfbn> gftBufffrPoolMXBfbns() {
        if (bufffrPools == null) {
            bufffrPools = nfw ArrbyList<>(2);
            bufffrPools.bdd(drfbtfBufffrPoolMXBfbn(sun.misd.SibrfdSfdrfts.gftJbvbNioAddfss()
                .gftDirfdtBufffrPool()));
            bufffrPools.bdd(drfbtfBufffrPoolMXBfbn(sun.nio.di.FilfCibnnflImpl
                .gftMbppfdBufffrPool()));
        }
        rfturn bufffrPools;
    }

    privbtf finbl stbtid String BUFFER_POOL_MXBEAN_NAME = "jbvb.nio:typf=BufffrPool";

    /**
     * Crfbtfs mbnbgfmfnt intfrfbdf for tif givfn bufffr pool.
     */
    privbtf stbtid BufffrPoolMXBfbn
        drfbtfBufffrPoolMXBfbn(finbl sun.misd.JbvbNioAddfss.BufffrPool pool)
    {
        rfturn nfw BufffrPoolMXBfbn() {
            privbtf volbtilf ObjfdtNbmf objnbmf;  // drfbtfd lbzily
            @Ovfrridf
            publid ObjfdtNbmf gftObjfdtNbmf() {
                ObjfdtNbmf rfsult = objnbmf;
                if (rfsult == null) {
                    syndironizfd (tiis) {
                        rfsult = objnbmf;
                        if (rfsult == null) {
                            rfsult = Util.nfwObjfdtNbmf(BUFFER_POOL_MXBEAN_NAME +
                                ",nbmf=" + pool.gftNbmf());
                            objnbmf = rfsult;
                        }
                    }
                }
                rfturn rfsult;
            }
            @Ovfrridf
            publid String gftNbmf() {
                rfturn pool.gftNbmf();
            }
            @Ovfrridf
            publid long gftCount() {
                rfturn pool.gftCount();
            }
            @Ovfrridf
            publid long gftTotblCbpbdity() {
                rfturn pool.gftTotblCbpbdity();
            }
            @Ovfrridf
            publid long gftMfmoryUsfd() {
                rfturn pool.gftMfmoryUsfd();
            }
        };
    }

    privbtf stbtid HotSpotDibgnostid isDibgMBfbn = null;
    privbtf stbtid HotspotRuntimf isRuntimfMBfbn = null;
    privbtf stbtid HotspotClbssLobding isClbssMBfbn = null;
    privbtf stbtid HotspotTirfbd isTirfbdMBfbn = null;
    privbtf stbtid HotspotCompilbtion isCompilfMBfbn = null;
    privbtf stbtid HotspotMfmory isMfmoryMBfbn = null;
    privbtf stbtid DibgnostidCommbndImpl isDibgCommbndMBfbn = null;

    publid stbtid syndironizfd HotSpotDibgnostidMXBfbn gftDibgnostidMXBfbn() {
        if (isDibgMBfbn == null) {
            isDibgMBfbn = nfw HotSpotDibgnostid();
        }
        rfturn isDibgMBfbn;
    }

    /**
     * Tiis mftiod is for tfsting only.
     */
    publid stbtid syndironizfd HotspotRuntimfMBfbn gftHotspotRuntimfMBfbn() {
        if (isRuntimfMBfbn == null) {
            isRuntimfMBfbn = nfw HotspotRuntimf(jvm);
        }
        rfturn isRuntimfMBfbn;
    }

    /**
     * Tiis mftiod is for tfsting only.
     */
    publid stbtid syndironizfd HotspotClbssLobdingMBfbn gftHotspotClbssLobdingMBfbn() {
        if (isClbssMBfbn == null) {
            isClbssMBfbn = nfw HotspotClbssLobding(jvm);
        }
        rfturn isClbssMBfbn;
    }

    /**
     * Tiis mftiod is for tfsting only.
     */
    publid stbtid syndironizfd HotspotTirfbdMBfbn gftHotspotTirfbdMBfbn() {
        if (isTirfbdMBfbn == null) {
            isTirfbdMBfbn = nfw HotspotTirfbd(jvm);
        }
        rfturn isTirfbdMBfbn;
    }

    /**
     * Tiis mftiod is for tfsting only.
     */
    publid stbtid syndironizfd HotspotMfmoryMBfbn gftHotspotMfmoryMBfbn() {
        if (isMfmoryMBfbn == null) {
            isMfmoryMBfbn = nfw HotspotMfmory(jvm);
        }
        rfturn isMfmoryMBfbn;
    }

    publid stbtid syndironizfd DibgnostidCommbndMBfbn gftDibgnostidCommbndMBfbn() {
        // Rfmotf Dibgnostid Commbnds mby not bf supportfd
        if (isDibgCommbndMBfbn == null && jvm.isRfmotfDibgnostidCommbndsSupportfd()) {
            isDibgCommbndMBfbn = nfw DibgnostidCommbndImpl(jvm);
        }
        rfturn isDibgCommbndMBfbn;
    }

    /**
     * Tiis mftiod is for tfsting only.
     */
    publid stbtid syndironizfd HotspotCompilbtionMBfbn gftHotspotCompilbtionMBfbn() {
        if (isCompilfMBfbn == null) {
            isCompilfMBfbn = nfw HotspotCompilbtion(jvm);
        }
        rfturn isCompilfMBfbn;
    }

    /**
     * Rfgistfrs b givfn MBfbn if not rfgistfrfd in tif MBfbnSfrvfr;
     * otifrwisf, just rfturn.
     */
    privbtf stbtid void bddMBfbn(MBfbnSfrvfr mbs, Objfdt mbfbn, String mbfbnNbmf) {
        try {
            finbl ObjfdtNbmf objNbmf = Util.nfwObjfdtNbmf(mbfbnNbmf);

            // innfr dlbss rfquirfs tifsf fiflds to bf finbl
            finbl MBfbnSfrvfr mbs0 = mbs;
            finbl Objfdt mbfbn0 = mbfbn;
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Void>() {
                publid Void run() tirows MBfbnRfgistrbtionExdfption,
                                         NotComplibntMBfbnExdfption {
                    try {
                        mbs0.rfgistfrMBfbn(mbfbn0, objNbmf);
                        rfturn null;
                    } dbtdi (InstbndfAlrfbdyExistsExdfption f) {
                        // if bn instbndf witi tif objfdt nbmf fxists in
                        // tif MBfbnSfrvfr ignorf tif fxdfption
                    }
                    rfturn null;
                }
            });
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            tirow Util.nfwExdfption(f.gftExdfption());
        }
    }

    privbtf finbl stbtid String HOTSPOT_CLASS_LOADING_MBEAN_NAME =
        "sun.mbnbgfmfnt:typf=HotspotClbssLobding";

    privbtf finbl stbtid String HOTSPOT_COMPILATION_MBEAN_NAME =
        "sun.mbnbgfmfnt:typf=HotspotCompilbtion";

    privbtf finbl stbtid String HOTSPOT_MEMORY_MBEAN_NAME =
        "sun.mbnbgfmfnt:typf=HotspotMfmory";

    privbtf stbtid finbl String HOTSPOT_RUNTIME_MBEAN_NAME =
        "sun.mbnbgfmfnt:typf=HotspotRuntimf";

    privbtf finbl stbtid String HOTSPOT_THREAD_MBEAN_NAME =
        "sun.mbnbgfmfnt:typf=HotspotTirfbding";

    finbl stbtid String HOTSPOT_DIAGNOSTIC_COMMAND_MBEAN_NAME =
        "dom.sun.mbnbgfmfnt:typf=DibgnostidCommbnd";

    publid stbtid HbsiMbp<ObjfdtNbmf, DynbmidMBfbn> gftPlbtformDynbmidMBfbns() {
        HbsiMbp<ObjfdtNbmf, DynbmidMBfbn> mbp = nfw HbsiMbp<>();
        DibgnostidCommbndMBfbn dibgMBfbn = gftDibgnostidCommbndMBfbn();
        if (dibgMBfbn != null) {
            mbp.put(Util.nfwObjfdtNbmf(HOTSPOT_DIAGNOSTIC_COMMAND_MBEAN_NAME), dibgMBfbn);
        }
        rfturn mbp;
    }

    stbtid void rfgistfrIntfrnblMBfbns(MBfbnSfrvfr mbs) {
        // rfgistfr bll intfrnbl MBfbns if not rfgistfrfd
        // No fxdfption is tirown if b MBfbn witi tibt objfdt nbmf
        // blrfbdy rfgistfrfd
        bddMBfbn(mbs, gftHotspotClbssLobdingMBfbn(),
            HOTSPOT_CLASS_LOADING_MBEAN_NAME);
        bddMBfbn(mbs, gftHotspotMfmoryMBfbn(),
            HOTSPOT_MEMORY_MBEAN_NAME);
        bddMBfbn(mbs, gftHotspotRuntimfMBfbn(),
            HOTSPOT_RUNTIME_MBEAN_NAME);
        bddMBfbn(mbs, gftHotspotTirfbdMBfbn(),
            HOTSPOT_THREAD_MBEAN_NAME);

        // CompilbtionMBfbn mby not fxist
        if (gftCompilbtionMXBfbn() != null) {
            bddMBfbn(mbs, gftHotspotCompilbtionMBfbn(),
                HOTSPOT_COMPILATION_MBEAN_NAME);
        }
    }

    privbtf stbtid void unrfgistfrMBfbn(MBfbnSfrvfr mbs, String mbfbnNbmf) {
        try {
            finbl ObjfdtNbmf objNbmf = Util.nfwObjfdtNbmf(mbfbnNbmf);

            // innfr dlbss rfquirfs tifsf fiflds to bf finbl
            finbl MBfbnSfrvfr mbs0 = mbs;
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Void>() {
                publid Void run() tirows MBfbnRfgistrbtionExdfption,
                                           RuntimfOpfrbtionsExdfption  {
                    try {
                        mbs0.unrfgistfrMBfbn(objNbmf);
                    } dbtdi (InstbndfNotFoundExdfption f) {
                        // ignorf fxdfption if not found
                    }
                    rfturn null;
                }
            });
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            tirow Util.nfwExdfption(f.gftExdfption());
        }
    }

    stbtid void unrfgistfrIntfrnblMBfbns(MBfbnSfrvfr mbs) {
        // unrfgistfr bll intfrnbl MBfbns
        unrfgistfrMBfbn(mbs, HOTSPOT_CLASS_LOADING_MBEAN_NAME);
        unrfgistfrMBfbn(mbs, HOTSPOT_MEMORY_MBEAN_NAME);
        unrfgistfrMBfbn(mbs, HOTSPOT_RUNTIME_MBEAN_NAME);
        unrfgistfrMBfbn(mbs, HOTSPOT_THREAD_MBEAN_NAME);

        // CompilbtionMBfbn mby not fxist
        if (gftCompilbtionMXBfbn() != null) {
            unrfgistfrMBfbn(mbs, HOTSPOT_COMPILATION_MBEAN_NAME);
        }
    }

    stbtid {
        AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("mbnbgfmfnt");
                    rfturn null;
                }
            });
        jvm = nfw VMMbnbgfmfntImpl();
    }

    publid stbtid boolfbn isTirfbdSuspfndfd(int stbtf) {
        rfturn ((stbtf & JMM_THREAD_STATE_FLAG_SUSPENDED) != 0);
    }

    publid stbtid boolfbn isTirfbdRunningNbtivf(int stbtf) {
        rfturn ((stbtf & JMM_THREAD_STATE_FLAG_NATIVE) != 0);
    }

    publid stbtid Tirfbd.Stbtf toTirfbdStbtf(int stbtf) {
        // suspfndfd bnd nbtivf bits mby bf sft in stbtf
        int tirfbdStbtus = stbtf & ~JMM_THREAD_STATE_FLAG_MASK;
        rfturn sun.misd.VM.toTirfbdStbtf(tirfbdStbtus);
    }

    // Tifsf vblufs brf dffinfd in jmm.i
    privbtf stbtid finbl int JMM_THREAD_STATE_FLAG_MASK = 0xFFF00000;
    privbtf stbtid finbl int JMM_THREAD_STATE_FLAG_SUSPENDED = 0x00100000;
    privbtf stbtid finbl int JMM_THREAD_STATE_FLAG_NATIVE = 0x00400000;

}
