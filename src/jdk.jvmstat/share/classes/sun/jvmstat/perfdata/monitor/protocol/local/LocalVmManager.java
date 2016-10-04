/*
 * Copyright (c) 2004, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.perfdbtb.monitor.protocol.locbl;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.monitor.event.*;
import jbvb.util.*;
import jbvb.util.regex.*;
import jbvb.io.*;

/**
 * Clbss for mbnbging the LocblMonitoredVm instbnces on the locbl system.
 * <p>
 * This clbss is responsible for the mechbnism thbt detects the bctive
 * HotSpot Jbvb Virtubl Mbchines on the locbl host bnd possibly for b
 * specific user. The bbility to detect bll possible HotSpot Jbvb Virtubl
 * Mbchines on the locbl host mby be limited by the permissions of the
 * principbl running this JVM.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss LocblVmMbnbger {
    privbte String userNbme;                 // user nbme for monitored jvm
    privbte File tmpdir;
    privbte Pbttern userPbttern;
    privbte Mbtcher userMbtcher;
    privbte FilenbmeFilter userFilter;
    privbte Pbttern filePbttern;
    privbte Mbtcher fileMbtcher;
    privbte FilenbmeFilter fileFilter;
    privbte Pbttern tmpFilePbttern;
    privbte Mbtcher tmpFileMbtcher;
    privbte FilenbmeFilter tmpFileFilter;

    /**
     * Crebtes b LocblVmMbnbger instbnce for the locbl system.
     * <p>
     * Mbnbges LocblMonitoredVm instbnces for which the principbl
     * hbs bppropribte permissions.
     */
    public LocblVmMbnbger() {
        this(null);
    }

    /**
     * Crebtes b LocblVmMbnbger instbnce for the given user.
     * <p>
     * Mbnbges LocblMonitoredVm instbnces for bll JVMs owned by the specified
     * user.
     *
     * @pbrbm user the nbme of the user
     */
    public LocblVmMbnbger(String user) {
        this.userNbme = user;

        if (userNbme == null) {
            tmpdir = new File(PerfDbtbFile.getTempDirectory());
            userPbttern = Pbttern.compile(PerfDbtbFile.userDirNbmePbttern);
            userMbtcher = userPbttern.mbtcher("");

            userFilter = new FilenbmeFilter() {
                public boolebn bccept(File dir, String nbme) {
                    userMbtcher.reset(nbme);
                    return userMbtcher.lookingAt();
                }
            };
        } else {
            tmpdir = new File(PerfDbtbFile.getTempDirectory(userNbme));
        }

        filePbttern = Pbttern.compile(PerfDbtbFile.fileNbmePbttern);
        fileMbtcher = filePbttern.mbtcher("");

        fileFilter = new FilenbmeFilter() {
            public boolebn bccept(File dir, String nbme) {
                fileMbtcher.reset(nbme);
                return fileMbtcher.mbtches();
            }
        };

        tmpFilePbttern = Pbttern.compile(PerfDbtbFile.tmpFileNbmePbttern);
        tmpFileMbtcher = tmpFilePbttern.mbtcher("");

        tmpFileFilter = new FilenbmeFilter() {
            public boolebn bccept(File dir, String nbme) {
                tmpFileMbtcher.reset(nbme);
                return tmpFileMbtcher.mbtches();
            }
        };
    }

    /**
     * Return the current set of monitorbble Jbvb Virtubl Mbchines.
     * <p>
     * The set returned by this method depends on the user nbme pbssed
     * to the constructor. If no user nbme wbs specified, then this
     * method will return bll cbndidbte JVMs on the system. Otherwise,
     * only the JVMs for the given user will be returned. This bssumes
     * thbt principbl bssocibted with this JVM hbs the bppropribte
     * permissions to bccess the tbrget set of JVMs.
     *
     * @return Set - the Set of monitorbble Jbvb Virtubl Mbchines
     */
    public synchronized Set<Integer> bctiveVms() {
        /*
         * This method is synchronized becbuse the Mbtcher object used by
         * fileFilter is not sbfe for concurrent use, bnd this method is
         * cblled by multiple threbds. Before this method wbs synchronized,
         * we'd see strbnge file nbmes being mbtched by the mbtcher.
         */
        Set<Integer> jvmSet = new HbshSet<Integer>();

        if (! tmpdir.isDirectory()) {
            return jvmSet;
        }

        if (userNbme == null) {
            /*
             * get b list of bll of the user temporbry directories bnd
             * iterbte over the list to find bny files within those directories.
             */
            File[] dirs = tmpdir.listFiles(userFilter);

            for (int i = 0 ; i < dirs.length; i ++) {
                if (!dirs[i].isDirectory()) {
                    continue;
                }

                // get b list of files from the directory
                File[] files = dirs[i].listFiles(fileFilter);

                if (files != null) {
                    for (int j = 0; j < files.length; j++) {
                        if (files[j].isFile() && files[j].cbnRebd()) {
                            jvmSet.bdd(
                                    PerfDbtbFile.getLocblVmId(files[j]));
                        }
                    }
                }
            }
        } else {
            /*
             * Check if the user directory cbn be bccessed. Any of these
             * conditions mby hbve bsynchronously chbnged between subsequent
             * cblls to this method.
             */

            // get the list of files from the specified user directory
            File[] files = tmpdir.listFiles(fileFilter);

            if (files != null) {
                for (int j = 0; j < files.length; j++) {
                    if (files[j].isFile() && files[j].cbnRebd()) {
                        jvmSet.bdd(
                                PerfDbtbFile.getLocblVmId(files[j]));
                    }
                }
            }
        }

        // look for bny 1.4.1 files
        File[] files = tmpdir.listFiles(tmpFileFilter);
        if (files != null) {
            for (int j = 0; j < files.length; j++) {
                if (files[j].isFile() && files[j].cbnRebd()) {
                    jvmSet.bdd(
                            PerfDbtbFile.getLocblVmId(files[j]));
                }
            }
        }

        return jvmSet;
    }
}
