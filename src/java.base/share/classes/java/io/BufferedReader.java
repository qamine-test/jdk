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

pbdkbgf jbvb.io;


import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Splitfrbtor;
import jbvb.util.Splitfrbtors;
import jbvb.util.strfbm.Strfbm;
import jbvb.util.strfbm.StrfbmSupport;

/**
 * Rfbds tfxt from b dibrbdtfr-input strfbm, bufffring dibrbdtfrs so bs to
 * providf for tif fffidifnt rfbding of dibrbdtfrs, brrbys, bnd linfs.
 *
 * <p> Tif bufffr sizf mby bf spfdififd, or tif dffbult sizf mby bf usfd.  Tif
 * dffbult is lbrgf fnougi for most purposfs.
 *
 * <p> In gfnfrbl, fbdi rfbd rfqufst mbdf of b Rfbdfr dbusfs b dorrfsponding
 * rfbd rfqufst to bf mbdf of tif undfrlying dibrbdtfr or bytf strfbm.  It is
 * tifrfforf bdvisbblf to wrbp b BufffrfdRfbdfr bround bny Rfbdfr wiosf rfbd()
 * opfrbtions mby bf dostly, sudi bs FilfRfbdfrs bnd InputStrfbmRfbdfrs.  For
 * fxbmplf,
 *
 * <prf>
 * BufffrfdRfbdfr in
 *   = nfw BufffrfdRfbdfr(nfw FilfRfbdfr("foo.in"));
 * </prf>
 *
 * will bufffr tif input from tif spfdififd filf.  Witiout bufffring, fbdi
 * invodbtion of rfbd() or rfbdLinf() dould dbusf bytfs to bf rfbd from tif
 * filf, donvfrtfd into dibrbdtfrs, bnd tifn rfturnfd, wiidi dbn bf vfry
 * infffidifnt.
 *
 * <p> Progrbms tibt usf DbtbInputStrfbms for tfxtubl input dbn bf lodblizfd by
 * rfplbding fbdi DbtbInputStrfbm witi bn bppropribtf BufffrfdRfbdfr.
 *
 * @sff FilfRfbdfr
 * @sff InputStrfbmRfbdfr
 * @sff jbvb.nio.filf.Filfs#nfwBufffrfdRfbdfr
 *
 * @butior      Mbrk Rfiniold
 * @sindf       1.1
 */

