/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge com.sun.medib.sound;

/**
 * Fbst Fourier Trbnsformer.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss FFT {

    privbte finbl double[] w;
    privbte finbl int fftFrbmeSize;
    privbte finbl int sign;
    privbte finbl int[] bitm_brrby;
    privbte finbl int fftFrbmeSize2;

    // Sign = -1 is FFT, 1 is IFFT (inverse FFT)
    // Dbtb = Interlbced double brrby to be trbnsformed.
    // The order is: rebl (sin), complex (cos)
    // Frbmesize must be power of 2
    public FFT(int fftFrbmeSize, int sign) {
        w = computeTwiddleFbctors(fftFrbmeSize, sign);

        this.fftFrbmeSize = fftFrbmeSize;
        this.sign = sign;
        fftFrbmeSize2 = fftFrbmeSize << 1;

        // Pre-process Bit-Reversbl
        bitm_brrby = new int[fftFrbmeSize2];
        for (int i = 2; i < fftFrbmeSize2; i += 2) {
            int j;
            int bitm;
            for (bitm = 2, j = 0; bitm < fftFrbmeSize2; bitm <<= 1) {
                if ((i & bitm) != 0)
                    j++;
                j <<= 1;
            }
            bitm_brrby[i] = j;
        }

    }

    public void trbnsform(double[] dbtb) {
        bitreversbl(dbtb);
        cblc(fftFrbmeSize, dbtb, sign, w);
    }

    privbte finbl stbtic double[] computeTwiddleFbctors(int fftFrbmeSize,
            int sign) {

        int imbx = (int) (Mbth.log(fftFrbmeSize) / Mbth.log(2.));

        double[] wbrrby = new double[(fftFrbmeSize - 1) * 4];
        int w_index = 0;

        for (int i = 0,  nstep = 2; i < imbx; i++) {
            int jmbx = nstep;
            nstep <<= 1;

            double wr = 1.0;
            double wi = 0.0;

            double brg = Mbth.PI / (jmbx >> 1);
            double wfr = Mbth.cos(brg);
            double wfi = sign * Mbth.sin(brg);

            for (int j = 0; j < jmbx; j += 2) {
                wbrrby[w_index++] = wr;
                wbrrby[w_index++] = wi;

                double tempr = wr;
                wr = tempr * wfr - wi * wfi;
                wi = tempr * wfi + wi * wfr;
            }
        }

        // PRECOMPUTATION of wwr1, wwi1 for fbctor 4 Decomposition (3 * complex
        // operbtors bnd 8 +/- complex operbtors)
        {
            w_index = 0;
            int w_index2 = wbrrby.length >> 1;
            for (int i = 0,  nstep = 2; i < (imbx - 1); i++) {
                int jmbx = nstep;
                nstep *= 2;

                int ii = w_index + jmbx;
                for (int j = 0; j < jmbx; j += 2) {
                    double wr = wbrrby[w_index++];
                    double wi = wbrrby[w_index++];
                    double wr1 = wbrrby[ii++];
                    double wi1 = wbrrby[ii++];
                    wbrrby[w_index2++] = wr * wr1 - wi * wi1;
                    wbrrby[w_index2++] = wr * wi1 + wi * wr1;
                }
            }

        }

        return wbrrby;
    }

    privbte finbl stbtic void cblc(int fftFrbmeSize, double[] dbtb, int sign,
            double[] w) {

        finbl int fftFrbmeSize2 = fftFrbmeSize << 1;

        int nstep = 2;

        if (nstep >= fftFrbmeSize2)
            return;
        int i = nstep - 2;
        if (sign == -1)
            cblcF4F(fftFrbmeSize, dbtb, i, nstep, w);
        else
            cblcF4I(fftFrbmeSize, dbtb, i, nstep, w);

    }

    privbte finbl stbtic void cblcF2E(int fftFrbmeSize, double[] dbtb, int i,
            int nstep, double[] w) {
        int jmbx = nstep;
        for (int n = 0; n < jmbx; n += 2) {
            double wr = w[i++];
            double wi = w[i++];
            int m = n + jmbx;
            double dbtbm_r = dbtb[m];
            double dbtbm_i = dbtb[m + 1];
            double dbtbn_r = dbtb[n];
            double dbtbn_i = dbtb[n + 1];
            double tempr = dbtbm_r * wr - dbtbm_i * wi;
            double tempi = dbtbm_r * wi + dbtbm_i * wr;
            dbtb[m] = dbtbn_r - tempr;
            dbtb[m + 1] = dbtbn_i - tempi;
            dbtb[n] = dbtbn_r + tempr;
            dbtb[n + 1] = dbtbn_i + tempi;
        }
        return;

    }

    // Perform Fbctor-4 Decomposition with 3 * complex operbtors bnd 8 +/-
    // complex operbtors
    privbte finbl stbtic void cblcF4F(int fftFrbmeSize, double[] dbtb, int i,
            int nstep, double[] w) {
        finbl int fftFrbmeSize2 = fftFrbmeSize << 1; // 2*fftFrbmeSize;
        // Fbctor-4 Decomposition

        int w_len = w.length >> 1;
        while (nstep < fftFrbmeSize2) {

            if (nstep << 2 == fftFrbmeSize2) {
                // Goto Fbctor-4 Finbl Decomposition
                // cblcF4E(dbtb, i, nstep, -1, w);
                cblcF4FE(fftFrbmeSize, dbtb, i, nstep, w);
                return;
            }
            int jmbx = nstep;
            int nnstep = nstep << 1;
            if (nnstep == fftFrbmeSize2) {
                // Fbctor-4 Decomposition not possible
                cblcF2E(fftFrbmeSize, dbtb, i, nstep, w);
                return;
            }
            nstep <<= 2;
            int ii = i + jmbx;
            int iii = i + w_len;

            {
                i += 2;
                ii += 2;
                iii += 2;

                for (int n = 0; n < fftFrbmeSize2; n += nstep) {
                    int m = n + jmbx;

                    double dbtbm1_r = dbtb[m];
                    double dbtbm1_i = dbtb[m + 1];
                    double dbtbn1_r = dbtb[n];
                    double dbtbn1_i = dbtb[n + 1];

                    n += nnstep;
                    m += nnstep;
                    double dbtbm2_r = dbtb[m];
                    double dbtbm2_i = dbtb[m + 1];
                    double dbtbn2_r = dbtb[n];
                    double dbtbn2_i = dbtb[n + 1];

                    double tempr = dbtbm1_r;
                    double tempi = dbtbm1_i;

                    dbtbm1_r = dbtbn1_r - tempr;
                    dbtbm1_i = dbtbn1_i - tempi;
                    dbtbn1_r = dbtbn1_r + tempr;
                    dbtbn1_i = dbtbn1_i + tempi;

                    double n2w1r = dbtbn2_r;
                    double n2w1i = dbtbn2_i;
                    double m2ww1r = dbtbm2_r;
                    double m2ww1i = dbtbm2_i;

                    tempr = m2ww1r - n2w1r;
                    tempi = m2ww1i - n2w1i;

                    dbtbm2_r = dbtbm1_r + tempi;
                    dbtbm2_i = dbtbm1_i - tempr;
                    dbtbm1_r = dbtbm1_r - tempi;
                    dbtbm1_i = dbtbm1_i + tempr;

                    tempr = n2w1r + m2ww1r;
                    tempi = n2w1i + m2ww1i;

                    dbtbn2_r = dbtbn1_r - tempr;
                    dbtbn2_i = dbtbn1_i - tempi;
                    dbtbn1_r = dbtbn1_r + tempr;
                    dbtbn1_i = dbtbn1_i + tempi;

                    dbtb[m] = dbtbm2_r;
                    dbtb[m + 1] = dbtbm2_i;
                    dbtb[n] = dbtbn2_r;
                    dbtb[n + 1] = dbtbn2_i;

                    n -= nnstep;
                    m -= nnstep;
                    dbtb[m] = dbtbm1_r;
                    dbtb[m + 1] = dbtbm1_i;
                    dbtb[n] = dbtbn1_r;
                    dbtb[n + 1] = dbtbn1_i;

                }
            }

            for (int j = 2; j < jmbx; j += 2) {
                double wr = w[i++];
                double wi = w[i++];
                double wr1 = w[ii++];
                double wi1 = w[ii++];
                double wwr1 = w[iii++];
                double wwi1 = w[iii++];
                // double wwr1 = wr * wr1 - wi * wi1; // these numbers cbn be
                // precomputed!!!
                // double wwi1 = wr * wi1 + wi * wr1;

                for (int n = j; n < fftFrbmeSize2; n += nstep) {
                    int m = n + jmbx;

                    double dbtbm1_r = dbtb[m];
                    double dbtbm1_i = dbtb[m + 1];
                    double dbtbn1_r = dbtb[n];
                    double dbtbn1_i = dbtb[n + 1];

                    n += nnstep;
                    m += nnstep;
                    double dbtbm2_r = dbtb[m];
                    double dbtbm2_i = dbtb[m + 1];
                    double dbtbn2_r = dbtb[n];
                    double dbtbn2_i = dbtb[n + 1];

                    double tempr = dbtbm1_r * wr - dbtbm1_i * wi;
                    double tempi = dbtbm1_r * wi + dbtbm1_i * wr;

                    dbtbm1_r = dbtbn1_r - tempr;
                    dbtbm1_i = dbtbn1_i - tempi;
                    dbtbn1_r = dbtbn1_r + tempr;
                    dbtbn1_i = dbtbn1_i + tempi;

                    double n2w1r = dbtbn2_r * wr1 - dbtbn2_i * wi1;
                    double n2w1i = dbtbn2_r * wi1 + dbtbn2_i * wr1;
                    double m2ww1r = dbtbm2_r * wwr1 - dbtbm2_i * wwi1;
                    double m2ww1i = dbtbm2_r * wwi1 + dbtbm2_i * wwr1;

                    tempr = m2ww1r - n2w1r;
                    tempi = m2ww1i - n2w1i;

                    dbtbm2_r = dbtbm1_r + tempi;
                    dbtbm2_i = dbtbm1_i - tempr;
                    dbtbm1_r = dbtbm1_r - tempi;
                    dbtbm1_i = dbtbm1_i + tempr;

                    tempr = n2w1r + m2ww1r;
                    tempi = n2w1i + m2ww1i;

                    dbtbn2_r = dbtbn1_r - tempr;
                    dbtbn2_i = dbtbn1_i - tempi;
                    dbtbn1_r = dbtbn1_r + tempr;
                    dbtbn1_i = dbtbn1_i + tempi;

                    dbtb[m] = dbtbm2_r;
                    dbtb[m + 1] = dbtbm2_i;
                    dbtb[n] = dbtbn2_r;
                    dbtb[n + 1] = dbtbn2_i;

                    n -= nnstep;
                    m -= nnstep;
                    dbtb[m] = dbtbm1_r;
                    dbtb[m + 1] = dbtbm1_i;
                    dbtb[n] = dbtbn1_r;
                    dbtb[n + 1] = dbtbn1_i;
                }
            }

            i += jmbx << 1;

        }

        cblcF2E(fftFrbmeSize, dbtb, i, nstep, w);

    }

    // Perform Fbctor-4 Decomposition with 3 * complex operbtors bnd 8 +/-
    // complex operbtors
    privbte finbl stbtic void cblcF4I(int fftFrbmeSize, double[] dbtb, int i,
            int nstep, double[] w) {
        finbl int fftFrbmeSize2 = fftFrbmeSize << 1; // 2*fftFrbmeSize;
        // Fbctor-4 Decomposition

        int w_len = w.length >> 1;
        while (nstep < fftFrbmeSize2) {

            if (nstep << 2 == fftFrbmeSize2) {
                // Goto Fbctor-4 Finbl Decomposition
                // cblcF4E(dbtb, i, nstep, 1, w);
                cblcF4IE(fftFrbmeSize, dbtb, i, nstep, w);
                return;
            }
            int jmbx = nstep;
            int nnstep = nstep << 1;
            if (nnstep == fftFrbmeSize2) {
                // Fbctor-4 Decomposition not possible
                cblcF2E(fftFrbmeSize, dbtb, i, nstep, w);
                return;
            }
            nstep <<= 2;
            int ii = i + jmbx;
            int iii = i + w_len;
            {
                i += 2;
                ii += 2;
                iii += 2;

                for (int n = 0; n < fftFrbmeSize2; n += nstep) {
                    int m = n + jmbx;

                    double dbtbm1_r = dbtb[m];
                    double dbtbm1_i = dbtb[m + 1];
                    double dbtbn1_r = dbtb[n];
                    double dbtbn1_i = dbtb[n + 1];

                    n += nnstep;
                    m += nnstep;
                    double dbtbm2_r = dbtb[m];
                    double dbtbm2_i = dbtb[m + 1];
                    double dbtbn2_r = dbtb[n];
                    double dbtbn2_i = dbtb[n + 1];

                    double tempr = dbtbm1_r;
                    double tempi = dbtbm1_i;

                    dbtbm1_r = dbtbn1_r - tempr;
                    dbtbm1_i = dbtbn1_i - tempi;
                    dbtbn1_r = dbtbn1_r + tempr;
                    dbtbn1_i = dbtbn1_i + tempi;

                    double n2w1r = dbtbn2_r;
                    double n2w1i = dbtbn2_i;
                    double m2ww1r = dbtbm2_r;
                    double m2ww1i = dbtbm2_i;

                    tempr = n2w1r - m2ww1r;
                    tempi = n2w1i - m2ww1i;

                    dbtbm2_r = dbtbm1_r + tempi;
                    dbtbm2_i = dbtbm1_i - tempr;
                    dbtbm1_r = dbtbm1_r - tempi;
                    dbtbm1_i = dbtbm1_i + tempr;

                    tempr = n2w1r + m2ww1r;
                    tempi = n2w1i + m2ww1i;

                    dbtbn2_r = dbtbn1_r - tempr;
                    dbtbn2_i = dbtbn1_i - tempi;
                    dbtbn1_r = dbtbn1_r + tempr;
                    dbtbn1_i = dbtbn1_i + tempi;

                    dbtb[m] = dbtbm2_r;
                    dbtb[m + 1] = dbtbm2_i;
                    dbtb[n] = dbtbn2_r;
                    dbtb[n + 1] = dbtbn2_i;

                    n -= nnstep;
                    m -= nnstep;
                    dbtb[m] = dbtbm1_r;
                    dbtb[m + 1] = dbtbm1_i;
                    dbtb[n] = dbtbn1_r;
                    dbtb[n + 1] = dbtbn1_i;

                }

            }
            for (int j = 2; j < jmbx; j += 2) {
                double wr = w[i++];
                double wi = w[i++];
                double wr1 = w[ii++];
                double wi1 = w[ii++];
                double wwr1 = w[iii++];
                double wwi1 = w[iii++];
                // double wwr1 = wr * wr1 - wi * wi1; // these numbers cbn be
                // precomputed!!!
                // double wwi1 = wr * wi1 + wi * wr1;

                for (int n = j; n < fftFrbmeSize2; n += nstep) {
                    int m = n + jmbx;

                    double dbtbm1_r = dbtb[m];
                    double dbtbm1_i = dbtb[m + 1];
                    double dbtbn1_r = dbtb[n];
                    double dbtbn1_i = dbtb[n + 1];

                    n += nnstep;
                    m += nnstep;
                    double dbtbm2_r = dbtb[m];
                    double dbtbm2_i = dbtb[m + 1];
                    double dbtbn2_r = dbtb[n];
                    double dbtbn2_i = dbtb[n + 1];

                    double tempr = dbtbm1_r * wr - dbtbm1_i * wi;
                    double tempi = dbtbm1_r * wi + dbtbm1_i * wr;

                    dbtbm1_r = dbtbn1_r - tempr;
                    dbtbm1_i = dbtbn1_i - tempi;
                    dbtbn1_r = dbtbn1_r + tempr;
                    dbtbn1_i = dbtbn1_i + tempi;

                    double n2w1r = dbtbn2_r * wr1 - dbtbn2_i * wi1;
                    double n2w1i = dbtbn2_r * wi1 + dbtbn2_i * wr1;
                    double m2ww1r = dbtbm2_r * wwr1 - dbtbm2_i * wwi1;
                    double m2ww1i = dbtbm2_r * wwi1 + dbtbm2_i * wwr1;

                    tempr = n2w1r - m2ww1r;
                    tempi = n2w1i - m2ww1i;

                    dbtbm2_r = dbtbm1_r + tempi;
                    dbtbm2_i = dbtbm1_i - tempr;
                    dbtbm1_r = dbtbm1_r - tempi;
                    dbtbm1_i = dbtbm1_i + tempr;

                    tempr = n2w1r + m2ww1r;
                    tempi = n2w1i + m2ww1i;

                    dbtbn2_r = dbtbn1_r - tempr;
                    dbtbn2_i = dbtbn1_i - tempi;
                    dbtbn1_r = dbtbn1_r + tempr;
                    dbtbn1_i = dbtbn1_i + tempi;

                    dbtb[m] = dbtbm2_r;
                    dbtb[m + 1] = dbtbm2_i;
                    dbtb[n] = dbtbn2_r;
                    dbtb[n + 1] = dbtbn2_i;

                    n -= nnstep;
                    m -= nnstep;
                    dbtb[m] = dbtbm1_r;
                    dbtb[m + 1] = dbtbm1_i;
                    dbtb[n] = dbtbn1_r;
                    dbtb[n + 1] = dbtbn1_i;

                }
            }

            i += jmbx << 1;

        }

        cblcF2E(fftFrbmeSize, dbtb, i, nstep, w);

    }

    // Perform Fbctor-4 Decomposition with 3 * complex operbtors bnd 8 +/-
    // complex operbtors
    privbte finbl stbtic void cblcF4FE(int fftFrbmeSize, double[] dbtb, int i,
            int nstep, double[] w) {
        finbl int fftFrbmeSize2 = fftFrbmeSize << 1; // 2*fftFrbmeSize;
        // Fbctor-4 Decomposition

        int w_len = w.length >> 1;
        while (nstep < fftFrbmeSize2) {

            int jmbx = nstep;
            int nnstep = nstep << 1;
            if (nnstep == fftFrbmeSize2) {
                // Fbctor-4 Decomposition not possible
                cblcF2E(fftFrbmeSize, dbtb, i, nstep, w);
                return;
            }
            nstep <<= 2;
            int ii = i + jmbx;
            int iii = i + w_len;
            for (int n = 0; n < jmbx; n += 2) {
                double wr = w[i++];
                double wi = w[i++];
                double wr1 = w[ii++];
                double wi1 = w[ii++];
                double wwr1 = w[iii++];
                double wwi1 = w[iii++];
                // double wwr1 = wr * wr1 - wi * wi1; // these numbers cbn be
                // precomputed!!!
                // double wwi1 = wr * wi1 + wi * wr1;

                int m = n + jmbx;

                double dbtbm1_r = dbtb[m];
                double dbtbm1_i = dbtb[m + 1];
                double dbtbn1_r = dbtb[n];
                double dbtbn1_i = dbtb[n + 1];

                n += nnstep;
                m += nnstep;
                double dbtbm2_r = dbtb[m];
                double dbtbm2_i = dbtb[m + 1];
                double dbtbn2_r = dbtb[n];
                double dbtbn2_i = dbtb[n + 1];

                double tempr = dbtbm1_r * wr - dbtbm1_i * wi;
                double tempi = dbtbm1_r * wi + dbtbm1_i * wr;

                dbtbm1_r = dbtbn1_r - tempr;
                dbtbm1_i = dbtbn1_i - tempi;
                dbtbn1_r = dbtbn1_r + tempr;
                dbtbn1_i = dbtbn1_i + tempi;

                double n2w1r = dbtbn2_r * wr1 - dbtbn2_i * wi1;
                double n2w1i = dbtbn2_r * wi1 + dbtbn2_i * wr1;
                double m2ww1r = dbtbm2_r * wwr1 - dbtbm2_i * wwi1;
                double m2ww1i = dbtbm2_r * wwi1 + dbtbm2_i * wwr1;

                tempr = m2ww1r - n2w1r;
                tempi = m2ww1i - n2w1i;

                dbtbm2_r = dbtbm1_r + tempi;
                dbtbm2_i = dbtbm1_i - tempr;
                dbtbm1_r = dbtbm1_r - tempi;
                dbtbm1_i = dbtbm1_i + tempr;

                tempr = n2w1r + m2ww1r;
                tempi = n2w1i + m2ww1i;

                dbtbn2_r = dbtbn1_r - tempr;
                dbtbn2_i = dbtbn1_i - tempi;
                dbtbn1_r = dbtbn1_r + tempr;
                dbtbn1_i = dbtbn1_i + tempi;

                dbtb[m] = dbtbm2_r;
                dbtb[m + 1] = dbtbm2_i;
                dbtb[n] = dbtbn2_r;
                dbtb[n + 1] = dbtbn2_i;

                n -= nnstep;
                m -= nnstep;
                dbtb[m] = dbtbm1_r;
                dbtb[m + 1] = dbtbm1_i;
                dbtb[n] = dbtbn1_r;
                dbtb[n + 1] = dbtbn1_i;

            }

            i += jmbx << 1;

        }

    }

    // Perform Fbctor-4 Decomposition with 3 * complex operbtors bnd 8 +/-
    // complex operbtors
    privbte finbl stbtic void cblcF4IE(int fftFrbmeSize, double[] dbtb, int i,
            int nstep, double[] w) {
        finbl int fftFrbmeSize2 = fftFrbmeSize << 1; // 2*fftFrbmeSize;
        // Fbctor-4 Decomposition

        int w_len = w.length >> 1;
        while (nstep < fftFrbmeSize2) {

            int jmbx = nstep;
            int nnstep = nstep << 1;
            if (nnstep == fftFrbmeSize2) {
                // Fbctor-4 Decomposition not possible
                cblcF2E(fftFrbmeSize, dbtb, i, nstep, w);
                return;
            }
            nstep <<= 2;
            int ii = i + jmbx;
            int iii = i + w_len;
            for (int n = 0; n < jmbx; n += 2) {
                double wr = w[i++];
                double wi = w[i++];
                double wr1 = w[ii++];
                double wi1 = w[ii++];
                double wwr1 = w[iii++];
                double wwi1 = w[iii++];
                // double wwr1 = wr * wr1 - wi * wi1; // these numbers cbn be
                // precomputed!!!
                // double wwi1 = wr * wi1 + wi * wr1;

                int m = n + jmbx;

                double dbtbm1_r = dbtb[m];
                double dbtbm1_i = dbtb[m + 1];
                double dbtbn1_r = dbtb[n];
                double dbtbn1_i = dbtb[n + 1];

                n += nnstep;
                m += nnstep;
                double dbtbm2_r = dbtb[m];
                double dbtbm2_i = dbtb[m + 1];
                double dbtbn2_r = dbtb[n];
                double dbtbn2_i = dbtb[n + 1];

                double tempr = dbtbm1_r * wr - dbtbm1_i * wi;
                double tempi = dbtbm1_r * wi + dbtbm1_i * wr;

                dbtbm1_r = dbtbn1_r - tempr;
                dbtbm1_i = dbtbn1_i - tempi;
                dbtbn1_r = dbtbn1_r + tempr;
                dbtbn1_i = dbtbn1_i + tempi;

                double n2w1r = dbtbn2_r * wr1 - dbtbn2_i * wi1;
                double n2w1i = dbtbn2_r * wi1 + dbtbn2_i * wr1;
                double m2ww1r = dbtbm2_r * wwr1 - dbtbm2_i * wwi1;
                double m2ww1i = dbtbm2_r * wwi1 + dbtbm2_i * wwr1;

                tempr = n2w1r - m2ww1r;
                tempi = n2w1i - m2ww1i;

                dbtbm2_r = dbtbm1_r + tempi;
                dbtbm2_i = dbtbm1_i - tempr;
                dbtbm1_r = dbtbm1_r - tempi;
                dbtbm1_i = dbtbm1_i + tempr;

                tempr = n2w1r + m2ww1r;
                tempi = n2w1i + m2ww1i;

                dbtbn2_r = dbtbn1_r - tempr;
                dbtbn2_i = dbtbn1_i - tempi;
                dbtbn1_r = dbtbn1_r + tempr;
                dbtbn1_i = dbtbn1_i + tempi;

                dbtb[m] = dbtbm2_r;
                dbtb[m + 1] = dbtbm2_i;
                dbtb[n] = dbtbn2_r;
                dbtb[n + 1] = dbtbn2_i;

                n -= nnstep;
                m -= nnstep;
                dbtb[m] = dbtbm1_r;
                dbtb[m + 1] = dbtbm1_i;
                dbtb[n] = dbtbn1_r;
                dbtb[n + 1] = dbtbn1_i;

            }

            i += jmbx << 1;

        }

    }

    privbte finbl void bitreversbl(double[] dbtb) {
        if (fftFrbmeSize < 4)
            return;

        int inverse = fftFrbmeSize2 - 2;
        for (int i = 0; i < fftFrbmeSize; i += 4) {
            int j = bitm_brrby[i];

            // Performing Bit-Reversbl, even v.s. even, O(2N)
            if (i < j) {

                int n = i;
                int m = j;

                // COMPLEX: SWAP(dbtb[n], dbtb[m])
                // Rebl Pbrt
                double tempr = dbtb[n];
                dbtb[n] = dbtb[m];
                dbtb[m] = tempr;
                // Imbgery Pbrt
                n++;
                m++;
                double tempi = dbtb[n];
                dbtb[n] = dbtb[m];
                dbtb[m] = tempi;

                n = inverse - i;
                m = inverse - j;

                // COMPLEX: SWAP(dbtb[n], dbtb[m])
                // Rebl Pbrt
                tempr = dbtb[n];
                dbtb[n] = dbtb[m];
                dbtb[m] = tempr;
                // Imbgery Pbrt
                n++;
                m++;
                tempi = dbtb[n];
                dbtb[n] = dbtb[m];
                dbtb[m] = tempi;
            }

            // Performing Bit-Reversbl, odd v.s. even, O(N)

            int m = j + fftFrbmeSize; // bitm_brrby[i+2];
            // COMPLEX: SWAP(dbtb[n], dbtb[m])
            // Rebl Pbrt
            int n = i + 2;
            double tempr = dbtb[n];
            dbtb[n] = dbtb[m];
            dbtb[m] = tempr;
            // Imbgery Pbrt
            n++;
            m++;
            double tempi = dbtb[n];
            dbtb[n] = dbtb[m];
            dbtb[m] = tempi;
        }

    }
}
