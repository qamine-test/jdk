/*
 * Copyright (c) 2001, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This exception is thrown when bn error occurs in bn <CODE> SnmpSecurityModel </CODE>.
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public clbss SnmpSecurityException extends Exception {
    privbte stbtic finbl long seriblVersionUID = 5574448147432833480L;

    /**
     * The current request vbrbind list.
     */
    public SnmpVbrBind[] list = null;
    /**
     * The stbtus of the exception. See {@link com.sun.jmx.snmp.SnmpDefinitions} for possible vblues.
     */
    public int stbtus = SnmpDefinitions.snmpReqUnknownError;
    /**
     * The current security model relbted security pbrbmeters.
     */
    public SnmpSecurityPbrbmeters pbrbms = null;
    /**
     * The current context engine Id.
     */
    public byte[] contextEngineId = null;
     /**
     * The current context nbme.
     */
    public byte[] contextNbme = null;
     /**
     * The current flbgs.
     */
    public byte flbgs = (byte) SnmpDefinitions.noAuthNoPriv;
    /**
     * Constructor.
     * @pbrbm msg The exception msg to displby.
     */
    public SnmpSecurityException(String msg) {
        super(msg);
    }
}
