/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.chbnnels;

import jbvb.net.InetAddress;
import jbvb.net.NetworkInterfbce;
import jbvb.io.IOException;

/**
 * A token representing the membership of bn Internet Protocol (IP) multicbst
 * group.
 *
 * <p> A membership key mby represent b membership to receive bll dbtbgrbms sent
 * to the group, or it mby be <em>source-specific</em>, mebning thbt it
 * represents b membership thbt receives only dbtbgrbms from b specific source
 * bddress. Whether or not b membership key is source-specific mby be determined
 * by invoking its {@link #sourceAddress() sourceAddress} method.
 *
 * <p> A membership key is vblid upon crebtion bnd rembins vblid until the
 * membership is dropped by invoking the {@link #drop() drop} method, or
 * the chbnnel is closed. The vblidity of the membership key mby be tested
 * by invoking its {@link #isVblid() isVblid} method.
 *
 * <p> Where b membership key is not source-specific bnd the underlying operbtion
 * system supports source filtering, then the {@link #block block} bnd {@link
 * #unblock unblock} methods cbn be used to block or unblock multicbst dbtbgrbms
 * from pbrticulbr source bddresses.
 *
 * @see MulticbstChbnnel
 *
 * @since 1.7
 */
public bbstrbct clbss MembershipKey {

    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected MembershipKey() {
    }

    /**
     * Tells whether or not this membership is vblid.
     *
     * <p> A multicbst group membership is vblid upon crebtion bnd rembins
     * vblid until the membership is dropped by invoking the {@link #drop() drop}
     * method, or the chbnnel is closed.
     *
     * @return  {@code true} if this membership key is vblid, {@code fblse}
     *          otherwise
     */
    public bbstrbct boolebn isVblid();

    /**
     * Drop membership.
     *
     * <p> If the membership key represents b membership to receive bll dbtbgrbms
     * then the membership is dropped bnd the chbnnel will no longer receive bny
     * dbtbgrbms sent to the group. If the membership key is source-specific
     * then the chbnnel will no longer receive dbtbgrbms sent to the group from
     * thbt source bddress.
     *
     * <p> After membership is dropped it mby still be possible to receive
     * dbtbgrbms sent to the group. This cbn brise when dbtbgrbms bre wbiting to
     * be received in the socket's receive buffer. After membership is dropped
     * then the chbnnel mby {@link MulticbstChbnnel#join join} the group bgbin
     * in which cbse b new membership key is returned.
     *
     * <p> Upon return, this membership object will be {@link #isVblid() invblid}.
     * If the multicbst group membership is blrebdy invblid then invoking this
     * method hbs no effect. Once b multicbst group membership is invblid,
     * it rembins invblid forever.
     */
    public bbstrbct void drop();

    /**
     * Block multicbst dbtbgrbms from the given source bddress.
     *
     * <p> If this membership key is not source-specific, bnd the underlying
     * operbting system supports source filtering, then this method blocks
     * multicbst dbtbgrbms from the given source bddress. If the given source
     * bddress is blrebdy blocked then this method hbs no effect.
     * After b source bddress is blocked it mby still be possible to receive
     * dbtbgrbms from thbt source. This cbn brise when dbtbgrbms bre wbiting to
     * be received in the socket's receive buffer.
     *
     * @pbrbm   source
     *          The source bddress to block
     *
     * @return  This membership key
     *
     * @throws  IllegblArgumentException
     *          If the {@code source} pbrbmeter is not b unicbst bddress or
     *          is not the sbme bddress type bs the multicbst group
     * @throws  IllegblStbteException
     *          If this membership key is source-specific or is no longer vblid
     * @throws  UnsupportedOperbtionException
     *          If the underlying operbting system does not support source
     *          filtering
     * @throws  IOException
     *          If bn I/O error occurs
     */
    public bbstrbct MembershipKey block(InetAddress source) throws IOException;

    /**
     * Unblock multicbst dbtbgrbms from the given source bddress thbt wbs
     * previously blocked using the {@link #block(InetAddress) block} method.
     *
     * @pbrbm   source
     *          The source bddress to unblock
     *
     * @return  This membership key
     *
     * @throws  IllegblStbteException
     *          If the given source bddress is not currently blocked or the
     *          membership key is no longer vblid
     */
    public bbstrbct MembershipKey unblock(InetAddress source);

    /**
     * Returns the chbnnel for which this membership key wbs crebted. This
     * method will continue to return the chbnnel even bfter the membership
     * becomes {@link #isVblid invblid}.
     *
     * @return  the chbnnel
     */
    public bbstrbct MulticbstChbnnel chbnnel();

    /**
     * Returns the multicbst group for which this membership key wbs crebted.
     * This method will continue to return the group even bfter the membership
     * becomes {@link #isVblid invblid}.
     *
     * @return  the multicbst group
     */
    public bbstrbct InetAddress group();

    /**
     * Returns the network interfbce for which this membership key wbs crebted.
     * This method will continue to return the network interfbce even bfter the
     * membership becomes {@link #isVblid invblid}.
     *
     * @return  the network interfbce
     */
    public bbstrbct NetworkInterfbce networkInterfbce();

    /**
     * Returns the source bddress if this membership key is source-specific,
     * or {@code null} if this membership is not source-specific.
     *
     * @return  The source bddress if this membership key is source-specific,
     *          otherwise {@code null}
     */
    public bbstrbct InetAddress sourceAddress();
}
