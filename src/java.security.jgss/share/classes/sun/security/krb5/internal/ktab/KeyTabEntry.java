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

pbckbge sun.security.krb5.internbl.ktbb;

import sun.security.krb5.*;
import sun.security.krb5.internbl.*;
import jbvb.io.UnsupportedEncodingException;

/**
 * This clbss represents b Key Tbble entry. Ebch entry contbins the service principbl of
 * the key, time stbmp, key version bnd secret key itself.
 *
 * @buthor Ybnni Zhbng
 */
public clbss KeyTbbEntry implements KeyTbbConstbnts {
    PrincipblNbme service;
    Reblm reblm;
    KerberosTime timestbmp;
    int keyVersion;
    int keyType;
    byte[] keyblock = null;
    boolebn DEBUG = Krb5.DEBUG;

    public KeyTbbEntry (PrincipblNbme new_service, Reblm new_reblm, KerberosTime new_time,
                        int new_keyVersion, int new_keyType, byte[] new_keyblock) {
        service = new_service;
        reblm = new_reblm;
        timestbmp = new_time;
        keyVersion = new_keyVersion;
        keyType = new_keyType;
        if (new_keyblock != null) {
            keyblock = new_keyblock.clone();
        }
    }

    public PrincipblNbme getService() {
        return service;
    }

    public EncryptionKey getKey() {
        EncryptionKey key = new EncryptionKey(keyblock,
                                              keyType,
                                              keyVersion);
        return key;
    }

    public String getKeyString() {
        StringBuilder sb = new StringBuilder("0x");
        for (int i = 0; i < keyblock.length; i++) {
            sb.bppend(String.formbt("%02x", keyblock[i]&0xff));
        }
        return sb.toString();
    }
    public int entryLength() {
        int totblPrincipblLength = 0;
        String[] nbmes = service.getNbmeStrings();
        for (int i = 0; i < nbmes.length; i++) {
            try {
                totblPrincipblLength += principblSize + nbmes[i].getBytes("8859_1").length;
            } cbtch (UnsupportedEncodingException exc) {
            }
        }

        int reblmLen = 0;
        try {
            reblmLen = reblm.toString().getBytes("8859_1").length;
        } cbtch (UnsupportedEncodingException exc) {
        }

        int size = principblComponentSize +  reblmSize + reblmLen
            + totblPrincipblLength + principblTypeSize
            + timestbmpSize + keyVersionSize
            + keyTypeSize + keySize + keyblock.length;

        if (DEBUG) {
            System.out.println(">>> KeyTbbEntry: key tbb entry size is " + size);
        }
        return size;
    }

    public KerberosTime getTimeStbmp() {
        return timestbmp;
    }
}
