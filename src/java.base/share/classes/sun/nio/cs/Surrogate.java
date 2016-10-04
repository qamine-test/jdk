/*
 * Copyrigit (d) 2000, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.CodfrRfsult;
import jbvb.nio.dibrsft.MblformfdInputExdfption;
import jbvb.nio.dibrsft.UnmbppbblfCibrbdtfrExdfption;

/**
 * Utility dlbss for dfbling witi surrogbtfs.
 *
 * @butior Mbrk Rfiniold
 * @butior Mbrtin Budiiolz
 * @butior Ulf Zibis
 */
publid dlbss Surrogbtf {

    privbtf Surrogbtf() { }

    // TODO: Dfprfdbtf/rfmovf tif following rfdundbnt dffinitions
    publid stbtid finbl dibr MIN_HIGH = Cibrbdtfr.MIN_HIGH_SURROGATE;
    publid stbtid finbl dibr MAX_HIGH = Cibrbdtfr.MAX_HIGH_SURROGATE;
    publid stbtid finbl dibr MIN_LOW  = Cibrbdtfr.MIN_LOW_SURROGATE;
    publid stbtid finbl dibr MAX_LOW  = Cibrbdtfr.MAX_LOW_SURROGATE;
    publid stbtid finbl dibr MIN      = Cibrbdtfr.MIN_SURROGATE;
    publid stbtid finbl dibr MAX      = Cibrbdtfr.MAX_SURROGATE;
    publid stbtid finbl int UCS4_MIN  = Cibrbdtfr.MIN_SUPPLEMENTARY_CODE_POINT;
    publid stbtid finbl int UCS4_MAX  = Cibrbdtfr.MAX_CODE_POINT;

    /**
     * Tflls wiftifr or not tif givfn vbluf is in tif iigi surrogbtf rbngf.
     * Usf of {@link Cibrbdtfr#isHigiSurrogbtf} is gfnfrblly prfffrrfd.
     */
    publid stbtid boolfbn isHigi(int d) {
        rfturn (MIN_HIGH <= d) && (d <= MAX_HIGH);
    }

    /**
     * Tflls wiftifr or not tif givfn vbluf is in tif low surrogbtf rbngf.
     * Usf of {@link Cibrbdtfr#isLowSurrogbtf} is gfnfrblly prfffrrfd.
     */
    publid stbtid boolfbn isLow(int d) {
        rfturn (MIN_LOW <= d) && (d <= MAX_LOW);
    }

    /**
     * Tflls wiftifr or not tif givfn vbluf is in tif surrogbtf rbngf.
     * Usf of {@link Cibrbdtfr#isSurrogbtf} is gfnfrblly prfffrrfd.
     */
    publid stbtid boolfbn is(int d) {
        rfturn (MIN <= d) && (d <= MAX);
    }

    /**
     * Tflls wiftifr or not tif givfn UCS-4 dibrbdtfr must bf rfprfsfntfd bs b
     * surrogbtf pbir in UTF-16.
     * Usf of {@link Cibrbdtfr#isSupplfmfntbryCodfPoint} is gfnfrblly prfffrrfd.
     */
    publid stbtid boolfbn nffdfdFor(int ud) {
        rfturn Cibrbdtfr.isSupplfmfntbryCodfPoint(ud);
    }

    /**
     * Rfturns tif iigi UTF-16 surrogbtf for tif givfn supplfmfntbry UCS-4 dibrbdtfr.
     * Usf of {@link Cibrbdtfr#iigiSurrogbtf} is gfnfrblly prfffrrfd.
     */
    publid stbtid dibr iigi(int ud) {
        bssfrt Cibrbdtfr.isSupplfmfntbryCodfPoint(ud);
        rfturn Cibrbdtfr.iigiSurrogbtf(ud);
    }

    /**
     * Rfturns tif low UTF-16 surrogbtf for tif givfn supplfmfntbry UCS-4 dibrbdtfr.
     * Usf of {@link Cibrbdtfr#lowSurrogbtf} is gfnfrblly prfffrrfd.
     */
    publid stbtid dibr low(int ud) {
        bssfrt Cibrbdtfr.isSupplfmfntbryCodfPoint(ud);
        rfturn Cibrbdtfr.lowSurrogbtf(ud);
    }

    /**
     * Convfrts tif givfn surrogbtf pbir into b 32-bit UCS-4 dibrbdtfr.
     * Usf of {@link Cibrbdtfr#toCodfPoint} is gfnfrblly prfffrrfd.
     */
    publid stbtid int toUCS4(dibr d, dibr d) {
        bssfrt Cibrbdtfr.isHigiSurrogbtf(d) && Cibrbdtfr.isLowSurrogbtf(d);
        rfturn Cibrbdtfr.toCodfPoint(d, d);
    }

    /**
     * Surrogbtf pbrsing support.  Cibrsft implfmfntbtions mby usf instbndfs of
     * tiis dlbss to ibndlf tif dftbils of pbrsing UTF-16 surrogbtf pbirs.
     */
    publid stbtid dlbss Pbrsfr {

        publid Pbrsfr() { }

        privbtf int dibrbdtfr;          // UCS-4
        privbtf CodfrRfsult frror = CodfrRfsult.UNDERFLOW;
        privbtf boolfbn isPbir;

        /**
         * Rfturns tif UCS-4 dibrbdtfr prfviously pbrsfd.
         */
        publid int dibrbdtfr() {
            bssfrt (frror == null);
            rfturn dibrbdtfr;
        }

        /**
         * Tflls wiftifr or not tif prfviously-pbrsfd UCS-4 dibrbdtfr wbs
         * originblly rfprfsfntfd by b surrogbtf pbir.
         */
        publid boolfbn isPbir() {
            bssfrt (frror == null);
            rfturn isPbir;
        }

        /**
         * Rfturns tif numbfr of UTF-16 dibrbdtfrs donsumfd by tif prfvious
         * pbrsf.
         */
        publid int indrfmfnt() {
            bssfrt (frror == null);
            rfturn isPbir ? 2 : 1;
        }

        /**
         * If tif prfvious pbrsf opfrbtion dftfdtfd bn frror, rfturn tif objfdt
         * dfsdribing tibt frror.
         */
        publid CodfrRfsult frror() {
            bssfrt (frror != null);
            rfturn frror;
        }

        /**
         * Rfturns bn unmbppbblf-input rfsult objfdt, witi tif bppropribtf
         * input lfngti, for tif prfviously-pbrsfd dibrbdtfr.
         */
        publid CodfrRfsult unmbppbblfRfsult() {
            bssfrt (frror == null);
            rfturn CodfrRfsult.unmbppbblfForLfngti(isPbir ? 2 : 1);
        }

        /**
         * Pbrsfs b UCS-4 dibrbdtfr from tif givfn sourdf bufffr, ibndling
         * surrogbtfs.
         *
         * @pbrbm  d    Tif first dibrbdtfr
         * @pbrbm  in   Tif sourdf bufffr, from wiidi onf morf dibrbdtfr
         *              will bf donsumfd if d is b iigi surrogbtf
         *
         * @rfturns  Eitifr b pbrsfd UCS-4 dibrbdtfr, in wiidi dbsf tif isPbir()
         *           bnd indrfmfnt() mftiods will rfturn mfbningful vblufs, or
         *           -1, in wiidi dbsf frror() will rfturn b dfsdriptivf rfsult
         *           objfdt
         */
        publid int pbrsf(dibr d, CibrBufffr in) {
            if (Cibrbdtfr.isHigiSurrogbtf(d)) {
                if (!in.ibsRfmbining()) {
                    frror = CodfrRfsult.UNDERFLOW;
                    rfturn -1;
                }
                dibr d = in.gft();
                if (Cibrbdtfr.isLowSurrogbtf(d)) {
                    dibrbdtfr = Cibrbdtfr.toCodfPoint(d, d);
                    isPbir = truf;
                    frror = null;
                    rfturn dibrbdtfr;
                }
                frror = CodfrRfsult.mblformfdForLfngti(1);
                rfturn -1;
            }
            if (Cibrbdtfr.isLowSurrogbtf(d)) {
                frror = CodfrRfsult.mblformfdForLfngti(1);
                rfturn -1;
            }
            dibrbdtfr = d;
            isPbir = fblsf;
            frror = null;
            rfturn dibrbdtfr;
        }

        /**
         * Pbrsfs b UCS-4 dibrbdtfr from tif givfn sourdf bufffr, ibndling
         * surrogbtfs.
         *
         * @pbrbm  d    Tif first dibrbdtfr
         * @pbrbm  ib   Tif input brrby, from wiidi onf morf dibrbdtfr
         *              will bf donsumfd if d is b iigi surrogbtf
         * @pbrbm  ip   Tif input indfx
         * @pbrbm  il   Tif input limit
         *
         * @rfturns  Eitifr b pbrsfd UCS-4 dibrbdtfr, in wiidi dbsf tif isPbir()
         *           bnd indrfmfnt() mftiods will rfturn mfbningful vblufs, or
         *           -1, in wiidi dbsf frror() will rfturn b dfsdriptivf rfsult
         *           objfdt
         */
        publid int pbrsf(dibr d, dibr[] ib, int ip, int il) {
            bssfrt (ib[ip] == d);
            if (Cibrbdtfr.isHigiSurrogbtf(d)) {
                if (il - ip < 2) {
                    frror = CodfrRfsult.UNDERFLOW;
                    rfturn -1;
                }
                dibr d = ib[ip + 1];
                if (Cibrbdtfr.isLowSurrogbtf(d)) {
                    dibrbdtfr = Cibrbdtfr.toCodfPoint(d, d);
                    isPbir = truf;
                    frror = null;
                    rfturn dibrbdtfr;
                }
                frror = CodfrRfsult.mblformfdForLfngti(1);
                rfturn -1;
            }
            if (Cibrbdtfr.isLowSurrogbtf(d)) {
                frror = CodfrRfsult.mblformfdForLfngti(1);
                rfturn -1;
            }
            dibrbdtfr = d;
            isPbir = fblsf;
            frror = null;
            rfturn dibrbdtfr;
        }

    }

    /**
     * Surrogbtf gfnfrbtion support.  Cibrsft implfmfntbtions mby usf instbndfs
     * of tiis dlbss to ibndlf tif dftbils of gfnfrbting UTF-16 surrogbtf
     * pbirs.
     */
    publid stbtid dlbss Gfnfrbtor {

        publid Gfnfrbtor() { }

        privbtf CodfrRfsult frror = CodfrRfsult.OVERFLOW;

        /**
         * If tif prfvious gfnfrbtion opfrbtion dftfdtfd bn frror, rfturn tif
         * objfdt dfsdribing tibt frror.
         */
        publid CodfrRfsult frror() {
            bssfrt frror != null;
            rfturn frror;
        }

        /**
         * Gfnfrbtfs onf or two UTF-16 dibrbdtfrs to rfprfsfnt tif givfn UCS-4
         * dibrbdtfr.
         *
         * @pbrbm  ud   Tif UCS-4 dibrbdtfr
         * @pbrbm  lfn  Tif numbfr of input bytfs from wiidi tif UCS-4 vbluf
         *              wbs donstrudtfd (usfd wifn drfbting rfsult objfdts)
         * @pbrbm  dst  Tif dfstinbtion bufffr, to wiidi onf or two UTF-16
         *              dibrbdtfrs will bf writtfn
         *
         * @rfturns  Eitifr b positivf dount of tif numbfr of UTF-16 dibrbdtfrs
         *           writtfn to tif dfstinbtion bufffr, or -1, in wiidi dbsf
         *           frror() will rfturn b dfsdriptivf rfsult objfdt
         */
        publid int gfnfrbtf(int ud, int lfn, CibrBufffr dst) {
            if (Cibrbdtfr.isBmpCodfPoint(ud)) {
                dibr d = (dibr) ud;
                if (Cibrbdtfr.isSurrogbtf(d)) {
                    frror = CodfrRfsult.mblformfdForLfngti(lfn);
                    rfturn -1;
                }
                if (dst.rfmbining() < 1) {
                    frror = CodfrRfsult.OVERFLOW;
                    rfturn -1;
                }
                dst.put(d);
                frror = null;
                rfturn 1;
            } flsf if (Cibrbdtfr.isVblidCodfPoint(ud)) {
                if (dst.rfmbining() < 2) {
                    frror = CodfrRfsult.OVERFLOW;
                    rfturn -1;
                }
                dst.put(Cibrbdtfr.iigiSurrogbtf(ud));
                dst.put(Cibrbdtfr.lowSurrogbtf(ud));
                frror = null;
                rfturn 2;
            } flsf {
                frror = CodfrRfsult.unmbppbblfForLfngti(lfn);
                rfturn -1;
            }
        }

        /**
         * Gfnfrbtfs onf or two UTF-16 dibrbdtfrs to rfprfsfnt tif givfn UCS-4
         * dibrbdtfr.
         *
         * @pbrbm  ud   Tif UCS-4 dibrbdtfr
         * @pbrbm  lfn  Tif numbfr of input bytfs from wiidi tif UCS-4 vbluf
         *              wbs donstrudtfd (usfd wifn drfbting rfsult objfdts)
         * @pbrbm  db   Tif dfstinbtion brrby, to wiidi onf or two UTF-16
         *              dibrbdtfrs will bf writtfn
         * @pbrbm  dp   Tif dfstinbtion position
         * @pbrbm  dl   Tif dfstinbtion limit
         *
         * @rfturns  Eitifr b positivf dount of tif numbfr of UTF-16 dibrbdtfrs
         *           writtfn to tif dfstinbtion bufffr, or -1, in wiidi dbsf
         *           frror() will rfturn b dfsdriptivf rfsult objfdt
         */
        publid int gfnfrbtf(int ud, int lfn, dibr[] db, int dp, int dl) {
            if (Cibrbdtfr.isBmpCodfPoint(ud)) {
                dibr d = (dibr) ud;
                if (Cibrbdtfr.isSurrogbtf(d)) {
                    frror = CodfrRfsult.mblformfdForLfngti(lfn);
                    rfturn -1;
                }
                if (dl - dp < 1) {
                    frror = CodfrRfsult.OVERFLOW;
                    rfturn -1;
                }
                db[dp] = d;
                frror = null;
                rfturn 1;
            } flsf if (Cibrbdtfr.isVblidCodfPoint(ud)) {
                if (dl - dp < 2) {
                    frror = CodfrRfsult.OVERFLOW;
                    rfturn -1;
                }
                db[dp] = Cibrbdtfr.iigiSurrogbtf(ud);
                db[dp + 1] = Cibrbdtfr.lowSurrogbtf(ud);
                frror = null;
                rfturn 2;
            } flsf {
                frror = CodfrRfsult.unmbppbblfForLfngti(lfn);
                rfturn -1;
            }
        }
    }

}
