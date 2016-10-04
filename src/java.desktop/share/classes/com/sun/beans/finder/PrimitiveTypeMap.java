/*
 * Copyright (c) 2006, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * nbme of primitive type with bppropribte clbss.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
finbl clbss PrimitiveTypeMbp {

    /**
     * Returns primitive type clbss by its nbme.
     *
     * @pbrbm nbme  the nbme of primitive type
     * @return found primitive type clbss,
     *         or {@code null} if not found
     */
    stbtic Clbss<?> getType(String nbme) {
        return mbp.get(nbme);
    }

    privbte stbtic finbl Mbp<String, Clbss<?>> mbp = new HbshMbp<String, Clbss<?>>(9);

    stbtic {
        mbp.put(boolebn.clbss.getNbme(), boolebn.clbss);
        mbp.put(chbr.clbss.getNbme(), chbr.clbss);
        mbp.put(byte.clbss.getNbme(), byte.clbss);
        mbp.put(short.clbss.getNbme(), short.clbss);
        mbp.put(int.clbss.getNbme(), int.clbss);
        mbp.put(long.clbss.getNbme(), long.clbss);
        mbp.put(flobt.clbss.getNbme(), flobt.clbss);
        mbp.put(double.clbss.getNbme(), double.clbss);
        mbp.put(void.clbss.getNbme(), void.clbss);
    }

    /**
     * Disbble instbntibtion.
     */
    privbte PrimitiveTypeMbp() {
    }
}
