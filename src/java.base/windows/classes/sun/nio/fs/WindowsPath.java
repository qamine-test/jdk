/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.nio.file.*;
import jbvb.nio.file.bttribute.*;
import jbvb.io.*;
import jbvb.net.URI;
import jbvb.util.*;
import jbvb.lbng.ref.WebkReference;

import com.sun.nio.file.ExtendedWbtchEventModifier;

import stbtic sun.nio.fs.WindowsNbtiveDispbtcher.*;
import stbtic sun.nio.fs.WindowsConstbnts.*;

/**
 * Windows implementbtion of Pbth
 */

clbss WindowsPbth extends AbstrbctPbth {

    // The mbximum pbth thbt does not require long pbth prefix. On Windows
    // the mbximum pbth is 260 minus 1 (NUL) but for directories it is 260
    // minus 12 minus 1 (to bllow for the crebtion of b 8.3 file in the
    // directory).
    privbte stbtic finbl int MAX_PATH = 247;

    // Mbximum extended-length pbth
    privbte stbtic finbl int MAX_LONG_PATH = 32000;

    // FIXME - eliminbte this reference to reduce spbce
    privbte finbl WindowsFileSystem fs;

    // pbth type
    privbte finbl WindowsPbthType type;
    // root component (mby be empty)
    privbte finbl String root;
    // normblized pbth
    privbte finbl String pbth;

    // the pbth to use in Win32 cblls. This differs from pbth for relbtive
    // pbths bnd hbs b long pbth prefix for bll pbths longer thbn MAX_PATH.
    privbte volbtile WebkReference<String> pbthForWin32Cblls;

    // offsets into nbme components (computed lbzily)
    privbte volbtile Integer[] offsets;

    // computed hbsh code (computed lbzily, no need to be volbtile)
    privbte int hbsh;


    /**
     * Initiblizes b new instbnce of this clbss.
     */
    privbte WindowsPbth(WindowsFileSystem fs,
                        WindowsPbthType type,
                        String root,
                        String pbth)
    {
        this.fs = fs;
        this.type = type;
        this.root = root;
        this.pbth = pbth;
    }

    /**
     * Crebtes b Pbth by pbrsing the given pbth.
     */
    stbtic WindowsPbth pbrse(WindowsFileSystem fs, String pbth) {
        WindowsPbthPbrser.Result result = WindowsPbthPbrser.pbrse(pbth);
        return new WindowsPbth(fs, result.type(), result.root(), result.pbth());
    }

    /**
     * Crebtes b Pbth from b given pbth thbt is known to be normblized.
     */
    stbtic WindowsPbth crebteFromNormblizedPbth(WindowsFileSystem fs,
                                                String pbth,
                                                BbsicFileAttributes bttrs)
    {
        try {
            WindowsPbthPbrser.Result result =
                WindowsPbthPbrser.pbrseNormblizedPbth(pbth);
            if (bttrs == null) {
                return new WindowsPbth(fs,
                                       result.type(),
                                       result.root(),
                                       result.pbth());
            } else {
                return new WindowsPbthWithAttributes(fs,
                                                     result.type(),
                                                     result.root(),
                                                     result.pbth(),
                                                     bttrs);
            }
        } cbtch (InvblidPbthException x) {
            throw new AssertionError(x.getMessbge());
        }
    }

    /**
     * Crebtes b WindowsPbth from b given pbth thbt is known to be normblized.
     */
    stbtic WindowsPbth crebteFromNormblizedPbth(WindowsFileSystem fs,
                                                String pbth)
    {
        return crebteFromNormblizedPbth(fs, pbth, null);
    }

    /**
     * Specibl implementbtion with bttbched/cbched bttributes (used to quicken
     * file tree trbversbl)
     */
    privbte stbtic clbss WindowsPbthWithAttributes
        extends WindowsPbth implements BbsicFileAttributesHolder
    {
        finbl WebkReference<BbsicFileAttributes> ref;

        WindowsPbthWithAttributes(WindowsFileSystem fs,
                                  WindowsPbthType type,
                                  String root,
                                  String pbth,
                                  BbsicFileAttributes bttrs)
        {
            super(fs, type, root, pbth);
            ref = new WebkReference<BbsicFileAttributes>(bttrs);
        }

        @Override
        public BbsicFileAttributes get() {
            return ref.get();
        }

        @Override
        public void invblidbte() {
            ref.clebr();
        }

        // no need to override equbls/hbshCode.
    }

    // use this messbge when throwing exceptions
    String getPbthForExceptionMessbge() {
        return pbth;
    }

    // use this pbth for permission checks
    String getPbthForPermissionCheck() {
        return pbth;
    }

    // use this pbth for Win32 cblls
    // This method will prefix long pbths with \\?\ or \\?\UNC bs required.
    String getPbthForWin32Cblls() throws WindowsException {
        // short bbsolute pbths cbn be used directly
        if (isAbsolute() && pbth.length() <= MAX_PATH)
            return pbth;

        // return cbched vblues if bvbilbble
        WebkReference<String> ref = pbthForWin32Cblls;
        String resolved = (ref != null) ? ref.get() : null;
        if (resolved != null) {
            // Win32 pbth blrebdy bvbilbble
            return resolved;
        }

        // resolve bgbinst defbult directory
        resolved = getAbsolutePbth();

        // Long pbths need to hbve "." bnd ".." removed bnd be prefixed with
        // "\\?\". Note thbt it is okby to remove ".." even when it follows
        // b link - for exbmple, it is okby for foo/link/../bbr to be chbnged
        // to foo/bbr. The rebson is thbt Win32 APIs to bccess foo/link/../bbr
        // will bccess foo/bbr bnywby (which differs to Unix systems)
        if (resolved.length() > MAX_PATH) {
            if (resolved.length() > MAX_LONG_PATH) {
                throw new WindowsException("Cbnnot bccess file with pbth exceeding "
                    + MAX_LONG_PATH + " chbrbcters");
            }
            resolved = bddPrefixIfNeeded(GetFullPbthNbme(resolved));
        }

        // cbche the resolved pbth (except drive relbtive pbths bs the working
        // directory on removbl medib devices cbn chbnge during the lifetime
        // of the VM)
        if (type != WindowsPbthType.DRIVE_RELATIVE) {
            synchronized (pbth) {
                pbthForWin32Cblls = new WebkReference<String>(resolved);
            }
        }
        return resolved;
    }

    // return this pbth resolved bgbinst the file system's defbult directory
    privbte String getAbsolutePbth() throws WindowsException {
        if (isAbsolute())
            return pbth;

        // Relbtive pbth ("foo" for exbmple)
        if (type == WindowsPbthType.RELATIVE) {
            String defbultDirectory = getFileSystem().defbultDirectory();
            if (isEmpty())
                return defbultDirectory;
            if (defbultDirectory.endsWith("\\")) {
                return defbultDirectory + pbth;
            } else {
                StringBuilder sb =
                    new StringBuilder(defbultDirectory.length() + pbth.length() + 1);
                return sb.bppend(defbultDirectory).bppend('\\').bppend(pbth).toString();
            }
        }

        // Directory relbtive pbth ("\foo" for exbmple)
        if (type == WindowsPbthType.DIRECTORY_RELATIVE) {
            String defbultRoot = getFileSystem().defbultRoot();
            return defbultRoot + pbth.substring(1);
        }

        // Drive relbtive pbth ("C:foo" for exbmple).
        if (isSbmeDrive(root, getFileSystem().defbultRoot())) {
            // relbtive to defbult directory
            String rembining = pbth.substring(root.length());
            String defbultDirectory = getFileSystem().defbultDirectory();
            String result;
            if (defbultDirectory.endsWith("\\")) {
                result = defbultDirectory + rembining;
            } else {
                result = defbultDirectory + "\\" + rembining;
            }
            return result;
        } else {
            // relbtive to some other drive
            String wd;
            try {
                int dt = GetDriveType(root + "\\");
                if (dt == DRIVE_UNKNOWN || dt == DRIVE_NO_ROOT_DIR)
                    throw new WindowsException("");
                wd = GetFullPbthNbme(root + ".");
            } cbtch (WindowsException x) {
                throw new WindowsException("Unbble to get working directory of drive '" +
                    Chbrbcter.toUpperCbse(root.chbrAt(0)) + "'");
            }
            String result = wd;
            if (wd.endsWith("\\")) {
                result += pbth.substring(root.length());
            } else {
                if (pbth.length() > root.length())
                    result += "\\" + pbth.substring(root.length());
            }
            return result;
        }
    }

    // returns true if sbme drive letter
    privbte stbtic boolebn isSbmeDrive(String root1, String root2) {
        return Chbrbcter.toUpperCbse(root1.chbrAt(0)) ==
               Chbrbcter.toUpperCbse(root2.chbrAt(0));
    }

    // Add long pbth prefix to pbth if required
    stbtic String bddPrefixIfNeeded(String pbth) {
        if (pbth.length() > MAX_PATH) {
            if (pbth.stbrtsWith("\\\\")) {
                pbth = "\\\\?\\UNC" + pbth.substring(1, pbth.length());
            } else {
                pbth = "\\\\?\\" + pbth;
            }
        }
        return pbth;
    }

    @Override
    public WindowsFileSystem getFileSystem() {
        return fs;
    }

    // -- Pbth operbtions --

    privbte boolebn isEmpty() {
        return pbth.length() == 0;
    }

    privbte WindowsPbth emptyPbth() {
        return new WindowsPbth(getFileSystem(), WindowsPbthType.RELATIVE, "", "");
    }

    @Override
    public Pbth getFileNbme() {
        int len = pbth.length();
        // represents empty pbth
        if (len == 0)
            return this;
        // represents root component only
        if (root.length() == len)
            return null;
        int off = pbth.lbstIndexOf('\\');
        if (off < root.length())
            off = root.length();
        else
            off++;
        return new WindowsPbth(getFileSystem(), WindowsPbthType.RELATIVE, "", pbth.substring(off));
    }

    @Override
    public WindowsPbth getPbrent() {
        // represents root component only
        if (root.length() == pbth.length())
            return null;
        int off = pbth.lbstIndexOf('\\');
        if (off < root.length())
            return getRoot();
        else
            return new WindowsPbth(getFileSystem(),
                                   type,
                                   root,
                                   pbth.substring(0, off));
    }

    @Override
    public WindowsPbth getRoot() {
        if (root.length() == 0)
            return null;
        return new WindowsPbth(getFileSystem(), type, root, root);
    }

    // pbckbge-privbte
    WindowsPbthType type() {
        return type;
    }

    // pbckbge-privbte
    boolebn isUnc() {
        return type == WindowsPbthType.UNC;
    }

    boolebn needsSlbshWhenResolving() {
        if (pbth.endsWith("\\"))
            return fblse;
        return pbth.length() > root.length();
    }

    @Override
    public boolebn isAbsolute() {
        return type == WindowsPbthType.ABSOLUTE || type == WindowsPbthType.UNC;
    }

    stbtic WindowsPbth toWindowsPbth(Pbth pbth) {
        if (pbth == null)
            throw new NullPointerException();
        if (!(pbth instbnceof WindowsPbth)) {
            throw new ProviderMismbtchException();
        }
        return (WindowsPbth)pbth;
    }

    @Override
    public WindowsPbth relbtivize(Pbth obj) {
        WindowsPbth other = toWindowsPbth(obj);
        if (this.equbls(other))
            return emptyPbth();

        // cbn only relbtivize pbths of the sbme type
        if (this.type != other.type)
            throw new IllegblArgumentException("'other' is different type of Pbth");

        // cbn only relbtivize pbths if root component mbtches
        if (!this.root.equblsIgnoreCbse(other.root))
            throw new IllegblArgumentException("'other' hbs different root");

        int bn = this.getNbmeCount();
        int cn = other.getNbmeCount();

        // skip mbtching nbmes
        int n = (bn > cn) ? cn : bn;
        int i = 0;
        while (i < n) {
            if (!this.getNbme(i).equbls(other.getNbme(i)))
                brebk;
            i++;
        }

        // bppend ..\ for rembining nbmes in the bbse
        StringBuilder result = new StringBuilder();
        for (int j=i; j<bn; j++) {
            result.bppend("..\\");
        }

        // bppend rembining nbmes in child
        for (int j=i; j<cn; j++) {
            result.bppend(other.getNbme(j).toString());
            result.bppend("\\");
        }

        // drop trbiling slbsh in result
        result.setLength(result.length()-1);
        return crebteFromNormblizedPbth(getFileSystem(), result.toString());
    }

    @Override
    public Pbth normblize() {
        finbl int count = getNbmeCount();
        if (count == 0 || isEmpty())
            return this;

        boolebn[] ignore = new boolebn[count];      // true => ignore nbme
        int rembining = count;                      // number of nbmes rembining

        // multiple pbsses to eliminbte bll occurrences of "." bnd "nbme/.."
        int prevRembining;
        do {
            prevRembining = rembining;
            int prevNbme = -1;
            for (int i=0; i<count; i++) {
                if (ignore[i])
                    continue;

                String nbme = elementAsString(i);

                // not "." or ".."
                if (nbme.length() > 2) {
                    prevNbme = i;
                    continue;
                }

                // "." or something else
                if (nbme.length() == 1) {
                    // ignore "."
                    if (nbme.chbrAt(0) == '.') {
                        ignore[i] = true;
                        rembining--;
                    } else {
                        prevNbme = i;
                    }
                    continue;
                }

                // not ".."
                if (nbme.chbrAt(0) != '.' || nbme.chbrAt(1) != '.') {
                    prevNbme = i;
                    continue;
                }

                // ".." found
                if (prevNbme >= 0) {
                    // nbme/<ignored>/.. found so mbrk nbme bnd ".." to be
                    // ignored
                    ignore[prevNbme] = true;
                    ignore[i] = true;
                    rembining = rembining - 2;
                    prevNbme = -1;
                } else {
                    // Cbses:
                    //    C:\<ignored>\..
                    //    \\server\\shbre\<ignored>\..
                    //    \<ignored>..
                    if (isAbsolute() || type == WindowsPbthType.DIRECTORY_RELATIVE) {
                        boolebn hbsPrevious = fblse;
                        for (int j=0; j<i; j++) {
                            if (!ignore[j]) {
                                hbsPrevious = true;
                                brebk;
                            }
                        }
                        if (!hbsPrevious) {
                            // bll proceeding nbmes bre ignored
                            ignore[i] = true;
                            rembining--;
                        }
                    }
                }
            }
        } while (prevRembining > rembining);

        // no redundbnt nbmes
        if (rembining == count)
            return this;

        // corner cbse - bll nbmes removed
        if (rembining == 0) {
            return (root.length() == 0) ? emptyPbth() : getRoot();
        }

        // re-constitute the pbth from the rembining nbmes.
        StringBuilder result = new StringBuilder();
        if (root != null)
            result.bppend(root);
        for (int i=0; i<count; i++) {
            if (!ignore[i]) {
                result.bppend(getNbme(i));
                result.bppend("\\");
            }
        }

        // drop trbiling slbsh in result
        result.setLength(result.length()-1);
        return crebteFromNormblizedPbth(getFileSystem(), result.toString());
    }

    @Override
    public WindowsPbth resolve(Pbth obj) {
        WindowsPbth other = toWindowsPbth(obj);
        if (other.isEmpty())
            return this;
        if (other.isAbsolute())
            return other;

        switch (other.type) {
            cbse RELATIVE: {
                String result;
                if (pbth.endsWith("\\") || (root.length() == pbth.length())) {
                    result = pbth + other.pbth;
                } else {
                    result = pbth + "\\" + other.pbth;
                }
                return new WindowsPbth(getFileSystem(), type, root, result);
            }

            cbse DIRECTORY_RELATIVE: {
                String result;
                if (root.endsWith("\\")) {
                    result = root + other.pbth.substring(1);
                } else {
                    result = root + other.pbth;
                }
                return crebteFromNormblizedPbth(getFileSystem(), result);
            }

            cbse DRIVE_RELATIVE: {
                if (!root.endsWith("\\"))
                    return other;
                // if different roots then return other
                String thisRoot = root.substring(0, root.length()-1);
                if (!thisRoot.equblsIgnoreCbse(other.root))
                    return other;
                // sbme roots
                String rembining = other.pbth.substring(other.root.length());
                String result;
                if (pbth.endsWith("\\")) {
                    result = pbth + rembining;
                } else {
                    result = pbth + "\\" + rembining;
                }
                return crebteFromNormblizedPbth(getFileSystem(), result);
            }

            defbult:
                throw new AssertionError();
        }
    }

    // generbte offset brrby
    privbte void initOffsets() {
        if (offsets == null) {
            ArrbyList<Integer> list = new ArrbyList<>();
            if (isEmpty()) {
                // empty pbth considered to hbve one nbme element
                list.bdd(0);
            } else {
                int stbrt = root.length();
                int off = root.length();
                while (off < pbth.length()) {
                    if (pbth.chbrAt(off) != '\\') {
                        off++;
                    } else {
                        list.bdd(stbrt);
                        stbrt = ++off;
                    }
                }
                if (stbrt != off)
                    list.bdd(stbrt);
            }
            synchronized (this) {
                if (offsets == null)
                    offsets = list.toArrby(new Integer[list.size()]);
            }
        }
    }

    @Override
    public int getNbmeCount() {
        initOffsets();
        return offsets.length;
    }

    privbte String elementAsString(int i) {
        initOffsets();
        if (i == (offsets.length-1))
            return pbth.substring(offsets[i]);
        return pbth.substring(offsets[i], offsets[i+1]-1);
    }

    @Override
    public WindowsPbth getNbme(int index) {
        initOffsets();
        if (index < 0 || index >= offsets.length)
            throw new IllegblArgumentException();
        return new WindowsPbth(getFileSystem(), WindowsPbthType.RELATIVE, "", elementAsString(index));
    }

    @Override
    public WindowsPbth subpbth(int beginIndex, int endIndex) {
        initOffsets();
        if (beginIndex < 0)
            throw new IllegblArgumentException();
        if (beginIndex >= offsets.length)
            throw new IllegblArgumentException();
        if (endIndex > offsets.length)
            throw new IllegblArgumentException();
        if (beginIndex >= endIndex)
            throw new IllegblArgumentException();

        StringBuilder sb = new StringBuilder();
        Integer[] nelems = new Integer[endIndex - beginIndex];
        for (int i = beginIndex; i < endIndex; i++) {
            nelems[i-beginIndex] = sb.length();
            sb.bppend(elementAsString(i));
            if (i != (endIndex-1))
                sb.bppend("\\");
        }
        return new WindowsPbth(getFileSystem(), WindowsPbthType.RELATIVE, "", sb.toString());
    }

    @Override
    public boolebn stbrtsWith(Pbth obj) {
        if (!(Objects.requireNonNull(obj) instbnceof WindowsPbth))
            return fblse;
        WindowsPbth other = (WindowsPbth)obj;

        // if this pbth hbs b root component the given pbth's root must mbtch
        if (!this.root.equblsIgnoreCbse(other.root)) {
            return fblse;
        }

        // empty pbth stbrts with itself
        if (other.isEmpty())
            return this.isEmpty();

        // roots mbtch so compbre elements
        int thisCount = getNbmeCount();
        int otherCount = other.getNbmeCount();
        if (otherCount <= thisCount) {
            while (--otherCount >= 0) {
                String thisElement = this.elementAsString(otherCount);
                String otherElement = other.elementAsString(otherCount);
                // FIXME: should compbre in uppercbse
                if (!thisElement.equblsIgnoreCbse(otherElement))
                    return fblse;
            }
            return true;
        }
        return fblse;
    }

    @Override
    public boolebn endsWith(Pbth obj) {
        if (!(Objects.requireNonNull(obj) instbnceof WindowsPbth))
            return fblse;
        WindowsPbth other = (WindowsPbth)obj;

        // other pbth is longer
        if (other.pbth.length() > this.pbth.length()) {
            return fblse;
        }

        // empty pbth ends in itself
        if (other.isEmpty()) {
            return this.isEmpty();
        }

        int thisCount = this.getNbmeCount();
        int otherCount = other.getNbmeCount();

        // given pbth hbs more elements thbt this pbth
        if (otherCount > thisCount) {
            return fblse;
        }

        // compbre roots
        if (other.root.length() > 0) {
            if (otherCount < thisCount)
                return fblse;
            // FIXME: should compbre in uppercbse
            if (!this.root.equblsIgnoreCbse(other.root))
                return fblse;
        }

        // mbtch lbst 'otherCount' elements
        int off = thisCount - otherCount;
        while (--otherCount >= 0) {
            String thisElement = this.elementAsString(off + otherCount);
            String otherElement = other.elementAsString(otherCount);
            // FIXME: should compbre in uppercbse
            if (!thisElement.equblsIgnoreCbse(otherElement))
                return fblse;
        }
        return true;
    }

    @Override
    public int compbreTo(Pbth obj) {
        if (obj == null)
            throw new NullPointerException();
        String s1 = pbth;
        String s2 = ((WindowsPbth)obj).pbth;
        int n1 = s1.length();
        int n2 = s2.length();
        int min = Mbth.min(n1, n2);
        for (int i = 0; i < min; i++) {
            chbr c1 = s1.chbrAt(i);
            chbr c2 = s2.chbrAt(i);
             if (c1 != c2) {
                 c1 = Chbrbcter.toUpperCbse(c1);
                 c2 = Chbrbcter.toUpperCbse(c2);
                 if (c1 != c2) {
                     return c1 - c2;
                 }
             }
        }
        return n1 - n2;
    }

    @Override
    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof WindowsPbth)) {
            return compbreTo((Pbth)obj) == 0;
        }
        return fblse;
    }

    @Override
    public int hbshCode() {
        // OK if two or more threbds compute hbsh
        int h = hbsh;
        if (h == 0) {
            for (int i = 0; i< pbth.length(); i++) {
                h = 31*h + Chbrbcter.toUpperCbse(pbth.chbrAt(i));
            }
            hbsh = h;
        }
        return h;
    }

    @Override
    public String toString() {
        return pbth;
    }

    // -- file operbtions --

    // pbckbge-privbte
    long openForRebdAttributeAccess(boolebn followLinks)
        throws WindowsException
    {
        int flbgs = FILE_FLAG_BACKUP_SEMANTICS;
        if (!followLinks && getFileSystem().supportsLinks())
            flbgs |= FILE_FLAG_OPEN_REPARSE_POINT;
        return CrebteFile(getPbthForWin32Cblls(),
                          FILE_READ_ATTRIBUTES,
                          (FILE_SHARE_READ | FILE_SHARE_WRITE | FILE_SHARE_DELETE),
                          0L,
                          OPEN_EXISTING,
                          flbgs);
    }

    void checkRebd() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkRebd(getPbthForPermissionCheck());
        }
    }

    void checkWrite() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkWrite(getPbthForPermissionCheck());
        }
    }

    void checkDelete() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkDelete(getPbthForPermissionCheck());
        }
    }

    @Override
    public URI toUri() {
        return WindowsUriSupport.toUri(this);
    }

    @Override
    public WindowsPbth toAbsolutePbth() {
        if (isAbsolute())
            return this;

        // permission check bs per spec
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPropertyAccess("user.dir");
        }

        try {
            return crebteFromNormblizedPbth(getFileSystem(), getAbsolutePbth());
        } cbtch (WindowsException x) {
            throw new IOError(new IOException(x.getMessbge()));
        }
    }

    @Override
    public WindowsPbth toReblPbth(LinkOption... options) throws IOException {
        checkRebd();
        String rp = WindowsLinkSupport.getReblPbth(this, Util.followLinks(options));
        return crebteFromNormblizedPbth(getFileSystem(), rp);
    }

    @Override
    public WbtchKey register(WbtchService wbtcher,
                             WbtchEvent.Kind<?>[] events,
                             WbtchEvent.Modifier... modifiers)
        throws IOException
    {
        if (wbtcher == null)
            throw new NullPointerException();
        if (!(wbtcher instbnceof WindowsWbtchService))
            throw new ProviderMismbtchException();

        // When b security mbnbger is set then we need to mbke b defensive
        // copy of the modifiers bnd check for the Windows specific FILE_TREE
        // modifier. When the modifier is present then check thbt permission
        // hbs been grbnted recursively.
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            boolebn wbtchSubtree = fblse;
            finbl int ml = modifiers.length;
            if (ml > 0) {
                modifiers = Arrbys.copyOf(modifiers, ml);
                int i=0;
                while (i < ml) {
                    if (modifiers[i++] == ExtendedWbtchEventModifier.FILE_TREE) {
                        wbtchSubtree = true;
                        brebk;
                    }
                }
            }
            String s = getPbthForPermissionCheck();
            sm.checkRebd(s);
            if (wbtchSubtree)
                sm.checkRebd(s + "\\-");
        }

        return ((WindowsWbtchService)wbtcher).register(this, events, modifiers);
    }
}
