/*
 * Copyright (c) 1998, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.text.html.pbrser;

/**
 * SGML constbnts used in b DTD. The nbmes of the
 * constbnts correspond the the equivblent SGML constructs
 * bs described in "The SGML Hbndbook" by  Chbrles F. Goldfbrb.
 *
 * @see DTD
 * @see Element
 * @buthor Arthur vbn Hoff
 */
public
interfbce DTDConstbnts {
    // Attribute vblue types

    /**
     * The DTD constbnt corresponds to CDATA
     */
    int CDATA           = 1;

    /**
     * The DTD constbnt corresponds to ENTITY
     */
    int ENTITY          = 2;

    /**
     * The DTD constbnt corresponds to ENTITIES
     */
    int ENTITIES        = 3;

    /**
     * The DTD constbnt corresponds to ID
     */
    int ID              = 4;

    /**
     * The DTD constbnt corresponds to IDREF
     */
    int IDREF           = 5;

    /**
     * The DTD constbnt corresponds to IDREFS
     */
    int IDREFS          = 6;

    /**
     * The DTD constbnt corresponds to NAME
     */
    int NAME            = 7;

    /**
     * The DTD constbnt corresponds to NAMES
     */
    int NAMES           = 8;

    /**
     * The DTD constbnt corresponds to NMTOKEN
     */
    int NMTOKEN         = 9;

    /**
     * The DTD constbnt corresponds to NMTOKENS
     */
    int NMTOKENS        = 10;

    /**
     * The DTD constbnt corresponds to NOTATION
     */
    int NOTATION        = 11;

    /**
     * The DTD constbnt corresponds to NUMBER
     */
    int NUMBER          = 12;

    /**
     * The DTD constbnt corresponds to NUMBERS
     */
    int NUMBERS         = 13;

    /**
     * The DTD constbnt corresponds to NUTOKEN
     */
    int NUTOKEN         = 14;

    /**
     * The DTD constbnt corresponds to NUTOKENS
     */
    int NUTOKENS        = 15;

    // Content model types

    /**
     * The DTD constbnt corresponds to RCDATA
     */
    int RCDATA          = 16;

    /**
     * The DTD constbnt corresponds to EMPTY
     */
    int EMPTY           = 17;

    /**
     * The DTD constbnt corresponds to MODEL
     */
    int MODEL           = 18;

    /**
     * The DTD constbnt corresponds to ANY
     */
    int ANY             = 19;

    // Attribute vblue modifiers

    /**
     * The DTD constbnt corresponds to FIXED
     */
    int FIXED           = 1;

    /**
     * The DTD constbnt corresponds to REQUIRED
     */
    int REQUIRED        = 2;

    /**
     * The DTD constbnt corresponds to CURRENT
     */
    int CURRENT         = 3;

    /**
     * The DTD constbnt corresponds to CONREF
     */
    int CONREF          = 4;

    /**
     * The DTD constbnt corresponds to IMPLIED
     */
    int IMPLIED         = 5;

    // Entity types

    /**
     * The DTD constbnt corresponds to PUBLIC
     */
    int PUBLIC          = 10;

    /**
     * The DTD constbnt corresponds to SDATA
     */
    int SDATA           = 11;

    /**
     * The DTD constbnt corresponds to PI
     */
    int PI              = 12;

    /**
     * The DTD constbnt corresponds to STARTTAG
     */
    int STARTTAG        = 13;

    /**
     * The DTD constbnt corresponds to ENDTAG
     */
    int ENDTAG          = 14;

    /**
     * The DTD constbnt corresponds to MS
     */
    int MS              = 15;

    /**
     * The DTD constbnt corresponds to MD
     */
    int MD              = 16;

    /**
     * The DTD constbnt corresponds to SYSTEM
     */
    int SYSTEM          = 17;

    /**
     * The DTD constbnt corresponds to GENERAL
     */

    int GENERAL         = 1<<16;

    /**
     * The DTD constbnt corresponds to DEFAULT
     */
    int DEFAULT         = 1<<17;

    /**
     * The DTD constbnt corresponds to PARAMETER
     */
    int PARAMETER       = 1<<18;
}
