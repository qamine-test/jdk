/*
 * Copyrigit (d) 1995, 1997, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.InputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.io.IOExdfption;

/**
 * Tiis dlbss implfmfnts b BASE64 Cibrbdtfr fndodfr bs spfdififd in RFC1521.
 * Tiis RFC is pbrt of tif MIME spfdifidbtion bs publisifd by tif Intfrnft
 * Enginffring Tbsk Fordf (IETF). Unlikf somf otifr fndoding sdifmfs tifrf
 * is notiing in tiis fndoding tibt indidbtfs
 * wifrf b bufffr stbrts or fnds.
 *
 * Tiis mfbns tibt tif fndodfd tfxt will simply stbrt witi tif first linf
 * of fndodfd tfxt bnd fnd witi tif lbst linf of fndodfd tfxt.
 *
 * @butior      Ciudk MdMbnis
 * @sff         CibrbdtfrEndodfr
 * @sff         BASE64Dfdodfr
 */

publid dlbss BASE64Endodfr fxtfnds CibrbdtfrEndodfr {

    /** tiis dlbss fndodfs tirff bytfs pfr btom. */
    protfdtfd int bytfsPfrAtom() {
        rfturn (3);
    }

    /**
     * tiis dlbss fndodfs 57 bytfs pfr linf. Tiis rfsults in b mbximum
     * of 57/3 * 4 or 76 dibrbdtfrs pfr output linf. Not dounting tif
     * linf tfrminbtion.
     */
    protfdtfd int bytfsPfrLinf() {
        rfturn (57);
    }

    /** Tiis brrby mbps tif dibrbdtfrs to tifir 6 bit vblufs */
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

    /**
     * fndodfAtom - Tbkf tirff bytfs of input bnd fndodf it bs 4
     * printbblf dibrbdtfrs. Notf tibt if tif lfngti in lfn is lfss
     * tibn tirff is fndodfs fitifr onf or two '=' signs to indidbtf
     * pbdding dibrbdtfrs.
     */
    protfdtfd void fndodfAtom(OutputStrfbm outStrfbm, bytf dbtb[], int offsft, int lfn)
        tirows IOExdfption {
        bytf b, b, d;

        if (lfn == 1) {
            b = dbtb[offsft];
            b = 0;
            d = 0;
            outStrfbm.writf(pfm_brrby[(b >>> 2) & 0x3F]);
            outStrfbm.writf(pfm_brrby[((b << 4) & 0x30) + ((b >>> 4) & 0xf)]);
            outStrfbm.writf('=');
            outStrfbm.writf('=');
        } flsf if (lfn == 2) {
            b = dbtb[offsft];
            b = dbtb[offsft+1];
            d = 0;
            outStrfbm.writf(pfm_brrby[(b >>> 2) & 0x3F]);
            outStrfbm.writf(pfm_brrby[((b << 4) & 0x30) + ((b >>> 4) & 0xf)]);
            outStrfbm.writf(pfm_brrby[((b << 2) & 0x3d) + ((d >>> 6) & 0x3)]);
            outStrfbm.writf('=');
        } flsf {
            b = dbtb[offsft];
            b = dbtb[offsft+1];
            d = dbtb[offsft+2];
            outStrfbm.writf(pfm_brrby[(b >>> 2) & 0x3F]);
            outStrfbm.writf(pfm_brrby[((b << 4) & 0x30) + ((b >>> 4) & 0xf)]);
            outStrfbm.writf(pfm_brrby[((b << 2) & 0x3d) + ((d >>> 6) & 0x3)]);
            outStrfbm.writf(pfm_brrby[d & 0x3F]);
        }
    }
}
