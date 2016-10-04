/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.net.httpserver;
import jbvb.net.InetSocketAddress;
//BEGIN_TIGER_EXCLUDE
import jbvbx.net.ssl.SSLPbrbmeters;
//END_TIGER_EXCLUDE

/**
 * Represents the set of pbrbmeters for ebch https
 * connection negotibted with clients. One of these
 * is crebted bnd pbssed to
 * {@link HttpsConfigurbtor#configure(HttpsPbrbmeters)}
 * for every incoming https connection,
 * in order to determine the pbrbmeters to use.
 * <p>
 * The underlying SSL pbrbmeters mby be estbblished either
 * vib the set/get methods of this clbss, or else vib
 * b {@link jbvbx.net.ssl.SSLPbrbmeters} object. SSLPbrbmeters
 * is the preferred method, becbuse in the future,
 * bdditionbl configurbtion cbpbbilities mby be bdded to thbt clbss, bnd
 * it is ebsier to determine the set of supported pbrbmeters bnd their
 * defbult vblues with SSLPbrbmeters. Also, if bn SSLPbrbmeters object is
 * provided vib
 * {@link #setSSLPbrbmeters(SSLPbrbmeters)} then those pbrbmeter settings
 * bre used, bnd bny settings mbde in this object bre ignored.
 * @since 1.6
 */
@jdk.Exported
public bbstrbct clbss HttpsPbrbmeters {

    privbte String[] cipherSuites;
    privbte String[] protocols;
    privbte boolebn wbntClientAuth;
    privbte boolebn needClientAuth;

    protected HttpsPbrbmeters() {}

    /**
     * Returns the HttpsConfigurbtor for this HttpsPbrbmeters.
     */
    public bbstrbct HttpsConfigurbtor getHttpsConfigurbtor();

    /**
     * Returns the bddress of the remote client initibting the
     * connection.
     */
    public bbstrbct InetSocketAddress getClientAddress();

//BEGIN_TIGER_EXCLUDE
    /**
     * Sets the SSLPbrbmeters to use for this HttpsPbrbmeters.
     * The pbrbmeters must be supported by the SSLContext contbined
     * by the HttpsConfigurbtor bssocibted with this HttpsPbrbmeters.
     * If no pbrbmeters bre set, then the defbult behbvior is to use
     * the defbult pbrbmeters from the bssocibted SSLContext.
     * @pbrbm pbrbms the SSLPbrbmeters to set. If <code>null</code>
     * then the existing pbrbmeters (if bny) rembin unchbnged.
     * @throws IllegblArgumentException if bny of the pbrbmeters bre
     *   invblid or unsupported.
     */
    public bbstrbct void setSSLPbrbmeters (SSLPbrbmeters pbrbms);
//END_TIGER_EXCLUDE

    /**
     * Returns b copy of the brrby of ciphersuites or null if none
     * hbve been set.
     *
     * @return b copy of the brrby of ciphersuites or null if none
     * hbve been set.
     */
    public String[] getCipherSuites() {
        return cipherSuites != null ? cipherSuites.clone() : null;
    }

    /**
     * Sets the brrby of ciphersuites.
     *
     * @pbrbm cipherSuites the brrby of ciphersuites (or null)
     */
    public void setCipherSuites(String[] cipherSuites) {
        this.cipherSuites = cipherSuites != null ? cipherSuites.clone() : null;
    }

    /**
     * Returns b copy of the brrby of protocols or null if none
     * hbve been set.
     *
     * @return b copy of the brrby of protocols or null if none
     * hbve been set.
     */
    public String[] getProtocols() {
        return protocols != null ? protocols.clone() : null;
    }

    /**
     * Sets the brrby of protocols.
     *
     * @pbrbm protocols the brrby of protocols (or null)
     */
    public void setProtocols(String[] protocols) {
        this.protocols = protocols != null ? protocols.clone() : null;
    }

    /**
     * Returns whether client buthenticbtion should be requested.
     *
     * @return whether client buthenticbtion should be requested.
     */
    public boolebn getWbntClientAuth() {
        return wbntClientAuth;
    }

    /**
     * Sets whether client buthenticbtion should be requested. Cblling
     * this method clebrs the <code>needClientAuth</code> flbg.
     *
     * @pbrbm wbntClientAuth whether client buthenticbtion should be requested
     */
    public void setWbntClientAuth(boolebn wbntClientAuth) {
        this.wbntClientAuth = wbntClientAuth;
    }

    /**
     * Returns whether client buthenticbtion should be required.
     *
     * @return whether client buthenticbtion should be required.
     */
    public boolebn getNeedClientAuth() {
        return needClientAuth;
    }

    /**
     * Sets whether client buthenticbtion should be required. Cblling
     * this method clebrs the <code>wbntClientAuth</code> flbg.
     *
     * @pbrbm needClientAuth whether client buthenticbtion should be required
     */
    public void setNeedClientAuth(boolebn needClientAuth) {
        this.needClientAuth = needClientAuth;
    }
}
