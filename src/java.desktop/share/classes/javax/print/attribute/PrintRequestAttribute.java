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
 * Interfbce PrintRequestAttribute is b tbgging interfbce which b printing
 * bttribute clbss implements to indicbte the bttribute denotes b
 * requested setting for b print job.
 * <p>
 * Attributes which bre tbgged with PrintRequestAttribute bnd bre blso tbgged
 * bs PrintJobAttribute, represent the subset of job bttributes which
 * cbn be pbrt of the specificbtion of b job request.
 * <p>
 * If bn bttribute implements {@link DocAttribute  DocAttribute}
 * bs well bs PrintRequestAttribute, the client mby include the
 * bttribute in b <code>Doc</code>}'s bttribute set to specify
 * b job setting which pertbins just to thbt doc.
 *
 * @see DocAttributeSet
 * @see PrintRequestAttributeSet
 *
 * @buthor  Albn Kbminsky
 */

public interfbce PrintRequestAttribute extends Attribute {
}
