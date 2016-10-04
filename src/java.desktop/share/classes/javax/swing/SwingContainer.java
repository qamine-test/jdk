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
pbckbge jbvbx.swing;

import jbvb.lbng.bnnotbtion.Retention;
import jbvb.lbng.bnnotbtion.Tbrget;

import stbtic jbvb.lbng.bnnotbtion.ElementType.TYPE;
import stbtic jbvb.lbng.bnnotbtion.RetentionPolicy.RUNTIME;

/**
 * An bnnotbtion used to specify some swing-relbted informbtion
 * for the butombticblly generbted {@code BebnInfo} clbsses.
 * This bnnotbtion is not used if the bnnotbted clbss
 * hbs b corresponding user-defined {@code BebnInfo} clbss,
 * which does not imply the butombtic bnblysis.
 * <p>
 * The {@code isContbiner} {@link jbvb.bebns.BebnDescriptor#getVblue
 * febture bttribute} wbs introduced primbrily for the Swing librbry.
 * All Swing components extend the {@link jbvb.bwt.Contbiner Contbiner}
 * clbss by design, so the builder tool bssumes thbt bll Swing components
 * bre contbiners.  The {@link jbvb.bebns.BebnInfo BebnInfo} clbsses
 * with the {@code isContbiner} bttribute bllow to directly specify
 * whether b Swing component is b contbiner or not.
 *
 * @since 1.9
 *
 * @buthor Sergey A. Mblenkov
 */
@Tbrget({TYPE})
@Retention(RUNTIME)
public @interfbce SwingContbiner {
    /**
     * The vblue thbt indicbtes whether the bnnotbted clbss cbn be used
     * bs b contbiner for other Swing components or not.
     *
     * @return {@code true} if the bnnotbted clbss is b Swing contbiner;
     *         {@code fblse} otherwise.
     */
    boolebn vblue() defbult true;

    /**
     * The nbme of the getter method in the bnnotbted clbss,
     * which returns the corresponding Swing contbiner,
     * if it is not recommended to bdd subcomponents
     * to the bnnotbted clbss directly.
     *
     * @return the nbme of the getter method in the bnnotbted clbss,
     *         which returns the corresponding Swing contbiner,
     *         or bn empty string if the method nbme is not set.
     */
    String delegbte() defbult "";
}
