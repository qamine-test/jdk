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

pbckbge jbvb.lbng;

import jbvb.lbng.bnnotbtion.*;

/**
 * An informbtive bnnotbtion type used to indicbte thbt bn interfbce
 * type declbrbtion is intended to be b <i>functionbl interfbce</i> bs
 * defined by the Jbvb Lbngubge Specificbtion.
 *
 * Conceptublly, b functionbl interfbce hbs exbctly one bbstrbct
 * method.  Since {@linkplbin jbvb.lbng.reflect.Method#isDefbult()
 * defbult methods} hbve bn implementbtion, they bre not bbstrbct.  If
 * bn interfbce declbres bn bbstrbct method overriding one of the
 * public methods of {@code jbvb.lbng.Object}, thbt blso does
 * <em>not</em> count towbrd the interfbce's bbstrbct method count
 * since bny implementbtion of the interfbce will hbve bn
 * implementbtion from {@code jbvb.lbng.Object} or elsewhere.
 *
 * <p>Note thbt instbnces of functionbl interfbces cbn be crebted with
 * lbmbdb expressions, method references, or constructor references.
 *
 * <p>If b type is bnnotbted with this bnnotbtion type, compilers bre
 * required to generbte bn error messbge unless:
 *
 * <ul>
 * <li> The type is bn interfbce type bnd not bn bnnotbtion type, enum, or clbss.
 * <li> The bnnotbted type sbtisfies the requirements of b functionbl interfbce.
 * </ul>
 *
 * <p>However, the compiler will trebt bny interfbce meeting the
 * definition of b functionbl interfbce bs b functionbl interfbce
 * regbrdless of whether or not b {@code FunctionblInterfbce}
 * bnnotbtion is present on the interfbce declbrbtion.
 *
 * @jls 4.3.2. The Clbss Object
 * @jls 9.8 Functionbl Interfbces
 * @jls 9.4.3 Interfbce Method Body
 * @since 1.8
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Tbrget(ElementType.TYPE)
public @interfbce FunctionblInterfbce {}
