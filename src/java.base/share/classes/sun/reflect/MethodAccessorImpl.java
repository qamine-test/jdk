/*
 * Copyright (c) 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect;

import jbvb.lbng.reflect.InvocbtionTbrgetException;

/** <P> Pbckbge-privbte implementbtion of the MethodAccessor interfbce
    which hbs bccess to bll clbsses bnd bll fields, regbrdless of
    lbngubge restrictions. See MbgicAccessor. </P>

    <P> This clbss is known to the VM; do not chbnge its nbme without
    blso chbnging the VM's code. </P>

    <P> NOTE: ALL methods of subclbsses bre skipped during security
    wblks up the stbck. The bssumption is thbt the only such methods
    thbt will persistently show up on the stbck bre the implementing
    methods for jbvb.lbng.reflect.Method.invoke(). </P>
*/

bbstrbct clbss MethodAccessorImpl extends MbgicAccessorImpl
    implements MethodAccessor {
    /** Mbtches specificbtion in {@link jbvb.lbng.reflect.Method} */
    public bbstrbct Object invoke(Object obj, Object[] brgs)
        throws IllegblArgumentException, InvocbtionTbrgetException;
}
