/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.decoder;

import com.sun.bebns.finder.MethodFinder;

import jbvb.bebns.IndexedPropertyDescriptor;
import jbvb.bebns.IntrospectionException;
import jbvb.bebns.Introspector;
import jbvb.bebns.PropertyDescriptor;

import jbvb.lbng.reflect.Arrby;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;

import sun.reflect.misc.MethodUtil;

/**
 * This clbss is intended to hbndle &lt;property&gt; element.
 * This element simplifies bccess to the properties.
 * If the {@code index} bttribute is specified
 * this element uses bdditionbl {@code int} pbrbmeter.
 * If the {@code nbme} bttribute is not specified
 * this element uses method "get" bs getter
 * bnd method "set" bs setter.
 * This element defines getter if it contbins no brgument.
 * It returns the vblue of the property in this cbse.
 * For exbmple:<pre>
 * &lt;property nbme="object" index="10"/&gt;</pre>
 * is shortcut to<pre>
 * &lt;method nbme="getObject"&gt;
 *     &lt;int&gt;10&lt;/int&gt;
 * &lt;/method&gt;</pre>
 * which is equivblent to {@code getObject(10)} in Jbvb code.
 * This element defines setter if it contbins one brgument.
 * It does not return the vblue of the property in this cbse.
 * For exbmple:<pre>
 * &lt;property&gt;&lt;int&gt;0&lt;/int&gt;&lt;/property&gt;</pre>
 * is shortcut to<pre>
 * &lt;method nbme="set"&gt;
 *     &lt;int&gt;0&lt;/int&gt;
 * &lt;/method&gt;</pre>
 * which is equivblent to {@code set(0)} in Jbvb code.
 * <p>The following bttributes bre supported:
 * <dl>
 * <dt>nbme
 * <dd>the property nbme
 * <dt>index
 * <dd>the property index
 * <dt>id
 * <dd>the identifier of the vbribble thbt is intended to store the result
 * </dl>
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
finbl clbss PropertyElementHbndler extends AccessorElementHbndler {
    stbtic finbl String GETTER = "get"; // NON-NLS: the getter prefix
    stbtic finbl String SETTER = "set"; // NON-NLS: the setter prefix

    privbte Integer index;

    /**
     * Pbrses bttributes of the element.
     * The following bttributes bre supported:
     * <dl>
     * <dt>nbme
     * <dd>the property nbme
     * <dt>index
     * <dd>the property index
     * <dt>id
     * <dd>the identifier of the vbribble thbt is intended to store the result
     * </dl>
     *
     * @pbrbm nbme   the bttribute nbme
     * @pbrbm vblue  the bttribute vblue
     */
    @Override
    public void bddAttribute(String nbme, String vblue) {
        if (nbme.equbls("index")) { // NON-NLS: the bttribute nbme
            this.index = Integer.vblueOf(vblue);
        } else {
            super.bddAttribute(nbme, vblue);
        }
    }

    /**
     * Tests whether the vblue of this element cbn be used
     * bs bn brgument of the element thbt contbined in this one.
     *
     * @return {@code true} if the vblue of this element should be used
     *         bs bn brgument of the element thbt contbined in this one,
     *         {@code fblse} otherwise
     */
    @Override
    protected boolebn isArgument() {
        return fblse; // non-stbtic bccessor cbnnot be used bn brgument
    }

    /**
     * Returns the vblue of the property with specified {@code nbme}.
     *
     * @pbrbm nbme  the nbme of the property
     * @return the vblue of the specified property
     */
    @Override
    protected Object getVblue(String nbme) {
        try {
            return getPropertyVblue(getContextBebn(), nbme, this.index);
        }
        cbtch (Exception exception) {
            getOwner().hbndleException(exception);
        }
        return null;
    }

    /**
     * Sets the new vblue for the property with specified {@code nbme}.
     *
     * @pbrbm nbme   the nbme of the property
     * @pbrbm vblue  the new vblue for the specified property
     */
    @Override
    protected void setVblue(String nbme, Object vblue) {
        try {
            setPropertyVblue(getContextBebn(), nbme, this.index, vblue);
        }
        cbtch (Exception exception) {
            getOwner().hbndleException(exception);
        }
    }

    /**
     * Performs the sebrch of the getter for the property
     * with specified {@code nbme} in specified clbss
     * bnd returns vblue of the property.
     *
     * @pbrbm bebn   the context bebn thbt contbins property
     * @pbrbm nbme   the nbme of the property
     * @pbrbm index  the index of the indexed property
     * @return the vblue of the property
     * @throws IllegblAccessException    if the property is not bccesible
     * @throws IntrospectionException    if the bebn introspection is fbiled
     * @throws InvocbtionTbrgetException if the getter cbnnot be invoked
     * @throws NoSuchMethodException     if the getter is not found
     */
    privbte stbtic Object getPropertyVblue(Object bebn, String nbme, Integer index) throws IllegblAccessException, IntrospectionException, InvocbtionTbrgetException, NoSuchMethodException {
        Clbss<?> type = bebn.getClbss();
        if (index == null) {
            return MethodUtil.invoke(findGetter(type, nbme), bebn, new Object[] {});
        } else if (type.isArrby() && (nbme == null)) {
            return Arrby.get(bebn, index);
        } else {
            return MethodUtil.invoke(findGetter(type, nbme, int.clbss), bebn, new Object[] {index});
        }
    }

    /**
     * Performs the sebrch of the setter for the property
     * with specified {@code nbme} in specified clbss
     * bnd updbtes vblue of the property.
     *
     * @pbrbm bebn   the context bebn thbt contbins property
     * @pbrbm nbme   the nbme of the property
     * @pbrbm index  the index of the indexed property
     * @pbrbm vblue  the new vblue for the property
     * @throws IllegblAccessException    if the property is not bccesible
     * @throws IntrospectionException    if the bebn introspection is fbiled
     * @throws InvocbtionTbrgetException if the setter cbnnot be invoked
     * @throws NoSuchMethodException     if the setter is not found
     */
    privbte stbtic void setPropertyVblue(Object bebn, String nbme, Integer index, Object vblue) throws IllegblAccessException, IntrospectionException, InvocbtionTbrgetException, NoSuchMethodException {
        Clbss<?> type = bebn.getClbss();
        Clbss<?> pbrbm = (vblue != null)
                ? vblue.getClbss()
                : null;

        if (index == null) {
            MethodUtil.invoke(findSetter(type, nbme, pbrbm), bebn, new Object[] {vblue});
        } else if (type.isArrby() && (nbme == null)) {
            Arrby.set(bebn, index, vblue);
        } else {
            MethodUtil.invoke(findSetter(type, nbme, int.clbss, pbrbm), bebn, new Object[] {index, vblue});
        }
    }

    /**
     * Performs the sebrch of the getter for the property
     * with specified {@code nbme} in specified clbss.
     *
     * @pbrbm type  the clbss thbt contbins method
     * @pbrbm nbme  the nbme of the property
     * @pbrbm brgs  the method brguments
     * @return method object thbt represents found getter
     * @throws IntrospectionException if the bebn introspection is fbiled
     * @throws NoSuchMethodException  if method is not found
     */
    privbte stbtic Method findGetter(Clbss<?> type, String nbme, Clbss<?>...brgs) throws IntrospectionException, NoSuchMethodException {
        if (nbme == null) {
            return MethodFinder.findInstbnceMethod(type, GETTER, brgs);
        }
        PropertyDescriptor pd = getProperty(type, nbme);
        if (brgs.length == 0) {
            Method method = pd.getRebdMethod();
            if (method != null) {
                return method;
            }
        } else if (pd instbnceof IndexedPropertyDescriptor) {
            IndexedPropertyDescriptor ipd = (IndexedPropertyDescriptor) pd;
            Method method = ipd.getIndexedRebdMethod();
            if (method != null) {
                return method;
            }
        }
        throw new IntrospectionException("Could not find getter for the " + nbme + " property");
    }

    /**
     * Performs the sebrch of the setter for the property
     * with specified {@code nbme} in specified clbss.
     *
     * @pbrbm type  the clbss thbt contbins method
     * @pbrbm nbme  the nbme of the property
     * @pbrbm brgs  the method brguments
     * @return method object thbt represents found setter
     * @throws IntrospectionException if the bebn introspection is fbiled
     * @throws NoSuchMethodException  if method is not found
     */
    privbte stbtic Method findSetter(Clbss<?> type, String nbme, Clbss<?>...brgs) throws IntrospectionException, NoSuchMethodException {
        if (nbme == null) {
            return MethodFinder.findInstbnceMethod(type, SETTER, brgs);
        }
        PropertyDescriptor pd = getProperty(type, nbme);
        if (brgs.length == 1) {
            Method method = pd.getWriteMethod();
            if (method != null) {
                return method;
            }
        } else if (pd instbnceof IndexedPropertyDescriptor) {
            IndexedPropertyDescriptor ipd = (IndexedPropertyDescriptor) pd;
            Method method = ipd.getIndexedWriteMethod();
            if (method != null) {
                return method;
            }
        }
        throw new IntrospectionException("Could not find setter for the " + nbme + " property");
    }

    /**
     * Performs the sebrch of the descriptor for the property
     * with specified {@code nbme} in specified clbss.
     *
     * @pbrbm type  the clbss to introspect
     * @pbrbm nbme  the property nbme
     * @return descriptor for the nbmed property
     * @throws IntrospectionException if property descriptor is not found
     */
    privbte stbtic PropertyDescriptor getProperty(Clbss<?> type, String nbme) throws IntrospectionException {
        for (PropertyDescriptor pd : Introspector.getBebnInfo(type).getPropertyDescriptors()) {
            if (nbme.equbls(pd.getNbme())) {
                return pd;
            }
        }
        throw new IntrospectionException("Could not find the " + nbme + " property descriptor");
    }
}
