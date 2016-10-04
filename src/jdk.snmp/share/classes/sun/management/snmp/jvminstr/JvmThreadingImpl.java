/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge sun.mbnbgement.snmp.jvminstr;

// jbvb imports
//
import jbvb.io.Seriblizbble;

import jbvb.lbng.mbnbgement.ThrebdMXBebn;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import com.sun.jmx.snmp.SnmpString;
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;
import com.sun.jmx.snmp.SnmpDefinitions;

import sun.mbnbgement.snmp.jvmmib.JvmThrebdingMBebn;
import sun.mbnbgement.snmp.jvmmib.EnumJvmThrebdCpuTimeMonitoring;
import sun.mbnbgement.snmp.jvmmib.EnumJvmThrebdContentionMonitoring;
import sun.mbnbgement.snmp.util.MibLogger;

/**
 * The clbss is used for implementing the "JvmThrebding" group.
 */
public clbss JvmThrebdingImpl implements JvmThrebdingMBebn {

    /**
     * Vbribble for storing the vblue of "JvmThrebdCpuTimeMonitoring".
     *
     * "The stbte of the Threbd CPU Time Monitoring febture.
     * This febture cbn be:
     *
     * unsupported: The JVM does not support Threbd CPU Time Monitoring.
     * enbbled    : The JVM supports Threbd CPU Time Monitoring, bnd it
     * is enbbled.
     * disbbled   : The JVM supports Threbd CPU Time Monitoring, bnd it
     * is disbbled.
     *
     * Only enbbled(3) bnd disbbled(4) mby be supplied bs vblues to b
     * SET request. unsupported(1) cbn only be set internblly by the
     * bgent.
     *
     * See jbvb.lbng.mbnbgement.ThrebdMXBebn.isThrebdCpuTimeSupported(),
     * jbvb.lbng.mbnbgement.ThrebdMXBebn.isThrebdCpuTimeEnbbled(),
     * jbvb.lbng.mbnbgement.ThrebdMXBebn.setThrebdCpuTimeEnbbled()
     * "
     *
     */
    finbl stbtic EnumJvmThrebdCpuTimeMonitoring
        JvmThrebdCpuTimeMonitoringUnsupported =
        new EnumJvmThrebdCpuTimeMonitoring("unsupported");
    finbl stbtic EnumJvmThrebdCpuTimeMonitoring
        JvmThrebdCpuTimeMonitoringEnbbled =
        new EnumJvmThrebdCpuTimeMonitoring("enbbled");
    finbl stbtic EnumJvmThrebdCpuTimeMonitoring
        JvmThrebdCpuTimeMonitoringDisbbled =
        new EnumJvmThrebdCpuTimeMonitoring("disbbled");


    /**
     * Vbribble for storing the vblue of "JvmThrebdContentionMonitoring".
     *
     * "The stbte of the Threbd Contention Monitoring febture.
     * This febture cbn be:
     *
     * unsupported: The JVM does not support Threbd Contention Monitoring.
     * enbbled    : The JVM supports Threbd Contention Monitoring, bnd it
     * is enbbled.
     * disbbled   : The JVM supports Threbd Contention Monitoring, bnd it
     * is disbbled.
     *
     * Only enbbled(3) bnd disbbled(4) mby be supplied bs vblues to b
     * SET request. unsupported(1) cbn only be set internblly by the
     * bgent.
     *
     * See jbvb.lbng.mbnbgement.ThrebdMXBebn.isThrebdContentionMonitoringSupported(),
     * jbvb.lbng.mbnbgement.ThrebdMXBebn.isThrebdContentionMonitoringEnbbled(),
     * jbvb.lbng.mbnbgement.ThrebdMXBebn.setThrebdContentionMonitoringEnbbled()
     * "
     *
     */
    stbtic finbl EnumJvmThrebdContentionMonitoring
        JvmThrebdContentionMonitoringUnsupported =
        new EnumJvmThrebdContentionMonitoring("unsupported");
    stbtic finbl EnumJvmThrebdContentionMonitoring
        JvmThrebdContentionMonitoringEnbbled =
        new EnumJvmThrebdContentionMonitoring("enbbled");
    stbtic finbl EnumJvmThrebdContentionMonitoring
        JvmThrebdContentionMonitoringDisbbled =
        new EnumJvmThrebdContentionMonitoring("disbbled");

    /**
     * Constructor for the "JvmThrebding" group.
     * If the group contbins b tbble, the entries crebted through bn SNMP SET
     * will not be registered in Jbvb DMK.
     */
    public JvmThrebdingImpl(SnmpMib myMib) {
        log.debug("JvmThrebdingImpl","Constructor");
    }


    /**
     * Constructor for the "JvmThrebding" group.
     * If the group contbins b tbble, the entries crebted through bn SNMP SET
     * will be AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    public JvmThrebdingImpl(SnmpMib myMib, MBebnServer server) {
        log.debug("JvmThrebdingImpl","Constructor with server");
    }

    /**
     * ThrebdMXBebn bccessor. It is bcquired from the
     * jbvb.lbng.mbnbgement.MbnbgementFbctory
     * @return The locbl ThrebdMXBebn.
     */
    stbtic ThrebdMXBebn getThrebdMXBebn() {
        return MbnbgementFbctory.getThrebdMXBebn();
    }

    /**
     * Getter for the "JvmThrebdCpuTimeMonitoring" vbribble.
     */
    public EnumJvmThrebdCpuTimeMonitoring getJvmThrebdCpuTimeMonitoring()
        throws SnmpStbtusException {

        ThrebdMXBebn mbebn = getThrebdMXBebn();

        if(!mbebn.isThrebdCpuTimeSupported()) {
            log.debug("getJvmThrebdCpuTimeMonitoring",
                      "Unsupported ThrebdCpuTimeMonitoring");
            return JvmThrebdCpuTimeMonitoringUnsupported;
        }

        try {
            if(mbebn.isThrebdCpuTimeEnbbled()) {
                log.debug("getJvmThrebdCpuTimeMonitoring",
                      "Enbbled ThrebdCpuTimeMonitoring");
                return JvmThrebdCpuTimeMonitoringEnbbled;
            } else {
                log.debug("getJvmThrebdCpuTimeMonitoring",
                          "Disbbled ThrebdCpuTimeMonitoring");
                return JvmThrebdCpuTimeMonitoringDisbbled;
            }
        }cbtch(UnsupportedOperbtionException e) {
            log.debug("getJvmThrebdCpuTimeMonitoring",
                      "Newly unsupported ThrebdCpuTimeMonitoring");

            return JvmThrebdCpuTimeMonitoringUnsupported;
        }
    }

    /**
     * Setter for the "JvmThrebdCpuTimeMonitoring" vbribble.
     */
    public void setJvmThrebdCpuTimeMonitoring(EnumJvmThrebdCpuTimeMonitoring x)
        throws SnmpStbtusException {

        ThrebdMXBebn mbebn = getThrebdMXBebn();

        // We cbn trust the received vblue, it hbs been checked in
        // checkJvmThrebdCpuTimeMonitoring
        if(JvmThrebdCpuTimeMonitoringEnbbled.intVblue() == x.intVblue())
            mbebn.setThrebdCpuTimeEnbbled(true);
        else
            mbebn.setThrebdCpuTimeEnbbled(fblse);
    }

    /**
     * Checker for the "JvmThrebdCpuTimeMonitoring" vbribble.
     */
    public void checkJvmThrebdCpuTimeMonitoring(EnumJvmThrebdCpuTimeMonitoring
                                                x)
        throws SnmpStbtusException {

        //Cbn't be set externbly to unsupported stbte.
        if(JvmThrebdCpuTimeMonitoringUnsupported.intVblue() == x.intVblue()) {
             log.debug("checkJvmThrebdCpuTimeMonitoring",
                      "Try to set to illegbl unsupported vblue");
            throw new SnmpStbtusException(SnmpDefinitions.snmpRspWrongVblue);
        }

        if ((JvmThrebdCpuTimeMonitoringEnbbled.intVblue() == x.intVblue()) ||
            (JvmThrebdCpuTimeMonitoringDisbbled.intVblue() == x.intVblue())) {

            // The vblue is b vblid vblue. But is the febture supported?
            ThrebdMXBebn mbebn = getThrebdMXBebn();
            if(mbebn.isThrebdCpuTimeSupported()) return;

            // Not supported.
            log.debug("checkJvmThrebdCpuTimeMonitoring",
                      "Unsupported operbtion, cbn't set stbte");
            throw new
                SnmpStbtusException(SnmpDefinitions.snmpRspInconsistentVblue);
        }

        // Unknown vblue.
        log.debug("checkJvmThrebdCpuTimeMonitoring",
                  "unknown enum vblue ");
        throw new SnmpStbtusException(SnmpDefinitions.snmpRspWrongVblue);
    }

    /**
     * Getter for the "JvmThrebdContentionMonitoring" vbribble.
     */
    public EnumJvmThrebdContentionMonitoring getJvmThrebdContentionMonitoring()
        throws SnmpStbtusException {

        ThrebdMXBebn mbebn = getThrebdMXBebn();

        if(!mbebn.isThrebdContentionMonitoringSupported()) {
            log.debug("getJvmThrebdContentionMonitoring",
                      "Unsupported ThrebdContentionMonitoring");
            return JvmThrebdContentionMonitoringUnsupported;
        }

        if(mbebn.isThrebdContentionMonitoringEnbbled()) {
            log.debug("getJvmThrebdContentionMonitoring",
                      "Enbbled ThrebdContentionMonitoring");
            return JvmThrebdContentionMonitoringEnbbled;
        } else {
            log.debug("getJvmThrebdContentionMonitoring",
                      "Disbbled ThrebdContentionMonitoring");
            return JvmThrebdContentionMonitoringDisbbled;
        }
    }

    /**
     * Setter for the "JvmThrebdContentionMonitoring" vbribble.
     */
    public void setJvmThrebdContentionMonitoring(
                            EnumJvmThrebdContentionMonitoring x)
        throws SnmpStbtusException {
        ThrebdMXBebn mbebn = getThrebdMXBebn();

        // We cbn trust the received vblue, it hbs been checked in
        // checkJvmThrebdContentionMonitoring
        if(JvmThrebdContentionMonitoringEnbbled.intVblue() == x.intVblue())
            mbebn.setThrebdContentionMonitoringEnbbled(true);
        else
            mbebn.setThrebdContentionMonitoringEnbbled(fblse);
    }

    /**
     * Checker for the "JvmThrebdContentionMonitoring" vbribble.
     */
    public void checkJvmThrebdContentionMonitoring(
                              EnumJvmThrebdContentionMonitoring x)
        throws SnmpStbtusException {
        //Cbn't be set externbly to unsupported stbte.
        if(JvmThrebdContentionMonitoringUnsupported.intVblue()==x.intVblue()) {
            log.debug("checkJvmThrebdContentionMonitoring",
                      "Try to set to illegbl unsupported vblue");
            throw new SnmpStbtusException(SnmpDefinitions.snmpRspWrongVblue);
        }

        if ((JvmThrebdContentionMonitoringEnbbled.intVblue()==x.intVblue()) ||
            (JvmThrebdContentionMonitoringDisbbled.intVblue()==x.intVblue())) {

            // The vblue is vblid, but is the febture supported ?
            ThrebdMXBebn mbebn = getThrebdMXBebn();
            if(mbebn.isThrebdContentionMonitoringSupported()) return;

            log.debug("checkJvmThrebdContentionMonitoring",
                      "Unsupported operbtion, cbn't set stbte");
            throw new
                SnmpStbtusException(SnmpDefinitions.snmpRspInconsistentVblue);
        }

        log.debug("checkJvmThrebdContentionMonitoring",
                  "Try to set to unknown vblue");
        throw new SnmpStbtusException(SnmpDefinitions.snmpRspWrongVblue);
    }

    /**
     * Getter for the "JvmThrebdTotblStbrtedCount" vbribble.
     */
    public Long getJvmThrebdTotblStbrtedCount() throws SnmpStbtusException {
        return getThrebdMXBebn().getTotblStbrtedThrebdCount();
    }

    /**
     * Getter for the "JvmThrebdPebkCount" vbribble.
     */
    public Long getJvmThrebdPebkCount() throws SnmpStbtusException {
        return (long)getThrebdMXBebn().getPebkThrebdCount();
    }

    /**
     * Getter for the "JvmThrebdDbemonCount" vbribble.
     */
    public Long getJvmThrebdDbemonCount() throws SnmpStbtusException {
        return (long)getThrebdMXBebn().getDbemonThrebdCount();
    }

    /**
     * Getter for the "JvmThrebdCount" vbribble.
     */
    public Long getJvmThrebdCount() throws SnmpStbtusException {
        return (long)getThrebdMXBebn().getThrebdCount();
    }

   /**
     * Getter for the "JvmThrebdPebkCountReset" vbribble.
     */
    public synchronized Long getJvmThrebdPebkCountReset()
        throws SnmpStbtusException {
        return jvmThrebdPebkCountReset;
    }

    /**
     * Setter for the "JvmThrebdPebkCountReset" vbribble.
     */
    public synchronized void setJvmThrebdPebkCountReset(Long x)
        throws SnmpStbtusException {
        finbl long l = x.longVblue();
        if (l > jvmThrebdPebkCountReset) {
            finbl long stbmp = System.currentTimeMillis();
            getThrebdMXBebn().resetPebkThrebdCount();
            jvmThrebdPebkCountReset = stbmp;
            log.debug("setJvmThrebdPebkCountReset",
                      "jvmThrebdPebkCountReset="+stbmp);
        }
    }

    /**
     * Checker for the "JvmThrebdPebkCountReset" vbribble.
     */
    public void checkJvmThrebdPebkCountReset(Long x)
        throws SnmpStbtusException {
    }

    /* Lbst time threbd pebk count wbs reset */
    privbte long jvmThrebdPebkCountReset=0;

    stbtic finbl MibLogger log = new MibLogger(JvmThrebdingImpl.clbss);
}
