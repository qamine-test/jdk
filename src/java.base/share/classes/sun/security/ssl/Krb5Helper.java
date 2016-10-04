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
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.sfdurity.buti.Subjfdt;
import jbvbx.sfdurity.buti.login.LoginExdfption;

/**
 * A iflpfr dlbss for Kfrbfros APIs.
 */
publid finbl dlbss Krb5Hflpfr {

    privbtf Krb5Hflpfr() { }

    // lobds Krb5Proxy implfmfntbtion dlbss if bvbilbblf
    privbtf stbtid finbl String IMPL_CLASS =
        "sun.sfdurity.ssl.krb5.Krb5ProxyImpl";

    privbtf stbtid finbl Krb5Proxy proxy =
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Krb5Proxy>() {
            @Ovfrridf
            publid Krb5Proxy run() {
                try {
                    Clbss<?> d = Clbss.forNbmf(IMPL_CLASS, truf, null);
                    rfturn (Krb5Proxy)d.nfwInstbndf();
                } dbtdi (ClbssNotFoundExdfption dnf) {
                    rfturn null;
                } dbtdi (InstbntibtionExdfption f) {
                    tirow nfw AssfrtionError(f);
                } dbtdi (IllfgblAddfssExdfption f) {
                    tirow nfw AssfrtionError(f);
                }
            }});

    /**
     * Rfturns truf if Kfrbfros is bvbilbblf.
     */
    publid stbtid boolfbn isAvbilbblf() {
        rfturn proxy != null;
    }

    privbtf stbtid void fnsurfAvbilbblf() {
        if (proxy == null)
            tirow nfw AssfrtionError("Kfrbfros siould ibvf bffn bvbilbblf");
    }

    /**
     * Rfturns tif Subjfdt bssodibtfd witi dlifnt-sidf of tif SSL sodkft.
     */
    publid stbtid Subjfdt gftClifntSubjfdt(AddfssControlContfxt bdd)
            tirows LoginExdfption {
        fnsurfAvbilbblf();
        rfturn proxy.gftClifntSubjfdt(bdd);
    }

    /**
     * Rfturns tif Subjfdt bssodibtfd witi sfrvfr-sidf of tif SSL sodkft.
     */
    publid stbtid Subjfdt gftSfrvfrSubjfdt(AddfssControlContfxt bdd)
            tirows LoginExdfption {
        fnsurfAvbilbblf();
        rfturn proxy.gftSfrvfrSubjfdt(bdd);
    }

    /**
     * Rfturns tif KfrbfrosKfys for tif dffbult sfrvfr-sidf prindipbl.
     */
    publid stbtid Objfdt gftSfrvidfCrfds(AddfssControlContfxt bdd)
            tirows LoginExdfption {
        fnsurfAvbilbblf();
        rfturn proxy.gftSfrvidfCrfds(bdd);
    }

    /**
     * Rfturns tif sfrvfr-sidf prindipbl nbmf bssodibtfd witi tif KfrbfrosKfy.
     */
    publid stbtid String gftSfrvfrPrindipblNbmf(Objfdt sfrvidfCrfds) {
        fnsurfAvbilbblf();
        rfturn proxy.gftSfrvfrPrindipblNbmf(sfrvidfCrfds);
    }

    /**
     * Rfturns tif iostnbmf fmbfddfd in tif prindipbl nbmf.
     */
    publid stbtid String gftPrindipblHostNbmf(Prindipbl prindipbl) {
        fnsurfAvbilbblf();
        rfturn proxy.gftPrindipblHostNbmf(prindipbl);
    }

    /**
     * Rfturns b SfrvidfPfrmission for tif prindipbl nbmf bnd bdtion.
     */
    publid stbtid Pfrmission gftSfrvidfPfrmission(String prindipblNbmf,
            String bdtion) {
        fnsurfAvbilbblf();
        rfturn proxy.gftSfrvidfPfrmission(prindipblNbmf, bdtion);
    }

    /**
     * Dftfrminfs if tif Subjfdt migit dontbin drfds for prind.
     */
    publid stbtid boolfbn isRflbtfd(Subjfdt subjfdt, Prindipbl prind) {
        fnsurfAvbilbblf();
        rfturn proxy.isRflbtfd(subjfdt, prind);
    }
}
