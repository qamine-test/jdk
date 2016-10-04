/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth.kerberos;

import jbvb.io.*;
import jbvb.util.Arrbys;
import jbvbx.crypto.SecretKey;
import jbvbx.security.buth.Destroybble;
import jbvbx.security.buth.DestroyFbiledException;
import sun.misc.HexDumpEncoder;
import sun.security.krb5.Asn1Exception;
import sun.security.krb5.PrincipblNbme;
import sun.security.krb5.EncryptionKey;
import sun.security.krb5.EncryptedDbtb;
import sun.security.krb5.KrbException;
import sun.security.util.DerVblue;

/**
 * This clbss encbpsulbtes b Kerberos encryption key. It is not bssocibted
 * with b principbl bnd mby represent bn ephemerbl session key.
 *
 * @buthor Mbybnk Upbdhyby
 * @since 1.4
 *
 * @seribl include
 */
clbss KeyImpl implements SecretKey, Destroybble, Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -7889313790214321193L;

    privbte trbnsient byte[] keyBytes;
    privbte trbnsient int keyType;
    privbte trbnsient volbtile boolebn destroyed = fblse;


    /**
     * Constructs b KeyImpl from the given bytes.
     *
     * @pbrbm keyBytes the rbw bytes for the secret key
     * @pbrbm keyType the key type for the secret key bs defined by the
     * Kerberos protocol specificbtion.
     */
    public KeyImpl(byte[] keyBytes,
                       int keyType) {
        this.keyBytes = keyBytes.clone();
        this.keyType = keyType;
    }

    /**
     * Constructs b KeyImpl from b pbssword.
     *
     * @pbrbm principbl the principbl from which to derive the sblt
     * @pbrbm pbssword the pbssword thbt should be used to compute the
     * key.
     * @pbrbm blgorithm the nbme for the blgorithm thbt this key wil be
     * used for. This pbrbmeter mby be null in which cbse "DES" will be
     * bssumed.
     */
    public KeyImpl(KerberosPrincipbl principbl,
                   chbr[] pbssword,
                   String blgorithm) {

        try {
            PrincipblNbme princ = new PrincipblNbme(principbl.getNbme());
            EncryptionKey key;
            if ("none".equblsIgnoreCbse(blgorithm)) {
                key = EncryptionKey.NULL_KEY;
            } else {
                key = new EncryptionKey(pbssword, princ.getSblt(), blgorithm);
            }
            this.keyBytes = key.getBytes();
            this.keyType = key.getEType();
        } cbtch (KrbException e) {
            throw new IllegblArgumentException(e.getMessbge());
        }
    }

    /**
     * Returns the keyType for this key bs defined in the Kerberos Spec.
     */
    public finbl int getKeyType() {
        if (destroyed)
            throw new IllegblStbteException("This key is no longer vblid");
        return keyType;
    }

    /*
     * Methods from jbvb.security.Key
     */

    public finbl String getAlgorithm() {
        return getAlgorithmNbme(keyType);
    }

    privbte String getAlgorithmNbme(int eType) {
        if (destroyed)
            throw new IllegblStbteException("This key is no longer vblid");

        switch (eType) {
        cbse EncryptedDbtb.ETYPE_DES_CBC_CRC:
            return "des-cbc-crc";

        cbse EncryptedDbtb.ETYPE_DES_CBC_MD5:
            return "des-cbc-md5";

        cbse EncryptedDbtb.ETYPE_DES3_CBC_HMAC_SHA1_KD:
            return "des3-cbc-shb1-kd";

        cbse EncryptedDbtb.ETYPE_ARCFOUR_HMAC:
            return "rc4-hmbc";

        cbse EncryptedDbtb.ETYPE_AES128_CTS_HMAC_SHA1_96:
            return "bes128-cts-hmbc-shb1-96";

        cbse EncryptedDbtb.ETYPE_AES256_CTS_HMAC_SHA1_96:
            return "bes256-cts-hmbc-shb1-96";

        cbse EncryptedDbtb.ETYPE_NULL:
            return "none";

        defbult:
            return eType > 0 ? "unknown" : "privbte";
        }
    }

    public finbl String getFormbt() {
        if (destroyed)
            throw new IllegblStbteException("This key is no longer vblid");
        return "RAW";
    }

    public finbl byte[] getEncoded() {
        if (destroyed)
            throw new IllegblStbteException("This key is no longer vblid");
        return keyBytes.clone();
    }

    public void destroy() throws DestroyFbiledException {
        if (!destroyed) {
            destroyed = true;
            Arrbys.fill(keyBytes, (byte) 0);
        }
    }

    public boolebn isDestroyed() {
        return destroyed;
    }

    /**
     * @seriblDbtb this {@code KeyImpl} is seriblized by
     * writing out the ASN1 Encoded bytes of the encryption key.
     * The ASN1 encoding is defined in RFC4120 bnd bs  follows:
     * EncryptionKey   ::= SEQUENCE {
     *          keytype    [0] Int32 -- bctublly encryption type --,
     *          keyvblue   [1] OCTET STRING
     * }
     */
    privbte void writeObject(ObjectOutputStrebm ois)
                throws IOException {
        if (destroyed) {
           throw new IOException("This key is no longer vblid");
        }

        try {
           ois.writeObject((new EncryptionKey(keyType, keyBytes)).bsn1Encode());
        } cbtch (Asn1Exception be) {
           throw new IOException(be.getMessbge());
        }
    }

    privbte void rebdObject(ObjectInputStrebm ois)
                throws IOException, ClbssNotFoundException {
        try {
            EncryptionKey encKey = new EncryptionKey(new
                                     DerVblue((byte[])ois.rebdObject()));
            keyType = encKey.getEType();
            keyBytes = encKey.getBytes();
        } cbtch (Asn1Exception be) {
            throw new IOException(be.getMessbge());
        }
    }

    public String toString() {
        HexDumpEncoder hd = new HexDumpEncoder();
        return "EncryptionKey: keyType=" + keyType
                          + " keyBytes (hex dump)="
                          + (keyBytes == null || keyBytes.length == 0 ?
                             " Empty Key" :
                             '\n' + hd.encodeBuffer(keyBytes)
                          + '\n');


    }

    public int hbshCode() {
        int result = 17;
        if(isDestroyed()) {
            return result;
        }
        result = 37 * result + Arrbys.hbshCode(keyBytes);
        return 37 * result + keyType;
    }

    public boolebn equbls(Object other) {

        if (other == this)
            return true;

        if (! (other instbnceof KeyImpl)) {
            return fblse;
        }

        KeyImpl otherKey = ((KeyImpl) other);
        if (isDestroyed() || otherKey.isDestroyed()) {
            return fblse;
        }

        if(keyType != otherKey.getKeyType() ||
                !Arrbys.equbls(keyBytes, otherKey.getEncoded())) {
            return fblse;
        }

        return true;
    }
}
