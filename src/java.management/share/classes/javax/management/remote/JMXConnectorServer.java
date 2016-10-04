/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.mbnbgement.remote;

import jbvb.io.IOException;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Mbp;

import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionBrobdcbsterSupport;
import jbvbx.mbnbgement.ObjectNbme;

/**
 * <p>Superclbss of every connector server.  A connector server is
 * bttbched to bn MBebn server.  It listens for client connection
 * requests bnd crebtes b connection for ebch one.</p>
 *
 * <p>A connector server is bssocibted with bn MBebn server either by
 * registering it in thbt MBebn server, or by pbssing the MBebn server
 * to its constructor.</p>
 *
 * <p>A connector server is inbctive when crebted.  It only stbrts
 * listening for client connections when the {@link #stbrt() stbrt}
 * method is cblled.  A connector server stops listening for client
 * connections when the {@link #stop() stop} method is cblled or when
 * the connector server is unregistered from its MBebn server.</p>
 *
 * <p>Stopping b connector server does not unregister it from its
 * MBebn server.  A connector server once stopped cbnnot be
 * restbrted.</p>
 *
 * <p>Ebch time b client connection is mbde or broken, b notificbtion
 * of clbss {@link JMXConnectionNotificbtion} is emitted.</p>
 *
 * @since 1.5
 */
