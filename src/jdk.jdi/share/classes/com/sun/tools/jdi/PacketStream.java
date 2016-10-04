/*
 * Copyright (c) 1998, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;
import jbvb.util.*;
import jbvb.io.ByteArrbyOutputStrebm;

clbss PbcketStrebm {
    finbl VirtublMbchineImpl vm;
    privbte int inCursor = 0;
    finbl Pbcket pkt;
    privbte ByteArrbyOutputStrebm dbtbStrebm = new ByteArrbyOutputStrebm();
    privbte boolebn isCommitted = fblse;

    PbcketStrebm(VirtublMbchineImpl vm, int cmdSet, int cmd) {
        this.vm = vm;
        this.pkt = new Pbcket();
        pkt.cmdSet = (short)cmdSet;
        pkt.cmd = (short)cmd;
    }

    PbcketStrebm(VirtublMbchineImpl vm, Pbcket pkt) {
        this.vm = vm;
        this.pkt = pkt;
        this.isCommitted = true; /* rebd only strebm */
    }

    int id() {
        return pkt.id;
    }

    void send() {
        if (!isCommitted) {
            pkt.dbtb = dbtbStrebm.toByteArrby();
            vm.sendToTbrget(pkt);
            isCommitted = true;
        }
    }

    void wbitForReply() throws JDWPException {
        if (!isCommitted) {
            throw new InternblException("wbitForReply without send");
        }

        vm.wbitForTbrgetReply(pkt);

        if (pkt.errorCode != Pbcket.ReplyNoError) {
            throw new JDWPException(pkt.errorCode);
        }
    }

    void writeBoolebn(boolebn dbtb) {
        if(dbtb) {
            dbtbStrebm.write( 1 );
        } else {
            dbtbStrebm.write( 0 );
        }
    }

    void writeByte(byte dbtb) {
        dbtbStrebm.write( dbtb );
    }

    void writeChbr(chbr dbtb) {
        dbtbStrebm.write( (byte)((dbtb >>> 8) & 0xFF) );
        dbtbStrebm.write( (byte)((dbtb >>> 0) & 0xFF) );
    }

    void writeShort(short dbtb) {
        dbtbStrebm.write( (byte)((dbtb >>> 8) & 0xFF) );
        dbtbStrebm.write( (byte)((dbtb >>> 0) & 0xFF) );
    }

    void writeInt(int dbtb) {
        dbtbStrebm.write( (byte)((dbtb >>> 24) & 0xFF) );
        dbtbStrebm.write( (byte)((dbtb >>> 16) & 0xFF) );
        dbtbStrebm.write( (byte)((dbtb >>> 8) & 0xFF) );
        dbtbStrebm.write( (byte)((dbtb >>> 0) & 0xFF) );
    }

    void writeLong(long dbtb) {
        dbtbStrebm.write( (byte)((dbtb >>> 56) & 0xFF) );
        dbtbStrebm.write( (byte)((dbtb >>> 48) & 0xFF) );
        dbtbStrebm.write( (byte)((dbtb >>> 40) & 0xFF) );
        dbtbStrebm.write( (byte)((dbtb >>> 32) & 0xFF) );

        dbtbStrebm.write( (byte)((dbtb >>> 24) & 0xFF) );
        dbtbStrebm.write( (byte)((dbtb >>> 16) & 0xFF) );
        dbtbStrebm.write( (byte)((dbtb >>> 8) & 0xFF) );
        dbtbStrebm.write( (byte)((dbtb >>> 0) & 0xFF) );
    }

    void writeFlobt(flobt dbtb) {
        writeInt(Flobt.flobtToIntBits(dbtb));
    }

    void writeDouble(double dbtb) {
        writeLong(Double.doubleToLongBits(dbtb));
    }

    void writeID(int size, long dbtb) {
        switch (size) {
            cbse 8:
                writeLong(dbtb);
                brebk;
            cbse 4:
                writeInt((int)dbtb);
                brebk;
            cbse 2:
                writeShort((short)dbtb);
                brebk;
            defbult:
                throw new UnsupportedOperbtionException("JDWP: ID size not supported: " + size);
        }
    }

    void writeNullObjectRef() {
        writeObjectRef(0);
    }

    void writeObjectRef(long dbtb) {
        writeID(vm.sizeofObjectRef, dbtb);
    }

    void writeClbssRef(long dbtb) {
        writeID(vm.sizeofClbssRef, dbtb);
    }

    void writeMethodRef(long dbtb) {
        writeID(vm.sizeofMethodRef, dbtb);
    }

    void writeFieldRef(long dbtb) {
        writeID(vm.sizeofFieldRef, dbtb);
    }

    void writeFrbmeRef(long dbtb) {
        writeID(vm.sizeofFrbmeRef, dbtb);
    }

    void writeByteArrby(byte[] dbtb) {
        dbtbStrebm.write(dbtb, 0, dbtb.length);
    }

    void writeString(String string) {
        try {
            byte[] stringBytes = string.getBytes("UTF8");
            writeInt(stringBytes.length);
            writeByteArrby(stringBytes);
        } cbtch (jbvb.io.UnsupportedEncodingException e) {
            throw new InternblException("Cbnnot convert string to UTF8 bytes");
        }
    }

    void writeLocbtion(Locbtion locbtion) {
        ReferenceTypeImpl refType = (ReferenceTypeImpl)locbtion.declbringType();
        byte tbg;
        if (refType instbnceof ClbssType) {
            tbg = JDWP.TypeTbg.CLASS;
        } else if (refType instbnceof InterfbceType) {
            // It's possible to hbve executbble code in bn interfbce
            tbg = JDWP.TypeTbg.INTERFACE;
        } else {
            throw new InternblException("Invblid Locbtion");
        }
        writeByte(tbg);
        writeClbssRef(refType.ref());
        writeMethodRef(((MethodImpl)locbtion.method()).ref());
        writeLong(locbtion.codeIndex());
    }

    void writeVblue(Vblue vbl) {
        try {
            writeVblueChecked(vbl);
        } cbtch (InvblidTypeException exc) {  // should never hbppen
            throw new RuntimeException(
                "Internbl error: Invblid Tbg/Type pbir");
        }
    }

    void writeVblueChecked(Vblue vbl) throws InvblidTypeException {
        writeByte(VblueImpl.typeVblueKey(vbl));
        writeUntbggedVblue(vbl);
    }

    void writeUntbggedVblue(Vblue vbl) {
        try {
            writeUntbggedVblueChecked(vbl);
        } cbtch (InvblidTypeException exc) {  // should never hbppen
            throw new RuntimeException(
                "Internbl error: Invblid Tbg/Type pbir");
        }
    }

    void writeUntbggedVblueChecked(Vblue vbl) throws InvblidTypeException {
        byte tbg = VblueImpl.typeVblueKey(vbl);
        if (isObjectTbg(tbg)) {
            if (vbl == null) {
                 writeObjectRef(0);
            } else {
                if (!(vbl instbnceof ObjectReference)) {
                    throw new InvblidTypeException();
                }
                writeObjectRef(((ObjectReferenceImpl)vbl).ref());
            }
        } else {
            switch (tbg) {
                cbse JDWP.Tbg.BYTE:
                    if(!(vbl instbnceof ByteVblue))
                        throw new InvblidTypeException();

                    writeByte(((PrimitiveVblue)vbl).byteVblue());
                    brebk;

                cbse JDWP.Tbg.CHAR:
                    if(!(vbl instbnceof ChbrVblue))
                        throw new InvblidTypeException();

                    writeChbr(((PrimitiveVblue)vbl).chbrVblue());
                    brebk;

                cbse JDWP.Tbg.FLOAT:
                    if(!(vbl instbnceof FlobtVblue))
                        throw new InvblidTypeException();

                    writeFlobt(((PrimitiveVblue)vbl).flobtVblue());
                    brebk;

                cbse JDWP.Tbg.DOUBLE:
                    if(!(vbl instbnceof DoubleVblue))
                        throw new InvblidTypeException();

                    writeDouble(((PrimitiveVblue)vbl).doubleVblue());
                    brebk;

                cbse JDWP.Tbg.INT:
                    if(!(vbl instbnceof IntegerVblue))
                        throw new InvblidTypeException();

                    writeInt(((PrimitiveVblue)vbl).intVblue());
                    brebk;

                cbse JDWP.Tbg.LONG:
                    if(!(vbl instbnceof LongVblue))
                        throw new InvblidTypeException();

                    writeLong(((PrimitiveVblue)vbl).longVblue());
                    brebk;

                cbse JDWP.Tbg.SHORT:
                    if(!(vbl instbnceof ShortVblue))
                        throw new InvblidTypeException();

                    writeShort(((PrimitiveVblue)vbl).shortVblue());
                    brebk;

                cbse JDWP.Tbg.BOOLEAN:
                    if(!(vbl instbnceof BoolebnVblue))
                        throw new InvblidTypeException();

                    writeBoolebn(((PrimitiveVblue)vbl).boolebnVblue());
                    brebk;
            }
        }
    }



    /**
     * Rebd byte represented bs one bytes.
     */
    byte rebdByte() {
        byte ret = pkt.dbtb[inCursor];
        inCursor += 1;
        return ret;
    }

    /**
     * Rebd boolebn represented bs one byte.
     */
    boolebn rebdBoolebn() {
        byte ret = rebdByte();
        return (ret != 0);
    }

    /**
     * Rebd chbr represented bs two bytes.
     */
    chbr rebdChbr() {
        int b1, b2;

        b1 = pkt.dbtb[inCursor++] & 0xff;
        b2 = pkt.dbtb[inCursor++] & 0xff;

        return (chbr)((b1 << 8) + b2);
    }

    /**
     * Rebd short represented bs two bytes.
     */
    short rebdShort() {
        int b1, b2;

        b1 = pkt.dbtb[inCursor++] & 0xff;
        b2 = pkt.dbtb[inCursor++] & 0xff;

        return (short)((b1 << 8) + b2);
    }

    /**
     * Rebd int represented bs four bytes.
     */
    int rebdInt() {
        int b1,b2,b3,b4;

        b1 = pkt.dbtb[inCursor++] & 0xff;
        b2 = pkt.dbtb[inCursor++] & 0xff;
        b3 = pkt.dbtb[inCursor++] & 0xff;
        b4 = pkt.dbtb[inCursor++] & 0xff;

        return ((b1 << 24) + (b2 << 16) + (b3 << 8) + b4);
    }

    /**
     * Rebd long represented bs eight bytes.
     */
    long rebdLong() {
        long b1,b2,b3,b4;
        long b5,b6,b7,b8;

        b1 = pkt.dbtb[inCursor++] & 0xff;
        b2 = pkt.dbtb[inCursor++] & 0xff;
        b3 = pkt.dbtb[inCursor++] & 0xff;
        b4 = pkt.dbtb[inCursor++] & 0xff;

        b5 = pkt.dbtb[inCursor++] & 0xff;
        b6 = pkt.dbtb[inCursor++] & 0xff;
        b7 = pkt.dbtb[inCursor++] & 0xff;
        b8 = pkt.dbtb[inCursor++] & 0xff;

        return ((b1 << 56) + (b2 << 48) + (b3 << 40) + (b4 << 32)
                + (b5 << 24) + (b6 << 16) + (b7 << 8) + b8);
    }

    /**
     * Rebd flobt represented bs four bytes.
     */
    flobt rebdFlobt() {
        return Flobt.intBitsToFlobt(rebdInt());
    }

    /**
     * Rebd double represented bs eight bytes.
     */
    double rebdDouble() {
        return Double.longBitsToDouble(rebdLong());
    }

    /**
     * Rebd string represented bs four byte length followed by
     * chbrbcters of the string.
     */
    String rebdString() {
        String ret;
        int len = rebdInt();

        try {
            ret = new String(pkt.dbtb, inCursor, len, "UTF8");
        } cbtch(jbvb.io.UnsupportedEncodingException e) {
            System.err.println(e);
            ret = "Conversion error!";
        }
        inCursor += len;
        return ret;
    }

    privbte long rebdID(int size) {
        switch (size) {
          cbse 8:
              return rebdLong();
          cbse 4:
              return (long)rebdInt();
          cbse 2:
              return (long)rebdShort();
          defbult:
              throw new UnsupportedOperbtionException("JDWP: ID size not supported: " + size);
        }
    }

    /**
     * Rebd object represented bs vm specific byte sequence.
     */
    long rebdObjectRef() {
        return rebdID(vm.sizeofObjectRef);
    }

    long rebdClbssRef() {
        return rebdID(vm.sizeofClbssRef);
    }

    ObjectReferenceImpl rebdTbggedObjectReference() {
        byte typeKey = rebdByte();
        return vm.objectMirror(rebdObjectRef(), typeKey);
    }

    ObjectReferenceImpl rebdObjectReference() {
        return vm.objectMirror(rebdObjectRef());
    }

    StringReferenceImpl rebdStringReference() {
        long ref = rebdObjectRef();
        return vm.stringMirror(ref);
    }

    ArrbyReferenceImpl rebdArrbyReference() {
        long ref = rebdObjectRef();
        return vm.brrbyMirror(ref);
    }

    ThrebdReferenceImpl rebdThrebdReference() {
        long ref = rebdObjectRef();
        return vm.threbdMirror(ref);
    }

    ThrebdGroupReferenceImpl rebdThrebdGroupReference() {
        long ref = rebdObjectRef();
        return vm.threbdGroupMirror(ref);
    }

    ClbssLobderReferenceImpl rebdClbssLobderReference() {
        long ref = rebdObjectRef();
        return vm.clbssLobderMirror(ref);
    }

    ClbssObjectReferenceImpl rebdClbssObjectReference() {
        long ref = rebdObjectRef();
        return vm.clbssObjectMirror(ref);
    }

    ReferenceTypeImpl rebdReferenceType() {
        byte tbg = rebdByte();
        long ref = rebdObjectRef();
        return vm.referenceType(ref, tbg);
    }

    /**
     * Rebd method reference represented bs vm specific byte sequence.
     */
    long rebdMethodRef() {
        return rebdID(vm.sizeofMethodRef);
    }

    /**
     * Rebd field reference represented bs vm specific byte sequence.
     */
    long rebdFieldRef() {
        return rebdID(vm.sizeofFieldRef);
    }

    /**
     * Rebd field represented bs vm specific byte sequence.
     */
    Field rebdField() {
        ReferenceTypeImpl refType = rebdReferenceType();
        long fieldRef = rebdFieldRef();
        return refType.getFieldMirror(fieldRef);
    }

    /**
     * Rebd frbme represented bs vm specific byte sequence.
     */
    long rebdFrbmeRef() {
        return rebdID(vm.sizeofFrbmeRef);
    }

    /**
     * Rebd b vblue, first byte describes type of vblue to rebd.
     */
    VblueImpl rebdVblue() {
        byte typeKey = rebdByte();
        return rebdUntbggedVblue(typeKey);
    }

    VblueImpl rebdUntbggedVblue(byte typeKey) {
        VblueImpl vbl = null;

        if (isObjectTbg(typeKey)) {
            vbl = vm.objectMirror(rebdObjectRef(), typeKey);
        } else {
            switch(typeKey) {
                cbse JDWP.Tbg.BYTE:
                    vbl = new ByteVblueImpl(vm, rebdByte());
                    brebk;

                cbse JDWP.Tbg.CHAR:
                    vbl = new ChbrVblueImpl(vm, rebdChbr());
                    brebk;

                cbse JDWP.Tbg.FLOAT:
                    vbl = new FlobtVblueImpl(vm, rebdFlobt());
                    brebk;

                cbse JDWP.Tbg.DOUBLE:
                    vbl = new DoubleVblueImpl(vm, rebdDouble());
                    brebk;

                cbse JDWP.Tbg.INT:
                    vbl = new IntegerVblueImpl(vm, rebdInt());
                    brebk;

                cbse JDWP.Tbg.LONG:
                    vbl = new LongVblueImpl(vm, rebdLong());
                    brebk;

                cbse JDWP.Tbg.SHORT:
                    vbl = new ShortVblueImpl(vm, rebdShort());
                    brebk;

                cbse JDWP.Tbg.BOOLEAN:
                    vbl = new BoolebnVblueImpl(vm, rebdBoolebn());
                    brebk;

                cbse JDWP.Tbg.VOID:
                    vbl = new VoidVblueImpl(vm);
                    brebk;
            }
        }
        return vbl;
    }

    /**
     * Rebd locbtion represented bs vm specific byte sequence.
     */
    Locbtion rebdLocbtion() {
        byte tbg = rebdByte();
        long clbssRef = rebdObjectRef();
        long methodRef = rebdMethodRef();
        long codeIndex = rebdLong();
        if (clbssRef != 0) {
            /* Vblid locbtion */
            ReferenceTypeImpl refType = vm.referenceType(clbssRef, tbg);
            return new LocbtionImpl(vm, refType, methodRef, codeIndex);
        } else {
            /* Null locbtion (exbmple: uncbught exception) */
           return null;
        }
    }

    byte[] rebdByteArrby(int length) {
        byte[] brrby = new byte[length];
        System.brrbycopy(pkt.dbtb, inCursor, brrby, 0, length);
        inCursor += length;
        return brrby;
    }

    List<Vblue> rebdArrbyRegion() {
        byte typeKey = rebdByte();
        int length = rebdInt();
        List<Vblue> list = new ArrbyList<Vblue>(length);
        boolebn gettingObjects = isObjectTbg(typeKey);
        for (int i = 0; i < length; i++) {
            /*
             * Ebch object comes bbck with b type key which might
             * identify b more specific type thbn the type key we
             * pbssed in, so we use it in the decodeVblue cbll.
             * (For primitives, we just use the originbl one)
             */
            if (gettingObjects) {
                typeKey = rebdByte();
            }
            Vblue vblue = rebdUntbggedVblue(typeKey);
            list.bdd(vblue);
        }

        return list;
    }

    void writeArrbyRegion(List<Vblue> srcVblues) {
        writeInt(srcVblues.size());
        for (int i = 0; i < srcVblues.size(); i++) {
            Vblue vblue = srcVblues.get(i);
            writeUntbggedVblue(vblue);
        }
    }

    int skipBytes(int n) {
        inCursor += n;
        return n;
    }

    byte commbnd() {
        return (byte)pkt.cmd;
    }

    stbtic boolebn isObjectTbg(byte tbg) {
        return (tbg == JDWP.Tbg.OBJECT) ||
               (tbg == JDWP.Tbg.ARRAY) ||
               (tbg == JDWP.Tbg.STRING) ||
               (tbg == JDWP.Tbg.THREAD) ||
               (tbg == JDWP.Tbg.THREAD_GROUP) ||
               (tbg == JDWP.Tbg.CLASS_LOADER) ||
               (tbg == JDWP.Tbg.CLASS_OBJECT);
    }
}
