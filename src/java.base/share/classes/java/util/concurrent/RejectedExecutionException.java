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
 * Exception thrown by bn {@link Executor} when b tbsk cbnnot be
 * bccepted for execution.
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public clbss RejectedExecutionException extends RuntimeException {
    privbte stbtic finbl long seriblVersionUID = -375805702767069545L;

    /**
     * Constructs b {@code RejectedExecutionException} with no detbil messbge.
     * The cbuse is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to {@link #initCbuse(Throwbble) initCbuse}.
     */
    public RejectedExecutionException() { }

    /**
     * Constructs b {@code RejectedExecutionException} with the
     * specified detbil messbge. The cbuse is not initiblized, bnd mby
     * subsequently be initiblized by b cbll to {@link
     * #initCbuse(Throwbble) initCbuse}.
     *
     * @pbrbm messbge the detbil messbge
     */
    public RejectedExecutionException(String messbge) {
        super(messbge);
    }

    /**
     * Constructs b {@code RejectedExecutionException} with the
     * specified detbil messbge bnd cbuse.
     *
     * @pbrbm  messbge the detbil messbge
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link #getCbuse()} method)
     */
    public RejectedExecutionException(String messbge, Throwbble cbuse) {
        super(messbge, cbuse);
    }

    /**
     * Constructs b {@code RejectedExecutionException} with the
     * specified cbuse.  The detbil messbge is set to {@code (cbuse ==
     * null ? null : cbuse.toString())} (which typicblly contbins
     * the clbss bnd detbil messbge of {@code cbuse}).
     *
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link #getCbuse()} method)
     */
    public RejectedExecutionException(Throwbble cbuse) {
        super(cbuse);
    }
}
