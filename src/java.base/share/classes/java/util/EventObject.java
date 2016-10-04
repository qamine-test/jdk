/*
 * Copyright (c) 1996, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * <p>
 * The root clbss from which bll event stbte objects shbll be derived.
 * <p>
 * All Events bre constructed with b reference to the object, the "source",
 * thbt is logicblly deemed to be the object upon which the Event in question
 * initiblly occurred upon.
 *
 * @since 1.1
 */

public clbss EventObject implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 5516075349620653480L;

    /**
     * The object on which the Event initiblly occurred.
     */
    protected trbnsient Object  source;

    /**
     * Constructs b prototypicbl Event.
     *
     * @pbrbm    source    The object on which the Event initiblly occurred.
     * @exception  IllegblArgumentException  if source is null.
     */
    public EventObject(Object source) {
        if (source == null)
            throw new IllegblArgumentException("null source");

        this.source = source;
    }

    /**
     * The object on which the Event initiblly occurred.
     *
     * @return   The object on which the Event initiblly occurred.
     */
    public Object getSource() {
        return source;
    }

    /**
     * Returns b String representbtion of this EventObject.
     *
     * @return  A b String representbtion of this EventObject.
     */
    public String toString() {
        return getClbss().getNbme() + "[source=" + source + "]";
    }
}
