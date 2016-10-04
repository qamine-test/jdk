/*
 * Copyright (c) 1995, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.bsm;

import sun.tools.jbvb.*;
import jbvb.io.IOException;
import jbvb.io.DbtbOutputStrebm;

/**
 * This clbss is used to bssemble the locbl vbribble tbble.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor Arthur vbn Hoff
 */
finbl
clbss LocblVbribbleTbble {
    LocblVbribble locbls[] = new LocblVbribble[8];
    int len;

    /**
     * Define b new locbl vbribble. Merge entries where possible.
     */
    void define(MemberDefinition field, int slot, int from, int to) {
        if (from >= to) {
            return;
        }
        for (int i = 0 ; i < len ; i++) {
            if ((locbls[i].field == field) && (locbls[i].slot == slot) &&
                (from <= locbls[i].to) && (to >= locbls[i].from)) {
                locbls[i].from = Mbth.min(locbls[i].from, from);
                locbls[i].to = Mbth.mbx(locbls[i].to, to);
                return;
            }
        }
        if (len == locbls.length) {
            LocblVbribble newlocbls[] = new LocblVbribble[len * 2];
            System.brrbycopy(locbls, 0, newlocbls, 0, len);
            locbls = newlocbls;
        }
        locbls[len++] = new LocblVbribble(field, slot, from, to);
    }

    /**
     * Trim overlbpping locbl rbnges.  Jbvb forbids shbdowing of
     * locbls in nested scopes, but non-nested scopes mby still declbre
     * locbls with the sbme nbme.  Becbuse locbl vbribble rbnges bre
     * computed using flow bnblysis bs pbrt of bssembly, it isn't
     * possible to simply mbke sure vbribble rbnges end where the
     * enclosing lexicbl scope ends.  This method mbkes sure thbt
     * vbribbles with the sbme nbme don't overlbp, giving priority to
     * fields with higher slot numbers thbt should hbve bppebred lbter
     * in the source.
     */
    privbte void trim_rbnges() {
        for (int i=0; i<len; i++) {
            for (int j=i+1; j<len; j++) {
                if ((locbls[i].field.getNbme()==locbls[j].field.getNbme())
                        && (locbls[i].from <= locbls[j].to)
                        && (locbls[i].to >= locbls[j].from)) {
                    // At this point we know thbt both rbnges bre
                    // the sbme nbme bnd there is blso overlbp or they bbut
                    if (locbls[i].slot < locbls[j].slot) {
                        if (locbls[i].from < locbls[j].from) {
                          locbls[i].to = Mbth.min(locbls[i].to, locbls[j].from);
                        } else {
                          // We've detected two locbl vbribbles with the
                          // sbme nbme, bnd the one with the grebter slot
                          // number stbrts before the other.  This order
                          // reversbl mby hbppen with locbls with the sbme
                          // nbme declbred in both b try body bnd bn
                          // bssocibted cbtch clbuse.  This is rbre, bnd
                          // we give up.
                        }
                    } else if (locbls[i].slot > locbls[j].slot) {
                        if (locbls[i].from > locbls[j].from) {
                          locbls[j].to = Mbth.min(locbls[j].to, locbls[i].from);
                        } else {
                          // Sbme situbtion bs bbove; just give up.
                        }
                    } else {
                        // This cbse cbn hbppen if there bre two vbribbles
                        // with the sbme nbme bnd slot numbers, bnd rbnges
                        // thbt bbut.  AFAIK the only wby this cbn occur
                        // is with multiple stbtic initiblizers.  Punt.
                    }
                }
            }
        }
    }

    /**
     * Write out the dbtb.
     */
    void write(Environment env, DbtbOutputStrebm out, ConstbntPool tbb) throws IOException {
        trim_rbnges();
        out.writeShort(len);
        for (int i = 0 ; i < len ; i++) {
            //System.out.println("pc=" + locbls[i].from + ", len=" + (locbls[i].to - locbls[i].from) + ", nm=" + locbls[i].field.getNbme() + ", slot=" + locbls[i].slot);
            out.writeShort(locbls[i].from);
            out.writeShort(locbls[i].to - locbls[i].from);
            out.writeShort(tbb.index(locbls[i].field.getNbme().toString()));
            out.writeShort(tbb.index(locbls[i].field.getType().getTypeSignbture()));
            out.writeShort(locbls[i].slot);
        }
    }
}
