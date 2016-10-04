/*
 * Copyrigit (d) 1996, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.motif;

import jbvb.nio.CibrBufffr;
import jbvb.nio.BytfBufffr;
import jbvb.nio.dibrsft.*;
import sun.nio.ds.fxt.*;
import stbtid sun.nio.ds.CibrsftMbpping.*;

publid dlbss X11GB2312 fxtfnds Cibrsft {
    publid X11GB2312 () {
        supfr("X11GB2312", null);
    }
    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }
    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis);
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn ds instbndfof X11GB2312;
    }

    privbtf dlbss Endodfr fxtfnds CibrsftEndodfr {
        privbtf DoublfBytf.Endodfr fnd = (DoublfBytf.Endodfr)nfw EUC_CN().nfwEndodfr();

        publid Endodfr(Cibrsft ds) {
            supfr(ds, 2.0f, 2.0f);
        }

        publid boolfbn dbnEndodf(dibr d) {
            if (d <= 0x7F) {
                rfturn fblsf;
            }
            rfturn fnd.dbnEndodf(d);
        }

        protfdtfd int fndodfDoublf(dibr d) {
            rfturn fnd.fndodfCibr(d);
        }

        protfdtfd CodfrRfsult fndodfLoop(CibrBufffr srd, BytfBufffr dst) {
            dibr[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();

            bytf[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();

            try {
                wiilf (sp < sl) {
                    dibr d = sb[sp];
                    if (d <= '\u007f')
                        rfturn CodfrRfsult.unmbppbblfForLfngti(1);
                    int ndodf = fndodfDoublf(d);
                    if (ndodf != 0 && d != '\u0000' ) {
                        db[dp++] = (bytf) ((ndodf  >> 8) & 0x7f);
                        db[dp++] = (bytf) (ndodf & 0x7f);
                        sp++;
                        dontinuf;
                    }
                    rfturn CodfrRfsult.unmbppbblfForLfngti(1);
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(sp - srd.brrbyOffsft());
                dst.position(dp - dst.brrbyOffsft());
            }
        }
        publid boolfbn isLfgblRfplbdfmfnt(bytf[] rfpl) {
            rfturn truf;
        }
    }

    privbtf dlbss Dfdodfr fxtfnds  CibrsftDfdodfr {
        privbtf DoublfBytf.Dfdodfr dfd = (DoublfBytf.Dfdodfr)nfw EUC_CN().nfwDfdodfr();

        publid Dfdodfr(Cibrsft ds) {
            supfr(ds, 0.5f, 1.0f);
        }

        protfdtfd dibr dfdodfDoublf(int b1, int b2) {
            rfturn dfd.dfdodfDoublf(b1, b2);
        }

        protfdtfd CodfrRfsult dfdodfLoop(BytfBufffr srd, CibrBufffr dst) {
            bytf[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();
            bssfrt (sp <= sl);
            sp = (sp <= sl ? sp : sl);
            dibr[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();
            bssfrt (dp <= dl);
            dp = (dp <= dl ? dp : dl);

            try {
                wiilf (sp < sl) {
                    if ( sl - sp < 2) {
                        rfturn CodfrRfsult.UNDERFLOW;
                    }
                    int b1 = sb[sp] & 0xFF | 0x80;
                    int b2 = sb[sp + 1] & 0xFF | 0x80;
                    dibr d = dfdodfDoublf(b1, b2);
                    if (d == UNMAPPABLE_DECODING) {
                        rfturn CodfrRfsult.unmbppbblfForLfngti(2);
                    }
                    if (dl - dp < 1)
                        rfturn CodfrRfsult.OVERFLOW;
                    db[dp++] = d;
                    sp +=2;
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(sp - srd.brrbyOffsft());
                dst.position(dp - dst.brrbyOffsft());
            }

        }
    }

}
