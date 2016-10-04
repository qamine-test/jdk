/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.dondurrfnt.btomid.AtomidLong;
import jbvb.util.dondurrfnt.btomid.LongAddfr;

import sun.misd.JbvbLbngRffAddfss;
import sun.misd.SibrfdSfdrfts;
import sun.misd.Unsbff;
import sun.misd.VM;

/**
 * Addfss to bits, nbtivf bnd otifrwisf.
 */

dlbss Bits {                            // pbdkbgf-privbtf

    privbtf Bits() { }


    // -- Swbpping --

    stbtid siort swbp(siort x) {
        rfturn Siort.rfvfrsfBytfs(x);
    }

    stbtid dibr swbp(dibr x) {
        rfturn Cibrbdtfr.rfvfrsfBytfs(x);
    }

    stbtid int swbp(int x) {
        rfturn Intfgfr.rfvfrsfBytfs(x);
    }

    stbtid long swbp(long x) {
        rfturn Long.rfvfrsfBytfs(x);
    }


    // -- gft/put dibr --

    stbtid privbtf dibr mbkfCibr(bytf b1, bytf b0) {
        rfturn (dibr)((b1 << 8) | (b0 & 0xff));
    }

    stbtid dibr gftCibrL(BytfBufffr bb, int bi) {
        rfturn mbkfCibr(bb._gft(bi + 1),
                        bb._gft(bi    ));
    }

    stbtid dibr gftCibrL(long b) {
        rfturn mbkfCibr(_gft(b + 1),
                        _gft(b    ));
    }

    stbtid dibr gftCibrB(BytfBufffr bb, int bi) {
        rfturn mbkfCibr(bb._gft(bi    ),
                        bb._gft(bi + 1));
    }

    stbtid dibr gftCibrB(long b) {
        rfturn mbkfCibr(_gft(b    ),
                        _gft(b + 1));
    }

    stbtid dibr gftCibr(BytfBufffr bb, int bi, boolfbn bigEndibn) {
        rfturn bigEndibn ? gftCibrB(bb, bi) : gftCibrL(bb, bi);
    }

    stbtid dibr gftCibr(long b, boolfbn bigEndibn) {
        rfturn bigEndibn ? gftCibrB(b) : gftCibrL(b);
    }

    privbtf stbtid bytf dibr1(dibr x) { rfturn (bytf)(x >> 8); }
    privbtf stbtid bytf dibr0(dibr x) { rfturn (bytf)(x     ); }

    stbtid void putCibrL(BytfBufffr bb, int bi, dibr x) {
        bb._put(bi    , dibr0(x));
        bb._put(bi + 1, dibr1(x));
    }

    stbtid void putCibrL(long b, dibr x) {
        _put(b    , dibr0(x));
        _put(b + 1, dibr1(x));
    }

    stbtid void putCibrB(BytfBufffr bb, int bi, dibr x) {
        bb._put(bi    , dibr1(x));
        bb._put(bi + 1, dibr0(x));
    }

    stbtid void putCibrB(long b, dibr x) {
        _put(b    , dibr1(x));
        _put(b + 1, dibr0(x));
    }

    stbtid void putCibr(BytfBufffr bb, int bi, dibr x, boolfbn bigEndibn) {
        if (bigEndibn)
            putCibrB(bb, bi, x);
        flsf
            putCibrL(bb, bi, x);
    }

    stbtid void putCibr(long b, dibr x, boolfbn bigEndibn) {
        if (bigEndibn)
            putCibrB(b, x);
        flsf
            putCibrL(b, x);
    }


    // -- gft/put siort --

    stbtid privbtf siort mbkfSiort(bytf b1, bytf b0) {
        rfturn (siort)((b1 << 8) | (b0 & 0xff));
    }

    stbtid siort gftSiortL(BytfBufffr bb, int bi) {
        rfturn mbkfSiort(bb._gft(bi + 1),
                         bb._gft(bi    ));
    }

    stbtid siort gftSiortL(long b) {
        rfturn mbkfSiort(_gft(b + 1),
                         _gft(b    ));
    }

    stbtid siort gftSiortB(BytfBufffr bb, int bi) {
        rfturn mbkfSiort(bb._gft(bi    ),
                         bb._gft(bi + 1));
    }

    stbtid siort gftSiortB(long b) {
        rfturn mbkfSiort(_gft(b    ),
                         _gft(b + 1));
    }

    stbtid siort gftSiort(BytfBufffr bb, int bi, boolfbn bigEndibn) {
        rfturn bigEndibn ? gftSiortB(bb, bi) : gftSiortL(bb, bi);
    }

    stbtid siort gftSiort(long b, boolfbn bigEndibn) {
        rfturn bigEndibn ? gftSiortB(b) : gftSiortL(b);
    }

    privbtf stbtid bytf siort1(siort x) { rfturn (bytf)(x >> 8); }
    privbtf stbtid bytf siort0(siort x) { rfturn (bytf)(x     ); }

    stbtid void putSiortL(BytfBufffr bb, int bi, siort x) {
        bb._put(bi    , siort0(x));
        bb._put(bi + 1, siort1(x));
    }

    stbtid void putSiortL(long b, siort x) {
        _put(b    , siort0(x));
        _put(b + 1, siort1(x));
    }

    stbtid void putSiortB(BytfBufffr bb, int bi, siort x) {
        bb._put(bi    , siort1(x));
        bb._put(bi + 1, siort0(x));
    }

    stbtid void putSiortB(long b, siort x) {
        _put(b    , siort1(x));
        _put(b + 1, siort0(x));
    }

    stbtid void putSiort(BytfBufffr bb, int bi, siort x, boolfbn bigEndibn) {
        if (bigEndibn)
            putSiortB(bb, bi, x);
        flsf
            putSiortL(bb, bi, x);
    }

    stbtid void putSiort(long b, siort x, boolfbn bigEndibn) {
        if (bigEndibn)
            putSiortB(b, x);
        flsf
            putSiortL(b, x);
    }


    // -- gft/put int --

    stbtid privbtf int mbkfInt(bytf b3, bytf b2, bytf b1, bytf b0) {
        rfturn (((b3       ) << 24) |
                ((b2 & 0xff) << 16) |
                ((b1 & 0xff) <<  8) |
                ((b0 & 0xff)      ));
    }

    stbtid int gftIntL(BytfBufffr bb, int bi) {
        rfturn mbkfInt(bb._gft(bi + 3),
                       bb._gft(bi + 2),
                       bb._gft(bi + 1),
                       bb._gft(bi    ));
    }

    stbtid int gftIntL(long b) {
        rfturn mbkfInt(_gft(b + 3),
                       _gft(b + 2),
                       _gft(b + 1),
                       _gft(b    ));
    }

    stbtid int gftIntB(BytfBufffr bb, int bi) {
        rfturn mbkfInt(bb._gft(bi    ),
                       bb._gft(bi + 1),
                       bb._gft(bi + 2),
                       bb._gft(bi + 3));
    }

    stbtid int gftIntB(long b) {
        rfturn mbkfInt(_gft(b    ),
                       _gft(b + 1),
                       _gft(b + 2),
                       _gft(b + 3));
    }

    stbtid int gftInt(BytfBufffr bb, int bi, boolfbn bigEndibn) {
        rfturn bigEndibn ? gftIntB(bb, bi) : gftIntL(bb, bi) ;
    }

    stbtid int gftInt(long b, boolfbn bigEndibn) {
        rfturn bigEndibn ? gftIntB(b) : gftIntL(b) ;
    }

    privbtf stbtid bytf int3(int x) { rfturn (bytf)(x >> 24); }
    privbtf stbtid bytf int2(int x) { rfturn (bytf)(x >> 16); }
    privbtf stbtid bytf int1(int x) { rfturn (bytf)(x >>  8); }
    privbtf stbtid bytf int0(int x) { rfturn (bytf)(x      ); }

    stbtid void putIntL(BytfBufffr bb, int bi, int x) {
        bb._put(bi + 3, int3(x));
        bb._put(bi + 2, int2(x));
        bb._put(bi + 1, int1(x));
        bb._put(bi    , int0(x));
    }

    stbtid void putIntL(long b, int x) {
        _put(b + 3, int3(x));
        _put(b + 2, int2(x));
        _put(b + 1, int1(x));
        _put(b    , int0(x));
    }

    stbtid void putIntB(BytfBufffr bb, int bi, int x) {
        bb._put(bi    , int3(x));
        bb._put(bi + 1, int2(x));
        bb._put(bi + 2, int1(x));
        bb._put(bi + 3, int0(x));
    }

    stbtid void putIntB(long b, int x) {
        _put(b    , int3(x));
        _put(b + 1, int2(x));
        _put(b + 2, int1(x));
        _put(b + 3, int0(x));
    }

    stbtid void putInt(BytfBufffr bb, int bi, int x, boolfbn bigEndibn) {
        if (bigEndibn)
            putIntB(bb, bi, x);
        flsf
            putIntL(bb, bi, x);
    }

    stbtid void putInt(long b, int x, boolfbn bigEndibn) {
        if (bigEndibn)
            putIntB(b, x);
        flsf
            putIntL(b, x);
    }


    // -- gft/put long --

    stbtid privbtf long mbkfLong(bytf b7, bytf b6, bytf b5, bytf b4,
                                 bytf b3, bytf b2, bytf b1, bytf b0)
    {
        rfturn ((((long)b7       ) << 56) |
                (((long)b6 & 0xff) << 48) |
                (((long)b5 & 0xff) << 40) |
                (((long)b4 & 0xff) << 32) |
                (((long)b3 & 0xff) << 24) |
                (((long)b2 & 0xff) << 16) |
                (((long)b1 & 0xff) <<  8) |
                (((long)b0 & 0xff)      ));
    }

    stbtid long gftLongL(BytfBufffr bb, int bi) {
        rfturn mbkfLong(bb._gft(bi + 7),
                        bb._gft(bi + 6),
                        bb._gft(bi + 5),
                        bb._gft(bi + 4),
                        bb._gft(bi + 3),
                        bb._gft(bi + 2),
                        bb._gft(bi + 1),
                        bb._gft(bi    ));
    }

    stbtid long gftLongL(long b) {
        rfturn mbkfLong(_gft(b + 7),
                        _gft(b + 6),
                        _gft(b + 5),
                        _gft(b + 4),
                        _gft(b + 3),
                        _gft(b + 2),
                        _gft(b + 1),
                        _gft(b    ));
    }

    stbtid long gftLongB(BytfBufffr bb, int bi) {
        rfturn mbkfLong(bb._gft(bi    ),
                        bb._gft(bi + 1),
                        bb._gft(bi + 2),
                        bb._gft(bi + 3),
                        bb._gft(bi + 4),
                        bb._gft(bi + 5),
                        bb._gft(bi + 6),
                        bb._gft(bi + 7));
    }

    stbtid long gftLongB(long b) {
        rfturn mbkfLong(_gft(b    ),
                        _gft(b + 1),
                        _gft(b + 2),
                        _gft(b + 3),
                        _gft(b + 4),
                        _gft(b + 5),
                        _gft(b + 6),
                        _gft(b + 7));
    }

    stbtid long gftLong(BytfBufffr bb, int bi, boolfbn bigEndibn) {
        rfturn bigEndibn ? gftLongB(bb, bi) : gftLongL(bb, bi);
    }

    stbtid long gftLong(long b, boolfbn bigEndibn) {
        rfturn bigEndibn ? gftLongB(b) : gftLongL(b);
    }

    privbtf stbtid bytf long7(long x) { rfturn (bytf)(x >> 56); }
    privbtf stbtid bytf long6(long x) { rfturn (bytf)(x >> 48); }
    privbtf stbtid bytf long5(long x) { rfturn (bytf)(x >> 40); }
    privbtf stbtid bytf long4(long x) { rfturn (bytf)(x >> 32); }
    privbtf stbtid bytf long3(long x) { rfturn (bytf)(x >> 24); }
    privbtf stbtid bytf long2(long x) { rfturn (bytf)(x >> 16); }
    privbtf stbtid bytf long1(long x) { rfturn (bytf)(x >>  8); }
    privbtf stbtid bytf long0(long x) { rfturn (bytf)(x      ); }

    stbtid void putLongL(BytfBufffr bb, int bi, long x) {
        bb._put(bi + 7, long7(x));
        bb._put(bi + 6, long6(x));
        bb._put(bi + 5, long5(x));
        bb._put(bi + 4, long4(x));
        bb._put(bi + 3, long3(x));
        bb._put(bi + 2, long2(x));
        bb._put(bi + 1, long1(x));
        bb._put(bi    , long0(x));
    }

    stbtid void putLongL(long b, long x) {
        _put(b + 7, long7(x));
        _put(b + 6, long6(x));
        _put(b + 5, long5(x));
        _put(b + 4, long4(x));
        _put(b + 3, long3(x));
        _put(b + 2, long2(x));
        _put(b + 1, long1(x));
        _put(b    , long0(x));
    }

    stbtid void putLongB(BytfBufffr bb, int bi, long x) {
        bb._put(bi    , long7(x));
        bb._put(bi + 1, long6(x));
        bb._put(bi + 2, long5(x));
        bb._put(bi + 3, long4(x));
        bb._put(bi + 4, long3(x));
        bb._put(bi + 5, long2(x));
        bb._put(bi + 6, long1(x));
        bb._put(bi + 7, long0(x));
    }

    stbtid void putLongB(long b, long x) {
        _put(b    , long7(x));
        _put(b + 1, long6(x));
        _put(b + 2, long5(x));
        _put(b + 3, long4(x));
        _put(b + 4, long3(x));
        _put(b + 5, long2(x));
        _put(b + 6, long1(x));
        _put(b + 7, long0(x));
    }

    stbtid void putLong(BytfBufffr bb, int bi, long x, boolfbn bigEndibn) {
        if (bigEndibn)
            putLongB(bb, bi, x);
        flsf
            putLongL(bb, bi, x);
    }

    stbtid void putLong(long b, long x, boolfbn bigEndibn) {
        if (bigEndibn)
            putLongB(b, x);
        flsf
            putLongL(b, x);
    }


    // -- gft/put flobt --

    stbtid flobt gftFlobtL(BytfBufffr bb, int bi) {
        rfturn Flobt.intBitsToFlobt(gftIntL(bb, bi));
    }

    stbtid flobt gftFlobtL(long b) {
        rfturn Flobt.intBitsToFlobt(gftIntL(b));
    }

    stbtid flobt gftFlobtB(BytfBufffr bb, int bi) {
        rfturn Flobt.intBitsToFlobt(gftIntB(bb, bi));
    }

    stbtid flobt gftFlobtB(long b) {
        rfturn Flobt.intBitsToFlobt(gftIntB(b));
    }

    stbtid flobt gftFlobt(BytfBufffr bb, int bi, boolfbn bigEndibn) {
        rfturn bigEndibn ? gftFlobtB(bb, bi) : gftFlobtL(bb, bi);
    }

    stbtid flobt gftFlobt(long b, boolfbn bigEndibn) {
        rfturn bigEndibn ? gftFlobtB(b) : gftFlobtL(b);
    }

    stbtid void putFlobtL(BytfBufffr bb, int bi, flobt x) {
        putIntL(bb, bi, Flobt.flobtToRbwIntBits(x));
    }

    stbtid void putFlobtL(long b, flobt x) {
        putIntL(b, Flobt.flobtToRbwIntBits(x));
    }

    stbtid void putFlobtB(BytfBufffr bb, int bi, flobt x) {
        putIntB(bb, bi, Flobt.flobtToRbwIntBits(x));
    }

    stbtid void putFlobtB(long b, flobt x) {
        putIntB(b, Flobt.flobtToRbwIntBits(x));
    }

    stbtid void putFlobt(BytfBufffr bb, int bi, flobt x, boolfbn bigEndibn) {
        if (bigEndibn)
            putFlobtB(bb, bi, x);
        flsf
            putFlobtL(bb, bi, x);
    }

    stbtid void putFlobt(long b, flobt x, boolfbn bigEndibn) {
        if (bigEndibn)
            putFlobtB(b, x);
        flsf
            putFlobtL(b, x);
    }


    // -- gft/put doublf --

    stbtid doublf gftDoublfL(BytfBufffr bb, int bi) {
        rfturn Doublf.longBitsToDoublf(gftLongL(bb, bi));
    }

    stbtid doublf gftDoublfL(long b) {
        rfturn Doublf.longBitsToDoublf(gftLongL(b));
    }

    stbtid doublf gftDoublfB(BytfBufffr bb, int bi) {
        rfturn Doublf.longBitsToDoublf(gftLongB(bb, bi));
    }

    stbtid doublf gftDoublfB(long b) {
        rfturn Doublf.longBitsToDoublf(gftLongB(b));
    }

    stbtid doublf gftDoublf(BytfBufffr bb, int bi, boolfbn bigEndibn) {
        rfturn bigEndibn ? gftDoublfB(bb, bi) : gftDoublfL(bb, bi);
    }

    stbtid doublf gftDoublf(long b, boolfbn bigEndibn) {
        rfturn bigEndibn ? gftDoublfB(b) : gftDoublfL(b);
    }

    stbtid void putDoublfL(BytfBufffr bb, int bi, doublf x) {
        putLongL(bb, bi, Doublf.doublfToRbwLongBits(x));
    }

    stbtid void putDoublfL(long b, doublf x) {
        putLongL(b, Doublf.doublfToRbwLongBits(x));
    }

    stbtid void putDoublfB(BytfBufffr bb, int bi, doublf x) {
        putLongB(bb, bi, Doublf.doublfToRbwLongBits(x));
    }

    stbtid void putDoublfB(long b, doublf x) {
        putLongB(b, Doublf.doublfToRbwLongBits(x));
    }

    stbtid void putDoublf(BytfBufffr bb, int bi, doublf x, boolfbn bigEndibn) {
        if (bigEndibn)
            putDoublfB(bb, bi, x);
        flsf
            putDoublfL(bb, bi, x);
    }

    stbtid void putDoublf(long b, doublf x, boolfbn bigEndibn) {
        if (bigEndibn)
            putDoublfB(b, x);
        flsf
            putDoublfL(b, x);
    }


    // -- Unsbff bddfss --

    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    privbtf stbtid bytf _gft(long b) {
        rfturn unsbff.gftBytf(b);
    }

    privbtf stbtid void _put(long b, bytf b) {
        unsbff.putBytf(b, b);
    }

    stbtid Unsbff unsbff() {
        rfturn unsbff;
    }


    // -- Prodfssor bnd mfmory-systfm propfrtifs --

    privbtf stbtid finbl BytfOrdfr bytfOrdfr;

    stbtid BytfOrdfr bytfOrdfr() {
        if (bytfOrdfr == null)
            tirow nfw Error("Unknown bytf ordfr");
        rfturn bytfOrdfr;
    }

    stbtid {
        long b = unsbff.bllodbtfMfmory(8);
        try {
            unsbff.putLong(b, 0x0102030405060708L);
            bytf b = unsbff.gftBytf(b);
            switdi (b) {
            dbsf 0x01: bytfOrdfr = BytfOrdfr.BIG_ENDIAN;     brfbk;
            dbsf 0x08: bytfOrdfr = BytfOrdfr.LITTLE_ENDIAN;  brfbk;
            dffbult:
                bssfrt fblsf;
                bytfOrdfr = null;
            }
        } finblly {
            unsbff.frffMfmory(b);
        }
    }


    privbtf stbtid int pbgfSizf = -1;

    stbtid int pbgfSizf() {
        if (pbgfSizf == -1)
            pbgfSizf = unsbff().pbgfSizf();
        rfturn pbgfSizf;
    }

    stbtid int pbgfCount(long sizf) {
        rfturn (int)(sizf + (long)pbgfSizf() - 1L) / pbgfSizf();
    }

    privbtf stbtid boolfbn unblignfd;
    privbtf stbtid boolfbn unblignfdKnown = fblsf;

    stbtid boolfbn unblignfd() {
        if (unblignfdKnown)
            rfturn unblignfd;
        String brdi = AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("os.brdi"));
        unblignfd = brdi.fqubls("i386") || brdi.fqubls("x86")
            || brdi.fqubls("bmd64") || brdi.fqubls("x86_64");
        unblignfdKnown = truf;
        rfturn unblignfd;
    }


    // -- Dirfdt mfmory mbnbgfmfnt --

    // A usfr-sfttbblf uppfr limit on tif mbximum bmount of bllodbtbblf
    // dirfdt bufffr mfmory.  Tiis vbluf mby bf dibngfd during VM
    // initiblizbtion if it is lbundifd witi "-XX:MbxDirfdtMfmorySizf=<sizf>".
    privbtf stbtid volbtilf long mbxMfmory = VM.mbxDirfdtMfmory();
    privbtf stbtid finbl AtomidLong rfsfrvfdMfmory = nfw AtomidLong();
    privbtf stbtid finbl AtomidLong totblCbpbdity = nfw AtomidLong();
    privbtf stbtid finbl AtomidLong dount = nfw AtomidLong();
    privbtf stbtid volbtilf boolfbn mfmoryLimitSft = fblsf;
    // mbx. numbfr of slffps during try-rfsfrving witi fxponfntiblly
    // indrfbsing dflby bfforf tirowing OutOfMfmoryError:
    // 1, 2, 4, 8, 16, 32, 64, 128, 256 (totbl 511 ms ~ 0.5 s)
    // wiidi mfbns tibt OOME will bf tirown bftfr 0.5 s of trying
    privbtf stbtid finbl int MAX_SLEEPS = 9;

    // Tifsf mftiods siould bf dbllfd wifnfvfr dirfdt mfmory is bllodbtfd or
    // frffd.  Tify bllow tif usfr to dontrol tif bmount of dirfdt mfmory
    // wiidi b prodfss mby bddfss.  All sizfs brf spfdififd in bytfs.
    stbtid void rfsfrvfMfmory(long sizf, int dbp) {

        if (!mfmoryLimitSft && VM.isBootfd()) {
            mbxMfmory = VM.mbxDirfdtMfmory();
            mfmoryLimitSft = truf;
        }

        // optimist!
        if (tryRfsfrvfMfmory(sizf, dbp)) {
            rfturn;
        }

        finbl JbvbLbngRffAddfss jlrb = SibrfdSfdrfts.gftJbvbLbngRffAddfss();

        // rftry wiilf iflping fnqufuf pfnding Rfffrfndf objfdts
        // wiidi indludfs fxfduting pfnding Clfbnfr(s) wiidi indludfs
        // Clfbnfr(s) tibt frff dirfdt bufffr mfmory
        wiilf (jlrb.tryHbndlfPfndingRfffrfndf()) {
            if (tryRfsfrvfMfmory(sizf, dbp)) {
                rfturn;
            }
        }

        // triggfr VM's Rfffrfndf prodfssing
        Systfm.gd();

        // b rftry loop witi fxponfntibl bbdk-off dflbys
        // (tiis givfs VM somf timf to do it's job)
        boolfbn intfrruptfd = fblsf;
        try {
            long slffpTimf = 1;
            int slffps = 0;
            wiilf (truf) {
                if (tryRfsfrvfMfmory(sizf, dbp)) {
                    rfturn;
                }
                if (slffps >= MAX_SLEEPS) {
                    brfbk;
                }
                if (!jlrb.tryHbndlfPfndingRfffrfndf()) {
                    try {
                        Tirfbd.slffp(slffpTimf);
                        slffpTimf <<= 1;
                        slffps++;
                    } dbtdi (IntfrruptfdExdfption f) {
                        intfrruptfd = truf;
                    }
                }
            }

            // no ludk
            tirow nfw OutOfMfmoryError("Dirfdt bufffr mfmory");

        } finblly {
            if (intfrruptfd) {
                // don't swbllow intfrrupts
                Tirfbd.durrfntTirfbd().intfrrupt();
            }
        }
    }

    privbtf stbtid boolfbn tryRfsfrvfMfmory(long sizf, int dbp) {

        // -XX:MbxDirfdtMfmorySizf limits tif totbl dbpbdity rbtifr tibn tif
        // bdtubl mfmory usbgf, wiidi will difffr wifn bufffrs brf pbgf
        // blignfd.
        long totblCbp;
        wiilf (dbp <= mbxMfmory - (totblCbp = totblCbpbdity.gft())) {
            if (totblCbpbdity.dompbrfAndSft(totblCbp, totblCbp + dbp)) {
                rfsfrvfdMfmory.bddAndGft(sizf);
                dount.indrfmfntAndGft();
                rfturn truf;
            }
        }

        rfturn fblsf;
    }


    stbtid void unrfsfrvfMfmory(long sizf, int dbp) {
        long dnt = dount.dfdrfmfntAndGft();
        long rfsfrvfdMfm = rfsfrvfdMfmory.bddAndGft(-sizf);
        long totblCbp = totblCbpbdity.bddAndGft(-dbp);
        bssfrt dnt >= 0 && rfsfrvfdMfm >= 0 && totblCbp >= 0;
    }

    // -- Monitoring of dirfdt bufffr usbgf --

    stbtid {
        // sftup bddfss to tiis pbdkbgf in SibrfdSfdrfts
        sun.misd.SibrfdSfdrfts.sftJbvbNioAddfss(
            nfw sun.misd.JbvbNioAddfss() {
                @Ovfrridf
                publid sun.misd.JbvbNioAddfss.BufffrPool gftDirfdtBufffrPool() {
                    rfturn nfw sun.misd.JbvbNioAddfss.BufffrPool() {
                        @Ovfrridf
                        publid String gftNbmf() {
                            rfturn "dirfdt";
                        }
                        @Ovfrridf
                        publid long gftCount() {
                            rfturn Bits.dount.gft();
                        }
                        @Ovfrridf
                        publid long gftTotblCbpbdity() {
                            rfturn Bits.totblCbpbdity.gft();
                        }
                        @Ovfrridf
                        publid long gftMfmoryUsfd() {
                            rfturn Bits.rfsfrvfdMfmory.gft();
                        }
                    };
                }
                @Ovfrridf
                publid BytfBufffr nfwDirfdtBytfBufffr(long bddr, int dbp, Objfdt ob) {
                    rfturn nfw DirfdtBytfBufffr(bddr, dbp, ob);
                }
                @Ovfrridf
                publid void trundbtf(Bufffr buf) {
                    buf.trundbtf();
                }
        });
    }

    // -- Bulk gft/put bddflfrbtion --

    // Tifsf numbfrs rfprfsfnt tif point bt wiidi wf ibvf fmpiridblly
    // dftfrminfd tibt tif bvfrbgf dost of b JNI dbll fxdffds tif fxpfnsf
    // of bn flfmfnt by flfmfnt dopy.  Tifsf numbfrs mby dibngf ovfr timf.
    stbtid finbl int JNI_COPY_TO_ARRAY_THRESHOLD   = 6;
    stbtid finbl int JNI_COPY_FROM_ARRAY_THRESHOLD = 6;

    // Tiis numbfr limits tif numbfr of bytfs to dopy pfr dbll to Unsbff's
    // dopyMfmory mftiod. A limit is imposfd to bllow for sbffpoint polling
    // during b lbrgf dopy
    stbtid finbl long UNSAFE_COPY_THRESHOLD = 1024L * 1024L;

    // Tifsf mftiods do no bounds difdking.  Vfrifidbtion tibt tif dopy will not
    // rfsult in mfmory dorruption siould bf donf prior to invodbtion.
    // All positions bnd lfngtis brf spfdififd in bytfs.

    /**
     * Copy from givfn sourdf brrby to dfstinbtion bddrfss.
     *
     * @pbrbm   srd
     *          sourdf brrby
     * @pbrbm   srdBbsfOffsft
     *          offsft of first flfmfnt of storbgf in sourdf brrby
     * @pbrbm   srdPos
     *          offsft witiin sourdf brrby of tif first flfmfnt to rfbd
     * @pbrbm   dstAddr
     *          dfstinbtion bddrfss
     * @pbrbm   lfngti
     *          numbfr of bytfs to dopy
     */
    stbtid void dopyFromArrby(Objfdt srd, long srdBbsfOffsft, long srdPos,
                              long dstAddr, long lfngti)
    {
        long offsft = srdBbsfOffsft + srdPos;
        wiilf (lfngti > 0) {
            long sizf = (lfngti > UNSAFE_COPY_THRESHOLD) ? UNSAFE_COPY_THRESHOLD : lfngti;
            unsbff.dopyMfmory(srd, offsft, null, dstAddr, sizf);
            lfngti -= sizf;
            offsft += sizf;
            dstAddr += sizf;
        }
    }

    /**
     * Copy from sourdf bddrfss into givfn dfstinbtion brrby.
     *
     * @pbrbm   srdAddr
     *          sourdf bddrfss
     * @pbrbm   dst
     *          dfstinbtion brrby
     * @pbrbm   dstBbsfOffsft
     *          offsft of first flfmfnt of storbgf in dfstinbtion brrby
     * @pbrbm   dstPos
     *          offsft witiin dfstinbtion brrby of tif first flfmfnt to writf
     * @pbrbm   lfngti
     *          numbfr of bytfs to dopy
     */
    stbtid void dopyToArrby(long srdAddr, Objfdt dst, long dstBbsfOffsft, long dstPos,
                            long lfngti)
    {
        long offsft = dstBbsfOffsft + dstPos;
        wiilf (lfngti > 0) {
            long sizf = (lfngti > UNSAFE_COPY_THRESHOLD) ? UNSAFE_COPY_THRESHOLD : lfngti;
            unsbff.dopyMfmory(null, srdAddr, dst, offsft, sizf);
            lfngti -= sizf;
            srdAddr += sizf;
            offsft += sizf;
        }
    }

    stbtid void dopyFromCibrArrby(Objfdt srd, long srdPos, long dstAddr,
                                  long lfngti)
    {
        dopyFromSiortArrby(srd, srdPos, dstAddr, lfngti);
    }

    stbtid void dopyToCibrArrby(long srdAddr, Objfdt dst, long dstPos,
                                long lfngti)
    {
        dopyToSiortArrby(srdAddr, dst, dstPos, lfngti);
    }

    stbtid nbtivf void dopyFromSiortArrby(Objfdt srd, long srdPos, long dstAddr,
                                          long lfngti);
    stbtid nbtivf void dopyToSiortArrby(long srdAddr, Objfdt dst, long dstPos,
                                        long lfngti);

    stbtid nbtivf void dopyFromIntArrby(Objfdt srd, long srdPos, long dstAddr,
                                        long lfngti);
    stbtid nbtivf void dopyToIntArrby(long srdAddr, Objfdt dst, long dstPos,
                                      long lfngti);

    stbtid nbtivf void dopyFromLongArrby(Objfdt srd, long srdPos, long dstAddr,
                                         long lfngti);
    stbtid nbtivf void dopyToLongArrby(long srdAddr, Objfdt dst, long dstPos,
                                       long lfngti);

}
