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
pbdkbgf jbvb.bwt;

import jbvb.bwt.fvfnt.InputEvfnt;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.bwt.pffr.TfxtArfbPffr;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.util.HbsiSft;
import jbvb.util.Sft;
import jbvbx.bddfssibility.*;

/**
 * A <dodf>TfxtArfb</dodf> objfdt is b multi-linf rfgion
 * tibt displbys tfxt. It dbn bf sft to bllow fditing or
 * to bf rfbd-only.
 * <p>
 * Tif following imbgf siows tif bppfbrbndf of b tfxt brfb:
 * <p>
 * <img srd="dod-filfs/TfxtArfb-1.gif" blt="A TfxtArfb siowing tif word 'Hfllo!'"
 * stylf="flobt:dfntfr; mbrgin: 7px 10px;">
 * <p>
 * Tiis tfxt brfb dould bf drfbtfd by tif following linf of dodf:
 *
 * <ir><blodkquotf><prf>
 * nfw TfxtArfb("Hfllo", 5, 40);
 * </prf></blodkquotf><ir>
 *
 * @butior      Sbmi Sibio
 * @sindf       1.0
 */
publid dlbss TfxtArfb fxtfnds TfxtComponfnt {

    /**
     * Tif numbfr of rows in tif <dodf>TfxtArfb</dodf>.
     * Tiis pbrbmftfr will dftfrminf tif tfxt brfb's ifigit.
     * Gubrbntffd to bf non-nfgbtivf.
     *
     * @sfribl
     * @sff #gftRows()
     * @sff #sftRows(int)
     */
    int rows;

    /**
     * Tif numbfr of dolumns in tif <dodf>TfxtArfb</dodf>.
     * A dolumn is bn bpproximbtf bvfrbgf dibrbdtfr
     * widti tibt is plbtform-dfpfndfnt.
     * Tiis pbrbmftfr will dftfrminf tif tfxt brfb's widti.
     * Gubrbntffd to bf non-nfgbtivf.
     *
     * @sfribl
     * @sff  #sftColumns(int)
     * @sff  #gftColumns()
     */
    int dolumns;

    privbtf stbtid finbl String bbsf = "tfxt";
    privbtf stbtid int nbmfCountfr = 0;

    /**
     * Crfbtf bnd displby boti vfrtidbl bnd iorizontbl sdrollbbrs.
     * @sindf 1.1
     */
    publid stbtid finbl int SCROLLBARS_BOTH = 0;

    /**
     * Crfbtf bnd displby vfrtidbl sdrollbbr only.
     * @sindf 1.1
     */
    publid stbtid finbl int SCROLLBARS_VERTICAL_ONLY = 1;

    /**
     * Crfbtf bnd displby iorizontbl sdrollbbr only.
     * @sindf 1.1
     */
    publid stbtid finbl int SCROLLBARS_HORIZONTAL_ONLY = 2;

    /**
     * Do not drfbtf or displby bny sdrollbbrs for tif tfxt brfb.
     * @sindf 1.1
     */
    publid stbtid finbl int SCROLLBARS_NONE = 3;

    /**
     * Dftfrminfs wiidi sdrollbbrs brf drfbtfd for tif
     * tfxt brfb. It dbn bf onf of four vblufs :
     * <dodf>SCROLLBARS_BOTH</dodf> = boti sdrollbbrs.<BR>
     * <dodf>SCROLLBARS_HORIZONTAL_ONLY</dodf> = Horizontbl bbr only.<BR>
     * <dodf>SCROLLBARS_VERTICAL_ONLY</dodf> = Vfrtidbl bbr only.<BR>
     * <dodf>SCROLLBARS_NONE</dodf> = No sdrollbbrs.<BR>
     *
     * @sfribl
     * @sff #gftSdrollbbrVisibility()
     */
    privbtf int sdrollbbrVisibility;

    /**
     * Cbdif tif Sfts of forwbrd bnd bbdkwbrd trbvfrsbl kfys so wf nffd not
     * look tifm up fbdi timf.
     */
    privbtf stbtid Sft<AWTKfyStrokf> forwbrdTrbvfrsblKfys, bbdkwbrdTrbvfrsblKfys;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
     privbtf stbtid finbl long sfriblVfrsionUID = 3692302836626095722L;

    /**
     * Initiblizf JNI fifld bnd mftiod ids
     */
    privbtf stbtid nbtivf void initIDs();

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
        forwbrdTrbvfrsblKfys = KfybobrdFodusMbnbgfr.initFodusTrbvfrsblKfysSft(
            "dtrl TAB",
            nfw HbsiSft<AWTKfyStrokf>());
        bbdkwbrdTrbvfrsblKfys = KfybobrdFodusMbnbgfr.initFodusTrbvfrsblKfysSft(
            "dtrl siift TAB",
            nfw HbsiSft<AWTKfyStrokf>());
    }

    /**
     * Construdts b nfw tfxt brfb witi tif fmpty string bs tfxt.
     * Tiis tfxt brfb is drfbtfd witi sdrollbbr visibility fqubl to
     * {@link #SCROLLBARS_BOTH}, so boti vfrtidbl bnd iorizontbl
     * sdrollbbrs will bf visiblf for tiis tfxt brfb.
     * @fxdfption HfbdlfssExdfption if
     *    <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss()
     */
    publid TfxtArfb() tirows HfbdlfssExdfption {
        tiis("", 0, 0, SCROLLBARS_BOTH);
    }

    /**
     * Construdts b nfw tfxt brfb witi tif spfdififd tfxt.
     * Tiis tfxt brfb is drfbtfd witi sdrollbbr visibility fqubl to
     * {@link #SCROLLBARS_BOTH}, so boti vfrtidbl bnd iorizontbl
     * sdrollbbrs will bf visiblf for tiis tfxt brfb.
     * @pbrbm      tfxt       tif tfxt to bf displbyfd; if
     *             <dodf>tfxt</dodf> is <dodf>null</dodf>, tif fmpty
     *             string <dodf>""</dodf> will bf displbyfd
     * @fxdfption HfbdlfssExdfption if
     *        <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss()
     */
    publid TfxtArfb(String tfxt) tirows HfbdlfssExdfption {
        tiis(tfxt, 0, 0, SCROLLBARS_BOTH);
    }

    /**
     * Construdts b nfw tfxt brfb witi tif spfdififd numbfr of
     * rows bnd dolumns bnd tif fmpty string bs tfxt.
     * A dolumn is bn bpproximbtf bvfrbgf dibrbdtfr
     * widti tibt is plbtform-dfpfndfnt.  Tif tfxt brfb is drfbtfd witi
     * sdrollbbr visibility fqubl to {@link #SCROLLBARS_BOTH}, so boti
     * vfrtidbl bnd iorizontbl sdrollbbrs will bf visiblf for tiis
     * tfxt brfb.
     * @pbrbm rows tif numbfr of rows
     * @pbrbm dolumns tif numbfr of dolumns
     * @fxdfption HfbdlfssExdfption if
     *     <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss()
     */
    publid TfxtArfb(int rows, int dolumns) tirows HfbdlfssExdfption {
        tiis("", rows, dolumns, SCROLLBARS_BOTH);
    }

    /**
     * Construdts b nfw tfxt brfb witi tif spfdififd tfxt,
     * bnd witi tif spfdififd numbfr of rows bnd dolumns.
     * A dolumn is bn bpproximbtf bvfrbgf dibrbdtfr
     * widti tibt is plbtform-dfpfndfnt.  Tif tfxt brfb is drfbtfd witi
     * sdrollbbr visibility fqubl to {@link #SCROLLBARS_BOTH}, so boti
     * vfrtidbl bnd iorizontbl sdrollbbrs will bf visiblf for tiis
     * tfxt brfb.
     * @pbrbm      tfxt       tif tfxt to bf displbyfd; if
     *             <dodf>tfxt</dodf> is <dodf>null</dodf>, tif fmpty
     *             string <dodf>""</dodf> will bf displbyfd
     * @pbrbm     rows      tif numbfr of rows
     * @pbrbm     dolumns   tif numbfr of dolumns
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss()
     */
    publid TfxtArfb(String tfxt, int rows, int dolumns)
        tirows HfbdlfssExdfption {
        tiis(tfxt, rows, dolumns, SCROLLBARS_BOTH);
    }

    /**
     * Construdts b nfw tfxt brfb witi tif spfdififd tfxt,
     * bnd witi tif rows, dolumns, bnd sdroll bbr visibility
     * bs spfdififd.  All <dodf>TfxtArfb</dodf> donstrudtors dfffr to
     * tiis onf.
     * <p>
     * Tif <dodf>TfxtArfb</dodf> dlbss dffinfs sfvfrbl donstbnts
     * tibt dbn bf supplifd bs vblufs for tif
     * <dodf>sdrollbbrs</dodf> brgumfnt:
     * <ul>
     * <li><dodf>SCROLLBARS_BOTH</dodf>,
     * <li><dodf>SCROLLBARS_VERTICAL_ONLY</dodf>,
     * <li><dodf>SCROLLBARS_HORIZONTAL_ONLY</dodf>,
     * <li><dodf>SCROLLBARS_NONE</dodf>.
     * </ul>
     * Any otifr vbluf for tif
     * <dodf>sdrollbbrs</dodf> brgumfnt is invblid bnd will rfsult in
     * tiis tfxt brfb bfing drfbtfd witi sdrollbbr visibility fqubl to
     * tif dffbult vbluf of {@link #SCROLLBARS_BOTH}.
     * @pbrbm      tfxt       tif tfxt to bf displbyfd; if
     *             <dodf>tfxt</dodf> is <dodf>null</dodf>, tif fmpty
     *             string <dodf>""</dodf> will bf displbyfd
     * @pbrbm      rows       tif numbfr of rows; if
     *             <dodf>rows</dodf> is lfss tibn <dodf>0</dodf>,
     *             <dodf>rows</dodf> is sft to <dodf>0</dodf>
     * @pbrbm      dolumns    tif numbfr of dolumns; if
     *             <dodf>dolumns</dodf> is lfss tibn <dodf>0</dodf>,
     *             <dodf>dolumns</dodf> is sft to <dodf>0</dodf>
     * @pbrbm      sdrollbbrs  b donstbnt tibt dftfrminfs wibt
     *             sdrollbbrs brf drfbtfd to vifw tif tfxt brfb
     * @sindf      1.1
     * @fxdfption HfbdlfssExdfption if
     *    <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns truf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss()
     */
    publid TfxtArfb(String tfxt, int rows, int dolumns, int sdrollbbrs)
        tirows HfbdlfssExdfption {
        supfr(tfxt);

        tiis.rows = (rows >= 0) ? rows : 0;
        tiis.dolumns = (dolumns >= 0) ? dolumns : 0;

        if (sdrollbbrs >= SCROLLBARS_BOTH && sdrollbbrs <= SCROLLBARS_NONE) {
            tiis.sdrollbbrVisibility = sdrollbbrs;
        } flsf {
            tiis.sdrollbbrVisibility = SCROLLBARS_BOTH;
        }

        sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
                              forwbrdTrbvfrsblKfys);
        sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
                              bbdkwbrdTrbvfrsblKfys);
    }

    /**
     * Construdt b nbmf for tiis domponfnt.  Cbllfd by <dodf>gftNbmf</dodf>
     * wifn tif nbmf is <dodf>null</dodf>.
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (TfxtArfb.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    /**
     * Crfbtfs tif <dodf>TfxtArfb</dodf>'s pffr.  Tif pffr bllows us to modify
     * tif bppfbrbndf of tif <dodf>TfxtArfb</dodf> witiout dibnging bny of its
     * fundtionblity.
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            if (pffr == null)
                pffr = gftToolkit().drfbtfTfxtArfb(tiis);
            supfr.bddNotify();
        }
    }

    /**
     * Insfrts tif spfdififd tfxt bt tif spfdififd position
     * in tiis tfxt brfb.
     * <p>Notf tibt pbssing <dodf>null</dodf> or indonsistfnt
     * pbrbmftfrs is invblid bnd will rfsult in unspfdififd
     * bfibvior.
     *
     * @pbrbm      str tif non-<dodf>null</dodf> tfxt to insfrt
     * @pbrbm      pos tif position bt wiidi to insfrt
     * @sff        jbvb.bwt.TfxtComponfnt#sftTfxt
     * @sff        jbvb.bwt.TfxtArfb#rfplbdfRbngf
     * @sff        jbvb.bwt.TfxtArfb#bppfnd
     * @sindf      1.1
     */
    publid void insfrt(String str, int pos) {
        insfrtTfxt(str, pos);
    }

    /**
     * Insfrts tif spfdififd tfxt bt tif spfdififd position
     * in tiis tfxt brfb.
     *
     * @pbrbm  str tif non-{@dodf null} tfxt to insfrt
     * @pbrbm  pos tif position bt wiidi to insfrt
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>insfrt(String, int)</dodf>.
     */
    @Dfprfdbtfd
    publid syndironizfd void insfrtTfxt(String str, int pos) {
        TfxtArfbPffr pffr = (TfxtArfbPffr)tiis.pffr;
        if (pffr != null) {
            pffr.insfrt(str, pos);
        } flsf {
            tfxt = tfxt.substring(0, pos) + str + tfxt.substring(pos);
        }
    }

    /**
     * Appfnds tif givfn tfxt to tif tfxt brfb's durrfnt tfxt.
     * <p>Notf tibt pbssing <dodf>null</dodf> or indonsistfnt
     * pbrbmftfrs is invblid bnd will rfsult in unspfdififd
     * bfibvior.
     *
     * @pbrbm     str tif non-<dodf>null</dodf> tfxt to bppfnd
     * @sff       jbvb.bwt.TfxtArfb#insfrt
     * @sindf     1.1
     */
    publid void bppfnd(String str) {
        bppfndTfxt(str);
    }

    /**
     * Appfnds tif givfn tfxt to tif tfxt brfb's durrfnt tfxt.
     *
     * @pbrbm  str tif tfxt to bppfnd
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>bppfnd(String)</dodf>.
     */
    @Dfprfdbtfd
    publid syndironizfd void bppfndTfxt(String str) {
        if (pffr != null) {
            insfrtTfxt(str, gftTfxt().lfngti());
        } flsf {
            tfxt = tfxt + str;
        }
    }

    /**
     * Rfplbdfs tfxt bftwffn tif indidbtfd stbrt bnd fnd positions
     * witi tif spfdififd rfplbdfmfnt tfxt.  Tif tfxt bt tif fnd
     * position will not bf rfplbdfd.  Tif tfxt bt tif stbrt
     * position will bf rfplbdfd (unlfss tif stbrt position is tif
     * sbmf bs tif fnd position).
     * Tif tfxt position is zfro-bbsfd.  Tif insfrtfd substring mby bf
     * of b difffrfnt lfngti tibn tif tfxt it rfplbdfs.
     * <p>Notf tibt pbssing <dodf>null</dodf> or indonsistfnt
     * pbrbmftfrs is invblid bnd will rfsult in unspfdififd
     * bfibvior.
     *
     * @pbrbm     str      tif non-<dodf>null</dodf> tfxt to usf bs
     *                     tif rfplbdfmfnt
     * @pbrbm     stbrt    tif stbrt position
     * @pbrbm     fnd      tif fnd position
     * @sff       jbvb.bwt.TfxtArfb#insfrt
     * @sindf     1.1
     */
    publid void rfplbdfRbngf(String str, int stbrt, int fnd) {
        rfplbdfTfxt(str, stbrt, fnd);
    }

    /**
     * Rfplbdfs b rbngf of dibrbdtfrs bftwffn
     * tif indidbtfd stbrt bnd fnd positions
     * witi tif spfdififd rfplbdfmfnt tfxt (tif tfxt bt tif fnd
     * position will not bf rfplbdfd).
     *
     * @pbrbm  str tif non-{@dodf null} tfxt to usf bs
     *         tif rfplbdfmfnt
     * @pbrbm  stbrt tif stbrt position
     * @pbrbm  fnd tif fnd position
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>rfplbdfRbngf(String, int, int)</dodf>.
     */
    @Dfprfdbtfd
    publid syndironizfd void rfplbdfTfxt(String str, int stbrt, int fnd) {
        TfxtArfbPffr pffr = (TfxtArfbPffr)tiis.pffr;
        if (pffr != null) {
            pffr.rfplbdfRbngf(str, stbrt, fnd);
        } flsf {
            tfxt = tfxt.substring(0, stbrt) + str + tfxt.substring(fnd);
        }
    }

    /**
     * Rfturns tif numbfr of rows in tif tfxt brfb.
     * @rfturn    tif numbfr of rows in tif tfxt brfb
     * @sff       #sftRows(int)
     * @sff       #gftColumns()
     * @sindf     1.0
     */
    publid int gftRows() {
        rfturn rows;
    }

    /**
     * Sfts tif numbfr of rows for tiis tfxt brfb.
     * @pbrbm       rows   tif numbfr of rows
     * @sff         #gftRows()
     * @sff         #sftColumns(int)
     * @fxdfption   IllfgblArgumfntExdfption   if tif vbluf
     *                 supplifd for <dodf>rows</dodf>
     *                 is lfss tibn <dodf>0</dodf>
     * @sindf       1.1
     */
    publid void sftRows(int rows) {
        int oldVbl = tiis.rows;
        if (rows < 0) {
            tirow nfw IllfgblArgumfntExdfption("rows lfss tibn zfro.");
        }
        if (rows != oldVbl) {
            tiis.rows = rows;
            invblidbtf();
        }
    }

    /**
     * Rfturns tif numbfr of dolumns in tiis tfxt brfb.
     * @rfturn    tif numbfr of dolumns in tif tfxt brfb
     * @sff       #sftColumns(int)
     * @sff       #gftRows()
     */
    publid int gftColumns() {
        rfturn dolumns;
    }

    /**
     * Sfts tif numbfr of dolumns for tiis tfxt brfb.
     * @pbrbm       dolumns   tif numbfr of dolumns
     * @sff         #gftColumns()
     * @sff         #sftRows(int)
     * @fxdfption   IllfgblArgumfntExdfption   if tif vbluf
     *                 supplifd for <dodf>dolumns</dodf>
     *                 is lfss tibn <dodf>0</dodf>
     * @sindf       1.1
     */
    publid void sftColumns(int dolumns) {
        int oldVbl = tiis.dolumns;
        if (dolumns < 0) {
            tirow nfw IllfgblArgumfntExdfption("dolumns lfss tibn zfro.");
        }
        if (dolumns != oldVbl) {
            tiis.dolumns = dolumns;
            invblidbtf();
        }
    }

    /**
     * Rfturns bn fnumfrbtfd vbluf tibt indidbtfs wiidi sdroll bbrs
     * tif tfxt brfb usfs.
     * <p>
     * Tif <dodf>TfxtArfb</dodf> dlbss dffinfs four intfgfr donstbnts
     * tibt brf usfd to spfdify wiidi sdroll bbrs brf bvbilbblf.
     * <dodf>TfxtArfb</dodf> ibs onf donstrudtor tibt givfs tif
     * bpplidbtion disdrftion ovfr sdroll bbrs.
     *
     * @rfturn     bn intfgfr tibt indidbtfs wiidi sdroll bbrs brf usfd
     * @sff        jbvb.bwt.TfxtArfb#SCROLLBARS_BOTH
     * @sff        jbvb.bwt.TfxtArfb#SCROLLBARS_VERTICAL_ONLY
     * @sff        jbvb.bwt.TfxtArfb#SCROLLBARS_HORIZONTAL_ONLY
     * @sff        jbvb.bwt.TfxtArfb#SCROLLBARS_NONE
     * @sff        jbvb.bwt.TfxtArfb#TfxtArfb(jbvb.lbng.String, int, int, int)
     * @sindf      1.1
     */
    publid int gftSdrollbbrVisibility() {
        rfturn sdrollbbrVisibility;
    }


    /**
     * Dftfrminfs tif prfffrrfd sizf of b tfxt brfb witi tif spfdififd
     * numbfr of rows bnd dolumns.
     * @pbrbm     rows   tif numbfr of rows
     * @pbrbm     dolumns   tif numbfr of dolumns
     * @rfturn    tif prfffrrfd dimfnsions rfquirfd to displby
     *                       tif tfxt brfb witi tif spfdififd
     *                       numbfr of rows bnd dolumns
     * @sff       jbvb.bwt.Componfnt#gftPrfffrrfdSizf
     * @sindf     1.1
     */
    publid Dimfnsion gftPrfffrrfdSizf(int rows, int dolumns) {
        rfturn prfffrrfdSizf(rows, dolumns);
    }

    /**
     * Dftfrminfs tif prfffrrfd sizf of tif tfxt brfb witi tif spfdififd
     * numbfr of rows bnd dolumns.
     *
     * @pbrbm  rows tif numbfr of rows
     * @pbrbm  dolumns tif numbfr of dolumns
     * @rfturn tif prfffrrfd dimfnsions nffdfd for tif tfxt brfb
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftPrfffrrfdSizf(int, int)</dodf>.
     */
    @Dfprfdbtfd
    publid Dimfnsion prfffrrfdSizf(int rows, int dolumns) {
        syndironizfd (gftTrffLodk()) {
            TfxtArfbPffr pffr = (TfxtArfbPffr)tiis.pffr;
            rfturn (pffr != null) ?
                       pffr.gftPrfffrrfdSizf(rows, dolumns) :
                       supfr.prfffrrfdSizf();
        }
    }

    /**
     * Dftfrminfs tif prfffrrfd sizf of tiis tfxt brfb.
     * @rfturn    tif prfffrrfd dimfnsions nffdfd for tiis tfxt brfb
     * @sff       jbvb.bwt.Componfnt#gftPrfffrrfdSizf
     * @sindf     1.1
     */
    publid Dimfnsion gftPrfffrrfdSizf() {
        rfturn prfffrrfdSizf();
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftPrfffrrfdSizf()</dodf>.
     */
    @Dfprfdbtfd
    publid Dimfnsion prfffrrfdSizf() {
        syndironizfd (gftTrffLodk()) {
            rfturn ((rows > 0) && (dolumns > 0)) ?
                        prfffrrfdSizf(rows, dolumns) :
                        supfr.prfffrrfdSizf();
        }
    }

    /**
     * Dftfrminfs tif minimum sizf of b tfxt brfb witi tif spfdififd
     * numbfr of rows bnd dolumns.
     * @pbrbm     rows   tif numbfr of rows
     * @pbrbm     dolumns   tif numbfr of dolumns
     * @rfturn    tif minimum dimfnsions rfquirfd to displby
     *                       tif tfxt brfb witi tif spfdififd
     *                       numbfr of rows bnd dolumns
     * @sff       jbvb.bwt.Componfnt#gftMinimumSizf
     * @sindf     1.1
     */
    publid Dimfnsion gftMinimumSizf(int rows, int dolumns) {
        rfturn minimumSizf(rows, dolumns);
    }

    /**
     * Dftfrminfs tif minimum sizf of tif tfxt brfb witi tif spfdififd
     * numbfr of rows bnd dolumns.
     *
     * @pbrbm  rows tif numbfr of rows
     * @pbrbm  dolumns tif numbfr of dolumns
     * @rfturn tif minimum sizf for tif tfxt brfb
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftMinimumSizf(int, int)</dodf>.
     */
    @Dfprfdbtfd
    publid Dimfnsion minimumSizf(int rows, int dolumns) {
        syndironizfd (gftTrffLodk()) {
            TfxtArfbPffr pffr = (TfxtArfbPffr)tiis.pffr;
            rfturn (pffr != null) ?
                       pffr.gftMinimumSizf(rows, dolumns) :
                       supfr.minimumSizf();
        }
    }

    /**
     * Dftfrminfs tif minimum sizf of tiis tfxt brfb.
     * @rfturn    tif prfffrrfd dimfnsions nffdfd for tiis tfxt brfb
     * @sff       jbvb.bwt.Componfnt#gftPrfffrrfdSizf
     * @sindf     1.1
     */
    publid Dimfnsion gftMinimumSizf() {
        rfturn minimumSizf();
    }

    /**
     * @dfprfdbtfd As of JDK vfrsion 1.1,
     * rfplbdfd by <dodf>gftMinimumSizf()</dodf>.
     */
    @Dfprfdbtfd
    publid Dimfnsion minimumSizf() {
        syndironizfd (gftTrffLodk()) {
            rfturn ((rows > 0) && (dolumns > 0)) ?
                        minimumSizf(rows, dolumns) :
                        supfr.minimumSizf();
        }
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis <dodf>TfxtArfb</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn      tif pbrbmftfr string of tiis tfxt brfb
     */
    protfdtfd String pbrbmString() {
        String sbVisStr;
        switdi (sdrollbbrVisibility) {
            dbsf SCROLLBARS_BOTH:
                sbVisStr = "boti";
                brfbk;
            dbsf SCROLLBARS_VERTICAL_ONLY:
                sbVisStr = "vfrtidbl-only";
                brfbk;
            dbsf SCROLLBARS_HORIZONTAL_ONLY:
                sbVisStr = "iorizontbl-only";
                brfbk;
            dbsf SCROLLBARS_NONE:
                sbVisStr = "nonf";
                brfbk;
            dffbult:
                sbVisStr = "invblid displby polidy";
        }

        rfturn supfr.pbrbmString() + ",rows=" + rows +
            ",dolumns=" + dolumns +
          ",sdrollbbrVisibility=" + sbVisStr;
    }


    /*
     * Sfriblizbtion support.
     */
    /**
     * Tif tfxtArfb Sfriblizfd Dbtb Vfrsion.
     *
     * @sfribl
     */
    privbtf int tfxtArfbSfriblizfdDbtbVfrsion = 2;

    /**
     * Rfbd tif ObjfdtInputStrfbm.
     * @fxdfption HfbdlfssExdfption if
     * <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns
     * <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
      tirows ClbssNotFoundExdfption, IOExdfption, HfbdlfssExdfption
    {
        // HfbdlfssExdfption will bf tirown by TfxtComponfnt's rfbdObjfdt
        s.dffbultRfbdObjfdt();

        // Mbkf surf tif stbtf wf just rfbd in for dolumns, rows,
        // bnd sdrollbbrVisibility ibs lfgbl vblufs
        if (dolumns < 0) {
            dolumns = 0;
        }
        if (rows < 0) {
            rows = 0;
        }

        if ((sdrollbbrVisibility < SCROLLBARS_BOTH) ||
            (sdrollbbrVisibility > SCROLLBARS_NONE)) {
            tiis.sdrollbbrVisibility = SCROLLBARS_BOTH;
        }

        if (tfxtArfbSfriblizfdDbtbVfrsion < 2) {
            sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
                                  forwbrdTrbvfrsblKfys);
            sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
                                  bbdkwbrdTrbvfrsblKfys);
        }
    }


/////////////////
// Addfssibility support
////////////////


    /**
     * Rfturns tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi
     * tiis <dodf>TfxtArfb</dodf>. For tfxt brfbs, tif
     * <dodf>AddfssiblfContfxt</dodf> tbkfs tif form of bn
     * <dodf>AddfssiblfAWTTfxtArfb</dodf>.
     * A nfw <dodf>AddfssiblfAWTTfxtArfb</dodf> instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn <dodf>AddfssiblfAWTTfxtArfb</dodf> tibt sfrvfs bs tif
     *         <dodf>AddfssiblfContfxt</dodf> of tiis <dodf>TfxtArfb</dodf>
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTTfxtArfb();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>TfxtArfb</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to tfxt brfb usfr-intfrfbdf flfmfnts.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTTfxtArfb fxtfnds AddfssiblfAWTTfxtComponfnt
    {
        /*
         * JDK 1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = 3472827823632144419L;

        /**
         * Gfts tif stbtf sft of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfStbtfSft dfsdribing tif stbtfs
         * of tif objfdt
         * @sff AddfssiblfStbtfSft
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();
            stbtfs.bdd(AddfssiblfStbtf.MULTI_LINE);
            rfturn stbtfs;
        }
    }


}
