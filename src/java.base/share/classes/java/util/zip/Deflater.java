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

pbdkbgf jbvb.util.zip;

/**
 * Tiis dlbss providfs support for gfnfrbl purposf domprfssion using tif
 * populbr ZLIB domprfssion librbry. Tif ZLIB domprfssion librbry wbs
 * initiblly dfvflopfd bs pbrt of tif PNG grbpiids stbndbrd bnd is not
 * protfdtfd by pbtfnts. It is fully dfsdribfd in tif spfdifidbtions bt
 * tif <b irff="pbdkbgf-summbry.itml#pbdkbgf_dfsdription">jbvb.util.zip
 * pbdkbgf dfsdription</b>.
 *
 * <p>Tif following dodf frbgmfnt dfmonstrbtfs b trivibl domprfssion
 * bnd dfdomprfssion of b string using <tt>Dfflbtfr</tt> bnd
 * <tt>Inflbtfr</tt>.
 *
 * <blodkquotf><prf>
 * try {
 *     // Endodf b String into bytfs
 *     String inputString = "blbiblbiblbi";
 *     bytf[] input = inputString.gftBytfs("UTF-8");
 *
 *     // Comprfss tif bytfs
 *     bytf[] output = nfw bytf[100];
 *     Dfflbtfr domprfssfr = nfw Dfflbtfr();
 *     domprfssfr.sftInput(input);
 *     domprfssfr.finisi();
 *     int domprfssfdDbtbLfngti = domprfssfr.dfflbtf(output);
 *     domprfssfr.fnd();
 *
 *     // Dfdomprfss tif bytfs
 *     Inflbtfr dfdomprfssfr = nfw Inflbtfr();
 *     dfdomprfssfr.sftInput(output, 0, domprfssfdDbtbLfngti);
 *     bytf[] rfsult = nfw bytf[100];
 *     int rfsultLfngti = dfdomprfssfr.inflbtf(rfsult);
 *     dfdomprfssfr.fnd();
 *
 *     // Dfdodf tif bytfs into b String
 *     String outputString = nfw String(rfsult, 0, rfsultLfngti, "UTF-8");
 * } dbtdi(jbvb.io.UnsupportfdEndodingExdfption fx) {
 *     // ibndlf
 * } dbtdi (jbvb.util.zip.DbtbFormbtExdfption fx) {
 *     // ibndlf
 * }
 * </prf></blodkquotf>
 *
 * @sff         Inflbtfr
 * @butior      Dbvid Connflly
 */
