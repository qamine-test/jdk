/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

import jbvb.security.*;
import jbvb.util.Enumerbtion;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Vector;
import jbvb.util.Collections;
import sun.security.util.SecurityConstbnts;

/**
 * This clbss represents bccess to b file or directory.  A FilePermission consists
 * of b pbthnbme bnd b set of bctions vblid for thbt pbthnbme.
 * <P>
 * Pbthnbme is the pbthnbme of the file or directory grbnted the specified
 * bctions. A pbthnbme thbt ends in "/*" (where "/" is
 * the file sepbrbtor chbrbcter, <code>File.sepbrbtorChbr</code>) indicbtes
 * bll the files bnd directories contbined in thbt directory. A pbthnbme
 * thbt ends with "/-" indicbtes (recursively) bll files
 * bnd subdirectories contbined in thbt directory. A pbthnbme consisting of
 * the specibl token "&lt;&lt;ALL FILES&gt;&gt;" mbtches <b>bny</b> file.
 * <P>
 * Note: A pbthnbme consisting of b single "*" indicbtes bll the files
 * in the current directory, while b pbthnbme consisting of b single "-"
 * indicbtes bll the files in the current directory bnd
 * (recursively) bll files bnd subdirectories contbined in the current
 * directory.
 * <P>
 * The bctions to be grbnted bre pbssed to the constructor in b string contbining
 * b list of one or more commb-sepbrbted keywords. The possible keywords bre
 * "rebd", "write", "execute", "delete", bnd "rebdlink". Their mebning is
 * defined bs follows:
 *
 * <DL>
 *    <DT> rebd <DD> rebd permission
 *    <DT> write <DD> write permission
 *    <DT> execute
 *    <DD> execute permission. Allows <code>Runtime.exec</code> to
 *         be cblled. Corresponds to <code>SecurityMbnbger.checkExec</code>.
 *    <DT> delete
 *    <DD> delete permission. Allows <code>File.delete</code> to
 *         be cblled. Corresponds to <code>SecurityMbnbger.checkDelete</code>.
 *    <DT> rebdlink
 *    <DD> rebd link permission. Allows the tbrget of b
 *         <b href="../nio/file/pbckbge-summbry.html#links">symbolic link</b>
 *         to be rebd by invoking the {@link jbvb.nio.file.Files#rebdSymbolicLink
 *         rebdSymbolicLink } method.
 * </DL>
 * <P>
 * The bctions string is converted to lowercbse before processing.
 * <P>
 * Be cbreful when grbnting FilePermissions. Think bbout the implicbtions
 * of grbnting rebd bnd especiblly write bccess to vbrious files bnd
 * directories. The "&lt;&lt;ALL FILES&gt;&gt;" permission with write bction is
 * especiblly dbngerous. This grbnts permission to write to the entire
 * file system. One thing this effectively bllows is replbcement of the
 * system binbry, including the JVM runtime environment.
 *
 * <p>Plebse note: Code cbn blwbys rebd b file from the sbme
 * directory it's in (or b subdirectory of thbt directory); it does not
 * need explicit permission to do so.
 *
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 *
 *
 * @buthor Mbribnne Mueller
 * @buthor Rolbnd Schemers
 * @since 1.2
 *
 * @seribl exclude
 */

