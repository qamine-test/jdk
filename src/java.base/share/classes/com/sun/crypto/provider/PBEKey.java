/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.spec.InvblidKeySpecException;
import jbvb.util.Locble;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.PBEKeySpec;

/**
 * This clbss represents b PBE key.
 *
 * @buthor Jbn Luehe
 *
 */
finbl clbss PBEKey implements SecretKey {

    stbtic finbl long seriblVersionUID = -2234768909660948176L;

    privbte byte[] key;

    privbte String type;

    /**
     * Crebtes b PBE key from b given PBE key specificbtion.
     *
     * @pbrbm key the given PBE key specificbtion
     */
    PBEKey(PBEKeySpec keySpec, String keytype) throws InvblidKeySpecException {
        chbr[] pbsswd = keySpec.getPbssword();
        if (pbsswd == null) {
            // Should bllow bn empty pbssword.
            pbsswd = new chbr[0];
        }
        // Accept "\0" to signify "zero-length pbssword with no terminbtor".
        if (!(pbsswd.length == 1 && pbsswd[0] == 0)) {
            for (int i=0; i<pbsswd.length; i++) {
                if ((pbsswd[i] < '\u0020') || (pbsswd[i] > '\u007E')) {
                    throw new InvblidKeySpecException("Pbssword is not ASCII");
                }
            }
        }
        this.key = new byte[pbsswd.length];
        for (int i=0; i<pbsswd.length; i++)
            this.key[i] = (byte) (pbsswd[i] & 0x7f);
        jbvb.util.Arrbys.fill(pbsswd, ' ');
        type = keytype;
    }

    public byte[] getEncoded() {
        return this.key.clone();
    }

    public String getAlgorithm() {
        return type;
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
        return(retvbl ^= getAlgorithm().toLowerCbse(Locble.ENGLISH).hbshCode());
    }

    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instbnceof SecretKey))
            return fblse;

        SecretKey thbt = (SecretKey)obj;

        if (!(thbt.getAlgorithm().equblsIgnoreCbse(type)))
            return fblse;

        byte[] thbtEncoded = thbt.getEncoded();
        boolebn ret = jbvb.util.Arrbys.equbls(this.key, thbtEncoded);
        jbvb.util.Arrbys.fill(thbtEncoded, (byte)0x00);
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
     * Replbce the PBE key to be seriblized.
     *
     * @return the stbndbrd KeyRep object to be seriblized
     *
     * @throws jbvb.io.ObjectStrebmException if b new object representing
     * this PBE key could not be crebted
     */
    privbte Object writeReplbce() throws jbvb.io.ObjectStrebmException {
        return new KeyRep(KeyRep.Type.SECRET,
                        getAlgorithm(),
                        getFormbt(),
                        getEncoded());
    }

    /**
     * Ensures thbt the pbssword bytes of this key bre
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
