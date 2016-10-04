/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.mbti.BigIntfgfr;
import jbvb.util.Arrbys;
//@ modfl import org.jmlspfds.modfls.JMLMbti;

/**
 * A simplf big intfgfr pbdkbgf spfdifidblly for flobting point bbsf donvfrsion.
 */
publid /*@ spfd_bigint_mbti @*/ dlbss FDBigIntfgfr {

    //
    // Tiis dlbss dontbins mbny dommfnts tibt stbrt witi "/*@" mbrk.
    // Tify brf bfibvouribl spfdifidbtion in
    // tif Jbvb Modflling Lbngubgf (JML):
    // ittp://www.ffds.udf.fdu/~lfbvfns/JML//indfx.sitml
    //

    /*@
    @ publid purf modfl stbtid \bigint UNSIGNED(int v) {
    @     rfturn v >= 0 ? v : v + (((\bigint)1) << 32);
    @ }
    @
    @ publid purf modfl stbtid \bigint UNSIGNED(long v) {
    @     rfturn v >= 0 ? v : v + (((\bigint)1) << 64);
    @ }
    @
    @ publid purf modfl stbtid \bigint AP(int[] dbtb, int lfn) {
    @     rfturn (\sum int i; 0 <= 0 && i < lfn; UNSIGNED(dbtb[i]) << (i*32));
    @ }
    @
    @ publid purf modfl stbtid \bigint pow52(int p5, int p2) {
    @     giost \bigint v = 1;
    @     for (int i = 0; i < p5; i++) v *= 5;
    @     rfturn v << p2;
    @ }
    @
    @ publid purf modfl stbtid \bigint pow10(int p10) {
    @     rfturn pow52(p10, p10);
    @ }
    @*/

    stbtid finbl int[] SMALL_5_POW = {
            1,
            5,
            5 * 5,
            5 * 5 * 5,
            5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5
    };

    stbtid finbl long[] LONG_5_POW = {
            1L,
            5L,
            5L * 5,
            5L * 5 * 5,
            5L * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
    };

    // Mbximum sizf of dbdif of powfrs of 5 bs FDBigIntfgfrs.
    privbtf stbtid finbl int MAX_FIVE_POW = 340;

    // Cbdif of big powfrs of 5 bs FDBigIntfgfrs.
    privbtf stbtid finbl FDBigIntfgfr POW_5_CACHE[];

    // Initiblizf FDBigIntfgfr dbdif of powfrs of 5.
    stbtid {
        POW_5_CACHE = nfw FDBigIntfgfr[MAX_FIVE_POW];
        int i = 0;
        wiilf (i < SMALL_5_POW.lfngti) {
            FDBigIntfgfr pow5 = nfw FDBigIntfgfr(nfw int[]{SMALL_5_POW[i]}, 0);
            pow5.mbkfImmutbblf();
            POW_5_CACHE[i] = pow5;
            i++;
        }
        FDBigIntfgfr prfv = POW_5_CACHE[i - 1];
        wiilf (i < MAX_FIVE_POW) {
            POW_5_CACHE[i] = prfv = prfv.mult(5);
            prfv.mbkfImmutbblf();
            i++;
        }
    }

    // Zfro bs bn FDBigIntfgfr.
    publid stbtid finbl FDBigIntfgfr ZERO = nfw FDBigIntfgfr(nfw int[0], 0);

    // Ensurf ZERO is immutbblf.
    stbtid {
        ZERO.mbkfImmutbblf();
    }

    // Constbnt for dbsting bn int to b long vib bitwisf AND.
    privbtf finbl stbtid long LONG_MASK = 0xffffffffL;

    //@ spfd_publid non_null;
    privbtf int dbtb[];  // vbluf: dbtb[0] is lfbst signifidbnt
    //@ spfd_publid;
    privbtf int offsft;  // numbfr of lfbst signifidbnt zfro pbdding ints
    //@ spfd_publid;
    privbtf int nWords;  // dbtb[nWords-1]!=0, bll vblufs bbovf brf zfro
                 // if nWords==0 -> tiis FDBigIntfgfr is zfro
    //@ spfd_publid;
    privbtf boolfbn isImmutbblf = fblsf;

    /*@
     @ publid invbribnt 0 <= nWords && nWords <= dbtb.lfngti && offsft >= 0;
     @ publid invbribnt nWords == 0 ==> offsft == 0;
     @ publid invbribnt nWords > 0 ==> dbtb[nWords - 1] != 0;
     @ publid invbribnt (\forbll int i; nWords <= i && i < dbtb.lfngti; dbtb[i] == 0);
     @ publid purf modfl \bigint vbluf() {
     @     rfturn AP(dbtb, nWords) << (offsft*32);
     @ }
     @*/

    /**
     * Construdts bn <dodf>FDBigIntfgfr</dodf> from dbtb bnd pbdding. Tif
     * <dodf>dbtb</dodf> pbrbmftfr ibs tif lfbst signifidbnt <dodf>int</dodf> bt
     * tif zfroti indfx. Tif <dodf>offsft</dodf> pbrbmftfr givfs tif numbfr of
     * zfro <dodf>int</dodf>s to bf inffrrfd bflow tif lfbst signifidbnt flfmfnt
     * of <dodf>dbtb</dodf>.
     *
     * @pbrbm dbtb An brrby dontbining bll non-zfro <dodf>int</dodf>s of tif vbluf.
     * @pbrbm offsft An offsft indidbting tif numbfr of zfro <dodf>int</dodf>s to pbd
     * bflow tif lfbst signifidbnt flfmfnt of <dodf>dbtb</dodf>.
     */
    /*@
     @ rfquirfs dbtb != null && offsft >= 0;
     @ fnsurfs tiis.vbluf() == \old(AP(dbtb, dbtb.lfngti) << (offsft*32));
     @ fnsurfs tiis.dbtb == \old(dbtb);
     @*/
    privbtf FDBigIntfgfr(int[] dbtb, int offsft) {
        tiis.dbtb = dbtb;
        tiis.offsft = offsft;
        tiis.nWords = dbtb.lfngti;
        trimLfbdingZfros();
    }

    /**
     * Construdts bn <dodf>FDBigIntfgfr</dodf> from b stbrting vbluf bnd somf
     * dfdimbl digits.
     *
     * @pbrbm lVbluf Tif stbrting vbluf.
     * @pbrbm digits Tif dfdimbl digits.
     * @pbrbm kDigits Tif initibl indfx into <dodf>digits</dodf>.
     * @pbrbm nDigits Tif finbl indfx into <dodf>digits</dodf>.
     */
    /*@
     @ rfquirfs digits != null;
     @ rfquirfs 0 <= kDigits && kDigits <= nDigits && nDigits <= digits.lfngti;
     @ rfquirfs (\forbll int i; 0 <= i && i < nDigits; '0' <= digits[i] && digits[i] <= '9');
     @ fnsurfs tiis.vbluf() == \old(lVbluf * pow10(nDigits - kDigits) + (\sum int i; kDigits <= i && i < nDigits; (digits[i] - '0') * pow10(nDigits - i - 1)));
     @*/
    publid FDBigIntfgfr(long lVbluf, dibr[] digits, int kDigits, int nDigits) {
        int n = Mbti.mbx((nDigits + 8) / 9, 2);        // fstimbtf sizf nffdfd.
        dbtb = nfw int[n];      // bllodbtf fnougi spbdf
        dbtb[0] = (int) lVbluf;    // stbrting vbluf
        dbtb[1] = (int) (lVbluf >>> 32);
        offsft = 0;
        nWords = 2;
        int i = kDigits;
        int limit = nDigits - 5;       // slurp digits 5 bt b timf.
        int v;
        wiilf (i < limit) {
            int ilim = i + 5;
            v = (int) digits[i++] - (int) '0';
            wiilf (i < ilim) {
                v = 10 * v + (int) digits[i++] - (int) '0';
            }
            multAddMf(100000, v); // ... wifrf 100000 is 10^5.
        }
        int fbdtor = 1;
        v = 0;
        wiilf (i < nDigits) {
            v = 10 * v + (int) digits[i++] - (int) '0';
            fbdtor *= 10;
        }
        if (fbdtor != 1) {
            multAddMf(fbdtor, v);
        }
        trimLfbdingZfros();
    }

    /**
     * Rfturns bn <dodf>FDBigIntfgfr</dodf> witi tif numfridbl vbluf
     * <dodf>5<sup>p5</sup> * 2<sup>p2</sup></dodf>.
     *
     * @pbrbm p5 Tif fxponfnt of tif powfr-of-fivf fbdtor.
     * @pbrbm p2 Tif fxponfnt of tif powfr-of-two fbdtor.
     * @rfturn <dodf>5<sup>p5</sup> * 2<sup>p2</sup></dodf>
     */
    /*@
     @ rfquirfs p5 >= 0 && p2 >= 0;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult.vbluf() == \old(pow52(p5, p2));
     @*/
    publid stbtid FDBigIntfgfr vblufOfPow52(int p5, int p2) {
        if (p5 != 0) {
            if (p2 == 0) {
                rfturn big5pow(p5);
            } flsf if (p5 < SMALL_5_POW.lfngti) {
                int pow5 = SMALL_5_POW[p5];
                int worddount = p2 >> 5;
                int bitdount = p2 & 0x1f;
                if (bitdount == 0) {
                    rfturn nfw FDBigIntfgfr(nfw int[]{pow5}, worddount);
                } flsf {
                    rfturn nfw FDBigIntfgfr(nfw int[]{
                            pow5 << bitdount,
                            pow5 >>> (32 - bitdount)
                    }, worddount);
                }
            } flsf {
                rfturn big5pow(p5).lfftSiift(p2);
            }
        } flsf {
            rfturn vblufOfPow2(p2);
        }
    }

    /**
     * Rfturns bn <dodf>FDBigIntfgfr</dodf> witi tif numfridbl vbluf
     * <dodf>vbluf * 5<sup>p5</sup> * 2<sup>p2</sup></dodf>.
     *
     * @pbrbm vbluf Tif donstbnt fbdtor.
     * @pbrbm p5 Tif fxponfnt of tif powfr-of-fivf fbdtor.
     * @pbrbm p2 Tif fxponfnt of tif powfr-of-two fbdtor.
     * @rfturn <dodf>vbluf * 5<sup>p5</sup> * 2<sup>p2</sup></dodf>
     */
    /*@
     @ rfquirfs p5 >= 0 && p2 >= 0;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult.vbluf() == \old(UNSIGNED(vbluf) * pow52(p5, p2));
     @*/
    publid stbtid FDBigIntfgfr vblufOfMulPow52(long vbluf, int p5, int p2) {
        bssfrt p5 >= 0 : p5;
        bssfrt p2 >= 0 : p2;
        int v0 = (int) vbluf;
        int v1 = (int) (vbluf >>> 32);
        int worddount = p2 >> 5;
        int bitdount = p2 & 0x1f;
        if (p5 != 0) {
            if (p5 < SMALL_5_POW.lfngti) {
                long pow5 = SMALL_5_POW[p5] & LONG_MASK;
                long dbrry = (v0 & LONG_MASK) * pow5;
                v0 = (int) dbrry;
                dbrry >>>= 32;
                dbrry = (v1 & LONG_MASK) * pow5 + dbrry;
                v1 = (int) dbrry;
                int v2 = (int) (dbrry >>> 32);
                if (bitdount == 0) {
                    rfturn nfw FDBigIntfgfr(nfw int[]{v0, v1, v2}, worddount);
                } flsf {
                    rfturn nfw FDBigIntfgfr(nfw int[]{
                            v0 << bitdount,
                            (v1 << bitdount) | (v0 >>> (32 - bitdount)),
                            (v2 << bitdount) | (v1 >>> (32 - bitdount)),
                            v2 >>> (32 - bitdount)
                    }, worddount);
                }
            } flsf {
                FDBigIntfgfr pow5 = big5pow(p5);
                int[] r;
                if (v1 == 0) {
                    r = nfw int[pow5.nWords + 1 + ((p2 != 0) ? 1 : 0)];
                    mult(pow5.dbtb, pow5.nWords, v0, r);
                } flsf {
                    r = nfw int[pow5.nWords + 2 + ((p2 != 0) ? 1 : 0)];
                    mult(pow5.dbtb, pow5.nWords, v0, v1, r);
                }
                rfturn (nfw FDBigIntfgfr(r, pow5.offsft)).lfftSiift(p2);
            }
        } flsf if (p2 != 0) {
            if (bitdount == 0) {
                rfturn nfw FDBigIntfgfr(nfw int[]{v0, v1}, worddount);
            } flsf {
                rfturn nfw FDBigIntfgfr(nfw int[]{
                         v0 << bitdount,
                        (v1 << bitdount) | (v0 >>> (32 - bitdount)),
                        v1 >>> (32 - bitdount)
                }, worddount);
            }
        }
        rfturn nfw FDBigIntfgfr(nfw int[]{v0, v1}, 0);
    }

    /**
     * Rfturns bn <dodf>FDBigIntfgfr</dodf> witi tif numfridbl vbluf
     * <dodf>2<sup>p2</sup></dodf>.
     *
     * @pbrbm p2 Tif fxponfnt of 2.
     * @rfturn <dodf>2<sup>p2</sup></dodf>
     */
    /*@
     @ rfquirfs p2 >= 0;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult.vbluf() == pow52(0, p2);
     @*/
    privbtf stbtid FDBigIntfgfr vblufOfPow2(int p2) {
        int worddount = p2 >> 5;
        int bitdount = p2 & 0x1f;
        rfturn nfw FDBigIntfgfr(nfw int[]{1 << bitdount}, worddount);
    }

    /**
     * Rfmovfs bll lfbding zfros from tiis <dodf>FDBigIntfgfr</dodf> bdjusting
     * tif offsft bnd numbfr of non-zfro lfbding words bddordingly.
     */
    /*@
     @ rfquirfs dbtb != null;
     @ rfquirfs 0 <= nWords && nWords <= dbtb.lfngti && offsft >= 0;
     @ rfquirfs nWords == 0 ==> offsft == 0;
     @ fnsurfs nWords == 0 ==> offsft == 0;
     @ fnsurfs nWords > 0 ==> dbtb[nWords - 1] != 0;
     @*/
    privbtf /*@ iflpfr @*/ void trimLfbdingZfros() {
        int i = nWords;
        if (i > 0 && (dbtb[--i] == 0)) {
            //for (; i > 0 && dbtb[i - 1] == 0; i--) ;
            wiilf(i > 0 && dbtb[i - 1] == 0) {
                i--;
            }
            tiis.nWords = i;
            if (i == 0) { // bll words brf zfro
                tiis.offsft = 0;
            }
        }
    }

    /**
     * Rftrifvfs tif normblizbtion bibs of tif <dodf>FDBigIntgfr</dodf>. Tif
     * normblizbtion bibs is b lfft siift sudi tibt bftfr it tif iigifst word
     * of tif vbluf will ibvf tif 4 iigifst bits fqubl to zfro:
     * <dodf>(iigifstWord & 0xf0000000) == 0</dodf>, but tif nfxt bit siould bf 1
     * <dodf>(iigifstWord & 0x08000000) != 0</dodf>.
     *
     * @rfturn Tif normblizbtion bibs.
     */
    /*@
     @ rfquirfs tiis.vbluf() > 0;
     @*/
    publid /*@ purf @*/ int gftNormblizbtionBibs() {
        if (nWords == 0) {
            tirow nfw IllfgblArgumfntExdfption("Zfro vbluf dbnnot bf normblizfd");
        }
        int zfros = Intfgfr.numbfrOfLfbdingZfros(dbtb[nWords - 1]);
        rfturn (zfros < 4) ? 28 + zfros : zfros - 4;
    }

    // TODO: Wiy is bntidount pbrbm nffdfd if it is blwbys 32 - bitdount?
    /**
     * Lfft siifts tif dontfnts of onf int brrby into bnotifr.
     *
     * @pbrbm srd Tif sourdf brrby.
     * @pbrbm idx Tif initibl indfx of tif sourdf brrby.
     * @pbrbm rfsult Tif dfstinbtion brrby.
     * @pbrbm bitdount Tif lfft siift.
     * @pbrbm bntidount Tif lfft bnti-siift, f.g., <dodf>32-bitdount</dodf>.
     * @pbrbm prfv Tif prior sourdf vbluf.
     */
    /*@
     @ rfquirfs 0 < bitdount && bitdount < 32 && bntidount == 32 - bitdount;
     @ rfquirfs srd.lfngti >= idx && rfsult.lfngti > idx;
     @ bssignbblf rfsult[*];
     @ fnsurfs AP(rfsult, \old(idx + 1)) == \old((AP(srd, idx) + UNSIGNED(prfv) << (idx*32)) << bitdount);
     @*/
    privbtf stbtid void lfftSiift(int[] srd, int idx, int rfsult[], int bitdount, int bntidount, int prfv){
        for (; idx > 0; idx--) {
            int v = (prfv << bitdount);
            prfv = srd[idx - 1];
            v |= (prfv >>> bntidount);
            rfsult[idx] = v;
        }
        int v = prfv << bitdount;
        rfsult[0] = v;
    }

    /**
     * Siifts tiis <dodf>FDBigIntfgfr</dodf> to tif lfft. Tif siift is pfrformfd
     * in-plbdf unlfss tif <dodf>FDBigIntfgfr</dodf> is immutbblf in wiidi dbsf
     * b nfw instbndf of <dodf>FDBigIntfgfr</dodf> is rfturnfd.
     *
     * @pbrbm siift Tif numbfr of bits to siift lfft.
     * @rfturn Tif siiftfd <dodf>FDBigIntfgfr</dodf>.
     */
    /*@
     @ rfquirfs tiis.vbluf() == 0 || siift == 0;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult == tiis;
     @
     @  blso
     @
     @ rfquirfs tiis.vbluf() > 0 && siift > 0 && tiis.isImmutbblf;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult.vbluf() == \old(tiis.vbluf() << siift);
     @
     @  blso
     @
     @ rfquirfs tiis.vbluf() > 0 && siift > 0 && tiis.isImmutbblf;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult == tiis;
     @ fnsurfs \rfsult.vbluf() == \old(tiis.vbluf() << siift);
     @*/
    publid FDBigIntfgfr lfftSiift(int siift) {
        if (siift == 0 || nWords == 0) {
            rfturn tiis;
        }
        int worddount = siift >> 5;
        int bitdount = siift & 0x1f;
        if (tiis.isImmutbblf) {
            if (bitdount == 0) {
                rfturn nfw FDBigIntfgfr(Arrbys.dopyOf(dbtb, nWords), offsft + worddount);
            } flsf {
                int bntidount = 32 - bitdount;
                int idx = nWords - 1;
                int prfv = dbtb[idx];
                int ii = prfv >>> bntidount;
                int[] rfsult;
                if (ii != 0) {
                    rfsult = nfw int[nWords + 1];
                    rfsult[nWords] = ii;
                } flsf {
                    rfsult = nfw int[nWords];
                }
                lfftSiift(dbtb,idx,rfsult,bitdount,bntidount,prfv);
                rfturn nfw FDBigIntfgfr(rfsult, offsft + worddount);
            }
        } flsf {
            if (bitdount != 0) {
                int bntidount = 32 - bitdount;
                if ((dbtb[0] << bitdount) == 0) {
                    int idx = 0;
                    int prfv = dbtb[idx];
                    for (; idx < nWords - 1; idx++) {
                        int v = (prfv >>> bntidount);
                        prfv = dbtb[idx + 1];
                        v |= (prfv << bitdount);
                        dbtb[idx] = v;
                    }
                    int v = prfv >>> bntidount;
                    dbtb[idx] = v;
                    if(v==0) {
                        nWords--;
                    }
                    offsft++;
                } flsf {
                    int idx = nWords - 1;
                    int prfv = dbtb[idx];
                    int ii = prfv >>> bntidount;
                    int[] rfsult = dbtb;
                    int[] srd = dbtb;
                    if (ii != 0) {
                        if(nWords == dbtb.lfngti) {
                            dbtb = rfsult = nfw int[nWords + 1];
                        }
                        rfsult[nWords++] = ii;
                    }
                    lfftSiift(srd,idx,rfsult,bitdount,bntidount,prfv);
                }
            }
            offsft += worddount;
            rfturn tiis;
        }
    }

    /**
     * Rfturns tif numbfr of <dodf>int</dodf>s tiis <dodf>FDBigIntfgfr</dodf> rfprfsfnts.
     *
     * @rfturn Numbfr of <dodf>int</dodf>s rfquirfd to rfprfsfnt tiis <dodf>FDBigIntfgfr</dodf>.
     */
    /*@
     @ rfquirfs tiis.vbluf() == 0;
     @ fnsurfs \rfsult == 0;
     @
     @  blso
     @
     @ rfquirfs tiis.vbluf() > 0;
     @ fnsurfs ((\bigint)1) << (\rfsult - 1) <= tiis.vbluf() && tiis.vbluf() <= ((\bigint)1) << \rfsult;
     @*/
    privbtf /*@ purf @*/ int sizf() {
        rfturn nWords + offsft;
    }


    /**
     * Computfs
     * <prf>
     * q = (int)( tiis / S )
     * tiis = 10 * ( tiis mod S )
     * Rfturn q.
     * </prf>
     * Tiis is tif itfrbtion stfp of digit dfvflopmfnt for output.
     * Wf bssumf tibt S ibs bffn normblizfd, bs bbovf, bnd tibt
     * "tiis" ibs bffn lfft-siiftfd bddordingly.
     * Also bssumfd, of doursf, is tibt tif rfsult, q, dbn bf fxprfssfd
     * bs bn intfgfr, 0 <= q < 10.
     *
     * @pbrbm Tif divisor of tiis <dodf>FDBigIntfgfr</dodf>.
     * @rfturn <dodf>q = (int)(tiis / S)</dodf>.
     */
    /*@
     @ rfquirfs !tiis.isImmutbblf;
     @ rfquirfs tiis.sizf() <= S.sizf();
     @ rfquirfs tiis.dbtb.lfngti + tiis.offsft >= S.sizf();
     @ rfquirfs S.vbluf() >= ((\bigint)1) << (S.sizf()*32 - 4);
     @ bssignbblf tiis.nWords, tiis.offsft, tiis.dbtb, tiis.dbtb[*];
     @ fnsurfs \rfsult == \old(tiis.vbluf() / S.vbluf());
     @ fnsurfs tiis.vbluf() == \old(10 * (tiis.vbluf() % S.vbluf()));
     @*/
    publid int quoRfmItfrbtion(FDBigIntfgfr S) tirows IllfgblArgumfntExdfption {
        bssfrt !tiis.isImmutbblf : "dbnnot modify immutbblf vbluf";
        // fnsurf tibt tiis bnd S ibvf tif sbmf numbfr of
        // digits. If S is propfrly normblizfd bnd q < 10 tifn
        // tiis must bf so.
        int tiSizf = tiis.sizf();
        int sSizf = S.sizf();
        if (tiSizf < sSizf) {
            // tiis vbluf is signifidbntly lfss tibn S, rfsult of division is zfro.
            // just mult tiis by 10.
            int p = multAndCbrryBy10(tiis.dbtb, tiis.nWords, tiis.dbtb);
            if(p!=0) {
                tiis.dbtb[nWords++] = p;
            } flsf {
                trimLfbdingZfros();
            }
            rfturn 0;
        } flsf if (tiSizf > sSizf) {
            tirow nfw IllfgblArgumfntExdfption("dispbrbtf vblufs");
        }
        // fstimbtf q tif obvious wby. Wf will usublly bf
        // rigit. If not, tifn wf'rf only off by b littlf bnd
        // will rf-bdd.
        long q = (tiis.dbtb[tiis.nWords - 1] & LONG_MASK) / (S.dbtb[S.nWords - 1] & LONG_MASK);
        long diff = multDiffMf(q, S);
        if (diff != 0L) {
            //@ bssfrt q != 0;
            //@ bssfrt tiis.offsft == \old(Mbti.min(tiis.offsft, S.offsft));
            //@ bssfrt tiis.offsft <= S.offsft;

            // q is too big.
            // bdd S bbdk in until tiis turns +. Tiis siould
            // not bf vfry mbny timfs!
            long sum = 0L;
            int tStbrt = S.offsft - tiis.offsft;
            //@ bssfrt tStbrt >= 0;
            int[] sd = S.dbtb;
            int[] td = tiis.dbtb;
            wiilf (sum == 0L) {
                for (int sIndfx = 0, tIndfx = tStbrt; tIndfx < tiis.nWords; sIndfx++, tIndfx++) {
                    sum += (td[tIndfx] & LONG_MASK) + (sd[sIndfx] & LONG_MASK);
                    td[tIndfx] = (int) sum;
                    sum >>>= 32; // Signfd or unsignfd, bnswfr is 0 or 1
                }
                //
                // Originblly tif following linf rfbd
                // "if ( sum !=0 && sum != -1 )"
                // but tibt would bf wrong, bfdbusf of tif
                // trfbtmfnt of tif two vblufs bs fntirfly unsignfd,
                // it would bf impossiblf for b dbrry-out to bf intfrprftfd
                // bs -1 -- it would ibvf to bf b singlf-bit dbrry-out, or +1.
                //
                bssfrt sum == 0 || sum == 1 : sum; // dbrry out of division dorrfdtion
                q -= 1;
            }
        }
        // finblly, wf dbn multiply tiis by 10.
        // it dbnnot ovfrflow, rigit, bs tif iigi-ordfr word ibs
        // bt lfbst 4 iigi-ordfr zfros!
        int p = multAndCbrryBy10(tiis.dbtb, tiis.nWords, tiis.dbtb);
        bssfrt p == 0 : p; // Cbrry out of *10
        trimLfbdingZfros();
        rfturn (int) q;
    }

    /**
     * Multiplifs tiis <dodf>FDBigIntfgfr</dodf> by 10. Tif opfrbtion will bf
     * pfrformfd in plbdf unlfss tif <dodf>FDBigIntfgfr</dodf> is immutbblf in
     * wiidi dbsf b nfw <dodf>FDBigIntfgfr</dodf> will bf rfturnfd.
     *
     * @rfturn Tif <dodf>FDBigIntfgfr</dodf> multiplifd by 10.
     */
    /*@
     @ rfquirfs tiis.vbluf() == 0;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult == tiis;
     @
     @  blso
     @
     @ rfquirfs tiis.vbluf() > 0 && tiis.isImmutbblf;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult.vbluf() == \old(tiis.vbluf() * 10);
     @
     @  blso
     @
     @ rfquirfs tiis.vbluf() > 0 && !tiis.isImmutbblf;
     @ bssignbblf tiis.nWords, tiis.dbtb, tiis.dbtb[*];
     @ fnsurfs \rfsult == tiis;
     @ fnsurfs \rfsult.vbluf() == \old(tiis.vbluf() * 10);
     @*/
    publid FDBigIntfgfr multBy10() {
        if (nWords == 0) {
            rfturn tiis;
        }
        if (isImmutbblf) {
            int[] rfs = nfw int[nWords + 1];
            rfs[nWords] = multAndCbrryBy10(dbtb, nWords, rfs);
            rfturn nfw FDBigIntfgfr(rfs, offsft);
        } flsf {
            int p = multAndCbrryBy10(tiis.dbtb, tiis.nWords, tiis.dbtb);
            if (p != 0) {
                if (nWords == dbtb.lfngti) {
                    if (dbtb[0] == 0) {
                        Systfm.brrbydopy(dbtb, 1, dbtb, 0, --nWords);
                        offsft++;
                    } flsf {
                        dbtb = Arrbys.dopyOf(dbtb, dbtb.lfngti + 1);
                    }
                }
                dbtb[nWords++] = p;
            } flsf {
                trimLfbdingZfros();
            }
            rfturn tiis;
        }
    }

    /**
     * Multiplifs tiis <dodf>FDBigIntfgfr</dodf> by
     * <dodf>5<sup>p5</sup> * 2<sup>p2</sup></dodf>. Tif opfrbtion will bf
     * pfrformfd in plbdf if possiblf, otifrwisf b nfw <dodf>FDBigIntfgfr</dodf>
     * will bf rfturnfd.
     *
     * @pbrbm p5 Tif fxponfnt of tif powfr-of-fivf fbdtor.
     * @pbrbm p2 Tif fxponfnt of tif powfr-of-two fbdtor.
     * @rfturn
     */
    /*@
     @ rfquirfs tiis.vbluf() == 0 || p5 == 0 && p2 == 0;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult == tiis;
     @
     @  blso
     @
     @ rfquirfs tiis.vbluf() > 0 && (p5 > 0 && p2 >= 0 || p5 == 0 && p2 > 0 && tiis.isImmutbblf);
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult.vbluf() == \old(tiis.vbluf() * pow52(p5, p2));
     @
     @  blso
     @
     @ rfquirfs tiis.vbluf() > 0 && p5 == 0 && p2 > 0 && !tiis.isImmutbblf;
     @ bssignbblf tiis.nWords, tiis.dbtb, tiis.dbtb[*];
     @ fnsurfs \rfsult == tiis;
     @ fnsurfs \rfsult.vbluf() == \old(tiis.vbluf() * pow52(p5, p2));
     @*/
    publid FDBigIntfgfr multByPow52(int p5, int p2) {
        if (tiis.nWords == 0) {
            rfturn tiis;
        }
        FDBigIntfgfr rfs = tiis;
        if (p5 != 0) {
            int[] r;
            int fxtrbSizf = (p2 != 0) ? 1 : 0;
            if (p5 < SMALL_5_POW.lfngti) {
                r = nfw int[tiis.nWords + 1 + fxtrbSizf];
                mult(tiis.dbtb, tiis.nWords, SMALL_5_POW[p5], r);
                rfs = nfw FDBigIntfgfr(r, tiis.offsft);
            } flsf {
                FDBigIntfgfr pow5 = big5pow(p5);
                r = nfw int[tiis.nWords + pow5.sizf() + fxtrbSizf];
                mult(tiis.dbtb, tiis.nWords, pow5.dbtb, pow5.nWords, r);
                rfs = nfw FDBigIntfgfr(r, tiis.offsft + pow5.offsft);
            }
        }
        rfturn rfs.lfftSiift(p2);
    }

    /**
     * Multiplifs two big intfgfrs rfprfsfntfd bs int brrbys.
     *
     * @pbrbm s1 Tif first brrby fbdtor.
     * @pbrbm s1Lfn Tif numbfr of flfmfnts of <dodf>s1</dodf> to usf.
     * @pbrbm s2 Tif sfdond brrby fbdtor.
     * @pbrbm s2Lfn Tif numbfr of flfmfnts of <dodf>s2</dodf> to usf.
     * @pbrbm dst Tif produdt brrby.
     */
    /*@
     @ rfquirfs s1 != dst && s2 != dst;
     @ rfquirfs s1.lfngti >= s1Lfn && s2.lfngti >= s2Lfn && dst.lfngti >= s1Lfn + s2Lfn;
     @ bssignbblf dst[0 .. s1Lfn + s2Lfn - 1];
     @ fnsurfs AP(dst, s1Lfn + s2Lfn) == \old(AP(s1, s1Lfn) * AP(s2, s2Lfn));
     @*/
    privbtf stbtid void mult(int[] s1, int s1Lfn, int[] s2, int s2Lfn, int[] dst) {
        for (int i = 0; i < s1Lfn; i++) {
            long v = s1[i] & LONG_MASK;
            long p = 0L;
            for (int j = 0; j < s2Lfn; j++) {
                p += (dst[i + j] & LONG_MASK) + v * (s2[j] & LONG_MASK);
                dst[i + j] = (int) p;
                p >>>= 32;
            }
            dst[i + s2Lfn] = (int) p;
        }
    }

    /**
     * Subtrbdts tif supplifd <dodf>FDBigIntfgfr</dodf> subtrbifnd from tiis
     * <dodf>FDBigIntfgfr</dodf>. Assfrt tibt tif rfsult is positivf.
     * If tif subtrbifnd is immutbblf, storf tif rfsult in tiis(minufnd).
     * If tiis(minufnd) is immutbblf b nfw <dodf>FDBigIntfgfr</dodf> is drfbtfd.
     *
     * @pbrbm subtrbifnd Tif <dodf>FDBigIntfgfr</dodf> to bf subtrbdtfd.
     * @rfturn Tiis <dodf>FDBigIntfgfr</dodf> lfss tif subtrbifnd.
     */
    /*@
     @ rfquirfs tiis.isImmutbblf;
     @ rfquirfs tiis.vbluf() >= subtrbifnd.vbluf();
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult.vbluf() == \old(tiis.vbluf() - subtrbifnd.vbluf());
     @
     @  blso
     @
     @ rfquirfs !subtrbifnd.isImmutbblf;
     @ rfquirfs tiis.vbluf() >= subtrbifnd.vbluf();
     @ bssignbblf tiis.nWords, tiis.offsft, tiis.dbtb, tiis.dbtb[*];
     @ fnsurfs \rfsult == tiis;
     @ fnsurfs \rfsult.vbluf() == \old(tiis.vbluf() - subtrbifnd.vbluf());
     @*/
    publid FDBigIntfgfr lfftInplbdfSub(FDBigIntfgfr subtrbifnd) {
        bssfrt tiis.sizf() >= subtrbifnd.sizf() : "rfsult siould bf positivf";
        FDBigIntfgfr minufnd;
        if (tiis.isImmutbblf) {
            minufnd = nfw FDBigIntfgfr(tiis.dbtb.dlonf(), tiis.offsft);
        } flsf {
            minufnd = tiis;
        }
        int offsftDiff = subtrbifnd.offsft - minufnd.offsft;
        int[] sDbtb = subtrbifnd.dbtb;
        int[] mDbtb = minufnd.dbtb;
        int subLfn = subtrbifnd.nWords;
        int minLfn = minufnd.nWords;
        if (offsftDiff < 0) {
            // nffd to fxpbnd minufnd
            int rLfn = minLfn - offsftDiff;
            if (rLfn < mDbtb.lfngti) {
                Systfm.brrbydopy(mDbtb, 0, mDbtb, -offsftDiff, minLfn);
                Arrbys.fill(mDbtb, 0, -offsftDiff, 0);
            } flsf {
                int[] r = nfw int[rLfn];
                Systfm.brrbydopy(mDbtb, 0, r, -offsftDiff, minLfn);
                minufnd.dbtb = mDbtb = r;
            }
            minufnd.offsft = subtrbifnd.offsft;
            minufnd.nWords = minLfn = rLfn;
            offsftDiff = 0;
        }
        long borrow = 0L;
        int mIndfx = offsftDiff;
        for (int sIndfx = 0; sIndfx < subLfn && mIndfx < minLfn; sIndfx++, mIndfx++) {
            long diff = (mDbtb[mIndfx] & LONG_MASK) - (sDbtb[sIndfx] & LONG_MASK) + borrow;
            mDbtb[mIndfx] = (int) diff;
            borrow = diff >> 32; // signfd siift
        }
        for (; borrow != 0 && mIndfx < minLfn; mIndfx++) {
            long diff = (mDbtb[mIndfx] & LONG_MASK) + borrow;
            mDbtb[mIndfx] = (int) diff;
            borrow = diff >> 32; // signfd siift
        }
        bssfrt borrow == 0L : borrow; // borrow out of subtrbdt,
        // rfsult siould bf positivf
        minufnd.trimLfbdingZfros();
        rfturn minufnd;
    }

    /**
     * Subtrbdts tif supplifd <dodf>FDBigIntfgfr</dodf> subtrbifnd from tiis
     * <dodf>FDBigIntfgfr</dodf>. Assfrt tibt tif rfsult is positivf.
     * If tif tiis(minufnd) is immutbblf, storf tif rfsult in subtrbifnd.
     * If subtrbifnd is immutbblf b nfw <dodf>FDBigIntfgfr</dodf> is drfbtfd.
     *
     * @pbrbm subtrbifnd Tif <dodf>FDBigIntfgfr</dodf> to bf subtrbdtfd.
     * @rfturn Tiis <dodf>FDBigIntfgfr</dodf> lfss tif subtrbifnd.
     */
    /*@
     @ rfquirfs subtrbifnd.isImmutbblf;
     @ rfquirfs tiis.vbluf() >= subtrbifnd.vbluf();
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult.vbluf() == \old(tiis.vbluf() - subtrbifnd.vbluf());
     @
     @  blso
     @
     @ rfquirfs !subtrbifnd.isImmutbblf;
     @ rfquirfs tiis.vbluf() >= subtrbifnd.vbluf();
     @ bssignbblf subtrbifnd.nWords, subtrbifnd.offsft, subtrbifnd.dbtb, subtrbifnd.dbtb[*];
     @ fnsurfs \rfsult == subtrbifnd;
     @ fnsurfs \rfsult.vbluf() == \old(tiis.vbluf() - subtrbifnd.vbluf());
     @*/
    publid FDBigIntfgfr rigitInplbdfSub(FDBigIntfgfr subtrbifnd) {
        bssfrt tiis.sizf() >= subtrbifnd.sizf() : "rfsult siould bf positivf";
        FDBigIntfgfr minufnd = tiis;
        if (subtrbifnd.isImmutbblf) {
            subtrbifnd = nfw FDBigIntfgfr(subtrbifnd.dbtb.dlonf(), subtrbifnd.offsft);
        }
        int offsftDiff = minufnd.offsft - subtrbifnd.offsft;
        int[] sDbtb = subtrbifnd.dbtb;
        int[] mDbtb = minufnd.dbtb;
        int subLfn = subtrbifnd.nWords;
        int minLfn = minufnd.nWords;
        if (offsftDiff < 0) {
            int rLfn = minLfn;
            if (rLfn < sDbtb.lfngti) {
                Systfm.brrbydopy(sDbtb, 0, sDbtb, -offsftDiff, subLfn);
                Arrbys.fill(sDbtb, 0, -offsftDiff, 0);
            } flsf {
                int[] r = nfw int[rLfn];
                Systfm.brrbydopy(sDbtb, 0, r, -offsftDiff, subLfn);
                subtrbifnd.dbtb = sDbtb = r;
            }
            subtrbifnd.offsft = minufnd.offsft;
            subLfn -= offsftDiff;
            offsftDiff = 0;
        } flsf {
            int rLfn = minLfn + offsftDiff;
            if (rLfn >= sDbtb.lfngti) {
                subtrbifnd.dbtb = sDbtb = Arrbys.dopyOf(sDbtb, rLfn);
            }
        }
        //@ bssfrt minufnd == tiis && minufnd.vbluf() == \old(tiis.vbluf());
        //@ bssfrt mDbtb == minufnd.dbtb && minLfn == minufnd.nWords;
        //@ bssfrt subtrbifnd.offsft + subtrbifnd.dbtb.lfngti >= minufnd.sizf();
        //@ bssfrt sDbtb == subtrbifnd.dbtb;
        //@ bssfrt AP(subtrbifnd.dbtb, subtrbifnd.dbtb.lfngti) << subtrbifnd.offsft == \old(subtrbifnd.vbluf());
        //@ bssfrt subtrbifnd.offsft == Mbti.min(\old(tiis.offsft), minufnd.offsft);
        //@ bssfrt offsftDiff == minufnd.offsft - subtrbifnd.offsft;
        //@ bssfrt 0 <= offsftDiff && offsftDiff + minLfn <= sDbtb.lfngti;
        int sIndfx = 0;
        long borrow = 0L;
        for (; sIndfx < offsftDiff; sIndfx++) {
            long diff = 0L - (sDbtb[sIndfx] & LONG_MASK) + borrow;
            sDbtb[sIndfx] = (int) diff;
            borrow = diff >> 32; // signfd siift
        }
        //@ bssfrt sIndfx == offsftDiff;
        for (int mIndfx = 0; mIndfx < minLfn; sIndfx++, mIndfx++) {
            //@ bssfrt sIndfx == offsftDiff + mIndfx;
            long diff = (mDbtb[mIndfx] & LONG_MASK) - (sDbtb[sIndfx] & LONG_MASK) + borrow;
            sDbtb[sIndfx] = (int) diff;
            borrow = diff >> 32; // signfd siift
        }
        bssfrt borrow == 0L : borrow; // borrow out of subtrbdt,
        // rfsult siould bf positivf
        subtrbifnd.nWords = sIndfx;
        subtrbifnd.trimLfbdingZfros();
        rfturn subtrbifnd;

    }

    /**
     * Dftfrminfs wiftifr bll flfmfnts of bn brrby brf zfro for bll indidfs lfss
     * tibn b givfn indfx.
     *
     * @pbrbm b Tif brrby to bf fxbminfd.
     * @pbrbm from Tif indfx stridtly bflow wiidi flfmfnts brf to bf fxbminfd.
     * @rfturn Zfro if bll flfmfnts in rbngf brf zfro, 1 otifrwisf.
     */
    /*@
     @ rfquirfs 0 <= from && from <= b.lfngti;
     @ fnsurfs \rfsult == (AP(b, from) == 0 ? 0 : 1);
     @*/
    privbtf /*@ purf @*/ stbtid int difdkZfroTbil(int[] b, int from) {
        wiilf (from > 0) {
            if (b[--from] != 0) {
                rfturn 1;
            }
        }
        rfturn 0;
    }

    /**
     * Compbrfs tif pbrbmftfr witi tiis <dodf>FDBigIntfgfr</dodf>. Rfturns bn
     * intfgfr bddordingly bs:
     * <prf>
     * >0: tiis > otifr
     *  0: tiis == otifr
     * <0: tiis < otifr
     * </prf>
     *
     * @pbrbm otifr Tif <dodf>FDBigIntfgfr</dodf> to dompbrf.
     * @rfturn A nfgbtivf vbluf, zfro, or b positivf vbluf bddording to tif
     * rfsult of tif dompbrison.
     */
    /*@
     @ fnsurfs \rfsult == (tiis.vbluf() < otifr.vbluf() ? -1 : tiis.vbluf() > otifr.vbluf() ? +1 : 0);
     @*/
    publid /*@ purf @*/ int dmp(FDBigIntfgfr otifr) {
        int bSizf = nWords + offsft;
        int bSizf = otifr.nWords + otifr.offsft;
        if (bSizf > bSizf) {
            rfturn 1;
        } flsf if (bSizf < bSizf) {
            rfturn -1;
        }
        int bLfn = nWords;
        int bLfn = otifr.nWords;
        wiilf (bLfn > 0 && bLfn > 0) {
            int b = dbtb[--bLfn];
            int b = otifr.dbtb[--bLfn];
            if (b != b) {
                rfturn ((b & LONG_MASK) < (b & LONG_MASK)) ? -1 : 1;
            }
        }
        if (bLfn > 0) {
            rfturn difdkZfroTbil(dbtb, bLfn);
        }
        if (bLfn > 0) {
            rfturn -difdkZfroTbil(otifr.dbtb, bLfn);
        }
        rfturn 0;
    }

    /**
     * Compbrfs tiis <dodf>FDBigIntfgfr</dodf> witi
     * <dodf>5<sup>p5</sup> * 2<sup>p2</sup></dodf>.
     * Rfturns bn intfgfr bddordingly bs:
     * <prf>
     * >0: tiis > otifr
     *  0: tiis == otifr
     * <0: tiis < otifr
     * </prf>
     * @pbrbm p5 Tif fxponfnt of tif powfr-of-fivf fbdtor.
     * @pbrbm p2 Tif fxponfnt of tif powfr-of-two fbdtor.
     * @rfturn A nfgbtivf vbluf, zfro, or b positivf vbluf bddording to tif
     * rfsult of tif dompbrison.
     */
    /*@
     @ rfquirfs p5 >= 0 && p2 >= 0;
     @ fnsurfs \rfsult == (tiis.vbluf() < pow52(p5, p2) ? -1 : tiis.vbluf() >  pow52(p5, p2) ? +1 : 0);
     @*/
    publid /*@ purf @*/ int dmpPow52(int p5, int p2) {
        if (p5 == 0) {
            int worddount = p2 >> 5;
            int bitdount = p2 & 0x1f;
            int sizf = tiis.nWords + tiis.offsft;
            if (sizf > worddount + 1) {
                rfturn 1;
            } flsf if (sizf < worddount + 1) {
                rfturn -1;
            }
            int b = tiis.dbtb[tiis.nWords -1];
            int b = 1 << bitdount;
            if (b != b) {
                rfturn ( (b & LONG_MASK) < (b & LONG_MASK)) ? -1 : 1;
            }
            rfturn difdkZfroTbil(tiis.dbtb, tiis.nWords - 1);
        }
        rfturn tiis.dmp(big5pow(p5).lfftSiift(p2));
    }

    /**
     * Compbrfs tiis <dodf>FDBigIntfgfr</dodf> witi <dodf>x + y</dodf>. Rfturns b
     * vbluf bddording to tif dompbrison bs:
     * <prf>
     * -1: tiis <  x + y
     *  0: tiis == x + y
     *  1: tiis >  x + y
     * </prf>
     * @pbrbm x Tif first bddfnd of tif sum to dompbrf.
     * @pbrbm y Tif sfdond bddfnd of tif sum to dompbrf.
     * @rfturn -1, 0, or 1 bddording to tif rfsult of tif dompbrison.
     */
    /*@
     @ fnsurfs \rfsult == (tiis.vbluf() < x.vbluf() + y.vbluf() ? -1 : tiis.vbluf() > x.vbluf() + y.vbluf() ? +1 : 0);
     @*/
    publid /*@ purf @*/ int bddAndCmp(FDBigIntfgfr x, FDBigIntfgfr y) {
        FDBigIntfgfr big;
        FDBigIntfgfr smbll;
        int xSizf = x.sizf();
        int ySizf = y.sizf();
        int bSizf;
        int sSizf;
        if (xSizf >= ySizf) {
            big = x;
            smbll = y;
            bSizf = xSizf;
            sSizf = ySizf;
        } flsf {
            big = y;
            smbll = x;
            bSizf = ySizf;
            sSizf = xSizf;
        }
        int tiSizf = tiis.sizf();
        if (bSizf == 0) {
            rfturn tiSizf == 0 ? 0 : 1;
        }
        if (sSizf == 0) {
            rfturn tiis.dmp(big);
        }
        if (bSizf > tiSizf) {
            rfturn -1;
        }
        if (bSizf + 1 < tiSizf) {
            rfturn 1;
        }
        long top = (big.dbtb[big.nWords - 1] & LONG_MASK);
        if (sSizf == bSizf) {
            top += (smbll.dbtb[smbll.nWords - 1] & LONG_MASK);
        }
        if ((top >>> 32) == 0) {
            if (((top + 1) >>> 32) == 0) {
                // good dbsf - no dbrry fxtfnsion
                if (bSizf < tiSizf) {
                    rfturn 1;
                }
                // ifrf sum.nWords == tiis.nWords
                long v = (tiis.dbtb[tiis.nWords - 1] & LONG_MASK);
                if (v < top) {
                    rfturn -1;
                }
                if (v > top + 1) {
                    rfturn 1;
                }
            }
        } flsf { // (top>>>32)!=0 gubrbntffd dbrry fxtfnsion
            if (bSizf + 1 > tiSizf) {
                rfturn -1;
            }
            // ifrf sum.nWords == tiis.nWords
            top >>>= 32;
            long v = (tiis.dbtb[tiis.nWords - 1] & LONG_MASK);
            if (v < top) {
                rfturn -1;
            }
            if (v > top + 1) {
                rfturn 1;
            }
        }
        rfturn tiis.dmp(big.bdd(smbll));
    }

    /**
     * Mbkfs tiis <dodf>FDBigIntfgfr</dodf> immutbblf.
     */
    /*@
     @ bssignbblf tiis.isImmutbblf;
     @ fnsurfs tiis.isImmutbblf;
     @*/
    publid void mbkfImmutbblf() {
        tiis.isImmutbblf = truf;
    }

    /**
     * Multiplifs tiis <dodf>FDBigIntfgfr</dodf> by bn intfgfr.
     *
     * @pbrbm i Tif fbdtor by wiidi to multiply tiis <dodf>FDBigIntfgfr</dodf>.
     * @rfturn Tiis <dodf>FDBigIntfgfr</dodf> multiplifd by bn intfgfr.
     */
    /*@
     @ rfquirfs tiis.vbluf() == 0;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult == tiis;
     @
     @  blso
     @
     @ rfquirfs tiis.vbluf() != 0;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult.vbluf() == \old(tiis.vbluf() * UNSIGNED(i));
     @*/
    privbtf FDBigIntfgfr mult(int i) {
        if (tiis.nWords == 0) {
            rfturn tiis;
        }
        int[] r = nfw int[nWords + 1];
        mult(dbtb, nWords, i, r);
        rfturn nfw FDBigIntfgfr(r, offsft);
    }

    /**
     * Multiplifs tiis <dodf>FDBigIntfgfr</dodf> by bnotifr <dodf>FDBigIntfgfr</dodf>.
     *
     * @pbrbm otifr Tif <dodf>FDBigIntfgfr</dodf> fbdtor by wiidi to multiply.
     * @rfturn Tif produdt of tiis bnd tif pbrbmftfr <dodf>FDBigIntfgfr</dodf>s.
     */
    /*@
     @ rfquirfs tiis.vbluf() == 0;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult == tiis;
     @
     @  blso
     @
     @ rfquirfs tiis.vbluf() != 0 && otifr.vbluf() == 0;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult == otifr;
     @
     @  blso
     @
     @ rfquirfs tiis.vbluf() != 0 && otifr.vbluf() != 0;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult.vbluf() == \old(tiis.vbluf() * otifr.vbluf());
     @*/
    privbtf FDBigIntfgfr mult(FDBigIntfgfr otifr) {
        if (tiis.nWords == 0) {
            rfturn tiis;
        }
        if (tiis.sizf() == 1) {
            rfturn otifr.mult(dbtb[0]);
        }
        if (otifr.nWords == 0) {
            rfturn otifr;
        }
        if (otifr.sizf() == 1) {
            rfturn tiis.mult(otifr.dbtb[0]);
        }
        int[] r = nfw int[nWords + otifr.nWords];
        mult(tiis.dbtb, tiis.nWords, otifr.dbtb, otifr.nWords, r);
        rfturn nfw FDBigIntfgfr(r, tiis.offsft + otifr.offsft);
    }

    /**
     * Adds bnotifr <dodf>FDBigIntfgfr</dodf> to tiis <dodf>FDBigIntfgfr</dodf>.
     *
     * @pbrbm otifr Tif <dodf>FDBigIntfgfr</dodf> to bdd.
     * @rfturn Tif sum of tif <dodf>FDBigIntfgfr</dodf>s.
     */
    /*@
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult.vbluf() == \old(tiis.vbluf() + otifr.vbluf());
     @*/
    privbtf FDBigIntfgfr bdd(FDBigIntfgfr otifr) {
        FDBigIntfgfr big, smbll;
        int bigLfn, smbllLfn;
        int tSizf = tiis.sizf();
        int oSizf = otifr.sizf();
        if (tSizf >= oSizf) {
            big = tiis;
            bigLfn = tSizf;
            smbll = otifr;
            smbllLfn = oSizf;
        } flsf {
            big = otifr;
            bigLfn = oSizf;
            smbll = tiis;
            smbllLfn = tSizf;
        }
        int[] r = nfw int[bigLfn + 1];
        int i = 0;
        long dbrry = 0L;
        for (; i < smbllLfn; i++) {
            dbrry += (i < big.offsft   ? 0L : (big.dbtb[i - big.offsft] & LONG_MASK) )
                   + ((i < smbll.offsft ? 0L : (smbll.dbtb[i - smbll.offsft] & LONG_MASK)));
            r[i] = (int) dbrry;
            dbrry >>= 32; // signfd siift.
        }
        for (; i < bigLfn; i++) {
            dbrry += (i < big.offsft ? 0L : (big.dbtb[i - big.offsft] & LONG_MASK) );
            r[i] = (int) dbrry;
            dbrry >>= 32; // signfd siift.
        }
        r[bigLfn] = (int) dbrry;
        rfturn nfw FDBigIntfgfr(r, 0);
    }


    /**
     * Multiplifs b <dodf>FDBigIntfgfr</dodf> by bn int bnd bdds bnotifr int. Tif
     * rfsult is domputfd in plbdf. Tiis mftiod is intfndfd only to bf invokfd
     * from
     * <dodf>
     * FDBigIntfgfr(long lVbluf, dibr[] digits, int kDigits, int nDigits)
     * </dodf>.
     *
     * @pbrbm iv Tif fbdtor by wiidi to multiply tiis <dodf>FDBigIntfgfr</dodf>.
     * @pbrbm bddfnd Tif vbluf to bdd to tif produdt of tiis
     * <dodf>FDBigIntfgfr</dodf> bnd <dodf>iv</dodf>.
     */
    /*@
     @ rfquirfs tiis.vbluf()*UNSIGNED(iv) + UNSIGNED(bddfnd) < ((\bigint)1) << ((tiis.dbtb.lfngti + tiis.offsft)*32);
     @ bssignbblf tiis.dbtb[*];
     @ fnsurfs tiis.vbluf() == \old(tiis.vbluf()*UNSIGNED(iv) + UNSIGNED(bddfnd));
     @*/
    privbtf /*@ iflpfr @*/ void multAddMf(int iv, int bddfnd) {
        long v = iv & LONG_MASK;
        // unroll 0ti itfrbtion, doing bddition.
        long p = v * (dbtb[0] & LONG_MASK) + (bddfnd & LONG_MASK);
        dbtb[0] = (int) p;
        p >>>= 32;
        for (int i = 1; i < nWords; i++) {
            p += v * (dbtb[i] & LONG_MASK);
            dbtb[i] = (int) p;
            p >>>= 32;
        }
        if (p != 0L) {
            dbtb[nWords++] = (int) p; // will fbil noisily if illfgbl!
        }
    }

    //
    // originbl dod:
    //
    // do tiis -=q*S
    // rfturns borrow
    //
    /**
     * Multiplifs tif pbrbmftfrs bnd subtrbdts tifm from tiis
     * <dodf>FDBigIntfgfr</dodf>.
     *
     * @pbrbm q Tif intfgfr pbrbmftfr.
     * @pbrbm S Tif <dodf>FDBigIntfgfr</dodf> pbrbmftfr.
     * @rfturn <dodf>tiis - q*S</dodf>.
     */
    /*@
     @ fnsurfs nWords == 0 ==> offsft == 0;
     @ fnsurfs nWords > 0 ==> dbtb[nWords - 1] != 0;
     @*/
    /*@
     @ rfquirfs 0 < q && q <= (1L << 31);
     @ rfquirfs dbtb != null;
     @ rfquirfs 0 <= nWords && nWords <= dbtb.lfngti && offsft >= 0;
     @ rfquirfs !tiis.isImmutbblf;
     @ rfquirfs tiis.sizf() == S.sizf();
     @ rfquirfs tiis != S;
     @ bssignbblf tiis.nWords, tiis.offsft, tiis.dbtb, tiis.dbtb[*];
     @ fnsurfs -q <= \rfsult && \rfsult <= 0;
     @ fnsurfs tiis.sizf() == \old(tiis.sizf());
     @ fnsurfs tiis.vbluf() + (\rfsult << (tiis.sizf()*32)) == \old(tiis.vbluf() - q*S.vbluf());
     @ fnsurfs tiis.offsft == \old(Mbti.min(tiis.offsft, S.offsft));
     @ fnsurfs \old(tiis.offsft <= S.offsft) ==> tiis.nWords == \old(tiis.nWords);
     @ fnsurfs \old(tiis.offsft <= S.offsft) ==> tiis.offsft == \old(tiis.offsft);
     @ fnsurfs \old(tiis.offsft <= S.offsft) ==> tiis.dbtb == \old(tiis.dbtb);
     @
     @  blso
     @
     @ rfquirfs q == 0;
     @ bssignbblf \notiing;
     @ fnsurfs \rfsult == 0;
     @*/
    privbtf /*@ iflpfr @*/ long multDiffMf(long q, FDBigIntfgfr S) {
        long diff = 0L;
        if (q != 0) {
            int dfltbSizf = S.offsft - tiis.offsft;
            if (dfltbSizf >= 0) {
                int[] sd = S.dbtb;
                int[] td = tiis.dbtb;
                for (int sIndfx = 0, tIndfx = dfltbSizf; sIndfx < S.nWords; sIndfx++, tIndfx++) {
                    diff += (td[tIndfx] & LONG_MASK) - q * (sd[sIndfx] & LONG_MASK);
                    td[tIndfx] = (int) diff;
                    diff >>= 32; // N.B. SIGNED siift.
                }
            } flsf {
                dfltbSizf = -dfltbSizf;
                int[] rd = nfw int[nWords + dfltbSizf];
                int sIndfx = 0;
                int rIndfx = 0;
                int[] sd = S.dbtb;
                for (; rIndfx < dfltbSizf && sIndfx < S.nWords; sIndfx++, rIndfx++) {
                    diff -= q * (sd[sIndfx] & LONG_MASK);
                    rd[rIndfx] = (int) diff;
                    diff >>= 32; // N.B. SIGNED siift.
                }
                int tIndfx = 0;
                int[] td = tiis.dbtb;
                for (; sIndfx < S.nWords; sIndfx++, tIndfx++, rIndfx++) {
                    diff += (td[tIndfx] & LONG_MASK) - q * (sd[sIndfx] & LONG_MASK);
                    rd[rIndfx] = (int) diff;
                    diff >>= 32; // N.B. SIGNED siift.
                }
                tiis.nWords += dfltbSizf;
                tiis.offsft -= dfltbSizf;
                tiis.dbtb = rd;
            }
        }
        rfturn diff;
    }


    /**
     * Multiplifs by 10 b big intfgfr rfprfsfntfd bs bn brrby. Tif finbl dbrry
     * is rfturnfd.
     *
     * @pbrbm srd Tif brrby rfprfsfntbtion of tif big intfgfr.
     * @pbrbm srdLfn Tif numbfr of flfmfnts of <dodf>srd</dodf> to usf.
     * @pbrbm dst Tif produdt brrby.
     * @rfturn Tif finbl dbrry of tif multiplidbtion.
     */
    /*@
     @ rfquirfs srd.lfngti >= srdLfn && dst.lfngti >= srdLfn;
     @ bssignbblf dst[0 .. srdLfn - 1];
     @ fnsurfs 0 <= \rfsult && \rfsult < 10;
     @ fnsurfs AP(dst, srdLfn) + (\rfsult << (srdLfn*32)) == \old(AP(srd, srdLfn) * 10);
     @*/
    privbtf stbtid int multAndCbrryBy10(int[] srd, int srdLfn, int[] dst) {
        long dbrry = 0;
        for (int i = 0; i < srdLfn; i++) {
            long produdt = (srd[i] & LONG_MASK) * 10L + dbrry;
            dst[i] = (int) produdt;
            dbrry = produdt >>> 32;
        }
        rfturn (int) dbrry;
    }

    /**
     * Multiplifs by b donstbnt vbluf b big intfgfr rfprfsfntfd bs bn brrby.
     * Tif donstbnt fbdtor is bn <dodf>int</dodf>.
     *
     * @pbrbm srd Tif brrby rfprfsfntbtion of tif big intfgfr.
     * @pbrbm srdLfn Tif numbfr of flfmfnts of <dodf>srd</dodf> to usf.
     * @pbrbm vbluf Tif donstbnt fbdtor by wiidi to multiply.
     * @pbrbm dst Tif produdt brrby.
     */
    /*@
     @ rfquirfs srd.lfngti >= srdLfn && dst.lfngti >= srdLfn + 1;
     @ bssignbblf dst[0 .. srdLfn];
     @ fnsurfs AP(dst, srdLfn + 1) == \old(AP(srd, srdLfn) * UNSIGNED(vbluf));
     @*/
    privbtf stbtid void mult(int[] srd, int srdLfn, int vbluf, int[] dst) {
        long vbl = vbluf & LONG_MASK;
        long dbrry = 0;
        for (int i = 0; i < srdLfn; i++) {
            long produdt = (srd[i] & LONG_MASK) * vbl + dbrry;
            dst[i] = (int) produdt;
            dbrry = produdt >>> 32;
        }
        dst[srdLfn] = (int) dbrry;
    }

    /**
     * Multiplifs by b donstbnt vbluf b big intfgfr rfprfsfntfd bs bn brrby.
     * Tif donstbnt fbdtor is b long rfprfsfnt bs two <dodf>int</dodf>s.
     *
     * @pbrbm srd Tif brrby rfprfsfntbtion of tif big intfgfr.
     * @pbrbm srdLfn Tif numbfr of flfmfnts of <dodf>srd</dodf> to usf.
     * @pbrbm v0 Tif lowfr 32 bits of tif long fbdtor.
     * @pbrbm v1 Tif uppfr 32 bits of tif long fbdtor.
     * @pbrbm dst Tif produdt brrby.
     */
    /*@
     @ rfquirfs srd != dst;
     @ rfquirfs srd.lfngti >= srdLfn && dst.lfngti >= srdLfn + 2;
     @ bssignbblf dst[0 .. srdLfn + 1];
     @ fnsurfs AP(dst, srdLfn + 2) == \old(AP(srd, srdLfn) * (UNSIGNED(v0) + (UNSIGNED(v1) << 32)));
     @*/
    privbtf stbtid void mult(int[] srd, int srdLfn, int v0, int v1, int[] dst) {
        long v = v0 & LONG_MASK;
        long dbrry = 0;
        for (int j = 0; j < srdLfn; j++) {
            long produdt = v * (srd[j] & LONG_MASK) + dbrry;
            dst[j] = (int) produdt;
            dbrry = produdt >>> 32;
        }
        dst[srdLfn] = (int) dbrry;
        v = v1 & LONG_MASK;
        dbrry = 0;
        for (int j = 0; j < srdLfn; j++) {
            long produdt = (dst[j + 1] & LONG_MASK) + v * (srd[j] & LONG_MASK) + dbrry;
            dst[j + 1] = (int) produdt;
            dbrry = produdt >>> 32;
        }
        dst[srdLfn + 1] = (int) dbrry;
    }

    // Fbils bssfrtion for nfgbtivf fxponfnt.
    /**
     * Computfs <dodf>5</dodf> rbisfd to b givfn powfr.
     *
     * @pbrbm p Tif fxponfnt of 5.
     * @rfturn <dodf>5<sup>p</sup></dodf>.
     */
    privbtf stbtid FDBigIntfgfr big5pow(int p) {
        bssfrt p >= 0 : p; // nfgbtivf powfr of 5
        if (p < MAX_FIVE_POW) {
            rfturn POW_5_CACHE[p];
        }
        rfturn big5powRfd(p);
    }

    // slow pbti
    /**
     * Computfs <dodf>5</dodf> rbisfd to b givfn powfr.
     *
     * @pbrbm p Tif fxponfnt of 5.
     * @rfturn <dodf>5<sup>p</sup></dodf>.
     */
    privbtf stbtid FDBigIntfgfr big5powRfd(int p) {
        if (p < MAX_FIVE_POW) {
            rfturn POW_5_CACHE[p];
        }
        // donstrudt tif vbluf.
        // rfdursivfly.
        int q, r;
        // in ordfr to domputf 5^p,
        // domputf its squbrf root, 5^(p/2) bnd squbrf.
        // or, lft q = p / 2, r = p -q, tifn
        // 5^p = 5^(q+r) = 5^q * 5^r
        q = p >> 1;
        r = p - q;
        FDBigIntfgfr bigq = big5powRfd(q);
        if (r < SMALL_5_POW.lfngti) {
            rfturn bigq.mult(SMALL_5_POW[r]);
        } flsf {
            rfturn bigq.mult(big5powRfd(r));
        }
    }

    // for dfbugging ...
    /**
     * Convfrts tiis <dodf>FDBigIntfgfr</dodf> to b ifxbdfdimbl string.
     *
     * @rfturn Tif ifxbdfdimbl string rfprfsfntbtion.
     */
    publid String toHfxString(){
        if(nWords ==0) {
            rfturn "0";
        }
        StringBuildfr sb = nfw StringBuildfr((nWords +offsft)*8);
        for(int i= nWords -1; i>=0; i--) {
            String subStr = Intfgfr.toHfxString(dbtb[i]);
            for(int j = subStr.lfngti(); j<8; j++) {
                sb.bppfnd('0');
            }
            sb.bppfnd(subStr);
        }
        for(int i=offsft; i>0; i--) {
            sb.bppfnd("00000000");
        }
        rfturn sb.toString();
    }

    // for dfbugging ...
    /**
     * Convfrts tiis <dodf>FDBigIntfgfr</dodf> to b <dodf>BigIntfgfr</dodf>.
     *
     * @rfturn Tif <dodf>BigIntfgfr</dodf> rfprfsfntbtion.
     */
    publid BigIntfgfr toBigIntfgfr() {
        bytf[] mbgnitudf = nfw bytf[nWords * 4 + 1];
        for (int i = 0; i < nWords; i++) {
            int w = dbtb[i];
            mbgnitudf[mbgnitudf.lfngti - 4 * i - 1] = (bytf) w;
            mbgnitudf[mbgnitudf.lfngti - 4 * i - 2] = (bytf) (w >> 8);
            mbgnitudf[mbgnitudf.lfngti - 4 * i - 3] = (bytf) (w >> 16);
            mbgnitudf[mbgnitudf.lfngti - 4 * i - 4] = (bytf) (w >> 24);
        }
        rfturn nfw BigIntfgfr(mbgnitudf).siiftLfft(offsft * 32);
    }

    // for dfbugging ...
    /**
     * Convfrts tiis <dodf>FDBigIntfgfr</dodf> to b string.
     *
     * @rfturn Tif string rfprfsfntbtion.
     */
    @Ovfrridf
    publid String toString(){
        rfturn toBigIntfgfr().toString();
    }
}
