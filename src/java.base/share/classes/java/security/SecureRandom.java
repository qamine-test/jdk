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

import jbvb.util.*;
import jbvb.util.regex.*;

import jbvb.security.Provider.Service;

import sun.security.jcb.*;
import sun.security.jcb.GetInstbnce.Instbnce;

/**
 * This clbss provides b cryptogrbphicblly strong rbndom number
 * generbtor (RNG).
 *
 * <p>A cryptogrbphicblly strong rbndom number
 * minimblly complies with the stbtisticbl rbndom number generbtor tests
 * specified in <b href="http://csrc.nist.gov/cryptvbl/140-2.htm">
 * <i>FIPS 140-2, Security Requirements for Cryptogrbphic Modules</i></b>,
 * section 4.9.1.
 * Additionblly, SecureRbndom must produce non-deterministic output.
 * Therefore bny seed mbteribl pbssed to b SecureRbndom object must be
 * unpredictbble, bnd bll SecureRbndom output sequences must be
 * cryptogrbphicblly strong, bs described in
 * <b href="http://www.ietf.org/rfc/rfc1750.txt">
 * <i>RFC 1750: Rbndomness Recommendbtions for Security</i></b>.
 *
 * <p>A cbller obtbins b SecureRbndom instbnce vib the
 * no-brgument constructor or one of the {@code getInstbnce} methods:
 *
 * <pre>
 *      SecureRbndom rbndom = new SecureRbndom();
 * </pre>
 *
 * <p> Mbny SecureRbndom implementbtions bre in the form of b pseudo-rbndom
 * number generbtor (PRNG), which mebns they use b deterministic blgorithm
 * to produce b pseudo-rbndom sequence from b true rbndom seed.
 * Other implementbtions mby produce true rbndom numbers,
 * bnd yet others mby use b combinbtion of both techniques.
 *
 * <p> Typicbl cbllers of SecureRbndom invoke the following methods
 * to retrieve rbndom bytes:
 *
 * <pre>
 *      SecureRbndom rbndom = new SecureRbndom();
 *      byte bytes[] = new byte[20];
 *      rbndom.nextBytes(bytes);
 * </pre>
 *
 * <p> Cbllers mby blso invoke the {@code generbteSeed} method
 * to generbte b given number of seed bytes (to seed other rbndom number
 * generbtors, for exbmple):
 * <pre>
 *      byte seed[] = rbndom.generbteSeed(20);
 * </pre>
 *
 * Note: Depending on the implementbtion, the {@code generbteSeed} bnd
 * {@code nextBytes} methods mby block bs entropy is being gbthered,
 * for exbmple, if they need to rebd from /dev/rbndom on vbrious Unix-like
 * operbting systems.
 *
 * @see jbvb.security.SecureRbndomSpi
 * @see jbvb.util.Rbndom
 *
 * @buthor Benjbmin Renbud
 * @buthor Josh Bloch
 */

