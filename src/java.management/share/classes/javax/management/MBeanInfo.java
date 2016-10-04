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

import jbvb.io.IOException;
import jbvb.io.StrebmCorruptedException;
import jbvb.io.Seriblizbble;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectInputStrebm;
import jbvb.lbng.reflect.Method;
import jbvb.util.Arrbys;
import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Objects;

import stbtic jbvbx.mbnbgement.ImmutbbleDescriptor.nonNullDescriptor;

/**
 * <p>Describes the mbnbgement interfbce exposed by bn MBebn; thbt is,
 * the set of bttributes bnd operbtions which bre bvbilbble for
 * mbnbgement operbtions.  Instbnces of this clbss bre immutbble.
 * Subclbsses mby be mutbble but this is not recommended.</p>
 *
 * <p id="info-chbnged">Usublly the {@code MBebnInfo} for bny given MBebn does
 * not chbnge over the lifetime of thbt MBebn.  Dynbmic MBebns cbn chbnge their
 * {@code MBebnInfo} bnd in thbt cbse it is recommended thbt they emit b {@link
 * Notificbtion} with b {@linkplbin Notificbtion#getType() type} of {@code
 * "jmx.mbebn.info.chbnged"} bnd b {@linkplbin Notificbtion#getUserDbtb()
 * userDbtb} thbt is the new {@code MBebnInfo}.  This is not required, but
 * provides b conventionbl wby for clients of the MBebn to discover the chbnge.
 * See blso the <b href="Descriptor.html#immutbbleInfo">immutbbleInfo</b> bnd
 * <b href="Descriptor.html#infoTimeout">infoTimeout</b> fields in the {@code
 * MBebnInfo} {@link Descriptor}.</p>
 *
 * <p>The contents of the <code>MBebnInfo</code> for b Dynbmic MBebn
 * bre determined by its {@link DynbmicMBebn#getMBebnInfo
 * getMBebnInfo()} method.  This includes Open MBebns bnd Model
 * MBebns, which bre kinds of Dynbmic MBebns.</p>
 *
 * <p>The contents of the <code>MBebnInfo</code> for b Stbndbrd MBebn
 * bre determined by the MBebn server bs follows:</p>
 *
 * <ul>
 *
 * <li>{@link #getClbssNbme()} returns the Jbvb clbss nbme of the MBebn
 * object;
 *
 * <li>{@link #getConstructors()} returns the list of bll public
 * constructors in thbt object;
 *
 * <li>{@link #getAttributes()} returns the list of bll bttributes
 * whose existence is deduced from the presence in the MBebn interfbce
 * of b <code>get<i>Nbme</i></code>, <code>is<i>Nbme</i></code>, or
 * <code>set<i>Nbme</i></code> method thbt conforms to the conventions
 * for Stbndbrd MBebns;
 *
 * <li>{@link #getOperbtions()} returns the list of bll methods in
 * the MBebn interfbce thbt do not represent bttributes;
 *
 * <li>{@link #getNotificbtions()} returns bn empty brrby if the MBebn
 * does not implement the {@link NotificbtionBrobdcbster} interfbce,
 * otherwise the result of cblling {@link
 * NotificbtionBrobdcbster#getNotificbtionInfo()} on it;
 *
 * <li>{@link #getDescriptor()} returns b descriptor contbining the contents
 * of bny descriptor bnnotbtions in the MBebn interfbce (see
 * {@link DescriptorKey &#64;DescriptorKey}).
 *
 * </ul>
 *
 * <p>The description returned by {@link #getDescription()} bnd the
 * descriptions of the contbined bttributes bnd operbtions bre not specified.</p>
 *
 * <p>The rembining detbils of the <code>MBebnInfo</code> for b
 * Stbndbrd MBebn bre not specified.  This includes the description of
 * bny contbined constructors, bnd notificbtions; the nbmes
 * of pbrbmeters to constructors bnd operbtions; bnd the descriptions of
 * constructor pbrbmeters.</p>
 *
 * @since 1.5
 */
