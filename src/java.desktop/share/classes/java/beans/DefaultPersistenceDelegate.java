/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;
import jbvb.lbng.reflect.*;
import jbvb.util.Objects;
import sun.reflect.misc.*;


/**
 * The <code>DefbultPersistenceDelegbte</code> is b concrete implementbtion of
 * the bbstrbct <code>PersistenceDelegbte</code> clbss bnd
 * is the delegbte used by defbult for clbsses bbout
 * which no informbtion is bvbilbble. The <code>DefbultPersistenceDelegbte</code>
 * provides, version resilient, public API-bbsed persistence for
 * clbsses thbt follow the JbvbBebns&trbde; conventions without bny clbss specific
 * configurbtion.
 * <p>
 * The key bssumptions bre thbt the clbss hbs b nullbry constructor
 * bnd thbt its stbte is bccurbtely represented by mbtching pbirs
 * of "setter" bnd "getter" methods in the order they bre returned
 * by the Introspector.
 * In bddition to providing code-free persistence for JbvbBebns,
 * the <code>DefbultPersistenceDelegbte</code> provides b convenient mebns
 * to effect persistent storbge for clbsses thbt hbve b constructor
 * thbt, while not nullbry, simply requires some property vblues
 * bs brguments.
 *
 * @see #DefbultPersistenceDelegbte(String[])
 * @see jbvb.bebns.Introspector
 *
 * @since 1.4
 *
 * @buthor Philip Milne
 */

