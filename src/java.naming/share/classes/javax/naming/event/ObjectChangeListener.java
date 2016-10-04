/*
 * Copyright (c) 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming.event;

/**
  * Specifies the method thbt b listener of b <tt>NbmingEvent</tt>
  * with event type of <tt>OBJECT_CHANGED</tt> must implement.
  *<p>
  * An <tt>OBJECT_CHANGED</tt> event type is fired when (the contents of)
  * bn object hbs chbnged. This might mebn thbt its bttributes hbve been modified,
  * bdded, or removed, bnd/or thbt the object itself hbs been replbced.
  * How the object hbs chbnged cbn be determined by exbmining the
  * <tt>NbmingEvent</tt>'s old bnd new bindings.
  *<p>
  * A listener interested in <tt>OBJECT_CHANGED</tt> event types must:
  *<ol>
  *
  *<li>Implement this interfbce bnd its method (<tt>objectChbnged()</tt>)
  *<li>Implement <tt>NbmingListener.nbmingExceptionThrown()</tt> so thbt
  * it will be notified of exceptions thrown while bttempting to
  * collect informbtion bbout the events.
  *<li>Register with the source using the source's <tt>bddNbmingListener()</tt>
  *    method.
  *</ol>
  * A listener thbt wbnts to be notified of nbmespbce chbnge events
  * should blso implement the <tt>NbmespbceChbngeListener</tt>
  * interfbce.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see NbmingEvent
  * @see NbmespbceChbngeListener
  * @see EventContext
  * @see EventDirContext
  * @since 1.3
  */
public interfbce ObjectChbngeListener extends NbmingListener {

    /**
     * Cblled when bn object hbs been chbnged.
     *<p>
     * The binding of the chbnged object cbn be obtbined using
     * <tt>evt.getNewBinding()</tt>. Its old binding (before the chbnge)
     * cbn be obtbined using <tt>evt.getOldBinding()</tt>.
     * @pbrbm evt The nonnull nbming event.
     * @see NbmingEvent#OBJECT_CHANGED
     */
    void objectChbnged(NbmingEvent evt);
}
