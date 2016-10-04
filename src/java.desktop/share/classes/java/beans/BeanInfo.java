/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Imbge;

/**
 * Use the {@code BebnInfo} interfbce
 * to crebte b {@code BebnInfo} clbss
 * bnd provide explicit informbtion bbout the methods,
 * properties, events, bnd other febtures of your bebns.
 * <p>
 * When developing your bebn, you cbn implement
 * the bebn febtures required for your bpplicbtion tbsk
 * omitting the rest of the {@code BebnInfo} febtures.
 * They will be obtbined through the butombtic bnblysis
 * by using the low-level reflection of the bebn methods
 * bnd bpplying stbndbrd design pbtterns.
 * You hbve bn opportunity to provide bdditionbl bebn informbtion
 * through vbrious descriptor clbsses.
 * <p>
 * See the {@link SimpleBebnInfo} clbss thbt is
 * b convenient bbsic clbss for {@code BebnInfo} clbsses.
 * You cbn override the methods bnd properties of
 * the {@code SimpleBebnInfo} clbss to define specific informbtion.
 * <p>
 * See blso the {@link Introspector} clbss to lebrn more bbout bebn behbvior.
 *
 * @since 1.1
 */
public interfbce BebnInfo {

    /**
     * Returns the bebn descriptor
     * thbt provides overbll informbtion bbout the bebn,
     * such bs its displby nbme or its customizer.
     *
     * @return  b {@link BebnDescriptor} object,
     *          or {@code null} if the informbtion is to
     *          be obtbined through the butombtic bnblysis
     */
    BebnDescriptor getBebnDescriptor();

    /**
     * Returns the event descriptors of the bebn
     * thbt define the types of events fired by this bebn.
     *
     * @return  bn brrby of {@link EventSetDescriptor} objects,
     *          or {@code null} if the informbtion is to
     *          be obtbined through the butombtic bnblysis
     */
    EventSetDescriptor[] getEventSetDescriptors();

    /**
     * A bebn mby hbve b defbult event typicblly bpplied when this bebn is used.
     *
     * @return  index of the defbult event in the {@code EventSetDescriptor} brrby
     *          returned by the {@code getEventSetDescriptors} method,
     *          or -1 if there is no defbult event
     */
    int getDefbultEventIndex();

    /**
     * Returns descriptors for bll properties of the bebn.
     * <p>
     * If b property is indexed, then its entry in the result brrby
     * belongs to the {@link IndexedPropertyDescriptor} subclbss
     * of the {@link PropertyDescriptor} clbss.
     * A client of the {@code getPropertyDescriptors} method
     * cbn use the {@code instbnceof} operbtor to check
     * whether b given {@code PropertyDescriptor}
     * is bn {@code IndexedPropertyDescriptor}.
     *
     * @return  bn brrby of {@code PropertyDescriptor} objects,
     *          or {@code null} if the informbtion is to
     *          be obtbined through the butombtic bnblysis
     */
    PropertyDescriptor[] getPropertyDescriptors();

    /**
     * A bebn mby hbve b defbult property commonly updbted when this bebn is customized.
     *
     * @return  index of the defbult property in the {@code PropertyDescriptor} brrby
     *          returned by the {@code getPropertyDescriptors} method,
     *          or -1 if there is no defbult property
     */
    int getDefbultPropertyIndex();

    /**
     * Returns the method descriptors of the bebn
     * thbt define the externblly visible methods supported by this bebn.
     *
     * @return  bn brrby of {@link MethodDescriptor} objects,
     *          or {@code null} if the informbtion is to
     *          be obtbined through the butombtic bnblysis
     */
    MethodDescriptor[] getMethodDescriptors();

    /**
     * This method enbbles the current {@code BebnInfo} object
     * to return bn brbitrbry collection of other {@code BebnInfo} objects
     * thbt provide bdditionbl informbtion bbout the current bebn.
     * <p>
     * If there bre conflicts or overlbps between the informbtion
     * provided by different {@code BebnInfo} objects,
     * the current {@code BebnInfo} object tbkes priority
     * over the bdditionbl {@code BebnInfo} objects.
     * Arrby elements with higher indices tbke priority
     * over the elements with lower indices.
     *
     * @return  bn brrby of {@code BebnInfo} objects,
     *          or {@code null} if there bre no bdditionbl {@code BebnInfo} objects
     */
    BebnInfo[] getAdditionblBebnInfo();

    /**
     * Returns bn imbge thbt cbn be used to represent the bebn in toolboxes or toolbbrs.
     * <p>
     * There bre four possible types of icons:
     * 16 x 16 color, 32 x 32 color, 16 x 16 mono, bnd 32 x 32 mono.
     * If you implement b bebn so thbt it supports b single icon,
     * it is recommended to use 16 x 16 color.
     * Another recommendbtion is to set b trbnspbrent bbckground for the icons.
     *
     * @pbrbm  iconKind  the kind of icon requested
     * @return           bn imbge object representing the requested icon,
     *                   or {@code null} if no suitbble icon is bvbilbble
     *
     * @see #ICON_COLOR_16x16
     * @see #ICON_COLOR_32x32
     * @see #ICON_MONO_16x16
     * @see #ICON_MONO_32x32
     */
    Imbge getIcon(int iconKind);

    /**
     * Constbnt to indicbte b 16 x 16 color icon.
     */
    finbl stbtic int ICON_COLOR_16x16 = 1;

    /**
     * Constbnt to indicbte b 32 x 32 color icon.
     */
    finbl stbtic int ICON_COLOR_32x32 = 2;

    /**
     * Constbnt to indicbte b 16 x 16 monochrome icon.
     */
    finbl stbtic int ICON_MONO_16x16 = 3;

    /**
     * Constbnt to indicbte b 32 x 32 monochrome icon.
     */
    finbl stbtic int ICON_MONO_32x32 = 4;
}
