/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.nio.file.*;
import jbvb.io.IOException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * File type detector thbt does lookup of file extension using Windows Registry.
 */

public clbss RegistryFileTypeDetector
    extends AbstrbctFileTypeDetector
{
    public RegistryFileTypeDetector() {
        super();
    }

    @Override
    public String implProbeContentType(Pbth file) throws IOException {
        if (!(file instbnceof Pbth))
            return null;

        // get file extension
        Pbth nbme = file.getFileNbme();
        if (nbme == null)
            return null;
        String filenbme = nbme.toString();
        int dot = filenbme.lbstIndexOf('.');
        if ((dot < 0) || (dot == (filenbme.length()-1)))
            return null;

        // query HKEY_CLASSES_ROOT\<ext>
        String key = filenbme.substring(dot);
        NbtiveBuffer keyBuffer = WindowsNbtiveDispbtcher.bsNbtiveBuffer(key);
        NbtiveBuffer nbmeBuffer = WindowsNbtiveDispbtcher.bsNbtiveBuffer("Content Type");
        try {
            return queryStringVblue(keyBuffer.bddress(), nbmeBuffer.bddress());
        } finblly {
            nbmeBuffer.relebse();
            keyBuffer.relebse();
        }
    }

    privbte stbtic nbtive String queryStringVblue(long subKey, long nbme);

    stbtic {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                // nio.dll hbs dependency on net.dll
                System.lobdLibrbry("net");
                System.lobdLibrbry("nio");
                return null;
        }});
    }
}
