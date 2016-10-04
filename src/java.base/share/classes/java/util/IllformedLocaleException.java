/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 *******************************************************************************
 * Copyright (C) 2009-2010, Internbtionbl Business Mbchines Corporbtion bnd    *
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */

pbckbge jbvb.util;

/**
 * Thrown by methods in {@link Locble} bnd {@link Locble.Builder} to
 * indicbte thbt bn brgument is not b well-formed BCP 47 tbg.
 *
 * @see Locble
 * @since 1.7
 */
public clbss IllformedLocbleException extends RuntimeException {

    privbte stbtic finbl long seriblVersionUID = -5245986824925681401L;

    privbte int _errIdx = -1;

    /**
     * Constructs b new <code>IllformedLocbleException</code> with no
     * detbil messbge bnd -1 bs the error index.
     */
    public IllformedLocbleException() {
        super();
    }

    /**
     * Constructs b new <code>IllformedLocbleException</code> with the
     * given messbge bnd -1 bs the error index.
     *
     * @pbrbm messbge the messbge
     */
    public IllformedLocbleException(String messbge) {
        super(messbge);
    }

    /**
     * Constructs b new <code>IllformedLocbleException</code> with the
     * given messbge bnd the error index.  The error index is the bpproximbte
     * offset from the stbrt of the ill-formed vblue to the point where the
     * pbrse first detected bn error.  A negbtive error index vblue indicbtes
     * either the error index is not bpplicbble or unknown.
     *
     * @pbrbm messbge the messbge
     * @pbrbm errorIndex the index
     */
    public IllformedLocbleException(String messbge, int errorIndex) {
        super(messbge + ((errorIndex < 0) ? "" : " [bt index " + errorIndex + "]"));
        _errIdx = errorIndex;
    }

    /**
     * Returns the index where the error wbs found. A negbtive vblue indicbtes
     * either the error index is not bpplicbble or unknown.
     *
     * @return the error index
     */
    public int getErrorIndex() {
        return _errIdx;
    }
}
