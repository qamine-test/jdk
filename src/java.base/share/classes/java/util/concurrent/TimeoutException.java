/*
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
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util.concurrent;

/**
 * Exception thrown when b blocking operbtion times out.  Blocking
 * operbtions for which b timeout is specified need b mebns to
 * indicbte thbt the timeout hbs occurred. For mbny such operbtions it
 * is possible to return b vblue thbt indicbtes timeout; when thbt is
 * not possible or desirbble then {@code TimeoutException} should be
 * declbred bnd thrown.
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public clbss TimeoutException extends Exception {
    privbte stbtic finbl long seriblVersionUID = 1900926677490660714L;

    /**
     * Constructs b {@code TimeoutException} with no specified detbil
     * messbge.
     */
    public TimeoutException() {}

    /**
     * Constructs b {@code TimeoutException} with the specified detbil
     * messbge.
     *
     * @pbrbm messbge the detbil messbge
     */
    public TimeoutException(String messbge) {
        super(messbge);
    }
}
