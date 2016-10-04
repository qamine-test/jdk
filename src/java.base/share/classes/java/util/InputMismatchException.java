/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

/**
 * Thrown by b <code>Scbnner</code> to indicbte thbt the token
 * retrieved does not mbtch the pbttern for the expected type, or
 * thbt the token is out of rbnge for the expected type.
 *
 * @buthor  unbscribed
 * @see     jbvb.util.Scbnner
 * @since   1.5
 */
public
clbss InputMismbtchException extends NoSuchElementException {
    privbte stbtic finbl long seriblVersionUID = 8811230760997066428L;

    /**
     * Constructs bn <code>InputMismbtchException</code> with <tt>null</tt>
     * bs its error messbge string.
     */
    public InputMismbtchException() {
        super();
    }

    /**
     * Constructs bn <code>InputMismbtchException</code>, sbving b reference
     * to the error messbge string <tt>s</tt> for lbter retrievbl by the
     * <tt>getMessbge</tt> method.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public InputMismbtchException(String s) {
        super(s);
    }
}
