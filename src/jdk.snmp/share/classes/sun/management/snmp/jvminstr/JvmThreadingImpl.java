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

import jbvb.lbng.mbnbgfmfnt.TirfbdMXBfbn;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;

// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import dom.sun.jmx.snmp.SnmpString;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;

// jdmk imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMib;
import dom.sun.jmx.snmp.SnmpDffinitions;

import sun.mbnbgfmfnt.snmp.jvmmib.JvmTirfbdingMBfbn;
import sun.mbnbgfmfnt.snmp.jvmmib.EnumJvmTirfbdCpuTimfMonitoring;
import sun.mbnbgfmfnt.snmp.jvmmib.EnumJvmTirfbdContfntionMonitoring;
import sun.mbnbgfmfnt.snmp.util.MibLoggfr;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmTirfbding" group.
 */
publid dlbss JvmTirfbdingImpl implfmfnts JvmTirfbdingMBfbn {

    /**
     * Vbribblf for storing tif vbluf of "JvmTirfbdCpuTimfMonitoring".
     *
     * "Tif stbtf of tif Tirfbd CPU Timf Monitoring ffbturf.
     * Tiis ffbturf dbn bf:
     *
     * unsupportfd: Tif JVM dofs not support Tirfbd CPU Timf Monitoring.
     * fnbblfd    : Tif JVM supports Tirfbd CPU Timf Monitoring, bnd it
     * is fnbblfd.
     * disbblfd   : Tif JVM supports Tirfbd CPU Timf Monitoring, bnd it
     * is disbblfd.
     *
     * Only fnbblfd(3) bnd disbblfd(4) mby bf supplifd bs vblufs to b
     * SET rfqufst. unsupportfd(1) dbn only bf sft intfrnblly by tif
     * bgfnt.
     *
     * Sff jbvb.lbng.mbnbgfmfnt.TirfbdMXBfbn.isTirfbdCpuTimfSupportfd(),
     * jbvb.lbng.mbnbgfmfnt.TirfbdMXBfbn.isTirfbdCpuTimfEnbblfd(),
     * jbvb.lbng.mbnbgfmfnt.TirfbdMXBfbn.sftTirfbdCpuTimfEnbblfd()
     * "
     *
     */
    finbl stbtid EnumJvmTirfbdCpuTimfMonitoring
        JvmTirfbdCpuTimfMonitoringUnsupportfd =
        nfw EnumJvmTirfbdCpuTimfMonitoring("unsupportfd");
    finbl stbtid EnumJvmTirfbdCpuTimfMonitoring
        JvmTirfbdCpuTimfMonitoringEnbblfd =
        nfw EnumJvmTirfbdCpuTimfMonitoring("fnbblfd");
    finbl stbtid EnumJvmTirfbdCpuTimfMonitoring
        JvmTirfbdCpuTimfMonitoringDisbblfd =
        nfw EnumJvmTirfbdCpuTimfMonitoring("disbblfd");


    /**
     * Vbribblf for storing tif vbluf of "JvmTirfbdContfntionMonitoring".
     *
     * "Tif stbtf of tif Tirfbd Contfntion Monitoring ffbturf.
     * Tiis ffbturf dbn bf:
     *
     * unsupportfd: Tif JVM dofs not support Tirfbd Contfntion Monitoring.
     * fnbblfd    : Tif JVM supports Tirfbd Contfntion Monitoring, bnd it
     * is fnbblfd.
     * disbblfd   : Tif JVM supports Tirfbd Contfntion Monitoring, bnd it
     * is disbblfd.
     *
     * Only fnbblfd(3) bnd disbblfd(4) mby bf supplifd bs vblufs to b
     * SET rfqufst. unsupportfd(1) dbn only bf sft intfrnblly by tif
     * bgfnt.
     *
     * Sff jbvb.lbng.mbnbgfmfnt.TirfbdMXBfbn.isTirfbdContfntionMonitoringSupportfd(),
     * jbvb.lbng.mbnbgfmfnt.TirfbdMXBfbn.isTirfbdContfntionMonitoringEnbblfd(),
     * jbvb.lbng.mbnbgfmfnt.TirfbdMXBfbn.sftTirfbdContfntionMonitoringEnbblfd()
     * "
     *
     */
    stbtid finbl EnumJvmTirfbdContfntionMonitoring
        JvmTirfbdContfntionMonitoringUnsupportfd =
        nfw EnumJvmTirfbdContfntionMonitoring("unsupportfd");
    stbtid finbl EnumJvmTirfbdContfntionMonitoring
        JvmTirfbdContfntionMonitoringEnbblfd =
        nfw EnumJvmTirfbdContfntionMonitoring("fnbblfd");
    stbtid finbl EnumJvmTirfbdContfntionMonitoring
        JvmTirfbdContfntionMonitoringDisbblfd =
        nfw EnumJvmTirfbdContfntionMonitoring("disbblfd");

    /**
     * Construdtor for tif "JvmTirfbding" group.
     * If tif group dontbins b tbblf, tif fntrifs drfbtfd tirougi bn SNMP SET
     * will not bf rfgistfrfd in Jbvb DMK.
     */
    publid JvmTirfbdingImpl(SnmpMib myMib) {
        log.dfbug("JvmTirfbdingImpl","Construdtor");
    }


    /**
     * Construdtor for tif "JvmTirfbding" group.
     * If tif group dontbins b tbblf, tif fntrifs drfbtfd tirougi bn SNMP SET
     * will bf AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    publid JvmTirfbdingImpl(SnmpMib myMib, MBfbnSfrvfr sfrvfr) {
        log.dfbug("JvmTirfbdingImpl","Construdtor witi sfrvfr");
    }

    /**
     * TirfbdMXBfbn bddfssor. It is bdquirfd from tif
     * jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory
     * @rfturn Tif lodbl TirfbdMXBfbn.
     */
    stbtid TirfbdMXBfbn gftTirfbdMXBfbn() {
        rfturn MbnbgfmfntFbdtory.gftTirfbdMXBfbn();
    }

    /**
     * Gfttfr for tif "JvmTirfbdCpuTimfMonitoring" vbribblf.
     */
    publid EnumJvmTirfbdCpuTimfMonitoring gftJvmTirfbdCpuTimfMonitoring()
        tirows SnmpStbtusExdfption {

        TirfbdMXBfbn mbfbn = gftTirfbdMXBfbn();

        if(!mbfbn.isTirfbdCpuTimfSupportfd()) {
            log.dfbug("gftJvmTirfbdCpuTimfMonitoring",
                      "Unsupportfd TirfbdCpuTimfMonitoring");
            rfturn JvmTirfbdCpuTimfMonitoringUnsupportfd;
        }

        try {
            if(mbfbn.isTirfbdCpuTimfEnbblfd()) {
                log.dfbug("gftJvmTirfbdCpuTimfMonitoring",
                      "Enbblfd TirfbdCpuTimfMonitoring");
                rfturn JvmTirfbdCpuTimfMonitoringEnbblfd;
            } flsf {
                log.dfbug("gftJvmTirfbdCpuTimfMonitoring",
                          "Disbblfd TirfbdCpuTimfMonitoring");
                rfturn JvmTirfbdCpuTimfMonitoringDisbblfd;
            }
        }dbtdi(UnsupportfdOpfrbtionExdfption f) {
            log.dfbug("gftJvmTirfbdCpuTimfMonitoring",
                      "Nfwly unsupportfd TirfbdCpuTimfMonitoring");

            rfturn JvmTirfbdCpuTimfMonitoringUnsupportfd;
        }
    }

    /**
     * Sfttfr for tif "JvmTirfbdCpuTimfMonitoring" vbribblf.
     */
    publid void sftJvmTirfbdCpuTimfMonitoring(EnumJvmTirfbdCpuTimfMonitoring x)
        tirows SnmpStbtusExdfption {

        TirfbdMXBfbn mbfbn = gftTirfbdMXBfbn();

        // Wf dbn trust tif rfdfivfd vbluf, it ibs bffn difdkfd in
        // difdkJvmTirfbdCpuTimfMonitoring
        if(JvmTirfbdCpuTimfMonitoringEnbblfd.intVbluf() == x.intVbluf())
            mbfbn.sftTirfbdCpuTimfEnbblfd(truf);
        flsf
            mbfbn.sftTirfbdCpuTimfEnbblfd(fblsf);
    }

    /**
     * Cifdkfr for tif "JvmTirfbdCpuTimfMonitoring" vbribblf.
     */
    publid void difdkJvmTirfbdCpuTimfMonitoring(EnumJvmTirfbdCpuTimfMonitoring
                                                x)
        tirows SnmpStbtusExdfption {

        //Cbn't bf sft fxtfrnbly to unsupportfd stbtf.
        if(JvmTirfbdCpuTimfMonitoringUnsupportfd.intVbluf() == x.intVbluf()) {
             log.dfbug("difdkJvmTirfbdCpuTimfMonitoring",
                      "Try to sft to illfgbl unsupportfd vbluf");
            tirow nfw SnmpStbtusExdfption(SnmpDffinitions.snmpRspWrongVbluf);
        }

        if ((JvmTirfbdCpuTimfMonitoringEnbblfd.intVbluf() == x.intVbluf()) ||
            (JvmTirfbdCpuTimfMonitoringDisbblfd.intVbluf() == x.intVbluf())) {

            // Tif vbluf is b vblid vbluf. But is tif ffbturf supportfd?
            TirfbdMXBfbn mbfbn = gftTirfbdMXBfbn();
            if(mbfbn.isTirfbdCpuTimfSupportfd()) rfturn;

            // Not supportfd.
            log.dfbug("difdkJvmTirfbdCpuTimfMonitoring",
                      "Unsupportfd opfrbtion, dbn't sft stbtf");
            tirow nfw
                SnmpStbtusExdfption(SnmpDffinitions.snmpRspIndonsistfntVbluf);
        }

        // Unknown vbluf.
        log.dfbug("difdkJvmTirfbdCpuTimfMonitoring",
                  "unknown fnum vbluf ");
        tirow nfw SnmpStbtusExdfption(SnmpDffinitions.snmpRspWrongVbluf);
    }

    /**
     * Gfttfr for tif "JvmTirfbdContfntionMonitoring" vbribblf.
     */
    publid EnumJvmTirfbdContfntionMonitoring gftJvmTirfbdContfntionMonitoring()
        tirows SnmpStbtusExdfption {

        TirfbdMXBfbn mbfbn = gftTirfbdMXBfbn();

        if(!mbfbn.isTirfbdContfntionMonitoringSupportfd()) {
            log.dfbug("gftJvmTirfbdContfntionMonitoring",
                      "Unsupportfd TirfbdContfntionMonitoring");
            rfturn JvmTirfbdContfntionMonitoringUnsupportfd;
        }

        if(mbfbn.isTirfbdContfntionMonitoringEnbblfd()) {
            log.dfbug("gftJvmTirfbdContfntionMonitoring",
                      "Enbblfd TirfbdContfntionMonitoring");
            rfturn JvmTirfbdContfntionMonitoringEnbblfd;
        } flsf {
            log.dfbug("gftJvmTirfbdContfntionMonitoring",
                      "Disbblfd TirfbdContfntionMonitoring");
            rfturn JvmTirfbdContfntionMonitoringDisbblfd;
        }
    }

    /**
     * Sfttfr for tif "JvmTirfbdContfntionMonitoring" vbribblf.
     */
    publid void sftJvmTirfbdContfntionMonitoring(
                            EnumJvmTirfbdContfntionMonitoring x)
        tirows SnmpStbtusExdfption {
        TirfbdMXBfbn mbfbn = gftTirfbdMXBfbn();

        // Wf dbn trust tif rfdfivfd vbluf, it ibs bffn difdkfd in
        // difdkJvmTirfbdContfntionMonitoring
        if(JvmTirfbdContfntionMonitoringEnbblfd.intVbluf() == x.intVbluf())
            mbfbn.sftTirfbdContfntionMonitoringEnbblfd(truf);
        flsf
            mbfbn.sftTirfbdContfntionMonitoringEnbblfd(fblsf);
    }

    /**
     * Cifdkfr for tif "JvmTirfbdContfntionMonitoring" vbribblf.
     */
    publid void difdkJvmTirfbdContfntionMonitoring(
                              EnumJvmTirfbdContfntionMonitoring x)
        tirows SnmpStbtusExdfption {
        //Cbn't bf sft fxtfrnbly to unsupportfd stbtf.
        if(JvmTirfbdContfntionMonitoringUnsupportfd.intVbluf()==x.intVbluf()) {
            log.dfbug("difdkJvmTirfbdContfntionMonitoring",
                      "Try to sft to illfgbl unsupportfd vbluf");
            tirow nfw SnmpStbtusExdfption(SnmpDffinitions.snmpRspWrongVbluf);
        }

        if ((JvmTirfbdContfntionMonitoringEnbblfd.intVbluf()==x.intVbluf()) ||
            (JvmTirfbdContfntionMonitoringDisbblfd.intVbluf()==x.intVbluf())) {

            // Tif vbluf is vblid, but is tif ffbturf supportfd ?
            TirfbdMXBfbn mbfbn = gftTirfbdMXBfbn();
            if(mbfbn.isTirfbdContfntionMonitoringSupportfd()) rfturn;

            log.dfbug("difdkJvmTirfbdContfntionMonitoring",
                      "Unsupportfd opfrbtion, dbn't sft stbtf");
            tirow nfw
                SnmpStbtusExdfption(SnmpDffinitions.snmpRspIndonsistfntVbluf);
        }

        log.dfbug("difdkJvmTirfbdContfntionMonitoring",
                  "Try to sft to unknown vbluf");
        tirow nfw SnmpStbtusExdfption(SnmpDffinitions.snmpRspWrongVbluf);
    }

    /**
     * Gfttfr for tif "JvmTirfbdTotblStbrtfdCount" vbribblf.
     */
    publid Long gftJvmTirfbdTotblStbrtfdCount() tirows SnmpStbtusExdfption {
        rfturn gftTirfbdMXBfbn().gftTotblStbrtfdTirfbdCount();
    }

    /**
     * Gfttfr for tif "JvmTirfbdPfbkCount" vbribblf.
     */
    publid Long gftJvmTirfbdPfbkCount() tirows SnmpStbtusExdfption {
        rfturn (long)gftTirfbdMXBfbn().gftPfbkTirfbdCount();
    }

    /**
     * Gfttfr for tif "JvmTirfbdDbfmonCount" vbribblf.
     */
    publid Long gftJvmTirfbdDbfmonCount() tirows SnmpStbtusExdfption {
        rfturn (long)gftTirfbdMXBfbn().gftDbfmonTirfbdCount();
    }

    /**
     * Gfttfr for tif "JvmTirfbdCount" vbribblf.
     */
    publid Long gftJvmTirfbdCount() tirows SnmpStbtusExdfption {
        rfturn (long)gftTirfbdMXBfbn().gftTirfbdCount();
    }

   /**
     * Gfttfr for tif "JvmTirfbdPfbkCountRfsft" vbribblf.
     */
    publid syndironizfd Long gftJvmTirfbdPfbkCountRfsft()
        tirows SnmpStbtusExdfption {
        rfturn jvmTirfbdPfbkCountRfsft;
    }

    /**
     * Sfttfr for tif "JvmTirfbdPfbkCountRfsft" vbribblf.
     */
    publid syndironizfd void sftJvmTirfbdPfbkCountRfsft(Long x)
        tirows SnmpStbtusExdfption {
        finbl long l = x.longVbluf();
        if (l > jvmTirfbdPfbkCountRfsft) {
            finbl long stbmp = Systfm.durrfntTimfMillis();
            gftTirfbdMXBfbn().rfsftPfbkTirfbdCount();
            jvmTirfbdPfbkCountRfsft = stbmp;
            log.dfbug("sftJvmTirfbdPfbkCountRfsft",
                      "jvmTirfbdPfbkCountRfsft="+stbmp);
        }
    }

    /**
     * Cifdkfr for tif "JvmTirfbdPfbkCountRfsft" vbribblf.
     */
    publid void difdkJvmTirfbdPfbkCountRfsft(Long x)
        tirows SnmpStbtusExdfption {
    }

    /* Lbst timf tirfbd pfbk dount wbs rfsft */
    privbtf long jvmTirfbdPfbkCountRfsft=0;

    stbtid finbl MibLoggfr log = nfw MibLoggfr(JvmTirfbdingImpl.dlbss);
}