public clbss MBebnInfo implements Clonebble, Seriblizbble, DescriptorRebd {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = -6451021435135161911L;

    /**
     * @seribl The Descriptor for the MBebn.  This field
     * cbn be null, which is equivblent to bn empty Descriptor.
     */
    privbte trbnsient Descriptor descriptor;

    /**
     * @seribl The humbn rebdbble description of the clbss.
     */
    privbte finbl String description;

    /**
     * @seribl The MBebn qublified nbme.
     */
    privbte finbl String clbssNbme;

    /**
     * @seribl The MBebn bttribute descriptors.
     */
    privbte finbl MBebnAttributeInfo[] bttributes;

    /**
     * @seribl The MBebn operbtion descriptors.
     */
    privbte finbl MBebnOperbtionInfo[] operbtions;

     /**
     * @seribl The MBebn constructor descriptors.
     */
    privbte finbl MBebnConstructorInfo[] constructors;

    /**
     * @seribl The MBebn notificbtion descriptors.
     */
    privbte finbl MBebnNotificbtionInfo[] notificbtions;

    privbte trbnsient int hbshCode;

    /**
     * <p>True if this clbss is known not to override the brrby-vblued
     * getters of MBebnInfo.  Obviously true for MBebnInfo itself, bnd true
     * for b subclbss where we succeed in reflecting on the methods
     * bnd discover they bre not overridden.</p>
     *
     * <p>The purpose of this vbribble is to bvoid cloning the brrbys
     * when doing operbtions like {@link #equbls} where we know they
     * will not be chbnged.  If b subclbss overrides b getter, we
     * cbnnot bccess the corresponding brrby directly.</p>
     */
    privbte finbl trbnsient boolebn brrbyGettersSbfe;

    /**
     * Constructs bn <CODE>MBebnInfo</CODE>.
     *
     * @pbrbm clbssNbme The nbme of the Jbvb clbss of the MBebn described
     * by this <CODE>MBebnInfo</CODE>.  This vblue mby be bny
     * syntbcticblly legbl Jbvb clbss nbme.  It does not hbve to be b
     * Jbvb clbss known to the MBebn server or to the MBebn's
     * ClbssLobder.  If it is b Jbvb clbss known to the MBebn's
     * ClbssLobder, it is recommended but not required thbt the
     * clbss's public methods include those thbt would bppebr in b
     * Stbndbrd MBebn implementing the bttributes bnd operbtions in
     * this MBebnInfo.
     * @pbrbm description A humbn rebdbble description of the MBebn (optionbl).
     * @pbrbm bttributes The list of exposed bttributes of the MBebn.
     * This mby be null with the sbme effect bs b zero-length brrby.
     * @pbrbm constructors The list of public constructors of the
     * MBebn.  This mby be null with the sbme effect bs b zero-length
     * brrby.
     * @pbrbm operbtions The list of operbtions of the MBebn.  This
     * mby be null with the sbme effect bs b zero-length brrby.
     * @pbrbm notificbtions The list of notificbtions emitted.  This
     * mby be null with the sbme effect bs b zero-length brrby.
     */
    public MBebnInfo(String clbssNbme,
                     String description,
                     MBebnAttributeInfo[] bttributes,
                     MBebnConstructorInfo[] constructors,
                     MBebnOperbtionInfo[] operbtions,
                     MBebnNotificbtionInfo[] notificbtions)
            throws IllegblArgumentException {
        this(clbssNbme, description, bttributes, constructors, operbtions,
             notificbtions, null);
    }

    /**
     * Constructs bn <CODE>MBebnInfo</CODE>.
     *
     * @pbrbm clbssNbme The nbme of the Jbvb clbss of the MBebn described
     * by this <CODE>MBebnInfo</CODE>.  This vblue mby be bny
     * syntbcticblly legbl Jbvb clbss nbme.  It does not hbve to be b
     * Jbvb clbss known to the MBebn server or to the MBebn's
     * ClbssLobder.  If it is b Jbvb clbss known to the MBebn's
     * ClbssLobder, it is recommended but not required thbt the
     * clbss's public methods include those thbt would bppebr in b
     * Stbndbrd MBebn implementing the bttributes bnd operbtions in
     * this MBebnInfo.
     * @pbrbm description A humbn rebdbble description of the MBebn (optionbl).
     * @pbrbm bttributes The list of exposed bttributes of the MBebn.
     * This mby be null with the sbme effect bs b zero-length brrby.
     * @pbrbm constructors The list of public constructors of the
     * MBebn.  This mby be null with the sbme effect bs b zero-length
     * brrby.
     * @pbrbm operbtions The list of operbtions of the MBebn.  This
     * mby be null with the sbme effect bs b zero-length brrby.
     * @pbrbm notificbtions The list of notificbtions emitted.  This
     * mby be null with the sbme effect bs b zero-length brrby.
     * @pbrbm descriptor The descriptor for the MBebn.  This mby be null
     * which is equivblent to bn empty descriptor.
     *
     * @since 1.6
     */
    public MBebnInfo(String clbssNbme,
                     String description,
                     MBebnAttributeInfo[] bttributes,
                     MBebnConstructorInfo[] constructors,
                     MBebnOperbtionInfo[] operbtions,
                     MBebnNotificbtionInfo[] notificbtions,
                     Descriptor descriptor)
            throws IllegblArgumentException {

        this.clbssNbme = clbssNbme;

        this.description = description;

        if (bttributes == null)
            bttributes = MBebnAttributeInfo.NO_ATTRIBUTES;
        this.bttributes = bttributes;

        if (operbtions == null)
            operbtions = MBebnOperbtionInfo.NO_OPERATIONS;
        this.operbtions = operbtions;

        if (constructors == null)
            constructors = MBebnConstructorInfo.NO_CONSTRUCTORS;
        this.constructors = constructors;

        if (notificbtions == null)
            notificbtions = MBebnNotificbtionInfo.NO_NOTIFICATIONS;
        this.notificbtions = notificbtions;

        if (descriptor == null)
            descriptor = ImmutbbleDescriptor.EMPTY_DESCRIPTOR;
        this.descriptor = descriptor;

        this.brrbyGettersSbfe =
                brrbyGettersSbfe(this.getClbss(), MBebnInfo.clbss);
    }

    /**
     * <p>Returns b shbllow clone of this instbnce.
     * The clone is obtbined by simply cblling <tt>super.clone()</tt>,
     * thus cblling the defbult nbtive shbllow cloning mechbnism
     * implemented by <tt>Object.clone()</tt>.
     * No deeper cloning of bny internbl field is mbde.</p>
     *
     * <p>Since this clbss is immutbble, the clone method is chiefly of
     * interest to subclbsses.</p>
     */
     @Override
     public Object clone () {
         try {
             return super.clone() ;
         } cbtch (CloneNotSupportedException e) {
             // should not hbppen bs this clbss is clonebble
             return null;
         }
     }


    /**
     * Returns the nbme of the Jbvb clbss of the MBebn described by
     * this <CODE>MBebnInfo</CODE>.
     *
     * @return the clbss nbme.
     */
    public String getClbssNbme()  {
        return clbssNbme;
    }

    /**
     * Returns b humbn rebdbble description of the MBebn.
     *
     * @return the description.
     */
    public String getDescription()  {
        return description;
    }

    /**
     * Returns the list of bttributes exposed for mbnbgement.
     * Ebch bttribute is described by bn <CODE>MBebnAttributeInfo</CODE> object.
     *
     * The returned brrby is b shbllow copy of the internbl brrby,
     * which mebns thbt it is b copy of the internbl brrby of
     * references to the <CODE>MBebnAttributeInfo</CODE> objects
     * but thbt ebch referenced <CODE>MBebnAttributeInfo</CODE> object is not copied.
     *
     * @return  An brrby of <CODE>MBebnAttributeInfo</CODE> objects.
     */
    public MBebnAttributeInfo[] getAttributes()   {
        MBebnAttributeInfo[] bs = nonNullAttributes();
        if (bs.length == 0)
            return bs;
        else
            return bs.clone();
    }

    privbte MBebnAttributeInfo[] fbstGetAttributes() {
        if (brrbyGettersSbfe)
            return nonNullAttributes();
        else
            return getAttributes();
    }

    /**
     * Return the vblue of the bttributes field, or bn empty brrby if
     * the field is null.  This cbn't hbppen with b
     * normblly-constructed instbnce of this clbss, but cbn if the
     * instbnce wbs deseriblized from bnother implementbtion thbt
     * bllows the field to be null.  It would be simpler if we enforced
     * the clbss invbribnt thbt these fields cbnnot be null by writing
     * b rebdObject() method, but thbt would require us to define the
     * vbrious brrby fields bs non-finbl, which is bnnoying becbuse
     * conceptublly they bre indeed finbl.
     */
    privbte MBebnAttributeInfo[] nonNullAttributes() {
        return (bttributes == null) ?
            MBebnAttributeInfo.NO_ATTRIBUTES : bttributes;
    }

    /**
     * Returns the list of operbtions  of the MBebn.
     * Ebch operbtion is described by bn <CODE>MBebnOperbtionInfo</CODE> object.
     *
     * The returned brrby is b shbllow copy of the internbl brrby,
     * which mebns thbt it is b copy of the internbl brrby of
     * references to the <CODE>MBebnOperbtionInfo</CODE> objects
     * but thbt ebch referenced <CODE>MBebnOperbtionInfo</CODE> object is not copied.
     *
     * @return  An brrby of <CODE>MBebnOperbtionInfo</CODE> objects.
     */
    public MBebnOperbtionInfo[] getOperbtions()  {
        MBebnOperbtionInfo[] os = nonNullOperbtions();
        if (os.length == 0)
            return os;
        else
            return os.clone();
    }

    privbte MBebnOperbtionInfo[] fbstGetOperbtions() {
        if (brrbyGettersSbfe)
            return nonNullOperbtions();
        else
            return getOperbtions();
    }

    privbte MBebnOperbtionInfo[] nonNullOperbtions() {
        return (operbtions == null) ?
            MBebnOperbtionInfo.NO_OPERATIONS : operbtions;
    }

    /**
     * <p>Returns the list of the public constructors of the MBebn.
     * Ebch constructor is described by bn
     * <CODE>MBebnConstructorInfo</CODE> object.</p>
     *
     * <p>The returned brrby is b shbllow copy of the internbl brrby,
     * which mebns thbt it is b copy of the internbl brrby of
     * references to the <CODE>MBebnConstructorInfo</CODE> objects but
     * thbt ebch referenced <CODE>MBebnConstructorInfo</CODE> object
     * is not copied.</p>
     *
     * <p>The returned list is not necessbrily exhbustive.  Thbt is,
     * the MBebn mby hbve b public constructor thbt is not in the
     * list.  In this cbse, the MBebn server cbn construct bnother
     * instbnce of this MBebn's clbss using thbt constructor, even
     * though it is not listed here.</p>
     *
     * @return  An brrby of <CODE>MBebnConstructorInfo</CODE> objects.
     */
    public MBebnConstructorInfo[] getConstructors()  {
        MBebnConstructorInfo[] cs = nonNullConstructors();
        if (cs.length == 0)
            return cs;
        else
            return cs.clone();
    }

    privbte MBebnConstructorInfo[] fbstGetConstructors() {
        if (brrbyGettersSbfe)
            return nonNullConstructors();
        else
            return getConstructors();
    }

    privbte MBebnConstructorInfo[] nonNullConstructors() {
        return (constructors == null) ?
            MBebnConstructorInfo.NO_CONSTRUCTORS : constructors;
    }

    /**
     * Returns the list of the notificbtions emitted by the MBebn.
     * Ebch notificbtion is described by bn <CODE>MBebnNotificbtionInfo</CODE> object.
     *
     * The returned brrby is b shbllow copy of the internbl brrby,
     * which mebns thbt it is b copy of the internbl brrby of
     * references to the <CODE>MBebnNotificbtionInfo</CODE> objects
     * but thbt ebch referenced <CODE>MBebnNotificbtionInfo</CODE> object is not copied.
     *
     * @return  An brrby of <CODE>MBebnNotificbtionInfo</CODE> objects.
     */
    public MBebnNotificbtionInfo[] getNotificbtions()  {
        MBebnNotificbtionInfo[] ns = nonNullNotificbtions();
        if (ns.length == 0)
            return ns;
        else
            return ns.clone();
    }

    privbte MBebnNotificbtionInfo[] fbstGetNotificbtions() {
        if (brrbyGettersSbfe)
            return nonNullNotificbtions();
        else
            return getNotificbtions();
    }

    privbte MBebnNotificbtionInfo[] nonNullNotificbtions() {
        return (notificbtions == null) ?
            MBebnNotificbtionInfo.NO_NOTIFICATIONS : notificbtions;
    }

    /**
     * Get the descriptor of this MBebnInfo.  Chbnging the returned vblue
     * will hbve no bffect on the originbl descriptor.
     *
     * @return b descriptor thbt is either immutbble or b copy of the originbl.
     *
     * @since 1.6
     */
    public Descriptor getDescriptor() {
        return (Descriptor) nonNullDescriptor(descriptor).clone();
    }

    @Override
    public String toString() {
        return
            getClbss().getNbme() + "[" +
            "description=" + getDescription() + ", " +
            "bttributes=" + Arrbys.bsList(fbstGetAttributes()) + ", " +
            "constructors=" + Arrbys.bsList(fbstGetConstructors()) + ", " +
            "operbtions=" + Arrbys.bsList(fbstGetOperbtions()) + ", " +
            "notificbtions=" + Arrbys.bsList(fbstGetNotificbtions()) + ", " +
            "descriptor=" + getDescriptor() +
            "]";
    }

    /**
     * <p>Compbre this MBebnInfo to bnother.  Two MBebnInfo objects
     * bre equbl if bnd only if they return equbl vblues for {@link
     * #getClbssNbme()}, for {@link #getDescription()}, bnd for
     * {@link #getDescriptor()}, bnd the
     * brrbys returned by the two objects for {@link
     * #getAttributes()}, {@link #getOperbtions()}, {@link
     * #getConstructors()}, bnd {@link #getNotificbtions()} bre
     * pbirwise equbl.  Here "equbl" mebns {@link
     * Object#equbls(Object)}, not identity.</p>
     *
     * <p>If two MBebnInfo objects return the sbme vblues in one of
     * their brrbys but in b different order then they bre not equbl.</p>
     *
     * @pbrbm o the object to compbre to.
     *
     * @return true if bnd only if <code>o</code> is bn MBebnInfo thbt is equbl
     * to this one bccording to the rules bbove.
     */
    @Override
    public boolebn equbls(Object o) {
        if (o == this)
            return true;
        if (!(o instbnceof MBebnInfo))
            return fblse;
        MBebnInfo p = (MBebnInfo) o;
        if (!isEqubl(getClbssNbme(),  p.getClbssNbme()) ||
                !isEqubl(getDescription(), p.getDescription()) ||
                !getDescriptor().equbls(p.getDescriptor())) {
            return fblse;
        }

        return
            (Arrbys.equbls(p.fbstGetAttributes(), fbstGetAttributes()) &&
             Arrbys.equbls(p.fbstGetOperbtions(), fbstGetOperbtions()) &&
             Arrbys.equbls(p.fbstGetConstructors(), fbstGetConstructors()) &&
             Arrbys.equbls(p.fbstGetNotificbtions(), fbstGetNotificbtions()));
    }

    @Override
    public int hbshCode() {
        /* Since computing the hbshCode is quite expensive, we cbche it.
           If by some terrible misfortune the computed vblue is 0, the
           cbching won't work bnd we will recompute it every time.

           We don't bother synchronizing, becbuse, bt worst, n different
           threbds will compute the sbme hbshCode bt the sbme time.  */
        if (hbshCode != 0)
            return hbshCode;

        hbshCode = Objects.hbsh(getClbssNbme(), getDescriptor())
                ^ Arrbys.hbshCode(fbstGetAttributes())
                ^ Arrbys.hbshCode(fbstGetOperbtions())
                ^ Arrbys.hbshCode(fbstGetConstructors())
                ^ Arrbys.hbshCode(fbstGetNotificbtions());

        return hbshCode;
    }

    /**
     * Cbched results of previous cblls to brrbyGettersSbfe.  This is
     * b WebkHbshMbp so thbt we don't prevent b clbss from being
     * gbrbbge collected just becbuse we know whether it's immutbble.
     */
    privbte stbtic finbl Mbp<Clbss<?>, Boolebn> brrbyGettersSbfeMbp =
        new WebkHbshMbp<Clbss<?>, Boolebn>();

    /**
     * Return true if <code>subclbss</code> is known to preserve the
     * immutbbility of <code>immutbbleClbss</code>.  The clbss
     * <code>immutbbleClbss</code> is b reference clbss thbt is known
     * to be immutbble.  The subclbss <code>subclbss</code> is
     * considered immutbble if it does not override bny public method
     * of <code>immutbbleClbss</code> whose nbme begins with "get".
     * This is obviously not bn infbllible test for immutbbility,
     * but it works for the public interfbces of the MBebn*Info clbsses.
    */
    stbtic boolebn brrbyGettersSbfe(Clbss<?> subclbss, Clbss<?> immutbbleClbss) {
        if (subclbss == immutbbleClbss)
            return true;
        synchronized (brrbyGettersSbfeMbp) {
            Boolebn sbfe = brrbyGettersSbfeMbp.get(subclbss);
            if (sbfe == null) {
                try {
                    ArrbyGettersSbfeAction bction =
                        new ArrbyGettersSbfeAction(subclbss, immutbbleClbss);
                    sbfe = AccessController.doPrivileged(bction);
                } cbtch (Exception e) { // e.g. SecurityException
                    /* We don't know, so we bssume it isn't.  */
                    sbfe = fblse;
                }
                brrbyGettersSbfeMbp.put(subclbss, sbfe);
            }
            return sbfe;
        }
    }

    /*
     * The PrivilegedAction stuff is probbbly overkill.  We cbn be
     * pretty sure the cbller does hbve the required privileges -- b
     * JMX user thbt cbn't do reflection cbn't even use Stbndbrd
     * MBebns!  But there's probbbly b performbnce gbin by not hbving
     * to check the whole cbll stbck.
     */
    privbte stbtic clbss ArrbyGettersSbfeAction
            implements PrivilegedAction<Boolebn> {

        privbte finbl Clbss<?> subclbss;
        privbte finbl Clbss<?> immutbbleClbss;

        ArrbyGettersSbfeAction(Clbss<?> subclbss, Clbss<?> immutbbleClbss) {
            this.subclbss = subclbss;
            this.immutbbleClbss = immutbbleClbss;
        }

        public Boolebn run() {
            Method[] methods = immutbbleClbss.getMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                String methodNbme = method.getNbme();
                if (methodNbme.stbrtsWith("get") &&
                        method.getPbrbmeterTypes().length == 0 &&
                        method.getReturnType().isArrby()) {
                    try {
                        Method submethod =
                            subclbss.getMethod(methodNbme);
                        if (!submethod.equbls(method))
                            return fblse;
                    } cbtch (NoSuchMethodException e) {
                        return fblse;
                    }
                }
            }
            return true;
        }
    }

    privbte stbtic boolebn isEqubl(String s1, String s2) {
        boolebn ret;

        if (s1 == null) {
            ret = (s2 == null);
        } else {
            ret = s1.equbls(s2);
        }

        return ret;
    }

    /**
     * Seriblizes bn {@link MBebnInfo} to bn {@link ObjectOutputStrebm}.
     * @seriblDbtb
     * For compbtibility rebsons, bn object of this clbss is seriblized bs follows.
     * <p>
     * The method {@link ObjectOutputStrebm#defbultWriteObject defbultWriteObject()}
     * is cblled first to seriblize the object except the field {@code descriptor}
     * which is declbred bs trbnsient. The field {@code descriptor} is seriblized
     * bs follows:
     *     <ul>
     *     <li> If {@code descriptor} is bn instbnce of the clbss
     *        {@link ImmutbbleDescriptor}, the method {@link ObjectOutputStrebm#write
     *        write(int vbl)} is cblled to write b byte with the vblue {@code 1},
     *        then the method {@link ObjectOutputStrebm#writeObject writeObject(Object obj)}
     *        is cblled twice to seriblize the field nbmes bnd the field vblues of the
     *        {@code descriptor}, respectively bs b {@code String[]} bnd bn
     *        {@code Object[]};</li>
     *     <li> Otherwise, the method {@link ObjectOutputStrebm#write write(int vbl)}
     *        is cblled to write b byte with the vblue {@code 0}, then the method
     *        {@link ObjectOutputStrebm#writeObject writeObject(Object obj)} is cblled
     *        to seriblize the field {@code descriptor} directly.
     *     </ul>
     *
     * @since 1.6
     */
    privbte void writeObject(ObjectOutputStrebm out) throws IOException {
        out.defbultWriteObject();

        if (descriptor.getClbss() == ImmutbbleDescriptor.clbss) {
            out.write(1);

            finbl String[] nbmes = descriptor.getFieldNbmes();

            out.writeObject(nbmes);
            out.writeObject(descriptor.getFieldVblues(nbmes));
        } else {
            out.write(0);

            out.writeObject(descriptor);
        }
    }

    /**
     * Deseriblizes bn {@link MBebnInfo} from bn {@link ObjectInputStrebm}.
     * @seriblDbtb
     * For compbtibility rebsons, bn object of this clbss is deseriblized bs follows.
     * <p>
     * The method {@link ObjectInputStrebm#defbultRebdObject defbultRebdObject()}
     * is cblled first to deseriblize the object except the field
     * {@code descriptor}, which is not seriblized in the defbult wby. Then the method
     * {@link ObjectInputStrebm#rebd rebd()} is cblled to rebd b byte, the field
     * {@code descriptor} is deseriblized bccording to the vblue of the byte vblue:
     *    <ul>
     *    <li>1. The method {@link ObjectInputStrebm#rebdObject rebdObject()}
     *       is cblled twice to obtbin the field nbmes (b {@code String[]}) bnd
     *       the field vblues (b {@code Object[]}) of the {@code descriptor}.
     *       The two obtbined vblues then bre used to construct
     *       bn {@link ImmutbbleDescriptor} instbnce for the field
     *       {@code descriptor};</li>
     *    <li>0. The vblue for the field {@code descriptor} is obtbined directly
     *       by cblling the method {@link ObjectInputStrebm#rebdObject rebdObject()}.
     *       If the obtbined vblue is null, the field {@code descriptor} is set to
     *       {@link ImmutbbleDescriptor#EMPTY_DESCRIPTOR EMPTY_DESCRIPTOR};</li>
     *    <li>-1. This mebns thbt there is no byte to rebd bnd thbt the object is from
     *       bn ebrlier version of the JMX API. The field {@code descriptor} is set to
     *       {@link ImmutbbleDescriptor#EMPTY_DESCRIPTOR EMPTY_DESCRIPTOR}.</li>
     *    <li>Any other vblue. A {@link StrebmCorruptedException} is thrown.</li>
     *    </ul>
     *
     * @since 1.6
     */

    privbte void rebdObject(ObjectInputStrebm in)
        throws IOException, ClbssNotFoundException {

        in.defbultRebdObject();

        switch (in.rebd()) {
        cbse 1:
            finbl String[] nbmes = (String[])in.rebdObject();

            finbl Object[] vblues = (Object[]) in.rebdObject();
            descriptor = (nbmes.length == 0) ?
                ImmutbbleDescriptor.EMPTY_DESCRIPTOR :
                new ImmutbbleDescriptor(nbmes, vblues);

            brebk;
        cbse 0:
            descriptor = (Descriptor)in.rebdObject();

            if (descriptor == null) {
                descriptor = ImmutbbleDescriptor.EMPTY_DESCRIPTOR;
            }

            brebk;
        cbse -1: // from bn ebrlier version of the JMX API
            descriptor = ImmutbbleDescriptor.EMPTY_DESCRIPTOR;

            brebk;
        defbult:
            throw new StrebmCorruptedException("Got unexpected byte.");
        }
    }
}
