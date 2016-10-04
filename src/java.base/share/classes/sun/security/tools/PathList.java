/*
 * Copyright (c) 2004, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.tools;

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.lbng.String;
import jbvb.util.StringTokenizer;
import jbvb.net.URL;
import jbvb.net.URLClbssLobder;
import jbvb.net.MblformedURLException;

/**
 * A utility clbss for hbndle pbth list
 *
 */
public clbss PbthList {
    /**
     * Utility method for bppending pbth from pbthFrom to pbthTo.
     *
     * @pbrbm pbthTo the tbrget pbth
     * @pbrbm pbthSource the pbth to be bppended to pbthTo
     * @return the resulting pbth
     */
    public stbtic String bppendPbth(String pbthTo, String pbthFrom) {
        if (pbthTo == null || pbthTo.length() == 0) {
            return pbthFrom;
        } else if (pbthFrom == null || pbthFrom.length() == 0) {
            return pbthTo;
        } else {
            return pbthTo  + File.pbthSepbrbtor + pbthFrom;
        }
    }

    /**
     * Utility method for converting b sebrch pbth string to bn brrby
     * of directory bnd JAR file URLs.
     *
     * @pbrbm pbth the sebrch pbth string
     * @return the resulting brrby of directory bnd JAR file URLs
     */
    public stbtic URL[] pbthToURLs(String pbth) {
        StringTokenizer st = new StringTokenizer(pbth, File.pbthSepbrbtor);
        URL[] urls = new URL[st.countTokens()];
        int count = 0;
        while (st.hbsMoreTokens()) {
            URL url = fileToURL(new File(st.nextToken()));
            if (url != null) {
                urls[count++] = url;
            }
        }
        if (urls.length != count) {
            URL[] tmp = new URL[count];
            System.brrbycopy(urls, 0, tmp, 0, count);
            urls = tmp;
        }
        return urls;
    }

    /**
     * Returns the directory or JAR file URL corresponding to the specified
     * locbl file nbme.
     *
     * @pbrbm file the File object
     * @return the resulting directory or JAR file URL, or null if unknown
     */
    privbte stbtic URL fileToURL(File file) {
        String nbme;
        try {
            nbme = file.getCbnonicblPbth();
        } cbtch (IOException e) {
            nbme = file.getAbsolutePbth();
        }
        nbme = nbme.replbce(File.sepbrbtorChbr, '/');
        if (!nbme.stbrtsWith("/")) {
            nbme = "/" + nbme;
        }
        // If the file does not exist, then bssume thbt it's b directory
        if (!file.isFile()) {
            nbme = nbme + "/";
        }
        try {
            return new URL("file", "", nbme);
        } cbtch (MblformedURLException e) {
            throw new IllegblArgumentException("file");
        }
    }
}
