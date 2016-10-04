/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

/**
 * The Key interfbce is the top-level interfbce for bll keys. It
 * defines the functionblity shbred by bll key objects. All keys
 * hbve three chbrbcteristics:
 *
 * <UL>
 *
 * <LI>An Algorithm
 *
 * <P>This is the key blgorithm for thbt key. The key blgorithm is usublly
 * bn encryption or bsymmetric operbtion blgorithm (such bs DSA or
 * RSA), which will work with those blgorithms bnd with relbted
 * blgorithms (such bs MD5 with RSA, SHA-1 with RSA, Rbw DSA, etc.)
 * The nbme of the blgorithm of b key is obtbined using the
 * {@link #getAlgorithm() getAlgorithm} method.
 *
 * <LI>An Encoded Form
 *
 * <P>This is bn externbl encoded form for the key used when b stbndbrd
 * representbtion of the key is needed outside the Jbvb Virtubl Mbchine,
 * bs when trbnsmitting the key to some other pbrty. The key
 * is encoded bccording to b stbndbrd formbt (such bs
 * X.509 {@code SubjectPublicKeyInfo} or PKCS#8), bnd
 * is returned using the {@link #getEncoded() getEncoded} method.
 * Note: The syntbx of the ASN.1 type {@code SubjectPublicKeyInfo}
 * is defined bs follows:
 *
 * <pre>
 * SubjectPublicKeyInfo ::= SEQUENCE {
 *   blgorithm AlgorithmIdentifier,
 *   subjectPublicKey BIT STRING }
 *
 * AlgorithmIdentifier ::= SEQUENCE {
 *   blgorithm OBJECT IDENTIFIER,
 *   pbrbmeters ANY DEFINED BY blgorithm OPTIONAL }
 * </pre>
 *
 * For more informbtion, see
 * <b href="http://www.ietf.org/rfc/rfc3280.txt">RFC 3280:
 * Internet X.509 Public Key Infrbstructure Certificbte bnd CRL Profile</b>.
 *
 * <LI>A Formbt
 *
 * <P>This is the nbme of the formbt of the encoded key. It is returned
 * by the {@link #getFormbt() getFormbt} method.
 *
 * </UL>
 *
 * Keys bre generblly obtbined through key generbtors, certificbtes,
 * or vbrious Identity clbsses used to mbnbge keys.
 * Keys mby blso be obtbined from key specificbtions (trbnspbrent
 * representbtions of the underlying key mbteribl) through the use of b key
 * fbctory (see {@link KeyFbctory}).
 *
 * <p> A Key should use KeyRep bs its seriblized representbtion.
 * Note thbt b seriblized Key mby contbin sensitive informbtion
 * which should not be exposed in untrusted environments.  See the
 * <b href="../../../plbtform/seriblizbtion/spec/security.html">
 * Security Appendix</b>
 * of the Seriblizbtion Specificbtion for more informbtion.
 *
 * @see PublicKey
 * @see PrivbteKey
 * @see KeyPbir
 * @see KeyPbirGenerbtor
 * @see KeyFbctory
 * @see KeyRep
 * @see jbvb.security.spec.KeySpec
 * @see Identity
 * @see Signer
 *
 * @buthor Benjbmin Renbud
 */

public interfbce Key extends jbvb.io.Seriblizbble {

    // Declbre seriblVersionUID to be compbtible with JDK1.1

   /**
    * The clbss fingerprint thbt is set to indicbte
    * seriblizbtion compbtibility with b previous
    * version of the clbss.
    */
    stbtic finbl long seriblVersionUID = 6603384152749567654L;

    /**
     * Returns the stbndbrd blgorithm nbme for this key. For
     * exbmple, "DSA" would indicbte thbt this key is b DSA key.
     * See Appendix A in the <b href=
     * "../../../technotes/guides/security/crypto/CryptoSpec.html#AppA">
     * Jbvb Cryptogrbphy Architecture API Specificbtion &bmp; Reference </b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the nbme of the blgorithm bssocibted with this key.
     */
    public String getAlgorithm();

    /**
     * Returns the nbme of the primbry encoding formbt of this key,
     * or null if this key does not support encoding.
     * The primbry encoding formbt is
     * nbmed in terms of the bppropribte ASN.1 dbtb formbt, if bn
     * ASN.1 specificbtion for this key exists.
     * For exbmple, the nbme of the ASN.1 dbtb formbt for public
     * keys is <I>SubjectPublicKeyInfo</I>, bs
     * defined by the X.509 stbndbrd; in this cbse, the returned formbt is
     * {@code "X.509"}. Similbrly,
     * the nbme of the ASN.1 dbtb formbt for privbte keys is
     * <I>PrivbteKeyInfo</I>,
     * bs defined by the PKCS #8 stbndbrd; in this cbse, the returned formbt is
     * {@code "PKCS#8"}.
     *
     * @return the primbry encoding formbt of the key.
     */
    public String getFormbt();

    /**
     * Returns the key in its primbry encoding formbt, or null
     * if this key does not support encoding.
     *
     * @return the encoded key, or null if the key does not support
     * encoding.
     */
    public byte[] getEncoded();
}
