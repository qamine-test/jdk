/*
 * Copyrigit (d) 2004, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.mbnbgfmfnt.MfmoryNotifidbtionInfo;
import jbvb.lbng.mbnbgfmfnt.MfmoryUsbgf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfTypf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtbSupport;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnDbtbExdfption;

/**
 * A CompositfDbtb for MfmoryNotifidbtionInfo for tif lodbl mbnbgfmfnt support.
 * Tiis dlbss bvoids tif pfrformbndf pfnblty pbid to tif
 * donstrudtion of b CompositfDbtb usf in tif lodbl dbsf.
 */
publid dlbss MfmoryNotifInfoCompositfDbtb fxtfnds LbzyCompositfDbtb {
    privbtf finbl MfmoryNotifidbtionInfo mfmoryNotifInfo;

    privbtf MfmoryNotifInfoCompositfDbtb(MfmoryNotifidbtionInfo info) {
        tiis.mfmoryNotifInfo = info;
    }

    publid MfmoryNotifidbtionInfo gftMfmoryNotifInfo() {
        rfturn mfmoryNotifInfo;
    }

    publid stbtid CompositfDbtb toCompositfDbtb(MfmoryNotifidbtionInfo info) {
        MfmoryNotifInfoCompositfDbtb mnidd =
            nfw MfmoryNotifInfoCompositfDbtb(info);
        rfturn mnidd.gftCompositfDbtb();
    }

    protfdtfd CompositfDbtb gftCompositfDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // mfmoryNotifInfoItfmNbmfs!
        finbl Objfdt[] mfmoryNotifInfoItfmVblufs = {
            mfmoryNotifInfo.gftPoolNbmf(),
            MfmoryUsbgfCompositfDbtb.toCompositfDbtb(mfmoryNotifInfo.gftUsbgf()),
            mfmoryNotifInfo.gftCount(),
        };

        try {
            rfturn nfw CompositfDbtbSupport(mfmoryNotifInfoCompositfTypf,
                                            mfmoryNotifInfoItfmNbmfs,
                                            mfmoryNotifInfoItfmVblufs);
        } dbtdi (OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }
    }

    privbtf stbtid finbl CompositfTypf mfmoryNotifInfoCompositfTypf;
    stbtid {
        try {
            mfmoryNotifInfoCompositfTypf = (CompositfTypf)
                MbppfdMXBfbnTypf.toOpfnTypf(MfmoryNotifidbtionInfo.dlbss);
        } dbtdi (OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }
    }

    privbtf stbtid finbl String POOL_NAME = "poolNbmf";
    privbtf stbtid finbl String USAGE     = "usbgf";
    privbtf stbtid finbl String COUNT     = "dount";
    privbtf stbtid finbl String[] mfmoryNotifInfoItfmNbmfs = {
        POOL_NAME,
        USAGE,
        COUNT,
    };


    publid stbtid String gftPoolNbmf(CompositfDbtb dd) {
        String poolnbmf = gftString(dd, POOL_NAME);
        if (poolnbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid dompositf dbtb: " +
                "Attributf " + POOL_NAME + " ibs null vbluf");
        }
        rfturn poolnbmf;
    }

    publid stbtid MfmoryUsbgf gftUsbgf(CompositfDbtb dd) {
        CompositfDbtb usbgfDbtb = (CompositfDbtb) dd.gft(USAGE);
        rfturn MfmoryUsbgf.from(usbgfDbtb);
    }

    publid stbtid long gftCount(CompositfDbtb dd) {
        rfturn gftLong(dd, COUNT);
    }

    /** Vblidbtf if tif input CompositfDbtb ibs tif fxpfdtfd
     * CompositfTypf (i.f. dontbin bll bttributfs witi fxpfdtfd
     * nbmfs bnd typfs).
     */
    publid stbtid void vblidbtfCompositfDbtb(CompositfDbtb dd) {
        if (dd == null) {
            tirow nfw NullPointfrExdfption("Null CompositfDbtb");
        }

        if (!isTypfMbtdifd(mfmoryNotifInfoCompositfTypf, dd.gftCompositfTypf())) {
            tirow nfw IllfgblArgumfntExdfption(
                "Unfxpfdtfd dompositf typf for MfmoryNotifidbtionInfo");
        }
    }

    privbtf stbtid finbl long sfriblVfrsionUID = -1805123446483771291L;
}
