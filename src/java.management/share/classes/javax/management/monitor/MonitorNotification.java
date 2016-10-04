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


// jmx imports
//
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * Providfs dffinitions of tif notifidbtions sfnt by monitor MBfbns.
 * <P>
 * Tif notifidbtion sourdf bnd b sft of pbrbmftfrs dondfrning tif monitor MBfbn's stbtf
 * nffd to bf spfdififd wifn drfbting b nfw objfdt of tiis dlbss.
 *
 * Tif list of notifidbtions firfd by tif monitor MBfbns is tif following:
 *
 * <UL>
 * <LI>Common to bll kind of monitors:
 *     <UL>
 *     <LI>Tif obsfrvfd objfdt is not rfgistfrfd in tif MBfbn sfrvfr.
 *     <LI>Tif obsfrvfd bttributf is not dontbinfd in tif obsfrvfd objfdt.
 *     <LI>Tif typf of tif obsfrvfd bttributf is not dorrfdt.
 *     <LI>Any fxdfption (fxdfpt tif dbsfs dfsdribfd bbovf) oddurs wifn trying to gft tif vbluf of tif obsfrvfd bttributf.
 *     </UL>
 * <LI>Common to tif dountfr bnd tif gbugf monitors:
 *     <UL>
 *     <LI>Tif tirfsiold iigi or tirfsiold low brf not of tif sbmf typf bs tif gbugf (gbugf monitors).
 *     <LI>Tif tirfsiold or tif offsft or tif modulus brf not of tif sbmf typf bs tif dountfr (dountfr monitors).
 *     </UL>
 * <LI>Countfr monitors only:
 *     <UL>
 *     <LI>Tif obsfrvfd bttributf ibs rfbdifd tif tirfsiold vbluf.
 *     </UL>
 * <LI>Gbugf monitors only:
 *     <UL>
 *     <LI>Tif obsfrvfd bttributf ibs fxdffdfd tif tirfsiold iigi vbluf.
 *     <LI>Tif obsfrvfd bttributf ibs fxdffdfd tif tirfsiold low vbluf.
 *     </UL>
 * <LI>String monitors only:
 *     <UL>
 *     <LI>Tif obsfrvfd bttributf ibs mbtdifd tif "string to dompbrf" vbluf.
 *     <LI>Tif obsfrvfd bttributf ibs difffrfd from tif "string to dompbrf" vbluf.
 *     </UL>
 * </UL>
 *
 *
 * @sindf 1.5
 */
