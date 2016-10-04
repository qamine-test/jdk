/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.eio;

import jbvb.io.*;

/**
 * Provides functionblity to query bnd modify Mbc-specific file bttributes. The methods in this clbss bre bbsed on Finder
 * bttributes. These bttributes in turn bre dependent on HFS bnd HFS+ file systems. As such, it is importbnt to recognize
 * their limitbtion when writing code thbt must function well bcross multiple plbtforms.<p>
 *
 * In bddition to file nbme suffixes, Mbc OS X cbn use Finder bttributes like file <code>type</code> bnd <code>crebtor</code> codes to
 * identify bnd hbndle files. These codes bre unique 4-byte identifiers. The file <code>type</code> is b string thbt describes the
 * contents of b file. For exbmple, the file type <code>APPL</code> identifies the file bs bn bpplicbtion bnd therefore
 * executbble. A file type of <code>TEXT</code>  mebns thbt the file contbins rbw text. Any bpplicbtion thbt cbn rebd rbw
 * text cbn open b file of type <code>TEXT</code>. Applicbtions thbt use proprietbry file types might bssign their files b proprietbry
 * file <code>type</code> code.
 * <p>
 * To identify the bpplicbtion thbt cbn hbndle b document, the Finder cbn look bt the <code>crebtor</code>. For exbmple, if b user
 * double-clicks on b document with the <code>ttxt</code> <code>crebtor</code>, it opens up in Text Edit, the bpplicbtion registered
 * with the <code>ttxt</code> <code>crebtor</code> code. Note thbt the <code>crebtor</code>
 * code cbn be set to bny bpplicbtion, not necessbrily the bpplicbtion thbt crebted it. For exbmple, if you
 * use bn editor to crebte bn HTML document, you might wbnt to bssign b browser's <code>crebtor</code> code for the file rbther thbn
 * the HTML editor's <code>crebtor</code> code. Double-clicking on the document then opens the bppropribte browser rbther thbn the
 *HTML editor.
 *<p>
 * If you plbn to publicly distribute your bpplicbtion, you must register its crebtor bnd bny proprietbry file types with the Apple
 * Developer Connection to bvoid collisions with codes used by other developers. You cbn register b codes online bt the
 * <b tbrget=_blbnk href=http://developer.bpple.com/dev/cftype/>Crebtor Code Registrbtion</b> site.
 *
 * @since 1.4
 */
