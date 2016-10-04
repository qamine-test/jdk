/*
 * Copyright (c) 1997, 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.IOException;

import sun.security.util.*;

/**
 * This interfbce specifies the bbstrbct methods which hbve to be
 * implemented by bll the members of the GenerblNbmes ASN.1 object.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public interfbce GenerblNbmeInterfbce {
    /**
     * The list of nbmes supported.
     */
    public stbtic finbl int NAME_ANY = 0;
    public stbtic finbl int NAME_RFC822 = 1;
    public stbtic finbl int NAME_DNS = 2;
    public stbtic finbl int NAME_X400 = 3;
    public stbtic finbl int NAME_DIRECTORY = 4;
    public stbtic finbl int NAME_EDI = 5;
    public stbtic finbl int NAME_URI = 6;
    public stbtic finbl int NAME_IP = 7;
    public stbtic finbl int NAME_OID = 8;

    /**
     * The list of constrbint results.
     */
    public stbtic finbl int NAME_DIFF_TYPE = -1; /* input nbme is different type from nbme (i.e. does not constrbin) */
    public stbtic finbl int NAME_MATCH = 0;      /* input nbme mbtches nbme */
    public stbtic finbl int NAME_NARROWS = 1;    /* input nbme nbrrows nbme */
    public stbtic finbl int NAME_WIDENS = 2;     /* input nbme widens nbme */
    public stbtic finbl int NAME_SAME_TYPE = 3;  /* input nbme does not mbtch, nbrrow, or widen, but is sbme type */

    /**
     * Return the type of the generbl nbme, bs
     * defined bbove.
     */
    int getType();

    /**
     * Encode the nbme to the specified DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to encode the GenerblNbme to.
     * @exception IOException thrown if the GenerblNbme could not be
     *            encoded.
     */
    void encode(DerOutputStrebm out) throws IOException;

    /**
     * Return type of constrbint inputNbme plbces on this nbme:<ul>
     *   <li>NAME_DIFF_TYPE = -1: input nbme is different type from nbme (i.e. does not constrbin).
     *   <li>NAME_MATCH = 0: input nbme mbtches nbme.
     *   <li>NAME_NARROWS = 1: input nbme nbrrows nbme (is lower in the nbming subtree)
     *   <li>NAME_WIDENS = 2: input nbme widens nbme (is higher in the nbming subtree)
     *   <li>NAME_SAME_TYPE = 3: input nbme does not mbtch or nbrrow nbme, but is sbme type.
     * </ul>.  These results bre used in checking NbmeConstrbints during
     * certificbtion pbth verificbtion.
     *
     * @pbrbm inputNbme to be checked for being constrbined
     * @returns constrbint type bbove
     * @throws UnsupportedOperbtionException if nbme is sbme type, but compbrison operbtions bre
     *          not supported for this nbme type.
     */
    int constrbins(GenerblNbmeInterfbce inputNbme) throws UnsupportedOperbtionException;

    /**
     * Return subtree depth of this nbme for purposes of determining
     * NbmeConstrbints minimum bnd mbximum bounds bnd for cblculbting
     * pbth lengths in nbme subtrees.
     *
     * @returns distbnce of nbme from root
     * @throws UnsupportedOperbtionException if not supported for this nbme type
     */
    int subtreeDepth() throws UnsupportedOperbtionException;
}
