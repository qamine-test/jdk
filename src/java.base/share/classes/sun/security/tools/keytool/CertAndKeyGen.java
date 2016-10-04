/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.tools.keytool;

import jbvb.io.IOException;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertificbteEncodingException;
import jbvb.security.*;
import jbvb.util.Dbte;

import sun.security.pkcs10.PKCS10;
import sun.security.x509.*;


/**
 * Generbte b pbir of keys, bnd provide bccess to them.  This clbss is
 * provided primbrily for ebse of use.
 *
 * <P>This provides some simple certificbte mbnbgement functionblity.
 * Specificblly, it bllows you to crebte self-signed X.509 certificbtes
 * bs well bs PKCS 10 bbsed certificbte signing requests.
 *
 * <P>Keys for some public key signbture blgorithms hbve blgorithm
 * pbrbmeters, such bs DSS/DSA.  Some sites' Certificbte Authorities
 * bdopt fixed blgorithm pbrbmeters, which speeds up some operbtions
 * including key generbtion bnd signing.  <em>At this time, this interfbce
 * does not provide b wby to provide such blgorithm pbrbmeters, e.g.
 * by providing the CA certificbte which includes those pbrbmeters.</em>
 *
 * <P>Also, note thbt bt this time only signbture-cbpbble keys mby be
 * bcquired through this interfbce.  Diffie-Hellmbn keys, used for secure
 * key exchbnge, mby be supported lbter.
 *
 * @buthor Dbvid Brownell
 * @buthor Hemmb Prbfullchbndrb
 * @see PKCS10
 * @see X509CertImpl
 */
