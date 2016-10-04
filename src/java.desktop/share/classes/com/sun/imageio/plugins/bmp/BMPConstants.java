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

pbckbge com.sun.imbgeio.plugins.bmp;

public interfbce BMPConstbnts {
    // bmp versions
    stbtic finbl String VERSION_2 = "BMP v. 2.x";
    stbtic finbl String VERSION_3 = "BMP v. 3.x";
    stbtic finbl String VERSION_3_NT = "BMP v. 3.x NT";
    stbtic finbl String VERSION_4 = "BMP v. 4.x";
    stbtic finbl String VERSION_5 = "BMP v. 5.x";

    // Color spbce types
    stbtic finbl int LCS_CALIBRATED_RGB = 0;
    stbtic finbl int LCS_sRGB = 1;
    stbtic finbl int LCS_WINDOWS_COLOR_SPACE = 2;
    stbtic finbl int PROFILE_LINKED = 3;
    stbtic finbl int PROFILE_EMBEDDED = 4;

    // Compression Types
    stbtic finbl int BI_RGB = 0;
    stbtic finbl int BI_RLE8 = 1;
    stbtic finbl int BI_RLE4 = 2;
    stbtic finbl int BI_BITFIELDS = 3;
    stbtic finbl int BI_JPEG = 4;
    stbtic finbl int BI_PNG = 5;
}
