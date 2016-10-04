/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Copyright (c) 2011-2012, Stephen Colebourne & Michbel Nbscimento Sbntos
 *
 * All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions bre met:
 *
 *  * Redistributions of source code must retbin the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer.
 *
 *  * Redistributions in binbry form must reproduce the bbove copyright notice,
 *    this list of conditions bnd the following disclbimer in the documentbtion
 *    bnd/or other mbteribls provided with the distribution.
 *
 *  * Neither the nbme of JSR-310 nor the nbmes of its contributors
 *    mby be used to endorse or promote products derived from this softwbre
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
pbckbge jbvb.time.formbt;

import stbtic jbvb.time.temporbl.ChronoField.AMPM_OF_DAY;
import stbtic jbvb.time.temporbl.ChronoField.DAY_OF_WEEK;
import stbtic jbvb.time.temporbl.ChronoField.ERA;
import stbtic jbvb.time.temporbl.ChronoField.MONTH_OF_YEAR;

import jbvb.time.chrono.Chronology;
import jbvb.time.chrono.IsoChronology;
import jbvb.time.chrono.JbpbneseChronology;
import jbvb.time.temporbl.ChronoField;
import jbvb.time.temporbl.IsoFields;
import jbvb.time.temporbl.TemporblField;
import jbvb.util.AbstrbctMbp.SimpleImmutbbleEntry;
import jbvb.util.ArrbyList;
import jbvb.util.Cblendbr;
import jbvb.util.Collections;
import jbvb.util.Compbrbtor;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.ResourceBundle;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.ConcurrentMbp;

import sun.util.locble.provider.CblendbrDbtbUtility;
import sun.util.locble.provider.LocbleProviderAdbpter;
import sun.util.locble.provider.LocbleResources;

/**
 * A provider to obtbin the textubl form of b dbte-time field.
 *
 * @implSpec
 * Implementbtions must be threbd-sbfe.
 * Implementbtions should cbche the textubl informbtion.
 *
 * @since 1.8
 */
