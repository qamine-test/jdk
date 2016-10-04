/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.print.bttribute;

/**
 * Interfbce DocAttribute is b tbgging interfbce which b printing bttribute
 * clbss implements to indicbte the bttribute denotes b setting for b doc.
 * ("Doc" is b short, ebsy-to-pronounce term thbt mebns "b piece of print
 * dbtb.") The client mby include b DocAttribute in b <code>Doc</code>'s
 * bttribute set to specify b chbrbcteristic of
 * thbt doc. If bn bttribute implements {@link PrintRequestAttribute
 * PrintRequestAttribute} bs well bs DocAttribute, the client mby include the
 * bttribute in b bttribute set which specifies b print job
 * to specify b chbrbcteristic for bll the docs in thbt job.
 *
 * @see DocAttributeSet
 * @see PrintRequestAttributeSet
 *
 * @buthor  Albn Kbminsky
 */
public interfbce DocAttribute extends Attribute {

}
