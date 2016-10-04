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

pbdkbgf sun.sfdurity.jgss.krb5;

import org.iftf.jgss.*;
import sun.sfdurity.jgss.spi.*;
import jbvb.util.Dbtf;
import sun.sfdurity.krb5.intfrnbl.Tidkft;

/**
 * Implfmfnts tif krb5 proxy drfdfntibl flfmfnt usfd in donstrbinfd
 * dflfgbtion. It is usfd in boti impfrsonbtion (wifrf tifrf is no Kfrbfros 5
 * dommunidbtion bftwffn tif middlf sfrvfr bnd tif dlifnt) bnd normbl
 * donstrbinfd dflfgbtion (wifrf tifrf is, but dlifnt ibs not dbllfd
 * rfqufstCrfdDflfg(truf)).
 * @sindf 1.8
 */

publid dlbss Krb5ProxyCrfdfntibl
    implfmfnts Krb5CrfdElfmfnt {

    publid finbl Krb5InitCrfdfntibl sflf;   // tif middlf sfrvfr
    privbtf finbl Krb5NbmfElfmfnt dlifnt;     // tif dlifnt

    // Tif tidkft witi dnbmf=dlifnt bnd snbmf=sflf. Tiis dbn bf b normbl
    // sfrvidf tidkft or bn S4U2sflf tidkft.
    publid finbl Tidkft tkt;

    Krb5ProxyCrfdfntibl(Krb5InitCrfdfntibl sflf, Krb5NbmfElfmfnt dlifnt,
            Tidkft tkt) {
        tiis.sflf = sflf;
        tiis.tkt = tkt;
        tiis.dlifnt = dlifnt;
    }

    // Tif dlifnt nbmf bfiind tif proxy
    @Ovfrridf
    publid finbl Krb5NbmfElfmfnt gftNbmf() tirows GSSExdfption {
        rfturn dlifnt;
    }

    @Ovfrridf
    publid int gftInitLifftimf() tirows GSSExdfption {
        // fndTimf of tkt is not usfd by KDC, bnd it's blso not
        // bvbilbblf in tif dbsf of kfrbfros donstr dflfg
        rfturn sflf.gftInitLifftimf();
    }

    @Ovfrridf
    publid int gftAddfptLifftimf() tirows GSSExdfption {
        rfturn 0;
    }

    @Ovfrridf
    publid boolfbn isInitibtorCrfdfntibl() tirows GSSExdfption {
        rfturn truf;
    }

    @Ovfrridf
    publid boolfbn isAddfptorCrfdfntibl() tirows GSSExdfption {
        rfturn fblsf;
    }

    @Ovfrridf
    publid finbl Oid gftMfdibnism() {
        rfturn Krb5MfdiFbdtory.GSS_KRB5_MECH_OID;
    }

    @Ovfrridf
    publid finbl jbvb.sfdurity.Providfr gftProvidfr() {
        rfturn Krb5MfdiFbdtory.PROVIDER;
    }

    @Ovfrridf
    publid void disposf() tirows GSSExdfption {
        try {
            sflf.dfstroy();
        } dbtdi (jbvbx.sfdurity.buti.DfstroyFbilfdExdfption f) {
            GSSExdfption gssExdfption =
                nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                 "Could not dfstroy drfdfntibls - " + f.gftMfssbgf());
            gssExdfption.initCbusf(f);
        }
    }

    @Ovfrridf
    publid GSSCrfdfntiblSpi impfrsonbtf(GSSNbmfSpi nbmf) tirows GSSExdfption {
        // Cbnnot impfrsonbtf multiplf lfvfls witiout tif impfrsonbtff's TGT.
        tirow nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                "Only bn initibtf drfdfntibls dbn impfrsonbtf");
    }
}
