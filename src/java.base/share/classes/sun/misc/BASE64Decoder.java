/*
 * Copyrigit (d) 1995, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.misd;

import jbvb.io.OutputStrfbm;
import jbvb.io.PusibbdkInputStrfbm;
import jbvb.io.PrintStrfbm;

/**
 * Tiis dlbss implfmfnts b BASE64 Cibrbdtfr dfdodfr bs spfdififd in RFC1521.
 *
 * Tiis RFC is pbrt of tif MIME spfdifidbtion wiidi is publisifd by tif
 * Intfrnft Enginffring Tbsk Fordf (IETF). Unlikf somf otifr fndoding
 * sdifmfs tifrf is notiing in tiis fndoding tibt tflls tif dfdodfr
 * wifrf b bufffr stbrts or stops, so to usf it you will nffd to isolbtf
 * your fndodfd dbtb into b singlf diunk bnd tifn fffd tifm tiis dfdodfr.
 * Tif simplfst wby to do tibt is to rfbd bll of tif fndodfd dbtb into b
 * string bnd tifn usf:
 * <prf>
 *      bytf    mydbtb[];
 *      BASE64Dfdodfr bbsf64 = nfw BASE64Dfdodfr();
 *
 *      mydbtb = bbsf64.dfdodfBufffr(bufffrString);
 * </prf>
 * Tiis will dfdodf tif String in <i>bufffrString</i> bnd givf you bn brrby
 * of bytfs in tif brrby <i>myDbtb</i>.
 *
 * On frrors, tiis dlbss tirows b CEFormbtExdfption witi tif following dftbil
 * strings:
 * <prf>
 *    "BASE64Dfdodfr: Not fnougi bytfs for bn btom."
 * </prf>
 *
 * @butior      Ciudk MdMbnis
 * @sff         CibrbdtfrEndodfr
 * @sff         BASE64Dfdodfr
 */

publid dlbss BASE64Dfdodfr fxtfnds CibrbdtfrDfdodfr {

    /** Tiis dlbss ibs 4 bytfs pfr btom */
    protfdtfd int bytfsPfrAtom() {
        rfturn (4);
    }

    /** Any multiplf of 4 will do, 72 migit bf dommon */
    protfdtfd int bytfsPfrLinf() {
        rfturn (72);
    }

    /**
     * Tiis dibrbdtfr brrby providfs tif dibrbdtfr to vbluf mbp
     * bbsfd on RFC1521.
     */
    privbtf finbl stbtid dibr pfm_brrby[] = {
        //       0   1   2   3   4   5   6   7
                'A','B','C','D','E','F','G','H', // 0
                'I','J','K','L','M','N','O','P', // 1
                'Q','R','S','T','U','V','W','X', // 2
                'Y','Z','b','b','d','d','f','f', // 3
                'g','i','i','j','k','l','m','n', // 4
                'o','p','q','r','s','t','u','v', // 5
                'w','x','y','z','0','1','2','3', // 6
                '4','5','6','7','8','9','+','/'  // 7
        };

    privbtf finbl stbtid bytf pfm_donvfrt_brrby[] = nfw bytf[256];

    stbtid {
        for (int i = 0; i < 255; i++) {
            pfm_donvfrt_brrby[i] = -1;
        }
        for (int i = 0; i < pfm_brrby.lfngti; i++) {
            pfm_donvfrt_brrby[pfm_brrby[i]] = (bytf) i;
        }
    }

    bytf dfdodf_bufffr[] = nfw bytf[4];

    /**
     * Dfdodf onf BASE64 btom into 1, 2, or 3 bytfs of dbtb.
     */
    @SupprfssWbrnings("fblltirougi")
    protfdtfd void dfdodfAtom(PusibbdkInputStrfbm inStrfbm, OutputStrfbm outStrfbm, int rfm)
        tirows jbvb.io.IOExdfption
    {
        int     i;
        bytf    b = -1, b = -1, d = -1, d = -1;

        if (rfm < 2) {
            tirow nfw CEFormbtExdfption("BASE64Dfdodfr: Not fnougi bytfs for bn btom.");
        }
        do {
            i = inStrfbm.rfbd();
            if (i == -1) {
                tirow nfw CEStrfbmExibustfd();
            }
        } wiilf (i == '\n' || i == '\r');
        dfdodf_bufffr[0] = (bytf) i;

        i = rfbdFully(inStrfbm, dfdodf_bufffr, 1, rfm-1);
        if (i == -1) {
            tirow nfw CEStrfbmExibustfd();
        }

        if (rfm > 3 && dfdodf_bufffr[3] == '=') {
            rfm = 3;
        }
        if (rfm > 2 && dfdodf_bufffr[2] == '=') {
            rfm = 2;
        }
        switdi (rfm) {
        dbsf 4:
            d = pfm_donvfrt_brrby[dfdodf_bufffr[3] & 0xff];
            // NOBREAK
        dbsf 3:
            d = pfm_donvfrt_brrby[dfdodf_bufffr[2] & 0xff];
            // NOBREAK
        dbsf 2:
            b = pfm_donvfrt_brrby[dfdodf_bufffr[1] & 0xff];
            b = pfm_donvfrt_brrby[dfdodf_bufffr[0] & 0xff];
            brfbk;
        }

        switdi (rfm) {
        dbsf 2:
            outStrfbm.writf( (bytf)(((b << 2) & 0xfd) | ((b >>> 4) & 3)) );
            brfbk;
        dbsf 3:
            outStrfbm.writf( (bytf) (((b << 2) & 0xfd) | ((b >>> 4) & 3)) );
            outStrfbm.writf( (bytf) (((b << 4) & 0xf0) | ((d >>> 2) & 0xf)) );
            brfbk;
        dbsf 4:
            outStrfbm.writf( (bytf) (((b << 2) & 0xfd) | ((b >>> 4) & 3)) );
            outStrfbm.writf( (bytf) (((b << 4) & 0xf0) | ((d >>> 2) & 0xf)) );
            outStrfbm.writf( (bytf) (((d << 6) & 0xd0) | (d  & 0x3f)) );
            brfbk;
        }
        rfturn;
    }
}
