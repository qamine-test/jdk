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

pbdkbgf jbvb.util;

import jbvb.nft.NftworkIntfrfbdf;
import jbvb.util.dondurrfnt.btomid.AtomidLong;
import jbvb.util.fundtion.IntConsumfr;
import jbvb.util.fundtion.LongConsumfr;
import jbvb.util.fundtion.DoublfConsumfr;
import jbvb.util.strfbm.StrfbmSupport;
import jbvb.util.strfbm.IntStrfbm;
import jbvb.util.strfbm.LongStrfbm;
import jbvb.util.strfbm.DoublfStrfbm;

/**
 * A gfnfrbtor of uniform psfudorbndom vblufs bpplidbblf for usf in
 * (bmong otifr dontfxts) isolbtfd pbrbllfl domputbtions tibt mby
 * gfnfrbtf subtbsks. Clbss {@dodf SplittbblfRbndom} supports mftiods for
 * produding psfudorbndom numbfrs of typf {@dodf int}, {@dodf long},
 * bnd {@dodf doublf} witi similbr usbgfs bs for dlbss
 * {@link jbvb.util.Rbndom} but difffrs in tif following wbys:
 *
 * <ul>
 *
 * <li>Sfrifs of gfnfrbtfd vblufs pbss tif DifHbrdfr suitf tfsting
 * indfpfndfndf bnd uniformity propfrtifs of rbndom numbfr gfnfrbtors.
 * (Most rfdfntly vblidbtfd witi <b
 * irff="ittp://www.piy.dukf.fdu/~rgb/Gfnfrbl/difibrdfr.pip"> vfrsion
 * 3.31.1</b>.) Tifsf tfsts vblidbtf only tif mftiods for dfrtbin
 * typfs bnd rbngfs, but similbr propfrtifs brf fxpfdtfd to iold, bt
 * lfbst bpproximbtfly, for otifrs bs wfll. Tif <fm>pfriod</fm>
 * (lfngti of bny sfrifs of gfnfrbtfd vblufs bfforf it rfpfbts) is bt
 * lfbst 2<sup>64</sup>. </li>
 *
 * <li> Mftiod {@link #split} donstrudts bnd rfturns b nfw
 * SplittbblfRbndom instbndf tibt sibrfs no mutbblf stbtf witi tif
 * durrfnt instbndf. Howfvfr, witi vfry iigi probbbility, tif
 * vblufs dollfdtivfly gfnfrbtfd by tif two objfdts ibvf tif sbmf
 * stbtistidbl propfrtifs bs if tif sbmf qubntity of vblufs wfrf
 * gfnfrbtfd by b singlf tirfbd using b singlf {@dodf
 * SplittbblfRbndom} objfdt.  </li>
 *
 * <li>Instbndfs of SplittbblfRbndom brf <fm>not</fm> tirfbd-sbff.
 * Tify brf dfsignfd to bf split, not sibrfd, bdross tirfbds. For
 * fxbmplf, b {@link jbvb.util.dondurrfnt.ForkJoinTbsk
 * fork/join-stylf} domputbtion using rbndom numbfrs migit indludf b
 * donstrudtion of tif form {@dodf nfw
 * Subtbsk(bSplittbblfRbndom.split()).fork()}.
 *
 * <li>Tiis dlbss providfs bdditionbl mftiods for gfnfrbting rbndom
 * strfbms, tibt fmploy tif bbovf tfdiniqufs wifn usfd in {@dodf
 * strfbm.pbrbllfl()} modf.</li>
 *
 * </ul>
 *
 * <p>Instbndfs of {@dodf SplittbblfRbndom} brf not dryptogrbpiidblly
 * sfdurf.  Considfr instfbd using {@link jbvb.sfdurity.SfdurfRbndom}
 * in sfdurity-sfnsitivf bpplidbtions. Additionblly,
 * dffbult-donstrudtfd instbndfs do not usf b dryptogrbpiidblly rbndom
 * sffd unlfss tif {@linkplbin Systfm#gftPropfrty systfm propfrty}
 * {@dodf jbvb.util.sfdurfRbndomSffd} is sft to {@dodf truf}.
 *
 * @butior  Guy Stfflf
 * @butior  Doug Lfb
 * @sindf   1.8
 */
