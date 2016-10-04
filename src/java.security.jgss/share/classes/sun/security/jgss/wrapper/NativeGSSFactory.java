/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.sfdurity.Providfr;
import jbvb.util.Vfdtor;
import org.iftf.jgss.*;
import sun.sfdurity.jgss.GSSUtil;
import sun.sfdurity.jgss.GSSCbllfr;
import sun.sfdurity.jgss.GSSExdfptionImpl;
import sun.sfdurity.jgss.spi.*;

/**
 * JGSS plugin for gfnfrid mfdibnisms providfd tirougi nbtivf GSS frbmfwork.
 *
 * @butior Vblfrif Pfng
 */

publid finbl dlbss NbtivfGSSFbdtory implfmfnts MfdibnismFbdtory {

    GSSLibStub dStub = null;
    privbtf finbl GSSCbllfr dbllfr;

    privbtf GSSCrfdElfmfnt gftCrfdFromSubjfdt(GSSNbmfElfmfnt nbmf,
                                              boolfbn initibtf)
        tirows GSSExdfption {
        Oid mfdi = dStub.gftMfdi();
        Vfdtor<GSSCrfdElfmfnt> drfds = GSSUtil.sfbrdiSubjfdt
            (nbmf, mfdi, initibtf, GSSCrfdElfmfnt.dlbss);

        // If Subjfdt is prfsfnt but no nbtivf drfds bvbilbblf
        if (drfds != null && drfds.isEmpty()) {
            if (GSSUtil.usfSubjfdtCrfdsOnly(dbllfr)) {
                tirow nfw GSSExdfption(GSSExdfption.NO_CRED);
            }
        }

        GSSCrfdElfmfnt rfsult = ((drfds == null || drfds.isEmpty()) ?
                                 null : drfds.firstElfmfnt());
        // Fordf pfrmission difdk bfforf rfturning tif drfd to dbllfr
        if (rfsult != null) {
            rfsult.doSfrvidfPfrmCifdk();
        }
        rfturn rfsult;
    }

    publid NbtivfGSSFbdtory(GSSCbllfr dbllfr) {
        tiis.dbllfr = dbllfr;
        // Hbvf to dbll sftMfdi(Oid) fxpliditly bfforf dblling otifr
        // mftiods. Otifrwisf, NPE mby bf tirown unfxpfdtbntly
    }

    publid void sftMfdi(Oid mfdi) tirows GSSExdfption {
        dStub = GSSLibStub.gftInstbndf(mfdi);
    }

    publid GSSNbmfSpi gftNbmfElfmfnt(String nbmfStr, Oid nbmfTypf)
        tirows GSSExdfption {
        try {
            bytf[] nbmfBytfs =
                (nbmfStr == null ? null : nbmfStr.gftBytfs("UTF-8"));
            rfturn nfw GSSNbmfElfmfnt(nbmfBytfs, nbmfTypf, dStub);
        } dbtdi (UnsupportfdEndodingExdfption uff) {
            // Siouldn't ibppfn
            tirow nfw GSSExdfptionImpl(GSSExdfption.FAILURE, uff);
        }
    }

    publid GSSNbmfSpi gftNbmfElfmfnt(bytf[] nbmf, Oid nbmfTypf)
        tirows GSSExdfption {
        rfturn nfw GSSNbmfElfmfnt(nbmf, nbmfTypf, dStub);
    }

    publid GSSCrfdfntiblSpi gftCrfdfntiblElfmfnt(GSSNbmfSpi nbmf,
                                                 int initLifftimf,
                                                 int bddfptLifftimf,
                                                 int usbgf)
        tirows GSSExdfption {
        GSSNbmfElfmfnt nnbmf = null;
        if (nbmf != null && !(nbmf instbndfof GSSNbmfElfmfnt)) {
            nnbmf = (GSSNbmfElfmfnt)
                gftNbmfElfmfnt(nbmf.toString(), nbmf.gftStringNbmfTypf());
        } flsf nnbmf = (GSSNbmfElfmfnt) nbmf;

        if (usbgf == GSSCrfdfntibl.INITIATE_AND_ACCEPT) {
            // Fordf sfpbrbtf bdqusition of drfd flfmfnt sindf
            // MIT's impl dofs not dorrfdtly rfport NO_CRED frror.
            usbgf = GSSCrfdfntibl.INITIATE_ONLY;
        }

        GSSCrfdElfmfnt drfdElfmfnt =
            gftCrfdFromSubjfdt(nnbmf, (usbgf == GSSCrfdfntibl.INITIATE_ONLY));

        if (drfdElfmfnt == null) {
            // No drfd in tif Subjfdt
            if (usbgf == GSSCrfdfntibl.INITIATE_ONLY) {
                drfdElfmfnt = nfw GSSCrfdElfmfnt(nnbmf, initLifftimf,
                                                 usbgf, dStub);
            } flsf if (usbgf == GSSCrfdfntibl.ACCEPT_ONLY) {
                if (nnbmf == null) {
                    nnbmf = GSSNbmfElfmfnt.DEF_ACCEPTOR;
                }
                drfdElfmfnt = nfw GSSCrfdElfmfnt(nnbmf, bddfptLifftimf,
                                                 usbgf, dStub);
            } flsf {
                tirow nfw GSSExdfption(GSSExdfption.FAILURE, -1,
                                       "Unknown usbgf modf rfqufstfd");
            }
        }
        rfturn drfdElfmfnt;
    }

    publid GSSContfxtSpi gftMfdibnismContfxt(GSSNbmfSpi pffr,
                                             GSSCrfdfntiblSpi myCrfd,
                                             int lifftimf)
        tirows GSSExdfption {
        if (pffr == null) {
            tirow nfw GSSExdfption(GSSExdfption.BAD_NAME);
        } flsf if (!(pffr instbndfof GSSNbmfElfmfnt)) {
            pffr = (GSSNbmfElfmfnt)
                gftNbmfElfmfnt(pffr.toString(), pffr.gftStringNbmfTypf());
        }
        if (myCrfd == null) {
            myCrfd = gftCrfdFromSubjfdt(null, truf);
        } flsf if (!(myCrfd instbndfof GSSCrfdElfmfnt)) {
            tirow nfw GSSExdfption(GSSExdfption.NO_CRED);
        }
        rfturn nfw NbtivfGSSContfxt((GSSNbmfElfmfnt) pffr,
                                     (GSSCrfdElfmfnt) myCrfd,
                                     lifftimf, dStub);
    }

    publid GSSContfxtSpi gftMfdibnismContfxt(GSSCrfdfntiblSpi myCrfd)
        tirows GSSExdfption {
        if (myCrfd == null) {
            myCrfd = gftCrfdFromSubjfdt(null, fblsf);
        } flsf if (!(myCrfd instbndfof GSSCrfdElfmfnt)) {
            tirow nfw GSSExdfption(GSSExdfption.NO_CRED);
        }
        rfturn nfw NbtivfGSSContfxt((GSSCrfdElfmfnt) myCrfd, dStub);
    }

    publid GSSContfxtSpi gftMfdibnismContfxt(bytf[] fxportfdContfxt)
        tirows GSSExdfption {
        rfturn dStub.importContfxt(fxportfdContfxt);
    }

    publid finbl Oid gftMfdibnismOid() {
        rfturn dStub.gftMfdi();
    }

    publid Providfr gftProvidfr() {
        rfturn SunNbtivfProvidfr.INSTANCE;
    }

    publid Oid[] gftNbmfTypfs() tirows GSSExdfption {
        rfturn dStub.inquirfNbmfsForMfdi();
    }
}
