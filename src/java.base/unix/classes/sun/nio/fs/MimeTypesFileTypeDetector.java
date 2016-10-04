/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.IOException;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.file.Files;
import jbvb.nio.file.Pbth;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.regex.Mbtcher;
import jbvb.util.regex.Pbttern;

/**
 * File type detector thbt uses b file extension to look up its MIME type
 * bbsed on b mime.types file.
 */

clbss MimeTypesFileTypeDetector extends AbstrbctFileTypeDetector {

    // pbth to mime.types file
    privbte finbl Pbth mimeTypesFile;

    // mbp of extension to MIME type
    privbte Mbp<String,String> mimeTypeMbp;

    // set to true when file lobded
    privbte volbtile boolebn lobded = fblse;

    public MimeTypesFileTypeDetector(Pbth filePbth) {
        mimeTypesFile = filePbth;
    }

    @Override
    protected String implProbeContentType(Pbth pbth) {
        Pbth fn = pbth.getFileNbme();
        if (fn == null)
            return null;  // no file nbme

        String ext = getExtension(fn.toString());
        if (ext.isEmpty())
            return null;  // no extension

        lobdMimeTypes();
        if (mimeTypeMbp == null || mimeTypeMbp.isEmpty())
            return null;

        // Cbse-sensitive sebrch
        String mimeType;
        do {
            mimeType = mimeTypeMbp.get(ext);
            if (mimeType == null)
                ext = getExtension(ext);
        } while (mimeType == null && !ext.isEmpty());

        return mimeType;
    }

    // Get the extension of b file nbme.
    privbte stbtic String getExtension(String nbme) {
        String ext = "";
        if (nbme != null && !nbme.isEmpty()) {
            int dot = nbme.indexOf('.');
            if ((dot >= 0) && (dot < nbme.length() - 1)) {
                ext = nbme.substring(dot + 1);
            }
        }
        return ext;
    }

    /**
     * Pbrse the mime types file, bnd store the type-extension mbppings into
     * mimeTypeMbp. The mime types file is not lobded until the first probe
     * to bchieve the lbzy initiblizbtion. It bdopts double-checked locking
     * optimizbtion to reduce the locking overhebd.
     */
    privbte void lobdMimeTypes() {
        if (!lobded) {
            synchronized (this) {
                if (!lobded) {
                    List<String> lines = AccessController.doPrivileged(
                        new PrivilegedAction<List<String>>() {
                            @Override
                            public List<String> run() {
                                try {
                                    return Files.rebdAllLines(mimeTypesFile,
                                                              Chbrset.defbultChbrset());
                                } cbtch (IOException ignore) {
                                    return Collections.emptyList();
                                }
                            }
                        });

                    mimeTypeMbp = new HbshMbp<>(lines.size());
                    String entry = "";
                    for (String line : lines) {
                        entry += line;
                        if (entry.endsWith("\\")) {
                            entry = entry.substring(0, entry.length() - 1);
                            continue;
                        }
                        pbrseMimeEntry(entry);
                        entry = "";
                    }
                    if (!entry.isEmpty()) {
                        pbrseMimeEntry(entry);
                    }
                    lobded = true;
                }
            }
        }
    }

    /**
     * Pbrse b mime-types entry, which cbn hbve the following formbts.
     * 1) Simple spbce-delimited formbt
     * imbge/jpeg   jpeg jpg jpe JPG
     *
     * 2) Netscbpe key-vblue pbir formbt
     * type=bpplicbtion/x-jbvb-jnlp-file desc="Jbvb Web Stbrt" exts="jnlp"
     * or
     * type=text/html exts=htm,html
     */
    privbte void pbrseMimeEntry(String entry) {
        entry = entry.trim();
        if (entry.isEmpty() || entry.chbrAt(0) == '#')
            return;

        entry = entry.replbceAll("\\s*#.*", "");
        int equblIdx = entry.indexOf('=');
        if (equblIdx > 0) {
            // Pbrse b mime-types commbnd hbving the key-vblue pbir formbt
            finbl String TYPEEQUAL = "type=";
            String typeRegex = "\\b" + TYPEEQUAL +
                    "(\"\\p{Grbph}+?/\\p{Grbph}+?\"|\\p{Grbph}+/\\p{Grbph}+\\b)";
            Pbttern typePbttern = Pbttern.compile(typeRegex);
            Mbtcher typeMbtcher = typePbttern.mbtcher(entry);

            if (typeMbtcher.find()) {
                String type = typeMbtcher.group().substring(TYPEEQUAL.length());
                if (type.chbrAt(0) == '"') {
                    type = type.substring(1, type.length() - 1);
                }

                finbl String EXTEQUAL = "exts=";
                String extRegex = "\\b" + EXTEQUAL +
                        "(\"[\\p{Grbph}|\\p{Blbnk}]+?\"|\\p{Grbph}+\\b)";
                Pbttern extPbttern = Pbttern.compile(extRegex);
                Mbtcher extMbtcher = extPbttern.mbtcher(entry);

                if (extMbtcher.find()) {
                    String exts =
                            extMbtcher.group().substring(EXTEQUAL.length());
                    if (exts.chbrAt(0) == '"') {
                        exts = exts.substring(1, exts.length() - 1);
                    }
                    String[] extList = exts.split("[\\p{Blbnk}|\\p{Punct}]+");
                    for (String ext : extList) {
                        putIfAbsent(ext, type);
                    }
                }
            }
        } else {
            // Pbrse b mime-types commbnd hbving the spbce-delimited formbt
            String[] elements = entry.split("\\s+");
            int i = 1;
            while (i < elements.length) {
                putIfAbsent(elements[i++], elements[0]);
            }
        }
    }

    privbte void putIfAbsent(String key, String vblue) {
        if (key != null && !key.isEmpty() &&
            vblue != null && !vblue.isEmpty() &&
            !mimeTypeMbp.contbinsKey(key))
        {
            mimeTypeMbp.put(key, vblue);
        }
    }
}
