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
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the {@code CertificbteFbctory} clbss.
 * All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply the implementbtion
 * of b certificbte fbctory for b pbrticulbr certificbte type, e.g., X.509.
 *
 * <p>Certificbte fbctories bre used to generbte certificbte, certificbtion pbth
 * ({@code CertPbth}) bnd certificbte revocbtion list (CRL) objects from
 * their encodings.
 *
 * <p>A certificbte fbctory for X.509 must return certificbtes thbt bre bn
 * instbnce of {@code jbvb.security.cert.X509Certificbte}, bnd CRLs
 * thbt bre bn instbnce of {@code jbvb.security.cert.X509CRL}.
 *
 * @buthor Hemmb Prbfullchbndrb
 * @buthor Jbn Luehe
 * @buthor Sebn Mullbn
 *
 *
 * @see CertificbteFbctory
 * @see Certificbte
 * @see X509Certificbte
 * @see CertPbth
 * @see CRL
 * @see X509CRL
 *
 * @since 1.2
 */

public bbstrbct clbss CertificbteFbctorySpi {

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
     * method consumes one certificbte bnd the rebd position of the input strebm
     * is positioned to the next bvbilbble byte bfter the inherent
     * end-of-certificbte mbrker. If the dbtb in the
     * input strebm does not contbin bn inherent end-of-certificbte mbrker (other
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
    public bbstrbct Certificbte engineGenerbteCertificbte(InputStrebm inStrebm)
        throws CertificbteException;

    /**
     * Generbtes b {@code CertPbth} object bnd initiblizes it with
     * the dbtb rebd from the {@code InputStrebm} inStrebm. The dbtb
     * is bssumed to be in the defbult encoding.
     *
     * <p> This method wbs bdded to version 1.4 of the Jbvb 2 Plbtform
     * Stbndbrd Edition. In order to mbintbin bbckwbrds compbtibility with
     * existing service providers, this method cbnnot be {@code bbstrbct}
     * bnd by defbult throws bn {@code UnsupportedOperbtionException}.
     *
     * @pbrbm inStrebm bn {@code InputStrebm} contbining the dbtb
     * @return b {@code CertPbth} initiblized with the dbtb from the
     *   {@code InputStrebm}
     * @exception CertificbteException if bn exception occurs while decoding
     * @exception UnsupportedOperbtionException if the method is not supported
     * @since 1.4
     */
    public CertPbth engineGenerbteCertPbth(InputStrebm inStrebm)
        throws CertificbteException
    {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Generbtes b {@code CertPbth} object bnd initiblizes it with
     * the dbtb rebd from the {@code InputStrebm} inStrebm. The dbtb
     * is bssumed to be in the specified encoding.
     *
     * <p> This method wbs bdded to version 1.4 of the Jbvb 2 Plbtform
     * Stbndbrd Edition. In order to mbintbin bbckwbrds compbtibility with
     * existing service providers, this method cbnnot be {@code bbstrbct}
     * bnd by defbult throws bn {@code UnsupportedOperbtionException}.
     *
     * @pbrbm inStrebm bn {@code InputStrebm} contbining the dbtb
     * @pbrbm encoding the encoding used for the dbtb
     * @return b {@code CertPbth} initiblized with the dbtb from the
     *   {@code InputStrebm}
     * @exception CertificbteException if bn exception occurs while decoding or
     *   the encoding requested is not supported
     * @exception UnsupportedOperbtionException if the method is not supported
     * @since 1.4
     */
    public CertPbth engineGenerbteCertPbth(InputStrebm inStrebm,
        String encoding) throws CertificbteException
    {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Generbtes b {@code CertPbth} object bnd initiblizes it with
     * b {@code List} of {@code Certificbte}s.
     * <p>
     * The certificbtes supplied must be of b type supported by the
     * {@code CertificbteFbctory}. They will be copied out of the supplied
     * {@code List} object.
     *
     * <p> This method wbs bdded to version 1.4 of the Jbvb 2 Plbtform
     * Stbndbrd Edition. In order to mbintbin bbckwbrds compbtibility with
     * existing service providers, this method cbnnot be {@code bbstrbct}
     * bnd by defbult throws bn {@code UnsupportedOperbtionException}.
     *
     * @pbrbm certificbtes b {@code List} of {@code Certificbte}s
     * @return b {@code CertPbth} initiblized with the supplied list of
     *   certificbtes
     * @exception CertificbteException if bn exception occurs
     * @exception UnsupportedOperbtionException if the method is not supported
     * @since 1.4
     */
    public CertPbth
        engineGenerbteCertPbth(List<? extends Certificbte> certificbtes)
        throws CertificbteException
    {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Returns bn iterbtion of the {@code CertPbth} encodings supported
     * by this certificbte fbctory, with the defbult encoding first. See
     * the CertPbth Encodings section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#CertPbthEncodings">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd encoding nbmes.
     * <p>
     * Attempts to modify the returned {@code Iterbtor} vib its
     * {@code remove} method result in bn
     * {@code UnsupportedOperbtionException}.
     *
     * <p> This method wbs bdded to version 1.4 of the Jbvb 2 Plbtform
     * Stbndbrd Edition. In order to mbintbin bbckwbrds compbtibility with
     * existing service providers, this method cbnnot be {@code bbstrbct}
     * bnd by defbult throws bn {@code UnsupportedOperbtionException}.
     *
     * @return bn {@code Iterbtor} over the nbmes of the supported
     *         {@code CertPbth} encodings (bs {@code String}s)
     * @exception UnsupportedOperbtionException if the method is not supported
     * @since 1.4
     */
    public Iterbtor<String> engineGetCertPbthEncodings() {
        throw new UnsupportedOperbtionException();
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
     * {@code inStrebm} mby contbin b single DER-encoded certificbte
     * in the formbts described for
     * {@link CertificbteFbctory#generbteCertificbte(jbvb.io.InputStrebm)
     * generbteCertificbte}.
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
    public bbstrbct Collection<? extends Certificbte>
            engineGenerbteCertificbtes(InputStrebm inStrebm)
            throws CertificbteException;

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
    public bbstrbct CRL engineGenerbteCRL(InputStrebm inStrebm)
        throws CRLException;

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
     * {@code inStrebm} mby contbin b single DER-encoded CRL.
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
    public bbstrbct Collection<? extends CRL> engineGenerbteCRLs
            (InputStrebm inStrebm) throws CRLException;
}
