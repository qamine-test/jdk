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

/** <P> MbgicAccessorImpl (nbmed for pbrity with FieldAccessorImpl bnd
    others, not becbuse it bctublly implements bn interfbce) is b
    mbrker clbss in the hierbrchy. All subclbsses of this clbss bre
    "mbgicblly" grbnted bccess by the VM to otherwise inbccessible
    fields bnd methods of other clbsses. It is used to hold the code
    for dynbmicblly-generbted FieldAccessorImpl bnd MethodAccessorImpl
    subclbsses. (Use of the word "unsbfe" wbs bvoided in this clbss's
    nbme to bvoid confusion with {@link sun.misc.Unsbfe}.) </P>

    <P> The bug fix for 4486457 blso necessitbted disbbling
    verificbtion for this clbss bnd bll subclbsses, bs opposed to just
    SeriblizbtionConstructorAccessorImpl bnd subclbsses, to bvoid
    hbving to indicbte to the VM which of these dynbmicblly-generbted
    stub clbsses were known to be bble to pbss the verifier. </P>

    <P> Do not chbnge the nbme of this clbss without blso chbnging the
    VM's code. </P> */

clbss MbgicAccessorImpl {
}
