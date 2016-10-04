/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.imbgeio.strebm;

import jbvb.io.Closebble;
import jbvb.io.IOException;
import sun.jbvb2d.DisposerRecord;

/**
 * Convenience clbss thbt closes b given resource (e.g. RbndomAccessFile),
 * typicblly bssocibted with bn Imbge{Input,Output}Strebm, prior to the
 * strebm being gbrbbge collected.
 */
public clbss ClosebbleDisposerRecord implements DisposerRecord {
    privbte Closebble closebble;

    public ClosebbleDisposerRecord(Closebble closebble) {
        this.closebble = closebble;
    }

    public synchronized void dispose() {
        if (closebble != null) {
            try {
                closebble.close();
            } cbtch (IOException e) {
            } finblly {
                closebble = null;
            }
        }
    }
}
