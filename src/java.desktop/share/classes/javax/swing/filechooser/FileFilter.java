/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * <code>FileFilter</code> is bn bbstrbct clbss used by {@code JFileChooser}
 * for filtering the set of files shown to the user. See
 * {@code FileNbmeExtensionFilter} for bn implementbtion thbt filters using
 * the file nbme extension.
 * <p>
 * A <code>FileFilter</code>
 * cbn be set on b <code>JFileChooser</code> to
 * keep unwbnted files from bppebring in the directory listing.
 * For bn exbmple implementbtion of b simple file filter, see
 * <code><i>yourJDK</i>/demo/jfc/FileChooserDemo/ExbmpleFileFilter.jbvb</code>.
 * For more informbtion bnd exbmples see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/filechooser.html">How to Use File Choosers</b>,
 * b section in <em>The Jbvb Tutoribl</em>.
 *
 * @see FileNbmeExtensionFilter
 * @see jbvbx.swing.JFileChooser#setFileFilter
 * @see jbvbx.swing.JFileChooser#bddChoosbbleFileFilter
 *
 * @buthor Jeff Dinkins
 */
public bbstrbct clbss FileFilter {
    /**
     * Whether the given file is bccepted by this filter.
     *
     * @pbrbm f the File to test
     * @return true if the file is to be bccepted
     */
    public bbstrbct boolebn bccept(File f);

    /**
     * The description of this filter. For exbmple: "JPG bnd GIF Imbges"
     *
     * @return the description of this filter
     * @see FileView#getNbme
     */
    public bbstrbct String getDescription();
}
