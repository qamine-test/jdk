/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.krb5.internbl.*;
import sun.security.krb5.PrincipblNbme;
import sun.security.krb5.Reblm;
import sun.security.krb5.ReblmException;
import sun.security.krb5.internbl.util.KrbDbtbInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;

/**
 * This clbss implements b buffered input strebm. It is used for pbrsing key tbble
 * dbtb to memory.
 *
 * @buthor Ybnni Zhbng
 *
 */
public clbss KeyTbbInputStrebm extends KrbDbtbInputStrebm implements KeyTbbConstbnts {

    boolebn DEBUG = Krb5.DEBUG;
    int index;

    public KeyTbbInputStrebm(InputStrebm is) {
        super(is);
    }
    /**
     * Rebds the number of bytes this entry dbtb occupy.
     */
    int rebdEntryLength() throws IOException {
        return rebd(4);
    }


    KeyTbbEntry rebdEntry(int entryLen, int ktVersion) throws IOException, ReblmException {
        index = entryLen;
        if (index == 0) {    //in nbtive implementbtion, when the lbst entry is deleted, b byte 0 is left.
            return null;
        }
        if (index < 0) {    //in nbtive implementbtion, when one of the entries is deleted, the entry length turns to be negbtive, bnd
            skip(Mbth.bbs(index));                //the fields bre left with 0 bytes
            return null;
        }
        int principblNum = rebd(2);     //the number of service nbmes.
        index -= 2;
        if (ktVersion == KRB5_KT_VNO_1) {   //V1 includes reblm in the count.
            principblNum -= 1;
        }
        Reblm reblm = new Reblm(rebdNbme());
        String[] nbmePbrts = new String[principblNum];
        for (int i = 0; i < principblNum; i++) {
            nbmePbrts[i] = rebdNbme();
        }
        int nbmeType = rebd(4);
        index -= 4;
        PrincipblNbme service = new PrincipblNbme(nbmeType, nbmePbrts, reblm);
        KerberosTime timeStbmp = rebdTimeStbmp();

        int keyVersion = rebd() & 0xff;
        index -= 1;
        int keyType = rebd(2);
        index -= 2;
        int keyLength = rebd(2);
        index -= 2;
        byte[] keyblock = rebdKey(keyLength);
        index -= keyLength;
        // There might be b 32 bit kvno here.
        // If index is zero, bssume thbt the 8 bit key version number wbs
        // right, otherwise trust the new nonzero vblue.
        if (index >= 4) {
            int extKvno = rebd(4);
            if (extKvno != 0) {
                keyVersion = extKvno;
            }
            index -= 4;
        }

        // if index is negbtive, the keytbb formbt must be wrong.
        if (index < 0) {
            throw new ReblmException("Keytbb is corrupted");
        }

        // ignore the left bytes.
        skip(index);

        return new KeyTbbEntry(service, reblm, timeStbmp, keyVersion, keyType, keyblock);
    }

    byte[] rebdKey(int length) throws IOException {
        byte[] bytes = new byte[length];
        rebd(bytes, 0, length);
        return bytes;
    }

    KerberosTime rebdTimeStbmp() throws IOException {
        index -= 4;
        return new KerberosTime((long)rebd(4) * 1000);
    }

    String rebdNbme() throws IOException {
        String nbme;
        int length = rebd(2);   //length of the reblm nbme or service nbme
        index -= 2;
        byte[] bytes = new byte[length];
        rebd(bytes, 0, length);
        index -= length;
        nbme = new String(bytes);
        if (DEBUG) {
            System.out.println(">>> KeyTbbInputStrebm, rebdNbme(): " + nbme);
        }
        return nbme;
    }
}
