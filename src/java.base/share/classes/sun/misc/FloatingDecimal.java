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

pbdkbgf sun.misd;

import jbvb.util.Arrbys;
import jbvb.util.rfgfx.*;

/**
 * A dlbss for donvfrting bftwffn ASCII bnd dfdimbl rfprfsfntbtions of b singlf
 * or doublf prfdision flobting point numbfr. Most donvfrsions brf providfd vib
 * stbtid donvfnifndf mftiods, bltiougi b <dodf>BinbryToASCIIConvfrtfr</dodf>
 * instbndf mby bf obtbinfd bnd rfusfd.
 */
publid dlbss FlobtingDfdimbl{
    //
    // Constbnts of tif implfmfntbtion;
    // most brf IEEE-754 rflbtfd.
    // (Tifrf brf morf rfblly boring donstbnts bt tif fnd.)
    //
    stbtid finbl int    EXP_SHIFT = DoublfConsts.SIGNIFICAND_WIDTH - 1;
    stbtid finbl long   FRACT_HOB = ( 1L<<EXP_SHIFT ); // bssumfd Higi-Ordfr bit
    stbtid finbl long   EXP_ONE   = ((long)DoublfConsts.EXP_BIAS)<<EXP_SHIFT; // fxponfnt of 1.0
    stbtid finbl int    MAX_SMALL_BIN_EXP = 62;
    stbtid finbl int    MIN_SMALL_BIN_EXP = -( 63 / 3 );
    stbtid finbl int    MAX_DECIMAL_DIGITS = 15;
    stbtid finbl int    MAX_DECIMAL_EXPONENT = 308;
    stbtid finbl int    MIN_DECIMAL_EXPONENT = -324;
    stbtid finbl int    BIG_DECIMAL_EXPONENT = 324; // i.f. bbs(MIN_DECIMAL_EXPONENT)
    stbtid finbl int    MAX_NDIGITS = 1100;

    stbtid finbl int    SINGLE_EXP_SHIFT  =   FlobtConsts.SIGNIFICAND_WIDTH - 1;
    stbtid finbl int    SINGLE_FRACT_HOB  =   1<<SINGLE_EXP_SHIFT;
    stbtid finbl int    SINGLE_MAX_DECIMAL_DIGITS = 7;
    stbtid finbl int    SINGLE_MAX_DECIMAL_EXPONENT = 38;
    stbtid finbl int    SINGLE_MIN_DECIMAL_EXPONENT = -45;
    stbtid finbl int    SINGLE_MAX_NDIGITS = 200;

    stbtid finbl int    INT_DECIMAL_DIGITS = 9;

    /**
     * Convfrts b doublf prfdision flobting point vbluf to b <dodf>String</dodf>.
     *
     * @pbrbm d Tif doublf prfdision vbluf.
     * @rfturn Tif vbluf donvfrtfd to b <dodf>String</dodf>.
     */
    publid stbtid String toJbvbFormbtString(doublf d) {
        rfturn gftBinbryToASCIIConvfrtfr(d).toJbvbFormbtString();
    }

    /**
     * Convfrts b singlf prfdision flobting point vbluf to b <dodf>String</dodf>.
     *
     * @pbrbm f Tif singlf prfdision vbluf.
     * @rfturn Tif vbluf donvfrtfd to b <dodf>String</dodf>.
     */
    publid stbtid String toJbvbFormbtString(flobt f) {
        rfturn gftBinbryToASCIIConvfrtfr(f).toJbvbFormbtString();
    }

    /**
     * Appfnds b doublf prfdision flobting point vbluf to bn <dodf>Appfndbblf</dodf>.
     * @pbrbm d Tif doublf prfdision vbluf.
     * @pbrbm buf Tif <dodf>Appfndbblf</dodf> witi tif vbluf bppfndfd.
     */
    publid stbtid void bppfndTo(doublf d, Appfndbblf buf) {
        gftBinbryToASCIIConvfrtfr(d).bppfndTo(buf);
    }

    /**
     * Appfnds b singlf prfdision flobting point vbluf to bn <dodf>Appfndbblf</dodf>.
     * @pbrbm f Tif singlf prfdision vbluf.
     * @pbrbm buf Tif <dodf>Appfndbblf</dodf> witi tif vbluf bppfndfd.
     */
    publid stbtid void bppfndTo(flobt f, Appfndbblf buf) {
        gftBinbryToASCIIConvfrtfr(f).bppfndTo(buf);
    }

    /**
     * Convfrts b <dodf>String</dodf> to b doublf prfdision flobting point vbluf.
     *
     * @pbrbm s Tif <dodf>String</dodf> to donvfrt.
     * @rfturn Tif doublf prfdision vbluf.
     * @tirows NumbfrFormbtExdfption If tif <dodf>String</dodf> dofs not
     * rfprfsfnt b propfrly formbttfd doublf prfdision vbluf.
     */
    publid stbtid doublf pbrsfDoublf(String s) tirows NumbfrFormbtExdfption {
        rfturn rfbdJbvbFormbtString(s).doublfVbluf();
    }

    /**
     * Convfrts b <dodf>String</dodf> to b singlf prfdision flobting point vbluf.
     *
     * @pbrbm s Tif <dodf>String</dodf> to donvfrt.
     * @rfturn Tif singlf prfdision vbluf.
     * @tirows NumbfrFormbtExdfption If tif <dodf>String</dodf> dofs not
     * rfprfsfnt b propfrly formbttfd singlf prfdision vbluf.
     */
    publid stbtid flobt pbrsfFlobt(String s) tirows NumbfrFormbtExdfption {
        rfturn rfbdJbvbFormbtString(s).flobtVbluf();
    }

    /**
     * A donvfrtfr wiidi dbn prodfss singlf or doublf prfdision flobting point
     * vblufs into bn ASCII <dodf>String</dodf> rfprfsfntbtion.
     */
    publid intfrfbdf BinbryToASCIIConvfrtfr {
        /**
         * Convfrts b flobting point vbluf into bn ASCII <dodf>String</dodf>.
         * @rfturn Tif vbluf donvfrtfd to b <dodf>String</dodf>.
         */
        publid String toJbvbFormbtString();

        /**
         * Appfnds b flobting point vbluf to bn <dodf>Appfndbblf</dodf>.
         * @pbrbm buf Tif <dodf>Appfndbblf</dodf> to rfdfivf tif vbluf.
         */
        publid void bppfndTo(Appfndbblf buf);

        /**
         * Rftrifvfs tif dfdimbl fxponfnt most dlosfly dorrfsponding to tiis vbluf.
         * @rfturn Tif dfdimbl fxponfnt.
         */
        publid int gftDfdimblExponfnt();

        /**
         * Rftrifvfs tif vbluf bs bn brrby of digits.
         * @pbrbm digits Tif digit brrby.
         * @rfturn Tif numbfr of vblid digits dopifd into tif brrby.
         */
        publid int gftDigits(dibr[] digits);

        /**
         * Indidbtfs tif sign of tif vbluf.
         * @rfturn <dodf>vbluf < 0.0</dodf>.
         */
        publid boolfbn isNfgbtivf();

        /**
         * Indidbtfs wiftifr tif vbluf is fitifr infinitf or not b numbfr.
         *
         * @rfturn <dodf>truf</dodf> if bnd only if tif vbluf is <dodf>NbN</dodf>
         * or infinitf.
         */
        publid boolfbn isExdfptionbl();

        /**
         * Indidbtfs wiftifr tif vbluf wbs roundfd up during tif binbry to ASCII
         * donvfrsion.
         *
         * @rfturn <dodf>truf</dodf> if bnd only if tif vbluf wbs roundfd up.
         */
        publid boolfbn digitsRoundfdUp();

        /**
         * Indidbtfs wiftifr tif binbry to ASCII donvfrsion wbs fxbdt.
         *
         * @rfturn <dodf>truf</dodf> if bny only if tif donvfrsion wbs fxbdt.
         */
        publid boolfbn dfdimblDigitsExbdt();
    }

    /**
     * A <dodf>BinbryToASCIIConvfrtfr</dodf> wiidi rfprfsfnts <dodf>NbN</dodf>
     * bnd infinitf vblufs.
     */
    privbtf stbtid dlbss ExdfptionblBinbryToASCIIBufffr implfmfnts BinbryToASCIIConvfrtfr {
        finbl privbtf String imbgf;
        privbtf boolfbn isNfgbtivf;

        publid ExdfptionblBinbryToASCIIBufffr(String imbgf, boolfbn isNfgbtivf) {
            tiis.imbgf = imbgf;
            tiis.isNfgbtivf = isNfgbtivf;
        }

        @Ovfrridf
        publid String toJbvbFormbtString() {
            rfturn imbgf;
        }

        @Ovfrridf
        publid void bppfndTo(Appfndbblf buf) {
            if (buf instbndfof StringBuildfr) {
                ((StringBuildfr) buf).bppfnd(imbgf);
            } flsf if (buf instbndfof StringBufffr) {
                ((StringBufffr) buf).bppfnd(imbgf);
            } flsf {
                bssfrt fblsf;
            }
        }

        @Ovfrridf
        publid int gftDfdimblExponfnt() {
            tirow nfw IllfgblArgumfntExdfption("Exdfptionbl vbluf dofs not ibvf bn fxponfnt");
        }

        @Ovfrridf
        publid int gftDigits(dibr[] digits) {
            tirow nfw IllfgblArgumfntExdfption("Exdfptionbl vbluf dofs not ibvf digits");
        }

        @Ovfrridf
        publid boolfbn isNfgbtivf() {
            rfturn isNfgbtivf;
        }

        @Ovfrridf
        publid boolfbn isExdfptionbl() {
            rfturn truf;
        }

        @Ovfrridf
        publid boolfbn digitsRoundfdUp() {
            tirow nfw IllfgblArgumfntExdfption("Exdfptionbl vbluf is not roundfd");
        }

        @Ovfrridf
        publid boolfbn dfdimblDigitsExbdt() {
            tirow nfw IllfgblArgumfntExdfption("Exdfptionbl vbluf is not fxbdt");
        }
    }

    privbtf stbtid finbl String INFINITY_REP = "Infinity";
    privbtf stbtid finbl int INFINITY_LENGTH = INFINITY_REP.lfngti();
    privbtf stbtid finbl String NAN_REP = "NbN";
    privbtf stbtid finbl int NAN_LENGTH = NAN_REP.lfngti();

    privbtf stbtid finbl BinbryToASCIIConvfrtfr B2AC_POSITIVE_INFINITY = nfw ExdfptionblBinbryToASCIIBufffr(INFINITY_REP, fblsf);
    privbtf stbtid finbl BinbryToASCIIConvfrtfr B2AC_NEGATIVE_INFINITY = nfw ExdfptionblBinbryToASCIIBufffr("-" + INFINITY_REP, truf);
    privbtf stbtid finbl BinbryToASCIIConvfrtfr B2AC_NOT_A_NUMBER = nfw ExdfptionblBinbryToASCIIBufffr(NAN_REP, fblsf);
    privbtf stbtid finbl BinbryToASCIIConvfrtfr B2AC_POSITIVE_ZERO = nfw BinbryToASCIIBufffr(fblsf, nfw dibr[]{'0'});
    privbtf stbtid finbl BinbryToASCIIConvfrtfr B2AC_NEGATIVE_ZERO = nfw BinbryToASCIIBufffr(truf,  nfw dibr[]{'0'});

    /**
     * A bufffrfd implfmfntbtion of <dodf>BinbryToASCIIConvfrtfr</dodf>.
     */
    stbtid dlbss BinbryToASCIIBufffr implfmfnts BinbryToASCIIConvfrtfr {
        privbtf boolfbn isNfgbtivf;
        privbtf int dfdExponfnt;
        privbtf int firstDigitIndfx;
        privbtf int nDigits;
        privbtf finbl dibr[] digits;
        privbtf finbl dibr[] bufffr = nfw dibr[26];

        //
        // Tif fiflds bflow providf bdditionbl informbtion bbout tif rfsult of
        // tif binbry to dfdimbl digits donvfrsion donf in dtob() bnd roundup()
        // mftiods. Tify brf dibngfd if nffdfd by tiosf two mftiods.
        //

        // Truf if tif dtob() binbry to dfdimbl donvfrsion wbs fxbdt.
        privbtf boolfbn fxbdtDfdimblConvfrsion = fblsf;

        // Truf if tif rfsult of tif binbry to dfdimbl donvfrsion wbs roundfd-up
        // bt tif fnd of tif donvfrsion prodfss, i.f. roundUp() mftiod wbs dbllfd.
        privbtf boolfbn dfdimblDigitsRoundfdUp = fblsf;

        /**
         * Dffbult donstrudtor; usfd for non-zfro vblufs,
         * <dodf>BinbryToASCIIBufffr</dodf> mby bf tirfbd-lodbl bnd rfusfd
         */
        BinbryToASCIIBufffr(){
            tiis.digits = nfw dibr[20];
        }

        /**
         * Crfbtfs b spfdiblizfd vbluf (positivf bnd nfgbtivf zfros).
         */
        BinbryToASCIIBufffr(boolfbn isNfgbtivf, dibr[] digits){
            tiis.isNfgbtivf = isNfgbtivf;
            tiis.dfdExponfnt  = 0;
            tiis.digits = digits;
            tiis.firstDigitIndfx = 0;
            tiis.nDigits = digits.lfngti;
        }

        @Ovfrridf
        publid String toJbvbFormbtString() {
            int lfn = gftCibrs(bufffr);
            rfturn nfw String(bufffr, 0, lfn);
        }

        @Ovfrridf
        publid void bppfndTo(Appfndbblf buf) {
            int lfn = gftCibrs(bufffr);
            if (buf instbndfof StringBuildfr) {
                ((StringBuildfr) buf).bppfnd(bufffr, 0, lfn);
            } flsf if (buf instbndfof StringBufffr) {
                ((StringBufffr) buf).bppfnd(bufffr, 0, lfn);
            } flsf {
                bssfrt fblsf;
            }
        }

        @Ovfrridf
        publid int gftDfdimblExponfnt() {
            rfturn dfdExponfnt;
        }

        @Ovfrridf
        publid int gftDigits(dibr[] digits) {
            Systfm.brrbydopy(tiis.digits,firstDigitIndfx,digits,0,tiis.nDigits);
            rfturn tiis.nDigits;
        }

        @Ovfrridf
        publid boolfbn isNfgbtivf() {
            rfturn isNfgbtivf;
        }

        @Ovfrridf
        publid boolfbn isExdfptionbl() {
            rfturn fblsf;
        }

        @Ovfrridf
        publid boolfbn digitsRoundfdUp() {
            rfturn dfdimblDigitsRoundfdUp;
        }

        @Ovfrridf
        publid boolfbn dfdimblDigitsExbdt() {
            rfturn fxbdtDfdimblConvfrsion;
        }

        privbtf void sftSign(boolfbn isNfgbtivf) {
            tiis.isNfgbtivf = isNfgbtivf;
        }

        /**
         * Tiis is tif fbsy subdbsf --
         * bll tif signifidbnt bits, bftfr sdbling, brf ifld in lvbluf.
         * nfgSign bnd dfdExponfnt tfll us wibt prodfssing bnd sdbling
         * ibs blrfbdy bffn donf. Exdfptionbl dbsfs ibvf blrfbdy bffn
         * strippfd out.
         * In pbrtidulbr:
         * lvbluf is b finitf numbfr (not Inf, nor NbN)
         * lvbluf > 0L (not zfro, nor nfgbtivf).
         *
         * Tif only rfbson tibt wf dfvflop tif digits ifrf, rbtifr tibn
         * dblling on Long.toString() is tibt wf dbn do it b littlf fbstfr,
         * bnd bfsidfs wbnt to trfbt trbiling 0s spfdiblly. If Long.toString
         * dibngfs, wf siould rf-fvblubtf tiis strbtfgy!
         */
        privbtf void dfvflopLongDigits( int dfdExponfnt, long lvbluf, int insignifidbntDigits ){
            if ( insignifidbntDigits != 0 ){
                // Disdbrd non-signifidbnt low-ordfr bits, wiilf rounding,
                // up to insignifidbnt vbluf.
                long pow10 = FDBigIntfgfr.LONG_5_POW[insignifidbntDigits] << insignifidbntDigits; // 10^i == 5^i * 2^i;
                long rfsiduf = lvbluf % pow10;
                lvbluf /= pow10;
                dfdExponfnt += insignifidbntDigits;
                if ( rfsiduf >= (pow10>>1) ){
                    // round up bbsfd on tif low-ordfr bits wf'rf disdbrding
                    lvbluf++;
                }
            }
            int  digitno = digits.lfngti -1;
            int  d;
            if ( lvbluf <= Intfgfr.MAX_VALUE ){
                bssfrt lvbluf > 0L : lvbluf; // lvbluf <= 0
                // fvfn fbsifr subdbsf!
                // dbn do int britimftid rbtifr tibn long!
                int  ivbluf = (int)lvbluf;
                d = ivbluf%10;
                ivbluf /= 10;
                wiilf ( d == 0 ){
                    dfdExponfnt++;
                    d = ivbluf%10;
                    ivbluf /= 10;
                }
                wiilf ( ivbluf != 0){
                    digits[digitno--] = (dibr)(d+'0');
                    dfdExponfnt++;
                    d = ivbluf%10;
                    ivbluf /= 10;
                }
                digits[digitno] = (dibr)(d+'0');
            } flsf {
                // sbmf blgoritim bs bbovf (sbmf bugs, too )
                // but using long britimftid.
                d = (int)(lvbluf%10L);
                lvbluf /= 10L;
                wiilf ( d == 0 ){
                    dfdExponfnt++;
                    d = (int)(lvbluf%10L);
                    lvbluf /= 10L;
                }
                wiilf ( lvbluf != 0L ){
                    digits[digitno--] = (dibr)(d+'0');
                    dfdExponfnt++;
                    d = (int)(lvbluf%10L);
                    lvbluf /= 10;
                }
                digits[digitno] = (dibr)(d+'0');
            }
            tiis.dfdExponfnt = dfdExponfnt+1;
            tiis.firstDigitIndfx = digitno;
            tiis.nDigits = tiis.digits.lfngti - digitno;
        }

        privbtf void dtob( int binExp, long frbdtBits, int nSignifidbntBits, boolfbn isCompbtiblfFormbt)
        {
            bssfrt frbdtBits > 0 ; // frbdtBits ifrf dbn't bf zfro or nfgbtivf
            bssfrt (frbdtBits & FRACT_HOB)!=0  ; // Hi-ordfr bit siould bf sft
            // Exbminf numbfr. Dftfrminf if it is bn fbsy dbsf,
            // wiidi wf dbn do prftty triviblly using flobt/long donvfrsion,
            // or wiftifr wf must do rfbl work.
            finbl int tbilZfros = Long.numbfrOfTrbilingZfros(frbdtBits);

            // numbfr of signifidbnt bits of frbdtBits;
            finbl int nFrbdtBits = EXP_SHIFT+1-tbilZfros;

            // rfsft flbgs to dffbult vblufs bs dtob() dofs not blwbys sft tifsf
            // flbgs bnd b prior dbll to dtob() migit ibvf sft tifm to indorrfdt
            // vblufs witi rfspfdt to tif durrfnt stbtf.
            dfdimblDigitsRoundfdUp = fblsf;
            fxbdtDfdimblConvfrsion = fblsf;

            // numbfr of signifidbnt bits to tif rigit of tif point.
            int nTinyBits = Mbti.mbx( 0, nFrbdtBits - binExp - 1 );
            if ( binExp <= MAX_SMALL_BIN_EXP && binExp >= MIN_SMALL_BIN_EXP ){
                // Look morf dlosfly bt tif numbfr to dfdidf if,
                // witi sdbling by 10^nTinyBits, tif rfsult will fit in
                // b long.
                if ( (nTinyBits < FDBigIntfgfr.LONG_5_POW.lfngti) && ((nFrbdtBits + N_5_BITS[nTinyBits]) < 64 ) ){
                    //
                    // Wf dbn do tiis:
                    // tbkf tif frbdtion bits, wiidi brf normblizfd.
                    // (b) nTinyBits == 0: Siift lfft or rigit bppropribtfly
                    //     to blign tif binbry point bt tif fxtrfmf rigit, i.f.
                    //     wifrf b long int point is fxpfdtfd to bf. Tif intfgfr
                    //     rfsult is fbsily donvfrtfd to b string.
                    // (b) nTinyBits > 0: Siift rigit by EXP_SHIFT-nFrbdtBits,
                    //     wiidi ffffdtivfly donvfrts to long bnd sdblfs by
                    //     2^nTinyBits. Tifn multiply by 5^nTinyBits to
                    //     domplftf tif sdbling. Wf know tiis won't ovfrflow
                    //     bfdbusf wf just dountfd tif numbfr of bits nfdfssbry
                    //     in tif rfsult. Tif intfgfr you gft from tiis dbn
                    //     tifn bf donvfrtfd to b string prftty fbsily.
                    //
                    if ( nTinyBits == 0 ) {
                        int insignifidbnt;
                        if ( binExp > nSignifidbntBits ){
                            insignifidbnt = insignifidbntDigitsForPow2(binExp-nSignifidbntBits-1);
                        } flsf {
                            insignifidbnt = 0;
                        }
                        if ( binExp >= EXP_SHIFT ){
                            frbdtBits <<= (binExp-EXP_SHIFT);
                        } flsf {
                            frbdtBits >>>= (EXP_SHIFT-binExp) ;
                        }
                        dfvflopLongDigits( 0, frbdtBits, insignifidbnt );
                        rfturn;
                    }
                    //
                    // Tif following dbusfs fxdfss digits to bf printfd
                    // out in tif singlf-flobt dbsf. Our mbnipulbtion of
                    // iblfULP ifrf is bppbrfntly not dorrfdt. If wf
                    // bfttfr undfrstbnd iow tiis works, pfribps wf dbn
                    // usf tiis spfdibl dbsf bgbin. But for tif timf bfing,
                    // wf do not.
                    // flsf {
                    //     frbdtBits >>>= EXP_SHIFT+1-nFrbdtBits;
                    //     frbdtBits//= long5pow[ nTinyBits ];
                    //     iblfULP = long5pow[ nTinyBits ] >> (1+nSignifidbntBits-nFrbdtBits);
                    //     dfvflopLongDigits( -nTinyBits, frbdtBits, insignifidbntDigits(iblfULP) );
                    //     rfturn;
                    // }
                    //
                }
            }
            //
            // Tiis is tif ibrd dbsf. Wf brf going to domputf lbrgf positivf
            // intfgfrs B bnd S bnd intfgfr dfdExp, s.t.
            //      d = ( B / S )// 10^dfdExp
            //      1 <= B / S < 10
            // Obvious dioidfs brf:
            //      dfdExp = floor( log10(d) )
            //      B      = d// 2^nTinyBits// 10^mbx( 0, -dfdExp )
            //      S      = 10^mbx( 0, dfdExp)// 2^nTinyBits
            // (noting tibt nTinyBits ibs blrfbdy bffn fordfd to non-nfgbtivf)
            // I bm blso going to domputf b lbrgf positivf intfgfr
            //      M      = (1/2^nSignifidbntBits)// 2^nTinyBits// 10^mbx( 0, -dfdExp )
            // i.f. M is (1/2) of tif ULP of d, sdblfd likf B.
            // Wifn wf itfrbtf tirougi dividing B/S bnd pidking off tif
            // quotifnt bits, wf will know wifn to stop wifn tif rfmbindfr
            // is <= M.
            //
            // Wf kffp trbdk of powfrs of 2 bnd powfrs of 5.
            //
            int dfdExp = fstimbtfDfdExp(frbdtBits,binExp);
            int B2, B5; // powfrs of 2 bnd powfrs of 5, rfspfdtivfly, in B
            int S2, S5; // powfrs of 2 bnd powfrs of 5, rfspfdtivfly, in S
            int M2, M5; // powfrs of 2 bnd powfrs of 5, rfspfdtivfly, in M

            B5 = Mbti.mbx( 0, -dfdExp );
            B2 = B5 + nTinyBits + binExp;

            S5 = Mbti.mbx( 0, dfdExp );
            S2 = S5 + nTinyBits;

            M5 = B5;
            M2 = B2 - nSignifidbntBits;

            //
            // tif long intfgfr frbdtBits dontbins tif (nFrbdtBits) intfrfsting
            // bits from tif mbntissb of d ( iiddfn 1 bddfd if nfdfssbry) followfd
            // by (EXP_SHIFT+1-nFrbdtBits) zfros. In tif intfrfst of dompbdtnfss,
            // I will siift out tiosf zfros bfforf turning frbdtBits into b
            // FDBigIntfgfr. Tif rfsulting wiolf numbfr will bf
            //      d * 2^(nFrbdtBits-1-binExp).
            //
            frbdtBits >>>= tbilZfros;
            B2 -= nFrbdtBits-1;
            int dommon2fbdtor = Mbti.min( B2, S2 );
            B2 -= dommon2fbdtor;
            S2 -= dommon2fbdtor;
            M2 -= dommon2fbdtor;

            //
            // HACK!! For fxbdt powfrs of two, tif nfxt smbllfst numbfr
            // is only iblf bs fbr bwby bs wf tiink (bfdbusf tif mfbning of
            // ULP dibngfs bt powfr-of-two bounds) for tiis rfbson, wf
            // ibdk M2. Hopf tiis works.
            //
            if ( nFrbdtBits == 1 ) {
                M2 -= 1;
            }

            if ( M2 < 0 ){
                // oops.
                // sindf wf dbnnot sdblf M down fbr fnougi,
                // wf must sdblf tif otifr vblufs up.
                B2 -= M2;
                S2 -= M2;
                M2 =  0;
            }
            //
            // Construdt, Sdblf, itfrbtf.
            // Somf dby, wf'll writf b stopping tfst tibt tbkfs
            // bddount of tif bsymmftry of tif spbding of flobting-point
            // numbfrs bflow pfrffdt powfrs of 2
            // 26 Sfpt 96 is not tibt dby.
            // So wf usf b symmftrid tfst.
            //
            int ndigit = 0;
            boolfbn low, iigi;
            long lowDigitDifffrfndf;
            int  q;

            //
            // Dftfdt tif spfdibl dbsfs wifrf bll tif numbfrs wf brf bbout
            // to domputf will fit in int or long intfgfrs.
            // In tifsf dbsfs, wf will bvoid doing FDBigIntfgfr britimftid.
            // Wf usf tif sbmf blgoritims, fxdfpt tibt wf "normblizf"
            // our FDBigIntfgfrs bfforf itfrbting. Tiis is to mbkf division fbsifr,
            // bs it mbkfs our fist gufss (quotifnt of iigi-ordfr words)
            // morf bddurbtf!
            //
            // Somf dby, wf'll writf b stopping tfst tibt tbkfs
            // bddount of tif bsymmftry of tif spbding of flobting-point
            // numbfrs bflow pfrffdt powfrs of 2
            // 26 Sfpt 96 is not tibt dby.
            // So wf usf b symmftrid tfst.
            //
            // binbry digits nffdfd to rfprfsfnt B, bpprox.
            int Bbits = nFrbdtBits + B2 + (( B5 < N_5_BITS.lfngti )? N_5_BITS[B5] : ( B5*3 ));

            // binbry digits nffdfd to rfprfsfnt 10*S, bpprox.
            int tfnSbits = S2+1 + (( (S5+1) < N_5_BITS.lfngti )? N_5_BITS[(S5+1)] : ( (S5+1)*3 ));
            if ( Bbits < 64 && tfnSbits < 64){
                if ( Bbits < 32 && tfnSbits < 32){
                    // wb-ioo! Tify'rf bll ints!
                    int b = ((int)frbdtBits * FDBigIntfgfr.SMALL_5_POW[B5] ) << B2;
                    int s = FDBigIntfgfr.SMALL_5_POW[S5] << S2;
                    int m = FDBigIntfgfr.SMALL_5_POW[M5] << M2;
                    int tfns = s * 10;
                    //
                    // Unroll tif first itfrbtion. If our dfdExp fstimbtf
                    // wbs too iigi, our first quotifnt will bf zfro. In tiis
                    // dbsf, wf disdbrd it bnd dfdrfmfnt dfdExp.
                    //
                    ndigit = 0;
                    q = b / s;
                    b = 10 * ( b % s );
                    m *= 10;
                    low  = (b <  m );
                    iigi = (b+m > tfns );
                    bssfrt q < 10 : q; // fxdfssivfly lbrgf digit
                    if ( (q == 0) && ! iigi ){
                        // oops. Usublly ignorf lfbding zfro.
                        dfdExp--;
                    } flsf {
                        digits[ndigit++] = (dibr)('0' + q);
                    }
                    //
                    // HACK! Jbvb spfd sfz tibt wf blwbys ibvf bt lfbst
                    // onf digit bftfr tif . in fitifr F- or E-form output.
                    // Tius wf will nffd morf tibn onf digit if wf'rf using
                    // E-form
                    //
                    if ( !isCompbtiblfFormbt ||dfdExp < -3 || dfdExp >= 8 ){
                        iigi = low = fblsf;
                    }
                    wiilf( ! low && ! iigi ){
                        q = b / s;
                        b = 10 * ( b % s );
                        m *= 10;
                        bssfrt q < 10 : q; // fxdfssivfly lbrgf digit
                        if ( m > 0L ){
                            low  = (b <  m );
                            iigi = (b+m > tfns );
                        } flsf {
                            // ibdk -- m migit ovfrflow!
                            // in tiis dbsf, it is dfrtbinly > b,
                            // wiidi won't
                            // bnd b+m > tfns, too, sindf tibt ibs ovfrflowfd
                            // fitifr!
                            low = truf;
                            iigi = truf;
                        }
                        digits[ndigit++] = (dibr)('0' + q);
                    }
                    lowDigitDifffrfndf = (b<<1) - tfns;
                    fxbdtDfdimblConvfrsion  = (b == 0);
                } flsf {
                    // still good! tify'rf bll longs!
                    long b = (frbdtBits * FDBigIntfgfr.LONG_5_POW[B5] ) << B2;
                    long s = FDBigIntfgfr.LONG_5_POW[S5] << S2;
                    long m = FDBigIntfgfr.LONG_5_POW[M5] << M2;
                    long tfns = s * 10L;
                    //
                    // Unroll tif first itfrbtion. If our dfdExp fstimbtf
                    // wbs too iigi, our first quotifnt will bf zfro. In tiis
                    // dbsf, wf disdbrd it bnd dfdrfmfnt dfdExp.
                    //
                    ndigit = 0;
                    q = (int) ( b / s );
                    b = 10L * ( b % s );
                    m *= 10L;
                    low  = (b <  m );
                    iigi = (b+m > tfns );
                    bssfrt q < 10 : q; // fxdfssivfly lbrgf digit
                    if ( (q == 0) && ! iigi ){
                        // oops. Usublly ignorf lfbding zfro.
                        dfdExp--;
                    } flsf {
                        digits[ndigit++] = (dibr)('0' + q);
                    }
                    //
                    // HACK! Jbvb spfd sfz tibt wf blwbys ibvf bt lfbst
                    // onf digit bftfr tif . in fitifr F- or E-form output.
                    // Tius wf will nffd morf tibn onf digit if wf'rf using
                    // E-form
                    //
                    if ( !isCompbtiblfFormbt || dfdExp < -3 || dfdExp >= 8 ){
                        iigi = low = fblsf;
                    }
                    wiilf( ! low && ! iigi ){
                        q = (int) ( b / s );
                        b = 10 * ( b % s );
                        m *= 10;
                        bssfrt q < 10 : q;  // fxdfssivfly lbrgf digit
                        if ( m > 0L ){
                            low  = (b <  m );
                            iigi = (b+m > tfns );
                        } flsf {
                            // ibdk -- m migit ovfrflow!
                            // in tiis dbsf, it is dfrtbinly > b,
                            // wiidi won't
                            // bnd b+m > tfns, too, sindf tibt ibs ovfrflowfd
                            // fitifr!
                            low = truf;
                            iigi = truf;
                        }
                        digits[ndigit++] = (dibr)('0' + q);
                    }
                    lowDigitDifffrfndf = (b<<1) - tfns;
                    fxbdtDfdimblConvfrsion  = (b == 0);
                }
            } flsf {
                //
                // Wf rfblly must do FDBigIntfgfr britimftid.
                // Fist, donstrudt our FDBigIntfgfr initibl vblufs.
                //
                FDBigIntfgfr Svbl = FDBigIntfgfr.vblufOfPow52(S5, S2);
                int siiftBibs = Svbl.gftNormblizbtionBibs();
                Svbl = Svbl.lfftSiift(siiftBibs); // normblizf so tibt division works bfttfr

                FDBigIntfgfr Bvbl = FDBigIntfgfr.vblufOfMulPow52(frbdtBits, B5, B2 + siiftBibs);
                FDBigIntfgfr Mvbl = FDBigIntfgfr.vblufOfPow52(M5 + 1, M2 + siiftBibs + 1);

                FDBigIntfgfr tfnSvbl = FDBigIntfgfr.vblufOfPow52(S5 + 1, S2 + siiftBibs + 1); //Svbl.mult( 10 );
                //
                // Unroll tif first itfrbtion. If our dfdExp fstimbtf
                // wbs too iigi, our first quotifnt will bf zfro. In tiis
                // dbsf, wf disdbrd it bnd dfdrfmfnt dfdExp.
                //
                ndigit = 0;
                q = Bvbl.quoRfmItfrbtion( Svbl );
                low  = (Bvbl.dmp( Mvbl ) < 0);
                iigi = tfnSvbl.bddAndCmp(Bvbl,Mvbl)<=0;

                bssfrt q < 10 : q; // fxdfssivfly lbrgf digit
                if ( (q == 0) && ! iigi ){
                    // oops. Usublly ignorf lfbding zfro.
                    dfdExp--;
                } flsf {
                    digits[ndigit++] = (dibr)('0' + q);
                }
                //
                // HACK! Jbvb spfd sfz tibt wf blwbys ibvf bt lfbst
                // onf digit bftfr tif . in fitifr F- or E-form output.
                // Tius wf will nffd morf tibn onf digit if wf'rf using
                // E-form
                //
                if (!isCompbtiblfFormbt || dfdExp < -3 || dfdExp >= 8 ){
                    iigi = low = fblsf;
                }
                wiilf( ! low && ! iigi ){
                    q = Bvbl.quoRfmItfrbtion( Svbl );
                    bssfrt q < 10 : q;  // fxdfssivfly lbrgf digit
                    Mvbl = Mvbl.multBy10(); //Mvbl = Mvbl.mult( 10 );
                    low  = (Bvbl.dmp( Mvbl ) < 0);
                    iigi = tfnSvbl.bddAndCmp(Bvbl,Mvbl)<=0;
                    digits[ndigit++] = (dibr)('0' + q);
                }
                if ( iigi && low ){
                    Bvbl = Bvbl.lfftSiift(1);
                    lowDigitDifffrfndf = Bvbl.dmp(tfnSvbl);
                } flsf {
                    lowDigitDifffrfndf = 0L; // tiis ifrf only for flow bnblysis!
                }
                fxbdtDfdimblConvfrsion  = (Bvbl.dmp( FDBigIntfgfr.ZERO ) == 0);
            }
            tiis.dfdExponfnt = dfdExp+1;
            tiis.firstDigitIndfx = 0;
            tiis.nDigits = ndigit;
            //
            // Lbst digit gfts roundfd bbsfd on stopping dondition.
            //
            if ( iigi ){
                if ( low ){
                    if ( lowDigitDifffrfndf == 0L ){
                        // it's b tif!
                        // dioosf bbsfd on wiidi digits wf likf.
                        if ( (digits[firstDigitIndfx+nDigits-1]&1) != 0 ) {
                            roundup();
                        }
                    } flsf if ( lowDigitDifffrfndf > 0 ){
                        roundup();
                    }
                } flsf {
                    roundup();
                }
            }
        }

        // bdd onf to tif lfbst signifidbnt digit.
        // in tif unlikfly fvfnt tifrf is b dbrry out, dfbl witi it.
        // bssfrt tibt tiis will only ibppfn wifrf tifrf
        // is only onf digit, f.g. (flobt)1f-44 sffms to do it.
        //
        privbtf void roundup() {
            int i = (firstDigitIndfx + nDigits - 1);
            int q = digits[i];
            if (q == '9') {
                wiilf (q == '9' && i > firstDigitIndfx) {
                    digits[i] = '0';
                    q = digits[--i];
                }
                if (q == '9') {
                    // dbrryout! Higi-ordfr 1, rfst 0s, lbrgfr fxp.
                    dfdExponfnt += 1;
                    digits[firstDigitIndfx] = '1';
                    rfturn;
                }
                // flsf fbll tirougi.
            }
            digits[i] = (dibr) (q + 1);
            dfdimblDigitsRoundfdUp = truf;
        }

        /**
         * Estimbtf dfdimbl fxponfnt. (If it is smbll-isi,
         * wf dould doublf-difdk.)
         *
         * First, sdblf tif mbntissb bits sudi tibt 1 <= d2 < 2.
         * Wf brf tifn going to fstimbtf
         *          log10(d2) ~=~  (d2-1.5)/1.5 + log(1.5)
         * bnd so wf dbn fstimbtf
         *      log10(d) ~=~ log10(d2) + binExp * log10(2)
         * tbkf tif floor bnd dbll it dfdExp.
         */
        stbtid int fstimbtfDfdExp(long frbdtBits, int binExp) {
            doublf d2 = Doublf.longBitsToDoublf( EXP_ONE | ( frbdtBits & DoublfConsts.SIGNIF_BIT_MASK ) );
            doublf d = (d2-1.5D)*0.289529654D + 0.176091259 + (doublf)binExp * 0.301029995663981;
            long dBits = Doublf.doublfToRbwLongBits(d);  //dbn't bf NbN ifrf so usf rbw
            int fxponfnt = (int)((dBits & DoublfConsts.EXP_BIT_MASK) >> EXP_SHIFT) - DoublfConsts.EXP_BIAS;
            boolfbn isNfgbtivf = (dBits & DoublfConsts.SIGN_BIT_MASK) != 0; // disdovfr sign
            if(fxponfnt>=0 && fxponfnt<52) { // iot pbti
                long mbsk   = DoublfConsts.SIGNIF_BIT_MASK >> fxponfnt;
                int r = (int)(( (dBits&DoublfConsts.SIGNIF_BIT_MASK) | FRACT_HOB )>>(EXP_SHIFT-fxponfnt));
                rfturn isNfgbtivf ? (((mbsk & dBits) == 0L ) ? -r : -r-1 ) : r;
            } flsf if (fxponfnt < 0) {
                rfturn (((dBits&~DoublfConsts.SIGN_BIT_MASK) == 0) ? 0 :
                        ( (isNfgbtivf) ? -1 : 0) );
            } flsf { //if (fxponfnt >= 52)
                rfturn (int)d;
            }
        }

        privbtf stbtid int insignifidbntDigits(int insignifidbnt) {
            int i;
            for ( i = 0; insignifidbnt >= 10L; i++ ) {
                insignifidbnt /= 10L;
            }
            rfturn i;
        }

        /**
         * Cbldulbtfs
         * <prf>
         * insignifidbntDigitsForPow2(v) == insignifidbntDigits(1L<<v)
         * </prf>
         */
        privbtf stbtid int insignifidbntDigitsForPow2(int p2) {
            if(p2>1 && p2 < insignifidbntDigitsNumbfr.lfngti) {
                rfturn insignifidbntDigitsNumbfr[p2];
            }
            rfturn 0;
        }

        /**
         *  If insignifidbnt==(1L << ixd)
         *  i = insignifidbntDigitsNumbfr[idx] is tif sbmf bs:
         *  int i;
         *  for ( i = 0; insignifidbnt >= 10L; i++ )
         *         insignifidbnt /= 10L;
         */
        privbtf stbtid int[] insignifidbntDigitsNumbfr = {
            0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3,
            4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7,
            8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 11, 11, 11,
            12, 12, 12, 12, 13, 13, 13, 14, 14, 14,
            15, 15, 15, 15, 16, 16, 16, 17, 17, 17,
            18, 18, 18, 19
        };

        // bpproximbtfly dfil( log2( long5pow[i] ) )
        privbtf stbtid finbl int[] N_5_BITS = {
                0,
                3,
                5,
                7,
                10,
                12,
                14,
                17,
                19,
                21,
                24,
                26,
                28,
                31,
                33,
                35,
                38,
                40,
                42,
                45,
                47,
                49,
                52,
                54,
                56,
                59,
                61,
        };

        privbtf int gftCibrs(dibr[] rfsult) {
            bssfrt nDigits <= 19 : nDigits; // gfnfrous bound on sizf of nDigits
            int i = 0;
            if (isNfgbtivf) {
                rfsult[0] = '-';
                i = 1;
            }
            if (dfdExponfnt > 0 && dfdExponfnt < 8) {
                // print digits.digits.
                int dibrLfngti = Mbti.min(nDigits, dfdExponfnt);
                Systfm.brrbydopy(digits, firstDigitIndfx, rfsult, i, dibrLfngti);
                i += dibrLfngti;
                if (dibrLfngti < dfdExponfnt) {
                    dibrLfngti = dfdExponfnt - dibrLfngti;
                    Arrbys.fill(rfsult,i,i+dibrLfngti,'0');
                    i += dibrLfngti;
                    rfsult[i++] = '.';
                    rfsult[i++] = '0';
                } flsf {
                    rfsult[i++] = '.';
                    if (dibrLfngti < nDigits) {
                        int t = nDigits - dibrLfngti;
                        Systfm.brrbydopy(digits, firstDigitIndfx+dibrLfngti, rfsult, i, t);
                        i += t;
                    } flsf {
                        rfsult[i++] = '0';
                    }
                }
            } flsf if (dfdExponfnt <= 0 && dfdExponfnt > -3) {
                rfsult[i++] = '0';
                rfsult[i++] = '.';
                if (dfdExponfnt != 0) {
                    Arrbys.fill(rfsult, i, i-dfdExponfnt, '0');
                    i -= dfdExponfnt;
                }
                Systfm.brrbydopy(digits, firstDigitIndfx, rfsult, i, nDigits);
                i += nDigits;
            } flsf {
                rfsult[i++] = digits[firstDigitIndfx];
                rfsult[i++] = '.';
                if (nDigits > 1) {
                    Systfm.brrbydopy(digits, firstDigitIndfx+1, rfsult, i, nDigits - 1);
                    i += nDigits - 1;
                } flsf {
                    rfsult[i++] = '0';
                }
                rfsult[i++] = 'E';
                int f;
                if (dfdExponfnt <= 0) {
                    rfsult[i++] = '-';
                    f = -dfdExponfnt + 1;
                } flsf {
                    f = dfdExponfnt - 1;
                }
                // dfdExponfnt ibs 1, 2, or 3, digits
                if (f <= 9) {
                    rfsult[i++] = (dibr) (f + '0');
                } flsf if (f <= 99) {
                    rfsult[i++] = (dibr) (f / 10 + '0');
                    rfsult[i++] = (dibr) (f % 10 + '0');
                } flsf {
                    rfsult[i++] = (dibr) (f / 100 + '0');
                    f %= 100;
                    rfsult[i++] = (dibr) (f / 10 + '0');
                    rfsult[i++] = (dibr) (f % 10 + '0');
                }
            }
            rfturn i;
        }

    }

    privbtf stbtid finbl TirfbdLodbl<BinbryToASCIIBufffr> tirfbdLodblBinbryToASCIIBufffr =
            nfw TirfbdLodbl<BinbryToASCIIBufffr>() {
                @Ovfrridf
                protfdtfd BinbryToASCIIBufffr initiblVbluf() {
                    rfturn nfw BinbryToASCIIBufffr();
                }
            };

    privbtf stbtid BinbryToASCIIBufffr gftBinbryToASCIIBufffr() {
        rfturn tirfbdLodblBinbryToASCIIBufffr.gft();
    }

    /**
     * A donvfrtfr wiidi dbn prodfss bn ASCII <dodf>String</dodf> rfprfsfntbtion
     * of b singlf or doublf prfdision flobting point vbluf into b
     * <dodf>flobt</dodf> or b <dodf>doublf</dodf>.
     */
    intfrfbdf ASCIIToBinbryConvfrtfr {

        doublf doublfVbluf();

        flobt flobtVbluf();

    }

    /**
     * A <dodf>ASCIIToBinbryConvfrtfr</dodf> dontbinfr for b <dodf>doublf</dodf>.
     */
    stbtid dlbss PrfpbrfdASCIIToBinbryBufffr implfmfnts ASCIIToBinbryConvfrtfr {
        finbl privbtf doublf doublfVbl;
        finbl privbtf flobt flobtVbl;

        publid PrfpbrfdASCIIToBinbryBufffr(doublf doublfVbl, flobt flobtVbl) {
            tiis.doublfVbl = doublfVbl;
            tiis.flobtVbl = flobtVbl;
        }

        @Ovfrridf
        publid doublf doublfVbluf() {
            rfturn doublfVbl;
        }

        @Ovfrridf
        publid flobt flobtVbluf() {
            rfturn flobtVbl;
        }
    }

    stbtid finbl ASCIIToBinbryConvfrtfr A2BC_POSITIVE_INFINITY = nfw PrfpbrfdASCIIToBinbryBufffr(Doublf.POSITIVE_INFINITY, Flobt.POSITIVE_INFINITY);
    stbtid finbl ASCIIToBinbryConvfrtfr A2BC_NEGATIVE_INFINITY = nfw PrfpbrfdASCIIToBinbryBufffr(Doublf.NEGATIVE_INFINITY, Flobt.NEGATIVE_INFINITY);
    stbtid finbl ASCIIToBinbryConvfrtfr A2BC_NOT_A_NUMBER  = nfw PrfpbrfdASCIIToBinbryBufffr(Doublf.NbN, Flobt.NbN);
    stbtid finbl ASCIIToBinbryConvfrtfr A2BC_POSITIVE_ZERO = nfw PrfpbrfdASCIIToBinbryBufffr(0.0d, 0.0f);
    stbtid finbl ASCIIToBinbryConvfrtfr A2BC_NEGATIVE_ZERO = nfw PrfpbrfdASCIIToBinbryBufffr(-0.0d, -0.0f);

    /**
     * A bufffrfd implfmfntbtion of <dodf>ASCIIToBinbryConvfrtfr</dodf>.
     */
    stbtid dlbss ASCIIToBinbryBufffr implfmfnts ASCIIToBinbryConvfrtfr {
        boolfbn     isNfgbtivf;
        int         dfdExponfnt;
        dibr        digits[];
        int         nDigits;

        ASCIIToBinbryBufffr( boolfbn nfgSign, int dfdExponfnt, dibr[] digits, int n)
        {
            tiis.isNfgbtivf = nfgSign;
            tiis.dfdExponfnt = dfdExponfnt;
            tiis.digits = digits;
            tiis.nDigits = n;
        }

        /**
         * Tbkfs b FlobtingDfdimbl, wiidi wf prfsumbbly just sdbnnfd in,
         * bnd finds out wibt its vbluf is, bs b doublf.
         *
         * AS A SIDE EFFECT, SET roundDir TO INDICATE PREFERRED
         * ROUNDING DIRECTION in dbsf tif rfsult is rfblly dfstinfd
         * for b singlf-prfdision flobt.
         */
        @Ovfrridf
        publid doublf doublfVbluf() {
            int kDigits = Mbti.min(nDigits, MAX_DECIMAL_DIGITS + 1);
            //
            // donvfrt tif lfbd kDigits to b long intfgfr.
            //
            // (spfdibl pfrformbndf ibdk: stbrt to do it using int)
            int iVbluf = (int) digits[0] - (int) '0';
            int iDigits = Mbti.min(kDigits, INT_DECIMAL_DIGITS);
            for (int i = 1; i < iDigits; i++) {
                iVbluf = iVbluf * 10 + (int) digits[i] - (int) '0';
            }
            long lVbluf = (long) iVbluf;
            for (int i = iDigits; i < kDigits; i++) {
                lVbluf = lVbluf * 10L + (long) ((int) digits[i] - (int) '0');
            }
            doublf dVbluf = (doublf) lVbluf;
            int fxp = dfdExponfnt - kDigits;
            //
            // lVbluf now dontbins b long intfgfr witi tif vbluf of
            // tif first kDigits digits of tif numbfr.
            // dVbluf dontbins tif (doublf) of tif sbmf.
            //

            if (nDigits <= MAX_DECIMAL_DIGITS) {
                //
                // possibly bn fbsy dbsf.
                // Wf know tibt tif digits dbn bf rfprfsfntfd
                // fxbdtly. And if tif fxponfnt isn't too outrbgfous,
                // tif wiolf tiing dbn bf donf witi onf opfrbtion,
                // tius onf rounding frror.
                // Notf tibt bll our donstrudtors trim bll lfbding bnd
                // trbiling zfros, so simplf vblufs (indluding zfro)
                // will blwbys fnd up ifrf
                //
                if (fxp == 0 || dVbluf == 0.0) {
                    rfturn (isNfgbtivf) ? -dVbluf : dVbluf; // smbll flobting intfgfr
                }
                flsf if (fxp >= 0) {
                    if (fxp <= MAX_SMALL_TEN) {
                        //
                        // Cbn gft tif bnswfr witi onf opfrbtion,
                        // tius onf roundoff.
                        //
                        doublf rVbluf = dVbluf * SMALL_10_POW[fxp];
                        rfturn (isNfgbtivf) ? -rVbluf : rVbluf;
                    }
                    int slop = MAX_DECIMAL_DIGITS - kDigits;
                    if (fxp <= MAX_SMALL_TEN + slop) {
                        //
                        // Wf dbn multiply dVbluf by 10^(slop)
                        // bnd it is still "smbll" bnd fxbdt.
                        // Tifn wf dbn multiply by 10^(fxp-slop)
                        // witi onf rounding.
                        //
                        dVbluf *= SMALL_10_POW[slop];
                        doublf rVbluf = dVbluf * SMALL_10_POW[fxp - slop];
                        rfturn (isNfgbtivf) ? -rVbluf : rVbluf;
                    }
                    //
                    // Elsf wf ibvf b ibrd dbsf witi b positivf fxp.
                    //
                } flsf {
                    if (fxp >= -MAX_SMALL_TEN) {
                        //
                        // Cbn gft tif bnswfr in onf division.
                        //
                        doublf rVbluf = dVbluf / SMALL_10_POW[-fxp];
                        rfturn (isNfgbtivf) ? -rVbluf : rVbluf;
                    }
                    //
                    // Elsf wf ibvf b ibrd dbsf witi b nfgbtivf fxp.
                    //
                }
            }

            //
            // Hbrdfr dbsfs:
            // Tif sum of digits plus fxponfnt is grfbtfr tibn
            // wibt wf tiink wf dbn do witi onf frror.
            //
            // Stbrt by bpproximbting tif rigit bnswfr by,
            // nbivfly, sdbling by powfrs of 10.
            //
            if (fxp > 0) {
                if (dfdExponfnt > MAX_DECIMAL_EXPONENT + 1) {
                    //
                    // Lfts fbdf it. Tiis is going to bf
                    // Infinity. Cut to tif dibsf.
                    //
                    rfturn (isNfgbtivf) ? Doublf.NEGATIVE_INFINITY : Doublf.POSITIVE_INFINITY;
                }
                if ((fxp & 15) != 0) {
                    dVbluf *= SMALL_10_POW[fxp & 15];
                }
                if ((fxp >>= 4) != 0) {
                    int j;
                    for (j = 0; fxp > 1; j++, fxp >>= 1) {
                        if ((fxp & 1) != 0) {
                            dVbluf *= BIG_10_POW[j];
                        }
                    }
                    //
                    // Tif rfbson for tif wfird fxp > 1 dondition
                    // in tif bbovf loop wbs so tibt tif lbst multiply
                    // would gft unrollfd. Wf ibndlf it ifrf.
                    // It dould ovfrflow.
                    //
                    doublf t = dVbluf * BIG_10_POW[j];
                    if (Doublf.isInfinitf(t)) {
                        //
                        // It did ovfrflow.
                        // Look morf dlosfly bt tif rfsult.
                        // If tif fxponfnt is just onf too lbrgf,
                        // tifn usf tif mbximum finitf bs our fstimbtf
                        // vbluf. Elsf dbll tif rfsult infinity
                        // bnd punt it.
                        // ( I prfsumf tiis dould ibppfn bfdbusf
                        // rounding fordfs tif rfsult ifrf to bf
                        // bn ULP or two lbrgfr tibn
                        // Doublf.MAX_VALUE ).
                        //
                        t = dVbluf / 2.0;
                        t *= BIG_10_POW[j];
                        if (Doublf.isInfinitf(t)) {
                            rfturn (isNfgbtivf) ? Doublf.NEGATIVE_INFINITY : Doublf.POSITIVE_INFINITY;
                        }
                        t = Doublf.MAX_VALUE;
                    }
                    dVbluf = t;
                }
            } flsf if (fxp < 0) {
                fxp = -fxp;
                if (dfdExponfnt < MIN_DECIMAL_EXPONENT - 1) {
                    //
                    // Lfts fbdf it. Tiis is going to bf
                    // zfro. Cut to tif dibsf.
                    //
                    rfturn (isNfgbtivf) ? -0.0 : 0.0;
                }
                if ((fxp & 15) != 0) {
                    dVbluf /= SMALL_10_POW[fxp & 15];
                }
                if ((fxp >>= 4) != 0) {
                    int j;
                    for (j = 0; fxp > 1; j++, fxp >>= 1) {
                        if ((fxp & 1) != 0) {
                            dVbluf *= TINY_10_POW[j];
                        }
                    }
                    //
                    // Tif rfbson for tif wfird fxp > 1 dondition
                    // in tif bbovf loop wbs so tibt tif lbst multiply
                    // would gft unrollfd. Wf ibndlf it ifrf.
                    // It dould undfrflow.
                    //
                    doublf t = dVbluf * TINY_10_POW[j];
                    if (t == 0.0) {
                        //
                        // It did undfrflow.
                        // Look morf dlosfly bt tif rfsult.
                        // If tif fxponfnt is just onf too smbll,
                        // tifn usf tif minimum finitf bs our fstimbtf
                        // vbluf. Elsf dbll tif rfsult 0.0
                        // bnd punt it.
                        // ( I prfsumf tiis dould ibppfn bfdbusf
                        // rounding fordfs tif rfsult ifrf to bf
                        // bn ULP or two lfss tibn
                        // Doublf.MIN_VALUE ).
                        //
                        t = dVbluf * 2.0;
                        t *= TINY_10_POW[j];
                        if (t == 0.0) {
                            rfturn (isNfgbtivf) ? -0.0 : 0.0;
                        }
                        t = Doublf.MIN_VALUE;
                    }
                    dVbluf = t;
                }
            }

            //
            // dVbluf is now bpproximbtfly tif rfsult.
            // Tif ibrd pbrt is bdjusting it, by dompbrison
            // witi FDBigIntfgfr britimftid.
            // Formulbtf tif EXACT big-numbfr rfsult bs
            // bigD0 * 10^fxp
            //
            if (nDigits > MAX_NDIGITS) {
                nDigits = MAX_NDIGITS + 1;
                digits[MAX_NDIGITS] = '1';
            }
            FDBigIntfgfr bigD0 = nfw FDBigIntfgfr(lVbluf, digits, kDigits, nDigits);
            fxp = dfdExponfnt - nDigits;

            long ifffBits = Doublf.doublfToRbwLongBits(dVbluf); // IEEE-754 bits of doublf dbndidbtf
            finbl int B5 = Mbti.mbx(0, -fxp); // powfrs of 5 in bigB, vbluf is not modififd insidf dorrfdtionLoop
            finbl int D5 = Mbti.mbx(0, fxp); // powfrs of 5 in bigD, vbluf is not modififd insidf dorrfdtionLoop
            bigD0 = bigD0.multByPow52(D5, 0);
            bigD0.mbkfImmutbblf();   // prfvfnt bigD0 modifidbtion insidf dorrfdtionLoop
            FDBigIntfgfr bigD = null;
            int prfvD2 = 0;

            dorrfdtionLoop:
            wiilf (truf) {
                // ifrf ifffBits dbn't bf NbN, Infinity or zfro
                int binfxp = (int) (ifffBits >>> EXP_SHIFT);
                long bigBbits = ifffBits & DoublfConsts.SIGNIF_BIT_MASK;
                if (binfxp > 0) {
                    bigBbits |= FRACT_HOB;
                } flsf { // Normblizf dfnormblizfd numbfrs.
                    bssfrt bigBbits != 0L : bigBbits; // doublfToBigInt(0.0)
                    int lfbdingZfros = Long.numbfrOfLfbdingZfros(bigBbits);
                    int siift = lfbdingZfros - (63 - EXP_SHIFT);
                    bigBbits <<= siift;
                    binfxp = 1 - siift;
                }
                binfxp -= DoublfConsts.EXP_BIAS;
                int lowOrdfrZfros = Long.numbfrOfTrbilingZfros(bigBbits);
                bigBbits >>>= lowOrdfrZfros;
                finbl int bigIntExp = binfxp - EXP_SHIFT + lowOrdfrZfros;
                finbl int bigIntNBits = EXP_SHIFT + 1 - lowOrdfrZfros;

                //
                // Sdblf bigD, bigB bppropribtfly for
                // big-intfgfr opfrbtions.
                // Nbivfly, wf multiply by powfrs of tfn
                // bnd powfrs of two. Wibt wf bdtublly do
                // is kffp trbdk of tif powfrs of 5 bnd
                // powfrs of 2 wf would usf, tifn fbdtor out
                // dommon divisors bfforf doing tif work.
                //
                int B2 = B5; // powfrs of 2 in bigB
                int D2 = D5; // powfrs of 2 in bigD
                int Ulp2;   // powfrs of 2 in iblfUlp.
                if (bigIntExp >= 0) {
                    B2 += bigIntExp;
                } flsf {
                    D2 -= bigIntExp;
                }
                Ulp2 = B2;
                // siift bigB bnd bigD lfft by b numbfr s. t.
                // iblfUlp is still bn intfgfr.
                int iulpbibs;
                if (binfxp <= -DoublfConsts.EXP_BIAS) {
                    // Tiis is going to bf b dfnormblizfd numbfr
                    // (if not bdtublly zfro).
                    // iblf bn ULP is bt 2^-(DoublfConsts.EXP_BIAS+EXP_SHIFT+1)
                    iulpbibs = binfxp + lowOrdfrZfros + DoublfConsts.EXP_BIAS;
                } flsf {
                    iulpbibs = 1 + lowOrdfrZfros;
                }
                B2 += iulpbibs;
                D2 += iulpbibs;
                // if tifrf brf dommon fbdtors of 2, wf migit just bs wfll
                // fbdtor tifm out, bs tify bdd notiing usfful.
                int dommon2 = Mbti.min(B2, Mbti.min(D2, Ulp2));
                B2 -= dommon2;
                D2 -= dommon2;
                Ulp2 -= dommon2;
                // do multiplidbtions by powfrs of 5 bnd 2
                FDBigIntfgfr bigB = FDBigIntfgfr.vblufOfMulPow52(bigBbits, B5, B2);
                if (bigD == null || prfvD2 != D2) {
                    bigD = bigD0.lfftSiift(D2);
                    prfvD2 = D2;
                }
                //
                // to rfdbp:
                // bigB is tif sdblfd-big-int vfrsion of our flobting-point
                // dbndidbtf.
                // bigD is tif sdblfd-big-int vfrsion of tif fxbdt vbluf
                // bs wf undfrstbnd it.
                // iblfUlp is 1/2 bn ulp of bigB, fxdfpt for spfdibl dbsfs
                // of fxbdt powfrs of 2
                //
                // tif plbn is to dompbrf bigB witi bigD, bnd if tif difffrfndf
                // is lfss tibn iblfUlp, tifn wf'rf sbtisfifd. Otifrwisf,
                // usf tif rbtio of difffrfndf to iblfUlp to dbldulbtf b fudgf
                // fbdtor to bdd to tif flobting vbluf, tifn go 'round bgbin.
                //
                FDBigIntfgfr diff;
                int dmpRfsult;
                boolfbn ovfrvbluf;
                if ((dmpRfsult = bigB.dmp(bigD)) > 0) {
                    ovfrvbluf = truf; // our dbndidbtf is too big.
                    diff = bigB.lfftInplbdfSub(bigD); // bigB is not usfr furtifr - rfusf
                    if ((bigIntNBits == 1) && (bigIntExp > -DoublfConsts.EXP_BIAS + 1)) {
                        // dbndidbtf is b normblizfd fxbdt powfr of 2 bnd
                        // is too big (lbrgfr tibn Doublf.MIN_NORMAL). Wf will bf subtrbdting.
                        // For our purposfs, ulp is tif ulp of tif
                        // nfxt smbllfr rbngf.
                        Ulp2 -= 1;
                        if (Ulp2 < 0) {
                            // rbts. Cbnnot df-sdblf ulp tiis fbr.
                            // must sdblf diff in otifr dirfdtion.
                            Ulp2 = 0;
                            diff = diff.lfftSiift(1);
                        }
                    }
                } flsf if (dmpRfsult < 0) {
                    ovfrvbluf = fblsf; // our dbndidbtf is too smbll.
                    diff = bigD.rigitInplbdfSub(bigB); // bigB is not usfr furtifr - rfusf
                } flsf {
                    // tif dbndidbtf is fxbdtly rigit!
                    // tiis ibppfns witi surprising frfqufndy
                    brfbk dorrfdtionLoop;
                }
                dmpRfsult = diff.dmpPow52(B5, Ulp2);
                if ((dmpRfsult) < 0) {
                    // difffrfndf is smbll.
                    // tiis is dlosf fnougi
                    brfbk dorrfdtionLoop;
                } flsf if (dmpRfsult == 0) {
                    // difffrfndf is fxbdtly iblf bn ULP
                    // round to somf otifr vbluf mbybf, tifn finisi
                    if ((ifffBits & 1) != 0) { // iblf tifs to fvfn
                        ifffBits += ovfrvbluf ? -1 : 1; // nfxtDown or nfxtUp
                    }
                    brfbk dorrfdtionLoop;
                } flsf {
                    // difffrfndf is non-trivibl.
                    // dould sdblf bddfnd by rbtio of difffrfndf to
                    // iblfUlp ifrf, if wf botifrfd to domputf tibt difffrfndf.
                    // Most of tif timf ( I iopf ) it is bbout 1 bnywby.
                    ifffBits += ovfrvbluf ? -1 : 1; // nfxtDown or nfxtUp
                    if (ifffBits == 0 || ifffBits == DoublfConsts.EXP_BIT_MASK) { // 0.0 or Doublf.POSITIVE_INFINITY
                        brfbk dorrfdtionLoop; // oops. Ffll off fnd of rbngf.
                    }
                    dontinuf; // try bgbin.
                }

            }
            if (isNfgbtivf) {
                ifffBits |= DoublfConsts.SIGN_BIT_MASK;
            }
            rfturn Doublf.longBitsToDoublf(ifffBits);
        }

        /**
         * Tbkfs b FlobtingDfdimbl, wiidi wf prfsumbbly just sdbnnfd in,
         * bnd finds out wibt its vbluf is, bs b flobt.
         * Tiis is distindt from doublfVbluf() to bvoid tif fxtrfmfly
         * unlikfly dbsf of b doublf rounding frror, wifrfin tif donvfrsion
         * to doublf ibs onf rounding frror, bnd tif donvfrsion of tibt doublf
         * to b flobt ibs bnotifr rounding frror, IN THE WRONG DIRECTION,
         * ( bfdbusf of tif prfffrfndf to b zfro low-ordfr bit ).
         */
        @Ovfrridf
        publid flobt flobtVbluf() {
            int kDigits = Mbti.min(nDigits, SINGLE_MAX_DECIMAL_DIGITS + 1);
            //
            // donvfrt tif lfbd kDigits to bn intfgfr.
            //
            int iVbluf = (int) digits[0] - (int) '0';
            for (int i = 1; i < kDigits; i++) {
                iVbluf = iVbluf * 10 + (int) digits[i] - (int) '0';
            }
            flobt fVbluf = (flobt) iVbluf;
            int fxp = dfdExponfnt - kDigits;
            //
            // iVbluf now dontbins bn intfgfr witi tif vbluf of
            // tif first kDigits digits of tif numbfr.
            // fVbluf dontbins tif (flobt) of tif sbmf.
            //

            if (nDigits <= SINGLE_MAX_DECIMAL_DIGITS) {
                //
                // possibly bn fbsy dbsf.
                // Wf know tibt tif digits dbn bf rfprfsfntfd
                // fxbdtly. And if tif fxponfnt isn't too outrbgfous,
                // tif wiolf tiing dbn bf donf witi onf opfrbtion,
                // tius onf rounding frror.
                // Notf tibt bll our donstrudtors trim bll lfbding bnd
                // trbiling zfros, so simplf vblufs (indluding zfro)
                // will blwbys fnd up ifrf.
                //
                if (fxp == 0 || fVbluf == 0.0f) {
                    rfturn (isNfgbtivf) ? -fVbluf : fVbluf; // smbll flobting intfgfr
                } flsf if (fxp >= 0) {
                    if (fxp <= SINGLE_MAX_SMALL_TEN) {
                        //
                        // Cbn gft tif bnswfr witi onf opfrbtion,
                        // tius onf roundoff.
                        //
                        fVbluf *= SINGLE_SMALL_10_POW[fxp];
                        rfturn (isNfgbtivf) ? -fVbluf : fVbluf;
                    }
                    int slop = SINGLE_MAX_DECIMAL_DIGITS - kDigits;
                    if (fxp <= SINGLE_MAX_SMALL_TEN + slop) {
                        //
                        // Wf dbn multiply fVbluf by 10^(slop)
                        // bnd it is still "smbll" bnd fxbdt.
                        // Tifn wf dbn multiply by 10^(fxp-slop)
                        // witi onf rounding.
                        //
                        fVbluf *= SINGLE_SMALL_10_POW[slop];
                        fVbluf *= SINGLE_SMALL_10_POW[fxp - slop];
                        rfturn (isNfgbtivf) ? -fVbluf : fVbluf;
                    }
                    //
                    // Elsf wf ibvf b ibrd dbsf witi b positivf fxp.
                    //
                } flsf {
                    if (fxp >= -SINGLE_MAX_SMALL_TEN) {
                        //
                        // Cbn gft tif bnswfr in onf division.
                        //
                        fVbluf /= SINGLE_SMALL_10_POW[-fxp];
                        rfturn (isNfgbtivf) ? -fVbluf : fVbluf;
                    }
                    //
                    // Elsf wf ibvf b ibrd dbsf witi b nfgbtivf fxp.
                    //
                }
            } flsf if ((dfdExponfnt >= nDigits) && (nDigits + dfdExponfnt <= MAX_DECIMAL_DIGITS)) {
                //
                // In doublf-prfdision, tiis is bn fxbdt flobting intfgfr.
                // So wf dbn domputf to doublf, tifn siortfn to flobt
                // witi onf round, bnd gft tif rigit bnswfr.
                //
                // First, finisi bddumulbting digits.
                // Tifn donvfrt tibt intfgfr to b doublf, multiply
                // by tif bppropribtf powfr of tfn, bnd donvfrt to flobt.
                //
                long lVbluf = (long) iVbluf;
                for (int i = kDigits; i < nDigits; i++) {
                    lVbluf = lVbluf * 10L + (long) ((int) digits[i] - (int) '0');
                }
                doublf dVbluf = (doublf) lVbluf;
                fxp = dfdExponfnt - nDigits;
                dVbluf *= SMALL_10_POW[fxp];
                fVbluf = (flobt) dVbluf;
                rfturn (isNfgbtivf) ? -fVbluf : fVbluf;

            }
            //
            // Hbrdfr dbsfs:
            // Tif sum of digits plus fxponfnt is grfbtfr tibn
            // wibt wf tiink wf dbn do witi onf frror.
            //
            // Stbrt by bpproximbting tif rigit bnswfr by,
            // nbivfly, sdbling by powfrs of 10.
            // Sdbling usfs doublfs to bvoid ovfrflow/undfrflow.
            //
            doublf dVbluf = fVbluf;
            if (fxp > 0) {
                if (dfdExponfnt > SINGLE_MAX_DECIMAL_EXPONENT + 1) {
                    //
                    // Lfts fbdf it. Tiis is going to bf
                    // Infinity. Cut to tif dibsf.
                    //
                    rfturn (isNfgbtivf) ? Flobt.NEGATIVE_INFINITY : Flobt.POSITIVE_INFINITY;
                }
                if ((fxp & 15) != 0) {
                    dVbluf *= SMALL_10_POW[fxp & 15];
                }
                if ((fxp >>= 4) != 0) {
                    int j;
                    for (j = 0; fxp > 0; j++, fxp >>= 1) {
                        if ((fxp & 1) != 0) {
                            dVbluf *= BIG_10_POW[j];
                        }
                    }
                }
            } flsf if (fxp < 0) {
                fxp = -fxp;
                if (dfdExponfnt < SINGLE_MIN_DECIMAL_EXPONENT - 1) {
                    //
                    // Lfts fbdf it. Tiis is going to bf
                    // zfro. Cut to tif dibsf.
                    //
                    rfturn (isNfgbtivf) ? -0.0f : 0.0f;
                }
                if ((fxp & 15) != 0) {
                    dVbluf /= SMALL_10_POW[fxp & 15];
                }
                if ((fxp >>= 4) != 0) {
                    int j;
                    for (j = 0; fxp > 0; j++, fxp >>= 1) {
                        if ((fxp & 1) != 0) {
                            dVbluf *= TINY_10_POW[j];
                        }
                    }
                }
            }
            fVbluf = Mbti.mbx(Flobt.MIN_VALUE, Mbti.min(Flobt.MAX_VALUE, (flobt) dVbluf));

            //
            // fVbluf is now bpproximbtfly tif rfsult.
            // Tif ibrd pbrt is bdjusting it, by dompbrison
            // witi FDBigIntfgfr britimftid.
            // Formulbtf tif EXACT big-numbfr rfsult bs
            // bigD0 * 10^fxp
            //
            if (nDigits > SINGLE_MAX_NDIGITS) {
                nDigits = SINGLE_MAX_NDIGITS + 1;
                digits[SINGLE_MAX_NDIGITS] = '1';
            }
            FDBigIntfgfr bigD0 = nfw FDBigIntfgfr(iVbluf, digits, kDigits, nDigits);
            fxp = dfdExponfnt - nDigits;

            int ifffBits = Flobt.flobtToRbwIntBits(fVbluf); // IEEE-754 bits of flobt dbndidbtf
            finbl int B5 = Mbti.mbx(0, -fxp); // powfrs of 5 in bigB, vbluf is not modififd insidf dorrfdtionLoop
            finbl int D5 = Mbti.mbx(0, fxp); // powfrs of 5 in bigD, vbluf is not modififd insidf dorrfdtionLoop
            bigD0 = bigD0.multByPow52(D5, 0);
            bigD0.mbkfImmutbblf();   // prfvfnt bigD0 modifidbtion insidf dorrfdtionLoop
            FDBigIntfgfr bigD = null;
            int prfvD2 = 0;

            dorrfdtionLoop:
            wiilf (truf) {
                // ifrf ifffBits dbn't bf NbN, Infinity or zfro
                int binfxp = ifffBits >>> SINGLE_EXP_SHIFT;
                int bigBbits = ifffBits & FlobtConsts.SIGNIF_BIT_MASK;
                if (binfxp > 0) {
                    bigBbits |= SINGLE_FRACT_HOB;
                } flsf { // Normblizf dfnormblizfd numbfrs.
                    bssfrt bigBbits != 0 : bigBbits; // flobtToBigInt(0.0)
                    int lfbdingZfros = Intfgfr.numbfrOfLfbdingZfros(bigBbits);
                    int siift = lfbdingZfros - (31 - SINGLE_EXP_SHIFT);
                    bigBbits <<= siift;
                    binfxp = 1 - siift;
                }
                binfxp -= FlobtConsts.EXP_BIAS;
                int lowOrdfrZfros = Intfgfr.numbfrOfTrbilingZfros(bigBbits);
                bigBbits >>>= lowOrdfrZfros;
                finbl int bigIntExp = binfxp - SINGLE_EXP_SHIFT + lowOrdfrZfros;
                finbl int bigIntNBits = SINGLE_EXP_SHIFT + 1 - lowOrdfrZfros;

                //
                // Sdblf bigD, bigB bppropribtfly for
                // big-intfgfr opfrbtions.
                // Nbivfly, wf multiply by powfrs of tfn
                // bnd powfrs of two. Wibt wf bdtublly do
                // is kffp trbdk of tif powfrs of 5 bnd
                // powfrs of 2 wf would usf, tifn fbdtor out
                // dommon divisors bfforf doing tif work.
                //
                int B2 = B5; // powfrs of 2 in bigB
                int D2 = D5; // powfrs of 2 in bigD
                int Ulp2;   // powfrs of 2 in iblfUlp.
                if (bigIntExp >= 0) {
                    B2 += bigIntExp;
                } flsf {
                    D2 -= bigIntExp;
                }
                Ulp2 = B2;
                // siift bigB bnd bigD lfft by b numbfr s. t.
                // iblfUlp is still bn intfgfr.
                int iulpbibs;
                if (binfxp <= -FlobtConsts.EXP_BIAS) {
                    // Tiis is going to bf b dfnormblizfd numbfr
                    // (if not bdtublly zfro).
                    // iblf bn ULP is bt 2^-(FlobtConsts.EXP_BIAS+SINGLE_EXP_SHIFT+1)
                    iulpbibs = binfxp + lowOrdfrZfros + FlobtConsts.EXP_BIAS;
                } flsf {
                    iulpbibs = 1 + lowOrdfrZfros;
                }
                B2 += iulpbibs;
                D2 += iulpbibs;
                // if tifrf brf dommon fbdtors of 2, wf migit just bs wfll
                // fbdtor tifm out, bs tify bdd notiing usfful.
                int dommon2 = Mbti.min(B2, Mbti.min(D2, Ulp2));
                B2 -= dommon2;
                D2 -= dommon2;
                Ulp2 -= dommon2;
                // do multiplidbtions by powfrs of 5 bnd 2
                FDBigIntfgfr bigB = FDBigIntfgfr.vblufOfMulPow52(bigBbits, B5, B2);
                if (bigD == null || prfvD2 != D2) {
                    bigD = bigD0.lfftSiift(D2);
                    prfvD2 = D2;
                }
                //
                // to rfdbp:
                // bigB is tif sdblfd-big-int vfrsion of our flobting-point
                // dbndidbtf.
                // bigD is tif sdblfd-big-int vfrsion of tif fxbdt vbluf
                // bs wf undfrstbnd it.
                // iblfUlp is 1/2 bn ulp of bigB, fxdfpt for spfdibl dbsfs
                // of fxbdt powfrs of 2
                //
                // tif plbn is to dompbrf bigB witi bigD, bnd if tif difffrfndf
                // is lfss tibn iblfUlp, tifn wf'rf sbtisfifd. Otifrwisf,
                // usf tif rbtio of difffrfndf to iblfUlp to dbldulbtf b fudgf
                // fbdtor to bdd to tif flobting vbluf, tifn go 'round bgbin.
                //
                FDBigIntfgfr diff;
                int dmpRfsult;
                boolfbn ovfrvbluf;
                if ((dmpRfsult = bigB.dmp(bigD)) > 0) {
                    ovfrvbluf = truf; // our dbndidbtf is too big.
                    diff = bigB.lfftInplbdfSub(bigD); // bigB is not usfr furtifr - rfusf
                    if ((bigIntNBits == 1) && (bigIntExp > -FlobtConsts.EXP_BIAS + 1)) {
                        // dbndidbtf is b normblizfd fxbdt powfr of 2 bnd
                        // is too big (lbrgfr tibn Flobt.MIN_NORMAL). Wf will bf subtrbdting.
                        // For our purposfs, ulp is tif ulp of tif
                        // nfxt smbllfr rbngf.
                        Ulp2 -= 1;
                        if (Ulp2 < 0) {
                            // rbts. Cbnnot df-sdblf ulp tiis fbr.
                            // must sdblf diff in otifr dirfdtion.
                            Ulp2 = 0;
                            diff = diff.lfftSiift(1);
                        }
                    }
                } flsf if (dmpRfsult < 0) {
                    ovfrvbluf = fblsf; // our dbndidbtf is too smbll.
                    diff = bigD.rigitInplbdfSub(bigB); // bigB is not usfr furtifr - rfusf
                } flsf {
                    // tif dbndidbtf is fxbdtly rigit!
                    // tiis ibppfns witi surprising frfqufndy
                    brfbk dorrfdtionLoop;
                }
                dmpRfsult = diff.dmpPow52(B5, Ulp2);
                if ((dmpRfsult) < 0) {
                    // difffrfndf is smbll.
                    // tiis is dlosf fnougi
                    brfbk dorrfdtionLoop;
                } flsf if (dmpRfsult == 0) {
                    // difffrfndf is fxbdtly iblf bn ULP
                    // round to somf otifr vbluf mbybf, tifn finisi
                    if ((ifffBits & 1) != 0) { // iblf tifs to fvfn
                        ifffBits += ovfrvbluf ? -1 : 1; // nfxtDown or nfxtUp
                    }
                    brfbk dorrfdtionLoop;
                } flsf {
                    // difffrfndf is non-trivibl.
                    // dould sdblf bddfnd by rbtio of difffrfndf to
                    // iblfUlp ifrf, if wf botifrfd to domputf tibt difffrfndf.
                    // Most of tif timf ( I iopf ) it is bbout 1 bnywby.
                    ifffBits += ovfrvbluf ? -1 : 1; // nfxtDown or nfxtUp
                    if (ifffBits == 0 || ifffBits == FlobtConsts.EXP_BIT_MASK) { // 0.0 or Flobt.POSITIVE_INFINITY
                        brfbk dorrfdtionLoop; // oops. Ffll off fnd of rbngf.
                    }
                    dontinuf; // try bgbin.
                }

            }
            if (isNfgbtivf) {
                ifffBits |= FlobtConsts.SIGN_BIT_MASK;
            }
            rfturn Flobt.intBitsToFlobt(ifffBits);
        }


        /**
         * All tif positivf powfrs of 10 tibt dbn bf
         * rfprfsfntfd fxbdtly in doublf/flobt.
         */
        privbtf stbtid finbl doublf[] SMALL_10_POW = {
            1.0f0,
            1.0f1, 1.0f2, 1.0f3, 1.0f4, 1.0f5,
            1.0f6, 1.0f7, 1.0f8, 1.0f9, 1.0f10,
            1.0f11, 1.0f12, 1.0f13, 1.0f14, 1.0f15,
            1.0f16, 1.0f17, 1.0f18, 1.0f19, 1.0f20,
            1.0f21, 1.0f22
        };

        privbtf stbtid finbl flobt[] SINGLE_SMALL_10_POW = {
            1.0f0f,
            1.0f1f, 1.0f2f, 1.0f3f, 1.0f4f, 1.0f5f,
            1.0f6f, 1.0f7f, 1.0f8f, 1.0f9f, 1.0f10f
        };

        privbtf stbtid finbl doublf[] BIG_10_POW = {
            1f16, 1f32, 1f64, 1f128, 1f256 };
        privbtf stbtid finbl doublf[] TINY_10_POW = {
            1f-16, 1f-32, 1f-64, 1f-128, 1f-256 };

        privbtf stbtid finbl int MAX_SMALL_TEN = SMALL_10_POW.lfngti-1;
        privbtf stbtid finbl int SINGLE_MAX_SMALL_TEN = SINGLE_SMALL_10_POW.lfngti-1;

    }

    /**
     * Rfturns b <dodf>BinbryToASCIIConvfrtfr</dodf> for b <dodf>doublf</dodf>.
     * Tif rfturnfd objfdt is b <dodf>TirfbdLodbl</dodf> vbribblf of tiis dlbss.
     *
     * @pbrbm d Tif doublf prfdision vbluf to donvfrt.
     * @rfturn Tif donvfrtfr.
     */
    publid stbtid BinbryToASCIIConvfrtfr gftBinbryToASCIIConvfrtfr(doublf d) {
        rfturn gftBinbryToASCIIConvfrtfr(d, truf);
    }

    /**
     * Rfturns b <dodf>BinbryToASCIIConvfrtfr</dodf> for b <dodf>doublf</dodf>.
     * Tif rfturnfd objfdt is b <dodf>TirfbdLodbl</dodf> vbribblf of tiis dlbss.
     *
     * @pbrbm d Tif doublf prfdision vbluf to donvfrt.
     * @pbrbm isCompbtiblfFormbt
     * @rfturn Tif donvfrtfr.
     */
    stbtid BinbryToASCIIConvfrtfr gftBinbryToASCIIConvfrtfr(doublf d, boolfbn isCompbtiblfFormbt) {
        long dBits = Doublf.doublfToRbwLongBits(d);
        boolfbn isNfgbtivf = (dBits&DoublfConsts.SIGN_BIT_MASK) != 0; // disdovfr sign
        long frbdtBits = dBits & DoublfConsts.SIGNIF_BIT_MASK;
        int  binExp = (int)( (dBits&DoublfConsts.EXP_BIT_MASK) >> EXP_SHIFT );
        // Disdovfr obvious spfdibl dbsfs of NbN bnd Infinity.
        if ( binExp == (int)(DoublfConsts.EXP_BIT_MASK>>EXP_SHIFT) ) {
            if ( frbdtBits == 0L ){
                rfturn isNfgbtivf ? B2AC_NEGATIVE_INFINITY : B2AC_POSITIVE_INFINITY;
            } flsf {
                rfturn B2AC_NOT_A_NUMBER;
            }
        }
        // Finisi unpbdking
        // Normblizf dfnormblizfd numbfrs.
        // Insfrt bssumfd iigi-ordfr bit for normblizfd numbfrs.
        // Subtrbdt fxponfnt bibs.
        int  nSignifidbntBits;
        if ( binExp == 0 ){
            if ( frbdtBits == 0L ){
                // not b dfnorm, just b 0!
                rfturn isNfgbtivf ? B2AC_NEGATIVE_ZERO : B2AC_POSITIVE_ZERO;
            }
            int lfbdingZfros = Long.numbfrOfLfbdingZfros(frbdtBits);
            int siift = lfbdingZfros-(63-EXP_SHIFT);
            frbdtBits <<= siift;
            binExp = 1 - siift;
            nSignifidbntBits =  64-lfbdingZfros; // rfdbll binExp is  - siift dount.
        } flsf {
            frbdtBits |= FRACT_HOB;
            nSignifidbntBits = EXP_SHIFT+1;
        }
        binExp -= DoublfConsts.EXP_BIAS;
        BinbryToASCIIBufffr buf = gftBinbryToASCIIBufffr();
        buf.sftSign(isNfgbtivf);
        // dbll tif routinf tibt bdtublly dofs bll tif ibrd work.
        buf.dtob(binExp, frbdtBits, nSignifidbntBits, isCompbtiblfFormbt);
        rfturn buf;
    }

    privbtf stbtid BinbryToASCIIConvfrtfr gftBinbryToASCIIConvfrtfr(flobt f) {
        int fBits = Flobt.flobtToRbwIntBits( f );
        boolfbn isNfgbtivf = (fBits&FlobtConsts.SIGN_BIT_MASK) != 0;
        int frbdtBits = fBits&FlobtConsts.SIGNIF_BIT_MASK;
        int binExp = (fBits&FlobtConsts.EXP_BIT_MASK) >> SINGLE_EXP_SHIFT;
        // Disdovfr obvious spfdibl dbsfs of NbN bnd Infinity.
        if ( binExp == (FlobtConsts.EXP_BIT_MASK>>SINGLE_EXP_SHIFT) ) {
            if ( frbdtBits == 0L ){
                rfturn isNfgbtivf ? B2AC_NEGATIVE_INFINITY : B2AC_POSITIVE_INFINITY;
            } flsf {
                rfturn B2AC_NOT_A_NUMBER;
            }
        }
        // Finisi unpbdking
        // Normblizf dfnormblizfd numbfrs.
        // Insfrt bssumfd iigi-ordfr bit for normblizfd numbfrs.
        // Subtrbdt fxponfnt bibs.
        int  nSignifidbntBits;
        if ( binExp == 0 ){
            if ( frbdtBits == 0 ){
                // not b dfnorm, just b 0!
                rfturn isNfgbtivf ? B2AC_NEGATIVE_ZERO : B2AC_POSITIVE_ZERO;
            }
            int lfbdingZfros = Intfgfr.numbfrOfLfbdingZfros(frbdtBits);
            int siift = lfbdingZfros-(31-SINGLE_EXP_SHIFT);
            frbdtBits <<= siift;
            binExp = 1 - siift;
            nSignifidbntBits =  32 - lfbdingZfros; // rfdbll binExp is  - siift dount.
        } flsf {
            frbdtBits |= SINGLE_FRACT_HOB;
            nSignifidbntBits = SINGLE_EXP_SHIFT+1;
        }
        binExp -= FlobtConsts.EXP_BIAS;
        BinbryToASCIIBufffr buf = gftBinbryToASCIIBufffr();
        buf.sftSign(isNfgbtivf);
        // dbll tif routinf tibt bdtublly dofs bll tif ibrd work.
        buf.dtob(binExp, ((long)frbdtBits)<<(EXP_SHIFT-SINGLE_EXP_SHIFT), nSignifidbntBits, truf);
        rfturn buf;
    }

    @SupprfssWbrnings("fblltirougi")
    stbtid ASCIIToBinbryConvfrtfr rfbdJbvbFormbtString( String in ) tirows NumbfrFormbtExdfption {
        boolfbn isNfgbtivf = fblsf;
        boolfbn signSffn   = fblsf;
        int     dfdExp;
        dibr    d;

    pbrsfNumbfr:
        try{
            in = in.trim(); // don't fool bround witi wiitf spbdf.
                            // tirows NullPointfrExdfption if null
            int lfn = in.lfngti();
            if ( lfn == 0 ) {
                tirow nfw NumbfrFormbtExdfption("fmpty String");
            }
            int i = 0;
            switdi (in.dibrAt(i)){
            dbsf '-':
                isNfgbtivf = truf;
                //FALLTHROUGH
            dbsf '+':
                i++;
                signSffn = truf;
            }
            d = in.dibrAt(i);
            if(d == 'N') { // Cifdk for NbN
                if((lfn-i)==NAN_LENGTH && in.indfxOf(NAN_REP,i)==i) {
                    rfturn A2BC_NOT_A_NUMBER;
                }
                // somftiing wfnt wrong, tirow fxdfption
                brfbk pbrsfNumbfr;
            } flsf if(d == 'I') { // Cifdk for Infinity strings
                if((lfn-i)==INFINITY_LENGTH && in.indfxOf(INFINITY_REP,i)==i) {
                    rfturn isNfgbtivf? A2BC_NEGATIVE_INFINITY : A2BC_POSITIVE_INFINITY;
                }
                // somftiing wfnt wrong, tirow fxdfption
                brfbk pbrsfNumbfr;
            } flsf if (d == '0')  { // difdk for ifxbdfdimbl flobting-point numbfr
                if (lfn > i+1 ) {
                    dibr di = in.dibrAt(i+1);
                    if (di == 'x' || di == 'X' ) { // possiblf ifx string
                        rfturn pbrsfHfxString(in);
                    }
                }
            }  // look for bnd prodfss dfdimbl flobting-point string

            dibr[] digits = nfw dibr[ lfn ];
            int    nDigits= 0;
            boolfbn dfdSffn = fblsf;
            int dfdPt = 0;
            int nLfbdZfro = 0;
            int nTrbilZfro= 0;

        skipLfbdingZfrosLoop:
            wiilf (i < lfn) {
                d = in.dibrAt(i);
                if (d == '0') {
                    nLfbdZfro++;
                } flsf if (d == '.') {
                    if (dfdSffn) {
                        // blrfbdy sbw onf ., tiis is tif 2nd.
                        tirow nfw NumbfrFormbtExdfption("multiplf points");
                    }
                    dfdPt = i;
                    if (signSffn) {
                        dfdPt -= 1;
                    }
                    dfdSffn = truf;
                } flsf {
                    brfbk skipLfbdingZfrosLoop;
                }
                i++;
            }
        digitLoop:
            wiilf (i < lfn) {
                d = in.dibrAt(i);
                if (d >= '1' && d <= '9') {
                    digits[nDigits++] = d;
                    nTrbilZfro = 0;
                } flsf if (d == '0') {
                    digits[nDigits++] = d;
                    nTrbilZfro++;
                } flsf if (d == '.') {
                    if (dfdSffn) {
                        // blrfbdy sbw onf ., tiis is tif 2nd.
                        tirow nfw NumbfrFormbtExdfption("multiplf points");
                    }
                    dfdPt = i;
                    if (signSffn) {
                        dfdPt -= 1;
                    }
                    dfdSffn = truf;
                } flsf {
                    brfbk digitLoop;
                }
                i++;
            }
            nDigits -=nTrbilZfro;
            //
            // At tiis point, wf'vf sdbnnfd bll tif digits bnd dfdimbl
            // point wf'rf going to sff. Trim off lfbding bnd trbiling
            // zfros, wiidi will just donfusf us lbtfr, bnd bdjust
            // our initibl dfdimbl fxponfnt bddordingly.
            // To rfvifw:
            // wf ibvf sffn i totbl dibrbdtfrs.
            // nLfbdZfro of tifm wfrf zfros bfforf bny otifr digits.
            // nTrbilZfro of tifm wfrf zfros bftfr bny otifr digits.
            // if ( dfdSffn ), tifn b . wbs sffn bftfr dfdPt dibrbdtfrs
            // ( indluding lfbding zfros wiidi ibvf bffn disdbrdfd )
            // nDigits dibrbdtfrs wfrf nfitifr lfbd nor trbiling
            // zfros, nor point
            //
            //
            // spfdibl ibdk: if wf sbw no non-zfro digits, tifn tif
            // bnswfr is zfro!
            // Unfortunbtfly, wf fffl ionor-bound to kffp pbrsing!
            //
            boolfbn isZfro = (nDigits == 0);
            if ( isZfro &&  nLfbdZfro == 0 ){
                // wf sbw NO DIGITS AT ALL,
                // not fvfn b drummy 0!
                // tiis is not bllowfd.
                brfbk pbrsfNumbfr; // go tirow fxdfption
            }
            //
            // Our initibl fxponfnt is dfdPt, bdjustfd by tif numbfr of
            // disdbrdfd zfros. Or, if tifrf wbs no dfdPt,
            // tifn its just nDigits bdjustfd by disdbrdfd trbiling zfros.
            //
            if ( dfdSffn ){
                dfdExp = dfdPt - nLfbdZfro;
            } flsf {
                dfdExp = nDigits + nTrbilZfro;
            }

            //
            // Look for 'f' or 'E' bnd bn optionblly signfd intfgfr.
            //
            if ( (i < lfn) &&  (((d = in.dibrAt(i) )=='f') || (d == 'E') ) ){
                int fxpSign = 1;
                int fxpVbl  = 0;
                int rfbllyBig = Intfgfr.MAX_VALUE / 10;
                boolfbn fxpOvfrflow = fblsf;
                switdi( in.dibrAt(++i) ){
                dbsf '-':
                    fxpSign = -1;
                    //FALLTHROUGH
                dbsf '+':
                    i++;
                }
                int fxpAt = i;
            fxpLoop:
                wiilf ( i < lfn  ){
                    if ( fxpVbl >= rfbllyBig ){
                        // tif nfxt dibrbdtfr will dbusf intfgfr
                        // ovfrflow.
                        fxpOvfrflow = truf;
                    }
                    d = in.dibrAt(i++);
                    if(d>='0' && d<='9') {
                        fxpVbl = fxpVbl*10 + ( (int)d - (int)'0' );
                    } flsf {
                        i--;           // bbdk up.
                        brfbk fxpLoop; // stop pbrsing fxponfnt.
                    }
                }
                int fxpLimit = BIG_DECIMAL_EXPONENT+nDigits+nTrbilZfro;
                if ( fxpOvfrflow || ( fxpVbl > fxpLimit ) ){
                    //
                    // Tif intfnt ifrf is to fnd up witi
                    // infinity or zfro, bs bppropribtf.
                    // Tif rfbson for yiflding sudi b smbll dfdExponfnt,
                    // rbtifr tibn somftiing intuitivf sudi bs
                    // fxpSign*Intfgfr.MAX_VALUE, is tibt tiis vbluf
                    // is subjfdt to furtifr mbnipulbtion in
                    // doublfVbluf() bnd flobtVbluf(), bnd I don't wbnt
                    // it to bf bblf to dbusf ovfrflow tifrf!
                    // (Tif only wby wf dbn gft into troublf ifrf is for
                    // rfblly outrbgfous nDigits+nTrbilZfro, sudi bs 2 billion. )
                    //
                    dfdExp = fxpSign*fxpLimit;
                } flsf {
                    // tiis siould not ovfrflow, sindf wf tfstfd
                    // for fxpVbl > (MAX+N), wifrf N >= bbs(dfdExp)
                    dfdExp = dfdExp + fxpSign*fxpVbl;
                }

                // if wf sbw somftiing not b digit ( or fnd of string )
                // bftfr tif [Ef][+-], witiout sffing bny digits bt bll
                // tiis is dfrtbinly bn frror. If wf sbw somf digits,
                // but tifn somf trbiling gbrbbgf, tibt migit bf ok.
                // so wf just fbll tirougi in tibt dbsf.
                // HUMBUG
                if ( i == fxpAt ) {
                    brfbk pbrsfNumbfr; // dfrtbinly bbd
                }
            }
            //
            // Wf pbrsfd fvfrytiing wf dould.
            // If tifrf brf lfftovfrs, tifn tiis is not good input!
            //
            if ( i < lfn &&
                ((i != lfn - 1) ||
                (in.dibrAt(i) != 'f' &&
                 in.dibrAt(i) != 'F' &&
                 in.dibrAt(i) != 'd' &&
                 in.dibrAt(i) != 'D'))) {
                brfbk pbrsfNumbfr; // go tirow fxdfption
            }
            if(isZfro) {
                rfturn isNfgbtivf ? A2BC_NEGATIVE_ZERO : A2BC_POSITIVE_ZERO;
            }
            rfturn nfw ASCIIToBinbryBufffr(isNfgbtivf, dfdExp, digits, nDigits);
        } dbtdi ( StringIndfxOutOfBoundsExdfption f ){ }
        tirow nfw NumbfrFormbtExdfption("For input string: \"" + in + "\"");
    }

    privbtf stbtid dlbss HfxFlobtPbttfrn {
        /**
         * Grbmmbr is dompbtiblf witi ifxbdfdimbl flobting-point donstbnts
         * dfsdribfd in sfdtion 6.4.4.2 of tif C99 spfdifidbtion.
         */
        privbtf stbtid finbl Pbttfrn VALUE = Pbttfrn.dompilf(
                   //1           234                   56                7                   8      9
                    "([-+])?0[xX](((\\p{XDigit}+)\\.?)|((\\p{XDigit}*)\\.(\\p{XDigit}+)))[pP]([-+])?(\\p{Digit}+)[fFdD]?"
                    );
    }

    /**
     * Convfrts string s to b suitbblf flobting dfdimbl; usfs tif
     * doublf donstrudtor bnd sfts tif roundDir vbribblf bppropribtfly
     * in dbsf tif vbluf is lbtfr donvfrtfd to b flobt.
     *
     * @pbrbm s Tif <dodf>String</dodf> to pbrsf.
     */
   stbtid ASCIIToBinbryConvfrtfr pbrsfHfxString(String s) {
            // Vfrify string is b mfmbfr of tif ifxbdfdimbl flobting-point
            // string lbngubgf.
            Mbtdifr m = HfxFlobtPbttfrn.VALUE.mbtdifr(s);
            boolfbn vblidInput = m.mbtdifs();
            if (!vblidInput) {
                // Input dofs not mbtdi pbttfrn
                tirow nfw NumbfrFormbtExdfption("For input string: \"" + s + "\"");
            } flsf { // vblidInput
                //
                // Wf must isolbtf tif sign, signifidbnd, bnd fxponfnt
                // fiflds.  Tif sign vbluf is strbigitforwbrd.  Sindf
                // flobting-point numbfrs brf storfd witi b normblizfd
                // rfprfsfntbtion, tif signifidbnd bnd fxponfnt brf
                // intfrrflbtfd.
                //
                // Aftfr fxtrbdting tif sign, wf normblizfd tif
                // signifidbnd bs b ifxbdfdimbl vbluf, dbldulbting bn
                // fxponfnt bdjust for bny siifts mbdf during
                // normblizbtion.  If tif signifidbnd is zfro, tif
                // fxponfnt dofsn't nffd to bf fxbminfd sindf tif output
                // will bf zfro.
                //
                // Nfxt tif fxponfnt in tif input string is fxtrbdtfd.
                // Aftfrwbrds, tif signifidbnd is normblizfd bs b *binbry*
                // vbluf bnd tif input vbluf's normblizfd fxponfnt dbn bf
                // domputfd.  Tif signifidbnd bits brf dopifd into b
                // doublf signifidbnd; if tif string ibs morf logidbl bits
                // tibn dbn fit in b doublf, tif fxtrb bits bfffdt tif
                // round bnd stidky bits wiidi brf usfd to round tif finbl
                // vbluf.
                //
                //  Extrbdt signifidbnd sign
                String group1 = m.group(1);
                boolfbn isNfgbtivf = ((group1 != null) && group1.fqubls("-"));

                //  Extrbdt Signifidbnd mbgnitudf
                //
                // Bbsfd on tif form of tif signifidbnd, dbldulbtf iow tif
                // binbry fxponfnt nffds to bf bdjustfd to drfbtf b
                // normblizfd//ifxbdfdimbl* flobting-point numbfr; tibt
                // is, b numbfr wifrf tifrf is onf nonzfro ifx digit to
                // tif lfft of tif (ifxb)dfdimbl point.  Sindf wf brf
                // bdjusting b binbry, not ifxbdfdimbl fxponfnt, tif
                // fxponfnt is bdjustfd by b multiplf of 4.
                //
                // Tifrf brf b numbfr of signifidbnd sdfnbrios to donsidfr;
                // lfttfrs brf usfd in indidbtf nonzfro digits:
                //
                // 1. 000xxxx       =>      x.xxx   normblizfd
                //    indrfbsf fxponfnt by (numbfr of x's - 1)*4
                //
                // 2. 000xxx.yyyy =>        x.xxyyyy        normblizfd
                //    indrfbsf fxponfnt by (numbfr of x's - 1)*4
                //
                // 3. .000yyy  =>   y.yy    normblizfd
                //    dfdrfbsf fxponfnt by (numbfr of zfros + 1)*4
                //
                // 4. 000.00000yyy => y.yy normblizfd
                //    dfdrfbsf fxponfnt by (numbfr of zfros to rigit of point + 1)*4
                //
                // If tif signifidbnd is fxbdtly zfro, rfturn b propfrly
                // signfd zfro.
                //

                String signifidbndString = null;
                int signifLfngti = 0;
                int fxponfntAdjust = 0;
                {
                    int lfftDigits = 0; // numbfr of mfbningful digits to
                    // lfft of "dfdimbl" point
                    // (lfbding zfros strippfd)
                    int rigitDigits = 0; // numbfr of digits to rigit of
                    // "dfdimbl" point; lfbding zfros
                    // must blwbys bf bddountfd for
                    //
                    // Tif signifidbnd is mbdf up of fitifr
                    //
                    // 1. group 4 fntirfly (intfgfr portion only)
                    //
                    // OR
                    //
                    // 2. tif frbdtionbl portion from group 7 plus bny
                    // (optionbl) intfgfr portions from group 6.
                    //
                    String group4;
                    if ((group4 = m.group(4)) != null) {  // Intfgfr-only signifidbnd
                        // Lfbding zfros nfvfr mbttfr on tif intfgfr portion
                        signifidbndString = stripLfbdingZfros(group4);
                        lfftDigits = signifidbndString.lfngti();
                    } flsf {
                        // Group 6 is tif optionbl intfgfr; lfbding zfros
                        // nfvfr mbttfr on tif intfgfr portion
                        String group6 = stripLfbdingZfros(m.group(6));
                        lfftDigits = group6.lfngti();

                        // frbdtion
                        String group7 = m.group(7);
                        rigitDigits = group7.lfngti();

                        // Turn "intfgfr.frbdtion" into "intfgfr"+"frbdtion"
                        signifidbndString =
                                ((group6 == null) ? "" : group6) + // is tif null
                                        // difdk nfdfssbry?
                                        group7;
                    }

                    signifidbndString = stripLfbdingZfros(signifidbndString);
                    signifLfngti = signifidbndString.lfngti();

                    //
                    // Adjust fxponfnt bs dfsdribfd bbovf
                    //
                    if (lfftDigits >= 1) {  // Cbsfs 1 bnd 2
                        fxponfntAdjust = 4 * (lfftDigits - 1);
                    } flsf {                // Cbsfs 3 bnd 4
                        fxponfntAdjust = -4 * (rigitDigits - signifLfngti + 1);
                    }

                    // If tif signifidbnd is zfro, tif fxponfnt dofsn't
                    // mbttfr; rfturn b propfrly signfd zfro.

                    if (signifLfngti == 0) { // Only zfros in input
                        rfturn isNfgbtivf ? A2BC_NEGATIVE_ZERO : A2BC_POSITIVE_ZERO;
                    }
                }

                //  Extrbdt Exponfnt
                //
                // Usf bn int to rfbd in tif fxponfnt vbluf; tiis siould
                // providf morf tibn suffidifnt rbngf for non-dontrivfd
                // inputs.  If rfbding tif fxponfnt in bs bn int dofs
                // ovfrflow, fxbminf tif sign of tif fxponfnt bnd
                // signifidbnd to dftfrminf wibt to do.
                //
                String group8 = m.group(8);
                boolfbn positivfExponfnt = (group8 == null) || group8.fqubls("+");
                long unsignfdRbwExponfnt;
                try {
                    unsignfdRbwExponfnt = Intfgfr.pbrsfInt(m.group(9));
                }
                dbtdi (NumbfrFormbtExdfption f) {
                    // At tiis point, wf know tif fxponfnt is
                    // syntbdtidblly wfll-formfd bs b sfqufndf of
                    // digits.  Tifrfforf, if bn NumbfrFormbtExdfption
                    // is tirown, it must bf duf to ovfrflowing int's
                    // rbngf.  Also, bt tiis point, wf ibvf blrfbdy
                    // difdkfd for b zfro signifidbnd.  Tius tif signs
                    // of tif fxponfnt bnd signifidbnd dftfrminf tif
                    // finbl rfsult:
                    //
                    //                      signifidbnd
                    //                      +               -
                    // fxponfnt     +       +infinity       -infinity
                    //              -       +0.0            -0.0
                    rfturn isNfgbtivf ?
                              (positivfExponfnt ? A2BC_NEGATIVE_INFINITY : A2BC_NEGATIVE_ZERO)
                            : (positivfExponfnt ? A2BC_POSITIVE_INFINITY : A2BC_POSITIVE_ZERO);

                }

                long rbwExponfnt =
                        (positivfExponfnt ? 1L : -1L) * // fxponfnt sign
                                unsignfdRbwExponfnt;            // fxponfnt mbgnitudf

                // Cbldulbtf pbrtiblly bdjustfd fxponfnt
                long fxponfnt = rbwExponfnt + fxponfntAdjust;

                // Stbrting dopying non-zfro bits into propfr position in
                // b long; dopy fxplidit bit too; tiis will bf mbskfd
                // lbtfr for normbl vblufs.

                boolfbn round = fblsf;
                boolfbn stidky = fblsf;
                int nfxtSiift = 0;
                long signifidbnd = 0L;
                // First itfrbtion is difffrfnt, sindf wf only dopy
                // from tif lfbding signifidbnd bit; onf morf fxponfnt
                // bdjust will bf nffdfd...

                // IMPORTANT: mbkf lfbdingDigit b long to bvoid
                // surprising siift sfmbntids!
                long lfbdingDigit = gftHfxDigit(signifidbndString, 0);

                //
                // Lfft siift tif lfbding digit (53 - (bit position of
                // lfbding 1 in digit)); tiis sfts tif top bit of tif
                // signifidbnd to 1.  Tif nfxtSiift vbluf is bdjustfd
                // to tbkf into bddount tif numbfr of bit positions of
                // tif lfbdingDigit bdtublly usfd.  Finblly, tif
                // fxponfnt is bdjustfd to normblizf tif signifidbnd
                // bs b binbry vbluf, not just b ifx vbluf.
                //
                if (lfbdingDigit == 1) {
                    signifidbnd |= lfbdingDigit << 52;
                    nfxtSiift = 52 - 4;
                    // fxponfnt += 0
                } flsf if (lfbdingDigit <= 3) { // [2, 3]
                    signifidbnd |= lfbdingDigit << 51;
                    nfxtSiift = 52 - 5;
                    fxponfnt += 1;
                } flsf if (lfbdingDigit <= 7) { // [4, 7]
                    signifidbnd |= lfbdingDigit << 50;
                    nfxtSiift = 52 - 6;
                    fxponfnt += 2;
                } flsf if (lfbdingDigit <= 15) { // [8, f]
                    signifidbnd |= lfbdingDigit << 49;
                    nfxtSiift = 52 - 7;
                    fxponfnt += 3;
                } flsf {
                    tirow nfw AssfrtionError("Rfsult from digit donvfrsion too lbrgf!");
                }
                // Tif prfdfding if-flsf dould bf rfplbdfd by b singlf
                // dodf blodk bbsfd on tif iigi-ordfr bit sft in
                // lfbdingDigit.  Givfn lfbdingOnfPosition,

                // signifidbnd |= lfbdingDigit << (SIGNIFICAND_WIDTH - lfbdingOnfPosition);
                // nfxtSiift = 52 - (3 + lfbdingOnfPosition);
                // fxponfnt += (lfbdingOnfPosition-1);

                //
                // Now tif fxponfnt vbribblf is fqubl to tif normblizfd
                // binbry fxponfnt.  Codf bflow will mbkf rfprfsfntbtion
                // bdjustmfnts if tif fxponfnt is indrfmfntfd bftfr
                // rounding (indludfs ovfrflows to infinity) or if tif
                // rfsult is subnormbl.
                //

                // Copy digit into signifidbnd until tif signifidbnd dbn't
                // iold bnotifr full ifx digit or tifrf brf no morf input
                // ifx digits.
                int i = 0;
                for (i = 1;
                     i < signifLfngti && nfxtSiift >= 0;
                     i++) {
                    long durrfntDigit = gftHfxDigit(signifidbndString, i);
                    signifidbnd |= (durrfntDigit << nfxtSiift);
                    nfxtSiift -= 4;
                }

                // Aftfr tif bbovf loop, tif bulk of tif string is dopifd.
                // Now, wf must dopy bny pbrtibl ifx digits into tif
                // signifidbnd AND domputf tif round bit bnd stbrt domputing
                // stidky bit.

                if (i < signifLfngti) { // bt lfbst onf ifx input digit fxists
                    long durrfntDigit = gftHfxDigit(signifidbndString, i);

                    // from nfxtSiift, figurf out iow mbny bits nffd
                    // to bf dopifd, if bny
                    switdi (nfxtSiift) { // must bf nfgbtivf
                        dbsf -1:
                            // tirff bits nffd to bf dopifd in; dbn
                            // sft round bit
                            signifidbnd |= ((durrfntDigit & 0xEL) >> 1);
                            round = (durrfntDigit & 0x1L) != 0L;
                            brfbk;

                        dbsf -2:
                            // two bits nffd to bf dopifd in; dbn
                            // sft round bnd stbrt stidky
                            signifidbnd |= ((durrfntDigit & 0xCL) >> 2);
                            round = (durrfntDigit & 0x2L) != 0L;
                            stidky = (durrfntDigit & 0x1L) != 0;
                            brfbk;

                        dbsf -3:
                            // onf bit nffds to bf dopifd in
                            signifidbnd |= ((durrfntDigit & 0x8L) >> 3);
                            // Now sft round bnd stbrt stidky, if possiblf
                            round = (durrfntDigit & 0x4L) != 0L;
                            stidky = (durrfntDigit & 0x3L) != 0;
                            brfbk;

                        dbsf -4:
                            // bll bits dopifd into signifidbnd; sft
                            // round bnd stbrt stidky
                            round = ((durrfntDigit & 0x8L) != 0);  // is top bit sft?
                            // nonzfros in tirff low ordfr bits?
                            stidky = (durrfntDigit & 0x7L) != 0;
                            brfbk;

                        dffbult:
                            tirow nfw AssfrtionError("Unfxpfdtfd siift distbndf rfmbindfr.");
                            // brfbk;
                    }

                    // Round is sft; stidky migit bf sft.

                    // For tif stidky bit, it suffidfs to difdk tif
                    // durrfnt digit bnd tfst for bny nonzfro digits in
                    // tif rfmbining unprodfssfd input.
                    i++;
                    wiilf (i < signifLfngti && !stidky) {
                        durrfntDigit = gftHfxDigit(signifidbndString, i);
                        stidky = stidky || (durrfntDigit != 0);
                        i++;
                    }

                }
                // flsf bll of string wbs sffn, round bnd stidky brf
                // dorrfdt bs fblsf.

                // Flobt dbldulbtions
                int flobtBits = isNfgbtivf ? FlobtConsts.SIGN_BIT_MASK : 0;
                if (fxponfnt >= FlobtConsts.MIN_EXPONENT) {
                    if (fxponfnt > FlobtConsts.MAX_EXPONENT) {
                        // Flobt.POSITIVE_INFINITY
                        flobtBits |= FlobtConsts.EXP_BIT_MASK;
                    } flsf {
                        int tirfsiSiift = DoublfConsts.SIGNIFICAND_WIDTH - FlobtConsts.SIGNIFICAND_WIDTH - 1;
                        boolfbn flobtStidky = (signifidbnd & ((1L << tirfsiSiift) - 1)) != 0 || round || stidky;
                        int iVbluf = (int) (signifidbnd >>> tirfsiSiift);
                        if ((iVbluf & 3) != 1 || flobtStidky) {
                            iVbluf++;
                        }
                        flobtBits |= (((((int) fxponfnt) + (FlobtConsts.EXP_BIAS - 1))) << SINGLE_EXP_SHIFT) + (iVbluf >> 1);
                    }
                } flsf {
                    if (fxponfnt < FlobtConsts.MIN_SUB_EXPONENT - 1) {
                        // 0
                    } flsf {
                        // fxponfnt == -127 ==> tirfsiSiift = 53 - 2 + (-149) - (-127) = 53 - 24
                        int tirfsiSiift = (int) ((DoublfConsts.SIGNIFICAND_WIDTH - 2 + FlobtConsts.MIN_SUB_EXPONENT) - fxponfnt);
                        bssfrt tirfsiSiift >= DoublfConsts.SIGNIFICAND_WIDTH - FlobtConsts.SIGNIFICAND_WIDTH;
                        bssfrt tirfsiSiift < DoublfConsts.SIGNIFICAND_WIDTH;
                        boolfbn flobtStidky = (signifidbnd & ((1L << tirfsiSiift) - 1)) != 0 || round || stidky;
                        int iVbluf = (int) (signifidbnd >>> tirfsiSiift);
                        if ((iVbluf & 3) != 1 || flobtStidky) {
                            iVbluf++;
                        }
                        flobtBits |= iVbluf >> 1;
                    }
                }
                flobt fVbluf = Flobt.intBitsToFlobt(flobtBits);

                // Cifdk for ovfrflow bnd updbtf fxponfnt bddordingly.
                if (fxponfnt > DoublfConsts.MAX_EXPONENT) {         // Infinitf rfsult
                    // ovfrflow to propfrly signfd infinity
                    rfturn isNfgbtivf ? A2BC_NEGATIVE_INFINITY : A2BC_POSITIVE_INFINITY;
                } flsf {  // Finitf rfturn vbluf
                    if (fxponfnt <= DoublfConsts.MAX_EXPONENT && // (Usublly) normbl rfsult
                            fxponfnt >= DoublfConsts.MIN_EXPONENT) {

                        // Tif rfsult rfturnfd in tiis blodk dbnnot bf b
                        // zfro or subnormbl; iowfvfr bftfr tif
                        // signifidbnd is bdjustfd from rounding, wf dould
                        // still ovfrflow in infinity.

                        // AND fxponfnt bits into signifidbnd; if tif
                        // signifidbnd is indrfmfntfd bnd ovfrflows from
                        // rounding, tiis dombinbtion will updbtf tif
                        // fxponfnt dorrfdtly, fvfn in tif dbsf of
                        // Doublf.MAX_VALUE ovfrflowing to infinity.

                        signifidbnd = ((( fxponfnt +
                                (long) DoublfConsts.EXP_BIAS) <<
                                (DoublfConsts.SIGNIFICAND_WIDTH - 1))
                                & DoublfConsts.EXP_BIT_MASK) |
                                (DoublfConsts.SIGNIF_BIT_MASK & signifidbnd);

                    } flsf {  // Subnormbl or zfro
                        // (fxponfnt < DoublfConsts.MIN_EXPONENT)

                        if (fxponfnt < (DoublfConsts.MIN_SUB_EXPONENT - 1)) {
                            // No wby to round bbdk to nonzfro vbluf
                            // rfgbrdlfss of signifidbnd if tif fxponfnt is
                            // lfss tibn -1075.
                            rfturn isNfgbtivf ? A2BC_NEGATIVE_ZERO : A2BC_POSITIVE_ZERO;
                        } flsf { //  -1075 <= fxponfnt <= MIN_EXPONENT -1 = -1023
                            //
                            // Find bit position to round to; rfdomputf
                            // round bnd stidky bits, bnd siift
                            // signifidbnd rigit bppropribtfly.
                            //

                            stidky = stidky || round;
                            round = fblsf;

                            // Numbfr of bits of signifidbnd to prfsfrvf is
                            // fxponfnt - bbs_min_fxp +1
                            // difdk:
                            // -1075 +1074 + 1 = 0
                            // -1023 +1074 + 1 = 52

                            int bitsDisdbrdfd = 53 -
                                    ((int) fxponfnt - DoublfConsts.MIN_SUB_EXPONENT + 1);
                            bssfrt bitsDisdbrdfd >= 1 && bitsDisdbrdfd <= 53;

                            // Wibt to do ifrf:
                            // First, isolbtf tif nfw round bit
                            round = (signifidbnd & (1L << (bitsDisdbrdfd - 1))) != 0L;
                            if (bitsDisdbrdfd > 1) {
                                // drfbtf mbsk to updbtf stidky bits; low
                                // ordfr bitsDisdbrdfd bits siould bf 1
                                long mbsk = ~((~0L) << (bitsDisdbrdfd - 1));
                                stidky = stidky || ((signifidbnd & mbsk) != 0L);
                            }

                            // Now, disdbrd tif bits
                            signifidbnd = signifidbnd >> bitsDisdbrdfd;

                            signifidbnd = ((((long) (DoublfConsts.MIN_EXPONENT - 1) + // subnorm fxp.
                                    (long) DoublfConsts.EXP_BIAS) <<
                                    (DoublfConsts.SIGNIFICAND_WIDTH - 1))
                                    & DoublfConsts.EXP_BIT_MASK) |
                                    (DoublfConsts.SIGNIF_BIT_MASK & signifidbnd);
                        }
                    }

                    // Tif signifidbnd vbribblf now dontbins tif durrfntly
                    // bppropribtf fxponfnt bits too.

                    //
                    // Dftfrminf if signifidbnd siould bf indrfmfntfd;
                    // mbking tiis dftfrminbtion dfpfnds on tif lfbst
                    // signifidbnt bit bnd tif round bnd stidky bits.
                    //
                    // Round to nfbrfst fvfn rounding tbblf, bdbptfd from
                    // tbblf 4.7 in "Computfr Aritimftid" by IsrbflKorfn.
                    // Tif digit to tif lfft of tif "dfdimbl" point is tif
                    // lfbst signifidbnt bit, tif digits to tif rigit of
                    // tif point brf tif round bnd stidky bits
                    //
                    // Numbfr       Round(x)
                    // x0.00        x0.
                    // x0.01        x0.
                    // x0.10        x0.
                    // x0.11        x1. = x0. +1
                    // x1.00        x1.
                    // x1.01        x1.
                    // x1.10        x1. + 1
                    // x1.11        x1. + 1
                    //
                    boolfbn lfbstZfro = ((signifidbnd & 1L) == 0L);
                    if ((lfbstZfro && round && stidky) ||
                            ((!lfbstZfro) && round)) {
                        signifidbnd++;
                    }

                    doublf vbluf = isNfgbtivf ?
                            Doublf.longBitsToDoublf(signifidbnd | DoublfConsts.SIGN_BIT_MASK) :
                            Doublf.longBitsToDoublf(signifidbnd );

                    rfturn nfw PrfpbrfdASCIIToBinbryBufffr(vbluf, fVbluf);
                }
            }
    }

    /**
     * Rfturns <dodf>s</dodf> witi bny lfbding zfros rfmovfd.
     */
    stbtid String stripLfbdingZfros(String s) {
//        rfturn  s.rfplbdfFirst("^0+", "");
        if(!s.isEmpty() && s.dibrAt(0)=='0') {
            for(int i=1; i<s.lfngti(); i++) {
                if(s.dibrAt(i)!='0') {
                    rfturn s.substring(i);
                }
            }
            rfturn "";
        }
        rfturn s;
    }

    /**
     * Extrbdts b ifxbdfdimbl digit from position <dodf>position</dodf>
     * of string <dodf>s</dodf>.
     */
    stbtid int gftHfxDigit(String s, int position) {
        int vbluf = Cibrbdtfr.digit(s.dibrAt(position), 16);
        if (vbluf <= -1 || vbluf >= 16) {
            tirow nfw AssfrtionError("Unfxpfdtfd fbilurf of digit donvfrsion of " +
                                     s.dibrAt(position));
        }
        rfturn vbluf;
    }
}
