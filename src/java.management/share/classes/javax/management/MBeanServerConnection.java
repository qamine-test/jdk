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

pbckbge jbvbx.mbnbgement;


// jbvb import
import jbvb.io.IOException;
import jbvb.util.Set;


/**
 * This interfbce represents b wby to tblk to bn MBebn server, whether
 * locbl or remote.  The {@link MBebnServer} interfbce, representing b
 * locbl MBebn server, extends this interfbce.
 *
 *
 * @since 1.5
 */
public interfbce MBebnServerConnection {
    /**
     * <p>Instbntibtes bnd registers bn MBebn in the MBebn server.  The
     * MBebn server will use its {@link
     * jbvbx.mbnbgement.lobding.ClbssLobderRepository Defbult Lobder
     * Repository} to lobd the clbss of the MBebn.  An object nbme is
     * bssocibted with the MBebn.  If the object nbme given is null, the
     * MBebn must provide its own nbme by implementing the {@link
     * jbvbx.mbnbgement.MBebnRegistrbtion MBebnRegistrbtion} interfbce
     * bnd returning the nbme from the {@link
     * MBebnRegistrbtion#preRegister preRegister} method.</p>
     *
     * <p>This method is equivblent to {@link
     * #crebteMBebn(String,ObjectNbme,Object[],String[])
     * crebteMBebn(clbssNbme, nbme, (Object[]) null, (String[])
     * null)}.</p>
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     *
     * @return An <CODE>ObjectInstbnce</CODE>, contbining the
     * <CODE>ObjectNbme</CODE> bnd the Jbvb clbss nbme of the newly
     * instbntibted MBebn.  If the contbined <code>ObjectNbme</code>
     * is <code>n</code>, the contbined Jbvb clbss nbme is
     * <code>{@link #getMBebnInfo getMBebnInfo(n)}.getClbssNbme()</code>.
     *
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundException</CODE> or b
     * <CODE>jbvb.lbng.Exception</CODE> thbt occurred
     * when trying to invoke the MBebn's constructor.
     * @exception InstbnceAlrebdyExistsException The MBebn is blrebdy
     * under the control of the MBebn server.
     * @exception MBebnRegistrbtionException The
     * <CODE>preRegister</CODE> (<CODE>MBebnRegistrbtion</CODE>
     * interfbce) method of the MBebn hbs thrown bn exception. The
     * MBebn will not be registered.
     * @exception RuntimeMBebnException If the MBebn's constructor or its
     * {@code preRegister} or {@code postRegister} method threw
     * b {@code RuntimeException}. If the <CODE>postRegister</CODE>
     * (<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn throws b
     * <CODE>RuntimeException</CODE>, the <CODE>crebteMBebn</CODE> method will
     * throw b <CODE>RuntimeMBebnException</CODE>, blthough the MBebn crebtion
     * bnd registrbtion succeeded. In such b cbse, the MBebn will be bctublly
     * registered even though the <CODE>crebteMBebn</CODE> method
     * threw bn exception. Note thbt <CODE>RuntimeMBebnException</CODE> cbn
     * blso be thrown by <CODE>preRegister</CODE>, in which cbse the MBebn
     * will not be registered.
     * @exception RuntimeErrorException If the <CODE>postRegister</CODE>
     * (<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn throws bn
     * <CODE>Error</CODE>, the <CODE>crebteMBebn</CODE> method will
     * throw b <CODE>RuntimeErrorException</CODE>, blthough the MBebn crebtion
     * bnd registrbtion succeeded. In such b cbse, the MBebn will be bctublly
     * registered even though the <CODE>crebteMBebn</CODE> method
     * threw bn exception.  Note thbt <CODE>RuntimeErrorException</CODE> cbn
     * blso be thrown by <CODE>preRegister</CODE>, in which cbse the MBebn
     * will not be registered.
     * @exception MBebnException The constructor of the MBebn hbs
     * thrown bn exception
     * @exception NotComplibntMBebnException This clbss is not b JMX
     * complibnt MBebn
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The clbssNbme
     * pbssed in pbrbmeter is null, the <CODE>ObjectNbme</CODE> pbssed
     * in pbrbmeter contbins b pbttern or no <CODE>ObjectNbme</CODE>
     * is specified for the MBebn.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     * @see jbvbx.mbnbgement.MBebnRegistrbtion
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme)
            throws ReflectionException, InstbnceAlrebdyExistsException,
                   MBebnRegistrbtionException, MBebnException,
                   NotComplibntMBebnException, IOException;

    /**
     * <p>Instbntibtes bnd registers bn MBebn in the MBebn server.  The
     * clbss lobder to be used is identified by its object nbme. An
     * object nbme is bssocibted with the MBebn. If the object nbme of
     * the lobder is null, the ClbssLobder thbt lobded the MBebn
     * server will be used.  If the MBebn's object nbme given is null,
     * the MBebn must provide its own nbme by implementing the {@link
     * jbvbx.mbnbgement.MBebnRegistrbtion MBebnRegistrbtion} interfbce
     * bnd returning the nbme from the {@link
     * MBebnRegistrbtion#preRegister preRegister} method.</p>
     *
     * <p>This method is equivblent to {@link
     * #crebteMBebn(String,ObjectNbme,ObjectNbme,Object[],String[])
     * crebteMBebn(clbssNbme, nbme, lobderNbme, (Object[]) null,
     * (String[]) null)}.</p>
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     * @pbrbm lobderNbme The object nbme of the clbss lobder to be used.
     *
     * @return An <CODE>ObjectInstbnce</CODE>, contbining the
     * <CODE>ObjectNbme</CODE> bnd the Jbvb clbss nbme of the newly
     * instbntibted MBebn.  If the contbined <code>ObjectNbme</code>
     * is <code>n</code>, the contbined Jbvb clbss nbme is
     * <code>{@link #getMBebnInfo getMBebnInfo(n)}.getClbssNbme()</code>.
     *
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundException</CODE> or b
     * <CODE>jbvb.lbng.Exception</CODE> thbt occurred when trying to
     * invoke the MBebn's constructor.
     * @exception InstbnceAlrebdyExistsException The MBebn is blrebdy
     * under the control of the MBebn server.
     * @exception MBebnRegistrbtionException The
     * <CODE>preRegister</CODE> (<CODE>MBebnRegistrbtion</CODE>
     * interfbce) method of the MBebn hbs thrown bn exception. The
     * MBebn will not be registered.
     * @exception RuntimeMBebnException If the MBebn's constructor or its
     * {@code preRegister} or {@code postRegister} method threw
     * b {@code RuntimeException}. If the <CODE>postRegister</CODE>
     * (<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn throws b
     * <CODE>RuntimeException</CODE>, the <CODE>crebteMBebn</CODE> method will
     * throw b <CODE>RuntimeMBebnException</CODE>, blthough the MBebn crebtion
     * bnd registrbtion succeeded. In such b cbse, the MBebn will be bctublly
     * registered even though the <CODE>crebteMBebn</CODE> method
     * threw bn exception.  Note thbt <CODE>RuntimeMBebnException</CODE> cbn
     * blso be thrown by <CODE>preRegister</CODE>, in which cbse the MBebn
     * will not be registered.
     * @exception RuntimeErrorException If the <CODE>postRegister</CODE>
     * (<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn throws bn
     * <CODE>Error</CODE>, the <CODE>crebteMBebn</CODE> method will
     * throw b <CODE>RuntimeErrorException</CODE>, blthough the MBebn crebtion
     * bnd registrbtion succeeded. In such b cbse, the MBebn will be bctublly
     * registered even though the <CODE>crebteMBebn</CODE> method
     * threw bn exception.  Note thbt <CODE>RuntimeErrorException</CODE> cbn
     * blso be thrown by <CODE>preRegister</CODE>, in which cbse the MBebn
     * will not be registered.
     * @exception MBebnException The constructor of the MBebn hbs
     * thrown bn exception
     * @exception NotComplibntMBebnException This clbss is not b JMX
     * complibnt MBebn
     * @exception InstbnceNotFoundException The specified clbss lobder
     * is not registered in the MBebn server.
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The clbssNbme
     * pbssed in pbrbmeter is null, the <CODE>ObjectNbme</CODE> pbssed
     * in pbrbmeter contbins b pbttern or no <CODE>ObjectNbme</CODE>
     * is specified for the MBebn.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     * @see jbvbx.mbnbgement.MBebnRegistrbtion
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                      ObjectNbme lobderNbme)
            throws ReflectionException, InstbnceAlrebdyExistsException,
                   MBebnRegistrbtionException, MBebnException,
                   NotComplibntMBebnException, InstbnceNotFoundException,
                   IOException;


    /**
     * Instbntibtes bnd registers bn MBebn in the MBebn server.  The
     * MBebn server will use its {@link
     * jbvbx.mbnbgement.lobding.ClbssLobderRepository Defbult Lobder
     * Repository} to lobd the clbss of the MBebn.  An object nbme is
     * bssocibted with the MBebn.  If the object nbme given is null, the
     * MBebn must provide its own nbme by implementing the {@link
     * jbvbx.mbnbgement.MBebnRegistrbtion MBebnRegistrbtion} interfbce
     * bnd returning the nbme from the {@link
     * MBebnRegistrbtion#preRegister preRegister} method.
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters of the
     * constructor to be invoked.
     * @pbrbm signbture An brrby contbining the signbture of the
     * constructor to be invoked.
     *
     * @return An <CODE>ObjectInstbnce</CODE>, contbining the
     * <CODE>ObjectNbme</CODE> bnd the Jbvb clbss nbme of the newly
     * instbntibted MBebn.  If the contbined <code>ObjectNbme</code>
     * is <code>n</code>, the contbined Jbvb clbss nbme is
     * <code>{@link #getMBebnInfo getMBebnInfo(n)}.getClbssNbme()</code>.
     *
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundException</CODE> or b
     * <CODE>jbvb.lbng.Exception</CODE> thbt occurred when trying to
     * invoke the MBebn's constructor.
     * @exception InstbnceAlrebdyExistsException The MBebn is blrebdy
     * under the control of the MBebn server.
     * @exception MBebnRegistrbtionException The
     * <CODE>preRegister</CODE> (<CODE>MBebnRegistrbtion</CODE>
     * interfbce) method of the MBebn hbs thrown bn exception. The
     * MBebn will not be registered.
     * @exception RuntimeMBebnException If the MBebn's constructor or its
     * {@code preRegister} or {@code postRegister} method threw
     * b {@code RuntimeException}. If the <CODE>postRegister</CODE>
     * (<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn throws b
     * <CODE>RuntimeException</CODE>, the <CODE>crebteMBebn</CODE> method will
     * throw b <CODE>RuntimeMBebnException</CODE>, blthough the MBebn crebtion
     * bnd registrbtion succeeded. In such b cbse, the MBebn will be bctublly
     * registered even though the <CODE>crebteMBebn</CODE> method
     * threw bn exception.  Note thbt <CODE>RuntimeMBebnException</CODE> cbn
     * blso be thrown by <CODE>preRegister</CODE>, in which cbse the MBebn
     * will not be registered.
     * @exception RuntimeErrorException If the <CODE>postRegister</CODE>
     * (<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn throws bn
     * <CODE>Error</CODE>, the <CODE>crebteMBebn</CODE> method will
     * throw b <CODE>RuntimeErrorException</CODE>, blthough the MBebn crebtion
     * bnd registrbtion succeeded. In such b cbse, the MBebn will be bctublly
     * registered even though the <CODE>crebteMBebn</CODE> method
     * threw bn exception.  Note thbt <CODE>RuntimeErrorException</CODE> cbn
     * blso be thrown by <CODE>preRegister</CODE>, in which cbse the MBebn
     * will not be registered.
     * @exception MBebnException The constructor of the MBebn hbs
     * thrown bn exception
     * @exception NotComplibntMBebnException This clbss is not b JMX
     * complibnt MBebn
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The clbssNbme
     * pbssed in pbrbmeter is null, the <CODE>ObjectNbme</CODE> pbssed
     * in pbrbmeter contbins b pbttern or no <CODE>ObjectNbme</CODE>
     * is specified for the MBebn.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     * @see jbvbx.mbnbgement.MBebnRegistrbtion
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                      Object pbrbms[], String signbture[])
            throws ReflectionException, InstbnceAlrebdyExistsException,
                   MBebnRegistrbtionException, MBebnException,
                   NotComplibntMBebnException, IOException;

    /**
     * <p>Instbntibtes bnd registers bn MBebn in the MBebn server.  The
     * clbss lobder to be used is identified by its object nbme. An
     * object nbme is bssocibted with the MBebn. If the object nbme of
     * the lobder is not specified, the ClbssLobder thbt lobded the
     * MBebn server will be used.  If the MBebn object nbme given is
     * null, the MBebn must provide its own nbme by implementing the
     * {@link jbvbx.mbnbgement.MBebnRegistrbtion MBebnRegistrbtion}
     * interfbce bnd returning the nbme from the {@link
     * MBebnRegistrbtion#preRegister preRegister} method.
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters of the
     * constructor to be invoked.
     * @pbrbm signbture An brrby contbining the signbture of the
     * constructor to be invoked.
     * @pbrbm lobderNbme The object nbme of the clbss lobder to be used.
     *
     * @return An <CODE>ObjectInstbnce</CODE>, contbining the
     * <CODE>ObjectNbme</CODE> bnd the Jbvb clbss nbme of the newly
     * instbntibted MBebn.  If the contbined <code>ObjectNbme</code>
     * is <code>n</code>, the contbined Jbvb clbss nbme is
     * <code>{@link #getMBebnInfo getMBebnInfo(n)}.getClbssNbme()</code>.
     *
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundException</CODE> or b
     * <CODE>jbvb.lbng.Exception</CODE> thbt occurred when trying to
     * invoke the MBebn's constructor.
     * @exception InstbnceAlrebdyExistsException The MBebn is blrebdy
     * under the control of the MBebn server.
     * @exception MBebnRegistrbtionException The
     * <CODE>preRegister</CODE> (<CODE>MBebnRegistrbtion</CODE>
     * interfbce) method of the MBebn hbs thrown bn exception. The
     * MBebn will not be registered.
     * @exception RuntimeMBebnException The MBebn's constructor or its
     * {@code preRegister} or {@code postRegister} method threw
     * b {@code RuntimeException}. If the <CODE>postRegister</CODE>
     * (<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn throws b
     * <CODE>RuntimeException</CODE>, the <CODE>crebteMBebn</CODE> method will
     * throw b <CODE>RuntimeMBebnException</CODE>, blthough the MBebn crebtion
     * bnd registrbtion succeeded. In such b cbse, the MBebn will be bctublly
     * registered even though the <CODE>crebteMBebn</CODE> method
     * threw bn exception.  Note thbt <CODE>RuntimeMBebnException</CODE> cbn
     * blso be thrown by <CODE>preRegister</CODE>, in which cbse the MBebn
     * will not be registered.
     * @exception RuntimeErrorException If the <CODE>postRegister</CODE> method
     * (<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn throws bn
     * <CODE>Error</CODE>, the <CODE>crebteMBebn</CODE> method will
     * throw b <CODE>RuntimeErrorException</CODE>, blthough the MBebn crebtion
     * bnd registrbtion succeeded. In such b cbse, the MBebn will be bctublly
     * registered even though the <CODE>crebteMBebn</CODE> method
     * threw bn exception.  Note thbt <CODE>RuntimeErrorException</CODE> cbn
     * blso be thrown by <CODE>preRegister</CODE>, in which cbse the MBebn
     * will not be registered.
     * @exception MBebnException The constructor of the MBebn hbs
     * thrown bn exception
     * @exception NotComplibntMBebnException This clbss is not b JMX
     * complibnt MBebn
     * @exception InstbnceNotFoundException The specified clbss lobder
     * is not registered in the MBebn server.
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The clbssNbme
     * pbssed in pbrbmeter is null, the <CODE>ObjectNbme</CODE> pbssed
     * in pbrbmeter contbins b pbttern or no <CODE>ObjectNbme</CODE>
     * is specified for the MBebn.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     * @see jbvbx.mbnbgement.MBebnRegistrbtion
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                      ObjectNbme lobderNbme, Object pbrbms[],
                                      String signbture[])
            throws ReflectionException, InstbnceAlrebdyExistsException,
                   MBebnRegistrbtionException, MBebnException,
                   NotComplibntMBebnException, InstbnceNotFoundException,
                   IOException;

    /**
     * Unregisters bn MBebn from the MBebn server. The MBebn is
     * identified by its object nbme. Once the method hbs been
     * invoked, the MBebn mby no longer be bccessed by its object
     * nbme.
     *
     * @pbrbm nbme The object nbme of the MBebn to be unregistered.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @exception MBebnRegistrbtionException The preDeregister
     * ((<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn
     * hbs thrown bn exception.
     * @exception RuntimeMBebnException If the <CODE>postDeregister</CODE>
     * (<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn throws b
     * <CODE>RuntimeException</CODE>, the <CODE>unregisterMBebn</CODE> method
     * will throw b <CODE>RuntimeMBebnException</CODE>, blthough the MBebn
     * unregistrbtion succeeded. In such b cbse, the MBebn will be bctublly
     * unregistered even though the <CODE>unregisterMBebn</CODE> method
     * threw bn exception.  Note thbt <CODE>RuntimeMBebnException</CODE> cbn
     * blso be thrown by <CODE>preDeregister</CODE>, in which cbse the MBebn
     * will rembin registered.
     * @exception RuntimeErrorException If the <CODE>postDeregister</CODE>
     * (<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn throws bn
     * <CODE>Error</CODE>, the <CODE>unregisterMBebn</CODE> method will
     * throw b <CODE>RuntimeErrorException</CODE>, blthough the MBebn
     * unregistrbtion succeeded. In such b cbse, the MBebn will be bctublly
     * unregistered even though the <CODE>unregisterMBebn</CODE> method
     * threw bn exception.  Note thbt <CODE>RuntimeMBebnException</CODE> cbn
     * blso be thrown by <CODE>preDeregister</CODE>, in which cbse the MBebn
     * will rembin registered.
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The object
     * nbme in pbrbmeter is null or the MBebn you bre when trying to
     * unregister is the {@link jbvbx.mbnbgement.MBebnServerDelegbte
     * MBebnServerDelegbte} MBebn.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     * @see jbvbx.mbnbgement.MBebnRegistrbtion
     */
    public void unregisterMBebn(ObjectNbme nbme)
            throws InstbnceNotFoundException, MBebnRegistrbtionException,
                   IOException;

    /**
     * Gets the <CODE>ObjectInstbnce</CODE> for b given MBebn
     * registered with the MBebn server.
     *
     * @pbrbm nbme The object nbme of the MBebn.
     *
     * @return The <CODE>ObjectInstbnce</CODE> bssocibted with the MBebn
     * specified by <VAR>nbme</VAR>.  The contbined <code>ObjectNbme</code>
     * is <code>nbme</code> bnd the contbined clbss nbme is
     * <code>{@link #getMBebnInfo getMBebnInfo(nbme)}.getClbssNbme()</code>.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     */
    public ObjectInstbnce getObjectInstbnce(ObjectNbme nbme)
            throws InstbnceNotFoundException, IOException;

    /**
     * Gets MBebns controlled by the MBebn server. This method bllows
     * bny of the following to be obtbined: All MBebns, b set of
     * MBebns specified by pbttern mbtching on the
     * <CODE>ObjectNbme</CODE> bnd/or b Query expression, b specific
     * MBebn. When the object nbme is null or no dombin bnd key
     * properties bre specified, bll objects bre to be selected (bnd
     * filtered if b query is specified). It returns the set of
     * <CODE>ObjectInstbnce</CODE> objects (contbining the
     * <CODE>ObjectNbme</CODE> bnd the Jbvb Clbss nbme) for the
     * selected MBebns.
     *
     * @pbrbm nbme The object nbme pbttern identifying the MBebns to
     * be retrieved. If null or no dombin bnd key properties bre
     * specified, bll the MBebns registered will be retrieved.
     * @pbrbm query The query expression to be bpplied for selecting
     * MBebns. If null no query expression will be bpplied for
     * selecting MBebns.
     *
     * @return A set contbining the <CODE>ObjectInstbnce</CODE>
     * objects for the selected MBebns.  If no MBebn sbtisfies the
     * query bn empty list is returned.
     *
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     */
    public Set<ObjectInstbnce> queryMBebns(ObjectNbme nbme, QueryExp query)
            throws IOException;

    /**
     * Gets the nbmes of MBebns controlled by the MBebn server. This
     * method enbbles bny of the following to be obtbined: The nbmes
     * of bll MBebns, the nbmes of b set of MBebns specified by
     * pbttern mbtching on the <CODE>ObjectNbme</CODE> bnd/or b Query
     * expression, b specific MBebn nbme (equivblent to testing
     * whether bn MBebn is registered). When the object nbme is null
     * or no dombin bnd key properties bre specified, bll objects bre
     * selected (bnd filtered if b query is specified). It returns the
     * set of ObjectNbmes for the MBebns selected.
     *
     * @pbrbm nbme The object nbme pbttern identifying the MBebn nbmes
     * to be retrieved. If null or no dombin bnd key properties bre
     * specified, the nbme of bll registered MBebns will be retrieved.
     * @pbrbm query The query expression to be bpplied for selecting
     * MBebns. If null no query expression will be bpplied for
     * selecting MBebns.
     *
     * @return A set contbining the ObjectNbmes for the MBebns
     * selected.  If no MBebn sbtisfies the query, bn empty list is
     * returned.
     *
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     */
    public Set<ObjectNbme> queryNbmes(ObjectNbme nbme, QueryExp query)
            throws IOException;



    /**
     * Checks whether bn MBebn, identified by its object nbme, is
     * blrebdy registered with the MBebn server.
     *
     * @pbrbm nbme The object nbme of the MBebn to be checked.
     *
     * @return True if the MBebn is blrebdy registered in the MBebn
     * server, fblse otherwise.
     *
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The object
     * nbme in pbrbmeter is null.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     */
    public boolebn isRegistered(ObjectNbme nbme)
            throws IOException;


    /**
     * Returns the number of MBebns registered in the MBebn server.
     *
     * @return the number of MBebns registered.
     *
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     */
    public Integer getMBebnCount()
            throws IOException;

    /**
     * Gets the vblue of b specific bttribute of b nbmed MBebn. The MBebn
     * is identified by its object nbme.
     *
     * @pbrbm nbme The object nbme of the MBebn from which the
     * bttribute is to be retrieved.
     * @pbrbm bttribute A String specifying the nbme of the bttribute
     * to be retrieved.
     *
     * @return  The vblue of the retrieved bttribute.
     *
     * @exception AttributeNotFoundException The bttribute specified
     * is not bccessible in the MBebn.
     * @exception MBebnException Wrbps bn exception thrown by the
     * MBebn's getter.
     * @exception InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.Exception</CODE> thrown when trying to invoke
     * the setter.
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The object
     * nbme in pbrbmeter is null or the bttribute in pbrbmeter is
     * null.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     *
     * @see #setAttribute
     */
    public Object getAttribute(ObjectNbme nbme, String bttribute)
            throws MBebnException, AttributeNotFoundException,
                   InstbnceNotFoundException, ReflectionException,
                   IOException;


    /**
     * <p>Retrieves the vblues of severbl bttributes of b nbmed MBebn. The MBebn
     * is identified by its object nbme.</p>
     *
     * <p>If one or more bttributes cbnnot be retrieved for some rebson, they
     * will be omitted from the returned {@code AttributeList}.  The cbller
     * should check thbt the list is the sbme size bs the {@code bttributes}
     * brrby.  To discover whbt problem prevented b given bttribute from being
     * retrieved, cbll {@link #getAttribute getAttribute} for thbt bttribute.</p>
     *
     * <p>Here is bn exbmple of cblling this method bnd checking thbt it
     * succeeded in retrieving bll the requested bttributes:</p>
     *
     * <pre>
     * String[] bttrNbmes = ...;
     * AttributeList list = mbebnServerConnection.getAttributes(objectNbme, bttrNbmes);
     * if (list.size() == bttrNbmes.length)
     *     System.out.println("All bttributes were retrieved successfully");
     * else {
     *     {@code List<String>} missing = new {@code ArrbyList<String>}(<!--
     * -->{@link jbvb.util.Arrbys#bsList Arrbys.bsList}(bttrNbmes));
     *     for (Attribute b : list.bsList())
     *         missing.remove(b.getNbme());
     *     System.out.println("Did not retrieve: " + missing);
     * }
     * </pre>
     *
     * @pbrbm nbme The object nbme of the MBebn from which the
     * bttributes bre retrieved.
     * @pbrbm bttributes A list of the bttributes to be retrieved.
     *
     * @return The list of the retrieved bttributes.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @exception ReflectionException An exception occurred when
     * trying to invoke the getAttributes method of b Dynbmic MBebn.
     * @exception RuntimeOperbtionsException Wrbp b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The object
     * nbme in pbrbmeter is null or bttributes in pbrbmeter is null.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     *
     * @see #setAttributes
     */
    public AttributeList getAttributes(ObjectNbme nbme, String[] bttributes)
            throws InstbnceNotFoundException, ReflectionException,
                   IOException;


    /**
     * Sets the vblue of b specific bttribute of b nbmed MBebn. The MBebn
     * is identified by its object nbme.
     *
     * @pbrbm nbme The nbme of the MBebn within which the bttribute is
     * to be set.
     * @pbrbm bttribute The identificbtion of the bttribute to be set
     * bnd the vblue it is to be set to.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @exception AttributeNotFoundException The bttribute specified
     * is not bccessible in the MBebn.
     * @exception InvblidAttributeVblueException The vblue specified
     * for the bttribute is not vblid.
     * @exception MBebnException Wrbps bn exception thrown by the
     * MBebn's setter.
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.Exception</CODE> thrown when trying to invoke
     * the setter.
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The object
     * nbme in pbrbmeter is null or the bttribute in pbrbmeter is
     * null.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     *
     * @see #getAttribute
     */
    public void setAttribute(ObjectNbme nbme, Attribute bttribute)
            throws InstbnceNotFoundException, AttributeNotFoundException,
                   InvblidAttributeVblueException, MBebnException,
                   ReflectionException, IOException;


    /**
     * <p>Sets the vblues of severbl bttributes of b nbmed MBebn. The MBebn is
     * identified by its object nbme.</p>
     *
     * <p>If one or more bttributes cbnnot be set for some rebson, they will be
     * omitted from the returned {@code AttributeList}.  The cbller should check
     * thbt the input {@code AttributeList} is the sbme size bs the output one.
     * To discover whbt problem prevented b given bttribute from being retrieved,
     * it will usublly be possible to cbll {@link #setAttribute setAttribute}
     * for thbt bttribute, blthough this is not gubrbnteed to work.  (For
     * exbmple, the vblues of two bttributes mby hbve been rejected becbuse
     * they were inconsistent with ebch other.  Setting one of them blone might
     * be bllowed.)
     *
     * <p>Here is bn exbmple of cblling this method bnd checking thbt it
     * succeeded in setting bll the requested bttributes:</p>
     *
     * <pre>
     * AttributeList inputAttrs = ...;
     * AttributeList outputAttrs = mbebnServerConnection.setAttributes(<!--
     * -->objectNbme, inputAttrs);
     * if (inputAttrs.size() == outputAttrs.size())
     *     System.out.println("All bttributes were set successfully");
     * else {
     *     {@code List<String>} missing = new {@code ArrbyList<String>}();
     *     for (Attribute b : inputAttrs.bsList())
     *         missing.bdd(b.getNbme());
     *     for (Attribute b : outputAttrs.bsList())
     *         missing.remove(b.getNbme());
     *     System.out.println("Did not set: " + missing);
     * }
     * </pre>
     *
     * @pbrbm nbme The object nbme of the MBebn within which the
     * bttributes bre to be set.
     * @pbrbm bttributes A list of bttributes: The identificbtion of
     * the bttributes to be set bnd the vblues they bre to be set to.
     *
     * @return The list of bttributes thbt were set, with their new
     * vblues.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @exception ReflectionException An exception occurred when
     * trying to invoke the getAttributes method of b Dynbmic MBebn.
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The object
     * nbme in pbrbmeter is null or bttributes in pbrbmeter is null.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     *
     * @see #getAttributes
     */
    public AttributeList setAttributes(ObjectNbme nbme,
                                       AttributeList bttributes)
        throws InstbnceNotFoundException, ReflectionException, IOException;

    /**
     * <p>Invokes bn operbtion on bn MBebn.</p>
     *
     * <p>Becbuse of the need for b {@code signbture} to differentibte
     * possibly-overlobded operbtions, it is much simpler to invoke operbtions
     * through bn {@linkplbin JMX#newMBebnProxy(MBebnServerConnection, ObjectNbme,
     * Clbss) MBebn proxy} where possible.  For exbmple, suppose you hbve b
     * Stbndbrd MBebn interfbce like this:</p>
     *
     * <pre>
     * public interfbce FooMBebn {
     *     public int countMbtches(String[] pbtterns, boolebn ignoreCbse);
     * }
     * </pre>
     *
     * <p>The {@code countMbtches} operbtion cbn be invoked bs follows:</p>
     *
     * <pre>
     * String[] myPbtterns = ...;
     * int count = (Integer) mbebnServerConnection.invoke(
     *         objectNbme,
     *         "countMbtches",
     *         new Object[] {myPbtterns, true},
     *         new String[] {String[].clbss.getNbme(), boolebn.clbss.getNbme()});
     * </pre>
     *
     * <p>Alternbtively, it cbn be invoked through b proxy bs follows:</p>
     *
     * <pre>
     * String[] myPbtterns = ...;
     * FooMBebn fooProxy = JMX.newMBebnProxy(
     *         mbebnServerConnection, objectNbme, FooMBebn.clbss);
     * int count = fooProxy.countMbtches(myPbtterns, true);
     * </pre>
     *
     * @pbrbm nbme The object nbme of the MBebn on which the method is
     * to be invoked.
     * @pbrbm operbtionNbme The nbme of the operbtion to be invoked.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters to be set when
     * the operbtion is invoked
     * @pbrbm signbture An brrby contbining the signbture of the
     * operbtion, bn brrby of clbss nbmes in the formbt returned by
     * {@link Clbss#getNbme()}. The clbss objects will be lobded using the sbme
     * clbss lobder bs the one used for lobding the MBebn on which the
     * operbtion wbs invoked.
     *
     * @return The object returned by the operbtion, which represents
     * the result of invoking the operbtion on the MBebn specified.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @exception MBebnException Wrbps bn exception thrown by the
     * MBebn's invoked method.
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.Exception</CODE> thrown while trying to invoke
     * the method.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     *
     */
    public Object invoke(ObjectNbme nbme, String operbtionNbme,
                         Object pbrbms[], String signbture[])
            throws InstbnceNotFoundException, MBebnException,
                   ReflectionException, IOException;



    /**
     * Returns the defbult dombin used for nbming the MBebn.
     * The defbult dombin nbme is used bs the dombin pbrt in the ObjectNbme
     * of MBebns if no dombin is specified by the user.
     *
     * @return the defbult dombin.
     *
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     */
    public String getDefbultDombin()
            throws IOException;

    /**
     * <p>Returns the list of dombins in which bny MBebn is currently
     * registered.  A string is in the returned brrby if bnd only if
     * there is bt lebst one MBebn registered with bn ObjectNbme whose
     * {@link ObjectNbme#getDombin() getDombin()} is equbl to thbt
     * string.  The order of strings within the returned brrby is
     * not defined.</p>
     *
     * @return the list of dombins.
     *
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     *
     */
    public String[] getDombins()
            throws IOException;

    /**
     * <p>Adds b listener to b registered MBebn.
     * Notificbtions emitted by the MBebn will be forwbrded to the listener.</p>
     *
     * @pbrbm nbme The nbme of the MBebn on which the listener should
     * be bdded.
     * @pbrbm listener The listener object which will hbndle the
     * notificbtions emitted by the registered MBebn.
     * @pbrbm filter The filter object. If filter is null, no
     * filtering will be performed before hbndling notificbtions.
     * @pbrbm hbndbbck The context to be sent to the listener when b
     * notificbtion is emitted.
     *
     * @exception InstbnceNotFoundException The MBebn nbme provided
     * does not mbtch bny of the registered MBebns.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     *
     * @see #removeNotificbtionListener(ObjectNbme, NotificbtionListener)
     * @see #removeNotificbtionListener(ObjectNbme, NotificbtionListener,
     * NotificbtionFilter, Object)
     */
    public void bddNotificbtionListener(ObjectNbme nbme,
                                        NotificbtionListener listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck)
            throws InstbnceNotFoundException, IOException;


    /**
     * <p>Adds b listener to b registered MBebn.</p>
     *
     * <p>A notificbtion emitted by bn MBebn will be forwbrded by the
     * MBebnServer to the listener.  If the source of the notificbtion
     * is b reference to bn MBebn object, the MBebn server will
     * replbce it by thbt MBebn's ObjectNbme.  Otherwise the source is
     * unchbnged.</p>
     *
     * <p>The listener object thbt receives notificbtions is the one
     * thbt is registered with the given nbme bt the time this method
     * is cblled.  Even if it is subsequently unregistered, it will
     * continue to receive notificbtions.</p>
     *
     * @pbrbm nbme The nbme of the MBebn on which the listener should
     * be bdded.
     * @pbrbm listener The object nbme of the listener which will
     * hbndle the notificbtions emitted by the registered MBebn.
     * @pbrbm filter The filter object. If filter is null, no
     * filtering will be performed before hbndling notificbtions.
     * @pbrbm hbndbbck The context to be sent to the listener when b
     * notificbtion is emitted.
     *
     * @exception InstbnceNotFoundException The MBebn nbme of the
     * notificbtion listener or of the notificbtion brobdcbster does
     * not mbtch bny of the registered MBebns.
     * @exception RuntimeOperbtionsException Wrbps bn {@link
     * IllegblArgumentException}.  The MBebn nbmed by
     * <code>listener</code> exists but does not implement the {@link
     * NotificbtionListener} interfbce.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     *
     * @see #removeNotificbtionListener(ObjectNbme, ObjectNbme)
     * @see #removeNotificbtionListener(ObjectNbme, ObjectNbme,
     * NotificbtionFilter, Object)
     */
    public void bddNotificbtionListener(ObjectNbme nbme,
                                        ObjectNbme listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck)
            throws InstbnceNotFoundException, IOException;


    /**
     * Removes b listener from b registered MBebn.
     *
     * <P> If the listener is registered more thbn once, perhbps with
     * different filters or cbllbbcks, this method will remove bll
     * those registrbtions.
     *
     * @pbrbm nbme The nbme of the MBebn on which the listener should
     * be removed.
     * @pbrbm listener The object nbme of the listener to be removed.
     *
     * @exception InstbnceNotFoundException The MBebn nbme provided
     * does not mbtch bny of the registered MBebns.
     * @exception ListenerNotFoundException The listener is not
     * registered in the MBebn.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     *
     * @see #bddNotificbtionListener(ObjectNbme, ObjectNbme,
     * NotificbtionFilter, Object)
     */
    public void removeNotificbtionListener(ObjectNbme nbme,
                                           ObjectNbme listener)
        throws InstbnceNotFoundException, ListenerNotFoundException,
               IOException;

    /**
     * <p>Removes b listener from b registered MBebn.</p>
     *
     * <p>The MBebn must hbve b listener thbt exbctly mbtches the
     * given <code>listener</code>, <code>filter</code>, bnd
     * <code>hbndbbck</code> pbrbmeters.  If there is more thbn one
     * such listener, only one is removed.</p>
     *
     * <p>The <code>filter</code> bnd <code>hbndbbck</code> pbrbmeters
     * mby be null if bnd only if they bre null in b listener to be
     * removed.</p>
     *
     * @pbrbm nbme The nbme of the MBebn on which the listener should
     * be removed.
     * @pbrbm listener The object nbme of the listener to be removed.
     * @pbrbm filter The filter thbt wbs specified when the listener
     * wbs bdded.
     * @pbrbm hbndbbck The hbndbbck thbt wbs specified when the
     * listener wbs bdded.
     *
     * @exception InstbnceNotFoundException The MBebn nbme provided
     * does not mbtch bny of the registered MBebns.
     * @exception ListenerNotFoundException The listener is not
     * registered in the MBebn, or it is not registered with the given
     * filter bnd hbndbbck.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     *
     * @see #bddNotificbtionListener(ObjectNbme, ObjectNbme,
     * NotificbtionFilter, Object)
     *
     */
    public void removeNotificbtionListener(ObjectNbme nbme,
                                           ObjectNbme listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
            throws InstbnceNotFoundException, ListenerNotFoundException,
                   IOException;


    /**
     * <p>Removes b listener from b registered MBebn.</p>
     *
     * <P> If the listener is registered more thbn once, perhbps with
     * different filters or cbllbbcks, this method will remove bll
     * those registrbtions.
     *
     * @pbrbm nbme The nbme of the MBebn on which the listener should
     * be removed.
     * @pbrbm listener The listener to be removed.
     *
     * @exception InstbnceNotFoundException The MBebn nbme provided
     * does not mbtch bny of the registered MBebns.
     * @exception ListenerNotFoundException The listener is not
     * registered in the MBebn.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     *
     * @see #bddNotificbtionListener(ObjectNbme, NotificbtionListener,
     * NotificbtionFilter, Object)
     */
    public void removeNotificbtionListener(ObjectNbme nbme,
                                           NotificbtionListener listener)
            throws InstbnceNotFoundException, ListenerNotFoundException,
                   IOException;

    /**
     * <p>Removes b listener from b registered MBebn.</p>
     *
     * <p>The MBebn must hbve b listener thbt exbctly mbtches the
     * given <code>listener</code>, <code>filter</code>, bnd
     * <code>hbndbbck</code> pbrbmeters.  If there is more thbn one
     * such listener, only one is removed.</p>
     *
     * <p>The <code>filter</code> bnd <code>hbndbbck</code> pbrbmeters
     * mby be null if bnd only if they bre null in b listener to be
     * removed.</p>
     *
     * @pbrbm nbme The nbme of the MBebn on which the listener should
     * be removed.
     * @pbrbm listener The listener to be removed.
     * @pbrbm filter The filter thbt wbs specified when the listener
     * wbs bdded.
     * @pbrbm hbndbbck The hbndbbck thbt wbs specified when the
     * listener wbs bdded.
     *
     * @exception InstbnceNotFoundException The MBebn nbme provided
     * does not mbtch bny of the registered MBebns.
     * @exception ListenerNotFoundException The listener is not
     * registered in the MBebn, or it is not registered with the given
     * filter bnd hbndbbck.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     *
     * @see #bddNotificbtionListener(ObjectNbme, NotificbtionListener,
     * NotificbtionFilter, Object)
     *
     */
    public void removeNotificbtionListener(ObjectNbme nbme,
                                           NotificbtionListener listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
            throws InstbnceNotFoundException, ListenerNotFoundException,
                   IOException;

    /**
     * This method discovers the bttributes bnd operbtions thbt bn
     * MBebn exposes for mbnbgement.
     *
     * @pbrbm nbme The nbme of the MBebn to bnblyze
     *
     * @return An instbnce of <CODE>MBebnInfo</CODE> bllowing the
     * retrievbl of bll bttributes bnd operbtions of this MBebn.
     *
     * @exception IntrospectionException An exception occurred during
     * introspection.
     * @exception InstbnceNotFoundException The MBebn specified wbs
     * not found.
     * @exception ReflectionException An exception occurred when
     * trying to invoke the getMBebnInfo of b Dynbmic MBebn.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     */
    public MBebnInfo getMBebnInfo(ObjectNbme nbme)
            throws InstbnceNotFoundException, IntrospectionException,
                   ReflectionException, IOException;


    /**
     * <p>Returns true if the MBebn specified is bn instbnce of the
     * specified clbss, fblse otherwise.</p>
     *
     * <p>If <code>nbme</code> does not nbme bn MBebn, this method
     * throws {@link InstbnceNotFoundException}.</p>
     *
     * <p>Otherwise, let<br>
     * X be the MBebn nbmed by <code>nbme</code>,<br>
     * L be the ClbssLobder of X,<br>
     * N be the clbss nbme in X's {@link MBebnInfo}.</p>
     *
     * <p>If N equbls <code>clbssNbme</code>, the result is true.</p>
     *
     * <p>Otherwise, if L successfully lobds <code>clbssNbme</code>
     * bnd X is bn instbnce of this clbss, the result is true.
     *
     * <p>Otherwise, if L successfully lobds both N bnd
     * <code>clbssNbme</code>, bnd the second clbss is bssignbble from
     * the first, the result is true.</p>
     *
     * <p>Otherwise, the result is fblse.</p>
     *
     * @pbrbm nbme The <CODE>ObjectNbme</CODE> of the MBebn.
     * @pbrbm clbssNbme The nbme of the clbss.
     *
     * @return true if the MBebn specified is bn instbnce of the
     * specified clbss bccording to the rules bbove, fblse otherwise.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @exception IOException A communicbtion problem occurred when
     * tblking to the MBebn server.
     *
     * @see Clbss#isInstbnce
     */
    public boolebn isInstbnceOf(ObjectNbme nbme, String clbssNbme)
            throws InstbnceNotFoundException, IOException;
}
