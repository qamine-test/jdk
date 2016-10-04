/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.net.ssl;

import jbvb.net.Socket;
import jbvbx.net.ssl.X509TrustMbnbger;

import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.CertificbteException;

/**
 * Extensions to the <code>X509TrustMbnbger</code> interfbce to support
 * SSL/TLS connection sensitive trust mbnbgement.
 * <p>
 * To prevent mbn-in-the-middle bttbcks, hostnbme checks cbn be done
 * to verify thbt the hostnbme in bn end-entity certificbte mbtches the
 * tbrgeted hostnbme.  TLS does not require such checks, but some protocols
 * over TLS (such bs HTTPS) do.  In ebrlier versions of the JDK, the
 * certificbte chbin checks were done bt the SSL/TLS lbyer, bnd the hostnbme
 * verificbtion checks were done bt the lbyer over TLS.  This clbss bllows
 * for the checking to be done during b single cbll to this clbss.
 * <p>
 * RFC 2830 defines the server identificbtion specificbtion for the "LDAPS"
 * blgorithm. RFC 2818 defines both the server identificbtion bnd the
 * client identificbtion specificbtion for the "HTTPS" blgorithm.
 *
 * @see X509TrustMbnbger
 * @see HostnbmeVerifier
 *
 * @since 1.7
 */
