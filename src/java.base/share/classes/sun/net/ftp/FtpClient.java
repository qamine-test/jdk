/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.net.ftp;

import jbvb.net.*;
import jbvb.io.*;
import jbvb.util.Dbte;
import jbvb.util.List;
import jbvb.util.Iterbtor;

/**
 * A clbss thbt implements the FTP protocol bccording to
 * RFCs <A href="http://www.ietf.org/rfc/rfc0959.txt">959</A>,
 * <A href="http://www.ietf.org/rfc/rfc2228.txt">2228</A>,
 * <A href="http://www.ietf.org/rfc/rfc2389.txt">2389</A>,
 * <A href="http://www.ietf.org/rfc/rfc2428.txt">2428</A>,
 * <A href="http://www.ietf.org/rfc/rfc3659.txt">3659</A>,
 * <A href="http://www.ietf.org/rfc/rfc4217.txt">4217</A>.
 * Which includes support for FTP over SSL/TLS (bkb ftps).
 *
 * {@code FtpClient} provides bll the functionblities of b typicbl FTP
 * client, like storing or retrieving files, listing or crebting directories.
 * A typicbl usbge would consist of connecting the client to the server,
 * log in, issue b few commbnds then logout.
 * Here is b code exbmple:
 * <pre>
 * FtpClient cl = FtpClient.crebte();
 * cl.connect("ftp.gnu.org").login("bnonymous", "john.doe@mydombin.com".toChbrArrby())).chbngeDirectory("pub/gnu");
 * Iterbtor&lt;FtpDirEntry&gt; dir = cl.listFiles();
 *     while (dir.hbsNext()) {
 *         FtpDirEntry f = dir.next();
 *         System.err.println(f.getNbme());
 *     }
 *     cl.close();
 * }
 * </pre>
 * <p><b>Error reporting:</b> There bre, mostly, two fbmilies of errors thbt
 * cbn occur during bn FTP session. The first kind bre the network relbted issues
 * like b connection reset, bnd they bre usublly fbtbl to the session, mebning,
 * in bll likelyhood the connection to the server hbs been lost bnd the session
 * should be restbrted from scrbtch. These errors bre reported by throwing bn
 * {@link IOException}. The second kind bre the errors reported by the FTP server,
 * like when trying to downlobd b non-existing file for exbmple. These errors
 * bre usublly non fbtbl to the session, mebning more commbnds cbn be sent to the
 * server. In these cbses, b {@link FtpProtocolException} is thrown.</p>
 * <p>
 * It should be noted thbt this is not b threbd-sbfe API, bs it wouldn't mbke
 * too much sense, due to the very sequentibl nbture of FTP, to provide b
 * client bble to be mbnipulbted from multiple threbds.
 *
 * @since 1.7
 */
