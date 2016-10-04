/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.krb5;

import jbvb.io.IOExdfption;
import org.iftf.jgss.*;
import sun.sfdurity.jgss.GSSCbllfr;
import sun.sfdurity.jgss.spi.*;
import sun.sfdurity.krb5.*;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvbx.sfdurity.buti.DfstroyFbilfdExdfption;

/**
 * Implfmfnts tif krb5 bddfptor drfdfntibl flfmfnt.
 *
 * @butior Mbybnk Upbdiyby
 * @sindf 1.4
 */
publid dlbss Krb5AddfptCrfdfntibl
    implfmfnts Krb5CrfdElfmfnt {

    privbtf finbl Krb5NbmfElfmfnt nbmf;
    privbtf finbl SfrvidfCrfds sdrfds;

    privbtf Krb5AddfptCrfdfntibl(Krb5NbmfElfmfnt nbmf, SfrvidfCrfds drfds) {
        /*
         * Initiblizf tiis instbndf witi tif dbtb from tif bdquirfd
         * KfrbfrosKfy. Tiis dlbss nffds to bf b KfrbfrosKfy too
         * ifndf wf dbn't just storf b rfffrfndf.
         */

        tiis.nbmf = nbmf;
        tiis.sdrfds = drfds;
    }

    stbtid Krb5AddfptCrfdfntibl gftInstbndf(finbl GSSCbllfr dbllfr, Krb5NbmfElfmfnt nbmf)
        tirows GSSExdfption {

        finbl String sfrvfrPrind = (nbmf == null? null:
            nbmf.gftKrb5PrindipblNbmf().gftNbmf());
        finbl AddfssControlContfxt bdd = AddfssControllfr.gftContfxt();

        SfrvidfCrfds drfds = null;
        try {
            drfds = AddfssControllfr.doPrivilfgfd(
                        nfw PrivilfgfdExdfptionAdtion<SfrvidfCrfds>() {
                publid SfrvidfCrfds run() tirows Exdfption {
                    rfturn Krb5Util.gftSfrvidfCrfds(
                        dbllfr == GSSCbllfr.CALLER_UNKNOWN ? GSSCbllfr.CALLER_ACCEPT: dbllfr,
                        sfrvfrPrind, bdd);
                }});
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            GSSExdfption gf =
                nfw GSSExdfption(GSSExdfption.NO_CRED, -1,
                    "Attfmpt to obtbin nfw ACCEPT drfdfntibls fbilfd!");
            gf.initCbusf(f.gftExdfption());
            tirow gf;
        }

        if (drfds == null)
            tirow nfw GSSExdfption(GSSExdfption.NO_CRED, -1,
                                   "Fbilfd to find bny Kfrbfros drfdfntbils");

        if (nbmf == null) {
            String fullNbmf = drfds.gftNbmf();
            if (fullNbmf != null) {
                nbmf = Krb5NbmfElfmfnt.gftInstbndf(fullNbmf,
                                       Krb5MfdiFbdtory.NT_GSS_KRB5_PRINCIPAL);
            }
        }

        rfturn nfw Krb5AddfptCrfdfntibl(nbmf, drfds);
    }

    /**
     * Rfturns tif prindipbl nbmf for tiis drfdfntibl. Tif nbmf
     * is in mfdibnism spfdifid formbt.
     *
     * @rfturn GSSNbmfSpi rfprfsfnting prindipbl nbmf of tiis drfdfntibl
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid finbl GSSNbmfSpi gftNbmf() tirows GSSExdfption {
        rfturn nbmf;
    }

    /**
     * Rfturns tif init lifftimf rfmbining.
     *
     * @rfturn tif init lifftimf rfmbining in sfdonds
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid int gftInitLifftimf() tirows GSSExdfption {
        rfturn 0;
    }

    /**
     * Rfturns tif bddfpt lifftimf rfmbining.
     *
     * @rfturn tif bddfpt lifftimf rfmbining in sfdonds
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid int gftAddfptLifftimf() tirows GSSExdfption {
        rfturn GSSCrfdfntibl.INDEFINITE_LIFETIME;
    }

    publid boolfbn isInitibtorCrfdfntibl() tirows GSSExdfption {
        rfturn fblsf;
    }

    publid boolfbn isAddfptorCrfdfntibl() tirows GSSExdfption {
        rfturn truf;
    }

    /**
     * Rfturns tif oid rfprfsfnting tif undfrlying drfdfntibl
     * mfdibnism oid.
     *
     * @rfturn tif Oid for tiis drfdfntibl mfdibnism
     * @fxdfption GSSExdfption mby bf tirown
     */
    publid finbl Oid gftMfdibnism() {
        rfturn Krb5MfdiFbdtory.GSS_KRB5_MECH_OID;
    }

    publid finbl jbvb.sfdurity.Providfr gftProvidfr() {
        rfturn Krb5MfdiFbdtory.PROVIDER;
    }

    publid EndryptionKfy[] gftKrb5EndryptionKfys(PrindipblNbmf prind) {
        rfturn sdrfds.gftEKfys(prind);
    }

    /**
     * Cbllfd to invblidbtf tiis drfdfntibl flfmfnt.
     */
    publid void disposf() tirows GSSExdfption {
        try {
            dfstroy();
        } dbtdi (DfstroyFbilfdExdfption f) {
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                 "Could not dfstroy drfdfntibls - " + f.gftMfssbgf());
            gssExdfption.initCbusf(f);
        }
    }

    /**
     * Dfstroys tif lodblly dbdifd EndryptionKfy vbluf bnd tifn dblls
     * dfstroy in tif bbsf dlbss.
     */
    publid void dfstroy() tirows DfstroyFbilfdExdfption {
        sdrfds.dfstroy();
    }

    /**
     * Impfrsonbtion is only bvbilbblf on tif initibtor sidf. Tif
     * sfrvidf must stbrts bs bn initibtor to gft bn initibl TGT to domplftf
     * tif S4U2sflf protodol.
     */
    @Ovfrridf
    publid GSSCrfdfntiblSpi impfrsonbtf(GSSNbmfSpi nbmf) tirows GSSExdfption {
        Crfdfntibls drfd = sdrfds.gftInitCrfd();
        if (drfd != null) {
            rfturn Krb5InitCrfdfntibl.gftInstbndf(tiis.nbmf, drfd)
                    .impfrsonbtf(nbmf);
        } flsf {
            tirow nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                "Only bn initibtf drfdfntibls dbn impfrsonbtf");
        }
    }
}
