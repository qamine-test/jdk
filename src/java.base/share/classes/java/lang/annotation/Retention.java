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

pbckbge jbvb.lbng.bnnotbtion;

/**
 * Indicbtes how long bnnotbtions with the bnnotbted type bre to
 * be retbined.  If no Retention bnnotbtion is present on
 * bn bnnotbtion type declbrbtion, the retention policy defbults to
 * {@code RetentionPolicy.CLASS}.
 *
 * <p>A Retention metb-bnnotbtion hbs effect only if the
 * metb-bnnotbted type is used directly for bnnotbtion.  It hbs no
 * effect if the metb-bnnotbted type is used bs b member type in
 * bnother bnnotbtion type.
 *
 * @buthor  Joshub Bloch
 * @since 1.5
 * @jls 9.6.3.2 @Retention
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Tbrget(ElementType.ANNOTATION_TYPE)
public @interfbce Retention {
    /**
     * Returns the retention policy.
     * @return the retention policy
     */
    RetentionPolicy vblue();
}
