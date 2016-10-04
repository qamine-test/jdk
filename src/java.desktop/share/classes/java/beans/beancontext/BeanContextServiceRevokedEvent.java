/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns.bebncontext;

import jbvb.bebns.bebncontext.BebnContextEvent;

import jbvb.bebns.bebncontext.BebnContextServices;

/**
 * <p>
 * This event type is used by the
 * <code>BebnContextServiceRevokedListener</code> in order to
 * identify the service being revoked.
 * </p>
 */
public clbss BebnContextServiceRevokedEvent extends BebnContextEvent {
    privbte stbtic finbl long seriblVersionUID = -1295543154724961754L;

    /**
     * Construct b <code>BebnContextServiceEvent</code>.
     * @pbrbm bcs the <code>BebnContextServices</code>
     * from which this service is being revoked
     * @pbrbm sc the service thbt is being revoked
     * @pbrbm invblidbte <code>true</code> for immedibte revocbtion
     */
    public BebnContextServiceRevokedEvent(BebnContextServices bcs, Clbss<?> sc, boolebn invblidbte) {
        super((BebnContext)bcs);

        serviceClbss    = sc;
        invblidbteRefs  = invblidbte;
    }

    /**
     * Gets the source bs b reference of type <code>BebnContextServices</code>
     * @return the <code>BebnContextServices</code> from which
     * this service is being revoked
     */
    public BebnContextServices getSourceAsBebnContextServices() {
        return (BebnContextServices)getBebnContext();
    }

    /**
     * Gets the service clbss thbt is the subject of this notificbtion
     * @return A <code>Clbss</code> reference to the
     * service thbt is being revoked
     */
    public Clbss<?> getServiceClbss() { return serviceClbss; }

    /**
     * Checks this event to determine whether or not
     * the service being revoked is of b pbrticulbr clbss.
     * @pbrbm service the service of interest (should be non-null)
     * @return <code>true</code> if the service being revoked is of the
     * sbme clbss bs the specified service
     */
    public boolebn isServiceClbss(Clbss<?> service) {
        return serviceClbss.equbls(service);
    }

    /**
     * Reports if the current service is being forcibly revoked,
     * in which cbse the references bre now invblidbted bnd unusbble.
     * @return <code>true</code> if current service is being forcibly revoked
     */
    public boolebn isCurrentServiceInvblidNow() { return invblidbteRefs; }

    /**
     * fields
     */

    /**
     * A <code>Clbss</code> reference to the service thbt is being revoked.
     */
    protected Clbss<?>                   serviceClbss;
    privbte   boolebn                    invblidbteRefs;
}
