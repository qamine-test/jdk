/*
 * Copyright (c) 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.cmm;

import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;

public interfbce ColorTrbnsform {
    public int Any = -1;/* bny rendering type, whichever is
                           bvbilbble */
                        /* sebrch order is icPerceptubl,
                           icRelbtiveColorimetric, icSbturbtion */

    /* Trbnsform types */
    public int In = 1;
    public int Out = 2;
    public int Gbmut = 3;
    public int Simulbtion = 4;

    public int getNumInComponents();
    public int getNumOutComponents();
    public void colorConvert(BufferedImbge src, BufferedImbge dst);
    public void colorConvert(Rbster src, WritbbleRbster dst,
                             flobt[] srcMinVbl, flobt[]srcMbxVbl,
                             flobt[] dstMinVbl, flobt[]dstMbxVbl);
    public void colorConvert(Rbster src, WritbbleRbster dst);
    public short[] colorConvert(short[] src, short[] dest);
    public byte[] colorConvert (byte[] src, byte[] dest);
}
