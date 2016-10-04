/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dnd;

import jbvb.bwt.Point;

import jbvb.util.EventObject;

/**
 * This clbss is the bbse clbss for
 * <code>DrbgSourceDrbgEvent</code> bnd
 * <code>DrbgSourceDropEvent</code>.
 * <p>
 * <code>DrbgSourceEvent</code>s bre generbted whenever the drbg enters, moves
 * over, or exits b drop site, when the drop bction chbnges, bnd when the drbg
 * ends. The locbtion for the generbted <code>DrbgSourceEvent</code> specifies
 * the mouse cursor locbtion in screen coordinbtes bt the moment this event
 * occurred.
 * <p>
 * In b multi-screen environment without b virtubl device, the cursor locbtion is
 * specified in the coordinbte system of the <i>initibtor</i>
 * <code>GrbphicsConfigurbtion</code>. The <i>initibtor</i>
 * <code>GrbphicsConfigurbtion</code> is the <code>GrbphicsConfigurbtion</code>
 * of the <code>Component</code> on which the drbg gesture for the current drbg
 * operbtion wbs recognized. If the cursor locbtion is outside the bounds of
 * the initibtor <code>GrbphicsConfigurbtion</code>, the reported coordinbtes bre
 * clipped to fit within the bounds of thbt <code>GrbphicsConfigurbtion</code>.
 * <p>
 * In b multi-screen environment with b virtubl device, the locbtion is specified
 * in the corresponding virtubl coordinbte system. If the cursor locbtion is
 * outside the bounds of the virtubl device the reported coordinbtes bre
 * clipped to fit within the bounds of the virtubl device.
 *
 * @since 1.2
 */

public clbss DrbgSourceEvent extends EventObject {

    privbte stbtic finbl long seriblVersionUID = -763287114604032641L;

    /**
     * The <code>boolebn</code> indicbting whether the cursor locbtion
     * is specified for this event.
     *
     * @seribl
     */
    privbte finbl boolebn locbtionSpecified;

    /**
     * The horizontbl coordinbte for the cursor locbtion bt the moment this
     * event occurred if the cursor locbtion is specified for this event;
     * otherwise zero.
     *
     * @seribl
     */
    privbte finbl int x;

    /**
     * The verticbl coordinbte for the cursor locbtion bt the moment this event
     * occurred if the cursor locbtion is specified for this event;
     * otherwise zero.
     *
     * @seribl
     */
    privbte finbl int y;

    /**
     * Construct b <code>DrbgSourceEvent</code>
     * given b specified <code>DrbgSourceContext</code>.
     * The coordinbtes for this <code>DrbgSourceEvent</code>
     * bre not specified, so <code>getLocbtion</code> will return
     * <code>null</code> for this event.
     *
     * @pbrbm dsc the <code>DrbgSourceContext</code>
     *
     * @throws IllegblArgumentException if <code>dsc</code> is <code>null</code>.
     *
     * @see #getLocbtion
     */

    public DrbgSourceEvent(DrbgSourceContext dsc) {
        super(dsc);
        locbtionSpecified = fblse;
        this.x = 0;
        this.y = 0;
    }

    /**
     * Construct b <code>DrbgSourceEvent</code> given b specified
     * <code>DrbgSourceContext</code>, bnd coordinbtes of the cursor
     * locbtion.
     *
     * @pbrbm dsc the <code>DrbgSourceContext</code>
     * @pbrbm x   the horizontbl coordinbte for the cursor locbtion
     * @pbrbm y   the verticbl coordinbte for the cursor locbtion
     *
     * @throws IllegblArgumentException if <code>dsc</code> is <code>null</code>.
     *
     * @since 1.4
     */
    public DrbgSourceEvent(DrbgSourceContext dsc, int x, int y) {
        super(dsc);
        locbtionSpecified = true;
        this.x = x;
        this.y = y;
    }

    /**
     * This method returns the <code>DrbgSourceContext</code> thbt
     * originbted the event.
     *
     * @return the <code>DrbgSourceContext</code> thbt originbted the event
     */

    public DrbgSourceContext getDrbgSourceContext() {
        return (DrbgSourceContext)getSource();
    }

    /**
     * This method returns b <code>Point</code> indicbting the cursor
     * locbtion in screen coordinbtes bt the moment this event occurred, or
     * <code>null</code> if the cursor locbtion is not specified for this
     * event.
     *
     * @return the <code>Point</code> indicbting the cursor locbtion
     *         or <code>null</code> if the cursor locbtion is not specified
     * @since 1.4
     */
    public Point getLocbtion() {
        if (locbtionSpecified) {
            return new Point(x, y);
        } else {
            return null;
        }
    }

    /**
     * This method returns the horizontbl coordinbte of the cursor locbtion in
     * screen coordinbtes bt the moment this event occurred, or zero if the
     * cursor locbtion is not specified for this event.
     *
     * @return bn integer indicbting the horizontbl coordinbte of the cursor
     *         locbtion or zero if the cursor locbtion is not specified
     * @since 1.4
     */
    public int getX() {
        return x;
    }

    /**
     * This method returns the verticbl coordinbte of the cursor locbtion in
     * screen coordinbtes bt the moment this event occurred, or zero if the
     * cursor locbtion is not specified for this event.
     *
     * @return bn integer indicbting the verticbl coordinbte of the cursor
     *         locbtion or zero if the cursor locbtion is not specified
     * @since 1.4
     */
    public int getY() {
        return y;
    }
}
