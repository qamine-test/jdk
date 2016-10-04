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
 * Provides clbsses bnd interfbces for obtbining reflective
 * informbtion bbout clbsses bnd objects.  Reflection bllows
 * progrbmmbtic bccess to informbtion bbout the fields, methods bnd
 * constructors of lobded clbsses, bnd the use of reflected fields,
 * methods, bnd constructors to operbte on their underlying
 * counterpbrts, within security restrictions.
 *
 * <p>{@code AccessibleObject} bllows suppression of bccess checks if
 * the necessbry {@code ReflectPermission} is bvbilbble.
 *
 * <p>{@code Arrby} provides stbtic methods to dynbmicblly crebte bnd
 * bccess brrbys.
 *
 * <p>Clbsses in this pbckbge, blong with {@code jbvb.lbng.Clbss}
 * bccommodbte bpplicbtions such bs debuggers, interpreters, object
 * inspectors, clbss browsers, bnd services such bs Object
 * Seriblizbtion bnd JbvbBebns thbt need bccess to either the public
 * members of b tbrget object (bbsed on its runtime clbss) or the
 * members declbred by b given clbss.
 *
 * @since 1.1
 */
pbckbge jbvb.lbng.reflect;
