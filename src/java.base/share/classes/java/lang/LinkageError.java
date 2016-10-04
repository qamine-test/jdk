/*
 * Copyright (c) 1995, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Subclbsses of {@code LinkbgeError} indicbte thbt b clbss hbs
 * some dependency on bnother clbss; however, the lbtter clbss hbs
 * incompbtibly chbnged bfter the compilbtion of the former clbss.
 *
 *
 * @buthor  Frbnk Yellin
 * @since   1.0
 */
public
clbss LinkbgeError extends Error {
    privbte stbtic finbl long seriblVersionUID = 3579600108157160122L;

    /**
     * Constructs b {@code LinkbgeError} with no detbil messbge.
     */
    public LinkbgeError() {
        super();
    }

    /**
     * Constructs b {@code LinkbgeError} with the specified detbil
     * messbge.
     *
     * @pbrbm   s   the detbil messbge.
     */
    public LinkbgeError(String s) {
        super(s);
    }

    /**
     * Constructs b {@code LinkbgeError} with the specified detbil
     * messbge bnd cbuse.
     *
     * @pbrbm s     the detbil messbge.
     * @pbrbm cbuse the cbuse, mby be {@code null}
     * @since 1.7
     */
    public LinkbgeError(String s, Throwbble cbuse) {
        super(s, cbuse);
    }
}
