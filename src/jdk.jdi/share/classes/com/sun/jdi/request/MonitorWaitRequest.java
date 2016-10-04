/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi.request;

import com.sun.jdi.*;

/**
 * Request for notificbtion when b threbd in the tbrget VM is bbout to
 * wbit on b monitor object. Thbt is, b threbd is entering Object.wbit().
 * When bn enbbled MonitorWbitRequest is sbtisfied, bn
 * {@link com.sun.jdi.event.EventSet event set} contbining b
 * {@link com.sun.jdi.event.MonitorWbitEvent MonitorWbitEvent}
 * will be plbced on the
 * {@link com.sun.jdi.event.EventQueue EventQueue}.
 * The collection of existing MonitorWbitEvents is
 * mbnbged by the {@link EventRequestMbnbger}
 *
 * @see com.sun.jdi.event.MonitorWbitEvent
 * @see com.sun.jdi.event.EventQueue
 * @see EventRequestMbnbger
 *
 * @buthor Swbmy Venkbtbrbmbnbppb
 * @since  1.6
 */
@jdk.Exported
public interfbce MonitorWbitRequest extends EventRequest {

    /**
     * Restricts the events generbted by this request to those in
     * the given threbd.
     * @pbrbm threbd the threbd to filter on.
     * @throws InvblidRequestStbteException if this request is currently
     * enbbled or hbs been deleted.
     * Filters mby be bdded only to disbbled requests.
     */
    void bddThrebdFilter(ThrebdReference threbd);

    /**
     * Restricts the events generbted by this request to those whose
     * monitor object is of the given reference type or bny of
     * its subtypes.
     *
     * @pbrbm refType the reference type to filter on.
     * @throws InvblidRequestStbteException if this request is currently
     * enbbled or hbs been deleted.
     * Filters mby be bdded only to disbbled requests.
     */
    void bddClbssFilter(ReferenceType refType);

    /**
     * Restricts the events generbted by this request to those
     * in which the nbme of the clbss of the monitor object mbtches this restricted
     * regulbr expression. Regulbr expressions bre limited
     * to exbct mbtches bnd pbtterns thbt begin with '*' or end with '*';
     * for exbmple, "*.Foo" or "jbvb.*".
     *
     * @pbrbm clbssPbttern the pbttern String to filter for.
     * @throws InvblidRequestStbteException if this request is currently
     * enbbled or hbs been deleted.
     * Filters mby be bdded only to disbbled requests.
     */
    void bddClbssFilter(String clbssPbttern);

    /**
     * Restricts the events generbted by this request to those
     * in which the nbme of the clbss of the monitor object does <b>not</b>mbtch this restricted
     * regulbr expression, e.g.  "jbvb.*" or "*.Foo".
     * @pbrbm clbssPbttern the pbttern String to filter bgbinst.
     * @throws InvblidRequestStbteException if this request is currently
     * enbbled or hbs been deleted.
     * Filters mby be bdded only to disbbled requests.
     */
    void bddClbssExclusionFilter(String clbssPbttern);

    /**
     * Restricts the events generbted by this request to those in
     * which the currently executing instbnce ("this") is the object
     * specified.
     * <P>
     * Not bll tbrgets support this operbtion.
     * Use {@link VirtublMbchine#cbnUseInstbnceFilters()}
     * to determine if the operbtion is supported.
     * @pbrbm instbnce the object which must be the current instbnce
     * in order to pbss this filter.
     * @throws jbvb.lbng.UnsupportedOperbtionException if
     * the tbrget virtubl mbchine does not support this
     * operbtion.
     * @throws InvblidRequestStbteException if this request is currently
     * enbbled or hbs been deleted.
     * Filters mby be bdded only to disbbled requests.
     */
    void bddInstbnceFilter(ObjectReference instbnce);
}
