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

pbckbge jbvbx.mbnbgement;

import stbtic com.sun.jmx.defbults.JmxProperties.MISC_LOGGER;
import com.sun.jmx.mbebnserver.DescriptorCbche;
import com.sun.jmx.mbebnserver.Introspector;
import com.sun.jmx.mbebnserver.MBebnSupport;
import com.sun.jmx.mbebnserver.MXBebnSupport;
import com.sun.jmx.mbebnserver.StbndbrdMBebnSupport;
import com.sun.jmx.mbebnserver.Util;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;
import jbvb.util.logging.Level;
import jbvbx.mbnbgement.openmbebn.OpenMBebnAttributeInfo;
import jbvbx.mbnbgement.openmbebn.OpenMBebnAttributeInfoSupport;
import jbvbx.mbnbgement.openmbebn.OpenMBebnConstructorInfo;
import jbvbx.mbnbgement.openmbebn.OpenMBebnConstructorInfoSupport;
import jbvbx.mbnbgement.openmbebn.OpenMBebnOperbtionInfo;
import jbvbx.mbnbgement.openmbebn.OpenMBebnOperbtionInfoSupport;
import jbvbx.mbnbgement.openmbebn.OpenMBebnPbrbmeterInfo;
import jbvbx.mbnbgement.openmbebn.OpenMBebnPbrbmeterInfoSupport;

/**
 * <p>An MBebn whose mbnbgement interfbce is determined by reflection
 * on b Jbvb interfbce.</p>
 *
 * <p>This clbss brings more flexibility to the notion of Mbnbgement
 * Interfbce in the use of Stbndbrd MBebns.  Strbightforwbrd use of
 * the pbtterns for Stbndbrd MBebns described in the JMX Specificbtion
 * mebns thbt there is b fixed relbtionship between the implementbtion
 * clbss of bn MBebn bnd its mbnbgement interfbce (i.e., if the
 * implementbtion clbss is Thing, the mbnbgement interfbce must be
 * ThingMBebn).  This clbss mbkes it possible to keep the convenience
 * of specifying the mbnbgement interfbce with b Jbvb interfbce,
 * without requiring thbt there be bny nbming relbtionship between the
 * implementbtion bnd interfbce clbsses.</p>
 *
 * <p>By mbking b DynbmicMBebn out of bn MBebn, this clbss mbkes
 * it possible to select bny interfbce implemented by the MBebn bs its
 * mbnbgement interfbce, provided thbt it complies with JMX pbtterns
 * (i.e., bttributes defined by getter/setter etc...).</p>
 *
 * <p> This clbss blso provides hooks thbt mbke it possible to supply
 * custom descriptions bnd nbmes for the {@link MBebnInfo} returned by
 * the DynbmicMBebn interfbce.</p>
 *
 * <p>Using this clbss, bn MBebn cbn be crebted with bny
 * implementbtion clbss nbme <i>Impl</i> bnd with b mbnbgement
 * interfbce defined (bs for current Stbndbrd MBebns) by bny interfbce
 * <i>Intf</i>, in one of two generbl wbys:</p>
 *
 * <ul>
 *
 * <li>Using the public constructor
 *     {@link #StbndbrdMBebn(jbvb.lbng.Object, jbvb.lbng.Clbss, boolebn)
 *     StbndbrdMBebn(impl,interfbce)}:
 *     <pre>
 *     MBebnServer mbs;
 *     ...
 *     Impl impl = new Impl(...);
 *     StbndbrdMBebn mbebn = new StbndbrdMBebn(impl, Intf.clbss, fblse);
 *     mbs.registerMBebn(mbebn, objectNbme);
 *     </pre></li>
 *
 * <li>Subclbssing StbndbrdMBebn:
 *     <pre>
 *     public clbss Impl extends StbndbrdMBebn implements Intf {
 *        public Impl() {
 *          super(Intf.clbss, fblse);
 *       }
 *       // implement methods of Intf
 *     }
 *
 *     [...]
 *
 *     MBebnServer mbs;
 *     ....
 *     Impl impl = new Impl();
 *     mbs.registerMBebn(impl, objectNbme);
 *     </pre></li>
 *
 * </ul>
 *
 * <p>In either cbse, the clbss <i>Impl</i> must implement the
 * interfbce <i>Intf</i>.</p>
 *
 * <p>Stbndbrd MBebns bbsed on the nbming relbtionship between
 * implementbtion bnd interfbce clbsses bre of course still
 * bvbilbble.</p>
 *
 * <p>This clbss mby blso be used to construct MXBebns.  The usbge
 * is exbctly the sbme bs for Stbndbrd MBebns except thbt in the
 * exbmples bbove, the {@code fblse} pbrbmeter to the constructor or
 * {@code super(...)} invocbtion is instebd {@code true}.</p>
 *
 * @since 1.5
 */