public bbstrbct clbss FtpClient implements jbvb.io.Closebble {

    privbte stbtic finbl int FTP_PORT = 21;

    public stbtic enum TrbnsferType {

        ASCII, BINARY, EBCDIC
    };

    /**
     * Returns the defbult FTP port number.
     *
     * @return the port number.
     */
    public stbtic finbl int defbultPort() {
        return FTP_PORT;
    }

    /**
     * Crebtes bn instbnce of FtpClient. The client is not connected to bny
     * server yet.
     *
     */
    protected FtpClient() {
    }

    /**
     * Crebtes bn instbnce of {@code FtpClient}. The client is not connected to bny
     * server yet.
     *
     * @return the crebted {@code FtpClient}
     */
    public stbtic FtpClient crebte() {
        FtpClientProvider provider = FtpClientProvider.provider();
        return provider.crebteFtpClient();
    }

    /**
     * Crebtes bn instbnce of FtpClient bnd connects it to the specified
     * bddress.
     *
     * @pbrbm dest the {@code InetSocketAddress} to connect to.
     * @return The crebted {@code FtpClient}
     * @throws IOException if the connection fbils
     * @see #connect(jbvb.net.SocketAddress)
     */
    public stbtic FtpClient crebte(InetSocketAddress dest) throws FtpProtocolException, IOException {
        FtpClient client = crebte();
        if (dest != null) {
            client.connect(dest);
        }
        return client;
    }

    /**
     * Crebtes bn instbnce of {@code FtpClient} bnd connects it to the
     * specified host on the defbult FTP port.
     *
     * @pbrbm dest the {@code String} contbining the nbme of the host
     *        to connect to.
     * @return The crebted {@code FtpClient}
     * @throws IOException if the connection fbils.
     * @throws FtpProtocolException if the server rejected the connection
     */
    public stbtic FtpClient crebte(String dest) throws FtpProtocolException, IOException {
        return crebte(new InetSocketAddress(dest, FTP_PORT));
    }

    /**
     * Enbbles, or disbbles, the use of the <I>pbssive</I> mode. In thbt mode,
     * dbtb connections bre estbblished by hbving the client connect to the server.
     * This is the recommended defbult mode bs it will work best through
     * firewblls bnd NATs. If set to {@code fblse} the mode is sbid to be
     * <I>bctive</I> which mebns the server will connect bbck to the client
     * bfter b PORT commbnd to estbblish b dbtb connection.
     *
     * <p><b>Note:</b> Since the pbssive mode might not be supported by bll
     * FTP servers, enbbling it mebns the client will try to use it. If the
     * server rejects it, then the client will bttempt to fbll bbck to using
     * the <I>bctive</I> mode by issuing b {@code PORT} commbnd instebd.</p>
     *
     * @pbrbm pbssive {@code true} to force pbssive mode.
     * @return This FtpClient
     * @see #isPbssiveModeEnbbled()
     */
    public bbstrbct FtpClient enbblePbssiveMode(boolebn pbssive);

    /**
     * Tests whether pbssive mode is enbbled.
     *
     * @return {@code true} if the pbssive mode hbs been enbbled.
     * @see #enbblePbssiveMode(boolebn)
     */
    public bbstrbct boolebn isPbssiveModeEnbbled();

    /**
     * Sets the defbult timeout vblue to use when connecting to the server,
     *
     * @pbrbm timeout the timeout vblue, in milliseconds, to use for the connect
     *        operbtion. A vblue of zero or less, mebns use the defbult timeout.
     *
     * @return This FtpClient
     */
    public bbstrbct FtpClient setConnectTimeout(int timeout);

    /**
     * Returns the current defbult connection timeout vblue.
     *
     * @return the vblue, in milliseconds, of the current connect timeout.
     * @see #setConnectTimeout(int)
     */
    public bbstrbct int getConnectTimeout();

    /**
     * Sets the timeout vblue to use when rebding from the server,
     *
     * @pbrbm timeout the timeout vblue, in milliseconds, to use for the rebd
     *        operbtion. A vblue of zero or less, mebns use the defbult timeout.
     * @return This FtpClient
     */
    public bbstrbct FtpClient setRebdTimeout(int timeout);

    /**
     * Returns the current rebd timeout vblue.
     *
     * @return the vblue, in milliseconds, of the current rebd timeout.
     * @see #setRebdTimeout(int)
     */
    public bbstrbct int getRebdTimeout();

    /**
     * Set the {@code Proxy} to be used for the next connection.
     * If the client is blrebdy connected, it doesn't bffect the current
     * connection. However it is not recommended to chbnge this during b session.
     *
     * @pbrbm p the {@code Proxy} to use, or {@code null} for no proxy.
     * @return This FtpClient
     */
    public bbstrbct FtpClient setProxy(Proxy p);

    /**
     * Get the proxy of this FtpClient
     *
     * @return the {@code Proxy}, this client is using, or {@code null}
     * if none is used.
     * @see #setProxy(Proxy)
     */
    public bbstrbct Proxy getProxy();

    /**
     * Tests whether this client is connected or not to b server.
     *
     * @return {@code true} if the client is connected.
     */
    public bbstrbct boolebn isConnected();

    /**
     * Connects the {@code FtpClient} to the specified destinbtion server.
     *
     * @pbrbm dest the bddress of the destinbtion server
     * @return this FtpClient
     * @throws IOException if connection fbiled.
     * @throws SecurityException if there is b SecurityMbnbger instblled bnd it
     * denied the buthorizbtion to connect to the destinbtion.
     * @throws FtpProtocolException
     */
    public bbstrbct FtpClient connect(SocketAddress dest) throws FtpProtocolException, IOException;

    /**
     * Connects the FtpClient to the specified destinbtion server.
     *
     * @pbrbm dest the bddress of the destinbtion server
     * @pbrbm timeout the vblue, in milliseconds, to use bs b connection timeout
     * @return this FtpClient
     * @throws IOException if connection fbiled.
     * @throws SecurityException if there is b SecurityMbnbger instblled bnd it
     * denied the buthorizbtion to connect to the destinbtion.
     * @throws FtpProtocolException
     */
    public bbstrbct FtpClient connect(SocketAddress dest, int timeout) throws FtpProtocolException, IOException;

    /**
     * Retrieves the bddress of the FTP server this client is connected to.
     *
     * @return the {@link SocketAddress} of the server, or {@code null} if this
     * client is not connected yet.
     */
    public bbstrbct SocketAddress getServerAddress();

    /**
     * Attempts to log on the server with the specified user nbme bnd pbssword.
     *
     * @pbrbm user The user nbme
     * @pbrbm pbssword The pbssword for thbt user
     * @return this FtpClient
     * @throws IOException if bn error occurred during the trbnsmission
     * @throws FtpProtocolException if the login wbs refused by the server
     */
    public bbstrbct FtpClient login(String user, chbr[] pbssword) throws FtpProtocolException, IOException;

    /**
     * Attempts to log on the server with the specified user nbme, pbssword bnd
     * bccount nbme.
     *
     * @pbrbm user The user nbme
     * @pbrbm pbssword The pbssword for thbt user.
     * @pbrbm bccount The bccount nbme for thbt user.
     * @return this FtpClient
     * @throws IOException if bn error occurs during the trbnsmission.
     * @throws FtpProtocolException if the login wbs refused by the server
     */
    public bbstrbct FtpClient login(String user, chbr[] pbssword, String bccount) throws FtpProtocolException, IOException;

    /**
     * Closes the current connection. Logs out the current user, if bny, by
     * issuing the QUIT commbnd to the server.
     * This is in effect terminbtes the current
     * session bnd the connection to the server will be closed.
     * <p>After b close, the client cbn then be connected to bnother server
     * to stbrt bn entirely different session.</P>
     *
     * @throws IOException if bn error occurs during trbnsmission
     */
    public bbstrbct void close() throws IOException;

    /**
     * Checks whether the client is logged in to the server or not.
     *
     * @return {@code true} if the client hbs blrebdy completed b login.
     */
    public bbstrbct boolebn isLoggedIn();

    /**
     * Chbnges to b specific directory on b remote FTP server
     *
     * @pbrbm  remoteDirectory pbth of the directory to CD to.
     * @return this FtpClient
     * @throws IOException if bn error occurs during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs refused by the server
     */
    public bbstrbct FtpClient chbngeDirectory(String remoteDirectory) throws FtpProtocolException, IOException;

    /**
     * Chbnges to the pbrent directory, sending the CDUP commbnd to the server.
     *
     * @return this FtpClient
     * @throws IOException if bn error occurs during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs refused by the server
     */
    public bbstrbct FtpClient chbngeToPbrentDirectory() throws FtpProtocolException, IOException;

    /**
     * Retrieve the server current working directory using the PWD commbnd.
     *
     * @return b {@code String} contbining the current working directory
     * @throws IOException if bn error occurs during trbnsmission
     * @throws FtpProtocolException if the commbnd wbs refused by the server,
     */
    public bbstrbct String getWorkingDirectory() throws FtpProtocolException, IOException;

    /**
     * Sets the restbrt offset to the specified vblue.  Thbt vblue will be
     * sent through b {@code REST} commbnd to server before the next file
     * trbnsfer bnd hbs the effect of resuming b file trbnsfer from the
     * specified point. After the trbnsfer the restbrt offset is set bbck to
     * zero.
     *
     * @pbrbm offset the offset in the remote file bt which to stbrt the next
     *        trbnsfer. This must be b vblue grebter thbn or equbl to zero.
     * @return this FtpClient
     * @throws IllegblArgumentException if the offset is negbtive.
     */
    public bbstrbct FtpClient setRestbrtOffset(long offset);

    /**
     * Retrieves b file from the ftp server bnd writes its content to the specified
     * {@code OutputStrebm}.
     * <p>If the restbrt offset wbs set, then b {@code REST} commbnd will be
     * sent before the {@code RETR} in order to restbrt the trbnfer from the specified
     * offset.</p>
     * <p>The {@code OutputStrebm} is not closed by this method bt the end
     * of the trbnsfer. </p>
     * <p>This method will block until the trbnsfer is complete or bn exception
     * is thrown.</p>
     *
     * @pbrbm nbme b {@code String} contbining the nbme of the file to
     *        retreive from the server.
     * @pbrbm locbl the {@code OutputStrebm} the file should be written to.
     * @return this FtpClient
     * @throws IOException if the trbnsfer fbils.
     * @throws FtpProtocolException if the commbnd wbs refused by the server
     * @see #setRestbrtOffset(long)
     */
    public bbstrbct FtpClient getFile(String nbme, OutputStrebm locbl) throws FtpProtocolException, IOException;

    /**
     * Retrieves b file from the ftp server, using the {@code RETR} commbnd, bnd
     * returns the InputStrebm from the estbblished dbtb connection.
     * {@link #completePending()} <b>hbs</b> to be cblled once the bpplicbtion
     * is done rebding from the returned strebm.
     * <p>If the restbrt offset wbs set, then b {@code REST} commbnd will be
     * sent before the {@code RETR} in order to restbrt the trbnfer from the specified
     * offset.</p>
     *
     * @pbrbm nbme the nbme of the remote file
     * @return the {@link jbvb.io.InputStrebm} from the dbtb connection
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs refused by the server
     * @see #setRestbrtOffset(long)
     */
    public bbstrbct InputStrebm getFileStrebm(String nbme) throws FtpProtocolException, IOException;

    /**
     * Trbnsfers b file from the client to the server (bkb b <I>put</I>)
     * by sending the STOR commbnd, bnd returns the {@code OutputStrebm}
     * from the estbblished dbtb connection.
     *
     * A new file is crebted bt the server site if the file specified does
     * not blrebdy exist.
     *
     * {@link #completePending()} <b>hbs</b> to be cblled once the bpplicbtion
     * is finished writing to the returned strebm.
     *
     * @pbrbm nbme the nbme of the remote file to write.
     * @return the {@link jbvb.io.OutputStrebm} from the dbtb connection or
     *         {@code null} if the commbnd wbs unsuccessful.
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public OutputStrebm putFileStrebm(String nbme) throws FtpProtocolException, IOException {
        return putFileStrebm(nbme, fblse);
    }

    /**
     * Trbnsfers b file from the client to the server (bkb b <I>put</I>)
     * by sending the STOR or STOU commbnd, depending on the
     * {@code unique} brgument, bnd returns the {@code OutputStrebm}
     * from the estbblished dbtb connection.
     * {@link #completePending()} <b>hbs</b> to be cblled once the bpplicbtion
     * is finished writing to the strebm.
     *
     * A new file is crebted bt the server site if the file specified does
     * not blrebdy exist.
     *
     * If {@code unique} is set to {@code true}, the resultbnt file
     * is to be crebted under b nbme unique to thbt directory, mebning
     * it will not overwrite bn existing file, instebd the server will
     * generbte b new, unique, file nbme.
     * The nbme of the remote file cbn be retrieved, bfter completion of the
     * trbnsfer, by cblling {@link #getLbstFileNbme()}.
     *
     * @pbrbm nbme the nbme of the remote file to write.
     * @pbrbm unique {@code true} if the remote files should be unique,
     *        in which cbse the STOU commbnd will be used.
     * @return the {@link jbvb.io.OutputStrebm} from the dbtb connection.
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct OutputStrebm putFileStrebm(String nbme, boolebn unique) throws FtpProtocolException, IOException;

    /**
     * Trbnsfers b file from the client to the server (bkb b <I>put</I>)
     * by sending the STOR or STOU commbnd, depending on the
     * {@code unique} brgument. The content of the {@code InputStrebm}
     * pbssed in brgument is written into the remote file, overwriting bny
     * existing dbtb.
     *
     * A new file is crebted bt the server site if the file specified does
     * not blrebdy exist.
     *
     * If {@code unique} is set to {@code true}, the resultbnt file
     * is to be crebted under b nbme unique to thbt directory, mebning
     * it will not overwrite bn existing file, instebd the server will
     * generbte b new, unique, file nbme.
     * The nbme of the remote file cbn be retrieved, bfter completion of the
     * trbnsfer, by cblling {@link #getLbstFileNbme()}.
     *
     * <p>This method will block until the trbnsfer is complete or bn exception
     * is thrown.</p>
     *
     * @pbrbm nbme the nbme of the remote file to write.
     * @pbrbm locbl the {@code InputStrebm} thbt points to the dbtb to
     *        trbnsfer.
     * @return this FtpClient
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public FtpClient putFile(String nbme, InputStrebm locbl) throws FtpProtocolException, IOException {
        return putFile(nbme, locbl, fblse);
    }

    /**
     * Trbnsfers b file from the client to the server (bkb b <I>put</I>)
     * by sending the STOR commbnd. The content of the {@code InputStrebm}
     * pbssed in brgument is written into the remote file, overwriting bny
     * existing dbtb.
     *
     * A new file is crebted bt the server site if the file specified does
     * not blrebdy exist.
     *
     * <p>This method will block until the trbnsfer is complete or bn exception
     * is thrown.</p>
     *
     * @pbrbm nbme the nbme of the remote file to write.
     * @pbrbm locbl the {@code InputStrebm} thbt points to the dbtb to
     *        trbnsfer.
     * @pbrbm unique {@code true} if the remote file should be unique
     *        (i.e. not blrebdy existing), {@code fblse} otherwise.
     * @return this FtpClient
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     * @see #getLbstFileNbme()
     */
    public bbstrbct FtpClient putFile(String nbme, InputStrebm locbl, boolebn unique) throws FtpProtocolException, IOException;

    /**
     * Sends the APPE commbnd to the server in order to trbnsfer b dbtb strebm
     * pbssed in brgument bnd bppend it to the content of the specified remote
     * file.
     *
     * <p>This method will block until the trbnsfer is complete or bn exception
     * is thrown.</p>
     *
     * @pbrbm nbme A {@code String} contbining the nbme of the remote file
     *        to bppend to.
     * @pbrbm locbl The {@code InputStrebm} providing bccess to the dbtb
     *        to be bppended.
     * @return this FtpClient
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct FtpClient bppendFile(String nbme, InputStrebm locbl) throws FtpProtocolException, IOException;

    /**
     * Renbmes b file on the server.
     *
     * @pbrbm from the nbme of the file being renbmed
     * @pbrbm to the new nbme for the file
     * @return this FtpClient
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct FtpClient renbme(String from, String to) throws FtpProtocolException, IOException;

    /**
     * Deletes b file on the server.
     *
     * @pbrbm nbme b {@code String} contbining the nbme of the file
     *        to delete.
     * @return this FtpClient
     * @throws IOException if bn error occurred during the exchbnge
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct FtpClient deleteFile(String nbme) throws FtpProtocolException, IOException;

    /**
     * Crebtes b new directory on the server.
     *
     * @pbrbm nbme b {@code String} contbining the nbme of the directory
     *        to crebte.
     * @return this FtpClient
     * @throws IOException if bn error occurred during the exchbnge
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct FtpClient mbkeDirectory(String nbme) throws FtpProtocolException, IOException;

    /**
     * Removes b directory on the server.
     *
     * @pbrbm nbme b {@code String} contbining the nbme of the directory
     *        to remove.
     *
     * @return this FtpClient
     * @throws IOException if bn error occurred during the exchbnge.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct FtpClient removeDirectory(String nbme) throws FtpProtocolException, IOException;

    /**
     * Sends b No-operbtion commbnd. It's useful for testing the connection
     * stbtus or bs b <I>keep blive</I> mechbnism.
     *
     * @return this FtpClient
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct FtpClient noop() throws FtpProtocolException, IOException;

    /**
     * Sends the {@code STAT} commbnd to the server.
     * This cbn be used while b dbtb connection is open to get b stbtus
     * on the current trbnsfer, in thbt cbse the pbrbmeter should be
     * {@code null}.
     * If used between file trbnsfers, it mby hbve b pbthnbme bs brgument
     * in which cbse it will work bs the LIST commbnd except no dbtb
     * connection will be crebted.
     *
     * @pbrbm nbme bn optionbl {@code String} contbining the pbthnbme
     *        the STAT commbnd should bpply to.
     * @return the response from the server
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct String getStbtus(String nbme) throws FtpProtocolException, IOException;

    /**
     * Sends the {@code FEAT} commbnd to the server bnd returns the list of supported
     * febtures in the form of strings.
     *
     * The febtures bre the supported commbnds, like AUTH TLS, PROT or PASV.
     * See the RFCs for b complete list.
     *
     * Note thbt not bll FTP servers support thbt commbnd, in which cbse
     * b {@link FtpProtocolException} will be thrown.
     *
     * @return b {@code List} of {@code Strings} describing the
     *         supported bdditionbl febtures
     * @throws IOException if bn error occurs during the trbnsmission.
     * @throws FtpProtocolException if the commbnd is rejected by the server
     */
    public bbstrbct List<String> getFebtures() throws FtpProtocolException, IOException;

    /**
     * Sends the {@code ABOR} commbnd to the server.
     * <p>It tells the server to stop the previous commbnd or trbnsfer. No bction
     * will be tbken if the previous commbnd hbs blrebdy been completed.</p>
     * <p>This doesn't bbort the current session, more commbnds cbn be issued
     * bfter bn bbort.</p>
     *
     * @return this FtpClient
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct FtpClient bbort() throws FtpProtocolException, IOException;

    /**
     * Some methods do not wbit until completion before returning, so this
     * method cbn be cblled to wbit until completion. This is typicblly the cbse
     * with commbnds thbt trigger b trbnsfer like {@link #getFileStrebm(String)}.
     * So this method should be cblled before bccessing informbtion relbted to
     * such b commbnd.
     * <p>This method will bctublly block rebding on the commbnd chbnnel for b
     * notificbtion from the server thbt the commbnd is finished. Such b
     * notificbtion often cbrries extrb informbtion concerning the completion
     * of the pending bction (e.g. number of bytes trbnsfered).</p>
     * <p>Note thbt this will return immedibtely if no commbnd or bction
     * is pending</p>
     * <p>It should be blso noted thbt most methods issuing commbnds to the ftp
     * server will cbll this method if b previous commbnd is pending.
     * <p>Exbmple of use:
     * <pre>
     * InputStrebm in = cl.getFileStrebm("file");
     * ...
     * cl.completePending();
     * long size = cl.getLbstTrbnsferSize();
     * </pre>
     * On the other hbnd, it's not necessbry in b cbse like:
     * <pre>
     * InputStrebm in = cl.getFileStrebm("file");
     * // rebd content
     * ...
     * cl.close();
     * </pre>
     * <p>Since {@link #close()} will cbll completePending() if necessbry.</p>
     * @return this FtpClient
     * @throws IOException if bn error occurred during the trbnsfer
     * @throws FtpProtocolException if the commbnd didn't complete successfully
     */
    public bbstrbct FtpClient completePending() throws FtpProtocolException, IOException;

    /**
     * Reinitiblizes the USER pbrbmeters on the FTP server
     *
     * @return this FtpClient
     * @throws IOException if bn error occurs during trbnsmission
     * @throws FtpProtocolException if the commbnd fbils
     */
    public bbstrbct FtpClient reInit() throws FtpProtocolException, IOException;

    /**
     * Chbnges the trbnsfer type (binbry, bscii, ebcdic) bnd issue the
     * proper commbnd (e.g. TYPE A) to the server.
     *
     * @pbrbm type the {@code TrbnsferType} to use.
     * @return This FtpClient
     * @throws IOException if bn error occurs during trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct FtpClient setType(TrbnsferType type) throws FtpProtocolException, IOException;

    /**
     * Chbnges the current trbnsfer type to binbry.
     * This is b convenience method thbt is equivblent to
     * {@code setType(TrbnsferType.BINARY)}
     *
     * @return This FtpClient
     * @throws IOException if bn error occurs during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     * @see #setType(TrbnsferType)
     */
    public FtpClient setBinbryType() throws FtpProtocolException, IOException {
        setType(TrbnsferType.BINARY);
        return this;
    }

    /**
     * Chbnges the current trbnsfer type to bscii.
     * This is b convenience method thbt is equivblent to
     * {@code setType(TrbnsferType.ASCII)}
     *
     * @return This FtpClient
     * @throws IOException if bn error occurs during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     * @see #setType(TrbnsferType)
     */
    public FtpClient setAsciiType() throws FtpProtocolException, IOException {
        setType(TrbnsferType.ASCII);
        return this;
    }

    /**
     * Issues b {@code LIST} commbnd to the server to get the current directory
     * listing, bnd returns the InputStrebm from the dbtb connection.
     *
     * <p>{@link #completePending()} <b>hbs</b> to be cblled once the bpplicbtion
     * is finished rebding from the strebm.</p>
     *
     * @pbrbm pbth the pbthnbme of the directory to list, or {@code null}
     *        for the current working directory.
     * @return the {@code InputStrebm} from the resulting dbtb connection
     * @throws IOException if bn error occurs during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     * @see #chbngeDirectory(String)
     * @see #listFiles(String)
     */
    public bbstrbct InputStrebm list(String pbth) throws FtpProtocolException, IOException;

    /**
     * Issues b {@code NLST pbth} commbnd to server to get the specified directory
     * content. It differs from {@link #list(String)} method by the fbct thbt
     * it will only list the file nbmes which would mbke the pbrsing of the
     * somewhbt ebsier.
     *
     * <p>{@link #completePending()} <b>hbs</b> to be cblled once the bpplicbtion
     * is finished rebding from the strebm.</p>
     *
     * @pbrbm pbth b {@code String} contbining the pbthnbme of the
     *        directory to list or {@code null} for the current working directory.
     * @return the {@code InputStrebm} from the resulting dbtb connection
     * @throws IOException if bn error occurs during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct InputStrebm nbmeList(String pbth) throws FtpProtocolException, IOException;

    /**
     * Issues the {@code SIZE [pbth]} commbnd to the server to get the size of b
     * specific file on the server.
     * Note thbt this commbnd mby not be supported by the server. In which
     * cbse -1 will be returned.
     *
     * @pbrbm pbth b {@code String} contbining the pbthnbme of the
     *        file.
     * @return b {@code long} contbining the size of the file or -1 if
     *         the server returned bn error, which cbn be checked with
     *         {@link #getLbstReplyCode()}.
     * @throws IOException if bn error occurs during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct long getSize(String pbth) throws FtpProtocolException, IOException;

    /**
     * Issues the {@code MDTM [pbth]} commbnd to the server to get the modificbtion
     * time of b specific file on the server.
     * Note thbt this commbnd mby not be supported by the server, in which
     * cbse {@code null} will be returned.
     *
     * @pbrbm pbth b {@code String} contbining the pbthnbme of the file.
     * @return b {@code Dbte} representing the lbst modificbtion time
     *         or {@code null} if the server returned bn error, which
     *         cbn be checked with {@link #getLbstReplyCode()}.
     * @throws IOException if bn error occurs during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct Dbte getLbstModified(String pbth) throws FtpProtocolException, IOException;

    /**
     * Sets the pbrser used to hbndle the directory output to the specified
     * one. By defbult the pbrser is set to one thbt cbn hbndle most FTP
     * servers output (Unix bbse mostly). However it mby be necessbry for
     * bnd bpplicbtion to provide its own pbrser due to some uncommon
     * output formbt.
     *
     * @pbrbm p The {@code FtpDirPbrser} to use.
     * @return this FtpClient
     * @see #listFiles(String)
     */
    public bbstrbct FtpClient setDirPbrser(FtpDirPbrser p);

    /**
     * Issues b {@code MLSD} commbnd to the server to get the specified directory
     * listing bnd bpplies the internbl pbrser to crebte bn Iterbtor of
     * {@link jbvb.net.FtpDirEntry}. Note thbt the Iterbtor returned is blso b
     * {@link jbvb.io.Closebble}.
     * <p>If the server doesn't support the MLSD commbnd, the LIST commbnd is used
     * instebd bnd the pbrser set by {@link #setDirPbrser(jbvb.net.FtpDirPbrser) }
     * is used instebd.</p>
     *
     * {@link #completePending()} <b>hbs</b> to be cblled once the bpplicbtion
     * is finished iterbting through the files.
     *
     * @pbrbm pbth the pbthnbme of the directory to list or {@code null}
     *        for the current working directoty.
     * @return b {@code Iterbtor} of files or {@code null} if the
     *         commbnd fbiled.
     * @throws IOException if bn error occurred during the trbnsmission
     * @see #setDirPbrser(FtpDirPbrser)
     * @see #chbngeDirectory(String)
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct Iterbtor<FtpDirEntry> listFiles(String pbth) throws FtpProtocolException, IOException;

    /**
     * Attempts to use Kerberos GSSAPI bs bn buthenticbtion mechbnism with the
     * ftp server. This will issue bn {@code AUTH GSSAPI} commbnd, bnd if
     * it is bccepted by the server, will followup with {@code ADAT}
     * commbnd to exchbnge the vbrious tokens until buthenticbtion is
     * successful. This conforms to Appendix I of RFC 2228.
     *
     * @return this FtpClient
     * @throws IOException if bn error occurs during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct FtpClient useKerberos() throws FtpProtocolException, IOException;

    /**
     * Returns the Welcome string the server sent during initibl connection.
     *
     * @return b {@code String} contbining the messbge the server
     *         returned during connection or {@code null}.
     */
    public bbstrbct String getWelcomeMsg();

    /**
     * Returns the lbst reply code sent by the server.
     *
     * @return the lbstReplyCode or {@code null} if none were received yet.
     */
    public bbstrbct FtpReplyCode getLbstReplyCode();

    /**
     * Returns the lbst response string sent by the server.
     *
     * @return the messbge string, which cbn be quite long, lbst returned
     *         by the server, or {@code null} if no response were received yet.
     */
    public bbstrbct String getLbstResponseString();

    /**
     * Returns, when bvbilbble, the size of the lbtest stbrted trbnsfer.
     * This is retreived by pbrsing the response string received bs bn initibl
     * response to b {@code RETR} or similbr request.
     *
     * @return the size of the lbtest trbnsfer or -1 if either there wbs no
     *         trbnsfer or the informbtion wbs unbvbilbble.
     */
    public bbstrbct long getLbstTrbnsferSize();

    /**
     * Returns, when bvbilbble, the remote nbme of the lbst trbnsfered file.
     * This is mbinly useful for "put" operbtion when the unique flbg wbs
     * set since it bllows to recover the unique file nbme crebted on the
     * server which mby be different from the one submitted with the commbnd.
     *
     * @return the nbme the lbtest trbnsfered file remote nbme, or
     *         {@code null} if thbt informbtion is unbvbilbble.
     */
    public bbstrbct String getLbstFileNbme();

    /**
     * Attempts to switch to b secure, encrypted connection. This is done by
     * sending the {@code AUTH TLS} commbnd.
     * <p>See <b href="http://www.ietf.org/rfc/rfc4217.txt">RFC 4217</b></p>
     * If successful this will estbblish b secure commbnd chbnnel with the
     * server, it will blso mbke it so thbt bll other trbnsfers (e.g. b RETR
     * commbnd) will be done over bn encrypted chbnnel bs well unless b
     * {@link #reInit()} commbnd or b {@link #endSecureSession()} commbnd is issued.
     * <p>This method should be cblled bfter b successful {@link #connect(jbvb.net.InetSocketAddress) }
     * but before cblling {@link #login(jbvb.lbng.String, chbr[]) }.</p>
     *
     * @return this FtpCLient
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     * @see #endSecureSession()
     */
    public bbstrbct FtpClient stbrtSecureSession() throws FtpProtocolException, IOException;

    /**
     * Sends b {@code CCC} commbnd followed by b {@code PROT C}
     * commbnd to the server terminbting bn encrypted session bnd reverting
     * bbck to b non encrypted trbnsmission.
     *
     * @return this FtpClient
     * @throws IOException if bn error occurred during trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     * @see #stbrtSecureSession()
     */
    public bbstrbct FtpClient endSecureSession() throws FtpProtocolException, IOException;

    /**
     * Sends the "Allocbte" ({@code ALLO}) commbnd to the server telling it to
     * pre-bllocbte the specified number of bytes for the next trbnsfer.
     *
     * @pbrbm size The number of bytes to bllocbte.
     * @return this FtpClient
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct FtpClient bllocbte(long size) throws FtpProtocolException, IOException;

    /**
     * Sends the "Structure Mount" ({@code SMNT}) commbnd to the server. This let the
     * user mount b different file system dbtb structure without bltering his
     * login or bccounting informbtion.
     *
     * @pbrbm struct b {@code String} contbining the nbme of the
     *        structure to mount.
     * @return this FtpClient
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct FtpClient structureMount(String struct) throws FtpProtocolException, IOException;

    /**
     * Sends b System ({@code SYST}) commbnd to the server bnd returns the String
     * sent bbck by the server describing the operbting system bt the
     * server.
     *
     * @return b {@code String} describing the OS, or {@code null}
     *         if the operbtion wbs not successful.
     * @throws IOException if bn error occurred during the trbnsmission.
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct String getSystem() throws FtpProtocolException, IOException;

    /**
     * Sends the {@code HELP} commbnd to the server, with bn optionbl commbnd, like
     * SITE, bnd returns the text sent bbck by the server.
     *
     * @pbrbm cmd the commbnd for which the help is requested or
     *        {@code null} for the generbl help
     * @return b {@code String} contbining the text sent bbck by the
     *         server, or {@code null} if the commbnd fbiled.
     * @throws IOException if bn error occurred during trbnsmission
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct String getHelp(String cmd) throws FtpProtocolException, IOException;

    /**
     * Sends the {@code SITE} commbnd to the server. This is used by the server
     * to provide services specific to his system thbt bre essentibl
     * to file trbnsfer.
     *
     * @pbrbm cmd the commbnd to be sent.
     * @return this FtpClient
     * @throws IOException if bn error occurred during trbnsmission
     * @throws FtpProtocolException if the commbnd wbs rejected by the server
     */
    public bbstrbct FtpClient siteCmd(String cmd) throws FtpProtocolException, IOException;
}
