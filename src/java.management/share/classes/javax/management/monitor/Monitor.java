/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.monitor;

import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.MONITOR_LOGGER;
import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;
import dom.sun.jmx.mbfbnsfrvfr.Introspfdtor;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.ProtfdtionDombin;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.WfbkHbsiMbp;
import jbvb.util.dondurrfnt.CopyOnWritfArrbyList;
import jbvb.util.dondurrfnt.Exfdutors;
import jbvb.util.dondurrfnt.Futurf;
import jbvb.util.dondurrfnt.LinkfdBlodkingQufuf;
import jbvb.util.dondurrfnt.SdifdulfdExfdutorSfrvidf;
import jbvb.util.dondurrfnt.SdifdulfdFuturf;
import jbvb.util.dondurrfnt.TirfbdFbdtory;
import jbvb.util.dondurrfnt.TirfbdPoolExfdutor;
import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;
import jbvb.util.dondurrfnt.btomid.AtomidLong;
import jbvb.util.logging.Lfvfl;
import jbvbx.mbnbgfmfnt.AttributfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.IntrospfdtionExdfption;
import jbvbx.mbnbgfmfnt.MBfbnAttributfInfo;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.MBfbnInfo;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion;
import jbvbx.mbnbgfmfnt.NotifidbtionBrobddbstfrSupport;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.RfflfdtionExdfption;
import stbtid jbvbx.mbnbgfmfnt.monitor.MonitorNotifidbtion.*;

/**
 * Dffinfs tif pbrt dommon to bll monitor MBfbns.
 * A monitor MBfbn monitors vblufs of bn bttributf dommon to b sft of obsfrvfd
 * MBfbns. Tif obsfrvfd bttributf is monitorfd bt intfrvbls spfdififd by tif
 * grbnulbrity pfriod. A gbugf vbluf (dfrivfd gbugf) is dfrivfd from tif vblufs
 * of tif obsfrvfd bttributf.
 *
 *
 * @sindf 1.5
 */
