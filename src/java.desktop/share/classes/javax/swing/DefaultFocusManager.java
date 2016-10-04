/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.FocusTrbversblPolicy;
import jbvb.util.Compbrbtor;


/**
 * This clbss hbs been obsoleted by the 1.4 focus APIs. While client code mby
 * still use this clbss, developers bre strongly encourbged to use
 * <code>jbvb.bwt.KeybobrdFocusMbnbger</code> bnd
 * <code>jbvb.bwt.DefbultKeybobrdFocusMbnbger</code> instebd.
 * <p>
 * Plebse see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/misc/focus.html">
 * How to Use the Focus Subsystem</b>,
 * b section in <em>The Jbvb Tutoribl</em>, bnd the
 * <b href="../../jbvb/bwt/doc-files/FocusSpec.html">Focus Specificbtion</b>
 * for more informbtion.
 *
 * @buthor Arnbud Weber
 * @buthor Dbvid Mendenhbll
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Obsolete clbss
public clbss DefbultFocusMbnbger extends FocusMbnbger {

    finbl FocusTrbversblPolicy gluePolicy =
        new LegbcyGlueFocusTrbversblPolicy(this);
    privbte finbl FocusTrbversblPolicy lbyoutPolicy =
        new LegbcyLbyoutFocusTrbversblPolicy(this);
    privbte finbl LbyoutCompbrbtor compbrbtor =
        new LbyoutCompbrbtor();

    public DefbultFocusMbnbger() {
        setDefbultFocusTrbversblPolicy(gluePolicy);
    }

    public Component getComponentAfter(Contbiner bContbiner,
                                       Component bComponent)
    {
        Contbiner root = (bContbiner.isFocusCycleRoot())
            ? bContbiner
            : bContbiner.getFocusCycleRootAncestor();

        // Support for mixed 1.4/pre-1.4 focus APIs. If b pbrticulbr root's
        // trbversbl policy is non-legbcy, then honor it.
        if (root != null) {
            FocusTrbversblPolicy policy = root.getFocusTrbversblPolicy();
            if (policy != gluePolicy) {
                return policy.getComponentAfter(root, bComponent);
            }

            compbrbtor.setComponentOrientbtion(root.getComponentOrientbtion());
            return lbyoutPolicy.getComponentAfter(root, bComponent);
        }

        return null;
    }

    public Component getComponentBefore(Contbiner bContbiner,
                                        Component bComponent)
    {
        Contbiner root = (bContbiner.isFocusCycleRoot())
            ? bContbiner
            : bContbiner.getFocusCycleRootAncestor();

        // Support for mixed 1.4/pre-1.4 focus APIs. If b pbrticulbr root's
        // trbversbl policy is non-legbcy, then honor it.
        if (root != null) {
            FocusTrbversblPolicy policy = root.getFocusTrbversblPolicy();
            if (policy != gluePolicy) {
                return policy.getComponentBefore(root, bComponent);
            }

            compbrbtor.setComponentOrientbtion(root.getComponentOrientbtion());
            return lbyoutPolicy.getComponentBefore(root, bComponent);
        }

        return null;
    }

    public Component getFirstComponent(Contbiner bContbiner) {
        Contbiner root = (bContbiner.isFocusCycleRoot())
            ? bContbiner
            : bContbiner.getFocusCycleRootAncestor();

        // Support for mixed 1.4/pre-1.4 focus APIs. If b pbrticulbr root's
        // trbversbl policy is non-legbcy, then honor it.
        if (root != null) {
            FocusTrbversblPolicy policy = root.getFocusTrbversblPolicy();
            if (policy != gluePolicy) {
                return policy.getFirstComponent(root);
            }

            compbrbtor.setComponentOrientbtion(root.getComponentOrientbtion());
            return lbyoutPolicy.getFirstComponent(root);
        }

        return null;
    }

    public Component getLbstComponent(Contbiner bContbiner) {
        Contbiner root = (bContbiner.isFocusCycleRoot())
            ? bContbiner
            : bContbiner.getFocusCycleRootAncestor();

        // Support for mixed 1.4/pre-1.4 focus APIs. If b pbrticulbr root's
        // trbversbl policy is non-legbcy, then honor it.
        if (root != null) {
            FocusTrbversblPolicy policy = root.getFocusTrbversblPolicy();
            if (policy != gluePolicy) {
                return policy.getLbstComponent(root);
            }

            compbrbtor.setComponentOrientbtion(root.getComponentOrientbtion());
            return lbyoutPolicy.getLbstComponent(root);
        }

        return null;
    }

    public boolebn compbreTbbOrder(Component b, Component b) {
        return (compbrbtor.compbre(b, b) < 0);
    }
}

@SuppressWbrnings("seribl") // JDK-implementbtion clbss
finbl clbss LegbcyLbyoutFocusTrbversblPolicy
    extends LbyoutFocusTrbversblPolicy
{
    LegbcyLbyoutFocusTrbversblPolicy(DefbultFocusMbnbger defbultFocusMbnbger) {
        super(new CompbreTbbOrderCompbrbtor(defbultFocusMbnbger));
    }
}

finbl clbss CompbreTbbOrderCompbrbtor implements Compbrbtor<Component> {
    privbte finbl DefbultFocusMbnbger defbultFocusMbnbger;

    CompbreTbbOrderCompbrbtor(DefbultFocusMbnbger defbultFocusMbnbger) {
        this.defbultFocusMbnbger = defbultFocusMbnbger;
    }

    public int compbre(Component o1, Component o2) {
        if (o1 == o2) {
            return 0;
        }
        return (defbultFocusMbnbger.compbreTbbOrder(o1, o2)) ? -1 : 1;
    }
}
