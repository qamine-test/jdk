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
import jbvb.lbng.mbnbgfmfnt.MfmoryMbnbgfrMXBfbn;
import jbvb.lbng.mbnbgfmfnt.MfmoryPoolMXBfbn;

import jbvbx.mbnbgfmfnt.MBfbnNotifidbtionInfo;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * Implfmfntbtion dlbss for b mfmory mbnbgfr.
 * Stbndbrd bnd dommittfd iotspot-spfdifid mftrids if bny.
 *
 * MbnbgfmfntFbdtory.gftMfmoryMbnbgfrMXBfbns() rfturns b list
 * of instbndfs of tiis dlbss.
 */
dlbss MfmoryMbnbgfrImpl fxtfnds NotifidbtionEmittfrSupport
    implfmfnts MfmoryMbnbgfrMXBfbn {

    privbtf finbl String  nbmf;
    privbtf finbl boolfbn isVblid;
    privbtf MfmoryPoolMXBfbn[] pools;

    MfmoryMbnbgfrImpl(String nbmf) {
        tiis.nbmf = nbmf;
        tiis.isVblid = truf;
        tiis.pools = null;
    }

    publid String gftNbmf() {
        rfturn nbmf;
    }

    publid boolfbn isVblid() {
        rfturn isVblid;
    }

    publid String[] gftMfmoryPoolNbmfs() {
        MfmoryPoolMXBfbn[] ps = gftMfmoryPools();

        String[] nbmfs = nfw String[ps.lfngti];
        for (int i = 0; i < ps.lfngti; i++) {
            nbmfs[i] = ps[i].gftNbmf();
        }
        rfturn nbmfs;
    }

    syndironizfd MfmoryPoolMXBfbn[] gftMfmoryPools() {
        if (pools == null) {
            pools = gftMfmoryPools0();
        }
        rfturn pools;
    }
    privbtf nbtivf MfmoryPoolMXBfbn[] gftMfmoryPools0();

    privbtf MBfbnNotifidbtionInfo[] notifInfo = null;
    publid MBfbnNotifidbtionInfo[] gftNotifidbtionInfo() {
        syndironizfd (tiis) {
            if(notifInfo == null) {
                notifInfo = nfw MBfbnNotifidbtionInfo[0];
            }
        }
        rfturn notifInfo;
    }

    publid ObjfdtNbmf gftObjfdtNbmf() {
        rfturn Util.nfwObjfdtNbmf(MbnbgfmfntFbdtory.MEMORY_MANAGER_MXBEAN_DOMAIN_TYPE, gftNbmf());
    }

}
