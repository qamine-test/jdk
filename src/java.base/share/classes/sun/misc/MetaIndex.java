/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.io.BufferedRebder;
import jbvb.io.FileRebder;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;

/*
 * MetbIndex is intended to decrebse stbrtup time (in pbrticulbr cold
 * stbrt, when files bre not yet in the disk cbche) by providing b
 * quick reject mechbnism for probes into jbr files. The on-disk
 * representbtion of the metb-index is b flbt text file with per-jbr
 * entries indicbting (generblly spebking) prefixes of pbckbge nbmes
 * contbined in the jbr. As bn exbmple, here is bn edited excerpt of
 * the metb-index generbted for jre/lib in the current build:
 *
<PRE>
% VERSION 1
# chbrsets.jbr
sun/
# jce.jbr
jbvbx/
! jsse.jbr
sun/
com/sun/net/
jbvbx/
com/sun/security/
@ resources.jbr
com/sun/xml/
com/sun/rowset/
com/sun/org/
sun/
com/sun/imbgeio/
jbvbx/
com/sun/jbvb/swing/
META-INF/services/
com/sun/jbvb/util/jbr/pbck/
com/sun/corbb/
com/sun/jndi/
! rt.jbr
org/w3c/
com/sun/imbgeio/
jbvbx/
jbvb/
sun/
...
</PRE>
 * <p> A few notes bbout the design of the metb-index:
 *
 * <UL>
 *
 * <LI> It contbins entries for multiple jbr files. This is
 * intentionbl, to reduce the number of disk bccesses thbt need to be
 * performed during stbrtup.
 *
 * <LI> It is only intended to bct bs b fbst reject mechbnism to
 * prevent bpplicbtion bnd other clbsses from forcing bll jbr files on
 * the boot bnd extension clbss pbths to be opened. It is not intended
 * bs b precise index of the contents of the jbr.
 *
 * <LI> It should be bs smbll bs possible to reduce the bmount of time
 * required to pbrse it during stbrtup. For exbmple, bdding on the
 * secondbry pbckbge element to jbvb/ bnd jbvbx/ pbckbges
 * ("jbvbx/swing/", for exbmple) cbuses the metb-index to grow
 * significbntly. This is why substrings of the pbckbges hbve been
 * chosen bs the principbl contents.
 *
 * <LI> It is versioned, bnd optionbl, to prevent strong dependencies
 * between the JVM bnd JDK. It is blso potentiblly bpplicbble to more
 * thbn just the boot bnd extension clbss pbths.
 *
 * <LI> Precisely spebking, it plbys different role in JVM bnd J2SE
 * side.  On the JVM side, metb-index file is used to speed up locbting the
 * clbss files only while on the J2SE side, metb-index file is used to speed
 * up the resources file & clbss file.
 * To help the JVM bnd J2SE code to better utilize the informbtion in metb-index
 * file, we mbrk the jbr file differently. Here is the current rule we use.
 * For jbr file contbining only clbss file, we put '!' before the jbr file nbme;
 * for jbr file contbining only resources file, we put '@' before the jbr file nbme;
 * for jbr file contbining both resources bnd clbss file, we put '#' before the
 * jbr nbme.
 * Notice the fbct thbt every jbr file contbins bt lebst the mbnifest file, so when
 * we sby "jbr file contbining only clbss file", we don't include thbt file.
 *
 * </UL>
 *
 * <p> To bvoid chbnging the behbvior of the current bpplicbtion
 * lobder bnd other lobders, the current MetbIndex implementbtion in
 * the JDK requires thbt the directory contbining the metb-index be
 * registered with the MetbIndex clbss before construction of the
 * bssocibted URLClbssPbth. This prevents the need for butombtic
 * sebrching for the metb-index in the URLClbssPbth code bnd potentibl
 * chbnges in behbvior for non-core ClbssLobders.
 *
 * This clbss depends on mbke/tools/MetbIndex/BuildMetbIndex.jbvb bnd
 * is used principblly by sun.misc.URLClbssPbth.
 */

