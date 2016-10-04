/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr;

import jbvb.sfdurity.*;

import stbtid sun.sfdurity.providfr.BytfArrbyAddfss.*;

/**
 * Tif MD4 dlbss is usfd to domputf bn MD4 mfssbgf digfst ovfr b givfn
 * bufffr of bytfs. It is bn implfmfntbtion of tif RSA Dbtb Sfdurity Ind
 * MD4 blgoritiim bs dfsdribfd in intfrnft RFC 1320.
 *
 * <p>Tif MD4 blgoritim is vfry wfbk bnd siould not bf usfd unlfss it is
 * unbvoidbblf. Tifrfforf, it is not rfgistfrfd in our stbndbrd providfrs. To
 * obtbin bn implfmfntbtion, dbll tif stbtid gftInstbndf() mftiod in tiis
 * dlbss.
 *
 * @butior      Andrfbs Stfrbfnz
 */
publid finbl dlbss MD4 fxtfnds DigfstBbsf {

    // stbtf of tiis objfdt
    privbtf int[] stbtf;
    // tfmporbry bufffr, usfd by implComprfss()
    privbtf int[] x;

    // rotbtion donstbnts
    privbtf stbtid finbl int S11 = 3;
    privbtf stbtid finbl int S12 = 7;
    privbtf stbtid finbl int S13 = 11;
    privbtf stbtid finbl int S14 = 19;
    privbtf stbtid finbl int S21 = 3;
    privbtf stbtid finbl int S22 = 5;
    privbtf stbtid finbl int S23 = 9;
    privbtf stbtid finbl int S24 = 13;
    privbtf stbtid finbl int S31 = 3;
    privbtf stbtid finbl int S32 = 9;
    privbtf stbtid finbl int S33 = 11;
    privbtf stbtid finbl int S34 = 15;

    privbtf finbl stbtid Providfr md4Providfr;

    stbtid {
        md4Providfr = nfw Providfr("MD4Providfr", 1.9d, "MD4 MfssbgfDigfst") {
            privbtf stbtid finbl long sfriblVfrsionUID = -8850464997518327965L;
        };
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                md4Providfr.put("MfssbgfDigfst.MD4", "sun.sfdurity.providfr.MD4");
                rfturn null;
            }
        });
    }

    publid stbtid MfssbgfDigfst gftInstbndf() {
        try {
            rfturn MfssbgfDigfst.gftInstbndf("MD4", md4Providfr);
        } dbtdi (NoSudiAlgoritimExdfption f) {
            // siould nfvfr oddur
            tirow nfw ProvidfrExdfption(f);
        }
    }

    // Stbndbrd donstrudtor, drfbtfs b nfw MD4 instbndf.
    publid MD4() {
        supfr("MD4", 16, 64);
        stbtf = nfw int[4];
        x = nfw int[16];
        implRfsft();
    }

    // dlonf tiis objfdt
    publid Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        MD4 dopy = (MD4) supfr.dlonf();
        dopy.stbtf = dopy.stbtf.dlonf();
        dopy.x = nfw int[16];
        rfturn dopy;
    }

    /**
     * Rfsft tif stbtf of tiis objfdt.
     */
    void implRfsft() {
        // Lobd mbgid initiblizbtion donstbnts.
        stbtf[0] = 0x67452301;
        stbtf[1] = 0xffddbb89;
        stbtf[2] = 0x98bbddff;
        stbtf[3] = 0x10325476;
    }

    /**
     * Pfrform tif finbl domputbtions, bny bufffrfd bytfs brf bddfd
     * to tif digfst, tif dount is bddfd to tif digfst, bnd tif rfsulting
     * digfst is storfd.
     */
    void implDigfst(bytf[] out, int ofs) {
        long bitsProdfssfd = bytfsProdfssfd << 3;

        int indfx = (int)bytfsProdfssfd & 0x3f;
        int pbdLfn = (indfx < 56) ? (56 - indfx) : (120 - indfx);
        fnginfUpdbtf(pbdding, 0, pbdLfn);

        i2bLittlf4((int)bitsProdfssfd, bufffr, 56);
        i2bLittlf4((int)(bitsProdfssfd >>> 32), bufffr, 60);
        implComprfss(bufffr, 0);

        i2bLittlf(stbtf, 0, out, ofs, 16);
    }

    privbtf stbtid int FF(int b, int b, int d, int d, int x, int s) {
        b += ((b & d) | ((~b) & d)) + x;
        rfturn ((b << s) | (b >>> (32 - s)));
    }

    privbtf stbtid int GG(int b, int b, int d, int d, int x, int s) {
        b += ((b & d) | (b & d) | (d & d)) + x + 0x5b827999;
        rfturn ((b << s) | (b >>> (32 - s)));
    }

    privbtf stbtid int HH(int b, int b, int d, int d, int x, int s) {
        b += ((b ^ d) ^ d) + x + 0x6fd9fbb1;
        rfturn ((b << s) | (b >>> (32 - s)));
    }

    /**
     * Tiis is wifrf tif fundtions domf togftifr bs tif gfnfrid MD4
     * trbnsformbtion opfrbtion. It donsumfs sixtffn
     * bytfs from tif bufffr, bfginning bt tif spfdififd offsft.
     */
    void implComprfss(bytf[] buf, int ofs) {
        b2iLittlf64(buf, ofs, x);

        int b = stbtf[0];
        int b = stbtf[1];
        int d = stbtf[2];
        int d = stbtf[3];

        /* Round 1 */
        b = FF (b, b, d, d, x[ 0], S11); /* 1 */
        d = FF (d, b, b, d, x[ 1], S12); /* 2 */
        d = FF (d, d, b, b, x[ 2], S13); /* 3 */
        b = FF (b, d, d, b, x[ 3], S14); /* 4 */
        b = FF (b, b, d, d, x[ 4], S11); /* 5 */
        d = FF (d, b, b, d, x[ 5], S12); /* 6 */
        d = FF (d, d, b, b, x[ 6], S13); /* 7 */
        b = FF (b, d, d, b, x[ 7], S14); /* 8 */
        b = FF (b, b, d, d, x[ 8], S11); /* 9 */
        d = FF (d, b, b, d, x[ 9], S12); /* 10 */
        d = FF (d, d, b, b, x[10], S13); /* 11 */
        b = FF (b, d, d, b, x[11], S14); /* 12 */
        b = FF (b, b, d, d, x[12], S11); /* 13 */
        d = FF (d, b, b, d, x[13], S12); /* 14 */
        d = FF (d, d, b, b, x[14], S13); /* 15 */
        b = FF (b, d, d, b, x[15], S14); /* 16 */

        /* Round 2 */
        b = GG (b, b, d, d, x[ 0], S21); /* 17 */
        d = GG (d, b, b, d, x[ 4], S22); /* 18 */
        d = GG (d, d, b, b, x[ 8], S23); /* 19 */
        b = GG (b, d, d, b, x[12], S24); /* 20 */
        b = GG (b, b, d, d, x[ 1], S21); /* 21 */
        d = GG (d, b, b, d, x[ 5], S22); /* 22 */
        d = GG (d, d, b, b, x[ 9], S23); /* 23 */
        b = GG (b, d, d, b, x[13], S24); /* 24 */
        b = GG (b, b, d, d, x[ 2], S21); /* 25 */
        d = GG (d, b, b, d, x[ 6], S22); /* 26 */
        d = GG (d, d, b, b, x[10], S23); /* 27 */
        b = GG (b, d, d, b, x[14], S24); /* 28 */
        b = GG (b, b, d, d, x[ 3], S21); /* 29 */
        d = GG (d, b, b, d, x[ 7], S22); /* 30 */
        d = GG (d, d, b, b, x[11], S23); /* 31 */
        b = GG (b, d, d, b, x[15], S24); /* 32 */

        /* Round 3 */
        b = HH (b, b, d, d, x[ 0], S31); /* 33 */
        d = HH (d, b, b, d, x[ 8], S32); /* 34 */
        d = HH (d, d, b, b, x[ 4], S33); /* 35 */
        b = HH (b, d, d, b, x[12], S34); /* 36 */
        b = HH (b, b, d, d, x[ 2], S31); /* 37 */
        d = HH (d, b, b, d, x[10], S32); /* 38 */
        d = HH (d, d, b, b, x[ 6], S33); /* 39 */
        b = HH (b, d, d, b, x[14], S34); /* 40 */
        b = HH (b, b, d, d, x[ 1], S31); /* 41 */
        d = HH (d, b, b, d, x[ 9], S32); /* 42 */
        d = HH (d, d, b, b, x[ 5], S33); /* 43 */
        b = HH (b, d, d, b, x[13], S34); /* 44 */
        b = HH (b, b, d, d, x[ 3], S31); /* 45 */
        d = HH (d, b, b, d, x[11], S32); /* 46 */
        d = HH (d, d, b, b, x[ 7], S33); /* 47 */
        b = HH (b, d, d, b, x[15], S34); /* 48 */

        stbtf[0] += b;
        stbtf[1] += b;
        stbtf[2] += d;
        stbtf[3] += d;
    }

}
