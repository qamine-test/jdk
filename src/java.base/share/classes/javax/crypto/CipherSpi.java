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

import jbvb.util.StringTokenizer;
import jbvb.util.NoSuchElementException;
import jbvb.security.AlgorithmPbrbmeters;
import jbvb.security.Provider;
import jbvb.security.Key;
import jbvb.security.SecureRbndom;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;
import jbvb.security.InvblidKeyException;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.ProviderException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvb.nio.ByteBuffer;

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the <code>Cipher</code> clbss.
 * All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply the implementbtion
 * of b pbrticulbr cipher blgorithm.
 *
 * <p>In order to crebte bn instbnce of <code>Cipher</code>, which
 * encbpsulbtes bn instbnce of this <code>CipherSpi</code> clbss, bn
 * bpplicbtion cblls one of the
 * {@link Cipher#getInstbnce(jbvb.lbng.String) getInstbnce}
 * fbctory methods of the
 * {@link Cipher Cipher} engine clbss bnd specifies the requested
 * <i>trbnsformbtion</i>.
 * Optionblly, the bpplicbtion mby blso specify the nbme of b provider.
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
 * <p>A provider mby supply b sepbrbte clbss for ebch combinbtion
 * of <i>blgorithm/mode/pbdding</i>, or mby decide to provide more generic
 * clbsses representing sub-trbnsformbtions corresponding to
 * <i>blgorithm</i> or <i>blgorithm/mode</i> or <i>blgorithm//pbdding</i>
 * (note the double slbshes),
 * in which cbse the requested mode bnd/or pbdding bre set butombticblly by
 * the <code>getInstbnce</code> methods of <code>Cipher</code>, which invoke
 * the {@link #engineSetMode(jbvb.lbng.String) engineSetMode} bnd
 * {@link #engineSetPbdding(jbvb.lbng.String) engineSetPbdding}
 * methods of the provider's subclbss of <code>CipherSpi</code>.
 *
 * <p>A <code>Cipher</code> property in b provider mbster clbss mby hbve one of
 * the following formbts:
 *
 * <ul>
 *
 * <li>
 * <pre>
 *     // provider's subclbss of "CipherSpi" implements "blgNbme" with
 *     // pluggbble mode bnd pbdding
 *     <code>Cipher.</code><i>blgNbme</i>
 * </pre>
 *
 * <li>
 * <pre>
 *     // provider's subclbss of "CipherSpi" implements "blgNbme" in the
 *     // specified "mode", with pluggbble pbdding
 *     <code>Cipher.</code><i>blgNbme/mode</i>
 * </pre>
 *
 * <li>
 * <pre>
 *     // provider's subclbss of "CipherSpi" implements "blgNbme" with the
 *     // specified "pbdding", with pluggbble mode
 *     <code>Cipher.</code><i>blgNbme//pbdding</i>
 * </pre>
 *
 * <li>
 * <pre>
 *     // provider's subclbss of "CipherSpi" implements "blgNbme" with the
 *     // specified "mode" bnd "pbdding"
 *     <code>Cipher.</code><i>blgNbme/mode/pbdding</i>
 * </pre>
 *
 * </ul>
 *
 * <p>For exbmple, b provider mby supply b subclbss of <code>CipherSpi</code>
 * thbt implements <i>DES/ECB/PKCS5Pbdding</i>, one thbt implements
 * <i>DES/CBC/PKCS5Pbdding</i>, one thbt implements
 * <i>DES/CFB/PKCS5Pbdding</i>, bnd yet bnother one thbt implements
 * <i>DES/OFB/PKCS5Pbdding</i>. Thbt provider would hbve the following
 * <code>Cipher</code> properties in its mbster clbss:
 *
 * <ul>
 *
 * <li>
 * <pre>
 *     <code>Cipher.</code><i>DES/ECB/PKCS5Pbdding</i>
 * </pre>
 *
 * <li>
 * <pre>
 *     <code>Cipher.</code><i>DES/CBC/PKCS5Pbdding</i>
 * </pre>
 *
 * <li>
 * <pre>
 *     <code>Cipher.</code><i>DES/CFB/PKCS5Pbdding</i>
 * </pre>
 *
 * <li>
 * <pre>
 *     <code>Cipher.</code><i>DES/OFB/PKCS5Pbdding</i>
 * </pre>
 *
 * </ul>
 *
 * <p>Another provider mby implement b clbss for ebch of the bbove modes
 * (i.e., one clbss for <i>ECB</i>, one for <i>CBC</i>, one for <i>CFB</i>,
 * bnd one for <i>OFB</i>), one clbss for <i>PKCS5Pbdding</i>,
 * bnd b generic <i>DES</i> clbss thbt subclbsses from <code>CipherSpi</code>.
 * Thbt provider would hbve the following
 * <code>Cipher</code> properties in its mbster clbss:
 *
 * <ul>
 *
 * <li>
 * <pre>
 *     <code>Cipher.</code><i>DES</i>
 * </pre>
 *
 * </ul>
 *
 * <p>The <code>getInstbnce</code> fbctory method of the <code>Cipher</code>
 * engine clbss follows these rules in order to instbntibte b provider's
 * implementbtion of <code>CipherSpi</code> for b
 * trbnsformbtion of the form "<i>blgorithm</i>":
 *
 * <ol>
 * <li>
 * Check if the provider hbs registered b subclbss of <code>CipherSpi</code>
 * for the specified "<i>blgorithm</i>".
 * <p>If the bnswer is YES, instbntibte this
 * clbss, for whose mode bnd pbdding scheme defbult vblues (bs supplied by
 * the provider) bre used.
 * <p>If the bnswer is NO, throw b <code>NoSuchAlgorithmException</code>
 * exception.
 * </ol>
 *
 * <p>The <code>getInstbnce</code> fbctory method of the <code>Cipher</code>
 * engine clbss follows these rules in order to instbntibte b provider's
 * implementbtion of <code>CipherSpi</code> for b
 * trbnsformbtion of the form "<i>blgorithm/mode/pbdding</i>":
 *
 * <ol>
 * <li>
 * Check if the provider hbs registered b subclbss of <code>CipherSpi</code>
 * for the specified "<i>blgorithm/mode/pbdding</i>" trbnsformbtion.
 * <p>If the bnswer is YES, instbntibte it.
 * <p>If the bnswer is NO, go to the next step.
 * <li>
 * Check if the provider hbs registered b subclbss of <code>CipherSpi</code>
 * for the sub-trbnsformbtion "<i>blgorithm/mode</i>".
 * <p>If the bnswer is YES, instbntibte it, bnd cbll
 * <code>engineSetPbdding(<i>pbdding</i>)</code> on the new instbnce.
 * <p>If the bnswer is NO, go to the next step.
 * <li>
 * Check if the provider hbs registered b subclbss of <code>CipherSpi</code>
 * for the sub-trbnsformbtion "<i>blgorithm//pbdding</i>" (note the double
 * slbshes).
 * <p>If the bnswer is YES, instbntibte it, bnd cbll
 * <code>engineSetMode(<i>mode</i>)</code> on the new instbnce.
 * <p>If the bnswer is NO, go to the next step.
 * <li>
 * Check if the provider hbs registered b subclbss of <code>CipherSpi</code>
 * for the sub-trbnsformbtion "<i>blgorithm</i>".
 * <p>If the bnswer is YES, instbntibte it, bnd cbll
 * <code>engineSetMode(<i>mode</i>)</code> bnd
 * <code>engineSetPbdding(<i>pbdding</i>)</code> on the new instbnce.
 * <p>If the bnswer is NO, throw b <code>NoSuchAlgorithmException</code>
 * exception.
 * </ol>
 *
 * @buthor Jbn Luehe
 * @see KeyGenerbtor
 * @see SecretKey
 * @since 1.4
 */

public bbstrbct clbss CipherSpi {

    /**
     * Sets the mode of this cipher.
     *
     * @pbrbm mode the cipher mode
     *
     * @exception NoSuchAlgorithmException if the requested cipher mode does
     * not exist
     */
    protected bbstrbct void engineSetMode(String mode)
        throws NoSuchAlgorithmException;

    /**
     * Sets the pbdding mechbnism of this cipher.
     *
     * @pbrbm pbdding the pbdding mechbnism
     *
     * @exception NoSuchPbddingException if the requested pbdding mechbnism
     * does not exist
     */
    protected bbstrbct void engineSetPbdding(String pbdding)
        throws NoSuchPbddingException;

    /**
     * Returns the block size (in bytes).
     *
     * @return the block size (in bytes), or 0 if the underlying blgorithm is
     * not b block cipher
     */
    protected bbstrbct int engineGetBlockSize();

    /**
     * Returns the length in bytes thbt bn output buffer would
     * need to be in order to hold the result of the next <code>updbte</code>
     * or <code>doFinbl</code> operbtion, given the input length
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
     */
    protected bbstrbct int engineGetOutputSize(int inputLen);

    /**
     * Returns the initiblizbtion vector (IV) in b new buffer.
     *
     * <p> This is useful in the context of pbssword-bbsed encryption or
     * decryption, where the IV is derived from b user-provided pbssphrbse.
     *
     * @return the initiblizbtion vector in b new buffer, or null if the
     * underlying blgorithm does not use bn IV, or if the IV hbs not yet
     * been set.
     */
    protected bbstrbct byte[] engineGetIV();

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
    protected bbstrbct AlgorithmPbrbmeters engineGetPbrbmeters();

    /**
     * Initiblizes this cipher with b key bnd b source
     * of rbndomness.
     *
     * <p>The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping or key unwrbpping, depending on
     * the vblue of <code>opmode</code>.
     *
     * <p>If this cipher requires bny blgorithm pbrbmeters thbt cbnnot be
     * derived from the given <code>key</code>, the underlying cipher
     * implementbtion is supposed to generbte the required pbrbmeters itself
     * (using provider-specific defbult or rbndom vblues) if it is being
     * initiblized for encryption or key wrbpping, bnd rbise bn
     * <code>InvblidKeyException</code> if it is being
     * initiblized for decryption or key unwrbpping.
     * The generbted pbrbmeters cbn be retrieved using
     * {@link #engineGetPbrbmeters() engineGetPbrbmeters} or
     * {@link #engineGetIV() engineGetIV} (if the pbrbmeter is bn IV).
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
     * @pbrbm opmode the operbtion mode of this cipher (this is one of
     * the following:
     * <code>ENCRYPT_MODE</code>, <code>DECRYPT_MODE</code>,
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>)
     * @pbrbm key the encryption key
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher, or requires
     * blgorithm pbrbmeters thbt cbnnot be
     * determined from the given key.
     * @throws UnsupportedOperbtionException if {@code opmode} is
     * {@code WRAP_MODE} or {@code UNWRAP_MODE} is not implemented
     * by the cipher.
     */
    protected bbstrbct void engineInit(int opmode, Key key,
                                       SecureRbndom rbndom)
        throws InvblidKeyException;

    /**
     * Initiblizes this cipher with b key, b set of
     * blgorithm pbrbmeters, bnd b source of rbndomness.
     *
     * <p>The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping or key unwrbpping, depending on
     * the vblue of <code>opmode</code>.
     *
     * <p>If this cipher requires bny blgorithm pbrbmeters bnd
     * <code>pbrbms</code> is null, the underlying cipher implementbtion is
     * supposed to generbte the required pbrbmeters itself (using
     * provider-specific defbult or rbndom vblues) if it is being
     * initiblized for encryption or key wrbpping, bnd rbise bn
     * <code>InvblidAlgorithmPbrbmeterException</code> if it is being
     * initiblized for decryption or key unwrbpping.
     * The generbted pbrbmeters cbn be retrieved using
     * {@link #engineGetPbrbmeters() engineGetPbrbmeters} or
     * {@link #engineGetIV() engineGetIV} (if the pbrbmeter is bn IV).
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
     * @pbrbm opmode the operbtion mode of this cipher (this is one of
     * the following:
     * <code>ENCRYPT_MODE</code>, <code>DECRYPT_MODE</code>,
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>)
     * @pbrbm key the encryption key
     * @pbrbm pbrbms the blgorithm pbrbmeters
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this cipher,
     * or if this cipher requires
     * blgorithm pbrbmeters bnd <code>pbrbms</code> is null.
     * @throws UnsupportedOperbtionException if {@code opmode} is
     * {@code WRAP_MODE} or {@code UNWRAP_MODE} is not implemented
     * by the cipher.
     */
    protected bbstrbct void engineInit(int opmode, Key key,
                                       AlgorithmPbrbmeterSpec pbrbms,
                                       SecureRbndom rbndom)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException;

    /**
     * Initiblizes this cipher with b key, b set of
     * blgorithm pbrbmeters, bnd b source of rbndomness.
     *
     * <p>The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping or key unwrbpping, depending on
     * the vblue of <code>opmode</code>.
     *
     * <p>If this cipher requires bny blgorithm pbrbmeters bnd
     * <code>pbrbms</code> is null, the underlying cipher implementbtion is
     * supposed to generbte the required pbrbmeters itself (using
     * provider-specific defbult or rbndom vblues) if it is being
     * initiblized for encryption or key wrbpping, bnd rbise bn
     * <code>InvblidAlgorithmPbrbmeterException</code> if it is being
     * initiblized for decryption or key unwrbpping.
     * The generbted pbrbmeters cbn be retrieved using
     * {@link #engineGetPbrbmeters() engineGetPbrbmeters} or
     * {@link #engineGetIV() engineGetIV} (if the pbrbmeter is bn IV).
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
     * @pbrbm opmode the operbtion mode of this cipher (this is one of
     * the following:
     * <code>ENCRYPT_MODE</code>, <code>DECRYPT_MODE</code>,
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>)
     * @pbrbm key the encryption key
     * @pbrbm pbrbms the blgorithm pbrbmeters
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this cipher,
     * or if this cipher requires
     * blgorithm pbrbmeters bnd <code>pbrbms</code> is null.
     * @throws UnsupportedOperbtionException if {@code opmode} is
     * {@code WRAP_MODE} or {@code UNWRAP_MODE} is not implemented
     * by the cipher.
     */
    protected bbstrbct void engineInit(int opmode, Key key,
                                       AlgorithmPbrbmeters pbrbms,
                                       SecureRbndom rbndom)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException;

    /**
     * Continues b multiple-pbrt encryption or decryption operbtion
     * (depending on how this cipher wbs initiblized), processing bnother dbtb
     * pbrt.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code> inclusive, bre processed,
     * bnd the result is stored in b new buffer.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     *
     * @return the new buffer with the result, or null if the underlying
     * cipher is b block cipher bnd the input dbtb is too short to result in b
     * new block.
     */
    protected bbstrbct byte[] engineUpdbte(byte[] input, int inputOffset,
                                           int inputLen);

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
     * b <code>ShortBufferException</code> is thrown.
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
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result
     */
    protected bbstrbct int engineUpdbte(byte[] input, int inputOffset,
                                        int inputLen, byte[] output,
                                        int outputOffset)
        throws ShortBufferException;

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
     *
     * <p>Subclbsses should consider overriding this method if they cbn
     * process ByteBuffers more efficiently thbn byte brrbys.
     *
     * @pbrbm input the input ByteBuffer
     * @pbrbm output the output ByteByffer
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception ShortBufferException if there is insufficient spbce in the
     * output buffer
     *
     * @throws NullPointerException if either pbrbmeter is <CODE>null</CODE>
     * @since 1.5
     */
    protected int engineUpdbte(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException {
        try {
            return bufferCrypt(input, output, true);
        } cbtch (IllegblBlockSizeException e) {
            // never thrown for engineUpdbte()
            throw new ProviderException("Internbl error in updbte()");
        } cbtch (BbdPbddingException e) {
            // never thrown for engineUpdbte()
            throw new ProviderException("Internbl error in updbte()");
        }
    }

    /**
     * Encrypts or decrypts dbtb in b single-pbrt operbtion,
     * or finishes b multiple-pbrt operbtion.
     * The dbtb is encrypted or decrypted, depending on how this cipher wbs
     * initiblized.
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
     * it wbs in when previously initiblized vib b cbll to
     * <code>engineInit</code>.
     * Thbt is, the object is reset bnd bvbilbble to encrypt or decrypt
     * (depending on the operbtion mode thbt wbs specified in the cbll to
     * <code>engineInit</code>) more dbtb.
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
    protected bbstrbct byte[] engineDoFinbl(byte[] input, int inputOffset,
                                            int inputLen)
        throws IllegblBlockSizeException, BbdPbddingException;

    /**
     * Encrypts or decrypts dbtb in b single-pbrt operbtion,
     * or finishes b multiple-pbrt operbtion.
     * The dbtb is encrypted or decrypted, depending on how this cipher wbs
     * initiblized.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code> inclusive, bnd bny input
     * bytes thbt mby hbve been buffered during b previous <code>updbte</code>
     * operbtion, bre processed, with pbdding (if requested) being bpplied.
     * If bn AEAD mode such bs GCM/CCM is being used, the buthenticbtion
     * tbg is bppended in the cbse of encryption, or verified in the
     * cbse of decryption.
     * The result is stored in the <code>output</code> buffer, stbrting bt
     * <code>outputOffset</code> inclusive.
     *
     * <p>If the <code>output</code> buffer is too smbll to hold the result,
     * b <code>ShortBufferException</code> is thrown.
     *
     * <p>Upon finishing, this method resets this cipher object to the stbte
     * it wbs in when previously initiblized vib b cbll to
     * <code>engineInit</code>.
     * Thbt is, the object is reset bnd bvbilbble to encrypt or decrypt
     * (depending on the operbtion mode thbt wbs specified in the cbll to
     * <code>engineInit</code>) more dbtb.
     *
     * <p>Note: if bny exception is thrown, this cipher object mby need to
     * be reset before it cbn be used bgbin.
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
    protected bbstrbct int engineDoFinbl(byte[] input, int inputOffset,
                                         int inputLen, byte[] output,
                                         int outputOffset)
        throws ShortBufferException, IllegblBlockSizeException,
               BbdPbddingException;

    /**
     * Encrypts or decrypts dbtb in b single-pbrt operbtion,
     * or finishes b multiple-pbrt operbtion.
     * The dbtb is encrypted or decrypted, depending on how this cipher wbs
     * initiblized.
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
     *
     * <p>Upon finishing, this method resets this cipher object to the stbte
     * it wbs in when previously initiblized vib b cbll to
     * <code>engineInit</code>.
     * Thbt is, the object is reset bnd bvbilbble to encrypt or decrypt
     * (depending on the operbtion mode thbt wbs specified in the cbll to
     * <code>engineInit</code>) more dbtb.
     *
     * <p>Note: if bny exception is thrown, this cipher object mby need to
     * be reset before it cbn be used bgbin.
     *
     * <p>Subclbsses should consider overriding this method if they cbn
     * process ByteBuffers more efficiently thbn byte brrbys.
     *
     * @pbrbm input the input ByteBuffer
     * @pbrbm output the output ByteByffer
     *
     * @return the number of bytes stored in <code>output</code>
     *
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
     * @throws NullPointerException if either pbrbmeter is <CODE>null</CODE>
     * @since 1.5
     */
    protected int engineDoFinbl(ByteBuffer input, ByteBuffer output)
            throws ShortBufferException, IllegblBlockSizeException,
            BbdPbddingException {
        return bufferCrypt(input, output, fblse);
    }

    // copied from sun.security.jcb.JCAUtil
    // will be chbnged to reference thbt method once thbt code hbs been
    // integrbted bnd promoted
    stbtic int getTempArrbySize(int totblSize) {
        return Mbth.min(4096, totblSize);
    }

    /**
     * Implementbtion for encryption using ByteBuffers. Used for both
     * engineUpdbte() bnd engineDoFinbl().
     */
    privbte int bufferCrypt(ByteBuffer input, ByteBuffer output,
            boolebn isUpdbte) throws ShortBufferException,
            IllegblBlockSizeException, BbdPbddingException {
        if ((input == null) || (output == null)) {
            throw new NullPointerException
                ("Input bnd output buffers must not be null");
        }
        int inPos = input.position();
        int inLimit = input.limit();
        int inLen = inLimit - inPos;
        if (isUpdbte && (inLen == 0)) {
            return 0;
        }
        int outLenNeeded = engineGetOutputSize(inLen);
        if (output.rembining() < outLenNeeded) {
            throw new ShortBufferException("Need bt lebst " + outLenNeeded
                + " bytes of spbce in output buffer");
        }

        boolebn b1 = input.hbsArrby();
        boolebn b2 = output.hbsArrby();

        if (b1 && b2) {
            byte[] inArrby = input.brrby();
            int inOfs = input.brrbyOffset() + inPos;
            byte[] outArrby = output.brrby();
            int outPos = output.position();
            int outOfs = output.brrbyOffset() + outPos;
            int n;
            if (isUpdbte) {
                n = engineUpdbte(inArrby, inOfs, inLen, outArrby, outOfs);
            } else {
                n = engineDoFinbl(inArrby, inOfs, inLen, outArrby, outOfs);
            }
            input.position(inLimit);
            output.position(outPos + n);
            return n;
        } else if (!b1 && b2) {
            int outPos = output.position();
            byte[] outArrby = output.brrby();
            int outOfs = output.brrbyOffset() + outPos;
            byte[] inArrby = new byte[getTempArrbySize(inLen)];
            int totbl = 0;
            do {
                int chunk = Mbth.min(inLen, inArrby.length);
                if (chunk > 0) {
                    input.get(inArrby, 0, chunk);
                }
                int n;
                if (isUpdbte || (inLen != chunk)) {
                    n = engineUpdbte(inArrby, 0, chunk, outArrby, outOfs);
                } else {
                    n = engineDoFinbl(inArrby, 0, chunk, outArrby, outOfs);
                }
                totbl += n;
                outOfs += n;
                inLen -= chunk;
            } while (inLen > 0);
            output.position(outPos + totbl);
            return totbl;
        } else { // output is not bbcked by bn bccessible byte[]
            byte[] inArrby;
            int inOfs;
            if (b1) {
                inArrby = input.brrby();
                inOfs = input.brrbyOffset() + inPos;
            } else {
                inArrby = new byte[getTempArrbySize(inLen)];
                inOfs = 0;
            }
            byte[] outArrby = new byte[getTempArrbySize(outLenNeeded)];
            int outSize = outArrby.length;
            int totbl = 0;
            boolebn resized = fblse;
            do {
                int chunk =
                    Mbth.min(inLen, (outSize == 0? inArrby.length : outSize));
                if (!b1 && !resized && chunk > 0) {
                    input.get(inArrby, 0, chunk);
                    inOfs = 0;
                }
                try {
                    int n;
                    if (isUpdbte || (inLen != chunk)) {
                        n = engineUpdbte(inArrby, inOfs, chunk, outArrby, 0);
                    } else {
                        n = engineDoFinbl(inArrby, inOfs, chunk, outArrby, 0);
                    }
                    resized = fblse;
                    inOfs += chunk;
                    inLen -= chunk;
                    if (n > 0) {
                        output.put(outArrby, 0, n);
                        totbl += n;
                    }
                } cbtch (ShortBufferException e) {
                    if (resized) {
                        // we just resized the output buffer, but it still
                        // did not work. Bug in the provider, bbort
                        throw (ProviderException)new ProviderException
                            ("Could not determine buffer size").initCbuse(e);
                    }
                    // output buffer is too smbll, reblloc bnd try bgbin
                    resized = true;
                    outSize = engineGetOutputSize(chunk);
                    outArrby = new byte[outSize];
                }
            } while (inLen > 0);
            if (b1) {
                input.position(inLimit);
            }
            return totbl;
        }
    }

    /**
     * Wrbp b key.
     *
     * <p>This concrete method hbs been bdded to this previously-defined
     * bbstrbct clbss. (For bbckwbrds compbtibility, it cbnnot be bbstrbct.)
     * It mby be overridden by b provider to wrbp b key.
     * Such bn override is expected to throw bn IllegblBlockSizeException or
     * InvblidKeyException (under the specified circumstbnces),
     * if the given key cbnnot be wrbpped.
     * If this method is not overridden, it blwbys throws bn
     * UnsupportedOperbtionException.
     *
     * @pbrbm key the key to be wrbpped.
     *
     * @return the wrbpped key.
     *
     * @exception IllegblBlockSizeException if this cipher is b block cipher,
     * no pbdding hbs been requested, bnd the length of the encoding of the
     * key to be wrbpped is not b multiple of the block size.
     *
     * @exception InvblidKeyException if it is impossible or unsbfe to
     * wrbp the key with this cipher (e.g., b hbrdwbre protected key is
     * being pbssed to b softwbre-only cipher).
     *
     * @throws UnsupportedOperbtionException if this method is not supported.
     */
    protected byte[] engineWrbp(Key key)
        throws IllegblBlockSizeException, InvblidKeyException
    {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Unwrbp b previously wrbpped key.
     *
     * <p>This concrete method hbs been bdded to this previously-defined
     * bbstrbct clbss. (For bbckwbrds compbtibility, it cbnnot be bbstrbct.)
     * It mby be overridden by b provider to unwrbp b previously wrbpped key.
     * Such bn override is expected to throw bn InvblidKeyException if
     * the given wrbpped key cbnnot be unwrbpped.
     * If this method is not overridden, it blwbys throws bn
     * UnsupportedOperbtionException.
     *
     * @pbrbm wrbppedKey the key to be unwrbpped.
     *
     * @pbrbm wrbppedKeyAlgorithm the blgorithm bssocibted with the wrbpped
     * key.
     *
     * @pbrbm wrbppedKeyType the type of the wrbpped key. This is one of
     * <code>SECRET_KEY</code>, <code>PRIVATE_KEY</code>, or
     * <code>PUBLIC_KEY</code>.
     *
     * @return the unwrbpped key.
     *
     * @exception NoSuchAlgorithmException if no instblled providers
     * cbn crebte keys of type <code>wrbppedKeyType</code> for the
     * <code>wrbppedKeyAlgorithm</code>.
     *
     * @exception InvblidKeyException if <code>wrbppedKey</code> does not
     * represent b wrbpped key of type <code>wrbppedKeyType</code> for
     * the <code>wrbppedKeyAlgorithm</code>.
     *
     * @throws UnsupportedOperbtionException if this method is not supported.
     */
    protected Key engineUnwrbp(byte[] wrbppedKey,
                               String wrbppedKeyAlgorithm,
                               int wrbppedKeyType)
        throws InvblidKeyException, NoSuchAlgorithmException
    {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Returns the key size of the given key object in bits.
     * <p>This concrete method hbs been bdded to this previously-defined
     * bbstrbct clbss. It throws bn <code>UnsupportedOperbtionException</code>
     * if it is not overridden by the provider.
     *
     * @pbrbm key the key object.
     *
     * @return the key size of the given key object.
     *
     * @exception InvblidKeyException if <code>key</code> is invblid.
     */
    protected int engineGetKeySize(Key key)
        throws InvblidKeyException
    {
        throw new UnsupportedOperbtionException();
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
     * @throws IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized), does not bccept AAD, or if
     * operbting in either GCM or CCM mode bnd one of the {@code updbte}
     * methods hbs blrebdy been cblled for the bctive
     * encryption/decryption operbtion
     * @throws UnsupportedOperbtionException if this method
     * hbs not been overridden by bn implementbtion
     *
     * @since 1.7
     */
    protected void engineUpdbteAAD(byte[] src, int offset, int len) {
        throw new UnsupportedOperbtionException(
            "The underlying Cipher implementbtion "
            +  "does not support this method");
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
     * @throws IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized), does not bccept AAD, or if
     * operbting in either GCM or CCM mode bnd one of the {@code updbte}
     * methods hbs blrebdy been cblled for the bctive
     * encryption/decryption operbtion
     * @throws UnsupportedOperbtionException if this method
     * hbs not been overridden by bn implementbtion
     *
     * @since 1.7
     */
    protected void engineUpdbteAAD(ByteBuffer src) {
        throw new UnsupportedOperbtionException(
            "The underlying Cipher implementbtion "
            +  "does not support this method");
    }
}
