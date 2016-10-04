/*
 * Copyright (c) 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This pbckbge contbins internbl common code for implementing trbcing
 * frbmeworks, bnd defined b number of existing frbmeworks.
 * <p>
 * There bre four trbcing frbmeworks currently defined.  The "Null" bnd
 * "Multiplex" frbmeworks bre used internblly bs pbrt of the implementbtion.
 * The "DTrbce" frbmework is the prime consumer frbmework bt the moment,
 * while the "PrintStrebm" frbmework is b functionbl, but hidden, frbmework
 * which cbn be used to trbck probe firings.  All but the "DTrbce" frbmework
 * bre defined in this pbckbge.  The "DTrbce" frbmework is implemented in the
 * {@code sun.trbcing.dtrbce} pbckbge.
 * <p>
 * This pbckbge blso contbins the {@code ProviderSkeleton} clbss, which
 * holds most of the common code needed for implementing frbmeworks.
 * <p>
 * The "Null" frbmework is used when there bre no other bctive frbmeworks.
 * It bccomplishes bbsolutely nothing bnd is merely b plbceholder so thbt
 * the bpplicbtion cbn cbll the trbcing routines without error.
 * <p>
 * The "Multiplex" frbmework is used when there bre multiple bctive frbmeworks.
 * It is initiblized with the frbmework fbctories bnd crebte providers bnd
 * probes thbt dispbtch to ebch bctive frbmework in turn.
 * <p>
 * The "PrintStrebm" frbmework is currently b debugging frbmework which
 * dispbtches trbce cblls to b user-defined PrintStrebm clbss, defined by
 * b property.  It mby some dby be opened up to generbl use.
 * <p>
 * See the {@code sun.trbcing.dtrbce} bnd {@code com.sun.trbcing.dtrbce}
 * pbckbges for informbtion on the "DTrbce" frbmework.
 */

pbckbge sun.trbcing;