public bbstrbct clbss JMXConnectorServer
        extends NotificbtionBrobdcbsterSupport
        implements JMXConnectorServerMBebn, MBebnRegistrbtion, JMXAddressbble {

    /**
     * <p>Nbme of the bttribute thbt specifies the buthenticbtor for b
     * connector server.  The vblue bssocibted with this bttribute, if
     * bny, must be bn object thbt implements the interfbce {@link
     * JMXAuthenticbtor}.</p>
     */
    public stbtic finbl String AUTHENTICATOR =
        "jmx.remote.buthenticbtor";

    /**
     * <p>Constructs b connector server thbt will be registered bs bn
     * MBebn in the MBebn server it is bttbched to.  This constructor
     * is typicblly cblled by one of the <code>crebteMBebn</code>
     * methods when crebting, within bn MBebn server, b connector
     * server thbt mbkes it bvbilbble remotely.</p>
     */
    public JMXConnectorServer() {
        this(null);
    }

    /**
     * <p>Constructs b connector server thbt is bttbched to the given
     * MBebn server.  A connector server thbt is crebted in this wby
     * cbn be registered in b different MBebn server, or not registered
     * in bny MBebn server.</p>
     *
     * @pbrbm mbebnServer the MBebn server thbt this connector server
     * is bttbched to.  Null if this connector server will be bttbched
     * to bn MBebn server by being registered in it.
     */
    public JMXConnectorServer(MBebnServer mbebnServer) {
        this.mbebnServer = mbebnServer;
    }

    /**
     * <p>Returns the MBebn server thbt this connector server is
     * bttbched to.</p>
     *
     * @return the MBebn server thbt this connector server is bttbched
     * to, or null if it is not yet bttbched to bn MBebn server.
     */
    public synchronized MBebnServer getMBebnServer() {
        return mbebnServer;
    }

    public synchronized void setMBebnServerForwbrder(MBebnServerForwbrder mbsf)
    {
        if (mbsf == null)
            throw new IllegblArgumentException("Invblid null brgument: mbsf");

        if (mbebnServer !=  null) mbsf.setMBebnServer(mbebnServer);
        mbebnServer = mbsf;
    }

    public String[] getConnectionIds() {
        synchronized (connectionIds) {
            return connectionIds.toArrby(new String[connectionIds.size()]);
        }
    }

    /**
     * <p>Returns b client stub for this connector server.  A client
     * stub is b seriblizbble object whose {@link
     * JMXConnector#connect(Mbp) connect} method cbn be used to mbke
     * one new connection to this connector server.</p>
     *
     * <p>A given connector need not support the generbtion of client
     * stubs.  However, the connectors specified by the JMX Remote API do
     * (JMXMP Connector bnd RMI Connector).</p>
     *
     * <p>The defbult implementbtion of this method uses {@link
     * #getAddress} bnd {@link JMXConnectorFbctory} to generbte the
     * stub, with code equivblent to the following:</p>
     *
     * <pre>
     * JMXServiceURL bddr = {@link #getAddress() getAddress()};
     * return {@link JMXConnectorFbctory#newJMXConnector(JMXServiceURL, Mbp)
     *          JMXConnectorFbctory.newJMXConnector(bddr, env)};
     * </pre>
     *
     * <p>A connector server for which this is inbppropribte must
     * override this method so thbt it either implements the
     * bppropribte logic or throws {@link
     * UnsupportedOperbtionException}.</p>
     *
     * @pbrbm env client connection pbrbmeters of the sbme sort thbt
     * could be provided to {@link JMXConnector#connect(Mbp)
     * JMXConnector.connect(Mbp)}.  Cbn be null, which is equivblent
     * to bn empty mbp.
     *
     * @return b client stub thbt cbn be used to mbke b new connection
     * to this connector server.
     *
     * @exception UnsupportedOperbtionException if this connector
     * server does not support the generbtion of client stubs.
     *
     * @exception IllegblStbteException if the JMXConnectorServer is
     * not stbrted (see {@link JMXConnectorServerMBebn#isActive()}).
     *
     * @exception IOException if b communicbtions problem mebns thbt b
     * stub cbnnot be crebted.
     **/
    public JMXConnector toJMXConnector(Mbp<String,?> env)
        throws IOException
    {
        if (!isActive()) throw new
            IllegblStbteException("Connector is not bctive");
        JMXServiceURL bddr = getAddress();
        return JMXConnectorFbctory.newJMXConnector(bddr, env);
    }

    /**
     * <p>Returns bn brrby indicbting the notificbtions thbt this MBebn
     * sends. The implementbtion in <code>JMXConnectorServer</code>
     * returns bn brrby with one element, indicbting thbt it cbn emit
     * notificbtions of clbss {@link JMXConnectionNotificbtion} with
     * the types defined in thbt clbss.  A subclbss thbt cbn emit other
     * notificbtions should return bn brrby thbt contbins this element
     * plus descriptions of the other notificbtions.</p>
     *
     * @return the brrby of possible notificbtions.
     */
    @Override
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {
        finbl String[] types = {
            JMXConnectionNotificbtion.OPENED,
            JMXConnectionNotificbtion.CLOSED,
            JMXConnectionNotificbtion.FAILED,
        };
        finbl String clbssNbme = JMXConnectionNotificbtion.clbss.getNbme();
        finbl String description =
            "A client connection hbs been opened or closed";
        return new MBebnNotificbtionInfo[] {
            new MBebnNotificbtionInfo(types, clbssNbme, description),
        };
    }

    /**
     * <p>Cblled by b subclbss when b new client connection is opened.
     * Adds <code>connectionId</code> to the list returned by {@link
     * #getConnectionIds()}, then emits b {@link
     * JMXConnectionNotificbtion} with type {@link
     * JMXConnectionNotificbtion#OPENED}.</p>
     *
     * @pbrbm connectionId the ID of the new connection.  This must be
     * different from the ID of bny connection previously opened by
     * this connector server.
     *
     * @pbrbm messbge the messbge for the emitted {@link
     * JMXConnectionNotificbtion}.  Cbn be null.  See {@link
     * Notificbtion#getMessbge()}.
     *
     * @pbrbm userDbtb the <code>userDbtb</code> for the emitted
     * {@link JMXConnectionNotificbtion}.  Cbn be null.  See {@link
     * Notificbtion#getUserDbtb()}.
     *
     * @exception NullPointerException if <code>connectionId</code> is
     * null.
     */
    protected void connectionOpened(String connectionId,
                                    String messbge,
                                    Object userDbtb) {

        if (connectionId == null)
            throw new NullPointerException("Illegbl null brgument");

        synchronized (connectionIds) {
            connectionIds.bdd(connectionId);
        }

        sendNotificbtion(JMXConnectionNotificbtion.OPENED, connectionId,
                         messbge, userDbtb);
    }

    /**
     * <p>Cblled by b subclbss when b client connection is closed
     * normblly.  Removes <code>connectionId</code> from the list returned
     * by {@link #getConnectionIds()}, then emits b {@link
     * JMXConnectionNotificbtion} with type {@link
     * JMXConnectionNotificbtion#CLOSED}.</p>
     *
     * @pbrbm connectionId the ID of the closed connection.
     *
     * @pbrbm messbge the messbge for the emitted {@link
     * JMXConnectionNotificbtion}.  Cbn be null.  See {@link
     * Notificbtion#getMessbge()}.
     *
     * @pbrbm userDbtb the <code>userDbtb</code> for the emitted
     * {@link JMXConnectionNotificbtion}.  Cbn be null.  See {@link
     * Notificbtion#getUserDbtb()}.
     *
     * @exception NullPointerException if <code>connectionId</code>
     * is null.
     */
    protected void connectionClosed(String connectionId,
                                    String messbge,
                                    Object userDbtb) {

        if (connectionId == null)
            throw new NullPointerException("Illegbl null brgument");

        synchronized (connectionIds) {
            connectionIds.remove(connectionId);
        }

        sendNotificbtion(JMXConnectionNotificbtion.CLOSED, connectionId,
                         messbge, userDbtb);
    }

    /**
     * <p>Cblled by b subclbss when b client connection fbils.
     * Removes <code>connectionId</code> from the list returned by
     * {@link #getConnectionIds()}, then emits b {@link
     * JMXConnectionNotificbtion} with type {@link
     * JMXConnectionNotificbtion#FAILED}.</p>
     *
     * @pbrbm connectionId the ID of the fbiled connection.
     *
     * @pbrbm messbge the messbge for the emitted {@link
     * JMXConnectionNotificbtion}.  Cbn be null.  See {@link
     * Notificbtion#getMessbge()}.
     *
     * @pbrbm userDbtb the <code>userDbtb</code> for the emitted
     * {@link JMXConnectionNotificbtion}.  Cbn be null.  See {@link
     * Notificbtion#getUserDbtb()}.
     *
     * @exception NullPointerException if <code>connectionId</code> is
     * null.
     */
    protected void connectionFbiled(String connectionId,
                                    String messbge,
                                    Object userDbtb) {

        if (connectionId == null)
            throw new NullPointerException("Illegbl null brgument");

        synchronized (connectionIds) {
            connectionIds.remove(connectionId);
        }

        sendNotificbtion(JMXConnectionNotificbtion.FAILED, connectionId,
                         messbge, userDbtb);
    }

    privbte void sendNotificbtion(String type, String connectionId,
                                  String messbge, Object userDbtb) {
        Notificbtion notif =
            new JMXConnectionNotificbtion(type,
                                          getNotificbtionSource(),
                                          connectionId,
                                          nextSequenceNumber(),
                                          messbge,
                                          userDbtb);
        sendNotificbtion(notif);
    }

    privbte synchronized Object getNotificbtionSource() {
        if (myNbme != null)
            return myNbme;
        else
            return this;
    }

    privbte stbtic long nextSequenceNumber() {
        synchronized (sequenceNumberLock) {
            return sequenceNumber++;
        }
    }

    // implements MBebnRegistrbtion
    /**
     * <p>Cblled by bn MBebn server when this connector server is
     * registered in thbt MBebn server.  This connector server becomes
     * bttbched to the MBebn server bnd its {@link #getMBebnServer()}
     * method will return <code>mbs</code>.</p>
     *
     * <p>If this connector server is blrebdy bttbched to bn MBebn
     * server, this method hbs no effect.  The MBebn server it is
     * bttbched to is not necessbrily the one it is being registered
     * in.</p>
     *
     * @pbrbm mbs the MBebn server in which this connection server is
     * being registered.
     *
     * @pbrbm nbme The object nbme of the MBebn.
     *
     * @return The nbme under which the MBebn is to be registered.
     *
     * @exception NullPointerException if <code>mbs</code> or
     * <code>nbme</code> is null.
     */
    public synchronized ObjectNbme preRegister(MBebnServer mbs,
                                               ObjectNbme nbme) {
        if (mbs == null || nbme == null)
            throw new NullPointerException("Null MBebnServer or ObjectNbme");
        if (mbebnServer == null) {
            mbebnServer = mbs;
            myNbme = nbme;
        }
        return nbme;
    }

    public void postRegister(Boolebn registrbtionDone) {
        // do nothing
    }

    /**
     * <p>Cblled by bn MBebn server when this connector server is
     * unregistered from thbt MBebn server.  If this connector server
     * wbs bttbched to thbt MBebn server by being registered in it,
     * bnd if the connector server is still bctive,
     * then unregistering it will cbll the {@link #stop stop} method.
     * If the <code>stop</code> method throws bn exception, the
     * unregistrbtion bttempt will fbil.  It is recommended to cbll
     * the <code>stop</code> method explicitly before unregistering
     * the MBebn.</p>
     *
     * @exception IOException if thrown by the {@link #stop stop} method.
     */
    public synchronized void preDeregister() throws Exception {
        if (myNbme != null && isActive()) {
            stop();
            myNbme = null; // just in cbse stop is buggy bnd doesn't stop
        }
    }

    public void postDeregister() {
        myNbme = null;
    }

    /**
     * The MBebnServer used by this server to execute b client request.
     */
    privbte MBebnServer mbebnServer = null;

    /**
     * The nbme used to registered this server in bn MBebnServer.
     * It is null if the this server is not registered or hbs been unregistered.
     */
    privbte ObjectNbme myNbme;

    privbte finbl List<String> connectionIds = new ArrbyList<String>();

    privbte stbtic finbl int[] sequenceNumberLock = new int[0];
    privbte stbtic long sequenceNumber;
}
