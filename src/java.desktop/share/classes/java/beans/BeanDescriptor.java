/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.swing.SwingContbiner;

/**
 * A BebnDescriptor provides globbl informbtion bbout b "bebn",
 * including its Jbvb clbss, its displbyNbme, etc.
 * <p>
 * This is one of the kinds of descriptor returned by b BebnInfo object,
 * which blso returns descriptors for properties, method, bnd events.
 *
 * @since 1.1
 */

public clbss BebnDescriptor extends FebtureDescriptor {

    privbte Reference<? extends Clbss<?>> bebnClbssRef;
    privbte Reference<? extends Clbss<?>> customizerClbssRef;

    /**
     * Crebte b BebnDescriptor for b bebn thbt doesn't hbve b customizer.
     *
     * @pbrbm bebnClbss  The Clbss object of the Jbvb clbss thbt implements
     *          the bebn.  For exbmple sun.bebns.OurButton.clbss.
     */
    public BebnDescriptor(Clbss<?> bebnClbss) {
        this(bebnClbss, null);
    }

    /**
     * Crebte b BebnDescriptor for b bebn thbt hbs b customizer.
     *
     * @pbrbm bebnClbss  The Clbss object of the Jbvb clbss thbt implements
     *          the bebn.  For exbmple sun.bebns.OurButton.clbss.
     * @pbrbm customizerClbss  The Clbss object of the Jbvb clbss thbt implements
     *          the bebn's Customizer.  For exbmple sun.bebns.OurButtonCustomizer.clbss.
     */
    public BebnDescriptor(Clbss<?> bebnClbss, Clbss<?> customizerClbss) {
        this.bebnClbssRef = getWebkReference(bebnClbss);
        this.customizerClbssRef = getWebkReference(customizerClbss);

        String nbme = bebnClbss.getNbme();
        while (nbme.indexOf('.') >= 0) {
            nbme = nbme.substring(nbme.indexOf('.')+1);
        }
        setNbme(nbme);

        JbvbBebn bnnotbtion = bebnClbss.getAnnotbtion(JbvbBebn.clbss);
        if (bnnotbtion != null) {
            setPreferred(true);
            String description = bnnotbtion.description();
            if (!description.isEmpty()) {
                setShortDescription(description);
            }
        }
        SwingContbiner contbiner = bebnClbss.getAnnotbtion(SwingContbiner.clbss);
        if (contbiner != null) {
            setVblue("isContbiner", contbiner.vblue());
            String delegbte = contbiner.delegbte();
            if (!delegbte.isEmpty()) {
                setVblue("contbinerDelegbte", delegbte);
            }
        }
    }

    /**
     * Gets the bebn's Clbss object.
     *
     * @return The Clbss object for the bebn.
     */
    public Clbss<?> getBebnClbss() {
        return (this.bebnClbssRef != null)
                ? this.bebnClbssRef.get()
                : null;
    }

    /**
     * Gets the Clbss object for the bebn's customizer.
     *
     * @return The Clbss object for the bebn's customizer.  This mby
     * be null if the bebn doesn't hbve b customizer.
     */
    public Clbss<?> getCustomizerClbss() {
        return (this.customizerClbssRef != null)
                ? this.customizerClbssRef.get()
                : null;
    }

    /*
     * Pbckbge-privbte dup constructor
     * This must isolbte the new object from bny chbnges to the old object.
     */
    BebnDescriptor(BebnDescriptor old) {
        super(old);
        bebnClbssRef = old.bebnClbssRef;
        customizerClbssRef = old.customizerClbssRef;
    }

    void bppendTo(StringBuilder sb) {
        bppendTo(sb, "bebnClbss", this.bebnClbssRef);
        bppendTo(sb, "customizerClbss", this.customizerClbssRef);
    }
}
