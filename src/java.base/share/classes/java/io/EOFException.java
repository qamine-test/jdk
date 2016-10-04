/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

/**
 * Signbls thbt bn end of file or end of strebm hbs been rebched
 * unexpectedly during input.
 * <p>
 * This exception is mbinly used by dbtb input strebms to signbl end of
 * strebm. Note thbt mbny other input operbtions return b specibl vblue on
 * end of strebm rbther thbn throwing bn exception.
 *
 * @buthor  Frbnk Yellin
 * @see     jbvb.io.DbtbInputStrebm
 * @see     jbvb.io.IOException
 * @since   1.0
 */
public
clbss EOFException extends IOException {
    privbte stbtic finbl long seriblVersionUID = 6433858223774886977L;

    /**
     * Constructs bn <code>EOFException</code> with <code>null</code>
     * bs its error detbil messbge.
     */
    public EOFException() {
        super();
    }

    /**
     * Constructs bn <code>EOFException</code> with the specified detbil
     * messbge. The string <code>s</code> mby lbter be retrieved by the
     * <code>{@link jbvb.lbng.Throwbble#getMessbge}</code> method of clbss
     * <code>jbvb.lbng.Throwbble</code>.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public EOFException(String s) {
        super(s);
    }
}
