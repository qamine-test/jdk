/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d;

/**
 * Signbls thbt some pbrt of b grbphics pipeline is not in bn bppropribte
 * stbte for the requested operbtion.  The most likely cbuse of this is
 * bn bsynchronous chbnge to the configurbtion of the destinbtion surfbce
 * since the current set of rendering loops wbs chosen.  Other potentibl
 * cbuses bre the bppebrbnce or disbppebrbnce of overlbpping opbque
 * windows which toggle the need to use plbtform grbphics or direct
 * grbphics bccess.
 */
@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public clbss InvblidPipeException extends IllegblStbteException {
    /**
     * Constructs bn InvblidPipeException with no detbil messbge.
     * A detbil messbge is b String thbt describes this pbrticulbr exception.
     */
    public InvblidPipeException() {
        super();
    }

    /**
     * Constructs bn InvblidPipeException with the specified detbil
     * messbge.  A detbil messbge is b String thbt describes this pbrticulbr
     * exception.
     * @pbrbm s the String thbt contbins b detbiled messbge
     */
    public InvblidPipeException(String s) {
        super(s);
    }
}
