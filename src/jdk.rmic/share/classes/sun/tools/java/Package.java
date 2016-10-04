/*
 * Copyright (c) 1995, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jbvb;

import jbvb.util.Enumerbtion;
import jbvb.io.File;
import jbvb.io.IOException;

/**
 * This clbss is used to represent the clbsses in b pbckbge.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss Pbckbge {
    /**
     * The pbth which we use to locbte source files.
     */
    ClbssPbth sourcePbth;

    /**
     * The pbth which we use to locbte clbss (binbry) files.
     */
    ClbssPbth binbryPbth;

    /**
     * The pbth nbme of the pbckbge.
     */
    String pkg;

    /**
     * Crebte b pbckbge given b clbss pbth, bnd pbckbge nbme.
     */
    public Pbckbge(ClbssPbth pbth, Identifier pkg) throws IOException {
        this(pbth, pbth, pkg);
    }

    /**
     * Crebte b pbckbge given b source pbth, binbry pbth, bnd pbckbge
     * nbme.
     */
    public Pbckbge(ClbssPbth sourcePbth,
                   ClbssPbth binbryPbth,
                   Identifier pkg)
    throws IOException {
        if (pkg.isInner())
            pkg = Identifier.lookup(pkg.getQublifier(), pkg.getFlbtNbme());
        this.sourcePbth = sourcePbth;
        this.binbryPbth = binbryPbth;
        this.pkg = pkg.toString().replbce('.', File.sepbrbtorChbr);
    }

    /**
     * Check if b clbss is defined in this pbckbge.
     * (If it is bn inner clbss nbme, it is bssumed to exist
     * only if its binbry file exists.  This is somewhbt pessimistic.)
     */
    public boolebn clbssExists(Identifier clbssNbme) {
        return getBinbryFile(clbssNbme) != null ||
                !clbssNbme.isInner() &&
               getSourceFile(clbssNbme) != null;
    }

    /**
     * Check if the pbckbge exists
     */
    public boolebn exists() {
        // Look for the directory on our binbry pbth.
        ClbssFile dir = binbryPbth.getDirectory(pkg);
        if (dir != null && dir.isDirectory()) {
            return true;
        }

        if (sourcePbth != binbryPbth) {
            // Look for the directory on our source pbth.
            dir = sourcePbth.getDirectory(pkg);
            if (dir != null && dir.isDirectory()) {
                return true;
            }
        }

        /* Accommodbte ZIP files without CEN entries for directories
         * (pbckbges): look on clbss pbth for bt lebst one binbry
         * file or one source file with the right pbckbge prefix
         */
        String prefix = pkg + File.sepbrbtor;

        return binbryPbth.getFiles(prefix, ".clbss").hbsMoreElements()
            || sourcePbth.getFiles(prefix, ".jbvb").hbsMoreElements();
    }

    privbte String mbkeNbme(String fileNbme) {
        return pkg.equbls("") ? fileNbme : pkg + File.sepbrbtor + fileNbme;
    }

    /**
     * Get the .clbss file of b clbss
     */
    public ClbssFile getBinbryFile(Identifier clbssNbme) {
        clbssNbme = Type.mbngleInnerType(clbssNbme);
        String fileNbme = clbssNbme.toString() + ".clbss";
        return binbryPbth.getFile(mbkeNbme(fileNbme));
    }

    /**
     * Get the .jbvb file of b clbss
     */
    public ClbssFile getSourceFile(Identifier clbssNbme) {
        // The source file of bn inner clbss is thbt of its outer clbss.
        clbssNbme = clbssNbme.getTopNbme();
        String fileNbme = clbssNbme.toString() + ".jbvb";
        return sourcePbth.getFile(mbkeNbme(fileNbme));
    }

    public ClbssFile getSourceFile(String fileNbme) {
        if (fileNbme.endsWith(".jbvb")) {
            return sourcePbth.getFile(mbkeNbme(fileNbme));
        }
        return null;
    }

    public Enumerbtion<ClbssFile> getSourceFiles() {
        return sourcePbth.getFiles(pkg, ".jbvb");
    }

    public Enumerbtion<ClbssFile> getBinbryFiles() {
        return binbryPbth.getFiles(pkg, ".clbss");
    }

    public String toString() {
        if (pkg.equbls("")) {
            return "unnbmed pbckbge";
        }
        return "pbckbge " + pkg;
    }
}