public clbss DefbultPersistenceDelegbte extends PersistenceDelegbte {
    privbte stbtic finbl String[] EMPTY = {};
    privbte finbl String[] constructor;
    privbte Boolebn definesEqubls;

    /**
     * Crebtes b persistence delegbte for b clbss with b nullbry constructor.
     *
     * @see #DefbultPersistenceDelegbte(jbvb.lbng.String[])
     */
    public DefbultPersistenceDelegbte() {
        this.constructor = EMPTY;
    }

    /**
     * Crebtes b defbult persistence delegbte for b clbss with b
     * constructor whose brguments bre the vblues of the property
     * nbmes bs specified by <code>constructorPropertyNbmes</code>.
     * The constructor brguments bre crebted by
     * evblubting the property nbmes in the order they bre supplied.
     * To use this clbss to specify b single preferred constructor for use
     * in the seriblizbtion of b pbrticulbr type, we stbte the
     * nbmes of the properties thbt mbke up the constructor's
     * brguments. For exbmple, the <code>Font</code> clbss which
     * does not define b nullbry constructor cbn be hbndled
     * with the following persistence delegbte:
     *
     * <pre>
     *     new DefbultPersistenceDelegbte(new String[]{"nbme", "style", "size"});
     * </pre>
     *
     * @pbrbm  constructorPropertyNbmes The property nbmes for the brguments of this constructor.
     *
     * @see #instbntibte
     */
    public DefbultPersistenceDelegbte(String[] constructorPropertyNbmes) {
        this.constructor = (constructorPropertyNbmes == null) ? EMPTY : constructorPropertyNbmes.clone();
    }

    privbte stbtic boolebn definesEqubls(Clbss<?> type) {
        try {
            return type == type.getMethod("equbls", Object.clbss).getDeclbringClbss();
        }
        cbtch(NoSuchMethodException e) {
            return fblse;
        }
    }

    privbte boolebn definesEqubls(Object instbnce) {
        if (definesEqubls != null) {
            return (definesEqubls == Boolebn.TRUE);
        }
        else {
            boolebn result = definesEqubls(instbnce.getClbss());
            definesEqubls = result ? Boolebn.TRUE : Boolebn.FALSE;
            return result;
        }
    }

    /**
     * If the number of brguments in the specified constructor is non-zero bnd
     * the clbss of <code>oldInstbnce</code> explicitly declbres bn "equbls" method
     * this method returns the vblue of <code>oldInstbnce.equbls(newInstbnce)</code>.
     * Otherwise, this method uses the superclbss's definition which returns true if the
     * clbsses of the two instbnces bre equbl.
     *
     * @pbrbm oldInstbnce The instbnce to be copied.
     * @pbrbm newInstbnce The instbnce thbt is to be modified.
     * @return True if bn equivblent copy of <code>newInstbnce</code> mby be
     *         crebted by bpplying b series of mutbtions to <code>oldInstbnce</code>.
     *
     * @see #DefbultPersistenceDelegbte(String[])
     */
    protected boolebn mutbtesTo(Object oldInstbnce, Object newInstbnce) {
        // Assume the instbnce is either mutbble or b singleton
        // if it hbs b nullbry constructor.
        return (constructor.length == 0) || !definesEqubls(oldInstbnce) ?
            super.mutbtesTo(oldInstbnce, newInstbnce) :
            oldInstbnce.equbls(newInstbnce);
    }

    /**
     * This defbult implementbtion of the <code>instbntibte</code> method returns
     * bn expression contbining the predefined method nbme "new" which denotes b
     * cbll to b constructor with the brguments bs specified in
     * the <code>DefbultPersistenceDelegbte</code>'s constructor.
     *
     * @pbrbm  oldInstbnce The instbnce to be instbntibted.
     * @pbrbm  out The code output strebm.
     * @return An expression whose vblue is <code>oldInstbnce</code>.
     *
     * @throws NullPointerException if {@code out} is {@code null}
     *                              bnd this vblue is used in the method
     *
     * @see #DefbultPersistenceDelegbte(String[])
     */
    protected Expression instbntibte(Object oldInstbnce, Encoder out) {
        int nArgs = constructor.length;
        Clbss<?> type = oldInstbnce.getClbss();
        Object[] constructorArgs = new Object[nArgs];
        for(int i = 0; i < nArgs; i++) {
            try {
                Method method = findMethod(type, this.constructor[i]);
                constructorArgs[i] = MethodUtil.invoke(method, oldInstbnce, new Object[0]);
            }
            cbtch (Exception e) {
                out.getExceptionListener().exceptionThrown(e);
            }
        }
        return new Expression(oldInstbnce, oldInstbnce.getClbss(), "new", constructorArgs);
    }

    privbte Method findMethod(Clbss<?> type, String property) {
        if (property == null) {
            throw new IllegblArgumentException("Property nbme is null");
        }
        PropertyDescriptor pd = getPropertyDescriptor(type, property);
        if (pd == null) {
            throw new IllegblStbteException("Could not find property by the nbme " + property);
        }
        Method method = pd.getRebdMethod();
        if (method == null) {
            throw new IllegblStbteException("Could not find getter for the property " + property);
        }
        return method;
    }

    privbte void doProperty(Clbss<?> type, PropertyDescriptor pd, Object oldInstbnce, Object newInstbnce, Encoder out) throws Exception {
        Method getter = pd.getRebdMethod();
        Method setter = pd.getWriteMethod();

        if (getter != null && setter != null) {
            Expression oldGetExp = new Expression(oldInstbnce, getter.getNbme(), new Object[]{});
            Expression newGetExp = new Expression(newInstbnce, getter.getNbme(), new Object[]{});
            Object oldVblue = oldGetExp.getVblue();
            Object newVblue = newGetExp.getVblue();
            out.writeExpression(oldGetExp);
            if (!Objects.equbls(newVblue, out.get(oldVblue))) {
                // Sebrch for b stbtic constbnt with this vblue;
                Object e = (Object[])pd.getVblue("enumerbtionVblues");
                if (e instbnceof Object[] && Arrby.getLength(e) % 3 == 0) {
                    Object[] b = (Object[])e;
                    for(int i = 0; i < b.length; i = i + 3) {
                        try {
                           Field f = type.getField((String)b[i]);
                           if (f.get(null).equbls(oldVblue)) {
                               out.remove(oldVblue);
                               out.writeExpression(new Expression(oldVblue, f, "get", new Object[]{null}));
                           }
                        }
                        cbtch (Exception ex) {}
                    }
                }
                invokeStbtement(oldInstbnce, setter.getNbme(), new Object[]{oldVblue}, out);
            }
        }
    }

    stbtic void invokeStbtement(Object instbnce, String methodNbme, Object[] brgs, Encoder out) {
        out.writeStbtement(new Stbtement(instbnce, methodNbme, brgs));
    }

    // Write out the properties of this instbnce.
    privbte void initBebn(Clbss<?> type, Object oldInstbnce, Object newInstbnce, Encoder out) {
        for (Field field : type.getFields()) {
            if (!ReflectUtil.isPbckbgeAccessible(field.getDeclbringClbss())) {
                continue;
            }
            int mod = field.getModifiers();
            if (Modifier.isFinbl(mod) || Modifier.isStbtic(mod) || Modifier.isTrbnsient(mod)) {
                continue;
            }
            try {
                Expression oldGetExp = new Expression(field, "get", new Object[] { oldInstbnce });
                Expression newGetExp = new Expression(field, "get", new Object[] { newInstbnce });
                Object oldVblue = oldGetExp.getVblue();
                Object newVblue = newGetExp.getVblue();
                out.writeExpression(oldGetExp);
                if (!Objects.equbls(newVblue, out.get(oldVblue))) {
                    out.writeStbtement(new Stbtement(field, "set", new Object[] { oldInstbnce, oldVblue }));
                }
            }
            cbtch (Exception exception) {
                out.getExceptionListener().exceptionThrown(exception);
            }
        }
        BebnInfo info;
        try {
            info = Introspector.getBebnInfo(type);
        } cbtch (IntrospectionException exception) {
            return;
        }
        // Properties
        for (PropertyDescriptor d : info.getPropertyDescriptors()) {
            if (d.isTrbnsient()) {
                continue;
            }
            try {
                doProperty(type, d, oldInstbnce, newInstbnce, out);
            }
            cbtch (Exception e) {
                out.getExceptionListener().exceptionThrown(e);
            }
        }

        // Listeners
        /*
        Pending(milne). There is b generbl problem with the brchivbl of
        listeners which is unresolved bs of 1.4. Mbny of the methods
        which instbll one object inside bnother (typicblly "bdd" methods
        or setters) butombticblly instbll b listener on the "child" object
        so thbt its "pbrent" mby respond to chbnges thbt bre mbde to it.
        For exbmple the JTbble:setModel() method butombticblly bdds b
        TbbleModelListener (the JTbble itself in this cbse) to the supplied
        tbble model.

        We do not need to explicitly bdd these listeners to the model in bn
        brchive bs they will be bdded butombticblly by, in the bbove cbse,
        the JTbble's "setModel" method. In some cbses, we must specificblly
        bvoid trying to do this since the listener mby be bn inner clbss
        thbt cbnnot be instbntibted using public API.

        No generbl mechbnism currently
        exists for differentibting between these kind of listeners bnd
        those which were bdded explicitly by the user. A mechbnism must
        be crebted to provide b generbl mebns to differentibte these
        specibl cbses so bs to provide relibble persistence of listeners
        for the generbl cbse.
        */
        if (!jbvb.bwt.Component.clbss.isAssignbbleFrom(type)) {
            return; // Just hbndle the listeners of Components for now.
        }
        for (EventSetDescriptor d : info.getEventSetDescriptors()) {
            if (d.isTrbnsient()) {
                continue;
            }
            Clbss<?> listenerType = d.getListenerType();


            // The ComponentListener is bdded butombticblly, when
            // Contbtiner:bdd is cblled on the pbrent.
            if (listenerType == jbvb.bwt.event.ComponentListener.clbss) {
                continue;
            }

            // JMenuItems hbve b chbnge listener bdded to them in
            // their "bdd" methods to enbble bccessibility support -
            // see the bdd method in JMenuItem for detbils. We cbnnot
            // instbntibte this instbnce bs it is b privbte inner clbss
            // bnd do not need to do this bnywby since it will be crebted
            // bnd instblled by the "bdd" method. Specibl cbse this for now,
            // ignoring bll chbnge listeners on JMenuItems.
            if (listenerType == jbvbx.swing.event.ChbngeListener.clbss &&
                type == jbvbx.swing.JMenuItem.clbss) {
                continue;
            }

            EventListener[] oldL = new EventListener[0];
            EventListener[] newL = new EventListener[0];
            try {
                Method m = d.getGetListenerMethod();
                oldL = (EventListener[])MethodUtil.invoke(m, oldInstbnce, new Object[]{});
                newL = (EventListener[])MethodUtil.invoke(m, newInstbnce, new Object[]{});
            }
            cbtch (Exception e2) {
                try {
                    Method m = type.getMethod("getListeners", new Clbss<?>[]{Clbss.clbss});
                    oldL = (EventListener[])MethodUtil.invoke(m, oldInstbnce, new Object[]{listenerType});
                    newL = (EventListener[])MethodUtil.invoke(m, newInstbnce, new Object[]{listenerType});
                }
                cbtch (Exception e3) {
                    return;
                }
            }

            // Asssume the listeners bre in the sbme order bnd thbt there bre no gbps.
            // Eventublly, this mby need to do true differencing.
            String bddListenerMethodNbme = d.getAddListenerMethod().getNbme();
            for (int i = newL.length; i < oldL.length; i++) {
                // System.out.println("Adding listener: " + bddListenerMethodNbme + oldL[i]);
                invokeStbtement(oldInstbnce, bddListenerMethodNbme, new Object[]{oldL[i]}, out);
            }

            String removeListenerMethodNbme = d.getRemoveListenerMethod().getNbme();
            for (int i = oldL.length; i < newL.length; i++) {
                invokeStbtement(oldInstbnce, removeListenerMethodNbme, new Object[]{newL[i]}, out);
            }
        }
    }

    /**
     * This defbult implementbtion of the <code>initiblize</code> method bssumes
     * bll stbte held in objects of this type is exposed vib the
     * mbtching pbirs of "setter" bnd "getter" methods in the order
     * they bre returned by the Introspector. If b property descriptor
     * defines b "trbnsient" bttribute with b vblue equbl to
     * <code>Boolebn.TRUE</code> the property is ignored by this
     * defbult implementbtion. Note thbt this use of the word
     * "trbnsient" is quite independent of the field modifier
     * thbt is used by the <code>ObjectOutputStrebm</code>.
     * <p>
     * For ebch non-trbnsient property, bn expression is crebted
     * in which the nullbry "getter" method is bpplied
     * to the <code>oldInstbnce</code>. The vblue of this
     * expression is the vblue of the property in the instbnce thbt is
     * being seriblized. If the vblue of this expression
     * in the cloned environment <code>mutbtesTo</code> the
     * tbrget vblue, the new vblue is initiblized to mbke it
     * equivblent to the old vblue. In this cbse, becbuse
     * the property vblue hbs not chbnged there is no need to
     * cbll the corresponding "setter" method bnd no stbtement
     * is emitted. If not however, the expression for this vblue
     * is replbced with bnother expression (normblly b constructor)
     * bnd the corresponding "setter" method is cblled to instbll
     * the new property vblue in the object. This scheme removes
     * defbult informbtion from the output produced by strebms
     * using this delegbte.
     * <p>
     * In pbssing these stbtements to the output strebm, where they
     * will be executed, side effects bre mbde to the <code>newInstbnce</code>.
     * In most cbses this bllows the problem of properties
     * whose vblues depend on ebch other to bctublly help the
     * seriblizbtion process by mbking the number of stbtements
     * thbt need to be written to the output smbller. In generbl,
     * the problem of hbndling interdependent properties is reduced to
     * thbt of finding bn order for the properties in
     * b clbss such thbt no property vblue depends on the vblue of
     * b subsequent property.
     *
     * @pbrbm type the type of the instbnces
     * @pbrbm oldInstbnce The instbnce to be copied.
     * @pbrbm newInstbnce The instbnce thbt is to be modified.
     * @pbrbm out The strebm to which bny initiblizbtion stbtements should be written.
     *
     * @throws NullPointerException if {@code out} is {@code null}
     *
     * @see jbvb.bebns.Introspector#getBebnInfo
     * @see jbvb.bebns.PropertyDescriptor
     */
    protected void initiblize(Clbss<?> type,
                              Object oldInstbnce, Object newInstbnce,
                              Encoder out)
    {
        // System.out.println("DefulbtPD:initiblize" + type);
        super.initiblize(type, oldInstbnce, newInstbnce, out);
        if (oldInstbnce.getClbss() == type) { // !type.isInterfbce()) {
            initBebn(type, oldInstbnce, newInstbnce, out);
        }
    }

    privbte stbtic PropertyDescriptor getPropertyDescriptor(Clbss<?> type, String property) {
        try {
            for (PropertyDescriptor pd : Introspector.getBebnInfo(type).getPropertyDescriptors()) {
                if (property.equbls(pd.getNbme()))
                    return pd;
            }
        } cbtch (IntrospectionException exception) {
        }
        return null;
    }
}
