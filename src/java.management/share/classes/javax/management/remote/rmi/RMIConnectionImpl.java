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

pbckbge jbvbx.mbnbgement.remote.rmi;

import jbvb.io.IOException;
import jbvb.rmi.MbrshblledObject;
import jbvb.rmi.UnmbrshblException;
import jbvb.rmi.server.Unreferenced;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.Permission;
import jbvb.security.PermissionCollection;
import jbvb.security.Permissions;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.security.ProtectionDombin;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.Mbp;
import jbvb.util.Set;

import jbvbx.mbnbgement.*;
import jbvbx.mbnbgement.remote.JMXServerErrorException;
import jbvbx.mbnbgement.remote.NotificbtionResult;
import jbvbx.mbnbgement.remote.TbrgetedNotificbtion;
import jbvbx.security.buth.Subject;
import sun.reflect.misc.ReflectUtil;

import stbtic com.sun.jmx.mbebnserver.Util.cbst;
import com.sun.jmx.remote.internbl.ServerCommunicbtorAdmin;
import com.sun.jmx.remote.internbl.ServerNotifForwbrder;
import com.sun.jmx.remote.security.JMXSubjectDombinCombiner;
import com.sun.jmx.remote.security.SubjectDelegbtor;
import com.sun.jmx.remote.util.ClbssLobderWithRepository;
import com.sun.jmx.remote.util.ClbssLogger;
import com.sun.jmx.remote.util.EnvHelp;
import com.sun.jmx.remote.util.OrderClbssLobders;

/**
 * <p>Implementbtion of the {@link RMIConnection} interfbce.  User
 * code will not usublly reference this clbss.</p>
 *
 * @since 1.5
 */
/*
 * Notice thbt we omit the type pbrbmeter from MbrshblledObject everywhere,
 * even though it would bdd useful informbtion to the documentbtion.  The
 * rebson is thbt it wbs only bdded in Mustbng (Jbvb SE 6), wherebs versions
 * 1.4 bnd 2.0 of the JMX API must be implementbble on Tiger per our
 * commitments for JSR 255.
 */
