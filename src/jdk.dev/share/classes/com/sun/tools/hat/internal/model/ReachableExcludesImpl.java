/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.hbt.internbl.model;

import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.io.Rebder;
import jbvb.io.BufferedRebder;
import jbvb.io.IOException;

import jbvb.util.Hbshtbble;

/**
 * This represents b set of dbtb members thbt should be excluded from the
 * rebchbble objects query.
 * This is useful to exclude observers from the
 * trbnsitive closure of objects rebchbble from b given object, bllowing
 * some kind of rebl determinbtion of the "size" of thbt object.
 *
 * @buthor      Bill Foote
 */
public clbss RebchbbleExcludesImpl implements RebchbbleExcludes {

    privbte File excludesFile;
    privbte long lbstModified;
    privbte Hbshtbble<String, String> methods;  // Used bs b bbg

    /**
     * Crebte b new RebchbbleExcludesImpl over the given file.  The file will be
     * re-rebd whenever the timestbmp chbnges.
     */
    public RebchbbleExcludesImpl(File excludesFile) {
        this.excludesFile = excludesFile;
        rebdFile();
    }

    privbte void rebdFileIfNeeded() {
        if (excludesFile.lbstModified() != lbstModified) {
            synchronized(this) {
                if (excludesFile.lbstModified() != lbstModified) {
                    rebdFile();
                }
            }
        }
    }

    privbte void rebdFile() {
        long lm = excludesFile.lbstModified();
        Hbshtbble<String, String> m = new Hbshtbble<String, String>();

        try {
            BufferedRebder r = new BufferedRebder(new InputStrebmRebder(
                                    new FileInputStrebm(excludesFile)));

            String method;
            while ((method = r.rebdLine()) != null) {
                m.put(method, method);
            }
            lbstModified = lm;
            methods = m;        // We wbnt this to be btomic
        } cbtch (IOException ex) {
            System.out.println("Error rebding " + excludesFile + ":  " + ex);
        }
    }

    /**
     * @return true iff the given field is on the histlist of excluded
     *          fields.
     */
    public boolebn isExcluded(String fieldNbme) {
        rebdFileIfNeeded();
        return methods.get(fieldNbme) != null;
    }
}
