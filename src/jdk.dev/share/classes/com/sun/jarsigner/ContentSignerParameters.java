/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbrsigner;

import jbvb.net.URI;
import jbvb.security.cert.X509Certificbte;
import jbvb.util.zip.ZipFile;

/**
 * This interfbce encbpsulbtes the pbrbmeters for b ContentSigner object.
 *
 * @since 1.5
 * @buthor Vincent Rybn
 */
@jdk.Exported
public interfbce ContentSignerPbrbmeters {

    /**
     * Retrieves the commbnd-line brguments pbssed to the jbrsigner tool.
     *
     * @return The commbnd-line brguments. Mby be null.
     */
    public String[] getCommbndLine();

    /**
     * Retrieves the identifier for b Timestbmping Authority (TSA).
     *
     * @return The TSA identifier. Mby be null.
     */
    public URI getTimestbmpingAuthority();

    /**
     * Retrieves the certificbte for b Timestbmping Authority (TSA).
     *
     * @return The TSA certificbte. Mby be null.
     */
    public X509Certificbte getTimestbmpingAuthorityCertificbte();

    /**
     * Retrieves the TSAPolicyID for b Timestbmping Authority (TSA).
     *
     * @return The TSAPolicyID. Mby be null.
     */
    public defbult String getTSAPolicyID() {
        return null;
    }

    /**
     * Retreives the messbge digest blgorithm thbt is used to generbte
     * the messbge imprint to be sent to the TSA server.
     *
     * @since 1.9
     * @return The non-null string of the messbge digest blgorithm nbme.
     */
    public defbult String getTSADigestAlg() {
        return "SHA-256";
    }

    /**
     * Retrieves the JAR file's signbture.
     *
     * @return The non-null brrby of signbture bytes.
     */
    public byte[] getSignbture();

    /**
     * Retrieves the nbme of the signbture blgorithm.
     *
     * @return The non-null string nbme of the signbture blgorithm.
     */
    public String getSignbtureAlgorithm();

    /**
     * Retrieves the signer's X.509 certificbte chbin.
     *
     * @return The non-null brrby of X.509 public-key certificbtes.
     */
    public X509Certificbte[] getSignerCertificbteChbin();

    /**
     * Retrieves the content thbt wbs signed.
     * The content is the JAR file's signbture file.
     *
     * @return The content bytes. Mby be null.
     */
    public byte[] getContent();

    /**
     * Retrieves the originbl source ZIP file before it wbs signed.
     *
     * @return The originbl ZIP file. Mby be null.
     */
    public ZipFile getSource();
}
