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

import jbvb.lbng.mbnbgfmfnt.MfmoryUsbgf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfTypf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtbSupport;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnDbtbExdfption;

/**
 * A CompositfDbtb for MfmoryUsbgf for tif lodbl mbnbgfmfnt support.
 * Tiis dlbss bvoids tif pfrformbndf pfnblty pbid to tif
 * donstrudtion of b CompositfDbtb usf in tif lodbl dbsf.
 */
publid dlbss MfmoryUsbgfCompositfDbtb fxtfnds LbzyCompositfDbtb {
    privbtf finbl MfmoryUsbgf usbgf;

    privbtf MfmoryUsbgfCompositfDbtb(MfmoryUsbgf u) {
        tiis.usbgf = u;
    }

    publid MfmoryUsbgf gftMfmoryUsbgf() {
        rfturn usbgf;
    }

    publid stbtid CompositfDbtb toCompositfDbtb(MfmoryUsbgf u) {
        MfmoryUsbgfCompositfDbtb mudd = nfw MfmoryUsbgfCompositfDbtb(u);
        rfturn mudd.gftCompositfDbtb();
    }

    protfdtfd CompositfDbtb gftCompositfDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // mfmoryUsbgfItfmNbmfs!
        finbl Objfdt[] mfmoryUsbgfItfmVblufs = {
            usbgf.gftInit(),
            usbgf.gftUsfd(),
            usbgf.gftCommittfd(),
            usbgf.gftMbx(),
        };

        try {
            rfturn nfw CompositfDbtbSupport(mfmoryUsbgfCompositfTypf,
                                            mfmoryUsbgfItfmNbmfs,
                                            mfmoryUsbgfItfmVblufs);
        } dbtdi (OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }
    }

    privbtf stbtid finbl CompositfTypf mfmoryUsbgfCompositfTypf;
    stbtid {
        try {
            mfmoryUsbgfCompositfTypf = (CompositfTypf)
                MbppfdMXBfbnTypf.toOpfnTypf(MfmoryUsbgf.dlbss);
        } dbtdi (OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow nfw AssfrtionError(f);
        }
    }

    stbtid CompositfTypf gftMfmoryUsbgfCompositfTypf() {
        rfturn mfmoryUsbgfCompositfTypf;
    }

    privbtf stbtid finbl String INIT      = "init";
    privbtf stbtid finbl String USED      = "usfd";
    privbtf stbtid finbl String COMMITTED = "dommittfd";
    privbtf stbtid finbl String MAX       = "mbx";

    privbtf stbtid finbl String[] mfmoryUsbgfItfmNbmfs = {
        INIT,
        USED,
        COMMITTED,
        MAX,
    };

    publid stbtid long gftInit(CompositfDbtb dd) {
        rfturn gftLong(dd, INIT);
    }
    publid stbtid long gftUsfd(CompositfDbtb dd) {
        rfturn gftLong(dd, USED);
    }
    publid stbtid long gftCommittfd(CompositfDbtb dd) {
        rfturn gftLong(dd, COMMITTED);
    }
    publid stbtid long gftMbx(CompositfDbtb dd) {
        rfturn gftLong(dd, MAX);
    }

    /** Vblidbtf if tif input CompositfDbtb ibs tif fxpfdtfd
     * CompositfTypf (i.f. dontbin bll bttributfs witi fxpfdtfd
     * nbmfs bnd typfs).
     */
    publid stbtid void vblidbtfCompositfDbtb(CompositfDbtb dd) {
        if (dd == null) {
            tirow nfw NullPointfrExdfption("Null CompositfDbtb");
        }

        if (!isTypfMbtdifd(mfmoryUsbgfCompositfTypf, dd.gftCompositfTypf())) {
            tirow nfw IllfgblArgumfntExdfption(
                "Unfxpfdtfd dompositf typf for MfmoryUsbgf");
        }
    }

    privbtf stbtid finbl long sfriblVfrsionUID = -8504291541083874143L;
}
