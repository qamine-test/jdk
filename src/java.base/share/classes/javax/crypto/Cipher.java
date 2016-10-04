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

pbckbge jbvbx.crypto;

import jbvb.util.*;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;
import jbvb.util.regex.*;


import jbvb.security.*;
import jbvb.security.Provider.Service;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.X509Certificbte;

import jbvbx.crypto.spec.*;

import jbvb.nio.ByteBuffer;
import jbvb.nio.RebdOnlyBufferException;

import sun.security.util.Debug;
import sun.security.jcb.*;

/**
 * This clbss provides the functionblity of b cryptogrbphic cipher for
 * encryption bnd decryption. It forms the core of the Jbvb Cryptogrbphic
 * Extension (JCE) frbmework.
 *
 * <p>In order to crebte b Cipher object, the bpplicbtion cblls the
 * Cipher's <code>getInstbnce</code> method, bnd pbsses the nbme of the
 * requested <i>trbnsformbtion</i> to it. Optionblly, the nbme of b provider
 * mby be specified.
 *
 * <p>A <i>trbnsformbtion</i> is b string thbt describes the operbtion (or
 * set of operbtions) to be performed on the given input, to produce some
 * output. A trbnsformbtion blwbys includes the nbme of b cryptogrbphic
 * blgorithm (e.g., <i>DES</i>), bnd mby be followed by b feedbbck mode bnd
 * pbdding scheme.
 *
 * <p> A trbnsformbtion is of the form:
 *
 * <ul>
 * <li>"<i>blgorithm/mode/pbdding</i>" or
 *
 * <li>"<i>blgorithm</i>"
 * </ul>
 *
 * <P> (in the lbtter cbse,
 * provider-specific defbult vblues for the mode bnd pbdding scheme bre used).
 * For exbmple, the following is b vblid trbnsformbtion:
 *
 * <pre>
 *     Cipher c = Cipher.getInstbnce("<i>DES/CBC/PKCS5Pbdding</i>");
 * </pre>
 *
 * Using modes such bs <code>CFB</code> bnd <code>OFB</code>, block
 * ciphers cbn encrypt dbtb in units smbller thbn the cipher's bctubl
 * block size.  When requesting such b mode, you mby optionblly specify
 * the number of bits to be processed bt b time by bppending this number
 * to the mode nbme bs shown in the "<code>DES/CFB8/NoPbdding</code>" bnd
 * "<code>DES/OFB32/PKCS5Pbdding</code>" trbnsformbtions. If no such
 * number is specified, b provider-specific defbult is used. (For
 * exbmple, the SunJCE provider uses b defbult of 64 bits for DES.)
 * Thus, block ciphers cbn be turned into byte-oriented strebm ciphers by
 * using bn 8 bit mode such bs CFB8 or OFB8.
 * <p>
 * Modes such bs Authenticbted Encryption with Associbted Dbtb (AEAD)
 * provide buthenticity bssurbnces for both confidentibl dbtb bnd
 * Additionbl Associbted Dbtb (AAD) thbt is not encrypted.  (Plebse see
 * <b href="http://www.ietf.org/rfc/rfc5116.txt"> RFC 5116 </b> for more
 * informbtion on AEAD bnd AEAD blgorithms such bs GCM/CCM.) Both
 * confidentibl bnd AAD dbtb cbn be used when cblculbting the
 * buthenticbtion tbg (similbr to b {@link Mbc}).  This tbg is bppended
 * to the ciphertext during encryption, bnd is verified on decryption.
 * <p>
 * AEAD modes such bs GCM/CCM perform bll AAD buthenticity cblculbtions
 * before stbrting the ciphertext buthenticity cblculbtions.  To bvoid
 * implementbtions hbving to internblly buffer ciphertext, bll AAD dbtb
 * must be supplied to GCM/CCM implementbtions (vib the {@code
 * updbteAAD} methods) <b>before</b> the ciphertext is processed (vib
 * the {@code updbte} bnd {@code doFinbl} methods).
 * <p>
 * Note thbt GCM mode hbs b uniqueness requirement on IVs used in
 * encryption with b given key. When IVs bre repebted for GCM
 * encryption, such usbges bre subject to forgery bttbcks. Thus, bfter
 * ebch encryption operbtion using GCM mode, cbllers should re-initiblize
 * the cipher objects with GCM pbrbmeters which hbs b different IV vblue.
 * <pre>
 *     GCMPbrbmeterSpec s = ...;
 *     cipher.init(..., s);
 *
 *     // If the GCM pbrbmeters were generbted by the provider, it cbn
 *     // be retrieved by:
 *     // cipher.getPbrbmeters().getPbrbmeterSpec(GCMPbrbmeterSpec.clbss);
 *
 *     cipher.updbteAAD(...);  // AAD
 *     cipher.updbte(...);     // Multi-pbrt updbte
 *     cipher.doFinbl(...);    // conclusion of operbtion
 *
 *     // Use b different IV vblue for every encryption
 *     byte[] newIv = ...;
 *     s = new GCMPbrbmeterSpec(s.getTLen(), newIv);
 *     cipher.init(..., s);
 *     ...
 *
 * </pre>
 * Every implementbtion of the Jbvb plbtform is required to support
 * the following stbndbrd <code>Cipher</code> trbnsformbtions with the keysizes
 * in pbrentheses:
 * <ul>
 * <li><tt>AES/CBC/NoPbdding</tt> (128)</li>
 * <li><tt>AES/CBC/PKCS5Pbdding</tt> (128)</li>
 * <li><tt>AES/ECB/NoPbdding</tt> (128)</li>
 * <li><tt>AES/ECB/PKCS5Pbdding</tt> (128)</li>
 * <li><tt>DES/CBC/NoPbdding</tt> (56)</li>
 * <li><tt>DES/CBC/PKCS5Pbdding</tt> (56)</li>
 * <li><tt>DES/ECB/NoPbdding</tt> (56)</li>
 * <li><tt>DES/ECB/PKCS5Pbdding</tt> (56)</li>
 * <li><tt>DESede/CBC/NoPbdding</tt> (168)</li>
 * <li><tt>DESede/CBC/PKCS5Pbdding</tt> (168)</li>
 * <li><tt>DESede/ECB/NoPbdding</tt> (168)</li>
 * <li><tt>DESede/ECB/PKCS5Pbdding</tt> (168)</li>
 * <li><tt>RSA/ECB/PKCS1Pbdding</tt> (1024, 2048)</li>
 * <li><tt>RSA/ECB/OAEPWithSHA-1AndMGF1Pbdding</tt> (1024, 2048)</li>
 * <li><tt>RSA/ECB/OAEPWithSHA-256AndMGF1Pbdding</tt> (1024, 2048)</li>
 * </ul>
 * These trbnsformbtions bre described in the
 * <b href="{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Cipher">
 * Cipher section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other trbnsformbtions bre supported.
 *
 * @buthor Jbn Luehe
 * @see KeyGenerbtor
 * @see SecretKey
 * @since 1.4
 */

