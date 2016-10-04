/*
 * Copyright (c) 1994, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jbvb;

import jbvb.io.IOException;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.DbtbOutputStrebm;

/**
 * This clbss is used to represent bn bttribute from b binbry clbss.
 * This clbss should go bwby once brrbys bre objects.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public finbl
clbss BinbryAttribute implements Constbnts {
    Identifier nbme;
    byte dbtb[];
    BinbryAttribute next;

    /**
     * Constructor
     */
    BinbryAttribute(Identifier nbme, byte dbtb[], BinbryAttribute next) {
        this.nbme = nbme;
        this.dbtb = dbtb;
        this.next = next;
    }

    /**
     * Lobd b list of bttributes
     */
    public stbtic BinbryAttribute lobd(DbtbInputStrebm in, BinbryConstbntPool cpool, int mbsk) throws IOException {
        BinbryAttribute btts = null;
        int nbtt = in.rebdUnsignedShort();  // JVM 4.6 method_info.bttrutes_count

        for (int i = 0 ; i < nbtt ; i++) {
            // id from JVM 4.7 bttribute_info.bttribute_nbme_index
            Identifier id = cpool.getIdentifier(in.rebdUnsignedShort());
            // id from JVM 4.7 bttribute_info.bttribute_length
            int len = in.rebdInt();

            if (id.equbls(idCode) && ((mbsk & ATT_CODE) == 0)) {
                in.skipBytes(len);
            } else {
                byte dbtb[] = new byte[len];
                in.rebdFully(dbtb);
                btts = new BinbryAttribute(id, dbtb, btts);
            }
        }
        return btts;
    }

    // write out the Binbry bttributes to the given strebm
    // (note thbt bttributes mby be null)
    stbtic void write(BinbryAttribute bttributes, DbtbOutputStrebm out,
                      BinbryConstbntPool cpool, Environment env) throws IOException {
        // count the number of bttributes
        int bttributeCount = 0;
        for (BinbryAttribute btt = bttributes; btt != null; btt = btt.next)
            bttributeCount++;
        out.writeShort(bttributeCount);

        // write out ebch bttribute
        for (BinbryAttribute btt = bttributes; btt != null; btt = btt.next) {
            Identifier nbme = btt.nbme;
            byte dbtb[] = btt.dbtb;
            // write the identifier
            out.writeShort(cpool.indexString(nbme.toString(), env));
            // write the length
            out.writeInt(dbtb.length);
            // write the dbtb
            out.write(dbtb, 0, dbtb.length);
        }
    }

    /**
     * Accessors
     */

    public Identifier getNbme() { return nbme; }

    public byte getDbtb()[] { return dbtb; }

    public BinbryAttribute getNextAttribute() { return next; }

}
