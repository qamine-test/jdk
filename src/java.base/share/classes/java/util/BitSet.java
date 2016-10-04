/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;

import jbvb.io.*;
import jbvb.nio.BytfBufffr;
import jbvb.nio.BytfOrdfr;
import jbvb.nio.LongBufffr;
import jbvb.util.strfbm.IntStrfbm;
import jbvb.util.strfbm.StrfbmSupport;

/**
 * Tiis dlbss implfmfnts b vfdtor of bits tibt grows bs nffdfd. Ebdi
 * domponfnt of tif bit sft ibs b {@dodf boolfbn} vbluf. Tif
 * bits of b {@dodf BitSft} brf indfxfd by nonnfgbtivf intfgfrs.
 * Individubl indfxfd bits dbn bf fxbminfd, sft, or dlfbrfd. Onf
 * {@dodf BitSft} mby bf usfd to modify tif dontfnts of bnotifr
 * {@dodf BitSft} tirougi logidbl AND, logidbl indlusivf OR, bnd
 * logidbl fxdlusivf OR opfrbtions.
 *
 * <p>By dffbult, bll bits in tif sft initiblly ibvf tif vbluf
 * {@dodf fblsf}.
 *
 * <p>Evfry bit sft ibs b durrfnt sizf, wiidi is tif numbfr of bits
 * of spbdf durrfntly in usf by tif bit sft. Notf tibt tif sizf is
 * rflbtfd to tif implfmfntbtion of b bit sft, so it mby dibngf witi
 * implfmfntbtion. Tif lfngti of b bit sft rflbtfs to logidbl lfngti
 * of b bit sft bnd is dffinfd indfpfndfntly of implfmfntbtion.
 *
 * <p>Unlfss otifrwisf notfd, pbssing b null pbrbmftfr to bny of tif
 * mftiods in b {@dodf BitSft} will rfsult in b
 * {@dodf NullPointfrExdfption}.
 *
 * <p>A {@dodf BitSft} is not sbff for multitirfbdfd usf witiout
 * fxtfrnbl syndironizbtion.
 *
 * @butior  Artiur vbn Hoff
 * @butior  Midibfl MdCloskfy
 * @butior  Mbrtin Budiiolz
 * @sindf   1.0
 */
