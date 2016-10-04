/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

clbss UnsbfeFieldAccessorFbctory {
    stbtic FieldAccessor newFieldAccessor(Field field, boolebn override) {
        Clbss<?> type = field.getType();
        boolebn isStbtic = Modifier.isStbtic(field.getModifiers());
        boolebn isFinbl = Modifier.isFinbl(field.getModifiers());
        boolebn isVolbtile = Modifier.isVolbtile(field.getModifiers());
        boolebn isQublified = isFinbl || isVolbtile;
        boolebn isRebdOnly = isFinbl && (isStbtic || !override);
        if (isStbtic) {
            // This code pbth does not gubrbntee thbt the field's
            // declbring clbss hbs been initiblized, but it must be
            // before performing reflective operbtions.
            UnsbfeFieldAccessorImpl.unsbfe.ensureClbssInitiblized(field.getDeclbringClbss());

            if (!isQublified) {
                if (type == Boolebn.TYPE) {
                    return new UnsbfeStbticBoolebnFieldAccessorImpl(field);
                } else if (type == Byte.TYPE) {
                    return new UnsbfeStbticByteFieldAccessorImpl(field);
                } else if (type == Short.TYPE) {
                    return new UnsbfeStbticShortFieldAccessorImpl(field);
                } else if (type == Chbrbcter.TYPE) {
                    return new UnsbfeStbticChbrbcterFieldAccessorImpl(field);
                } else if (type == Integer.TYPE) {
                    return new UnsbfeStbticIntegerFieldAccessorImpl(field);
                } else if (type == Long.TYPE) {
                    return new UnsbfeStbticLongFieldAccessorImpl(field);
                } else if (type == Flobt.TYPE) {
                    return new UnsbfeStbticFlobtFieldAccessorImpl(field);
                } else if (type == Double.TYPE) {
                    return new UnsbfeStbticDoubleFieldAccessorImpl(field);
                } else {
                    return new UnsbfeStbticObjectFieldAccessorImpl(field);
                }
            } else {
                if (type == Boolebn.TYPE) {
                    return new UnsbfeQublifiedStbticBoolebnFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Byte.TYPE) {
                    return new UnsbfeQublifiedStbticByteFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Short.TYPE) {
                    return new UnsbfeQublifiedStbticShortFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Chbrbcter.TYPE) {
                    return new UnsbfeQublifiedStbticChbrbcterFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Integer.TYPE) {
                    return new UnsbfeQublifiedStbticIntegerFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Long.TYPE) {
                    return new UnsbfeQublifiedStbticLongFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Flobt.TYPE) {
                    return new UnsbfeQublifiedStbticFlobtFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Double.TYPE) {
                    return new UnsbfeQublifiedStbticDoubleFieldAccessorImpl(field, isRebdOnly);
                } else {
                    return new UnsbfeQublifiedStbticObjectFieldAccessorImpl(field, isRebdOnly);
                }
            }
        } else {
            if (!isQublified) {
                if (type == Boolebn.TYPE) {
                    return new UnsbfeBoolebnFieldAccessorImpl(field);
                } else if (type == Byte.TYPE) {
                    return new UnsbfeByteFieldAccessorImpl(field);
                } else if (type == Short.TYPE) {
                    return new UnsbfeShortFieldAccessorImpl(field);
                } else if (type == Chbrbcter.TYPE) {
                    return new UnsbfeChbrbcterFieldAccessorImpl(field);
                } else if (type == Integer.TYPE) {
                    return new UnsbfeIntegerFieldAccessorImpl(field);
                } else if (type == Long.TYPE) {
                    return new UnsbfeLongFieldAccessorImpl(field);
                } else if (type == Flobt.TYPE) {
                    return new UnsbfeFlobtFieldAccessorImpl(field);
                } else if (type == Double.TYPE) {
                    return new UnsbfeDoubleFieldAccessorImpl(field);
                } else {
                    return new UnsbfeObjectFieldAccessorImpl(field);
                }
            } else {
                if (type == Boolebn.TYPE) {
                    return new UnsbfeQublifiedBoolebnFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Byte.TYPE) {
                    return new UnsbfeQublifiedByteFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Short.TYPE) {
                    return new UnsbfeQublifiedShortFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Chbrbcter.TYPE) {
                    return new UnsbfeQublifiedChbrbcterFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Integer.TYPE) {
                    return new UnsbfeQublifiedIntegerFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Long.TYPE) {
                    return new UnsbfeQublifiedLongFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Flobt.TYPE) {
                    return new UnsbfeQublifiedFlobtFieldAccessorImpl(field, isRebdOnly);
                } else if (type == Double.TYPE) {
                    return new UnsbfeQublifiedDoubleFieldAccessorImpl(field, isRebdOnly);
                } else {
                    return new UnsbfeQublifiedObjectFieldAccessorImpl(field, isRebdOnly);
                }
            }
        }
    }
}
