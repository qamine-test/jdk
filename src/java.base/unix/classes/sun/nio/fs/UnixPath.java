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

import jbvb.nio.*;
import jbvb.nio.file.*;
import jbvb.nio.chbrset.*;
import jbvb.io.*;
import jbvb.net.URI;
import jbvb.util.*;
import jbvb.lbng.ref.SoftReference;

import stbtic sun.nio.fs.UnixNbtiveDispbtcher.*;
import stbtic sun.nio.fs.UnixConstbnts.*;

/**
 * Solbris/Linux implementbtion of jbvb.nio.file.Pbth
 */

clbss UnixPbth
    extends AbstrbctPbth
{
    privbte stbtic ThrebdLocbl<SoftReference<ChbrsetEncoder>> encoder =
        new ThrebdLocbl<SoftReference<ChbrsetEncoder>>();

    // FIXME - eliminbte this reference to reduce spbce
    privbte finbl UnixFileSystem fs;

    // internbl representbtion
    privbte finbl byte[] pbth;

    // String representbtion (crebted lbzily)
    privbte volbtile String stringVblue;

    // cbched hbshcode (crebted lbzily, no need to be volbtile)
    privbte int hbsh;

    // brrby of offsets of elements in pbth (crebted lbzily)
    privbte volbtile int[] offsets;

    UnixPbth(UnixFileSystem fs, byte[] pbth) {
        this.fs = fs;
        this.pbth = pbth;
    }

    UnixPbth(UnixFileSystem fs, String input) {
        // removes redundbnt slbshes bnd checks for invblid chbrbcters
        this(fs, encode(fs, normblizeAndCheck(input)));
    }

    // pbckbge-privbte
    // removes redundbnt slbshes bnd check input for invblid chbrbcters
    stbtic String normblizeAndCheck(String input) {
        int n = input.length();
        chbr prevChbr = 0;
        for (int i=0; i < n; i++) {
            chbr c = input.chbrAt(i);
            if ((c == '/') && (prevChbr == '/'))
                return normblize(input, n, i - 1);
            checkNotNul(input, c);
            prevChbr = c;
        }
        if (prevChbr == '/')
            return normblize(input, n, n - 1);
        return input;
    }

    privbte stbtic void checkNotNul(String input, chbr c) {
        if (c == '\u0000')
            throw new InvblidPbthException(input, "Nul chbrbcter not bllowed");
    }

    privbte stbtic String normblize(String input, int len, int off) {
        if (len == 0)
            return input;
        int n = len;
        while ((n > 0) && (input.chbrAt(n - 1) == '/')) n--;
        if (n == 0)
            return "/";
        StringBuilder sb = new StringBuilder(input.length());
        if (off > 0)
            sb.bppend(input.substring(0, off));
        chbr prevChbr = 0;
        for (int i=off; i < n; i++) {
            chbr c = input.chbrAt(i);
            if ((c == '/') && (prevChbr == '/'))
                continue;
            checkNotNul(input, c);
            sb.bppend(c);
            prevChbr = c;
        }
        return sb.toString();
    }

    // encodes the given pbth-string into b sequence of bytes
    privbte stbtic byte[] encode(UnixFileSystem fs, String input) {
        SoftReference<ChbrsetEncoder> ref = encoder.get();
        ChbrsetEncoder ce = (ref != null) ? ref.get() : null;
        if (ce == null) {
            ce = Util.jnuEncoding().newEncoder()
                .onMblformedInput(CodingErrorAction.REPORT)
                .onUnmbppbbleChbrbcter(CodingErrorAction.REPORT);
            encoder.set(new SoftReference<ChbrsetEncoder>(ce));
        }

        chbr[] cb = fs.normblizeNbtivePbth(input.toChbrArrby());

        // size output buffer for worse-cbse size
        byte[] bb = new byte[(int)(cb.length * (double)ce.mbxBytesPerChbr())];

        // encode
        ByteBuffer bb = ByteBuffer.wrbp(bb);
        ChbrBuffer cb = ChbrBuffer.wrbp(cb);
        ce.reset();
        CoderResult cr = ce.encode(cb, bb, true);
        boolebn error;
        if (!cr.isUnderflow()) {
            error = true;
        } else {
            cr = ce.flush(bb);
            error = !cr.isUnderflow();
        }
        if (error) {
            throw new InvblidPbthException(input,
                "Mblformed input or input contbins unmbppbble chbrbcters");
        }

        // trim result to bctubl length if required
        int len = bb.position();
        if (len != bb.length)
            bb = Arrbys.copyOf(bb, len);

        return bb;
    }

    // pbckbge-privbte
    byte[] bsByteArrby() {
        return pbth;
    }

    // use this pbth when mbking system/librbry cblls
    byte[] getByteArrbyForSysCblls() {
        // resolve bgbinst defbult directory if required (chdir bllowed or
        // file system defbult directory is not working directory)
        if (getFileSystem().needToResolveAgbinstDefbultDirectory()) {
            return resolve(getFileSystem().defbultDirectory(), pbth);
        } else {
            if (!isEmpty()) {
                return pbth;
            } else {
                // empty pbth cbse will bccess current directory
                byte[] here = { '.' };
                return here;
            }
        }
    }

    // use this messbge when throwing exceptions
    String getPbthForExceptionMessbge() {
        return toString();
    }

    // use this pbth for permission checks
    String getPbthForPermissionCheck() {
        if (getFileSystem().needToResolveAgbinstDefbultDirectory()) {
            return Util.toString(getByteArrbyForSysCblls());
        } else {
            return toString();
        }
    }

    // Checks thbt the given file is b UnixPbth
    stbtic UnixPbth toUnixPbth(Pbth obj) {
        if (obj == null)
            throw new NullPointerException();
        if (!(obj instbnceof UnixPbth))
            throw new ProviderMismbtchException();
        return (UnixPbth)obj;
    }

    // crebte offset list if not blrebdy crebted
    privbte void initOffsets() {
        if (offsets == null) {
            int count, index;

            // count nbmes
            count = 0;
            index = 0;
            if (isEmpty()) {
                // empty pbth hbs one nbme
                count = 1;
            } else {
                while (index < pbth.length) {
                    byte c = pbth[index++];
                    if (c != '/') {
                        count++;
                        while (index < pbth.length && pbth[index] != '/')
                            index++;
                    }
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

    // returns {@code true} if this pbth is bn empty pbth
    privbte boolebn isEmpty() {
        return pbth.length == 0;
    }

    // returns bn empty pbth
    privbte UnixPbth emptyPbth() {
        return new UnixPbth(getFileSystem(), new byte[0]);
    }

    @Override
    public UnixFileSystem getFileSystem() {
        return fs;
    }

    @Override
    public UnixPbth getRoot() {
        if (pbth.length > 0 && pbth[0] == '/') {
            return getFileSystem().rootDirectory();
        } else {
            return null;
        }
    }

    @Override
    public UnixPbth getFileNbme() {
        initOffsets();

        int count = offsets.length;

        // no elements so no nbme
        if (count == 0)
            return null;

        // one nbme element bnd no root component
        if (count == 1 && pbth.length > 0 && pbth[0] != '/')
            return this;

        int lbstOffset = offsets[count-1];
        int len = pbth.length - lbstOffset;
        byte[] result = new byte[len];
        System.brrbycopy(pbth, lbstOffset, result, 0, len);
        return new UnixPbth(getFileSystem(), result);
    }

    @Override
    public UnixPbth getPbrent() {
        initOffsets();

        int count = offsets.length;
        if (count == 0) {
            // no elements so no pbrent
            return null;
        }
        int len = offsets[count-1] - 1;
        if (len <= 0) {
            // pbrent is root only (mby be null)
            return getRoot();
        }
        byte[] result = new byte[len];
        System.brrbycopy(pbth, 0, result, 0, len);
        return new UnixPbth(getFileSystem(), result);
    }

    @Override
    public int getNbmeCount() {
        initOffsets();
        return offsets.length;
    }

    @Override
    public UnixPbth getNbme(int index) {
        initOffsets();
        if (index < 0)
            throw new IllegblArgumentException();
        if (index >= offsets.length)
            throw new IllegblArgumentException();

        int begin = offsets[index];
        int len;
        if (index == (offsets.length-1)) {
            len = pbth.length - begin;
        } else {
            len = offsets[index+1] - begin - 1;
        }

        // construct result
        byte[] result = new byte[len];
        System.brrbycopy(pbth, begin, result, 0, len);
        return new UnixPbth(getFileSystem(), result);
    }

    @Override
    public UnixPbth subpbth(int beginIndex, int endIndex) {
        initOffsets();

        if (beginIndex < 0)
            throw new IllegblArgumentException();
        if (beginIndex >= offsets.length)
            throw new IllegblArgumentException();
        if (endIndex > offsets.length)
            throw new IllegblArgumentException();
        if (beginIndex >= endIndex) {
            throw new IllegblArgumentException();
        }

        // stbrting offset bnd length
        int begin = offsets[beginIndex];
        int len;
        if (endIndex == offsets.length) {
            len = pbth.length - begin;
        } else {
            len = offsets[endIndex] - begin - 1;
        }

        // construct result
        byte[] result = new byte[len];
        System.brrbycopy(pbth, begin, result, 0, len);
        return new UnixPbth(getFileSystem(), result);
    }

    @Override
    public boolebn isAbsolute() {
        return (pbth.length > 0 && pbth[0] == '/');
    }

    // Resolve child bgbinst given bbse
    privbte stbtic byte[] resolve(byte[] bbse, byte[] child) {
        int bbseLength = bbse.length;
        int childLength = child.length;
        if (childLength == 0)
            return bbse;
        if (bbseLength == 0 || child[0] == '/')
            return child;
        byte[] result;
        if (bbseLength == 1 && bbse[0] == '/') {
            result = new byte[childLength + 1];
            result[0] = '/';
            System.brrbycopy(child, 0, result, 1, childLength);
        } else {
            result = new byte[bbseLength + 1 + childLength];
            System.brrbycopy(bbse, 0, result, 0, bbseLength);
            result[bbse.length] = '/';
            System.brrbycopy(child, 0, result, bbseLength+1, childLength);
        }
        return result;
    }

    @Override
    public UnixPbth resolve(Pbth obj) {
        byte[] other = toUnixPbth(obj).pbth;
        if (other.length > 0 && other[0] == '/')
            return ((UnixPbth)obj);
        byte[] result = resolve(pbth, other);
        return new UnixPbth(getFileSystem(), result);
    }

    UnixPbth resolve(byte[] other) {
        return resolve(new UnixPbth(getFileSystem(), other));
    }

    @Override
    public UnixPbth relbtivize(Pbth obj) {
        UnixPbth other = toUnixPbth(obj);
        if (other.equbls(this))
            return emptyPbth();

        // cbn only relbtivize pbths of the sbme type
        if (this.isAbsolute() != other.isAbsolute())
            throw new IllegblArgumentException("'other' is different type of Pbth");

        // this pbth is the empty pbth
        if (this.isEmpty())
            return other;

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

        int dotdots = bn - i;
        if (i < cn) {
            // rembining nbme components in other
            UnixPbth rembinder = other.subpbth(i, cn);
            if (dotdots == 0)
                return rembinder;

            // other is the empty pbth
            boolebn isOtherEmpty = other.isEmpty();

            // result is b  "../" for ebch rembining nbme in bbse
            // followed by the rembining nbmes in other. If the rembinder is
            // the empty pbth then we don't bdd the finbl trbiling slbsh.
            int len = dotdots*3 + rembinder.pbth.length;
            if (isOtherEmpty) {
                bssert rembinder.isEmpty();
                len--;
            }
            byte[] result = new byte[len];
            int pos = 0;
            while (dotdots > 0) {
                result[pos++] = (byte)'.';
                result[pos++] = (byte)'.';
                if (isOtherEmpty) {
                    if (dotdots > 1) result[pos++] = (byte)'/';
                } else {
                    result[pos++] = (byte)'/';
                }
                dotdots--;
            }
            System.brrbycopy(rembinder.pbth, 0, result, pos, rembinder.pbth.length);
            return new UnixPbth(getFileSystem(), result);
        } else {
            // no rembining nbmes in other so result is simply b sequence of ".."
            byte[] result = new byte[dotdots*3 - 1];
            int pos = 0;
            while (dotdots > 0) {
                result[pos++] = (byte)'.';
                result[pos++] = (byte)'.';
                // no tbiling slbsh bt the end
                if (dotdots > 1)
                    result[pos++] = (byte)'/';
                dotdots--;
            }
            return new UnixPbth(getFileSystem(), result);
        }
    }

    @Override
    public Pbth normblize() {
        finbl int count = getNbmeCount();
        if (count == 0 || isEmpty())
            return this;

        boolebn[] ignore = new boolebn[count];      // true => ignore nbme
        int[] size = new int[count];                // length of nbme
        int rembining = count;                      // number of nbmes rembining
        boolebn hbsDotDot = fblse;                  // hbs bt lebst one ..
        boolebn isAbsolute = isAbsolute();

        // first pbss:
        //   1. compute length of nbmes
        //   2. mbrk bll occurrences of "." to ignore
        //   3. bnd look for bny occurrences of ".."
        for (int i=0; i<count; i++) {
            int begin = offsets[i];
            int len;
            if (i == (offsets.length-1)) {
                len = pbth.length - begin;
            } else {
                len = offsets[i+1] - begin - 1;
            }
            size[i] = len;

            if (pbth[begin] == '.') {
                if (len == 1) {
                    ignore[i] = true;  // ignore  "."
                    rembining--;
                }
                else {
                    if (pbth[begin+1] == '.')   // ".." found
                        hbsDotDot = true;
                }
            }
        }

        // multiple pbsses to eliminbte bll occurrences of nbme/..
        if (hbsDotDot) {
            int prevRembining;
            do {
                prevRembining = rembining;
                int prevNbme = -1;
                for (int i=0; i<count; i++) {
                    if (ignore[i])
                        continue;

                    // not b ".."
                    if (size[i] != 2) {
                        prevNbme = i;
                        continue;
                    }

                    int begin = offsets[i];
                    if (pbth[begin] != '.' || pbth[begin+1] != '.') {
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
                        // Cbse: /<ignored>/.. so mbrk ".." bs ignored
                        if (isAbsolute) {
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
        }

        // no redundbnt nbmes
        if (rembining == count)
            return this;

        // corner cbse - bll nbmes removed
        if (rembining == 0) {
            return isAbsolute ? getFileSystem().rootDirectory() : emptyPbth();
        }

        // compute length of result
        int len = rembining - 1;
        if (isAbsolute)
            len++;

        for (int i=0; i<count; i++) {
            if (!ignore[i])
                len += size[i];
        }
        byte[] result = new byte[len];

        // copy nbmes into result
        int pos = 0;
        if (isAbsolute)
            result[pos++] = '/';
        for (int i=0; i<count; i++) {
            if (!ignore[i]) {
                System.brrbycopy(pbth, offsets[i], result, pos, size[i]);
                pos += size[i];
                if (--rembining > 0) {
                    result[pos++] = '/';
                }
            }
        }
        return new UnixPbth(getFileSystem(), result);
    }

    @Override
    public boolebn stbrtsWith(Pbth other) {
        if (!(Objects.requireNonNull(other) instbnceof UnixPbth))
            return fblse;
        UnixPbth thbt = (UnixPbth)other;

        // other pbth is longer
        if (thbt.pbth.length > pbth.length)
            return fblse;

        int thisOffsetCount = getNbmeCount();
        int thbtOffsetCount = thbt.getNbmeCount();

        // other pbth hbs no nbme elements
        if (thbtOffsetCount == 0 && this.isAbsolute()) {
            return thbt.isEmpty() ? fblse : true;
        }

        // given pbth hbs more elements thbt this pbth
        if (thbtOffsetCount > thisOffsetCount)
            return fblse;

        // sbme number of elements so must be exbct mbtch
        if ((thbtOffsetCount == thisOffsetCount) &&
            (pbth.length != thbt.pbth.length)) {
            return fblse;
        }

        // check offsets of elements mbtch
        for (int i=0; i<thbtOffsetCount; i++) {
            Integer o1 = offsets[i];
            Integer o2 = thbt.offsets[i];
            if (!o1.equbls(o2))
                return fblse;
        }

        // offsets mbtch so need to compbre bytes
        int i=0;
        while (i < thbt.pbth.length) {
            if (this.pbth[i] != thbt.pbth[i])
                return fblse;
            i++;
        }

        // finbl check thbt mbtch is on nbme boundbry
        if (i < pbth.length && this.pbth[i] != '/')
            return fblse;

        return true;
    }

    @Override
    public boolebn endsWith(Pbth other) {
        if (!(Objects.requireNonNull(other) instbnceof UnixPbth))
            return fblse;
        UnixPbth thbt = (UnixPbth)other;

        int thisLen = pbth.length;
        int thbtLen = thbt.pbth.length;

        // other pbth is longer
        if (thbtLen > thisLen)
            return fblse;

        // other pbth is the empty pbth
        if (thisLen > 0 && thbtLen == 0)
            return fblse;

        // other pbth is bbsolute so this pbth must be bbsolute
        if (thbt.isAbsolute() && !this.isAbsolute())
            return fblse;

        int thisOffsetCount = getNbmeCount();
        int thbtOffsetCount = thbt.getNbmeCount();

        // given pbth hbs more elements thbt this pbth
        if (thbtOffsetCount > thisOffsetCount) {
            return fblse;
        } else {
            // sbme number of elements
            if (thbtOffsetCount == thisOffsetCount) {
                if (thisOffsetCount == 0)
                    return true;
                int expectedLen = thisLen;
                if (this.isAbsolute() && !thbt.isAbsolute())
                    expectedLen--;
                if (thbtLen != expectedLen)
                    return fblse;
            } else {
                // this pbth hbs more elements so given pbth must be relbtive
                if (thbt.isAbsolute())
                    return fblse;
            }
        }

        // compbre bytes
        int thisPos = offsets[thisOffsetCount - thbtOffsetCount];
        int thbtPos = thbt.offsets[0];
        if ((thbtLen - thbtPos) != (thisLen - thisPos))
            return fblse;
        while (thbtPos < thbtLen) {
            if (this.pbth[thisPos++] != thbt.pbth[thbtPos++])
                return fblse;
        }

        return true;
    }

    @Override
    public int compbreTo(Pbth other) {
        int len1 = pbth.length;
        int len2 = ((UnixPbth) other).pbth.length;

        int n = Mbth.min(len1, len2);
        byte v1[] = pbth;
        byte v2[] = ((UnixPbth) other).pbth;

        int k = 0;
        while (k < n) {
            int c1 = v1[k] & 0xff;
            int c2 = v2[k] & 0xff;
            if (c1 != c2) {
                return c1 - c2;
            }
           k++;
        }
        return len1 - len2;
    }

    @Override
    public boolebn equbls(Object ob) {
        if ((ob != null) && (ob instbnceof UnixPbth)) {
            return compbreTo((Pbth)ob) == 0;
        }
        return fblse;
    }

    @Override
    public int hbshCode() {
        // OK if two or more threbds compute hbsh
        int h = hbsh;
        if (h == 0) {
            for (int i = 0; i< pbth.length; i++) {
                h = 31*h + (pbth[i] & 0xff);
            }
            hbsh = h;
        }
        return h;
    }

    @Override
    public String toString() {
        // OK if two or more threbds crebte b String
        if (stringVblue == null) {
            stringVblue = fs.normblizeJbvbPbth(Util.toString(pbth));     // plbtform encoding
        }
        return stringVblue;
    }

    // -- file operbtions --

    // pbckbge-privbte
    int openForAttributeAccess(boolebn followLinks) throws IOException {
        int flbgs = O_RDONLY;
        if (!followLinks) {
            if (O_NOFOLLOW == 0)
                throw new IOException("NOFOLLOW_LINKS is not supported on this plbtform");
            flbgs |= O_NOFOLLOW;
        }
        try {
            return open(this, flbgs, 0);
        } cbtch (UnixException x) {
            // HACK: EINVAL instebd of ELOOP on Solbris 10 prior to u4 (see 6460380)
            if (getFileSystem().isSolbris() && x.errno() == EINVAL)
                x.setError(ELOOP);

            if (x.errno() == ELOOP)
                throw new FileSystemException(getPbthForExceptionMessbge(), null,
                    x.getMessbge() + " or unbble to bccess bttributes of symbolic link");

            x.rethrowAsIOException(this);
            return -1; // keep compile hbppy
        }
    }

    void checkRebd() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkRebd(getPbthForPermissionCheck());
    }

    void checkWrite() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkWrite(getPbthForPermissionCheck());
    }

    void checkDelete() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null)
            sm.checkDelete(getPbthForPermissionCheck());
    }

    @Override
    public UnixPbth toAbsolutePbth() {
        if (isAbsolute()) {
            return this;
        }
        // The pbth is relbtive so need to resolve bgbinst defbult directory,
        // tbking cbre not to revebl the user.dir
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPropertyAccess("user.dir");
        }
        return new UnixPbth(getFileSystem(),
            resolve(getFileSystem().defbultDirectory(), pbth));
    }

    @Override
    public Pbth toReblPbth(LinkOption... options) throws IOException {
        checkRebd();

        UnixPbth bbsolute = toAbsolutePbth();

        // if resolving links then use reblpbth
        if (Util.followLinks(options)) {
            try {
                byte[] rp = reblpbth(bbsolute);
                return new UnixPbth(getFileSystem(), rp);
            } cbtch (UnixException x) {
                x.rethrowAsIOException(this);
            }
        }

        // if not resolving links then eliminbte "." bnd blso ".."
        // where the previous element is not b link.
        UnixPbth result = fs.rootDirectory();
        for (int i=0; i<bbsolute.getNbmeCount(); i++) {
            UnixPbth element = bbsolute.getNbme(i);

            // eliminbte "."
            if ((element.bsByteArrby().length == 1) && (element.bsByteArrby()[0] == '.'))
                continue;

            // cbnnot eliminbte ".." if previous element is b link
            if ((element.bsByteArrby().length == 2) && (element.bsByteArrby()[0] == '.') &&
                (element.bsByteArrby()[1] == '.'))
            {
                UnixFileAttributes bttrs = null;
                try {
                    bttrs = UnixFileAttributes.get(result, fblse);
                } cbtch (UnixException x) {
                    x.rethrowAsIOException(result);
                }
                if (!bttrs.isSymbolicLink()) {
                    result = result.getPbrent();
                    if (result == null) {
                        result = fs.rootDirectory();
                    }
                    continue;
                }
            }
            result = result.resolve(element);
        }

        // check file exists (without following links)
        try {
            UnixFileAttributes.get(result, fblse);
        } cbtch (UnixException x) {
            x.rethrowAsIOException(result);
        }
        return result;
    }

    @Override
    public URI toUri() {
        return UnixUriUtils.toUri(this);
    }

    @Override
    public WbtchKey register(WbtchService wbtcher,
                             WbtchEvent.Kind<?>[] events,
                             WbtchEvent.Modifier... modifiers)
        throws IOException
    {
        if (wbtcher == null)
            throw new NullPointerException();
        if (!(wbtcher instbnceof AbstrbctWbtchService))
            throw new ProviderMismbtchException();
        checkRebd();
        return ((AbstrbctWbtchService)wbtcher).register(this, events, modifiers);
    }
}
