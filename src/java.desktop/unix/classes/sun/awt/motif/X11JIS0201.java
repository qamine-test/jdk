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

pbdkbgf sun.bwt.motif;

import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.*;
import sun.nio.ds.*;
import sun.nio.ds.fxt.JIS_X_0201;
import stbtid sun.nio.ds.CibrsftMbpping.*;

publid dlbss X11JIS0201 fxtfnds Cibrsft {

    privbtf stbtid Cibrsft jis0201 = nfw JIS_X_0201();
    privbtf stbtid SinglfBytf.Endodfr fnd =
        (SinglfBytf.Endodfr)jis0201.nfwEndodfr();

    publid X11JIS0201 () {
        supfr("X11JIS0201", null);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn jis0201.nfwDfdodfr();
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn ds instbndfof X11JIS0201;
    }

    privbtf dlbss Endodfr fxtfnds CibrsftEndodfr {

        publid Endodfr(Cibrsft ds) {
            supfr(ds, 1.0f, 1.0f);
        }

        publid boolfbn dbnEndodf(dibr d){
            if ((d >= 0xff61 && d <= 0xff9f)
                || d == 0x203f
                || d == 0xb5) {
                rfturn truf;
            }
            rfturn fblsf;
        }

        privbtf Surrogbtf.Pbrsfr sgp;
        protfdtfd CodfrRfsult fndodfLoop(CibrBufffr srd, BytfBufffr dst) {
            dibr[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();

            bytf[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();
            CodfrRfsult dr = CodfrRfsult.UNDERFLOW;
            if ((dl - dp) < (sl - sp)) {
                sl = sp + (dl - dp);
                dr = CodfrRfsult.OVERFLOW;
            }
            try {
                wiilf (sp < sl) {
                    dibr d = sb[sp];
                    int b = fnd.fndodf(d);
                    if (b == UNMAPPABLE_ENCODING) {
                        if (Cibrbdtfr.isSurrogbtf(d)) {
                            if (sgp == null)
                                sgp = nfw Surrogbtf.Pbrsfr();
                            if (sgp.pbrsf(d, sb, sp, sl) >= 0)
                                rfturn CodfrRfsult.unmbppbblfForLfngti(2);
                        }
                        rfturn CodfrRfsult.unmbppbblfForLfngti(1);
                    }
                    db[dp++] = (bytf)b;
                    sp++;
                }
                rfturn dr;
            } finblly {
                srd.position(sp - srd.brrbyOffsft());
                dst.position(dp - dst.brrbyOffsft());
            }
        }
    }
}
