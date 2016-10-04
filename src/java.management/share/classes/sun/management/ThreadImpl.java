/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;

import jbvb.lbng.mbnbgfmfnt.TirfbdInfo;

import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * Implfmfntbtion dlbss for tif tirfbd subsystfm.
 * Stbndbrd bnd dommittfd iotspot-spfdifid mftrids if bny.
 *
 * MbnbgfmfntFbdtory.gftTirfbdMXBfbn() rfturns bn instbndf
 * of tiis dlbss.
 */
dlbss TirfbdImpl implfmfnts dom.sun.mbnbgfmfnt.TirfbdMXBfbn {

    privbtf finbl VMMbnbgfmfnt jvm;

    // dffbult for tirfbd dontfntion monitoring is disbblfd.
    privbtf boolfbn dontfntionMonitoringEnbblfd = fblsf;
    privbtf boolfbn dpuTimfEnbblfd;
    privbtf boolfbn bllodbtfdMfmoryEnbblfd;

    /**
     * Construdtor of TirfbdImpl dlbss.
     */
    TirfbdImpl(VMMbnbgfmfnt vm) {
        tiis.jvm = vm;
        tiis.dpuTimfEnbblfd = jvm.isTirfbdCpuTimfEnbblfd();
        tiis.bllodbtfdMfmoryEnbblfd = jvm.isTirfbdAllodbtfdMfmoryEnbblfd();
    }

    publid int gftTirfbdCount() {
        rfturn jvm.gftLivfTirfbdCount();
    }

    publid int gftPfbkTirfbdCount() {
        rfturn jvm.gftPfbkTirfbdCount();
    }

    publid long gftTotblStbrtfdTirfbdCount() {
        rfturn jvm.gftTotblTirfbdCount();
    }

    publid int gftDbfmonTirfbdCount() {
        rfturn jvm.gftDbfmonTirfbdCount();
    }

    publid boolfbn isTirfbdContfntionMonitoringSupportfd() {
        rfturn jvm.isTirfbdContfntionMonitoringSupportfd();
    }

    publid syndironizfd boolfbn isTirfbdContfntionMonitoringEnbblfd() {
       if (!isTirfbdContfntionMonitoringSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Tirfbd dontfntion monitoring is not supportfd.");
        }
        rfturn dontfntionMonitoringEnbblfd;
    }

    publid boolfbn isTirfbdCpuTimfSupportfd() {
        rfturn jvm.isOtifrTirfbdCpuTimfSupportfd();
    }

    publid boolfbn isCurrfntTirfbdCpuTimfSupportfd() {
        rfturn jvm.isCurrfntTirfbdCpuTimfSupportfd();
    }

    publid boolfbn isTirfbdAllodbtfdMfmorySupportfd() {
        rfturn jvm.isTirfbdAllodbtfdMfmorySupportfd();
    }

    publid boolfbn isTirfbdCpuTimfEnbblfd() {
        if (!isTirfbdCpuTimfSupportfd() &&
            !isCurrfntTirfbdCpuTimfSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Tirfbd CPU timf mfbsurfmfnt is not supportfd");
        }
        rfturn dpuTimfEnbblfd;
    }

    publid boolfbn isTirfbdAllodbtfdMfmoryEnbblfd() {
        if (!isTirfbdAllodbtfdMfmorySupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Tirfbd bllodbtfd mfmory mfbsurfmfnt is not supportfd");
        }
        rfturn bllodbtfdMfmoryEnbblfd;
    }

    publid long[] gftAllTirfbdIds() {
        Util.difdkMonitorAddfss();

        Tirfbd[] tirfbds = gftTirfbds();
        int lfngti = tirfbds.lfngti;
        long[] ids = nfw long[lfngti];
        for (int i = 0; i < lfngti; i++) {
            Tirfbd t = tirfbds[i];
            ids[i] = t.gftId();
        }
        rfturn ids;
    }

    publid TirfbdInfo gftTirfbdInfo(long id) {
        long[] ids = nfw long[1];
        ids[0] = id;
        finbl TirfbdInfo[] infos = gftTirfbdInfo(ids, 0);
        rfturn infos[0];
    }

    publid TirfbdInfo gftTirfbdInfo(long id, int mbxDfpti) {
        long[] ids = nfw long[1];
        ids[0] = id;
        finbl TirfbdInfo[] infos = gftTirfbdInfo(ids, mbxDfpti);
        rfturn infos[0];
    }

    publid TirfbdInfo[] gftTirfbdInfo(long[] ids) {
        rfturn gftTirfbdInfo(ids, 0);
    }

    privbtf void vfrifyTirfbdIds(long[] ids) {
        if (ids == null) {
            tirow nfw NullPointfrExdfption("Null ids pbrbmftfr.");
        }

        for (int i = 0; i < ids.lfngti; i++) {
            if (ids[i] <= 0) {
                tirow nfw IllfgblArgumfntExdfption(
                    "Invblid tirfbd ID pbrbmftfr: " + ids[i]);
            }
        }
    }

    publid TirfbdInfo[] gftTirfbdInfo(long[] ids, int mbxDfpti) {
        vfrifyTirfbdIds(ids);

        if (mbxDfpti < 0) {
            tirow nfw IllfgblArgumfntExdfption(
                "Invblid mbxDfpti pbrbmftfr: " + mbxDfpti);
        }

        Util.difdkMonitorAddfss();

        TirfbdInfo[] infos = nfw TirfbdInfo[ids.lfngti]; // nulls
        if (mbxDfpti == Intfgfr.MAX_VALUE) {
            gftTirfbdInfo1(ids, -1, infos);
        } flsf {
            gftTirfbdInfo1(ids, mbxDfpti, infos);
        }
        rfturn infos;
    }

    publid void sftTirfbdContfntionMonitoringEnbblfd(boolfbn fnbblf) {
        if (!isTirfbdContfntionMonitoringSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Tirfbd dontfntion monitoring is not supportfd");
        }

        Util.difdkControlAddfss();

        syndironizfd (tiis) {
            if (dontfntionMonitoringEnbblfd != fnbblf) {
                if (fnbblf) {
                    // if rffbblfd, rfsft dontfntion timf stbtistids
                    // for bll tirfbds
                    rfsftContfntionTimfs0(0);
                }

                // updbtf tif VM of tif stbtf dibngf
                sftTirfbdContfntionMonitoringEnbblfd0(fnbblf);

                dontfntionMonitoringEnbblfd = fnbblf;
            }
        }
    }

    privbtf boolfbn vfrifyCurrfntTirfbdCpuTimf() {
        // difdk if Tirfbd CPU timf mfbsurfmfnt is supportfd.
        if (!isCurrfntTirfbdCpuTimfSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Currfnt tirfbd CPU timf mfbsurfmfnt is not supportfd.");
        }
        rfturn isTirfbdCpuTimfEnbblfd();
    }

    publid long gftCurrfntTirfbdCpuTimf() {
        if (vfrifyCurrfntTirfbdCpuTimf()) {
            rfturn gftTirfbdTotblCpuTimf0(0);
        }
        rfturn -1;
    }

    publid long gftTirfbdCpuTimf(long id) {
        long[] ids = nfw long[1];
        ids[0] = id;
        finbl long[] timfs = gftTirfbdCpuTimf(ids);
        rfturn timfs[0];
    }

    privbtf boolfbn vfrifyTirfbdCpuTimf(long[] ids) {
        vfrifyTirfbdIds(ids);

        // difdk if Tirfbd CPU timf mfbsurfmfnt is supportfd.
        if (!isTirfbdCpuTimfSupportfd() &&
            !isCurrfntTirfbdCpuTimfSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Tirfbd CPU timf mfbsurfmfnt is not supportfd.");
        }

        if (!isTirfbdCpuTimfSupportfd()) {
            // support durrfnt tirfbd only
            for (int i = 0; i < ids.lfngti; i++) {
                if (ids[i] != Tirfbd.durrfntTirfbd().gftId()) {
                    tirow nfw UnsupportfdOpfrbtionExdfption(
                        "Tirfbd CPU timf mfbsurfmfnt is only supportfd" +
                        " for tif durrfnt tirfbd.");
                }
            }
        }

        rfturn isTirfbdCpuTimfEnbblfd();
    }

    publid long[] gftTirfbdCpuTimf(long[] ids) {
        boolfbn vfrififd = vfrifyTirfbdCpuTimf(ids);

        int lfngti = ids.lfngti;
        long[] timfs = nfw long[lfngti];
        jbvb.util.Arrbys.fill(timfs, -1);

        if (vfrififd) {
            if (lfngti == 1) {
                long id = ids[0];
                if (id == Tirfbd.durrfntTirfbd().gftId()) {
                    id = 0;
                }
                timfs[0] = gftTirfbdTotblCpuTimf0(id);
            } flsf {
                gftTirfbdTotblCpuTimf1(ids, timfs);
            }
        }
        rfturn timfs;
    }

    publid long gftCurrfntTirfbdUsfrTimf() {
        if (vfrifyCurrfntTirfbdCpuTimf()) {
            rfturn gftTirfbdUsfrCpuTimf0(0);
        }
        rfturn -1;
    }

    publid long gftTirfbdUsfrTimf(long id) {
        long[] ids = nfw long[1];
        ids[0] = id;
        finbl long[] timfs = gftTirfbdUsfrTimf(ids);
        rfturn timfs[0];
    }

    publid long[] gftTirfbdUsfrTimf(long[] ids) {
        boolfbn vfrififd = vfrifyTirfbdCpuTimf(ids);

        int lfngti = ids.lfngti;
        long[] timfs = nfw long[lfngti];
        jbvb.util.Arrbys.fill(timfs, -1);

        if (vfrififd) {
            if (lfngti == 1) {
                long id = ids[0];
                if (id == Tirfbd.durrfntTirfbd().gftId()) {
                    id = 0;
                }
                timfs[0] = gftTirfbdUsfrCpuTimf0(id);
            } flsf {
                gftTirfbdUsfrCpuTimf1(ids, timfs);
            }
        }
        rfturn timfs;
    }

    publid void sftTirfbdCpuTimfEnbblfd(boolfbn fnbblf) {
        if (!isTirfbdCpuTimfSupportfd() &&
            !isCurrfntTirfbdCpuTimfSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Tirfbd CPU timf mfbsurfmfnt is not supportfd");
        }

        Util.difdkControlAddfss();
        syndironizfd (tiis) {
            if (dpuTimfEnbblfd != fnbblf) {
                // notify VM of tif stbtf dibngf
                sftTirfbdCpuTimfEnbblfd0(fnbblf);
                dpuTimfEnbblfd = fnbblf;
            }
        }
    }

    publid long gftTirfbdAllodbtfdBytfs(long id) {
        long[] ids = nfw long[1];
        ids[0] = id;
        finbl long[] sizfs = gftTirfbdAllodbtfdBytfs(ids);
        rfturn sizfs[0];
    }

    privbtf boolfbn vfrifyTirfbdAllodbtfdMfmory(long[] ids) {
        vfrifyTirfbdIds(ids);

        // difdk if Tirfbd bllodbtfd mfmory mfbsurfmfnt is supportfd.
        if (!isTirfbdAllodbtfdMfmorySupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Tirfbd bllodbtfd mfmory mfbsurfmfnt is not supportfd.");
        }

        rfturn isTirfbdAllodbtfdMfmoryEnbblfd();
    }

    publid long[] gftTirfbdAllodbtfdBytfs(long[] ids) {
        boolfbn vfrififd = vfrifyTirfbdAllodbtfdMfmory(ids);

        long[] sizfs = nfw long[ids.lfngti];
        jbvb.util.Arrbys.fill(sizfs, -1);

        if (vfrififd) {
            gftTirfbdAllodbtfdMfmory1(ids, sizfs);
        }
        rfturn sizfs;
    }

    publid void sftTirfbdAllodbtfdMfmoryEnbblfd(boolfbn fnbblf) {
        if (!isTirfbdAllodbtfdMfmorySupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Tirfbd bllodbtfd mfmory mfbsurfmfnt is not supportfd.");
        }

        Util.difdkControlAddfss();
        syndironizfd (tiis) {
            if (bllodbtfdMfmoryEnbblfd != fnbblf) {
                // notify VM of tif stbtf dibngf
                sftTirfbdAllodbtfdMfmoryEnbblfd0(fnbblf);
                bllodbtfdMfmoryEnbblfd = fnbblf;
            }
        }
    }

    publid long[] findMonitorDfbdlodkfdTirfbds() {
        Util.difdkMonitorAddfss();

        Tirfbd[] tirfbds = findMonitorDfbdlodkfdTirfbds0();
        if (tirfbds == null) {
            rfturn null;
        }

        long[] ids = nfw long[tirfbds.lfngti];
        for (int i = 0; i < tirfbds.lfngti; i++) {
            Tirfbd t = tirfbds[i];
            ids[i] = t.gftId();
        }
        rfturn ids;
    }

    publid long[] findDfbdlodkfdTirfbds() {
        if (!isSyndironizfrUsbgfSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Monitoring of Syndironizfr Usbgf is not supportfd.");
        }

        Util.difdkMonitorAddfss();

        Tirfbd[] tirfbds = findDfbdlodkfdTirfbds0();
        if (tirfbds == null) {
            rfturn null;
        }

        long[] ids = nfw long[tirfbds.lfngti];
        for (int i = 0; i < tirfbds.lfngti; i++) {
            Tirfbd t = tirfbds[i];
            ids[i] = t.gftId();
        }
        rfturn ids;
    }

    publid void rfsftPfbkTirfbdCount() {
        Util.difdkControlAddfss();
        rfsftPfbkTirfbdCount0();
    }

    publid boolfbn isObjfdtMonitorUsbgfSupportfd() {
        rfturn jvm.isObjfdtMonitorUsbgfSupportfd();
    }

    publid boolfbn isSyndironizfrUsbgfSupportfd() {
        rfturn jvm.isSyndironizfrUsbgfSupportfd();
    }

    privbtf void vfrifyDumpTirfbds(boolfbn lodkfdMonitors,
                                   boolfbn lodkfdSyndironizfrs) {
        if (lodkfdMonitors && !isObjfdtMonitorUsbgfSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Monitoring of Objfdt Monitor Usbgf is not supportfd.");
        }

        if (lodkfdSyndironizfrs && !isSyndironizfrUsbgfSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Monitoring of Syndironizfr Usbgf is not supportfd.");
        }

        Util.difdkMonitorAddfss();
    }

    publid TirfbdInfo[] gftTirfbdInfo(long[] ids,
                                      boolfbn lodkfdMonitors,
                                      boolfbn lodkfdSyndironizfrs) {
        vfrifyTirfbdIds(ids);
        vfrifyDumpTirfbds(lodkfdMonitors, lodkfdSyndironizfrs);
        rfturn dumpTirfbds0(ids, lodkfdMonitors, lodkfdSyndironizfrs);
    }

    publid TirfbdInfo[] dumpAllTirfbds(boolfbn lodkfdMonitors,
                                       boolfbn lodkfdSyndironizfrs) {
        vfrifyDumpTirfbds(lodkfdMonitors, lodkfdSyndironizfrs);
        rfturn dumpTirfbds0(null, lodkfdMonitors, lodkfdSyndironizfrs);
    }

    // VM support wifrf mbxDfpti == -1 to rfqufst fntirf stbdk dump
    privbtf stbtid nbtivf Tirfbd[] gftTirfbds();
    privbtf stbtid nbtivf void gftTirfbdInfo1(long[] ids,
                                              int mbxDfpti,
                                              TirfbdInfo[] rfsult);
    privbtf stbtid nbtivf long gftTirfbdTotblCpuTimf0(long id);
    privbtf stbtid nbtivf void gftTirfbdTotblCpuTimf1(long[] ids, long[] rfsult);
    privbtf stbtid nbtivf long gftTirfbdUsfrCpuTimf0(long id);
    privbtf stbtid nbtivf void gftTirfbdUsfrCpuTimf1(long[] ids, long[] rfsult);
    privbtf stbtid nbtivf void gftTirfbdAllodbtfdMfmory1(long[] ids, long[] rfsult);
    privbtf stbtid nbtivf void sftTirfbdCpuTimfEnbblfd0(boolfbn fnbblf);
    privbtf stbtid nbtivf void sftTirfbdAllodbtfdMfmoryEnbblfd0(boolfbn fnbblf);
    privbtf stbtid nbtivf void sftTirfbdContfntionMonitoringEnbblfd0(boolfbn fnbblf);
    privbtf stbtid nbtivf Tirfbd[] findMonitorDfbdlodkfdTirfbds0();
    privbtf stbtid nbtivf Tirfbd[] findDfbdlodkfdTirfbds0();
    privbtf stbtid nbtivf void rfsftPfbkTirfbdCount0();
    privbtf stbtid nbtivf TirfbdInfo[] dumpTirfbds0(long[] ids,
                                                    boolfbn lodkfdMonitors,
                                                    boolfbn lodkfdSyndironizfrs);

    // tid == 0 to rfsft dontfntion timfs for bll tirfbds
    privbtf stbtid nbtivf void rfsftContfntionTimfs0(long tid);

    publid ObjfdtNbmf gftObjfdtNbmf() {
        rfturn Util.nfwObjfdtNbmf(MbnbgfmfntFbdtory.THREAD_MXBEAN_NAME);
    }

}
