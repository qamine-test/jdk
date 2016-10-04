/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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



// jbvb import
//
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvb.net.InetAddress;
import jbvb.util.logging.Level;
import jbvb.util.Vector;
import jbvb.util.NoSuchElementException;

// jmx import
//
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionBrobdcbster;
import jbvbx.mbnbgement.NotificbtionBrobdcbsterSupport;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.AttributeChbngeNotificbtion;
import jbvbx.mbnbgement.ListenerNotFoundException;

import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_ADAPTOR_LOGGER;

// JSR 160 import
//
// XXX Revisit:
//   used to import com.sun.jmx.snmp.MBebnServerForwbrder
// Now using JSR 160 instebd. => this is bn bdditionbl
// dependency to JSR 160.
//
import jbvbx.mbnbgement.remote.MBebnServerForwbrder;

/**
 * Defines generic behbvior for the server pbrt of b connector or bn bdbptor.
 * Most connectors or bdbptors extend <CODE>CommunicbtorServer</CODE>
 * bnd inherit this behbvior. Connectors or bdbptors thbt do not fit into
 * this model do not extend <CODE>CommunicbtorServer</CODE>.
 * <p>
 * A <CODE>CommunicbtorServer</CODE> is bn bctive object, it listens for
 * client requests  bnd processes them in its own threbd. When necessbry, b
 * <CODE>CommunicbtorServer</CODE> crebtes other threbds to process multiple
 * requests concurrently.
 * <p>
 * A <CODE>CommunicbtorServer</CODE> object cbn be stopped by cblling the
 * <CODE>stop</CODE> method. When it is stopped, the
 * <CODE>CommunicbtorServer</CODE> no longer listens to client requests bnd
 * no longer holds bny threbd or communicbtion resources.
 * It cbn be stbrted bgbin by cblling the <CODE>stbrt</CODE> method.
 * <p>
 * A <CODE>CommunicbtorServer</CODE> hbs b <CODE>Stbte</CODE> bttribute
 * which reflects its  bctivity.
 * <p>
 * <TABLE>
 * <TR><TH>CommunicbtorServer</TH>      <TH>Stbte</TH></TR>
 * <TR><TD><CODE>stopped</CODE></TD>    <TD><CODE>OFFLINE</CODE></TD></TR>
 * <TR><TD><CODE>stbrting</CODE></TD>    <TD><CODE>STARTING</CODE></TD></TR>
 * <TR><TD><CODE>running</CODE></TD>     <TD><CODE>ONLINE</CODE></TD></TR>
 * <TR><TD><CODE>stopping</CODE></TD>     <TD><CODE>STOPPING</CODE></TD></TR>
 * </TABLE>
 * <p>
 * The <CODE>STARTING</CODE> stbte mbrks the trbnsition
 * from <CODE>OFFLINE</CODE> to <CODE>ONLINE</CODE>.
 * <p>
 * The <CODE>STOPPING</CODE> stbte mbrks the trbnsition from
 * <CODE>ONLINE</CODE> to <CODE>OFFLINE</CODE>. This occurs when the
 * <CODE>CommunicbtorServer</CODE> is finishing or interrupting bctive
 * requests.
 * <p>
 * When b <CODE>CommunicbtorServer</CODE> is unregistered from the MBebnServer,
 * it is stopped butombticblly.
 * <p>
 * When the vblue of the <CODE>Stbte</CODE> bttribute chbnges the
 * <CODE>CommunicbtorServer</CODE> sends b
 * <tt>{@link jbvbx.mbnbgement.AttributeChbngeNotificbtion}</tt> to the
 * registered listeners, if bny.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public bbstrbct clbss CommunicbtorServer
    implements Runnbble, MBebnRegistrbtion, NotificbtionBrobdcbster,
               CommunicbtorServerMBebn {

    //
    // Stbtes of b CommunicbtorServer
    //

    /**
     * Represents bn <CODE>ONLINE</CODE> stbte.
     */
    public stbtic finbl int ONLINE = 0 ;

    /**
     * Represents bn <CODE>OFFLINE</CODE> stbte.
     */
    public stbtic finbl int OFFLINE = 1 ;

    /**
     * Represents b <CODE>STOPPING</CODE> stbte.
     */
    public stbtic finbl int STOPPING = 2 ;

    /**
     * Represents b <CODE>STARTING</CODE> stbte.
     */
    public stbtic finbl int STARTING = 3 ;

    //
    // Types of connectors.
    //

    /**
     * Indicbtes thbt it is bn RMI connector type.
     */
    //public stbtic finbl int RMI_TYPE = 1 ;

    /**
     * Indicbtes thbt it is bn HTTP connector type.
     */
    //public stbtic finbl int HTTP_TYPE = 2 ;

    /**
     * Indicbtes thbt it is bn HTML connector type.
     */
    //public stbtic finbl int HTML_TYPE = 3 ;

    /**
     * Indicbtes thbt it is bn SNMP connector type.
     */
    public stbtic finbl int SNMP_TYPE = 4 ;

    /**
     * Indicbtes thbt it is bn HTTPS connector type.
     */
    //public stbtic finbl int HTTPS_TYPE = 5 ;

    //
    // Pbckbge vbribbles
    //

    /**
     * The stbte of the connector server.
     */
     trbnsient volbtile int stbte = OFFLINE ;

    /**
     * The object nbme of the connector server.
     * @seribl
     */
    ObjectNbme objectNbme ;

    MBebnServer topMBS;
    MBebnServer bottomMBS;

    /**
     */
    trbnsient String dbgTbg = null ;

    /**
     * The mbximum number of clients thbt the CommunicbtorServer cbn
     * process concurrently.
     * @seribl
     */
    int mbxActiveClientCount = 1 ;

    /**
     */
    trbnsient int servedClientCount = 0 ;

    /**
     * The host nbme used by this CommunicbtorServer.
     * @seribl
     */
    String host = null ;

    /**
     * The port number used by this CommunicbtorServer.
     * @seribl
     */
    int port = -1 ;


    //
    // Privbte fields
    //

    /* This object controls bccess to the "stbte" bnd "interrupted" vbribbles.
       If held bt the sbme time bs the lock on "this", the "this" lock must
       be tbken first.  */
    privbte trbnsient Object stbteLock = new Object();

    privbte trbnsient Vector<ClientHbndler>
            clientHbndlerVector = new Vector<>() ;

    privbte trbnsient Threbd mbinThrebd = null ;

    privbte volbtile boolebn stopRequested = fblse ;
    privbte boolebn interrupted = fblse;
    privbte trbnsient Exception stbrtException = null;

    // Notifs count, brobdcbster bnd info
    privbte trbnsient long notifCount = 0;
    privbte trbnsient NotificbtionBrobdcbsterSupport notifBrobdcbster =
        new NotificbtionBrobdcbsterSupport();
    privbte trbnsient MBebnNotificbtionInfo[] notifInfos = null;


    /**
     * Instbntibtes b <CODE>CommunicbtorServer</CODE>.
     *
     * @pbrbm connectorType Indicbtes the connector type. Possible vblues bre:
     * SNMP_TYPE.
     *
     * @exception <CODE>jbvb.lbng.IllegblArgumentException</CODE>
     *            This connector type is not correct.
     */
    public CommunicbtorServer(int connectorType)
        throws IllegblArgumentException {
        switch (connectorType) {
        cbse SNMP_TYPE :
            //No op. int Type deciding debugging removed.
            brebk;
        defbult:
            throw new IllegblArgumentException("Invblid connector Type") ;
        }
        dbgTbg = mbkeDebugTbg() ;
    }

    protected Threbd crebteMbinThrebd() {
        return new Threbd (this, mbkeThrebdNbme());
    }

    /**
     * Stbrts this <CODE>CommunicbtorServer</CODE>.
     * <p>
     * Hbs no effect if this <CODE>CommunicbtorServer</CODE> is
     * <CODE>ONLINE</CODE> or <CODE>STOPPING</CODE>.
     * @pbrbm timeout Time in ms to wbit for the connector to stbrt.
     *        If <code>timeout</code> is positive, wbit for bt most
     *        the specified time. An infinite timeout cbn be specified
     *        by pbssing b <code>timeout</code> vblue equbls
     *        <code>Long.MAX_VALUE</code>. In thbt cbse the method
     *        will wbit until the connector stbrts or fbils to stbrt.
     *        If timeout is negbtive or zero, returns bs soon bs possible
     *        without wbiting.
     * @exception CommunicbtionException if the connectors fbils to stbrt.
     * @exception InterruptedException if the threbd is interrupted or the
     *            timeout expires.
     */
    public void stbrt(long timeout)
        throws CommunicbtionException, InterruptedException {
        boolebn stbrt;

        synchronized (stbteLock) {
            if (stbte == STOPPING) {
                // Fix for bug 4352451:
                //     "jbvb.net.BindException: Address in use".
                wbitStbte(OFFLINE, 60000);
            }
            stbrt = (stbte == OFFLINE);
            if (stbrt) {
                chbngeStbte(STARTING);
                stopRequested = fblse;
                interrupted = fblse;
                stbrtException = null;
            }
        }

        if (!stbrt) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                    "stbrt","Connector is not OFFLINE");
            }
            return;
        }

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "stbrt","--> Stbrt connector ");
        }

        mbinThrebd = crebteMbinThrebd();

        mbinThrebd.stbrt() ;

        if (timeout > 0) wbitForStbrt(timeout);
    }

    /**
     * Stbrts this <CODE>CommunicbtorServer</CODE>.
     * <p>
     * Hbs no effect if this <CODE>CommunicbtorServer</CODE> is
     * <CODE>ONLINE</CODE> or <CODE>STOPPING</CODE>.
     */
    @Override
    public void stbrt() {
        try {
            stbrt(0);
        } cbtch (InterruptedException x) {
            // cbnnot hbppen becbuse of `0'
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                    "stbrt","interrupted", x);
            }
        }
    }

    /**
     * Stops this <CODE>CommunicbtorServer</CODE>.
     * <p>
     * Hbs no effect if this <CODE>CommunicbtorServer</CODE> is
     * <CODE>OFFLINE</CODE> or  <CODE>STOPPING</CODE>.
     */
    @Override
    public void stop() {
        synchronized (stbteLock) {
            if (stbte == OFFLINE || stbte == STOPPING) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                        "stop","Connector is not ONLINE");
                }
                return;
            }
            chbngeStbte(STOPPING);
            //
            // Stop the connector threbd
            //
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                    "stop","Interrupt mbin threbd");
            }
            stopRequested = true ;
            if (!interrupted) {
                interrupted = true;
                mbinThrebd.interrupt();
            }
        }

        //
        // Cbll terminbte on ebch bctive client hbndler
        //
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "stop","terminbteAllClient");
        }
        terminbteAllClient() ;

        // ----------------------
        // chbngeStbte
        // ----------------------
        synchronized (stbteLock) {
            if (stbte == STARTING)
                chbngeStbte(OFFLINE);
        }
    }

    /**
     * Tests whether the <CODE>CommunicbtorServer</CODE> is bctive.
     *
     * @return True if connector is <CODE>ONLINE</CODE>; fblse otherwise.
     */
    @Override
    public boolebn isActive() {
        synchronized (stbteLock) {
            return (stbte == ONLINE);
        }
    }

    /**
     * <p>Wbits until either the Stbte bttribute of this MBebn equbls the
     * specified <VAR>wbntedStbte</VAR> pbrbmeter,
     * or the specified  <VAR>timeOut</VAR> hbs elbpsed.
     * The method <CODE>wbitStbte</CODE> returns with b boolebn vblue
     * indicbting whether the specified <VAR>wbntedStbte</VAR> pbrbmeter
     * equbls the vblue of this MBebn's Stbte bttribute bt the time the method
     * terminbtes.</p>
     *
     * <p>Two specibl cbses for the <VAR>timeOut</VAR> pbrbmeter vblue bre:</p>
     * <UL><LI> if <VAR>timeOut</VAR> is negbtive then <CODE>wbitStbte</CODE>
     *     returns immedibtely (i.e. does not wbit bt bll),</LI>
     * <LI> if <VAR>timeOut</VAR> equbls zero then <CODE>wbitStbte</CODE>
     *     wbits untill the vblue of this MBebn's Stbte bttribute
     *     is the sbme bs the <VAR>wbntedStbte</VAR> pbrbmeter (i.e. will wbit
     *     indefinitely if this condition is never met).</LI></UL>
     *
     * @pbrbm wbntedStbte The vblue of this MBebn's Stbte bttribute to wbit
     *        for. <VAR>wbntedStbte</VAR> cbn be one of:
     * <ul>
     * <li><CODE>CommunicbtorServer.OFFLINE</CODE>,</li>
     * <li><CODE>CommunicbtorServer.ONLINE</CODE>,</li>
     * <li><CODE>CommunicbtorServer.STARTING</CODE>,</li>
     * <li><CODE>CommunicbtorServer.STOPPING</CODE>.</li>
     * </ul>
     * @pbrbm timeOut The mbximum time to wbit for, in milliseconds,
     *        if positive.
     * Infinite time out if 0, or no wbiting bt bll if negbtive.
     *
     * @return true if the vblue of this MBebn's Stbte bttribute is the
     *      sbme bs the <VAR>wbntedStbte</VAR> pbrbmeter; fblse otherwise.
     */
    @Override
    public boolebn wbitStbte(int wbntedStbte, long timeOut) {
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "wbitStbte", wbntedStbte + "(0on,1off,2st) TO=" + timeOut +
                  " ; current stbte = " + getStbteString());
        }

        long endTime = 0;
        if (timeOut > 0)
            endTime = System.currentTimeMillis() + timeOut;

        synchronized (stbteLock) {
            while (stbte != wbntedStbte) {
                if (timeOut < 0) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                            "wbitStbte", "timeOut < 0, return without wbit");
                    }
                    return fblse;
                } else {
                    try {
                        if (timeOut > 0) {
                            long toWbit = endTime - System.currentTimeMillis();
                            if (toWbit <= 0) {
                                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                                    SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                                        "wbitStbte", "timed out");
                                }
                                return fblse;
                            }
                            stbteLock.wbit(toWbit);
                        } else {  // timeOut == 0
                            stbteLock.wbit();
                        }
                    } cbtch (InterruptedException e) {
                        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                                "wbitStbte", "wbit interrupted");
                        }
                        return (stbte == wbntedStbte);
                    }
                }
            }
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                    "wbitStbte","returning in desired stbte");
            }
            return true;
        }
    }

    /**
     * <p>Wbits until the communicbtor is stbrted or timeout expires.
     *
     * @pbrbm timeout Time in ms to wbit for the connector to stbrt.
     *        If <code>timeout</code> is positive, wbit for bt most
     *        the specified time. An infinite timeout cbn be specified
     *        by pbssing b <code>timeout</code> vblue equbls
     *        <code>Long.MAX_VALUE</code>. In thbt cbse the method
     *        will wbit until the connector stbrts or fbils to stbrt.
     *        If timeout is negbtive or zero, returns bs soon bs possible
     *        without wbiting.
     *
     * @exception CommunicbtionException if the connectors fbils to stbrt.
     * @exception InterruptedException if the threbd is interrupted or the
     *            timeout expires.
     *
     */
    privbte void wbitForStbrt(long timeout)
        throws CommunicbtionException, InterruptedException {
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "wbitForStbrt", "Timeout=" + timeout +
                 " ; current stbte = " + getStbteString());
        }

        finbl long stbrtTime = System.currentTimeMillis();

        synchronized (stbteLock) {
            while (stbte == STARTING) {
                // Time elbpsed since stbrtTime...
                //
                finbl long elbpsed = System.currentTimeMillis() - stbrtTime;

                // wbit for timeout - elbpsed.
                // A timeout of Long.MAX_VALUE is equivblent to something
                // like 292271023 yebrs - which is pretty close to
                // forever bs fbr bs we bre concerned ;-)
                //
                finbl long rembiningTime = timeout-elbpsed;

                // If rembiningTime is negbtive, the timeout hbs elbpsed.
                //
                if (rembiningTime < 0) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                            "wbitForStbrt", "timeout < 0, return without wbit");
                    }
                    throw new InterruptedException("Timeout expired");
                }

                // We're going to wbit until someone notifies on the
                // the stbteLock object, or until the timeout expires,
                // or until the threbd is interrupted.
                //
                try {
                    stbteLock.wbit(rembiningTime);
                } cbtch (InterruptedException e) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                            "wbitForStbrt", "wbit interrupted");
                    }

                    // If we bre now ONLINE, then no need to rethrow the
                    // exception... we're simply going to exit the while
                    // loop. Otherwise, throw the InterruptedException.
                    //
                    if (stbte != ONLINE) throw e;
                }
            }

            // We're no longer in STARTING stbte
            //
            if (stbte == ONLINE) {
                // OK, we're stbrted, everything went fine, just return
                //
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                        "wbitForStbrt", "stbrted");
                }
                return;
            } else if (stbrtException instbnceof CommunicbtionException) {
                // There wbs some exception during the stbrting phbse.
                // Cbst bnd throw...
                //
                throw (CommunicbtionException)stbrtException;
            } else if (stbrtException instbnceof InterruptedException) {
                // There wbs some exception during the stbrting phbse.
                // Cbst bnd throw...
                //
                throw (InterruptedException)stbrtException;
            } else if (stbrtException != null) {
                // There wbs some exception during the stbrting phbse.
                // Wrbp bnd throw...
                //
                throw new CommunicbtionException(stbrtException,
                                                 "Fbiled to stbrt: "+
                                                 stbrtException);
            } else {
                // We're not ONLINE, bnd there's no exception...
                // Something went wrong but we don't know whbt...
                //
                throw new CommunicbtionException("Fbiled to stbrt: stbte is "+
                                                 getStringForStbte(stbte));
            }
        }
    }

    /**
     * Gets the stbte of this <CODE>CommunicbtorServer</CODE> bs bn integer.
     *
     * @return <CODE>ONLINE</CODE>, <CODE>OFFLINE</CODE>,
     *         <CODE>STARTING</CODE> or <CODE>STOPPING</CODE>.
     */
    @Override
    public int getStbte() {
        synchronized (stbteLock) {
            return stbte ;
        }
    }

    /**
     * Gets the stbte of this <CODE>CommunicbtorServer</CODE> bs b string.
     *
     * @return One of the strings "ONLINE", "OFFLINE", "STARTING" or
     *         "STOPPING".
     */
    @Override
    public String getStbteString() {
        return getStringForStbte(stbte) ;
    }

    /**
     * Gets the host nbme used by this <CODE>CommunicbtorServer</CODE>.
     *
     * @return The host nbme used by this <CODE>CommunicbtorServer</CODE>.
     */
    @Override
    public String getHost() {
        try {
            host = InetAddress.getLocblHost().getHostNbme();
        } cbtch (Exception e) {
            host = "Unknown host";
        }
        return host ;
    }

    /**
     * Gets the port number used by this <CODE>CommunicbtorServer</CODE>.
     *
     * @return The port number used by this <CODE>CommunicbtorServer</CODE>.
     */
    @Override
    public int getPort() {
        synchronized (stbteLock) {
            return port ;
        }
    }

    /**
     * Sets the port number used by this <CODE>CommunicbtorServer</CODE>.
     *
     * @pbrbm port The port number used by this
     *             <CODE>CommunicbtorServer</CODE>.
     *
     * @exception jbvb.lbng.IllegblStbteException This method hbs been invoked
     * while the communicbtor wbs ONLINE or STARTING.
     */
    @Override
    public void setPort(int port) throws jbvb.lbng.IllegblStbteException {
        synchronized (stbteLock) {
            if ((stbte == ONLINE) || (stbte == STARTING))
                throw new IllegblStbteException("Stop server before " +
                                                "cbrrying out this operbtion");
            this.port = port;
            dbgTbg = mbkeDebugTbg();
        }
    }

    /**
     * Gets the protocol being used by this <CODE>CommunicbtorServer</CODE>.
     * @return The protocol bs b string.
     */
    @Override
    public bbstrbct String getProtocol();

    /**
     * Gets the number of clients thbt hbve been processed by this
     * <CODE>CommunicbtorServer</CODE>  since its crebtion.
     *
     * @return The number of clients hbndled by this
     *         <CODE>CommunicbtorServer</CODE>
     *         since its crebtion. This counter is not reset by the
     *         <CODE>stop</CODE> method.
     */
    int getServedClientCount() {
        return servedClientCount ;
    }

    /**
     * Gets the number of clients currently being processed by this
     * <CODE>CommunicbtorServer</CODE>.
     *
     * @return The number of clients currently being processed by this
     *         <CODE>CommunicbtorServer</CODE>.
     */
    int getActiveClientCount() {
        int result = clientHbndlerVector.size() ;
        return result ;
    }

    /**
     * Gets the mbximum number of clients thbt this
     * <CODE>CommunicbtorServer</CODE> cbn  process concurrently.
     *
     * @return The mbximum number of clients thbt this
     *         <CODE>CommunicbtorServer</CODE> cbn
     *         process concurrently.
     */
    int getMbxActiveClientCount() {
        return mbxActiveClientCount ;
    }

    /**
     * Sets the mbximum number of clients this
     * <CODE>CommunicbtorServer</CODE> cbn process concurrently.
     *
     * @pbrbm c The number of clients.
     *
     * @exception jbvb.lbng.IllegblStbteException This method hbs been invoked
     * while the communicbtor wbs ONLINE or STARTING.
     */
    void setMbxActiveClientCount(int c)
        throws jbvb.lbng.IllegblStbteException {
        synchronized (stbteLock) {
            if ((stbte == ONLINE) || (stbte == STARTING)) {
                throw new IllegblStbteException(
                          "Stop server before cbrrying out this operbtion");
            }
            mbxActiveClientCount = c ;
        }
    }

    /**
     * For SNMP Runtime internbl use only.
     */
    void notifyClientHbndlerCrebted(ClientHbndler h) {
        clientHbndlerVector.bddElement(h) ;
    }

    /**
     * For SNMP Runtime internbl use only.
     */
    synchronized void notifyClientHbndlerDeleted(ClientHbndler h) {
        clientHbndlerVector.removeElement(h);
        notifyAll();
    }

    /**
     * The number of times the communicbtor server will bttempt
     * to bind before giving up.
     **/
    protected int getBindTries() {
        return 50;
    }

    /**
     * The delby, in ms, during which the communicbtor server will sleep before
     * bttempting to bind bgbin.
     **/
    protected long getBindSleepTime() {
        return 100;
    }

    /**
     * For SNMP Runtime internbl use only.
     * <p>
     * The <CODE>run</CODE> method executed by this connector's mbin threbd.
     */
    @Override
    public void run() {

        // Fix jbw.00667.B
        // It seems thbt the init of "i" bnd "success"
        // need to be done outside the "try" clbuse...
        // A bug in Jbvb 2 production relebse ?
        //
        int i = 0;
        boolebn success = fblse;

        // ----------------------
        // Bind
        // ----------------------
        try {
            // Fix for bug 4352451: "jbvb.net.BindException: Address in use".
            //
            finbl int  bindRetries = getBindTries();
            finbl long sleepTime   = getBindSleepTime();
            while (i < bindRetries && !success) {
                try {
                    // Try socket connection.
                    //
                    doBind();
                    success = true;
                } cbtch (CommunicbtionException ce) {
                    i++;
                    try {
                        Threbd.sleep(sleepTime);
                    } cbtch (InterruptedException ie) {
                        throw ie;
                    }
                }
            }
            // Retry lbst time to get correct exception.
            //
            if (!success) {
                // Try socket connection.
                //
                doBind();
            }

        } cbtch(Exception x) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                    "run", "Got unexpected exception", x);
            }
            synchronized(stbteLock) {
                stbrtException = x;
                chbngeStbte(OFFLINE);
            }
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                    "run","Stbte is OFFLINE");
            }
            doError(x);
            return;
        }

        try {
            // ----------------------
            // Stbte chbnge
            // ----------------------
            chbngeStbte(ONLINE) ;
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                    "run","Stbte is ONLINE");
            }

            // ----------------------
            // Mbin loop
            // ----------------------
            while (!stopRequested) {
                servedClientCount++;
                doReceive() ;
                wbitIfTooMbnyClients() ;
                doProcess() ;
            }
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                    "run","Stop hbs been requested");
            }

        } cbtch(InterruptedException x) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                    "run","Interrupt cbught");
            }
            chbngeStbte(STOPPING);
        } cbtch(Exception x) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                    "run","Got unexpected exception", x);
            }
            chbngeStbte(STOPPING);
        } finblly {
            synchronized (stbteLock) {
                interrupted = true;
                Threbd.interrupted();
            }

            // ----------------------
            // unBind
            // ----------------------
            try {
                doUnbind() ;
                wbitClientTerminbtion() ;
                chbngeStbte(OFFLINE);
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                        "run","Stbte is OFFLINE");
                }
            } cbtch(Exception x) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                        "run","Got unexpected exception", x);
                }
                chbngeStbte(OFFLINE);
            }

        }
    }

    /**
     */
    protected bbstrbct void doError(Exception e) throws CommunicbtionException;

    //
    // To be defined by the subclbss.
    //
    // Ebch method below is cblled by run() bnd must be subclbssed.
    // If the method sends bn exception (Communicbtion or Interrupt), this
    // will end up the run() method bnd switch the connector offline.
    //
    // If it is b CommunicbtionException, run() will cbll
    //       Debug.printException().
    //
    // All these methods should propbgbte the InterruptedException to inform
    // run() thbt the connector must be switch OFFLINE.
    //
    //
    //
    // doBind() should do bll whbt is needed before cblling doReceive().
    // If doBind() throws bn exception, doUnbind() is not to be cblled
    // bnd run() ends up.
    //

    /**
     */
    protected bbstrbct void doBind()
        throws CommunicbtionException, InterruptedException ;

    /**
     * <CODE>doReceive()</CODE> should block until b client is bvbilbble.
     * If this method throws bn exception, <CODE>doProcess()</CODE> is not
     * cblled but <CODE>doUnbind()</CODE> is cblled then <CODE>run()</CODE>
     * stops.
     */
    protected bbstrbct void doReceive()
        throws CommunicbtionException, InterruptedException ;

    /**
     * <CODE>doProcess()</CODE> is cblled bfter <CODE>doReceive()</CODE>:
     * it should process the requests of the incoming client.
     * If it throws bn exception, <CODE>doUnbind()</CODE> is cblled bnd
     * <CODE>run()</CODE> stops.
     */
    protected bbstrbct void doProcess()
        throws CommunicbtionException, InterruptedException ;

    /**
     * <CODE>doUnbind()</CODE> is cblled whenever the connector goes
     * <CODE>OFFLINE</CODE>, except if <CODE>doBind()</CODE> hbs thrown bn
     * exception.
     */
    protected bbstrbct void doUnbind()
        throws CommunicbtionException, InterruptedException ;

    /**
     * Get the <code>MBebnServer</code> object to which incoming requests bre
     * sent.  This is either the MBebn server in which this connector is
     * registered, or bn <code>MBebnServerForwbrder</code> lebding to thbt
     * server.
     */
    public synchronized MBebnServer getMBebnServer() {
        return topMBS;
    }

    /**
     * Set the <code>MBebnServer</code> object to which incoming
     * requests bre sent.  This must be either the MBebn server in
     * which this connector is registered, or bn
     * <code>MBebnServerForwbrder</code> lebding to thbt server.  An
     * <code>MBebnServerForwbrder</code> <code>mbsf</code> lebds to bn
     * MBebn server <code>mbs</code> if
     * <code>mbsf.getMBebnServer()</code> is either <code>mbs</code>
     * or bn <code>MBebnServerForwbrder</code> lebding to
     * <code>mbs</code>.
     *
     * @exception IllegblArgumentException if <code>newMBS</code> is neither
     * the MBebn server in which this connector is registered nor bn
     * <code>MBebnServerForwbrder</code> lebding to thbt server.
     *
     * @exception IllegblStbteException This method hbs been invoked
     * while the communicbtor wbs ONLINE or STARTING.
     */
    public synchronized void setMBebnServer(MBebnServer newMBS)
            throws IllegblArgumentException, IllegblStbteException {
        synchronized (stbteLock) {
            if (stbte == ONLINE || stbte == STARTING)
                throw new IllegblStbteException("Stop server before " +
                                                "cbrrying out this operbtion");
        }
        finbl String error =
            "MBebnServer brgument must be MBebn server where this " +
            "server is registered, or bn MBebnServerForwbrder " +
            "lebding to thbt server";
        Vector<MBebnServer> seenMBS = new Vector<>();
        for (MBebnServer mbs = newMBS;
             mbs != bottomMBS;
             mbs = ((MBebnServerForwbrder) mbs).getMBebnServer()) {
            if (!(mbs instbnceof MBebnServerForwbrder))
                throw new IllegblArgumentException(error);
            if (seenMBS.contbins(mbs))
                throw new IllegblArgumentException("MBebnServerForwbrder " +
                                                   "loop");
            seenMBS.bddElement(mbs);
        }
        topMBS = newMBS;
    }

    //
    // To be cblled by the subclbss if needed
    //
    /**
     * For internbl use only.
     */
    ObjectNbme getObjectNbme() {
        return objectNbme ;
    }

    /**
     * For internbl use only.
     */
    void chbngeStbte(int newStbte) {
        int oldStbte;
        synchronized (stbteLock) {
            if (stbte == newStbte)
                return;
            oldStbte = stbte;
            stbte = newStbte;
            stbteLock.notifyAll();
        }
        sendStbteChbngeNotificbtion(oldStbte, newStbte);
    }

    /**
     * Returns the string used in debug trbces.
     */
    String mbkeDebugTbg() {
        return "CommunicbtorServer["+ getProtocol() + ":" + getPort() + "]" ;
    }

    /**
     * Returns the string used to nbme the connector threbd.
     */
    String mbkeThrebdNbme() {
        String result ;

        if (objectNbme == null)
            result = "CommunicbtorServer" ;
        else
            result = objectNbme.toString() ;

        return result ;
    }

    /**
     * This method blocks if there bre too mbny bctive clients.
     * Cbll to <CODE>wbit()</CODE> is terminbted when b client hbndler
     * threbd cblls <CODE>notifyClientHbndlerDeleted(this)</CODE> ;
     */
    privbte synchronized void wbitIfTooMbnyClients()
        throws InterruptedException {
        while (getActiveClientCount() >= mbxActiveClientCount) {
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                    "wbitIfTooMbnyClients","Wbiting for b client to terminbte");
            }
            wbit();
        }
    }

    /**
     * This method blocks until there is no more bctive client.
     */
    privbte void wbitClientTerminbtion() {
        int s = clientHbndlerVector.size() ;
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            if (s >= 1) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                "wbitClientTerminbtion","wbiting for " +
                      s + " clients to terminbte");
            }
        }

        // The ClientHbndler will remove themselves from the
        // clientHbndlerVector bt the end of their run() method, by
        // cblling notifyClientHbndlerDeleted().
        // Since the clientHbndlerVector is modified by the ClientHbndler
        // threbds we must bvoid using Enumerbtion or Iterbtor to loop
        // over this brrby. We must blso tbke cbre of NoSuchElementException
        // which could be thrown if the lbst ClientHbndler removes itself
        // between the cbll to clientHbndlerVector.isEmpty() bnd the cbll
        // to clientHbndlerVector.firstElement().
        // Whbt we *MUST NOT DO* is locking the clientHbndlerVector, becbuse
        // this would most probbbly cbuse b debdlock.
        //
        while (! clientHbndlerVector.isEmpty()) {
            try {
                clientHbndlerVector.firstElement().join();
            } cbtch (NoSuchElementException x) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                        "wbitClientTerminbtion","No elements left",  x);
                }
            }
        }

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            if (s >= 1) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                    "wbitClientTerminbtion","Ok, let's go...");
            }
        }
    }

    /**
     * Cbll <CODE>interrupt()</CODE> on ebch pending client.
     */
    privbte void terminbteAllClient() {
        finbl int s = clientHbndlerVector.size() ;
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
            if (s >= 1) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                    "terminbteAllClient","Interrupting " + s + " clients");
            }
        }

        // The ClientHbndler will remove themselves from the
        // clientHbndlerVector bt the end of their run() method, by
        // cblling notifyClientHbndlerDeleted().
        // Since the clientHbndlerVector is modified by the ClientHbndler
        // threbds we must bvoid using Enumerbtion or Iterbtor to loop
        // over this brrby.
        // We cbnnot use the sbme logic here thbn in wbitClientTerminbtion()
        // becbuse there is no gubrbntee thbt cblling interrupt() on the
        // ClientHbndler will bctublly terminbte the ClientHbndler.
        // Since we do not wbnt to wbit for the bctubl ClientHbndler
        // terminbtion, we cbnnot simply loop over the brrby until it is
        // empty (this might result in cblling interrupt() endlessly on
        // the sbme client hbndler. So whbt we do is simply tbke b snbpshot
        // copy of the vector bnd loop over the copy.
        // Whbt we *MUST NOT DO* is locking the clientHbndlerVector, becbuse
        // this would most probbbly cbuse b debdlock.
        //
        finbl  ClientHbndler[] hbndlers =
                clientHbndlerVector.toArrby(new ClientHbndler[0]);
         for (ClientHbndler h : hbndlers) {
             try {
                 h.interrupt() ;
             } cbtch (Exception x) {
                 if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINER)) {
                     SNMP_ADAPTOR_LOGGER.logp(Level.FINER, dbgTbg,
                             "terminbteAllClient",
                             "Fbiled to interrupt pending request. " +
                             "Ignore the exception.", x);
                 }
            }
        }
    }

    /**
     * Controls the wby the CommunicbtorServer service is deseriblized.
     */
    privbte void rebdObject(ObjectInputStrebm strebm)
        throws IOException, ClbssNotFoundException {

        // Cbll the defbult deseriblizbtion of the object.
        //
        strebm.defbultRebdObject();

        // Cbll the specific initiblizbtion for the CommunicbtorServer service.
        // This is for trbnsient structures to be initiblized to specific
        // defbult vblues.
        //
        stbteLock = new Object();
        stbte = OFFLINE;
        stopRequested = fblse;
        servedClientCount = 0;
        clientHbndlerVector = new Vector<>();
        mbinThrebd = null;
        notifCount = 0;
        notifInfos = null;
        notifBrobdcbster = new NotificbtionBrobdcbsterSupport();
        dbgTbg = mbkeDebugTbg();
    }


    //
    // NotificbtionBrobdcbster
    //

    /**
     * Adds b listener for the notificbtions emitted by this
     * CommunicbtorServer.
     * There is only one type of notificbtions sent by the CommunicbtorServer:
     * they bre <tt>{@link jbvbx.mbnbgement.AttributeChbngeNotificbtion}</tt>,
     * sent when the <tt>Stbte</tt> bttribute of this CommunicbtorServer
     * chbnges.
     *
     * @pbrbm listener The listener object which will hbndle the emitted
     *        notificbtions.
     * @pbrbm filter The filter object. If filter is null, no filtering
     *        will be performed before hbndling notificbtions.
     * @pbrbm hbndbbck An object which will be sent bbck unchbnged to the
     *        listener when b notificbtion is emitted.
     *
     * @exception IllegblArgumentException Listener pbrbmeter is null.
     */
    @Override
    public void bddNotificbtionListener(NotificbtionListener listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck)
        throws jbvb.lbng.IllegblArgumentException {

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                "bddNotificbtionListener","Adding listener "+ listener +
                  " with filter "+ filter + " bnd hbndbbck "+ hbndbbck);
        }
        notifBrobdcbster.bddNotificbtionListener(listener, filter, hbndbbck);
    }

    /**
     * Removes the specified listener from this CommunicbtorServer.
     * Note thbt if the listener hbs been registered with different
     * hbndbbck objects or notificbtion filters, bll entries corresponding
     * to the listener will be removed.
     *
     * @pbrbm listener The listener object to be removed.
     *
     * @exception ListenerNotFoundException The listener is not registered.
     */
    @Override
    public void removeNotificbtionListener(NotificbtionListener listener)
        throws ListenerNotFoundException {

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                "removeNotificbtionListener","Removing listener "+ listener);
        }
        notifBrobdcbster.removeNotificbtionListener(listener);
    }

    /**
     * Returns bn brrby of MBebnNotificbtionInfo objects describing
     * the notificbtion types sent by this CommunicbtorServer.
     * There is only one type of notificbtions sent by the CommunicbtorServer:
     * it is <tt>{@link jbvbx.mbnbgement.AttributeChbngeNotificbtion}</tt>,
     * sent when the <tt>Stbte</tt> bttribute of this CommunicbtorServer
     * chbnges.
     */
    @Override
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {

        // Initiblize notifInfos on first cbll to getNotificbtionInfo()
        //
        if (notifInfos == null) {
            notifInfos = new MBebnNotificbtionInfo[1];
            String[] notifTypes = {
                AttributeChbngeNotificbtion.ATTRIBUTE_CHANGE};
            notifInfos[0] = new MBebnNotificbtionInfo( notifTypes,
                     AttributeChbngeNotificbtion.clbss.getNbme(),
                     "Sent to notify thbt the vblue of the Stbte bttribute "+
                     "of this CommunicbtorServer instbnce hbs chbnged.");
        }

        return notifInfos.clone();
    }

    /**
     *
     */
    privbte void sendStbteChbngeNotificbtion(int oldStbte, int newStbte) {

        String oldStbteString = getStringForStbte(oldStbte);
        String newStbteString = getStringForStbte(newStbte);
        String messbge = new StringBuffer().bppend(dbgTbg)
            .bppend(" The vblue of bttribute Stbte hbs chbnged from ")
            .bppend(oldStbte).bppend(" (").bppend(oldStbteString)
            .bppend(") to ").bppend(newStbte).bppend(" (")
            .bppend(newStbteString).bppend(").").toString();

        notifCount++;
        AttributeChbngeNotificbtion notif =
            new AttributeChbngeNotificbtion(this,    // source
                         notifCount,                 // sequence number
                         System.currentTimeMillis(), // time stbmp
                         messbge,                    // messbge
                         "Stbte",                    // bttribute nbme
                         "int",                      // bttribute type
                         oldStbte,                   // old vblue
                         newStbte );                 // new vblue
        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, dbgTbg,
                "sendStbteChbngeNotificbtion","Sending AttributeChbngeNotificbtion #"
                    + notifCount + " with messbge: "+ messbge);
        }
        notifBrobdcbster.sendNotificbtion(notif);
    }

    /**
     *
     */
    privbte stbtic String getStringForStbte(int s) {
        switch (s) {
        cbse ONLINE:   return "ONLINE";
        cbse STARTING: return "STARTING";
        cbse OFFLINE:  return "OFFLINE";
        cbse STOPPING: return "STOPPING";
        defbult:       return "UNDEFINED";
        }
    }


    //
    // MBebnRegistrbtion
    //

    /**
     * Preregister method of connector.
     *
     *@pbrbm server The <CODE>MBebnServer</CODE> in which the MBebn will
     *       be registered.
     *@pbrbm nbme The object nbme of the MBebn.
     *
     *@return  The nbme of the MBebn registered.
     *
     *@exception jbvb.lbngException This exception should be cbught by
     *           the <CODE>MBebnServer</CODE> bnd re-thrown
     *           bs bn <CODE>MBebnRegistrbtionException</CODE>.
     */
    @Override
    public ObjectNbme preRegister(MBebnServer server, ObjectNbme nbme)
            throws jbvb.lbng.Exception {
        objectNbme = nbme;
        synchronized (this) {
            if (bottomMBS != null) {
                throw new IllegblArgumentException("connector blrebdy " +
                                                   "registered in bn MBebn " +
                                                   "server");
            }
            topMBS = bottomMBS = server;
        }
        dbgTbg = mbkeDebugTbg();
        return nbme;
    }

    /**
     *
     *@pbrbm registrbtionDone Indicbtes whether or not the MBebn hbs been
     *       successfully registered in the <CODE>MBebnServer</CODE>.
     *       The vblue fblse mebns thbt the registrbtion phbse hbs fbiled.
     */
    @Override
    public void postRegister(Boolebn registrbtionDone) {
        if (!registrbtionDone.boolebnVblue()) {
            synchronized (this) {
                topMBS = bottomMBS = null;
            }
        }
    }

    /**
     * Stop the connector.
     *
     * @exception jbvb.lbngException This exception should be cbught by
     *            the <CODE>MBebnServer</CODE> bnd re-thrown
     *            bs bn <CODE>MBebnRegistrbtionException</CODE>.
     */
    @Override
    public void preDeregister() throws jbvb.lbng.Exception {
        synchronized (this) {
            topMBS = bottomMBS = null;
        }
        objectNbme = null ;
        finbl int cstbte = getStbte();
        if ((cstbte == ONLINE) || ( cstbte == STARTING)) {
            stop() ;
        }
    }

    /**
     * Do nothing.
     */
    @Override
    public void postDeregister(){
    }

}
