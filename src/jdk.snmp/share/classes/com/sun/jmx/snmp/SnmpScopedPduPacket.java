/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.Seriblizbble;

import com.sun.jmx.snmp.SnmpSecurityPbrbmeters;

import com.sun.jmx.snmp.SnmpDefinitions;
/**
 * Is the fully decoded representbtion of bn SNMP V3 pbcket.
 * <P>
 *
 * Clbsses bre derived from <CODE>SnmpPdu</CODE> to
 * represent the different forms of SNMP pdu
 * ({@link com.sun.jmx.snmp.SnmpScopedPduRequest SnmpScopedPduRequest},
 * {@link com.sun.jmx.snmp.SnmpScopedPduBulk SnmpScopedPduBulk}).
 * <BR>The <CODE>SnmpScopedPduPbcket</CODE> clbss defines the bttributes
 * common to every scoped SNMP pbckets.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @see SnmpV3Messbge
 *
 * @since 1.5
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss SnmpScopedPduPbcket extends SnmpPdu
    implements Seriblizbble {
    /**
     * Messbge mbx size the pdu sender cbn debl with.
     */
    public int msgMbxSize = 0;

    /**
     * Messbge identifier.
     */
    public int msgId = 0;

    /**
     * Messbge flbgs. Reportbble flbg  bnd security level.</P>
     *<PRE>
     * --  .... ...1   buthFlbg
     * --  .... ..1.   privFlbg
     * --  .... .1..   reportbbleFlbg
     * --              Plebse observe:
     * --  .... ..00   is OK, mebns noAuthNoPriv
     * --  .... ..01   is OK, mebns buthNoPriv
     * --  .... ..10   reserved, must NOT be used.
     * --  .... ..11   is OK, mebns buthPriv
     *</PRE>
     */
    public byte msgFlbgs = 0;

    /**
     * The security model the security sub system MUST use in order to debl with this pdu (eg: User bbsed Security Model Id = 3).
     */
    public int msgSecurityModel = 0;

    /**
     * The context engine Id in which the pdu must be hbndled (Generbly the locbl engine Id).
     */
    public byte[] contextEngineId = null;

    /**
     * The context nbme in which the OID hbve to be interpreted.
     */
    public byte[] contextNbme = null;

    /**
     * The security pbrbmeters. This is bn opbque member thbt is
     * interpreted by the concerned security model.
     */
    public SnmpSecurityPbrbmeters securityPbrbmeters = null;

    /**
     * Constructor. Is only cblled by b son. Set the version to <CODE>SnmpDefinitions.snmpVersionThree</CODE>.
     */
    protected SnmpScopedPduPbcket() {
        version = SnmpDefinitions.snmpVersionThree;
    }
}
