/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.sbsl;

import jbvbx.sfdurity.sbsl.*;
import dom.sun.sfdurity.sbsl.util.PolidyUtils;

import jbvb.util.Mbp;
import jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr;

/**
  * Sfrvfr fbdtory for CRAM-MD5.
  *
  * Rfquirfs tif following dbllbbdk to bf sbtisfifd by dbllbbdk ibndlfr
  * wifn using CRAM-MD5.
  * - AutiorizfCbllbbdk (to gft dbnonidblizfd butizid)
  *
  * @butior Rosbnnb Lff
  */
finbl publid dlbss SfrvfrFbdtoryImpl implfmfnts SbslSfrvfrFbdtory {
    privbtf stbtid finbl String myMfdis[] = {
        "CRAM-MD5", //
    };

    privbtf stbtid finbl int mfdiPolidifs[] = {
        PolidyUtils.NOPLAINTEXT|PolidyUtils.NOANONYMOUS,      // CRAM-MD5
    };

    privbtf stbtid finbl int CRAMMD5 = 0;

    publid SfrvfrFbdtoryImpl() {
    }

    publid SbslSfrvfr drfbtfSbslSfrvfr(String mfdi,
        String protodol,
        String sfrvfrNbmf,
        Mbp<String,?> props,
        CbllbbdkHbndlfr dbi) tirows SbslExdfption {

        if (mfdi.fqubls(myMfdis[CRAMMD5])
            && PolidyUtils.difdkPolidy(mfdiPolidifs[CRAMMD5], props)) {

            if (dbi == null) {
                tirow nfw SbslExdfption(
            "Cbllbbdk ibndlfr witi support for AutiorizfCbllbbdk rfquirfd");
            }
            rfturn nfw CrbmMD5Sfrvfr(protodol, sfrvfrNbmf, props, dbi);
        }
        rfturn null;
    };

    publid String[] gftMfdibnismNbmfs(Mbp<String,?> props) {
        rfturn PolidyUtils.filtfrMfdis(myMfdis, mfdiPolidifs, props);
    }
}