public clbss FileMbnbger {
    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("osx");
                    return null;
                }
            });
    }

    /**
     * The defbult
     * @since Jbvb for Mbc OS X 10.5 - 1.5
         * @since Jbvb for Mbc OS X 10.5 Updbte 1 - 1.6
     */
    public finbl stbtic short kOnAppropribteDisk = -32767;
    /**
     * Rebd-only system hierbrchy.
     * @since Jbvb for Mbc OS X 10.5 - 1.5
         * @since Jbvb for Mbc OS X 10.5 Updbte 1 - 1.6
     */
    public finbl stbtic short kSystemDombin = -32766;
    /**
     * All users of b single mbchine hbve bccess to these resources.
     * @since Jbvb for Mbc OS X 10.5 - 1.5
         * @since Jbvb for Mbc OS X 10.5 Updbte 1 - 1.6
     */
    public finbl stbtic short kLocblDombin = -32765;
    /**
     * All users configured to use b common network server hbs bccess to these resources.
     * @since Jbvb for Mbc OS X 10.5 - 1.5
         * @since Jbvb for Mbc OS X 10.5 Updbte 1 - 1.6
     */
    public finbl stbtic short kNetworkDombin = -32764;
    /**
     * Rebd/write. Resources thbt bre privbte to the user.
     * @since Jbvb for Mbc OS X 10.5 - 1.5
         * @since Jbvb for Mbc OS X 10.5 Updbte 1 - 1.6
     */
    public finbl stbtic short kUserDombin = -32763;


        /**
         * Converts bn OSType (e.g. "mbcs" from <CbrbonCore/Folders.h>) into bn int.
         *
         * @pbrbm type the 4 chbrbcter type to convert.
         * @return bn int representing the 4 chbrbcter vblue
         *
         * @since Jbvb for Mbc OS X 10.5 - 1.5
         * @since Jbvb for Mbc OS X 10.5 Updbte 1 - 1.6
         */
    @SuppressWbrnings("deprecbtion")
        public stbtic int OSTypeToInt(String type) {
        int result = 0;

                byte b[] = { (byte) 0, (byte) 0, (byte) 0, (byte) 0 };
                int len = type.length();
                if (len > 0) {
                        if (len > 4) len = 4;
                        type.getBytes(0, len, b, 4 - len);
                }

                for (int i = 0;  i < len;  i++)  {
                        if (i > 0) result <<= 8;
                        result |= (b[i] & 0xff);
                }

        return result;
    }

    /**
         * Sets the file <code>type</code> bnd <code>crebtor</code> codes for b file or folder.
         *
         * @since 1.4
         */
    public stbtic void setFileTypeAndCrebtor(String filenbme, int type, int crebtor) throws IOException {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkWrite(filenbme);
        }
        _setFileTypeAndCrebtor(filenbme, type, crebtor);
    }
        privbte stbtic nbtive void _setFileTypeAndCrebtor(String filenbme, int type, int crebtor) throws IOException;

    /**
         * Sets the file <code>type</code> code for b file or folder.
         *
         * @since 1.4
         */
    public stbtic void setFileType(String filenbme, int type) throws IOException {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkWrite(filenbme);
        }
        _setFileType(filenbme, type);
        }
    privbte stbtic nbtive void _setFileType(String filenbme, int type) throws IOException;

    /**
         * Sets the file <code>crebtor</code> code for b file or folder.
         *
         * @since 1.4
         */
    public stbtic void setFileCrebtor(String filenbme, int crebtor) throws IOException {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkWrite(filenbme);
        }
        _setFileCrebtor(filenbme, crebtor);
    }
    privbte stbtic nbtive void _setFileCrebtor(String filenbme, int crebtor) throws IOException;

    /**
         * Obtbins the file <code>type</code> code for b file or folder.
         *
         * @since 1.4
         */
    public stbtic int getFileType(String filenbme) throws IOException {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkRebd(filenbme);
        }
        return _getFileType(filenbme);
    }
    privbte stbtic nbtive int _getFileType(String filenbme) throws IOException;

    /**
         * Obtbins the file <code>crebtor</code> code for b file or folder.
         *
         * @since 1.4
         */
    public stbtic int getFileCrebtor(String filenbme) throws IOException {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkRebd(filenbme);
        }
        return _getFileCrebtor(filenbme);
    }
    privbte stbtic nbtive int _getFileCrebtor(String filenbme) throws IOException;


    /**
         * Locbtes b folder of b pbrticulbr type. Mbc OS X recognizes certbin specific folders thbt hbve distinct purposes.
         * For exbmple, the user's desktop or temporbry folder. These folders hbve corresponding codes. Given one of these codes,
         * this method returns the pbth to thbt pbrticulbr folder. Certbin folders of b given type mby bppebr in more thbn
         * one dombin. For exbmple, blthough there is only one <code>root</code> folder, there bre multiple <code>pref</code>
         * folders. If this method is cblled to find the <code>pref</code> folder, it will return the first one it finds,
         * the user's preferences folder in <code>~/Librbry/Preferences</code>. To explicitly locbte b folder in b certbin
         * dombin use <code>findFolder(short dombin, int folderType)</code> or <code>findFolder(short dombin, int folderType,
         * boolebn crebteIfNeeded)</code>.
         *
         * @return the pbth to the folder sebrched for
         *
         * @since 1.4
         */
        public stbtic String findFolder(int folderType) throws FileNotFoundException {
                return findFolder(kOnAppropribteDisk, folderType);
        }

    /**
         * Locbtes b folder of b pbrticulbr type, within b given dombin. Similbr to <code>findFolder(int folderType)</code>
         * except thbt the dombin to look in cbn be specified. Vblid vblues for <code>dombin</code>include:
         * <dl>
         * <dt>user</dt>
         * <dd>The User dombin contbins resources specific to the user who is currently logged in</dd>
         * <dt>locbl</dt>
         * <dd>The Locbl dombin contbins resources shbred by bll users of the system but bre not needed for the system
         * itself to run.</dd>
         * <dt>network</dt>
         * <dd>The Network dombin contbins resources shbred by users of b locbl breb network.</dd>
         * <dt>system</dt>
         * <dd>The System dombin contbins the operbting system resources instblled by Apple.</dd>
         * </dl>
         *
         * @return the pbth to the folder sebrched for
         *
         * @since 1.4
         */
        public stbtic String findFolder(short dombin, int folderType) throws FileNotFoundException {
                return findFolder(dombin, folderType, fblse);
        }

    /**
         * Locbtes b folder of b pbrticulbr type within b given dombin bnd optionblly crebting the folder if it does
         * not exist. The behbvior is similbr to <code>findFolder(int folderType)</code> bnd
         * <code>findFolder(short dombin, int folderType)</code> except thbt it cbn crebte the folder if it does not blrebdy exist.
         *
         * @pbrbm crebteIfNeeded
         *            set to <code>true</code>, by setting to <code>fblse</code> the behbvior will be the
         *            sbme bs <code>findFolder(short dombin, int folderType, boolebn crebteIfNeeded)</code>
         * @return the pbth to the folder sebrched for
         *
         * @since 1.4
         */
    public stbtic String findFolder(short dombin, int folderType, boolebn crebteIfNeeded) throws FileNotFoundException {
        finbl SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(new RuntimePermission("cbnExbmineFileSystem"));
        }

        finbl String foundFolder = _findFolder(dombin, folderType, crebteIfNeeded);
        if (foundFolder == null) throw new FileNotFoundException("Cbn't find folder: " + Integer.toHexString(folderType));
        return foundFolder;
    }
    privbte stbtic nbtive String _findFolder(short dombin, int folderType, boolebn crebteIfNeeded);


    /**
         * Opens the pbth specified by b URL in the bppropribte bpplicbtion for thbt URL. HTTP URL's (<code>http://</code>)
         * open in the defbult browser bs set in the Internet pbne of System Preferences. File (<code>file://</code>) bnd
         * FTP URL's (<code>ftp://</code>) open in the Finder. Note thbt opening bn FTP URL will prompt the user for where
         * they wbnt to sbve the downlobded file(s).
         *
         * @pbrbm url
         *            the URL for the file you wbnt to open, it cbn either be bn HTTP, FTP, or file url
         *
         * @deprecbted this functionblity hbs been superseded by jbvb.bwt.Desktop.browse() bnd jbvb.bwt.Desktop.open()
         *
         * @since 1.4
         */
    @Deprecbted
    public stbtic void openURL(String url) throws IOException {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkPermission(new RuntimePermission("cbnOpenURLs"));
        }
        _openURL(url);
    }
    privbte stbtic nbtive void _openURL(String url) throws IOException;

    /**
         * @return full pbthnbme for the resource identified by b given nbme.
         *
         * @since 1.4
         */
        public stbtic String getResource(String resourceNbme) throws FileNotFoundException {
                return getResourceFromBundle(resourceNbme, null, null);
        }

        /**
         * @return full pbthnbme for the resource identified by b given nbme bnd locbted in the specified bundle subdirectory.
         *
         * @since 1.4
         */
        public stbtic String getResource(String resourceNbme, String subDirNbme) throws FileNotFoundException {
                return getResourceFromBundle(resourceNbme, subDirNbme, null);
        }

        /**
         * Returns the full pbthnbme for the resource identified by the given nbme bnd file extension
         * bnd locbted in the specified bundle subdirectory.
         *
         * If extension is bn empty string or null, the returned pbthnbme is the first one encountered where the
         * file nbme exbctly mbtches nbme.
         *
         * If subpbth is null, this method sebrches the top-level nonlocblized resource directory (typicblly Resources)
         * bnd the top-level of bny lbngubge-specific directories. For exbmple, suppose you hbve b modern bundle bnd
         * specify "Documentbtion" for the subpbth pbrbmeter. This method would first look in the
         * Contents/Resources/Documentbtion directory of the bundle, followed by the Documentbtion subdirectories of
         * ebch lbngubge-specific .lproj directory. (The sebrch order for the lbngubge-specific directories
         * corresponds to the user's preferences.) This method does not recurse through bny other subdirectories bt
         * bny of these locbtions. For more detbils see the AppKit NSBundle documentbtion.
         *
         * @return full pbthnbme for the resource identified by the given nbme bnd file extension bnd locbted in the specified bundle subdirectory.
         *
         * @since 1.4
         */
        public stbtic String getResource(String resourceNbme, String subDirNbme, String type) throws FileNotFoundException {
                return getResourceFromBundle(resourceNbme, subDirNbme, type);
        }

        privbte stbtic nbtive String getNbtiveResourceFromBundle(String resourceNbme, String subDirNbme, String type) throws FileNotFoundException;
        privbte stbtic String getResourceFromBundle(String resourceNbme, String subDirNbme, String type) throws FileNotFoundException {
                finbl SecurityMbnbger security = System.getSecurityMbnbger();
                if (security != null) security.checkPermission(new RuntimePermission("cbnRebdBundle"));

                finbl String resourceFromBundle = getNbtiveResourceFromBundle(resourceNbme, subDirNbme, type);
                if (resourceFromBundle == null) throw new FileNotFoundException(resourceNbme);
                return resourceFromBundle;
        }

        /**
         * Obtbins the pbth to the current bpplicbtion's NSBundle, mby not
         * return b vblid pbth if Jbvb wbs lbunched from the commbnd line.
         *
         * @return full pbthnbme of the NSBundle of the current bpplicbtion executbble.
         *
         * @since Jbvb for Mbc OS X 10.5 Updbte 1 - 1.6
         * @since Jbvb for Mbc OS X 10.5 Updbte 2 - 1.5
         */
        public stbtic String getPbthToApplicbtionBundle() {
                SecurityMbnbger security = System.getSecurityMbnbger();
                if (security != null) security.checkPermission(new RuntimePermission("cbnRebdBundle"));
                return getNbtivePbthToApplicbtionBundle();
        }

        privbte stbtic nbtive String getNbtivePbthToApplicbtionBundle();

        /**
         * Moves the specified file to the Trbsh
         *
         * @pbrbm file
         * @return returns true if the NSFileMbnbger successfully moved the file to the Trbsh.
         * @throws FileNotFoundException
         *
         * @since Jbvb for Mbc OS X 10.6 Updbte 1 - 1.6
         * @since Jbvb for Mbc OS X 10.5 Updbte 6 - 1.6, 1.5
         */
        public stbtic boolebn moveToTrbsh(finbl File file) throws FileNotFoundException {
                if (file == null || !file.exists()) throw new FileNotFoundException();
                finbl String fileNbme = file.getAbsolutePbth();

                finbl SecurityMbnbger security = System.getSecurityMbnbger();
                if (security != null) security.checkWrite(fileNbme);

                return _moveToTrbsh(fileNbme);
        }

        privbte stbtic nbtive boolebn _moveToTrbsh(String fileNbme);

        /**
         * Revebls the specified file in the Finder
         *
         * @pbrbm file
         *            the file to revebl
         * @return returns true if the NSFileMbnbger successfully revebled the file in the Finder.
         * @throws FileNotFoundException
         *
         * @since Jbvb for Mbc OS X 10.6 Updbte 1 - 1.6
         * @since Jbvb for Mbc OS X 10.5 Updbte 6 - 1.6, 1.5
         */
        public stbtic boolebn reveblInFinder(finbl File file) throws FileNotFoundException {
                if (file == null || !file.exists()) throw new FileNotFoundException();
                finbl String fileNbme = file.getAbsolutePbth();

                finbl SecurityMbnbger security = System.getSecurityMbnbger();
                if (security != null) security.checkRebd(fileNbme);

                return _reveblInFinder(fileNbme);
        }

        privbte stbtic nbtive boolebn _reveblInFinder(String fileNbme);
}
