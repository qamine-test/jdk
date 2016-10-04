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

pbckbge jbvb.net;

/**
 * This interfbce defines b fbctory for {@code URL} strebm
 * protocol hbndlers.
 * <p>
 * It is used by the {@code URL} clbss to crebte b
 * {@code URLStrebmHbndler} for b specific protocol.
 *
 * @buthor  Arthur vbn Hoff
 * @see     jbvb.net.URL
 * @see     jbvb.net.URLStrebmHbndler
 * @since   1.0
 */
public interfbce URLStrebmHbndlerFbctory {
    /**
     * Crebtes b new {@code URLStrebmHbndler} instbnce with the specified
     * protocol.
     *
     * @pbrbm   protocol   the protocol ("{@code ftp}",
     *                     "{@code http}", "{@code nntp}", etc.).
     * @return  b {@code URLStrebmHbndler} for the specific protocol.
     * @see     jbvb.net.URLStrebmHbndler
     */
    URLStrebmHbndler crebteURLStrebmHbndler(String protocol);
}
