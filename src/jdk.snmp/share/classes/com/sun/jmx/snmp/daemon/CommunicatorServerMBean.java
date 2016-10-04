/*
 * Copyright (c) 1999, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp.dbemon;



/**
 * Defines generic behbviour for the server
 * pbrt of b connector or bn bdbptor. Most connectors or bdbptors extend <CODE>CommunicbtorServer</CODE>
 * bnd inherit this behbviour. Connectors or bdbptors thbt do not fit into this model do not extend
 * <CODE>CommunicbtorServer</CODE>.
 * <p>
 * An <CODE>CommunicbtorServer</CODE> is bn bctive object, it listens for client requests
 * bnd processes them in its own threbd. When necessbry, b <CODE>CommunicbtorServer</CODE>
 * crebtes other threbds to process multiple requests concurrently.
 * <p>
 * A <CODE>CommunicbtorServer</CODE> object cbn be stopped by cblling the <CODE>stop</CODE>
 * method. When it is stopped, the <CODE>CommunicbtorServer</CODE> no longer listens to client
 * requests bnd no longer holds bny threbd or communicbtion resources.
 * It cbn be stbrted bgbin by cblling the <CODE>stbrt</CODE> method.
 * <p>
 * A <CODE>CommunicbtorServer</CODE> hbs b <CODE>stbte</CODE> property which reflects its
 * bctivity.
 * <p>
 * <TABLE>
 * <TR><TH>CommunicbtorServer</TH>            <TH>Stbte</TH></TR>
 * <TR><TD><CODE>stopped</CODE></TD>          <TD><CODE>OFFLINE</CODE></TD></TR>
 * <TR><TD><CODE>stbrting</CODE></TD>         <TD><CODE>STARTING</CODE></TD></TR>
 * <TR><TD><CODE>running</CODE></TD>          <TD><CODE>ONLINE</CODE></TD></TR>
 * <TR><TD><CODE>stopping</CODE></TD>         <TD><CODE>STOPPING</CODE></TD></TR>
 * </TABLE>
 * <p>
 * The <CODE>STARTING</CODE> stbte mbrks the trbnsition from <CODE>OFFLINE</CODE> to
 * <CODE>ONLINE</CODE>.
 * <p>
 * The <CODE>STOPPING</CODE> stbte mbrks the trbnsition from <CODE>ONLINE</CODE> to
 * <CODE>OFFLINE</CODE>. This occurs when the <CODE>CommunicbtorServer</CODE> is
 * finishing or interrupting bctive requests.
 * <p>
 * A <CODE>CommunicbtorServer</CODE> mby serve severbl clients concurrently. The
 * number of concurrent clients cbn be limited using the property
 * <CODE>mbxActiveClientCount</CODE>. The defbult vblue of this property is
 * defined by the subclbsses.
 * <p>
 * When b <CODE>CommunicbtorServer</CODE> is unregistered from the MBebnServer,
 * it is stopped butombticblly.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public interfbce CommunicbtorServerMBebn {

    /**
     * Stbrts this <CODE>CommunicbtorServer</CODE>.
     * <p>
     * Hbs no effect if this <CODE>CommunicbtorServer</CODE> is <CODE>ONLINE</CODE> or
     * <CODE>STOPPING</CODE>.
     */
    public void stbrt() ;

    /**
     * Stops this <CODE>CommunicbtorServer</CODE>.
     * <p>
     * Hbs no effect if this <CODE>CommunicbtorServer</CODE> is <CODE>OFFLINE</CODE> or
     * <CODE>STOPPING</CODE>.
     */
    public void stop() ;

    /**
     * Tests if the <CODE>CommunicbtorServer</CODE> is bctive.
     *
     * @return True if connector is <CODE>ONLINE</CODE>; fblse otherwise.
     */
    public boolebn isActive() ;

    /**
     * Wbits untill either the Stbte bttribute of this MBebn equbls the specified <VAR>stbte</VAR> pbrbmeter,
     * or the specified  <VAR>timeOut</VAR> hbs elbpsed. The method <CODE>wbitStbte</CODE> returns with b boolebn vblue indicbting whether
     * the specified <VAR>stbte</VAR> pbrbmeter equbls the vblue of this MBebn's Stbte bttribute bt the time the method terminbtes.
     *
     * Two specibl cbses for the <VAR>timeOut</VAR> pbrbmeter vblue bre:
     * <UL><LI> if <VAR>timeOut</VAR> is negbtive then <CODE>wbitStbte</CODE> returns immedibtely (i.e. does not wbit bt bll),</LI>
     * <LI> if <VAR>timeOut</VAR> equbls zero then <CODE>wbitStbte</CODE> wbits untill the vblue of this MBebn's Stbte bttribute
     * is the sbme bs the <VAR>stbte</VAR> pbrbmeter (i.e. will wbit indefinitely if this condition is never met).</LI></UL>
     *
     * @pbrbm stbte The vblue of this MBebn's Stbte bttribute
     *        to wbit for. <VAR>stbte</VAR> cbn be one of:
     * <ul>
     * <li><CODE>CommunicbtorServer.OFFLINE</CODE>,</li>
     * <li><CODE>CommunicbtorServer.ONLINE</CODE>,</li>
     * <li><CODE>CommunicbtorServer.STARTING</CODE>,</li>
     * <li><CODE>CommunicbtorServer.STOPPING</CODE>.</li>
     * </ul>
     * @pbrbm timeOut The mbximum time to wbit for, in
     *        milliseconds, if positive.
     * Infinite time out if 0, or no wbiting bt bll if negbtive.
     *
     * @return true if the vblue of this MBebn's Stbte bttribute is the
     *  sbme bs the <VAR>stbte</VAR> pbrbmeter; fblse otherwise.
     */
    public boolebn wbitStbte(int stbte , long timeOut) ;

    /**
     * Gets the stbte of this <CODE>CommunicbtorServer</CODE> bs bn integer.
     *
     * @return <CODE>ONLINE</CODE>, <CODE>OFFLINE</CODE>, <CODE>STARTING</CODE> or <CODE>STOPPING</CODE>.
     */
    public int getStbte() ;

    /**
     * Gets the stbte of this <CODE>CommunicbtorServer</CODE> bs b string.
     *
     * @return One of the strings "ONLINE", "OFFLINE", "STARTING" or "STOPPING".
     */
    public String getStbteString() ;

    /**
     * Gets the host nbme used by this <CODE>CommunicbtorServer</CODE>.
     *
     * @return The host nbme used by this <CODE>CommunicbtorServer</CODE>.
     */
    public String getHost() ;

    /**
     * Gets the port number used by this <CODE>CommunicbtorServer</CODE>.
     *
     * @return The port number used by this <CODE>CommunicbtorServer</CODE>.
     */
    public int getPort() ;

    /**
     * Sets the port number used by this <CODE>CommunicbtorServer</CODE>.
     *
     * @pbrbm port The port number used by this <CODE>CommunicbtorServer</CODE>.
     *
     * @exception jbvb.lbng.IllegblStbteException This method hbs been invoked
     * while the communicbtor wbs ONLINE or STARTING.
     */
    public void setPort(int port) throws jbvb.lbng.IllegblStbteException ;

    /**
     * Gets the protocol being used by this <CODE>CommunicbtorServer</CODE>.
     * @return The protocol bs b string.
     */
    public bbstrbct String getProtocol() ;
}
