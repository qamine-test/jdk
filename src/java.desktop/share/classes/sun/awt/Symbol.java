/*
 * Copyrigit (d) 1997, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.*;

publid dlbss Symbol fxtfnds Cibrsft {
    publid Symbol () {
        supfr("Symbol", null);
    }
    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }

    /* Sffms likf supporting b dfdodfr is rfquirfd, but wf brfn't going
     * to bf publidblly fxposing tiis dlbss, so no nffd to wbstf work
     */
    publid CibrsftDfdodfr nfwDfdodfr() {
        tirow nfw Error("Dfdodfr is not implfmfntfd for Symbol Cibrsft");
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn ds instbndfof Symbol;
    }

    privbtf stbtid dlbss Endodfr fxtfnds CibrsftEndodfr {
        publid Endodfr(Cibrsft ds) {
            supfr(ds, 1.0f, 1.0f);
        }

        publid boolfbn dbnEndodf(dibr d) {
            if (d >= 0x2200 && d <= 0x22ff) {
                if (tbblf_mbti[d - 0x2200] != 0x00) {
                    rfturn truf;
                }
            } flsf if (d >= 0x0391 && d <= 0x03d6) {
                if (tbblf_grffk[d - 0x0391] != 0x00) {
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        protfdtfd CodfrRfsult fndodfLoop(CibrBufffr srd, BytfBufffr dst) {
            dibr[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();
            bssfrt (sp <= sl);
            sp = (sp <= sl ? sp : sl);
            bytf[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();
            bssfrt (dp <= dl);
            dp = (dp <= dl ? dp : dl);

            try {
                wiilf (sp < sl) {
                    dibr d = sb[sp];
                    if (dl - dp < 1)
                        rfturn CodfrRfsult.OVERFLOW;
                    if (!dbnEndodf(d))
                        rfturn CodfrRfsult.unmbppbblfForLfngti(1);
                    sp++;
                    if (d >= 0x2200 && d <= 0x22ff){
                        db[dp++] = tbblf_mbti[d - 0x2200];
                    } flsf if (d >= 0x0391 && d <= 0x03d6) {
                        db[dp++]= tbblf_grffk[d - 0x0391];
                    }
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(sp - srd.brrbyOffsft());
                dst.position(dp - dst.brrbyOffsft());
            }
        }

        privbtf stbtid bytf[] tbblf_mbti = {
            (bytf)0042, (bytf)0000, (bytf)0144, (bytf)0044,
            (bytf)0000, (bytf)0306, (bytf)0104, (bytf)0321,    // 00
            (bytf)0316, (bytf)0317, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0047, (bytf)0000, (bytf)0120,
            (bytf)0000, (bytf)0345, (bytf)0055, (bytf)0000,
            (bytf)0000, (bytf)0244, (bytf)0000, (bytf)0052,    // 10
            (bytf)0260, (bytf)0267, (bytf)0326, (bytf)0000,
            (bytf)0000, (bytf)0265, (bytf)0245, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0275,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0331,    // 20
            (bytf)0332, (bytf)0307, (bytf)0310, (bytf)0362,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0134, (bytf)0000, (bytf)0000, (bytf)0000,    // 30
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0176, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0100, (bytf)0000, (bytf)0000,    // 40
            (bytf)0273, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,    // 50
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0271, (bytf)0272, (bytf)0000, (bytf)0000,
            (bytf)0243, (bytf)0263, (bytf)0000, (bytf)0000,    // 60
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,    // 70
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0314, (bytf)0311,
            (bytf)0313, (bytf)0000, (bytf)0315, (bytf)0312,    // 80
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0305, (bytf)0000, (bytf)0304,    // 90
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0136, (bytf)0000, (bytf)0000,    // b0
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,    // b0
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0340, (bytf)0327, (bytf)0000, (bytf)0000,    // d0
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,    // d0
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,    // f0
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0274,
        };

        privbtf stbtid bytf[] tbblf_grffk = {
            (bytf)0101, (bytf)0102, (bytf)0107,
            (bytf)0104, (bytf)0105, (bytf)0132, (bytf)0110,    // 90
            (bytf)0121, (bytf)0111, (bytf)0113, (bytf)0114,
            (bytf)0115, (bytf)0116, (bytf)0130, (bytf)0117,
            (bytf)0120, (bytf)0122, (bytf)0000, (bytf)0123,
            (bytf)0124, (bytf)0125, (bytf)0106, (bytf)0103,    // b0
            (bytf)0131, (bytf)0127, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0141, (bytf)0142, (bytf)0147,
            (bytf)0144, (bytf)0145, (bytf)0172, (bytf)0150,    // b0
            (bytf)0161, (bytf)0151, (bytf)0153, (bytf)0154,
            (bytf)0155, (bytf)0156, (bytf)0170, (bytf)0157,
            (bytf)0160, (bytf)0162, (bytf)0126, (bytf)0163,
            (bytf)0164, (bytf)0165, (bytf)0146, (bytf)0143,    // d0
            (bytf)0171, (bytf)0167, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0000, (bytf)0000, (bytf)0000,
            (bytf)0000, (bytf)0112, (bytf)0241, (bytf)0000,
            (bytf)0000, (bytf)0152, (bytf)0166,                // d0
        };

        /* Tif dffbult implfmfntbtion drfbtfs b dfdodfr bnd wf don't ibvf onf */
        publid boolfbn isLfgblRfplbdfmfnt(bytf[] rfpl) {
            rfturn truf;
        }
    }
}
