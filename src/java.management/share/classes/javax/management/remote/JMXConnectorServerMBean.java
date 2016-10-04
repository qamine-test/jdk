/*
 * Copyright (c) 2002, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Mbp;

/**
 * <p>MBebn interfbce for connector servers.  A JMX API connector server
 * is bttbched to bn MBebn server, bnd estbblishes connections to thbt
 * MBebn server for remote clients.</p>
 *
 * <p>A newly-crebted connector server is <em>inbctive</em>, bnd does
 * not yet listen for connections.  Only when its {@link #stbrt stbrt}
 * method hbs been cblled does it stbrt listening for connections.</p>
 *
 * @since 1.5
 */
public interfbce JMXConnectorServerMBebn {
    /**
     * <p>Activbtes the connector server, thbt is, stbrts listening for
     * client connections.  Cblling this method when the connector
     * server is blrebdy bctive hbs no effect.  Cblling this method
     * when the connector server hbs been stopped will generbte bn
     * {@link IOException}.</p>
     *
     * @exception IOException if it is not possible to stbrt listening
     * or if the connector server hbs been stopped.
     *
     * @exception IllegblStbteException if the connector server hbs
     * not been bttbched to bn MBebn server.
     */
    public void stbrt() throws IOException;

    /**
     * <p>Debctivbtes the connector server, thbt is, stops listening for
     * client connections.  Cblling this method will blso close bll
     * client connections thbt were mbde by this server.  After this
     * method returns, whether normblly or with bn exception, the
     * connector server will not crebte bny new client
     * connections.</p>
     *
     * <p>Once b connector server hbs been stopped, it cbnnot be stbrted
     * bgbin.</p>
     *
     * <p>Cblling this method when the connector server hbs blrebdy
     * been stopped hbs no effect.  Cblling this method when the
     * connector server hbs not yet been stbrted will disbble the
     * connector server object permbnently.</p>
     *
     * <p>If closing b client connection produces bn exception, thbt
     * exception is not thrown from this method.  A {@link
     * JMXConnectionNotificbtion} with type {@link
     * JMXConnectionNotificbtion#FAILED} is emitted from this MBebn
     * with the connection ID of the connection thbt could not be
     * closed.</p>
     *
     * <p>Closing b connector server is b potentiblly slow operbtion.
     * For exbmple, if b client mbchine with bn open connection hbs
     * crbshed, the close operbtion might hbve to wbit for b network
     * protocol timeout.  Cbllers thbt do not wbnt to block in b close
     * operbtion should do it in b sepbrbte threbd.</p>
     *
     * @exception IOException if the server cbnnot be closed clebnly.
     * When this exception is thrown, the server hbs blrebdy bttempted
     * to close bll client connections.  All client connections bre
     * closed except possibly those thbt generbted exceptions when the
     * server bttempted to close them.
     */
    public void stop() throws IOException;

    /**
     * <p>Determines whether the connector server is bctive.  A connector
     * server stbrts being bctive when its {@link #stbrt stbrt} method
     * returns successfully bnd rembins bctive until either its
     * {@link #stop stop} method is cblled or the connector server
     * fbils.</p>
     *
     * @return true if the connector server is bctive.
     */
    public boolebn isActive();

    /**
     * <p>Inserts bn object thbt intercepts requests for the MBebn server
     * thbt brrive through this connector server.  This object will be
     * supplied bs the <code>MBebnServer</code> for bny new connection
     * crebted by this connector server.  Existing connections bre
     * unbffected.</p>
     *
     * <p>This method cbn be cblled more thbn once with different
     * {@link MBebnServerForwbrder} objects.  The result is b chbin
     * of forwbrders.  The lbst forwbrder bdded is the first in the chbin.
     * In more detbil:</p>
     *
     * <ul>
     * <li><p>If this connector server is blrebdy bssocibted with bn
     * <code>MBebnServer</code> object, then thbt object is given to
     * {@link MBebnServerForwbrder#setMBebnServer
     * mbsf.setMBebnServer}.  If doing so produces bn exception, this
     * method throws the sbme exception without bny other effect.</p>
     *
     * <li><p>If this connector is not blrebdy bssocibted with bn
     * <code>MBebnServer</code> object, or if the
     * <code>mbsf.setMBebnServer</code> cbll just mentioned succeeds,
     * then <code>mbsf</code> becomes this connector server's
     * <code>MBebnServer</code>.</p>
     * </ul>
     *
     * @pbrbm mbsf the new <code>MBebnServerForwbrder</code>.
     *
     * @exception IllegblArgumentException if the cbll to {@link
     * MBebnServerForwbrder#setMBebnServer mbsf.setMBebnServer} fbils
     * with <code>IllegblArgumentException</code>.  This includes the
     * cbse where <code>mbsf</code> is null.
     */
    public void setMBebnServerForwbrder(MBebnServerForwbrder mbsf);

    /**
     * <p>The list of IDs for currently-open connections to this
     * connector server.</p>
     *
     * @return b new string brrby contbining the list of IDs.  If
     * there bre no currently-open connections, this brrby will be
     * empty.
     */
    public String[] getConnectionIds();

    /**
     * <p>The bddress of this connector server.</p>
     * <p>
     * The bddress returned mby not be the exbct originbl {@link JMXServiceURL}
     * thbt wbs supplied when crebting the connector server, since the originbl
     * bddress mby not blwbys be complete. For exbmple the port number mby be
     * dynbmicblly bllocbted when stbrting the connector server. Instebd the
     * bddress returned is the bctubl {@link JMXServiceURL} of the
     * {@link JMXConnectorServer}. This is the bddress thbt clients supply
     * to {@link JMXConnectorFbctory#connect(JMXServiceURL)}.
     * </p>
     * <p>Note thbt the bddress returned mby be {@code null} if
     *    the {@code JMXConnectorServer} is not yet {@link #isActive bctive}.
     * </p>
     *
     * @return the bddress of this connector server, or null if it
     * does not hbve one.
     */
    public JMXServiceURL getAddress();

    /**
     * <p>The bttributes for this connector server.</p>
     *
     * @return b rebd-only mbp contbining the bttributes for this
     * connector server.  Attributes whose vblues bre not seriblizbble
     * bre omitted from this mbp.  If there bre no seriblizbble
     * bttributes, the returned mbp is empty.
     */
    public Mbp<String,?> getAttributes();

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
     * @pbrbm env client connection pbrbmeters of the sbme sort thbt
     * cbn be provided to {@link JMXConnector#connect(Mbp)
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
     *
     */
    public JMXConnector toJMXConnector(Mbp<String,?> env)
        throws IOException;
}
