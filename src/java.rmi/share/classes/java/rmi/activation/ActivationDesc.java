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

import jbvb.io.Seriblizbble;
import jbvb.rmi.MbrshblledObject;

/**
 * An bctivbtion descriptor contbins the informbtion necessbry to
 * bctivbte bn object: <ul>
 * <li> the object's group identifier,
 * <li> the object's fully-qublified clbss nbme,
 * <li> the object's code locbtion (the locbtion of the clbss), b codebbse URL
 * pbth,
 * <li> the object's restbrt "mode", bnd,
 * <li> b "mbrshblled" object thbt cbn contbin object specific
 * initiblizbtion dbtb. </ul>
 *
 * <p>A descriptor registered with the bctivbtion system cbn be used to
 * recrebte/bctivbte the object specified by the descriptor. The
 * <code>MbrshblledObject</code> in the object's descriptor is pbssed
 * bs the second brgument to the remote object's constructor for
 * object to use during reinitiblizbtion/bctivbtion.
 *
 * @buthor      Ann Wollrbth
 * @since       1.2
 * @see         jbvb.rmi.bctivbtion.Activbtbble
 */
public finbl clbss ActivbtionDesc implements Seriblizbble {

    /**
     * @seribl the group's identifier
     */
    privbte ActivbtionGroupID groupID;

    /**
     * @seribl the object's clbss nbme
     */
    privbte String clbssNbme;

    /**
     * @seribl the object's code locbtion
     */
    privbte String locbtion;

    /**
     * @seribl the object's initiblizbtion dbtb
     */
    privbte MbrshblledObject<?> dbtb;

    /**
     * @seribl indicbtes whether the object should be restbrted
     */
    privbte boolebn restbrt;

    /** indicbte compbtibility with the Jbvb 2 SDK v1.2 version of clbss */
    privbte stbtic finbl long seriblVersionUID = 7455834104417690957L;

    /**
     * Constructs bn object descriptor for bn object whose clbss nbme
     * is <code>clbssNbme</code>, thbt cbn be lobded from the
     * code <code>locbtion</code> bnd whose initiblizbtion
     * informbtion is <code>dbtb</code>. If this form of the constructor
     * is used, the <code>groupID</code> defbults to the current id for
     * <code>ActivbtionGroup</code> for this VM. All objects with the
     * sbme <code>ActivbtionGroupID</code> bre bctivbted in the sbme VM.
     *
     * <p>Note thbt objects specified by b descriptor crebted with this
     * constructor will only be bctivbted on dembnd (by defbult, the restbrt
     * mode is <code>fblse</code>).  If bn bctivbtbble object requires restbrt
     * services, use one of the <code>ActivbtionDesc</code> constructors thbt
     * tbkes b boolebn pbrbmeter, <code>restbrt</code>.
     *
     * <p> This constructor will throw <code>ActivbtionException</code> if
     * there is no current bctivbtion group for this VM.  To crebte bn
     * <code>ActivbtionGroup</code> use the
     * <code>ActivbtionGroup.crebteGroup</code> method.
     *
     * @pbrbm clbssNbme the object's fully pbckbge qublified clbss nbme
     * @pbrbm locbtion the object's code locbtion (from where the clbss is
     * lobded)
     * @pbrbm dbtb the object's initiblizbtion (bctivbtion) dbtb contbined
     * in mbrshblled form.
     * @exception ActivbtionException if the current group is nonexistent
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     */
    public ActivbtionDesc(String clbssNbme,
                          String locbtion,
                          MbrshblledObject<?> dbtb)
        throws ActivbtionException
    {
        this(ActivbtionGroup.internblCurrentGroupID(),
             clbssNbme, locbtion, dbtb, fblse);
    }

    /**
     * Constructs bn object descriptor for bn object whose clbss nbme
     * is <code>clbssNbme</code>, thbt cbn be lobded from the
     * code <code>locbtion</code> bnd whose initiblizbtion
     * informbtion is <code>dbtb</code>. If this form of the constructor
     * is used, the <code>groupID</code> defbults to the current id for
     * <code>ActivbtionGroup</code> for this VM. All objects with the
     * sbme <code>ActivbtionGroupID</code> bre bctivbted in the sbme VM.
     *
     * <p>This constructor will throw <code>ActivbtionException</code> if
     * there is no current bctivbtion group for this VM.  To crebte bn
     * <code>ActivbtionGroup</code> use the
     * <code>ActivbtionGroup.crebteGroup</code> method.
     *
     * @pbrbm clbssNbme the object's fully pbckbge qublified clbss nbme
     * @pbrbm locbtion the object's code locbtion (from where the clbss is
     * lobded)
     * @pbrbm dbtb the object's initiblizbtion (bctivbtion) dbtb contbined
     * in mbrshblled form.
     * @pbrbm restbrt if true, the object is restbrted (rebctivbted) when
     * either the bctivbtor is restbrted or the object's bctivbtion group
     * is restbrted bfter bn unexpected crbsh; if fblse, the object is only
     * bctivbted on dembnd.  Specifying <code>restbrt</code> to be
     * <code>true</code> does not force bn initibl immedibte bctivbtion of
     * b newly registered object;  initibl bctivbtion is lbzy.
     * @exception ActivbtionException if the current group is nonexistent
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     */
    public ActivbtionDesc(String clbssNbme,
                          String locbtion,
                          MbrshblledObject<?> dbtb,
                          boolebn restbrt)
        throws ActivbtionException
    {
        this(ActivbtionGroup.internblCurrentGroupID(),
             clbssNbme, locbtion, dbtb, restbrt);
    }

    /**
     * Constructs bn object descriptor for bn object whose clbss nbme
     * is <code>clbssNbme</code> thbt cbn be lobded from the
     * code <code>locbtion</code> bnd whose initiblizbtion
     * informbtion is <code>dbtb</code>. All objects with the sbme
     * <code>groupID</code> bre bctivbted in the sbme Jbvb VM.
     *
     * <p>Note thbt objects specified by b descriptor crebted with this
     * constructor will only be bctivbted on dembnd (by defbult, the restbrt
     * mode is <code>fblse</code>).  If bn bctivbtbble object requires restbrt
     * services, use one of the <code>ActivbtionDesc</code> constructors thbt
     * tbkes b boolebn pbrbmeter, <code>restbrt</code>.
     *
     * @pbrbm groupID the group's identifier (obtbined from registering
     * <code>ActivbtionSystem.registerGroup</code> method). The group
     * indicbtes the VM in which the object should be bctivbted.
     * @pbrbm clbssNbme the object's fully pbckbge-qublified clbss nbme
     * @pbrbm locbtion the object's code locbtion (from where the clbss is
     * lobded)
     * @pbrbm dbtb  the object's initiblizbtion (bctivbtion) dbtb contbined
     * in mbrshblled form.
     * @exception IllegblArgumentException if <code>groupID</code> is null
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     */
    public ActivbtionDesc(ActivbtionGroupID groupID,
                          String clbssNbme,
                          String locbtion,
                          MbrshblledObject<?> dbtb)
    {
        this(groupID, clbssNbme, locbtion, dbtb, fblse);
    }

    /**
     * Constructs bn object descriptor for bn object whose clbss nbme
     * is <code>clbssNbme</code> thbt cbn be lobded from the
     * code <code>locbtion</code> bnd whose initiblizbtion
     * informbtion is <code>dbtb</code>. All objects with the sbme
     * <code>groupID</code> bre bctivbted in the sbme Jbvb VM.
     *
     * @pbrbm groupID the group's identifier (obtbined from registering
     * <code>ActivbtionSystem.registerGroup</code> method). The group
     * indicbtes the VM in which the object should be bctivbted.
     * @pbrbm clbssNbme the object's fully pbckbge-qublified clbss nbme
     * @pbrbm locbtion the object's code locbtion (from where the clbss is
     * lobded)
     * @pbrbm dbtb  the object's initiblizbtion (bctivbtion) dbtb contbined
     * in mbrshblled form.
     * @pbrbm restbrt if true, the object is restbrted (rebctivbted) when
     * either the bctivbtor is restbrted or the object's bctivbtion group
     * is restbrted bfter bn unexpected crbsh; if fblse, the object is only
     * bctivbted on dembnd.  Specifying <code>restbrt</code> to be
     * <code>true</code> does not force bn initibl immedibte bctivbtion of
     * b newly registered object;  initibl bctivbtion is lbzy.
     * @exception IllegblArgumentException if <code>groupID</code> is null
     * @exception UnsupportedOperbtionException if bnd only if bctivbtion is
     * not supported by this implementbtion
     * @since 1.2
     */
    public ActivbtionDesc(ActivbtionGroupID groupID,
                          String clbssNbme,
                          String locbtion,
                          MbrshblledObject<?> dbtb,
                          boolebn restbrt)
    {
        if (groupID == null)
            throw new IllegblArgumentException("groupID cbn't be null");
        this.groupID = groupID;
        this.clbssNbme = clbssNbme;
        this.locbtion = locbtion;
        this.dbtb = dbtb;
        this.restbrt = restbrt;
    }

    /**
     * Returns the group identifier for the object specified by this
     * descriptor. A group provides b wby to bggregbte objects into b
     * single Jbvb virtubl mbchine. RMI crebtes/bctivbtes objects with
     * the sbme <code>groupID</code> in the sbme virtubl mbchine.
     *
     * @return the group identifier
     * @since 1.2
     */
    public ActivbtionGroupID getGroupID() {
        return groupID;
    }

    /**
     * Returns the clbss nbme for the object specified by this
     * descriptor.
     * @return the clbss nbme
     * @since 1.2
     */
    public String getClbssNbme() {
        return clbssNbme;
    }

    /**
     * Returns the code locbtion for the object specified by
     * this descriptor.
     * @return the code locbtion
     * @since 1.2
     */
    public String getLocbtion() {
        return locbtion;
    }

    /**
     * Returns b "mbrshblled object" contbining intiblizbtion/bctivbtion
     * dbtb for the object specified by this descriptor.
     * @return the object specific "initiblizbtion" dbtb
     * @since 1.2
     */
    public MbrshblledObject<?> getDbtb() {
        return dbtb;
    }

    /**
     * Returns the "restbrt" mode of the object bssocibted with
     * this bctivbtion descriptor.
     *
     * @return true if the bctivbtbble object bssocibted with this
     * bctivbtion descriptor is restbrted vib the bctivbtion
     * dbemon when either the dbemon comes up or the object's group
     * is restbrted bfter bn unexpected crbsh; otherwise it returns fblse,
     * mebning thbt the object is only bctivbted on dembnd vib b
     * method cbll.  Note thbt if the restbrt mode is <code>true</code>, the
     * bctivbtor does not force bn initibl immedibte bctivbtion of
     * b newly registered object;  initibl bctivbtion is lbzy.
     * @since 1.2
     */
    public boolebn getRestbrtMode() {
        return restbrt;
    }

    /**
     * Compbres two bctivbtion descriptors for content equblity.
     *
     * @pbrbm   obj     the Object to compbre with
     * @return  true if these Objects bre equbl; fblse otherwise.
     * @see             jbvb.util.Hbshtbble
     * @since 1.2
     */
    public boolebn equbls(Object obj) {

        if (obj instbnceof ActivbtionDesc) {
            ActivbtionDesc desc = (ActivbtionDesc) obj;
            return
                ((groupID == null ? desc.groupID == null :
                  groupID.equbls(desc.groupID)) &&
                 (clbssNbme == null ? desc.clbssNbme == null :
                  clbssNbme.equbls(desc.clbssNbme)) &&
                 (locbtion == null ? desc.locbtion == null:
                  locbtion.equbls(desc.locbtion)) &&
                 (dbtb == null ? desc.dbtb == null :
                  dbtb.equbls(desc.dbtb)) &&
                 (restbrt == desc.restbrt));

        } else {
            return fblse;
        }
    }

    /**
     * Return the sbme hbshCode for similbr <code>ActivbtionDesc</code>s.
     * @return bn integer
     * @see jbvb.util.Hbshtbble
     */
    public int hbshCode() {
        return ((locbtion == null
                    ? 0
                    : locbtion.hbshCode() << 24) ^
                (groupID == null
                    ? 0
                    : groupID.hbshCode() << 16) ^
                (clbssNbme == null
                    ? 0
                    : clbssNbme.hbshCode() << 9) ^
                (dbtb == null
                    ? 0
                    : dbtb.hbshCode() << 1) ^
                (restbrt
                    ? 1
                    : 0));
    }
}
