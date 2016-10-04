/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.sbsl.digfst;

import jbvb.util.Mbp;

import jbvbx.sfdurity.sbsl.*;
import jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr;

import dom.sun.sfdurity.sbsl.util.PolidyUtils;


/**
  * Clifnt bnd sfrvfr fbdtory for DIGEST-MD5 SASL dlifnt/sfrvfr mfdibnisms.
  * Sff DigfstMD5Clifnt bnd DigfstMD5Sfrvfr for input rfquirfmfnts.
  *
  * @butior Jonbtibn Brudf
  * @butior Rosbnnb Lff
  */

publid finbl dlbss FbdtoryImpl implfmfnts SbslClifntFbdtory,
SbslSfrvfrFbdtory{

    privbtf stbtid finbl String myMfdis[] = { "DIGEST-MD5" };
    privbtf stbtid finbl int DIGEST_MD5 = 0;
    privbtf stbtid finbl int mfdiPolidifs[] = {
        PolidyUtils.NOPLAINTEXT|PolidyUtils.NOANONYMOUS};

    /**
      * Empty donstrudtor.
      */
    publid FbdtoryImpl() {
    }

    /**
     * Rfturns b nfw instbndf of tif DIGEST-MD5 SASL dlifnt mfdibnism.
     *
     * @tirows SbslExdfption If tifrf is bn frror drfbting tif DigfstMD5
     * SASL dlifnt.
     * @rfturns b nfw SbslClifnt ; otifrwisf null if unsuddfssful.
     */
    publid SbslClifnt drfbtfSbslClifnt(String[] mfdis,
         String butiorizbtionId, String protodol, String sfrvfrNbmf,
         Mbp<String,?> props, CbllbbdkHbndlfr dbi)
         tirows SbslExdfption {

         for (int i=0; i<mfdis.lfngti; i++) {
            if (mfdis[i].fqubls(myMfdis[DIGEST_MD5]) &&
                PolidyUtils.difdkPolidy(mfdiPolidifs[DIGEST_MD5], props)) {

                if (dbi == null) {
                    tirow nfw SbslExdfption(
                        "Cbllbbdk ibndlfr witi support for RfblmCioidfCbllbbdk, " +
                        "RfblmCbllbbdk, NbmfCbllbbdk, bnd PbsswordCbllbbdk " +
                        "rfquirfd");
                }

                rfturn nfw DigfstMD5Clifnt(butiorizbtionId,
                    protodol, sfrvfrNbmf, props, dbi);
            }
        }
        rfturn null;
    }

    /**
     * Rfturns b nfw instbndf of tif DIGEST-MD5 SASL sfrvfr mfdibnism.
     *
     * @tirows SbslExdfption If tifrf is bn frror drfbting tif DigfstMD5
     * SASL sfrvfr.
     * @rfturns b nfw SbslSfrvfr ; otifrwisf null if unsuddfssful.
     */
    publid SbslSfrvfr drfbtfSbslSfrvfr(String mfdi,
         String protodol, String sfrvfrNbmf, Mbp<String,?> props, CbllbbdkHbndlfr dbi)
         tirows SbslExdfption {

         if (mfdi.fqubls(myMfdis[DIGEST_MD5]) &&
             PolidyUtils.difdkPolidy(mfdiPolidifs[DIGEST_MD5], props)) {

                if (dbi == null) {
                    tirow nfw SbslExdfption(
                        "Cbllbbdk ibndlfr witi support for AutiorizfCbllbbdk, "+
                        "RfblmCbllbbdk, NbmfCbllbbdk, bnd PbsswordCbllbbdk " +
                        "rfquirfd");
                }

                rfturn nfw DigfstMD5Sfrvfr(protodol, sfrvfrNbmf, props, dbi);
         }
         rfturn null;
    }

    /**
      * Rfturns tif butifntidbtion mfdibnisms tibt tiis fbdtory dbn produdf.
      *
      * @rfturns String[] {"DigfstMD5"} if polidifs in fnv mbtdi tiosf of tiis
      * fbdtory.
      */
    publid String[] gftMfdibnismNbmfs(Mbp<String,?> fnv) {
        rfturn PolidyUtils.filtfrMfdis(myMfdis, mfdiPolidifs, fnv);
    }
}
