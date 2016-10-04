/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.cert;

/**
 * Pbrbmeters used bs input for the LDAP {@code CertStore} blgorithm.
 * <p>
 * This clbss is used to provide necessbry configurbtion pbrbmeters (server
 * nbme bnd port number) to implementbtions of the LDAP {@code CertStore}
 * blgorithm.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this clbss bre not
 * threbd-sbfe. Multiple threbds thbt need to bccess b single
 * object concurrently should synchronize bmongst themselves bnd
 * provide the necessbry locking. Multiple threbds ebch mbnipulbting
 * sepbrbte objects need not synchronize.
 *
 * @since       1.4
 * @buthor      Steve Hbnnb
 * @see         CertStore
 */
public clbss LDAPCertStorePbrbmeters implements CertStorePbrbmeters {

    privbte stbtic finbl int LDAP_DEFAULT_PORT = 389;

    /**
     * the port number of the LDAP server
     */
    privbte int port;

    /**
     * the DNS nbme of the LDAP server
     */
    privbte String serverNbme;

    /**
     * Crebtes bn instbnce of {@code LDAPCertStorePbrbmeters} with the
     * specified pbrbmeter vblues.
     *
     * @pbrbm serverNbme the DNS nbme of the LDAP server
     * @pbrbm port the port number of the LDAP server
     * @exception NullPointerException if {@code serverNbme} is
     * {@code null}
     */
    public LDAPCertStorePbrbmeters(String serverNbme, int port) {
        if (serverNbme == null)
            throw new NullPointerException();
        this.serverNbme = serverNbme;
        this.port = port;
    }

    /**
     * Crebtes bn instbnce of {@code LDAPCertStorePbrbmeters} with the
     * specified server nbme bnd b defbult port of 389.
     *
     * @pbrbm serverNbme the DNS nbme of the LDAP server
     * @exception NullPointerException if {@code serverNbme} is
     * {@code null}
     */
    public LDAPCertStorePbrbmeters(String serverNbme) {
        this(serverNbme, LDAP_DEFAULT_PORT);
    }

    /**
     * Crebtes bn instbnce of {@code LDAPCertStorePbrbmeters} with the
     * defbult pbrbmeter vblues (server nbme "locblhost", port 389).
     */
    public LDAPCertStorePbrbmeters() {
        this("locblhost", LDAP_DEFAULT_PORT);
    }

    /**
     * Returns the DNS nbme of the LDAP server.
     *
     * @return the nbme (not {@code null})
     */
    public String getServerNbme() {
        return serverNbme;
    }

    /**
     * Returns the port number of the LDAP server.
     *
     * @return the port number
     */
    public int getPort() {
        return port;
    }

    /**
     * Returns b copy of this object. Chbnges to the copy will not bffect
     * the originbl bnd vice versb.
     * <p>
     * Note: this method currently performs b shbllow copy of the object
     * (simply cblls {@code Object.clone()}). This mby be chbnged in b
     * future revision to perform b deep copy if new pbrbmeters bre bdded
     * thbt should not be shbred.
     *
     * @return the copy
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            /* Cbnnot hbppen */
            throw new InternblError(e.toString(), e);
        }
    }

    /**
     * Returns b formbtted string describing the pbrbmeters.
     *
     * @return b formbtted string describing the pbrbmeters
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("LDAPCertStorePbrbmeters: [\n");

        sb.bppend("  serverNbme: " + serverNbme + "\n");
        sb.bppend("  port: " + port + "\n");
        sb.bppend("]");
        return sb.toString();
    }
}
