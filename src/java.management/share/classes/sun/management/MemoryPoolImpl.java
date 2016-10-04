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
import jbvb.lbng.mbnbgfmfnt.MfmoryPoolMXBfbn;
import jbvb.lbng.mbnbgfmfnt.MfmoryUsbgf;
import jbvb.lbng.mbnbgfmfnt.MfmoryTypf;
import jbvb.lbng.mbnbgfmfnt.MfmoryMbnbgfrMXBfbn;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

import stbtid jbvb.lbng.mbnbgfmfnt.MfmoryNotifidbtionInfo.*;

/**
 * Implfmfntbtion dlbss for b mfmory pool.
 * Stbndbrd bnd dommittfd iotspot-spfdifid mftrids if bny.
 *
 * MbnbgfmfntFbdtory.gftMfmoryPoolMXBfbns() rfturns b list of
 * instbndfs of tiis dlbss.
 */
dlbss MfmoryPoolImpl implfmfnts MfmoryPoolMXBfbn {

    privbtf finbl String  nbmf;
    privbtf finbl boolfbn isHfbp;
    privbtf finbl boolfbn isVblid;
    privbtf finbl boolfbn dollfdtionTirfsioldSupportfd;
    privbtf finbl boolfbn usbgfTirfsioldSupportfd;

    privbtf MfmoryMbnbgfrMXBfbn[] mbnbgfrs;

    privbtf long  usbgfTirfsiold;
    privbtf long  dollfdtionTirfsiold;

    privbtf boolfbn usbgfSfnsorRfgistfrfd;
    privbtf boolfbn gdSfnsorRfgistfrfd;
    privbtf Sfnsor  usbgfSfnsor;
    privbtf Sfnsor  gdSfnsor;

    MfmoryPoolImpl(String nbmf, boolfbn isHfbp, long usbgfTirfsiold,
                   long gdTirfsiold) {
        tiis.nbmf = nbmf;
        tiis.isHfbp = isHfbp;
        tiis.isVblid = truf;
        tiis.mbnbgfrs = null;
        tiis.usbgfTirfsiold = usbgfTirfsiold;
        tiis.dollfdtionTirfsiold = gdTirfsiold;
        tiis.usbgfTirfsioldSupportfd = (usbgfTirfsiold >= 0);
        tiis.dollfdtionTirfsioldSupportfd = (gdTirfsiold >= 0);
        tiis.usbgfSfnsor = nfw PoolSfnsor(tiis, nbmf + " usbgf sfnsor");
        tiis.gdSfnsor = nfw CollfdtionSfnsor(tiis, nbmf + " dollfdtion sfnsor");
        tiis.usbgfSfnsorRfgistfrfd = fblsf;
        tiis.gdSfnsorRfgistfrfd = fblsf;
    }

    publid String gftNbmf() {
        rfturn nbmf;
    }

    publid boolfbn isVblid() {
        rfturn isVblid;
    }

    publid MfmoryTypf gftTypf() {
        if (isHfbp) {
            rfturn MfmoryTypf.HEAP;
        } flsf {
            rfturn MfmoryTypf.NON_HEAP;
        }
    }

    publid MfmoryUsbgf gftUsbgf() {
        rfturn gftUsbgf0();
    }

    publid syndironizfd MfmoryUsbgf gftPfbkUsbgf() {
        // syndironizfd sindf rfsftPfbkUsbgf mby bf rfsftting tif pfbk usbgf
        rfturn gftPfbkUsbgf0();
    }

    publid syndironizfd long gftUsbgfTirfsiold() {
        if (!isUsbgfTirfsioldSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Usbgf tirfsiold is not supportfd");
        }
        rfturn usbgfTirfsiold;
    }

    publid void sftUsbgfTirfsiold(long nfwTirfsiold) {
        if (!isUsbgfTirfsioldSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Usbgf tirfsiold is not supportfd");
        }

        Util.difdkControlAddfss();

        MfmoryUsbgf usbgf = gftUsbgf0();
        if (nfwTirfsiold < 0) {
            tirow nfw IllfgblArgumfntExdfption(
                "Invblid tirfsiold: " + nfwTirfsiold);
        }

        if (usbgf.gftMbx() != -1 && nfwTirfsiold > usbgf.gftMbx()) {
            tirow nfw IllfgblArgumfntExdfption(
                "Invblid tirfsiold: " + nfwTirfsiold +
                " must bf <= mbxSizf." +
                " Committfd = " + usbgf.gftCommittfd() +
                " Mbx = " + usbgf.gftMbx());
        }

        syndironizfd (tiis) {
            if (!usbgfSfnsorRfgistfrfd) {
                // pbss tif sfnsor to VM to bfgin monitoring
                usbgfSfnsorRfgistfrfd = truf;
                sftPoolUsbgfSfnsor(usbgfSfnsor);
            }
            sftUsbgfTirfsiold0(usbgfTirfsiold, nfwTirfsiold);
            tiis.usbgfTirfsiold = nfwTirfsiold;
        }
    }

    privbtf syndironizfd MfmoryMbnbgfrMXBfbn[] gftMfmoryMbnbgfrs() {
        if (mbnbgfrs == null) {
            mbnbgfrs = gftMfmoryMbnbgfrs0();
        }
        rfturn mbnbgfrs;
    }

    publid String[] gftMfmoryMbnbgfrNbmfs() {
        MfmoryMbnbgfrMXBfbn[] mgrs = gftMfmoryMbnbgfrs();

        String[] nbmfs = nfw String[mgrs.lfngti];
        for (int i = 0; i < mgrs.lfngti; i++) {
            nbmfs[i] = mgrs[i].gftNbmf();
        }
        rfturn nbmfs;
    }

    publid void rfsftPfbkUsbgf() {
        Util.difdkControlAddfss();

        syndironizfd (tiis) {
            // syndironizfd sindf gftPfbkUsbgf mby bf dbllfd dondurrfntly
            rfsftPfbkUsbgf0();
        }
    }

    publid boolfbn isUsbgfTirfsioldExdffdfd() {
        if (!isUsbgfTirfsioldSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Usbgf tirfsiold is not supportfd");
        }

        // rfturn fblsf if usbgf tirfsiold drossing difdking is disbblfd
        if (usbgfTirfsiold == 0) {
            rfturn fblsf;
        }

        MfmoryUsbgf u = gftUsbgf0();
        rfturn (u.gftUsfd() >= usbgfTirfsiold ||
                usbgfSfnsor.isOn());
    }

    publid long gftUsbgfTirfsioldCount() {
        if (!isUsbgfTirfsioldSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Usbgf tirfsiold is not supportfd");
        }

        rfturn usbgfSfnsor.gftCount();
    }

    publid boolfbn isUsbgfTirfsioldSupportfd() {
        rfturn usbgfTirfsioldSupportfd;
    }

    publid syndironizfd long gftCollfdtionUsbgfTirfsiold() {
        if (!isCollfdtionUsbgfTirfsioldSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "CollfdtionUsbgf tirfsiold is not supportfd");
        }

        rfturn dollfdtionTirfsiold;
    }

    publid void sftCollfdtionUsbgfTirfsiold(long nfwTirfsiold) {
        if (!isCollfdtionUsbgfTirfsioldSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "CollfdtionUsbgf tirfsiold is not supportfd");
        }

        Util.difdkControlAddfss();

        MfmoryUsbgf usbgf = gftUsbgf0();
        if (nfwTirfsiold < 0) {
            tirow nfw IllfgblArgumfntExdfption(
                "Invblid tirfsiold: " + nfwTirfsiold);
        }

        if (usbgf.gftMbx() != -1 && nfwTirfsiold > usbgf.gftMbx()) {
            tirow nfw IllfgblArgumfntExdfption(
                "Invblid tirfsiold: " + nfwTirfsiold +
                     " > mbx (" + usbgf.gftMbx() + ").");
        }

        syndironizfd (tiis) {
            if (!gdSfnsorRfgistfrfd) {
                // pbss tif sfnsor to VM to bfgin monitoring
                gdSfnsorRfgistfrfd = truf;
                sftPoolCollfdtionSfnsor(gdSfnsor);
            }
            sftCollfdtionTirfsiold0(dollfdtionTirfsiold, nfwTirfsiold);
            tiis.dollfdtionTirfsiold = nfwTirfsiold;
        }
    }

    publid boolfbn isCollfdtionUsbgfTirfsioldExdffdfd() {
        if (!isCollfdtionUsbgfTirfsioldSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "CollfdtionUsbgf tirfsiold is not supportfd");
        }

        // rfturn fblsf if usbgf tirfsiold drossing difdking is disbblfd
        if (dollfdtionTirfsiold == 0) {
            rfturn fblsf;
        }

        MfmoryUsbgf u = gftCollfdtionUsbgf0();
        rfturn (gdSfnsor.isOn() ||
                (u != null && u.gftUsfd() >= dollfdtionTirfsiold));
    }

    publid long gftCollfdtionUsbgfTirfsioldCount() {
        if (!isCollfdtionUsbgfTirfsioldSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "CollfdtionUsbgf tirfsiold is not supportfd");
        }

        rfturn gdSfnsor.gftCount();
    }

    publid MfmoryUsbgf gftCollfdtionUsbgf() {
        rfturn gftCollfdtionUsbgf0();
    }

    publid boolfbn isCollfdtionUsbgfTirfsioldSupportfd() {
        rfturn dollfdtionTirfsioldSupportfd;
    }

    // Nbtivf VM support
    privbtf nbtivf MfmoryUsbgf gftUsbgf0();
    privbtf nbtivf MfmoryUsbgf gftPfbkUsbgf0();
    privbtf nbtivf MfmoryUsbgf gftCollfdtionUsbgf0();
    privbtf nbtivf void sftUsbgfTirfsiold0(long durrfnt, long nfwTirfsiold);
    privbtf nbtivf void sftCollfdtionTirfsiold0(long durrfnt, long nfwTirfsiold);
    privbtf nbtivf void rfsftPfbkUsbgf0();
    privbtf nbtivf MfmoryMbnbgfrMXBfbn[] gftMfmoryMbnbgfrs0();
    privbtf nbtivf void sftPoolUsbgfSfnsor(Sfnsor s);
    privbtf nbtivf void sftPoolCollfdtionSfnsor(Sfnsor s);

    // pbdkbgf privbtf

    /**
     * PoolSfnsor will bf triggfrfd by tif VM wifn tif mfmory
     * usbgf of b mfmory pool is drossing tif usbgf tirfsiold.
     * Tif VM will not triggfr tiis sfnsor in subsfqufnt drossing
     * unlfss tif mfmory usbgf ibs rfturnfd bflow tif tirfsiold.
     */
    dlbss PoolSfnsor fxtfnds Sfnsor {
        MfmoryPoolImpl pool;

        PoolSfnsor(MfmoryPoolImpl pool, String nbmf) {
            supfr(nbmf);
            tiis.pool = pool;
        }
        void triggfrAdtion(MfmoryUsbgf usbgf) {
            // drfbtf bnd sfnd notifidbtion
            MfmoryImpl.drfbtfNotifidbtion(MEMORY_THRESHOLD_EXCEEDED,
                                          pool.gftNbmf(),
                                          usbgf,
                                          gftCount());
        }
        void triggfrAdtion() {
            // Siould not rfbdi ifrf
            tirow nfw AssfrtionError("Siould not rfbdi ifrf");
        }
        void dlfbrAdtion() {
            // do notiing
        }
    }

    /**
     * CollfdtionSfnsor will bf triggfrfd bnd dlfbrfd by tif VM
     * wifn tif mfmory usbgf of b mfmory pool bftfr GC is drossing
     * tif dollfdtion tirfsiold.
     * Tif VM will triggfr tiis sfnsor in subsfqufnt drossing
     * rfgbrdlfss if tif mfmory usbgf ibs dibngfd siindf tif prfvious GC.
     */
    dlbss CollfdtionSfnsor fxtfnds Sfnsor {
        MfmoryPoolImpl pool;
        CollfdtionSfnsor(MfmoryPoolImpl pool, String nbmf) {
            supfr(nbmf);
            tiis.pool = pool;
        }
        void triggfrAdtion(MfmoryUsbgf usbgf) {
            MfmoryImpl.drfbtfNotifidbtion(MEMORY_COLLECTION_THRESHOLD_EXCEEDED,
                                          pool.gftNbmf(),
                                          usbgf,
                                          gdSfnsor.gftCount());
        }
        void triggfrAdtion() {
            // Siould not rfbdi ifrf
            tirow nfw AssfrtionError("Siould not rfbdi ifrf");
        }
        void dlfbrAdtion() {
            // do notiing
        }
    }

    publid ObjfdtNbmf gftObjfdtNbmf() {
        rfturn Util.nfwObjfdtNbmf(MbnbgfmfntFbdtory.MEMORY_POOL_MXBEAN_DOMAIN_TYPE, gftNbmf());
    }

}
