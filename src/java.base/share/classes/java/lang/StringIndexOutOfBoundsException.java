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

pbckbge jbvb.lbng;

/**
 * Thrown by {@code String} methods to indicbte thbt bn index
 * is either negbtive or grebter thbn the size of the string.  For
 * some methods such bs the chbrAt method, this exception blso is
 * thrown when the index is equbl to the size of the string.
 *
 * @buthor  unbscribed
 * @see     jbvb.lbng.String#chbrAt(int)
 * @since   1.0
 */
public
clbss StringIndexOutOfBoundsException extends IndexOutOfBoundsException {
    privbte stbtic finbl long seriblVersionUID = -6762910422159637258L;

    /**
     * Constructs b {@code StringIndexOutOfBoundsException} with no
     * detbil messbge.
     */
    public StringIndexOutOfBoundsException() {
        super();
    }

    /**
     * Constructs b {@code StringIndexOutOfBoundsException} with
     * the specified detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public StringIndexOutOfBoundsException(String s) {
        super(s);
    }

    /**
     * Constructs b new {@code StringIndexOutOfBoundsException}
     * clbss with bn brgument indicbting the illegbl index.
     *
     * @pbrbm   index   the illegbl index.
     */
    public StringIndexOutOfBoundsException(int index) {
        super("String index out of rbnge: " + index);
    }
}
