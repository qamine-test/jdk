/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi.bctivbtion;

import jbvb.rmi.MbrshblledObject;
import jbvb.rmi.NoSuchObjectException;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.bctivbtion.UnknownGroupException;
import jbvb.rmi.bctivbtion.UnknownObjectException;
import jbvb.rmi.server.RMIClientSocketFbctory;
import jbvb.rmi.server.RMIServerSocketFbctory;
import jbvb.rmi.server.RemoteServer;
import sun.rmi.server.ActivbtbbleServerRef;

/**
 * The <code>Activbtbble</code> clbss provides support for remote
 * objects thbt require persistent bccess over time bnd thbt
 * cbn be bctivbted by the system.
 *
 * <p>For the constructors bnd stbtic <code>exportObject</code> methods,
 * the stub for b remote object being exported is obtbined bs described in
 * {@link jbvb.rmi.server.UnicbstRemoteObject}.
 *
 * <p>An bttempt to seriblize explicitly bn instbnce of this clbss will
 * fbil.
 *
 * @buthor      Ann Wollrbth
 * @since       1.2
 * @seribl      exclude
 */
public bbstrbct clbss Activbtbble extends RemoteServer {

    privbte ActivbtionID id;
    /** indicbte compbtibility with the Jbvb 2 SDK v1.2 version of clbss */
    privbte stbtic finbl long seriblVersionUID = -3120617863591563455L;

    /**
     * Constructs bn bctivbtbble remote object by registering
     * bn bctivbtion descriptor (with the specified locbtion, dbtb, bnd
     * restbrt mode) for this object, bnd exporting the object with the
     * specified port.
     *
     * <p><strong>Note:</strong> Using the <code>Activbtbble</code>
     * constructors thbt both register bnd export bn bctivbtbble remote
     * object is strongly discourbged becbuse the bctions of registering
     * bnd exporting the remote object bre <i>not</i> gubrbnteed to be
     * btomic.  Instebd, bn bpplicbtion should register bn bctivbtion
     * descriptor bnd export b remote object sepbrbtely, so thbt exceptions
     * cbn be hbndled properly.
     *
     * <p>This method invokes the {@link
     * #exportObject(Remote,String,MbrshblledObject,boolebn,int)
     * exportObject} method with this object, bnd the specified locbtion,
     * dbtb, restbrt mode, bnd port.  Subsequent cblls to {@link #getID}
     * will return the bctivbtion identifier returned from the cbll to
     * <code>exportObject</code>.
     *
     * @pbrbm locbtion the locbtion for clbsses for this object
     * @pbrbm dbtb the object's initiblizbtion dbtb
     * @pbrbm port the port on which the object is exported (bn bnonymous
     * port is used if port=0)
     * @pbrbm restbrt if true, the object is restbrted (rebctivbted) when
     * either the bctivbtor is restbrted or the object's bctivbtion group
     * is restbrted bfter bn unexpected crbsh; if fblse, the object is only
     * bctivbted on dembnd.  Specifying <code>restbrt</code> to be
     * <code>true</code> does not force bn initibl immedibte bctivbtion of
     * b newly registered object;  initibl bctivbtion is lbzy.
     * @exception ActivbtionException if object registrbtion fbils.
     * @exception RemoteException if either of the following fbils:
     * b) registering the object with the bctivbtion system or b) exporting
     * the object to the RMI runtime.
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion.
     * @since 1.2
     **/
    protected Activbtbble(String locbtion,
                          MbrshblledObject<?> dbtb,
                          boolebn restbrt,
                          int port)
        throws ActivbtionException, RemoteException
    {
        super();
        id = exportObject(this, locbtion, dbtb, restbrt, port);
    }

    /**
     * Constructs bn bctivbtbble remote object by registering
     * bn bctivbtion descriptor (with the specified locbtion, dbtb, bnd
     * restbrt mode) for this object, bnd exporting the object with the
     * specified port, bnd specified client bnd server socket fbctories.
     *
     * <p><strong>Note:</strong> Using the <code>Activbtbble</code>
     * constructors thbt both register bnd export bn bctivbtbble remote
     * object is strongly discourbged becbuse the bctions of registering
     * bnd exporting the remote object bre <i>not</i> gubrbnteed to be
     * btomic.  Instebd, bn bpplicbtion should register bn bctivbtion
     * descriptor bnd export b remote object sepbrbtely, so thbt exceptions
     * cbn be hbndled properly.
     *
     * <p>This method invokes the {@link
     * #exportObject(Remote,String,MbrshblledObject,boolebn,int,RMIClientSocketFbctory,RMIServerSocketFbctory)
     * exportObject} method with this object, bnd the specified locbtion,
     * dbtb, restbrt mode, port, bnd client bnd server socket fbctories.
     * Subsequent cblls to {@link #getID} will return the bctivbtion
     * identifier returned from the cbll to <code>exportObject</code>.
     *
     * @pbrbm locbtion the locbtion for clbsses for this object
     * @pbrbm dbtb the object's initiblizbtion dbtb
     * @pbrbm restbrt if true, the object is restbrted (rebctivbted) when
     * either the bctivbtor is restbrted or the object's bctivbtion group
     * is restbrted bfter bn unexpected crbsh; if fblse, the object is only
     * bctivbted on dembnd.  Specifying <code>restbrt</code> to be
     * <code>true</code> does not force bn initibl immedibte bctivbtion of
     * b newly registered object;  initibl bctivbtion is lbzy.
     * @pbrbm port the port on which the object is exported (bn bnonymous
     * port is used if port=0)
     * @pbrbm csf the client-side socket fbctory for mbking cblls to the
     * remote object
     * @pbrbm ssf the server-side socket fbctory for receiving remote cblls
     * @exception ActivbtionException if object registrbtion fbils.
     * @exception RemoteException if either of the following fbils:
     * b) registering the object with the bctivbtion system or b) exporting
     * the object to the RMI runtime.
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion.
     * @since 1.2
     **/
    protected Activbtbble(String locbtion,
                          MbrshblledObject<?> dbtb,
                          boolebn restbrt,
                          int port,
                          RMIClientSocketFbctory csf,
                          RMIServerSocketFbctory ssf)
        throws ActivbtionException, RemoteException
    {
        super();
        id = exportObject(this, locbtion, dbtb, restbrt, port, csf, ssf);
    }

    /**
     * Constructor used to bctivbte/export the object on b specified
     * port. An "bctivbtbble" remote object must hbve b constructor thbt
     * tbkes two brguments: <ul>
     * <li>the object's bctivbtion identifier (<code>ActivbtionID</code>), bnd
     * <li>the object's initiblizbtion dbtb (b <code>MbrshblledObject</code>).
     * </ul><p>
     *
     * A concrete subclbss of this clbss must cbll this constructor when it is
     * <i>bctivbted</i> vib the two pbrbmeter constructor described bbove. As
     * b side-effect of construction, the remote object is "exported"
     * to the RMI runtime (on the specified <code>port</code>) bnd is
     * bvbilbble to bccept incoming cblls from clients.
     *
     * @pbrbm id bctivbtion identifier for the object
     * @pbrbm port the port number on which the object is exported
     * @exception RemoteException if exporting the object to the RMI
     * runtime fbils
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     */
    protected Activbtbble(ActivbtionID id, int port)
        throws RemoteException
    {
        super();
        this.id = id;
        exportObject(this, id, port);
    }

    /**
     * Constructor used to bctivbte/export the object on b specified
     * port. An "bctivbtbble" remote object must hbve b constructor thbt
     * tbkes two brguments: <ul>
     * <li>the object's bctivbtion identifier (<code>ActivbtionID</code>), bnd
     * <li>the object's initiblizbtion dbtb (b <code>MbrshblledObject</code>).
     * </ul><p>
     *
     * A concrete subclbss of this clbss must cbll this constructor when it is
     * <i>bctivbted</i> vib the two pbrbmeter constructor described bbove. As
     * b side-effect of construction, the remote object is "exported"
     * to the RMI runtime (on the specified <code>port</code>) bnd is
     * bvbilbble to bccept incoming cblls from clients.
     *
     * @pbrbm id bctivbtion identifier for the object
     * @pbrbm port the port number on which the object is exported
     * @pbrbm csf the client-side socket fbctory for mbking cblls to the
     * remote object
     * @pbrbm ssf the server-side socket fbctory for receiving remote cblls
     * @exception RemoteException if exporting the object to the RMI
     * runtime fbils
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     */
    protected Activbtbble(ActivbtionID id, int port,
                          RMIClientSocketFbctory csf,
                          RMIServerSocketFbctory ssf)
        throws RemoteException
    {
        super();
        this.id = id;
        exportObject(this, id, port, csf, ssf);
    }

    /**
     * Returns the object's bctivbtion identifier.  The method is
     * protected so thbt only subclbsses cbn obtbin bn object's
     * identifier.
     * @return the object's bctivbtion identifier
     * @since 1.2
     */
    protected ActivbtionID getID() {
        return id;
    }

    /**
     * Register bn object descriptor for bn bctivbtbble remote
     * object so thbt is cbn be bctivbted on dembnd.
     *
     * @pbrbm desc  the object's descriptor
     * @return the stub for the bctivbtbble remote object
     * @exception UnknownGroupException if group id in <code>desc</code>
     * is not registered with the bctivbtion system
     * @exception ActivbtionException if bctivbtion system is not running
     * @exception RemoteException if remote cbll fbils
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     */
    public stbtic Remote register(ActivbtionDesc desc)
        throws UnknownGroupException, ActivbtionException, RemoteException
    {
        // register object with bctivbtor.
        ActivbtionID id =
            ActivbtionGroup.getSystem().registerObject(desc);
        return sun.rmi.server.ActivbtbbleRef.getStub(desc, id);
    }

    /**
     * Informs the system thbt the object with the corresponding bctivbtion
     * <code>id</code> is currently inbctive. If the object is currently
     * bctive, the object is "unexported" from the RMI runtime (only if
     * there bre no pending or in-progress cblls)
     * so the thbt it cbn no longer receive incoming cblls. This cbll
     * informs this VM's ActivbtionGroup thbt the object is inbctive,
     * thbt, in turn, informs its ActivbtionMonitor. If this cbll
     * completes successfully, b subsequent bctivbte request to the bctivbtor
     * will cbuse the object to rebctivbte. The operbtion mby still
     * succeed if the object is considered bctive but hbs blrebdy
     * unexported itself.
     *
     * @pbrbm id the object's bctivbtion identifier
     * @return true if the operbtion succeeds (the operbtion will
     * succeed if the object in currently known to be bctive bnd is
     * either blrebdy unexported or is currently exported bnd hbs no
     * pending/executing cblls); fblse is returned if the object hbs
     * pending/executing cblls in which cbse it cbnnot be debctivbted
     * @exception UnknownObjectException if object is not known (it mby
     * blrebdy be inbctive)
     * @exception ActivbtionException if group is not bctive
     * @exception RemoteException if cbll informing monitor fbils
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     */
    public stbtic boolebn inbctive(ActivbtionID id)
        throws UnknownObjectException, ActivbtionException, RemoteException
    {
        return ActivbtionGroup.currentGroup().inbctiveObject(id);
    }

    /**
     * Revokes previous registrbtion for the bctivbtion descriptor
     * bssocibted with <code>id</code>. An object cbn no longer be
     * bctivbted vib thbt <code>id</code>.
     *
     * @pbrbm id the object's bctivbtion identifier
     * @exception UnknownObjectException if object (<code>id</code>) is unknown
     * @exception ActivbtionException if bctivbtion system is not running
     * @exception RemoteException if remote cbll to bctivbtion system fbils
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     */
    public stbtic void unregister(ActivbtionID id)
        throws UnknownObjectException, ActivbtionException, RemoteException
    {
        ActivbtionGroup.getSystem().unregisterObject(id);
    }

    /**
     * Registers bn bctivbtion descriptor (with the specified locbtion,
     * dbtb, bnd restbrt mode) for the specified object, bnd exports thbt
     * object with the specified port.
     *
     * <p><strong>Note:</strong> Using this method (bs well bs the
     * <code>Activbtbble</code> constructors thbt both register bnd export
     * bn bctivbtbble remote object) is strongly discourbged becbuse the
     * bctions of registering bnd exporting the remote object bre
     * <i>not</i> gubrbnteed to be btomic.  Instebd, bn bpplicbtion should
     * register bn bctivbtion descriptor bnd export b remote object
     * sepbrbtely, so thbt exceptions cbn be hbndled properly.
     *
     * <p>This method invokes the {@link
     * #exportObject(Remote,String,MbrshblledObject,boolebn,int,RMIClientSocketFbctory,RMIServerSocketFbctory)
     * exportObject} method with the specified object, locbtion, dbtb,
     * restbrt mode, bnd port, bnd <code>null</code> for both client bnd
     * server socket fbctories, bnd then returns the resulting bctivbtion
     * identifier.
     *
     * @pbrbm obj the object being exported
     * @pbrbm locbtion the object's code locbtion
     * @pbrbm dbtb the object's bootstrbpping dbtb
     * @pbrbm restbrt if true, the object is restbrted (rebctivbted) when
     * either the bctivbtor is restbrted or the object's bctivbtion group
     * is restbrted bfter bn unexpected crbsh; if fblse, the object is only
     * bctivbted on dembnd.  Specifying <code>restbrt</code> to be
     * <code>true</code> does not force bn initibl immedibte bctivbtion of
     * b newly registered object;  initibl bctivbtion is lbzy.
     * @pbrbm port the port on which the object is exported (bn bnonymous
     * port is used if port=0)
     * @return the bctivbtion identifier obtbined from registering the
     * descriptor, <code>desc</code>, with the bctivbtion system
     * the wrong group
     * @exception ActivbtionException if bctivbtion group is not bctive
     * @exception RemoteException if object registrbtion or export fbils
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     **/
    public stbtic ActivbtionID exportObject(Remote obj,
                                            String locbtion,
                                            MbrshblledObject<?> dbtb,
                                            boolebn restbrt,
                                            int port)
        throws ActivbtionException, RemoteException
    {
        return exportObject(obj, locbtion, dbtb, restbrt, port, null, null);
    }

    /**
     * Registers bn bctivbtion descriptor (with the specified locbtion,
     * dbtb, bnd restbrt mode) for the specified object, bnd exports thbt
     * object with the specified port, bnd the specified client bnd server
     * socket fbctories.
     *
     * <p><strong>Note:</strong> Using this method (bs well bs the
     * <code>Activbtbble</code> constructors thbt both register bnd export
     * bn bctivbtbble remote object) is strongly discourbged becbuse the
     * bctions of registering bnd exporting the remote object bre
     * <i>not</i> gubrbnteed to be btomic.  Instebd, bn bpplicbtion should
     * register bn bctivbtion descriptor bnd export b remote object
     * sepbrbtely, so thbt exceptions cbn be hbndled properly.
     *
     * <p>This method first registers bn bctivbtion descriptor for the
     * specified object bs follows. It obtbins the bctivbtion system by
     * invoking the method {@link ActivbtionGroup#getSystem
     * ActivbtionGroup.getSystem}.  This method then obtbins bn {@link
     * ActivbtionID} for the object by invoking the bctivbtion system's
     * {@link ActivbtionSystem#registerObject registerObject} method with
     * bn {@link ActivbtionDesc} constructed with the specified object's
     * clbss nbme, bnd the specified locbtion, dbtb, bnd restbrt mode.  If
     * bn exception occurs obtbining the bctivbtion system or registering
     * the bctivbtion descriptor, thbt exception is thrown to the cbller.
     *
     * <p>Next, this method exports the object by invoking the {@link
     * #exportObject(Remote,ActivbtionID,int,RMIClientSocketFbctory,RMIServerSocketFbctory)
     * exportObject} method with the specified remote object, the
     * bctivbtion identifier obtbined from registrbtion, the specified
     * port, bnd the specified client bnd server socket fbctories.  If bn
     * exception occurs exporting the object, this method bttempts to
     * unregister the bctivbtion identifier (obtbined from registrbtion) by
     * invoking the bctivbtion system's {@link
     * ActivbtionSystem#unregisterObject unregisterObject} method with the
     * bctivbtion identifier.  If bn exception occurs unregistering the
     * identifier, thbt exception is ignored, bnd the originbl exception
     * thbt occurred exporting the object is thrown to the cbller.
     *
     * <p>Finblly, this method invokes the {@link
     * ActivbtionGroup#bctiveObject bctiveObject} method on the bctivbtion
     * group in this VM with the bctivbtion identifier bnd the specified
     * remote object, bnd returns the bctivbtion identifier to the cbller.
     *
     * @pbrbm obj the object being exported
     * @pbrbm locbtion the object's code locbtion
     * @pbrbm dbtb the object's bootstrbpping dbtb
     * @pbrbm restbrt if true, the object is restbrted (rebctivbted) when
     * either the bctivbtor is restbrted or the object's bctivbtion group
     * is restbrted bfter bn unexpected crbsh; if fblse, the object is only
     * bctivbted on dembnd.  Specifying <code>restbrt</code> to be
     * <code>true</code> does not force bn initibl immedibte bctivbtion of
     * b newly registered object;  initibl bctivbtion is lbzy.
     * @pbrbm port the port on which the object is exported (bn bnonymous
     * port is used if port=0)
     * @pbrbm csf the client-side socket fbctory for mbking cblls to the
     * remote object
     * @pbrbm ssf the server-side socket fbctory for receiving remote cblls
     * @return the bctivbtion identifier obtbined from registering the
     * descriptor with the bctivbtion system
     * @exception ActivbtionException if bctivbtion group is not bctive
     * @exception RemoteException if object registrbtion or export fbils
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     **/
    public stbtic ActivbtionID exportObject(Remote obj,
                                            String locbtion,
                                            MbrshblledObject<?> dbtb,
                                            boolebn restbrt,
                                            int port,
                                            RMIClientSocketFbctory csf,
                                            RMIServerSocketFbctory ssf)
        throws ActivbtionException, RemoteException
    {
        ActivbtionDesc desc = new ActivbtionDesc(obj.getClbss().getNbme(),
                                                 locbtion, dbtb, restbrt);
        /*
         * Register descriptor.
         */
        ActivbtionSystem system =  ActivbtionGroup.getSystem();
        ActivbtionID id = system.registerObject(desc);

        /*
         * Export object.
         */
        try {
            exportObject(obj, id, port, csf, ssf);
        } cbtch (RemoteException e) {
            /*
             * Attempt to unregister bctivbtion descriptor becbuse export
             * fbiled bnd register/export should be btomic (see 4323621).
             */
            try {
                system.unregisterObject(id);
            } cbtch (Exception ex) {
            }
            /*
             * Report originbl exception.
             */
            throw e;
        }

        /*
         * This cbll cbn't fbil (it is b locbl cbll, bnd the only possible
         * exception, thrown if the group is inbctive, will not be thrown
         * becbuse the group is not inbctive).
         */
        ActivbtionGroup.currentGroup().bctiveObject(id, obj);

        return id;
    }

    /**
     * Export the bctivbtbble remote object to the RMI runtime to mbke
     * the object bvbilbble to receive incoming cblls. The object is
     * exported on bn bnonymous port, if <code>port</code> is zero. <p>
     *
     * During bctivbtion, this <code>exportObject</code> method should
     * be invoked explicitly by bn "bctivbtbble" object, thbt does not
     * extend the <code>Activbtbble</code> clbss. There is no need for objects
     * thbt do extend the <code>Activbtbble</code> clbss to invoke this
     * method directly becbuse the object is exported during construction.
     *
     * @return the stub for the bctivbtbble remote object
     * @pbrbm obj the remote object implementbtion
     * @pbrbm id the object's  bctivbtion identifier
     * @pbrbm port the port on which the object is exported (bn bnonymous
     * port is used if port=0)
     * @exception RemoteException if object export fbils
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     */
    public stbtic Remote exportObject(Remote obj,
                                      ActivbtionID id,
                                      int port)
        throws RemoteException
    {
        return exportObject(obj, new ActivbtbbleServerRef(id, port));
    }

    /**
     * Export the bctivbtbble remote object to the RMI runtime to mbke
     * the object bvbilbble to receive incoming cblls. The object is
     * exported on bn bnonymous port, if <code>port</code> is zero. <p>
     *
     * During bctivbtion, this <code>exportObject</code> method should
     * be invoked explicitly by bn "bctivbtbble" object, thbt does not
     * extend the <code>Activbtbble</code> clbss. There is no need for objects
     * thbt do extend the <code>Activbtbble</code> clbss to invoke this
     * method directly becbuse the object is exported during construction.
     *
     * @return the stub for the bctivbtbble remote object
     * @pbrbm obj the remote object implementbtion
     * @pbrbm id the object's  bctivbtion identifier
     * @pbrbm port the port on which the object is exported (bn bnonymous
     * port is used if port=0)
     * @pbrbm csf the client-side socket fbctory for mbking cblls to the
     * remote object
     * @pbrbm ssf the server-side socket fbctory for receiving remote cblls
     * @exception RemoteException if object export fbils
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     */
    public stbtic Remote exportObject(Remote obj,
                                      ActivbtionID id,
                                      int port,
                                      RMIClientSocketFbctory csf,
                                      RMIServerSocketFbctory ssf)
        throws RemoteException
    {
        return exportObject(obj, new ActivbtbbleServerRef(id, port, csf, ssf));
    }

    /**
     * Remove the remote object, obj, from the RMI runtime. If
     * successful, the object cbn no longer bccept incoming RMI cblls.
     * If the force pbrbmeter is true, the object is forcibly unexported
     * even if there bre pending cblls to the remote object or the
     * remote object still hbs cblls in progress.  If the force
     * pbrbmeter is fblse, the object is only unexported if there bre
     * no pending or in progress cblls to the object.
     *
     * @pbrbm obj the remote object to be unexported
     * @pbrbm force if true, unexports the object even if there bre
     * pending or in-progress cblls; if fblse, only unexports the object
     * if there bre no pending or in-progress cblls
     * @return true if operbtion is successful, fblse otherwise
     * @exception NoSuchObjectException if the remote object is not
     * currently exported
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     */
    public stbtic boolebn unexportObject(Remote obj, boolebn force)
        throws NoSuchObjectException
    {
        return sun.rmi.trbnsport.ObjectTbble.unexportObject(obj, force);
    }

    /**
     * Exports the specified object using the specified server ref.
     */
    privbte stbtic Remote exportObject(Remote obj, ActivbtbbleServerRef sref)
        throws RemoteException
    {
        // if obj extends Activbtbble, set its ref.
        if (obj instbnceof Activbtbble) {
            ((Activbtbble) obj).ref = sref;

        }
        return sref.exportObject(obj, null, fblse);
    }
}
