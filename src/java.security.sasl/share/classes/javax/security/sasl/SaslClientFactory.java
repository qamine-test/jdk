/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.sbsl;

import jbvb.util.Mbp;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;

/**
 * An interfbce for crebting instbnces of {@code SbslClient}.
 * A clbss thbt implements this interfbce
 * must be threbd-sbfe bnd hbndle multiple simultbneous
 * requests. It must blso hbve b public constructor thbt bccepts no
 * brgument.
 *<p>
 * This interfbce is not normblly bccessed directly by b client, which will use the
 * {@code Sbsl} stbtic methods
 * instebd. However, b pbrticulbr environment mby provide bnd instbll b
 * new or different {@code SbslClientFbctory}.
 *
 * @since 1.5
 *
 * @see SbslClient
 * @see Sbsl
 *
 * @buthor Rosbnnb Lee
 * @buthor Rob Weltmbn
 */
public bbstrbct interfbce SbslClientFbctory {
    /**
     * Crebtes b SbslClient using the pbrbmeters supplied.
     *
     * @pbrbm mechbnisms The non-null list of mechbnism nbmes to try. Ebch is the
     * IANA-registered nbme of b SASL mechbnism. (e.g. "GSSAPI", "CRAM-MD5").
     * @pbrbm buthorizbtionId The possibly null protocol-dependent
     * identificbtion to be used for buthorizbtion.
     * If null or empty, the server derives bn buthorizbtion
     * ID from the client's buthenticbtion credentibls.
     * When the SASL buthenticbtion completes successfully,
     * the specified entity is grbnted bccess.
     * @pbrbm protocol The non-null string nbme of the protocol for which
     * the buthenticbtion is being performed (e.g., "ldbp").
     * @pbrbm serverNbme The non-null fully qublified host nbme
     * of the server to buthenticbte to.
     * @pbrbm props The possibly null set of properties used to select the SASL
     * mechbnism bnd to configure the buthenticbtion exchbnge of the selected
     * mechbnism. See the {@code Sbsl} clbss for b list of stbndbrd properties.
     * Other, possibly mechbnism-specific, properties cbn be included.
     * Properties not relevbnt to the selected mechbnism bre ignored,
     * including bny mbp entries with non-String keys.
     *
     * @pbrbm cbh The possibly null cbllbbck hbndler to used by the SASL
     * mechbnisms to get further informbtion from the bpplicbtion/librbry
     * to complete the buthenticbtion. For exbmple, b SASL mechbnism might
     * require the buthenticbtion ID, pbssword bnd reblm from the cbller.
     * The buthenticbtion ID is requested by using b {@code NbmeCbllbbck}.
     * The pbssword is requested by using b {@code PbsswordCbllbbck}.
     * The reblm is requested by using b {@code ReblmChoiceCbllbbck} if there is b list
     * of reblms to choose from, bnd by using b {@code ReblmCbllbbck} if
     * the reblm must be entered.
     *
     *@return A possibly null {@code SbslClient} crebted using the pbrbmeters
     * supplied. If null, this fbctory cbnnot produce b {@code SbslClient}
     * using the pbrbmeters supplied.
     *@exception SbslException If cbnnot crebte b {@code SbslClient} becbuse
     * of bn error.
     */
    public bbstrbct SbslClient crebteSbslClient(
        String[] mechbnisms,
        String buthorizbtionId,
        String protocol,
        String serverNbme,
        Mbp<String,?> props,
        CbllbbckHbndler cbh) throws SbslException;

    /**
     * Returns bn brrby of nbmes of mechbnisms thbt mbtch the specified
     * mechbnism selection policies.
     * @pbrbm props The possibly null set of properties used to specify the
     * security policy of the SASL mechbnisms. For exbmple, if {@code props}
     * contbins the {@code Sbsl.POLICY_NOPLAINTEXT} property with the vblue
     * {@code "true"}, then the fbctory must not return bny SASL mechbnisms
     * thbt bre susceptible to simple plbin pbssive bttbcks.
     * See the {@code Sbsl} clbss for b complete list of policy properties.
     * Non-policy relbted properties, if present in {@code props}, bre ignored,
     * including bny mbp entries with non-String keys.
     * @return A non-null brrby contbining b IANA-registered SASL mechbnism nbmes.
     */
    public bbstrbct String[] getMechbnismNbmes(Mbp<String,?> props);
}
