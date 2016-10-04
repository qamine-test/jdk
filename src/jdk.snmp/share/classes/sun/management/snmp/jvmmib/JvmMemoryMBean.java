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
 * This interfbce is used for representing the remote mbnbgement interfbce for the "JvmMemory" MBebn.
 */
public interfbce JvmMemoryMBebn {

    /**
     * Getter for the "JvmMemoryNonHebpMbxSize" vbribble.
     */
    public Long getJvmMemoryNonHebpMbxSize() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemoryNonHebpCommitted" vbribble.
     */
    public Long getJvmMemoryNonHebpCommitted() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemoryNonHebpUsed" vbribble.
     */
    public Long getJvmMemoryNonHebpUsed() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemoryNonHebpInitSize" vbribble.
     */
    public Long getJvmMemoryNonHebpInitSize() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemoryHebpMbxSize" vbribble.
     */
    public Long getJvmMemoryHebpMbxSize() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemoryHebpCommitted" vbribble.
     */
    public Long getJvmMemoryHebpCommitted() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemoryGCCbll" vbribble.
     */
    public EnumJvmMemoryGCCbll getJvmMemoryGCCbll() throws SnmpStbtusException;

    /**
     * Setter for the "JvmMemoryGCCbll" vbribble.
     */
    public void setJvmMemoryGCCbll(EnumJvmMemoryGCCbll x) throws SnmpStbtusException;

    /**
     * Checker for the "JvmMemoryGCCbll" vbribble.
     */
    public void checkJvmMemoryGCCbll(EnumJvmMemoryGCCbll x) throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemoryHebpUsed" vbribble.
     */
    public Long getJvmMemoryHebpUsed() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemoryGCVerboseLevel" vbribble.
     */
    public EnumJvmMemoryGCVerboseLevel getJvmMemoryGCVerboseLevel() throws SnmpStbtusException;

    /**
     * Setter for the "JvmMemoryGCVerboseLevel" vbribble.
     */
    public void setJvmMemoryGCVerboseLevel(EnumJvmMemoryGCVerboseLevel x) throws SnmpStbtusException;

    /**
     * Checker for the "JvmMemoryGCVerboseLevel" vbribble.
     */
    public void checkJvmMemoryGCVerboseLevel(EnumJvmMemoryGCVerboseLevel x) throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemoryHebpInitSize" vbribble.
     */
    public Long getJvmMemoryHebpInitSize() throws SnmpStbtusException;

    /**
     * Getter for the "JvmMemoryPendingFinblCount" vbribble.
     */
    public Long getJvmMemoryPendingFinblCount() throws SnmpStbtusException;

}
