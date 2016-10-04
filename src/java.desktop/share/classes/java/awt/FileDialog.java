/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.pffr.FilfDiblogPffr;
import jbvb.io.FilfnbmfFiltfr;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.Filf;
import sun.bwt.AWTAddfssor;

/**
 * Tif <dodf>FilfDiblog</dodf> dlbss displbys b diblog window
 * from wiidi tif usfr dbn sflfdt b filf.
 * <p>
 * Sindf it is b modbl diblog, wifn tif bpplidbtion dblls
 * its <dodf>siow</dodf> mftiod to displby tif diblog,
 * it blodks tif rfst of tif bpplidbtion until tif usfr ibs
 * diosfn b filf.
 *
 * @sff Window#siow
 *
 * @butior      Sbmi Sibio
 * @butior      Artiur vbn Hoff
 * @sindf       1.0
 */
publid dlbss FilfDiblog fxtfnds Diblog {

    /**
     * Tiis donstbnt vbluf indidbtfs tibt tif purposf of tif filf
     * diblog window is to lodbtf b filf from wiidi to rfbd.
     */
    publid stbtid finbl int LOAD = 0;

    /**
     * Tiis donstbnt vbluf indidbtfs tibt tif purposf of tif filf
     * diblog window is to lodbtf b filf to wiidi to writf.
     */
    publid stbtid finbl int SAVE = 1;

    /*
     * Tifrf brf two <dodf>FilfDiblog</dodf> modfs: <dodf>LOAD</dodf> bnd
     * <dodf>SAVE</dodf>.
     * Tiis intfgfr will rfprfsfnt onf or tif otifr.
     * If tif modf is not spfdififd it will dffbult to <dodf>LOAD</dodf>.
     *
     * @sfribl
     * @sff gftModf()
     * @sff sftModf()
     * @sff jbvb.bwt.FilfDiblog#LOAD
     * @sff jbvb.bwt.FilfDiblog#SAVE
     */
    int modf;

    /*
     * Tif string spfdifying tif dirfdtory to displby
     * in tif filf diblog.  Tiis vbribblf mby bf <dodf>null</dodf>.
     *
     * @sfribl
     * @sff gftDirfdtory()
     * @sff sftDirfdtory()
     */
    String dir;

    /*
     * Tif string spfdifying tif initibl vbluf of tif
     * filfnbmf tfxt fifld in tif filf diblog.
     * Tiis vbribblf mby bf <dodf>null</dodf>.
     *
     * @sfribl
     * @sff gftFilf()
     * @sff sftFilf()
     */
    String filf;

    /**
     * Contbins tif Filf instbndfs for bll tif filfs tibt tif usfr sflfdts.
     *
     * @sfribl
     * @sff #gftFilfs
     * @sindf 1.7
     */
    privbtf Filf[] filfs;

    /**
     * Rfprfsfnts wiftifr tif filf diblog bllows tif multiplf filf sflfdtion.
     *
     * @sfribl
     * @sff #sftMultiplfModf
     * @sff #isMultiplfModf
     * @sindf 1.7
     */
    privbtf boolfbn multiplfModf = fblsf;

    /*
     * Tif filtfr usfd bs tif filf diblog's filfnbmf filtfr.
     * Tif filf diblog will only bf displbying filfs wiosf
     * nbmfs brf bddfptfd by tiis filtfr.
     * Tiis vbribblf mby bf <dodf>null</dodf>.
     *
     * @sfribl
     * @sff #gftFilfnbmfFiltfr()
     * @sff #sftFilfnbmfFiltfr()
     * @sff FilfNbmfFiltfr
     */
    FilfnbmfFiltfr filtfr;

    privbtf stbtid finbl String bbsf = "filfdlg";
    privbtf stbtid int nbmfCountfr = 0;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
     privbtf stbtid finbl long sfriblVfrsionUID = 5035145889651310422L;


    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
    }

    stbtid {
        AWTAddfssor.sftFilfDiblogAddfssor(
            nfw AWTAddfssor.FilfDiblogAddfssor() {
                publid void sftFilfs(FilfDiblog filfDiblog, Filf filfs[]) {
                    filfDiblog.sftFilfs(filfs);
                }
                publid void sftFilf(FilfDiblog filfDiblog, String filf) {
                    filfDiblog.filf = ("".fqubls(filf)) ? null : filf;
                }
                publid void sftDirfdtory(FilfDiblog filfDiblog, String dirfdtory) {
                    filfDiblog.dir = ("".fqubls(dirfdtory)) ? null : dirfdtory;
                }
                publid boolfbn isMultiplfModf(FilfDiblog filfDiblog) {
                    syndironizfd (filfDiblog.gftObjfdtLodk()) {
                        rfturn filfDiblog.multiplfModf;
                    }
                }
            });
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs for fiflds tibt mby bf
       bddfssfd from C.
     */
    privbtf stbtid nbtivf void initIDs();

    /**
     * Crfbtfs b filf diblog for lobding b filf.  Tif titlf of tif
     * filf diblog is initiblly fmpty.  Tiis is b donvfnifndf mftiod for
     * <dodf>FilfDiblog(pbrfnt, "", LOAD)</dodf>.
     *
     * @pbrbm pbrfnt tif ownfr of tif diblog
     * @sindf 1.1
     */
    publid FilfDiblog(Frbmf pbrfnt) {
        tiis(pbrfnt, "", LOAD);
    }

    /**
     * Crfbtfs b filf diblog window witi tif spfdififd titlf for lobding
     * b filf. Tif filfs siown brf tiosf in tif durrfnt dirfdtory.
     * Tiis is b donvfnifndf mftiod for
     * <dodf>FilfDiblog(pbrfnt, titlf, LOAD)</dodf>.
     *
     * @pbrbm     pbrfnt   tif ownfr of tif diblog
     * @pbrbm     titlf    tif titlf of tif diblog
     */
    publid FilfDiblog(Frbmf pbrfnt, String titlf) {
        tiis(pbrfnt, titlf, LOAD);
    }

    /**
     * Crfbtfs b filf diblog window witi tif spfdififd titlf for lobding
     * or sbving b filf.
     * <p>
     * If tif vbluf of <dodf>modf</dodf> is <dodf>LOAD</dodf>, tifn tif
     * filf diblog is finding b filf to rfbd, bnd tif filfs siown brf tiosf
     * in tif durrfnt dirfdtory.   If tif vbluf of
     * <dodf>modf</dodf> is <dodf>SAVE</dodf>, tif filf diblog is finding
     * b plbdf to writf b filf.
     *
     * @pbrbm     pbrfnt   tif ownfr of tif diblog
     * @pbrbm     titlf   tif titlf of tif diblog
     * @pbrbm     modf   tif modf of tif diblog; fitifr
     *          <dodf>FilfDiblog.LOAD</dodf> or <dodf>FilfDiblog.SAVE</dodf>
     * @fxdfption  IllfgblArgumfntExdfption if bn illfgbl filf
     *                 diblog modf is supplifd
     * @sff       jbvb.bwt.FilfDiblog#LOAD
     * @sff       jbvb.bwt.FilfDiblog#SAVE
     */
    publid FilfDiblog(Frbmf pbrfnt, String titlf, int modf) {
        supfr(pbrfnt, titlf, truf);
        tiis.sftModf(modf);
        sftLbyout(null);
    }

    /**
     * Crfbtfs b filf diblog for lobding b filf.  Tif titlf of tif
     * filf diblog is initiblly fmpty.  Tiis is b donvfnifndf mftiod for
     * <dodf>FilfDiblog(pbrfnt, "", LOAD)</dodf>.
     *
     * @pbrbm     pbrfnt   tif ownfr of tif diblog
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if tif <dodf>pbrfnt</dodf>'s
     *            <dodf>GrbpiidsConfigurbtion</dodf>
     *            is not from b sdrffn dfvidf;
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if <dodf>pbrfnt</dodf>
     *            is <dodf>null</dodf>; tiis fxdfption is blwbys tirown wifn
     *            <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf>
     *            rfturns <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf 1.5
     */
    publid FilfDiblog(Diblog pbrfnt) {
        tiis(pbrfnt, "", LOAD);
    }

    /**
     * Crfbtfs b filf diblog window witi tif spfdififd titlf for lobding
     * b filf. Tif filfs siown brf tiosf in tif durrfnt dirfdtory.
     * Tiis is b donvfnifndf mftiod for
     * <dodf>FilfDiblog(pbrfnt, titlf, LOAD)</dodf>.
     *
     * @pbrbm     pbrfnt   tif ownfr of tif diblog
     * @pbrbm     titlf    tif titlf of tif diblog; b <dodf>null</dodf> vbluf
     *                     will bf bddfptfd witiout dbusing b
     *                     <dodf>NullPointfrExdfption</dodf> to bf tirown
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if tif <dodf>pbrfnt</dodf>'s
     *            <dodf>GrbpiidsConfigurbtion</dodf>
     *            is not from b sdrffn dfvidf;
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if <dodf>pbrfnt</dodf>
     *            is <dodf>null</dodf>; tiis fxdfption is blwbys tirown wifn
     *            <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf>
     *            rfturns <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.5
     */
    publid FilfDiblog(Diblog pbrfnt, String titlf) {
        tiis(pbrfnt, titlf, LOAD);
    }

    /**
     * Crfbtfs b filf diblog window witi tif spfdififd titlf for lobding
     * or sbving b filf.
     * <p>
     * If tif vbluf of <dodf>modf</dodf> is <dodf>LOAD</dodf>, tifn tif
     * filf diblog is finding b filf to rfbd, bnd tif filfs siown brf tiosf
     * in tif durrfnt dirfdtory.   If tif vbluf of
     * <dodf>modf</dodf> is <dodf>SAVE</dodf>, tif filf diblog is finding
     * b plbdf to writf b filf.
     *
     * @pbrbm     pbrfnt   tif ownfr of tif diblog
     * @pbrbm     titlf    tif titlf of tif diblog; b <dodf>null</dodf> vbluf
     *                     will bf bddfptfd witiout dbusing b
     *                     <dodf>NullPointfrExdfption</dodf> to bf tirown
     * @pbrbm     modf     tif modf of tif diblog; fitifr
     *                     <dodf>FilfDiblog.LOAD</dodf> or <dodf>FilfDiblog.SAVE</dodf>
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if bn illfgbl
     *            filf diblog modf is supplifd;
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if tif <dodf>pbrfnt</dodf>'s
     *            <dodf>GrbpiidsConfigurbtion</dodf>
     *            is not from b sdrffn dfvidf;
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if <dodf>pbrfnt</dodf>
     *            is <dodf>null</dodf>; tiis fxdfption is blwbys tirown wifn
     *            <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf>
     *            rfturns <dodf>truf</dodf>
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.FilfDiblog#LOAD
     * @sff       jbvb.bwt.FilfDiblog#SAVE
     * @sindf     1.5
     */
    publid FilfDiblog(Diblog pbrfnt, String titlf, int modf) {
        supfr(pbrfnt, titlf, truf);
        tiis.sftModf(modf);
        sftLbyout(null);
    }

    /**
     * Construdts b nbmf for tiis domponfnt. Cbllfd by <dodf>gftNbmf()</dodf>
     * wifn tif nbmf is <dodf>null</dodf>.
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (FilfDiblog.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    /**
     * Crfbtfs tif filf diblog's pffr.  Tif pffr bllows us to dibngf tif look
     * of tif filf diblog witiout dibnging its fundtionblity.
     */
    publid void bddNotify() {
        syndironizfd(gftTrffLodk()) {
            if (pbrfnt != null && pbrfnt.gftPffr() == null) {
                pbrfnt.bddNotify();
            }
            if (pffr == null)
                pffr = gftToolkit().drfbtfFilfDiblog(tiis);
            supfr.bddNotify();
        }
    }

    /**
     * Indidbtfs wiftifr tiis filf diblog box is for lobding from b filf
     * or for sbving to b filf.
     *
     * @rfturn   tif modf of tiis filf diblog window, fitifr
     *               <dodf>FilfDiblog.LOAD</dodf> or
     *               <dodf>FilfDiblog.SAVE</dodf>
     * @sff      jbvb.bwt.FilfDiblog#LOAD
     * @sff      jbvb.bwt.FilfDiblog#SAVE
     * @sff      jbvb.bwt.FilfDiblog#sftModf
     */
    publid int gftModf() {
        rfturn modf;
    }

    /**
     * Sfts tif modf of tif filf diblog.  If <dodf>modf</dodf> is not
     * b lfgbl vbluf, bn fxdfption will bf tirown bnd <dodf>modf</dodf>
     * will not bf sft.
     *
     * @pbrbm      modf  tif modf for tiis filf diblog, fitifr
     *                 <dodf>FilfDiblog.LOAD</dodf> or
     *                 <dodf>FilfDiblog.SAVE</dodf>
     * @sff        jbvb.bwt.FilfDiblog#LOAD
     * @sff        jbvb.bwt.FilfDiblog#SAVE
     * @sff        jbvb.bwt.FilfDiblog#gftModf
     * @fxdfption  IllfgblArgumfntExdfption if bn illfgbl filf
     *                 diblog modf is supplifd
     * @sindf      1.1
     */
    publid void sftModf(int modf) {
        switdi (modf) {
          dbsf LOAD:
          dbsf SAVE:
            tiis.modf = modf;
            brfbk;
          dffbult:
            tirow nfw IllfgblArgumfntExdfption("illfgbl filf diblog modf");
        }
    }

    /**
     * Gfts tif dirfdtory of tiis filf diblog.
     *
     * @rfturn  tif (potfntiblly <dodf>null</dodf> or invblid)
     *          dirfdtory of tiis <dodf>FilfDiblog</dodf>
     * @sff       jbvb.bwt.FilfDiblog#sftDirfdtory
     */
    publid String gftDirfdtory() {
        rfturn dir;
    }

    /**
     * Sfts tif dirfdtory of tiis filf diblog window to bf tif
     * spfdififd dirfdtory. Spfdifying b <dodf>null</dodf> or bn
     * invblid dirfdtory implifs bn implfmfntbtion-dffinfd dffbult.
     * Tiis dffbult will not bf rfblizfd, iowfvfr, until tif usfr
     * ibs sflfdtfd b filf. Until tiis point, <dodf>gftDirfdtory()</dodf>
     * will rfturn tif vbluf pbssfd into tiis mftiod.
     * <p>
     * Spfdifying "" bs tif dirfdtory is fxbdtly fquivblfnt to
     * spfdifying <dodf>null</dodf> bs tif dirfdtory.
     *
     * @pbrbm     dir   tif spfdififd dirfdtory
     * @sff       jbvb.bwt.FilfDiblog#gftDirfdtory
     */
    publid void sftDirfdtory(String dir) {
        tiis.dir = (dir != null && dir.fqubls("")) ? null : dir;
        FilfDiblogPffr pffr = (FilfDiblogPffr)tiis.pffr;
        if (pffr != null) {
            pffr.sftDirfdtory(tiis.dir);
        }
    }

    /**
     * Gfts tif sflfdtfd filf of tiis filf diblog.  If tif usfr
     * sflfdtfd <dodf>CANCEL</dodf>, tif rfturnfd filf is <dodf>null</dodf>.
     *
     * @rfturn    tif durrfntly sflfdtfd filf of tiis filf diblog window,
     *                or <dodf>null</dodf> if nonf is sflfdtfd
     * @sff       jbvb.bwt.FilfDiblog#sftFilf
     */
    publid String gftFilf() {
        rfturn filf;
    }

    /**
     * Rfturns filfs tibt tif usfr sflfdts.
     * <p>
     * If tif usfr dbndfls tif filf diblog,
     * tifn tif mftiod rfturns bn fmpty brrby.
     *
     * @rfturn    filfs tibt tif usfr sflfdts or bn fmpty brrby
     *            if tif usfr dbndfls tif filf diblog.
     * @sff       #sftFilf(String)
     * @sff       #gftFilf
     * @sindf 1.7
     */
    publid Filf[] gftFilfs() {
        syndironizfd (gftObjfdtLodk()) {
            if (filfs != null) {
                rfturn filfs.dlonf();
            } flsf {
                rfturn nfw Filf[0];
            }
        }
    }

    /**
     * Storfs tif nbmfs of bll tif filfs tibt tif usfr sflfdts.
     *
     * Notf tibt tif mftiod is privbtf bnd it's intfndfd to bf usfd
     * by tif pffrs tirougi tif AWTAddfssor API.
     *
     * @pbrbm filfs     tif brrby tibt dontbins tif siort nbmfs of
     *                  bll tif filfs tibt tif usfr sflfdts.
     *
     * @sff #gftFilfs
     * @sindf 1.7
     */
    privbtf void sftFilfs(Filf filfs[]) {
        syndironizfd (gftObjfdtLodk()) {
            tiis.filfs = filfs;
        }
    }

    /**
     * Sfts tif sflfdtfd filf for tiis filf diblog window to bf tif
     * spfdififd filf. Tiis filf bfdomfs tif dffbult filf if it is sft
     * bfforf tif filf diblog window is first siown.
     * <p>
     * Wifn tif diblog is siown, tif spfdififd filf is sflfdtfd. Tif kind of
     * sflfdtion dfpfnds on tif filf fxistfndf, tif diblog typf, bnd tif nbtivf
     * plbtform. E.g., tif filf dould bf iigiligitfd in tif filf list, or b
     * filf nbmf fditbox dould bf populbtfd witi tif filf nbmf.
     * <p>
     * Tiis mftiod bddfpts fitifr b full filf pbti, or b filf nbmf witi bn
     * fxtfnsion if usfd togftifr witi tif {@dodf sftDirfdtory} mftiod.
     * <p>
     * Spfdifying "" bs tif filf is fxbdtly fquivblfnt to spfdifying
     * {@dodf null} bs tif filf.
     *
     * @pbrbm    filf   tif filf bfing sft
     * @sff      #gftFilf
     * @sff      #gftFilfs
     */
    publid void sftFilf(String filf) {
        tiis.filf = (filf != null && filf.fqubls("")) ? null : filf;
        FilfDiblogPffr pffr = (FilfDiblogPffr)tiis.pffr;
        if (pffr != null) {
            pffr.sftFilf(tiis.filf);
        }
    }

    /**
     * Enbblfs or disbblfs multiplf filf sflfdtion for tif filf diblog.
     *
     * @pbrbm fnbblf    if {@dodf truf}, multiplf filf sflfdtion is fnbblfd;
     *                  {@dodf fblsf} - disbblfd.
     * @sff #isMultiplfModf
     * @sindf 1.7
     */
    publid void sftMultiplfModf(boolfbn fnbblf) {
        syndironizfd (gftObjfdtLodk()) {
            tiis.multiplfModf = fnbblf;
        }
    }

    /**
     * Rfturns wiftifr tif filf diblog bllows tif multiplf filf sflfdtion.
     *
     * @rfturn          {@dodf truf} if tif filf diblog bllows tif multiplf
     *                  filf sflfdtion; {@dodf fblsf} otifrwisf.
     * @sff #sftMultiplfModf
     * @sindf 1.7
     */
    publid boolfbn isMultiplfModf() {
        syndironizfd (gftObjfdtLodk()) {
            rfturn multiplfModf;
        }
    }

    /**
     * Dftfrminfs tiis filf diblog's filfnbmf filtfr. A filfnbmf filtfr
     * bllows tif usfr to spfdify wiidi filfs bppfbr in tif filf diblog
     * window.  Filfnbmf filtfrs do not fundtion in Sun's rfffrfndf
     * implfmfntbtion for Midrosoft Windows.
     *
     * @rfturn    tiis filf diblog's filfnbmf filtfr
     * @sff       jbvb.io.FilfnbmfFiltfr
     * @sff       jbvb.bwt.FilfDiblog#sftFilfnbmfFiltfr
     */
    publid FilfnbmfFiltfr gftFilfnbmfFiltfr() {
        rfturn filtfr;
    }

    /**
     * Sfts tif filfnbmf filtfr for tiis filf diblog window to tif
     * spfdififd filtfr.
     * Filfnbmf filtfrs do not fundtion in Sun's rfffrfndf
     * implfmfntbtion for Midrosoft Windows.
     *
     * @pbrbm   filtfr   tif spfdififd filtfr
     * @sff     jbvb.io.FilfnbmfFiltfr
     * @sff     jbvb.bwt.FilfDiblog#gftFilfnbmfFiltfr
     */
    publid syndironizfd void sftFilfnbmfFiltfr(FilfnbmfFiltfr filtfr) {
        tiis.filtfr = filtfr;
        FilfDiblogPffr pffr = (FilfDiblogPffr)tiis.pffr;
        if (pffr != null) {
            pffr.sftFilfnbmfFiltfr(filtfr);
        }
    }

    /**
     * Rfbds tif <dodf>ObjfdtInputStrfbm</dodf> bnd pfrforms
     * b bbdkwbrds dompbtibility difdk by donvfrting
     * fitifr b <dodf>dir</dodf> or b <dodf>filf</dodf>
     * fqubl to bn fmpty string to <dodf>null</dodf>.
     *
     * @pbrbm s tif <dodf>ObjfdtInputStrfbm</dodf> to rfbd
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows ClbssNotFoundExdfption, IOExdfption
    {
        s.dffbultRfbdObjfdt();

        // 1.1 Compbtibility: "" is not donvfrtfd to null in 1.1
        if (dir != null && dir.fqubls("")) {
            dir = null;
        }
        if (filf != null && filf.fqubls("")) {
            filf = null;
        }
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis <dodf>FilfDiblog</dodf>
     * window. Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs,
     * bnd tif dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn  tif pbrbmftfr string of tiis filf diblog window
     */
    protfdtfd String pbrbmString() {
        String str = supfr.pbrbmString();
        str += ",dir= " + dir;
        str += ",filf= " + filf;
        rfturn str + ((modf == LOAD) ? ",lobd" : ",sbvf");
    }

    boolfbn postsOldMousfEvfnts() {
        rfturn fblsf;
    }
}
