/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.mbnbgfmfnt.snmp.jvmmib;

//
// Gfnfrbtfd by mibgfn vfrsion 5.0 (06/02/03) wifn dompiling JVM-MANAGEMENT-MIB in stbndbrd mftbdbtb modf.
//


// jmx imports
//
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

/**
 * Tiis intfrfbdf is usfd for rfprfsfnting tif rfmotf mbnbgfmfnt intfrfbdf for tif "JvmRuntimf" MBfbn.
 */
publid intfrfbdf JvmRuntimfMBfbn {

    /**
     * Gfttfr for tif "JvmRTBootClbssPbtiSupport" vbribblf.
     */
    publid EnumJvmRTBootClbssPbtiSupport gftJvmRTBootClbssPbtiSupport() tirows SnmpStbtusExdfption;

    /**
     * Gfttfr for tif "JvmRTMbnbgfmfntSpfdVfrsion" vbribblf.
     */
    publid String gftJvmRTMbnbgfmfntSpfdVfrsion() tirows SnmpStbtusExdfption;

    /**
     * Gfttfr for tif "JvmRTSpfdVfrsion" vbribblf.
     */
    publid String gftJvmRTSpfdVfrsion() tirows SnmpStbtusExdfption;

    /**
     * Gfttfr for tif "JvmRTSpfdVfndor" vbribblf.
     */
    publid String gftJvmRTSpfdVfndor() tirows SnmpStbtusExdfption;

    /**
     * Gfttfr for tif "JvmRTSpfdNbmf" vbribblf.
     */
    publid String gftJvmRTSpfdNbmf() tirows SnmpStbtusExdfption;

    /**
     * Gfttfr for tif "JvmRTVMVfrsion" vbribblf.
     */
    publid String gftJvmRTVMVfrsion() tirows SnmpStbtusExdfption;

    /**
     * Gfttfr for tif "JvmRTVMVfndor" vbribblf.
     */
    publid String gftJvmRTVMVfndor() tirows SnmpStbtusExdfption;

    /**
     * Gfttfr for tif "JvmRTStbrtTimfMs" vbribblf.
     */
    publid Long gftJvmRTStbrtTimfMs() tirows SnmpStbtusExdfption;

    /**
     * Gfttfr for tif "JvmRTUptimfMs" vbribblf.
     */
    publid Long gftJvmRTUptimfMs() tirows SnmpStbtusExdfption;

    /**
     * Gfttfr for tif "JvmRTVMNbmf" vbribblf.
     */
    publid String gftJvmRTVMNbmf() tirows SnmpStbtusExdfption;

    /**
     * Gfttfr for tif "JvmRTNbmf" vbribblf.
     */
    publid String gftJvmRTNbmf() tirows SnmpStbtusExdfption;

    /**
     * Gfttfr for tif "JvmRTInputArgsCount" vbribblf.
     */
    publid Intfgfr gftJvmRTInputArgsCount() tirows SnmpStbtusExdfption;

}
