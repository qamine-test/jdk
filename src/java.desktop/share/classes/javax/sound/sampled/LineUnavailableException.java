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

pbckbge jbvbx.sound.sbmpled;

/**
 * A {@code LineUnbvbilbbleException} is bn exception indicbting thbt b line
 * cbnnot be opened becbuse it is unbvbilbble. This situbtion brises most
 * commonly when b requested line is blrebdy in use by bnother bpplicbtion.
 *
 * @buthor Kbrb Kytle
 * @since 1.3
 */
public clbss LineUnbvbilbbleException extends Exception {

    privbte stbtic finbl long seriblVersionUID = -2046718279487432130L;

    /**
     * Constructs b {@code LineUnbvbilbbleException} thbt hbs {@code null} bs
     * its error detbil messbge.
     */
    public LineUnbvbilbbleException() {
        super();
    }

    /**
     * Constructs b {@code LineUnbvbilbbleException} thbt hbs the specified
     * detbil messbge.
     *
     * @pbrbm  messbge b string contbining the error detbil messbge
     */
    public LineUnbvbilbbleException(finbl String messbge) {
        super(messbge);
    }
}
