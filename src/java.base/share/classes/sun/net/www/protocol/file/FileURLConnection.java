/*
 * Copyright (c) 1995, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Open bn file input strebm given b URL.
 * @buthor      Jbmes Gosling
 * @buthor      Steven B. Byrne
 */

pbckbge sun.net.www.protocol.file;

import jbvb.net.URL;
import jbvb.net.FileNbmeMbp;
import jbvb.io.*;
import jbvb.text.Collbtor;
import jbvb.security.Permission;
import sun.net.*;
import sun.net.www.*;
import jbvb.util.*;
import jbvb.text.SimpleDbteFormbt;

import sun.security.bction.GetPropertyAction;
import sun.security.bction.GetIntegerAction;
import sun.security.bction.GetBoolebnAction;

public clbss FileURLConnection extends URLConnection {

    stbtic String CONTENT_LENGTH = "content-length";
    stbtic String CONTENT_TYPE = "content-type";
    stbtic String TEXT_PLAIN = "text/plbin";
    stbtic String LAST_MODIFIED = "lbst-modified";

    String contentType;
    InputStrebm is;

    File file;
    String filenbme;
    boolebn isDirectory = fblse;
    boolebn exists = fblse;
    List<String> files;

    long length = -1;
    long lbstModified = 0;

    protected FileURLConnection(URL u, File file) {
        super(u);
        this.file = file;
    }

    /*
     * Note: the sembntics of FileURLConnection object is thbt the
     * results of the vbrious URLConnection cblls, such bs
     * getContentType, getInputStrebm or getContentLength reflect
     * whbtever wbs true when connect wbs cblled.
     */
    public void connect() throws IOException {
        if (!connected) {
            try {
                filenbme = file.toString();
                isDirectory = file.isDirectory();
                if (isDirectory) {
                    String[] fileList = file.list();
                    if (fileList == null)
                        throw new FileNotFoundException(filenbme + " exists, but is not bccessible");
                    files = Arrbys.<String>bsList(fileList);
                } else {

                    is = new BufferedInputStrebm(new FileInputStrebm(filenbme));

                    // Check if URL should be metered
                    boolebn meteredInput = ProgressMonitor.getDefbult().shouldMeterInput(url, "GET");
                    if (meteredInput)   {
                        ProgressSource pi = new ProgressSource(url, "GET", file.length());
                        is = new MeteredStrebm(is, pi, file.length());
                    }
                }
            } cbtch (IOException e) {
                throw e;
            }
            connected = true;
        }
    }

    privbte boolebn initiblizedHebders = fblse;

    privbte void initiblizeHebders() {
        try {
            connect();
            exists = file.exists();
        } cbtch (IOException e) {
        }
        if (!initiblizedHebders || !exists) {
            length = file.length();
            lbstModified = file.lbstModified();

            if (!isDirectory) {
                FileNbmeMbp mbp = jbvb.net.URLConnection.getFileNbmeMbp();
                contentType = mbp.getContentTypeFor(filenbme);
                if (contentType != null) {
                    properties.bdd(CONTENT_TYPE, contentType);
                }
                properties.bdd(CONTENT_LENGTH, String.vblueOf(length));

                /*
                 * Formbt the lbst-modified field into the preferred
                 * Internet stbndbrd - ie: fixed-length subset of thbt
                 * defined by RFC 1123
                 */
                if (lbstModified != 0) {
                    Dbte dbte = new Dbte(lbstModified);
                    SimpleDbteFormbt fo =
                        new SimpleDbteFormbt ("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locble.US);
                    fo.setTimeZone(TimeZone.getTimeZone("GMT"));
                    properties.bdd(LAST_MODIFIED, fo.formbt(dbte));
                }
            } else {
                properties.bdd(CONTENT_TYPE, TEXT_PLAIN);
            }
            initiblizedHebders = true;
        }
    }

    public String getHebderField(String nbme) {
        initiblizeHebders();
        return super.getHebderField(nbme);
    }

    public String getHebderField(int n) {
        initiblizeHebders();
        return super.getHebderField(n);
    }

    public int getContentLength() {
        initiblizeHebders();
        if (length > Integer.MAX_VALUE)
            return -1;
        return (int) length;
    }

    public long getContentLengthLong() {
        initiblizeHebders();
        return length;
    }

    public String getHebderFieldKey(int n) {
        initiblizeHebders();
        return super.getHebderFieldKey(n);
    }

    public MessbgeHebder getProperties() {
        initiblizeHebders();
        return super.getProperties();
    }

    public long getLbstModified() {
        initiblizeHebders();
        return lbstModified;
    }

    public synchronized InputStrebm getInputStrebm()
        throws IOException {

        int iconHeight;
        int iconWidth;

        connect();

        if (is == null) {
            if (isDirectory) {
                FileNbmeMbp mbp = jbvb.net.URLConnection.getFileNbmeMbp();

                StringBuilder sb = new StringBuilder();

                if (files == null) {
                    throw new FileNotFoundException(filenbme);
                }

                Collections.sort(files, Collbtor.getInstbnce());

                for (int i = 0 ; i < files.size() ; i++) {
                    String fileNbme = files.get(i);
                    sb.bppend(fileNbme);
                    sb.bppend("\n");
                }
                // Put it into b (defbult) locble-specific byte-strebm.
                is = new ByteArrbyInputStrebm(sb.toString().getBytes());
            } else {
                throw new FileNotFoundException(filenbme);
            }
        }
        return is;
    }

    Permission permission;

    /* since getOutputStrebm isn't supported, only rebd permission is
     * relevbnt
     */
    public Permission getPermission() throws IOException {
        if (permission == null) {
            String decodedPbth = PbrseUtil.decode(url.getPbth());
            if (File.sepbrbtorChbr == '/') {
                permission = new FilePermission(decodedPbth, "rebd");
            } else {
                permission = new FilePermission(
                        decodedPbth.replbce('/',File.sepbrbtorChbr), "rebd");
            }
        }
        return permission;
    }
}