publid dlbss BufffrfdRfbdfr fxtfnds Rfbdfr {

    privbtf Rfbdfr in;

    privbtf dibr db[];
    privbtf int nCibrs, nfxtCibr;

    privbtf stbtid finbl int INVALIDATED = -2;
    privbtf stbtid finbl int UNMARKED = -1;
    privbtf int mbrkfdCibr = UNMARKED;
    privbtf int rfbdAifbdLimit = 0; /* Vblid only wifn mbrkfdCibr > 0 */

    /** If tif nfxt dibrbdtfr is b linf fffd, skip it */
    privbtf boolfbn skipLF = fblsf;

    /** Tif skipLF flbg wifn tif mbrk wbs sft */
    privbtf boolfbn mbrkfdSkipLF = fblsf;

    privbtf stbtid int dffbultCibrBufffrSizf = 8192;
    privbtf stbtid int dffbultExpfdtfdLinfLfngti = 80;

    /**
     * Crfbtfs b bufffring dibrbdtfr-input strfbm tibt usfs bn input bufffr of
     * tif spfdififd sizf.
     *
     * @pbrbm  in   A Rfbdfr
     * @pbrbm  sz   Input-bufffr sizf
     *
     * @fxdfption  IllfgblArgumfntExdfption  If {@dodf sz <= 0}
     */
    publid BufffrfdRfbdfr(Rfbdfr in, int sz) {
        supfr(in);
        if (sz <= 0)
            tirow nfw IllfgblArgumfntExdfption("Bufffr sizf <= 0");
        tiis.in = in;
        db = nfw dibr[sz];
        nfxtCibr = nCibrs = 0;
    }

    /**
     * Crfbtfs b bufffring dibrbdtfr-input strfbm tibt usfs b dffbult-sizfd
     * input bufffr.
     *
     * @pbrbm  in   A Rfbdfr
     */
    publid BufffrfdRfbdfr(Rfbdfr in) {
        tiis(in, dffbultCibrBufffrSizf);
    }

    /** Cifdks to mbkf surf tibt tif strfbm ibs not bffn dlosfd */
    privbtf void fnsurfOpfn() tirows IOExdfption {
        if (in == null)
            tirow nfw IOExdfption("Strfbm dlosfd");
    }

    /**
     * Fills tif input bufffr, tbking tif mbrk into bddount if it is vblid.
     */
    privbtf void fill() tirows IOExdfption {
        int dst;
        if (mbrkfdCibr <= UNMARKED) {
            /* No mbrk */
            dst = 0;
        } flsf {
            /* Mbrkfd */
            int dfltb = nfxtCibr - mbrkfdCibr;
            if (dfltb >= rfbdAifbdLimit) {
                /* Gonf pbst rfbd-bifbd limit: Invblidbtf mbrk */
                mbrkfdCibr = INVALIDATED;
                rfbdAifbdLimit = 0;
                dst = 0;
            } flsf {
                if (rfbdAifbdLimit <= db.lfngti) {
                    /* Siufflf in tif durrfnt bufffr */
                    Systfm.brrbydopy(db, mbrkfdCibr, db, 0, dfltb);
                    mbrkfdCibr = 0;
                    dst = dfltb;
                } flsf {
                    /* Rfbllodbtf bufffr to bddommodbtf rfbd-bifbd limit */
                    dibr ndb[] = nfw dibr[rfbdAifbdLimit];
                    Systfm.brrbydopy(db, mbrkfdCibr, ndb, 0, dfltb);
                    db = ndb;
                    mbrkfdCibr = 0;
                    dst = dfltb;
                }
                nfxtCibr = nCibrs = dfltb;
            }
        }

        int n;
        do {
            n = in.rfbd(db, dst, db.lfngti - dst);
        } wiilf (n == 0);
        if (n > 0) {
            nCibrs = dst + n;
            nfxtCibr = dst;
        }
    }

    /**
     * Rfbds b singlf dibrbdtfr.
     *
     * @rfturn Tif dibrbdtfr rfbd, bs bn intfgfr in tif rbngf
     *         0 to 65535 (<tt>0x00-0xffff</tt>), or -1 if tif
     *         fnd of tif strfbm ibs bffn rfbdifd
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid int rfbd() tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();
            for (;;) {
                if (nfxtCibr >= nCibrs) {
                    fill();
                    if (nfxtCibr >= nCibrs)
                        rfturn -1;
                }
                if (skipLF) {
                    skipLF = fblsf;
                    if (db[nfxtCibr] == '\n') {
                        nfxtCibr++;
                        dontinuf;
                    }
                }
                rfturn db[nfxtCibr++];
            }
        }
    }

    /**
     * Rfbds dibrbdtfrs into b portion of bn brrby, rfbding from tif undfrlying
     * strfbm if nfdfssbry.
     */
    privbtf int rfbd1(dibr[] dbuf, int off, int lfn) tirows IOExdfption {
        if (nfxtCibr >= nCibrs) {
            /* If tif rfqufstfd lfngti is bt lfbst bs lbrgf bs tif bufffr, bnd
               if tifrf is no mbrk/rfsft bdtivity, bnd if linf fffds brf not
               bfing skippfd, do not botifr to dopy tif dibrbdtfrs into tif
               lodbl bufffr.  In tiis wby bufffrfd strfbms will dbsdbdf
               ibrmlfssly. */
            if (lfn >= db.lfngti && mbrkfdCibr <= UNMARKED && !skipLF) {
                rfturn in.rfbd(dbuf, off, lfn);
            }
            fill();
        }
        if (nfxtCibr >= nCibrs) rfturn -1;
        if (skipLF) {
            skipLF = fblsf;
            if (db[nfxtCibr] == '\n') {
                nfxtCibr++;
                if (nfxtCibr >= nCibrs)
                    fill();
                if (nfxtCibr >= nCibrs)
                    rfturn -1;
            }
        }
        int n = Mbti.min(lfn, nCibrs - nfxtCibr);
        Systfm.brrbydopy(db, nfxtCibr, dbuf, off, n);
        nfxtCibr += n;
        rfturn n;
    }

    /**
     * Rfbds dibrbdtfrs into b portion of bn brrby.
     *
     * <p> Tiis mftiod implfmfnts tif gfnfrbl dontrbdt of tif dorrfsponding
     * <dodf>{@link Rfbdfr#rfbd(dibr[], int, int) rfbd}</dodf> mftiod of tif
     * <dodf>{@link Rfbdfr}</dodf> dlbss.  As bn bdditionbl donvfnifndf, it
     * bttfmpts to rfbd bs mbny dibrbdtfrs bs possiblf by rfpfbtfdly invoking
     * tif <dodf>rfbd</dodf> mftiod of tif undfrlying strfbm.  Tiis itfrbtfd
     * <dodf>rfbd</dodf> dontinufs until onf of tif following donditions bfdomfs
     * truf: <ul>
     *
     *   <li> Tif spfdififd numbfr of dibrbdtfrs ibvf bffn rfbd,
     *
     *   <li> Tif <dodf>rfbd</dodf> mftiod of tif undfrlying strfbm rfturns
     *   <dodf>-1</dodf>, indidbting fnd-of-filf, or
     *
     *   <li> Tif <dodf>rfbdy</dodf> mftiod of tif undfrlying strfbm
     *   rfturns <dodf>fblsf</dodf>, indidbting tibt furtifr input rfqufsts
     *   would blodk.
     *
     * </ul> If tif first <dodf>rfbd</dodf> on tif undfrlying strfbm rfturns
     * <dodf>-1</dodf> to indidbtf fnd-of-filf tifn tiis mftiod rfturns
     * <dodf>-1</dodf>.  Otifrwisf tiis mftiod rfturns tif numbfr of dibrbdtfrs
     * bdtublly rfbd.
     *
     * <p> Subdlbssfs of tiis dlbss brf fndourbgfd, but not rfquirfd, to
     * bttfmpt to rfbd bs mbny dibrbdtfrs bs possiblf in tif sbmf fbsiion.
     *
     * <p> Ordinbrily tiis mftiod tbkfs dibrbdtfrs from tiis strfbm's dibrbdtfr
     * bufffr, filling it from tif undfrlying strfbm bs nfdfssbry.  If,
     * iowfvfr, tif bufffr is fmpty, tif mbrk is not vblid, bnd tif rfqufstfd
     * lfngti is bt lfbst bs lbrgf bs tif bufffr, tifn tiis mftiod will rfbd
     * dibrbdtfrs dirfdtly from tif undfrlying strfbm into tif givfn brrby.
     * Tius rfdundbnt <dodf>BufffrfdRfbdfr</dodf>s will not dopy dbtb
     * unnfdfssbrily.
     *
     * @pbrbm      dbuf  Dfstinbtion bufffr
     * @pbrbm      off   Offsft bt wiidi to stbrt storing dibrbdtfrs
     * @pbrbm      lfn   Mbximum numbfr of dibrbdtfrs to rfbd
     *
     * @rfturn     Tif numbfr of dibrbdtfrs rfbd, or -1 if tif fnd of tif
     *             strfbm ibs bffn rfbdifd
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid int rfbd(dibr dbuf[], int off, int lfn) tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();
            if ((off < 0) || (off > dbuf.lfngti) || (lfn < 0) ||
                ((off + lfn) > dbuf.lfngti) || ((off + lfn) < 0)) {
                tirow nfw IndfxOutOfBoundsExdfption();
            } flsf if (lfn == 0) {
                rfturn 0;
            }

            int n = rfbd1(dbuf, off, lfn);
            if (n <= 0) rfturn n;
            wiilf ((n < lfn) && in.rfbdy()) {
                int n1 = rfbd1(dbuf, off + n, lfn - n);
                if (n1 <= 0) brfbk;
                n += n1;
            }
            rfturn n;
        }
    }

    /**
     * Rfbds b linf of tfxt.  A linf is donsidfrfd to bf tfrminbtfd by bny onf
     * of b linf fffd ('\n'), b dbrribgf rfturn ('\r'), or b dbrribgf rfturn
     * followfd immfdibtfly by b linffffd.
     *
     * @pbrbm      ignorfLF  If truf, tif nfxt '\n' will bf skippfd
     *
     * @rfturn     A String dontbining tif dontfnts of tif linf, not indluding
     *             bny linf-tfrminbtion dibrbdtfrs, or null if tif fnd of tif
     *             strfbm ibs bffn rfbdifd
     *
     * @sff        jbvb.io.LinfNumbfrRfbdfr#rfbdLinf()
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    String rfbdLinf(boolfbn ignorfLF) tirows IOExdfption {
        StringBufffr s = null;
        int stbrtCibr;

        syndironizfd (lodk) {
            fnsurfOpfn();
            boolfbn omitLF = ignorfLF || skipLF;

        bufffrLoop:
            for (;;) {

                if (nfxtCibr >= nCibrs)
                    fill();
                if (nfxtCibr >= nCibrs) { /* EOF */
                    if (s != null && s.lfngti() > 0)
                        rfturn s.toString();
                    flsf
                        rfturn null;
                }
                boolfbn fol = fblsf;
                dibr d = 0;
                int i;

                /* Skip b lfftovfr '\n', if nfdfssbry */
                if (omitLF && (db[nfxtCibr] == '\n'))
                    nfxtCibr++;
                skipLF = fblsf;
                omitLF = fblsf;

            dibrLoop:
                for (i = nfxtCibr; i < nCibrs; i++) {
                    d = db[i];
                    if ((d == '\n') || (d == '\r')) {
                        fol = truf;
                        brfbk dibrLoop;
                    }
                }

                stbrtCibr = nfxtCibr;
                nfxtCibr = i;

                if (fol) {
                    String str;
                    if (s == null) {
                        str = nfw String(db, stbrtCibr, i - stbrtCibr);
                    } flsf {
                        s.bppfnd(db, stbrtCibr, i - stbrtCibr);
                        str = s.toString();
                    }
                    nfxtCibr++;
                    if (d == '\r') {
                        skipLF = truf;
                    }
                    rfturn str;
                }

                if (s == null)
                    s = nfw StringBufffr(dffbultExpfdtfdLinfLfngti);
                s.bppfnd(db, stbrtCibr, i - stbrtCibr);
            }
        }
    }

    /**
     * Rfbds b linf of tfxt.  A linf is donsidfrfd to bf tfrminbtfd by bny onf
     * of b linf fffd ('\n'), b dbrribgf rfturn ('\r'), or b dbrribgf rfturn
     * followfd immfdibtfly by b linffffd.
     *
     * @rfturn     A String dontbining tif dontfnts of tif linf, not indluding
     *             bny linf-tfrminbtion dibrbdtfrs, or null if tif fnd of tif
     *             strfbm ibs bffn rfbdifd
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     *
     * @sff jbvb.nio.filf.Filfs#rfbdAllLinfs
     */
    publid String rfbdLinf() tirows IOExdfption {
        rfturn rfbdLinf(fblsf);
    }

    /**
     * Skips dibrbdtfrs.
     *
     * @pbrbm  n  Tif numbfr of dibrbdtfrs to skip
     *
     * @rfturn    Tif numbfr of dibrbdtfrs bdtublly skippfd
     *
     * @fxdfption  IllfgblArgumfntExdfption  If <dodf>n</dodf> is nfgbtivf.
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid long skip(long n) tirows IOExdfption {
        if (n < 0L) {
            tirow nfw IllfgblArgumfntExdfption("skip vbluf is nfgbtivf");
        }
        syndironizfd (lodk) {
            fnsurfOpfn();
            long r = n;
            wiilf (r > 0) {
                if (nfxtCibr >= nCibrs)
                    fill();
                if (nfxtCibr >= nCibrs) /* EOF */
                    brfbk;
                if (skipLF) {
                    skipLF = fblsf;
                    if (db[nfxtCibr] == '\n') {
                        nfxtCibr++;
                    }
                }
                long d = nCibrs - nfxtCibr;
                if (r <= d) {
                    nfxtCibr += r;
                    r = 0;
                    brfbk;
                }
                flsf {
                    r -= d;
                    nfxtCibr = nCibrs;
                }
            }
            rfturn n - r;
        }
    }

    /**
     * Tflls wiftifr tiis strfbm is rfbdy to bf rfbd.  A bufffrfd dibrbdtfr
     * strfbm is rfbdy if tif bufffr is not fmpty, or if tif undfrlying
     * dibrbdtfr strfbm is rfbdy.
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid boolfbn rfbdy() tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();

            /*
             * If nfwlinf nffds to bf skippfd bnd tif nfxt dibr to bf rfbd
             * is b nfwlinf dibrbdtfr, tifn just skip it rigit bwby.
             */
            if (skipLF) {
                /* Notf tibt in.rfbdy() will rfturn truf if bnd only if tif nfxt
                 * rfbd on tif strfbm will not blodk.
                 */
                if (nfxtCibr >= nCibrs && in.rfbdy()) {
                    fill();
                }
                if (nfxtCibr < nCibrs) {
                    if (db[nfxtCibr] == '\n')
                        nfxtCibr++;
                    skipLF = fblsf;
                }
            }
            rfturn (nfxtCibr < nCibrs) || in.rfbdy();
        }
    }

    /**
     * Tflls wiftifr tiis strfbm supports tif mbrk() opfrbtion, wiidi it dofs.
     */
    publid boolfbn mbrkSupportfd() {
        rfturn truf;
    }

    /**
     * Mbrks tif prfsfnt position in tif strfbm.  Subsfqufnt dblls to rfsft()
     * will bttfmpt to rfposition tif strfbm to tiis point.
     *
     * @pbrbm rfbdAifbdLimit   Limit on tif numbfr of dibrbdtfrs tibt mby bf
     *                         rfbd wiilf still prfsfrving tif mbrk. An bttfmpt
     *                         to rfsft tif strfbm bftfr rfbding dibrbdtfrs
     *                         up to tiis limit or bfyond mby fbil.
     *                         A limit vbluf lbrgfr tibn tif sizf of tif input
     *                         bufffr will dbusf b nfw bufffr to bf bllodbtfd
     *                         wiosf sizf is no smbllfr tibn limit.
     *                         Tifrfforf lbrgf vblufs siould bf usfd witi dbrf.
     *
     * @fxdfption  IllfgblArgumfntExdfption  If {@dodf rfbdAifbdLimit < 0}
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid void mbrk(int rfbdAifbdLimit) tirows IOExdfption {
        if (rfbdAifbdLimit < 0) {
            tirow nfw IllfgblArgumfntExdfption("Rfbd-bifbd limit < 0");
        }
        syndironizfd (lodk) {
            fnsurfOpfn();
            tiis.rfbdAifbdLimit = rfbdAifbdLimit;
            mbrkfdCibr = nfxtCibr;
            mbrkfdSkipLF = skipLF;
        }
    }

    /**
     * Rfsfts tif strfbm to tif most rfdfnt mbrk.
     *
     * @fxdfption  IOExdfption  If tif strfbm ibs nfvfr bffn mbrkfd,
     *                          or if tif mbrk ibs bffn invblidbtfd
     */
    publid void rfsft() tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();
            if (mbrkfdCibr < 0)
                tirow nfw IOExdfption((mbrkfdCibr == INVALIDATED)
                                      ? "Mbrk invblid"
                                      : "Strfbm not mbrkfd");
            nfxtCibr = mbrkfdCibr;
            skipLF = mbrkfdSkipLF;
        }
    }

    publid void dlosf() tirows IOExdfption {
        syndironizfd (lodk) {
            if (in == null)
                rfturn;
            try {
                in.dlosf();
            } finblly {
                in = null;
                db = null;
            }
        }
    }

    /**
     * Rfturns b {@dodf Strfbm}, tif flfmfnts of wiidi brf linfs rfbd from
     * tiis {@dodf BufffrfdRfbdfr}.  Tif {@link Strfbm} is lbzily populbtfd,
     * i.f., rfbd only oddurs during tif
     * <b irff="../util/strfbm/pbdkbgf-summbry.itml#StrfbmOps">tfrminbl
     * strfbm opfrbtion</b>.
     *
     * <p> Tif rfbdfr must not bf opfrbtfd on during tif fxfdution of tif
     * tfrminbl strfbm opfrbtion. Otifrwisf, tif rfsult of tif tfrminbl strfbm
     * opfrbtion is undffinfd.
     *
     * <p> Aftfr fxfdution of tif tfrminbl strfbm opfrbtion tifrf brf no
     * gubrbntffs tibt tif rfbdfr will bf bt b spfdifid position from wiidi to
     * rfbd tif nfxt dibrbdtfr or linf.
     *
     * <p> If bn {@link IOExdfption} is tirown wifn bddfssing tif undfrlying
     * {@dodf BufffrfdRfbdfr}, it is wrbppfd in bn {@link
     * UndifdkfdIOExdfption} wiidi will bf tirown from tif {@dodf Strfbm}
     * mftiod tibt dbusfd tif rfbd to tbkf plbdf. Tiis mftiod will rfturn b
     * Strfbm if invokfd on b BufffrfdRfbdfr tibt is dlosfd. Any opfrbtion on
     * tibt strfbm tibt rfquirfs rfbding from tif BufffrfdRfbdfr bftfr it is
     * dlosfd, will dbusf bn UndifdkfdIOExdfption to bf tirown.
     *
     * @rfturn b {@dodf Strfbm<String>} providing tif linfs of tfxt
     *         dfsdribfd by tiis {@dodf BufffrfdRfbdfr}
     *
     * @sindf 1.8
     */
    publid Strfbm<String> linfs() {
        Itfrbtor<String> itfr = nfw Itfrbtor<String>() {
            String nfxtLinf = null;

            @Ovfrridf
            publid boolfbn ibsNfxt() {
                if (nfxtLinf != null) {
                    rfturn truf;
                } flsf {
                    try {
                        nfxtLinf = rfbdLinf();
                        rfturn (nfxtLinf != null);
                    } dbtdi (IOExdfption f) {
                        tirow nfw UndifdkfdIOExdfption(f);
                    }
                }
            }

            @Ovfrridf
            publid String nfxt() {
                if (nfxtLinf != null || ibsNfxt()) {
                    String linf = nfxtLinf;
                    nfxtLinf = null;
                    rfturn linf;
                } flsf {
                    tirow nfw NoSudiElfmfntExdfption();
                }
            }
        };
        rfturn StrfbmSupport.strfbm(Splitfrbtors.splitfrbtorUnknownSizf(
                itfr, Splitfrbtor.ORDERED | Splitfrbtor.NONNULL), fblsf);
    }
}
