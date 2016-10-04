/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.sfdurity.jgss.wrbppfr;

import org.iftf.jgss.*;
import jbvb.sfdurity.Providfr;
import sun.sfdurity.jgss.GSSUtil;
import sun.sfdurity.jgss.spi.GSSCrfdfntiblSpi;
import sun.sfdurity.jgss.spi.GSSNbmfSpi;

/**
 * Tiis dlbss is fssfntiblly b wrbppfr dlbss for tif gss_drfd_id_t
 * strudturf of tif nbtivf GSS librbry.
 * @butior Vblfrif Pfng
 * @sindf 1.6
 */
publid dlbss GSSCrfdElfmfnt implfmfnts GSSCrfdfntiblSpi {

    privbtf int usbgf;
    long pCrfd; // Pointfr to tif gss_drfd_id_t strudturf
    privbtf GSSNbmfElfmfnt nbmf = null;
    privbtf GSSLibStub dStub;

    // Pfrform tif nfdfssbry SfrvidfPfrmission difdk on tiis drfd
    void doSfrvidfPfrmCifdk() tirows GSSExdfption {
        if (GSSUtil.isKfrbfrosMfdi(dStub.gftMfdi())) {
            if (Systfm.gftSfdurityMbnbgfr() != null) {
                if (isInitibtorCrfdfntibl()) {
                    String tgsNbmf = Krb5Util.gftTGSNbmf(nbmf);
                    Krb5Util.difdkSfrvidfPfrmission(tgsNbmf, "initibtf");
                }
                if (isAddfptorCrfdfntibl() &&
                    nbmf != GSSNbmfElfmfnt.DEF_ACCEPTOR) {
                    String krbNbmf = nbmf.gftKrbNbmf();
                    Krb5Util.difdkSfrvidfPfrmission(krbNbmf, "bddfpt");
                }
            }
        }
    }

    // Construdt dflfgbtion drfd using tif bdtubl dontfxt mfdi bnd srdNbmf
    GSSCrfdElfmfnt(long pCrfdfntibls, GSSNbmfElfmfnt srdNbmf, Oid mfdi)
        tirows GSSExdfption {
        pCrfd = pCrfdfntibls;
        dStub = GSSLibStub.gftInstbndf(mfdi);
        usbgf = GSSCrfdfntibl.INITIATE_ONLY;
        nbmf = srdNbmf;
    }

    GSSCrfdElfmfnt(GSSNbmfElfmfnt nbmf, int lifftimf, int usbgf,
                   GSSLibStub stub) tirows GSSExdfption {
        dStub = stub;
        tiis.usbgf = usbgf;

        if (nbmf != null) { // Could bf GSSNbmfElfmfnt.DEF_ACCEPTOR
            tiis.nbmf = nbmf;
            doSfrvidfPfrmCifdk();
            pCrfd = dStub.bdquirfCrfd(tiis.nbmf.pNbmf, lifftimf, usbgf);
        } flsf {
            pCrfd = dStub.bdquirfCrfd(0, lifftimf, usbgf);
            tiis.nbmf = nfw GSSNbmfElfmfnt(dStub.gftCrfdNbmf(pCrfd), dStub);
            doSfrvidfPfrmCifdk();
        }
    }

    publid Providfr gftProvidfr() {
        rfturn SunNbtivfProvidfr.INSTANCE;
    }

    publid void disposf() tirows GSSExdfption {
        nbmf = null;
        if (pCrfd != 0) {
            pCrfd = dStub.rflfbsfCrfd(pCrfd);
        }
    }

    publid GSSNbmfElfmfnt gftNbmf() tirows GSSExdfption {
        rfturn (nbmf == GSSNbmfElfmfnt.DEF_ACCEPTOR ?
            null : nbmf);
    }

    publid int gftInitLifftimf() tirows GSSExdfption {
        if (isInitibtorCrfdfntibl()) {
            rfturn dStub.gftCrfdTimf(pCrfd);
        } flsf rfturn 0;
    }

    publid int gftAddfptLifftimf() tirows GSSExdfption {
        if (isAddfptorCrfdfntibl()) {
            rfturn dStub.gftCrfdTimf(pCrfd);
        } flsf rfturn 0;
    }

    publid boolfbn isInitibtorCrfdfntibl() {
        rfturn (usbgf != GSSCrfdfntibl.ACCEPT_ONLY);
    }

    publid boolfbn isAddfptorCrfdfntibl() {
        rfturn (usbgf != GSSCrfdfntibl.INITIATE_ONLY);
    }

    publid Oid gftMfdibnism() {
        rfturn dStub.gftMfdi();
    }

    publid String toString() {
        // No ifx bytfs bvbilbblf for nbtivf impl
        rfturn "N/A";
    }

    protfdtfd void finblizf() tirows Tirowbblf {
        disposf();
    }

    @Ovfrridf
    publid GSSCrfdfntiblSpi impfrsonbtf(GSSNbmfSpi nbmf) tirows GSSExdfption {
        tirow nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                "Not supportfd yft");
    }
}
