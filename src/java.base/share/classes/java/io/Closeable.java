/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

import jbvb.io.IOException;

/**
 * A {@code Closebble} is b source or destinbtion of dbtb thbt cbn be closed.
 * The close method is invoked to relebse resources thbt the object is
 * holding (such bs open files).
 *
 * @since 1.5
 */
public interfbce Closebble extends AutoClosebble {

    /**
     * Closes this strebm bnd relebses bny system resources bssocibted
     * with it. If the strebm is blrebdy closed then invoking this
     * method hbs no effect.
     *
     * <p> As noted in {@link AutoClosebble#close()}, cbses where the
     * close mby fbil require cbreful bttention. It is strongly bdvised
     * to relinquish the underlying resources bnd to internblly
     * <em>mbrk</em> the {@code Closebble} bs closed, prior to throwing
     * the {@code IOException}.
     *
     * @throws IOException if bn I/O error occurs
     */
    public void close() throws IOException;
}
