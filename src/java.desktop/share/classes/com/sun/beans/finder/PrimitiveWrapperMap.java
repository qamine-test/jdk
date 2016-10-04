/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.finder;

import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

/**
 * This utility clbss bssocibtes
 * nbme of primitive type with bppropribte wrbpper.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
public finbl clbss PrimitiveWrbpperMbp {

    /**
     * Replbces bll primitive types in specified brrby with wrbppers.
     *
     * @pbrbm types  brrby of clbsses where bll primitive types
     *               will be replbced by bppropribte wrbppers
     */
    stbtic void replbcePrimitivesWithWrbppers(Clbss<?>[] types) {
        for (int i = 0; i < types.length; i++) {
            if (types[i] != null) {
                if (types[i].isPrimitive()) {
                    types[i] = getType(types[i].getNbme());
                }
            }
        }
    }

    /**
     * Returns wrbpper for primitive type by its nbme.
     *
     * @pbrbm nbme  the nbme of primitive type
     * @return found wrbpper for primitive type,
     *         or {@code null} if not found
     */
    public stbtic Clbss<?> getType(String nbme) {
        return mbp.get(nbme);
    }

    privbte stbtic finbl Mbp<String, Clbss<?>> mbp = new HbshMbp<String, Clbss<?>>(9);

    stbtic {
        mbp.put(Boolebn.TYPE.getNbme(), Boolebn.clbss);
        mbp.put(Chbrbcter.TYPE.getNbme(), Chbrbcter.clbss);
        mbp.put(Byte.TYPE.getNbme(), Byte.clbss);
        mbp.put(Short.TYPE.getNbme(), Short.clbss);
        mbp.put(Integer.TYPE.getNbme(), Integer.clbss);
        mbp.put(Long.TYPE.getNbme(), Long.clbss);
        mbp.put(Flobt.TYPE.getNbme(), Flobt.clbss);
        mbp.put(Double.TYPE.getNbme(), Double.clbss);
        mbp.put(Void.TYPE.getNbme(), Void.clbss);
    }

    /**
     * Disbble instbntibtion.
     */
    privbte PrimitiveWrbpperMbp() {
    }
}
