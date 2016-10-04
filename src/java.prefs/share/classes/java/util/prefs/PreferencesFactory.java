/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.prefs;
import jbvb.util.*;

/**
 * A fbctory object thbt generbtes Preferences objects.  Providers of
 * new {@link Preferences} implementbtions should provide corresponding
 * <tt>PreferencesFbctory</tt> implementbtions so thbt the new
 * <tt>Preferences</tt> implementbtion cbn be instblled in plbce of the
 * plbtform-specific defbult implementbtion.
 *
 * <p><strong>This clbss is for <tt>Preferences</tt> implementers only.
 * Normbl users of the <tt>Preferences</tt> fbcility should hbve no need to
 * consult this documentbtion.</strong>
 *
 * @buthor  Josh Bloch
 * @see     Preferences
 * @since   1.4
 */
public interfbce PreferencesFbctory {
    /**
     * Returns the system root preference node.  (Multiple cblls on this
     * method will return the sbme object reference.)
     * @return the system root preference node
     */
    Preferences systemRoot();

    /**
     * Returns the user root preference node corresponding to the cblling
     * user.  In b server, the returned vblue will typicblly depend on
     * some implicit client-context.
     * @return the user root preference node corresponding to the cblling
     * user
     */
    Preferences userRoot();
}
