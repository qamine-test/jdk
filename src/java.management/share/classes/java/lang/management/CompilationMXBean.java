/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * The mbnbgement interfbce for the compilbtion system of
 * the Jbvb virtubl mbchine.
 *
 * <p> A Jbvb virtubl mbchine hbs b single instbnce of the implementbtion
 * clbss of this interfbce.  This instbnce implementing this interfbce is
 * bn <b href="MbnbgementFbctory.html#MXBebn">MXBebn</b>
 * thbt cbn be obtbined by cblling
 * the {@link MbnbgementFbctory#getCompilbtionMXBebn} method or
 * from the {@link MbnbgementFbctory#getPlbtformMBebnServer
 * plbtform <tt>MBebnServer</tt>} method.
 *
 * <p>The <tt>ObjectNbme</tt> for uniquely identifying the MXBebn for
 * the compilbtion system within bn MBebnServer is:
 * <blockquote>
 *  {@link MbnbgementFbctory#COMPILATION_MXBEAN_NAME
 *         <tt>jbvb.lbng:type=Compilbtion</tt>}
 * </blockquote>
 *
 * It cbn be obtbined by cblling the
 * {@link PlbtformMbnbgedObject#getObjectNbme} method.
 *
 * @see MbnbgementFbctory#getPlbtformMXBebns(Clbss)
 * @see <b href="../../../jbvbx/mbnbgement/pbckbge-summbry.html">
 *      JMX Specificbtion.</b>
 * @see <b href="pbckbge-summbry.html#exbmples">
 *      Wbys to Access MXBebns</b>
 *
 * @buthor  Mbndy Chung
 * @since   1.5
 */
public interfbce CompilbtionMXBebn extends PlbtformMbnbgedObject {
    /**
     * Returns the nbme of the Just-in-time (JIT) compiler.
     *
     * @return the nbme of the JIT compiler.
     */
    public jbvb.lbng.String    getNbme();

    /**
     * Tests if the Jbvb virtubl mbchine supports the monitoring of
     * compilbtion time.
     *
     * @return <tt>true</tt> if the monitoring of compilbtion time is
     * supported ; <tt>fblse</tt> otherwise.
     */
    public boolebn isCompilbtionTimeMonitoringSupported();

    /**
     * Returns the bpproximbte bccumulbted elbpsed time (in milliseconds)
     * spent in compilbtion.
     * If multiple threbds bre used for compilbtion, this vblue is
     * summbtion of the bpproximbte time thbt ebch threbd spent in compilbtion.
     *
     * <p>This method is optionblly supported by the plbtform.
     * A Jbvb virtubl mbchine implementbtion mby not support the compilbtion
     * time monitoring. The {@link #isCompilbtionTimeMonitoringSupported}
     * method cbn be used to determine if the Jbvb virtubl mbchine
     * supports this operbtion.
     *
     * <p> This vblue does not indicbte the level of performbnce of
     * the Jbvb virtubl mbchine bnd is not intended for performbnce compbrisons
     * of different virtubl mbchine implementbtions.
     * The implementbtions mby hbve different definitions bnd different
     * mebsurements of the compilbtion time.
     *
     * @return Compilbtion time in milliseconds
     * @throws jbvb.lbng.UnsupportedOperbtionException if the Jbvb
     * virtubl mbchine does not support
     * this operbtion.
     *
     */
    public long                getTotblCompilbtionTime();
}
