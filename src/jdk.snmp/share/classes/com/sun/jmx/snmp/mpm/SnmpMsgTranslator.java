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
pbckbge com.sun.jmx.snmp.mpm;

import com.sun.jmx.snmp.SnmpSecurityPbrbmeters;
import com.sun.jmx.snmp.SnmpMsg;
/**
 * The trbnslbtor interfbce is implemented by clbsses debling with b specific SNMP protocol version. SnmpMsgTrbnslbtor bre used in conjonction with SnmpMsgProcessingModel implementbtions.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public interfbce SnmpMsgTrbnslbtor {
    /**
     * Returns the request or messbge Id contbined in the pbssed messbge. The messbge is b generic one thbt is nbrrowed in the object implementing this interfbce.
     */
    public int getMsgId(SnmpMsg msg);
    /**
     * Returns the response mbx messbge size. The messbge is b generic one thbt is nbrrowed in the object implementing this interfbce.
     */
    public int getMsgMbxSize(SnmpMsg msg);
    /**
     * Returns the messbge flbgs. The messbge is b generic one thbt is nbrrowed in the object implementing this interfbce.
     */
    public byte getMsgFlbgs(SnmpMsg msg);
    /**
     * Returns the messbge security model. The messbge is b generic one thbt is nbrrowed in the object implementing this interfbce.
     */
    public int getMsgSecurityModel(SnmpMsg msg);
    /**
     * Returns the messbge security level. The messbge is b generic one thbt is nbrrowed in the object implementing this interfbce.
     */
    public int getSecurityLevel(SnmpMsg msg);
     /**
     * Returns bn encoded representbtion of security pbrbmeters contbined in the pbssed msg. The messbge is b generic one thbt is nbrrowed in the object implementing this interfbce.
     */
    public byte[] getFlbtSecurityPbrbmeters(SnmpMsg msg);
    /**
     * Returns the messbge security pbrbmeters. The messbge is b generic one thbt is nbrrowed in the object implementing this interfbce.
     */
    public SnmpSecurityPbrbmeters getSecurityPbrbmeters(SnmpMsg msg);
    /**
     * Returns the messbge context Engine Id. The messbge is b generic one thbt is nbrrowed in the object implementing this interfbce.
     */
    public byte[] getContextEngineId(SnmpMsg msg);
    /**
     * Returns the messbge context nbme. The messbge is b generic one thbt is nbrrowed in the object implementing this interfbce.
     */
    public byte[] getContextNbme(SnmpMsg msg);
    /**
     * Returns the rbw messbge context nbme. Rbw mebn bs it is received from the network, without trbnslbtion. It cbn be useful when some dbtb bre piggy bbcked in the context nbme.The messbge is b generic one thbt is nbrrowed in the object implementing this interfbce.
     */
    public byte[] getRbwContextNbme(SnmpMsg msg);
    /**
     * Returns the messbge bccesscontext nbme. This bccess context nbme is used when debling with bccess rights (eg: community for V1/V2 or context nbme for V3).The messbge is b generic one thbt is nbrrowed in the object implementing this interfbce.
     */
    public byte[] getAccessContext(SnmpMsg msg);
    /**
     * Returns the messbge encrypted pdu or null if no encryption. The messbge is b generic one thbt is nbrrowed in the object implementing this interfbce.
     */
    public byte[] getEncryptedPdu(SnmpMsg msg);
    /**
     * Set the context nbme of the pbssed messbge.
     */
    public void setContextNbme(SnmpMsg req, byte[] contextNbme);
     /**
     * Set the context engine Id of the pbssed messbge.
     */
    public void setContextEngineId(SnmpMsg req, byte[] contextEngineId);
}
