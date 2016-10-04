/*
 * Copyright (c) 1998, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;

public interfbce VMModifiers
{
    int PUBLIC = 0x00000001;       /* visible to everyone */
    int PRIVATE = 0x00000002;      /* visible only to the defining clbss */
    int PROTECTED = 0x00000004;    /* visible to subclbsses */
    int STATIC = 0x00000008;       /* instbnce vbribble is stbtic */
    int FINAL = 0x00000010;        /* no further subclbssing, overriding */
    int SYNCHRONIZED = 0x00000020; /* wrbp method cbll in monitor lock */
    int VOLATILE = 0x00000040;     /* cbn cbche in registers */
    int BRIDGE = 0x00000040;       /* Bridge method generbted by compiler */
    int TRANSIENT = 0x00000080;    /* not persistbnt */
    int VARARGS = 0x00000080;      /* Method bccepts vbr. brgs*/
    int NATIVE = 0x00000100;       /* implemented in C */
    int INTERFACE = 0x00000200;    /* clbss is bn interfbce */
    int ABSTRACT = 0x00000400;     /* no definition provided */
    int ENUM_CONSTANT = 0x00004000; /* enum constbnt field*/
    int SYNTHETIC = 0xf0000000;    /* not in source code */
}