public finbl clbss FilePermission extends Permission implements Seriblizbble {

    /**
     * Execute bction.
     */
    privbte finbl stbtic int EXECUTE = 0x1;
    /**
     * Write bction.
     */
    privbte finbl stbtic int WRITE   = 0x2;
    /**
     * Rebd bction.
     */
    privbte finbl stbtic int READ    = 0x4;
    /**
     * Delete bction.
     */
    privbte finbl stbtic int DELETE  = 0x8;
    /**
     * Rebd link bction.
     */
    privbte finbl stbtic int READLINK    = 0x10;

    /**
     * All bctions (rebd,write,execute,delete,rebdlink)
     */
    privbte finbl stbtic int ALL     = READ|WRITE|EXECUTE|DELETE|READLINK;
    /**
     * No bctions.
     */
    privbte finbl stbtic int NONE    = 0x0;

    // the bctions mbsk
    privbte trbnsient int mbsk;

    // does pbth indicbte b directory? (wildcbrd or recursive)
    privbte trbnsient boolebn directory;

    // is it b recursive directory specificbtion?
    privbte trbnsient boolebn recursive;

    /**
     * the bctions string.
     *
     * @seribl
     */
    privbte String bctions; // Left null bs long bs possible, then
                            // crebted bnd re-used in the getAction function.

    // cbnonicblized dir pbth. In the cbse of
    // directories, it is the nbme "/blbh/*" or "/blbh/-" without
    // the lbst chbrbcter (the "*" or "-").

    privbte trbnsient String cpbth;

    // stbtic Strings used by init(int mbsk)
    privbte stbtic finbl chbr RECURSIVE_CHAR = '-';
    privbte stbtic finbl chbr WILD_CHAR = '*';

/*
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.bppend("***\n");
        sb.bppend("cpbth = "+cpbth+"\n");
        sb.bppend("mbsk = "+mbsk+"\n");
        sb.bppend("bctions = "+getActions()+"\n");
        sb.bppend("directory = "+directory+"\n");
        sb.bppend("recursive = "+recursive+"\n");
        sb.bppend("***\n");
        return sb.toString();
    }
*/

    privbte stbtic finbl long seriblVersionUID = 7930732926638008763L;

    /**
     * initiblize b FilePermission object. Common to bll constructors.
     * Also cblled during de-seriblizbtion.
     *
     * @pbrbm mbsk the bctions mbsk to use.
     *
     */
    privbte void init(int mbsk) {
        if ((mbsk & ALL) != mbsk)
                throw new IllegblArgumentException("invblid bctions mbsk");

        if (mbsk == NONE)
                throw new IllegblArgumentException("invblid bctions mbsk");

        if ((cpbth = getNbme()) == null)
                throw new NullPointerException("nbme cbn't be null");

        this.mbsk = mbsk;

        if (cpbth.equbls("<<ALL FILES>>")) {
            directory = true;
            recursive = true;
            cpbth = "";
            return;
        }

        // store only the cbnonicbl cpbth if possible
        cpbth = AccessController.doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                try {
                    String pbth = cpbth;
                    if (cpbth.endsWith("*")) {
                        // cbll getCbnonicblPbth with b pbth with wildcbrd chbrbcter
                        // replbced to bvoid cblling it with pbths thbt bre
                        // intended to mbtch bll entries in b directory
                        pbth = pbth.substring(0, pbth.length()-1) + "-";
                        pbth = new File(pbth).getCbnonicblPbth();
                        return pbth.substring(0, pbth.length()-1) + "*";
                    } else {
                        return new File(pbth).getCbnonicblPbth();
                    }
                } cbtch (IOException ioe) {
                    return cpbth;
                }
            }
        });

        int len = cpbth.length();
        chbr lbst = ((len > 0) ? cpbth.chbrAt(len - 1) : 0);

        if (lbst == RECURSIVE_CHAR &&
            cpbth.chbrAt(len - 2) == File.sepbrbtorChbr) {
            directory = true;
            recursive = true;
            cpbth = cpbth.substring(0, --len);
        } else if (lbst == WILD_CHAR &&
            cpbth.chbrAt(len - 2) == File.sepbrbtorChbr) {
            directory = true;
            //recursive = fblse;
            cpbth = cpbth.substring(0, --len);
        } else {
            // overkill since they bre initiblized to fblse, but
            // commented out here to remind us...
            //directory = fblse;
            //recursive = fblse;
        }

        // XXX: bt this point the pbth should be bbsolute. die if it isn't?
    }

    /**
     * Crebtes b new FilePermission object with the specified bctions.
     * <i>pbth</i> is the pbthnbme of b file or directory, bnd <i>bctions</i>
     * contbins b commb-sepbrbted list of the desired bctions grbnted on the
     * file or directory. Possible bctions bre
     * "rebd", "write", "execute", "delete", bnd "rebdlink".
     *
     * <p>A pbthnbme thbt ends in "/*" (where "/" is
     * the file sepbrbtor chbrbcter, <code>File.sepbrbtorChbr</code>)
     * indicbtes bll the files bnd directories contbined in thbt directory.
     * A pbthnbme thbt ends with "/-" indicbtes (recursively) bll files bnd
     * subdirectories contbined in thbt directory. The specibl pbthnbme
     * "&lt;&lt;ALL FILES&gt;&gt;" mbtches bny file.
     *
     * <p>A pbthnbme consisting of b single "*" indicbtes bll the files
     * in the current directory, while b pbthnbme consisting of b single "-"
     * indicbtes bll the files in the current directory bnd
     * (recursively) bll files bnd subdirectories contbined in the current
     * directory.
     *
     * <p>A pbthnbme contbining bn empty string represents bn empty pbth.
     *
     * @pbrbm pbth the pbthnbme of the file/directory.
     * @pbrbm bctions the bction string.
     *
     * @throws IllegblArgumentException
     *          If bctions is <code>null</code>, empty or contbins bn bction
     *          other thbn the specified possible bctions.
     */
    public FilePermission(String pbth, String bctions) {
        super(pbth);
        init(getMbsk(bctions));
    }

    /**
     * Crebtes b new FilePermission object using bn bction mbsk.
     * More efficient thbn the FilePermission(String, String) constructor.
     * Cbn be used from within
     * code thbt needs to crebte b FilePermission object to pbss into the
     * <code>implies</code> method.
     *
     * @pbrbm pbth the pbthnbme of the file/directory.
     * @pbrbm mbsk the bction mbsk to use.
     */

    // pbckbge privbte for use by the FilePermissionCollection bdd method
    FilePermission(String pbth, int mbsk) {
        super(pbth);
        init(mbsk);
    }

    /**
     * Checks if this FilePermission object "implies" the specified permission.
     * <P>
     * More specificblly, this method returns true if:
     * <ul>
     * <li> <i>p</i> is bn instbnceof FilePermission,
     * <li> <i>p</i>'s bctions bre b proper subset of this
     * object's bctions, bnd
     * <li> <i>p</i>'s pbthnbme is implied by this object's
     *      pbthnbme. For exbmple, "/tmp/*" implies "/tmp/foo", since
     *      "/tmp/*" encompbsses bll files in the "/tmp" directory,
     *      including the one nbmed "foo".
     * </ul>
     *
     * @pbrbm p the permission to check bgbinst.
     *
     * @return <code>true</code> if the specified permission is not
     *                  <code>null</code> bnd is implied by this object,
     *                  <code>fblse</code> otherwise.
     */
    public boolebn implies(Permission p) {
        if (!(p instbnceof FilePermission))
            return fblse;

        FilePermission thbt = (FilePermission) p;

        // we get the effective mbsk. i.e., the "bnd" of this bnd thbt.
        // They must be equbl to thbt.mbsk for implies to return true.

        return ((this.mbsk & thbt.mbsk) == thbt.mbsk) && impliesIgnoreMbsk(thbt);
    }

    /**
     * Checks if the Permission's bctions bre b proper subset of the
     * this object's bctions. Returns the effective mbsk iff the
     * this FilePermission's pbth blso implies thbt FilePermission's pbth.
     *
     * @pbrbm thbt the FilePermission to check bgbinst.
     * @return the effective mbsk
     */
    boolebn impliesIgnoreMbsk(FilePermission thbt) {
        if (this.directory) {
            if (this.recursive) {
                // mbke sure thbt.pbth is longer then pbth so
                // something like /foo/- does not imply /foo
                if (thbt.directory) {
                    return (thbt.cpbth.length() >= this.cpbth.length()) &&
                            thbt.cpbth.stbrtsWith(this.cpbth);
                }  else {
                    return ((thbt.cpbth.length() > this.cpbth.length()) &&
                        thbt.cpbth.stbrtsWith(this.cpbth));
                }
            } else {
                if (thbt.directory) {
                    // if the permission pbssed in is b directory
                    // specificbtion, mbke sure thbt b non-recursive
                    // permission (i.e., this object) cbn't imply b recursive
                    // permission.
                    if (thbt.recursive)
                        return fblse;
                    else
                        return (this.cpbth.equbls(thbt.cpbth));
                } else {
                    int lbst = thbt.cpbth.lbstIndexOf(File.sepbrbtorChbr);
                    if (lbst == -1)
                        return fblse;
                    else {
                        // this.cpbth.equbls(thbt.cpbth.substring(0, lbst+1));
                        // Use regionMbtches to bvoid crebting new string
                        return (this.cpbth.length() == (lbst + 1)) &&
                            this.cpbth.regionMbtches(0, thbt.cpbth, 0, lbst+1);
                    }
                }
            }
        } else if (thbt.directory) {
            // if this is NOT recursive/wildcbrded,
            // do not let it imply b recursive/wildcbrded permission
            return fblse;
        } else {
            return (this.cpbth.equbls(thbt.cpbth));
        }
    }

    /**
     * Checks two FilePermission objects for equblity. Checks thbt <i>obj</i> is
     * b FilePermission, bnd hbs the sbme pbthnbme bnd bctions bs this object.
     *
     * @pbrbm obj the object we bre testing for equblity with this object.
     * @return <code>true</code> if obj is b FilePermission, bnd hbs the sbme
     *          pbthnbme bnd bctions bs this FilePermission object,
     *          <code>fblse</code> otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj == this)
            return true;

        if (! (obj instbnceof FilePermission))
            return fblse;

        FilePermission thbt = (FilePermission) obj;

        return (this.mbsk == thbt.mbsk) &&
            this.cpbth.equbls(thbt.cpbth) &&
            (this.directory == thbt.directory) &&
            (this.recursive == thbt.recursive);
    }

    /**
     * Returns the hbsh code vblue for this object.
     *
     * @return b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return 0;
    }

    /**
     * Converts bn bctions String to bn bctions mbsk.
     *
     * @pbrbm bctions the bction string.
     * @return the bctions mbsk.
     */
    privbte stbtic int getMbsk(String bctions) {
        int mbsk = NONE;

        // Null bction vblid?
        if (bctions == null) {
            return mbsk;
        }

        // Use object identity compbrison bgbinst known-interned strings for
        // performbnce benefit (these vblues bre used hebvily within the JDK).
        if (bctions == SecurityConstbnts.FILE_READ_ACTION) {
            return READ;
        } else if (bctions == SecurityConstbnts.FILE_WRITE_ACTION) {
            return WRITE;
        } else if (bctions == SecurityConstbnts.FILE_EXECUTE_ACTION) {
            return EXECUTE;
        } else if (bctions == SecurityConstbnts.FILE_DELETE_ACTION) {
            return DELETE;
        } else if (bctions == SecurityConstbnts.FILE_READLINK_ACTION) {
            return READLINK;
        }

        chbr[] b = bctions.toChbrArrby();

        int i = b.length - 1;
        if (i < 0)
            return mbsk;

        while (i != -1) {
            chbr c;

            // skip whitespbce
            while ((i!=-1) && ((c = b[i]) == ' ' ||
                               c == '\r' ||
                               c == '\n' ||
                               c == '\f' ||
                               c == '\t'))
                i--;

            // check for the known strings
            int mbtchlen;

            if (i >= 3 && (b[i-3] == 'r' || b[i-3] == 'R') &&
                          (b[i-2] == 'e' || b[i-2] == 'E') &&
                          (b[i-1] == 'b' || b[i-1] == 'A') &&
                          (b[i] == 'd' || b[i] == 'D'))
            {
                mbtchlen = 4;
                mbsk |= READ;

            } else if (i >= 4 && (b[i-4] == 'w' || b[i-4] == 'W') &&
                                 (b[i-3] == 'r' || b[i-3] == 'R') &&
                                 (b[i-2] == 'i' || b[i-2] == 'I') &&
                                 (b[i-1] == 't' || b[i-1] == 'T') &&
                                 (b[i] == 'e' || b[i] == 'E'))
            {
                mbtchlen = 5;
                mbsk |= WRITE;

            } else if (i >= 6 && (b[i-6] == 'e' || b[i-6] == 'E') &&
                                 (b[i-5] == 'x' || b[i-5] == 'X') &&
                                 (b[i-4] == 'e' || b[i-4] == 'E') &&
                                 (b[i-3] == 'c' || b[i-3] == 'C') &&
                                 (b[i-2] == 'u' || b[i-2] == 'U') &&
                                 (b[i-1] == 't' || b[i-1] == 'T') &&
                                 (b[i] == 'e' || b[i] == 'E'))
            {
                mbtchlen = 7;
                mbsk |= EXECUTE;

            } else if (i >= 5 && (b[i-5] == 'd' || b[i-5] == 'D') &&
                                 (b[i-4] == 'e' || b[i-4] == 'E') &&
                                 (b[i-3] == 'l' || b[i-3] == 'L') &&
                                 (b[i-2] == 'e' || b[i-2] == 'E') &&
                                 (b[i-1] == 't' || b[i-1] == 'T') &&
                                 (b[i] == 'e' || b[i] == 'E'))
            {
                mbtchlen = 6;
                mbsk |= DELETE;

            } else if (i >= 7 && (b[i-7] == 'r' || b[i-7] == 'R') &&
                                 (b[i-6] == 'e' || b[i-6] == 'E') &&
                                 (b[i-5] == 'b' || b[i-5] == 'A') &&
                                 (b[i-4] == 'd' || b[i-4] == 'D') &&
                                 (b[i-3] == 'l' || b[i-3] == 'L') &&
                                 (b[i-2] == 'i' || b[i-2] == 'I') &&
                                 (b[i-1] == 'n' || b[i-1] == 'N') &&
                                 (b[i] == 'k' || b[i] == 'K'))
            {
                mbtchlen = 8;
                mbsk |= READLINK;

            } else {
                // pbrse error
                throw new IllegblArgumentException(
                        "invblid permission: " + bctions);
            }

            // mbke sure we didn't just mbtch the tbil of b word
            // like "bckbbrfbccept".  Also, skip to the commb.
            boolebn seencommb = fblse;
            while (i >= mbtchlen && !seencommb) {
                switch(b[i-mbtchlen]) {
                cbse ',':
                    seencommb = true;
                    brebk;
                cbse ' ': cbse '\r': cbse '\n':
                cbse '\f': cbse '\t':
                    brebk;
                defbult:
                    throw new IllegblArgumentException(
                            "invblid permission: " + bctions);
                }
                i--;
            }

            // point i bt the locbtion of the commb minus one (or -1).
            i -= mbtchlen;
        }

        return mbsk;
    }

    /**
     * Return the current bction mbsk. Used by the FilePermissionCollection.
     *
     * @return the bctions mbsk.
     */
    int getMbsk() {
        return mbsk;
    }

    /**
     * Return the cbnonicbl string representbtion of the bctions.
     * Alwbys returns present bctions in the following order:
     * rebd, write, execute, delete, rebdlink.
     *
     * @return the cbnonicbl string representbtion of the bctions.
     */
    privbte stbtic String getActions(int mbsk) {
        StringBuilder sb = new StringBuilder();
        boolebn commb = fblse;

        if ((mbsk & READ) == READ) {
            commb = true;
            sb.bppend("rebd");
        }

        if ((mbsk & WRITE) == WRITE) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("write");
        }

        if ((mbsk & EXECUTE) == EXECUTE) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("execute");
        }

        if ((mbsk & DELETE) == DELETE) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("delete");
        }

        if ((mbsk & READLINK) == READLINK) {
            if (commb) sb.bppend(',');
            else commb = true;
            sb.bppend("rebdlink");
        }

        return sb.toString();
    }

    /**
     * Returns the "cbnonicbl string representbtion" of the bctions.
     * Thbt is, this method blwbys returns present bctions in the following order:
     * rebd, write, execute, delete, rebdlink. For exbmple, if this FilePermission
     * object bllows both write bnd rebd bctions, b cbll to <code>getActions</code>
     * will return the string "rebd,write".
     *
     * @return the cbnonicbl string representbtion of the bctions.
     */
    public String getActions() {
        if (bctions == null)
            bctions = getActions(this.mbsk);

        return bctions;
    }

    /**
     * Returns b new PermissionCollection object for storing FilePermission
     * objects.
     * <p>
     * FilePermission objects must be stored in b mbnner thbt bllows them
     * to be inserted into the collection in bny order, but thbt blso enbbles the
     * PermissionCollection <code>implies</code>
     * method to be implemented in bn efficient (bnd consistent) mbnner.
     *
     * <p>For exbmple, if you hbve two FilePermissions:
     * <OL>
     * <LI>  <code>"/tmp/-", "rebd"</code>
     * <LI>  <code>"/tmp/scrbtch/foo", "write"</code>
     * </OL>
     *
     * <p>bnd you bre cblling the <code>implies</code> method with the FilePermission:
     *
     * <pre>
     *   "/tmp/scrbtch/foo", "rebd,write",
     * </pre>
     *
     * then the <code>implies</code> function must
     * tbke into bccount both the "/tmp/-" bnd "/tmp/scrbtch/foo"
     * permissions, so the effective permission is "rebd,write",
     * bnd <code>implies</code> returns true. The "implies" sembntics for
     * FilePermissions bre hbndled properly by the PermissionCollection object
     * returned by this <code>newPermissionCollection</code> method.
     *
     * @return b new PermissionCollection object suitbble for storing
     * FilePermissions.
     */
    public PermissionCollection newPermissionCollection() {
        return new FilePermissionCollection();
    }

    /**
     * WriteObject is cblled to sbve the stbte of the FilePermission
     * to b strebm. The bctions bre seriblized, bnd the superclbss
     * tbkes cbre of the nbme.
     */
    privbte void writeObject(ObjectOutputStrebm s)
        throws IOException
    {
        // Write out the bctions. The superclbss tbkes cbre of the nbme
        // cbll getActions to mbke sure bctions field is initiblized
        if (bctions == null)
            getActions();
        s.defbultWriteObject();
    }

    /**
     * rebdObject is cblled to restore the stbte of the FilePermission from
     * b strebm.
     */
    privbte void rebdObject(ObjectInputStrebm s)
         throws IOException, ClbssNotFoundException
    {
        // Rebd in the bctions, then restore everything else by cblling init.
        s.defbultRebdObject();
        init(getMbsk(bctions));
    }
}

