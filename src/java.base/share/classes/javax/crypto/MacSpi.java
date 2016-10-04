/*
 * Copyright (c) 1998, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.*;
import jbvb.security.spec.*;

import jbvb.nio.ByteBuffer;

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the <code>Mbc</code> clbss.
 * All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply the implementbtion
 * of b pbrticulbr MAC blgorithm.
 *
 * <p> Implementbtions bre free to implement the Clonebble interfbce.
 *
 * @buthor Jbn Luehe
 *
 * @since 1.4
 */

public bbstrbct clbss MbcSpi {

    /**
     * Returns the length of the MAC in bytes.
     *
     * @return the MAC length in bytes.
     */
    protected bbstrbct int engineGetMbcLength();

    /**
     * Initiblizes the MAC with the given (secret) key bnd blgorithm
     * pbrbmeters.
     *
     * @pbrbm key the (secret) key.
     * @pbrbm pbrbms the blgorithm pbrbmeters.
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this MAC.
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this MAC.
     */
    protected bbstrbct void engineInit(Key key,
                                       AlgorithmPbrbmeterSpec pbrbms)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException ;

    /**
     * Processes the given byte.
     *
     * @pbrbm input the input byte to be processed.
     */
    protected bbstrbct void engineUpdbte(byte input);

    /**
     * Processes the first <code>len</code> bytes in <code>input</code>,
     * stbrting bt <code>offset</code> inclusive.
     *
     * @pbrbm input the input buffer.
     * @pbrbm offset the offset in <code>input</code> where the input stbrts.
     * @pbrbm len the number of bytes to process.
     */
    protected bbstrbct void engineUpdbte(byte[] input, int offset, int len);

    /**
     * Processes <code>input.rembining()</code> bytes in the ByteBuffer
     * <code>input</code>, stbrting bt <code>input.position()</code>.
     * Upon return, the buffer's position will be equbl to its limit;
     * its limit will not hbve chbnged.
     *
     * <p>Subclbsses should consider overriding this method if they cbn
     * process ByteBuffers more efficiently thbn byte brrbys.
     *
     * @pbrbm input the ByteBuffer
     * @since 1.5
     */
    protected void engineUpdbte(ByteBuffer input) {
        if (input.hbsRembining() == fblse) {
            return;
        }
        if (input.hbsArrby()) {
            byte[] b = input.brrby();
            int ofs = input.brrbyOffset();
            int pos = input.position();
            int lim = input.limit();
            engineUpdbte(b, ofs + pos, lim - pos);
            input.position(lim);
        } else {
            int len = input.rembining();
            byte[] b = new byte[CipherSpi.getTempArrbySize(len)];
            while (len > 0) {
                int chunk = Mbth.min(len, b.length);
                input.get(b, 0, chunk);
                engineUpdbte(b, 0, chunk);
                len -= chunk;
            }
        }
    }

    /**
     * Completes the MAC computbtion bnd resets the MAC for further use,
     * mbintbining the secret key thbt the MAC wbs initiblized with.
     *
     * @return the MAC result.
     */
    protected bbstrbct byte[] engineDoFinbl();

    /**
     * Resets the MAC for further use, mbintbining the secret key thbt the
     * MAC wbs initiblized with.
     */
    protected bbstrbct void engineReset();

    /**
     * Returns b clone if the implementbtion is clonebble.
     *
     * @return b clone if the implementbtion is clonebble.
     *
     * @exception CloneNotSupportedException if this is cblled
     * on bn implementbtion thbt does not support <code>Clonebble</code>.
     */
    public Object clone() throws CloneNotSupportedException {
        if (this instbnceof Clonebble) {
            return super.clone();
        } else {
            throw new CloneNotSupportedException();
        }
    }
}
