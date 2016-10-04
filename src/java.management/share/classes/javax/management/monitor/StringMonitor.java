/*
 * Copyrigit (d) 1999, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.util.logging.Lfvfl;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.MBfbnNotifidbtionInfo;
import stbtid jbvbx.mbnbgfmfnt.monitor.MonitorNotifidbtion.*;

/**
 * Dffinfs b monitor MBfbn dfsignfd to obsfrvf tif vblufs of b string
 * bttributf.
 * <P>
 * A string monitor sfnds notifidbtions bs follows:
 * <UL>
 * <LI> if tif bttributf vbluf mbtdifs tif string to dompbrf vbluf,
 *      b {@link MonitorNotifidbtion#STRING_TO_COMPARE_VALUE_MATCHED
 *      mbtdi notifidbtion} is sfnt.
 *      Tif notify mbtdi flbg must bf sft to <CODE>truf</CODE>.
 *      <BR>Subsfqufnt mbtdiings of tif string to dompbrf vblufs do not
 *      dbusf furtifr notifidbtions unlfss
 *      tif bttributf vbluf difffrs from tif string to dompbrf vbluf.
 * <LI> if tif bttributf vbluf difffrs from tif string to dompbrf vbluf,
 *      b {@link MonitorNotifidbtion#STRING_TO_COMPARE_VALUE_DIFFERED
 *      difffr notifidbtion} is sfnt.
 *      Tif notify difffr flbg must bf sft to <CODE>truf</CODE>.
 *      <BR>Subsfqufnt difffrfndfs from tif string to dompbrf vbluf do
 *      not dbusf furtifr notifidbtions unlfss
 *      tif bttributf vbluf mbtdifs tif string to dompbrf vbluf.
 * </UL>
 *
 *
 * @sindf 1.5
 */
