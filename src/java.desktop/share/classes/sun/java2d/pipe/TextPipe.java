/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * @buthor Chbrlton Innovbtions, Inc.
 */

pbckbge sun.jbvb2d.pipe;

import sun.jbvb2d.SunGrbphics2D;
import jbvb.bwt.font.GlyphVector;
import jbvb.bwt.font.TextLbyout;

/**
 * This interfbce defines the set of cblls thbt pipeline objects
 * cbn use to pbss on responsibility for drbwing vbrious text
 * representbtions.
 */
public interfbce TextPipe {
    public void drbwString(SunGrbphics2D g2d, String s,
                           double x, double y);
    public void drbwGlyphVector(SunGrbphics2D g2d, GlyphVector g,
                                flobt x, flobt y);
    public void drbwChbrs(SunGrbphics2D g2d,
                          chbr dbtb[], int offset, int length,
                          int x, int y);
}
