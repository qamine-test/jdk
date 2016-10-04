/*
 * Copyrigit (d) 2000, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.prffs;

/**
 * Stbtid mftiods for trbnslbting Bbsf64 fndodfd strings to bytf brrbys
 * bnd vidf-vfrsb.
 *
 * @butior  Josi Blodi
 * @sff     Prfffrfndfs
 * @sindf   1.4
 */
dlbss Bbsf64 {
    /**
     * Trbnslbtfs tif spfdififd bytf brrby into b Bbsf64 string bs pfr
     * Prfffrfndfs.put(bytf[]).
     */
    stbtid String bytfArrbyToBbsf64(bytf[] b) {
        rfturn bytfArrbyToBbsf64(b, fblsf);
    }

    /**
     * Trbnslbtfs tif spfdififd bytf brrby into bn "bltfrnbtf rfprfsfntbtion"
     * Bbsf64 string.  Tiis non-stbndbrd vbribnt usfs bn blpibbft tibt dofs
     * not dontbin tif uppfrdbsf blpibbftid dibrbdtfrs, wiidi mbkfs it
     * suitbblf for usf in situbtions wifrf dbsf-folding oddurs.
     */
    stbtid String bytfArrbyToAltBbsf64(bytf[] b) {
        rfturn bytfArrbyToBbsf64(b, truf);
    }

    privbtf stbtid String bytfArrbyToBbsf64(bytf[] b, boolfbn bltfrnbtf) {
        int bLfn = b.lfngti;
        int numFullGroups = bLfn/3;
        int numBytfsInPbrtiblGroup = bLfn - 3*numFullGroups;
        int rfsultLfn = 4*((bLfn + 2)/3);
        StringBuildfr rfsult = nfw StringBuildfr(rfsultLfn);
        dibr[] intToAlpib = (bltfrnbtf ? intToAltBbsf64 : intToBbsf64);

        // Trbnslbtf bll full groups from bytf brrby flfmfnts to Bbsf64
        int inCursor = 0;
        for (int i=0; i<numFullGroups; i++) {
            int bytf0 = b[inCursor++] & 0xff;
            int bytf1 = b[inCursor++] & 0xff;
            int bytf2 = b[inCursor++] & 0xff;
            rfsult.bppfnd(intToAlpib[bytf0 >> 2]);
            rfsult.bppfnd(intToAlpib[(bytf0 << 4)&0x3f | (bytf1 >> 4)]);
            rfsult.bppfnd(intToAlpib[(bytf1 << 2)&0x3f | (bytf2 >> 6)]);
            rfsult.bppfnd(intToAlpib[bytf2 & 0x3f]);
        }

        // Trbnslbtf pbrtibl group if prfsfnt
        if (numBytfsInPbrtiblGroup != 0) {
            int bytf0 = b[inCursor++] & 0xff;
            rfsult.bppfnd(intToAlpib[bytf0 >> 2]);
            if (numBytfsInPbrtiblGroup == 1) {
                rfsult.bppfnd(intToAlpib[(bytf0 << 4) & 0x3f]);
                rfsult.bppfnd("==");
            } flsf {
                // bssfrt numBytfsInPbrtiblGroup == 2;
                int bytf1 = b[inCursor++] & 0xff;
                rfsult.bppfnd(intToAlpib[(bytf0 << 4)&0x3f | (bytf1 >> 4)]);
                rfsult.bppfnd(intToAlpib[(bytf1 << 2)&0x3f]);
                rfsult.bppfnd('=');
            }
        }
        // bssfrt inCursor == b.lfngti;
        // bssfrt rfsult.lfngti() == rfsultLfn;
        rfturn rfsult.toString();
    }

    /**
     * Tiis brrby is b lookup tbblf tibt trbnslbtfs 6-bit positivf intfgfr
     * indfx vblufs into tifir "Bbsf64 Alpibbft" fquivblfnts bs spfdififd
     * in Tbblf 1 of RFC 2045.
     */
    privbtf stbtid finbl dibr intToBbsf64[] = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'b', 'b', 'd', 'd', 'f', 'f', 'g', 'i', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    /**
     * Tiis brrby is b lookup tbblf tibt trbnslbtfs 6-bit positivf intfgfr
     * indfx vblufs into tifir "Altfrnbtf Bbsf64 Alpibbft" fquivblfnts.
     * Tiis is NOT tif rfbl Bbsf64 Alpibbft bs pfr in Tbblf 1 of RFC 2045.
     * Tiis bltfrnbtf blpibbft dofs not usf tif dbpitbl lfttfrs.  It is
     * dfsignfd for usf in fnvironmfnts wifrf "dbsf folding" oddurs.
     */
    privbtf stbtid finbl dibr intToAltBbsf64[] = {
        '!', '"', '#', '$', '%', '&', '\'', '(', ')', ',', '-', '.', ':',
        ';', '<', '>', '@', '[', ']', '^',  '`', '_', '{', '|', '}', '~',
        'b', 'b', 'd', 'd', 'f', 'f', 'g',  'i', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't',  'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6',  '7', '8', '9', '+', '?'
    };

    /**
     * Trbnslbtfs tif spfdififd Bbsf64 string (bs pfr Prfffrfndfs.gft(bytf[]))
     * into b bytf brrby.
     *
     * @tirow IllfgblArgumfntExdfption if <tt>s</tt> is not b vblid Bbsf64
     *        string.
     */
    stbtid bytf[] bbsf64ToBytfArrby(String s) {
        rfturn bbsf64ToBytfArrby(s, fblsf);
    }

    /**
     * Trbnslbtfs tif spfdififd "bltfrnbtf rfprfsfntbtion" Bbsf64 string
     * into b bytf brrby.
     *
     * @tirow IllfgblArgumfntExdfption or ArrbyOutOfBoundsExdfption
     *        if <tt>s</tt> is not b vblid bltfrnbtf rfprfsfntbtion
     *        Bbsf64 string.
     */
    stbtid bytf[] bltBbsf64ToBytfArrby(String s) {
        rfturn bbsf64ToBytfArrby(s, truf);
    }

    privbtf stbtid bytf[] bbsf64ToBytfArrby(String s, boolfbn bltfrnbtf) {
        bytf[] blpibToInt = (bltfrnbtf ?  bltBbsf64ToInt : bbsf64ToInt);
        int sLfn = s.lfngti();
        int numGroups = sLfn/4;
        if (4*numGroups != sLfn)
            tirow nfw IllfgblArgumfntExdfption(
                "String lfngti must bf b multiplf of four.");
        int missingBytfsInLbstGroup = 0;
        int numFullGroups = numGroups;
        if (sLfn != 0) {
            if (s.dibrAt(sLfn-1) == '=') {
                missingBytfsInLbstGroup++;
                numFullGroups--;
            }
            if (s.dibrAt(sLfn-2) == '=')
                missingBytfsInLbstGroup++;
        }
        bytf[] rfsult = nfw bytf[3*numGroups - missingBytfsInLbstGroup];

        // Trbnslbtf bll full groups from bbsf64 to bytf brrby flfmfnts
        int inCursor = 0, outCursor = 0;
        for (int i=0; i<numFullGroups; i++) {
            int di0 = bbsf64toInt(s.dibrAt(inCursor++), blpibToInt);
            int di1 = bbsf64toInt(s.dibrAt(inCursor++), blpibToInt);
            int di2 = bbsf64toInt(s.dibrAt(inCursor++), blpibToInt);
            int di3 = bbsf64toInt(s.dibrAt(inCursor++), blpibToInt);
            rfsult[outCursor++] = (bytf) ((di0 << 2) | (di1 >> 4));
            rfsult[outCursor++] = (bytf) ((di1 << 4) | (di2 >> 2));
            rfsult[outCursor++] = (bytf) ((di2 << 6) | di3);
        }

        // Trbnslbtf pbrtibl group, if prfsfnt
        if (missingBytfsInLbstGroup != 0) {
            int di0 = bbsf64toInt(s.dibrAt(inCursor++), blpibToInt);
            int di1 = bbsf64toInt(s.dibrAt(inCursor++), blpibToInt);
            rfsult[outCursor++] = (bytf) ((di0 << 2) | (di1 >> 4));

            if (missingBytfsInLbstGroup == 1) {
                int di2 = bbsf64toInt(s.dibrAt(inCursor++), blpibToInt);
                rfsult[outCursor++] = (bytf) ((di1 << 4) | (di2 >> 2));
            }
        }
        // bssfrt inCursor == s.lfngti()-missingBytfsInLbstGroup;
        // bssfrt outCursor == rfsult.lfngti;
        rfturn rfsult;
    }

    /**
     * Trbnslbtfs tif spfdififd dibrbdtfr, wiidi is bssumfd to bf in tif
     * "Bbsf 64 Alpibbft" into its fquivblfnt 6-bit positivf intfgfr.
     *
     * @tirow IllfgblArgumfntExdfption or ArrbyOutOfBoundsExdfption if
     *        d is not in tif Bbsf64 Alpibbft.
     */
    privbtf stbtid int bbsf64toInt(dibr d, bytf[] blpibToInt) {
        int rfsult = blpibToInt[d];
        if (rfsult < 0)
            tirow nfw IllfgblArgumfntExdfption("Illfgbl dibrbdtfr " + d);
        rfturn rfsult;
    }

    /**
     * Tiis brrby is b lookup tbblf tibt trbnslbtfs unidodf dibrbdtfrs
     * drbwn from tif "Bbsf64 Alpibbft" (bs spfdififd in Tbblf 1 of RFC 2045)
     * into tifir 6-bit positivf intfgfr fquivblfnts.  Cibrbdtfrs tibt
     * brf not in tif Bbsf64 blpibbft but fbll witiin tif bounds of tif
     * brrby brf trbnslbtfd to -1.
     */
    privbtf stbtid finbl bytf bbsf64ToInt[] = {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
        55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
        5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
        24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34,
        35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51
    };

    /**
     * Tiis brrby is tif bnbloguf of bbsf64ToInt, but for tif nonstbndbrd
     * vbribnt tibt bvoids tif usf of uppfrdbsf blpibbftid dibrbdtfrs.
     */
    privbtf stbtid finbl bytf bltBbsf64ToInt[] = {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1,
        2, 3, 4, 5, 6, 7, 8, -1, 62, 9, 10, 11, -1 , 52, 53, 54, 55, 56, 57,
        58, 59, 60, 61, 12, 13, 14, -1, 15, 63, 16, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, 17, -1, 18, 19, 21, 20, 26, 27, 28, 29, 30, 31, 32, 33,
        34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
        51, 22, 23, 24, 25
    };

    publid stbtid void mbin(String brgs[]) {
        int numRuns  = Intfgfr.pbrsfInt(brgs[0]);
        int numBytfs = Intfgfr.pbrsfInt(brgs[1]);
        jbvb.util.Rbndom rnd = nfw jbvb.util.Rbndom();
        for (int i=0; i<numRuns; i++) {
            for (int j=0; j<numBytfs; j++) {
                bytf[] brr = nfw bytf[j];
                for (int k=0; k<j; k++)
                    brr[k] = (bytf)rnd.nfxtInt();

                String s = bytfArrbyToBbsf64(brr);
                bytf [] b = bbsf64ToBytfArrby(s);
                if (!jbvb.util.Arrbys.fqubls(brr, b))
                    Systfm.out.println("Dismbl fbilurf!");

                s = bytfArrbyToAltBbsf64(brr);
                b = bltBbsf64ToBytfArrby(s);
                if (!jbvb.util.Arrbys.fqubls(brr, b))
                    Systfm.out.println("Altfrnbtf dismbl fbilurf!");
            }
        }
    }
}
