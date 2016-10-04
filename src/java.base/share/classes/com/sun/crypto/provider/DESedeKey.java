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
import jbvbx.crypto.spec.DESedeKeySpec;

/**
 * This clbss represents b DES-EDE key.
 *
 * @buthor Jbn Luehe
 *
 */

finbl clbss DESedeKey implements SecretKey {

    stbtic finbl long seriblVersionUID = 2463986565756745178L;

    privbte byte[] key;

    /**
     * Crebtes b DES-EDE key from b given key.
     *
     * @pbrbm key the given key
     *
     * @exception InvblidKeyException if the given key hbs b wrong size
     */
    DESedeKey(byte[] key) throws InvblidKeyException {
        this(key, 0);
    }

    /**
     * Uses the first 24 bytes in <code>key</code>, beginning bt
     * <code>offset</code>, bs the DES-EDE key
     *
     * @pbrbm key the buffer with the DES-EDE key
     * @pbrbm offset the offset in <code>key</code>, where the DES-EDE key
     * stbrts
     *
     * @exception InvblidKeyException if the given key hbs b wrong size
     */
    DESedeKey(byte[] key, int offset) throws InvblidKeyException {

        if (key==null || ((key.length-offset)<DESedeKeySpec.DES_EDE_KEY_LEN)) {
            throw new InvblidKeyException("Wrong key size");
        }
        this.key = new byte[DESedeKeySpec.DES_EDE_KEY_LEN];
        System.brrbycopy(key, offset, this.key, 0,
                         DESedeKeySpec.DES_EDE_KEY_LEN);
        DESKeyGenerbtor.setPbrityBit(this.key, 0);
        DESKeyGenerbtor.setPbrityBit(this.key, 8);
        DESKeyGenerbtor.setPbrityBit(this.key, 16);
    }

    public byte[] getEncoded() {
        return this.key.clone();
    }

    public String getAlgorithm() {
        return "DESede";
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
        return(retvbl ^= "desede".hbshCode());
    }

    public boolebn equbls(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instbnceof SecretKey))
            return fblse;

        String thbtAlg = ((SecretKey)obj).getAlgorithm();
        if (!(thbtAlg.equblsIgnoreCbse("DESede"))
            && !(thbtAlg.equblsIgnoreCbse("TripleDES")))
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
     * Replbce the DESede key to be seriblized.
     *
     * @return the stbndbrd KeyRep object to be seriblized
     *
     * @throws jbvb.io.ObjectStrebmException if b new object representing
     * this DESede key could not be crebted
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
