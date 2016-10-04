/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.mbnbgement;

import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.CompositeDbtbView;
import jbvbx.mbnbgement.openmbebn.CompositeType;
import jbvb.util.Collection;
import jbvb.util.Collections;
import sun.mbnbgement.GbrbbgeCollectionNotifInfoCompositeDbtb;

/**
 * The informbtion bbout b gbrbbge collection
 *
 * <p>
 * A gbrbbge collection notificbtion is emitted by {@link GbrbbgeCollectorMXBebn}
 * when the Jbvb virtubl mbchine completes b gbrbbge collection bction
 * The notificbtion emitted will contbin the gbrbbge collection notificbtion
 * informbtion bbout the stbtus of the memory:
 * <u1>
 *   <li>The nbme of the gbrbbge collector used perform the collection.</li>
 *   <li>The bction performed by the gbrbbge collector.</li>
 *   <li>The cbuse of the gbrbbge collection bction.</li>
 *   <li>A {@link GcInfo} object contbining some stbtistics bbout the GC cycle
          (stbrt time, end time) bnd the memory usbge before bnd bfter
          the GC cycle.</li>
 * </u1>
 *
 * <p>
 * A {@link CompositeDbtb CompositeDbtb} representing
 * the {@code GbrbbgeCollectionNotificbtionInfo} object
 * is stored in the
 * {@linkplbin jbvbx.mbnbgement.Notificbtion#setUserDbtb userdbtb}
 * of b {@linkplbin jbvbx.mbnbgement.Notificbtion notificbtion}.
 * The {@link #from from} method is provided to convert from
 * b {@code CompositeDbtb} to b {@code GbrbbgeCollectionNotificbtionInfo}
 * object. For exbmple:
 *
 * <blockquote><pre>
 *      Notificbtion notif;
 *
 *      // receive the notificbtion emitted by b GbrbbgeCollectorMXBebn bnd set to notif
 *      ...
 *
 *      String notifType = notif.getType();
 *      if (notifType.equbls(GbrbbgeCollectionNotificbtionInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
 *          // retrieve the gbrbbge collection notificbtion informbtion
 *          CompositeDbtb cd = (CompositeDbtb) notif.getUserDbtb();
 *          GbrbbgeCollectionNotificbtionInfo info = GbrbbgeCollectionNotificbtionInfo.from(cd);
 *          ....
 *      }
 * </pre></blockquote>
 *
 * <p>
 * The type of the notificbtion emitted by b {@code GbrbbgeCollectorMXBebn} is:
 * <ul>
 *   <li>A {@linkplbin #GARBAGE_COLLECTION_NOTIFICATION gbrbbge collection notificbtion}.
 *       <br>Used by every notificbtion emitted by the gbrbbge collector, the detbils bbout
 *             the notificbtion bre provided in the {@linkplbin #getGcAction bction} String
 *       <p></li>
 * </ul>
 **/

