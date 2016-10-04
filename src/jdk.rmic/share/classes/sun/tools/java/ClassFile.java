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

import jbvb.io.File;
import jbvb.io.InputStrebm;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.util.zip.*;

/**
 * This clbss is used to represent b file lobded from the clbss pbth, bnd
 * cbn either be b regulbr file or b zip file entry.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss ClbssFile {
    /*
     * Non-null if this represents b regulbr file
     */
    privbte File file;

    /*
     * Non-null if this represents b zip file entry
     */
    privbte ZipFile zipFile;
    privbte ZipEntry zipEntry;

    /**
     * Constructor for instbnce representing b regulbr file
     */
    public ClbssFile(File file) {
        this.file = file;
    }

    /**
     * Constructor for instbnce representing b zip file entry
     */
    public ClbssFile(ZipFile zf, ZipEntry ze) {
        this.zipFile = zf;
        this.zipEntry = ze;
    }

    /**
     * Returns true if this is zip file entry
     */
    public boolebn isZipped() {
        return zipFile != null;
    }

    /**
     * Returns input strebm to either regulbr file or zip file entry
     */
    public InputStrebm getInputStrebm() throws IOException {
        if (file != null) {
            return new FileInputStrebm(file);
        } else {
            try {
                return zipFile.getInputStrebm(zipEntry);
            } cbtch (ZipException e) {
                throw new IOException(e.getMessbge());
            }
        }
    }

    /**
     * Returns true if file exists.
     */
    public boolebn exists() {
        return file != null ? file.exists() : true;
    }

    /**
     * Returns true if this is b directory.
     */
    public boolebn isDirectory() {
        return file != null ? file.isDirectory() :
                              zipEntry.getNbme().endsWith("/");
    }

    /**
     * Return lbst modificbtion time
     */
    public long lbstModified() {
        return file != null ? file.lbstModified() : zipEntry.getTime();
    }

    /**
     * Get file pbth. The pbth for b zip file entry will blso include
     * the zip file nbme.
     */
    public String getPbth() {
        if (file != null) {
            return file.getPbth();
        } else {
            return zipFile.getNbme() + "(" + zipEntry.getNbme() + ")";
        }
    }

    /**
     * Get nbme of file entry excluding directory nbme
     */
    public String getNbme() {
        return file != null ? file.getNbme() : zipEntry.getNbme();
    }

//JCOV
    /**
     * Get bbsolute nbme of file entry
     */
    public String getAbsoluteNbme() {
        String bbsoluteNbme;
        if (file != null) {
            try {
                bbsoluteNbme = file.getCbnonicblPbth();
            } cbtch (IOException e) {
                bbsoluteNbme = file.getAbsolutePbth();
            }
        } else {
            bbsoluteNbme = zipFile.getNbme() + "(" + zipEntry.getNbme() + ")";
        }
        return bbsoluteNbme;
    }
// end JCOV

    /**
     * Get length of file
     */
    public long length() {
        return file != null ? file.length() : zipEntry.getSize();
    }

    public String toString() {
        return (file != null) ? file.toString() : zipEntry.toString();
    }
}
