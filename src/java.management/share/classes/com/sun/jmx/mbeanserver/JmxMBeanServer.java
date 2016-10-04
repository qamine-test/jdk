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

pbckbge com.sun.jmx.mbebnserver;

import com.sun.jmx.interceptor.DefbultMBebnServerInterceptor;
import com.sun.jmx.interceptor.MBebnServerInterceptor;
import stbtic com.sun.jmx.defbults.JmxProperties.MBEANSERVER_LOGGER;

import jbvb.io.ObjectInputStrebm;
import jbvb.security.AccessController;
import jbvb.security.Permission;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.List;
import jbvb.util.Set;
import jbvb.util.logging.Level;

import jbvbx.mbnbgement.Attribute;
import jbvbx.mbnbgement.AttributeList;
import jbvbx.mbnbgement.AttributeNotFoundException;
import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.IntrospectionException;
import jbvbx.mbnbgement.InvblidAttributeVblueException;
import jbvbx.mbnbgement.ListenerNotFoundException;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.MBebnPermission;
import jbvbx.mbnbgement.MBebnRegistrbtionException;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnServerDelegbte;
import jbvbx.mbnbgement.MBebnServerPermission;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.ObjectInstbnce;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.OperbtionsException;
import jbvbx.mbnbgement.QueryExp;
import jbvbx.mbnbgement.ReflectionException;
import jbvbx.mbnbgement.RuntimeOperbtionsException;
import jbvbx.mbnbgement.lobding.ClbssLobderRepository;

/**
 * This is the bbse clbss for MBebn mbnipulbtion on the bgent side. It
 * contbins the methods necessbry for the crebtion, registrbtion, bnd
 * deletion of MBebns bs well bs the bccess methods for registered MBebns.
 * This is the core component of the JMX infrbstructure.
 * <P>
 * Every MBebn which is bdded to the MBebn server becomes mbnbgebble:
 * its bttributes bnd operbtions become remotely bccessible through
 * the connectors/bdbptors connected to thbt MBebn server.
 * A Jbvb object cbnnot be registered in the MBebn server unless it is b
 * JMX complibnt MBebn.
 * <P>
 * When bn MBebn is registered or unregistered in the MBebn server bn
 * {@link jbvbx.mbnbgement.MBebnServerNotificbtion MBebnServerNotificbtion}
 * Notificbtion is emitted. To register bn object bs listener to
 * MBebnServerNotificbtions you should cbll the MBebn server method
 * {@link #bddNotificbtionListener bddNotificbtionListener} with
 * the <CODE>ObjectNbme</CODE> of the
 * {@link jbvbx.mbnbgement.MBebnServerDelegbte MBebnServerDelegbte}.
 * This <CODE>ObjectNbme</CODE> is:
 * <BR>
 * <CODE>JMImplementbtion:type=MBebnServerDelegbte</CODE>.
 *
 * @since 1.5
 */
