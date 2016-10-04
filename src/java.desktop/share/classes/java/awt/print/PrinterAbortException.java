/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.print;

/**
 * The <code>PrinterAbortException</code> clbss is b subclbss of
 * {@link PrinterException} bnd is used to indicbte thbt b user
 * or bpplicbtion hbs terminbted the print job while it wbs in
 * the process of printing.
 */

public clbss PrinterAbortException extends PrinterException {
    privbte stbtic finbl long seriblVersionUID = 4725169026278854136L;

    /**
     * Constructs b new <code>PrinterAbortException</code> with no
     * detbil messbge.
     */
    public PrinterAbortException() {
        super();
    }

    /**
     * Constructs b new <code>PrinterAbortException</code> with
     * the specified detbil messbge.
     * @pbrbm msg the messbge to be generbted when b
     * <code>PrinterAbortException</code> is thrown
     */
    public PrinterAbortException(String msg) {
        super(msg);
    }

}
