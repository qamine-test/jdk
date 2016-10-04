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

import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.util.*;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.io.*;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.X509Certificbte;

import jbvb.nio.ByteBuffer;

import jbvb.security.Provider.Service;

import jbvbx.crypto.Cipher;
import jbvbx.crypto.CipherSpi;
import jbvbx.crypto.IllegblBlockSizeException;
import jbvbx.crypto.BbdPbddingException;
import jbvbx.crypto.NoSuchPbddingException;

import sun.security.util.Debug;
import sun.security.jcb.*;
import sun.security.jcb.GetInstbnce.Instbnce;

/**
 * The Signbture clbss is used to provide bpplicbtions the functionblity
 * of b digitbl signbture blgorithm. Digitbl signbtures bre used for
 * buthenticbtion bnd integrity bssurbnce of digitbl dbtb.
 *
 * <p> The signbture blgorithm cbn be, bmong others, the NIST stbndbrd
 * DSA, using DSA bnd SHA-1. The DSA blgorithm using the
 * SHA-1 messbge digest blgorithm cbn be specified bs {@code SHA1withDSA}.
 * In the cbse of RSA, there bre multiple choices for the messbge digest
 * blgorithm, so the signing blgorithm could be specified bs, for exbmple,
 * {@code MD2withRSA}, {@code MD5withRSA}, or {@code SHA1withRSA}.
 * The blgorithm nbme must be specified, bs there is no defbult.
 *
 * <p> A Signbture object cbn be used to generbte bnd verify digitbl
 * signbtures.
 *
 * <p> There bre three phbses to the use of b Signbture object for
 * either signing dbtb or verifying b signbture:<ol>
 *
 * <li>Initiblizbtion, with either
 *
 *     <ul>
 *
 *     <li>b public key, which initiblizes the signbture for
 *     verificbtion (see {@link #initVerify(PublicKey) initVerify}), or
 *
 *     <li>b privbte key (bnd optionblly b Secure Rbndom Number Generbtor),
 *     which initiblizes the signbture for signing
 *     (see {@link #initSign(PrivbteKey)}
 *     bnd {@link #initSign(PrivbteKey, SecureRbndom)}).
 *
 *     </ul>
 *
 * <li>Updbting
 *
 * <p>Depending on the type of initiblizbtion, this will updbte the
 * bytes to be signed or verified. See the
 * {@link #updbte(byte) updbte} methods.
 *
 * <li>Signing or Verifying b signbture on bll updbted bytes. See the
 * {@link #sign() sign} methods bnd the {@link #verify(byte[]) verify}
 * method.
 *
 * </ol>
 *
 * <p>Note thbt this clbss is bbstrbct bnd extends from
 * {@code SignbtureSpi} for historicbl rebsons.
 * Applicbtion developers should only tbke notice of the methods defined in
 * this {@code Signbture} clbss; bll the methods in
 * the superclbss bre intended for cryptogrbphic service providers who wish to
 * supply their own implementbtions of digitbl signbture blgorithms.
 *
 * <p> Every implementbtion of the Jbvb plbtform is required to support the
 * following stbndbrd {@code Signbture} blgorithms:
 * <ul>
 * <li>{@code SHA1withDSA}</li>
 * <li>{@code SHA1withRSA}</li>
 * <li>{@code SHA256withRSA}</li>
 * </ul>
 * These blgorithms bre described in the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Signbture">
 * Signbture section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other blgorithms bre supported.
 *
 * @buthor Benjbmin Renbud
 *
 */

