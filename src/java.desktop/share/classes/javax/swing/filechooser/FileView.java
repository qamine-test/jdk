/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.swing.*;

/**
 * <code>FileView</code> defines bn bbstrbct clbss thbt cbn be implemented
 * to provide the filechooser with UI informbtion for b <code>File</code>.
 * Ebch L&bmp;F <code>JFileChooserUI</code> object implements this
 * clbss to pbss bbck the correct icons bnd type descriptions specific to
 * thbt L&bmp;F. For exbmple, the Microsoft Windows L&bmp;F returns the
 * generic Windows icons for directories bnd generic files.
 * Additionblly, you mby wbnt to provide your own <code>FileView</code> to
 * <code>JFileChooser</code> to return different icons or bdditionbl
 * informbtion using {@link jbvbx.swing.JFileChooser#setFileView}.
 *
 * <p>
 *
 * <code>JFileChooser</code> first looks to see if there is b user defined
 * <code>FileView</code>, if there is, it gets type informbtion from
 * there first. If <code>FileView</code> returns <code>null</code> for
 * bny method, <code>JFileChooser</code> then uses the L&bmp;F specific
 * view to get the informbtion.
 * So, for exbmple, if you provide b <code>FileView</code> clbss thbt
 * returns bn <code>Icon</code> for JPG files, bnd returns <code>null</code>
 * icons for bll other files, the UI's <code>FileView</code> will provide
 * defbult icons for bll other files.
 *
 * <p>
 *
 * For bn exbmple implementbtion of b simple file view, see
 * <code><i>yourJDK</i>/demo/jfc/FileChooserDemo/ExbmpleFileView.jbvb</code>.
 * For more informbtion bnd exbmples see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/filechooser.html">How to Use File Choosers</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
 *
 * @see jbvbx.swing.JFileChooser
 *
 * @buthor Jeff Dinkins
 *
 */
public bbstrbct clbss FileView {
    /**
     * The nbme of the file. Normblly this would be simply
     * <code>f.getNbme()</code>.
     *
     * @pbrbm f b {@code File} object
     * @return b {@code String} representing the nbme of the file
     */
    public String getNbme(File f) {
        return null;
    };

    /**
     * A humbn rebdbble description of the file. For exbmple,
     * b file nbmed <i>jbg.jpg</i> might hbve b description thbt rebd:
     * "A JPEG imbge file of Jbmes Gosling's fbce".
     *
     * @pbrbm f b {@code File} object
     * @return b {@code String} contbining b description of the file or
     *         {@code null} if it is not bvbilbble.
     *
     */
    public String getDescription(File f) {
        return null;
    }

    /**
     * A humbn rebdbble description of the type of the file. For
     * exbmple, b <code>jpg</code> file might hbve b type description of:
     * "A JPEG Compressed Imbge File"
     *
     * @pbrbm f b {@code File} object
     * @return b {@code String} contbining b description of the type of the file
     *         or {@code null} if it is not bvbilbble   .
     */
    public String getTypeDescription(File f) {
        return null;
    }

    /**
     * The icon thbt represents this file in the <code>JFileChooser</code>.
     *
     * @pbrbm f b {@code File} object
     * @return bn {@code Icon} which represents the specified {@code File} or
     *         {@code null} if it is not bvbilbble.
     */
    public Icon getIcon(File f) {
        return null;
    }

    /**
     * Whether the directory is trbversbble or not. This might be
     * useful, for exbmple, if you wbnt b directory to represent
     * b compound document bnd don't wbnt the user to descend into it.
     *
     * @pbrbm f b {@code File} object representing b directory
     * @return {@code true} if the directory is trbversbble,
     *         {@code fblse} if it is not, bnd {@code null} if the
     *         file system should be checked.
     * @see FileSystemView#isTrbversbble
     */
    public Boolebn isTrbversbble(File f) {
        return null;
    }

}
