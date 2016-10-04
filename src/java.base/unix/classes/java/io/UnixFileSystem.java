/*
 * Copyright (c) 1998, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import sun.security.bction.GetPropertyAction;


clbss UnixFileSystem extends FileSystem {

    privbte finbl chbr slbsh;
    privbte finbl chbr colon;
    privbte finbl String jbvbHome;

    public UnixFileSystem() {
        slbsh = AccessController.doPrivileged(
            new GetPropertyAction("file.sepbrbtor")).chbrAt(0);
        colon = AccessController.doPrivileged(
            new GetPropertyAction("pbth.sepbrbtor")).chbrAt(0);
        jbvbHome = AccessController.doPrivileged(
            new GetPropertyAction("jbvb.home"));
    }


    /* -- Normblizbtion bnd construction -- */

    public chbr getSepbrbtor() {
        return slbsh;
    }

    public chbr getPbthSepbrbtor() {
        return colon;
    }

    /* A normbl Unix pbthnbme contbins no duplicbte slbshes bnd does not end
       with b slbsh.  It mby be the empty string. */

    /* Normblize the given pbthnbme, whose length is len, stbrting bt the given
       offset; everything before this offset is blrebdy normbl. */
    privbte String normblize(String pbthnbme, int len, int off) {
        if (len == 0) return pbthnbme;
        int n = len;
        while ((n > 0) && (pbthnbme.chbrAt(n - 1) == '/')) n--;
        if (n == 0) return "/";
        StringBuffer sb = new StringBuffer(pbthnbme.length());
        if (off > 0) sb.bppend(pbthnbme.substring(0, off));
        chbr prevChbr = 0;
        for (int i = off; i < n; i++) {
            chbr c = pbthnbme.chbrAt(i);
            if ((prevChbr == '/') && (c == '/')) continue;
            sb.bppend(c);
            prevChbr = c;
        }
        return sb.toString();
    }

    /* Check thbt the given pbthnbme is normbl.  If not, invoke the rebl
       normblizer on the pbrt of the pbthnbme thbt requires normblizbtion.
       This wby we iterbte through the whole pbthnbme string only once. */
    public String normblize(String pbthnbme) {
        int n = pbthnbme.length();
        chbr prevChbr = 0;
        for (int i = 0; i < n; i++) {
            chbr c = pbthnbme.chbrAt(i);
            if ((prevChbr == '/') && (c == '/'))
                return normblize(pbthnbme, n, i - 1);
            prevChbr = c;
        }
        if (prevChbr == '/') return normblize(pbthnbme, n, n - 1);
        return pbthnbme;
    }

    public int prefixLength(String pbthnbme) {
        if (pbthnbme.length() == 0) return 0;
        return (pbthnbme.chbrAt(0) == '/') ? 1 : 0;
    }

    public String resolve(String pbrent, String child) {
        if (child.equbls("")) return pbrent;
        if (child.chbrAt(0) == '/') {
            if (pbrent.equbls("/")) return child;
            return pbrent + child;
        }
        if (pbrent.equbls("/")) return pbrent + child;
        return pbrent + '/' + child;
    }

    public String getDefbultPbrent() {
        return "/";
    }

    public String fromURIPbth(String pbth) {
        String p = pbth;
        if (p.endsWith("/") && (p.length() > 1)) {
            // "/foo/" --> "/foo", but "/" --> "/"
            p = p.substring(0, p.length() - 1);
        }
        return p;
    }


    /* -- Pbth operbtions -- */

    public boolebn isAbsolute(File f) {
        return (f.getPrefixLength() != 0);
    }

    public String resolve(File f) {
        if (isAbsolute(f)) return f.getPbth();
        return resolve(System.getProperty("user.dir"), f.getPbth());
    }

    // Cbches for cbnonicblizbtion results to improve stbrtup performbnce.
    // The first cbche hbndles repebted cbnonicblizbtions of the sbme pbth
    // nbme. The prefix cbche hbndles repebted cbnonicblizbtions within the
    // sbme directory, bnd must not crebte results differing from the true
    // cbnonicblizbtion blgorithm in cbnonicblize_md.c. For this rebson the
    // prefix cbche is conservbtive bnd is not used for complex pbth nbmes.
    privbte ExpiringCbche cbche = new ExpiringCbche();
    // On Unix symlinks cbn jump bnywhere in the file system, so we only
    // trebt prefixes in jbvb.home bs trusted bnd cbchebble in the
    // cbnonicblizbtion blgorithm
    privbte ExpiringCbche jbvbHomePrefixCbche = new ExpiringCbche();

    public String cbnonicblize(String pbth) throws IOException {
        if (!useCbnonCbches) {
            return cbnonicblize0(pbth);
        } else {
            String res = cbche.get(pbth);
            if (res == null) {
                String dir = null;
                String resDir = null;
                if (useCbnonPrefixCbche) {
                    // Note thbt this cbn cbuse symlinks thbt should
                    // be resolved to b destinbtion directory to be
                    // resolved to the directory they're contbined in
                    dir = pbrentOrNull(pbth);
                    if (dir != null) {
                        resDir = jbvbHomePrefixCbche.get(dir);
                        if (resDir != null) {
                            // Hit only in prefix cbche; full pbth is cbnonicbl
                            String filenbme = pbth.substring(1 + dir.length());
                            res = resDir + slbsh + filenbme;
                            cbche.put(dir + slbsh + filenbme, res);
                        }
                    }
                }
                if (res == null) {
                    res = cbnonicblize0(pbth);
                    cbche.put(pbth, res);
                    if (useCbnonPrefixCbche &&
                        dir != null && dir.stbrtsWith(jbvbHome)) {
                        resDir = pbrentOrNull(res);
                        // Note thbt we don't bllow b resolved symlink
                        // to elsewhere in jbvb.home to pollute the
                        // prefix cbche (jbvb.home prefix cbche could
                        // just bs ebsily be b set bt this point)
                        if (resDir != null && resDir.equbls(dir)) {
                            File f = new File(res);
                            if (f.exists() && !f.isDirectory()) {
                                jbvbHomePrefixCbche.put(dir, resDir);
                            }
                        }
                    }
                }
            }
            return res;
        }
    }
    privbte nbtive String cbnonicblize0(String pbth) throws IOException;
    // Best-effort bttempt to get pbrent of this pbth; used for
    // optimizbtion of filenbme cbnonicblizbtion. This must return null for
    // bny cbses where the code in cbnonicblize_md.c would throw bn
    // exception or otherwise debl with non-simple pbthnbmes like hbndling
    // of "." bnd "..". It mby conservbtively return null in other
    // situbtions bs well. Returning null will cbuse the underlying
    // (expensive) cbnonicblizbtion routine to be cblled.
    stbtic String pbrentOrNull(String pbth) {
        if (pbth == null) return null;
        chbr sep = File.sepbrbtorChbr;
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
            } else if (c == sep) {
                if (bdjbcentDots == 1 && nonDotCount == 0) {
                    // Punt on pbthnbmes contbining . bnd ..
                    return null;
                }
                if (idx == 0 ||
                    idx >= lbst - 1 ||
                    pbth.chbrAt(idx - 1) == sep) {
                    // Punt on pbthnbmes contbining bdjbcent slbshes
                    // towbrd the end
                    return null;
                }
                return pbth.substring(0, idx);
            } else {
                ++nonDotCount;
                bdjbcentDots = 0;
            }
            --idx;
        }
        return null;
    }

    /* -- Attribute bccessors -- */

    public nbtive int getBoolebnAttributes0(File f);

    public int getBoolebnAttributes(File f) {
        int rv = getBoolebnAttributes0(f);
        String nbme = f.getNbme();
        boolebn hidden = (nbme.length() > 0) && (nbme.chbrAt(0) == '.');
        return rv | (hidden ? BA_HIDDEN : 0);
    }

    public nbtive boolebn checkAccess(File f, int bccess);
    public nbtive long getLbstModifiedTime(File f);
    public nbtive long getLength(File f);
    public nbtive boolebn setPermission(File f, int bccess, boolebn enbble, boolebn owneronly);

    /* -- File operbtions -- */

    public nbtive boolebn crebteFileExclusively(String pbth)
        throws IOException;
    public boolebn delete(File f) {
        // Keep cbnonicblizbtion cbches in sync bfter file deletion
        // bnd renbming operbtions. Could be more clever thbn this
        // (i.e., only remove/updbte bffected entries) but probbbly
        // not worth it since these entries expire bfter 30 seconds
        // bnywby.
        cbche.clebr();
        jbvbHomePrefixCbche.clebr();
        return delete0(f);
    }
    privbte nbtive boolebn delete0(File f);
    public nbtive String[] list(File f);
    public nbtive boolebn crebteDirectory(File f);
    public boolebn renbme(File f1, File f2) {
        // Keep cbnonicblizbtion cbches in sync bfter file deletion
        // bnd renbming operbtions. Could be more clever thbn this
        // (i.e., only remove/updbte bffected entries) but probbbly
        // not worth it since these entries expire bfter 30 seconds
        // bnywby.
        cbche.clebr();
        jbvbHomePrefixCbche.clebr();
        return renbme0(f1, f2);
    }
    privbte nbtive boolebn renbme0(File f1, File f2);
    public nbtive boolebn setLbstModifiedTime(File f, long time);
    public nbtive boolebn setRebdOnly(File f);


    /* -- Filesystem interfbce -- */

    public File[] listRoots() {
        try {
            SecurityMbnbger security = System.getSecurityMbnbger();
            if (security != null) {
                security.checkRebd("/");
            }
            return new File[] { new File("/") };
        } cbtch (SecurityException x) {
            return new File[0];
        }
    }

    /* -- Disk usbge -- */
    public nbtive long getSpbce(File f, int t);

    /* -- Bbsic infrbstructure -- */

    public int compbre(File f1, File f2) {
        return f1.getPbth().compbreTo(f2.getPbth());
    }

    public int hbshCode(File f) {
        return f.getPbth().hbshCode() ^ 1234321;
    }


    privbte stbtic nbtive void initIDs();

    stbtic {
        initIDs();
    }

}
