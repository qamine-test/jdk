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

pbckbge jbvbx.mbnbgement.remote.rmi;

import jbvb.io.Closebble;
import jbvb.io.IOException;
import jbvb.rmi.MbrshblledObject;
import jbvb.rmi.Remote;
import jbvb.util.Set;

import jbvbx.mbnbgement.AttributeList;
import jbvbx.mbnbgement.AttributeNotFoundException;
import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.IntrospectionException;
import jbvbx.mbnbgement.InvblidAttributeVblueException;
import jbvbx.mbnbgement.ListenerNotFoundException;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.MBebnRegistrbtionException;
import jbvbx.mbnbgement.MBebnServerConnection;
import jbvbx.mbnbgement.NotComplibntMBebnException;

import jbvbx.mbnbgement.ObjectInstbnce;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.ReflectionException;
import jbvbx.mbnbgement.RuntimeMBebnException;
import jbvbx.mbnbgement.RuntimeOperbtionsException;
import jbvbx.mbnbgement.remote.NotificbtionResult;
import jbvbx.security.buth.Subject;

/**
 * <p>RMI object used to forwbrd bn MBebnServer request from b client
 * to its MBebnServer implementbtion on the server side.  There is one
 * Remote object implementing this interfbce for ebch remote client
 * connected to bn RMI connector.</p>
 *
 * <p>User code does not usublly refer to this interfbce.  It is
 * specified bs pbrt of the public API so thbt different
 * implementbtions of thbt API will interoperbte.</p>
 *
 * <p>To ensure thbt client pbrbmeters will be deseriblized bt the
 * server side with the correct clbsslobder, client pbrbmeters such bs
 * pbrbmeters used to invoke b method bre wrbpped in b {@link
 * MbrshblledObject}.  An implementbtion of this interfbce must first
 * get the bppropribte clbss lobder for the operbtion bnd its tbrget,
 * then deseriblize the mbrshblled pbrbmeters with this clbsslobder.
 * Except bs noted, b pbrbmeter thbt is b
 * <code>MbrshblledObject</code> or <code>MbrshblledObject[]</code>
 * must not be null; the behbvior is unspecified if it is.</p>
 *
 * <p>Clbss lobding bspects bre detbiled in the
 * <b href="{@docRoot}/../technotes/guides/jmx/JMX_1_4_specificbtion.pdf">
 * JMX Specificbtion, version 1.4</b> PDF document.</p>
 *
 * <p>Most methods in this interfbce pbrbllel methods in the {@link
 * MBebnServerConnection} interfbce.  Where bn bspect of the behbvior
 * of b method is not specified here, it is the sbme bs in the
 * corresponding <code>MBebnServerConnection</code> method.
 *
 * @since 1.5
 */
/*
 * Notice thbt we omit the type pbrbmeter from MbrshblledObject everywhere,
 * even though it would bdd useful informbtion to the documentbtion.  The
 * rebson is thbt it wbs only bdded in Mustbng (Jbvb SE 6), wherebs versions
 * 1.4 bnd 2.0 of the JMX API must be implementbble on Tiger per our
 * commitments for JSR 255.  This is blso why we suppress rbwtypes wbrnings.
 */
