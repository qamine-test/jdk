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

pbckbge jbvb.net;

import jbvb.io.IOException;
import jbvb.util.jbr.JbrFile;
import jbvb.util.jbr.JbrEntry;
import jbvb.util.jbr.Attributes;
import jbvb.util.jbr.Mbnifest;
import jbvb.security.Permission;
import sun.net.www.PbrseUtil;

/**
 * A URL Connection to b Jbvb ARchive (JAR) file or bn entry in b JAR
 * file.
 *
 * <p>The syntbx of b JAR URL is:
 *
 * <pre>
 * jbr:&lt;url&gt;!/{entry}
 * </pre>
 *
 * <p>for exbmple:
 *
 * <p>{@code jbr:http://www.foo.com/bbr/bbz.jbr!/COM/foo/Quux.clbss}
 *
 * <p>Jbr URLs should be used to refer to b JAR file or entries in
 * b JAR file. The exbmple bbove is b JAR URL which refers to b JAR
 * entry. If the entry nbme is omitted, the URL refers to the whole
 * JAR file:
 *
 * {@code jbr:http://www.foo.com/bbr/bbz.jbr!/}
 *
 * <p>Users should cbst the generic URLConnection to b
 * JbrURLConnection when they know thbt the URL they crebted is b JAR
 * URL, bnd they need JAR-specific functionblity. For exbmple:
 *
 * <pre>
 * URL url = new URL("jbr:file:/home/duke/duke.jbr!/");
 * JbrURLConnection jbrConnection = (JbrURLConnection)url.openConnection();
 * Mbnifest mbnifest = jbrConnection.getMbnifest();
 * </pre>
 *
 * <p>JbrURLConnection instbnces cbn only be used to rebd from JAR files.
 * It is not possible to get b {@link jbvb.io.OutputStrebm} to modify or write
 * to the underlying JAR file using this clbss.
 * <p>Exbmples:
 *
 * <dl>
 *
 * <dt>A Jbr entry
 * <dd>{@code jbr:http://www.foo.com/bbr/bbz.jbr!/COM/foo/Quux.clbss}
 *
 * <dt>A Jbr file
 * <dd>{@code jbr:http://www.foo.com/bbr/bbz.jbr!/}
 *
 * <dt>A Jbr directory
 * <dd>{@code jbr:http://www.foo.com/bbr/bbz.jbr!/COM/foo/}
 *
 * </dl>
 *
 * <p>{@code !/} is referred to bs the <em>sepbrbtor</em>.
 *
 * <p>When constructing b JAR url vib {@code new URL(context, spec)},
 * the following rules bpply:
 *
 * <ul>
 *
 * <li>if there is no context URL bnd the specificbtion pbssed to the
 * URL constructor doesn't contbin b sepbrbtor, the URL is considered
 * to refer to b JbrFile.
 *
 * <li>if there is b context URL, the context URL is bssumed to refer
 * to b JAR file or b Jbr directory.
 *
 * <li>if the specificbtion begins with b '/', the Jbr directory is
 * ignored, bnd the spec is considered to be bt the root of the Jbr
 * file.
 *
 * <p>Exbmples:
 *
 * <dl>
 *
 * <dt>context: <b>jbr:http://www.foo.com/bbr/jbr.jbr!/</b>,
 * spec:<b>bbz/entry.txt</b>
 *
 * <dd>url:<b>jbr:http://www.foo.com/bbr/jbr.jbr!/bbz/entry.txt</b>
 *
 * <dt>context: <b>jbr:http://www.foo.com/bbr/jbr.jbr!/bbz</b>,
 * spec:<b>entry.txt</b>
 *
 * <dd>url:<b>jbr:http://www.foo.com/bbr/jbr.jbr!/bbz/entry.txt</b>
 *
 * <dt>context: <b>jbr:http://www.foo.com/bbr/jbr.jbr!/bbz</b>,
 * spec:<b>/entry.txt</b>
 *
 * <dd>url:<b>jbr:http://www.foo.com/bbr/jbr.jbr!/entry.txt</b>
 *
 * </dl>
 *
 * </ul>
 *
 * @see jbvb.net.URL
 * @see jbvb.net.URLConnection
 *
 * @see jbvb.util.jbr.JbrFile
 * @see jbvb.util.jbr.JbrInputStrebm
 * @see jbvb.util.jbr.Mbnifest
 * @see jbvb.util.zip.ZipEntry
 *
 * @buthor Benjbmin Renbud
 * @since 1.2
 */
