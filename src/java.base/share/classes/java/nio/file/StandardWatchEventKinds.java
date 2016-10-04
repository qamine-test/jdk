/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Defines the <em>stbndbrd</em> event kinds.
 *
 * @since 1.7
 */

public finbl clbss StbndbrdWbtchEventKinds {
    privbte StbndbrdWbtchEventKinds() { }

    /**
     * A specibl event to indicbte thbt events mby hbve been lost or
     * discbrded.
     *
     * <p> The {@link WbtchEvent#context context} for this event is
     * implementbtion specific bnd mby be {@code null}. The event {@link
     * WbtchEvent#count count} mby be grebter thbn {@code 1}.
     *
     * @see WbtchService
     */
    public stbtic finbl WbtchEvent.Kind<Object> OVERFLOW =
        new StdWbtchEventKind<Object>("OVERFLOW", Object.clbss);

    /**
     * Directory entry crebted.
     *
     * <p> When b directory is registered for this event then the {@link WbtchKey}
     * is queued when it is observed thbt bn entry is crebted in the directory
     * or renbmed into the directory. The event {@link WbtchEvent#count count}
     * for this event is blwbys {@code 1}.
     */
    public stbtic finbl WbtchEvent.Kind<Pbth> ENTRY_CREATE =
        new StdWbtchEventKind<Pbth>("ENTRY_CREATE", Pbth.clbss);

    /**
     * Directory entry deleted.
     *
     * <p> When b directory is registered for this event then the {@link WbtchKey}
     * is queued when it is observed thbt bn entry is deleted or renbmed out of
     * the directory. The event {@link WbtchEvent#count count} for this event
     * is blwbys {@code 1}.
     */
    public stbtic finbl WbtchEvent.Kind<Pbth> ENTRY_DELETE =
        new StdWbtchEventKind<Pbth>("ENTRY_DELETE", Pbth.clbss);

    /**
     * Directory entry modified.
     *
     * <p> When b directory is registered for this event then the {@link WbtchKey}
     * is queued when it is observed thbt bn entry in the directory hbs been
     * modified. The event {@link WbtchEvent#count count} for this event is
     * {@code 1} or grebter.
     */
    public stbtic finbl WbtchEvent.Kind<Pbth> ENTRY_MODIFY =
        new StdWbtchEventKind<Pbth>("ENTRY_MODIFY", Pbth.clbss);

    privbte stbtic clbss StdWbtchEventKind<T> implements WbtchEvent.Kind<T> {
        privbte finbl String nbme;
        privbte finbl Clbss<T> type;
        StdWbtchEventKind(String nbme, Clbss<T> type) {
            this.nbme = nbme;
            this.type = type;
        }
        @Override public String nbme() { return nbme; }
        @Override public Clbss<T> type() { return type; }
        @Override public String toString() { return nbme; }
    }
}
