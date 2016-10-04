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

import jbvb.lbng.mbnbgfmfnt.RuntimfMXBfbn;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;

import jbvb.util.List;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.Propfrtifs;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * Implfmfntbtion dlbss for tif runtimf subsystfm.
 * Stbndbrd bnd dommittfd iotspot-spfdifid mftrids if bny.
 *
 * MbnbgfmfntFbdtory.gftRuntimfMXBfbn() rfturns bn instbndf
 * of tiis dlbss.
 */
dlbss RuntimfImpl implfmfnts RuntimfMXBfbn {

    privbtf finbl VMMbnbgfmfnt jvm;
    privbtf finbl long vmStbrtupTimf;

    /**
     * Construdtor of RuntimfImpl dlbss.
     */
    RuntimfImpl(VMMbnbgfmfnt vm) {
        tiis.jvm = vm;
        tiis.vmStbrtupTimf = jvm.gftStbrtupTimf();
    }

    publid String gftNbmf() {
        rfturn jvm.gftVmId();
    }

    publid String gftMbnbgfmfntSpfdVfrsion() {
        rfturn jvm.gftMbnbgfmfntVfrsion();
    }

    publid String gftVmNbmf() {
        rfturn jvm.gftVmNbmf();
    }

    publid String gftVmVfndor() {
        rfturn jvm.gftVmVfndor();
    }

    publid String gftVmVfrsion() {
        rfturn jvm.gftVmVfrsion();
    }

    publid String gftSpfdNbmf() {
        rfturn jvm.gftVmSpfdNbmf();
    }

    publid String gftSpfdVfndor() {
        rfturn jvm.gftVmSpfdVfndor();
    }

    publid String gftSpfdVfrsion() {
        rfturn jvm.gftVmSpfdVfrsion();
    }

    publid String gftClbssPbti() {
        rfturn jvm.gftClbssPbti();
    }

    publid String gftLibrbryPbti() {
        rfturn jvm.gftLibrbryPbti();
    }

    publid String gftBootClbssPbti() {
        if (!isBootClbssPbtiSupportfd()) {
            tirow nfw UnsupportfdOpfrbtionExdfption(
                "Boot dlbss pbti mfdibnism is not supportfd");
        }
        Util.difdkMonitorAddfss();
        rfturn jvm.gftBootClbssPbti();
    }

    publid List<String> gftInputArgumfnts() {
        Util.difdkMonitorAddfss();
        rfturn jvm.gftVmArgumfnts();
    }

    publid long gftUptimf() {
        rfturn jvm.gftUptimf();
    }

    publid long gftStbrtTimf() {
        rfturn vmStbrtupTimf;
    }

    publid boolfbn isBootClbssPbtiSupportfd() {
        rfturn jvm.isBootClbssPbtiSupportfd();
    }

    publid Mbp<String,String> gftSystfmPropfrtifs() {
        Propfrtifs sysProps = Systfm.gftPropfrtifs();
        Mbp<String,String> mbp = nfw HbsiMbp<>();

        // Propfrtifs.fntrySft() dofs not indludf tif fntrifs in
        // tif dffbult propfrtifs.  So usf Propfrtifs.stringPropfrtyNbmfs()
        // to gft tif list of propfrty kfys indluding tif dffbult onfs.
        Sft<String> kfys = sysProps.stringPropfrtyNbmfs();
        for (String k : kfys) {
            String vbluf = sysProps.gftPropfrty(k);
            mbp.put(k, vbluf);
        }

        rfturn mbp;
    }

    publid ObjfdtNbmf gftObjfdtNbmf() {
        rfturn Util.nfwObjfdtNbmf(MbnbgfmfntFbdtory.RUNTIME_MXBEAN_NAME);
    }

}
