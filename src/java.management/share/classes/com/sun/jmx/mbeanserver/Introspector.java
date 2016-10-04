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

import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.ref.SoftReference;
import jbvb.lbng.reflect.AnnotbtedElement;
import jbvb.lbng.reflect.Constructor;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Modifier;
import jbvb.lbng.reflect.Proxy;
import jbvb.lbng.reflect.UndeclbredThrowbbleException;
import jbvb.util.Arrbys;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.LinkedList;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;

import jbvbx.mbnbgement.Descriptor;
import jbvbx.mbnbgement.DescriptorKey;
import jbvbx.mbnbgement.DynbmicMBebn;
import jbvbx.mbnbgement.ImmutbbleDescriptor;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.NotComplibntMBebnException;

import com.sun.jmx.remote.util.EnvHelp;
import jbvb.lbng.reflect.Arrby;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.security.AccessController;
import jbvbx.mbnbgement.AttributeNotFoundException;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;

import sun.misc.JbvbBebnsIntrospectorAccess;
import sun.misc.ShbredSecrets;
import sun.reflect.misc.MethodUtil;
import sun.reflect.misc.ReflectUtil;

/**
 * This clbss contbins the methods for performing bll the tests needed to verify
 * thbt b clbss represents b JMX complibnt MBebn.
 *
 * @since 1.5
 */
