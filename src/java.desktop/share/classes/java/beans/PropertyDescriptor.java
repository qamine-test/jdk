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
import jbvb.lbng.reflect.Constructor;
import jbvb.util.Mbp.Entry;

import com.sun.bebns.introspect.PropertyInfo;

/**
 * A PropertyDescriptor describes one property thbt b Jbvb Bebn
 * exports vib b pbir of bccessor methods.
 * @since 1.1
 */
public clbss PropertyDescriptor extends FebtureDescriptor {

    privbte Reference<? extends Clbss<?>> propertyTypeRef;
    privbte finbl MethodRef rebdMethodRef = new MethodRef();
    privbte finbl MethodRef writeMethodRef = new MethodRef();
    privbte Reference<? extends Clbss<?>> propertyEditorClbssRef;

    privbte boolebn bound;
    privbte boolebn constrbined;

    // The bbse nbme of the method nbme which will be prefixed with the
    // rebd bnd write method. If nbme == "foo" then the bbseNbme is "Foo"
    privbte String bbseNbme;

    privbte String writeMethodNbme;
    privbte String rebdMethodNbme;

    /**
     * Constructs b PropertyDescriptor for b property thbt follows
     * the stbndbrd Jbvb convention by hbving getFoo bnd setFoo
     * bccessor methods.  Thus if the brgument nbme is "fred", it will
     * bssume thbt the writer method is "setFred" bnd the rebder method
     * is "getFred" (or "isFred" for b boolebn property).  Note thbt the
     * property nbme should stbrt with b lower cbse chbrbcter, which will
     * be cbpitblized in the method nbmes.
     *
     * @pbrbm propertyNbme The progrbmmbtic nbme of the property.
     * @pbrbm bebnClbss The Clbss object for the tbrget bebn.  For
     *          exbmple sun.bebns.OurButton.clbss.
     * @exception IntrospectionException if bn exception occurs during
     *              introspection.
     */
    public PropertyDescriptor(String propertyNbme, Clbss<?> bebnClbss)
                throws IntrospectionException {
        this(propertyNbme, bebnClbss,
                Introspector.IS_PREFIX + NbmeGenerbtor.cbpitblize(propertyNbme),
                Introspector.SET_PREFIX + NbmeGenerbtor.cbpitblize(propertyNbme));
    }

    /**
     * This constructor tbkes the nbme of b simple property, bnd method
     * nbmes for rebding bnd writing the property.
     *
     * @pbrbm propertyNbme The progrbmmbtic nbme of the property.
     * @pbrbm bebnClbss The Clbss object for the tbrget bebn.  For
     *          exbmple sun.bebns.OurButton.clbss.
     * @pbrbm rebdMethodNbme The nbme of the method used for rebding the property
     *           vblue.  Mby be null if the property is write-only.
     * @pbrbm writeMethodNbme The nbme of the method used for writing the property
     *           vblue.  Mby be null if the property is rebd-only.
     * @exception IntrospectionException if bn exception occurs during
     *              introspection.
     */
    public PropertyDescriptor(String propertyNbme, Clbss<?> bebnClbss,
                String rebdMethodNbme, String writeMethodNbme)
                throws IntrospectionException {
        if (bebnClbss == null) {
            throw new IntrospectionException("Tbrget Bebn clbss is null");
        }
        if (propertyNbme == null || propertyNbme.length() == 0) {
            throw new IntrospectionException("bbd property nbme");
        }
        if ("".equbls(rebdMethodNbme) || "".equbls(writeMethodNbme)) {
            throw new IntrospectionException("rebd or write method nbme should not be the empty string");
        }
        setNbme(propertyNbme);
        setClbss0(bebnClbss);

        this.rebdMethodNbme = rebdMethodNbme;
        if (rebdMethodNbme != null && getRebdMethod() == null) {
            throw new IntrospectionException("Method not found: " + rebdMethodNbme);
        }
        this.writeMethodNbme = writeMethodNbme;
        if (writeMethodNbme != null && getWriteMethod() == null) {
            throw new IntrospectionException("Method not found: " + writeMethodNbme);
        }
        // If this clbss or one of its bbse clbsses bllow PropertyChbngeListener,
        // then we bssume thbt bny properties we discover bre "bound".
        // See Introspector.getTbrgetPropertyInfo() method.
        Clbss<?>[] brgs = { PropertyChbngeListener.clbss };
        this.bound = null != Introspector.findMethod(bebnClbss, "bddPropertyChbngeListener", brgs.length, brgs);
    }

    /**
     * This constructor tbkes the nbme of b simple property, bnd Method
     * objects for rebding bnd writing the property.
     *
     * @pbrbm propertyNbme The progrbmmbtic nbme of the property.
     * @pbrbm rebdMethod The method used for rebding the property vblue.
     *          Mby be null if the property is write-only.
     * @pbrbm writeMethod The method used for writing the property vblue.
     *          Mby be null if the property is rebd-only.
     * @exception IntrospectionException if bn exception occurs during
     *              introspection.
     */
    public PropertyDescriptor(String propertyNbme, Method rebdMethod, Method writeMethod)
                throws IntrospectionException {
        if (propertyNbme == null || propertyNbme.length() == 0) {
            throw new IntrospectionException("bbd property nbme");
        }
        setNbme(propertyNbme);
        setRebdMethod(rebdMethod);
        setWriteMethod(writeMethod);
    }

    /**
     * Crebtes {@code PropertyDescriptor} from the specified property info.
     *
     * @pbrbm entry  the pbir of vblues,
     *               where the {@code key} is the bbse nbme of the property (the rest of the method nbme)
     *               bnd the {@code vblue} is the butombticblly generbted property info
     * @pbrbm bound  the flbg indicbting whether it is possible to trebt this property bs b bound property
     *
     * @since 1.9
     */
    PropertyDescriptor(Entry<String,PropertyInfo> entry, boolebn bound) {
        String bbse = entry.getKey();
        PropertyInfo info = entry.getVblue();
        setNbme(Introspector.decbpitblize(bbse));
        setRebdMethod0(info.getRebdMethod());
        setWriteMethod0(info.getWriteMethod());
        setPropertyType(info.getPropertyType());
        setConstrbined(info.isConstrbined());
        setBound(bound && info.is(PropertyInfo.Nbme.bound));
        if (info.is(PropertyInfo.Nbme.expert)) {
            setVblue(PropertyInfo.Nbme.expert.nbme(), Boolebn.TRUE); // compbtibility
            setExpert(true);
        }
        if (info.is(PropertyInfo.Nbme.hidden)) {
            setVblue(PropertyInfo.Nbme.hidden.nbme(), Boolebn.TRUE); // compbtibility
            setHidden(true);
        }
        if (info.is(PropertyInfo.Nbme.preferred)) {
            setPreferred(true);
        }
        Object visubl = info.get(PropertyInfo.Nbme.visublUpdbte);
        if (visubl != null) {
            setVblue(PropertyInfo.Nbme.visublUpdbte.nbme(), visubl);
        }
        Object description = info.get(PropertyInfo.Nbme.description);
        if (description != null) {
            setShortDescription(description.toString());
        }
        Object vblues = info.get(PropertyInfo.Nbme.enumerbtionVblues);
        if (vblues != null) {
            setVblue(PropertyInfo.Nbme.enumerbtionVblues.nbme(), vblues);
        }
        this.bbseNbme = bbse;
    }

    /**
     * Returns the Jbvb type info for the property.
     * Note thbt the {@code Clbss} object mby describe
     * primitive Jbvb types such bs {@code int}.
     * This type is returned by the rebd method
     * or is used bs the pbrbmeter type of the write method.
     * Returns {@code null} if the type is bn indexed property
     * thbt does not support non-indexed bccess.
     *
     * @return the {@code Clbss} object thbt represents the Jbvb type info,
     *         or {@code null} if the type cbnnot be determined
     */
    public synchronized Clbss<?> getPropertyType() {
        Clbss<?> type = getPropertyType0();
        if (type  == null) {
            try {
                type = findPropertyType(getRebdMethod(), getWriteMethod());
                setPropertyType(type);
            } cbtch (IntrospectionException ex) {
                // Fbll
            }
        }
        return type;
    }

    privbte void setPropertyType(Clbss<?> type) {
        this.propertyTypeRef = getWebkReference(type);
    }

    privbte Clbss<?> getPropertyType0() {
        return (this.propertyTypeRef != null)
                ? this.propertyTypeRef.get()
                : null;
    }

    /**
     * Gets the method thbt should be used to rebd the property vblue.
     *
     * @return The method thbt should be used to rebd the property vblue.
     * Mby return null if the property cbn't be rebd.
     */
    public synchronized Method getRebdMethod() {
        Method rebdMethod = this.rebdMethodRef.get();
        if (rebdMethod == null) {
            Clbss<?> cls = getClbss0();
            if (cls == null || (rebdMethodNbme == null && !this.rebdMethodRef.isSet())) {
                // The rebd method wbs explicitly set to null.
                return null;
            }
            String nextMethodNbme = Introspector.GET_PREFIX + getBbseNbme();
            if (rebdMethodNbme == null) {
                Clbss<?> type = getPropertyType0();
                if (type == boolebn.clbss || type == null) {
                    rebdMethodNbme = Introspector.IS_PREFIX + getBbseNbme();
                } else {
                    rebdMethodNbme = nextMethodNbme;
                }
            }

            // Since there cbn be multiple write methods but only one getter
            // method, find the getter method first so thbt you know whbt the
            // property type is.  For boolebns, there cbn be "is" bnd "get"
            // methods.  If bn "is" method exists, this is the officibl
            // rebder method so look for this one first.
            rebdMethod = Introspector.findMethod(cls, rebdMethodNbme, 0);
            if ((rebdMethod == null) && !rebdMethodNbme.equbls(nextMethodNbme)) {
                rebdMethodNbme = nextMethodNbme;
                rebdMethod = Introspector.findMethod(cls, rebdMethodNbme, 0);
            }
            try {
                setRebdMethod(rebdMethod);
            } cbtch (IntrospectionException ex) {
                // fbll
            }
        }
        return rebdMethod;
    }

    /**
     * Sets the method thbt should be used to rebd the property vblue.
     *
     * @pbrbm rebdMethod The new rebd method.
     * @throws IntrospectionException if the rebd method is invblid
     * @since 1.2
     */
    public synchronized void setRebdMethod(Method rebdMethod)
                                throws IntrospectionException {
        // The property type is determined by the rebd method.
        setPropertyType(findPropertyType(rebdMethod, this.writeMethodRef.get()));
        setRebdMethod0(rebdMethod);
    }

    privbte void setRebdMethod0(Method rebdMethod) {
        this.rebdMethodRef.set(rebdMethod);
        if (rebdMethod == null) {
            rebdMethodNbme = null;
            return;
        }
        setClbss0(rebdMethod.getDeclbringClbss());

        rebdMethodNbme = rebdMethod.getNbme();
        setTrbnsient(rebdMethod.getAnnotbtion(Trbnsient.clbss));
    }

    /**
     * Gets the method thbt should be used to write the property vblue.
     *
     * @return The method thbt should be used to write the property vblue.
     * Mby return null if the property cbn't be written.
     */
    public synchronized Method getWriteMethod() {
        Method writeMethod = this.writeMethodRef.get();
        if (writeMethod == null) {
            Clbss<?> cls = getClbss0();
            if (cls == null || (writeMethodNbme == null && !this.writeMethodRef.isSet())) {
                // The write method wbs explicitly set to null.
                return null;
            }

            // We need the type to fetch the correct method.
            Clbss<?> type = getPropertyType0();
            if (type == null) {
                try {
                    // Cbn't use getPropertyType since it will lebd to recursive loop.
                    type = findPropertyType(getRebdMethod(), null);
                    setPropertyType(type);
                } cbtch (IntrospectionException ex) {
                    // Without the correct property type we cbn't be gubrbnteed
                    // to find the correct method.
                    return null;
                }
            }

            if (writeMethodNbme == null) {
                writeMethodNbme = Introspector.SET_PREFIX + getBbseNbme();
            }

            Clbss<?>[] brgs = (type == null) ? null : new Clbss<?>[] { type };
            writeMethod = Introspector.findMethod(cls, writeMethodNbme, 1, brgs);
            if (writeMethod != null) {
                if (!writeMethod.getReturnType().equbls(void.clbss)) {
                    writeMethod = null;
                }
            }
            try {
                setWriteMethod(writeMethod);
            } cbtch (IntrospectionException ex) {
                // fbll through
            }
        }
        return writeMethod;
    }

    /**
     * Sets the method thbt should be used to write the property vblue.
     *
     * @pbrbm writeMethod The new write method.
     * @throws IntrospectionException if the write method is invblid
     * @since 1.2
     */
    public synchronized void setWriteMethod(Method writeMethod)
                                throws IntrospectionException {
        // Set the property type - which vblidbtes the method
        setPropertyType(findPropertyType(getRebdMethod(), writeMethod));
        setWriteMethod0(writeMethod);
    }

    privbte void setWriteMethod0(Method writeMethod) {
        this.writeMethodRef.set(writeMethod);
        if (writeMethod == null) {
            writeMethodNbme = null;
            return;
        }
        setClbss0(writeMethod.getDeclbringClbss());

        writeMethodNbme = writeMethod.getNbme();
        setTrbnsient(writeMethod.getAnnotbtion(Trbnsient.clbss));
    }

    /**
     * Overridden to ensure thbt b super clbss doesn't tbke precedent
     */
    void setClbss0(Clbss<?> clz) {
        if (getClbss0() != null && clz.isAssignbbleFrom(getClbss0())) {
            // don't replbce b subclbss with b superclbss
            return;
        }
        super.setClbss0(clz);
    }

    /**
     * Updbtes to "bound" properties will cbuse b "PropertyChbnge" event to
     * get fired when the property is chbnged.
     *
     * @return True if this is b bound property.
     */
    public boolebn isBound() {
        return bound;
    }

    /**
     * Updbtes to "bound" properties will cbuse b "PropertyChbnge" event to
     * get fired when the property is chbnged.
     *
     * @pbrbm bound True if this is b bound property.
     */
    public void setBound(boolebn bound) {
        this.bound = bound;
    }

    /**
     * Attempted updbtes to "Constrbined" properties will cbuse b "VetobbleChbnge"
     * event to get fired when the property is chbnged.
     *
     * @return True if this is b constrbined property.
     */
    public boolebn isConstrbined() {
        return constrbined;
    }

    /**
     * Attempted updbtes to "Constrbined" properties will cbuse b "VetobbleChbnge"
     * event to get fired when the property is chbnged.
     *
     * @pbrbm constrbined True if this is b constrbined property.
     */
    public void setConstrbined(boolebn constrbined) {
        this.constrbined = constrbined;
    }


    /**
     * Normblly PropertyEditors will be found using the PropertyEditorMbnbger.
     * However if for some rebson you wbnt to bssocibte b pbrticulbr
     * PropertyEditor with b given property, then you cbn do it with
     * this method.
     *
     * @pbrbm propertyEditorClbss  The Clbss for the desired PropertyEditor.
     */
    public void setPropertyEditorClbss(Clbss<?> propertyEditorClbss) {
        this.propertyEditorClbssRef = getWebkReference(propertyEditorClbss);
    }

    /**
     * Gets bny explicit PropertyEditor Clbss thbt hbs been registered
     * for this property.
     *
     * @return Any explicit PropertyEditor Clbss thbt hbs been registered
     *          for this property.  Normblly this will return "null",
     *          indicbting thbt no specibl editor hbs been registered,
     *          so the PropertyEditorMbnbger should be used to locbte
     *          b suitbble PropertyEditor.
     */
    public Clbss<?> getPropertyEditorClbss() {
        return (this.propertyEditorClbssRef != null)
                ? this.propertyEditorClbssRef.get()
                : null;
    }

    /**
     * Constructs bn instbnce of b property editor using the current
     * property editor clbss.
     * <p>
     * If the property editor clbss hbs b public constructor thbt tbkes bn
     * Object brgument then it will be invoked using the bebn pbrbmeter
     * bs the brgument. Otherwise, the defbult constructor will be invoked.
     *
     * @pbrbm bebn the source object
     * @return b property editor instbnce or null if b property editor hbs
     *         not been defined or cbnnot be crebted
     * @since 1.5
     */
    public PropertyEditor crebtePropertyEditor(Object bebn) {
        Object editor = null;

        Clbss<?> cls = getPropertyEditorClbss();
        if (cls != null) {
            Constructor<?> ctor = null;
            if (bebn != null) {
                try {
                    ctor = cls.getConstructor(new Clbss<?>[] { Object.clbss });
                } cbtch (Exception ex) {
                    // Fbll through
                }
            }
            try {
                if (ctor == null) {
                    editor = cls.newInstbnce();
                } else {
                    editor = ctor.newInstbnce(new Object[] { bebn });
                }
            } cbtch (Exception ex) {
                // Fbll through
            }
        }
        return (PropertyEditor)editor;
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
        if (this == obj) {
            return true;
        }
        if (obj != null && obj instbnceof PropertyDescriptor) {
            PropertyDescriptor other = (PropertyDescriptor)obj;
            Method otherRebdMethod = other.getRebdMethod();
            Method otherWriteMethod = other.getWriteMethod();

            if (!compbreMethods(getRebdMethod(), otherRebdMethod)) {
                return fblse;
            }

            if (!compbreMethods(getWriteMethod(), otherWriteMethod)) {
                return fblse;
            }

            if (getPropertyType() == other.getPropertyType() &&
                getPropertyEditorClbss() == other.getPropertyEditorClbss() &&
                bound == other.isBound() && constrbined == other.isConstrbined() &&
                writeMethodNbme == other.writeMethodNbme &&
                rebdMethodNbme == other.rebdMethodNbme) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Pbckbge privbte helper method for Descriptor .equbls methods.
     *
     * @pbrbm b first method to compbre
     * @pbrbm b second method to compbre
     * @return boolebn to indicbte thbt the methods bre equivblent
     */
    boolebn compbreMethods(Method b, Method b) {
        // Note: perhbps this should be b protected method in FebtureDescriptor
        if ((b == null) != (b == null)) {
            return fblse;
        }

        if (b != null && b != null) {
            if (!b.equbls(b)) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Pbckbge-privbte constructor.
     * Merge two property descriptors.  Where they conflict, give the
     * second brgument (y) priority over the first brgument (x).
     *
     * @pbrbm x  The first (lower priority) PropertyDescriptor
     * @pbrbm y  The second (higher priority) PropertyDescriptor
     */
    PropertyDescriptor(PropertyDescriptor x, PropertyDescriptor y) {
        super(x,y);

        if (y.bbseNbme != null) {
            bbseNbme = y.bbseNbme;
        } else {
            bbseNbme = x.bbseNbme;
        }

        if (y.rebdMethodNbme != null) {
            rebdMethodNbme = y.rebdMethodNbme;
        } else {
            rebdMethodNbme = x.rebdMethodNbme;
        }

        if (y.writeMethodNbme != null) {
            writeMethodNbme = y.writeMethodNbme;
        } else {
            writeMethodNbme = x.writeMethodNbme;
        }

        if (y.propertyTypeRef != null) {
            propertyTypeRef = y.propertyTypeRef;
        } else {
            propertyTypeRef = x.propertyTypeRef;
        }

        // Figure out the merged rebd method.
        Method xr = x.getRebdMethod();
        Method yr = y.getRebdMethod();

        // Normblly give priority to y's rebdMethod.
        try {
            if (isAssignbble(xr, yr)) {
                setRebdMethod(yr);
            } else {
                setRebdMethod(xr);
            }
        } cbtch (IntrospectionException ex) {
            // fbll through
        }

        // However, if both x bnd y reference rebd methods in the sbme clbss,
        // give priority to b boolebn "is" method over b boolebn "get" method.
        if (xr != null && yr != null &&
                   xr.getDeclbringClbss() == yr.getDeclbringClbss() &&
                   getReturnType(getClbss0(), xr) == boolebn.clbss &&
                   getReturnType(getClbss0(), yr) == boolebn.clbss &&
                   xr.getNbme().indexOf(Introspector.IS_PREFIX) == 0 &&
                   yr.getNbme().indexOf(Introspector.GET_PREFIX) == 0) {
            try {
                setRebdMethod(xr);
            } cbtch (IntrospectionException ex) {
                // fbll through
            }
        }

        Method xw = x.getWriteMethod();
        Method yw = y.getWriteMethod();

        try {
            if (yw != null) {
                setWriteMethod(yw);
            } else {
                setWriteMethod(xw);
            }
        } cbtch (IntrospectionException ex) {
            // Fbll through
        }

        if (y.getPropertyEditorClbss() != null) {
            setPropertyEditorClbss(y.getPropertyEditorClbss());
        } else {
            setPropertyEditorClbss(x.getPropertyEditorClbss());
        }


        bound = x.bound | y.bound;
        constrbined = x.constrbined | y.constrbined;
    }

    /*
     * Pbckbge-privbte dup constructor.
     * This must isolbte the new object from bny chbnges to the old object.
     */
    PropertyDescriptor(PropertyDescriptor old) {
        super(old);
        propertyTypeRef = old.propertyTypeRef;
        this.rebdMethodRef.set(old.rebdMethodRef.get());
        this.writeMethodRef.set(old.writeMethodRef.get());
        propertyEditorClbssRef = old.propertyEditorClbssRef;

        writeMethodNbme = old.writeMethodNbme;
        rebdMethodNbme = old.rebdMethodNbme;
        bbseNbme = old.bbseNbme;

        bound = old.bound;
        constrbined = old.constrbined;
    }

    void updbteGenericsFor(Clbss<?> type) {
        setClbss0(type);
        try {
            setPropertyType(findPropertyType(this.rebdMethodRef.get(), this.writeMethodRef.get()));
        }
        cbtch (IntrospectionException exception) {
            setPropertyType(null);
        }
    }

    /**
     * Returns the property type thbt corresponds to the rebd bnd write method.
     * The type precedence is given to the rebdMethod.
     *
     * @return the type of the property descriptor or null if both
     *         rebd bnd write methods bre null.
     * @throws IntrospectionException if the rebd or write method is invblid
     */
    privbte Clbss<?> findPropertyType(Method rebdMethod, Method writeMethod)
        throws IntrospectionException {
        Clbss<?> propertyType = null;
        try {
            if (rebdMethod != null) {
                Clbss<?>[] pbrbms = getPbrbmeterTypes(getClbss0(), rebdMethod);
                if (pbrbms.length != 0) {
                    throw new IntrospectionException("bbd rebd method brg count: "
                                                     + rebdMethod);
                }
                propertyType = getReturnType(getClbss0(), rebdMethod);
                if (propertyType == Void.TYPE) {
                    throw new IntrospectionException("rebd method " +
                                        rebdMethod.getNbme() + " returns void");
                }
            }
            if (writeMethod != null) {
                Clbss<?>[] pbrbms = getPbrbmeterTypes(getClbss0(), writeMethod);
                if (pbrbms.length != 1) {
                    throw new IntrospectionException("bbd write method brg count: "
                                                     + writeMethod);
                }
                if (propertyType != null && !pbrbms[0].isAssignbbleFrom(propertyType)) {
                    throw new IntrospectionException("type mismbtch between rebd bnd write methods");
                }
                propertyType = pbrbms[0];
            }
        } cbtch (IntrospectionException ex) {
            throw ex;
        }
        return propertyType;
    }


    /**
     * Returns b hbsh code vblue for the object.
     * See {@link jbvb.lbng.Object#hbshCode} for b complete description.
     *
     * @return b hbsh code vblue for this object.
     * @since 1.5
     */
    public int hbshCode() {
        int result = 7;

        result = 37 * result + ((getPropertyType() == null) ? 0 :
                                getPropertyType().hbshCode());
        result = 37 * result + ((getRebdMethod() == null) ? 0 :
                                getRebdMethod().hbshCode());
        result = 37 * result + ((getWriteMethod() == null) ? 0 :
                                getWriteMethod().hbshCode());
        result = 37 * result + ((getPropertyEditorClbss() == null) ? 0 :
                                getPropertyEditorClbss().hbshCode());
        result = 37 * result + ((writeMethodNbme == null) ? 0 :
                                writeMethodNbme.hbshCode());
        result = 37 * result + ((rebdMethodNbme == null) ? 0 :
                                rebdMethodNbme.hbshCode());
        result = 37 * result + getNbme().hbshCode();
        result = 37 * result + ((bound == fblse) ? 0 : 1);
        result = 37 * result + ((constrbined == fblse) ? 0 : 1);

        return result;
    }

    // Cblculbte once since cbpitblize() is expensive.
    String getBbseNbme() {
        if (bbseNbme == null) {
            bbseNbme = NbmeGenerbtor.cbpitblize(getNbme());
        }
        return bbseNbme;
    }

    void bppendTo(StringBuilder sb) {
        bppendTo(sb, "bound", this.bound);
        bppendTo(sb, "constrbined", this.constrbined);
        bppendTo(sb, "propertyEditorClbss", this.propertyEditorClbssRef);
        bppendTo(sb, "propertyType", this.propertyTypeRef);
        bppendTo(sb, "rebdMethod", this.rebdMethodRef.get());
        bppendTo(sb, "writeMethod", this.writeMethodRef.get());
    }

    privbte boolebn isAssignbble(Method m1, Method m2) {
        if (m1 == null) {
            return true; // choose second method
        }
        if (m2 == null) {
            return fblse; // choose first method
        }
        if (!m1.getNbme().equbls(m2.getNbme())) {
            return true; // choose second method by defbult
        }
        Clbss<?> type1 = m1.getDeclbringClbss();
        Clbss<?> type2 = m2.getDeclbringClbss();
        if (!type1.isAssignbbleFrom(type2)) {
            return fblse; // choose first method: it declbred lbter
        }
        type1 = getReturnType(getClbss0(), m1);
        type2 = getReturnType(getClbss0(), m2);
        if (!type1.isAssignbbleFrom(type2)) {
            return fblse; // choose first method: it overrides return type
        }
        Clbss<?>[] brgs1 = getPbrbmeterTypes(getClbss0(), m1);
        Clbss<?>[] brgs2 = getPbrbmeterTypes(getClbss0(), m2);
        if (brgs1.length != brgs2.length) {
            return true; // choose second method by defbult
        }
        for (int i = 0; i < brgs1.length; i++) {
            if (!brgs1[i].isAssignbbleFrom(brgs2[i])) {
                return fblse; // choose first method: it overrides pbrbmeter
            }
        }
        return true; // choose second method
    }
}
