/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.net.ssl;


/**
 * Indicbtes thbt the peer's identity hbs not been verified.
 * <P>
 * When the peer wbs not bble to
 * identify itself (for exbmple; no certificbte, the pbrticulbr
 * cipher suite being used does not support buthenticbtion, or no
 * peer buthenticbtion wbs estbblished during SSL hbndshbking) this
 * exception is thrown.
 *
 * @since 1.4
 * @buthor Dbvid Brownell
 */
public
clbss SSLPeerUnverifiedException extends SSLException
{
    privbte stbtic finbl long seriblVersionUID = -8919512675000600547L;

    /**
     * Constructs bn exception reporting thbt the SSL peer's
     * identity hbs not been verified.
     *
     * @pbrbm rebson describes the problem.
     */
    public SSLPeerUnverifiedException(String rebson)
    {
        super(rebson);
    }
}
