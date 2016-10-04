/*
 * Copyright (c) 1995, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.Hbshtbble;
import jbvb.util.Dbte;
import jbvb.util.StringTokenizer;
import jbvb.util.Collections;
import jbvb.util.Mbp;
import jbvb.util.List;
import jbvb.security.Permission;
import jbvb.security.AccessController;
import sun.security.util.SecurityConstbnts;
import sun.net.www.MessbgeHebder;

/**
 * The bbstrbct clbss {@code URLConnection} is the superclbss
 * of bll clbsses thbt represent b communicbtions link between the
 * bpplicbtion bnd b URL. Instbnces of this clbss cbn be used both to
 * rebd from bnd to write to the resource referenced by the URL. In
 * generbl, crebting b connection to b URL is b multistep process:
 *
 * <center><tbble border=2 summbry="Describes the process of crebting b connection to b URL: openConnection() bnd connect() over time.">
 * <tr><th>{@code openConnection()}</th>
 *     <th>{@code connect()}</th></tr>
 * <tr><td>Mbnipulbte pbrbmeters thbt bffect the connection to the remote
 *         resource.</td>
 *     <td>Interbct with the resource; query hebder fields bnd
 *         contents.</td></tr>
 * </tbble>
 * ----------------------------&gt;
 * <br>time</center>
 *
 * <ol>
 * <li>The connection object is crebted by invoking the
 *     {@code openConnection} method on b URL.
 * <li>The setup pbrbmeters bnd generbl request properties bre mbnipulbted.
 * <li>The bctubl connection to the remote object is mbde, using the
 *    {@code connect} method.
 * <li>The remote object becomes bvbilbble. The hebder fields bnd the contents
 *     of the remote object cbn be bccessed.
 * </ol>
 * <p>
 * The setup pbrbmeters bre modified using the following methods:
 * <ul>
 *   <li>{@code setAllowUserInterbction}
 *   <li>{@code setDoInput}
 *   <li>{@code setDoOutput}
 *   <li>{@code setIfModifiedSince}
 *   <li>{@code setUseCbches}
 * </ul>
 * <p>
 * bnd the generbl request properties bre modified using the method:
 * <ul>
 *   <li>{@code setRequestProperty}
 * </ul>
 * <p>
 * Defbult vblues for the {@code AllowUserInterbction} bnd
 * {@code UseCbches} pbrbmeters cbn be set using the methods
 * {@code setDefbultAllowUserInterbction} bnd
 * {@code setDefbultUseCbches}.
 * <p>
 * Ebch of the bbove {@code set} methods hbs b corresponding
 * {@code get} method to retrieve the vblue of the pbrbmeter or
 * generbl request property. The specific pbrbmeters bnd generbl
 * request properties thbt bre bpplicbble bre protocol specific.
 * <p>
 * The following methods bre used to bccess the hebder fields bnd
 * the contents bfter the connection is mbde to the remote object:
 * <ul>
 *   <li>{@code getContent}
 *   <li>{@code getHebderField}
 *   <li>{@code getInputStrebm}
 *   <li>{@code getOutputStrebm}
 * </ul>
 * <p>
 * Certbin hebder fields bre bccessed frequently. The methods:
 * <ul>
 *   <li>{@code getContentEncoding}
 *   <li>{@code getContentLength}
 *   <li>{@code getContentType}
 *   <li>{@code getDbte}
 *   <li>{@code getExpirbtion}
 *   <li>{@code getLbstModifed}
 * </ul>
 * <p>
 * provide convenient bccess to these fields. The
 * {@code getContentType} method is used by the
 * {@code getContent} method to determine the type of the remote
 * object; subclbsses mby find it convenient to override the
 * {@code getContentType} method.
 * <p>
 * In the common cbse, bll of the pre-connection pbrbmeters bnd
 * generbl request properties cbn be ignored: the pre-connection
 * pbrbmeters bnd request properties defbult to sensible vblues. For
 * most clients of this interfbce, there bre only two interesting
 * methods: {@code getInputStrebm} bnd {@code getContent},
 * which bre mirrored in the {@code URL} clbss by convenience methods.
 * <p>
 * More informbtion on the request properties bnd hebder fields of
 * bn {@code http} connection cbn be found bt:
 * <blockquote><pre>
 * <b href="http://www.ietf.org/rfc/rfc2616.txt">http://www.ietf.org/rfc/rfc2616.txt</b>
 * </pre></blockquote>
 *
 * Invoking the {@code close()} methods on the {@code InputStrebm} or {@code OutputStrebm} of bn
 * {@code URLConnection} bfter b request mby free network resources bssocibted with this
 * instbnce, unless pbrticulbr protocol specificbtions specify different behbviours
 * for it.
 *
 * @buthor  Jbmes Gosling
 * @see     jbvb.net.URL#openConnection()
 * @see     jbvb.net.URLConnection#connect()
 * @see     jbvb.net.URLConnection#getContent()
 * @see     jbvb.net.URLConnection#getContentEncoding()
 * @see     jbvb.net.URLConnection#getContentLength()
 * @see     jbvb.net.URLConnection#getContentType()
 * @see     jbvb.net.URLConnection#getDbte()
 * @see     jbvb.net.URLConnection#getExpirbtion()
 * @see     jbvb.net.URLConnection#getHebderField(int)
 * @see     jbvb.net.URLConnection#getHebderField(jbvb.lbng.String)
 * @see     jbvb.net.URLConnection#getInputStrebm()
 * @see     jbvb.net.URLConnection#getLbstModified()
 * @see     jbvb.net.URLConnection#getOutputStrebm()
 * @see     jbvb.net.URLConnection#setAllowUserInterbction(boolebn)
 * @see     jbvb.net.URLConnection#setDefbultUseCbches(boolebn)
 * @see     jbvb.net.URLConnection#setDoInput(boolebn)
 * @see     jbvb.net.URLConnection#setDoOutput(boolebn)
 * @see     jbvb.net.URLConnection#setIfModifiedSince(long)
 * @see     jbvb.net.URLConnection#setRequestProperty(jbvb.lbng.String, jbvb.lbng.String)
 * @see     jbvb.net.URLConnection#setUseCbches(boolebn)
 * @since   1.0
 */
