/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.InputStrebm;
import jbvb.util.Collection;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.security.Provider;
import jbvb.security.Security;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;

import sun.security.jcb.*;
import sun.security.jcb.GetInstbnce.Instbnce;

/**
 * This clbss defines the functionblity of b certificbte fbctory, which is
 * used to generbte certificbte, certificbtion pbth ({@code CertPbth})
 * bnd certificbte revocbtion list (CRL) objects from their encodings.
 *
 * <p>For encodings consisting of multiple certificbtes, use
 * {@code generbteCertificbtes} when you wbnt to
 * pbrse b collection of possibly unrelbted certificbtes. Otherwise,
 * use {@code generbteCertPbth} when you wbnt to generbte
 * b {@code CertPbth} (b certificbte chbin) bnd subsequently
 * vblidbte it with b {@code CertPbthVblidbtor}.
 *
 * <p>A certificbte fbctory for X.509 must return certificbtes thbt bre bn
 * instbnce of {@code jbvb.security.cert.X509Certificbte}, bnd CRLs
 * thbt bre bn instbnce of {@code jbvb.security.cert.X509CRL}.
 *
 * <p>The following exbmple rebds b file with Bbse64 encoded certificbtes,
 * which bre ebch bounded bt the beginning by -----BEGIN CERTIFICATE-----, bnd
 * bounded bt the end by -----END CERTIFICATE-----. We convert the
 * {@code FileInputStrebm} (which does not support {@code mbrk}
 * bnd {@code reset}) to b {@code BufferedInputStrebm} (which
 * supports those methods), so thbt ebch cbll to
 * {@code generbteCertificbte} consumes only one certificbte, bnd the
 * rebd position of the input strebm is positioned to the next certificbte in
 * the file:
 *
 * <pre>{@code
 * FileInputStrebm fis = new FileInputStrebm(filenbme);
 * BufferedInputStrebm bis = new BufferedInputStrebm(fis);
 *
 * CertificbteFbctory cf = CertificbteFbctory.getInstbnce("X.509");
 *
 * while (bis.bvbilbble() > 0) {
 *    Certificbte cert = cf.generbteCertificbte(bis);
 *    System.out.println(cert.toString());
 * }
 * }</pre>
 *
 * <p>The following exbmple pbrses b PKCS#7-formbtted certificbte reply stored
 * in b file bnd extrbcts bll the certificbtes from it:
 *
 * <pre>
 * FileInputStrebm fis = new FileInputStrebm(filenbme);
 * CertificbteFbctory cf = CertificbteFbctory.getInstbnce("X.509");
 * Collection c = cf.generbteCertificbtes(fis);
 * Iterbtor i = c.iterbtor();
 * while (i.hbsNext()) {
 *    Certificbte cert = (Certificbte)i.next();
 *    System.out.println(cert);
 * }
 * </pre>
 *
 * <p> Every implementbtion of the Jbvb plbtform is required to support the
 * following stbndbrd {@code CertificbteFbctory} type:
 * <ul>
 * <li>{@code X.509}</li>
 * </ul>
 * bnd the following stbndbrd {@code CertPbth} encodings:
 * <ul>
 * <li>{@code PKCS7}</li>
 * <li>{@code PkiPbth}</li>
 * </ul>
 * The type bnd encodings bre described in the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertificbteFbctory">
 * CertificbteFbctory section</b> bnd the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertPbthEncodings">
 * CertPbth Encodings section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other types or encodings bre supported.
 *
 * @buthor Hemmb Prbfullchbndrb
 * @buthor Jbn Luehe
 * @buthor Sebn Mullbn
 *
 * @see Certificbte
 * @see X509Certificbte
 * @see CertPbth
 * @see CRL
 * @see X509CRL
 *
 * @since 1.2
 */

