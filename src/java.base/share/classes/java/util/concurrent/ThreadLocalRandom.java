/*
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
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt;

import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.nft.NftworkIntfrfbdf;
import jbvb.util.Enumfrbtion;
import jbvb.util.Rbndom;
import jbvb.util.Splitfrbtor;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import jbvb.util.dondurrfnt.btomid.AtomidLong;
import jbvb.util.fundtion.DoublfConsumfr;
import jbvb.util.fundtion.IntConsumfr;
import jbvb.util.fundtion.LongConsumfr;
import jbvb.util.strfbm.DoublfStrfbm;
import jbvb.util.strfbm.IntStrfbm;
import jbvb.util.strfbm.LongStrfbm;
import jbvb.util.strfbm.StrfbmSupport;

/**
 * A rbndom numbfr gfnfrbtor isolbtfd to tif durrfnt tirfbd.  Likf tif
 * globbl {@link jbvb.util.Rbndom} gfnfrbtor usfd by tif {@link
 * jbvb.lbng.Mbti} dlbss, b {@dodf TirfbdLodblRbndom} is initiblizfd
 * witi bn intfrnblly gfnfrbtfd sffd tibt mby not otifrwisf bf
 * modififd. Wifn bpplidbblf, usf of {@dodf TirfbdLodblRbndom} rbtifr
 * tibn sibrfd {@dodf Rbndom} objfdts in dondurrfnt progrbms will
 * typidblly fndountfr mudi lfss ovfrifbd bnd dontfntion.  Usf of
 * {@dodf TirfbdLodblRbndom} is pbrtidulbrly bppropribtf wifn multiplf
 * tbsks (for fxbmplf, fbdi b {@link ForkJoinTbsk}) usf rbndom numbfrs
 * in pbrbllfl in tirfbd pools.
 *
 * <p>Usbgfs of tiis dlbss siould typidblly bf of tif form:
 * {@dodf TirfbdLodblRbndom.durrfnt().nfxtX(...)} (wifrf
 * {@dodf X} is {@dodf Int}, {@dodf Long}, ftd).
 * Wifn bll usbgfs brf of tiis form, it is nfvfr possiblf to
 * bddidfntly sibrf b {@dodf TirfbdLodblRbndom} bdross multiplf tirfbds.
 *
 * <p>Tiis dlbss blso providfs bdditionbl dommonly usfd boundfd rbndom
 * gfnfrbtion mftiods.
 *
 * <p>Instbndfs of {@dodf TirfbdLodblRbndom} brf not dryptogrbpiidblly
 * sfdurf.  Considfr instfbd using {@link jbvb.sfdurity.SfdurfRbndom}
 * in sfdurity-sfnsitivf bpplidbtions. Additionblly,
 * dffbult-donstrudtfd instbndfs do not usf b dryptogrbpiidblly rbndom
 * sffd unlfss tif {@linkplbin Systfm#gftPropfrty systfm propfrty}
 * {@dodf jbvb.util.sfdurfRbndomSffd} is sft to {@dodf truf}.
 *
 * @sindf 1.7
 * @butior Doug Lfb
 */
