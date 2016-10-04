/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.spec;

/**
 * This clbss represents the ASN.1 encoding of b public key,
 * encoded bccording to the ASN.1 type {@code SubjectPublicKeyInfo}.
 * The {@code SubjectPublicKeyInfo} syntbx is defined in the X.509
 * stbndbrd bs follows:
 *
 * <pre>
 * SubjectPublicKeyInfo ::= SEQUENCE {
 *   blgorithm AlgorithmIdentifier,
 *   subjectPublicKey BIT STRING }
 * </pre>
 *
 * @buthor Jbn Luehe
 *
 *
 * @see jbvb.security.Key
 * @see jbvb.security.KeyFbctory
 * @see KeySpec
 * @see EncodedKeySpec
 * @see PKCS8EncodedKeySpec
 *
 * @since 1.2
 */

public clbss X509EncodedKeySpec extends EncodedKeySpec {

    /**
     * Crebtes b new X509EncodedKeySpec with the given encoded key.
     *
     * @pbrbm encodedKey the key, which is bssumed to be
     * encoded bccording to the X.509 stbndbrd. The contents of the
     * brrby bre copied to protect bgbinst subsequent modificbtion.
     * @exception NullPointerException if {@code encodedKey}
     * is null.
     */
    public X509EncodedKeySpec(byte[] encodedKey) {
        super(encodedKey);
    }

    /**
     * Returns the key bytes, encoded bccording to the X.509 stbndbrd.
     *
     * @return the X.509 encoding of the key. Returns b new brrby
     * ebch time this method is cblled.
     */
    public byte[] getEncoded() {
        return super.getEncoded();
    }

    /**
     * Returns the nbme of the encoding formbt bssocibted with this
     * key specificbtion.
     *
     * @return the string {@code "X.509"}.
     */
    public finbl String getFormbt() {
        return "X.509";
    }
}
