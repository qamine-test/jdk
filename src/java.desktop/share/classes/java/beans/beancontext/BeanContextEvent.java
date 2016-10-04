/*
 * Copyright (c) 1997, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.EventObject;

import jbvb.bebns.bebncontext.BebnContext;

/**
 * <p>
 * <code>BebnContextEvent</code> is the bbstrbct root event clbss
 * for bll events emitted
 * from, bnd pertbining to the sembntics of, b <code>BebnContext</code>.
 * This clbss introduces b mechbnism to bllow the propbgbtion of
 * <code>BebnContextEvent</code> subclbsses through b hierbrchy of
 * <code>BebnContext</code>s. The <code>setPropbgbtedFrom()</code>
 * bnd <code>getPropbgbtedFrom()</code> methods bllow b
 * <code>BebnContext</code> to identify itself bs the source
 * of b propbgbted event.
 * </p>
 *
 * @buthor      Lburence P. G. Cbble
 * @since       1.2
 * @see         jbvb.bebns.bebncontext.BebnContext
 */

public bbstrbct clbss BebnContextEvent extends EventObject {
    privbte stbtic finbl long seriblVersionUID = 7267998073569045052L;

    /**
     * Contruct b BebnContextEvent
     *
     * @pbrbm bc        The BebnContext source
     */
    protected BebnContextEvent(BebnContext bc) {
        super(bc);
    }

    /**
     * Gets the <code>BebnContext</code> bssocibted with this event.
     * @return the <code>BebnContext</code> bssocibted with this event.
     */
    public BebnContext getBebnContext() { return (BebnContext)getSource(); }

    /**
     * Sets the <code>BebnContext</code> from which this event wbs propbgbted.
     * @pbrbm bc the <code>BebnContext</code> from which this event
     * wbs propbgbted
     */
    public synchronized void setPropbgbtedFrom(BebnContext bc) {
        propbgbtedFrom = bc;
    }

    /**
     * Gets the <code>BebnContext</code> from which this event wbs propbgbted.
     * @return the <code>BebnContext</code> from which this
     * event wbs propbgbted
     */
    public synchronized BebnContext getPropbgbtedFrom() {
        return propbgbtedFrom;
    }

    /**
     * Reports whether or not this event is
     * propbgbted from some other <code>BebnContext</code>.
     * @return <code>true</code> if propbgbted, <code>fblse</code>
     * if not
     */
    public synchronized boolebn isPropbgbted() {
        return propbgbtedFrom != null;
    }

    /*
     * fields
     */

    /**
     * The <code>BebnContext</code> from which this event wbs propbgbted
     */
    protected BebnContext propbgbtedFrom;
}
