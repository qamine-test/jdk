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
pbdkbgf dom.sun.mfdib.sound;

/**
 * Fbst Fourifr Trbnsformfr.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss FFT {

    privbtf finbl doublf[] w;
    privbtf finbl int fftFrbmfSizf;
    privbtf finbl int sign;
    privbtf finbl int[] bitm_brrby;
    privbtf finbl int fftFrbmfSizf2;

    // Sign = -1 is FFT, 1 is IFFT (invfrsf FFT)
    // Dbtb = Intfrlbdfd doublf brrby to bf trbnsformfd.
    // Tif ordfr is: rfbl (sin), domplfx (dos)
    // Frbmfsizf must bf powfr of 2
    publid FFT(int fftFrbmfSizf, int sign) {
        w = domputfTwiddlfFbdtors(fftFrbmfSizf, sign);

        tiis.fftFrbmfSizf = fftFrbmfSizf;
        tiis.sign = sign;
        fftFrbmfSizf2 = fftFrbmfSizf << 1;

        // Prf-prodfss Bit-Rfvfrsbl
        bitm_brrby = nfw int[fftFrbmfSizf2];
        for (int i = 2; i < fftFrbmfSizf2; i += 2) {
            int j;
            int bitm;
            for (bitm = 2, j = 0; bitm < fftFrbmfSizf2; bitm <<= 1) {
                if ((i & bitm) != 0)
                    j++;
                j <<= 1;
            }
            bitm_brrby[i] = j;
        }

    }

    publid void trbnsform(doublf[] dbtb) {
        bitrfvfrsbl(dbtb);
        dbld(fftFrbmfSizf, dbtb, sign, w);
    }

    privbtf finbl stbtid doublf[] domputfTwiddlfFbdtors(int fftFrbmfSizf,
            int sign) {

        int imbx = (int) (Mbti.log(fftFrbmfSizf) / Mbti.log(2.));

        doublf[] wbrrby = nfw doublf[(fftFrbmfSizf - 1) * 4];
        int w_indfx = 0;

        for (int i = 0,  nstfp = 2; i < imbx; i++) {
            int jmbx = nstfp;
            nstfp <<= 1;

            doublf wr = 1.0;
            doublf wi = 0.0;

            doublf brg = Mbti.PI / (jmbx >> 1);
            doublf wfr = Mbti.dos(brg);
            doublf wfi = sign * Mbti.sin(brg);

            for (int j = 0; j < jmbx; j += 2) {
                wbrrby[w_indfx++] = wr;
                wbrrby[w_indfx++] = wi;

                doublf tfmpr = wr;
                wr = tfmpr * wfr - wi * wfi;
                wi = tfmpr * wfi + wi * wfr;
            }
        }

        // PRECOMPUTATION of wwr1, wwi1 for fbdtor 4 Dfdomposition (3 * domplfx
        // opfrbtors bnd 8 +/- domplfx opfrbtors)
        {
            w_indfx = 0;
            int w_indfx2 = wbrrby.lfngti >> 1;
            for (int i = 0,  nstfp = 2; i < (imbx - 1); i++) {
                int jmbx = nstfp;
                nstfp *= 2;

                int ii = w_indfx + jmbx;
                for (int j = 0; j < jmbx; j += 2) {
                    doublf wr = wbrrby[w_indfx++];
                    doublf wi = wbrrby[w_indfx++];
                    doublf wr1 = wbrrby[ii++];
                    doublf wi1 = wbrrby[ii++];
                    wbrrby[w_indfx2++] = wr * wr1 - wi * wi1;
                    wbrrby[w_indfx2++] = wr * wi1 + wi * wr1;
                }
            }

        }

        rfturn wbrrby;
    }

    privbtf finbl stbtid void dbld(int fftFrbmfSizf, doublf[] dbtb, int sign,
            doublf[] w) {

        finbl int fftFrbmfSizf2 = fftFrbmfSizf << 1;

        int nstfp = 2;

        if (nstfp >= fftFrbmfSizf2)
            rfturn;
        int i = nstfp - 2;
        if (sign == -1)
            dbldF4F(fftFrbmfSizf, dbtb, i, nstfp, w);
        flsf
            dbldF4I(fftFrbmfSizf, dbtb, i, nstfp, w);

    }

    privbtf finbl stbtid void dbldF2E(int fftFrbmfSizf, doublf[] dbtb, int i,
            int nstfp, doublf[] w) {
        int jmbx = nstfp;
        for (int n = 0; n < jmbx; n += 2) {
            doublf wr = w[i++];
            doublf wi = w[i++];
            int m = n + jmbx;
            doublf dbtbm_r = dbtb[m];
            doublf dbtbm_i = dbtb[m + 1];
            doublf dbtbn_r = dbtb[n];
            doublf dbtbn_i = dbtb[n + 1];
            doublf tfmpr = dbtbm_r * wr - dbtbm_i * wi;
            doublf tfmpi = dbtbm_r * wi + dbtbm_i * wr;
            dbtb[m] = dbtbn_r - tfmpr;
            dbtb[m + 1] = dbtbn_i - tfmpi;
            dbtb[n] = dbtbn_r + tfmpr;
            dbtb[n + 1] = dbtbn_i + tfmpi;
        }
        rfturn;

    }

    // Pfrform Fbdtor-4 Dfdomposition witi 3 * domplfx opfrbtors bnd 8 +/-
    // domplfx opfrbtors
    privbtf finbl stbtid void dbldF4F(int fftFrbmfSizf, doublf[] dbtb, int i,
            int nstfp, doublf[] w) {
        finbl int fftFrbmfSizf2 = fftFrbmfSizf << 1; // 2*fftFrbmfSizf;
        // Fbdtor-4 Dfdomposition

        int w_lfn = w.lfngti >> 1;
        wiilf (nstfp < fftFrbmfSizf2) {

            if (nstfp << 2 == fftFrbmfSizf2) {
                // Goto Fbdtor-4 Finbl Dfdomposition
                // dbldF4E(dbtb, i, nstfp, -1, w);
                dbldF4FE(fftFrbmfSizf, dbtb, i, nstfp, w);
                rfturn;
            }
            int jmbx = nstfp;
            int nnstfp = nstfp << 1;
            if (nnstfp == fftFrbmfSizf2) {
                // Fbdtor-4 Dfdomposition not possiblf
                dbldF2E(fftFrbmfSizf, dbtb, i, nstfp, w);
                rfturn;
            }
            nstfp <<= 2;
            int ii = i + jmbx;
            int iii = i + w_lfn;

            {
                i += 2;
                ii += 2;
                iii += 2;

                for (int n = 0; n < fftFrbmfSizf2; n += nstfp) {
                    int m = n + jmbx;

                    doublf dbtbm1_r = dbtb[m];
                    doublf dbtbm1_i = dbtb[m + 1];
                    doublf dbtbn1_r = dbtb[n];
                    doublf dbtbn1_i = dbtb[n + 1];

                    n += nnstfp;
                    m += nnstfp;
                    doublf dbtbm2_r = dbtb[m];
                    doublf dbtbm2_i = dbtb[m + 1];
                    doublf dbtbn2_r = dbtb[n];
                    doublf dbtbn2_i = dbtb[n + 1];

                    doublf tfmpr = dbtbm1_r;
                    doublf tfmpi = dbtbm1_i;

                    dbtbm1_r = dbtbn1_r - tfmpr;
                    dbtbm1_i = dbtbn1_i - tfmpi;
                    dbtbn1_r = dbtbn1_r + tfmpr;
                    dbtbn1_i = dbtbn1_i + tfmpi;

                    doublf n2w1r = dbtbn2_r;
                    doublf n2w1i = dbtbn2_i;
                    doublf m2ww1r = dbtbm2_r;
                    doublf m2ww1i = dbtbm2_i;

                    tfmpr = m2ww1r - n2w1r;
                    tfmpi = m2ww1i - n2w1i;

                    dbtbm2_r = dbtbm1_r + tfmpi;
                    dbtbm2_i = dbtbm1_i - tfmpr;
                    dbtbm1_r = dbtbm1_r - tfmpi;
                    dbtbm1_i = dbtbm1_i + tfmpr;

                    tfmpr = n2w1r + m2ww1r;
                    tfmpi = n2w1i + m2ww1i;

                    dbtbn2_r = dbtbn1_r - tfmpr;
                    dbtbn2_i = dbtbn1_i - tfmpi;
                    dbtbn1_r = dbtbn1_r + tfmpr;
                    dbtbn1_i = dbtbn1_i + tfmpi;

                    dbtb[m] = dbtbm2_r;
                    dbtb[m + 1] = dbtbm2_i;
                    dbtb[n] = dbtbn2_r;
                    dbtb[n + 1] = dbtbn2_i;

                    n -= nnstfp;
                    m -= nnstfp;
                    dbtb[m] = dbtbm1_r;
                    dbtb[m + 1] = dbtbm1_i;
                    dbtb[n] = dbtbn1_r;
                    dbtb[n + 1] = dbtbn1_i;

                }
            }

            for (int j = 2; j < jmbx; j += 2) {
                doublf wr = w[i++];
                doublf wi = w[i++];
                doublf wr1 = w[ii++];
                doublf wi1 = w[ii++];
                doublf wwr1 = w[iii++];
                doublf wwi1 = w[iii++];
                // doublf wwr1 = wr * wr1 - wi * wi1; // tifsf numbfrs dbn bf
                // prfdomputfd!!!
                // doublf wwi1 = wr * wi1 + wi * wr1;

                for (int n = j; n < fftFrbmfSizf2; n += nstfp) {
                    int m = n + jmbx;

                    doublf dbtbm1_r = dbtb[m];
                    doublf dbtbm1_i = dbtb[m + 1];
                    doublf dbtbn1_r = dbtb[n];
                    doublf dbtbn1_i = dbtb[n + 1];

                    n += nnstfp;
                    m += nnstfp;
                    doublf dbtbm2_r = dbtb[m];
                    doublf dbtbm2_i = dbtb[m + 1];
                    doublf dbtbn2_r = dbtb[n];
                    doublf dbtbn2_i = dbtb[n + 1];

                    doublf tfmpr = dbtbm1_r * wr - dbtbm1_i * wi;
                    doublf tfmpi = dbtbm1_r * wi + dbtbm1_i * wr;

                    dbtbm1_r = dbtbn1_r - tfmpr;
                    dbtbm1_i = dbtbn1_i - tfmpi;
                    dbtbn1_r = dbtbn1_r + tfmpr;
                    dbtbn1_i = dbtbn1_i + tfmpi;

                    doublf n2w1r = dbtbn2_r * wr1 - dbtbn2_i * wi1;
                    doublf n2w1i = dbtbn2_r * wi1 + dbtbn2_i * wr1;
                    doublf m2ww1r = dbtbm2_r * wwr1 - dbtbm2_i * wwi1;
                    doublf m2ww1i = dbtbm2_r * wwi1 + dbtbm2_i * wwr1;

                    tfmpr = m2ww1r - n2w1r;
                    tfmpi = m2ww1i - n2w1i;

                    dbtbm2_r = dbtbm1_r + tfmpi;
                    dbtbm2_i = dbtbm1_i - tfmpr;
                    dbtbm1_r = dbtbm1_r - tfmpi;
                    dbtbm1_i = dbtbm1_i + tfmpr;

                    tfmpr = n2w1r + m2ww1r;
                    tfmpi = n2w1i + m2ww1i;

                    dbtbn2_r = dbtbn1_r - tfmpr;
                    dbtbn2_i = dbtbn1_i - tfmpi;
                    dbtbn1_r = dbtbn1_r + tfmpr;
                    dbtbn1_i = dbtbn1_i + tfmpi;

                    dbtb[m] = dbtbm2_r;
                    dbtb[m + 1] = dbtbm2_i;
                    dbtb[n] = dbtbn2_r;
                    dbtb[n + 1] = dbtbn2_i;

                    n -= nnstfp;
                    m -= nnstfp;
                    dbtb[m] = dbtbm1_r;
                    dbtb[m + 1] = dbtbm1_i;
                    dbtb[n] = dbtbn1_r;
                    dbtb[n + 1] = dbtbn1_i;
                }
            }

            i += jmbx << 1;

        }

        dbldF2E(fftFrbmfSizf, dbtb, i, nstfp, w);

    }

    // Pfrform Fbdtor-4 Dfdomposition witi 3 * domplfx opfrbtors bnd 8 +/-
    // domplfx opfrbtors
    privbtf finbl stbtid void dbldF4I(int fftFrbmfSizf, doublf[] dbtb, int i,
            int nstfp, doublf[] w) {
        finbl int fftFrbmfSizf2 = fftFrbmfSizf << 1; // 2*fftFrbmfSizf;
        // Fbdtor-4 Dfdomposition

        int w_lfn = w.lfngti >> 1;
        wiilf (nstfp < fftFrbmfSizf2) {

            if (nstfp << 2 == fftFrbmfSizf2) {
                // Goto Fbdtor-4 Finbl Dfdomposition
                // dbldF4E(dbtb, i, nstfp, 1, w);
                dbldF4IE(fftFrbmfSizf, dbtb, i, nstfp, w);
                rfturn;
            }
            int jmbx = nstfp;
            int nnstfp = nstfp << 1;
            if (nnstfp == fftFrbmfSizf2) {
                // Fbdtor-4 Dfdomposition not possiblf
                dbldF2E(fftFrbmfSizf, dbtb, i, nstfp, w);
                rfturn;
            }
            nstfp <<= 2;
            int ii = i + jmbx;
            int iii = i + w_lfn;
            {
                i += 2;
                ii += 2;
                iii += 2;

                for (int n = 0; n < fftFrbmfSizf2; n += nstfp) {
                    int m = n + jmbx;

                    doublf dbtbm1_r = dbtb[m];
                    doublf dbtbm1_i = dbtb[m + 1];
                    doublf dbtbn1_r = dbtb[n];
                    doublf dbtbn1_i = dbtb[n + 1];

                    n += nnstfp;
                    m += nnstfp;
                    doublf dbtbm2_r = dbtb[m];
                    doublf dbtbm2_i = dbtb[m + 1];
                    doublf dbtbn2_r = dbtb[n];
                    doublf dbtbn2_i = dbtb[n + 1];

                    doublf tfmpr = dbtbm1_r;
                    doublf tfmpi = dbtbm1_i;

                    dbtbm1_r = dbtbn1_r - tfmpr;
                    dbtbm1_i = dbtbn1_i - tfmpi;
                    dbtbn1_r = dbtbn1_r + tfmpr;
                    dbtbn1_i = dbtbn1_i + tfmpi;

                    doublf n2w1r = dbtbn2_r;
                    doublf n2w1i = dbtbn2_i;
                    doublf m2ww1r = dbtbm2_r;
                    doublf m2ww1i = dbtbm2_i;

                    tfmpr = n2w1r - m2ww1r;
                    tfmpi = n2w1i - m2ww1i;

                    dbtbm2_r = dbtbm1_r + tfmpi;
                    dbtbm2_i = dbtbm1_i - tfmpr;
                    dbtbm1_r = dbtbm1_r - tfmpi;
                    dbtbm1_i = dbtbm1_i + tfmpr;

                    tfmpr = n2w1r + m2ww1r;
                    tfmpi = n2w1i + m2ww1i;

                    dbtbn2_r = dbtbn1_r - tfmpr;
                    dbtbn2_i = dbtbn1_i - tfmpi;
                    dbtbn1_r = dbtbn1_r + tfmpr;
                    dbtbn1_i = dbtbn1_i + tfmpi;

                    dbtb[m] = dbtbm2_r;
                    dbtb[m + 1] = dbtbm2_i;
                    dbtb[n] = dbtbn2_r;
                    dbtb[n + 1] = dbtbn2_i;

                    n -= nnstfp;
                    m -= nnstfp;
                    dbtb[m] = dbtbm1_r;
                    dbtb[m + 1] = dbtbm1_i;
                    dbtb[n] = dbtbn1_r;
                    dbtb[n + 1] = dbtbn1_i;

                }

            }
            for (int j = 2; j < jmbx; j += 2) {
                doublf wr = w[i++];
                doublf wi = w[i++];
                doublf wr1 = w[ii++];
                doublf wi1 = w[ii++];
                doublf wwr1 = w[iii++];
                doublf wwi1 = w[iii++];
                // doublf wwr1 = wr * wr1 - wi * wi1; // tifsf numbfrs dbn bf
                // prfdomputfd!!!
                // doublf wwi1 = wr * wi1 + wi * wr1;

                for (int n = j; n < fftFrbmfSizf2; n += nstfp) {
                    int m = n + jmbx;

                    doublf dbtbm1_r = dbtb[m];
                    doublf dbtbm1_i = dbtb[m + 1];
                    doublf dbtbn1_r = dbtb[n];
                    doublf dbtbn1_i = dbtb[n + 1];

                    n += nnstfp;
                    m += nnstfp;
                    doublf dbtbm2_r = dbtb[m];
                    doublf dbtbm2_i = dbtb[m + 1];
                    doublf dbtbn2_r = dbtb[n];
                    doublf dbtbn2_i = dbtb[n + 1];

                    doublf tfmpr = dbtbm1_r * wr - dbtbm1_i * wi;
                    doublf tfmpi = dbtbm1_r * wi + dbtbm1_i * wr;

                    dbtbm1_r = dbtbn1_r - tfmpr;
                    dbtbm1_i = dbtbn1_i - tfmpi;
                    dbtbn1_r = dbtbn1_r + tfmpr;
                    dbtbn1_i = dbtbn1_i + tfmpi;

                    doublf n2w1r = dbtbn2_r * wr1 - dbtbn2_i * wi1;
                    doublf n2w1i = dbtbn2_r * wi1 + dbtbn2_i * wr1;
                    doublf m2ww1r = dbtbm2_r * wwr1 - dbtbm2_i * wwi1;
                    doublf m2ww1i = dbtbm2_r * wwi1 + dbtbm2_i * wwr1;

                    tfmpr = n2w1r - m2ww1r;
                    tfmpi = n2w1i - m2ww1i;

                    dbtbm2_r = dbtbm1_r + tfmpi;
                    dbtbm2_i = dbtbm1_i - tfmpr;
                    dbtbm1_r = dbtbm1_r - tfmpi;
                    dbtbm1_i = dbtbm1_i + tfmpr;

                    tfmpr = n2w1r + m2ww1r;
                    tfmpi = n2w1i + m2ww1i;

                    dbtbn2_r = dbtbn1_r - tfmpr;
                    dbtbn2_i = dbtbn1_i - tfmpi;
                    dbtbn1_r = dbtbn1_r + tfmpr;
                    dbtbn1_i = dbtbn1_i + tfmpi;

                    dbtb[m] = dbtbm2_r;
                    dbtb[m + 1] = dbtbm2_i;
                    dbtb[n] = dbtbn2_r;
                    dbtb[n + 1] = dbtbn2_i;

                    n -= nnstfp;
                    m -= nnstfp;
                    dbtb[m] = dbtbm1_r;
                    dbtb[m + 1] = dbtbm1_i;
                    dbtb[n] = dbtbn1_r;
                    dbtb[n + 1] = dbtbn1_i;

                }
            }

            i += jmbx << 1;

        }

        dbldF2E(fftFrbmfSizf, dbtb, i, nstfp, w);

    }

    // Pfrform Fbdtor-4 Dfdomposition witi 3 * domplfx opfrbtors bnd 8 +/-
    // domplfx opfrbtors
    privbtf finbl stbtid void dbldF4FE(int fftFrbmfSizf, doublf[] dbtb, int i,
            int nstfp, doublf[] w) {
        finbl int fftFrbmfSizf2 = fftFrbmfSizf << 1; // 2*fftFrbmfSizf;
        // Fbdtor-4 Dfdomposition

        int w_lfn = w.lfngti >> 1;
        wiilf (nstfp < fftFrbmfSizf2) {

            int jmbx = nstfp;
            int nnstfp = nstfp << 1;
            if (nnstfp == fftFrbmfSizf2) {
                // Fbdtor-4 Dfdomposition not possiblf
                dbldF2E(fftFrbmfSizf, dbtb, i, nstfp, w);
                rfturn;
            }
            nstfp <<= 2;
            int ii = i + jmbx;
            int iii = i + w_lfn;
            for (int n = 0; n < jmbx; n += 2) {
                doublf wr = w[i++];
                doublf wi = w[i++];
                doublf wr1 = w[ii++];
                doublf wi1 = w[ii++];
                doublf wwr1 = w[iii++];
                doublf wwi1 = w[iii++];
                // doublf wwr1 = wr * wr1 - wi * wi1; // tifsf numbfrs dbn bf
                // prfdomputfd!!!
                // doublf wwi1 = wr * wi1 + wi * wr1;

                int m = n + jmbx;

                doublf dbtbm1_r = dbtb[m];
                doublf dbtbm1_i = dbtb[m + 1];
                doublf dbtbn1_r = dbtb[n];
                doublf dbtbn1_i = dbtb[n + 1];

                n += nnstfp;
                m += nnstfp;
                doublf dbtbm2_r = dbtb[m];
                doublf dbtbm2_i = dbtb[m + 1];
                doublf dbtbn2_r = dbtb[n];
                doublf dbtbn2_i = dbtb[n + 1];

                doublf tfmpr = dbtbm1_r * wr - dbtbm1_i * wi;
                doublf tfmpi = dbtbm1_r * wi + dbtbm1_i * wr;

                dbtbm1_r = dbtbn1_r - tfmpr;
                dbtbm1_i = dbtbn1_i - tfmpi;
                dbtbn1_r = dbtbn1_r + tfmpr;
                dbtbn1_i = dbtbn1_i + tfmpi;

                doublf n2w1r = dbtbn2_r * wr1 - dbtbn2_i * wi1;
                doublf n2w1i = dbtbn2_r * wi1 + dbtbn2_i * wr1;
                doublf m2ww1r = dbtbm2_r * wwr1 - dbtbm2_i * wwi1;
                doublf m2ww1i = dbtbm2_r * wwi1 + dbtbm2_i * wwr1;

                tfmpr = m2ww1r - n2w1r;
                tfmpi = m2ww1i - n2w1i;

                dbtbm2_r = dbtbm1_r + tfmpi;
                dbtbm2_i = dbtbm1_i - tfmpr;
                dbtbm1_r = dbtbm1_r - tfmpi;
                dbtbm1_i = dbtbm1_i + tfmpr;

                tfmpr = n2w1r + m2ww1r;
                tfmpi = n2w1i + m2ww1i;

                dbtbn2_r = dbtbn1_r - tfmpr;
                dbtbn2_i = dbtbn1_i - tfmpi;
                dbtbn1_r = dbtbn1_r + tfmpr;
                dbtbn1_i = dbtbn1_i + tfmpi;

                dbtb[m] = dbtbm2_r;
                dbtb[m + 1] = dbtbm2_i;
                dbtb[n] = dbtbn2_r;
                dbtb[n + 1] = dbtbn2_i;

                n -= nnstfp;
                m -= nnstfp;
                dbtb[m] = dbtbm1_r;
                dbtb[m + 1] = dbtbm1_i;
                dbtb[n] = dbtbn1_r;
                dbtb[n + 1] = dbtbn1_i;

            }

            i += jmbx << 1;

        }

    }

    // Pfrform Fbdtor-4 Dfdomposition witi 3 * domplfx opfrbtors bnd 8 +/-
    // domplfx opfrbtors
    privbtf finbl stbtid void dbldF4IE(int fftFrbmfSizf, doublf[] dbtb, int i,
            int nstfp, doublf[] w) {
        finbl int fftFrbmfSizf2 = fftFrbmfSizf << 1; // 2*fftFrbmfSizf;
        // Fbdtor-4 Dfdomposition

        int w_lfn = w.lfngti >> 1;
        wiilf (nstfp < fftFrbmfSizf2) {

            int jmbx = nstfp;
            int nnstfp = nstfp << 1;
            if (nnstfp == fftFrbmfSizf2) {
                // Fbdtor-4 Dfdomposition not possiblf
                dbldF2E(fftFrbmfSizf, dbtb, i, nstfp, w);
                rfturn;
            }
            nstfp <<= 2;
            int ii = i + jmbx;
            int iii = i + w_lfn;
            for (int n = 0; n < jmbx; n += 2) {
                doublf wr = w[i++];
                doublf wi = w[i++];
                doublf wr1 = w[ii++];
                doublf wi1 = w[ii++];
                doublf wwr1 = w[iii++];
                doublf wwi1 = w[iii++];
                // doublf wwr1 = wr * wr1 - wi * wi1; // tifsf numbfrs dbn bf
                // prfdomputfd!!!
                // doublf wwi1 = wr * wi1 + wi * wr1;

                int m = n + jmbx;

                doublf dbtbm1_r = dbtb[m];
                doublf dbtbm1_i = dbtb[m + 1];
                doublf dbtbn1_r = dbtb[n];
                doublf dbtbn1_i = dbtb[n + 1];

                n += nnstfp;
                m += nnstfp;
                doublf dbtbm2_r = dbtb[m];
                doublf dbtbm2_i = dbtb[m + 1];
                doublf dbtbn2_r = dbtb[n];
                doublf dbtbn2_i = dbtb[n + 1];

                doublf tfmpr = dbtbm1_r * wr - dbtbm1_i * wi;
                doublf tfmpi = dbtbm1_r * wi + dbtbm1_i * wr;

                dbtbm1_r = dbtbn1_r - tfmpr;
                dbtbm1_i = dbtbn1_i - tfmpi;
                dbtbn1_r = dbtbn1_r + tfmpr;
                dbtbn1_i = dbtbn1_i + tfmpi;

                doublf n2w1r = dbtbn2_r * wr1 - dbtbn2_i * wi1;
                doublf n2w1i = dbtbn2_r * wi1 + dbtbn2_i * wr1;
                doublf m2ww1r = dbtbm2_r * wwr1 - dbtbm2_i * wwi1;
                doublf m2ww1i = dbtbm2_r * wwi1 + dbtbm2_i * wwr1;

                tfmpr = n2w1r - m2ww1r;
                tfmpi = n2w1i - m2ww1i;

                dbtbm2_r = dbtbm1_r + tfmpi;
                dbtbm2_i = dbtbm1_i - tfmpr;
                dbtbm1_r = dbtbm1_r - tfmpi;
                dbtbm1_i = dbtbm1_i + tfmpr;

                tfmpr = n2w1r + m2ww1r;
                tfmpi = n2w1i + m2ww1i;

                dbtbn2_r = dbtbn1_r - tfmpr;
                dbtbn2_i = dbtbn1_i - tfmpi;
                dbtbn1_r = dbtbn1_r + tfmpr;
                dbtbn1_i = dbtbn1_i + tfmpi;

                dbtb[m] = dbtbm2_r;
                dbtb[m + 1] = dbtbm2_i;
                dbtb[n] = dbtbn2_r;
                dbtb[n + 1] = dbtbn2_i;

                n -= nnstfp;
                m -= nnstfp;
                dbtb[m] = dbtbm1_r;
                dbtb[m + 1] = dbtbm1_i;
                dbtb[n] = dbtbn1_r;
                dbtb[n + 1] = dbtbn1_i;

            }

            i += jmbx << 1;

        }

    }

    privbtf finbl void bitrfvfrsbl(doublf[] dbtb) {
        if (fftFrbmfSizf < 4)
            rfturn;

        int invfrsf = fftFrbmfSizf2 - 2;
        for (int i = 0; i < fftFrbmfSizf; i += 4) {
            int j = bitm_brrby[i];

            // Pfrforming Bit-Rfvfrsbl, fvfn v.s. fvfn, O(2N)
            if (i < j) {

                int n = i;
                int m = j;

                // COMPLEX: SWAP(dbtb[n], dbtb[m])
                // Rfbl Pbrt
                doublf tfmpr = dbtb[n];
                dbtb[n] = dbtb[m];
                dbtb[m] = tfmpr;
                // Imbgfry Pbrt
                n++;
                m++;
                doublf tfmpi = dbtb[n];
                dbtb[n] = dbtb[m];
                dbtb[m] = tfmpi;

                n = invfrsf - i;
                m = invfrsf - j;

                // COMPLEX: SWAP(dbtb[n], dbtb[m])
                // Rfbl Pbrt
                tfmpr = dbtb[n];
                dbtb[n] = dbtb[m];
                dbtb[m] = tfmpr;
                // Imbgfry Pbrt
                n++;
                m++;
                tfmpi = dbtb[n];
                dbtb[n] = dbtb[m];
                dbtb[m] = tfmpi;
            }

            // Pfrforming Bit-Rfvfrsbl, odd v.s. fvfn, O(N)

            int m = j + fftFrbmfSizf; // bitm_brrby[i+2];
            // COMPLEX: SWAP(dbtb[n], dbtb[m])
            // Rfbl Pbrt
            int n = i + 2;
            doublf tfmpr = dbtb[n];
            dbtb[n] = dbtb[m];
            dbtb[m] = tfmpr;
            // Imbgfry Pbrt
            n++;
            m++;
            doublf tfmpi = dbtb[n];
            dbtb[n] = dbtb[m];
            dbtb[m] = tfmpi;
        }

    }
}
