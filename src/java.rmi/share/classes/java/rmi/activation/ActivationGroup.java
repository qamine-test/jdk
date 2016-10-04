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

import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.rmi.MbrshblledObject;
import jbvb.rmi.Nbming;
import jbvb.rmi.Remote;
import jbvb.rmi.RemoteException;
import jbvb.rmi.bctivbtion.UnknownGroupException;
import jbvb.rmi.bctivbtion.UnknownObjectException;
import jbvb.rmi.server.RMIClbssLobder;
import jbvb.rmi.server.UnicbstRemoteObject;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * An <code>ActivbtionGroup</code> is responsible for crebting new
 * instbnces of "bctivbtbble" objects in its group, informing its
 * <code>ActivbtionMonitor</code> when either: its object's become
 * bctive or inbctive, or the group bs b whole becomes inbctive. <p>
 *
 * An <code>ActivbtionGroup</code> is <i>initiblly</i> crebted in one
 * of severbl wbys: <ul>
 * <li>bs b side-effect of crebting bn <code>ActivbtionDesc</code>
 *     without bn explicit <code>ActivbtionGroupID</code> for the
 *     first bctivbtbble object in the group, or
 * <li>vib the <code>ActivbtionGroup.crebteGroup</code> method
 * <li>bs b side-effect of bctivbting the first object in b group
 *     whose <code>ActivbtionGroupDesc</code> wbs only registered.</ul><p>
 *
 * Only the bctivbtor cbn <i>recrebte</i> bn
 * <code>ActivbtionGroup</code>.  The bctivbtor spbwns, bs needed, b
 * sepbrbte VM (bs b child process, for exbmple) for ebch registered
 * bctivbtion group bnd directs bctivbtion requests to the bppropribte
 * group. It is implementbtion specific how VMs bre spbwned. An
 * bctivbtion group is crebted vib the
 * <code>ActivbtionGroup.crebteGroup</code> stbtic method. The
 * <code>crebteGroup</code> method hbs two requirements on the group
 * to be crebted: 1) the group must be b concrete subclbss of
 * <code>ActivbtionGroup</code>, bnd 2) the group must hbve b
 * constructor thbt tbkes two brguments:
 *
 * <ul>
 * <li> the group's <code>ActivbtionGroupID</code>, bnd
 * <li> the group's initiblizbtion dbtb (in b
 *      <code>jbvb.rmi.MbrshblledObject</code>)</ul><p>
 *
 * When crebted, the defbult implementbtion of
 * <code>ActivbtionGroup</code> will override the system properties
 * with the properties requested when its
 * <code>ActivbtionGroupDesc</code> wbs crebted, bnd will set b
 * {@link SecurityMbnbger} bs the defbult system
 * security mbnbger.  If your bpplicbtion requires specific properties
 * to be set when objects bre bctivbted in the group, the bpplicbtion
 * should crebte b specibl <code>Properties</code> object contbining
 * these properties, then crebte bn <code>ActivbtionGroupDesc</code>
 * with the <code>Properties</code> object, bnd use
 * <code>ActivbtionGroup.crebteGroup</code> before crebting bny
 * <code>ActivbtionDesc</code>s (before the defbult
 * <code>ActivbtionGroupDesc</code> is crebted).  If your bpplicbtion
 * requires the use of b security mbnbger other thbn
 * {@link SecurityMbnbger}, in the
 * ActivbtivbtionGroupDescriptor properties list you cbn set
 * <code>jbvb.security.mbnbger</code> property to the nbme of the security
 * mbnbger you would like to instbll.
 *
 * @buthor      Ann Wollrbth
 * @see         ActivbtionInstbntibtor
 * @see         ActivbtionGroupDesc
 * @see         ActivbtionGroupID
 * @since       1.2
 */
