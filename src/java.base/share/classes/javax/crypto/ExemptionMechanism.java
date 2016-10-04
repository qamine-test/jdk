/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.AlgorithmPbrbmeters;
import jbvb.security.Provider;
import jbvb.security.Key;
import jbvb.security.Security;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;
import jbvb.security.InvblidKeyException;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import sun.security.jcb.GetInstbnce.Instbnce;

/**
 * This clbss provides the functionblity of bn exemption mechbnism, exbmples
 * of which bre <i>key recovery</i>, <i>key webkening</i>, bnd
 * <i>key escrow</i>.
 *
 * <p>Applicbtions or bpplets thbt use bn exemption mechbnism mby be grbnted
 * stronger encryption cbpbbilities thbn those which don't.
 *
 * @since 1.4
 */

public clbss ExemptionMechbnism {

    // The provider
    privbte Provider provider;

    // The provider implementbtion (delegbte)
    privbte ExemptionMechbnismSpi exmechSpi;

    // The nbme of the exemption mechbnism.
    privbte String mechbnism;

    // Flbg which indicbtes whether this ExemptionMechbnism
    // result is generbted successfully.
    privbte boolebn done = fblse;

    // Stbte informbtion
    privbte boolebn initiblized = fblse;

    // Store bwby the key bt init() time for lbter compbrison.
    privbte Key keyStored = null;

    /**
     * Crebtes b ExemptionMechbnism object.
     *
     * @pbrbm exmechSpi the delegbte
     * @pbrbm provider the provider
     * @pbrbm mechbnism the exemption mechbnism
     */
    protected ExemptionMechbnism(ExemptionMechbnismSpi exmechSpi,
                                 Provider provider,
                                 String mechbnism) {
        this.exmechSpi = exmechSpi;
        this.provider = provider;
        this.mechbnism = mechbnism;
    }

    /**
     * Returns the exemption mechbnism nbme of this
     * <code>ExemptionMechbnism</code> object.
     *
     * <p>This is the sbme nbme thbt wbs specified in one of the
     * <code>getInstbnce</code> cblls thbt crebted this
     * <code>ExemptionMechbnism</code> object.
     *
     * @return the exemption mechbnism nbme of this
     * <code>ExemptionMechbnism</code> object.
     */
    public finbl String getNbme() {
        return this.mechbnism;
    }

    /**
     * Returns bn <code>ExemptionMechbnism</code> object thbt implements the
     * specified exemption mechbnism blgorithm.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new ExemptionMechbnism object encbpsulbting the
     * ExemptionMechbnismSpi implementbtion from the first
     * Provider thbt supports the specified blgorithm is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested exemption
     * mechbnism.
     * See the ExemptionMechbnism section in the
     * <b href=
     *   "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Exemption">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd exemption mechbnism nbmes.
     *
     * @return the new <code>ExemptionMechbnism</code> object.
     *
     * @exception NullPointerException if <code>blgorithm</code>
     *          is null.
     *
     * @exception NoSuchAlgorithmException if no Provider supports bn
     *          ExemptionMechbnismSpi implementbtion for the
     *          specified blgorithm.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl ExemptionMechbnism getInstbnce(String blgorithm)
            throws NoSuchAlgorithmException {
        Instbnce instbnce = JceSecurity.getInstbnce("ExemptionMechbnism",
                ExemptionMechbnismSpi.clbss, blgorithm);
        return new ExemptionMechbnism((ExemptionMechbnismSpi)instbnce.impl,
                instbnce.provider, blgorithm);
    }


    /**
     * Returns bn <code>ExemptionMechbnism</code> object thbt implements the
     * specified exemption mechbnism blgorithm.
     *
     * <p> A new ExemptionMechbnism object encbpsulbting the
     * ExemptionMechbnismSpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.

     * @pbrbm blgorithm the stbndbrd nbme of the requested exemption mechbnism.
     * See the ExemptionMechbnism section in the
     * <b href=
     *   "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Exemption">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd exemption mechbnism nbmes.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return the new <code>ExemptionMechbnism</code> object.
     *
     * @exception NullPointerException if <code>blgorithm</code>
     *          is null.
     *
     * @exception NoSuchAlgorithmException if bn ExemptionMechbnismSpi
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
    public stbtic finbl ExemptionMechbnism getInstbnce(String blgorithm,
            String provider) throws NoSuchAlgorithmException,
            NoSuchProviderException {
        Instbnce instbnce = JceSecurity.getInstbnce("ExemptionMechbnism",
                ExemptionMechbnismSpi.clbss, blgorithm, provider);
        return new ExemptionMechbnism((ExemptionMechbnismSpi)instbnce.impl,
                instbnce.provider, blgorithm);
    }

    /**
     * Returns bn <code>ExemptionMechbnism</code> object thbt implements the
     * specified exemption mechbnism blgorithm.
     *
     * <p> A new ExemptionMechbnism object encbpsulbting the
     * ExemptionMechbnismSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm blgorithm the stbndbrd nbme of the requested exemption mechbnism.
     * See the ExemptionMechbnism section in the
     * <b href=
     *   "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Exemption">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd exemption mechbnism nbmes.
     *
     * @pbrbm provider the provider.
     *
     * @return the new <code>ExemptionMechbnism</code> object.
     *
     * @exception NullPointerException if <code>blgorithm</code>
     *          is null.
     *
     * @exception NoSuchAlgorithmException if bn ExemptionMechbnismSpi
     *          implementbtion for the specified blgorithm is not bvbilbble
     *          from the specified Provider object.
     *
     * @exception IllegblArgumentException if the <code>provider</code>
     *          is null.
     *
     * @see jbvb.security.Provider
     */
    public stbtic finbl ExemptionMechbnism getInstbnce(String blgorithm,
            Provider provider) throws NoSuchAlgorithmException {
        Instbnce instbnce = JceSecurity.getInstbnce("ExemptionMechbnism",
                ExemptionMechbnismSpi.clbss, blgorithm, provider);
        return new ExemptionMechbnism((ExemptionMechbnismSpi)instbnce.impl,
                instbnce.provider, blgorithm);
    }

    /**
     * Returns the provider of this <code>ExemptionMechbnism</code> object.
     *
     * @return the provider of this <code>ExemptionMechbnism</code> object.
     */
    public finbl Provider getProvider() {
        return this.provider;
    }

    /**
     * Returns whether the result blob hbs been generbted successfully by this
     * exemption mechbnism.
     *
     * <p>The method blso mbkes sure thbt the key pbssed in is the sbme bs
     * the one this exemption mechbnism used in initiblizing bnd generbting
     * phbses.
     *
     * @pbrbm key the key the crypto is going to use.
     *
     * @return whether the result blob of the sbme key hbs been generbted
     * successfully by this exemption mechbnism; fblse if <code>key</code>
     * is null.
     *
     * @exception ExemptionMechbnismException if problem(s) encountered
     * while determining whether the result blob hbs been generbted successfully
     * by this exemption mechbnism object.
     */
    public finbl boolebn isCryptoAllowed(Key key)
            throws ExemptionMechbnismException {
        boolebn ret = fblse;
        if (done && (key != null)) {
            // Check if the key pbssed in is the sbme bs the one
            // this exemption mechbnism used.
            ret = keyStored.equbls(key);
        }
        return ret;
     }

    /**
     * Returns the length in bytes thbt bn output buffer would need to be in
     * order to hold the result of the next
     * {@link #genExemptionBlob(byte[]) genExemptionBlob}
     * operbtion, given the input length <code>inputLen</code> (in bytes).
     *
     * <p>The bctubl output length of the next
     * {@link #genExemptionBlob(byte[]) genExemptionBlob}
     * cbll mby be smbller thbn the length returned by this method.
     *
     * @pbrbm inputLen the input length (in bytes)
     *
     * @return the required output buffer size (in bytes)
     *
     * @exception IllegblStbteException if this exemption mechbnism is in b
     * wrong stbte (e.g., hbs not yet been initiblized)
     */
    public finbl int getOutputSize(int inputLen) throws IllegblStbteException {
        if (!initiblized) {
            throw new IllegblStbteException(
                "ExemptionMechbnism not initiblized");
        }
        if (inputLen < 0) {
            throw new IllegblArgumentException(
                "Input size must be equbl to " + "or grebter thbn zero");
        }
        return exmechSpi.engineGetOutputSize(inputLen);
    }

    /**
     * Initiblizes this exemption mechbnism with b key.
     *
     * <p>If this exemption mechbnism requires bny blgorithm pbrbmeters
     * thbt cbnnot be derived from the given <code>key</code>, the
     * underlying exemption mechbnism implementbtion is supposed to
     * generbte the required pbrbmeters itself (using provider-specific
     * defbult vblues); in the cbse thbt blgorithm pbrbmeters must be
     * specified by the cbller, bn <code>InvblidKeyException</code> is rbised.
     *
     * @pbrbm key the key for this exemption mechbnism
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * this exemption mechbnism.
     * @exception ExemptionMechbnismException if problem(s) encountered in the
     * process of initiblizing.
     */
    public finbl void init(Key key)
            throws InvblidKeyException, ExemptionMechbnismException {
        done = fblse;
        initiblized = fblse;

        keyStored = key;
        exmechSpi.engineInit(key);
        initiblized = true;
    }

    /**
     * Initiblizes this exemption mechbnism with b key bnd b set of blgorithm
     * pbrbmeters.
     *
     * <p>If this exemption mechbnism requires bny blgorithm pbrbmeters
     * bnd <code>pbrbms</code> is null, the underlying exemption
     * mechbnism implementbtion is supposed to generbte the required
     * pbrbmeters itself (using provider-specific defbult vblues); in the cbse
     * thbt blgorithm pbrbmeters must be specified by the cbller, bn
     * <code>InvblidAlgorithmPbrbmeterException</code> is rbised.
     *
     * @pbrbm key the key for this exemption mechbnism
     * @pbrbm pbrbms the blgorithm pbrbmeters
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * this exemption mechbnism.
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this exemption mechbnism.
     * @exception ExemptionMechbnismException if problem(s) encountered in the
     * process of initiblizing.
     */
    public finbl void init(Key key, AlgorithmPbrbmeterSpec pbrbms)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException,
            ExemptionMechbnismException {
        done = fblse;
        initiblized = fblse;

        keyStored = key;
        exmechSpi.engineInit(key, pbrbms);
        initiblized = true;
    }

    /**
     * Initiblizes this exemption mechbnism with b key bnd b set of blgorithm
     * pbrbmeters.
     *
     * <p>If this exemption mechbnism requires bny blgorithm pbrbmeters
     * bnd <code>pbrbms</code> is null, the underlying exemption mechbnism
     * implementbtion is supposed to generbte the required pbrbmeters itself
     * (using provider-specific defbult vblues); in the cbse thbt blgorithm
     * pbrbmeters must be specified by the cbller, bn
     * <code>InvblidAlgorithmPbrbmeterException</code> is rbised.
     *
     * @pbrbm key the key for this exemption mechbnism
     * @pbrbm pbrbms the blgorithm pbrbmeters
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * this exemption mechbnism.
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this exemption mechbnism.
     * @exception ExemptionMechbnismException if problem(s) encountered in the
     * process of initiblizing.
     */
    public finbl void init(Key key, AlgorithmPbrbmeters pbrbms)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException,
            ExemptionMechbnismException {
        done = fblse;
        initiblized = fblse;

        keyStored = key;
        exmechSpi.engineInit(key, pbrbms);
        initiblized = true;
    }

    /**
     * Generbtes the exemption mechbnism key blob.
     *
     * @return the new buffer with the result key blob.
     *
     * @exception IllegblStbteException if this exemption mechbnism is in
     * b wrong stbte (e.g., hbs not been initiblized).
     * @exception ExemptionMechbnismException if problem(s) encountered in the
     * process of generbting.
     */
    public finbl byte[] genExemptionBlob() throws IllegblStbteException,
            ExemptionMechbnismException {
        if (!initiblized) {
            throw new IllegblStbteException(
                "ExemptionMechbnism not initiblized");
        }
        byte[] blob = exmechSpi.engineGenExemptionBlob();
        done = true;
        return blob;
    }

    /**
     * Generbtes the exemption mechbnism key blob, bnd stores the result in
     * the <code>output</code> buffer.
     *
     * <p>If the <code>output</code> buffer is too smbll to hold the result,
     * b <code>ShortBufferException</code> is thrown. In this cbse, repebt this
     * cbll with b lbrger output buffer. Use
     * {@link #getOutputSize(int) getOutputSize} to determine how big
     * the output buffer should be.
     *
     * @pbrbm output the buffer for the result
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception IllegblStbteException if this exemption mechbnism is in
     * b wrong stbte (e.g., hbs not been initiblized).
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result.
     * @exception ExemptionMechbnismException if problem(s) encountered in the
     * process of generbting.
     */
    public finbl int genExemptionBlob(byte[] output)
            throws IllegblStbteException, ShortBufferException,
            ExemptionMechbnismException {
        if (!initiblized) {
            throw new IllegblStbteException
            ("ExemptionMechbnism not initiblized");
        }
        int n = exmechSpi.engineGenExemptionBlob(output, 0);
        done = true;
        return n;
    }

    /**
     * Generbtes the exemption mechbnism key blob, bnd stores the result in
     * the <code>output</code> buffer, stbrting bt <code>outputOffset</code>
     * inclusive.
     *
     * <p>If the <code>output</code> buffer is too smbll to hold the result,
     * b <code>ShortBufferException</code> is thrown. In this cbse, repebt this
     * cbll with b lbrger output buffer. Use
     * {@link #getOutputSize(int) getOutputSize} to determine how big
     * the output buffer should be.
     *
     * @pbrbm output the buffer for the result
     * @pbrbm outputOffset the offset in <code>output</code> where the result
     * is stored
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception IllegblStbteException if this exemption mechbnism is in
     * b wrong stbte (e.g., hbs not been initiblized).
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result.
     * @exception ExemptionMechbnismException if problem(s) encountered in the
     * process of generbting.
     */
    public finbl int genExemptionBlob(byte[] output, int outputOffset)
            throws IllegblStbteException, ShortBufferException,
            ExemptionMechbnismException {
        if (!initiblized) {
            throw new IllegblStbteException
            ("ExemptionMechbnism not initiblized");
        }
        int n = exmechSpi.engineGenExemptionBlob(output, outputOffset);
        done = true;
        return n;
    }

    /**
     * Ensures thbt the key stored bwby by this ExemptionMechbnism
     * object will be wiped out when there bre no more references to it.
     */
    protected void finblize() {
        keyStored = null;
        // Are there bnything else we could do?
    }
}
