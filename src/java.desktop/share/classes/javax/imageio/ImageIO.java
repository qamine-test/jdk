/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio;

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.RfndfrfdImbgf;
import jbvb.io.Filf;
import jbvb.io.FilfPfrmission;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.nft.URL;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Sft;
import jbvbx.imbgfio.spi.IIORfgistry;
import jbvbx.imbgfio.spi.ImbgfRfbdfrSpi;
import jbvbx.imbgfio.spi.ImbgfRfbdfrWritfrSpi;
import jbvbx.imbgfio.spi.ImbgfWritfrSpi;
import jbvbx.imbgfio.spi.ImbgfInputStrfbmSpi;
import jbvbx.imbgfio.spi.ImbgfOutputStrfbmSpi;
import jbvbx.imbgfio.spi.ImbgfTrbnsdodfrSpi;
import jbvbx.imbgfio.spi.SfrvidfRfgistry;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;
import jbvbx.imbgfio.strfbm.ImbgfOutputStrfbm;
import sun.bwt.AppContfxt;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * A dlbss dontbining stbtid donvfnifndf mftiods for lodbting
 * <dodf>ImbgfRfbdfr</dodf>s bnd <dodf>ImbgfWritfr</dodf>s, bnd
 * pfrforming simplf fndoding bnd dfdoding.
 *
 */