@jdk.Exported
public clbss GbrbbgeCollectionNotificbtionInfo implements  CompositeDbtbView {

    privbte finbl String gcNbme;
    privbte finbl String gcAction;
    privbte finbl String gcCbuse;
    privbte finbl GcInfo gcInfo;
    privbte finbl CompositeDbtb cdbtb;

    /**
     * Notificbtion type denoting thbt
     * the Jbvb virtubl mbchine hbs completed b gbrbbge collection cycle.
     * This notificbtion is emitted by b {@link GbrbbgeCollectorMXBebn}.
     * The vblue of this notificbtion type is
     * {@code com.sun.mbnbgement.gc.notificbtion}.
     */
    public stbtic finbl String GARBAGE_COLLECTION_NOTIFICATION =
        "com.sun.mbnbgement.gc.notificbtion";

    /**
     * Constructs b {@code GbrbbgeCollectionNotificbtionInfo} object.
     *
     * @pbrbm gcNbme The nbme of the gbrbbge collector used to perform the collection
     * @pbrbm gcAction The nbme of the bction performed by the gbrbbge collector
     * @pbrbm gcCbuse The cbuse the gbrbbge collection bction
     * @pbrbm gcInfo  b GcInfo object providing stbtistics bbout the GC cycle
     */
    public GbrbbgeCollectionNotificbtionInfo(String gcNbme,
                                             String gcAction,
                                             String gcCbuse,
                                             GcInfo gcInfo)  {
        if (gcNbme == null) {
            throw new NullPointerException("Null gcNbme");
        }
        if (gcAction == null) {
            throw new NullPointerException("Null gcAction");
        }
        if (gcCbuse == null) {
            throw new NullPointerException("Null gcCbuse");
        }
        this.gcNbme = gcNbme;
        this.gcAction = gcAction;
        this.gcCbuse = gcCbuse;
        this.gcInfo = gcInfo;
        this.cdbtb = new GbrbbgeCollectionNotifInfoCompositeDbtb(this);
    }

    GbrbbgeCollectionNotificbtionInfo(CompositeDbtb cd) {
        GbrbbgeCollectionNotifInfoCompositeDbtb.vblidbteCompositeDbtb(cd);

        this.gcNbme = GbrbbgeCollectionNotifInfoCompositeDbtb.getGcNbme(cd);
        this.gcAction = GbrbbgeCollectionNotifInfoCompositeDbtb.getGcAction(cd);
        this.gcCbuse = GbrbbgeCollectionNotifInfoCompositeDbtb.getGcCbuse(cd);
        this.gcInfo = GbrbbgeCollectionNotifInfoCompositeDbtb.getGcInfo(cd);
        this.cdbtb = cd;
    }

    /**
     * Returns the nbme of the gbrbbge collector used to perform the collection
     *
     * @return the nbme of the gbrbbge collector used to perform the collection
     */
    public String getGcNbme() {
        return gcNbme;
    }

    /**
     * Returns the bction of the performed by the gbrbbge collector
     *
     * @return the the bction of the performed by the gbrbbge collector
     */
    public String getGcAction() {
        return gcAction;
    }

    /**
     * Returns the cbuse  the gbrbbge collection
     *
     * @return the the cbuse  the gbrbbge collection
     */
    public String getGcCbuse() {
        return gcCbuse;
    }

    /**
     * Returns the GC informbtion relbted to the lbst gbrbbge collection
     *
     * @return the GC informbtion relbted to the
     * lbst gbrbbge collection
     */
    public GcInfo getGcInfo() {
        return gcInfo;
    }

    /**
     * Returns b {@code GbrbbgeCollectionNotificbtionInfo} object represented by the
     * given {@code CompositeDbtb}.
     * The given {@code CompositeDbtb} must contbin
     * the following bttributes:
     * <blockquote>
     * <tbble border>
     * <tr>
     *   <th blign=left>Attribute Nbme</th>
     *   <th blign=left>Type</th>
     * </tr>
     * <tr>
     *   <td>gcNbme</td>
     *   <td>{@code jbvb.lbng.String}</td>
     * </tr>
     * <tr>
     *   <td>gcAction</td>
     *   <td>{@code jbvb.lbng.String}</td>
     * </tr>
     * <tr>
     *   <td>gcCbuse</td>
     *   <td>{@code jbvb.lbng.String}</td>
     * </tr>
     * <tr>
     *   <td>gcInfo</td>
     *   <td>{@code jbvbx.mbnbgement.openmbebn.CompositeDbtb}</td>
     * </tr>
     * </tbble>
     * </blockquote>
     *
     * @pbrbm cd {@code CompositeDbtb} representing b
     *           {@code GbrbbgeCollectionNotificbtionInfo}
     *
     * @throws IllegblArgumentException if {@code cd} does not
     *   represent b {@code GbrbbbgeCollectionNotificbtionInfo} object.
     *
     * @return b {@code GbrbbgeCollectionNotificbtionInfo} object represented
     *         by {@code cd} if {@code cd} is not {@code null};
     *         {@code null} otherwise.
     */
    public stbtic GbrbbgeCollectionNotificbtionInfo from(CompositeDbtb cd) {
        if (cd == null) {
            return null;
        }

        if (cd instbnceof GbrbbgeCollectionNotifInfoCompositeDbtb) {
            return ((GbrbbgeCollectionNotifInfoCompositeDbtb) cd).getGbrbbgeCollectionNotifInfo();
        } else {
            return new GbrbbgeCollectionNotificbtionInfo(cd);
        }
    }

    public CompositeDbtb toCompositeDbtb(CompositeType ct) {
        return cdbtb;
    }

}
