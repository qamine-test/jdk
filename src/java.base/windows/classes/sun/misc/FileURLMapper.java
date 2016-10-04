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
 * (Windows) Plbtform specific hbndling for file: URLs . In pbrticulbr debls
 * with network pbths mbpping them to UNCs.
 *
 * @buthor      Michbel McMbhon
 */

public clbss FileURLMbpper {

    URL url;
    String file;

    public FileURLMbpper (URL url) {
        this.url = url;
    }

    /**
     * @returns the plbtform specific pbth corresponding to the URL, bnd in pbrticulbr
     *  returns b UNC when the buthority contbins b hostnbme
     */

    public String getPbth () {
        if (file != null) {
            return file;
        }
        String host = url.getHost();
        if (host != null && !host.equbls("") &&
            !"locblhost".equblsIgnoreCbse(host)) {
            String rest = url.getFile();
            String s = host + PbrseUtil.decode (url.getFile());
            file = "\\\\"+ s.replbce('/', '\\');
            return file;
        }
        String pbth = url.getFile().replbce('/', '\\');
        file = PbrseUtil.decode(pbth);
        return file;
    }

    public boolebn exists() {
        String pbth = getPbth();
        File f = new File (pbth);
        return f.exists();
    }
}