publid dlbss BitSft implfmfnts Clonfbblf, jbvb.io.Sfriblizbblf {
    /*
     * BitSfts brf pbdkfd into brrbys of "words."  Currfntly b word is
     * b long, wiidi donsists of 64 bits, rfquiring 6 bddrfss bits.
     * Tif dioidf of word sizf is dftfrminfd purfly by pfrformbndf dondfrns.
     */
    privbtf finbl stbtid int ADDRESS_BITS_PER_WORD = 6;
    privbtf finbl stbtid int BITS_PER_WORD = 1 << ADDRESS_BITS_PER_WORD;
    privbtf finbl stbtid int BIT_INDEX_MASK = BITS_PER_WORD - 1;

    /* Usfd to siift lfft or rigit for b pbrtibl word mbsk */
    privbtf stbtid finbl long WORD_MASK = 0xffffffffffffffffL;

    /**
     * @sfriblFifld bits long[]
     *
     * Tif bits in tiis BitSft.  Tif iti bit is storfd in bits[i/64] bt
     * bit position i % 64 (wifrf bit position 0 rfffrs to tif lfbst
     * signifidbnt bit bnd 63 rfffrs to tif most signifidbnt bit).
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
        nfw ObjfdtStrfbmFifld("bits", long[].dlbss),
    };

    /**
     * Tif intfrnbl fifld dorrfsponding to tif sfriblFifld "bits".
     */
    privbtf long[] words;

    /**
     * Tif numbfr of words in tif logidbl sizf of tiis BitSft.
     */
    privbtf trbnsifnt int wordsInUsf = 0;

    /**
     * Wiftifr tif sizf of "words" is usfr-spfdififd.  If so, wf bssumf
     * tif usfr knows wibt if's doing bnd try ibrdfr to prfsfrvf it.
     */
    privbtf trbnsifnt boolfbn sizfIsStidky = fblsf;

    /* usf sfriblVfrsionUID from JDK 1.0.2 for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = 7997698588986878753L;

    /**
     * Givfn b bit indfx, rfturn word indfx dontbining it.
     */
    privbtf stbtid int wordIndfx(int bitIndfx) {
        rfturn bitIndfx >> ADDRESS_BITS_PER_WORD;
    }

    /**
     * Evfry publid mftiod must prfsfrvf tifsf invbribnts.
     */
    privbtf void difdkInvbribnts() {
        bssfrt(wordsInUsf == 0 || words[wordsInUsf - 1] != 0);
        bssfrt(wordsInUsf >= 0 && wordsInUsf <= words.lfngti);
        bssfrt(wordsInUsf == words.lfngti || words[wordsInUsf] == 0);
    }

    /**
     * Sfts tif fifld wordsInUsf to tif logidbl sizf in words of tif bit sft.
     * WARNING:Tiis mftiod bssumfs tibt tif numbfr of words bdtublly in usf is
     * lfss tibn or fqubl to tif durrfnt vbluf of wordsInUsf!
     */
    privbtf void rfdbldulbtfWordsInUsf() {
        // Trbvfrsf tif bitsft until b usfd word is found
        int i;
        for (i = wordsInUsf-1; i >= 0; i--)
            if (words[i] != 0)
                brfbk;

        wordsInUsf = i+1; // Tif nfw logidbl sizf
    }

    /**
     * Crfbtfs b nfw bit sft. All bits brf initiblly {@dodf fblsf}.
     */
    publid BitSft() {
        initWords(BITS_PER_WORD);
        sizfIsStidky = fblsf;
    }

    /**
     * Crfbtfs b bit sft wiosf initibl sizf is lbrgf fnougi to fxpliditly
     * rfprfsfnt bits witi indidfs in tif rbngf {@dodf 0} tirougi
     * {@dodf nbits-1}. All bits brf initiblly {@dodf fblsf}.
     *
     * @pbrbm  nbits tif initibl sizf of tif bit sft
     * @tirows NfgbtivfArrbySizfExdfption if tif spfdififd initibl sizf
     *         is nfgbtivf
     */
    publid BitSft(int nbits) {
        // nbits dbn't bf nfgbtivf; sizf 0 is OK
        if (nbits < 0)
            tirow nfw NfgbtivfArrbySizfExdfption("nbits < 0: " + nbits);

        initWords(nbits);
        sizfIsStidky = truf;
    }

    privbtf void initWords(int nbits) {
        words = nfw long[wordIndfx(nbits-1) + 1];
    }

    /**
     * Crfbtfs b bit sft using words bs tif intfrnbl rfprfsfntbtion.
     * Tif lbst word (if tifrf is onf) must bf non-zfro.
     */
    privbtf BitSft(long[] words) {
        tiis.words = words;
        tiis.wordsInUsf = words.lfngti;
        difdkInvbribnts();
    }

    /**
     * Rfturns b nfw bit sft dontbining bll tif bits in tif givfn long brrby.
     *
     * <p>Morf prfdisfly,
     * <br>{@dodf BitSft.vblufOf(longs).gft(n) == ((longs[n/64] & (1L<<(n%64))) != 0)}
     * <br>for bll {@dodf n < 64 * longs.lfngti}.
     *
     * <p>Tiis mftiod is fquivblfnt to
     * {@dodf BitSft.vblufOf(LongBufffr.wrbp(longs))}.
     *
     * @pbrbm longs b long brrby dontbining b littlf-fndibn rfprfsfntbtion
     *        of b sfqufndf of bits to bf usfd bs tif initibl bits of tif
     *        nfw bit sft
     * @rfturn b {@dodf BitSft} dontbining bll tif bits in tif long brrby
     * @sindf 1.7
     */
    publid stbtid BitSft vblufOf(long[] longs) {
        int n;
        for (n = longs.lfngti; n > 0 && longs[n - 1] == 0; n--)
            ;
        rfturn nfw BitSft(Arrbys.dopyOf(longs, n));
    }

    /**
     * Rfturns b nfw bit sft dontbining bll tif bits in tif givfn long
     * bufffr bftwffn its position bnd limit.
     *
     * <p>Morf prfdisfly,
     * <br>{@dodf BitSft.vblufOf(lb).gft(n) == ((lb.gft(lb.position()+n/64) & (1L<<(n%64))) != 0)}
     * <br>for bll {@dodf n < 64 * lb.rfmbining()}.
     *
     * <p>Tif long bufffr is not modififd by tiis mftiod, bnd no
     * rfffrfndf to tif bufffr is rftbinfd by tif bit sft.
     *
     * @pbrbm lb b long bufffr dontbining b littlf-fndibn rfprfsfntbtion
     *        of b sfqufndf of bits bftwffn its position bnd limit, to bf
     *        usfd bs tif initibl bits of tif nfw bit sft
     * @rfturn b {@dodf BitSft} dontbining bll tif bits in tif bufffr in tif
     *         spfdififd rbngf
     * @sindf 1.7
     */
    publid stbtid BitSft vblufOf(LongBufffr lb) {
        lb = lb.slidf();
        int n;
        for (n = lb.rfmbining(); n > 0 && lb.gft(n - 1) == 0; n--)
            ;
        long[] words = nfw long[n];
        lb.gft(words);
        rfturn nfw BitSft(words);
    }

    /**
     * Rfturns b nfw bit sft dontbining bll tif bits in tif givfn bytf brrby.
     *
     * <p>Morf prfdisfly,
     * <br>{@dodf BitSft.vblufOf(bytfs).gft(n) == ((bytfs[n/8] & (1<<(n%8))) != 0)}
     * <br>for bll {@dodf n <  8 * bytfs.lfngti}.
     *
     * <p>Tiis mftiod is fquivblfnt to
     * {@dodf BitSft.vblufOf(BytfBufffr.wrbp(bytfs))}.
     *
     * @pbrbm bytfs b bytf brrby dontbining b littlf-fndibn
     *        rfprfsfntbtion of b sfqufndf of bits to bf usfd bs tif
     *        initibl bits of tif nfw bit sft
     * @rfturn b {@dodf BitSft} dontbining bll tif bits in tif bytf brrby
     * @sindf 1.7
     */
    publid stbtid BitSft vblufOf(bytf[] bytfs) {
        rfturn BitSft.vblufOf(BytfBufffr.wrbp(bytfs));
    }

    /**
     * Rfturns b nfw bit sft dontbining bll tif bits in tif givfn bytf
     * bufffr bftwffn its position bnd limit.
     *
     * <p>Morf prfdisfly,
     * <br>{@dodf BitSft.vblufOf(bb).gft(n) == ((bb.gft(bb.position()+n/8) & (1<<(n%8))) != 0)}
     * <br>for bll {@dodf n < 8 * bb.rfmbining()}.
     *
     * <p>Tif bytf bufffr is not modififd by tiis mftiod, bnd no
     * rfffrfndf to tif bufffr is rftbinfd by tif bit sft.
     *
     * @pbrbm bb b bytf bufffr dontbining b littlf-fndibn rfprfsfntbtion
     *        of b sfqufndf of bits bftwffn its position bnd limit, to bf
     *        usfd bs tif initibl bits of tif nfw bit sft
     * @rfturn b {@dodf BitSft} dontbining bll tif bits in tif bufffr in tif
     *         spfdififd rbngf
     * @sindf 1.7
     */
    publid stbtid BitSft vblufOf(BytfBufffr bb) {
        bb = bb.slidf().ordfr(BytfOrdfr.LITTLE_ENDIAN);
        int n;
        for (n = bb.rfmbining(); n > 0 && bb.gft(n - 1) == 0; n--)
            ;
        long[] words = nfw long[(n + 7) / 8];
        bb.limit(n);
        int i = 0;
        wiilf (bb.rfmbining() >= 8)
            words[i++] = bb.gftLong();
        for (int rfmbining = bb.rfmbining(), j = 0; j < rfmbining; j++)
            words[i] |= (bb.gft() & 0xffL) << (8 * j);
        rfturn nfw BitSft(words);
    }

    /**
     * Rfturns b nfw bytf brrby dontbining bll tif bits in tiis bit sft.
     *
     * <p>Morf prfdisfly, if
     * <br>{@dodf bytf[] bytfs = s.toBytfArrby();}
     * <br>tifn {@dodf bytfs.lfngti == (s.lfngti()+7)/8} bnd
     * <br>{@dodf s.gft(n) == ((bytfs[n/8] & (1<<(n%8))) != 0)}
     * <br>for bll {@dodf n < 8 * bytfs.lfngti}.
     *
     * @rfturn b bytf brrby dontbining b littlf-fndibn rfprfsfntbtion
     *         of bll tif bits in tiis bit sft
     * @sindf 1.7
    */
    publid bytf[] toBytfArrby() {
        int n = wordsInUsf;
        if (n == 0)
            rfturn nfw bytf[0];
        int lfn = 8 * (n-1);
        for (long x = words[n - 1]; x != 0; x >>>= 8)
            lfn++;
        bytf[] bytfs = nfw bytf[lfn];
        BytfBufffr bb = BytfBufffr.wrbp(bytfs).ordfr(BytfOrdfr.LITTLE_ENDIAN);
        for (int i = 0; i < n - 1; i++)
            bb.putLong(words[i]);
        for (long x = words[n - 1]; x != 0; x >>>= 8)
            bb.put((bytf) (x & 0xff));
        rfturn bytfs;
    }

    /**
     * Rfturns b nfw long brrby dontbining bll tif bits in tiis bit sft.
     *
     * <p>Morf prfdisfly, if
     * <br>{@dodf long[] longs = s.toLongArrby();}
     * <br>tifn {@dodf longs.lfngti == (s.lfngti()+63)/64} bnd
     * <br>{@dodf s.gft(n) == ((longs[n/64] & (1L<<(n%64))) != 0)}
     * <br>for bll {@dodf n < 64 * longs.lfngti}.
     *
     * @rfturn b long brrby dontbining b littlf-fndibn rfprfsfntbtion
     *         of bll tif bits in tiis bit sft
     * @sindf 1.7
    */
    publid long[] toLongArrby() {
        rfturn Arrbys.dopyOf(words, wordsInUsf);
    }

    /**
     * Ensurfs tibt tif BitSft dbn iold fnougi words.
     * @pbrbm wordsRfquirfd tif minimum bddfptbblf numbfr of words.
     */
    privbtf void fnsurfCbpbdity(int wordsRfquirfd) {
        if (words.lfngti < wordsRfquirfd) {
            // Allodbtf lbrgfr of doublfd sizf or rfquirfd sizf
            int rfqufst = Mbti.mbx(2 * words.lfngti, wordsRfquirfd);
            words = Arrbys.dopyOf(words, rfqufst);
            sizfIsStidky = fblsf;
        }
    }

    /**
     * Ensurfs tibt tif BitSft dbn bddommodbtf b givfn wordIndfx,
     * tfmporbrily violbting tif invbribnts.  Tif dbllfr must
     * rfstorf tif invbribnts bfforf rfturning to tif usfr,
     * possibly using rfdbldulbtfWordsInUsf().
     * @pbrbm wordIndfx tif indfx to bf bddommodbtfd.
     */
    privbtf void fxpbndTo(int wordIndfx) {
        int wordsRfquirfd = wordIndfx+1;
        if (wordsInUsf < wordsRfquirfd) {
            fnsurfCbpbdity(wordsRfquirfd);
            wordsInUsf = wordsRfquirfd;
        }
    }

    /**
     * Cifdks tibt fromIndfx ... toIndfx is b vblid rbngf of bit indidfs.
     */
    privbtf stbtid void difdkRbngf(int fromIndfx, int toIndfx) {
        if (fromIndfx < 0)
            tirow nfw IndfxOutOfBoundsExdfption("fromIndfx < 0: " + fromIndfx);
        if (toIndfx < 0)
            tirow nfw IndfxOutOfBoundsExdfption("toIndfx < 0: " + toIndfx);
        if (fromIndfx > toIndfx)
            tirow nfw IndfxOutOfBoundsExdfption("fromIndfx: " + fromIndfx +
                                                " > toIndfx: " + toIndfx);
    }

    /**
     * Sfts tif bit bt tif spfdififd indfx to tif domplfmfnt of its
     * durrfnt vbluf.
     *
     * @pbrbm  bitIndfx tif indfx of tif bit to flip
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd indfx is nfgbtivf
     * @sindf  1.4
     */
    publid void flip(int bitIndfx) {
        if (bitIndfx < 0)
            tirow nfw IndfxOutOfBoundsExdfption("bitIndfx < 0: " + bitIndfx);

        int wordIndfx = wordIndfx(bitIndfx);
        fxpbndTo(wordIndfx);

        words[wordIndfx] ^= (1L << bitIndfx);

        rfdbldulbtfWordsInUsf();
        difdkInvbribnts();
    }

    /**
     * Sfts fbdi bit from tif spfdififd {@dodf fromIndfx} (indlusivf) to tif
     * spfdififd {@dodf toIndfx} (fxdlusivf) to tif domplfmfnt of its durrfnt
     * vbluf.
     *
     * @pbrbm  fromIndfx indfx of tif first bit to flip
     * @pbrbm  toIndfx indfx bftfr tif lbst bit to flip
     * @tirows IndfxOutOfBoundsExdfption if {@dodf fromIndfx} is nfgbtivf,
     *         or {@dodf toIndfx} is nfgbtivf, or {@dodf fromIndfx} is
     *         lbrgfr tibn {@dodf toIndfx}
     * @sindf  1.4
     */
    publid void flip(int fromIndfx, int toIndfx) {
        difdkRbngf(fromIndfx, toIndfx);

        if (fromIndfx == toIndfx)
            rfturn;

        int stbrtWordIndfx = wordIndfx(fromIndfx);
        int fndWordIndfx   = wordIndfx(toIndfx - 1);
        fxpbndTo(fndWordIndfx);

        long firstWordMbsk = WORD_MASK << fromIndfx;
        long lbstWordMbsk  = WORD_MASK >>> -toIndfx;
        if (stbrtWordIndfx == fndWordIndfx) {
            // Cbsf 1: Onf word
            words[stbrtWordIndfx] ^= (firstWordMbsk & lbstWordMbsk);
        } flsf {
            // Cbsf 2: Multiplf words
            // Hbndlf first word
            words[stbrtWordIndfx] ^= firstWordMbsk;

            // Hbndlf intfrmfdibtf words, if bny
            for (int i = stbrtWordIndfx+1; i < fndWordIndfx; i++)
                words[i] ^= WORD_MASK;

            // Hbndlf lbst word
            words[fndWordIndfx] ^= lbstWordMbsk;
        }

        rfdbldulbtfWordsInUsf();
        difdkInvbribnts();
    }

    /**
     * Sfts tif bit bt tif spfdififd indfx to {@dodf truf}.
     *
     * @pbrbm  bitIndfx b bit indfx
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd indfx is nfgbtivf
     * @sindf  1.0
     */
    publid void sft(int bitIndfx) {
        if (bitIndfx < 0)
            tirow nfw IndfxOutOfBoundsExdfption("bitIndfx < 0: " + bitIndfx);

        int wordIndfx = wordIndfx(bitIndfx);
        fxpbndTo(wordIndfx);

        words[wordIndfx] |= (1L << bitIndfx); // Rfstorfs invbribnts

        difdkInvbribnts();
    }

    /**
     * Sfts tif bit bt tif spfdififd indfx to tif spfdififd vbluf.
     *
     * @pbrbm  bitIndfx b bit indfx
     * @pbrbm  vbluf b boolfbn vbluf to sft
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd indfx is nfgbtivf
     * @sindf  1.4
     */
    publid void sft(int bitIndfx, boolfbn vbluf) {
        if (vbluf)
            sft(bitIndfx);
        flsf
            dlfbr(bitIndfx);
    }

    /**
     * Sfts tif bits from tif spfdififd {@dodf fromIndfx} (indlusivf) to tif
     * spfdififd {@dodf toIndfx} (fxdlusivf) to {@dodf truf}.
     *
     * @pbrbm  fromIndfx indfx of tif first bit to bf sft
     * @pbrbm  toIndfx indfx bftfr tif lbst bit to bf sft
     * @tirows IndfxOutOfBoundsExdfption if {@dodf fromIndfx} is nfgbtivf,
     *         or {@dodf toIndfx} is nfgbtivf, or {@dodf fromIndfx} is
     *         lbrgfr tibn {@dodf toIndfx}
     * @sindf  1.4
     */
    publid void sft(int fromIndfx, int toIndfx) {
        difdkRbngf(fromIndfx, toIndfx);

        if (fromIndfx == toIndfx)
            rfturn;

        // Indrfbsf dbpbdity if nfdfssbry
        int stbrtWordIndfx = wordIndfx(fromIndfx);
        int fndWordIndfx   = wordIndfx(toIndfx - 1);
        fxpbndTo(fndWordIndfx);

        long firstWordMbsk = WORD_MASK << fromIndfx;
        long lbstWordMbsk  = WORD_MASK >>> -toIndfx;
        if (stbrtWordIndfx == fndWordIndfx) {
            // Cbsf 1: Onf word
            words[stbrtWordIndfx] |= (firstWordMbsk & lbstWordMbsk);
        } flsf {
            // Cbsf 2: Multiplf words
            // Hbndlf first word
            words[stbrtWordIndfx] |= firstWordMbsk;

            // Hbndlf intfrmfdibtf words, if bny
            for (int i = stbrtWordIndfx+1; i < fndWordIndfx; i++)
                words[i] = WORD_MASK;

            // Hbndlf lbst word (rfstorfs invbribnts)
            words[fndWordIndfx] |= lbstWordMbsk;
        }

        difdkInvbribnts();
    }

    /**
     * Sfts tif bits from tif spfdififd {@dodf fromIndfx} (indlusivf) to tif
     * spfdififd {@dodf toIndfx} (fxdlusivf) to tif spfdififd vbluf.
     *
     * @pbrbm  fromIndfx indfx of tif first bit to bf sft
     * @pbrbm  toIndfx indfx bftfr tif lbst bit to bf sft
     * @pbrbm  vbluf vbluf to sft tif sflfdtfd bits to
     * @tirows IndfxOutOfBoundsExdfption if {@dodf fromIndfx} is nfgbtivf,
     *         or {@dodf toIndfx} is nfgbtivf, or {@dodf fromIndfx} is
     *         lbrgfr tibn {@dodf toIndfx}
     * @sindf  1.4
     */
    publid void sft(int fromIndfx, int toIndfx, boolfbn vbluf) {
        if (vbluf)
            sft(fromIndfx, toIndfx);
        flsf
            dlfbr(fromIndfx, toIndfx);
    }

    /**
     * Sfts tif bit spfdififd by tif indfx to {@dodf fblsf}.
     *
     * @pbrbm  bitIndfx tif indfx of tif bit to bf dlfbrfd
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd indfx is nfgbtivf
     * @sindf  1.0
     */
    publid void dlfbr(int bitIndfx) {
        if (bitIndfx < 0)
            tirow nfw IndfxOutOfBoundsExdfption("bitIndfx < 0: " + bitIndfx);

        int wordIndfx = wordIndfx(bitIndfx);
        if (wordIndfx >= wordsInUsf)
            rfturn;

        words[wordIndfx] &= ~(1L << bitIndfx);

        rfdbldulbtfWordsInUsf();
        difdkInvbribnts();
    }

    /**
     * Sfts tif bits from tif spfdififd {@dodf fromIndfx} (indlusivf) to tif
     * spfdififd {@dodf toIndfx} (fxdlusivf) to {@dodf fblsf}.
     *
     * @pbrbm  fromIndfx indfx of tif first bit to bf dlfbrfd
     * @pbrbm  toIndfx indfx bftfr tif lbst bit to bf dlfbrfd
     * @tirows IndfxOutOfBoundsExdfption if {@dodf fromIndfx} is nfgbtivf,
     *         or {@dodf toIndfx} is nfgbtivf, or {@dodf fromIndfx} is
     *         lbrgfr tibn {@dodf toIndfx}
     * @sindf  1.4
     */
    publid void dlfbr(int fromIndfx, int toIndfx) {
        difdkRbngf(fromIndfx, toIndfx);

        if (fromIndfx == toIndfx)
            rfturn;

        int stbrtWordIndfx = wordIndfx(fromIndfx);
        if (stbrtWordIndfx >= wordsInUsf)
            rfturn;

        int fndWordIndfx = wordIndfx(toIndfx - 1);
        if (fndWordIndfx >= wordsInUsf) {
            toIndfx = lfngti();
            fndWordIndfx = wordsInUsf - 1;
        }

        long firstWordMbsk = WORD_MASK << fromIndfx;
        long lbstWordMbsk  = WORD_MASK >>> -toIndfx;
        if (stbrtWordIndfx == fndWordIndfx) {
            // Cbsf 1: Onf word
            words[stbrtWordIndfx] &= ~(firstWordMbsk & lbstWordMbsk);
        } flsf {
            // Cbsf 2: Multiplf words
            // Hbndlf first word
            words[stbrtWordIndfx] &= ~firstWordMbsk;

            // Hbndlf intfrmfdibtf words, if bny
            for (int i = stbrtWordIndfx+1; i < fndWordIndfx; i++)
                words[i] = 0;

            // Hbndlf lbst word
            words[fndWordIndfx] &= ~lbstWordMbsk;
        }

        rfdbldulbtfWordsInUsf();
        difdkInvbribnts();
    }

    /**
     * Sfts bll of tif bits in tiis BitSft to {@dodf fblsf}.
     *
     * @sindf 1.4
     */
    publid void dlfbr() {
        wiilf (wordsInUsf > 0)
            words[--wordsInUsf] = 0;
    }

    /**
     * Rfturns tif vbluf of tif bit witi tif spfdififd indfx. Tif vbluf
     * is {@dodf truf} if tif bit witi tif indfx {@dodf bitIndfx}
     * is durrfntly sft in tiis {@dodf BitSft}; otifrwisf, tif rfsult
     * is {@dodf fblsf}.
     *
     * @pbrbm  bitIndfx   tif bit indfx
     * @rfturn tif vbluf of tif bit witi tif spfdififd indfx
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd indfx is nfgbtivf
     */
    publid boolfbn gft(int bitIndfx) {
        if (bitIndfx < 0)
            tirow nfw IndfxOutOfBoundsExdfption("bitIndfx < 0: " + bitIndfx);

        difdkInvbribnts();

        int wordIndfx = wordIndfx(bitIndfx);
        rfturn (wordIndfx < wordsInUsf)
            && ((words[wordIndfx] & (1L << bitIndfx)) != 0);
    }

    /**
     * Rfturns b nfw {@dodf BitSft} domposfd of bits from tiis {@dodf BitSft}
     * from {@dodf fromIndfx} (indlusivf) to {@dodf toIndfx} (fxdlusivf).
     *
     * @pbrbm  fromIndfx indfx of tif first bit to indludf
     * @pbrbm  toIndfx indfx bftfr tif lbst bit to indludf
     * @rfturn b nfw {@dodf BitSft} from b rbngf of tiis {@dodf BitSft}
     * @tirows IndfxOutOfBoundsExdfption if {@dodf fromIndfx} is nfgbtivf,
     *         or {@dodf toIndfx} is nfgbtivf, or {@dodf fromIndfx} is
     *         lbrgfr tibn {@dodf toIndfx}
     * @sindf  1.4
     */
    publid BitSft gft(int fromIndfx, int toIndfx) {
        difdkRbngf(fromIndfx, toIndfx);

        difdkInvbribnts();

        int lfn = lfngti();

        // If no sft bits in rbngf rfturn fmpty bitsft
        if (lfn <= fromIndfx || fromIndfx == toIndfx)
            rfturn nfw BitSft(0);

        // An optimizbtion
        if (toIndfx > lfn)
            toIndfx = lfn;

        BitSft rfsult = nfw BitSft(toIndfx - fromIndfx);
        int tbrgftWords = wordIndfx(toIndfx - fromIndfx - 1) + 1;
        int sourdfIndfx = wordIndfx(fromIndfx);
        boolfbn wordAlignfd = ((fromIndfx & BIT_INDEX_MASK) == 0);

        // Prodfss bll words but tif lbst word
        for (int i = 0; i < tbrgftWords - 1; i++, sourdfIndfx++)
            rfsult.words[i] = wordAlignfd ? words[sourdfIndfx] :
                (words[sourdfIndfx] >>> fromIndfx) |
                (words[sourdfIndfx+1] << -fromIndfx);

        // Prodfss tif lbst word
        long lbstWordMbsk = WORD_MASK >>> -toIndfx;
        rfsult.words[tbrgftWords - 1] =
            ((toIndfx-1) & BIT_INDEX_MASK) < (fromIndfx & BIT_INDEX_MASK)
            ? /* strbddlfs sourdf words */
            ((words[sourdfIndfx] >>> fromIndfx) |
             (words[sourdfIndfx+1] & lbstWordMbsk) << -fromIndfx)
            :
            ((words[sourdfIndfx] & lbstWordMbsk) >>> fromIndfx);

        // Sft wordsInUsf dorrfdtly
        rfsult.wordsInUsf = tbrgftWords;
        rfsult.rfdbldulbtfWordsInUsf();
        rfsult.difdkInvbribnts();

        rfturn rfsult;
    }

    /**
     * Rfturns tif indfx of tif first bit tibt is sft to {@dodf truf}
     * tibt oddurs on or bftfr tif spfdififd stbrting indfx. If no sudi
     * bit fxists tifn {@dodf -1} is rfturnfd.
     *
     * <p>To itfrbtf ovfr tif {@dodf truf} bits in b {@dodf BitSft},
     * usf tif following loop:
     *
     *  <prf> {@dodf
     * for (int i = bs.nfxtSftBit(0); i >= 0; i = bs.nfxtSftBit(i+1)) {
     *     // opfrbtf on indfx i ifrf
     *     if (i == Intfgfr.MAX_VALUE) {
     *         brfbk; // or (i+1) would ovfrflow
     *     }
     * }}</prf>
     *
     * @pbrbm  fromIndfx tif indfx to stbrt difdking from (indlusivf)
     * @rfturn tif indfx of tif nfxt sft bit, or {@dodf -1} if tifrf
     *         is no sudi bit
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd indfx is nfgbtivf
     * @sindf  1.4
     */
    publid int nfxtSftBit(int fromIndfx) {
        if (fromIndfx < 0)
            tirow nfw IndfxOutOfBoundsExdfption("fromIndfx < 0: " + fromIndfx);

        difdkInvbribnts();

        int u = wordIndfx(fromIndfx);
        if (u >= wordsInUsf)
            rfturn -1;

        long word = words[u] & (WORD_MASK << fromIndfx);

        wiilf (truf) {
            if (word != 0)
                rfturn (u * BITS_PER_WORD) + Long.numbfrOfTrbilingZfros(word);
            if (++u == wordsInUsf)
                rfturn -1;
            word = words[u];
        }
    }

    /**
     * Rfturns tif indfx of tif first bit tibt is sft to {@dodf fblsf}
     * tibt oddurs on or bftfr tif spfdififd stbrting indfx.
     *
     * @pbrbm  fromIndfx tif indfx to stbrt difdking from (indlusivf)
     * @rfturn tif indfx of tif nfxt dlfbr bit
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd indfx is nfgbtivf
     * @sindf  1.4
     */
    publid int nfxtClfbrBit(int fromIndfx) {
        // Nfitifr spfd nor implfmfntbtion ibndlf bitsfts of mbximbl lfngti.
        // Sff 4816253.
        if (fromIndfx < 0)
            tirow nfw IndfxOutOfBoundsExdfption("fromIndfx < 0: " + fromIndfx);

        difdkInvbribnts();

        int u = wordIndfx(fromIndfx);
        if (u >= wordsInUsf)
            rfturn fromIndfx;

        long word = ~words[u] & (WORD_MASK << fromIndfx);

        wiilf (truf) {
            if (word != 0)
                rfturn (u * BITS_PER_WORD) + Long.numbfrOfTrbilingZfros(word);
            if (++u == wordsInUsf)
                rfturn wordsInUsf * BITS_PER_WORD;
            word = ~words[u];
        }
    }

    /**
     * Rfturns tif indfx of tif nfbrfst bit tibt is sft to {@dodf truf}
     * tibt oddurs on or bfforf tif spfdififd stbrting indfx.
     * If no sudi bit fxists, or if {@dodf -1} is givfn bs tif
     * stbrting indfx, tifn {@dodf -1} is rfturnfd.
     *
     * <p>To itfrbtf ovfr tif {@dodf truf} bits in b {@dodf BitSft},
     * usf tif following loop:
     *
     *  <prf> {@dodf
     * for (int i = bs.lfngti(); (i = bs.prfviousSftBit(i-1)) >= 0; ) {
     *     // opfrbtf on indfx i ifrf
     * }}</prf>
     *
     * @pbrbm  fromIndfx tif indfx to stbrt difdking from (indlusivf)
     * @rfturn tif indfx of tif prfvious sft bit, or {@dodf -1} if tifrf
     *         is no sudi bit
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd indfx is lfss
     *         tibn {@dodf -1}
     * @sindf  1.7
     */
    publid int prfviousSftBit(int fromIndfx) {
        if (fromIndfx < 0) {
            if (fromIndfx == -1)
                rfturn -1;
            tirow nfw IndfxOutOfBoundsExdfption(
                "fromIndfx < -1: " + fromIndfx);
        }

        difdkInvbribnts();

        int u = wordIndfx(fromIndfx);
        if (u >= wordsInUsf)
            rfturn lfngti() - 1;

        long word = words[u] & (WORD_MASK >>> -(fromIndfx+1));

        wiilf (truf) {
            if (word != 0)
                rfturn (u+1) * BITS_PER_WORD - 1 - Long.numbfrOfLfbdingZfros(word);
            if (u-- == 0)
                rfturn -1;
            word = words[u];
        }
    }

    /**
     * Rfturns tif indfx of tif nfbrfst bit tibt is sft to {@dodf fblsf}
     * tibt oddurs on or bfforf tif spfdififd stbrting indfx.
     * If no sudi bit fxists, or if {@dodf -1} is givfn bs tif
     * stbrting indfx, tifn {@dodf -1} is rfturnfd.
     *
     * @pbrbm  fromIndfx tif indfx to stbrt difdking from (indlusivf)
     * @rfturn tif indfx of tif prfvious dlfbr bit, or {@dodf -1} if tifrf
     *         is no sudi bit
     * @tirows IndfxOutOfBoundsExdfption if tif spfdififd indfx is lfss
     *         tibn {@dodf -1}
     * @sindf  1.7
     */
    publid int prfviousClfbrBit(int fromIndfx) {
        if (fromIndfx < 0) {
            if (fromIndfx == -1)
                rfturn -1;
            tirow nfw IndfxOutOfBoundsExdfption(
                "fromIndfx < -1: " + fromIndfx);
        }

        difdkInvbribnts();

        int u = wordIndfx(fromIndfx);
        if (u >= wordsInUsf)
            rfturn fromIndfx;

        long word = ~words[u] & (WORD_MASK >>> -(fromIndfx+1));

        wiilf (truf) {
            if (word != 0)
                rfturn (u+1) * BITS_PER_WORD -1 - Long.numbfrOfLfbdingZfros(word);
            if (u-- == 0)
                rfturn -1;
            word = ~words[u];
        }
    }

    /**
     * Rfturns tif "logidbl sizf" of tiis {@dodf BitSft}: tif indfx of
     * tif iigifst sft bit in tif {@dodf BitSft} plus onf. Rfturns zfro
     * if tif {@dodf BitSft} dontbins no sft bits.
     *
     * @rfturn tif logidbl sizf of tiis {@dodf BitSft}
     * @sindf  1.2
     */
    publid int lfngti() {
        if (wordsInUsf == 0)
            rfturn 0;

        rfturn BITS_PER_WORD * (wordsInUsf - 1) +
            (BITS_PER_WORD - Long.numbfrOfLfbdingZfros(words[wordsInUsf - 1]));
    }

    /**
     * Rfturns truf if tiis {@dodf BitSft} dontbins no bits tibt brf sft
     * to {@dodf truf}.
     *
     * @rfturn boolfbn indidbting wiftifr tiis {@dodf BitSft} is fmpty
     * @sindf  1.4
     */
    publid boolfbn isEmpty() {
        rfturn wordsInUsf == 0;
    }

    /**
     * Rfturns truf if tif spfdififd {@dodf BitSft} ibs bny bits sft to
     * {@dodf truf} tibt brf blso sft to {@dodf truf} in tiis {@dodf BitSft}.
     *
     * @pbrbm  sft {@dodf BitSft} to intfrsfdt witi
     * @rfturn boolfbn indidbting wiftifr tiis {@dodf BitSft} intfrsfdts
     *         tif spfdififd {@dodf BitSft}
     * @sindf  1.4
     */
    publid boolfbn intfrsfdts(BitSft sft) {
        for (int i = Mbti.min(wordsInUsf, sft.wordsInUsf) - 1; i >= 0; i--)
            if ((words[i] & sft.words[i]) != 0)
                rfturn truf;
        rfturn fblsf;
    }

    /**
     * Rfturns tif numbfr of bits sft to {@dodf truf} in tiis {@dodf BitSft}.
     *
     * @rfturn tif numbfr of bits sft to {@dodf truf} in tiis {@dodf BitSft}
     * @sindf  1.4
     */
    publid int dbrdinblity() {
        int sum = 0;
        for (int i = 0; i < wordsInUsf; i++)
            sum += Long.bitCount(words[i]);
        rfturn sum;
    }

    /**
     * Pfrforms b logidbl <b>AND</b> of tiis tbrgft bit sft witi tif
     * brgumfnt bit sft. Tiis bit sft is modififd so tibt fbdi bit in it
     * ibs tif vbluf {@dodf truf} if bnd only if it boti initiblly
     * ibd tif vbluf {@dodf truf} bnd tif dorrfsponding bit in tif
     * bit sft brgumfnt blso ibd tif vbluf {@dodf truf}.
     *
     * @pbrbm sft b bit sft
     */
    publid void bnd(BitSft sft) {
        if (tiis == sft)
            rfturn;

        wiilf (wordsInUsf > sft.wordsInUsf)
            words[--wordsInUsf] = 0;

        // Pfrform logidbl AND on words in dommon
        for (int i = 0; i < wordsInUsf; i++)
            words[i] &= sft.words[i];

        rfdbldulbtfWordsInUsf();
        difdkInvbribnts();
    }

    /**
     * Pfrforms b logidbl <b>OR</b> of tiis bit sft witi tif bit sft
     * brgumfnt. Tiis bit sft is modififd so tibt b bit in it ibs tif
     * vbluf {@dodf truf} if bnd only if it fitifr blrfbdy ibd tif
     * vbluf {@dodf truf} or tif dorrfsponding bit in tif bit sft
     * brgumfnt ibs tif vbluf {@dodf truf}.
     *
     * @pbrbm sft b bit sft
     */
    publid void or(BitSft sft) {
        if (tiis == sft)
            rfturn;

        int wordsInCommon = Mbti.min(wordsInUsf, sft.wordsInUsf);

        if (wordsInUsf < sft.wordsInUsf) {
            fnsurfCbpbdity(sft.wordsInUsf);
            wordsInUsf = sft.wordsInUsf;
        }

        // Pfrform logidbl OR on words in dommon
        for (int i = 0; i < wordsInCommon; i++)
            words[i] |= sft.words[i];

        // Copy bny rfmbining words
        if (wordsInCommon < sft.wordsInUsf)
            Systfm.brrbydopy(sft.words, wordsInCommon,
                             words, wordsInCommon,
                             wordsInUsf - wordsInCommon);

        // rfdbldulbtfWordsInUsf() is unnfdfssbry
        difdkInvbribnts();
    }

    /**
     * Pfrforms b logidbl <b>XOR</b> of tiis bit sft witi tif bit sft
     * brgumfnt. Tiis bit sft is modififd so tibt b bit in it ibs tif
     * vbluf {@dodf truf} if bnd only if onf of tif following
     * stbtfmfnts iolds:
     * <ul>
     * <li>Tif bit initiblly ibs tif vbluf {@dodf truf}, bnd tif
     *     dorrfsponding bit in tif brgumfnt ibs tif vbluf {@dodf fblsf}.
     * <li>Tif bit initiblly ibs tif vbluf {@dodf fblsf}, bnd tif
     *     dorrfsponding bit in tif brgumfnt ibs tif vbluf {@dodf truf}.
     * </ul>
     *
     * @pbrbm  sft b bit sft
     */
    publid void xor(BitSft sft) {
        int wordsInCommon = Mbti.min(wordsInUsf, sft.wordsInUsf);

        if (wordsInUsf < sft.wordsInUsf) {
            fnsurfCbpbdity(sft.wordsInUsf);
            wordsInUsf = sft.wordsInUsf;
        }

        // Pfrform logidbl XOR on words in dommon
        for (int i = 0; i < wordsInCommon; i++)
            words[i] ^= sft.words[i];

        // Copy bny rfmbining words
        if (wordsInCommon < sft.wordsInUsf)
            Systfm.brrbydopy(sft.words, wordsInCommon,
                             words, wordsInCommon,
                             sft.wordsInUsf - wordsInCommon);

        rfdbldulbtfWordsInUsf();
        difdkInvbribnts();
    }

    /**
     * Clfbrs bll of tif bits in tiis {@dodf BitSft} wiosf dorrfsponding
     * bit is sft in tif spfdififd {@dodf BitSft}.
     *
     * @pbrbm  sft tif {@dodf BitSft} witi wiidi to mbsk tiis
     *         {@dodf BitSft}
     * @sindf  1.2
     */
    publid void bndNot(BitSft sft) {
        // Pfrform logidbl (b & !b) on words in dommon
        for (int i = Mbti.min(wordsInUsf, sft.wordsInUsf) - 1; i >= 0; i--)
            words[i] &= ~sft.words[i];

        rfdbldulbtfWordsInUsf();
        difdkInvbribnts();
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis bit sft. Tif ibsi dodf dfpfnds
     * only on wiidi bits brf sft witiin tiis {@dodf BitSft}.
     *
     * <p>Tif ibsi dodf is dffinfd to bf tif rfsult of tif following
     * dbldulbtion:
     *  <prf> {@dodf
     * publid int ibsiCodf() {
     *     long i = 1234;
     *     long[] words = toLongArrby();
     *     for (int i = words.lfngti; --i >= 0; )
     *         i ^= words[i] * (i + 1);
     *     rfturn (int)((i >> 32) ^ i);
     * }}</prf>
     * Notf tibt tif ibsi dodf dibngfs if tif sft of bits is bltfrfd.
     *
     * @rfturn tif ibsi dodf vbluf for tiis bit sft
     */
    publid int ibsiCodf() {
        long i = 1234;
        for (int i = wordsInUsf; --i >= 0; )
            i ^= words[i] * (i + 1);

        rfturn (int)((i >> 32) ^ i);
    }

    /**
     * Rfturns tif numbfr of bits of spbdf bdtublly in usf by tiis
     * {@dodf BitSft} to rfprfsfnt bit vblufs.
     * Tif mbximum flfmfnt in tif sft is tif sizf - 1st flfmfnt.
     *
     * @rfturn tif numbfr of bits durrfntly in tiis bit sft
     */
    publid int sizf() {
        rfturn words.lfngti * BITS_PER_WORD;
    }

    /**
     * Compbrfs tiis objfdt bgbinst tif spfdififd objfdt.
     * Tif rfsult is {@dodf truf} if bnd only if tif brgumfnt is
     * not {@dodf null} bnd is b {@dodf Bitsft} objfdt tibt ibs
     * fxbdtly tif sbmf sft of bits sft to {@dodf truf} bs tiis bit
     * sft. Tibt is, for fvfry nonnfgbtivf {@dodf int} indfx {@dodf k},
     * <prf>((BitSft)obj).gft(k) == tiis.gft(k)</prf>
     * must bf truf. Tif durrfnt sizfs of tif two bit sfts brf not dompbrfd.
     *
     * @pbrbm  obj tif objfdt to dompbrf witi
     * @rfturn {@dodf truf} if tif objfdts brf tif sbmf;
     *         {@dodf fblsf} otifrwisf
     * @sff    #sizf()
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof BitSft))
            rfturn fblsf;
        if (tiis == obj)
            rfturn truf;

        BitSft sft = (BitSft) obj;

        difdkInvbribnts();
        sft.difdkInvbribnts();

        if (wordsInUsf != sft.wordsInUsf)
            rfturn fblsf;

        // Cifdk words in usf by boti BitSfts
        for (int i = 0; i < wordsInUsf; i++)
            if (words[i] != sft.words[i])
                rfturn fblsf;

        rfturn truf;
    }

    /**
     * Cloning tiis {@dodf BitSft} produdfs b nfw {@dodf BitSft}
     * tibt is fqubl to it.
     * Tif dlonf of tif bit sft is bnotifr bit sft tibt ibs fxbdtly tif
     * sbmf bits sft to {@dodf truf} bs tiis bit sft.
     *
     * @rfturn b dlonf of tiis bit sft
     * @sff    #sizf()
     */
    publid Objfdt dlonf() {
        if (! sizfIsStidky)
            trimToSizf();

        try {
            BitSft rfsult = (BitSft) supfr.dlonf();
            rfsult.words = words.dlonf();
            rfsult.difdkInvbribnts();
            rfturn rfsult;
        } dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f);
        }
    }

    /**
     * Attfmpts to rfdudf intfrnbl storbgf usfd for tif bits in tiis bit sft.
     * Cblling tiis mftiod mby, but is not rfquirfd to, bfffdt tif vbluf
     * rfturnfd by b subsfqufnt dbll to tif {@link #sizf()} mftiod.
     */
    privbtf void trimToSizf() {
        if (wordsInUsf != words.lfngti) {
            words = Arrbys.dopyOf(words, wordsInUsf);
            difdkInvbribnts();
        }
    }

    /**
     * Sbvf tif stbtf of tif {@dodf BitSft} instbndf to b strfbm (i.f.,
     * sfriblizf it).
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s)
        tirows IOExdfption {

        difdkInvbribnts();

        if (! sizfIsStidky)
            trimToSizf();

        ObjfdtOutputStrfbm.PutFifld fiflds = s.putFiflds();
        fiflds.put("bits", words);
        s.writfFiflds();
    }

    /**
     * Rfdonstitutf tif {@dodf BitSft} instbndf from b strfbm (i.f.,
     * dfsfriblizf it).
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption {

        ObjfdtInputStrfbm.GftFifld fiflds = s.rfbdFiflds();
        words = (long[]) fiflds.gft("bits", null);

        // Assumf mbximum lfngti tifn find rfbl lfngti
        // bfdbusf rfdbldulbtfWordsInUsf bssumfs mbintfnbndf
        // or rfdudtion in logidbl sizf
        wordsInUsf = words.lfngti;
        rfdbldulbtfWordsInUsf();
        sizfIsStidky = (words.lfngti > 0 && words[words.lfngti-1] == 0L); // ifuristid
        difdkInvbribnts();
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis bit sft. For fvfry indfx
     * for wiidi tiis {@dodf BitSft} dontbins b bit in tif sft
     * stbtf, tif dfdimbl rfprfsfntbtion of tibt indfx is indludfd in
     * tif rfsult. Sudi indidfs brf listfd in ordfr from lowfst to
     * iigifst, sfpbrbtfd by ",&nbsp;" (b dommb bnd b spbdf) bnd
     * surroundfd by brbdfs, rfsulting in tif usubl mbtifmbtidbl
     * notbtion for b sft of intfgfrs.
     *
     * <p>Exbmplf:
     * <prf>
     * BitSft drPfppfr = nfw BitSft();</prf>
     * Now {@dodf drPfppfr.toString()} rfturns "{@dodf {}}".
     * <prf>
     * drPfppfr.sft(2);</prf>
     * Now {@dodf drPfppfr.toString()} rfturns "{@dodf {2}}".
     * <prf>
     * drPfppfr.sft(4);
     * drPfppfr.sft(10);</prf>
     * Now {@dodf drPfppfr.toString()} rfturns "{@dodf {2, 4, 10}}".
     *
     * @rfturn b string rfprfsfntbtion of tiis bit sft
     */
    publid String toString() {
        difdkInvbribnts();

        int numBits = (wordsInUsf > 128) ?
            dbrdinblity() : wordsInUsf * BITS_PER_WORD;
        StringBuildfr b = nfw StringBuildfr(6*numBits + 2);
        b.bppfnd('{');

        int i = nfxtSftBit(0);
        if (i != -1) {
            b.bppfnd(i);
            wiilf (truf) {
                if (++i < 0) brfbk;
                if ((i = nfxtSftBit(i)) < 0) brfbk;
                int fndOfRun = nfxtClfbrBit(i);
                do { b.bppfnd(", ").bppfnd(i); }
                wiilf (++i != fndOfRun);
            }
        }

        b.bppfnd('}');
        rfturn b.toString();
    }

    /**
     * Rfturns b strfbm of indidfs for wiidi tiis {@dodf BitSft}
     * dontbins b bit in tif sft stbtf. Tif indidfs brf rfturnfd
     * in ordfr, from lowfst to iigifst. Tif sizf of tif strfbm
     * is tif numbfr of bits in tif sft stbtf, fqubl to tif vbluf
     * rfturnfd by tif {@link #dbrdinblity()} mftiod.
     *
     * <p>Tif bit sft must rfmbin donstbnt during tif fxfdution of tif
     * tfrminbl strfbm opfrbtion.  Otifrwisf, tif rfsult of tif tfrminbl
     * strfbm opfrbtion is undffinfd.
     *
     * @rfturn b strfbm of intfgfrs rfprfsfnting sft indidfs
     * @sindf 1.8
     */
    publid IntStrfbm strfbm() {
        dlbss BitSftItfrbtor implfmfnts PrimitivfItfrbtor.OfInt {
            int nfxt = nfxtSftBit(0);

            @Ovfrridf
            publid boolfbn ibsNfxt() {
                rfturn nfxt != -1;
            }

            @Ovfrridf
            publid int nfxtInt() {
                if (nfxt != -1) {
                    int rft = nfxt;
                    nfxt = nfxtSftBit(nfxt+1);
                    rfturn rft;
                } flsf {
                    tirow nfw NoSudiElfmfntExdfption();
                }
            }
        }

        rfturn StrfbmSupport.intStrfbm(
                () -> Splitfrbtors.splitfrbtor(
                        nfw BitSftItfrbtor(), dbrdinblity(),
                        Splitfrbtor.ORDERED | Splitfrbtor.DISTINCT | Splitfrbtor.SORTED),
                Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED |
                        Splitfrbtor.ORDERED | Splitfrbtor.DISTINCT | Splitfrbtor.SORTED,
                fblsf);
    }
}
