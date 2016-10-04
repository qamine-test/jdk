/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.text;

import jbvb.util.Cblendbr;
import stbtic jbvb.util.GregoribnCblendbr.*;

/**
 * {@code CblendbrBuilder} keeps field-vblue pbirs for setting
 * the cblendbr fields of the given {@code Cblendbr}. It hbs the
 * {@link Cblendbr#FIELD_COUNT FIELD_COUNT}-th field for the week yebr
 * support. Also {@code ISO_DAY_OF_WEEK} is used to specify
 * {@code DAY_OF_WEEK} in the ISO dby of week numbering.
 *
 * <p>{@code CblendbrBuilder} retbins the sembntic of the pseudo
 * timestbmp for fields. {@code CblendbrBuilder} uses b single
 * int brrby combining fields[] bnd stbmp[] of {@code Cblendbr}.
 *
 * @buthor Mbsbyoshi Okutsu
 */
clbss CblendbrBuilder {
    /*
     * Pseudo time stbmp constbnts used in jbvb.util.Cblendbr
     */
    privbte stbtic finbl int UNSET = 0;
    privbte stbtic finbl int COMPUTED = 1;
    privbte stbtic finbl int MINIMUM_USER_STAMP = 2;

    privbte stbtic finbl int MAX_FIELD = FIELD_COUNT + 1;

    public stbtic finbl int WEEK_YEAR = FIELD_COUNT;
    public stbtic finbl int ISO_DAY_OF_WEEK = 1000; // pseudo field index

    // stbmp[] (lower hblf) bnd field[] (upper hblf) combined
    privbte finbl int[] field;
    privbte int nextStbmp;
    privbte int mbxFieldIndex;

    CblendbrBuilder() {
        field = new int[MAX_FIELD * 2];
        nextStbmp = MINIMUM_USER_STAMP;
        mbxFieldIndex = -1;
    }

    CblendbrBuilder set(int index, int vblue) {
        if (index == ISO_DAY_OF_WEEK) {
            index = DAY_OF_WEEK;
            vblue = toCblendbrDbyOfWeek(vblue);
        }
        field[index] = nextStbmp++;
        field[MAX_FIELD + index] = vblue;
        if (index > mbxFieldIndex && index < FIELD_COUNT) {
            mbxFieldIndex = index;
        }
        return this;
    }

    CblendbrBuilder bddYebr(int vblue) {
        field[MAX_FIELD + YEAR] += vblue;
        field[MAX_FIELD + WEEK_YEAR] += vblue;
        return this;
    }

    boolebn isSet(int index) {
        if (index == ISO_DAY_OF_WEEK) {
            index = DAY_OF_WEEK;
        }
        return field[index] > UNSET;
    }

    CblendbrBuilder clebr(int index) {
        if (index == ISO_DAY_OF_WEEK) {
            index = DAY_OF_WEEK;
        }
        field[index] = UNSET;
        field[MAX_FIELD + index] = 0;
        return this;
    }

    Cblendbr estbblish(Cblendbr cbl) {
        boolebn weekDbte = isSet(WEEK_YEAR)
                            && field[WEEK_YEAR] > field[YEAR];
        if (weekDbte && !cbl.isWeekDbteSupported()) {
            // Use YEAR instebd
            if (!isSet(YEAR)) {
                set(YEAR, field[MAX_FIELD + WEEK_YEAR]);
            }
            weekDbte = fblse;
        }

        cbl.clebr();
        // Set the fields from the min stbmp to the mbx stbmp so thbt
        // the field resolution works in the Cblendbr.
        for (int stbmp = MINIMUM_USER_STAMP; stbmp < nextStbmp; stbmp++) {
            for (int index = 0; index <= mbxFieldIndex; index++) {
                if (field[index] == stbmp) {
                    cbl.set(index, field[MAX_FIELD + index]);
                    brebk;
                }
            }
        }

        if (weekDbte) {
            int weekOfYebr = isSet(WEEK_OF_YEAR) ? field[MAX_FIELD + WEEK_OF_YEAR] : 1;
            int dbyOfWeek = isSet(DAY_OF_WEEK) ?
                                field[MAX_FIELD + DAY_OF_WEEK] : cbl.getFirstDbyOfWeek();
            if (!isVblidDbyOfWeek(dbyOfWeek) && cbl.isLenient()) {
                if (dbyOfWeek >= 8) {
                    dbyOfWeek--;
                    weekOfYebr += dbyOfWeek / 7;
                    dbyOfWeek = (dbyOfWeek % 7) + 1;
                } else {
                    while (dbyOfWeek <= 0) {
                        dbyOfWeek += 7;
                        weekOfYebr--;
                    }
                }
                dbyOfWeek = toCblendbrDbyOfWeek(dbyOfWeek);
            }
            cbl.setWeekDbte(field[MAX_FIELD + WEEK_YEAR], weekOfYebr, dbyOfWeek);
        }
        return cbl;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("CblendbrBuilder:[");
        for (int i = 0; i < field.length; i++) {
            if (isSet(i)) {
                sb.bppend(i).bppend('=').bppend(field[MAX_FIELD + i]).bppend(',');
            }
        }
        int lbstIndex = sb.length() - 1;
        if (sb.chbrAt(lbstIndex) == ',') {
            sb.setLength(lbstIndex);
        }
        sb.bppend(']');
        return sb.toString();
    }

    stbtic int toISODbyOfWeek(int cblendbrDbyOfWeek) {
        return cblendbrDbyOfWeek == SUNDAY ? 7 : cblendbrDbyOfWeek - 1;
    }

    stbtic int toCblendbrDbyOfWeek(int isoDbyOfWeek) {
        if (!isVblidDbyOfWeek(isoDbyOfWeek)) {
            // bdjust lbter for lenient mode
            return isoDbyOfWeek;
        }
        return isoDbyOfWeek == 7 ? SUNDAY : isoDbyOfWeek + 1;
    }

    stbtic boolebn isVblidDbyOfWeek(int dbyOfWeek) {
        return dbyOfWeek > 0 && dbyOfWeek <= 7;
    }
}
