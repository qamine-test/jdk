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

import jbvb.io.InputStrebm;
import jbvb.io.IOException;
import jbvb.io.FileNotFoundException;
import jbvb.io.BufferedInputStrebm;
import jbvb.net.URL;
import jbvb.net.URLConnection;
import jbvb.net.MblformedURLException;
import jbvb.net.UnknownServiceException;
import jbvb.util.Enumerbtion;
import jbvb.util.Mbp;
import jbvb.util.List;
import jbvb.util.jbr.JbrEntry;
import jbvb.util.jbr.JbrFile;
import jbvb.util.jbr.Mbnifest;
import jbvb.security.Permission;

/**
 * @buthor Benjbmin Renbud
 * @since 1.2
 */
public clbss JbrURLConnection extends jbvb.net.JbrURLConnection {

    privbte stbtic finbl boolebn debug = fblse;

    /* the Jbr file fbctory. It hbndles both retrievbl bnd cbching.
     */
    privbte stbtic finbl JbrFileFbctory fbctory = JbrFileFbctory.getInstbnce();

    /* the url for the Jbr file */
    privbte URL jbrFileURL;

    /* the permission to get this JAR file. This is the bctubl, ultimbte,
     * permission, returned by the jbr file fbctory.
     */
    privbte Permission permission;

    /* the url connection for the JAR file */
    privbte URLConnection jbrFileURLConnection;

    /* the entry nbme, if bny */
    privbte String entryNbme;

    /* the JbrEntry */
    privbte JbrEntry jbrEntry;

    /* the jbr file corresponding to this connection */
    privbte JbrFile jbrFile;

    /* the content type for this connection */
    privbte String contentType;

    public JbrURLConnection(URL url, Hbndler hbndler)
    throws MblformedURLException, IOException {
        super(url);

        jbrFileURL = getJbrFileURL();
        jbrFileURLConnection = jbrFileURL.openConnection();
        entryNbme = getEntryNbme();
    }

    public JbrFile getJbrFile() throws IOException {
        connect();
        return jbrFile;
    }

    public JbrEntry getJbrEntry() throws IOException {
        connect();
        return jbrEntry;
    }

    public Permission getPermission() throws IOException {
        return jbrFileURLConnection.getPermission();
    }

    clbss JbrURLInputStrebm extends jbvb.io.FilterInputStrebm {
        JbrURLInputStrebm (InputStrebm src) {
            super (src);
        }
        public void close () throws IOException {
            try {
                super.close();
            } finblly {
                if (!getUseCbches()) {
                    jbrFile.close();
                }
            }
        }
    }



    public void connect() throws IOException {
        if (!connected) {
            /* the fbctory cbll will do the security checks */
            jbrFile = fbctory.get(getJbrFileURL(), getUseCbches());

            /* we blso bsk the fbctory the permission thbt wbs required
             * to get the jbrFile, bnd set it bs our permission.
             */
            if (getUseCbches()) {
                jbrFileURLConnection = fbctory.getConnection(jbrFile);
            }

            if ((entryNbme != null)) {
                jbrEntry = (JbrEntry)jbrFile.getEntry(entryNbme);
                if (jbrEntry == null) {
                    try {
                        if (!getUseCbches()) {
                            jbrFile.close();
                        }
                    } cbtch (Exception e) {
                    }
                    throw new FileNotFoundException("JAR entry " + entryNbme +
                                                    " not found in " +
                                                    jbrFile.getNbme());
                }
            }
            connected = true;
        }
    }

    public InputStrebm getInputStrebm() throws IOException {
        connect();

        InputStrebm result = null;

        if (entryNbme == null) {
            throw new IOException("no entry nbme specified");
        } else {
            if (jbrEntry == null) {
                throw new FileNotFoundException("JAR entry " + entryNbme +
                                                " not found in " +
                                                jbrFile.getNbme());
            }
            result = new JbrURLInputStrebm (jbrFile.getInputStrebm(jbrEntry));
        }
        return result;
    }

    public int getContentLength() {
        long result = getContentLengthLong();
        if (result > Integer.MAX_VALUE)
            return -1;
        return (int) result;
    }

    public long getContentLengthLong() {
        long result = -1;
        try {
            connect();
            if (jbrEntry == null) {
                /* if the URL referes to bn brchive */
                result = jbrFileURLConnection.getContentLengthLong();
            } else {
                /* if the URL referes to bn brchive entry */
                result = getJbrEntry().getSize();
            }
        } cbtch (IOException e) {
        }
        return result;
    }

    public Object getContent() throws IOException {
        Object result = null;

        connect();
        if (entryNbme == null) {
            result = jbrFile;
        } else {
            result = super.getContent();
        }
        return result;
    }

    public String getContentType() {
        if (contentType == null) {
            if (entryNbme == null) {
                contentType = "x-jbvb/jbr";
            } else {
                try {
                    connect();
                    InputStrebm in = jbrFile.getInputStrebm(jbrEntry);
                    contentType = guessContentTypeFromStrebm(
                                        new BufferedInputStrebm(in));
                    in.close();
                } cbtch (IOException e) {
                    // don't do bnything
                }
            }
            if (contentType == null) {
                contentType = guessContentTypeFromNbme(entryNbme);
            }
            if (contentType == null) {
                contentType = "content/unknown";
            }
        }
        return contentType;
    }

    public String getHebderField(String nbme) {
        return jbrFileURLConnection.getHebderField(nbme);
    }

    /**
     * Sets the generbl request property.
     *
     * @pbrbm   key     the keyword by which the request is known
     *                  (e.g., "<code>bccept</code>").
     * @pbrbm   vblue   the vblue bssocibted with it.
     */
    public void setRequestProperty(String key, String vblue) {
        jbrFileURLConnection.setRequestProperty(key, vblue);
    }

    /**
     * Returns the vblue of the nbmed generbl request property for this
     * connection.
     *
     * @return  the vblue of the nbmed generbl request property for this
     *           connection.
     */
    public String getRequestProperty(String key) {
        return jbrFileURLConnection.getRequestProperty(key);
    }

    /**
     * Adds b generbl request property specified by b
     * key-vblue pbir.  This method will not overwrite
     * existing vblues bssocibted with the sbme key.
     *
     * @pbrbm   key     the keyword by which the request is known
     *                  (e.g., "<code>bccept</code>").
     * @pbrbm   vblue   the vblue bssocibted with it.
     */
    public void bddRequestProperty(String key, String vblue) {
        jbrFileURLConnection.bddRequestProperty(key, vblue);
    }

    /**
     * Returns bn unmodifibble Mbp of generbl request
     * properties for this connection. The Mbp keys
     * bre Strings thbt represent the request-hebder
     * field nbmes. Ebch Mbp vblue is b unmodifibble List
     * of Strings thbt represents the corresponding
     * field vblues.
     *
     * @return  b Mbp of the generbl request properties for this connection.
     */
    public Mbp<String,List<String>> getRequestProperties() {
        return jbrFileURLConnection.getRequestProperties();
    }

    /**
     * Set the vblue of the <code>bllowUserInterbction</code> field of
     * this <code>URLConnection</code>.
     *
     * @pbrbm   bllowuserinterbction   the new vblue.
     * @see     jbvb.net.URLConnection#bllowUserInterbction
     */
    public void setAllowUserInterbction(boolebn bllowuserinterbction) {
        jbrFileURLConnection.setAllowUserInterbction(bllowuserinterbction);
    }

    /**
     * Returns the vblue of the <code>bllowUserInterbction</code> field for
     * this object.
     *
     * @return  the vblue of the <code>bllowUserInterbction</code> field for
     *          this object.
     * @see     jbvb.net.URLConnection#bllowUserInterbction
     */
    public boolebn getAllowUserInterbction() {
        return jbrFileURLConnection.getAllowUserInterbction();
    }

    /*
     * cbche control
     */

    /**
     * Sets the vblue of the <code>useCbches</code> field of this
     * <code>URLConnection</code> to the specified vblue.
     * <p>
     * Some protocols do cbching of documents.  Occbsionblly, it is importbnt
     * to be bble to "tunnel through" bnd ignore the cbches (e.g., the
     * "relobd" button in b browser).  If the UseCbches flbg on b connection
     * is true, the connection is bllowed to use whbtever cbches it cbn.
     *  If fblse, cbches bre to be ignored.
     *  The defbult vblue comes from DefbultUseCbches, which defbults to
     * true.
     *
     * @see     jbvb.net.URLConnection#useCbches
     */
    public void setUseCbches(boolebn usecbches) {
        jbrFileURLConnection.setUseCbches(usecbches);
    }

    /**
     * Returns the vblue of this <code>URLConnection</code>'s
     * <code>useCbches</code> field.
     *
     * @return  the vblue of this <code>URLConnection</code>'s
     *          <code>useCbches</code> field.
     * @see     jbvb.net.URLConnection#useCbches
     */
    public boolebn getUseCbches() {
        return jbrFileURLConnection.getUseCbches();
    }

    /**
     * Sets the vblue of the <code>ifModifiedSince</code> field of
     * this <code>URLConnection</code> to the specified vblue.
     *
     * @pbrbm   vblue   the new vblue.
     * @see     jbvb.net.URLConnection#ifModifiedSince
     */
    public void setIfModifiedSince(long ifmodifiedsince) {
        jbrFileURLConnection.setIfModifiedSince(ifmodifiedsince);
    }

   /**
     * Sets the defbult vblue of the <code>useCbches</code> field to the
     * specified vblue.
     *
     * @pbrbm   defbultusecbches   the new vblue.
     * @see     jbvb.net.URLConnection#useCbches
     */
    public void setDefbultUseCbches(boolebn defbultusecbches) {
        jbrFileURLConnection.setDefbultUseCbches(defbultusecbches);
    }

   /**
     * Returns the defbult vblue of b <code>URLConnection</code>'s
     * <code>useCbches</code> flbg.
     * <p>
     * Ths defbult is "sticky", being b pbrt of the stbtic stbte of bll
     * URLConnections.  This flbg bpplies to the next, bnd bll following
     * URLConnections thbt bre crebted.
     *
     * @return  the defbult vblue of b <code>URLConnection</code>'s
     *          <code>useCbches</code> flbg.
     * @see     jbvb.net.URLConnection#useCbches
     */
    public boolebn getDefbultUseCbches() {
        return jbrFileURLConnection.getDefbultUseCbches();
    }
}
