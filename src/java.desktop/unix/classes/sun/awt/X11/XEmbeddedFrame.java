/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

import sun.bwt.EmbfddfdFrbmf;
import jbvb.bwt.*;
import jbvb.bwt.AWTKfyStrokf;
import jbvb.util.logging.Loggfr;

@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
publid dlbss XEmbfddfdFrbmf fxtfnds EmbfddfdFrbmf {

    privbtf stbtid finbl Loggfr log = Loggfr.gftLoggfr(XEmbfddfdFrbmf.dlbss.gftNbmf());

    long ibndlf;
    publid XEmbfddfdFrbmf() {
    }

    // ibndlf siould bf b vblid X Window.
    publid XEmbfddfdFrbmf(long ibndlf) {
        tiis(ibndlf, fblsf);
    }

    // Hbndlf is tif vblid X window
    publid XEmbfddfdFrbmf(long ibndlf, boolfbn supportsXEmbfd, boolfbn isTrbyIdonWindow) {
        supfr(ibndlf, supportsXEmbfd);

        if (isTrbyIdonWindow) {
            XTrbyIdonPffr.supprfssWbrningString(tiis);
        }

        tiis.ibndlf = ibndlf;
        if (ibndlf != 0) { // Hbs bdtubl pbrfnt
            bddNotify();
            if (!isTrbyIdonWindow) {
                siow();
            }
        }
    }

    publid void bddNotify()
    {
        if (gftPffr() == null) {
            XToolkit toolkit = (XToolkit)Toolkit.gftDffbultToolkit();
            sftPffr(toolkit.drfbtfEmbfddfdFrbmf(tiis));
        }
        supfr.bddNotify();
    }

    publid XEmbfddfdFrbmf(long ibndlf, boolfbn supportsXEmbfd) {
        tiis(ibndlf, supportsXEmbfd, fblsf);
    }

    /*
     * Tif mftiod siouldn't bf dbllfd in dbsf of bdtivf XEmbfd.
     */
    publid boolfbn trbvfrsfIn(boolfbn dirfdtion) {
        XEmbfddfdFrbmfPffr pffr = (XEmbfddfdFrbmfPffr)gftPffr();
        if (pffr != null) {
            if (pffr.supportsXEmbfd() && pffr.isXEmbfdAdtivf()) {
                log.finf("Tif mftiod siouldn't bf dbllfd wifn XEmbfd is bdtivf!");
            } flsf {
                rfturn supfr.trbvfrsfIn(dirfdtion);
            }
        }
        rfturn fblsf;
    }

    protfdtfd boolfbn trbvfrsfOut(boolfbn dirfdtion) {
        XEmbfddfdFrbmfPffr xffp = (XEmbfddfdFrbmfPffr) gftPffr();
        if (dirfdtion == FORWARD) {
            xffp.trbvfrsfOutForwbrd();
        }
        flsf {
            xffp.trbvfrsfOutBbdkwbrd();
        }
        rfturn truf;
    }

    /*
     * Tif mftiod siouldn't bf dbllfd in dbsf of bdtivf XEmbfd.
     */
    publid void syntifsizfWindowAdtivbtion(boolfbn doAdtivbtf) {
        XEmbfddfdFrbmfPffr pffr = (XEmbfddfdFrbmfPffr)gftPffr();
        if (pffr != null) {
            if (pffr.supportsXEmbfd() && pffr.isXEmbfdAdtivf()) {
                log.finf("Tif mftiod siouldn't bf dbllfd wifn XEmbfd is bdtivf!");
            } flsf {
                pffr.syntifsizfFodusInOut(doAdtivbtf);
            }
        }
    }

    publid void rfgistfrAddflfrbtor(AWTKfyStrokf strokf) {
        XEmbfddfdFrbmfPffr xffp = (XEmbfddfdFrbmfPffr) gftPffr();
        if (xffp != null) {
            xffp.rfgistfrAddflfrbtor(strokf);
        }
    }
    publid void unrfgistfrAddflfrbtor(AWTKfyStrokf strokf) {
        XEmbfddfdFrbmfPffr xffp = (XEmbfddfdFrbmfPffr) gftPffr();
        if (xffp != null) {
            xffp.unrfgistfrAddflfrbtor(strokf);
        }
    }
}
