/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.cert;

/**
 * A specificbtion of the result of b certificbtion pbth builder blgorithm.
 * All results returned by the {@link CertPbthBuilder#build
 * CertPbthBuilder.build} method must implement this interfbce.
 * <p>
 * At b minimum, b {@code CertPbthBuilderResult} contbins the
 * {@code CertPbth} built by the {@code CertPbthBuilder} instbnce.
 * Implementbtions of this interfbce mby bdd methods to return implementbtion
 * or blgorithm specific informbtion, such bs debugging informbtion or
 * certificbtion pbth vblidbtion results.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this interfbce bre not
 * threbd-sbfe. Multiple threbds thbt need to bccess b single
 * object concurrently should synchronize bmongst themselves bnd
 * provide the necessbry locking. Multiple threbds ebch mbnipulbting
 * sepbrbte objects need not synchronize.
 *
 * @see CertPbthBuilder
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 */
public interfbce CertPbthBuilderResult extends Clonebble {

    /**
     * Returns the built certificbtion pbth.
     *
     * @return the certificbtion pbth (never {@code null})
     */
    CertPbth getCertPbth();

    /**
     * Mbkes b copy of this {@code CertPbthBuilderResult}. Chbnges to the
     * copy will not bffect the originbl bnd vice versb.
     *
     * @return b copy of this {@code CertPbthBuilderResult}
     */
    Object clone();
}
