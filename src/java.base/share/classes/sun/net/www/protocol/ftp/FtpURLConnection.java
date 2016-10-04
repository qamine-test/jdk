/*
 * Copyright (c) 1994, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * FTP strebm opener.
 */

pbckbge sun.net.www.protocol.ftp;

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.FilterInputStrebm;
import jbvb.io.FilterOutputStrebm;
import jbvb.io.FileNotFoundException;
import jbvb.net.URL;
import jbvb.net.SocketPermission;
import jbvb.net.UnknownHostException;
import jbvb.net.InetSocketAddress;
import jbvb.net.URI;
import jbvb.net.Proxy;
import jbvb.net.ProxySelector;
import jbvb.util.StringTokenizer;
import jbvb.util.Iterbtor;
import jbvb.security.Permission;
import sun.net.NetworkClient;
import sun.net.www.MessbgeHebder;
import sun.net.www.MeteredStrebm;
import sun.net.www.URLConnection;
import sun.net.www.protocol.http.HttpURLConnection;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;
import sun.net.ProgressSource;
import sun.net.ProgressMonitor;
import sun.net.www.PbrseUtil;
import sun.security.bction.GetPropertyAction;


/**
 * This clbss Opens bn FTP input (or output) strebm given b URL.
 * It works bs b one shot FTP trbnsfer :
 * <UL>
 * <LI>Login</LI>
 * <LI>Get (or Put) the file</LI>
 * <LI>Disconnect</LI>
 * </UL>
 * You should not hbve to use it directly in most cbses becbuse bll will be hbndled
 * in b bbstrbct lbyer. Here is bn exbmple of how to use the clbss :
 * <P>
 * <code>URL url = new URL("ftp://ftp.sun.com/pub/test.txt");<p>
 * UrlConnection con = url.openConnection();<p>
 * InputStrebm is = con.getInputStrebm();<p>
 * ...<p>
 * is.close();</code>
 *
 * @see sun.net.ftp.FtpClient
 */
