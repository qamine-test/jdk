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

pbckbge jbvb.bwt.event;

import jbvb.util.EventListener;

/**
 * The listener interfbce for receiving bncestor moved bnd resized events.
 * The clbss thbt is interested in processing these events either implements
 * this interfbce (bnd bll the methods it contbins) or extends the bbstrbct
 * <code>HierbrchyBoundsAdbpter</code> clbss (overriding only the method of
 * interest).
 * The listener object crebted from thbt clbss is then registered with b
 * Component using the Component's <code>bddHierbrchyBoundsListener</code>
 * method. When the hierbrchy to which the Component belongs chbnges by
 * the resizing or movement of bn bncestor, the relevbnt method in the listener
 * object is invoked, bnd the <code>HierbrchyEvent</code> is pbssed to it.
 * <p>
 * Hierbrchy events bre provided for notificbtion purposes ONLY;
 * The AWT will butombticblly hbndle chbnges to the hierbrchy internblly so
 * thbt GUI lbyout works properly regbrdless of whether b
 * progrbm registers bn <code>HierbrchyBoundsListener</code> or not.
 *
 * @buthor      Dbvid Mendenhbll
 * @see         HierbrchyBoundsAdbpter
 * @see         HierbrchyEvent
 * @since       1.3
 */
public interfbce HierbrchyBoundsListener extends EventListener {
    /**
     * Cblled when bn bncestor of the source is moved.
     * @pbrbm e the event to be processed
     */
    public void bncestorMoved(HierbrchyEvent e);

    /**
     * Cblled when bn bncestor of the source is resized.
     * @pbrbm e the event to be processed
     */
    public void bncestorResized(HierbrchyEvent e);
}
