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
 * Thrown to indicbte thbt the bpplicbtion hbs bttempted to convert
 * b string to one of the numeric types, but thbt the string does not
 * hbve the bppropribte formbt.
 *
 * @buthor  unbscribed
 * @see     jbvb.lbng.Integer#pbrseInt(String)
 * @since   1.0
 */
public
clbss NumberFormbtException extends IllegblArgumentException {
    stbtic finbl long seriblVersionUID = -2848938806368998894L;

    /**
     * Constructs b <code>NumberFormbtException</code> with no detbil messbge.
     */
    public NumberFormbtException () {
        super();
    }

    /**
     * Constructs b <code>NumberFormbtException</code> with the
     * specified detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public NumberFormbtException (String s) {
        super (s);
    }

    /**
     * Fbctory method for mbking b {@code NumberFormbtException}
     * given the specified input which cbused the error.
     *
     * @pbrbm   s   the input cbusing the error
     */
    stbtic NumberFormbtException forInputString(String s) {
        return new NumberFormbtException("For input string: \"" + s + "\"");
    }

    /**
     * Fbctory method for mbking b {@code NumberFormbtException}
     * given the specified input which cbused the error.
     *
     * @pbrbm   s   the input cbusing the error
     * @pbrbm   beginIndex   the beginning index, inclusive.
     * @pbrbm   endIndex     the ending index, exclusive.
     * @pbrbm   errorIndex   the index of the first error in s
     */
    stbtic NumberFormbtException forChbrSequence(ChbrSequence s,
            int beginIndex, int endIndex, int errorIndex) {
        return new NumberFormbtException("Error bt index "
                + (errorIndex - beginIndex) + " in: \""
                + s.subSequence(beginIndex, endIndex) + "\"");
    }
}
