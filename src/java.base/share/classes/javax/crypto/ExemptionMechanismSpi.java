/*
 * Copyright (c) 1999, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.Key;
import jbvb.security.AlgorithmPbrbmeters;
import jbvb.security.InvblidKeyException;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the <code>ExemptionMechbnism</code> clbss.
 * All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply the implementbtion
 * of b pbrticulbr exemption mechbnism.
 *
 * @buthor Shbron Liu
 *
 * @since 1.4
 */

public bbstrbct clbss ExemptionMechbnismSpi {

    /**
     * Returns the length in bytes thbt bn output buffer would need to be in
     * order to hold the result of the next
     * {@link #engineGenExemptionBlob(byte[], int) engineGenExemptionBlob}
     * operbtion, given the input length <code>inputLen</code> (in bytes).
     *
     * <p>The bctubl output length of the next
     * {@link #engineGenExemptionBlob(byte[], int) engineGenExemptionBlob}
     * cbll mby be smbller thbn the length returned by this method.
     *
     * @pbrbm inputLen the input length (in bytes)
     *
     * @return the required output buffer size (in bytes)
     */
    protected bbstrbct int engineGetOutputSize(int inputLen);

    /**
     * Initiblizes this exemption mechbnism with b key.
     *
     * <p>If this exemption mechbnism requires bny blgorithm pbrbmeters
     * thbt cbnnot be derived from the given <code>key</code>, the underlying
     * exemption mechbnism implementbtion is supposed to generbte the required
     * pbrbmeters itself (using provider-specific defbult vblues); in the cbse
     * thbt blgorithm pbrbmeters must be specified by the cbller, bn
     * <code>InvblidKeyException</code> is rbised.
     *
     * @pbrbm key the key for this exemption mechbnism
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * this exemption mechbnism.
     * @exception ExemptionMechbnismException if problem(s) encountered in the
     * process of initiblizing.
     */
    protected bbstrbct void engineInit(Key key)
    throws InvblidKeyException, ExemptionMechbnismException;

    /**
     * Initiblizes this exemption mechbnism with b key bnd b set of blgorithm
     * pbrbmeters.
     *
     * <p>If this exemption mechbnism requires bny blgorithm pbrbmeters bnd
     * <code>pbrbms</code> is null, the underlying exemption mechbnism
     * implementbtion is supposed to generbte the required pbrbmeters
     * itself (using provider-specific defbult vblues); in the cbse thbt
     * blgorithm pbrbmeters must be specified by the cbller, bn
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
    protected bbstrbct void engineInit(Key key, AlgorithmPbrbmeterSpec pbrbms)
    throws InvblidKeyException, InvblidAlgorithmPbrbmeterException,
    ExemptionMechbnismException;

    /**
     * Initiblizes this exemption mechbnism with b key bnd b set of blgorithm
     * pbrbmeters.
     *
     * <p>If this exemption mechbnism requires bny blgorithm pbrbmeters
     * bnd <code>pbrbms</code> is null, the underlying exemption mechbnism
     * implementbtion is supposed to generbte the required pbrbmeters
     * itself (using provider-specific defbult vblues); in the cbse thbt
     * blgorithm pbrbmeters must be specified by the cbller, bn
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
    protected bbstrbct void engineInit(Key key, AlgorithmPbrbmeters pbrbms)
    throws InvblidKeyException, InvblidAlgorithmPbrbmeterException,
    ExemptionMechbnismException;

    /**
     * Generbtes the exemption mechbnism key blob.
     *
     * @return the new buffer with the result key blob.
     *
     * @exception ExemptionMechbnismException if problem(s) encountered in the
     * process of generbting.
     */
    protected bbstrbct byte[] engineGenExemptionBlob()
        throws ExemptionMechbnismException;

    /**
     * Generbtes the exemption mechbnism key blob, bnd stores the result in
     * the <code>output</code> buffer, stbrting bt <code>outputOffset</code>
     * inclusive.
     *
     * <p>If the <code>output</code> buffer is too smbll to hold the result,
     * b <code>ShortBufferException</code> is thrown. In this cbse, repebt this
     * cbll with b lbrger output buffer. Use
     * {@link #engineGetOutputSize(int) engineGetOutputSize} to determine
     * how big the output buffer should be.
     *
     * @pbrbm output the buffer for the result
     * @pbrbm outputOffset the offset in <code>output</code> where the result
     * is stored
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result.
     * @exception ExemptionMechbnismException if problem(s) encountered in the
     * process of generbting.
     */
    protected bbstrbct int engineGenExemptionBlob
    (byte[] output, int outputOffset)
        throws ShortBufferException, ExemptionMechbnismException;
}
