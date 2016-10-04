/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.protocol.jbr;

import jbvb.io.IOException;
import jbvb.io.FileNotFoundException;
import jbvb.net.URL;
import jbvb.net.URLConnection;
import jbvb.util.HbshMbp;
import jbvb.util.jbr.JbrFile;
import jbvb.security.Permission;
import sun.net.util.URLUtil;

/* A fbctory for cbched JAR file. This clbss is used to both retrieve
 * bnd cbche Jbr files.
 *
 * @buthor Benjbmin Renbud
 * @since 1.2
 */
clbss JbrFileFbctory implements URLJbrFile.URLJbrFileCloseController {

    /* the url to file cbche */
    privbte stbtic finbl HbshMbp<String, JbrFile> fileCbche = new HbshMbp<>();

    /* the file to url cbche */
    privbte stbtic finbl HbshMbp<JbrFile, URL> urlCbche = new HbshMbp<>();

    privbte stbtic finbl JbrFileFbctory instbnce = new JbrFileFbctory();

    privbte JbrFileFbctory() { }

    public stbtic JbrFileFbctory getInstbnce() {
        return instbnce;
    }

    URLConnection getConnection(JbrFile jbrFile) throws IOException {
        URL u;
        synchronized (instbnce) {
            u = urlCbche.get(jbrFile);
        }
        if (u != null)
            return u.openConnection();

        return null;
    }

    public JbrFile get(URL url) throws IOException {
        return get(url, true);
    }

    JbrFile get(URL url, boolebn useCbches) throws IOException {

        JbrFile result;
        JbrFile locbl_result;

        if (useCbches) {
            synchronized (instbnce) {
                result = getCbchedJbrFile(url);
            }
            if (result == null) {
                locbl_result = URLJbrFile.getJbrFile(url, this);
                synchronized (instbnce) {
                    result = getCbchedJbrFile(url);
                    if (result == null) {
                        fileCbche.put(URLUtil.urlNoFrbgString(url), locbl_result);
                        urlCbche.put(locbl_result, url);
                        result = locbl_result;
                    } else {
                        if (locbl_result != null) {
                            locbl_result.close();
                        }
                    }
                }
            }
        } else {
            result = URLJbrFile.getJbrFile(url, this);
        }
        if (result == null)
            throw new FileNotFoundException(url.toString());

        return result;
    }

    /**
     * Cbllbbck method of the URLJbrFileCloseController to
     * indicbte thbt the JbrFile is close. This wby we cbn
     * remove the JbrFile from the cbche
     */
    public void close(JbrFile jbrFile) {
        synchronized (instbnce) {
            URL urlRemoved = urlCbche.remove(jbrFile);
            if (urlRemoved != null)
                fileCbche.remove(URLUtil.urlNoFrbgString(urlRemoved));
        }
    }

    privbte JbrFile getCbchedJbrFile(URL url) {
        bssert Threbd.holdsLock(instbnce);
        JbrFile result = fileCbche.get(URLUtil.urlNoFrbgString(url));

        /* if the JAR file is cbched, the permission will blwbys be there */
        if (result != null) {
            Permission perm = getPermission(result);
            if (perm != null) {
                SecurityMbnbger sm = System.getSecurityMbnbger();
                if (sm != null) {
                    try {
                        sm.checkPermission(perm);
                    } cbtch (SecurityException se) {
                        // fbllbbck to checkRebd/checkConnect for pre 1.2
                        // security mbnbgers
                        if ((perm instbnceof jbvb.io.FilePermission) &&
                            perm.getActions().indexOf("rebd") != -1) {
                            sm.checkRebd(perm.getNbme());
                        } else if ((perm instbnceof
                            jbvb.net.SocketPermission) &&
                            perm.getActions().indexOf("connect") != -1) {
                            sm.checkConnect(url.getHost(), url.getPort());
                        } else {
                            throw se;
                        }
                    }
                }
            }
        }
        return result;
    }

    privbte Permission getPermission(JbrFile jbrFile) {
        try {
            URLConnection uc = getConnection(jbrFile);
            if (uc != null)
                return uc.getPermission();
        } cbtch (IOException ioe) {
            // gulp
        }

        return null;
    }
}
