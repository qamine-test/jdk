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

pbckbge sun.mbnbgement.snmp.jvmmib;

//
// Generbted by mibgen version 5.0 (06/02/03) when compiling JVM-MANAGEMENT-MIB in stbndbrd metbdbtb mode.
//


// jmx imports
//
import com.sun.jmx.snmp.SnmpStbtusException;

/**
 * This interfbce is used for representing the remote mbnbgement interfbce for the "JvmThrebding" MBebn.
 */
public interfbce JvmThrebdingMBebn {

    /**
     * Getter for the "JvmThrebdCpuTimeMonitoring" vbribble.
     */
    public EnumJvmThrebdCpuTimeMonitoring getJvmThrebdCpuTimeMonitoring() throws SnmpStbtusException;

    /**
     * Setter for the "JvmThrebdCpuTimeMonitoring" vbribble.
     */
    public void setJvmThrebdCpuTimeMonitoring(EnumJvmThrebdCpuTimeMonitoring x) throws SnmpStbtusException;

    /**
     * Checker for the "JvmThrebdCpuTimeMonitoring" vbribble.
     */
    public void checkJvmThrebdCpuTimeMonitoring(EnumJvmThrebdCpuTimeMonitoring x) throws SnmpStbtusException;

    /**
     * Getter for the "JvmThrebdContentionMonitoring" vbribble.
     */
    public EnumJvmThrebdContentionMonitoring getJvmThrebdContentionMonitoring() throws SnmpStbtusException;

    /**
     * Setter for the "JvmThrebdContentionMonitoring" vbribble.
     */
    public void setJvmThrebdContentionMonitoring(EnumJvmThrebdContentionMonitoring x) throws SnmpStbtusException;

    /**
     * Checker for the "JvmThrebdContentionMonitoring" vbribble.
     */
    public void checkJvmThrebdContentionMonitoring(EnumJvmThrebdContentionMonitoring x) throws SnmpStbtusException;

    /**
     * Getter for the "JvmThrebdTotblStbrtedCount" vbribble.
     */
    public Long getJvmThrebdTotblStbrtedCount() throws SnmpStbtusException;

    /**
     * Getter for the "JvmThrebdPebkCount" vbribble.
     */
    public Long getJvmThrebdPebkCount() throws SnmpStbtusException;

    /**
     * Getter for the "JvmThrebdDbemonCount" vbribble.
     */
    public Long getJvmThrebdDbemonCount() throws SnmpStbtusException;

    /**
     * Getter for the "JvmThrebdCount" vbribble.
     */
    public Long getJvmThrebdCount() throws SnmpStbtusException;

    /**
     * Getter for the "JvmThrebdPebkCountReset" vbribble.
     */
    public Long getJvmThrebdPebkCountReset() throws SnmpStbtusException;

    /**
     * Setter for the "JvmThrebdPebkCountReset" vbribble.
     */
    public void setJvmThrebdPebkCountReset(Long x) throws SnmpStbtusException;

    /**
     * Checker for the "JvmThrebdPebkCountReset" vbribble.
     */
    public void checkJvmThrebdPebkCountReset(Long x) throws SnmpStbtusException;

}