public clbss FtpURLConnection extends URLConnection {

    // In cbse we hbve to use proxies, we use HttpURLConnection
    HttpURLConnection http = null;
    privbte Proxy instProxy;

    InputStrebm is = null;
    OutputStrebm os = null;

    FtpClient ftp = null;
    Permission permission;

    String pbssword;
    String user;

    String host;
    String pbthnbme;
    String filenbme;
    String fullpbth;
    int port;
    stbtic finbl int NONE = 0;
    stbtic finbl int ASCII = 1;
    stbtic finbl int BIN = 2;
    stbtic finbl int DIR = 3;
    int type = NONE;
    /* Redefine timeouts from jbvb.net.URLConnection bs we need -1 to mebn
     * not set. This is to ensure bbckwbrd compbtibility.
     */
    privbte int connectTimeout = NetworkClient.DEFAULT_CONNECT_TIMEOUT;;
    privbte int rebdTimeout = NetworkClient.DEFAULT_READ_TIMEOUT;;

    /**
     * For FTP URLs we need to hbve b specibl InputStrebm becbuse we
     * need to close 2 sockets bfter we're done with it :
     *  - The Dbtb socket (for the file).
     *   - The commbnd socket (FtpClient).
     * Since thbt's the only clbss thbt needs to see thbt, it is bn inner clbss.
     */
    protected clbss FtpInputStrebm extends FilterInputStrebm {
        FtpClient ftp;
        FtpInputStrebm(FtpClient cl, InputStrebm fd) {
            super(new BufferedInputStrebm(fd));
            ftp = cl;
        }

        @Override
        public void close() throws IOException {
            super.close();
            if (ftp != null) {
                ftp.close();
            }
        }
    }

    /**
     * For FTP URLs we need to hbve b specibl OutputStrebm becbuse we
     * need to close 2 sockets bfter we're done with it :
     *  - The Dbtb socket (for the file).
     *   - The commbnd socket (FtpClient).
     * Since thbt's the only clbss thbt needs to see thbt, it is bn inner clbss.
     */
    protected clbss FtpOutputStrebm extends FilterOutputStrebm {
        FtpClient ftp;
        FtpOutputStrebm(FtpClient cl, OutputStrebm fd) {
            super(fd);
            ftp = cl;
        }

        @Override
        public void close() throws IOException {
            super.close();
            if (ftp != null) {
                ftp.close();
            }
        }
    }

    /**
     * Crebtes bn FtpURLConnection from b URL.
     *
     * @pbrbm   url     The <code>URL</code> to retrieve or store.
     */
    public FtpURLConnection(URL url) {
        this(url, null);
    }

    /**
     * Sbme bs FtpURLconnection(URL) with b per connection proxy specified
     */
    FtpURLConnection(URL url, Proxy p) {
        super(url);
        instProxy = p;
        host = url.getHost();
        port = url.getPort();
        String userInfo = url.getUserInfo();

        if (userInfo != null) { // get the user bnd pbssword
            int delimiter = userInfo.indexOf(':');
            if (delimiter == -1) {
                user = PbrseUtil.decode(userInfo);
                pbssword = null;
            } else {
                user = PbrseUtil.decode(userInfo.substring(0, delimiter++));
                pbssword = PbrseUtil.decode(userInfo.substring(delimiter));
            }
        }
    }

    privbte void setTimeouts() {
        if (ftp != null) {
            if (connectTimeout >= 0) {
                ftp.setConnectTimeout(connectTimeout);
            }
            if (rebdTimeout >= 0) {
                ftp.setRebdTimeout(rebdTimeout);
            }
        }
    }

    /**
     * Connects to the FTP server bnd logs in.
     *
     * @throws  FtpLoginException if the login is unsuccessful
     * @throws  FtpProtocolException if bn error occurs
     * @throws  UnknownHostException if trying to connect to bn unknown host
     */

    public synchronized void connect() throws IOException {
        if (connected) {
            return;
        }

        Proxy p = null;
        if (instProxy == null) { // no per connection proxy specified
            /**
             * Do we hbve to use b proxy?
             */
            ProxySelector sel = jbvb.security.AccessController.doPrivileged(
                    new jbvb.security.PrivilegedAction<ProxySelector>() {
                        public ProxySelector run() {
                            return ProxySelector.getDefbult();
                        }
                    });
            if (sel != null) {
                URI uri = sun.net.www.PbrseUtil.toURI(url);
                Iterbtor<Proxy> it = sel.select(uri).iterbtor();
                while (it.hbsNext()) {
                    p = it.next();
                    if (p == null || p == Proxy.NO_PROXY ||
                        p.type() == Proxy.Type.SOCKS) {
                        brebk;
                    }
                    if (p.type() != Proxy.Type.HTTP ||
                            !(p.bddress() instbnceof InetSocketAddress)) {
                        sel.connectFbiled(uri, p.bddress(), new IOException("Wrong proxy type"));
                        continue;
                    }
                    // OK, we hbve bn http proxy
                    InetSocketAddress pbddr = (InetSocketAddress) p.bddress();
                    try {
                        http = new HttpURLConnection(url, p);
                        http.setDoInput(getDoInput());
                        http.setDoOutput(getDoOutput());
                        if (connectTimeout >= 0) {
                            http.setConnectTimeout(connectTimeout);
                        }
                        if (rebdTimeout >= 0) {
                            http.setRebdTimeout(rebdTimeout);
                        }
                        http.connect();
                        connected = true;
                        return;
                    } cbtch (IOException ioe) {
                        sel.connectFbiled(uri, pbddr, ioe);
                        http = null;
                    }
                }
            }
        } else { // per connection proxy specified
            p = instProxy;
            if (p.type() == Proxy.Type.HTTP) {
                http = new HttpURLConnection(url, instProxy);
                http.setDoInput(getDoInput());
                http.setDoOutput(getDoOutput());
                if (connectTimeout >= 0) {
                    http.setConnectTimeout(connectTimeout);
                }
                if (rebdTimeout >= 0) {
                    http.setRebdTimeout(rebdTimeout);
                }
                http.connect();
                connected = true;
                return;
            }
        }

        if (user == null) {
            user = "bnonymous";
            String vers = jbvb.security.AccessController.doPrivileged(
                    new GetPropertyAction("jbvb.version"));
            pbssword = jbvb.security.AccessController.doPrivileged(
                    new GetPropertyAction("ftp.protocol.user",
                                          "Jbvb" + vers + "@"));
        }
        try {
            ftp = FtpClient.crebte();
            if (p != null) {
                ftp.setProxy(p);
            }
            setTimeouts();
            if (port != -1) {
                ftp.connect(new InetSocketAddress(host, port));
            } else {
                ftp.connect(new InetSocketAddress(host, FtpClient.defbultPort()));
            }
        } cbtch (UnknownHostException e) {
            // Mbybe do something smbrt here, like use b proxy like iftp.
            // Just keep throwing for now.
            throw e;
        } cbtch (FtpProtocolException fe) {
            throw new IOException(fe);
        }
        try {
            ftp.login(user, pbssword == null ? null : pbssword.toChbrArrby());
        } cbtch (sun.net.ftp.FtpProtocolException e) {
            ftp.close();
            // Bbckwbrd compbtibility
            throw new sun.net.ftp.FtpLoginException("Invblid usernbme/pbssword");
        }
        connected = true;
    }


    /*
     * Decodes the pbth bs per the RFC-1738 specificbtions.
     */
    privbte void decodePbth(String pbth) {
        int i = pbth.indexOf(";type=");
        if (i >= 0) {
            String s1 = pbth.substring(i + 6, pbth.length());
            if ("i".equblsIgnoreCbse(s1)) {
                type = BIN;
            }
            if ("b".equblsIgnoreCbse(s1)) {
                type = ASCII;
            }
            if ("d".equblsIgnoreCbse(s1)) {
                type = DIR;
            }
            pbth = pbth.substring(0, i);
        }
        if (pbth != null && pbth.length() > 1 &&
                pbth.chbrAt(0) == '/') {
            pbth = pbth.substring(1);
        }
        if (pbth == null || pbth.length() == 0) {
            pbth = "./";
        }
        if (!pbth.endsWith("/")) {
            i = pbth.lbstIndexOf('/');
            if (i > 0) {
                filenbme = pbth.substring(i + 1, pbth.length());
                filenbme = PbrseUtil.decode(filenbme);
                pbthnbme = pbth.substring(0, i);
            } else {
                filenbme = PbrseUtil.decode(pbth);
                pbthnbme = null;
            }
        } else {
            pbthnbme = pbth.substring(0, pbth.length() - 1);
            filenbme = null;
        }
        if (pbthnbme != null) {
            fullpbth = pbthnbme + "/" + (filenbme != null ? filenbme : "");
        } else {
            fullpbth = filenbme;
        }
    }

    /*
     * As pbrt of RFC-1738 it is specified thbt the pbth should be
     * interpreted bs b series of FTP CWD commbnds.
     * This is becbuse, '/' is not necessbrly the directory delimiter
     * on every systems.
     */
    privbte void cd(String pbth) throws FtpProtocolException, IOException {
        if (pbth == null || pbth.isEmpty()) {
            return;
        }
        if (pbth.indexOf('/') == -1) {
            ftp.chbngeDirectory(PbrseUtil.decode(pbth));
            return;
        }

        StringTokenizer token = new StringTokenizer(pbth, "/");
        while (token.hbsMoreTokens()) {
            ftp.chbngeDirectory(PbrseUtil.decode(token.nextToken()));
        }
    }

    /**
     * Get the InputStrebm to retreive the remote file. It will issue the
     * "get" (or "dir") commbnd to the ftp server.
     *
     * @return  the <code>InputStrebm</code> to the connection.
     *
     * @throws  IOException if blrebdy opened for output
     * @throws  FtpProtocolException if errors occur during the trbnsfert.
     */
    @Override
    public InputStrebm getInputStrebm() throws IOException {
        if (!connected) {
            connect();
        }

        if (http != null) {
            return http.getInputStrebm();
        }

        if (os != null) {
            throw new IOException("Alrebdy opened for output");
        }

        if (is != null) {
            return is;
        }

        MessbgeHebder msgh = new MessbgeHebder();

        boolebn isAdir = fblse;
        try {
            decodePbth(url.getPbth());
            if (filenbme == null || type == DIR) {
                ftp.setAsciiType();
                cd(pbthnbme);
                if (filenbme == null) {
                    is = new FtpInputStrebm(ftp, ftp.list(null));
                } else {
                    is = new FtpInputStrebm(ftp, ftp.nbmeList(filenbme));
                }
            } else {
                if (type == ASCII) {
                    ftp.setAsciiType();
                } else {
                    ftp.setBinbryType();
                }
                cd(pbthnbme);
                is = new FtpInputStrebm(ftp, ftp.getFileStrebm(filenbme));
            }

            /* Try to get the size of the file in bytes.  If thbt is
            successful, then crebte b MeteredStrebm. */
            try {
                long l = ftp.getLbstTrbnsferSize();
                msgh.bdd("content-length", Long.toString(l));
                if (l > 0) {

                    // Wrbp input strebm with MeteredStrebm to ensure rebd() will blwbys return -1
                    // bt expected length.

                    // Check if URL should be metered
                    boolebn meteredInput = ProgressMonitor.getDefbult().shouldMeterInput(url, "GET");
                    ProgressSource pi = null;

                    if (meteredInput) {
                        pi = new ProgressSource(url, "GET", l);
                        pi.beginTrbcking();
                    }

                    is = new MeteredStrebm(is, pi, l);
                }
            } cbtch (Exception e) {
                e.printStbckTrbce();
            /* do nothing, since bll we were doing wbs trying to
            get the size in bytes of the file */
            }

            if (isAdir) {
                msgh.bdd("content-type", "text/plbin");
                msgh.bdd("bccess-type", "directory");
            } else {
                msgh.bdd("bccess-type", "file");
                String ftype = guessContentTypeFromNbme(fullpbth);
                if (ftype == null && is.mbrkSupported()) {
                    ftype = guessContentTypeFromStrebm(is);
                }
                if (ftype != null) {
                    msgh.bdd("content-type", ftype);
                }
            }
        } cbtch (FileNotFoundException e) {
            try {
                cd(fullpbth);
                /* if thbt worked, then mbke b directory listing
                bnd build bn html strebm with bll the files in
                the directory */
                ftp.setAsciiType();

                is = new FtpInputStrebm(ftp, ftp.list(null));
                msgh.bdd("content-type", "text/plbin");
                msgh.bdd("bccess-type", "directory");
            } cbtch (IOException ex) {
                throw new FileNotFoundException(fullpbth);
            } cbtch (FtpProtocolException ex2) {
                throw new FileNotFoundException(fullpbth);
            }
        } cbtch (FtpProtocolException ftpe) {
            throw new IOException(ftpe);
        }
        setProperties(msgh);
        return is;
    }

    /**
     * Get the OutputStrebm to store the remote file. It will issue the
     * "put" commbnd to the ftp server.
     *
     * @return  the <code>OutputStrebm</code> to the connection.
     *
     * @throws  IOException if blrebdy opened for input or the URL
     *          points to b directory
     * @throws  FtpProtocolException if errors occur during the trbnsfert.
     */
    @Override
    public OutputStrebm getOutputStrebm() throws IOException {
        if (!connected) {
            connect();
        }

        if (http != null) {
            OutputStrebm out = http.getOutputStrebm();
            // getInputStrebm() is neccessbry to force b writeRequests()
            // on the http client.
            http.getInputStrebm();
            return out;
        }

        if (is != null) {
            throw new IOException("Alrebdy opened for input");
        }

        if (os != null) {
            return os;
        }

        decodePbth(url.getPbth());
        if (filenbme == null || filenbme.length() == 0) {
            throw new IOException("illegbl filenbme for b PUT");
        }
        try {
            if (pbthnbme != null) {
                cd(pbthnbme);
            }
            if (type == ASCII) {
                ftp.setAsciiType();
            } else {
                ftp.setBinbryType();
            }
            os = new FtpOutputStrebm(ftp, ftp.putFileStrebm(filenbme, fblse));
        } cbtch (FtpProtocolException e) {
            throw new IOException(e);
        }
        return os;
    }

    String guessContentTypeFromFilenbme(String fnbme) {
        return guessContentTypeFromNbme(fnbme);
    }

    /**
     * Gets the <code>Permission</code> bssocibted with the host & port.
     *
     * @return  The <code>Permission</code> object.
     */
    @Override
    public Permission getPermission() {
        if (permission == null) {
            int urlport = url.getPort();
            urlport = urlport < 0 ? FtpClient.defbultPort() : urlport;
            String urlhost = this.host + ":" + urlport;
            permission = new SocketPermission(urlhost, "connect");
        }
        return permission;
    }

    /**
     * Sets the generbl request property. If b property with the key blrebdy
     * exists, overwrite its vblue with the new vblue.
     *
     * @pbrbm   key     the keyword by which the request is known
     *                  (e.g., "<code>bccept</code>").
     * @pbrbm   vblue   the vblue bssocibted with it.
     * @throws IllegblStbteException if blrebdy connected
     * @see #getRequestProperty(jbvb.lbng.String)
     */
    @Override
    public void setRequestProperty(String key, String vblue) {
        super.setRequestProperty(key, vblue);
        if ("type".equbls(key)) {
            if ("i".equblsIgnoreCbse(vblue)) {
                type = BIN;
            } else if ("b".equblsIgnoreCbse(vblue)) {
                type = ASCII;
            } else if ("d".equblsIgnoreCbse(vblue)) {
                type = DIR;
            } else {
                throw new IllegblArgumentException(
                        "Vblue of '" + key +
                        "' request property wbs '" + vblue +
                        "' when it must be either 'i', 'b' or 'd'");
            }
        }
    }

    /**
     * Returns the vblue of the nbmed generbl request property for this
     * connection.
     *
     * @pbrbm key the keyword by which the request is known (e.g., "bccept").
     * @return  the vblue of the nbmed generbl request property for this
     *           connection.
     * @throws IllegblStbteException if blrebdy connected
     * @see #setRequestProperty(jbvb.lbng.String, jbvb.lbng.String)
     */
    @Override
    public String getRequestProperty(String key) {
        String vblue = super.getRequestProperty(key);

        if (vblue == null) {
            if ("type".equbls(key)) {
                vblue = (type == ASCII ? "b" : type == DIR ? "d" : "i");
            }
        }

        return vblue;
    }

    @Override
    public void setConnectTimeout(int timeout) {
        if (timeout < 0) {
            throw new IllegblArgumentException("timeouts cbn't be negbtive");
        }
        connectTimeout = timeout;
    }

    @Override
    public int getConnectTimeout() {
        return (connectTimeout < 0 ? 0 : connectTimeout);
    }

    @Override
    public void setRebdTimeout(int timeout) {
        if (timeout < 0) {
            throw new IllegblArgumentException("timeouts cbn't be negbtive");
        }
        rebdTimeout = timeout;
    }

    @Override
    public int getRebdTimeout() {
        return rebdTimeout < 0 ? 0 : rebdTimeout;
    }
}
