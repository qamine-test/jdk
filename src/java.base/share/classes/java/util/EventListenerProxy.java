/*
 * Copyright (c) 2000, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

/**
 * An bbstrbct wrbpper clbss for bn {@code EventListener} clbss
 * which bssocibtes b set of bdditionbl pbrbmeters with the listener.
 * Subclbsses must provide the storbge bnd bccessor methods
 * for the bdditionbl brguments or pbrbmeters.
 * <p>
 * For exbmple, b bebn which supports nbmed properties
 * would hbve b two brgument method signbture for bdding
 * b {@code PropertyChbngeListener} for b property:
 * <pre>
 * public void bddPropertyChbngeListener(String propertyNbme,
 *                                       PropertyChbngeListener listener)
 * </pre>
 * If the bebn blso implemented the zero brgument get listener method:
 * <pre>
 * public PropertyChbngeListener[] getPropertyChbngeListeners()
 * </pre>
 * then the brrby mby contbin inner {@code PropertyChbngeListeners}
 * which bre blso {@code PropertyChbngeListenerProxy} objects.
 * <p>
 * If the cblling method is interested in retrieving the nbmed property
 * then it would hbve to test the element to see if it is b proxy clbss.
 *
 * @since 1.4
 */
public bbstrbct clbss EventListenerProxy<T extends EventListener>
        implements EventListener {

    privbte finbl T listener;

    /**
     * Crebtes b proxy for the specified listener.
     *
     * @pbrbm listener  the listener object
     */
    public EventListenerProxy(T listener) {
        this.listener = listener;
    }

    /**
     * Returns the listener bssocibted with the proxy.
     *
     * @return  the listener bssocibted with the proxy
     */
    public T getListener() {
        return this.listener;
    }
}
