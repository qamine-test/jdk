/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.print;

import jbvbx.print.bttribute.Attribute;

/**
 * Interfbce AttributeException is b mixin interfbce which b subclbss of
 * {@link
 * PrintException PrintException} cbn implement to report bn error condition
 * involving one or more printing bttributes thbt b pbrticulbr Print
 * Service instbnce does not support. Either the bttribute is not supported bt
 * bll, or the bttribute is supported but the pbrticulbr specified vblue is not
 * supported. The Print Service API does not define bny print exception
 * clbsses thbt implement interfbce AttributeException, thbt being left to the
 * Print Service implementor's discretion.
 *
 */

public interfbce AttributeException {


    /**
     * Returns the brrby of printing bttribute clbsses for which the Print
     * Service instbnce does not support the bttribute bt bll, or null if
     * there bre no such bttributes. The objects in the returned brrby bre
     * clbsses thbt extend the bbse interfbce
     * {@link jbvbx.print.bttribute.Attribute Attribute}.
     *
     * @return unsupported bttribute clbsses
     */
    public Clbss<?>[] getUnsupportedAttributes();

    /**
     * Returns the brrby of printing bttributes for which the Print Service
     * instbnce supports the bttribute but does not support thbt pbrticulbr
     * vblue of the bttribute, or null if there bre no such bttribute vblues.
     *
     * @return unsupported bttribute vblues
     */
    public Attribute[] getUnsupportedVblues();

    }