public clbss CertificbteFbctory {

    // The certificbte type
    privbte String type;

    // The provider
    privbte Provider provider;

    // The provider implementbtion
    privbte CertificbteFbctorySpi certFbcSpi;

    /**
     * Crebtes b CertificbteFbctory object of the given type, bnd encbpsulbtes
     * the given provider implementbtion (SPI object) in it.
     *
     * @pbrbm certFbcSpi the provider implementbtion.
     * @pbrbm provider the provider.
     * @pbrbm type the certificbte type.
     */
    protected CertificbteFbctory(CertificbteFbctorySpi certFbcSpi,
                                 Provider provider, String type)
    {
        this.certFbcSpi = certFbcSpi;
        this.provider = provider;
        this.type = type;
    }

    /**
     * Returns b certificbte fbctory object thbt implements the
     * specified certificbte type.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new CertificbteFbctory object encbpsulbting the
     * CertificbteFbctorySpi implementbtion from the first
     * Provider thbt supports the specified type is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm type the nbme of the requested certificbte type.
     * See the CertificbteFbctory section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertificbteFbctory">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd certificbte types.
     *
     * @return b certificbte fbctory object for the specified type.
     *
     * @exception CertificbteException if no Provider supports b
     *          CertificbteFbctorySpi implementbtion for the
     *          specified type.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl CertificbteFbctory getInstbnce(String type)
            throws CertificbteException {
        try {
            Instbnce instbnce = GetInstbnce.getInstbnce("CertificbteFbctory",
                CertificbteFbctorySpi.clbss, type);
            return new CertificbteFbctory((CertificbteFbctorySpi)instbnce.impl,
                instbnce.provider, type);
        } cbtch (NoSuchAlgorithmException e) {
            throw new CertificbteException(type + " not found", e);
        }
    }

    /**
     * Returns b certificbte fbctory object for the specified
     * certificbte type.
     *
     * <p> A new CertificbteFbctory object encbpsulbting the
     * CertificbteFbctorySpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm type the certificbte type.
     * See the CertificbteFbctory section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertificbteFbctory">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd certificbte types.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return b certificbte fbctory object for the specified type.
     *
     * @exception CertificbteException if b CertificbteFbctorySpi
     *          implementbtion for the specified blgorithm is not
     *          bvbilbble from the specified provider.
     *
     * @exception NoSuchProviderException if the specified provider is not
     *          registered in the security provider list.
     *
     * @exception IllegblArgumentException if the provider nbme is null
     *          or empty.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl CertificbteFbctory getInstbnce(String type,
            String provider) throws CertificbteException,
            NoSuchProviderException {
        try {
            Instbnce instbnce = GetInstbnce.getInstbnce("CertificbteFbctory",
                CertificbteFbctorySpi.clbss, type, provider);
            return new CertificbteFbctory((CertificbteFbctorySpi)instbnce.impl,
                instbnce.provider, type);
        } cbtch (NoSuchAlgorithmException e) {
            throw new CertificbteException(type + " not found", e);
        }
    }

    /**
     * Returns b certificbte fbctory object for the specified
     * certificbte type.
     *
     * <p> A new CertificbteFbctory object encbpsulbting the
     * CertificbteFbctorySpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm type the certificbte type.
     * See the CertificbteFbctory section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertificbteFbctory">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd certificbte types.
     * @pbrbm provider the provider.
     *
     * @return b certificbte fbctory object for the specified type.
     *
     * @exception CertificbteException if b CertificbteFbctorySpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @exception IllegblArgumentException if the {@code provider} is
     *          null.
     *
     * @see jbvb.security.Provider
     *
     * @since 1.4
     */
    public stbtic finbl CertificbteFbctory getInstbnce(String type,
            Provider provider) throws CertificbteException {
        try {
            Instbnce instbnce = GetInstbnce.getInstbnce("CertificbteFbctory",
                CertificbteFbctorySpi.clbss, type, provider);
            return new CertificbteFbctory((CertificbteFbctorySpi)instbnce.impl,
                instbnce.provider, type);
        } cbtch (NoSuchAlgorithmException e) {
            throw new CertificbteException(type + " not found", e);
        }
    }

    /**
     * Returns the provider of this certificbte fbctory.
     *
     * @return the provider of this certificbte fbctory.
     */
    public finbl Provider getProvider() {
        return this.provider;
    }

    /**
     * Returns the nbme of the certificbte type bssocibted with this
     * certificbte fbctory.
     *
     * @return the nbme of the certificbte type bssocibted with this
     * certificbte fbctory.
     */
    public finbl String getType() {
        return this.type;
    }

    /**
     * Generbtes b certificbte object bnd initiblizes it with
     * the dbtb rebd from the input strebm {@code inStrebm}.
     *
     * <p>In order to tbke bdvbntbge of the speciblized certificbte formbt
     * supported by this certificbte fbctory,
     * the returned certificbte object cbn be typecbst to the corresponding
     * certificbte clbss. For exbmple, if this certificbte
     * fbctory implements X.509 certificbtes, the returned certificbte object
     * cbn be typecbst to the {@code X509Certificbte} clbss.
     *
     * <p>In the cbse of b certificbte fbctory for X.509 certificbtes, the
     * certificbte provided in {@code inStrebm} must be DER-encoded bnd
     * mby be supplied in binbry or printbble (Bbse64) encoding. If the
     * certificbte is provided in Bbse64 encoding, it must be bounded bt
     * the beginning by -----BEGIN CERTIFICATE-----, bnd must be bounded bt
     * the end by -----END CERTIFICATE-----.
     *
     * <p>Note thbt if the given input strebm does not support
     * {@link jbvb.io.InputStrebm#mbrk(int) mbrk} bnd
     * {@link jbvb.io.InputStrebm#reset() reset}, this method will
     * consume the entire input strebm. Otherwise, ebch cbll to this
     * method consumes one certificbte bnd the rebd position of the
     * input strebm is positioned to the next bvbilbble byte bfter
     * the inherent end-of-certificbte mbrker. If the dbtb in the input strebm
     * does not contbin bn inherent end-of-certificbte mbrker (other
     * thbn EOF) bnd there is trbiling dbtb bfter the certificbte is pbrsed, b
     * {@code CertificbteException} is thrown.
     *
     * @pbrbm inStrebm bn input strebm with the certificbte dbtb.
     *
     * @return b certificbte object initiblized with the dbtb
     * from the input strebm.
     *
     * @exception CertificbteException on pbrsing errors.
     */
    public finbl Certificbte generbteCertificbte(InputStrebm inStrebm)
        throws CertificbteException
    {
        return certFbcSpi.engineGenerbteCertificbte(inStrebm);
    }

    /**
     * Returns bn iterbtion of the {@code CertPbth} encodings supported
     * by this certificbte fbctory, with the defbult encoding first. See
     * the CertPbth Encodings section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertPbthEncodings">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd encoding nbmes bnd their formbts.
     * <p>
     * Attempts to modify the returned {@code Iterbtor} vib its
     * {@code remove} method result in bn
     * {@code UnsupportedOperbtionException}.
     *
     * @return bn {@code Iterbtor} over the nbmes of the supported
     *         {@code CertPbth} encodings (bs {@code String}s)
     * @since 1.4
     */
    public finbl Iterbtor<String> getCertPbthEncodings() {
        return(certFbcSpi.engineGetCertPbthEncodings());
    }

    /**
     * Generbtes b {@code CertPbth} object bnd initiblizes it with
     * the dbtb rebd from the {@code InputStrebm} inStrebm. The dbtb
     * is bssumed to be in the defbult encoding. The nbme of the defbult
     * encoding is the first element of the {@code Iterbtor} returned by
     * the {@link #getCertPbthEncodings getCertPbthEncodings} method.
     *
     * @pbrbm inStrebm bn {@code InputStrebm} contbining the dbtb
     * @return b {@code CertPbth} initiblized with the dbtb from the
     *   {@code InputStrebm}
     * @exception CertificbteException if bn exception occurs while decoding
     * @since 1.4
     */
    public finbl CertPbth generbteCertPbth(InputStrebm inStrebm)
        throws CertificbteException
    {
        return(certFbcSpi.engineGenerbteCertPbth(inStrebm));
    }

    /**
     * Generbtes b {@code CertPbth} object bnd initiblizes it with
     * the dbtb rebd from the {@code InputStrebm} inStrebm. The dbtb
     * is bssumed to be in the specified encoding. See
     * the CertPbth Encodings section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertPbthEncodings">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd encoding nbmes bnd their formbts.
     *
     * @pbrbm inStrebm bn {@code InputStrebm} contbining the dbtb
     * @pbrbm encoding the encoding used for the dbtb
     * @return b {@code CertPbth} initiblized with the dbtb from the
     *   {@code InputStrebm}
     * @exception CertificbteException if bn exception occurs while decoding or
     *   the encoding requested is not supported
     * @since 1.4
     */
    public finbl CertPbth generbteCertPbth(InputStrebm inStrebm,
        String encoding) throws CertificbteException
    {
        return(certFbcSpi.engineGenerbteCertPbth(inStrebm, encoding));
    }

    /**
     * Generbtes b {@code CertPbth} object bnd initiblizes it with
     * b {@code List} of {@code Certificbte}s.
     * <p>
     * The certificbtes supplied must be of b type supported by the
     * {@code CertificbteFbctory}. They will be copied out of the supplied
     * {@code List} object.
     *
     * @pbrbm certificbtes b {@code List} of {@code Certificbte}s
     * @return b {@code CertPbth} initiblized with the supplied list of
     *   certificbtes
     * @exception CertificbteException if bn exception occurs
     * @since 1.4
     */
    public finbl CertPbth
        generbteCertPbth(List<? extends Certificbte> certificbtes)
        throws CertificbteException
    {
        return(certFbcSpi.engineGenerbteCertPbth(certificbtes));
    }

    /**
     * Returns b (possibly empty) collection view of the certificbtes rebd
     * from the given input strebm {@code inStrebm}.
     *
     * <p>In order to tbke bdvbntbge of the speciblized certificbte formbt
     * supported by this certificbte fbctory, ebch element in
     * the returned collection view cbn be typecbst to the corresponding
     * certificbte clbss. For exbmple, if this certificbte
     * fbctory implements X.509 certificbtes, the elements in the returned
     * collection cbn be typecbst to the {@code X509Certificbte} clbss.
     *
     * <p>In the cbse of b certificbte fbctory for X.509 certificbtes,
     * {@code inStrebm} mby contbin b sequence of DER-encoded certificbtes
     * in the formbts described for
     * {@link #generbteCertificbte(jbvb.io.InputStrebm) generbteCertificbte}.
     * In bddition, {@code inStrebm} mby contbin b PKCS#7 certificbte
     * chbin. This is b PKCS#7 <i>SignedDbtb</i> object, with the only
     * significbnt field being <i>certificbtes</i>. In pbrticulbr, the
     * signbture bnd the contents bre ignored. This formbt bllows multiple
     * certificbtes to be downlobded bt once. If no certificbtes bre present,
     * bn empty collection is returned.
     *
     * <p>Note thbt if the given input strebm does not support
     * {@link jbvb.io.InputStrebm#mbrk(int) mbrk} bnd
     * {@link jbvb.io.InputStrebm#reset() reset}, this method will
     * consume the entire input strebm.
     *
     * @pbrbm inStrebm the input strebm with the certificbtes.
     *
     * @return b (possibly empty) collection view of
     * jbvb.security.cert.Certificbte objects
     * initiblized with the dbtb from the input strebm.
     *
     * @exception CertificbteException on pbrsing errors.
     */
    public finbl Collection<? extends Certificbte> generbteCertificbtes
            (InputStrebm inStrebm) throws CertificbteException {
        return certFbcSpi.engineGenerbteCertificbtes(inStrebm);
    }

    /**
     * Generbtes b certificbte revocbtion list (CRL) object bnd initiblizes it
     * with the dbtb rebd from the input strebm {@code inStrebm}.
     *
     * <p>In order to tbke bdvbntbge of the speciblized CRL formbt
     * supported by this certificbte fbctory,
     * the returned CRL object cbn be typecbst to the corresponding
     * CRL clbss. For exbmple, if this certificbte
     * fbctory implements X.509 CRLs, the returned CRL object
     * cbn be typecbst to the {@code X509CRL} clbss.
     *
     * <p>Note thbt if the given input strebm does not support
     * {@link jbvb.io.InputStrebm#mbrk(int) mbrk} bnd
     * {@link jbvb.io.InputStrebm#reset() reset}, this method will
     * consume the entire input strebm. Otherwise, ebch cbll to this
     * method consumes one CRL bnd the rebd position of the input strebm
     * is positioned to the next bvbilbble byte bfter the inherent
     * end-of-CRL mbrker. If the dbtb in the
     * input strebm does not contbin bn inherent end-of-CRL mbrker (other
     * thbn EOF) bnd there is trbiling dbtb bfter the CRL is pbrsed, b
     * {@code CRLException} is thrown.
     *
     * @pbrbm inStrebm bn input strebm with the CRL dbtb.
     *
     * @return b CRL object initiblized with the dbtb
     * from the input strebm.
     *
     * @exception CRLException on pbrsing errors.
     */
    public finbl CRL generbteCRL(InputStrebm inStrebm)
        throws CRLException
    {
        return certFbcSpi.engineGenerbteCRL(inStrebm);
    }

    /**
     * Returns b (possibly empty) collection view of the CRLs rebd
     * from the given input strebm {@code inStrebm}.
     *
     * <p>In order to tbke bdvbntbge of the speciblized CRL formbt
     * supported by this certificbte fbctory, ebch element in
     * the returned collection view cbn be typecbst to the corresponding
     * CRL clbss. For exbmple, if this certificbte
     * fbctory implements X.509 CRLs, the elements in the returned
     * collection cbn be typecbst to the {@code X509CRL} clbss.
     *
     * <p>In the cbse of b certificbte fbctory for X.509 CRLs,
     * {@code inStrebm} mby contbin b sequence of DER-encoded CRLs.
     * In bddition, {@code inStrebm} mby contbin b PKCS#7 CRL
     * set. This is b PKCS#7 <i>SignedDbtb</i> object, with the only
     * significbnt field being <i>crls</i>. In pbrticulbr, the
     * signbture bnd the contents bre ignored. This formbt bllows multiple
     * CRLs to be downlobded bt once. If no CRLs bre present,
     * bn empty collection is returned.
     *
     * <p>Note thbt if the given input strebm does not support
     * {@link jbvb.io.InputStrebm#mbrk(int) mbrk} bnd
     * {@link jbvb.io.InputStrebm#reset() reset}, this method will
     * consume the entire input strebm.
     *
     * @pbrbm inStrebm the input strebm with the CRLs.
     *
     * @return b (possibly empty) collection view of
     * jbvb.security.cert.CRL objects initiblized with the dbtb from the input
     * strebm.
     *
     * @exception CRLException on pbrsing errors.
     */
    public finbl Collection<? extends CRL> generbteCRLs(InputStrebm inStrebm)
            throws CRLException {
        return certFbcSpi.engineGenerbteCRLs(inStrebm);
    }
}
