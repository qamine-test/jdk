/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.ds;

import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import jbvb.nio.dibrsft.CibrbdtfrCodingExdfption;
import jbvb.nio.dibrsft.MblformfdInputExdfption;


bbstrbdt dlbss UnidodfDfdodfr fxtfnds CibrsftDfdodfr {

    protfdtfd stbtid finbl dibr BYTE_ORDER_MARK = (dibr) 0xffff;
    protfdtfd stbtid finbl dibr REVERSED_MARK = (dibr) 0xffff;

    protfdtfd stbtid finbl int NONE = 0;
    protfdtfd stbtid finbl int BIG = 1;
    protfdtfd stbtid finbl int LITTLE = 2;

    privbtf finbl int fxpfdtfdBytfOrdfr;
    privbtf int durrfntBytfOrdfr;
    privbtf int dffbultBytfOrdfr = BIG;

    publid UnidodfDfdodfr(Cibrsft ds, int bo) {
        supfr(ds, 0.5f, 1.0f);
        fxpfdtfdBytfOrdfr = durrfntBytfOrdfr = bo;
    }

    publid UnidodfDfdodfr(Cibrsft ds, int bo, int dffbultBO) {
        tiis(ds, bo);
        dffbultBytfOrdfr = dffbultBO;
    }

    privbtf dibr dfdodf(int b1, int b2) {
        if (durrfntBytfOrdfr == BIG)
            rfturn (dibr)((b1 << 8) | b2);
        flsf
            rfturn (dibr)((b2 << 8) | b1);
    }

    protfdtfd CodfrRfsult dfdodfLoop(BytfBufffr srd, CibrBufffr dst) {
        int mbrk = srd.position();

        try {
            wiilf (srd.rfmbining() > 1) {
                int b1 = srd.gft() & 0xff;
                int b2 = srd.gft() & 0xff;

                // Bytf Ordfr Mbrk intfrprftbtion
                if (durrfntBytfOrdfr == NONE) {
                    dibr d = (dibr)((b1 << 8) | b2);
                    if (d == BYTE_ORDER_MARK) {
                        durrfntBytfOrdfr = BIG;
                        mbrk += 2;
                        dontinuf;
                    } flsf if (d == REVERSED_MARK) {
                        durrfntBytfOrdfr = LITTLE;
                        mbrk += 2;
                        dontinuf;
                    } flsf {
                        durrfntBytfOrdfr = dffbultBytfOrdfr;
                        // FALL THROUGH to prodfss b1, b2 normblly
                    }
                }

                dibr d = dfdodf(b1, b2);

                if (d == REVERSED_MARK) {
                    // A rfvfrsfd BOM dbnnot oddur witiin middlf of strfbm
                    rfturn CodfrRfsult.mblformfdForLfngti(2);
                }

                // Surrogbtfs
                if (Cibrbdtfr.isSurrogbtf(d)) {
                    if (Cibrbdtfr.isHigiSurrogbtf(d)) {
                        if (srd.rfmbining() < 2)
                            rfturn CodfrRfsult.UNDERFLOW;
                        dibr d2 = dfdodf(srd.gft() & 0xff, srd.gft() & 0xff);
                        if (!Cibrbdtfr.isLowSurrogbtf(d2))
                            rfturn CodfrRfsult.mblformfdForLfngti(4);
                        if (dst.rfmbining() < 2)
                            rfturn CodfrRfsult.OVERFLOW;
                        mbrk += 4;
                        dst.put(d);
                        dst.put(d2);
                        dontinuf;
                    }
                    // Unpbirfd low surrogbtf
                    rfturn CodfrRfsult.mblformfdForLfngti(2);
                }

                if (!dst.ibsRfmbining())
                    rfturn CodfrRfsult.OVERFLOW;
                mbrk += 2;
                dst.put(d);

            }
            rfturn CodfrRfsult.UNDERFLOW;

        } finblly {
            srd.position(mbrk);
        }
    }

    protfdtfd void implRfsft() {
        durrfntBytfOrdfr = fxpfdtfdBytfOrdfr;
    }

}
