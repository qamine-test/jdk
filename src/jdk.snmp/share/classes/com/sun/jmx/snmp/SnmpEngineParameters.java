/*
 * Copyright (c) 2002, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This clbss is used to pbss some specific pbrbmeters to bn <CODE>
 * SnmpEngineFbctory </CODE>.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @since 1.5
 */
public clbss SnmpEnginePbrbmeters implements Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 3720556613478400808L;

    privbte UserAcl ubcl = null;
    privbte String securityFile = null;
    privbte boolebn encrypt = fblse;
    privbte SnmpEngineId engineId = null;

    /**
     * Sets the file to use for SNMP Runtime Lcd. If no file is provided, the defbult locbtion will be checked.
     */
    public void setSecurityFile(String securityFile) {
        this.securityFile = securityFile;
    }

    /**
     * Gets the file to use for SNMP Runtime Lcd.
     * @return The security file.
     */
    public String getSecurityFile() {
        return securityFile;
    }
    /**
     * Sets b customized user ACL. User Acl is used in order to check
     * bccess for SNMP V3 requests. If no ACL is provided,
     * <CODE>com.sun.jmx.snmp.usm.UserAcl.UserAcl</CODE> is instbntibted.
     * @pbrbm ubcl The user ACL to use.
     */
    public void setUserAcl(UserAcl ubcl) {
        this.ubcl = ubcl;
    }

    /**
     * Gets the customized user ACL.
     * @return The customized user ACL.
     */
    public UserAcl getUserAcl() {
        return ubcl;
    }

    /**
     * Activbte SNMP V3 encryption. By defbult the encryption is not bctivbted. Be sure thbt the security provider clbsses needed for DES bre in your clbsspbth (eg:JCE clbsses)
     *
     */
    public void bctivbteEncryption() {
        this.encrypt = true;
    }

    /**
     * Debctivbte SNMP V3 encryption. By defbult the encryption is not bctivbted. Be sure thbt the security provider clbsses needed for DES bre in your clbsspbth (eg:JCE clbsses)
     *
     */
    public void debctivbteEncryption() {
        this.encrypt = fblse;
    }

    /**
     * Check if encryption is bctivbted. By defbult the encryption is not bctivbted.
     * @return The encryption bctivbtion stbtus.
     */
    public boolebn isEncryptionEnbbled() {
        return encrypt;
    }

    /**
     * Set the engine Id.
     * @pbrbm engineId The engine Id to use.
     */
    public void setEngineId(SnmpEngineId engineId) {
        this.engineId = engineId;
    }

    /**
     * Get the engine Id.
     * @return The engineId.
     */
    public SnmpEngineId getEngineId() {
        return engineId;
    }
}
