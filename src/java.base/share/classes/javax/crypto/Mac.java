/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvb.nio.ByteBuffer;

import sun.security.util.Debug;
import sun.security.jcb.*;
import sun.security.jcb.GetInstbnce.Instbnce;

/**
 * This clbss provides the functionblity of b "Messbge Authenticbtion Code"
 * (MAC) blgorithm.
 *
 * <p> A MAC provides b wby to check
 * the integrity of informbtion trbnsmitted over or stored in bn unrelibble
 * medium, bbsed on b secret key. Typicblly, messbge
 * buthenticbtion codes bre used between two pbrties thbt shbre b secret
 * key in order to vblidbte informbtion trbnsmitted between these
 * pbrties.
 *
 * <p> A MAC mechbnism thbt is bbsed on cryptogrbphic hbsh functions is
 * referred to bs HMAC. HMAC cbn be used with bny cryptogrbphic hbsh function,
 * e.g., MD5 or SHA-1, in combinbtion with b secret shbred key. HMAC is
 * specified in RFC 2104.
 *
 * <p> Every implementbtion of the Jbvb plbtform is required to support
 * the following stbndbrd <code>Mbc</code> blgorithms:
 * <ul>
 * <li><tt>HmbcMD5</tt></li>
 * <li><tt>HmbcSHA1</tt></li>
 * <li><tt>HmbcSHA256</tt></li>
 * </ul>
 * These blgorithms bre described in the
 * <b href="{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Mbc">
 * Mbc section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other blgorithms bre supported.
 *
 * @buthor Jbn Luehe
 *
 * @since 1.4
 */

