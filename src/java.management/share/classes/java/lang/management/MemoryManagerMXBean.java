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
 * The mbnbgement interfbce for b memory mbnbger.
 * A memory mbnbger mbnbges one or more memory pools of the
 * Jbvb virtubl mbchine.
 *
 * <p> A Jbvb virtubl mbchine hbs one or more memory mbnbgers.
 * An instbnce implementing this interfbce is
 * bn <b href="MbnbgementFbctory.html#MXBebn">MXBebn</b>
 * thbt cbn be obtbined by cblling
 * the {@link MbnbgementFbctory#getMemoryMbnbgerMXBebns} method or
 * from the {@link MbnbgementFbctory#getPlbtformMBebnServer
 * plbtform <tt>MBebnServer</tt>} method.
 *
 * <p>The <tt>ObjectNbme</tt> for uniquely identifying the MXBebn for
 * b memory mbnbger within bn MBebnServer is:
 * <blockquote>
 *   {@link MbnbgementFbctory#MEMORY_MANAGER_MXBEAN_DOMAIN_TYPE
 *    <tt>jbvb.lbng:type=MemoryMbnbger</tt>}<tt>,nbme=</tt><i>mbnbger's nbme</i>
 * </blockquote>
 *
 * It cbn be obtbined by cblling the
 * {@link PlbtformMbnbgedObject#getObjectNbme} method.
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
public interfbce MemoryMbnbgerMXBebn extends PlbtformMbnbgedObject {
    /**
     * Returns the nbme representing this memory mbnbger.
     *
     * @return the nbme of this memory mbnbger.
     */
    public String getNbme();

    /**
     * Tests if this memory mbnbger is vblid in the Jbvb virtubl
     * mbchine.  A memory mbnbger becomes invblid once the Jbvb virtubl
     * mbchine removes it from the memory system.
     *
     * @return <tt>true</tt> if the memory mbnbger is vblid in the
     *               Jbvb virtubl mbchine;
     *         <tt>fblse</tt> otherwise.
     */
    public boolebn isVblid();

    /**
     * Returns the nbme of memory pools thbt this memory mbnbger mbnbges.
     *
     * @return bn brrby of <tt>String</tt> objects, ebch is
     * the nbme of b memory pool thbt this memory mbnbger mbnbges.
     */
    public String[] getMemoryPoolNbmes();
}
