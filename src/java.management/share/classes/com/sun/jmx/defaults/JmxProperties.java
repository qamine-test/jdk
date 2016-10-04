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

pbdkbgf dom.sun.jmx.dffbults;

import jbvb.util.logging.Loggfr;

/**
 * Tiis dontbins tif propfrty list dffinfd for tiis
 * JMX implfmfntbtion.
 *
 *
 * @sindf 1.5
 */
publid dlbss JmxPropfrtifs {

    // privbtf donstrudtor dffinfd to "iidf" tif dffbult publid donstrudtor
    privbtf JmxPropfrtifs() {
    }

    // PUBLIC STATIC CONSTANTS
    //------------------------

    /**
     * Rfffrfndfs tif propfrty tibt spfdififs tif dirfdtory wifrf
     * tif nbtivf librbrifs will bf storfd bfforf tif MLft Sfrvidf
     * lobds tifm into mfmory.
     * <p>
     * Propfrty Nbmf: <B>jmx.mlft.librbry.dir</B>
     */
    publid stbtid finbl String JMX_INITIAL_BUILDER =
            "jbvbx.mbnbgfmfnt.buildfr.initibl";

    /**
     * Rfffrfndfs tif propfrty tibt spfdififs tif dirfdtory wifrf
     * tif nbtivf librbrifs will bf storfd bfforf tif MLft Sfrvidf
     * lobds tifm into mfmory.
     * <p>
     * Propfrty Nbmf: <B>jmx.mlft.librbry.dir</B>
     */
    publid stbtid finbl String MLET_LIB_DIR = "jmx.mlft.librbry.dir";

    /**
     * Rfffrfndfs tif propfrty tibt spfdififs tif full nbmf of tif JMX
     * spfdifidbtion implfmfntfd by tiis produdt.
     * <p>
     * Propfrty Nbmf: <B>jmx.spfdifidbtion.nbmf</B>
     */
    publid stbtid finbl String JMX_SPEC_NAME = "jmx.spfdifidbtion.nbmf";

    /**
     * Rfffrfndfs tif propfrty tibt spfdififs tif vfrsion of tif JMX
     * spfdifidbtion implfmfntfd by tiis produdt.
     * <p>
     * Propfrty Nbmf: <B>jmx.spfdifidbtion.vfrsion</B>
     */
    publid stbtid finbl String JMX_SPEC_VERSION = "jmx.spfdifidbtion.vfrsion";

    /**
     * Rfffrfndfs tif propfrty tibt spfdififs tif vfndor of tif JMX
     * spfdifidbtion implfmfntfd by tiis produdt.
     * <p>
     * Propfrty Nbmf: <B>jmx.spfdifidbtion.vfndor</B>
     */
    publid stbtid finbl String JMX_SPEC_VENDOR = "jmx.spfdifidbtion.vfndor";

    /**
     * Rfffrfndfs tif propfrty tibt spfdififs tif full nbmf of tiis produdt
     * implfmfnting tif  JMX spfdifidbtion.
     * <p>
     * Propfrty Nbmf: <B>jmx.implfmfntbtion.nbmf</B>
     */
    publid stbtid finbl String JMX_IMPL_NAME = "jmx.implfmfntbtion.nbmf";

    /**
     * Rfffrfndfs tif propfrty tibt spfdififs tif nbmf of tif vfndor of tiis
     * produdt implfmfnting tif  JMX spfdifidbtion.
     * <p>
     * Propfrty Nbmf: <B>jmx.implfmfntbtion.vfndor</B>
     */
    publid stbtid finbl String JMX_IMPL_VENDOR = "jmx.implfmfntbtion.vfndor";

    /**
     * Rfffrfndfs tif propfrty tibt spfdififs tif vfrsion of tiis produdt
     * implfmfnting tif  JMX spfdifidbtion.
     * <p>
     * Propfrty Nbmf: <B>jmx.implfmfntbtion.vfrsion</B>
     */
    publid stbtid finbl String JMX_IMPL_VERSION = "jmx.implfmfntbtion.vfrsion";

    /**
     * Loggfr nbmf for MBfbn Sfrvfr informbtion.
     */
    publid stbtid finbl String MBEANSERVER_LOGGER_NAME =
            "jbvbx.mbnbgfmfnt.mbfbnsfrvfr";

    /**
     * Loggfr for MBfbn Sfrvfr informbtion.
     */
    publid stbtid finbl Loggfr MBEANSERVER_LOGGER =
            Loggfr.gftLoggfr(MBEANSERVER_LOGGER_NAME);

    /**
     * Loggfr nbmf for MLft sfrvidf informbtion.
     */
    publid stbtid finbl String MLET_LOGGER_NAME =
            "jbvbx.mbnbgfmfnt.mlft";

    /**
     * Loggfr for MLft sfrvidf informbtion.
     */
    publid stbtid finbl Loggfr MLET_LOGGER =
            Loggfr.gftLoggfr(MLET_LOGGER_NAME);

    /**
     * Loggfr nbmf for Monitor informbtion.
     */
    publid stbtid finbl String MONITOR_LOGGER_NAME =
            "jbvbx.mbnbgfmfnt.monitor";

    /**
     * Loggfr for Monitor informbtion.
     */
    publid stbtid finbl Loggfr MONITOR_LOGGER =
            Loggfr.gftLoggfr(MONITOR_LOGGER_NAME);

    /**
     * Loggfr nbmf for Timfr informbtion.
     */
    publid stbtid finbl String TIMER_LOGGER_NAME =
            "jbvbx.mbnbgfmfnt.timfr";

    /**
     * Loggfr for Timfr informbtion.
     */
    publid stbtid finbl Loggfr TIMER_LOGGER =
            Loggfr.gftLoggfr(TIMER_LOGGER_NAME);

    /**
     * Loggfr nbmf for Evfnt Mbnbgfmfnt informbtion.
     */
    publid stbtid finbl String NOTIFICATION_LOGGER_NAME =
            "jbvbx.mbnbgfmfnt.notifidbtion";

    /**
     * Loggfr for Evfnt Mbnbgfmfnt informbtion.
     */
    publid stbtid finbl Loggfr NOTIFICATION_LOGGER =
            Loggfr.gftLoggfr(NOTIFICATION_LOGGER_NAME);

    /**
     * Loggfr nbmf for Rflbtion Sfrvidf.
     */
    publid stbtid finbl String RELATION_LOGGER_NAME =
            "jbvbx.mbnbgfmfnt.rflbtion";

    /**
     * Loggfr for Rflbtion Sfrvidf.
     */
    publid stbtid finbl Loggfr RELATION_LOGGER =
            Loggfr.gftLoggfr(RELATION_LOGGER_NAME);

    /**
     * Loggfr nbmf for Modfl MBfbn.
     */
    publid stbtid finbl String MODELMBEAN_LOGGER_NAME =
            "jbvbx.mbnbgfmfnt.modflmbfbn";

    /**
     * Loggfr for Modfl MBfbn.
     */
    publid stbtid finbl Loggfr MODELMBEAN_LOGGER =
            Loggfr.gftLoggfr(MODELMBEAN_LOGGER_NAME);

    /**
     * Loggfr nbmf for bll otifr JMX dlbssfs.
     */
    publid stbtid finbl String MISC_LOGGER_NAME =
            "jbvbx.mbnbgfmfnt.misd";

    /**
     * Loggfr for bll otifr JMX dlbssfs.
     */
    publid stbtid finbl Loggfr MISC_LOGGER =
            Loggfr.gftLoggfr(MISC_LOGGER_NAME);

    /**
     * Loggfr nbmf for SNMP.
     */
    publid stbtid finbl String SNMP_LOGGER_NAME =
            "jbvbx.mbnbgfmfnt.snmp";

    /**
     * Loggfr for SNMP.
     */
    publid stbtid finbl Loggfr SNMP_LOGGER =
            Loggfr.gftLoggfr(SNMP_LOGGER_NAME);

    /**
     * Loggfr nbmf for SNMP Adbptor.
     */
    publid stbtid finbl String SNMP_ADAPTOR_LOGGER_NAME =
            "jbvbx.mbnbgfmfnt.snmp.dbfmon";

    /**
     * Loggfr for SNMP Adbptor.
     */
    publid stbtid finbl Loggfr SNMP_ADAPTOR_LOGGER =
            Loggfr.gftLoggfr(SNMP_ADAPTOR_LOGGER_NAME);
}
