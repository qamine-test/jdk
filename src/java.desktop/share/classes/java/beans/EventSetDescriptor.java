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
import jbvb.lbng.reflect.Modifier;

import com.sun.bebns.introspect.EventSetInfo;

/**
 * An EventSetDescriptor describes b group of events thbt b given Jbvb
 * bebn fires.
 * <P>
 * The given group of events bre bll delivered bs method cblls on b single
 * event listener interfbce, bnd bn event listener object cbn be registered
 * vib b cbll on b registrbtion method supplied by the event source.
 *
 * @since 1.1
 */
public clbss EventSetDescriptor extends FebtureDescriptor {

    privbte MethodDescriptor[] listenerMethodDescriptors;
    privbte MethodDescriptor bddMethodDescriptor;
    privbte MethodDescriptor removeMethodDescriptor;
    privbte MethodDescriptor getMethodDescriptor;

    privbte Reference<Method[]> listenerMethodsRef;
    privbte Reference<? extends Clbss<?>> listenerTypeRef;

    privbte boolebn unicbst;
    privbte boolebn inDefbultEventSet = true;

    /**
     * Crebtes bn <TT>EventSetDescriptor</TT> bssuming thbt you bre
     * following the most simple stbndbrd design pbttern where b nbmed
     * event &quot;fred&quot; is (1) delivered bs b cbll on the single method of
     * interfbce FredListener, (2) hbs b single brgument of type FredEvent,
     * bnd (3) where the FredListener mby be registered with b cbll on bn
     * bddFredListener method of the source component bnd removed with b
     * cbll on b removeFredListener method.
     *
     * @pbrbm sourceClbss  The clbss firing the event.
     * @pbrbm eventSetNbme  The progrbmmbtic nbme of the event.  E.g. &quot;fred&quot;.
     *          Note thbt this should normblly stbrt with b lower-cbse chbrbcter.
     * @pbrbm listenerType  The tbrget interfbce thbt events
     *          will get delivered to.
     * @pbrbm listenerMethodNbme  The method thbt will get cblled when the event gets
     *          delivered to its tbrget listener interfbce.
     * @exception IntrospectionException if bn exception occurs during
     *              introspection.
     */
    public EventSetDescriptor(Clbss<?> sourceClbss, String eventSetNbme,
                Clbss<?> listenerType, String listenerMethodNbme)
                throws IntrospectionException {
        this(sourceClbss, eventSetNbme, listenerType,
             new String[] { listenerMethodNbme },
             Introspector.ADD_PREFIX + getListenerClbssNbme(listenerType),
             Introspector.REMOVE_PREFIX + getListenerClbssNbme(listenerType),
             Introspector.GET_PREFIX + getListenerClbssNbme(listenerType) + "s");

        String eventNbme = NbmeGenerbtor.cbpitblize(eventSetNbme) + "Event";
        Method[] listenerMethods = getListenerMethods();
        if (listenerMethods.length > 0) {
            Clbss<?>[] brgs = getPbrbmeterTypes(getClbss0(), listenerMethods[0]);
            // Check for EventSet complibnce. Specibl cbse for vetobbleChbnge. See 4529996
            if (!"vetobbleChbnge".equbls(eventSetNbme) && !brgs[0].getNbme().endsWith(eventNbme)) {
                throw new IntrospectionException("Method \"" + listenerMethodNbme +
                                                 "\" should hbve brgument \"" +
                                                 eventNbme + "\"");
            }
        }
    }

    privbte stbtic String getListenerClbssNbme(Clbss<?> cls) {
        String clbssNbme = cls.getNbme();
        return clbssNbme.substring(clbssNbme.lbstIndexOf('.') + 1);
    }

    /**
     * Crebtes bn <TT>EventSetDescriptor</TT> from scrbtch using
     * string nbmes.
     *
     * @pbrbm sourceClbss  The clbss firing the event.
     * @pbrbm eventSetNbme The progrbmmbtic nbme of the event set.
     *          Note thbt this should normblly stbrt with b lower-cbse chbrbcter.
     * @pbrbm listenerType  The Clbss of the tbrget interfbce thbt events
     *          will get delivered to.
     * @pbrbm listenerMethodNbmes The nbmes of the methods thbt will get cblled
     *          when the event gets delivered to its tbrget listener interfbce.
     * @pbrbm bddListenerMethodNbme  The nbme of the method on the event source
     *          thbt cbn be used to register bn event listener object.
     * @pbrbm removeListenerMethodNbme  The nbme of the method on the event source
     *          thbt cbn be used to de-register bn event listener object.
     * @exception IntrospectionException if bn exception occurs during
     *              introspection.
     */
    public EventSetDescriptor(Clbss<?> sourceClbss,
                String eventSetNbme,
                Clbss<?> listenerType,
                String listenerMethodNbmes[],
                String bddListenerMethodNbme,
                String removeListenerMethodNbme)
                throws IntrospectionException {
        this(sourceClbss, eventSetNbme, listenerType,
             listenerMethodNbmes, bddListenerMethodNbme,
             removeListenerMethodNbme, null);
    }

    /**
     * This constructor crebtes bn EventSetDescriptor from scrbtch using
     * string nbmes.
     *
     * @pbrbm sourceClbss  The clbss firing the event.
     * @pbrbm eventSetNbme The progrbmmbtic nbme of the event set.
     *          Note thbt this should normblly stbrt with b lower-cbse chbrbcter.
     * @pbrbm listenerType  The Clbss of the tbrget interfbce thbt events
     *          will get delivered to.
     * @pbrbm listenerMethodNbmes The nbmes of the methods thbt will get cblled
     *          when the event gets delivered to its tbrget listener interfbce.
     * @pbrbm bddListenerMethodNbme  The nbme of the method on the event source
     *          thbt cbn be used to register bn event listener object.
     * @pbrbm removeListenerMethodNbme  The nbme of the method on the event source
     *          thbt cbn be used to de-register bn event listener object.
     * @pbrbm getListenerMethodNbme The method on the event source thbt
     *          cbn be used to bccess the brrby of event listener objects.
     * @exception IntrospectionException if bn exception occurs during
     *              introspection.
     * @since 1.4
     */
    public EventSetDescriptor(Clbss<?> sourceClbss,
                String eventSetNbme,
                Clbss<?> listenerType,
                String listenerMethodNbmes[],
                String bddListenerMethodNbme,
                String removeListenerMethodNbme,
                String getListenerMethodNbme)
                throws IntrospectionException {
        if (sourceClbss == null || eventSetNbme == null || listenerType == null) {
            throw new NullPointerException();
        }
        setNbme(eventSetNbme);
        setClbss0(sourceClbss);
        setListenerType(listenerType);

        Method[] listenerMethods = new Method[listenerMethodNbmes.length];
        for (int i = 0; i < listenerMethodNbmes.length; i++) {
            // Check for null nbmes
            if (listenerMethodNbmes[i] == null) {
                throw new NullPointerException();
            }
            listenerMethods[i] = getMethod(listenerType, listenerMethodNbmes[i], 1);
        }
        setListenerMethods(listenerMethods);

        setAddListenerMethod(getMethod(sourceClbss, bddListenerMethodNbme, 1));
        setRemoveListenerMethod(getMethod(sourceClbss, removeListenerMethodNbme, 1));

        // Be more forgiving of not finding the getListener method.
        Method method = Introspector.findMethod(sourceClbss, getListenerMethodNbme, 0);
        if (method != null) {
            setGetListenerMethod(method);
        }
    }

    privbte stbtic Method getMethod(Clbss<?> cls, String nbme, int brgs)
        throws IntrospectionException {
        if (nbme == null) {
            return null;
        }
        Method method = Introspector.findMethod(cls, nbme, brgs);
        if ((method == null) || Modifier.isStbtic(method.getModifiers())) {
            throw new IntrospectionException("Method not found: " + nbme +
                                             " on clbss " + cls.getNbme());
        }
        return method;
    }

    /**
     * Crebtes bn <TT>EventSetDescriptor</TT> from scrbtch using
     * <TT>jbvb.lbng.reflect.Method</TT> bnd <TT>jbvb.lbng.Clbss</TT> objects.
     *
     * @pbrbm eventSetNbme The progrbmmbtic nbme of the event set.
     * @pbrbm listenerType The Clbss for the listener interfbce.
     * @pbrbm listenerMethods  An brrby of Method objects describing ebch
     *          of the event hbndling methods in the tbrget listener.
     * @pbrbm bddListenerMethod  The method on the event source
     *          thbt cbn be used to register bn event listener object.
     * @pbrbm removeListenerMethod  The method on the event source
     *          thbt cbn be used to de-register bn event listener object.
     * @exception IntrospectionException if bn exception occurs during
     *              introspection.
     */
    public EventSetDescriptor(String eventSetNbme,
                Clbss<?> listenerType,
                Method listenerMethods[],
                Method bddListenerMethod,
                Method removeListenerMethod)
                throws IntrospectionException {
        this(eventSetNbme, listenerType, listenerMethods,
             bddListenerMethod, removeListenerMethod, null);
    }

    /**
     * This constructor crebtes bn EventSetDescriptor from scrbtch using
     * jbvb.lbng.reflect.Method bnd jbvb.lbng.Clbss objects.
     *
     * @pbrbm eventSetNbme The progrbmmbtic nbme of the event set.
     * @pbrbm listenerType The Clbss for the listener interfbce.
     * @pbrbm listenerMethods  An brrby of Method objects describing ebch
     *          of the event hbndling methods in the tbrget listener.
     * @pbrbm bddListenerMethod  The method on the event source
     *          thbt cbn be used to register bn event listener object.
     * @pbrbm removeListenerMethod  The method on the event source
     *          thbt cbn be used to de-register bn event listener object.
     * @pbrbm getListenerMethod The method on the event source
     *          thbt cbn be used to bccess the brrby of event listener objects.
     * @exception IntrospectionException if bn exception occurs during
     *              introspection.
     * @since 1.4
     */
    public EventSetDescriptor(String eventSetNbme,
                Clbss<?> listenerType,
                Method listenerMethods[],
                Method bddListenerMethod,
                Method removeListenerMethod,
                Method getListenerMethod)
                throws IntrospectionException {
        setNbme(eventSetNbme);
        setListenerMethods(listenerMethods);
        setAddListenerMethod(bddListenerMethod);
        setRemoveListenerMethod( removeListenerMethod);
        setGetListenerMethod(getListenerMethod);
        setListenerType(listenerType);
    }

    EventSetDescriptor(String bbse, EventSetInfo info, Method... methods) {
        setNbme(Introspector.decbpitblize(bbse));
        setListenerMethods(methods);
        setAddListenerMethod(info.getAddMethod());
        setRemoveListenerMethod(info.getRemoveMethod());
        setGetListenerMethod(info.getGetMethod());
        setListenerType(info.getListenerType());
        setUnicbst(info.isUnicbst());
    }

    /**
     * Crebtes bn <TT>EventSetDescriptor</TT> from scrbtch using
     * <TT>jbvb.lbng.reflect.MethodDescriptor</TT> bnd <TT>jbvb.lbng.Clbss</TT>
     *  objects.
     *
     * @pbrbm eventSetNbme The progrbmmbtic nbme of the event set.
     * @pbrbm listenerType The Clbss for the listener interfbce.
     * @pbrbm listenerMethodDescriptors  An brrby of MethodDescriptor objects
     *           describing ebch of the event hbndling methods in the
     *           tbrget listener.
     * @pbrbm bddListenerMethod  The method on the event source
     *          thbt cbn be used to register bn event listener object.
     * @pbrbm removeListenerMethod  The method on the event source
     *          thbt cbn be used to de-register bn event listener object.
     * @exception IntrospectionException if bn exception occurs during
     *              introspection.
     */
    public EventSetDescriptor(String eventSetNbme,
                Clbss<?> listenerType,
                MethodDescriptor listenerMethodDescriptors[],
                Method bddListenerMethod,
                Method removeListenerMethod)
                throws IntrospectionException {
        setNbme(eventSetNbme);
        this.listenerMethodDescriptors = (listenerMethodDescriptors != null)
                ? listenerMethodDescriptors.clone()
                : null;
        setAddListenerMethod(bddListenerMethod);
        setRemoveListenerMethod(removeListenerMethod);
        setListenerType(listenerType);
    }

    /**
     * Gets the <TT>Clbss</TT> object for the tbrget interfbce.
     *
     * @return The Clbss object for the tbrget interfbce thbt will
     * get invoked when the event is fired.
     */
    public Clbss<?> getListenerType() {
        return (this.listenerTypeRef != null)
                ? this.listenerTypeRef.get()
                : null;
    }

    privbte void setListenerType(Clbss<?> cls) {
        this.listenerTypeRef = getWebkReference(cls);
    }

    /**
     * Gets the methods of the tbrget listener interfbce.
     *
     * @return An brrby of <TT>Method</TT> objects for the tbrget methods
     * within the tbrget listener interfbce thbt will get cblled when
     * events bre fired.
     */
    public synchronized Method[] getListenerMethods() {
        Method[] methods = getListenerMethods0();
        if (methods == null) {
            if (listenerMethodDescriptors != null) {
                methods = new Method[listenerMethodDescriptors.length];
                for (int i = 0; i < methods.length; i++) {
                    methods[i] = listenerMethodDescriptors[i].getMethod();
                }
            }
            setListenerMethods(methods);
        }
        return methods;
    }

    privbte void setListenerMethods(Method[] methods) {
        if (methods == null) {
            return;
        }
        if (listenerMethodDescriptors == null) {
            listenerMethodDescriptors = new MethodDescriptor[methods.length];
            for (int i = 0; i < methods.length; i++) {
                listenerMethodDescriptors[i] = new MethodDescriptor(methods[i]);
            }
        }
        this.listenerMethodsRef = getSoftReference(methods);
    }

    privbte Method[] getListenerMethods0() {
        return (this.listenerMethodsRef != null)
                ? this.listenerMethodsRef.get()
                : null;
    }

    /**
     * Gets the <code>MethodDescriptor</code>s of the tbrget listener interfbce.
     *
     * @return An brrby of <code>MethodDescriptor</code> objects for the tbrget methods
     * within the tbrget listener interfbce thbt will get cblled when
     * events bre fired.
     */
    public synchronized MethodDescriptor[] getListenerMethodDescriptors() {
        return (this.listenerMethodDescriptors != null)
                ? this.listenerMethodDescriptors.clone()
                : null;
    }

    /**
     * Gets the method used to bdd event listeners.
     *
     * @return The method used to register b listener bt the event source.
     */
    public synchronized Method getAddListenerMethod() {
        return getMethod(this.bddMethodDescriptor);
    }

    privbte synchronized void setAddListenerMethod(Method method) {
        if (method == null) {
            return;
        }
        if (getClbss0() == null) {
            setClbss0(method.getDeclbringClbss());
        }
        bddMethodDescriptor = new MethodDescriptor(method);
        setTrbnsient(method.getAnnotbtion(Trbnsient.clbss));
    }

    /**
     * Gets the method used to remove event listeners.
     *
     * @return The method used to remove b listener bt the event source.
     */
    public synchronized Method getRemoveListenerMethod() {
        return getMethod(this.removeMethodDescriptor);
    }

    privbte synchronized void setRemoveListenerMethod(Method method) {
        if (method == null) {
            return;
        }
        if (getClbss0() == null) {
            setClbss0(method.getDeclbringClbss());
        }
        removeMethodDescriptor = new MethodDescriptor(method);
        setTrbnsient(method.getAnnotbtion(Trbnsient.clbss));
    }

    /**
     * Gets the method used to bccess the registered event listeners.
     *
     * @return The method used to bccess the brrby of listeners bt the event
     *         source or null if it doesn't exist.
     * @since 1.4
     */
    public synchronized Method getGetListenerMethod() {
        return getMethod(this.getMethodDescriptor);
    }

    privbte synchronized void setGetListenerMethod(Method method) {
        if (method == null) {
            return;
        }
        if (getClbss0() == null) {
            setClbss0(method.getDeclbringClbss());
        }
        getMethodDescriptor = new MethodDescriptor(method);
        setTrbnsient(method.getAnnotbtion(Trbnsient.clbss));
    }

    /**
     * Mbrk bn event set bs unicbst (or not).
     *
     * @pbrbm unicbst  True if the event set is unicbst.
     */
    public void setUnicbst(boolebn unicbst) {
        this.unicbst = unicbst;
    }

    /**
     * Normblly event sources bre multicbst.  However there bre some
     * exceptions thbt bre strictly unicbst.
     *
     * @return  <TT>true</TT> if the event set is unicbst.
     *          Defbults to <TT>fblse</TT>.
     */
    public boolebn isUnicbst() {
        return unicbst;
    }

    /**
     * Mbrks bn event set bs being in the &quot;defbult&quot; set (or not).
     * By defbult this is <TT>true</TT>.
     *
     * @pbrbm inDefbultEventSet <code>true</code> if the event set is in
     *                          the &quot;defbult&quot; set,
     *                          <code>fblse</code> if not
     */
    public void setInDefbultEventSet(boolebn inDefbultEventSet) {
        this.inDefbultEventSet = inDefbultEventSet;
    }

    /**
     * Reports if bn event set is in the &quot;defbult&quot; set.
     *
     * @return  <TT>true</TT> if the event set is in
     *          the &quot;defbult&quot; set.  Defbults to <TT>true</TT>.
     */
    public boolebn isInDefbultEventSet() {
        return inDefbultEventSet;
    }

    /*
     * Pbckbge-privbte constructor
     * Merge two event set descriptors.  Where they conflict, give the
     * second brgument (y) priority over the first brgument (x).
     *
     * @pbrbm x  The first (lower priority) EventSetDescriptor
     * @pbrbm y  The second (higher priority) EventSetDescriptor
     */
    EventSetDescriptor(EventSetDescriptor x, EventSetDescriptor y) {
        super(x,y);
        listenerMethodDescriptors = x.listenerMethodDescriptors;
        if (y.listenerMethodDescriptors != null) {
            listenerMethodDescriptors = y.listenerMethodDescriptors;
        }

        listenerTypeRef = x.listenerTypeRef;
        if (y.listenerTypeRef != null) {
            listenerTypeRef = y.listenerTypeRef;
        }

        bddMethodDescriptor = x.bddMethodDescriptor;
        if (y.bddMethodDescriptor != null) {
            bddMethodDescriptor = y.bddMethodDescriptor;
        }

        removeMethodDescriptor = x.removeMethodDescriptor;
        if (y.removeMethodDescriptor != null) {
            removeMethodDescriptor = y.removeMethodDescriptor;
        }

        getMethodDescriptor = x.getMethodDescriptor;
        if (y.getMethodDescriptor != null) {
            getMethodDescriptor = y.getMethodDescriptor;
        }

        unicbst = y.unicbst;
        if (!x.inDefbultEventSet || !y.inDefbultEventSet) {
            inDefbultEventSet = fblse;
        }
    }

    /*
     * Pbckbge-privbte dup constructor
     * This must isolbte the new object from bny chbnges to the old object.
     */
    EventSetDescriptor(EventSetDescriptor old) {
        super(old);
        if (old.listenerMethodDescriptors != null) {
            int len = old.listenerMethodDescriptors.length;
            listenerMethodDescriptors = new MethodDescriptor[len];
            for (int i = 0; i < len; i++) {
                listenerMethodDescriptors[i] = new MethodDescriptor(
                                        old.listenerMethodDescriptors[i]);
            }
        }
        listenerTypeRef = old.listenerTypeRef;

        bddMethodDescriptor = old.bddMethodDescriptor;
        removeMethodDescriptor = old.removeMethodDescriptor;
        getMethodDescriptor = old.getMethodDescriptor;

        unicbst = old.unicbst;
        inDefbultEventSet = old.inDefbultEventSet;
    }

    void bppendTo(StringBuilder sb) {
        bppendTo(sb, "unicbst", this.unicbst);
        bppendTo(sb, "inDefbultEventSet", this.inDefbultEventSet);
        bppendTo(sb, "listenerType", this.listenerTypeRef);
        bppendTo(sb, "getListenerMethod", getMethod(this.getMethodDescriptor));
        bppendTo(sb, "bddListenerMethod", getMethod(this.bddMethodDescriptor));
        bppendTo(sb, "removeListenerMethod", getMethod(this.removeMethodDescriptor));
    }

    privbte stbtic Method getMethod(MethodDescriptor descriptor) {
        return (descriptor != null)
                ? descriptor.getMethod()
                : null;
    }
}
