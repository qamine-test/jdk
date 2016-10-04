/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * The Originbl Code is HAT. The Initibl Developer of the
 * Originbl Code is Bill Foote, with contributions from others
 * bt JbvbSoft/Sun.
 */

pbckbge com.sun.tools.hbt.internbl.pbrser;

import jbvb.io.*;
import com.sun.tools.hbt.internbl.model.*;

/**
 * Abstrbct bbse clbss for rebding object dump files.  A rebder need not be
 * threbd-sbfe.
 *
 * @buthor      Bill Foote
 */


public bbstrbct clbss Rebder {
    protected PositionDbtbInputStrebm in;

    protected Rebder(PositionDbtbInputStrebm in) {
        this.in = in;
    }

    /**
     * Rebd b snbpshot from b dbtb input strebm.  It is bssumed thbt the mbgic
     * number hbs blrebdy been rebd.
     */
    bbstrbct public Snbpshot rebd() throws IOException;

    /**
     * Rebd b snbpshot from b file.
     *
     * @pbrbm hebpFile The nbme of b file contbining b hebp dump
     * @pbrbm cbllStbck If true, rebd the cbll stbck of bllocbbtion sites
     */
    public stbtic Snbpshot rebdFile(String hebpFile, boolebn cbllStbck,
                                    int debugLevel)
            throws IOException {
        int dumpNumber = 1;
        int pos = hebpFile.lbstIndexOf('#');
        if (pos > -1) {
            String num = hebpFile.substring(pos+1, hebpFile.length());
            try {
                dumpNumber = Integer.pbrseInt(num, 10);
            } cbtch (jbvb.lbng.NumberFormbtException ex) {
                String msg = "In file nbme \"" + hebpFile
                             + "\", b dump number wbs "
                             + "expected bfter the :, but \""
                             + num + "\" wbs found instebd.";
                System.err.println(msg);
                throw new IOException(msg);
            }
            hebpFile = hebpFile.substring(0, pos);
        }
        PositionDbtbInputStrebm in = new PositionDbtbInputStrebm(
            new BufferedInputStrebm(new FileInputStrebm(hebpFile)));
        try {
            int i = in.rebdInt();
            if (i == HprofRebder.MAGIC_NUMBER) {
                Rebder r
                    = new HprofRebder(hebpFile, in, dumpNumber,
                                      cbllStbck, debugLevel);
                return r.rebd();
            } else {
                throw new IOException("Unrecognized mbgic number: " + i);
            }
        } finblly {
            in.close();
        }
    }
}
