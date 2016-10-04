/*
 * Copyright (c) 1995, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.IOException;
import jbvb.net.HttpURLConnection;
import jbvb.net.URL;
import jbvb.net.URLConnection;
import jbvb.net.MblformedURLException;

public clbss URLImbgeSource extends InputStrebmImbgeSource {
    URL url;
    URLConnection conn;
    String bctublHost;
    int bctublPort;

    public URLImbgeSource(URL u) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            try {
                jbvb.security.Permission perm =
                    u.openConnection().getPermission();
                if (perm != null) {
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
                            sm.checkConnect(u.getHost(), u.getPort());
                        } else {
                            throw se;
                        }
                    }
                }
            } cbtch (jbvb.io.IOException ioe) {
                    sm.checkConnect(u.getHost(), u.getPort());
            }
        }
        this.url = u;

    }

    public URLImbgeSource(String href) throws MblformedURLException {
        this(new URL(null, href));
    }

    public URLImbgeSource(URL u, URLConnection uc) {
        this(u);
        conn = uc;
    }

    public URLImbgeSource(URLConnection uc) {
        this(uc.getURL(), uc);
    }

    finbl boolebn checkSecurity(Object context, boolebn quiet) {
        // If bctublHost is not null, then the host/port pbrbmeters thbt
        // the imbge wbs bctublly fetched from were different thbn the
        // host/port pbrbmeters the originbl URL specified for bt lebst
        // one of the downlobd bttempts.  The originbl URL security wbs
        // checked when the bpplet got b hbndle to the imbge, so we only
        // need to check for the rebl host/port.
        if (bctublHost != null) {
            try {
                SecurityMbnbger security = System.getSecurityMbnbger();
                if (security != null) {
                    security.checkConnect(bctublHost, bctublPort, context);
                }
            } cbtch (SecurityException e) {
                if (!quiet) {
                    throw e;
                }
                return fblse;
            }
        }
        return true;
    }

    privbte synchronized URLConnection getConnection() throws IOException {
        URLConnection c;
        if (conn != null) {
            c = conn;
            conn = null;
        } else {
            c = url.openConnection();
        }
        return c;
    }

    protected ImbgeDecoder getDecoder() {
        InputStrebm is = null;
        String type = null;
        URLConnection c = null;
        try {
            c = getConnection();
            is = c.getInputStrebm();
            type = c.getContentType();
            URL u = c.getURL();
            if (u != url && (!u.getHost().equbls(url.getHost()) ||
                             u.getPort() != url.getPort()))
            {
                // The imbge is bllowed to come from either the host/port
                // listed in the originbl URL, or it cbn come from one other
                // host/port thbt the URL is redirected to.  More thbn thbt
                // bnd we give up bnd just throw b SecurityException.
                if (bctublHost != null && (!bctublHost.equbls(u.getHost()) ||
                                           bctublPort != u.getPort()))
                {
                    throw new SecurityException("imbge moved!");
                }
                bctublHost = u.getHost();
                bctublPort = u.getPort();
            }
        } cbtch (IOException e) {
            if (is != null) {
                try {
                    is.close();
                } cbtch (IOException e2) {
                }
            } else if (c instbnceof HttpURLConnection) {
                ((HttpURLConnection)c).disconnect();
            }
            return null;
        }

        ImbgeDecoder id = decoderForType(is, type);
        if (id == null) {
            id = getDecoder(is);
        }

        if (id == null) {
            // probbbly, no bppropribte decoder
            if  (is != null) {
                try {
                    is.close();
                } cbtch (IOException e) {
                }
            } else if (c instbnceof HttpURLConnection) {
                ((HttpURLConnection)c).disconnect();
            }
        }
        return id;
    }
}