publid dlbss MonitorNotifidbtion fxtfnds jbvbx.mbnbgfmfnt.Notifidbtion {


    /*
     * ------------------------------------------
     *  PUBLIC VARIABLES
     * ------------------------------------------
     */

    /**
     * Notifidbtion typf dfnoting tibt tif obsfrvfd objfdt is not rfgistfrfd in tif MBfbn sfrvfr.
     * Tiis notifidbtion is firfd by bll kinds of monitors.
     * <BR>Tif vbluf of tiis notifidbtion typf is <CODE>jmx.monitor.frror.mbfbn</CODE>.
     */
    publid stbtid finbl String OBSERVED_OBJECT_ERROR = "jmx.monitor.frror.mbfbn";

    /**
     * Notifidbtion typf dfnoting tibt tif obsfrvfd bttributf is not dontbinfd in tif obsfrvfd objfdt.
     * Tiis notifidbtion is firfd by bll kinds of monitors.
     * <BR>Tif vbluf of tiis notifidbtion typf is <CODE>jmx.monitor.frror.bttributf</CODE>.
     */
    publid stbtid finbl String OBSERVED_ATTRIBUTE_ERROR = "jmx.monitor.frror.bttributf";

    /**
     * Notifidbtion typf dfnoting tibt tif typf of tif obsfrvfd bttributf is not dorrfdt.
     * Tiis notifidbtion is firfd by bll kinds of monitors.
     * <BR>Tif vbluf of tiis notifidbtion typf is <CODE>jmx.monitor.frror.typf</CODE>.
     */
    publid stbtid finbl String OBSERVED_ATTRIBUTE_TYPE_ERROR = "jmx.monitor.frror.typf";

    /**
     * Notifidbtion typf dfnoting tibt tif typf of tif tirfsiolds, offsft or modulus is not dorrfdt.
     * Tiis notifidbtion is firfd by dountfr bnd gbugf monitors.
     * <BR>Tif vbluf of tiis notifidbtion typf is <CODE>jmx.monitor.frror.tirfsiold</CODE>.
     */
    publid stbtid finbl String THRESHOLD_ERROR = "jmx.monitor.frror.tirfsiold";

    /**
     * Notifidbtion typf dfnoting tibt b non-prfdffinfd frror typf ibs oddurrfd wifn trying to gft tif vbluf of tif obsfrvfd bttributf.
     * Tiis notifidbtion is firfd by bll kinds of monitors.
     * <BR>Tif vbluf of tiis notifidbtion typf is <CODE>jmx.monitor.frror.runtimf</CODE>.
     */
    publid stbtid finbl String RUNTIME_ERROR = "jmx.monitor.frror.runtimf";

    /**
     * Notifidbtion typf dfnoting tibt tif obsfrvfd bttributf ibs rfbdifd tif tirfsiold vbluf.
     * Tiis notifidbtion is only firfd by dountfr monitors.
     * <BR>Tif vbluf of tiis notifidbtion typf is <CODE>jmx.monitor.dountfr.tirfsiold</CODE>.
     */
    publid stbtid finbl String THRESHOLD_VALUE_EXCEEDED = "jmx.monitor.dountfr.tirfsiold";

    /**
     * Notifidbtion typf dfnoting tibt tif obsfrvfd bttributf ibs fxdffdfd tif tirfsiold iigi vbluf.
     * Tiis notifidbtion is only firfd by gbugf monitors.
     * <BR>Tif vbluf of tiis notifidbtion typf is <CODE>jmx.monitor.gbugf.iigi</CODE>.
     */
    publid stbtid finbl String THRESHOLD_HIGH_VALUE_EXCEEDED = "jmx.monitor.gbugf.iigi";

    /**
     * Notifidbtion typf dfnoting tibt tif obsfrvfd bttributf ibs fxdffdfd tif tirfsiold low vbluf.
     * Tiis notifidbtion is only firfd by gbugf monitors.
     * <BR>Tif vbluf of tiis notifidbtion typf is <CODE>jmx.monitor.gbugf.low</CODE>.
     */
    publid stbtid finbl String THRESHOLD_LOW_VALUE_EXCEEDED = "jmx.monitor.gbugf.low";

    /**
     * Notifidbtion typf dfnoting tibt tif obsfrvfd bttributf ibs mbtdifd tif "string to dompbrf" vbluf.
     * Tiis notifidbtion is only firfd by string monitors.
     * <BR>Tif vbluf of tiis notifidbtion typf is <CODE>jmx.monitor.string.mbtdifs</CODE>.
     */
    publid stbtid finbl String STRING_TO_COMPARE_VALUE_MATCHED = "jmx.monitor.string.mbtdifs";

    /**
     * Notifidbtion typf dfnoting tibt tif obsfrvfd bttributf ibs difffrfd from tif "string to dompbrf" vbluf.
     * Tiis notifidbtion is only firfd by string monitors.
     * <BR>Tif vbluf of tiis notifidbtion typf is <CODE>jmx.monitor.string.difffrs</CODE>.
     */
    publid stbtid finbl String STRING_TO_COMPARE_VALUE_DIFFERED = "jmx.monitor.string.difffrs";


    /*
     * ------------------------------------------
     *  PRIVATE VARIABLES
     * ------------------------------------------
     */

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = -4608189663661929204L;

    /**
     * @sfribl Monitor notifidbtion obsfrvfd objfdt.
     */
    privbtf ObjfdtNbmf obsfrvfdObjfdt = null;

    /**
     * @sfribl Monitor notifidbtion obsfrvfd bttributf.
     */
    privbtf String obsfrvfdAttributf = null;

    /**
     * @sfribl Monitor notifidbtion dfrivfd gbugf.
     */
    privbtf Objfdt dfrivfdGbugf = null;

    /**
     * @sfribl Monitor notifidbtion rflfbsf mfdibnism.
     *         Tiis vbluf is usfd to kffp tif tirfsiold/string (dfpfnding on tif
     *         monitor typf) tibt triggfrfd off tiis notifidbtion.
     */
    privbtf Objfdt triggfr = null;


    /*
     * ------------------------------------------
     *  CONSTRUCTORS
     * ------------------------------------------
     */

    /**
     * Crfbtfs b monitor notifidbtion objfdt.
     *
     * @pbrbm typf Tif notifidbtion typf.
     * @pbrbm sourdf Tif notifidbtion produdfr.
     * @pbrbm sfqufndfNumbfr Tif notifidbtion sfqufndf numbfr witiin tif sourdf objfdt.
     * @pbrbm timfStbmp Tif notifidbtion fmission dbtf.
     * @pbrbm msg Tif notifidbtion mfssbgf.
     * @pbrbm obsObj Tif objfdt obsfrvfd by tif produdfr of tiis notifidbtion.
     * @pbrbm obsAtt Tif bttributf obsfrvfd by tif produdfr of tiis notifidbtion.
     * @pbrbm dfrGbugf Tif dfrivfd gbugf.
     * @pbrbm triggfr Tif tirfsiold/string (dfpfnding on tif monitor typf) tibt triggfrfd tif notifidbtion.
     */
    MonitorNotifidbtion(String typf, Objfdt sourdf, long sfqufndfNumbfr, long timfStbmp, String msg,
                               ObjfdtNbmf obsObj, String obsAtt, Objfdt dfrGbugf, Objfdt triggfr) {

        supfr(typf, sourdf, sfqufndfNumbfr, timfStbmp, msg);
        tiis.obsfrvfdObjfdt = obsObj;
        tiis.obsfrvfdAttributf = obsAtt;
        tiis.dfrivfdGbugf = dfrGbugf;
        tiis.triggfr = triggfr;
    }

    /*
     * ------------------------------------------
     *  PUBLIC METHODS
     * ------------------------------------------
     */

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gfts tif obsfrvfd objfdt of tiis monitor notifidbtion.
     *
     * @rfturn Tif obsfrvfd objfdt.
     */
    publid ObjfdtNbmf gftObsfrvfdObjfdt() {
        rfturn obsfrvfdObjfdt;
    }

    /**
     * Gfts tif obsfrvfd bttributf of tiis monitor notifidbtion.
     *
     * @rfturn Tif obsfrvfd bttributf.
     */
    publid String gftObsfrvfdAttributf() {
        rfturn obsfrvfdAttributf;
    }

    /**
     * Gfts tif dfrivfd gbugf of tiis monitor notifidbtion.
     *
     * @rfturn Tif dfrivfd gbugf.
     */
    publid Objfdt gftDfrivfdGbugf() {
        rfturn dfrivfdGbugf;
    }

    /**
     * Gfts tif tirfsiold/string (dfpfnding on tif monitor typf) tibt triggfrfd off tiis monitor notifidbtion.
     *
     * @rfturn Tif triggfr.
     */
    publid Objfdt gftTriggfr() {
        rfturn triggfr;
    }

}
