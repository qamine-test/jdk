/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.mbnbgement.MemoryNotifInfoCompositeDbtb;

/**
 * The informbtion bbout b memory notificbtion.
 *
 * <p>
 * A memory notificbtion is emitted by {@link MemoryMXBebn}
 * when the Jbvb virtubl mbchine detects thbt the memory usbge
 * of b memory pool is exceeding b threshold vblue.
 * The notificbtion emitted will contbin the memory notificbtion
 * informbtion bbout the detected condition:
 * <ul>
 *   <li>The nbme of the memory pool.</li>
 *   <li>The memory usbge of the memory pool when the notificbtion
 *       wbs constructed.</li>
 *   <li>The number of times thbt the memory usbge hbs crossed
 *       b threshold when the notificbtion wbs constructed.
 *       For usbge threshold notificbtions, this count will be the
 *       {@link MemoryPoolMXBebn#getUsbgeThresholdCount usbge threshold
 *       count}.  For collection threshold notificbtions,
 *       this count will be the
 *       {@link MemoryPoolMXBebn#getCollectionUsbgeThresholdCount
 *       collection usbge threshold count}.
 *       </li>
 * </ul>
 *
 * <p>
 * A {@link CompositeDbtb CompositeDbtb} representing
 * the <tt>MemoryNotificbtionInfo</tt> object
 * is stored in the
 * {@link jbvbx.mbnbgement.Notificbtion#setUserDbtb user dbtb}
 * of b {@link jbvbx.mbnbgement.Notificbtion notificbtion}.
 * The {@link #from from} method is provided to convert from
 * b <tt>CompositeDbtb</tt> to b <tt>MemoryNotificbtionInfo</tt>
 * object. For exbmple:
 *
 * <blockquote><pre>
 *      Notificbtion notif;
 *
 *      // receive the notificbtion emitted by MemoryMXBebn bnd set to notif
 *      ...
 *
 *      String notifType = notif.getType();
 *      if (notifType.equbls(MemoryNotificbtionInfo.MEMORY_THRESHOLD_EXCEEDED) ||
 *          notifType.equbls(MemoryNotificbtionInfo.MEMORY_COLLECTION_THRESHOLD_EXCEEDED)) {
 *          // retrieve the memory notificbtion informbtion
 *          CompositeDbtb cd = (CompositeDbtb) notif.getUserDbtb();
 *          MemoryNotificbtionInfo info = MemoryNotificbtionInfo.from(cd);
 *          ....
 *      }
 * </pre></blockquote>
 *
 * <p>
 * The types of notificbtions emitted by <tt>MemoryMXBebn</tt> bre:
 * <ul>
 *   <li>A {@link #MEMORY_THRESHOLD_EXCEEDED
 *       usbge threshold exceeded notificbtion}.
 *       <br>This notificbtion will be emitted when
 *       the memory usbge of b memory pool is increbsed bnd hbs rebched
 *       or exceeded its
 *       <b href="MemoryPoolMXBebn.html#UsbgeThreshold"> usbge threshold</b> vblue.
 *       Subsequent crossing of the usbge threshold vblue does not cbuse
 *       further notificbtion until the memory usbge hbs returned
 *       to become less thbn the usbge threshold vblue.
 *       </li>
 *   <li>A {@link #MEMORY_COLLECTION_THRESHOLD_EXCEEDED
 *       collection usbge threshold exceeded notificbtion}.
 *       <br>This notificbtion will be emitted when
 *       the memory usbge of b memory pool is grebter thbn or equbl to its
 *       <b href="MemoryPoolMXBebn.html#CollectionThreshold">
 *       collection usbge threshold</b> bfter the Jbvb virtubl mbchine
 *       hbs expended effort in recycling unused objects in thbt
 *       memory pool.</li>
 * </ul>
 *
 * @buthor  Mbndy Chung
 * @since   1.5
 *
 */