@SuppressWbrnings("rbwtypes")
public interfbce RMIConnection extends Closebble, Remote {
    /**
     * <p>Returns the connection ID.  This string is different for
     * every open connection to b given RMI connector server.</p>
     *
     * @return the connection ID
     *
     * @see RMIConnector#connect RMIConnector.connect
     *
     * @throws IOException if b generbl communicbtion exception occurred.
     */
    public String getConnectionId() throws IOException;

    /**
     * <p>Closes this connection.  On return from this method, the RMI
     * object implementing this interfbce is unexported, so further
     * remote cblls to it will fbil.</p>
     *
     * @throws IOException if the connection could not be closed,
     * or the Remote object could not be unexported, or there wbs b
     * communicbtion fbilure when trbnsmitting the remote close
     * request.
     */
    public void close() throws IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#crebteMBebn(String,
     * ObjectNbme)}.
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return An <code>ObjectInstbnce</code>, contbining the
     * <code>ObjectNbme</code> bnd the Jbvb clbss nbme of the newly
     * instbntibted MBebn.  If the contbined <code>ObjectNbme</code>
     * is <code>n</code>, the contbined Jbvb clbss nbme is
     * <code>{@link #getMBebnInfo getMBebnInfo(n)}.getClbssNbme()</code>.
     *
     * @throws ReflectionException Wrbps b
     * <code>jbvb.lbng.ClbssNotFoundException</code> or b
     * <code>jbvb.lbng.Exception</code> thbt occurred
     * when trying to invoke the MBebn's constructor.
     * @throws InstbnceAlrebdyExistsException The MBebn is blrebdy
     * under the control of the MBebn server.
     * @throws MBebnRegistrbtionException The
     * <code>preRegister</code> (<code>MBebnRegistrbtion</code>
     * interfbce) method of the MBebn hbs thrown bn exception. The
     * MBebn will not be registered.
     * @throws MBebnException The constructor of the MBebn hbs
     * thrown bn exception.
     * @throws NotComplibntMBebnException This clbss is not b JMX
     * complibnt MBebn.
     * @throws RuntimeOperbtionsException Wrbps b
     * <code>jbvb.lbng.IllegblArgumentException</code>: The clbssNbme
     * pbssed in pbrbmeter is null, the <code>ObjectNbme</code> pbssed
     * in pbrbmeter contbins b pbttern or no <code>ObjectNbme</code>
     * is specified for the MBebn.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     */
    public ObjectInstbnce crebteMBebn(String clbssNbme,
                                      ObjectNbme nbme,
                                      Subject delegbtionSubject)
        throws
        ReflectionException,
        InstbnceAlrebdyExistsException,
        MBebnRegistrbtionException,
        MBebnException,
        NotComplibntMBebnException,
        IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#crebteMBebn(String,
     * ObjectNbme, ObjectNbme)}.
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     * @pbrbm lobderNbme The object nbme of the clbss lobder to be used.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return An <code>ObjectInstbnce</code>, contbining the
     * <code>ObjectNbme</code> bnd the Jbvb clbss nbme of the newly
     * instbntibted MBebn.  If the contbined <code>ObjectNbme</code>
     * is <code>n</code>, the contbined Jbvb clbss nbme is
     * <code>{@link #getMBebnInfo getMBebnInfo(n)}.getClbssNbme()</code>.
     *
     * @throws ReflectionException Wrbps b
     * <code>jbvb.lbng.ClbssNotFoundException</code> or b
     * <code>jbvb.lbng.Exception</code> thbt occurred when trying to
     * invoke the MBebn's constructor.
     * @throws InstbnceAlrebdyExistsException The MBebn is blrebdy
     * under the control of the MBebn server.
     * @throws MBebnRegistrbtionException The
     * <code>preRegister</code> (<code>MBebnRegistrbtion</code>
     * interfbce) method of the MBebn hbs thrown bn exception. The
     * MBebn will not be registered.
     * @throws MBebnException The constructor of the MBebn hbs
     * thrown bn exception.
     * @throws NotComplibntMBebnException This clbss is not b JMX
     * complibnt MBebn.
     * @throws InstbnceNotFoundException The specified clbss lobder
     * is not registered in the MBebn server.
     * @throws RuntimeOperbtionsException Wrbps b
     * <code>jbvb.lbng.IllegblArgumentException</code>: The clbssNbme
     * pbssed in pbrbmeter is null, the <code>ObjectNbme</code> pbssed
     * in pbrbmeter contbins b pbttern or no <code>ObjectNbme</code>
     * is specified for the MBebn.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     */
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
        IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#crebteMBebn(String,
     * ObjectNbme, Object[], String[])}.  The <code>Object[]</code>
     * pbrbmeter is wrbpped in b <code>MbrshblledObject</code>.
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters of the
     * constructor to be invoked, encbpsulbted into b
     * <code>MbrshblledObject</code>.  The encbpsulbted brrby cbn be
     * null, equivblent to bn empty brrby.
     * @pbrbm signbture An brrby contbining the signbture of the
     * constructor to be invoked.  Cbn be null, equivblent to bn empty
     * brrby.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return An <code>ObjectInstbnce</code>, contbining the
     * <code>ObjectNbme</code> bnd the Jbvb clbss nbme of the newly
     * instbntibted MBebn.  If the contbined <code>ObjectNbme</code>
     * is <code>n</code>, the contbined Jbvb clbss nbme is
     * <code>{@link #getMBebnInfo getMBebnInfo(n)}.getClbssNbme()</code>.
     *
     * @throws ReflectionException Wrbps b
     * <code>jbvb.lbng.ClbssNotFoundException</code> or b
     * <code>jbvb.lbng.Exception</code> thbt occurred when trying to
     * invoke the MBebn's constructor.
     * @throws InstbnceAlrebdyExistsException The MBebn is blrebdy
     * under the control of the MBebn server.
     * @throws MBebnRegistrbtionException The
     * <code>preRegister</code> (<code>MBebnRegistrbtion</code>
     * interfbce) method of the MBebn hbs thrown bn exception. The
     * MBebn will not be registered.
     * @throws MBebnException The constructor of the MBebn hbs
     * thrown bn exception.
     * @throws NotComplibntMBebnException This clbss is not b JMX
     * complibnt MBebn.
     * @throws RuntimeOperbtionsException Wrbps b
     * <code>jbvb.lbng.IllegblArgumentException</code>: The clbssNbme
     * pbssed in pbrbmeter is null, the <code>ObjectNbme</code> pbssed
     * in pbrbmeter contbins b pbttern, or no <code>ObjectNbme</code>
     * is specified for the MBebn.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     */
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
        IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#crebteMBebn(String,
     * ObjectNbme, ObjectNbme, Object[], String[])}.  The
     * <code>Object[]</code> pbrbmeter is wrbpped in b
     * <code>MbrshblledObject</code>.
     *
     * @pbrbm clbssNbme The clbss nbme of the MBebn to be instbntibted.
     * @pbrbm nbme The object nbme of the MBebn. Mby be null.
     * @pbrbm lobderNbme The object nbme of the clbss lobder to be used.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters of the
     * constructor to be invoked, encbpsulbted into b
     * <code>MbrshblledObject</code>.  The encbpsulbted brrby cbn be
     * null, equivblent to bn empty brrby.
     * @pbrbm signbture An brrby contbining the signbture of the
     * constructor to be invoked.  Cbn be null, equivblent to bn empty
     * brrby.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return An <code>ObjectInstbnce</code>, contbining the
     * <code>ObjectNbme</code> bnd the Jbvb clbss nbme of the newly
     * instbntibted MBebn.  If the contbined <code>ObjectNbme</code>
     * is <code>n</code>, the contbined Jbvb clbss nbme is
     * <code>{@link #getMBebnInfo getMBebnInfo(n)}.getClbssNbme()</code>.
     *
     * @throws ReflectionException Wrbps b
     * <code>jbvb.lbng.ClbssNotFoundException</code> or b
     * <code>jbvb.lbng.Exception</code> thbt occurred when trying to
     * invoke the MBebn's constructor.
     * @throws InstbnceAlrebdyExistsException The MBebn is blrebdy
     * under the control of the MBebn server.
     * @throws MBebnRegistrbtionException The
     * <code>preRegister</code> (<code>MBebnRegistrbtion</code>
     * interfbce) method of the MBebn hbs thrown bn exception. The
     * MBebn will not be registered.
     * @throws MBebnException The constructor of the MBebn hbs
     * thrown bn exception.
     * @throws NotComplibntMBebnException This clbss is not b JMX
     * complibnt MBebn.
     * @throws InstbnceNotFoundException The specified clbss lobder
     * is not registered in the MBebn server.
     * @throws RuntimeOperbtionsException Wrbps b
     * <code>jbvb.lbng.IllegblArgumentException</code>: The clbssNbme
     * pbssed in pbrbmeter is null, the <code>ObjectNbme</code> pbssed
     * in pbrbmeter contbins b pbttern, or no <code>ObjectNbme</code>
     * is specified for the MBebn.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     */
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
        IOException;

    /**
     * Hbndles the method
     * {@link jbvbx.mbnbgement.MBebnServerConnection#unregisterMBebn(ObjectNbme)}.
     *
     * @pbrbm nbme The object nbme of the MBebn to be unregistered.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @throws InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @throws MBebnRegistrbtionException The preDeregister
     * ((<code>MBebnRegistrbtion</code> interfbce) method of the MBebn
     * hbs thrown bn exception.
     * @throws RuntimeOperbtionsException Wrbps b
     * <code>jbvb.lbng.IllegblArgumentException</code>: The object
     * nbme in pbrbmeter is null or the MBebn you bre when trying to
     * unregister is the {@link jbvbx.mbnbgement.MBebnServerDelegbte
     * MBebnServerDelegbte} MBebn.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     */
    public void unregisterMBebn(ObjectNbme nbme, Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        MBebnRegistrbtionException,
        IOException;

    /**
     * Hbndles the method
     * {@link jbvbx.mbnbgement.MBebnServerConnection#getObjectInstbnce(ObjectNbme)}.
     *
     * @pbrbm nbme The object nbme of the MBebn.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return The <code>ObjectInstbnce</code> bssocibted with the MBebn
     * specified by <vbr>nbme</vbr>.  The contbined <code>ObjectNbme</code>
     * is <code>nbme</code> bnd the contbined clbss nbme is
     * <code>{@link #getMBebnInfo getMBebnInfo(nbme)}.getClbssNbme()</code>.
     *
     * @throws InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @throws RuntimeOperbtionsException Wrbps b
     * <code>jbvb.lbng.IllegblArgumentException</code>: The object
     * nbme in pbrbmeter is null.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     */
    public ObjectInstbnce getObjectInstbnce(ObjectNbme nbme,
                                            Subject delegbtionSubject)
        throws InstbnceNotFoundException, IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#queryMBebns(ObjectNbme,
     * QueryExp)}.  The <code>QueryExp</code> is wrbpped in b
     * <code>MbrshblledObject</code>.
     *
     * @pbrbm nbme The object nbme pbttern identifying the MBebns to
     * be retrieved. If null or no dombin bnd key properties bre
     * specified, bll the MBebns registered will be retrieved.
     * @pbrbm query The query expression to be bpplied for selecting
     * MBebns, encbpsulbted into b <code>MbrshblledObject</code>. If
     * the <code>MbrshblledObject</code> encbpsulbtes b null vblue no
     * query expression will be bpplied for selecting MBebns.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return A set contbining the <code>ObjectInstbnce</code>
     * objects for the selected MBebns.  If no MBebn sbtisfies the
     * query bn empty list is returned.
     *
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     */
    public Set<ObjectInstbnce>
        queryMBebns(ObjectNbme nbme,
                    MbrshblledObject query,
                    Subject delegbtionSubject)
        throws IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#queryNbmes(ObjectNbme,
     * QueryExp)}.  The <code>QueryExp</code> is wrbpped in b
     * <code>MbrshblledObject</code>.
     *
     * @pbrbm nbme The object nbme pbttern identifying the MBebn nbmes
     * to be retrieved. If null or no dombin bnd key properties bre
     * specified, the nbme of bll registered MBebns will be retrieved.
     * @pbrbm query The query expression to be bpplied for selecting
     * MBebns, encbpsulbted into b <code>MbrshblledObject</code>. If
     * the <code>MbrshblledObject</code> encbpsulbtes b null vblue no
     * query expression will be bpplied for selecting MBebns.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return A set contbining the ObjectNbmes for the MBebns
     * selected.  If no MBebn sbtisfies the query, bn empty list is
     * returned.
     *
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     */
    public Set<ObjectNbme>
        queryNbmes(ObjectNbme nbme,
                   MbrshblledObject query,
                   Subject delegbtionSubject)
        throws IOException;

    /**
     * Hbndles the method
     * {@link jbvbx.mbnbgement.MBebnServerConnection#isRegistered(ObjectNbme)}.
     *
     * @pbrbm nbme The object nbme of the MBebn to be checked.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return True if the MBebn is blrebdy registered in the MBebn
     * server, fblse otherwise.
     *
     * @throws RuntimeOperbtionsException Wrbps b
     * <code>jbvb.lbng.IllegblArgumentException</code>: The object
     * nbme in pbrbmeter is null.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     */
    public boolebn isRegistered(ObjectNbme nbme, Subject delegbtionSubject)
        throws IOException;

    /**
     * Hbndles the method
     * {@link jbvbx.mbnbgement.MBebnServerConnection#getMBebnCount()}.
     *
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return the number of MBebns registered.
     *
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     */
    public Integer getMBebnCount(Subject delegbtionSubject)
        throws IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#getAttribute(ObjectNbme,
     * String)}.
     *
     * @pbrbm nbme The object nbme of the MBebn from which the
     * bttribute is to be retrieved.
     * @pbrbm bttribute A String specifying the nbme of the bttribute
     * to be retrieved.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return  The vblue of the retrieved bttribute.
     *
     * @throws AttributeNotFoundException The bttribute specified
     * is not bccessible in the MBebn.
     * @throws MBebnException Wrbps bn exception thrown by the
     * MBebn's getter.
     * @throws InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @throws ReflectionException Wrbps b
     * <code>jbvb.lbng.Exception</code> thrown when trying to invoke
     * the getter.
     * @throws RuntimeOperbtionsException Wrbps b
     * <code>jbvb.lbng.IllegblArgumentException</code>: The object
     * nbme in pbrbmeter is null or the bttribute in pbrbmeter is
     * null.
     * @throws RuntimeMBebnException Wrbps b runtime exception thrown
     * by the MBebn's getter.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     *
     * @see #setAttribute
     */
    public Object getAttribute(ObjectNbme nbme,
                               String bttribute,
                               Subject delegbtionSubject)
        throws
        MBebnException,
        AttributeNotFoundException,
        InstbnceNotFoundException,
        ReflectionException,
        IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#getAttributes(ObjectNbme,
     * String[])}.
     *
     * @pbrbm nbme The object nbme of the MBebn from which the
     * bttributes bre retrieved.
     * @pbrbm bttributes A list of the bttributes to be retrieved.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return The list of the retrieved bttributes.
     *
     * @throws InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @throws ReflectionException An exception occurred when
     * trying to invoke the getAttributes method of b Dynbmic MBebn.
     * @throws RuntimeOperbtionsException Wrbp b
     * <code>jbvb.lbng.IllegblArgumentException</code>: The object
     * nbme in pbrbmeter is null or bttributes in pbrbmeter is null.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     *
     * @see #setAttributes
     */
    public AttributeList getAttributes(ObjectNbme nbme,
                                       String[] bttributes,
                                       Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        ReflectionException,
        IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#setAttribute(ObjectNbme,
     * Attribute)}.  The <code>Attribute</code> pbrbmeter is wrbpped
     * in b <code>MbrshblledObject</code>.
     *
     * @pbrbm nbme The nbme of the MBebn within which the bttribute is
     * to be set.
     * @pbrbm bttribute The identificbtion of the bttribute to be set
     * bnd the vblue it is to be set to, encbpsulbted into b
     * <code>MbrshblledObject</code>.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @throws InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @throws AttributeNotFoundException The bttribute specified
     * is not bccessible in the MBebn.
     * @throws InvblidAttributeVblueException The vblue specified
     * for the bttribute is not vblid.
     * @throws MBebnException Wrbps bn exception thrown by the
     * MBebn's setter.
     * @throws ReflectionException Wrbps b
     * <code>jbvb.lbng.Exception</code> thrown when trying to invoke
     * the setter.
     * @throws RuntimeOperbtionsException Wrbps b
     * <code>jbvb.lbng.IllegblArgumentException</code>: The object
     * nbme in pbrbmeter is null or the bttribute in pbrbmeter is
     * null.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     *
     * @see #getAttribute
     */
    public void setAttribute(ObjectNbme nbme,
                             MbrshblledObject bttribute,
                             Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        AttributeNotFoundException,
        InvblidAttributeVblueException,
        MBebnException,
        ReflectionException,
        IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#setAttributes(ObjectNbme,
     * AttributeList)}.  The <code>AttributeList</code> pbrbmeter is
     * wrbpped in b <code>MbrshblledObject</code>.
     *
     * @pbrbm nbme The object nbme of the MBebn within which the
     * bttributes bre to be set.
     * @pbrbm bttributes A list of bttributes: The identificbtion of
     * the bttributes to be set bnd the vblues they bre to be set to,
     * encbpsulbted into b <code>MbrshblledObject</code>.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return The list of bttributes thbt were set, with their new
     * vblues.
     *
     * @throws InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @throws ReflectionException An exception occurred when
     * trying to invoke the getAttributes method of b Dynbmic MBebn.
     * @throws RuntimeOperbtionsException Wrbps b
     * <code>jbvb.lbng.IllegblArgumentException</code>: The object
     * nbme in pbrbmeter is null or bttributes in pbrbmeter is null.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     *
     * @see #getAttributes
     */
    public AttributeList setAttributes(ObjectNbme nbme,
                          MbrshblledObject bttributes,
                          Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        ReflectionException,
        IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#invoke(ObjectNbme,
     * String, Object[], String[])}.  The <code>Object[]</code>
     * pbrbmeter is wrbpped in b <code>MbrshblledObject</code>.
     *
     * @pbrbm nbme The object nbme of the MBebn on which the method is
     * to be invoked.
     * @pbrbm operbtionNbme The nbme of the operbtion to be invoked.
     * @pbrbm pbrbms An brrby contbining the pbrbmeters to be set when
     * the operbtion is invoked, encbpsulbted into b
     * <code>MbrshblledObject</code>.  The encbpsulbted brrby cbn be
     * null, equivblent to bn empty brrby.
     * @pbrbm signbture An brrby contbining the signbture of the
     * operbtion. The clbss objects will be lobded using the sbme
     * clbss lobder bs the one used for lobding the MBebn on which the
     * operbtion wbs invoked.  Cbn be null, equivblent to bn empty
     * brrby.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return The object returned by the operbtion, which represents
     * the result of invoking the operbtion on the MBebn specified.
     *
     * @throws InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @throws MBebnException Wrbps bn exception thrown by the
     * MBebn's invoked method.
     * @throws ReflectionException Wrbps b
     * <code>jbvb.lbng.Exception</code> thrown while trying to invoke
     * the method.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     * @throws RuntimeOperbtionsException Wrbps bn {@link
     * IllegblArgumentException} when <code>nbme</code> or
     * <code>operbtionNbme</code> is null.
     */
    public Object invoke(ObjectNbme nbme,
                         String operbtionNbme,
                         MbrshblledObject pbrbms,
                         String signbture[],
                         Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        MBebnException,
        ReflectionException,
        IOException;

    /**
     * Hbndles the method
     * {@link jbvbx.mbnbgement.MBebnServerConnection#getDefbultDombin()}.
     *
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return the defbult dombin.
     *
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     */
    public String getDefbultDombin(Subject delegbtionSubject)
        throws IOException;

    /**
     * Hbndles the method
     * {@link jbvbx.mbnbgement.MBebnServerConnection#getDombins()}.
     *
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return the list of dombins.
     *
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     */
    public String[] getDombins(Subject delegbtionSubject)
        throws IOException;

    /**
     * Hbndles the method
     * {@link jbvbx.mbnbgement.MBebnServerConnection#getMBebnInfo(ObjectNbme)}.
     *
     * @pbrbm nbme The nbme of the MBebn to bnblyze
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return An instbnce of <code>MBebnInfo</code> bllowing the
     * retrievbl of bll bttributes bnd operbtions of this MBebn.
     *
     * @throws IntrospectionException An exception occurred during
     * introspection.
     * @throws InstbnceNotFoundException The MBebn specified wbs
     * not found.
     * @throws ReflectionException An exception occurred when
     * trying to invoke the getMBebnInfo of b Dynbmic MBebn.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     * @throws RuntimeOperbtionsException Wrbps b
     * <code>jbvb.lbng.IllegblArgumentException</code>: The object
     * nbme in pbrbmeter is null.
     */
    public MBebnInfo getMBebnInfo(ObjectNbme nbme, Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        IntrospectionException,
        ReflectionException,
        IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#isInstbnceOf(ObjectNbme,
     * String)}.
     *
     * @pbrbm nbme The <code>ObjectNbme</code> of the MBebn.
     * @pbrbm clbssNbme The nbme of the clbss.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @return true if the MBebn specified is bn instbnce of the
     * specified clbss bccording to the rules bbove, fblse otherwise.
     *
     * @throws InstbnceNotFoundException The MBebn specified is not
     * registered in the MBebn server.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     * @throws RuntimeOperbtionsException Wrbps b
     * <code>jbvb.lbng.IllegblArgumentException</code>: The object
     * nbme in pbrbmeter is null.
     */
    public boolebn isInstbnceOf(ObjectNbme nbme,
                                String clbssNbme,
                                Subject delegbtionSubject)
        throws InstbnceNotFoundException, IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#bddNotificbtionListener(ObjectNbme,
     * ObjectNbme, NotificbtionFilter, Object)}.  The
     * <code>NotificbtionFilter</code> pbrbmeter is wrbpped in b
     * <code>MbrshblledObject</code>.  The <code>Object</code>
     * (hbndbbck) pbrbmeter is blso wrbpped in b
     * <code>MbrshblledObject</code>.
     *
     * @pbrbm nbme The nbme of the MBebn on which the listener should
     * be bdded.
     * @pbrbm listener The object nbme of the listener which will
     * hbndle the notificbtions emitted by the registered MBebn.
     * @pbrbm filter The filter object, encbpsulbted into b
     * <code>MbrshblledObject</code>. If filter encbpsulbted in the
     * <code>MbrshblledObject</code> hbs b null vblue, no filtering
     * will be performed before hbndling notificbtions.
     * @pbrbm hbndbbck The context to be sent to the listener when b
     * notificbtion is emitted, encbpsulbted into b
     * <code>MbrshblledObject</code>.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @throws InstbnceNotFoundException The MBebn nbme of the
     * notificbtion listener or of the notificbtion brobdcbster does
     * not mbtch bny of the registered MBebns.
     * @throws RuntimeOperbtionsException Wrbps bn {@link
     * IllegblArgumentException}.  The MBebn nbmed by
     * <code>listener</code> exists but does not implement the
     * {@link jbvbx.mbnbgement.NotificbtionListener} interfbce,
     * or <code>nbme</code> or <code>listener</code> is null.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     *
     * @see #removeNotificbtionListener(ObjectNbme, ObjectNbme, Subject)
     * @see #removeNotificbtionListener(ObjectNbme, ObjectNbme,
     * MbrshblledObject, MbrshblledObject, Subject)
     */
    public void bddNotificbtionListener(ObjectNbme nbme,
                        ObjectNbme listener,
                        MbrshblledObject filter,
                        MbrshblledObject hbndbbck,
                        Subject delegbtionSubject)
        throws InstbnceNotFoundException, IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#removeNotificbtionListener(ObjectNbme,
     * ObjectNbme)}.
     *
     * @pbrbm nbme The nbme of the MBebn on which the listener should
     * be removed.
     * @pbrbm listener The object nbme of the listener to be removed.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @throws InstbnceNotFoundException The MBebn nbme provided
     * does not mbtch bny of the registered MBebns.
     * @throws ListenerNotFoundException The listener is not
     * registered in the MBebn.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     * @throws RuntimeOperbtionsException Wrbps bn {@link
     * IllegblArgumentException} when <code>nbme</code> or
     * <code>listener</code> is null.
     *
     * @see #bddNotificbtionListener
     */
    public void removeNotificbtionListener(ObjectNbme nbme,
                                           ObjectNbme listener,
                                           Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        ListenerNotFoundException,
        IOException;

    /**
     * Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#removeNotificbtionListener(ObjectNbme,
     * ObjectNbme, NotificbtionFilter, Object)}.  The
     * <code>NotificbtionFilter</code> pbrbmeter is wrbpped in b
     * <code>MbrshblledObject</code>.  The <code>Object</code>
     * pbrbmeter is blso wrbpped in b <code>MbrshblledObject</code>.
     *
     * @pbrbm nbme The nbme of the MBebn on which the listener should
     * be removed.
     * @pbrbm listener A listener thbt wbs previously bdded to this
     * MBebn.
     * @pbrbm filter The filter thbt wbs specified when the listener
     * wbs bdded, encbpsulbted into b <code>MbrshblledObject</code>.
     * @pbrbm hbndbbck The hbndbbck thbt wbs specified when the
     * listener wbs bdded, encbpsulbted into b <code>MbrshblledObject</code>.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @throws InstbnceNotFoundException The MBebn nbme provided
     * does not mbtch bny of the registered MBebns.
     * @throws ListenerNotFoundException The listener is not
     * registered in the MBebn, or it is not registered with the given
     * filter bnd hbndbbck.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to perform this operbtion.
     * @throws IOException if b generbl communicbtion exception occurred.
     * @throws RuntimeOperbtionsException Wrbps bn {@link
     * IllegblArgumentException} when <code>nbme</code> or
     * <code>listener</code> is null.
     *
     * @see #bddNotificbtionListener
     */
    public void removeNotificbtionListener(ObjectNbme nbme,
                      ObjectNbme listener,
                      MbrshblledObject filter,
                      MbrshblledObject hbndbbck,
                      Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        ListenerNotFoundException,
        IOException;

    // Specibl Hbndling of Notificbtions -------------------------------------

    /**
     * <p>Hbndles the method {@link
     * jbvbx.mbnbgement.MBebnServerConnection#bddNotificbtionListener(ObjectNbme,
     * NotificbtionListener, NotificbtionFilter, Object)}.</p>
     *
     * <p>Register for notificbtions from the given MBebns thbt mbtch
     * the given filters.  The remote client cbn subsequently retrieve
     * the notificbtions using the {@link #fetchNotificbtions
     * fetchNotificbtions} method.</p>
     *
     * <p>For ebch listener, the originbl
     * <code>NotificbtionListener</code> bnd <code>hbndbbck</code> bre
     * kept on the client side; in order for the client to be bble to
     * identify them, the server generbtes bnd returns b unique
     * <code>listenerID</code>.  This <code>listenerID</code> is
     * forwbrded with the <code>Notificbtions</code> to the remote
     * client.</p>
     *
     * <p>If bny one of the given (nbme, filter) pbirs cbnnot be
     * registered, then the operbtion fbils with bn exception, bnd no
     * nbmes or filters bre registered.</p>
     *
     * @pbrbm nbmes the <code>ObjectNbmes</code> identifying the
     * MBebns emitting the Notificbtions.
     * @pbrbm filters bn brrby of mbrshblled representbtions of the
     * <code>NotificbtionFilters</code>.  Elements of this brrby cbn
     * be null.
     * @pbrbm delegbtionSubjects the <code>Subjects</code> on behblf
     * of which the listeners bre being bdded.  Elements of this brrby
     * cbn be null.  Also, the <code>delegbtionSubjects</code>
     * pbrbmeter itself cbn be null, which is equivblent to bn brrby
     * of null vblues with the sbme size bs the <code>nbmes</code> bnd
     * <code>filters</code> brrbys.
     *
     * @return bn brrby of <code>listenerIDs</code> identifying the
     * locbl listeners.  This brrby hbs the sbme number of elements bs
     * the pbrbmeters.
     *
     * @throws IllegblArgumentException if <code>nbmes</code> or
     * <code>filters</code> is null, or if <code>nbmes</code> contbins
     * b null element, or if the three brrbys do not bll hbve the sbme
     * size.
     * @throws ClbssCbstException if one of the elements of
     * <code>filters</code> unmbrshblls bs b non-null object thbt is
     * not b <code>NotificbtionFilter</code>.
     * @throws InstbnceNotFoundException if one of the
     * <code>nbmes</code> does not correspond to bny registered MBebn.
     * @throws SecurityException if, for one of the MBebns, the
     * client, or the delegbted Subject if bny, does not hbve
     * permission to bdd b listener.
     * @throws IOException if b generbl communicbtion exception occurred.
     */
    public Integer[] bddNotificbtionListeners(ObjectNbme[] nbmes,
                    MbrshblledObject[] filters,
                    Subject[] delegbtionSubjects)
        throws InstbnceNotFoundException, IOException;

    /**
     * <p>Hbndles the
     * {@link jbvbx.mbnbgement.MBebnServerConnection#removeNotificbtionListener(ObjectNbme,NotificbtionListener)
     * removeNotificbtionListener(ObjectNbme, NotificbtionListener)} bnd
     * {@link jbvbx.mbnbgement.MBebnServerConnection#removeNotificbtionListener(ObjectNbme,NotificbtionListener,NotificbtionFilter,Object)
     * removeNotificbtionListener(ObjectNbme, NotificbtionListener, NotificbtionFilter, Object)} methods.</p>
     *
     * <p>This method removes one or more
     * <code>NotificbtionListener</code>s from b given MBebn in the
     * MBebn server.</p>
     *
     * <p>The <code>NotificbtionListeners</code> bre identified by the
     * IDs which were returned by the {@link
     * #bddNotificbtionListeners(ObjectNbme[], MbrshblledObject[],
     * Subject[])} method.</p>
     *
     * @pbrbm nbme the <code>ObjectNbme</code> identifying the MBebn
     * emitting the Notificbtions.
     * @pbrbm listenerIDs the list of the IDs corresponding to the
     * listeners to remove.
     * @pbrbm delegbtionSubject The <code>Subject</code> contbining the
     * delegbtion principbls or <code>null</code> if the buthenticbtion
     * principbl is used instebd.
     *
     * @throws InstbnceNotFoundException if the given
     * <code>nbme</code> does not correspond to bny registered MBebn.
     * @throws ListenerNotFoundException if one of the listeners wbs
     * not found on the server side.  This exception cbn hbppen if the
     * MBebn discbrded b listener for some rebson other thbn b cbll to
     * <code>MBebnServer.removeNotificbtionListener</code>.
     * @throws SecurityException if the client, or the delegbted Subject
     * if bny, does not hbve permission to remove the listeners.
     * @throws IOException if b generbl communicbtion exception occurred.
     * @throws IllegblArgumentException if <code>ObjectNbme</code> or
     * <code>listenerIds</code> is null or if <code>listenerIds</code>
     * contbins b null element.
     */
    public void removeNotificbtionListeners(ObjectNbme nbme,
                                            Integer[] listenerIDs,
                                            Subject delegbtionSubject)
        throws
        InstbnceNotFoundException,
        ListenerNotFoundException,
        IOException;

    /**
     * <p>Retrieves notificbtions from the connector server.  This
     * method cbn block until there is bt lebst one notificbtion or
     * until the specified timeout is rebched.  The method cbn blso
     * return bt bny time with zero notificbtions.</p>
     *
     * <p>A notificbtion cbn be included in the result if its sequence
     * number is no less thbn <code>clientSequenceNumber</code> bnd
     * this client hbs registered bt lebst one listener for the MBebn
     * generbting the notificbtion, with b filter thbt bccepts the
     * notificbtion.  Ebch listener thbt is interested in the
     * notificbtion is identified by bn Integer ID thbt wbs returned
     * by {@link #bddNotificbtionListeners(ObjectNbme[],
     * MbrshblledObject[], Subject[])}.</p>
     *
     * @pbrbm clientSequenceNumber the first sequence number thbt the
     * client is interested in.  If negbtive, it is interpreted bs
     * mebning the sequence number thbt the next notificbtion will
     * hbve.
     *
     * @pbrbm mbxNotificbtions the mbximum number of different
     * notificbtions to return.  The <code>TbrgetedNotificbtion</code>
     * brrby in the returned <code>NotificbtionResult</code> cbn hbve
     * more elements thbn this if the sbme notificbtion bppebrs more
     * thbn once.  The behbvior is unspecified if this pbrbmeter is
     * negbtive.
     *
     * @pbrbm timeout the mbximum time in milliseconds to wbit for b
     * notificbtion to brrive.  This cbn be 0 to indicbte thbt the
     * method should not wbit if there bre no notificbtions, but
     * should return bt once.  It cbn be <code>Long.MAX_VALUE</code>
     * to indicbte thbt there is no timeout.  The behbvior is
     * unspecified if this pbrbmeter is negbtive.
     *
     * @return A <code>NotificbtionResult</code>.
     *
     * @throws IOException if b generbl communicbtion exception occurred.
     */
    public NotificbtionResult fetchNotificbtions(long clientSequenceNumber,
                                                 int mbxNotificbtions,
                                                 long timeout)
            throws IOException;
}
