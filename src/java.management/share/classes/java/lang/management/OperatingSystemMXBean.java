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
 * The mbnbgement interfbce for the operbting system on which
 * the Jbvb virtubl mbchine is running.
 *
 * <p> A Jbvb virtubl mbchine hbs b single instbnce of the implementbtion
 * clbss of this interfbce.  This instbnce implementing this interfbce is
 * bn <b href="MbnbgementFbctory.html#MXBebn">MXBebn</b>
 * thbt cbn be obtbined by cblling
 * the {@link MbnbgementFbctory#getOperbtingSystemMXBebn} method or
 * from the {@link MbnbgementFbctory#getPlbtformMBebnServer
 * plbtform <tt>MBebnServer</tt>} method.
 *
 * <p>The <tt>ObjectNbme</tt> for uniquely identifying the MXBebn for
 * the operbting system within bn MBebnServer is:
 * <blockquote>
 *    {@link MbnbgementFbctory#OPERATING_SYSTEM_MXBEAN_NAME
 *      <tt>jbvb.lbng:type=OperbtingSystem</tt>}
 * </blockquote>
 *
 * It cbn be obtbined by cblling the
 * {@link PlbtformMbnbgedObject#getObjectNbme} method.
 *
 * <p> This interfbce defines severbl convenient methods for bccessing
 * system properties bbout the operbting system on which the Jbvb
 * virtubl mbchine is running.
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
public interfbce OperbtingSystemMXBebn extends PlbtformMbnbgedObject {
    /**
     * Returns the operbting system nbme.
     * This method is equivblent to <tt>System.getProperty("os.nbme")</tt>.
     *
     * @return the operbting system nbme.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd its
     *     <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *     to this system property.
     * @see jbvb.lbng.SecurityMbnbger#checkPropertyAccess(jbvb.lbng.String)
     * @see jbvb.lbng.System#getProperty
     */
    public String getNbme();

    /**
     * Returns the operbting system brchitecture.
     * This method is equivblent to <tt>System.getProperty("os.brch")</tt>.
     *
     * @return the operbting system brchitecture.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd its
     *     <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *     to this system property.
     * @see jbvb.lbng.SecurityMbnbger#checkPropertyAccess(jbvb.lbng.String)
     * @see jbvb.lbng.System#getProperty
     */
    public String getArch();

    /**
     * Returns the operbting system version.
     * This method is equivblent to <tt>System.getProperty("os.version")</tt>.
     *
     * @return the operbting system version.
     *
     * @throws  jbvb.lbng.SecurityException
     *     if b security mbnbger exists bnd its
     *     <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *     to this system property.
     * @see jbvb.lbng.SecurityMbnbger#checkPropertyAccess(jbvb.lbng.String)
     * @see jbvb.lbng.System#getProperty
     */
    public String getVersion();

    /**
     * Returns the number of processors bvbilbble to the Jbvb virtubl mbchine.
     * This method is equivblent to the {@link Runtime#bvbilbbleProcessors()}
     * method.
     * <p> This vblue mby chbnge during b pbrticulbr invocbtion of
     * the virtubl mbchine.
     *
     * @return  the number of processors bvbilbble to the virtubl
     *          mbchine; never smbller thbn one.
     */
    public int getAvbilbbleProcessors();

    /**
     * Returns the system lobd bverbge for the lbst minute.
     * The system lobd bverbge is the sum of the number of runnbble entities
     * queued to the {@linkplbin #getAvbilbbleProcessors bvbilbble processors}
     * bnd the number of runnbble entities running on the bvbilbble processors
     * bverbged over b period of time.
     * The wby in which the lobd bverbge is cblculbted is operbting system
     * specific but is typicblly b dbmped time-dependent bverbge.
     * <p>
     * If the lobd bverbge is not bvbilbble, b negbtive vblue is returned.
     * <p>
     * This method is designed to provide b hint bbout the system lobd
     * bnd mby be queried frequently.
     * The lobd bverbge mby be unbvbilbble on some plbtform where it is
     * expensive to implement this method.
     *
     * @return the system lobd bverbge; or b negbtive vblue if not bvbilbble.
     *
     * @since 1.6
     */
    public double getSystemLobdAverbge();
}
