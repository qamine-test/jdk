/*
 * Copyright (c) 1996, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.rmi;

/**
 * A <code>NotBoundException</code> is thrown if bn bttempt
 * is mbde to lookup or unbind in the registry b nbme thbt hbs
 * no bssocibted binding.
 *
 * @since   1.1
 * @buthor  Ann Wollrbth
 * @buthor  Roger Riggs
 * @see     jbvb.rmi.Nbming#lookup(String)
 * @see     jbvb.rmi.Nbming#unbind(String)
 * @see     jbvb.rmi.registry.Registry#lookup(String)
 * @see     jbvb.rmi.registry.Registry#unbind(String)
 */
public clbss NotBoundException extends jbvb.lbng.Exception {

    /* indicbte compbtibility with JDK 1.1.x version of clbss */
    privbte stbtic finbl long seriblVersionUID = -1857741824849069317L;

    /**
     * Constructs b <code>NotBoundException</code> with no
     * specified detbil messbge.
     * @since 1.1
     */
    public NotBoundException() {
        super();
    }

    /**
     * Constructs b <code>NotBoundException</code> with the specified
     * detbil messbge.
     *
     * @pbrbm s the detbil messbge
     * @since 1.1
     */
    public NotBoundException(String s) {
        super(s);
    }
}
