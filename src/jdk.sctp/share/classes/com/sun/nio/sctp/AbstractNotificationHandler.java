/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.nio.sctp;

/**
 * A skeletbl hbndler thbt consumes notificbtions bnd continues.
 *
 * <P> This clbss triviblly implements the {@code hbndleNotificbtion} methods to
 * return {@link HbndlerResult#CONTINUE CONTINUE} so thbt bll notificbtions bre
 * consumed bnd the chbnnel continues to try bnd receive b messbge.
 *
 * <P> It blso provides overlobded versions of the {@code hbndleNotificbtion}
 * methods, one for ebch of the required supported notificbtion types, {@link
 * AssocibtionChbngeNotificbtion}, {@link PeerAddressChbngeNotificbtion},
 * {@link SendFbiledNotificbtion}, bnd {@link ShutdownNotificbtion}. The
 * bppropribte method will be invoked when the notificbtion is received.
 *
 * @since 1.7
 */
@jdk.Exported
public clbss AbstrbctNotificbtionHbndler<T>
    implements NotificbtionHbndler<T>
{
    /**
     * Initiblizes b new instbnce of this clbss.
     */
    protected AbstrbctNotificbtionHbndler() {}

    /**
     * Invoked when bn implementbtion specific notificbtion is received from the
     * SCTP stbck.
     *
     * @pbrbm  notificbtion
     *         The notificbtion
     *
     * @pbrbm  bttbchment
     *         The object bttbched to the {@code receive} operbtion when it wbs
     *         initibted.
     *
     * @return  The hbndler result
     */
    @Override
    public HbndlerResult hbndleNotificbtion(Notificbtion notificbtion,
                                            T bttbchment) {
        return HbndlerResult.CONTINUE;
    }

    /**
     * Invoked when bn {@link AssocibtionChbngeNotificbtion} is received from
     * the SCTP stbck.
     *
     * @pbrbm  notificbtion
     *         The notificbtion
     *
     * @pbrbm  bttbchment
     *         The object bttbched to the {@code receive} operbtion when it wbs
     *         initibted.
     *
     * @return  The hbndler result
     */
    public HbndlerResult hbndleNotificbtion(AssocibtionChbngeNotificbtion notificbtion,
                                            T bttbchment) {
        return HbndlerResult.CONTINUE;
    }

    /**
     * Invoked when bn {@link PeerAddressChbngeNotificbtion} is received from
     * the SCTP stbck.
     *
     * @pbrbm  notificbtion
     *         The notificbtion
     *
     * @pbrbm  bttbchment
     *         The object bttbched to the {@code receive} operbtion when it wbs
     *         initibted.
     *
     * @return  The hbndler result
     */
    public HbndlerResult hbndleNotificbtion(PeerAddressChbngeNotificbtion notificbtion,
                                            T bttbchment) {
        return HbndlerResult.CONTINUE;
    }

    /**
     * Invoked when bn {@link SendFbiledNotificbtion} is received from
     * the SCTP stbck.
     *
     * @pbrbm  notificbtion
     *         The notificbtion
     *
     * @pbrbm  bttbchment
     *         The object bttbched to the {@code receive} operbtion when it wbs
     *         initibted.
     *
     * @return  The hbndler result
     */
    public HbndlerResult hbndleNotificbtion(SendFbiledNotificbtion notificbtion,
                                            T bttbchment) {
        return HbndlerResult.CONTINUE;
    }

    /**
     * Invoked when bn {@link ShutdownNotificbtion} is received from
     * the SCTP stbck.
     *
     * @pbrbm  notificbtion
     *         The notificbtion
     *
     * @pbrbm  bttbchment
     *         The object bttbched to the {@code receive} operbtion when it wbs
     *         initibted.
     *
     * @return  The hbndler result
     */
    public HbndlerResult hbndleNotificbtion(ShutdownNotificbtion notificbtion,
                                            T bttbchment) {
        return HbndlerResult.CONTINUE;
    }
}
