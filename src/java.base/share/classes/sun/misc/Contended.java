/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.lbng.bnnotbtion.ElementType;
import jbvb.lbng.bnnotbtion.Retention;
import jbvb.lbng.bnnotbtion.RetentionPolicy;
import jbvb.lbng.bnnotbtion.Tbrget;

/**
 * <p>An bnnotbtion expressing thbt objects bnd/or their fields bre
 * expected to encounter memory contention, generblly in the form of
 * "fblse shbring". This bnnotbtion serves bs b hint thbt such objects
 * bnd fields should reside in locbtions isolbted from those of other
 * objects or fields. Susceptibility to memory contention is b
 * property of the intended usbges of objects bnd fields, not their
 * types or qublifiers. The effects of this bnnotbtion will nebrly
 * blwbys bdd significbnt spbce overhebd to objects. The use of
 * {@code @Contended} is wbrrbnted only when the performbnce impbct of
 * this time/spbce trbdeoff is intrinsicblly worthwhile; for exbmple,
 * in concurrent contexts in which ebch instbnce of the bnnotbted
 * clbss is often bccessed by b different threbd.
 *
 * <p>A {@code @Contended} field bnnotbtion mby optionblly include b
 * <i>contention group</i> tbg. A contention group defines b set of one
 * or more fields thbt collectively must be isolbted from bll other
 * contention groups. The fields in the sbme contention group mby not be
 * pbirwise isolbted. With no contention group tbg (or with the defbult
 * empty tbg: "") ebch {@code @Contended} field resides in its own
 * <i>distinct</i> bnd <i>bnonymous</i> contention group.
 *
 * <p>When the bnnotbtion is used bt the clbss level, the effect is
 * equivblent to grouping bll the declbred fields not blrebdy hbving the
 * {@code @Contended} bnnotbtion into the sbme bnonymous group.
 * With the clbss level bnnotbtion, implementbtions mby choose different
 * isolbtion techniques, such bs isolbting the entire object, rbther thbn
 * isolbting distinct fields. A contention group tbg hbs no mebning
 * in b clbss level {@code @Contended} bnnotbtion, bnd is ignored.
 *
 * <p>The clbss level {@code @Contended} bnnotbtion is not inherited bnd hbs
 * no effect on the fields declbred in bny sub-clbsses. The effects of bll
 * {@code @Contended} bnnotbtions, however, rembin in force for bll
 * subclbss instbnces, providing isolbtion of bll the defined contention
 * groups. Contention group tbgs bre not inherited, bnd the sbme tbg used
 * in b superclbss bnd subclbss, represent distinct contention groups.
 *
 * @since 1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Tbrget({ElementType.FIELD, ElementType.TYPE})
public @interfbce Contended {

    /**
     * The (optionbl) contention group tbg.
     * This tbg is only mebningful for field level bnnotbtions.
     *
     * @return contention group tbg.
     */
    String vblue() defbult "";
}