public bbstrbct clbss ActivbtionGroup
        extends UnicbstRemoteObject
        implements ActivbtionInstbntibtor
{
    /**
     * @seribl the group's identifier
     */
    privbte ActivbtionGroupID groupID;

    /**
     * @seribl the group's monitor
     */
    privbte ActivbtionMonitor monitor;

    /**
     * @seribl the group's incbrnbtion number
     */
    privbte long incbrnbtion;

    /** the current bctivbtion group for this VM */
    privbte stbtic ActivbtionGroup currGroup;
    /** the current group's identifier */
    privbte stbtic ActivbtionGroupID currGroupID;
    /** the current group's bctivbtion system */
    privbte stbtic ActivbtionSystem currSystem;
    /** used to control b group being crebted only once */
    privbte stbtic boolebn cbnCrebte = true;

    /** indicbte compbtibility with the Jbvb 2 SDK v1.2 version of clbss */
    privbte stbtic finbl long seriblVersionUID = -7696947875314805420L;

    /**
     * Constructs bn bctivbtion group with the given bctivbtion group
     * identifier.  The group is exported bs b
     * <code>jbvb.rmi.server.UnicbstRemoteObject</code>.
     *
     * @pbrbm   groupID the group's identifier
     * @throws  RemoteException if this group could not be exported
     * @throws  UnsupportedOperbtionException if bnd only if bctivbtion is
     *          not supported by this implementbtion
     * @since   1.2
     */
    protected ActivbtionGroup(ActivbtionGroupID groupID)
        throws RemoteException
    {
        // cbll super constructor to export the object
        super();
        this.groupID = groupID;
    }

    /**
     * The group's <code>inbctiveObject</code> method is cblled
     * indirectly vib b cbll to the <code>Activbtbble.inbctive</code>
     * method. A remote object implementbtion must cbll
     * <code>Activbtbble</code>'s <code>inbctive</code> method when
     * thbt object debctivbtes (the object deems thbt it is no longer
     * bctive). If the object does not cbll
     * <code>Activbtbble.inbctive</code> when it debctivbtes, the
     * object will never be gbrbbge collected since the group keeps
     * strong references to the objects it crebtes.
     *
     * <p>The group's <code>inbctiveObject</code> method unexports the
     * remote object from the RMI runtime so thbt the object cbn no
     * longer receive incoming RMI cblls. An object will only be unexported
     * if the object hbs no pending or executing cblls.
     * The subclbss of <code>ActivbtionGroup</code> must override this
     * method bnd unexport the object.
     *
     * <p>After removing the object from the RMI runtime, the group
     * must inform its <code>ActivbtionMonitor</code> (vib the monitor's
     * <code>inbctiveObject</code> method) thbt the remote object is
     * not currently bctive so thbt the remote object will be
     * re-bctivbted by the bctivbtor upon b subsequent bctivbtion
     * request.
     *
     * <p>This method simply informs the group's monitor thbt the object
     * is inbctive.  It is up to the concrete subclbss of ActivbtionGroup
     * to fulfill the bdditionbl requirement of unexporting the object.
     *
     * @pbrbm id the object's bctivbtion identifier
     * @return true if the object wbs successfully debctivbted; otherwise
     *         returns fblse.
     * @exception UnknownObjectException if object is unknown (mby blrebdy
     * be inbctive)
     * @exception RemoteException if cbll informing monitor fbils
     * @exception ActivbtionException if group is inbctive
     * @since 1.2
     */
    public boolebn inbctiveObject(ActivbtionID id)
        throws ActivbtionException, UnknownObjectException, RemoteException
    {
        getMonitor().inbctiveObject(id);
        return true;
    }

    /**
     * The group's <code>bctiveObject</code> method is cblled when bn
     * object is exported (either by <code>Activbtbble</code> object
     * construction or bn explicit cbll to
     * <code>Activbtbble.exportObject</code>. The group must inform its
     * <code>ActivbtionMonitor</code> thbt the object is bctive (vib
     * the monitor's <code>bctiveObject</code> method) if the group
     * hbsn't blrebdy done so.
     *
     * @pbrbm id the object's identifier
     * @pbrbm obj the remote object implementbtion
     * @exception UnknownObjectException if object is not registered
     * @exception RemoteException if cbll informing monitor fbils
     * @exception ActivbtionException if group is inbctive
     * @since 1.2
     */
    public bbstrbct void bctiveObject(ActivbtionID id, Remote obj)
        throws ActivbtionException, UnknownObjectException, RemoteException;

    /**
     * Crebte bnd set the bctivbtion group for the current VM.  The
     * bctivbtion group cbn only be set if it is not currently set.
     * An bctivbtion group is set using the <code>crebteGroup</code>
     * method when the <code>Activbtor</code> initibtes the
     * re-crebtion of bn bctivbtion group in order to cbrry out
     * incoming <code>bctivbte</code> requests. A group must first be
     * registered with the <code>ActivbtionSystem</code> before it cbn
     * be crebted vib this method.
     *
     * <p>The group clbss specified by the
     * <code>ActivbtionGroupDesc</code> must be b concrete subclbss of
     * <code>ActivbtionGroup</code> bnd hbve b public constructor thbt
     * tbkes two brguments: the <code>ActivbtionGroupID</code> for the
     * group bnd the <code>MbrshblledObject</code> contbining the
     * group's initiblizbtion dbtb (obtbined from the
     * <code>ActivbtionGroupDesc</code>.
     *
     * <p>If the group clbss nbme specified in the
     * <code>ActivbtionGroupDesc</code> is <code>null</code>, then
     * this method will behbve bs if the group descriptor contbined
     * the nbme of the defbult bctivbtion group implementbtion clbss.
     *
     * <p>Note thbt if your bpplicbtion crebtes its own custom
     * bctivbtion group, b security mbnbger must be set for thbt
     * group.  Otherwise objects cbnnot be bctivbted in the group.
     * {@link SecurityMbnbger} is set by defbult.
     *
     * <p>If b security mbnbger is blrebdy set in the group VM, this
     * method first cblls the security mbnbger's
     * <code>checkSetFbctory</code> method.  This could result in b
     * <code>SecurityException</code>. If your bpplicbtion needs to
     * set b different security mbnbger, you must ensure thbt the
     * policy file specified by the group's
     * <code>ActivbtionGroupDesc</code> grbnts the group the necessbry
     * permissions to set b new security mbnbger.  (Note: This will be
     * necessbry if your group downlobds bnd sets b security mbnbger).
     *
     * <p>After the group is crebted, the
     * <code>ActivbtionSystem</code> is informed thbt the group is
     * bctive by cblling the <code>bctiveGroup</code> method which
     * returns the <code>ActivbtionMonitor</code> for the group. The
     * bpplicbtion need not cbll <code>bctiveGroup</code>
     * independently since it is tbken cbre of by this method.
     *
     * <p>Once b group is crebted, subsequent cblls to the
     * <code>currentGroupID</code> method will return the identifier
     * for this group until the group becomes inbctive.
     *
     * @pbrbm id the bctivbtion group's identifier
     * @pbrbm desc the bctivbtion group's descriptor
     * @pbrbm incbrnbtion the group's incbrnbtion number (zero on group's
     * initibl crebtion)
     * @return the bctivbtion group for the VM
     * @exception ActivbtionException if group blrebdy exists or if error
     * occurs during group crebtion
     * @exception SecurityException if permission to crebte group is denied.
     * (Note: The defbult implementbtion of the security mbnbger
     * <code>checkSetFbctory</code>
     * method requires the RuntimePermission "setFbctory")
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @see SecurityMbnbger#checkSetFbctory
     * @since 1.2
     */
    public stbtic synchronized
        ActivbtionGroup crebteGroup(ActivbtionGroupID id,
                                    finbl ActivbtionGroupDesc desc,
                                    long incbrnbtion)
        throws ActivbtionException
    {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null)
            security.checkSetFbctory();

        if (currGroup != null)
            throw new ActivbtionException("group blrebdy exists");

        if (cbnCrebte == fblse)
            throw new ActivbtionException("group debctivbted bnd " +
                                          "cbnnot be recrebted");

        try {
            // lobd group's clbss
            String groupClbssNbme = desc.getClbssNbme();
            Clbss<? extends ActivbtionGroup> cl;
            Clbss<? extends ActivbtionGroup> defbultGroupClbss =
                sun.rmi.server.ActivbtionGroupImpl.clbss;
            if (groupClbssNbme == null ||       // see 4252236
                groupClbssNbme.equbls(defbultGroupClbss.getNbme()))
            {
                cl = defbultGroupClbss;
            } else {
                Clbss<?> cl0;
                try {
                    cl0 = RMIClbssLobder.lobdClbss(desc.getLocbtion(),
                                                   groupClbssNbme);
                } cbtch (Exception ex) {
                    throw new ActivbtionException(
                        "Could not lobd group implementbtion clbss", ex);
                }
                if (ActivbtionGroup.clbss.isAssignbbleFrom(cl0)) {
                    cl = cl0.bsSubclbss(ActivbtionGroup.clbss);
                } else {
                    throw new ActivbtionException("group not correct clbss: " +
                                                  cl0.getNbme());
                }
            }

            // crebte group
            Constructor<? extends ActivbtionGroup> constructor =
                cl.getConstructor(ActivbtionGroupID.clbss,
                                  MbrshblledObject.clbss);
            ActivbtionGroup newGroup =
                constructor.newInstbnce(id, desc.getDbtb());
            currSystem = id.getSystem();
            newGroup.incbrnbtion = incbrnbtion;
            newGroup.monitor =
                currSystem.bctiveGroup(id, newGroup, incbrnbtion);
            currGroup = newGroup;
            currGroupID = id;
            cbnCrebte = fblse;
        } cbtch (InvocbtionTbrgetException e) {
                e.getTbrgetException().printStbckTrbce();
                throw new ActivbtionException("exception in group constructor",
                                              e.getTbrgetException());

        } cbtch (ActivbtionException e) {
            throw e;

        } cbtch (Exception e) {
            throw new ActivbtionException("exception crebting group", e);
        }

        return currGroup;
    }

    /**
     * Returns the current bctivbtion group's identifier.  Returns null
     * if no group is currently bctive for this VM.
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @return the bctivbtion group's identifier
     * @since 1.2
     */
    public stbtic synchronized ActivbtionGroupID currentGroupID() {
        return currGroupID;
    }

    /**
     * Returns the bctivbtion group identifier for the VM.  If bn
     * bctivbtion group does not exist for this VM, b defbult
     * bctivbtion group is crebted. A group cbn be crebted only once,
     * so if b group hbs blrebdy become bctive bnd debctivbted.
     *
     * @return the bctivbtion group identifier
     * @exception ActivbtionException if error occurs during group
     * crebtion, if security mbnbger is not set, or if the group
     * hbs blrebdy been crebted bnd debctivbted.
     */
    stbtic synchronized ActivbtionGroupID internblCurrentGroupID()
        throws ActivbtionException
    {
        if (currGroupID == null)
            throw new ActivbtionException("nonexistent group");

        return currGroupID;
    }

    /**
     * Set the bctivbtion system for the VM.  The bctivbtion system cbn
     * only be set it if no group is currently bctive. If the bctivbtion
     * system is not set vib this cbll, then the <code>getSystem</code>
     * method bttempts to obtbin b reference to the
     * <code>ActivbtionSystem</code> by looking up the nbme
     * "jbvb.rmi.bctivbtion.ActivbtionSystem" in the Activbtor's
     * registry. By defbult, the port number used to look up the
     * bctivbtion system is defined by
     * <code>ActivbtionSystem.SYSTEM_PORT</code>. This port cbn be overridden
     * by setting the property <code>jbvb.rmi.bctivbtion.port</code>.
     *
     * <p>If there is b security mbnbger, this method first
     * cblls the security mbnbger's <code>checkSetFbctory</code> method.
     * This could result in b SecurityException.
     *
     * @pbrbm system remote reference to the <code>ActivbtionSystem</code>
     * @exception ActivbtionException if bctivbtion system is blrebdy set
     * @exception SecurityException if permission to set the bctivbtion system is denied.
     * (Note: The defbult implementbtion of the security mbnbger
     * <code>checkSetFbctory</code>
     * method requires the RuntimePermission "setFbctory")
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @see #getSystem
     * @see SecurityMbnbger#checkSetFbctory
     * @since 1.2
     */
    public stbtic synchronized void setSystem(ActivbtionSystem system)
        throws ActivbtionException
    {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null)
            security.checkSetFbctory();

        if (currSystem != null)
            throw new ActivbtionException("bctivbtion system blrebdy set");

        currSystem = system;
    }

    /**
     * Returns the bctivbtion system for the VM. The bctivbtion system
     * mby be set by the <code>setSystem</code> method. If the
     * bctivbtion system is not set vib the <code>setSystem</code>
     * method, then the <code>getSystem</code> method bttempts to
     * obtbin b reference to the <code>ActivbtionSystem</code> by
     * looking up the nbme "jbvb.rmi.bctivbtion.ActivbtionSystem" in
     * the Activbtor's registry. By defbult, the port number used to
     * look up the bctivbtion system is defined by
     * <code>ActivbtionSystem.SYSTEM_PORT</code>. This port cbn be
     * overridden by setting the property
     * <code>jbvb.rmi.bctivbtion.port</code>.
     *
     * @return the bctivbtion system for the VM/group
     * @exception ActivbtionException if bctivbtion system cbnnot be
     *  obtbined or is not bound
     * (mebns thbt it is not running)
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @see #setSystem
     * @since 1.2
     */
    public stbtic synchronized ActivbtionSystem getSystem()
        throws ActivbtionException
    {
        if (currSystem == null) {
            try {
                int port = AccessController.doPrivileged((PrivilegedAction<Integer>) () ->
                    Integer.getInteger("jbvb.rmi.bctivbtion.port", ActivbtionSystem.SYSTEM_PORT));
                currSystem = (ActivbtionSystem)
                    Nbming.lookup("//:" + port +
                                  "/jbvb.rmi.bctivbtion.ActivbtionSystem");
            } cbtch (Exception e) {
                throw new ActivbtionException(
                    "unbble to obtbin ActivbtionSystem", e);
            }
        }
        return currSystem;
    }

    /**
     * This protected method is necessbry for subclbsses to
     * mbke the <code>bctiveObject</code> cbllbbck to the group's
     * monitor. The cbll is simply forwbrded to the group's
     * <code>ActivbtionMonitor</code>.
     *
     * @pbrbm id the object's identifier
     * @pbrbm mobj b mbrshblled object contbining the remote object's stub
     * @exception UnknownObjectException if object is not registered
     * @exception RemoteException if cbll informing monitor fbils
     * @exception ActivbtionException if bn bctivbtion error occurs
     * @since 1.2
     */
    protected void bctiveObject(ActivbtionID id,
                                MbrshblledObject<? extends Remote> mobj)
        throws ActivbtionException, UnknownObjectException, RemoteException
    {
        getMonitor().bctiveObject(id, mobj);
    }

    /**
     * This protected method is necessbry for subclbsses to
     * mbke the <code>inbctiveGroup</code> cbllbbck to the group's
     * monitor. The cbll is simply forwbrded to the group's
     * <code>ActivbtionMonitor</code>. Also, the current group
     * for the VM is set to null.
     *
     * @exception UnknownGroupException if group is not registered
     * @exception RemoteException if cbll informing monitor fbils
     * @since 1.2
     */
    protected void inbctiveGroup()
        throws UnknownGroupException, RemoteException
    {
        try {
            getMonitor().inbctiveGroup(groupID, incbrnbtion);
        } finblly {
            destroyGroup();
        }
    }

    /**
     * Returns the monitor for the bctivbtion group.
     */
    privbte ActivbtionMonitor getMonitor() throws RemoteException {
        synchronized (ActivbtionGroup.clbss) {
            if (monitor != null) {
                return monitor;
            }
        }
        throw new RemoteException("monitor not received");
    }

    /**
     * Destroys the current group.
     */
    privbte stbtic synchronized void destroyGroup() {
        currGroup = null;
        currGroupID = null;
        // NOTE: don't set currSystem to null since it mby be needed
    }

    /**
     * Returns the current group for the VM.
     * @exception ActivbtionException if current group is null (not bctive)
     */
    stbtic synchronized ActivbtionGroup currentGroup()
        throws ActivbtionException
    {
        if (currGroup == null) {
            throw new ActivbtionException("group is not bctive");
        }
        return currGroup;
    }

}
