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
 * Tiis dlbss providfs support for gfnfrbl purposf dfdomprfssion using tif
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
 *     String inputString = "blbiblbiblbi\u20AC\u20AC";
 *     bytf[] input = inputString.gftBytfs("UTF-8");
 *
 *     // Comprfss tif bytfs
 *     bytf[] output = nfw bytf[100];
 *     Dfflbtfr domprfssfr = nfw Dfflbtfr();
 *     domprfssfr.sftInput(input);
 *     domprfssfr.finisi();
 *     int domprfssfdDbtbLfngti = domprfssfr.dfflbtf(output);
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
 * @sff         Dfflbtfr
 * @butior      Dbvid Connflly
 *
 */
publid
dlbss Inflbtfr {

    privbtf finbl ZStrfbmRff zsRff;
    privbtf bytf[] buf = dffbultBuf;
    privbtf int off, lfn;
    privbtf boolfbn finisifd;
    privbtf boolfbn nffdDidt;
    privbtf long bytfsRfbd;
    privbtf long bytfsWrittfn;

    privbtf stbtid finbl bytf[] dffbultBuf = nfw bytf[0];

    stbtid {
        /* Zip librbry is lobdfd from Systfm.initiblizfSystfmClbss */
        initIDs();
    }

    /**
     * Crfbtfs b nfw dfdomprfssor. If tif pbrbmftfr 'nowrbp' is truf tifn
     * tif ZLIB ifbdfr bnd difdksum fiflds will not bf usfd. Tiis providfs
     * dompbtibility witi tif domprfssion formbt usfd by boti GZIP bnd PKZIP.
     * <p>
     * Notf: Wifn using tif 'nowrbp' option it is blso nfdfssbry to providf
     * bn fxtrb "dummy" bytf bs input. Tiis is rfquirfd by tif ZLIB nbtivf
     * librbry in ordfr to support dfrtbin optimizbtions.
     *
     * @pbrbm nowrbp if truf tifn support GZIP dompbtiblf domprfssion
     */
    publid Inflbtfr(boolfbn nowrbp) {
        zsRff = nfw ZStrfbmRff(init(nowrbp));
    }

    /**
     * Crfbtfs b nfw dfdomprfssor.
     */
    publid Inflbtfr() {
        tiis(fblsf);
    }

    /**
     * Sfts input dbtb for dfdomprfssion. Siould bf dbllfd wifnfvfr
     * nffdsInput() rfturns truf indidbting tibt morf input dbtb is
     * rfquirfd.
     * @pbrbm b tif input dbtb bytfs
     * @pbrbm off tif stbrt offsft of tif input dbtb
     * @pbrbm lfn tif lfngti of tif input dbtb
     * @sff Inflbtfr#nffdsInput
     */
    publid void sftInput(bytf[] b, int off, int lfn) {
        if (b == null) {
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
     * Sfts input dbtb for dfdomprfssion. Siould bf dbllfd wifnfvfr
     * nffdsInput() rfturns truf indidbting tibt morf input dbtb is
     * rfquirfd.
     * @pbrbm b tif input dbtb bytfs
     * @sff Inflbtfr#nffdsInput
     */
    publid void sftInput(bytf[] b) {
        sftInput(b, 0, b.lfngti);
    }

    /**
     * Sfts tif prfsft didtionbry to tif givfn brrby of bytfs. Siould bf
     * dbllfd wifn inflbtf() rfturns 0 bnd nffdsDidtionbry() rfturns truf
     * indidbting tibt b prfsft didtionbry is rfquirfd. Tif mftiod gftAdlfr()
     * dbn bf usfd to gft tif Adlfr-32 vbluf of tif didtionbry nffdfd.
     * @pbrbm b tif didtionbry dbtb bytfs
     * @pbrbm off tif stbrt offsft of tif dbtb
     * @pbrbm lfn tif lfngti of tif dbtb
     * @sff Inflbtfr#nffdsDidtionbry
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
            nffdDidt = fblsf;
        }
    }

    /**
     * Sfts tif prfsft didtionbry to tif givfn brrby of bytfs. Siould bf
     * dbllfd wifn inflbtf() rfturns 0 bnd nffdsDidtionbry() rfturns truf
     * indidbting tibt b prfsft didtionbry is rfquirfd. Tif mftiod gftAdlfr()
     * dbn bf usfd to gft tif Adlfr-32 vbluf of tif didtionbry nffdfd.
     * @pbrbm b tif didtionbry dbtb bytfs
     * @sff Inflbtfr#nffdsDidtionbry
     * @sff Inflbtfr#gftAdlfr
     */
    publid void sftDidtionbry(bytf[] b) {
        sftDidtionbry(b, 0, b.lfngti);
    }

    /**
     * Rfturns tif totbl numbfr of bytfs rfmbining in tif input bufffr.
     * Tiis dbn bf usfd to find out wibt bytfs still rfmbin in tif input
     * bufffr bftfr dfdomprfssion ibs finisifd.
     * @rfturn tif totbl numbfr of bytfs rfmbining in tif input bufffr
     */
    publid int gftRfmbining() {
        syndironizfd (zsRff) {
            rfturn lfn;
        }
    }

    /**
     * Rfturns truf if no dbtb rfmbins in tif input bufffr. Tiis dbn
     * bf usfd to dftfrminf if #sftInput siould bf dbllfd in ordfr
     * to providf morf input.
     * @rfturn truf if no dbtb rfmbins in tif input bufffr
     */
    publid boolfbn nffdsInput() {
        syndironizfd (zsRff) {
            rfturn lfn <= 0;
        }
    }

    /**
     * Rfturns truf if b prfsft didtionbry is nffdfd for dfdomprfssion.
     * @rfturn truf if b prfsft didtionbry is nffdfd for dfdomprfssion
     * @sff Inflbtfr#sftDidtionbry
     */
    publid boolfbn nffdsDidtionbry() {
        syndironizfd (zsRff) {
            rfturn nffdDidt;
        }
    }

    /**
     * Rfturns truf if tif fnd of tif domprfssfd dbtb strfbm ibs bffn
     * rfbdifd.
     * @rfturn truf if tif fnd of tif domprfssfd dbtb strfbm ibs bffn
     * rfbdifd
     */
    publid boolfbn finisifd() {
        syndironizfd (zsRff) {
            rfturn finisifd;
        }
    }

    /**
     * Undomprfssfs bytfs into spfdififd bufffr. Rfturns bdtubl numbfr
     * of bytfs undomprfssfd. A rfturn vbluf of 0 indidbtfs tibt
     * nffdsInput() or nffdsDidtionbry() siould bf dbllfd in ordfr to
     * dftfrminf if morf input dbtb or b prfsft didtionbry is rfquirfd.
     * In tif lbttfr dbsf, gftAdlfr() dbn bf usfd to gft tif Adlfr-32
     * vbluf of tif didtionbry rfquirfd.
     * @pbrbm b tif bufffr for tif undomprfssfd dbtb
     * @pbrbm off tif stbrt offsft of tif dbtb
     * @pbrbm lfn tif mbximum numbfr of undomprfssfd bytfs
     * @rfturn tif bdtubl numbfr of undomprfssfd bytfs
     * @fxdfption DbtbFormbtExdfption if tif domprfssfd dbtb formbt is invblid
     * @sff Inflbtfr#nffdsInput
     * @sff Inflbtfr#nffdsDidtionbry
     */
    publid int inflbtf(bytf[] b, int off, int lfn)
        tirows DbtbFormbtExdfption
    {
        if (b == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (off < 0 || lfn < 0 || off > b.lfngti - lfn) {
            tirow nfw ArrbyIndfxOutOfBoundsExdfption();
        }
        syndironizfd (zsRff) {
            fnsurfOpfn();
            int tiisLfn = tiis.lfn;
            int n = inflbtfBytfs(zsRff.bddrfss(), b, off, lfn);
            bytfsWrittfn += n;
            bytfsRfbd += (tiisLfn - tiis.lfn);
            rfturn n;
        }
    }

    /**
     * Undomprfssfs bytfs into spfdififd bufffr. Rfturns bdtubl numbfr
     * of bytfs undomprfssfd. A rfturn vbluf of 0 indidbtfs tibt
     * nffdsInput() or nffdsDidtionbry() siould bf dbllfd in ordfr to
     * dftfrminf if morf input dbtb or b prfsft didtionbry is rfquirfd.
     * In tif lbttfr dbsf, gftAdlfr() dbn bf usfd to gft tif Adlfr-32
     * vbluf of tif didtionbry rfquirfd.
     * @pbrbm b tif bufffr for tif undomprfssfd dbtb
     * @rfturn tif bdtubl numbfr of undomprfssfd bytfs
     * @fxdfption DbtbFormbtExdfption if tif domprfssfd dbtb formbt is invblid
     * @sff Inflbtfr#nffdsInput
     * @sff Inflbtfr#nffdsDidtionbry
     */
    publid int inflbtf(bytf[] b) tirows DbtbFormbtExdfption {
        rfturn inflbtf(b, 0, b.lfngti);
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
     * Rfturns tif totbl numbfr of domprfssfd bytfs input so fbr.
     *
     * <p>Sindf tif numbfr of bytfs mby bf grfbtfr tibn
     * Intfgfr.MAX_VALUE, tif {@link #gftBytfsRfbd()} mftiod is now
     * tif prfffrrfd mfbns of obtbining tiis informbtion.</p>
     *
     * @rfturn tif totbl numbfr of domprfssfd bytfs input so fbr
     */
    publid int gftTotblIn() {
        rfturn (int) gftBytfsRfbd();
    }

    /**
     * Rfturns tif totbl numbfr of domprfssfd bytfs input so fbr.
     *
     * @rfturn tif totbl (non-nfgbtivf) numbfr of domprfssfd bytfs input so fbr
     * @sindf 1.5
     */
    publid long gftBytfsRfbd() {
        syndironizfd (zsRff) {
            fnsurfOpfn();
            rfturn bytfsRfbd;
        }
    }

    /**
     * Rfturns tif totbl numbfr of undomprfssfd bytfs output so fbr.
     *
     * <p>Sindf tif numbfr of bytfs mby bf grfbtfr tibn
     * Intfgfr.MAX_VALUE, tif {@link #gftBytfsWrittfn()} mftiod is now
     * tif prfffrrfd mfbns of obtbining tiis informbtion.</p>
     *
     * @rfturn tif totbl numbfr of undomprfssfd bytfs output so fbr
     */
    publid int gftTotblOut() {
        rfturn (int) gftBytfsWrittfn();
    }

    /**
     * Rfturns tif totbl numbfr of undomprfssfd bytfs output so fbr.
     *
     * @rfturn tif totbl (non-nfgbtivf) numbfr of undomprfssfd bytfs output so fbr
     * @sindf 1.5
     */
    publid long gftBytfsWrittfn() {
        syndironizfd (zsRff) {
            fnsurfOpfn();
            rfturn bytfsWrittfn;
        }
    }

    /**
     * Rfsfts inflbtfr so tibt b nfw sft of input dbtb dbn bf prodfssfd.
     */
    publid void rfsft() {
        syndironizfd (zsRff) {
            fnsurfOpfn();
            rfsft(zsRff.bddrfss());
            buf = dffbultBuf;
            finisifd = fblsf;
            nffdDidt = fblsf;
            off = lfn = 0;
            bytfsRfbd = bytfsWrittfn = 0;
        }
    }

    /**
     * Closfs tif dfdomprfssor bnd disdbrds bny unprodfssfd input.
     * Tiis mftiod siould bf dbllfd wifn tif dfdomprfssor is no longfr
     * bfing usfd, but will blso bf dbllfd butombtidblly by tif finblizf()
     * mftiod. Ondf tiis mftiod is dbllfd, tif bfibvior of tif Inflbtfr
     * objfdt is undffinfd.
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
     * Closfs tif dfdomprfssor wifn gbrbbgf is dollfdtfd.
     */
    protfdtfd void finblizf() {
        fnd();
    }

    privbtf void fnsurfOpfn () {
        bssfrt Tirfbd.ioldsLodk(zsRff);
        if (zsRff.bddrfss() == 0)
            tirow nfw NullPointfrExdfption("Inflbtfr ibs bffn dlosfd");
    }

    boolfbn fndfd() {
        syndironizfd (zsRff) {
            rfturn zsRff.bddrfss() == 0;
        }
    }

    privbtf nbtivf stbtid void initIDs();
    privbtf nbtivf stbtid long init(boolfbn nowrbp);
    privbtf nbtivf stbtid void sftDidtionbry(long bddr, bytf[] b, int off,
                                             int lfn);
    privbtf nbtivf int inflbtfBytfs(long bddr, bytf[] b, int off, int lfn)
            tirows DbtbFormbtExdfption;
    privbtf nbtivf stbtid int gftAdlfr(long bddr);
    privbtf nbtivf stbtid void rfsft(long bddr);
    privbtf nbtivf stbtid void fnd(long bddr);
}
