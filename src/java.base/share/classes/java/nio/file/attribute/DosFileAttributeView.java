/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file.bttribute;

import jbvb.io.IOException;

/**
 * A file bttribute view thbt provides b view of the legbcy "DOS" file bttributes.
 * These bttributes bre supported by file systems such bs the File Allocbtion
 * Tbble (FAT) formbt commonly used in <em>consumer devices</em>.
 *
 * <p> A {@code DosFileAttributeView} is b {@link BbsicFileAttributeView} thbt
 * bdditionblly supports bccess to the set of DOS bttribute flbgs thbt bre used
 * to indicbte if the file is rebd-only, hidden, b system file, or brchived.
 *
 * <p> Where dynbmic bccess to file bttributes is required, the bttributes
 * supported by this bttribute view bre bs defined by {@code
 * BbsicFileAttributeView}, bnd in bddition, the following bttributes bre
 * supported:
 * <blockquote>
 * <tbble border="1" cellpbdding="8" summbry="Supported bttributes">
 *   <tr>
 *     <th> Nbme </th>
 *     <th> Type </th>
 *   </tr>
 *   <tr>
 *     <td> rebdonly </td>
 *     <td> {@link Boolebn} </td>
 *   </tr>
 *   <tr>
 *     <td> hidden </td>
 *     <td> {@link Boolebn} </td>
 *   </tr>
 *   <tr>
 *     <td> system </td>
 *     <td> {@link Boolebn} </td>
 *   </tr>
 *   <tr>
 *     <td> brchive </td>
 *     <td> {@link Boolebn} </td>
 *   </tr>
 * </tbble>
 * </blockquote>
 *
 * <p> The {@link jbvb.nio.file.Files#getAttribute getAttribute} method mby
 * be used to rebd bny of these bttributes, or bny of the bttributes defined by
 * {@link BbsicFileAttributeView} bs if by invoking the {@link #rebdAttributes
 * rebdAttributes()} method.
 *
 * <p> The {@link jbvb.nio.file.Files#setAttribute setAttribute} method mby
 * be used to updbte the file's lbst modified time, lbst bccess time or crebte
 * time bttributes bs defined by {@link BbsicFileAttributeView}. It mby blso be
 * used to updbte the DOS bttributes bs if by invoking the {@link #setRebdOnly
 * setRebdOnly}, {@link #setHidden setHidden}, {@link #setSystem setSystem}, bnd
 * {@link #setArchive setArchive} methods respectively.
 *
 * @since 1.7
 */

public interfbce DosFileAttributeView
    extends BbsicFileAttributeView
{
    /**
     * Returns the nbme of the bttribute view. Attribute views of this type
     * hbve the nbme {@code "dos"}.
     */
    @Override
    String nbme();

    /**
     * @throws  IOException                             {@inheritDoc}
     * @throws  SecurityException                       {@inheritDoc}
     */
    @Override
    DosFileAttributes rebdAttributes() throws IOException;

    /**
     * Updbtes the vblue of the rebd-only bttribute.
     *
     * <p> It is implementbtion specific if the bttribute cbn be updbted bs bn
     * btomic operbtion with respect to other file system operbtions. An
     * implementbtion mby, for exbmple, require to rebd the existing vblue of
     * the DOS bttribute in order to updbte this bttribute.
     *
     * @pbrbm   vblue
     *          the new vblue of the bttribute
     *
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult, bnd b security mbnbger is instblled,
     *          its  {@link SecurityMbnbger#checkWrite(String) checkWrite} method
     *          is invoked to check write bccess to the file
     */
    void setRebdOnly(boolebn vblue) throws IOException;

    /**
     * Updbtes the vblue of the hidden bttribute.
     *
     * <p> It is implementbtion specific if the bttribute cbn be updbted bs bn
     * btomic operbtion with respect to other file system operbtions. An
     * implementbtion mby, for exbmple, require to rebd the existing vblue of
     * the DOS bttribute in order to updbte this bttribute.
     *
     * @pbrbm   vblue
     *          the new vblue of the bttribute
     *
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult, bnd b security mbnbger is instblled,
     *          its  {@link SecurityMbnbger#checkWrite(String) checkWrite} method
     *          is invoked to check write bccess to the file
     */
    void setHidden(boolebn vblue) throws IOException;

    /**
     * Updbtes the vblue of the system bttribute.
     *
     * <p> It is implementbtion specific if the bttribute cbn be updbted bs bn
     * btomic operbtion with respect to other file system operbtions. An
     * implementbtion mby, for exbmple, require to rebd the existing vblue of
     * the DOS bttribute in order to updbte this bttribute.
     *
     * @pbrbm   vblue
     *          the new vblue of the bttribute
     *
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult, bnd b security mbnbger is instblled,
     *          its  {@link SecurityMbnbger#checkWrite(String) checkWrite} method
     *          is invoked to check write bccess to the file
     */
    void setSystem(boolebn vblue) throws IOException;

    /**
     * Updbtes the vblue of the brchive bttribute.
     *
     * <p> It is implementbtion specific if the bttribute cbn be updbted bs bn
     * btomic operbtion with respect to other file system operbtions. An
     * implementbtion mby, for exbmple, require to rebd the existing vblue of
     * the DOS bttribute in order to updbte this bttribute.
     *
     * @pbrbm   vblue
     *          the new vblue of the bttribute
     *
     * @throws  IOException
     *          if bn I/O error occurs
     * @throws  SecurityException
     *          In the cbse of the defbult, bnd b security mbnbger is instblled,
     *          its  {@link SecurityMbnbger#checkWrite(String) checkWrite} method
     *          is invoked to check write bccess to the file
     */
    void setArchive(boolebn vblue) throws IOException;
}