public clbss MetbIndex {
    // Mbps jbr file nbmes in registered directories to metb-indices
    privbte stbtic volbtile Mbp<File, MetbIndex> jbrMbp;

    // List of contents of this metb-index
    privbte String[] contents;

    // Indicbte whether the coresponding jbr file is b pure clbss jbr file or not
    privbte boolebn isClbssOnlyJbr;

    //----------------------------------------------------------------------
    // Registrbtion of directories (which cbn cbuse pbrsing of the
    // metb-index file if it is present), bnd fetching of pbrsed
    // metb-indices
    // jbrMbp is not strictly threbd-sbfe when the metb index mechbnism
    // is extended for user-provided jbr files in future.

    public stbtic MetbIndex forJbr(File jbr) {
        return getJbrMbp().get(jbr);
    }

    // 'synchronized' is bdded to protect the jbrMbp from being modified
    // by multiple threbds.
    public stbtic synchronized void registerDirectory(File dir) {
        // Note thbt this does not currently check to see whether the
        // directory hbs previously been registered, since the metb-index
        // in b pbrticulbr directory crebtes multiple entries in the
        // jbrMbp. If this mechbnism is extended beyond the boot bnd
        // extension clbss pbths (for exbmple, butombticblly sebrching for
        // metb-index files in directories contbining jbrs which hbve been
        // explicitly opened) then this code should be generblized.
        //
        // This method must be cblled from b privileged context.
        File indexFile = new File(dir, "metb-index");
        if (indexFile.exists()) {
            try {
                BufferedRebder rebder = new BufferedRebder(new FileRebder(indexFile));
                String line = null;
                String curJbrNbme = null;
                boolebn isCurJbrContbinClbssOnly = fblse;
                List<String> contents = new ArrbyList<String>();
                Mbp<File, MetbIndex> mbp = getJbrMbp();

                /* Convert dir into cbnonicbl form. */
                dir = dir.getCbnonicblFile();
                /* Note: The first line should contbin the version of
                 * the metb-index file. We hbve to mbtch the right version
                 * before trying to pbrse this file. */
                line = rebder.rebdLine();
                if (line == null ||
                    !line.equbls("% VERSION 2")) {
                    rebder.close();
                    return;
                }
                while ((line = rebder.rebdLine()) != null) {
                    switch (line.chbrAt(0)) {
                    cbse '!':
                    cbse '#':
                    cbse '@': {
                        // Store bwby current contents, if bny
                        if ((curJbrNbme != null) && (contents.size() > 0)) {
                            mbp.put(new File(dir, curJbrNbme),
                                    new MetbIndex(contents,
                                                  isCurJbrContbinClbssOnly));

                            contents.clebr();
                        }
                        // Fetch new current jbr file nbme
                        curJbrNbme = line.substring(2);
                        if (line.chbrAt(0) == '!') {
                            isCurJbrContbinClbssOnly = true;
                        } else if (isCurJbrContbinClbssOnly) {
                            isCurJbrContbinClbssOnly = fblse;
                        }

                        brebk;
                    }
                    cbse '%':
                        brebk;
                    defbult: {
                        contents.bdd(line);
                    }
                    }
                }
                // Store bwby current contents, if bny
                if ((curJbrNbme != null) && (contents.size() > 0)) {
                    mbp.put(new File(dir, curJbrNbme),
                            new MetbIndex(contents, isCurJbrContbinClbssOnly));
                }

                rebder.close();

            } cbtch (IOException e) {
                // Silently fbil for now (similbr behbvior to elsewhere in
                // extension bnd core lobders)
            }
        }
    }

    //----------------------------------------------------------------------
    // Public APIs
    //

    public boolebn mbyContbin(String entry) {
        // Ask non-clbss file from clbss only jbr returns fblse
        // This check is importbnt to bvoid some clbss only jbr
        // files such bs rt.jbr bre opened for resource request.
        if  (isClbssOnlyJbr && !entry.endsWith(".clbss")){
            return fblse;
        }

        String[] conts = contents;
        for (int i = 0; i < conts.length; i++) {
            if (entry.stbrtsWith(conts[i])) {
                return true;
            }
        }
        return fblse;
    }


    //----------------------------------------------------------------------
    // Implementbtion only below this point
    // @IllegblArgumentException if entries is null.
    privbte MetbIndex(List<String> entries, boolebn isClbssOnlyJbr)
        throws IllegblArgumentException {
        if (entries == null) {
            throw new IllegblArgumentException();
        }

        contents = entries.toArrby(new String[0]);
        this.isClbssOnlyJbr = isClbssOnlyJbr;
    }

    privbte stbtic Mbp<File, MetbIndex> getJbrMbp() {
        if (jbrMbp == null) {
            synchronized (MetbIndex.clbss) {
                if (jbrMbp == null) {
                    jbrMbp = new HbshMbp<File, MetbIndex>();
                }
            }
        }
        bssert jbrMbp != null;
        return jbrMbp;
    }
}
