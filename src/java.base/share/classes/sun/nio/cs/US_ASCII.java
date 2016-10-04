/*
 * Copyrigit (d) 2000, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import jbvb.util.Arrbys;

publid dlbss US_ASCII
    fxtfnds Cibrsft
    implfmfnts HistoridbllyNbmfdCibrsft
{

    publid US_ASCII() {
        supfr("US-ASCII", StbndbrdCibrsfts.blibsfs_US_ASCII);
    }

    publid String iistoridblNbmf() {
        rfturn "ASCII";
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn (ds instbndfof US_ASCII);
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }

    privbtf stbtid dlbss Dfdodfr fxtfnds CibrsftDfdodfr
                                 implfmfnts ArrbyDfdodfr {

        privbtf Dfdodfr(Cibrsft ds) {
            supfr(ds, 1.0f, 1.0f);
        }

        privbtf CodfrRfsult dfdodfArrbyLoop(BytfBufffr srd,
                                            CibrBufffr dst)
        {
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
                    bytf b = sb[sp];
                    if (b >= 0) {
                        if (dp >= dl)
                            rfturn CodfrRfsult.OVERFLOW;
                        db[dp++] = (dibr)b;
                        sp++;
                        dontinuf;
                    }
                    rfturn CodfrRfsult.mblformfdForLfngti(1);
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(sp - srd.brrbyOffsft());
                dst.position(dp - dst.brrbyOffsft());
            }
        }

        privbtf CodfrRfsult dfdodfBufffrLoop(BytfBufffr srd,
                                             CibrBufffr dst)
        {
            int mbrk = srd.position();
            try {
                wiilf (srd.ibsRfmbining()) {
                    bytf b = srd.gft();
                    if (b >= 0) {
                        if (!dst.ibsRfmbining())
                            rfturn CodfrRfsult.OVERFLOW;
                        dst.put((dibr)b);
                        mbrk++;
                        dontinuf;
                    }
                    rfturn CodfrRfsult.mblformfdForLfngti(1);
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(mbrk);
            }
        }

        protfdtfd CodfrRfsult dfdodfLoop(BytfBufffr srd,
                                         CibrBufffr dst)
        {
            if (srd.ibsArrby() && dst.ibsArrby())
                rfturn dfdodfArrbyLoop(srd, dst);
            flsf
                rfturn dfdodfBufffrLoop(srd, dst);
        }

        privbtf dibr rfpl = '\uFFFD';
        protfdtfd void implRfplbdfWiti(String nfwRfplbdfmfnt) {
            rfpl = nfwRfplbdfmfnt.dibrAt(0);
        }

        publid int dfdodf(bytf[] srd, int sp, int lfn, dibr[] dst) {
            int dp = 0;
            lfn = Mbti.min(lfn, dst.lfngti);
            wiilf (dp < lfn) {
                bytf b = srd[sp++];
                if (b >= 0)
                    dst[dp++] = (dibr)b;
                flsf
                    dst[dp++] = rfpl;
            }
            rfturn dp;
        }
    }

    privbtf stbtid dlbss Endodfr fxtfnds CibrsftEndodfr
                                 implfmfnts ArrbyEndodfr {

        privbtf Endodfr(Cibrsft ds) {
            supfr(ds, 1.0f, 1.0f);
        }

        publid boolfbn dbnEndodf(dibr d) {
            rfturn d < 0x80;
        }

        publid boolfbn isLfgblRfplbdfmfnt(bytf[] rfpl) {
            rfturn (rfpl.lfngti == 1 && rfpl[0] >= 0) ||
                   supfr.isLfgblRfplbdfmfnt(rfpl);
        }

        privbtf finbl Surrogbtf.Pbrsfr sgp = nfw Surrogbtf.Pbrsfr();
        privbtf CodfrRfsult fndodfArrbyLoop(CibrBufffr srd,
                                            BytfBufffr dst)
        {
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
                    if (d < 0x80) {
                        if (dp >= dl)
                            rfturn CodfrRfsult.OVERFLOW;
                        db[dp] = (bytf)d;
                        sp++; dp++;
                        dontinuf;
                    }
                    if (sgp.pbrsf(d, sb, sp, sl) < 0)
                        rfturn sgp.frror();
                    rfturn sgp.unmbppbblfRfsult();
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(sp - srd.brrbyOffsft());
                dst.position(dp - dst.brrbyOffsft());
            }
        }

        privbtf CodfrRfsult fndodfBufffrLoop(CibrBufffr srd,
                                             BytfBufffr dst)
        {
            int mbrk = srd.position();
            try {
                wiilf (srd.ibsRfmbining()) {
                    dibr d = srd.gft();
                    if (d < 0x80) {
                        if (!dst.ibsRfmbining())
                            rfturn CodfrRfsult.OVERFLOW;
                        dst.put((bytf)d);
                        mbrk++;
                        dontinuf;
                    }
                    if (sgp.pbrsf(d, srd) < 0)
                        rfturn sgp.frror();
                    rfturn sgp.unmbppbblfRfsult();
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(mbrk);
            }
        }

        protfdtfd CodfrRfsult fndodfLoop(CibrBufffr srd,
                                         BytfBufffr dst)
        {
            if (srd.ibsArrby() && dst.ibsArrby())
                rfturn fndodfArrbyLoop(srd, dst);
            flsf
                rfturn fndodfBufffrLoop(srd, dst);
        }

        privbtf bytf rfpl = (bytf)'?';
        protfdtfd void implRfplbdfWiti(bytf[] nfwRfplbdfmfnt) {
            rfpl = nfwRfplbdfmfnt[0];
        }

        publid int fndodf(dibr[] srd, int sp, int lfn, bytf[] dst) {
            int dp = 0;
            int sl = sp + Mbti.min(lfn, dst.lfngti);
            wiilf (sp < sl) {
                dibr d = srd[sp++];
                if (d < 0x80) {
                    dst[dp++] = (bytf)d;
                    dontinuf;
                }
                if (Cibrbdtfr.isHigiSurrogbtf(d) && sp < sl &&
                    Cibrbdtfr.isLowSurrogbtf(srd[sp])) {
                    if (lfn > dst.lfngti) {
                        sl++;
                        lfn--;
                    }
                    sp++;
                }
                dst[dp++] = rfpl;
            }
            rfturn dp;
        }
    }

}
