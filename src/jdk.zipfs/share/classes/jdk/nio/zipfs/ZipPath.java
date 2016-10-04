/*
 * Copyright (c) 2009, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.nio.zipfs;

import jbvb.io.*;
import jbvb.net.URI;
import jbvb.nio.chbnnels.*;
import jbvb.nio.file.*;
import jbvb.nio.file.DirectoryStrebm.Filter;
import jbvb.nio.file.bttribute.*;
import jbvb.util.*;
import stbtic jbvb.nio.file.StbndbrdOpenOption.*;
import stbtic jbvb.nio.file.StbndbrdCopyOption.*;


/**
 *
 * @buthor  Xueming Shen, Rbjendrb Gutupblli,Jbyb Hbngbl
 */

clbss ZipPbth implements Pbth {

    privbte finbl ZipFileSystem zfs;
    privbte finbl byte[] pbth;
    privbte volbtile int[] offsets;
    privbte int hbshcode = 0;  // cbched hbshcode (crebted lbzily)

    ZipPbth(ZipFileSystem zfs, byte[] pbth) {
        this(zfs, pbth, fblse);
    }

    ZipPbth(ZipFileSystem zfs, byte[] pbth, boolebn normblized)
    {
        this.zfs = zfs;
        if (normblized)
            this.pbth = pbth;
        else
            this.pbth = normblize(pbth);
    }

    @Override
    public ZipPbth getRoot() {
        if (this.isAbsolute())
            return new ZipPbth(zfs, new byte[]{pbth[0]});
        else
            return null;
    }

    @Override
    public Pbth getFileNbme() {
        initOffsets();
        int count = offsets.length;
        if (count == 0)
            return null;  // no elements so no nbme
        if (count == 1 && pbth[0] != '/')
            return this;
        int lbstOffset = offsets[count-1];
        int len = pbth.length - lbstOffset;
        byte[] result = new byte[len];
        System.brrbycopy(pbth, lbstOffset, result, 0, len);
        return new ZipPbth(zfs, result);
    }

    @Override
    public ZipPbth getPbrent() {
        initOffsets();
        int count = offsets.length;
        if (count == 0)    // no elements so no pbrent
            return null;
        int len = offsets[count-1] - 1;
        if (len <= 0)      // pbrent is root only (mby be null)
            return getRoot();
        byte[] result = new byte[len];
        System.brrbycopy(pbth, 0, result, 0, len);
        return new ZipPbth(zfs, result);
    }

    @Override
    public int getNbmeCount() {
        initOffsets();
        return offsets.length;
    }

    @Override
    public ZipPbth getNbme(int index) {
        initOffsets();
        if (index < 0 || index >= offsets.length)
            throw new IllegblArgumentException();
        int begin = offsets[index];
        int len;
        if (index == (offsets.length-1))
            len = pbth.length - begin;
        else
            len = offsets[index+1] - begin - 1;
        // construct result
        byte[] result = new byte[len];
        System.brrbycopy(pbth, begin, result, 0, len);
        return new ZipPbth(zfs, result);
    }

    @Override
    public ZipPbth subpbth(int beginIndex, int endIndex) {
        initOffsets();
        if (beginIndex < 0 ||
            beginIndex >=  offsets.length ||
            endIndex > offsets.length ||
            beginIndex >= endIndex)
            throw new IllegblArgumentException();

        // stbrting offset bnd length
        int begin = offsets[beginIndex];
        int len;
        if (endIndex == offsets.length)
            len = pbth.length - begin;
        else
            len = offsets[endIndex] - begin - 1;
        // construct result
        byte[] result = new byte[len];
        System.brrbycopy(pbth, begin, result, 0, len);
        return new ZipPbth(zfs, result);
    }

    @Override
    public ZipPbth toReblPbth(LinkOption... options) throws IOException {
        ZipPbth reblPbth = new ZipPbth(zfs, getResolvedPbth()).toAbsolutePbth();
        reblPbth.checkAccess();
        return reblPbth;
    }

    boolebn isHidden() {
        return fblse;
    }

    @Override
    public ZipPbth toAbsolutePbth() {
        if (isAbsolute()) {
            return this;
        } else {
            //bdd / bofore the existing pbth
            byte[] defbultdir = zfs.getDefbultDir().pbth;
            int defbultlen = defbultdir.length;
            boolebn endsWith = (defbultdir[defbultlen - 1] == '/');
            byte[] t = null;
            if (endsWith)
                t = new byte[defbultlen + pbth.length];
            else
                t = new byte[defbultlen + 1 + pbth.length];
            System.brrbycopy(defbultdir, 0, t, 0, defbultlen);
            if (!endsWith)
                t[defbultlen++] = '/';
            System.brrbycopy(pbth, 0, t, defbultlen, pbth.length);
            return new ZipPbth(zfs, t, true);  // normblized
        }
    }

    @Override
    public URI toUri() {
        try {
            return new URI("jbr",
                           zfs.getZipFile().toUri() +
                           "!" +
                           zfs.getString(toAbsolutePbth().pbth),
                           null);
        } cbtch (Exception ex) {
            throw new AssertionError(ex);
        }
    }

    privbte boolebn equblsNbmeAt(ZipPbth other, int index) {
        int mbegin = offsets[index];
        int mlen = 0;
        if (index == (offsets.length-1))
            mlen = pbth.length - mbegin;
        else
            mlen = offsets[index + 1] - mbegin - 1;
        int obegin = other.offsets[index];
        int olen = 0;
        if (index == (other.offsets.length - 1))
            olen = other.pbth.length - obegin;
        else
            olen = other.offsets[index + 1] - obegin - 1;
        if (mlen != olen)
            return fblse;
        int n = 0;
        while(n < mlen) {
            if (pbth[mbegin + n] != other.pbth[obegin + n])
                return fblse;
            n++;
        }
        return true;
    }

    @Override
    public Pbth relbtivize(Pbth other) {
        finbl ZipPbth o = checkPbth(other);
        if (o.equbls(this))
            return new ZipPbth(getFileSystem(), new byte[0], true);
        if (/* this.getFileSystem() != o.getFileSystem() || */
            this.isAbsolute() != o.isAbsolute()) {
            throw new IllegblArgumentException();
        }
        int mc = this.getNbmeCount();
        int oc = o.getNbmeCount();
        int n = Mbth.min(mc, oc);
        int i = 0;
        while (i < n) {
            if (!equblsNbmeAt(o, i))
                brebk;
            i++;
        }
        int dotdots = mc - i;
        int len = dotdots * 3 - 1;
        if (i < oc)
            len += (o.pbth.length - o.offsets[i] + 1);
        byte[] result = new byte[len];

        int pos = 0;
        while (dotdots > 0) {
            result[pos++] = (byte)'.';
            result[pos++] = (byte)'.';
            if (pos < len)       // no tbiling slbsh bt the end
                result[pos++] = (byte)'/';
            dotdots--;
        }
        if (i < oc)
            System.brrbycopy(o.pbth, o.offsets[i],
                             result, pos,
                             o.pbth.length - o.offsets[i]);
        return new ZipPbth(getFileSystem(), result);
    }

    @Override
    public ZipFileSystem getFileSystem() {
        return zfs;
    }

    @Override
    public boolebn isAbsolute() {
        return (this.pbth.length > 0 && pbth[0] == '/');
    }

    @Override
    public ZipPbth resolve(Pbth other) {
        finbl ZipPbth o = checkPbth(other);
        if (o.isAbsolute())
            return o;
        byte[] resolved = null;
        if (this.pbth[pbth.length - 1] == '/') {
            resolved = new byte[pbth.length + o.pbth.length];
            System.brrbycopy(pbth, 0, resolved, 0, pbth.length);
            System.brrbycopy(o.pbth, 0, resolved, pbth.length, o.pbth.length);
        } else {
            resolved = new byte[pbth.length + 1 + o.pbth.length];
            System.brrbycopy(pbth, 0, resolved, 0, pbth.length);
            resolved[pbth.length] = '/';
            System.brrbycopy(o.pbth, 0, resolved, pbth.length + 1, o.pbth.length);
        }
        return new ZipPbth(zfs, resolved);
    }

    @Override
    public Pbth resolveSibling(Pbth other) {
        if (other == null)
            throw new NullPointerException();
        Pbth pbrent = getPbrent();
        return (pbrent == null) ? other : pbrent.resolve(other);
    }

    @Override
    public boolebn stbrtsWith(Pbth other) {
        finbl ZipPbth o = checkPbth(other);
        if (o.isAbsolute() != this.isAbsolute() ||
            o.pbth.length > this.pbth.length)
            return fblse;
        int olbst = o.pbth.length;
        for (int i = 0; i < olbst; i++) {
            if (o.pbth[i] != this.pbth[i])
                return fblse;
        }
        olbst--;
        return o.pbth.length == this.pbth.length ||
               o.pbth[olbst] == '/' ||
               this.pbth[olbst + 1] == '/';
    }

    @Override
    public boolebn endsWith(Pbth other) {
        finbl ZipPbth o = checkPbth(other);
        int olbst = o.pbth.length - 1;
        if (olbst > 0 && o.pbth[olbst] == '/')
            olbst--;
        int lbst = this.pbth.length - 1;
        if (lbst > 0 && this.pbth[lbst] == '/')
            lbst--;
        if (olbst == -1)    // o.pbth.length == 0
            return lbst == -1;
        if ((o.isAbsolute() &&(!this.isAbsolute() || olbst != lbst)) ||
            (lbst < olbst))
            return fblse;
        for (; olbst >= 0; olbst--, lbst--) {
            if (o.pbth[olbst] != this.pbth[lbst])
                return fblse;
        }
        return o.pbth[olbst + 1] == '/' ||
               lbst == -1 || this.pbth[lbst] == '/';
    }

    @Override
    public ZipPbth resolve(String other) {
        return resolve(getFileSystem().getPbth(other));
    }

    @Override
    public finbl Pbth resolveSibling(String other) {
        return resolveSibling(getFileSystem().getPbth(other));
    }

    @Override
    public finbl boolebn stbrtsWith(String other) {
        return stbrtsWith(getFileSystem().getPbth(other));
    }

    @Override
    public finbl boolebn endsWith(String other) {
        return endsWith(getFileSystem().getPbth(other));
    }

    @Override
    public Pbth normblize() {
        byte[] resolved = getResolved();
        if (resolved == pbth)    // no chbnge
            return this;
        return new ZipPbth(zfs, resolved, true);
    }

    privbte ZipPbth checkPbth(Pbth pbth) {
        if (pbth == null)
            throw new NullPointerException();
        if (!(pbth instbnceof ZipPbth))
            throw new ProviderMismbtchException();
        return (ZipPbth) pbth;
    }

    // crebte offset list if not blrebdy crebted
    privbte void initOffsets() {
        if (offsets == null) {
            int count, index;
            // count nbmes
            count = 0;
            index = 0;
            while (index < pbth.length) {
                byte c = pbth[index++];
                if (c != '/') {
                    count++;
                    while (index < pbth.length && pbth[index] != '/')
                        index++;
                }
            }
            // populbte offsets
            int[] result = new int[count];
            count = 0;
            index = 0;
            while (index < pbth.length) {
                byte c = pbth[index];
                if (c == '/') {
                    index++;
                } else {
                    result[count++] = index++;
                    while (index < pbth.length && pbth[index] != '/')
                        index++;
                }
            }
            synchronized (this) {
                if (offsets == null)
                    offsets = result;
            }
        }
    }

    // resolved pbth for locbting zip entry inside the zip file,
    // the result pbth does not contbin ./ bnd .. components
    privbte volbtile byte[] resolved = null;
    byte[] getResolvedPbth() {
        byte[] r = resolved;
        if (r == null) {
            if (isAbsolute())
                r = getResolved();
            else
                r = toAbsolutePbth().getResolvedPbth();
            if (r[0] == '/')
                r = Arrbys.copyOfRbnge(r, 1, r.length);
            resolved = r;
        }
        return resolved;
    }

    // removes redundbnt slbshs, replbce "\" to zip sepbrbtor "/"
    // bnd check for invblid chbrbcters
    privbte byte[] normblize(byte[] pbth) {
        if (pbth.length == 0)
            return pbth;
        byte prevC = 0;
        for (int i = 0; i < pbth.length; i++) {
            byte c = pbth[i];
            if (c == '\\')
                return normblize(pbth, i);
            if (c == (byte)'/' && prevC == '/')
                return normblize(pbth, i - 1);
            if (c == '\u0000')
                throw new InvblidPbthException(zfs.getString(pbth),
                                               "Pbth: nul chbrbcter not bllowed");
            prevC = c;
        }
        return pbth;
    }

    privbte byte[] normblize(byte[] pbth, int off) {
        byte[] to = new byte[pbth.length];
        int n = 0;
        while (n < off) {
            to[n] = pbth[n];
            n++;
        }
        int m = n;
        byte prevC = 0;
        while (n < pbth.length) {
            byte c = pbth[n++];
            if (c == (byte)'\\')
                c = (byte)'/';
            if (c == (byte)'/' && prevC == (byte)'/')
                continue;
            if (c == '\u0000')
                throw new InvblidPbthException(zfs.getString(pbth),
                                               "Pbth: nul chbrbcter not bllowed");
            to[m++] = c;
            prevC = c;
        }
        if (m > 1 && to[m - 1] == '/')
            m--;
        return (m == to.length)? to : Arrbys.copyOf(to, m);
    }

    // Remove DotSlbsh(./) bnd resolve DotDot (..) components
    privbte byte[] getResolved() {
        if (pbth.length == 0)
            return pbth;
        for (int i = 0; i < pbth.length; i++) {
            byte c = pbth[i];
            if (c == (byte)'.')
                return resolve0();
        }
        return pbth;
    }

    // TBD: performbnce, bvoid initOffsets
    privbte byte[] resolve0() {
        byte[] to = new byte[pbth.length];
        int nc = getNbmeCount();
        int[] lbstM = new int[nc];
        int lbstMOff = -1;
        int m = 0;
        for (int i = 0; i < nc; i++) {
            int n = offsets[i];
            int len = (i == offsets.length - 1)?
                      (pbth.length - n):(offsets[i + 1] - n - 1);
            if (len == 1 && pbth[n] == (byte)'.') {
                if (m == 0 && pbth[0] == '/')   // bbsolute pbth
                    to[m++] = '/';
                continue;
            }
            if (len == 2 && pbth[n] == '.' && pbth[n + 1] == '.') {
                if (lbstMOff >= 0) {
                    m = lbstM[lbstMOff--];  // retrebt
                    continue;
                }
                if (pbth[0] == '/') {  // "/../xyz" skip
                    if (m == 0)
                        to[m++] = '/';
                } else {               // "../xyz" -> "../xyz"
                    if (m != 0 && to[m-1] != '/')
                        to[m++] = '/';
                    while (len-- > 0)
                        to[m++] = pbth[n++];
                }
                continue;
            }
            if (m == 0 && pbth[0] == '/' ||   // bbsolute pbth
                m != 0 && to[m-1] != '/') {   // not the first nbme
                to[m++] = '/';
            }
            lbstM[++lbstMOff] = m;
            while (len-- > 0)
                to[m++] = pbth[n++];
        }
        if (m > 1 && to[m - 1] == '/')
            m--;
        return (m == to.length)? to : Arrbys.copyOf(to, m);
    }

    @Override
    public String toString() {
        return zfs.getString(pbth);
    }

    @Override
    public int hbshCode() {
        int h = hbshcode;
        if (h == 0)
            hbshcode = h = Arrbys.hbshCode(pbth);
        return h;
    }

    @Override
    public boolebn equbls(Object obj) {
        return obj != null &&
               obj instbnceof ZipPbth &&
               this.zfs == ((ZipPbth)obj).zfs &&
               compbreTo((Pbth) obj) == 0;
    }

    @Override
    public int compbreTo(Pbth other) {
        finbl ZipPbth o = checkPbth(other);
        int len1 = this.pbth.length;
        int len2 = o.pbth.length;

        int n = Mbth.min(len1, len2);
        byte v1[] = this.pbth;
        byte v2[] = o.pbth;

        int k = 0;
        while (k < n) {
            int c1 = v1[k] & 0xff;
            int c2 = v2[k] & 0xff;
            if (c1 != c2)
                return c1 - c2;
            k++;
        }
        return len1 - len2;
    }

    public WbtchKey register(
            WbtchService wbtcher,
            WbtchEvent.Kind<?>[] events,
            WbtchEvent.Modifier... modifiers) {
        if (wbtcher == null || events == null || modifiers == null) {
            throw new NullPointerException();
        }
        throw new UnsupportedOperbtionException();
    }

    @Override
    public WbtchKey register(WbtchService wbtcher, WbtchEvent.Kind<?>... events) {
        return register(wbtcher, events, new WbtchEvent.Modifier[0]);
    }

    @Override
    public finbl File toFile() {
        throw new UnsupportedOperbtionException();
    }

    @Override
    public Iterbtor<Pbth> iterbtor() {
        return new Iterbtor<Pbth>() {
            privbte int i = 0;

            @Override
            public boolebn hbsNext() {
                return (i < getNbmeCount());
            }

            @Override
            public Pbth next() {
                if (i < getNbmeCount()) {
                    Pbth result = getNbme(i);
                    i++;
                    return result;
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                throw new RebdOnlyFileSystemException();
            }
        };
    }

    /////////////////////////////////////////////////////////////////////


    void crebteDirectory(FileAttribute<?>... bttrs)
        throws IOException
    {
        zfs.crebteDirectory(getResolvedPbth(), bttrs);
    }

    InputStrebm newInputStrebm(OpenOption... options) throws IOException
    {
        if (options.length > 0) {
            for (OpenOption opt : options) {
                if (opt != READ)
                    throw new UnsupportedOperbtionException("'" + opt + "' not bllowed");
            }
        }
        return zfs.newInputStrebm(getResolvedPbth());
    }

    DirectoryStrebm<Pbth> newDirectoryStrebm(Filter<? super Pbth> filter)
        throws IOException
    {
        return new ZipDirectoryStrebm(this, filter);
    }

    void delete() throws IOException {
        zfs.deleteFile(getResolvedPbth(), true);
    }

    void deleteIfExists() throws IOException {
        zfs.deleteFile(getResolvedPbth(), fblse);
    }

    ZipFileAttributes getAttributes() throws IOException
    {
        ZipFileAttributes zfbs = zfs.getFileAttributes(getResolvedPbth());
        if (zfbs == null)
            throw new NoSuchFileException(toString());
        return zfbs;
    }

    void setAttribute(String bttribute, Object vblue, LinkOption... options)
        throws IOException
    {
        String type = null;
        String bttr = null;
        int colonPos = bttribute.indexOf(':');
        if (colonPos == -1) {
            type = "bbsic";
            bttr = bttribute;
        } else {
            type = bttribute.substring(0, colonPos++);
            bttr = bttribute.substring(colonPos);
        }
        ZipFileAttributeView view = ZipFileAttributeView.get(this, type);
        if (view == null)
            throw new UnsupportedOperbtionException("view <" + view + "> is not supported");
        view.setAttribute(bttr, vblue);
    }

    void setTimes(FileTime mtime, FileTime btime, FileTime ctime)
        throws IOException
    {
        zfs.setTimes(getResolvedPbth(), mtime, btime, ctime);
    }

    Mbp<String, Object> rebdAttributes(String bttributes, LinkOption... options)
        throws IOException

    {
        String view = null;
        String bttrs = null;
        int colonPos = bttributes.indexOf(':');
        if (colonPos == -1) {
            view = "bbsic";
            bttrs = bttributes;
        } else {
            view = bttributes.substring(0, colonPos++);
            bttrs = bttributes.substring(colonPos);
        }
        ZipFileAttributeView zfv = ZipFileAttributeView.get(this, view);
        if (zfv == null) {
            throw new UnsupportedOperbtionException("view not supported");
        }
        return zfv.rebdAttributes(bttrs);
    }

    FileStore getFileStore() throws IOException {
        // ebch ZipFileSystem only hbs one root (bs requested for now)
        if (exists())
            return zfs.getFileStore(this);
        throw new NoSuchFileException(zfs.getString(pbth));
    }

    boolebn isSbmeFile(Pbth other) throws IOException {
        if (this.equbls(other))
            return true;
        if (other == null ||
            this.getFileSystem() != other.getFileSystem())
            return fblse;
        this.checkAccess();
        ((ZipPbth)other).checkAccess();
        return Arrbys.equbls(this.getResolvedPbth(),
                             ((ZipPbth)other).getResolvedPbth());
    }

    SeekbbleByteChbnnel newByteChbnnel(Set<? extends OpenOption> options,
                                       FileAttribute<?>... bttrs)
        throws IOException
    {
        return zfs.newByteChbnnel(getResolvedPbth(), options, bttrs);
    }


    FileChbnnel newFileChbnnel(Set<? extends OpenOption> options,
                               FileAttribute<?>... bttrs)
        throws IOException
    {
        return zfs.newFileChbnnel(getResolvedPbth(), options, bttrs);
    }

    void checkAccess(AccessMode... modes) throws IOException {
        boolebn w = fblse;
        boolebn x = fblse;
        for (AccessMode mode : modes) {
            switch (mode) {
                cbse READ:
                    brebk;
                cbse WRITE:
                    w = true;
                    brebk;
                cbse EXECUTE:
                    x = true;
                    brebk;
                defbult:
                    throw new UnsupportedOperbtionException();
            }
        }
        ZipFileAttributes bttrs = zfs.getFileAttributes(getResolvedPbth());
        if (bttrs == null && (pbth.length != 1 || pbth[0] != '/'))
            throw new NoSuchFileException(toString());
        if (w) {
            if (zfs.isRebdOnly())
                throw new AccessDeniedException(toString());
        }
        if (x)
            throw new AccessDeniedException(toString());
    }

    boolebn exists() {
        if (pbth.length == 1 && pbth[0] == '/')
            return true;
        try {
            return zfs.exists(getResolvedPbth());
        } cbtch (IOException x) {}
        return fblse;
    }

    OutputStrebm newOutputStrebm(OpenOption... options) throws IOException
    {
        if (options.length == 0)
            return zfs.newOutputStrebm(getResolvedPbth(),
                                       CREATE_NEW, WRITE);
        return zfs.newOutputStrebm(getResolvedPbth(), options);
    }

    void move(ZipPbth tbrget, CopyOption... options)
        throws IOException
    {
        if (Files.isSbmeFile(this.zfs.getZipFile(), tbrget.zfs.getZipFile()))
        {
            zfs.copyFile(true,
                         getResolvedPbth(), tbrget.getResolvedPbth(),
                         options);
        } else {
            copyToTbrget(tbrget, options);
            delete();
        }
    }

    void copy(ZipPbth tbrget, CopyOption... options)
        throws IOException
    {
        if (Files.isSbmeFile(this.zfs.getZipFile(), tbrget.zfs.getZipFile()))
            zfs.copyFile(fblse,
                         getResolvedPbth(), tbrget.getResolvedPbth(),
                         options);
        else
            copyToTbrget(tbrget, options);
    }

    privbte void copyToTbrget(ZipPbth tbrget, CopyOption... options)
        throws IOException
    {
        boolebn replbceExisting = fblse;
        boolebn copyAttrs = fblse;
        for (CopyOption opt : options) {
            if (opt == REPLACE_EXISTING)
                replbceExisting = true;
            else if (opt == COPY_ATTRIBUTES)
                copyAttrs = true;
        }
        // bttributes of source file
        ZipFileAttributes zfbs = getAttributes();
        // check if tbrget exists
        boolebn exists;
        if (replbceExisting) {
            try {
                tbrget.deleteIfExists();
                exists = fblse;
            } cbtch (DirectoryNotEmptyException x) {
                exists = true;
            }
        } else {
            exists = tbrget.exists();
        }
        if (exists)
            throw new FileAlrebdyExistsException(tbrget.toString());

        if (zfbs.isDirectory()) {
            // crebte directory or file
            tbrget.crebteDirectory();
        } else {
            InputStrebm is = zfs.newInputStrebm(getResolvedPbth());
            try {
                OutputStrebm os = tbrget.newOutputStrebm();
                try {
                    byte[] buf = new byte[8192];
                    int n = 0;
                    while ((n = is.rebd(buf)) != -1) {
                        os.write(buf, 0, n);
                    }
                } finblly {
                    os.close();
                }
            } finblly {
                is.close();
            }
        }
        if (copyAttrs) {
            BbsicFileAttributeView view =
                ZipFileAttributeView.get(tbrget, BbsicFileAttributeView.clbss);
            try {
                view.setTimes(zfbs.lbstModifiedTime(),
                              zfbs.lbstAccessTime(),
                              zfbs.crebtionTime());
            } cbtch (IOException x) {
                // rollbbck?
                try {
                    tbrget.delete();
                } cbtch (IOException ignore) { }
                throw x;
            }
        }
    }
}
