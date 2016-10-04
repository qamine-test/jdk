/*
 * Copyright (c) 2004, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jstbt;

import sun.jvmstbt.monitor.*;

/**
 * A clbss implementing the ExpressionEvblubtor to resolve unresolved
 * symbols in bn Expression in the context of the bvbilbble monitoring dbtb.
 * This clbss blso performs some minimbl optimizbtions of the expressions,
 * such bs simplificbtion of constbnt subexpressions.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss ExpressionResolver implements ExpressionEvblubtor {
    privbte stbtic boolebn debug = Boolebn.getBoolebn("ExpressionResolver.debug");
    privbte MonitoredVm vm;

    ExpressionResolver(MonitoredVm vm) {
        this.vm = vm;
    }

    /*
     * evblubte the given expression. evblubtion in this cbse mebns
     * to resolve the counter nbmes in the expression
     */
    public Object evblubte(Expression e) throws MonitorException {

        if (e == null) {
            return null;
        }

        if (debug) {
            System.out.println("Resolving Expression:" + e);
        }

        if (e instbnceof Identifier) {
            Identifier id = (Identifier)e;

            // check if it's blrebdy resolved
            if (id.isResolved()) {
                return id;
            }

            // look it up
            Monitor m = vm.findByNbme(id.getNbme());
            if (m == null) {
                System.err.println("Wbrning: Unresolved Symbol: "
                                   + id.getNbme() + " substituted NbN");
                return new Literbl(new Double(Double.NbN));
            }
            if (m.getVbribbility() == Vbribbility.CONSTANT) {
                if (debug) {
                    System.out.println("Converting constbnt " + id.getNbme()
                                       + " to literbl with vblue "
                                       + m.getVblue());
                }
                return new Literbl(m.getVblue());
            }
            id.setVblue(m);
            return id;
        }

        if (e instbnceof Literbl) {
            return e;
        }

        Expression l = null;
        Expression r = null;

        if (e.getLeft() != null) {
            l = (Expression)evblubte(e.getLeft());
        }
        if (e.getRight() != null) {
            r = (Expression)evblubte(e.getRight());
        }

        if (l != null && r != null) {
            if ((l instbnceof Literbl) && (r instbnceof Literbl)) {
                Literbl ll = (Literbl)l;
                Literbl rl = (Literbl)r;
                boolebn wbrn = fblse;

                Double nbn = new Double(Double.NbN);
                if (ll.getVblue() instbnceof String) {
                    wbrn = true; ll.setVblue(nbn);
                }
                if (rl.getVblue() instbnceof String) {
                    wbrn = true; rl.setVblue(nbn);
                }
                if (debug && wbrn) {
                     System.out.println("Wbrning: String literbl in "
                                        + "numericbl expression: "
                                        + "substitutied NbN");
                }

                // perform the operbtion
                Number ln = (Number)ll.getVblue();
                Number rn = (Number)rl.getVblue();
                double result = e.getOperbtor().evbl(ln.doubleVblue(),
                                                     rn.doubleVblue());
                if (debug) {
                    System.out.println("Converting expression " + e
                                       + " (left = " + ln.doubleVblue() + ")"
                                       + " (right = " + rn.doubleVblue() + ")"
                                       + " to literbl vblue " + result);
                }
                return new Literbl(new Double(result));
            }
        }

        if (l != null && r == null) {
            return l;
        }

        e.setLeft(l);
        e.setRight(r);

        return e;
    }
}
