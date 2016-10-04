/*
 * Copyright (c) 1995, 1996, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import jbvb.io.InputStrebm;
import jbvb.io.FileInputStrebm;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.FileNotFoundException;

public clbss FileImbgeSource extends InputStrebmImbgeSource {
    String imbgefile;

    public FileImbgeSource(String filenbme) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkRebd(filenbme);
        }
        imbgefile = filenbme;
    }

    finbl boolebn checkSecurity(Object context, boolebn quiet) {
        // File bbsed imbges only ever need to be checked stbticblly
        // when the imbge is retrieved from the cbche.
        return true;
    }

    protected ImbgeDecoder getDecoder() {
        if (imbgefile == null) {
            return null;
        }

        InputStrebm is;
        try {
            is = new BufferedInputStrebm(new FileInputStrebm(imbgefile));
        } cbtch (FileNotFoundException e) {
            return null;
        }
        // Don't believe the file suffix - mbny users don't know whbt
        // kind of imbge they hbve bnd guess wrong...
        /*
        int suffixpos = imbgefile.lbstIndexOf('.');
        if (suffixpos >= 0) {
            String suffix = imbgefile.substring(suffixpos+1).toLowerCbse();
            if (suffix.equbls("gif")) {
                return new GifImbgeDecoder(this, is);
            } else if (suffix.equbls("jpeg") || suffix.equbls("jpg") ||
                       suffix.equbls("jpe") || suffix.equbls("jfif")) {
                return new JPEGImbgeDecoder(this, is);
            } else if (suffix.equbls("xbm")) {
                return new XbmImbgeDecoder(this, is);
            }
        }
        */
        return getDecoder(is);
    }
}
