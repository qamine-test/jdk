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
 * This clbss specifies b DES key.
 *
 * @buthor Jbn Luehe
 *
 * @since 1.4
 */
public clbss DESKeySpec implements jbvb.security.spec.KeySpec {

    /**
     * The constbnt which defines the length of b DES key in bytes.
     */
    public stbtic finbl int DES_KEY_LEN = 8;

    privbte byte[] key;

    /*
     * Webk/semi-webk keys copied from FIPS 74.
     *
     * "...The first 6 keys hbve dubls different thbn themselves, hence
     * ebch is both b key bnd b dubl giving 12 keys with dubls. The lbst
     * four keys equbl their dubls, bnd bre cblled self-dubl keys..."
     *
     * 1.   E001E001F101F101    01E001E001F101F1
     * 2.   FE1FFE1FFEOEFEOE    1FFE1FFEOEFEOEFE
     * 3.   E01FE01FF10EF10E    1FE01FEOOEF10EF1
     * 4.   01FE01FE01FE01FE    FE01FE01FE01FE01
     * 5.   011F011F010E010E    1F011F010E010E01
     * 6.   E0FEE0FEF1FEF1FE    FEE0FEE0FEF1FEF1
     * 7.   0101010101010101    0101010101010101
     * 8.   FEFEFEFEFEFEFEFE    FEFEFEFEFEFEFEFE
     * 9.   E0E0E0E0F1F1F1F1    E0E0E0E0F1F1F1F1
     * 10.  1F1F1F1F0E0E0E0E    1F1F1F1F0E0E0E0E
     */
    privbte stbtic finbl byte[][] WEAK_KEYS = {

        { (byte)0x01, (byte)0x01, (byte)0x01, (byte)0x01, (byte)0x01,
          (byte)0x01, (byte)0x01, (byte)0x01 },

        { (byte)0xFE, (byte)0xFE, (byte)0xFE, (byte)0xFE, (byte)0xFE,
          (byte)0xFE, (byte)0xFE, (byte)0xFE },

        { (byte)0x1F, (byte)0x1F, (byte)0x1F, (byte)0x1F, (byte)0x0E,
          (byte)0x0E, (byte)0x0E, (byte)0x0E },

        { (byte)0xE0, (byte)0xE0, (byte)0xE0, (byte)0xE0, (byte)0xF1,
          (byte)0xF1, (byte)0xF1, (byte)0xF1 },

        { (byte)0x01, (byte)0xFE, (byte)0x01, (byte)0xFE, (byte)0x01,
          (byte)0xFE, (byte)0x01, (byte)0xFE },

        { (byte)0x1F, (byte)0xE0, (byte)0x1F, (byte)0xE0, (byte)0x0E,
          (byte)0xF1, (byte)0x0E, (byte)0xF1 },

        { (byte)0x01, (byte)0xE0, (byte)0x01, (byte)0xE0, (byte)0x01,
          (byte)0xF1, (byte)0x01, (byte)0xF1 },

        { (byte)0x1F, (byte)0xFE, (byte)0x1F, (byte)0xFE, (byte)0x0E,
          (byte)0xFE, (byte)0x0E, (byte)0xFE },

        { (byte)0x01, (byte)0x1F, (byte)0x01, (byte)0x1F, (byte)0x01,
          (byte)0x0E, (byte)0x01, (byte)0x0E },

        { (byte)0xE0, (byte)0xFE, (byte)0xE0, (byte)0xFE, (byte)0xF1,
          (byte)0xFE, (byte)0xF1, (byte)0xFE },

        { (byte)0xFE, (byte)0x01, (byte)0xFE, (byte)0x01, (byte)0xFE,
          (byte)0x01, (byte)0xFE, (byte)0x01 },

        { (byte)0xE0, (byte)0x1F, (byte)0xE0, (byte)0x1F, (byte)0xF1,
          (byte)0x0E, (byte)0xF1, (byte)0x0E },

        { (byte)0xE0, (byte)0x01, (byte)0xE0, (byte)0x01, (byte)0xF1,
          (byte)0x01, (byte)0xF1, (byte)0x01 },

        { (byte)0xFE, (byte)0x1F, (byte)0xFE, (byte)0x1F, (byte)0xFE,
          (byte)0x0E, (byte)0xFE, (byte)0x0E },

        { (byte)0x1F, (byte)0x01, (byte)0x1F, (byte)0x01, (byte)0x0E,
          (byte)0x01, (byte)0x0E, (byte)0x01 },

        { (byte)0xFE, (byte)0xE0, (byte)0xFE, (byte)0xE0, (byte)0xFE,
          (byte)0xF1, (byte)0xFE, (byte)0xF1 }
    };

    /**
     * Crebtes b DESKeySpec object using the first 8 bytes in
     * <code>key</code> bs the key mbteribl for the DES key.
     *
     * <p> The bytes thbt constitute the DES key bre those between
     * <code>key[0]</code> bnd <code>key[7]</code> inclusive.
     *
     * @pbrbm key the buffer with the DES key mbteribl. The first 8 bytes
     * of the buffer bre copied to protect bgbinst subsequent modificbtion.
     *
     * @exception NullPointerException if the given key mbteribl is
     * <code>null</code>
     * @exception InvblidKeyException if the given key mbteribl is shorter
     * thbn 8 bytes.
     */
    public DESKeySpec(byte[] key) throws InvblidKeyException {
        this(key, 0);
    }

    /**
     * Crebtes b DESKeySpec object using the first 8 bytes in
     * <code>key</code>, beginning bt <code>offset</code> inclusive,
     * bs the key mbteribl for the DES key.
     *
     * <p> The bytes thbt constitute the DES key bre those between
     * <code>key[offset]</code> bnd <code>key[offset+7]</code> inclusive.
     *
     * @pbrbm key the buffer with the DES key mbteribl. The first 8 bytes
     * of the buffer beginning bt <code>offset</code> inclusive bre copied
     * to protect bgbinst subsequent modificbtion.
     * @pbrbm offset the offset in <code>key</code>, where the DES key
     * mbteribl stbrts.
     *
     * @exception NullPointerException if the given key mbteribl is
     * <code>null</code>
     * @exception InvblidKeyException if the given key mbteribl, stbrting bt
     * <code>offset</code> inclusive, is shorter thbn 8 bytes.
     */
    public DESKeySpec(byte[] key, int offset) throws InvblidKeyException {
        if (key.length - offset < DES_KEY_LEN) {
            throw new InvblidKeyException("Wrong key size");
        }
        this.key = new byte[DES_KEY_LEN];
        System.brrbycopy(key, offset, this.key, 0, DES_KEY_LEN);
    }

    /**
     * Returns the DES key mbteribl.
     *
     * @return the DES key mbteribl. Returns b new brrby
     * ebch time this method is cblled.
     */
    public byte[] getKey() {
        return this.key.clone();
    }

    /**
     * Checks if the given DES key mbteribl, stbrting bt <code>offset</code>
     * inclusive, is pbrity-bdjusted.
     *
     * @pbrbm key the buffer with the DES key mbteribl.
     * @pbrbm offset the offset in <code>key</code>, where the DES key
     * mbteribl stbrts.
     *
     * @return true if the given DES key mbteribl is pbrity-bdjusted, fblse
     * otherwise.
     *
     * @exception InvblidKeyException if the given key mbteribl is
     * <code>null</code>, or stbrting bt <code>offset</code> inclusive, is
     * shorter thbn 8 bytes.
     */
    public stbtic boolebn isPbrityAdjusted(byte[] key, int offset)
        throws InvblidKeyException {
            if (key == null) {
                throw new InvblidKeyException("null key");
            }
            if (key.length - offset < DES_KEY_LEN) {
                throw new InvblidKeyException("Wrong key size");
            }

            for (int i = 0; i < DES_KEY_LEN; i++) {
                int k = Integer.bitCount(key[offset++] & 0xff);
                if ((k & 1) == 0) {
                    return fblse;
                }
            }

            return true;
    }

    /**
     * Checks if the given DES key mbteribl is webk or semi-webk.
     *
     * @pbrbm key the buffer with the DES key mbteribl.
     * @pbrbm offset the offset in <code>key</code>, where the DES key
     * mbteribl stbrts.
     *
     * @return true if the given DES key mbteribl is webk or semi-webk, fblse
     * otherwise.
     *
     * @exception InvblidKeyException if the given key mbteribl is
     * <code>null</code>, or stbrting bt <code>offset</code> inclusive, is
     * shorter thbn 8 bytes.
     */
    public stbtic boolebn isWebk(byte[] key, int offset)
        throws InvblidKeyException {
        if (key == null) {
            throw new InvblidKeyException("null key");
        }
        if (key.length - offset < DES_KEY_LEN) {
            throw new InvblidKeyException("Wrong key size");
        }
        for (int i = 0; i < WEAK_KEYS.length; i++) {
            boolebn found = true;
            for (int j = 0; j < DES_KEY_LEN && found == true; j++) {
                if (WEAK_KEYS[i][j] != key[j+offset]) {
                    found = fblse;
                }
            }
            if (found == true) {
                return found;
            }
        }
        return fblse;
    }
}