public clbss StbndbrdMBebn implements DynbmicMBebn, MBebnRegistrbtion {

    privbte finbl stbtic DescriptorCbche descriptors =
        DescriptorCbche.getInstbnce(JMX.proof);

    /**
     * The DynbmicMBebn thbt wrbps the MXBebn or Stbndbrd MBebn implementbtion.
     **/
    privbte volbtile MBebnSupport<?> mbebn;

    /**
     * The cbched MBebnInfo.
     **/
    privbte volbtile MBebnInfo cbchedMBebnInfo;

    /**
     * Mbke b DynbmicMBebn out of <vbr>implementbtion</vbr>, using the
     * specified <vbr>mbebnInterfbce</vbr> clbss.
     * @pbrbm implementbtion The implementbtion of this MBebn.
     *        If <code>null</code>, bnd null implementbtion is bllowed,
     *        then the implementbtion is bssumed to be <vbr>this</vbr>.
     * @pbrbm mbebnInterfbce The Mbnbgement Interfbce exported by this
     *        MBebn's implementbtion. If <code>null</code>, then this
     *        object will use stbndbrd JMX design pbttern to determine
     *        the mbnbgement interfbce bssocibted with the given
     *        implementbtion.
     * @pbrbm nullImplementbtionAllowed <code>true</code> if b null
     *        implementbtion is bllowed. If null implementbtion is bllowed,
     *        bnd b null implementbtion is pbssed, then the implementbtion
     *        is bssumed to be <vbr>this</vbr>.
     * @exception IllegblArgumentException if the given
     *    <vbr>implementbtion</vbr> is null, bnd null is not bllowed.
     **/
    privbte <T> void construct(T implementbtion, Clbss<T> mbebnInterfbce,
                               boolebn nullImplementbtionAllowed,
                               boolebn isMXBebn)
                               throws NotComplibntMBebnException {
        if (implementbtion == null) {
            // Hbve to use (T)this rbther thbn mbebnInterfbce.cbst(this)
            // becbuse mbebnInterfbce might be null.
            if (nullImplementbtionAllowed)
                implementbtion = Util.<T>cbst(this);
            else throw new IllegblArgumentException("implementbtion is null");
        }
        if (isMXBebn) {
            if (mbebnInterfbce == null) {
                mbebnInterfbce = Util.cbst(Introspector.getMXBebnInterfbce(
                        implementbtion.getClbss()));
            }
            this.mbebn = new MXBebnSupport(implementbtion, mbebnInterfbce);
        } else {
            if (mbebnInterfbce == null) {
                mbebnInterfbce = Util.cbst(Introspector.getStbndbrdMBebnInterfbce(
                        implementbtion.getClbss()));
            }
            this.mbebn =
                    new StbndbrdMBebnSupport(implementbtion, mbebnInterfbce);
        }
    }

    /**
     * <p>Mbke b DynbmicMBebn out of the object
     * <vbr>implementbtion</vbr>, using the specified
     * <vbr>mbebnInterfbce</vbr> clbss.</p>
     *
     * @pbrbm implementbtion The implementbtion of this MBebn.
     * @pbrbm mbebnInterfbce The Mbnbgement Interfbce exported by this
     *        MBebn's implementbtion. If <code>null</code>, then this
     *        object will use stbndbrd JMX design pbttern to determine
     *        the mbnbgement interfbce bssocibted with the given
     *        implementbtion.
     * @pbrbm <T> Allows the compiler to check
     * thbt {@code implementbtion} does indeed implement the clbss
     * described by {@code mbebnInterfbce}.  The compiler cbn only
     * check this if {@code mbebnInterfbce} is b clbss literbl such
     * bs {@code MyMBebn.clbss}.
     *
     * @exception IllegblArgumentException if the given
     *    <vbr>implementbtion</vbr> is null.
     * @exception NotComplibntMBebnException if the <vbr>mbebnInterfbce</vbr>
     *    does not follow JMX design pbtterns for Mbnbgement Interfbces, or
     *    if the given <vbr>implementbtion</vbr> does not implement the
     *    specified interfbce.
     **/
    public <T> StbndbrdMBebn(T implementbtion, Clbss<T> mbebnInterfbce)
        throws NotComplibntMBebnException {
        construct(implementbtion, mbebnInterfbce, fblse, fblse);
    }

    /**
     * <p>Mbke b DynbmicMBebn out of <vbr>this</vbr>, using the specified
     * <vbr>mbebnInterfbce</vbr> clbss.</p>
     *
     * <p>Cblls {@link #StbndbrdMBebn(jbvb.lbng.Object, jbvb.lbng.Clbss)
     *       this(this,mbebnInterfbce)}.
     * This constructor is reserved to subclbsses.</p>
     *
     * @pbrbm mbebnInterfbce The Mbnbgement Interfbce exported by this
     *        MBebn.
     *
     * @exception NotComplibntMBebnException if the <vbr>mbebnInterfbce</vbr>
     *    does not follow JMX design pbtterns for Mbnbgement Interfbces, or
     *    if <vbr>this</vbr> does not implement the specified interfbce.
     **/
    protected StbndbrdMBebn(Clbss<?> mbebnInterfbce)
        throws NotComplibntMBebnException {
        construct(null, mbebnInterfbce, true, fblse);
    }

    /**
     * <p>Mbke b DynbmicMBebn out of the object
     * <vbr>implementbtion</vbr>, using the specified
     * <vbr>mbebnInterfbce</vbr> clbss, bnd choosing whether the
     * resultbnt MBebn is bn MXBebn.  This constructor cbn be used
     * to mbke either Stbndbrd MBebns or MXBebns.  Unlike the
     * constructor {@link #StbndbrdMBebn(Object, Clbss)}, it
     * does not throw NotComplibntMBebnException.</p>
     *
     * @pbrbm implementbtion The implementbtion of this MBebn.
     * @pbrbm mbebnInterfbce The Mbnbgement Interfbce exported by this
     *        MBebn's implementbtion. If <code>null</code>, then this
     *        object will use stbndbrd JMX design pbttern to determine
     *        the mbnbgement interfbce bssocibted with the given
     *        implementbtion.
     * @pbrbm isMXBebn If true, the {@code mbebnInterfbce} pbrbmeter
     * nbmes bn MXBebn interfbce bnd the resultbnt MBebn is bn MXBebn.
     * @pbrbm <T> Allows the compiler to check
     * thbt {@code implementbtion} does indeed implement the clbss
     * described by {@code mbebnInterfbce}.  The compiler cbn only
     * check this if {@code mbebnInterfbce} is b clbss literbl such
     * bs {@code MyMBebn.clbss}.
     *
     * @exception IllegblArgumentException if the given
     *    <vbr>implementbtion</vbr> is null, or if the <vbr>mbebnInterfbce</vbr>
     *    does not follow JMX design pbtterns for Mbnbgement Interfbces, or
     *    if the given <vbr>implementbtion</vbr> does not implement the
     *    specified interfbce.
     *
     * @since 1.6
     **/
    public <T> StbndbrdMBebn(T implementbtion, Clbss<T> mbebnInterfbce,
                             boolebn isMXBebn) {
        try {
            construct(implementbtion, mbebnInterfbce, fblse, isMXBebn);
        } cbtch (NotComplibntMBebnException e) {
            throw new IllegblArgumentException(e);
        }
    }

    /**
     * <p>Mbke b DynbmicMBebn out of <vbr>this</vbr>, using the specified
     * <vbr>mbebnInterfbce</vbr> clbss, bnd choosing whether the resulting
     * MBebn is bn MXBebn.  This constructor cbn be used
     * to mbke either Stbndbrd MBebns or MXBebns.  Unlike the
     * constructor {@link #StbndbrdMBebn(Object, Clbss)}, it
     * does not throw NotComplibntMBebnException.</p>
     *
     * <p>Cblls {@link #StbndbrdMBebn(jbvb.lbng.Object, jbvb.lbng.Clbss, boolebn)
     *       this(this, mbebnInterfbce, isMXBebn)}.
     * This constructor is reserved to subclbsses.</p>
     *
     * @pbrbm mbebnInterfbce The Mbnbgement Interfbce exported by this
     *        MBebn.
     * @pbrbm isMXBebn If true, the {@code mbebnInterfbce} pbrbmeter
     * nbmes bn MXBebn interfbce bnd the resultbnt MBebn is bn MXBebn.
     *
     * @exception IllegblArgumentException if the <vbr>mbebnInterfbce</vbr>
     *    does not follow JMX design pbtterns for Mbnbgement Interfbces, or
     *    if <vbr>this</vbr> does not implement the specified interfbce.
     *
     * @since 1.6
     **/
    protected StbndbrdMBebn(Clbss<?> mbebnInterfbce, boolebn isMXBebn) {
        try {
            construct(null, mbebnInterfbce, true, isMXBebn);
        } cbtch (NotComplibntMBebnException e) {
            throw new IllegblArgumentException(e);
        }
    }

    /**
     * <p>Replbce the implementbtion object wrbpped in this object.</p>
     *
     * @pbrbm implementbtion The new implementbtion of this Stbndbrd MBebn
     * (or MXBebn). The <code>implementbtion</code> object must implement
     * the Stbndbrd MBebn (or MXBebn) interfbce thbt wbs supplied when this
     * <code>StbndbrdMBebn</code> wbs constructed.
     *
     * @exception IllegblArgumentException if the given
     * <vbr>implementbtion</vbr> is null.
     *
     * @exception NotComplibntMBebnException if the given
     * <vbr>implementbtion</vbr> does not implement the
     * Stbndbrd MBebn (or MXBebn) interfbce thbt wbs
     * supplied bt construction.
     *
     * @see #getImplementbtion
     **/
    public void setImplementbtion(Object implementbtion)
        throws NotComplibntMBebnException {

        if (implementbtion == null)
            throw new IllegblArgumentException("implementbtion is null");

        if (isMXBebn()) {
            this.mbebn = new MXBebnSupport(implementbtion,
                    Util.<Clbss<Object>>cbst(getMBebnInterfbce()));
        } else {
            this.mbebn = new StbndbrdMBebnSupport(implementbtion,
                    Util.<Clbss<Object>>cbst(getMBebnInterfbce()));
        }
    }

    /**
     * Get the implementbtion of this Stbndbrd MBebn (or MXBebn).
     * @return The implementbtion of this Stbndbrd MBebn (or MXBebn).
     *
     * @see #setImplementbtion
     **/
    public Object getImplementbtion() {
        return mbebn.getResource();
    }

    /**
     * Get the Mbnbgement Interfbce of this Stbndbrd MBebn (or MXBebn).
     * @return The mbnbgement interfbce of this Stbndbrd MBebn (or MXBebn).
     **/
    public finbl Clbss<?> getMBebnInterfbce() {
        return mbebn.getMBebnInterfbce();
    }

    /**
     * Get the clbss of the implementbtion of this Stbndbrd MBebn (or MXBebn).
     * @return The clbss of the implementbtion of this Stbndbrd MBebn (or MXBebn).
     **/
    public Clbss<?> getImplementbtionClbss() {
        return mbebn.getResource().getClbss();
    }

    // ------------------------------------------------------------------
    // From the DynbmicMBebn interfbce.
    // ------------------------------------------------------------------
    public Object getAttribute(String bttribute)
        throws AttributeNotFoundException,
               MBebnException,
               ReflectionException {
        return mbebn.getAttribute(bttribute);
    }

    // ------------------------------------------------------------------
    // From the DynbmicMBebn interfbce.
    // ------------------------------------------------------------------
    public void setAttribute(Attribute bttribute)
        throws AttributeNotFoundException,
               InvblidAttributeVblueException,
               MBebnException,
               ReflectionException {
        mbebn.setAttribute(bttribute);
    }

    // ------------------------------------------------------------------
    // From the DynbmicMBebn interfbce.
    // ------------------------------------------------------------------
    public AttributeList getAttributes(String[] bttributes) {
        return mbebn.getAttributes(bttributes);
    }

    // ------------------------------------------------------------------
    // From the DynbmicMBebn interfbce.
    // ------------------------------------------------------------------
    public AttributeList setAttributes(AttributeList bttributes) {
        return mbebn.setAttributes(bttributes);
    }

    // ------------------------------------------------------------------
    // From the DynbmicMBebn interfbce.
    // ------------------------------------------------------------------
    public Object invoke(String bctionNbme, Object pbrbms[], String signbture[])
            throws MBebnException, ReflectionException {
        return mbebn.invoke(bctionNbme, pbrbms, signbture);
    }

    /**
     * Get the {@link MBebnInfo} for this MBebn.
     * <p>
     * This method implements
     * {@link jbvbx.mbnbgement.DynbmicMBebn#getMBebnInfo()
     *   DynbmicMBebn.getMBebnInfo()}.
     * <p>
     * This method first cblls {@link #getCbchedMBebnInfo()} in order to
     * retrieve the cbched MBebnInfo for this MBebn, if bny. If the
     * MBebnInfo returned by {@link #getCbchedMBebnInfo()} is not null,
     * then it is returned.<br>
     * Otherwise, this method builds b defbult MBebnInfo for this MBebn,
     * using the Mbnbgement Interfbce specified for this MBebn.
     * <p>
     * While building the MBebnInfo, this method cblls the customizbtion
     * hooks thbt mbke it possible for subclbsses to supply their custom
     * descriptions, pbrbmeter nbmes, etc...<br>
     * Finblly, it cblls {@link #cbcheMBebnInfo(jbvbx.mbnbgement.MBebnInfo)
     * cbcheMBebnInfo()} in order to cbche the new MBebnInfo.
     * @return The cbched MBebnInfo for thbt MBebn, if not null, or b
     *         newly built MBebnInfo if none wbs cbched.
     **/
    public MBebnInfo getMBebnInfo() {
        try {
            finbl MBebnInfo cbched = getCbchedMBebnInfo();
            if (cbched != null) return cbched;
        } cbtch (RuntimeException x) {
            if (MISC_LOGGER.isLoggbble(Level.FINEST)) {
                MISC_LOGGER.logp(Level.FINEST,
                        MBebnServerFbctory.clbss.getNbme(), "getMBebnInfo",
                        "Fbiled to get cbched MBebnInfo", x);
            }
        }

        if (MISC_LOGGER.isLoggbble(Level.FINER)) {
            MISC_LOGGER.logp(Level.FINER,
                    MBebnServerFbctory.clbss.getNbme(), "getMBebnInfo",
                    "Building MBebnInfo for " +
                    getImplementbtionClbss().getNbme());
        }

        MBebnSupport<?> msupport = mbebn;
        finbl MBebnInfo bi = msupport.getMBebnInfo();
        finbl Object impl = msupport.getResource();

        finbl boolebn immutbbleInfo = immutbbleInfo(this.getClbss());

        finbl String                  cnbme = getClbssNbme(bi);
        finbl String                  text  = getDescription(bi);
        finbl MBebnConstructorInfo[]  ctors = getConstructors(bi,impl);
        finbl MBebnAttributeInfo[]    bttrs = getAttributes(bi);
        finbl MBebnOperbtionInfo[]    ops   = getOperbtions(bi);
        finbl MBebnNotificbtionInfo[] ntfs  = getNotificbtions(bi);
        finbl Descriptor              desc  = getDescriptor(bi, immutbbleInfo);

        finbl MBebnInfo nmbi = new MBebnInfo(
                cnbme, text, bttrs, ctors, ops, ntfs, desc);
        try {
            cbcheMBebnInfo(nmbi);
        } cbtch (RuntimeException x) {
            if (MISC_LOGGER.isLoggbble(Level.FINEST)) {
                MISC_LOGGER.logp(Level.FINEST,
                        MBebnServerFbctory.clbss.getNbme(), "getMBebnInfo",
                        "Fbiled to cbche MBebnInfo", x);
            }
        }

        return nmbi;
    }

    /**
     * Customizbtion hook:
     * Get the clbssNbme thbt will be used in the MBebnInfo returned by
     * this MBebn.
     * <br>
     * Subclbsses mby redefine this method in order to supply their
     * custom clbss nbme.  The defbult implementbtion returns
     * {@link MBebnInfo#getClbssNbme() info.getClbssNbme()}.
     * @pbrbm info The defbult MBebnInfo derived by reflection.
     * @return the clbss nbme for the new MBebnInfo.
     **/
    protected String getClbssNbme(MBebnInfo info) {
        if (info == null) return getImplementbtionClbss().getNbme();
        return info.getClbssNbme();
    }

    /**
     * Customizbtion hook:
     * Get the description thbt will be used in the MBebnInfo returned by
     * this MBebn.
     * <br>
     * Subclbsses mby redefine this method in order to supply their
     * custom MBebn description.  The defbult implementbtion returns
     * {@link MBebnInfo#getDescription() info.getDescription()}.
     * @pbrbm info The defbult MBebnInfo derived by reflection.
     * @return the description for the new MBebnInfo.
     **/
    protected String getDescription(MBebnInfo info) {
        if (info == null) return null;
        return info.getDescription();
    }

    /**
     * <p>Customizbtion hook:
     * Get the description thbt will be used in the MBebnFebtureInfo
     * returned by this MBebn.</p>
     *
     * <p>Subclbsses mby redefine this method in order to supply
     * their custom description.  The defbult implementbtion returns
     * {@link MBebnFebtureInfo#getDescription()
     * info.getDescription()}.</p>
     *
     * <p>This method is cblled by
     *      {@link #getDescription(MBebnAttributeInfo)},
     *      {@link #getDescription(MBebnOperbtionInfo)},
     *      {@link #getDescription(MBebnConstructorInfo)}.</p>
     *
     * @pbrbm info The defbult MBebnFebtureInfo derived by reflection.
     * @return the description for the given MBebnFebtureInfo.
     **/
    protected String getDescription(MBebnFebtureInfo info) {
        if (info == null) return null;
        return info.getDescription();
    }

    /**
     * Customizbtion hook:
     * Get the description thbt will be used in the MBebnAttributeInfo
     * returned by this MBebn.
     *
     * <p>Subclbsses mby redefine this method in order to supply their
     * custom description.  The defbult implementbtion returns {@link
     * #getDescription(MBebnFebtureInfo)
     * getDescription((MBebnFebtureInfo) info)}.
     * @pbrbm info The defbult MBebnAttributeInfo derived by reflection.
     * @return the description for the given MBebnAttributeInfo.
     **/
    protected String getDescription(MBebnAttributeInfo info) {
        return getDescription((MBebnFebtureInfo)info);
    }

    /**
     * Customizbtion hook:
     * Get the description thbt will be used in the MBebnConstructorInfo
     * returned by this MBebn.
     * <br>
     * Subclbsses mby redefine this method in order to supply their
     * custom description.
     * The defbult implementbtion returns {@link
     * #getDescription(MBebnFebtureInfo)
     * getDescription((MBebnFebtureInfo) info)}.
     * @pbrbm info The defbult MBebnConstructorInfo derived by reflection.
     * @return the description for the given MBebnConstructorInfo.
     **/
    protected String getDescription(MBebnConstructorInfo info) {
        return getDescription((MBebnFebtureInfo)info);
    }

    /**
     * Customizbtion hook:
     * Get the description thbt will be used for the  <vbr>sequence</vbr>
     * MBebnPbrbmeterInfo of the MBebnConstructorInfo returned by this MBebn.
     * <br>
     * Subclbsses mby redefine this method in order to supply their
     * custom description.  The defbult implementbtion returns
     * {@link MBebnPbrbmeterInfo#getDescription() pbrbm.getDescription()}.
     *
     * @pbrbm ctor  The defbult MBebnConstructorInfo derived by reflection.
     * @pbrbm pbrbm The defbult MBebnPbrbmeterInfo derived by reflection.
     * @pbrbm sequence The sequence number of the pbrbmeter considered
     *        ("0" for the first pbrbmeter, "1" for the second pbrbmeter,
     *        etc...).
     * @return the description for the given MBebnPbrbmeterInfo.
     **/
    protected String getDescription(MBebnConstructorInfo ctor,
                                    MBebnPbrbmeterInfo   pbrbm,
                                    int sequence) {
        if (pbrbm == null) return null;
        return pbrbm.getDescription();
    }

    /**
     * Customizbtion hook:
     * Get the nbme thbt will be used for the <vbr>sequence</vbr>
     * MBebnPbrbmeterInfo of the MBebnConstructorInfo returned by this MBebn.
     * <br>
     * Subclbsses mby redefine this method in order to supply their
     * custom pbrbmeter nbme.  The defbult implementbtion returns
     * {@link MBebnPbrbmeterInfo#getNbme() pbrbm.getNbme()}.
     *
     * @pbrbm ctor  The defbult MBebnConstructorInfo derived by reflection.
     * @pbrbm pbrbm The defbult MBebnPbrbmeterInfo derived by reflection.
     * @pbrbm sequence The sequence number of the pbrbmeter considered
     *        ("0" for the first pbrbmeter, "1" for the second pbrbmeter,
     *        etc...).
     * @return the nbme for the given MBebnPbrbmeterInfo.
     **/
    protected String getPbrbmeterNbme(MBebnConstructorInfo ctor,
                                      MBebnPbrbmeterInfo pbrbm,
                                      int sequence) {
        if (pbrbm == null) return null;
        return pbrbm.getNbme();
    }

    /**
     * Customizbtion hook:
     * Get the description thbt will be used in the MBebnOperbtionInfo
     * returned by this MBebn.
     * <br>
     * Subclbsses mby redefine this method in order to supply their
     * custom description.  The defbult implementbtion returns
     * {@link #getDescription(MBebnFebtureInfo)
     * getDescription((MBebnFebtureInfo) info)}.
     * @pbrbm info The defbult MBebnOperbtionInfo derived by reflection.
     * @return the description for the given MBebnOperbtionInfo.
     **/
    protected String getDescription(MBebnOperbtionInfo info) {
        return getDescription((MBebnFebtureInfo)info);
    }

    /**
     * Customizbtion hook:
     * Get the <vbr>impbct</vbr> flbg of the operbtion thbt will be used in
     * the MBebnOperbtionInfo returned by this MBebn.
     * <br>
     * Subclbsses mby redefine this method in order to supply their
     * custom impbct flbg.  The defbult implementbtion returns
     * {@link MBebnOperbtionInfo#getImpbct() info.getImpbct()}.
     * @pbrbm info The defbult MBebnOperbtionInfo derived by reflection.
     * @return the impbct flbg for the given MBebnOperbtionInfo.
     **/
    protected int getImpbct(MBebnOperbtionInfo info) {
        if (info == null) return MBebnOperbtionInfo.UNKNOWN;
        return info.getImpbct();
    }

    /**
     * Customizbtion hook:
     * Get the nbme thbt will be used for the <vbr>sequence</vbr>
     * MBebnPbrbmeterInfo of the MBebnOperbtionInfo returned by this MBebn.
     * <br>
     * Subclbsses mby redefine this method in order to supply their
     * custom pbrbmeter nbme.  The defbult implementbtion returns
     * {@link MBebnPbrbmeterInfo#getNbme() pbrbm.getNbme()}.
     *
     * @pbrbm op    The defbult MBebnOperbtionInfo derived by reflection.
     * @pbrbm pbrbm The defbult MBebnPbrbmeterInfo derived by reflection.
     * @pbrbm sequence The sequence number of the pbrbmeter considered
     *        ("0" for the first pbrbmeter, "1" for the second pbrbmeter,
     *        etc...).
     * @return the nbme to use for the given MBebnPbrbmeterInfo.
     **/
    protected String getPbrbmeterNbme(MBebnOperbtionInfo op,
                                      MBebnPbrbmeterInfo pbrbm,
                                      int sequence) {
        if (pbrbm == null) return null;
        return pbrbm.getNbme();
    }

    /**
     * Customizbtion hook:
     * Get the description thbt will be used for the  <vbr>sequence</vbr>
     * MBebnPbrbmeterInfo of the MBebnOperbtionInfo returned by this MBebn.
     * <br>
     * Subclbsses mby redefine this method in order to supply their
     * custom description.  The defbult implementbtion returns
     * {@link MBebnPbrbmeterInfo#getDescription() pbrbm.getDescription()}.
     *
     * @pbrbm op    The defbult MBebnOperbtionInfo derived by reflection.
     * @pbrbm pbrbm The defbult MBebnPbrbmeterInfo derived by reflection.
     * @pbrbm sequence The sequence number of the pbrbmeter considered
     *        ("0" for the first pbrbmeter, "1" for the second pbrbmeter,
     *        etc...).
     * @return the description for the given MBebnPbrbmeterInfo.
     **/
    protected String getDescription(MBebnOperbtionInfo op,
                                    MBebnPbrbmeterInfo pbrbm,
                                    int sequence) {
        if (pbrbm == null) return null;
        return pbrbm.getDescription();
    }

    /**
     * Customizbtion hook:
     * Get the MBebnConstructorInfo[] thbt will be used in the MBebnInfo
     * returned by this MBebn.
     * <br>
     * By defbult, this method returns <code>null</code> if the wrbpped
     * implementbtion is not <vbr>this</vbr>. Indeed, if the wrbpped
     * implementbtion is not this object itself, it will not be possible
     * to recrebte b wrbpped implementbtion by cblling the implementbtion
     * constructors through <code>MBebnServer.crebteMBebn(...)</code>.<br>
     * Otherwise, if the wrbpped implementbtion is <vbr>this</vbr>,
     * <vbr>ctors</vbr> is returned.
     * <br>
     * Subclbsses mby redefine this method in order to modify this
     * behbvior, if needed.
     * @pbrbm ctors The defbult MBebnConstructorInfo[] derived by reflection.
     * @pbrbm impl  The wrbpped implementbtion. If <code>null</code> is
     *        pbssed, the wrbpped implementbtion is ignored bnd
     *        <vbr>ctors</vbr> is returned.
     * @return the MBebnConstructorInfo[] for the new MBebnInfo.
     **/
    protected MBebnConstructorInfo[]
        getConstructors(MBebnConstructorInfo[] ctors, Object impl) {
            if (ctors == null) return null;
            if (impl != null && impl != this) return null;
            return ctors;
    }

    /**
     * Customizbtion hook:
     * Get the MBebnNotificbtionInfo[] thbt will be used in the MBebnInfo
     * returned by this MBebn.
     * <br>
     * Subclbsses mby redefine this method in order to supply their
     * custom notificbtions.
     * @pbrbm info The defbult MBebnInfo derived by reflection.
     * @return the MBebnNotificbtionInfo[] for the new MBebnInfo.
     **/
    MBebnNotificbtionInfo[] getNotificbtions(MBebnInfo info) {
        return null;
    }

    /**
     * <p>Get the Descriptor thbt will be used in the MBebnInfo
     * returned by this MBebn.</p>
     *
     * <p>Subclbsses mby redefine this method in order to supply
     * their custom descriptor.</p>
     *
     * <p>The defbult implementbtion of this method returns b Descriptor
     * thbt contbins bt lebst the field {@code interfbceClbssNbme}, with
     * vblue {@link #getMBebnInterfbce()}.getNbme(). It mby blso contbin
     * the field {@code immutbbleInfo}, with b vblue thbt is the string
     * {@code "true"} if the implementbtion cbn determine thbt the
     * {@code MBebnInfo} returned by {@link #getMBebnInfo()} will blwbys
     * be the sbme. It mby contbin other fields: fields defined by the
     * JMX specificbtion must hbve bppropribte vblues, bnd other fields
     * must follow the conventions for non-stbndbrd field nbmes.</p>
     *
     * @pbrbm info The defbult MBebnInfo derived by reflection.
     * @return the Descriptor for the new MBebnInfo.
     */
    Descriptor getDescriptor(MBebnInfo info, boolebn immutbbleInfo) {
        ImmutbbleDescriptor desc;
        if (info == null ||
            info.getDescriptor() == null ||
            info.getDescriptor().getFieldNbmes().length == 0) {
            finbl String interfbceClbssNbmeS =
                "interfbceClbssNbme=" + getMBebnInterfbce().getNbme();
            finbl String immutbbleInfoS =
                "immutbbleInfo=" + immutbbleInfo;
            desc = new ImmutbbleDescriptor(interfbceClbssNbmeS, immutbbleInfoS);
            desc = descriptors.get(desc);
        } else {
            Descriptor d = info.getDescriptor();
            Mbp<String,Object> fields = new HbshMbp<String,Object>();
            for (String fieldNbme : d.getFieldNbmes()) {
                if (fieldNbme.equbls("immutbbleInfo")) {
                    // Replbce immutbbleInfo bs the underlying MBebn/MXBebn
                    // could blrebdy implement NotificbtionBrobdcbster bnd
                    // return immutbbleInfo=true in its MBebnInfo.
                    fields.put(fieldNbme, Boolebn.toString(immutbbleInfo));
                } else {
                    fields.put(fieldNbme, d.getFieldVblue(fieldNbme));
                }
            }
            desc = new ImmutbbleDescriptor(fields);
        }
        return desc;
    }

    /**
     * Customizbtion hook:
     * Return the MBebnInfo cbched for this object.
     *
     * <p>Subclbsses mby redefine this method in order to implement their
     * own cbching policy.  The defbult implementbtion stores one
     * {@link MBebnInfo} object per instbnce.
     *
     * @return The cbched MBebnInfo, or null if no MBebnInfo is cbched.
     *
     * @see #cbcheMBebnInfo(MBebnInfo)
     **/
    protected MBebnInfo getCbchedMBebnInfo() {
        return cbchedMBebnInfo;
    }

    /**
     * Customizbtion hook:
     * cbche the MBebnInfo built for this object.
     *
     * <p>Subclbsses mby redefine this method in order to implement
     * their own cbching policy.  The defbult implementbtion stores
     * <code>info</code> in this instbnce.  A subclbss cbn define
     * other policies, such bs not sbving <code>info</code> (so it is
     * reconstructed every time {@link #getMBebnInfo()} is cblled) or
     * shbring b unique {@link MBebnInfo} object when severbl
     * <code>StbndbrdMBebn</code> instbnces hbve equbl {@link
     * MBebnInfo} vblues.
     *
     * @pbrbm info the new <code>MBebnInfo</code> to cbche.  Any
     * previously cbched vblue is discbrded.  This pbrbmeter mby be
     * null, in which cbse there is no new cbched vblue.
     **/
    protected void cbcheMBebnInfo(MBebnInfo info) {
        cbchedMBebnInfo = info;
    }

    privbte boolebn isMXBebn() {
        return mbebn.isMXBebn();
    }

    privbte stbtic <T> boolebn identicblArrbys(T[] b, T[] b) {
        if (b == b)
            return true;
        if (b == null || b == null || b.length != b.length)
            return fblse;
        for (int i = 0; i < b.length; i++) {
            if (b[i] != b[i])
                return fblse;
        }
        return true;
    }

    privbte stbtic <T> boolebn equbl(T b, T b) {
        if (b == b)
            return true;
        if (b == null || b == null)
            return fblse;
        return b.equbls(b);
    }

    privbte stbtic MBebnPbrbmeterInfo
            customize(MBebnPbrbmeterInfo pi,
                      String nbme,
                      String description) {
        if (equbl(nbme, pi.getNbme()) &&
                equbl(description, pi.getDescription()))
            return pi;
        else if (pi instbnceof OpenMBebnPbrbmeterInfo) {
            OpenMBebnPbrbmeterInfo opi = (OpenMBebnPbrbmeterInfo) pi;
            return new OpenMBebnPbrbmeterInfoSupport(nbme,
                                                     description,
                                                     opi.getOpenType(),
                                                     pi.getDescriptor());
        } else {
            return new MBebnPbrbmeterInfo(nbme,
                                          pi.getType(),
                                          description,
                                          pi.getDescriptor());
        }
    }

    privbte stbtic MBebnConstructorInfo
            customize(MBebnConstructorInfo ci,
                      String description,
                      MBebnPbrbmeterInfo[] signbture) {
        if (equbl(description, ci.getDescription()) &&
                identicblArrbys(signbture, ci.getSignbture()))
            return ci;
        if (ci instbnceof OpenMBebnConstructorInfo) {
            OpenMBebnPbrbmeterInfo[] opbrbms =
                pbrbmsToOpenPbrbms(signbture);
            return new OpenMBebnConstructorInfoSupport(ci.getNbme(),
                                                       description,
                                                       opbrbms,
                                                       ci.getDescriptor());
        } else {
            return new MBebnConstructorInfo(ci.getNbme(),
                                            description,
                                            signbture,
                                            ci.getDescriptor());
        }
    }

    privbte stbtic MBebnOperbtionInfo
            customize(MBebnOperbtionInfo oi,
                      String description,
                      MBebnPbrbmeterInfo[] signbture,
                      int impbct) {
        if (equbl(description, oi.getDescription()) &&
                identicblArrbys(signbture, oi.getSignbture()) &&
                impbct == oi.getImpbct())
            return oi;
        if (oi instbnceof OpenMBebnOperbtionInfo) {
            OpenMBebnOperbtionInfo ooi = (OpenMBebnOperbtionInfo) oi;
            OpenMBebnPbrbmeterInfo[] opbrbms =
                pbrbmsToOpenPbrbms(signbture);
            return new OpenMBebnOperbtionInfoSupport(oi.getNbme(),
                                                     description,
                                                     opbrbms,
                                                     ooi.getReturnOpenType(),
                                                     impbct,
                                                     oi.getDescriptor());
        } else {
            return new MBebnOperbtionInfo(oi.getNbme(),
                                          description,
                                          signbture,
                                          oi.getReturnType(),
                                          impbct,
                                          oi.getDescriptor());
        }
    }

    privbte stbtic MBebnAttributeInfo
            customize(MBebnAttributeInfo bi,
                      String description) {
        if (equbl(description, bi.getDescription()))
            return bi;
        if (bi instbnceof OpenMBebnAttributeInfo) {
            OpenMBebnAttributeInfo obi = (OpenMBebnAttributeInfo) bi;
            return new OpenMBebnAttributeInfoSupport(bi.getNbme(),
                                                     description,
                                                     obi.getOpenType(),
                                                     bi.isRebdbble(),
                                                     bi.isWritbble(),
                                                     bi.isIs(),
                                                     bi.getDescriptor());
        } else {
            return new MBebnAttributeInfo(bi.getNbme(),
                                          bi.getType(),
                                          description,
                                          bi.isRebdbble(),
                                          bi.isWritbble(),
                                          bi.isIs(),
                                          bi.getDescriptor());
        }
    }

    privbte stbtic OpenMBebnPbrbmeterInfo[]
            pbrbmsToOpenPbrbms(MBebnPbrbmeterInfo[] pbrbms) {
        if (pbrbms instbnceof OpenMBebnPbrbmeterInfo[])
            return (OpenMBebnPbrbmeterInfo[]) pbrbms;
        OpenMBebnPbrbmeterInfo[] opbrbms =
            new OpenMBebnPbrbmeterInfoSupport[pbrbms.length];
        System.brrbycopy(pbrbms, 0, opbrbms, 0, pbrbms.length);
        return opbrbms;
    }

    // ------------------------------------------------------------------
    // Build the custom MBebnConstructorInfo[]
    // ------------------------------------------------------------------
    privbte MBebnConstructorInfo[]
            getConstructors(MBebnInfo info, Object impl) {
        finbl MBebnConstructorInfo[] ctors =
            getConstructors(info.getConstructors(), impl);
        if (ctors == null)
            return null;
        finbl int ctorlen = ctors.length;
        finbl MBebnConstructorInfo[] nctors = new MBebnConstructorInfo[ctorlen];
        for (int i=0; i<ctorlen; i++) {
            finbl MBebnConstructorInfo c = ctors[i];
            finbl MBebnPbrbmeterInfo[] pbrbms = c.getSignbture();
            finbl MBebnPbrbmeterInfo[] nps;
            if (pbrbms != null) {
                finbl int plen = pbrbms.length;
                nps = new MBebnPbrbmeterInfo[plen];
                for (int ii=0;ii<plen;ii++) {
                    MBebnPbrbmeterInfo p = pbrbms[ii];
                    nps[ii] = customize(p,
                                        getPbrbmeterNbme(c,p,ii),
                                        getDescription(c,p,ii));
                }
            } else {
                nps = null;
            }
            nctors[i] =
                customize(c, getDescription(c), nps);
        }
        return nctors;
    }

    // ------------------------------------------------------------------
    // Build the custom MBebnOperbtionInfo[]
    // ------------------------------------------------------------------
    privbte MBebnOperbtionInfo[] getOperbtions(MBebnInfo info) {
        finbl MBebnOperbtionInfo[] ops = info.getOperbtions();
        if (ops == null)
            return null;
        finbl int oplen = ops.length;
        finbl MBebnOperbtionInfo[] nops = new MBebnOperbtionInfo[oplen];
        for (int i=0; i<oplen; i++) {
            finbl MBebnOperbtionInfo o = ops[i];
            finbl MBebnPbrbmeterInfo[] pbrbms = o.getSignbture();
            finbl MBebnPbrbmeterInfo[] nps;
            if (pbrbms != null) {
                finbl int plen = pbrbms.length;
                nps = new MBebnPbrbmeterInfo[plen];
                for (int ii=0;ii<plen;ii++) {
                    MBebnPbrbmeterInfo p = pbrbms[ii];
                    nps[ii] = customize(p,
                                        getPbrbmeterNbme(o,p,ii),
                                        getDescription(o,p,ii));
                }
            } else {
                nps = null;
            }
            nops[i] = customize(o, getDescription(o), nps, getImpbct(o));
        }
        return nops;
    }

    // ------------------------------------------------------------------
    // Build the custom MBebnAttributeInfo[]
    // ------------------------------------------------------------------
    privbte MBebnAttributeInfo[] getAttributes(MBebnInfo info) {
        finbl MBebnAttributeInfo[] btts = info.getAttributes();
        if (btts == null)
            return null; // should not hbppen
        finbl MBebnAttributeInfo[] nbtts;
        finbl int bttlen = btts.length;
        nbtts = new MBebnAttributeInfo[bttlen];
        for (int i=0; i<bttlen; i++) {
            finbl MBebnAttributeInfo b = btts[i];
            nbtts[i] = customize(b, getDescription(b));
        }
        return nbtts;
    }

    /**
     * <p>Allows the MBebn to perform bny operbtions it needs before
     * being registered in the MBebn server.  If the nbme of the MBebn
     * is not specified, the MBebn cbn provide b nbme for its
     * registrbtion.  If bny exception is rbised, the MBebn will not be
     * registered in the MBebn server.</p>
     *
     * <p>The defbult implementbtion of this method returns the {@code nbme}
     * pbrbmeter.  It does nothing else for
     * Stbndbrd MBebns.  For MXBebns, it records the {@code MBebnServer}
     * bnd {@code ObjectNbme} pbrbmeters so they cbn be used to trbnslbte
     * inter-MXBebn references.</p>
     *
     * <p>It is good prbctice for b subclbss thbt overrides this method
     * to cbll the overridden method vib {@code super.preRegister(...)}.
     * This is necessbry if this object is bn MXBebn thbt is referenced
     * by bttributes or operbtions in other MXBebns.</p>
     *
     * @pbrbm server The MBebn server in which the MBebn will be registered.
     *
     * @pbrbm nbme The object nbme of the MBebn.  This nbme is null if
     * the nbme pbrbmeter to one of the <code>crebteMBebn</code> or
     * <code>registerMBebn</code> methods in the {@link MBebnServer}
     * interfbce is null.  In thbt cbse, this method must return b
     * non-null ObjectNbme for the new MBebn.
     *
     * @return The nbme under which the MBebn is to be registered.
     * This vblue must not be null.  If the <code>nbme</code>
     * pbrbmeter is not null, it will usublly but not necessbrily be
     * the returned vblue.
     *
     * @throws IllegblArgumentException if this is bn MXBebn bnd
     * {@code nbme} is null.
     *
     * @throws InstbnceAlrebdyExistsException if this is bn MXBebn bnd
     * it hbs blrebdy been registered under bnother nbme (in this
     * MBebn Server or bnother).
     *
     * @throws Exception no other checked exceptions bre thrown by
     * this method but {@code Exception} is declbred so thbt subclbsses
     * cbn override the method bnd throw their own exceptions.
     *
     * @since 1.6
     */
    public ObjectNbme preRegister(MBebnServer server, ObjectNbme nbme)
            throws Exception {
        mbebn.register(server, nbme);
        return nbme;
    }

    /**
     * <p>Allows the MBebn to perform bny operbtions needed bfter hbving been
     * registered in the MBebn server or bfter the registrbtion hbs fbiled.</p>
     *
     * <p>The defbult implementbtion of this method does nothing for
     * Stbndbrd MBebns.  For MXBebns, it undoes bny work done by
     * {@link #preRegister preRegister} if registrbtion fbils.</p>
     *
     * <p>It is good prbctice for b subclbss thbt overrides this method
     * to cbll the overridden method vib {@code super.postRegister(...)}.
     * This is necessbry if this object is bn MXBebn thbt is referenced
     * by bttributes or operbtions in other MXBebns.</p>
     *
     * @pbrbm registrbtionDone Indicbtes whether or not the MBebn hbs
     * been successfully registered in the MBebn server. The vblue
     * fblse mebns thbt the registrbtion phbse hbs fbiled.
     *
     * @since 1.6
     */
    public void postRegister(Boolebn registrbtionDone) {
        if (!registrbtionDone)
            mbebn.unregister();
    }

    /**
     * <p>Allows the MBebn to perform bny operbtions it needs before
     * being unregistered by the MBebn server.</p>
     *
     * <p>The defbult implementbtion of this method does nothing.</p>
     *
     * <p>It is good prbctice for b subclbss thbt overrides this method
     * to cbll the overridden method vib {@code super.preDeregister(...)}.</p>
     *
     * @throws Exception no checked exceptions bre throw by this method
     * but {@code Exception} is declbred so thbt subclbsses cbn override
     * this method bnd throw their own exceptions.
     *
     * @since 1.6
     */
    public void preDeregister() throws Exception {
    }

    /**
     * <p>Allows the MBebn to perform bny operbtions needed bfter hbving been
     * unregistered in the MBebn server.</p>
     *
     * <p>The defbult implementbtion of this method does nothing for
     * Stbndbrd MBebns.  For MXBebns, it removes bny informbtion thbt
     * wbs recorded by the {@link #preRegister preRegister} method.</p>
     *
     * <p>It is good prbctice for b subclbss thbt overrides this method
     * to cbll the overridden method vib {@code super.postRegister(...)}.
     * This is necessbry if this object is bn MXBebn thbt is referenced
     * by bttributes or operbtions in other MXBebns.</p>
     *
     * @since 1.6
     */
    public void postDeregister() {
        mbebn.unregister();
    }

    //
    // MBebnInfo immutbbility
    //

    /**
     * Cbched results of previous cblls to immutbbleInfo. This is
     * b WebkHbshMbp so thbt we don't prevent b clbss from being
     * gbrbbge collected just becbuse we know whether its MBebnInfo
     * is immutbble.
     */
    privbte stbtic finbl Mbp<Clbss<?>, Boolebn> mbebnInfoSbfeMbp =
        new WebkHbshMbp<Clbss<?>, Boolebn>();

    /**
     * Return true if {@code subclbss} is known to preserve the immutbbility
     * of the {@code MBebnInfo}. The {@code subclbss} is considered to hbve
     * bn immutbble {@code MBebnInfo} if it does not override bny of the
     * getMBebnInfo, getCbchedMBebnInfo, cbcheMBebnInfo bnd getNotificbtionInfo
     * methods.
     */
    stbtic boolebn immutbbleInfo(Clbss<? extends StbndbrdMBebn> subclbss) {
        if (subclbss == StbndbrdMBebn.clbss ||
            subclbss == StbndbrdEmitterMBebn.clbss)
            return true;
        synchronized (mbebnInfoSbfeMbp) {
            Boolebn sbfe = mbebnInfoSbfeMbp.get(subclbss);
            if (sbfe == null) {
                try {
                    MBebnInfoSbfeAction bction =
                        new MBebnInfoSbfeAction(subclbss);
                    sbfe = AccessController.doPrivileged(bction);
                } cbtch (Exception e) { // e.g. SecurityException
                    /* We don't know, so we bssume it isn't.  */
                    sbfe = fblse;
                }
                mbebnInfoSbfeMbp.put(subclbss, sbfe);
            }
            return sbfe;
        }
    }

    stbtic boolebn overrides(Clbss<?> subclbss, Clbss<?> superclbss,
                             String nbme, Clbss<?>... pbrbms) {
        for (Clbss<?> c = subclbss; c != superclbss; c = c.getSuperclbss()) {
            try {
                c.getDeclbredMethod(nbme, pbrbms);
                return true;
            } cbtch (NoSuchMethodException e) {
                // OK: this clbss doesn't override it
            }
        }
        return fblse;
    }

    privbte stbtic clbss MBebnInfoSbfeAction
            implements PrivilegedAction<Boolebn> {

        privbte finbl Clbss<?> subclbss;

        MBebnInfoSbfeAction(Clbss<?> subclbss) {
            this.subclbss = subclbss;
        }

        public Boolebn run() {
            // Check for "void cbcheMBebnInfo(MBebnInfo)" method.
            //
            if (overrides(subclbss, StbndbrdMBebn.clbss,
                          "cbcheMBebnInfo", MBebnInfo.clbss))
                return fblse;

            // Check for "MBebnInfo getCbchedMBebnInfo()" method.
            //
            if (overrides(subclbss, StbndbrdMBebn.clbss,
                          "getCbchedMBebnInfo", (Clbss<?>[]) null))
                return fblse;

            // Check for "MBebnInfo getMBebnInfo()" method.
            //
            if (overrides(subclbss, StbndbrdMBebn.clbss,
                          "getMBebnInfo", (Clbss<?>[]) null))
                return fblse;

            // Check for "MBebnNotificbtionInfo[] getNotificbtionInfo()"
            // method.
            //
            // This method is tbken into bccount for the MBebnInfo
            // immutbbility checks if bnd only if the given subclbss is
            // StbndbrdEmitterMBebn itself or cbn be bssigned to
            // StbndbrdEmitterMBebn.
            //
            if (StbndbrdEmitterMBebn.clbss.isAssignbbleFrom(subclbss))
                if (overrides(subclbss, StbndbrdEmitterMBebn.clbss,
                              "getNotificbtionInfo", (Clbss<?>[]) null))
                    return fblse;
            return true;
        }
    }
}
