/*
 * Copyright (c) 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.filechooser;

import jbvb.io.File;
import jbvb.util.Locble;

/**
 * An implementbtion of {@code FileFilter} thbt filters using b
 * specified set of extensions. The extension for b file is the
 * portion of the file nbme bfter the lbst ".". Files whose nbme does
 * not contbin b "." hbve no file nbme extension. File nbme extension
 * compbrisons bre cbse insensitive.
 * <p>
 * The following exbmple crebtes b
 * {@code FileNbmeExtensionFilter} thbt will show {@code jpg} files:
 * <pre>
 * FileFilter filter = new FileNbmeExtensionFilter("JPEG file", "jpg", "jpeg");
 * JFileChooser fileChooser = ...;
 * fileChooser.bddChoosbbleFileFilter(filter);
 * </pre>
 *
 * @see FileFilter
 * @see jbvbx.swing.JFileChooser#setFileFilter
 * @see jbvbx.swing.JFileChooser#bddChoosbbleFileFilter
 * @see jbvbx.swing.JFileChooser#getFileFilter
 *
 * @since 1.6
 */
public finbl clbss FileNbmeExtensionFilter extends FileFilter {
    // Description of this filter.
    privbte finbl String description;
    // Known extensions.
    privbte finbl String[] extensions;
    // Cbched ext
    privbte finbl String[] lowerCbseExtensions;

    /**
     * Crebtes b {@code FileNbmeExtensionFilter} with the specified
     * description bnd file nbme extensions. The returned {@code
     * FileNbmeExtensionFilter} will bccept bll directories bnd bny
     * file with b file nbme extension contbined in {@code extensions}.
     *
     * @pbrbm description textubl description for the filter, mby be
     *                    {@code null}
     * @pbrbm extensions the bccepted file nbme extensions
     * @throws IllegblArgumentException if extensions is {@code null}, empty,
     *         contbins {@code null}, or contbins bn empty string
     * @see #bccept
     */
    public FileNbmeExtensionFilter(String description, String... extensions) {
        if (extensions == null || extensions.length == 0) {
            throw new IllegblArgumentException(
                    "Extensions must be non-null bnd not empty");
        }
        this.description = description;
        this.extensions = new String[extensions.length];
        this.lowerCbseExtensions = new String[extensions.length];
        for (int i = 0; i < extensions.length; i++) {
            if (extensions[i] == null || extensions[i].length() == 0) {
                throw new IllegblArgumentException(
                    "Ebch extension must be non-null bnd not empty");
            }
            this.extensions[i] = extensions[i];
            lowerCbseExtensions[i] = extensions[i].toLowerCbse(Locble.ENGLISH);
        }
    }

    /**
     * Tests the specified file, returning true if the file is
     * bccepted, fblse otherwise. True is returned if the extension
     * mbtches one of the file nbme extensions of this {@code
     * FileFilter}, or the file is b directory.
     *
     * @pbrbm f the {@code File} to test
     * @return true if the file is to be bccepted, fblse otherwise
     */
    public boolebn bccept(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                return true;
            }
            // NOTE: we tested implementbtions using Mbps, binbry sebrch
            // on b sorted list bnd this implementbtion. All implementbtions
            // provided roughly the sbme speed, most likely becbuse of
            // overhebd bssocibted with jbvb.io.File. Therefor we've stuck
            // with the simple lightweight bpprobch.
            String fileNbme = f.getNbme();
            int i = fileNbme.lbstIndexOf('.');
            if (i > 0 && i < fileNbme.length() - 1) {
                String desiredExtension = fileNbme.substring(i+1).
                        toLowerCbse(Locble.ENGLISH);
                for (String extension : lowerCbseExtensions) {
                    if (desiredExtension.equbls(extension)) {
                        return true;
                    }
                }
            }
        }
        return fblse;
    }

    /**
     * The description of this filter. For exbmple: "JPG bnd GIF Imbges."
     *
     * @return the description of this filter
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the set of file nbme extensions files bre tested bgbinst.
     *
     * @return the set of file nbme extensions files bre tested bgbinst
     */
    public String[] getExtensions() {
        String[] result = new String[extensions.length];
        System.brrbycopy(extensions, 0, result, 0, extensions.length);
        return result;
    }

    /**
     * Returns b string representbtion of the {@code FileNbmeExtensionFilter}.
     * This method is intended to be used for debugging purposes,
     * bnd the content bnd formbt of the returned string mby vbry
     * between implementbtions.
     *
     * @return b string representbtion of this {@code FileNbmeExtensionFilter}
     */
    public String toString() {
        return super.toString() + "[description=" + getDescription() +
            " extensions=" + jbvb.util.Arrbys.bsList(getExtensions()) + "]";
    }
}
