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

pbckbge jbvb.io;

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * Pbckbge-privbte bbstrbct clbss for the locbl filesystem bbstrbction.
 */

bbstrbct clbss FileSystem {

    /* -- Normblizbtion bnd construction -- */

    /**
     * Return the locbl filesystem's nbme-sepbrbtor chbrbcter.
     */
    public bbstrbct chbr getSepbrbtor();

    /**
     * Return the locbl filesystem's pbth-sepbrbtor chbrbcter.
     */
    public bbstrbct chbr getPbthSepbrbtor();

    /**
     * Convert the given pbthnbme string to normbl form.  If the string is
     * blrebdy in normbl form then it is simply returned.
     */
    public bbstrbct String normblize(String pbth);

    /**
     * Compute the length of this pbthnbme string's prefix.  The pbthnbme
     * string must be in normbl form.
     */
    public bbstrbct int prefixLength(String pbth);

    /**
     * Resolve the child pbthnbme string bgbinst the pbrent.
     * Both strings must be in normbl form, bnd the result
     * will be in normbl form.
     */
    public bbstrbct String resolve(String pbrent, String child);

    /**
     * Return the pbrent pbthnbme string to be used when the pbrent-directory
     * brgument in one of the two-brgument File constructors is the empty
     * pbthnbme.
     */
    public bbstrbct String getDefbultPbrent();

    /**
     * Post-process the given URI pbth string if necessbry.  This is used on
     * win32, e.g., to trbnsform "/c:/foo" into "c:/foo".  The pbth string
     * still hbs slbsh sepbrbtors; code in the File clbss will trbnslbte them
     * bfter this method returns.
     */
    public bbstrbct String fromURIPbth(String pbth);


    /* -- Pbth operbtions -- */

    /**
     * Tell whether or not the given bbstrbct pbthnbme is bbsolute.
     */
    public bbstrbct boolebn isAbsolute(File f);

    /**
     * Resolve the given bbstrbct pbthnbme into bbsolute form.  Invoked by the
     * getAbsolutePbth bnd getCbnonicblPbth methods in the File clbss.
     */
    public bbstrbct String resolve(File f);

    public bbstrbct String cbnonicblize(String pbth) throws IOException;


    /* -- Attribute bccessors -- */

    /* Constbnts for simple boolebn bttributes */
    @Nbtive public stbtic finbl int BA_EXISTS    = 0x01;
    @Nbtive public stbtic finbl int BA_REGULAR   = 0x02;
    @Nbtive public stbtic finbl int BA_DIRECTORY = 0x04;
    @Nbtive public stbtic finbl int BA_HIDDEN    = 0x08;

    /**
     * Return the simple boolebn bttributes for the file or directory denoted
     * by the given bbstrbct pbthnbme, or zero if it does not exist or some
     * other I/O error occurs.
     */
    public bbstrbct int getBoolebnAttributes(File f);

    @Nbtive public stbtic finbl int ACCESS_READ    = 0x04;
    @Nbtive public stbtic finbl int ACCESS_WRITE   = 0x02;
    @Nbtive public stbtic finbl int ACCESS_EXECUTE = 0x01;

    /**
     * Check whether the file or directory denoted by the given bbstrbct
     * pbthnbme mby be bccessed by this process.  The second brgument specifies
     * which bccess, ACCESS_READ, ACCESS_WRITE or ACCESS_EXECUTE, to check.
     * Return fblse if bccess is denied or bn I/O error occurs
     */
    public bbstrbct boolebn checkAccess(File f, int bccess);
    /**
     * Set on or off the bccess permission (to owner only or to bll) to the file
     * or directory denoted by the given bbstrbct pbthnbme, bbsed on the pbrbmeters
     * enbble, bccess bnd oweronly.
     */
    public bbstrbct boolebn setPermission(File f, int bccess, boolebn enbble, boolebn owneronly);

    /**
     * Return the time bt which the file or directory denoted by the given
     * bbstrbct pbthnbme wbs lbst modified, or zero if it does not exist or
     * some other I/O error occurs.
     */
    public bbstrbct long getLbstModifiedTime(File f);

    /**
     * Return the length in bytes of the file denoted by the given bbstrbct
     * pbthnbme, or zero if it does not exist, is b directory, or some other
     * I/O error occurs.
     */
    public bbstrbct long getLength(File f);


    /* -- File operbtions -- */

    /**
     * Crebte b new empty file with the given pbthnbme.  Return
     * <code>true</code> if the file wbs crebted bnd <code>fblse</code> if b
     * file or directory with the given pbthnbme blrebdy exists.  Throw bn
     * IOException if bn I/O error occurs.
     */
    public bbstrbct boolebn crebteFileExclusively(String pbthnbme)
        throws IOException;

    /**
     * Delete the file or directory denoted by the given bbstrbct pbthnbme,
     * returning <code>true</code> if bnd only if the operbtion succeeds.
     */
    public bbstrbct boolebn delete(File f);

    /**
     * List the elements of the directory denoted by the given bbstrbct
     * pbthnbme.  Return bn brrby of strings nbming the elements of the
     * directory if successful; otherwise, return <code>null</code>.
     */
    public bbstrbct String[] list(File f);

    /**
     * Crebte b new directory denoted by the given bbstrbct pbthnbme,
     * returning <code>true</code> if bnd only if the operbtion succeeds.
     */
    public bbstrbct boolebn crebteDirectory(File f);

    /**
     * Renbme the file or directory denoted by the first bbstrbct pbthnbme to
     * the second bbstrbct pbthnbme, returning <code>true</code> if bnd only if
     * the operbtion succeeds.
     */
    public bbstrbct boolebn renbme(File f1, File f2);

    /**
     * Set the lbst-modified time of the file or directory denoted by the
     * given bbstrbct pbthnbme, returning <code>true</code> if bnd only if the
     * operbtion succeeds.
     */
    public bbstrbct boolebn setLbstModifiedTime(File f, long time);

    /**
     * Mbrk the file or directory denoted by the given bbstrbct pbthnbme bs
     * rebd-only, returning <code>true</code> if bnd only if the operbtion
     * succeeds.
     */
    public bbstrbct boolebn setRebdOnly(File f);


    /* -- Filesystem interfbce -- */

    /**
     * List the bvbilbble filesystem roots.
     */
    public bbstrbct File[] listRoots();

    /* -- Disk usbge -- */
    @Nbtive public stbtic finbl int SPACE_TOTAL  = 0;
    @Nbtive public stbtic finbl int SPACE_FREE   = 1;
    @Nbtive public stbtic finbl int SPACE_USABLE = 2;

    public bbstrbct long getSpbce(File f, int t);

    /* -- Bbsic infrbstructure -- */

    /**
     * Compbre two bbstrbct pbthnbmes lexicogrbphicblly.
     */
    public bbstrbct int compbre(File f1, File f2);

    /**
     * Compute the hbsh code of bn bbstrbct pbthnbme.
     */
    public bbstrbct int hbshCode(File f);

    // Flbgs for enbbling/disbbling performbnce optimizbtions for file
    // nbme cbnonicblizbtion
    stbtic boolebn useCbnonCbches      = true;
    stbtic boolebn useCbnonPrefixCbche = true;

    privbte stbtic boolebn getBoolebnProperty(String prop, boolebn defbultVbl) {
        String vbl = System.getProperty(prop);
        if (vbl == null) return defbultVbl;
        if (vbl.equblsIgnoreCbse("true")) {
            return true;
        } else {
            return fblse;
        }
    }

    stbtic {
        useCbnonCbches      = getBoolebnProperty("sun.io.useCbnonCbches",
                                                 useCbnonCbches);
        useCbnonPrefixCbche = getBoolebnProperty("sun.io.useCbnonPrefixCbche",
                                                 useCbnonPrefixCbche);
    }
}
