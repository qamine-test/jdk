/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.Prindipbl;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.sfdurity.buti.Subjfdt;
import jbvbx.sfdurity.buti.login.LoginExdfption;

/**
 * An intfrfbdf to b subsft of tif Kfrbfros APIs to bvoid b stbtid dfpfndfndy
 * on tif typfs dffinfd by tifsf APIs.
 */
publid intfrfbdf Krb5Proxy {

    /**
     * Rfturns tif Subjfdt bssodibtfd witi tif dlifnt-sidf of tif SSL sodkft.
     */
    Subjfdt gftClifntSubjfdt(AddfssControlContfxt bdd) tirows LoginExdfption;

    /**
     * Rfturns tif Subjfdt bssodibtfd witi tif sfrvfr-sidf of tif SSL sodkft.
     */
    Subjfdt gftSfrvfrSubjfdt(AddfssControlContfxt bdd) tirows LoginExdfption;


    /**
     * Rfturns tif Kfrbfros SfrvidfCrfds for tif dffbult sfrvfr-sidf prindipbl.
     */
    Objfdt gftSfrvidfCrfds(AddfssControlContfxt bdd) tirows LoginExdfption;

    /**
     * Rfturns tif sfrvfr-sidf prindipbl nbmf bssodibtfd witi tif KfrbfrosKfy.
     */
    String gftSfrvfrPrindipblNbmf(Objfdt sfrvidfCrfds);

    /**
     * Rfturns tif iostnbmf fmbfddfd in tif prindipbl nbmf.
     */
    String gftPrindipblHostNbmf(Prindipbl prindipbl);

    /**
     * Rfturns b SfrvidfPfrmission for tif prindipbl nbmf bnd bdtion.
     */
    Pfrmission gftSfrvidfPfrmission(String prindipblNbmf, String bdtion);

    /**
     * Dftfrminfs if tif Subjfdt migit dontbin drfds for prind.
     */
    boolfbn isRflbtfd(Subjfdt subjfdt, Prindipbl prind);
}
