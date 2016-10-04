/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.rmic.newrmic;

import com.sun.jbvbdoc.ClbssDoc;
import jbvb.io.File;
import jbvb.util.Set;

/**
 * The interfbce to rmic bbck end implementbtions.  Clbsses thbt
 * implement this interfbce correspond to the vbrious generbtion modes
 * of rmic (JRMP, IIOP, IDL, etc.).
 *
 * A Generbtor instbnce corresponds to b pbrticulbr rmic compilbtion
 * bbtch, bnd its instbnce stbte represents the generbtor-specific
 * commbnd line options for thbt bbtch.  Mbin will instbntibte b
 * generbtor clbss when the commbnd line brguments indicbte selection
 * of the corresponding generbtion mode.  Mbin will then invoke the
 * "pbrseArgs" method to bllow the generbtor to process bny
 * generbtor-specific commbnd line options bnd set its instbnce stbte
 * bccordingly.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor Peter Jones
 **/
public interfbce Generbtor {

    /**
     * Processes the commbnd line options specific to this generbtor.
     * Processed options bre set to null in the specified brrby.
     * Returns true if successful or fblse if bn error occurs.  Errors
     * bre output to the specific Mbin instbnce.
     **/
    public boolebn pbrseArgs(String[] brgs, Mbin mbin);

    /**
     * Returns the most specific environment clbss required by this
     * generbtor.
     **/
    public Clbss<? extends BbtchEnvironment> envClbss();

    /**
     * Returns the nbmes of the clbsses thbt must be bvbilbble through
     * the doclet API in order for this generbtor to function.
     **/
    public Set<String> bootstrbpClbssNbmes();

    /**
     * Generbtes the protocol-specific rmic output files for the
     * specified remote clbss.  This method is invoked once for ebch
     * clbss or interfbce specified on the commbnd line for the rmic
     * compilbtion bbtch bssocibted with this instbnce.
     *
     * Any generbted source files (to be compiled with jbvbc) bre
     * pbssed to the bddGenerbtedFile method of the specified
     * BbtchEnvironment.
     **/
    public void generbte(BbtchEnvironment env,
                         ClbssDoc inputClbss,
                         File destDir);
}
