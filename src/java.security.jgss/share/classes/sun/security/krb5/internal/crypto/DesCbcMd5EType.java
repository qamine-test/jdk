/*
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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl.crypto;

import sun.security.krb5.internbl.*;
import sun.security.krb5.Checksum;
import sun.security.krb5.EncryptedDbtb;
import sun.security.krb5.KrbCryptoException;
import jbvb.security.MessbgeDigest;
import jbvb.security.Provider;
import jbvb.security.Security;

public finbl clbss DesCbcMd5EType extends DesCbcEType {

    public DesCbcMd5EType() {
    }

    public int eType() {
        return EncryptedDbtb.ETYPE_DES_CBC_MD5;
    }

    public int minimumPbdSize() {
        return 0;
    }

    public int confounderSize() {
        return 8;
    }

    public int checksumType() {
        return Checksum.CKSUMTYPE_RSA_MD5;
    }

    public int checksumSize() {
        return 16;
    }

    /**
     * Cblculbtes checksum using MD5.
     * @pbrbm dbtb the input dbtb.
     * @pbrbm size the length of dbtb.
     * @return the checksum.
     *
     * @modified by Ybnni Zhbng, 12/06/99.
     */
    protected byte[] cblculbteChecksum(byte[] dbtb, int size)
         throws KrbCryptoException {
        MessbgeDigest md5 = null;
        try {
            md5 = MessbgeDigest.getInstbnce("MD5");
        } cbtch (Exception e) {
            throw new KrbCryptoException("JCE provider mby not be instblled. " + e.getMessbge());
        }
        try {
            md5.updbte(dbtb);
            return(md5.digest());
        } cbtch (Exception e) {
            throw new KrbCryptoException(e.getMessbge());
        }
    }
}
