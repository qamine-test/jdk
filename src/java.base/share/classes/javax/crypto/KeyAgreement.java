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

import jbvb.security.*;
import jbvb.security.Provider.Service;
import jbvb.security.spec.*;

import sun.security.util.Debug;
import sun.security.jcb.*;
import sun.security.jcb.GetInstbnce.Instbnce;

/**
 * This clbss provides the functionblity of b key bgreement (or key
 * exchbnge) protocol.
 * <p>
 * The keys involved in estbblishing b shbred secret bre crebted by one of the
 * key generbtors (<code>KeyPbirGenerbtor</code> or
 * <code>KeyGenerbtor</code>), b <code>KeyFbctory</code>, or bs b result from
 * bn intermedibte phbse of the key bgreement protocol.
 *
 * <p> For ebch of the correspondents in the key exchbnge, <code>doPhbse</code>
 * needs to be cblled. For exbmple, if this key exchbnge is with one other
 * pbrty, <code>doPhbse</code> needs to be cblled once, with the
 * <code>lbstPhbse</code> flbg set to <code>true</code>.
 * If this key exchbnge is
 * with two other pbrties, <code>doPhbse</code> needs to be cblled twice,
 * the first time setting the <code>lbstPhbse</code> flbg to
 * <code>fblse</code>, bnd the second time setting it to <code>true</code>.
 * There mby be bny number of pbrties involved in b key exchbnge.
 *
 * <p> Every implementbtion of the Jbvb plbtform is required to support the
 * following stbndbrd <code>KeyAgreement</code> blgorithm:
 * <ul>
 * <li><tt>DiffieHellmbn</tt></li>
 * </ul>
 * This blgorithm is described in the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyAgreement">
 * KeyAgreement section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other blgorithms bre supported.
 *
 * @buthor Jbn Luehe
 *
 * @see KeyGenerbtor
 * @see SecretKey
 * @since 1.4
 */

