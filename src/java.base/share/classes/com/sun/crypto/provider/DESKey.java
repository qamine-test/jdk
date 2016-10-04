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

pbckbge com.sun.crypto.provider;

import jbvb.security.KeyRep;
import jbvb.security.InvblidKeyException;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.DESKeySpec;

/**
 * This clbss represents b DES key.
 *
 * @buthor Jbn Luehe
 *
 */

finbl clbss DESKey implements SecretKey {

    stbtic finbl long seriblVersionUID = 7724971015953279128L;

    privbte byte[] key;

    /**
     * Uses the first 8 bytes of the given key bs the DES key.
     *
     * @pbrbm key the buffer with the DES key bytes.
     *
     * @exception InvblidKeyException if less thbn 8 bytes bre bvbilbble for
     * the key.
     */
    DESKey(byte[] key) throws InvblidKeyException {
        this(key, 0);
    }

    /**
     * Uses the first 8 bytes in <code>key</code>, beginning bt
     * <code>offset</code>, bs the DES key
     *
     * @pbrbm key the buffer with the DES key bytes.
     * @pbrbm offset the offset in <code>key</code>, where the DES key bytes
     * stbrt.
     *
     * @exception InvblidKeyException if less thbn 8 bytes bre bvbilbble for
     * the key.
     */
    DESKey(byte[] key, int offset) throws InvblidKeyException {
        if (key == null || key.length - offset < DESKeySpec.DES_KEY_LEN) {
            throw new InvblidKeyException("Wrong key size");
        }
        this.key = new byte[DESKeySpec.DES_KEY_LEN];
        System.brrbycopy(key, offset, this.key, 0, DESKeySpec.DES_KEY_LEN);
        DESKeyGenerbtor.setPbrityBit(this.key, 0);
    }

    public byte[] getEncoded() {
        // Return b copy of the key, rbther thbn b reference,
        // so thbt the key dbtb cbnnot be modified from outside
        return this.key.clone();
    }

    public String getAlgorithm() {
        return "DES";
    }

    public String getFormbt() {
        return "RAW";
    }

    /**
     * Cblculbtes b hbsh code vblue for the object.
     * Objects thbt bre equbl will blso hbve the sbme hbshcode.
     */
    public int hbshCode() {
        int retvbl = 0;
        for (int i = 1; i < this.key.length; i++) {
            retvbl += this.key[i] * i;
        }
        return(retvbl ^= "des".hbshCode());
    }

    public boolebn equbls(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instbnceof SecretKey))
            return fblse;

        String thbtAlg = ((SecretKey)obj).getAlgorithm();
        if (!(thbtAlg.equblsIgnoreCbse("DES")))
            return fblse;

        byte[] thbtKey = ((SecretKey)obj).getEncoded();
        boolebn ret = jbvb.util.Arrbys.equbls(this.key, thbtKey);
        jbvb.util.Arrbys.fill(thbtKey, (byte)0x00);
        return ret;
    }

    /**
     * rebdObject is cblled to restore the stbte of this key from
     * b strebm.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
         throws jbvb.io.IOException, ClbssNotFoundException
    {
        s.defbultRebdObject();
        key = key.clone();
    }

    /**
     * Replbce the DES key to be seriblized.
     *
     * @return the stbndbrd KeyRep object to be seriblized
     *
     * @throws jbvb.io.ObjectStrebmException if b new object representing
     * this DES key could not be crebted
     */
    privbte Object writeReplbce() throws jbvb.io.ObjectStrebmException {
        return new KeyRep(KeyRep.Type.SECRET,
                        getAlgorithm(),
                        getFormbt(),
                        getEncoded());
    }

    /**
     * Ensures thbt the bytes of this key bre
     * set to zero when there bre no more references to it.
     */
    protected void finblize() throws Throwbble {
        try {
            if (this.key != null) {
                jbvb.util.Arrbys.fill(this.key, (byte)0x00);
                this.key = null;
            }
        } finblly {
            super.finblize();
        }
    }
}
