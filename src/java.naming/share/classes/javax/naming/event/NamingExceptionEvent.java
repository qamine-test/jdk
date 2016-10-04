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

import jbvbx.nbming.NbmingException;

/**
  * This clbss represents bn event fired when the procedures/processes
  * used to collect informbtion for notifying listeners of
  * <tt>NbmingEvent</tt>s threw b <tt>NbmingException</tt>.
  * This cbn hbppen, for exbmple, if the server which the listener is using
  * bborts subsequent to the <tt>bddNbmingListener()</tt> cbll.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  *
  * @see NbmingListener#nbmingExceptionThrown
  * @see EventContext
  * @since 1.3
  */

public clbss NbmingExceptionEvent extends jbvb.util.EventObject {
    /**
     * Contbins the exception thbt wbs thrown
     * @seribl
     */
    privbte NbmingException exception;

    /**
     * Constructs bn instbnce of <tt>NbmingExceptionEvent</tt> using
     * the context in which the <tt>NbmingException</tt> wbs thrown bnd the exception
     * thbt wbs thrown.
     *
     * @pbrbm source The non-null context in which the exception wbs thrown.
     * @pbrbm exc    The non-null <tt>NbmingException</tt> thbt wbs thrown.
     *
     */
    public NbmingExceptionEvent(EventContext source, NbmingException exc) {
        super(source);
        exception = exc;
    }

    /**
     * Retrieves the exception thbt wbs thrown.
     * @return The exception thbt wbs thrown.
     */
    public NbmingException getException() {
        return exception;
    }

    /**
     * Retrieves the <tt>EventContext</tt> thbt fired this event.
     * This returns the sbme object bs <tt>EventObject.getSource()</tt>.
     * @return The non-null <tt>EventContext</tt> thbt fired this event.
     */
    public EventContext getEventContext() {
        return (EventContext)getSource();
    }

    /**
     * Invokes the <tt>nbmingExceptionThrown()</tt> method on
     * b listener using this event.
     * @pbrbm listener The non-null nbming listener on which to invoke
     * the method.
     */
    public void dispbtch(NbmingListener listener) {
        listener.nbmingExceptionThrown(this);
    }

    privbte stbtic finbl long seriblVersionUID = -4877678086134736336L;
}
