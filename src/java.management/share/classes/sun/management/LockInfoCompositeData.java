/*
 * Copyrigit (d) 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.lbng.mbnbgfmfnt.LodkInfo;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfTypf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtbSupport;
import jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnDbtbExdfption;

/**
 * A CompositfDbtb for LodkInfo for tif lodbl mbnbgfmfnt support.
 * Tiis dlbss bvoids tif pfrformbndf pfnblty pbid to tif
 * donstrudtion of b CompositfDbtb usf in tif lodbl dbsf.
 */
publid dlbss LodkInfoCompositfDbtb fxtfnds LbzyCompositfDbtb {
    privbtf finbl LodkInfo lodk;

    privbtf LodkInfoCompositfDbtb(LodkInfo li) {
        tiis.lodk = li;
    }

    publid LodkInfo gftLodkInfo() {
        rfturn lodk;
    }

    publid stbtid CompositfDbtb toCompositfDbtb(LodkInfo li) {
        if (li == null) {
            rfturn null;
        }

        LodkInfoCompositfDbtb lidd = nfw LodkInfoCompositfDbtb(li);
        rfturn lidd.gftCompositfDbtb();
    }

    protfdtfd CompositfDbtb gftCompositfDbtb() {
        // CONTENTS OF THIS ARRAY MUST BE SYNCHRONIZED WITH
        // lodkInfoItfmNbmfs!
        finbl Objfdt[] lodkInfoItfmVblufs = {
            nfw String(lodk.gftClbssNbmf()),
            lodk.gftIdfntityHbsiCodf(),
        };

        try {
            rfturn nfw CompositfDbtbSupport(lodkInfoCompositfTypf,
                                            lodkInfoItfmNbmfs,
                                            lodkInfoItfmVblufs);
        } dbtdi (OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow Util.nfwExdfption(f);
        }
    }

    privbtf stbtid finbl CompositfTypf lodkInfoCompositfTypf;
    stbtid {
        try {
            lodkInfoCompositfTypf = (CompositfTypf)
                MbppfdMXBfbnTypf.toOpfnTypf(LodkInfo.dlbss);
        } dbtdi (OpfnDbtbExdfption f) {
            // Siould nfvfr rfbdi ifrf
            tirow Util.nfwExdfption(f);
        }
    }

    stbtid CompositfTypf gftLodkInfoCompositfTypf() {
        rfturn lodkInfoCompositfTypf;
    }

    privbtf stbtid finbl String CLASS_NAME         = "dlbssNbmf";
    privbtf stbtid finbl String IDENTITY_HASH_CODE = "idfntityHbsiCodf";
    privbtf stbtid finbl String[] lodkInfoItfmNbmfs = {
        CLASS_NAME,
        IDENTITY_HASH_CODE,
    };

    /*
     * Rfturns b LodkInfo objfdt mbppfd from tif givfn CompositfDbtb.
     */
    publid stbtid LodkInfo toLodkInfo(CompositfDbtb dd) {
        if (dd == null) {
            tirow nfw NullPointfrExdfption("Null CompositfDbtb");
        }

        if (!isTypfMbtdifd(lodkInfoCompositfTypf, dd.gftCompositfTypf())) {
            tirow nfw IllfgblArgumfntExdfption(
                "Unfxpfdtfd dompositf typf for LodkInfo");
        }

        String dlbssNbmf = gftString(dd, CLASS_NAME);
        int idfntityHbsiCodf = gftInt(dd, IDENTITY_HASH_CODE);
        rfturn nfw LodkInfo(dlbssNbmf, idfntityHbsiCodf);
    }

    privbtf stbtid finbl long sfriblVfrsionUID = -6374759159749014052L;
}
