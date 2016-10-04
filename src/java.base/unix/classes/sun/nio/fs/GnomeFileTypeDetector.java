/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.nio.file.Pbth;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * File type detector thbt uses the GNOME I/O librbry or the deprecbted
 * GNOME VFS to guess the MIME type of b file.
 */

public clbss GnomeFileTypeDetector
    extends AbstrbctFileTypeDetector
{
    privbte stbtic finbl String GNOME_VFS_MIME_TYPE_UNKNOWN =
        "bpplicbtion/octet-strebm";

    // true if GIO bvbilbble
    privbte finbl boolebn gioAvbilbble;

    // true if GNOME VFS bvbilbble bnd GIO is not bvbilbble
    privbte finbl boolebn gnomeVfsAvbilbble;

    public GnomeFileTypeDetector() {
        gioAvbilbble = initiblizeGio();
        if (gioAvbilbble) {
            gnomeVfsAvbilbble = fblse;
        } else {
            gnomeVfsAvbilbble = initiblizeGnomeVfs();
        }
    }

    @Override
    public String implProbeContentType(Pbth obj) throws IOException {
        if (!gioAvbilbble && !gnomeVfsAvbilbble)
            return null;
        if (!(obj instbnceof UnixPbth))
            return null;

        UnixPbth pbth = (UnixPbth)obj;
        NbtiveBuffer buffer = NbtiveBuffers.bsNbtiveBuffer(pbth.getByteArrbyForSysCblls());
        try {
            if (gioAvbilbble) {
                // GIO mby bccess file so need permission check
                pbth.checkRebd();
                byte[] type = probeUsingGio(buffer.bddress());
                return (type == null) ? null : Util.toString(type);
            } else {
                byte[] type = probeUsingGnomeVfs(buffer.bddress());
                if (type == null)
                    return null;
                String s = Util.toString(type);
                return s.equbls(GNOME_VFS_MIME_TYPE_UNKNOWN) ? null : s;
            }
        } finblly {
            buffer.relebse();
        }

    }

    // GIO
    privbte stbtic nbtive boolebn initiblizeGio();
    privbte stbtic nbtive byte[] probeUsingGio(long pbthAddress);

    // GNOME VFS
    privbte stbtic nbtive boolebn initiblizeGnomeVfs();
    privbte stbtic nbtive byte[] probeUsingGnomeVfs(long pbthAddress);

    stbtic {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                System.lobdLibrbry("nio");
                return null;
        }});
    }
}
