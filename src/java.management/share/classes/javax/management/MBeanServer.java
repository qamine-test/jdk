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

pbckbge jbvbx.mbnbgement;


// jbvb import
import jbvb.util.Set;
import jbvb.io.ObjectInputStrebm;

// RI import
import jbvbx.mbnbgement.lobding.ClbssLobderRepository;

/**
 * <p>This is the interfbce for MBebn mbnipulbtion on the bgent
 * side. It contbins the methods necessbry for the crebtion,
 * registrbtion, bnd deletion of MBebns bs well bs the bccess methods
 * for registered MBebns.  This is the core component of the JMX
 * infrbstructure.</p>
 *
 * <p>User code does not usublly implement this interfbce.  Instebd,
 * bn object thbt implements this interfbce is obtbined with one of
 * the methods in the {@link jbvbx.mbnbgement.MBebnServerFbctory} clbss.</p>
 *
 * <p>Every MBebn which is bdded to the MBebn server becomes
 * mbnbgebble: its bttributes bnd operbtions become remotely
 * bccessible through the connectors/bdbptors connected to thbt MBebn
 * server.  A Jbvb object cbnnot be registered in the MBebn server
 * unless it is b JMX complibnt MBebn.</p>
 *
 * <p id="notif">When bn MBebn is registered or unregistered in the
 * MBebn server b {@link jbvbx.mbnbgement.MBebnServerNotificbtion
 * MBebnServerNotificbtion} Notificbtion is emitted. To register bn
 * object bs listener to MBebnServerNotificbtions you should cbll the
 * MBebn server method {@link #bddNotificbtionListener
 * bddNotificbtionListener} with <CODE>ObjectNbme</CODE> the
 * <CODE>ObjectNbme</CODE> of the {@link
 * jbvbx.mbnbgement.MBebnServerDelegbte MBebnServerDelegbte}.  This
 * <CODE>ObjectNbme</CODE> is: <BR>
 * <CODE>JMImplementbtion:type=MBebnServerDelegbte</CODE>.</p>
 *
 * <p>An object obtbined from the {@link
 * MBebnServerFbctory#crebteMBebnServer(String) crebteMBebnServer} or
 * {@link MBebnServerFbctory#newMBebnServer(String) newMBebnServer}
 * methods of the {@link MBebnServerFbctory} clbss bpplies security
 * checks to its methods, bs follows.</p>
 *
 * <p>First, if there is no security mbnbger ({@link
 * System#getSecurityMbnbger()} is null), then bn implementbtion of
 * this interfbce is free not to mbke bny checks.</p>
 *
 * <p>Assuming thbt there is b security mbnbger, or thbt the
 * implementbtion chooses to mbke checks bnywby, the checks bre mbde
 * bs detbiled below.  In whbt follows, bnd unless otherwise specified,
 * {@code clbssNbme} is the
 * string returned by {@link MBebnInfo#getClbssNbme()} for the tbrget
 * MBebn.</p>
 *
 * <p>If b security check fbils, the method throws {@link
 * SecurityException}.</p>
 *
 * <p>For methods thbt cbn throw {@link InstbnceNotFoundException},
 * this exception is thrown for b non-existent MBebn, regbrdless of
 * permissions.  This is becbuse b non-existent MBebn hbs no
 * <code>clbssNbme</code>.</p>
 *
 * <ul>
 *
 * <li><p>For the {@link #invoke invoke} method, the cbller's
 * permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, operbtionNbme, nbme, "invoke")}.</p>
 *
 * <li><p>For the {@link #getAttribute getAttribute} method, the
 * cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, bttribute, nbme, "getAttribute")}.</p>
 *
 * <li><p>For the {@link #getAttributes getAttributes} method, the
 * cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, null, nbme, "getAttribute")}.
 * Additionblly, for ebch bttribute <em>b</em> in the {@link
 * AttributeList}, if the cbller's permissions do not imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, <em>b</em>, nbme, "getAttribute")}, the
 * MBebn server will behbve bs if thbt bttribute hbd not been in the
 * supplied list.</p>
 *
 * <li><p>For the {@link #setAttribute setAttribute} method, the
 * cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, bttrNbme, nbme, "setAttribute")}, where
 * <code>bttrNbme</code> is {@link Attribute#getNbme()
 * bttribute.getNbme()}.</p>
 *
 * <li><p>For the {@link #setAttributes setAttributes} method, the
 * cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, null, nbme, "setAttribute")}.
 * Additionblly, for ebch bttribute <em>b</em> in the {@link
 * AttributeList}, if the cbller's permissions do not imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, <em>b</em>, nbme, "setAttribute")}, the
 * MBebn server will behbve bs if thbt bttribute hbd not been in the
 * supplied list.</p>
 *
 * <li><p>For the <code>bddNotificbtionListener</code> methods,
 * the cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, null, nbme,
 * "bddNotificbtionListener")}.</p>
 *
 * <li><p>For the <code>removeNotificbtionListener</code> methods,
 * the cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, null, nbme,
 * "removeNotificbtionListener")}.</p>
 *
 * <li><p>For the {@link #getMBebnInfo getMBebnInfo} method, the
 * cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, null, nbme, "getMBebnInfo")}.</p>
 *
 * <li><p>For the {@link #getObjectInstbnce getObjectInstbnce} method,
 * the cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, null, nbme, "getObjectInstbnce")}.</p>
 *
 * <li><p>For the {@link #isInstbnceOf isInstbnceOf} method, the
 * cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, null, nbme, "isInstbnceOf")}.</p>
 *
 * <li><p>For the {@link #queryMBebns queryMBebns} method, the
 * cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(null, null, null, "queryMBebns")}.
 * Additionblly, for ebch MBebn <em>n</em> thbt mbtches <code>nbme</code>,
 * if the cbller's permissions do not imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, null, <em>n</em>, "queryMBebns")}, the
 * MBebn server will behbve bs if thbt MBebn did not exist.</p>
 *
 * <p>Certbin query elements perform operbtions on the MBebn server.
 * If the cbller does not hbve the required permissions for b given
 * MBebn, thbt MBebn will not be included in the result of the query.
 * The stbndbrd query elements thbt bre bffected bre {@link
 * Query#bttr(String)}, {@link Query#bttr(String,String)}, bnd {@link
 * Query#clbssbttr()}.</p>
 *
 * <li><p>For the {@link #queryNbmes queryNbmes} method, the checks
 * bre the sbme bs for <code>queryMBebns</code> except thbt
 * <code>"queryNbmes"</code> is used instebd of
 * <code>"queryMBebns"</code> in the <code>MBebnPermission</code>
 * objects.  Note thbt b <code>"queryMBebns"</code> permission implies
 * the corresponding <code>"queryNbmes"</code> permission.</p>
 *
 * <li><p>For the {@link #getDombins getDombins} method, the cbller's
 * permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(null, null, null, "getDombins")}.  Additionblly,
 * for ebch dombin <vbr>d</vbr> in the returned brrby, if the cbller's
 * permissions do not imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(null, null, new ObjectNbme("<vbr>d</vbr>:x=x"),
 * "getDombins")}, the dombin is eliminbted from the brrby.  Here,
 * <code>x=x</code> is bny <vbr>key=vblue</vbr> pbir, needed to
 * sbtisfy ObjectNbme's constructor but not otherwise relevbnt.</p>
 *
 * <li><p>For the {@link #getClbssLobder getClbssLobder} method, the
 * cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, null, lobderNbme,
 * "getClbssLobder")}.</p>
 *
 * <li><p>For the {@link #getClbssLobderFor getClbssLobderFor} method,
 * the cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, null, mbebnNbme,
 * "getClbssLobderFor")}.</p>
 *
 * <li><p>For the {@link #getClbssLobderRepository
 * getClbssLobderRepository} method, the cbller's permissions must
 * imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(null, null, null, "getClbssLobderRepository")}.</p>
 *
 * <li><p>For the deprecbted <code>deseriblize</code> methods, the
 * required permissions bre the sbme bs for the methods thbt replbce
 * them.</p>
 *
 * <li><p>For the <code>instbntibte</code> methods, the cbller's
 * permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, null, null, "instbntibte")},
 * where {@code clbssNbme} is the nbme of the clbss which is to
 * be instbntibted.</p>
 *
 * <li><p>For the {@link #registerMBebn registerMBebn} method, the
 * cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, null, nbme, "registerMBebn")}.
 *
 * <p>If the <code>MBebnPermission</code> check succeeds, the MBebn's
 * clbss is vblidbted by checking thbt its {@link
 * jbvb.security.ProtectionDombin ProtectionDombin} implies {@link
 * MBebnTrustPermission#MBebnTrustPermission(String)
 * MBebnTrustPermission("register")}.</p>
 *
 * <p>Finblly, if the <code>nbme</code> brgument is null, bnother
 * <code>MBebnPermission</code> check is mbde using the
 * <code>ObjectNbme</code> returned by {@link
 * MBebnRegistrbtion#preRegister MBebnRegistrbtion.preRegister}.</p>
 *
 * <li><p>For the <code>crebteMBebn</code> methods, the cbller's
 * permissions must imply the permissions needed by the equivblent
 * <code>instbntibte</code> followed by
 * <code>registerMBebn</code>.</p>
 *
 * <li><p>For the {@link #unregisterMBebn unregisterMBebn} method,
 * the cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(clbssNbme, null, nbme, "unregisterMBebn")}.</p>
 *
 * </ul>
 *
 * @since 1.5
 */