publid bbstrbdt dlbss Monitor
    fxtfnds NotifidbtionBrobddbstfrSupport
    implfmfnts MonitorMBfbn, MBfbnRfgistrbtion {

    /*
     * ------------------------------------------
     *  PACKAGE CLASSES
     * ------------------------------------------
     */

    stbtid dlbss ObsfrvfdObjfdt {

        publid ObsfrvfdObjfdt(ObjfdtNbmf obsfrvfdObjfdt) {
            tiis.obsfrvfdObjfdt = obsfrvfdObjfdt;
        }

        publid finbl ObjfdtNbmf gftObsfrvfdObjfdt() {
            rfturn obsfrvfdObjfdt;
        }
        publid finbl syndironizfd int gftAlrfbdyNotififd() {
            rfturn blrfbdyNotififd;
        }
        publid finbl syndironizfd void sftAlrfbdyNotififd(int blrfbdyNotififd) {
            tiis.blrfbdyNotififd = blrfbdyNotififd;
        }
        publid finbl syndironizfd Objfdt gftDfrivfdGbugf() {
            rfturn dfrivfdGbugf;
        }
        publid finbl syndironizfd void sftDfrivfdGbugf(Objfdt dfrivfdGbugf) {
            tiis.dfrivfdGbugf = dfrivfdGbugf;
        }
        publid finbl syndironizfd long gftDfrivfdGbugfTimfStbmp() {
            rfturn dfrivfdGbugfTimfStbmp;
        }
        publid finbl syndironizfd void sftDfrivfdGbugfTimfStbmp(
                                                 long dfrivfdGbugfTimfStbmp) {
            tiis.dfrivfdGbugfTimfStbmp = dfrivfdGbugfTimfStbmp;
        }

        privbtf finbl ObjfdtNbmf obsfrvfdObjfdt;
        privbtf int blrfbdyNotififd;
        privbtf Objfdt dfrivfdGbugf;
        privbtf long dfrivfdGbugfTimfStbmp;
    }

    /*
     * ------------------------------------------
     *  PRIVATE VARIABLES
     * ------------------------------------------
     */

    /**
     * Attributf to obsfrvf.
     */
    privbtf String obsfrvfdAttributf;

    /**
     * Monitor grbnulbrity pfriod (in millisfdonds).
     * Tif dffbult vbluf is sft to 10 sfdonds.
     */
    privbtf long grbnulbrityPfriod = 10000;

    /**
     * Monitor stbtf.
     * Tif dffbult vbluf is sft to <CODE>fblsf</CODE>.
     */
    privbtf boolfbn isAdtivf = fblsf;

    /**
     * Monitor sfqufndf numbfr.
     * Tif dffbult vbluf is sft to 0.
     */
    privbtf finbl AtomidLong sfqufndfNumbfr = nfw AtomidLong();

    /**
     * Complfx typf bttributf flbg.
     * Tif dffbult vbluf is sft to <CODE>fblsf</CODE>.
     */
    privbtf boolfbn isComplfxTypfAttributf = fblsf;

    /**
     * First bttributf nbmf fxtrbdtfd from domplfx typf bttributf nbmf.
     */
    privbtf String firstAttributf;

    /**
     * Rfmbining bttributf nbmfs fxtrbdtfd from domplfx typf bttributf nbmf.
     */
    privbtf finbl List<String> rfmbiningAttributfs =
        nfw CopyOnWritfArrbyList<String>();

    /**
     * AddfssControlContfxt of tif Monitor.stbrt() dbllfr.
     */
    privbtf stbtid finbl AddfssControlContfxt noPfrmissionsACC =
            nfw AddfssControlContfxt(
            nfw ProtfdtionDombin[] {nfw ProtfdtionDombin(null, null)});
    privbtf volbtilf AddfssControlContfxt bdd = noPfrmissionsACC;

    /**
     * Sdifdulfr Sfrvidf.
     */
    privbtf stbtid finbl SdifdulfdExfdutorSfrvidf sdifdulfr =
        Exfdutors.nfwSinglfTirfbdSdifdulfdExfdutor(
            nfw DbfmonTirfbdFbdtory("Sdifdulfr"));

    /**
     * Mbp dontbining tif tirfbd pool fxfdutor pfr tirfbd group.
     */
    privbtf stbtid finbl Mbp<TirfbdPoolExfdutor, Void> fxfdutors =
            nfw WfbkHbsiMbp<TirfbdPoolExfdutor, Void>();

    /**
     * Lodk for fxfdutors mbp.
     */
    privbtf stbtid finbl Objfdt fxfdutorsLodk = nfw Objfdt();

    /**
     * Mbximum Pool Sizf
     */
    privbtf stbtid finbl int mbximumPoolSizf;
    stbtid {
        finbl String mbximumPoolSizfSysProp = "jmx.x.monitor.mbximum.pool.sizf";
        finbl String mbximumPoolSizfStr = AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion(mbximumPoolSizfSysProp));
        if (mbximumPoolSizfStr == null ||
            mbximumPoolSizfStr.trim().lfngti() == 0) {
            mbximumPoolSizf = 10;
        } flsf {
            int mbximumPoolSizfTmp = 10;
            try {
                mbximumPoolSizfTmp = Intfgfr.pbrsfInt(mbximumPoolSizfStr);
            } dbtdi (NumbfrFormbtExdfption f) {
                if (MONITOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    MONITOR_LOGGER.logp(Lfvfl.FINER, Monitor.dlbss.gftNbmf(),
                            "<stbtid initiblizfr>",
                            "Wrong vbluf for " + mbximumPoolSizfSysProp +
                            " systfm propfrty", f);
                    MONITOR_LOGGER.logp(Lfvfl.FINER, Monitor.dlbss.gftNbmf(),
                            "<stbtid initiblizfr>",
                            mbximumPoolSizfSysProp + " dffbults to 10");
                }
                mbximumPoolSizfTmp = 10;
            }
            if (mbximumPoolSizfTmp < 1) {
                mbximumPoolSizf = 1;
            } flsf {
                mbximumPoolSizf = mbximumPoolSizfTmp;
            }
        }
    }

    /**
     * Futurf bssodibtfd to tif durrfnt monitor tbsk.
     */
    privbtf Futurf<?> monitorFuturf;

    /**
     * Sdifdulfr tbsk to bf fxfdutfd by tif Sdifdulfr Sfrvidf.
     */
    privbtf finbl SdifdulfrTbsk sdifdulfrTbsk = nfw SdifdulfrTbsk();

    /**
     * SdifdulfdFuturf bssodibtfd to tif durrfnt sdifdulfr tbsk.
     */
    privbtf SdifdulfdFuturf<?> sdifdulfrFuturf;

    /*
     * ------------------------------------------
     *  PROTECTED VARIABLES
     * ------------------------------------------
     */

    /**
     * Tif bmount by wiidi tif dbpbdity of tif monitor brrbys brf
     * butombtidblly indrfmfntfd wifn tifir sizf bfdomfs grfbtfr tibn
     * tifir dbpbdity.
     */
    protfdtfd finbl stbtid int dbpbdityIndrfmfnt = 16;

    /**
     * Tif numbfr of vblid domponfnts in tif vfdtor of obsfrvfd objfdts.
     *
     */
    protfdtfd int flfmfntCount = 0;

    /**
     * Monitor frrors tibt ibvf blrfbdy bffn notififd.
     * @dfprfdbtfd fquivblfnt to {@link #blrfbdyNotififds}[0].
     */
    @Dfprfdbtfd
    protfdtfd int blrfbdyNotififd = 0;

    /**
     * <p>Sflfdtfd monitor frrors tibt ibvf blrfbdy bffn notififd.</p>
     *
     * <p>Ebdi flfmfnt in tiis brrby dorrfsponds to bn obsfrvfd objfdt
     * in tif vfdtor.  It dontbins b bit mbsk of tif flbgs {@link
     * #OBSERVED_OBJECT_ERROR_NOTIFIED} ftd, indidbting wiftifr tif
     * dorrfsponding notifidbtion ibs blrfbdy bffn sfnt for tif MBfbn
     * bfing monitorfd.</p>
     *
     */
    protfdtfd int blrfbdyNotififds[] = nfw int[dbpbdityIndrfmfnt];

    /**
     * Rfffrfndf to tif MBfbn sfrvfr.  Tiis rfffrfndf is null wifn tif
     * monitor MBfbn is not rfgistfrfd in bn MBfbn sfrvfr.  Tiis
     * rfffrfndf is initiblizfd bfforf tif monitor MBfbn is rfgistfrfd
     * in tif MBfbn sfrvfr.
     * @sff #prfRfgistfr(MBfbnSfrvfr sfrvfr, ObjfdtNbmf nbmf)
     */
    protfdtfd MBfbnSfrvfr sfrvfr;

    // Flbgs dffining possiblf monitor frrors.
    //

    /**
     * Tiis flbg is usfd to rfsft tif {@link #blrfbdyNotififds
     * blrfbdyNotififds} monitor bttributf.
     */
    protfdtfd stbtid finbl int RESET_FLAGS_ALREADY_NOTIFIED             = 0;

    /**
     * Flbg dfnoting tibt b notifidbtion ibs oddurrfd bftfr dibnging
     * tif obsfrvfd objfdt.  Tiis flbg is usfd to difdk tibt tif nfw
     * obsfrvfd objfdt is rfgistfrfd in tif MBfbn sfrvfr bt tif timf
     * of tif first notifidbtion.
     */
    protfdtfd stbtid finbl int OBSERVED_OBJECT_ERROR_NOTIFIED           = 1;

    /**
     * Flbg dfnoting tibt b notifidbtion ibs oddurrfd bftfr dibnging
     * tif obsfrvfd bttributf.  Tiis flbg is usfd to difdk tibt tif
     * nfw obsfrvfd bttributf bflongs to tif obsfrvfd objfdt bt tif
     * timf of tif first notifidbtion.
     */
    protfdtfd stbtid finbl int OBSERVED_ATTRIBUTE_ERROR_NOTIFIED        = 2;

    /**
     * Flbg dfnoting tibt b notifidbtion ibs oddurrfd bftfr dibnging
     * tif obsfrvfd objfdt or tif obsfrvfd bttributf.  Tiis flbg is
     * usfd to difdk tibt tif obsfrvfd bttributf typf is dorrfdt
     * (dfpfnding on tif monitor in usf) bt tif timf of tif first
     * notifidbtion.
     */
    protfdtfd stbtid finbl int OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED   = 4;

    /**
     * Flbg dfnoting tibt b notifidbtion ibs oddurrfd bftfr dibnging
     * tif obsfrvfd objfdt or tif obsfrvfd bttributf.  Tiis flbg is
     * usfd to notify bny fxdfption (fxdfpt tif dbsfs dfsdribfd bbovf)
     * wifn trying to gft tif vbluf of tif obsfrvfd bttributf bt tif
     * timf of tif first notifidbtion.
     */
    protfdtfd stbtid finbl int RUNTIME_ERROR_NOTIFIED                   = 8;

    /**
     * Tiis fifld is rftbinfd for dompbtibility but siould not bf rfffrfndfd.
     *
     * @dfprfdbtfd No rfplbdfmfnt.
     */
    @Dfprfdbtfd
    protfdtfd String dbgTbg = Monitor.dlbss.gftNbmf();

    /*
     * ------------------------------------------
     *  PACKAGE VARIABLES
     * ------------------------------------------
     */

    /**
     * List of ObsfrvfdObjfdts to wiidi tif bttributf to obsfrvf bflongs.
     */
    finbl List<ObsfrvfdObjfdt> obsfrvfdObjfdts =
        nfw CopyOnWritfArrbyList<ObsfrvfdObjfdt>();

    /**
     * Flbg dfnoting tibt b notifidbtion ibs oddurrfd bftfr dibnging
     * tif tirfsiold. Tiis flbg is usfd to notify bny fxdfption
     * rflbtfd to invblid tirfsiolds sfttings.
     */
    stbtid finbl int THRESHOLD_ERROR_NOTIFIED                           = 16;

    /**
     * Enumfrbtion usfd to kffp trbdf of tif dfrivfd gbugf typf
     * in dountfr bnd gbugf monitors.
     */
    fnum NumfridblTypf { BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE };

    /**
     * Constbnt usfd to initiblizf bll tif numfrid vblufs.
     */
    stbtid finbl Intfgfr INTEGER_ZERO = 0;


    /*
     * ------------------------------------------
     *  PUBLIC METHODS
     * ------------------------------------------
     */

    /**
     * Allows tif monitor MBfbn to pfrform bny opfrbtions it nffds
     * bfforf bfing rfgistfrfd in tif MBfbn sfrvfr.
     * <P>
     * Initiblizfs tif rfffrfndf to tif MBfbn sfrvfr.
     *
     * @pbrbm sfrvfr Tif MBfbn sfrvfr in wiidi tif monitor MBfbn will
     * bf rfgistfrfd.
     * @pbrbm nbmf Tif objfdt nbmf of tif monitor MBfbn.
     *
     * @rfturn Tif nbmf of tif monitor MBfbn rfgistfrfd.
     *
     * @fxdfption Exdfption
     */
    publid ObjfdtNbmf prfRfgistfr(MBfbnSfrvfr sfrvfr, ObjfdtNbmf nbmf)
        tirows Exdfption {

        MONITOR_LOGGER.logp(Lfvfl.FINER, Monitor.dlbss.gftNbmf(),
                "prfRfgistfr(MBfbnSfrvfr, ObjfdtNbmf)",
                "initiblizf tif rfffrfndf on tif MBfbn sfrvfr");

        tiis.sfrvfr = sfrvfr;
        rfturn nbmf;
    }

    /**
     * Allows tif monitor MBfbn to pfrform bny opfrbtions nffdfd bftfr
     * ibving bffn rfgistfrfd in tif MBfbn sfrvfr or bftfr tif
     * rfgistrbtion ibs fbilfd.
     * <P>
     * Not usfd in tiis dontfxt.
     */
    publid void postRfgistfr(Boolfbn rfgistrbtionDonf) {
    }

    /**
     * Allows tif monitor MBfbn to pfrform bny opfrbtions it nffds
     * bfforf bfing unrfgistfrfd by tif MBfbn sfrvfr.
     * <P>
     * Stops tif monitor.
     *
     * @fxdfption Exdfption
     */
    publid void prfDfrfgistfr() tirows Exdfption {

        MONITOR_LOGGER.logp(Lfvfl.FINER, Monitor.dlbss.gftNbmf(),
                "prfDfrfgistfr()", "stop tif monitor");

        // Stop tif Monitor.
        //
        stop();
    }

    /**
     * Allows tif monitor MBfbn to pfrform bny opfrbtions nffdfd bftfr
     * ibving bffn unrfgistfrfd by tif MBfbn sfrvfr.
     * <P>
     * Not usfd in tiis dontfxt.
     */
    publid void postDfrfgistfr() {
    }

    /**
     * Stbrts tif monitor.
     */
    publid bbstrbdt void stbrt();

    /**
     * Stops tif monitor.
     */
    publid bbstrbdt void stop();

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Rfturns tif objfdt nbmf of tif first objfdt in tif sft of obsfrvfd
     * MBfbns, or <dodf>null</dodf> if tifrf is no sudi objfdt.
     *
     * @rfturn Tif objfdt bfing obsfrvfd.
     *
     * @sff #sftObsfrvfdObjfdt(ObjfdtNbmf)
     *
     * @dfprfdbtfd As of JMX 1.2, rfplbdfd by {@link #gftObsfrvfdObjfdts}
     */
    @Dfprfdbtfd
    publid syndironizfd ObjfdtNbmf gftObsfrvfdObjfdt() {
        if (obsfrvfdObjfdts.isEmpty()) {
            rfturn null;
        } flsf {
            rfturn obsfrvfdObjfdts.gft(0).gftObsfrvfdObjfdt();
        }
    }

    /**
     * Rfmovfs bll objfdts from tif sft of obsfrvfd objfdts, bnd tifn bdds tif
     * spfdififd objfdt.
     *
     * @pbrbm objfdt Tif objfdt to obsfrvf.
     * @fxdfption IllfgblArgumfntExdfption Tif spfdififd
     * objfdt is null.
     *
     * @sff #gftObsfrvfdObjfdt()
     *
     * @dfprfdbtfd As of JMX 1.2, rfplbdfd by {@link #bddObsfrvfdObjfdt}
     */
    @Dfprfdbtfd
    publid syndironizfd void sftObsfrvfdObjfdt(ObjfdtNbmf objfdt)
        tirows IllfgblArgumfntExdfption {
        if (objfdt == null)
            tirow nfw IllfgblArgumfntExdfption("Null obsfrvfd objfdt");
        if (obsfrvfdObjfdts.sizf() == 1 && dontbinsObsfrvfdObjfdt(objfdt))
            rfturn;
        obsfrvfdObjfdts.dlfbr();
        bddObsfrvfdObjfdt(objfdt);
    }

    /**
     * Adds tif spfdififd objfdt in tif sft of obsfrvfd MBfbns, if tiis objfdt
     * is not blrfbdy prfsfnt.
     *
     * @pbrbm objfdt Tif objfdt to obsfrvf.
     * @fxdfption IllfgblArgumfntExdfption Tif spfdififd objfdt is null.
     *
     */
    publid syndironizfd void bddObsfrvfdObjfdt(ObjfdtNbmf objfdt)
        tirows IllfgblArgumfntExdfption {

        if (objfdt == null) {
            tirow nfw IllfgblArgumfntExdfption("Null obsfrvfd objfdt");
        }

        // Cifdk tibt tif spfdififd objfdt is not blrfbdy dontbinfd.
        //
        if (dontbinsObsfrvfdObjfdt(objfdt))
            rfturn;

        // Add tif spfdififd objfdt in tif list.
        //
        ObsfrvfdObjfdt o = drfbtfObsfrvfdObjfdt(objfdt);
        o.sftAlrfbdyNotififd(RESET_FLAGS_ALREADY_NOTIFIED);
        o.sftDfrivfdGbugf(INTEGER_ZERO);
        o.sftDfrivfdGbugfTimfStbmp(Systfm.durrfntTimfMillis());
        obsfrvfdObjfdts.bdd(o);

        // Updbtf lfgbdy protfdtfd stuff.
        //
        drfbtfAlrfbdyNotififd();
    }

    /**
     * Rfmovfs tif spfdififd objfdt from tif sft of obsfrvfd MBfbns.
     *
     * @pbrbm objfdt Tif objfdt to rfmovf.
     *
     */
    publid syndironizfd void rfmovfObsfrvfdObjfdt(ObjfdtNbmf objfdt) {
        // Cifdk for null objfdt.
        //
        if (objfdt == null)
            rfturn;

        finbl ObsfrvfdObjfdt o = gftObsfrvfdObjfdt(objfdt);
        if (o != null) {
            // Rfmovf tif spfdififd objfdt from tif list.
            //
            obsfrvfdObjfdts.rfmovf(o);
            // Updbtf lfgbdy protfdtfd stuff.
            //
            drfbtfAlrfbdyNotififd();
        }
    }

    /**
     * Tfsts wiftifr tif spfdififd objfdt is in tif sft of obsfrvfd MBfbns.
     *
     * @pbrbm objfdt Tif objfdt to difdk.
     * @rfturn <CODE>truf</CODE> if tif spfdififd objfdt is prfsfnt,
     * <CODE>fblsf</CODE> otifrwisf.
     *
     */
    publid syndironizfd boolfbn dontbinsObsfrvfdObjfdt(ObjfdtNbmf objfdt) {
        rfturn gftObsfrvfdObjfdt(objfdt) != null;
    }

    /**
     * Rfturns bn brrby dontbining tif objfdts bfing obsfrvfd.
     *
     * @rfturn Tif objfdts bfing obsfrvfd.
     *
     */
    publid syndironizfd ObjfdtNbmf[] gftObsfrvfdObjfdts() {
        ObjfdtNbmf[] nbmfs = nfw ObjfdtNbmf[obsfrvfdObjfdts.sizf()];
        for (int i = 0; i < nbmfs.lfngti; i++)
            nbmfs[i] = obsfrvfdObjfdts.gft(i).gftObsfrvfdObjfdt();
        rfturn nbmfs;
    }

    /**
     * Gfts tif bttributf bfing obsfrvfd.
     * <BR>Tif obsfrvfd bttributf is not initiblizfd by dffbult (sft to null).
     *
     * @rfturn Tif bttributf bfing obsfrvfd.
     *
     * @sff #sftObsfrvfdAttributf
     */
    publid syndironizfd String gftObsfrvfdAttributf() {
        rfturn obsfrvfdAttributf;
    }

    /**
     * Sfts tif bttributf to obsfrvf.
     * <BR>Tif obsfrvfd bttributf is not initiblizfd by dffbult (sft to null).
     *
     * @pbrbm bttributf Tif bttributf to obsfrvf.
     * @fxdfption IllfgblArgumfntExdfption Tif spfdififd
     * bttributf is null.
     *
     * @sff #gftObsfrvfdAttributf
     */
    publid void sftObsfrvfdAttributf(String bttributf)
        tirows IllfgblArgumfntExdfption {

        if (bttributf == null) {
            tirow nfw IllfgblArgumfntExdfption("Null obsfrvfd bttributf");
        }

        // Updbtf blrfbdyNotififd brrby.
        //
        syndironizfd (tiis) {
            if (obsfrvfdAttributf != null &&
                obsfrvfdAttributf.fqubls(bttributf))
                rfturn;
            obsfrvfdAttributf = bttributf;

            // Rfsft tif domplfx typf bttributf informbtion
            // sudi tibt it is rfdbldulbtfd bgbin.
            //
            dlfbnupIsComplfxTypfAttributf();

            int indfx = 0;
            for (ObsfrvfdObjfdt o : obsfrvfdObjfdts) {
                rfsftAlrfbdyNotififd(o, indfx++,
                                     OBSERVED_ATTRIBUTE_ERROR_NOTIFIED |
                                     OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED);
            }
        }
    }

    /**
     * Gfts tif grbnulbrity pfriod (in millisfdonds).
     * <BR>Tif dffbult vbluf of tif grbnulbrity pfriod is 10 sfdonds.
     *
     * @rfturn Tif grbnulbrity pfriod vbluf.
     *
     * @sff #sftGrbnulbrityPfriod
     */
    publid syndironizfd long gftGrbnulbrityPfriod() {
        rfturn grbnulbrityPfriod;
    }

    /**
     * Sfts tif grbnulbrity pfriod (in millisfdonds).
     * <BR>Tif dffbult vbluf of tif grbnulbrity pfriod is 10 sfdonds.
     *
     * @pbrbm pfriod Tif grbnulbrity pfriod vbluf.
     * @fxdfption IllfgblArgumfntExdfption Tif grbnulbrity
     * pfriod is lfss tibn or fqubl to zfro.
     *
     * @sff #gftGrbnulbrityPfriod
     */
    publid syndironizfd void sftGrbnulbrityPfriod(long pfriod)
        tirows IllfgblArgumfntExdfption {

        if (pfriod <= 0) {
            tirow nfw IllfgblArgumfntExdfption("Nonpositivf grbnulbrity " +
                                               "pfriod");
        }

        if (grbnulbrityPfriod == pfriod)
            rfturn;
        grbnulbrityPfriod = pfriod;

        // Rfsdifdulf tif sdifdulfr tbsk if tif monitor is bdtivf.
        //
        if (isAdtivf()) {
            dlfbnupFuturfs();
            sdifdulfrFuturf = sdifdulfr.sdifdulf(sdifdulfrTbsk,
                                                 pfriod,
                                                 TimfUnit.MILLISECONDS);
        }
    }

    /**
     * Tfsts wiftifr tif monitor MBfbn is bdtivf.  A monitor MBfbn is
     * mbrkfd bdtivf wifn tif {@link #stbrt stbrt} mftiod is dbllfd.
     * It bfdomfs inbdtivf wifn tif {@link #stop stop} mftiod is
     * dbllfd.
     *
     * @rfturn <CODE>truf</CODE> if tif monitor MBfbn is bdtivf,
     * <CODE>fblsf</CODE> otifrwisf.
     */
    /* Tiis mftiod must bf syndironizfd so tibt tif monitoring tirfbd will
       dorrfdtly sff modifidbtions to tif isAdtivf vbribblf. Sff tif MonitorTbsk
       bdtion fxfdutfd by tif Sdifdulfd Exfdutor Sfrvidf. */
    publid syndironizfd boolfbn isAdtivf() {
        rfturn isAdtivf;
    }

    /*
     * ------------------------------------------
     *  PACKAGE METHODS
     * ------------------------------------------
     */

    /**
     * Stbrts tif monitor.
     */
    void doStbrt() {
            MONITOR_LOGGER.logp(Lfvfl.FINER, Monitor.dlbss.gftNbmf(),
                "doStbrt()", "stbrt tif monitor");

        syndironizfd (tiis) {
            if (isAdtivf()) {
                MONITOR_LOGGER.logp(Lfvfl.FINER, Monitor.dlbss.gftNbmf(),
                        "doStbrt()", "tif monitor is blrfbdy bdtivf");
                rfturn;
            }

            isAdtivf = truf;

            // Rfsft tif domplfx typf bttributf informbtion
            // sudi tibt it is rfdbldulbtfd bgbin.
            //
            dlfbnupIsComplfxTypfAttributf();

            // Cbdif tif AddfssControlContfxt of tif Monitor.stbrt() dbllfr.
            // Tif monitor tbsks will bf fxfdutfd witiin tiis dontfxt.
            //
            bdd = AddfssControllfr.gftContfxt();

            // Stbrt tif sdifdulfr.
            //
            dlfbnupFuturfs();
            sdifdulfrTbsk.sftMonitorTbsk(nfw MonitorTbsk());
            sdifdulfrFuturf = sdifdulfr.sdifdulf(sdifdulfrTbsk,
                                                 gftGrbnulbrityPfriod(),
                                                 TimfUnit.MILLISECONDS);
        }
    }

    /**
     * Stops tif monitor.
     */
    void doStop() {
        MONITOR_LOGGER.logp(Lfvfl.FINER, Monitor.dlbss.gftNbmf(),
                "doStop()", "stop tif monitor");

        syndironizfd (tiis) {
            if (!isAdtivf()) {
                MONITOR_LOGGER.logp(Lfvfl.FINER, Monitor.dlbss.gftNbmf(),
                        "doStop()", "tif monitor is not bdtivf");
                rfturn;
            }

            isAdtivf = fblsf;

            // Cbndfl tif sdifdulfr tbsk bssodibtfd witi tif
            // sdifdulfr bnd its bssodibtfd monitor tbsk.
            //
            dlfbnupFuturfs();

            // Rfsft tif AddfssControlContfxt.
            //
            bdd = noPfrmissionsACC;

            // Rfsft tif domplfx typf bttributf informbtion
            // sudi tibt it is rfdbldulbtfd bgbin.
            //
            dlfbnupIsComplfxTypfAttributf();
        }
    }

    /**
     * Gfts tif dfrivfd gbugf of tif spfdififd objfdt, if tiis objfdt is
     * dontbinfd in tif sft of obsfrvfd MBfbns, or <dodf>null</dodf> otifrwisf.
     *
     * @pbrbm objfdt tif nbmf of tif objfdt wiosf dfrivfd gbugf is to
     * bf rfturnfd.
     *
     * @rfturn Tif dfrivfd gbugf of tif spfdififd objfdt.
     *
     * @sindf 1.6
     */
    syndironizfd Objfdt gftDfrivfdGbugf(ObjfdtNbmf objfdt) {
        finbl ObsfrvfdObjfdt o = gftObsfrvfdObjfdt(objfdt);
        rfturn o == null ? null : o.gftDfrivfdGbugf();
    }

    /**
     * Gfts tif dfrivfd gbugf timfstbmp of tif spfdififd objfdt, if
     * tiis objfdt is dontbinfd in tif sft of obsfrvfd MBfbns, or
     * <dodf>0</dodf> otifrwisf.
     *
     * @pbrbm objfdt tif nbmf of tif objfdt wiosf dfrivfd gbugf
     * timfstbmp is to bf rfturnfd.
     *
     * @rfturn Tif dfrivfd gbugf timfstbmp of tif spfdififd objfdt.
     *
     */
    syndironizfd long gftDfrivfdGbugfTimfStbmp(ObjfdtNbmf objfdt) {
        finbl ObsfrvfdObjfdt o = gftObsfrvfdObjfdt(objfdt);
        rfturn o == null ? 0 : o.gftDfrivfdGbugfTimfStbmp();
    }

    Objfdt gftAttributf(MBfbnSfrvfrConnfdtion mbsd,
                        ObjfdtNbmf objfdt,
                        String bttributf)
        tirows AttributfNotFoundExdfption,
               InstbndfNotFoundExdfption,
               MBfbnExdfption,
               RfflfdtionExdfption,
               IOExdfption {
        // Cifdk for "ObsfrvfdAttributf" rfplbdfmfnt.
        // Tiis dould ibppfn if b tirfbd A dbllfd sftObsfrvfdAttributf()
        // wiilf otifr tirfbd B wbs in tif middlf of tif monitor() mftiod
        // bnd rfdfivfd tif old obsfrvfd bttributf vbluf.
        //
        finbl boolfbn lookupMBfbnInfo;
        syndironizfd (tiis) {
            if (!isAdtivf())
                tirow nfw IllfgblArgumfntExdfption(
                    "Tif monitor ibs bffn stoppfd");
            if (!bttributf.fqubls(gftObsfrvfdAttributf()))
                tirow nfw IllfgblArgumfntExdfption(
                    "Tif obsfrvfd bttributf ibs bffn dibngfd");
            lookupMBfbnInfo =
                (firstAttributf == null && bttributf.indfxOf('.') != -1);
        }

        // Look up MBfbnInfo if nffdfd
        //
        finbl MBfbnInfo mbi;
        if (lookupMBfbnInfo) {
            try {
                mbi = mbsd.gftMBfbnInfo(objfdt);
            } dbtdi (IntrospfdtionExdfption f) {
                tirow nfw IllfgblArgumfntExdfption(f);
            }
        } flsf {
            mbi = null;
        }

        // Cifdk for domplfx typf bttributf
        //
        finbl String fb;
        syndironizfd (tiis) {
            if (!isAdtivf())
                tirow nfw IllfgblArgumfntExdfption(
                    "Tif monitor ibs bffn stoppfd");
            if (!bttributf.fqubls(gftObsfrvfdAttributf()))
                tirow nfw IllfgblArgumfntExdfption(
                    "Tif obsfrvfd bttributf ibs bffn dibngfd");
            if (firstAttributf == null) {
                if (bttributf.indfxOf('.') != -1) {
                    MBfbnAttributfInfo mbbiArrby[] = mbi.gftAttributfs();
                    for (MBfbnAttributfInfo mbbi : mbbiArrby) {
                        if (bttributf.fqubls(mbbi.gftNbmf())) {
                            firstAttributf = bttributf;
                            brfbk;
                        }
                    }
                    if (firstAttributf == null) {
                        String tokfns[] = bttributf.split("\\.", -1);
                        firstAttributf = tokfns[0];
                        for (int i = 1; i < tokfns.lfngti; i++)
                            rfmbiningAttributfs.bdd(tokfns[i]);
                        isComplfxTypfAttributf = truf;
                    }
                } flsf {
                    firstAttributf = bttributf;
                }
            }
            fb = firstAttributf;
        }
        rfturn mbsd.gftAttributf(objfdt, fb);
    }

    Compbrbblf<?> gftCompbrbblfFromAttributf(ObjfdtNbmf objfdt,
                                             String bttributf,
                                             Objfdt vbluf)
        tirows AttributfNotFoundExdfption {
        if (isComplfxTypfAttributf) {
            Objfdt v = vbluf;
            for (String bttr : rfmbiningAttributfs)
                v = Introspfdtor.flfmfntFromComplfx(v, bttr);
            rfturn (Compbrbblf<?>) v;
        } flsf {
            rfturn (Compbrbblf<?>) vbluf;
        }
    }

    boolfbn isCompbrbblfTypfVblid(ObjfdtNbmf objfdt,
                                  String bttributf,
                                  Compbrbblf<?> vbluf) {
        rfturn truf;
    }

    String buildErrorNotifidbtion(ObjfdtNbmf objfdt,
                                  String bttributf,
                                  Compbrbblf<?> vbluf) {
        rfturn null;
    }

    void onErrorNotifidbtion(MonitorNotifidbtion notifidbtion) {
    }

    Compbrbblf<?> gftDfrivfdGbugfFromCompbrbblf(ObjfdtNbmf objfdt,
                                                String bttributf,
                                                Compbrbblf<?> vbluf) {
        rfturn (Compbrbblf<?>) vbluf;
    }

    MonitorNotifidbtion buildAlbrmNotifidbtion(ObjfdtNbmf objfdt,
                                               String bttributf,
                                               Compbrbblf<?> vbluf){
        rfturn null;
    }

    boolfbn isTirfsioldTypfVblid(ObjfdtNbmf objfdt,
                                 String bttributf,
                                 Compbrbblf<?> vbluf) {
        rfturn truf;
    }

    stbtid Clbss<? fxtfnds Numbfr> dlbssForTypf(NumfridblTypf typf) {
        switdi (typf) {
            dbsf BYTE:
                rfturn Bytf.dlbss;
            dbsf SHORT:
                rfturn Siort.dlbss;
            dbsf INTEGER:
                rfturn Intfgfr.dlbss;
            dbsf LONG:
                rfturn Long.dlbss;
            dbsf FLOAT:
                rfturn Flobt.dlbss;
            dbsf DOUBLE:
                rfturn Doublf.dlbss;
            dffbult:
                tirow nfw IllfgblArgumfntExdfption(
                    "Unsupportfd numfridbl typf");
        }
    }

    stbtid boolfbn isVblidForTypf(Objfdt vbluf, Clbss<? fxtfnds Numbfr> d) {
        rfturn ((vbluf == INTEGER_ZERO) || d.isInstbndf(vbluf));
    }

    /**
     * Gft tif spfdififd {@dodf ObsfrvfdObjfdt} if tiis objfdt is
     * dontbinfd in tif sft of obsfrvfd MBfbns, or {@dodf null}
     * otifrwisf.
     *
     * @pbrbm objfdt tif nbmf of tif {@dodf ObsfrvfdObjfdt} to rftrifvf.
     *
     * @rfturn Tif {@dodf ObsfrvfdObjfdt} bssodibtfd to tif supplifd
     * {@dodf ObjfdtNbmf}.
     *
     * @sindf 1.6
     */
    syndironizfd ObsfrvfdObjfdt gftObsfrvfdObjfdt(ObjfdtNbmf objfdt) {
        for (ObsfrvfdObjfdt o : obsfrvfdObjfdts)
            if (o.gftObsfrvfdObjfdt().fqubls(objfdt))
                rfturn o;
        rfturn null;
    }

    /**
     * Fbdtory mftiod for ObsfrvfdObjfdt drfbtion.
     *
     * @sindf 1.6
     */
    ObsfrvfdObjfdt drfbtfObsfrvfdObjfdt(ObjfdtNbmf objfdt) {
        rfturn nfw ObsfrvfdObjfdt(objfdt);
    }

    /**
     * Crfbtf tif {@link #blrfbdyNotififd} brrby from
     * tif {@dodf ObsfrvfdObjfdt} brrby list.
     */
    syndironizfd void drfbtfAlrfbdyNotififd() {
        // Updbtf flfmfntCount.
        //
        flfmfntCount = obsfrvfdObjfdts.sizf();

        // Updbtf brrbys.
        //
        blrfbdyNotififds = nfw int[flfmfntCount];
        for (int i = 0; i < flfmfntCount; i++) {
            blrfbdyNotififds[i] = obsfrvfdObjfdts.gft(i).gftAlrfbdyNotififd();
        }
        updbtfDfprfdbtfdAlrfbdyNotififd();
    }

    /**
     * Updbtf tif dfprfdbtfd {@link #blrfbdyNotififd} fifld.
     */
    syndironizfd void updbtfDfprfdbtfdAlrfbdyNotififd() {
        if (flfmfntCount > 0)
            blrfbdyNotififd = blrfbdyNotififds[0];
        flsf
            blrfbdyNotififd = 0;
    }

    /**
     * Updbtf tif {@link #blrfbdyNotififds} brrby flfmfnt bt tif givfn indfx
     * witi tif blrfbdy notififd flbg in tif givfn {@dodf ObsfrvfdObjfdt}.
     * Ensurf tif dfprfdbtfd {@link #blrfbdyNotififd} fifld is updbtfd
     * if bppropribtf.
     */
    syndironizfd void updbtfAlrfbdyNotififd(ObsfrvfdObjfdt o, int indfx) {
        blrfbdyNotififds[indfx] = o.gftAlrfbdyNotififd();
        if (indfx == 0)
            updbtfDfprfdbtfdAlrfbdyNotififd();
    }

    /**
     * Cifdk if tif givfn bits in tif givfn flfmfnt of {@link #blrfbdyNotififds}
     * brf sft.
     */
    syndironizfd boolfbn isAlrfbdyNotififd(ObsfrvfdObjfdt o, int mbsk) {
        rfturn ((o.gftAlrfbdyNotififd() & mbsk) != 0);
    }

    /**
     * Sft tif givfn bits in tif givfn flfmfnt of {@link #blrfbdyNotififds}.
     * Ensurf tif dfprfdbtfd {@link #blrfbdyNotififd} fifld is updbtfd
     * if bppropribtf.
     */
    syndironizfd void sftAlrfbdyNotififd(ObsfrvfdObjfdt o, int indfx,
                                         int mbsk, int bn[]) {
        finbl int i = domputfAlrfbdyNotififdIndfx(o, indfx, bn);
        if (i == -1)
            rfturn;
        o.sftAlrfbdyNotififd(o.gftAlrfbdyNotififd() | mbsk);
        updbtfAlrfbdyNotififd(o, i);
    }

    /**
     * Rfsft tif givfn bits in tif givfn flfmfnt of {@link #blrfbdyNotififds}.
     * Ensurf tif dfprfdbtfd {@link #blrfbdyNotififd} fifld is updbtfd
     * if bppropribtf.
     */
    syndironizfd void rfsftAlrfbdyNotififd(ObsfrvfdObjfdt o,
                                           int indfx, int mbsk) {
        o.sftAlrfbdyNotififd(o.gftAlrfbdyNotififd() & ~mbsk);
        updbtfAlrfbdyNotififd(o, indfx);
    }

    /**
     * Rfsft bll bits in tif givfn flfmfnt of {@link #blrfbdyNotififds}.
     * Ensurf tif dfprfdbtfd {@link #blrfbdyNotififd} fifld is updbtfd
     * if bppropribtf.
     */
    syndironizfd void rfsftAllAlrfbdyNotififd(ObsfrvfdObjfdt o,
                                              int indfx, int bn[]) {
        finbl int i = domputfAlrfbdyNotififdIndfx(o, indfx, bn);
        if (i == -1)
            rfturn;
        o.sftAlrfbdyNotififd(RESET_FLAGS_ALREADY_NOTIFIED);
        updbtfAlrfbdyNotififd(o, indfx);
    }

    /**
     * Cifdk if tif {@link #blrfbdyNotififds} brrby ibs bffn modififd.
     * If truf rfdomputf tif indfx for tif givfn obsfrvfd objfdt.
     */
    syndironizfd int domputfAlrfbdyNotififdIndfx(ObsfrvfdObjfdt o,
                                                 int indfx, int bn[]) {
        if (bn == blrfbdyNotififds) {
            rfturn indfx;
        } flsf {
            rfturn obsfrvfdObjfdts.indfxOf(o);
        }
    }

    /*
     * ------------------------------------------
     *  PRIVATE METHODS
     * ------------------------------------------
     */

    /**
     * Tiis mftiod is usfd by tif monitor MBfbn to drfbtf bnd sfnd b
     * monitor notifidbtion to bll tif listfnfrs rfgistfrfd for tiis
     * kind of notifidbtion.
     *
     * @pbrbm typf Tif notifidbtion typf.
     * @pbrbm timfStbmp Tif notifidbtion fmission dbtf.
     * @pbrbm msg Tif notifidbtion mfssbgf.
     * @pbrbm dfrGbugf Tif dfrivfd gbugf.
     * @pbrbm triggfr Tif tirfsiold/string (dfpfnding on tif monitor
     * typf) tibt triggfrfd off tif notifidbtion.
     * @pbrbm objfdt Tif ObjfdtNbmf of tif obsfrvfd objfdt tibt triggfrfd
     * off tif notifidbtion.
     * @pbrbm onError Flbg indidbting if tiis monitor notifidbtion is
     * bn frror notifidbtion or bn blbrm notifidbtion.
     */
    privbtf void sfndNotifidbtion(String typf, long timfStbmp, String msg,
                                  Objfdt dfrGbugf, Objfdt triggfr,
                                  ObjfdtNbmf objfdt, boolfbn onError) {
        if (!isAdtivf())
            rfturn;

        if (MONITOR_LOGGER.isLoggbblf(Lfvfl.FINER)) {
            MONITOR_LOGGER.logp(Lfvfl.FINER, Monitor.dlbss.gftNbmf(),
                    "sfndNotifidbtion", "sfnd notifidbtion: " +
                    "\n\tNotifidbtion obsfrvfd objfdt = " + objfdt +
                    "\n\tNotifidbtion obsfrvfd bttributf = " + obsfrvfdAttributf +
                    "\n\tNotifidbtion dfrivfd gbugf = " + dfrGbugf);
        }

        long sfqno = sfqufndfNumbfr.gftAndIndrfmfnt();

        MonitorNotifidbtion mn =
            nfw MonitorNotifidbtion(typf,
                                    tiis,
                                    sfqno,
                                    timfStbmp,
                                    msg,
                                    objfdt,
                                    obsfrvfdAttributf,
                                    dfrGbugf,
                                    triggfr);
        if (onError)
            onErrorNotifidbtion(mn);
        sfndNotifidbtion(mn);
    }

    /**
     * Tiis mftiod is dbllfd by tif monitor fbdi timf
     * tif grbnulbrity pfriod ibs bffn fxdffdfd.
     * @pbrbm o Tif obsfrvfd objfdt.
     */
    privbtf void monitor(ObsfrvfdObjfdt o, int indfx, int bn[]) {

        String bttributf;
        String notifTypf = null;
        String msg = null;
        Objfdt dfrGbugf = null;
        Objfdt triggfr = null;
        ObjfdtNbmf objfdt;
        Compbrbblf<?> vbluf = null;
        MonitorNotifidbtion blbrm = null;

        if (!isAdtivf())
            rfturn;

        // Cifdk tibt nfitifr tif obsfrvfd objfdt nor tif
        // obsfrvfd bttributf brf null.  If tif obsfrvfd
        // objfdt or obsfrvfd bttributf is null, tiis mfbns
        // tibt tif monitor stbrtfd bfforf b domplftf
        // initiblizbtion bnd notiing is donf.
        //
        syndironizfd (tiis) {
            objfdt = o.gftObsfrvfdObjfdt();
            bttributf = gftObsfrvfdAttributf();
            if (objfdt == null || bttributf == null) {
                rfturn;
            }
        }

        // Cifdk tibt tif obsfrvfd objfdt is rfgistfrfd in tif
        // MBfbn sfrvfr bnd tibt tif obsfrvfd bttributf
        // bflongs to tif obsfrvfd objfdt.
        //
        Objfdt bttributfVbluf = null;
        try {
            bttributfVbluf = gftAttributf(sfrvfr, objfdt, bttributf);
            if (bttributfVbluf == null)
                if (isAlrfbdyNotififd(
                        o, OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED))
                    rfturn;
                flsf {
                    notifTypf = OBSERVED_ATTRIBUTE_TYPE_ERROR;
                    sftAlrfbdyNotififd(
                        o, indfx, OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED, bn);
                    msg = "Tif obsfrvfd bttributf vbluf is null.";
                    MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                            "monitor", msg);
                }
        } dbtdi (NullPointfrExdfption np_fx) {
            if (isAlrfbdyNotififd(o, RUNTIME_ERROR_NOTIFIED))
                rfturn;
            flsf {
                notifTypf = RUNTIME_ERROR;
                sftAlrfbdyNotififd(o, indfx, RUNTIME_ERROR_NOTIFIED, bn);
                msg =
                    "Tif monitor must bf rfgistfrfd in tif MBfbn " +
                    "sfrvfr or bn MBfbnSfrvfrConnfdtion must bf " +
                    "fxpliditly supplifd.";
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", np_fx.toString());
            }
        } dbtdi (InstbndfNotFoundExdfption inf_fx) {
            if (isAlrfbdyNotififd(o, OBSERVED_OBJECT_ERROR_NOTIFIED))
                rfturn;
            flsf {
                notifTypf = OBSERVED_OBJECT_ERROR;
                sftAlrfbdyNotififd(
                    o, indfx, OBSERVED_OBJECT_ERROR_NOTIFIED, bn);
                msg =
                    "Tif obsfrvfd objfdt must bf bddfssiblf in " +
                    "tif MBfbnSfrvfrConnfdtion.";
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", inf_fx.toString());
            }
        } dbtdi (AttributfNotFoundExdfption bnf_fx) {
            if (isAlrfbdyNotififd(o, OBSERVED_ATTRIBUTE_ERROR_NOTIFIED))
                rfturn;
            flsf {
                notifTypf = OBSERVED_ATTRIBUTE_ERROR;
                sftAlrfbdyNotififd(
                    o, indfx, OBSERVED_ATTRIBUTE_ERROR_NOTIFIED, bn);
                msg =
                    "Tif obsfrvfd bttributf must bf bddfssiblf in " +
                    "tif obsfrvfd objfdt.";
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", bnf_fx.toString());
            }
        } dbtdi (MBfbnExdfption mb_fx) {
            if (isAlrfbdyNotififd(o, RUNTIME_ERROR_NOTIFIED))
                rfturn;
            flsf {
                notifTypf = RUNTIME_ERROR;
                sftAlrfbdyNotififd(o, indfx, RUNTIME_ERROR_NOTIFIED, bn);
                msg = mb_fx.gftMfssbgf() == null ? "" : mb_fx.gftMfssbgf();
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", mb_fx.toString());
            }
        } dbtdi (RfflfdtionExdfption rff_fx) {
            if (isAlrfbdyNotififd(o, RUNTIME_ERROR_NOTIFIED)) {
                rfturn;
            } flsf {
                notifTypf = RUNTIME_ERROR;
                sftAlrfbdyNotififd(o, indfx, RUNTIME_ERROR_NOTIFIED, bn);
                msg = rff_fx.gftMfssbgf() == null ? "" : rff_fx.gftMfssbgf();
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", rff_fx.toString());
            }
        } dbtdi (IOExdfption io_fx) {
            if (isAlrfbdyNotififd(o, RUNTIME_ERROR_NOTIFIED))
                rfturn;
            flsf {
                notifTypf = RUNTIME_ERROR;
                sftAlrfbdyNotififd(o, indfx, RUNTIME_ERROR_NOTIFIED, bn);
                msg = io_fx.gftMfssbgf() == null ? "" : io_fx.gftMfssbgf();
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", io_fx.toString());
            }
        } dbtdi (RuntimfExdfption rt_fx) {
            if (isAlrfbdyNotififd(o, RUNTIME_ERROR_NOTIFIED))
                rfturn;
            flsf {
                notifTypf = RUNTIME_ERROR;
                sftAlrfbdyNotififd(o, indfx, RUNTIME_ERROR_NOTIFIED, bn);
                msg = rt_fx.gftMfssbgf() == null ? "" : rt_fx.gftMfssbgf();
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Lfvfl.FINEST, Monitor.dlbss.gftNbmf(),
                        "monitor", rt_fx.toString());
            }
        }

        syndironizfd (tiis) {

            // Cifdk if tif monitor ibs bffn stoppfd.
            //
            if (!isAdtivf())
                rfturn;

            // Cifdk if tif obsfrvfd bttributf ibs bffn dibngfd.
            //
            // Avoid rbdf dondition wifrf mbs.gftAttributf() suddffdfd but
            // bnotifr tirfbd rfplbdfd tif obsfrvfd bttributf mfbnwiilf.
            //
            // Avoid sftting domputfd dfrivfd gbugf on frronfous bttributf.
            //
            if (!bttributf.fqubls(gftObsfrvfdAttributf()))
                rfturn;

            // Dfrivf b Compbrbblf objfdt from tif ObsfrvfdAttributf vbluf
            // if tif typf of tif ObsfrvfdAttributf vbluf is b domplfx typf.
            //
            if (msg == null) {
                try {
                    vbluf = gftCompbrbblfFromAttributf(objfdt,
                                                       bttributf,
                                                       bttributfVbluf);
                } dbtdi (ClbssCbstExdfption f) {
                    if (isAlrfbdyNotififd(
                            o, OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED))
                        rfturn;
                    flsf {
                        notifTypf = OBSERVED_ATTRIBUTE_TYPE_ERROR;
                        sftAlrfbdyNotififd(o, indfx,
                            OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED, bn);
                        msg =
                            "Tif obsfrvfd bttributf vbluf dofs not " +
                            "implfmfnt tif Compbrbblf intfrfbdf.";
                        MONITOR_LOGGER.logp(Lfvfl.FINEST,
                                Monitor.dlbss.gftNbmf(), "monitor", msg);
                        MONITOR_LOGGER.logp(Lfvfl.FINEST,
                                Monitor.dlbss.gftNbmf(), "monitor", f.toString());
                    }
                } dbtdi (AttributfNotFoundExdfption f) {
                    if (isAlrfbdyNotififd(o, OBSERVED_ATTRIBUTE_ERROR_NOTIFIED))
                        rfturn;
                    flsf {
                        notifTypf = OBSERVED_ATTRIBUTE_ERROR;
                        sftAlrfbdyNotififd(
                            o, indfx, OBSERVED_ATTRIBUTE_ERROR_NOTIFIED, bn);
                        msg =
                            "Tif obsfrvfd bttributf must bf bddfssiblf in " +
                            "tif obsfrvfd objfdt.";
                        MONITOR_LOGGER.logp(Lfvfl.FINEST,
                                Monitor.dlbss.gftNbmf(), "monitor", msg);
                        MONITOR_LOGGER.logp(Lfvfl.FINEST,
                                Monitor.dlbss.gftNbmf(), "monitor", f.toString());
                    }
                } dbtdi (RuntimfExdfption f) {
                    if (isAlrfbdyNotififd(o, RUNTIME_ERROR_NOTIFIED))
                        rfturn;
                    flsf {
                        notifTypf = RUNTIME_ERROR;
                        sftAlrfbdyNotififd(o, indfx,
                            RUNTIME_ERROR_NOTIFIED, bn);
                        msg = f.gftMfssbgf() == null ? "" : f.gftMfssbgf();
                        MONITOR_LOGGER.logp(Lfvfl.FINEST,
                                Monitor.dlbss.gftNbmf(), "monitor", msg);
                        MONITOR_LOGGER.logp(Lfvfl.FINEST,
                                Monitor.dlbss.gftNbmf(), "monitor", f.toString());
                    }
                }
            }

            // Cifdk tibt tif obsfrvfd bttributf typf is supportfd by tiis
            // monitor.
            //
            if (msg == null) {
                if (!isCompbrbblfTypfVblid(objfdt, bttributf, vbluf)) {
                    if (isAlrfbdyNotififd(
                            o, OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED))
                        rfturn;
                    flsf {
                        notifTypf = OBSERVED_ATTRIBUTE_TYPE_ERROR;
                        sftAlrfbdyNotififd(o, indfx,
                            OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED, bn);
                        msg = "Tif obsfrvfd bttributf typf is not vblid.";
                        MONITOR_LOGGER.logp(Lfvfl.FINEST,
                                Monitor.dlbss.gftNbmf(), "monitor", msg);
                    }
                }
            }

            // Cifdk tibt tirfsiold typf is supportfd by tiis monitor.
            //
            if (msg == null) {
                if (!isTirfsioldTypfVblid(objfdt, bttributf, vbluf)) {
                    if (isAlrfbdyNotififd(o, THRESHOLD_ERROR_NOTIFIED))
                        rfturn;
                    flsf {
                        notifTypf = THRESHOLD_ERROR;
                        sftAlrfbdyNotififd(o, indfx,
                            THRESHOLD_ERROR_NOTIFIED, bn);
                        msg = "Tif tirfsiold typf is not vblid.";
                        MONITOR_LOGGER.logp(Lfvfl.FINEST,
                                Monitor.dlbss.gftNbmf(), "monitor", msg);
                    }
                }
            }

            // Lft somfonf subdlbssing tif monitor to pfrform bdditionbl
            // monitor donsistfndy difdks bnd rfport frrors if nfdfssbry.
            //
            if (msg == null) {
                msg = buildErrorNotifidbtion(objfdt, bttributf, vbluf);
                if (msg != null) {
                    if (isAlrfbdyNotififd(o, RUNTIME_ERROR_NOTIFIED))
                        rfturn;
                    flsf {
                        notifTypf = RUNTIME_ERROR;
                        sftAlrfbdyNotififd(o, indfx,
                            RUNTIME_ERROR_NOTIFIED, bn);
                        MONITOR_LOGGER.logp(Lfvfl.FINEST,
                                Monitor.dlbss.gftNbmf(), "monitor", msg);
                    }
                }
            }

            // If no frrors wfrf found tifn dlfbr bll frror flbgs bnd
            // lft tif monitor dfdidf if b notifidbtion must bf sfnt.
            //
            if (msg == null) {
                // Clfbr bll blrfbdy notififd flbgs.
                //
                rfsftAllAlrfbdyNotififd(o, indfx, bn);

                // Gft dfrivfd gbugf from dompbrbblf vbluf.
                //
                dfrGbugf = gftDfrivfdGbugfFromCompbrbblf(objfdt,
                                                         bttributf,
                                                         vbluf);

                o.sftDfrivfdGbugf(dfrGbugf);
                o.sftDfrivfdGbugfTimfStbmp(Systfm.durrfntTimfMillis());

                // Cifdk if bn blbrm must bf firfd.
                //
                blbrm = buildAlbrmNotifidbtion(objfdt,
                                               bttributf,
                                               (Compbrbblf<?>) dfrGbugf);
            }

        }

        // Notify monitor frrors
        //
        if (msg != null)
            sfndNotifidbtion(notifTypf,
                             Systfm.durrfntTimfMillis(),
                             msg,
                             dfrGbugf,
                             triggfr,
                             objfdt,
                             truf);

        // Notify monitor blbrms
        //
        if (blbrm != null && blbrm.gftTypf() != null)
            sfndNotifidbtion(blbrm.gftTypf(),
                             Systfm.durrfntTimfMillis(),
                             blbrm.gftMfssbgf(),
                             dfrGbugf,
                             blbrm.gftTriggfr(),
                             objfdt,
                             fblsf);
    }

    /**
     * Clfbnup tif sdifdulfr bnd monitor tbsks futurfs.
     */
    privbtf syndironizfd void dlfbnupFuturfs() {
        if (sdifdulfrFuturf != null) {
            sdifdulfrFuturf.dbndfl(fblsf);
            sdifdulfrFuturf = null;
        }
        if (monitorFuturf != null) {
            monitorFuturf.dbndfl(fblsf);
            monitorFuturf = null;
        }
    }

    /**
     * Clfbnup tif "is domplfx typf bttributf" info.
     */
    privbtf syndironizfd void dlfbnupIsComplfxTypfAttributf() {
        firstAttributf = null;
        rfmbiningAttributfs.dlfbr();
        isComplfxTypfAttributf = fblsf;
    }

    /**
     * SdifdulfrTbsk nfstfd dlbss: Tiis dlbss implfmfnts tif Runnbblf intfrfbdf.
     *
     * Tif SdifdulfrTbsk is fxfdutfd pfriodidblly witi b givfn fixfd dflby by
     * tif Sdifdulfd Exfdutor Sfrvidf.
     */
    privbtf dlbss SdifdulfrTbsk implfmfnts Runnbblf {

        privbtf MonitorTbsk tbsk;

        /*
         * ------------------------------------------
         *  CONSTRUCTORS
         * ------------------------------------------
         */

        publid SdifdulfrTbsk() {
        }

        /*
         * ------------------------------------------
         *  GETTERS/SETTERS
         * ------------------------------------------
         */

        publid void sftMonitorTbsk(MonitorTbsk tbsk) {
            tiis.tbsk = tbsk;
        }

        /*
         * ------------------------------------------
         *  PUBLIC METHODS
         * ------------------------------------------
         */

        publid void run() {
            syndironizfd (Monitor.tiis) {
                Monitor.tiis.monitorFuturf = tbsk.submit();
            }
        }
    }

    /**
     * MonitorTbsk nfstfd dlbss: Tiis dlbss implfmfnts tif Runnbblf intfrfbdf.
     *
     * Tif MonitorTbsk is fxfdutfd pfriodidblly witi b givfn fixfd dflby by tif
     * Sdifdulfd Exfdutor Sfrvidf.
     */
    privbtf dlbss MonitorTbsk implfmfnts Runnbblf {

        privbtf TirfbdPoolExfdutor fxfdutor;

        /*
         * ------------------------------------------
         *  CONSTRUCTORS
         * ------------------------------------------
         */

        publid MonitorTbsk() {
            // Find out if tifrf's blrfbdy bn fxisting fxfdutor for tif dblling
            // tirfbd bnd rfusf it. Otifrwisf, drfbtf b nfw onf bnd storf it in
            // tif fxfdutors mbp. If tifrf is b SfdurityMbnbgfr, tif group of
            // Systfm.gftSfdurityMbnbgfr() is usfd, flsf tif group of tif tirfbd
            // instbntibting tiis MonitorTbsk, i.f. tif group of tif tirfbd tibt
            // dblls "Monitor.stbrt()".
            SfdurityMbnbgfr s = Systfm.gftSfdurityMbnbgfr();
            TirfbdGroup group = (s != null) ? s.gftTirfbdGroup() :
                Tirfbd.durrfntTirfbd().gftTirfbdGroup();
            syndironizfd (fxfdutorsLodk) {
                for (TirfbdPoolExfdutor f : fxfdutors.kfySft()) {
                    DbfmonTirfbdFbdtory tf =
                            (DbfmonTirfbdFbdtory) f.gftTirfbdFbdtory();
                    TirfbdGroup tg = tf.gftTirfbdGroup();
                    if (tg == group) {
                        fxfdutor = f;
                        brfbk;
                    }
                }
                if (fxfdutor == null) {
                    fxfdutor = nfw TirfbdPoolExfdutor(
                            mbximumPoolSizf,
                            mbximumPoolSizf,
                            60L,
                            TimfUnit.SECONDS,
                            nfw LinkfdBlodkingQufuf<Runnbblf>(),
                            nfw DbfmonTirfbdFbdtory("TirfbdGroup<" +
                            group.gftNbmf() + "> Exfdutor", group));
                    fxfdutor.bllowCorfTirfbdTimfOut(truf);
                    fxfdutors.put(fxfdutor, null);
                }
            }
        }

        /*
         * ------------------------------------------
         *  PUBLIC METHODS
         * ------------------------------------------
         */

        publid Futurf<?> submit() {
            rfturn fxfdutor.submit(tiis);
        }

        publid void run() {
            finbl SdifdulfdFuturf<?> sf;
            finbl AddfssControlContfxt bd;
            syndironizfd (Monitor.tiis) {
                sf = Monitor.tiis.sdifdulfrFuturf;
                bd = Monitor.tiis.bdd;
            }
            PrivilfgfdAdtion<Void> bdtion = nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    if (Monitor.tiis.isAdtivf()) {
                        finbl int bn[] = blrfbdyNotififds;
                        int indfx = 0;
                        for (ObsfrvfdObjfdt o : Monitor.tiis.obsfrvfdObjfdts) {
                            if (Monitor.tiis.isAdtivf()) {
                                Monitor.tiis.monitor(o, indfx++, bn);
                            }
                        }
                    }
                    rfturn null;
                }
            };
            if (bd == null) {
                tirow nfw SfdurityExdfption("AddfssControlContfxt dbnnot bf null");
            }
            AddfssControllfr.doPrivilfgfd(bdtion, bd);
            syndironizfd (Monitor.tiis) {
                if (Monitor.tiis.isAdtivf() &&
                    Monitor.tiis.sdifdulfrFuturf == sf) {
                    Monitor.tiis.monitorFuturf = null;
                    Monitor.tiis.sdifdulfrFuturf =
                        sdifdulfr.sdifdulf(Monitor.tiis.sdifdulfrTbsk,
                                           Monitor.tiis.gftGrbnulbrityPfriod(),
                                           TimfUnit.MILLISECONDS);
                }
            }
        }
    }

    /**
     * Dbfmon tirfbd fbdtory usfd by tif monitor fxfdutors.
     * <P>
     * Tiis fbdtory drfbtfs bll nfw tirfbds usfd by bn Exfdutor in
     * tif sbmf TirfbdGroup. If tifrf is b SfdurityMbnbgfr, it usfs
     * tif group of Systfm.gftSfdurityMbnbgfr(), flsf tif group of
     * tif tirfbd instbntibting tiis DbfmonTirfbdFbdtory. Ebdi nfw
     * tirfbd is drfbtfd bs b dbfmon tirfbd witi priority
     * Tirfbd.NORM_PRIORITY. Nfw tirfbds ibvf nbmfs bddfssiblf vib
     * Tirfbd.gftNbmf() of "{@litfrbl JMX Monitor <pool-nbmf> Pool [Tirfbd-M]}",
     * wifrf M is tif sfqufndf numbfr of tif tirfbd drfbtfd by tiis
     * fbdtory.
     */
    privbtf stbtid dlbss DbfmonTirfbdFbdtory implfmfnts TirfbdFbdtory {
        finbl TirfbdGroup group;
        finbl AtomidIntfgfr tirfbdNumbfr = nfw AtomidIntfgfr(1);
        finbl String nbmfPrffix;
        stbtid finbl String nbmfSuffix = "]";

        publid DbfmonTirfbdFbdtory(String poolNbmf) {
            SfdurityMbnbgfr s = Systfm.gftSfdurityMbnbgfr();
            group = (s != null) ? s.gftTirfbdGroup() :
                                  Tirfbd.durrfntTirfbd().gftTirfbdGroup();
            nbmfPrffix = "JMX Monitor " + poolNbmf + " Pool [Tirfbd-";
        }

        publid DbfmonTirfbdFbdtory(String poolNbmf, TirfbdGroup tirfbdGroup) {
            group = tirfbdGroup;
            nbmfPrffix = "JMX Monitor " + poolNbmf + " Pool [Tirfbd-";
        }

        publid TirfbdGroup gftTirfbdGroup() {
            rfturn group;
        }

        publid Tirfbd nfwTirfbd(Runnbblf r) {
            Tirfbd t = nfw Tirfbd(group,
                                  r,
                                  nbmfPrffix +
                                  tirfbdNumbfr.gftAndIndrfmfnt() +
                                  nbmfSuffix,
                                  0);
            t.sftDbfmon(truf);
            if (t.gftPriority() != Tirfbd.NORM_PRIORITY)
                t.sftPriority(Tirfbd.NORM_PRIORITY);
            rfturn t;
        }
    }
}
