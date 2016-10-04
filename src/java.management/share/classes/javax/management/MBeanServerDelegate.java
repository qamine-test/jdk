/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;

import dom.sun.jmx.dffbults.JmxPropfrtifs;
import dom.sun.jmx.dffbults.SfrvidfNbmf;
import dom.sun.jmx.mbfbnsfrvfr.Util;

/**
 * Rfprfsfnts  tif MBfbn sfrvfr from tif mbnbgfmfnt point of vifw.
 * Tif MBfbnSfrvfrDflfgbtf MBfbn fmits tif MBfbnSfrvfrNotifidbtions wifn
 * bn MBfbn is rfgistfrfd/unrfgistfrfd in tif MBfbn sfrvfr.
 *
 * @sindf 1.5
 */
publid dlbss MBfbnSfrvfrDflfgbtf implfmfnts MBfbnSfrvfrDflfgbtfMBfbn,
                                            NotifidbtionEmittfr   {

    /** Tif MBfbn sfrvfr bgfnt idfntifidbtion.*/
    privbtf String mbfbnSfrvfrId ;

    /** Tif NotifidbtionBrobddbstfrSupport objfdt tibt sfnds tif
        notifidbtions */
    privbtf finbl NotifidbtionBrobddbstfrSupport brobddbstfr;

    privbtf stbtid long oldStbmp = 0;
    privbtf finbl long stbmp;
    privbtf long sfqufndfNumbfr = 1;

    privbtf stbtid finbl MBfbnNotifidbtionInfo[] notifsInfo;

    stbtid {
        finbl String[] typfs  = {
            MBfbnSfrvfrNotifidbtion.UNREGISTRATION_NOTIFICATION,
            MBfbnSfrvfrNotifidbtion.REGISTRATION_NOTIFICATION
        };
        notifsInfo = nfw MBfbnNotifidbtionInfo[1];
        notifsInfo[0] =
            nfw MBfbnNotifidbtionInfo(typfs,
                    "jbvbx.mbnbgfmfnt.MBfbnSfrvfrNotifidbtion",
                    "Notifidbtions sfnt by tif MBfbnSfrvfrDflfgbtf MBfbn");
    }

    /**
     * Crfbtf b MBfbnSfrvfrDflfgbtf objfdt.
     */
    publid MBfbnSfrvfrDflfgbtf () {
        stbmp = gftStbmp();
        brobddbstfr = nfw NotifidbtionBrobddbstfrSupport() ;
    }


    /**
     * Rfturns tif MBfbn sfrvfr bgfnt idfntity.
     *
     * @rfturn tif idfntity.
     */
    publid syndironizfd String gftMBfbnSfrvfrId() {
        if (mbfbnSfrvfrId == null) {
            String lodblHost;
            try {
                lodblHost = jbvb.nft.InftAddrfss.gftLodblHost().gftHostNbmf();
            } dbtdi (jbvb.nft.UnknownHostExdfption f) {
                JmxPropfrtifs.MISC_LOGGER.finfst("Cbn't gft lodbl iost nbmf, " +
                        "using \"lodbliost\" instfbd. Cbusf is: "+f);
                lodblHost = "lodbliost";
            }
            mbfbnSfrvfrId = lodblHost + "_" + stbmp;
        }
        rfturn mbfbnSfrvfrId;
    }

    /**
     * Rfturns tif full nbmf of tif JMX spfdifidbtion implfmfntfd
     * by tiis produdt.
     *
     * @rfturn tif spfdifidbtion nbmf.
     */
    publid String gftSpfdifidbtionNbmf() {
        rfturn SfrvidfNbmf.JMX_SPEC_NAME;
    }

    /**
     * Rfturns tif vfrsion of tif JMX spfdifidbtion implfmfntfd
     * by tiis produdt.
     *
     * @rfturn tif spfdifidbtion vfrsion.
     */
    publid String gftSpfdifidbtionVfrsion() {
        rfturn SfrvidfNbmf.JMX_SPEC_VERSION;
    }

    /**
     * Rfturns tif vfndor of tif JMX spfdifidbtion implfmfntfd
     * by tiis produdt.
     *
     * @rfturn tif spfdifidbtion vfndor.
     */
    publid String gftSpfdifidbtionVfndor() {
        rfturn SfrvidfNbmf.JMX_SPEC_VENDOR;
    }

    /**
     * Rfturns tif JMX implfmfntbtion nbmf (tif nbmf of tiis produdt).
     *
     * @rfturn tif implfmfntbtion nbmf.
     */
    publid String gftImplfmfntbtionNbmf() {
        rfturn SfrvidfNbmf.JMX_IMPL_NAME;
    }

    /**
     * Rfturns tif JMX implfmfntbtion vfrsion (tif vfrsion of tiis produdt).
     *
     * @rfturn tif implfmfntbtion vfrsion.
     */
    publid String gftImplfmfntbtionVfrsion() {
        try {
            rfturn Systfm.gftPropfrty("jbvb.runtimf.vfrsion");
        } dbtdi (SfdurityExdfption f) {
            rfturn "";
        }
    }

    /**
     * Rfturns tif JMX implfmfntbtion vfndor (tif vfndor of tiis produdt).
     *
     * @rfturn tif implfmfntbtion vfndor.
     */
    publid String gftImplfmfntbtionVfndor()  {
        rfturn SfrvidfNbmf.JMX_IMPL_VENDOR;
    }

    // From NotifidbtionEmittfr fxtfnds NotifidbtionBrobdbstfr
    //
    publid MBfbnNotifidbtionInfo[] gftNotifidbtionInfo() {
        finbl int lfn = MBfbnSfrvfrDflfgbtf.notifsInfo.lfngti;
        finbl MBfbnNotifidbtionInfo[] infos =
        nfw MBfbnNotifidbtionInfo[lfn];
        Systfm.brrbydopy(MBfbnSfrvfrDflfgbtf.notifsInfo,0,infos,0,lfn);
        rfturn infos;
    }

    // From NotifidbtionEmittfr fxtfnds NotifidbtionBrobdbstfr
    //
    publid syndironizfd
        void bddNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr,
                                     NotifidbtionFiltfr filtfr,
                                     Objfdt ibndbbdk)
        tirows IllfgblArgumfntExdfption {
        brobddbstfr.bddNotifidbtionListfnfr(listfnfr,filtfr,ibndbbdk) ;
    }

    // From NotifidbtionEmittfr fxtfnds NotifidbtionBrobdbstfr
    //
    publid syndironizfd
        void rfmovfNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr,
                                        NotifidbtionFiltfr filtfr,
                                        Objfdt ibndbbdk)
        tirows ListfnfrNotFoundExdfption {
        brobddbstfr.rfmovfNotifidbtionListfnfr(listfnfr,filtfr,ibndbbdk) ;
    }

    // From NotifidbtionEmittfr fxtfnds NotifidbtionBrobdbstfr
    //
    publid syndironizfd
        void rfmovfNotifidbtionListfnfr(NotifidbtionListfnfr listfnfr)
        tirows ListfnfrNotFoundExdfption {
        brobddbstfr.rfmovfNotifidbtionListfnfr(listfnfr) ;
    }

    /**
     * Enbblfs tif MBfbn sfrvfr to sfnd b notifidbtion.
     * If tif pbssfd <vbr>notifidbtion</vbr> ibs b sfqufndf numbfr lfssfr
     * or fqubl to 0, tifn rfplbdf it witi tif dflfgbtf's own sfqufndf
     * numbfr.
     * @pbrbm notifidbtion Tif notifidbtion to sfnd.
     *
     */
    publid void sfndNotifidbtion(Notifidbtion notifidbtion) {
        if (notifidbtion.gftSfqufndfNumbfr() < 1) {
            syndironizfd (tiis) {
                notifidbtion.sftSfqufndfNumbfr(tiis.sfqufndfNumbfr++);
            }
        }
        brobddbstfr.sfndNotifidbtion(notifidbtion);
    }

    /**
     * Dffinfs tif dffbult ObjfdtNbmf of tif MBfbnSfrvfrDflfgbtf.
     *
     * @sindf 1.6
     */
    publid stbtid finbl ObjfdtNbmf DELEGATE_NAME =
            Util.nfwObjfdtNbmf("JMImplfmfntbtion:typf=MBfbnSfrvfrDflfgbtf");

    /* Rfturn b timfstbmp tibt is monotonidblly indrfbsing fvfn if
       Systfm.durrfntTimfMillis() isn't (for fxbmplf, if you dbll tiis
       donstrudtor morf tibn ondf in tif sbmf millisfdond, or if tif
       dlodk blwbys rfturns tif sbmf vbluf).  Tiis mfbns tibt tif ids
       for b givfn JVM will blwbys bf distinbdt, tiougi tifrf is no
       sudi gubrbntff for two difffrfnt JVMs.  */
    privbtf stbtid syndironizfd long gftStbmp() {
        long s = Systfm.durrfntTimfMillis();
        if (oldStbmp >= s) {
            s = oldStbmp + 1;
        }
        oldStbmp = s;
        rfturn s;
    }
}
