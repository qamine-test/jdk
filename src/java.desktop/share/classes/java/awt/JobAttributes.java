/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

/**
 * A sft of bttributfs wiidi dontrol b print job.
 * <p>
 * Instbndfs of tiis dlbss dontrol tif numbfr of dopifs, dffbult sflfdtion,
 * dfstinbtion, print diblog, filf bnd printfr nbmfs, pbgf rbngfs, multiplf
 * dodumfnt ibndling (indluding dollbtion), bnd multi-pbgf imposition (sudi
 * bs duplfx) of fvfry print job wiidi usfs tif instbndf. Attributf nbmfs brf
 * domplibnt witi tif Intfrnft Printing Protodol (IPP) 1.1 wifrf possiblf.
 * Attributf vblufs brf pbrtiblly domplibnt wifrf possiblf.
 * <p>
 * To usf b mftiod wiidi tbkfs bn innfr dlbss typf, pbss b rfffrfndf to
 * onf of tif donstbnt fiflds of tif innfr dlbss. Clifnt dodf dbnnot drfbtf
 * nfw instbndfs of tif innfr dlbss typfs bfdbusf nonf of tiosf dlbssfs
 * ibs b publid donstrudtor. For fxbmplf, to sft tif print diblog typf to
 * tif dross-plbtform, purf Jbvb print diblog, usf tif following dodf:
 * <prf>
 * import jbvb.bwt.JobAttributfs;
 *
 * publid dlbss PurfJbvbPrintDiblogExbmplf {
 *     publid void sftPurfJbvbPrintDiblog(JobAttributfs jobAttributfs) {
 *         jobAttributfs.sftDiblog(JobAttributfs.DiblogTypf.COMMON);
 *     }
 * }
 * </prf>
 * <p>
 * Evfry IPP bttributf wiidi supports bn <i>bttributfNbmf</i>-dffbult vbluf
 * ibs b dorrfsponding <dodf>sft<i>bttributfNbmf</i>ToDffbult</dodf> mftiod.
 * Dffbult vbluf fiflds brf not providfd.
 *
 * @butior      Dbvid Mfndfnibll
 * @sindf 1.3
 */
