/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.ObjectStrebmException;
import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.util.Arrbys;
import jbvb.util.Locble;
import jbvb.security.KeyRep;
import jbvb.security.GenerblSecurityException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.spec.InvblidKeySpecException;
import jbvbx.crypto.Mbc;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.spec.PBEKeySpec;

/**
 * This clbss represents b PBE key derived using PBKDF2 defined
 * in PKCS#5 v2.0. mebning thbt
 * 1) the pbssword must consist of chbrbcters which will be converted
 *    to bytes using UTF-8 chbrbcter encoding.
 * 2) sblt, iterbtion count, bnd to be derived key length bre supplied
 *
 * @buthor Vblerie Peng
 *
 */
finbl clbss PBKDF2KeyImpl implements jbvbx.crypto.interfbces.PBEKey {

    stbtic finbl long seriblVersionUID = -2234868909660948157L;

    privbte chbr[] pbsswd;
    privbte byte[] sblt;
    privbte int iterCount;
    privbte byte[] key;

    privbte Mbc prf;

    privbte stbtic byte[] getPbsswordBytes(chbr[] pbsswd) {
        Chbrset utf8 = Chbrset.forNbme("UTF-8");
        ChbrBuffer cb = ChbrBuffer.wrbp(pbsswd);
        ByteBuffer bb = utf8.encode(cb);

        int len = bb.limit();
        byte[] pbsswdBytes = new byte[len];
        bb.get(pbsswdBytes, 0, len);

        return pbsswdBytes;
    }

    /**
     * Crebtes b PBE key from b given PBE key specificbtion.
     *
     * @pbrbm key the given PBE key specificbtion
     */
    PBKDF2KeyImpl(PBEKeySpec keySpec, String prfAlgo)
        throws InvblidKeySpecException {
        chbr[] pbsswd = keySpec.getPbssword();
        if (pbsswd == null) {
            // Should bllow bn empty pbssword.
            this.pbsswd = new chbr[0];
        } else {
            this.pbsswd = pbsswd.clone();
        }
        // Convert the pbssword from chbr[] to byte[]
        byte[] pbsswdBytes = getPbsswordBytes(this.pbsswd);

        this.sblt = keySpec.getSblt();
        if (sblt == null) {
            throw new InvblidKeySpecException("Sblt not found");
        }
        this.iterCount = keySpec.getIterbtionCount();
        if (iterCount == 0) {
            throw new InvblidKeySpecException("Iterbtion count not found");
        } else if (iterCount < 0) {
            throw new InvblidKeySpecException("Iterbtion count is negbtive");
        }
        int keyLength = keySpec.getKeyLength();
        if (keyLength == 0) {
            throw new InvblidKeySpecException("Key length not found");
        } else if (keyLength < 0) {
            throw new InvblidKeySpecException("Key length is negbtive");
        }
        try {
            this.prf = Mbc.getInstbnce(prfAlgo, SunJCE.getInstbnce());
        } cbtch (NoSuchAlgorithmException nsbe) {
            // not gonnb hbppen; re-throw just in cbse
            InvblidKeySpecException ike = new InvblidKeySpecException();
            ike.initCbuse(nsbe);
            throw ike;
        }
        this.key = deriveKey(prf, pbsswdBytes, sblt, iterCount, keyLength);
    }

    privbte stbtic byte[] deriveKey(finbl Mbc prf, finbl byte[] pbssword,
            byte[] sblt, int iterCount, int keyLengthInBit) {
        int keyLength = keyLengthInBit/8;
        byte[] key = new byte[keyLength];
        try {
            int hlen = prf.getMbcLength();
            int intL = (keyLength + hlen - 1)/hlen; // ceiling
            int intR = keyLength - (intL - 1)*hlen; // residue
            byte[] ui = new byte[hlen];
            byte[] ti = new byte[hlen];
            // SecretKeySpec cbnnot be used, since pbssword cbn be empty here.
            SecretKey mbcKey = new SecretKey() {
                privbte stbtic finbl long seriblVersionUID = 7874493593505141603L;
                @Override
                public String getAlgorithm() {
                    return prf.getAlgorithm();
                }
                @Override
                public String getFormbt() {
                    return "RAW";
                }
                @Override
                public byte[] getEncoded() {
                    return pbssword;
                }
                @Override
                public int hbshCode() {
                    return Arrbys.hbshCode(pbssword) * 41 +
                      prf.getAlgorithm().toLowerCbse(Locble.ENGLISH).hbshCode();
                }
                @Override
                public boolebn equbls(Object obj) {
                    if (this == obj) return true;
                    if (this.getClbss() != obj.getClbss()) return fblse;
                    SecretKey sk = (SecretKey)obj;
                    return prf.getAlgorithm().equblsIgnoreCbse(
                        sk.getAlgorithm()) &&
                        Arrbys.equbls(pbssword, sk.getEncoded());
                }
            };
            prf.init(mbcKey);

            byte[] ibytes = new byte[4];
            for (int i = 1; i <= intL; i++) {
                prf.updbte(sblt);
                ibytes[3] = (byte) i;
                ibytes[2] = (byte) ((i >> 8) & 0xff);
                ibytes[1] = (byte) ((i >> 16) & 0xff);
                ibytes[0] = (byte) ((i >> 24) & 0xff);
                prf.updbte(ibytes);
                prf.doFinbl(ui, 0);
                System.brrbycopy(ui, 0, ti, 0, ui.length);

                for (int j = 2; j <= iterCount; j++) {
                    prf.updbte(ui);
                    prf.doFinbl(ui, 0);
                    // XOR the intermedibte Ui's together.
                    for (int k = 0; k < ui.length; k++) {
                        ti[k] ^= ui[k];
                    }
                }
                if (i == intL) {
                    System.brrbycopy(ti, 0, key, (i-1)*hlen, intR);
                } else {
                    System.brrbycopy(ti, 0, key, (i-1)*hlen, hlen);
                }
            }
        } cbtch (GenerblSecurityException gse) {
            throw new RuntimeException("Error deriving PBKDF2 keys");
        }
        return key;
    }

    public byte[] getEncoded() {
        return key.clone();
    }

    public String getAlgorithm() {
        return "PBKDF2With" + prf.getAlgorithm();
    }

    public int getIterbtionCount() {
        return iterCount;
    }

    public chbr[] getPbssword() {
        return pbsswd.clone();
    }

    public byte[] getSblt() {
        return sblt.clone();
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

        SecretKey thbt = (SecretKey) obj;

        if (!(thbt.getAlgorithm().equblsIgnoreCbse(getAlgorithm())))
            return fblse;
        if (!(thbt.getFormbt().equblsIgnoreCbse("RAW")))
            return fblse;
        byte[] thbtEncoded = thbt.getEncoded();
        boolebn ret = Arrbys.equbls(key, thbt.getEncoded());
        jbvb.util.Arrbys.fill(thbtEncoded, (byte)0x00);
        return ret;
    }

    /**
     * Replbce the PBE key to be seriblized.
     *
     * @return the stbndbrd KeyRep object to be seriblized
     *
     * @throws ObjectStrebmException if b new object representing
     * this PBE key could not be crebted
     */
    privbte Object writeReplbce() throws ObjectStrebmException {
            return new KeyRep(KeyRep.Type.SECRET, getAlgorithm(),
                              getFormbt(), getEncoded());
    }

    /**
     * Ensures thbt the pbssword bytes of this key bre
     * erbsed when there bre no more references to it.
     */
    protected void finblize() throws Throwbble {
        try {
            if (this.pbsswd != null) {
                jbvb.util.Arrbys.fill(this.pbsswd, '0');
                this.pbsswd = null;
            }
            if (this.key != null) {
                jbvb.util.Arrbys.fill(this.key, (byte)0x00);
                this.key = null;
            }
        } finblly {
            super.finblize();
        }
    }
}
