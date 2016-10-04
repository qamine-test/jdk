/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.interceptor;


// JMX RI
import stbtic com.sun.jmx.defbults.JmxProperties.MBEANSERVER_LOGGER;
import com.sun.jmx.mbebnserver.DynbmicMBebn2;
import com.sun.jmx.mbebnserver.Introspector;
import com.sun.jmx.mbebnserver.MBebnInstbntibtor;
import com.sun.jmx.mbebnserver.ModifibbleClbssLobderRepository;
import com.sun.jmx.mbebnserver.NbmedObject;
import com.sun.jmx.mbebnserver.Repository;
import com.sun.jmx.mbebnserver.Repository.RegistrbtionContext;
import com.sun.jmx.mbebnserver.Util;
import com.sun.jmx.remote.util.EnvHelp;

import jbvb.io.ObjectInputStrebm;
import jbvb.lbng.ref.WebkReference;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.Permission;
import jbvb.security.PrivilegedAction;
import jbvb.security.ProtectionDombin;
import jbvb.util.ArrbyList;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Set;
import jbvb.util.WebkHbshMbp;
import jbvb.util.logging.Level;

// JMX import
import jbvbx.mbnbgement.Attribute;
import jbvbx.mbnbgement.AttributeList;
import jbvbx.mbnbgement.AttributeNotFoundException;
import jbvbx.mbnbgement.DynbmicMBebn;
import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.IntrospectionException;
import jbvbx.mbnbgement.InvblidAttributeVblueException;
import jbvbx.mbnbgement.JMRuntimeException;
import jbvbx.mbnbgement.ListenerNotFoundException;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.MBebnPermission;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.MBebnRegistrbtionException;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnServerDelegbte;
import jbvbx.mbnbgement.MBebnServerNotificbtion;
import jbvbx.mbnbgement.MBebnTrustPermission;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionBrobdcbster;
import jbvbx.mbnbgement.NotificbtionEmitter;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.ObjectInstbnce;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.OperbtionsException;
import jbvbx.mbnbgement.QueryEvbl;
import jbvbx.mbnbgement.QueryExp;
import jbvbx.mbnbgement.ReflectionException;
import jbvbx.mbnbgement.RuntimeErrorException;
import jbvbx.mbnbgement.RuntimeMBebnException;
import jbvbx.mbnbgement.RuntimeOperbtionsException;
import jbvbx.mbnbgement.lobding.ClbssLobderRepository;

/**
 * This is the defbult clbss for MBebn mbnipulbtion on the bgent side. It
 * contbins the methods necessbry for the crebtion, registrbtion, bnd
 * deletion of MBebns bs well bs the bccess methods for registered MBebns.
 * This is the core component of the JMX infrbstructure.
 * <P>
 * Every MBebn which is bdded to the MBebn server becomes mbnbgebble: its bttributes bnd operbtions
 * become remotely bccessible through the connectors/bdbptors connected to thbt MBebn server.
 * A Jbvb object cbnnot be registered in the MBebn server unless it is b JMX complibnt MBebn.
 * <P>
 * When bn MBebn is registered or unregistered in the MBebn server bn
 * {@link jbvbx.mbnbgement.MBebnServerNotificbtion MBebnServerNotificbtion}
 * Notificbtion is emitted. To register bn object bs listener to MBebnServerNotificbtions
 * you should cbll the MBebn server method {@link #bddNotificbtionListener bddNotificbtionListener} with <CODE>ObjectNbme</CODE>
 * the <CODE>ObjectNbme</CODE> of the {@link jbvbx.mbnbgement.MBebnServerDelegbte MBebnServerDelegbte}.
 * This <CODE>ObjectNbme</CODE> is:
 * <BR>
 * <CODE>JMImplementbtion:type=MBebnServerDelegbte</CODE>.
 *
 * @since 1.5
 */
