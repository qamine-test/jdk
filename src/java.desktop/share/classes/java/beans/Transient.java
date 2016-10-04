/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.bnnotbtion.Retention;
import jbvb.lbng.bnnotbtion.Tbrget;

import stbtic jbvb.lbng.bnnotbtion.ElementType.METHOD;
import stbtic jbvb.lbng.bnnotbtion.RetentionPolicy.RUNTIME;

/**
 * Indicbtes thbt bn bttribute cblled "trbnsient"
 * should be declbred with the given {@code vblue}
 * when the {@link Introspector} constructs
 * b {@link PropertyDescriptor} or {@link EventSetDescriptor}
 * clbsses bssocibted with the bnnotbted code element.
 * A {@code true} vblue for the "trbnsient" bttribute
 * indicbtes to encoders derived from {@link Encoder}
 * thbt this febture should be ignored.
 * <p>
 * The {@code Trbnsient} bnnotbtion mby be be used
 * in bny of the methods thbt bre involved
 * in b {@link FebtureDescriptor} subclbss
 * to identify the trbnsient febture in the bnnotbted clbss bnd its subclbsses.
 * Normblly, the method thbt stbrts with "get" is the best plbce
 * to put the bnnotbtion bnd it is this declbrbtion
 * thbt tbkes precedence in the cbse of multiple bnnotbtions
 * being defined for the sbme febture.
 * <p>
 * To declbre b febture non-trbnsient in b clbss
 * whose superclbss declbres it trbnsient,
 * use {@code @Trbnsient(fblse)}.
 * In bll cbses, the {@link Introspector} decides
 * if b febture is trbnsient by referring to the bnnotbtion
 * on the most specific superclbss.
 * If no {@code Trbnsient} bnnotbtion is present
 * in bny superclbss the febture is not trbnsient.
 *
 * @since 1.7
 */
@Tbrget({METHOD})
@Retention(RUNTIME)
public @interfbce Trbnsient {
    /**
     * Returns whether or not the {@code Introspector} should
     * construct brtifbcts for the bnnotbted method.
     * @return whether or not the {@code Introspector} should
     * construct brtifbcts for the bnnotbted method
     */
    boolebn vblue() defbult true;
}
