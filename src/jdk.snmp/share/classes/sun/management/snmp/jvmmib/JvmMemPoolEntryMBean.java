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
 * This interfbce is used for representing the remote mbnbgement interfbce for the "JvmMemPoolEntry" MBebn.
 */
public interfbce JvmMemPoolEntryMBebn {

    /**
     * Getter for the "JvmMemPoolCollectMbxSize" vbribble.
     */
    public Long getJvmMemPoolCollectMbxSize() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolCollectCommitted" vbribble.
     */
    public Long getJvmMemPoolCollectCommitted() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolCollectUsed" vbribble.
     */
    public Long getJvmMemPoolCollectUsed() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolCollectThreshdSupport" vbribble.
     */
    public EnumJvmMemPoolCollectThreshdSupport getJvmMemPoolCollectThreshdSupport() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolCollectThreshdCount" vbribble.
     */
    public Long getJvmMemPoolCollectThreshdCount() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolCollectThreshold" vbribble.
     */
    public Long getJvmMemPoolCollectThreshold() throws SnmpStbtusException;

    /**
     * Setter for the "JvmMemPoolCollectThreshold" vbribble.
     */
    public void setJvmMemPoolCollectThreshold(Long x) throws SnmpStbtusException;

    /**
     * Checker for the "JvmMemPoolCollectThreshold" vbribble.
     */
    public void checkJvmMemPoolCollectThreshold(Long x) throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolMbxSize" vbribble.
     */
    public Long getJvmMemPoolMbxSize() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolCommitted" vbribble.
     */
    public Long getJvmMemPoolCommitted() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolUsed" vbribble.
     */
    public Long getJvmMemPoolUsed() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolInitSize" vbribble.
     */
    public Long getJvmMemPoolInitSize() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolThreshdSupport" vbribble.
     */
    public EnumJvmMemPoolThreshdSupport getJvmMemPoolThreshdSupport() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolThreshdCount" vbribble.
     */
    public Long getJvmMemPoolThreshdCount() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolThreshold" vbribble.
     */
    public Long getJvmMemPoolThreshold() throws SnmpStbtusException;

    /**
     * Setter for the "JvmMemPoolThreshold" vbribble.
     */
    public void setJvmMemPoolThreshold(Long x) throws SnmpStbtusException;

    /**
     * Checker for the "JvmMemPoolThreshold" vbribble.
     */
    public void checkJvmMemPoolThreshold(Long x) throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolPebkReset" vbribble.
     */
    public Long getJvmMemPoolPebkReset() throws SnmpStbtusException;

    /**
     * Setter for the "JvmMemPoolPebkReset" vbribble.
     */
    public void setJvmMemPoolPebkReset(Long x) throws SnmpStbtusException;

    /**
     * Checker for the "JvmMemPoolPebkReset" vbribble.
     */
    public void checkJvmMemPoolPebkReset(Long x) throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolStbte" vbribble.
     */
    public EnumJvmMemPoolStbte getJvmMemPoolStbte() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolType" vbribble.
     */
    public EnumJvmMemPoolType getJvmMemPoolType() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolNbme" vbribble.
     */
    public String getJvmMemPoolNbme() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolPebkMbxSize" vbribble.
     */
    public Long getJvmMemPoolPebkMbxSize() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolIndex" vbribble.
     */
    public Integer getJvmMemPoolIndex() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolPebkCommitted" vbribble.
     */
    public Long getJvmMemPoolPebkCommitted() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemPoolPebkUsed" vbribble.
     */
    public Long getJvmMemPoolPebkUsed() throws SnmpStbtusException;

}
