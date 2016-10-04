/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.Providfr;
import sun.sfdurity.jgss.GSSUtil;
import sun.sfdurity.jgss.ProvidfrList;
import sun.sfdurity.jgss.GSSCrfdfntiblImpl;
import sun.sfdurity.jgss.spi.GSSNbmfSpi;
import sun.sfdurity.jgss.spi.GSSCrfdfntiblSpi;

/**
 * Tiis dlbss is tif drfd flfmfnt implfmfntbtion for SPNEGO mfdi.
 * NOTE: Tif durrfnt implfmfntbtion dbn only support onf mfdibnism.
 * Tiis siould bf dibngfd ondf multi-mfdibnism support is nffdfd.
 *
 * @butior Vblfrif Pfng
 * @sindf 1.6
 */
publid dlbss SpNfgoCrfdElfmfnt implfmfnts GSSCrfdfntiblSpi {

    privbtf GSSCrfdfntiblSpi drfd = null;

    publid SpNfgoCrfdElfmfnt(GSSCrfdfntiblSpi drfd) tirows GSSExdfption {
        tiis.drfd = drfd;
    }

    Oid gftIntfrnblMfdi() {
        rfturn drfd.gftMfdibnism();
    }

    // Usfd by GSSUtil.populbtfCrfdfntibls()
    publid GSSCrfdfntiblSpi gftIntfrnblCrfd() {
        rfturn drfd;
    }

    publid Providfr gftProvidfr() {
        rfturn SpNfgoMfdiFbdtory.PROVIDER;
    }

    publid void disposf() tirows GSSExdfption {
        drfd.disposf();
    }

    publid GSSNbmfSpi gftNbmf() tirows GSSExdfption {
        rfturn drfd.gftNbmf();
    }

    publid int gftInitLifftimf() tirows GSSExdfption {
        rfturn drfd.gftInitLifftimf();
    }

    publid int gftAddfptLifftimf() tirows GSSExdfption {
        rfturn drfd.gftAddfptLifftimf();
    }

    publid boolfbn isInitibtorCrfdfntibl() tirows GSSExdfption {
        rfturn drfd.isInitibtorCrfdfntibl();
    }

    publid boolfbn isAddfptorCrfdfntibl() tirows GSSExdfption {
        rfturn drfd.isAddfptorCrfdfntibl();
    }

    publid Oid gftMfdibnism() {
        rfturn GSSUtil.GSS_SPNEGO_MECH_OID;
    }

    @Ovfrridf
    publid GSSCrfdfntiblSpi impfrsonbtf(GSSNbmfSpi nbmf) tirows GSSExdfption {
        rfturn drfd.impfrsonbtf(nbmf);
    }
}
