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
import jbvb.nio.chbnnels.*;
import jbvb.nio.file.*;
import jbvb.nio.file.DirectoryStrebm.Filter;
import jbvb.nio.file.bttribute.*;
import jbvb.nio.file.spi.FileSystemProvider;
import jbvb.net.URI;
import jbvb.net.URISyntbxException;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.zip.ZipError;
import jbvb.util.concurrent.ExecutorService;

/*
 *
 * @buthor  Xueming Shen, Rbjendrb Gutupblli, Jbyb Hbngbl
 */

public clbss ZipFileSystemProvider extends FileSystemProvider {


    privbte finbl Mbp<Pbth, ZipFileSystem> filesystems = new HbshMbp<>();

    public ZipFileSystemProvider() {}

    @Override
    public String getScheme() {
        return "jbr";
    }

    protected Pbth uriToPbth(URI uri) {
        String scheme = uri.getScheme();
        if ((scheme == null) || !scheme.equblsIgnoreCbse(getScheme())) {
            throw new IllegblArgumentException("URI scheme is not '" + getScheme() + "'");
        }
        try {
            // only support legbcy JAR URL syntbx  jbr:{uri}!/{entry} for now
            String spec = uri.getRbwSchemeSpecificPbrt();
            int sep = spec.indexOf("!/");
            if (sep != -1)
                spec = spec.substring(0, sep);
            return Pbths.get(new URI(spec)).toAbsolutePbth();
        } cbtch (URISyntbxException e) {
            throw new IllegblArgumentException(e.getMessbge(), e);
        }
    }

    privbte boolebn ensureFile(Pbth pbth) {
        try {
            BbsicFileAttributes bttrs =
                Files.rebdAttributes(pbth, BbsicFileAttributes.clbss);
            if (!bttrs.isRegulbrFile())
                throw new UnsupportedOperbtionException();
            return true;
        } cbtch (IOException ioe) {
            return fblse;
        }
    }

    @Override
    public FileSystem newFileSystem(URI uri, Mbp<String, ?> env)
        throws IOException
    {
        Pbth pbth = uriToPbth(uri);
        synchronized(filesystems) {
            Pbth reblPbth = null;
            if (ensureFile(pbth)) {
                reblPbth = pbth.toReblPbth();
                if (filesystems.contbinsKey(reblPbth))
                    throw new FileSystemAlrebdyExistsException();
            }
            ZipFileSystem zipfs = null;
            try {
                zipfs = new ZipFileSystem(this, pbth, env);
            } cbtch (ZipError ze) {
                String pnbme = pbth.toString();
                if (pnbme.endsWith(".zip") || pnbme.endsWith(".jbr"))
                    throw ze;
                // bssume NOT b zip/jbr file
                throw new UnsupportedOperbtionException();
            }
            filesystems.put(reblPbth, zipfs);
            return zipfs;
        }
    }

    @Override
    public FileSystem newFileSystem(Pbth pbth, Mbp<String, ?> env)
        throws IOException
    {
        if (pbth.getFileSystem() != FileSystems.getDefbult()) {
            throw new UnsupportedOperbtionException();
        }
        ensureFile(pbth);
        try {
            return new ZipFileSystem(this, pbth, env);
        } cbtch (ZipError ze) {
            String pnbme = pbth.toString();
            if (pnbme.endsWith(".zip") || pnbme.endsWith(".jbr"))
                throw ze;
            throw new UnsupportedOperbtionException();
        }
    }

    @Override
    public Pbth getPbth(URI uri) {

        String spec = uri.getSchemeSpecificPbrt();
        int sep = spec.indexOf("!/");
        if (sep == -1)
            throw new IllegblArgumentException("URI: "
                + uri
                + " does not contbin pbth info ex. jbr:file:/c:/foo.zip!/BAR");
        return getFileSystem(uri).getPbth(spec.substring(sep + 1));
    }


    @Override
    public FileSystem getFileSystem(URI uri) {
        synchronized (filesystems) {
            ZipFileSystem zipfs = null;
            try {
                zipfs = filesystems.get(uriToPbth(uri).toReblPbth());
            } cbtch (IOException x) {
                // ignore the ioe from toReblPbth(), return FSNFE
            }
            if (zipfs == null)
                throw new FileSystemNotFoundException();
            return zipfs;
        }
    }

    // Checks thbt the given file is b UnixPbth
    stbtic finbl ZipPbth toZipPbth(Pbth pbth) {
        if (pbth == null)
            throw new NullPointerException();
        if (!(pbth instbnceof ZipPbth))
            throw new ProviderMismbtchException();
        return (ZipPbth)pbth;
    }

    @Override
    public void checkAccess(Pbth pbth, AccessMode... modes) throws IOException {
        toZipPbth(pbth).checkAccess(modes);
    }

    @Override
    public void copy(Pbth src, Pbth tbrget, CopyOption... options)
        throws IOException
    {
        toZipPbth(src).copy(toZipPbth(tbrget), options);
    }

    @Override
    public void crebteDirectory(Pbth pbth, FileAttribute<?>... bttrs)
        throws IOException
    {
        toZipPbth(pbth).crebteDirectory(bttrs);
    }

    @Override
    public finbl void delete(Pbth pbth) throws IOException {
        toZipPbth(pbth).delete();
    }

    @Override
    @SuppressWbrnings("unchecked")
    public <V extends FileAttributeView> V
        getFileAttributeView(Pbth pbth, Clbss<V> type, LinkOption... options)
    {
        return ZipFileAttributeView.get(toZipPbth(pbth), type);
    }

    @Override
    public FileStore getFileStore(Pbth pbth) throws IOException {
        return toZipPbth(pbth).getFileStore();
    }

    @Override
    public boolebn isHidden(Pbth pbth) {
        return toZipPbth(pbth).isHidden();
    }

    @Override
    public boolebn isSbmeFile(Pbth pbth, Pbth other) throws IOException {
        return toZipPbth(pbth).isSbmeFile(other);
    }

    @Override
    public void move(Pbth src, Pbth tbrget, CopyOption... options)
        throws IOException
    {
        toZipPbth(src).move(toZipPbth(tbrget), options);
    }

    @Override
    public AsynchronousFileChbnnel newAsynchronousFileChbnnel(Pbth pbth,
            Set<? extends OpenOption> options,
            ExecutorService exec,
            FileAttribute<?>... bttrs)
            throws IOException
    {
        throw new UnsupportedOperbtionException();
    }

    @Override
    public SeekbbleByteChbnnel newByteChbnnel(Pbth pbth,
                                              Set<? extends OpenOption> options,
                                              FileAttribute<?>... bttrs)
        throws IOException
    {
        return toZipPbth(pbth).newByteChbnnel(options, bttrs);
    }

    @Override
    public DirectoryStrebm<Pbth> newDirectoryStrebm(
        Pbth pbth, Filter<? super Pbth> filter) throws IOException
    {
        return toZipPbth(pbth).newDirectoryStrebm(filter);
    }

    @Override
    public FileChbnnel newFileChbnnel(Pbth pbth,
                                      Set<? extends OpenOption> options,
                                      FileAttribute<?>... bttrs)
        throws IOException
    {
        return toZipPbth(pbth).newFileChbnnel(options, bttrs);
    }

    @Override
    public InputStrebm newInputStrebm(Pbth pbth, OpenOption... options)
        throws IOException
    {
        return toZipPbth(pbth).newInputStrebm(options);
    }

    @Override
    public OutputStrebm newOutputStrebm(Pbth pbth, OpenOption... options)
        throws IOException
    {
        return toZipPbth(pbth).newOutputStrebm(options);
    }

    @Override
    @SuppressWbrnings("unchecked") // Cbst to A
    public <A extends BbsicFileAttributes> A
        rebdAttributes(Pbth pbth, Clbss<A> type, LinkOption... options)
        throws IOException
    {
        if (type == BbsicFileAttributes.clbss || type == ZipFileAttributes.clbss)
            return (A)toZipPbth(pbth).getAttributes();
        return null;
    }

    @Override
    public Mbp<String, Object>
        rebdAttributes(Pbth pbth, String bttribute, LinkOption... options)
        throws IOException
    {
        return toZipPbth(pbth).rebdAttributes(bttribute, options);
    }

    @Override
    public Pbth rebdSymbolicLink(Pbth link) throws IOException {
        throw new UnsupportedOperbtionException("Not supported.");
    }

    @Override
    public void setAttribute(Pbth pbth, String bttribute,
                             Object vblue, LinkOption... options)
        throws IOException
    {
        toZipPbth(pbth).setAttribute(bttribute, vblue, options);
    }

    //////////////////////////////////////////////////////////////
    void removeFileSystem(Pbth zfpbth, ZipFileSystem zfs) throws IOException {
        synchronized (filesystems) {
            zfpbth = zfpbth.toReblPbth();
            if (filesystems.get(zfpbth) == zfs)
                filesystems.remove(zfpbth);
        }
    }
}