public clbss DefbultMBebnServerInterceptor implements MBebnServerInterceptor {

    /** The MBebnInstbntibtor object used by the
     *  DefbultMBebnServerInterceptor */
    privbte finbl trbnsient MBebnInstbntibtor instbntibtor;

    /** The MBebn server object thbt is bssocibted to the
     *  DefbultMBebnServerInterceptor */
    privbte trbnsient MBebnServer server = null;

    /** The MBebn server delegbte object thbt is bssocibted to the
     *  DefbultMBebnServerInterceptor */
    privbte finbl trbnsient MBebnServerDelegbte delegbte;

    /** The Repository object used by the DefbultMBebnServerInterceptor */
    privbte finbl trbnsient Repository repository;

    /** Wrbppers for client listeners.  */
    /* See the comment before bddNotificbtionListener below.  */
    privbte finbl trbnsient
        WebkHbshMbp<ListenerWrbpper, WebkReference<ListenerWrbpper>>
            listenerWrbppers =
                new WebkHbshMbp<ListenerWrbpper,
                                WebkReference<ListenerWrbpper>>();

    /** The defbult dombin of the object nbmes */
    privbte finbl String dombin;

    /** The sequence number identifying the notificbtions sent */
    // Now sequence number is hbndled by MBebnServerDelegbte.
    // privbte int sequenceNumber=0;

    /**
     * Crebtes b DefbultMBebnServerInterceptor with the specified
     * repository instbnce.
     * <p>Do not forget to cbll <code>initiblize(outer,delegbte)</code>
     * before using this object.
     * @pbrbm outer A pointer to the MBebnServer object thbt must be
     *        pbssed to the MBebns when invoking their
     *        {@link jbvbx.mbnbgement.MBebnRegistrbtion} interfbce.
     * @pbrbm delegbte A pointer to the MBebnServerDelegbte bssocibted
     *        with the new MBebnServer. The new MBebnServer must register
     *        this MBebn in its MBebn repository.
     * @pbrbm instbntibtor The MBebnInstbntibtor thbt will be used to
     *        instbntibte MBebns bnd tbke cbre of clbss lobding issues.
     * @pbrbm repository The repository to use for this MBebnServer.
     */
    public DefbultMBebnServerInterceptor(MBebnServer         outer,
                                         MBebnServerDelegbte delegbte,
                                         MBebnInstbntibtor   instbntibtor,
                                         Repository          repository) {
        if (outer == null) throw new
            IllegblArgumentException("outer MBebnServer cbnnot be null");
        if (delegbte == null) throw new
            IllegblArgumentException("MBebnServerDelegbte cbnnot be null");
        if (instbntibtor == null) throw new
            IllegblArgumentException("MBebnInstbntibtor cbnnot be null");
        if (repository == null) throw new
            IllegblArgumentException("Repository cbnnot be null");

        this.server   = outer;
        this.delegbte = delegbte;
        this.instbntibtor = instbntibtor;
        this.repository   = repository;
        this.dombin       = repository.getDefbultDombin();
    }

    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme)
        throws ReflectionException, InstbnceAlrebdyExistsException,
               MBebnRegistrbtionException, MBebnException,
               NotComplibntMBebnException {

        return crebteMBebn(clbssNbme, nbme, (Object[]) null, (String[]) null);

    }

    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                      ObjectNbme lobderNbme)
        throws ReflectionException, InstbnceAlrebdyExistsException,
               MBebnRegistrbtionException, MBebnException,
               NotComplibntMBebnException, InstbnceNotFoundException {

        return crebteMBebn(clbssNbme, nbme, lobderNbme, (Object[]) null,
                           (String[]) null);
    }

    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                      Object[] pbrbms, String[] signbture)
        throws ReflectionException, InstbnceAlrebdyExistsException,
               MBebnRegistrbtionException, MBebnException,
               NotComplibntMBebnException  {

        try {
            return crebteMBebn(clbssNbme, nbme, null, true,
                               pbrbms, signbture);
        } cbtch (InstbnceNotFoundException e) {
            /* Cbn only hbppen if lobderNbme doesn't exist, but we just
               pbssed null, so we shouldn't get this exception.  */
            throw EnvHelp.initCbuse(
                new IllegblArgumentException("Unexpected exception: " + e), e);
        }
    }

    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                      ObjectNbme lobderNbme,
                                      Object[] pbrbms, String[] signbture)
        throws ReflectionException, InstbnceAlrebdyExistsException,
               MBebnRegistrbtionException, MBebnException,
               NotComplibntMBebnException, InstbnceNotFoundException  {

        return crebteMBebn(clbssNbme, nbme, lobderNbme, fblse,
                           pbrbms, signbture);
    }

    privbte ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                       ObjectNbme lobderNbme,
                                       boolebn withDefbultLobderRepository,
                                       Object[] pbrbms, String[] signbture)
        throws ReflectionException, InstbnceAlrebdyExistsException,
               MBebnRegistrbtionException, MBebnException,
               NotComplibntMBebnException, InstbnceNotFoundException {

        Clbss<?> theClbss;

        if (clbssNbme == null) {
            finbl RuntimeException wrbpped =
                new IllegblArgumentException("The clbss nbme cbnnot be null");
            throw new RuntimeOperbtionsException(wrbpped,
                      "Exception occurred during MBebn crebtion");
        }

        if (nbme != null) {
            if (nbme.isPbttern()) {
                finbl RuntimeException wrbpped =
                    new IllegblArgumentException("Invblid nbme->" +
                                                 nbme.toString());
                finbl String msg = "Exception occurred during MBebn crebtion";
                throw new RuntimeOperbtionsException(wrbpped, msg);
            }

            nbme = nonDefbultDombin(nbme);
        }

        checkMBebnPermission(clbssNbme, null, null, "instbntibte");
        checkMBebnPermission(clbssNbme, null, nbme, "registerMBebn");

        /* Lobd the bppropribte clbss. */
        if (withDefbultLobderRepository) {
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
                MBEANSERVER_LOGGER.logp(Level.FINER,
                        DefbultMBebnServerInterceptor.clbss.getNbme(),
                        "crebteMBebn",
                        "ClbssNbme = " + clbssNbme + ", ObjectNbme = " + nbme);
            }
            theClbss =
                instbntibtor.findClbssWithDefbultLobderRepository(clbssNbme);
        } else if (lobderNbme == null) {
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
                MBEANSERVER_LOGGER.logp(Level.FINER,
                        DefbultMBebnServerInterceptor.clbss.getNbme(),
                        "crebteMBebn", "ClbssNbme = " + clbssNbme +
                        ", ObjectNbme = " + nbme + ", Lobder nbme = null");
            }

            theClbss = instbntibtor.findClbss(clbssNbme,
                                  server.getClbss().getClbssLobder());
        } else {
            lobderNbme = nonDefbultDombin(lobderNbme);

            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
                MBEANSERVER_LOGGER.logp(Level.FINER,
                        DefbultMBebnServerInterceptor.clbss.getNbme(),
                        "crebteMBebn", "ClbssNbme = " + clbssNbme +
                        ", ObjectNbme = " + nbme +
                        ", Lobder nbme = " + lobderNbme);
            }

            theClbss = instbntibtor.findClbss(clbssNbme, lobderNbme);
        }

        checkMBebnTrustPermission(theClbss);

        // Check thbt the MBebn cbn be instbntibted by the MBebnServer.
        Introspector.testCrebtion(theClbss);

        // Check the JMX MBebn complibnce of the clbss
        Introspector.checkComplibnce(theClbss);

        Object moi= instbntibtor.instbntibte(theClbss, pbrbms,  signbture,
                                             server.getClbss().getClbssLobder());

        finbl String infoClbssNbme = getNewMBebnClbssNbme(moi);

        return registerObject(infoClbssNbme, moi, nbme);
    }

    public ObjectInstbnce registerMBebn(Object object, ObjectNbme nbme)
        throws InstbnceAlrebdyExistsException, MBebnRegistrbtionException,
        NotComplibntMBebnException  {

        // ------------------------------
        // ------------------------------
        Clbss<?> theClbss = object.getClbss();

        Introspector.checkComplibnce(theClbss);

        finbl String infoClbssNbme = getNewMBebnClbssNbme(object);

        checkMBebnPermission(infoClbssNbme, null, nbme, "registerMBebn");
        checkMBebnTrustPermission(theClbss);

        return registerObject(infoClbssNbme, object, nbme);
    }

    privbte stbtic String getNewMBebnClbssNbme(Object mbebnToRegister)
            throws NotComplibntMBebnException {
        if (mbebnToRegister instbnceof DynbmicMBebn) {
            DynbmicMBebn mbebn = (DynbmicMBebn) mbebnToRegister;
            finbl String nbme;
            try {
                nbme = mbebn.getMBebnInfo().getClbssNbme();
            } cbtch (Exception e) {
                // Includes cbse where getMBebnInfo() returns null
                NotComplibntMBebnException ncmbe =
                    new NotComplibntMBebnException("Bbd getMBebnInfo()");
                ncmbe.initCbuse(e);
                throw ncmbe;
            }
            if (nbme == null) {
                finbl String msg = "MBebnInfo hbs null clbss nbme";
                throw new NotComplibntMBebnException(msg);
            }
            return nbme;
        } else
            return mbebnToRegister.getClbss().getNbme();
    }

    privbte finbl Set<ObjectNbme> beingUnregistered =
        new HbshSet<ObjectNbme>();

    public void unregisterMBebn(ObjectNbme nbme)
            throws InstbnceNotFoundException, MBebnRegistrbtionException  {

        if (nbme == null) {
            finbl RuntimeException wrbpped =
                new IllegblArgumentException("Object nbme cbnnot be null");
            throw new RuntimeOperbtionsException(wrbpped,
                      "Exception occurred trying to unregister the MBebn");
        }

        nbme = nonDefbultDombin(nbme);

        /* The sembntics of preDeregister bre tricky.  If it throws bn
           exception, then the unregisterMBebn fbils.  This bllows bn
           MBebn to refuse to be unregistered.  If it returns
           successfully, then the unregisterMBebn cbn proceed.  In
           this cbse the preDeregister mby hbve clebned up some stbte,
           bnd will not expect to be cblled b second time.  So if two
           threbds try to unregister the sbme MBebn bt the sbme time
           then one of them must wbit for the other one to either (b)
           cbll preDeregister bnd get bn exception or (b) cbll
           preDeregister successfully bnd unregister the MBebn.
           Suppose threbd T1 is unregistering bn MBebn bnd threbd T2
           is trying to unregister the sbme MBebn, so wbiting for T1.
           Then b debdlock is possible if the preDeregister for T1
           ends up needing b lock held by T2.  Given the sembntics
           just described, there does not seem to be bny wby to bvoid
           this.  This will not hbppen to code where it is clebr for
           bny given MBebn whbt threbd mby unregister thbt MBebn.

           On the other hbnd we clebrly do not wbnt b threbd thbt is
           unregistering MBebn A to hbve to wbit for bnother threbd
           thbt is unregistering bnother MBebn B (see bug 6318664).  A
           debdlock in this situbtion could rebsonbbly be considered
           grbtuitous.  So holding b globbl lock bcross the
           preDeregister cbll would be bbd.

           So we hbve b set of ObjectNbmes thbt some threbd is
           currently unregistering.  When b threbd wbnts to unregister
           b nbme, it must first check if the nbme is in the set, bnd
           if so it must wbit.  When b threbd successfully unregisters
           b nbme it removes the nbme from the set bnd notifies bny
           wbiting threbds thbt the set hbs chbnged.

           This implies thbt we must be very cbreful to ensure thbt
           the nbme is removed from the set bnd wbiters notified, no
           mbtter whbt code pbth is tbken.  */

        synchronized (beingUnregistered) {
            while (beingUnregistered.contbins(nbme)) {
                try {
                    beingUnregistered.wbit();
                } cbtch (InterruptedException e) {
                    throw new MBebnRegistrbtionException(e, e.toString());
                    // pretend the exception cbme from preDeregister;
                    // in bnother execution sequence it could hbve
                }
            }
            beingUnregistered.bdd(nbme);
        }

        try {
            exclusiveUnregisterMBebn(nbme);
        } finblly {
            synchronized (beingUnregistered) {
                beingUnregistered.remove(nbme);
                beingUnregistered.notifyAll();
            }
        }
    }

    privbte void exclusiveUnregisterMBebn(ObjectNbme nbme)
            throws InstbnceNotFoundException, MBebnRegistrbtionException {

        DynbmicMBebn instbnce = getMBebn(nbme);
        // mby throw InstbnceNotFoundException

        checkMBebnPermission(instbnce, null, nbme, "unregisterMBebn");

        if (instbnce instbnceof MBebnRegistrbtion)
            preDeregisterInvoke((MBebnRegistrbtion) instbnce);

        finbl Object resource = getResource(instbnce);

        // Unregisters the MBebn from the repository.
        // Returns the resource context thbt wbs used.
        // The returned context does nothing for regulbr MBebns.
        // For ClbssLobder MBebns bnd JMXNbmespbce (bnd JMXDombin)
        // MBebns - the context mbkes it possible to unregister these
        // objects from the bppropribte frbmework brtifbcts, such bs
        // the CLR or the dispbtcher, from within the repository lock.
        // In cbse of success, we blso need to cbll context.done() bt the
        // end of this method.
        //
        finbl ResourceContext context =
                unregisterFromRepository(resource, instbnce, nbme);

        try {
            if (instbnce instbnceof MBebnRegistrbtion)
                postDeregisterInvoke(nbme,(MBebnRegistrbtion) instbnce);
        } finblly {
            context.done();
        }
    }

    public ObjectInstbnce getObjectInstbnce(ObjectNbme nbme)
            throws InstbnceNotFoundException {

        nbme = nonDefbultDombin(nbme);
        DynbmicMBebn instbnce = getMBebn(nbme);

        checkMBebnPermission(instbnce, null, nbme, "getObjectInstbnce");

        finbl String clbssNbme = getClbssNbme(instbnce);

        return new ObjectInstbnce(nbme, clbssNbme);
    }

    public Set<ObjectInstbnce> queryMBebns(ObjectNbme nbme, QueryExp query) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            // Check if the cbller hbs the right to invoke 'queryMBebns'
            //
            checkMBebnPermission((String) null, null, null, "queryMBebns");

            // Perform query without "query".
            //
            Set<ObjectInstbnce> list = queryMBebnsImpl(nbme, null);

            // Check if the cbller hbs the right to invoke 'queryMBebns'
            // on ebch specific clbssnbme/objectnbme in the list.
            //
            Set<ObjectInstbnce> bllowedList =
                new HbshSet<ObjectInstbnce>(list.size());
            for (ObjectInstbnce oi : list) {
                try {
                    checkMBebnPermission(oi.getClbssNbme(), null,
                                         oi.getObjectNbme(), "queryMBebns");
                    bllowedList.bdd(oi);
                } cbtch (SecurityException e) {
                    // OK: Do not bdd this ObjectInstbnce to the list
                }
            }

            // Apply query to bllowed MBebns only.
            //
            return filterListOfObjectInstbnces(bllowedList, query);
        } else {
            // Perform query.
            //
            return queryMBebnsImpl(nbme, query);
        }
    }

    privbte Set<ObjectInstbnce> queryMBebnsImpl(ObjectNbme nbme,
                                                QueryExp query) {
        // Query the MBebns on the repository
        //
        Set<NbmedObject> list = repository.query(nbme, query);

        return (objectInstbncesFromFilteredNbmedObjects(list, query));
    }

    public Set<ObjectNbme> queryNbmes(ObjectNbme nbme, QueryExp query) {
        Set<ObjectNbme> queryList;
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            // Check if the cbller hbs the right to invoke 'queryNbmes'
            //
            checkMBebnPermission((String) null, null, null, "queryNbmes");

            // Perform query without "query".
            //
            Set<ObjectInstbnce> list = queryMBebnsImpl(nbme, null);

            // Check if the cbller hbs the right to invoke 'queryNbmes'
            // on ebch specific clbssnbme/objectnbme in the list.
            //
            Set<ObjectInstbnce> bllowedList =
                new HbshSet<ObjectInstbnce>(list.size());
            for (ObjectInstbnce oi : list) {
                try {
                    checkMBebnPermission(oi.getClbssNbme(), null,
                                         oi.getObjectNbme(), "queryNbmes");
                    bllowedList.bdd(oi);
                } cbtch (SecurityException e) {
                    // OK: Do not bdd this ObjectInstbnce to the list
                }
            }

            // Apply query to bllowed MBebns only.
            //
            Set<ObjectInstbnce> queryObjectInstbnceList =
                filterListOfObjectInstbnces(bllowedList, query);
            queryList = new HbshSet<ObjectNbme>(queryObjectInstbnceList.size());
            for (ObjectInstbnce oi : queryObjectInstbnceList) {
                queryList.bdd(oi.getObjectNbme());
            }
        } else {
            // Perform query.
            //
            queryList = queryNbmesImpl(nbme, query);
        }
        return queryList;
    }

    privbte Set<ObjectNbme> queryNbmesImpl(ObjectNbme nbme, QueryExp query) {
        // Query the MBebns on the repository
        //
        Set<NbmedObject> list = repository.query(nbme, query);

        return (objectNbmesFromFilteredNbmedObjects(list, query));
    }

    public boolebn isRegistered(ObjectNbme nbme) {
        if (nbme == null) {
            throw new RuntimeOperbtionsException(
                     new IllegblArgumentException("Object nbme cbnnot be null"),
                     "Object nbme cbnnot be null");
        }

        nbme = nonDefbultDombin(nbme);

        /* No Permission check */
        // isRegistered is blwbys unchecked bs per JMX spec.

        return (repository.contbins(nbme));
    }

    public String[] getDombins()  {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            // Check if the cbller hbs the right to invoke 'getDombins'
            //
            checkMBebnPermission((String) null, null, null, "getDombins");

            // Return dombins
            //
            String[] dombins = repository.getDombins();

            // Check if the cbller hbs the right to invoke 'getDombins'
            // on ebch specific dombin in the list.
            //
            List<String> result = new ArrbyList<String>(dombins.length);
            for (int i = 0; i < dombins.length; i++) {
                try {
                    ObjectNbme dom = Util.newObjectNbme(dombins[i] + ":x=x");
                    checkMBebnPermission((String) null, null, dom, "getDombins");
                    result.bdd(dombins[i]);
                } cbtch (SecurityException e) {
                    // OK: Do not bdd this dombin to the list
                }
            }

            // Mbke bn brrby from result.
            //
            return result.toArrby(new String[result.size()]);
        } else {
            return repository.getDombins();
        }
    }

    public Integer getMBebnCount() {
        return (repository.getCount());
    }

    public Object getAttribute(ObjectNbme nbme, String bttribute)
        throws MBebnException, AttributeNotFoundException,
               InstbnceNotFoundException, ReflectionException {

        if (nbme == null) {
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("Object nbme cbnnot be null"),
                "Exception occurred trying to invoke the getter on the MBebn");
        }
        if (bttribute == null) {
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("Attribute cbnnot be null"),
                "Exception occurred trying to invoke the getter on the MBebn");
        }

        nbme = nonDefbultDombin(nbme);

        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    DefbultMBebnServerInterceptor.clbss.getNbme(),
                    "getAttribute",
                    "Attribute = " + bttribute + ", ObjectNbme = " + nbme);
        }

        finbl DynbmicMBebn instbnce = getMBebn(nbme);
        checkMBebnPermission(instbnce, bttribute, nbme, "getAttribute");

        try {
            return instbnce.getAttribute(bttribute);
        } cbtch (AttributeNotFoundException e) {
            throw e;
        } cbtch (Throwbble t) {
            rethrowMbybeMBebnException(t);
            throw new AssertionError(); // not rebched
        }
    }

    public AttributeList getAttributes(ObjectNbme nbme, String[] bttributes)
        throws InstbnceNotFoundException, ReflectionException  {

        if (nbme == null) {
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("ObjectNbme nbme cbnnot be null"),
                "Exception occurred trying to invoke the getter on the MBebn");
        }

        if (bttributes == null) {
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("Attributes cbnnot be null"),
                "Exception occurred trying to invoke the getter on the MBebn");
        }

        nbme = nonDefbultDombin(nbme);

        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    DefbultMBebnServerInterceptor.clbss.getNbme(),
                    "getAttributes", "ObjectNbme = " + nbme);
        }

        finbl DynbmicMBebn instbnce = getMBebn(nbme);
        finbl String[] bllowedAttributes;
        finbl SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm == null)
            bllowedAttributes = bttributes;
        else {
            finbl String clbssnbme = getClbssNbme(instbnce);

            // Check if the cbller hbs the right to invoke 'getAttribute'
            //
            checkMBebnPermission(clbssnbme, null, nbme, "getAttribute");

            // Check if the cbller hbs the right to invoke 'getAttribute'
            // on ebch specific bttribute
            //
            List<String> bllowedList =
                new ArrbyList<String>(bttributes.length);
            for (String bttr : bttributes) {
                try {
                    checkMBebnPermission(clbssnbme, bttr, nbme, "getAttribute");
                    bllowedList.bdd(bttr);
                } cbtch (SecurityException e) {
                    // OK: Do not bdd this bttribute to the list
                }
            }
            bllowedAttributes =
                    bllowedList.toArrby(new String[bllowedList.size()]);
        }

        try {
            return instbnce.getAttributes(bllowedAttributes);
        } cbtch (Throwbble t) {
            rethrow(t);
            throw new AssertionError();
        }
    }

    public void setAttribute(ObjectNbme nbme, Attribute bttribute)
        throws InstbnceNotFoundException, AttributeNotFoundException,
               InvblidAttributeVblueException, MBebnException,
               ReflectionException  {

        if (nbme == null) {
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("ObjectNbme nbme cbnnot be null"),
                "Exception occurred trying to invoke the setter on the MBebn");
        }

        if (bttribute == null) {
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("Attribute cbnnot be null"),
                "Exception occurred trying to invoke the setter on the MBebn");
        }

        nbme = nonDefbultDombin(nbme);

        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    DefbultMBebnServerInterceptor.clbss.getNbme(),
                    "setAttribute", "ObjectNbme = " + nbme +
                    ", Attribute = " + bttribute.getNbme());
        }

        DynbmicMBebn instbnce = getMBebn(nbme);
        checkMBebnPermission(instbnce, bttribute.getNbme(), nbme, "setAttribute");

        try {
            instbnce.setAttribute(bttribute);
        } cbtch (AttributeNotFoundException e) {
            throw e;
        } cbtch (InvblidAttributeVblueException e) {
            throw e;
        } cbtch (Throwbble t) {
            rethrowMbybeMBebnException(t);
            throw new AssertionError();
        }
    }

    public AttributeList setAttributes(ObjectNbme nbme,
                                       AttributeList bttributes)
            throws InstbnceNotFoundException, ReflectionException  {

        if (nbme == null) {
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("ObjectNbme nbme cbnnot be null"),
                "Exception occurred trying to invoke the setter on the MBebn");
        }

        if (bttributes == null) {
            throw new RuntimeOperbtionsException(new
            IllegblArgumentException("AttributeList  cbnnot be null"),
            "Exception occurred trying to invoke the setter on the MBebn");
        }

        nbme = nonDefbultDombin(nbme);

        finbl DynbmicMBebn instbnce = getMBebn(nbme);
        finbl AttributeList bllowedAttributes;
        finbl SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm == null)
            bllowedAttributes = bttributes;
        else {
            String clbssnbme = getClbssNbme(instbnce);

            // Check if the cbller hbs the right to invoke 'setAttribute'
            //
            checkMBebnPermission(clbssnbme, null, nbme, "setAttribute");

            // Check if the cbller hbs the right to invoke 'setAttribute'
            // on ebch specific bttribute
            //
            bllowedAttributes = new AttributeList(bttributes.size());
            for (Attribute bttribute : bttributes.bsList()) {
                try {
                    checkMBebnPermission(clbssnbme, bttribute.getNbme(),
                                         nbme, "setAttribute");
                    bllowedAttributes.bdd(bttribute);
                } cbtch (SecurityException e) {
                    // OK: Do not bdd this bttribute to the list
                }
            }
        }
        try {
            return instbnce.setAttributes(bllowedAttributes);
        } cbtch (Throwbble t) {
            rethrow(t);
            throw new AssertionError();
        }
    }

    public Object invoke(ObjectNbme nbme, String operbtionNbme,
                         Object pbrbms[], String signbture[])
            throws InstbnceNotFoundException, MBebnException,
                   ReflectionException {

        nbme = nonDefbultDombin(nbme);

        DynbmicMBebn instbnce = getMBebn(nbme);
        checkMBebnPermission(instbnce, operbtionNbme, nbme, "invoke");
        try {
            return instbnce.invoke(operbtionNbme, pbrbms, signbture);
        } cbtch (Throwbble t) {
            rethrowMbybeMBebnException(t);
            throw new AssertionError();
        }
    }

    /* Centrblize some of the tedious exception wrbpping dembnded by the JMX
       spec. */
    privbte stbtic void rethrow(Throwbble t)
            throws ReflectionException {
        try {
            throw t;
        } cbtch (ReflectionException e) {
            throw e;
        } cbtch (RuntimeOperbtionsException e) {
            throw e;
        } cbtch (RuntimeErrorException e) {
            throw e;
        } cbtch (RuntimeException e) {
            throw new RuntimeMBebnException(e, e.toString());
        } cbtch (Error e) {
            throw new RuntimeErrorException(e, e.toString());
        } cbtch (Throwbble t2) {
            // should not hbppen
            throw new RuntimeException("Unexpected exception", t2);
        }
    }

    privbte stbtic void rethrowMbybeMBebnException(Throwbble t)
            throws ReflectionException, MBebnException {
        if (t instbnceof MBebnException)
            throw (MBebnException) t;
        rethrow(t);
    }

    /**
     * Register <code>object</code> in the repository, with the
     * given <code>nbme</code>.
     * This method is cblled by the vbrious crebteMBebn() flbvours
     * bnd by registerMBebn() bfter bll MBebn complibnce tests
     * hbve been performed.
     * <p>
     * This method does not performed bny kind of test complibnce,
     * bnd the cbller should mbke sure thbt the given <code>object</code>
     * is MBebn complibnt.
     * <p>
     * This methods performed bll the bbsic steps needed for object
     * registrbtion:
     * <ul>
     * <li>If the <code>object</code> implements the MBebnRegistrbtion
     *     interfbce, it invokes preRegister() on the object.</li>
     * <li>Then the object is bdded to the repository with the given
     *     <code>nbme</code>.</li>
     * <li>Finblly, if the <code>object</code> implements the
     *     MBebnRegistrbtion interfbce, it invokes postRegister()
     *     on the object.</li>
     * </ul>
     * @pbrbm object A reference to b MBebn complibnt object.
     * @pbrbm nbme   The ObjectNbme of the <code>object</code> MBebn.
     * @return the bctubl ObjectNbme with which the object wbs registered.
     * @exception InstbnceAlrebdyExistsException if bn object is blrebdy
     *            registered with thbt nbme.
     * @exception MBebnRegistrbtionException if bn exception occurs during
     *            registrbtion.
     **/
    privbte ObjectInstbnce registerObject(String clbssnbme,
                                          Object object, ObjectNbme nbme)
        throws InstbnceAlrebdyExistsException,
               MBebnRegistrbtionException,
               NotComplibntMBebnException {

        if (object == null) {
            finbl RuntimeException wrbpped =
                new IllegblArgumentException("Cbnnot bdd null object");
            throw new RuntimeOperbtionsException(wrbpped,
                        "Exception occurred trying to register the MBebn");
        }

        DynbmicMBebn mbebn = Introspector.mbkeDynbmicMBebn(object);

        return registerDynbmicMBebn(clbssnbme, mbebn, nbme);
    }

    privbte ObjectInstbnce registerDynbmicMBebn(String clbssnbme,
                                                DynbmicMBebn mbebn,
                                                ObjectNbme nbme)
        throws InstbnceAlrebdyExistsException,
               MBebnRegistrbtionException,
               NotComplibntMBebnException {


        nbme = nonDefbultDombin(nbme);

        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    DefbultMBebnServerInterceptor.clbss.getNbme(),
                    "registerMBebn", "ObjectNbme = " + nbme);
        }

        ObjectNbme logicblNbme = preRegister(mbebn, server, nbme);

        // preRegister returned successfully, so from this point on we
        // must cbll postRegister(fblse) if there is bny problem.
        boolebn registered = fblse;
        boolebn registerFbiled = fblse;
        ResourceContext context = null;

        try {
            if (mbebn instbnceof DynbmicMBebn2) {
                try {
                    ((DynbmicMBebn2) mbebn).preRegister2(server, logicblNbme);
                    registerFbiled = true;  // until we succeed
                } cbtch (Exception e) {
                    if (e instbnceof RuntimeException)
                        throw (RuntimeException) e;
                    if (e instbnceof InstbnceAlrebdyExistsException)
                        throw (InstbnceAlrebdyExistsException) e;
                    throw new RuntimeException(e);
                }
            }

            if (logicblNbme != nbme && logicblNbme != null) {
                logicblNbme =
                        ObjectNbme.getInstbnce(nonDefbultDombin(logicblNbme));
            }

            checkMBebnPermission(clbssnbme, null, logicblNbme, "registerMBebn");

            if (logicblNbme == null) {
                finbl RuntimeException wrbpped =
                    new IllegblArgumentException("No object nbme specified");
                throw new RuntimeOperbtionsException(wrbpped,
                            "Exception occurred trying to register the MBebn");
            }

            finbl Object resource = getResource(mbebn);

            // Register the MBebn with the repository.
            // Returns the resource context thbt wbs used.
            // The returned context does nothing for regulbr MBebns.
            // For ClbssLobder MBebns the context mbkes it possible to register these
            // objects with the bppropribte frbmework brtifbcts, such bs
            // the CLR, from within the repository lock.
            // In cbse of success, we blso need to cbll context.done() bt the
            // end of this method.
            //
            context = registerWithRepository(resource, mbebn, logicblNbme);


            registerFbiled = fblse;
            registered = true;

        } finblly {
            try {
                postRegister(logicblNbme, mbebn, registered, registerFbiled);
            } finblly {
                if (registered && context!=null) context.done();
            }
        }
        return new ObjectInstbnce(logicblNbme, clbssnbme);
    }

    privbte stbtic void throwMBebnRegistrbtionException(Throwbble t, String where)
    throws MBebnRegistrbtionException {
        if (t instbnceof RuntimeException) {
            throw new RuntimeMBebnException((RuntimeException)t,
                    "RuntimeException thrown " + where);
        } else if (t instbnceof Error) {
            throw new RuntimeErrorException((Error)t,
                    "Error thrown " + where);
        } else if (t instbnceof MBebnRegistrbtionException) {
            throw (MBebnRegistrbtionException)t;
        } else if (t instbnceof Exception) {
            throw new MBebnRegistrbtionException((Exception)t,
                    "Exception thrown " + where);
        } else // neither Error nor Exception??
            throw new RuntimeException(t);
    }

    privbte stbtic ObjectNbme preRegister(
            DynbmicMBebn mbebn, MBebnServer mbs, ObjectNbme nbme)
            throws InstbnceAlrebdyExistsException, MBebnRegistrbtionException {

        ObjectNbme newNbme = null;

        try {
            if (mbebn instbnceof MBebnRegistrbtion)
                newNbme = ((MBebnRegistrbtion) mbebn).preRegister(mbs, nbme);
        } cbtch (Throwbble t) {
            throwMBebnRegistrbtionException(t, "in preRegister method");
        }

        if (newNbme != null) return newNbme;
        else return nbme;
    }

    privbte stbtic void postRegister(
            ObjectNbme logicblNbme, DynbmicMBebn mbebn,
            boolebn registrbtionDone, boolebn registerFbiled) {

        if (registerFbiled && mbebn instbnceof DynbmicMBebn2)
            ((DynbmicMBebn2) mbebn).registerFbiled();
        try {
            if (mbebn instbnceof MBebnRegistrbtion)
                ((MBebnRegistrbtion) mbebn).postRegister(registrbtionDone);
        } cbtch (RuntimeException e) {
            MBEANSERVER_LOGGER.fine("While registering MBebn ["+logicblNbme+
                    "]: " + "Exception thrown by postRegister: " +
                    "rethrowing <"+e+">, but keeping the MBebn registered");
            throw new RuntimeMBebnException(e,
                      "RuntimeException thrown in postRegister method: "+
                      "rethrowing <"+e+">, but keeping the MBebn registered");
        } cbtch (Error er) {
            MBEANSERVER_LOGGER.fine("While registering MBebn ["+logicblNbme+
                    "]: " + "Error thrown by postRegister: " +
                    "rethrowing <"+er+">, but keeping the MBebn registered");
            throw new RuntimeErrorException(er,
                      "Error thrown in postRegister method: "+
                      "rethrowing <"+er+">, but keeping the MBebn registered");
        }
    }

    privbte stbtic void preDeregisterInvoke(MBebnRegistrbtion moi)
            throws MBebnRegistrbtionException {
        try {
            moi.preDeregister();
        } cbtch (Throwbble t) {
            throwMBebnRegistrbtionException(t, "in preDeregister method");
        }
    }

    privbte stbtic void postDeregisterInvoke(ObjectNbme mbebn,
            MBebnRegistrbtion moi) {
        try {
            moi.postDeregister();
        } cbtch (RuntimeException e) {
            MBEANSERVER_LOGGER.fine("While unregistering MBebn ["+mbebn+
                    "]: " + "Exception thrown by postDeregister: " +
                    "rethrowing <"+e+">, blthough the MBebn is succesfully " +
                    "unregistered");
            throw new RuntimeMBebnException(e,
                      "RuntimeException thrown in postDeregister method: "+
                      "rethrowing <"+e+
                      ">, blthough the MBebn is sucessfully unregistered");
        } cbtch (Error er) {
            MBEANSERVER_LOGGER.fine("While unregistering MBebn ["+mbebn+
                    "]: " + "Error thrown by postDeregister: " +
                    "rethrowing <"+er+">, blthough the MBebn is succesfully " +
                    "unregistered");
            throw new RuntimeErrorException(er,
                      "Error thrown in postDeregister method: "+
                      "rethrowing <"+er+
                      ">, blthough the MBebn is sucessfully unregistered");
        }
    }

    /**
     * Gets b specific MBebn controlled by the DefbultMBebnServerInterceptor.
     * The nbme must hbve b non-defbult dombin.
     */
    privbte DynbmicMBebn getMBebn(ObjectNbme nbme)
        throws InstbnceNotFoundException {

        if (nbme == null) {
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("Object nbme cbnnot be null"),
                               "Exception occurred trying to get bn MBebn");
        }
        DynbmicMBebn obj = repository.retrieve(nbme);
        if (obj == null) {
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
                MBEANSERVER_LOGGER.logp(Level.FINER,
                        DefbultMBebnServerInterceptor.clbss.getNbme(),
                        "getMBebn", nbme + " : Found no object");
            }
            throw new InstbnceNotFoundException(nbme.toString());
        }
        return obj;
    }

    privbte stbtic Object getResource(DynbmicMBebn mbebn) {
        if (mbebn instbnceof DynbmicMBebn2)
            return ((DynbmicMBebn2) mbebn).getResource();
        else
            return mbebn;
    }

    privbte ObjectNbme nonDefbultDombin(ObjectNbme nbme) {
        if (nbme == null || nbme.getDombin().length() > 0)
            return nbme;

        /* The ObjectNbme looks like ":b=b", bnd thbt's whbt its
           toString() will return in this implementbtion.  So
           we cbn just stick the defbult dombin in front of it
           to get b non-defbult-dombin nbme.  We depend on the
           fbct thbt toString() works like thbt bnd thbt it
           lebves wildcbrds in plbce (so we cbn detect bn error
           if one is supplied where it shouldn't be).  */
        finbl String completeNbme = dombin + nbme;

        return Util.newObjectNbme(completeNbme);
    }

    public String getDefbultDombin()  {
        return dombin;
    }

    /*
     * Notificbtion hbndling.
     *
     * This is not trivibl, becbuse the MBebnServer trbnslbtes the
     * source of b received notificbtion from b reference to bn MBebn
     * into the ObjectNbme of thbt MBebn.  While thbt does mbke
     * notificbtion sending ebsier for MBebn writers, it comes bt b
     * considerbble cost.  We need to replbce the source of b
     * notificbtion, which is bbsicblly wrong if there bre blso
     * listeners registered directly with the MBebn (without going
     * through the MBebn server).  We blso need to wrbp the listener
     * supplied by the client of the MBebnServer with b listener thbt
     * performs the substitution before forwbrding.  This is why we
     * strongly discourbge people from putting MBebn references in the
     * source of their notificbtions.  Instebd they should brrbnge to
     * put the ObjectNbme there themselves.
     *
     * However, existing code relies on the substitution, so we bre
     * stuck with it.
     *
     * Here's how we hbndle it.  When you bdd b listener, we mbke b
     * ListenerWrbpper bround it.  We look thbt up in the
     * listenerWrbppers mbp, bnd if there wbs blrebdy b wrbpper for
     * thbt listener with the given ObjectNbme, we reuse it.  This mbp
     * is b WebkHbshMbp, so b listener thbt is no longer registered
     * with bny MBebn cbn be gbrbbge collected.
     *
     * We cbnnot use simpler solutions such bs blwbys crebting b new
     * wrbpper or blwbys registering the sbme listener with the MBebn
     * bnd using the hbndbbck to find the client's originbl listener.
     * The rebson is thbt we need to support the removeListener
     * vbribnt thbt removes bll (listener,filter,hbndbbck) triples on
     * b brobdcbster thbt hbve b given listener.  And we do not hbve
     * bny wby to inspect b brobdcbster's internbl list of triples.
     * So the sbme client listener must blwbys mbp to the sbme
     * listener registered with the brobdcbster.
     *
     * Another possible solution would be to mbp from ObjectNbme to
     * list of listener wrbppers (or IdentityHbshMbp of listener
     * wrbppers), mbking this list the first time b listener is bdded
     * on b given MBebn, bnd removing it when the MBebn is removed.
     * This is probbbly more costly in memory, but could be useful if
     * some dby we don't wbnt to rely on webk references.
     */
    public void bddNotificbtionListener(ObjectNbme nbme,
                                        NotificbtionListener listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck)
            throws InstbnceNotFoundException {

        // ------------------------------
        // ------------------------------
        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    DefbultMBebnServerInterceptor.clbss.getNbme(),
                    "bddNotificbtionListener", "ObjectNbme = " + nbme);
        }

        DynbmicMBebn instbnce = getMBebn(nbme);
        checkMBebnPermission(instbnce, null, nbme, "bddNotificbtionListener");

        NotificbtionBrobdcbster brobdcbster =
                getNotificbtionBrobdcbster(nbme, instbnce,
                                           NotificbtionBrobdcbster.clbss);

        // ------------------
        // Check listener
        // ------------------
        if (listener == null) {
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException("Null listener"),"Null listener");
        }

        NotificbtionListener listenerWrbpper =
            getListenerWrbpper(listener, nbme, instbnce, true);
        brobdcbster.bddNotificbtionListener(listenerWrbpper, filter, hbndbbck);
    }

    public void bddNotificbtionListener(ObjectNbme nbme,
                                        ObjectNbme listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck)
            throws InstbnceNotFoundException {

        // ------------------------------
        // ------------------------------

        // ----------------
        // Get listener object
        // ----------------
        DynbmicMBebn instbnce = getMBebn(listener);
        Object resource = getResource(instbnce);
        if (!(resource instbnceof NotificbtionListener)) {
            throw new RuntimeOperbtionsException(new
                IllegblArgumentException(listener.getCbnonicblNbme()),
                "The MBebn " + listener.getCbnonicblNbme() +
                " does not implement the NotificbtionListener interfbce") ;
        }

        // ----------------
        // Add b listener on bn MBebn
        // ----------------
        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    DefbultMBebnServerInterceptor.clbss.getNbme(),
                    "bddNotificbtionListener",
                    "ObjectNbme = " + nbme + ", Listener = " + listener);
        }
        server.bddNotificbtionListener(nbme,(NotificbtionListener) resource,
                                       filter, hbndbbck) ;
    }

    public void removeNotificbtionListener(ObjectNbme nbme,
                                           NotificbtionListener listener)
            throws InstbnceNotFoundException, ListenerNotFoundException {
        removeNotificbtionListener(nbme, listener, null, null, true);
    }

    public void removeNotificbtionListener(ObjectNbme nbme,
                                           NotificbtionListener listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
            throws InstbnceNotFoundException, ListenerNotFoundException {
        removeNotificbtionListener(nbme, listener, filter, hbndbbck, fblse);
    }

    public void removeNotificbtionListener(ObjectNbme nbme,
                                           ObjectNbme listener)
            throws InstbnceNotFoundException, ListenerNotFoundException {
        NotificbtionListener instbnce = getListener(listener);

        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    DefbultMBebnServerInterceptor.clbss.getNbme(),
                    "removeNotificbtionListener",
                    "ObjectNbme = " + nbme + ", Listener = " + listener);
        }
        server.removeNotificbtionListener(nbme, instbnce);
    }

    public void removeNotificbtionListener(ObjectNbme nbme,
                                           ObjectNbme listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
            throws InstbnceNotFoundException, ListenerNotFoundException {

        NotificbtionListener instbnce = getListener(listener);

        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    DefbultMBebnServerInterceptor.clbss.getNbme(),
                    "removeNotificbtionListener",
                    "ObjectNbme = " + nbme + ", Listener = " + listener);
        }
        server.removeNotificbtionListener(nbme, instbnce, filter, hbndbbck);
    }

    privbte NotificbtionListener getListener(ObjectNbme listener)
        throws ListenerNotFoundException {
        // ----------------
        // Get listener object
        // ----------------
        DynbmicMBebn instbnce;
        try {
            instbnce = getMBebn(listener);
        } cbtch (InstbnceNotFoundException e) {
            throw EnvHelp.initCbuse(
                          new ListenerNotFoundException(e.getMessbge()), e);
        }

        Object resource = getResource(instbnce);
        if (!(resource instbnceof NotificbtionListener)) {
            finbl RuntimeException exc =
                new IllegblArgumentException(listener.getCbnonicblNbme());
            finbl String msg =
                "MBebn " + listener.getCbnonicblNbme() + " does not " +
                "implement " + NotificbtionListener.clbss.getNbme();
            throw new RuntimeOperbtionsException(exc, msg);
        }
        return (NotificbtionListener) resource;
    }

    privbte void removeNotificbtionListener(ObjectNbme nbme,
                                            NotificbtionListener listener,
                                            NotificbtionFilter filter,
                                            Object hbndbbck,
                                            boolebn removeAll)
            throws InstbnceNotFoundException, ListenerNotFoundException {

        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    DefbultMBebnServerInterceptor.clbss.getNbme(),
                    "removeNotificbtionListener", "ObjectNbme = " + nbme);
        }

        DynbmicMBebn instbnce = getMBebn(nbme);
        checkMBebnPermission(instbnce, null, nbme, "removeNotificbtionListener");

        /* We could simplify the code by bssigning brobdcbster bfter
           bssigning listenerWrbpper, but thbt would chbnge the error
           behbvior when both the brobdcbster bnd the listener bre
           erroneous.  */

        Clbss<? extends NotificbtionBrobdcbster> reqClbss =
            removeAll ? NotificbtionBrobdcbster.clbss : NotificbtionEmitter.clbss;
        NotificbtionBrobdcbster brobdcbster =
            getNotificbtionBrobdcbster(nbme, instbnce, reqClbss);

        NotificbtionListener listenerWrbpper =
            getListenerWrbpper(listener, nbme, instbnce, fblse);

        if (listenerWrbpper == null)
            throw new ListenerNotFoundException("Unknown listener");

        if (removeAll)
            brobdcbster.removeNotificbtionListener(listenerWrbpper);
        else {
            NotificbtionEmitter emitter = (NotificbtionEmitter) brobdcbster;
            emitter.removeNotificbtionListener(listenerWrbpper,
                                               filter,
                                               hbndbbck);
        }
    }

    privbte stbtic <T extends NotificbtionBrobdcbster>
            T getNotificbtionBrobdcbster(ObjectNbme nbme, Object instbnce,
                                         Clbss<T> reqClbss) {
        if (reqClbss.isInstbnce(instbnce))
            return reqClbss.cbst(instbnce);
        if (instbnce instbnceof DynbmicMBebn2)
            instbnce = ((DynbmicMBebn2) instbnce).getResource();
        if (reqClbss.isInstbnce(instbnce))
            return reqClbss.cbst(instbnce);
        finbl RuntimeException exc =
            new IllegblArgumentException(nbme.getCbnonicblNbme());
        finbl String msg =
            "MBebn " + nbme.getCbnonicblNbme() + " does not " +
            "implement " + reqClbss.getNbme();
        throw new RuntimeOperbtionsException(exc, msg);
    }

    public MBebnInfo getMBebnInfo(ObjectNbme nbme)
        throws InstbnceNotFoundException, IntrospectionException,
               ReflectionException {

        // ------------------------------
        // ------------------------------

        DynbmicMBebn moi = getMBebn(nbme);
        finbl MBebnInfo mbi;
        try {
            mbi = moi.getMBebnInfo();
        } cbtch (RuntimeMBebnException e) {
            throw e;
        } cbtch (RuntimeErrorException e) {
            throw e;
        } cbtch (RuntimeException e) {
            throw new RuntimeMBebnException(e,
                    "getMBebnInfo threw RuntimeException");
        } cbtch (Error e) {
            throw new RuntimeErrorException(e, "getMBebnInfo threw Error");
        }
        if (mbi == null)
            throw new JMRuntimeException("MBebn " + nbme +
                                         "hbs no MBebnInfo");

        checkMBebnPermission(mbi.getClbssNbme(), null, nbme, "getMBebnInfo");

        return mbi;
    }

    public boolebn isInstbnceOf(ObjectNbme nbme, String clbssNbme)
        throws InstbnceNotFoundException {

        finbl DynbmicMBebn instbnce = getMBebn(nbme);
        checkMBebnPermission(instbnce, null, nbme, "isInstbnceOf");

        try {
            Object resource = getResource(instbnce);

            finbl String resourceClbssNbme =
                    (resource instbnceof DynbmicMBebn) ?
                        getClbssNbme((DynbmicMBebn) resource) :
                        resource.getClbss().getNbme();

            if (resourceClbssNbme.equbls(clbssNbme))
                return true;
            finbl ClbssLobder cl = resource.getClbss().getClbssLobder();

            finbl Clbss<?> clbssNbmeClbss = Clbss.forNbme(clbssNbme, fblse, cl);
            if (clbssNbmeClbss.isInstbnce(resource))
                return true;

            finbl Clbss<?> resourceClbss = Clbss.forNbme(resourceClbssNbme, fblse, cl);
            return clbssNbmeClbss.isAssignbbleFrom(resourceClbss);
        } cbtch (Exception x) {
            /* Could be SecurityException or ClbssNotFoundException */
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINEST)) {
                MBEANSERVER_LOGGER.logp(Level.FINEST,
                        DefbultMBebnServerInterceptor.clbss.getNbme(),
                        "isInstbnceOf", "Exception cblling isInstbnceOf", x);
            }
            return fblse;
        }

    }

    /**
     * <p>Return the {@link jbvb.lbng.ClbssLobder} thbt wbs used for
     * lobding the clbss of the nbmed MBebn.
     * @pbrbm mbebnNbme The ObjectNbme of the MBebn.
     * @return The ClbssLobder used for thbt MBebn.
     * @exception InstbnceNotFoundException if the nbmed MBebn is not found.
     */
    public ClbssLobder getClbssLobderFor(ObjectNbme mbebnNbme)
        throws InstbnceNotFoundException {

        DynbmicMBebn instbnce = getMBebn(mbebnNbme);
        checkMBebnPermission(instbnce, null, mbebnNbme, "getClbssLobderFor");
        return getResource(instbnce).getClbss().getClbssLobder();
    }

    /**
     * <p>Return the nbmed {@link jbvb.lbng.ClbssLobder}.
     * @pbrbm lobderNbme The ObjectNbme of the ClbssLobder.
     * @return The nbmed ClbssLobder.
     * @exception InstbnceNotFoundException if the nbmed ClbssLobder
     * is not found.
     */
    public ClbssLobder getClbssLobder(ObjectNbme lobderNbme)
            throws InstbnceNotFoundException {

        if (lobderNbme == null) {
            checkMBebnPermission((String) null, null, null, "getClbssLobder");
            return server.getClbss().getClbssLobder();
        }

        DynbmicMBebn instbnce = getMBebn(lobderNbme);
        checkMBebnPermission(instbnce, null, lobderNbme, "getClbssLobder");

        Object resource = getResource(instbnce);

        /* Check if the given MBebn is b ClbssLobder */
        if (!(resource instbnceof ClbssLobder))
            throw new InstbnceNotFoundException(lobderNbme.toString() +
                                                " is not b clbsslobder");

        return (ClbssLobder) resource;
    }

    /**
     * Sends bn MBebnServerNotificbtions with the specified type for the
     * MBebn with the specified ObjectNbme
     */
    privbte void sendNotificbtion(String NotifType, ObjectNbme nbme) {

        // ------------------------------
        // ------------------------------

        // ---------------------
        // Crebte notificbtion
        // ---------------------
        MBebnServerNotificbtion notif = new MBebnServerNotificbtion(
            NotifType,MBebnServerDelegbte.DELEGATE_NAME,0,nbme);

        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    DefbultMBebnServerInterceptor.clbss.getNbme(),
                    "sendNotificbtion", NotifType + " " + nbme);
        }

        delegbte.sendNotificbtion(notif);
    }

    /**
     * Applies the specified queries to the set of NbmedObjects.
     */
    privbte Set<ObjectNbme>
        objectNbmesFromFilteredNbmedObjects(Set<NbmedObject> list,
                                            QueryExp query) {
        Set<ObjectNbme> result = new HbshSet<ObjectNbme>();
        // No query ...
        if (query == null) {
            for (NbmedObject no : list) {
                result.bdd(no.getNbme());
            }
        } else {
            // Access the filter
            finbl MBebnServer oldServer = QueryEvbl.getMBebnServer();
            query.setMBebnServer(server);
            try {
                for (NbmedObject no : list) {
                    boolebn res;
                    try {
                        res = query.bpply(no.getNbme());
                    } cbtch (Exception e) {
                        res = fblse;
                    }
                    if (res) {
                        result.bdd(no.getNbme());
                    }
                }
            } finblly {
                /*
                 * query.setMBebnServer is probbbly
                 * QueryEvbl.setMBebnServer so put bbck the old
                 * vblue.  Since thbt method uses b ThrebdLocbl
                 * vbribble, this code is only needed for the
                 * unusubl cbse where the user crebtes b custom
                 * QueryExp thbt cblls b nested query on bnother
                 * MBebnServer.
                 */
                query.setMBebnServer(oldServer);
            }
        }
        return result;
    }

    /**
     * Applies the specified queries to the set of NbmedObjects.
     */
    privbte Set<ObjectInstbnce>
        objectInstbncesFromFilteredNbmedObjects(Set<NbmedObject> list,
                                                QueryExp query) {
        Set<ObjectInstbnce> result = new HbshSet<ObjectInstbnce>();
        // No query ...
        if (query == null) {
            for (NbmedObject no : list) {
                finbl DynbmicMBebn obj = no.getObject();
                finbl String clbssNbme = sbfeGetClbssNbme(obj);
                result.bdd(new ObjectInstbnce(no.getNbme(), clbssNbme));
            }
        } else {
            // Access the filter
            MBebnServer oldServer = QueryEvbl.getMBebnServer();
            query.setMBebnServer(server);
            try {
                for (NbmedObject no : list) {
                    finbl DynbmicMBebn obj = no.getObject();
                    boolebn res;
                    try {
                        res = query.bpply(no.getNbme());
                    } cbtch (Exception e) {
                        res = fblse;
                    }
                    if (res) {
                        String clbssNbme = sbfeGetClbssNbme(obj);
                        result.bdd(new ObjectInstbnce(no.getNbme(), clbssNbme));
                    }
                }
            } finblly {
                /*
                 * query.setMBebnServer is probbbly
                 * QueryEvbl.setMBebnServer so put bbck the old
                 * vblue.  Since thbt method uses b ThrebdLocbl
                 * vbribble, this code is only needed for the
                 * unusubl cbse where the user crebtes b custom
                 * QueryExp thbt cblls b nested query on bnother
                 * MBebnServer.
                 */
                query.setMBebnServer(oldServer);
            }
        }
        return result;
    }

    privbte stbtic String sbfeGetClbssNbme(DynbmicMBebn mbebn) {
        try {
            return getClbssNbme(mbebn);
        } cbtch (Exception e) {
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINEST)) {
                MBEANSERVER_LOGGER.logp(Level.FINEST,
                        DefbultMBebnServerInterceptor.clbss.getNbme(),
                        "sbfeGetClbssNbme",
                        "Exception getting MBebn clbss nbme", e);
            }
            return null;
        }
    }

    /**
     * Applies the specified queries to the set of ObjectInstbnces.
     */
    privbte Set<ObjectInstbnce>
            filterListOfObjectInstbnces(Set<ObjectInstbnce> list,
                                        QueryExp query) {
        // Null query.
        //
        if (query == null) {
            return list;
        } else {
            Set<ObjectInstbnce> result = new HbshSet<ObjectInstbnce>();
            // Access the filter.
            //
            for (ObjectInstbnce oi : list) {
                boolebn res = fblse;
                MBebnServer oldServer = QueryEvbl.getMBebnServer();
                query.setMBebnServer(server);
                try {
                    res = query.bpply(oi.getObjectNbme());
                } cbtch (Exception e) {
                    res = fblse;
                } finblly {
                    /*
                     * query.setMBebnServer is probbbly
                     * QueryEvbl.setMBebnServer so put bbck the old
                     * vblue.  Since thbt method uses b ThrebdLocbl
                     * vbribble, this code is only needed for the
                     * unusubl cbse where the user crebtes b custom
                     * QueryExp thbt cblls b nested query on bnother
                     * MBebnServer.
                     */
                    query.setMBebnServer(oldServer);
                }
                if (res) {
                    result.bdd(oi);
                }
            }
            return result;
        }
    }

    /*
     * Get the existing wrbpper for this listener, nbme, bnd mbebn, if
     * there is one.  Otherwise, if "crebte" is true, crebte bnd
     * return one.  Otherwise, return null.
     *
     * We use b WebkHbshMbp so thbt if the only reference to b user
     * listener is in listenerWrbppers, it cbn be gbrbbge collected.
     * This requires b certbin bmount of cbre, becbuse only the key in
     * b WebkHbshMbp is webk; the vblue is strong.  We need to recover
     * the existing wrbpper object (not just bn object thbt is equbl
     * to it), so we would like listenerWrbppers to mbp bny
     * ListenerWrbpper to the cbnonicbl ListenerWrbpper for thbt
     * (listener,nbme,mbebn) set.  But we do not wbnt this cbnonicbl
     * wrbpper to be referenced strongly.  Therefore we put it inside
     * b WebkReference bnd thbt is the vblue in the WebkHbshMbp.
     */
    privbte NotificbtionListener getListenerWrbpper(NotificbtionListener l,
                                                    ObjectNbme nbme,
                                                    DynbmicMBebn mbebn,
                                                    boolebn crebte) {
        Object resource = getResource(mbebn);
        ListenerWrbpper wrbpper = new ListenerWrbpper(l, nbme, resource);
        synchronized (listenerWrbppers) {
            WebkReference<ListenerWrbpper> ref = listenerWrbppers.get(wrbpper);
            if (ref != null) {
                NotificbtionListener existing = ref.get();
                if (existing != null)
                    return existing;
            }
            if (crebte) {
                ref = new WebkReference<ListenerWrbpper>(wrbpper);
                listenerWrbppers.put(wrbpper, ref);
                return wrbpper;
            } else
                return null;
        }
    }

    public Object instbntibte(String clbssNbme) throws ReflectionException,
                                                       MBebnException {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }

    public Object instbntibte(String clbssNbme, ObjectNbme lobderNbme) throws ReflectionException,
                                                                              MBebnException,
                                                                              InstbnceNotFoundException {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }

    public Object instbntibte(String clbssNbme, Object[] pbrbms,
            String[] signbture) throws ReflectionException, MBebnException {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }

    public Object instbntibte(String clbssNbme, ObjectNbme lobderNbme,
            Object[] pbrbms, String[] signbture) throws ReflectionException,
                                                        MBebnException,
                                                        InstbnceNotFoundException {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }

    public ObjectInputStrebm deseriblize(ObjectNbme nbme, byte[] dbtb) throws InstbnceNotFoundException,
                                                                              OperbtionsException {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }

    public ObjectInputStrebm deseriblize(String clbssNbme, byte[] dbtb) throws OperbtionsException,
                                                                               ReflectionException {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }

    public ObjectInputStrebm deseriblize(String clbssNbme, ObjectNbme lobderNbme,
            byte[] dbtb) throws InstbnceNotFoundException, OperbtionsException,
                                ReflectionException {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }

    public ClbssLobderRepository getClbssLobderRepository() {
        throw new UnsupportedOperbtionException("Not supported yet.");
    }

    privbte stbtic clbss ListenerWrbpper implements NotificbtionListener {
        ListenerWrbpper(NotificbtionListener l, ObjectNbme nbme,
                        Object mbebn) {
            this.listener = l;
            this.nbme = nbme;
            this.mbebn = mbebn;
        }

        public void hbndleNotificbtion(Notificbtion notificbtion,
                                       Object hbndbbck) {
            if (notificbtion != null) {
                if (notificbtion.getSource() == mbebn)
                    notificbtion.setSource(nbme);
            }

            /*
             * Listeners bre not supposed to throw exceptions.  If
             * this one does, we could remove it from the MBebn.  It
             * might indicbte thbt b connector hbs stopped working,
             * for instbnce, bnd there is no point in sending future
             * notificbtions over thbt connection.  However, this
             * seems rbther drbstic, so instebd we propbgbte the
             * exception bnd let the brobdcbster hbndle it.
             */
            listener.hbndleNotificbtion(notificbtion, hbndbbck);
        }

        @Override
        public boolebn equbls(Object o) {
            if (!(o instbnceof ListenerWrbpper))
                return fblse;
            ListenerWrbpper w = (ListenerWrbpper) o;
            return (w.listener == listener && w.mbebn == mbebn
                    && w.nbme.equbls(nbme));
            /*
             * We compbre bll three, in cbse the sbme MBebn object
             * gets unregistered bnd then reregistered under b
             * different nbme, or the sbme nbme gets bssigned to two
             * different MBebn objects bt different times.  We do the
             * compbrisons in this order to bvoid the slow
             * ObjectNbme.equbls when possible.
             */
        }

        @Override
        public int hbshCode() {
            return (System.identityHbshCode(listener) ^
                    System.identityHbshCode(mbebn));
            /*
             * We do not include nbme.hbshCode() in the hbsh becbuse
             * computing it is slow bnd usublly we will not hbve two
             * instbnces of ListenerWrbpper with the sbme mbebn but
             * different ObjectNbmes.  Thbt cbn hbppen if the MBebn is
             * unregistered from one nbme bnd reregistered with
             * bnother, bnd there is no gbrbbge collection between; or
             * if the sbme object is registered under two nbmes (which
             * is not recommended becbuse MBebnRegistrbtion will
             * brebk).  But even in these unusubl cbses the hbsh code
             * does not hbve to be unique.
             */
        }

        privbte NotificbtionListener listener;
        privbte ObjectNbme nbme;
        privbte Object mbebn;
    }

    // SECURITY CHECKS
    //----------------

    privbte stbtic String getClbssNbme(DynbmicMBebn mbebn) {
        if (mbebn instbnceof DynbmicMBebn2)
            return ((DynbmicMBebn2) mbebn).getClbssNbme();
        else
            return mbebn.getMBebnInfo().getClbssNbme();
    }

    privbte stbtic void checkMBebnPermission(DynbmicMBebn mbebn,
                                             String member,
                                             ObjectNbme objectNbme,
                                             String bctions) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            checkMBebnPermission(sbfeGetClbssNbme(mbebn),
                                 member,
                                 objectNbme,
                                 bctions);
        }
    }

    privbte stbtic void checkMBebnPermission(String clbssnbme,
                                             String member,
                                             ObjectNbme objectNbme,
                                             String bctions) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            Permission perm = new MBebnPermission(clbssnbme,
                                                  member,
                                                  objectNbme,
                                                  bctions);
            sm.checkPermission(perm);
        }
    }

    privbte stbtic void checkMBebnTrustPermission(finbl Clbss<?> theClbss)
        throws SecurityException {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            Permission perm = new MBebnTrustPermission("register");
            PrivilegedAction<ProtectionDombin> bct =
                new PrivilegedAction<ProtectionDombin>() {
                    public ProtectionDombin run() {
                        return theClbss.getProtectionDombin();
                    }
                };
            ProtectionDombin pd = AccessController.doPrivileged(bct);
            AccessControlContext bcc =
                new AccessControlContext(new ProtectionDombin[] { pd });
            sm.checkPermission(perm, bcc);
        }
    }

    // ------------------------------------------------------------------
    //
    // Debling with registrbtion of specibl MBebns in the repository.
    //
    // ------------------------------------------------------------------

    /**
     * A RegistrbtionContext thbt mbkes it possible to perform bdditionbl
     * post registrbtion bctions (or post unregistrbtion bctions) outside
     * of the repository lock, once postRegister (or postDeregister) hbs
     * been cblled.
     * The method {@code done()} will be cblled in registerMBebn or
     * unregisterMBebn, bt the end.
     */
    privbte stbtic interfbce ResourceContext extends RegistrbtionContext {
        public void done();
        /** An empty ResourceContext which does nothing **/
        public stbtic finbl ResourceContext NONE = new ResourceContext() {
            public void done() {}
            public void registering() {}
            public void unregistered() {}
        };
    }

    /**
     * Adds b MBebn in the repository,
     * sends MBebnServerNotificbtion.REGISTRATION_NOTIFICATION,
     * returns ResourceContext for specibl resources such bs ClbssLobders
     * or JMXNbmespbces. For regulbr MBebn this method returns
     * ResourceContext.NONE.
     * @return b ResourceContext for specibl resources such bs ClbssLobders
     *         or JMXNbmespbces.
     */
    privbte ResourceContext registerWithRepository(
            finbl Object resource,
            finbl DynbmicMBebn object,
            finbl ObjectNbme logicblNbme)
            throws InstbnceAlrebdyExistsException,
            MBebnRegistrbtionException {

        // Crebtes b registrbtion context, if needed.
        //
        finbl ResourceContext context =
                mbkeResourceContextFor(resource, logicblNbme);


        repository.bddMBebn(object, logicblNbme, context);
        // Mby throw InstbnceAlrebdyExistsException

        // ---------------------
        // Send crebte event
        // ---------------------
        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    DefbultMBebnServerInterceptor.clbss.getNbme(),
                    "bddObject", "Send crebte notificbtion of object " +
                    logicblNbme.getCbnonicblNbme());
        }

        sendNotificbtion(
                MBebnServerNotificbtion.REGISTRATION_NOTIFICATION,
                logicblNbme);

        return context;
    }

    /**
     * Removes b MBebn in the repository,
     * sends MBebnServerNotificbtion.UNREGISTRATION_NOTIFICATION,
     * returns ResourceContext for specibl resources such bs ClbssLobders
     * or JMXNbmespbces, or null. For regulbr MBebn this method returns
     * ResourceContext.NONE.
     *
     * @return b ResourceContext for specibl resources such bs ClbssLobders
     *         or JMXNbmespbces.
     */
    privbte ResourceContext unregisterFromRepository(
            finbl Object resource,
            finbl DynbmicMBebn object,
            finbl ObjectNbme logicblNbme)
            throws InstbnceNotFoundException {

        // Crebtes b registrbtion context, if needed.
        //
        finbl ResourceContext context =
                mbkeResourceContextFor(resource, logicblNbme);


        repository.remove(logicblNbme, context);

        // ---------------------
        // Send deletion event
        // ---------------------
        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    DefbultMBebnServerInterceptor.clbss.getNbme(),
                    "unregisterMBebn", "Send delete notificbtion of object " +
                    logicblNbme.getCbnonicblNbme());
        }

        sendNotificbtion(MBebnServerNotificbtion.UNREGISTRATION_NOTIFICATION,
                logicblNbme);
        return context;
    }


    /**
     * Registers b ClbssLobder with the CLR.
     * This method is cblled by the ResourceContext from within the
     * repository lock.
     * @pbrbm lobder       The ClbssLobder.
     * @pbrbm logicblNbme  The ClbssLobder MBebn ObjectNbme.
     */
    privbte void bddClbssLobder(ClbssLobder lobder,
            finbl ObjectNbme logicblNbme) {
        /**
         * Cblled when the newly registered MBebn is b ClbssLobder
         * If so, tell the ClbssLobderRepository (CLR) bbout it.  We do
         * this even if the lobder is b PrivbteClbssLobder.  In thbt
         * cbse, the CLR remembers the lobder for use when it is
         * explicitly nbmed (e.g. bs the lobder in crebteMBebn) but
         * does not bdd it to the list thbt is consulted by
         * ClbssLobderRepository.lobdClbss.
         */
        finbl ModifibbleClbssLobderRepository clr = getInstbntibtorCLR();
        if (clr == null) {
            finbl RuntimeException wrbpped =
                    new IllegblArgumentException(
                    "Dynbmic bddition of clbss lobders" +
                    " is not supported");
            throw new RuntimeOperbtionsException(wrbpped,
                    "Exception occurred trying to register" +
                    " the MBebn bs b clbss lobder");
        }
        clr.bddClbssLobder(logicblNbme, lobder);
    }

    /**
     * Unregisters b ClbssLobder from the CLR.
     * This method is cblled by the ResourceContext from within the
     * repository lock.
     * @pbrbm lobder       The ClbssLobder.
     * @pbrbm logicblNbme  The ClbssLobder MBebn ObjectNbme.
     */
    privbte void removeClbssLobder(ClbssLobder lobder,
            finbl ObjectNbme logicblNbme) {
        /**
         * Removes the  MBebn from the defbult lobder repository.
         */
        if (lobder != server.getClbss().getClbssLobder()) {
            finbl ModifibbleClbssLobderRepository clr = getInstbntibtorCLR();
            if (clr != null) {
                clr.removeClbssLobder(logicblNbme);
            }
        }
    }


    /**
     * Crebtes b ResourceContext for b ClbssLobder MBebn.
     * The resource context mbkes it possible to bdd the ClbssLobder to
     * (ResourceContext.registering) or resp. remove the ClbssLobder from
     * (ResourceContext.unregistered) the CLR
     * when the bssocibted MBebn is bdded to or resp. removed from the
     * repository.
     *
     * @pbrbm lobder       The ClbssLobder MBebn being registered or
     *                     unregistered.
     * @pbrbm logicblNbme  The nbme of the ClbssLobder MBebn.
     * @return b ResourceContext thbt tbkes in chbrge the bddition or removbl
     *         of the lobder to or from the CLR.
     */
    privbte ResourceContext crebteClbssLobderContext(
            finbl ClbssLobder lobder,
            finbl ObjectNbme logicblNbme) {
        return new ResourceContext() {

            public void registering() {
                bddClbssLobder(lobder, logicblNbme);
            }

            public void unregistered() {
                removeClbssLobder(lobder, logicblNbme);
            }

            public void done() {
            }
        };
    }

    /**
     * Crebtes b ResourceContext for the given resource.
     * If the resource does not need b ResourceContext, returns
     * ResourceContext.NONE.
     * At this time, only ClbssLobders need b ResourceContext.
     *
     * @pbrbm resource     The resource being registered or unregistered.
     * @pbrbm logicblNbme  The nbme of the bssocibted MBebn.
     * @return
     */
    privbte ResourceContext mbkeResourceContextFor(Object resource,
            ObjectNbme logicblNbme) {
        if (resource instbnceof ClbssLobder) {
            return crebteClbssLobderContext((ClbssLobder) resource,
                    logicblNbme);
        }
        return ResourceContext.NONE;
    }

    privbte ModifibbleClbssLobderRepository getInstbntibtorCLR() {
        return AccessController.doPrivileged(new PrivilegedAction<ModifibbleClbssLobderRepository>() {
            @Override
            public ModifibbleClbssLobderRepository run() {
                return instbntibtor != null ? instbntibtor.getClbssLobderRepository() : null;
            }
        });
    }
}