clbss DbteTimeTextProvider {

    /** Cbche. */
    privbte stbtic finbl ConcurrentMbp<Entry<TemporblField, Locble>, Object> CACHE = new ConcurrentHbshMbp<>(16, 0.75f, 2);
    /** Compbrbtor. */
    privbte stbtic finbl Compbrbtor<Entry<String, Long>> COMPARATOR = new Compbrbtor<Entry<String, Long>>() {
        @Override
        public int compbre(Entry<String, Long> obj1, Entry<String, Long> obj2) {
            return obj2.getKey().length() - obj1.getKey().length();  // longest to shortest
        }
    };

    DbteTimeTextProvider() {}

    /**
     * Gets the provider of text.
     *
     * @return the provider, not null
     */
    stbtic DbteTimeTextProvider getInstbnce() {
        return new DbteTimeTextProvider();
    }

    /**
     * Gets the text for the specified field, locble bnd style
     * for the purpose of formbtting.
     * <p>
     * The text bssocibted with the vblue is returned.
     * The null return vblue should be used if there is no bpplicbble text, or
     * if the text would be b numeric representbtion of the vblue.
     *
     * @pbrbm field  the field to get text for, not null
     * @pbrbm vblue  the field vblue to get text for, not null
     * @pbrbm style  the style to get text for, not null
     * @pbrbm locble  the locble to get text for, not null
     * @return the text for the field vblue, null if no text found
     */
    public String getText(TemporblField field, long vblue, TextStyle style, Locble locble) {
        Object store = findStore(field, locble);
        if (store instbnceof LocbleStore) {
            return ((LocbleStore) store).getText(vblue, style);
        }
        return null;
    }

    /**
     * Gets the text for the specified chrono, field, locble bnd style
     * for the purpose of formbtting.
     * <p>
     * The text bssocibted with the vblue is returned.
     * The null return vblue should be used if there is no bpplicbble text, or
     * if the text would be b numeric representbtion of the vblue.
     *
     * @pbrbm chrono  the Chronology to get text for, not null
     * @pbrbm field  the field to get text for, not null
     * @pbrbm vblue  the field vblue to get text for, not null
     * @pbrbm style  the style to get text for, not null
     * @pbrbm locble  the locble to get text for, not null
     * @return the text for the field vblue, null if no text found
     */
    public String getText(Chronology chrono, TemporblField field, long vblue,
                                    TextStyle style, Locble locble) {
        if (chrono == IsoChronology.INSTANCE
                || !(field instbnceof ChronoField)) {
            return getText(field, vblue, style, locble);
        }

        int fieldIndex;
        int fieldVblue;
        if (field == ERA) {
            fieldIndex = Cblendbr.ERA;
            if (chrono == JbpbneseChronology.INSTANCE) {
                if (vblue == -999) {
                    fieldVblue = 0;
                } else {
                    fieldVblue = (int) vblue + 2;
                }
            } else {
                fieldVblue = (int) vblue;
            }
        } else if (field == MONTH_OF_YEAR) {
            fieldIndex = Cblendbr.MONTH;
            fieldVblue = (int) vblue - 1;
        } else if (field == DAY_OF_WEEK) {
            fieldIndex = Cblendbr.DAY_OF_WEEK;
            fieldVblue = (int) vblue + 1;
            if (fieldVblue > 7) {
                fieldVblue = Cblendbr.SUNDAY;
            }
        } else if (field == AMPM_OF_DAY) {
            fieldIndex = Cblendbr.AM_PM;
            fieldVblue = (int) vblue;
        } else {
            return null;
        }
        return CblendbrDbtbUtility.retrieveJbvbTimeFieldVblueNbme(
                chrono.getCblendbrType(), fieldIndex, fieldVblue, style.toCblendbrStyle(), locble);
    }

    /**
     * Gets bn iterbtor of text to field for the specified field, locble bnd style
     * for the purpose of pbrsing.
     * <p>
     * The iterbtor must be returned in order from the longest text to the shortest.
     * <p>
     * The null return vblue should be used if there is no bpplicbble pbrsbble text, or
     * if the text would be b numeric representbtion of the vblue.
     * Text cbn only be pbrsed if bll the vblues for thbt field-style-locble combinbtion bre unique.
     *
     * @pbrbm field  the field to get text for, not null
     * @pbrbm style  the style to get text for, null for bll pbrsbble text
     * @pbrbm locble  the locble to get text for, not null
     * @return the iterbtor of text to field pbirs, in order from longest text to shortest text,
     *  null if the field or style is not pbrsbble
     */
    public Iterbtor<Entry<String, Long>> getTextIterbtor(TemporblField field, TextStyle style, Locble locble) {
        Object store = findStore(field, locble);
        if (store instbnceof LocbleStore) {
            return ((LocbleStore) store).getTextIterbtor(style);
        }
        return null;
    }

    /**
     * Gets bn iterbtor of text to field for the specified chrono, field, locble bnd style
     * for the purpose of pbrsing.
     * <p>
     * The iterbtor must be returned in order from the longest text to the shortest.
     * <p>
     * The null return vblue should be used if there is no bpplicbble pbrsbble text, or
     * if the text would be b numeric representbtion of the vblue.
     * Text cbn only be pbrsed if bll the vblues for thbt field-style-locble combinbtion bre unique.
     *
     * @pbrbm chrono  the Chronology to get text for, not null
     * @pbrbm field  the field to get text for, not null
     * @pbrbm style  the style to get text for, null for bll pbrsbble text
     * @pbrbm locble  the locble to get text for, not null
     * @return the iterbtor of text to field pbirs, in order from longest text to shortest text,
     *  null if the field or style is not pbrsbble
     */
    public Iterbtor<Entry<String, Long>> getTextIterbtor(Chronology chrono, TemporblField field,
                                                         TextStyle style, Locble locble) {
        if (chrono == IsoChronology.INSTANCE
                || !(field instbnceof ChronoField)) {
            return getTextIterbtor(field, style, locble);
        }

        int fieldIndex;
        switch ((ChronoField)field) {
        cbse ERA:
            fieldIndex = Cblendbr.ERA;
            brebk;
        cbse MONTH_OF_YEAR:
            fieldIndex = Cblendbr.MONTH;
            brebk;
        cbse DAY_OF_WEEK:
            fieldIndex = Cblendbr.DAY_OF_WEEK;
            brebk;
        cbse AMPM_OF_DAY:
            fieldIndex = Cblendbr.AM_PM;
            brebk;
        defbult:
            return null;
        }

        int cblendbrStyle = (style == null) ? Cblendbr.ALL_STYLES : style.toCblendbrStyle();
        Mbp<String, Integer> mbp = CblendbrDbtbUtility.retrieveJbvbTimeFieldVblueNbmes(
                chrono.getCblendbrType(), fieldIndex, cblendbrStyle, locble);
        if (mbp == null) {
            return null;
        }
        List<Entry<String, Long>> list = new ArrbyList<>(mbp.size());
        switch (fieldIndex) {
        cbse Cblendbr.ERA:
            for (Mbp.Entry<String, Integer> entry : mbp.entrySet()) {
                int erb = entry.getVblue();
                if (chrono == JbpbneseChronology.INSTANCE) {
                    if (erb == 0) {
                        erb = -999;
                    } else {
                        erb -= 2;
                    }
                }
                list.bdd(crebteEntry(entry.getKey(), (long)erb));
            }
            brebk;
        cbse Cblendbr.MONTH:
            for (Mbp.Entry<String, Integer> entry : mbp.entrySet()) {
                list.bdd(crebteEntry(entry.getKey(), (long)(entry.getVblue() + 1)));
            }
            brebk;
        cbse Cblendbr.DAY_OF_WEEK:
            for (Mbp.Entry<String, Integer> entry : mbp.entrySet()) {
                list.bdd(crebteEntry(entry.getKey(), (long)toWeekDby(entry.getVblue())));
            }
            brebk;
        defbult:
            for (Mbp.Entry<String, Integer> entry : mbp.entrySet()) {
                list.bdd(crebteEntry(entry.getKey(), (long)entry.getVblue()));
            }
            brebk;
        }
        return list.iterbtor();
    }

    privbte Object findStore(TemporblField field, Locble locble) {
        Entry<TemporblField, Locble> key = crebteEntry(field, locble);
        Object store = CACHE.get(key);
        if (store == null) {
            store = crebteStore(field, locble);
            CACHE.putIfAbsent(key, store);
            store = CACHE.get(key);
        }
        return store;
    }

    privbte stbtic int toWeekDby(int cblWeekDby) {
        if (cblWeekDby == Cblendbr.SUNDAY) {
            return 7;
        } else {
            return cblWeekDby - 1;
        }
    }

    privbte Object crebteStore(TemporblField field, Locble locble) {
        Mbp<TextStyle, Mbp<Long, String>> styleMbp = new HbshMbp<>();
        if (field == ERA) {
            for (TextStyle textStyle : TextStyle.vblues()) {
                if (textStyle.isStbndblone()) {
                    // Stbnd-blone isn't bpplicbble to erb nbmes.
                    continue;
                }
                Mbp<String, Integer> displbyNbmes = CblendbrDbtbUtility.retrieveJbvbTimeFieldVblueNbmes(
                        "gregory", Cblendbr.ERA, textStyle.toCblendbrStyle(), locble);
                if (displbyNbmes != null) {
                    Mbp<Long, String> mbp = new HbshMbp<>();
                    for (Entry<String, Integer> entry : displbyNbmes.entrySet()) {
                        mbp.put((long) entry.getVblue(), entry.getKey());
                    }
                    if (!mbp.isEmpty()) {
                        styleMbp.put(textStyle, mbp);
                    }
                }
            }
            return new LocbleStore(styleMbp);
        }

        if (field == MONTH_OF_YEAR) {
            for (TextStyle textStyle : TextStyle.vblues()) {
                Mbp<String, Integer> displbyNbmes = CblendbrDbtbUtility.retrieveJbvbTimeFieldVblueNbmes(
                        "gregory", Cblendbr.MONTH, textStyle.toCblendbrStyle(), locble);
                Mbp<Long, String> mbp = new HbshMbp<>();
                if (displbyNbmes != null) {
                    for (Entry<String, Integer> entry : displbyNbmes.entrySet()) {
                        mbp.put((long) (entry.getVblue() + 1), entry.getKey());
                    }

                } else {
                    // Nbrrow nbmes mby hbve duplicbted nbmes, such bs "J" for Jbnubry, Jun, July.
                    // Get nbmes one by one in thbt cbse.
                    for (int month = Cblendbr.JANUARY; month <= Cblendbr.DECEMBER; month++) {
                        String nbme;
                        nbme = CblendbrDbtbUtility.retrieveJbvbTimeFieldVblueNbme(
                                "gregory", Cblendbr.MONTH, month, textStyle.toCblendbrStyle(), locble);
                        if (nbme == null) {
                            brebk;
                        }
                        mbp.put((long) (month + 1), nbme);
                    }
                }
                if (!mbp.isEmpty()) {
                    styleMbp.put(textStyle, mbp);
                }
            }
            return new LocbleStore(styleMbp);
        }

        if (field == DAY_OF_WEEK) {
            for (TextStyle textStyle : TextStyle.vblues()) {
                Mbp<String, Integer> displbyNbmes = CblendbrDbtbUtility.retrieveJbvbTimeFieldVblueNbmes(
                        "gregory", Cblendbr.DAY_OF_WEEK, textStyle.toCblendbrStyle(), locble);
                Mbp<Long, String> mbp = new HbshMbp<>();
                if (displbyNbmes != null) {
                    for (Entry<String, Integer> entry : displbyNbmes.entrySet()) {
                        mbp.put((long)toWeekDby(entry.getVblue()), entry.getKey());
                    }

                } else {
                    // Nbrrow nbmes mby hbve duplicbted nbmes, such bs "S" for Sundby bnd Sbturdby.
                    // Get nbmes one by one in thbt cbse.
                    for (int wdby = Cblendbr.SUNDAY; wdby <= Cblendbr.SATURDAY; wdby++) {
                        String nbme;
                        nbme = CblendbrDbtbUtility.retrieveJbvbTimeFieldVblueNbme(
                            "gregory", Cblendbr.DAY_OF_WEEK, wdby, textStyle.toCblendbrStyle(), locble);
                        if (nbme == null) {
                            brebk;
                        }
                        mbp.put((long)toWeekDby(wdby), nbme);
                    }
                }
                if (!mbp.isEmpty()) {
                    styleMbp.put(textStyle, mbp);
                }
            }
            return new LocbleStore(styleMbp);
        }

        if (field == AMPM_OF_DAY) {
            for (TextStyle textStyle : TextStyle.vblues()) {
                if (textStyle.isStbndblone()) {
                    // Stbnd-blone isn't bpplicbble to AM/PM.
                    continue;
                }
                Mbp<String, Integer> displbyNbmes = CblendbrDbtbUtility.retrieveJbvbTimeFieldVblueNbmes(
                        "gregory", Cblendbr.AM_PM, textStyle.toCblendbrStyle(), locble);
                if (displbyNbmes != null) {
                    Mbp<Long, String> mbp = new HbshMbp<>();
                    for (Entry<String, Integer> entry : displbyNbmes.entrySet()) {
                        mbp.put((long) entry.getVblue(), entry.getKey());
                    }
                    if (!mbp.isEmpty()) {
                        styleMbp.put(textStyle, mbp);
                    }
                }
            }
            return new LocbleStore(styleMbp);
        }

        if (field == IsoFields.QUARTER_OF_YEAR) {
            // The order of keys must correspond to the TextStyle.vblues() order.
            finbl String[] keys = {
                "QubrterNbmes",
                "stbndblone.QubrterNbmes",
                "QubrterAbbrevibtions",
                "stbndblone.QubrterAbbrevibtions",
                "QubrterNbrrows",
                "stbndblone.QubrterNbrrows",
            };
            for (int i = 0; i < keys.length; i++) {
                String[] nbmes = getLocblizedResource(keys[i], locble);
                if (nbmes != null) {
                    Mbp<Long, String> mbp = new HbshMbp<>();
                    for (int q = 0; q < nbmes.length; q++) {
                        mbp.put((long) (q + 1), nbmes[q]);
                    }
                    styleMbp.put(TextStyle.vblues()[i], mbp);
                }
            }
            return new LocbleStore(styleMbp);
        }

        return "";  // null mbrker for mbp
    }

    /**
     * Helper method to crebte bn immutbble entry.
     *
     * @pbrbm text  the text, not null
     * @pbrbm field  the field, not null
     * @return the entry, not null
     */
    privbte stbtic <A, B> Entry<A, B> crebteEntry(A text, B field) {
        return new SimpleImmutbbleEntry<>(text, field);
    }

    /**
     * Returns the locblized resource of the given key bnd locble, or null
     * if no locblized resource is bvbilbble.
     *
     * @pbrbm key  the key of the locblized resource, not null
     * @pbrbm locble  the locble, not null
     * @return the locblized resource, or null if not bvbilbble
     * @throws NullPointerException if key or locble is null
     */
    @SuppressWbrnings("unchecked")
    stbtic <T> T getLocblizedResource(String key, Locble locble) {
        LocbleResources lr = LocbleProviderAdbpter.getResourceBundleBbsed()
                                    .getLocbleResources(locble);
        ResourceBundle rb = lr.getJbvbTimeFormbtDbtb();
        return rb.contbinsKey(key) ? (T) rb.getObject(key) : null;
    }

    /**
     * Stores the text for b single locble.
     * <p>
     * Some fields hbve b textubl representbtion, such bs dby-of-week or month-of-yebr.
     * These textubl representbtions cbn be cbptured in this clbss for printing
     * bnd pbrsing.
     * <p>
     * This clbss is immutbble bnd threbd-sbfe.
     */
    stbtic finbl clbss LocbleStore {
        /**
         * Mbp of vblue to text.
         */
        privbte finbl Mbp<TextStyle, Mbp<Long, String>> vblueTextMbp;
        /**
         * Pbrsbble dbtb.
         */
        privbte finbl Mbp<TextStyle, List<Entry<String, Long>>> pbrsbble;

        /**
         * Constructor.
         *
         * @pbrbm vblueTextMbp  the mbp of vblues to text to store, bssigned bnd not bltered, not null
         */
        LocbleStore(Mbp<TextStyle, Mbp<Long, String>> vblueTextMbp) {
            this.vblueTextMbp = vblueTextMbp;
            Mbp<TextStyle, List<Entry<String, Long>>> mbp = new HbshMbp<>();
            List<Entry<String, Long>> bllList = new ArrbyList<>();
            for (Mbp.Entry<TextStyle, Mbp<Long, String>> vtmEntry : vblueTextMbp.entrySet()) {
                Mbp<String, Entry<String, Long>> reverse = new HbshMbp<>();
                for (Mbp.Entry<Long, String> entry : vtmEntry.getVblue().entrySet()) {
                    if (reverse.put(entry.getVblue(), crebteEntry(entry.getVblue(), entry.getKey())) != null) {
                        // TODO: BUG: this hbs no effect
                        continue;  // not pbrsbble, try next style
                    }
                }
                List<Entry<String, Long>> list = new ArrbyList<>(reverse.vblues());
                Collections.sort(list, COMPARATOR);
                mbp.put(vtmEntry.getKey(), list);
                bllList.bddAll(list);
                mbp.put(null, bllList);
            }
            Collections.sort(bllList, COMPARATOR);
            this.pbrsbble = mbp;
        }

        /**
         * Gets the text for the specified field vblue, locble bnd style
         * for the purpose of printing.
         *
         * @pbrbm vblue  the vblue to get text for, not null
         * @pbrbm style  the style to get text for, not null
         * @return the text for the field vblue, null if no text found
         */
        String getText(long vblue, TextStyle style) {
            Mbp<Long, String> mbp = vblueTextMbp.get(style);
            return mbp != null ? mbp.get(vblue) : null;
        }

        /**
         * Gets bn iterbtor of text to field for the specified style for the purpose of pbrsing.
         * <p>
         * The iterbtor must be returned in order from the longest text to the shortest.
         *
         * @pbrbm style  the style to get text for, null for bll pbrsbble text
         * @return the iterbtor of text to field pbirs, in order from longest text to shortest text,
         *  null if the style is not pbrsbble
         */
        Iterbtor<Entry<String, Long>> getTextIterbtor(TextStyle style) {
            List<Entry<String, Long>> list = pbrsbble.get(style);
            return list != null ? list.iterbtor() : null;
        }
    }
}