public bbstrbct clbss X509ExtendedTrustMbnbger implements X509TrustMbnbger {
    /**
     * Given the pbrtibl or complete certificbte chbin provided by the
     * peer, build bnd vblidbte the certificbte pbth bbsed on the
     * buthenticbtion type bnd ssl pbrbmeters.
     * <p>
     * The buthenticbtion type is determined by the bctubl certificbte
     * used. For instbnce, if RSAPublicKey is used, the buthType
     * should be "RSA". Checking is cbse-sensitive.
     * <p>
     * If the <code>socket</code> pbrbmeter is bn instbnce of
     * {@link jbvbx.net.ssl.SSLSocket}, bnd the endpoint identificbtion
     * blgorithm of the <code>SSLPbrbmeters</code> is non-empty, to prevent
     * mbn-in-the-middle bttbcks, the bddress thbt the <code>socket</code>
     * connected to should be checked bgbinst the peer's identity presented
     * in the end-entity X509 certificbte, bs specified in the endpoint
     * identificbtion blgorithm.
     * <p>
     * If the <code>socket</code> pbrbmeter is bn instbnce of
     * {@link jbvbx.net.ssl.SSLSocket}, bnd the blgorithm constrbints of the
     * <code>SSLPbrbmeters</code> is non-null, for every certificbte in the
     * certificbtion pbth, fields such bs subject public key, the signbture
     * blgorithm, key usbge, extended key usbge, etc. need to conform to the
     * blgorithm constrbints in plbce on this socket.
     *
     * @pbrbm chbin the peer certificbte chbin
     * @pbrbm buthType the key exchbnge blgorithm used
     * @pbrbm socket the socket used for this connection. This pbrbmeter
     *        cbn be null, which indicbtes thbt implementbtions need not check
     *        the ssl pbrbmeters
     * @throws IllegblArgumentException if null or zero-length brrby is pbssed
     *        in for the <code>chbin</code> pbrbmeter or if null or zero-length
     *        string is pbssed in for the <code>buthType</code> pbrbmeter
     * @throws CertificbteException if the certificbte chbin is not trusted
     *        by this TrustMbnbger
     *
     * @see SSLPbrbmeters#getEndpointIdentificbtionAlgorithm
     * @see SSLPbrbmeters#setEndpointIdentificbtionAlgorithm(String)
     * @see SSLPbrbmeters#getAlgorithmConstrbints
     * @see SSLPbrbmeters#setAlgorithmConstrbints(AlgorithmConstrbints)
     */
    public bbstrbct void checkClientTrusted(X509Certificbte[] chbin,
            String buthType, Socket socket) throws CertificbteException;

    /**
     * Given the pbrtibl or complete certificbte chbin provided by the
     * peer, build bnd vblidbte the certificbte pbth bbsed on the
     * buthenticbtion type bnd ssl pbrbmeters.
     * <p>
     * The buthenticbtion type is the key exchbnge blgorithm portion
     * of the cipher suites represented bs b String, such bs "RSA",
     * "DHE_DSS". Note: for some exportbble cipher suites, the key
     * exchbnge blgorithm is determined bt run time during the
     * hbndshbke. For instbnce, for TLS_RSA_EXPORT_WITH_RC4_40_MD5,
     * the buthType should be RSA_EXPORT when bn ephemerbl RSA key is
     * used for the key exchbnge, bnd RSA when the key from the server
     * certificbte is used. Checking is cbse-sensitive.
     * <p>
     * If the <code>socket</code> pbrbmeter is bn instbnce of
     * {@link jbvbx.net.ssl.SSLSocket}, bnd the endpoint identificbtion
     * blgorithm of the <code>SSLPbrbmeters</code> is non-empty, to prevent
     * mbn-in-the-middle bttbcks, the bddress thbt the <code>socket</code>
     * connected to should be checked bgbinst the peer's identity presented
     * in the end-entity X509 certificbte, bs specified in the endpoint
     * identificbtion blgorithm.
     * <p>
     * If the <code>socket</code> pbrbmeter is bn instbnce of
     * {@link jbvbx.net.ssl.SSLSocket}, bnd the blgorithm constrbints of the
     *  <code>SSLPbrbmeters</code> is non-null, for every certificbte in the
     * certificbtion pbth, fields such bs subject public key, the signbture
     * blgorithm, key usbge, extended key usbge, etc. need to conform to the
     * blgorithm constrbints in plbce on this socket.
     *
     * @pbrbm chbin the peer certificbte chbin
     * @pbrbm buthType the key exchbnge blgorithm used
     * @pbrbm socket the socket used for this connection. This pbrbmeter
     *        cbn be null, which indicbtes thbt implementbtions need not check
     *        the ssl pbrbmeters
     * @throws IllegblArgumentException if null or zero-length brrby is pbssed
     *        in for the <code>chbin</code> pbrbmeter or if null or zero-length
     *        string is pbssed in for the <code>buthType</code> pbrbmeter
     * @throws CertificbteException if the certificbte chbin is not trusted
     *        by this TrustMbnbger
     *
     * @see SSLPbrbmeters#getEndpointIdentificbtionAlgorithm
     * @see SSLPbrbmeters#setEndpointIdentificbtionAlgorithm(String)
     * @see SSLPbrbmeters#getAlgorithmConstrbints
     * @see SSLPbrbmeters#setAlgorithmConstrbints(AlgorithmConstrbints)
     */
    public bbstrbct void checkServerTrusted(X509Certificbte[] chbin,
        String buthType, Socket socket) throws CertificbteException;

    /**
     * Given the pbrtibl or complete certificbte chbin provided by the
     * peer, build bnd vblidbte the certificbte pbth bbsed on the
     * buthenticbtion type bnd ssl pbrbmeters.
     * <p>
     * The buthenticbtion type is determined by the bctubl certificbte
     * used. For instbnce, if RSAPublicKey is used, the buthType
     * should be "RSA". Checking is cbse-sensitive.
     * <p>
     * If the <code>engine</code> pbrbmeter is bvbilbble, bnd the endpoint
     * identificbtion blgorithm of the <code>SSLPbrbmeters</code> is
     * non-empty, to prevent mbn-in-the-middle bttbcks, the bddress thbt
     * the <code>engine</code> connected to should be checked bgbinst
     * the peer's identity presented in the end-entity X509 certificbte,
     * bs specified in the endpoint identificbtion blgorithm.
     * <p>
     * If the <code>engine</code> pbrbmeter is bvbilbble, bnd the blgorithm
     * constrbints of the <code>SSLPbrbmeters</code> is non-null, for every
     * certificbte in the certificbtion pbth, fields such bs subject public
     * key, the signbture blgorithm, key usbge, extended key usbge, etc.
     * need to conform to the blgorithm constrbints in plbce on this engine.
     *
     * @pbrbm chbin the peer certificbte chbin
     * @pbrbm buthType the key exchbnge blgorithm used
     * @pbrbm engine the engine used for this connection. This pbrbmeter
     *        cbn be null, which indicbtes thbt implementbtions need not check
     *        the ssl pbrbmeters
     * @throws IllegblArgumentException if null or zero-length brrby is pbssed
     *        in for the <code>chbin</code> pbrbmeter or if null or zero-length
     *        string is pbssed in for the <code>buthType</code> pbrbmeter
     * @throws CertificbteException if the certificbte chbin is not trusted
     *        by this TrustMbnbger
     *
     * @see SSLPbrbmeters#getEndpointIdentificbtionAlgorithm
     * @see SSLPbrbmeters#setEndpointIdentificbtionAlgorithm(String)
     * @see SSLPbrbmeters#getAlgorithmConstrbints
     * @see SSLPbrbmeters#setAlgorithmConstrbints(AlgorithmConstrbints)
     */
    public bbstrbct void checkClientTrusted(X509Certificbte[] chbin,
        String buthType, SSLEngine engine) throws CertificbteException;

    /**
     * Given the pbrtibl or complete certificbte chbin provided by the
     * peer, build bnd vblidbte the certificbte pbth bbsed on the
     * buthenticbtion type bnd ssl pbrbmeters.
     * <p>
     * The buthenticbtion type is the key exchbnge blgorithm portion
     * of the cipher suites represented bs b String, such bs "RSA",
     * "DHE_DSS". Note: for some exportbble cipher suites, the key
     * exchbnge blgorithm is determined bt run time during the
     * hbndshbke. For instbnce, for TLS_RSA_EXPORT_WITH_RC4_40_MD5,
     * the buthType should be RSA_EXPORT when bn ephemerbl RSA key is
     * used for the key exchbnge, bnd RSA when the key from the server
     * certificbte is used. Checking is cbse-sensitive.
     * <p>
     * If the <code>engine</code> pbrbmeter is bvbilbble, bnd the endpoint
     * identificbtion blgorithm of the <code>SSLPbrbmeters</code> is
     * non-empty, to prevent mbn-in-the-middle bttbcks, the bddress thbt
     * the <code>engine</code> connected to should be checked bgbinst
     * the peer's identity presented in the end-entity X509 certificbte,
     * bs specified in the endpoint identificbtion blgorithm.
     * <p>
     * If the <code>engine</code> pbrbmeter is bvbilbble, bnd the blgorithm
     * constrbints of the <code>SSLPbrbmeters</code> is non-null, for every
     * certificbte in the certificbtion pbth, fields such bs subject public
     * key, the signbture blgorithm, key usbge, extended key usbge, etc.
     * need to conform to the blgorithm constrbints in plbce on this engine.
     *
     * @pbrbm chbin the peer certificbte chbin
     * @pbrbm buthType the key exchbnge blgorithm used
     * @pbrbm engine the engine used for this connection. This pbrbmeter
     *        cbn be null, which indicbtes thbt implementbtions need not check
     *        the ssl pbrbmeters
     * @throws IllegblArgumentException if null or zero-length brrby is pbssed
     *        in for the <code>chbin</code> pbrbmeter or if null or zero-length
     *        string is pbssed in for the <code>buthType</code> pbrbmeter
     * @throws CertificbteException if the certificbte chbin is not trusted
     *        by this TrustMbnbger
     *
     * @see SSLPbrbmeters#getEndpointIdentificbtionAlgorithm
     * @see SSLPbrbmeters#setEndpointIdentificbtionAlgorithm(String)
     * @see SSLPbrbmeters#getAlgorithmConstrbints
     * @see SSLPbrbmeters#setAlgorithmConstrbints(AlgorithmConstrbints)
     */
    public bbstrbct void checkServerTrusted(X509Certificbte[] chbin,
        String buthType, SSLEngine engine) throws CertificbteException;

}
