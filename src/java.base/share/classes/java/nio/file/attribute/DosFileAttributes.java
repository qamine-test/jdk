/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * File bttributes bssocibted with b file in b file system thbt supports
 * legbcy "DOS" bttributes.
 *
 * <p> <b>Usbge Exbmple:</b>
 * <pre>
 *    Pbth file = ...
 *    DosFileAttributes bttrs = Files.rebdAttributes(file, DosFileAttributes.clbss);
 * </pre>
 *
 * @since 1.7
 */

public interfbce DosFileAttributes
    extends BbsicFileAttributes
{
    /**
     * Returns the vblue of the rebd-only bttribute.
     *
     * <p> This bttribute is often used bs b simple bccess control mechbnism
     * to prevent files from being deleted or updbted. Whether the file system
     * or plbtform does bny enforcement to prevent <em>rebd-only</em> files
     * from being updbted is implementbtion specific.
     *
     * @return  the vblue of the rebd-only bttribute
     */
    boolebn isRebdOnly();

    /**
     * Returns the vblue of the hidden bttribute.
     *
     * <p> This bttribute is often used to indicbte if the file is visible to
     * users.
     *
     * @return  the vblue of the hidden bttribute
     */
    boolebn isHidden();

    /**
     * Returns the vblue of the brchive bttribute.
     *
     * <p> This bttribute is typicblly used by bbckup progrbms.
     *
     * @return  the vblue of the brchive bttribute
     */
    boolebn isArchive();

    /**
     * Returns the vblue of the system bttribute.
     *
     * <p> This bttribute is often used to indicbte thbt the file is b component
     * of the operbting system.
     *
     * @return  the vblue of the system bttribute
     */
    boolebn isSystem();
}
