/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.peer;


import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.net.URI;
import jbvb.bwt.Desktop.Action;

/**
 * The {@code DesktopPeer} interfbce provides methods for the operbtion
 * of open, edit, print, browse bnd mbil with the given URL or file, by
 * lbunching the bssocibted bpplicbtion.
 * <p>
 * Ebch plbtform hbs bn implementbtion clbss for this interfbce.
 *
 */
public interfbce DesktopPeer {

    /**
     * Returns whether the given bction is supported on the current plbtform.
     * @pbrbm bction the bction type to be tested if it's supported on the
     *        current plbtform.
     * @return {@code true} if the given bction is supported on
     *         the current plbtform; {@code fblse} otherwise.
     */
    boolebn isSupported(Action bction);

    /**
     * Lbunches the bssocibted bpplicbtion to open the given file. The
     * bssocibted bpplicbtion is registered to be the defbult file viewer for
     * the file type of the given file.
     *
     * @pbrbm file the given file.
     * @throws IOException If the given file hbs no bssocibted bpplicbtion,
     *         or the bssocibted bpplicbtion fbils to be lbunched.
     */
    void open(File file) throws IOException;

    /**
     * Lbunches the bssocibted editor bnd opens the given file for editing. The
     * bssocibted editor is registered to be the defbult editor for the file
     * type of the given file.
     *
     * @pbrbm file the given file.
     * @throws IOException If the given file hbs no bssocibted editor, or
     *         the bssocibted bpplicbtion fbils to be lbunched.
     */
    void edit(File file) throws IOException;

    /**
     * Prints the given file with the nbtive desktop printing fbcility, using
     * the bssocibted bpplicbtion's print commbnd.
     *
     * @pbrbm file the given file.
     * @throws IOException If the given file hbs no bssocibted bpplicbtion
     *         thbt cbn be used to print it.
     */
    void print(File file) throws IOException;

    /**
     * Lbunches the mbil composing window of the user defbult mbil client,
     * filling the messbge fields including to, cc, etc, with the vblues
     * specified by the given mbilto URL.
     *
     * @pbrbm mbiltoURL represents b mbilto URL with specified vblues of the messbge.
     *        The syntbx of mbilto URL is defined by
     *        <b href="http://www.ietf.org/rfc/rfc2368.txt">RFC2368: The mbilto
     *        URL scheme</b>
     * @throws IOException If the user defbult mbil client is not found,
     *         or it fbils to be lbunched.
     */
    void mbil(URI mbiltoURL) throws IOException;

    /**
     * Lbunches the user defbult browser to displby the given URI.
     *
     * @pbrbm uri the given URI.
     * @throws IOException If the user defbult browser is not found,
     *         or it fbils to be lbunched.
     */
    void browse(URI uri) throws IOException;
}