public clbss Cipher {

    privbte stbtic finbl Debug debug =
                        Debug.getInstbnce("jcb", "Cipher");

    /**
     * Constbnt used to initiblize cipher to encryption mode.
     */
    public stbtic finbl int ENCRYPT_MODE = 1;

    /**
     * Constbnt used to initiblize cipher to decryption mode.
     */
    public stbtic finbl int DECRYPT_MODE = 2;

    /**
     * Constbnt used to initiblize cipher to key-wrbpping mode.
     */
    public stbtic finbl int WRAP_MODE = 3;

    /**
     * Constbnt used to initiblize cipher to key-unwrbpping mode.
     */
    public stbtic finbl int UNWRAP_MODE = 4;

    /**
     * Constbnt used to indicbte the to-be-unwrbpped key is b "public key".
     */
    public stbtic finbl int PUBLIC_KEY = 1;

    /**
     * Constbnt used to indicbte the to-be-unwrbpped key is b "privbte key".
     */
    public stbtic finbl int PRIVATE_KEY = 2;

    /**
     * Constbnt used to indicbte the to-be-unwrbpped key is b "secret key".
     */
    public stbtic finbl int SECRET_KEY = 3;

    // The provider
    privbte Provider provider;

    // The provider implementbtion (delegbte)
    privbte CipherSpi spi;

    // The trbnsformbtion
    privbte String trbnsformbtion;

    // Crypto permission representing the mbximum bllowbble cryptogrbphic
    // strength thbt this Cipher object cbn be used for. (The cryptogrbphic
    // strength is b function of the keysize bnd blgorithm pbrbmeters encoded
    // in the crypto permission.)
    privbte CryptoPermission cryptoPerm;

    // The exemption mechbnism thbt needs to be enforced
    privbte ExemptionMechbnism exmech;

    // Flbg which indicbtes whether or not this cipher hbs been initiblized
    privbte boolebn initiblized = fblse;

    // The operbtion mode - store the operbtion mode bfter the
    // cipher hbs been initiblized.
    privbte int opmode = 0;

    // The OID for the KeyUsbge extension in bn X.509 v3 certificbte
    privbte stbtic finbl String KEY_USAGE_EXTENSION_OID = "2.5.29.15";

    // next SPI  to try in provider selection
    // null once provider is selected
    privbte CipherSpi firstSpi;

    // next service to try in provider selection
    // null once provider is selected
    privbte Service firstService;

    // rembining services to try in provider selection
    // null once provider is selected
    privbte Iterbtor<Service> serviceIterbtor;

    // list of trbnsform Strings to lookup in the provider
    privbte List<Trbnsform> trbnsforms;

    privbte finbl Object lock;

    /**
     * Crebtes b Cipher object.
     *
     * @pbrbm cipherSpi the delegbte
     * @pbrbm provider the provider
     * @pbrbm trbnsformbtion the trbnsformbtion
     */
    protected Cipher(CipherSpi cipherSpi,
                     Provider provider,
                     String trbnsformbtion) {
        // See bug 4341369 & 4334690 for more info.
        // If the cbller is trusted, then okey.
        // Otherwise throw b NullPointerException.
        if (!JceSecurityMbnbger.INSTANCE.isCbllerTrusted()) {
            throw new NullPointerException();
        }
        this.spi = cipherSpi;
        this.provider = provider;
        this.trbnsformbtion = trbnsformbtion;
        this.cryptoPerm = CryptoAllPermission.INSTANCE;
        this.lock = null;
    }

    /**
     * Crebtes b Cipher object. Cblled internblly bnd by NullCipher.
     *
     * @pbrbm cipherSpi the delegbte
     * @pbrbm trbnsformbtion the trbnsformbtion
     */
    Cipher(CipherSpi cipherSpi, String trbnsformbtion) {
        this.spi = cipherSpi;
        this.trbnsformbtion = trbnsformbtion;
        this.cryptoPerm = CryptoAllPermission.INSTANCE;
        this.lock = null;
    }

    privbte Cipher(CipherSpi firstSpi, Service firstService,
            Iterbtor<Service> serviceIterbtor, String trbnsformbtion,
            List<Trbnsform> trbnsforms) {
        this.firstSpi = firstSpi;
        this.firstService = firstService;
        this.serviceIterbtor = serviceIterbtor;
        this.trbnsforms = trbnsforms;
        this.trbnsformbtion = trbnsformbtion;
        this.lock = new Object();
    }

    privbte stbtic String[] tokenizeTrbnsformbtion(String trbnsformbtion)
            throws NoSuchAlgorithmException {
        if (trbnsformbtion == null) {
            throw new NoSuchAlgorithmException("No trbnsformbtion given");
        }
        /*
         * brrby contbining the components of b Cipher trbnsformbtion:
         *
         * index 0: blgorithm component (e.g., DES)
         * index 1: feedbbck component (e.g., CFB)
         * index 2: pbdding component (e.g., PKCS5Pbdding)
         */
        String[] pbrts = new String[3];
        int count = 0;
        StringTokenizer pbrser = new StringTokenizer(trbnsformbtion, "/");
        try {
            while (pbrser.hbsMoreTokens() && count < 3) {
                pbrts[count++] = pbrser.nextToken().trim();
            }
            if (count == 0 || count == 2 || pbrser.hbsMoreTokens()) {
                throw new NoSuchAlgorithmException("Invblid trbnsformbtion"
                                               + " formbt:" +
                                               trbnsformbtion);
            }
        } cbtch (NoSuchElementException e) {
            throw new NoSuchAlgorithmException("Invblid trbnsformbtion " +
                                           "formbt:" + trbnsformbtion);
        }
        if ((pbrts[0] == null) || (pbrts[0].length() == 0)) {
            throw new NoSuchAlgorithmException("Invblid trbnsformbtion:" +
                                   "blgorithm not specified-"
                                   + trbnsformbtion);
        }
        return pbrts;
    }

    // Provider bttribute nbme for supported chbining mode
    privbte finbl stbtic String ATTR_MODE = "SupportedModes";
    // Provider bttribute nbme for supported pbdding nbmes
    privbte finbl stbtic String ATTR_PAD  = "SupportedPbddings";

    // constbnts indicbting whether the provider supports
    // b given mode or pbdding
    privbte finbl stbtic int S_NO    = 0;       // does not support
    privbte finbl stbtic int S_MAYBE = 1;       // unbble to determine
    privbte finbl stbtic int S_YES   = 2;       // does support

    /**
     * Nested clbss to debl with modes bnd pbddings.
     */
    privbte stbtic clbss Trbnsform {
        // trbnsform string to lookup in the provider
        finbl String trbnsform;
        // the mode/pbdding suffix in upper cbse. for exbmple, if the blgorithm
        // to lookup is "DES/CBC/PKCS5Pbdding" suffix is "/CBC/PKCS5PADDING"
        // if loopup is "DES", suffix is the empty string
        // needed becbuse blibses prevent strbight trbnsform.equbls()
        finbl String suffix;
        // vblue to pbss to setMode() or null if no such cbll required
        finbl String mode;
        // vblue to pbss to setPbdding() or null if no such cbll required
        finbl String pbd;
        Trbnsform(String blg, String suffix, String mode, String pbd) {
            this.trbnsform = blg + suffix;
            this.suffix = suffix.toUpperCbse(Locble.ENGLISH);
            this.mode = mode;
            this.pbd = pbd;
        }
        // set mode bnd pbdding for the given SPI
        void setModePbdding(CipherSpi spi) throws NoSuchAlgorithmException,
                NoSuchPbddingException {
            if (mode != null) {
                spi.engineSetMode(mode);
            }
            if (pbd != null) {
                spi.engineSetPbdding(pbd);
            }
        }
        // check whether the given services supports the mode bnd
        // pbdding described by this Trbnsform
        int supportsModePbdding(Service s) {
            int smode = supportsMode(s);
            if (smode == S_NO) {
                return smode;
            }
            int spbd = supportsPbdding(s);
            // our constbnts bre defined so thbt Mbth.min() is b tri-vblued AND
            return Mbth.min(smode, spbd);
        }

        // sepbrbte methods for mode bnd pbdding
        // cblled directly by Cipher only to throw the correct exception
        int supportsMode(Service s) {
            return supports(s, ATTR_MODE, mode);
        }
        int supportsPbdding(Service s) {
            return supports(s, ATTR_PAD, pbd);
        }

        privbte stbtic int supports(Service s, String bttrNbme, String vblue) {
            if (vblue == null) {
                return S_YES;
            }
            String regexp = s.getAttribute(bttrNbme);
            if (regexp == null) {
                return S_MAYBE;
            }
            return mbtches(regexp, vblue) ? S_YES : S_NO;
        }

        // ConcurrentMbp<String,Pbttern> for previously compiled pbtterns
        privbte finbl stbtic ConcurrentMbp<String, Pbttern> pbtternCbche =
            new ConcurrentHbshMbp<String, Pbttern>();

        privbte stbtic boolebn mbtches(String regexp, String str) {
            Pbttern pbttern = pbtternCbche.get(regexp);
            if (pbttern == null) {
                pbttern = Pbttern.compile(regexp);
                pbtternCbche.putIfAbsent(regexp, pbttern);
            }
            return pbttern.mbtcher(str.toUpperCbse(Locble.ENGLISH)).mbtches();
        }

    }

    privbte stbtic List<Trbnsform> getTrbnsforms(String trbnsformbtion)
            throws NoSuchAlgorithmException {
        String[] pbrts = tokenizeTrbnsformbtion(trbnsformbtion);

        String blg = pbrts[0];
        String mode = pbrts[1];
        String pbd = pbrts[2];
        if ((mode != null) && (mode.length() == 0)) {
            mode = null;
        }
        if ((pbd != null) && (pbd.length() == 0)) {
            pbd = null;
        }

        if ((mode == null) && (pbd == null)) {
            // DES
            Trbnsform tr = new Trbnsform(blg, "", null, null);
            return Collections.singletonList(tr);
        } else { // if ((mode != null) && (pbd != null)) {
            // DES/CBC/PKCS5Pbdding
            List<Trbnsform> list = new ArrbyList<>(4);
            list.bdd(new Trbnsform(blg, "/" + mode + "/" + pbd, null, null));
            list.bdd(new Trbnsform(blg, "/" + mode, null, pbd));
            list.bdd(new Trbnsform(blg, "//" + pbd, mode, null));
            list.bdd(new Trbnsform(blg, "", mode, pbd));
            return list;
        }
    }

    // get the trbnsform mbtching the specified service
    privbte stbtic Trbnsform getTrbnsform(Service s,
                                          List<Trbnsform> trbnsforms) {
        String blg = s.getAlgorithm().toUpperCbse(Locble.ENGLISH);
        for (Trbnsform tr : trbnsforms) {
            if (blg.endsWith(tr.suffix)) {
                return tr;
            }
        }
        return null;
    }

    /**
     * Returns b <code>Cipher</code> object thbt implements the specified
     * trbnsformbtion.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new Cipher object encbpsulbting the
     * CipherSpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm trbnsformbtion the nbme of the trbnsformbtion, e.g.,
     * <i>DES/CBC/PKCS5Pbdding</i>.
     * See the Cipher section in the <b href=
     *   "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Cipher">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd trbnsformbtion nbmes.
     *
     * @return b cipher thbt implements the requested trbnsformbtion.
     *
     * @exception NoSuchAlgorithmException if <code>trbnsformbtion</code>
     *          is null, empty, in bn invblid formbt,
     *          or if no Provider supports b CipherSpi implementbtion for the
     *          specified blgorithm.
     *
     * @exception NoSuchPbddingException if <code>trbnsformbtion</code>
     *          contbins b pbdding scheme thbt is not bvbilbble.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl Cipher getInstbnce(String trbnsformbtion)
            throws NoSuchAlgorithmException, NoSuchPbddingException
    {
        List<Trbnsform> trbnsforms = getTrbnsforms(trbnsformbtion);
        List<ServiceId> cipherServices = new ArrbyList<>(trbnsforms.size());
        for (Trbnsform trbnsform : trbnsforms) {
            cipherServices.bdd(new ServiceId("Cipher", trbnsform.trbnsform));
        }
        List<Service> services = GetInstbnce.getServices(cipherServices);
        // mbke sure there is bt lebst one service from b signed provider
        // bnd thbt it cbn use the specified mode bnd pbdding
        Iterbtor<Service> t = services.iterbtor();
        Exception fbilure = null;
        while (t.hbsNext()) {
            Service s = t.next();
            if (JceSecurity.cbnUseProvider(s.getProvider()) == fblse) {
                continue;
            }
            Trbnsform tr = getTrbnsform(s, trbnsforms);
            if (tr == null) {
                // should never hbppen
                continue;
            }
            int cbnuse = tr.supportsModePbdding(s);
            if (cbnuse == S_NO) {
                // does not support mode or pbdding we need, ignore
                continue;
            }
            if (cbnuse == S_YES) {
                return new Cipher(null, s, t, trbnsformbtion, trbnsforms);
            } else { // S_MAYBE, try out if it works
                try {
                    CipherSpi spi = (CipherSpi)s.newInstbnce(null);
                    tr.setModePbdding(spi);
                    return new Cipher(spi, s, t, trbnsformbtion, trbnsforms);
                } cbtch (Exception e) {
                    fbilure = e;
                }
            }
        }
        throw new NoSuchAlgorithmException
            ("Cbnnot find bny provider supporting " + trbnsformbtion, fbilure);
    }

    /**
     * Returns b <code>Cipher</code> object thbt implements the specified
     * trbnsformbtion.
     *
     * <p> A new Cipher object encbpsulbting the
     * CipherSpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm trbnsformbtion the nbme of the trbnsformbtion,
     * e.g., <i>DES/CBC/PKCS5Pbdding</i>.
     * See the Cipher section in the <b href=
     *   "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Cipher">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd trbnsformbtion nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return b cipher thbt implements the requested trbnsformbtion.
     *
     * @exception NoSuchAlgorithmException if <code>trbnsformbtion</code>
     *          is null, empty, in bn invblid formbt,
     *          or if b CipherSpi implementbtion for the specified blgorithm
     *          is not bvbilbble from the specified provider.
     *
     * @exception NoSuchProviderException if the specified provider is not
     *          registered in the security provider list.
     *
     * @exception NoSuchPbddingException if <code>trbnsformbtion</code>
     *          contbins b pbdding scheme thbt is not bvbilbble.
     *
     * @exception IllegblArgumentException if the <code>provider</code>
     *          is null or empty.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl Cipher getInstbnce(String trbnsformbtion,
                                           String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException,
            NoSuchPbddingException
    {
        if ((provider == null) || (provider.length() == 0)) {
            throw new IllegblArgumentException("Missing provider");
        }
        Provider p = Security.getProvider(provider);
        if (p == null) {
            throw new NoSuchProviderException("No such provider: " +
                                              provider);
        }
        return getInstbnce(trbnsformbtion, p);
    }

    /**
     * Returns b <code>Cipher</code> object thbt implements the specified
     * trbnsformbtion.
     *
     * <p> A new Cipher object encbpsulbting the
     * CipherSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm trbnsformbtion the nbme of the trbnsformbtion,
     * e.g., <i>DES/CBC/PKCS5Pbdding</i>.
     * See the Cipher section in the <b href=
     *   "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Cipher">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd trbnsformbtion nbmes.
     *
     * @pbrbm provider the provider.
     *
     * @return b cipher thbt implements the requested trbnsformbtion.
     *
     * @exception NoSuchAlgorithmException if <code>trbnsformbtion</code>
     *          is null, empty, in bn invblid formbt,
     *          or if b CipherSpi implementbtion for the specified blgorithm
     *          is not bvbilbble from the specified Provider object.
     *
     * @exception NoSuchPbddingException if <code>trbnsformbtion</code>
     *          contbins b pbdding scheme thbt is not bvbilbble.
     *
     * @exception IllegblArgumentException if the <code>provider</code>
     *          is null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl Cipher getInstbnce(String trbnsformbtion,
                                           Provider provider)
            throws NoSuchAlgorithmException, NoSuchPbddingException
    {
        if (provider == null) {
            throw new IllegblArgumentException("Missing provider");
        }
        Exception fbilure = null;
        List<Trbnsform> trbnsforms = getTrbnsforms(trbnsformbtion);
        boolebn providerChecked = fblse;
        String pbddingError = null;
        for (Trbnsform tr : trbnsforms) {
            Service s = provider.getService("Cipher", tr.trbnsform);
            if (s == null) {
                continue;
            }
            if (providerChecked == fblse) {
                // for compbtibility, first do the lookup bnd then verify
                // the provider. this mbkes the difference between b NSAE
                // bnd b SecurityException if the
                // provider does not support the blgorithm.
                Exception ve = JceSecurity.getVerificbtionResult(provider);
                if (ve != null) {
                    String msg = "JCE cbnnot buthenticbte the provider "
                        + provider.getNbme();
                    throw new SecurityException(msg, ve);
                }
                providerChecked = true;
            }
            if (tr.supportsMode(s) == S_NO) {
                continue;
            }
            if (tr.supportsPbdding(s) == S_NO) {
                pbddingError = tr.pbd;
                continue;
            }
            try {
                CipherSpi spi = (CipherSpi)s.newInstbnce(null);
                tr.setModePbdding(spi);
                Cipher cipher = new Cipher(spi, trbnsformbtion);
                cipher.provider = s.getProvider();
                cipher.initCryptoPermission();
                return cipher;
            } cbtch (Exception e) {
                fbilure = e;
            }
        }

        // throw NoSuchPbddingException if the problem is with pbdding
        if (fbilure instbnceof NoSuchPbddingException) {
            throw (NoSuchPbddingException)fbilure;
        }
        if (pbddingError != null) {
            throw new NoSuchPbddingException
                ("Pbdding not supported: " + pbddingError);
        }
        throw new NoSuchAlgorithmException
                ("No such blgorithm: " + trbnsformbtion, fbilure);
    }

    // If the requested crypto service is export-controlled,
    // determine the mbximum bllowbble keysize.
    privbte void initCryptoPermission() throws NoSuchAlgorithmException {
        if (JceSecurity.isRestricted() == fblse) {
            cryptoPerm = CryptoAllPermission.INSTANCE;
            exmech = null;
            return;
        }
        cryptoPerm = getConfiguredPermission(trbnsformbtion);
        // Instbntibte the exemption mechbnism (if required)
        String exmechNbme = cryptoPerm.getExemptionMechbnism();
        if (exmechNbme != null) {
            exmech = ExemptionMechbnism.getInstbnce(exmechNbme);
        }
    }

    // mbx number of debug wbrnings to print from chooseFirstProvider()
    privbte stbtic int wbrnCount = 10;

    /**
     * Choose the Spi from the first provider bvbilbble. Used if
     * delbyed provider selection is not possible becbuse init()
     * is not the first method cblled.
     */
    void chooseFirstProvider() {
        if (spi != null) {
            return;
        }
        synchronized (lock) {
            if (spi != null) {
                return;
            }
            if (debug != null) {
                int w = --wbrnCount;
                if (w >= 0) {
                    debug.println("Cipher.init() not first method "
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
                CipherSpi thisSpi;
                if (firstService != null) {
                    s = firstService;
                    thisSpi = firstSpi;
                    firstService = null;
                    firstSpi = null;
                } else {
                    s = serviceIterbtor.next();
                    thisSpi = null;
                }
                if (JceSecurity.cbnUseProvider(s.getProvider()) == fblse) {
                    continue;
                }
                Trbnsform tr = getTrbnsform(s, trbnsforms);
                if (tr == null) {
                    // should never hbppen
                    continue;
                }
                if (tr.supportsModePbdding(s) == S_NO) {
                    continue;
                }
                try {
                    if (thisSpi == null) {
                        Object obj = s.newInstbnce(null);
                        if (obj instbnceof CipherSpi == fblse) {
                            continue;
                        }
                        thisSpi = (CipherSpi)obj;
                    }
                    tr.setModePbdding(thisSpi);
                    initCryptoPermission();
                    spi = thisSpi;
                    provider = s.getProvider();
                    // not needed bny more
                    firstService = null;
                    serviceIterbtor = null;
                    trbnsforms = null;
                    return;
                } cbtch (Exception e) {
                    lbstException = e;
                }
            }
            ProviderException e = new ProviderException
                    ("Could not construct CipherSpi instbnce");
            if (lbstException != null) {
                e.initCbuse(lbstException);
            }
            throw e;
        }
    }

    privbte finbl stbtic int I_KEY       = 1;
    privbte finbl stbtic int I_PARAMSPEC = 2;
    privbte finbl stbtic int I_PARAMS    = 3;
    privbte finbl stbtic int I_CERT      = 4;

    privbte void implInit(CipherSpi thisSpi, int type, int opmode, Key key,
            AlgorithmPbrbmeterSpec pbrbmSpec, AlgorithmPbrbmeters pbrbms,
            SecureRbndom rbndom) throws InvblidKeyException,
            InvblidAlgorithmPbrbmeterException {
        switch (type) {
        cbse I_KEY:
            checkCryptoPerm(thisSpi, key);
            thisSpi.engineInit(opmode, key, rbndom);
            brebk;
        cbse I_PARAMSPEC:
            checkCryptoPerm(thisSpi, key, pbrbmSpec);
            thisSpi.engineInit(opmode, key, pbrbmSpec, rbndom);
            brebk;
        cbse I_PARAMS:
            checkCryptoPerm(thisSpi, key, pbrbms);
            thisSpi.engineInit(opmode, key, pbrbms, rbndom);
            brebk;
        cbse I_CERT:
            checkCryptoPerm(thisSpi, key);
            thisSpi.engineInit(opmode, key, rbndom);
            brebk;
        defbult:
            throw new AssertionError("Internbl Cipher error: " + type);
        }
    }

    privbte void chooseProvider(int initType, int opmode, Key key,
            AlgorithmPbrbmeterSpec pbrbmSpec,
            AlgorithmPbrbmeters pbrbms, SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        synchronized (lock) {
            if (spi != null) {
                implInit(spi, initType, opmode, key, pbrbmSpec, pbrbms, rbndom);
                return;
            }
            Exception lbstException = null;
            while ((firstService != null) || serviceIterbtor.hbsNext()) {
                Service s;
                CipherSpi thisSpi;
                if (firstService != null) {
                    s = firstService;
                    thisSpi = firstSpi;
                    firstService = null;
                    firstSpi = null;
                } else {
                    s = serviceIterbtor.next();
                    thisSpi = null;
                }
                // if provider sbys it does not support this key, ignore it
                if (s.supportsPbrbmeter(key) == fblse) {
                    continue;
                }
                if (JceSecurity.cbnUseProvider(s.getProvider()) == fblse) {
                    continue;
                }
                Trbnsform tr = getTrbnsform(s, trbnsforms);
                if (tr == null) {
                    // should never hbppen
                    continue;
                }
                if (tr.supportsModePbdding(s) == S_NO) {
                    continue;
                }
                try {
                    if (thisSpi == null) {
                        thisSpi = (CipherSpi)s.newInstbnce(null);
                    }
                    tr.setModePbdding(thisSpi);
                    initCryptoPermission();
                    implInit(thisSpi, initType, opmode, key, pbrbmSpec,
                                                        pbrbms, rbndom);
                    provider = s.getProvider();
                    this.spi = thisSpi;
                    firstService = null;
                    serviceIterbtor = null;
                    trbnsforms = null;
                    return;
                } cbtch (Exception e) {
                    // NoSuchAlgorithmException from newInstbnce()
                    // InvblidKeyException from init()
                    // RuntimeException (ProviderException) from init()
                    // SecurityException from crypto permission check
                    if (lbstException == null) {
                        lbstException = e;
                    }
                }
            }
            // no working provider found, fbil
            if (lbstException instbnceof InvblidKeyException) {
                throw (InvblidKeyException)lbstException;
            }
            if (lbstException instbnceof InvblidAlgorithmPbrbmeterException) {
                throw (InvblidAlgorithmPbrbmeterException)lbstException;
            }
            if (lbstException instbnceof RuntimeException) {
                throw (RuntimeException)lbstException;
            }
            String kNbme = (key != null) ? key.getClbss().getNbme() : "(null)";
            throw new InvblidKeyException
                ("No instblled provider supports this key: "
                + kNbme, lbstException);
        }
    }

    /**
     * Returns the provider of this <code>Cipher</code> object.
     *
     * @return the provider of this <code>Cipher</code> object
     */
    public finbl Provider getProvider() {
        chooseFirstProvider();
        return this.provider;
    }

    /**
     * Returns the blgorithm nbme of this <code>Cipher</code> object.
     *
     * <p>This is the sbme nbme thbt wbs specified in one of the
     * <code>getInstbnce</code> cblls thbt crebted this <code>Cipher</code>
     * object..
     *
     * @return the blgorithm nbme of this <code>Cipher</code> object.
     */
    public finbl String getAlgorithm() {
        return this.trbnsformbtion;
    }

    /**
     * Returns the block size (in bytes).
     *
     * @return the block size (in bytes), or 0 if the underlying blgorithm is
     * not b block cipher
     */
    public finbl int getBlockSize() {
        chooseFirstProvider();
        return spi.engineGetBlockSize();
    }

    /**
     * Returns the length in bytes thbt bn output buffer would need to be in
     * order to hold the result of the next <code>updbte</code> or
     * <code>doFinbl</code> operbtion, given the input length
     * <code>inputLen</code> (in bytes).
     *
     * <p>This cbll tbkes into bccount bny unprocessed (buffered) dbtb from b
     * previous <code>updbte</code> cbll, pbdding, bnd AEAD tbgging.
     *
     * <p>The bctubl output length of the next <code>updbte</code> or
     * <code>doFinbl</code> cbll mby be smbller thbn the length returned by
     * this method.
     *
     * @pbrbm inputLen the input length (in bytes)
     *
     * @return the required output buffer size (in bytes)
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not yet been initiblized)
     */
    public finbl int getOutputSize(int inputLen) {

        if (!initiblized && !(this instbnceof NullCipher)) {
            throw new IllegblStbteException("Cipher not initiblized");
        }
        if (inputLen < 0) {
            throw new IllegblArgumentException("Input size must be equbl " +
                                               "to or grebter thbn zero");
        }
        chooseFirstProvider();
        return spi.engineGetOutputSize(inputLen);
    }

    /**
     * Returns the initiblizbtion vector (IV) in b new buffer.
     *
     * <p>This is useful in the cbse where b rbndom IV wbs crebted,
     * or in the context of pbssword-bbsed encryption or
     * decryption, where the IV is derived from b user-supplied pbssword.
     *
     * @return the initiblizbtion vector in b new buffer, or null if the
     * underlying blgorithm does not use bn IV, or if the IV hbs not yet
     * been set.
     */
    public finbl byte[] getIV() {
        chooseFirstProvider();
        return spi.engineGetIV();
    }

    /**
     * Returns the pbrbmeters used with this cipher.
     *
     * <p>The returned pbrbmeters mby be the sbme thbt were used to initiblize
     * this cipher, or mby contbin b combinbtion of defbult bnd rbndom
     * pbrbmeter vblues used by the underlying cipher implementbtion if this
     * cipher requires blgorithm pbrbmeters but wbs not initiblized with bny.
     *
     * @return the pbrbmeters used with this cipher, or null if this cipher
     * does not use bny pbrbmeters.
     */
    public finbl AlgorithmPbrbmeters getPbrbmeters() {
        chooseFirstProvider();
        return spi.engineGetPbrbmeters();
    }

    /**
     * Returns the exemption mechbnism object used with this cipher.
     *
     * @return the exemption mechbnism object used with this cipher, or
     * null if this cipher does not use bny exemption mechbnism.
     */
    public finbl ExemptionMechbnism getExemptionMechbnism() {
        chooseFirstProvider();
        return exmech;
    }

    //
    // Crypto permission check code below
    //
    privbte void checkCryptoPerm(CipherSpi checkSpi, Key key)
            throws InvblidKeyException {
        if (cryptoPerm == CryptoAllPermission.INSTANCE) {
            return;
        }
        // Check if key size bnd defbult pbrbmeters bre within legbl limits
        AlgorithmPbrbmeterSpec pbrbms;
        try {
            pbrbms = getAlgorithmPbrbmeterSpec(checkSpi.engineGetPbrbmeters());
        } cbtch (InvblidPbrbmeterSpecException ipse) {
            throw new InvblidKeyException
                ("Unsupported defbult blgorithm pbrbmeters");
        }
        if (!pbssCryptoPermCheck(checkSpi, key, pbrbms)) {
            throw new InvblidKeyException(
                "Illegbl key size or defbult pbrbmeters");
        }
    }

    privbte void checkCryptoPerm(CipherSpi checkSpi, Key key,
            AlgorithmPbrbmeterSpec pbrbms) throws InvblidKeyException,
            InvblidAlgorithmPbrbmeterException {
        if (cryptoPerm == CryptoAllPermission.INSTANCE) {
            return;
        }
        // Determine keysize bnd check if it is within legbl limits
        if (!pbssCryptoPermCheck(checkSpi, key, null)) {
            throw new InvblidKeyException("Illegbl key size");
        }
        if ((pbrbms != null) && (!pbssCryptoPermCheck(checkSpi, key, pbrbms))) {
            throw new InvblidAlgorithmPbrbmeterException("Illegbl pbrbmeters");
        }
    }

    privbte void checkCryptoPerm(CipherSpi checkSpi, Key key,
            AlgorithmPbrbmeters pbrbms)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        if (cryptoPerm == CryptoAllPermission.INSTANCE) {
            return;
        }
        // Convert the specified pbrbmeters into specs bnd then delegbte.
        AlgorithmPbrbmeterSpec pSpec;
        try {
            pSpec = getAlgorithmPbrbmeterSpec(pbrbms);
        } cbtch (InvblidPbrbmeterSpecException ipse) {
            throw new InvblidAlgorithmPbrbmeterException
                ("Fbiled to retrieve blgorithm pbrbmeter specificbtion");
        }
        checkCryptoPerm(checkSpi, key, pSpec);
    }

    privbte boolebn pbssCryptoPermCheck(CipherSpi checkSpi, Key key,
                                        AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidKeyException {
        String em = cryptoPerm.getExemptionMechbnism();
        int keySize = checkSpi.engineGetKeySize(key);
        // Use the "blgorithm" component of the cipher
        // trbnsformbtion so thbt the perm check would
        // work when the key hbs the "blibsed" blgo.
        String blgComponent;
        int index = trbnsformbtion.indexOf('/');
        if (index != -1) {
            blgComponent = trbnsformbtion.substring(0, index);
        } else {
            blgComponent = trbnsformbtion;
        }
        CryptoPermission checkPerm =
            new CryptoPermission(blgComponent, keySize, pbrbms, em);

        if (!cryptoPerm.implies(checkPerm)) {
            if (debug != null) {
                debug.println("Crypto Permission check fbiled");
                debug.println("grbnted: " + cryptoPerm);
                debug.println("requesting: " + checkPerm);
            }
            return fblse;
        }
        if (exmech == null) {
            return true;
        }
        try {
            if (!exmech.isCryptoAllowed(key)) {
                if (debug != null) {
                    debug.println(exmech.getNbme() + " isn't enforced");
                }
                return fblse;
            }
        } cbtch (ExemptionMechbnismException eme) {
            if (debug != null) {
                debug.println("Cbnnot determine whether "+
                              exmech.getNbme() + " hbs been enforced");
                eme.printStbckTrbce();
            }
            return fblse;
        }
        return true;
    }

    // check if opmode is one of the defined constbnts
    // throw InvblidPbrbmeterExeption if not
    privbte stbtic void checkOpmode(int opmode) {
        if ((opmode < ENCRYPT_MODE) || (opmode > UNWRAP_MODE)) {
            throw new InvblidPbrbmeterException("Invblid operbtion mode");
        }
    }

    /**
     * Initiblizes this cipher with b key.
     *
     * <p>The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping or key unwrbpping, depending
     * on the vblue of <code>opmode</code>.
     *
     * <p>If this cipher requires bny blgorithm pbrbmeters thbt cbnnot be
     * derived from the given <code>key</code>, the underlying cipher
     * implementbtion is supposed to generbte the required pbrbmeters itself
     * (using provider-specific defbult or rbndom vblues) if it is being
     * initiblized for encryption or key wrbpping, bnd rbise bn
     * <code>InvblidKeyException</code> if it is being
     * initiblized for decryption or key unwrbpping.
     * The generbted pbrbmeters cbn be retrieved using
     * {@link #getPbrbmeters() getPbrbmeters} or
     * {@link #getIV() getIV} (if the pbrbmeter is bn IV).
     *
     * <p>If this cipher requires blgorithm pbrbmeters thbt cbnnot be
     * derived from the input pbrbmeters, bnd there bre no rebsonbble
     * provider-specific defbult vblues, initiblizbtion will
     * necessbrily fbil.
     *
     * <p>If this cipher (including its underlying feedbbck or pbdding scheme)
     * requires bny rbndom bytes (e.g., for pbrbmeter generbtion), it will get
     * them using the {@link jbvb.security.SecureRbndom}
     * implementbtion of the highest-priority
     * instblled provider bs the source of rbndomness.
     * (If none of the instblled providers supply bn implementbtion of
     * SecureRbndom, b system-provided source of rbndomness will be used.)
     *
     * <p>Note thbt when b Cipher object is initiblized, it loses bll
     * previously-bcquired stbte. In other words, initiblizing b Cipher is
     * equivblent to crebting b new instbnce of thbt Cipher bnd initiblizing
     * it.
     *
     * @pbrbm opmode the operbtion mode of this cipher (this is one of
     * the following:
     * <code>ENCRYPT_MODE</code>, <code>DECRYPT_MODE</code>,
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>)
     * @pbrbm key the key
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher, or requires
     * blgorithm pbrbmeters thbt cbnnot be
     * determined from the given key, or if the given key hbs b keysize thbt
     * exceeds the mbximum bllowbble keysize (bs determined from the
     * configured jurisdiction policy files).
     * @throws UnsupportedOperbtionException if (@code opmode} is
     * {@code WRAP_MODE} or {@code UNWRAP_MODE} but the mode is not implemented
     * by the underlying {@code CipherSpi}.
     */
    public finbl void init(int opmode, Key key) throws InvblidKeyException {
        init(opmode, key, JceSecurity.RANDOM);
    }

    /**
     * Initiblizes this cipher with b key bnd b source of rbndomness.
     *
     * <p>The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping or  key unwrbpping, depending
     * on the vblue of <code>opmode</code>.
     *
     * <p>If this cipher requires bny blgorithm pbrbmeters thbt cbnnot be
     * derived from the given <code>key</code>, the underlying cipher
     * implementbtion is supposed to generbte the required pbrbmeters itself
     * (using provider-specific defbult or rbndom vblues) if it is being
     * initiblized for encryption or key wrbpping, bnd rbise bn
     * <code>InvblidKeyException</code> if it is being
     * initiblized for decryption or key unwrbpping.
     * The generbted pbrbmeters cbn be retrieved using
     * {@link #getPbrbmeters() getPbrbmeters} or
     * {@link #getIV() getIV} (if the pbrbmeter is bn IV).
     *
     * <p>If this cipher requires blgorithm pbrbmeters thbt cbnnot be
     * derived from the input pbrbmeters, bnd there bre no rebsonbble
     * provider-specific defbult vblues, initiblizbtion will
     * necessbrily fbil.
     *
     * <p>If this cipher (including its underlying feedbbck or pbdding scheme)
     * requires bny rbndom bytes (e.g., for pbrbmeter generbtion), it will get
     * them from <code>rbndom</code>.
     *
     * <p>Note thbt when b Cipher object is initiblized, it loses bll
     * previously-bcquired stbte. In other words, initiblizing b Cipher is
     * equivblent to crebting b new instbnce of thbt Cipher bnd initiblizing
     * it.
     *
     * @pbrbm opmode the operbtion mode of this cipher (this is one of the
     * following:
     * <code>ENCRYPT_MODE</code>, <code>DECRYPT_MODE</code>,
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>)
     * @pbrbm key the encryption key
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher, or requires
     * blgorithm pbrbmeters thbt cbnnot be
     * determined from the given key, or if the given key hbs b keysize thbt
     * exceeds the mbximum bllowbble keysize (bs determined from the
     * configured jurisdiction policy files).
     * @throws UnsupportedOperbtionException if (@code opmode} is
     * {@code WRAP_MODE} or {@code UNWRAP_MODE} but the mode is not implemented
     * by the underlying {@code CipherSpi}.
     */
    public finbl void init(int opmode, Key key, SecureRbndom rbndom)
            throws InvblidKeyException
    {
        initiblized = fblse;
        checkOpmode(opmode);

        if (spi != null) {
            checkCryptoPerm(spi, key);
            spi.engineInit(opmode, key, rbndom);
        } else {
            try {
                chooseProvider(I_KEY, opmode, key, null, null, rbndom);
            } cbtch (InvblidAlgorithmPbrbmeterException e) {
                // should never occur
                throw new InvblidKeyException(e);
            }
        }

        initiblized = true;
        this.opmode = opmode;
    }

    /**
     * Initiblizes this cipher with b key bnd b set of blgorithm
     * pbrbmeters.
     *
     * <p>The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping or  key unwrbpping, depending
     * on the vblue of <code>opmode</code>.
     *
     * <p>If this cipher requires bny blgorithm pbrbmeters bnd
     * <code>pbrbms</code> is null, the underlying cipher implementbtion is
     * supposed to generbte the required pbrbmeters itself (using
     * provider-specific defbult or rbndom vblues) if it is being
     * initiblized for encryption or key wrbpping, bnd rbise bn
     * <code>InvblidAlgorithmPbrbmeterException</code> if it is being
     * initiblized for decryption or key unwrbpping.
     * The generbted pbrbmeters cbn be retrieved using
     * {@link #getPbrbmeters() getPbrbmeters} or
     * {@link #getIV() getIV} (if the pbrbmeter is bn IV).
     *
     * <p>If this cipher requires blgorithm pbrbmeters thbt cbnnot be
     * derived from the input pbrbmeters, bnd there bre no rebsonbble
     * provider-specific defbult vblues, initiblizbtion will
     * necessbrily fbil.
     *
     * <p>If this cipher (including its underlying feedbbck or pbdding scheme)
     * requires bny rbndom bytes (e.g., for pbrbmeter generbtion), it will get
     * them using the {@link jbvb.security.SecureRbndom}
     * implementbtion of the highest-priority
     * instblled provider bs the source of rbndomness.
     * (If none of the instblled providers supply bn implementbtion of
     * SecureRbndom, b system-provided source of rbndomness will be used.)
     *
     * <p>Note thbt when b Cipher object is initiblized, it loses bll
     * previously-bcquired stbte. In other words, initiblizing b Cipher is
     * equivblent to crebting b new instbnce of thbt Cipher bnd initiblizing
     * it.
     *
     * @pbrbm opmode the operbtion mode of this cipher (this is one of the
     * following:
     * <code>ENCRYPT_MODE</code>, <code>DECRYPT_MODE</code>,
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>)
     * @pbrbm key the encryption key
     * @pbrbm pbrbms the blgorithm pbrbmeters
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher, or its keysize exceeds the mbximum bllowbble
     * keysize (bs determined from the configured jurisdiction policy files).
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this cipher,
     * or this cipher requires
     * blgorithm pbrbmeters bnd <code>pbrbms</code> is null, or the given
     * blgorithm pbrbmeters imply b cryptogrbphic strength thbt would exceed
     * the legbl limits (bs determined from the configured jurisdiction
     * policy files).
     * @throws UnsupportedOperbtionException if (@code opmode} is
     * {@code WRAP_MODE} or {@code UNWRAP_MODE} but the mode is not implemented
     * by the underlying {@code CipherSpi}.
     */
    public finbl void init(int opmode, Key key, AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException
    {
        init(opmode, key, pbrbms, JceSecurity.RANDOM);
    }

    /**
     * Initiblizes this cipher with b key, b set of blgorithm
     * pbrbmeters, bnd b source of rbndomness.
     *
     * <p>The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping or  key unwrbpping, depending
     * on the vblue of <code>opmode</code>.
     *
     * <p>If this cipher requires bny blgorithm pbrbmeters bnd
     * <code>pbrbms</code> is null, the underlying cipher implementbtion is
     * supposed to generbte the required pbrbmeters itself (using
     * provider-specific defbult or rbndom vblues) if it is being
     * initiblized for encryption or key wrbpping, bnd rbise bn
     * <code>InvblidAlgorithmPbrbmeterException</code> if it is being
     * initiblized for decryption or key unwrbpping.
     * The generbted pbrbmeters cbn be retrieved using
     * {@link #getPbrbmeters() getPbrbmeters} or
     * {@link #getIV() getIV} (if the pbrbmeter is bn IV).
     *
     * <p>If this cipher requires blgorithm pbrbmeters thbt cbnnot be
     * derived from the input pbrbmeters, bnd there bre no rebsonbble
     * provider-specific defbult vblues, initiblizbtion will
     * necessbrily fbil.
     *
     * <p>If this cipher (including its underlying feedbbck or pbdding scheme)
     * requires bny rbndom bytes (e.g., for pbrbmeter generbtion), it will get
     * them from <code>rbndom</code>.
     *
     * <p>Note thbt when b Cipher object is initiblized, it loses bll
     * previously-bcquired stbte. In other words, initiblizing b Cipher is
     * equivblent to crebting b new instbnce of thbt Cipher bnd initiblizing
     * it.
     *
     * @pbrbm opmode the operbtion mode of this cipher (this is one of the
     * following:
     * <code>ENCRYPT_MODE</code>, <code>DECRYPT_MODE</code>,
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>)
     * @pbrbm key the encryption key
     * @pbrbm pbrbms the blgorithm pbrbmeters
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher, or its keysize exceeds the mbximum bllowbble
     * keysize (bs determined from the configured jurisdiction policy files).
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this cipher,
     * or this cipher requires
     * blgorithm pbrbmeters bnd <code>pbrbms</code> is null, or the given
     * blgorithm pbrbmeters imply b cryptogrbphic strength thbt would exceed
     * the legbl limits (bs determined from the configured jurisdiction
     * policy files).
     * @throws UnsupportedOperbtionException if (@code opmode} is
     * {@code WRAP_MODE} or {@code UNWRAP_MODE} but the mode is not implemented
     * by the underlying {@code CipherSpi}.
     */
    public finbl void init(int opmode, Key key, AlgorithmPbrbmeterSpec pbrbms,
                           SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException
    {
        initiblized = fblse;
        checkOpmode(opmode);

        if (spi != null) {
            checkCryptoPerm(spi, key, pbrbms);
            spi.engineInit(opmode, key, pbrbms, rbndom);
        } else {
            chooseProvider(I_PARAMSPEC, opmode, key, pbrbms, null, rbndom);
        }

        initiblized = true;
        this.opmode = opmode;
    }

    /**
     * Initiblizes this cipher with b key bnd b set of blgorithm
     * pbrbmeters.
     *
     * <p>The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping or  key unwrbpping, depending
     * on the vblue of <code>opmode</code>.
     *
     * <p>If this cipher requires bny blgorithm pbrbmeters bnd
     * <code>pbrbms</code> is null, the underlying cipher implementbtion is
     * supposed to generbte the required pbrbmeters itself (using
     * provider-specific defbult or rbndom vblues) if it is being
     * initiblized for encryption or key wrbpping, bnd rbise bn
     * <code>InvblidAlgorithmPbrbmeterException</code> if it is being
     * initiblized for decryption or key unwrbpping.
     * The generbted pbrbmeters cbn be retrieved using
     * {@link #getPbrbmeters() getPbrbmeters} or
     * {@link #getIV() getIV} (if the pbrbmeter is bn IV).
     *
     * <p>If this cipher requires blgorithm pbrbmeters thbt cbnnot be
     * derived from the input pbrbmeters, bnd there bre no rebsonbble
     * provider-specific defbult vblues, initiblizbtion will
     * necessbrily fbil.
     *
     * <p>If this cipher (including its underlying feedbbck or pbdding scheme)
     * requires bny rbndom bytes (e.g., for pbrbmeter generbtion), it will get
     * them using the {@link jbvb.security.SecureRbndom}
     * implementbtion of the highest-priority
     * instblled provider bs the source of rbndomness.
     * (If none of the instblled providers supply bn implementbtion of
     * SecureRbndom, b system-provided source of rbndomness will be used.)
     *
     * <p>Note thbt when b Cipher object is initiblized, it loses bll
     * previously-bcquired stbte. In other words, initiblizing b Cipher is
     * equivblent to crebting b new instbnce of thbt Cipher bnd initiblizing
     * it.
     *
     * @pbrbm opmode the operbtion mode of this cipher (this is one of the
     * following: <code>ENCRYPT_MODE</code>,
     * <code>DECRYPT_MODE</code>, <code>WRAP_MODE</code>
     * or <code>UNWRAP_MODE</code>)
     * @pbrbm key the encryption key
     * @pbrbm pbrbms the blgorithm pbrbmeters
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher, or its keysize exceeds the mbximum bllowbble
     * keysize (bs determined from the configured jurisdiction policy files).
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this cipher,
     * or this cipher requires
     * blgorithm pbrbmeters bnd <code>pbrbms</code> is null, or the given
     * blgorithm pbrbmeters imply b cryptogrbphic strength thbt would exceed
     * the legbl limits (bs determined from the configured jurisdiction
     * policy files).
     * @throws UnsupportedOperbtionException if (@code opmode} is
     * {@code WRAP_MODE} or {@code UNWRAP_MODE} but the mode is not implemented
     * by the underlying {@code CipherSpi}.
     */
    public finbl void init(int opmode, Key key, AlgorithmPbrbmeters pbrbms)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException
    {
        init(opmode, key, pbrbms, JceSecurity.RANDOM);
    }

    /**
     * Initiblizes this cipher with b key, b set of blgorithm
     * pbrbmeters, bnd b source of rbndomness.
     *
     * <p>The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping or  key unwrbpping, depending
     * on the vblue of <code>opmode</code>.
     *
     * <p>If this cipher requires bny blgorithm pbrbmeters bnd
     * <code>pbrbms</code> is null, the underlying cipher implementbtion is
     * supposed to generbte the required pbrbmeters itself (using
     * provider-specific defbult or rbndom vblues) if it is being
     * initiblized for encryption or key wrbpping, bnd rbise bn
     * <code>InvblidAlgorithmPbrbmeterException</code> if it is being
     * initiblized for decryption or key unwrbpping.
     * The generbted pbrbmeters cbn be retrieved using
     * {@link #getPbrbmeters() getPbrbmeters} or
     * {@link #getIV() getIV} (if the pbrbmeter is bn IV).
     *
     * <p>If this cipher requires blgorithm pbrbmeters thbt cbnnot be
     * derived from the input pbrbmeters, bnd there bre no rebsonbble
     * provider-specific defbult vblues, initiblizbtion will
     * necessbrily fbil.
     *
     * <p>If this cipher (including its underlying feedbbck or pbdding scheme)
     * requires bny rbndom bytes (e.g., for pbrbmeter generbtion), it will get
     * them from <code>rbndom</code>.
     *
     * <p>Note thbt when b Cipher object is initiblized, it loses bll
     * previously-bcquired stbte. In other words, initiblizing b Cipher is
     * equivblent to crebting b new instbnce of thbt Cipher bnd initiblizing
     * it.
     *
     * @pbrbm opmode the operbtion mode of this cipher (this is one of the
     * following: <code>ENCRYPT_MODE</code>,
     * <code>DECRYPT_MODE</code>, <code>WRAP_MODE</code>
     * or <code>UNWRAP_MODE</code>)
     * @pbrbm key the encryption key
     * @pbrbm pbrbms the blgorithm pbrbmeters
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher, or its keysize exceeds the mbximum bllowbble
     * keysize (bs determined from the configured jurisdiction policy files).
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this cipher,
     * or this cipher requires
     * blgorithm pbrbmeters bnd <code>pbrbms</code> is null, or the given
     * blgorithm pbrbmeters imply b cryptogrbphic strength thbt would exceed
     * the legbl limits (bs determined from the configured jurisdiction
     * policy files).
     * @throws UnsupportedOperbtionException if (@code opmode} is
     * {@code WRAP_MODE} or {@code UNWRAP_MODE} but the mode is not implemented
     * by the underlying {@code CipherSpi}.
     */
    public finbl void init(int opmode, Key key, AlgorithmPbrbmeters pbrbms,
                           SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException
    {
        initiblized = fblse;
        checkOpmode(opmode);

        if (spi != null) {
            checkCryptoPerm(spi, key, pbrbms);
            spi.engineInit(opmode, key, pbrbms, rbndom);
        } else {
            chooseProvider(I_PARAMS, opmode, key, null, pbrbms, rbndom);
        }

        initiblized = true;
        this.opmode = opmode;
    }

    /**
     * Initiblizes this cipher with the public key from the given certificbte.
     * <p> The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping or  key unwrbpping, depending
     * on the vblue of <code>opmode</code>.
     *
     * <p>If the certificbte is of type X.509 bnd hbs b <i>key usbge</i>
     * extension field mbrked bs criticbl, bnd the vblue of the <i>key usbge</i>
     * extension field implies thbt the public key in
     * the certificbte bnd its corresponding privbte key bre not
     * supposed to be used for the operbtion represented by the vblue
     * of <code>opmode</code>,
     * bn <code>InvblidKeyException</code>
     * is thrown.
     *
     * <p> If this cipher requires bny blgorithm pbrbmeters thbt cbnnot be
     * derived from the public key in the given certificbte, the underlying
     * cipher
     * implementbtion is supposed to generbte the required pbrbmeters itself
     * (using provider-specific defbult or rbndom vblues) if it is being
     * initiblized for encryption or key wrbpping, bnd rbise bn <code>
     * InvblidKeyException</code> if it is being initiblized for decryption or
     * key unwrbpping.
     * The generbted pbrbmeters cbn be retrieved using
     * {@link #getPbrbmeters() getPbrbmeters} or
     * {@link #getIV() getIV} (if the pbrbmeter is bn IV).
     *
     * <p>If this cipher requires blgorithm pbrbmeters thbt cbnnot be
     * derived from the input pbrbmeters, bnd there bre no rebsonbble
     * provider-specific defbult vblues, initiblizbtion will
     * necessbrily fbil.
     *
     * <p>If this cipher (including its underlying feedbbck or pbdding scheme)
     * requires bny rbndom bytes (e.g., for pbrbmeter generbtion), it will get
     * them using the
     * <code>SecureRbndom</code>
     * implementbtion of the highest-priority
     * instblled provider bs the source of rbndomness.
     * (If none of the instblled providers supply bn implementbtion of
     * SecureRbndom, b system-provided source of rbndomness will be used.)
     *
     * <p>Note thbt when b Cipher object is initiblized, it loses bll
     * previously-bcquired stbte. In other words, initiblizing b Cipher is
     * equivblent to crebting b new instbnce of thbt Cipher bnd initiblizing
     * it.
     *
     * @pbrbm opmode the operbtion mode of this cipher (this is one of the
     * following:
     * <code>ENCRYPT_MODE</code>, <code>DECRYPT_MODE</code>,
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>)
     * @pbrbm certificbte the certificbte
     *
     * @exception InvblidKeyException if the public key in the given
     * certificbte is inbppropribte for initiblizing this cipher, or this
     * cipher requires blgorithm pbrbmeters thbt cbnnot be determined from the
     * public key in the given certificbte, or the keysize of the public key
     * in the given certificbte hbs b keysize thbt exceeds the mbximum
     * bllowbble keysize (bs determined by the configured jurisdiction policy
     * files).
     * @throws UnsupportedOperbtionException if (@code opmode} is
     * {@code WRAP_MODE} or {@code UNWRAP_MODE} but the mode is not implemented
     * by the underlying {@code CipherSpi}.
     */
    public finbl void init(int opmode, Certificbte certificbte)
            throws InvblidKeyException
    {
        init(opmode, certificbte, JceSecurity.RANDOM);
    }

    /**
     * Initiblizes this cipher with the public key from the given certificbte
     * bnd
     * b source of rbndomness.
     *
     * <p>The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping
     * or key unwrbpping, depending on
     * the vblue of <code>opmode</code>.
     *
     * <p>If the certificbte is of type X.509 bnd hbs b <i>key usbge</i>
     * extension field mbrked bs criticbl, bnd the vblue of the <i>key usbge</i>
     * extension field implies thbt the public key in
     * the certificbte bnd its corresponding privbte key bre not
     * supposed to be used for the operbtion represented by the vblue of
     * <code>opmode</code>,
     * bn <code>InvblidKeyException</code>
     * is thrown.
     *
     * <p>If this cipher requires bny blgorithm pbrbmeters thbt cbnnot be
     * derived from the public key in the given <code>certificbte</code>,
     * the underlying cipher
     * implementbtion is supposed to generbte the required pbrbmeters itself
     * (using provider-specific defbult or rbndom vblues) if it is being
     * initiblized for encryption or key wrbpping, bnd rbise bn
     * <code>InvblidKeyException</code> if it is being
     * initiblized for decryption or key unwrbpping.
     * The generbted pbrbmeters cbn be retrieved using
     * {@link #getPbrbmeters() getPbrbmeters} or
     * {@link #getIV() getIV} (if the pbrbmeter is bn IV).
     *
     * <p>If this cipher requires blgorithm pbrbmeters thbt cbnnot be
     * derived from the input pbrbmeters, bnd there bre no rebsonbble
     * provider-specific defbult vblues, initiblizbtion will
     * necessbrily fbil.
     *
     * <p>If this cipher (including its underlying feedbbck or pbdding scheme)
     * requires bny rbndom bytes (e.g., for pbrbmeter generbtion), it will get
     * them from <code>rbndom</code>.
     *
     * <p>Note thbt when b Cipher object is initiblized, it loses bll
     * previously-bcquired stbte. In other words, initiblizing b Cipher is
     * equivblent to crebting b new instbnce of thbt Cipher bnd initiblizing
     * it.
     *
     * @pbrbm opmode the operbtion mode of this cipher (this is one of the
     * following:
     * <code>ENCRYPT_MODE</code>, <code>DECRYPT_MODE</code>,
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>)
     * @pbrbm certificbte the certificbte
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the public key in the given
     * certificbte is inbppropribte for initiblizing this cipher, or this
     * cipher
     * requires blgorithm pbrbmeters thbt cbnnot be determined from the
     * public key in the given certificbte, or the keysize of the public key
     * in the given certificbte hbs b keysize thbt exceeds the mbximum
     * bllowbble keysize (bs determined by the configured jurisdiction policy
     * files).
     * @throws UnsupportedOperbtionException if (@code opmode} is
     * {@code WRAP_MODE} or {@code UNWRAP_MODE} but the mode is not implemented
     * by the underlying {@code CipherSpi}.
     */
    public finbl void init(int opmode, Certificbte certificbte,
                           SecureRbndom rbndom)
            throws InvblidKeyException
    {
        initiblized = fblse;
        checkOpmode(opmode);

        // Check key usbge if the certificbte is of
        // type X.509.
        if (certificbte instbnceof jbvb.security.cert.X509Certificbte) {
            // Check whether the cert hbs b key usbge extension
            // mbrked bs b criticbl extension.
            X509Certificbte cert = (X509Certificbte)certificbte;
            Set<String> critSet = cert.getCriticblExtensionOIDs();

            if (critSet != null && !critSet.isEmpty()
                && critSet.contbins(KEY_USAGE_EXTENSION_OID)) {
                boolebn[] keyUsbgeInfo = cert.getKeyUsbge();
                // keyUsbgeInfo[2] is for keyEncipherment;
                // keyUsbgeInfo[3] is for dbtbEncipherment.
                if ((keyUsbgeInfo != null) &&
                    (((opmode == Cipher.ENCRYPT_MODE) &&
                      (keyUsbgeInfo.length > 3) &&
                      (keyUsbgeInfo[3] == fblse)) ||
                     ((opmode == Cipher.WRAP_MODE) &&
                      (keyUsbgeInfo.length > 2) &&
                      (keyUsbgeInfo[2] == fblse)))) {
                    throw new InvblidKeyException("Wrong key usbge");
                }
            }
        }

        PublicKey publicKey =
            (certificbte==null? null:certificbte.getPublicKey());

        if (spi != null) {
            checkCryptoPerm(spi, publicKey);
            spi.engineInit(opmode, publicKey, rbndom);
        } else {
            try {
                chooseProvider(I_CERT, opmode, publicKey, null, null, rbndom);
            } cbtch (InvblidAlgorithmPbrbmeterException e) {
                // should never occur
                throw new InvblidKeyException(e);
            }
        }

        initiblized = true;
        this.opmode = opmode;
    }

    /**
     * Ensures thbt Cipher is in b vblid stbte for updbte() bnd doFinbl()
     * cblls - should be initiblized bnd in ENCRYPT_MODE or DECRYPT_MODE.
     * @throws IllegblStbteException if Cipher object is not in vblid stbte.
     */
    privbte void checkCipherStbte() {
        if (!(this instbnceof NullCipher)) {
            if (!initiblized) {
                throw new IllegblStbteException("Cipher not initiblized");
            }
            if ((opmode != Cipher.ENCRYPT_MODE) &&
                (opmode != Cipher.DECRYPT_MODE)) {
                throw new IllegblStbteException("Cipher not initiblized " +
                                                "for encryption/decryption");
            }
        }
    }

    /**
     * Continues b multiple-pbrt encryption or decryption operbtion
     * (depending on how this cipher wbs initiblized), processing bnother dbtb
     * pbrt.
     *
     * <p>The bytes in the <code>input</code> buffer bre processed, bnd the
     * result is stored in b new buffer.
     *
     * <p>If <code>input</code> hbs b length of zero, this method returns
     * <code>null</code>.
     *
     * @pbrbm input the input buffer
     *
     * @return the new buffer with the result, or null if the underlying
     * cipher is b block cipher bnd the input dbtb is too short to result in b
     * new block.
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized)
     */
    public finbl byte[] updbte(byte[] input) {
        checkCipherStbte();

        // Input sbnity check
        if (input == null) {
            throw new IllegblArgumentException("Null input buffer");
        }

        chooseFirstProvider();
        if (input.length == 0) {
            return null;
        }
        return spi.engineUpdbte(input, 0, input.length);
    }

    /**
     * Continues b multiple-pbrt encryption or decryption operbtion
     * (depending on how this cipher wbs initiblized), processing bnother dbtb
     * pbrt.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code> inclusive, bre processed,
     * bnd the result is stored in b new buffer.
     *
     * <p>If <code>inputLen</code> is zero, this method returns
     * <code>null</code>.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     *
     * @return the new buffer with the result, or null if the underlying
     * cipher is b block cipher bnd the input dbtb is too short to result in b
     * new block.
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized)
     */
    public finbl byte[] updbte(byte[] input, int inputOffset, int inputLen) {
        checkCipherStbte();

        // Input sbnity check
        if (input == null || inputOffset < 0
            || inputLen > (input.length - inputOffset) || inputLen < 0) {
            throw new IllegblArgumentException("Bbd brguments");
        }

        chooseFirstProvider();
        if (inputLen == 0) {
            return null;
        }
        return spi.engineUpdbte(input, inputOffset, inputLen);
    }

    /**
     * Continues b multiple-pbrt encryption or decryption operbtion
     * (depending on how this cipher wbs initiblized), processing bnother dbtb
     * pbrt.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code> inclusive, bre processed,
     * bnd the result is stored in the <code>output</code> buffer.
     *
     * <p>If the <code>output</code> buffer is too smbll to hold the result,
     * b <code>ShortBufferException</code> is thrown. In this cbse, repebt this
     * cbll with b lbrger output buffer. Use
     * {@link #getOutputSize(int) getOutputSize} to determine how big
     * the output buffer should be.
     *
     * <p>If <code>inputLen</code> is zero, this method returns
     * b length of zero.
     *
     * <p>Note: this method should be copy-sbfe, which mebns the
     * <code>input</code> bnd <code>output</code> buffers cbn reference
     * the sbme byte brrby bnd no unprocessed input dbtb is overwritten
     * when the result is copied into the output buffer.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     * @pbrbm output the buffer for the result
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized)
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result
     */
    public finbl int updbte(byte[] input, int inputOffset, int inputLen,
                            byte[] output)
            throws ShortBufferException {
        checkCipherStbte();

        // Input sbnity check
        if (input == null || inputOffset < 0
            || inputLen > (input.length - inputOffset) || inputLen < 0) {
            throw new IllegblArgumentException("Bbd brguments");
        }

        chooseFirstProvider();
        if (inputLen == 0) {
            return 0;
        }
        return spi.engineUpdbte(input, inputOffset, inputLen,
                                      output, 0);
    }

    /**
     * Continues b multiple-pbrt encryption or decryption operbtion
     * (depending on how this cipher wbs initiblized), processing bnother dbtb
     * pbrt.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code> inclusive, bre processed,
     * bnd the result is stored in the <code>output</code> buffer, stbrting bt
     * <code>outputOffset</code> inclusive.
     *
     * <p>If the <code>output</code> buffer is too smbll to hold the result,
     * b <code>ShortBufferException</code> is thrown. In this cbse, repebt this
     * cbll with b lbrger output buffer. Use
     * {@link #getOutputSize(int) getOutputSize} to determine how big
     * the output buffer should be.
     *
     * <p>If <code>inputLen</code> is zero, this method returns
     * b length of zero.
     *
     * <p>Note: this method should be copy-sbfe, which mebns the
     * <code>input</code> bnd <code>output</code> buffers cbn reference
     * the sbme byte brrby bnd no unprocessed input dbtb is overwritten
     * when the result is copied into the output buffer.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     * @pbrbm output the buffer for the result
     * @pbrbm outputOffset the offset in <code>output</code> where the result
     * is stored
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized)
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result
     */
    public finbl int updbte(byte[] input, int inputOffset, int inputLen,
                            byte[] output, int outputOffset)
            throws ShortBufferException {
        checkCipherStbte();

        // Input sbnity check
        if (input == null || inputOffset < 0
            || inputLen > (input.length - inputOffset) || inputLen < 0
            || outputOffset < 0) {
            throw new IllegblArgumentException("Bbd brguments");
        }

        chooseFirstProvider();
        if (inputLen == 0) {
            return 0;
        }
        return spi.engineUpdbte(input, inputOffset, inputLen,
                                      output, outputOffset);
    }

    /**
     * Continues b multiple-pbrt encryption or decryption operbtion
     * (depending on how this cipher wbs initiblized), processing bnother dbtb
     * pbrt.
     *
     * <p>All <code>input.rembining()</code> bytes stbrting bt
     * <code>input.position()</code> bre processed. The result is stored
     * in the output buffer.
     * Upon return, the input buffer's position will be equbl
     * to its limit; its limit will not hbve chbnged. The output buffer's
     * position will hbve bdvbnced by n, where n is the vblue returned
     * by this method; the output buffer's limit will not hbve chbnged.
     *
     * <p>If <code>output.rembining()</code> bytes bre insufficient to
     * hold the result, b <code>ShortBufferException</code> is thrown.
     * In this cbse, repebt this cbll with b lbrger output buffer. Use
     * {@link #getOutputSize(int) getOutputSize} to determine how big
     * the output buffer should be.
     *
     * <p>Note: this method should be copy-sbfe, which mebns the
     * <code>input</code> bnd <code>output</code> buffers cbn reference
     * the sbme block of memory bnd no unprocessed input dbtb is overwritten
     * when the result is copied into the output buffer.
     *
     * @pbrbm input the input ByteBuffer
     * @pbrbm output the output ByteByffer
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized)
     * @exception IllegblArgumentException if input bnd output bre the
     *   sbme object
     * @exception RebdOnlyBufferException if the output buffer is rebd-only
     * @exception ShortBufferException if there is insufficient spbce in the
     * output buffer
     * @since 1.5
     */
    public finbl int updbte(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException {
        checkCipherStbte();

        if ((input == null) || (output == null)) {
            throw new IllegblArgumentException("Buffers must not be null");
        }
        if (input == output) {
            throw new IllegblArgumentException("Input bnd output buffers must "
                + "not be the sbme object, consider using buffer.duplicbte()");
        }
        if (output.isRebdOnly()) {
            throw new RebdOnlyBufferException();
        }

        chooseFirstProvider();
        return spi.engineUpdbte(input, output);
    }

    /**
     * Finishes b multiple-pbrt encryption or decryption operbtion, depending
     * on how this cipher wbs initiblized.
     *
     * <p>Input dbtb thbt mby hbve been buffered during b previous
     * <code>updbte</code> operbtion is processed, with pbdding (if requested)
     * being bpplied.
     * If bn AEAD mode such bs GCM/CCM is being used, the buthenticbtion
     * tbg is bppended in the cbse of encryption, or verified in the
     * cbse of decryption.
     * The result is stored in b new buffer.
     *
     * <p>Upon finishing, this method resets this cipher object to the stbte
     * it wbs in when previously initiblized vib b cbll to <code>init</code>.
     * Thbt is, the object is reset bnd bvbilbble to encrypt or decrypt
     * (depending on the operbtion mode thbt wbs specified in the cbll to
     * <code>init</code>) more dbtb.
     *
     * <p>Note: if bny exception is thrown, this cipher object mby need to
     * be reset before it cbn be used bgbin.
     *
     * @return the new buffer with the result
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized)
     * @exception IllegblBlockSizeException if this cipher is b block cipher,
     * no pbdding hbs been requested (only in encryption mode), bnd the totbl
     * input length of the dbtb processed by this cipher is not b multiple of
     * block size; or if this encryption blgorithm is unbble to
     * process the input dbtb provided.
     * @exception BbdPbddingException if this cipher is in decryption mode,
     * bnd (un)pbdding hbs been requested, but the decrypted dbtb is not
     * bounded by the bppropribte pbdding bytes
     * @exception AEADBbdTbgException if this cipher is decrypting in bn
     * AEAD mode (such bs GCM/CCM), bnd the received buthenticbtion tbg
     * does not mbtch the cblculbted vblue
     */
    public finbl byte[] doFinbl()
            throws IllegblBlockSizeException, BbdPbddingException {
        checkCipherStbte();

        chooseFirstProvider();
        return spi.engineDoFinbl(null, 0, 0);
    }

    /**
     * Finishes b multiple-pbrt encryption or decryption operbtion, depending
     * on how this cipher wbs initiblized.
     *
     * <p>Input dbtb thbt mby hbve been buffered during b previous
     * <code>updbte</code> operbtion is processed, with pbdding (if requested)
     * being bpplied.
     * If bn AEAD mode such bs GCM/CCM is being used, the buthenticbtion
     * tbg is bppended in the cbse of encryption, or verified in the
     * cbse of decryption.
     * The result is stored in the <code>output</code> buffer, stbrting bt
     * <code>outputOffset</code> inclusive.
     *
     * <p>If the <code>output</code> buffer is too smbll to hold the result,
     * b <code>ShortBufferException</code> is thrown. In this cbse, repebt this
     * cbll with b lbrger output buffer. Use
     * {@link #getOutputSize(int) getOutputSize} to determine how big
     * the output buffer should be.
     *
     * <p>Upon finishing, this method resets this cipher object to the stbte
     * it wbs in when previously initiblized vib b cbll to <code>init</code>.
     * Thbt is, the object is reset bnd bvbilbble to encrypt or decrypt
     * (depending on the operbtion mode thbt wbs specified in the cbll to
     * <code>init</code>) more dbtb.
     *
     * <p>Note: if bny exception is thrown, this cipher object mby need to
     * be reset before it cbn be used bgbin.
     *
     * @pbrbm output the buffer for the result
     * @pbrbm outputOffset the offset in <code>output</code> where the result
     * is stored
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized)
     * @exception IllegblBlockSizeException if this cipher is b block cipher,
     * no pbdding hbs been requested (only in encryption mode), bnd the totbl
     * input length of the dbtb processed by this cipher is not b multiple of
     * block size; or if this encryption blgorithm is unbble to
     * process the input dbtb provided.
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result
     * @exception BbdPbddingException if this cipher is in decryption mode,
     * bnd (un)pbdding hbs been requested, but the decrypted dbtb is not
     * bounded by the bppropribte pbdding bytes
     * @exception AEADBbdTbgException if this cipher is decrypting in bn
     * AEAD mode (such bs GCM/CCM), bnd the received buthenticbtion tbg
     * does not mbtch the cblculbted vblue
     */
    public finbl int doFinbl(byte[] output, int outputOffset)
            throws IllegblBlockSizeException, ShortBufferException,
               BbdPbddingException {
        checkCipherStbte();

        // Input sbnity check
        if ((output == null) || (outputOffset < 0)) {
            throw new IllegblArgumentException("Bbd brguments");
        }

        chooseFirstProvider();
        return spi.engineDoFinbl(null, 0, 0, output, outputOffset);
    }

    /**
     * Encrypts or decrypts dbtb in b single-pbrt operbtion, or finishes b
     * multiple-pbrt operbtion. The dbtb is encrypted or decrypted,
     * depending on how this cipher wbs initiblized.
     *
     * <p>The bytes in the <code>input</code> buffer, bnd bny input bytes thbt
     * mby hbve been buffered during b previous <code>updbte</code> operbtion,
     * bre processed, with pbdding (if requested) being bpplied.
     * If bn AEAD mode such bs GCM/CCM is being used, the buthenticbtion
     * tbg is bppended in the cbse of encryption, or verified in the
     * cbse of decryption.
     * The result is stored in b new buffer.
     *
     * <p>Upon finishing, this method resets this cipher object to the stbte
     * it wbs in when previously initiblized vib b cbll to <code>init</code>.
     * Thbt is, the object is reset bnd bvbilbble to encrypt or decrypt
     * (depending on the operbtion mode thbt wbs specified in the cbll to
     * <code>init</code>) more dbtb.
     *
     * <p>Note: if bny exception is thrown, this cipher object mby need to
     * be reset before it cbn be used bgbin.
     *
     * @pbrbm input the input buffer
     *
     * @return the new buffer with the result
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized)
     * @exception IllegblBlockSizeException if this cipher is b block cipher,
     * no pbdding hbs been requested (only in encryption mode), bnd the totbl
     * input length of the dbtb processed by this cipher is not b multiple of
     * block size; or if this encryption blgorithm is unbble to
     * process the input dbtb provided.
     * @exception BbdPbddingException if this cipher is in decryption mode,
     * bnd (un)pbdding hbs been requested, but the decrypted dbtb is not
     * bounded by the bppropribte pbdding bytes
     * @exception AEADBbdTbgException if this cipher is decrypting in bn
     * AEAD mode (such bs GCM/CCM), bnd the received buthenticbtion tbg
     * does not mbtch the cblculbted vblue
     */
    public finbl byte[] doFinbl(byte[] input)
            throws IllegblBlockSizeException, BbdPbddingException {
        checkCipherStbte();

        // Input sbnity check
        if (input == null) {
            throw new IllegblArgumentException("Null input buffer");
        }

        chooseFirstProvider();
        return spi.engineDoFinbl(input, 0, input.length);
    }

    /**
     * Encrypts or decrypts dbtb in b single-pbrt operbtion, or finishes b
     * multiple-pbrt operbtion. The dbtb is encrypted or decrypted,
     * depending on how this cipher wbs initiblized.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code> inclusive, bnd bny input
     * bytes thbt mby hbve been buffered during b previous <code>updbte</code>
     * operbtion, bre processed, with pbdding (if requested) being bpplied.
     * If bn AEAD mode such bs GCM/CCM is being used, the buthenticbtion
     * tbg is bppended in the cbse of encryption, or verified in the
     * cbse of decryption.
     * The result is stored in b new buffer.
     *
     * <p>Upon finishing, this method resets this cipher object to the stbte
     * it wbs in when previously initiblized vib b cbll to <code>init</code>.
     * Thbt is, the object is reset bnd bvbilbble to encrypt or decrypt
     * (depending on the operbtion mode thbt wbs specified in the cbll to
     * <code>init</code>) more dbtb.
     *
     * <p>Note: if bny exception is thrown, this cipher object mby need to
     * be reset before it cbn be used bgbin.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     *
     * @return the new buffer with the result
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized)
     * @exception IllegblBlockSizeException if this cipher is b block cipher,
     * no pbdding hbs been requested (only in encryption mode), bnd the totbl
     * input length of the dbtb processed by this cipher is not b multiple of
     * block size; or if this encryption blgorithm is unbble to
     * process the input dbtb provided.
     * @exception BbdPbddingException if this cipher is in decryption mode,
     * bnd (un)pbdding hbs been requested, but the decrypted dbtb is not
     * bounded by the bppropribte pbdding bytes
     * @exception AEADBbdTbgException if this cipher is decrypting in bn
     * AEAD mode (such bs GCM/CCM), bnd the received buthenticbtion tbg
     * does not mbtch the cblculbted vblue
     */
    public finbl byte[] doFinbl(byte[] input, int inputOffset, int inputLen)
            throws IllegblBlockSizeException, BbdPbddingException {
        checkCipherStbte();

        // Input sbnity check
        if (input == null || inputOffset < 0
            || inputLen > (input.length - inputOffset) || inputLen < 0) {
            throw new IllegblArgumentException("Bbd brguments");
        }

        chooseFirstProvider();
        return spi.engineDoFinbl(input, inputOffset, inputLen);
    }

    /**
     * Encrypts or decrypts dbtb in b single-pbrt operbtion, or finishes b
     * multiple-pbrt operbtion. The dbtb is encrypted or decrypted,
     * depending on how this cipher wbs initiblized.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code> inclusive, bnd bny input
     * bytes thbt mby hbve been buffered during b previous <code>updbte</code>
     * operbtion, bre processed, with pbdding (if requested) being bpplied.
     * If bn AEAD mode such bs GCM/CCM is being used, the buthenticbtion
     * tbg is bppended in the cbse of encryption, or verified in the
     * cbse of decryption.
     * The result is stored in the <code>output</code> buffer.
     *
     * <p>If the <code>output</code> buffer is too smbll to hold the result,
     * b <code>ShortBufferException</code> is thrown. In this cbse, repebt this
     * cbll with b lbrger output buffer. Use
     * {@link #getOutputSize(int) getOutputSize} to determine how big
     * the output buffer should be.
     *
     * <p>Upon finishing, this method resets this cipher object to the stbte
     * it wbs in when previously initiblized vib b cbll to <code>init</code>.
     * Thbt is, the object is reset bnd bvbilbble to encrypt or decrypt
     * (depending on the operbtion mode thbt wbs specified in the cbll to
     * <code>init</code>) more dbtb.
     *
     * <p>Note: if bny exception is thrown, this cipher object mby need to
     * be reset before it cbn be used bgbin.
     *
     * <p>Note: this method should be copy-sbfe, which mebns the
     * <code>input</code> bnd <code>output</code> buffers cbn reference
     * the sbme byte brrby bnd no unprocessed input dbtb is overwritten
     * when the result is copied into the output buffer.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     * @pbrbm output the buffer for the result
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized)
     * @exception IllegblBlockSizeException if this cipher is b block cipher,
     * no pbdding hbs been requested (only in encryption mode), bnd the totbl
     * input length of the dbtb processed by this cipher is not b multiple of
     * block size; or if this encryption blgorithm is unbble to
     * process the input dbtb provided.
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result
     * @exception BbdPbddingException if this cipher is in decryption mode,
     * bnd (un)pbdding hbs been requested, but the decrypted dbtb is not
     * bounded by the bppropribte pbdding bytes
     * @exception AEADBbdTbgException if this cipher is decrypting in bn
     * AEAD mode (such bs GCM/CCM), bnd the received buthenticbtion tbg
     * does not mbtch the cblculbted vblue
     */
    public finbl int doFinbl(byte[] input, int inputOffset, int inputLen,
                             byte[] output)
            throws ShortBufferException, IllegblBlockSizeException,
            BbdPbddingException {
        checkCipherStbte();

        // Input sbnity check
        if (input == null || inputOffset < 0
            || inputLen > (input.length - inputOffset) || inputLen < 0) {
            throw new IllegblArgumentException("Bbd brguments");
        }

        chooseFirstProvider();
        return spi.engineDoFinbl(input, inputOffset, inputLen,
                                       output, 0);
    }

    /**
     * Encrypts or decrypts dbtb in b single-pbrt operbtion, or finishes b
     * multiple-pbrt operbtion. The dbtb is encrypted or decrypted,
     * depending on how this cipher wbs initiblized.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code> inclusive, bnd bny input
     * bytes thbt mby hbve been buffered during b previous
     * <code>updbte</code> operbtion, bre processed, with pbdding
     * (if requested) being bpplied.
     * If bn AEAD mode such bs GCM/CCM is being used, the buthenticbtion
     * tbg is bppended in the cbse of encryption, or verified in the
     * cbse of decryption.
     * The result is stored in the <code>output</code> buffer, stbrting bt
     * <code>outputOffset</code> inclusive.
     *
     * <p>If the <code>output</code> buffer is too smbll to hold the result,
     * b <code>ShortBufferException</code> is thrown. In this cbse, repebt this
     * cbll with b lbrger output buffer. Use
     * {@link #getOutputSize(int) getOutputSize} to determine how big
     * the output buffer should be.
     *
     * <p>Upon finishing, this method resets this cipher object to the stbte
     * it wbs in when previously initiblized vib b cbll to <code>init</code>.
     * Thbt is, the object is reset bnd bvbilbble to encrypt or decrypt
     * (depending on the operbtion mode thbt wbs specified in the cbll to
     * <code>init</code>) more dbtb.
     *
     * <p>Note: if bny exception is thrown, this cipher object mby need to
     * be reset before it cbn be used bgbin.
     *
     * <p>Note: this method should be copy-sbfe, which mebns the
     * <code>input</code> bnd <code>output</code> buffers cbn reference
     * the sbme byte brrby bnd no unprocessed input dbtb is overwritten
     * when the result is copied into the output buffer.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     * @pbrbm output the buffer for the result
     * @pbrbm outputOffset the offset in <code>output</code> where the result
     * is stored
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized)
     * @exception IllegblBlockSizeException if this cipher is b block cipher,
     * no pbdding hbs been requested (only in encryption mode), bnd the totbl
     * input length of the dbtb processed by this cipher is not b multiple of
     * block size; or if this encryption blgorithm is unbble to
     * process the input dbtb provided.
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result
     * @exception BbdPbddingException if this cipher is in decryption mode,
     * bnd (un)pbdding hbs been requested, but the decrypted dbtb is not
     * bounded by the bppropribte pbdding bytes
     * @exception AEADBbdTbgException if this cipher is decrypting in bn
     * AEAD mode (such bs GCM/CCM), bnd the received buthenticbtion tbg
     * does not mbtch the cblculbted vblue
     */
    public finbl int doFinbl(byte[] input, int inputOffset, int inputLen,
                             byte[] output, int outputOffset)
            throws ShortBufferException, IllegblBlockSizeException,
            BbdPbddingException {
        checkCipherStbte();

        // Input sbnity check
        if (input == null || inputOffset < 0
            || inputLen > (input.length - inputOffset) || inputLen < 0
            || outputOffset < 0) {
            throw new IllegblArgumentException("Bbd brguments");
        }

        chooseFirstProvider();
        return spi.engineDoFinbl(input, inputOffset, inputLen,
                                       output, outputOffset);
    }

    /**
     * Encrypts or decrypts dbtb in b single-pbrt operbtion, or finishes b
     * multiple-pbrt operbtion. The dbtb is encrypted or decrypted,
     * depending on how this cipher wbs initiblized.
     *
     * <p>All <code>input.rembining()</code> bytes stbrting bt
     * <code>input.position()</code> bre processed.
     * If bn AEAD mode such bs GCM/CCM is being used, the buthenticbtion
     * tbg is bppended in the cbse of encryption, or verified in the
     * cbse of decryption.
     * The result is stored in the output buffer.
     * Upon return, the input buffer's position will be equbl
     * to its limit; its limit will not hbve chbnged. The output buffer's
     * position will hbve bdvbnced by n, where n is the vblue returned
     * by this method; the output buffer's limit will not hbve chbnged.
     *
     * <p>If <code>output.rembining()</code> bytes bre insufficient to
     * hold the result, b <code>ShortBufferException</code> is thrown.
     * In this cbse, repebt this cbll with b lbrger output buffer. Use
     * {@link #getOutputSize(int) getOutputSize} to determine how big
     * the output buffer should be.
     *
     * <p>Upon finishing, this method resets this cipher object to the stbte
     * it wbs in when previously initiblized vib b cbll to <code>init</code>.
     * Thbt is, the object is reset bnd bvbilbble to encrypt or decrypt
     * (depending on the operbtion mode thbt wbs specified in the cbll to
     * <code>init</code>) more dbtb.
     *
     * <p>Note: if bny exception is thrown, this cipher object mby need to
     * be reset before it cbn be used bgbin.
     *
     * <p>Note: this method should be copy-sbfe, which mebns the
     * <code>input</code> bnd <code>output</code> buffers cbn reference
     * the sbme byte brrby bnd no unprocessed input dbtb is overwritten
     * when the result is copied into the output buffer.
     *
     * @pbrbm input the input ByteBuffer
     * @pbrbm output the output ByteBuffer
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized)
     * @exception IllegblArgumentException if input bnd output bre the
     *   sbme object
     * @exception RebdOnlyBufferException if the output buffer is rebd-only
     * @exception IllegblBlockSizeException if this cipher is b block cipher,
     * no pbdding hbs been requested (only in encryption mode), bnd the totbl
     * input length of the dbtb processed by this cipher is not b multiple of
     * block size; or if this encryption blgorithm is unbble to
     * process the input dbtb provided.
     * @exception ShortBufferException if there is insufficient spbce in the
     * output buffer
     * @exception BbdPbddingException if this cipher is in decryption mode,
     * bnd (un)pbdding hbs been requested, but the decrypted dbtb is not
     * bounded by the bppropribte pbdding bytes
     * @exception AEADBbdTbgException if this cipher is decrypting in bn
     * AEAD mode (such bs GCM/CCM), bnd the received buthenticbtion tbg
     * does not mbtch the cblculbted vblue
     *
     * @since 1.5
     */
    public finbl int doFinbl(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException, IllegblBlockSizeException,
            BbdPbddingException {
        checkCipherStbte();

        if ((input == null) || (output == null)) {
            throw new IllegblArgumentException("Buffers must not be null");
        }
        if (input == output) {
            throw new IllegblArgumentException("Input bnd output buffers must "
                + "not be the sbme object, consider using buffer.duplicbte()");
        }
        if (output.isRebdOnly()) {
            throw new RebdOnlyBufferException();
        }

        chooseFirstProvider();
        return spi.engineDoFinbl(input, output);
    }

    /**
     * Wrbp b key.
     *
     * @pbrbm key the key to be wrbpped.
     *
     * @return the wrbpped key.
     *
     * @exception IllegblStbteException if this cipher is in b wrong
     * stbte (e.g., hbs not been initiblized).
     *
     * @exception IllegblBlockSizeException if this cipher is b block
     * cipher, no pbdding hbs been requested, bnd the length of the
     * encoding of the key to be wrbpped is not b
     * multiple of the block size.
     *
     * @exception InvblidKeyException if it is impossible or unsbfe to
     * wrbp the key with this cipher (e.g., b hbrdwbre protected key is
     * being pbssed to b softwbre-only cipher).
     *
     * @throws UnsupportedOperbtionException if the corresponding method in the
     * {@code CipherSpi} is not supported.
     */
    public finbl byte[] wrbp(Key key)
            throws IllegblBlockSizeException, InvblidKeyException {
        if (!(this instbnceof NullCipher)) {
            if (!initiblized) {
                throw new IllegblStbteException("Cipher not initiblized");
            }
            if (opmode != Cipher.WRAP_MODE) {
                throw new IllegblStbteException("Cipher not initiblized " +
                                                "for wrbpping keys");
            }
        }

        chooseFirstProvider();
        return spi.engineWrbp(key);
    }

    /**
     * Unwrbp b previously wrbpped key.
     *
     * @pbrbm wrbppedKey the key to be unwrbpped.
     *
     * @pbrbm wrbppedKeyAlgorithm the blgorithm bssocibted with the wrbpped
     * key.
     *
     * @pbrbm wrbppedKeyType the type of the wrbpped key. This must be one of
     * <code>SECRET_KEY</code>, <code>PRIVATE_KEY</code>, or
     * <code>PUBLIC_KEY</code>.
     *
     * @return the unwrbpped key.
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized).
     *
     * @exception NoSuchAlgorithmException if no instblled providers
     * cbn crebte keys of type <code>wrbppedKeyType</code> for the
     * <code>wrbppedKeyAlgorithm</code>.
     *
     * @exception InvblidKeyException if <code>wrbppedKey</code> does not
     * represent b wrbpped key of type <code>wrbppedKeyType</code> for
     * the <code>wrbppedKeyAlgorithm</code>.
     *
     * @throws UnsupportedOperbtionException if the corresponding method in the
     * {@code CipherSpi} is not supported.
     */
    public finbl Key unwrbp(byte[] wrbppedKey,
                            String wrbppedKeyAlgorithm,
                            int wrbppedKeyType)
            throws InvblidKeyException, NoSuchAlgorithmException {

        if (!(this instbnceof NullCipher)) {
            if (!initiblized) {
                throw new IllegblStbteException("Cipher not initiblized");
            }
            if (opmode != Cipher.UNWRAP_MODE) {
                throw new IllegblStbteException("Cipher not initiblized " +
                                                "for unwrbpping keys");
            }
        }
        if ((wrbppedKeyType != SECRET_KEY) &&
            (wrbppedKeyType != PRIVATE_KEY) &&
            (wrbppedKeyType != PUBLIC_KEY)) {
            throw new InvblidPbrbmeterException("Invblid key type");
        }

        chooseFirstProvider();
        return spi.engineUnwrbp(wrbppedKey,
                                      wrbppedKeyAlgorithm,
                                      wrbppedKeyType);
    }

    privbte AlgorithmPbrbmeterSpec getAlgorithmPbrbmeterSpec(
                                      AlgorithmPbrbmeters pbrbms)
            throws InvblidPbrbmeterSpecException {
        if (pbrbms == null) {
            return null;
        }

        String blg = pbrbms.getAlgorithm().toUpperCbse(Locble.ENGLISH);

        if (blg.equblsIgnoreCbse("RC2")) {
            return pbrbms.getPbrbmeterSpec(RC2PbrbmeterSpec.clbss);
        }

        if (blg.equblsIgnoreCbse("RC5")) {
            return pbrbms.getPbrbmeterSpec(RC5PbrbmeterSpec.clbss);
        }

        if (blg.stbrtsWith("PBE")) {
            return pbrbms.getPbrbmeterSpec(PBEPbrbmeterSpec.clbss);
        }

        if (blg.stbrtsWith("DES")) {
            return pbrbms.getPbrbmeterSpec(IvPbrbmeterSpec.clbss);
        }
        return null;
    }

    privbte stbtic CryptoPermission getConfiguredPermission(
            String trbnsformbtion) throws NullPointerException,
            NoSuchAlgorithmException {
        if (trbnsformbtion == null) throw new NullPointerException();
        String[] pbrts = tokenizeTrbnsformbtion(trbnsformbtion);
        return JceSecurityMbnbger.INSTANCE.getCryptoPermission(pbrts[0]);
    }

    /**
     * Returns the mbximum key length for the specified trbnsformbtion
     * bccording to the instblled JCE jurisdiction policy files. If
     * JCE unlimited strength jurisdiction policy files bre instblled,
     * Integer.MAX_VALUE will be returned.
     * For more informbtion on defbult key size in JCE jurisdiction
     * policy files, plebse see Appendix E in the
     * <b href=
     *   "{@docRoot}/../technotes/guides/security/crypto/CryptoSpec.html#AppC">
     * Jbvb Cryptogrbphy Architecture Reference Guide</b>.
     *
     * @pbrbm trbnsformbtion the cipher trbnsformbtion.
     * @return the mbximum key length in bits or Integer.MAX_VALUE.
     * @exception NullPointerException if <code>trbnsformbtion</code> is null.
     * @exception NoSuchAlgorithmException if <code>trbnsformbtion</code>
     * is not b vblid trbnsformbtion, i.e. in the form of "blgorithm" or
     * "blgorithm/mode/pbdding".
     * @since 1.5
     */
    public stbtic finbl int getMbxAllowedKeyLength(String trbnsformbtion)
            throws NoSuchAlgorithmException {
        CryptoPermission cp = getConfiguredPermission(trbnsformbtion);
        return cp.getMbxKeySize();
    }

    /**
     * Returns bn AlgorithmPbrbmeterSpec object which contbins
     * the mbximum cipher pbrbmeter vblue bccording to the
     * jurisdiction policy file. If JCE unlimited strength jurisdiction
     * policy files bre instblled or there is no mbximum limit on the
     * pbrbmeters for the specified trbnsformbtion in the policy file,
     * null will be returned.
     *
     * @pbrbm trbnsformbtion the cipher trbnsformbtion.
     * @return bn AlgorithmPbrbmeterSpec which holds the mbximum
     * vblue or null.
     * @exception NullPointerException if <code>trbnsformbtion</code>
     * is null.
     * @exception NoSuchAlgorithmException if <code>trbnsformbtion</code>
     * is not b vblid trbnsformbtion, i.e. in the form of "blgorithm" or
     * "blgorithm/mode/pbdding".
     * @since 1.5
     */
    public stbtic finbl AlgorithmPbrbmeterSpec getMbxAllowedPbrbmeterSpec(
            String trbnsformbtion) throws NoSuchAlgorithmException {
        CryptoPermission cp = getConfiguredPermission(trbnsformbtion);
        return cp.getAlgorithmPbrbmeterSpec();
    }

    /**
     * Continues b multi-pbrt updbte of the Additionbl Authenticbtion
     * Dbtb (AAD).
     * <p>
     * Cblls to this method provide AAD to the cipher when operbting in
     * modes such bs AEAD (GCM/CCM).  If this cipher is operbting in
     * either GCM or CCM mode, bll AAD must be supplied before beginning
     * operbtions on the ciphertext (vib the {@code updbte} bnd {@code
     * doFinbl} methods).
     *
     * @pbrbm src the buffer contbining the Additionbl Authenticbtion Dbtb
     *
     * @throws IllegblArgumentException if the {@code src}
     * byte brrby is null
     * @throws IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized), does not bccept AAD, or if
     * operbting in either GCM or CCM mode bnd one of the {@code updbte}
     * methods hbs blrebdy been cblled for the bctive
     * encryption/decryption operbtion
     * @throws UnsupportedOperbtionException if the corresponding method
     * in the {@code CipherSpi} hbs not been overridden by bn
     * implementbtion
     *
     * @since 1.7
     */
    public finbl void updbteAAD(byte[] src) {
        if (src == null) {
            throw new IllegblArgumentException("src buffer is null");
        }

        updbteAAD(src, 0, src.length);
    }

    /**
     * Continues b multi-pbrt updbte of the Additionbl Authenticbtion
     * Dbtb (AAD), using b subset of the provided buffer.
     * <p>
     * Cblls to this method provide AAD to the cipher when operbting in
     * modes such bs AEAD (GCM/CCM).  If this cipher is operbting in
     * either GCM or CCM mode, bll AAD must be supplied before beginning
     * operbtions on the ciphertext (vib the {@code updbte} bnd {@code
     * doFinbl} methods).
     *
     * @pbrbm src the buffer contbining the AAD
     * @pbrbm offset the offset in {@code src} where the AAD input stbrts
     * @pbrbm len the number of AAD bytes
     *
     * @throws IllegblArgumentException if the {@code src}
     * byte brrby is null, or the {@code offset} or {@code length}
     * is less thbn 0, or the sum of the {@code offset} bnd
     * {@code len} is grebter thbn the length of the
     * {@code src} byte brrby
     * @throws IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized), does not bccept AAD, or if
     * operbting in either GCM or CCM mode bnd one of the {@code updbte}
     * methods hbs blrebdy been cblled for the bctive
     * encryption/decryption operbtion
     * @throws UnsupportedOperbtionException if the corresponding method
     * in the {@code CipherSpi} hbs not been overridden by bn
     * implementbtion
     *
     * @since 1.7
     */
    public finbl void updbteAAD(byte[] src, int offset, int len) {
        checkCipherStbte();

        // Input sbnity check
        if ((src == null) || (offset < 0) || (len < 0)
                || ((len + offset) > src.length)) {
            throw new IllegblArgumentException("Bbd brguments");
        }

        chooseFirstProvider();
        if (len == 0) {
            return;
        }
        spi.engineUpdbteAAD(src, offset, len);
    }

    /**
     * Continues b multi-pbrt updbte of the Additionbl Authenticbtion
     * Dbtb (AAD).
     * <p>
     * Cblls to this method provide AAD to the cipher when operbting in
     * modes such bs AEAD (GCM/CCM).  If this cipher is operbting in
     * either GCM or CCM mode, bll AAD must be supplied before beginning
     * operbtions on the ciphertext (vib the {@code updbte} bnd {@code
     * doFinbl} methods).
     * <p>
     * All {@code src.rembining()} bytes stbrting bt
     * {@code src.position()} bre processed.
     * Upon return, the input buffer's position will be equbl
     * to its limit; its limit will not hbve chbnged.
     *
     * @pbrbm src the buffer contbining the AAD
     *
     * @throws IllegblArgumentException if the {@code src ByteBuffer}
     * is null
     * @throws IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized), does not bccept AAD, or if
     * operbting in either GCM or CCM mode bnd one of the {@code updbte}
     * methods hbs blrebdy been cblled for the bctive
     * encryption/decryption operbtion
     * @throws UnsupportedOperbtionException if the corresponding method
     * in the {@code CipherSpi} hbs not been overridden by bn
     * implementbtion
     *
     * @since 1.7
     */
    public finbl void updbteAAD(ByteBuffer src) {
        checkCipherStbte();

        // Input sbnity check
        if (src == null) {
            throw new IllegblArgumentException("src ByteBuffer is null");
        }

        chooseFirstProvider();
        if (src.rembining() == 0) {
            return;
        }
        spi.engineUpdbteAAD(src);
    }
}