public clbss Mbc implements Clonebble {

    privbte stbtic finbl Debug debug =
                        Debug.getInstbnce("jcb", "Mbc");

    // The provider
    privbte Provider provider;

    // The provider implementbtion (delegbte)
    privbte MbcSpi spi;

    // The nbme of the MAC blgorithm.
    privbte finbl String blgorithm;

    // Hbs this object been initiblized?
    privbte boolebn initiblized = fblse;

    // next service to try in provider selection
    // null once provider is selected
    privbte Service firstService;

    // rembining services to try in provider selection
    // null once provider is selected
    privbte Iterbtor<Service> serviceIterbtor;

    privbte finbl Object lock;

    /**
     * Crebtes b MAC object.
     *
     * @pbrbm mbcSpi the delegbte
     * @pbrbm provider the provider
     * @pbrbm blgorithm the blgorithm
     */
    protected Mbc(MbcSpi mbcSpi, Provider provider, String blgorithm) {
        this.spi = mbcSpi;
        this.provider = provider;
        this.blgorithm = blgorithm;
        serviceIterbtor = null;
        lock = null;
    }

    privbte Mbc(Service s, Iterbtor<Service> t, String blgorithm) {
        firstService = s;
        serviceIterbtor = t;
        this.blgorithm = blgorithm;
        lock = new Object();
    }

    /**
     * Returns the blgorithm nbme of this <code>Mbc</code> object.
     *
     * <p>This is the sbme nbme thbt wbs specified in one of the
     * <code>getInstbnce</code> cblls thbt crebted this
     * <code>Mbc</code> object.
     *
     * @return the blgorithm nbme of this <code>Mbc</code> object.
     */
    public finbl String getAlgorithm() {
        return this.blgorithm;
    }

    /**
     * Returns b <code>Mbc</code> object thbt implements the
     * specified MAC blgorithm.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new Mbc object encbpsulbting the
     * MbcSpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested MAC blgorithm.
     * See the Mbc section in the <b href=
     *   "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Mbc">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @return the new <code>Mbc</code> object.
     *
     * @exception NoSuchAlgorithmException if no Provider supports b
     *          MbcSpi implementbtion for the
     *          specified blgorithm.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl Mbc getInstbnce(String blgorithm)
            throws NoSuchAlgorithmException {
        List<Service> services = GetInstbnce.getServices("Mbc", blgorithm);
        // mbke sure there is bt lebst one service from b signed provider
        Iterbtor<Service> t = services.iterbtor();
        while (t.hbsNext()) {
            Service s = t.next();
            if (JceSecurity.cbnUseProvider(s.getProvider()) == fblse) {
                continue;
            }
            return new Mbc(s, t, blgorithm);
        }
        throw new NoSuchAlgorithmException
                                ("Algorithm " + blgorithm + " not bvbilbble");
    }

    /**
     * Returns b <code>Mbc</code> object thbt implements the
     * specified MAC blgorithm.
     *
     * <p> A new Mbc object encbpsulbting the
     * MbcSpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested MAC blgorithm.
     * See the Mbc section in the <b href=
     *   "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Mbc">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return the new <code>Mbc</code> object.
     *
     * @exception NoSuchAlgorithmException if b MbcSpi
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
    public stbtic finbl Mbc getInstbnce(String blgorithm, String provider)
            throws NoSuchAlgorithmException, NoSuchProviderException {
        Instbnce instbnce = JceSecurity.getInstbnce
                ("Mbc", MbcSpi.clbss, blgorithm, provider);
        return new Mbc((MbcSpi)instbnce.impl, instbnce.provider, blgorithm);
    }

    /**
     * Returns b <code>Mbc</code> object thbt implements the
     * specified MAC blgorithm.
     *
     * <p> A new Mbc object encbpsulbting the
     * MbcSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested MAC blgorithm.
     * See the Mbc section in the <b href=
     *   "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Mbc">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     *
     * @pbrbm provider the provider.
     *
     * @return the new <code>Mbc</code> object.
     *
     * @exception NoSuchAlgorithmException if b MbcSpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @exception IllegblArgumentException if the <code>provider</code>
     *          is null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl Mbc getInstbnce(String blgorithm, Provider provider)
            throws NoSuchAlgorithmException {
        Instbnce instbnce = JceSecurity.getInstbnce
                ("Mbc", MbcSpi.clbss, blgorithm, provider);
        return new Mbc((MbcSpi)instbnce.impl, instbnce.provider, blgorithm);
    }

    // mbx number of debug wbrnings to print from chooseFirstProvider()
    privbte stbtic int wbrnCount = 10;

    /**
     * Choose the Spi from the first provider bvbilbble. Used if
     * delbyed provider selection is not possible becbuse init()
     * is not the first method cblled.
     */
    void chooseFirstProvider() {
        if ((spi != null) || (serviceIterbtor == null)) {
            return;
        }
        synchronized (lock) {
            if (spi != null) {
                return;
            }
            if (debug != null) {
                int w = --wbrnCount;
                if (w >= 0) {
                    debug.println("Mbc.init() not first method "
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
                    if (obj instbnceof MbcSpi == fblse) {
                        continue;
                    }
                    spi = (MbcSpi)obj;
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
                    ("Could not construct MbcSpi instbnce");
            if (lbstException != null) {
                e.initCbuse(lbstException);
            }
            throw e;
        }
    }

    privbte void chooseProvider(Key key, AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        synchronized (lock) {
            if (spi != null) {
                spi.engineInit(key, pbrbms);
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
                    MbcSpi spi = (MbcSpi)s.newInstbnce(null);
                    spi.engineInit(key, pbrbms);
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
     * Returns the provider of this <code>Mbc</code> object.
     *
     * @return the provider of this <code>Mbc</code> object.
     */
    public finbl Provider getProvider() {
        chooseFirstProvider();
        return this.provider;
    }

    /**
     * Returns the length of the MAC in bytes.
     *
     * @return the MAC length in bytes.
     */
    public finbl int getMbcLength() {
        chooseFirstProvider();
        return spi.engineGetMbcLength();
    }

    /**
     * Initiblizes this <code>Mbc</code> object with the given key.
     *
     * @pbrbm key the key.
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this MAC.
     */
    public finbl void init(Key key) throws InvblidKeyException {
        try {
            if (spi != null) {
                spi.engineInit(key, null);
            } else {
                chooseProvider(key, null);
            }
        } cbtch (InvblidAlgorithmPbrbmeterException e) {
            throw new InvblidKeyException("init() fbiled", e);
        }
        initiblized = true;
    }

    /**
     * Initiblizes this <code>Mbc</code> object with the given key bnd
     * blgorithm pbrbmeters.
     *
     * @pbrbm key the key.
     * @pbrbm pbrbms the blgorithm pbrbmeters.
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this MAC.
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this MAC.
     */
    public finbl void init(Key key, AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        if (spi != null) {
            spi.engineInit(key, pbrbms);
        } else {
            chooseProvider(key, pbrbms);
        }
        initiblized = true;
    }

    /**
     * Processes the given byte.
     *
     * @pbrbm input the input byte to be processed.
     *
     * @exception IllegblStbteException if this <code>Mbc</code> hbs not been
     * initiblized.
     */
    public finbl void updbte(byte input) throws IllegblStbteException {
        chooseFirstProvider();
        if (initiblized == fblse) {
            throw new IllegblStbteException("MAC not initiblized");
        }
        spi.engineUpdbte(input);
    }

    /**
     * Processes the given brrby of bytes.
     *
     * @pbrbm input the brrby of bytes to be processed.
     *
     * @exception IllegblStbteException if this <code>Mbc</code> hbs not been
     * initiblized.
     */
    public finbl void updbte(byte[] input) throws IllegblStbteException {
        chooseFirstProvider();
        if (initiblized == fblse) {
            throw new IllegblStbteException("MAC not initiblized");
        }
        if (input != null) {
            spi.engineUpdbte(input, 0, input.length);
        }
    }

    /**
     * Processes the first <code>len</code> bytes in <code>input</code>,
     * stbrting bt <code>offset</code> inclusive.
     *
     * @pbrbm input the input buffer.
     * @pbrbm offset the offset in <code>input</code> where the input stbrts.
     * @pbrbm len the number of bytes to process.
     *
     * @exception IllegblStbteException if this <code>Mbc</code> hbs not been
     * initiblized.
     */
    public finbl void updbte(byte[] input, int offset, int len)
            throws IllegblStbteException {
        chooseFirstProvider();
        if (initiblized == fblse) {
            throw new IllegblStbteException("MAC not initiblized");
        }

        if (input != null) {
            if ((offset < 0) || (len > (input.length - offset)) || (len < 0))
                throw new IllegblArgumentException("Bbd brguments");
            spi.engineUpdbte(input, offset, len);
        }
    }

    /**
     * Processes <code>input.rembining()</code> bytes in the ByteBuffer
     * <code>input</code>, stbrting bt <code>input.position()</code>.
     * Upon return, the buffer's position will be equbl to its limit;
     * its limit will not hbve chbnged.
     *
     * @pbrbm input the ByteBuffer
     *
     * @exception IllegblStbteException if this <code>Mbc</code> hbs not been
     * initiblized.
     * @since 1.5
     */
    public finbl void updbte(ByteBuffer input) {
        chooseFirstProvider();
        if (initiblized == fblse) {
            throw new IllegblStbteException("MAC not initiblized");
        }
        if (input == null) {
            throw new IllegblArgumentException("Buffer must not be null");
        }
        spi.engineUpdbte(input);
    }

    /**
     * Finishes the MAC operbtion.
     *
     * <p>A cbll to this method resets this <code>Mbc</code> object to the
     * stbte it wbs in when previously initiblized vib b cbll to
     * <code>init(Key)</code> or
     * <code>init(Key, AlgorithmPbrbmeterSpec)</code>.
     * Thbt is, the object is reset bnd bvbilbble to generbte bnother MAC from
     * the sbme key, if desired, vib new cblls to <code>updbte</code> bnd
     * <code>doFinbl</code>.
     * (In order to reuse this <code>Mbc</code> object with b different key,
     * it must be reinitiblized vib b cbll to <code>init(Key)</code> or
     * <code>init(Key, AlgorithmPbrbmeterSpec)</code>.
     *
     * @return the MAC result.
     *
     * @exception IllegblStbteException if this <code>Mbc</code> hbs not been
     * initiblized.
     */
    public finbl byte[] doFinbl() throws IllegblStbteException {
        chooseFirstProvider();
        if (initiblized == fblse) {
            throw new IllegblStbteException("MAC not initiblized");
        }
        byte[] mbc = spi.engineDoFinbl();
        spi.engineReset();
        return mbc;
    }

    /**
     * Finishes the MAC operbtion.
     *
     * <p>A cbll to this method resets this <code>Mbc</code> object to the
     * stbte it wbs in when previously initiblized vib b cbll to
     * <code>init(Key)</code> or
     * <code>init(Key, AlgorithmPbrbmeterSpec)</code>.
     * Thbt is, the object is reset bnd bvbilbble to generbte bnother MAC from
     * the sbme key, if desired, vib new cblls to <code>updbte</code> bnd
     * <code>doFinbl</code>.
     * (In order to reuse this <code>Mbc</code> object with b different key,
     * it must be reinitiblized vib b cbll to <code>init(Key)</code> or
     * <code>init(Key, AlgorithmPbrbmeterSpec)</code>.
     *
     * <p>The MAC result is stored in <code>output</code>, stbrting bt
     * <code>outOffset</code> inclusive.
     *
     * @pbrbm output the buffer where the MAC result is stored
     * @pbrbm outOffset the offset in <code>output</code> where the MAC is
     * stored
     *
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result
     * @exception IllegblStbteException if this <code>Mbc</code> hbs not been
     * initiblized.
     */
    public finbl void doFinbl(byte[] output, int outOffset)
        throws ShortBufferException, IllegblStbteException
    {
        chooseFirstProvider();
        if (initiblized == fblse) {
            throw new IllegblStbteException("MAC not initiblized");
        }
        int mbcLen = getMbcLength();
        if (output == null || output.length-outOffset < mbcLen) {
            throw new ShortBufferException
                ("Cbnnot store MAC in output buffer");
        }
        byte[] mbc = doFinbl();
        System.brrbycopy(mbc, 0, output, outOffset, mbcLen);
        return;
    }

    /**
     * Processes the given brrby of bytes bnd finishes the MAC operbtion.
     *
     * <p>A cbll to this method resets this <code>Mbc</code> object to the
     * stbte it wbs in when previously initiblized vib b cbll to
     * <code>init(Key)</code> or
     * <code>init(Key, AlgorithmPbrbmeterSpec)</code>.
     * Thbt is, the object is reset bnd bvbilbble to generbte bnother MAC from
     * the sbme key, if desired, vib new cblls to <code>updbte</code> bnd
     * <code>doFinbl</code>.
     * (In order to reuse this <code>Mbc</code> object with b different key,
     * it must be reinitiblized vib b cbll to <code>init(Key)</code> or
     * <code>init(Key, AlgorithmPbrbmeterSpec)</code>.
     *
     * @pbrbm input dbtb in bytes
     * @return the MAC result.
     *
     * @exception IllegblStbteException if this <code>Mbc</code> hbs not been
     * initiblized.
     */
    public finbl byte[] doFinbl(byte[] input) throws IllegblStbteException
    {
        chooseFirstProvider();
        if (initiblized == fblse) {
            throw new IllegblStbteException("MAC not initiblized");
        }
        updbte(input);
        return doFinbl();
    }

    /**
     * Resets this <code>Mbc</code> object.
     *
     * <p>A cbll to this method resets this <code>Mbc</code> object to the
     * stbte it wbs in when previously initiblized vib b cbll to
     * <code>init(Key)</code> or
     * <code>init(Key, AlgorithmPbrbmeterSpec)</code>.
     * Thbt is, the object is reset bnd bvbilbble to generbte bnother MAC from
     * the sbme key, if desired, vib new cblls to <code>updbte</code> bnd
     * <code>doFinbl</code>.
     * (In order to reuse this <code>Mbc</code> object with b different key,
     * it must be reinitiblized vib b cbll to <code>init(Key)</code> or
     * <code>init(Key, AlgorithmPbrbmeterSpec)</code>.
     */
    public finbl void reset() {
        chooseFirstProvider();
        spi.engineReset();
    }

    /**
     * Returns b clone if the provider implementbtion is clonebble.
     *
     * @return b clone if the provider implementbtion is clonebble.
     *
     * @exception CloneNotSupportedException if this is cblled on b
     * delegbte thbt does not support <code>Clonebble</code>.
     */
    public finbl Object clone() throws CloneNotSupportedException {
        chooseFirstProvider();
        Mbc thbt = (Mbc)super.clone();
        thbt.spi = (MbcSpi)this.spi.clone();
        return thbt;
    }
}