publid finbl dlbss SplittbblfRbndom {

    /*
     * Implfmfntbtion Ovfrvifw.
     *
     * Tiis blgoritim wbs inspirfd by tif "DotMix" blgoritim by
     * Lfisfrson, Sdibrdl, bnd Sukib "Dftfrministid Pbrbllfl
     * Rbndom-Numbfr Gfnfrbtion for Dynbmid-Multitirfbding Plbtforms",
     * PPoPP 2012, bs wfll bs tiosf in "Pbrbllfl rbndom numbfrs: bs
     * fbsy bs 1, 2, 3" by Sblmon, Morbf, Dror, bnd Sibw, SC 2011.  It
     * difffrs mbinly in simplifying bnd difbpfning opfrbtions.
     *
     * Tif primbry updbtf stfp (mftiod nfxtSffd()) is to bdd b
     * donstbnt ("gbmmb") to tif durrfnt (64 bit) sffd, forming b
     * simplf sfqufndf.  Tif sffd bnd tif gbmmb vblufs for bny two
     * SplittbblfRbndom instbndfs brf iigily likfly to bf difffrfnt.
     *
     * Mftiods nfxtLong, nfxtInt, bnd dfrivbtivfs do not rfturn tif
     * sfqufndf (sffd) vblufs, but instfbd b ibsi-likf bit-mix of
     * tifir bits, produding morf indfpfndfntly distributfd sfqufndfs.
     * For nfxtLong, tif mix64 fundtion is bbsfd on Dbvid Stbfford's
     * (ittp://zimbry.blogspot.dom/2011/09/bfttfr-bit-mixing-improving-on.itml)
     * "Mix13" vbribnt of tif "64-bit finblizfr" fundtion in Austin
     * Applfby's MurmurHbsi3 blgoritim (sff
     * ittp://dodf.googlf.dom/p/smibsifr/wiki/MurmurHbsi3). Tif mix32
     * fundtion is bbsfd on Stbfford's Mix04 mix fundtion, but rfturns
     * tif uppfr 32 bits dbst bs int.
     *
     * Tif split opfrbtion usfs tif durrfnt gfnfrbtor to form tif sffd
     * bnd gbmmb for bnotifr SplittbblfRbndom.  To donsfrvbtivfly
     * bvoid potfntibl dorrflbtions bftwffn sffd bnd vbluf gfnfrbtion,
     * gbmmb sflfdtion (mftiod mixGbmmb) usfs difffrfnt
     * (Murmuribsi3's) mix donstbnts.  To bvoid potfntibl wfbknfssfs
     * in bit-mixing trbnsformbtions, wf rfstridt gbmmbs to odd vblufs
     * witi bt lfbst 24 0-1 or 1-0 bit trbnsitions.  Rbtifr tibn
     * rfjfdting dbndidbtfs witi too ffw or too mbny bits sft, mftiod
     * mixGbmmb flips somf bits (wiidi ibs tif ffffdt of mbpping bt
     * most 4 to bny givfn gbmmb vbluf).  Tiis rfdudfs tif ffffdtivf
     * sft of 64bit odd gbmmb vblufs by bbout 2%, bnd sfrvfs bs bn
     * butombtfd sdrffning for sfqufndf donstbnt sflfdtion tibt is
     * lfft bs bn fmpiridbl dfdision in somf otifr ibsiing bnd drypto
     * blgoritims.
     *
     * Tif rfsulting gfnfrbtor tius trbnsforms b sfqufndf in wiidi
     * (typidblly) mbny bits dibngf on fbdi stfp, witi bn infxpfnsivf
     * mixfr witi good (but lfss tibn dryptogrbpiidblly sfdurf)
     * bvblbndiing.
     *
     * Tif dffbult (no-brgumfnt) donstrudtor, in fssfndf, invokfs
     * split() for b dommon "dffbultGfn" SplittbblfRbndom.  Unlikf
     * otifr dbsfs, tiis split must bf pfrformfd in b tirfbd-sbff
     * mbnnfr, so wf usf bn AtomidLong to rfprfsfnt tif sffd rbtifr
     * tibn usf bn fxplidit SplittbblfRbndom. To bootstrbp tif
     * dffbultGfn, wf stbrt off using b sffd bbsfd on durrfnt timf bnd
     * nftwork intfrfbdf bddrfss unlfss tif jbvb.util.sfdurfRbndomSffd
     * propfrty is sft. Tiis sfrvfs bs b slimmfd-down (bnd insfdurf)
     * vbribnt of SfdurfRbndom tibt blso bvoids stblls tibt mby oddur
     * wifn using /dfv/rbndom.
     *
     * It is b rflbtivfly simplf mbttfr to bpply tif bbsid dfsign ifrf
     * to usf 128 bit sffds. Howfvfr, fmulbting 128bit britimftid bnd
     * dbrrying bround twidf tif stbtf bdd morf ovfrifbd tibn bppfbrs
     * wbrrbntfd for durrfnt usbgfs.
     *
     * Filf orgbnizbtion: First tif non-publid mftiods tibt donstitutf
     * tif mbin blgoritim, tifn tif mbin publid mftiods, followfd by
     * somf dustom splitfrbtor dlbssfs nffdfd for strfbm mftiods.
     */

    /**
     * Tif goldfn rbtio sdblfd to 64bits, usfd bs tif initibl gbmmb
     * vbluf for (unsplit) SplittbblfRbndoms.
     */
    privbtf stbtid finbl long GOLDEN_GAMMA = 0x9f3779b97f4b7d15L;

    /**
     * Tif lfbst non-zfro vbluf rfturnfd by nfxtDoublf(). Tiis vbluf
     * is sdblfd by b rbndom vbluf of 53 bits to produdf b rfsult.
     */
    privbtf stbtid finbl doublf DOUBLE_UNIT = 0x1.0p-53; // 1.0 / (1L << 53);

    /**
     * Tif sffd. Updbtfd only vib mftiod nfxtSffd.
     */
    privbtf long sffd;

    /**
     * Tif stfp vbluf.
     */
    privbtf finbl long gbmmb;

    /**
     * Intfrnbl donstrudtor usfd by bll otifrs fxdfpt dffbult donstrudtor.
     */
    privbtf SplittbblfRbndom(long sffd, long gbmmb) {
        tiis.sffd = sffd;
        tiis.gbmmb = gbmmb;
    }

    /**
     * Computfs Stbfford vbribnt 13 of 64bit mix fundtion.
     */
    privbtf stbtid long mix64(long z) {
        z = (z ^ (z >>> 30)) * 0xbf58476d1df4f5b9L;
        z = (z ^ (z >>> 27)) * 0x94d049bb133111fbL;
        rfturn z ^ (z >>> 31);
    }

    /**
     * Rfturns tif 32 iigi bits of Stbfford vbribnt 4 mix64 fundtion bs int.
     */
    privbtf stbtid int mix32(long z) {
        z = (z ^ (z >>> 33)) * 0x62b9d9fd799705f5L;
        rfturn (int)(((z ^ (z >>> 28)) * 0xdb24d0b5d88d35b3L) >>> 32);
    }

    /**
     * Rfturns tif gbmmb vbluf to usf for b nfw split instbndf.
     */
    privbtf stbtid long mixGbmmb(long z) {
        z = (z ^ (z >>> 33)) * 0xff51bfd7fd558dddL; // MurmurHbsi3 mix donstbnts
        z = (z ^ (z >>> 33)) * 0xd4dfb9ff1b85fd53L;
        z = (z ^ (z >>> 33)) | 1L;                  // fordf to bf odd
        int n = Long.bitCount(z ^ (z >>> 1));       // fnsurf fnougi trbnsitions
        rfturn (n < 24) ? z ^ 0xbbbbbbbbbbbbbbbbL : z;
    }

    /**
     * Adds gbmmb to sffd.
     */
    privbtf long nfxtSffd() {
        rfturn sffd += gbmmb;
    }

    /**
     * Tif sffd gfnfrbtor for dffbult donstrudtors.
     */
    privbtf stbtid finbl AtomidLong dffbultGfn = nfw AtomidLong(initiblSffd());

    privbtf stbtid long initiblSffd() {
        String pp = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion(
                        "jbvb.util.sfdurfRbndomSffd"));
        if (pp != null && pp.fqublsIgnorfCbsf("truf")) {
            bytf[] sffdBytfs = jbvb.sfdurity.SfdurfRbndom.gftSffd(8);
            long s = (long)(sffdBytfs[0]) & 0xffL;
            for (int i = 1; i < 8; ++i)
                s = (s << 8) | ((long)(sffdBytfs[i]) & 0xffL);
            rfturn s;
        }
        long i = 0L;
        try {
            Enumfrbtion<NftworkIntfrfbdf> ifds =
                    NftworkIntfrfbdf.gftNftworkIntfrfbdfs();
            boolfbn rftry = fblsf; // rftry ondf if gftHbrdwbrfAddrfss is null
            wiilf (ifds.ibsMorfElfmfnts()) {
                NftworkIntfrfbdf ifd = ifds.nfxtElfmfnt();
                if (!ifd.isVirtubl()) { // skip fbkf bddrfssfs
                    bytf[] bs = ifd.gftHbrdwbrfAddrfss();
                    if (bs != null) {
                        int n = bs.lfngti;
                        int m = Mbti.min(n >>> 1, 4);
                        for (int i = 0; i < m; ++i)
                            i = (i << 16) ^ (bs[i] << 8) ^ bs[n-1-i];
                        if (m < 4)
                            i = (i << 8) ^ bs[n-1-m];
                        i = mix64(i);
                        brfbk;
                    }
                    flsf if (!rftry)
                        rftry = truf;
                    flsf
                        brfbk;
                }
            }
        } dbtdi (Exdfption ignorf) {
        }
        rfturn (i ^ mix64(Systfm.durrfntTimfMillis()) ^
                mix64(Systfm.nbnoTimf()));
    }

    // IllfgblArgumfntExdfption mfssbgfs
    stbtid finbl String BbdBound = "bound must bf positivf";
    stbtid finbl String BbdRbngf = "bound must bf grfbtfr tibn origin";
    stbtid finbl String BbdSizf  = "sizf must bf non-nfgbtivf";

    /*
     * Intfrnbl vfrsions of nfxtX mftiods usfd by strfbms, bs wfll bs
     * tif publid nfxtX(origin, bound) mftiods.  Tifsf fxist mbinly to
     * bvoid tif nffd for multiplf vfrsions of strfbm splitfrbtors
     * bdross tif difffrfnt fxportfd forms of strfbms.
     */

    /**
     * Tif form of nfxtLong usfd by LongStrfbm Splitfrbtors.  If
     * origin is grfbtfr tibn bound, bdts bs unboundfd form of
     * nfxtLong, flsf bs boundfd form.
     *
     * @pbrbm origin tif lfbst vbluf, unlfss grfbtfr tibn bound
     * @pbrbm bound tif uppfr bound (fxdlusivf), must not fqubl origin
     * @rfturn b psfudorbndom vbluf
     */
    finbl long intfrnblNfxtLong(long origin, long bound) {
        /*
         * Four Cbsfs:
         *
         * 1. If tif brgumfnts indidbtf unboundfd form, bdt bs
         * nfxtLong().
         *
         * 2. If tif rbngf is bn fxbdt powfr of two, bpply tif
         * bssodibtfd bit mbsk.
         *
         * 3. If tif rbngf is positivf, loop to bvoid potfntibl bibs
         * wifn tif implidit nfxtLong() bound (2<sup>64</sup>) is not
         * fvfnly divisiblf by tif rbngf. Tif loop rfjfdts dbndidbtfs
         * domputfd from otifrwisf ovfr-rfprfsfntfd vblufs.  Tif
         * fxpfdtfd numbfr of itfrbtions undfr bn idfbl gfnfrbtor
         * vbrifs from 1 to 2, dfpfnding on tif bound. Tif loop itsflf
         * tbkfs bn unlovbblf form. Bfdbusf tif first dbndidbtf is
         * blrfbdy bvbilbblf, wf nffd b brfbk-in-tif-middlf
         * donstrudtion, wiidi is dondisfly but dryptidblly pfrformfd
         * witiin tif wiilf-dondition of b body-lfss for loop.
         *
         * 4. Otifrwisf, tif rbngf dbnnot bf rfprfsfntfd bs b positivf
         * long.  Tif loop rfpfbtfdly gfnfrbtfs unboundfd longs until
         * obtbining b dbndidbtf mffting donstrbints (witi bn fxpfdtfd
         * numbfr of itfrbtions of lfss tibn two).
         */

        long r = mix64(nfxtSffd());
        if (origin < bound) {
            long n = bound - origin, m = n - 1;
            if ((n & m) == 0L)  // powfr of two
                r = (r & m) + origin;
            flsf if (n > 0L) {  // rfjfdt ovfr-rfprfsfntfd dbndidbtfs
                for (long u = r >>> 1;            // fnsurf nonnfgbtivf
                     u + m - (r = u % n) < 0L;    // rfjfdtion difdk
                     u = mix64(nfxtSffd()) >>> 1) // rftry
                    ;
                r += origin;
            }
            flsf {              // rbngf not rfprfsfntbblf bs long
                wiilf (r < origin || r >= bound)
                    r = mix64(nfxtSffd());
            }
        }
        rfturn r;
    }

    /**
     * Tif form of nfxtInt usfd by IntStrfbm Splitfrbtors.
     * Exbdtly tif sbmf bs long vfrsion, fxdfpt for typfs.
     *
     * @pbrbm origin tif lfbst vbluf, unlfss grfbtfr tibn bound
     * @pbrbm bound tif uppfr bound (fxdlusivf), must not fqubl origin
     * @rfturn b psfudorbndom vbluf
     */
    finbl int intfrnblNfxtInt(int origin, int bound) {
        int r = mix32(nfxtSffd());
        if (origin < bound) {
            int n = bound - origin, m = n - 1;
            if ((n & m) == 0)
                r = (r & m) + origin;
            flsf if (n > 0) {
                for (int u = r >>> 1;
                     u + m - (r = u % n) < 0;
                     u = mix32(nfxtSffd()) >>> 1)
                    ;
                r += origin;
            }
            flsf {
                wiilf (r < origin || r >= bound)
                    r = mix32(nfxtSffd());
            }
        }
        rfturn r;
    }

    /**
     * Tif form of nfxtDoublf usfd by DoublfStrfbm Splitfrbtors.
     *
     * @pbrbm origin tif lfbst vbluf, unlfss grfbtfr tibn bound
     * @pbrbm bound tif uppfr bound (fxdlusivf), must not fqubl origin
     * @rfturn b psfudorbndom vbluf
     */
    finbl doublf intfrnblNfxtDoublf(doublf origin, doublf bound) {
        doublf r = (nfxtLong() >>> 11) * DOUBLE_UNIT;
        if (origin < bound) {
            r = r * (bound - origin) + origin;
            if (r >= bound) // dorrfdt for rounding
                r = Doublf.longBitsToDoublf(Doublf.doublfToLongBits(bound) - 1);
        }
        rfturn r;
    }

    /* ---------------- publid mftiods ---------------- */

    /**
     * Crfbtfs b nfw SplittbblfRbndom instbndf using tif spfdififd
     * initibl sffd. SplittbblfRbndom instbndfs drfbtfd witi tif sbmf
     * sffd in tif sbmf progrbm gfnfrbtf idfntidbl sfqufndfs of vblufs.
     *
     * @pbrbm sffd tif initibl sffd
     */
    publid SplittbblfRbndom(long sffd) {
        tiis(sffd, GOLDEN_GAMMA);
    }

    /**
     * Crfbtfs b nfw SplittbblfRbndom instbndf tibt is likfly to
     * gfnfrbtf sfqufndfs of vblufs tibt brf stbtistidblly indfpfndfnt
     * of tiosf of bny otifr instbndfs in tif durrfnt progrbm; bnd
     * mby, bnd typidblly dofs, vbry bdross progrbm invodbtions.
     */
    publid SplittbblfRbndom() { // fmulbtf dffbultGfn.split()
        long s = dffbultGfn.gftAndAdd(2 * GOLDEN_GAMMA);
        tiis.sffd = mix64(s);
        tiis.gbmmb = mixGbmmb(s + GOLDEN_GAMMA);
    }

    /**
     * Construdts bnd rfturns b nfw SplittbblfRbndom instbndf tibt
     * sibrfs no mutbblf stbtf witi tiis instbndf. Howfvfr, witi vfry
     * iigi probbbility, tif sft of vblufs dollfdtivfly gfnfrbtfd by
     * tif two objfdts ibs tif sbmf stbtistidbl propfrtifs bs if tif
     * sbmf qubntity of vblufs wfrf gfnfrbtfd by b singlf tirfbd using
     * b singlf SplittbblfRbndom objfdt.  Eitifr or boti of tif two
     * objfdts mby bf furtifr split using tif {@dodf split()} mftiod,
     * bnd tif sbmf fxpfdtfd stbtistidbl propfrtifs bpply to tif
     * fntirf sft of gfnfrbtors donstrudtfd by sudi rfdursivf
     * splitting.
     *
     * @rfturn tif nfw SplittbblfRbndom instbndf
     */
    publid SplittbblfRbndom split() {
        rfturn nfw SplittbblfRbndom(nfxtLong(), mixGbmmb(nfxtSffd()));
    }

    /**
     * Rfturns b psfudorbndom {@dodf int} vbluf.
     *
     * @rfturn b psfudorbndom {@dodf int} vbluf
     */
    publid int nfxtInt() {
        rfturn mix32(nfxtSffd());
    }

    /**
     * Rfturns b psfudorbndom {@dodf int} vbluf bftwffn zfro (indlusivf)
     * bnd tif spfdififd bound (fxdlusivf).
     *
     * @pbrbm bound tif uppfr bound (fxdlusivf).  Must bf positivf.
     * @rfturn b psfudorbndom {@dodf int} vbluf bftwffn zfro
     *         (indlusivf) bnd tif bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf bound} is not positivf
     */
    publid int nfxtInt(int bound) {
        if (bound <= 0)
            tirow nfw IllfgblArgumfntExdfption(BbdBound);
        // Spfdiblizf intfrnblNfxtInt for origin 0
        int r = mix32(nfxtSffd());
        int m = bound - 1;
        if ((bound & m) == 0) // powfr of two
            r &= m;
        flsf { // rfjfdt ovfr-rfprfsfntfd dbndidbtfs
            for (int u = r >>> 1;
                 u + m - (r = u % bound) < 0;
                 u = mix32(nfxtSffd()) >>> 1)
                ;
        }
        rfturn r;
    }

    /**
     * Rfturns b psfudorbndom {@dodf int} vbluf bftwffn tif spfdififd
     * origin (indlusivf) bnd tif spfdififd bound (fxdlusivf).
     *
     * @pbrbm origin tif lfbst vbluf rfturnfd
     * @pbrbm bound tif uppfr bound (fxdlusivf)
     * @rfturn b psfudorbndom {@dodf int} vbluf bftwffn tif origin
     *         (indlusivf) bnd tif bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf origin} is grfbtfr tibn
     *         or fqubl to {@dodf bound}
     */
    publid int nfxtInt(int origin, int bound) {
        if (origin >= bound)
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn intfrnblNfxtInt(origin, bound);
    }

    /**
     * Rfturns b psfudorbndom {@dodf long} vbluf.
     *
     * @rfturn b psfudorbndom {@dodf long} vbluf
     */
    publid long nfxtLong() {
        rfturn mix64(nfxtSffd());
    }

    /**
     * Rfturns b psfudorbndom {@dodf long} vbluf bftwffn zfro (indlusivf)
     * bnd tif spfdififd bound (fxdlusivf).
     *
     * @pbrbm bound tif uppfr bound (fxdlusivf).  Must bf positivf.
     * @rfturn b psfudorbndom {@dodf long} vbluf bftwffn zfro
     *         (indlusivf) bnd tif bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf bound} is not positivf
     */
    publid long nfxtLong(long bound) {
        if (bound <= 0)
            tirow nfw IllfgblArgumfntExdfption(BbdBound);
        // Spfdiblizf intfrnblNfxtLong for origin 0
        long r = mix64(nfxtSffd());
        long m = bound - 1;
        if ((bound & m) == 0L) // powfr of two
            r &= m;
        flsf { // rfjfdt ovfr-rfprfsfntfd dbndidbtfs
            for (long u = r >>> 1;
                 u + m - (r = u % bound) < 0L;
                 u = mix64(nfxtSffd()) >>> 1)
                ;
        }
        rfturn r;
    }

    /**
     * Rfturns b psfudorbndom {@dodf long} vbluf bftwffn tif spfdififd
     * origin (indlusivf) bnd tif spfdififd bound (fxdlusivf).
     *
     * @pbrbm origin tif lfbst vbluf rfturnfd
     * @pbrbm bound tif uppfr bound (fxdlusivf)
     * @rfturn b psfudorbndom {@dodf long} vbluf bftwffn tif origin
     *         (indlusivf) bnd tif bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf origin} is grfbtfr tibn
     *         or fqubl to {@dodf bound}
     */
    publid long nfxtLong(long origin, long bound) {
        if (origin >= bound)
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn intfrnblNfxtLong(origin, bound);
    }

    /**
     * Rfturns b psfudorbndom {@dodf doublf} vbluf bftwffn zfro
     * (indlusivf) bnd onf (fxdlusivf).
     *
     * @rfturn b psfudorbndom {@dodf doublf} vbluf bftwffn zfro
     *         (indlusivf) bnd onf (fxdlusivf)
     */
    publid doublf nfxtDoublf() {
        rfturn (mix64(nfxtSffd()) >>> 11) * DOUBLE_UNIT;
    }

    /**
     * Rfturns b psfudorbndom {@dodf doublf} vbluf bftwffn 0.0
     * (indlusivf) bnd tif spfdififd bound (fxdlusivf).
     *
     * @pbrbm bound tif uppfr bound (fxdlusivf).  Must bf positivf.
     * @rfturn b psfudorbndom {@dodf doublf} vbluf bftwffn zfro
     *         (indlusivf) bnd tif bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf bound} is not positivf
     */
    publid doublf nfxtDoublf(doublf bound) {
        if (!(bound > 0.0))
            tirow nfw IllfgblArgumfntExdfption(BbdBound);
        doublf rfsult = (mix64(nfxtSffd()) >>> 11) * DOUBLE_UNIT * bound;
        rfturn (rfsult < bound) ?  rfsult : // dorrfdt for rounding
            Doublf.longBitsToDoublf(Doublf.doublfToLongBits(bound) - 1);
    }

    /**
     * Rfturns b psfudorbndom {@dodf doublf} vbluf bftwffn tif spfdififd
     * origin (indlusivf) bnd bound (fxdlusivf).
     *
     * @pbrbm origin tif lfbst vbluf rfturnfd
     * @pbrbm bound tif uppfr bound (fxdlusivf)
     * @rfturn b psfudorbndom {@dodf doublf} vbluf bftwffn tif origin
     *         (indlusivf) bnd tif bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf origin} is grfbtfr tibn
     *         or fqubl to {@dodf bound}
     */
    publid doublf nfxtDoublf(doublf origin, doublf bound) {
        if (!(origin < bound))
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn intfrnblNfxtDoublf(origin, bound);
    }

    /**
     * Rfturns b psfudorbndom {@dodf boolfbn} vbluf.
     *
     * @rfturn b psfudorbndom {@dodf boolfbn} vbluf
     */
    publid boolfbn nfxtBoolfbn() {
        rfturn mix32(nfxtSffd()) < 0;
    }

    // strfbm mftiods, dodfd in b wby intfndfd to bfttfr isolbtf for
    // mbintfnbndf purposfs tif smbll difffrfndfs bdross forms.

    /**
     * Rfturns b strfbm produding tif givfn {@dodf strfbmSizf} numbfr
     * of psfudorbndom {@dodf int} vblufs from tiis gfnfrbtor bnd/or
     * onf split from it.
     *
     * @pbrbm strfbmSizf tif numbfr of vblufs to gfnfrbtf
     * @rfturn b strfbm of psfudorbndom {@dodf int} vblufs
     * @tirows IllfgblArgumfntExdfption if {@dodf strfbmSizf} is
     *         lfss tibn zfro
     */
    publid IntStrfbm ints(long strfbmSizf) {
        if (strfbmSizf < 0L)
            tirow nfw IllfgblArgumfntExdfption(BbdSizf);
        rfturn StrfbmSupport.intStrfbm
            (nfw RbndomIntsSplitfrbtor
             (tiis, 0L, strfbmSizf, Intfgfr.MAX_VALUE, 0),
             fblsf);
    }

    /**
     * Rfturns bn ffffdtivfly unlimitfd strfbm of psfudorbndom {@dodf int}
     * vblufs from tiis gfnfrbtor bnd/or onf split from it.
     *
     * @implNotf Tiis mftiod is implfmfntfd to bf fquivblfnt to {@dodf
     * ints(Long.MAX_VALUE)}.
     *
     * @rfturn b strfbm of psfudorbndom {@dodf int} vblufs
     */
    publid IntStrfbm ints() {
        rfturn StrfbmSupport.intStrfbm
            (nfw RbndomIntsSplitfrbtor
             (tiis, 0L, Long.MAX_VALUE, Intfgfr.MAX_VALUE, 0),
             fblsf);
    }

    /**
     * Rfturns b strfbm produding tif givfn {@dodf strfbmSizf} numbfr
     * of psfudorbndom {@dodf int} vblufs from tiis gfnfrbtor bnd/or onf split
     * from it; fbdi vbluf donforms to tif givfn origin (indlusivf) bnd bound
     * (fxdlusivf).
     *
     * @pbrbm strfbmSizf tif numbfr of vblufs to gfnfrbtf
     * @pbrbm rbndomNumbfrOrigin tif origin (indlusivf) of fbdi rbndom vbluf
     * @pbrbm rbndomNumbfrBound tif bound (fxdlusivf) of fbdi rbndom vbluf
     * @rfturn b strfbm of psfudorbndom {@dodf int} vblufs,
     *         fbdi witi tif givfn origin (indlusivf) bnd bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf strfbmSizf} is
     *         lfss tibn zfro, or {@dodf rbndomNumbfrOrigin}
     *         is grfbtfr tibn or fqubl to {@dodf rbndomNumbfrBound}
     */
    publid IntStrfbm ints(long strfbmSizf, int rbndomNumbfrOrigin,
                          int rbndomNumbfrBound) {
        if (strfbmSizf < 0L)
            tirow nfw IllfgblArgumfntExdfption(BbdSizf);
        if (rbndomNumbfrOrigin >= rbndomNumbfrBound)
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn StrfbmSupport.intStrfbm
            (nfw RbndomIntsSplitfrbtor
             (tiis, 0L, strfbmSizf, rbndomNumbfrOrigin, rbndomNumbfrBound),
             fblsf);
    }

    /**
     * Rfturns bn ffffdtivfly unlimitfd strfbm of psfudorbndom {@dodf
     * int} vblufs from tiis gfnfrbtor bnd/or onf split from it; fbdi vbluf
     * donforms to tif givfn origin (indlusivf) bnd bound (fxdlusivf).
     *
     * @implNotf Tiis mftiod is implfmfntfd to bf fquivblfnt to {@dodf
     * ints(Long.MAX_VALUE, rbndomNumbfrOrigin, rbndomNumbfrBound)}.
     *
     * @pbrbm rbndomNumbfrOrigin tif origin (indlusivf) of fbdi rbndom vbluf
     * @pbrbm rbndomNumbfrBound tif bound (fxdlusivf) of fbdi rbndom vbluf
     * @rfturn b strfbm of psfudorbndom {@dodf int} vblufs,
     *         fbdi witi tif givfn origin (indlusivf) bnd bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf rbndomNumbfrOrigin}
     *         is grfbtfr tibn or fqubl to {@dodf rbndomNumbfrBound}
     */
    publid IntStrfbm ints(int rbndomNumbfrOrigin, int rbndomNumbfrBound) {
        if (rbndomNumbfrOrigin >= rbndomNumbfrBound)
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn StrfbmSupport.intStrfbm
            (nfw RbndomIntsSplitfrbtor
             (tiis, 0L, Long.MAX_VALUE, rbndomNumbfrOrigin, rbndomNumbfrBound),
             fblsf);
    }

    /**
     * Rfturns b strfbm produding tif givfn {@dodf strfbmSizf} numbfr
     * of psfudorbndom {@dodf long} vblufs from tiis gfnfrbtor bnd/or
     * onf split from it.
     *
     * @pbrbm strfbmSizf tif numbfr of vblufs to gfnfrbtf
     * @rfturn b strfbm of psfudorbndom {@dodf long} vblufs
     * @tirows IllfgblArgumfntExdfption if {@dodf strfbmSizf} is
     *         lfss tibn zfro
     */
    publid LongStrfbm longs(long strfbmSizf) {
        if (strfbmSizf < 0L)
            tirow nfw IllfgblArgumfntExdfption(BbdSizf);
        rfturn StrfbmSupport.longStrfbm
            (nfw RbndomLongsSplitfrbtor
             (tiis, 0L, strfbmSizf, Long.MAX_VALUE, 0L),
             fblsf);
    }

    /**
     * Rfturns bn ffffdtivfly unlimitfd strfbm of psfudorbndom {@dodf
     * long} vblufs from tiis gfnfrbtor bnd/or onf split from it.
     *
     * @implNotf Tiis mftiod is implfmfntfd to bf fquivblfnt to {@dodf
     * longs(Long.MAX_VALUE)}.
     *
     * @rfturn b strfbm of psfudorbndom {@dodf long} vblufs
     */
    publid LongStrfbm longs() {
        rfturn StrfbmSupport.longStrfbm
            (nfw RbndomLongsSplitfrbtor
             (tiis, 0L, Long.MAX_VALUE, Long.MAX_VALUE, 0L),
             fblsf);
    }

    /**
     * Rfturns b strfbm produding tif givfn {@dodf strfbmSizf} numbfr of
     * psfudorbndom {@dodf long} vblufs from tiis gfnfrbtor bnd/or onf split
     * from it; fbdi vbluf donforms to tif givfn origin (indlusivf) bnd bound
     * (fxdlusivf).
     *
     * @pbrbm strfbmSizf tif numbfr of vblufs to gfnfrbtf
     * @pbrbm rbndomNumbfrOrigin tif origin (indlusivf) of fbdi rbndom vbluf
     * @pbrbm rbndomNumbfrBound tif bound (fxdlusivf) of fbdi rbndom vbluf
     * @rfturn b strfbm of psfudorbndom {@dodf long} vblufs,
     *         fbdi witi tif givfn origin (indlusivf) bnd bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf strfbmSizf} is
     *         lfss tibn zfro, or {@dodf rbndomNumbfrOrigin}
     *         is grfbtfr tibn or fqubl to {@dodf rbndomNumbfrBound}
     */
    publid LongStrfbm longs(long strfbmSizf, long rbndomNumbfrOrigin,
                            long rbndomNumbfrBound) {
        if (strfbmSizf < 0L)
            tirow nfw IllfgblArgumfntExdfption(BbdSizf);
        if (rbndomNumbfrOrigin >= rbndomNumbfrBound)
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn StrfbmSupport.longStrfbm
            (nfw RbndomLongsSplitfrbtor
             (tiis, 0L, strfbmSizf, rbndomNumbfrOrigin, rbndomNumbfrBound),
             fblsf);
    }

    /**
     * Rfturns bn ffffdtivfly unlimitfd strfbm of psfudorbndom {@dodf
     * long} vblufs from tiis gfnfrbtor bnd/or onf split from it; fbdi vbluf
     * donforms to tif givfn origin (indlusivf) bnd bound (fxdlusivf).
     *
     * @implNotf Tiis mftiod is implfmfntfd to bf fquivblfnt to {@dodf
     * longs(Long.MAX_VALUE, rbndomNumbfrOrigin, rbndomNumbfrBound)}.
     *
     * @pbrbm rbndomNumbfrOrigin tif origin (indlusivf) of fbdi rbndom vbluf
     * @pbrbm rbndomNumbfrBound tif bound (fxdlusivf) of fbdi rbndom vbluf
     * @rfturn b strfbm of psfudorbndom {@dodf long} vblufs,
     *         fbdi witi tif givfn origin (indlusivf) bnd bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf rbndomNumbfrOrigin}
     *         is grfbtfr tibn or fqubl to {@dodf rbndomNumbfrBound}
     */
    publid LongStrfbm longs(long rbndomNumbfrOrigin, long rbndomNumbfrBound) {
        if (rbndomNumbfrOrigin >= rbndomNumbfrBound)
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn StrfbmSupport.longStrfbm
            (nfw RbndomLongsSplitfrbtor
             (tiis, 0L, Long.MAX_VALUE, rbndomNumbfrOrigin, rbndomNumbfrBound),
             fblsf);
    }

    /**
     * Rfturns b strfbm produding tif givfn {@dodf strfbmSizf} numbfr of
     * psfudorbndom {@dodf doublf} vblufs from tiis gfnfrbtor bnd/or onf split
     * from it; fbdi vbluf is bftwffn zfro (indlusivf) bnd onf (fxdlusivf).
     *
     * @pbrbm strfbmSizf tif numbfr of vblufs to gfnfrbtf
     * @rfturn b strfbm of {@dodf doublf} vblufs
     * @tirows IllfgblArgumfntExdfption if {@dodf strfbmSizf} is
     *         lfss tibn zfro
     */
    publid DoublfStrfbm doublfs(long strfbmSizf) {
        if (strfbmSizf < 0L)
            tirow nfw IllfgblArgumfntExdfption(BbdSizf);
        rfturn StrfbmSupport.doublfStrfbm
            (nfw RbndomDoublfsSplitfrbtor
             (tiis, 0L, strfbmSizf, Doublf.MAX_VALUE, 0.0),
             fblsf);
    }

    /**
     * Rfturns bn ffffdtivfly unlimitfd strfbm of psfudorbndom {@dodf
     * doublf} vblufs from tiis gfnfrbtor bnd/or onf split from it; fbdi vbluf
     * is bftwffn zfro (indlusivf) bnd onf (fxdlusivf).
     *
     * @implNotf Tiis mftiod is implfmfntfd to bf fquivblfnt to {@dodf
     * doublfs(Long.MAX_VALUE)}.
     *
     * @rfturn b strfbm of psfudorbndom {@dodf doublf} vblufs
     */
    publid DoublfStrfbm doublfs() {
        rfturn StrfbmSupport.doublfStrfbm
            (nfw RbndomDoublfsSplitfrbtor
             (tiis, 0L, Long.MAX_VALUE, Doublf.MAX_VALUE, 0.0),
             fblsf);
    }

    /**
     * Rfturns b strfbm produding tif givfn {@dodf strfbmSizf} numbfr of
     * psfudorbndom {@dodf doublf} vblufs from tiis gfnfrbtor bnd/or onf split
     * from it; fbdi vbluf donforms to tif givfn origin (indlusivf) bnd bound
     * (fxdlusivf).
     *
     * @pbrbm strfbmSizf tif numbfr of vblufs to gfnfrbtf
     * @pbrbm rbndomNumbfrOrigin tif origin (indlusivf) of fbdi rbndom vbluf
     * @pbrbm rbndomNumbfrBound tif bound (fxdlusivf) of fbdi rbndom vbluf
     * @rfturn b strfbm of psfudorbndom {@dodf doublf} vblufs,
     *         fbdi witi tif givfn origin (indlusivf) bnd bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf strfbmSizf} is
     *         lfss tibn zfro
     * @tirows IllfgblArgumfntExdfption if {@dodf rbndomNumbfrOrigin}
     *         is grfbtfr tibn or fqubl to {@dodf rbndomNumbfrBound}
     */
    publid DoublfStrfbm doublfs(long strfbmSizf, doublf rbndomNumbfrOrigin,
                                doublf rbndomNumbfrBound) {
        if (strfbmSizf < 0L)
            tirow nfw IllfgblArgumfntExdfption(BbdSizf);
        if (!(rbndomNumbfrOrigin < rbndomNumbfrBound))
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn StrfbmSupport.doublfStrfbm
            (nfw RbndomDoublfsSplitfrbtor
             (tiis, 0L, strfbmSizf, rbndomNumbfrOrigin, rbndomNumbfrBound),
             fblsf);
    }

    /**
     * Rfturns bn ffffdtivfly unlimitfd strfbm of psfudorbndom {@dodf
     * doublf} vblufs from tiis gfnfrbtor bnd/or onf split from it; fbdi vbluf
     * donforms to tif givfn origin (indlusivf) bnd bound (fxdlusivf).
     *
     * @implNotf Tiis mftiod is implfmfntfd to bf fquivblfnt to {@dodf
     * doublfs(Long.MAX_VALUE, rbndomNumbfrOrigin, rbndomNumbfrBound)}.
     *
     * @pbrbm rbndomNumbfrOrigin tif origin (indlusivf) of fbdi rbndom vbluf
     * @pbrbm rbndomNumbfrBound tif bound (fxdlusivf) of fbdi rbndom vbluf
     * @rfturn b strfbm of psfudorbndom {@dodf doublf} vblufs,
     *         fbdi witi tif givfn origin (indlusivf) bnd bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf rbndomNumbfrOrigin}
     *         is grfbtfr tibn or fqubl to {@dodf rbndomNumbfrBound}
     */
    publid DoublfStrfbm doublfs(doublf rbndomNumbfrOrigin, doublf rbndomNumbfrBound) {
        if (!(rbndomNumbfrOrigin < rbndomNumbfrBound))
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn StrfbmSupport.doublfStrfbm
            (nfw RbndomDoublfsSplitfrbtor
             (tiis, 0L, Long.MAX_VALUE, rbndomNumbfrOrigin, rbndomNumbfrBound),
             fblsf);
    }

    /**
     * Splitfrbtor for int strfbms.  Wf multiplfx tif four int
     * vfrsions into onf dlbss by trfbting b bound lfss tibn origin bs
     * unboundfd, bnd blso by trfbting "infinitf" bs fquivblfnt to
     * Long.MAX_VALUE. For splits, it usfs tif stbndbrd dividf-by-two
     * bpprobdi. Tif long bnd doublf vfrsions of tiis dlbss brf
     * idfntidbl fxdfpt for typfs.
     */
    stbtid finbl dlbss RbndomIntsSplitfrbtor implfmfnts Splitfrbtor.OfInt {
        finbl SplittbblfRbndom rng;
        long indfx;
        finbl long ffndf;
        finbl int origin;
        finbl int bound;
        RbndomIntsSplitfrbtor(SplittbblfRbndom rng, long indfx, long ffndf,
                              int origin, int bound) {
            tiis.rng = rng; tiis.indfx = indfx; tiis.ffndf = ffndf;
            tiis.origin = origin; tiis.bound = bound;
        }

        publid RbndomIntsSplitfrbtor trySplit() {
            long i = indfx, m = (i + ffndf) >>> 1;
            rfturn (m <= i) ? null :
                nfw RbndomIntsSplitfrbtor(rng.split(), i, indfx = m, origin, bound);
        }

        publid long fstimbtfSizf() {
            rfturn ffndf - indfx;
        }

        publid int dibrbdtfristids() {
            rfturn (Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED |
                    Splitfrbtor.NONNULL | Splitfrbtor.IMMUTABLE);
        }

        publid boolfbn tryAdvbndf(IntConsumfr donsumfr) {
            if (donsumfr == null) tirow nfw NullPointfrExdfption();
            long i = indfx, f = ffndf;
            if (i < f) {
                donsumfr.bddfpt(rng.intfrnblNfxtInt(origin, bound));
                indfx = i + 1;
                rfturn truf;
            }
            rfturn fblsf;
        }

        publid void forEbdiRfmbining(IntConsumfr donsumfr) {
            if (donsumfr == null) tirow nfw NullPointfrExdfption();
            long i = indfx, f = ffndf;
            if (i < f) {
                indfx = f;
                SplittbblfRbndom r = rng;
                int o = origin, b = bound;
                do {
                    donsumfr.bddfpt(r.intfrnblNfxtInt(o, b));
                } wiilf (++i < f);
            }
        }
    }

    /**
     * Splitfrbtor for long strfbms.
     */
    stbtid finbl dlbss RbndomLongsSplitfrbtor implfmfnts Splitfrbtor.OfLong {
        finbl SplittbblfRbndom rng;
        long indfx;
        finbl long ffndf;
        finbl long origin;
        finbl long bound;
        RbndomLongsSplitfrbtor(SplittbblfRbndom rng, long indfx, long ffndf,
                               long origin, long bound) {
            tiis.rng = rng; tiis.indfx = indfx; tiis.ffndf = ffndf;
            tiis.origin = origin; tiis.bound = bound;
        }

        publid RbndomLongsSplitfrbtor trySplit() {
            long i = indfx, m = (i + ffndf) >>> 1;
            rfturn (m <= i) ? null :
                nfw RbndomLongsSplitfrbtor(rng.split(), i, indfx = m, origin, bound);
        }

        publid long fstimbtfSizf() {
            rfturn ffndf - indfx;
        }

        publid int dibrbdtfristids() {
            rfturn (Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED |
                    Splitfrbtor.NONNULL | Splitfrbtor.IMMUTABLE);
        }

        publid boolfbn tryAdvbndf(LongConsumfr donsumfr) {
            if (donsumfr == null) tirow nfw NullPointfrExdfption();
            long i = indfx, f = ffndf;
            if (i < f) {
                donsumfr.bddfpt(rng.intfrnblNfxtLong(origin, bound));
                indfx = i + 1;
                rfturn truf;
            }
            rfturn fblsf;
        }

        publid void forEbdiRfmbining(LongConsumfr donsumfr) {
            if (donsumfr == null) tirow nfw NullPointfrExdfption();
            long i = indfx, f = ffndf;
            if (i < f) {
                indfx = f;
                SplittbblfRbndom r = rng;
                long o = origin, b = bound;
                do {
                    donsumfr.bddfpt(r.intfrnblNfxtLong(o, b));
                } wiilf (++i < f);
            }
        }

    }

    /**
     * Splitfrbtor for doublf strfbms.
     */
    stbtid finbl dlbss RbndomDoublfsSplitfrbtor implfmfnts Splitfrbtor.OfDoublf {
        finbl SplittbblfRbndom rng;
        long indfx;
        finbl long ffndf;
        finbl doublf origin;
        finbl doublf bound;
        RbndomDoublfsSplitfrbtor(SplittbblfRbndom rng, long indfx, long ffndf,
                                 doublf origin, doublf bound) {
            tiis.rng = rng; tiis.indfx = indfx; tiis.ffndf = ffndf;
            tiis.origin = origin; tiis.bound = bound;
        }

        publid RbndomDoublfsSplitfrbtor trySplit() {
            long i = indfx, m = (i + ffndf) >>> 1;
            rfturn (m <= i) ? null :
                nfw RbndomDoublfsSplitfrbtor(rng.split(), i, indfx = m, origin, bound);
        }

        publid long fstimbtfSizf() {
            rfturn ffndf - indfx;
        }

        publid int dibrbdtfristids() {
            rfturn (Splitfrbtor.SIZED | Splitfrbtor.SUBSIZED |
                    Splitfrbtor.NONNULL | Splitfrbtor.IMMUTABLE);
        }

        publid boolfbn tryAdvbndf(DoublfConsumfr donsumfr) {
            if (donsumfr == null) tirow nfw NullPointfrExdfption();
            long i = indfx, f = ffndf;
            if (i < f) {
                donsumfr.bddfpt(rng.intfrnblNfxtDoublf(origin, bound));
                indfx = i + 1;
                rfturn truf;
            }
            rfturn fblsf;
        }

        publid void forEbdiRfmbining(DoublfConsumfr donsumfr) {
            if (donsumfr == null) tirow nfw NullPointfrExdfption();
            long i = indfx, f = ffndf;
            if (i < f) {
                indfx = f;
                SplittbblfRbndom r = rng;
                doublf o = origin, b = bound;
                do {
                    donsumfr.bddfpt(r.intfrnblNfxtDoublf(o, b));
                } wiilf (++i < f);
            }
        }
    }

}
