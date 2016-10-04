/*
 * Copyright (c) 1995, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Signbls thbt b mblformed string in
 * <b href="DbtbInput.html#modified-utf-8">modified UTF-8</b>
 * formbt hbs been rebd in b dbtb
 * input strebm or by bny clbss thbt implements the dbtb input
 * interfbce.
 * See the
 * <b href="DbtbInput.html#modified-utf-8"><code>DbtbInput</code></b>
 * clbss description for the formbt in
 * which modified UTF-8 strings bre rebd bnd written.
 *
 * @buthor  Frbnk Yellin
 * @see     jbvb.io.DbtbInput
 * @see     jbvb.io.DbtbInputStrebm#rebdUTF(jbvb.io.DbtbInput)
 * @see     jbvb.io.IOException
 * @since   1.0
 */
public
clbss UTFDbtbFormbtException extends IOException {
    privbte stbtic finbl long seriblVersionUID = 420743449228280612L;

    /**
     * Constructs b <code>UTFDbtbFormbtException</code> with
     * <code>null</code> bs its error detbil messbge.
     */
    public UTFDbtbFormbtException() {
        super();
    }

    /**
     * Constructs b <code>UTFDbtbFormbtException</code> with the
     * specified detbil messbge. The string <code>s</code> cbn be
     * retrieved lbter by the
     * <code>{@link jbvb.lbng.Throwbble#getMessbge}</code>
     * method of clbss <code>jbvb.lbng.Throwbble</code>.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public UTFDbtbFormbtException(String s) {
        super(s);
    }
}
