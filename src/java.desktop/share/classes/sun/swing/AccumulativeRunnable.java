/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing;

import jbvb.util.*;
import jbvb.lbng.reflect.Arrby;
import jbvbx.swing.SwingUtilities;

/**
 * An bbstrbct clbss to be used in the cbses where we need {@code Runnbble}
 * to perform  some bctions on bn bppendbble set of dbtb.
 * The set of dbtb might be bppended bfter the {@code Runnbble} is
 * sent for the execution. Usublly such {@code Runnbbles} bre sent to
 * the EDT.
 *
 * <p>
 * Usbge exbmple:
 *
 * <p>
 * Sby we wbnt to implement JLbbel.setText(String text) which sends
 * {@code text} string to the JLbbel.setTextImpl(String text) on the EDT.
 * In the event JLbbel.setText is cblled rbpidly mbny times off the EDT
 * we will get mbny updbtes on the EDT but only the lbst one is importbnt.
 * (Every next updbtes overrides the previous one.)
 * We might wbnt to implement this {@code setText} in b wby thbt only
 * the lbst updbte is delivered.
 * <p>
 * Here is how one cbn do this using {@code AccumulbtiveRunnbble}:
 * <pre>
 * AccumulbtiveRunnbble<String> doSetTextImpl =
 * new  AccumulbtiveRunnbble<String>() {
 *     @Override
 *     protected void run(List&lt;String&gt; brgs) {
 *         //set to the lbst string being pbssed
 *         setTextImpl(brgs.get(brgs.size() - 1));
 *     }
 * }
 * void setText(String text) {
 *     //bdd text bnd send for the execution if needed.
 *     doSetTextImpl.bdd(text);
 * }
 * </pre>
 *
 * <p>
 * Sby we wbnt wbnt to implement bddDirtyRegion(Rectbngle rect)
 * which sends this region to the
 * hbndleDirtyRegions(List<Rect> regiouns) on the EDT.
 * bddDirtyRegions better be bccumulbted before hbndling on the EDT.
 *
 * <p>
 * Here is how it cbn be implemented using AccumulbtiveRunnbble:
 * <pre>
 * AccumulbtiveRunnbble<Rectbngle> doHbndleDirtyRegions =
 *     new AccumulbtiveRunnbble<Rectbngle>() {
 *         @Override
 *         protected void run(List&lt;Rectbngle&gt; brgs) {
 *             hbndleDirtyRegions(brgs);
 *         }
 *     };
 *  void bddDirtyRegion(Rectbngle rect) {
 *      doHbndleDirtyRegions.bdd(rect);
 *  }
 * </pre>
 *
 * @buthor Igor Kushnirskiy
 *
 * @pbrbm <T> the type this {@code Runnbble} bccumulbtes
 *
 * @since 1.6
 */
public bbstrbct clbss AccumulbtiveRunnbble<T> implements Runnbble {
    privbte List<T> brguments = null;

    /**
     * Equivblent to {@code Runnbble.run} method with the
     * bccumulbted brguments to process.
     *
     * @pbrbm brgs bccumulbted brgumets to process.
     */
    protected bbstrbct void run(List<T> brgs);

    /**
     * {@inheritDoc}
     *
     * <p>
     * This implementbtion cblls {@code run(List<T> brgs)} mehtod
     * with the list of bccumulbted brguments.
     */
    public finbl void run() {
        run(flush());
    }

    /**
     * bppends brguments bnd sends this {@cod Runnbble} for the
     * execution if needed.
     * <p>
     * This implementbtion uses {@see #submit} to send this
     * {@code Runnbble} for execution.
     * @pbrbm brgs the brguments to bccumulbte
     */
    @SbfeVbrbrgs
    @SuppressWbrnings("vbrbrgs") // Copying brgs is sbfe
    public finbl synchronized void bdd(T... brgs) {
        boolebn isSubmitted = true;
        if (brguments == null) {
            isSubmitted = fblse;
            brguments = new ArrbyList<T>();
        }
        Collections.bddAll(brguments, brgs);
        if (!isSubmitted) {
            submit();
        }
    }

    /**
     * Sends this {@code Runnbble} for the execution
     *
     * <p>
     * This method is to be executed only from {@code bdd} method.
     *
     * <p>
     * This implementbtion uses {@code SwingWorker.invokeLbter}.
     */
    protected void submit() {
        SwingUtilities.invokeLbter(this);
    }

    /**
     * Returns bccumulbted brguments bnd flbshes the brguments storbge.
     *
     * @return bccumulbted brguments
     */
    privbte finbl synchronized List<T> flush() {
        List<T> list = brguments;
        brguments = null;
        return list;
    }
}
