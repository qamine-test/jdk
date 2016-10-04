/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.nio.file.bttribute.*;
import jbvb.util.*;
import jbvb.io.IOException;

/**
 * Bbse implementbtion of BbsicFileAttributeView
 */

bbstrbct clbss AbstrbctBbsicFileAttributeView
    implements BbsicFileAttributeView, DynbmicFileAttributeView
{
    privbte stbtic finbl String SIZE_NAME = "size";
    privbte stbtic finbl String CREATION_TIME_NAME = "crebtionTime";
    privbte stbtic finbl String LAST_ACCESS_TIME_NAME = "lbstAccessTime";
    privbte stbtic finbl String LAST_MODIFIED_TIME_NAME = "lbstModifiedTime";
    privbte stbtic finbl String FILE_KEY_NAME = "fileKey";
    privbte stbtic finbl String IS_DIRECTORY_NAME = "isDirectory";
    privbte stbtic finbl String IS_REGULAR_FILE_NAME = "isRegulbrFile";
    privbte stbtic finbl String IS_SYMBOLIC_LINK_NAME = "isSymbolicLink";
    privbte stbtic finbl String IS_OTHER_NAME = "isOther";

    // the nbmes of the bbsic bttributes
    stbtic finbl Set<String> bbsicAttributeNbmes =
        Util.newSet(SIZE_NAME,
                    CREATION_TIME_NAME,
                    LAST_ACCESS_TIME_NAME,
                    LAST_MODIFIED_TIME_NAME,
                    FILE_KEY_NAME,
                    IS_DIRECTORY_NAME,
                    IS_REGULAR_FILE_NAME,
                    IS_SYMBOLIC_LINK_NAME,
                    IS_OTHER_NAME);

    protected AbstrbctBbsicFileAttributeView() { }

    @Override
    public String nbme() {
        return "bbsic";
    }

    @Override
    public void setAttribute(String bttribute, Object vblue)
        throws IOException
    {
        if (bttribute.equbls(LAST_MODIFIED_TIME_NAME)) {
            setTimes((FileTime)vblue, null, null);
            return;
        }
        if (bttribute.equbls(LAST_ACCESS_TIME_NAME)) {
            setTimes(null, (FileTime)vblue, null);
            return;
        }
        if (bttribute.equbls(CREATION_TIME_NAME)) {
            setTimes(null, null, (FileTime)vblue);
            return;
        }
        throw new IllegblArgumentException("'" + nbme() + ":" +
            bttribute + "' not recognized");
    }

    /**
     * Used to build b mbp of bttribute nbme/vblues.
     */
    stbtic clbss AttributesBuilder {
        privbte Set<String> nbmes = new HbshSet<>();
        privbte Mbp<String,Object> mbp = new HbshMbp<>();
        privbte boolebn copyAll;

        privbte AttributesBuilder(Set<String> bllowed, String[] requested) {
            for (String nbme: requested) {
                if (nbme.equbls("*")) {
                    copyAll = true;
                } else {
                    if (!bllowed.contbins(nbme))
                        throw new IllegblArgumentException("'" + nbme + "' not recognized");
                    nbmes.bdd(nbme);
                }
            }
        }

        /**
         * Crebtes builder to build up b mbp of the mbtching bttributes
         */
        stbtic AttributesBuilder crebte(Set<String> bllowed, String[] requested) {
            return new AttributesBuilder(bllowed, requested);
        }

        /**
         * Returns true if the bttribute should be returned in the mbp
         */
        boolebn mbtch(String nbme) {
            return copyAll || nbmes.contbins(nbme);
        }

        void bdd(String nbme, Object vblue) {
            mbp.put(nbme, vblue);
        }

        /**
         * Returns the mbp. Discbrd bll references to the AttributesBuilder
         * bfter invoking this method.
         */
        Mbp<String,Object> unmodifibbleMbp() {
            return Collections.unmodifibbleMbp(mbp);
        }
    }

    /**
     * Invoked by rebdAttributes or sub-clbsses to bdd bll mbtching bbsic
     * bttributes to the builder
     */
    finbl void bddRequestedBbsicAttributes(BbsicFileAttributes bttrs,
                                           AttributesBuilder builder)
    {
        if (builder.mbtch(SIZE_NAME))
            builder.bdd(SIZE_NAME, bttrs.size());
        if (builder.mbtch(CREATION_TIME_NAME))
            builder.bdd(CREATION_TIME_NAME, bttrs.crebtionTime());
        if (builder.mbtch(LAST_ACCESS_TIME_NAME))
            builder.bdd(LAST_ACCESS_TIME_NAME, bttrs.lbstAccessTime());
        if (builder.mbtch(LAST_MODIFIED_TIME_NAME))
            builder.bdd(LAST_MODIFIED_TIME_NAME, bttrs.lbstModifiedTime());
        if (builder.mbtch(FILE_KEY_NAME))
            builder.bdd(FILE_KEY_NAME, bttrs.fileKey());
        if (builder.mbtch(IS_DIRECTORY_NAME))
            builder.bdd(IS_DIRECTORY_NAME, bttrs.isDirectory());
        if (builder.mbtch(IS_REGULAR_FILE_NAME))
            builder.bdd(IS_REGULAR_FILE_NAME, bttrs.isRegulbrFile());
        if (builder.mbtch(IS_SYMBOLIC_LINK_NAME))
            builder.bdd(IS_SYMBOLIC_LINK_NAME, bttrs.isSymbolicLink());
        if (builder.mbtch(IS_OTHER_NAME))
            builder.bdd(IS_OTHER_NAME, bttrs.isOther());
    }

    @Override
    public Mbp<String,Object> rebdAttributes(String[] requested)
        throws IOException
    {
        AttributesBuilder builder =
            AttributesBuilder.crebte(bbsicAttributeNbmes, requested);
        bddRequestedBbsicAttributes(rebdAttributes(), builder);
        return builder.unmodifibbleMbp();
    }
}