public finbl clbss JmxMBebnServer
    implements SunJmxMBebnServer {

    /** Control the defbult locking policy of the repository.
     *  By defbult, we will be using b fbir locking policy.
     **/
    public stbtic finbl boolebn DEFAULT_FAIR_LOCK_POLICY = true;

    privbte finbl MBebnInstbntibtor instbntibtor;
    privbte finbl SecureClbssLobderRepository secureClr;

    /** true if interceptors bre enbbled **/
    privbte finbl boolebn interceptorsEnbbled;

    privbte finbl MBebnServer outerShell;

    privbte volbtile MBebnServer mbsInterceptor = null;

    /** The MBebnServerDelegbte object representing the MBebn Server */
    privbte finbl MBebnServerDelegbte mBebnServerDelegbteObject;

    /**
     * <b>Pbckbge:</b> Crebtes bn MBebnServer with the
     * specified defbult dombin nbme, outer interfbce, bnd delegbte.
     * <p>The defbult dombin nbme is used bs the dombin pbrt in the ObjectNbme
     * of MBebns if no dombin is specified by the user.
     * <ul><b>Note:</b>Using this constructor directly is strongly
     *     discourbged. You should use
     *     {@link jbvbx.mbnbgement.MBebnServerFbctory#crebteMBebnServer(jbvb.lbng.String)}
     *     or
     *     {@link jbvbx.mbnbgement.MBebnServerFbctory#newMBebnServer(jbvb.lbng.String)}
     *     instebd.
     *     <p>
     *     By defbult, interceptors bre disbbled. Use
     *     {@link #JmxMBebnServer(jbvb.lbng.String,jbvbx.mbnbgement.MBebnServer,jbvbx.mbnbgement.MBebnServerDelegbte,boolebn)} to enbble them.
     * </ul>
     * @pbrbm dombin The defbult dombin nbme used by this MBebnServer.
     * @pbrbm outer A pointer to the MBebnServer object thbt must be
     *        pbssed to the MBebns when invoking their
     *        {@link jbvbx.mbnbgement.MBebnRegistrbtion} interfbce.
     * @pbrbm delegbte A pointer to the MBebnServerDelegbte bssocibted
     *        with the new MBebnServer. The new MBebnServer must register
     *        this MBebn in its MBebn repository.
     * @exception IllegblArgumentException if the instbntibtor is null.
     */
    JmxMBebnServer(String dombin, MBebnServer outer,
                   MBebnServerDelegbte delegbte) {
        this(dombin,outer,delegbte,null,fblse);
    }

    /**
     * <b>Pbckbge:</b> Crebtes bn MBebnServer with the
     * specified defbult dombin nbme, outer interfbce, bnd delegbte.
     * <p>The defbult dombin nbme is used bs the dombin pbrt in the ObjectNbme
     * of MBebns if no dombin is specified by the user.
     * <ul><b>Note:</b>Using this constructor directly is strongly
     *     discourbged. You should use
     *     {@link jbvbx.mbnbgement.MBebnServerFbctory#crebteMBebnServer(jbvb.lbng.String)}
     *     or
     *     {@link jbvbx.mbnbgement.MBebnServerFbctory#newMBebnServer(jbvb.lbng.String)}
     *     instebd.
     * </ul>
     * @pbrbm dombin The defbult dombin nbme used by this MBebnServer.
     * @pbrbm outer A pointer to the MBebnServer object thbt must be
     *        pbssed to the MBebns when invoking their
     *        {@link jbvbx.mbnbgement.MBebnRegistrbtion} interfbce.
     * @pbrbm delegbte A pointer to the MBebnServerDelegbte bssocibted
     *        with the new MBebnServer. The new MBebnServer must register
     *        this MBebn in its MBebn repository.
     * @pbrbm interceptors If <code>true</code>,
     *        {@link MBebnServerInterceptor} will be enbbled (defbult is
     *        <code>fblse</code>)
     *        Note: this pbrbmeter is not tbken into bccount by this
     *        implementbtion - the defbult vblue <code>fblse</code> is
     *        blwbys used.
     * @exception IllegblArgumentException if the instbntibtor is null.
     */
    JmxMBebnServer(String dombin, MBebnServer outer,
                   MBebnServerDelegbte delegbte, boolebn interceptors) {
        this(dombin,outer,delegbte,null,fblse);
    }

    /**
     * <b>Pbckbge:</b> Crebtes bn MBebnServer.
     * @pbrbm dombin The defbult dombin nbme used by this MBebnServer.
     * @pbrbm outer A pointer to the MBebnServer object thbt must be
     *        pbssed to the MBebns when invoking their
     *        {@link jbvbx.mbnbgement.MBebnRegistrbtion} interfbce.
     * @pbrbm delegbte A pointer to the MBebnServerDelegbte bssocibted
     *        with the new MBebnServer. The new MBebnServer must register
     *        this MBebn in its MBebn repository.
     * @pbrbm instbntibtor The MBebnInstbntibtor thbt will be used to
     *        instbntibte MBebns bnd tbke cbre of clbss lobding issues.
     * @pbrbm metbdbtb The MetbDbtb object thbt will be used by the
     *        MBebn server in order to invoke the MBebn interfbce of
     *        the registered MBebns.
     * @pbrbm interceptors If <code>true</code>,
     *        {@link MBebnServerInterceptor} will be enbbled (defbult is
     *        <code>fblse</code>).
     */
    JmxMBebnServer(String dombin, MBebnServer outer,
                   MBebnServerDelegbte    delegbte,
                   MBebnInstbntibtor      instbntibtor,
                   boolebn                interceptors)  {
                   this(dombin,outer,delegbte,instbntibtor,interceptors,true);
    }

    /**
     * <b>Pbckbge:</b> Crebtes bn MBebnServer.
     * @pbrbm dombin The defbult dombin nbme used by this MBebnServer.
     * @pbrbm outer A pointer to the MBebnServer object thbt must be
     *        pbssed to the MBebns when invoking their
     *        {@link jbvbx.mbnbgement.MBebnRegistrbtion} interfbce.
     * @pbrbm delegbte A pointer to the MBebnServerDelegbte bssocibted
     *        with the new MBebnServer. The new MBebnServer must register
     *        this MBebn in its MBebn repository.
     * @pbrbm instbntibtor The MBebnInstbntibtor thbt will be used to
     *        instbntibte MBebns bnd tbke cbre of clbss lobding issues.
     * @pbrbm metbdbtb The MetbDbtb object thbt will be used by the
     *        MBebn server in order to invoke the MBebn interfbce of
     *        the registered MBebns.
     * @pbrbm interceptors If <code>true</code>,
     *        {@link MBebnServerInterceptor} will be enbbled (defbult is
     *        <code>fblse</code>).
     * @pbrbm fbirLock If {@code true}, the MBebn repository will use b {@link
     *        jbvb.util.concurrent.locks.ReentrbntRebdWriteLock#ReentrbntRebdWriteLock(boolebn)
     *        fbir locking} policy.
     */
    JmxMBebnServer(String dombin, MBebnServer outer,
                   MBebnServerDelegbte    delegbte,
                   MBebnInstbntibtor      instbntibtor,
                   boolebn                interceptors,
                   boolebn                fbirLock)  {

        if (instbntibtor == null) {
            finbl ModifibbleClbssLobderRepository
                clr = new ClbssLobderRepositorySupport();
            instbntibtor = new MBebnInstbntibtor(clr);
        }

        finbl MBebnInstbntibtor fInstbntibtor = instbntibtor;
        this.secureClr = new
            SecureClbssLobderRepository(AccessController.doPrivileged(new PrivilegedAction<ClbssLobderRepository>() {
                @Override
                public ClbssLobderRepository run() {
                    return fInstbntibtor.getClbssLobderRepository();
                }
            })
        );
        if (delegbte == null)
            delegbte = new MBebnServerDelegbteImpl();
        if (outer == null)
            outer = this;

        this.instbntibtor = instbntibtor;
        this.mBebnServerDelegbteObject = delegbte;
        this.outerShell   = outer;

        finbl Repository repository = new Repository(dombin);
        this.mbsInterceptor =
            new DefbultMBebnServerInterceptor(outer, delegbte, instbntibtor,
                                              repository);
        this.interceptorsEnbbled = interceptors;
        initiblize();
    }

    /**
     * Tell whether {@link MBebnServerInterceptor}s bre enbbled on this
     * object.
     * @return <code>true</code> if {@link MBebnServerInterceptor}s bre
     *         enbbled.
     * @see #newMBebnServer(jbvb.lbng.String,jbvbx.mbnbgement.MBebnServer,jbvbx.mbnbgement.MBebnServerDelegbte,boolebn)
     **/
    public boolebn interceptorsEnbbled() {
        return interceptorsEnbbled;
    }

    /**
     * Return the MBebnInstbntibtor bssocibted to this MBebnServer.
     * @exception UnsupportedOperbtionException if
     *            {@link MBebnServerInterceptor}s
     *            bre not enbbled on this object.
     * @see #interceptorsEnbbled
     **/
    public MBebnInstbntibtor getMBebnInstbntibtor() {
        if (interceptorsEnbbled) return instbntibtor;
        else throw new UnsupportedOperbtionException(
                       "MBebnServerInterceptors bre disbbled.");
    }

    /**
     * Instbntibtes bnd registers bn MBebn in the MBebn server.
     * The MBebn server will use its
     * {@link jbvbx.mbnbgement.lobding.ClbssLobderRepository Defbult Lobder Repository}
     * to lobd the clbss of the MBebn.
     * An object nbme is bssocibted to the MBebn.
     * If the object nbme given is null, the MBebn cbn butombticblly
     * provide its own nbme by implementing the
     * {@link jbvbx.mbnbgement.MBebnRegistrbtion MBebnRegistrbtion} interfbce.
     * The cbll returns bn <CODE>ObjectInstbnce</CODE> object representing
     * the newly crebted MBebn.
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     *
     * @return  An <CODE>ObjectInstbnce</CODE>, contbining the
     *     <CODE>ObjectNbme</CODE> bnd the Jbvb clbss nbme of the newly
     *     instbntibted MBebn.
     *
     * @exception ReflectionException Wrbps bn
     *     <CODE>{@link jbvb.lbng.ClbssNotFoundException}</CODE> or bn
     *     <CODE>{@link jbvb.lbng.Exception}</CODE> thbt occurred
     *     when trying to invoke the MBebn's constructor.
     * @exception InstbnceAlrebdyExistsException The MBebn is blrebdy
     *     under the control of the MBebn server.
     * @exception MBebnRegistrbtionException The <CODE>preRegister()</CODE>
     *     (<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn
     *     hbs thrown bn exception. The MBebn will not be registered.
     * @exception MBebnException The constructor of the MBebn hbs thrown
     *     bn exception.
     * @exception NotComplibntMBebnException This clbss is not b JMX
     *     complibnt MBebn.
     * @exception RuntimeOperbtionsException Wrbps bn
     *     <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>:
     *     The clbssNbme pbssed in pbrbmeter is null, the
     *     <CODE>ObjectNbme</CODE> pbssed in pbrbmeter contbins b pbttern
     *     or no <CODE>ObjectNbme</CODE> is specified for the MBebn.
     *
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme)
        throws ReflectionException, InstbnceAlrebdyExistsException,
               MBebnRegistrbtionException, MBebnException,
               NotComplibntMBebnException {

        return mbsInterceptor.crebteMBebn(clbssNbme,
                                          cloneObjectNbme(nbme),
                                          (Object[]) null,
                                          (String[]) null);
    }

    /**
     * Instbntibtes bnd registers bn MBebn in the MBebn server.
     * The clbss lobder to be used is identified by its object  nbme.
     * An object nbme is bssocibted to the MBebn.
     * If the object nbme  of the lobder is null, the ClbssLobder thbt
     * lobded the MBebn server will be used.
     * If the MBebn's object nbme given is null, the MBebn cbn
     * butombticblly provide its own nbme by implementing the
     * {@link jbvbx.mbnbgement.MBebnRegistrbtion MBebnRegistrbtion} interfbce.
     * The cbll returns bn <CODE>ObjectInstbnce</CODE> object representing
     * the newly crebted MBebn.
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     * @pbrbm lobderNbme The object nbme of the clbss lobder to be used.
     *
     * @return  An <CODE>ObjectInstbnce</CODE>, contbining the
     *     <CODE>ObjectNbme</CODE> bnd the Jbvb clbss nbme
     *     of the newly instbntibted MBebn.
     *
     * @exception ReflectionException  Wrbps bn
     *     <CODE>{@link jbvb.lbng.ClbssNotFoundException}</CODE> or bn
     *     <CODE>{@link jbvb.lbng.Exception}</CODE> thbt occurred when trying
     *     to invoke the MBebn's constructor.
     * @exception InstbnceAlrebdyExistsException The MBebn is blrebdy
     *     under the control of the MBebn server.
     * @exception MBebnRegistrbtionException The <CODE>preRegister()</CODE>
     *     (<CODE>MBebnRegistrbtion</CODE>  interfbce) method of the MBebn
     *     hbs thrown bn exception. The MBebn will not be registered.
     * @exception MBebnException The constructor of the MBebn hbs thrown
     *     bn exception
     * @exception NotComplibntMBebnException This clbss is not b JMX
     *     complibnt MBebn.
     * @exception InstbnceNotFoundException The specified clbss lobder
     *     is not registered in the MBebn server.
     * @exception RuntimeOperbtionsException Wrbps bn
     *     <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>: The
     *     clbssNbme pbssed in pbrbmeter is null, the <CODE>ObjectNbme</CODE>
     *     pbssed in pbrbmeter contbins b pbttern or no
     *     <CODE>ObjectNbme</CODE> is specified for the MBebn.
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                      ObjectNbme lobderNbme)
        throws ReflectionException, InstbnceAlrebdyExistsException,
               MBebnRegistrbtionException, MBebnException,
               NotComplibntMBebnException, InstbnceNotFoundException {

        return mbsInterceptor.crebteMBebn(clbssNbme,
                                          cloneObjectNbme(nbme),
                                          lobderNbme,
                                          (Object[]) null,
                                          (String[]) null);
    }

    /**
     * Instbntibtes bnd registers bn MBebn in the MBebn server.
     * The MBebn server will use its
     * {@link jbvbx.mbnbgement.lobding.ClbssLobderRepository Defbult Lobder Repository}
     * to lobd the clbss of the MBebn.
     * An object nbme is bssocibted to the MBebn.
     * If the object nbme given is null, the MBebn cbn butombticblly
     * provide its own nbme by implementing the
     * {@link jbvbx.mbnbgement.MBebnRegistrbtion MBebnRegistrbtion} interfbce.
     * The cbll returns bn <CODE>ObjectInstbnce</CODE> object representing
     * the newly crebted MBebn.
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters of the constructor
     *     to be invoked.
     * @pbrbm signbture An brrby contbining the signbture of the
     *     constructor to be invoked.
     *
     * @return  An <CODE>ObjectInstbnce</CODE>, contbining the
     *     <CODE>ObjectNbme</CODE> bnd the Jbvb clbss nbme
     *     of the newly instbntibted MBebn.
     *
     * @exception ReflectionException Wrbps b
     *     <CODE>{@link jbvb.lbng.ClbssNotFoundException}</CODE> or bn
     *     <CODE>{@link jbvb.lbng.Exception}</CODE> thbt occurred
     *     when trying to invoke the MBebn's constructor.
     * @exception InstbnceAlrebdyExistsException The MBebn is blrebdy
     *     under the control of the MBebn server.
     * @exception MBebnRegistrbtionException The <CODE>preRegister()</CODE>
     *     (<CODE>MBebnRegistrbtion</CODE>  interfbce) method of the MBebn
     *     hbs thrown bn exception. The MBebn will not be registered.
     * @exception MBebnException The constructor of the MBebn hbs
     *     thrown bn exception.
     * @exception RuntimeOperbtionsException Wrbps bn
     *     <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>: The
     *     clbssNbme pbssed in pbrbmeter is null, the <CODE>ObjectNbme</CODE>
     *     pbssed in pbrbmeter contbins b pbttern or no
     *     <CODE>ObjectNbme</CODE> is specified for the MBebn.
     *
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                      Object pbrbms[], String signbture[])
        throws ReflectionException, InstbnceAlrebdyExistsException,
               MBebnRegistrbtionException, MBebnException,
               NotComplibntMBebnException  {

        return mbsInterceptor.crebteMBebn(clbssNbme, cloneObjectNbme(nbme),
                                          pbrbms, signbture);
    }

   /**
     * Instbntibtes bnd registers bn MBebn in the MBebn server.
     * The clbss lobder to be used is identified by its object nbme.
     * An object nbme is bssocibted to the MBebn. If the object nbme
     * of the lobder is not specified, the ClbssLobder thbt lobded the
     * MBebn server will be used.
     * If  the MBebn object nbme given is null, the MBebn cbn butombticblly
     * provide its own nbme by implementing the
     * {@link jbvbx.mbnbgement.MBebnRegistrbtion MBebnRegistrbtion} interfbce.
     * The cbll returns bn <CODE>ObjectInstbnce</CODE> object representing
     * the newly crebted MBebn.
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters of the constructor
     *      to be invoked.
     * @pbrbm signbture An brrby contbining the signbture of the
     *     constructor to be invoked.
     * @pbrbm lobderNbme The object nbme of the clbss lobder to be used.
     *
     * @return  An <CODE>ObjectInstbnce</CODE>, contbining the
     *     <CODE>ObjectNbme</CODE> bnd the Jbvb clbss nbme of the newly
     *     instbntibted MBebn.
     *
     * @exception ReflectionException Wrbps b
     *     <CODE>{@link jbvb.lbng.ClbssNotFoundException}</CODE> or bn
     *     <CODE>{@link jbvb.lbng.Exception}</CODE>
     *     thbt occurred when trying to invoke the MBebn's constructor.
     * @exception InstbnceAlrebdyExistsException The MBebn is blrebdy
     *     under the control of the MBebn server.
     * @exception MBebnRegistrbtionException The <CODE>preRegister()</CODE>
     *     (<CODE>MBebnRegistrbtion</CODE>  interfbce) method of the MBebn
     *     hbs thrown bn exception. The MBebn will not be registered.
     * @exception MBebnException The constructor of the MBebn hbs
     *      thrown bn exception
     * @exception InstbnceNotFoundException The specified clbss lobder is
     *      not registered in the MBebn server.
     * @exception RuntimeOperbtionsException Wrbps bn
     *     <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>: The
     *     clbssNbme pbssed in pbrbmeter is null, the <CODE>ObjectNbme</CODE>
     *     pbssed in pbrbmeter contbins b pbttern or no
     *     <CODE>ObjectNbme</CODE> is specified for the MBebn.
     *
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                      ObjectNbme lobderNbme, Object pbrbms[],
                                      String signbture[])
        throws ReflectionException, InstbnceAlrebdyExistsException,
               MBebnRegistrbtionException, MBebnException,
               NotComplibntMBebnException, InstbnceNotFoundException {

        return mbsInterceptor.crebteMBebn(clbssNbme, cloneObjectNbme(nbme),
                                          lobderNbme, pbrbms, signbture);
    }

    /**
     * Registers b pre-existing object bs bn MBebn with the MBebn server.
     * If the object nbme given is null, the MBebn mby butombticblly
     * provide its own nbme by implementing the
     * {@link jbvbx.mbnbgement.MBebnRegistrbtion MBebnRegistrbtion}  interfbce.
     * The cbll returns bn <CODE>ObjectInstbnce</CODE> object representing
     * the registered MBebn.
     *
     * @pbrbm object The  MBebn to be registered bs bn MBebn.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     *
     * @return The <CODE>ObjectInstbnce</CODE> for the MBebn thbt hbs been
     *      registered.
     *
     * @exception InstbnceAlrebdyExistsException The MBebn is blrebdy
     *      under the control of the MBebn server.
     * @exception MBebnRegistrbtionException The <CODE>preRegister()</CODE>
     *      (<CODE>MBebnRegistrbtion</CODE>  interfbce) method of the MBebn
     *      hbs thrown bn exception. The MBebn will not be registered.
     * @exception NotComplibntMBebnException This object is not b JMX
     *      complibnt MBebn
     * @exception RuntimeOperbtionsException Wrbps bn
     *      <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>: The
     *      object pbssed in pbrbmeter is null or no object nbme is specified.
     *
     */
    public ObjectInstbnce registerMBebn(Object object, ObjectNbme nbme)
        throws InstbnceAlrebdyExistsException, MBebnRegistrbtionException,
               NotComplibntMBebnException  {

        return mbsInterceptor.registerMBebn(object, cloneObjectNbme(nbme));
    }

    /**
     * De-registers bn MBebn from the MBebn server. The MBebn is identified by
     * its object nbme. Once the method hbs been invoked, the MBebn mby
     * no longer be bccessed by its object nbme.
     *
     * @pbrbm nbme The object nbme of the MBebn to be de-registered.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     *     registered in the MBebn server.
     * @exception MBebnRegistrbtionException The <code>preDeregister()</code>
     *     (<CODE>MBebnRegistrbtion</CODE>  interfbce) method of the MBebn
     *     hbs thrown bn exception.
     * @exception RuntimeOperbtionsException Wrbps bn
     *     <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>: The
     *     object nbme in pbrbmeter is null or the MBebn you bre when
     *     trying to de-register is the
     *     {@link jbvbx.mbnbgement.MBebnServerDelegbte MBebnServerDelegbte}
     *     MBebn.
     **/
    public void unregisterMBebn(ObjectNbme nbme)
        throws InstbnceNotFoundException, MBebnRegistrbtionException  {
        mbsInterceptor.unregisterMBebn(cloneObjectNbme(nbme));
    }

    /**
     * Gets the <CODE>ObjectInstbnce</CODE> for b given MBebn registered
     * with the MBebn server.
     *
     * @pbrbm nbme The object nbme of the MBebn.
     *
     * @return The <CODE>ObjectInstbnce</CODE> bssocibted to the MBebn
     *       specified by <VAR>nbme</VAR>.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     *       registered in the MBebn server.
     */
    public ObjectInstbnce getObjectInstbnce(ObjectNbme nbme)
        throws InstbnceNotFoundException {

        return mbsInterceptor.getObjectInstbnce(cloneObjectNbme(nbme));
    }

    /**
     * Gets MBebns controlled by the MBebn server. This method bllows bny
     * of the following to be obtbined: All MBebns, b set of MBebns specified
     * by pbttern mbtching on the <CODE>ObjectNbme</CODE> bnd/or b Query
     * expression, b specific MBebn. When the object nbme is null or no
     * dombin bnd key properties bre specified, bll objects bre to be
     * selected (bnd filtered if b query is specified). It returns the
     * set of <CODE>ObjectInstbnce</CODE> objects (contbining the
     * <CODE>ObjectNbme</CODE> bnd the Jbvb Clbss nbme) for
     * the selected MBebns.
     *
     * @pbrbm nbme The object nbme pbttern identifying the MBebns to
     *      be retrieved. If null or or no dombin bnd key properties
     *      bre specified, bll the MBebns registered will be retrieved.
     * @pbrbm query The query expression to be bpplied for selecting
     *      MBebns. If null no query expression will be bpplied for
     *      selecting MBebns.
     *
     * @return  A set contbining the <CODE>ObjectInstbnce</CODE> objects
     *      for the selected MBebns.
     *      If no MBebn sbtisfies the query bn empty list is returned.
     *
     */
    public Set<ObjectInstbnce> queryMBebns(ObjectNbme nbme, QueryExp query) {

        return mbsInterceptor.queryMBebns(cloneObjectNbme(nbme), query);
    }

    /**
     * Gets the nbmes of MBebns controlled by the MBebn server. This method
     * enbbles bny of the following to be obtbined: The nbmes of bll MBebns,
     * the nbmes of b set of MBebns specified by pbttern mbtching on the
     * <CODE>ObjectNbme</CODE> bnd/or b Query expression, b specific
     * MBebn nbme (equivblent to testing whether bn MBebn is registered).
     * When the object nbme is null or or no dombin bnd key properties bre
     * specified, bll objects bre selected (bnd filtered if b query is
     * specified). It returns the set of ObjectNbmes for the MBebns
     * selected.
     *
     * @pbrbm nbme The object nbme pbttern identifying the MBebns to be
     *     retrieved. If null or no dombin bnd key properties bre
     *     specified, bll the MBebns registered will be retrieved.
     * @pbrbm query The query expression to be bpplied for selecting
     *     MBebns. If null no query expression will be bpplied for
     *     selecting MBebns.
     *
     * @return  A set contbining the ObjectNbmes for the MBebns selected.
     *     If no MBebn sbtisfies the query, bn empty list is returned.
     *
     */
    public Set<ObjectNbme> queryNbmes(ObjectNbme nbme, QueryExp query) {

        return mbsInterceptor.queryNbmes(cloneObjectNbme(nbme), query);
    }

    /**
     * Checks whether bn MBebn, identified by its object nbme, is blrebdy
     * registered with the MBebn server.
     *
     * @pbrbm nbme The object nbme of the MBebn to be checked.
     *
     * @return  True if the MBebn is blrebdy registered in the MBebn server,
     *     fblse otherwise.
     *
     * @exception RuntimeOperbtionsException Wrbps bn
     *     <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>: The object
     *      nbme in pbrbmeter is null.
     *
     */
    public boolebn isRegistered(ObjectNbme nbme)  {

        return mbsInterceptor.isRegistered(nbme);
    }

    /**
     * Returns the number of MBebns registered in the MBebn server.
     */
    public Integer getMBebnCount()  {

        return mbsInterceptor.getMBebnCount();
    }

    /**
     * Gets the vblue of b specific bttribute of b nbmed MBebn. The MBebn
     * is identified by its object nbme.
     *
     * @pbrbm nbme The object nbme of the MBebn from which the bttribute
     *     is to be retrieved.
     * @pbrbm bttribute A String specifying the nbme of the bttribute to be
     *     retrieved.
     *
     * @return  The vblue of the retrieved bttribute.
     *
     * @exception AttributeNotFoundException The bttribute specified
     *     is not bccessible in the MBebn.
     * @exception MBebnException  Wrbps bn exception thrown by the
     *     MBebn's getter.
     * @exception InstbnceNotFoundException The MBebn specified is not
     *     registered in the MBebn server.
     * @exception ReflectionException  Wrbps bn
     *     <CODE>{@link jbvb.lbng.Exception}</CODE> thrown when trying to
     *     invoke the setter.
     * @exception RuntimeOperbtionsException Wrbps bn
     *     <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>:
     *     The object nbme in pbrbmeter is null or the bttribute in
     *     pbrbmeter is null.
     */
    public Object getAttribute(ObjectNbme nbme, String bttribute)
        throws MBebnException, AttributeNotFoundException,
               InstbnceNotFoundException, ReflectionException {

        return mbsInterceptor.getAttribute(cloneObjectNbme(nbme), bttribute);
    }


    /**
     * Enbbles the vblues of severbl bttributes of b nbmed MBebn. The MBebn
     * is identified by its object nbme.
     *
     * @pbrbm nbme The object nbme of the MBebn from which the bttributes bre
     *     retrieved.
     * @pbrbm bttributes A list of the bttributes to be retrieved.
     *
     * @return The list of the retrieved bttributes.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     *     registered in the MBebn server.
     * @exception ReflectionException An exception occurred when trying
     *     to invoke the getAttributes method of b Dynbmic MBebn.
     * @exception RuntimeOperbtionsException Wrbp bn
     *     <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>: The
     *     object nbme in pbrbmeter is null or bttributes in pbrbmeter
     *     is null.
     *
     */
    public AttributeList getAttributes(ObjectNbme nbme, String[] bttributes)
        throws InstbnceNotFoundException, ReflectionException  {

        return mbsInterceptor.getAttributes(cloneObjectNbme(nbme), bttributes);

    }

    /**
     * Sets the vblue of b specific bttribute of b nbmed MBebn. The MBebn
     * is identified by its object nbme.
     *
     * @pbrbm nbme The nbme of the MBebn within which the bttribute is
     *     to be set.
     * @pbrbm bttribute The identificbtion of the bttribute to be set
     *     bnd the vblue it is to be set to.
     *
     * @exception InstbnceNotFoundException The MBebn specified is
     *     not registered in the MBebn server.
     * @exception AttributeNotFoundException The bttribute specified is
     *     not bccessible in the MBebn.
     * @exception InvblidAttributeVblueException The vblue specified for
     *     the bttribute is not vblid.
     * @exception MBebnException Wrbps bn exception thrown by the
     *     MBebn's setter.
     * @exception ReflectionException  Wrbps bn
     *     <CODE>{@link jbvb.lbng.Exception}</CODE> thrown when trying
     *     to invoke the setter.
     * @exception RuntimeOperbtionsException Wrbps bn
     *     <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>: The
     *     object nbme in pbrbmeter is null or the bttribute in pbrbmeter
     *     is null.
     */
    public void setAttribute(ObjectNbme nbme, Attribute bttribute)
        throws InstbnceNotFoundException, AttributeNotFoundException,
               InvblidAttributeVblueException, MBebnException,
               ReflectionException  {

        mbsInterceptor.setAttribute(cloneObjectNbme(nbme),
                                    cloneAttribute(bttribute));
    }

    /**
     * Sets the vblues of severbl bttributes of b nbmed MBebn. The MBebn is
     * identified by its object nbme.
     *
     * @pbrbm nbme The object nbme of the MBebn within which the
     *     bttributes bre to  be set.
     * @pbrbm bttributes A list of bttributes: The identificbtion of the
     *     bttributes to be set bnd  the vblues they bre to be set to.
     *
     * @return  The list of bttributes thbt were set, with their new vblues.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     *      registered in the MBebn server.
     * @exception ReflectionException An exception occurred when trying
     *      to invoke the getAttributes method of b Dynbmic MBebn.
     * @exception RuntimeOperbtionsException Wrbps bn
     *      <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>:
     *     The object nbme in pbrbmeter is null or  bttributes in
     *     pbrbmeter is null.
     *
     */
    public AttributeList setAttributes(ObjectNbme nbme,
                                       AttributeList bttributes)
        throws InstbnceNotFoundException, ReflectionException  {

        return mbsInterceptor.setAttributes(cloneObjectNbme(nbme),
                                            cloneAttributeList(bttributes));
    }

    /**
     * Invokes bn operbtion on bn MBebn.
     *
     * @pbrbm nbme The object nbme of the MBebn on which the method is to be
     *     invoked.
     * @pbrbm operbtionNbme The nbme of the operbtion to be invoked.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters to be set when
     *     the operbtion is invoked
     * @pbrbm signbture An brrby contbining the signbture of the operbtion.
     *     The clbss objects will be lobded using the sbme clbss lobder bs
     *     the one used for lobding the MBebn on which the operbtion wbs
     *     invoked.
     *
     * @return  The object returned by the operbtion, which represents the
     *      result ofinvoking the operbtion on the  MBebn specified.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     *       registered in the MBebn server.
     * @exception MBebnException  Wrbps bn exception thrown by the MBebn's
     *       invoked method.
     * @exception ReflectionException  Wrbps bn
     *       <CODE>{@link jbvb.lbng.Exception}</CODE> thrown while trying
     *        to invoke the method.
     *
     */
    public Object invoke(ObjectNbme nbme, String operbtionNbme,
                         Object pbrbms[], String signbture[])
        throws InstbnceNotFoundException, MBebnException,
               ReflectionException {
        return mbsInterceptor.invoke(cloneObjectNbme(nbme), operbtionNbme,
                                     pbrbms, signbture);
    }

    /**
     * Returns the defbult dombin used for nbming the MBebn.
     * The defbult dombin nbme is used bs the dombin pbrt in the ObjectNbme
     * of MBebns if no dombin is specified by the user.
     */
    public String getDefbultDombin()  {
        return mbsInterceptor.getDefbultDombin();
    }

    // From MBebnServer
    public String[] getDombins() {
        return mbsInterceptor.getDombins();
    }

    /**
     * Adds b listener to b registered MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the listener should be bdded.
     * @pbrbm listener The listener object which will hbndle the
     *        notificbtions emitted by the registered MBebn.
     * @pbrbm filter The filter object. If filter is null, no filtering
     *        will be performed before hbndling notificbtions.
     * @pbrbm hbndbbck The context to be sent to the listener when b
     *        notificbtion is emitted.
     *
     * @exception InstbnceNotFoundException The MBebn nbme provided does
     *       not mbtch bny of the registered MBebns.
     */
    public void bddNotificbtionListener(ObjectNbme nbme,
                                        NotificbtionListener listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck)
        throws InstbnceNotFoundException {

        mbsInterceptor.bddNotificbtionListener(cloneObjectNbme(nbme), listener,
                                               filter, hbndbbck);
    }

    /**
     * Adds b listener to b registered MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the listener should be bdded.
     * @pbrbm listener The object nbme of the listener which will hbndle the
     *        notificbtions emitted by the registered MBebn.
     * @pbrbm filter The filter object. If filter is null, no filtering will
     *        be performed before hbndling notificbtions.
     * @pbrbm hbndbbck The context to be sent to the listener when b
     *        notificbtion is emitted.
     *
     * @exception InstbnceNotFoundException The MBebn nbme of the
     *       notificbtion listener or of the notificbtion brobdcbster
     *       does not mbtch bny of the registered MBebns.
     */
    public void bddNotificbtionListener(ObjectNbme nbme, ObjectNbme listener,
                                   NotificbtionFilter filter, Object hbndbbck)
        throws InstbnceNotFoundException {

        mbsInterceptor.bddNotificbtionListener(cloneObjectNbme(nbme), listener,
                                               filter, hbndbbck);
    }

    public void removeNotificbtionListener(ObjectNbme nbme,
                                           NotificbtionListener listener)
            throws InstbnceNotFoundException, ListenerNotFoundException {

        mbsInterceptor.removeNotificbtionListener(cloneObjectNbme(nbme),
                                                  listener);
    }

    public void removeNotificbtionListener(ObjectNbme nbme,
                                           NotificbtionListener listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
            throws InstbnceNotFoundException, ListenerNotFoundException {

        mbsInterceptor.removeNotificbtionListener(cloneObjectNbme(nbme),
                                                  listener, filter, hbndbbck);
    }

    public void removeNotificbtionListener(ObjectNbme nbme,
                                           ObjectNbme listener)
        throws InstbnceNotFoundException, ListenerNotFoundException {

        mbsInterceptor.removeNotificbtionListener(cloneObjectNbme(nbme),
                                                  listener);
    }

    public void removeNotificbtionListener(ObjectNbme nbme,
                                           ObjectNbme listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
            throws InstbnceNotFoundException, ListenerNotFoundException {

        mbsInterceptor.removeNotificbtionListener(cloneObjectNbme(nbme),
                                                  listener, filter, hbndbbck);
    }

    /**
     * This method discovers the bttributes bnd operbtions thbt bn MBebn exposes
     * for mbnbgement.
     *
     * @pbrbm nbme The nbme of the MBebn to bnblyze
     *
     * @return  An instbnce of <CODE>MBebnInfo</CODE> bllowing the retrievbl of
     * bll bttributes bnd operbtions of this MBebn.
     *
     * @exception IntrospectionException An exception occurs during
     * introspection.
     * @exception InstbnceNotFoundException The MBebn specified is not found.
     * @exception ReflectionException An exception occurred when trying to
     * invoke the getMBebnInfo of b Dynbmic MBebn.
     */
    public MBebnInfo getMBebnInfo(ObjectNbme nbme) throws
    InstbnceNotFoundException, IntrospectionException, ReflectionException {

        return mbsInterceptor.getMBebnInfo(cloneObjectNbme(nbme));
    }

    /**
     * Instbntibtes bn object using the list of bll clbss lobders registered
     * in the MBebn server (using its
     * {@link jbvbx.mbnbgement.lobding.ClbssLobderRepository Defbult Lobder Repository}).
     * The object's clbss should hbve b public constructor.
     * It returns b reference to the newly crebted object.
     * The newly crebted object is not registered in the MBebn server.
     *
     * @pbrbm clbssNbme The clbss nbme of the object to be instbntibted.
     *
     * @return The newly instbntibted object.
     *
     * @exception ReflectionException Wrbps the
     *     <CODE>{@link jbvb.lbng.ClbssNotFoundException}</CODE> or the
     *     <CODE>{@link jbvb.lbng.Exception}</CODE> thbt
     *     occurred when trying to invoke the object's constructor.
     * @exception MBebnException The constructor of the object hbs thrown
     *     bn exception.
     * @exception RuntimeOperbtionsException Wrbps bn
     *     <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>:
     *     The clbssNbme pbssed in pbrbmeter is null.
     *
     */
    public Object instbntibte(String clbssNbme)
        throws ReflectionException, MBebnException {

        /* Permission check */
        checkMBebnPermission(clbssNbme, null, null, "instbntibte");

        return instbntibtor.instbntibte(clbssNbme);
    }

    /**
     * Instbntibtes bn object using the clbss Lobder specified by its
     * <CODE>ObjectNbme</CODE>.
     * If the lobder nbme is null, the ClbssLobder thbt lobded the
     * MBebn Server will be used.
     * The object's clbss should hbve b public constructor.
     * It returns b reference to the newly crebted object.
     * The newly crebted object is not registered in the MBebn server.
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm lobderNbme The object nbme of the clbss lobder to be used.
     *
     * @return The newly instbntibted object.
     *
     * @exception ReflectionException Wrbps the
     *     <CODE>{@link jbvb.lbng.ClbssNotFoundException}</CODE> or the
     *     <CODE>{@link jbvb.lbng.Exception}</CODE> thbt
     *     occurred when trying to invoke the object's constructor.
     * @exception MBebnException The constructor of the object hbs thrown
     *     bn exception.
     * @exception InstbnceNotFoundException The specified clbss lobder
     *     is not registered in the MBbenServer.
     * @exception RuntimeOperbtionsException Wrbps bn
     *     <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>: The
     *     clbssNbme pbssed in pbrbmeter is null.
     *
     */
    public Object instbntibte(String clbssNbme, ObjectNbme lobderNbme)
        throws ReflectionException, MBebnException,
               InstbnceNotFoundException {

        /* Permission check */
        checkMBebnPermission(clbssNbme, null, null, "instbntibte");

        ClbssLobder myLobder = outerShell.getClbss().getClbssLobder();
        return instbntibtor.instbntibte(clbssNbme, lobderNbme, myLobder);
    }

    /**
     * Instbntibtes bn object using the list of bll clbss lobders registered
     * in the MBebn server (using its
     * {@link jbvbx.mbnbgement.lobding.ClbssLobderRepository Defbult Lobder Repository}).
     * The object's clbss should hbve b public constructor.
     * The cbll returns b reference to the newly crebted object.
     * The newly crebted object is not registered in the MBebn server.
     *
     * @pbrbm clbssNbme The clbss nbme of the object to be instbntibted.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters of the constructor
     *     to be invoked.
     * @pbrbm signbture An brrby contbining the signbture of the
     *     constructor to be invoked.
     *
     * @return The newly instbntibted object.
     *
     * @exception ReflectionException Wrbps the
     *     <CODE>{@link jbvb.lbng.ClbssNotFoundException}</CODE> or the
     *     <CODE>{@link jbvb.lbng.Exception}</CODE> thbt
     *     occurred when trying to invoke the object's constructor.
     * @exception MBebnException The constructor of the object hbs thrown
     *     bn exception.
     * @exception RuntimeOperbtionsException Wrbps bn
     *     <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>:
     *     The clbssNbme pbssed in pbrbmeter is null.
     *
     */
    public Object instbntibte(String clbssNbme, Object pbrbms[],
                              String signbture[])
        throws ReflectionException, MBebnException {

        /* Permission check */
        checkMBebnPermission(clbssNbme, null, null, "instbntibte");

        ClbssLobder myLobder = outerShell.getClbss().getClbssLobder();
        return instbntibtor.instbntibte(clbssNbme, pbrbms, signbture,
                                        myLobder);
    }

    /**
     * Instbntibtes bn object. The clbss lobder to be used is identified
     * by its object nbme. If the object nbme of the lobder is null,
     * the ClbssLobder thbt lobded the MBebn server will be used.
     * The object's clbss should hbve b public constructor.
     * The cbll returns b reference to the newly crebted object.
     * The newly crebted object is not registered in the MBebn server.
     *
     * @pbrbm clbssNbme The clbss nbme of the object to be instbntibted.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters of the constructor
     *     to be invoked.
     * @pbrbm signbture An brrby contbining the signbture of the constructor
     *     to be invoked.
     * @pbrbm lobderNbme The object nbme of the clbss lobder to be used.
     *
     * @return The newly instbntibted object.
     *
     * @exception ReflectionException Wrbps the
     *    <CODE>{@link jbvb.lbng.ClbssNotFoundException}</CODE> or the
     *    <CODE>{@link jbvb.lbng.Exception}</CODE> thbt
     *    occurred when trying to invoke the object's constructor.
     * @exception MBebnException The constructor of the object hbs thrown
     *    bn exception.
     * @exception InstbnceNotFoundException The specified clbss lobder
     *    is not registered in the MBebn server.
     * @exception RuntimeOperbtionsException Wrbps bn
     *    <CODE>{@link jbvb.lbng.IllegblArgumentException}</CODE>:
     *    The clbssNbme pbssed in pbrbmeter is null.
     *
     */
    public Object instbntibte(String clbssNbme, ObjectNbme lobderNbme,
                              Object pbrbms[], String signbture[])
        throws ReflectionException, MBebnException,
               InstbnceNotFoundException {

        /* Permission check */
        checkMBebnPermission(clbssNbme, null, null, "instbntibte");

        ClbssLobder myLobder = outerShell.getClbss().getClbssLobder();
        return instbntibtor.instbntibte(clbssNbme,lobderNbme,pbrbms,signbture,
                                        myLobder);
    }

    /**
     * Returns true if the MBebn specified is bn instbnce of the specified
     * clbss, fblse otherwise.
     *
     * @pbrbm nbme The <CODE>ObjectNbme</CODE> of the MBebn.
     * @pbrbm clbssNbme The nbme of the clbss.
     *
     * @return true if the MBebn specified is bn instbnce of the specified
     *     clbss, fblse otherwise.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     *     registered in the MBebn server.
     */
    public boolebn isInstbnceOf(ObjectNbme nbme, String clbssNbme)
        throws InstbnceNotFoundException {

        return mbsInterceptor.isInstbnceOf(cloneObjectNbme(nbme), clbssNbme);
    }

    /**
     * De-seriblizes b byte brrby in the context of the clbss lobder
     * of bn MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn whose clbss lobder should
     *     be used for the de-seriblizbtion.
     * @pbrbm dbtb The byte brrby to be de-sereriblized.
     *
     * @return  The de-seriblized object strebm.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     *     found.
     * @exception OperbtionsException Any of the usubl Input/Output
     *     relbted exceptions.
     *
     */
    @Deprecbted
    public ObjectInputStrebm deseriblize(ObjectNbme nbme, byte[] dbtb)
        throws InstbnceNotFoundException, OperbtionsException {

        /* Permission check */
        // This cbll requires MBebnPermission 'getClbssLobderFor'
        finbl ClbssLobder lobder = getClbssLobderFor(nbme);

        return instbntibtor.deseriblize(lobder, dbtb);
    }

    /**
     * De-seriblizes b byte brrby in the context of b given MBebn clbss lobder.
     * The clbss lobder is the one thbt lobded the clbss with nbme "clbssNbme".
     *
     * @pbrbm clbssNbme The nbme of the clbss whose clbss lobder should be
     *      used for the de-seriblizbtion.
     * @pbrbm dbtb The byte brrby to be de-sereriblized.
     *
     * @return  The de-seriblized object strebm.
     *
     * @exception OperbtionsException Any of the usubl Input/Output
     *      relbted exceptions.
     * @exception ReflectionException The specified clbss could not be
     *      lobded by the defbult lobder repository
     *
     */
    @Deprecbted
    public ObjectInputStrebm deseriblize(String clbssNbme, byte[] dbtb)
        throws OperbtionsException, ReflectionException {

        if (clbssNbme == null) {
            throw new  RuntimeOperbtionsException(
                                        new IllegblArgumentException(),
                                        "Null clbssNbme pbssed in pbrbmeter");
        }

        /* Permission check */
        // This cbll requires MBebnPermission 'getClbssLobderRepository'
        finbl ClbssLobderRepository clr = getClbssLobderRepository();

        Clbss<?> theClbss;
        try {
            if (clr == null) throw new ClbssNotFoundException(clbssNbme);
            theClbss = clr.lobdClbss(clbssNbme);
        } cbtch (ClbssNotFoundException e) {
            throw new ReflectionException(e,
                                          "The given clbss could not be " +
                                          "lobded by the defbult lobder " +
                                          "repository");
        }

        return instbntibtor.deseriblize(theClbss.getClbssLobder(), dbtb);
    }

    /**
     * De-seriblizes b byte brrby in the context of b given MBebn clbss lobder.
     * The clbss lobder is the one thbt lobded the clbss with nbme "clbssNbme".
     * The nbme of the clbss lobder to be used for lobding the specified
     * clbss is specified.
     * If null, the MBebn Server's clbss lobder will be used.
     *
     * @pbrbm clbssNbme The nbme of the clbss whose clbss lobder should be
     *     used for the de-seriblizbtion.
     * @pbrbm dbtb The byte brrby to be de-sereriblized.
     * @pbrbm lobderNbme The nbme of the clbss lobder to be used for
     *     lobding the specified clbss.
     *     If null, the MBebn Server's clbss lobder will be used.
     *
     * @return  The de-seriblized object strebm.
     *
     * @exception InstbnceNotFoundException The specified clbss lobder
     *     MBebn is not found.
     * @exception OperbtionsException Any of the usubl Input/Output
     *     relbted exceptions.
     * @exception ReflectionException The specified clbss could not
     *     be lobded by the specified clbss lobder.
     *
     */
    @Deprecbted
    public ObjectInputStrebm deseriblize(String clbssNbme,
                                         ObjectNbme lobderNbme,
                                         byte[] dbtb) throws
        InstbnceNotFoundException, OperbtionsException, ReflectionException {

        // Clone ObjectNbme
        //
        lobderNbme = cloneObjectNbme(lobderNbme);

        /* Permission check */
        // Mbke this cbll just to force the 'getClbssLobder'
        // permission check
        try {
            getClbssLobder(lobderNbme);
        } cbtch (SecurityException e) {
            throw e;
        } cbtch (Exception e) {
        }

        ClbssLobder myLobder = outerShell.getClbss().getClbssLobder();
        return instbntibtor.deseriblize(clbssNbme, lobderNbme, dbtb, myLobder);
    }

    /**
     * Initiblizes this MBebnServer, registering the MBebnServerDelegbte.
     * <p>This method must be cblled once, before using the MBebnServer.
     **/
    privbte void initiblize() {
        if (instbntibtor == null) throw new
            IllegblStbteException("instbntibtor must not be null.");

        // Registers the MBebnServer identificbtion MBebn
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
                public Object run() throws Exception {
                    mbsInterceptor.registerMBebn(
                            mBebnServerDelegbteObject,
                            MBebnServerDelegbte.DELEGATE_NAME);
                    return null;
                }
            });
        } cbtch (SecurityException e) {
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINEST)) {
                MBEANSERVER_LOGGER.logp(Level.FINEST,
                        JmxMBebnServer.clbss.getNbme(), "initiblize",
                        "Unexpected security exception occurred", e);
            }
            throw e;
        } cbtch (Exception e) {
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINEST)) {
                MBEANSERVER_LOGGER.logp(Level.FINEST,
                        JmxMBebnServer.clbss.getNbme(), "initiblize",
                        "Unexpected exception occurred", e);
            }
            throw new
                IllegblStbteException("Cbn't register delegbte.",e);
        }


        /* Add my clbss lobder to the repository
           This cbn be null if my clbss lobder is the bootstrbp
           clbss lobder.  The ClbssLobderRepository knows how
           to hbndle thbt cbse.  */
        ClbssLobder myLobder = outerShell.getClbss().getClbssLobder();
        finbl ModifibbleClbssLobderRepository lobders = AccessController.doPrivileged(new PrivilegedAction<ModifibbleClbssLobderRepository>() {

            @Override
            public ModifibbleClbssLobderRepository run() {
                return instbntibtor.getClbssLobderRepository();
            }
        });

        if (lobders != null) {
            lobders.bddClbssLobder(myLobder);

            /* Add the system clbss lobder, so thbt if the MBebn server is
               lobded by the bootstrbp clbss lobder we cbn still lobd
               MBebns from the clbsspbth using
               crebteMBebn(clbssNbme, objectNbme).

               If this clbss (JmxMBebnServer) wbs not lobded by the
               system clbss lobder or b pbrent of it, then the cbller
               must hbve RuntimePermission("getClbssLobder") for the
               getSystemClbssLobder() cbll to succeed.  If the cbller
               does not hbve thbt permission, bny cbll to
               Clbss.getClbssLobder() will fbil.  Since there bre lots
               of those in JMX, we better throw the exception now.

               This permission question is irrelevbnt when JMX is pbrt
               of J2SE (bs of 1.5). */
            ClbssLobder systemLobder = ClbssLobder.getSystemClbssLobder();
            if (systemLobder != myLobder)
                lobders.bddClbssLobder(systemLobder);
        }
    }

    /**
     * Return the MBebnServerInterceptor.
     * @exception UnsupportedOperbtionException if
     *            {@link MBebnServerInterceptor}s
     *            bre not enbbled on this object.
     * @see #interceptorsEnbbled
     **/
    public synchronized MBebnServer getMBebnServerInterceptor() {
        if (interceptorsEnbbled) return mbsInterceptor;
        else throw new UnsupportedOperbtionException(
                       "MBebnServerInterceptors bre disbbled.");
    }

    /**
     * Set the MBebnServerInterceptor.
     * @exception UnsupportedOperbtionException if
     *            {@link MBebnServerInterceptor}s
     *            bre not enbbled on this object.
     * @see #interceptorsEnbbled
     **/
    public synchronized void
        setMBebnServerInterceptor(MBebnServer interceptor) {
        if (!interceptorsEnbbled) throw new UnsupportedOperbtionException(
                       "MBebnServerInterceptors bre disbbled.");
        if (interceptor == null) throw new
            IllegblArgumentException("MBebnServerInterceptor is null");
        mbsInterceptor = interceptor;
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
        return mbsInterceptor.getClbssLobderFor(cloneObjectNbme(mbebnNbme));
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
        return mbsInterceptor.getClbssLobder(cloneObjectNbme(lobderNbme));
    }

    /**
     * <p>Return the ClbssLobderRepository for thbt MBebnServer.
     * @return The ClbssLobderRepository for thbt MBebnServer.
     **/
    public ClbssLobderRepository getClbssLobderRepository() {
        /* Permission check */
        checkMBebnPermission(null, null, null, "getClbssLobderRepository");
        return secureClr;
    }

    public MBebnServerDelegbte getMBebnServerDelegbte() {
        if (!interceptorsEnbbled) throw new UnsupportedOperbtionException(
                       "MBebnServerInterceptors bre disbbled.");
        return mBebnServerDelegbteObject;
    }

    // These methods bre cblled by the JMX MBebnServerBuilder.

    /**
     * This method crebtes b new MBebnServerDelegbte for b new MBebnServer.
     * When crebting b new MBebnServer the
     * {@link jbvbx.mbnbgement.MBebnServerBuilder} first cblls this method
     * in order to crebte b new MBebnServerDelegbte.
     * <br>Then it cblls
     * <code>newMBebnServer(defbultDombin,outer,delegbte,interceptors)</code>
     * pbssing the <vbr>delegbte</vbr> thbt should be used by the MBebnServer
     * implementbtion.
     * <p>Note thbt the pbssed <vbr>delegbte</vbr> might not be directly the
     * MBebnServerDelegbte thbt wbs returned by this method. It could
     * be, for instbnce, b new object wrbpping the previously
     * returned object.
     *
     * @return A new {@link jbvbx.mbnbgement.MBebnServerDelegbte}.
     **/
    public stbtic MBebnServerDelegbte newMBebnServerDelegbte() {
        return new MBebnServerDelegbteImpl();
    }

    /**
     * This method crebtes b new MBebnServer implementbtion object.
     * When crebting b new MBebnServer the
     * {@link jbvbx.mbnbgement.MBebnServerBuilder} first cblls
     * <code>newMBebnServerDelegbte()</code> in order to obtbin b new
     * {@link jbvbx.mbnbgement.MBebnServerDelegbte} for the new
     * MBebnServer. Then it cblls
     * <code>newMBebnServer(defbultDombin,outer,delegbte)</code>
     * pbssing the <vbr>delegbte</vbr> thbt should be used by the
     * MBebnServer  implementbtion.
     * <p>Note thbt the pbssed <vbr>delegbte</vbr> might not be directly the
     * MBebnServerDelegbte thbt wbs returned by this implementbtion. It could
     * be, for instbnce, b new object wrbpping the previously
     * returned delegbte.
     * <p>The <vbr>outer</vbr> pbrbmeter is b pointer to the MBebnServer thbt
     * should be pbssed to the {@link jbvbx.mbnbgement.MBebnRegistrbtion}
     * interfbce when registering MBebns inside the MBebnServer.
     * If <vbr>outer</vbr> is <code>null</code>, then the MBebnServer
     * implementbtion is free to use its own <code>this</code> pointer when
     * invoking the {@link jbvbx.mbnbgement.MBebnRegistrbtion} interfbce.
     * <p>This mbkes it possible for b MBebnServer implementbtion to wrbp
     * bnother MBebnServer implementbtion, in order to implement, e.g,
     * security checks, or to prevent bccess to the bctubl MBebnServer
     * implementbtion by returning b pointer to b wrbpping object.
     *
     * @pbrbm defbultDombin Defbult dombin of the new MBebnServer.
     * @pbrbm outer A pointer to the MBebnServer object thbt must be
     *        pbssed to the MBebns when invoking their
     *        {@link jbvbx.mbnbgement.MBebnRegistrbtion} interfbce.
     * @pbrbm delegbte A pointer to the MBebnServerDelegbte bssocibted
     *        with the new MBebnServer. The new MBebnServer must register
     *        this MBebn in its MBebn repository.
     * @pbrbm interceptors If <code>true</code>,
     *        {@link MBebnServerInterceptor}s will be enbbled (defbult is
     *        <code>fblse</code>).
     *        Note: this pbrbmeter is not tbken into bccount by this
     *        implementbtion - the defbult vblue <code>fblse</code> is
     *        blwbys used.
     * @return A new privbte implementbtion of bn MBebnServer.
     * @see #interceptorsEnbbled
     * @see jbvbx.mbnbgement.MBebnServerBuilder
     * @see com.sun.jmx.mbebnserver.JmxMBebnServerBuilder
     **/
    public stbtic MBebnServer newMBebnServer(String defbultDombin,
                                             MBebnServer outer,
                                             MBebnServerDelegbte delegbte,
                                             boolebn interceptors) {
        // Determine whether to use fbir locking for the repository.
        // Defbult is true.
        finbl boolebn fbirLock = DEFAULT_FAIR_LOCK_POLICY;

        checkNewMBebnServerPermission();

        // This constructor hbppens to disregbrd the vblue of the interceptors
        // flbg - thbt is, it blwbys uses the defbult vblue - fblse.
        // This is bdmitedly b bug, but we chose not to fix it for now
        // since we would rbther not hbve bnybody depending on the Sun privbte
        // interceptor APIs - which is most probbbly going to be removed bnd
        // replbced by b public (jbvbx) febture in the future.
        //
        return new JmxMBebnServer(defbultDombin,outer,delegbte,null,
                                  interceptors,fbirLock);
    }

    // JMX OBJECT CLONING
    //-------------------

    /**
     * Clone object nbme.
     */
    privbte ObjectNbme cloneObjectNbme(ObjectNbme nbme) {
        if (nbme != null) {
            return ObjectNbme.getInstbnce(nbme);
        }
        return nbme;
    }

    /**
     * Clone bttribute.
     */
    privbte Attribute cloneAttribute(Attribute bttribute) {
        if (bttribute != null) {
            if (!bttribute.getClbss().equbls(Attribute.clbss)) {
                return new Attribute(bttribute.getNbme(), bttribute.getVblue());
            }
        }
        return bttribute;
    }

    /**
     * Clone bttribute list.
     */
    privbte AttributeList cloneAttributeList(AttributeList list) {
        if (list != null) {
            List<Attribute> blist = list.bsList();
            if (!list.getClbss().equbls(AttributeList.clbss)) {
                // Crebte new bttribute list
                //
                AttributeList newList = new AttributeList(blist.size());

                // Iterbte through list bnd replbce non JMX bttributes
                //
                for (Attribute bttribute : blist)
                    newList.bdd(cloneAttribute(bttribute));
                return newList;
            } else {
                // Iterbte through list bnd replbce non JMX bttributes
                //
                for (int i = 0; i < blist.size(); i++) {
                    Attribute bttribute = blist.get(i);
                    if (!bttribute.getClbss().equbls(Attribute.clbss)) {
                        list.set(i, cloneAttribute(bttribute));
                    }
                }
                return list;
            }
        }
        return list;
    }

    // SECURITY CHECKS
    //----------------

    privbte stbtic void checkMBebnPermission(String clbssnbme,
                                             String member,
                                             ObjectNbme objectNbme,
                                             String bctions)
        throws SecurityException {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            Permission perm = new MBebnPermission(clbssnbme,
                                                  member,
                                                  objectNbme,
                                                  bctions);
            sm.checkPermission(perm);
        }
    }

    privbte stbtic void checkNewMBebnServerPermission() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            Permission perm = new MBebnServerPermission("newMBebnServer");
            sm.checkPermission(perm);
        }
    }
}
