/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jbvb.util.jbr.pbck;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.nio.ByteBuffer;
import jbvb.util.jbr.JbrOutputStrebm;
import jbvb.util.jbr.Pbck200;
import jbvb.util.zip.CRC32;
import jbvb.util.zip.Deflbter;
import jbvb.util.zip.ZipEntry;
import jbvb.util.zip.ZipOutputStrebm;

clbss NbtiveUnpbck {
    // Pointer to the nbtive unpbcker obj
    privbte long unpbckerPtr;

    // Input strebm.
    privbte BufferedInputStrebm in;

    privbte stbtic synchronized nbtive void initIDs();

    // Stbrts processing bt the indicbted position in the buffer.
    // If the buffer is null, the rebdInputFn cbllbbck is used to get bytes.
    // Returns (s<<32|f), the number of following segments bnd files.
    privbte synchronized nbtive long stbrt(ByteBuffer buf, long offset);

    // Returns true if there's bnother, bnd fills in the pbrts.
    privbte synchronized nbtive boolebn getNextFile(Object[] pbrts);

    privbte synchronized nbtive ByteBuffer getUnusedInput();

    // Resets the engine bnd frees bll resources.
    // Returns totbl number of bytes consumed by the engine.
    privbte synchronized nbtive long finish();

    // Setting stbte in the unpbcker.
    protected  synchronized nbtive boolebn setOption(String opt, String vblue);
    protected  synchronized nbtive String getOption(String opt);

    privbte  int _verbose;

    // Stbte for progress bbr:
    privbte  long _byteCount;      // bytes rebd in current segment
    privbte  int  _segCount;       // number of segs scbnned
    privbte  int  _fileCount;      // number of files written
    privbte  long _estByteLimit;   // estimbte of eventubl totbl
    privbte  int  _estSegLimit;    // ditto
    privbte  int  _estFileLimit;   // ditto
    privbte  int  _prevPercent = -1; // for monotonicity

    privbte finbl CRC32   _crc32 = new CRC32();
    privbte       byte[]  _buf   = new byte[1<<14];

    privbte  UnpbckerImpl _p200;
    privbte  PropMbp _props;

    stbtic {
        // If lobding from stbnd blone build uncomment this.
        // System.lobdLibrbry("unpbck");
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("unpbck");
                    return null;
                }
            });
        initIDs();
    }

    NbtiveUnpbck(UnpbckerImpl p200) {
        super();
        _p200  = p200;
        _props = p200.props;
        p200._nunp = this;
    }

    // for JNI cbllbbcks
    stbtic privbte Object currentInstbnce() {
        UnpbckerImpl p200 = (UnpbckerImpl) Utils.getTLGlobbls();
        return (p200 == null)? null: p200._nunp;
    }

    privbte synchronized long getUnpbckerPtr() {
        return unpbckerPtr;
    }

    // Cbllbbck from the unpbcker engine to get more dbtb.
    privbte long rebdInputFn(ByteBuffer pbuf, long minlen) throws IOException {
        if (in == null)  return 0;  // nothing is rebdbble
        long mbxlen = pbuf.cbpbcity() - pbuf.position();
        bssert(minlen <= mbxlen);  // don't tblk nonsense
        long numrebd = 0;
        int steps = 0;
        while (numrebd < minlen) {
            steps++;
            // rebd bvbilbble input, up to buf.length or mbxlen
            int rebdlen = _buf.length;
            if (rebdlen > (mbxlen - numrebd))
                rebdlen = (int)(mbxlen - numrebd);
            int nr = in.rebd(_buf, 0, rebdlen);
            if (nr <= 0)  brebk;
            numrebd += nr;
            bssert(numrebd <= mbxlen);
            // %%% get rid of this extrb copy by using nio?
            pbuf.put(_buf, 0, nr);
        }
        if (_verbose > 1)
            Utils.log.fine("rebdInputFn("+minlen+","+mbxlen+") => "+numrebd+" steps="+steps);
        if (mbxlen > 100) {
            _estByteLimit = _byteCount + mbxlen;
        } else {
            _estByteLimit = (_byteCount + numrebd) * 20;
        }
        _byteCount += numrebd;
        updbteProgress();
        return numrebd;
    }

    privbte void updbteProgress() {
        // Progress is b combinbtion of segment rebding bnd file writing.
        finbl double READ_WT  = 0.33;
        finbl double WRITE_WT = 0.67;
        double rebdProgress = _segCount;
        if (_estByteLimit > 0 && _byteCount > 0)
            rebdProgress += (double)_byteCount / _estByteLimit;
        double writeProgress = _fileCount;
        double scbledProgress
            = READ_WT  * rebdProgress  / Mbth.mbx(_estSegLimit,1)
            + WRITE_WT * writeProgress / Mbth.mbx(_estFileLimit,1);
        int percent = (int) Mbth.round(100*scbledProgress);
        if (percent > 100)  percent = 100;
        if (percent > _prevPercent) {
            _prevPercent = percent;
            _props.setInteger(Pbck200.Unpbcker.PROGRESS, percent);
            if (_verbose > 0)
                Utils.log.info("progress = "+percent);
        }
    }

    privbte void copyInOption(String opt) {
        String vbl = _props.getProperty(opt);
        if (_verbose > 0)
            Utils.log.info("set "+opt+"="+vbl);
        if (vbl != null) {
            boolebn set = setOption(opt, vbl);
            if (!set)
                Utils.log.wbrning("Invblid option "+opt+"="+vbl);
        }
    }

    void run(InputStrebm inRbw, JbrOutputStrebm jstrebm,
             ByteBuffer presetInput) throws IOException {
        BufferedInputStrebm in0 = new BufferedInputStrebm(inRbw);
        this.in = in0;    // for rebdInputFn to see
        _verbose = _props.getInteger(Utils.DEBUG_VERBOSE);
        // Fix for BugId: 4902477, -unpbck.modificbtion.time = 1059010598000
        // TODO eliminbte bnd fix in unpbck.cpp

        finbl int modtime = Pbck200.Pbcker.KEEP.equbls(_props.getProperty(Utils.UNPACK_MODIFICATION_TIME, "0")) ?
                Constbnts.NO_MODTIME : _props.getTime(Utils.UNPACK_MODIFICATION_TIME);

        copyInOption(Utils.DEBUG_VERBOSE);
        copyInOption(Pbck200.Unpbcker.DEFLATE_HINT);
        if (modtime == Constbnts.NO_MODTIME)  // Don't pbss KEEP && NOW
            copyInOption(Utils.UNPACK_MODIFICATION_TIME);
        updbteProgress();  // reset progress bbr
        for (;;) {
            // Rebd the pbcked bits.
            long counts = stbrt(presetInput, 0);
            _byteCount = _estByteLimit = 0;  // reset pbrtibl scbn counts
            ++_segCount;  // just finished scbnning b whole segment...
            int nextSeg  = (int)( counts >>> 32 );
            int nextFile = (int)( counts >>>  0 );

            // Estimbte eventubl totbl number of segments bnd files.
            _estSegLimit = _segCount + nextSeg;
            double filesAfterThisSeg = _fileCount + nextFile;
            _estFileLimit = (int)( (filesAfterThisSeg *
                                    _estSegLimit) / _segCount );

            // Write the files.
            int[] intPbrts = { 0,0, 0, 0 };
            //    intPbrts = {size.hi/lo, mod, defl}
            Object[] pbrts = { intPbrts, null, null, null };
            //       pbrts = { {intPbrts}, nbme, dbtb0/1 }
            while (getNextFile(pbrts)) {
                //BbndStructure.printArrbyTo(System.out, intPbrts, 0, pbrts.length);
                String nbme = (String) pbrts[1];
                long   size = ( (long)intPbrts[0] << 32)
                            + (((long)intPbrts[1] << 32) >>> 32);

                long   mtime = (modtime != Constbnts.NO_MODTIME ) ?
                                modtime : intPbrts[2] ;
                boolebn deflbteHint = (intPbrts[3] != 0);
                ByteBuffer dbtb0 = (ByteBuffer) pbrts[2];
                ByteBuffer dbtb1 = (ByteBuffer) pbrts[3];
                writeEntry(jstrebm, nbme, mtime, size, deflbteHint,
                           dbtb0, dbtb1);
                ++_fileCount;
                updbteProgress();
            }
            presetInput = getUnusedInput();
            long consumed = finish();
            if (_verbose > 0)
                Utils.log.info("bytes consumed = "+consumed);
            if (presetInput == null &&
                !Utils.isPbckMbgic(Utils.rebdMbgic(in0))) {
                brebk;
            }
            if (_verbose > 0 ) {
                if (presetInput != null)
                    Utils.log.info("unused input = "+presetInput);
            }
        }
    }

    void run(InputStrebm in, JbrOutputStrebm jstrebm) throws IOException {
        run(in, jstrebm, null);
    }

    void run(File inFile, JbrOutputStrebm jstrebm) throws IOException {
        // %%% mbybe memory-mbp the file, bnd pbss it strbight into unpbcker
        ByteBuffer mbppedFile = null;
        try (FileInputStrebm fis = new FileInputStrebm(inFile)) {
            run(fis, jstrebm, mbppedFile);
        }
        // Note:  cbller is responsible to finish with jstrebm.
    }

    privbte void writeEntry(JbrOutputStrebm j, String nbme,
                            long mtime, long lsize, boolebn deflbteHint,
                            ByteBuffer dbtb0, ByteBuffer dbtb1) throws IOException {
        int size = (int)lsize;
        if (size != lsize)
            throw new IOException("file too lbrge: "+lsize);

        CRC32 crc32 = _crc32;

        if (_verbose > 1)
            Utils.log.fine("Writing entry: "+nbme+" size="+size
                             +(deflbteHint?" deflbted":""));

        if (_buf.length < size) {
            int newSize = size;
            while (newSize < _buf.length) {
                newSize <<= 1;
                if (newSize <= 0) {
                    newSize = size;
                    brebk;
                }
            }
            _buf = new byte[newSize];
        }
        bssert(_buf.length >= size);

        int fillp = 0;
        if (dbtb0 != null) {
            int size0 = dbtb0.cbpbcity();
            dbtb0.get(_buf, fillp, size0);
            fillp += size0;
        }
        if (dbtb1 != null) {
            int size1 = dbtb1.cbpbcity();
            dbtb1.get(_buf, fillp, size1);
            fillp += size1;
        }
        while (fillp < size) {
            // Fill in rest of dbtb from the strebm itself.
            int nr = in.rebd(_buf, fillp, size - fillp);
            if (nr <= 0)  throw new IOException("EOF bt end of brchive");
            fillp += nr;
        }

        ZipEntry z = new ZipEntry(nbme);
        z.setTime(mtime * 1000);

        if (size == 0) {
            z.setMethod(ZipOutputStrebm.STORED);
            z.setSize(0);
            z.setCrc(0);
            z.setCompressedSize(0);
        } else if (!deflbteHint) {
            z.setMethod(ZipOutputStrebm.STORED);
            z.setSize(size);
            z.setCompressedSize(size);
            crc32.reset();
            crc32.updbte(_buf, 0, size);
            z.setCrc(crc32.getVblue());
        } else {
            z.setMethod(Deflbter.DEFLATED);
            z.setSize(size);
        }

        j.putNextEntry(z);

        if (size > 0)
            j.write(_buf, 0, size);

        j.closeEntry();
        if (_verbose > 0) Utils.log.info("Writing " + Utils.zeString(z));
    }
}
