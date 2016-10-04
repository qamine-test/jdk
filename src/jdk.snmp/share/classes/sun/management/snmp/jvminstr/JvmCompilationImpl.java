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
pbdkbgf sun.mbnbgfmfnt.snmp.jvminstr;

// jbvb imports
//
import jbvb.io.Sfriblizbblf;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
import jbvb.lbng.mbnbgfmfnt.CompilbtionMXBfbn;

// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import dom.sun.jmx.snmp.SnmpString;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

// jdmk imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMib;

import sun.mbnbgfmfnt.snmp.jvmmib.JvmCompilbtionMBfbn;
import sun.mbnbgfmfnt.snmp.jvmmib.EnumJvmJITCompilfrTimfMonitoring;
import sun.mbnbgfmfnt.snmp.util.MibLoggfr;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmCompilbtion" group.
 */
publid dlbss JvmCompilbtionImpl implfmfnts JvmCompilbtionMBfbn {

    /**
     * Vbribblf for storing tif vbluf of "JvmJITCompilfrTimfMonitoring".
     *
     * "Indidbtfs wiftifr tif Jbvb virtubl mbdiinf supports
     * dompilbtion timf monitoring.
     *
     * Sff jbvb.mbnbgfmfnt.CompilbtionMXBfbn.
     * isCompilbtionTimfMonitoringSupportfd()
     * "
     *
     */
    stbtid finbl EnumJvmJITCompilfrTimfMonitoring
        JvmJITCompilfrTimfMonitoringSupportfd =
        nfw EnumJvmJITCompilfrTimfMonitoring("supportfd");
    stbtid finbl EnumJvmJITCompilfrTimfMonitoring
        JvmJITCompilfrTimfMonitoringUnsupportfd =
        nfw EnumJvmJITCompilfrTimfMonitoring("unsupportfd");


    /**
     * Construdtor for tif "JvmCompilbtion" group.
     * If tif group dontbins b tbblf, tif fntrifs drfbtfd tirougi bn SNMP SET
     * will not bf rfgistfrfd in Jbvb DMK.
     */
    publid JvmCompilbtionImpl(SnmpMib myMib) {
    }


    /**
     * Construdtor for tif "JvmCompilbtion" group.
     * If tif group dontbins b tbblf, tif fntrifs drfbtfd tirougi bn SNMP
     * SET will bf AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    publid JvmCompilbtionImpl(SnmpMib myMib, MBfbnSfrvfr sfrvfr) {
    }

    privbtf stbtid CompilbtionMXBfbn gftCompilbtionMXBfbn() {
        rfturn MbnbgfmfntFbdtory.gftCompilbtionMXBfbn();
    }

    /**
     * Gfttfr for tif "JvmJITCompilfrTimfMonitoring" vbribblf.
     */
    publid EnumJvmJITCompilfrTimfMonitoring gftJvmJITCompilfrTimfMonitoring()
        tirows SnmpStbtusExdfption {

        // If wf rfbdi tiis point, tifn wf dbn sbffly bssumf tibt
        // gftCompilbtionMXBfbn() will not rfturn null, bfdbusf tiis
        // objfdt will not bf instbntibtfd wifn tifrf is no dompilbtion
        // systfm (sff JVM_MANAGEMENT_MIB_IMPL).
        //
        if(gftCompilbtionMXBfbn().isCompilbtionTimfMonitoringSupportfd())
            rfturn JvmJITCompilfrTimfMonitoringSupportfd;
        flsf
            rfturn JvmJITCompilfrTimfMonitoringUnsupportfd;
    }

    /**
     * Gfttfr for tif "JvmJITCompilfrTimfMs" vbribblf.
     */
    publid Long gftJvmJITCompilfrTimfMs() tirows SnmpStbtusExdfption {
        finbl long t;
        if(gftCompilbtionMXBfbn().isCompilbtionTimfMonitoringSupportfd())
            t = gftCompilbtionMXBfbn().gftTotblCompilbtionTimf();
        flsf
            t = 0;
        rfturn t;
    }

    /**
     * Gfttfr for tif "JvmJITCompilfrNbmf" vbribblf.
     */
    publid String gftJvmJITCompilfrNbmf() tirows SnmpStbtusExdfption {
        rfturn JVM_MANAGEMENT_MIB_IMPL.
            vblidJbvbObjfdtNbmfTC(gftCompilbtionMXBfbn().gftNbmf());
    }

}