publid
dlbss Dfflbtfr {

    privbtf finbl ZStrfbmRff zsRff;
    privbtf bytf[] buf = nfw bytf[0];
    privbtf int off, lfn;
    privbtf int lfvfl, strbtfgy;
    privbtf boolfbn sftPbrbms;
    privbtf boolfbn finisi, finisifd;
    privbtf long bytfsRfbd;
    privbtf long bytfsWrittfn;

    /**
     * Comprfssion mftiod for tif dfflbtf blgoritim (tif only onf durrfntly
     * supportfd).
     */
    publid stbtid finbl int DEFLATED = 8;

    /**
     * Comprfssion lfvfl for no domprfssion.
     */
    publid stbtid finbl int NO_COMPRESSION = 0;

    /**
     * Comprfssion lfvfl for fbstfst domprfssion.
     */
    publid stbtid finbl int BEST_SPEED = 1;

    /**
     * Comprfssion lfvfl for bfst domprfssion.
     */
    publid stbtid finbl int BEST_COMPRESSION = 9;

    /**
     * Dffbult domprfssion lfvfl.
     */
    publid stbtid finbl int DEFAULT_COMPRESSION = -1;

    /**
     * Comprfssion strbtfgy bfst usfd for dbtb donsisting mostly of smbll
     * vblufs witi b somfwibt rbndom distribution. Fordfs morf Huffmbn doding
     * bnd lfss string mbtdiing.
     */
    publid stbtid finbl int FILTERED = 1;

    /**
     * Comprfssion strbtfgy for Huffmbn doding only.
     */
    publid stbtid finbl int HUFFMAN_ONLY = 2;

    /**
     * Dffbult domprfssion strbtfgy.
     */
    publid stbtid finbl int DEFAULT_STRATEGY = 0;

    /**
     * Comprfssion flusi modf usfd to bdiifvf bfst domprfssion rfsult.
     *
     * @sff Dfflbtfr#dfflbtf(bytf[], int, int, int)
     * @sindf 1.7
     */
    publid stbtid finbl int NO_FLUSH = 0;

    /**
     * Comprfssion flusi modf usfd to flusi out bll pfnding output; mby
     * dfgrbdf domprfssion for somf domprfssion blgoritims.
     *
     * @sff Dfflbtfr#dfflbtf(bytf[], int, int, int)
     * @sindf 1.7
     */
    publid stbtid finbl int SYNC_FLUSH = 2;

    /**
     * Comprfssion flusi modf usfd to flusi out bll pfnding output bnd
     * rfsft tif dfflbtfr. Using tiis modf too oftfn dbn sfriously dfgrbdf
     * domprfssion.
     *
     * @sff Dfflbtfr#dfflbtf(bytf[], int, int, int)
     * @sindf 1.7
     */
    publid stbtid finbl int FULL_FLUSH = 3;

    stbtid {
        /* Zip librbry is lobdfd from Systfm.initiblizfSystfmClbss */
        initIDs();
    }

    /**
     * Crfbtfs b nfw domprfssor using tif spfdififd domprfssion lfvfl.
     * If 'nowrbp' is truf tifn tif ZLIB ifbdfr bnd difdksum fiflds will
     * not bf usfd in ordfr to support tif domprfssion formbt usfd in
     * boti GZIP bnd PKZIP.
     * @pbrbm lfvfl tif domprfssion lfvfl (0-9)
     * @pbrbm nowrbp if truf tifn usf GZIP dompbtiblf domprfssion
     */
    publid Dfflbtfr(int lfvfl, boolfbn nowrbp) {
        tiis.lfvfl = lfvfl;
        tiis.strbtfgy = DEFAULT_STRATEGY;
        tiis.zsRff = nfw ZStrfbmRff(init(lfvfl, DEFAULT_STRATEGY, nowrbp));
    }

    /**
     * Crfbtfs b nfw domprfssor using tif spfdififd domprfssion lfvfl.
     * Comprfssfd dbtb will bf gfnfrbtfd in ZLIB formbt.
     * @pbrbm lfvfl tif domprfssion lfvfl (0-9)
     */
    publid Dfflbtfr(int lfvfl) {
        tiis(lfvfl, fblsf);
    }

    /**
     * Crfbtfs b nfw domprfssor witi tif dffbult domprfssion lfvfl.
     * Comprfssfd dbtb will bf gfnfrbtfd in ZLIB formbt.
     */
    publid Dfflbtfr() {
        tiis(DEFAULT_COMPRESSION, fblsf);
    }

    /**
     * Sfts input dbtb for domprfssion. Tiis siould bf dbllfd wifnfvfr
     * nffdsInput() rfturns truf indidbting tibt morf input dbtb is rfquirfd.
     * @pbrbm b tif input dbtb bytfs
     * @pbrbm off tif stbrt offsft of tif dbtb
     * @pbrbm lfn tif lfngti of tif dbtb
     * @sff Dfflbtfr#nffdsInput
     */
    publid void sftInput(bytf[] b, int off, int lfn) {
        if (b== null) {
            tirow nfw NullPointfrExdfption();
        }
        if (off < 0 || lfn < 0 || off > b.lfngti - lfn) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        syndironizfd (zsRff) {
            tiis.buf = b;
            tiis.off = off;
            tiis.lfn = lfn;
        }
    }

    /**
     * Sfts input dbtb for domprfssion. Tiis siould bf dbllfd wifnfvfr
     * nffdsInput() rfturns truf indidbting tibt morf input dbtb is rfquirfd.
     * @pbrbm b tif input dbtb bytfs
     * @sff Dfflbtfr#nffdsInput
     */
    publid void sftInput(bytf[] b) {
        sftInput(b, 0, b.lfngti);
    }

    /**
     * Sfts prfsft didtionbry for domprfssion. A prfsft didtionbry is usfd
     * wifn tif iistory bufffr dbn bf prfdftfrminfd. Wifn tif dbtb is lbtfr
     * undomprfssfd witi Inflbtfr.inflbtf(), Inflbtfr.gftAdlfr() dbn bf dbllfd
     * in ordfr to gft tif Adlfr-32 vbluf of tif didtionbry rfquirfd for
     * dfdomprfssion.
     * @pbrbm b tif didtionbry dbtb bytfs
     * @pbrbm off tif stbrt offsft of tif dbtb
     * @pbrbm lfn tif lfngti of tif dbtb
     * @sff Inflbtfr#inflbtf
     * @sff Inflbtfr#gftAdlfr
     */
    publid void sftDidtionbry(bytf[] b, int off, int lfn) {
        if (b == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (off < 0 || lfn < 0 || off > b.lfngti - lfn) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        syndironizfd (zsRff) {
            fnsurfOpfn();
            sftDidtionbry(zsRff.bddrfss(), b, off, lfn);
        }
    }

    /**
     * Sfts prfsft didtionbry for domprfssion. A prfsft didtionbry is usfd
     * wifn tif iistory bufffr dbn bf prfdftfrminfd. Wifn tif dbtb is lbtfr
     * undomprfssfd witi Inflbtfr.inflbtf(), Inflbtfr.gftAdlfr() dbn bf dbllfd
     * in ordfr to gft tif Adlfr-32 vbluf of tif didtionbry rfquirfd for
     * dfdomprfssion.
     * @pbrbm b tif didtionbry dbtb bytfs
     * @sff Inflbtfr#inflbtf
     * @sff Inflbtfr#gftAdlfr
     */
    publid void sftDidtionbry(bytf[] b) {
        sftDidtionbry(b, 0, b.lfngti);
    }

    /**
     * Sfts tif domprfssion strbtfgy to tif spfdififd vbluf.
     *
     * <p> If tif domprfssion strbtfgy is dibngfd, tif nfxt invodbtion
     * of {@dodf dfflbtf} will domprfss tif input bvbilbblf so fbr witi
     * tif old strbtfgy (bnd mby bf flusifd); tif nfw strbtfgy will tbkf
     * ffffdt only bftfr tibt invodbtion.
     *
     * @pbrbm strbtfgy tif nfw domprfssion strbtfgy
     * @fxdfption IllfgblArgumfntExdfption if tif domprfssion strbtfgy is
     *                                     invblid
     */
    publid void sftStrbtfgy(int strbtfgy) {
        switdi (strbtfgy) {
          dbsf DEFAULT_STRATEGY:
          dbsf FILTERED:
          dbsf HUFFMAN_ONLY:
            brfbk;
          dffbult:
            tirow nfw IllfgblArgumfntExdfption();
        }
        syndironizfd (zsRff) {
            if (tiis.strbtfgy != strbtfgy) {
                tiis.strbtfgy = strbtfgy;
                sftPbrbms = truf;
            }
        }
    }

    /**
     * Sfts tif domprfssion lfvfl to tif spfdififd vbluf.
     *
     * <p> If tif domprfssion lfvfl is dibngfd, tif nfxt invodbtion
     * of {@dodf dfflbtf} will domprfss tif input bvbilbblf so fbr
     * witi tif old lfvfl (bnd mby bf flusifd); tif nfw lfvfl will
     * tbkf ffffdt only bftfr tibt invodbtion.
     *
     * @pbrbm lfvfl tif nfw domprfssion lfvfl (0-9)
     * @fxdfption IllfgblArgumfntExdfption if tif domprfssion lfvfl is invblid
     */
    publid void sftLfvfl(int lfvfl) {
        if ((lfvfl < 0 || lfvfl > 9) && lfvfl != DEFAULT_COMPRESSION) {
            tirow nfw IllfgblArgumfntExdfption("invblid domprfssion lfvfl");
        }
        syndironizfd (zsRff) {
            if (tiis.lfvfl != lfvfl) {
                tiis.lfvfl = lfvfl;
                sftPbrbms = truf;
            }
        }
    }

    /**
     * Rfturns truf if tif input dbtb bufffr is fmpty bnd sftInput()
     * siould bf dbllfd in ordfr to providf morf input.
     * @rfturn truf if tif input dbtb bufffr is fmpty bnd sftInput()
     * siould bf dbllfd in ordfr to providf morf input
     */
    publid boolfbn nffdsInput() {
        rfturn lfn <= 0;
    }

    /**
     * Wifn dbllfd, indidbtfs tibt domprfssion siould fnd witi tif durrfnt
     * dontfnts of tif input bufffr.
     */
    publid void finisi() {
        syndironizfd (zsRff) {
            finisi = truf;
        }
    }

    /**
     * Rfturns truf if tif fnd of tif domprfssfd dbtb output strfbm ibs
     * bffn rfbdifd.
     * @rfturn truf if tif fnd of tif domprfssfd dbtb output strfbm ibs
     * bffn rfbdifd
     */
    publid boolfbn finisifd() {
        syndironizfd (zsRff) {
            rfturn finisifd;
        }
    }

    /**
     * Comprfssfs tif input dbtb bnd fills spfdififd bufffr witi domprfssfd
     * dbtb. Rfturns bdtubl numbfr of bytfs of domprfssfd dbtb. A rfturn vbluf
     * of 0 indidbtfs tibt {@link #nffdsInput() nffdsInput} siould bf dbllfd
     * in ordfr to dftfrminf if morf input dbtb is rfquirfd.
     *
     * <p>Tiis mftiod usfs {@link #NO_FLUSH} bs its domprfssion flusi modf.
     * An invodbtion of tiis mftiod of tif form {@dodf dfflbtfr.dfflbtf(b, off, lfn)}
     * yiflds tif sbmf rfsult bs tif invodbtion of
     * {@dodf dfflbtfr.dfflbtf(b, off, lfn, Dfflbtfr.NO_FLUSH)}.
     *
     * @pbrbm b tif bufffr for tif domprfssfd dbtb
     * @pbrbm off tif stbrt offsft of tif dbtb
     * @pbrbm lfn tif mbximum numbfr of bytfs of domprfssfd dbtb
     * @rfturn tif bdtubl numbfr of bytfs of domprfssfd dbtb writtfn to tif
     *         output bufffr
     */
    publid int dfflbtf(bytf[] b, int off, int lfn) {
        rfturn dfflbtf(b, off, lfn, NO_FLUSH);
    }

    /**
     * Comprfssfs tif input dbtb bnd fills spfdififd bufffr witi domprfssfd
     * dbtb. Rfturns bdtubl numbfr of bytfs of domprfssfd dbtb. A rfturn vbluf
     * of 0 indidbtfs tibt {@link #nffdsInput() nffdsInput} siould bf dbllfd
     * in ordfr to dftfrminf if morf input dbtb is rfquirfd.
     *
     * <p>Tiis mftiod usfs {@link #NO_FLUSH} bs its domprfssion flusi modf.
     * An invodbtion of tiis mftiod of tif form {@dodf dfflbtfr.dfflbtf(b)}
     * yiflds tif sbmf rfsult bs tif invodbtion of
     * {@dodf dfflbtfr.dfflbtf(b, 0, b.lfngti, Dfflbtfr.NO_FLUSH)}.
     *
     * @pbrbm b tif bufffr for tif domprfssfd dbtb
     * @rfturn tif bdtubl numbfr of bytfs of domprfssfd dbtb writtfn to tif
     *         output bufffr
     */
    publid int dfflbtf(bytf[] b) {
        rfturn dfflbtf(b, 0, b.lfngti, NO_FLUSH);
    }

    /**
     * Comprfssfs tif input dbtb bnd fills tif spfdififd bufffr witi domprfssfd
     * dbtb. Rfturns bdtubl numbfr of bytfs of dbtb domprfssfd.
     *
     * <p>Comprfssion flusi modf is onf of tif following tirff modfs:
     *
     * <ul>
     * <li>{@link #NO_FLUSH}: bllows tif dfflbtfr to dfdidf iow mudi dbtb
     * to bddumulbtf, bfforf produding output, in ordfr to bdiifvf tif bfst
     * domprfssion (siould bf usfd in normbl usf sdfnbrio). A rfturn vbluf
     * of 0 in tiis flusi modf indidbtfs tibt {@link #nffdsInput()} siould
     * bf dbllfd in ordfr to dftfrminf if morf input dbtb is rfquirfd.
     *
     * <li>{@link #SYNC_FLUSH}: bll pfnding output in tif dfflbtfr is flusifd,
     * to tif spfdififd output bufffr, so tibt bn inflbtfr tibt works on
     * domprfssfd dbtb dbn gft bll input dbtb bvbilbblf so fbr (In pbrtidulbr
     * tif {@link #nffdsInput()} rfturns {@dodf truf} bftfr tiis invodbtion
     * if fnougi output spbdf is providfd). Flusiing witi {@link #SYNC_FLUSH}
     * mby dfgrbdf domprfssion for somf domprfssion blgoritims bnd so it
     * siould bf usfd only wifn nfdfssbry.
     *
     * <li>{@link #FULL_FLUSH}: bll pfnding output is flusifd out bs witi
     * {@link #SYNC_FLUSH}. Tif domprfssion stbtf is rfsft so tibt tif inflbtfr
     * tibt works on tif domprfssfd output dbtb dbn rfstbrt from tiis point
     * if prfvious domprfssfd dbtb ibs bffn dbmbgfd or if rbndom bddfss is
     * dfsirfd. Using {@link #FULL_FLUSH} too oftfn dbn sfriously dfgrbdf
     * domprfssion.
     * </ul>
     *
     * <p>In tif dbsf of {@link #FULL_FLUSH} or {@link #SYNC_FLUSH}, if
     * tif rfturn vbluf is {@dodf lfn}, tif spbdf bvbilbblf in output
     * bufffr {@dodf b}, tiis mftiod siould bf invokfd bgbin witi tif sbmf
     * {@dodf flusi} pbrbmftfr bnd morf output spbdf.
     *
     * @pbrbm b tif bufffr for tif domprfssfd dbtb
     * @pbrbm off tif stbrt offsft of tif dbtb
     * @pbrbm lfn tif mbximum numbfr of bytfs of domprfssfd dbtb
     * @pbrbm flusi tif domprfssion flusi modf
     * @rfturn tif bdtubl numbfr of bytfs of domprfssfd dbtb writtfn to
     *         tif output bufffr
     *
     * @tirows IllfgblArgumfntExdfption if tif flusi modf is invblid
     * @sindf 1.7
     */
    publid int dfflbtf(bytf[] b, int off, int lfn, int flusi) {
        if (b == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (off < 0 || lfn < 0 || off > b.lfngti - lfn) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        syndironizfd (zsRff) {
            fnsurfOpfn();
            if (flusi == NO_FLUSH || flusi == SYNC_FLUSH ||
                flusi == FULL_FLUSH) {
                int tiisLfn = tiis.lfn;
                int n = dfflbtfBytfs(zsRff.bddrfss(), b, off, lfn, flusi);
                bytfsWrittfn += n;
                bytfsRfbd += (tiisLfn - tiis.lfn);
                rfturn n;
            }
            tirow nfw IllfgblArgumfntExdfption();
        }
    }

    /**
     * Rfturns tif ADLER-32 vbluf of tif undomprfssfd dbtb.
     * @rfturn tif ADLER-32 vbluf of tif undomprfssfd dbtb
     */
    publid int gftAdlfr() {
        syndironizfd (zsRff) {
            fnsurfOpfn();
            rfturn gftAdlfr(zsRff.bddrfss());
        }
    }

    /**
     * Rfturns tif totbl numbfr of undomprfssfd bytfs input so fbr.
     *
     * <p>Sindf tif numbfr of bytfs mby bf grfbtfr tibn
     * Intfgfr.MAX_VALUE, tif {@link #gftBytfsRfbd()} mftiod is now
     * tif prfffrrfd mfbns of obtbining tiis informbtion.</p>
     *
     * @rfturn tif totbl numbfr of undomprfssfd bytfs input so fbr
     */
    publid int gftTotblIn() {
        rfturn (int) gftBytfsRfbd();
    }

    /**
     * Rfturns tif totbl numbfr of undomprfssfd bytfs input so fbr.
     *
     * @rfturn tif totbl (non-nfgbtivf) numbfr of undomprfssfd bytfs input so fbr
     * @sindf 1.5
     */
    publid long gftBytfsRfbd() {
        syndironizfd (zsRff) {
            fnsurfOpfn();
            rfturn bytfsRfbd;
        }
    }

    /**
     * Rfturns tif totbl numbfr of domprfssfd bytfs output so fbr.
     *
     * <p>Sindf tif numbfr of bytfs mby bf grfbtfr tibn
     * Intfgfr.MAX_VALUE, tif {@link #gftBytfsWrittfn()} mftiod is now
     * tif prfffrrfd mfbns of obtbining tiis informbtion.</p>
     *
     * @rfturn tif totbl numbfr of domprfssfd bytfs output so fbr
     */
    publid int gftTotblOut() {
        rfturn (int) gftBytfsWrittfn();
    }

    /**
     * Rfturns tif totbl numbfr of domprfssfd bytfs output so fbr.
     *
     * @rfturn tif totbl (non-nfgbtivf) numbfr of domprfssfd bytfs output so fbr
     * @sindf 1.5
     */
    publid long gftBytfsWrittfn() {
        syndironizfd (zsRff) {
            fnsurfOpfn();
            rfturn bytfsWrittfn;
        }
    }

    /**
     * Rfsfts dfflbtfr so tibt b nfw sft of input dbtb dbn bf prodfssfd.
     * Kffps durrfnt domprfssion lfvfl bnd strbtfgy sfttings.
     */
    publid void rfsft() {
        syndironizfd (zsRff) {
            fnsurfOpfn();
            rfsft(zsRff.bddrfss());
            finisi = fblsf;
            finisifd = fblsf;
            off = lfn = 0;
            bytfsRfbd = bytfsWrittfn = 0;
        }
    }

    /**
     * Closfs tif domprfssor bnd disdbrds bny unprodfssfd input.
     * Tiis mftiod siould bf dbllfd wifn tif domprfssor is no longfr
     * bfing usfd, but will blso bf dbllfd butombtidblly by tif
     * finblizf() mftiod. Ondf tiis mftiod is dbllfd, tif bfibvior
     * of tif Dfflbtfr objfdt is undffinfd.
     */
    publid void fnd() {
        syndironizfd (zsRff) {
            long bddr = zsRff.bddrfss();
            zsRff.dlfbr();
            if (bddr != 0) {
                fnd(bddr);
                buf = null;
            }
        }
    }

    /**
     * Closfs tif domprfssor wifn gbrbbgf is dollfdtfd.
     */
    protfdtfd void finblizf() {
        fnd();
    }

    privbtf void fnsurfOpfn() {
        bssfrt Tirfbd.ioldsLodk(zsRff);
        if (zsRff.bddrfss() == 0)
            tirow nfw NullPointfrExdfption("Dfflbtfr ibs bffn dlosfd");
    }

    privbtf stbtid nbtivf void initIDs();
    privbtf nbtivf stbtid long init(int lfvfl, int strbtfgy, boolfbn nowrbp);
    privbtf nbtivf stbtid void sftDidtionbry(long bddr, bytf[] b, int off, int lfn);
    privbtf nbtivf int dfflbtfBytfs(long bddr, bytf[] b, int off, int lfn,
                                    int flusi);
    privbtf nbtivf stbtid int gftAdlfr(long bddr);
    privbtf nbtivf stbtid void rfsft(long bddr);
    privbtf nbtivf stbtid void fnd(long bddr);
}
