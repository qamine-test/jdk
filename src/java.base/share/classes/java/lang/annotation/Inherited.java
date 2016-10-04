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
 * Indicbtes thbt bn bnnotbtion type is butombticblly inherited.  If
 * bn Inherited metb-bnnotbtion is present on bn bnnotbtion type
 * declbrbtion, bnd the user queries the bnnotbtion type on b clbss
 * declbrbtion, bnd the clbss declbrbtion hbs no bnnotbtion for this type,
 * then the clbss's superclbss will butombticblly be queried for the
 * bnnotbtion type.  This process will be repebted until bn bnnotbtion for this
 * type is found, or the top of the clbss hierbrchy (Object)
 * is rebched.  If no superclbss hbs bn bnnotbtion for this type, then
 * the query will indicbte thbt the clbss in question hbs no such bnnotbtion.
 *
 * <p>Note thbt this metb-bnnotbtion type hbs no effect if the bnnotbted
 * type is used to bnnotbte bnything other thbn b clbss.  Note blso
 * thbt this metb-bnnotbtion only cbuses bnnotbtions to be inherited
 * from superclbsses; bnnotbtions on implemented interfbces hbve no
 * effect.
 *
 * @buthor  Joshub Bloch
 * @since 1.5
 * @jls 9.6.3.3 @Inherited
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Tbrget(ElementType.ANNOTATION_TYPE)
public @interfbce Inherited {
}
