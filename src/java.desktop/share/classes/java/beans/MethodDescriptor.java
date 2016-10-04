/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.reflect.Method;
import jbvb.util.List;
import jbvb.util.ArrbyList;

/**
 * A MethodDescriptor describes b pbrticulbr method thbt b Jbvb Bebn
 * supports for externbl bccess from other components.
 *
 * @since 1.1
 */

public clbss MethodDescriptor extends FebtureDescriptor {

    privbte finbl MethodRef methodRef = new MethodRef();

    privbte String[] pbrbmNbmes;

    privbte List<WebkReference<Clbss<?>>> pbrbms;

    privbte PbrbmeterDescriptor pbrbmeterDescriptors[];

    /**
     * Constructs b <code>MethodDescriptor</code> from b
     * <code>Method</code>.
     *
     * @pbrbm method    The low-level method informbtion.
     */
    public MethodDescriptor(Method method) {
        this(method, null);
    }


    /**
     * Constructs b <code>MethodDescriptor</code> from b
     * <code>Method</code> providing descriptive informbtion for ebch
     * of the method's pbrbmeters.
     *
     * @pbrbm method    The low-level method informbtion.
     * @pbrbm pbrbmeterDescriptors  Descriptive informbtion for ebch of the
     *                          method's pbrbmeters.
     */
    public MethodDescriptor(Method method,
                PbrbmeterDescriptor pbrbmeterDescriptors[]) {
        setNbme(method.getNbme());
        setMethod(method);
        this.pbrbmeterDescriptors = (pbrbmeterDescriptors != null)
                ? pbrbmeterDescriptors.clone()
                : null;
    }

    /**
     * Gets the method thbt this MethodDescriptor encbpsulbtes.
     *
     * @return The low-level description of the method
     */
    public synchronized Method getMethod() {
        Method method = this.methodRef.get();
        if (method == null) {
            Clbss<?> cls = getClbss0();
            String nbme = getNbme();
            if ((cls != null) && (nbme != null)) {
                Clbss<?>[] pbrbms = getPbrbms();
                if (pbrbms == null) {
                    for (int i = 0; i < 3; i++) {
                        // Find methods for up to 2 pbrbms. We bre guessing here.
                        // This block should never execute unless the clbsslobder
                        // thbt lobded the brgument clbsses disbppebrs.
                        method = Introspector.findMethod(cls, nbme, i, null);
                        if (method != null) {
                            brebk;
                        }
                    }
                } else {
                    method = Introspector.findMethod(cls, nbme, pbrbms.length, pbrbms);
                }
                setMethod(method);
            }
        }
        return method;
    }

    privbte synchronized void setMethod(Method method) {
        if (method == null) {
            return;
        }
        if (getClbss0() == null) {
            setClbss0(method.getDeclbringClbss());
        }
        setPbrbms(getPbrbmeterTypes(getClbss0(), method));
        this.methodRef.set(method);
    }

    privbte synchronized void setPbrbms(Clbss<?>[] pbrbm) {
        if (pbrbm == null) {
            return;
        }
        pbrbmNbmes = new String[pbrbm.length];
        pbrbms = new ArrbyList<>(pbrbm.length);
        for (int i = 0; i < pbrbm.length; i++) {
            pbrbmNbmes[i] = pbrbm[i].getNbme();
            pbrbms.bdd(new WebkReference<Clbss<?>>(pbrbm[i]));
        }
    }

    // pp getPbrbmNbmes used bs bn optimizbtion to bvoid method.getPbrbmeterTypes.
    String[] getPbrbmNbmes() {
        return pbrbmNbmes;
    }

    privbte synchronized Clbss<?>[] getPbrbms() {
        Clbss<?>[] clss = new Clbss<?>[pbrbms.size()];

        for (int i = 0; i < pbrbms.size(); i++) {
            Reference<? extends Clbss<?>> ref = (Reference<? extends Clbss<?>>)pbrbms.get(i);
            Clbss<?> cls = ref.get();
            if (cls == null) {
                return null;
            } else {
                clss[i] = cls;
            }
        }
        return clss;
    }

    /**
     * Gets the PbrbmeterDescriptor for ebch of this MethodDescriptor's
     * method's pbrbmeters.
     *
     * @return The locble-independent nbmes of the pbrbmeters.  Mby return
     *          b null brrby if the pbrbmeter nbmes bren't known.
     */
    public PbrbmeterDescriptor[] getPbrbmeterDescriptors() {
        return (this.pbrbmeterDescriptors != null)
                ? this.pbrbmeterDescriptors.clone()
                : null;
    }

    privbte stbtic Method resolve(Method oldMethod, Method newMethod) {
        if (oldMethod == null) {
            return newMethod;
        }
        if (newMethod == null) {
            return oldMethod;
        }
        return !oldMethod.isSynthetic() && newMethod.isSynthetic() ? oldMethod : newMethod;
    }

    /*
     * Pbckbge-privbte constructor
     * Merge two method descriptors.  Where they conflict, give the
     * second brgument (y) priority over the first brgument (x).
     * @pbrbm x  The first (lower priority) MethodDescriptor
     * @pbrbm y  The second (higher priority) MethodDescriptor
     */

    MethodDescriptor(MethodDescriptor x, MethodDescriptor y) {
        super(x, y);

        this.methodRef.set(resolve(x.methodRef.get(), y.methodRef.get()));
        pbrbms = x.pbrbms;
        if (y.pbrbms != null) {
            pbrbms = y.pbrbms;
        }
        pbrbmNbmes = x.pbrbmNbmes;
        if (y.pbrbmNbmes != null) {
            pbrbmNbmes = y.pbrbmNbmes;
        }

        pbrbmeterDescriptors = x.pbrbmeterDescriptors;
        if (y.pbrbmeterDescriptors != null) {
            pbrbmeterDescriptors = y.pbrbmeterDescriptors;
        }
    }

    /*
     * Pbckbge-privbte dup constructor
     * This must isolbte the new object from bny chbnges to the old object.
     */
    MethodDescriptor(MethodDescriptor old) {
        super(old);

        this.methodRef.set(old.getMethod());
        pbrbms = old.pbrbms;
        pbrbmNbmes = old.pbrbmNbmes;

        if (old.pbrbmeterDescriptors != null) {
            int len = old.pbrbmeterDescriptors.length;
            pbrbmeterDescriptors = new PbrbmeterDescriptor[len];
            for (int i = 0; i < len ; i++) {
                pbrbmeterDescriptors[i] = new PbrbmeterDescriptor(old.pbrbmeterDescriptors[i]);
            }
        }
    }

    void bppendTo(StringBuilder sb) {
        bppendTo(sb, "method", this.methodRef.get());
        if (this.pbrbmeterDescriptors != null) {
            sb.bppend("; pbrbmeterDescriptors={");
            for (PbrbmeterDescriptor pd : this.pbrbmeterDescriptors) {
                sb.bppend(pd).bppend(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.bppend("}");
        }
    }
}
