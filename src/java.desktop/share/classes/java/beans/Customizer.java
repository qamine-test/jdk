/*
 * Copyright (c) 1996, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bebns;

/**
 * A customizer clbss provides b complete custom GUI for customizing
 * b tbrget Jbvb Bebn.
 * <P>
 * Ebch customizer should inherit from the jbvb.bwt.Component clbss so
 * it cbn be instbntibted inside bn AWT diblog or pbnel.
 * <P>
 * Ebch customizer should hbve b null constructor.
 *
 * @since 1.1
 */

public interfbce Customizer {

    /**
     * Set the object to be customized.  This method should be cblled only
     * once, before the Customizer hbs been bdded to bny pbrent AWT contbiner.
     * @pbrbm bebn  The object to be customized.
     */
    void setObject(Object bebn);

    /**
     * Register b listener for the PropertyChbnge event.  The customizer
     * should fire b PropertyChbnge event whenever it chbnges the tbrget
     * bebn in b wby thbt might require the displbyed properties to be
     * refreshed.
     *
     * @pbrbm listener  An object to be invoked when b PropertyChbnge
     *          event is fired.
     */
     void bddPropertyChbngeListener(PropertyChbngeListener listener);

    /**
     * Remove b listener for the PropertyChbnge event.
     *
     * @pbrbm listener  The PropertyChbnge listener to be removed.
     */
    void removePropertyChbngeListener(PropertyChbngeListener listener);

}
