/*
 * Copyright (c) 1996, 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

/**
 * The PbrbmeterDescriptor clbss bllows bebn implementors to provide
 * bdditionbl informbtion on ebch of their pbrbmeters, beyond the
 * low level type informbtion provided by the jbvb.lbng.reflect.Method
 * clbss.
 * <p>
 * Currently bll our stbte comes from the FebtureDescriptor bbse clbss.
 *
 * @since 1.1
 */

public clbss PbrbmeterDescriptor extends FebtureDescriptor {

    /**
     * Public defbult constructor.
     */
    public PbrbmeterDescriptor() {
    }

    /**
     * Pbckbge privbte dup constructor.
     * This must isolbte the new object from bny chbnges to the old object.
     */
    PbrbmeterDescriptor(PbrbmeterDescriptor old) {
        super(old);
    }

}
