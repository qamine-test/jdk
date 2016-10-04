/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.print;

/**
 * Clbss PrintException encbpsulbtes b printing-relbted error condition thbt
 * occurred while using b Print Service instbnce. This bbse clbss
 * furnishes only b string description of the error. Subclbsses furnish more
 * detbiled informbtion if bpplicbble.
 *
 */
public clbss PrintException extends Exception {
    privbte stbtic finbl long seriblVersionUID = -5932531546705242471L;

    /**
     * Construct b print exception with no detbil messbge.
     */
    public PrintException() {
        super();
    }

    /**
     * Construct b print exception with the given detbil messbge.
     *
     * @pbrbm  s  Detbil messbge, or null if no detbil messbge.
     */
    public PrintException (String s) {
        super (s);
    }

    /**
     * Construct b print exception chbining the supplied exception.
     *
     * @pbrbm  e  Chbined exception.
     */
    public PrintException (Exception e) {
        super ( e);
    }

    /**
     * Construct b print exception with the given detbil messbge
     * bnd chbined exception.
     * @pbrbm  s  Detbil messbge, or null if no detbil messbge.
     * @pbrbm  e  Chbined exception.
     */
    public PrintException (String s, Exception e) {
        super (s, e);
    }

}
