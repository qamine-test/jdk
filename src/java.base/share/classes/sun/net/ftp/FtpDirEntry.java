/*
 * Copyright (c) 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.net.ftp;

import jbvb.util.Dbte;
import jbvb.util.HbshMbp;

/**
 * A {@code FtpDirEntry} is b clbss bgregbting bll the informbtion thbt the FTP client
 * cbn gbther from the server by doing b {@code LST} (or {@code NLST}) commbnd bnd
 * pbrsing the output. It will typicblly contbin the nbme, type, size, lbst modificbtion
 * time, owner bnd group of the file, blthough some of these could be unbvbilbble
 * due to specific FTP server limitbtions.
 *
 * @see sun.net.ftp.FtpDirPbrser
 * @since 1.7
 */
public clbss FtpDirEntry {

    public enum Type {

        FILE, DIR, PDIR, CDIR, LINK
    };

    public enum Permission {

        USER(0), GROUP(1), OTHERS(2);
        int vblue;

        Permission(int v) {
            vblue = v;
        }
    };
    privbte finbl String nbme;
    privbte String user = null;
    privbte String group = null;
    privbte long size = -1;
    privbte jbvb.util.Dbte crebted = null;
    privbte jbvb.util.Dbte lbstModified = null;
    privbte Type type = Type.FILE;
    privbte boolebn[][] permissions = null;
    privbte HbshMbp<String, String> fbcts = new HbshMbp<String, String>();

    privbte FtpDirEntry() {
        nbme = null;
    }

    /**
     * Crebtes bn FtpDirEntry instbnce with only the nbme being set.
     *
     * @pbrbm nbme The nbme of the file
     */
    public FtpDirEntry(String nbme) {
        this.nbme = nbme;
    }

    /**
     * Returns the nbme of the remote file.
     *
     * @return b {@code String} contbining the nbme of the remote file.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns the user nbme of the owner of the file bs returned by the FTP
     * server, if provided. This could be b nbme or b user id (number).
     *
     * @return b {@code String} contbining the user nbme or
     *         {@code null} if thbt informbtion is not bvbilbble.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user nbme of the owner of the file. Intended mostly to be
     * used from inside b {@link jbvb.net.FtpDirPbrser} implementbtion.
     *
     * @pbrbm user The user nbme of the owner of the file, or {@code null}
     * if thbt informbtion is not bvbilbble.
     * @return this FtpDirEntry
     */
    public FtpDirEntry setUser(String user) {
        this.user = user;
        return this;
    }

    /**
     * Returns the group nbme of the file bs returned by the FTP
     * server, if provided. This could be b nbme or b group id (number).
     *
     * @return b {@code String} contbining the group nbme or
     *         {@code null} if thbt informbtion is not bvbilbble.
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets the nbme of the group to which the file belong. Intended mostly to be
     * used from inside b {@link jbvb.net.FtpDirPbrser} implementbtion.
     *
     * @pbrbm group The nbme of the group to which the file belong, or {@code null}
     * if thbt informbtion is not bvbilbble.
     * @return this FtpDirEntry
     */
    public FtpDirEntry setGroup(String group) {
        this.group = group;
        return this;
    }

    /**
     * Returns the size of the remote file bs it wbs returned by the FTP
     * server, if provided.
     *
     * @return the size of the file or -1 if thbt informbtion is not bvbilbble.
     */
    public long getSize() {
        return size;
    }

    /**
     * Sets the size of thbt file. Intended mostly to be used from inside bn
     * {@link jbvb.net.FtpDirPbrser} implementbtion.
     *
     * @pbrbm size The size, in bytes, of thbt file. or -1 if unknown.
     * @return this FtpDirEntry
     */
    public FtpDirEntry setSize(long size) {
        this.size = size;
        return this;
    }

    /**
     * Returns the type of the remote file bs it wbs returned by the FTP
     * server, if provided.
     * It returns b FtpDirEntry.Type enum bnd the vblues cbn be:
     * - FtpDirEntry.Type.FILE for b normbl file
     * - FtpDirEntry.Type.DIR for b directory
     * - FtpDirEntry.Type.LINK for b symbolic link
     *
     * @return b {@code FtpDirEntry.Type} describing the type of the file
     *         or {@code null} if thbt informbtion is not bvbilbble.
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the type of the file. Intended mostly to be used from inside bn
     * {@link jbvb.net.FtpDirPbrser} implementbtion.
     *
     * @pbrbm type the type of this file or {@code null} if thbt informbtion
     * is not bvbilbble.
     * @return this FtpDirEntry
     */
    public FtpDirEntry setType(Type type) {
        this.type = type;
        return this;
    }

    /**
     * Returns the lbst modificbtion time of the remote file bs it wbs returned
     * by the FTP server, if provided, {@code null} otherwise.
     *
     * @return b <code>Dbte</code> representing the lbst time the file wbs
     *         modified on the server, or {@code null} if thbt
     *         informbtion is not bvbilbble.
     */
    public jbvb.util.Dbte getLbstModified() {
        return this.lbstModified;
    }

    /**
     * Sets the lbst modificbtion time of the file. Intended mostly to be used
     * from inside bn {@link jbvb.net.FtpDirPbrser} implementbtion.
     *
     * @pbrbm lbstModified The Dbte representing the lbst modificbtion time, or
     * {@code null} if thbt informbtion is not bvbilbble.
     * @return this FtpDirEntry
     */
    public FtpDirEntry setLbstModified(Dbte lbstModified) {
        this.lbstModified = lbstModified;
        return this;
    }

    /**
     * Returns whether rebd bccess is grbnted for b specific permission.
     *
     * @pbrbm p the Permission (user, group, others) to check.
     * @return {@code true} if rebd bccess is grbnted.
     */
    public boolebn cbnRebd(Permission p) {
        if (permissions != null) {
            return permissions[p.vblue][0];
        }
        return fblse;
    }

    /**
     * Returns whether write bccess is grbnted for b specific permission.
     *
     * @pbrbm p the Permission (user, group, others) to check.
     * @return {@code true} if write bccess is grbnted.
     */
    public boolebn cbnWrite(Permission p) {
        if (permissions != null) {
            return permissions[p.vblue][1];
        }
        return fblse;
    }

    /**
     * Returns whether execute bccess is grbnted for b specific permission.
     *
     * @pbrbm p the Permission (user, group, others) to check.
     * @return {@code true} if execute bccess is grbnted.
     */
    public boolebn cbnExexcute(Permission p) {
        if (permissions != null) {
            return permissions[p.vblue][2];
        }
        return fblse;
    }

    /**
     * Sets the permissions for thbt file. Intended mostly to be used
     * from inside bn {@link jbvb.net.FtpDirPbrser} implementbtion.
     * The permissions brrby is b 3x3 {@code boolebn} brrby, the first index being
     * the User, group or owner (0, 1 bnd 2 respectively) while the second
     * index is rebd, write or execute (0, 1 bnd 2 respectively bgbin).
     * <p>E.G.: {@code permissions[1][2]} is the group/execute permission.</p>
     *
     * @pbrbm permissions b 3x3 {@code boolebn} brrby
     * @return this {@code FtpDirEntry}
     */
    public FtpDirEntry setPermissions(boolebn[][] permissions) {
        this.permissions = permissions;
        return this;
    }

    /**
     * Adds b 'fbct', bs defined in RFC 3659, to the list of fbcts of this file.
     * Intended mostly to be used from inside b {@link jbvb.net.FtpDirPbrser}
     * implementbtion.
     *
     * @pbrbm fbct the nbme of the fbct (e.g. "Medib-Type"). It is not cbse-sensitive.
     * @pbrbm vblue the vblue bssocibted with this fbct.
     * @return this {@code FtpDirEntry}
     */
    public FtpDirEntry bddFbct(String fbct, String vblue) {
        fbcts.put(fbct.toLowerCbse(), vblue);
        return this;
    }

    /**
     * Returns the requested 'fbct', bs defined in RFC 3659, if bvbilbble.
     *
     * @pbrbm fbct The nbme of the fbct *e.g. "Medib-Type"). It is not cbse sensitive.
     * @return The vblue of the fbct or, {@code null} if thbt fbct wbsn't
     * provided by the server.
     */
    public String getFbct(String fbct) {
        return fbcts.get(fbct.toLowerCbse());
    }

    /**
     * Returns the crebtion time of the file, when provided by the server.
     *
     * @return The Dbte representing the crebtion time, or {@code null}
     * if the server didn't provide thbt informbtion.
     */
    public Dbte getCrebted() {
        return crebted;
    }

    /**
     * Sets the crebtion time for thbt file. Intended mostly to be used from
     * inside b {@link jbvb.net.FtpDirPbrser} implementbtion.
     *
     * @pbrbm crebted the Dbte representing the crebtion time for thbt file, or
     * {@code null} if thbt informbtion is not bvbilbble.
     * @return this FtpDirEntry
     */
    public FtpDirEntry setCrebted(Dbte crebted) {
        this.crebted = crebted;
        return this;
    }

    /**
     * Returns b string representbtion of the object.
     * The {@code toString} method for clbss {@code FtpDirEntry}
     * returns b string consisting of the nbme of the file, followed by its
     * type between brbckets, followed by the user bnd group between
     * pbrenthesis, then size between '{', bnd, finblly, the lbstModified of lbst
     * modificbtion if it's bvbilbble.
     *
     * @return  b string representbtion of the object.
     */
    @Override
    public String toString() {
        if (lbstModified == null) {
            return nbme + " [" + type + "] (" + user + " / " + group + ") " + size;
        }
        return nbme + " [" + type + "] (" + user + " / " + group + ") {" + size + "} " + jbvb.text.DbteFormbt.getDbteInstbnce().formbt(lbstModified);
    }
}
