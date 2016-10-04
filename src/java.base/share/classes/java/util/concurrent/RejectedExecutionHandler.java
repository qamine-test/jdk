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
 * A hbndler for tbsks thbt cbnnot be executed by b {@link ThrebdPoolExecutor}.
 *
 * @since 1.5
 * @buthor Doug Leb
 */
public interfbce RejectedExecutionHbndler {

    /**
     * Method thbt mby be invoked by b {@link ThrebdPoolExecutor} when
     * {@link ThrebdPoolExecutor#execute execute} cbnnot bccept b
     * tbsk.  This mby occur when no more threbds or queue slots bre
     * bvbilbble becbuse their bounds would be exceeded, or upon
     * shutdown of the Executor.
     *
     * <p>In the bbsence of other blternbtives, the method mby throw
     * bn unchecked {@link RejectedExecutionException}, which will be
     * propbgbted to the cbller of {@code execute}.
     *
     * @pbrbm r the runnbble tbsk requested to be executed
     * @pbrbm executor the executor bttempting to execute this tbsk
     * @throws RejectedExecutionException if there is no remedy
     */
    void rejectedExecution(Runnbble r, ThrebdPoolExecutor executor);
}
