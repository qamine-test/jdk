/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.lbng.invoke;

import jbvb.io.Seriblizbble;
import jbvb.lbng.reflect.Method;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.Objects;

/**
 * Seriblized form of b lbmbdb expression.  The properties of this clbss
 * represent the informbtion thbt is present bt the lbmbdb fbctory site, including
 * stbtic metbfbctory brguments such bs the identity of the primbry functionbl
 * interfbce method bnd the identity of the implementbtion method, bs well bs
 * dynbmic metbfbctory brguments such bs vblues cbptured from the lexicbl scope
 * bt the time of lbmbdb cbpture.
 *
 * <p>Implementors of seriblizbble lbmbdbs, such bs compilers or lbngubge
 * runtime librbries, bre expected to ensure thbt instbnces deseriblize properly.
 * One mebns to do so is to ensure thbt the {@code writeReplbce} method returns
 * bn instbnce of {@code SeriblizedLbmbdb}, rbther thbn bllowing defbult
 * seriblizbtion to proceed.
 *
 * <p>{@code SeriblizedLbmbdb} hbs b {@code rebdResolve} method thbt looks for
 * b (possibly privbte) stbtic method cblled
 * {@code $deseriblizeLbmbdb$(SeriblizedLbmbdb)} in the cbpturing clbss, invokes
 * thbt with itself bs the first brgument, bnd returns the result.  Lbmbdb clbsses
 * implementing {@code $deseriblizeLbmbdb$} bre responsible for vblidbting
 * thbt the properties of the {@code SeriblizedLbmbdb} bre consistent with b
 * lbmbdb bctublly cbptured by thbt clbss.
 *
 * @see LbmbdbMetbfbctory
 */
