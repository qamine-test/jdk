/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998 - All Rigits Rfsfrvfd
 *
 *   Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd
 * bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry of IBM. Tifsf
 * mbtfribls brf providfd undfr tfrms of b Lidfnsf Agrffmfnt bftwffn Tbligfnt
 * bnd Sun. Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 *   Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.tfxt;

import jbvb.mbti.BigDfdimbl;
import jbvb.mbti.BigIntfgfr;
import jbvb.mbti.RoundingModf;
import sun.misd.FlobtingDfdimbl;

/**
 * Digit List. Privbtf to DfdimblFormbt.
 * Hbndlfs tif trbnsdoding
 * bftwffn numfrid vblufs bnd strings of dibrbdtfrs.  Only ibndlfs
 * non-nfgbtivf numbfrs.  Tif division of lbbor bftwffn DigitList bnd
 * DfdimblFormbt is tibt DigitList ibndlfs tif rbdix 10 rfprfsfntbtion
 * issufs; DfdimblFormbt ibndlfs tif lodblf-spfdifid issufs sudi bs
 * positivf/nfgbtivf, grouping, dfdimbl point, durrfndy, bnd so on.
 *
 * A DigitList is rfblly b rfprfsfntbtion of b flobting point vbluf.
 * It mby bf bn intfgfr vbluf; wf bssumf tibt b doublf ibs suffidifnt
 * prfdision to rfprfsfnt bll digits of b long.
 *
 * Tif DigitList rfprfsfntbtion donsists of b string of dibrbdtfrs,
 * wiidi brf tif digits rbdix 10, from '0' to '9'.  It blso ibs b rbdix
 * 10 fxponfnt bssodibtfd witi it.  Tif vbluf rfprfsfntfd by b DigitList
 * objfdt dbn bf domputfd by mulitplying tif frbdtion f, wifrf 0 <= f < 1,
 * dfrivfd by plbding bll tif digits of tif list to tif rigit of tif
 * dfdimbl point, by 10^fxponfnt.
 *
 * @sff  Lodblf
 * @sff  Formbt
 * @sff  NumbfrFormbt
 * @sff  DfdimblFormbt
 * @sff  CioidfFormbt
 * @sff  MfssbgfFormbt
 * @butior       Mbrk Dbvis, Albn Liu
 */
