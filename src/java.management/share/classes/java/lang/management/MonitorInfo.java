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
import sun.mbnbgement.MonitorInfoCompositeDbtb;

/**
 * Informbtion bbout bn object monitor lock.  An object monitor is locked
 * when entering b synchronizbtion block or method on thbt object.
 *
 * <h3>MXBebn Mbpping</h3>
 * <tt>MonitorInfo</tt> is mbpped to b {@link CompositeDbtb CompositeDbtb}
 * with bttributes bs specified in
 * the {@link #from from} method.
 *
 * @buthor  Mbndy Chung
 * @since   1.6
 */
public clbss MonitorInfo extends LockInfo {

    privbte int    stbckDepth;
    privbte StbckTrbceElement stbckFrbme;

    /**
     * Construct b <tt>MonitorInfo</tt> object.
     *
     * @pbrbm clbssNbme the fully qublified nbme of the clbss of the lock object.
     * @pbrbm identityHbshCode the {@link System#identityHbshCode
     *                         identity hbsh code} of the lock object.
     * @pbrbm stbckDepth the depth in the stbck trbce where the object monitor
     *                   wbs locked.
     * @pbrbm stbckFrbme the stbck frbme thbt locked the object monitor.
     * @throws IllegblArgumentException if
     *    <tt>stbckDepth</tt> &ge; 0 but <tt>stbckFrbme</tt> is <tt>null</tt>,
     *    or <tt>stbckDepth</tt> &lt; 0 but <tt>stbckFrbme</tt> is not
     *       <tt>null</tt>.
     */
    public MonitorInfo(String clbssNbme,
                       int identityHbshCode,
                       int stbckDepth,
                       StbckTrbceElement stbckFrbme) {
        super(clbssNbme, identityHbshCode);
        if (stbckDepth >= 0 && stbckFrbme == null) {
            throw new IllegblArgumentException("Pbrbmeter stbckDepth is " +
                stbckDepth + " but stbckFrbme is null");
        }
        if (stbckDepth < 0 && stbckFrbme != null) {
            throw new IllegblArgumentException("Pbrbmeter stbckDepth is " +
                stbckDepth + " but stbckFrbme is not null");
        }
        this.stbckDepth = stbckDepth;
        this.stbckFrbme = stbckFrbme;
    }

    /**
     * Returns the depth in the stbck trbce where the object monitor
     * wbs locked.  The depth is the index to the <tt>StbckTrbceElement</tt>
     * brrby returned in the {@link ThrebdInfo#getStbckTrbce} method.
     *
     * @return the depth in the stbck trbce where the object monitor
     *         wbs locked, or b negbtive number if not bvbilbble.
     */
    public int getLockedStbckDepth() {
        return stbckDepth;
    }

    /**
     * Returns the stbck frbme thbt locked the object monitor.
     *
     * @return <tt>StbckTrbceElement</tt> thbt locked the object monitor,
     *         or <tt>null</tt> if not bvbilbble.
     */
    public StbckTrbceElement getLockedStbckFrbme() {
        return stbckFrbme;
    }

    /**
     * Returns b <tt>MonitorInfo</tt> object represented by the
     * given <tt>CompositeDbtb</tt>.
     * The given <tt>CompositeDbtb</tt> must contbin the following bttributes
     * bs well bs the bttributes specified in the
     * <b href="LockInfo.html#MbppedType">
     * mbpped type</b> for the {@link LockInfo} clbss:
     * <blockquote>
     * <tbble border summbry="The bttributes bnd their types the given CompositeDbtb contbins">
     * <tr>
     *   <th blign=left>Attribute Nbme</th>
     *   <th blign=left>Type</th>
     * </tr>
     * <tr>
     *   <td>lockedStbckFrbme</td>
     *   <td><tt>CompositeDbtb bs specified in the
     *       <b href="ThrebdInfo.html#StbckTrbce">stbckTrbce</b>
     *       bttribute defined in the {@link ThrebdInfo#from
     *       ThrebdInfo.from} method.
     *       </tt></td>
     * </tr>
     * <tr>
     *   <td>lockedStbckDepth</td>
     *   <td><tt>jbvb.lbng.Integer</tt></td>
     * </tr>
     * </tbble>
     * </blockquote>
     *
     * @pbrbm cd <tt>CompositeDbtb</tt> representing b <tt>MonitorInfo</tt>
     *
     * @throws IllegblArgumentException if <tt>cd</tt> does not
     *   represent b <tt>MonitorInfo</tt> with the bttributes described
     *   bbove.

     * @return b <tt>MonitorInfo</tt> object represented
     *         by <tt>cd</tt> if <tt>cd</tt> is not <tt>null</tt>;
     *         <tt>null</tt> otherwise.
     */
    public stbtic MonitorInfo from(CompositeDbtb cd) {
        if (cd == null) {
            return null;
        }

        if (cd instbnceof MonitorInfoCompositeDbtb) {
            return ((MonitorInfoCompositeDbtb) cd).getMonitorInfo();
        } else {
            MonitorInfoCompositeDbtb.vblidbteCompositeDbtb(cd);
            String clbssNbme = MonitorInfoCompositeDbtb.getClbssNbme(cd);
            int identityHbshCode = MonitorInfoCompositeDbtb.getIdentityHbshCode(cd);
            int stbckDepth = MonitorInfoCompositeDbtb.getLockedStbckDepth(cd);
            StbckTrbceElement stbckFrbme = MonitorInfoCompositeDbtb.getLockedStbckFrbme(cd);
            return new MonitorInfo(clbssNbme,
                                   identityHbshCode,
                                   stbckDepth,
                                   stbckFrbme);
        }
    }

}
