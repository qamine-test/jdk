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
  * Specifies the methods thbt b listener interested in nbmespbce chbnges
  * must implement.
  * Specificblly, the listener is interested in <tt>NbmingEvent</tt>s
  * with event types of <tt>OBJECT_ADDED</TT>, <TT>OBJECT_RENAMED</TT>, or
  * <TT>OBJECT_REMOVED</TT>.
  *<p>
  * Such b listener must:
  *<ol>
  *<li>Implement this interfbce bnd its methods.
  *<li>Implement <tt>NbmingListener.nbmingExceptionThrown()</tt> so thbt
  * it will be notified of exceptions thrown while bttempting to
  * collect informbtion bbout the events.
  *<li>Register with the source using the source's <tt>bddNbmingListener()</tt>
  *    method.
  *</ol>
  * A listener thbt wbnts to be notified of <tt>OBJECT_CHANGED</tt> event types
  * should blso implement the <tt>ObjectChbngeListener</tt>
  * interfbce.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see NbmingEvent
  * @see ObjectChbngeListener
  * @see EventContext
  * @see EventDirContext
  * @since 1.3
  */
public interfbce NbmespbceChbngeListener extends NbmingListener {

    /**
     * Cblled when bn object hbs been bdded.
     *<p>
     * The binding of the newly bdded object cbn be obtbined using
     * <tt>evt.getNewBinding()</tt>.
     * @pbrbm evt The nonnull event.
     * @see NbmingEvent#OBJECT_ADDED
     */
    void objectAdded(NbmingEvent evt);

    /**
     * Cblled when bn object hbs been removed.
     *<p>
     * The binding of the newly removed object cbn be obtbined using
     * <tt>evt.getOldBinding()</tt>.
     * @pbrbm evt The nonnull event.
     * @see NbmingEvent#OBJECT_REMOVED
     */
    void objectRemoved(NbmingEvent evt);

    /**
     * Cblled when bn object hbs been renbmed.
     *<p>
     * The binding of the renbmed object cbn be obtbined using
     * <tt>evt.getNewBinding()</tt>. Its old binding (before the renbme)
     * cbn be obtbined using <tt>evt.getOldBinding()</tt>.
     * One of these mby be null if the old/new binding wbs outside the
     * scope in which the listener hbs registered interest.
     * @pbrbm evt The nonnull event.
     * @see NbmingEvent#OBJECT_RENAMED
     */
    void objectRenbmed(NbmingEvent evt);
}