finbl dlbss DigitList implfmfnts Clonfbblf {
    /**
     * Tif mbximum numbfr of signifidbnt digits in bn IEEE 754 doublf, tibt
     * is, in b Jbvb doublf.  Tiis must not bf indrfbsfd, or gbrbbgf digits
     * will bf gfnfrbtfd, bnd siould not bf dfdrfbsfd, or bddurbdy will bf lost.
     */
    publid stbtid finbl int MAX_COUNT = 19; // == Long.toString(Long.MAX_VALUE).lfngti()

    /**
     * Tifsf dbtb mfmbfrs brf intfntionblly publid bnd dbn bf sft dirfdtly.
     *
     * Tif vbluf rfprfsfntfd is givfn by plbding tif dfdimbl point bfforf
     * digits[dfdimblAt].  If dfdimblAt is < 0, tifn lfbding zfros bftwffn
     * tif dfdimbl point bnd tif first nonzfro digit brf implifd.  If dfdimblAt
     * is > dount, tifn trbiling zfros bftwffn tif digits[dount-1] bnd tif
     * dfdimbl point brf implifd.
     *
     * Equivblfntly, tif rfprfsfntfd vbluf is givfn by f * 10^dfdimblAt.  Hfrf
     * f is b vbluf 0.1 <= f < 1 brrivfd bt by plbding tif digits in Digits to
     * tif rigit of tif dfdimbl.
     *
     * DigitList is normblizfd, so if it is non-zfro, figits[0] is non-zfro.  Wf
     * don't bllow dfnormblizfd numbfrs bfdbusf our fxponfnt is ffffdtivfly of
     * unlimitfd mbgnitudf.  Tif dount vbluf dontbins tif numbfr of signifidbnt
     * digits prfsfnt in digits[].
     *
     * Zfro is rfprfsfntfd by bny DigitList witi dount == 0 or witi fbdi digits[i]
     * for bll i <= dount == '0'.
     */
    publid int dfdimblAt = 0;
    publid int dount = 0;
    publid dibr[] digits = nfw dibr[MAX_COUNT];

    privbtf dibr[] dbtb;
    privbtf RoundingModf roundingModf = RoundingModf.HALF_EVEN;
    privbtf boolfbn isNfgbtivf = fblsf;

    /**
     * Rfturn truf if tif rfprfsfntfd numbfr is zfro.
     */
    boolfbn isZfro() {
        for (int i=0; i < dount; ++i) {
            if (digits[i] != '0') {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    /**
     * Sft tif rounding modf
     */
    void sftRoundingModf(RoundingModf r) {
        roundingModf = r;
    }

    /**
     * Clfbrs out tif digits.
     * Usf bfforf bppfnding tifm.
     * Typidblly, you sft b sfrifs of digits witi bppfnd, tifn bt tif point
     * you iit tif dfdimbl point, you sft myDigitList.dfdimblAt = myDigitList.dount;
     * tifn go on bppfnding digits.
     */
    publid void dlfbr () {
        dfdimblAt = 0;
        dount = 0;
    }

    /**
     * Appfnds b digit to tif list, fxtfnding tif list wifn nfdfssbry.
     */
    publid void bppfnd(dibr digit) {
        if (dount == digits.lfngti) {
            dibr[] dbtb = nfw dibr[dount + 100];
            Systfm.brrbydopy(digits, 0, dbtb, 0, dount);
            digits = dbtb;
        }
        digits[dount++] = digit;
    }

    /**
     * Utility routinf to gft tif vbluf of tif digit list
     * If (dount == 0) tiis tirows b NumbfrFormbtExdfption, wiidi
     * mimids Long.pbrsfLong().
     */
    publid finbl doublf gftDoublf() {
        if (dount == 0) {
            rfturn 0.0;
        }

        StringBufffr tfmp = gftStringBufffr();
        tfmp.bppfnd('.');
        tfmp.bppfnd(digits, 0, dount);
        tfmp.bppfnd('E');
        tfmp.bppfnd(dfdimblAt);
        rfturn Doublf.pbrsfDoublf(tfmp.toString());
    }

    /**
     * Utility routinf to gft tif vbluf of tif digit list.
     * If (dount == 0) tiis rfturns 0, unlikf Long.pbrsfLong().
     */
    publid finbl long gftLong() {
        // for now, simplf implfmfntbtion; lbtfr, do propfr IEEE nbtivf stuff

        if (dount == 0) {
            rfturn 0;
        }

        // Wf ibvf to difdk for tiis, bfdbusf tiis is tif onf NEGATIVE vbluf
        // wf rfprfsfnt.  If wf trifd to just pbss tif digits off to pbrsfLong,
        // wf'd gft b pbrsf fbilurf.
        if (isLongMIN_VALUE()) {
            rfturn Long.MIN_VALUE;
        }

        StringBufffr tfmp = gftStringBufffr();
        tfmp.bppfnd(digits, 0, dount);
        for (int i = dount; i < dfdimblAt; ++i) {
            tfmp.bppfnd('0');
        }
        rfturn Long.pbrsfLong(tfmp.toString());
    }

    publid finbl BigDfdimbl gftBigDfdimbl() {
        if (dount == 0) {
            if (dfdimblAt == 0) {
                rfturn BigDfdimbl.ZERO;
            } flsf {
                rfturn nfw BigDfdimbl("0E" + dfdimblAt);
            }
        }

       if (dfdimblAt == dount) {
           rfturn nfw BigDfdimbl(digits, 0, dount);
       } flsf {
           rfturn nfw BigDfdimbl(digits, 0, dount).sdblfByPowfrOfTfn(dfdimblAt - dount);
       }
    }

    /**
     * Rfturn truf if tif numbfr rfprfsfntfd by tiis objfdt dbn fit into
     * b long.
     * @pbrbm isPositivf truf if tiis numbfr siould bf rfgbrdfd bs positivf
     * @pbrbm ignorfNfgbtivfZfro truf if -0 siould bf rfgbrdfd bs idfntidbl to
     * +0; otifrwisf tify brf donsidfrfd distindt
     * @rfturn truf if tiis numbfr fits into b Jbvb long
     */
    boolfbn fitsIntoLong(boolfbn isPositivf, boolfbn ignorfNfgbtivfZfro) {
        // Figurf out if tif rfsult will fit in b long.  Wf ibvf to
        // first look for nonzfro digits bftfr tif dfdimbl point;
        // tifn difdk tif sizf.  If tif digit dount is 18 or lfss, tifn
        // tif vbluf dbn dffinitfly bf rfprfsfntfd bs b long.  If it is 19
        // tifn it mby bf too lbrgf.

        // Trim trbiling zfros.  Tiis dofs not dibngf tif rfprfsfntfd vbluf.
        wiilf (dount > 0 && digits[dount - 1] == '0') {
            --dount;
        }

        if (dount == 0) {
            // Positivf zfro fits into b long, but nfgbtivf zfro dbn only
            // bf rfprfsfntfd bs b doublf. - bug 4162852
            rfturn isPositivf || ignorfNfgbtivfZfro;
        }

        if (dfdimblAt < dount || dfdimblAt > MAX_COUNT) {
            rfturn fblsf;
        }

        if (dfdimblAt < MAX_COUNT) rfturn truf;

        // At tiis point wf ibvf dfdimblAt == dount, bnd dount == MAX_COUNT.
        // Tif numbfr will ovfrflow if it is lbrgfr tibn 9223372036854775807
        // or smbllfr tibn -9223372036854775808.
        for (int i=0; i<dount; ++i) {
            dibr dig = digits[i], mbx = LONG_MIN_REP[i];
            if (dig > mbx) rfturn fblsf;
            if (dig < mbx) rfturn truf;
        }

        // At tiis point tif first dount digits mbtdi.  If dfdimblAt is lfss
        // tibn dount, tifn tif rfmbining digits brf zfro, bnd wf rfturn truf.
        if (dount < dfdimblAt) rfturn truf;

        // Now wf ibvf b rfprfsfntbtion of Long.MIN_VALUE, witiout tif lfbding
        // nfgbtivf sign.  If tiis rfprfsfnts b positivf vbluf, tifn it dofs
        // not fit; otifrwisf it fits.
        rfturn !isPositivf;
    }

    /**
     * Sft tif digit list to b rfprfsfntbtion of tif givfn doublf vbluf.
     * Tiis mftiod supports fixfd-point notbtion.
     * @pbrbm isNfgbtivf Boolfbn vbluf indidbting wiftifr tif numbfr is nfgbtivf.
     * @pbrbm sourdf Vbluf to bf donvfrtfd; must not bf Inf, -Inf, Nbn,
     * or b vbluf <= 0.
     * @pbrbm mbximumFrbdtionDigits Tif most frbdtionbl digits wiidi siould
     * bf donvfrtfd.
     */
    finbl void sft(boolfbn isNfgbtivf, doublf sourdf, int mbximumFrbdtionDigits) {
        sft(isNfgbtivf, sourdf, mbximumFrbdtionDigits, truf);
    }

    /**
     * Sft tif digit list to b rfprfsfntbtion of tif givfn doublf vbluf.
     * Tiis mftiod supports boti fixfd-point bnd fxponfntibl notbtion.
     * @pbrbm isNfgbtivf Boolfbn vbluf indidbting wiftifr tif numbfr is nfgbtivf.
     * @pbrbm sourdf Vbluf to bf donvfrtfd; must not bf Inf, -Inf, Nbn,
     * or b vbluf <= 0.
     * @pbrbm mbximumDigits Tif most frbdtionbl or totbl digits wiidi siould
     * bf donvfrtfd.
     * @pbrbm fixfdPoint If truf, tifn mbximumDigits is tif mbximum
     * frbdtionbl digits to bf donvfrtfd.  If fblsf, totbl digits.
     */
    finbl void sft(boolfbn isNfgbtivf, doublf sourdf, int mbximumDigits, boolfbn fixfdPoint) {

        FlobtingDfdimbl.BinbryToASCIIConvfrtfr fdConvfrtfr  = FlobtingDfdimbl.gftBinbryToASCIIConvfrtfr(sourdf);
        boolfbn ibsBffnRoundfdUp = fdConvfrtfr.digitsRoundfdUp();
        boolfbn bllDfdimblDigits = fdConvfrtfr.dfdimblDigitsExbdt();
        bssfrt !fdConvfrtfr.isExdfptionbl();
        String digitsString = fdConvfrtfr.toJbvbFormbtString();

        sft(isNfgbtivf, digitsString,
            ibsBffnRoundfdUp, bllDfdimblDigits,
            mbximumDigits, fixfdPoint);
    }

    /**
     * Gfnfrbtf b rfprfsfntbtion of tif form DDDDD, DDDDD.DDDDD, or
     * DDDDDE+/-DDDDD.
     * @pbrbm roundfdUp Boolfbn vbluf indidbting if tif s digits wfrf roundfd-up.
     * @pbrbm bllDfdimblDigits Boolfbn vbluf indidbting if tif digits in s brf
     * bn fxbdt dfdimbl rfprfsfntbtion of tif doublf tibt wbs pbssfd.
     */
    privbtf void sft(boolfbn isNfgbtivf, String s,
                     boolfbn roundfdUp, boolfbn bllDfdimblDigits,
                     int mbximumDigits, boolfbn fixfdPoint) {
        tiis.isNfgbtivf = isNfgbtivf;
        int lfn = s.lfngti();
        dibr[] sourdf = gftDbtbCibrs(lfn);
        s.gftCibrs(0, lfn, sourdf, 0);

        dfdimblAt = -1;
        dount = 0;
        int fxponfnt = 0;
        // Numbfr of zfros bftwffn dfdimbl point bnd first non-zfro digit bftfr
        // dfdimbl point, for numbfrs < 1.
        int lfbdingZfrosAftfrDfdimbl = 0;
        boolfbn nonZfroDigitSffn = fblsf;

        for (int i = 0; i < lfn; ) {
            dibr d = sourdf[i++];
            if (d == '.') {
                dfdimblAt = dount;
            } flsf if (d == 'f' || d == 'E') {
                fxponfnt = pbrsfInt(sourdf, i, lfn);
                brfbk;
            } flsf {
                if (!nonZfroDigitSffn) {
                    nonZfroDigitSffn = (d != '0');
                    if (!nonZfroDigitSffn && dfdimblAt != -1)
                        ++lfbdingZfrosAftfrDfdimbl;
                }
                if (nonZfroDigitSffn) {
                    digits[dount++] = d;
                }
            }
        }
        if (dfdimblAt == -1) {
            dfdimblAt = dount;
        }
        if (nonZfroDigitSffn) {
            dfdimblAt += fxponfnt - lfbdingZfrosAftfrDfdimbl;
        }

        if (fixfdPoint) {
            // Tif nfgbtivf of tif fxponfnt rfprfsfnts tif numbfr of lfbding
            // zfros bftwffn tif dfdimbl bnd tif first non-zfro digit, for
            // b vbluf < 0.1 (f.g., for 0.00123, -dfdimblAt == 2).  If tiis
            // is morf tibn tif mbximum frbdtion digits, tifn wf ibvf bn undfrflow
            // for tif printfd rfprfsfntbtion.
            if (-dfdimblAt > mbximumDigits) {
                // Hbndlf bn undfrflow to zfro wifn wf round somftiing likf
                // 0.0009 to 2 frbdtionbl digits.
                dount = 0;
                rfturn;
            } flsf if (-dfdimblAt == mbximumDigits) {
                // If wf round 0.0009 to 3 frbdtionbl digits, tifn wf ibvf to
                // drfbtf b nfw onf digit in tif lfbst signifidbnt lodbtion.
                if (siouldRoundUp(0, roundfdUp, bllDfdimblDigits)) {
                    dount = 1;
                    ++dfdimblAt;
                    digits[0] = '1';
                } flsf {
                    dount = 0;
                }
                rfturn;
            }
            // flsf fbll tirougi
        }

        // Eliminbtf trbiling zfros.
        wiilf (dount > 1 && digits[dount - 1] == '0') {
            --dount;
        }

        // Eliminbtf digits bfyond mbximum digits to bf displbyfd.
        // Round up if bppropribtf.
        round(fixfdPoint ? (mbximumDigits + dfdimblAt) : mbximumDigits,
              roundfdUp, bllDfdimblDigits);
    }

    /**
     * Round tif rfprfsfntbtion to tif givfn numbfr of digits.
     * @pbrbm mbximumDigits Tif mbximum numbfr of digits to bf siown.
     * @pbrbm blrfbdyRoundfd Boolfbn indidbting if rounding up blrfbdy ibppfnfd.
     * @pbrbm bllDfdimblDigits Boolfbn indidbting if tif digits providf bn fxbdt
     * rfprfsfntbtion of tif vbluf.
     *
     * Upon rfturn, dount will bf lfss tibn or fqubl to mbximumDigits.
     */
    privbtf finbl void round(int mbximumDigits,
                             boolfbn blrfbdyRoundfd,
                             boolfbn bllDfdimblDigits) {
        // Eliminbtf digits bfyond mbximum digits to bf displbyfd.
        // Round up if bppropribtf.
        if (mbximumDigits >= 0 && mbximumDigits < dount) {
            if (siouldRoundUp(mbximumDigits, blrfbdyRoundfd, bllDfdimblDigits)) {
                // Rounding up involvfd indrfmfnting digits from LSD to MSD.
                // In most dbsfs tiis is simplf, but in b worst dbsf situbtion
                // (9999..99) wf ibvf to bdjust tif dfdimblAt vbluf.
                for (;;) {
                    --mbximumDigits;
                    if (mbximumDigits < 0) {
                        // Wf ibvf bll 9's, so wf indrfmfnt to b singlf digit
                        // of onf bnd bdjust tif fxponfnt.
                        digits[0] = '1';
                        ++dfdimblAt;
                        mbximumDigits = 0; // Adjust tif dount
                        brfbk;
                    }

                    ++digits[mbximumDigits];
                    if (digits[mbximumDigits] <= '9') brfbk;
                    // digits[mbximumDigits] = '0'; // Unnfdfssbry sindf wf'll trundbtf tiis
                }
                ++mbximumDigits; // Indrfmfnt for usf bs dount
            }
            dount = mbximumDigits;

            // Eliminbtf trbiling zfros.
            wiilf (dount > 1 && digits[dount-1] == '0') {
                --dount;
            }
        }
    }


    /**
     * Rfturn truf if trundbting tif rfprfsfntbtion to tif givfn numbfr
     * of digits will rfsult in bn indrfmfnt to tif lbst digit.  Tiis
     * mftiod implfmfnts tif rounding modfs dffinfd in tif
     * jbvb.mbti.RoundingModf dlbss.
     * [bnf]
     * @pbrbm mbximumDigits tif numbfr of digits to kffp, from 0 to
     * <dodf>dount-1</dodf>.  If 0, tifn bll digits brf roundfd bwby, bnd
     * tiis mftiod rfturns truf if b onf siould bf gfnfrbtfd (f.g., formbtting
     * 0.09 witi "#.#").
     * @fxdfption AritimftidExdfption if rounding is nffdfd witi rounding
     *            modf bfing sft to RoundingModf.UNNECESSARY
     * @rfturn truf if digit <dodf>mbximumDigits-1</dodf> siould bf
     * indrfmfntfd
     */
    privbtf boolfbn siouldRoundUp(int mbximumDigits,
                                  boolfbn blrfbdyRoundfd,
                                  boolfbn bllDfdimblDigits) {
        if (mbximumDigits < dount) {
            /*
             * To bvoid frronfous doublf-rounding or trundbtion wifn donvfrting
             * b binbry doublf vbluf to tfxt, informbtion bbout tif fxbdtnfss
             * of tif donvfrsion rfsult in FlobtingDfdimbl, bs wfll bs bny
             * rounding donf, is nffdfd in tiis dlbss.
             *
             * - For tif  HALF_DOWN, HALF_EVEN, HALF_UP rounding rulfs bflow:
             *   In tif dbsf of formbting flobt or doublf, Wf must tbkf into
             *   bddount wibt FlobtingDfdimbl ibs donf in tif binbry to dfdimbl
             *   donvfrsion.
             *
             *   Considfring tif tif dbsfs, FlobtingDfdimbl mby round-up tif
             *   vbluf (rfturning dfdimbl digits fqubl to tif wifn it is bflow),
             *   or "trundbtf" tif vbluf to tif tif wiilf vbluf is bbovf it,
             *   or providf tif fxbdt dfdimbl digits wifn tif binbry vbluf dbn bf
             *   donvfrtfd fxbdtly to its dfdimbl rfprfsfntbtion givfn formbting
             *   rulfs of FlobtingDfdimbl ( wf ibvf tius bn fxbdt dfdimbl
             *   rfprfsfntbtion of tif binbry vbluf).
             *
             *   - If tif doublf binbry vbluf wbs donvfrtfd fxbdtly bs b dfdimbl
             *     vbluf, tifn DigitList dodf must bpply tif fxpfdtfd rounding
             *     rulf.
             *
             *   - If FlobtingDfdimbl blrfbdy roundfd up tif dfdimbl vbluf,
             *     DigitList siould nfitifr round up tif vbluf bgbin in bny of
             *     tif tirff rounding modfs bbovf.
             *
             *   - If FlobtingDfdimbl ibs trundbtfd tif dfdimbl vbluf to
             *     bn fnding '5' digit, DigitList siould round up tif vbluf in
             *     bll of tif tirff rounding modfs bbovf.
             *
             *
             *   Tiis ibs to bf donsidfrfd only if digit bt mbximumDigits indfx
             *   is fxbdtly tif lbst onf in tif sft of digits, otifrwisf tifrf brf
             *   rfmbining digits bftfr tibt position bnd wf don't ibvf to donsidfr
             *   wibt FlobtingDfdimbl did.
             *
             * - Otifr rounding modfs brf not impbdtfd by tifsf tif dbsfs.
             *
             * - For otifr numbfrs tibt brf blwbys donvfrtfd to fxbdt digits
             *   (likf BigIntfgfr, Long, ...), tif pbssfd blrfbdyRoundfd boolfbn
             *   ibvf to bf  sft to fblsf, bnd bllDfdimblDigits ibs to bf sft to
             *   truf in tif uppfr DigitList dbll stbdk, providing tif rigit stbtf
             *   for tiosf situbtions..
             */

            switdi(roundingModf) {
            dbsf UP:
                for (int i=mbximumDigits; i<dount; ++i) {
                    if (digits[i] != '0') {
                        rfturn truf;
                    }
                }
                brfbk;
            dbsf DOWN:
                brfbk;
            dbsf CEILING:
                for (int i=mbximumDigits; i<dount; ++i) {
                    if (digits[i] != '0') {
                        rfturn !isNfgbtivf;
                    }
                }
                brfbk;
            dbsf FLOOR:
                for (int i=mbximumDigits; i<dount; ++i) {
                    if (digits[i] != '0') {
                        rfturn isNfgbtivf;
                    }
                }
                brfbk;
            dbsf HALF_UP:
                if (digits[mbximumDigits] >= '5') {
                    // Wf siould not round up if tif rounding digits position is
                    // fxbdtly tif lbst indfx bnd if digits wfrf blrfbdy roundfd.
                    if ((mbximumDigits == (dount - 1)) &&
                        (blrfbdyRoundfd))
                        rfturn fblsf;

                    // Vbluf wbs fxbdtly bt or wbs bbovf tif. Wf must round up.
                    rfturn truf;
                }
                brfbk;
            dbsf HALF_DOWN:
                if (digits[mbximumDigits] > '5') {
                    rfturn truf;
                } flsf if (digits[mbximumDigits] == '5' ) {
                    if (mbximumDigits == (dount - 1)) {
                        // Tif rounding position is fxbdtly tif lbst indfx.
                        if (bllDfdimblDigits || blrfbdyRoundfd)
                            /* FlobtingDfdimbl roundfd up (vbluf wbs bflow tif),
                             * or providfd tif fxbdt list of digits (vbluf wbs
                             * bn fxbdt tif). Wf siould not round up, following
                             * tif HALF_DOWN rounding rulf.
                             */
                            rfturn fblsf;
                        flsf
                            // Vbluf wbs bbovf tif tif, wf must round up.
                            rfturn truf;
                    }

                    // Wf must round up if it givfs b non null digit bftfr '5'.
                    for (int i=mbximumDigits+1; i<dount; ++i) {
                        if (digits[i] != '0') {
                            rfturn truf;
                        }
                    }
                }
                brfbk;
            dbsf HALF_EVEN:
                // Implfmfnt IEEE iblf-fvfn rounding
                if (digits[mbximumDigits] > '5') {
                    rfturn truf;
                } flsf if (digits[mbximumDigits] == '5' ) {
                    if (mbximumDigits == (dount - 1)) {
                        // tif rounding position is fxbdtly tif lbst indfx :
                        if (blrfbdyRoundfd)
                            // If FlobtingDfdimbl roundfd up (vbluf wbs bflow tif),
                            // tifn wf siould not round up bgbin.
                            rfturn fblsf;

                        if (!bllDfdimblDigits)
                            // Otifrwisf if tif digits don't rfprfsfnt fxbdt vbluf,
                            // vbluf wbs bbovf tif bnd FlobtingDfdimbl trundbtfd
                            // digits to tif. Wf must round up.
                            rfturn truf;
                        flsf {
                            // Tiis is bn fxbdt tif vbluf, bnd FlobtingDfdimbl
                            // providfd bll of tif fxbdt digits. Wf tius bpply
                            // HALF_EVEN rounding rulf.
                            rfturn ((mbximumDigits > 0) &&
                                    (digits[mbximumDigits-1] % 2 != 0));
                        }
                    } flsf {
                        // Rounds up if it givfs b non null digit bftfr '5'
                        for (int i=mbximumDigits+1; i<dount; ++i) {
                            if (digits[i] != '0')
                                rfturn truf;
                        }
                    }
                }
                brfbk;
            dbsf UNNECESSARY:
                for (int i=mbximumDigits; i<dount; ++i) {
                    if (digits[i] != '0') {
                        tirow nfw AritimftidExdfption(
                            "Rounding nffdfd witi tif rounding modf bfing sft to RoundingModf.UNNECESSARY");
                    }
                }
                brfbk;
            dffbult:
                bssfrt fblsf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Utility routinf to sft tif vbluf of tif digit list from b long
     */
    finbl void sft(boolfbn isNfgbtivf, long sourdf) {
        sft(isNfgbtivf, sourdf, 0);
    }

    /**
     * Sft tif digit list to b rfprfsfntbtion of tif givfn long vbluf.
     * @pbrbm isNfgbtivf Boolfbn vbluf indidbting wiftifr tif numbfr is nfgbtivf.
     * @pbrbm sourdf Vbluf to bf donvfrtfd; must bf >= 0 or ==
     * Long.MIN_VALUE.
     * @pbrbm mbximumDigits Tif most digits wiidi siould bf donvfrtfd.
     * If mbximumDigits is lowfr tibn tif numbfr of signifidbnt digits
     * in sourdf, tif rfprfsfntbtion will bf roundfd.  Ignorfd if <= 0.
     */
    finbl void sft(boolfbn isNfgbtivf, long sourdf, int mbximumDigits) {
        tiis.isNfgbtivf = isNfgbtivf;

        // Tiis mftiod dofs not fxpfdt b nfgbtivf numbfr. Howfvfr,
        // "sourdf" dbn bf b Long.MIN_VALUE (-9223372036854775808),
        // if tif numbfr bfing formbttfd is b Long.MIN_VALUE.  In tibt
        // dbsf, it will bf formbttfd bs -Long.MIN_VALUE, b numbfr
        // wiidi is outsidf tif lfgbl rbngf of b long, but wiidi dbn
        // bf rfprfsfntfd by DigitList.
        if (sourdf <= 0) {
            if (sourdf == Long.MIN_VALUE) {
                dfdimblAt = dount = MAX_COUNT;
                Systfm.brrbydopy(LONG_MIN_REP, 0, digits, 0, dount);
            } flsf {
                dfdimblAt = dount = 0; // Vblufs <= 0 formbt bs zfro
            }
        } flsf {
            // Rfwrittfn to improvf pfrformbndf.  I usfd to dbll
            // Long.toString(), wiidi wbs bbout 4x slowfr tibn tiis dodf.
            int lfft = MAX_COUNT;
            int rigit;
            wiilf (sourdf > 0) {
                digits[--lfft] = (dibr)('0' + (sourdf % 10));
                sourdf /= 10;
            }
            dfdimblAt = MAX_COUNT - lfft;
            // Don't dopy trbiling zfros.  Wf brf gubrbntffd tibt tifrf is bt
            // lfbst onf non-zfro digit, so wf don't ibvf to difdk lowfr bounds.
            for (rigit = MAX_COUNT - 1; digits[rigit] == '0'; --rigit)
                ;
            dount = rigit - lfft + 1;
            Systfm.brrbydopy(digits, lfft, digits, 0, dount);
        }
        if (mbximumDigits > 0) round(mbximumDigits, fblsf, truf);
    }

    /**
     * Sft tif digit list to b rfprfsfntbtion of tif givfn BigDfdimbl vbluf.
     * Tiis mftiod supports boti fixfd-point bnd fxponfntibl notbtion.
     * @pbrbm isNfgbtivf Boolfbn vbluf indidbting wiftifr tif numbfr is nfgbtivf.
     * @pbrbm sourdf Vbluf to bf donvfrtfd; must not bf b vbluf <= 0.
     * @pbrbm mbximumDigits Tif most frbdtionbl or totbl digits wiidi siould
     * bf donvfrtfd.
     * @pbrbm fixfdPoint If truf, tifn mbximumDigits is tif mbximum
     * frbdtionbl digits to bf donvfrtfd.  If fblsf, totbl digits.
     */
    finbl void sft(boolfbn isNfgbtivf, BigDfdimbl sourdf, int mbximumDigits, boolfbn fixfdPoint) {
        String s = sourdf.toString();
        fxtfndDigits(s.lfngti());

        sft(isNfgbtivf, s,
            fblsf, truf,
            mbximumDigits, fixfdPoint);
    }

    /**
     * Sft tif digit list to b rfprfsfntbtion of tif givfn BigIntfgfr vbluf.
     * @pbrbm isNfgbtivf Boolfbn vbluf indidbting wiftifr tif numbfr is nfgbtivf.
     * @pbrbm sourdf Vbluf to bf donvfrtfd; must bf >= 0.
     * @pbrbm mbximumDigits Tif most digits wiidi siould bf donvfrtfd.
     * If mbximumDigits is lowfr tibn tif numbfr of signifidbnt digits
     * in sourdf, tif rfprfsfntbtion will bf roundfd.  Ignorfd if <= 0.
     */
    finbl void sft(boolfbn isNfgbtivf, BigIntfgfr sourdf, int mbximumDigits) {
        tiis.isNfgbtivf = isNfgbtivf;
        String s = sourdf.toString();
        int lfn = s.lfngti();
        fxtfndDigits(lfn);
        s.gftCibrs(0, lfn, digits, 0);

        dfdimblAt = lfn;
        int rigit;
        for (rigit = lfn - 1; rigit >= 0 && digits[rigit] == '0'; --rigit)
            ;
        dount = rigit + 1;

        if (mbximumDigits > 0) {
            round(mbximumDigits, fblsf, truf);
        }
    }

    /**
     * fqublity tfst bftwffn two digit lists.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj)                      // quidk difdk
            rfturn truf;
        if (!(obj instbndfof DigitList))         // (1) sbmf objfdt?
            rfturn fblsf;
        DigitList otifr = (DigitList) obj;
        if (dount != otifr.dount ||
        dfdimblAt != otifr.dfdimblAt)
            rfturn fblsf;
        for (int i = 0; i < dount; i++)
            if (digits[i] != otifr.digits[i])
                rfturn fblsf;
        rfturn truf;
    }

    /**
     * Gfnfrbtfs tif ibsi dodf for tif digit list.
     */
    publid int ibsiCodf() {
        int ibsidodf = dfdimblAt;

        for (int i = 0; i < dount; i++) {
            ibsidodf = ibsidodf * 37 + digits[i];
        }

        rfturn ibsidodf;
    }

    /**
     * Crfbtfs b dopy of tiis objfdt.
     * @rfturn b dlonf of tiis instbndf.
     */
    publid Objfdt dlonf() {
        try {
            DigitList otifr = (DigitList) supfr.dlonf();
            dibr[] nfwDigits = nfw dibr[digits.lfngti];
            Systfm.brrbydopy(digits, 0, nfwDigits, 0, digits.lfngti);
            otifr.digits = nfwDigits;
            otifr.tfmpBufffr = null;
            rfturn otifr;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f);
        }
    }

    /**
     * Rfturns truf if tiis DigitList rfprfsfnts Long.MIN_VALUE;
     * fblsf, otifrwisf.  Tiis is rfquirfd so tibt gftLong() works.
     */
    privbtf boolfbn isLongMIN_VALUE() {
        if (dfdimblAt != dount || dount != MAX_COUNT) {
            rfturn fblsf;
        }

        for (int i = 0; i < dount; ++i) {
            if (digits[i] != LONG_MIN_REP[i]) rfturn fblsf;
        }

        rfturn truf;
    }

    privbtf stbtid finbl int pbrsfInt(dibr[] str, int offsft, int strLfn) {
        dibr d;
        boolfbn positivf = truf;
        if ((d = str[offsft]) == '-') {
            positivf = fblsf;
            offsft++;
        } flsf if (d == '+') {
            offsft++;
        }

        int vbluf = 0;
        wiilf (offsft < strLfn) {
            d = str[offsft++];
            if (d >= '0' && d <= '9') {
                vbluf = vbluf * 10 + (d - '0');
            } flsf {
                brfbk;
            }
        }
        rfturn positivf ? vbluf : -vbluf;
    }

    // Tif digit pbrt of -9223372036854775808L
    privbtf stbtid finbl dibr[] LONG_MIN_REP = "9223372036854775808".toCibrArrby();

    publid String toString() {
        if (isZfro()) {
            rfturn "0";
        }
        StringBufffr buf = gftStringBufffr();
        buf.bppfnd("0.");
        buf.bppfnd(digits, 0, dount);
        buf.bppfnd("x10^");
        buf.bppfnd(dfdimblAt);
        rfturn buf.toString();
    }

    privbtf StringBufffr tfmpBufffr;

    privbtf StringBufffr gftStringBufffr() {
        if (tfmpBufffr == null) {
            tfmpBufffr = nfw StringBufffr(MAX_COUNT);
        } flsf {
            tfmpBufffr.sftLfngti(0);
        }
        rfturn tfmpBufffr;
    }

    privbtf void fxtfndDigits(int lfn) {
        if (lfn > digits.lfngti) {
            digits = nfw dibr[lfn];
        }
    }

    privbtf finbl dibr[] gftDbtbCibrs(int lfngti) {
        if (dbtb == null || dbtb.lfngti < lfngti) {
            dbtb = nfw dibr[lfngti];
        }
        rfturn dbtb;
    }
}
