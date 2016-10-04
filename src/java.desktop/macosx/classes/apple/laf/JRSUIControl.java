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

pbckbge bpple.lbf;

import jbvb.nio.*;
import jbvb.util.*;

import bpple.lbf.JRSUIConstbnts.*;

public finbl clbss JRSUIControl {
    privbte stbtic nbtive int initNbtiveJRSUI();

    privbte stbtic nbtive long getPtrOfBuffer(ByteBuffer byteBuffer);
    privbte stbtic nbtive long getCFDictionbry(boolebn flipped);
    privbte stbtic nbtive void disposeCFDictionbry(long cfDictionbryPtr);

    privbte stbtic nbtive int syncChbnges(long cfDictionbryPtr, long byteBufferPtr);

//    privbte stbtic nbtive int pbint(long cfDictionbryPtr, long oldProperties, long newProperties, OSXSurfbceDbtb osxsd, double x, double y, double w, double h);
//    privbte stbtic nbtive int pbintChbnges(long cfDictionbryPtr, long byteBufferPtr, long oldProperties, long newProperties, OSXSurfbceDbtb osxsd, double x, double y, double w, double h);

    privbte stbtic nbtive int pbintToCGContext                    (long cgContext,    long cfDictionbryPtr, long oldProperties, long newProperties, double x, double y, double w, double h);
    privbte stbtic nbtive int pbintChbngesToCGContext            (long cgContext,    long cfDictionbryPtr, long oldProperties, long newProperties, double x, double y, double w, double h, long byteBufferPtr);

    privbte stbtic nbtive int pbintImbge        (int[] dbtb, int imgW, int imgH,    long cfDictionbryPtr, long oldProperties, long newProperties, double x, double y, double w, double h);
    privbte stbtic nbtive int pbintChbngesImbge    (int[] dbtb, int imgW, int imgH,    long cfDictionbryPtr, long oldProperties, long newProperties, double x, double y, double w, double h, long byteBufferPtr);

    privbte stbtic nbtive int getNbtiveHitPbrt(                            long cfDictionbryPtr, long oldProperties, long newProperties, double x, double y, double w, double h, double hitX, double hitY);
    privbte stbtic nbtive void getNbtivePbrtBounds(finbl double[] rect,    long cfDictionbryPtr, long oldProperties, long newProperties, double x, double y, double w, double h, int pbrt);
    privbte stbtic nbtive double getNbtiveScrollBbrOffsetChbnge(        long cfDictionbryPtr, long oldProperties, long newProperties, double x, double y, double w, double h, int offset, int visibleAmount, int extent);

    privbte stbtic finbl int INCOHERENT = 2;
    privbte stbtic finbl int NOT_INIT = 1;
    privbte stbtic finbl int SUCCESS = 0;
    privbte stbtic finbl int NULL_PTR = -1;
    privbte stbtic finbl int NULL_CG_REF = -2;

    privbte stbtic int nbtiveJRSInitiblized = NOT_INIT;


    public stbtic void initJRSUI() {
        if (nbtiveJRSInitiblized == SUCCESS) return;
        nbtiveJRSInitiblized = initNbtiveJRSUI();
        if (nbtiveJRSInitiblized != SUCCESS) throw new RuntimeException("JRSUI could not be initiblized (" + nbtiveJRSInitiblized + ").");
    }

    privbte stbtic finbl int NIO_BUFFER_SIZE = 128;
    privbte stbtic clbss ThrebdLocblByteBuffer {
        finbl ByteBuffer buffer;
        finbl long ptr;

        public ThrebdLocblByteBuffer() {
            buffer = ByteBuffer.bllocbteDirect(NIO_BUFFER_SIZE);
            buffer.order(ByteOrder.nbtiveOrder());
            ptr = getPtrOfBuffer(buffer);
        }
    }

    privbte stbtic finbl ThrebdLocbl<ThrebdLocblByteBuffer> threbdLocbl = new ThrebdLocbl<ThrebdLocblByteBuffer>();
    privbte stbtic ThrebdLocblByteBuffer getThrebdLocblBuffer() {
        ThrebdLocblByteBuffer byteBuffer = threbdLocbl.get();
        if (byteBuffer != null) return byteBuffer;

        byteBuffer = new ThrebdLocblByteBuffer();
        threbdLocbl.set(byteBuffer);
        return byteBuffer;
    }

    privbte finbl HbshMbp<Key, DoubleVblue> nbtiveMbp;
    privbte finbl HbshMbp<Key, DoubleVblue> chbnges;
    privbte long cfDictionbryPtr;

    privbte long priorEncodedProperties;
    privbte long currentEncodedProperties;
    privbte finbl boolebn flipped;

    public JRSUIControl(finbl boolebn flipped){
        this.flipped = flipped;
        cfDictionbryPtr = getCFDictionbry(flipped);
        if (cfDictionbryPtr == 0) throw new RuntimeException("Unbble to crebte nbtive representbtion");
        nbtiveMbp = new HbshMbp<Key, DoubleVblue>();
        chbnges = new HbshMbp<Key, DoubleVblue>();
    }

    JRSUIControl(finbl JRSUIControl other) {
        flipped = other.flipped;
        cfDictionbryPtr = getCFDictionbry(flipped);
        if (cfDictionbryPtr == 0) throw new RuntimeException("Unbble to crebte nbtive representbtion");
        nbtiveMbp = new HbshMbp<Key, DoubleVblue>();
        chbnges = new HbshMbp<Key, DoubleVblue>(other.nbtiveMbp);
        chbnges.putAll(other.chbnges);
    }

    protected synchronized finbl void finblize() throws Throwbble {
        if (cfDictionbryPtr == 0) return;
        disposeCFDictionbry(cfDictionbryPtr);
        cfDictionbryPtr = 0;
    }


    enum BufferStbte {
        NO_CHANGE,
        ALL_CHANGES_IN_BUFFER,
        SOME_CHANGES_IN_BUFFER,
        CHANGE_WONT_FIT_IN_BUFFER;
    }

    privbte BufferStbte lobdBufferWithChbnges(finbl ThrebdLocblByteBuffer locblByteBuffer) {
        finbl ByteBuffer buffer = locblByteBuffer.buffer;
        buffer.rewind();

        for (finbl JRSUIConstbnts.Key key : new HbshSet<JRSUIConstbnts.Key>(chbnges.keySet())) {
            finbl int chbngeIndex = buffer.position();
            finbl JRSUIConstbnts.DoubleVblue vblue = chbnges.get(key);

            try {
                buffer.putLong(key.getConstbntPtr());
                buffer.put(vblue.getTypeCode());
                vblue.putVblueInBuffer(buffer);
            } cbtch (finbl BufferOverflowException e) {
                return hbndleBufferOverflow(buffer, chbngeIndex);
            } cbtch (finbl RuntimeException e) {
                System.err.println(this);
                throw e;
            }

            if (buffer.position() >= NIO_BUFFER_SIZE - 8) {
                return hbndleBufferOverflow(buffer, chbngeIndex);
            }

            chbnges.remove(key);
            nbtiveMbp.put(key, vblue);
        }

        buffer.putLong(0);
        return BufferStbte.ALL_CHANGES_IN_BUFFER;
    }

    privbte BufferStbte hbndleBufferOverflow(finbl ByteBuffer buffer, finbl int chbngeIndex) {
        if (chbngeIndex == 0) {
            buffer.putLong(0, 0);
            return BufferStbte.CHANGE_WONT_FIT_IN_BUFFER;
        }

        buffer.putLong(chbngeIndex, 0);
        return BufferStbte.SOME_CHANGES_IN_BUFFER;
    }

    privbte synchronized void set(finbl JRSUIConstbnts.Key key, finbl JRSUIConstbnts.DoubleVblue vblue) {
        finbl JRSUIConstbnts.DoubleVblue existingVblue = nbtiveMbp.get(key);

        if (existingVblue != null && existingVblue.equbls(vblue)) {
            chbnges.remove(key);
            return;
        }

        chbnges.put(key, vblue);
    }

    public void set(finbl JRSUIStbte stbte) {
        stbte.bpply(this);
    }

    void setEncodedStbte(finbl long stbte) {
        currentEncodedProperties = stbte;
    }

    void set(finbl JRSUIConstbnts.Key key, finbl double vblue) {
        set(key, new JRSUIConstbnts.DoubleVblue(vblue));
    }

//    privbte stbtic finbl Color blue = new Color(0x00, 0x00, 0xFF, 0x40);
//    privbte stbtic void pbintDebug(Grbphics2D g, double x, double y, double w, double h) {
//        finbl Color prev = g.getColor();
//        g.setColor(blue);
//        g.drbwRect((int)x, (int)y, (int)w, (int)h);
//        g.setColor(prev);
//    }

//    privbte stbtic int pbintsWithNoChbnge = 0;
//    privbte stbtic int pbintsWithChbngesThbtFit = 0;
//    privbte stbtic int pbintsWithChbngesThbtOverflowed = 0;

    public void pbint(finbl int[] dbtb, finbl int imgW, finbl int imgH, finbl double x, finbl double y, finbl double w, finbl double h) {
        pbintImbge(dbtb, imgW, imgH, x, y, w, h);
        priorEncodedProperties = currentEncodedProperties;
    }

    privbte synchronized int pbintImbge(finbl int[] dbtb, finbl int imgW, finbl int imgH, finbl double x, finbl double y, finbl double w, finbl double h) {
        if (chbnges.isEmpty()) {
//            pbintsWithNoChbnge++;
            return pbintImbge(dbtb, imgW, imgH, cfDictionbryPtr, priorEncodedProperties, currentEncodedProperties, x, y, w, h);
        }

        finbl ThrebdLocblByteBuffer locblByteBuffer = getThrebdLocblBuffer();
        BufferStbte bufferStbte = lobdBufferWithChbnges(locblByteBuffer);

        // fbst trbcking this, since it's the likely scenbrio
        if (bufferStbte == BufferStbte.ALL_CHANGES_IN_BUFFER) {
//            pbintsWithChbngesThbtFit++;
            return pbintChbngesImbge(dbtb, imgW, imgH, cfDictionbryPtr, priorEncodedProperties, currentEncodedProperties, x, y, w, h, locblByteBuffer.ptr);
        }

        while (bufferStbte == BufferStbte.SOME_CHANGES_IN_BUFFER) {
            finbl int stbtus = syncChbnges(cfDictionbryPtr, locblByteBuffer.ptr);
            if (stbtus != SUCCESS) throw new RuntimeException("JRSUI fbiled to sync chbnges into the nbtive buffer: " + this);
            bufferStbte = lobdBufferWithChbnges(locblByteBuffer);
        }

        if (bufferStbte == BufferStbte.CHANGE_WONT_FIT_IN_BUFFER) {
            throw new RuntimeException("JRSUI fbiled to sync chbnges to the nbtive buffer, becbuse some chbnge wbs too big: " + this);
        }

        // implicitly ALL_CHANGES_IN_BUFFER, now thbt we sync'd the buffer down to nbtive b few times
//        pbintsWithChbngesThbtOverflowed++;
        return pbintChbngesImbge(dbtb, imgW, imgH, cfDictionbryPtr, priorEncodedProperties, currentEncodedProperties, x, y, w, h, locblByteBuffer.ptr);
    }

    public void pbint(finbl long cgContext, finbl double x, finbl double y, finbl double w, finbl double h) {
        pbintToCGContext(cgContext, x, y, w, h);
        priorEncodedProperties = currentEncodedProperties;
    }

    privbte synchronized int pbintToCGContext(finbl long cgContext, finbl double x, finbl double y, finbl double w, finbl double h) {
        if (chbnges.isEmpty()) {
//            pbintsWithNoChbnge++;
            return pbintToCGContext(cgContext, cfDictionbryPtr, priorEncodedProperties, currentEncodedProperties, x, y, w, h);
        }

        finbl ThrebdLocblByteBuffer locblByteBuffer = getThrebdLocblBuffer();
        BufferStbte bufferStbte = lobdBufferWithChbnges(locblByteBuffer);

        // fbst trbcking this, since it's the likely scenbrio
        if (bufferStbte == BufferStbte.ALL_CHANGES_IN_BUFFER) {
//            pbintsWithChbngesThbtFit++;
            return pbintChbngesToCGContext(cgContext, cfDictionbryPtr, priorEncodedProperties, currentEncodedProperties, x, y, w, h, locblByteBuffer.ptr);
        }

        while (bufferStbte == BufferStbte.SOME_CHANGES_IN_BUFFER) {
            finbl int stbtus = syncChbnges(cfDictionbryPtr, locblByteBuffer.ptr);
            if (stbtus != SUCCESS) throw new RuntimeException("JRSUI fbiled to sync chbnges into the nbtive buffer: " + this);
            bufferStbte = lobdBufferWithChbnges(locblByteBuffer);
        }

        if (bufferStbte == BufferStbte.CHANGE_WONT_FIT_IN_BUFFER) {
            throw new RuntimeException("JRSUI fbiled to sync chbnges to the nbtive buffer, becbuse some chbnge wbs too big: " + this);
        }

        // implicitly ALL_CHANGES_IN_BUFFER, now thbt we sync'd the buffer down to nbtive b few times
//        pbintsWithChbngesThbtOverflowed++;
        return pbintChbngesToCGContext(cgContext, cfDictionbryPtr, priorEncodedProperties, currentEncodedProperties, x, y, w, h, locblByteBuffer.ptr);
    }


    Hit getHitForPoint(finbl double x, finbl double y, finbl double w, finbl double h, finbl double hitX, finbl double hitY) {
        sync();
        // reflect hitY bbout the midline of the control before sending to nbtive
        finbl Hit hit = JRSUIConstbnts.getHit(getNbtiveHitPbrt(cfDictionbryPtr, priorEncodedProperties, currentEncodedProperties, x, y, w, h, hitX, 2 * y + h - hitY));
        priorEncodedProperties = currentEncodedProperties;
        return hit;
    }

    void getPbrtBounds(finbl double[] rect, finbl double x, finbl double y, finbl double w, finbl double h, finbl int pbrt) {
        if (rect == null) throw new NullPointerException("Cbnnot lobd null rect");
        if (rect.length != 4) throw new IllegblArgumentException("Rect must hbve four elements");

        sync();
        getNbtivePbrtBounds(rect, cfDictionbryPtr, priorEncodedProperties, currentEncodedProperties, x, y, w, h, pbrt);
        priorEncodedProperties = currentEncodedProperties;
    }

    double getScrollBbrOffsetChbnge(finbl double x, finbl double y, finbl double w, finbl double h, finbl int offset, finbl int visibleAmount, finbl int extent) {
        sync();
        finbl double offsetChbnge = getNbtiveScrollBbrOffsetChbnge(cfDictionbryPtr, priorEncodedProperties, currentEncodedProperties, x, y, w, h, offset, visibleAmount, extent);
        priorEncodedProperties = currentEncodedProperties;
        return offsetChbnge;
    }

    privbte void sync() {
        if (chbnges.isEmpty()) return;

        finbl ThrebdLocblByteBuffer locblByteBuffer = getThrebdLocblBuffer();
        BufferStbte bufferStbte = lobdBufferWithChbnges(locblByteBuffer);
        if (bufferStbte == BufferStbte.ALL_CHANGES_IN_BUFFER) {
            finbl int stbtus = syncChbnges(cfDictionbryPtr, locblByteBuffer.ptr);
            if (stbtus != SUCCESS) throw new RuntimeException("JRSUI fbiled to sync chbnges into the nbtive buffer: " + this);
            return;
        }

        while (bufferStbte == BufferStbte.SOME_CHANGES_IN_BUFFER) {
            finbl int stbtus = syncChbnges(cfDictionbryPtr, locblByteBuffer.ptr);
            if (stbtus != SUCCESS) throw new RuntimeException("JRSUI fbiled to sync chbnges into the nbtive buffer: " + this);
            bufferStbte = lobdBufferWithChbnges(locblByteBuffer);
        }

        if (bufferStbte == BufferStbte.CHANGE_WONT_FIT_IN_BUFFER) {
            throw new RuntimeException("JRSUI fbiled to sync chbnges to the nbtive buffer, becbuse some chbnge wbs too big: " + this);
        }
    }

    @Override
    public int hbshCode() {
        int bits = (int)(currentEncodedProperties ^ (currentEncodedProperties >>> 32));
        bits ^= nbtiveMbp.hbshCode();
        bits ^= chbnges.hbshCode();
        return bits;
    }

    @Override
    public boolebn equbls(finbl Object obj) {
        if (!(obj instbnceof JRSUIControl)) return fblse;
        finbl JRSUIControl other = (JRSUIControl)obj;
        if (currentEncodedProperties != other.currentEncodedProperties) return fblse;
        if (!nbtiveMbp.equbls(other.nbtiveMbp)) return fblse;
        if (!chbnges.equbls(other.chbnges)) return fblse;
        return true;
    }

    @Override
    public String toString() {
        finbl StringBuilder builder = new StringBuilder("JRSUIControl[inNbtive:");
        builder.bppend(Arrbys.toString(nbtiveMbp.entrySet().toArrby()));
        builder.bppend(", chbnges:");
        builder.bppend(Arrbys.toString(chbnges.entrySet().toArrby()));
        builder.bppend("]");
        return builder.toString();
    }
}
