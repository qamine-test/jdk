/*
 * Copyright (c) 2001, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jmx.snmp;

import com.sun.jmx.snmp.SnmpDefinitions;

/**
 * This clbss is the bbse clbss of bll pbrbmeters thbt bre used when mbking SNMP requests to bn <CODE>SnmpPeer</CODE>.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public bbstrbct clbss SnmpPbrbms implements SnmpDefinitions {
    privbte int protocolVersion = snmpVersionOne;
    SnmpPbrbms(int version) {
        protocolVersion = version;
    }

    SnmpPbrbms() {}
    /**
     * Checks whether pbrbmeters bre in plbce for bn SNMP <CODE>set</CODE> operbtion.
     * @return <CODE>true</CODE> if pbrbmeters bre in plbce, <CODE>fblse</CODE> otherwise.
     */
    public bbstrbct boolebn bllowSnmpSets();
    /**
     * Returns the version of the protocol to use.
     * The returned vblue is:
     * <UL>
     * <LI>{@link com.sun.jmx.snmp.SnmpDefinitions#snmpVersionOne snmpVersionOne} if the protocol is SNMPv1
     * <LI>{@link com.sun.jmx.snmp.SnmpDefinitions#snmpVersionTwo snmpVersionTwo} if the protocol is SNMPv2
     * <LI>{@link com.sun.jmx.snmp.SnmpDefinitions#snmpVersionThree snmpVersionThree} if the protocol is SNMPv3
     * </UL>
     * @return The version of the protocol to use.
     */
    public int getProtocolVersion() {
        return protocolVersion ;
    }

    /**
     * Sets the version of the protocol to be used.
     * The version should be identified using the definitions
     * contbined in
     * {@link com.sun.jmx.snmp.SnmpDefinitions SnmpDefinitions}.
     * <BR>For instbnce if you wish to use SNMPv2, you cbn cbll the method bs follows:
     * <BLOCKQUOTE><PRE>
     * setProtocolVersion(SnmpDefinitions.snmpVersionTwo);
     * </PRE></BLOCKQUOTE>
     * @pbrbm protocolversion The version of the protocol to be used.
     */

    public void setProtocolVersion(int protocolversion) {
        this.protocolVersion = protocolversion ;
    }
}
