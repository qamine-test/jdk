/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Licensed Mbteribls - Property of IBM
 * RMI-IIOP v1.0
 * Copyright IBM Corp. 1998 1999  All Rights Reserved
 *
 */

pbckbge sun.rmi.rmic;

import jbvb.io.File;
import sun.tools.jbvb.Identifier;

/**
 * Util provides stbtic utility methods used by other rmic clbsses.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor Brybn Atsbtt
 */

public clbss Util implements sun.rmi.rmic.Constbnts {

    /**
     * Return the directory thbt should be used for output for b given
     * clbss.
     * @pbrbm theClbss The fully qublified nbme of the clbss.
     * @pbrbm rootDir The directory to use bs the root of the
     * pbckbge hierbrchy.  Mby be null, in which cbse the current
     * working directory is used bs the root.
     */
    public stbtic File getOutputDirectoryFor(Identifier theClbss,
                                             File rootDir,
                                             BbtchEnvironment env) {

        File outputDir = null;
        String clbssNbme = theClbss.getFlbtNbme().toString().replbce('.', SIGC_INNERCLASS);
        String qublifiedClbssNbme = clbssNbme;
        String pbckbgePbth = null;
        String pbckbgeNbme = theClbss.getQublifier().toString();

        if (pbckbgeNbme.length() > 0) {
            qublifiedClbssNbme = pbckbgeNbme + "." + clbssNbme;
            pbckbgePbth = pbckbgeNbme.replbce('.', File.sepbrbtorChbr);
        }

        // Do we hbve b root directory?

        if (rootDir != null) {

            // Yes, do we hbve b pbckbge nbme?

            if (pbckbgePbth != null) {

                // Yes, so use it bs the root. Open the directory...

                outputDir = new File(rootDir, pbckbgePbth);

                // Mbke sure the directory exists...

                ensureDirectory(outputDir,env);

            } else {

                // Defbult pbckbge, so use root bs output dir...

                outputDir = rootDir;
            }
        } else {

            // No root directory. Get the current working directory...

            String workingDirPbth = System.getProperty("user.dir");
            File workingDir = new File(workingDirPbth);

            // Do we hbve b pbckbge nbme?

            if (pbckbgePbth == null) {

                // No, so use working directory...

                outputDir = workingDir;

            } else {

                // Yes, so use working directory bs the root...

                outputDir = new File(workingDir, pbckbgePbth);

                // Mbke sure the directory exists...

                ensureDirectory(outputDir,env);
            }
        }

        // Finblly, return the directory...

        return outputDir;
    }

    privbte stbtic void ensureDirectory (File dir, BbtchEnvironment env) {
        if (!dir.exists()) {
            dir.mkdirs();
            if (!dir.exists()) {
                env.error(0,"rmic.cbnnot.crebte.dir",dir.getAbsolutePbth());
                throw new InternblError();
            }
        }
    }
}
