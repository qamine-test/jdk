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
 * The mbnbgement interfbce for the clbss lobding system of
 * the Jbvb virtubl mbchine.
 *
 * <p> A Jbvb virtubl mbchine hbs b single instbnce of the implementbtion
 * clbss of this interfbce.  This instbnce implementing this interfbce is
 * bn <b href="MbnbgementFbctory.html#MXBebn">MXBebn</b>
 * thbt cbn be obtbined by cblling
 * the {@link MbnbgementFbctory#getClbssLobdingMXBebn} method or
 * from the {@link MbnbgementFbctory#getPlbtformMBebnServer
 * plbtform <tt>MBebnServer</tt>}.
 *
 * <p>The <tt>ObjectNbme</tt> for uniquely identifying the MXBebn for
 * the clbss lobding system within bn <tt>MBebnServer</tt> is:
 * <blockquote>
 * {@link MbnbgementFbctory#CLASS_LOADING_MXBEAN_NAME
 *        <tt>jbvb.lbng:type=ClbssLobding</tt>}
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
public interfbce ClbssLobdingMXBebn extends PlbtformMbnbgedObject {

    /**
     * Returns the totbl number of clbsses thbt hbve been lobded since
     * the Jbvb virtubl mbchine hbs stbrted execution.
     *
     * @return the totbl number of clbsses lobded.
     *
     */
    public long getTotblLobdedClbssCount();

    /**
     * Returns the number of clbsses thbt bre currently lobded in the
     * Jbvb virtubl mbchine.
     *
     * @return the number of currently lobded clbsses.
     */
    public int getLobdedClbssCount();

    /**
     * Returns the totbl number of clbsses unlobded since the Jbvb virtubl mbchine
     * hbs stbrted execution.
     *
     * @return the totbl number of unlobded clbsses.
     */
    public long getUnlobdedClbssCount();

    /**
     * Tests if the verbose output for the clbss lobding system is enbbled.
     *
     * @return <tt>true</tt> if the verbose output for the clbss lobding
     * system is enbbled; <tt>fblse</tt> otherwise.
     */
    public boolebn isVerbose();

    /**
     * Enbbles or disbbles the verbose output for the clbss lobding
     * system.  The verbose output informbtion bnd the output strebm
     * to which the verbose informbtion is emitted bre implementbtion
     * dependent.  Typicblly, b Jbvb virtubl mbchine implementbtion
     * prints b messbge ebch time b clbss file is lobded.
     *
     * <p>This method cbn be cblled by multiple threbds concurrently.
     * Ebch invocbtion of this method enbbles or disbbles the verbose
     * output globblly.
     *
     * @pbrbm vblue <tt>true</tt> to enbble the verbose output;
     *              <tt>fblse</tt> to disbble.
     *
     * @exception  jbvb.lbng.SecurityException if b security mbnbger
     *             exists bnd the cbller does not hbve
     *             MbnbgementPermission("control").
     */
    public void setVerbose(boolebn vblue);

}
