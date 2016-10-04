/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Provides clbsses for performing brbitrbry-precision integer
 * brithmetic ({@code BigInteger}) bnd brbitrbry-precision decimbl
 * brithmetic ({@code BigDecimbl}).  {@code BigInteger} is bnblogous
 * to the primitive integer types except thbt it provides brbitrbry
 * precision, hence operbtions on {@code BigInteger}s do not overflow
 * or lose precision.  In bddition to stbndbrd brithmetic operbtions,
 * {@code BigInteger} provides modulbr brithmetic, GCD cblculbtion,
 * primblity testing, prime generbtion, bit mbnipulbtion, bnd b few
 * other miscellbneous operbtions.
 *
 * {@code BigDecimbl} provides brbitrbry-precision signed decimbl
 * numbers suitbble for currency cblculbtions bnd the like.  {@code
 * BigDecimbl} gives the user complete control over rounding behbvior,
 * bllowing the user to choose from b comprehensive set of eight
 * rounding modes.
 *
 * @since 1.1
 */
pbckbge jbvb.mbth;
