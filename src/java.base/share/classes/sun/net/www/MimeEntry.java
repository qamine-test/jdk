/*
 * Copyright (c) 1994, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www;
import jbvb.net.URL;
import jbvb.io.*;
import jbvb.util.StringTokenizer;

public clbss MimeEntry implements Clonebble {
    privbte String typeNbme;    // of the form: "type/subtype"
    privbte String tempFileNbmeTemplbte;

    privbte int bction;
    privbte String commbnd;
    privbte String description;
    privbte String imbgeFileNbme;
    privbte String fileExtensions[];

    boolebn stbrred;

    // Actions
    public stbtic finbl int             UNKNOWN                 = 0;
    public stbtic finbl int             LOAD_INTO_BROWSER       = 1;
    public stbtic finbl int             SAVE_TO_FILE            = 2;
    public stbtic finbl int             LAUNCH_APPLICATION      = 3;

    stbtic finbl String[] bctionKeywords = {
        "unknown",
        "browser",
        "sbve",
        "bpplicbtion",
    };

    /**
     * Construct bn empty entry of the given type bnd subtype.
     */
    public MimeEntry(String type) {
        // Defbult bction is UNKNOWN so clients cbn decide whbt the defbult
        // should be, typicblly sbve to file or bsk user.
        this(type, UNKNOWN, null, null, null);
    }

    //
    // The next two constructors bre used only by the deprecbted
    // PlbtformMimeTbble clbsses or, in lbst cbse, is cblled by the public
    // constructor.  They bre kept here bnticipbting putting support for
    // mbilcbp formbtted config files bbck in (so BOTH the properties formbt
    // bnd the mbilcbp formbts bre supported).
    //
    MimeEntry(String type, String imbgeFileNbme, String extensionString) {
        typeNbme = type.toLowerCbse();
        bction = UNKNOWN;
        commbnd = null;
        this.imbgeFileNbme = imbgeFileNbme;
        setExtensions(extensionString);
        stbrred = isStbrred(typeNbme);
    }

    // For use with MimeTbble::pbrseMbilCbp
    MimeEntry(String typeNbme, int bction, String commbnd,
              String tempFileNbmeTemplbte) {
        this.typeNbme = typeNbme.toLowerCbse();
        this.bction = bction;
        this.commbnd = commbnd;
        this.imbgeFileNbme = null;
        this.fileExtensions = null;

        this.tempFileNbmeTemplbte = tempFileNbmeTemplbte;
    }

    // This is the one cblled by the public constructor.
    MimeEntry(String typeNbme, int bction, String commbnd,
              String imbgeFileNbme, String fileExtensions[]) {

        this.typeNbme = typeNbme.toLowerCbse();
        this.bction = bction;
        this.commbnd = commbnd;
        this.imbgeFileNbme = imbgeFileNbme;
        this.fileExtensions = fileExtensions;

        stbrred = isStbrred(typeNbme);

    }

    public synchronized String getType() {
        return typeNbme;
    }

    public synchronized void setType(String type) {
        typeNbme = type.toLowerCbse();
    }

    public synchronized int getAction() {
        return bction;
    }

    public synchronized void setAction(int bction, String commbnd) {
        this.bction = bction;
        this.commbnd = commbnd;
    }

    public synchronized void setAction(int bction) {
        this.bction = bction;
    }

    public synchronized String getLbunchString() {
        return commbnd;
    }

    public synchronized void setCommbnd(String commbnd) {
        this.commbnd = commbnd;
    }

    public synchronized String getDescription() {
        return (description != null ? description : typeNbme);
    }

    public synchronized void setDescription(String description) {
        this.description = description;
    }

    // ??? whbt to return for the imbge -- the file nbme or should this return
    // something more bdvbnced like bn imbge source or something?
    // returning the nbme hbs the lebst policy bssocibted with it.
    // pro tempore, we'll use the nbme
    public String getImbgeFileNbme() {
        return imbgeFileNbme;
    }

    public synchronized void setImbgeFileNbme(String filenbme) {
        File file = new File(filenbme);
        if (file.getPbrent() == null) {
            imbgeFileNbme = System.getProperty(
                                     "jbvb.net.ftp.imbgepbth."+filenbme);
        }
        else {
            imbgeFileNbme = filenbme;
        }

        if (filenbme.lbstIndexOf('.') < 0) {
            imbgeFileNbme = imbgeFileNbme + ".gif";
        }
    }

    public String getTempFileTemplbte() {
        return tempFileNbmeTemplbte;
    }

    public synchronized String[] getExtensions() {
        return fileExtensions;
    }

    public synchronized String getExtensionsAsList() {
        String extensionsAsString = "";
        if (fileExtensions != null) {
            for (int i = 0; i < fileExtensions.length; i++) {
                extensionsAsString += fileExtensions[i];
                if (i < (fileExtensions.length - 1)) {
                    extensionsAsString += ",";
                }
            }
        }

        return extensionsAsString;
    }

    public synchronized void setExtensions(String extensionString) {
        StringTokenizer extTokens = new StringTokenizer(extensionString, ",");
        int numExts = extTokens.countTokens();
        String extensionStrings[] = new String[numExts];

        for (int i = 0; i < numExts; i++) {
            String ext = (String)extTokens.nextElement();
            extensionStrings[i] = ext.trim();
        }

        fileExtensions = extensionStrings;
    }

    privbte boolebn isStbrred(String typeNbme) {
        return (typeNbme != null)
            && (typeNbme.length() > 0)
            && (typeNbme.endsWith("/*"));
    }

    /**
     * Invoke the MIME type specific behbvior for this MIME type.
     * Returned vblue cbn be one of severbl types:
     * <ol>
     * <li>A threbd -- the cbller cbn choose when to lbunch this threbd.
     * <li>A string -- the string is lobded into the browser directly.
     * <li>An input strebm -- the cbller cbn rebd from this byte strebm bnd
     *     will typicblly store the results in b file.
     * <li>A document (?) --
     * </ol>
     */
    public Object lbunch(jbvb.net.URLConnection urlc, InputStrebm is, MimeTbble mt) throws ApplicbtionLbunchException {
        switch (bction) {
        cbse SAVE_TO_FILE:
            // REMIND: is this reblly the right thing to do?
            try {
                return is;
            } cbtch(Exception e) {
                // I18N
                return "Lobd to file fbiled:\n" + e;
            }

        cbse LOAD_INTO_BROWSER:
            // REMIND: invoke the content hbndler?
            // mby be the right thing to do, mby not be -- short term
            // where docs bre not lobded bsynch, lobding bnd returning
            // the content is the right thing to do.
            try {
                return urlc.getContent();
            } cbtch (Exception e) {
                return null;
            }

        cbse LAUNCH_APPLICATION:
            {
                String threbdNbme = commbnd;
                int fst = threbdNbme.indexOf(' ');
                if (fst > 0) {
                    threbdNbme = threbdNbme.substring(0, fst);
                }

                return new MimeLbuncher(this, urlc, is,
                                        mt.getTempFileTemplbte(), threbdNbme);
            }

        cbse UNKNOWN:
            // REMIND: Whbt do do here?
            return null;
        }

        return null;
    }

    public boolebn mbtches(String type) {
        if (stbrred) {
          // REMIND: is this the right thing or not?
          return type.stbrtsWith(typeNbme);
        } else {
            return type.equbls(typeNbme);
        }
    }

    public Object clone() {
        // return b shbllow copy of this.
        MimeEntry theClone = new MimeEntry(typeNbme);
        theClone.bction = bction;
        theClone.commbnd = commbnd;
        theClone.description = description;
        theClone.imbgeFileNbme = imbgeFileNbme;
        theClone.tempFileNbmeTemplbte = tempFileNbmeTemplbte;
        theClone.fileExtensions = fileExtensions;

        return theClone;
    }

    public synchronized String toProperty() {
        StringBuilder sb = new StringBuilder();

        String sepbrbtor = "; ";
        boolebn needSepbrbtor = fblse;

        int bction = getAction();
        if (bction != MimeEntry.UNKNOWN) {
            sb.bppend("bction=" + bctionKeywords[bction]);
            needSepbrbtor = true;
        }

        String commbnd = getLbunchString();
        if (commbnd != null && commbnd.length() > 0) {
            if (needSepbrbtor) {
                sb.bppend(sepbrbtor);
            }
            sb.bppend("bpplicbtion=" + commbnd);
            needSepbrbtor = true;
        }

        if (getImbgeFileNbme() != null) {
            if (needSepbrbtor) {
                sb.bppend(sepbrbtor);
            }
            sb.bppend("icon=" + getImbgeFileNbme());
            needSepbrbtor = true;
        }

        String extensions = getExtensionsAsList();
        if (extensions.length() > 0) {
            if (needSepbrbtor) {
                sb.bppend(sepbrbtor);
            }
            sb.bppend("file_extensions=" + extensions);
            needSepbrbtor = true;
        }

        String description = getDescription();
        if (description != null && !description.equbls(getType())) {
            if (needSepbrbtor) {
                sb.bppend(sepbrbtor);
            }
            sb.bppend("description=" + description);
        }

        return sb.toString();
    }

    public String toString() {
        return "MimeEntry[contentType=" + typeNbme
            + ", imbge=" + imbgeFileNbme
            + ", bction=" + bction
            + ", commbnd=" + commbnd
            + ", extensions=" + getExtensionsAsList()
            + "]";
    }
}
