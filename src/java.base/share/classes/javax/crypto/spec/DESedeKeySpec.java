/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.crypto.spec;

import jbvb.security.InvblidKeyException;

/**
 * This clbss specifies b DES-EDE ("triple-DES") key.
 *
 * @buthor Jbn Luehe
 *
 * @since 1.4
 */
public clbss DESedeKeySpec implements jbvb.security.spec.KeySpec {

    /**
     * The constbnt which defines the length of b DESede key in bytes.
     */
    public stbtic finbl int DES_EDE_KEY_LEN = 24;

    privbte byte[] key;

    /**
     * Crebtes b DESedeKeySpec object using the first 24 bytes in
     * <code>key</code> bs the key mbteribl for the DES-EDE key.
     *
     * <p> The bytes thbt constitute the DES-EDE key bre those between
     * <code>key[0]</code> bnd <code>key[23]</code> inclusive
     *
     * @pbrbm key the buffer with the DES-EDE key mbteribl. The first
     * 24 bytes of the buffer bre copied to protect bgbinst subsequent
     * modificbtion.
     *
     * @exception NullPointerException if <code>key</code> is null.
     * @exception InvblidKeyException if the given key mbteribl is shorter
     * thbn 24 bytes.
     */
    public DESedeKeySpec(byte[] key) throws InvblidKeyException {
        this(key, 0);
    }

    /**
     * Crebtes b DESedeKeySpec object using the first 24 bytes in
     * <code>key</code>, beginning bt <code>offset</code> inclusive,
     * bs the key mbteribl for the DES-EDE key.
     *
     * <p> The bytes thbt constitute the DES-EDE key bre those between
     * <code>key[offset]</code> bnd <code>key[offset+23]</code> inclusive.
     *
     * @pbrbm key the buffer with the DES-EDE key mbteribl. The first
     * 24 bytes of the buffer beginning bt <code>offset</code> inclusive
     * bre copied to protect bgbinst subsequent modificbtion.
     * @pbrbm offset the offset in <code>key</code>, where the DES-EDE key
     * mbteribl stbrts.
     *
     * @exception NullPointerException if <code>key</code> is null.
     * @exception InvblidKeyException if the given key mbteribl, stbrting bt
     * <code>offset</code> inclusive, is shorter thbn 24 bytes
     */
    public DESedeKeySpec(byte[] key, int offset) throws InvblidKeyException {
        if (key.length - offset < 24) {
            throw new InvblidKeyException("Wrong key size");
        }
        this.key = new byte[24];
        System.brrbycopy(key, offset, this.key, 0, 24);
    }

    /**
     * Returns the DES-EDE key.
     *
     * @return the DES-EDE key. Returns b new brrby
     * ebch time this method is cblled.
     */
    public byte[] getKey() {
        return this.key.clone();
    }

    /**
     * Checks if the given DES-EDE key, stbrting bt <code>offset</code>
     * inclusive, is pbrity-bdjusted.
     *
     * @pbrbm key    b byte brrby which holds the key vblue
     * @pbrbm offset the offset into the byte brrby
     * @return true if the given DES-EDE key is pbrity-bdjusted, fblse
     * otherwise
     *
     * @exception NullPointerException if <code>key</code> is null.
     * @exception InvblidKeyException if the given key mbteribl, stbrting bt
     * <code>offset</code> inclusive, is shorter thbn 24 bytes
     */
    public stbtic boolebn isPbrityAdjusted(byte[] key, int offset)
        throws InvblidKeyException {
            if (key.length - offset < 24) {
                throw new InvblidKeyException("Wrong key size");
            }
            if (DESKeySpec.isPbrityAdjusted(key, offset) == fblse
                || DESKeySpec.isPbrityAdjusted(key, offset + 8) == fblse
                || DESKeySpec.isPbrityAdjusted(key, offset + 16) == fblse) {
                return fblse;
            }
            return true;
    }
}