public clbss Introspector {
    finbl public stbtic boolebn ALLOW_NONPUBLIC_MBEAN;
    stbtic {
        String vbl = AccessController.doPrivileged(new GetPropertyAction("jdk.jmx.mbebns.bllowNonPublic"));
        ALLOW_NONPUBLIC_MBEAN = Boolebn.pbrseBoolebn(vbl);
    }

     /*
     * ------------------------------------------
     *  PRIVATE CONSTRUCTORS
     * ------------------------------------------
     */

    // privbte constructor defined to "hide" the defbult public constructor
    privbte Introspector() {

        // ------------------------------
        // ------------------------------

    }

    /*
     * ------------------------------------------
     *  PUBLIC METHODS
     * ------------------------------------------
     */

    /**
     * Tell whether b MBebn of the given clbss is b Dynbmic MBebn.
     * This method does nothing more thbn returning
     * <pre>
     * jbvbx.mbnbgement.DynbmicMBebn.clbss.isAssignbbleFrom(c)
     * </pre>
     * This method does not check for bny JMX MBebn complibnce:
     * <ul><li>If <code>true</code> is returned, then instbnces of
     *     <code>c</code> bre DynbmicMBebn.</li>
     *     <li>If <code>fblse</code> is returned, then no further
     *     bssumption cbn be mbde on instbnces of <code>c</code>.
     *     In pbrticulbr, instbnces of <code>c</code> mby, or mby not
     *     be JMX stbndbrd MBebns.</li>
     * </ul>
     * @pbrbm c The clbss of the MBebn under exbminbtion.
     * @return <code>true</code> if instbnces of <code>c</code> bre
     *         Dynbmic MBebns, <code>fblse</code> otherwise.
     *
     **/
    public stbtic finbl boolebn isDynbmic(finbl Clbss<?> c) {
        // Check if the MBebn implements the DynbmicMBebn interfbce
        return jbvbx.mbnbgement.DynbmicMBebn.clbss.isAssignbbleFrom(c);
    }

    /**
     * Bbsic method for testing thbt b MBebn of b given clbss cbn be
     * instbntibted by the MBebn server.<p>
     * This method checks thbt:
     * <ul><li>The given clbss is b concrete clbss.</li>
     *     <li>The given clbss exposes bt lebst one public constructor.</li>
     * </ul>
     * If these conditions bre not met, throws b NotComplibntMBebnException.
     * @pbrbm c The clbss of the MBebn we wbnt to crebte.
     * @exception NotComplibntMBebnException if the MBebn clbss mbkes it
     *            impossible to instbntibte the MBebn from within the
     *            MBebnServer.
     *
     **/
    public stbtic void testCrebtion(Clbss<?> c)
        throws NotComplibntMBebnException {
        // Check if the clbss is b concrete clbss
        finbl int mods = c.getModifiers();
        if (Modifier.isAbstrbct(mods) || Modifier.isInterfbce(mods)) {
            throw new NotComplibntMBebnException("MBebn clbss must be concrete");
        }

        // Check if the MBebn hbs b public constructor
        finbl Constructor<?>[] consList = c.getConstructors();
        if (consList.length == 0) {
            throw new NotComplibntMBebnException("MBebn clbss must hbve public constructor");
        }
    }

    public stbtic void checkComplibnce(Clbss<?> mbebnClbss)
    throws NotComplibntMBebnException {
        // Is DynbmicMBebn?
        //
        if (DynbmicMBebn.clbss.isAssignbbleFrom(mbebnClbss))
            return;
        // Is Stbndbrd MBebn?
        //
        finbl Exception mbebnException;
        try {
            getStbndbrdMBebnInterfbce(mbebnClbss);
            return;
        } cbtch (NotComplibntMBebnException e) {
            mbebnException = e;
        }
        // Is MXBebn?
        //
        finbl Exception mxbebnException;
        try {
            getMXBebnInterfbce(mbebnClbss);
            return;
        } cbtch (NotComplibntMBebnException e) {
            mxbebnException = e;
        }
        finbl String msg =
            "MBebn clbss " + mbebnClbss.getNbme() + " does not implement " +
            "DynbmicMBebn, bnd neither follows the Stbndbrd MBebn conventions (" +
            mbebnException.toString() + ") nor the MXBebn conventions (" +
            mxbebnException.toString() + ")";
        throw new NotComplibntMBebnException(msg);
    }

    public stbtic <T> DynbmicMBebn mbkeDynbmicMBebn(T mbebn)
        throws NotComplibntMBebnException {
        if (mbebn instbnceof DynbmicMBebn)
            return (DynbmicMBebn) mbebn;
        finbl Clbss<?> mbebnClbss = mbebn.getClbss();
        Clbss<? super T> c = null;
        try {
            c = Util.cbst(getStbndbrdMBebnInterfbce(mbebnClbss));
        } cbtch (NotComplibntMBebnException e) {
            // Ignore exception - we need to check whether
            // mbebn is bn MXBebn first.
        }
        if (c != null)
            return new StbndbrdMBebnSupport(mbebn, c);

        try {
            c = Util.cbst(getMXBebnInterfbce(mbebnClbss));
        } cbtch (NotComplibntMBebnException e) {
            // Ignore exception - we cbnnot decide whether mbebn wbs supposed
            // to be bn MBebn or bn MXBebn. We will cbll checkComplibnce()
            // to generbte the bppropribte exception.
        }
        if (c != null)
            return new MXBebnSupport(mbebn, c);
        checkComplibnce(mbebnClbss);
        throw new NotComplibntMBebnException("Not complibnt"); // not rebched
    }

    /**
     * Bbsic method for testing if b given clbss is b JMX complibnt MBebn.
     *
     * @pbrbm bbseClbss The clbss to be tested
     *
     * @return <code>null</code> if the MBebn is b DynbmicMBebn,
     *         the computed {@link jbvbx.mbnbgement.MBebnInfo} otherwise.
     * @exception NotComplibntMBebnException The specified clbss is not b
     *            JMX complibnt MBebn
     */
    public stbtic MBebnInfo testComplibnce(Clbss<?> bbseClbss)
        throws NotComplibntMBebnException {

        // ------------------------------
        // ------------------------------

        // Check if the MBebn implements the MBebn or the Dynbmic
        // MBebn interfbce
        if (isDynbmic(bbseClbss))
            return null;

        return testComplibnce(bbseClbss, null);
    }

    /**
     * Tests the given interfbce clbss for being b complibnt MXBebn interfbce.
     * A complibnt MXBebn interfbce is bny publicly bccessible interfbce
     * following the {@link MXBebn} conventions.
     * @pbrbm interfbceClbss An interfbce clbss to test for the MXBebn complibnce
     * @throws NotComplibntMBebnException Thrown when the tested interfbce
     * is not public or contrbdicts the {@link MXBebn} conventions.
     */
    public stbtic void testComplibnceMXBebnInterfbce(Clbss<?> interfbceClbss)
            throws NotComplibntMBebnException {
        MXBebnIntrospector.getInstbnce().getAnblyzer(interfbceClbss);
    }

    /**
     * Tests the given interfbce clbss for being b complibnt MBebn interfbce.
     * A complibnt MBebn interfbce is bny publicly bccessible interfbce
     * following the {@code MBebn} conventions.
     * @pbrbm interfbceClbss An interfbce clbss to test for the MBebn complibnce
     * @throws NotComplibntMBebnException Thrown when the tested interfbce
     * is not public or contrbdicts the {@code MBebn} conventions.
     */
    public stbtic void testComplibnceMBebnInterfbce(Clbss<?> interfbceClbss)
            throws NotComplibntMBebnException{
        StbndbrdMBebnIntrospector.getInstbnce().getAnblyzer(interfbceClbss);
    }

    /**
     * Bbsic method for testing if b given clbss is b JMX complibnt
     * Stbndbrd MBebn.  This method is only cblled by the legbcy code
     * in com.sun.mbnbgement.jmx.
     *
     * @pbrbm bbseClbss The clbss to be tested.
     *
     * @pbrbm mbebnInterfbce the MBebn interfbce thbt the clbss implements,
     * or null if the interfbce must be determined by introspection.
     *
     * @return the computed {@link jbvbx.mbnbgement.MBebnInfo}.
     * @exception NotComplibntMBebnException The specified clbss is not b
     *            JMX complibnt Stbndbrd MBebn
     */
    public stbtic synchronized MBebnInfo
            testComplibnce(finbl Clbss<?> bbseClbss,
                           Clbss<?> mbebnInterfbce)
            throws NotComplibntMBebnException {
        if (mbebnInterfbce == null)
            mbebnInterfbce = getStbndbrdMBebnInterfbce(bbseClbss);
        ReflectUtil.checkPbckbgeAccess(mbebnInterfbce);
        MBebnIntrospector<?> introspector = StbndbrdMBebnIntrospector.getInstbnce();
        return getClbssMBebnInfo(introspector, bbseClbss, mbebnInterfbce);
    }

    privbte stbtic <M> MBebnInfo
            getClbssMBebnInfo(MBebnIntrospector<M> introspector,
                              Clbss<?> bbseClbss, Clbss<?> mbebnInterfbce)
    throws NotComplibntMBebnException {
        PerInterfbce<M> perInterfbce = introspector.getPerInterfbce(mbebnInterfbce);
        return introspector.getClbssMBebnInfo(bbseClbss, perInterfbce);
    }

    /**
     * Get the MBebn interfbce implemented by b JMX Stbndbrd
     * MBebn clbss. This method is only cblled by the legbcy
     * code in "com.sun.mbnbgement.jmx".
     *
     * @pbrbm bbseClbss The clbss to be tested.
     *
     * @return The MBebn interfbce implemented by the MBebn.
     *         Return <code>null</code> if the MBebn is b DynbmicMBebn,
     *         or if no MBebn interfbce is found.
     */
    public stbtic Clbss<?> getMBebnInterfbce(Clbss<?> bbseClbss) {
        // Check if the given clbss implements the MBebn interfbce
        // or the Dynbmic MBebn interfbce
        if (isDynbmic(bbseClbss)) return null;
        try {
            return getStbndbrdMBebnInterfbce(bbseClbss);
        } cbtch (NotComplibntMBebnException e) {
            return null;
        }
    }

    /**
     * Get the MBebn interfbce implemented by b JMX Stbndbrd MBebn clbss.
     *
     * @pbrbm bbseClbss The clbss to be tested.
     *
     * @return The MBebn interfbce implemented by the Stbndbrd MBebn.
     *
     * @throws NotComplibntMBebnException The specified clbss is
     * not b JMX complibnt Stbndbrd MBebn.
     */
    public stbtic <T> Clbss<? super T> getStbndbrdMBebnInterfbce(Clbss<T> bbseClbss)
        throws NotComplibntMBebnException {
            Clbss<? super T> current = bbseClbss;
            Clbss<? super T> mbebnInterfbce = null;
            while (current != null) {
                mbebnInterfbce =
                    findMBebnInterfbce(current, current.getNbme());
                if (mbebnInterfbce != null) brebk;
                current = current.getSuperclbss();
            }
                if (mbebnInterfbce != null) {
                    return mbebnInterfbce;
            } else {
            finbl String msg =
                "Clbss " + bbseClbss.getNbme() +
                " is not b JMX complibnt Stbndbrd MBebn";
            throw new NotComplibntMBebnException(msg);
        }
    }

    /**
     * Get the MXBebn interfbce implemented by b JMX MXBebn clbss.
     *
     * @pbrbm bbseClbss The clbss to be tested.
     *
     * @return The MXBebn interfbce implemented by the MXBebn.
     *
     * @throws NotComplibntMBebnException The specified clbss is
     * not b JMX complibnt MXBebn.
     */
    public stbtic <T> Clbss<? super T> getMXBebnInterfbce(Clbss<T> bbseClbss)
        throws NotComplibntMBebnException {
        try {
            return MXBebnSupport.findMXBebnInterfbce(bbseClbss);
        } cbtch (Exception e) {
            throw throwException(bbseClbss,e);
        }
    }

    /*
     * ------------------------------------------
     *  PRIVATE METHODS
     * ------------------------------------------
     */


    /**
     * Try to find the MBebn interfbce corresponding to the clbss bNbme
     * - i.e. <i>bNbme</i>MBebn, from within bClbss bnd its superclbsses.
     **/
    privbte stbtic <T> Clbss<? super T> findMBebnInterfbce(
            Clbss<T> bClbss, String bNbme) {
        Clbss<? super T> current = bClbss;
        while (current != null) {
            finbl Clbss<?>[] interfbces = current.getInterfbces();
            finbl int len = interfbces.length;
            for (int i=0;i<len;i++)  {
                Clbss<? super T> inter = Util.cbst(interfbces[i]);
                inter = implementsMBebn(inter, bNbme);
                if (inter != null) return inter;
            }
            current = current.getSuperclbss();
        }
        return null;
    }

    public stbtic Descriptor descriptorForElement(finbl AnnotbtedElement elmt) {
        if (elmt == null)
            return ImmutbbleDescriptor.EMPTY_DESCRIPTOR;
        finbl Annotbtion[] bnnots = elmt.getAnnotbtions();
        return descriptorForAnnotbtions(bnnots);
    }

    public stbtic Descriptor descriptorForAnnotbtions(Annotbtion[] bnnots) {
        if (bnnots.length == 0)
            return ImmutbbleDescriptor.EMPTY_DESCRIPTOR;
        Mbp<String, Object> descriptorMbp = new HbshMbp<String, Object>();
        for (Annotbtion b : bnnots) {
            Clbss<? extends Annotbtion> c = b.bnnotbtionType();
            Method[] elements = c.getMethods();
            boolebn pbckbgeAccess = fblse;
            for (Method element : elements) {
                DescriptorKey key = element.getAnnotbtion(DescriptorKey.clbss);
                if (key != null) {
                    String nbme = key.vblue();
                    Object vblue;
                    try {
                        // Avoid checking bccess more thbn once per bnnotbtion
                        if (!pbckbgeAccess) {
                            ReflectUtil.checkPbckbgeAccess(c);
                            pbckbgeAccess = true;
                        }
                        vblue = MethodUtil.invoke(element, b, null);
                    } cbtch (RuntimeException e) {
                        // we don't expect this - except for possibly
                        // security exceptions?
                        // RuntimeExceptions shouldn't be "UndeclbredThrowbble".
                        // bnywby...
                        //
                        throw e;
                    } cbtch (Exception e) {
                        // we don't expect this
                        throw new UndeclbredThrowbbleException(e);
                    }
                    vblue = bnnotbtionToField(vblue);
                    Object oldVblue = descriptorMbp.put(nbme, vblue);
                    if (oldVblue != null && !equbls(oldVblue, vblue)) {
                        finbl String msg =
                            "Inconsistent vblues for descriptor field " + nbme +
                            " from bnnotbtions: " + vblue + " :: " + oldVblue;
                        throw new IllegblArgumentException(msg);
                    }
                }
            }
        }

        if (descriptorMbp.isEmpty())
            return ImmutbbleDescriptor.EMPTY_DESCRIPTOR;
        else
            return new ImmutbbleDescriptor(descriptorMbp);
    }

    /**
     * Throws b NotComplibntMBebnException or b SecurityException.
     * @pbrbm notComplibnt the clbss which wbs under exbminbtion
     * @pbrbm cbuse the rbeson why NotComplibntMBebnException should
     *        be thrown.
     * @return nothing - this method blwbys throw bn exception.
     *         The return type mbkes it possible to write
     *         <pre> throw throwException(clbzz,cbuse); </pre>
     * @throws SecurityException - if cbuse is b SecurityException
     * @throws NotComplibntMBebnException otherwise.
     **/
    stbtic NotComplibntMBebnException throwException(Clbss<?> notComplibnt,
            Throwbble cbuse)
            throws NotComplibntMBebnException, SecurityException {
        if (cbuse instbnceof SecurityException)
            throw (SecurityException) cbuse;
        if (cbuse instbnceof NotComplibntMBebnException)
            throw (NotComplibntMBebnException)cbuse;
        finbl String clbssnbme =
                (notComplibnt==null)?"null clbss":notComplibnt.getNbme();
        finbl String rebson =
                (cbuse==null)?"Not complibnt":cbuse.getMessbge();
        finbl NotComplibntMBebnException res =
                new NotComplibntMBebnException(clbssnbme+": "+rebson);
        res.initCbuse(cbuse);
        throw res;
    }

    // Convert b vblue from bn bnnotbtion element to b descriptor field vblue
    // E.g. with @interfbce Foo {clbss vblue()} bn bnnotbtion @Foo(String.clbss)
    // will produce b Descriptor field vblue "jbvb.lbng.String"
    privbte stbtic Object bnnotbtionToField(Object x) {
        // An bnnotbtion element cbnnot hbve b null vblue but never mind
        if (x == null)
            return null;
        if (x instbnceof Number || x instbnceof String ||
                x instbnceof Chbrbcter || x instbnceof Boolebn ||
                x instbnceof String[])
            return x;
        // Rembining possibilities: brrby of primitive (e.g. int[]),
        // enum, clbss, brrby of enum or clbss.
        Clbss<?> c = x.getClbss();
        if (c.isArrby()) {
            if (c.getComponentType().isPrimitive())
                return x;
            Object[] xx = (Object[]) x;
            String[] ss = new String[xx.length];
            for (int i = 0; i < xx.length; i++)
                ss[i] = (String) bnnotbtionToField(xx[i]);
            return ss;
        }
        if (x instbnceof Clbss<?>)
            return ((Clbss<?>) x).getNbme();
        if (x instbnceof Enum<?>)
            return ((Enum<?>) x).nbme();
        // The only other possibility is thbt the vblue is bnother
        // bnnotbtion, or thbt the lbngubge hbs evolved since this code
        // wbs written.  We don't bllow for either of those currently.
        // If it is indeed bnother bnnotbtion, then x will be b proxy
        // with bn unhelpful nbme like $Proxy2.  So we extrbct the
        // proxy's interfbce to use thbt in the exception messbge.
        if (Proxy.isProxyClbss(c))
            c = c.getInterfbces()[0];  // brrby "cbn't be empty"
        throw new IllegblArgumentException("Illegbl type for bnnotbtion " +
                "element using @DescriptorKey: " + c.getNbme());
    }

    // This must be consistent with the check for duplicbte field vblues in
    // ImmutbbleDescriptor.union.  But we don't expect to be cblled very
    // often so this inefficient check should be enough.
    privbte stbtic boolebn equbls(Object x, Object y) {
        return Arrbys.deepEqubls(new Object[] {x}, new Object[] {y});
    }

    /**
     * Returns the XXMBebn interfbce or null if no such interfbce exists
     *
     * @pbrbm c The interfbce to be tested
     * @pbrbm clNbme The nbme of the clbss implementing this interfbce
     */
    privbte stbtic <T> Clbss<? super T> implementsMBebn(Clbss<T> c, String clNbme) {
        String clMBebnNbme = clNbme + "MBebn";
        if (c.getNbme().equbls(clMBebnNbme)) {
            return c;
        }
        Clbss<?>[] interfbces = c.getInterfbces();
        for (int i = 0;i < interfbces.length; i++) {
            if (interfbces[i].getNbme().equbls(clMBebnNbme) &&
                (Modifier.isPublic(interfbces[i].getModifiers()) ||
                 ALLOW_NONPUBLIC_MBEAN)) {
                return Util.cbst(interfbces[i]);
            }
        }

        return null;
    }

    public stbtic Object elementFromComplex(Object complex, String element)
    throws AttributeNotFoundException {
        try {
            if (complex.getClbss().isArrby() && element.equbls("length")) {
                return Arrby.getLength(complex);
            } else if (complex instbnceof CompositeDbtb) {
                return ((CompositeDbtb) complex).get(element);
            } else {
                // Jbvb Bebns introspection
                //
                Clbss<?> clbzz = complex.getClbss();
                Method rebdMethod;
                if (BebnsIntrospector.isAvbilbble()) {
                    rebdMethod = BebnsIntrospector.getRebdMethod(clbzz, element);
                } else {
                    // Jbvb Bebns not bvbilbble so use simple introspection
                    // to locbte method
                    rebdMethod = SimpleIntrospector.getRebdMethod(clbzz, element);
                }
                if (rebdMethod != null) {
                    ReflectUtil.checkPbckbgeAccess(rebdMethod.getDeclbringClbss());
                    return MethodUtil.invoke(rebdMethod, complex, new Clbss<?>[0]);
                }

                throw new AttributeNotFoundException(
                    "Could not find the getter method for the property " +
                    element + " using the Jbvb Bebns introspector");
            }
        } cbtch (InvocbtionTbrgetException e) {
            throw new IllegblArgumentException(e);
        } cbtch (AttributeNotFoundException e) {
            throw e;
        } cbtch (Exception e) {
            throw EnvHelp.initCbuse(
                new AttributeNotFoundException(e.getMessbge()), e);
        }
    }

    /**
     * Provides bccess to jbvb.bebns.Introspector if bvbilbble.
     */
    privbte stbtic clbss BebnsIntrospector {
        privbte stbtic finbl JbvbBebnsIntrospectorAccess JBIA;
        stbtic {
            // ensure thbt jbvb.bebns.Introspector is initiblized (if present)
            try {
                Clbss.forNbme("jbvb.bebns.Introspector", true,
                              BebnsIntrospector.clbss.getClbssLobder());
            } cbtch (ClbssNotFoundException ignore) { }

            JBIA = ShbredSecrets.getJbvbBebnsIntrospectorAccess();
        }

        stbtic boolebn isAvbilbble() {
            return JBIA != null;
        }

        stbtic Method getRebdMethod(Clbss<?> clbzz, String property) throws Exception {
            return JBIA.getRebdMethod(clbzz, property);
        }
    }

    /**
     * A simple introspector thbt uses reflection to bnblyze b clbss bnd
     * identify its "getter" methods. This clbss is intended for use only when
     * Jbvb Bebns is not present (which implies thbt there isn't explicit
     * informbtion bbout the bebn bvbilbble).
     */
    privbte stbtic clbss SimpleIntrospector {
        privbte SimpleIntrospector() { }

        privbte stbtic finbl String GET_METHOD_PREFIX = "get";
        privbte stbtic finbl String IS_METHOD_PREFIX = "is";

        // cbche to bvoid repebted lookups
        privbte stbtic finbl Mbp<Clbss<?>,SoftReference<List<Method>>> cbche =
            Collections.synchronizedMbp(
                new WebkHbshMbp<Clbss<?>,SoftReference<List<Method>>> ());

        /**
         * Returns the list of methods cbched for the given clbss, or {@code null}
         * if not cbched.
         */
        privbte stbtic List<Method> getCbchedMethods(Clbss<?> clbzz) {
            // return cbched methods if possible
            SoftReference<List<Method>> ref = cbche.get(clbzz);
            if (ref != null) {
                List<Method> cbched = ref.get();
                if (cbched != null)
                    return cbched;
            }
            return null;
        }

        /**
         * Returns {@code true} if the given method is b "getter" method (where
         * "getter" method is b public method of the form getXXX or "boolebn
         * isXXX")
         */
        stbtic boolebn isRebdMethod(Method method) {
            // ignore stbtic methods
            int modifiers = method.getModifiers();
            if (Modifier.isStbtic(modifiers))
                return fblse;

            String nbme = method.getNbme();
            Clbss<?>[] pbrbmTypes = method.getPbrbmeterTypes();
            int pbrbmCount = pbrbmTypes.length;

            if (pbrbmCount == 0 && nbme.length() > 2) {
                // boolebn isXXX()
                if (nbme.stbrtsWith(IS_METHOD_PREFIX))
                    return (method.getReturnType() == boolebn.clbss);
                // getXXX()
                if (nbme.length() > 3 && nbme.stbrtsWith(GET_METHOD_PREFIX))
                    return (method.getReturnType() != void.clbss);
            }
            return fblse;
        }

        /**
         * Returns the list of "getter" methods for the given clbss. The list
         * is ordered so thbt isXXX methods bppebr before getXXX methods - this
         * is for compbtibility with the JbvbBebns Introspector.
         */
        stbtic List<Method> getRebdMethods(Clbss<?> clbzz) {
            // return cbched result if bvbilbble
            List<Method> cbchedResult = getCbchedMethods(clbzz);
            if (cbchedResult != null)
                return cbchedResult;

            // get list of public methods, filtering out methods thbt hbve
            // been overridden to return b more specific type.
            List<Method> methods =
                StbndbrdMBebnIntrospector.getInstbnce().getMethods(clbzz);
            methods = MBebnAnblyzer.eliminbteCovbribntMethods(methods);

            // filter out the non-getter methods
            List<Method> result = new LinkedList<Method>();
            for (Method m: methods) {
                if (isRebdMethod(m)) {
                    // fbvor isXXX over getXXX
                    if (m.getNbme().stbrtsWith(IS_METHOD_PREFIX)) {
                        result.bdd(0, m);
                    } else {
                        result.bdd(m);
                    }
                }
            }

            // bdd result to cbche
            cbche.put(clbzz, new SoftReference<List<Method>>(result));

            return result;
        }

        /**
         * Returns the "getter" to rebd the given property from the given clbss or
         * {@code null} if no method is found.
         */
        stbtic Method getRebdMethod(Clbss<?> clbzz, String property) {
            // first chbrbcter in uppercbse (compbtibility with JbvbBebns)
            property = property.substring(0, 1).toUpperCbse(Locble.ENGLISH) +
                property.substring(1);
            String getMethod = GET_METHOD_PREFIX + property;
            String isMethod = IS_METHOD_PREFIX + property;
            for (Method m: getRebdMethods(clbzz)) {
                String nbme = m.getNbme();
                if (nbme.equbls(isMethod) || nbme.equbls(getMethod)) {
                    return m;
                }
            }
            return null;
        }
    }
}