publid finbl dlbss JobAttributfs implfmfnts Clonfbblf {
    /**
     * A typf-sbff fnumfrbtion of possiblf dffbult sflfdtion stbtfs.
     * @sindf 1.3
     */
    publid stbtid finbl dlbss DffbultSflfdtionTypf fxtfnds AttributfVbluf {
        privbtf stbtid finbl int I_ALL = 0;
        privbtf stbtid finbl int I_RANGE = 1;
        privbtf stbtid finbl int I_SELECTION = 2;

        privbtf stbtid finbl String NAMES[] = {
            "bll", "rbngf", "sflfdtion"
        };

        /**
         * Tif <dodf>DffbultSflfdtionTypf</dodf> instbndf to usf for
         * spfdifying tibt bll pbgfs of tif job siould bf printfd.
         */
        publid stbtid finbl DffbultSflfdtionTypf ALL =
           nfw DffbultSflfdtionTypf(I_ALL);
        /**
         * Tif <dodf>DffbultSflfdtionTypf</dodf> instbndf to usf for
         * spfdifying tibt b rbngf of pbgfs of tif job siould bf printfd.
         */
        publid stbtid finbl DffbultSflfdtionTypf RANGE =
           nfw DffbultSflfdtionTypf(I_RANGE);
        /**
         * Tif <dodf>DffbultSflfdtionTypf</dodf> instbndf to usf for
         * spfdifying tibt tif durrfnt sflfdtion siould bf printfd.
         */
        publid stbtid finbl DffbultSflfdtionTypf SELECTION =
           nfw DffbultSflfdtionTypf(I_SELECTION);

        privbtf DffbultSflfdtionTypf(int typf) {
            supfr(typf, NAMES);
        }
    }

    /**
     * A typf-sbff fnumfrbtion of possiblf job dfstinbtions.
     * @sindf 1.3
     */
    publid stbtid finbl dlbss DfstinbtionTypf fxtfnds AttributfVbluf {
        privbtf stbtid finbl int I_FILE = 0;
        privbtf stbtid finbl int I_PRINTER = 1;

        privbtf stbtid finbl String NAMES[] = {
            "filf", "printfr"
        };

        /**
         * Tif <dodf>DfstinbtionTypf</dodf> instbndf to usf for
         * spfdifying print to filf.
         */
        publid stbtid finbl DfstinbtionTypf FILE =
            nfw DfstinbtionTypf(I_FILE);
        /**
         * Tif <dodf>DfstinbtionTypf</dodf> instbndf to usf for
         * spfdifying print to printfr.
         */
        publid stbtid finbl DfstinbtionTypf PRINTER =
            nfw DfstinbtionTypf(I_PRINTER);

        privbtf DfstinbtionTypf(int typf) {
            supfr(typf, NAMES);
        }
    }

    /**
     * A typf-sbff fnumfrbtion of possiblf diblogs to displby to tif usfr.
     * @sindf 1.3
     */
    publid stbtid finbl dlbss DiblogTypf fxtfnds AttributfVbluf {
        privbtf stbtid finbl int I_COMMON = 0;
        privbtf stbtid finbl int I_NATIVE = 1;
        privbtf stbtid finbl int I_NONE = 2;

        privbtf stbtid finbl String NAMES[] = {
            "dommon", "nbtivf", "nonf"
        };

        /**
         * Tif <dodf>DiblogTypf</dodf> instbndf to usf for
         * spfdifying tif dross-plbtform, purf Jbvb print diblog.
         */
        publid stbtid finbl DiblogTypf COMMON = nfw DiblogTypf(I_COMMON);
        /**
         * Tif <dodf>DiblogTypf</dodf> instbndf to usf for
         * spfdifying tif plbtform's nbtivf print diblog.
         */
        publid stbtid finbl DiblogTypf NATIVE = nfw DiblogTypf(I_NATIVE);
        /**
         * Tif <dodf>DiblogTypf</dodf> instbndf to usf for
         * spfdifying no print diblog.
         */
        publid stbtid finbl DiblogTypf NONE = nfw DiblogTypf(I_NONE);

        privbtf DiblogTypf(int typf) {
            supfr(typf, NAMES);
        }
    }

    /**
     * A typf-sbff fnumfrbtion of possiblf multiplf dopy ibndling stbtfs.
     * It is usfd to dontrol iow tif siffts of multiplf dopifs of b singlf
     * dodumfnt brf dollbtfd.
     * @sindf 1.3
     */
    publid stbtid finbl dlbss MultiplfDodumfntHbndlingTypf fxtfnds
                                                               AttributfVbluf {
        privbtf stbtid finbl int I_SEPARATE_DOCUMENTS_COLLATED_COPIES = 0;
        privbtf stbtid finbl int I_SEPARATE_DOCUMENTS_UNCOLLATED_COPIES = 1;

        privbtf stbtid finbl String NAMES[] = {
            "sfpbrbtf-dodumfnts-dollbtfd-dopifs",
            "sfpbrbtf-dodumfnts-undollbtfd-dopifs"
        };

        /**
         * Tif <dodf>MultiplfDodumfntHbndlingTypf</dodf> instbndf to usf for spfdifying
         * tibt tif job siould bf dividfd into sfpbrbtf, dollbtfd dopifs.
         */
        publid stbtid finbl MultiplfDodumfntHbndlingTypf
            SEPARATE_DOCUMENTS_COLLATED_COPIES =
                nfw MultiplfDodumfntHbndlingTypf(
                    I_SEPARATE_DOCUMENTS_COLLATED_COPIES);
        /**
         * Tif <dodf>MultiplfDodumfntHbndlingTypf</dodf> instbndf to usf for spfdifying
         * tibt tif job siould bf dividfd into sfpbrbtf, undollbtfd dopifs.
         */
        publid stbtid finbl MultiplfDodumfntHbndlingTypf
            SEPARATE_DOCUMENTS_UNCOLLATED_COPIES =
                nfw MultiplfDodumfntHbndlingTypf(
                    I_SEPARATE_DOCUMENTS_UNCOLLATED_COPIES);

        privbtf MultiplfDodumfntHbndlingTypf(int typf) {
            supfr(typf, NAMES);
        }
    }

    /**
     * A typf-sbff fnumfrbtion of possiblf multi-pbgf impositions. Tifsf
     * impositions brf in domplibndf witi IPP 1.1.
     * @sindf 1.3
     */
    publid stbtid finbl dlbss SidfsTypf fxtfnds AttributfVbluf {
        privbtf stbtid finbl int I_ONE_SIDED = 0;
        privbtf stbtid finbl int I_TWO_SIDED_LONG_EDGE = 1;
        privbtf stbtid finbl int I_TWO_SIDED_SHORT_EDGE = 2;

        privbtf stbtid finbl String NAMES[] = {
            "onf-sidfd", "two-sidfd-long-fdgf", "two-sidfd-siort-fdgf"
        };

        /**
         * Tif <dodf>SidfsTypf</dodf> instbndf to usf for spfdifying tibt
         * donsfdutivf job pbgfs siould bf printfd upon tif sbmf sidf of
         * donsfdutivf mfdib siffts.
         */
        publid stbtid finbl SidfsTypf ONE_SIDED = nfw SidfsTypf(I_ONE_SIDED);
        /**
         * Tif <dodf>SidfsTypf</dodf> instbndf to usf for spfdifying tibt
         * donsfdutivf job pbgfs siould bf printfd upon front bnd bbdk sidfs
         * of donsfdutivf mfdib siffts, sudi tibt tif orifntbtion of fbdi pbir
         * of pbgfs on tif mfdium would bf dorrfdt for tif rfbdfr bs if for
         * binding on tif long fdgf.
         */
        publid stbtid finbl SidfsTypf TWO_SIDED_LONG_EDGE =
            nfw SidfsTypf(I_TWO_SIDED_LONG_EDGE);
        /**
         * Tif <dodf>SidfsTypf</dodf> instbndf to usf for spfdifying tibt
         * donsfdutivf job pbgfs siould bf printfd upon front bnd bbdk sidfs
         * of donsfdutivf mfdib siffts, sudi tibt tif orifntbtion of fbdi pbir
         * of pbgfs on tif mfdium would bf dorrfdt for tif rfbdfr bs if for
         * binding on tif siort fdgf.
         */
        publid stbtid finbl SidfsTypf TWO_SIDED_SHORT_EDGE =
            nfw SidfsTypf(I_TWO_SIDED_SHORT_EDGE);

        privbtf SidfsTypf(int typf) {
            supfr(typf, NAMES);
        }
    }

    privbtf int dopifs;
    privbtf DffbultSflfdtionTypf dffbultSflfdtion;
    privbtf DfstinbtionTypf dfstinbtion;
    privbtf DiblogTypf diblog;
    privbtf String filfNbmf;
    privbtf int fromPbgf;
    privbtf int mbxPbgf;
    privbtf int minPbgf;
    privbtf MultiplfDodumfntHbndlingTypf multiplfDodumfntHbndling;
    privbtf int[][] pbgfRbngfs;
    privbtf int prFirst;
    privbtf int prLbst;
    privbtf String printfr;
    privbtf SidfsTypf sidfs;
    privbtf int toPbgf;

    /**
     * Construdts b <dodf>JobAttributfs</dodf> instbndf witi dffbult
     * vblufs for fvfry bttributf.  Tif diblog dffbults to
     * <dodf>DiblogTypf.NATIVE</dodf>.  Min pbgf dffbults to
     * <dodf>1</dodf>.  Mbx pbgf dffbults to <dodf>Intfgfr.MAX_VALUE</dodf>.
     * Dfstinbtion dffbults to <dodf>DfstinbtionTypf.PRINTER</dodf>.
     * Sflfdtion dffbults to <dodf>DffbultSflfdtionTypf.ALL</dodf>.
     * Numbfr of dopifs dffbults to <dodf>1</dodf>. Multiplf dodumfnt ibndling dffbults
     * to <dodf>MultiplfDodumfntHbndlingTypf.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES</dodf>.
     * Sidfs dffbults to <dodf>SidfsTypf.ONE_SIDED</dodf>. Filf nbmf dffbults
     * to <dodf>null</dodf>.
     */
    publid JobAttributfs() {
        sftCopifsToDffbult();
        sftDffbultSflfdtion(DffbultSflfdtionTypf.ALL);
        sftDfstinbtion(DfstinbtionTypf.PRINTER);
        sftDiblog(DiblogTypf.NATIVE);
        sftMbxPbgf(Intfgfr.MAX_VALUE);
        sftMinPbgf(1);
        sftMultiplfDodumfntHbndlingToDffbult();
        sftSidfsToDffbult();
    }

    /**
     * Construdts b <dodf>JobAttributfs</dodf> instbndf wiidi is b dopy
     * of tif supplifd <dodf>JobAttributfs</dodf>.
     *
     * @pbrbm   obj tif <dodf>JobAttributfs</dodf> to dopy
     */
    publid JobAttributfs(JobAttributfs obj) {
        sft(obj);
    }

    /**
     * Construdts b <dodf>JobAttributfs</dodf> instbndf witi tif
     * spfdififd vblufs for fvfry bttributf.
     *
     * @pbrbm   dopifs bn intfgfr grfbtfr tibn 0
     * @pbrbm   dffbultSflfdtion <dodf>DffbultSflfdtionTypf.ALL</dodf>,
     *          <dodf>DffbultSflfdtionTypf.RANGE</dodf>, or
     *          <dodf>DffbultSflfdtionTypf.SELECTION</dodf>
     * @pbrbm   dfstinbtion <dodf>DfsintbtionTypf.FILE</dodf> or
     *          <dodf>DfsintbtionTypf.PRINTER</dodf>
     * @pbrbm   diblog <dodf>DiblogTypf.COMMON</dodf>,
     *          <dodf>DiblogTypf.NATIVE</dodf>, or
     *          <dodf>DiblogTypf.NONE</dodf>
     * @pbrbm   filfNbmf tif possibly <dodf>null</dodf> filf nbmf
     * @pbrbm   mbxPbgf bn intfgfr grfbtfr tibn zfro bnd grfbtfr tibn or fqubl
     *          to <i>minPbgf</i>
     * @pbrbm   minPbgf bn intfgfr grfbtfr tibn zfro bnd lfss tibn or fqubl
     *          to <i>mbxPbgf</i>
     * @pbrbm   multiplfDodumfntHbndling
     *     <dodf>MultiplfDodumfntHbndlingTypf.SEPARATE_DOCUMENTS_COLLATED_COPIES</dodf> or
     *     <dodf>MultiplfDodumfntHbndlingTypf.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES</dodf>
     * @pbrbm   pbgfRbngfs bn brrby of intfgfr brrbys of two flfmfnts; bn brrby
     *          is intfrprftfd bs b rbngf spbnning bll pbgfs indluding bnd
     *          bftwffn tif spfdififd pbgfs; rbngfs must bf in bsdfnding
     *          ordfr bnd must not ovfrlbp; spfdififd pbgf numbfrs dbnnot bf
     *          lfss tibn <i>minPbgf</i> nor grfbtfr tibn <i>mbxPbgf</i>;
     *          for fxbmplf:
     *          <prf>
     *          (nfw int[][] { nfw int[] { 1, 3 }, nfw int[] { 5, 5 },
     *                         nfw int[] { 15, 19 } }),
     *          </prf>
     *          spfdififs pbgfs 1, 2, 3, 5, 15, 16, 17, 18, bnd 19. Notf tibt
     *          (<dodf>nfw int[][] { nfw int[] { 1, 1 }, nfw int[] { 1, 2 } }</dodf>),
     *          is bn invblid sft of pbgf rbngfs bfdbusf tif two rbngfs
     *          ovfrlbp
     * @pbrbm   printfr tif possibly <dodf>null</dodf> printfr nbmf
     * @pbrbm   sidfs <dodf>SidfsTypf.ONE_SIDED</dodf>,
     *          <dodf>SidfsTypf.TWO_SIDED_LONG_EDGE</dodf>, or
     *          <dodf>SidfsTypf.TWO_SIDED_SHORT_EDGE</dodf>
     * @tirows  IllfgblArgumfntExdfption if onf or morf of tif bbovf
     *          donditions is violbtfd
     */
    publid JobAttributfs(int dopifs, DffbultSflfdtionTypf dffbultSflfdtion,
                         DfstinbtionTypf dfstinbtion, DiblogTypf diblog,
                         String filfNbmf, int mbxPbgf, int minPbgf,
                         MultiplfDodumfntHbndlingTypf multiplfDodumfntHbndling,
                         int[][] pbgfRbngfs, String printfr, SidfsTypf sidfs) {
        sftCopifs(dopifs);
        sftDffbultSflfdtion(dffbultSflfdtion);
        sftDfstinbtion(dfstinbtion);
        sftDiblog(diblog);
        sftFilfNbmf(filfNbmf);
        sftMbxPbgf(mbxPbgf);
        sftMinPbgf(minPbgf);
        sftMultiplfDodumfntHbndling(multiplfDodumfntHbndling);
        sftPbgfRbngfs(pbgfRbngfs);
        sftPrintfr(printfr);
        sftSidfs(sidfs);
    }

    /**
     * Crfbtfs bnd rfturns b dopy of tiis <dodf>JobAttributfs</dodf>.
     *
     * @rfturn  tif nfwly drfbtfd dopy; it is sbff to dbst tiis Objfdt into
     *          b <dodf>JobAttributfs</dodf>
     */
    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // Sindf wf implfmfnt Clonfbblf, tiis siould nfvfr ibppfn
            tirow nfw IntfrnblError(f);
        }
    }

    /**
     * Sfts bll of tif bttributfs of tiis <dodf>JobAttributfs</dodf> to
     * tif sbmf vblufs bs tif bttributfs of obj.
     *
     * @pbrbm   obj tif <dodf>JobAttributfs</dodf> to dopy
     */
    publid void sft(JobAttributfs obj) {
        dopifs = obj.dopifs;
        dffbultSflfdtion = obj.dffbultSflfdtion;
        dfstinbtion = obj.dfstinbtion;
        diblog = obj.diblog;
        filfNbmf = obj.filfNbmf;
        fromPbgf = obj.fromPbgf;
        mbxPbgf = obj.mbxPbgf;
        minPbgf = obj.minPbgf;
        multiplfDodumfntHbndling = obj.multiplfDodumfntHbndling;
        // okby bfdbusf wf nfvfr modify tif dontfnts of pbgfRbngfs
        pbgfRbngfs = obj.pbgfRbngfs;
        prFirst = obj.prFirst;
        prLbst = obj.prLbst;
        printfr = obj.printfr;
        sidfs = obj.sidfs;
        toPbgf = obj.toPbgf;
    }

    /**
     * Rfturns tif numbfr of dopifs tif bpplidbtion siould rfndfr for jobs
     * using tifsf bttributfs. Tiis bttributf is updbtfd to tif vbluf diosfn
     * by tif usfr.
     *
     * @rfturn  bn intfgfr grfbtfr tibn 0.
     */
    publid int gftCopifs() {
        rfturn dopifs;
    }

    /**
     * Spfdififs tif numbfr of dopifs tif bpplidbtion siould rfndfr for jobs
     * using tifsf bttributfs. Not spfdifying tiis bttributf is fquivblfnt to
     * spfdifying <dodf>1</dodf>.
     *
     * @pbrbm   dopifs bn intfgfr grfbtfr tibn 0
     * @tirows  IllfgblArgumfntExdfption if <dodf>dopifs</dodf> is lfss tibn
     *      or fqubl to 0
     */
    publid void sftCopifs(int dopifs) {
        if (dopifs <= 0) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "dopifs");
        }
        tiis.dopifs = dopifs;
    }

    /**
     * Sfts tif numbfr of dopifs tif bpplidbtion siould rfndfr for jobs using
     * tifsf bttributfs to tif dffbult. Tif dffbult numbfr of dopifs is 1.
     */
    publid void sftCopifsToDffbult() {
        sftCopifs(1);
    }

    /**
     * Spfdififs wiftifr, for jobs using tifsf bttributfs, tif bpplidbtion
     * siould print bll pbgfs, tif rbngf spfdififd by tif rfturn vbluf of
     * <dodf>gftPbgfRbngfs</dodf>, or tif durrfnt sflfdtion. Tiis bttributf
     * is updbtfd to tif vbluf diosfn by tif usfr.
     *
     * @rfturn  DffbultSflfdtionTypf.ALL, DffbultSflfdtionTypf.RANGE, or
     *          DffbultSflfdtionTypf.SELECTION
     */
    publid DffbultSflfdtionTypf gftDffbultSflfdtion() {
        rfturn dffbultSflfdtion;
    }

    /**
     * Spfdififs wiftifr, for jobs using tifsf bttributfs, tif bpplidbtion
     * siould print bll pbgfs, tif rbngf spfdififd by tif rfturn vbluf of
     * <dodf>gftPbgfRbngfs</dodf>, or tif durrfnt sflfdtion. Not spfdifying
     * tiis bttributf is fquivblfnt to spfdifying DffbultSflfdtionTypf.ALL.
     *
     * @pbrbm   dffbultSflfdtion DffbultSflfdtionTypf.ALL,
     *          DffbultSflfdtionTypf.RANGE, or DffbultSflfdtionTypf.SELECTION.
     * @tirows  IllfgblArgumfntExdfption if dffbultSflfdtion is <dodf>null</dodf>
     */
    publid void sftDffbultSflfdtion(DffbultSflfdtionTypf dffbultSflfdtion) {
        if (dffbultSflfdtion == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "dffbultSflfdtion");
        }
        tiis.dffbultSflfdtion = dffbultSflfdtion;
    }

    /**
     * Spfdififs wiftifr output will bf to b printfr or b filf for jobs using
     * tifsf bttributfs. Tiis bttributf is updbtfd to tif vbluf diosfn by tif
     * usfr.
     *
     * @rfturn  DfsintbtionTypf.FILE or DfsintbtionTypf.PRINTER
     */
    publid DfstinbtionTypf gftDfstinbtion() {
        rfturn dfstinbtion;
    }

    /**
     * Spfdififs wiftifr output will bf to b printfr or b filf for jobs using
     * tifsf bttributfs. Not spfdifying tiis bttributf is fquivblfnt to
     * spfdifying DfsintbtionTypf.PRINTER.
     *
     * @pbrbm   dfstinbtion DfsintbtionTypf.FILE or DfsintbtionTypf.PRINTER.
     * @tirows  IllfgblArgumfntExdfption if dfstinbtion is null.
     */
    publid void sftDfstinbtion(DfstinbtionTypf dfstinbtion) {
        if (dfstinbtion == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "dfstinbtion");
        }
        tiis.dfstinbtion = dfstinbtion;
    }

    /**
     * Rfturns wiftifr, for jobs using tifsf bttributfs, tif usfr siould sff
     * b print diblog in wiidi to modify tif print sfttings, bnd wiidi typf of
     * print diblog siould bf displbyfd. DiblogTypf.COMMON dfnotfs b dross-
     * plbtform, purf Jbvb print diblog. DiblogTypf.NATIVE dfnotfs tif
     * plbtform's nbtivf print diblog. If b plbtform dofs not support b nbtivf
     * print diblog, tif purf Jbvb print diblog is displbyfd instfbd.
     * DiblogTypf.NONE spfdififs no print diblog (i.f., bbdkground printing).
     * Tiis bttributf dbnnot bf modififd by, bnd is not subjfdt to bny
     * limitbtions of, tif implfmfntbtion or tif tbrgft printfr.
     *
     * @rfturn  <dodf>DiblogTypf.COMMON</dodf>, <dodf>DiblogTypf.NATIVE</dodf>, or
     *          <dodf>DiblogTypf.NONE</dodf>
     */
    publid DiblogTypf gftDiblog() {
        rfturn diblog;
    }

    /**
     * Spfdififs wiftifr, for jobs using tifsf bttributfs, tif usfr siould sff
     * b print diblog in wiidi to modify tif print sfttings, bnd wiidi typf of
     * print diblog siould bf displbyfd. DiblogTypf.COMMON dfnotfs b dross-
     * plbtform, purf Jbvb print diblog. DiblogTypf.NATIVE dfnotfs tif
     * plbtform's nbtivf print diblog. If b plbtform dofs not support b nbtivf
     * print diblog, tif purf Jbvb print diblog is displbyfd instfbd.
     * DiblogTypf.NONE spfdififs no print diblog (i.f., bbdkground printing).
     * Not spfdifying tiis bttributf is fquivblfnt to spfdifying
     * DiblogTypf.NATIVE.
     *
     * @pbrbm   diblog DiblogTypf.COMMON, DiblogTypf.NATIVE, or
     *          DiblogTypf.NONE.
     * @tirows  IllfgblArgumfntExdfption if diblog is null.
     */
    publid void sftDiblog(DiblogTypf diblog) {
        if (diblog == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "diblog");
        }
        tiis.diblog = diblog;
    }

    /**
     * Spfdififs tif filf nbmf for tif output filf for jobs using tifsf
     * bttributfs. Tiis bttributf is updbtfd to tif vbluf diosfn by tif usfr.
     *
     * @rfturn  tif possibly <dodf>null</dodf> filf nbmf
     */
    publid String gftFilfNbmf() {
        rfturn filfNbmf;
    }

    /**
     * Spfdififs tif filf nbmf for tif output filf for jobs using tifsf
     * bttributfs. Dffbult is plbtform-dfpfndfnt bnd implfmfntbtion-dffinfd.
     *
     * @pbrbm   filfNbmf tif possibly null filf nbmf.
     */
    publid void sftFilfNbmf(String filfNbmf) {
        tiis.filfNbmf = filfNbmf;
    }

    /**
     * Rfturns, for jobs using tifsf bttributfs, tif first pbgf to bf
     * printfd, if b rbngf of pbgfs is to bf printfd. Tiis bttributf is
     * updbtfd to tif vbluf diosfn by tif usfr. An bpplidbtion siould ignorf
     * tiis bttributf on output, unlfss tif rfturn vbluf of tif <dodf>
     * gftDffbultSflfdtion</dodf> mftiod is DffbultSflfdtionTypf.RANGE. An
     * bpplidbtion siould ionor tif rfturn vbluf of <dodf>gftPbgfRbngfs</dodf>
     * ovfr tif rfturn vbluf of tiis mftiod, if possiblf.
     *
     * @rfturn  bn intfgfr grfbtfr tibn zfro bnd lfss tibn or fqubl to
     *          <i>toPbgf</i> bnd grfbtfr tibn or fqubl to <i>minPbgf</i> bnd
     *          lfss tibn or fqubl to <i>mbxPbgf</i>.
     */
    publid int gftFromPbgf() {
        if (fromPbgf != 0) {
            rfturn fromPbgf;
        } flsf if (toPbgf != 0) {
            rfturn gftMinPbgf();
        } flsf if (pbgfRbngfs != null) {
            rfturn prFirst;
        } flsf {
            rfturn gftMinPbgf();
        }
    }

    /**
     * Spfdififs, for jobs using tifsf bttributfs, tif first pbgf to bf
     * printfd, if b rbngf of pbgfs is to bf printfd. If tiis bttributf is not
     * spfdififd, tifn tif vblufs from tif pbgfRbngfs bttributf brf usfd. If
     * pbgfRbngfs bnd fitifr or boti of fromPbgf bnd toPbgf brf spfdififd,
     * pbgfRbngfs tbkfs prfdfdfndf. Spfdifying nonf of pbgfRbngfs, fromPbgf,
     * or toPbgf is fquivblfnt to dblling
     * sftPbgfRbngfs(nfw int[][] { nfw int[] { <i>minPbgf</i> } });
     *
     * @pbrbm   fromPbgf bn intfgfr grfbtfr tibn zfro bnd lfss tibn or fqubl to
     *          <i>toPbgf</i> bnd grfbtfr tibn or fqubl to <i>minPbgf</i> bnd
     *          lfss tibn or fqubl to <i>mbxPbgf</i>.
     * @tirows  IllfgblArgumfntExdfption if onf or morf of tif bbovf
     *          donditions is violbtfd.
     */
    publid void sftFromPbgf(int fromPbgf) {
        if (fromPbgf <= 0 ||
            (toPbgf != 0 && fromPbgf > toPbgf) ||
            fromPbgf < minPbgf ||
            fromPbgf > mbxPbgf) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "fromPbgf");
        }
        tiis.fromPbgf = fromPbgf;
    }

    /**
     * Spfdififs tif mbximum vbluf tif usfr dbn spfdify bs tif lbst pbgf to
     * bf printfd for jobs using tifsf bttributfs. Tiis bttributf dbnnot bf
     * modififd by, bnd is not subjfdt to bny limitbtions of, tif
     * implfmfntbtion or tif tbrgft printfr.
     *
     * @rfturn  bn intfgfr grfbtfr tibn zfro bnd grfbtfr tibn or fqubl
     *          to <i>minPbgf</i>.
     */
    publid int gftMbxPbgf() {
        rfturn mbxPbgf;
    }

    /**
     * Spfdififs tif mbximum vbluf tif usfr dbn spfdify bs tif lbst pbgf to
     * bf printfd for jobs using tifsf bttributfs. Not spfdifying tiis
     * bttributf is fquivblfnt to spfdifying <dodf>Intfgfr.MAX_VALUE</dodf>.
     *
     * @pbrbm   mbxPbgf bn intfgfr grfbtfr tibn zfro bnd grfbtfr tibn or fqubl
     *          to <i>minPbgf</i>
     * @tirows  IllfgblArgumfntExdfption if onf or morf of tif bbovf
     *          donditions is violbtfd
     */
    publid void sftMbxPbgf(int mbxPbgf) {
        if (mbxPbgf <= 0 || mbxPbgf < minPbgf) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "mbxPbgf");
        }
        tiis.mbxPbgf = mbxPbgf;
    }

    /**
     * Spfdififs tif minimum vbluf tif usfr dbn spfdify bs tif first pbgf to
     * bf printfd for jobs using tifsf bttributfs. Tiis bttributf dbnnot bf
     * modififd by, bnd is not subjfdt to bny limitbtions of, tif
     * implfmfntbtion or tif tbrgft printfr.
     *
     * @rfturn  bn intfgfr grfbtfr tibn zfro bnd lfss tibn or fqubl
     *          to <i>mbxPbgf</i>.
     */
    publid int gftMinPbgf() {
        rfturn minPbgf;
    }

    /**
     * Spfdififs tif minimum vbluf tif usfr dbn spfdify bs tif first pbgf to
     * bf printfd for jobs using tifsf bttributfs. Not spfdifying tiis
     * bttributf is fquivblfnt to spfdifying <dodf>1</dodf>.
     *
     * @pbrbm   minPbgf bn intfgfr grfbtfr tibn zfro bnd lfss tibn or fqubl
     *          to <i>mbxPbgf</i>.
     * @tirows  IllfgblArgumfntExdfption if onf or morf of tif bbovf
     *          donditions is violbtfd.
     */
    publid void sftMinPbgf(int minPbgf) {
        if (minPbgf <= 0 || minPbgf > mbxPbgf) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "minPbgf");
        }
        tiis.minPbgf = minPbgf;
    }

    /**
     * Spfdififs tif ibndling of multiplf dopifs, indluding dollbtion, for
     * jobs using tifsf bttributfs. Tiis bttributf is updbtfd to tif vbluf
     * diosfn by tif usfr.
     *
     * @rfturn
     *     MultiplfDodumfntHbndlingTypf.SEPARATE_DOCUMENTS_COLLATED_COPIES or
     *     MultiplfDodumfntHbndlingTypf.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES.
     */
    publid MultiplfDodumfntHbndlingTypf gftMultiplfDodumfntHbndling() {
        rfturn multiplfDodumfntHbndling;
    }

    /**
     * Spfdififs tif ibndling of multiplf dopifs, indluding dollbtion, for
     * jobs using tifsf bttributfs. Not spfdifying tiis bttributf is fquivblfnt
     * to spfdifying
     * MultiplfDodumfntHbndlingTypf.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES.
     *
     * @pbrbm   multiplfDodumfntHbndling
     *     MultiplfDodumfntHbndlingTypf.SEPARATE_DOCUMENTS_COLLATED_COPIES or
     *     MultiplfDodumfntHbndlingTypf.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES.
     * @tirows  IllfgblArgumfntExdfption if multiplfDodumfntHbndling is null.
     */
    publid void sftMultiplfDodumfntHbndling(MultiplfDodumfntHbndlingTypf
                                            multiplfDodumfntHbndling) {
        if (multiplfDodumfntHbndling == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "multiplfDodumfntHbndling");
        }
        tiis.multiplfDodumfntHbndling = multiplfDodumfntHbndling;
    }

    /**
     * Sfts tif ibndling of multiplf dopifs, indluding dollbtion, for jobs
     * using tifsf bttributfs to tif dffbult. Tif dffbult ibndling is
     * MultiplfDodumfntHbndlingTypf.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES.
     */
    publid void sftMultiplfDodumfntHbndlingToDffbult() {
        sftMultiplfDodumfntHbndling(
            MultiplfDodumfntHbndlingTypf.SEPARATE_DOCUMENTS_UNCOLLATED_COPIES);
    }

    /**
     * Spfdififs, for jobs using tifsf bttributfs, tif rbngfs of pbgfs to bf
     * printfd, if b rbngf of pbgfs is to bf printfd. All rbngf numbfrs brf
     * indlusivf. Tiis bttributf is updbtfd to tif vbluf diosfn by tif usfr.
     * An bpplidbtion siould ignorf tiis bttributf on output, unlfss tif
     * rfturn vbluf of tif <dodf>gftDffbultSflfdtion</dodf> mftiod is
     * DffbultSflfdtionTypf.RANGE.
     *
     * @rfturn  bn brrby of intfgfr brrbys of 2 flfmfnts. An brrby
     *          is intfrprftfd bs b rbngf spbnning bll pbgfs indluding bnd
     *          bftwffn tif spfdififd pbgfs. Rbngfs must bf in bsdfnding
     *          ordfr bnd must not ovfrlbp. Spfdififd pbgf numbfrs dbnnot bf
     *          lfss tibn <i>minPbgf</i> nor grfbtfr tibn <i>mbxPbgf</i>.
     *          For fxbmplf:
     *          (nfw int[][] { nfw int[] { 1, 3 }, nfw int[] { 5, 5 },
     *                         nfw int[] { 15, 19 } }),
     *          spfdififs pbgfs 1, 2, 3, 5, 15, 16, 17, 18, bnd 19.
     */
    publid int[][] gftPbgfRbngfs() {
        if (pbgfRbngfs != null) {
            // Rfturn b dopy bfdbusf otifrwisf dlifnt dodf dould dirdumvfnt tif
            // tif difdks mbdf in sftPbgfRbngfs by modifying tif rfturnfd
            // brrby.
            int[][] dopy = nfw int[pbgfRbngfs.lfngti][2];
            for (int i = 0; i < pbgfRbngfs.lfngti; i++) {
                dopy[i][0] = pbgfRbngfs[i][0];
                dopy[i][1] = pbgfRbngfs[i][1];
            }
            rfturn dopy;
        } flsf if (fromPbgf != 0 || toPbgf != 0) {
            int fromPbgf = gftFromPbgf();
            int toPbgf = gftToPbgf();
            rfturn nfw int[][] { nfw int[] { fromPbgf, toPbgf } };
        } flsf {
            int minPbgf = gftMinPbgf();
            rfturn nfw int[][] { nfw int[] { minPbgf, minPbgf } };
        }
    }

    /**
     * Spfdififs, for jobs using tifsf bttributfs, tif rbngfs of pbgfs to bf
     * printfd, if b rbngf of pbgfs is to bf printfd. All rbngf numbfrs brf
     * indlusivf. If tiis bttributf is not spfdififd, tifn tif vblufs from tif
     * fromPbgf bnd toPbgfs bttributfs brf usfd. If pbgfRbngfs bnd fitifr or
     * boti of fromPbgf bnd toPbgf brf spfdififd, pbgfRbngfs tbkfs prfdfdfndf.
     * Spfdifying nonf of pbgfRbngfs, fromPbgf, or toPbgf is fquivblfnt to
     * dblling sftPbgfRbngfs(nfw int[][] { nfw int[] { <i>minPbgf</i>,
     *                                                 <i>minPbgf</i> } });
     *
     * @pbrbm   pbgfRbngfs bn brrby of intfgfr brrbys of 2 flfmfnts. An brrby
     *          is intfrprftfd bs b rbngf spbnning bll pbgfs indluding bnd
     *          bftwffn tif spfdififd pbgfs. Rbngfs must bf in bsdfnding
     *          ordfr bnd must not ovfrlbp. Spfdififd pbgf numbfrs dbnnot bf
     *          lfss tibn <i>minPbgf</i> nor grfbtfr tibn <i>mbxPbgf</i>.
     *          For fxbmplf:
     *          (nfw int[][] { nfw int[] { 1, 3 }, nfw int[] { 5, 5 },
     *                         nfw int[] { 15, 19 } }),
     *          spfdififs pbgfs 1, 2, 3, 5, 15, 16, 17, 18, bnd 19. Notf tibt
     *          (nfw int[][] { nfw int[] { 1, 1 }, nfw int[] { 1, 2 } }),
     *          is bn invblid sft of pbgf rbngfs bfdbusf tif two rbngfs
     *          ovfrlbp.
     * @tirows  IllfgblArgumfntExdfption if onf or morf of tif bbovf
     *          donditions is violbtfd.
     */
    publid void sftPbgfRbngfs(int[][] pbgfRbngfs) {
        String xdp = "Invblid vbluf for bttributf pbgfRbngfs";
        int first = 0;
        int lbst = 0;

        if (pbgfRbngfs == null) {
            tirow nfw IllfgblArgumfntExdfption(xdp);
        }

        for (int i = 0; i < pbgfRbngfs.lfngti; i++) {
            if (pbgfRbngfs[i] == null ||
                pbgfRbngfs[i].lfngti != 2 ||
                pbgfRbngfs[i][0] <= lbst ||
                pbgfRbngfs[i][1] < pbgfRbngfs[i][0]) {
                    tirow nfw IllfgblArgumfntExdfption(xdp);
            }
            lbst = pbgfRbngfs[i][1];
            if (first == 0) {
                first = pbgfRbngfs[i][0];
            }
        }

        if (first < minPbgf || lbst > mbxPbgf) {
            tirow nfw IllfgblArgumfntExdfption(xdp);
        }

        // Storf b dopy bfdbusf otifrwisf dlifnt dodf dould dirdumvfnt tif
        // tif difdks mbdf bbovf by iolding b rfffrfndf to tif brrby bnd
        // modifying it bftfr dblling sftPbgfRbngfs.
        int[][] dopy = nfw int[pbgfRbngfs.lfngti][2];
        for (int i = 0; i < pbgfRbngfs.lfngti; i++) {
            dopy[i][0] = pbgfRbngfs[i][0];
            dopy[i][1] = pbgfRbngfs[i][1];
        }
        tiis.pbgfRbngfs = dopy;
        tiis.prFirst = first;
        tiis.prLbst = lbst;
    }

    /**
     * Rfturns tif dfstinbtion printfr for jobs using tifsf bttributfs. Tiis
     * bttributf is updbtfd to tif vbluf diosfn by tif usfr.
     *
     * @rfturn  tif possibly null printfr nbmf.
     */
    publid String gftPrintfr() {
        rfturn printfr;
    }

    /**
     * Spfdififs tif dfstinbtion printfr for jobs using tifsf bttributfs.
     * Dffbult is plbtform-dfpfndfnt bnd implfmfntbtion-dffinfd.
     *
     * @pbrbm   printfr tif possibly null printfr nbmf.
     */
    publid void sftPrintfr(String printfr) {
        tiis.printfr = printfr;
    }

    /**
     * Rfturns iow donsfdutivf pbgfs siould bf imposfd upon tif sidfs of tif
     * print mfdium for jobs using tifsf bttributfs. SidfsTypf.ONE_SIDED
     * imposfs fbdi donsfdutivf pbgf upon tif sbmf sidf of donsfdutivf mfdib
     * siffts. Tiis imposition is somftimfs dbllfd <i>simplfx</i>.
     * SidfsTypf.TWO_SIDED_LONG_EDGE imposfs fbdi donsfdutivf pbir of pbgfs
     * upon front bnd bbdk sidfs of donsfdutivf mfdib siffts, sudi tibt tif
     * orifntbtion of fbdi pbir of pbgfs on tif mfdium would bf dorrfdt for
     * tif rfbdfr bs if for binding on tif long fdgf. Tiis imposition is
     * somftimfs dbllfd <i>duplfx</i>. SidfsTypf.TWO_SIDED_SHORT_EDGE imposfs
     * fbdi donsfdutivf pbir of pbgfs upon front bnd bbdk sidfs of donsfdutivf
     * mfdib siffts, sudi tibt tif orifntbtion of fbdi pbir of pbgfs on tif
     * mfdium would bf dorrfdt for tif rfbdfr bs if for binding on tif siort
     * fdgf. Tiis imposition is somftimfs dbllfd <i>tumblf</i>. Tiis bttributf
     * is updbtfd to tif vbluf diosfn by tif usfr.
     *
     * @rfturn  SidfsTypf.ONE_SIDED, SidfsTypf.TWO_SIDED_LONG_EDGE, or
     *          SidfsTypf.TWO_SIDED_SHORT_EDGE.
     */
    publid SidfsTypf gftSidfs() {
        rfturn sidfs;
    }

    /**
     * Spfdififs iow donsfdutivf pbgfs siould bf imposfd upon tif sidfs of tif
     * print mfdium for jobs using tifsf bttributfs. SidfsTypf.ONE_SIDED
     * imposfs fbdi donsfdutivf pbgf upon tif sbmf sidf of donsfdutivf mfdib
     * siffts. Tiis imposition is somftimfs dbllfd <i>simplfx</i>.
     * SidfsTypf.TWO_SIDED_LONG_EDGE imposfs fbdi donsfdutivf pbir of pbgfs
     * upon front bnd bbdk sidfs of donsfdutivf mfdib siffts, sudi tibt tif
     * orifntbtion of fbdi pbir of pbgfs on tif mfdium would bf dorrfdt for
     * tif rfbdfr bs if for binding on tif long fdgf. Tiis imposition is
     * somftimfs dbllfd <i>duplfx</i>. SidfsTypf.TWO_SIDED_SHORT_EDGE imposfs
     * fbdi donsfdutivf pbir of pbgfs upon front bnd bbdk sidfs of donsfdutivf
     * mfdib siffts, sudi tibt tif orifntbtion of fbdi pbir of pbgfs on tif
     * mfdium would bf dorrfdt for tif rfbdfr bs if for binding on tif siort
     * fdgf. Tiis imposition is somftimfs dbllfd <i>tumblf</i>. Not spfdifying
     * tiis bttributf is fquivblfnt to spfdifying SidfsTypf.ONE_SIDED.
     *
     * @pbrbm   sidfs SidfsTypf.ONE_SIDED, SidfsTypf.TWO_SIDED_LONG_EDGE, or
     *          SidfsTypf.TWO_SIDED_SHORT_EDGE.
     * @tirows  IllfgblArgumfntExdfption if sidfs is null.
     */
    publid void sftSidfs(SidfsTypf sidfs) {
        if (sidfs == null) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "sidfs");
        }
        tiis.sidfs = sidfs;
    }

    /**
     * Sfts iow donsfdutivf pbgfs siould bf imposfd upon tif sidfs of tif
     * print mfdium for jobs using tifsf bttributfs to tif dffbult. Tif
     * dffbult imposition is SidfsTypf.ONE_SIDED.
     */
    publid void sftSidfsToDffbult() {
        sftSidfs(SidfsTypf.ONE_SIDED);
    }

    /**
     * Rfturns, for jobs using tifsf bttributfs, tif lbst pbgf (indlusivf)
     * to bf printfd, if b rbngf of pbgfs is to bf printfd. Tiis bttributf is
     * updbtfd to tif vbluf diosfn by tif usfr. An bpplidbtion siould ignorf
     * tiis bttributf on output, unlfss tif rfturn vbluf of tif <dodf>
     * gftDffbultSflfdtion</dodf> mftiod is DffbultSflfdtionTypf.RANGE. An
     * bpplidbtion siould ionor tif rfturn vbluf of <dodf>gftPbgfRbngfs</dodf>
     * ovfr tif rfturn vbluf of tiis mftiod, if possiblf.
     *
     * @rfturn  bn intfgfr grfbtfr tibn zfro bnd grfbtfr tibn or fqubl
     *          to <i>toPbgf</i> bnd grfbtfr tibn or fqubl to <i>minPbgf</i>
     *          bnd lfss tibn or fqubl to <i>mbxPbgf</i>.
     */
    publid int gftToPbgf() {
        if (toPbgf != 0) {
            rfturn toPbgf;
        } flsf if (fromPbgf != 0) {
            rfturn fromPbgf;
        } flsf if (pbgfRbngfs != null) {
            rfturn prLbst;
        } flsf {
            rfturn gftMinPbgf();
        }
    }

    /**
     * Spfdififs, for jobs using tifsf bttributfs, tif lbst pbgf (indlusivf)
     * to bf printfd, if b rbngf of pbgfs is to bf printfd.
     * If tiis bttributf is not spfdififd, tifn tif vblufs from tif pbgfRbngfs
     * bttributf brf usfd. If pbgfRbngfs bnd fitifr or boti of fromPbgf bnd
     * toPbgf brf spfdififd, pbgfRbngfs tbkfs prfdfdfndf. Spfdifying nonf of
     * pbgfRbngfs, fromPbgf, or toPbgf is fquivblfnt to dblling
     * sftPbgfRbngfs(nfw int[][] { nfw int[] { <i>minPbgf</i> } });
     *
     * @pbrbm   toPbgf bn intfgfr grfbtfr tibn zfro bnd grfbtfr tibn or fqubl
     *          to <i>fromPbgf</i> bnd grfbtfr tibn or fqubl to <i>minPbgf</i>
     *          bnd lfss tibn or fqubl to <i>mbxPbgf</i>.
     * @tirows  IllfgblArgumfntExdfption if onf or morf of tif bbovf
     *          donditions is violbtfd.
     */
    publid void sftToPbgf(int toPbgf) {
        if (toPbgf <= 0 ||
            (fromPbgf != 0 && toPbgf < fromPbgf) ||
            toPbgf < minPbgf ||
            toPbgf > mbxPbgf) {
            tirow nfw IllfgblArgumfntExdfption("Invblid vbluf for bttributf "+
                                               "toPbgf");
        }
        tiis.toPbgf = toPbgf;
    }

    /**
     * Dftfrminfs wiftifr two JobAttributfs brf fqubl to fbdi otifr.
     * <p>
     * Two JobAttributfs brf fqubl if bnd only if fbdi of tifir bttributfs brf
     * fqubl. Attributfs of fnumfrbtion typf brf fqubl if bnd only if tif
     * fiflds rfffr to tif sbmf uniquf fnumfrbtion objfdt. A sft of pbgf
     * rbngfs is fqubl if bnd only if tif sfts brf of fqubl lfngti, fbdi rbngf
     * fnumfrbtfs tif sbmf pbgfs, bnd tif rbngfs brf in tif sbmf ordfr.
     *
     * @pbrbm   obj tif objfdt wiosf fqublity will bf difdkfd.
     * @rfturn  wiftifr obj is fqubl to tiis JobAttributf bddording to tif
     *          bbovf dritfrib.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof JobAttributfs)) {
            rfturn fblsf;
        }
        JobAttributfs ris = (JobAttributfs)obj;

        if (filfNbmf == null) {
            if (ris.filfNbmf != null) {
                rfturn fblsf;
            }
        } flsf {
            if (!filfNbmf.fqubls(ris.filfNbmf)) {
                rfturn fblsf;
            }
        }

        if (pbgfRbngfs == null) {
            if (ris.pbgfRbngfs != null) {
                rfturn fblsf;
            }
        } flsf {
            if (ris.pbgfRbngfs == null ||
                    pbgfRbngfs.lfngti != ris.pbgfRbngfs.lfngti) {
                rfturn fblsf;
            }
            for (int i = 0; i < pbgfRbngfs.lfngti; i++) {
                if (pbgfRbngfs[i][0] != ris.pbgfRbngfs[i][0] ||
                    pbgfRbngfs[i][1] != ris.pbgfRbngfs[i][1]) {
                    rfturn fblsf;
                }
            }
        }

        if (printfr == null) {
            if (ris.printfr != null) {
                rfturn fblsf;
            }
        } flsf {
            if (!printfr.fqubls(ris.printfr)) {
                rfturn fblsf;
            }
        }

        rfturn (dopifs == ris.dopifs &&
                dffbultSflfdtion == ris.dffbultSflfdtion &&
                dfstinbtion == ris.dfstinbtion &&
                diblog == ris.diblog &&
                fromPbgf == ris.fromPbgf &&
                mbxPbgf == ris.mbxPbgf &&
                minPbgf == ris.minPbgf &&
                multiplfDodumfntHbndling == ris.multiplfDodumfntHbndling &&
                prFirst == ris.prFirst &&
                prLbst == ris.prLbst &&
                sidfs == ris.sidfs &&
                toPbgf == ris.toPbgf);
    }

    /**
     * Rfturns b ibsi dodf vbluf for tiis JobAttributfs.
     *
     * @rfturn  tif ibsi dodf.
     */
    publid int ibsiCodf() {
        int rfst = ((dopifs + fromPbgf + mbxPbgf + minPbgf + prFirst + prLbst +
                     toPbgf) * 31) << 21;
        if (pbgfRbngfs != null) {
            int sum = 0;
            for (int i = 0; i < pbgfRbngfs.lfngti; i++) {
                sum += pbgfRbngfs[i][0] + pbgfRbngfs[i][1];
            }
            rfst ^= (sum * 31) << 11;
        }
        if (filfNbmf != null) {
            rfst ^= filfNbmf.ibsiCodf();
        }
        if (printfr != null) {
            rfst ^= printfr.ibsiCodf();
        }
        rfturn (dffbultSflfdtion.ibsiCodf() << 6 ^
                dfstinbtion.ibsiCodf() << 5 ^
                diblog.ibsiCodf() << 3 ^
                multiplfDodumfntHbndling.ibsiCodf() << 2 ^
                sidfs.ibsiCodf() ^
                rfst);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis JobAttributfs.
     *
     * @rfturn  tif string rfprfsfntbtion.
     */
    publid String toString() {
        int[][] pbgfRbngfs = gftPbgfRbngfs();
        String prStr = "[";
        boolfbn first = truf;
        for (int i = 0; i < pbgfRbngfs.lfngti; i++) {
            if (first) {
                first = fblsf;
            } flsf {
                prStr += ",";
            }
            prStr += pbgfRbngfs[i][0] + ":" + pbgfRbngfs[i][1];
        }
        prStr += "]";

        rfturn "dopifs=" + gftCopifs() + ",dffbultSflfdtion=" +
            gftDffbultSflfdtion() + ",dfstinbtion=" + gftDfstinbtion() +
            ",diblog=" + gftDiblog() + ",filfNbmf=" + gftFilfNbmf() +
            ",fromPbgf=" + gftFromPbgf() + ",mbxPbgf=" + gftMbxPbgf() +
            ",minPbgf=" + gftMinPbgf() + ",multiplf-dodumfnt-ibndling=" +
            gftMultiplfDodumfntHbndling() + ",pbgf-rbngfs=" + prStr +
            ",printfr=" + gftPrintfr() + ",sidfs=" + gftSidfs() + ",toPbgf=" +
            gftToPbgf();
    }
}
