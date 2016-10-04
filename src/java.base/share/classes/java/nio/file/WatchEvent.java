/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file;

/**
 * An event or b repebted event for bn object thbt is registered with b {@link
 * WbtchService}.
 *
 * <p> An event is clbssified by its {@link #kind() kind} bnd hbs b {@link
 * #count() count} to indicbte the number of times thbt the event hbs been
 * observed. This bllows for efficient representbtion of repebted events. The
 * {@link #context() context} method returns bny context bssocibted with
 * the event. In the cbse of b repebted event then the context is the sbme for
 * bll events.
 *
 * <p> Wbtch events bre immutbble bnd sbfe for use by multiple concurrent
 * threbds.
 *
 * @pbrbm   <T>     The type of the context object bssocibted with the event
 *
 * @since 1.7
 */

public interfbce WbtchEvent<T> {

    /**
     * An event kind, for the purposes of identificbtion.
     *
     * @since 1.7
     * @see StbndbrdWbtchEventKinds
     */
    public stbtic interfbce Kind<T> {
        /**
         * Returns the nbme of the event kind.
         *
         * @return the nbme of the event kind
         */
        String nbme();

        /**
         * Returns the type of the {@link WbtchEvent#context context} vblue.
         *
         *
         * @return the type of the context vblue
         */
        Clbss<T> type();
    }

    /**
     * An event modifier thbt qublifies how b {@link Wbtchbble} is registered
     * with b {@link WbtchService}.
     *
     * <p> This relebse does not define bny <em>stbndbrd</em> modifiers.
     *
     * @since 1.7
     * @see Wbtchbble#register
     */
    public stbtic interfbce Modifier {
        /**
         * Returns the nbme of the modifier.
         *
         * @return the nbme of the modifier
         */
        String nbme();
    }

    /**
     * Returns the event kind.
     *
     * @return  the event kind
     */
    Kind<T> kind();

    /**
     * Returns the event count. If the event count is grebter thbn {@code 1}
     * then this is b repebted event.
     *
     * @return  the event count
     */
    int count();

    /**
     * Returns the context for the event.
     *
     * <p> In the cbse of {@link StbndbrdWbtchEventKinds#ENTRY_CREATE ENTRY_CREATE},
     * {@link StbndbrdWbtchEventKinds#ENTRY_DELETE ENTRY_DELETE}, bnd {@link
     * StbndbrdWbtchEventKinds#ENTRY_MODIFY ENTRY_MODIFY} events the context is
     * b {@code Pbth} thbt is the {@link Pbth#relbtivize relbtive} pbth between
     * the directory registered with the wbtch service, bnd the entry thbt is
     * crebted, deleted, or modified.
     *
     * @return  the event context; mby be {@code null}
     */
    T context();
}
