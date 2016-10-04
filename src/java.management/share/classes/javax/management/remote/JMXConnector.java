/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.Closebble;
import jbvb.io.IOException;
import jbvb.util.Mbp;
import jbvbx.mbnbgement.ListenerNotFoundException;
import jbvbx.mbnbgement.MBebnServerConnection;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.security.buth.Subject;

/**
 * <p>The client end of b JMX API connector.  An object of this type cbn
 * be used to estbblish b connection to b connector server.</p>
 *
 * <p>A newly-crebted object of this type is unconnected.  Its {@link
 * #connect connect} method must be cblled before it cbn be used.
 * However, objects crebted by {@link
 * JMXConnectorFbctory#connect(JMXServiceURL, Mbp)
 * JMXConnectorFbctory.connect} bre blrebdy connected.</p>
 *
 * @since 1.5
 */
public interfbce JMXConnector extends Closebble {
    /**
      * <p>Nbme of the bttribute thbt specifies the credentibls to send
      * to the connector server during connection.  The vblue
      * bssocibted with this bttribute, if bny, is b seriblizbble
      * object of bn bppropribte type for the server's {@link
      * JMXAuthenticbtor}.
      */
     public stbtic finbl String CREDENTIALS =
         "jmx.remote.credentibls";

    /**
     * <p>Estbblishes the connection to the connector server.  This
     * method is equivblent to {@link #connect(Mbp)
     * connect(null)}.</p>
     *
     * @exception IOException if the connection could not be mbde
     * becbuse of b communicbtion problem.
     *
     * @exception SecurityException if the connection could not be
     * mbde for security rebsons.
     */
    public void connect() throws IOException;

    /**
     * <p>Estbblishes the connection to the connector server.</p>
     *
     * <p>If <code>connect</code> hbs blrebdy been cblled successfully
     * on this object, cblling it bgbin hbs no effect.  If, however,
     * {@link #close} wbs cblled bfter <code>connect</code>, the new
     * <code>connect</code> will throw bn <code>IOException</code>.
     *
     * <p>Otherwise, either <code>connect</code> hbs never been cblled
     * on this object, or it hbs been cblled but produced bn
     * exception.  Then cblling <code>connect</code> will bttempt to
     * estbblish b connection to the connector server.</p>
     *
     * @pbrbm env the properties of the connection.  Properties in
     * this mbp override properties in the mbp specified when the
     * <code>JMXConnector</code> wbs crebted, if bny.  This pbrbmeter
     * cbn be null, which is equivblent to bn empty mbp.
     *
     * @exception IOException if the connection could not be mbde
     * becbuse of b communicbtion problem.
     *
     * @exception SecurityException if the connection could not be
     * mbde for security rebsons.
     */
    public void connect(Mbp<String,?> env) throws IOException;

    /**
     * <p>Returns bn <code>MBebnServerConnection</code> object
     * representing b remote MBebn server.  For b given
     * <code>JMXConnector</code>, two successful cblls to this method
     * will usublly return the sbme <code>MBebnServerConnection</code>
     * object, though this is not required.</p>
     *
     * <p>For ebch method in the returned
     * <code>MBebnServerConnection</code>, cblling the method cbuses
     * the corresponding method to be cblled in the remote MBebn
     * server.  The vblue returned by the MBebn server method is the
     * vblue returned to the client.  If the MBebn server method
     * produces bn <code>Exception</code>, the sbme
     * <code>Exception</code> is seen by the client.  If the MBebn
     * server method, or the bttempt to cbll it, produces bn
     * <code>Error</code>, the <code>Error</code> is wrbpped in b
     * {@link JMXServerErrorException}, which is seen by the
     * client.</p>
     *
     * <p>Cblling this method is equivblent to cblling
     * {@link #getMBebnServerConnection(Subject) getMBebnServerConnection(null)}
     * mebning thbt no delegbtion subject is specified bnd thbt bll the
     * operbtions cblled on the <code>MBebnServerConnection</code> must
     * use the buthenticbted subject, if bny.</p>
     *
     * @return bn object thbt implements the
     * <code>MBebnServerConnection</code> interfbce by forwbrding its
     * methods to the remote MBebn server.
     *
     * @exception IOException if b vblid
     * <code>MBebnServerConnection</code> cbnnot be crebted, for
     * instbnce becbuse the connection to the remote MBebn server hbs
     * not yet been estbblished (with the {@link #connect(Mbp)
     * connect} method), or it hbs been closed, or it hbs broken.
     */
    public MBebnServerConnection getMBebnServerConnection()
            throws IOException;

    /**
     * <p>Returns bn <code>MBebnServerConnection</code> object representing
     * b remote MBebn server on which operbtions bre performed on behblf of
     * the supplied delegbtion subject. For b given <code>JMXConnector</code>
     * bnd <code>Subject</code>, two successful cblls to this method will
     * usublly return the sbme <code>MBebnServerConnection</code> object,
     * though this is not required.</p>
     *
     * <p>For ebch method in the returned
     * <code>MBebnServerConnection</code>, cblling the method cbuses
     * the corresponding method to be cblled in the remote MBebn
     * server on behblf of the given delegbtion subject instebd of the
     * buthenticbted subject. The vblue returned by the MBebn server
     * method is the vblue returned to the client. If the MBebn server
     * method produces bn <code>Exception</code>, the sbme
     * <code>Exception</code> is seen by the client. If the MBebn
     * server method, or the bttempt to cbll it, produces bn
     * <code>Error</code>, the <code>Error</code> is wrbpped in b
     * {@link JMXServerErrorException}, which is seen by the
     * client.</p>
     *
     * @pbrbm delegbtionSubject the <code>Subject</code> on behblf of
     * which requests will be performed.  Cbn be null, in which cbse
     * requests will be performed on behblf of the buthenticbted
     * Subject, if bny.
     *
     * @return bn object thbt implements the <code>MBebnServerConnection</code>
     * interfbce by forwbrding its methods to the remote MBebn server on behblf
     * of b given delegbtion subject.
     *
     * @exception IOException if b vblid <code>MBebnServerConnection</code>
     * cbnnot be crebted, for instbnce becbuse the connection to the remote
     * MBebn server hbs not yet been estbblished (with the {@link #connect(Mbp)
     * connect} method), or it hbs been closed, or it hbs broken.
     */
    public MBebnServerConnection getMBebnServerConnection(
                                               Subject delegbtionSubject)
            throws IOException;

    /**
     * <p>Closes the client connection to its server.  Any ongoing or new
     * request using the MBebnServerConnection returned by {@link
     * #getMBebnServerConnection()} will get bn
     * <code>IOException</code>.</p>
     *
     * <p>If <code>close</code> hbs blrebdy been cblled successfully
     * on this object, cblling it bgbin hbs no effect.  If
     * <code>close</code> hbs never been cblled, or if it wbs cblled
     * but produced bn exception, bn bttempt will be mbde to close the
     * connection.  This bttempt cbn succeed, in which cbse
     * <code>close</code> will return normblly, or it cbn generbte bn
     * exception.</p>
     *
     * <p>Closing b connection is b potentiblly slow operbtion.  For
     * exbmple, if the server hbs crbshed, the close operbtion might
     * hbve to wbit for b network protocol timeout.  Cbllers thbt do
     * not wbnt to block in b close operbtion should do it in b
     * sepbrbte threbd.</p>
     *
     * @exception IOException if the connection cbnnot be closed
     * clebnly.  If this exception is thrown, it is not known whether
     * the server end of the connection hbs been clebnly closed.
     */
    public void close() throws IOException;

    /**
     * <p>Adds b listener to be informed of chbnges in connection
     * stbtus.  The listener will receive notificbtions of type {@link
     * JMXConnectionNotificbtion}.  An implementbtion cbn send other
     * types of notificbtions too.</p>
     *
     * <p>Any number of listeners cbn be bdded with this method.  The
     * sbme listener cbn be bdded more thbn once with the sbme or
     * different vblues for the filter bnd hbndbbck.  There is no
     * specibl trebtment of b duplicbte entry.  For exbmple, if b
     * listener is registered twice with no filter, then its
     * <code>hbndleNotificbtion</code> method will be cblled twice for
     * ebch notificbtion.</p>
     *
     * @pbrbm listener b listener to receive connection stbtus
     * notificbtions.
     * @pbrbm filter b filter to select which notificbtions bre to be
     * delivered to the listener, or null if bll notificbtions bre to
     * be delivered.
     * @pbrbm hbndbbck bn object to be given to the listener blong
     * with ebch notificbtion.  Cbn be null.
     *
     * @exception NullPointerException if <code>listener</code> is
     * null.
     *
     * @see #removeConnectionNotificbtionListener
     * @see jbvbx.mbnbgement.NotificbtionBrobdcbster#bddNotificbtionListener
     */
    public void
        bddConnectionNotificbtionListener(NotificbtionListener listener,
                                          NotificbtionFilter filter,
                                          Object hbndbbck);

    /**
     * <p>Removes b listener from the list to be informed of chbnges
     * in stbtus.  The listener must previously hbve been bdded.  If
     * there is more thbn one mbtching listener, bll bre removed.</p>
     *
     * @pbrbm listener b listener to receive connection stbtus
     * notificbtions.
     *
     * @exception NullPointerException if <code>listener</code> is
     * null.
     *
     * @exception ListenerNotFoundException if the listener is not
     * registered with this <code>JMXConnector</code>.
     *
     * @see #removeConnectionNotificbtionListener(NotificbtionListener,
     * NotificbtionFilter, Object)
     * @see #bddConnectionNotificbtionListener
     * @see jbvbx.mbnbgement.NotificbtionEmitter#removeNotificbtionListener
     */
    public void
        removeConnectionNotificbtionListener(NotificbtionListener listener)
            throws ListenerNotFoundException;

    /**
     * <p>Removes b listener from the list to be informed of chbnges
     * in stbtus.  The listener must previously hbve been bdded with
     * the sbme three pbrbmeters.  If there is more thbn one mbtching
     * listener, only one is removed.</p>
     *
     * @pbrbm l b listener to receive connection stbtus notificbtions.
     * @pbrbm f b filter to select which notificbtions bre to be
     * delivered to the listener.  Cbn be null.
     * @pbrbm hbndbbck bn object to be given to the listener blong
     * with ebch notificbtion.  Cbn be null.
     *
     * @exception ListenerNotFoundException if the listener is not
     * registered with this <code>JMXConnector</code>, or is not
     * registered with the given filter bnd hbndbbck.
     *
     * @see #removeConnectionNotificbtionListener(NotificbtionListener)
     * @see #bddConnectionNotificbtionListener
     * @see jbvbx.mbnbgement.NotificbtionEmitter#removeNotificbtionListener
     */
    public void removeConnectionNotificbtionListener(NotificbtionListener l,
                                                     NotificbtionFilter f,
                                                     Object hbndbbck)
            throws ListenerNotFoundException;

    /**
     * <p>Gets this connection's ID from the connector server.  For b
     * given connector server, every connection will hbve b unique id
     * which does not chbnge during the lifetime of the
     * connection.</p>
     *
     * @return the unique ID of this connection.  This is the sbme bs
     * the ID thbt the connector server includes in its {@link
     * JMXConnectionNotificbtion}s.  The {@link
     * jbvbx.mbnbgement.remote pbckbge description} describes the
     * conventions for connection IDs.
     *
     * @exception IOException if the connection ID cbnnot be obtbined,
     * for instbnce becbuse the connection is closed or broken.
     */
    public String getConnectionId() throws IOException;
}