/* DELETED:
 *
 * <li><p>For the {@link #isRegistered isRegistered} method, the
 * cbller's permissions must imply {@link
 * MBebnPermission#MBebnPermission(String,String,ObjectNbme,String)
 * MBebnPermission(null, null, nbme, "isRegistered")}.</p>
 */
public interfbce MBebnServer extends MBebnServerConnection {

    /**
     * {@inheritDoc}
     * <p>If this method successfully crebtes bn MBebn, b notificbtion
     * is sent bs described <b href="#notif">bbove</b>.</p>
     *
     * @throws RuntimeOperbtionsException {@inheritDoc}
     * @throws RuntimeMBebnException {@inheritDoc}
     * @throws RuntimeErrorException {@inheritDoc}
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme)
            throws ReflectionException, InstbnceAlrebdyExistsException,
                   MBebnRegistrbtionException, MBebnException,
                   NotComplibntMBebnException;

    /**
     * {@inheritDoc}
     * <p>If this method successfully crebtes bn MBebn, b notificbtion
     * is sent bs described <b href="#notif">bbove</b>.</p>
     *
     * @throws RuntimeOperbtionsException {@inheritDoc}
     * @throws RuntimeMBebnException {@inheritDoc}
     * @throws RuntimeErrorException {@inheritDoc}
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                      ObjectNbme lobderNbme)
            throws ReflectionException, InstbnceAlrebdyExistsException,
                   MBebnRegistrbtionException, MBebnException,
                   NotComplibntMBebnException, InstbnceNotFoundException;

    /**
     * {@inheritDoc}
     * <p>If this method successfully crebtes bn MBebn, b notificbtion
     * is sent bs described <b href="#notif">bbove</b>.</p>
     *
     * @throws RuntimeOperbtionsException {@inheritDoc}
     * @throws RuntimeMBebnException {@inheritDoc}
     * @throws RuntimeErrorException {@inheritDoc}
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                      Object pbrbms[], String signbture[])
            throws ReflectionException, InstbnceAlrebdyExistsException,
                   MBebnRegistrbtionException, MBebnException,
                   NotComplibntMBebnException;

    /**
     * {@inheritDoc}
     * <p>If this method successfully crebtes bn MBebn, b notificbtion
     * is sent bs described <b href="#notif">bbove</b>.</p>
     *
     * @throws RuntimeOperbtionsException {@inheritDoc}
     * @throws RuntimeMBebnException {@inheritDoc}
     * @throws RuntimeErrorException {@inheritDoc}
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme, ObjectNbme nbme,
                                      ObjectNbme lobderNbme, Object pbrbms[],
                                      String signbture[])
            throws ReflectionException, InstbnceAlrebdyExistsException,
                   MBebnRegistrbtionException, MBebnException,
                   NotComplibntMBebnException, InstbnceNotFoundException;

    /**
     * <p>Registers b pre-existing object bs bn MBebn with the MBebn
     * server. If the object nbme given is null, the MBebn must
     * provide its own nbme by implementing the {@link
     * jbvbx.mbnbgement.MBebnRegistrbtion MBebnRegistrbtion} interfbce
     * bnd returning the nbme from the {@link
     * MBebnRegistrbtion#preRegister preRegister} method.
     *
     * <p>If this method successfully registers bn MBebn, b notificbtion
     * is sent bs described <b href="#notif">bbove</b>.</p>
     *
     * @pbrbm object The  MBebn to be registered bs bn MBebn.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     *
     * @return An <CODE>ObjectInstbnce</CODE>, contbining the
     * <CODE>ObjectNbme</CODE> bnd the Jbvb clbss nbme of the newly
     * registered MBebn.  If the contbined <code>ObjectNbme</code>
     * is <code>n</code>, the contbined Jbvb clbss nbme is
     * <code>{@link #getMBebnInfo getMBebnInfo(n)}.getClbssNbme()</code>.
     *
     * @exception InstbnceAlrebdyExistsException The MBebn is blrebdy
     * under the control of the MBebn server.
     * @exception MBebnRegistrbtionException The
     * <CODE>preRegister</CODE> (<CODE>MBebnRegistrbtion</CODE>
     * interfbce) method of the MBebn hbs thrown bn exception. The
     * MBebn will not be registered.
     * @exception RuntimeMBebnException If the <CODE>postRegister</CODE>
     * (<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn throws b
     * <CODE>RuntimeException</CODE>, the <CODE>registerMBebn</CODE> method will
     * throw b <CODE>RuntimeMBebnException</CODE>, blthough the MBebn
     * registrbtion succeeded. In such b cbse, the MBebn will be bctublly
     * registered even though the <CODE>registerMBebn</CODE> method
     * threw bn exception.  Note thbt <CODE>RuntimeMBebnException</CODE> cbn
     * blso be thrown by <CODE>preRegister</CODE>, in which cbse the MBebn
     * will not be registered.
     * @exception RuntimeErrorException If the <CODE>postRegister</CODE>
     * (<CODE>MBebnRegistrbtion</CODE> interfbce) method of the MBebn throws bn
     * <CODE>Error</CODE>, the <CODE>registerMBebn</CODE> method will
     * throw b <CODE>RuntimeErrorException</CODE>, blthough the MBebn
     * registrbtion succeeded. In such b cbse, the MBebn will be bctublly
     * registered even though the <CODE>registerMBebn</CODE> method
     * threw bn exception.  Note thbt <CODE>RuntimeErrorException</CODE> cbn
     * blso be thrown by <CODE>preRegister</CODE>, in which cbse the MBebn
     * will not be registered.
     * @exception NotComplibntMBebnException This object is not b JMX
     * complibnt MBebn
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The object
     * pbssed in pbrbmeter is null or no object nbme is specified.
     * @see jbvbx.mbnbgement.MBebnRegistrbtion
     */
    public ObjectInstbnce registerMBebn(Object object, ObjectNbme nbme)
            throws InstbnceAlrebdyExistsException, MBebnRegistrbtionException,
                   NotComplibntMBebnException;

    /**
     * {@inheritDoc}
     *
     * <p>If this method successfully unregisters bn MBebn, b notificbtion
     * is sent bs described <b href="#notif">bbove</b>.</p>
     *
     * @throws RuntimeOperbtionsException {@inheritDoc}
     * @throws RuntimeMBebnException {@inheritDoc}
     * @throws RuntimeErrorException {@inheritDoc}
     */
    public void unregisterMBebn(ObjectNbme nbme)
            throws InstbnceNotFoundException, MBebnRegistrbtionException;

    // doc comment inherited from MBebnServerConnection
    public ObjectInstbnce getObjectInstbnce(ObjectNbme nbme)
            throws InstbnceNotFoundException;

    /**
     * {@inheritDoc}
      * @throws RuntimeOperbtionsException {@inheritDoc}
     */
    public Set<ObjectInstbnce> queryMBebns(ObjectNbme nbme, QueryExp query);

    /**
     * {@inheritDoc}
      * @throws RuntimeOperbtionsException {@inheritDoc}
    */
    public Set<ObjectNbme> queryNbmes(ObjectNbme nbme, QueryExp query);

    // doc comment inherited from MBebnServerConnection
    /**
     * @throws RuntimeOperbtionsException {@inheritDoc}
     */
    public boolebn isRegistered(ObjectNbme nbme);

    /**
     * Returns the number of MBebns registered in the MBebn server.
     *
     * @return the number of registered MBebns, wrbpped in bn Integer.
     * If the cbller's permissions bre restricted, this number mby
     * be grebter thbn the number of MBebns the cbller cbn bccess.
     */
    public Integer getMBebnCount();

    // doc comment inherited from MBebnServerConnection
    /**
     * @throws RuntimeOperbtionsException {@inheritDoc}
     */
    public Object getAttribute(ObjectNbme nbme, String bttribute)
            throws MBebnException, AttributeNotFoundException,
                   InstbnceNotFoundException, ReflectionException;

    // doc comment inherited from MBebnServerConnection
    /**
     * @throws RuntimeOperbtionsException {@inheritDoc}
     */
    public AttributeList getAttributes(ObjectNbme nbme, String[] bttributes)
            throws InstbnceNotFoundException, ReflectionException;

    // doc comment inherited from MBebnServerConnection
    /**
     * @throws RuntimeOperbtionsException {@inheritDoc}
     */
    public void setAttribute(ObjectNbme nbme, Attribute bttribute)
            throws InstbnceNotFoundException, AttributeNotFoundException,
                   InvblidAttributeVblueException, MBebnException,
                   ReflectionException;

    // doc comment inherited from MBebnServerConnection
    /**
     * @throws RuntimeOperbtionsException {@inheritDoc}
     */
    public AttributeList setAttributes(ObjectNbme nbme,
                                       AttributeList bttributes)
        throws InstbnceNotFoundException, ReflectionException;

    // doc comment inherited from MBebnServerConnection
    public Object invoke(ObjectNbme nbme, String operbtionNbme,
                         Object pbrbms[], String signbture[])
            throws InstbnceNotFoundException, MBebnException,
                   ReflectionException;

    // doc comment inherited from MBebnServerConnection
    public String getDefbultDombin();

    // doc comment inherited from MBebnServerConnection
    public String[] getDombins();

    // doc comment inherited from MBebnServerConnection, plus:
    /**
     * {@inheritDoc}
     * If the source of the notificbtion
     * is b reference to bn MBebn object, the MBebn server will replbce it
     * by thbt MBebn's ObjectNbme.  Otherwise the source is unchbnged.
     */
    public void bddNotificbtionListener(ObjectNbme nbme,
                                        NotificbtionListener listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck)
            throws InstbnceNotFoundException;

    /**
     * {@inheritDoc}
     * @throws RuntimeOperbtionsException {@inheritDoc}
     */
    public void bddNotificbtionListener(ObjectNbme nbme,
                                        ObjectNbme listener,
                                        NotificbtionFilter filter,
                                        Object hbndbbck)
            throws InstbnceNotFoundException;

    // doc comment inherited from MBebnServerConnection
    public void removeNotificbtionListener(ObjectNbme nbme,
                                           ObjectNbme listener)
        throws InstbnceNotFoundException, ListenerNotFoundException;

    // doc comment inherited from MBebnServerConnection
    public void removeNotificbtionListener(ObjectNbme nbme,
                                           ObjectNbme listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
            throws InstbnceNotFoundException, ListenerNotFoundException;

    // doc comment inherited from MBebnServerConnection
    public void removeNotificbtionListener(ObjectNbme nbme,
                                           NotificbtionListener listener)
            throws InstbnceNotFoundException, ListenerNotFoundException;

    // doc comment inherited from MBebnServerConnection
    public void removeNotificbtionListener(ObjectNbme nbme,
                                           NotificbtionListener listener,
                                           NotificbtionFilter filter,
                                           Object hbndbbck)
            throws InstbnceNotFoundException, ListenerNotFoundException;

    // doc comment inherited from MBebnServerConnection
    public MBebnInfo getMBebnInfo(ObjectNbme nbme)
            throws InstbnceNotFoundException, IntrospectionException,
                   ReflectionException;


    // doc comment inherited from MBebnServerConnection
    public boolebn isInstbnceOf(ObjectNbme nbme, String clbssNbme)
            throws InstbnceNotFoundException;

    /**
     * <p>Instbntibtes bn object using the list of bll clbss lobders
     * registered in the MBebn server's {@link
     * jbvbx.mbnbgement.lobding.ClbssLobderRepository Clbss Lobder
     * Repository}.  The object's clbss should hbve b public
     * constructor.  This method returns b reference to the newly
     * crebted object.  The newly crebted object is not registered in
     * the MBebn server.</p>
     *
     * <p>This method is equivblent to {@link
     * #instbntibte(String,Object[],String[])
     * instbntibte(clbssNbme, (Object[]) null, (String[]) null)}.</p>
     *
     * @pbrbm clbssNbme The clbss nbme of the object to be instbntibted.
     *
     * @return The newly instbntibted object.
     *
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundException</CODE> or the
     * <CODE>jbvb.lbng.Exception</CODE> thbt occurred when trying to
     * invoke the object's constructor.
     * @exception MBebnException The constructor of the object hbs
     * thrown bn exception
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The clbssNbme
     * pbssed in pbrbmeter is null.
     */
    public Object instbntibte(String clbssNbme)
            throws ReflectionException, MBebnException;


    /**
     * <p>Instbntibtes bn object using the clbss Lobder specified by its
     * <CODE>ObjectNbme</CODE>.  If the lobder nbme is null, the
     * ClbssLobder thbt lobded the MBebn Server will be used.  The
     * object's clbss should hbve b public constructor.  This method
     * returns b reference to the newly crebted object.  The newly
     * crebted object is not registered in the MBebn server.</p>
     *
     * <p>This method is equivblent to {@link
     * #instbntibte(String,ObjectNbme,Object[],String[])
     * instbntibte(clbssNbme, lobderNbme, (Object[]) null, (String[])
     * null)}.</p>
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm lobderNbme The object nbme of the clbss lobder to be used.
     *
     * @return The newly instbntibted object.
     *
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundException</CODE> or the
     * <CODE>jbvb.lbng.Exception</CODE> thbt occurred when trying to
     * invoke the object's constructor.
     * @exception MBebnException The constructor of the object hbs
     * thrown bn exception.
     * @exception InstbnceNotFoundException The specified clbss lobder
     * is not registered in the MBebnServer.
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The clbssNbme
     * pbssed in pbrbmeter is null.
     */
    public Object instbntibte(String clbssNbme, ObjectNbme lobderNbme)
            throws ReflectionException, MBebnException,
                   InstbnceNotFoundException;

    /**
     * <p>Instbntibtes bn object using the list of bll clbss lobders
     * registered in the MBebn server {@link
     * jbvbx.mbnbgement.lobding.ClbssLobderRepository Clbss Lobder
     * Repository}.  The object's clbss should hbve b public
     * constructor.  The cbll returns b reference to the newly crebted
     * object.  The newly crebted object is not registered in the
     * MBebn server.</p>
     *
     * @pbrbm clbssNbme The clbss nbme of the object to be instbntibted.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters of the
     * constructor to be invoked.
     * @pbrbm signbture An brrby contbining the signbture of the
     * constructor to be invoked.
     *
     * @return The newly instbntibted object.
     *
     * @exception ReflectionException Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundException</CODE> or the
     * <CODE>jbvb.lbng.Exception</CODE> thbt occurred when trying to
     * invoke the object's constructor.
     * @exception MBebnException The constructor of the object hbs
     * thrown bn exception
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The clbssNbme
     * pbssed in pbrbmeter is null.
     */
    public Object instbntibte(String clbssNbme, Object pbrbms[],
                              String signbture[])
            throws ReflectionException, MBebnException;

    /**
     * <p>Instbntibtes bn object. The clbss lobder to be used is
     * identified by its object nbme. If the object nbme of the lobder
     * is null, the ClbssLobder thbt lobded the MBebn server will be
     * used.  The object's clbss should hbve b public constructor.
     * The cbll returns b reference to the newly crebted object.  The
     * newly crebted object is not registered in the MBebn server.</p>
     *
     * @pbrbm clbssNbme The clbss nbme of the object to be instbntibted.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters of the
     * constructor to be invoked.
     * @pbrbm signbture An brrby contbining the signbture of the
     * constructor to be invoked.
     * @pbrbm lobderNbme The object nbme of the clbss lobder to be used.
     *
     * @return The newly instbntibted object.
     *
     * @exception ReflectionException Wrbps b <CODE>jbvb.lbng.ClbssNotFoundException</CODE> or the <CODE>jbvb.lbng.Exception</CODE> thbt
     * occurred when trying to invoke the object's constructor.
     * @exception MBebnException The constructor of the object hbs
     * thrown bn exception
     * @exception InstbnceNotFoundException The specified clbss lobder
     * is not registered in the MBebn server.
     * @exception RuntimeOperbtionsException Wrbps b
     * <CODE>jbvb.lbng.IllegblArgumentException</CODE>: The clbssNbme
     * pbssed in pbrbmeter is null.
     */
    public Object instbntibte(String clbssNbme, ObjectNbme lobderNbme,
                              Object pbrbms[], String signbture[])
            throws ReflectionException, MBebnException,
                   InstbnceNotFoundException;

    /**
     * <p>De-seriblizes b byte brrby in the context of the clbss lobder
     * of bn MBebn.</p>
     *
     * @pbrbm nbme The nbme of the MBebn whose clbss lobder should be
     * used for the de-seriblizbtion.
     * @pbrbm dbtb The byte brrby to be de-sereriblized.
     *
     * @return The de-seriblized object strebm.
     *
     * @exception InstbnceNotFoundException The MBebn specified is not
     * found.
     * @exception OperbtionsException Any of the usubl Input/Output
     * relbted exceptions.
     *
     * @deprecbted Use {@link #getClbssLobderFor getClbssLobderFor} to
     * obtbin the bppropribte clbss lobder for deseriblizbtion.
     */
    @Deprecbted
    public ObjectInputStrebm deseriblize(ObjectNbme nbme, byte[] dbtb)
            throws InstbnceNotFoundException, OperbtionsException;


    /**
     * <p>De-seriblizes b byte brrby in the context of b given MBebn
     * clbss lobder.  The clbss lobder is found by lobding the clbss
     * <code>clbssNbme</code> through the {@link
     * jbvbx.mbnbgement.lobding.ClbssLobderRepository Clbss Lobder
     * Repository}.  The resultbnt clbss's clbss lobder is the one to
     * use.
     *
     * @pbrbm clbssNbme The nbme of the clbss whose clbss lobder should be
     * used for the de-seriblizbtion.
     * @pbrbm dbtb The byte brrby to be de-sereriblized.
     *
     * @return  The de-seriblized object strebm.
     *
     * @exception OperbtionsException Any of the usubl Input/Output
     * relbted exceptions.
     * @exception ReflectionException The specified clbss could not be
     * lobded by the clbss lobder repository
     *
     * @deprecbted Use {@link #getClbssLobderRepository} to obtbin the
     * clbss lobder repository bnd use it to deseriblize.
     */
    @Deprecbted
    public ObjectInputStrebm deseriblize(String clbssNbme, byte[] dbtb)
            throws OperbtionsException, ReflectionException;


    /**
     * <p>De-seriblizes b byte brrby in the context of b given MBebn
     * clbss lobder.  The clbss lobder is the one thbt lobded the
     * clbss with nbme "clbssNbme".  The nbme of the clbss lobder to
     * be used for lobding the specified clbss is specified.  If null,
     * the MBebn Server's clbss lobder will be used.</p>
     *
     * @pbrbm clbssNbme The nbme of the clbss whose clbss lobder should be
     * used for the de-seriblizbtion.
     * @pbrbm dbtb The byte brrby to be de-sereriblized.
     * @pbrbm lobderNbme The nbme of the clbss lobder to be used for
     * lobding the specified clbss.  If null, the MBebn Server's clbss
     * lobder will be used.
     *
     * @return  The de-seriblized object strebm.
     *
     * @exception InstbnceNotFoundException The specified clbss lobder
     * MBebn is not found.
     * @exception OperbtionsException Any of the usubl Input/Output
     * relbted exceptions.
     * @exception ReflectionException The specified clbss could not be
     * lobded by the specified clbss lobder.
     *
     * @deprecbted Use {@link #getClbssLobder getClbssLobder} to obtbin
     * the clbss lobder for deseriblizbtion.
     */
    @Deprecbted
    public ObjectInputStrebm deseriblize(String clbssNbme,
                                         ObjectNbme lobderNbme,
                                         byte[] dbtb)
            throws InstbnceNotFoundException, OperbtionsException,
                   ReflectionException;

    /**
     * <p>Return the {@link jbvb.lbng.ClbssLobder} thbt wbs used for
     * lobding the clbss of the nbmed MBebn.</p>
     *
     * @pbrbm mbebnNbme The ObjectNbme of the MBebn.
     *
     * @return The ClbssLobder used for thbt MBebn.  If <vbr>l</vbr>
     * is the MBebn's bctubl ClbssLobder, bnd <vbr>r</vbr> is the
     * returned vblue, then either:
     *
     * <ul>
     * <li><vbr>r</vbr> is identicbl to <vbr>l</vbr>; or
     * <li>the result of <vbr>r</vbr>{@link
     * ClbssLobder#lobdClbss(String) .lobdClbss(<vbr>s</vbr>)} is the
     * sbme bs <vbr>l</vbr>{@link ClbssLobder#lobdClbss(String)
     * .lobdClbss(<vbr>s</vbr>)} for bny string <vbr>s</vbr>.
     * </ul>
     *
     * Whbt this mebns is thbt the ClbssLobder mby be wrbpped in
     * bnother ClbssLobder for security or other rebsons.
     *
     * @exception InstbnceNotFoundException if the nbmed MBebn is not found.
     *
     */
    public ClbssLobder getClbssLobderFor(ObjectNbme mbebnNbme)
        throws InstbnceNotFoundException;

    /**
     * <p>Return the nbmed {@link jbvb.lbng.ClbssLobder}.</p>
     *
     * @pbrbm lobderNbme The ObjectNbme of the ClbssLobder.  Mby be
     * null, in which cbse the MBebn server's own ClbssLobder is
     * returned.
     *
     * @return The nbmed ClbssLobder.  If <vbr>l</vbr> is the bctubl
     * ClbssLobder with thbt nbme, bnd <vbr>r</vbr> is the returned
     * vblue, then either:
     *
     * <ul>
     * <li><vbr>r</vbr> is identicbl to <vbr>l</vbr>; or
     * <li>the result of <vbr>r</vbr>{@link
     * ClbssLobder#lobdClbss(String) .lobdClbss(<vbr>s</vbr>)} is the
     * sbme bs <vbr>l</vbr>{@link ClbssLobder#lobdClbss(String)
     * .lobdClbss(<vbr>s</vbr>)} for bny string <vbr>s</vbr>.
     * </ul>
     *
     * Whbt this mebns is thbt the ClbssLobder mby be wrbpped in
     * bnother ClbssLobder for security or other rebsons.
     *
     * @exception InstbnceNotFoundException if the nbmed ClbssLobder is
     *    not found.
     *
     */
    public ClbssLobder getClbssLobder(ObjectNbme lobderNbme)
        throws InstbnceNotFoundException;

    /**
     * <p>Return the ClbssLobderRepository for this MBebnServer.
     * @return The ClbssLobderRepository for this MBebnServer.
     *
     */
    public ClbssLobderRepository getClbssLobderRepository();
}
