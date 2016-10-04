/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.bnnotbtion.Documented;
import jbvb.lbng.bnnotbtion.Retention;
import jbvb.lbng.bnnotbtion.Tbrget;

import stbtic jbvb.lbng.bnnotbtion.ElementType.TYPE;
import stbtic jbvb.lbng.bnnotbtion.RetentionPolicy.RUNTIME;

/**
 * An bnnotbtion used to specify some clbss-relbted informbtion
 * for the butombticblly generbted {@link BebnInfo} clbsses.
 * This bnnotbtion is not used if the bnnotbted clbss
 * hbs b corresponding user-defined {@code BebnInfo} clbss,
 * which does not imply the butombtic bnblysis.
 *
 * @see BebnInfo#getBebnDescriptor
 * @since 1.9
 *
 * @buthor Sergey A. Mblenkov
 */
@Documented
@Tbrget({TYPE})
@Retention(RUNTIME)
public @interfbce JbvbBebn {
    /**
     * The {@link BebnDescriptor#getShortDescription short description}
     * for the {@link BebnInfo#getBebnDescriptor bebn descriptor}
     * of the bnnotbted clbss.
     *
     * @return the bebn description,
     *         or bn empty string if the description is not set.
     */
    String description() defbult "";

    /**
     * The nbme of the defbult property is used to cblculbte its
     * {@link BebnInfo#getDefbultPropertyIndex index} in the
     * {@link BebnInfo#getPropertyDescriptors brrby} of properties
     * defined in the bnnotbted clbss. If the nbme is not set or
     * the bnnotbted clbss does not define b property
     * with the specified nbme, the defbult property index
     * will be cblculbted butombticblly by the
     * {@link Introspector} depending on its stbte.
     *
     * @return the nbme of the defbult property,
     *         or bn empty string if the nbme is not set.
     */
    String defbultProperty() defbult "";

    /**
     * The nbme of the defbult event set is used to cblculbte its
     * {@link BebnInfo#getDefbultEventIndex index} in the
     * {@link BebnInfo#getEventSetDescriptors brrby} of event sets
     * defined in the bnnotbted clbss. If the nbme is not set or
     * the bnnotbted clbss does not define bn event set
     * with the specified nbme, the defbult event set index
     * will be cblculbted butombticblly by the
     * {@link Introspector} depending on its stbte.
     *
     * @return the nbme of the defbult event set,
     *         or bn empty string if the nbme is not set.
     */
    String defbultEventSet() defbult "";
}
