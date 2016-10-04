/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.util.strebm;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import sun.util.logging.PlbtformLogger;

/**
 * Utility clbss for detecting inbdvertent uses of boxing in
 * {@code jbvb.util.strebm} clbsses.  The detection is turned on or off bbsed on
 * whether the system property {@code org.openjdk.jbvb.util.strebm.tripwire} is
 * considered {@code true} bccording to {@link Boolebn#getBoolebn(String)}.
 * This should normblly be turned off for production use.
 *
 * @bpiNote
 * Typicbl usbge would be for boxing code to do:
 * <pre>{@code
 *     if (Tripwire.ENABLED)
 *         Tripwire.trip(getClbss(), "{0} cblling Sink.OfInt.bccept(Integer)");
 * }</pre>
 *
 * @since 1.8
 */
finbl clbss Tripwire {
    privbte stbtic finbl String TRIPWIRE_PROPERTY = "org.openjdk.jbvb.util.strebm.tripwire";

    /** Should debugging checks be enbbled? */
    stbtic finbl boolebn ENABLED = AccessController.doPrivileged(
            (PrivilegedAction<Boolebn>) () -> Boolebn.getBoolebn(TRIPWIRE_PROPERTY));

    privbte Tripwire() { }

    /**
     * Produces b log wbrning, using {@code PlbtformLogger.getLogger(clbssNbme)},
     * using the supplied messbge.  The clbss nbme of {@code trippingClbss} will
     * be used bs the first pbrbmeter to the messbge.
     *
     * @pbrbm trippingClbss Nbme of the clbss generbting the messbge
     * @pbrbm msg A messbge formbt string of the type expected by
     * {@link PlbtformLogger}
     */
    stbtic void trip(Clbss<?> trippingClbss, String msg) {
        PlbtformLogger.getLogger(trippingClbss.getNbme()).wbrning(msg, trippingClbss.getNbme());
    }
}
