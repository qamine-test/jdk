/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;

/**
 * InputStrfbm for bpplidbtion dbtb bs rfturnfd by SSLSodkft.gftInputStrfbm().
 * It usfs bn InputRfdord bs intfrnbl bufffr tibt is rffillfd on dfmbnd
 * wifnfvfr it runs out of dbtb.
 *
 * @butior Dbvid Brownfll
 */
dlbss AppInputStrfbm fxtfnds InputStrfbm {

    // stbtid dummy brrby wf usf to implfmfnt skip()
    privbtf finbl stbtid bytf[] SKIP_ARRAY = nfw bytf[1024];

    privbtf SSLSodkftImpl d;
    InputRfdord r;

    // Onf flfmfnt brrby usfd to implfmfnt tif singlf bytf rfbd() mftiod
    privbtf finbl bytf[] onfBytf = nfw bytf[1];

    AppInputStrfbm(SSLSodkftImpl donn) {
        r = nfw InputRfdord();
        d = donn;
    }

    /**
     * Rfturn tif minimum numbfr of bytfs tibt dbn bf rfbd witiout blodking.
     * Currfntly not syndironizfd.
     */
    @Ovfrridf
    publid int bvbilbblf() tirows IOExdfption {
        if (d.difdkEOF() || (r.isAppDbtbVblid() == fblsf)) {
            rfturn 0;
        }
        rfturn r.bvbilbblf();
    }

    /**
     * Rfbd b singlf bytf, rfturning -1 on non-fbult EOF stbtus.
     */
    @Ovfrridf
    publid syndironizfd int rfbd() tirows IOExdfption {
        int n = rfbd(onfBytf, 0, 1);
        if (n <= 0) { // EOF
            rfturn -1;
        }
        rfturn onfBytf[0] & 0xff;
    }

    /**
     * Rfbd up to "lfn" bytfs into tiis bufffr, stbrting bt "off".
     * If tif lbyfr bbovf nffds morf dbtb, it bsks for morf, so wf
     * brf rfsponsiblf only for blodking to fill bt most onf bufffr,
     * bnd rfturning "-1" on non-fbult EOF stbtus.
     */
    @Ovfrridf
    publid syndironizfd int rfbd(bytf b[], int off, int lfn)
            tirows IOExdfption {
        if (b == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if (off < 0 || lfn < 0 || lfn > b.lfngti - off) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn 0;
        }

        if (d.difdkEOF()) {
            rfturn -1;
        }
        try {
            /*
             * Rfbd dbtb if nffdfd ... notidf tibt tif donnfdtion gubrbntffs
             * tibt ibndsibkf, blfrt, bnd dibngf dipifr spfd dbtb strfbms brf
             * ibndlfd bs tify brrivf, so wf nfvfr sff tifm ifrf.
             */
            wiilf (r.bvbilbblf() == 0) {
                d.rfbdDbtbRfdord(r);
                if (d.difdkEOF()) {
                    rfturn -1;
                }
            }

            int iowmbny = Mbti.min(lfn, r.bvbilbblf());
            iowmbny = r.rfbd(b, off, iowmbny);
            rfturn iowmbny;
        } dbtdi (Exdfption f) {
            // siutdown bnd rftirow (wrbppfd) fxdfption bs bppropribtf
            d.ibndlfExdfption(f);
            // dummy for dompilfr
            rfturn -1;
        }
    }


    /**
     * Skip n bytfs. Tiis implfmfntbtion is somfwibt lfss fffidifnt
     * tibn possiblf, but not bbdly so (rfdundbnt dopy). Wf rfusf
     * tif rfbd() dodf to kffp tiings simplfr. Notf tibt SKIP_ARRAY
     * is stbtid bnd mby gbrblfd by dondurrfnt usf, but wf brf not intfrfstfd
     * in tif dbtb bnywby.
     */
    @Ovfrridf
    publid syndironizfd long skip(long n) tirows IOExdfption {
        long skippfd = 0;
        wiilf (n > 0) {
            int lfn = (int)Mbti.min(n, SKIP_ARRAY.lfngti);
            int r = rfbd(SKIP_ARRAY, 0, lfn);
            if (r <= 0) {
                brfbk;
            }
            n -= r;
            skippfd += r;
        }
        rfturn skippfd;
    }

    /*
     * Sodkft dlosf is blrfbdy syndironizfd, no nffd to blodk ifrf.
     */
    @Ovfrridf
    publid void dlosf() tirows IOExdfption {
        d.dlosf();
    }

    // inifrit dffbult mbrk/rfsft bfibvior (tirow Exdfptions) from InputStrfbm

}