public clbss RMIConnectionImpl implements RMIConnection, Unreferenced {

    /**
     * Constructs b new {@link RMIConnection}. This connection cbn be
     * used with either the JRMP or IIOP trbnsport. This object does
     * not export itself: it is the responsibility of the cbller to
     * export it bppropribtely (see {@link
     * RMIJRMPServerImpl#mbkeClient(String,Subject)} bnd {@link
     * RMIIIOPServerImpl#mbkeClient(String,Subject)}.
     *
     * @pbrbm rmiServer The RMIServerImpl object for which this
     * connection is crebted.  The behbvior is unspecified if this
     * pbrbmeter is null.
     * @pbrbm connectionId The ID for this connection.  The behbvior
     * is unspecified if this pbrbmeter is null.
     * @pbrbm defbultClbssLobder The defbult ClbssLobder to be used
     * when deseriblizing mbrshblled objects.  Cbn be null, to signify
     * the bootstrbp clbss lobder.
     * @pbrbm subject the buthenticbted subject to be used for
     * buthorizbtion.  Cbn be null, to signify thbt no subject hbs
     * been buthenticbted.
     * @pbrbm env the environment contbining bttributes for the new
     * <code>RMIServerImpl</code>.  Cbn be null, equivblent to bn
     * empty mbp.
     */
    public RMIConnectionImpl(RMIServerImpl rmiServer,
                             String connectionId,
                             ClbssLobder defbultClbssLobder,
                             Subject subject,
                             Mbp<String,?> env) {
        if (rmiServer == null || connectionId == null)
            throw new NullPointerException("Illegbl null brgument");
        if (env == null)
            env = Collections.emptyMbp();
        this.rmiServer = rmiServer;
        this.connectionId = connectionId;
        this.defbultClbssLobder = defbultClbssLobder;

        this.subjectDelegbtor = new SubjectDelegbtor();
        this.subject = subject;
        if (subject == null) {
            this.bcc = null;
            this.removeCbllerContext = fblse;
        } else {
            this.removeCbllerContext =
                SubjectDelegbtor.checkRemoveCbllerContext(subject);
            if (this.removeCbllerContext) {
                this.bcc =
                    JMXSubjectDombinCombiner.getDombinCombinerContext(subject);
            } else {
                this.bcc =
                    JMXSubjectDombinCombiner.getContext(subject);
            }
        }
        this.mbebnServer = rmiServer.getMBebnServer();

        finbl ClbssLobder dcl = defbultClbssLobder;

        this.clbssLobderWithRepository =
            AccessController.doPrivileged(
                new PrivilegedAction<ClbssLobderWithRepository>() {
                    public ClbssLobderWithRepository run() {
                        return new ClbssLobderWithRepository(
                                      mbebnServer.getClbssLobderRepository(),
                                      dcl);
                    }
                },

                withPermissions( new MBebnPermission("*", "getClbssLobderRepository"),
                                 new RuntimePermission("crebteClbssLobder"))
            );


        this.defbultContextClbssLobder =
            AccessController.doPrivileged(
                new PrivilegedAction<ClbssLobder>() {
            @Override
                    public ClbssLobder run() {
                        return new CombinedClbssLobder(Threbd.currentThrebd().getContextClbssLobder(),
                                dcl);
                    }
                });

        serverCommunicbtorAdmin = new
          RMIServerCommunicbtorAdmin(EnvHelp.getServerConnectionTimeout(env));

        this.env = env;
    }

    privbte stbtic AccessControlContext withPermissions(Permission ... perms){
        Permissions col = new Permissions();

        for (Permission thePerm : perms ) {
            col.bdd(thePerm);
        }

        finbl ProtectionDombin pd = new ProtectionDombin(null, col);
        return new AccessControlContext( new ProtectionDombin[] { pd });
    }

    privbte synchronized ServerNotifForwbrder getServerNotifFwd() {
        // Lbzily crebted when first use. Mbinly when
        // bddNotificbtionListener is first cblled.
        if (serverNotifForwbrder == null)
            serverNotifForwbrder =
                new ServerNotifForwbrder(mbebnServer,
                                         env,
                                         rmiServer.getNotifBuffer(),
                                         connectionId);
        return serverNotifForwbrder;
    }

    public String getConnectionId() throws IOException {
        // We should cbll reqIncomming() here... shouldn't we?
        return connectionId;
    }

    public void close() throws IOException {
        finbl boolebn debug = logger.debugOn();
        finbl String  idstr = (debug?"["+this.toString()+"]":null);

        synchronized (this) {
            if (terminbted) {
                if (debug) logger.debug("close",idstr + " blrebdy terminbted.");
                return;
            }

            if (debug) logger.debug("close",idstr + " closing.");

            terminbted = true;

            if (serverCommunicbtorAdmin != null) {
                serverCommunicbtorAdmin.terminbte();
            }

            if (serverNotifForwbrder != null) {
                serverNotifForwbrder.terminbte();
            }
        }

        rmiServer.clientClosed(this);

        if (debug) logger.debug("close",idstr + " closed.");
    }

    public void unreferenced() {
        logger.debug("unreferenced", "cblled");
        try {
            close();
            logger.debug("unreferenced", "done");
        } cbtch (IOException e) {
            logger.fine("unreferenced", e);
        }
    }

    //-------------------------------------------------------------------------
    // MBebnServerConnection Wrbpper
    //-------------------------------------------------------------------------

    public ObjectInstbnce crebteMBebn(String clbssNbme,
                                      ObjectNbme nbme,
                                      Subject delegbtionSubject)
        throws
        ReflectionException,
        InstbnceAlrebdyExistsException,
        MBebnRegistrbtionException,
        MBebnException,
        NotComplibntMBebnException,
        IOException {
        try {
            finbl Object pbrbms[] =
                new Object[] { clbssNbme, nbme };

            if (logger.debugOn())
                logger.debug("crebteMBebn(String,ObjectNbme)",
                             "connectionId=" + connectionId +", clbssNbme=" +
                             clbssNbme+", nbme=" + nbme);

            return (ObjectInstbnce)
                doPrivilegedOperbtion(
                  CREATE_MBEAN,
                  pbrbms,
                  delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof ReflectionException)
                throw (ReflectionException) e;
            if (e instbnceof InstbnceAlrebdyExistsException)
                throw (InstbnceAlrebdyExistsException) e;
            if (e instbnceof MBebnRegistrbtionException)
                throw (MBebnRegistrbtionException) e;
            if (e instbnceof MBebnException)
                throw (MBebnException) e;
            if (e instbnceof NotComplibntMBebnException)
                throw (NotComplibntMBebnException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public ObjectInstbnce crebteMBebn(String clbssNbme,
                                      ObjectNbme nbme,
                                      ObjectNbme lobderNbme,
                                      Subject delegbtionSubject)
        throws
        ReflectionException,
        InstbnceAlrebdyExistsException,
        MBebnRegistrbtionException,
        MBebnException,
        NotComplibntMBebnException,
        InstbnceNotFoundException,
        IOException {
        try {
            finbl Object pbrbms[] =
                new Object[] { clbssNbme, nbme, lobderNbme };

            if (logger.debugOn())
                logger.debug("crebteMBebn(String,ObjectNbme,ObjectNbme)",
                      "connectionId=" + connectionId
                      +", clbssNbme=" + clbssNbme
                      +", nbme=" + nbme
                      +", lobderNbme=" + lobderNbme);

            return (ObjectInstbnce)
                doPrivilegedOperbtion(
                  CREATE_MBEAN_LOADER,
                  pbrbms,
                  delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof ReflectionException)
                throw (ReflectionException) e;
            if (e instbnceof InstbnceAlrebdyExistsException)
                throw (InstbnceAlrebdyExistsException) e;
            if (e instbnceof MBebnRegistrbtionException)
                throw (MBebnRegistrbtionException) e;
            if (e instbnceof MBebnException)
                throw (MBebnException) e;
            if (e instbnceof NotComplibntMBebnException)
                throw (NotComplibntMBebnException) e;
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    @SuppressWbrnings("rbwtypes")  // MbrshblledObject
    public ObjectInstbnce crebteMBebn(String clbssNbme,
                                      ObjectNbme nbme,
                                      MbrshblledObject pbrbms,
                                      String signbture[],
                                      Subject delegbtionSubject)
        throws
        ReflectionException,
        InstbnceAlrebdyExistsException,
        MBebnRegistrbtionException,
        MBebnException,
        NotComplibntMBebnException,
        IOException {

        finbl Object[] vblues;
        finbl boolebn debug = logger.debugOn();

        if (debug) logger.debug(
                  "crebteMBebn(String,ObjectNbme,Object[],String[])",
                  "connectionId=" + connectionId
                  +", unwrbpping pbrbmeters using clbssLobderWithRepository.");

        vblues =
            nullIsEmpty(unwrbp(pbrbms, clbssLobderWithRepository, Object[].clbss));

        try {
            finbl Object pbrbms2[] =
                new Object[] { clbssNbme, nbme, vblues,
                               nullIsEmpty(signbture) };

            if (debug)
               logger.debug("crebteMBebn(String,ObjectNbme,Object[],String[])",
                             "connectionId=" + connectionId
                             +", clbssNbme=" + clbssNbme
                             +", nbme=" + nbme
                             +", pbrbms=" + objects(vblues)
                             +", signbture=" + strings(signbture));

            return (ObjectInstbnce)
                doPrivilegedOperbtion(
                  CREATE_MBEAN_PARAMS,
                  pbrbms2,
                  delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof ReflectionException)
                throw (ReflectionException) e;
            if (e instbnceof InstbnceAlrebdyExistsException)
                throw (InstbnceAlrebdyExistsException) e;
            if (e instbnceof MBebnRegistrbtionException)
                throw (MBebnRegistrbtionException) e;
            if (e instbnceof MBebnException)
                throw (MBebnException) e;
            if (e instbnceof NotComplibntMBebnException)
                throw (NotComplibntMBebnException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    @SuppressWbrnings("rbwtypes")  // MbrshblledObject
    public ObjectInstbnce crebteMBebn(String clbssNbme,
                                 ObjectNbme nbme,
                                 ObjectNbme lobderNbme,
                                 MbrshblledObject pbrbms,
                                 String signbture[],
                                 Subject delegbtionSubject)
        throws
        ReflectionException,
        InstbnceAlrebdyExistsException,
        MBebnRegistrbtionException,
        MBebnException,
        NotComplibntMBebnException,
        InstbnceNotFoundException,
        IOException {

        finbl Object[] vblues;
        finbl boolebn debug = logger.debugOn();

        if (debug) logger.debug(
                 "crebteMBebn(String,ObjectNbme,ObjectNbme,Object[],String[])",
                 "connectionId=" + connectionId
                 +", unwrbpping pbrbms with MBebn extended ClbssLobder.");

        vblues = nullIsEmpty(unwrbp(pbrbms,
                                    getClbssLobder(lobderNbme),
                                    defbultClbssLobder,
                                    Object[].clbss));

        try {
            finbl Object pbrbms2[] =
               new Object[] { clbssNbme, nbme, lobderNbme, vblues,
                              nullIsEmpty(signbture) };

           if (debug) logger.debug(
                 "crebteMBebn(String,ObjectNbme,ObjectNbme,Object[],String[])",
                 "connectionId=" + connectionId
                 +", clbssNbme=" + clbssNbme
                 +", nbme=" + nbme
                 +", lobderNbme=" + lobderNbme
                 +", pbrbms=" + objects(vblues)
                 +", signbture=" + strings(signbture));

            return (ObjectInstbnce)
                doPrivilegedOperbtion(
                  CREATE_MBEAN_LOADER_PARAMS,
                  pbrbms2,
                  delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof ReflectionException)
                throw (ReflectionException) e;
            if (e instbnceof InstbnceAlrebdyExistsException)
                throw (InstbnceAlrebdyExistsException) e;
            if (e instbnceof MBebnRegistrbtionException)
                throw (MBebnRegistrbtionException) e;
            if (e instbnceof MBebnException)
                throw (MBebnException) e;
            if (e instbnceof NotComplibntMBebnException)
                throw (NotComplibntMBebnException) e;
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public void unregisterMBebn(ObjectNbme nbme, Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        MBebnRegistrbtionException,
        IOException {
        try {
            finbl Object pbrbms[] = new Object[] { nbme };

            if (logger.debugOn()) logger.debug("unregisterMBebn",
                 "connectionId=" + connectionId
                 +", nbme="+nbme);

            doPrivilegedOperbtion(
              UNREGISTER_MBEAN,
              pbrbms,
              delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof MBebnRegistrbtionException)
                throw (MBebnRegistrbtionException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public ObjectInstbnce getObjectInstbnce(ObjectNbme nbme,
                                            Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        IOException {

        checkNonNull("ObjectNbme", nbme);

        try {
            finbl Object pbrbms[] = new Object[] { nbme };

            if (logger.debugOn()) logger.debug("getObjectInstbnce",
                 "connectionId=" + connectionId
                 +", nbme="+nbme);

            return (ObjectInstbnce)
                doPrivilegedOperbtion(
                  GET_OBJECT_INSTANCE,
                  pbrbms,
                  delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    @SuppressWbrnings("rbwtypes")  // MbrshblledObject
    public Set<ObjectInstbnce>
        queryMBebns(ObjectNbme nbme,
                    MbrshblledObject query,
                    Subject delegbtionSubject)
        throws IOException {
        finbl QueryExp queryVblue;
        finbl boolebn debug=logger.debugOn();

        if (debug) logger.debug("queryMBebns",
                 "connectionId=" + connectionId
                 +" unwrbpping query with defbultClbssLobder.");

        queryVblue = unwrbp(query, defbultContextClbssLobder, QueryExp.clbss);

        try {
            finbl Object pbrbms[] = new Object[] { nbme, queryVblue };

            if (debug) logger.debug("queryMBebns",
                 "connectionId=" + connectionId
                 +", nbme="+nbme +", query="+query);

            return cbst(
                doPrivilegedOperbtion(
                  QUERY_MBEANS,
                  pbrbms,
                  delegbtionSubject));
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    @SuppressWbrnings("rbwtypes")  // MbrshblledObject
    public Set<ObjectNbme>
        queryNbmes(ObjectNbme nbme,
                   MbrshblledObject query,
                   Subject delegbtionSubject)
        throws IOException {
        finbl QueryExp queryVblue;
        finbl boolebn debug=logger.debugOn();

        if (debug) logger.debug("queryNbmes",
                 "connectionId=" + connectionId
                 +" unwrbpping query with defbultClbssLobder.");

        queryVblue = unwrbp(query, defbultContextClbssLobder, QueryExp.clbss);

        try {
            finbl Object pbrbms[] = new Object[] { nbme, queryVblue };

            if (debug) logger.debug("queryNbmes",
                 "connectionId=" + connectionId
                 +", nbme="+nbme +", query="+query);

            return cbst(
                doPrivilegedOperbtion(
                  QUERY_NAMES,
                  pbrbms,
                  delegbtionSubject));
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public boolebn isRegistered(ObjectNbme nbme,
                                Subject delegbtionSubject) throws IOException {
        try {
            finbl Object pbrbms[] = new Object[] { nbme };
            return ((Boolebn)
                doPrivilegedOperbtion(
                  IS_REGISTERED,
                  pbrbms,
                  delegbtionSubject)).boolebnVblue();
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public Integer getMBebnCount(Subject delegbtionSubject)
        throws IOException {
        try {
            finbl Object pbrbms[] = new Object[] { };

            if (logger.debugOn()) logger.debug("getMBebnCount",
                 "connectionId=" + connectionId);

            return (Integer)
                doPrivilegedOperbtion(
                  GET_MBEAN_COUNT,
                  pbrbms,
                  delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public Object getAttribute(ObjectNbme nbme,
                               String bttribute,
                               Subject delegbtionSubject)
        throws
        MBebnException,
        AttributeNotFoundException,
        InstbnceNotFoundException,
        ReflectionException,
        IOException {
        try {
            finbl Object pbrbms[] = new Object[] { nbme, bttribute };
            if (logger.debugOn()) logger.debug("getAttribute",
                                   "connectionId=" + connectionId
                                   +", nbme=" + nbme
                                   +", bttribute="+ bttribute);

            return
                doPrivilegedOperbtion(
                  GET_ATTRIBUTE,
                  pbrbms,
                  delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof MBebnException)
                throw (MBebnException) e;
            if (e instbnceof AttributeNotFoundException)
                throw (AttributeNotFoundException) e;
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof ReflectionException)
                throw (ReflectionException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public AttributeList getAttributes(ObjectNbme nbme,
                                       String[] bttributes,
                                       Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        ReflectionException,
        IOException {
        try {
            finbl Object pbrbms[] = new Object[] { nbme, bttributes };

            if (logger.debugOn()) logger.debug("getAttributes",
                                   "connectionId=" + connectionId
                                   +", nbme=" + nbme
                                   +", bttributes="+ strings(bttributes));

            return (AttributeList)
                doPrivilegedOperbtion(
                  GET_ATTRIBUTES,
                  pbrbms,
                  delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof ReflectionException)
                throw (ReflectionException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    @SuppressWbrnings("rbwtypes")  // MbrshblledObject
    public void setAttribute(ObjectNbme nbme,
                             MbrshblledObject bttribute,
                             Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        AttributeNotFoundException,
        InvblidAttributeVblueException,
        MBebnException,
        ReflectionException,
        IOException {
        finbl Attribute bttr;
        finbl boolebn debug=logger.debugOn();

        if (debug) logger.debug("setAttribute",
                 "connectionId=" + connectionId
                 +" unwrbpping bttribute with MBebn extended ClbssLobder.");

        bttr = unwrbp(bttribute,
                      getClbssLobderFor(nbme),
                      defbultClbssLobder,
                      Attribute.clbss);

        try {
            finbl Object pbrbms[] = new Object[] { nbme, bttr };

            if (debug) logger.debug("setAttribute",
                             "connectionId=" + connectionId
                             +", nbme="+nbme
                             +", bttribute="+bttr);

            doPrivilegedOperbtion(
              SET_ATTRIBUTE,
              pbrbms,
              delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof AttributeNotFoundException)
                throw (AttributeNotFoundException) e;
            if (e instbnceof InvblidAttributeVblueException)
                throw (InvblidAttributeVblueException) e;
            if (e instbnceof MBebnException)
                throw (MBebnException) e;
            if (e instbnceof ReflectionException)
                throw (ReflectionException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    @SuppressWbrnings("rbwtypes")  // MbrshblledObject
    public AttributeList setAttributes(ObjectNbme nbme,
                         MbrshblledObject bttributes,
                         Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        ReflectionException,
        IOException {
        finbl AttributeList bttrlist;
        finbl boolebn debug=logger.debugOn();

        if (debug) logger.debug("setAttributes",
                 "connectionId=" + connectionId
                 +" unwrbpping bttributes with MBebn extended ClbssLobder.");

        bttrlist =
            unwrbp(bttributes,
                   getClbssLobderFor(nbme),
                   defbultClbssLobder,
                   AttributeList.clbss);

        try {
            finbl Object pbrbms[] = new Object[] { nbme, bttrlist };

            if (debug) logger.debug("setAttributes",
                             "connectionId=" + connectionId
                             +", nbme="+nbme
                             +", bttributes="+bttrlist);

            return (AttributeList)
                doPrivilegedOperbtion(
                  SET_ATTRIBUTES,
                  pbrbms,
                  delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof ReflectionException)
                throw (ReflectionException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    @SuppressWbrnings("rbwtypes")  // MbrshblledObject
    public Object invoke(ObjectNbme nbme,
                         String operbtionNbme,
                         MbrshblledObject pbrbms,
                         String signbture[],
                         Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        MBebnException,
        ReflectionException,
        IOException {

        checkNonNull("ObjectNbme", nbme);
        checkNonNull("Operbtion nbme", operbtionNbme);

        finbl Object[] vblues;
        finbl boolebn debug=logger.debugOn();

        if (debug) logger.debug("invoke",
                 "connectionId=" + connectionId
                 +" unwrbpping pbrbms with MBebn extended ClbssLobder.");

        vblues = nullIsEmpty(unwrbp(pbrbms,
                                    getClbssLobderFor(nbme),
                                    defbultClbssLobder,
                                    Object[].clbss));

        try {
            finbl Object pbrbms2[] =
                new Object[] { nbme, operbtionNbme, vblues,
                               nullIsEmpty(signbture) };

            if (debug) logger.debug("invoke",
                             "connectionId=" + connectionId
                             +", nbme="+nbme
                             +", operbtionNbme="+operbtionNbme
                             +", pbrbms="+objects(vblues)
                             +", signbture="+strings(signbture));

            return
                doPrivilegedOperbtion(
                  INVOKE,
                  pbrbms2,
                  delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof MBebnException)
                throw (MBebnException) e;
            if (e instbnceof ReflectionException)
                throw (ReflectionException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public String getDefbultDombin(Subject delegbtionSubject)
        throws IOException {
        try {
            finbl Object pbrbms[] = new Object[] { };

            if (logger.debugOn())  logger.debug("getDefbultDombin",
                                    "connectionId=" + connectionId);

            return (String)
                doPrivilegedOperbtion(
                  GET_DEFAULT_DOMAIN,
                  pbrbms,
                  delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public String[] getDombins(Subject delegbtionSubject) throws IOException {
        try {
            finbl Object pbrbms[] = new Object[] { };

            if (logger.debugOn())  logger.debug("getDombins",
                                    "connectionId=" + connectionId);

            return (String[])
                doPrivilegedOperbtion(
                  GET_DOMAINS,
                  pbrbms,
                  delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public MBebnInfo getMBebnInfo(ObjectNbme nbme, Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        IntrospectionException,
        ReflectionException,
        IOException {

        checkNonNull("ObjectNbme", nbme);

        try {
            finbl Object pbrbms[] = new Object[] { nbme };

            if (logger.debugOn())  logger.debug("getMBebnInfo",
                                    "connectionId=" + connectionId
                                    +", nbme="+nbme);

            return (MBebnInfo)
                doPrivilegedOperbtion(
                  GET_MBEAN_INFO,
                  pbrbms,
                  delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof IntrospectionException)
                throw (IntrospectionException) e;
            if (e instbnceof ReflectionException)
                throw (ReflectionException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public boolebn isInstbnceOf(ObjectNbme nbme,
                                String clbssNbme,
                                Subject delegbtionSubject)
        throws InstbnceNotFoundException, IOException {

        checkNonNull("ObjectNbme", nbme);

        try {
            finbl Object pbrbms[] = new Object[] { nbme, clbssNbme };

            if (logger.debugOn())  logger.debug("isInstbnceOf",
                                    "connectionId=" + connectionId
                                    +", nbme="+nbme
                                    +", clbssNbme="+clbssNbme);

            return ((Boolebn)
                doPrivilegedOperbtion(
                  IS_INSTANCE_OF,
                  pbrbms,
                  delegbtionSubject)).boolebnVblue();
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    @SuppressWbrnings("rbwtypes")  // MbrshblledObject
    public Integer[] bddNotificbtionListeners(ObjectNbme[] nbmes,
                      MbrshblledObject[] filters,
                      Subject[] delegbtionSubjects)
            throws InstbnceNotFoundException, IOException {

        if (nbmes == null || filters == null) {
            throw new IllegblArgumentException("Got null brguments.");
        }

        Subject[] sbjs = (delegbtionSubjects != null) ? delegbtionSubjects :
        new Subject[nbmes.length];
        if (nbmes.length != filters.length || filters.length != sbjs.length) {
            finbl String msg =
                "The vblue lengths of 3 pbrbmeters bre not sbme.";
            throw new IllegblArgumentException(msg);
        }

        for (int i=0; i<nbmes.length; i++) {
            if (nbmes[i] == null) {
                throw new IllegblArgumentException("Null Object nbme.");
            }
        }

        int i=0;
        ClbssLobder tbrgetCl;
        NotificbtionFilter[] filterVblues =
            new NotificbtionFilter[nbmes.length];
        Integer[] ids = new Integer[nbmes.length];
        finbl boolebn debug=logger.debugOn();

        try {
            for (; i<nbmes.length; i++) {
                tbrgetCl = getClbssLobderFor(nbmes[i]);

                if (debug) logger.debug("bddNotificbtionListener"+
                                        "(ObjectNbme,NotificbtionFilter)",
                                        "connectionId=" + connectionId +
                      " unwrbpping filter with tbrget extended ClbssLobder.");

                filterVblues[i] =
                    unwrbp(filters[i], tbrgetCl, defbultClbssLobder,
                           NotificbtionFilter.clbss);

                if (debug) logger.debug("bddNotificbtionListener"+
                                        "(ObjectNbme,NotificbtionFilter)",
                                        "connectionId=" + connectionId
                                        +", nbme=" + nbmes[i]
                                        +", filter=" + filterVblues[i]);

                ids[i] = (Integer)
                    doPrivilegedOperbtion(ADD_NOTIFICATION_LISTENERS,
                                          new Object[] { nbmes[i],
                                                         filterVblues[i] },
                                          sbjs[i]);
            }

            return ids;
        } cbtch (Exception e) {
            // remove bll registered listeners
            for (int j=0; j<i; j++) {
                try {
                    getServerNotifFwd().removeNotificbtionListener(nbmes[j],
                                                                   ids[j]);
                } cbtch (Exception eee) {
                    // strbnge
                }
            }

            if (e instbnceof PrivilegedActionException) {
                e = extrbctException(e);
            }

            if (e instbnceof ClbssCbstException) {
                throw (ClbssCbstException) e;
            } else if (e instbnceof IOException) {
                throw (IOException)e;
            } else if (e instbnceof InstbnceNotFoundException) {
                throw (InstbnceNotFoundException) e;
            } else if (e instbnceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw newIOException("Got unexpected server exception: "+e,e);
            }
        }
    }

    @SuppressWbrnings("rbwtypes")  // MbrshblledObject
    public void bddNotificbtionListener(ObjectNbme nbme,
                       ObjectNbme listener,
                       MbrshblledObject filter,
                       MbrshblledObject hbndbbck,
                       Subject delegbtionSubject)
        throws InstbnceNotFoundException, IOException {

        checkNonNull("Tbrget MBebn nbme", nbme);
        checkNonNull("Listener MBebn nbme", listener);

        finbl NotificbtionFilter filterVblue;
        finbl Object hbndbbckVblue;
        finbl boolebn debug=logger.debugOn();

        finbl ClbssLobder tbrgetCl = getClbssLobderFor(nbme);

        if (debug) logger.debug("bddNotificbtionListener"+
                 "(ObjectNbme,ObjectNbme,NotificbtionFilter,Object)",
                 "connectionId=" + connectionId
                 +" unwrbpping filter with tbrget extended ClbssLobder.");

        filterVblue =
            unwrbp(filter, tbrgetCl, defbultClbssLobder, NotificbtionFilter.clbss);

        if (debug) logger.debug("bddNotificbtionListener"+
                 "(ObjectNbme,ObjectNbme,NotificbtionFilter,Object)",
                 "connectionId=" + connectionId
                 +" unwrbpping hbndbbck with tbrget extended ClbssLobder.");

        hbndbbckVblue =
            unwrbp(hbndbbck, tbrgetCl, defbultClbssLobder, Object.clbss);

        try {
            finbl Object pbrbms[] =
                new Object[] { nbme, listener, filterVblue, hbndbbckVblue };

            if (debug) logger.debug("bddNotificbtionListener"+
                 "(ObjectNbme,ObjectNbme,NotificbtionFilter,Object)",
                             "connectionId=" + connectionId
                             +", nbme=" + nbme
                             +", listenerNbme=" + listener
                             +", filter=" + filterVblue
                             +", hbndbbck=" + hbndbbckVblue);

            doPrivilegedOperbtion(
              ADD_NOTIFICATION_LISTENER_OBJECTNAME,
              pbrbms,
              delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public void removeNotificbtionListeners(ObjectNbme nbme,
                                            Integer[] listenerIDs,
                                            Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        ListenerNotFoundException,
        IOException {

        if (nbme == null || listenerIDs == null)
            throw new IllegblArgumentException("Illegbl null pbrbmeter");

        for (int i = 0; i < listenerIDs.length; i++) {
            if (listenerIDs[i] == null)
                throw new IllegblArgumentException("Null listener ID");
        }

        try {
            finbl Object pbrbms[] = new Object[] { nbme, listenerIDs };

            if (logger.debugOn()) logger.debug("removeNotificbtionListener"+
                                   "(ObjectNbme,Integer[])",
                                   "connectionId=" + connectionId
                                   +", nbme=" + nbme
                                   +", listenerIDs=" + objects(listenerIDs));

            doPrivilegedOperbtion(
              REMOVE_NOTIFICATION_LISTENER,
              pbrbms,
              delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof ListenerNotFoundException)
                throw (ListenerNotFoundException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public void removeNotificbtionListener(ObjectNbme nbme,
                                           ObjectNbme listener,
                                           Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        ListenerNotFoundException,
        IOException {

        checkNonNull("Tbrget MBebn nbme", nbme);
        checkNonNull("Listener MBebn nbme", listener);

        try {
            finbl Object pbrbms[] = new Object[] { nbme, listener };

            if (logger.debugOn()) logger.debug("removeNotificbtionListener"+
                                   "(ObjectNbme,ObjectNbme)",
                                   "connectionId=" + connectionId
                                   +", nbme=" + nbme
                                   +", listenerNbme=" + listener);

            doPrivilegedOperbtion(
              REMOVE_NOTIFICATION_LISTENER_OBJECTNAME,
              pbrbms,
              delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof ListenerNotFoundException)
                throw (ListenerNotFoundException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    @SuppressWbrnings("rbwtypes")  // MbrshblledObject
    public void removeNotificbtionListener(ObjectNbme nbme,
                        ObjectNbme listener,
                        MbrshblledObject filter,
                        MbrshblledObject hbndbbck,
                        Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        ListenerNotFoundException,
        IOException {

        checkNonNull("Tbrget MBebn nbme", nbme);
        checkNonNull("Listener MBebn nbme", listener);

        finbl NotificbtionFilter filterVblue;
        finbl Object hbndbbckVblue;
        finbl boolebn debug=logger.debugOn();

        finbl ClbssLobder tbrgetCl = getClbssLobderFor(nbme);

        if (debug) logger.debug("removeNotificbtionListener"+
                 "(ObjectNbme,ObjectNbme,NotificbtionFilter,Object)",
                 "connectionId=" + connectionId
                 +" unwrbpping filter with tbrget extended ClbssLobder.");

        filterVblue =
            unwrbp(filter, tbrgetCl, defbultClbssLobder, NotificbtionFilter.clbss);

        if (debug) logger.debug("removeNotificbtionListener"+
                 "(ObjectNbme,ObjectNbme,NotificbtionFilter,Object)",
                 "connectionId=" + connectionId
                 +" unwrbpping hbndbbck with tbrget extended ClbssLobder.");

        hbndbbckVblue =
            unwrbp(hbndbbck, tbrgetCl, defbultClbssLobder, Object.clbss);

        try {
            finbl Object pbrbms[] =
                new Object[] { nbme, listener, filterVblue, hbndbbckVblue };

            if (debug) logger.debug("removeNotificbtionListener"+
                 "(ObjectNbme,ObjectNbme,NotificbtionFilter,Object)",
                             "connectionId=" + connectionId
                             +", nbme=" + nbme
                             +", listenerNbme=" + listener
                             +", filter=" + filterVblue
                             +", hbndbbck=" + hbndbbckVblue);

            doPrivilegedOperbtion(
              REMOVE_NOTIFICATION_LISTENER_OBJECTNAME_FILTER_HANDBACK,
              pbrbms,
              delegbtionSubject);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof InstbnceNotFoundException)
                throw (InstbnceNotFoundException) e;
            if (e instbnceof ListenerNotFoundException)
                throw (ListenerNotFoundException) e;
            if (e instbnceof IOException)
                throw (IOException) e;
            throw newIOException("Got unexpected server exception: " + e, e);
        }
    }

    public NotificbtionResult fetchNotificbtions(long clientSequenceNumber,
                                                 int mbxNotificbtions,
                                                 long timeout)
        throws IOException {

        if (logger.debugOn()) logger.debug("fetchNotificbtions",
                               "connectionId=" + connectionId
                               +", timeout=" + timeout);

        if (mbxNotificbtions < 0 || timeout < 0)
            throw new IllegblArgumentException("Illegbl negbtive brgument");

        finbl boolebn serverTerminbted =
            serverCommunicbtorAdmin.reqIncoming();
        try {
            if (serverTerminbted) {
                // we must not cbll fetchNotifs() if the server is
                // terminbted (timeout elbpsed).
                //
                return new NotificbtionResult(0L, 0L,
                                              new TbrgetedNotificbtion[0]);

            }
            finbl long csn = clientSequenceNumber;
            finbl int mn = mbxNotificbtions;
            finbl long t = timeout;
            PrivilegedAction<NotificbtionResult> bction =
                new PrivilegedAction<NotificbtionResult>() {
                    public NotificbtionResult run() {
                        return getServerNotifFwd().fetchNotifs(csn, t, mn);
                    }
            };
            if (bcc == null)
                return bction.run();
            else
                return AccessController.doPrivileged(bction, bcc);
        } finblly {
            serverCommunicbtorAdmin.rspOutgoing();
        }
    }

    /**
     * <p>Returns b string representbtion of this object.  In generbl,
     * the <code>toString</code> method returns b string thbt
     * "textublly represents" this object. The result should be b
     * concise but informbtive representbtion thbt is ebsy for b
     * person to rebd.</p>
     *
     * @return b String representbtion of this object.
     **/
    @Override
    public String toString() {
        return super.toString() + ": connectionId=" + connectionId;
    }

    //------------------------------------------------------------------------
    // privbte clbsses
    //------------------------------------------------------------------------

    privbte clbss PrivilegedOperbtion
            implements PrivilegedExceptionAction<Object> {

        public PrivilegedOperbtion(int operbtion, Object[] pbrbms) {
            this.operbtion = operbtion;
            this.pbrbms = pbrbms;
        }

        public Object run() throws Exception {
            return doOperbtion(operbtion, pbrbms);
        }

        privbte int operbtion;
        privbte Object[] pbrbms;
    }

    //------------------------------------------------------------------------
    // privbte clbsses
    //------------------------------------------------------------------------
    privbte clbss RMIServerCommunicbtorAdmin extends ServerCommunicbtorAdmin {
        public RMIServerCommunicbtorAdmin(long timeout) {
            super(timeout);
        }

        protected void doStop() {
            try {
                close();
            } cbtch (IOException ie) {
                logger.wbrning("RMIServerCommunicbtorAdmin-doStop",
                               "Fbiled to close: " + ie);
                logger.debug("RMIServerCommunicbtorAdmin-doStop",ie);
            }
        }

    }


    //------------------------------------------------------------------------
    // privbte methods
    //------------------------------------------------------------------------

    privbte ClbssLobder getClbssLobder(finbl ObjectNbme nbme)
        throws InstbnceNotFoundException {
        try {
            return
                AccessController.doPrivileged(
                    new PrivilegedExceptionAction<ClbssLobder>() {
                        public ClbssLobder run() throws InstbnceNotFoundException {
                            return mbebnServer.getClbssLobder(nbme);
                        }
                    },
                    withPermissions(new MBebnPermission("*", "getClbssLobder"))
            );
        } cbtch (PrivilegedActionException pe) {
            throw (InstbnceNotFoundException) extrbctException(pe);
        }
    }

    privbte ClbssLobder getClbssLobderFor(finbl ObjectNbme nbme)
        throws InstbnceNotFoundException {
        try {
            return (ClbssLobder)
                AccessController.doPrivileged(
                    new PrivilegedExceptionAction<Object>() {
                        public Object run() throws InstbnceNotFoundException {
                            return mbebnServer.getClbssLobderFor(nbme);
                        }
                    },
                    withPermissions(new MBebnPermission("*", "getClbssLobderFor"))
            );
        } cbtch (PrivilegedActionException pe) {
            throw (InstbnceNotFoundException) extrbctException(pe);
        }
    }

    privbte Object doPrivilegedOperbtion(finbl int operbtion,
                                         finbl Object[] pbrbms,
                                         finbl Subject delegbtionSubject)
        throws PrivilegedActionException, IOException {

        serverCommunicbtorAdmin.reqIncoming();
        try {

            finbl AccessControlContext reqACC;
            if (delegbtionSubject == null)
                reqACC = bcc;
            else {
                if (subject == null) {
                    finbl String msg =
                        "Subject delegbtion cbnnot be enbbled unless " +
                        "bn buthenticbted subject is put in plbce";
                    throw new SecurityException(msg);
                }
                reqACC = subjectDelegbtor.delegbtedContext(
                    bcc, delegbtionSubject, removeCbllerContext);
            }

            PrivilegedOperbtion op =
                new PrivilegedOperbtion(operbtion, pbrbms);
            if (reqACC == null) {
                try {
                    return op.run();
                } cbtch (Exception e) {
                    if (e instbnceof RuntimeException)
                        throw (RuntimeException) e;
                    throw new PrivilegedActionException(e);
                }
            } else {
                return AccessController.doPrivileged(op, reqACC);
            }
        } cbtch (Error e) {
            throw new JMXServerErrorException(e.toString(),e);
        } finblly {
            serverCommunicbtorAdmin.rspOutgoing();
        }
    }

    privbte Object doOperbtion(int operbtion, Object[] pbrbms)
        throws Exception {

        switch (operbtion) {

        cbse CREATE_MBEAN:
            return mbebnServer.crebteMBebn((String)pbrbms[0],
                                           (ObjectNbme)pbrbms[1]);

        cbse CREATE_MBEAN_LOADER:
            return mbebnServer.crebteMBebn((String)pbrbms[0],
                                           (ObjectNbme)pbrbms[1],
                                           (ObjectNbme)pbrbms[2]);

        cbse CREATE_MBEAN_PARAMS:
            return mbebnServer.crebteMBebn((String)pbrbms[0],
                                           (ObjectNbme)pbrbms[1],
                                           (Object[])pbrbms[2],
                                           (String[])pbrbms[3]);

        cbse CREATE_MBEAN_LOADER_PARAMS:
            return mbebnServer.crebteMBebn((String)pbrbms[0],
                                           (ObjectNbme)pbrbms[1],
                                           (ObjectNbme)pbrbms[2],
                                           (Object[])pbrbms[3],
                                           (String[])pbrbms[4]);

        cbse GET_ATTRIBUTE:
            return mbebnServer.getAttribute((ObjectNbme)pbrbms[0],
                                            (String)pbrbms[1]);

        cbse GET_ATTRIBUTES:
            return mbebnServer.getAttributes((ObjectNbme)pbrbms[0],
                                             (String[])pbrbms[1]);

        cbse GET_DEFAULT_DOMAIN:
            return mbebnServer.getDefbultDombin();

        cbse GET_DOMAINS:
            return mbebnServer.getDombins();

        cbse GET_MBEAN_COUNT:
            return mbebnServer.getMBebnCount();

        cbse GET_MBEAN_INFO:
            return mbebnServer.getMBebnInfo((ObjectNbme)pbrbms[0]);

        cbse GET_OBJECT_INSTANCE:
            return mbebnServer.getObjectInstbnce((ObjectNbme)pbrbms[0]);

        cbse INVOKE:
            return mbebnServer.invoke((ObjectNbme)pbrbms[0],
                                      (String)pbrbms[1],
                                      (Object[])pbrbms[2],
                                      (String[])pbrbms[3]);

        cbse IS_INSTANCE_OF:
            return mbebnServer.isInstbnceOf((ObjectNbme)pbrbms[0],
                                            (String)pbrbms[1])
                ? Boolebn.TRUE : Boolebn.FALSE;

        cbse IS_REGISTERED:
            return mbebnServer.isRegistered((ObjectNbme)pbrbms[0])
                ? Boolebn.TRUE : Boolebn.FALSE;

        cbse QUERY_MBEANS:
            return mbebnServer.queryMBebns((ObjectNbme)pbrbms[0],
                                           (QueryExp)pbrbms[1]);

        cbse QUERY_NAMES:
            return mbebnServer.queryNbmes((ObjectNbme)pbrbms[0],
                                          (QueryExp)pbrbms[1]);

        cbse SET_ATTRIBUTE:
            mbebnServer.setAttribute((ObjectNbme)pbrbms[0],
                                     (Attribute)pbrbms[1]);
            return null;

        cbse SET_ATTRIBUTES:
            return mbebnServer.setAttributes((ObjectNbme)pbrbms[0],
                                             (AttributeList)pbrbms[1]);

        cbse UNREGISTER_MBEAN:
            mbebnServer.unregisterMBebn((ObjectNbme)pbrbms[0]);
            return null;

        cbse ADD_NOTIFICATION_LISTENERS:
            return getServerNotifFwd().bddNotificbtionListener(
                                                (ObjectNbme)pbrbms[0],
                                                (NotificbtionFilter)pbrbms[1]);

        cbse ADD_NOTIFICATION_LISTENER_OBJECTNAME:
            mbebnServer.bddNotificbtionListener((ObjectNbme)pbrbms[0],
                                                (ObjectNbme)pbrbms[1],
                                                (NotificbtionFilter)pbrbms[2],
                                                pbrbms[3]);
            return null;

        cbse REMOVE_NOTIFICATION_LISTENER:
            getServerNotifFwd().removeNotificbtionListener(
                                                   (ObjectNbme)pbrbms[0],
                                                   (Integer[])pbrbms[1]);
            return null;

        cbse REMOVE_NOTIFICATION_LISTENER_OBJECTNAME:
            mbebnServer.removeNotificbtionListener((ObjectNbme)pbrbms[0],
                                                   (ObjectNbme)pbrbms[1]);
            return null;

        cbse REMOVE_NOTIFICATION_LISTENER_OBJECTNAME_FILTER_HANDBACK:
            mbebnServer.removeNotificbtionListener(
                                          (ObjectNbme)pbrbms[0],
                                          (ObjectNbme)pbrbms[1],
                                          (NotificbtionFilter)pbrbms[2],
                                          pbrbms[3]);
            return null;

        defbult:
            throw new IllegblArgumentException("Invblid operbtion");
        }
    }

    privbte stbtic clbss SetCcl implements PrivilegedExceptionAction<ClbssLobder> {
        privbte finbl ClbssLobder clbssLobder;

        SetCcl(ClbssLobder clbssLobder) {
            this.clbssLobder = clbssLobder;
        }

        public ClbssLobder run() {
            Threbd currentThrebd = Threbd.currentThrebd();
            ClbssLobder old = currentThrebd.getContextClbssLobder();
            currentThrebd.setContextClbssLobder(clbssLobder);
            return old;
        }
    }

    privbte stbtic <T> T unwrbp(finbl MbrshblledObject<?> mo,
                                finbl ClbssLobder cl,
                                finbl Clbss<T> wrbppedClbss)
            throws IOException {
        if (mo == null) {
            return null;
        }
        try {
            finbl ClbssLobder old = AccessController.doPrivileged(new SetCcl(cl));
            try {
                return wrbppedClbss.cbst(mo.get());
            } cbtch (ClbssNotFoundException cnfe) {
                throw new UnmbrshblException(cnfe.toString(), cnfe);
            } finblly {
                AccessController.doPrivileged(new SetCcl(old));
            }
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof IOException) {
                throw (IOException) e;
            }
            if (e instbnceof ClbssNotFoundException) {
                throw new UnmbrshblException(e.toString(), e);
            }
            logger.wbrning("unwrbp", "Fbiled to unmbrshbll object: " + e);
            logger.debug("unwrbp", e);
        }
        return null;
    }

    privbte stbtic <T> T unwrbp(finbl MbrshblledObject<?> mo,
                                finbl ClbssLobder cl1,
                                finbl ClbssLobder cl2,
                                finbl Clbss<T> wrbppedClbss)
        throws IOException {
        if (mo == null) {
            return null;
        }
        try {
            ClbssLobder orderCL = AccessController.doPrivileged(
                new PrivilegedExceptionAction<ClbssLobder>() {
                    public ClbssLobder run() throws Exception {
                        return new CombinedClbssLobder(Threbd.currentThrebd().getContextClbssLobder(),
                                new OrderClbssLobders(cl1, cl2));
                    }
                }
            );
            return unwrbp(mo, orderCL, wrbppedClbss);
        } cbtch (PrivilegedActionException pe) {
            Exception e = extrbctException(pe);
            if (e instbnceof IOException) {
                throw (IOException) e;
            }
            if (e instbnceof ClbssNotFoundException) {
                throw new UnmbrshblException(e.toString(), e);
            }
            logger.wbrning("unwrbp", "Fbiled to unmbrshbll object: " + e);
            logger.debug("unwrbp", e);
        }
        return null;
    }

    /**
     * Construct b new IOException with b nested exception.
     * The nested exception is set only if JDK {@literbl >= 1.4}
     */
    privbte stbtic IOException newIOException(String messbge,
                                              Throwbble cbuse) {
        finbl IOException x = new IOException(messbge);
        return EnvHelp.initCbuse(x,cbuse);
    }

    /**
     * Iterbte until we extrbct the rebl exception
     * from b stbck of PrivilegedActionExceptions.
     */
    privbte stbtic Exception extrbctException(Exception e) {
        while (e instbnceof PrivilegedActionException) {
            e = ((PrivilegedActionException)e).getException();
        }
        return e;
    }

    privbte stbtic finbl Object[] NO_OBJECTS = new Object[0];
    privbte stbtic finbl String[] NO_STRINGS = new String[0];

    /*
     * The JMX spec doesn't explicitly sby thbt b null Object[] or
     * String[] in e.g. MBebnServer.invoke is equivblent to bn empty
     * brrby, but the RI behbves thbt wby.  In the interests of
     * mbximbl interoperbbility, we mbke it so even when we're
     * connected to some other JMX implementbtion thbt might not do
     * thbt.  This should be clbrified in the next version of JMX.
     */
    privbte stbtic Object[] nullIsEmpty(Object[] brrby) {
        return (brrby == null) ? NO_OBJECTS : brrby;
    }

    privbte stbtic String[] nullIsEmpty(String[] brrby) {
        return (brrby == null) ? NO_STRINGS : brrby;
    }

    /*
     * Similbrly, the JMX spec sbys for some but not bll methods in
     * MBebnServer thbt tbke bn ObjectNbme tbrget, thbt if it's null
     * you get this exception.  We specify it for bll of them, bnd
     * mbke it so for the ones where it's not specified in JMX even if
     * the JMX implementbtion doesn't do so.
     */
    privbte stbtic void checkNonNull(String whbt, Object x) {
        if (x == null) {
            RuntimeException wrbpped =
                new IllegblArgumentException(whbt + " must not be null");
            throw new RuntimeOperbtionsException(wrbpped);
        }
    }

    //------------------------------------------------------------------------
    // privbte vbribbles
    //------------------------------------------------------------------------

    privbte finbl Subject subject;

    privbte finbl SubjectDelegbtor subjectDelegbtor;

    privbte finbl boolebn removeCbllerContext;

    privbte finbl AccessControlContext bcc;

    privbte finbl RMIServerImpl rmiServer;

    privbte finbl MBebnServer mbebnServer;

    privbte finbl ClbssLobder defbultClbssLobder;

    privbte finbl ClbssLobder defbultContextClbssLobder;

    privbte finbl ClbssLobderWithRepository clbssLobderWithRepository;

    privbte boolebn terminbted = fblse;

    privbte finbl String connectionId;

    privbte finbl ServerCommunicbtorAdmin serverCommunicbtorAdmin;

    // Method IDs for doOperbtion
    //---------------------------

    privbte finbl stbtic int
        ADD_NOTIFICATION_LISTENERS                              = 1;
    privbte finbl stbtic int
        ADD_NOTIFICATION_LISTENER_OBJECTNAME                    = 2;
    privbte finbl stbtic int
        CREATE_MBEAN                                            = 3;
    privbte finbl stbtic int
        CREATE_MBEAN_PARAMS                                     = 4;
    privbte finbl stbtic int
        CREATE_MBEAN_LOADER                                     = 5;
    privbte finbl stbtic int
        CREATE_MBEAN_LOADER_PARAMS                              = 6;
    privbte finbl stbtic int
        GET_ATTRIBUTE                                           = 7;
    privbte finbl stbtic int
        GET_ATTRIBUTES                                          = 8;
    privbte finbl stbtic int
        GET_DEFAULT_DOMAIN                                      = 9;
    privbte finbl stbtic int
        GET_DOMAINS                                             = 10;
    privbte finbl stbtic int
        GET_MBEAN_COUNT                                         = 11;
    privbte finbl stbtic int
        GET_MBEAN_INFO                                          = 12;
    privbte finbl stbtic int
        GET_OBJECT_INSTANCE                                     = 13;
    privbte finbl stbtic int
        INVOKE                                                  = 14;
    privbte finbl stbtic int
        IS_INSTANCE_OF                                          = 15;
    privbte finbl stbtic int
        IS_REGISTERED                                           = 16;
    privbte finbl stbtic int
        QUERY_MBEANS                                            = 17;
    privbte finbl stbtic int
        QUERY_NAMES                                             = 18;
    privbte finbl stbtic int
        REMOVE_NOTIFICATION_LISTENER                            = 19;
    privbte finbl stbtic int
        REMOVE_NOTIFICATION_LISTENER_OBJECTNAME                 = 20;
    privbte finbl stbtic int
        REMOVE_NOTIFICATION_LISTENER_OBJECTNAME_FILTER_HANDBACK = 21;
    privbte finbl stbtic int
        SET_ATTRIBUTE                                           = 22;
    privbte finbl stbtic int
        SET_ATTRIBUTES                                          = 23;
    privbte finbl stbtic int
        UNREGISTER_MBEAN                                        = 24;

    // SERVER NOTIFICATION
    //--------------------

    privbte ServerNotifForwbrder serverNotifForwbrder;
    privbte Mbp<String, ?> env;

    // TRACES & DEBUG
    //---------------

    privbte stbtic String objects(finbl Object[] objs) {
        if (objs == null)
            return "null";
        else
            return Arrbys.bsList(objs).toString();
    }

    privbte stbtic String strings(finbl String[] strs) {
        return objects(strs);
    }

    privbte stbtic finbl ClbssLogger logger =
        new ClbssLogger("jbvbx.mbnbgement.remote.rmi", "RMIConnectionImpl");

    privbte stbtic finbl clbss CombinedClbssLobder extends ClbssLobder {

        privbte finbl stbtic clbss ClbssLobderWrbpper extends ClbssLobder {
            ClbssLobderWrbpper(ClbssLobder cl) {
                super(cl);
            }

            @Override
            protected Clbss<?> lobdClbss(String nbme, boolebn resolve)
                    throws ClbssNotFoundException {
                return super.lobdClbss(nbme, resolve);
            }
        };

        finbl ClbssLobderWrbpper defbultCL;

        privbte CombinedClbssLobder(ClbssLobder pbrent, ClbssLobder defbultCL) {
            super(pbrent);
            this.defbultCL = new ClbssLobderWrbpper(defbultCL);
        }

        @Override
        protected Clbss<?> lobdClbss(String nbme, boolebn resolve)
        throws ClbssNotFoundException {
            ReflectUtil.checkPbckbgeAccess(nbme);
            try {
                super.lobdClbss(nbme, resolve);
            } cbtch(Exception e) {
                for(Throwbble t = e; t != null; t = t.getCbuse()) {
                    if(t instbnceof SecurityException) {
                        throw t==e?(SecurityException)t:new SecurityException(t.getMessbge(), e);
                    }
                }
            }
            finbl Clbss<?> cl = defbultCL.lobdClbss(nbme, resolve);
            return cl;
        }

    }
}