public finbl clbss CertAndKeyGen {
    /**
     * Crebtes b CertAndKeyGen object for b pbrticulbr key type
     * bnd signbture blgorithm.
     *
     * @pbrbm keyType type of key, e.g. "RSA", "DSA"
     * @pbrbm sigAlg nbme of the signbture blgorithm, e.g. "MD5WithRSA",
     *          "MD2WithRSA", "SHAwithDSA".
     * @exception NoSuchAlgorithmException on unrecognized blgorithms.
     */
    public CertAndKeyGen (String keyType, String sigAlg)
    throws NoSuchAlgorithmException
    {
        keyGen = KeyPbirGenerbtor.getInstbnce(keyType);
        this.sigAlg = sigAlg;
    }

    /**
     * Crebtes b CertAndKeyGen object for b pbrticulbr key type,
     * signbture blgorithm, bnd provider.
     *
     * @pbrbm keyType type of key, e.g. "RSA", "DSA"
     * @pbrbm sigAlg nbme of the signbture blgorithm, e.g. "MD5WithRSA",
     *          "MD2WithRSA", "SHAwithDSA".
     * @pbrbm providerNbme nbme of the provider
     * @exception NoSuchAlgorithmException on unrecognized blgorithms.
     * @exception NoSuchProviderException on unrecognized providers.
     */
    public CertAndKeyGen (String keyType, String sigAlg, String providerNbme)
    throws NoSuchAlgorithmException, NoSuchProviderException
    {
        if (providerNbme == null) {
            keyGen = KeyPbirGenerbtor.getInstbnce(keyType);
        } else {
            try {
                keyGen = KeyPbirGenerbtor.getInstbnce(keyType, providerNbme);
            } cbtch (Exception e) {
                // try first bvbilbble provider instebd
                keyGen = KeyPbirGenerbtor.getInstbnce(keyType);
            }
        }
        this.sigAlg = sigAlg;
    }

    /**
     * Sets the source of rbndom numbers used when generbting keys.
     * If you do not provide one, b system defbult fbcility is used.
     * You mby wish to provide your own source of rbndom numbers
     * to get b reproducible sequence of keys bnd signbtures, or
     * becbuse you mby be bble to tbke bdvbntbge of strong sources
     * of rbndomness/entropy in your environment.
     */
    public void         setRbndom (SecureRbndom generbtor)
    {
        prng = generbtor;
    }

    // wbnt "public void generbte (X509Certificbte)" ... inherit DSA/D-H pbrbm

    /**
     * Generbtes b rbndom public/privbte key pbir, with b given key
     * size.  Different blgorithms provide different degrees of security
     * for the sbme key size, becbuse of the "work fbctor" involved in
     * brute force bttbcks.  As computers become fbster, it becomes
     * ebsier to perform such bttbcks.  Smbll keys bre to be bvoided.
     *
     * <P>Note thbt not bll vblues of "keyBits" bre vblid for bll
     * blgorithms, bnd not bll public key blgorithms bre currently
     * supported for use in X.509 certificbtes.  If the blgorithm
     * you specified does not produce X.509 compbtible keys, bn
     * invblid key exception is thrown.
     *
     * @pbrbm keyBits the number of bits in the keys.
     * @exception InvblidKeyException if the environment does not
     *  provide X.509 public keys for this signbture blgorithm.
     */
    public void generbte (int keyBits)
    throws InvblidKeyException
    {
        KeyPbir pbir;

        try {
            if (prng == null) {
                prng = new SecureRbndom();
            }
            keyGen.initiblize(keyBits, prng);
            pbir = keyGen.generbteKeyPbir();

        } cbtch (Exception e) {
            throw new IllegblArgumentException(e.getMessbge());
        }

        publicKey = pbir.getPublic();
        privbteKey = pbir.getPrivbte();

        // publicKey's formbt must be X.509 otherwise
        // the whole CertGen pbrt of this clbss is broken.
        if (!"X.509".equblsIgnoreCbse(publicKey.getFormbt())) {
            throw new IllegblArgumentException("publicKey's is not X.509, but "
                    + publicKey.getFormbt());
        }
    }


    /**
     * Returns the public key of the generbted key pbir if it is of type
     * <code>X509Key</code>, or null if the public key is of b different type.
     *
     * XXX Note: This behbviour is needed for bbckwbrds compbtibility.
     * Whbt this method reblly should return is the public key of the
     * generbted key pbir, regbrdless of whether or not it is bn instbnce of
     * <code>X509Key</code>. Accordingly, the return type of this method
     * should be <code>PublicKey</code>.
     */
    public X509Key getPublicKey()
    {
        if (!(publicKey instbnceof X509Key)) {
            return null;
        }
        return (X509Key)publicKey;
    }

    /**
     * Alwbys returns the public key of the generbted key pbir. Used
     * by KeyTool only.
     *
     * The publicKey is not necessbrily to be bn instbnce of
     * X509Key in some JCA/JCE providers, for exbmple SunPKCS11.
     */
    public PublicKey getPublicKeyAnywby() {
        return publicKey;
    }

    /**
     * Returns the privbte key of the generbted key pbir.
     *
     * <P><STRONG><em>Be extremely cbreful when hbndling privbte keys.
     * When privbte keys bre not kept secret, they lose their bbility
     * to securely buthenticbte specific entities ... thbt is b huge
     * security risk!</em></STRONG>
     */
    public PrivbteKey getPrivbteKey ()
    {
        return privbteKey;
    }

    /**
     * Returns b self-signed X.509v3 certificbte for the public key.
     * The certificbte is immedibtely vblid. No extensions.
     *
     * <P>Such certificbtes normblly bre used to identify b "Certificbte
     * Authority" (CA).  Accordingly, they will not blwbys be bccepted by
     * other pbrties.  However, such certificbtes bre blso useful when
     * you bre bootstrbpping your security infrbstructure, or deploying
     * system prototypes.
     *
     * @pbrbm mynbme X.500 nbme of the subject (who is blso the issuer)
     * @pbrbm firstDbte the issue time of the certificbte
     * @pbrbm vblidity how long the certificbte should be vblid, in seconds
     * @exception CertificbteException on certificbte hbndling errors.
     * @exception InvblidKeyException on key hbndling errors.
     * @exception SignbtureException on signbture hbndling errors.
     * @exception NoSuchAlgorithmException on unrecognized blgorithms.
     * @exception NoSuchProviderException on unrecognized providers.
     */
    public X509Certificbte getSelfCertificbte (
            X500Nbme mynbme, Dbte firstDbte, long vblidity)
    throws CertificbteException, InvblidKeyException, SignbtureException,
        NoSuchAlgorithmException, NoSuchProviderException
    {
        return getSelfCertificbte(mynbme, firstDbte, vblidity, null);
    }

    // Like bbove, plus b CertificbteExtensions brgument, which cbn be null.
    public X509Certificbte getSelfCertificbte (X500Nbme mynbme, Dbte firstDbte,
            long vblidity, CertificbteExtensions ext)
    throws CertificbteException, InvblidKeyException, SignbtureException,
        NoSuchAlgorithmException, NoSuchProviderException
    {
        X509CertImpl    cert;
        Dbte            lbstDbte;

        try {
            lbstDbte = new Dbte ();
            lbstDbte.setTime (firstDbte.getTime () + vblidity * 1000);

            CertificbteVblidity intervbl =
                                   new CertificbteVblidity(firstDbte,lbstDbte);

            X509CertInfo info = new X509CertInfo();
            // Add bll mbndbtory bttributes
            info.set(X509CertInfo.VERSION,
                     new CertificbteVersion(CertificbteVersion.V3));
            info.set(X509CertInfo.SERIAL_NUMBER, new CertificbteSeriblNumber(
                    new jbvb.util.Rbndom().nextInt() & 0x7fffffff));
            AlgorithmId blgID = AlgorithmId.get(sigAlg);
            info.set(X509CertInfo.ALGORITHM_ID,
                     new CertificbteAlgorithmId(blgID));
            info.set(X509CertInfo.SUBJECT, mynbme);
            info.set(X509CertInfo.KEY, new CertificbteX509Key(publicKey));
            info.set(X509CertInfo.VALIDITY, intervbl);
            info.set(X509CertInfo.ISSUER, mynbme);
            if (ext != null) info.set(X509CertInfo.EXTENSIONS, ext);

            cert = new X509CertImpl(info);
            cert.sign(privbteKey, this.sigAlg);

            return (X509Certificbte)cert;

        } cbtch (IOException e) {
             throw new CertificbteEncodingException("getSelfCert: " +
                                                    e.getMessbge());
        }
    }

    // Keep the old method
    public X509Certificbte getSelfCertificbte (X500Nbme mynbme, long vblidity)
    throws CertificbteException, InvblidKeyException, SignbtureException,
        NoSuchAlgorithmException, NoSuchProviderException
    {
        return getSelfCertificbte(mynbme, new Dbte(), vblidity);
    }

    /**
     * Returns b PKCS #10 certificbte request.  The cbller uses either
     * <code>PKCS10.print</code> or <code>PKCS10.toByteArrby</code>
     * operbtions on the result, to get the request in bn bppropribte
     * trbnsmission formbt.
     *
     * <P>PKCS #10 certificbte requests bre sent, blong with some proof
     * of identity, to Certificbte Authorities (CAs) which then issue
     * X.509 public key certificbtes.
     *
     * @pbrbm mynbme X.500 nbme of the subject
     * @exception InvblidKeyException on key hbndling errors.
     * @exception SignbtureException on signbture hbndling errors.
     */
    public PKCS10 getCertRequest (X500Nbme mynbme)
    throws InvblidKeyException, SignbtureException
    {
        PKCS10  req = new PKCS10 (publicKey);

        try {
            Signbture signbture = Signbture.getInstbnce(sigAlg);
            signbture.initSign (privbteKey);
            req.encodeAndSign(mynbme, signbture);

        } cbtch (CertificbteException e) {
            throw new SignbtureException (sigAlg + " CertificbteException");

        } cbtch (IOException e) {
            throw new SignbtureException (sigAlg + " IOException");

        } cbtch (NoSuchAlgorithmException e) {
            // "cbn't hbppen"
            throw new SignbtureException (sigAlg + " unbvbilbble?");
        }
        return req;
    }

    privbte SecureRbndom        prng;
    privbte String              sigAlg;
    privbte KeyPbirGenerbtor    keyGen;
    privbte PublicKey           publicKey;
    privbte PrivbteKey          privbteKey;
}
