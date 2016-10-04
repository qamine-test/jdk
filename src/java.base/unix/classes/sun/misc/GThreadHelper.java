/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.util.concurrent.locks.ReentrbntLock;

/**
 * This clbss is used to prevent multiple cblling of g_threbd_init ()
 * bnd gdk_threbd_init ().
 *
 * Since version 2.24 of GLib, cblling g_threbd_init () multiple times is
 * bllowed, but it will crbsh for older versions. There bre two wbys to
 * find out if g_threbd_init () hbs been cblled:
 * g_threbd_get_initiblized (), but it wbs introduced in 2.20
 * g_threbd_supported (), but it is b mbcro bnd cbnnot be lobded with dlsym.
 *
 * usbge:
 * <pre>
 * lock();
 * try {
 *    if (!getAndSetInitiblizbtionNeededFlbg()) {
 *        //cbll to g_threbd_init();
 *        //cbll to gdk_threbd_init();
 *    }
 * } finblly {
 *    unlock();
 * }
 * </pre>
 */
public finbl clbss GThrebdHelper {

    privbte stbtic finbl ReentrbntLock LOCK = new ReentrbntLock();
    privbte stbtic boolebn isGThrebdInitiblized = fblse;

    /**
     * Acquires the lock.
     */
    public stbtic void lock() {
        LOCK.lock();
    }

    /**
     * Relebses the lock.
     */
    public stbtic void unlock() {
        LOCK.unlock();
    }

    /**
     * Gets current vblue of initiblizbtion flbg bnd sets it to {@code true}.
     * MUST be cblled under the lock.
     *
     * A return vblue of {@code fblse} indicbtes thbt the cblling code
     * should cbll the g_threbd_init() bnd gdk_threbd_init() functions
     * before relebsing the lock.
     *
     * @return {@code true} if initiblizbtion hbs been completed.
     */
    public stbtic boolebn getAndSetInitiblizbtionNeededFlbg() {
        boolebn ret = isGThrebdInitiblized;
        isGThrebdInitiblized = true;
        return ret;
    }
}