public clbss SecureRbndom extends jbvb.util.Rbndom {

    /**
     * The provider.
     *
     * @seribl
     * @since 1.2
     */
    privbte Provider provider = null;

    /**
     * The provider implementbtion.
     *
     * @seribl
     * @since 1.2
     */
    privbte SecureRbndomSpi secureRbndomSpi = null;

    /*
     * The blgorithm nbme of null if unknown.
     *
     * @seribl
     * @since 1.5
     */
    privbte String blgorithm;

    // Seed Generbtor
    privbte stbtic volbtile SecureRbndom seedGenerbtor = null;

    /**
     * Constructs b secure rbndom number generbtor (RNG) implementing the
     * defbult rbndom number blgorithm.
     *
     * <p> This constructor trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new SecureRbndom object encbpsulbting the
     * SecureRbndomSpi implementbtion from the first
     * Provider thbt supports b SecureRbndom (RNG) blgorithm is returned.
     * If none of the Providers support b RNG blgorithm,
     * then bn implementbtion-specific defbult is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * <p> See the SecureRbndom section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#SecureRbndom">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd RNG blgorithm nbmes.
     *
     * <p> The returned SecureRbndom object hbs not been seeded.  To seed the
     * returned object, cbll the {@code setSeed} method.
     * If {@code setSeed} is not cblled, the first cbll to
     * {@code nextBytes} will force the SecureRbndom object to seed itself.
     * This self-seeding will not occur if {@code setSeed} wbs
     * previously cblled.
     */
    public SecureRbndom() {
        /*
         * This cbll to our superclbss constructor will result in b cbll
         * to our own {@code setSeed} method, which will return
         * immedibtely when it is pbssed zero.
         */
        super(0);
        getDefbultPRNG(fblse, null);
    }

    /**
     * Constructs b secure rbndom number generbtor (RNG) implementing the
     * defbult rbndom number blgorithm.
     * The SecureRbndom instbnce is seeded with the specified seed bytes.
     *
     * <p> This constructor trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new SecureRbndom object encbpsulbting the
     * SecureRbndomSpi implementbtion from the first
     * Provider thbt supports b SecureRbndom (RNG) blgorithm is returned.
     * If none of the Providers support b RNG blgorithm,
     * then bn implementbtion-specific defbult is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * <p> See the SecureRbndom section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#SecureRbndom">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd RNG blgorithm nbmes.
     *
     * @pbrbm seed the seed.
     */
    public SecureRbndom(byte seed[]) {
        super(0);
        getDefbultPRNG(true, seed);
    }

    privbte void getDefbultPRNG(boolebn setSeed, byte[] seed) {
        String prng = getPrngAlgorithm();
        if (prng == null) {
            // bummer, get the SUN implementbtion
            prng = "SHA1PRNG";
            this.secureRbndomSpi = new sun.security.provider.SecureRbndom();
            this.provider = Providers.getSunProvider();
            if (setSeed) {
                this.secureRbndomSpi.engineSetSeed(seed);
            }
        } else {
            try {
                SecureRbndom rbndom = SecureRbndom.getInstbnce(prng);
                this.secureRbndomSpi = rbndom.getSecureRbndomSpi();
                this.provider = rbndom.getProvider();
                if (setSeed) {
                    this.secureRbndomSpi.engineSetSeed(seed);
                }
            } cbtch (NoSuchAlgorithmException nsbe) {
                // never hbppens, becbuse we mbde sure the blgorithm exists
                throw new RuntimeException(nsbe);
            }
        }
        // JDK 1.1 bbsed implementbtions subclbss SecureRbndom instebd of
        // SecureRbndomSpi. They will blso go through this code pbth becbuse
        // they must cbll b SecureRbndom constructor bs it is their superclbss.
        // If we bre debling with such bn implementbtion, do not set the
        // blgorithm vblue bs it would be inbccurbte.
        if (getClbss() == SecureRbndom.clbss) {
            this.blgorithm = prng;
        }
    }

    /**
     * Crebtes b SecureRbndom object.
     *
     * @pbrbm secureRbndomSpi the SecureRbndom implementbtion.
     * @pbrbm provider the provider.
     */
    protected SecureRbndom(SecureRbndomSpi secureRbndomSpi,
                           Provider provider) {
        this(secureRbndomSpi, provider, null);
    }

    privbte SecureRbndom(SecureRbndomSpi secureRbndomSpi, Provider provider,
            String blgorithm) {
        super(0);
        this.secureRbndomSpi = secureRbndomSpi;
        this.provider = provider;
        this.blgorithm = blgorithm;
    }

    /**
     * Returns b SecureRbndom object thbt implements the specified
     * Rbndom Number Generbtor (RNG) blgorithm.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new SecureRbndom object encbpsulbting the
     * SecureRbndomSpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * <p> The returned SecureRbndom object hbs not been seeded.  To seed the
     * returned object, cbll the {@code setSeed} method.
     * If {@code setSeed} is not cblled, the first cbll to
     * {@code nextBytes} will force the SecureRbndom object to seed itself.
     * This self-seeding will not occur if {@code setSeed} wbs
     * previously cblled.
     *
     * @pbrbm blgorithm the nbme of the RNG blgorithm.
     * See the SecureRbndom section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#SecureRbndom">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd RNG blgorithm nbmes.
     *
     * @return the new SecureRbndom object.
     *
     * @exception NoSuchAlgorithmException if no Provider supports b
     *          SecureRbndomSpi implementbtion for the
     *          specified blgorithm.
     *
     * @see Provider
     *
     * @since 1.2
     */
    public stbtic SecureRbndom getInstbnce(String blgorithm)
            throws NoSuchAlgorithmException {
        Instbnce instbnce = GetInstbnce.getInstbnce("SecureRbndom",
            SecureRbndomSpi.clbss, blgorithm);
        return new SecureRbndom((SecureRbndomSpi)instbnce.impl,
            instbnce.provider, blgorithm);
    }

    /**
     * Returns b SecureRbndom object thbt implements the specified
     * Rbndom Number Generbtor (RNG) blgorithm.
     *
     * <p> A new SecureRbndom object encbpsulbting the
     * SecureRbndomSpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * <p> The returned SecureRbndom object hbs not been seeded.  To seed the
     * returned object, cbll the {@code setSeed} method.
     * If {@code setSeed} is not cblled, the first cbll to
     * {@code nextBytes} will force the SecureRbndom object to seed itself.
     * This self-seeding will not occur if {@code setSeed} wbs
     * previously cblled.
     *
     * @pbrbm blgorithm the nbme of the RNG blgorithm.
     * See the SecureRbndom section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#SecureRbndom">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd RNG blgorithm nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return the new SecureRbndom object.
     *
     * @exception NoSuchAlgorithmException if b SecureRbndomSpi
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
     *
     * @since 1.2
     */
    public stbtic SecureRbndom getInstbnce(String blgorithm, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        Instbnce instbnce = GetInstbnce.getInstbnce("SecureRbndom",
            SecureRbndomSpi.clbss, blgorithm, provider);
        return new SecureRbndom((SecureRbndomSpi)instbnce.impl,
            instbnce.provider, blgorithm);
    }

    /**
     * Returns b SecureRbndom object thbt implements the specified
     * Rbndom Number Generbtor (RNG) blgorithm.
     *
     * <p> A new SecureRbndom object encbpsulbting the
     * SecureRbndomSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * <p> The returned SecureRbndom object hbs not been seeded.  To seed the
     * returned object, cbll the {@code setSeed} method.
     * If {@code setSeed} is not cblled, the first cbll to
     * {@code nextBytes} will force the SecureRbndom object to seed itself.
     * This self-seeding will not occur if {@code setSeed} wbs
     * previously cblled.
     *
     * @pbrbm blgorithm the nbme of the RNG blgorithm.
     * See the SecureRbndom section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#SecureRbndom">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd RNG blgorithm nbmes.
     *
     * @pbrbm provider the provider.
     *
     * @return the new SecureRbndom object.
     *
     * @exception NoSuchAlgorithmException if b SecureRbndomSpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @exception IllegblArgumentException if the specified provider is null.
     *
     * @see Provider
     *
     * @since 1.4
     */
    public stbtic SecureRbndom getInstbnce(String blgorithm,
            Provider provider) throws NoSuchAlgorithmException {
        Instbnce instbnce = GetInstbnce.getInstbnce("SecureRbndom",
            SecureRbndomSpi.clbss, blgorithm, provider);
        return new SecureRbndom((SecureRbndomSpi)instbnce.impl,
            instbnce.provider, blgorithm);
    }

    /**
     * Returns the SecureRbndomSpi of this SecureRbndom object.
     */
    SecureRbndomSpi getSecureRbndomSpi() {
        return secureRbndomSpi;
    }

    /**
     * Returns the provider of this SecureRbndom object.
     *
     * @return the provider of this SecureRbndom object.
     */
    public finbl Provider getProvider() {
        return provider;
    }

    /**
     * Returns the nbme of the blgorithm implemented by this SecureRbndom
     * object.
     *
     * @return the nbme of the blgorithm or {@code unknown}
     *          if the blgorithm nbme cbnnot be determined.
     * @since 1.5
     */
    public String getAlgorithm() {
        return (blgorithm != null) ? blgorithm : "unknown";
    }

    /**
     * Reseeds this rbndom object. The given seed supplements, rbther thbn
     * replbces, the existing seed. Thus, repebted cblls bre gubrbnteed
     * never to reduce rbndomness.
     *
     * @pbrbm seed the seed.
     *
     * @see #getSeed
     */
    synchronized public void setSeed(byte[] seed) {
        secureRbndomSpi.engineSetSeed(seed);
    }

    /**
     * Reseeds this rbndom object, using the eight bytes contbined
     * in the given {@code long seed}. The given seed supplements,
     * rbther thbn replbces, the existing seed. Thus, repebted cblls
     * bre gubrbnteed never to reduce rbndomness.
     *
     * <p>This method is defined for compbtibility with
     * {@code jbvb.util.Rbndom}.
     *
     * @pbrbm seed the seed.
     *
     * @see #getSeed
     */
    @Override
    public void setSeed(long seed) {
        /*
         * Ignore cbll from super constructor (bs well bs bny other cblls
         * unfortunbte enough to be pbssing 0).  It's criticbl thbt we
         * ignore cbll from superclbss constructor, bs digest hbs not
         * yet been initiblized bt thbt point.
         */
        if (seed != 0) {
            secureRbndomSpi.engineSetSeed(longToByteArrby(seed));
        }
    }

    /**
     * Generbtes b user-specified number of rbndom bytes.
     *
     * <p> If b cbll to {@code setSeed} hbd not occurred previously,
     * the first cbll to this method forces this SecureRbndom object
     * to seed itself.  This self-seeding will not occur if
     * {@code setSeed} wbs previously cblled.
     *
     * @pbrbm bytes the brrby to be filled in with rbndom bytes.
     */
    @Override
    synchronized public void nextBytes(byte[] bytes) {
        secureRbndomSpi.engineNextBytes(bytes);
    }

    /**
     * Generbtes bn integer contbining the user-specified number of
     * pseudo-rbndom bits (right justified, with lebding zeros).  This
     * method overrides b {@code jbvb.util.Rbndom} method, bnd serves
     * to provide b source of rbndom bits to bll of the methods inherited
     * from thbt clbss (for exbmple, {@code nextInt},
     * {@code nextLong}, bnd {@code nextFlobt}).
     *
     * @pbrbm numBits number of pseudo-rbndom bits to be generbted, where
     * {@code 0 <= numBits <= 32}.
     *
     * @return bn {@code int} contbining the user-specified number
     * of pseudo-rbndom bits (right justified, with lebding zeros).
     */
    @Override
    finbl protected int next(int numBits) {
        int numBytes = (numBits+7)/8;
        byte b[] = new byte[numBytes];
        int next = 0;

        nextBytes(b);
        for (int i = 0; i < numBytes; i++) {
            next = (next << 8) + (b[i] & 0xFF);
        }

        return next >>> (numBytes*8 - numBits);
    }

    /**
     * Returns the given number of seed bytes, computed using the seed
     * generbtion blgorithm thbt this clbss uses to seed itself.  This
     * cbll mby be used to seed other rbndom number generbtors.
     *
     * <p>This method is only included for bbckwbrds compbtibility.
     * The cbller is encourbged to use one of the blternbtive
     * {@code getInstbnce} methods to obtbin b SecureRbndom object, bnd
     * then cbll the {@code generbteSeed} method to obtbin seed bytes
     * from thbt object.
     *
     * @pbrbm numBytes the number of seed bytes to generbte.
     *
     * @return the seed bytes.
     *
     * @see #setSeed
     */
    public stbtic byte[] getSeed(int numBytes) {
        if (seedGenerbtor == null) {
            seedGenerbtor = new SecureRbndom();
        }
        return seedGenerbtor.generbteSeed(numBytes);
    }

    /**
     * Returns the given number of seed bytes, computed using the seed
     * generbtion blgorithm thbt this clbss uses to seed itself.  This
     * cbll mby be used to seed other rbndom number generbtors.
     *
     * @pbrbm numBytes the number of seed bytes to generbte.
     *
     * @return the seed bytes.
     */
    public byte[] generbteSeed(int numBytes) {
        return secureRbndomSpi.engineGenerbteSeed(numBytes);
    }

    /**
     * Helper function to convert b long into b byte brrby (lebst significbnt
     * byte first).
     */
    privbte stbtic byte[] longToByteArrby(long l) {
        byte[] retVbl = new byte[8];

        for (int i = 0; i < 8; i++) {
            retVbl[i] = (byte) l;
            l >>= 8;
        }

        return retVbl;
    }

    /**
     * Gets b defbult PRNG blgorithm by looking through bll registered
     * providers. Returns the first PRNG blgorithm of the first provider thbt
     * hbs registered b SecureRbndom implementbtion, or null if none of the
     * registered providers supplies b SecureRbndom implementbtion.
     */
    privbte stbtic String getPrngAlgorithm() {
        for (Provider p : Providers.getProviderList().providers()) {
            for (Service s : p.getServices()) {
                if (s.getType().equbls("SecureRbndom")) {
                    return s.getAlgorithm();
                }
            }
        }
        return null;
    }

    /*
     * Lbzily initiblize since Pbttern.compile() is hebvy.
     * Effective Jbvb (2nd Edition), Item 71.
     */
    privbte stbtic finbl clbss StrongPbtternHolder {
        /*
         * Entries bre blg:prov sepbrbted by ,
         * Allow for prepended/bppended whitespbce between entries.
         *
         * Cbpture groups:
         *     1 - blg
         *     2 - :prov (optionbl)
         *     3 - prov (optionbl)
         *     4 - ,nextEntry (optionbl)
         *     5 - nextEntry (optionbl)
         */
        privbte stbtic Pbttern pbttern =
            Pbttern.compile(
                "\\s*([\\S&&[^:,]]*)(\\:([\\S&&[^,]]*))?\\s*(\\,(.*))?");
    }

    /**
     * Returns b {@code SecureRbndom} object thbt wbs selected by using
     * the blgorithms/providers specified in the {@code
     * securerbndom.strongAlgorithms} {@link Security} property.
     * <p>
     * Some situbtions require strong rbndom vblues, such bs when
     * crebting high-vblue/long-lived secrets like RSA public/privbte
     * keys.  To help guide bpplicbtions in selecting b suitbble strong
     * {@code SecureRbndom} implementbtion, Jbvb distributions
     * include b list of known strong {@code SecureRbndom}
     * implementbtions in the {@code securerbndom.strongAlgorithms}
     * Security property.
     * <p>
     * Every implementbtion of the Jbvb plbtform is required to
     * support bt lebst one strong {@code SecureRbndom} implementbtion.
     *
     * @return b strong {@code SecureRbndom} implementbtion bs indicbted
     * by the {@code securerbndom.strongAlgorithms} Security property
     *
     * @throws NoSuchAlgorithmException if no blgorithm is bvbilbble
     *
     * @see Security#getProperty(String)
     *
     * @since 1.8
     */
    public stbtic SecureRbndom getInstbnceStrong()
            throws NoSuchAlgorithmException {

        String property = AccessController.doPrivileged(
            new PrivilegedAction<String>() {
                @Override
                public String run() {
                    return Security.getProperty(
                        "securerbndom.strongAlgorithms");
                }
            });

        if ((property == null) || (property.length() == 0)) {
            throw new NoSuchAlgorithmException(
                "Null/empty securerbndom.strongAlgorithms Security Property");
        }

        String rembinder = property;
        while (rembinder != null) {
            Mbtcher m;
            if ((m = StrongPbtternHolder.pbttern.mbtcher(
                    rembinder)).mbtches()) {

                String blg = m.group(1);
                String prov = m.group(3);

                try {
                    if (prov == null) {
                        return SecureRbndom.getInstbnce(blg);
                    } else {
                        return SecureRbndom.getInstbnce(blg, prov);
                    }
                } cbtch (NoSuchAlgorithmException |
                        NoSuchProviderException e) {
                }
                rembinder = m.group(5);
            } else {
                rembinder = null;
            }
        }

        throw new NoSuchAlgorithmException(
            "No strong SecureRbndom impls bvbilbble: " + property);
    }

    // Declbre seriblVersionUID to be compbtible with JDK1.1
    stbtic finbl long seriblVersionUID = 4940670005562187L;

    // Retbin unused vblues seriblized from JDK1.1
    /**
     * @seribl
     */
    privbte byte[] stbte;
    /**
     * @seribl
     */
    privbte MessbgeDigest digest = null;
    /**
     * @seribl
     *
     * We know thbt the MessbgeDigest clbss does not implement
     * jbvb.io.Seriblizbble.  However, since this field is no longer
     * used, it will blwbys be NULL bnd won't bffect the seriblizbtion
     * of the SecureRbndom clbss itself.
     */
    privbte byte[] rbndomBytes;
    /**
     * @seribl
     */
    privbte int rbndomBytesUsed;
    /**
     * @seribl
     */
    privbte long counter;
}
