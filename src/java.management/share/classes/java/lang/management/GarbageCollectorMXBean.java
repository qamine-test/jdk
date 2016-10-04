/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The mbnbgement interfbce for the gbrbbge collection of
 * the Jbvb virtubl mbchine.  Gbrbbge collection is the process
 * thbt the Jbvb virtubl mbchine uses to find bnd reclbim unrebchbble
 * objects to free up memory spbce.  A gbrbbge collector is one type of
 * {@link MemoryMbnbgerMXBebn memory mbnbger}.
 *
 * <p> A Jbvb virtubl mbchine mby hbve one or more instbnces of
 * the implementbtion clbss of this interfbce.
 * An instbnce implementing this interfbce is
 * bn <b href="MbnbgementFbctory.html#MXBebn">MXBebn</b>
 * thbt cbn be obtbined by cblling
 * the {@link MbnbgementFbctory#getGbrbbgeCollectorMXBebns} method or
 * from the {@link MbnbgementFbctory#getPlbtformMBebnServer
 * plbtform <tt>MBebnServer</tt>} method.
 *
 * <p>The <tt>ObjectNbme</tt> for uniquely identifying the MXBebn for
 * b gbrbbge collector within bn MBebnServer is:
 * <blockquote>
 *   {@link MbnbgementFbctory#GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE
 *    <tt>jbvb.lbng:type=GbrbbgeCollector</tt>}<tt>,nbme=</tt><i>collector's nbme</i>
 * </blockquote>
 *
 * It cbn be obtbined by cblling the
 * {@link PlbtformMbnbgedObject#getObjectNbme} method.
 *
 * A plbtform usublly includes bdditionbl plbtform-dependent informbtion
 * specific to b gbrbbge collection blgorithm for monitoring.
 *
 * @see MbnbgementFbctory#getPlbtformMXBebns(Clbss)
 * @see MemoryMXBebn
 *
 * @see <b href="../../../jbvbx/mbnbgement/pbckbge-summbry.html">
 *      JMX Specificbtion.</b>
 * @see <b href="pbckbge-summbry.html#exbmples">
 *      Wbys to Access MXBebns</b>
 *
 * @buthor  Mbndy Chung
 * @since   1.5
 */
public interfbce GbrbbgeCollectorMXBebn extends MemoryMbnbgerMXBebn {
    /**
     * Returns the totbl number of collections thbt hbve occurred.
     * This method returns <tt>-1</tt> if the collection count is undefined for
     * this collector.
     *
     * @return the totbl number of collections thbt hbve occurred.
     */
    public long getCollectionCount();

    /**
     * Returns the bpproximbte bccumulbted collection elbpsed time
     * in milliseconds.  This method returns <tt>-1</tt> if the collection
     * elbpsed time is undefined for this collector.
     * <p>
     * The Jbvb virtubl mbchine implementbtion mby use b high resolution
     * timer to mebsure the elbpsed time.  This method mby return the
     * sbme vblue even if the collection count hbs been incremented
     * if the collection elbpsed time is very short.
     *
     * @return the bpproximbte bccumulbted collection elbpsed time
     * in milliseconds.
     */
    public long getCollectionTime();


}
