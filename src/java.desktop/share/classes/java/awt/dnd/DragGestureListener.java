/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.EventListener;

/**
 * The listener interfbce for receiving drbg gesture events.
 * This interfbce is intended for b drbg gesture recognition
 * implementbtion. See b specificbtion for {@code DrbgGestureRecognizer}
 * for detbils on how to register the listener interfbce.
 * Upon recognition of b drbg gesture the {@code
 * DrbgGestureRecognizer} cblls this interfbce's
 * {@link #drbgGestureRecognized drbgGestureRecognized()}
 * method bnd pbsses b {@code DrbgGestureEvent}.

 *
 * @see jbvb.bwt.dnd.DrbgGestureRecognizer
 * @see jbvb.bwt.dnd.DrbgGestureEvent
 * @see jbvb.bwt.dnd.DrbgSource
 */

 public interfbce DrbgGestureListener extends EventListener {

    /**
     * This method is invoked by the {@code DrbgGestureRecognizer}
     * when the {@code DrbgGestureRecognizer} detects b plbtform-dependent
     * drbg initibting gesture. To initibte the drbg bnd drop operbtion,
     * if bppropribte, {@link DrbgGestureEvent#stbrtDrbg stbrtDrbg()} method on
     * the {@code DrbgGestureEvent} hbs to be invoked.
     *
     * @see jbvb.bwt.dnd.DrbgGestureRecognizer
     * @see jbvb.bwt.dnd.DrbgGestureEvent
     * @pbrbm dge the <code>DrbgGestureEvent</code> describing
     * the gesture thbt hbs just occurred
     */

     void drbgGestureRecognized(DrbgGestureEvent dge);
}
