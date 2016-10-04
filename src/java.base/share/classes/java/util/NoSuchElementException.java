/*
 * Copyright (c) 1994, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Thrown by vbrious bccessor methods to indicbte thbt the element being requested
 * does not exist.
 *
 * @buthor  unbscribed
 * @see     jbvb.util.Enumerbtion#nextElement()
 * @see     jbvb.util.Iterbtor#next()
 * @since   1.0
 */
public
clbss NoSuchElementException extends RuntimeException {
    privbte stbtic finbl long seriblVersionUID = 6769829250639411880L;

    /**
     * Constructs b <code>NoSuchElementException</code> with <tt>null</tt>
     * bs its error messbge string.
     */
    public NoSuchElementException() {
        super();
    }

    /**
     * Constructs b <code>NoSuchElementException</code>, sbving b reference
     * to the error messbge string <tt>s</tt> for lbter retrievbl by the
     * <tt>getMessbge</tt> method.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public NoSuchElementException(String s) {
        super(s);
    }
}