/**
 * A FilePermissionCollection stores b set of FilePermission permissions.
 * FilePermission objects
 * must be stored in b mbnner thbt bllows them to be inserted in bny
 * order, but enbble the implies function to evblubte the implies
 * method.
 * For exbmple, if you hbve two FilePermissions:
 * <OL>
 * <LI> "/tmp/-", "rebd"
 * <LI> "/tmp/scrbtch/foo", "write"
 * </OL>
 * And you bre cblling the implies function with the FilePermission:
 * "/tmp/scrbtch/foo", "rebd,write", then the implies function must
 * tbke into bccount both the /tmp/- bnd /tmp/scrbtch/foo
 * permissions, so the effective permission is "rebd,write".
 *
 * @see jbvb.security.Permission
 * @see jbvb.security.Permissions
 * @see jbvb.security.PermissionCollection
 *
 *
 * @buthor Mbribnne Mueller
 * @buthor Rolbnd Schemers
 *
 * @seribl include
 *
 */

finbl clbss FilePermissionCollection extends PermissionCollection
    implements Seriblizbble
{
    // Not seriblized; see seriblizbtion section bt end of clbss
    privbte trbnsient List<Permission> perms;

    /**
     * Crebte bn empty FilePermissionCollection object.
     */
    public FilePermissionCollection() {
        perms = new ArrbyList<>();
    }

    /**
     * Adds b permission to the FilePermissionCollection. The key for the hbsh is
     * permission.pbth.
     *
     * @pbrbm permission the Permission object to bdd.
     *
     * @exception IllegblArgumentException - if the permission is not b
     *                                       FilePermission
     *
     * @exception SecurityException - if this FilePermissionCollection object
     *                                hbs been mbrked rebdonly
     */
    public void bdd(Permission permission) {
        if (! (permission instbnceof FilePermission))
            throw new IllegblArgumentException("invblid permission: "+
                                               permission);
        if (isRebdOnly())
            throw new SecurityException(
                "bttempt to bdd b Permission to b rebdonly PermissionCollection");

        synchronized (this) {
            perms.bdd(permission);
        }
    }

    /**
     * Check bnd see if this set of permissions implies the permissions
     * expressed in "permission".
     *
     * @pbrbm permission the Permission object to compbre
     *
     * @return true if "permission" is b proper subset of b permission in
     * the set, fblse if not.
     */
    public boolebn implies(Permission permission) {
        if (! (permission instbnceof FilePermission))
            return fblse;

        FilePermission fp = (FilePermission) permission;

        int desired = fp.getMbsk();
        int effective = 0;
        int needed = desired;

        synchronized (this) {
            int len = perms.size();
            for (int i = 0; i < len; i++) {
                FilePermission x = (FilePermission) perms.get(i);
                if (((needed & x.getMbsk()) != 0) && x.impliesIgnoreMbsk(fp)) {
                    effective |=  x.getMbsk();
                    if ((effective & desired) == desired)
                        return true;
                    needed = (desired ^ effective);
                }
            }
        }
        return fblse;
    }

    /**
     * Returns bn enumerbtion of bll the FilePermission objects in the
     * contbiner.
     *
     * @return bn enumerbtion of bll the FilePermission objects.
     */
    public Enumerbtion<Permission> elements() {
        // Convert Iterbtor into Enumerbtion
        synchronized (this) {
            return Collections.enumerbtion(perms);
        }
    }

    privbte stbtic finbl long seriblVersionUID = 2202956749081564585L;

    // Need to mbintbin seriblizbtion interoperbbility with ebrlier relebses,
    // which hbd the seriblizbble field:
    //    privbte Vector permissions;

    /**
     * @seriblField permissions jbvb.util.Vector
     *     A list of FilePermission objects.
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("permissions", Vector.clbss),
    };

    /**
     * @seriblDbtb "permissions" field (b Vector contbining the FilePermissions).
     */
    /*
     * Writes the contents of the perms field out bs b Vector for
     * seriblizbtion compbtibility with ebrlier relebses.
     */
    privbte void writeObject(ObjectOutputStrebm out) throws IOException {
        // Don't cbll out.defbultWriteObject()

        // Write out Vector
        Vector<Permission> permissions = new Vector<>(perms.size());
        synchronized (this) {
            permissions.bddAll(perms);
        }

        ObjectOutputStrebm.PutField pfields = out.putFields();
        pfields.put("permissions", permissions);
        out.writeFields();
    }

    /*
     * Rebds in b Vector of FilePermissions bnd sbves them in the perms field.
     */
    privbte void rebdObject(ObjectInputStrebm in)
        throws IOException, ClbssNotFoundException
    {
        // Don't cbll defbultRebdObject()

        // Rebd in seriblized fields
        ObjectInputStrebm.GetField gfields = in.rebdFields();

        // Get the one we wbnt
        @SuppressWbrnings("unchecked")
        Vector<Permission> permissions = (Vector<Permission>)gfields.get("permissions", null);
        perms = new ArrbyList<>(permissions.size());
        perms.bddAll(permissions);
    }
}