public clbss KeyAgreement {

    privbte stbtic finbl Debug debug =
                        Debug.getInstbnce("jcb", "KeyAgreement");

    // The provider
    privbte Provider provider;

    // The provider implementbtion (delegbte)
    privbte KeyAgreementSpi spi;

    // The nbme of the key bgreement blgorithm.
    privbte finbl String blgorithm;

    // next service to try in provider selection
    // null once provider is selected
    privbte Service firstService;

    // rembining services to try in provider selection
    // null once provider is selected
    privbte Iterbtor<Service> serviceIterbtor;

    privbte finbl Object lock;

    /**
     * Crebtes b KeyAgreement object.
     *
     * @pbrbm keyAgreeSpi the delegbte
     * @pbrbm provider the provider
     * @pbrbm blgorithm the blgorithm
     */
    protected KeyAgreement(KeyAgreementSpi keyAgreeSpi, Provider provider,
                           String blgorithm) {
        this.spi = keyAgreeSpi;
        this.provider = provider;
        this.blgorithm = blgorithm;
        lock = null;
    }

    privbte KeyAgreement(Service s, Iterbtor<Service> t, String blgorithm) {
        firstService = s;
        serviceIterbtor = t;
        this.blgorithm = blgorithm;
        lock = new Object();
    }

    /**
     * Returns the blgorithm nbme of this <code>KeyAgreement</code> object.
     *
     * <p>This is the sbme nbme thbt wbs specified in one of the
     * <code>getInstbnce</code> cblls thbt crebted this
     * <code>KeyAgreement</code> object.
     *
     * @return the blgorithm nbme of this <code>KeyAgreement</code> object.
     */
    public finbl String getAlgorithm() {
        return this.blgorithm;
    }

    /**
     * Returns b <code>KeyAgreement</code> object thbt implements the
     * specified key bgreement blgorithm.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new KeyAgreement object encbpsulbting the
     * KeyAgreementSpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested key bgreement
     * blgorithm.
     * See the KeyAgreement section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyAgreement">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the new <code>KeyAgreement</code> object.
     *
     * @exception NullPointerException if the specified blgorithm
     *          is null.
     *
     * @exception NoSuchAlgorithmException if no Provider supports b
     *          KeyAgreementSpi implementbtion for the
     *          specified blgorithm.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl KeyAgreement getInstbnce(String blgorithm)
            throws NoSuchAlgorithmException {
        List<Service> services =
                GetInstbnce.getServices("KeyAgreement", blgorithm);
        // mbke sure there is bt lebst one service from b signed provider
        Iterbtor<Service> t = services.iterbtor();
        while (t.hbsNext()) {
            Service s = t.next();
            if (JceSecurity.cbnUseProvider(s.getProvider()) == fblse) {
                continue;
            }
            return new KeyAgreement(s, t, blgorithm);
        }
        throw new NoSuchAlgorithmException
                                ("Algorithm " + blgorithm + " not bvbilbble");
    }

    /**
     * Returns b <code>KeyAgreement</code> object thbt implements the
     * specified key bgreement blgorithm.
     *
     * <p> A new KeyAgreement object encbpsulbting the
     * KeyAgreementSpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested key bgreement
     * blgorithm.
     * See the KeyAgreement section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyAgreement">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return the new <code>KeyAgreement</code> object.
     *
     * @exception NullPointerException if the specified blgorithm
     *          is null.
     *
     * @exception NoSuchAlgorithmException if b KeyAgreementSpi
     *          implementbtion for the specified blgorithm is not
     *          bvbilbble from the specified provider.
     *
     * @exception NoSuchProviderException if the specified provider is not
     *          registered in the security provider list.
     *
     * @exception IllegblArgumentException if the <code>provider</code>
     *          is null or empty.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl KeyAgreement getInstbnce(String blgorithm,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        Instbnce instbnce = JceSecurity.getInstbnce
                ("KeyAgreement", KeyAgreementSpi.clbss, blgorithm, provider);
        return new KeyAgreement((KeyAgreementSpi)instbnce.impl,
                instbnce.provider, blgorithm);
    }

    /**
     * Returns b <code>KeyAgreement</code> object thbt implements the
     * specified key bgreement blgorithm.
     *
     * <p> A new KeyAgreement object encbpsulbting the
     * KeyAgreementSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested key bgreement
     * blgorithm.
     * See the KeyAgreement section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyAgreement">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the provider.
     *
     * @return the new <code>KeyAgreement</code> object.
     *
     * @exception NullPointerException if the specified blgorithm
     *          is null.
     *
     * @exception NoSuchAlgorithmException if b KeyAgreementSpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @exception IllegblArgumentException if the <code>provider</code>
     *          is null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl KeyAgreement getInstbnce(String blgorithm,
            Provider provider) throws NoSuchAlgorithmException {
        Instbnce instbnce = JceSecurity.getInstbnce
                ("KeyAgreement", KeyAgreementSpi.clbss, blgorithm, provider);
        return new KeyAgreement((KeyAgreementSpi)instbnce.impl,
                instbnce.provider, blgorithm);
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
                    debug.println("KeyAgreement.init() not first method "
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
                if (JceSecurity.cbnUseProvider(s.getProvider()) == fblse) {
                    continue;
                }
                try {
                    Object obj = s.newInstbnce(null);
                    if (obj instbnceof KeyAgreementSpi == fblse) {
                        continue;
                    }
                    spi = (KeyAgreementSpi)obj;
                    provider = s.getProvider();
                    // not needed bny more
                    firstService = null;
                    serviceIterbtor = null;
                    return;
                } cbtch (Exception e) {
                    lbstException = e;
                }
            }
            ProviderException e = new ProviderException
                    ("Could not construct KeyAgreementSpi instbnce");
            if (lbstException != null) {
                e.initCbuse(lbstException);
            }
            throw e;
        }
    }

    privbte finbl stbtic int I_NO_PARAMS = 1;
    privbte finbl stbtic int I_PARAMS    = 2;

    privbte void implInit(KeyAgreementSpi spi, int type, Key key,
            AlgorithmPbrbmeterSpec pbrbms, SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        if (type == I_NO_PARAMS) {
            spi.engineInit(key, rbndom);
        } else { // I_PARAMS
            spi.engineInit(key, pbrbms, rbndom);
        }
    }

    privbte void chooseProvider(int initType, Key key,
            AlgorithmPbrbmeterSpec pbrbms, SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        synchronized (lock) {
            if (spi != null) {
                implInit(spi, initType, key, pbrbms, rbndom);
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
                if (JceSecurity.cbnUseProvider(s.getProvider()) == fblse) {
                    continue;
                }
                try {
                    KeyAgreementSpi spi = (KeyAgreementSpi)s.newInstbnce(null);
                    implInit(spi, initType, key, pbrbms, rbndom);
                    provider = s.getProvider();
                    this.spi = spi;
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
     * Returns the provider of this <code>KeyAgreement</code> object.
     *
     * @return the provider of this <code>KeyAgreement</code> object
     */
    public finbl Provider getProvider() {
        chooseFirstProvider();
        return this.provider;
    }

    /**
     * Initiblizes this key bgreement with the given key, which is required to
     * contbin bll the blgorithm pbrbmeters required for this key bgreement.
     *
     * <p> If this key bgreement requires bny rbndom bytes, it will get
     * them using the
     * {@link jbvb.security.SecureRbndom}
     * implementbtion of the highest-priority
     * instblled provider bs the source of rbndomness.
     * (If none of the instblled providers supply bn implementbtion of
     * SecureRbndom, b system-provided source of rbndomness will be used.)
     *
     * @pbrbm key the pbrty's privbte informbtion. For exbmple, in the cbse
     * of the Diffie-Hellmbn key bgreement, this would be the pbrty's own
     * Diffie-Hellmbn privbte key.
     *
     * @exception InvblidKeyException if the given key is
     * inbppropribte for this key bgreement, e.g., is of the wrong type or
     * hbs bn incompbtible blgorithm type.
     */
    public finbl void init(Key key) throws InvblidKeyException {
        init(key, JceSecurity.RANDOM);
    }

    /**
     * Initiblizes this key bgreement with the given key bnd source of
     * rbndomness. The given key is required to contbin bll the blgorithm
     * pbrbmeters required for this key bgreement.
     *
     * <p> If the key bgreement blgorithm requires rbndom bytes, it gets them
     * from the given source of rbndomness, <code>rbndom</code>.
     * However, if the underlying
     * blgorithm implementbtion does not require bny rbndom bytes,
     * <code>rbndom</code> is ignored.
     *
     * @pbrbm key the pbrty's privbte informbtion. For exbmple, in the cbse
     * of the Diffie-Hellmbn key bgreement, this would be the pbrty's own
     * Diffie-Hellmbn privbte key.
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the given key is
     * inbppropribte for this key bgreement, e.g., is of the wrong type or
     * hbs bn incompbtible blgorithm type.
     */
    public finbl void init(Key key, SecureRbndom rbndom)
            throws InvblidKeyException {
        if (spi != null) {
            spi.engineInit(key, rbndom);
        } else {
            try {
                chooseProvider(I_NO_PARAMS, key, null, rbndom);
            } cbtch (InvblidAlgorithmPbrbmeterException e) {
                // should never occur
                throw new InvblidKeyException(e);
            }
        }
    }

    /**
     * Initiblizes this key bgreement with the given key bnd set of
     * blgorithm pbrbmeters.
     *
     * <p> If this key bgreement requires bny rbndom bytes, it will get
     * them using the
     * {@link jbvb.security.SecureRbndom}
     * implementbtion of the highest-priority
     * instblled provider bs the source of rbndomness.
     * (If none of the instblled providers supply bn implementbtion of
     * SecureRbndom, b system-provided source of rbndomness will be used.)
     *
     * @pbrbm key the pbrty's privbte informbtion. For exbmple, in the cbse
     * of the Diffie-Hellmbn key bgreement, this would be the pbrty's own
     * Diffie-Hellmbn privbte key.
     * @pbrbm pbrbms the key bgreement pbrbmeters
     *
     * @exception InvblidKeyException if the given key is
     * inbppropribte for this key bgreement, e.g., is of the wrong type or
     * hbs bn incompbtible blgorithm type.
     * @exception InvblidAlgorithmPbrbmeterException if the given pbrbmeters
     * bre inbppropribte for this key bgreement.
     */
    public finbl void init(Key key, AlgorithmPbrbmeterSpec pbrbms)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException
    {
        init(key, pbrbms, JceSecurity.RANDOM);
    }

    /**
     * Initiblizes this key bgreement with the given key, set of
     * blgorithm pbrbmeters, bnd source of rbndomness.
     *
     * @pbrbm key the pbrty's privbte informbtion. For exbmple, in the cbse
     * of the Diffie-Hellmbn key bgreement, this would be the pbrty's own
     * Diffie-Hellmbn privbte key.
     * @pbrbm pbrbms the key bgreement pbrbmeters
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the given key is
     * inbppropribte for this key bgreement, e.g., is of the wrong type or
     * hbs bn incompbtible blgorithm type.
     * @exception InvblidAlgorithmPbrbmeterException if the given pbrbmeters
     * bre inbppropribte for this key bgreement.
     */
    public finbl void init(Key key, AlgorithmPbrbmeterSpec pbrbms,
                           SecureRbndom rbndom)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException
    {
        if (spi != null) {
            spi.engineInit(key, pbrbms, rbndom);
        } else {
            chooseProvider(I_PARAMS, key, pbrbms, rbndom);
        }
    }

    /**
     * Executes the next phbse of this key bgreement with the given
     * key thbt wbs received from one of the other pbrties involved in this key
     * bgreement.
     *
     * @pbrbm key the key for this phbse. For exbmple, in the cbse of
     * Diffie-Hellmbn between 2 pbrties, this would be the other pbrty's
     * Diffie-Hellmbn public key.
     * @pbrbm lbstPhbse flbg which indicbtes whether or not this is the lbst
     * phbse of this key bgreement.
     *
     * @return the (intermedibte) key resulting from this phbse, or null
     * if this phbse does not yield b key
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * this phbse.
     * @exception IllegblStbteException if this key bgreement hbs not been
     * initiblized.
     */
    public finbl Key doPhbse(Key key, boolebn lbstPhbse)
        throws InvblidKeyException, IllegblStbteException
    {
        chooseFirstProvider();
        return spi.engineDoPhbse(key, lbstPhbse);
    }

    /**
     * Generbtes the shbred secret bnd returns it in b new buffer.
     *
     * <p>This method resets this <code>KeyAgreement</code> object, so thbt it
     * cbn be reused for further key bgreements. Unless this key bgreement is
     * reinitiblized with one of the <code>init</code> methods, the sbme
     * privbte informbtion bnd blgorithm pbrbmeters will be used for
     * subsequent key bgreements.
     *
     * @return the new buffer with the shbred secret
     *
     * @exception IllegblStbteException if this key bgreement hbs not been
     * completed yet
     */
    public finbl byte[] generbteSecret() throws IllegblStbteException {
        chooseFirstProvider();
        return spi.engineGenerbteSecret();
    }

    /**
     * Generbtes the shbred secret, bnd plbces it into the buffer
     * <code>shbredSecret</code>, beginning bt <code>offset</code> inclusive.
     *
     * <p>If the <code>shbredSecret</code> buffer is too smbll to hold the
     * result, b <code>ShortBufferException</code> is thrown.
     * In this cbse, this cbll should be repebted with b lbrger output buffer.
     *
     * <p>This method resets this <code>KeyAgreement</code> object, so thbt it
     * cbn be reused for further key bgreements. Unless this key bgreement is
     * reinitiblized with one of the <code>init</code> methods, the sbme
     * privbte informbtion bnd blgorithm pbrbmeters will be used for
     * subsequent key bgreements.
     *
     * @pbrbm shbredSecret the buffer for the shbred secret
     * @pbrbm offset the offset in <code>shbredSecret</code> where the
     * shbred secret will be stored
     *
     * @return the number of bytes plbced into <code>shbredSecret</code>
     *
     * @exception IllegblStbteException if this key bgreement hbs not been
     * completed yet
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the secret
     */
    public finbl int generbteSecret(byte[] shbredSecret, int offset)
        throws IllegblStbteException, ShortBufferException
    {
        chooseFirstProvider();
        return spi.engineGenerbteSecret(shbredSecret, offset);
    }

    /**
     * Crebtes the shbred secret bnd returns it bs b <code>SecretKey</code>
     * object of the specified blgorithm.
     *
     * <p>This method resets this <code>KeyAgreement</code> object, so thbt it
     * cbn be reused for further key bgreements. Unless this key bgreement is
     * reinitiblized with one of the <code>init</code> methods, the sbme
     * privbte informbtion bnd blgorithm pbrbmeters will be used for
     * subsequent key bgreements.
     *
     * @pbrbm blgorithm the requested secret-key blgorithm
     *
     * @return the shbred secret key
     *
     * @exception IllegblStbteException if this key bgreement hbs not been
     * completed yet
     * @exception NoSuchAlgorithmException if the specified secret-key
     * blgorithm is not bvbilbble
     * @exception InvblidKeyException if the shbred secret-key mbteribl cbnnot
     * be used to generbte b secret key of the specified blgorithm (e.g.,
     * the key mbteribl is too short)
     */
    public finbl SecretKey generbteSecret(String blgorithm)
        throws IllegblStbteException, NoSuchAlgorithmException,
            InvblidKeyException
    {
        chooseFirstProvider();
        return spi.engineGenerbteSecret(blgorithm);
    }
}
