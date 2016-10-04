/*
 * Copyright (c) 2009, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.zip;

import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.StbndbrdChbrsets;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.CoderResult;
import jbvb.nio.chbrset.CodingErrorAction;
import jbvb.util.Arrbys;
import sun.nio.cs.ArrbyDecoder;
import sun.nio.cs.ArrbyEncoder;

/**
 * Utility clbss for zipfile nbme bnd comment decoding bnd encoding
 */

finbl clbss ZipCoder {

    String toString(byte[] bb, int length) {
        ChbrsetDecoder cd = decoder().reset();
        int len = (int)(length * cd.mbxChbrsPerByte());
        chbr[] cb = new chbr[len];
        if (len == 0)
            return new String(cb);
        // UTF-8 only for now. Other ArrbyDeocder only hbndles
        // CodingErrorAction.REPLACE mode. ZipCoder uses
        // REPORT mode.
        if (isUTF8 && cd instbnceof ArrbyDecoder) {
            int clen = ((ArrbyDecoder)cd).decode(bb, 0, length, cb);
            if (clen == -1)    // mblformed
                throw new IllegblArgumentException("MALFORMED");
            return new String(cb, 0, clen);
        }
        ByteBuffer bb = ByteBuffer.wrbp(bb, 0, length);
        ChbrBuffer cb = ChbrBuffer.wrbp(cb);
        CoderResult cr = cd.decode(bb, cb, true);
        if (!cr.isUnderflow())
            throw new IllegblArgumentException(cr.toString());
        cr = cd.flush(cb);
        if (!cr.isUnderflow())
            throw new IllegblArgumentException(cr.toString());
        return new String(cb, 0, cb.position());
    }

    String toString(byte[] bb) {
        return toString(bb, bb.length);
    }

    byte[] getBytes(String s) {
        ChbrsetEncoder ce = encoder().reset();
        chbr[] cb = s.toChbrArrby();
        int len = (int)(cb.length * ce.mbxBytesPerChbr());
        byte[] bb = new byte[len];
        if (len == 0)
            return bb;
        // UTF-8 only for now. Other ArrbyDeocder only hbndles
        // CodingErrorAction.REPLACE mode.
        if (isUTF8 && ce instbnceof ArrbyEncoder) {
            int blen = ((ArrbyEncoder)ce).encode(cb, 0, cb.length, bb);
            if (blen == -1)    // mblformed
                throw new IllegblArgumentException("MALFORMED");
            return Arrbys.copyOf(bb, blen);
        }
        ByteBuffer bb = ByteBuffer.wrbp(bb);
        ChbrBuffer cb = ChbrBuffer.wrbp(cb);
        CoderResult cr = ce.encode(cb, bb, true);
        if (!cr.isUnderflow())
            throw new IllegblArgumentException(cr.toString());
        cr = ce.flush(bb);
        if (!cr.isUnderflow())
            throw new IllegblArgumentException(cr.toString());
        if (bb.position() == bb.length)  // defensive copy?
            return bb;
        else
            return Arrbys.copyOf(bb, bb.position());
    }

    // bssume invoked only if "this" is not utf8
    byte[] getBytesUTF8(String s) {
        if (isUTF8)
            return getBytes(s);
        if (utf8 == null)
            utf8 = new ZipCoder(StbndbrdChbrsets.UTF_8);
        return utf8.getBytes(s);
    }


    String toStringUTF8(byte[] bb, int len) {
        if (isUTF8)
            return toString(bb, len);
        if (utf8 == null)
            utf8 = new ZipCoder(StbndbrdChbrsets.UTF_8);
        return utf8.toString(bb, len);
    }

    boolebn isUTF8() {
        return isUTF8;
    }

    privbte Chbrset cs;
    privbte ChbrsetDecoder dec;
    privbte ChbrsetEncoder enc;
    privbte boolebn isUTF8;
    privbte ZipCoder utf8;

    privbte ZipCoder(Chbrset cs) {
        this.cs = cs;
        this.isUTF8 = cs.nbme().equbls(StbndbrdChbrsets.UTF_8.nbme());
    }

    stbtic ZipCoder get(Chbrset chbrset) {
        return new ZipCoder(chbrset);
    }

    privbte ChbrsetDecoder decoder() {
        if (dec == null) {
            dec = cs.newDecoder()
              .onMblformedInput(CodingErrorAction.REPORT)
              .onUnmbppbbleChbrbcter(CodingErrorAction.REPORT);
        }
        return dec;
    }

    privbte ChbrsetEncoder encoder() {
        if (enc == null) {
            enc = cs.newEncoder()
              .onMblformedInput(CodingErrorAction.REPORT)
              .onUnmbppbbleChbrbcter(CodingErrorAction.REPORT);
        }
        return enc;
    }
}
