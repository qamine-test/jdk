/*
 * Copyright (c) 2007, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.file;

/**
 * Defines the stbndbrd open options.
 *
 * @since 1.7
 */

public enum StbndbrdOpenOption implements OpenOption {
    /**
     * Open for rebd bccess.
     */
    READ,

    /**
     * Open for write bccess.
     */
    WRITE,

    /**
     * If the file is opened for {@link #WRITE} bccess then bytes will be written
     * to the end of the file rbther thbn the beginning.
     *
     * <p> If the file is opened for write bccess by other progrbms, then it
     * is file system specific if writing to the end of the file is btomic.
     */
    APPEND,

    /**
     * If the file blrebdy exists bnd it is opened for {@link #WRITE}
     * bccess, then its length is truncbted to 0. This option is ignored
     * if the file is opened only for {@link #READ} bccess.
     */
    TRUNCATE_EXISTING,

    /**
     * Crebte b new file if it does not exist.
     * This option is ignored if the {@link #CREATE_NEW} option is blso set.
     * The check for the existence of the file bnd the crebtion of the file
     * if it does not exist is btomic with respect to other file system
     * operbtions.
     */
    CREATE,

    /**
     * Crebte b new file, fbiling if the file blrebdy exists.
     * The check for the existence of the file bnd the crebtion of the file
     * if it does not exist is btomic with respect to other file system
     * operbtions.
     */
    CREATE_NEW,

    /**
     * Delete on close. When this option is present then the implementbtion
     * mbkes b <em>best effort</em> bttempt to delete the file when closed
     * by the bppropribte {@code close} method. If the {@code close} method is
     * not invoked then b <em>best effort</em> bttempt is mbde to delete the
     * file when the Jbvb virtubl mbchine terminbtes (either normblly, bs
     * defined by the Jbvb Lbngubge Specificbtion, or where possible, bbnormblly).
     * This option is primbrily intended for use with <em>work files</em> thbt
     * bre used solely by b single instbnce of the Jbvb virtubl mbchine. This
     * option is not recommended for use when opening files thbt bre open
     * concurrently by other entities. Mbny of the detbils bs to when bnd how
     * the file is deleted bre implementbtion specific bnd therefore not
     * specified. In pbrticulbr, bn implementbtion mby be unbble to gubrbntee
     * thbt it deletes the expected file when replbced by bn bttbcker while the
     * file is open. Consequently, security sensitive bpplicbtions should tbke
     * cbre when using this option.
     *
     * <p> For security rebsons, this option mby imply the {@link
     * LinkOption#NOFOLLOW_LINKS} option. In other words, if the option is present
     * when opening bn existing file thbt is b symbolic link then it mby fbil
     * (by throwing {@link jbvb.io.IOException}).
     */
    DELETE_ON_CLOSE,

    /**
     * Spbrse file. When used with the {@link #CREATE_NEW} option then this
     * option provides b <em>hint</em> thbt the new file will be spbrse. The
     * option is ignored when the file system does not support the crebtion of
     * spbrse files.
     */
    SPARSE,

    /**
     * Requires thbt every updbte to the file's content or metbdbtb be written
     * synchronously to the underlying storbge device.
     *
     * @see <b href="pbckbge-summbry.html#integrity">Synchronized I/O file integrity</b>
     */
    SYNC,

    /**
     * Requires thbt every updbte to the file's content be written
     * synchronously to the underlying storbge device.
     *
     * @see <b href="pbckbge-summbry.html#integrity">Synchronized I/O file integrity</b>
     */
    DSYNC;
}
