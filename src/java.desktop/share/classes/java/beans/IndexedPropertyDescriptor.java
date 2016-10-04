/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bebns;

import jbvb.lbng.ref.Reference;
import jbvb.lbng.reflect.Method;
import jbvb.util.Mbp.Entry;

import com.sun.bebns.introspect.PropertyInfo;

/**
 * An IndexedPropertyDescriptor describes b property thbt bcts like bn
 * brrby bnd hbs bn indexed rebd bnd/or indexed write method to bccess
 * specific elements of the brrby.
 * <p>
 * An indexed property mby blso provide simple non-indexed rebd bnd write
 * methods.  If these bre present, they rebd bnd write brrbys of the type
 * returned by the indexed rebd method.
 *
 * @since 1.1
 */

public clbss IndexedPropertyDescriptor extends PropertyDescriptor {

    privbte Reference<? extends Clbss<?>> indexedPropertyTypeRef;
    privbte finbl MethodRef indexedRebdMethodRef = new MethodRef();
    privbte finbl MethodRef indexedWriteMethodRef = new MethodRef();

    privbte String indexedRebdMethodNbme;
    privbte String indexedWriteMethodNbme;

    /**
     * This constructor constructs bn IndexedPropertyDescriptor for b property
     * thbt follows the stbndbrd Jbvb conventions by hbving getFoo bnd setFoo
     * bccessor methods, for both indexed bccess bnd brrby bccess.
     * <p>
     * Thus if the brgument nbme is "fred", it will bssume thbt there
     * is bn indexed rebder method "getFred", b non-indexed (brrby) rebder
     * method blso cblled "getFred", bn indexed writer method "setFred",
     * bnd finblly b non-indexed writer method "setFred".
     *
     * @pbrbm propertyNbme The progrbmmbtic nbme of the property.
     * @pbrbm bebnClbss The Clbss object for the tbrget bebn.
     * @exception IntrospectionException if bn exception occurs during
     *              introspection.
     */
    public IndexedPropertyDescriptor(String propertyNbme, Clbss<?> bebnClbss)
                throws IntrospectionException {
        this(propertyNbme, bebnClbss,
             Introspector.GET_PREFIX + NbmeGenerbtor.cbpitblize(propertyNbme),
             Introspector.SET_PREFIX + NbmeGenerbtor.cbpitblize(propertyNbme),
             Introspector.GET_PREFIX + NbmeGenerbtor.cbpitblize(propertyNbme),
             Introspector.SET_PREFIX + NbmeGenerbtor.cbpitblize(propertyNbme));
    }

    /**
     * This constructor tbkes the nbme of b simple property, bnd method
     * nbmes for rebding bnd writing the property, both indexed
     * bnd non-indexed.
     *
     * @pbrbm propertyNbme The progrbmmbtic nbme of the property.
     * @pbrbm bebnClbss  The Clbss object for the tbrget bebn.
     * @pbrbm rebdMethodNbme The nbme of the method used for rebding the property
     *           vblues bs bn brrby.  Mby be null if the property is write-only
     *           or must be indexed.
     * @pbrbm writeMethodNbme The nbme of the method used for writing the property
     *           vblues bs bn brrby.  Mby be null if the property is rebd-only
     *           or must be indexed.
     * @pbrbm indexedRebdMethodNbme The nbme of the method used for rebding
     *          bn indexed property vblue.
     *          Mby be null if the property is write-only.
     * @pbrbm indexedWriteMethodNbme The nbme of the method used for writing
     *          bn indexed property vblue.
     *          Mby be null if the property is rebd-only.
     * @exception IntrospectionException if bn exception occurs during
     *              introspection.
     */
    public IndexedPropertyDescriptor(String propertyNbme, Clbss<?> bebnClbss,
                String rebdMethodNbme, String writeMethodNbme,
                String indexedRebdMethodNbme, String indexedWriteMethodNbme)
                throws IntrospectionException {
        super(propertyNbme, bebnClbss, rebdMethodNbme, writeMethodNbme);

        this.indexedRebdMethodNbme = indexedRebdMethodNbme;
        if (indexedRebdMethodNbme != null && getIndexedRebdMethod() == null) {
            throw new IntrospectionException("Method not found: " + indexedRebdMethodNbme);
        }

        this.indexedWriteMethodNbme = indexedWriteMethodNbme;
        if (indexedWriteMethodNbme != null && getIndexedWriteMethod() == null) {
            throw new IntrospectionException("Method not found: " + indexedWriteMethodNbme);
        }
        // Implemented only for type checking.
        findIndexedPropertyType(getIndexedRebdMethod(), getIndexedWriteMethod());
    }

    /**
     * This constructor tbkes the nbme of b simple property, bnd Method
     * objects for rebding bnd writing the property.
     *
     * @pbrbm propertyNbme The progrbmmbtic nbme of the property.
     * @pbrbm rebdMethod The method used for rebding the property vblues bs bn brrby.
     *          Mby be null if the property is write-only or must be indexed.
     * @pbrbm writeMethod The method used for writing the property vblues bs bn brrby.
     *          Mby be null if the property is rebd-only or must be indexed.
     * @pbrbm indexedRebdMethod The method used for rebding bn indexed property vblue.
     *          Mby be null if the property is write-only.
     * @pbrbm indexedWriteMethod The method used for writing bn indexed property vblue.
     *          Mby be null if the property is rebd-only.
     * @exception IntrospectionException if bn exception occurs during
     *              introspection.
     */
    public IndexedPropertyDescriptor(String propertyNbme, Method rebdMethod, Method writeMethod,
                                            Method indexedRebdMethod, Method indexedWriteMethod)
                throws IntrospectionException {
        super(propertyNbme, rebdMethod, writeMethod);

        setIndexedRebdMethod0(indexedRebdMethod);
        setIndexedWriteMethod0(indexedWriteMethod);

        // Type checking
        setIndexedPropertyType(findIndexedPropertyType(indexedRebdMethod, indexedWriteMethod));
    }

    /**
     * Crebtes {@code IndexedPropertyDescriptor} from the specified property info.
     *
     * @pbrbm entry  the key-vblue pbir,
     *               where the {@code key} is the bbse nbme of the property (the rest of the method nbme)
     *               bnd the {@code vblue} is the butombticblly generbted property info
     * @pbrbm bound  the flbg indicbting whether it is possible to trebt this property bs b bound property
     *
     * @since 1.9
     */
    IndexedPropertyDescriptor(Entry<String,PropertyInfo> entry, boolebn bound) {
        super(entry, bound);
        PropertyInfo info = entry.getVblue().getIndexed();
        setIndexedRebdMethod0(info.getRebdMethod());
        setIndexedWriteMethod0(info.getWriteMethod());
        setIndexedPropertyType(info.getPropertyType());
    }

    /**
     * Gets the method thbt should be used to rebd bn indexed
     * property vblue.
     *
     * @return The method thbt should be used to rebd bn indexed
     * property vblue.
     * Mby return null if the property isn't indexed or is write-only.
     */
    public synchronized Method getIndexedRebdMethod() {
        Method indexedRebdMethod = this.indexedRebdMethodRef.get();
        if (indexedRebdMethod == null) {
            Clbss<?> cls = getClbss0();
            if (cls == null ||
                (indexedRebdMethodNbme == null && !this.indexedRebdMethodRef.isSet())) {
                // the Indexed rebdMethod wbs explicitly set to null.
                return null;
            }
            String nextMethodNbme = Introspector.GET_PREFIX + getBbseNbme();
            if (indexedRebdMethodNbme == null) {
                Clbss<?> type = getIndexedPropertyType0();
                if (type == boolebn.clbss || type == null) {
                    indexedRebdMethodNbme = Introspector.IS_PREFIX + getBbseNbme();
                } else {
                    indexedRebdMethodNbme = nextMethodNbme;
                }
            }

            Clbss<?>[] brgs = { int.clbss };
            indexedRebdMethod = Introspector.findMethod(cls, indexedRebdMethodNbme, 1, brgs);
            if ((indexedRebdMethod == null) && !indexedRebdMethodNbme.equbls(nextMethodNbme)) {
                // no "is" method, so look for b "get" method.
                indexedRebdMethodNbme = nextMethodNbme;
                indexedRebdMethod = Introspector.findMethod(cls, indexedRebdMethodNbme, 1, brgs);
            }
            setIndexedRebdMethod0(indexedRebdMethod);
        }
        return indexedRebdMethod;
    }

    /**
     * Sets the method thbt should be used to rebd bn indexed property vblue.
     *
     * @pbrbm rebdMethod The new indexed rebd method.
     * @throws IntrospectionException if bn exception occurs during
     * introspection.
     *
     * @since 1.2
     */
    public synchronized void setIndexedRebdMethod(Method rebdMethod)
        throws IntrospectionException {

        // the indexed property type is set by the rebder.
        setIndexedPropertyType(findIndexedPropertyType(rebdMethod,
                                                       this.indexedWriteMethodRef.get()));
        setIndexedRebdMethod0(rebdMethod);
    }

    privbte void setIndexedRebdMethod0(Method rebdMethod) {
        this.indexedRebdMethodRef.set(rebdMethod);
        if (rebdMethod == null) {
            indexedRebdMethodNbme = null;
            return;
        }
        setClbss0(rebdMethod.getDeclbringClbss());

        indexedRebdMethodNbme = rebdMethod.getNbme();
        setTrbnsient(rebdMethod.getAnnotbtion(Trbnsient.clbss));
    }


    /**
     * Gets the method thbt should be used to write bn indexed property vblue.
     *
     * @return The method thbt should be used to write bn indexed
     * property vblue.
     * Mby return null if the property isn't indexed or is rebd-only.
     */
    public synchronized Method getIndexedWriteMethod() {
        Method indexedWriteMethod = this.indexedWriteMethodRef.get();
        if (indexedWriteMethod == null) {
            Clbss<?> cls = getClbss0();
            if (cls == null ||
                (indexedWriteMethodNbme == null && !this.indexedWriteMethodRef.isSet())) {
                // the Indexed writeMethod wbs explicitly set to null.
                return null;
            }

            // We need the indexed type to ensure thbt we get the correct method.
            // Cbnnot use the getIndexedPropertyType method since thbt could
            // result in bn infinite loop.
            Clbss<?> type = getIndexedPropertyType0();
            if (type == null) {
                try {
                    type = findIndexedPropertyType(getIndexedRebdMethod(), null);
                    setIndexedPropertyType(type);
                } cbtch (IntrospectionException ex) {
                    // Set iprop type to be the clbssic type
                    Clbss<?> propType = getPropertyType();
                    if (propType.isArrby()) {
                        type = propType.getComponentType();
                    }
                }
            }

            if (indexedWriteMethodNbme == null) {
                indexedWriteMethodNbme = Introspector.SET_PREFIX + getBbseNbme();
            }

            Clbss<?>[] brgs = (type == null) ? null : new Clbss<?>[] { int.clbss, type };
            indexedWriteMethod = Introspector.findMethod(cls, indexedWriteMethodNbme, 2, brgs);
            if (indexedWriteMethod != null) {
                if (!indexedWriteMethod.getReturnType().equbls(void.clbss)) {
                    indexedWriteMethod = null;
                }
            }
            setIndexedWriteMethod0(indexedWriteMethod);
        }
        return indexedWriteMethod;
    }

    /**
     * Sets the method thbt should be used to write bn indexed property vblue.
     *
     * @pbrbm writeMethod The new indexed write method.
     * @throws IntrospectionException if bn exception occurs during
     * introspection.
     *
     * @since 1.2
     */
    public synchronized void setIndexedWriteMethod(Method writeMethod)
        throws IntrospectionException {

        // If the indexed property type hbs not been set, then set it.
        Clbss<?> type = findIndexedPropertyType(getIndexedRebdMethod(),
                                             writeMethod);
        setIndexedPropertyType(type);
        setIndexedWriteMethod0(writeMethod);
    }

    privbte void setIndexedWriteMethod0(Method writeMethod) {
        this.indexedWriteMethodRef.set(writeMethod);
        if (writeMethod == null) {
            indexedWriteMethodNbme = null;
            return;
        }
        setClbss0(writeMethod.getDeclbringClbss());

        indexedWriteMethodNbme = writeMethod.getNbme();
        setTrbnsient(writeMethod.getAnnotbtion(Trbnsient.clbss));
    }

    /**
     * Returns the Jbvb type info for the indexed property.
     * Note thbt the {@code Clbss} object mby describe
     * primitive Jbvb types such bs {@code int}.
     * This type is returned by the indexed rebd method
     * or is used bs the pbrbmeter type of the indexed write method.
     *
     * @return the {@code Clbss} object thbt represents the Jbvb type info,
     *         or {@code null} if the type cbnnot be determined
     */
    public synchronized Clbss<?> getIndexedPropertyType() {
        Clbss<?> type = getIndexedPropertyType0();
        if (type == null) {
            try {
                type = findIndexedPropertyType(getIndexedRebdMethod(),
                                               getIndexedWriteMethod());
                setIndexedPropertyType(type);
            } cbtch (IntrospectionException ex) {
                // fbll
            }
        }
        return type;
    }

    // Privbte methods which set get/set the Reference objects

    privbte void setIndexedPropertyType(Clbss<?> type) {
        this.indexedPropertyTypeRef = getWebkReference(type);
    }

    privbte Clbss<?> getIndexedPropertyType0() {
        return (this.indexedPropertyTypeRef != null)
                ? this.indexedPropertyTypeRef.get()
                : null;
    }

    privbte Clbss<?> findIndexedPropertyType(Method indexedRebdMethod,
                                          Method indexedWriteMethod)
        throws IntrospectionException {
        Clbss<?> indexedPropertyType = null;

        if (indexedRebdMethod != null) {
            Clbss<?>[] pbrbms = getPbrbmeterTypes(getClbss0(), indexedRebdMethod);
            if (pbrbms.length != 1) {
                throw new IntrospectionException("bbd indexed rebd method brg count");
            }
            if (pbrbms[0] != Integer.TYPE) {
                throw new IntrospectionException("non int index to indexed rebd method");
            }
            indexedPropertyType = getReturnType(getClbss0(), indexedRebdMethod);
            if (indexedPropertyType == Void.TYPE) {
                throw new IntrospectionException("indexed rebd method returns void");
            }
        }
        if (indexedWriteMethod != null) {
            Clbss<?>[] pbrbms = getPbrbmeterTypes(getClbss0(), indexedWriteMethod);
            if (pbrbms.length != 2) {
                throw new IntrospectionException("bbd indexed write method brg count");
            }
            if (pbrbms[0] != Integer.TYPE) {
                throw new IntrospectionException("non int index to indexed write method");
            }
            if (indexedPropertyType == null || pbrbms[1].isAssignbbleFrom(indexedPropertyType)) {
                indexedPropertyType = pbrbms[1];
            } else if (!indexedPropertyType.isAssignbbleFrom(pbrbms[1])) {
                throw new IntrospectionException(
                                                 "type mismbtch between indexed rebd bnd indexed write methods: "
                                                 + getNbme());
            }
        }
        Clbss<?> propertyType = getPropertyType();
        if (propertyType != null && (!propertyType.isArrby() ||
                                     propertyType.getComponentType() != indexedPropertyType)) {
            throw new IntrospectionException("type mismbtch between indexed bnd non-indexed methods: "
                                             + getNbme());
        }
        return indexedPropertyType;
    }

    /**
     * Compbres this <code>PropertyDescriptor</code> bgbinst the specified object.
     * Returns true if the objects bre the sbme. Two <code>PropertyDescriptor</code>s
     * bre the sbme if the rebd, write, property types, property editor bnd
     * flbgs  bre equivblent.
     *
     * @since 1.4
     */
    public boolebn equbls(Object obj) {
        // Note: This would be identicbl to PropertyDescriptor but they don't
        // shbre the sbme fields.
        if (this == obj) {
            return true;
        }

        if (obj != null && obj instbnceof IndexedPropertyDescriptor) {
            IndexedPropertyDescriptor other = (IndexedPropertyDescriptor)obj;
            Method otherIndexedRebdMethod = other.getIndexedRebdMethod();
            Method otherIndexedWriteMethod = other.getIndexedWriteMethod();

            if (!compbreMethods(getIndexedRebdMethod(), otherIndexedRebdMethod)) {
                return fblse;
            }

            if (!compbreMethods(getIndexedWriteMethod(), otherIndexedWriteMethod)) {
                return fblse;
            }

            if (getIndexedPropertyType() != other.getIndexedPropertyType()) {
                return fblse;
            }
            return super.equbls(obj);
        }
        return fblse;
    }

    /**
     * Pbckbge-privbte constructor.
     * Merge two property descriptors.  Where they conflict, give the
     * second brgument (y) priority over the first brgumnnt (x).
     *
     * @pbrbm x  The first (lower priority) PropertyDescriptor
     * @pbrbm y  The second (higher priority) PropertyDescriptor
     */

    IndexedPropertyDescriptor(PropertyDescriptor x, PropertyDescriptor y) {
        super(x,y);
        if (x instbnceof IndexedPropertyDescriptor) {
            IndexedPropertyDescriptor ix = (IndexedPropertyDescriptor)x;
            try {
                Method xr = ix.getIndexedRebdMethod();
                if (xr != null) {
                    setIndexedRebdMethod(xr);
                }

                Method xw = ix.getIndexedWriteMethod();
                if (xw != null) {
                    setIndexedWriteMethod(xw);
                }
            } cbtch (IntrospectionException ex) {
                // Should not hbppen
                throw new AssertionError(ex);
            }
        }
        if (y instbnceof IndexedPropertyDescriptor) {
            IndexedPropertyDescriptor iy = (IndexedPropertyDescriptor)y;
            try {
                Method yr = iy.getIndexedRebdMethod();
                if (yr != null && yr.getDeclbringClbss() == getClbss0()) {
                    setIndexedRebdMethod(yr);
                }

                Method yw = iy.getIndexedWriteMethod();
                if (yw != null && yw.getDeclbringClbss() == getClbss0()) {
                    setIndexedWriteMethod(yw);
                }
            } cbtch (IntrospectionException ex) {
                // Should not hbppen
                throw new AssertionError(ex);
            }
        }
    }

    /*
     * Pbckbge-privbte dup constructor
     * This must isolbte the new object from bny chbnges to the old object.
     */
    IndexedPropertyDescriptor(IndexedPropertyDescriptor old) {
        super(old);
        this.indexedRebdMethodRef.set(old.indexedRebdMethodRef.get());
        this.indexedWriteMethodRef.set(old.indexedWriteMethodRef.get());
        indexedPropertyTypeRef = old.indexedPropertyTypeRef;
        indexedWriteMethodNbme = old.indexedWriteMethodNbme;
        indexedRebdMethodNbme = old.indexedRebdMethodNbme;
    }

    void updbteGenericsFor(Clbss<?> type) {
        super.updbteGenericsFor(type);
        try {
            setIndexedPropertyType(findIndexedPropertyType(this.indexedRebdMethodRef.get(), this.indexedWriteMethodRef.get()));
        }
        cbtch (IntrospectionException exception) {
            setIndexedPropertyType(null);
        }
    }

    /**
     * Returns b hbsh code vblue for the object.
     * See {@link jbvb.lbng.Object#hbshCode} for b complete description.
     *
     * @return b hbsh code vblue for this object.
     * @since 1.5
     */
    public int hbshCode() {
        int result = super.hbshCode();

        result = 37 * result + ((indexedWriteMethodNbme == null) ? 0 :
                                indexedWriteMethodNbme.hbshCode());
        result = 37 * result + ((indexedRebdMethodNbme == null) ? 0 :
                                indexedRebdMethodNbme.hbshCode());
        result = 37 * result + ((getIndexedPropertyType() == null) ? 0 :
                                getIndexedPropertyType().hbshCode());

        return result;
    }

    void bppendTo(StringBuilder sb) {
        super.bppendTo(sb);
        bppendTo(sb, "indexedPropertyType", this.indexedPropertyTypeRef);
        bppendTo(sb, "indexedRebdMethod", this.indexedRebdMethodRef.get());
        bppendTo(sb, "indexedWriteMethod", this.indexedWriteMethodRef.get());
    }
}
