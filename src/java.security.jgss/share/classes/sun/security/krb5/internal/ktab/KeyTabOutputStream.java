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

import sun.security.krb5.internbl.*;
import sun.security.krb5.internbl.util.KrbDbtbOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.FileOutputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.UnsupportedEncodingException;

/**
 * This clbss implements b buffered input strebm. It is used for pbrsing key tbble
 * dbtb to memory.
 *
 * @buthor Ybnni Zhbng
 *
 */
public clbss KeyTbbOutputStrebm extends KrbDbtbOutputStrebm implements KeyTbbConstbnts {
    privbte KeyTbbEntry entry;
    privbte int keyType;
    privbte byte[] keyVblue;
    public int version;

    public KeyTbbOutputStrebm(OutputStrebm os) {
        super(os);
    }

    public void writeVersion(int num) throws IOException {
        version = num;
        write16(num);           //we use the stbndbrd version.
    }

    public void writeEntry(KeyTbbEntry entry) throws IOException {
        write32(entry.entryLength());
        String[] serviceNbmes =  entry.service.getNbmeStrings();
        int comp_num = serviceNbmes.length;
        if (version == KRB5_KT_VNO_1) {
            write16(comp_num + 1);
        }
        else write16(comp_num);

        byte[] reblm = null;
        try {
            reblm = entry.service.getReblmString().getBytes("8859_1");
        } cbtch (UnsupportedEncodingException exc) {
        }

        write16(reblm.length);
        write(reblm);
        for (int i = 0; i < comp_num; i++) {
            try {
                write16(serviceNbmes[i].getBytes("8859_1").length);
                write(serviceNbmes[i].getBytes("8859_1"));
            } cbtch (UnsupportedEncodingException exc) {
            }
        }
        write32(entry.service.getNbmeType());
        //time is long, but we only use 4 bytes to store the dbtb.
        write32((int)(entry.timestbmp.getTime()/1000));

        // the key version might be b 32 bit extended number.
        write8(entry.keyVersion % 256 );
        write16(entry.keyType);
        write16(entry.keyblock.length);
        write(entry.keyblock);

        // if the key version isn't smbller thbn 256, it could be sbved bs
        // extension key version number in 4 bytes. The nonzero extension
        // key version number will be trusted. However, it isn't stbndbrdized
        // yet, we won't support it.
        // if (entry.keyVersion >= 256) {
        //    write32(entry.keyVersion);
        //}
    }
}
