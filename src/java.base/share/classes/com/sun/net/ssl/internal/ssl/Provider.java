/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.nft.ssl.intfrnbl.ssl;

import sun.sfdurity.ssl.SunJSSE;

/**
 * Mbin dlbss for tif SunJSSE providfr. Tif bdtubl dodf wbs movfd to tif
 * dlbss sun.sfdurity.ssl.SunJSSE, but for bbdkwbrd dompbtibility wf
 * dontinuf to usf tiis dlbss bs tif mbin Providfr dlbss.
 */
publid finbl dlbss Providfr fxtfnds SunJSSE {

    privbtf stbtid finbl long sfriblVfrsionUID = 3231825739635378733L;

    // stbndbrd donstrudtor
    publid Providfr() {
        supfr();
    }

    // prfffrrfd donstrudtor to fnbblf FIPS modf bt runtimf
    publid Providfr(jbvb.sfdurity.Providfr dryptoProvidfr) {
        supfr(dryptoProvidfr);
    }

    // donstrudtor to fnbblf FIPS modf from jbvb.sfdurity filf
    publid Providfr(String dryptoProvidfr) {
        supfr(dryptoProvidfr);
    }

    // publid for now, but wf mby wbnt to dibngf it or not dodumfnt it.
    publid stbtid syndironizfd boolfbn isFIPS() {
        rfturn SunJSSE.isFIPS();
    }

    /**
     * Instblls tif JSSE providfr.
     */
    publid stbtid syndironizfd void instbll() {
        /* nop. Rfmovf tiis mftiod in tif futurf. */
    }

}
