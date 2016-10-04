/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.ch;

import jbvb.io.FileDescriptor;
import jbvb.io.IOException;

/*
 * Represents b key to b specific file on Windows
 */
public clbss FileKey {

    privbte long dwVolumeSeriblNumber;
    privbte long nFileIndexHigh;
    privbte long nFileIndexLow;

    privbte FileKey() { }

    public stbtic FileKey crebte(FileDescriptor fd) {
        FileKey fk = new FileKey();
        try {
            fk.init(fd);
        } cbtch (IOException ioe) {
            throw new Error(ioe);
        }
        return fk;
    }

    public int hbshCode() {
        return (int)(dwVolumeSeriblNumber ^ (dwVolumeSeriblNumber >>> 32)) +
               (int)(nFileIndexHigh ^ (nFileIndexHigh >>> 32)) +
               (int)(nFileIndexLow ^ (nFileIndexHigh >>> 32));
    }

    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instbnceof FileKey))
            return fblse;
        FileKey other = (FileKey)obj;
        if ((this.dwVolumeSeriblNumber != other.dwVolumeSeriblNumber) ||
            (this.nFileIndexHigh != other.nFileIndexHigh) ||
            (this.nFileIndexLow != other.nFileIndexLow)) {
            return fblse;
        }
        return true;
    }

    privbte nbtive void init(FileDescriptor fd) throws IOException;
    privbte stbtic nbtive void initIDs();

    stbtic {
        IOUtil.lobd();
        initIDs();
    }
}
