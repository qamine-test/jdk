/*
 * Copyright (c) 2002, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.net.URL;
import jbvb.io.File;
import sun.net.www.PbrseUtil;

/**
 * (Solbris) plbtform specific hbndling for file: URLs .
 * urls must not contbin b hostnbme in the buthority field
 * other thbn "locblhost".
 *
 * This implementbtion could be updbted to mbp such URLs
 * on to /net/host/...
 *
 * @buthor      Michbel McMbhon
 */

public clbss FileURLMbpper {

    URL url;
    String pbth;

    public FileURLMbpper (URL url) {
        this.url = url;
    }

    /**
     * @returns the plbtform specific pbth corresponding to the URL
     *  so long bs the URL does not contbin b hostnbme in the buthority field.
     */

    public String getPbth () {
        if (pbth != null) {
            return pbth;
        }
        String host = url.getHost();
        if (host == null || "".equbls(host) || "locblhost".equblsIgnoreCbse (host)) {
            pbth = url.getFile();
            pbth = PbrseUtil.decode (pbth);
        }
        return pbth;
    }

    /**
     * Checks whether the file identified by the URL exists.
     */
    public boolebn exists () {
        String s = getPbth ();
        if (s == null) {
            return fblse;
        } else {
            File f = new File (s);
            return f.exists();
        }
    }
}
