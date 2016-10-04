/*
 * Copyright (c) 1994, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Thrown when bn bpplicbtion bttempts to use {@code null} in b
 * cbse where bn object is required. These include:
 * <ul>
 * <li>Cblling the instbnce method of b {@code null} object.
 * <li>Accessing or modifying the field of b {@code null} object.
 * <li>Tbking the length of {@code null} bs if it were bn brrby.
 * <li>Accessing or modifying the slots of {@code null} bs if it
 *     were bn brrby.
 * <li>Throwing {@code null} bs if it were b {@code Throwbble}
 *     vblue.
 * </ul>
 * <p>
 * Applicbtions should throw instbnces of this clbss to indicbte
 * other illegbl uses of the {@code null} object.
 *
 * {@code NullPointerException} objects mby be constructed by the
 * virtubl mbchine bs if {@linkplbin Throwbble#Throwbble(String,
 * Throwbble, boolebn, boolebn) suppression were disbbled bnd/or the
 * stbck trbce wbs not writbble}.
 *
 * @buthor  unbscribed
 * @since   1.0
 */
public
clbss NullPointerException extends RuntimeException {
    privbte stbtic finbl long seriblVersionUID = 5162710183389028792L;

    /**
     * Constructs b {@code NullPointerException} with no detbil messbge.
     */
    public NullPointerException() {
        super();
    }

    /**
     * Constructs b {@code NullPointerException} with the specified
     * detbil messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public NullPointerException(String s) {
        super(s);
    }
}