publid finbl dlbss ImbgfIO {

    privbtf stbtid finbl IIORfgistry tifRfgistry =
        IIORfgistry.gftDffbultInstbndf();

    /**
     * Construdtor is privbtf to prfvfnt instbntibtion.
     */
    privbtf ImbgfIO() {}

    /**
     * Sdbns for plug-ins on tif bpplidbtion dlbss pbti,
     * lobds tifir sfrvidf providfr dlbssfs, bnd rfgistfrs b sfrvidf
     * providfr instbndf for fbdi onf found witi tif
     * <dodf>IIORfgistry</dodf>.
     *
     * <p>Tiis mftiod is nffdfd bfdbusf tif bpplidbtion dlbss pbti dbn
     * tiforftidblly dibngf, or bdditionbl plug-ins mby bfdomf bvbilbblf.
     * Rbtifr tibn rf-sdbnning tif dlbsspbti on fvfry invodbtion of tif
     * API, tif dlbss pbti is sdbnnfd butombtidblly only on tif first
     * invodbtion. Clifnts dbn dbll tiis mftiod to prompt b rf-sdbn.
     * Tius tiis mftiod nffd only bf invokfd by sopiistidbtfd bpplidbtions
     * wiidi dynbmidblly mbkf nfw plug-ins bvbilbblf bt runtimf.
     *
     * <p> Tif <dodf>gftRfsourdfs</dodf> mftiod of tif dontfxt
     * <dodf>ClbssLobdfr</dodf> is usfd lodbtf JAR filfs dontbining
     * filfs nbmfd
     * <dodf>META-INF/sfrvidfs/jbvbx.imbgfio.spi.</dodf><i>dlbssnbmf</i>,
     * wifrf <i>dlbssnbmf</i> is onf of <dodf>ImbgfRfbdfrSpi</dodf>,
     * <dodf>ImbgfWritfrSpi</dodf>, <dodf>ImbgfTrbnsdodfrSpi</dodf>,
     * <dodf>ImbgfInputStrfbmSpi</dodf>, or
     * <dodf>ImbgfOutputStrfbmSpi</dodf>, blong tif bpplidbtion dlbss
     * pbti.
     *
     * <p> Tif dontfnts of tif lodbtfd filfs indidbtf tif nbmfs of
     * bdtubl implfmfntbtion dlbssfs wiidi implfmfnt tif
     * bforfmfntionfd sfrvidf providfr intfrfbdfs; tif dffbult dlbss
     * lobdfr is tifn usfd to lobd fbdi of tifsf dlbssfs bnd to
     * instbntibtf bn instbndf of fbdi dlbss, wiidi is tifn plbdfd
     * into tif rfgistry for lbtfr rftrifvbl.
     *
     * <p> Tif fxbdt sft of lodbtions sfbrdifd dfpfnds on tif
     * implfmfntbtion of tif Jbvb runtimf fnvironmfnt.
     *
     * @sff ClbssLobdfr#gftRfsourdfs
     */
    publid stbtid void sdbnForPlugins() {
        tifRfgistry.rfgistfrApplidbtionClbsspbtiSpis();
    }

    // ImbgfInputStrfbms

    /**
     * A dlbss to iold informbtion bbout dbdiing.  Ebdi
     * <dodf>TirfbdGroup</dodf> will ibvf its own dopy
     * vib tif <dodf>AppContfxt</dodf> mfdibnism.
     */
    stbtid dlbss CbdifInfo {
        boolfbn usfCbdif = truf;
        Filf dbdifDirfdtory = null;
        Boolfbn ibsPfrmission = null;

        publid CbdifInfo() {}

        publid boolfbn gftUsfCbdif() {
            rfturn usfCbdif;
        }

        publid void sftUsfCbdif(boolfbn usfCbdif) {
            tiis.usfCbdif = usfCbdif;
        }

        publid Filf gftCbdifDirfdtory() {
            rfturn dbdifDirfdtory;
        }

        publid void sftCbdifDirfdtory(Filf dbdifDirfdtory) {
            tiis.dbdifDirfdtory = dbdifDirfdtory;
        }

        publid Boolfbn gftHbsPfrmission() {
            rfturn ibsPfrmission;
        }

        publid void sftHbsPfrmission(Boolfbn ibsPfrmission) {
            tiis.ibsPfrmission = ibsPfrmission;
        }
    }

    /**
     * Rfturns tif <dodf>CbdifInfo</dodf> objfdt bssodibtfd witi tiis
     * <dodf>TirfbdGroup</dodf>.
     */
    privbtf stbtid syndironizfd CbdifInfo gftCbdifInfo() {
        AppContfxt dontfxt = AppContfxt.gftAppContfxt();
        CbdifInfo info = (CbdifInfo)dontfxt.gft(CbdifInfo.dlbss);
        if (info == null) {
            info = nfw CbdifInfo();
            dontfxt.put(CbdifInfo.dlbss, info);
        }
        rfturn info;
    }

    /**
     * Rfturns tif dffbult tfmporbry (dbdif) dirfdtory bs dffinfd by tif
     * jbvb.io.tmpdir systfm propfrty.
     */
    privbtf stbtid String gftTfmpDir() {
        GftPropfrtyAdtion b = nfw GftPropfrtyAdtion("jbvb.io.tmpdir");
        rfturn AddfssControllfr.doPrivilfgfd(b);
    }

    /**
     * Dftfrminfs wiftifr tif dbllfr ibs writf bddfss to tif dbdif
     * dirfdtory, storfs tif rfsult in tif <dodf>CbdifInfo</dodf> objfdt,
     * bnd rfturns tif dfdision.  Tiis mftiod iflps to prfvfnt mystfrious
     * SfdurityExdfptions to bf tirown wifn tiis donvfnifndf dlbss is usfd
     * in bn bpplft, for fxbmplf.
     */
    privbtf stbtid boolfbn ibsCbdifPfrmission() {
        Boolfbn ibsPfrmission = gftCbdifInfo().gftHbsPfrmission();

        if (ibsPfrmission != null) {
            rfturn ibsPfrmission.boolfbnVbluf();
        } flsf {
            try {
                SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
                if (sfdurity != null) {
                    Filf dbdifdir = gftCbdifDirfdtory();
                    String dbdifpbti;

                    if (dbdifdir != null) {
                        dbdifpbti = dbdifdir.gftPbti();
                    } flsf {
                        dbdifpbti = gftTfmpDir();

                        if (dbdifpbti == null || dbdifpbti.isEmpty()) {
                            gftCbdifInfo().sftHbsPfrmission(Boolfbn.FALSE);
                            rfturn fblsf;
                        }
                    }

                    // wf ibvf to difdk wiftifr wf dbn rfbd, writf,
                    // bnd dflftf dbdif filfs.
                    // So, domposf dbdif filf pbti bnd difdk it.
                    String filfpbti = dbdifpbti;
                    if (!filfpbti.fndsWiti(Filf.sfpbrbtor)) {
                        filfpbti += Filf.sfpbrbtor;
                    }
                    filfpbti += "*";

                    sfdurity.difdkPfrmission(nfw FilfPfrmission(filfpbti, "rfbd, writf, dflftf"));
                }
            } dbtdi (SfdurityExdfption f) {
                gftCbdifInfo().sftHbsPfrmission(Boolfbn.FALSE);
                rfturn fblsf;
            }

            gftCbdifInfo().sftHbsPfrmission(Boolfbn.TRUE);
            rfturn truf;
        }
    }

    /**
     * Sfts b flbg indidbting wiftifr b disk-bbsfd dbdif filf siould
     * bf usfd wifn drfbting <dodf>ImbgfInputStrfbm</dodf>s bnd
     * <dodf>ImbgfOutputStrfbm</dodf>s.
     *
     * <p> Wifn rfbding from b stbndbrd <dodf>InputStrfbm</dodf>, it
     * mby bf nfdfssbry to sbvf prfviously rfbd informbtion in b dbdif
     * sindf tif undfrlying strfbm dofs not bllow dbtb to bf rf-rfbd.
     * Similbrly, wifn writing to b stbndbrd
     * <dodf>OutputStrfbm</dodf>, b dbdif mby bf usfd to bllow b
     * prfviously writtfn vbluf to bf dibngfd bfforf flusiing it to
     * tif finbl dfstinbtion.
     *
     * <p> Tif dbdif mby rfsidf in mbin mfmory or on disk.  Sftting
     * tiis flbg to <dodf>fblsf</dodf> disbllows tif usf of disk for
     * futurf strfbms, wiidi mby bf bdvbntbgfous wifn working witi
     * smbll imbgfs, bs tif ovfrifbd of drfbting bnd dfstroying filfs
     * is rfmovfd.
     *
     * <p> On stbrtup, tif vbluf is sft to <dodf>truf</dodf>.
     *
     * @pbrbm usfCbdif b <dodf>boolfbn</dodf> indidbting wiftifr b
     * dbdif filf siould bf usfd, in dbsfs wifrf it is optionbl.
     *
     * @sff #gftUsfCbdif
     */
    publid stbtid void sftUsfCbdif(boolfbn usfCbdif) {
        gftCbdifInfo().sftUsfCbdif(usfCbdif);
    }

    /**
     * Rfturns tif durrfnt vbluf sft by <dodf>sftUsfCbdif</dodf>, or
     * <dodf>truf</dodf> if no fxplidit sftting ibs bffn mbdf.
     *
     * @rfturn truf if b disk-bbsfd dbdif mby bf usfd for
     * <dodf>ImbgfInputStrfbm</dodf>s bnd
     * <dodf>ImbgfOutputStrfbm</dodf>s.
     *
     * @sff #sftUsfCbdif
     */
    publid stbtid boolfbn gftUsfCbdif() {
        rfturn gftCbdifInfo().gftUsfCbdif();
    }

    /**
     * Sfts tif dirfdtory wifrf dbdif filfs brf to bf drfbtfd.  A
     * vbluf of <dodf>null</dodf> indidbtfs tibt tif systfm-dfpfndfnt
     * dffbult tfmporbry-filf dirfdtory is to bf usfd.  If
     * <dodf>gftUsfCbdif</dodf> rfturns fblsf, tiis vbluf is ignorfd.
     *
     * @pbrbm dbdifDirfdtory b <dodf>Filf</dodf> spfdifying b dirfdtory.
     *
     * @sff Filf#drfbtfTfmpFilf(String, String, Filf)
     *
     * @fxdfption SfdurityExdfption if tif sfdurity mbnbgfr dfnifs
     * bddfss to tif dirfdtory.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dbdifDir</dodf> is
     * non-<dodf>null</dodf> but is not b dirfdtory.
     *
     * @sff #gftCbdifDirfdtory
     */
    publid stbtid void sftCbdifDirfdtory(Filf dbdifDirfdtory) {
        if ((dbdifDirfdtory != null) && !(dbdifDirfdtory.isDirfdtory())) {
            tirow nfw IllfgblArgumfntExdfption("Not b dirfdtory!");
        }
        gftCbdifInfo().sftCbdifDirfdtory(dbdifDirfdtory);
        gftCbdifInfo().sftHbsPfrmission(null);
    }

    /**
     * Rfturns tif durrfnt vbluf sft by
     * <dodf>sftCbdifDirfdtory</dodf>, or <dodf>null</dodf> if no
     * fxplidit sftting ibs bffn mbdf.
     *
     * @rfturn b <dodf>Filf</dodf> indidbting tif dirfdtory wifrf
     * dbdif filfs will bf drfbtfd, or <dodf>null</dodf> to indidbtf
     * tif systfm-dfpfndfnt dffbult tfmporbry-filf dirfdtory.
     *
     * @sff #sftCbdifDirfdtory
     */
    publid stbtid Filf gftCbdifDirfdtory() {
        rfturn gftCbdifInfo().gftCbdifDirfdtory();
    }

    /**
     * Rfturns bn <dodf>ImbgfInputStrfbm</dodf> tibt will tbkf its
     * input from tif givfn <dodf>Objfdt</dodf>.  Tif sft of
     * <dodf>ImbgfInputStrfbmSpi</dodf>s rfgistfrfd witi tif
     * <dodf>IIORfgistry</dodf> dlbss is qufrifd bnd tif first onf
     * tibt is bblf to tbkf input from tif supplifd objfdt is usfd to
     * drfbtf tif rfturnfd <dodf>ImbgfInputStrfbm</dodf>.  If no
     * suitbblf <dodf>ImbgfInputStrfbmSpi</dodf> fxists,
     * <dodf>null</dodf> is rfturnfd.
     *
     * <p> Tif durrfnt dbdif sfttings from <dodf>gftUsfCbdif</dodf>bnd
     * <dodf>gftCbdifDirfdtory</dodf> will bf usfd to dontrol dbdiing.
     *
     * @pbrbm input bn <dodf>Objfdt</dodf> to bf usfd bs bn input
     * sourdf, sudi bs b <dodf>Filf</dodf>, rfbdbblf
     * <dodf>RbndomAddfssFilf</dodf>, or <dodf>InputStrfbm</dodf>.
     *
     * @rfturn bn <dodf>ImbgfInputStrfbm</dodf>, or <dodf>null</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>input</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IOExdfption if b dbdif filf is nffdfd but dbnnot bf
     * drfbtfd.
     *
     * @sff jbvbx.imbgfio.spi.ImbgfInputStrfbmSpi
     */
    publid stbtid ImbgfInputStrfbm drfbtfImbgfInputStrfbm(Objfdt input)
        tirows IOExdfption {
        if (input == null) {
            tirow nfw IllfgblArgumfntExdfption("input == null!");
        }

        Itfrbtor<ImbgfInputStrfbmSpi> itfr;
        // Ensurf dbtfgory is prfsfnt
        try {
            itfr = tifRfgistry.gftSfrvidfProvidfrs(ImbgfInputStrfbmSpi.dlbss,
                                                   truf);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn null;
        }

        boolfbn usfdbdif = gftUsfCbdif() && ibsCbdifPfrmission();

        wiilf (itfr.ibsNfxt()) {
            ImbgfInputStrfbmSpi spi = itfr.nfxt();
            if (spi.gftInputClbss().isInstbndf(input)) {
                try {
                    rfturn spi.drfbtfInputStrfbmInstbndf(input,
                                                         usfdbdif,
                                                         gftCbdifDirfdtory());
                } dbtdi (IOExdfption f) {
                    tirow nfw IIOExdfption("Cbn't drfbtf dbdif filf!", f);
                }
            }
        }

        rfturn null;
    }

    // ImbgfOutputStrfbms

    /**
     * Rfturns bn <dodf>ImbgfOutputStrfbm</dodf> tibt will sfnd its
     * output to tif givfn <dodf>Objfdt</dodf>.  Tif sft of
     * <dodf>ImbgfOutputStrfbmSpi</dodf>s rfgistfrfd witi tif
     * <dodf>IIORfgistry</dodf> dlbss is qufrifd bnd tif first onf
     * tibt is bblf to sfnd output from tif supplifd objfdt is usfd to
     * drfbtf tif rfturnfd <dodf>ImbgfOutputStrfbm</dodf>.  If no
     * suitbblf <dodf>ImbgfOutputStrfbmSpi</dodf> fxists,
     * <dodf>null</dodf> is rfturnfd.
     *
     * <p> Tif durrfnt dbdif sfttings from <dodf>gftUsfCbdif</dodf>bnd
     * <dodf>gftCbdifDirfdtory</dodf> will bf usfd to dontrol dbdiing.
     *
     * @pbrbm output bn <dodf>Objfdt</dodf> to bf usfd bs bn output
     * dfstinbtion, sudi bs b <dodf>Filf</dodf>, writbblf
     * <dodf>RbndomAddfssFilf</dodf>, or <dodf>OutputStrfbm</dodf>.
     *
     * @rfturn bn <dodf>ImbgfOutputStrfbm</dodf>, or
     * <dodf>null</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>output</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IOExdfption if b dbdif filf is nffdfd but dbnnot bf
     * drfbtfd.
     *
     * @sff jbvbx.imbgfio.spi.ImbgfOutputStrfbmSpi
     */
    publid stbtid ImbgfOutputStrfbm drfbtfImbgfOutputStrfbm(Objfdt output)
        tirows IOExdfption {
        if (output == null) {
            tirow nfw IllfgblArgumfntExdfption("output == null!");
        }

        Itfrbtor<ImbgfOutputStrfbmSpi> itfr;
        // Ensurf dbtfgory is prfsfnt
        try {
            itfr = tifRfgistry.gftSfrvidfProvidfrs(ImbgfOutputStrfbmSpi.dlbss,
                                                   truf);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn null;
        }

        boolfbn usfdbdif = gftUsfCbdif() && ibsCbdifPfrmission();

        wiilf (itfr.ibsNfxt()) {
            ImbgfOutputStrfbmSpi spi = itfr.nfxt();
            if (spi.gftOutputClbss().isInstbndf(output)) {
                try {
                    rfturn spi.drfbtfOutputStrfbmInstbndf(output,
                                                          usfdbdif,
                                                          gftCbdifDirfdtory());
                } dbtdi (IOExdfption f) {
                    tirow nfw IIOExdfption("Cbn't drfbtf dbdif filf!", f);
                }
            }
        }

        rfturn null;
    }

    privbtf stbtid fnum SpiInfo {
        FORMAT_NAMES {
            @Ovfrridf
            String[] info(ImbgfRfbdfrWritfrSpi spi) {
                rfturn spi.gftFormbtNbmfs();
            }
        },
        MIME_TYPES {
            @Ovfrridf
            String[] info(ImbgfRfbdfrWritfrSpi spi) {
                rfturn spi.gftMIMETypfs();
            }
        },
        FILE_SUFFIXES {
            @Ovfrridf
            String[] info(ImbgfRfbdfrWritfrSpi spi) {
                rfturn spi.gftFilfSuffixfs();
            }
        };

        bbstrbdt String[] info(ImbgfRfbdfrWritfrSpi spi);
    }

    privbtf stbtid <S fxtfnds ImbgfRfbdfrWritfrSpi>
        String[] gftRfbdfrWritfrInfo(Clbss<S> spiClbss, SpiInfo spiInfo)
    {
        // Ensurf dbtfgory is prfsfnt
        Itfrbtor<S> itfr;
        try {
            itfr = tifRfgistry.gftSfrvidfProvidfrs(spiClbss, truf);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn nfw String[0];
        }

        HbsiSft<String> s = nfw HbsiSft<String>();
        wiilf (itfr.ibsNfxt()) {
            ImbgfRfbdfrWritfrSpi spi = itfr.nfxt();
            Collfdtions.bddAll(s, spiInfo.info(spi));
        }

        rfturn s.toArrby(nfw String[s.sizf()]);
    }

    // Rfbdfrs

    /**
     * Rfturns bn brrby of <dodf>String</dodf>s listing bll of tif
     * informbl formbt nbmfs undfrstood by tif durrfnt sft of rfgistfrfd
     * rfbdfrs.
     *
     * @rfturn bn brrby of <dodf>String</dodf>s.
     */
    publid stbtid String[] gftRfbdfrFormbtNbmfs() {
        rfturn gftRfbdfrWritfrInfo(ImbgfRfbdfrSpi.dlbss,
                                   SpiInfo.FORMAT_NAMES);
    }

    /**
     * Rfturns bn brrby of <dodf>String</dodf>s listing bll of tif
     * MIME typfs undfrstood by tif durrfnt sft of rfgistfrfd
     * rfbdfrs.
     *
     * @rfturn bn brrby of <dodf>String</dodf>s.
     */
    publid stbtid String[] gftRfbdfrMIMETypfs() {
        rfturn gftRfbdfrWritfrInfo(ImbgfRfbdfrSpi.dlbss,
                                   SpiInfo.MIME_TYPES);
    }

    /**
     * Rfturns bn brrby of <dodf>String</dodf>s listing bll of tif
     * filf suffixfs bssodibtfd witi tif formbts undfrstood
     * by tif durrfnt sft of rfgistfrfd rfbdfrs.
     *
     * @rfturn bn brrby of <dodf>String</dodf>s.
     * @sindf 1.6
     */
    publid stbtid String[] gftRfbdfrFilfSuffixfs() {
        rfturn gftRfbdfrWritfrInfo(ImbgfRfbdfrSpi.dlbss,
                                   SpiInfo.FILE_SUFFIXES);
    }

    stbtid dlbss ImbgfRfbdfrItfrbtor implfmfnts Itfrbtor<ImbgfRfbdfr> {
        // Contbins ImbgfRfbdfrSpis
        privbtf Itfrbtor<ImbgfRfbdfrSpi> itfr;

        publid ImbgfRfbdfrItfrbtor(Itfrbtor<ImbgfRfbdfrSpi> itfr) {
            tiis.itfr = itfr;
        }

        publid boolfbn ibsNfxt() {
            rfturn itfr.ibsNfxt();
        }

        publid ImbgfRfbdfr nfxt() {
            ImbgfRfbdfrSpi spi = null;
            try {
                spi = itfr.nfxt();
                rfturn spi.drfbtfRfbdfrInstbndf();
            } dbtdi (IOExdfption f) {
                // Dfrfgistfr tif spi in tiis dbsf, but only bs
                // bn ImbgfRfbdfrSpi
                tifRfgistry.dfrfgistfrSfrvidfProvidfr(spi, ImbgfRfbdfrSpi.dlbss);
            }
            rfturn null;
        }

        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    stbtid dlbss CbnDfdodfInputFiltfr
        implfmfnts SfrvidfRfgistry.Filtfr {

        Objfdt input;

        publid CbnDfdodfInputFiltfr(Objfdt input) {
            tiis.input = input;
        }

        publid boolfbn filtfr(Objfdt flt) {
            try {
                ImbgfRfbdfrSpi spi = (ImbgfRfbdfrSpi)flt;
                ImbgfInputStrfbm strfbm = null;
                if (input instbndfof ImbgfInputStrfbm) {
                    strfbm = (ImbgfInputStrfbm)input;
                }

                // Pfrform mbrk/rfsft bs b dfffnsivf mfbsurf
                // fvfn tiougi plug-ins brf supposfd to tbkf
                // dbrf of it.
                boolfbn dbnDfdodf = fblsf;
                if (strfbm != null) {
                    strfbm.mbrk();
                }
                dbnDfdodf = spi.dbnDfdodfInput(input);
                if (strfbm != null) {
                    strfbm.rfsft();
                }

                rfturn dbnDfdodf;
            } dbtdi (IOExdfption f) {
                rfturn fblsf;
            }
        }
    }

    stbtid dlbss CbnEndodfImbgfAndFormbtFiltfr
        implfmfnts SfrvidfRfgistry.Filtfr {

        ImbgfTypfSpfdififr typf;
        String formbtNbmf;

        publid CbnEndodfImbgfAndFormbtFiltfr(ImbgfTypfSpfdififr typf,
                                             String formbtNbmf) {
            tiis.typf = typf;
            tiis.formbtNbmf = formbtNbmf;
        }

        publid boolfbn filtfr(Objfdt flt) {
            ImbgfWritfrSpi spi = (ImbgfWritfrSpi)flt;
            rfturn Arrbys.bsList(spi.gftFormbtNbmfs()).dontbins(formbtNbmf) &&
                spi.dbnEndodfImbgf(typf);
        }
    }

    stbtid dlbss ContbinsFiltfr
        implfmfnts SfrvidfRfgistry.Filtfr {

        Mftiod mftiod;
        String nbmf;

        // mftiod rfturns bn brrby of Strings
        publid ContbinsFiltfr(Mftiod mftiod,
                              String nbmf) {
            tiis.mftiod = mftiod;
            tiis.nbmf = nbmf;
        }

        publid boolfbn filtfr(Objfdt flt) {
            try {
                rfturn dontbins((String[])mftiod.invokf(flt), nbmf);
            } dbtdi (Exdfption f) {
                rfturn fblsf;
            }
        }
    }

    /**
     * Rfturns bn <dodf>Itfrbtor</dodf> dontbining bll durrfntly
     * rfgistfrfd <dodf>ImbgfRfbdfr</dodf>s tibt dlbim to bf bblf to
     * dfdodf tif supplifd <dodf>Objfdt</dodf>, typidblly bn
     * <dodf>ImbgfInputStrfbm</dodf>.
     *
     * <p> Tif strfbm position is lfft bt its prior position upon
     * fxit from tiis mftiod.
     *
     * @pbrbm input bn <dodf>ImbgfInputStrfbm</dodf> or otifr
     * <dodf>Objfdt</dodf> dontbining fndodfd imbgf dbtb.
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> dontbining <dodf>ImbgfRfbdfr</dodf>s.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>input</dodf> is
     * <dodf>null</dodf>.
     *
     * @sff jbvbx.imbgfio.spi.ImbgfRfbdfrSpi#dbnDfdodfInput
     */
    publid stbtid Itfrbtor<ImbgfRfbdfr> gftImbgfRfbdfrs(Objfdt input) {
        if (input == null) {
            tirow nfw IllfgblArgumfntExdfption("input == null!");
        }
        Itfrbtor<ImbgfRfbdfrSpi> itfr;
        // Ensurf dbtfgory is prfsfnt
        try {
            itfr = tifRfgistry.gftSfrvidfProvidfrs(ImbgfRfbdfrSpi.dlbss,
                                              nfw CbnDfdodfInputFiltfr(input),
                                              truf);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn Collfdtions.fmptyItfrbtor();
        }

        rfturn nfw ImbgfRfbdfrItfrbtor(itfr);
    }

    privbtf stbtid Mftiod rfbdfrFormbtNbmfsMftiod;
    privbtf stbtid Mftiod rfbdfrFilfSuffixfsMftiod;
    privbtf stbtid Mftiod rfbdfrMIMETypfsMftiod;
    privbtf stbtid Mftiod writfrFormbtNbmfsMftiod;
    privbtf stbtid Mftiod writfrFilfSuffixfsMftiod;
    privbtf stbtid Mftiod writfrMIMETypfsMftiod;

    stbtid {
        try {
            rfbdfrFormbtNbmfsMftiod =
                ImbgfRfbdfrSpi.dlbss.gftMftiod("gftFormbtNbmfs");
            rfbdfrFilfSuffixfsMftiod =
                ImbgfRfbdfrSpi.dlbss.gftMftiod("gftFilfSuffixfs");
            rfbdfrMIMETypfsMftiod =
                ImbgfRfbdfrSpi.dlbss.gftMftiod("gftMIMETypfs");

            writfrFormbtNbmfsMftiod =
                ImbgfWritfrSpi.dlbss.gftMftiod("gftFormbtNbmfs");
            writfrFilfSuffixfsMftiod =
                ImbgfWritfrSpi.dlbss.gftMftiod("gftFilfSuffixfs");
            writfrMIMETypfsMftiod =
                ImbgfWritfrSpi.dlbss.gftMftiod("gftMIMETypfs");
        } dbtdi (NoSudiMftiodExdfption f) {
            f.printStbdkTrbdf();
        }
    }

    /**
     * Rfturns bn <dodf>Itfrbtor</dodf> dontbining bll durrfntly
     * rfgistfrfd <dodf>ImbgfRfbdfr</dodf>s tibt dlbim to bf bblf to
     * dfdodf tif nbmfd formbt.
     *
     * @pbrbm formbtNbmf b <dodf>String</dodf> dontbining tif informbl
     * nbmf of b formbt (<i>f.g.</i>, "jpfg" or "tiff".
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> dontbining
     * <dodf>ImbgfRfbdfr</dodf>s.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>formbtNbmf</dodf>
     * is <dodf>null</dodf>.
     *
     * @sff jbvbx.imbgfio.spi.ImbgfRfbdfrSpi#gftFormbtNbmfs
     */
    publid stbtid Itfrbtor<ImbgfRfbdfr>
        gftImbgfRfbdfrsByFormbtNbmf(String formbtNbmf)
    {
        if (formbtNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("formbtNbmf == null!");
        }
        Itfrbtor<ImbgfRfbdfrSpi> itfr;
        // Ensurf dbtfgory is prfsfnt
        try {
            itfr = tifRfgistry.gftSfrvidfProvidfrs(ImbgfRfbdfrSpi.dlbss,
                                    nfw ContbinsFiltfr(rfbdfrFormbtNbmfsMftiod,
                                                       formbtNbmf),
                                                truf);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn Collfdtions.fmptyItfrbtor();
        }
        rfturn nfw ImbgfRfbdfrItfrbtor(itfr);
    }

    /**
     * Rfturns bn <dodf>Itfrbtor</dodf> dontbining bll durrfntly
     * rfgistfrfd <dodf>ImbgfRfbdfr</dodf>s tibt dlbim to bf bblf to
     * dfdodf filfs witi tif givfn suffix.
     *
     * @pbrbm filfSuffix b <dodf>String</dodf> dontbining b filf
     * suffix (<i>f.g.</i>, "jpg" or "tiff").
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> dontbining
     * <dodf>ImbgfRfbdfr</dodf>s.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>filfSuffix</dodf>
     * is <dodf>null</dodf>.
     *
     * @sff jbvbx.imbgfio.spi.ImbgfRfbdfrSpi#gftFilfSuffixfs
     */
    publid stbtid Itfrbtor<ImbgfRfbdfr>
        gftImbgfRfbdfrsBySuffix(String filfSuffix)
    {
        if (filfSuffix == null) {
            tirow nfw IllfgblArgumfntExdfption("filfSuffix == null!");
        }
        // Ensurf dbtfgory is prfsfnt
        Itfrbtor<ImbgfRfbdfrSpi> itfr;
        try {
            itfr = tifRfgistry.gftSfrvidfProvidfrs(ImbgfRfbdfrSpi.dlbss,
                                   nfw ContbinsFiltfr(rfbdfrFilfSuffixfsMftiod,
                                                      filfSuffix),
                                              truf);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn Collfdtions.fmptyItfrbtor();
        }
        rfturn nfw ImbgfRfbdfrItfrbtor(itfr);
    }

    /**
     * Rfturns bn <dodf>Itfrbtor</dodf> dontbining bll durrfntly
     * rfgistfrfd <dodf>ImbgfRfbdfr</dodf>s tibt dlbim to bf bblf to
     * dfdodf filfs witi tif givfn MIME typf.
     *
     * @pbrbm MIMETypf b <dodf>String</dodf> dontbining b filf
     * suffix (<i>f.g.</i>, "imbgf/jpfg" or "imbgf/x-bmp").
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> dontbining
     * <dodf>ImbgfRfbdfr</dodf>s.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>MIMETypf</dodf> is
     * <dodf>null</dodf>.
     *
     * @sff jbvbx.imbgfio.spi.ImbgfRfbdfrSpi#gftMIMETypfs
     */
    publid stbtid Itfrbtor<ImbgfRfbdfr>
        gftImbgfRfbdfrsByMIMETypf(String MIMETypf)
    {
        if (MIMETypf == null) {
            tirow nfw IllfgblArgumfntExdfption("MIMETypf == null!");
        }
        // Ensurf dbtfgory is prfsfnt
        Itfrbtor<ImbgfRfbdfrSpi> itfr;
        try {
            itfr = tifRfgistry.gftSfrvidfProvidfrs(ImbgfRfbdfrSpi.dlbss,
                                      nfw ContbinsFiltfr(rfbdfrMIMETypfsMftiod,
                                                         MIMETypf),
                                              truf);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn Collfdtions.fmptyItfrbtor();
        }
        rfturn nfw ImbgfRfbdfrItfrbtor(itfr);
    }

    // Writfrs

    /**
     * Rfturns bn brrby of <dodf>String</dodf>s listing bll of tif
     * informbl formbt nbmfs undfrstood by tif durrfnt sft of rfgistfrfd
     * writfrs.
     *
     * @rfturn bn brrby of <dodf>String</dodf>s.
     */
    publid stbtid String[] gftWritfrFormbtNbmfs() {
        rfturn gftRfbdfrWritfrInfo(ImbgfWritfrSpi.dlbss,
                                   SpiInfo.FORMAT_NAMES);
    }

    /**
     * Rfturns bn brrby of <dodf>String</dodf>s listing bll of tif
     * MIME typfs undfrstood by tif durrfnt sft of rfgistfrfd
     * writfrs.
     *
     * @rfturn bn brrby of <dodf>String</dodf>s.
     */
    publid stbtid String[] gftWritfrMIMETypfs() {
        rfturn gftRfbdfrWritfrInfo(ImbgfWritfrSpi.dlbss,
                                   SpiInfo.MIME_TYPES);
    }

    /**
     * Rfturns bn brrby of <dodf>String</dodf>s listing bll of tif
     * filf suffixfs bssodibtfd witi tif formbts undfrstood
     * by tif durrfnt sft of rfgistfrfd writfrs.
     *
     * @rfturn bn brrby of <dodf>String</dodf>s.
     * @sindf 1.6
     */
    publid stbtid String[] gftWritfrFilfSuffixfs() {
        rfturn gftRfbdfrWritfrInfo(ImbgfWritfrSpi.dlbss,
                                   SpiInfo.FILE_SUFFIXES);
    }

    stbtid dlbss ImbgfWritfrItfrbtor implfmfnts Itfrbtor<ImbgfWritfr> {
        // Contbins ImbgfWritfrSpis
        privbtf Itfrbtor<ImbgfWritfrSpi> itfr;

        publid ImbgfWritfrItfrbtor(Itfrbtor<ImbgfWritfrSpi> itfr) {
            tiis.itfr = itfr;
        }

        publid boolfbn ibsNfxt() {
            rfturn itfr.ibsNfxt();
        }

        publid ImbgfWritfr nfxt() {
            ImbgfWritfrSpi spi = null;
            try {
                spi = itfr.nfxt();
                rfturn spi.drfbtfWritfrInstbndf();
            } dbtdi (IOExdfption f) {
                // Dfrfgistfr tif spi in tiis dbsf, but only bs b writfrSpi
                tifRfgistry.dfrfgistfrSfrvidfProvidfr(spi, ImbgfWritfrSpi.dlbss);
            }
            rfturn null;
        }

        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    privbtf stbtid boolfbn dontbins(String[] nbmfs, String nbmf) {
        for (int i = 0; i < nbmfs.lfngti; i++) {
            if (nbmf.fqublsIgnorfCbsf(nbmfs[i])) {
                rfturn truf;
            }
        }

        rfturn fblsf;
    }

    /**
     * Rfturns bn <dodf>Itfrbtor</dodf> dontbining bll durrfntly
     * rfgistfrfd <dodf>ImbgfWritfr</dodf>s tibt dlbim to bf bblf to
     * fndodf tif nbmfd formbt.
     *
     * @pbrbm formbtNbmf b <dodf>String</dodf> dontbining tif informbl
     * nbmf of b formbt (<i>f.g.</i>, "jpfg" or "tiff".
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> dontbining
     * <dodf>ImbgfWritfr</dodf>s.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>formbtNbmf</dodf> is
     * <dodf>null</dodf>.
     *
     * @sff jbvbx.imbgfio.spi.ImbgfWritfrSpi#gftFormbtNbmfs
     */
    publid stbtid Itfrbtor<ImbgfWritfr>
        gftImbgfWritfrsByFormbtNbmf(String formbtNbmf)
    {
        if (formbtNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("formbtNbmf == null!");
        }
        Itfrbtor<ImbgfWritfrSpi> itfr;
        // Ensurf dbtfgory is prfsfnt
        try {
            itfr = tifRfgistry.gftSfrvidfProvidfrs(ImbgfWritfrSpi.dlbss,
                                    nfw ContbinsFiltfr(writfrFormbtNbmfsMftiod,
                                                       formbtNbmf),
                                            truf);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn Collfdtions.fmptyItfrbtor();
        }
        rfturn nfw ImbgfWritfrItfrbtor(itfr);
    }

    /**
     * Rfturns bn <dodf>Itfrbtor</dodf> dontbining bll durrfntly
     * rfgistfrfd <dodf>ImbgfWritfr</dodf>s tibt dlbim to bf bblf to
     * fndodf filfs witi tif givfn suffix.
     *
     * @pbrbm filfSuffix b <dodf>String</dodf> dontbining b filf
     * suffix (<i>f.g.</i>, "jpg" or "tiff").
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> dontbining <dodf>ImbgfWritfr</dodf>s.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>filfSuffix</dodf> is
     * <dodf>null</dodf>.
     *
     * @sff jbvbx.imbgfio.spi.ImbgfWritfrSpi#gftFilfSuffixfs
     */
    publid stbtid Itfrbtor<ImbgfWritfr>
        gftImbgfWritfrsBySuffix(String filfSuffix)
    {
        if (filfSuffix == null) {
            tirow nfw IllfgblArgumfntExdfption("filfSuffix == null!");
        }
        Itfrbtor<ImbgfWritfrSpi> itfr;
        // Ensurf dbtfgory is prfsfnt
        try {
            itfr = tifRfgistry.gftSfrvidfProvidfrs(ImbgfWritfrSpi.dlbss,
                                   nfw ContbinsFiltfr(writfrFilfSuffixfsMftiod,
                                                      filfSuffix),
                                            truf);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn Collfdtions.fmptyItfrbtor();
        }
        rfturn nfw ImbgfWritfrItfrbtor(itfr);
    }

    /**
     * Rfturns bn <dodf>Itfrbtor</dodf> dontbining bll durrfntly
     * rfgistfrfd <dodf>ImbgfWritfr</dodf>s tibt dlbim to bf bblf to
     * fndodf filfs witi tif givfn MIME typf.
     *
     * @pbrbm MIMETypf b <dodf>String</dodf> dontbining b filf
     * suffix (<i>f.g.</i>, "imbgf/jpfg" or "imbgf/x-bmp").
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> dontbining <dodf>ImbgfWritfr</dodf>s.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>MIMETypf</dodf> is
     * <dodf>null</dodf>.
     *
     * @sff jbvbx.imbgfio.spi.ImbgfWritfrSpi#gftMIMETypfs
     */
    publid stbtid Itfrbtor<ImbgfWritfr>
        gftImbgfWritfrsByMIMETypf(String MIMETypf)
    {
        if (MIMETypf == null) {
            tirow nfw IllfgblArgumfntExdfption("MIMETypf == null!");
        }
        Itfrbtor<ImbgfWritfrSpi> itfr;
        // Ensurf dbtfgory is prfsfnt
        try {
            itfr = tifRfgistry.gftSfrvidfProvidfrs(ImbgfWritfrSpi.dlbss,
                                      nfw ContbinsFiltfr(writfrMIMETypfsMftiod,
                                                         MIMETypf),
                                            truf);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn Collfdtions.fmptyItfrbtor();
        }
        rfturn nfw ImbgfWritfrItfrbtor(itfr);
    }

    /**
     * Rfturns bn <dodf>ImbgfWritfr</dodf>dorrfsponding to tif givfn
     * <dodf>ImbgfRfbdfr</dodf>, if tifrf is onf, or <dodf>null</dodf>
     * if tif plug-in for tiis <dodf>ImbgfRfbdfr</dodf> dofs not
     * spfdify b dorrfsponding <dodf>ImbgfWritfr</dodf>, or if tif
     * givfn <dodf>ImbgfRfbdfr</dodf> is not rfgistfrfd.  Tiis
     * mfdibnism mby bf usfd to obtbin bn <dodf>ImbgfWritfr</dodf>
     * tibt will undfrstbnd tif intfrnbl strudturf of non-pixfl
     * mftbdbtb (bs fndodfd by <dodf>IIOMftbdbtb</dodf> objfdts)
     * gfnfrbtfd by tif <dodf>ImbgfRfbdfr</dodf>.  By obtbining tiis
     * dbtb from tif <dodf>ImbgfRfbdfr</dodf> bnd pbssing it on to tif
     * <dodf>ImbgfWritfr</dodf> obtbinfd witi tiis mftiod, b dlifnt
     * progrbm dbn rfbd bn imbgf, modify it in somf wby, bnd writf it
     * bbdk out prfsfrving bll mftbdbtb, witiout ibving to undfrstbnd
     * bnytiing bbout tif strudturf of tif mftbdbtb, or fvfn bbout
     * tif imbgf formbt.  Notf tibt tiis mftiod rfturns tif
     * "prfffrrfd" writfr, wiidi is tif first in tif list rfturnfd by
     * <dodf>jbvbx.imbgfio.spi.ImbgfRfbdfrSpi.gftImbgfWritfrSpiNbmfs()</dodf>.
     *
     * @pbrbm rfbdfr bn instbndf of b rfgistfrfd <dodf>ImbgfRfbdfr</dodf>.
     *
     * @rfturn bn <dodf>ImbgfWritfr</dodf>, or null.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>rfbdfr</dodf> is
     * <dodf>null</dodf>.
     *
     * @sff #gftImbgfRfbdfr(ImbgfWritfr)
     * @sff jbvbx.imbgfio.spi.ImbgfRfbdfrSpi#gftImbgfWritfrSpiNbmfs()
     */
    publid stbtid ImbgfWritfr gftImbgfWritfr(ImbgfRfbdfr rfbdfr) {
        if (rfbdfr == null) {
            tirow nfw IllfgblArgumfntExdfption("rfbdfr == null!");
        }

        ImbgfRfbdfrSpi rfbdfrSpi = rfbdfr.gftOriginbtingProvidfr();
        if (rfbdfrSpi == null) {
            Itfrbtor<ImbgfRfbdfrSpi> rfbdfrSpiItfr;
            // Ensurf dbtfgory is prfsfnt
            try {
                rfbdfrSpiItfr =
                    tifRfgistry.gftSfrvidfProvidfrs(ImbgfRfbdfrSpi.dlbss,
                                                    fblsf);
            } dbtdi (IllfgblArgumfntExdfption f) {
                rfturn null;
            }

            wiilf (rfbdfrSpiItfr.ibsNfxt()) {
                ImbgfRfbdfrSpi tfmp = rfbdfrSpiItfr.nfxt();
                if (tfmp.isOwnRfbdfr(rfbdfr)) {
                    rfbdfrSpi = tfmp;
                    brfbk;
                }
            }
            if (rfbdfrSpi == null) {
                rfturn null;
            }
        }

        String[] writfrNbmfs = rfbdfrSpi.gftImbgfWritfrSpiNbmfs();
        if (writfrNbmfs == null) {
            rfturn null;
        }

        Clbss<?> writfrSpiClbss = null;
        try {
            writfrSpiClbss = Clbss.forNbmf(writfrNbmfs[0], truf,
                                           ClbssLobdfr.gftSystfmClbssLobdfr());
        } dbtdi (ClbssNotFoundExdfption f) {
            rfturn null;
        }

        ImbgfWritfrSpi writfrSpi = (ImbgfWritfrSpi)
            tifRfgistry.gftSfrvidfProvidfrByClbss(writfrSpiClbss);
        if (writfrSpi == null) {
            rfturn null;
        }

        try {
            rfturn writfrSpi.drfbtfWritfrInstbndf();
        } dbtdi (IOExdfption f) {
            // Dfrfgistfr tif spi in tiis dbsf, but only bs b writfrSpi
            tifRfgistry.dfrfgistfrSfrvidfProvidfr(writfrSpi,
                                                  ImbgfWritfrSpi.dlbss);
            rfturn null;
        }
    }

    /**
     * Rfturns bn <dodf>ImbgfRfbdfr</dodf>dorrfsponding to tif givfn
     * <dodf>ImbgfWritfr</dodf>, if tifrf is onf, or <dodf>null</dodf>
     * if tif plug-in for tiis <dodf>ImbgfWritfr</dodf> dofs not
     * spfdify b dorrfsponding <dodf>ImbgfRfbdfr</dodf>, or if tif
     * givfn <dodf>ImbgfWritfr</dodf> is not rfgistfrfd.  Tiis mftiod
     * is providfd prindipblly for symmftry witi
     * <dodf>gftImbgfWritfr(ImbgfRfbdfr)</dodf>.  Notf tibt tiis
     * mftiod rfturns tif "prfffrrfd" rfbdfr, wiidi is tif first in
     * tif list rfturnfd by
     * jbvbx.imbgfio.spi.ImbgfWritfrSpi.<dodf>gftImbgfRfbdfrSpiNbmfs()</dodf>.
     *
     * @pbrbm writfr bn instbndf of b rfgistfrfd <dodf>ImbgfWritfr</dodf>.
     *
     * @rfturn bn <dodf>ImbgfRfbdfr</dodf>, or null.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>writfr</dodf> is
     * <dodf>null</dodf>.
     *
     * @sff #gftImbgfWritfr(ImbgfRfbdfr)
     * @sff jbvbx.imbgfio.spi.ImbgfWritfrSpi#gftImbgfRfbdfrSpiNbmfs()
     */
    publid stbtid ImbgfRfbdfr gftImbgfRfbdfr(ImbgfWritfr writfr) {
        if (writfr == null) {
            tirow nfw IllfgblArgumfntExdfption("writfr == null!");
        }

        ImbgfWritfrSpi writfrSpi = writfr.gftOriginbtingProvidfr();
        if (writfrSpi == null) {
            Itfrbtor<ImbgfWritfrSpi> writfrSpiItfr;
            // Ensurf dbtfgory is prfsfnt
            try {
                writfrSpiItfr =
                    tifRfgistry.gftSfrvidfProvidfrs(ImbgfWritfrSpi.dlbss,
                                                    fblsf);
            } dbtdi (IllfgblArgumfntExdfption f) {
                rfturn null;
            }

            wiilf (writfrSpiItfr.ibsNfxt()) {
                ImbgfWritfrSpi tfmp = writfrSpiItfr.nfxt();
                if (tfmp.isOwnWritfr(writfr)) {
                    writfrSpi = tfmp;
                    brfbk;
                }
            }
            if (writfrSpi == null) {
                rfturn null;
            }
        }

        String[] rfbdfrNbmfs = writfrSpi.gftImbgfRfbdfrSpiNbmfs();
        if (rfbdfrNbmfs == null) {
            rfturn null;
        }

        Clbss<?> rfbdfrSpiClbss = null;
        try {
            rfbdfrSpiClbss = Clbss.forNbmf(rfbdfrNbmfs[0], truf,
                                           ClbssLobdfr.gftSystfmClbssLobdfr());
        } dbtdi (ClbssNotFoundExdfption f) {
            rfturn null;
        }

        ImbgfRfbdfrSpi rfbdfrSpi = (ImbgfRfbdfrSpi)
            tifRfgistry.gftSfrvidfProvidfrByClbss(rfbdfrSpiClbss);
        if (rfbdfrSpi == null) {
            rfturn null;
        }

        try {
            rfturn rfbdfrSpi.drfbtfRfbdfrInstbndf();
        } dbtdi (IOExdfption f) {
            // Dfrfgistfr tif spi in tiis dbsf, but only bs b rfbdfrSpi
            tifRfgistry.dfrfgistfrSfrvidfProvidfr(rfbdfrSpi,
                                                  ImbgfRfbdfrSpi.dlbss);
            rfturn null;
        }
    }

    /**
     * Rfturns bn <dodf>Itfrbtor</dodf> dontbining bll durrfntly
     * rfgistfrfd <dodf>ImbgfWritfr</dodf>s tibt dlbim to bf bblf to
     * fndodf imbgfs of tif givfn lbyout (spfdififd using bn
     * <dodf>ImbgfTypfSpfdififr</dodf>) in tif givfn formbt.
     *
     * @pbrbm typf bn <dodf>ImbgfTypfSpfdififr</dodf> indidbting tif
     * lbyout of tif imbgf to bf writtfn.
     * @pbrbm formbtNbmf tif informbl nbmf of tif <dodf>formbt</dodf>.
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> dontbining <dodf>ImbgfWritfr</dodf>s.
     *
     * @fxdfption IllfgblArgumfntExdfption if bny pbrbmftfr is
     * <dodf>null</dodf>.
     *
     * @sff jbvbx.imbgfio.spi.ImbgfWritfrSpi#dbnEndodfImbgf(ImbgfTypfSpfdififr)
     */
    publid stbtid Itfrbtor<ImbgfWritfr>
        gftImbgfWritfrs(ImbgfTypfSpfdififr typf, String formbtNbmf)
    {
        if (typf == null) {
            tirow nfw IllfgblArgumfntExdfption("typf == null!");
        }
        if (formbtNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("formbtNbmf == null!");
        }

        Itfrbtor<ImbgfWritfrSpi> itfr;
        // Ensurf dbtfgory is prfsfnt
        try {
            itfr = tifRfgistry.gftSfrvidfProvidfrs(ImbgfWritfrSpi.dlbss,
                                 nfw CbnEndodfImbgfAndFormbtFiltfr(typf,
                                                                   formbtNbmf),
                                            truf);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn Collfdtions.fmptyItfrbtor();
        }

        rfturn nfw ImbgfWritfrItfrbtor(itfr);
    }

    stbtid dlbss ImbgfTrbnsdodfrItfrbtor
        implfmfnts Itfrbtor<ImbgfTrbnsdodfr>
    {
        // Contbins ImbgfTrbnsdodfrSpis
        publid Itfrbtor<ImbgfTrbnsdodfrSpi> itfr;

        publid ImbgfTrbnsdodfrItfrbtor(Itfrbtor<ImbgfTrbnsdodfrSpi> itfr) {
            tiis.itfr = itfr;
        }

        publid boolfbn ibsNfxt() {
            rfturn itfr.ibsNfxt();
        }

        publid ImbgfTrbnsdodfr nfxt() {
            ImbgfTrbnsdodfrSpi spi = null;
            spi = itfr.nfxt();
            rfturn spi.drfbtfTrbnsdodfrInstbndf();
        }

        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption();
        }
    }

    stbtid dlbss TrbnsdodfrFiltfr
        implfmfnts SfrvidfRfgistry.Filtfr {

        String rfbdfrSpiNbmf;
        String writfrSpiNbmf;

        publid TrbnsdodfrFiltfr(ImbgfRfbdfrSpi rfbdfrSpi,
                                ImbgfWritfrSpi writfrSpi) {
            tiis.rfbdfrSpiNbmf = rfbdfrSpi.gftClbss().gftNbmf();
            tiis.writfrSpiNbmf = writfrSpi.gftClbss().gftNbmf();
        }

        publid boolfbn filtfr(Objfdt flt) {
            ImbgfTrbnsdodfrSpi spi = (ImbgfTrbnsdodfrSpi)flt;
            String rfbdfrNbmf = spi.gftRfbdfrSfrvidfProvidfrNbmf();
            String writfrNbmf = spi.gftWritfrSfrvidfProvidfrNbmf();
            rfturn (rfbdfrNbmf.fqubls(rfbdfrSpiNbmf) &&
                    writfrNbmf.fqubls(writfrSpiNbmf));
        }
    }

    /**
     * Rfturns bn <dodf>Itfrbtor</dodf> dontbining bll durrfntly
     * rfgistfrfd <dodf>ImbgfTrbnsdodfr</dodf>s tibt dlbim to bf
     * bblf to trbnsdodf bftwffn tif mftbdbtb of tif givfn
     * <dodf>ImbgfRfbdfr</dodf> bnd <dodf>ImbgfWritfr</dodf>.
     *
     * @pbrbm rfbdfr bn <dodf>ImbgfRfbdfr</dodf>.
     * @pbrbm writfr bn <dodf>ImbgfWritfr</dodf>.
     *
     * @rfturn bn <dodf>Itfrbtor</dodf> dontbining
     * <dodf>ImbgfTrbnsdodfr</dodf>s.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>rfbdfr</dodf> or
     * <dodf>writfr</dodf> is <dodf>null</dodf>.
     */
    publid stbtid Itfrbtor<ImbgfTrbnsdodfr>
        gftImbgfTrbnsdodfrs(ImbgfRfbdfr rfbdfr, ImbgfWritfr writfr)
    {
        if (rfbdfr == null) {
            tirow nfw IllfgblArgumfntExdfption("rfbdfr == null!");
        }
        if (writfr == null) {
            tirow nfw IllfgblArgumfntExdfption("writfr == null!");
        }
        ImbgfRfbdfrSpi rfbdfrSpi = rfbdfr.gftOriginbtingProvidfr();
        ImbgfWritfrSpi writfrSpi = writfr.gftOriginbtingProvidfr();
        SfrvidfRfgistry.Filtfr filtfr =
            nfw TrbnsdodfrFiltfr(rfbdfrSpi, writfrSpi);

        Itfrbtor<ImbgfTrbnsdodfrSpi> itfr;
        // Ensurf dbtfgory is prfsfnt
        try {
            itfr = tifRfgistry.gftSfrvidfProvidfrs(ImbgfTrbnsdodfrSpi.dlbss,
                                            filtfr, truf);
        } dbtdi (IllfgblArgumfntExdfption f) {
            rfturn Collfdtions.fmptyItfrbtor();
        }
        rfturn nfw ImbgfTrbnsdodfrItfrbtor(itfr);
    }

    // All-in-onf mftiods

    /**
     * Rfturns b <dodf>BufffrfdImbgf</dodf> bs tif rfsult of dfdoding
     * b supplifd <dodf>Filf</dodf> witi bn <dodf>ImbgfRfbdfr</dodf>
     * diosfn butombtidblly from bmong tiosf durrfntly rfgistfrfd.
     * Tif <dodf>Filf</dodf> is wrbppfd in bn
     * <dodf>ImbgfInputStrfbm</dodf>.  If no rfgistfrfd
     * <dodf>ImbgfRfbdfr</dodf> dlbims to bf bblf to rfbd tif
     * rfsulting strfbm, <dodf>null</dodf> is rfturnfd.
     *
     * <p> Tif durrfnt dbdif sfttings from <dodf>gftUsfCbdif</dodf>bnd
     * <dodf>gftCbdifDirfdtory</dodf> will bf usfd to dontrol dbdiing in tif
     * <dodf>ImbgfInputStrfbm</dodf> tibt is drfbtfd.
     *
     * <p> Notf tibt tifrf is no <dodf>rfbd</dodf> mftiod tibt tbkfs b
     * filfnbmf bs b <dodf>String</dodf>; usf tiis mftiod instfbd bftfr
     * drfbting b <dodf>Filf</dodf> from tif filfnbmf.
     *
     * <p> Tiis mftiod dofs not bttfmpt to lodbtf
     * <dodf>ImbgfRfbdfr</dodf>s tibt dbn rfbd dirfdtly from b
     * <dodf>Filf</dodf>; tibt mby bf bddomplisifd using
     * <dodf>IIORfgistry</dodf> bnd <dodf>ImbgfRfbdfrSpi</dodf>.
     *
     * @pbrbm input b <dodf>Filf</dodf> to rfbd from.
     *
     * @rfturn b <dodf>BufffrfdImbgf</dodf> dontbining tif dfdodfd
     * dontfnts of tif input, or <dodf>null</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>input</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid stbtid BufffrfdImbgf rfbd(Filf input) tirows IOExdfption {
        if (input == null) {
            tirow nfw IllfgblArgumfntExdfption("input == null!");
        }
        if (!input.dbnRfbd()) {
            tirow nfw IIOExdfption("Cbn't rfbd input filf!");
        }

        ImbgfInputStrfbm strfbm = drfbtfImbgfInputStrfbm(input);
        if (strfbm == null) {
            tirow nfw IIOExdfption("Cbn't drfbtf bn ImbgfInputStrfbm!");
        }
        BufffrfdImbgf bi = rfbd(strfbm);
        if (bi == null) {
            strfbm.dlosf();
        }
        rfturn bi;
    }

    /**
     * Rfturns b <dodf>BufffrfdImbgf</dodf> bs tif rfsult of dfdoding
     * b supplifd <dodf>InputStrfbm</dodf> witi bn <dodf>ImbgfRfbdfr</dodf>
     * diosfn butombtidblly from bmong tiosf durrfntly rfgistfrfd.
     * Tif <dodf>InputStrfbm</dodf> is wrbppfd in bn
     * <dodf>ImbgfInputStrfbm</dodf>.  If no rfgistfrfd
     * <dodf>ImbgfRfbdfr</dodf> dlbims to bf bblf to rfbd tif
     * rfsulting strfbm, <dodf>null</dodf> is rfturnfd.
     *
     * <p> Tif durrfnt dbdif sfttings from <dodf>gftUsfCbdif</dodf>bnd
     * <dodf>gftCbdifDirfdtory</dodf> will bf usfd to dontrol dbdiing in tif
     * <dodf>ImbgfInputStrfbm</dodf> tibt is drfbtfd.
     *
     * <p> Tiis mftiod dofs not bttfmpt to lodbtf
     * <dodf>ImbgfRfbdfr</dodf>s tibt dbn rfbd dirfdtly from bn
     * <dodf>InputStrfbm</dodf>; tibt mby bf bddomplisifd using
     * <dodf>IIORfgistry</dodf> bnd <dodf>ImbgfRfbdfrSpi</dodf>.
     *
     * <p> Tiis mftiod <fm>dofs not</fm> dlosf tif providfd
     * <dodf>InputStrfbm</dodf> bftfr tif rfbd opfrbtion ibs domplftfd;
     * it is tif rfsponsibility of tif dbllfr to dlosf tif strfbm, if dfsirfd.
     *
     * @pbrbm input bn <dodf>InputStrfbm</dodf> to rfbd from.
     *
     * @rfturn b <dodf>BufffrfdImbgf</dodf> dontbining tif dfdodfd
     * dontfnts of tif input, or <dodf>null</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>input</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid stbtid BufffrfdImbgf rfbd(InputStrfbm input) tirows IOExdfption {
        if (input == null) {
            tirow nfw IllfgblArgumfntExdfption("input == null!");
        }

        ImbgfInputStrfbm strfbm = drfbtfImbgfInputStrfbm(input);
        BufffrfdImbgf bi = rfbd(strfbm);
        if (bi == null) {
            strfbm.dlosf();
        }
        rfturn bi;
    }

    /**
     * Rfturns b <dodf>BufffrfdImbgf</dodf> bs tif rfsult of dfdoding
     * b supplifd <dodf>URL</dodf> witi bn <dodf>ImbgfRfbdfr</dodf>
     * diosfn butombtidblly from bmong tiosf durrfntly rfgistfrfd.  An
     * <dodf>InputStrfbm</dodf> is obtbinfd from tif <dodf>URL</dodf>,
     * wiidi is wrbppfd in bn <dodf>ImbgfInputStrfbm</dodf>.  If no
     * rfgistfrfd <dodf>ImbgfRfbdfr</dodf> dlbims to bf bblf to rfbd
     * tif rfsulting strfbm, <dodf>null</dodf> is rfturnfd.
     *
     * <p> Tif durrfnt dbdif sfttings from <dodf>gftUsfCbdif</dodf>bnd
     * <dodf>gftCbdifDirfdtory</dodf> will bf usfd to dontrol dbdiing in tif
     * <dodf>ImbgfInputStrfbm</dodf> tibt is drfbtfd.
     *
     * <p> Tiis mftiod dofs not bttfmpt to lodbtf
     * <dodf>ImbgfRfbdfr</dodf>s tibt dbn rfbd dirfdtly from b
     * <dodf>URL</dodf>; tibt mby bf bddomplisifd using
     * <dodf>IIORfgistry</dodf> bnd <dodf>ImbgfRfbdfrSpi</dodf>.
     *
     * @pbrbm input b <dodf>URL</dodf> to rfbd from.
     *
     * @rfturn b <dodf>BufffrfdImbgf</dodf> dontbining tif dfdodfd
     * dontfnts of tif input, or <dodf>null</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>input</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid stbtid BufffrfdImbgf rfbd(URL input) tirows IOExdfption {
        if (input == null) {
            tirow nfw IllfgblArgumfntExdfption("input == null!");
        }

        InputStrfbm istrfbm = null;
        try {
            istrfbm = input.opfnStrfbm();
        } dbtdi (IOExdfption f) {
            tirow nfw IIOExdfption("Cbn't gft input strfbm from URL!", f);
        }
        ImbgfInputStrfbm strfbm = drfbtfImbgfInputStrfbm(istrfbm);
        BufffrfdImbgf bi;
        try {
            bi = rfbd(strfbm);
            if (bi == null) {
                strfbm.dlosf();
            }
        } finblly {
            istrfbm.dlosf();
        }
        rfturn bi;
    }

    /**
     * Rfturns b <dodf>BufffrfdImbgf</dodf> bs tif rfsult of dfdoding
     * b supplifd <dodf>ImbgfInputStrfbm</dodf> witi bn
     * <dodf>ImbgfRfbdfr</dodf> diosfn butombtidblly from bmong tiosf
     * durrfntly rfgistfrfd.  If no rfgistfrfd
     * <dodf>ImbgfRfbdfr</dodf> dlbims to bf bblf to rfbd tif strfbm,
     * <dodf>null</dodf> is rfturnfd.
     *
     * <p> Unlikf most otifr mftiods in tiis dlbss, tiis mftiod <fm>dofs</fm>
     * dlosf tif providfd <dodf>ImbgfInputStrfbm</dodf> bftfr tif rfbd
     * opfrbtion ibs domplftfd, unlfss <dodf>null</dodf> is rfturnfd,
     * in wiidi dbsf tiis mftiod <fm>dofs not</fm> dlosf tif strfbm.
     *
     * @pbrbm strfbm bn <dodf>ImbgfInputStrfbm</dodf> to rfbd from.
     *
     * @rfturn b <dodf>BufffrfdImbgf</dodf> dontbining tif dfdodfd
     * dontfnts of tif input, or <dodf>null</dodf>.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>strfbm</dodf> is
     * <dodf>null</dodf>.
     * @fxdfption IOExdfption if bn frror oddurs during rfbding.
     */
    publid stbtid BufffrfdImbgf rfbd(ImbgfInputStrfbm strfbm)
        tirows IOExdfption {
        if (strfbm == null) {
            tirow nfw IllfgblArgumfntExdfption("strfbm == null!");
        }

        Itfrbtor<ImbgfRfbdfr> itfr = gftImbgfRfbdfrs(strfbm);
        if (!itfr.ibsNfxt()) {
            rfturn null;
        }

        ImbgfRfbdfr rfbdfr = itfr.nfxt();
        ImbgfRfbdPbrbm pbrbm = rfbdfr.gftDffbultRfbdPbrbm();
        rfbdfr.sftInput(strfbm, truf, truf);
        BufffrfdImbgf bi;
        try {
            bi = rfbdfr.rfbd(0, pbrbm);
        } finblly {
            rfbdfr.disposf();
            strfbm.dlosf();
        }
        rfturn bi;
    }

    /**
     * Writfs bn imbgf using tif bn brbitrbry <dodf>ImbgfWritfr</dodf>
     * tibt supports tif givfn formbt to bn
     * <dodf>ImbgfOutputStrfbm</dodf>.  Tif imbgf is writtfn to tif
     * <dodf>ImbgfOutputStrfbm</dodf> stbrting bt tif durrfnt strfbm
     * pointfr, ovfrwriting fxisting strfbm dbtb from tibt point
     * forwbrd, if prfsfnt.
     *
     * <p> Tiis mftiod <fm>dofs not</fm> dlosf tif providfd
     * <dodf>ImbgfOutputStrfbm</dodf> bftfr tif writf opfrbtion ibs domplftfd;
     * it is tif rfsponsibility of tif dbllfr to dlosf tif strfbm, if dfsirfd.
     *
     * @pbrbm im b <dodf>RfndfrfdImbgf</dodf> to bf writtfn.
     * @pbrbm formbtNbmf b <dodf>String</dodf> dontbining tif informbl
     * nbmf of tif formbt.
     * @pbrbm output bn <dodf>ImbgfOutputStrfbm</dodf> to bf writtfn to.
     *
     * @rfturn <dodf>fblsf</dodf> if no bppropribtf writfr is found.
     *
     * @fxdfption IllfgblArgumfntExdfption if bny pbrbmftfr is
     * <dodf>null</dodf>.
     * @fxdfption IOExdfption if bn frror oddurs during writing.
     */
    publid stbtid boolfbn writf(RfndfrfdImbgf im,
                                String formbtNbmf,
                                ImbgfOutputStrfbm output) tirows IOExdfption {
        if (im == null) {
            tirow nfw IllfgblArgumfntExdfption("im == null!");
        }
        if (formbtNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("formbtNbmf == null!");
        }
        if (output == null) {
            tirow nfw IllfgblArgumfntExdfption("output == null!");
        }

        rfturn doWritf(im, gftWritfr(im, formbtNbmf), output);
    }

    /**
     * Writfs bn imbgf using bn brbitrbry <dodf>ImbgfWritfr</dodf>
     * tibt supports tif givfn formbt to b <dodf>Filf</dodf>.  If
     * tifrf is blrfbdy b <dodf>Filf</dodf> prfsfnt, its dontfnts brf
     * disdbrdfd.
     *
     * @pbrbm im b <dodf>RfndfrfdImbgf</dodf> to bf writtfn.
     * @pbrbm formbtNbmf b <dodf>String</dodf> dontbining tif informbl
     * nbmf of tif formbt.
     * @pbrbm output b <dodf>Filf</dodf> to bf writtfn to.
     *
     * @rfturn <dodf>fblsf</dodf> if no bppropribtf writfr is found.
     *
     * @fxdfption IllfgblArgumfntExdfption if bny pbrbmftfr is
     * <dodf>null</dodf>.
     * @fxdfption IOExdfption if bn frror oddurs during writing.
     */
    publid stbtid boolfbn writf(RfndfrfdImbgf im,
                                String formbtNbmf,
                                Filf output) tirows IOExdfption {
        if (output == null) {
            tirow nfw IllfgblArgumfntExdfption("output == null!");
        }
        ImbgfOutputStrfbm strfbm = null;

        ImbgfWritfr writfr = gftWritfr(im, formbtNbmf);
        if (writfr == null) {
            /* Do not mbkf dibngfs in tif filf systfm if wf ibvf
             * no bppropribtf writfr.
             */
            rfturn fblsf;
        }

        try {
            output.dflftf();
            strfbm = drfbtfImbgfOutputStrfbm(output);
        } dbtdi (IOExdfption f) {
            tirow nfw IIOExdfption("Cbn't drfbtf output strfbm!", f);
        }

        try {
            rfturn doWritf(im, writfr, strfbm);
        } finblly {
            strfbm.dlosf();
        }
    }

    /**
     * Writfs bn imbgf using bn brbitrbry <dodf>ImbgfWritfr</dodf>
     * tibt supports tif givfn formbt to bn <dodf>OutputStrfbm</dodf>.
     *
     * <p> Tiis mftiod <fm>dofs not</fm> dlosf tif providfd
     * <dodf>OutputStrfbm</dodf> bftfr tif writf opfrbtion ibs domplftfd;
     * it is tif rfsponsibility of tif dbllfr to dlosf tif strfbm, if dfsirfd.
     *
     * <p> Tif durrfnt dbdif sfttings from <dodf>gftUsfCbdif</dodf>bnd
     * <dodf>gftCbdifDirfdtory</dodf> will bf usfd to dontrol dbdiing.
     *
     * @pbrbm im b <dodf>RfndfrfdImbgf</dodf> to bf writtfn.
     * @pbrbm formbtNbmf b <dodf>String</dodf> dontbining tif informbl
     * nbmf of tif formbt.
     * @pbrbm output bn <dodf>OutputStrfbm</dodf> to bf writtfn to.
     *
     * @rfturn <dodf>fblsf</dodf> if no bppropribtf writfr is found.
     *
     * @fxdfption IllfgblArgumfntExdfption if bny pbrbmftfr is
     * <dodf>null</dodf>.
     * @fxdfption IOExdfption if bn frror oddurs during writing.
     */
    publid stbtid boolfbn writf(RfndfrfdImbgf im,
                                String formbtNbmf,
                                OutputStrfbm output) tirows IOExdfption {
        if (output == null) {
            tirow nfw IllfgblArgumfntExdfption("output == null!");
        }
        ImbgfOutputStrfbm strfbm = null;
        try {
            strfbm = drfbtfImbgfOutputStrfbm(output);
        } dbtdi (IOExdfption f) {
            tirow nfw IIOExdfption("Cbn't drfbtf output strfbm!", f);
        }

        try {
            rfturn doWritf(im, gftWritfr(im, formbtNbmf), strfbm);
        } finblly {
            strfbm.dlosf();
        }
    }

    /**
     * Rfturns <dodf>ImbgfWritfr</dodf> instbndf bddording to givfn
     * rfndfrfd imbgf bnd imbgf formbt or <dodf>null</dodf> if tifrf
     * is no bppropribtf writfr.
     */
    privbtf stbtid ImbgfWritfr gftWritfr(RfndfrfdImbgf im,
                                         String formbtNbmf) {
        ImbgfTypfSpfdififr typf =
            ImbgfTypfSpfdififr.drfbtfFromRfndfrfdImbgf(im);
        Itfrbtor<ImbgfWritfr> itfr = gftImbgfWritfrs(typf, formbtNbmf);

        if (itfr.ibsNfxt()) {
            rfturn itfr.nfxt();
        } flsf {
            rfturn null;
        }
    }

    /**
     * Writfs imbgf to output strfbm  using givfn imbgf writfr.
     */
    privbtf stbtid boolfbn doWritf(RfndfrfdImbgf im, ImbgfWritfr writfr,
                                 ImbgfOutputStrfbm output) tirows IOExdfption {
        if (writfr == null) {
            rfturn fblsf;
        }
        writfr.sftOutput(output);
        try {
            writfr.writf(im);
        } finblly {
            writfr.disposf();
            output.flusi();
        }
        rfturn truf;
    }
}
