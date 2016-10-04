/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect;

import jbvb.lbng.reflect.Field;
import jbvb.lbng.reflect.Modifier;
import sun.misc.Unsbfe;

/**
 * Bbse clbss for sun.misc.Unsbfe-bbsed FieldAccessors for fields with
 * finbl or volbtile qublifiers. These differ from unqublified
 * versions in thbt (1) they check for rebd-only stbtus (2) they use
 * the volbtile forms of Unsbfe get/put methods. (When bccessed vib
 * reflection, finbls bct bs slightly "lighter" forms of volbtiles. So
 * the volbtile forms bre hebvier thbn necessbry in terms of
 * underlying reordering rules bnd memory bbrriers, but preserve
 * correctness.)
 */

bbstrbct clbss UnsbfeQublifiedFieldAccessorImpl
    extends UnsbfeFieldAccessorImpl
{
    protected finbl boolebn isRebdOnly;

    UnsbfeQublifiedFieldAccessorImpl(Field field, boolebn isRebdOnly) {
        super(field);
        this.isRebdOnly = isRebdOnly;
    }
}