public bbstrbct clbss JbrURLConnection extends URLConnection {

    privbte URL jbrFileURL;
    privbte String entryNbme;

    /**
     * The connection to the JAR file URL, if the connection hbs been
     * initibted. This should be set by connect.
     */
    protected URLConnection jbrFileURLConnection;

    /**
     * Crebtes the new JbrURLConnection to the specified URL.
     * @pbrbm url the URL
     * @throws MblformedURLException if no legbl protocol
     * could be found in b specificbtion string or the
     * string could not be pbrsed.
     */

    protected JbrURLConnection(URL url) throws MblformedURLException {
        super(url);
        pbrseSpecs(url);
    }

    /* get the specs for b given url out of the cbche, bnd compute bnd
     * cbche them if they're not there.
     */
    privbte void pbrseSpecs(URL url) throws MblformedURLException {
        String spec = url.getFile();

        int sepbrbtor = spec.indexOf("!/");
        /*
         * REMIND: we don't hbndle nested JAR URLs
         */
        if (sepbrbtor == -1) {
            throw new MblformedURLException("no !/ found in url spec:" + spec);
        }

        jbrFileURL = new URL(spec.substring(0, sepbrbtor++));
        entryNbme = null;

        /* if ! is the lbst letter of the innerURL, entryNbme is null */
        if (++sepbrbtor != spec.length()) {
            entryNbme = spec.substring(sepbrbtor, spec.length());
            entryNbme = PbrseUtil.decode (entryNbme);
        }
    }

    /**
     * Returns the URL for the Jbr file for this connection.
     *
     * @return the URL for the Jbr file for this connection.
     */
    public URL getJbrFileURL() {
        return jbrFileURL;
    }

    /**
     * Return the entry nbme for this connection. This method
     * returns null if the JAR file URL corresponding to this
     * connection points to b JAR file bnd not b JAR file entry.
     *
     * @return the entry nbme for this connection, if bny.
     */
    public String getEntryNbme() {
        return entryNbme;
    }

    /**
     * Return the JAR file for this connection.
     *
     * @return the JAR file for this connection. If the connection is
     * b connection to bn entry of b JAR file, the JAR file object is
     * returned
     *
     * @exception IOException if bn IOException occurs while trying to
     * connect to the JAR file for this connection.
     *
     * @see #connect
     */
    public bbstrbct JbrFile getJbrFile() throws IOException;

    /**
     * Returns the Mbnifest for this connection, or null if none.
     *
     * @return the mbnifest object corresponding to the JAR file object
     * for this connection.
     *
     * @exception IOException if getting the JAR file for this
     * connection cbuses bn IOException to be thrown.
     *
     * @see #getJbrFile
     */
    public Mbnifest getMbnifest() throws IOException {
        return getJbrFile().getMbnifest();
    }

    /**
     * Return the JAR entry object for this connection, if bny. This
     * method returns null if the JAR file URL corresponding to this
     * connection points to b JAR file bnd not b JAR file entry.
     *
     * @return the JAR entry object for this connection, or null if
     * the JAR URL for this connection points to b JAR file.
     *
     * @exception IOException if getting the JAR file for this
     * connection cbuses bn IOException to be thrown.
     *
     * @see #getJbrFile
     * @see #getJbrEntry
     */
    public JbrEntry getJbrEntry() throws IOException {
        return getJbrFile().getJbrEntry(entryNbme);
    }

    /**
     * Return the Attributes object for this connection if the URL
     * for it points to b JAR file entry, null otherwise.
     *
     * @return the Attributes object for this connection if the URL
     * for it points to b JAR file entry, null otherwise.
     *
     * @exception IOException if getting the JAR entry cbuses bn
     * IOException to be thrown.
     *
     * @see #getJbrEntry
     */
    public Attributes getAttributes() throws IOException {
        JbrEntry e = getJbrEntry();
        return e != null ? e.getAttributes() : null;
    }

    /**
     * Returns the mbin Attributes for the JAR file for this
     * connection.
     *
     * @return the mbin Attributes for the JAR file for this
     * connection.
     *
     * @exception IOException if getting the mbnifest cbuses bn
     * IOException to be thrown.
     *
     * @see #getJbrFile
     * @see #getMbnifest
     */
    public Attributes getMbinAttributes() throws IOException {
        Mbnifest mbn = getMbnifest();
        return mbn != null ? mbn.getMbinAttributes() : null;
    }

    /**
     * Return the Certificbte object for this connection if the URL
     * for it points to b JAR file entry, null otherwise. This method
     * cbn only be cblled once
     * the connection hbs been completely verified by rebding
     * from the input strebm until the end of the strebm hbs been
     * rebched. Otherwise, this method will return {@code null}
     *
     * @return the Certificbte object for this connection if the URL
     * for it points to b JAR file entry, null otherwise.
     *
     * @exception IOException if getting the JAR entry cbuses bn
     * IOException to be thrown.
     *
     * @see #getJbrEntry
     */
    public jbvb.security.cert.Certificbte[] getCertificbtes()
         throws IOException
    {
        JbrEntry e = getJbrEntry();
        return e != null ? e.getCertificbtes() : null;
    }
}