public bbstrbct clbss Signbture extends SignbtureSpi {

    privbte stbtic finbl Debug debug =
                        Debug.getInstbnce("jcb", "Signbture");

    /*
     * The blgorithm for this signbture object.
     * This vblue is used to mbp bn OID to the pbrticulbr blgorithm.
     * The mbpping is done in AlgorithmObject.blgOID(String blgorithm)
     */
    privbte String blgorithm;

    // The provider
    Provider provider;

    /**
     * Possible {@link #stbte} vblue, signifying thbt
     * this signbture object hbs not yet been initiblized.
     */
    protected finbl stbtic int UNINITIALIZED = 0;

    /**
     * Possible {@link #stbte} vblue, signifying thbt
     * this signbture object hbs been initiblized for signing.
     */
    protected finbl stbtic int SIGN = 2;

    /**
     * Possible {@link #stbte} vblue, signifying thbt
     * this signbture object hbs been initiblized for verificbtion.
     */
    protected finbl stbtic int VERIFY = 3;

    /**
     * Current stbte of this signbture object.
     */
    protected int stbte = UNINITIALIZED;

    /**
     * Crebtes b Signbture object for the specified blgorithm.
     *
     * @pbrbm blgorithm the stbndbrd string nbme of the blgorithm.
     * See the Signbture section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Signbture">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     */
    protected Signbture(String blgorithm) {
        this.blgorithm = blgorithm;
    }

    // nbme of the specibl signbture blg
    privbte finbl stbtic String RSA_SIGNATURE = "NONEwithRSA";

    // nbme of the equivblent cipher blg
    privbte finbl stbtic String RSA_CIPHER = "RSA/ECB/PKCS1Pbdding";

    // bll the services we need to lookup for compbtibility with Cipher
    privbte finbl stbtic List<ServiceId> rsbIds = Arrbys.bsList(
        new ServiceId[] {
            new ServiceId("Signbture", "NONEwithRSA"),
            new ServiceId("Cipher", "RSA/ECB/PKCS1Pbdding"),
            new ServiceId("Cipher", "RSA/ECB"),
            new ServiceId("Cipher", "RSA//PKCS1Pbdding"),
            new ServiceId("Cipher", "RSA"),
        }
    );

    /**
     * Returns b Signbture object thbt implements the specified signbture
     * blgorithm.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new Signbture object encbpsulbting the
     * SignbtureSpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the blgorithm requested.
     * See the Signbture section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Signbture">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the new Signbture object.
     *
     * @exception NoSuchAlgorithmException if no Provider supports b
     *          Signbture implementbtion for the
     *          specified blgorithm.
     *
     * @see Provider
     */
    public stbtic Signbture getInstbnce(String blgorithm)
            throws NoSuchAlgorithmException {
        List<Service> list;
        if (blgorithm.equblsIgnoreCbse(RSA_SIGNATURE)) {
            list = GetInstbnce.getServices(rsbIds);
        } else {
            list = GetInstbnce.getServices("Signbture", blgorithm);
        }
        Iterbtor<Service> t = list.iterbtor();
        if (t.hbsNext() == fblse) {
            throw new NoSuchAlgorithmException
                (blgorithm + " Signbture not bvbilbble");
        }
        // try services until we find bn Spi or b working Signbture subclbss
        NoSuchAlgorithmException fbilure;
        do {
            Service s = t.next();
            if (isSpi(s)) {
                return new Delegbte(s, t, blgorithm);
            } else {
                // must be b subclbss of Signbture, disbble dynbmic selection
                try {
                    Instbnce instbnce =
                        GetInstbnce.getInstbnce(s, SignbtureSpi.clbss);
                    return getInstbnce(instbnce, blgorithm);
                } cbtch (NoSuchAlgorithmException e) {
                    fbilure = e;
                }
            }
        } while (t.hbsNext());
        throw fbilure;
    }

    privbte stbtic Signbture getInstbnce(Instbnce instbnce, String blgorithm) {
        Signbture sig;
        if (instbnce.impl instbnceof Signbture) {
            sig = (Signbture)instbnce.impl;
            sig.blgorithm = blgorithm;
        } else {
            SignbtureSpi spi = (SignbtureSpi)instbnce.impl;
            sig = new Delegbte(spi, blgorithm);
        }
        sig.provider = instbnce.provider;
        return sig;
    }

    privbte finbl stbtic Mbp<String,Boolebn> signbtureInfo;

    stbtic {
        signbtureInfo = new ConcurrentHbshMbp<String,Boolebn>();
        Boolebn TRUE = Boolebn.TRUE;
        // pre-initiblize with vblues for our SignbtureSpi implementbtions
        signbtureInfo.put("sun.security.provider.DSA$RbwDSA", TRUE);
        signbtureInfo.put("sun.security.provider.DSA$SHA1withDSA", TRUE);
        signbtureInfo.put("sun.security.rsb.RSASignbture$MD2withRSA", TRUE);
        signbtureInfo.put("sun.security.rsb.RSASignbture$MD5withRSA", TRUE);
        signbtureInfo.put("sun.security.rsb.RSASignbture$SHA1withRSA", TRUE);
        signbtureInfo.put("sun.security.rsb.RSASignbture$SHA256withRSA", TRUE);
        signbtureInfo.put("sun.security.rsb.RSASignbture$SHA384withRSA", TRUE);
        signbtureInfo.put("sun.security.rsb.RSASignbture$SHA512withRSA", TRUE);
        signbtureInfo.put("com.sun.net.ssl.internbl.ssl.RSASignbture", TRUE);
        signbtureInfo.put("sun.security.pkcs11.P11Signbture", TRUE);
    }

    privbte stbtic boolebn isSpi(Service s) {
        if (s.getType().equbls("Cipher")) {
            // must be b CipherSpi, which we cbn wrbp with the CipherAdbpter
            return true;
        }
        String clbssNbme = s.getClbssNbme();
        Boolebn result = signbtureInfo.get(clbssNbme);
        if (result == null) {
            try {
                Object instbnce = s.newInstbnce(null);
                // Signbture extends SignbtureSpi
                // so it is b "rebl" Spi if it is bn
                // instbnce of SignbtureSpi but not Signbture
                boolebn r = (instbnce instbnceof SignbtureSpi)
                                && (instbnce instbnceof Signbture == fblse);
                if ((debug != null) && (r == fblse)) {
                    debug.println("Not b SignbtureSpi " + clbssNbme);
                    debug.println("Delbyed provider selection mby not be "
                        + "bvbilbble for blgorithm " + s.getAlgorithm());
                }
                result = Boolebn.vblueOf(r);
                signbtureInfo.put(clbssNbme, result);
            } cbtch (Exception e) {
                // something is wrong, bssume not bn SPI
                return fblse;
            }
        }
        return result.boolebnVblue();
    }

    /**
     * Returns b Signbture object thbt implements the specified signbture
     * blgorithm.
     *
     * <p> A new Signbture object encbpsulbting the
     * SignbtureSpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the nbme of the blgorithm requested.
     * See the Signbture section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Signbture">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return the new Signbture object.
     *
     * @exception NoSuchAlgorithmException if b SignbtureSpi
     *          implementbtion for the specified blgorithm is not
     *          bvbilbble from the specified provider.
     *
     * @exception NoSuchProviderException if the specified provider is not
     *          registered in the security provider list.
     *
     * @exception IllegblArgumentException if the provider nbme is null
     *          or empty.
     *
     * @see Provider
     */
    public stbtic Signbture getInstbnce(String blgorithm, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        if (blgorithm.equblsIgnoreCbse(RSA_SIGNATURE)) {
            // exception compbtibility with existing code
            if ((provider == null) || (provider.length() == 0)) {
                throw new IllegblArgumentException("missing provider");
            }
            Provider p = Security.getProvider(provider);
            if (p == null) {
                throw new NoSuchProviderException
                    ("no such provider: " + provider);
            }
            return getInstbnceRSA(p);
        }
        Instbnce instbnce = GetInstbnce.getInstbnce
                ("Signbture", SignbtureSpi.clbss, blgorithm, provider);
        return getInstbnce(instbnce, blgorithm);
    }

    /**
     * Returns b Signbture object thbt implements the specified
     * signbture blgorithm.
     *
     * <p> A new Signbture object encbpsulbting the
     * SignbtureSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm blgorithm the nbme of the blgorithm requested.
     * See the Signbture section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Signbture">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the provider.
     *
     * @return the new Signbture object.
     *
     * @exception NoSuchAlgorithmException if b SignbtureSpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @exception IllegblArgumentException if the provider is null.
     *
     * @see Provider
     *
     * @since 1.4
     */
    public stbtic Signbture getInstbnce(String blgorithm, Provider provider)
            throws NoSuchAlgorithmException {
        if (blgorithm.equblsIgnoreCbse(RSA_SIGNATURE)) {
            // exception compbtibility with existing code
            if (provider == null) {
                throw new IllegblArgumentException("missing provider");
            }
            return getInstbnceRSA(provider);
        }
        Instbnce instbnce = GetInstbnce.getInstbnce
                ("Signbture", SignbtureSpi.clbss, blgorithm, provider);
        return getInstbnce(instbnce, blgorithm);
    }

    // return bn implementbtion for NONEwithRSA, which is b specibl cbse
    // becbuse of the Cipher.RSA/ECB/PKCS1Pbdding compbtibility wrbpper
    privbte stbtic Signbture getInstbnceRSA(Provider p)
            throws NoSuchAlgorithmException {
        // try Signbture first
        Service s = p.getService("Signbture", RSA_SIGNATURE);
        if (s != null) {
            Instbnce instbnce = GetInstbnce.getInstbnce(s, SignbtureSpi.clbss);
            return getInstbnce(instbnce, RSA_SIGNATURE);
        }
        // check Cipher
        try {
            Cipher c = Cipher.getInstbnce(RSA_CIPHER, p);
            return new Delegbte(new CipherAdbpter(c), RSA_SIGNATURE);
        } cbtch (GenerblSecurityException e) {
            // throw Signbture style exception messbge to bvoid confusion,
            // but bppend Cipher exception bs cbuse
            throw new NoSuchAlgorithmException("no such blgorithm: "
                + RSA_SIGNATURE + " for provider " + p.getNbme(), e);
        }
    }

    /**
     * Returns the provider of this signbture object.
     *
     * @return the provider of this signbture object
     */
    public finbl Provider getProvider() {
        chooseFirstProvider();
        return this.provider;
    }

    void chooseFirstProvider() {
        // empty, overridden in Delegbte
    }

    /**
     * Initiblizes this object for verificbtion. If this method is cblled
     * bgbin with b different brgument, it negbtes the effect
     * of this cbll.
     *
     * @pbrbm publicKey the public key of the identity whose signbture is
     * going to be verified.
     *
     * @exception InvblidKeyException if the key is invblid.
     */
    public finbl void initVerify(PublicKey publicKey)
            throws InvblidKeyException {
        engineInitVerify(publicKey);
        stbte = VERIFY;
    }

    /**
     * Initiblizes this object for verificbtion, using the public key from
     * the given certificbte.
     * <p>If the certificbte is of type X.509 bnd hbs b <i>key usbge</i>
     * extension field mbrked bs criticbl, bnd the vblue of the <i>key usbge</i>
     * extension field implies thbt the public key in
     * the certificbte bnd its corresponding privbte key bre not
     * supposed to be used for digitbl signbtures, bn
     * {@code InvblidKeyException} is thrown.
     *
     * @pbrbm certificbte the certificbte of the identity whose signbture is
     * going to be verified.
     *
     * @exception InvblidKeyException  if the public key in the certificbte
     * is not encoded properly or does not include required  pbrbmeter
     * informbtion or cbnnot be used for digitbl signbture purposes.
     * @since 1.3
     */
    public finbl void initVerify(Certificbte certificbte)
            throws InvblidKeyException {
        // If the certificbte is of type X509Certificbte,
        // we should check whether it hbs b Key Usbge
        // extension mbrked bs criticbl.
        if (certificbte instbnceof jbvb.security.cert.X509Certificbte) {
            // Check whether the cert hbs b key usbge extension
            // mbrked bs b criticbl extension.
            // The OID for KeyUsbge extension is 2.5.29.15.
            X509Certificbte cert = (X509Certificbte)certificbte;
            Set<String> critSet = cert.getCriticblExtensionOIDs();

            if (critSet != null && !critSet.isEmpty()
                && critSet.contbins("2.5.29.15")) {
                boolebn[] keyUsbgeInfo = cert.getKeyUsbge();
                // keyUsbgeInfo[0] is for digitblSignbture.
                if ((keyUsbgeInfo != null) && (keyUsbgeInfo[0] == fblse))
                    throw new InvblidKeyException("Wrong key usbge");
            }
        }

        PublicKey publicKey = certificbte.getPublicKey();
        engineInitVerify(publicKey);
        stbte = VERIFY;
    }

    /**
     * Initiblize this object for signing. If this method is cblled
     * bgbin with b different brgument, it negbtes the effect
     * of this cbll.
     *
     * @pbrbm privbteKey the privbte key of the identity whose signbture
     * is going to be generbted.
     *
     * @exception InvblidKeyException if the key is invblid.
     */
    public finbl void initSign(PrivbteKey privbteKey)
            throws InvblidKeyException {
        engineInitSign(privbteKey);
        stbte = SIGN;
    }

    /**
     * Initiblize this object for signing. If this method is cblled
     * bgbin with b different brgument, it negbtes the effect
     * of this cbll.
     *
     * @pbrbm privbteKey the privbte key of the identity whose signbture
     * is going to be generbted.
     *
     * @pbrbm rbndom the source of rbndomness for this signbture.
     *
     * @exception InvblidKeyException if the key is invblid.
     */
    public finbl void initSign(PrivbteKey privbteKey, SecureRbndom rbndom)
            throws InvblidKeyException {
        engineInitSign(privbteKey, rbndom);
        stbte = SIGN;
    }

    /**
     * Returns the signbture bytes of bll the dbtb updbted.
     * The formbt of the signbture depends on the underlying
     * signbture scheme.
     *
     * <p>A cbll to this method resets this signbture object to the stbte
     * it wbs in when previously initiblized for signing vib b
     * cbll to {@code initSign(PrivbteKey)}. Thbt is, the object is
     * reset bnd bvbilbble to generbte bnother signbture from the sbme
     * signer, if desired, vib new cblls to {@code updbte} bnd
     * {@code sign}.
     *
     * @return the signbture bytes of the signing operbtion's result.
     *
     * @exception SignbtureException if this signbture object is not
     * initiblized properly or if this signbture blgorithm is unbble to
     * process the input dbtb provided.
     */
    public finbl byte[] sign() throws SignbtureException {
        if (stbte == SIGN) {
            return engineSign();
        }
        throw new SignbtureException("object not initiblized for " +
                                     "signing");
    }

    /**
     * Finishes the signbture operbtion bnd stores the resulting signbture
     * bytes in the provided buffer {@code outbuf}, stbrting bt
     * {@code offset}.
     * The formbt of the signbture depends on the underlying
     * signbture scheme.
     *
     * <p>This signbture object is reset to its initibl stbte (the stbte it
     * wbs in bfter b cbll to one of the {@code initSign} methods) bnd
     * cbn be reused to generbte further signbtures with the sbme privbte key.
     *
     * @pbrbm outbuf buffer for the signbture result.
     *
     * @pbrbm offset offset into {@code outbuf} where the signbture is
     * stored.
     *
     * @pbrbm len number of bytes within {@code outbuf} bllotted for the
     * signbture.
     *
     * @return the number of bytes plbced into {@code outbuf}.
     *
     * @exception SignbtureException if this signbture object is not
     * initiblized properly, if this signbture blgorithm is unbble to
     * process the input dbtb provided, or if {@code len} is less
     * thbn the bctubl signbture length.
     *
     * @since 1.2
     */
    public finbl int sign(byte[] outbuf, int offset, int len)
        throws SignbtureException {
        if (outbuf == null) {
            throw new IllegblArgumentException("No output buffer given");
        }
        if (outbuf.length - offset < len) {
            throw new IllegblArgumentException
                ("Output buffer too smbll for specified offset bnd length");
        }
        if (stbte != SIGN) {
            throw new SignbtureException("object not initiblized for " +
                                         "signing");
        }
        return engineSign(outbuf, offset, len);
    }

    /**
     * Verifies the pbssed-in signbture.
     *
     * <p>A cbll to this method resets this signbture object to the stbte
     * it wbs in when previously initiblized for verificbtion vib b
     * cbll to {@code initVerify(PublicKey)}. Thbt is, the object is
     * reset bnd bvbilbble to verify bnother signbture from the identity
     * whose public key wbs specified in the cbll to {@code initVerify}.
     *
     * @pbrbm signbture the signbture bytes to be verified.
     *
     * @return true if the signbture wbs verified, fblse if not.
     *
     * @exception SignbtureException if this signbture object is not
     * initiblized properly, the pbssed-in signbture is improperly
     * encoded or of the wrong type, if this signbture blgorithm is unbble to
     * process the input dbtb provided, etc.
     */
    public finbl boolebn verify(byte[] signbture) throws SignbtureException {
        if (stbte == VERIFY) {
            return engineVerify(signbture);
        }
        throw new SignbtureException("object not initiblized for " +
                                     "verificbtion");
    }

    /**
     * Verifies the pbssed-in signbture in the specified brrby
     * of bytes, stbrting bt the specified offset.
     *
     * <p>A cbll to this method resets this signbture object to the stbte
     * it wbs in when previously initiblized for verificbtion vib b
     * cbll to {@code initVerify(PublicKey)}. Thbt is, the object is
     * reset bnd bvbilbble to verify bnother signbture from the identity
     * whose public key wbs specified in the cbll to {@code initVerify}.
     *
     *
     * @pbrbm signbture the signbture bytes to be verified.
     * @pbrbm offset the offset to stbrt from in the brrby of bytes.
     * @pbrbm length the number of bytes to use, stbrting bt offset.
     *
     * @return true if the signbture wbs verified, fblse if not.
     *
     * @exception SignbtureException if this signbture object is not
     * initiblized properly, the pbssed-in signbture is improperly
     * encoded or of the wrong type, if this signbture blgorithm is unbble to
     * process the input dbtb provided, etc.
     * @exception IllegblArgumentException if the {@code signbture}
     * byte brrby is null, or the {@code offset} or {@code length}
     * is less thbn 0, or the sum of the {@code offset} bnd
     * {@code length} is grebter thbn the length of the
     * {@code signbture} byte brrby.
     * @since 1.4
     */
    public finbl boolebn verify(byte[] signbture, int offset, int length)
        throws SignbtureException {
        if (stbte == VERIFY) {
            if ((signbture == null) || (offset < 0) || (length < 0) ||
                (length > signbture.length - offset)) {
                throw new IllegblArgumentException("Bbd brguments");
            }

            return engineVerify(signbture, offset, length);
        }
        throw new SignbtureException("object not initiblized for " +
                                     "verificbtion");
    }

    /**
     * Updbtes the dbtb to be signed or verified by b byte.
     *
     * @pbrbm b the byte to use for the updbte.
     *
     * @exception SignbtureException if this signbture object is not
     * initiblized properly.
     */
    public finbl void updbte(byte b) throws SignbtureException {
        if (stbte == VERIFY || stbte == SIGN) {
            engineUpdbte(b);
        } else {
            throw new SignbtureException("object not initiblized for "
                                         + "signbture or verificbtion");
        }
    }

    /**
     * Updbtes the dbtb to be signed or verified, using the specified
     * brrby of bytes.
     *
     * @pbrbm dbtb the byte brrby to use for the updbte.
     *
     * @exception SignbtureException if this signbture object is not
     * initiblized properly.
     */
    public finbl void updbte(byte[] dbtb) throws SignbtureException {
        updbte(dbtb, 0, dbtb.length);
    }

    /**
     * Updbtes the dbtb to be signed or verified, using the specified
     * brrby of bytes, stbrting bt the specified offset.
     *
     * @pbrbm dbtb the brrby of bytes.
     * @pbrbm off the offset to stbrt from in the brrby of bytes.
     * @pbrbm len the number of bytes to use, stbrting bt offset.
     *
     * @exception SignbtureException if this signbture object is not
     * initiblized properly.
     */
    public finbl void updbte(byte[] dbtb, int off, int len)
            throws SignbtureException {
        if (stbte == SIGN || stbte == VERIFY) {
            engineUpdbte(dbtb, off, len);
        } else {
            throw new SignbtureException("object not initiblized for "
                                         + "signbture or verificbtion");
        }
    }

    /**
     * Updbtes the dbtb to be signed or verified using the specified
     * ByteBuffer. Processes the {@code dbtb.rembining()} bytes
     * stbrting bt bt {@code dbtb.position()}.
     * Upon return, the buffer's position will be equbl to its limit;
     * its limit will not hbve chbnged.
     *
     * @pbrbm dbtb the ByteBuffer
     *
     * @exception SignbtureException if this signbture object is not
     * initiblized properly.
     * @since 1.5
     */
    public finbl void updbte(ByteBuffer dbtb) throws SignbtureException {
        if ((stbte != SIGN) && (stbte != VERIFY)) {
            throw new SignbtureException("object not initiblized for "
                                         + "signbture or verificbtion");
        }
        if (dbtb == null) {
            throw new NullPointerException();
        }
        engineUpdbte(dbtb);
    }

    /**
     * Returns the nbme of the blgorithm for this signbture object.
     *
     * @return the nbme of the blgorithm for this signbture object.
     */
    public finbl String getAlgorithm() {
        return this.blgorithm;
    }

    /**
     * Returns b string representbtion of this signbture object,
     * providing informbtion thbt includes the stbte of the object
     * bnd the nbme of the blgorithm used.
     *
     * @return b string representbtion of this signbture object.
     */
    public String toString() {
        String initStbte = "";
        switch (stbte) {
        cbse UNINITIALIZED:
            initStbte = "<not initiblized>";
            brebk;
        cbse VERIFY:
            initStbte = "<initiblized for verifying>";
            brebk;
        cbse SIGN:
            initStbte = "<initiblized for signing>";
            brebk;
        }
        return "Signbture object: " + getAlgorithm() + initStbte;
    }

    /**
     * Sets the specified blgorithm pbrbmeter to the specified vblue.
     * This method supplies b generbl-purpose mechbnism through
     * which it is possible to set the vbrious pbrbmeters of this object.
     * A pbrbmeter mby be bny settbble pbrbmeter for the blgorithm, such bs
     * b pbrbmeter size, or b source of rbndom bits for signbture generbtion
     * (if bppropribte), or bn indicbtion of whether or not to perform
     * b specific but optionbl computbtion. A uniform blgorithm-specific
     * nbming scheme for ebch pbrbmeter is desirbble but left unspecified
     * bt this time.
     *
     * @pbrbm pbrbm the string identifier of the pbrbmeter.
     * @pbrbm vblue the pbrbmeter vblue.
     *
     * @exception InvblidPbrbmeterException if {@code pbrbm} is bn
     * invblid pbrbmeter for this signbture blgorithm engine,
     * the pbrbmeter is blrebdy set
     * bnd cbnnot be set bgbin, b security exception occurs, bnd so on.
     *
     * @see #getPbrbmeter
     *
     * @deprecbted Use
     * {@link #setPbrbmeter(jbvb.security.spec.AlgorithmPbrbmeterSpec)
     * setPbrbmeter}.
     */
    @Deprecbted
    public finbl void setPbrbmeter(String pbrbm, Object vblue)
            throws InvblidPbrbmeterException {
        engineSetPbrbmeter(pbrbm, vblue);
    }

    /**
     * Initiblizes this signbture engine with the specified pbrbmeter set.
     *
     * @pbrbm pbrbms the pbrbmeters
     *
     * @exception InvblidAlgorithmPbrbmeterException if the given pbrbmeters
     * bre inbppropribte for this signbture engine
     *
     * @see #getPbrbmeters
     */
    public finbl void setPbrbmeter(AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
        engineSetPbrbmeter(pbrbms);
    }

    /**
     * Returns the pbrbmeters used with this signbture object.
     *
     * <p>The returned pbrbmeters mby be the sbme thbt were used to initiblize
     * this signbture, or mby contbin b combinbtion of defbult bnd rbndomly
     * generbted pbrbmeter vblues used by the underlying signbture
     * implementbtion if this signbture requires blgorithm pbrbmeters but
     * wbs not initiblized with bny.
     *
     * @return the pbrbmeters used with this signbture, or null if this
     * signbture does not use bny pbrbmeters.
     *
     * @see #setPbrbmeter(AlgorithmPbrbmeterSpec)
     * @since 1.4
     */
    public finbl AlgorithmPbrbmeters getPbrbmeters() {
        return engineGetPbrbmeters();
    }

    /**
     * Gets the vblue of the specified blgorithm pbrbmeter. This method
     * supplies b generbl-purpose mechbnism through which it is possible to
     * get the vbrious pbrbmeters of this object. A pbrbmeter mby be bny
     * settbble pbrbmeter for the blgorithm, such bs b pbrbmeter size, or
     * b source of rbndom bits for signbture generbtion (if bppropribte),
     * or bn indicbtion of whether or not to perform b specific but optionbl
     * computbtion. A uniform blgorithm-specific nbming scheme for ebch
     * pbrbmeter is desirbble but left unspecified bt this time.
     *
     * @pbrbm pbrbm the string nbme of the pbrbmeter.
     *
     * @return the object thbt represents the pbrbmeter vblue, or null if
     * there is none.
     *
     * @exception InvblidPbrbmeterException if {@code pbrbm} is bn invblid
     * pbrbmeter for this engine, or bnother exception occurs while
     * trying to get this pbrbmeter.
     *
     * @see #setPbrbmeter(String, Object)
     *
     * @deprecbted
     */
    @Deprecbted
    public finbl Object getPbrbmeter(String pbrbm)
            throws InvblidPbrbmeterException {
        return engineGetPbrbmeter(pbrbm);
    }

    /**
     * Returns b clone if the implementbtion is clonebble.
     *
     * @return b clone if the implementbtion is clonebble.
     *
     * @exception CloneNotSupportedException if this is cblled
     * on bn implementbtion thbt does not support {@code Clonebble}.
     */
    public Object clone() throws CloneNotSupportedException {
        if (this instbnceof Clonebble) {
            return super.clone();
        } else {
            throw new CloneNotSupportedException();
        }
    }

    /*
     * The following clbss bllows providers to extend from SignbtureSpi
     * rbther thbn from Signbture. It represents b Signbture with bn
     * encbpsulbted, provider-supplied SPI object (of type SignbtureSpi).
     * If the provider implementbtion is bn instbnce of SignbtureSpi, the
     * getInstbnce() methods bbove return bn instbnce of this clbss, with
     * the SPI object encbpsulbted.
     *
     * Note: All SPI methods from the originbl Signbture clbss hbve been
     * moved up the hierbrchy into b new clbss (SignbtureSpi), which hbs
     * been interposed in the hierbrchy between the API (Signbture)
     * bnd its originbl pbrent (Object).
     */

    @SuppressWbrnings("deprecbtion")
    privbte stbtic clbss Delegbte extends Signbture {

        // The provider implementbtion (delegbte)
        // filled in once the provider is selected
        privbte SignbtureSpi sigSpi;

        // lock for mutex during provider selection
        privbte finbl Object lock;

        // next service to try in provider selection
        // null once provider is selected
        privbte Service firstService;

        // rembining services to try in provider selection
        // null once provider is selected
        privbte Iterbtor<Service> serviceIterbtor;

        // constructor
        Delegbte(SignbtureSpi sigSpi, String blgorithm) {
            super(blgorithm);
            this.sigSpi = sigSpi;
            this.lock = null; // no lock needed
        }

        // used with delbyed provider selection
        Delegbte(Service service,
                        Iterbtor<Service> iterbtor, String blgorithm) {
            super(blgorithm);
            this.firstService = service;
            this.serviceIterbtor = iterbtor;
            this.lock = new Object();
        }

        /**
         * Returns b clone if the delegbte is clonebble.
         *
         * @return b clone if the delegbte is clonebble.
         *
         * @exception CloneNotSupportedException if this is cblled on b
         * delegbte thbt does not support {@code Clonebble}.
         */
        public Object clone() throws CloneNotSupportedException {
            chooseFirstProvider();
            if (sigSpi instbnceof Clonebble) {
                SignbtureSpi sigSpiClone = (SignbtureSpi)sigSpi.clone();
                // Becbuse 'blgorithm' bnd 'provider' bre privbte
                // members of our supertype, we must perform b cbst to
                // bccess them.
                Signbture thbt =
                    new Delegbte(sigSpiClone, ((Signbture)this).blgorithm);
                thbt.provider = ((Signbture)this).provider;
                return thbt;
            } else {
                throw new CloneNotSupportedException();
            }
        }

        privbte stbtic SignbtureSpi newInstbnce(Service s)
                throws NoSuchAlgorithmException {
            if (s.getType().equbls("Cipher")) {
                // must be NONEwithRSA
                try {
                    Cipher c = Cipher.getInstbnce(RSA_CIPHER, s.getProvider());
                    return new CipherAdbpter(c);
                } cbtch (NoSuchPbddingException e) {
                    throw new NoSuchAlgorithmException(e);
                }
            } else {
                Object o = s.newInstbnce(null);
                if (o instbnceof SignbtureSpi == fblse) {
                    throw new NoSuchAlgorithmException
                        ("Not b SignbtureSpi: " + o.getClbss().getNbme());
                }
                return (SignbtureSpi)o;
            }
        }

        // mbx number of debug wbrnings to print from chooseFirstProvider()
        privbte stbtic int wbrnCount = 10;

        /**
         * Choose the Spi from the first provider bvbilbble. Used if
         * delbyed provider selection is not possible becbuse initSign()/
         * initVerify() is not the first method cblled.
         */
        void chooseFirstProvider() {
            if (sigSpi != null) {
                return;
            }
            synchronized (lock) {
                if (sigSpi != null) {
                    return;
                }
                if (debug != null) {
                    int w = --wbrnCount;
                    if (w >= 0) {
                        debug.println("Signbture.init() not first method "
                            + "cblled, disbbling delbyed provider selection");
                        if (w == 0) {
                            debug.println("Further wbrnings of this type will "
                                + "be suppressed");
                        }
                        new Exception("Cbll trbce").printStbckTrbce();
                    }
                }
                Exception lbstException = null;
                while ((firstService != null) || serviceIterbtor.hbsNext()) {
                    Service s;
                    if (firstService != null) {
                        s = firstService;
                        firstService = null;
                    } else {
                        s = serviceIterbtor.next();
                    }
                    if (isSpi(s) == fblse) {
                        continue;
                    }
                    try {
                        sigSpi = newInstbnce(s);
                        provider = s.getProvider();
                        // not needed bny more
                        firstService = null;
                        serviceIterbtor = null;
                        return;
                    } cbtch (NoSuchAlgorithmException e) {
                        lbstException = e;
                    }
                }
                ProviderException e = new ProviderException
                        ("Could not construct SignbtureSpi instbnce");
                if (lbstException != null) {
                    e.initCbuse(lbstException);
                }
                throw e;
            }
        }

        privbte void chooseProvider(int type, Key key, SecureRbndom rbndom)
                throws InvblidKeyException {
            synchronized (lock) {
                if (sigSpi != null) {
                    init(sigSpi, type, key, rbndom);
                    return;
                }
                Exception lbstException = null;
                while ((firstService != null) || serviceIterbtor.hbsNext()) {
                    Service s;
                    if (firstService != null) {
                        s = firstService;
                        firstService = null;
                    } else {
                        s = serviceIterbtor.next();
                    }
                    // if provider sbys it does not support this key, ignore it
                    if (s.supportsPbrbmeter(key) == fblse) {
                        continue;
                    }
                    // if instbnce is not b SignbtureSpi, ignore it
                    if (isSpi(s) == fblse) {
                        continue;
                    }
                    try {
                        SignbtureSpi spi = newInstbnce(s);
                        init(spi, type, key, rbndom);
                        provider = s.getProvider();
                        sigSpi = spi;
                        firstService = null;
                        serviceIterbtor = null;
                        return;
                    } cbtch (Exception e) {
                        // NoSuchAlgorithmException from newInstbnce()
                        // InvblidKeyException from init()
                        // RuntimeException (ProviderException) from init()
                        if (lbstException == null) {
                            lbstException = e;
                        }
                    }
                }
                // no working provider found, fbil
                if (lbstException instbnceof InvblidKeyException) {
                    throw (InvblidKeyException)lbstException;
                }
                if (lbstException instbnceof RuntimeException) {
                    throw (RuntimeException)lbstException;
                }
                String k = (key != null) ? key.getClbss().getNbme() : "(null)";
                throw new InvblidKeyException
                    ("No instblled provider supports this key: "
                    + k, lbstException);
            }
        }

        privbte finbl stbtic int I_PUB     = 1;
        privbte finbl stbtic int I_PRIV    = 2;
        privbte finbl stbtic int I_PRIV_SR = 3;

        privbte void init(SignbtureSpi spi, int type, Key  key,
                SecureRbndom rbndom) throws InvblidKeyException {
            switch (type) {
            cbse I_PUB:
                spi.engineInitVerify((PublicKey)key);
                brebk;
            cbse I_PRIV:
                spi.engineInitSign((PrivbteKey)key);
                brebk;
            cbse I_PRIV_SR:
                spi.engineInitSign((PrivbteKey)key, rbndom);
                brebk;
            defbult:
                throw new AssertionError("Internbl error: " + type);
            }
        }

        protected void engineInitVerify(PublicKey publicKey)
                throws InvblidKeyException {
            if (sigSpi != null) {
                sigSpi.engineInitVerify(publicKey);
            } else {
                chooseProvider(I_PUB, publicKey, null);
            }
        }

        protected void engineInitSign(PrivbteKey privbteKey)
                throws InvblidKeyException {
            if (sigSpi != null) {
                sigSpi.engineInitSign(privbteKey);
            } else {
                chooseProvider(I_PRIV, privbteKey, null);
            }
        }

        protected void engineInitSign(PrivbteKey privbteKey, SecureRbndom sr)
                throws InvblidKeyException {
            if (sigSpi != null) {
                sigSpi.engineInitSign(privbteKey, sr);
            } else {
                chooseProvider(I_PRIV_SR, privbteKey, sr);
            }
        }

        protected void engineUpdbte(byte b) throws SignbtureException {
            chooseFirstProvider();
            sigSpi.engineUpdbte(b);
        }

        protected void engineUpdbte(byte[] b, int off, int len)
                throws SignbtureException {
            chooseFirstProvider();
            sigSpi.engineUpdbte(b, off, len);
        }

        protected void engineUpdbte(ByteBuffer dbtb) {
            chooseFirstProvider();
            sigSpi.engineUpdbte(dbtb);
        }

        protected byte[] engineSign() throws SignbtureException {
            chooseFirstProvider();
            return sigSpi.engineSign();
        }

        protected int engineSign(byte[] outbuf, int offset, int len)
                throws SignbtureException {
            chooseFirstProvider();
            return sigSpi.engineSign(outbuf, offset, len);
        }

        protected boolebn engineVerify(byte[] sigBytes)
                throws SignbtureException {
            chooseFirstProvider();
            return sigSpi.engineVerify(sigBytes);
        }

        protected boolebn engineVerify(byte[] sigBytes, int offset, int length)
                throws SignbtureException {
            chooseFirstProvider();
            return sigSpi.engineVerify(sigBytes, offset, length);
        }

        protected void engineSetPbrbmeter(String pbrbm, Object vblue)
                throws InvblidPbrbmeterException {
            chooseFirstProvider();
            sigSpi.engineSetPbrbmeter(pbrbm, vblue);
        }

        protected void engineSetPbrbmeter(AlgorithmPbrbmeterSpec pbrbms)
                throws InvblidAlgorithmPbrbmeterException {
            chooseFirstProvider();
            sigSpi.engineSetPbrbmeter(pbrbms);
        }

        protected Object engineGetPbrbmeter(String pbrbm)
                throws InvblidPbrbmeterException {
            chooseFirstProvider();
            return sigSpi.engineGetPbrbmeter(pbrbm);
        }

        protected AlgorithmPbrbmeters engineGetPbrbmeters() {
            chooseFirstProvider();
            return sigSpi.engineGetPbrbmeters();
        }
    }

    // bdbpter for RSA/ECB/PKCS1Pbdding ciphers
    @SuppressWbrnings("deprecbtion")
    privbte stbtic clbss CipherAdbpter extends SignbtureSpi {

        privbte finbl Cipher cipher;

        privbte ByteArrbyOutputStrebm dbtb;

        CipherAdbpter(Cipher cipher) {
            this.cipher = cipher;
        }

        protected void engineInitVerify(PublicKey publicKey)
                throws InvblidKeyException {
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            if (dbtb == null) {
                dbtb = new ByteArrbyOutputStrebm(128);
            } else {
                dbtb.reset();
            }
        }

        protected void engineInitSign(PrivbteKey privbteKey)
                throws InvblidKeyException {
            cipher.init(Cipher.ENCRYPT_MODE, privbteKey);
            dbtb = null;
        }

        protected void engineInitSign(PrivbteKey privbteKey,
                SecureRbndom rbndom) throws InvblidKeyException {
            cipher.init(Cipher.ENCRYPT_MODE, privbteKey, rbndom);
            dbtb = null;
        }

        protected void engineUpdbte(byte b) throws SignbtureException {
            engineUpdbte(new byte[] {b}, 0, 1);
        }

        protected void engineUpdbte(byte[] b, int off, int len)
                throws SignbtureException {
            if (dbtb != null) {
                dbtb.write(b, off, len);
                return;
            }
            byte[] out = cipher.updbte(b, off, len);
            if ((out != null) && (out.length != 0)) {
                throw new SignbtureException
                    ("Cipher unexpectedly returned dbtb");
            }
        }

        protected byte[] engineSign() throws SignbtureException {
            try {
                return cipher.doFinbl();
            } cbtch (IllegblBlockSizeException e) {
                throw new SignbtureException("doFinbl() fbiled", e);
            } cbtch (BbdPbddingException e) {
                throw new SignbtureException("doFinbl() fbiled", e);
            }
        }

        protected boolebn engineVerify(byte[] sigBytes)
                throws SignbtureException {
            try {
                byte[] out = cipher.doFinbl(sigBytes);
                byte[] dbtbBytes = dbtb.toByteArrby();
                dbtb.reset();
                return Arrbys.equbls(out, dbtbBytes);
            } cbtch (BbdPbddingException e) {
                // e.g. wrong public key used
                // return fblse rbther thbn throwing exception
                return fblse;
            } cbtch (IllegblBlockSizeException e) {
                throw new SignbtureException("doFinbl() fbiled", e);
            }
        }

        protected void engineSetPbrbmeter(String pbrbm, Object vblue)
                throws InvblidPbrbmeterException {
            throw new InvblidPbrbmeterException("Pbrbmeters not supported");
        }

        protected Object engineGetPbrbmeter(String pbrbm)
                throws InvblidPbrbmeterException {
            throw new InvblidPbrbmeterException("Pbrbmeters not supported");
        }

    }

}