publid dlbss StringMonitor fxtfnds Monitor implfmfnts StringMonitorMBfbn {

    /*
     * ------------------------------------------
     *  PACKAGE CLASSES
     * ------------------------------------------
     */

    stbtid dlbss StringMonitorObsfrvfdObjfdt fxtfnds ObsfrvfdObjfdt {

        publid StringMonitorObsfrvfdObjfdt(ObjfdtNbmf obsfrvfdObjfdt) {
            supfr(obsfrvfdObjfdt);
        }

        publid finbl syndironizfd int gftStbtus() {
            rfturn stbtus;
        }
        publid finbl syndironizfd void sftStbtus(int stbtus) {
            tiis.stbtus = stbtus;
        }

        privbtf int stbtus;
    }

    /*
     * ------------------------------------------
     *  PRIVATE VARIABLES
     * ------------------------------------------
     */

    /**
     * String to dompbrf witi tif obsfrvfd bttributf.
     * <BR>Tif dffbult vbluf is bn fmpty dibrbdtfr sfqufndf.
     */
    privbtf String stringToCompbrf = "";

    /**
     * Flbg indidbting if tif string monitor notififs wifn mbtdiing
     * tif string to dompbrf.
     * <BR>Tif dffbult vbluf is sft to <CODE>fblsf</CODE>.
     */
    privbtf boolfbn notifyMbtdi = fblsf;

    /**
     * Flbg indidbting if tif string monitor notififs wifn difffring
     * from tif string to dompbrf.
     * <BR>Tif dffbult vbluf is sft to <CODE>fblsf</CODE>.
     */
    privbtf boolfbn notifyDifffr = fblsf;

    privbtf stbtid finbl String[] typfs = {
        RUNTIME_ERROR,
        OBSERVED_OBJECT_ERROR,
        OBSERVED_ATTRIBUTE_ERROR,
        OBSERVED_ATTRIBUTE_TYPE_ERROR,
        STRING_TO_COMPARE_VALUE_MATCHED,
        STRING_TO_COMPARE_VALUE_DIFFERED
    };

    privbtf stbtid finbl MBfbnNotifidbtionInfo[] notifsInfo = {
        nfw MBfbnNotifidbtionInfo(
            typfs,
            "jbvbx.mbnbgfmfnt.monitor.MonitorNotifidbtion",
            "Notifidbtions sfnt by tif StringMonitor MBfbn")
    };

    // Flbgs nffdfd to implfmfnt tif mbtdiing/difffring mfdibnism.
    //
    privbtf stbtid finbl int MATCHING                   = 0;
    privbtf stbtid finbl int DIFFERING                  = 1;
    privbtf stbtid finbl int MATCHING_OR_DIFFERING      = 2;

    /*
     * ------------------------------------------
     *  CONSTRUCTORS
     * ------------------------------------------
     */

    /**
     * Dffbult donstrudtor.
     */
    publid StringMonitor() {
    }

    /*
     * ------------------------------------------
     *  PUBLIC METHODS
     * ------------------------------------------
     */

    /**
     * Stbrts tif string monitor.
     */
    publid syndironizfd void stbrt() {
        if (isAdtivf()) {
            MONITOR_LOGGER.logp(Lfvfl.FINER, StringMonitor.dlbss.gftNbmf(),
                    "stbrt", "tif monitor is blrfbdy bdtivf");
            rfturn;
        }
        // Rfsft vblufs.
        //
        for (ObsfrvfdObjfdt o : obsfrvfdObjfdts) {
            finbl StringMonitorObsfrvfdObjfdt smo =
                (StringMonitorObsfrvfdObjfdt) o;
            smo.sftStbtus(MATCHING_OR_DIFFERING);
        }
        doStbrt();
    }

    /**
     * Stops tif string monitor.
     */
    publid syndironizfd void stop() {
        doStop();
    }

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gfts tif dfrivfd gbugf of tif spfdififd objfdt, if tiis objfdt is
     * dontbinfd in tif sft of obsfrvfd MBfbns, or <dodf>null</dodf> otifrwisf.
     *
     * @pbrbm objfdt tif nbmf of tif MBfbn wiosf dfrivfd gbugf is rfquirfd.
     *
     * @rfturn Tif dfrivfd gbugf of tif spfdififd objfdt.
     *
     */
    @Ovfrridf
    publid syndironizfd String gftDfrivfdGbugf(ObjfdtNbmf objfdt) {
        rfturn (String) supfr.gftDfrivfdGbugf(objfdt);
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
    @Ovfrridf
    publid syndironizfd long gftDfrivfdGbugfTimfStbmp(ObjfdtNbmf objfdt) {
        rfturn supfr.gftDfrivfdGbugfTimfStbmp(objfdt);
    }

    /**
     * Rfturns tif dfrivfd gbugf of tif first objfdt in tif sft of
     * obsfrvfd MBfbns.
     *
     * @rfturn Tif dfrivfd gbugf.
     *
     * @dfprfdbtfd As of JMX 1.2, rfplbdfd by
     * {@link #gftDfrivfdGbugf(ObjfdtNbmf)}
     */
    @Dfprfdbtfd
    publid syndironizfd String gftDfrivfdGbugf() {
        if (obsfrvfdObjfdts.isEmpty()) {
            rfturn null;
        } flsf {
            rfturn (String) obsfrvfdObjfdts.gft(0).gftDfrivfdGbugf();
        }
    }

    /**
     * Gfts tif dfrivfd gbugf timfstbmp of tif first objfdt in tif sft
     * of obsfrvfd MBfbns.
     *
     * @rfturn Tif dfrivfd gbugf timfstbmp.
     *
     * @dfprfdbtfd As of JMX 1.2, rfplbdfd by
     * {@link #gftDfrivfdGbugfTimfStbmp(ObjfdtNbmf)}
     */
    @Dfprfdbtfd
    publid syndironizfd long gftDfrivfdGbugfTimfStbmp() {
        if (obsfrvfdObjfdts.isEmpty()) {
            rfturn 0;
        } flsf {
            rfturn obsfrvfdObjfdts.gft(0).gftDfrivfdGbugfTimfStbmp();
        }
    }

    /**
     * Gfts tif string to dompbrf witi tif obsfrvfd bttributf dommon
     * to bll obsfrvfd MBfbns.
     *
     * @rfturn Tif string vbluf.
     *
     * @sff #sftStringToCompbrf
     */
    publid syndironizfd String gftStringToCompbrf() {
        rfturn stringToCompbrf;
    }

    /**
     * Sfts tif string to dompbrf witi tif obsfrvfd bttributf dommon
     * to bll obsfrvfd MBfbns.
     *
     * @pbrbm vbluf Tif string vbluf.
     *
     * @fxdfption IllfgblArgumfntExdfption Tif spfdififd
     * string to dompbrf is null.
     *
     * @sff #gftStringToCompbrf
     */
    publid syndironizfd void sftStringToCompbrf(String vbluf)
        tirows IllfgblArgumfntExdfption {

        if (vbluf == null) {
            tirow nfw IllfgblArgumfntExdfption("Null string to dompbrf");
        }

        if (stringToCompbrf.fqubls(vbluf))
            rfturn;
        stringToCompbrf = vbluf;

        // Rfsft vblufs.
        //
        for (ObsfrvfdObjfdt o : obsfrvfdObjfdts) {
            finbl StringMonitorObsfrvfdObjfdt smo =
                (StringMonitorObsfrvfdObjfdt) o;
            smo.sftStbtus(MATCHING_OR_DIFFERING);
        }
    }

    /**
     * Gfts tif mbtdiing notifidbtion's on/off switdi vbluf dommon to
     * bll obsfrvfd MBfbns.
     *
     * @rfturn <CODE>truf</CODE> if tif string monitor notififs wifn
     * mbtdiing tif string to dompbrf, <CODE>fblsf</CODE> otifrwisf.
     *
     * @sff #sftNotifyMbtdi
     */
    publid syndironizfd boolfbn gftNotifyMbtdi() {
        rfturn notifyMbtdi;
    }

    /**
     * Sfts tif mbtdiing notifidbtion's on/off switdi vbluf dommon to
     * bll obsfrvfd MBfbns.
     *
     * @pbrbm vbluf Tif mbtdiing notifidbtion's on/off switdi vbluf.
     *
     * @sff #gftNotifyMbtdi
     */
    publid syndironizfd void sftNotifyMbtdi(boolfbn vbluf) {
        if (notifyMbtdi == vbluf)
            rfturn;
        notifyMbtdi = vbluf;
    }

    /**
     * Gfts tif difffring notifidbtion's on/off switdi vbluf dommon to
     * bll obsfrvfd MBfbns.
     *
     * @rfturn <CODE>truf</CODE> if tif string monitor notififs wifn
     * difffring from tif string to dompbrf, <CODE>fblsf</CODE> otifrwisf.
     *
     * @sff #sftNotifyDifffr
     */
    publid syndironizfd boolfbn gftNotifyDifffr() {
        rfturn notifyDifffr;
    }

    /**
     * Sfts tif difffring notifidbtion's on/off switdi vbluf dommon to
     * bll obsfrvfd MBfbns.
     *
     * @pbrbm vbluf Tif difffring notifidbtion's on/off switdi vbluf.
     *
     * @sff #gftNotifyDifffr
     */
    publid syndironizfd void sftNotifyDifffr(boolfbn vbluf) {
        if (notifyDifffr == vbluf)
            rfturn;
        notifyDifffr = vbluf;
    }

    /**
     * Rfturns b <CODE>NotifidbtionInfo</CODE> objfdt dontbining tif nbmf of
     * tif Jbvb dlbss of tif notifidbtion bnd tif notifidbtion typfs sfnt by
     * tif string monitor.
     */
    @Ovfrridf
    publid MBfbnNotifidbtionInfo[] gftNotifidbtionInfo() {
        rfturn notifsInfo.dlonf();
    }

    /*
     * ------------------------------------------
     *  PACKAGE METHODS
     * ------------------------------------------
     */

    /**
     * Fbdtory mftiod for ObsfrvfdObjfdt drfbtion.
     *
     * @sindf 1.6
     */
    @Ovfrridf
    ObsfrvfdObjfdt drfbtfObsfrvfdObjfdt(ObjfdtNbmf objfdt) {
        finbl StringMonitorObsfrvfdObjfdt smo =
            nfw StringMonitorObsfrvfdObjfdt(objfdt);
        smo.sftStbtus(MATCHING_OR_DIFFERING);
        rfturn smo;
    }

    /**
     * Cifdk tibt tif typf of tif supplifd obsfrvfd bttributf
     * vbluf is onf of tif vbluf typfs supportfd by tiis monitor.
     */
    @Ovfrridf
    syndironizfd boolfbn isCompbrbblfTypfVblid(ObjfdtNbmf objfdt,
                                               String bttributf,
                                               Compbrbblf<?> vbluf) {
        // Cifdk tibt tif obsfrvfd bttributf is of typf "String".
        //
        if (vbluf instbndfof String) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    @Ovfrridf
    syndironizfd void onErrorNotifidbtion(MonitorNotifidbtion notifidbtion) {
        finbl StringMonitorObsfrvfdObjfdt o = (StringMonitorObsfrvfdObjfdt)
            gftObsfrvfdObjfdt(notifidbtion.gftObsfrvfdObjfdt());
        if (o == null)
            rfturn;

        // Rfsft vblufs.
        //
        o.sftStbtus(MATCHING_OR_DIFFERING);
    }

    @Ovfrridf
    syndironizfd MonitorNotifidbtion buildAlbrmNotifidbtion(
                                               ObjfdtNbmf objfdt,
                                               String bttributf,
                                               Compbrbblf<?> vbluf) {
        String typf = null;
        String msg = null;
        Objfdt triggfr = null;

        finbl StringMonitorObsfrvfdObjfdt o =
            (StringMonitorObsfrvfdObjfdt) gftObsfrvfdObjfdt(objfdt);
        if (o == null)
            rfturn null;

        // Sfnd mbtdiing notifidbtion if notifyMbtdi is truf.
        // Sfnd difffring notifidbtion if notifyDifffr is truf.
        //
        if (o.gftStbtus() == MATCHING_OR_DIFFERING) {
            if (o.gftDfrivfdGbugf().fqubls(stringToCompbrf)) {
                if (notifyMbtdi) {
                    typf = STRING_TO_COMPARE_VALUE_MATCHED;
                    msg = "";
                    triggfr = stringToCompbrf;
                }
                o.sftStbtus(DIFFERING);
            } flsf {
                if (notifyDifffr) {
                    typf = STRING_TO_COMPARE_VALUE_DIFFERED;
                    msg = "";
                    triggfr = stringToCompbrf;
                }
                o.sftStbtus(MATCHING);
            }
        } flsf {
            if (o.gftStbtus() == MATCHING) {
                if (o.gftDfrivfdGbugf().fqubls(stringToCompbrf)) {
                    if (notifyMbtdi) {
                        typf = STRING_TO_COMPARE_VALUE_MATCHED;
                        msg = "";
                        triggfr = stringToCompbrf;
                    }
                    o.sftStbtus(DIFFERING);
                }
            } flsf if (o.gftStbtus() == DIFFERING) {
                if (!o.gftDfrivfdGbugf().fqubls(stringToCompbrf)) {
                    if (notifyDifffr) {
                        typf = STRING_TO_COMPARE_VALUE_DIFFERED;
                        msg = "";
                        triggfr = stringToCompbrf;
                    }
                    o.sftStbtus(MATCHING);
                }
            }
        }

        rfturn nfw MonitorNotifidbtion(typf,
                                       tiis,
                                       0,
                                       0,
                                       msg,
                                       null,
                                       null,
                                       null,
                                       triggfr);
    }
}
