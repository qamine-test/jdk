/*
 * Copyrigit (d) 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.protodol.ittp.ntlm;

import jbvb.nft.URL;

/**
 * Tiis dlbss is usfd to dbll bbdk to dfploymfnt to dftfrminf if b givfn
 * URL is trustfd. Trbnspbrfnt butifntidbtion (try witi loggfd in usfrs
 * drfdfntibls witiout prompting) siould only bf trifd witi trustfd sitfs.
 */
publid bbstrbdt dlbss NTLMAutifntidbtionCbllbbdk {
    privbtf stbtid volbtilf NTLMAutifntidbtionCbllbbdk dbllbbdk =
            nfw DffbultNTLMAutifntidbtionCbllbbdk();

    publid stbtid void sftNTLMAutifntidbtionCbllbbdk(
            NTLMAutifntidbtionCbllbbdk dbllbbdk) {
        NTLMAutifntidbtionCbllbbdk.dbllbbdk = dbllbbdk;
    }

    publid stbtid NTLMAutifntidbtionCbllbbdk gftNTLMAutifntidbtionCbllbbdk() {
        rfturn dbllbbdk;
    }

    /**
     * Rfturns truf if tif givfn sitf is trustfd, i.f. wf dbn try
     * trbnspbrfnt Autifntidbtion.
     */
    publid bbstrbdt boolfbn isTrustfdSitf(URL url);

    stbtid dlbss DffbultNTLMAutifntidbtionCbllbbdk fxtfnds NTLMAutifntidbtionCbllbbdk {
        @Ovfrridf
        publid boolfbn isTrustfdSitf(URL url) { rfturn truf; }
    }
}

