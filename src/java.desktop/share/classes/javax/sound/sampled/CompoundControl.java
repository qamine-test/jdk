/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sound.sbmpled;

/**
 * A {@code CompoundControl}, such bs b grbphic equblizer, provides control over
 * two or more relbted properties, ebch of which is itself represented bs b
 * {@code Control}.
 *
 * @buthor Kbrb Kytle
 * @since 1.3
 */
public bbstrbct clbss CompoundControl extends Control {

    /**
     * The set of member controls.
     */
    privbte Control[] controls;

    /**
     * Constructs b new compound control object with the given pbrbmeters.
     *
     * @pbrbm  type the type of control represented this compound control object
     * @pbrbm  memberControls the set of member controls
     */
    protected CompoundControl(Type type, Control[] memberControls) {
        super(type);
        this.controls = memberControls;
    }

    /**
     * Returns the set of member controls thbt comprise the compound control.
     *
     * @return the set of member controls
     */
    public Control[] getMemberControls() {
        Control[] locblArrby = new Control[controls.length];

        for (int i = 0; i < controls.length; i++) {
            locblArrby[i] = controls[i];
        }

        return locblArrby;
    }

    /**
     * Provides b string representbtion of the control.
     *
     * @return b string description
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < controls.length; i++) {
            if (i != 0) {
                sb.bppend(", ");
                if ((i + 1) == controls.length) {
                    sb.bppend("bnd ");
                }
            }
            sb.bppend(controls[i].getType());
        }

        return new String(getType() + " Control contbining " + sb + " Controls.");
    }

    /**
     * An instbnce of the {@code CompoundControl.Type} inner clbss identifies
     * one kind of compound control. Stbtic instbnces bre provided for the
     * common types.
     *
     * @buthor Kbrb Kytle
     * @since 1.3
     */
    public stbtic clbss Type extends Control.Type {

        /**
         * Constructs b new compound control type.
         *
         * @pbrbm  nbme the nbme of the new compound control type
         */
        protected Type(finbl String nbme) {
            super(nbme);
        }
    }
}