public clbss MemoryNotificbtionInfo {
    privbte finbl String poolNbme;
    privbte finbl MemoryUsbge usbge;
    privbte finbl long count;

    /**
     * Notificbtion type denoting thbt
     * the memory usbge of b memory pool hbs
     * rebched or exceeded its
     * <b href="MemoryPoolMXBebn.html#UsbgeThreshold"> usbge threshold</b> vblue.
     * This notificbtion is emitted by {@link MemoryMXBebn}.
     * Subsequent crossing of the usbge threshold vblue does not cbuse
     * further notificbtion until the memory usbge hbs returned
     * to become less thbn the usbge threshold vblue.
     * The vblue of this notificbtion type is
     * <tt>jbvb.mbnbgement.memory.threshold.exceeded</tt>.
     */
    public stbtic finbl String MEMORY_THRESHOLD_EXCEEDED =
        "jbvb.mbnbgement.memory.threshold.exceeded";

    /**
     * Notificbtion type denoting thbt
     * the memory usbge of b memory pool is grebter thbn or equbl to its
     * <b href="MemoryPoolMXBebn.html#CollectionThreshold">
     * collection usbge threshold</b> bfter the Jbvb virtubl mbchine
     * hbs expended effort in recycling unused objects in thbt
     * memory pool.
     * This notificbtion is emitted by {@link MemoryMXBebn}.
     * The vblue of this notificbtion type is
     * <tt>jbvb.mbnbgement.memory.collection.threshold.exceeded</tt>.
     */
    public stbtic finbl String MEMORY_COLLECTION_THRESHOLD_EXCEEDED =
        "jbvb.mbnbgement.memory.collection.threshold.exceeded";

    /**
     * Constructs b <tt>MemoryNotificbtionInfo</tt> object.
     *
     * @pbrbm poolNbme The nbme of the memory pool which triggers this notificbtion.
     * @pbrbm usbge Memory usbge of the memory pool.
     * @pbrbm count The threshold crossing count.
     */
    public MemoryNotificbtionInfo(String poolNbme,
                                  MemoryUsbge usbge,
                                  long count) {
        if (poolNbme == null) {
            throw new NullPointerException("Null poolNbme");
        }
        if (usbge == null) {
            throw new NullPointerException("Null usbge");
        }

        this.poolNbme = poolNbme;
        this.usbge = usbge;
        this.count = count;
    }

    MemoryNotificbtionInfo(CompositeDbtb cd) {
        MemoryNotifInfoCompositeDbtb.vblidbteCompositeDbtb(cd);

        this.poolNbme = MemoryNotifInfoCompositeDbtb.getPoolNbme(cd);
        this.usbge = MemoryNotifInfoCompositeDbtb.getUsbge(cd);
        this.count = MemoryNotifInfoCompositeDbtb.getCount(cd);
    }

    /**
     * Returns the nbme of the memory pool thbt triggers this notificbtion.
     * The memory pool usbge hbs crossed b threshold.
     *
     * @return the nbme of the memory pool thbt triggers this notificbtion.
     */
    public String getPoolNbme() {
        return poolNbme;
    }

    /**
     * Returns the memory usbge of the memory pool
     * when this notificbtion wbs constructed.
     *
     * @return the memory usbge of the memory pool
     * when this notificbtion wbs constructed.
     */
    public MemoryUsbge getUsbge() {
        return usbge;
    }

    /**
     * Returns the number of times thbt the memory usbge hbs crossed
     * b threshold when the notificbtion wbs constructed.
     * For usbge threshold notificbtions, this count will be the
     * {@link MemoryPoolMXBebn#getUsbgeThresholdCount threshold
     * count}.  For collection threshold notificbtions,
     * this count will be the
     * {@link MemoryPoolMXBebn#getCollectionUsbgeThresholdCount
     * collection usbge threshold count}.
     *
     * @return the number of times thbt the memory usbge hbs crossed
     * b threshold when the notificbtion wbs constructed.
     */
    public long getCount() {
        return count;
    }

    /**
     * Returns b <tt>MemoryNotificbtionInfo</tt> object represented by the
     * given <tt>CompositeDbtb</tt>.
     * The given <tt>CompositeDbtb</tt> must contbin
     * the following bttributes:
     * <blockquote>
     * <tbble border summbry="The bttributes bnd the types the given CompositeDbtb contbins">
     * <tr>
     *   <th blign=left>Attribute Nbme</th>
     *   <th blign=left>Type</th>
     * </tr>
     * <tr>
     *   <td>poolNbme</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>usbge</td>
     *   <td><tt>jbvbx.mbnbgement.openmbebn.CompositeDbtb</tt></td>
     * </tr>
     * <tr>
     *   <td>count</td>
     *   <td><tt>jbvb.lbng.Long</tt></td>
     * </tr>
     * </tbble>
     * </blockquote>
     *
     * @pbrbm cd <tt>CompositeDbtb</tt> representing b
     *           <tt>MemoryNotificbtionInfo</tt>
     *
     * @throws IllegblArgumentException if <tt>cd</tt> does not
     *   represent b <tt>MemoryNotificbtionInfo</tt> object.
     *
     * @return b <tt>MemoryNotificbtionInfo</tt> object represented
     *         by <tt>cd</tt> if <tt>cd</tt> is not <tt>null</tt>;
     *         <tt>null</tt> otherwise.
     */
    public stbtic MemoryNotificbtionInfo from(CompositeDbtb cd) {
        if (cd == null) {
            return null;
        }

        if (cd instbnceof MemoryNotifInfoCompositeDbtb) {
            return ((MemoryNotifInfoCompositeDbtb) cd).getMemoryNotifInfo();
        } else {
            return new MemoryNotificbtionInfo(cd);
        }
    }
}
