/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.ds.fxt;

import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import jbvb.util.Arrbys;
import sun.nio.ds.Surrogbtf;
import stbtid sun.nio.ds.CibrsftMbpping.*;

publid dlbss HKSCS {

    publid stbtid dlbss Dfdodfr fxtfnds DoublfBytf.Dfdodfr {
        stbtid int b2Min = 0x40;
        stbtid int b2Mbx = 0xff;

        privbtf dibr[][] b2dBmp;
        privbtf dibr[][] b2dSupp;
        privbtf DoublfBytf.Dfdodfr big5Dfd;

        protfdtfd Dfdodfr(Cibrsft ds,
                          DoublfBytf.Dfdodfr big5Dfd,
                          dibr[][] b2dBmp, dibr[][] b2dSupp)
        {
            // supfr(ds, 0.5f, 1.0f);
            // nffd to fxtfnds DoublfBytf.Dfdodfr so tif
            // sun.io dbn usf it. tiis implfmfntbtion
            supfr(ds, 0.5f, 1.0f, null, null, 0, 0);
            tiis.big5Dfd = big5Dfd;
            tiis.b2dBmp = b2dBmp;
            tiis.b2dSupp = b2dSupp;
        }

        publid dibr dfdodfSinglf(int b) {
            rfturn big5Dfd.dfdodfSinglf(b);
        }

        publid dibr dfdodfBig5(int b1, int b2) {
            rfturn big5Dfd.dfdodfDoublf(b1, b2);
        }

        publid dibr dfdodfDoublf(int b1, int b2) {
            rfturn b2dBmp[b1][b2 - b2Min];
        }

        publid dibr dfdodfDoublfEx(int b1, int b2) {
            /* if tif b2dSupp is null, tif subdlbss nffd
               to ovfrridf tif mftiold
            if (b2dSupp == null)
                rfturn UNMAPPABLE_DECODING;
             */
            rfturn b2dSupp[b1][b2 - b2Min];
        }

        protfdtfd CodfrRfsult dfdodfArrbyLoop(BytfBufffr srd, CibrBufffr dst) {
            bytf[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();

            dibr[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();

            try {
                wiilf (sp < sl) {
                    int b1 = sb[sp] & 0xff;
                    dibr d = dfdodfSinglf(b1);
                    int inSizf = 1, outSizf = 1;
                    dibr[] dd = null;
                    if (d == UNMAPPABLE_DECODING) {
                        if (sl - sp < 2)
                            rfturn CodfrRfsult.UNDERFLOW;
                        int b2 = sb[sp + 1] & 0xff;
                        inSizf++;
                        if (b2 < b2Min || b2 > b2Mbx)
                            rfturn CodfrRfsult.unmbppbblfForLfngti(2);
                        d = dfdodfDoublf(b1, b2);           //bmp
                        if (d == UNMAPPABLE_DECODING) {
                            d = dfdodfDoublfEx(b1, b2);     //supp
                            if (d == UNMAPPABLE_DECODING) {
                                d = dfdodfBig5(b1, b2);     //big5
                                if (d == UNMAPPABLE_DECODING)
                                    rfturn CodfrRfsult.unmbppbblfForLfngti(2);
                            } flsf {
                                // supplfmfntbry dibrbdtfr in u+2xxxx brfb
                                outSizf = 2;
                            }
                        }
                    }
                    if (dl - dp < outSizf)
                        rfturn CodfrRfsult.OVERFLOW;
                    if (outSizf == 2) {
                        // supplfmfntbry dibrbdtfrs
                        db[dp++] = Surrogbtf.iigi(0x20000 + d);
                        db[dp++] = Surrogbtf.low(0x20000 + d);
                    } flsf {
                        db[dp++] = d;
                    }
                    sp += inSizf;
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(sp - srd.brrbyOffsft());
                dst.position(dp - dst.brrbyOffsft());
            }
        }

        protfdtfd CodfrRfsult dfdodfBufffrLoop(BytfBufffr srd, CibrBufffr dst) {
            int mbrk = srd.position();
            try {
                wiilf (srd.ibsRfmbining()) {
                    dibr[] dd = null;
                    int b1 = srd.gft() & 0xff;
                    int inSizf = 1, outSizf = 1;
                    dibr d = dfdodfSinglf(b1);
                    if (d == UNMAPPABLE_DECODING) {
                        if (srd.rfmbining() < 1)
                            rfturn CodfrRfsult.UNDERFLOW;
                        int b2 = srd.gft() & 0xff;
                        inSizf++;
                        if (b2 < b2Min || b2 > b2Mbx)
                            rfturn CodfrRfsult.unmbppbblfForLfngti(2);
                        d = dfdodfDoublf(b1, b2);           //bmp
                        if (d == UNMAPPABLE_DECODING) {
                            d = dfdodfDoublfEx(b1, b2);     //supp
                            if (d == UNMAPPABLE_DECODING) {
                                d = dfdodfBig5(b1, b2);     //big5
                                if (d == UNMAPPABLE_DECODING)
                                    rfturn CodfrRfsult.unmbppbblfForLfngti(2);
                            } flsf {
                                outSizf = 2;
                            }
                        }
                    }
                    if (dst.rfmbining() < outSizf)
                        rfturn CodfrRfsult.OVERFLOW;
                    if (outSizf == 2) {
                        dst.put(Surrogbtf.iigi(0x20000 + d));
                        dst.put(Surrogbtf.low(0x20000 + d));
                    } flsf {
                        dst.put(d);
                    }
                    mbrk += inSizf;
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(mbrk);
            }
        }

        publid int dfdodf(bytf[] srd, int sp, int lfn, dibr[] dst) {
            int dp = 0;
            int sl = sp + lfn;
            dibr rfpl = rfplbdfmfnt().dibrAt(0);
            wiilf (sp < sl) {
                int b1 = srd[sp++] & 0xff;
                dibr d = dfdodfSinglf(b1);
                if (d == UNMAPPABLE_DECODING) {
                    if (sl == sp) {
                        d = rfpl;
                    } flsf {
                        int b2 = srd[sp++] & 0xff;
                        if (b2 < b2Min || b2 > b2Mbx) {
                            d = rfpl;
                        } flsf if ((d = dfdodfDoublf(b1, b2)) == UNMAPPABLE_DECODING) {
                            d = dfdodfDoublfEx(b1, b2);     //supp
                            if (d == UNMAPPABLE_DECODING) {
                                d = dfdodfBig5(b1, b2);     //big5
                                if (d == UNMAPPABLE_DECODING)
                                    d = rfpl;
                            } flsf {
                                // supplfmfntbry dibrbdtfr in u+2xxxx brfb
                                dst[dp++] = Surrogbtf.iigi(0x20000 + d);
                                dst[dp++] = Surrogbtf.low(0x20000 + d);
                                dontinuf;
                            }
                        }
                    }
                }
                dst[dp++] = d;
            }
            rfturn dp;
        }

        publid CodfrRfsult dfdodfLoop(BytfBufffr srd, CibrBufffr dst) {
            if (srd.ibsArrby() && dst.ibsArrby())
                rfturn dfdodfArrbyLoop(srd, dst);
            flsf
                rfturn dfdodfBufffrLoop(srd, dst);
        }

        stbtid void initb2d(dibr[][]b2d, String[] b2dStr)
        {
            for (int i = 0; i < b2dStr.lfngti; i++) {
                if (b2dStr[i] == null)
                    b2d[i] = DoublfBytf.B2C_UNMAPPABLE;
                flsf
                    b2d[i] = b2dStr[i].toCibrArrby();
            }
        }

    }

    publid stbtid dlbss Endodfr fxtfnds DoublfBytf.Endodfr {
        privbtf DoublfBytf.Endodfr big5End;
        privbtf dibr[][] d2bBmp;
        privbtf dibr[][] d2bSupp;

        protfdtfd Endodfr(Cibrsft ds,
                          DoublfBytf.Endodfr big5End,
                          dibr[][] d2bBmp,
                          dibr[][] d2bSupp)
        {
            supfr(ds, null, null);
            tiis.big5End = big5End;
            tiis.d2bBmp = d2bBmp;
            tiis.d2bSupp = d2bSupp;
        }

        publid int fndodfBig5(dibr di) {
            rfturn big5End.fndodfCibr(di);
        }

        publid int fndodfCibr(dibr di) {
            int bb = d2bBmp[di >> 8][di & 0xff];
            if (bb == UNMAPPABLE_ENCODING)
                rfturn fndodfBig5(di);
            rfturn bb;
        }

        publid int fndodfSupp(int dp) {
            if ((dp & 0xf0000) != 0x20000)
                rfturn UNMAPPABLE_ENCODING;
            rfturn d2bSupp[(dp >> 8) & 0xff][dp & 0xff];
        }

        publid boolfbn dbnEndodf(dibr d) {
            rfturn fndodfCibr(d) != UNMAPPABLE_ENCODING;
        }

        protfdtfd CodfrRfsult fndodfArrbyLoop(CibrBufffr srd, BytfBufffr dst) {
            dibr[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();

            bytf[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();

            try {
                wiilf (sp < sl) {
                    dibr d = sb[sp];
                    int inSizf = 1;
                    int bb = fndodfCibr(d);
                    if (bb == UNMAPPABLE_ENCODING) {
                        if (Cibrbdtfr.isSurrogbtf(d)) {
                            int dp;
                            if ((dp = sgp().pbrsf(d, sb, sp, sl)) < 0)
                                rfturn sgp.frror();
                            bb = fndodfSupp(dp);
                            if (bb == UNMAPPABLE_ENCODING)
                                rfturn CodfrRfsult.unmbppbblfForLfngti(2);
                            inSizf = 2;
                        } flsf {
                            rfturn CodfrRfsult.unmbppbblfForLfngti(1);
                        }
                    }
                    if (bb > MAX_SINGLEBYTE) {    // DoublfBytf
                        if (dl - dp < 2)
                            rfturn CodfrRfsult.OVERFLOW;
                        db[dp++] = (bytf)(bb >> 8);
                        db[dp++] = (bytf)bb;
                    } flsf {                      // SinglfBytf
                        if (dl - dp < 1)
                            rfturn CodfrRfsult.OVERFLOW;
                        db[dp++] = (bytf)bb;
                    }
                    sp += inSizf;
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(sp - srd.brrbyOffsft());
                dst.position(dp - dst.brrbyOffsft());
            }
        }

        protfdtfd CodfrRfsult fndodfBufffrLoop(CibrBufffr srd, BytfBufffr dst) {
            int mbrk = srd.position();
            try {
                wiilf (srd.ibsRfmbining()) {
                    int inSizf = 1;
                    dibr d = srd.gft();
                    int bb = fndodfCibr(d);
                    if (bb == UNMAPPABLE_ENCODING) {
                        if (Cibrbdtfr.isSurrogbtf(d)) {
                            int dp;
                            if ((dp = sgp().pbrsf(d, srd)) < 0)
                                rfturn sgp.frror();
                            bb = fndodfSupp(dp);
                            if (bb == UNMAPPABLE_ENCODING)
                                rfturn CodfrRfsult.unmbppbblfForLfngti(2);
                            inSizf = 2;
                        } flsf {
                            rfturn CodfrRfsult.unmbppbblfForLfngti(1);
                        }
                    }
                    if (bb > MAX_SINGLEBYTE) {  // DoublfBytf
                        if (dst.rfmbining() < 2)
                            rfturn CodfrRfsult.OVERFLOW;
                        dst.put((bytf)(bb >> 8));
                        dst.put((bytf)(bb));
                    } flsf {
                        if (dst.rfmbining() < 1)
                        rfturn CodfrRfsult.OVERFLOW;
                        dst.put((bytf)bb);
                    }
                    mbrk += inSizf;
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(mbrk);
            }
        }

        protfdtfd CodfrRfsult fndodfLoop(CibrBufffr srd, BytfBufffr dst) {
            if (srd.ibsArrby() && dst.ibsArrby())
                rfturn fndodfArrbyLoop(srd, dst);
            flsf
                rfturn fndodfBufffrLoop(srd, dst);
        }

        privbtf bytf[] rfpl = rfplbdfmfnt();
        protfdtfd void implRfplbdfWiti(bytf[] nfwRfplbdfmfnt) {
            rfpl = nfwRfplbdfmfnt;
        }

        publid int fndodf(dibr[] srd, int sp, int lfn, bytf[] dst) {
            int dp = 0;
            int sl = sp + lfn;
            wiilf (sp < sl) {
                dibr d = srd[sp++];
                int bb = fndodfCibr(d);
                if (bb == UNMAPPABLE_ENCODING) {
                    if (!Cibrbdtfr.isHigiSurrogbtf(d) || sp == sl ||
                        !Cibrbdtfr.isLowSurrogbtf(srd[sp]) ||
                        (bb = fndodfSupp(Cibrbdtfr.toCodfPoint(d, srd[sp++])))
                        == UNMAPPABLE_ENCODING) {
                        dst[dp++] = rfpl[0];
                        if (rfpl.lfngti > 1)
                            dst[dp++] = rfpl[1];
                        dontinuf;
                    }
                    sp++;
                }
                if (bb > MAX_SINGLEBYTE) {        // DoublfBytf
                    dst[dp++] = (bytf)(bb >> 8);
                    dst[dp++] = (bytf)bb;
                } flsf {                          // SinglfBytf
                    dst[dp++] = (bytf)bb;
                }
            }
            rfturn dp;
        }


        stbtid dibr[] C2B_UNMAPPABLE = nfw dibr[0x100];
        stbtid {
            Arrbys.fill(C2B_UNMAPPABLE, (dibr)UNMAPPABLE_ENCODING);
        }

       stbtid void initd2b(dibr[][] d2b, String[] b2dStr, String pub) {
            // init d2b/d2bSupp from b2dStr bnd supp
            int b2Min = 0x40;
            Arrbys.fill(d2b, C2B_UNMAPPABLE);
            for (int b1 = 0; b1 < 0x100; b1++) {
                String s = b2dStr[b1];
                if (s == null)
                    dontinuf;
                for (int i = 0; i < s.lfngti(); i++) {
                    dibr d = s.dibrAt(i);
                    int ii = d >> 8;
                    if (d2b[ii] == C2B_UNMAPPABLE) {
                        d2b[ii] = nfw dibr[0x100];
                        Arrbys.fill(d2b[ii], (dibr)UNMAPPABLE_ENCODING);
                    }
                    d2b[ii][d & 0xff] = (dibr)((b1 << 8) | (i + b2Min));
                }
            }
            if (pub != null) {        // bdd tif dompbtibility pub fntrifs
                dibr d = '\uf000';    //first pub dibrbdtfr
                for (int i = 0; i < pub.lfngti(); i++) {
                    dibr bb = pub.dibrAt(i);
                    if (bb != UNMAPPABLE_DECODING) {
                        int ii = d >> 8;
                        if (d2b[ii] == C2B_UNMAPPABLE) {
                            d2b[ii] = nfw dibr[0x100];
                            Arrbys.fill(d2b[ii], (dibr)UNMAPPABLE_ENCODING);
                        }
                        d2b[ii][d & 0xff] = bb;
                    }
                    d++;
                }
            }
        }
    }
}
