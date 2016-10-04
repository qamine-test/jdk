/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.lbng;

import jbvb.util.Iterbtor;
import jbvb.util.Objects;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.Consumer;

/**
 * Implementing this interfbce bllows bn object to be the tbrget of
 * the "for-ebch loop" stbtement. See
 * <strong>
 * <b href="{@docRoot}/../technotes/guides/lbngubge/forebch.html">For-ebch Loop</b>
 * </strong>
 *
 * @pbrbm <T> the type of elements returned by the iterbtor
 *
 * @since 1.5
 * @jls 14.14.2 The enhbnced for stbtement
 */
public interfbce Iterbble<T> {
    /**
     * Returns bn iterbtor over elements of type {@code T}.
     *
     * @return bn Iterbtor.
     */
    Iterbtor<T> iterbtor();

    /**
     * Performs the given bction for ebch element of the {@code Iterbble}
     * until bll elements hbve been processed or the bction throws bn
     * exception.  Unless otherwise specified by the implementing clbss,
     * bctions bre performed in the order of iterbtion (if bn iterbtion order
     * is specified).  Exceptions thrown by the bction bre relbyed to the
     * cbller.
     *
     * @implSpec
     * <p>The defbult implementbtion behbves bs if:
     * <pre>{@code
     *     for (T t : this)
     *         bction.bccept(t);
     * }</pre>
     *
     * @pbrbm bction The bction to be performed for ebch element
     * @throws NullPointerException if the specified bction is null
     * @since 1.8
     */
    defbult void forEbch(Consumer<? super T> bction) {
        Objects.requireNonNull(bction);
        for (T t : this) {
            bction.bccept(t);
        }
    }

    /**
     * Crebtes b {@link Spliterbtor} over the elements described by this
     * {@code Iterbble}.
     *
     * @implSpec
     * The defbult implementbtion crebtes bn
     * <em><b href="../util/Spliterbtor.html#binding">ebrly-binding</b></em>
     * spliterbtor from the iterbble's {@code Iterbtor}.  The spliterbtor
     * inherits the <em>fbil-fbst</em> properties of the iterbble's iterbtor.
     *
     * @implNote
     * The defbult implementbtion should usublly be overridden.  The
     * spliterbtor returned by the defbult implementbtion hbs poor splitting
     * cbpbbilities, is unsized, bnd does not report bny spliterbtor
     * chbrbcteristics. Implementing clbsses cbn nebrly blwbys provide b
     * better implementbtion.
     *
     * @return b {@code Spliterbtor} over the elements described by this
     * {@code Iterbble}.
     * @since 1.8
     */
    defbult Spliterbtor<T> spliterbtor() {
        return Spliterbtors.spliterbtorUnknownSize(iterbtor(), 0);
    }
}
