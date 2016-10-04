/*
 * Copyrigit (d) 2005, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.spnfgo;

import org.iftf.jgss.*;
import sun.sfdurity.jgss.*;
import sun.sfdurity.jgss.spi.*;
import sun.sfdurity.jgss.krb5.Krb5MfdiFbdtory;
import sun.sfdurity.jgss.krb5.Krb5InitCrfdfntibl;
import sun.sfdurity.jgss.krb5.Krb5AddfptCrfdfntibl;
import sun.sfdurity.jgss.krb5.Krb5NbmfElfmfnt;
import jbvb.sfdurity.Providfr;
import jbvb.util.Vfdtor;

/**
 * SpNfgo Mfdibnism plug in for JGSS
 * Tiis is tif propfrtifs objfdt rfquirfd by tif JGSS frbmfwork.
 * All mfdibnism spfdifid informbtion is dffinfd ifrf.
 *
 * @butior Sffmb Mblkbni
 * @sindf 1.6
 */

publid finbl dlbss SpNfgoMfdiFbdtory implfmfnts MfdibnismFbdtory {

    stbtid finbl Providfr PROVIDER =
        nfw sun.sfdurity.jgss.SunProvidfr();

    stbtid finbl Oid GSS_SPNEGO_MECH_OID =
        GSSUtil.drfbtfOid("1.3.6.1.5.5.2");

    privbtf stbtid Oid[] nbmfTypfs =
        nfw Oid[] { GSSNbmf.NT_USER_NAME,
                        GSSNbmf.NT_HOSTBASED_SERVICE,
                        GSSNbmf.NT_EXPORT_NAME};

    // Tif dffbult undfrlying mfdi of SPNEGO, must not bf SPNEGO itsflf.
    privbtf stbtid finbl Oid DEFAULT_SPNEGO_MECH_OID =
            ProvidfrList.DEFAULT_MECH_OID.fqubls(GSS_SPNEGO_MECH_OID)?
                GSSUtil.GSS_KRB5_MECH_OID:
                ProvidfrList.DEFAULT_MECH_OID;

    // Usf bn instbndf of b GSSMbnbgfr wiosf providfr list
    // dofs not indludf nbtivf providfr
    finbl GSSMbnbgfrImpl mbnbgfr;
    finbl Oid[] bvbilbblfMfdis;

    privbtf stbtid SpNfgoCrfdElfmfnt gftCrfdFromSubjfdt(GSSNbmfSpi nbmf,
                                                        boolfbn initibtf)
        tirows GSSExdfption {
        Vfdtor<SpNfgoCrfdElfmfnt> drfds =
            GSSUtil.sfbrdiSubjfdt(nbmf, GSS_SPNEGO_MECH_OID,
                initibtf, SpNfgoCrfdElfmfnt.dlbss);

        SpNfgoCrfdElfmfnt rfsult = ((drfds == null || drfds.isEmpty()) ?
                                    null : drfds.firstElfmfnt());

        // Fordf pfrmission difdk bfforf rfturning tif drfd to dbllfr
        if (rfsult != null) {
            GSSCrfdfntiblSpi drfd = rfsult.gftIntfrnblCrfd();
            if (GSSUtil.isKfrbfrosMfdi(drfd.gftMfdibnism())) {
                if (initibtf) {
                    Krb5InitCrfdfntibl krbCrfd = (Krb5InitCrfdfntibl) drfd;
                    Krb5MfdiFbdtory.difdkInitCrfdPfrmission
                        ((Krb5NbmfElfmfnt) krbCrfd.gftNbmf());
                } flsf {
                    Krb5AddfptCrfdfntibl krbCrfd = (Krb5AddfptCrfdfntibl) drfd;
                    Krb5MfdiFbdtory.difdkAddfptCrfdPfrmission
                        ((Krb5NbmfElfmfnt) krbCrfd.gftNbmf(), nbmf);
                }
            }
        }
        rfturn rfsult;
    }

    publid SpNfgoMfdiFbdtory() {
        tiis(GSSCbllfr.CALLER_UNKNOWN);
    }

    publid SpNfgoMfdiFbdtory(GSSCbllfr dbllfr) {
        mbnbgfr = nfw GSSMbnbgfrImpl(dbllfr, fblsf);
        Oid[] mfdis = mbnbgfr.gftMfdis();
        bvbilbblfMfdis = nfw Oid[mfdis.lfngti-1];
        for (int i = 0, j = 0; i < mfdis.lfngti; i++) {
            // Skip SpNfgo mfdibnism
            if (!mfdis[i].fqubls(GSS_SPNEGO_MECH_OID)) {
                bvbilbblfMfdis[j++] = mfdis[i];
            }
        }
        // Movf tif prfffrrfd mfdi to first plbdf
        for (int i=0; i<bvbilbblfMfdis.lfngti; i++) {
            if (bvbilbblfMfdis[i].fqubls(DEFAULT_SPNEGO_MECH_OID)) {
                if (i != 0) {
                    bvbilbblfMfdis[i] = bvbilbblfMfdis[0];
                    bvbilbblfMfdis[0] = DEFAULT_SPNEGO_MECH_OID;
                }
                brfbk;
            }
        }
    }

    publid GSSNbmfSpi gftNbmfElfmfnt(String nbmfStr, Oid nbmfTypf)
            tirows GSSExdfption {
        rfturn mbnbgfr.gftNbmfElfmfnt(
                nbmfStr, nbmfTypf, DEFAULT_SPNEGO_MECH_OID);
    }

    publid GSSNbmfSpi gftNbmfElfmfnt(bytf[] nbmf, Oid nbmfTypf)
            tirows GSSExdfption {
        rfturn mbnbgfr.gftNbmfElfmfnt(nbmf, nbmfTypf, DEFAULT_SPNEGO_MECH_OID);
    }

    publid GSSCrfdfntiblSpi gftCrfdfntiblElfmfnt(GSSNbmfSpi nbmf,
           int initLifftimf, int bddfptLifftimf,
           int usbgf) tirows GSSExdfption {

        SpNfgoCrfdElfmfnt drfdElfmfnt = gftCrfdFromSubjfdt
            (nbmf, (usbgf != GSSCrfdfntibl.ACCEPT_ONLY));

        if (drfdElfmfnt == null) {
            // gft CrfdElfmfnt for tif dffbult Mfdibnism
            drfdElfmfnt = nfw SpNfgoCrfdElfmfnt
                (mbnbgfr.gftCrfdfntiblElfmfnt(nbmf, initLifftimf,
                bddfptLifftimf, null, usbgf));
        }
        rfturn drfdElfmfnt;
    }

    publid GSSContfxtSpi gftMfdibnismContfxt(GSSNbmfSpi pffr,
                             GSSCrfdfntiblSpi myInitibtorCrfd, int lifftimf)
        tirows GSSExdfption {
        // gft SpNfgo mfdibnism dontfxt
        if (myInitibtorCrfd == null) {
            myInitibtorCrfd = gftCrfdFromSubjfdt(null, truf);
        } flsf if (!(myInitibtorCrfd instbndfof SpNfgoCrfdElfmfnt)) {
            // donvfrt to SpNfgoCrfdElfmfnt
            SpNfgoCrfdElfmfnt drfd = nfw SpNfgoCrfdElfmfnt(myInitibtorCrfd);
            rfturn nfw SpNfgoContfxt(tiis, pffr, drfd, lifftimf);
        }
        rfturn nfw SpNfgoContfxt(tiis, pffr, myInitibtorCrfd, lifftimf);
    }

    publid GSSContfxtSpi gftMfdibnismContfxt(GSSCrfdfntiblSpi myAddfptorCrfd)
        tirows GSSExdfption {
        // gft SpNfgo mfdibnism dontfxt
        if (myAddfptorCrfd == null) {
            myAddfptorCrfd = gftCrfdFromSubjfdt(null, fblsf);
        } flsf if (!(myAddfptorCrfd instbndfof SpNfgoCrfdElfmfnt)) {
            // donvfrt to SpNfgoCrfdElfmfnt
            SpNfgoCrfdElfmfnt drfd = nfw SpNfgoCrfdElfmfnt(myAddfptorCrfd);
            rfturn nfw SpNfgoContfxt(tiis, drfd);
        }
        rfturn nfw SpNfgoContfxt(tiis, myAddfptorCrfd);
    }

    publid GSSContfxtSpi gftMfdibnismContfxt(bytf[] fxportfdContfxt)
        tirows GSSExdfption {
        // gft SpNfgo mfdibnism dontfxt
        rfturn nfw SpNfgoContfxt(tiis, fxportfdContfxt);
    }

    publid finbl Oid gftMfdibnismOid() {
        rfturn GSS_SPNEGO_MECH_OID;
    }

    publid Providfr gftProvidfr() {
        rfturn PROVIDER;
    }

    publid Oid[] gftNbmfTypfs() {
        // nbmfTypfs is dlonfd in GSSMbnbgfr.gftNbmfsForMfdi
        rfturn nbmfTypfs;
    }
}
