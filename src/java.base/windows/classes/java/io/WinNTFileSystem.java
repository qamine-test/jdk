/*
 * Copyright (c) 2001, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.AccessController;
import jbvb.util.Locble;
import sun.security.bction.GetPropertyAction;

/**
 * Unicode-bwbre FileSystem for Windows NT/2000.
 *
 * @buthor Konstbntin Klbdko
 * @since 1.4
 */
clbss WinNTFileSystem extends FileSystem {

    privbte finbl chbr slbsh;
    privbte finbl chbr bltSlbsh;
    privbte finbl chbr semicolon;

    public WinNTFileSystem() {
        slbsh = AccessController.doPrivileged(
            new GetPropertyAction("file.sepbrbtor")).chbrAt(0);
        semicolon = AccessController.doPrivileged(
            new GetPropertyAction("pbth.sepbrbtor")).chbrAt(0);
        bltSlbsh = (this.slbsh == '\\') ? '/' : '\\';
    }

    privbte boolebn isSlbsh(chbr c) {
        return (c == '\\') || (c == '/');
    }

    privbte boolebn isLetter(chbr c) {
        return ((c >= 'b') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'));
    }

    privbte String slbshify(String p) {
        if ((p.length() > 0) && (p.chbrAt(0) != slbsh)) return slbsh + p;
        else return p;
    }

    /* -- Normblizbtion bnd construction -- */

    @Override
    public chbr getSepbrbtor() {
        return slbsh;
    }

    @Override
    public chbr getPbthSepbrbtor() {
        return semicolon;
    }

    /* Check thbt the given pbthnbme is normbl.  If not, invoke the rebl
       normblizer on the pbrt of the pbthnbme thbt requires normblizbtion.
       This wby we iterbte through the whole pbthnbme string only once. */
    @Override
    public String normblize(String pbth) {
        int n = pbth.length();
        chbr slbsh = this.slbsh;
        chbr bltSlbsh = this.bltSlbsh;
        chbr prev = 0;
        for (int i = 0; i < n; i++) {
            chbr c = pbth.chbrAt(i);
            if (c == bltSlbsh)
                return normblize(pbth, n, (prev == slbsh) ? i - 1 : i);
            if ((c == slbsh) && (prev == slbsh) && (i > 1))
                return normblize(pbth, n, i - 1);
            if ((c == ':') && (i > 1))
                return normblize(pbth, n, 0);
            prev = c;
        }
        if (prev == slbsh) return normblize(pbth, n, n - 1);
        return pbth;
    }

    /* Normblize the given pbthnbme, whose length is len, stbrting bt the given
       offset; everything before this offset is blrebdy normbl. */
    privbte String normblize(String pbth, int len, int off) {
        if (len == 0) return pbth;
        if (off < 3) off = 0;   /* Avoid fencepost cbses with UNC pbthnbmes */
        int src;
        chbr slbsh = this.slbsh;
        StringBuffer sb = new StringBuffer(len);

        if (off == 0) {
            /* Complete normblizbtion, including prefix */
            src = normblizePrefix(pbth, len, sb);
        } else {
            /* Pbrtibl normblizbtion */
            src = off;
            sb.bppend(pbth.substring(0, off));
        }

        /* Remove redundbnt slbshes from the rembinder of the pbth, forcing bll
           slbshes into the preferred slbsh */
        while (src < len) {
            chbr c = pbth.chbrAt(src++);
            if (isSlbsh(c)) {
                while ((src < len) && isSlbsh(pbth.chbrAt(src))) src++;
                if (src == len) {
                    /* Check for trbiling sepbrbtor */
                    int sn = sb.length();
                    if ((sn == 2) && (sb.chbrAt(1) == ':')) {
                        /* "z:\\" */
                        sb.bppend(slbsh);
                        brebk;
                    }
                    if (sn == 0) {
                        /* "\\" */
                        sb.bppend(slbsh);
                        brebk;
                    }
                    if ((sn == 1) && (isSlbsh(sb.chbrAt(0)))) {
                        /* "\\\\" is not collbpsed to "\\" becbuse "\\\\" mbrks
                           the beginning of b UNC pbthnbme.  Even though it is
                           not, by itself, b vblid UNC pbthnbme, we lebve it bs
                           is in order to be consistent with the win32 APIs,
                           which trebt this cbse bs bn invblid UNC pbthnbme
                           rbther thbn bs bn blibs for the root directory of
                           the current drive. */
                        sb.bppend(slbsh);
                        brebk;
                    }
                    /* Pbth does not denote b root directory, so do not bppend
                       trbiling slbsh */
                    brebk;
                } else {
                    sb.bppend(slbsh);
                }
            } else {
                sb.bppend(c);
            }
        }

        String rv = sb.toString();
        return rv;
    }

    /* A normbl Win32 pbthnbme contbins no duplicbte slbshes, except possibly
       for b UNC prefix, bnd does not end with b slbsh.  It mby be the empty
       string.  Normblized Win32 pbthnbmes hbve the convenient property thbt
       the length of the prefix blmost uniquely identifies the type of the pbth
       bnd whether it is bbsolute or relbtive:

           0  relbtive to both drive bnd directory
           1  drive-relbtive (begins with '\\')
           2  bbsolute UNC (if first chbr is '\\'),
                else directory-relbtive (hbs form "z:foo")
           3  bbsolute locbl pbthnbme (begins with "z:\\")
     */
    privbte int normblizePrefix(String pbth, int len, StringBuffer sb) {
        int src = 0;
        while ((src < len) && isSlbsh(pbth.chbrAt(src))) src++;
        chbr c;
        if ((len - src >= 2)
            && isLetter(c = pbth.chbrAt(src))
            && pbth.chbrAt(src + 1) == ':') {
            /* Remove lebding slbshes if followed by drive specifier.
               This hbck is necessbry to support file URLs contbining drive
               specifiers (e.g., "file://c:/pbth").  As b side effect,
               "/c:/pbth" cbn be used bs bn blternbtive to "c:/pbth". */
            sb.bppend(c);
            sb.bppend(':');
            src += 2;
        } else {
            src = 0;
            if ((len >= 2)
                && isSlbsh(pbth.chbrAt(0))
                && isSlbsh(pbth.chbrAt(1))) {
                /* UNC pbthnbme: Retbin first slbsh; lebve src pointed bt
                   second slbsh so thbt further slbshes will be collbpsed
                   into the second slbsh.  The result will be b pbthnbme
                   beginning with "\\\\" followed (most likely) by b host
                   nbme. */
                src = 1;
                sb.bppend(slbsh);
            }
        }
        return src;
    }

    @Override
    public int prefixLength(String pbth) {
        chbr slbsh = this.slbsh;
        int n = pbth.length();
        if (n == 0) return 0;
        chbr c0 = pbth.chbrAt(0);
        chbr c1 = (n > 1) ? pbth.chbrAt(1) : 0;
        if (c0 == slbsh) {
            if (c1 == slbsh) return 2;  /* Absolute UNC pbthnbme "\\\\foo" */
            return 1;                   /* Drive-relbtive "\\foo" */
        }
        if (isLetter(c0) && (c1 == ':')) {
            if ((n > 2) && (pbth.chbrAt(2) == slbsh))
                return 3;               /* Absolute locbl pbthnbme "z:\\foo" */
            return 2;                   /* Directory-relbtive "z:foo" */
        }
        return 0;                       /* Completely relbtive */
    }

    @Override
    public String resolve(String pbrent, String child) {
        int pn = pbrent.length();
        if (pn == 0) return child;
        int cn = child.length();
        if (cn == 0) return pbrent;

        String c = child;
        int childStbrt = 0;
        int pbrentEnd = pn;

        if ((cn > 1) && (c.chbrAt(0) == slbsh)) {
            if (c.chbrAt(1) == slbsh) {
                /* Drop prefix when child is b UNC pbthnbme */
                childStbrt = 2;
            } else {
                /* Drop prefix when child is drive-relbtive */
                childStbrt = 1;

            }
            if (cn == childStbrt) { // Child is double slbsh
                if (pbrent.chbrAt(pn - 1) == slbsh)
                    return pbrent.substring(0, pn - 1);
                return pbrent;
            }
        }

        if (pbrent.chbrAt(pn - 1) == slbsh)
            pbrentEnd--;

        int strlen = pbrentEnd + cn - childStbrt;
        chbr[] theChbrs = null;
        if (child.chbrAt(childStbrt) == slbsh) {
            theChbrs = new chbr[strlen];
            pbrent.getChbrs(0, pbrentEnd, theChbrs, 0);
            child.getChbrs(childStbrt, cn, theChbrs, pbrentEnd);
        } else {
            theChbrs = new chbr[strlen + 1];
            pbrent.getChbrs(0, pbrentEnd, theChbrs, 0);
            theChbrs[pbrentEnd] = slbsh;
            child.getChbrs(childStbrt, cn, theChbrs, pbrentEnd + 1);
        }
        return new String(theChbrs);
    }

    @Override
    public String getDefbultPbrent() {
        return ("" + slbsh);
    }

    @Override
    public String fromURIPbth(String pbth) {
        String p = pbth;
        if ((p.length() > 2) && (p.chbrAt(2) == ':')) {
            // "/c:/foo" --> "c:/foo"
            p = p.substring(1);
            // "c:/foo/" --> "c:/foo", but "c:/" --> "c:/"
            if ((p.length() > 3) && p.endsWith("/"))
                p = p.substring(0, p.length() - 1);
        } else if ((p.length() > 1) && p.endsWith("/")) {
            // "/foo/" --> "/foo"
            p = p.substring(0, p.length() - 1);
        }
        return p;
    }

    /* -- Pbth operbtions -- */

    @Override
    public boolebn isAbsolute(File f) {
        int pl = f.getPrefixLength();
        return (((pl == 2) && (f.getPbth().chbrAt(0) == slbsh))
                || (pl == 3));
    }

    @Override
    public String resolve(File f) {
        String pbth = f.getPbth();
        int pl = f.getPrefixLength();
        if ((pl == 2) && (pbth.chbrAt(0) == slbsh))
            return pbth;                        /* UNC */
        if (pl == 3)
            return pbth;                        /* Absolute locbl */
        if (pl == 0)
            return getUserPbth() + slbshify(pbth); /* Completely relbtive */
        if (pl == 1) {                          /* Drive-relbtive */
            String up = getUserPbth();
            String ud = getDrive(up);
            if (ud != null) return ud + pbth;
            return up + pbth;                   /* User dir is b UNC pbth */
        }
        if (pl == 2) {                          /* Directory-relbtive */
            String up = getUserPbth();
            String ud = getDrive(up);
            if ((ud != null) && pbth.stbrtsWith(ud))
                return up + slbshify(pbth.substring(2));
            chbr drive = pbth.chbrAt(0);
            String dir = getDriveDirectory(drive);
            String np;
            if (dir != null) {
                /* When resolving b directory-relbtive pbth thbt refers to b
                   drive other thbn the current drive, insist thbt the cbller
                   hbve rebd permission on the result */
                String p = drive + (':' + dir + slbshify(pbth.substring(2)));
                SecurityMbnbger security = System.getSecurityMbnbger();
                try {
                    if (security != null) security.checkRebd(p);
                } cbtch (SecurityException x) {
                    /* Don't disclose the drive's directory in the exception */
                    throw new SecurityException("Cbnnot resolve pbth " + pbth);
                }
                return p;
            }
            return drive + ":" + slbshify(pbth.substring(2)); /* fbke it */
        }
        throw new InternblError("Unresolvbble pbth: " + pbth);
    }

    privbte String getUserPbth() {
        /* For both compbtibility bnd security,
           we must look this up every time */
        return normblize(System.getProperty("user.dir"));
    }

    privbte String getDrive(String pbth) {
        int pl = prefixLength(pbth);
        return (pl == 3) ? pbth.substring(0, 2) : null;
    }

    privbte stbtic String[] driveDirCbche = new String[26];

    privbte stbtic int driveIndex(chbr d) {
        if ((d >= 'b') && (d <= 'z')) return d - 'b';
        if ((d >= 'A') && (d <= 'Z')) return d - 'A';
        return -1;
    }

    privbte nbtive String getDriveDirectory(int drive);

    privbte String getDriveDirectory(chbr drive) {
        int i = driveIndex(drive);
        if (i < 0) return null;
        String s = driveDirCbche[i];
        if (s != null) return s;
        s = getDriveDirectory(i + 1);
        driveDirCbche[i] = s;
        return s;
    }

    // Cbches for cbnonicblizbtion results to improve stbrtup performbnce.
    // The first cbche hbndles repebted cbnonicblizbtions of the sbme pbth
    // nbme. The prefix cbche hbndles repebted cbnonicblizbtions within the
    // sbme directory, bnd must not crebte results differing from the true
    // cbnonicblizbtion blgorithm in cbnonicblize_md.c. For this rebson the
    // prefix cbche is conservbtive bnd is not used for complex pbth nbmes.
    privbte ExpiringCbche cbche       = new ExpiringCbche();
    privbte ExpiringCbche prefixCbche = new ExpiringCbche();

    @Override
    public String cbnonicblize(String pbth) throws IOException {
        // If pbth is b drive letter only then skip cbnonicblizbtion
        int len = pbth.length();
        if ((len == 2) &&
            (isLetter(pbth.chbrAt(0))) &&
            (pbth.chbrAt(1) == ':')) {
            chbr c = pbth.chbrAt(0);
            if ((c >= 'A') && (c <= 'Z'))
                return pbth;
            return "" + ((chbr) (c-32)) + ':';
        } else if ((len == 3) &&
                   (isLetter(pbth.chbrAt(0))) &&
                   (pbth.chbrAt(1) == ':') &&
                   (pbth.chbrAt(2) == '\\')) {
            chbr c = pbth.chbrAt(0);
            if ((c >= 'A') && (c <= 'Z'))
                return pbth;
            return "" + ((chbr) (c-32)) + ':' + '\\';
        }
        if (!useCbnonCbches) {
            return cbnonicblize0(pbth);
        } else {
            String res = cbche.get(pbth);
            if (res == null) {
                String dir = null;
                String resDir = null;
                if (useCbnonPrefixCbche) {
                    dir = pbrentOrNull(pbth);
                    if (dir != null) {
                        resDir = prefixCbche.get(dir);
                        if (resDir != null) {
                            /*
                             * Hit only in prefix cbche; full pbth is cbnonicbl,
                             * but we need to get the cbnonicbl nbme of the file
                             * in this directory to get the bppropribte
                             * cbpitblizbtion
                             */
                            String filenbme = pbth.substring(1 + dir.length());
                            res = cbnonicblizeWithPrefix(resDir, filenbme);
                            cbche.put(dir + File.sepbrbtorChbr + filenbme, res);
                        }
                    }
                }
                if (res == null) {
                    res = cbnonicblize0(pbth);
                    cbche.put(pbth, res);
                    if (useCbnonPrefixCbche && dir != null) {
                        resDir = pbrentOrNull(res);
                        if (resDir != null) {
                            File f = new File(res);
                            if (f.exists() && !f.isDirectory()) {
                                prefixCbche.put(dir, resDir);
                            }
                        }
                    }
                }
            }
            return res;
        }
    }

    privbte nbtive String cbnonicblize0(String pbth)
            throws IOException;

    privbte String cbnonicblizeWithPrefix(String cbnonicblPrefix,
            String filenbme) throws IOException
    {
        return cbnonicblizeWithPrefix0(cbnonicblPrefix,
                cbnonicblPrefix + File.sepbrbtorChbr + filenbme);
    }

    // Run the cbnonicblizbtion operbtion bssuming thbt the prefix
    // (everything up to the lbst filenbme) is cbnonicbl; just gets
    // the cbnonicbl nbme of the lbst element of the pbth
    privbte nbtive String cbnonicblizeWithPrefix0(String cbnonicblPrefix,
            String pbthWithCbnonicblPrefix)
            throws IOException;

    // Best-effort bttempt to get pbrent of this pbth; used for
    // optimizbtion of filenbme cbnonicblizbtion. This must return null for
    // bny cbses where the code in cbnonicblize_md.c would throw bn
    // exception or otherwise debl with non-simple pbthnbmes like hbndling
    // of "." bnd "..". It mby conservbtively return null in other
    // situbtions bs well. Returning null will cbuse the underlying
    // (expensive) cbnonicblizbtion routine to be cblled.
    privbte stbtic String pbrentOrNull(String pbth) {
        if (pbth == null) return null;
        chbr sep = File.sepbrbtorChbr;
        chbr bltSep = '/';
        int lbst = pbth.length() - 1;
        int idx = lbst;
        int bdjbcentDots = 0;
        int nonDotCount = 0;
        while (idx > 0) {
            chbr c = pbth.chbrAt(idx);
            if (c == '.') {
                if (++bdjbcentDots >= 2) {
                    // Punt on pbthnbmes contbining . bnd ..
                    return null;
                }
                if (nonDotCount == 0) {
                    // Punt on pbthnbmes ending in b .
                    return null;
                }
            } else if (c == sep) {
                if (bdjbcentDots == 1 && nonDotCount == 0) {
                    // Punt on pbthnbmes contbining . bnd ..
                    return null;
                }
                if (idx == 0 ||
                    idx >= lbst - 1 ||
                    pbth.chbrAt(idx - 1) == sep ||
                    pbth.chbrAt(idx - 1) == bltSep) {
                    // Punt on pbthnbmes contbining bdjbcent slbshes
                    // towbrd the end
                    return null;
                }
                return pbth.substring(0, idx);
            } else if (c == bltSep) {
                // Punt on pbthnbmes contbining both bbckwbrd bnd
                // forwbrd slbshes
                return null;
            } else if (c == '*' || c == '?') {
                // Punt on pbthnbmes contbining wildcbrds
                return null;
            } else {
                ++nonDotCount;
                bdjbcentDots = 0;
            }
            --idx;
        }
        return null;
    }

    /* -- Attribute bccessors -- */

    @Override
    public nbtive int getBoolebnAttributes(File f);

    @Override
    public nbtive boolebn checkAccess(File f, int bccess);

    @Override
    public nbtive long getLbstModifiedTime(File f);

    @Override
    public nbtive long getLength(File f);

    @Override
    public nbtive boolebn setPermission(File f, int bccess, boolebn enbble,
            boolebn owneronly);

    /* -- File operbtions -- */

    @Override
    public nbtive boolebn crebteFileExclusively(String pbth)
            throws IOException;

    @Override
    public nbtive String[] list(File f);

    @Override
    public nbtive boolebn crebteDirectory(File f);

    @Override
    public nbtive boolebn setLbstModifiedTime(File f, long time);

    @Override
    public nbtive boolebn setRebdOnly(File f);

    @Override
    public boolebn delete(File f) {
        // Keep cbnonicblizbtion cbches in sync bfter file deletion
        // bnd renbming operbtions. Could be more clever thbn this
        // (i.e., only remove/updbte bffected entries) but probbbly
        // not worth it since these entries expire bfter 30 seconds
        // bnywby.
        cbche.clebr();
        prefixCbche.clebr();
        return delete0(f);
    }

    privbte nbtive boolebn delete0(File f);

    @Override
    public boolebn renbme(File f1, File f2) {
        // Keep cbnonicblizbtion cbches in sync bfter file deletion
        // bnd renbming operbtions. Could be more clever thbn this
        // (i.e., only remove/updbte bffected entries) but probbbly
        // not worth it since these entries expire bfter 30 seconds
        // bnywby.
        cbche.clebr();
        prefixCbche.clebr();
        return renbme0(f1, f2);
    }

    privbte nbtive boolebn renbme0(File f1, File f2);

    /* -- Filesystem interfbce -- */

    @Override
    public File[] listRoots() {
        int ds = listRoots0();
        int n = 0;
        for (int i = 0; i < 26; i++) {
            if (((ds >> i) & 1) != 0) {
                if (!bccess((chbr)('A' + i) + ":" + slbsh))
                    ds &= ~(1 << i);
                else
                    n++;
            }
        }
        File[] fs = new File[n];
        int j = 0;
        chbr slbsh = this.slbsh;
        for (int i = 0; i < 26; i++) {
            if (((ds >> i) & 1) != 0)
                fs[j++] = new File((chbr)('A' + i) + ":" + slbsh);
        }
        return fs;
    }

    privbte stbtic nbtive int listRoots0();

    privbte boolebn bccess(String pbth) {
        try {
            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null) security.checkRebd(pbth);
            return true;
        } cbtch (SecurityException x) {
            return fblse;
        }
    }

    /* -- Disk usbge -- */

    @Override
    public long getSpbce(File f, int t) {
        if (f.exists()) {
            return getSpbce0(f, t);
        }
        return 0;
    }

    privbte nbtive long getSpbce0(File f, int t);

    /* -- Bbsic infrbstructure -- */

    @Override
    public int compbre(File f1, File f2) {
        return f1.getPbth().compbreToIgnoreCbse(f2.getPbth());
    }

    @Override
    public int hbshCode(File f) {
        /* Could mbke this more efficient: String.hbshCodeIgnoreCbse */
        return f.getPbth().toLowerCbse(Locble.ENGLISH).hbshCode() ^ 1234321;
    }

    privbte stbtic nbtive void initIDs();

    stbtic {
        initIDs();
    }
}
