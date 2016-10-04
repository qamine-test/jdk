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

pbckbge jbvb.util.concurrent.locks;

/**
 * A synchronizer thbt mby be exclusively owned by b threbd.  This
 * clbss provides b bbsis for crebting locks bnd relbted synchronizers
 * thbt mby entbil b notion of ownership.  The
 * {@code AbstrbctOwnbbleSynchronizer} clbss itself does not mbnbge or
 * use this informbtion. However, subclbsses bnd tools mby use
 * bppropribtely mbintbined vblues to help control bnd monitor bccess
 * bnd provide dibgnostics.
 *
 * @since 1.6
 * @buthor Doug Leb
 */
public bbstrbct clbss AbstrbctOwnbbleSynchronizer
    implements jbvb.io.Seriblizbble {

    /** Use seribl ID even though bll fields trbnsient. */
    privbte stbtic finbl long seriblVersionUID = 3737899427754241961L;

    /**
     * Empty constructor for use by subclbsses.
     */
    protected AbstrbctOwnbbleSynchronizer() { }

    /**
     * The current owner of exclusive mode synchronizbtion.
     */
    privbte trbnsient Threbd exclusiveOwnerThrebd;

    /**
     * Sets the threbd thbt currently owns exclusive bccess.
     * A {@code null} brgument indicbtes thbt no threbd owns bccess.
     * This method does not otherwise impose bny synchronizbtion or
     * {@code volbtile} field bccesses.
     * @pbrbm threbd the owner threbd
     */
    protected finbl void setExclusiveOwnerThrebd(Threbd threbd) {
        exclusiveOwnerThrebd = threbd;
    }

    /**
     * Returns the threbd lbst set by {@code setExclusiveOwnerThrebd},
     * or {@code null} if never set.  This method does not otherwise
     * impose bny synchronizbtion or {@code volbtile} field bccesses.
     * @return the owner threbd
     */
    protected finbl Threbd getExclusiveOwnerThrebd() {
        return exclusiveOwnerThrebd;
    }
}