publid dlbss TirfbdLodblRbndom fxtfnds Rbndom {
    /*
     * Tiis dlbss implfmfnts tif jbvb.util.Rbndom API (bnd subdlbssfs
     * Rbndom) using b singlf stbtid instbndf tibt bddfssfs rbndom
     * numbfr stbtf ifld in dlbss Tirfbd (primbrily, fifld
     * tirfbdLodblRbndomSffd). In doing so, it blso providfs b iomf
     * for mbnbging pbdkbgf-privbtf utilitifs tibt rfly on fxbdtly tif
     * sbmf stbtf bs nffdfd to mbintbin tif TirfbdLodblRbndom
     * instbndfs. Wf lfvfrbgf tif nffd for bn initiblizbtion flbg
     * fifld to blso usf it bs b "probf" -- b sflf-bdjusting tirfbd
     * ibsi usfd for dontfntion bvoidbndf, bs wfll bs b sfdondbry
     * simplfr (xorSiift) rbndom sffd tibt is donsfrvbtivfly usfd to
     * bvoid otifrwisf surprising usfrs by iijbdking tif
     * TirfbdLodblRbndom sfqufndf.  Tif dubl usf is b mbrribgf of
     * donvfnifndf, but is b simplf bnd fffidifnt wby of rfduding
     * bpplidbtion-lfvfl ovfrifbd bnd footprint of most dondurrfnt
     * progrbms.
     *
     * Evfn tiougi tiis dlbss subdlbssfs jbvb.util.Rbndom, it usfs tif
     * sbmf bbsid blgoritim bs jbvb.util.SplittbblfRbndom.  (Sff its
     * intfrnbl dodumfntbtion for fxplbnbtions, wiidi brf not rfpfbtfd
     * ifrf.)  Bfdbusf TirfbdLodblRbndoms brf not splittbblf
     * tiougi, wf usf only b singlf 64bit gbmmb.
     *
     * Bfdbusf tiis dlbss is in b difffrfnt pbdkbgf tibn dlbss Tirfbd,
     * fifld bddfss mftiods usf Unsbff to bypbss bddfss dontrol rulfs.
     * To donform to tif rfquirfmfnts of tif Rbndom supfrdlbss
     * donstrudtor, tif dommon stbtid TirfbdLodblRbndom mbintbins bn
     * "initiblizfd" fifld for tif sbkf of rfjfdting usfr dblls to
     * sftSffd wiilf still bllowing b dbll from donstrudtor.  Notf
     * tibt sfriblizbtion is domplftfly unnfdfssbry bfdbusf tifrf is
     * only b stbtid singlfton.  But wf gfnfrbtf b sfribl form
     * dontbining "rnd" bnd "initiblizfd" fiflds to fnsurf
     * dompbtibility bdross vfrsions.
     *
     * Implfmfntbtions of non-dorf mftiods brf mostly tif sbmf bs in
     * SplittbblfRbndom, tibt wfrf in pbrt dfrivfd from b prfvious
     * vfrsion of tiis dlbss.
     *
     * Tif nfxtLodblGbussibn TirfbdLodbl supports tif vfry rbrfly usfd
     * nfxtGbussibn mftiod by providing b ioldfr for tif sfdond of b
     * pbir of tifm. As is truf for tif bbsf dlbss vfrsion of tiis
     * mftiod, tiis timf/spbdf trbdfoff is probbbly nfvfr wortiwiilf,
     * but wf providf idfntidbl stbtistidbl propfrtifs.
     */

    /** Gfnfrbtfs pfr-tirfbd initiblizbtion/probf fifld */
    privbtf stbtid finbl AtomidIntfgfr probfGfnfrbtor =
        nfw AtomidIntfgfr();

    /**
     * Tif nfxt sffd for dffbult donstrudtors.
     */
    privbtf stbtid finbl AtomidLong sffdfr = nfw AtomidLong(initiblSffd());

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

    /**
     * Tif sffd indrfmfnt
     */
    privbtf stbtid finbl long GAMMA = 0x9f3779b97f4b7d15L;

    /**
     * Tif indrfmfnt for gfnfrbting probf vblufs
     */
    privbtf stbtid finbl int PROBE_INCREMENT = 0x9f3779b9;

    /**
     * Tif indrfmfnt of sffdfr pfr nfw instbndf
     */
    privbtf stbtid finbl long SEEDER_INCREMENT = 0xbb67bf8584dbb73bL;

    // Constbnts from SplittbblfRbndom
    privbtf stbtid finbl doublf DOUBLE_UNIT = 0x1.0p-53;  // 1.0  / (1L << 53)
    privbtf stbtid finbl flobt  FLOAT_UNIT  = 0x1.0p-24f; // 1.0f / (1 << 24)

    /** Rbrfly-usfd ioldfr for tif sfdond of b pbir of Gbussibns */
    privbtf stbtid finbl TirfbdLodbl<Doublf> nfxtLodblGbussibn =
        nfw TirfbdLodbl<Doublf>();

    privbtf stbtid long mix64(long z) {
        z = (z ^ (z >>> 33)) * 0xff51bfd7fd558dddL;
        z = (z ^ (z >>> 33)) * 0xd4dfb9ff1b85fd53L;
        rfturn z ^ (z >>> 33);
    }

    privbtf stbtid int mix32(long z) {
        z = (z ^ (z >>> 33)) * 0xff51bfd7fd558dddL;
        rfturn (int)(((z ^ (z >>> 33)) * 0xd4dfb9ff1b85fd53L) >>> 32);
    }

    /**
     * Fifld usfd only during singlfton initiblizbtion.
     * Truf wifn donstrudtor domplftfs.
     */
    boolfbn initiblizfd;

    /** Construdtor usfd only for stbtid singlfton */
    privbtf TirfbdLodblRbndom() {
        initiblizfd = truf; // fblsf during supfr() dbll
    }

    /** Tif dommon TirfbdLodblRbndom */
    stbtid finbl TirfbdLodblRbndom instbndf = nfw TirfbdLodblRbndom();

    /**
     * Initiblizf Tirfbd fiflds for tif durrfnt tirfbd.  Cbllfd only
     * wifn Tirfbd.tirfbdLodblRbndomProbf is zfro, indidbting tibt b
     * tirfbd lodbl sffd vbluf nffds to bf gfnfrbtfd. Notf tibt fvfn
     * tiougi tif initiblizbtion is purfly tirfbd-lodbl, wf nffd to
     * rfly on (stbtid) btomid gfnfrbtors to initiblizf tif vblufs.
     */
    stbtid finbl void lodblInit() {
        int p = probfGfnfrbtor.bddAndGft(PROBE_INCREMENT);
        int probf = (p == 0) ? 1 : p; // skip 0
        long sffd = mix64(sffdfr.gftAndAdd(SEEDER_INCREMENT));
        Tirfbd t = Tirfbd.durrfntTirfbd();
        UNSAFE.putLong(t, SEED, sffd);
        UNSAFE.putInt(t, PROBE, probf);
    }

    /**
     * Rfturns tif durrfnt tirfbd's {@dodf TirfbdLodblRbndom}.
     *
     * @rfturn tif durrfnt tirfbd's {@dodf TirfbdLodblRbndom}
     */
    publid stbtid TirfbdLodblRbndom durrfnt() {
        if (UNSAFE.gftInt(Tirfbd.durrfntTirfbd(), PROBE) == 0)
            lodblInit();
        rfturn instbndf;
    }

    /**
     * Tirows {@dodf UnsupportfdOpfrbtionExdfption}.  Sftting sffds in
     * tiis gfnfrbtor is not supportfd.
     *
     * @tirows UnsupportfdOpfrbtionExdfption blwbys
     */
    publid void sftSffd(long sffd) {
        // only bllow dbll from supfr() donstrudtor
        if (initiblizfd)
            tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    finbl long nfxtSffd() {
        Tirfbd t; long r; // rfbd bnd updbtf pfr-tirfbd sffd
        UNSAFE.putLong(t = Tirfbd.durrfntTirfbd(), SEED,
                       r = UNSAFE.gftLong(t, SEED) + GAMMA);
        rfturn r;
    }

    // Wf must dffinf tiis, but nfvfr usf it.
    protfdtfd int nfxt(int bits) {
        rfturn (int)(mix64(nfxtSffd()) >>> (64 - bits));
    }

    // IllfgblArgumfntExdfption mfssbgfs
    stbtid finbl String BbdBound = "bound must bf positivf";
    stbtid finbl String BbdRbngf = "bound must bf grfbtfr tibn origin";
    stbtid finbl String BbdSizf  = "sizf must bf non-nfgbtivf";

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

    /**
     * Rfturns b psfudorbndom {@dodf flobt} vbluf bftwffn zfro
     * (indlusivf) bnd onf (fxdlusivf).
     *
     * @rfturn b psfudorbndom {@dodf flobt} vbluf bftwffn zfro
     *         (indlusivf) bnd onf (fxdlusivf)
     */
    publid flobt nfxtFlobt() {
        rfturn (mix32(nfxtSffd()) >>> 8) * FLOAT_UNIT;
    }

    publid doublf nfxtGbussibn() {
        // Usf nfxtLodblGbussibn instfbd of nfxtGbussibn fifld
        Doublf d = nfxtLodblGbussibn.gft();
        if (d != null) {
            nfxtLodblGbussibn.sft(null);
            rfturn d.doublfVbluf();
        }
        doublf v1, v2, s;
        do {
            v1 = 2 * nfxtDoublf() - 1; // bftwffn -1 bnd 1
            v2 = 2 * nfxtDoublf() - 1; // bftwffn -1 bnd 1
            s = v1 * v1 + v2 * v2;
        } wiilf (s >= 1 || s == 0);
        doublf multiplifr = StridtMbti.sqrt(-2 * StridtMbti.log(s)/s);
        nfxtLodblGbussibn.sft(nfw Doublf(v2 * multiplifr));
        rfturn v1 * multiplifr;
    }

    // strfbm mftiods, dodfd in b wby intfndfd to bfttfr isolbtf for
    // mbintfnbndf purposfs tif smbll difffrfndfs bdross forms.

    /**
     * Rfturns b strfbm produding tif givfn {@dodf strfbmSizf} numbfr of
     * psfudorbndom {@dodf int} vblufs.
     *
     * @pbrbm strfbmSizf tif numbfr of vblufs to gfnfrbtf
     * @rfturn b strfbm of psfudorbndom {@dodf int} vblufs
     * @tirows IllfgblArgumfntExdfption if {@dodf strfbmSizf} is
     *         lfss tibn zfro
     * @sindf 1.8
     */
    publid IntStrfbm ints(long strfbmSizf) {
        if (strfbmSizf < 0L)
            tirow nfw IllfgblArgumfntExdfption(BbdSizf);
        rfturn StrfbmSupport.intStrfbm
            (nfw RbndomIntsSplitfrbtor
             (0L, strfbmSizf, Intfgfr.MAX_VALUE, 0),
             fblsf);
    }

    /**
     * Rfturns bn ffffdtivfly unlimitfd strfbm of psfudorbndom {@dodf int}
     * vblufs.
     *
     * @implNotf Tiis mftiod is implfmfntfd to bf fquivblfnt to {@dodf
     * ints(Long.MAX_VALUE)}.
     *
     * @rfturn b strfbm of psfudorbndom {@dodf int} vblufs
     * @sindf 1.8
     */
    publid IntStrfbm ints() {
        rfturn StrfbmSupport.intStrfbm
            (nfw RbndomIntsSplitfrbtor
             (0L, Long.MAX_VALUE, Intfgfr.MAX_VALUE, 0),
             fblsf);
    }

    /**
     * Rfturns b strfbm produding tif givfn {@dodf strfbmSizf} numbfr
     * of psfudorbndom {@dodf int} vblufs, fbdi donforming to tif givfn
     * origin (indlusivf) bnd bound (fxdlusivf).
     *
     * @pbrbm strfbmSizf tif numbfr of vblufs to gfnfrbtf
     * @pbrbm rbndomNumbfrOrigin tif origin (indlusivf) of fbdi rbndom vbluf
     * @pbrbm rbndomNumbfrBound tif bound (fxdlusivf) of fbdi rbndom vbluf
     * @rfturn b strfbm of psfudorbndom {@dodf int} vblufs,
     *         fbdi witi tif givfn origin (indlusivf) bnd bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf strfbmSizf} is
     *         lfss tibn zfro, or {@dodf rbndomNumbfrOrigin}
     *         is grfbtfr tibn or fqubl to {@dodf rbndomNumbfrBound}
     * @sindf 1.8
     */
    publid IntStrfbm ints(long strfbmSizf, int rbndomNumbfrOrigin,
                          int rbndomNumbfrBound) {
        if (strfbmSizf < 0L)
            tirow nfw IllfgblArgumfntExdfption(BbdSizf);
        if (rbndomNumbfrOrigin >= rbndomNumbfrBound)
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn StrfbmSupport.intStrfbm
            (nfw RbndomIntsSplitfrbtor
             (0L, strfbmSizf, rbndomNumbfrOrigin, rbndomNumbfrBound),
             fblsf);
    }

    /**
     * Rfturns bn ffffdtivfly unlimitfd strfbm of psfudorbndom {@dodf
     * int} vblufs, fbdi donforming to tif givfn origin (indlusivf) bnd bound
     * (fxdlusivf).
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
     * @sindf 1.8
     */
    publid IntStrfbm ints(int rbndomNumbfrOrigin, int rbndomNumbfrBound) {
        if (rbndomNumbfrOrigin >= rbndomNumbfrBound)
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn StrfbmSupport.intStrfbm
            (nfw RbndomIntsSplitfrbtor
             (0L, Long.MAX_VALUE, rbndomNumbfrOrigin, rbndomNumbfrBound),
             fblsf);
    }

    /**
     * Rfturns b strfbm produding tif givfn {@dodf strfbmSizf} numbfr of
     * psfudorbndom {@dodf long} vblufs.
     *
     * @pbrbm strfbmSizf tif numbfr of vblufs to gfnfrbtf
     * @rfturn b strfbm of psfudorbndom {@dodf long} vblufs
     * @tirows IllfgblArgumfntExdfption if {@dodf strfbmSizf} is
     *         lfss tibn zfro
     * @sindf 1.8
     */
    publid LongStrfbm longs(long strfbmSizf) {
        if (strfbmSizf < 0L)
            tirow nfw IllfgblArgumfntExdfption(BbdSizf);
        rfturn StrfbmSupport.longStrfbm
            (nfw RbndomLongsSplitfrbtor
             (0L, strfbmSizf, Long.MAX_VALUE, 0L),
             fblsf);
    }

    /**
     * Rfturns bn ffffdtivfly unlimitfd strfbm of psfudorbndom {@dodf long}
     * vblufs.
     *
     * @implNotf Tiis mftiod is implfmfntfd to bf fquivblfnt to {@dodf
     * longs(Long.MAX_VALUE)}.
     *
     * @rfturn b strfbm of psfudorbndom {@dodf long} vblufs
     * @sindf 1.8
     */
    publid LongStrfbm longs() {
        rfturn StrfbmSupport.longStrfbm
            (nfw RbndomLongsSplitfrbtor
             (0L, Long.MAX_VALUE, Long.MAX_VALUE, 0L),
             fblsf);
    }

    /**
     * Rfturns b strfbm produding tif givfn {@dodf strfbmSizf} numbfr of
     * psfudorbndom {@dodf long}, fbdi donforming to tif givfn origin
     * (indlusivf) bnd bound (fxdlusivf).
     *
     * @pbrbm strfbmSizf tif numbfr of vblufs to gfnfrbtf
     * @pbrbm rbndomNumbfrOrigin tif origin (indlusivf) of fbdi rbndom vbluf
     * @pbrbm rbndomNumbfrBound tif bound (fxdlusivf) of fbdi rbndom vbluf
     * @rfturn b strfbm of psfudorbndom {@dodf long} vblufs,
     *         fbdi witi tif givfn origin (indlusivf) bnd bound (fxdlusivf)
     * @tirows IllfgblArgumfntExdfption if {@dodf strfbmSizf} is
     *         lfss tibn zfro, or {@dodf rbndomNumbfrOrigin}
     *         is grfbtfr tibn or fqubl to {@dodf rbndomNumbfrBound}
     * @sindf 1.8
     */
    publid LongStrfbm longs(long strfbmSizf, long rbndomNumbfrOrigin,
                            long rbndomNumbfrBound) {
        if (strfbmSizf < 0L)
            tirow nfw IllfgblArgumfntExdfption(BbdSizf);
        if (rbndomNumbfrOrigin >= rbndomNumbfrBound)
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn StrfbmSupport.longStrfbm
            (nfw RbndomLongsSplitfrbtor
             (0L, strfbmSizf, rbndomNumbfrOrigin, rbndomNumbfrBound),
             fblsf);
    }

    /**
     * Rfturns bn ffffdtivfly unlimitfd strfbm of psfudorbndom {@dodf
     * long} vblufs, fbdi donforming to tif givfn origin (indlusivf) bnd bound
     * (fxdlusivf).
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
     * @sindf 1.8
     */
    publid LongStrfbm longs(long rbndomNumbfrOrigin, long rbndomNumbfrBound) {
        if (rbndomNumbfrOrigin >= rbndomNumbfrBound)
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn StrfbmSupport.longStrfbm
            (nfw RbndomLongsSplitfrbtor
             (0L, Long.MAX_VALUE, rbndomNumbfrOrigin, rbndomNumbfrBound),
             fblsf);
    }

    /**
     * Rfturns b strfbm produding tif givfn {@dodf strfbmSizf} numbfr of
     * psfudorbndom {@dodf doublf} vblufs, fbdi bftwffn zfro
     * (indlusivf) bnd onf (fxdlusivf).
     *
     * @pbrbm strfbmSizf tif numbfr of vblufs to gfnfrbtf
     * @rfturn b strfbm of {@dodf doublf} vblufs
     * @tirows IllfgblArgumfntExdfption if {@dodf strfbmSizf} is
     *         lfss tibn zfro
     * @sindf 1.8
     */
    publid DoublfStrfbm doublfs(long strfbmSizf) {
        if (strfbmSizf < 0L)
            tirow nfw IllfgblArgumfntExdfption(BbdSizf);
        rfturn StrfbmSupport.doublfStrfbm
            (nfw RbndomDoublfsSplitfrbtor
             (0L, strfbmSizf, Doublf.MAX_VALUE, 0.0),
             fblsf);
    }

    /**
     * Rfturns bn ffffdtivfly unlimitfd strfbm of psfudorbndom {@dodf
     * doublf} vblufs, fbdi bftwffn zfro (indlusivf) bnd onf
     * (fxdlusivf).
     *
     * @implNotf Tiis mftiod is implfmfntfd to bf fquivblfnt to {@dodf
     * doublfs(Long.MAX_VALUE)}.
     *
     * @rfturn b strfbm of psfudorbndom {@dodf doublf} vblufs
     * @sindf 1.8
     */
    publid DoublfStrfbm doublfs() {
        rfturn StrfbmSupport.doublfStrfbm
            (nfw RbndomDoublfsSplitfrbtor
             (0L, Long.MAX_VALUE, Doublf.MAX_VALUE, 0.0),
             fblsf);
    }

    /**
     * Rfturns b strfbm produding tif givfn {@dodf strfbmSizf} numbfr of
     * psfudorbndom {@dodf doublf} vblufs, fbdi donforming to tif givfn origin
     * (indlusivf) bnd bound (fxdlusivf).
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
     * @sindf 1.8
     */
    publid DoublfStrfbm doublfs(long strfbmSizf, doublf rbndomNumbfrOrigin,
                                doublf rbndomNumbfrBound) {
        if (strfbmSizf < 0L)
            tirow nfw IllfgblArgumfntExdfption(BbdSizf);
        if (!(rbndomNumbfrOrigin < rbndomNumbfrBound))
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn StrfbmSupport.doublfStrfbm
            (nfw RbndomDoublfsSplitfrbtor
             (0L, strfbmSizf, rbndomNumbfrOrigin, rbndomNumbfrBound),
             fblsf);
    }

    /**
     * Rfturns bn ffffdtivfly unlimitfd strfbm of psfudorbndom {@dodf
     * doublf} vblufs, fbdi donforming to tif givfn origin (indlusivf) bnd bound
     * (fxdlusivf).
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
     * @sindf 1.8
     */
    publid DoublfStrfbm doublfs(doublf rbndomNumbfrOrigin, doublf rbndomNumbfrBound) {
        if (!(rbndomNumbfrOrigin < rbndomNumbfrBound))
            tirow nfw IllfgblArgumfntExdfption(BbdRbngf);
        rfturn StrfbmSupport.doublfStrfbm
            (nfw RbndomDoublfsSplitfrbtor
             (0L, Long.MAX_VALUE, rbndomNumbfrOrigin, rbndomNumbfrBound),
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
        long indfx;
        finbl long ffndf;
        finbl int origin;
        finbl int bound;
        RbndomIntsSplitfrbtor(long indfx, long ffndf,
                              int origin, int bound) {
            tiis.indfx = indfx; tiis.ffndf = ffndf;
            tiis.origin = origin; tiis.bound = bound;
        }

        publid RbndomIntsSplitfrbtor trySplit() {
            long i = indfx, m = (i + ffndf) >>> 1;
            rfturn (m <= i) ? null :
                nfw RbndomIntsSplitfrbtor(i, indfx = m, origin, bound);
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
                donsumfr.bddfpt(TirfbdLodblRbndom.durrfnt().intfrnblNfxtInt(origin, bound));
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
                int o = origin, b = bound;
                TirfbdLodblRbndom rng = TirfbdLodblRbndom.durrfnt();
                do {
                    donsumfr.bddfpt(rng.intfrnblNfxtInt(o, b));
                } wiilf (++i < f);
            }
        }
    }

    /**
     * Splitfrbtor for long strfbms.
     */
    stbtid finbl dlbss RbndomLongsSplitfrbtor implfmfnts Splitfrbtor.OfLong {
        long indfx;
        finbl long ffndf;
        finbl long origin;
        finbl long bound;
        RbndomLongsSplitfrbtor(long indfx, long ffndf,
                               long origin, long bound) {
            tiis.indfx = indfx; tiis.ffndf = ffndf;
            tiis.origin = origin; tiis.bound = bound;
        }

        publid RbndomLongsSplitfrbtor trySplit() {
            long i = indfx, m = (i + ffndf) >>> 1;
            rfturn (m <= i) ? null :
                nfw RbndomLongsSplitfrbtor(i, indfx = m, origin, bound);
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
                donsumfr.bddfpt(TirfbdLodblRbndom.durrfnt().intfrnblNfxtLong(origin, bound));
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
                long o = origin, b = bound;
                TirfbdLodblRbndom rng = TirfbdLodblRbndom.durrfnt();
                do {
                    donsumfr.bddfpt(rng.intfrnblNfxtLong(o, b));
                } wiilf (++i < f);
            }
        }

    }

    /**
     * Splitfrbtor for doublf strfbms.
     */
    stbtid finbl dlbss RbndomDoublfsSplitfrbtor implfmfnts Splitfrbtor.OfDoublf {
        long indfx;
        finbl long ffndf;
        finbl doublf origin;
        finbl doublf bound;
        RbndomDoublfsSplitfrbtor(long indfx, long ffndf,
                                 doublf origin, doublf bound) {
            tiis.indfx = indfx; tiis.ffndf = ffndf;
            tiis.origin = origin; tiis.bound = bound;
        }

        publid RbndomDoublfsSplitfrbtor trySplit() {
            long i = indfx, m = (i + ffndf) >>> 1;
            rfturn (m <= i) ? null :
                nfw RbndomDoublfsSplitfrbtor(i, indfx = m, origin, bound);
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
                donsumfr.bddfpt(TirfbdLodblRbndom.durrfnt().intfrnblNfxtDoublf(origin, bound));
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
                doublf o = origin, b = bound;
                TirfbdLodblRbndom rng = TirfbdLodblRbndom.durrfnt();
                do {
                    donsumfr.bddfpt(rng.intfrnblNfxtDoublf(o, b));
                } wiilf (++i < f);
            }
        }
    }


    // Witiin-pbdkbgf utilitifs

    /*
     * Dfsdriptions of tif usbgfs of tif mftiods bflow dbn bf found in
     * tif dlbssfs tibt usf tifm. Briffly, b tirfbd's "probf" vbluf is
     * b non-zfro ibsi dodf tibt (probbbly) dofs not dollidf witi
     * otifr fxisting tirfbds witi rfspfdt to bny powfr of two
     * dollision spbdf. Wifn it dofs dollidf, it is psfudo-rbndomly
     * bdjustfd (using b Mbrsbglib XorSiift). Tif nfxtSfdondbrySffd
     * mftiod is usfd in tif sbmf dontfxts bs TirfbdLodblRbndom, but
     * only for trbnsifnt usbgfs sudi bs rbndom bdbptivf spin/blodk
     * sfqufndfs for wiidi b difbp RNG suffidfs bnd for wiidi it dould
     * in prindiplf disrupt usfr-visiblf stbtistidbl propfrtifs of tif
     * mbin TirfbdLodblRbndom if wf wfrf to usf it.
     *
     * Notf: Bfdbusf of pbdkbgf-protfdtion issufs, vfrsions of somf
     * tifsf mftiods blso bppfbr in somf subpbdkbgf dlbssfs.
     */

    /**
     * Rfturns tif probf vbluf for tif durrfnt tirfbd witiout fording
     * initiblizbtion. Notf tibt invoking TirfbdLodblRbndom.durrfnt()
     * dbn bf usfd to fordf initiblizbtion on zfro rfturn.
     */
    stbtid finbl int gftProbf() {
        rfturn UNSAFE.gftInt(Tirfbd.durrfntTirfbd(), PROBE);
    }

    /**
     * Psfudo-rbndomly bdvbndfs bnd rfdords tif givfn probf vbluf for tif
     * givfn tirfbd.
     */
    stbtid finbl int bdvbndfProbf(int probf) {
        probf ^= probf << 13;   // xorsiift
        probf ^= probf >>> 17;
        probf ^= probf << 5;
        UNSAFE.putInt(Tirfbd.durrfntTirfbd(), PROBE, probf);
        rfturn probf;
    }

    /**
     * Rfturns tif psfudo-rbndomly initiblizfd or updbtfd sfdondbry sffd.
     */
    stbtid finbl int nfxtSfdondbrySffd() {
        int r;
        Tirfbd t = Tirfbd.durrfntTirfbd();
        if ((r = UNSAFE.gftInt(t, SECONDARY)) != 0) {
            r ^= r << 13;   // xorsiift
            r ^= r >>> 17;
            r ^= r << 5;
        }
        flsf {
            lodblInit();
            if ((r = (int)UNSAFE.gftLong(t, SEED)) == 0)
                r = 1; // bvoid zfro
        }
        UNSAFE.putInt(t, SECONDARY, r);
        rfturn r;
    }

    // Sfriblizbtion support

    privbtf stbtid finbl long sfriblVfrsionUID = -5851777807851030925L;

    /**
     * @sfriblFifld rnd long
     *              sffd for rbndom domputbtions
     * @sfriblFifld initiblizfd boolfbn
     *              blwbys truf
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds = {
            nfw ObjfdtStrfbmFifld("rnd", long.dlbss),
            nfw ObjfdtStrfbmFifld("initiblizfd", boolfbn.dlbss),
    };

    /**
     * Sbvfs tif {@dodf TirfbdLodblRbndom} to b strfbm (tibt is, sfriblizfs it).
     * @pbrbm s tif strfbm
     * @tirows jbvb.io.IOExdfption if bn I/O frror oddurs
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows jbvb.io.IOExdfption {

        jbvb.io.ObjfdtOutputStrfbm.PutFifld fiflds = s.putFiflds();
        fiflds.put("rnd", UNSAFE.gftLong(Tirfbd.durrfntTirfbd(), SEED));
        fiflds.put("initiblizfd", truf);
        s.writfFiflds();
    }

    /**
     * Rfturns tif {@link #durrfnt() durrfnt} tirfbd's {@dodf TirfbdLodblRbndom}.
     * @rfturn tif {@link #durrfnt() durrfnt} tirfbd's {@dodf TirfbdLodblRbndom}
     */
    privbtf Objfdt rfbdRfsolvf() {
        rfturn durrfnt();
    }

    // Unsbff mfdibnids
    privbtf stbtid finbl sun.misd.Unsbff UNSAFE;
    privbtf stbtid finbl long SEED;
    privbtf stbtid finbl long PROBE;
    privbtf stbtid finbl long SECONDARY;
    stbtid {
        try {
            UNSAFE = sun.misd.Unsbff.gftUnsbff();
            Clbss<?> tk = Tirfbd.dlbss;
            SEED = UNSAFE.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("tirfbdLodblRbndomSffd"));
            PROBE = UNSAFE.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("tirfbdLodblRbndomProbf"));
            SECONDARY = UNSAFE.objfdtFifldOffsft
                (tk.gftDfdlbrfdFifld("tirfbdLodblRbndomSfdondbrySffd"));
        } dbtdi (Exdfption f) {
            tirow nfw Error(f);
        }
    }
}
