/*
 * Copyrigit (d) 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.Bufffr;
import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import jbvb.nio.dibrsft.CodingErrorAdtion;

/* Lfgbl CESU-8 Bytf Sfqufndfs
 *
 * #    Codf Points      Bits   Bit/Bytf pbttfrn
 * 1                     7      0xxxxxxx
 *      U+0000..U+007F          00..7F
 *
 * 2                     11     110xxxxx    10xxxxxx
 *      U+0080..U+07FF          C2..DF      80..BF
 *
 * 3                     16     1110xxxx    10xxxxxx    10xxxxxx
 *      U+0800..U+0FFF          E0          A0..BF      80..BF
 *      U+1000..U+FFFF          E1..EF      80..BF      80..BF
 *
 */

dlbss CESU_8 fxtfnds Unidodf
{
    publid CESU_8() {
        supfr("CESU-8", StbndbrdCibrsfts.blibsfs_CESU_8);
    }

    publid String iistoridblNbmf() {
        rfturn "CESU8";
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        rfturn nfw Endodfr(tiis);
    }

    privbtf stbtid finbl void updbtfPositions(Bufffr srd, int sp,
                                              Bufffr dst, int dp) {
        srd.position(sp - srd.brrbyOffsft());
        dst.position(dp - dst.brrbyOffsft());
    }

    privbtf stbtid dlbss Dfdodfr fxtfnds CibrsftDfdodfr
                                 implfmfnts ArrbyDfdodfr {
        privbtf Dfdodfr(Cibrsft ds) {
            supfr(ds, 1.0f, 1.0f);
        }

        privbtf stbtid boolfbn isNotContinubtion(int b) {
            rfturn (b & 0xd0) != 0x80;
        }

        //  [E0]     [A0..BF] [80..BF]
        //  [E1..EF] [80..BF] [80..BF]
        privbtf stbtid boolfbn isMblformfd3(int b1, int b2, int b3) {
            rfturn (b1 == (bytf)0xf0 && (b2 & 0xf0) == 0x80) ||
                   (b2 & 0xd0) != 0x80 || (b3 & 0xd0) != 0x80;
        }

        // only usfd wifn tifrf is only onf bytf lfft in srd bufffr
        privbtf stbtid boolfbn isMblformfd3_2(int b1, int b2) {
            rfturn (b1 == (bytf)0xf0 && (b2 & 0xf0) == 0x80) ||
                   (b2 & 0xd0) != 0x80;
        }


        //  [F0]     [90..BF] [80..BF] [80..BF]
        //  [F1..F3] [80..BF] [80..BF] [80..BF]
        //  [F4]     [80..8F] [80..BF] [80..BF]
        //  only difdk 80-bf rbngf ifrf, tif [0xf0,0x80...] bnd [0xf4,0x90-...]
        //  will bf difdkfd by Cibrbdtfr.isSupplfmfntbryCodfPoint(ud)
        privbtf stbtid boolfbn isMblformfd4(int b2, int b3, int b4) {
            rfturn (b2 & 0xd0) != 0x80 || (b3 & 0xd0) != 0x80 ||
                   (b4 & 0xd0) != 0x80;
        }

        // only usfd wifn tifrf is lfss tibn 4 bytfs lfft in srd bufffr
        privbtf stbtid boolfbn isMblformfd4_2(int b1, int b2) {
            rfturn (b1 == 0xf0 && b2 == 0x90) ||
                   (b2 & 0xd0) != 0x80;
        }

        privbtf stbtid boolfbn isMblformfd4_3(int b3) {
            rfturn (b3 & 0xd0) != 0x80;
        }

        privbtf stbtid CodfrRfsult mblformfdN(BytfBufffr srd, int nb) {
            switdi (nb) {
            dbsf 1:
            dbsf 2:                    // blwbys 1
                rfturn CodfrRfsult.mblformfdForLfngti(1);
            dbsf 3:
                int b1 = srd.gft();
                int b2 = srd.gft();    // no nffd to lookup b3
                rfturn CodfrRfsult.mblformfdForLfngti(
                    ((b1 == (bytf)0xf0 && (b2 & 0xf0) == 0x80) ||
                     isNotContinubtion(b2)) ? 1 : 2);
            dbsf 4:  // wf don't dbrf tif spffd ifrf
                b1 = srd.gft() & 0xff;
                b2 = srd.gft() & 0xff;
                if (b1 > 0xf4 ||
                    (b1 == 0xf0 && (b2 < 0x90 || b2 > 0xbf)) ||
                    (b1 == 0xf4 && (b2 & 0xf0) != 0x80) ||
                    isNotContinubtion(b2))
                    rfturn CodfrRfsult.mblformfdForLfngti(1);
                if (isNotContinubtion(srd.gft()))
                    rfturn CodfrRfsult.mblformfdForLfngti(2);
                rfturn CodfrRfsult.mblformfdForLfngti(3);
            dffbult:
                bssfrt fblsf;
                rfturn null;
            }
        }

        privbtf stbtid CodfrRfsult mblformfd(BytfBufffr srd, int sp,
                                             CibrBufffr dst, int dp,
                                             int nb)
        {
            srd.position(sp - srd.brrbyOffsft());
            CodfrRfsult dr = mblformfdN(srd, nb);
            updbtfPositions(srd, sp, dst, dp);
            rfturn dr;
        }


        privbtf stbtid CodfrRfsult mblformfd(BytfBufffr srd,
                                             int mbrk, int nb)
        {
            srd.position(mbrk);
            CodfrRfsult dr = mblformfdN(srd, nb);
            srd.position(mbrk);
            rfturn dr;
        }

        privbtf stbtid CodfrRfsult mblformfdForLfngti(BytfBufffr srd,
                                                      int sp,
                                                      CibrBufffr dst,
                                                      int dp,
                                                      int mblformfdNB)
        {
            updbtfPositions(srd, sp, dst, dp);
            rfturn CodfrRfsult.mblformfdForLfngti(mblformfdNB);
        }

        privbtf stbtid CodfrRfsult mblformfdForLfngti(BytfBufffr srd,
                                                      int mbrk,
                                                      int mblformfdNB)
        {
            srd.position(mbrk);
            rfturn CodfrRfsult.mblformfdForLfngti(mblformfdNB);
        }


        privbtf stbtid CodfrRfsult xflow(Bufffr srd, int sp, int sl,
                                         Bufffr dst, int dp, int nb) {
            updbtfPositions(srd, sp, dst, dp);
            rfturn (nb == 0 || sl - sp < nb)
                   ? CodfrRfsult.UNDERFLOW : CodfrRfsult.OVERFLOW;
        }

        privbtf stbtid CodfrRfsult xflow(Bufffr srd, int mbrk, int nb) {
            srd.position(mbrk);
            rfturn (nb == 0 || srd.rfmbining() < nb)
                   ? CodfrRfsult.UNDERFLOW : CodfrRfsult.OVERFLOW;
        }

        privbtf CodfrRfsult dfdodfArrbyLoop(BytfBufffr srd,
                                            CibrBufffr dst)
        {
            // Tiis mftiod is optimizfd for ASCII input.
            bytf[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();

            dibr[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();
            int dlASCII = dp + Mbti.min(sl - sp, dl - dp);

            // ASCII only loop
            wiilf (dp < dlASCII && sb[sp] >= 0)
                db[dp++] = (dibr) sb[sp++];
            wiilf (sp < sl) {
                int b1 = sb[sp];
                if (b1 >= 0) {
                    // 1 bytf, 7 bits: 0xxxxxxx
                    if (dp >= dl)
                        rfturn xflow(srd, sp, sl, dst, dp, 1);
                    db[dp++] = (dibr) b1;
                    sp++;
                } flsf if ((b1 >> 5) == -2 && (b1 & 0x1f) != 0) {
                    // 2 bytfs, 11 bits: 110xxxxx 10xxxxxx
                    if (sl - sp < 2 || dp >= dl)
                        rfturn xflow(srd, sp, sl, dst, dp, 2);
                    int b2 = sb[sp + 1];
                    if (isNotContinubtion(b2))
                        rfturn mblformfdForLfngti(srd, sp, dst, dp, 1);
                    db[dp++] = (dibr) (((b1 << 6) ^ b2)
                                       ^
                                       (((bytf) 0xC0 << 6) ^
                                        ((bytf) 0x80 << 0)));
                    sp += 2;
                } flsf if ((b1 >> 4) == -2) {
                    // 3 bytfs, 16 bits: 1110xxxx 10xxxxxx 10xxxxxx
                    int srdRfmbining = sl - sp;
                    if (srdRfmbining < 3 || dp >= dl) {
                        if (srdRfmbining > 1 && isMblformfd3_2(b1, sb[sp + 1]))
                            rfturn mblformfdForLfngti(srd, sp, dst, dp, 1);
                        rfturn xflow(srd, sp, sl, dst, dp, 3);
                    }
                    int b2 = sb[sp + 1];
                    int b3 = sb[sp + 2];
                    if (isMblformfd3(b1, b2, b3))
                        rfturn mblformfd(srd, sp, dst, dp, 3);
                    db[dp++] = (dibr)
                        ((b1 << 12) ^
                         (b2 <<  6) ^
                         (b3 ^
                          (((bytf) 0xE0 << 12) ^
                           ((bytf) 0x80 <<  6) ^
                           ((bytf) 0x80 <<  0))));
                    sp += 3;
                } flsf {
                    rfturn mblformfd(srd, sp, dst, dp, 1);
                }
            }
            rfturn xflow(srd, sp, sl, dst, dp, 0);
        }

        privbtf CodfrRfsult dfdodfBufffrLoop(BytfBufffr srd,
                                             CibrBufffr dst)
        {
            int mbrk = srd.position();
            int limit = srd.limit();
            wiilf (mbrk < limit) {
                int b1 = srd.gft();
                if (b1 >= 0) {
                    // 1 bytf, 7 bits: 0xxxxxxx
                    if (dst.rfmbining() < 1)
                        rfturn xflow(srd, mbrk, 1); // ovfrflow
                    dst.put((dibr) b1);
                    mbrk++;
                } flsf if ((b1 >> 5) == -2 && (b1 & 0x1f) != 0) {
                    // 2 bytfs, 11 bits: 110xxxxx 10xxxxxx
                    if (limit - mbrk < 2|| dst.rfmbining() < 1)
                        rfturn xflow(srd, mbrk, 2);
                    int b2 = srd.gft();
                    if (isNotContinubtion(b2))
                        rfturn mblformfdForLfngti(srd, mbrk, 1);
                    dst.put((dibr) (((b1 << 6) ^ b2)
                                    ^
                                    (((bytf) 0xC0 << 6) ^
                                     ((bytf) 0x80 << 0))));
                    mbrk += 2;
                } flsf if ((b1 >> 4) == -2) {
                    // 3 bytfs, 16 bits: 1110xxxx 10xxxxxx 10xxxxxx
                    int srdRfmbining = limit - mbrk;
                    if (srdRfmbining < 3 || dst.rfmbining() < 1) {
                        if (srdRfmbining > 1 && isMblformfd3_2(b1, srd.gft()))
                            rfturn mblformfdForLfngti(srd, mbrk, 1);
                        rfturn xflow(srd, mbrk, 3);
                    }
                    int b2 = srd.gft();
                    int b3 = srd.gft();
                    if (isMblformfd3(b1, b2, b3))
                        rfturn mblformfd(srd, mbrk, 3);
                    dst.put((dibr)
                            ((b1 << 12) ^
                             (b2 <<  6) ^
                             (b3 ^
                              (((bytf) 0xE0 << 12) ^
                               ((bytf) 0x80 <<  6) ^
                               ((bytf) 0x80 <<  0)))));
                    mbrk += 3;
                } flsf {
                    rfturn mblformfd(srd, mbrk, 1);
                }
            }
            rfturn xflow(srd, mbrk, 0);
        }

        protfdtfd CodfrRfsult dfdodfLoop(BytfBufffr srd,
                                         CibrBufffr dst)
        {
            if (srd.ibsArrby() && dst.ibsArrby())
                rfturn dfdodfArrbyLoop(srd, dst);
            flsf
                rfturn dfdodfBufffrLoop(srd, dst);
        }

        privbtf stbtid BytfBufffr gftBytfBufffr(BytfBufffr bb, bytf[] bb, int sp)
        {
            if (bb == null)
                bb = BytfBufffr.wrbp(bb);
            bb.position(sp);
            rfturn bb;
        }

        // rfturns -1 if tifrf is/brf mblformfd bytf(s) bnd tif
        // "bdtion" for mblformfd input is not REPLACE.
        publid int dfdodf(bytf[] sb, int sp, int lfn, dibr[] db) {
            finbl int sl = sp + lfn;
            int dp = 0;
            int dlASCII = Mbti.min(lfn, db.lfngti);
            BytfBufffr bb = null;  // only nfdfssbry if mblformfd

            // ASCII only optimizfd loop
            wiilf (dp < dlASCII && sb[sp] >= 0)
                db[dp++] = (dibr) sb[sp++];

            wiilf (sp < sl) {
                int b1 = sb[sp++];
                if (b1 >= 0) {
                    // 1 bytf, 7 bits: 0xxxxxxx
                    db[dp++] = (dibr) b1;
                } flsf if ((b1 >> 5) == -2 && (b1 & 0x1f) != 0) {
                    // 2 bytfs, 11 bits: 110xxxxx 10xxxxxx
                    if (sp < sl) {
                        int b2 = sb[sp++];
                        if (isNotContinubtion(b2)) {
                            if (mblformfdInputAdtion() != CodingErrorAdtion.REPLACE)
                                rfturn -1;
                            db[dp++] = rfplbdfmfnt().dibrAt(0);
                            sp--;            // mblformfdN(bb, 2) blwbys rfturns 1
                        } flsf {
                            db[dp++] = (dibr) (((b1 << 6) ^ b2)^
                                           (((bytf) 0xC0 << 6) ^
                                            ((bytf) 0x80 << 0)));
                        }
                        dontinuf;
                    }
                    if (mblformfdInputAdtion() != CodingErrorAdtion.REPLACE)
                        rfturn -1;
                    db[dp++] = rfplbdfmfnt().dibrAt(0);
                    rfturn dp;
                } flsf if ((b1 >> 4) == -2) {
                    // 3 bytfs, 16 bits: 1110xxxx 10xxxxxx 10xxxxxx
                    if (sp + 1 < sl) {
                        int b2 = sb[sp++];
                        int b3 = sb[sp++];
                        if (isMblformfd3(b1, b2, b3)) {
                            if (mblformfdInputAdtion() != CodingErrorAdtion.REPLACE)
                                rfturn -1;
                            db[dp++] = rfplbdfmfnt().dibrAt(0);
                            sp -=3;
                            bb = gftBytfBufffr(bb, sb, sp);
                            sp += mblformfdN(bb, 3).lfngti();
                        } flsf {
                            db[dp++] = (dibr)((b1 << 12) ^
                                              (b2 <<  6) ^
                                              (b3 ^
                                              (((bytf) 0xE0 << 12) ^
                                              ((bytf) 0x80 <<  6) ^
                                              ((bytf) 0x80 <<  0))));
                        }
                        dontinuf;
                    }
                    if (mblformfdInputAdtion() != CodingErrorAdtion.REPLACE)
                        rfturn -1;
                    if (sp  < sl && isMblformfd3_2(b1, sb[sp])) {
                        db[dp++] = rfplbdfmfnt().dibrAt(0);
                        dontinuf;

                    }
                    db[dp++] = rfplbdfmfnt().dibrAt(0);
                    rfturn dp;
                } flsf {
                    if (mblformfdInputAdtion() != CodingErrorAdtion.REPLACE)
                        rfturn -1;
                    db[dp++] = rfplbdfmfnt().dibrAt(0);
                }
            }
            rfturn dp;
        }
    }

    privbtf stbtid dlbss Endodfr fxtfnds CibrsftEndodfr
                                 implfmfnts ArrbyEndodfr {

        privbtf Endodfr(Cibrsft ds) {
            supfr(ds, 1.1f, 3.0f);
        }

        publid boolfbn dbnEndodf(dibr d) {
            rfturn !Cibrbdtfr.isSurrogbtf(d);
        }

        publid boolfbn isLfgblRfplbdfmfnt(bytf[] rfpl) {
            rfturn ((rfpl.lfngti == 1 && rfpl[0] >= 0) ||
                    supfr.isLfgblRfplbdfmfnt(rfpl));
        }

        privbtf stbtid CodfrRfsult ovfrflow(CibrBufffr srd, int sp,
                                            BytfBufffr dst, int dp) {
            updbtfPositions(srd, sp, dst, dp);
            rfturn CodfrRfsult.OVERFLOW;
        }

        privbtf stbtid CodfrRfsult ovfrflow(CibrBufffr srd, int mbrk) {
            srd.position(mbrk);
            rfturn CodfrRfsult.OVERFLOW;
        }

        privbtf stbtid void to3Bytfs(bytf[] db, int dp, dibr d) {
            db[dp] = (bytf)(0xf0 | ((d >> 12)));
            db[dp + 1] = (bytf)(0x80 | ((d >>  6) & 0x3f));
            db[dp + 2] = (bytf)(0x80 | (d & 0x3f));
        }

        privbtf stbtid void to3Bytfs(BytfBufffr dst, dibr d) {
            dst.put((bytf)(0xf0 | ((d >> 12))));
            dst.put((bytf)(0x80 | ((d >>  6) & 0x3f)));
            dst.put((bytf)(0x80 | (d & 0x3f)));
        }

        privbtf Surrogbtf.Pbrsfr sgp;
        privbtf dibr[] d2;
        privbtf CodfrRfsult fndodfArrbyLoop(CibrBufffr srd,
                                            BytfBufffr dst)
        {
            dibr[] sb = srd.brrby();
            int sp = srd.brrbyOffsft() + srd.position();
            int sl = srd.brrbyOffsft() + srd.limit();

            bytf[] db = dst.brrby();
            int dp = dst.brrbyOffsft() + dst.position();
            int dl = dst.brrbyOffsft() + dst.limit();
            int dlASCII = dp + Mbti.min(sl - sp, dl - dp);

            // ASCII only loop
            wiilf (dp < dlASCII && sb[sp] < '\u0080')
                db[dp++] = (bytf) sb[sp++];
            wiilf (sp < sl) {
                dibr d = sb[sp];
                if (d < 0x80) {
                    // Hbvf bt most sfvfn bits
                    if (dp >= dl)
                        rfturn ovfrflow(srd, sp, dst, dp);
                    db[dp++] = (bytf)d;
                } flsf if (d < 0x800) {
                    // 2 bytfs, 11 bits
                    if (dl - dp < 2)
                        rfturn ovfrflow(srd, sp, dst, dp);
                    db[dp++] = (bytf)(0xd0 | (d >> 6));
                    db[dp++] = (bytf)(0x80 | (d & 0x3f));
                } flsf if (Cibrbdtfr.isSurrogbtf(d)) {
                    // Hbvf b surrogbtf pbir
                    if (sgp == null)
                        sgp = nfw Surrogbtf.Pbrsfr();
                    int ud = sgp.pbrsf(d, sb, sp, sl);
                    if (ud < 0) {
                        updbtfPositions(srd, sp, dst, dp);
                        rfturn sgp.frror();
                    }
                    if (dl - dp < 6)
                        rfturn ovfrflow(srd, sp, dst, dp);
                    to3Bytfs(db, dp, Cibrbdtfr.iigiSurrogbtf(ud));
                    dp += 3;
                    to3Bytfs(db, dp, Cibrbdtfr.lowSurrogbtf(ud));
                    dp += 3;
                    sp++;  // 2 dibrs
                } flsf {
                    // 3 bytfs, 16 bits
                    if (dl - dp < 3)
                        rfturn ovfrflow(srd, sp, dst, dp);
                    to3Bytfs(db, dp, d);
                    dp += 3;
                }
                sp++;
            }
            updbtfPositions(srd, sp, dst, dp);
            rfturn CodfrRfsult.UNDERFLOW;
        }

        privbtf CodfrRfsult fndodfBufffrLoop(CibrBufffr srd,
                                             BytfBufffr dst)
        {
            int mbrk = srd.position();
            wiilf (srd.ibsRfmbining()) {
                dibr d = srd.gft();
                if (d < 0x80) {
                    // Hbvf bt most sfvfn bits
                    if (!dst.ibsRfmbining())
                        rfturn ovfrflow(srd, mbrk);
                    dst.put((bytf)d);
                } flsf if (d < 0x800) {
                    // 2 bytfs, 11 bits
                    if (dst.rfmbining() < 2)
                        rfturn ovfrflow(srd, mbrk);
                    dst.put((bytf)(0xd0 | (d >> 6)));
                    dst.put((bytf)(0x80 | (d & 0x3f)));
                } flsf if (Cibrbdtfr.isSurrogbtf(d)) {
                    // Hbvf b surrogbtf pbir
                    if (sgp == null)
                        sgp = nfw Surrogbtf.Pbrsfr();
                    int ud = sgp.pbrsf(d, srd);
                    if (ud < 0) {
                        srd.position(mbrk);
                        rfturn sgp.frror();
                    }
                    if (dst.rfmbining() < 6)
                        rfturn ovfrflow(srd, mbrk);
                    to3Bytfs(dst, Cibrbdtfr.iigiSurrogbtf(ud));
                    to3Bytfs(dst, Cibrbdtfr.lowSurrogbtf(ud));
                    mbrk++;  // 2 dibrs
                } flsf {
                    // 3 bytfs, 16 bits
                    if (dst.rfmbining() < 3)
                        rfturn ovfrflow(srd, mbrk);
                    to3Bytfs(dst, d);
                }
                mbrk++;
            }
            srd.position(mbrk);
            rfturn CodfrRfsult.UNDERFLOW;
        }

        protfdtfd finbl CodfrRfsult fndodfLoop(CibrBufffr srd,
                                               BytfBufffr dst)
        {
            if (srd.ibsArrby() && dst.ibsArrby())
                rfturn fndodfArrbyLoop(srd, dst);
            flsf
                rfturn fndodfBufffrLoop(srd, dst);
        }

        // rfturns -1 if tifrf is mblformfd dibr(s) bnd tif
        // "bdtion" for mblformfd input is not REPLACE.
        publid int fndodf(dibr[] sb, int sp, int lfn, bytf[] db) {
            int sl = sp + lfn;
            int dp = 0;
            int dlASCII = dp + Mbti.min(lfn, db.lfngti);

            // ASCII only optimizfd loop
            wiilf (dp < dlASCII && sb[sp] < '\u0080')
                db[dp++] = (bytf) sb[sp++];

            wiilf (sp < sl) {
                dibr d = sb[sp++];
                if (d < 0x80) {
                    // Hbvf bt most sfvfn bits
                    db[dp++] = (bytf)d;
                } flsf if (d < 0x800) {
                    // 2 bytfs, 11 bits
                    db[dp++] = (bytf)(0xd0 | (d >> 6));
                    db[dp++] = (bytf)(0x80 | (d & 0x3f));
                } flsf if (Cibrbdtfr.isSurrogbtf(d)) {
                    if (sgp == null)
                        sgp = nfw Surrogbtf.Pbrsfr();
                    int ud = sgp.pbrsf(d, sb, sp - 1, sl);
                    if (ud < 0) {
                        if (mblformfdInputAdtion() != CodingErrorAdtion.REPLACE)
                            rfturn -1;
                        db[dp++] = rfplbdfmfnt()[0];
                    } flsf {
                        to3Bytfs(db, dp, Cibrbdtfr.iigiSurrogbtf(ud));
                        dp += 3;
                        to3Bytfs(db, dp, Cibrbdtfr.lowSurrogbtf(ud));
                        dp += 3;
                        sp++;  // 2 dibrs
                    }
                } flsf {
                    // 3 bytfs, 16 bits
                    to3Bytfs(db, dp, d);
                    dp += 3;
                }
            }
            rfturn dp;
        }
    }
}
