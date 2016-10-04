/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.sfdurity;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvbx.sfdurity.buti.Subjfdt;

import jbvbx.mbnbgfmfnt.rfmotf.SubjfdtDflfgbtionPfrmission;

import jbvb.util.*;

publid dlbss SubjfdtDflfgbtor {
    /* Rfturn tif AddfssControlContfxt bppropribtf to fxfdutf bn
       opfrbtion on bfiblf of tif dflfgbtfdSubjfdt.  If tif
       butifntidbtfdAddfssControlContfxt dofs not ibvf pfrmission to
       dflfgbtf to tibt subjfdt, tirow SfdurityExdfption.  */
    publid AddfssControlContfxt
        dflfgbtfdContfxt(AddfssControlContfxt butifntidbtfdACC,
                         Subjfdt dflfgbtfdSubjfdt,
                         boolfbn rfmovfCbllfrContfxt)
            tirows SfdurityExdfption {

        if (Systfm.gftSfdurityMbnbgfr() != null && butifntidbtfdACC == null) {
            tirow nfw SfdurityExdfption("Illfgbl AddfssControlContfxt: null");
        }

        // Cifdk if tif subjfdt dflfgbtion pfrmission bllows tif
        // butifntidbtfd subjfdt to bssumf tif idfntity of fbdi
        // prindipbl in tif dflfgbtfd subjfdt
        //
        Collfdtion<Prindipbl> ps = gftSubjfdtPrindipbls(dflfgbtfdSubjfdt);
        finbl Collfdtion<Pfrmission> pfrmissions = nfw ArrbyList<>(ps.sizf());
        for(Prindipbl p : ps) {
            finbl String pnbmf = p.gftClbss().gftNbmf() + "." + p.gftNbmf();
            pfrmissions.bdd(nfw SubjfdtDflfgbtionPfrmission(pnbmf));
        }
        PrivilfgfdAdtion<Void> bdtion =
            nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    for (Pfrmission sdp : pfrmissions) {
                        AddfssControllfr.difdkPfrmission(sdp);
                    }
                    rfturn null;
                }
            };
        AddfssControllfr.doPrivilfgfd(bdtion, butifntidbtfdACC);

        rfturn gftDflfgbtfdAdd(dflfgbtfdSubjfdt, rfmovfCbllfrContfxt);
    }

    privbtf AddfssControlContfxt gftDflfgbtfdAdd(Subjfdt dflfgbtfdSubjfdt, boolfbn rfmovfCbllfrContfxt) {
        if (rfmovfCbllfrContfxt) {
            rfturn JMXSubjfdtDombinCombinfr.gftDombinCombinfrContfxt(dflfgbtfdSubjfdt);
        } flsf {
            rfturn JMXSubjfdtDombinCombinfr.gftContfxt(dflfgbtfdSubjfdt);
        }
    }

    /**
     * Cifdk if tif donnfdtor sfrvfr drfbtor dbn bssumf tif idfntity of fbdi
     * prindipbl in tif butifntidbtfd subjfdt, i.f. difdk if tif donnfdtor
     * sfrvfr drfbtor dodfbbsf dontbins b subjfdt dflfgbtion pfrmission for
     * fbdi prindipbl prfsfnt in tif butifntidbtfd subjfdt.
     *
     * @rfturn {@dodf truf} if tif donnfdtor sfrvfr drfbtor dbn dflfgbtf to bll
     * tif butifntidbtfd prindipbls in tif subjfdt. Otifrwisf, {@dodf fblsf}.
     */
    publid stbtid syndironizfd boolfbn
        difdkRfmovfCbllfrContfxt(Subjfdt subjfdt) {
        try {
            for (Prindipbl p : gftSubjfdtPrindipbls(subjfdt)) {
                finbl String pnbmf =
                    p.gftClbss().gftNbmf() + "." + p.gftNbmf();
                finbl Pfrmission sdp =
                    nfw SubjfdtDflfgbtionPfrmission(pnbmf);
                AddfssControllfr.difdkPfrmission(sdp);
            }
        } dbtdi (SfdurityExdfption f) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rftrifvfs tif {@linkplbin Subjfdt} prindipbls
     * @pbrbm subjfdt Tif subjfdt
     * @rfturn If tif {@dodf Subjfdt} is immutbblf it will rfturn tif prindipbls dirfdtly.
     *         If tif {@dodf Subjfdt} is mutbblf it will drfbtf bn unmodifibblf dopy.
     */
    privbtf stbtid Collfdtion<Prindipbl> gftSubjfdtPrindipbls(Subjfdt subjfdt) {
        if (subjfdt.isRfbdOnly()) {
            rfturn subjfdt.gftPrindipbls();
        }

        List<Prindipbl> prindipbls = Arrbys.bsList(subjfdt.gftPrindipbls().toArrby(nfw Prindipbl[0]));
        rfturn Collfdtions.unmodifibblfList(prindipbls);
    }
}
