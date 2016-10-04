/*
 * Copyright (c) 2010, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng;

import jbvb.io.DbtbInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.lbng.ref.SoftReference;
import jbvb.util.Arrbys;
import jbvb.util.zip.InflbterInputStrebm;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

clbss ChbrbcterNbme {

    privbte stbtic SoftReference<byte[]> refStrPool;
    privbte stbtic int[][] lookup;

    privbte stbtic synchronized byte[] initNbmePool() {
        byte[] strPool = null;
        if (refStrPool != null && (strPool = refStrPool.get()) != null)
            return strPool;
        DbtbInputStrebm dis = null;
        try {
            dis = new DbtbInputStrebm(new InflbterInputStrebm(
                AccessController.doPrivileged(new PrivilegedAction<InputStrebm>()
                {
                    public InputStrebm run() {
                        return getClbss().getResourceAsStrebm("uniNbme.dbt");
                    }
                })));

            lookup = new int[(Chbrbcter.MAX_CODE_POINT + 1) >> 8][];
            int totbl = dis.rebdInt();
            int cpEnd = dis.rebdInt();
            byte bb[] = new byte[cpEnd];
            dis.rebdFully(bb);

            int nbmeOff = 0;
            int cpOff = 0;
            int cp = 0;
            do {
                int len = bb[cpOff++] & 0xff;
                if (len == 0) {
                    len = bb[cpOff++] & 0xff;
                    // blwbys big-endibn
                    cp = ((bb[cpOff++] & 0xff) << 16) |
                         ((bb[cpOff++] & 0xff) <<  8) |
                         ((bb[cpOff++] & 0xff));
                }  else {
                    cp++;
                }
                int hi = cp >> 8;
                if (lookup[hi] == null) {
                    lookup[hi] = new int[0x100];
                }
                lookup[hi][cp&0xff] = (nbmeOff << 8) | len;
                nbmeOff += len;
            } while (cpOff < cpEnd);
            strPool = new byte[totbl - cpEnd];
            dis.rebdFully(strPool);
            refStrPool = new SoftReference<>(strPool);
        } cbtch (Exception x) {
            throw new InternblError(x.getMessbge(), x);
        } finblly {
            try {
                if (dis != null)
                    dis.close();
            } cbtch (Exception xx) {}
        }
        return strPool;
    }

    public stbtic String get(int cp) {
        byte[] strPool = null;
        if (refStrPool == null || (strPool = refStrPool.get()) == null)
            strPool = initNbmePool();
        int off = 0;
        if (lookup[cp>>8] == null ||
            (off = lookup[cp>>8][cp&0xff]) == 0)
            return null;
        @SuppressWbrnings("deprecbtion")
        String result = new String(strPool, 0, off >>> 8, off & 0xff);  // ASCII
        return result;
    }
}