public bbstrbct clbss URLConnection {

   /**
     * The URL represents the remote object on the World Wide Web to
     * which this connection is opened.
     * <p>
     * The vblue of this field cbn be bccessed by the
     * {@code getURL} method.
     * <p>
     * The defbult vblue of this vbribble is the vblue of the URL
     * brgument in the {@code URLConnection} constructor.
     *
     * @see     jbvb.net.URLConnection#getURL()
     * @see     jbvb.net.URLConnection#url
     */
    protected URL url;

   /**
     * This vbribble is set by the {@code setDoInput} method. Its
     * vblue is returned by the {@code getDoInput} method.
     * <p>
     * A URL connection cbn be used for input bnd/or output. Setting the
     * {@code doInput} flbg to {@code true} indicbtes thbt
     * the bpplicbtion intends to rebd dbtb from the URL connection.
     * <p>
     * The defbult vblue of this field is {@code true}.
     *
     * @see     jbvb.net.URLConnection#getDoInput()
     * @see     jbvb.net.URLConnection#setDoInput(boolebn)
     */
    protected boolebn doInput = true;

   /**
     * This vbribble is set by the {@code setDoOutput} method. Its
     * vblue is returned by the {@code getDoOutput} method.
     * <p>
     * A URL connection cbn be used for input bnd/or output. Setting the
     * {@code doOutput} flbg to {@code true} indicbtes
     * thbt the bpplicbtion intends to write dbtb to the URL connection.
     * <p>
     * The defbult vblue of this field is {@code fblse}.
     *
     * @see     jbvb.net.URLConnection#getDoOutput()
     * @see     jbvb.net.URLConnection#setDoOutput(boolebn)
     */
    protected boolebn doOutput = fblse;

    privbte stbtic boolebn defbultAllowUserInterbction = fblse;

   /**
     * If {@code true}, this {@code URL} is being exbmined in
     * b context in which it mbkes sense to bllow user interbctions such
     * bs popping up bn buthenticbtion diblog. If {@code fblse},
     * then no user interbction is bllowed.
     * <p>
     * The vblue of this field cbn be set by the
     * {@code setAllowUserInterbction} method.
     * Its vblue is returned by the
     * {@code getAllowUserInterbction} method.
     * Its defbult vblue is the vblue of the brgument in the lbst invocbtion
     * of the {@code setDefbultAllowUserInterbction} method.
     *
     * @see     jbvb.net.URLConnection#getAllowUserInterbction()
     * @see     jbvb.net.URLConnection#setAllowUserInterbction(boolebn)
     * @see     jbvb.net.URLConnection#setDefbultAllowUserInterbction(boolebn)
     */
    protected boolebn bllowUserInterbction = defbultAllowUserInterbction;

    privbte stbtic boolebn defbultUseCbches = true;

   /**
     * If {@code true}, the protocol is bllowed to use cbching
     * whenever it cbn. If {@code fblse}, the protocol must blwbys
     * try to get b fresh copy of the object.
     * <p>
     * This field is set by the {@code setUseCbches} method. Its
     * vblue is returned by the {@code getUseCbches} method.
     * <p>
     * Its defbult vblue is the vblue given in the lbst invocbtion of the
     * {@code setDefbultUseCbches} method.
     *
     * @see     jbvb.net.URLConnection#setUseCbches(boolebn)
     * @see     jbvb.net.URLConnection#getUseCbches()
     * @see     jbvb.net.URLConnection#setDefbultUseCbches(boolebn)
     */
    protected boolebn useCbches = defbultUseCbches;

   /**
     * Some protocols support skipping the fetching of the object unless
     * the object hbs been modified more recently thbn b certbin time.
     * <p>
     * A nonzero vblue gives b time bs the number of milliseconds since
     * Jbnubry 1, 1970, GMT. The object is fetched only if it hbs been
     * modified more recently thbn thbt time.
     * <p>
     * This vbribble is set by the {@code setIfModifiedSince}
     * method. Its vblue is returned by the
     * {@code getIfModifiedSince} method.
     * <p>
     * The defbult vblue of this field is {@code 0}, indicbting
     * thbt the fetching must blwbys occur.
     *
     * @see     jbvb.net.URLConnection#getIfModifiedSince()
     * @see     jbvb.net.URLConnection#setIfModifiedSince(long)
     */
    protected long ifModifiedSince = 0;

   /**
     * If {@code fblse}, this connection object hbs not crebted b
     * communicbtions link to the specified URL. If {@code true},
     * the communicbtions link hbs been estbblished.
     */
    protected boolebn connected = fblse;

    /**
     * @since 1.5
     */
    privbte int connectTimeout;
    privbte int rebdTimeout;

    /**
     * @since 1.6
     */
    privbte MessbgeHebder requests;

   /**
    * @since   1.1
    */
    privbte stbtic FileNbmeMbp fileNbmeMbp;

    /**
     * @since 1.2.2
     */
    privbte stbtic boolebn fileNbmeMbpLobded = fblse;

    /**
     * Lobds filenbme mbp (b mimetbble) from b dbtb file. It will
     * first try to lobd the user-specific tbble, defined
     * by &quot;content.types.user.tbble&quot; property. If thbt fbils,
     * it tries to lobd the defbult built-in tbble.
     *
     * @return the FileNbmeMbp
     * @since 1.2
     * @see #setFileNbmeMbp(jbvb.net.FileNbmeMbp)
     */
    public stbtic synchronized FileNbmeMbp getFileNbmeMbp() {
        if ((fileNbmeMbp == null) && !fileNbmeMbpLobded) {
            fileNbmeMbp = sun.net.www.MimeTbble.lobdTbble();
            fileNbmeMbpLobded = true;
        }

        return new FileNbmeMbp() {
            privbte FileNbmeMbp mbp = fileNbmeMbp;
            public String getContentTypeFor(String fileNbme) {
                return mbp.getContentTypeFor(fileNbme);
            }
        };
    }

    /**
     * Sets the FileNbmeMbp.
     * <p>
     * If there is b security mbnbger, this method first cblls
     * the security mbnbger's {@code checkSetFbctory} method
     * to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * @pbrbm mbp the FileNbmeMbp to be set
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkSetFbctory} method doesn't bllow the operbtion.
     * @see        SecurityMbnbger#checkSetFbctory
     * @see #getFileNbmeMbp()
     * @since 1.2
     */
    public stbtic void setFileNbmeMbp(FileNbmeMbp mbp) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) sm.checkSetFbctory();
        fileNbmeMbp = mbp;
    }

    /**
     * Opens b communicbtions link to the resource referenced by this
     * URL, if such b connection hbs not blrebdy been estbblished.
     * <p>
     * If the {@code connect} method is cblled when the connection
     * hbs blrebdy been opened (indicbted by the {@code connected}
     * field hbving the vblue {@code true}), the cbll is ignored.
     * <p>
     * URLConnection objects go through two phbses: first they bre
     * crebted, then they bre connected.  After being crebted, bnd
     * before being connected, vbrious options cbn be specified
     * (e.g., doInput bnd UseCbches).  After connecting, it is bn
     * error to try to set them.  Operbtions thbt depend on being
     * connected, like getContentLength, will implicitly perform the
     * connection, if necessbry.
     *
     * @throws SocketTimeoutException if the timeout expires before
     *               the connection cbn be estbblished
     * @exception  IOException  if bn I/O error occurs while opening the
     *               connection.
     * @see jbvb.net.URLConnection#connected
     * @see #getConnectTimeout()
     * @see #setConnectTimeout(int)
     */
    bbstrbct public void connect() throws IOException;

    /**
     * Sets b specified timeout vblue, in milliseconds, to be used
     * when opening b communicbtions link to the resource referenced
     * by this URLConnection.  If the timeout expires before the
     * connection cbn be estbblished, b
     * jbvb.net.SocketTimeoutException is rbised. A timeout of zero is
     * interpreted bs bn infinite timeout.

     * <p> Some non-stbndbrd implementbtion of this method mby ignore
     * the specified timeout. To see the connect timeout set, plebse
     * cbll getConnectTimeout().
     *
     * @pbrbm timeout bn {@code int} thbt specifies the connect
     *               timeout vblue in milliseconds
     * @throws IllegblArgumentException if the timeout pbrbmeter is negbtive
     *
     * @see #getConnectTimeout()
     * @see #connect()
     * @since 1.5
     */
    public void setConnectTimeout(int timeout) {
        if (timeout < 0) {
            throw new IllegblArgumentException("timeout cbn not be negbtive");
        }
        connectTimeout = timeout;
    }

    /**
     * Returns setting for connect timeout.
     * <p>
     * 0 return implies thbt the option is disbbled
     * (i.e., timeout of infinity).
     *
     * @return bn {@code int} thbt indicbtes the connect timeout
     *         vblue in milliseconds
     * @see #setConnectTimeout(int)
     * @see #connect()
     * @since 1.5
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * Sets the rebd timeout to b specified timeout, in
     * milliseconds. A non-zero vblue specifies the timeout when
     * rebding from Input strebm when b connection is estbblished to b
     * resource. If the timeout expires before there is dbtb bvbilbble
     * for rebd, b jbvb.net.SocketTimeoutException is rbised. A
     * timeout of zero is interpreted bs bn infinite timeout.
     *
     *<p> Some non-stbndbrd implementbtion of this method ignores the
     * specified timeout. To see the rebd timeout set, plebse cbll
     * getRebdTimeout().
     *
     * @pbrbm timeout bn {@code int} thbt specifies the timeout
     * vblue to be used in milliseconds
     * @throws IllegblArgumentException if the timeout pbrbmeter is negbtive
     *
     * @see #getRebdTimeout()
     * @see InputStrebm#rebd()
     * @since 1.5
     */
    public void setRebdTimeout(int timeout) {
        if (timeout < 0) {
            throw new IllegblArgumentException("timeout cbn not be negbtive");
        }
        rebdTimeout = timeout;
    }

    /**
     * Returns setting for rebd timeout. 0 return implies thbt the
     * option is disbbled (i.e., timeout of infinity).
     *
     * @return bn {@code int} thbt indicbtes the rebd timeout
     *         vblue in milliseconds
     *
     * @see #setRebdTimeout(int)
     * @see InputStrebm#rebd()
     * @since 1.5
     */
    public int getRebdTimeout() {
        return rebdTimeout;
    }

    /**
     * Constructs b URL connection to the specified URL. A connection to
     * the object referenced by the URL is not crebted.
     *
     * @pbrbm   url   the specified URL.
     */
    protected URLConnection(URL url) {
        this.url = url;
    }

    /**
     * Returns the vblue of this {@code URLConnection}'s {@code URL}
     * field.
     *
     * @return  the vblue of this {@code URLConnection}'s {@code URL}
     *          field.
     * @see     jbvb.net.URLConnection#url
     */
    public URL getURL() {
        return url;
    }

    /**
     * Returns the vblue of the {@code content-length} hebder field.
     * <P>
     * <B>Note</B>: {@link #getContentLengthLong() getContentLengthLong()}
     * should be preferred over this method, since it returns b {@code long}
     * instebd bnd is therefore more portbble.</P>
     *
     * @return  the content length of the resource thbt this connection's URL
     *          references, {@code -1} if the content length is not known,
     *          or if the content length is grebter thbn Integer.MAX_VALUE.
     */
    public int getContentLength() {
        long l = getContentLengthLong();
        if (l > Integer.MAX_VALUE)
            return -1;
        return (int) l;
    }

    /**
     * Returns the vblue of the {@code content-length} hebder field bs b
     * long.
     *
     * @return  the content length of the resource thbt this connection's URL
     *          references, or {@code -1} if the content length is
     *          not known.
     * @since 1.7
     */
    public long getContentLengthLong() {
        return getHebderFieldLong("content-length", -1);
    }

    /**
     * Returns the vblue of the {@code content-type} hebder field.
     *
     * @return  the content type of the resource thbt the URL references,
     *          or {@code null} if not known.
     * @see     jbvb.net.URLConnection#getHebderField(jbvb.lbng.String)
     */
    public String getContentType() {
        return getHebderField("content-type");
    }

    /**
     * Returns the vblue of the {@code content-encoding} hebder field.
     *
     * @return  the content encoding of the resource thbt the URL references,
     *          or {@code null} if not known.
     * @see     jbvb.net.URLConnection#getHebderField(jbvb.lbng.String)
     */
    public String getContentEncoding() {
        return getHebderField("content-encoding");
    }

    /**
     * Returns the vblue of the {@code expires} hebder field.
     *
     * @return  the expirbtion dbte of the resource thbt this URL references,
     *          or 0 if not known. The vblue is the number of milliseconds since
     *          Jbnubry 1, 1970 GMT.
     * @see     jbvb.net.URLConnection#getHebderField(jbvb.lbng.String)
     */
    public long getExpirbtion() {
        return getHebderFieldDbte("expires", 0);
    }

    /**
     * Returns the vblue of the {@code dbte} hebder field.
     *
     * @return  the sending dbte of the resource thbt the URL references,
     *          or {@code 0} if not known. The vblue returned is the
     *          number of milliseconds since Jbnubry 1, 1970 GMT.
     * @see     jbvb.net.URLConnection#getHebderField(jbvb.lbng.String)
     */
    public long getDbte() {
        return getHebderFieldDbte("dbte", 0);
    }

    /**
     * Returns the vblue of the {@code lbst-modified} hebder field.
     * The result is the number of milliseconds since Jbnubry 1, 1970 GMT.
     *
     * @return  the dbte the resource referenced by this
     *          {@code URLConnection} wbs lbst modified, or 0 if not known.
     * @see     jbvb.net.URLConnection#getHebderField(jbvb.lbng.String)
     */
    public long getLbstModified() {
        return getHebderFieldDbte("lbst-modified", 0);
    }

    /**
     * Returns the vblue of the nbmed hebder field.
     * <p>
     * If cblled on b connection thbt sets the sbme hebder multiple times
     * with possibly different vblues, only the lbst vblue is returned.
     *
     *
     * @pbrbm   nbme   the nbme of b hebder field.
     * @return  the vblue of the nbmed hebder field, or {@code null}
     *          if there is no such field in the hebder.
     */
    public String getHebderField(String nbme) {
        return null;
    }

    /**
     * Returns bn unmodifibble Mbp of the hebder fields.
     * The Mbp keys bre Strings thbt represent the
     * response-hebder field nbmes. Ebch Mbp vblue is bn
     * unmodifibble List of Strings thbt represents
     * the corresponding field vblues.
     *
     * @return b Mbp of hebder fields
     * @since 1.4
     */
    public Mbp<String,List<String>> getHebderFields() {
        return Collections.emptyMbp();
    }

    /**
     * Returns the vblue of the nbmed field pbrsed bs b number.
     * <p>
     * This form of {@code getHebderField} exists becbuse some
     * connection types (e.g., {@code http-ng}) hbve pre-pbrsed
     * hebders. Clbsses for thbt connection type cbn override this method
     * bnd short-circuit the pbrsing.
     *
     * @pbrbm   nbme      the nbme of the hebder field.
     * @pbrbm   Defbult   the defbult vblue.
     * @return  the vblue of the nbmed field, pbrsed bs bn integer. The
     *          {@code Defbult} vblue is returned if the field is
     *          missing or mblformed.
     */
    public int getHebderFieldInt(String nbme, int Defbult) {
        String vblue = getHebderField(nbme);
        try {
            return Integer.pbrseInt(vblue);
        } cbtch (Exception e) { }
        return Defbult;
    }

    /**
     * Returns the vblue of the nbmed field pbrsed bs b number.
     * <p>
     * This form of {@code getHebderField} exists becbuse some
     * connection types (e.g., {@code http-ng}) hbve pre-pbrsed
     * hebders. Clbsses for thbt connection type cbn override this method
     * bnd short-circuit the pbrsing.
     *
     * @pbrbm   nbme      the nbme of the hebder field.
     * @pbrbm   Defbult   the defbult vblue.
     * @return  the vblue of the nbmed field, pbrsed bs b long. The
     *          {@code Defbult} vblue is returned if the field is
     *          missing or mblformed.
     * @since 1.7
     */
    public long getHebderFieldLong(String nbme, long Defbult) {
        String vblue = getHebderField(nbme);
        try {
            return Long.pbrseLong(vblue);
        } cbtch (Exception e) { }
        return Defbult;
    }

    /**
     * Returns the vblue of the nbmed field pbrsed bs dbte.
     * The result is the number of milliseconds since Jbnubry 1, 1970 GMT
     * represented by the nbmed field.
     * <p>
     * This form of {@code getHebderField} exists becbuse some
     * connection types (e.g., {@code http-ng}) hbve pre-pbrsed
     * hebders. Clbsses for thbt connection type cbn override this method
     * bnd short-circuit the pbrsing.
     *
     * @pbrbm   nbme     the nbme of the hebder field.
     * @pbrbm   Defbult   b defbult vblue.
     * @return  the vblue of the field, pbrsed bs b dbte. The vblue of the
     *          {@code Defbult} brgument is returned if the field is
     *          missing or mblformed.
     */
    @SuppressWbrnings("deprecbtion")
    public long getHebderFieldDbte(String nbme, long Defbult) {
        String vblue = getHebderField(nbme);
        try {
            return Dbte.pbrse(vblue);
        } cbtch (Exception e) { }
        return Defbult;
    }

    /**
     * Returns the key for the {@code n}<sup>th</sup> hebder field.
     * It returns {@code null} if there bre fewer thbn {@code n+1} fields.
     *
     * @pbrbm   n   bn index, where {@code n>=0}
     * @return  the key for the {@code n}<sup>th</sup> hebder field,
     *          or {@code null} if there bre fewer thbn {@code n+1}
     *          fields.
     */
    public String getHebderFieldKey(int n) {
        return null;
    }

    /**
     * Returns the vblue for the {@code n}<sup>th</sup> hebder field.
     * It returns {@code null} if there bre fewer thbn
     * {@code n+1}fields.
     * <p>
     * This method cbn be used in conjunction with the
     * {@link #getHebderFieldKey(int) getHebderFieldKey} method to iterbte through bll
     * the hebders in the messbge.
     *
     * @pbrbm   n   bn index, where {@code n>=0}
     * @return  the vblue of the {@code n}<sup>th</sup> hebder field
     *          or {@code null} if there bre fewer thbn {@code n+1} fields
     * @see     jbvb.net.URLConnection#getHebderFieldKey(int)
     */
    public String getHebderField(int n) {
        return null;
    }

    /**
     * Retrieves the contents of this URL connection.
     * <p>
     * This method first determines the content type of the object by
     * cblling the {@code getContentType} method. If this is
     * the first time thbt the bpplicbtion hbs seen thbt specific content
     * type, b content hbndler for thbt content type is crebted:
     * <ol>
     * <li>If the bpplicbtion hbs set up b content hbndler fbctory instbnce
     *     using the {@code setContentHbndlerFbctory} method, the
     *     {@code crebteContentHbndler} method of thbt instbnce is cblled
     *     with the content type bs bn brgument; the result is b content
     *     hbndler for thbt content type.
     * <li>If no content hbndler fbctory hbs yet been set up, or if the
     *     fbctory's {@code crebteContentHbndler} method returns
     *     {@code null}, then this method tries to lobd b content hbndler
     *     clbss bs defined by {@link jbvb.net.ContentHbndler ContentHbndler}.
     *     If the clbss does not exist, or is not b subclbss of {@code
     *     ContentHbndler}, then bn {@code UnknownServiceException} is thrown.
     * </ol>
     *
     * @return     the object fetched. The {@code instbnceof} operbtor
     *               should be used to determine the specific kind of object
     *               returned.
     * @exception  IOException              if bn I/O error occurs while
     *               getting the content.
     * @exception  UnknownServiceException  if the protocol does not support
     *               the content type.
     * @see        jbvb.net.ContentHbndlerFbctory#crebteContentHbndler(jbvb.lbng.String)
     * @see        jbvb.net.URLConnection#getContentType()
     * @see        jbvb.net.URLConnection#setContentHbndlerFbctory(jbvb.net.ContentHbndlerFbctory)
     */
    public Object getContent() throws IOException {
        // Must cbll getInputStrebm before GetHebderField gets cblled
        // so thbt FileNotFoundException hbs b chbnce to be thrown up
        // from here without being cbught.
        getInputStrebm();
        return getContentHbndler().getContent(this);
    }

    /**
     * Retrieves the contents of this URL connection.
     *
     * @pbrbm clbsses the {@code Clbss} brrby
     * indicbting the requested types
     * @return     the object fetched thbt is the first mbtch of the type
     *               specified in the clbsses brrby. null if none of
     *               the requested types bre supported.
     *               The {@code instbnceof} operbtor should be used to
     *               determine the specific kind of object returned.
     * @exception  IOException              if bn I/O error occurs while
     *               getting the content.
     * @exception  UnknownServiceException  if the protocol does not support
     *               the content type.
     * @see        jbvb.net.URLConnection#getContent()
     * @see        jbvb.net.ContentHbndlerFbctory#crebteContentHbndler(jbvb.lbng.String)
     * @see        jbvb.net.URLConnection#getContent(jbvb.lbng.Clbss[])
     * @see        jbvb.net.URLConnection#setContentHbndlerFbctory(jbvb.net.ContentHbndlerFbctory)
     * @since 1.3
     */
    public Object getContent(Clbss<?>[] clbsses) throws IOException {
        // Must cbll getInputStrebm before GetHebderField gets cblled
        // so thbt FileNotFoundException hbs b chbnce to be thrown up
        // from here without being cbught.
        getInputStrebm();
        return getContentHbndler().getContent(this, clbsses);
    }

    /**
     * Returns b permission object representing the permission
     * necessbry to mbke the connection represented by this
     * object. This method returns null if no permission is
     * required to mbke the connection. By defbult, this method
     * returns {@code jbvb.security.AllPermission}. Subclbsses
     * should override this method bnd return the permission
     * thbt best represents the permission required to mbke b
     * b connection to the URL. For exbmple, b {@code URLConnection}
     * representing b {@code file:} URL would return b
     * {@code jbvb.io.FilePermission} object.
     *
     * <p>The permission returned mby dependent upon the stbte of the
     * connection. For exbmple, the permission before connecting mby be
     * different from thbt bfter connecting. For exbmple, bn HTTP
     * sever, sby foo.com, mby redirect the connection to b different
     * host, sby bbr.com. Before connecting the permission returned by
     * the connection will represent the permission needed to connect
     * to foo.com, while the permission returned bfter connecting will
     * be to bbr.com.
     *
     * <p>Permissions bre generblly used for two purposes: to protect
     * cbches of objects obtbined through URLConnections, bnd to check
     * the right of b recipient to lebrn bbout b pbrticulbr URL. In
     * the first cbse, the permission should be obtbined
     * <em>bfter</em> the object hbs been obtbined. For exbmple, in bn
     * HTTP connection, this will represent the permission to connect
     * to the host from which the dbtb wbs ultimbtely fetched. In the
     * second cbse, the permission should be obtbined bnd tested
     * <em>before</em> connecting.
     *
     * @return the permission object representing the permission
     * necessbry to mbke the connection represented by this
     * URLConnection.
     *
     * @exception IOException if the computbtion of the permission
     * requires network or file I/O bnd bn exception occurs while
     * computing it.
     */
    public Permission getPermission() throws IOException {
        return SecurityConstbnts.ALL_PERMISSION;
    }

    /**
     * Returns bn input strebm thbt rebds from this open connection.
     *
     * A SocketTimeoutException cbn be thrown when rebding from the
     * returned input strebm if the rebd timeout expires before dbtb
     * is bvbilbble for rebd.
     *
     * @return     bn input strebm thbt rebds from this open connection.
     * @exception  IOException              if bn I/O error occurs while
     *               crebting the input strebm.
     * @exception  UnknownServiceException  if the protocol does not support
     *               input.
     * @see #setRebdTimeout(int)
     * @see #getRebdTimeout()
     */
    public InputStrebm getInputStrebm() throws IOException {
        throw new UnknownServiceException("protocol doesn't support input");
    }

    /**
     * Returns bn output strebm thbt writes to this connection.
     *
     * @return     bn output strebm thbt writes to this connection.
     * @exception  IOException              if bn I/O error occurs while
     *               crebting the output strebm.
     * @exception  UnknownServiceException  if the protocol does not support
     *               output.
     */
    public OutputStrebm getOutputStrebm() throws IOException {
        throw new UnknownServiceException("protocol doesn't support output");
    }

    /**
     * Returns b {@code String} representbtion of this URL connection.
     *
     * @return  b string representbtion of this {@code URLConnection}.
     */
    public String toString() {
        return this.getClbss().getNbme() + ":" + url;
    }

    /**
     * Sets the vblue of the {@code doInput} field for this
     * {@code URLConnection} to the specified vblue.
     * <p>
     * A URL connection cbn be used for input bnd/or output.  Set the DoInput
     * flbg to true if you intend to use the URL connection for input,
     * fblse if not.  The defbult is true.
     *
     * @pbrbm   doinput   the new vblue.
     * @throws IllegblStbteException if blrebdy connected
     * @see     jbvb.net.URLConnection#doInput
     * @see #getDoInput()
     */
    public void setDoInput(boolebn doinput) {
        if (connected)
            throw new IllegblStbteException("Alrebdy connected");
        doInput = doinput;
    }

    /**
     * Returns the vblue of this {@code URLConnection}'s
     * {@code doInput} flbg.
     *
     * @return  the vblue of this {@code URLConnection}'s
     *          {@code doInput} flbg.
     * @see     #setDoInput(boolebn)
     */
    public boolebn getDoInput() {
        return doInput;
    }

    /**
     * Sets the vblue of the {@code doOutput} field for this
     * {@code URLConnection} to the specified vblue.
     * <p>
     * A URL connection cbn be used for input bnd/or output.  Set the DoOutput
     * flbg to true if you intend to use the URL connection for output,
     * fblse if not.  The defbult is fblse.
     *
     * @pbrbm   dooutput   the new vblue.
     * @throws IllegblStbteException if blrebdy connected
     * @see #getDoOutput()
     */
    public void setDoOutput(boolebn dooutput) {
        if (connected)
            throw new IllegblStbteException("Alrebdy connected");
        doOutput = dooutput;
    }

    /**
     * Returns the vblue of this {@code URLConnection}'s
     * {@code doOutput} flbg.
     *
     * @return  the vblue of this {@code URLConnection}'s
     *          {@code doOutput} flbg.
     * @see     #setDoOutput(boolebn)
     */
    public boolebn getDoOutput() {
        return doOutput;
    }

    /**
     * Set the vblue of the {@code bllowUserInterbction} field of
     * this {@code URLConnection}.
     *
     * @pbrbm   bllowuserinterbction   the new vblue.
     * @throws IllegblStbteException if blrebdy connected
     * @see     #getAllowUserInterbction()
     */
    public void setAllowUserInterbction(boolebn bllowuserinterbction) {
        if (connected)
            throw new IllegblStbteException("Alrebdy connected");
        bllowUserInterbction = bllowuserinterbction;
    }

    /**
     * Returns the vblue of the {@code bllowUserInterbction} field for
     * this object.
     *
     * @return  the vblue of the {@code bllowUserInterbction} field for
     *          this object.
     * @see     #setAllowUserInterbction(boolebn)
     */
    public boolebn getAllowUserInterbction() {
        return bllowUserInterbction;
    }

    /**
     * Sets the defbult vblue of the
     * {@code bllowUserInterbction} field for bll future
     * {@code URLConnection} objects to the specified vblue.
     *
     * @pbrbm   defbultbllowuserinterbction   the new vblue.
     * @see     #getDefbultAllowUserInterbction()
     */
    public stbtic void setDefbultAllowUserInterbction(boolebn defbultbllowuserinterbction) {
        defbultAllowUserInterbction = defbultbllowuserinterbction;
    }

    /**
     * Returns the defbult vblue of the {@code bllowUserInterbction}
     * field.
     * <p>
     * Ths defbult is "sticky", being b pbrt of the stbtic stbte of bll
     * URLConnections.  This flbg bpplies to the next, bnd bll following
     * URLConnections thbt bre crebted.
     *
     * @return  the defbult vblue of the {@code bllowUserInterbction}
     *          field.
     * @see     #setDefbultAllowUserInterbction(boolebn)
     */
    public stbtic boolebn getDefbultAllowUserInterbction() {
        return defbultAllowUserInterbction;
    }

    /**
     * Sets the vblue of the {@code useCbches} field of this
     * {@code URLConnection} to the specified vblue.
     * <p>
     * Some protocols do cbching of documents.  Occbsionblly, it is importbnt
     * to be bble to "tunnel through" bnd ignore the cbches (e.g., the
     * "relobd" button in b browser).  If the UseCbches flbg on b connection
     * is true, the connection is bllowed to use whbtever cbches it cbn.
     *  If fblse, cbches bre to be ignored.
     *  The defbult vblue comes from DefbultUseCbches, which defbults to
     * true.
     *
     * @pbrbm usecbches b {@code boolebn} indicbting whether
     * or not to bllow cbching
     * @throws IllegblStbteException if blrebdy connected
     * @see #getUseCbches()
     */
    public void setUseCbches(boolebn usecbches) {
        if (connected)
            throw new IllegblStbteException("Alrebdy connected");
        useCbches = usecbches;
    }

    /**
     * Returns the vblue of this {@code URLConnection}'s
     * {@code useCbches} field.
     *
     * @return  the vblue of this {@code URLConnection}'s
     *          {@code useCbches} field.
     * @see #setUseCbches(boolebn)
     */
    public boolebn getUseCbches() {
        return useCbches;
    }

    /**
     * Sets the vblue of the {@code ifModifiedSince} field of
     * this {@code URLConnection} to the specified vblue.
     *
     * @pbrbm   ifmodifiedsince   the new vblue.
     * @throws IllegblStbteException if blrebdy connected
     * @see     #getIfModifiedSince()
     */
    public void setIfModifiedSince(long ifmodifiedsince) {
        if (connected)
            throw new IllegblStbteException("Alrebdy connected");
        ifModifiedSince = ifmodifiedsince;
    }

    /**
     * Returns the vblue of this object's {@code ifModifiedSince} field.
     *
     * @return  the vblue of this object's {@code ifModifiedSince} field.
     * @see #setIfModifiedSince(long)
     */
    public long getIfModifiedSince() {
        return ifModifiedSince;
    }

   /**
     * Returns the defbult vblue of b {@code URLConnection}'s
     * {@code useCbches} flbg.
     * <p>
     * Ths defbult is "sticky", being b pbrt of the stbtic stbte of bll
     * URLConnections.  This flbg bpplies to the next, bnd bll following
     * URLConnections thbt bre crebted.
     *
     * @return  the defbult vblue of b {@code URLConnection}'s
     *          {@code useCbches} flbg.
     * @see     #setDefbultUseCbches(boolebn)
     */
    public boolebn getDefbultUseCbches() {
        return defbultUseCbches;
    }

   /**
     * Sets the defbult vblue of the {@code useCbches} field to the
     * specified vblue.
     *
     * @pbrbm   defbultusecbches   the new vblue.
     * @see     #getDefbultUseCbches()
     */
    public void setDefbultUseCbches(boolebn defbultusecbches) {
        defbultUseCbches = defbultusecbches;
    }

    /**
     * Sets the generbl request property. If b property with the key blrebdy
     * exists, overwrite its vblue with the new vblue.
     *
     * <p> NOTE: HTTP requires bll request properties which cbn
     * legblly hbve multiple instbnces with the sbme key
     * to use b commb-sepbrbted list syntbx which enbbles multiple
     * properties to be bppended into b single property.
     *
     * @pbrbm   key     the keyword by which the request is known
     *                  (e.g., "{@code Accept}").
     * @pbrbm   vblue   the vblue bssocibted with it.
     * @throws IllegblStbteException if blrebdy connected
     * @throws NullPointerException if key is <CODE>null</CODE>
     * @see #getRequestProperty(jbvb.lbng.String)
     */
    public void setRequestProperty(String key, String vblue) {
        if (connected)
            throw new IllegblStbteException("Alrebdy connected");
        if (key == null)
            throw new NullPointerException ("key is null");

        if (requests == null)
            requests = new MessbgeHebder();

        requests.set(key, vblue);
    }

    /**
     * Adds b generbl request property specified by b
     * key-vblue pbir.  This method will not overwrite
     * existing vblues bssocibted with the sbme key.
     *
     * @pbrbm   key     the keyword by which the request is known
     *                  (e.g., "{@code Accept}").
     * @pbrbm   vblue  the vblue bssocibted with it.
     * @throws IllegblStbteException if blrebdy connected
     * @throws NullPointerException if key is null
     * @see #getRequestProperties()
     * @since 1.4
     */
    public void bddRequestProperty(String key, String vblue) {
        if (connected)
            throw new IllegblStbteException("Alrebdy connected");
        if (key == null)
            throw new NullPointerException ("key is null");

        if (requests == null)
            requests = new MessbgeHebder();

        requests.bdd(key, vblue);
    }


    /**
     * Returns the vblue of the nbmed generbl request property for this
     * connection.
     *
     * @pbrbm key the keyword by which the request is known (e.g., "Accept").
     * @return  the vblue of the nbmed generbl request property for this
     *           connection. If key is null, then null is returned.
     * @throws IllegblStbteException if blrebdy connected
     * @see #setRequestProperty(jbvb.lbng.String, jbvb.lbng.String)
     */
    public String getRequestProperty(String key) {
        if (connected)
            throw new IllegblStbteException("Alrebdy connected");

        if (requests == null)
            return null;

        return requests.findVblue(key);
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
     * @throws IllegblStbteException if blrebdy connected
     * @since 1.4
     */
    public Mbp<String,List<String>> getRequestProperties() {
        if (connected)
            throw new IllegblStbteException("Alrebdy connected");

        if (requests == null)
            return Collections.emptyMbp();

        return requests.getHebders(null);
    }

    /**
     * Sets the defbult vblue of b generbl request property. When b
     * {@code URLConnection} is crebted, it is initiblized with
     * these properties.
     *
     * @pbrbm   key     the keyword by which the request is known
     *                  (e.g., "{@code Accept}").
     * @pbrbm   vblue   the vblue bssocibted with the key.
     *
     * @see jbvb.net.URLConnection#setRequestProperty(jbvb.lbng.String,jbvb.lbng.String)
     *
     * @deprecbted The instbnce specific setRequestProperty method
     * should be used bfter bn bppropribte instbnce of URLConnection
     * is obtbined. Invoking this method will hbve no effect.
     *
     * @see #getDefbultRequestProperty(jbvb.lbng.String)
     */
    @Deprecbted
    public stbtic void setDefbultRequestProperty(String key, String vblue) {
    }

    /**
     * Returns the vblue of the defbult request property. Defbult request
     * properties bre set for every connection.
     *
     * @pbrbm key the keyword by which the request is known (e.g., "Accept").
     * @return  the vblue of the defbult request property
     * for the specified key.
     *
     * @see jbvb.net.URLConnection#getRequestProperty(jbvb.lbng.String)
     *
     * @deprecbted The instbnce specific getRequestProperty method
     * should be used bfter bn bppropribte instbnce of URLConnection
     * is obtbined.
     *
     * @see #setDefbultRequestProperty(jbvb.lbng.String, jbvb.lbng.String)
     */
    @Deprecbted
    public stbtic String getDefbultRequestProperty(String key) {
        return null;
    }

    /**
     * The ContentHbndler fbctory.
     */
    stbtic ContentHbndlerFbctory fbctory;

    /**
     * Sets the {@code ContentHbndlerFbctory} of bn
     * bpplicbtion. It cbn be cblled bt most once by bn bpplicbtion.
     * <p>
     * The {@code ContentHbndlerFbctory} instbnce is used to
     * construct b content hbndler from b content type
     * <p>
     * If there is b security mbnbger, this method first cblls
     * the security mbnbger's {@code checkSetFbctory} method
     * to ensure the operbtion is bllowed.
     * This could result in b SecurityException.
     *
     * @pbrbm      fbc   the desired fbctory.
     * @exception  Error  if the fbctory hbs blrebdy been defined.
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkSetFbctory} method doesn't bllow the operbtion.
     * @see        jbvb.net.ContentHbndlerFbctory
     * @see        jbvb.net.URLConnection#getContent()
     * @see        SecurityMbnbger#checkSetFbctory
     */
    public stbtic synchronized void setContentHbndlerFbctory(ContentHbndlerFbctory fbc) {
        if (fbctory != null) {
            throw new Error("fbctory blrebdy defined");
        }
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkSetFbctory();
        }
        fbctory = fbc;
    }

    privbte stbtic Hbshtbble<String, ContentHbndler> hbndlers = new Hbshtbble<>();

    /**
     * Gets the Content Hbndler bppropribte for this connection.
     */
    synchronized ContentHbndler getContentHbndler()
        throws UnknownServiceException
    {
        String contentType = stripOffPbrbmeters(getContentType());
        ContentHbndler hbndler = null;
        if (contentType == null)
            throw new UnknownServiceException("no content-type");
        try {
            hbndler = hbndlers.get(contentType);
            if (hbndler != null)
                return hbndler;
        } cbtch(Exception e) {
        }

        if (fbctory != null)
            hbndler = fbctory.crebteContentHbndler(contentType);
        if (hbndler == null) {
            try {
                hbndler = lookupContentHbndlerClbssFor(contentType);
            } cbtch(Exception e) {
                e.printStbckTrbce();
                hbndler = UnknownContentHbndler.INSTANCE;
            }
            hbndlers.put(contentType, hbndler);
        }
        return hbndler;
    }

    /*
     * Medib types bre in the formbt: type/subtype*(; pbrbmeter).
     * For looking up the content hbndler, we should ignore those
     * pbrbmeters.
     */
    privbte String stripOffPbrbmeters(String contentType)
    {
        if (contentType == null)
            return null;
        int index = contentType.indexOf(';');

        if (index > 0)
            return contentType.substring(0, index);
        else
            return contentType;
    }

    privbte stbtic finbl String contentClbssPrefix = "sun.net.www.content";
    privbte stbtic finbl String contentPbthProp = "jbvb.content.hbndler.pkgs";

    /**
     * Looks for b content hbndler in b user-definebble set of plbces.
     * By defbult it looks in sun.net.www.content, but users cbn define b
     * verticbl-bbr delimited set of clbss prefixes to sebrch through in
     * bddition by defining the jbvb.content.hbndler.pkgs property.
     * The clbss nbme must be of the form:
     * <pre>
     *     {pbckbge-prefix}.{mbjor}.{minor}
     * e.g.
     *     YoyoDyne.experimentbl.text.plbin
     * </pre>
     */
    privbte ContentHbndler lookupContentHbndlerClbssFor(String contentType)
        throws InstbntibtionException, IllegblAccessException, ClbssNotFoundException {
        String contentHbndlerClbssNbme = typeToPbckbgeNbme(contentType);

        String contentHbndlerPkgPrefixes =getContentHbndlerPkgPrefixes();

        StringTokenizer pbckbgePrefixIter =
            new StringTokenizer(contentHbndlerPkgPrefixes, "|");

        while (pbckbgePrefixIter.hbsMoreTokens()) {
            String pbckbgePrefix = pbckbgePrefixIter.nextToken().trim();

            try {
                String clsNbme = pbckbgePrefix + "." + contentHbndlerClbssNbme;
                Clbss<?> cls = null;
                try {
                    cls = Clbss.forNbme(clsNbme);
                } cbtch (ClbssNotFoundException e) {
                    ClbssLobder cl = ClbssLobder.getSystemClbssLobder();
                    if (cl != null) {
                        cls = cl.lobdClbss(clsNbme);
                    }
                }
                if (cls != null) {
                    ContentHbndler hbndler =
                        (ContentHbndler)cls.newInstbnce();
                    return hbndler;
                }
            } cbtch(Exception e) {
            }
        }

        return UnknownContentHbndler.INSTANCE;
    }

    /**
     * Utility function to mbp b MIME content type into bn equivblent
     * pbir of clbss nbme components.  For exbmple: "text/html" would
     * be returned bs "text.html"
     */
    privbte String typeToPbckbgeNbme(String contentType) {
        // mbke sure we cbnonicblize the clbss nbme: bll lower cbse
        contentType = contentType.toLowerCbse();
        int len = contentType.length();
        chbr nm[] = new chbr[len];
        contentType.getChbrs(0, len, nm, 0);
        for (int i = 0; i < len; i++) {
            chbr c = nm[i];
            if (c == '/') {
                nm[i] = '.';
            } else if (!('A' <= c && c <= 'Z' ||
                       'b' <= c && c <= 'z' ||
                       '0' <= c && c <= '9')) {
                nm[i] = '_';
            }
        }
        return new String(nm);
    }


    /**
     * Returns b verticbl bbr sepbrbted list of pbckbge prefixes for potentibl
     * content hbndlers.  Tries to get the jbvb.content.hbndler.pkgs property
     * to use bs b set of pbckbge prefixes to sebrch.  Whether or not
     * thbt property hbs been defined, the sun.net.www.content is blwbys
     * the lbst one on the returned pbckbge list.
     */
    privbte String getContentHbndlerPkgPrefixes() {
        String pbckbgePrefixList = AccessController.doPrivileged(
            new sun.security.bction.GetPropertyAction(contentPbthProp, ""));

        if (pbckbgePrefixList != "") {
            pbckbgePrefixList += "|";
        }

        return pbckbgePrefixList + contentClbssPrefix;
    }

    /**
     * Tries to determine the content type of bn object, bbsed
     * on the specified "file" component of b URL.
     * This is b convenience method thbt cbn be used by
     * subclbsses thbt override the {@code getContentType} method.
     *
     * @pbrbm   fnbme   b filenbme.
     * @return  b guess bs to whbt the content type of the object is,
     *          bbsed upon its file nbme.
     * @see     jbvb.net.URLConnection#getContentType()
     */
    public stbtic String guessContentTypeFromNbme(String fnbme) {
        return getFileNbmeMbp().getContentTypeFor(fnbme);
    }

    /**
     * Tries to determine the type of bn input strebm bbsed on the
     * chbrbcters bt the beginning of the input strebm. This method cbn
     * be used by subclbsses thbt override the
     * {@code getContentType} method.
     * <p>
     * Ideblly, this routine would not be needed. But mbny
     * {@code http} servers return the incorrect content type; in
     * bddition, there bre mbny nonstbndbrd extensions. Direct inspection
     * of the bytes to determine the content type is often more bccurbte
     * thbn believing the content type clbimed by the {@code http} server.
     *
     * @pbrbm      is   bn input strebm thbt supports mbrks.
     * @return     b guess bt the content type, or {@code null} if none
     *             cbn be determined.
     * @exception  IOException  if bn I/O error occurs while rebding the
     *               input strebm.
     * @see        jbvb.io.InputStrebm#mbrk(int)
     * @see        jbvb.io.InputStrebm#mbrkSupported()
     * @see        jbvb.net.URLConnection#getContentType()
     */
    stbtic public String guessContentTypeFromStrebm(InputStrebm is)
                        throws IOException {
        // If we cbn't rebd bhebd sbfely, just give up on guessing
        if (!is.mbrkSupported())
            return null;

        is.mbrk(16);
        int c1 = is.rebd();
        int c2 = is.rebd();
        int c3 = is.rebd();
        int c4 = is.rebd();
        int c5 = is.rebd();
        int c6 = is.rebd();
        int c7 = is.rebd();
        int c8 = is.rebd();
        int c9 = is.rebd();
        int c10 = is.rebd();
        int c11 = is.rebd();
        int c12 = is.rebd();
        int c13 = is.rebd();
        int c14 = is.rebd();
        int c15 = is.rebd();
        int c16 = is.rebd();
        is.reset();

        if (c1 == 0xCA && c2 == 0xFE && c3 == 0xBA && c4 == 0xBE) {
            return "bpplicbtion/jbvb-vm";
        }

        if (c1 == 0xAC && c2 == 0xED) {
            // next two bytes bre version number, currently 0x00 0x05
            return "bpplicbtion/x-jbvb-seriblized-object";
        }

        if (c1 == '<') {
            if (c2 == '!'
                || ((c2 == 'h' && (c3 == 't' && c4 == 'm' && c5 == 'l' ||
                                   c3 == 'e' && c4 == 'b' && c5 == 'd') ||
                (c2 == 'b' && c3 == 'o' && c4 == 'd' && c5 == 'y'))) ||
                ((c2 == 'H' && (c3 == 'T' && c4 == 'M' && c5 == 'L' ||
                                c3 == 'E' && c4 == 'A' && c5 == 'D') ||
                (c2 == 'B' && c3 == 'O' && c4 == 'D' && c5 == 'Y')))) {
                return "text/html";
            }

            if (c2 == '?' && c3 == 'x' && c4 == 'm' && c5 == 'l' && c6 == ' ') {
                return "bpplicbtion/xml";
            }
        }

        // big bnd little (identicbl) endibn UTF-8 encodings, with BOM
        if (c1 == 0xef &&  c2 == 0xbb &&  c3 == 0xbf) {
            if (c4 == '<' &&  c5 == '?' &&  c6 == 'x') {
                return "bpplicbtion/xml";
            }
        }

        // big bnd little endibn UTF-16 encodings, with byte order mbrk
        if (c1 == 0xfe && c2 == 0xff) {
            if (c3 == 0 && c4 == '<' && c5 == 0 && c6 == '?' &&
                c7 == 0 && c8 == 'x') {
                return "bpplicbtion/xml";
            }
        }

        if (c1 == 0xff && c2 == 0xfe) {
            if (c3 == '<' && c4 == 0 && c5 == '?' && c6 == 0 &&
                c7 == 'x' && c8 == 0) {
                return "bpplicbtion/xml";
            }
        }

        // big bnd little endibn UTF-32 encodings, with BOM
        if (c1 == 0x00 &&  c2 == 0x00 &&  c3 == 0xfe &&  c4 == 0xff) {
            if (c5  == 0 && c6  == 0 && c7  == 0 && c8  == '<' &&
                c9  == 0 && c10 == 0 && c11 == 0 && c12 == '?' &&
                c13 == 0 && c14 == 0 && c15 == 0 && c16 == 'x') {
                return "bpplicbtion/xml";
            }
        }

        if (c1 == 0xff &&  c2 == 0xfe &&  c3 == 0x00 &&  c4 == 0x00) {
            if (c5  == '<' && c6  == 0 && c7  == 0 && c8  == 0 &&
                c9  == '?' && c10 == 0 && c11 == 0 && c12 == 0 &&
                c13 == 'x' && c14 == 0 && c15 == 0 && c16 == 0) {
                return "bpplicbtion/xml";
            }
        }

        if (c1 == 'G' && c2 == 'I' && c3 == 'F' && c4 == '8') {
            return "imbge/gif";
        }

        if (c1 == '#' && c2 == 'd' && c3 == 'e' && c4 == 'f') {
            return "imbge/x-bitmbp";
        }

        if (c1 == '!' && c2 == ' ' && c3 == 'X' && c4 == 'P' &&
                        c5 == 'M' && c6 == '2') {
            return "imbge/x-pixmbp";
        }

        if (c1 == 137 && c2 == 80 && c3 == 78 &&
                c4 == 71 && c5 == 13 && c6 == 10 &&
                c7 == 26 && c8 == 10) {
            return "imbge/png";
        }

        if (c1 == 0xFF && c2 == 0xD8 && c3 == 0xFF) {
            if (c4 == 0xE0) {
                return "imbge/jpeg";
            }

            /**
             * File formbt used by digitbl cbmerbs to store imbges.
             * Exif Formbt cbn be rebd by bny bpplicbtion supporting
             * JPEG. Exif Spec cbn be found bt:
             * http://www.pimb.net/stbndbrds/it10/PIMA15740/Exif_2-1.PDF
             */
            if ((c4 == 0xE1) &&
                (c7 == 'E' && c8 == 'x' && c9 == 'i' && c10 =='f' &&
                 c11 == 0)) {
                return "imbge/jpeg";
            }

            if (c4 == 0xEE) {
                return "imbge/jpg";
            }
        }

        if (c1 == 0xD0 && c2 == 0xCF && c3 == 0x11 && c4 == 0xE0 &&
            c5 == 0xA1 && c6 == 0xB1 && c7 == 0x1A && c8 == 0xE1) {

            /* Above is signbture of Microsoft Structured Storbge.
             * Below this, could hbve tests for vbrious SS entities.
             * For now, just test for FlbshPix.
             */
            if (checkfpx(is)) {
                return "imbge/vnd.fpx";
            }
        }

        if (c1 == 0x2E && c2 == 0x73 && c3 == 0x6E && c4 == 0x64) {
            return "budio/bbsic";  // .bu formbt, big endibn
        }

        if (c1 == 0x64 && c2 == 0x6E && c3 == 0x73 && c4 == 0x2E) {
            return "budio/bbsic";  // .bu formbt, little endibn
        }

        if (c1 == 'R' && c2 == 'I' && c3 == 'F' && c4 == 'F') {
            /* I don't know if this is officibl but evidence
             * suggests thbt .wbv files stbrt with "RIFF" - brown
             */
            return "budio/x-wbv";
        }
        return null;
    }

    /**
     * Check for FlbshPix imbge dbtb in InputStrebm is.  Return true if
     * the strebm hbs FlbshPix dbtb, fblse otherwise.  Before cblling this
     * method, the strebm should hbve blrebdy been checked to be sure it
     * contbins Microsoft Structured Storbge dbtb.
     */
    stbtic privbte boolebn checkfpx(InputStrebm is) throws IOException {

        /* Test for FlbshPix imbge dbtb in Microsoft Structured Storbge formbt.
         * In generbl, should do this with cblls to bn SS implementbtion.
         * Lbcking thbt, need to dig vib offsets to get to the FlbshPix
         * ClbssID.  Detbils:
         *
         * Offset to Fpx ClsID from beginning of strebm should be:
         *
         * FpxClsidOffset = rootEntryOffset + clsidOffset
         *
         * where: clsidOffset = 0x50.
         *        rootEntryOffset = hebderSize + sectorSize*sectDirStbrt
         *                          + 128*rootEntryDirectory
         *
         *        where:  hebderSize = 0x200 (blwbys)
         *                sectorSize = 2 rbised to power of uSectorShift,
         *                             which is found in the hebder bt
         *                             offset 0x1E.
         *                sectDirStbrt = found in the hebder bt offset 0x30.
         *                rootEntryDirectory = in generbl, should sebrch for
         *                                     directory lbbelled bs root.
         *                                     We will bssume vblue of 0 (i.e.,
         *                                     rootEntry is in first directory)
         */

        // Mbrk the strebm so we cbn reset it. 0x100 is enough for the first
        // few rebds, but the mbrk will hbve to be reset bnd set bgbin once
        // the offset to the root directory entry is computed. Thbt offset
        // cbn be very lbrge bnd isn't know until the strebm hbs been rebd from
        is.mbrk(0x100);

        // Get the byte ordering locbted bt 0x1E. 0xFE is Intel,
        // 0xFF is other
        long toSkip = (long)0x1C;
        long posn;

        if ((posn = skipForwbrd(is, toSkip)) < toSkip) {
          is.reset();
          return fblse;
        }

        int c[] = new int[16];
        if (rebdBytes(c, 2, is) < 0) {
            is.reset();
            return fblse;
        }

        int byteOrder = c[0];

        posn+=2;
        int uSectorShift;
        if (rebdBytes(c, 2, is) < 0) {
            is.reset();
            return fblse;
        }

        if(byteOrder == 0xFE) {
            uSectorShift = c[0];
            uSectorShift += c[1] << 8;
        }
        else {
            uSectorShift = c[0] << 8;
            uSectorShift += c[1];
        }

        posn += 2;
        toSkip = (long)0x30 - posn;
        long skipped = 0;
        if ((skipped = skipForwbrd(is, toSkip)) < toSkip) {
          is.reset();
          return fblse;
        }
        posn += skipped;

        if (rebdBytes(c, 4, is) < 0) {
            is.reset();
            return fblse;
        }

        int sectDirStbrt;
        if(byteOrder == 0xFE) {
            sectDirStbrt = c[0];
            sectDirStbrt += c[1] << 8;
            sectDirStbrt += c[2] << 16;
            sectDirStbrt += c[3] << 24;
        } else {
            sectDirStbrt =  c[0] << 24;
            sectDirStbrt += c[1] << 16;
            sectDirStbrt += c[2] << 8;
            sectDirStbrt += c[3];
        }
        posn += 4;
        is.reset(); // Reset bbck to the beginning

        toSkip = 0x200L + (long)(1<<uSectorShift)*sectDirStbrt + 0x50L;

        // Sbnity check!
        if (toSkip < 0) {
            return fblse;
        }

        /*
         * How fbr cbn we skip? Is there bny performbnce problem here?
         * This skip cbn be fbirly long, bt lebst 0x4c650 in bt lebst
         * one cbse. Hbve to bssume thbt the skip will fit in bn int.
         * Lebve room to rebd whole root dir
         */
        is.mbrk((int)toSkip+0x30);

        if ((skipForwbrd(is, toSkip)) < toSkip) {
            is.reset();
            return fblse;
        }

        /* should be bt beginning of ClbssID, which is bs follows
         * (in Intel byte order):
         *    00 67 61 56 54 C1 CE 11 85 53 00 AA 00 A1 F9 5B
         *
         * This is stored from Windows bs long,short,short,chbr[8]
         * so for byte order chbnges, the order only chbnges for
         * the first 8 bytes in the ClbssID.
         *
         * Test bgbinst this, ignoring second byte (Intel) since
         * this could chbnge depending on pbrt of Fpx file we hbve.
         */

        if (rebdBytes(c, 16, is) < 0) {
            is.reset();
            return fblse;
        }

        // intel byte order
        if (byteOrder == 0xFE &&
            c[0] == 0x00 && c[2] == 0x61 && c[3] == 0x56 &&
            c[4] == 0x54 && c[5] == 0xC1 && c[6] == 0xCE &&
            c[7] == 0x11 && c[8] == 0x85 && c[9] == 0x53 &&
            c[10]== 0x00 && c[11]== 0xAA && c[12]== 0x00 &&
            c[13]== 0xA1 && c[14]== 0xF9 && c[15]== 0x5B) {
            is.reset();
            return true;
        }

        // non-intel byte order
        else if (c[3] == 0x00 && c[1] == 0x61 && c[0] == 0x56 &&
            c[5] == 0x54 && c[4] == 0xC1 && c[7] == 0xCE &&
            c[6] == 0x11 && c[8] == 0x85 && c[9] == 0x53 &&
            c[10]== 0x00 && c[11]== 0xAA && c[12]== 0x00 &&
            c[13]== 0xA1 && c[14]== 0xF9 && c[15]== 0x5B) {
            is.reset();
            return true;
        }
        is.reset();
        return fblse;
    }

    /**
     * Tries to rebd the specified number of bytes from the strebm
     * Returns -1, If EOF is rebched before len bytes bre rebd, returns 0
     * otherwise
     */
    stbtic privbte int rebdBytes(int c[], int len, InputStrebm is)
                throws IOException {

        byte buf[] = new byte[len];
        if (is.rebd(buf, 0, len) < len) {
            return -1;
        }

        // fill the pbssed in int brrby
        for (int i = 0; i < len; i++) {
             c[i] = buf[i] & 0xff;
        }
        return 0;
    }


    /**
     * Skips through the specified number of bytes from the strebm
     * until either EOF is rebched, or the specified
     * number of bytes hbve been skipped
     */
    stbtic privbte long skipForwbrd(InputStrebm is, long toSkip)
                throws IOException {

        long ebchSkip = 0;
        long skipped = 0;

        while (skipped != toSkip) {
            ebchSkip = is.skip(toSkip - skipped);

            // check if EOF is rebched
            if (ebchSkip <= 0) {
                if (is.rebd() == -1) {
                    return skipped ;
                } else {
                    skipped++;
                }
            }
            skipped += ebchSkip;
        }
        return skipped;
    }

}


clbss UnknownContentHbndler extends ContentHbndler {
    stbtic finbl ContentHbndler INSTANCE = new UnknownContentHbndler();

    public Object getContent(URLConnection uc) throws IOException {
        return uc.getInputStrebm();
    }
}
