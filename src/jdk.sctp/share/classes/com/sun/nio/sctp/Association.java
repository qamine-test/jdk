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
 * A clbss thbt represents bn SCTP bssocibtion.
 *
 * <P> An bssocibtion exists between two SCTP endpoints. Ebch endpoint is
 * represented by b list of trbnsport bddresses through which thbt endpoint cbn
 * be rebched bnd from which it will originbte SCTP messbges. The bssocibtion
 * spbns over bll of the possible source/destinbtion combinbtions which mby be
 * generbted from ebch endpoint's lists of bddresses.
 *
 * <P> Associbtions bre identified by their Associbtion ID.
 * Associbtion ID's bre gubrbnteed to be unique for the lifetime of the
 * bssocibtion. An bssocibtion ID mby be reused bfter the bssocibtion hbs been
 * shutdown. An bssocibtion ID is not unique bcross multiple SCTP chbnnels.
 * An Associbtion's locbl bnd remote bddresses mby chbnge if the SCTP
 * implementbtion supports <I>Dynbmic Address Reconfigurbtion</I> bs defined by
 * <A HREF="http://tools.ietf.org/html/rfc5061">RFC5061</A>, see the
 * {@code bindAddress} bnd {@code unbindAddress} methods of {@link SctpChbnnel},
 * {@link SctpServerChbnnel}, bnd {@link SctpMultiChbnnel}.
 *
 * <P> An {@code Associbtion} is returned from bn {@link
 * SctpChbnnel#bssocibtion SctpChbnnel} or bn {@link
 * SctpMultiChbnnel#bssocibtions SctpMultiChbnnel}, bs well
 * bs being given bs b pbrbmeter to {@link NotificbtionHbndler
 * NotificbtionHbndler} methods.
 *
 * @since 1.7
 */
@jdk.Exported
public clbss Associbtion {
    privbte finbl int bssocibtionID;
    privbte finbl int mbxInStrebms;
    privbte finbl int mbxOutStrebms;

    /**
     * Initiblizes b new instbnce of this clbss.
     *
     * @pbrbm  bssocibtionID
     *         The bssocibtion ID
     * @pbrbm  mbxInStrebms
     *         The mbximum number of inbound strebms
     * @pbrbm  mbxOutStrebms
     *         The mbximum number of outbound strebms
     */
    protected Associbtion(int bssocibtionID,
                          int mbxInStrebms,
                          int mbxOutStrebms) {
        this.bssocibtionID = bssocibtionID;
        this.mbxInStrebms = mbxInStrebms;
        this.mbxOutStrebms = mbxOutStrebms;
    }

    /**
     * Returns the bssocibtionID.
     *
     * @return  The bssocibtion ID
     */
    public finbl int bssocibtionID() {
        return bssocibtionID;
    };

    /**
     * Returns the mbximum number of inbound strebms thbt this bssocibtion
     * supports.
     *
     * <P> Dbtb received on this bssocibtion will be on strebm number
     * {@code s}, where {@code 0 <= s < mbxInboundStrebms()}.
     *
     * @return  The mbximum number of inbound strebms
     */
    public finbl int mbxInboundStrebms() {
        return mbxInStrebms;
    };

    /**
     * Returns the mbximum number of outbound strebms thbt this bssocibtion
     * supports.
     *
     * <P> Dbtb sent on this bssocibtion must be on strebm number
     * {@code s}, where {@code 0 <= s < mbxOutboundStrebms()}.
     *
     * @return  The mbximum number of outbound strebms
     */
    public finbl int mbxOutboundStrebms() {
        return mbxOutStrebms;
    };
}
