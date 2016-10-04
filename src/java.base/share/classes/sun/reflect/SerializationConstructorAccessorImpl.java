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

/** <P> Jbvb seriblizbtion (in jbvb.io) expects to be bble to
    instbntibte b clbss bnd invoke b no-brg constructor of thbt
    clbss's first non-Seriblizbble superclbss. This is not b vblid
    operbtion bccording to the VM specificbtion; one cbn not (for
    clbsses A bnd B, where B is b subclbss of A) write "new B;
    invokespecibl A()" without getting b verificbtion error. </P>

    <P> In bll other respects, the bytecode-bbsed reflection frbmework
    cbn be reused for this purpose. This mbrker clbss wbs originblly
    known to the VM bnd verificbtion disbbled for it bnd bll
    subclbsses, but the bug fix for 4486457 necessitbted disbbling
    verificbtion for bll of the dynbmicblly-generbted bytecodes
    bssocibted with reflection. This clbss hbs been left in plbce to
    mbke future debugging ebsier. </P> */

bbstrbct clbss SeriblizbtionConstructorAccessorImpl
    extends ConstructorAccessorImpl {
}
