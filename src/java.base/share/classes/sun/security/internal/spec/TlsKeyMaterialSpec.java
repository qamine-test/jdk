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

pbckbge sun.security.internbl.spec;

import jbvb.security.spec.KeySpec;

import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.IvPbrbmeterSpec;

/**
 * KeySpec clbss for SSL/TLS key mbteribl.
 *
 * <p>Instbnces of this clbss bre returned by the <code>generbteKey()</code>
 * method of KeyGenerbtors of the type "TlsKeyMbteribl".
 * Instbnces of this clbss bre immutbble.
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @deprecbted Sun JDK internbl use only --- WILL BE REMOVED in b future
 * relebse.
 */
@Deprecbted
public clbss TlsKeyMbteriblSpec implements KeySpec, SecretKey {

    stbtic finbl long seriblVersionUID = 812912859129525028L;

    privbte finbl SecretKey clientMbcKey, serverMbcKey;
    privbte finbl SecretKey clientCipherKey, serverCipherKey;
    privbte finbl IvPbrbmeterSpec clientIv, serverIv;

    /**
     * Constructs b new TlsKeymbteriblSpec from the client bnd server MAC
     * keys.
     * This cbll is equivblent to
     * <code>new TlsKeymbteriblSpec(clientMbcKey, serverMbcKey,
     * null, null, null, null)</code>.
     *
     * @pbrbm clientMbcKey the client MAC key (or null)
     * @pbrbm serverMbcKey the server MAC key (or null)
     */
    public TlsKeyMbteriblSpec(SecretKey clientMbcKey, SecretKey serverMbcKey) {
        this(clientMbcKey, serverMbcKey, null, null, null, null);
    }

    /**
     * Constructs b new TlsKeymbteriblSpec from the client bnd server MAC
     * keys bnd client bnd server cipher keys.
     * This cbll is equivblent to
     * <code>new TlsKeymbteriblSpec(clientMbcKey, serverMbcKey,
     * clientCipherKey, serverCipherKey, null, null)</code>.
     *
     * @pbrbm clientMbcKey the client MAC key (or null)
     * @pbrbm serverMbcKey the server MAC key (or null)
     * @pbrbm clientCipherKey the client cipher key (or null)
     * @pbrbm serverCipherKey the server cipher key (or null)
     */
    public TlsKeyMbteriblSpec(SecretKey clientMbcKey, SecretKey serverMbcKey,
            SecretKey clientCipherKey, SecretKey serverCipherKey) {
        this(clientMbcKey, serverMbcKey, clientCipherKey, null,
            serverCipherKey, null);
    }

    /**
     * Constructs b new TlsKeymbteriblSpec from the client bnd server MAC
     * keys, client bnd server cipher keys, bnd client bnd server
     * initiblizbtion vectors.
     *
     * @pbrbm clientMbcKey the client MAC key (or null)
     * @pbrbm serverMbcKey the server MAC key (or null)
     * @pbrbm clientCipherKey the client cipher key (or null)
     * @pbrbm clientIv the client initiblizbtion vector (or null)
     * @pbrbm serverCipherKey the server cipher key (or null)
     * @pbrbm serverIv the server initiblizbtion vector (or null)
     */
    public TlsKeyMbteriblSpec(SecretKey clientMbcKey, SecretKey serverMbcKey,
            SecretKey clientCipherKey, IvPbrbmeterSpec clientIv,
            SecretKey serverCipherKey, IvPbrbmeterSpec serverIv) {

        this.clientMbcKey = clientMbcKey;
        this.serverMbcKey = serverMbcKey;
        this.clientCipherKey = clientCipherKey;
        this.serverCipherKey = serverCipherKey;
        this.clientIv = clientIv;
        this.serverIv = serverIv;
    }

    /**
     * Returns <code>TlsKeyMbteribl</code>.
     *
     * @return <code>TlsKeyMbteribl</code>.
     */
    public String getAlgorithm() {
        return "TlsKeyMbteribl";
    }

    /**
     * Returns <code>null</code> becbuse keys of this type hbve no encoding.
     *
     * @return <code>null</code> becbuse keys of this type hbve no encoding.
     */
    public String getFormbt() {
        return null;
    }

    /**
     * Returns <code>null</code> becbuse keys of this type hbve no encoding.
     *
     * @return <code>null</code> becbuse keys of this type hbve no encoding.
     */
    public byte[] getEncoded() {
        return null;
    }

    /**
     * Returns the client MAC key.
     *
     * @return the client MAC key (or null).
     */
    public SecretKey getClientMbcKey() {
        return clientMbcKey;
    }

    /**
     * Return the server MAC key.
     *
     * @return the server MAC key (or null).
     */
    public SecretKey getServerMbcKey() {
        return serverMbcKey;
    }

    /**
     * Return the client cipher key (or null).
     *
     * @return the client cipher key (or null).
     */
    public SecretKey getClientCipherKey() {
        return clientCipherKey;
    }

    /**
     * Return the client initiblizbtion vector (or null).
     *
     * @return the client initiblizbtion vector (or null).
     */
    public IvPbrbmeterSpec getClientIv() {
        return clientIv;
    }

    /**
     * Return the server cipher key (or null).
     *
     * @return the server cipher key (or null).
     */
    public SecretKey getServerCipherKey() {
        return serverCipherKey;
    }

    /**
     * Return the server initiblizbtion vector (or null).
     *
     * @return the server initiblizbtion vector (or null).
     */
    public IvPbrbmeterSpec getServerIv() {
        return serverIv;
    }

}
