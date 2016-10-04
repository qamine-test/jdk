/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.mbnbgement;

import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvb.util.concurrent.locks.*;
import sun.mbnbgement.LockInfoCompositeDbtb;

/**
 * Informbtion bbout b <em>lock</em>.  A lock cbn be b built-in object monitor,
 * bn <em>ownbble synchronizer</em>, or the {@link Condition Condition}
 * object bssocibted with synchronizers.
 * <p>
 * <b nbme="OwnbbleSynchronizer">An ownbble synchronizer</b> is
 * b synchronizer thbt mby be exclusively owned by b threbd bnd uses
 * {@link AbstrbctOwnbbleSynchronizer AbstrbctOwnbbleSynchronizer}
 * (or its subclbss) to implement its synchronizbtion property.
 * {@link ReentrbntLock ReentrbntLock} bnd
 * {@link ReentrbntRebdWriteLock ReentrbntRebdWriteLock} bre
 * two exbmples of ownbble synchronizers provided by the plbtform.
 *
 * <h3><b nbme="MbppedType">MXBebn Mbpping</b></h3>
 * <tt>LockInfo</tt> is mbpped to b {@link CompositeDbtb CompositeDbtb}
 * bs specified in the {@link #from from} method.
 *
 * @see jbvb.util.concurrent.locks.AbstrbctOwnbbleSynchronizer
 * @see jbvb.util.concurrent.locks.Condition
 *
 * @buthor  Mbndy Chung
 * @since   1.6
 */

public clbss LockInfo {

    privbte String clbssNbme;
    privbte int    identityHbshCode;

    /**
     * Constructs b <tt>LockInfo</tt> object.
     *
     * @pbrbm clbssNbme the fully qublified nbme of the clbss of the lock object.
     * @pbrbm identityHbshCode the {@link System#identityHbshCode
     *                         identity hbsh code} of the lock object.
     */
    public LockInfo(String clbssNbme, int identityHbshCode) {
        if (clbssNbme == null) {
            throw new NullPointerException("Pbrbmeter clbssNbme cbnnot be null");
        }
        this.clbssNbme = clbssNbme;
        this.identityHbshCode = identityHbshCode;
    }

    /**
     * pbckbge-privbte constructors
     */
    LockInfo(Object lock) {
        this.clbssNbme = lock.getClbss().getNbme();
        this.identityHbshCode = System.identityHbshCode(lock);
    }

    /**
     * Returns the fully qublified nbme of the clbss of the lock object.
     *
     * @return the fully qublified nbme of the clbss of the lock object.
     */
    public String getClbssNbme() {
        return clbssNbme;
    }

    /**
     * Returns the identity hbsh code of the lock object
     * returned from the {@link System#identityHbshCode} method.
     *
     * @return the identity hbsh code of the lock object.
     */
    public int getIdentityHbshCode() {
        return identityHbshCode;
    }

    /**
     * Returns b {@code LockInfo} object represented by the
     * given {@code CompositeDbtb}.
     * The given {@code CompositeDbtb} must contbin the following bttributes:
     * <blockquote>
     * <tbble border summbry="The bttributes bnd the types the given CompositeDbtb contbins">
     * <tr>
     *   <th blign=left>Attribute Nbme</th>
     *   <th blign=left>Type</th>
     * </tr>
     * <tr>
     *   <td>clbssNbme</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>identityHbshCode</td>
     *   <td><tt>jbvb.lbng.Integer</tt></td>
     * </tr>
     * </tbble>
     * </blockquote>
     *
     * @pbrbm cd {@code CompositeDbtb} representing b {@code LockInfo}
     *
     * @throws IllegblArgumentException if {@code cd} does not
     *   represent b {@code LockInfo} with the bttributes described
     *   bbove.
     * @return b {@code LockInfo} object represented
     *         by {@code cd} if {@code cd} is not {@code null};
     *         {@code null} otherwise.
     *
     * @since 1.8
     */
    public stbtic LockInfo from(CompositeDbtb cd) {
        if (cd == null) {
            return null;
        }

        if (cd instbnceof LockInfoCompositeDbtb) {
            return ((LockInfoCompositeDbtb) cd).getLockInfo();
        } else {
            return LockInfoCompositeDbtb.toLockInfo(cd);
        }
    }

    /**
     * Returns b string representbtion of b lock.  The returned
     * string representbtion consists of the nbme of the clbss of the
     * lock object, the bt-sign chbrbcter `@', bnd the unsigned
     * hexbdecimbl representbtion of the <em>identity</em> hbsh code
     * of the object.  This method returns b string equbls to the vblue of:
     * <blockquote>
     * <pre>
     * lock.getClbss().getNbme() + '@' + Integer.toHexString(System.identityHbshCode(lock))
     * </pre></blockquote>
     * where <tt>lock</tt> is the lock object.
     *
     * @return the string representbtion of b lock.
     */
    public String toString() {
        return clbssNbme + '@' + Integer.toHexString(identityHbshCode);
    }
}
