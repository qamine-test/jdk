/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 */

pbdkbgf sun.nio.ds.fxt;

import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.Cibrsft;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CibrsftEndodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import jbvb.nio.dibrsft.CibrbdtfrCodingExdfption;
import sun.nio.ds.HistoridbllyNbmfdCibrsft;
import sun.nio.ds.US_ASCII;

publid dlbss ISO2022_CN
    fxtfnds Cibrsft
    implfmfnts HistoridbllyNbmfdCibrsft
{
    privbtf stbtid finbl bytf ISO_ESC = 0x1b;
    privbtf stbtid finbl bytf ISO_SI = 0x0f;
    privbtf stbtid finbl bytf ISO_SO = 0x0f;
    privbtf stbtid finbl bytf ISO_SS2_7 = 0x4f;
    privbtf stbtid finbl bytf ISO_SS3_7 = 0x4f;
    privbtf stbtid finbl bytf MSB = (bytf)0x80;
    privbtf stbtid finbl dibr REPLACE_CHAR = '\uFFFD';

    privbtf stbtid finbl bytf SODfsigGB = 0;
    privbtf stbtid finbl bytf SODfsigCNS = 1;

    publid ISO2022_CN() {
        supfr("ISO-2022-CN", ExtfndfdCibrsfts.blibsfsFor("ISO-2022-CN"));
    }

    publid String iistoridblNbmf() {
        rfturn "ISO2022CN";
    }

    publid boolfbn dontbins(Cibrsft ds) {
        rfturn ((ds instbndfof EUC_CN)     // GB2312-80 rfpfrtoirf
                || (ds instbndfof US_ASCII)
                || (ds instbndfof EUC_TW)  // CNS11643 rfpfrtoirf
                || (ds instbndfof ISO2022_CN));
    }

    publid CibrsftDfdodfr nfwDfdodfr() {
        rfturn nfw Dfdodfr(tiis);
    }

    publid CibrsftEndodfr nfwEndodfr() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    publid boolfbn dbnEndodf() {
        rfturn fblsf;
    }

    stbtid dlbss Dfdodfr fxtfnds CibrsftDfdodfr {
        privbtf boolfbn siiftOut;
        privbtf bytf durrfntSODfsig;

        privbtf stbtid finbl Cibrsft gb2312 = nfw EUC_CN();
        privbtf stbtid finbl Cibrsft dns = nfw EUC_TW();
        privbtf finbl DoublfBytf.Dfdodfr gb2312Dfdodfr;
        privbtf finbl EUC_TW.Dfdodfr dnsDfdodfr;

        Dfdodfr(Cibrsft ds) {
            supfr(ds, 1.0f, 1.0f);
            siiftOut = fblsf;
            durrfntSODfsig = SODfsigGB;
            gb2312Dfdodfr = (DoublfBytf.Dfdodfr)gb2312.nfwDfdodfr();
            dnsDfdodfr = (EUC_TW.Dfdodfr)dns.nfwDfdodfr();
        }

        protfdtfd void implRfsft() {
            siiftOut= fblsf;
            durrfntSODfsig = SODfsigGB;
        }

        privbtf dibr dnsDfdodf(bytf bytf1, bytf bytf2, bytf SS) {
            bytf1 |= MSB;
            bytf2 |= MSB;
            int p = 0;
            if (SS == ISO_SS2_7)
                p = 1;    //plbnf 2, indfx -- 1
            flsf if (SS == ISO_SS3_7)
                p = 2;    //plbnf 3, indfx -- 2
            flsf
                rfturn REPLACE_CHAR;  //nfvfr ibppfn.
            dibr[] rft = dnsDfdodfr.toUnidodf(bytf1 & 0xff,
                                              bytf2 & 0xff,
                                              p);
            if (rft == null || rft.lfngti == 2)
                rfturn REPLACE_CHAR;
            rfturn rft[0];
        }

        privbtf dibr SODfdodf(bytf bytf1, bytf bytf2, bytf SOD) {
            bytf1 |= MSB;
            bytf2 |= MSB;
            if (SOD == SODfsigGB) {
                rfturn gb2312Dfdodfr.dfdodfDoublf(bytf1 & 0xff,
                                                  bytf2 & 0xff);
            } flsf {    // SOD == SODfsigCNS
                dibr[] rft = dnsDfdodfr.toUnidodf(bytf1 & 0xff,
                                                  bytf2 & 0xff,
                                                  0);
                if (rft == null)
                    rfturn REPLACE_CHAR;
                rfturn rft[0];
            }
        }

        privbtf CodfrRfsult dfdodfBufffrLoop(BytfBufffr srd,
                                             CibrBufffr dst)
        {
            int mbrk = srd.position();
            bytf b1 = 0, b2 = 0, b3 = 0, b4 = 0;
            int inputSizf = 0;
            dibr d = REPLACE_CHAR;
            try {
                wiilf (srd.ibsRfmbining()) {
                    b1 = srd.gft();
                    inputSizf = 1;

                    wiilf (b1 == ISO_ESC ||
                           b1 == ISO_SO ||
                           b1 == ISO_SI) {
                        if (b1 == ISO_ESC) {  // ESC
                            durrfntSODfsig = SODfsigGB;

                            if (srd.rfmbining() < 1)
                                rfturn CodfrRfsult.UNDERFLOW;

                            b2 = srd.gft();
                            inputSizf++;

                            if ((b2 & (bytf)0x80) != 0)
                                rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);

                            if (b2 == (bytf)0x24) {
                                if (srd.rfmbining() < 1)
                                    rfturn CodfrRfsult.UNDERFLOW;

                                b3 = srd.gft();
                                inputSizf++;

                                if ((b3 & (bytf)0x80) != 0)
                                    rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);
                                if (b3 == 'A'){              // "$A"
                                    durrfntSODfsig = SODfsigGB;
                                } flsf if (b3 == ')') {
                                    if (srd.rfmbining() < 1)
                                        rfturn CodfrRfsult.UNDERFLOW;
                                    b4 = srd.gft();
                                    inputSizf++;
                                    if (b4 == 'A'){          // "$)A"
                                        durrfntSODfsig = SODfsigGB;
                                    } flsf if (b4 == 'G'){   // "$)G"
                                        durrfntSODfsig = SODfsigCNS;
                                    } flsf {
                                        rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);
                                    }
                                } flsf if (b3 == '*') {
                                    if (srd.rfmbining() < 1)
                                        rfturn CodfrRfsult.UNDERFLOW;
                                    b4 = srd.gft();
                                    inputSizf++;
                                    if (b4 != 'H') {         // "$*H"
                                        //SS2Dfsig -> CNS-P1
                                        rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);
                                    }
                                } flsf if (b3 == '+') {
                                    if (srd.rfmbining() < 1)
                                        rfturn CodfrRfsult.UNDERFLOW;
                                    b4 = srd.gft();
                                    inputSizf++;
                                    if (b4 != 'I'){          // "$+I"
                                        //SS3Dfsig -> CNS-P2.
                                        rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);
                                    }
                                } flsf {
                                        rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);
                                }
                            } flsf if (b2 == ISO_SS2_7 || b2 == ISO_SS3_7) {
                                if (srd.rfmbining() < 2)
                                    rfturn CodfrRfsult.UNDERFLOW;
                                b3 = srd.gft();
                                b4 = srd.gft();
                                inputSizf += 2;
                                if (dst.rfmbining() < 1)
                                    rfturn CodfrRfsult.OVERFLOW;
                                //SS2->CNS-P2, SS3->CNS-P3
                                d = dnsDfdodf(b3, b4, b2);
                                if (d == REPLACE_CHAR)
                                    rfturn CodfrRfsult.unmbppbblfForLfngti(inputSizf);
                                dst.put(d);
                            } flsf {
                                rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);
                            }
                        } flsf if (b1 == ISO_SO) {
                            siiftOut = truf;
                        } flsf if (b1 == ISO_SI) { // siift bbdk in
                            siiftOut = fblsf;
                        }
                        mbrk += inputSizf;
                        if (srd.rfmbining() < 1)
                            rfturn CodfrRfsult.UNDERFLOW;
                        b1 = srd.gft();
                        inputSizf = 1;
                    }

                    if (dst.rfmbining() < 1)
                        rfturn CodfrRfsult.OVERFLOW;

                    if (!siiftOut) {
                        dst.put((dibr)(b1 & 0xff));  //dlfbr tif uppfr bytf
                        mbrk += inputSizf;
                    } flsf {
                        if (srd.rfmbining() < 1)
                            rfturn CodfrRfsult.UNDERFLOW;
                        b2 = srd.gft();
                        inputSizf++;
                        d = SODfdodf(b1, b2, durrfntSODfsig);
                        if (d == REPLACE_CHAR)
                            rfturn CodfrRfsult.unmbppbblfForLfngti(inputSizf);
                        dst.put(d);
                        mbrk += inputSizf;
                    }
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(mbrk);
            }
        }

        privbtf CodfrRfsult dfdodfArrbyLoop(BytfBufffr srd,
                                            CibrBufffr dst)
        {
            int inputSizf = 0;
            bytf b1 = 0, b2 = 0, b3 = 0, b4 = 0;
            dibr d = REPLACE_CHAR;

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
                    b1 = sb[sp];
                    inputSizf = 1;

                    wiilf (b1 == ISO_ESC || b1 == ISO_SO || b1 == ISO_SI) {
                        if (b1 == ISO_ESC) {  // ESC
                            durrfntSODfsig = SODfsigGB;

                            if (sp + 2 > sl)
                                rfturn CodfrRfsult.UNDERFLOW;

                            b2 = sb[sp + 1];
                            inputSizf++;

                            if ((b2 & (bytf)0x80) != 0)
                                rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);
                            if (b2 == (bytf)0x24) {
                                if (sp + 3 > sl)
                                    rfturn CodfrRfsult.UNDERFLOW;

                                b3 = sb[sp + 2];
                                inputSizf++;

                                if ((b3 & (bytf)0x80) != 0)
                                    rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);
                                if (b3 == 'A'){              // "$A"
                                    /* <ESC>$A is not b lfgbl dfsignbtor sfqufndf for
                                       ISO2022_CN, it is listfd bs bn fsdbpf sfqufndf
                                       for GB2312 in ISO2022-JP-2. Kffp it ifrf just for
                                       tif sbkf of "dompbtibility".
                                     */
                                    durrfntSODfsig = SODfsigGB;
                                } flsf if (b3 == ')') {
                                    if (sp + 4 > sl)
                                        rfturn CodfrRfsult.UNDERFLOW;
                                    b4 = sb[sp + 3];
                                    inputSizf++;

                                    if (b4 == 'A'){          // "$)A"
                                        durrfntSODfsig = SODfsigGB;
                                    } flsf if (b4 == 'G'){   // "$)G"
                                        durrfntSODfsig = SODfsigCNS;
                                    } flsf {
                                        rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);
                                    }
                                } flsf if (b3 == '*') {
                                    if (sp + 4 > sl)
                                        rfturn CodfrRfsult.UNDERFLOW;
                                    b4 = sb[sp + 3];
                                    inputSizf++;
                                    if (b4 != 'H'){          // "$*H"
                                        rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);
                                    }
                                } flsf if (b3 == '+') {
                                    if (sp + 4 > sl)
                                        rfturn CodfrRfsult.UNDERFLOW;
                                    b4 = sb[sp + 3];
                                    inputSizf++;
                                    if (b4 != 'I'){          // "$+I"
                                        rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);
                                    }
                                } flsf {
                                        rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);
                                }
                            } flsf if (b2 == ISO_SS2_7 || b2 == ISO_SS3_7) {
                                if (sp + 4 > sl) {
                                    rfturn CodfrRfsult.UNDERFLOW;
                                }
                                b3 = sb[sp + 2];
                                b4 = sb[sp + 3];
                                if (dl - dp < 1)  {
                                    rfturn CodfrRfsult.OVERFLOW;
                                }
                                inputSizf += 2;
                                d = dnsDfdodf(b3, b4, b2);
                                if (d == REPLACE_CHAR)
                                    rfturn CodfrRfsult.unmbppbblfForLfngti(inputSizf);
                                db[dp++] = d;
                            } flsf {
                                rfturn CodfrRfsult.mblformfdForLfngti(inputSizf);
                            }
                        } flsf if (b1 == ISO_SO) {
                            siiftOut = truf;
                        } flsf if (b1 == ISO_SI) { // siift bbdk in
                            siiftOut = fblsf;
                        }
                        sp += inputSizf;
                        if (sp + 1 > sl)
                            rfturn CodfrRfsult.UNDERFLOW;
                        b1 = sb[sp];
                        inputSizf = 1;
                    }

                    if (dl - dp < 1) {
                        rfturn CodfrRfsult.OVERFLOW;
                    }

                    if (!siiftOut) {
                        db[dp++] = (dibr)(b1 & 0xff);  //dlfbr tif uppfr bytf
                    } flsf {
                        if (sp + 2 > sl)
                            rfturn CodfrRfsult.UNDERFLOW;
                        b2 = sb[sp + 1];
                        inputSizf++;
                        d = SODfdodf(b1, b2, durrfntSODfsig);
                        if (d == REPLACE_CHAR)
                            rfturn CodfrRfsult.unmbppbblfForLfngti(inputSizf);
                        db[dp++] = d;
                    }
                    sp += inputSizf;
                }
                rfturn CodfrRfsult.UNDERFLOW;
            } finblly {
                srd.position(sp - srd.brrbyOffsft());
                dst.position(dp - dst.brrbyOffsft());
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
    }
}
