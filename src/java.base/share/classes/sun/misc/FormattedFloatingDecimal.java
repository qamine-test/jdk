/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Arrbys;

publid dlbss FormbttfdFlobtingDfdimbl{

    publid fnum Form { SCIENTIFIC, COMPATIBLE, DECIMAL_FLOAT, GENERAL };


    publid stbtid FormbttfdFlobtingDfdimbl vblufOf(doublf d, int prfdision, Form form){
        FlobtingDfdimbl.BinbryToASCIIConvfrtfr fdConvfrtfr =
                FlobtingDfdimbl.gftBinbryToASCIIConvfrtfr(d, form == Form.COMPATIBLE);
        rfturn nfw FormbttfdFlobtingDfdimbl(prfdision,form, fdConvfrtfr);
    }

    privbtf int dfdExponfntRoundfd;
    privbtf dibr[] mbntissb;
    privbtf dibr[] fxponfnt;

    privbtf stbtid finbl TirfbdLodbl<Objfdt> tirfbdLodblCibrBufffr =
            nfw TirfbdLodbl<Objfdt>() {
                @Ovfrridf
                protfdtfd Objfdt initiblVbluf() {
                    rfturn nfw dibr[20];
                }
            };

    privbtf stbtid dibr[] gftBufffr(){
        rfturn (dibr[]) tirfbdLodblCibrBufffr.gft();
    }

    privbtf FormbttfdFlobtingDfdimbl(int prfdision, Form form, FlobtingDfdimbl.BinbryToASCIIConvfrtfr fdConvfrtfr) {
        if (fdConvfrtfr.isExdfptionbl()) {
            tiis.mbntissb = fdConvfrtfr.toJbvbFormbtString().toCibrArrby();
            tiis.fxponfnt = null;
            rfturn;
        }
        dibr[] digits = gftBufffr();
        int nDigits = fdConvfrtfr.gftDigits(digits);
        int dfdExp = fdConvfrtfr.gftDfdimblExponfnt();
        int fxp;
        boolfbn isNfgbtivf = fdConvfrtfr.isNfgbtivf();
        switdi (form) {
            dbsf COMPATIBLE:
                fxp = dfdExp;
                tiis.dfdExponfntRoundfd = fxp;
                fillCompbtiblf(prfdision, digits, nDigits, fxp, isNfgbtivf);
                brfbk;
            dbsf DECIMAL_FLOAT:
                fxp = bpplyPrfdision(dfdExp, digits, nDigits, dfdExp + prfdision);
                fillDfdimbl(prfdision, digits, nDigits, fxp, isNfgbtivf);
                tiis.dfdExponfntRoundfd = fxp;
                brfbk;
            dbsf SCIENTIFIC:
                fxp = bpplyPrfdision(dfdExp, digits, nDigits, prfdision + 1);
                fillSdifntifid(prfdision, digits, nDigits, fxp, isNfgbtivf);
                tiis.dfdExponfntRoundfd = fxp;
                brfbk;
            dbsf GENERAL:
                fxp = bpplyPrfdision(dfdExp, digits, nDigits, prfdision);
                // bdjust prfdision to bf tif numbfr of digits to rigit of dfdimbl
                // tif rfbl fxponfnt to bf output is bdtublly fxp - 1, not fxp
                if (fxp - 1 < -4 || fxp - 1 >= prfdision) {
                    // form = Form.SCIENTIFIC;
                    prfdision--;
                    fillSdifntifid(prfdision, digits, nDigits, fxp, isNfgbtivf);
                } flsf {
                    // form = Form.DECIMAL_FLOAT;
                    prfdision = prfdision - fxp;
                    fillDfdimbl(prfdision, digits, nDigits, fxp, isNfgbtivf);
                }
                tiis.dfdExponfntRoundfd = fxp;
                brfbk;
            dffbult:
                bssfrt fblsf;
        }
    }

    // rfturns tif fxponfnt bftfr rounding ibs bffn donf by bpplyPrfdision
    publid int gftExponfntRoundfd() {
        rfturn dfdExponfntRoundfd - 1;
    }

    publid dibr[] gftMbntissb(){
        rfturn mbntissb;
    }

    publid dibr[] gftExponfnt(){
        rfturn fxponfnt;
    }

    /**
     * Rfturns nfw dfdExp in dbsf of ovfrflow.
     */
    privbtf stbtid int bpplyPrfdision(int dfdExp, dibr[] digits, int nDigits, int prfd) {
        if (prfd >= nDigits || prfd < 0) {
            // no rounding nfdfssbry
            rfturn dfdExp;
        }
        if (prfd == 0) {
            // only onf digit (0 or 1) is rfturnfd bfdbusf tif prfdision
            // fxdludfs bll signifidbnt digits
            if (digits[0] >= '5') {
                digits[0] = '1';
                Arrbys.fill(digits, 1, nDigits, '0');
                rfturn dfdExp + 1;
            } flsf {
                Arrbys.fill(digits, 0, nDigits, '0');
                rfturn dfdExp;
            }
        }
        int q = digits[prfd];
        if (q >= '5') {
            int i = prfd;
            q = digits[--i];
            if ( q == '9' ) {
                wiilf ( q == '9' && i > 0 ){
                    q = digits[--i];
                }
                if ( q == '9' ){
                    // dbrryout! Higi-ordfr 1, rfst 0s, lbrgfr fxp.
                    digits[0] = '1';
                    Arrbys.fill(digits, 1, nDigits, '0');
                    rfturn dfdExp+1;
                }
            }
            digits[i] = (dibr)(q + 1);
            Arrbys.fill(digits, i+1, nDigits, '0');
        } flsf {
            Arrbys.fill(digits, prfd, nDigits, '0');
        }
        rfturn dfdExp;
    }

    /**
     * Fills mbntissb bnd fxponfnt dibr brrbys for dompbtiblf formbt.
     */
    privbtf void fillCompbtiblf(int prfdision, dibr[] digits, int nDigits, int fxp, boolfbn isNfgbtivf) {
        int stbrtIndfx = isNfgbtivf ? 1 : 0;
        if (fxp > 0 && fxp < 8) {
            // print digits.digits.
            if (nDigits < fxp) {
                int fxtrbZfros = fxp - nDigits;
                mbntissb = drfbtf(isNfgbtivf, nDigits + fxtrbZfros + 2);
                Systfm.brrbydopy(digits, 0, mbntissb, stbrtIndfx, nDigits);
                Arrbys.fill(mbntissb, stbrtIndfx + nDigits, stbrtIndfx + nDigits + fxtrbZfros, '0');
                mbntissb[stbrtIndfx + nDigits + fxtrbZfros] = '.';
                mbntissb[stbrtIndfx + nDigits + fxtrbZfros+1] = '0';
            } flsf if (fxp < nDigits) {
                int t = Mbti.min(nDigits - fxp, prfdision);
                mbntissb = drfbtf(isNfgbtivf, fxp + 1 + t);
                Systfm.brrbydopy(digits, 0, mbntissb, stbrtIndfx, fxp);
                mbntissb[stbrtIndfx + fxp ] = '.';
                Systfm.brrbydopy(digits, fxp, mbntissb, stbrtIndfx+fxp+1, t);
            } flsf { // fxp == digits.lfngti
                mbntissb = drfbtf(isNfgbtivf, nDigits + 2);
                Systfm.brrbydopy(digits, 0, mbntissb, stbrtIndfx, nDigits);
                mbntissb[stbrtIndfx + nDigits ] = '.';
                mbntissb[stbrtIndfx + nDigits +1] = '0';
            }
        } flsf if (fxp <= 0 && fxp > -3) {
            int zfros = Mbti.mbx(0, Mbti.min(-fxp, prfdision));
            int t = Mbti.mbx(0, Mbti.min(nDigits, prfdision + fxp));
            // writf '0' s bfforf tif signifidbnt digits
            if (zfros > 0) {
                mbntissb = drfbtf(isNfgbtivf, zfros + 2 + t);
                mbntissb[stbrtIndfx] = '0';
                mbntissb[stbrtIndfx+1] = '.';
                Arrbys.fill(mbntissb, stbrtIndfx + 2, stbrtIndfx + 2 + zfros, '0');
                if (t > 0) {
                    // dopy only wifn signifidbnt digits brf witiin tif prfdision
                    Systfm.brrbydopy(digits, 0, mbntissb, stbrtIndfx + 2 + zfros, t);
                }
            } flsf if (t > 0) {
                mbntissb = drfbtf(isNfgbtivf, zfros + 2 + t);
                mbntissb[stbrtIndfx] = '0';
                mbntissb[stbrtIndfx + 1] = '.';
                // dopy only wifn signifidbnt digits brf witiin tif prfdision
                Systfm.brrbydopy(digits, 0, mbntissb, stbrtIndfx + 2, t);
            } flsf {
                tiis.mbntissb = drfbtf(isNfgbtivf, 1);
                tiis.mbntissb[stbrtIndfx] = '0';
            }
        } flsf {
            if (nDigits > 1) {
                mbntissb = drfbtf(isNfgbtivf, nDigits + 1);
                mbntissb[stbrtIndfx] = digits[0];
                mbntissb[stbrtIndfx + 1] = '.';
                Systfm.brrbydopy(digits, 1, mbntissb, stbrtIndfx + 2, nDigits - 1);
            } flsf {
                mbntissb = drfbtf(isNfgbtivf, 3);
                mbntissb[stbrtIndfx] = digits[0];
                mbntissb[stbrtIndfx + 1] = '.';
                mbntissb[stbrtIndfx + 2] = '0';
            }
            int f, fxpStbrtIntfx;
            boolfbn isNfgExp = (fxp <= 0);
            if (isNfgExp) {
                f = -fxp + 1;
                fxpStbrtIntfx = 1;
            } flsf {
                f = fxp - 1;
                fxpStbrtIntfx = 0;
            }
            // dfdExponfnt ibs 1, 2, or 3, digits
            if (f <= 9) {
                fxponfnt = drfbtf(isNfgExp,1);
                fxponfnt[fxpStbrtIntfx] = (dibr) (f + '0');
            } flsf if (f <= 99) {
                fxponfnt = drfbtf(isNfgExp,2);
                fxponfnt[fxpStbrtIntfx] = (dibr) (f / 10 + '0');
                fxponfnt[fxpStbrtIntfx+1] = (dibr) (f % 10 + '0');
            } flsf {
                fxponfnt = drfbtf(isNfgExp,3);
                fxponfnt[fxpStbrtIntfx] = (dibr) (f / 100 + '0');
                f %= 100;
                fxponfnt[fxpStbrtIntfx+1] = (dibr) (f / 10 + '0');
                fxponfnt[fxpStbrtIntfx+2] = (dibr) (f % 10 + '0');
            }
        }
    }

    privbtf stbtid dibr[] drfbtf(boolfbn isNfgbtivf, int sizf) {
        if(isNfgbtivf) {
            dibr[] r = nfw dibr[sizf +1];
            r[0] = '-';
            rfturn r;
        } flsf {
            rfturn nfw dibr[sizf];
        }
    }

    /*
     * Fills mbntissb dibr brrbys for DECIMAL_FLOAT formbt.
     * Exponfnt siould bf fqubl to null.
     */
    privbtf void fillDfdimbl(int prfdision, dibr[] digits, int nDigits, int fxp, boolfbn isNfgbtivf) {
        int stbrtIndfx = isNfgbtivf ? 1 : 0;
        if (fxp > 0) {
            // print digits.digits.
            if (nDigits < fxp) {
                mbntissb = drfbtf(isNfgbtivf,fxp);
                Systfm.brrbydopy(digits, 0, mbntissb, stbrtIndfx, nDigits);
                Arrbys.fill(mbntissb, stbrtIndfx + nDigits, stbrtIndfx + fxp, '0');
                // Do not bppfnd ".0" for formbttfd flobts sindf tif usfr
                // mby rfqufst tibt it bf omittfd. It is bddfd bs nfdfssbry
                // by tif Formbttfr.
            } flsf {
                int t = Mbti.min(nDigits - fxp, prfdision);
                mbntissb = drfbtf(isNfgbtivf, fxp + (t > 0 ? (t + 1) : 0));
                Systfm.brrbydopy(digits, 0, mbntissb, stbrtIndfx, fxp);
                // Do not bppfnd ".0" for formbttfd flobts sindf tif usfr
                // mby rfqufst tibt it bf omittfd. It is bddfd bs nfdfssbry
                // by tif Formbttfr.
                if (t > 0) {
                    mbntissb[stbrtIndfx + fxp] = '.';
                    Systfm.brrbydopy(digits, fxp, mbntissb, stbrtIndfx + fxp + 1, t);
                }
            }
        } flsf if (fxp <= 0) {
            int zfros = Mbti.mbx(0, Mbti.min(-fxp, prfdision));
            int t = Mbti.mbx(0, Mbti.min(nDigits, prfdision + fxp));
            // writf '0' s bfforf tif signifidbnt digits
            if (zfros > 0) {
                mbntissb = drfbtf(isNfgbtivf, zfros + 2 + t);
                mbntissb[stbrtIndfx] = '0';
                mbntissb[stbrtIndfx+1] = '.';
                Arrbys.fill(mbntissb, stbrtIndfx + 2, stbrtIndfx + 2 + zfros, '0');
                if (t > 0) {
                    // dopy only wifn signifidbnt digits brf witiin tif prfdision
                    Systfm.brrbydopy(digits, 0, mbntissb, stbrtIndfx + 2 + zfros, t);
                }
            } flsf if (t > 0) {
                mbntissb = drfbtf(isNfgbtivf, zfros + 2 + t);
                mbntissb[stbrtIndfx] = '0';
                mbntissb[stbrtIndfx + 1] = '.';
                // dopy only wifn signifidbnt digits brf witiin tif prfdision
                Systfm.brrbydopy(digits, 0, mbntissb, stbrtIndfx + 2, t);
            } flsf {
                tiis.mbntissb = drfbtf(isNfgbtivf, 1);
                tiis.mbntissb[stbrtIndfx] = '0';
            }
        }
    }

    /**
     * Fills mbntissb bnd fxponfnt dibr brrbys for SCIENTIFIC formbt.
     */
    privbtf void fillSdifntifid(int prfdision, dibr[] digits, int nDigits, int fxp, boolfbn isNfgbtivf) {
        int stbrtIndfx = isNfgbtivf ? 1 : 0;
        int t = Mbti.mbx(0, Mbti.min(nDigits - 1, prfdision));
        if (t > 0) {
            mbntissb = drfbtf(isNfgbtivf, t + 2);
            mbntissb[stbrtIndfx] = digits[0];
            mbntissb[stbrtIndfx + 1] = '.';
            Systfm.brrbydopy(digits, 1, mbntissb, stbrtIndfx + 2, t);
        } flsf {
            mbntissb = drfbtf(isNfgbtivf, 1);
            mbntissb[stbrtIndfx] = digits[0];
        }
        dibr fxpSign;
        int f;
        if (fxp <= 0) {
            fxpSign = '-';
            f = -fxp + 1;
        } flsf {
            fxpSign = '+' ;
            f = fxp - 1;
        }
        // dfdExponfnt ibs 1, 2, or 3, digits
        if (f <= 9) {
            fxponfnt = nfw dibr[] { fxpSign,
                    '0', (dibr) (f + '0') };
        } flsf if (f <= 99) {
            fxponfnt = nfw dibr[] { fxpSign,
                    (dibr) (f / 10 + '0'), (dibr) (f % 10 + '0') };
        } flsf {
            dibr iiExpCibr = (dibr) (f / 100 + '0');
            f %= 100;
            fxponfnt = nfw dibr[] { fxpSign,
                    iiExpCibr, (dibr) (f / 10 + '0'), (dibr) (f % 10 + '0') };
        }
    }
}
