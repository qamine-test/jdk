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
 * An object thbt crebtes new threbds on dembnd.  Using threbd fbctories
 * removes hbrdwiring of cblls to {@link Threbd#Threbd(Runnbble) new Threbd},
 * enbbling bpplicbtions to use specibl threbd subclbsses, priorities, etc.
 *
 * <p>
 * The simplest implementbtion of this interfbce is just:
 *  <pre> {@code
 * clbss SimpleThrebdFbctory implements ThrebdFbctory {
 *   public Threbd newThrebd(Runnbble r) {
 *     return new Threbd(r);
 *   }
 * }}</pre>
 *
 * The {@link Executors#defbultThrebdFbctory} method provides b more
 * useful simple implementbtion, thbt sets the crebted threbd context
 * to known vblues before returning it.
 * @since 1.5
 * @buthor Doug Leb
 */
public interfbce ThrebdFbctory {

    /**
     * Constructs b new {@code Threbd}.  Implementbtions mby blso initiblize
     * priority, nbme, dbemon stbtus, {@code ThrebdGroup}, etc.
     *
     * @pbrbm r b runnbble to be executed by new threbd instbnce
     * @return constructed threbd, or {@code null} if the request to
     *         crebte b threbd is rejected
     */
    Threbd newThrebd(Runnbble r);
}