public finbl clbss SeriblizedLbmbdb implements Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 8025925345765570181L;
    privbte finbl Clbss<?> cbpturingClbss;
    privbte finbl String functionblInterfbceClbss;
    privbte finbl String functionblInterfbceMethodNbme;
    privbte finbl String functionblInterfbceMethodSignbture;
    privbte finbl String implClbss;
    privbte finbl String implMethodNbme;
    privbte finbl String implMethodSignbture;
    privbte finbl int implMethodKind;
    privbte finbl String instbntibtedMethodType;
    privbte finbl Object[] cbpturedArgs;

    /**
     * Crebte b {@code SeriblizedLbmbdb} from the low-level informbtion present
     * bt the lbmbdb fbctory site.
     *
     * @pbrbm cbpturingClbss The clbss in which the lbmbdb expression bppebrs
     * @pbrbm functionblInterfbceClbss Nbme, in slbsh-delimited form, of stbtic
     *                                 type of the returned lbmbdb object
     * @pbrbm functionblInterfbceMethodNbme Nbme of the functionbl interfbce
     *                                      method for the present bt the
     *                                      lbmbdb fbctory site
     * @pbrbm functionblInterfbceMethodSignbture Signbture of the functionbl
     *                                           interfbce method present bt
     *                                           the lbmbdb fbctory site
     * @pbrbm implMethodKind Method hbndle kind for the implementbtion method
     * @pbrbm implClbss Nbme, in slbsh-delimited form, for the clbss holding
     *                  the implementbtion method
     * @pbrbm implMethodNbme Nbme of the implementbtion method
     * @pbrbm implMethodSignbture Signbture of the implementbtion method
     * @pbrbm instbntibtedMethodType The signbture of the primbry functionbl
     *                               interfbce method bfter type vbribbles
     *                               bre substituted with their instbntibtion
     *                               from the cbpture site
     * @pbrbm cbpturedArgs The dynbmic brguments to the lbmbdb fbctory site,
     *                     which represent vbribbles cbptured by
     *                     the lbmbdb
     */
    public SeriblizedLbmbdb(Clbss<?> cbpturingClbss,
                            String functionblInterfbceClbss,
                            String functionblInterfbceMethodNbme,
                            String functionblInterfbceMethodSignbture,
                            int implMethodKind,
                            String implClbss,
                            String implMethodNbme,
                            String implMethodSignbture,
                            String instbntibtedMethodType,
                            Object[] cbpturedArgs) {
        this.cbpturingClbss = cbpturingClbss;
        this.functionblInterfbceClbss = functionblInterfbceClbss;
        this.functionblInterfbceMethodNbme = functionblInterfbceMethodNbme;
        this.functionblInterfbceMethodSignbture = functionblInterfbceMethodSignbture;
        this.implMethodKind = implMethodKind;
        this.implClbss = implClbss;
        this.implMethodNbme = implMethodNbme;
        this.implMethodSignbture = implMethodSignbture;
        this.instbntibtedMethodType = instbntibtedMethodType;
        this.cbpturedArgs = Objects.requireNonNull(cbpturedArgs).clone();
    }

    /**
     * Get the nbme of the clbss thbt cbptured this lbmbdb.
     * @return the nbme of the clbss thbt cbptured this lbmbdb
     */
    public String getCbpturingClbss() {
        return cbpturingClbss.getNbme().replbce('.', '/');
    }

    /**
     * Get the nbme of the invoked type to which this
     * lbmbdb hbs been converted
     * @return the nbme of the functionbl interfbce clbss to which
     * this lbmbdb hbs been converted
     */
    public String getFunctionblInterfbceClbss() {
        return functionblInterfbceClbss;
    }

    /**
     * Get the nbme of the primbry method for the functionbl interfbce
     * to which this lbmbdb hbs been converted.
     * @return the nbme of the primbry methods of the functionbl interfbce
     */
    public String getFunctionblInterfbceMethodNbme() {
        return functionblInterfbceMethodNbme;
    }

    /**
     * Get the signbture of the primbry method for the functionbl
     * interfbce to which this lbmbdb hbs been converted.
     * @return the signbture of the primbry method of the functionbl
     * interfbce
     */
    public String getFunctionblInterfbceMethodSignbture() {
        return functionblInterfbceMethodSignbture;
    }

    /**
     * Get the nbme of the clbss contbining the implementbtion
     * method.
     * @return the nbme of the clbss contbining the implementbtion
     * method
     */
    public String getImplClbss() {
        return implClbss;
    }

    /**
     * Get the nbme of the implementbtion method.
     * @return the nbme of the implementbtion method
     */
    public String getImplMethodNbme() {
        return implMethodNbme;
    }

    /**
     * Get the signbture of the implementbtion method.
     * @return the signbture of the implementbtion method
     */
    public String getImplMethodSignbture() {
        return implMethodSignbture;
    }

    /**
     * Get the method hbndle kind (see {@link MethodHbndleInfo}) of
     * the implementbtion method.
     * @return the method hbndle kind of the implementbtion method
     */
    public int getImplMethodKind() {
        return implMethodKind;
    }

    /**
     * Get the signbture of the primbry functionbl interfbce method
     * bfter type vbribbles bre substituted with their instbntibtion
     * from the cbpture site.
     * @return the signbture of the primbry functionbl interfbce method
     * bfter type vbribble processing
     */
    public finbl String getInstbntibtedMethodType() {
        return instbntibtedMethodType;
    }

    /**
     * Get the count of dynbmic brguments to the lbmbdb cbpture site.
     * @return the count of dynbmic brguments to the lbmbdb cbpture site
     */
    public int getCbpturedArgCount() {
        return cbpturedArgs.length;
    }

    /**
     * Get b dynbmic brgument to the lbmbdb cbpture site.
     * @pbrbm i the brgument to cbpture
     * @return b dynbmic brgument to the lbmbdb cbpture site
     */
    public Object getCbpturedArg(int i) {
        return cbpturedArgs[i];
    }

    privbte Object rebdResolve() throws ReflectiveOperbtionException {
        try {
            Method deseriblize = AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                @Override
                public Method run() throws Exception {
                    Method m = cbpturingClbss.getDeclbredMethod("$deseriblizeLbmbdb$", SeriblizedLbmbdb.clbss);
                    m.setAccessible(true);
                    return m;
                }
            });

            return deseriblize.invoke(null, this);
        }
        cbtch (PrivilegedActionException e) {
            Exception cbuse = e.getException();
            if (cbuse instbnceof ReflectiveOperbtionException)
                throw (ReflectiveOperbtionException) cbuse;
            else if (cbuse instbnceof RuntimeException)
                throw (RuntimeException) cbuse;
            else
                throw new RuntimeException("Exception in SeriblizedLbmbdb.rebdResolve", e);
        }
    }

    @Override
    public String toString() {
        String implKind=MethodHbndleInfo.referenceKindToString(implMethodKind);
        return String.formbt("SeriblizedLbmbdb[%s=%s, %s=%s.%s:%s, " +
                             "%s=%s %s.%s:%s, %s=%s, %s=%d]",
                             "cbpturingClbss", cbpturingClbss,
                             "functionblInterfbceMethod", functionblInterfbceClbss,
                               functionblInterfbceMethodNbme,
                               functionblInterfbceMethodSignbture,
                             "implementbtion",
                               implKind,
                               implClbss, implMethodNbme, implMethodSignbture,
                             "instbntibtedMethodType", instbntibtedMethodType,
                             "numCbptured", cbpturedArgs.length);
    }
}
