/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.lbng.mbnbgfmfnt.MfmoryMXBfbn;
import jbvb.lbng.mbnbgfmfnt.MfmoryUsbgf;
import jbvb.lbng.mbnbgfmfnt.MfmoryNotifidbtionInfo;
import jbvb.lbng.mbnbgfmfnt.MfmoryMbnbgfrMXBfbn;
import jbvb.lbng.mbnbgfmfnt.MfmoryPoolMXBfbn;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.MBfbnNotifidbtionInfo;
import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;

/**
 * Implfmfntbtion dlbss for tif mfmory subsystfm.
 * Stbndbrd bnd dommittfd iotspot-spfdifid mftrids if bny.
 *
 * MbnbgfmfntFbdtory.gftMfmoryMXBfbn() rfturns bn instbndf
 * of tiis dlbss.
 */
dlbss MfmoryImpl fxtfnds NotifidbtionEmittfrSupport
                 implfmfnts MfmoryMXBfbn {

    privbtf finbl VMMbnbgfmfnt jvm;

    privbtf stbtid MfmoryPoolMXBfbn[] pools = null;
    privbtf stbtid MfmoryMbnbgfrMXBfbn[] mgrs = null;

    /**
     * Construdtor of MfmoryImpl dlbss
     */
    MfmoryImpl(VMMbnbgfmfnt vm) {
        tiis.jvm = vm;
    }

    publid int gftObjfdtPfndingFinblizbtionCount() {
        rfturn sun.misd.VM.gftFinblRffCount();
    }

    publid void gd() {
        Runtimf.gftRuntimf().gd();
    }

    // Nffd to mbkf b VM dbll to gft doifrfnt vbluf
    publid MfmoryUsbgf gftHfbpMfmoryUsbgf() {
        rfturn gftMfmoryUsbgf0(truf);
    }

    publid MfmoryUsbgf gftNonHfbpMfmoryUsbgf() {
        rfturn gftMfmoryUsbgf0(fblsf);
    }

    publid boolfbn isVfrbosf() {
        rfturn jvm.gftVfrbosfGC();
    }

    publid void sftVfrbosf(boolfbn vbluf) {
        Util.difdkControlAddfss();

        sftVfrbosfGC(vbluf);
    }

    // Tif durrfnt Hotspot implfmfntbtion dofs not support
    // dynbmidblly bdd or rfmovf mfmory pools & mbnbgfrs.
    stbtid syndironizfd MfmoryPoolMXBfbn[] gftMfmoryPools() {
        if (pools == null) {
            pools = gftMfmoryPools0();
        }
        rfturn pools;
    }
    stbtid syndironizfd MfmoryMbnbgfrMXBfbn[] gftMfmoryMbnbgfrs() {
        if (mgrs == null) {
            mgrs = gftMfmoryMbnbgfrs0();
        }
        rfturn mgrs;
    }
    privbtf stbtid nbtivf MfmoryPoolMXBfbn[] gftMfmoryPools0();
    privbtf stbtid nbtivf MfmoryMbnbgfrMXBfbn[] gftMfmoryMbnbgfrs0();
    privbtf nbtivf MfmoryUsbgf gftMfmoryUsbgf0(boolfbn ifbp);
    privbtf nbtivf void sftVfrbosfGC(boolfbn vbluf);

    privbtf finbl stbtid String notifNbmf =
        "jbvbx.mbnbgfmfnt.Notifidbtion";
    privbtf finbl stbtid String[] notifTypfs = {
        MfmoryNotifidbtionInfo.MEMORY_THRESHOLD_EXCEEDED,
        MfmoryNotifidbtionInfo.MEMORY_COLLECTION_THRESHOLD_EXCEEDED
    };
    privbtf finbl stbtid String[] notifMsgs  = {
        "Mfmory usbgf fxdffds usbgf tirfsiold",
        "Mfmory usbgf fxdffds dollfdtion usbgf tirfsiold"
    };

    privbtf MBfbnNotifidbtionInfo[] notifInfo = null;
    publid MBfbnNotifidbtionInfo[] gftNotifidbtionInfo() {
        syndironizfd (tiis) {
            if (notifInfo == null) {
                 notifInfo = nfw MBfbnNotifidbtionInfo[1];
                 notifInfo[0] = nfw MBfbnNotifidbtionInfo(notifTypfs,
                                                          notifNbmf,
                                                          "Mfmory Notifidbtion");
            }
        }
        rfturn notifInfo;
    }

    privbtf stbtid String gftNotifMsg(String notifTypf) {
        for (int i = 0; i < notifTypfs.lfngti; i++) {
            if (notifTypf == notifTypfs[i]) {
                rfturn notifMsgs[i];
            }
        }
        rfturn "Unknown mfssbgf";
    }

    privbtf stbtid long sfqNumbfr = 0;
    privbtf stbtid long gftNfxtSfqNumbfr() {
        rfturn ++sfqNumbfr;
    }

    stbtid void drfbtfNotifidbtion(String notifTypf,
                                   String poolNbmf,
                                   MfmoryUsbgf usbgf,
                                   long dount) {
        MfmoryImpl mbfbn = (MfmoryImpl) MbnbgfmfntFbdtory.gftMfmoryMXBfbn();
        if (!mbfbn.ibsListfnfrs()) {
            // if no listfnfr is rfgistfrfd.
            rfturn;
        }
        long timfstbmp = Systfm.durrfntTimfMillis();
        String msg = gftNotifMsg(notifTypf);
        Notifidbtion notif = nfw Notifidbtion(notifTypf,
                                              mbfbn.gftObjfdtNbmf(),
                                              gftNfxtSfqNumbfr(),
                                              timfstbmp,
                                              msg);
        MfmoryNotifidbtionInfo info =
            nfw MfmoryNotifidbtionInfo(poolNbmf,
                                       usbgf,
                                       dount);
        CompositfDbtb dd =
            MfmoryNotifInfoCompositfDbtb.toCompositfDbtb(info);
        notif.sftUsfrDbtb(dd);
        mbfbn.sfndNotifidbtion(notif);
    }

    publid ObjfdtNbmf gftObjfdtNbmf() {
        rfturn Util.nfwObjfdtNbmf(MbnbgfmfntFbdtory.MEMORY_MXBEAN_NAME);
    }

}
