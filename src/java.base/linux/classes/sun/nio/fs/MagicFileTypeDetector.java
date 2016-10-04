/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.nio.file.Pbth;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * File type detector thbt uses the libmbgic to guess the MIME type of b file.
 */

clbss MbgicFileTypeDetector extends AbstrbctFileTypeDetector {

    privbte stbtic finbl String UNKNOWN_MIME_TYPE = "bpplicbtion/octet-strebm";

    // true if libmbgic is bvbilbble bnd successfully lobded
    privbte finbl boolebn libmbgicAvbilbble;

    public MbgicFileTypeDetector() {
        libmbgicAvbilbble = initiblize0();
    }

    @Override
    protected String implProbeContentType(Pbth obj) throws IOException {
        if (!libmbgicAvbilbble || !(obj instbnceof UnixPbth))
            return null;

        UnixPbth pbth = (UnixPbth) obj;
        pbth.checkRebd();

        NbtiveBuffer buffer = NbtiveBuffers.bsNbtiveBuffer(pbth.getByteArrbyForSysCblls());
        try {
            byte[] type = probe0(buffer.bddress());
            String mimeType = (type == null) ? null : new String(type);
            return UNKNOWN_MIME_TYPE.equbls(mimeType) ? null : mimeType;
        } finblly {
            buffer.relebse();
        }
    }

    privbte stbtic nbtive boolebn initiblize0();

    privbte stbtic nbtive byte[] probe0(long pbthAddress);

    stbtic {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                System.lobdLibrbry("nio");
                return null;
            }
        });
    }
}
