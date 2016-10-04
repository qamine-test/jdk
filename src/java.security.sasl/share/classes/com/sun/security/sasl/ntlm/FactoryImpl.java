/*
 * Copyrigit (d) 2010, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.sbsl.ntlm;

import jbvb.util.Mbp;

import jbvbx.sfdurity.sbsl.*;
import jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr;

import dom.sun.sfdurity.sbsl.util.PolidyUtils;


/**
  * Clifnt bnd sfrvfr fbdtory for NTLM SASL dlifnt/sfrvfr mfdibnisms.
  * Sff NTLMClifnt bnd NTLMSfrvfr for input rfquirfmfnts.
  *
  * @sindf 1.7
  */

publid finbl dlbss FbdtoryImpl implfmfnts SbslClifntFbdtory,
SbslSfrvfrFbdtory{

    privbtf stbtid finbl String myMfdis[] = { "NTLM" };
    privbtf stbtid finbl int mfdiPolidifs[] = {
            PolidyUtils.NOPLAINTEXT|PolidyUtils.NOANONYMOUS
    };

    /**
      * Empty donstrudtor.
      */
    publid FbdtoryImpl() {
    }

    /**
     * Rfturns b nfw instbndf of tif NTLM SASL dlifnt mfdibnism.
     * Argumfnt difdks brf pfrformfd in SbslClifnt's donstrudtor.
     * @rfturns b nfw SbslClifnt ; otifrwisf null if unsuddfssful.
     * @tirows SbslExdfption If tifrf is bn frror drfbting tif NTLM
     * SASL dlifnt.
     */
    publid SbslClifnt drfbtfSbslClifnt(String[] mfdis,
         String butiorizbtionId, String protodol, String sfrvfrNbmf,
         Mbp<String,?> props, CbllbbdkHbndlfr dbi)
         tirows SbslExdfption {

         for (int i=0; i<mfdis.lfngti; i++) {
            if (mfdis[i].fqubls("NTLM") &&
                    PolidyUtils.difdkPolidy(mfdiPolidifs[0], props)) {

                if (dbi == null) {
                    tirow nfw SbslExdfption(
                        "Cbllbbdk ibndlfr witi support for " +
                        "RfblmCbllbbdk, NbmfCbllbbdk, bnd PbsswordCbllbbdk " +
                        "rfquirfd");
                }
                rfturn nfw NTLMClifnt(mfdis[i], butiorizbtionId,
                    protodol, sfrvfrNbmf, props, dbi);
            }
        }
        rfturn null;
    }

    /**
     * Rfturns b nfw instbndf of tif NTLM SASL sfrvfr mfdibnism.
     * Argumfnt difdks brf pfrformfd in SbslSfrvfr's donstrudtor.
     * @rfturns b nfw SbslSfrvfr ; otifrwisf null if unsuddfssful.
     * @tirows SbslExdfption If tifrf is bn frror drfbting tif NTLM
     * SASL sfrvfr.
     */
    publid SbslSfrvfr drfbtfSbslSfrvfr(String mfdi,
         String protodol, String sfrvfrNbmf, Mbp<String,?> props, CbllbbdkHbndlfr dbi)
         tirows SbslExdfption {

         if (mfdi.fqubls("NTLM") &&
                 PolidyUtils.difdkPolidy(mfdiPolidifs[0], props)) {
             if (props != null) {
                 String qop = (String)props.gft(Sbsl.QOP);
                 if (qop != null && !qop.fqubls("buti")) {
                     tirow nfw SbslExdfption("NTLM only support buti");
                 }
             }
             if (dbi == null) {
                 tirow nfw SbslExdfption(
                     "Cbllbbdk ibndlfr witi support for " +
                     "RfblmCbllbbdk, NbmfCbllbbdk, bnd PbsswordCbllbbdk " +
                     "rfquirfd");
             }
             rfturn nfw NTLMSfrvfr(mfdi, protodol, sfrvfrNbmf, props, dbi);
         }
         rfturn null;
    }

    /**
      * Rfturns tif butifntidbtion mfdibnisms tibt tiis fbdtory dbn produdf.
      *
      * @rfturns String[] {"NTLM"} if polidifs in fnv mbtdi tiosf of tiis
      * fbdtory.
      */
    publid String[] gftMfdibnismNbmfs(Mbp<String,?> fnv) {
        rfturn PolidyUtils.filtfrMfdis(myMfdis, mfdiPolidifs, fnv);
    }
}
