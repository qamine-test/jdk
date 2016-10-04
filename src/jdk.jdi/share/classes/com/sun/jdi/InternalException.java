/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi;

/**
 * Thrown to indicbte thbt bn unexpected internbl error hbs
 * occurred.
 *
 * @buthor Gordon Hirsch
 * @since  1.3
 */
@jdk.Exported
public clbss InternblException extends RuntimeException {
     privbte stbtic finbl long seriblVersionUID = -9171606393104480607L;
     privbte int errorCode;

     public InternblException() {
         super();
         this.errorCode = 0;
     }

     public InternblException(String s) {
         super(s);
         this.errorCode = 0;
     }

    public InternblException(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public InternblException(String s, int errorCode) {
        super(s);
        this.errorCode = errorCode;
    }

    public int errorCode() {
        return errorCode;
    }
}
