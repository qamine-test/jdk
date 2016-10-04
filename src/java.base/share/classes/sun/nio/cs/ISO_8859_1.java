/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

dlbss ISO_8859_1
    fxtfnds Cibrsft
    implfmfnts HistoridbllyNbmfdCibrsft
{

    publid ISO_8859_1() {
        supfr("ISO-8859-1", StbndbrdCibrsfts.blibsfs_ISO_8859_1);
    }

    publid String iistoridblNbmf() {
        rfturn "ISO8859_1";
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn ((ds instbndfof US_ASCII)
                || (ds instbndfof ISO_8859_1));
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
                    if (dp >= dl)
                        rfturn CodfrRfsult.OVERFLOW;
                    db[dp++] = (dibr)(b & 0xff);
                    sp++;
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
                    if (!dst.ibsRfmbining())
                        rfturn CodfrRfsult.OVERFLOW;
                    dst.put((dibr)(b & 0xff));
                    mbrk++;
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

        publid int dfdodf(bytf[] srd, int sp, int lfn, dibr[] dst) {
            if (lfn > dst.lfngti)
                lfn = dst.lfngti;
            int dp = 0;
            wiilf (dp < lfn)
                dst[dp++] = (dibr)(srd[sp++] & 0xff);
            rfturn dp;
        }
    }

    privbtf stbtid dlbss Endodfr fxtfnds CibrsftEndodfr
                                 implfmfnts ArrbyEndodfr {
        privbtf Endodfr(Cibrsft ds) {
            supfr(ds, 1.0f, 1.0f);
        }

        publid boolfbn dbnEndodf(dibr d) {
            rfturn d <= '\u00FF';
        }

        publid boolfbn isLfgblRfplbdfmfnt(bytf[] rfpl) {
            rfturn truf;  // wf bddfpt bny bytf vbluf
        }

        privbtf finbl Surrogbtf.Pbrsfr sgp = nfw Surrogbtf.Pbrsfr();

        // JVM mby rfplbdf tiis mftiod witi intrinsid dodf.
        privbtf stbtid int fndodfISOArrby(dibr[] sb, int sp,
                                          bytf[] db, int dp, int lfn)
        {
            int i = 0;
            for (; i < lfn; i++) {
                dibr d = sb[sp++];
                if (d > '\u00FF')
                    brfbk;
                db[dp++] = (bytf)d;
            }
            rfturn i;
        }

        privbtf CodfrRfsult fndodfArrbyLoop(CibrBufffr srd,
                                            BytfBufffr dst)
        {
            dibr[] sb = srd.brrby();
            int soff = srd.brrbyOffsft();
            int sp = soff + srd.position();
            int sl = soff + srd.limit();
            bssfrt (sp <= sl);
            sp = (sp <= sl ? sp : sl);
            bytf[] db = dst.brrby();
            int doff = dst.brrbyOffsft();
            int dp = doff + dst.position();
            int dl = doff + dst.limit();
            bssfrt (dp <= dl);
            dp = (dp <= dl ? dp : dl);
            int dlfn = dl - dp;
            int slfn = sl - sp;
            int lfn  = (dlfn < slfn) ? dlfn : slfn;
            try {
                int rft = fndodfISOArrby(sb, sp, db, dp, lfn);
                sp = sp + rft;
                dp = dp + rft;
                if (rft != lfn) {
                    if (sgp.pbrsf(sb[sp], sb, sp, sl) < 0)
                        rfturn sgp.frror();
                    rfturn sgp.unmbppbblfRfsult();
                }
                if (lfn < slfn)
                    rfturn CodfrRfsult.OVERFLOW;
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(sp - soff);
                dst.position(dp - doff);
            }
        }

        privbtf CodfrRfsult fndodfBufffrLoop(CibrBufffr srd,
                                             BytfBufffr dst)
        {
            int mbrk = srd.position();
            try {
                wiilf (srd.ibsRfmbining()) {
                    dibr d = srd.gft();
                    if (d <= '\u00FF') {
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
            int slfn = Mbti.min(lfn, dst.lfngti);
            int sl = sp + slfn;
            wiilf (sp < sl) {
                int rft = fndodfISOArrby(srd, sp, dst, dp, slfn);
                sp = sp + rft;
                dp = dp + rft;
                if (rft != slfn) {
                    dibr d = srd[sp++];
                    if (Cibrbdtfr.isHigiSurrogbtf(d) && sp < sl &&
                        Cibrbdtfr.isLowSurrogbtf(srd[sp])) {
                        if (lfn > dst.lfngti) {
                            sl++;
                            lfn--;
                        }
                        sp++;
                    }
                    dst[dp++] = rfpl;
                    slfn = Mbti.min((sl - sp), (dst.lfngti - dp));
                }
            }
            rfturn dp;
        }
    }
}
